package nl.coralic.picasa.backup.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Database
{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public void setResources(EntityManagerFactory emf, EntityManager em)
	{
		this.emf = emf;
		this.em = em;
	}
	
	public void closeDatabase()
	{
		em.close();
        emf.close();
	}

	public boolean albumExists(String albumId)
	{
		TypedQuery<AlbumEntity> query = em.createQuery("SELECT a FROM AlbumEntity a WHERE a.albumId='"+albumId+"'", AlbumEntity.class);
		List<AlbumEntity> results = query.getResultList();
		if(results.isEmpty())
		{
			return false;
		}
		return true;
	}

	public void saveAlbum(AlbumEntity albumEntity)
	{
		em.getTransaction().begin();
		em.persist(albumEntity);
		em.getTransaction().commit();
	}

	public void saveMedia(MediaEntity media)
	{
		em.getTransaction().begin();
		em.persist(media);
		em.getTransaction().commit();
	}

	public boolean mediaExists(String mediaId)
	{
		TypedQuery<MediaEntity> query = em.createQuery("SELECT m FROM MediaEntity m WHERE m.mediaId='"+mediaId+"'", MediaEntity.class);
		List<MediaEntity> results = query.getResultList();
		if(results.isEmpty())
		{
			return false;
		}
		return true;
	}
}
