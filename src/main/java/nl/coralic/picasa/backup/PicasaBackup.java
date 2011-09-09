package nl.coralic.picasa.backup;

import org.apache.log4j.Logger;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.content.MediaContent;
import nl.coralic.picasa.backup.file.FileHandler;
import nl.coralic.picasa.backup.service.Picasa;
import nl.coralic.picasa.backup.service.PicasaFactory;

import com.google.gdata.util.AuthenticationException;

public class PicasaBackup
{
	static Logger logger = Logger.getLogger("PicasaBackup");
	static Picasa picasa;
	static Arguments arguments;

	public static void run(Arguments args) throws AuthenticationException
	{
		arguments = args;
		createPicasa();
		fetchMediaThenSave();
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
}
