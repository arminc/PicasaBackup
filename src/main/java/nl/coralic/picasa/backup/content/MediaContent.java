package nl.coralic.picasa.backup.content;

import com.google.gdata.data.photos.PhotoEntry;

public class MediaContent
{
	PhotoEntry photoEntry;
	
	public MediaContent(PhotoEntry photoEntry)
	{
		this.photoEntry = photoEntry;
	}

	public boolean isPhoto()
	{
		if(photoEntry.getMediaContents().size() == 1)
		{
			return true;
		}
		return false;
	}

	public String getName()
	{
		return photoEntry.getTitle().getPlainText();
	}

	public String getContentUrl()
	{
		if(isPhoto())
		{
			return photoEntry.getMediaContents().get(0).getUrl();
		}
		return photoEntry.getMediaContents().get(photoEntry.getMediaContents().size()-1).getUrl();
	}
}
