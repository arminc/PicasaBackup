package nl.coralic.picasa.backup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import nl.coralic.picasa.backup.content.Album;
import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;
import nl.coralic.picasa.backup.content.MediaContent;
import nl.coralic.picasa.backup.file.FileHandler;

import com.google.gdata.util.AuthenticationException;

public class PicasaBackup
{

	//TODO: can an album have folders/albums inside it?
	//TODO: add log4j
	
	public static void main(String[] args) throws AuthenticationException, MalformedURLException, IOException
	{
		checkArguments(args);
		if(!FileHandler.folderExists(args[2]))
		{
			System.out.println("Folder " + args[2] + " does not exist.");
			exit();
		}
		Picasa picasa = PicasaFactory.createPicasa();
		picasa.login(args[0], args[1]);
		Albums albums = picasa.fetchAlbums();
		for(Album album : albums)
		{
			FileHandler.createFolder(args[2], album.getAlbumName());
			Media media = picasa.fetchMedia(album.getAlbumId());
			for(MediaContent mediaContent : media)
			{
				System.out.println("Download: " + mediaContent.getContentUrl());
				FileUtils.copyURLToFile(new URL(mediaContent.getContentUrl()), new File(mediaContent.getName()));
			}
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
