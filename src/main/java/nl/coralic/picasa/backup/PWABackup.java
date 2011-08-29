package nl.coralic.picasa.backup;

public class PWABackup
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		checkArguments(args);
		//check if folder exists
		//create album folder
		//download photo
		//download video
	}
	
	private static void checkArguments(String[] args)
	{
		if(args.length != 3)
		{
			printUsage();
			exit();
		}
	}

	private static void printUsage()
	{
		System.out.println("Use as follow: \n PWABackup Username Password BackupFolder");
	}
	
	private static void exit()
	{
		System.exit(0);
	}
}
