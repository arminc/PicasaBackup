package nl.coralic.picasa.backup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gdata.data.photos.PhotoEntry;

public class Media implements Iterable<MediaContent>
{
	List<MediaContent> mediaContent;
	
	public Media(List<PhotoEntry> photoEntries)
	{
		mediaContent = generateAlbumsList(photoEntries);
	}
	
	private List<MediaContent> generateAlbumsList(List<PhotoEntry> photoEntries)
	{
		mediaContent = new ArrayList<MediaContent>();
		for(PhotoEntry photoEntry : photoEntries)
		{
			mediaContent.add(new MediaContent(photoEntry));
		}
		return mediaContent;
	}
	
	public Iterator<MediaContent> iterator()
	{
		return mediaContent.iterator();
	}

}
