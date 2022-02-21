package model;

import tuxedo.*;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;

public class GG_TrmlUtilSrvc
{
	static Logger logger = FoisLogger.getLogger(GG_TrmlUtilSrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;	
	private String strAddlData2[][]=null;
	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public GG_TrmlUtilSrvc()
	{
		super();
	}
	public GG_TrmlUtilSrvc(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String[][] getTrmlList(String si_Sttn) 
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTrmlList....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Sttn  #"+si_Sttn+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Unable to get Client Object";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_PROFILE");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Sttn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	"");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Unable to write to buffer";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Exception in Service Call";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Problem in Data Filling";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][5];
		
		logger.info("Start reading data for OD Pairs List");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Problem in Data Filling";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Problem in Session End";
			return null;
		}
		return strData;
	}
	public String[][] getTrmlProf(String si_Sttn)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTrmlProf....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Sttn  #"+si_Sttn+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_PROFILE");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"PROF");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Sttn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	"");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Problem in Writing Buffer";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Row Count Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][20];
		
		logger.info("Start reading data for OD Pairs List");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();				
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();				
				strData[i][12]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",i,"0").trim();				
				strData[i][13]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE3",i,"0").trim();				
				strData[i][14]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB",i,"0").trim();				
				strData[i][15]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB1",i,"0").trim();				
				strData[i][16]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB2",i,"0").trim();				
				strData[i][17]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB3",i,"0").trim();				
				strData[i][18]		= RQSAHAYPRTL.getStringItemDef("F_RAKEID",i,"0").trim();				
				strData[i][19]		= RQSAHAYPRTL.getStringItemDef("F_RAKETYPE",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getSMARTFcty(String si_Sttn)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getSMARTFcty....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Sttn  #"+si_Sttn+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_PROFILE");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"SMART_FCTY");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Sttn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	"");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Buffer Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][18];
		
		logger.info("Start reading data for OD Pairs List");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();				
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();				
				strData[i][12]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",i,"0").trim();				
				strData[i][13]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE3",i,"0").trim();				
				strData[i][14]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB",i,"0").trim();				
				strData[i][15]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB1",i,"0").trim();				
				strData[i][16]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB2",i,"0").trim();				
				strData[i][17]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB3",i,"0").trim();	
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getNearLocnFcty(String si_Sttn)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getNearLocnFcty....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Sttn  #"+si_Sttn+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_PROFILE");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"NEAR_LOCN_FCTY");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Sttn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	"");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Buffer Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][11];
		
		logger.info("Start reading data for OD Pairs List");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getCRTLocn(String si_LocnFlag, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getCRTLocn....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_LocnFlag+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"CRT");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_LocnFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_Lng);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Buffer Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][12];
		
		logger.info("Start reading data for CRT Locations");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();			
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getPFTLocn(String si_LocnFlag, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getPFTLocn....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_LocnFlag+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"PFT");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_LocnFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_Lng);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][12];
		
		logger.info("Start reading data for PFT Locations");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();			
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getLdngLocn(String si_LocnFlag, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng) 
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getLdngLocn....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_LocnFlag+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"LDNG");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_LocnFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_Lng);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][12];
		
		logger.info("Start reading data for Loading Locations");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();			
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getUldgLocn(String si_LocnFlag, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getUldgLocn....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_LocnFlag+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"ULDG");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_LocnFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_Lng);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][12];
		
		logger.info("Start reading data for Loading Locations");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();			
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getTimeTblRout(String si_SttnFrom, String si_SttnTo) 
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTimeTblRout....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_SttnFrom+"#");
		logger.info("si_Locn  #"+si_SttnTo+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TIME_TABLED");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"TT_ROUTES");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_SttnFrom);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_SttnTo);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	"");
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][18];
		
		logger.info("Start reading data for Timetabled Routes");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();				
				strData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();				
				strData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();			
				strData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();		
				strData[i][12]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",i,"0").trim();		
				strData[i][13]		= RQSAHAYPRTL.getStringItemDef("F_OWNGZONE3",i,"0").trim();		
				strData[i][14]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB",i,"0").trim();		
				strData[i][15]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB1",i,"0").trim();		
				strData[i][16]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB2",i,"0").trim();		
				strData[i][17]		= RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getTimeTblSchd(String si_Schd) 
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTimeTblSchd....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnFlag  #"+si_Schd+"#");

		FOISTuxedo RQSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Client Object Problem";
			return null;
		}

		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
			RQSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RQSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TIME_TABLED");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"TTSCHD");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	"");
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	"");
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Schd);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Service Call Problem";
			return null;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			strCallStts="F";
			strCallMesg=erorCode1;
			return null;
		}
		String startRowCount1   = null;
		String startRowCount2   = null;
		int start1				= 0;
		int start2				= 0;

		try
		{
			startRowCount1		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
			startRowCount2		= RQSAHAYPRTL.getStringItemDef("F_ROWCONT1",0,"0");
			start1 = new Integer(startRowCount1.trim()).intValue();
			start2 = new Integer(startRowCount2.trim()).intValue();
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Data Problem";
			return null;
		}

		logger.info("start1 : " + start1);

		strData = new String[start1][9];
		strAddlData=new String[start2][13];
		
		logger.info("Start reading data for Timetabled Schedule");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();				
				strData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();				
				strData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();				
				strData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();				
				strData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();				
				strData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();	
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop
		
		logger.info("Start reading data for Timetabled Schedule Movement");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strAddlData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_RAKEID",i,"0").trim();
				strAddlData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_RAKETYPE",i,"0").trim();
				strAddlData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_CLBLUNTS",i,"0").trim();
				strAddlData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_OPBLUNTS",i,"0").trim();				
				strAddlData[i][4]		= RQSAHAYPRTL.getStringItemDef("F_EXCPUNTS",i,"0").trim();				
				strAddlData[i][5]		= RQSAHAYPRTL.getStringItemDef("F_UNTS",i,"0").trim();				
				strAddlData[i][6]		= RQSAHAYPRTL.getStringItemDef("F_DSPTUNTS",i,"0").trim();				
				strAddlData[i][7]		= RQSAHAYPRTL.getStringItemDef("F_FRMDUNTS",i,"0").trim();				
				strAddlData[i][8]		= RQSAHAYPRTL.getStringItemDef("F_FRWH",i,"0").trim();				
				strAddlData[i][9]		= RQSAHAYPRTL.getStringItemDef("F_FRWHDPRT",i,"0").trim();				
				strAddlData[i][10]		= RQSAHAYPRTL.getStringItemDef("F_FRWHARVL",i,"0").trim();				
				strAddlData[i][11]		= RQSAHAYPRTL.getStringItemDef("F_FORMTIME",i,"0").trim();				
				strAddlData[i][12]		= RQSAHAYPRTL.getStringItemDef("F_FITSRVC",i,"0").trim();	
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
    			strCallStts="F";
    			strCallMesg="Data Problem";
    			return null;
            }
           
        } // End of for Loop

		try
		{
			RQSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strData;
	}
	public String[][] getAddlData()
	{
		return strAddlData;
	}
	public String[][] getAddlData1()
	{
		return strAddlData1;
	}
	public String[][] getAddlData2()
	{
		return strAddlData2;
	}
	public String getRmrk()
	{
		return strRmrk;
	}
	public String getCallStts()
	{
		return strCallStts;
	}	
	public String getCallMesg()
	{
		return strCallMesg;
	}
}