package nl.coralic.picasa.backup.content;

import java.util.ArrayList;
import java.util.List;

import nl.coralic.picasa.backup.content.Media;

import org.junit.Before;
import org.junit.Test;

import com.google.gdata.data.photos.PhotoEntry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MediaTest
{
	Media media;
	
	@Before
	public void initialize()
	{
		media = new Media(createFakeMediaList());
	}
	
	private List<PhotoEntry> createFakeMediaList()
	{
		List<PhotoEntry> mediaList = new ArrayList<PhotoEntry>();
		mediaList.add(createFakePhotoEntry());
		return mediaList;
	}
	
	private PhotoEntry createFakePhotoEntry()
	{
		return mock(PhotoEntry.class);
	}
	
	@Test
	public void getIterator()
	{
		assertNotNull(media.iterator());
	}
}
