package util;

import org.apache.log4j.Logger;
import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

public class GG_AppFuncMap
{	
	static Logger logger = FoisLogger.getLogger(GG_AppFuncMap.class.getName());
	public  String[][] mapGetFrgtCalcLatLong (String si_route)
	{
		String strLatLong[][] = null;   // Variable to be returned as output of function

		logger.info("Entering mapGetFrgtCalcLatLong....");
		logger.info("si_cnmdid       #"+si_route    +"#");
		
		FOISTuxedo RQMAPLATLONG	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQMAPLATLONG = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			return strLatLong ;			
		}

		try
		{
			RQMAPLATLONG.tuxInit("RQMAPLATLONG");
			RQMAPLATLONG.setString("F_USERID",		0,	"FOISAPP");
			RQMAPLATLONG.setString("F_CLNTID",		0,	"1.1.1.1");
			RQMAPLATLONG.setString("F_FLAG",		0,	"FC");
			RQMAPLATLONG.setString("F_ZONE",		0,	"IR");
			RQMAPLATLONG.setString("F_RAKEID",		0,	si_route);
			RQMAPLATLONG.setString("F_COMMITFLAG",	        0,	"N");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			return strLatLong ;			
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQMAPLATLONG.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());		
			return strLatLong ;	
		}
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQMAPLATLONG.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			return strLatLong ;			
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			logger.info("Came to get rowcount");
			startRowCount1		= RQMAPLATLONG.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			return strLatLong ;	
		}

		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.warn("NO ROWS in BUFFER");
			return strLatLong ;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		logger.info("start1 : " + start1);

		String       strSttn        = "";
		String       strSttnFullName= "";
		String       strZone   		= "";
		String       strDvsn		= "";
		String       strLat       	= "";
		String       strLng       	= "";
		
		strLatLong = new String[start1][6];
		logger.info("Start reading data for Map Lattitude Longitude");
		for(int intCtrFcic=0; intCtrFcic<start1; intCtrFcic++)
		{
			try
			{
				strSttn           	= RQMAPLATLONG.getStringItemDef("F_NEXTZONEICPT",	  	intCtrFcic,	"0").trim();
				strSttnFullName     = RQMAPLATLONG.getStringItemDef("F_NEXTDVSNICPT",	  	intCtrFcic,	"0").trim().replaceAll("'", "");
				strZone         	= RQMAPLATLONG.getStringItemDef("F_NEXTDVSNDRTNTO",	  	intCtrFcic,	"0").trim();
				strDvsn      		= RQMAPLATLONG.getStringItemDef("F_NEXTZONEDRTNTO",	  	intCtrFcic,	"0").trim();
				strLat       		= RQMAPLATLONG.getStringItemDef("F_NEXTZONEIFFLAG",	  	intCtrFcic,	"0").trim();
				strLng       		= RQMAPLATLONG.getStringItemDef("F_NEXTDVSNTO",	  		intCtrFcic,	"0").trim();
				
				//System.out.println(":"+strSttn+":"+strDvsn+":"+strZone+":"+strLoadStts+":"+strSttsTime+":"+strLEFlag+":"+strUnts+":"+strSttnFullName+":"+
				//strLat+":"+strarvlTime+":"+strdprtTime+":");
				
				strLatLong[intCtrFcic][0]=  strSttn;
				strLatLong[intCtrFcic][1]=  strSttnFullName;
				strLatLong[intCtrFcic][2]=  strZone;
				strLatLong[intCtrFcic][3]=  strDvsn;
				strLatLong[intCtrFcic][4]=  strLat;
				strLatLong[intCtrFcic][5]=  strLng;
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQMAPLATLONG and filling data into array" + d.toString());
            }
        } // End of for Loop
		logger.info("start1 end : " + start1);
		try
		{
			RQMAPLATLONG.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
		}
		return strLatLong ;
	}
}