package nl.coralic.picasa.backup.db;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import nl.coralic.picasa.backup.file.FileHandler;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseBackupTest
{
	static final String DBPATH = "target";
	static final String DBNAME = "picasabackup.odb";
	static final String FAKEDB = "FAKEDB.odb";
	static final String ALBUMID = "112345";
	static final String ALBUMID2 = "67890";
	static final String MEDIAID = "112345";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static Database database;
	
	@BeforeClass
	public static void initialize() throws Exception
	{	
		createDatabase();
		createAlbums(em);
		createMedia();
	}
	
	private static void createDatabase()
	{
		database = new Database();
		emf = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(DBPATH, DBNAME));
		em = emf.createEntityManager();
		database.setResources(emf, em);
	}
	
	private static void createAlbums(EntityManager eManager)
	{
		AlbumEntity albumEntity = new AlbumEntity(ALBUMID);
		AlbumEntity albumEntity2 = new AlbumEntity(ALBUMID2);
		eManager.getTransaction().begin();
		eManager.persist(albumEntity);
		eManager.persist(albumEntity2);
		eManager.getTransaction().commit();
	}
	
	private static void createMedia()
	{
		MediaEntity mediaEntity = new MediaEntity(ALBUMID,MEDIAID,"fakeFileName");
		em.getTransaction().begin();
		em.persist(mediaEntity);
		em.getTransaction().commit();
	}
	
	@AfterClass
	public static void close() throws Exception
	{
		em.close();
        emf.close();
		deleteDatabases();
	}
	
	private static void deleteDatabases() throws Exception
	{
		File firstDatabase = new File(FileHandler.constructNewPath(DBPATH, DBNAME));
		File secondDatabase = new File(FileHandler.constructNewPath(DBPATH, FAKEDB));
		if(!firstDatabase.delete())
		{
			throw new Exception("Could not delete database: " + DBNAME);
		}
		if(!secondDatabase.delete())
		{
			throw new Exception("Could not delete database: " + FAKEDB);
		}
	}

	@Test
	public void albumDoesNotExist()
	{
		assertFalse(database.albumExists("FAKEID"));
	}
	
	@Test
	public void albumExists()
	{
		assertTrue(database.albumExists(ALBUMID));
	}

	@Test
	public void createAlbum()
	{
		TypedQuery<AlbumEntity> query = em.createQuery("SELECT a FROM AlbumEntity a WHERE a.albumId='PRIVATEID'", AlbumEntity.class);
		List<AlbumEntity> results = query.getResultList();
		assertTrue(results.isEmpty());
		AlbumEntity albumEntityTmp = new AlbumEntity("PRIVATEID");
		database.saveAlbum(albumEntityTmp);
		results = query.getResultList();
		assertFalse(results.isEmpty());
	}
	
	@Test
	public void closeDatabase()
	{
		//Open the Database and generate some work to lock it
		EntityManagerFactory emfFirst = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(DBPATH, FAKEDB));
		EntityManager emFirst = emfFirst.createEntityManager();
		//set the resources to the db
		Database db = new Database();
		db.setResources(emfFirst, emFirst);
		createAlbums(emFirst);
		
		EntityManagerFactory emfSecondFaild = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(DBPATH, FAKEDB));
		EntityManager emSecondFaild = emfSecondFaild.createEntityManager();
		try
		{
			createAlbums(emSecondFaild);
		}
		catch (PersistenceException e) 
		{
			//If this is the failure then it is oke it means the file is locked
		}
		//Close the database
		db.closeDatabase();
		//Create a new Database connection
		EntityManagerFactory emfSecond = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(DBPATH, FAKEDB));
		EntityManager emSecond = emfSecond.createEntityManager();
		createAlbums(emSecond);
		//this time there should be no error
	}
	
	@Test
	public void saveMedia()
	{
		TypedQuery<MediaEntity> query = em.createQuery("SELECT m FROM MediaEntity m WHERE m.mediaId='PRIVATEID'", MediaEntity.class);
		List<MediaEntity> results = query.getResultList();
		assertTrue(results.isEmpty());
		MediaEntity mediaEntity = new MediaEntity("AlbumId","PRIVATEID","FileName");
		database.saveMedia(mediaEntity);
		results = query.getResultList();
		assertFalse(results.isEmpty());
	}
	
	@Test
	public void mediaDoesNotExist()
	{
		assertFalse(database.mediaExists("FAKEID"));
	}
	
	@Test
	public void mediaExists()
	{
		assertTrue(database.mediaExists(MEDIAID));
	}
}
