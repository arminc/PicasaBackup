package nl.coralic.picasa.backup;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gdata.util.AuthenticationException;

public class PicasaWebAlbumsTest
{	
	//TODO: move this test class in to integration stage
	private Picasa picasa;
	static String USERNAME;
	static String PASSWORD;

	@Before
	public void initalize() throws FileNotFoundException, IOException, AuthenticationException
	{
		Properties p = new Properties();
		p.load(new FileReader(new File("config.properties")));
		USERNAME = p.getProperty("username");
		PASSWORD = p.getProperty("password");
		picasa = new Picasa();
		picasa.login(USERNAME, PASSWORD);
	}
	
	@Test
	public void fetchAlbumsFromPicasa()
	{
		Albums albums = picasa.fetchAlbums();
		assertNotSame(0, albums.size());
	}
	
	@Test
	public void fetchMediaFromPicasa()
	{
		//TODO
	}
}
