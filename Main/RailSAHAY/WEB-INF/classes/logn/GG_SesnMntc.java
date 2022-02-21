package logn;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import tuxedo.FOISTuxedo;
import util.exception.GG_Exception;
import util.logger.FoisLogger;
import Cris.util.CrisFileStringOpr;

/*
************************************************************************
*	Servlet  to logout the user from application.						*
*	File Name : GG_SesnMntc.java										*
*																		*
*	Name of The Service : ATSESNMNTC									*
*	Description : Used to Maintain Sessions								*
*																		*
*	Name of the Developer	: 	Raman Arora								*
*	Creation Date			: 	11-05-2019								*
*																		*
*																		*
*	Usage : This Servlet is Used to save user logout/login details.		*
************************************************************************
*/

public class GG_SesnMntc extends HttpServlet
{
	static Logger logger		= FoisLogger.getLogger(GG_SesnMntc.class.getName());
	
	public GG_SesnMntc()
	{
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse  res) throws IOException
	{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse  res) throws IOException
	{
		NDC.push((String)req.getParameter("userid"));
		logger.info("Entering GG_SesnMntc...");
		String responseXml	= "<data>%Error%%Data%</data>";			
		
		try
		{
            HttpSession session     = req.getSession();
			String strUserName		= ((String)req.getParameter("userid")).toUpperCase().trim();
            String strProj  		= ((String)req.getParameter("proj")).toUpperCase().trim();
            String strFlag			= ((String)req.getParameter("flag")).toUpperCase().trim();
                    
			//*************************************************************************************
									//Calling Tuxedo Service from WTC
			//**************************Calling Service ATSESNMNTC******************************
	
			FOISTuxedo ATSESNMNTC = null;
			try
			{
				logger.info("Calling Tuxedo");
				ATSESNMNTC			= new FOISTuxedo();
				logger.info("Cliet Object Got.");
			}
			catch (Exception ne)
			{
				logger.fatal("Unable to get the Client Object: " + ne.toString());
				throw new GG_Exception(101, ne);
			}
			
			logger.info("strUserName :"		+ strUserName);
			logger.info("strProj :"		+ strProj);
			
			try
			{
                 if (strProj.equals("RMS ARCHQ") || strProj.equals("RMS ARCHZ"))
                 {
                	 ATSESNMNTC.tuxInit("ATSESNMNTCARCH");   
                 }
                 else
                 {
                	 ATSESNMNTC.tuxInit("ATSESNMNTC");
                 }
                 ATSESNMNTC.setString("F_USERID",	0,	strUserName);
                 ATSESNMNTC.setString("F_CLNTID",	0,	strProj);
                 if(strFlag.equals("O"))
                 {
                	 ATSESNMNTC.setString("F_AJSTFLAG",	0,	"LOGOUT_ALL_SESN");
                 }
                 if(strFlag.equals("C"))
                 {
                	 ATSESNMNTC.setString("F_AJSTFLAG",	0,	"LOGOUT_CRNT_SESN");
                	 ATSESNMNTC.setString("F_SESNSTTS",	0,	session.getId());
                 }
			}
			catch(Exception e)
			{
				logger.fatal("Unable to write to buffer : " + e.toString());
				throw new GG_Exception(102, e);
			}
	
			try
			{
				logger.info("Calling Tuxedo Service ATSESNMNTC ...");
				ATSESNMNTC.call("N");
				logger.info("CALL COMPLETED !");
			}
			catch(Exception e)
			{
				logger.fatal("Exception while call to the service is " + e.toString());
				throw new GG_Exception(103, e);
			}
	
			//********************************************************************************
											//END of WTC calling
			//********************************************************************************
			
			// Checking For Any Error from Service
			String erorCode			= null;
			try
			{
				erorCode			= ATSESNMNTC.getStringItemDef("F_ERORNO",0,"0");
			}
			catch(Exception e)
			{
				// F_ERORNO is not set by Developer, So, it is not an Error
			}
			if(erorCode != null && (!erorCode.equals("")))
			{
				logger.fatal(erorCode);
				responseXml		= CrisFileStringOpr.replace(responseXml,"%Data%",	erorCode);
				responseXml		= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
				//throw new GG_Exception(erorCode);
			}
			
			try
			{
				ATSESNMNTC.endSession();
			}
			catch(Exception e)
			{
				logger.fatal("Error In End Session:" + e.toString());
				throw new GG_Exception(106, e);
			}			
			logger.info("Sucessfull Execution of GG_SesnMntc||ATSESNMNTC");
			
			responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	"SUCCESS");
			responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
		}
		catch(Exception e)
		{
			if(e instanceof GG_Exception)
			{
				GG_Exception ek		= (GG_Exception)e;
				
				String strErorDesc	= ek.getErrorCodeMessage(req).replaceAll("\"", "'");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	"");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"" + strErorDesc);
			}
			else
			{
				GG_Exception ge		= new GG_Exception(e.toString(), e);
				logger.error(e.toString() + "\n" + ge.getStacktrace());
				
				String strErorDesc	= e.toString().replaceAll("\"", "'");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	"");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"" + strErorDesc);
			}
		}
		finally
		{
			PrintWriter out		= res.getWriter();
			logger.debug("Output::" + responseXml);
			out.println(responseXml);
			logger.info("Exiting GG_LgotActn.");
			NDC.pop();
			NDC.remove();
		}
	}
} // end of main