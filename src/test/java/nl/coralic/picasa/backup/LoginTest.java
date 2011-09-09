package nl.coralic.picasa.backup;

import static org.junit.Assert.*;

import nl.coralic.picasa.backup.service.Picasa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.util.AuthenticationException;

public class LoginTest
{
	Picasa picasa;

	@Before
	public void initalize()
	{
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
		PicasawebService picasaServiceMock = mock(PicasawebService.class);
		doThrow(new AuthenticationException("Fake error")).when(
				picasaServiceMock).setUserCredentials("username", "password");
		Picasa newPicasa = new Picasa();
		newPicasa.setPicasaService(picasaServiceMock);
		newPicasa.login("username", "password");
	}
}
