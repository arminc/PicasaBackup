package nl.coralic.picasa.backup;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArgumentsTest
{
	private Arguments arguments;

	@Before
	public void initialize()
	{
		arguments = new Arguments(new String[]
		{ "USERNAME", "PASSWORD", "PATH", "LOG4J" });
	}

	@Test
	public void getUsername()
	{
		assertEquals("USERNAME", arguments.getUsername());
	}

	@Test
	public void getPassword()
	{
		assertEquals("PASSWORD", arguments.getPassword());
	}

	@Test
	public void getPath()
	{
		assertEquals("PATH", arguments.getRootPath());
	}

	@Test
	public void log4jIsSet()
	{
		assertTrue(arguments.isLog4jPropertieSet());
		assertEquals("LOG4J", arguments.getLog4jPropertie());
	}

	@Test
	public void log4jNotSet()
	{
		Arguments args = new Arguments(new String[]
		{ "USERNAME", "PASSWORD", "PATH" });
		assertFalse(args.isLog4jPropertieSet());
	}
}
