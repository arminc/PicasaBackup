package nl.coralic.picasa.backup;

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
		String id = albumEntry.getId();
		int lastSlash = id.lastIndexOf("/");
		return id.substring(lastSlash+1);
	}

}
