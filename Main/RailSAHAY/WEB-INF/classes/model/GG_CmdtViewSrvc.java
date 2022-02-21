package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class GG_CmdtViewSrvc
{
	static Logger logger = FoisLogger.getLogger(GG_CmdtViewSrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;
	private String strAddlData2[][]=null;
	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public GG_CmdtViewSrvc()
	{
		super();
	}
	public GG_CmdtViewSrvc(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String[][] getNumCmdtList(String si_Cmdt)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getNumCmdtList....");
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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"NUM_CMDT_LIST");
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

		strData = new String[start1][2];

		logger.info("Start reading data for Numeric Commodity List");

		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
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
    public String[][] getCmdtSchm(String si_CmdtLevel,String si_Cmdt)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getCmdtSchm....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_CmdtLevel  #"+si_CmdtLevel+"#");
            logger.info("si_Cmdt  #"+si_Cmdt+"#");

            FOISTuxedo RQSAHAYPRTLCMDT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
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
                    RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                    RQSAHAYPRTLCMDT.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTLCMDT.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTLCMDT.setString("F_FLAG",                 0,      "CMDT_SCHM");
                    RQSAHAYPRTLCMDT.setString("F_HLDGZONE",             0,      si_CmdtLevel);
                     RQSAHAYPRTLCMDT.setString("F_HLDGZONE",             1,      si_Cmdt);
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
                    RQSAHAYPRTLCMDT.call("N");
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
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
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
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTLCMDT.getStringItemDef("F_ROWCONT",0,"0");
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

            strData = new String[start1][8];

            logger.info("Start reading data for Commodity Schemes");

            for(int i=0; i<=start1-1; i++)
            {
                    try
                    {
                            strData[i][0]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData[i][1]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                             strData[i][2]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                            strData[i][3]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY4",i,"0").trim();
                             strData[i][4]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY5",i,"0").trim();
                            strData[i][5]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY6",i,"0").trim();
                             strData[i][6]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY7",i,"0").trim();
                            strData[i][7]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY8",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQSAHAYPRTLCMDT and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Problem in Data Filling";
                    return null;
        }

    } // End of for Loop

            try
            {
                    RQSAHAYPRTLCMDT.endSession();
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
    public String[][] getSchmCmdt(String si_SchmCode)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getSchmCmdt....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_SchmCode  #"+si_SchmCode+"#");

            FOISTuxedo RQSAHAYPRTLCMDT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
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
                    RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                    RQSAHAYPRTLCMDT.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTLCMDT.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTLCMDT.setString("F_FLAG",                 0,      "SCHM_CMDT");
                    RQSAHAYPRTLCMDT.setString("F_HLDGZONE",             0,      si_SchmCode);
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
                    RQSAHAYPRTLCMDT.call("N");
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
                    //*************************************************************************************/
            /* Checking For Any Error from Service*/
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
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
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTLCMDT.getStringItemDef("F_ROWCONT",0,"0");
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

            logger.info("Start reading data for  Schemes");

            for(int i=0; i<=start1-1; i++)
            {
                    try
                    {
                            strData[i][0]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData[i][1]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                             strData[i][2]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                            strData[i][3]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY4",i,"0").trim();
                             strData[i][4]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY5",i,"0").trim();
                            strData[i][5]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY6",i,"0").trim();
                             strData[i][6]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY7",i,"0").trim();
                            strData[i][7]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY8",i,"0").trim();
                             strData[i][8]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY9",i,"0").trim();
                              strData[i][9]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY10",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQSAHAYPRTLCMDT and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Problem in Data Filling";
                    return null;
        }

    } // End of for Loop

            try
            {
                    RQSAHAYPRTLCMDT.endSession();
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
    public String[][] getDristiData()
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getDristiData....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");

            FOISTuxedo RQSAHAYPRTLCMDT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
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
                    RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                    RQSAHAYPRTLCMDT.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTLCMDT.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTLCMDT.setString("F_FLAG",                 0,      "DRISTI_DATA");
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
                    RQSAHAYPRTLCMDT.call("N");
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
                    //*************************************************************************************/
            /* Checking For Any Error from Service*/
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
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
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTLCMDT.getStringItemDef("F_ROWCONT",0,"0");
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

            logger.info("Start reading data for  Schemes");

            for(int i=0; i<=start1-1; i++)
            {
                    try
                    {
                            strData[i][0]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData[i][1]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                             strData[i][2]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                            strData[i][3]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY4",i,"0").trim();
                             strData[i][4]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY5",i,"0").trim();
                            strData[i][5]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY6",i,"0").trim();
                             strData[i][6]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY7",i,"0").trim();
                            strData[i][7]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY8",i,"0").trim();
                             strData[i][8]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY9",i,"0").trim();
                       
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQSAHAYPRTLCMDT and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Problem in Data Filling";
                    return null;
        }

    } // End of for Loop

            try
            {
                    RQSAHAYPRTLCMDT.endSession();
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
    public String[][] getDristiHead()
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getDristiHead....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");

            FOISTuxedo RQSAHAYPRTLCMDT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
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
                    RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                    RQSAHAYPRTLCMDT.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTLCMDT.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTLCMDT.setString("F_FLAG",                 0,      "DRISTI_HEAD");
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
                    RQSAHAYPRTLCMDT.call("N");
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
                    //*************************************************************************************/
            /* Checking For Any Error from Service*/
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
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
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTLCMDT.getStringItemDef("F_ROWCONT",0,"0");
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

            strData = new String[start1][3];

            logger.info("Start reading data for  Schemes");

            for(int i=0; i<=start1-1; i++)
            {
                    try
                    {
                            strData[i][0]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData[i][1]           = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                             
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQSAHAYPRTLCMDT and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Problem in Data Filling";
                    return null;
        }

    } // End of for Loop

            try
            {
                    RQSAHAYPRTLCMDT.endSession();
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

    public String[] getCmdtStats(String si_RakeCmdt, String si_NumCmdt)
    {
            String strData[]= null;   // Variable to be returned as output of function

            logger.info("Entering getCmdtStats....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_RakeCmdt  #"+si_RakeCmdt+"#");
            logger.info("si_NumCmdt  #"+si_NumCmdt+"#");

            FOISTuxedo RQSAHAYPRTLCMDT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
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
                    RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                    RQSAHAYPRTLCMDT.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTLCMDT.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTLCMDT.setString("F_FLAG",                 0,      "CMDT_STATS");
                    RQSAHAYPRTLCMDT.setString("F_HLDGZONE",             0,      si_RakeCmdt);
                    RQSAHAYPRTLCMDT.setString("F_HLDGZONE",             1,      si_NumCmdt);
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
                    RQSAHAYPRTLCMDT.call("N");
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
                    //*************************************************************************************/
            /* Checking For Any Error from Service*/
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
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
            strData=new String[4];
            try
            {
                    strData[0]          = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",0,"0");
                    strData[1]          = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",1,"0");
                    strData[2]          = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",2,"0");
                    strData[3]          = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",3,"0");
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Problem in Data Filling";
                    return null;
            }

            try
            {
                    RQSAHAYPRTLCMDT.endSession();
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