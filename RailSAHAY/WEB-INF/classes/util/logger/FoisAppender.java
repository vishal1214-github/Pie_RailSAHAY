package util.logger;
/*
 * Created on Dec 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.PatternLayout;
//import java.io.Writer;
//import java.io.File;
/**
 * @author Mitesh Kumar Saini
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FoisAppender extends DailyRollingFileAppender {
	
	public FoisAppender() throws IOException
	{
	    super(new PatternLayout("%p %d{HH:mm:ss,SSS} %t %x %c - %m%n"), "Unified.log","'.'dd-MM-yyyy ");
  	}

	public FoisAppender(String strFileName) throws IOException
	{
		super(new PatternLayout("%p %d{HH:mm:ss,SSS} %t %x %c - %m%n"), strFileName,"'.'dd-MM-yyyy ");
  	}
	public FoisAppender(String strFileName, String strPattern) throws IOException
	{
		super(new PatternLayout(strPattern), strFileName,"'.'dd-MM-yyyy ");
  	}
}
