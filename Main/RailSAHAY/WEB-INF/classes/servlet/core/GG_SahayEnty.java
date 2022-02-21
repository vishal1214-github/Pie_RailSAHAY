package servlet.core;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import util.logger.FoisLogger;
import tuxedo.FOISTuxedo;
import java.util.*;

public class GG_SahayEnty extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_SahayEnty.class.getName());
	String si_UserID="";
	String si_ClntID="";
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException, ServletException
	{
		logger.info("Calling GG_SahayEnty...");

		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
	    RequestDispatcher rd=null;
		//Just To View the Information
		Enumeration enumeration = req.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			String name		= (String)enumeration.nextElement();
			String values[]	= req.getParameterValues(name);
		}
		HttpSession session = req.getSession();

		String si_Optn	= req.getParameter("Optn").toUpperCase().trim();
        logger.info("si_Optn" + si_Optn);

		try
		{
			si_UserID		= (String) session.getAttribute("UserID");
        	si_ClntID		= (String) session.getAttribute("ClntID");
		}
		catch(Exception e)
		{
			si_UserID="";
			si_ClntID="";
		}

		if(si_Optn.equals("PARAMCNTL"))
		{
			String strParmCntl[][]	= this.getParmCntlData();
			logger.info("Parameter Control List Length:"+strParmCntl.length);
			Hashtable htable1 = new Hashtable(); 
			for(int i=0;i<strParmCntl.length;i++)
				htable1.put(strParmCntl[i][0], strParmCntl[i][1]);

	        	session.setAttribute("ParamCntl", htable1);
		        logger.info("Hashtable Attribute (ParamCntl) Set!!");
		}
	}
	public String[][] getParmCntlData()
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getParmCntlData....");

		FOISTuxedo RQSAHAYMNTR	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYMNTR = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			return null;
		}

		try
		{
			RQSAHAYMNTR.tuxInit("RQSAHAYMNTR");
			RQSAHAYMNTR.setString("F_USERID",		0,	this.si_UserID);
			RQSAHAYMNTR.setString("F_CLNTID",		0,	this.si_ClntID);
			RQSAHAYMNTR.setString("F_FLAG",			0,	"PARM_CNTL");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYMNTR.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYMNTR.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYMNTR.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][2];

		logger.info("Start reading data for Portal Parameter Controls");

		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYMNTR.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYMNTR.getStringItemDef("F_ORDRBY2",i,"0").trim();
			}
			catch(Exception d)
			{
				logger.fatal("Problem in Calling Service RQSAHAYMNTR and filling data into array" + d.toString());
				return null;
			}

		} // End of for Loop

		try
		{
			RQSAHAYMNTR.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			//strCallStts="F";
			//strCallMesg="Problem in Session End";
			return null;
		}
		return strData;
	}
}