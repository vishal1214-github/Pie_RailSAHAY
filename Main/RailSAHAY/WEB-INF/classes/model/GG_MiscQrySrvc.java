package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class GG_MiscQrySrvc
{
	static Logger logger = FoisLogger.getLogger(GG_MiscQrySrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;
	private String strAddlData2[][]=null;
	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public GG_MiscQrySrvc()
	{
		super();
	}
	public GG_MiscQrySrvc(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String[][] getCmdtWgonMpng(String si_Cmdt)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getCmdtWgonMpng....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",		0,	"CMDT_WGON_MPNG");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Cmdt);
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

		strData = new String[start1][15];

		logger.info("Start reading data for Commodity Wagon Mapping");

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
                                strData[i][7]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();
                                strData[i][8]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();
                                strData[i][9]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",i,"0").trim();
                                strData[i][10]           = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",i,"0").trim();
                                strData[i][11]           = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                                strData[i][12]           = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",i,"0").trim();
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
		public String[][] getSrchPageList(String si_Word)
		{
			String strData[][] = null;   // Variable to be returned as output of function

			logger.info("Entering getSrchPageList....");
			logger.info("Function called with inputs :");
			logger.info("si_UserID  #"+si_UserID+"#");
			logger.info("si_ClntID  #"+si_ClntID+"#");
			logger.info("si_Word  #"+si_Word+"#");

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
				RQSAHAYPRTL.setString("F_FLAG",		0,	"SRCH_PAGE_LIST");
				RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Word);
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

			strData = new String[start1][4];

			logger.info("Start reading data for Search Page List");

			for(int i=0; i<=start1-1; i++)
			{
				try
				{
					strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
					strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
					strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
					strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();
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
	public String[] getCmdtRateClss(String si_Cmdt)
	{
		String strData[] = null;   // Variable to be returned as output of function

		logger.info("Entering getCmdtRateClss....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"CMDT_FRGT_RATES");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"C");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Cmdt);
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
		strData = new String[5];

		try
		{
			strData[0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",0,"0");
			strData[1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",1,"0");
			strData[2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",2,"0");
			strData[3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",3,"0");
			strData[4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",4,"0");
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			strCallStts="F";
			strCallMesg="Problem in Data Filling";
			return null;
		}
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
	public String[][] getRateClssDtls(String si_RateClss)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getRateClssDtls....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_RateClss  #"+si_RateClss+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",		0,	"CMDT_FRGT_RATES");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	"D");
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_RateClss);
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

		strData = new String[start1][4];

		logger.info("Start reading data for Search Page List");

		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
				strData[i][3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();
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

	public String[][] getTwoPtCombi(String si_IWOWFlag,String si_LocnFlag, String si_Locn, String si_Cmdt, String si_Type)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getTwoPtCombi....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_IWOWFlag  #"+si_IWOWFlag+"#");
		logger.info("si_LocnFlag #"+si_LocnFlag+"#");
		logger.info("si_Locn #"+si_Locn+"#");
		logger.info("si_Cmdt  #"+si_Cmdt+"#");
		logger.info("si_Type #"+si_Type+"#");


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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"TWO_POINT_COMBI");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_IWOWFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_LocnFlag);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Locn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		3,	si_Cmdt);
			RQSAHAYPRTL.setString("F_HLDGZONE",		4,	si_Type);
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

		strData = new String[start1][11];

		logger.info("Start reading data for Commodity Wagon Mapping");

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