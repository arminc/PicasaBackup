package nl.coralic.picasa.backup;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class LoginTest extends Initialize
{
	@Test
	public void successful()
	{
		try
		{
			new PWABackup(USERNAME, PASSWORD);
		} catch (AuthenticationException e)
		{
			fail();
		}
	}

	@Test
	public void emptyUsername()
	{
		try
		{
			new PWABackup("", PASSWORD);
			fail();
		} catch (AuthenticationException e)
		{
			assertEquals("Username can not be empty", e.getMessage());
		}
	}

	@Test
	public void emptyPassword()
	{
		try
		{
			new PWABackup(USERNAME, "");
			fail();
		} catch (AuthenticationException e)
		{
			assertEquals("Password can not be empty", e.getMessage());
		}
	}

	@Test(expected = AuthenticationException.class)
	public void wrongCredentials() throws AuthenticationException
	{
		new PWABackup("username", "password");
	}
}
