package nl.coralic.picasa.backup.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
		Query query = em.createQuery("SELECT COUNT(a) FROM AlbumEntity a");
		try
		{
			if(query.getSingleResult().equals("1"))
			{
				return true;
			}
		}
		catch(PersistenceException pe)
		{
			
		}
		return false;
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
}
