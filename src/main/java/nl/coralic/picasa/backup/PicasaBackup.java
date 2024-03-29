package nl.coralic.picasa.backup;

import org.apache.log4j.Logger;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.content.MediaContent;
import nl.coralic.picasa.backup.db.AlbumEntity;
import nl.coralic.picasa.backup.db.Database;
import nl.coralic.picasa.backup.db.DatabaseFactory;
import nl.coralic.picasa.backup.db.MediaEntity;
import nl.coralic.picasa.backup.file.FileHandler;
import nl.coralic.picasa.backup.service.Picasa;
import nl.coralic.picasa.backup.service.PicasaFactory;

import com.google.gdata.util.AuthenticationException;

public class PicasaBackup
{
	static Logger logger = Logger.getLogger("PicasaBackup");
	static Picasa picasa;
	static Arguments arguments;
	static Database database;

	public static void run(Arguments args) throws AuthenticationException
	{
		arguments = args;
		createDatabase();
		createPicasa();
		startProcessing();
		closeDatabase();
	}
	
	private static void createDatabase()
	{
		database = DatabaseFactory.createDatabase(arguments.getRootPath());
	}
	
	private static void closeDatabase()
	{
		database.closeDatabase();
	}

	private static void createPicasa() throws AuthenticationException
	{
		picasa = PicasaFactory.createPicasa();
		picasa.login(arguments.getUsername(), arguments.getPassword());
	}

	private static void startProcessing()
	{
		Albums albums = picasa.fetchAlbums();
		for (Album album : albums)
		{
			albumExistsOrSave(album);
			logger.info("Processing album " + album.getAlbumName());
			processMedia(album);
		}
	}
	
	private static void albumExistsOrSave(Album album)
	{
		if(!database.albumExists(album.getAlbumId()))
		{
			AlbumEntity albumEntity = new AlbumEntity(album.getAlbumId());
			database.saveAlbum(albumEntity);
		}		
	}

	private static void processMedia(Album album)
	{
		Media media = picasa.fetchMedia(album.getAlbumId());
		for (MediaContent mediaContent : media)
		{
			if(downloadMediaContent(mediaContent.getId(),mediaContent.getLastChanged()))
			{
				saveMediaToFile(mediaContent, album.getAlbumName());			
				saveMediaToDatabase(mediaContent, album.getAlbumId());
			}
			else
			{
				logger.info("Skip media " + mediaContent.getFileName());
			}
		}
	}
	
	private static boolean downloadMediaContent(String mediaId, long lastChanged)
	{
		if(database.mediaExists(mediaId))
		{
			MediaEntity mediaEntity = database.getMediaEntity(mediaId);
			if(lastChanged == mediaEntity.getLastChanged())
			{
				return false;
			}
		}
		return true;
	}
	
	private static void saveMediaToFile(MediaContent mediaContent, String albumName)
	{
		String albumPath = FileHandler.constructNewPath(arguments.getRootPath(), albumName);
		String mediaContentFileName = FileHandler.constructMediaContentFileNameWithId(mediaContent.getFileName(), mediaContent.getId());
		String saveToPath = FileHandler.constructNewPath(albumPath, mediaContentFileName);
		logger.info("Downloading media " + mediaContent.getFileName() + " local file name: " + mediaContentFileName);
		FileHandler.saveMediaToFile(mediaContent.getContentUrl(),saveToPath);
	}
	
	private static void saveMediaToDatabase(MediaContent mediaContent, String albumId)
	{
		MediaEntity mediaEntity = new MediaEntity(albumId, mediaContent.getId(), mediaContent.getFileName(), mediaContent.getLastChanged());
		database.saveMedia(mediaEntity);
	}
}
