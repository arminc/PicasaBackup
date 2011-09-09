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

	public static void run(String[] args) throws AuthenticationException
	{
		createPicasa(args);
		fetchMediaThenSave(args);
	}

	private static void createPicasa(String[] args)
			throws AuthenticationException
	{
		picasa = PicasaFactory.createPicasa();
		picasa.login(args[0], args[1]);
	}

	private static void fetchMediaThenSave(String[] args)
	{
		Albums albums = picasa.fetchAlbums();
		for (Album album : albums)
		{
			logger.info("Downloading album " + album.getAlbumName());
			String albumPath = FileHandler.constructFolderPath(args[2],
					album.getAlbumName());
			Media media = picasa.fetchMedia(album.getAlbumId());
			saveMediaToFile(media, albumPath);
		}
	}

	private static void saveMediaToFile(Media media, String savePath)
	{
		for (MediaContent mediaContent : media)
		{
			String mediaPath = FileHandler.constructFolderPath(savePath,
					mediaContent.getName());
			logger.info("Downloading media " + mediaContent.getName());
			FileHandler
					.saveMediaToFile(mediaContent.getContentUrl(), mediaPath);
		}
	}
}
