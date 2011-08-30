package nl.coralic.picasa.backup;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.file.FileHandler;

import com.google.gdata.util.AuthenticationException;

public class PicasaBackup
{

	//TODO: can an album have folders/albums inside it?
	//TODO: add log4j
	
	public static void main(String[] args) throws AuthenticationException
	{
		checkArguments(args);
		if(!FileHandler.folderExists(args[2]))
		{
			System.out.println("Folder " + args[2] + " does not exist.");
			exit();
		}
		Picasa picasa = new Picasa();
		picasa.login(args[0], args[1]);
		Albums albums = picasa.fetchAlbums();
		for(Album album : albums)
		{
			FileHandler.createFolder(args[2], album.getAlbumName());
			Media media = picasa.fetchMedia(album.getAlbumId());
		}
			//download photos
			//download videos
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
}
