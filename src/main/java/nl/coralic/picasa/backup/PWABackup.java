package nl.coralic.picasa.backup;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.util.AuthenticationException;

public class PWABackup
{

	private PicasawebService picasaService;

	public PWABackup(String username, String password)
			throws AuthenticationException
	{
		validateUsernamePassword(username, password);
		createPicasawebService();
		setCredentials(username, password);
	}

	private void validateUsernamePassword(String username, String password)
			throws AuthenticationException
	{
		if (username == null || username.equals(""))
		{
			throw new AuthenticationException("Username can not be empty");
		}
		if (password == null || password.equals(""))
		{
			throw new AuthenticationException("Password can not be empty");
		}
	}

	private void createPicasawebService()
	{
		picasaService = new PicasawebService(PWABackup.class.getName());
	}

	private void setCredentials(String username, String password)
			throws AuthenticationException
	{
		picasaService.setUserCredentials(username, password);
	}
}
