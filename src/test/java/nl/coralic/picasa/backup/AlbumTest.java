package nl.coralic.picasa.backup;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gdata.data.photos.AlbumEntry;

public class AlbumTest
{		
	private static final String ALBUMID = "ID123456789";
	Albums albums;
	
	@Before
	public void initialize()
	{
		albums = new Albums(createFakeAlbumList());
	}
	
	private List<AlbumEntry> createFakeAlbumList()
	{
		List<AlbumEntry> albumList = new ArrayList<AlbumEntry>();
		albumList.add(createFakeAlbumEntry());
		return albumList;
	}
	
	private AlbumEntry createFakeAlbumEntry()
	{
		AlbumEntry fakeAlbumEntry = mock(AlbumEntry.class);
		when(fakeAlbumEntry.getGphotoId()).thenReturn(ALBUMID);
		return fakeAlbumEntry;
	}
	
	@Test
	public void getSize()
	{
		assertSame(1, albums.size());
	}
	
	@Test 
	public void getAlbumId()
	{
		for(Album album : albums)
		{
			assertEquals(ALBUMID, album.getAlbumId());
			return;
		}
		fail("Did not go in to for loop.");
	}
	
	@Test 
	public void getIter()
	{
		assertNotNull(albums.iterator());
	}

}
