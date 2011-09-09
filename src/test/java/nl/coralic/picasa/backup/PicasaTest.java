package nl.coralic.picasa.backup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nl.coralic.picasa.backup.service.Picasa;

import org.junit.Before;
import org.junit.Test;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PicasaTest
{
	Picasa picasa;
	private static final String ALBUMID = "FAKEALBUMID";
	
	@Before
	public void initialize() throws MalformedURLException, IOException, ServiceException
	{
		PicasawebService picasaServiceMock = createFakePicasawebService();
		picasa = new Picasa();		
		picasa.setPicasaService(picasaServiceMock);
		
	}
	
	private PicasawebService createFakePicasawebService() throws MalformedURLException, IOException, ServiceException
	{
		PicasawebService picasaServiceMock = mock(PicasawebService.class);
		UserFeed userFeed = createFakeUserFeed();
		when(picasaServiceMock.getFeed(new URL("https://picasaweb.google.com/data/feed/api/user/default?kind=album"), UserFeed.class)).thenReturn(userFeed);
		AlbumFeed albumFeed = createFakeAlbumFeed();
		when(picasaServiceMock.getFeed(new URL("https://picasaweb.google.com/data/feed/api/user/default/albumid/"+ALBUMID+"?imgmax=d"), AlbumFeed.class)).thenReturn(albumFeed);
		return picasaServiceMock;
	}
	
	private UserFeed createFakeUserFeed()
	{
		UserFeed userFeed = mock(UserFeed.class);
		List<AlbumEntry> list = new ArrayList<AlbumEntry>();
		list.add(new AlbumEntry());
		list.add(new AlbumEntry());
		when(userFeed.getAlbumEntries()).thenReturn(list);
		return userFeed;
	}
	
	private AlbumFeed createFakeAlbumFeed()
	{
		AlbumFeed albumFeed = mock(AlbumFeed.class);
		List<PhotoEntry> list = new ArrayList<PhotoEntry>();
		list.add(new PhotoEntry());
		list.add(new PhotoEntry());
		when(albumFeed.getPhotoEntries()).thenReturn(list);
		return albumFeed;
	}

	@Test
	public void fetchAlbums()
	{
		assertEquals(2, picasa.fetchAlbums().size());
	}
	
	@Test
	public void fetchMedia()
	{
		assertEquals(2, picasa.fetchMedia(ALBUMID).size());
	}
}
