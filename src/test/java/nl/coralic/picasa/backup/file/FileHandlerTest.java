package nl.coralic.picasa.backup.file;

import java.io.File;

import nl.coralic.picasa.backup.file.FileHandler;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest
{
	//TODO: save things to file
	@Test
	public void constructFolder()
	{
		assertEquals("test" + File.separator + "test",
				FileHandler.constructNewPath("test", "test"));
	}
	
	@Test
	public void constructMediaContentFileNameWithId()
	{
		String fileName = "testing.jpg";
		String id = "12345";
		assertEquals("testing_12345.jpg", FileHandler.constructMediaContentFileNameWithId(fileName, id));
	}
}
