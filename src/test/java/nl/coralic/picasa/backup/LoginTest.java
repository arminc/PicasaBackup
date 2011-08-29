package nl.coralic.picasa.backup;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class LoginTest
{
	Picasa picasa;
	
	@Before
	public void initalize(){
		picasa = new Picasa();
	}
	
	@Test
	public void emptyUsername()
	{
		try
		{
			picasa.login("", "PASSWORD");
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
			picasa.login("USERNAME", "");
			fail();
		} catch (AuthenticationException e)
		{
			assertEquals("Password can not be empty", e.getMessage());
		}
	}

	@Test(expected = AuthenticationException.class)
	public void wrongCredentials() throws AuthenticationException
	{
		//TODO: Don't use picasa to test this
		picasa.login("username", "password");
	}
}
