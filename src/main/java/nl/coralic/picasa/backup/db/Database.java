package nl.coralic.picasa.backup.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.coralic.picasa.backup.file.FileHandler;

public class Database
{
	public static final String DBNAME = "picasabackup.odb";
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public Database(String rootFolder)
	{
		emf = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(rootFolder, DBNAME));
	    em = emf.createEntityManager();
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
}
