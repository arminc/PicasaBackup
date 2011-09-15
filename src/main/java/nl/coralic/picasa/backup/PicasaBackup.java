package nl.coralic.picasa.backup;

import org.apache.log4j.Logger;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.content.MediaContent;
import nl.coralic.picasa.backup.db.AlbumEntity;
import nl.coralic.picasa.backup.db.Database;
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
		database = new Database(arguments.getRootPath());
		createPicasa();
		fetchMediaThenSave();
		database.closeDatabase();
	}

	private static void createPicasa() throws AuthenticationException
	{
		picasa = PicasaFactory.createPicasa();
		picasa.login(arguments.getUsername(), arguments.getPassword());
	}

	private static void fetchMediaThenSave()
	{
		Albums albums = picasa.fetchAlbums();
		for (Album album : albums)
		{
			albumExistsOrSave(album.getAlbumId());
			logger.info("Downloading album " + album.getAlbumName());
			String albumPath = FileHandler.constructNewPath(
					arguments.getRootPath(), album.getAlbumName());
			Media media = picasa.fetchMedia(album.getAlbumId());
			saveMediaToFile(media, albumPath);
		}
	}

	private static void saveMediaToFile(Media media, String albumPath)
	{
		for (MediaContent mediaContent : media)
		{
			String saveToPath = FileHandler.constructNewPath(albumPath,
					mediaContent.getName());
			logger.info("Downloading media " + mediaContent.getName());
			FileHandler.saveMediaToFile(mediaContent.getContentUrl(),
					saveToPath);
		}
	}
	
	private static void albumExistsOrSave(String albumId)
	{
		if(!database.albumExists(albumId))
		{
			AlbumEntity albumEntity = new AlbumEntity(albumId);
			database.saveAlbum(albumEntity);
		}		
	}
}
