package nl.coralic.picasa.backup;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class LoginTest extends Initialize
{
	@Test
	public void successful()
	{
		try
		{
			new Picasa(USERNAME, PASSWORD);
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
			new Picasa("", PASSWORD);
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
			new Picasa(USERNAME, "");
			fail();
		} catch (AuthenticationException e)
		{
			assertEquals("Password can not be empty", e.getMessage());
		}
	}

	@Test(expected = AuthenticationException.class)
	public void wrongCredentials() throws AuthenticationException
	{
		new Picasa("username", "password");
	}
}
