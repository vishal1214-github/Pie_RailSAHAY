package servlet.AjaxData;

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

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class GG_SahayAjaxData extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_SahayAjaxData.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException, ServletException {

		logger.info("Calling GG_SahayAjaxData...");

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/xml");
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
		
		String si_DtlsType	= req.getParameter("Help").toUpperCase().trim();
                logger.info("si_DtlsType" + si_DtlsType);
		String resData="";
		
		if(si_DtlsType.equals("SRCHRPRT"))
		{
			String strSrch	= req.getParameter("Srch").toUpperCase().trim();
			resData= sahayGetSrch(strSrch);
		}
		if(si_DtlsType.equals("CODEHELP"))
		{
			String strSrch	= req.getParameter("Srch").toUpperCase().trim();
			resData= sahayGetHelpCode(strSrch);
		}
		if(si_DtlsType.equals("SETLANGFLAG"))
		{
			String strLangFlag	= req.getParameter("Srch").toUpperCase().trim();
				session.setAttribute("LangFlag", strLangFlag);
		}
		if(si_DtlsType.equals("SETTHEME"))
		{
			String strTheme	= req.getParameter("Srch").toUpperCase().trim();
			session.setAttribute("Theme", strTheme);
		}
	    if(si_DtlsType.equals("SETCMDT"))
	    {
	        logger.info("IN SETCMDT");
	            String strCmdt = req.getParameter("Srch").toUpperCase().trim();
	        logger.info("strCmdt" + strCmdt);
	            session.setAttribute("Cmdt", strCmdt);
	        logger.info("IN FORWARD");
	         rd=req.getRequestDispatcher("pages/Industry.jsp");
                rd.forward(req,res);
            }
	    if(si_DtlsType.equals("SETSUBCMDT"))
	    {
	        logger.info("IN SESUBTCMDT");
	            String strSubCmdt = req.getParameter("Srch").toUpperCase().trim();
	        logger.info("strSubCmdt" + strSubCmdt);
	            session.setAttribute("SubCmdt", strSubCmdt);
	        rd=req.getRequestDispatcher("pages/Industry.jsp");
	        rd.forward(req,res);
	       
	    }
	    rd.forward(req,res);
	
		PrintWriter out			= res.getWriter();
		out.println(resData);
	}
	public String sahayGetSrch(String si_Srch)
	{
		logger.info("Entered sahayGetSrch For:"+si_Srch);
		String strRprtName="";
		String strRprtPath="";
		String strData="";
		String strCrntData="";
	
		String responseXml	= "<?xml version=\"1.0\"?><Cris><Error>%Error%</Error><Data>%Data%</Data><Count>%Count%</Count></Cris>";
		FOISTuxedo RTSAHAYPRTL	= null;
		try
		{
			RTSAHAYPRTL = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
			RTSAHAYPRTL.setString("F_USERID",		0,	"SAHAYPRTL");
			RTSAHAYPRTL.setString("F_CLNTID",		0,	"1.1.1.1");
			RTSAHAYPRTL.setString("F_FLAG",			0,	"SAHAYSRCH");
			RTSAHAYPRTL.setString("F_COMMITFLAG",		0,	"N");
			RTSAHAYPRTL.setString("F_CNSGNAME",		0,	si_Srch);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RTSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			responseXml			= responseXml.replaceAll("%Error%",	erorCode);
			return responseXml;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RTSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			responseXml			= responseXml.replaceAll("%Error%",	"No data found");
			return responseXml;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
	
		for(int k=0; k<=start1-1; k++)
		{
			try
			{
				strRprtName 		= RTSAHAYPRTL.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strRprtPath 		= RTSAHAYPRTL.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strCrntData	= strRprtName+"@"+strRprtPath+"@";
				if(k==0)
				{
					strData=strCrntData;
				}
				else
				{
					strData=strData+"#"+strCrntData;
				}
				
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		logger.info("COUNT"+start1 );
		logger.info("Data"+strData );
		responseXml	= responseXml.replace("%Count%",	start1+"");
		responseXml	= responseXml.replace("%Data%",	strData);
		try
		{
			RTSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		logger.info(responseXml);
		return responseXml;
	}

	public String sahayGetHelpCode(String si_Srch)
	{
		logger.info("Entered sahayGetHelpCode For:"+si_Srch);
		String strCtgr="";
		String strPrevCtgr="";
		String strDesc="";
		String strData="";
		String strCrntData="";
	
		String responseXml	= "<?xml version=\"1.0\"?><Cris><Error>%Error%</Error><Data>%Data%</Data><Count>%Count%</Count></Cris>";
		FOISTuxedo RQSAHAYPRTL	= null;
		try
		{
			RQSAHAYPRTL = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}
		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	"SAHAYPRTL");
			RQSAHAYPRTL.setString("F_CLNTID",		0,	"1.1.1.1");
			RQSAHAYPRTL.setString("F_FLAG",			0,	"CODE_HELP");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Srch);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			responseXml			= responseXml.replaceAll("%Error%",	erorCode);
			return responseXml;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			responseXml			= responseXml.replaceAll("%Error%",	"No data found");
			return responseXml;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
	
		for(int k=0; k<=start1-1; k++)
		{
			try
			{
				strCtgr 		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDesc 		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				
				if(strPrevCtgr.equals(strCtgr))
				{
					continue;
				}
				strCrntData	= strCtgr+"@"+strDesc+"@";
				
				if(k==0)					
				{
					strData=strCrntData;
				}
				else
				{
					strData=strData+"#"+strCrntData;
				}
				strPrevCtgr=strCtgr;
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		logger.info("COUNT"+start1 );
		logger.info("Data"+strData );
		responseXml	= responseXml.replace("%Count%",	start1+"");
		responseXml	= responseXml.replace("%Data%",	strData);
		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		logger.info(responseXml);
		return responseXml;
	}

}