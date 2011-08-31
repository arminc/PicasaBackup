package nl.coralic.picasa.backup;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.content.MediaContent;
import nl.coralic.picasa.backup.file.FileHandler;

import com.google.gdata.util.AuthenticationException;

public class PicasaBackup
{
	static Logger logger = Logger.getLogger("PicasaBackup");
	static Picasa picasa;
	
	public static void main(String[] args) throws AuthenticationException, MalformedURLException, IOException
	{
		checkArguments(args);
		LogFactory.initializeLog4J(args);
		checkTargetFolder(args);
		createPicasa(args);
		fetchMediaThenSave(args);
	}
	
	private static void checkArguments(String[] args)
	{
		if(args.length != 3)
		{
			printUsage();
			exit();
		}
	}

	private static void printUsage()
	{
		System.out.println("Use as follow: \n PWABackup Username Password BackupFolder");
	}
	
	private static void exit()
	{
		System.exit(0);
	}
	
	private static void checkTargetFolder(String[] args)
	{
		if(!FileHandler.folderExists(args[2]))
		{
			logger.info("Folder " + args[2] + " does not exist.");
			exit();
		}
	}
	
	private static void createPicasa(String[] args) throws AuthenticationException
	{
		picasa = PicasaFactory.createPicasa();
		picasa.login(args[0], args[1]);
	}
	
	private static void fetchMediaThenSave(String[] args)
	{
		Albums albums = picasa.fetchAlbums();
		for(Album album : albums)
		{
			logger.info("Downloading album " + album.getAlbumName());
			String albumPath = FileHandler.constructFolderPath(args[2], album.getAlbumName());
			Media media = picasa.fetchMedia(album.getAlbumId());
			saveMediaToFile(media, albumPath);
		}
	}
	
	private static void saveMediaToFile(Media media, String savePath)
	{
		for(MediaContent mediaContent : media)
		{
			String mediaPath = FileHandler.constructFolderPath(savePath, mediaContent.getName());
			logger.info("Downloading media " + mediaContent.getName());
			FileHandler.saveMediaToFile(mediaContent.getContentUrl(), mediaPath);
		}
	}
}
