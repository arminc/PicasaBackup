package nl.coralic.picasa.backup.file;

import nl.coralic.picasa.backup.file.FileHandler;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest
{
	@AfterClass
	public static void tearDown()
	{
		
	}
	
	@Test
	public void folderExists()
	{
		assertEquals(true, FileHandler.folderExists("target"));
	}
	
	@Test
	public void createFolder()
	{
		assertEquals(true,FileHandler.createFolder("target","album"));
	}
	
	@Test
	public void saveDataToFile()
	{
		//TODO: how to save
	}
}
