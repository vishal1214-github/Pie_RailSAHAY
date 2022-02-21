/*
 * Created on Dec 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.logger;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.help.GG_VldtDesc;

/**
 * @author Mitesh Kumar Saini
 *
 */
public class Log4jInit extends HttpServlet {
	static public String strLevel= "info"; // Our Default Level
	static public String strPattern = "%p %d{HH:mm:ss,SSS} %t %x %c - %m%n"; // Our Default Pattern
	static public String strLogsPath;
    static public String strPropertiesPath;
    public static String strResourcePath;

	public void init() {
		Properties properties=new Properties();

		strPropertiesPath   = getInitParameter("properties-path");
		strLogsPath         = getInitParameter("log4j-logs-path");
        String file         = null;
        if(strPropertiesPath != null)
            file    = strPropertiesPath + "log4j.properties";

	    System.out.println("file " + file);
        //System.out.println("strPropertiesPath" + strPropertiesPath);
        //System.out.println("strLogsPath" + strLogsPath);
	    // if the log4j-init-file is not set, then no point in trying
	    if(file != null) {
	      try
	        {
	      		properties.load(new FileInputStream(file));
	            strLevel = properties.getProperty("level");
	            strPattern = properties.getProperty("pattern");
	        }
	        catch(Exception e)
	        {
	            System.out.println("Error Loading the Property properties log4j.properties" );
                e.printStackTrace();
	        }
	    }
        GG_VldtDesc.loadTagCodes(this.getServletContext());
        strResourcePath = getInitParameter("resource-folder-path");
        getServletContext().setAttribute("RESOURCE_FOLDER_PATH", strResourcePath);
	  }

	  public
	  void doGet(HttpServletRequest req, HttpServletResponse res) {
	  }

}
