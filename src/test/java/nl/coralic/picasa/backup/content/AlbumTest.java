package nl.coralic.picasa.backup.content;

import nl.coralic.picasa.backup.content.Album;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.photos.AlbumEntry;

public class AlbumTest
{
	private static final String ALBUMID = "ID123456789";
	private static final String ALBUMNAME = "album1";
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
		TextConstruct fakeText = createFakeTextConstruct();
		when(fakeAlbumEntry.getTitle()).thenReturn(fakeText);
		return fakeAlbumEntry;
	}
	
	private TextConstruct createFakeTextConstruct()
	{
		TextConstruct fakeText = mock(TextConstruct.class);
		when(fakeText.getPlainText()).thenReturn(ALBUMNAME);
		return fakeText;
	}

	@Test
	public void getAlbumId()
	{
		assertEquals(ALBUMID, album.getAlbumId());
	}
	
	@Test
	public void getAlbumName()
	{
		assertEquals(ALBUMNAME, album.getAlbumName());
	}
}
