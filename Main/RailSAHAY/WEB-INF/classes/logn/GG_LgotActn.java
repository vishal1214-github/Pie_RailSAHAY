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
*	File Name : LogoutAction.java										*
*																		*
*	Name of The Service : ATUSERLGOT									*
*	Description : Used to save logout details.							*
*																		*
*	Name of the Developer	: 	Kunal Malhan							*
*	Creation Date			: 	02-12-2006								*
*																		*
*																		*
*	Usage : This Servlet is Used to save user logout details.			*
************************************************************************
*/

public class GG_LgotActn extends HttpServlet
{
	static Logger logger		= FoisLogger.getLogger(GG_LgotActn.class.getName());
	
	public GG_LgotActn()
	{
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse  res) throws IOException
	{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse  res) throws IOException
	{
		NDC.push((String)req.getParameter("txtClientId"));
		logger.info("Entering GG_LgotActn...");
		String responseXml	= "<data>%Error%%Data%</data>";
		
		
		
		try
		{
            HttpSession session     = req.getSession();
			String strUserName		= "";
			String strClntId		= "";
			strClntId               = (String) session.getAttribute("ClntID");
            strUserName             = (String) session.getAttribute("UserID");
           String si_Proj  		= (String)session.getAttribute("ProjName");
                    
			//*************************************************************************************
									//Calling Tuxedo Service from WTC
			//**************************Calling Service RQWEBRAKEHNDL******************************
	
			FOISTuxedo ATUSERLGOT = null;
			try
			{
				logger.info("Calling Tuxedo");
				ATUSERLGOT			= new FOISTuxedo();
				logger.info("Cliet Object Got.");
			}
			catch (Exception ne)
			{
				logger.fatal("Unable to get the Client Object: " + ne.toString());
				throw new GG_Exception(101, ne);
			}
			
			logger.info("strUserName :"		+ strUserName);
			logger.info("strClntId :"		+ strClntId);
			
			try
			{
                 if (si_Proj.equals("RMS ARCHQ") || si_Proj.equals("RMS ARCHZ"))
                 {
                 ATUSERLGOT.tuxInit("ATUSERLGOTARCH");   
                 }
                 else
                 {
				ATUSERLGOT.tuxInit("ATUSERLGOT");
                 }
				ATUSERLGOT.setString("F_USERID",	0,	strUserName);
				ATUSERLGOT.setString("F_CLNTID",	0,	strClntId);
				ATUSERLGOT.setString("F_FLINFLAG",	0,	"W");
			}
			catch(Exception e)
			{
				logger.fatal("Unable to write to buffer : " + e.toString());
				throw new GG_Exception(102, e);
			}
	
			try
			{
				logger.info("Calling Tuxedo Service ATVLDTUSER ...");
				ATUSERLGOT.call("N");
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
				erorCode			= ATUSERLGOT.getStringItemDef("F_ERORNO",0,"0");
			}
			catch(Exception e)
			{
				// F_ERORNO is not set by Developer, So, it is not an Error
			}
			if(erorCode != null && (!erorCode.equals("")))
			{
				logger.fatal(erorCode);
				//throw new GG_Exception(erorCode);
			}
			
			/**********************************************************************/
											/*Data Reading */
			/**********************************************************************/
			
			responseXml		= CrisFileStringOpr.replace(responseXml,"%Data%",	"/Logout");
			responseXml		= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
			
			try
			{
				ATUSERLGOT.endSession();
			}
			catch(Exception e)
			{
				logger.fatal("Error In End Session:" + e.toString());
				throw new GG_Exception(106, e);
			}
			
			if(strClntId.equals(""))
			{
				res.setContentType("text/html");
			}
			else
			{
				req.setCharacterEncoding("UTF-8");
				res.setContentType("text/xml");
				res.setHeader("Cache-Control", "no-cache");
			}
			session.invalidate();
			
			PrintWriter out		= res.getWriter();
			out.println(responseXml);
			logger.info("Sucessfull Execution of GG_LgotActn||ATUSERLGOT");
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