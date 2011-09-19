package nl.coralic.picasa.backup.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.coralic.picasa.backup.file.FileHandler;

public class DatabaseFactory
{
	public static final String DBNAME = "picasabackup.odb";
	
	public static Database createDatabase(String rootFolder)
	{
		Database database = new Database();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(FileHandler.constructNewPath(rootFolder, DBNAME));
		database.setResources(emf, emf.createEntityManager());
		return database;
	}
}
