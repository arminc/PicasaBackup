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
	
	public static String constructNewPath(String rootFolder, String newFolderOrFile)
	{
		return rootFolder + File.separator + newFolderOrFile;
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

	public static String constructMediaContentFileNameWithId(String fileName,
			String id)
	{
		int lastIndex = fileName.lastIndexOf(".");
		String tmpFileName = fileName.substring(0, lastIndex);
		String tmpFileExtention = fileName.substring(lastIndex);
		return tmpFileName + "_" + id + tmpFileExtention;
	}
}
