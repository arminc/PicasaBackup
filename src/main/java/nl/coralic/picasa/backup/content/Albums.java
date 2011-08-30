package nl.coralic.picasa.backup.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gdata.data.photos.AlbumEntry;

public class Albums implements Iterable<Album>
{
	List<Album> albums;

	public Albums(List<AlbumEntry> albumEntries)
	{
		this.albums = generateAlbumsList(albumEntries);
	}

	public Albums()
	{
		this.albums = new ArrayList<Album>();
	}
	
	private List<Album> generateAlbumsList(List<AlbumEntry> albumEntries)
	{
		albums = new ArrayList<Album>();
		for(AlbumEntry albumEntry : albumEntries)
		{
			albums.add(new Album(albumEntry));
		}
		return albums;
	}

	public int size()
	{
		return albums.size();
	}

	public Iterator<Album> iterator()
	{
		return albums.iterator();
	}
}
