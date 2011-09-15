package nl.coralic.picasa.backup.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AlbumEntity
{
	@Id @GeneratedValue
    private long id;
	
	private String albumId;
	
	public AlbumEntity(String albumId)
	{
		this.albumId = albumId;
	}

	public long getId()
	{
		return id;
	}

	public String getAlbumId()
	{
		return albumId;
	}
}
