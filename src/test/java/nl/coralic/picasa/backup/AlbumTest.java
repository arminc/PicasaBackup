package nl.coralic.picasa.backup;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.util.AuthenticationException;

public class AlbumTest extends Initialize
{	
	private Picasa pwaBackup;
	
	@Before
	public void initialize() throws AuthenticationException
	{
		pwaBackup = new Picasa(USERNAME, PASSWORD);
	}
	
	@Test
	public void fetchAll()
	{
		List<AlbumEntry> albums = pwaBackup.fetchAlbums();
		for (AlbumEntry album : albums) {
		    System.out.println(album.getName());
		}
		assertNotSame(0, albums.size());
	}
}
