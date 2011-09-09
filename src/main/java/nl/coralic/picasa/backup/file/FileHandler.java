package nl.coralic.picasa.backup.file;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileHandler
{
	static Logger logger = Logger.getLogger("PicasaBackup");
	
	public static boolean doesFileOrFolderExist(String folder)
	{
		return new File(folder).exists();
	}

	public static void createFolder(String createFolder) throws IOException
	{
		FileUtils.forceMkdir(new File(createFolder));
	}
	
	public static String constructFolderPath(String rootFolder, String newFolder)
	{
		return rootFolder + File.separator + newFolder;
	}

	public static void saveMediaToFile(String contentUrl, String fileName)
	{
		try
		{
			FileUtils.copyURLToFile(new URL(contentUrl), new File(fileName));
		} catch (Exception e)
		{
			logger.error("Faild to save " + fileName, e);
		}		
	}
}
