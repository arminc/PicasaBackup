package nl.coralic.picasa.backup.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.photos.PhotoEntry;

public class MediaContentTest
{
	MediaContent mediaContentPhoto;
	MediaContent mediaContentVideo;
	private static final String NAME = "test.jpg";
	private static final String URL = "http://picasa.com/photo.jpg";
	private static final String ID = "id12345";
	
	@Before
	public void initialize()
	{
		mediaContentPhoto = new MediaContent(createFakePhotoEntryPhoto());
		mediaContentVideo = new MediaContent(createFakePhotoEntryVideo());
	}
	
	private PhotoEntry createFakePhotoEntryPhoto()
	{
		PhotoEntry fakePhotoEntry = createFakePhotoEntry();
		List<com.google.gdata.data.media.mediarss.MediaContent> fakeList = createFakeList(1);
		when(fakePhotoEntry.getMediaContents()).thenReturn(fakeList);
		when(fakePhotoEntry.getUpdated()).thenReturn(new DateTime(1000));
		return fakePhotoEntry;
	}
	
	private PhotoEntry createFakePhotoEntryVideo()
	{
		PhotoEntry fakePhotoEntry = createFakePhotoEntry();
		List<com.google.gdata.data.media.mediarss.MediaContent> fakeList = createFakeList(2);
		when(fakePhotoEntry.getMediaContents()).thenReturn(fakeList);
		return fakePhotoEntry;
	}
	
	private List<com.google.gdata.data.media.mediarss.MediaContent> createFakeList(int size)
	{
		@SuppressWarnings("unchecked")
		List<com.google.gdata.data.media.mediarss.MediaContent> fakeList = mock(ArrayList.class);
		when(fakeList.size()).thenReturn(size);
		com.google.gdata.data.media.mediarss.MediaContent fakeMediaContent = createFakeMediaContent();
		for(int i = 0;i<size;i++)
		{
			when(fakeList.get(i)).thenReturn(fakeMediaContent);
		}		
		return fakeList;
	}
	
	private com.google.gdata.data.media.mediarss.MediaContent createFakeMediaContent()
	{
		com.google.gdata.data.media.mediarss.MediaContent fakeMediaContent = mock(com.google.gdata.data.media.mediarss.MediaContent.class);
		when(fakeMediaContent.getUrl()).thenReturn(URL);
		return fakeMediaContent;
	}
	
	private PhotoEntry createFakePhotoEntry()
	{
		PhotoEntry fakePhotoEntry = mock(PhotoEntry.class);
		TextConstruct fakeText = createFakeTextConstruct();
		when(fakePhotoEntry.getTitle()).thenReturn(fakeText);
		when(fakePhotoEntry.getGphotoId()).thenReturn(ID);
		return fakePhotoEntry;
	}
	
	private TextConstruct createFakeTextConstruct()
	{
		TextConstruct fakeText = mock(TextConstruct.class);
		when(fakeText.getPlainText()).thenReturn(NAME);
		return fakeText;
	}

	@Test
	public void isPhoto()
	{
		assertTrue(mediaContentPhoto.isPhoto());
	}
	
	@Test
	public void isVideo()
	{
		assertFalse(mediaContentVideo.isPhoto());
	}
	
	@Test
	public void getFileName()
	{
		assertEquals(NAME, mediaContentPhoto.getFileName());
	}
	
	@Test
	public void getId()
	{
		assertEquals(ID, mediaContentPhoto.getId());
	}
	
	@Test
	public void getContentUrlPhoto()
	{		
		assertTrue(mediaContentPhoto.isPhoto());
	}
	
	@Test
	public void getContentUrlVideo()
	{
		assertEquals(URL, mediaContentVideo.getContentUrl());
	}
	
	@Test
	public void getLastChanged()
	{
		assertEquals(1000, mediaContentPhoto.getLastChanged());
	}
}
