package nl.coralic.picasa.backup.content;

import com.google.gdata.data.photos.AlbumEntry;

public class Album
{
	AlbumEntry albumEntry;
	
	public Album(AlbumEntry albumEntry)
	{
		this.albumEntry = albumEntry;
	}

	public String getAlbumId()
	{
		return albumEntry.getGphotoId();
	}

}
