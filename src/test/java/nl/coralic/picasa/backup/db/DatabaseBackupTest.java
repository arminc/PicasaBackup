package nl.coralic.picasa.backup.db;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.coralic.picasa.backup.file.FileHandler;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseBackupTest
{
	static final String DBPATH = "target";
	static final String DBNAME = "picasabackup.odb";
	static Database database;
	
	@BeforeClass
	public static void initialize() throws Exception
	{
		createDatabase();		
		database = new Database("target");
	}
	
	private static void createDatabase()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(DBPATH, DBNAME));
	    EntityManager em = emf.createEntityManager();
	    AlbumEntity albumEntity = new AlbumEntity("FAKEALBUMID");
	    em.getTransaction().begin();
	    em.persist(albumEntity);	
	    em.getTransaction().commit();
	    em.close();
        emf.close();
	}
	
	@AfterClass
	public static void close() throws Exception
	{
		deleteDatabase();
	}
	
	private static void deleteDatabase() throws Exception
	{
		String path = FileHandler.constructNewPath(DBPATH, "picasabackup.odb");
		File file = new File(path);
		if(!file.delete())
		{
			throw new Exception("Could not delete: " + path);
		}
	}

	@Test
	public void albumDoesNotExist()
	{
		
	}
	
	@Test
	public void albumExists()
	{
		
	}

	@Test
	public void createAlbum()
	{

	}
	

}
