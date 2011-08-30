package nl.coralic.picasa.backup;

import java.security.Permission;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

import static org.junit.Assert.*;

public class PicasaBackupTest
{

	@BeforeClass
	public static void setUp() throws Exception
	{
		System.setSecurityManager(new NoExitSecurityManager());
	}

	@AfterClass
	public static void tearDown() throws Exception
	{
		System.setSecurityManager(null);
	}

	@Test
	public void checkArgumentsWrong() throws AuthenticationException
	{
		try
		{
			PicasaBackup.main(new String[]
			{ "1" });
		} catch (ExitException e)
		{
			assertEquals(0, e.status);
		}
	}
	
	//These two classes needed for testing System.exit in the main function
	protected static class ExitException extends SecurityException
	{
		private static final long serialVersionUID = -4307476468054559890L;
		public final int status;

		public ExitException(int status)
		{
			super("There is no escape!");
			this.status = status;
		}
	}

	private static class NoExitSecurityManager extends SecurityManager
	{
		@Override
		public void checkPermission(Permission perm)
		{
			// allow anything.
		}

		@Override
		public void checkPermission(Permission perm, Object context)
		{
			// allow anything.
		}

		@Override
		public void checkExit(int status)
		{
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

}
