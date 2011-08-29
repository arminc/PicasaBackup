package nl.coralic.picasa.backup;

import java.io.File;

public class FileHandler
{

	public boolean folderExists(String folder)
	{
		File file = new File(folder);
		return file.exists();
	}

}
