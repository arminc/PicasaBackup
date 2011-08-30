package nl.coralic.picasa.backup.apidemo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.Category;
import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;

public class PicasaWebAlbumsAPI
{	
	private static PicasawebService picasa;

	public static void main(String args[]) throws Exception
	{
		initalize();
		
		//fetch albums
		URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/default?kind=album");
		UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);

		for (AlbumEntry myAlbum : myUserFeed.getAlbumEntries()) 
		{
		    System.out.println("Album name: " + myAlbum.getTitle().getPlainText());
		    System.out.println("Album ID: " + myAlbum.getGphotoId());
		    feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/default/albumid/"+myAlbum.getGphotoId()+"?imgmax=d");
			AlbumFeed feed = picasa.getFeed(feedUrl, AlbumFeed.class);
			
			for(PhotoEntry photo : feed.getPhotoEntries()) 
			{
			    System.out.println("Photo name: " + photo.getTitle().getPlainText());
			    List<MediaContent> contentList = photo.getMediaContents();
			    for(MediaContent content : contentList)
			    {
			    	System.out.println("Photo type: " + content.getType());
			    	System.out.println("Photo medium: " + content.getMedium());
			    	System.out.println("Photo height: " + content.getHeight());
			    	System.out.println("Photo url: " + content.getUrl());
			    }
			}
		}

	}
	
	public static void initalize() throws FileNotFoundException, IOException, AuthenticationException
	{
		Properties p = new Properties();
		p.load(new FileReader(new File("config.properties")));
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		picasa = new PicasawebService("picasatest");
		picasa.setUserCredentials(USERNAME, PASSWORD);
	}
}
