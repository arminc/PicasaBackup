package nl.coralic.picasa.backup;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gdata.data.photos.AlbumEntry;

public class AlbumsTest
{		
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
		return mock(AlbumEntry.class);
	}
	
	@Test
	public void getSize()
	{
		assertSame(1, albums.size());
	}
	
	@Test 
	public void getIterator()
	{
		assertNotNull(albums.iterator());
	}

}
