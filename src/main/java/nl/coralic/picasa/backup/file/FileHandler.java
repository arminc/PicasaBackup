package nl.coralic.picasa.backup.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileHandler
{
	public static boolean folderExists(String folder)
	{
		return new File(folder).exists();
	}

	public static boolean createFolder(String rootFolder, String newFolder)
	{
		File folderToCreate = new File(rootFolder + File.separator + newFolder);
		try
		{
			FileUtils.forceMkdir(folderToCreate);
		} catch (IOException e)
		{
			return false;
		}
		return true;
	}
}
