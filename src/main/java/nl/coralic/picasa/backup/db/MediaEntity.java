package nl.coralic.picasa.backup.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MediaEntity
{
	@Id
	@GeneratedValue
	private long id;

	private String albumId;
	private String mediaId;
	private String contentFileName;
	private long lastChanged;

	public MediaEntity(String albumId, String mediaId, String contentFileName, long lastChanged)
	{
		this.albumId = albumId;
		this.mediaId = mediaId;
		this.contentFileName = contentFileName;
		this.lastChanged = lastChanged;
	}

	public long getId()
	{
		return id;
	}

	public String getAlbumId()
	{
		return albumId;
	}

	public String getMediaId()
	{
		return mediaId;
	}
	
	public String getContentFileName()
	{
		return contentFileName;
	}
	
	public long getLastChanged()
	{
		return lastChanged;
	}
}
