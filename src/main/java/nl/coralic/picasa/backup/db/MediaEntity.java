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

	public MediaEntity(String albumId, String mediaId, String contentFileName)
	{
		this.albumId = albumId;
		this.mediaId = mediaId;
		this.contentFileName = contentFileName;
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

}
