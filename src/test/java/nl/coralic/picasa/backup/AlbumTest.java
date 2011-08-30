package nl.coralic.picasa.backup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gdata.data.photos.AlbumEntry;

public class AlbumTest
{
	private static final String ALBUMID = "ID123456789";
	Album album;

	@Before
	public void initialize()
	{
		album = new Album(createFakeAlbumEntry());
	}

	private AlbumEntry createFakeAlbumEntry()
	{
		AlbumEntry fakeAlbumEntry = mock(AlbumEntry.class);
		when(fakeAlbumEntry.getGphotoId()).thenReturn(ALBUMID);
		return fakeAlbumEntry;
	}

	@Test
	public void getAlbumId()
	{
		assertEquals(ALBUMID, album.getAlbumId());
	}
}
