package nl.coralic.picasa.backup;

import org.junit.Test;
import static org.junit.Assert.*;

public class FileFolderTest
{

	@Test
	public void folderExists()
	{
		FileHandler fileHandler = new FileHandler();
		boolean folderEists = fileHandler.folderExists("target");
		assertEquals(folderEists, true);
	}
	
	//maak folder aan
	//save data
}
