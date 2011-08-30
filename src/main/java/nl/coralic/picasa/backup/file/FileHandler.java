package nl.coralic.picasa.backup.file;

import java.io.File;
import java.io.IOException;

public class FileHandler
{

	public static boolean folderExists(String folder)
	{
		File file = new File(folder);
		return file.exists();
	}

	public static boolean createFolder(String rootFolder, String newFolder)
	{
		File folderToCreate = new File(rootFolder + File.pathSeparator + newFolder);
		try
		{
			return folderToCreate.createNewFile();
		} catch (IOException e)
		{
			return false;
		}
	}

}
