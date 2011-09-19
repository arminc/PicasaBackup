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

	public String getFileName()
	{
		return photoEntry.getTitle().getPlainText();
	}
	
	public String getId()
	{
		return photoEntry.getGphotoId();
	}

	public String getContentUrl()
	{
		if(isPhoto())
		{
			return photoEntry.getMediaContents().get(0).getUrl();
		}
		//get the last one because that seems to be the biggest video size?
		return photoEntry.getMediaContents().get(photoEntry.getMediaContents().size()-1).getUrl();
	}
}
