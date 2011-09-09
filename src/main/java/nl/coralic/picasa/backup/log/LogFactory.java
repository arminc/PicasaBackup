package nl.coralic.picasa.backup.log;

import nl.coralic.picasa.backup.Arguments;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class LogFactory
{
	private static final String DEFAULTPATTERN = "%-5p [%t]: %m%n";
	
	public static void initializeLog4J(Arguments arguments)
	{
		if(arguments.isLog4jPropertieSet())
		{
			PropertyConfigurator.configure(arguments.getLog4jPropertie());
		}
		else
		{
			Logger rootLogger = Logger.getRootLogger();
			rootLogger.setLevel(Level.INFO);
		    rootLogger.addAppender(new ConsoleAppender(new PatternLayout(DEFAULTPATTERN)));
		}
	}

}
