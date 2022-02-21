package util.logger;
/*
 * Created on Dec 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Mitesh Kumar Saini
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FoisLogger {
	static public Logger getLogger(String strLoggerName)
	{
		Logger logger = Logger.getLogger(strLoggerName);

		//This Section has been moved to Log4jInit servlet
		/*Properties properties=new Properties();
		String strLevel = "info"; // Our Default Level
		String strPattern = "%p %d{HH:mm:ss,SSS} %t %x %c - %m%n"; // Our Default Pattern
		*/
		/* Reading Configuration File */
		/*try
        {
            properties.load(new FileInputStream("log4j.properties"));
            strLevel = properties.getProperty("level");
            strPattern = properties.getProperty("pattern");
        }
        catch(Exception e)
        {
            System.out.println("Error Loading the Property properties log4j.properties" );
        } */

        String strFolderFile [] = strLoggerName.split("\\.");
        String strFileName = "";
        String strFolderName = "";

        for (int x=0; x<strFolderFile.length-1; x++)
        	strFolderName = strFolderName + strFolderFile[x] + "//";
        strFileName = strFolderFile[strFolderFile.length-1];

        System.out.println("folder file name" + Log4jInit.strLogsPath + strFolderName + strFileName+ ".log");
        System.out.println("Log4jInit.strLevel  "+Log4jInit.strLevel+ "       pattern" + Log4jInit.strPattern);

        logger.removeAllAppenders();
        try
        {
            FoisAppender appender = new FoisAppender(Log4jInit.strLogsPath + strFolderName+ strFileName +".log",Log4jInit.strPattern);

            {
                logger.addAppender(appender);

                if (Log4jInit.strLevel.equalsIgnoreCase("debug"))
                    logger.setLevel((Level) Level.DEBUG);
                else if (Log4jInit.strLevel.equalsIgnoreCase("info"))
                    logger.setLevel((Level) Level.INFO);
                else if (Log4jInit.strLevel.equalsIgnoreCase("warn"))
                    logger.setLevel((Level) Level.WARN);
                else if (Log4jInit.strLevel.equalsIgnoreCase("error"))
                    logger.setLevel((Level) Level.ERROR);
                else if (Log4jInit.strLevel.equalsIgnoreCase("fatal"))
                    logger.setLevel((Level) Level.FATAL);
                else
                    logger.setLevel((Level) Level.INFO);
            }
        }catch(Exception e) {
            System.out.println("Error in initlization of Fois Appender");
            e.printStackTrace();
        }

		return logger;
	}
}
