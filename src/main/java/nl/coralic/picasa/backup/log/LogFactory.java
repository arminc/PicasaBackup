package nl.coralic.picasa.backup.log;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class LogFactory
{

	public static void initializeLog4J(String[] args)
	{
		if(args.length == 4)
		{
			PropertyConfigurator.configure(args[3]);
		}
		else
		{
			Logger rootLogger = Logger.getRootLogger();
			rootLogger.setLevel(Level.INFO);
		    rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%-5p [%t]: %m%n")));
		}
	}

}
