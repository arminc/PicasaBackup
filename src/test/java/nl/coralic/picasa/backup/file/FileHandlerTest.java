package nl.coralic.picasa.backup.file;

import java.io.File;

import nl.coralic.picasa.backup.file.FileHandler;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest
{
	@Test
	public void constructFolder()
	{
		assertEquals("test" + File.separator + "test",
				FileHandler.constructNewPath("test", "test"));
	}
}
