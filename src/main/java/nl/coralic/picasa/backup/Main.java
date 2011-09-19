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
		Arguments arguments = checkCreateArgumentsOrExit(args);
		LogFactory.initializeLog4J(arguments);
		checkPathExistsOrExit(arguments);
		preCheck(arguments);
		PicasaBackup.run(arguments);
	}

	private static Arguments checkCreateArgumentsOrExit(String[] args)
	{
		if (args.length != 3)
		{
			printUsage();
			exit();
		}
		Arguments arguments = new Arguments(args);
		return arguments;
	}
	
	private static void checkPathExistsOrExit(Arguments arguments)
	{
		if(!FileHandler.doesFileOrFolderExist(arguments.getRootPath()))
		{
			logger.info("Folder " + arguments.getRootPath() + " does not exist.");
			exit();
		}		
	}
	
	private static void preCheck(Arguments arguments)
	{

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
	
	
}
