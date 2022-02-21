package model;

import tuxedo.*;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;

public class GG_TrmlDashbord
{
	static Logger logger = FoisLogger.getLogger(GG_TrmlDashbord.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;
	private String strAddlData2[][]=null;
	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public GG_TrmlDashbord()
	{
		super();
	}
	public GG_TrmlDashbord(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String[] getSmryStats(String si_LocnType, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng, String si_TrmlFcty, String si_SMARTFcty)
	{
		String strData[] = null;   // Variable to be returned as output of function

		logger.info("Entering getSmryStats....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnType  #"+si_LocnType+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");
		logger.info("si_TrmlFcty  #"+si_TrmlFcty+"#");
		logger.info("si_SMARTFcty  #"+si_SMARTFcty+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRML_DASHBOARD_STATS");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_LocnType);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lng);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_TrmlFcty);
			RQSAHAYPRTL.setString("F_HLDGZONE",		6,	si_SMARTFcty);
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

		strData = new String[4];

		logger.info("Start reading data for Summary Statistics");

		for(int i=0; i<4; i++)
		{
			try
			{
				strData[i]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
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
	public String[][] getTrmlList(String si_LocnType, String si_Locn, String si_Cmdt, String si_Lat, String si_Lng, String si_TrmlType, String si_TrmlFcty, String si_SMARTFcty)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTrmlList....");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_LocnType  #"+si_LocnType+"#");
		logger.info("si_Locn  #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Lat  #"+si_Lat+"#");
		logger.info("si_Lng  #"+si_Lng+"#");
		logger.info("si_TrmlType  #"+si_TrmlType+"#");
		logger.info("si_TrmlFcty  #"+si_TrmlFcty+"#");
		logger.info("si_SMARTFcty  #"+si_SMARTFcty+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",		0,	"TRMLDB_TRML_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_LocnType);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Lat);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Lng);
			RQSAHAYPRTL.setString("F_HLDGZONE",		5,	si_TrmlType);
			RQSAHAYPRTL.setString("F_HLDGZONE",		6,	si_TrmlFcty);
			RQSAHAYPRTL.setString("F_HLDGZONE",		7,	si_SMARTFcty);
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

		strData = new String[start1][11];

		logger.info("Start reading data Terminals List");

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

        }
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

		public String[][] getBrkUpList(String si_BrkType, String si_TrmlType, String si_Cmdt, String si_TrmlFcty, String si_SMARTFcty)
		{
			String strData[][] = null;   // Variable to be returned as output of function

			logger.info("Entering getTrmlList....");
			logger.info("si_UserID  #"+si_UserID+"#");
			logger.info("si_ClntID  #"+si_ClntID+"#");
			logger.info("si_BrkType  #"+si_BrkType+"#");
			logger.info("si_TrmlType  #"+si_TrmlType+"#");
			logger.info("si_Cmdt  #"+si_Cmdt+"#");
			logger.info("si_TrmlFcty  #"+si_TrmlFcty+"#");
			logger.info("si_SMARTFcty  #"+si_SMARTFcty+"#");

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
				RQSAHAYPRTL.setString("F_FLAG",		0,	"TRMLDB_BRKUP");
				RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_BrkType);
				RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_TrmlType);
				RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Cmdt);
				RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_TrmlFcty);
				RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_SMARTFcty);
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

			strData = new String[start1][6];

			logger.info("Start reading data Terminals List");

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
	            }
	            catch(Exception d)
	            {
	                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
	    			strCallStts="F";
	    			strCallMesg="Data Problem";
	    			return null;
	            }

	        }
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