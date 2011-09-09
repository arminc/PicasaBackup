package nl.coralic.picasa.backup;

public class Arguments
{
	private String username;
	private String password;
	private String rootPath;
	private String log4jPropertie;
	
	public Arguments(String[] args)
	{
		username = args[0];
		password = args[1];
		rootPath = args[2];
		if(args.length == 4)
		{
			log4jPropertie = args[3];
		}
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getRootPath()
	{
		return rootPath;
	}

	public String getLog4jPropertie()
	{
		return log4jPropertie;
	}

	public boolean isLog4jPropertieSet()
	{
		if (log4jPropertie == null || "".equals(log4jPropertie))
		{
			return false;
		}
		return true;
	}
}
