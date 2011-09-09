package nl.coralic.picasa.backup.service;

import java.net.URL;

import nl.coralic.picasa.backup.content.Albums;
import nl.coralic.picasa.backup.content.Media;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;

public class Picasa
{
	private PicasawebService picasaService;
	
	public void setPicasaService(PicasawebService picasaService)
	{
		this.picasaService = picasaService;
	}
	
	public void login(String username, String password) throws AuthenticationException
	{
		validateUsernamePassword(username, password);
		setCredentials(username, password);
	}

	private void validateUsernamePassword(String username, String password)
			throws AuthenticationException
	{
		if (username == null || username.equals(""))
		{
			throw new AuthenticationException("Username can not be empty");
		}
		if (password == null || password.equals(""))
		{
			throw new AuthenticationException("Password can not be empty");
		}
	}

	private void setCredentials(String username, String password)
			throws AuthenticationException
	{
		picasaService.setUserCredentials(username, password);
	}

	public Albums fetchAlbums()
	{
		UserFeed userFeed;
		try
		{
			userFeed = picasaService.getFeed(new URL("https://picasaweb.google.com/data/feed/api/user/default?kind=album"), UserFeed.class);
		} catch (Exception e)
		{
			return new Albums();
		}
		return new Albums(userFeed.getAlbumEntries());
	}

	public Media fetchMedia(String albumid)
	{
		AlbumFeed albumFeed;
		try
		{
			albumFeed = picasaService.getFeed(new URL("https://picasaweb.google.com/data/feed/api/user/default/albumid/"+albumid+"?imgmax=d"), AlbumFeed.class);
		} catch (Exception e)
		{
			return new Media();
		}
		
		return new Media(albumFeed.getPhotoEntries());
	}
}
