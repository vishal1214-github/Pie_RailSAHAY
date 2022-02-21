package servlet.AppData;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import tuxedo.*;
import util.logger.FoisLogger;

public class SHY_FrgtRatesDtls extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(SHY_FrgtRatesDtls.class.getName());
	boolean bolValid=true;
	String strDtls[][];

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		logger.info("In Freight Rates");
		String strRateClss = (String)req.getParameter("RateClss").toUpperCase(); 
		FOISTuxedo RQCMDTRATEDTLS = null;
		String strError="";				
                String strData="";
		try
		{
			logger.info("Calling Tuxedo");
			RQCMDTRATEDTLS = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Tuxedo Connnection Failed.");
			strError="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
                        strData="{\"ErorMesg\":\""+strError+"\",\"SttnList\":[]}";
                        res.getWriter().write(strData);
		}
		try
		{
			logger.info("Calling tuxInit.");

			RQCMDTRATEDTLS.tuxInit("RQCMDTRATEDTLS");
			RQCMDTRATEDTLS.setString("F_USERID", 0, "WEBPRTL");
			RQCMDTRATEDTLS.setString("F_CLNTID", 0,  "1.1.1.1");
			RQCMDTRATEDTLS.setString("F_FLAG", 0,  "R");
			RQCMDTRATEDTLS.setString("F_RATECLSS", 0,  strRateClss);
			
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
                            logger.fatal("Tuxedo Connnection Failed.");
                            strError="ERROR IN GETTING FREIGHT RATES";
                            strData="{\"ErorMesg\":\""+strError+"\",\"SttnList\":[]}";
                            res.getWriter().write(strData);
			}
			
			String strRow = RQCMDTRATEDTLS.getStringItemDef("F_ROWCONT", 0);
			logger.info("Called Tux_Fchg.");
			logger.info("F_ROWCONT :"+ strRow);
			int intRowCount=Integer.parseInt(strRow);					
			strDtls = new String[intRowCount][5];
			for (int i=0;i<intRowCount;i++)
			{
				strDtls[i][0]	= RQCMDTRATEDTLS.getStringItemDef("F_SQNC",			i);
				strDtls[i][1]	= RQCMDTRATEDTLS.getStringItemDef("F_CLSS",  	                i);
				strDtls[i][2]	= RQCMDTRATEDTLS.getStringItemDef("F_CHBLDISTFROM",  	        i);
				strDtls[i][3]	= RQCMDTRATEDTLS.getStringItemDef("F_CHBLDISTTO",	     	i);
				strDtls[i][4]	= RQCMDTRATEDTLS.getStringItemDef("F_RATEPERTON", 	        i);
			}
                    String strTemp  =   "";
		    for(int i=0;i<intRowCount;i++)
		    {                                     

		            if(i==(intRowCount-1))
		                    strTemp+="{\"Class\":\""+strDtls[i][1]+"\",\"DistFrom\":\""+strDtls[i][2]+"\",\"DistTo\":\""+strDtls[i][3]+"\",\"RatePerTon\":\""+strDtls[i][4]+"\"}";
		            else
		                    strTemp+="{\"Class\":\""+strDtls[i][1]+"\",\"DistFrom\":\""+strDtls[i][2]+"\",\"DistTo\":\""+strDtls[i][3]+"\",\"RatePerTon\":\""+strDtls[i][4]+"\"},";                     
		    }

		    strData="{\"ErorMesg\":\""+strError+"\",\"SttnList\":["+strTemp+"]}";
		    logger.info(strData);
		    res.getWriter().write(strData);
                    
                    //RQCMDTRATEDTLS.endSession();
                    logger.info("End RQCMDTRATEDTLS.");
		}
		catch (Exception e)
		{
                    System.out.println("Error in Data Fetch");
                    res.getWriter().write("Data Contains Error! or No Records");
		}
		finally
		{
			try
			{
				RQCMDTRATEDTLS  =   null;
			}
			catch(Exception e)
			{
                            logger.fatal("Errror In Finally.");
                            strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(3)";
                            strData="{\"ErorMesg\":\""+strError+"\",\"SttnList\":[]}";
                            res.getWriter().write(strData);
			}
        }     
        logger.info("SUCCESSFULL");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            doPost(request, response);
    }
}


