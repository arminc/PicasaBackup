package nl.coralic.picasa.backup;

import java.io.IOException;
import java.net.MalformedURLException;

import nl.coralic.picasa.backup.file.FileHandler;
import nl.coralic.picasa.backup.log.LogFactory;

import org.apache.log4j.Logger;

import com.google.gdata.util.AuthenticationException;

public class Main
{
	static Logger logger = Logger.getLogger("PicasaBackup");

	public static void main(String[] args) throws AuthenticationException,
			MalformedURLException, IOException
	{
		checkArguments(args);
		LogFactory.initializeLog4J(args);
		checkTargetFolder(args);
		PicasaBackup.run(args);
	}

	private static void checkArguments(String[] args)
	{
		if (args.length != 3)
		{
			printUsage();
			exit();
		}
	}

	private static void printUsage()
	{
		System.out
				.println("Use as follow: \n PWABackup Username Password BackupFolder");
	}

	private static void exit()
	{
		System.exit(0);
	}

	private static void checkTargetFolder(String[] args)
	{
		if (!FileHandler.folderExists(args[2]))
		{
			logger.info("Folder " + args[2] + " does not exist.");
			exit();
		}
	}
}
