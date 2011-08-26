package nl.coralic.picasa.backup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;

public class Initialize
{
	static String USERNAME;
	static String PASSWORD;

	@Before
	public void initalize() throws FileNotFoundException, IOException
	{
		Properties p = new Properties();
		p.load(new FileReader(new File("config.properties")));
		USERNAME = p.getProperty("username");
		PASSWORD = p.getProperty("password");
	}
}
