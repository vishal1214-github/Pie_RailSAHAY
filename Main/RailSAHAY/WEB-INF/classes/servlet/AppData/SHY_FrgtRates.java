package servlet.AppData;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import tuxedo.*;
import util.logger.FoisLogger;

public class SHY_FrgtRates extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(SHY_FrgtRates.class.getName());
	boolean bolValid=true;
	String strDtls[][];

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		logger.info("In Freight Rates");	 
		FOISTuxedo RQCMDTRATEDTLS = null;
		String strError="";
		try
		{
			logger.info("Calling Tuxedo");
			RQCMDTRATEDTLS = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.info("Tuxedo Connnection Failed.");
			strError="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
			logger.info("strError"+strError);
			req.setAttribute("ERROR",strError);
			RequestDispatcher rd=req.getRequestDispatcher("pages/Blank.jsp");
			rd.forward(req,res);
			return;
		}
		try
		{
			logger.info("Calling tuxInit.");

			RQCMDTRATEDTLS.tuxInit("RQCMDTRATEDTLS");
			RQCMDTRATEDTLS.setString("F_USERID", 0, "WEBPRTL");
			RQCMDTRATEDTLS.setString("F_CLNTID", 0,  "1.1.1.1");
			RQCMDTRATEDTLS.setString("F_FLAG", 0,  "C");
			
			logger.info("Called Tux_Fchg.");
			RQCMDTRATEDTLS.call("N");
			logger.info("Called Tux_Call.");
			
			String erorCode1			= null;
			try
			{
				erorCode1				= RQCMDTRATEDTLS.getStringItemDef("F_ERORNO",0,"0");
			}
			catch(Exception e)
			{
				// F_ERORNO is not set by Developer, So, it is not an Error
			}
			if(erorCode1 != null && (!erorCode1.equals("")))
			{
				logger.info("Tuxedo Connnection Failed.");
				strError="ERROR IN GETTING FREIGHT RATES";
				logger.info("erorCode1"+erorCode1);
				req.setAttribute("ERROR",erorCode1);
				RequestDispatcher rd=req.getRequestDispatcher("pages/Blank.jsp");
				rd.forward(req,res);
				return;
			}
			
			String strData = RQCMDTRATEDTLS.getStringItemDef("F_ROWCONT", 0);
			logger.info("Called Tux_Fchg.");
			logger.info("F_ROWCONT :"+ strData);
			int intRowCount=Integer.parseInt(strData);					
			strDtls = new String[intRowCount][5];
			for (int i=0;i<intRowCount;i++)
			{
				strDtls[i][0]	= RQCMDTRATEDTLS.getStringItemDef("F_SQNC",			i);
				strDtls[i][1]	= RQCMDTRATEDTLS.getStringItemDef("F_CMDT",  	i);
				strDtls[i][2]	= RQCMDTRATEDTLS.getStringItemDef("F_CMDTCODE",  	i);
				strDtls[i][3]	= RQCMDTRATEDTLS.getStringItemDef("F_RATECLSS",	     	i);
				strDtls[i][4]	= RQCMDTRATEDTLS.getStringItemDef("F_CLSS", 	    i);
			}

			RQCMDTRATEDTLS.endSession();
			logger.info("End RQCMDTRATEDTLS.");
		}
		catch (Exception e)
		{
			logger.info("There was an exception while creating and using the FOIS."+e);
			strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(2)";
			logger.info("strError"+strError);
			req.setAttribute("ERROR",strError);
			RequestDispatcher rd=req.getRequestDispatcher("pages/Blank.jsp");
			rd.forward(req,res);
			return;

		}
		finally
		{
			try
			{
				RQCMDTRATEDTLS=null;
			}
			catch(Exception e)
			{
				logger.info("Errror In Finally.");
				strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(3)";
				logger.info("strError"+strError);
				req.setAttribute("ERROR",strError);
				RequestDispatcher rd=req.getRequestDispatcher("pages/Blank.jsp");
				rd.forward(req,res);
				return;
			}
        }
        
        req.setAttribute("Details",strDtls);
        logger.info("SUCCESSFULL");
		RequestDispatcher rd=req.getRequestDispatcher("pages/FrgtRates.jsp");
		rd.forward(req,res);
	}
public void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException
	{
		doPost(request, response);
	}

}


