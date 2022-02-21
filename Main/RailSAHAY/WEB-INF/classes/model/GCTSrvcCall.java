package model;

import org.apache.log4j.Logger;
import java.util.StringTokenizer;
import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

public class GCTSrvcCall
{
	static Logger logger = FoisLogger.getLogger(GCTSrvcCall.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;
        private String strAddlData2[][]=null;
        private String strAddlData3[][]=null;
        private String strAddlData4[][]=null;
        private String strAddlData5[][]=null;

	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public GCTSrvcCall()
	{
		super();
	}
	public GCTSrvcCall(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String discardAppl(String si_RfrnId, String si_FileRfrnId)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering discardAppl....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
                logger.info("si_RfrnId  #"+si_RfrnId+"#");
                logger.info("si_FileRfrnId  #"+si_FileRfrnId+"#");
        
		FOISTuxedo RTSAVEGCTO	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RTSAVEGCTO = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			strCallStts="F";
			strCallMesg="Unable to get Client Object";
			return strCallMesg;
		}

		try
		{
			RTSAVEGCTO.tuxInit("RTSAVEGCTO");
			RTSAVEGCTO.setString("F_USERID",		0,	si_UserID);
			RTSAVEGCTO.setString("F_CLNTID",		0,	si_ClntID);
			RTSAVEGCTO.setString("F_FLAG",		        0,	"DISCARD_APPL");
			RTSAVEGCTO.setString("F_HLDGZONE",	        0,	si_RfrnId);
			RTSAVEGCTO.setString("F_HLDGZONE",	        1,	si_FileRfrnId);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			strCallStts="F";
			strCallMesg="Unable to write to buffer";
			return strCallMesg;
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RTSAVEGCTO.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			strCallStts="F";
			strCallMesg="Exception in Service Call";
			return strCallMesg;
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode1			= null;
		try
		{
			erorCode1				= RTSAVEGCTO.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			return erorCode1;
		}

	    try
	    {
	            RTSAVEGCTO.endSession();
	    }
	    catch(Exception e)
	    {
	            logger.fatal("Error In End Session:" + e.toString());
	            strCallStts="F";
	            strCallMesg="Session Problem";
	            return strCallMesg;
	    }
	    return "SUCCESS";
	}

    public String saveGCTAppl(String si_ApplMode, String si_RfrnId, String si_Dvsn, String si_Sttn, String si_Name, String si_Desg, String si_GstIn, String si_ApltType, String si_FileRfrnId, String si_CmdtCont, String si_CmdtDtls)
    {
            String strRfrnId="";

            logger.info("Entering saveDraft....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_ApplMode  #"+si_ApplMode+"#");
            logger.info("si_RfrnId  #"+si_RfrnId+"#");
            logger.info("si_Dvsn  #"+si_Dvsn+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            logger.info("si_Name  #"+si_Name+"#");
            logger.info("si_Desg  #"+si_Desg+"#");
            logger.info("si_GstIn  #"+si_GstIn+"#");
            logger.info("si_ApltType  #"+si_ApltType+"#");
            logger.info("si_FileRfrnId  #"+si_FileRfrnId+"#");
            logger.info("si_CmdtCont  #"+si_CmdtCont+"#");
            logger.info("si_CmdtDtls  #"+si_CmdtDtls+"#");
    
            FOISTuxedo RTSAVEGCTO   = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RTSAVEGCTO = new FOISTuxedo();
                    logger.info("Client Object Got.");
            }
            catch (Exception ne)
            {
                    logger.fatal("Unable to get the Client Object: " + ne.toString());
                    strCallStts="F";
                    strCallMesg="Unable to get Client Object";
                    return strCallMesg;
            }

            try
            {
                    RTSAVEGCTO.tuxInit("RTSAVEGCTO");
                    RTSAVEGCTO.setString("F_USERID",                0,      si_UserID);
                    RTSAVEGCTO.setString("F_CLNTID",                0,      si_ClntID);
                    RTSAVEGCTO.setString("F_FLAG",                  0,      "SAVE_GCTORQST");
                    RTSAVEGCTO.setString("F_COMMITFLAG",            0,      "Y");
                    RTSAVEGCTO.setString("F_ORDRBY1",              0,      si_Name);
                    RTSAVEGCTO.setString("F_ORDRBY2",              0,      si_Desg);
                    RTSAVEGCTO.setString("F_ORDRBY3",              0,      si_ApltType);
                    RTSAVEGCTO.setString("F_ORDRBY4",              0,      si_Dvsn);
                    RTSAVEGCTO.setString("F_ORDRBY5",              0,      si_Sttn);
                    RTSAVEGCTO.setString("F_ORDRBY6",              0,      si_CmdtCont);
                    RTSAVEGCTO.setString("F_NUMB",                  0,      si_GstIn);
                    RTSAVEGCTO.setString("F_ORDRBY8",              0,      si_RfrnId);
                    RTSAVEGCTO.setString("F_ORDRBY9",              0,      si_ApplMode);
                    RTSAVEGCTO.setString("F_RFRNNUMB",              0,      si_FileRfrnId);
                    int intCmdtCntr=0;
                    if(Integer.parseInt(si_CmdtCont)>1)
                    {
                        StringTokenizer st=new StringTokenizer(si_CmdtDtls,"@");
                        while(st.hasMoreTokens()) {
                            StringTokenizer st1=new StringTokenizer(st.nextToken(),"#");
                            String strCmdt=st1.nextToken();
                            String strExpdVol=st1.nextToken();
                            String strExpdRake=st1.nextToken();
                            String strTrfcType=st1.nextToken();
                            logger.info("Setting input for commodity ("+intCmdtCntr+"): "+strCmdt+", "+strExpdVol+"   "+strExpdRake+"   "+strTrfcType);
                            RTSAVEGCTO.setString("F_RPWBNUMB",                intCmdtCntr,      strCmdt);
                            RTSAVEGCTO.setString("F_RPWBNUMB1",               intCmdtCntr,      strExpdVol);
                            RTSAVEGCTO.setString("F_RPWBNUMB2",               intCmdtCntr,      strExpdRake);
                            RTSAVEGCTO.setString("F_CHRGDESC",                intCmdtCntr,      strTrfcType);
                            intCmdtCntr++;
                        }
                        
                    }
                    if(Integer.parseInt(si_CmdtCont)==1)
                    {
                        StringTokenizer st=new StringTokenizer(si_CmdtDtls,"#");
                        RTSAVEGCTO.setString("F_RPWBNUMB",                0,      st.nextToken());
                        RTSAVEGCTO.setString("F_RPWBNUMB1",               0,      st.nextToken());
                        RTSAVEGCTO.setString("F_RPWBNUMB2",               0,      st.nextToken());
                        RTSAVEGCTO.setString("F_CHRGDESC",                0,      st.nextToken());
                    }
            }
            catch(Exception e)
            {
                    logger.fatal("Unable to write to buffer : " + e.toString());
                    strCallStts="F";
                    strCallMesg="Unable to write to buffer";
                    return strCallMesg;
            }
            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RTSAVEGCTO.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Exception in Service Call";
                    return strCallMesg;
            }
                    //*************************************************************************************
                                                            //END of WTC calling
                    //*************************************************************************************
            // Checking For Any Error from Service
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RTSAVEGCTO.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode1 != null && (!erorCode1.equals("")))
            {
                    logger.fatal(erorCode1);
                    return erorCode1;
            }

        String strNewRfrnId="";
        try
        {
                strNewRfrnId                               = RTSAVEGCTO.getStringItemDef("F_RAKEID",0,"0");
        }
        catch(Exception e)
        {
                // F_ERORNO is not set by Developer, So, it is not an Error
        }
        try
        {
                RTSAVEGCTO.endSession();
        }
        catch(Exception e)
        {
                logger.fatal("Error In End Session:" + e.toString());
                strCallStts="F";
                strCallMesg="Session Problem";
                return strCallMesg;
        }
        return strNewRfrnId;
    }
    public String[][] fetchDraftAppl()
    {
        String strData[][] = null;   // Variable to be returned as output of function

        logger.info("Entering fetchDraftAppl....");
        logger.info("Function called with inputs :");
        logger.info("si_UserID  #"+si_UserID+"#");
        logger.info("si_ClntID  #"+si_ClntID+"#");

        FOISTuxedo RQFBDGCTQRY  = null;

        try
        {
                logger.info("Calling Tuxedo");
                RQFBDGCTQRY = new FOISTuxedo();
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
                RQFBDGCTQRY.tuxInit("RQFBDGCTQRY");
                RQFBDGCTQRY.setString("F_USERID",               0,      si_UserID);
                RQFBDGCTQRY.setString("F_CLNTID",               0,      si_ClntID);
                RQFBDGCTQRY.setString("F_FLAG",                 0,      "DRAFT_APPL_LIST");
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
                RQFBDGCTQRY.call("N");
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
        String erorCode1                        = null;
        try
        {
                erorCode1                               = RQFBDGCTQRY.getStringItemDef("F_ERORNO",0,"0");
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
                startRowCount1          = RQFBDGCTQRY.getStringItemDef("F_ROWCONT",0,"0");
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

        strData = new String[start1][22];

        logger.info("Start reading data for Draft Application List");

        for(int i=0; i<=start1-1; i++)
        {
                try
                {
                        strData[i][0]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY4",i,"0").trim();
                        strData[i][4]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQFBDGCTQRY.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQFBDGCTQRY.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][11]           = RQFBDGCTQRY.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][12]           = RQFBDGCTQRY.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strData[i][13]           = RQFBDGCTQRY.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strData[i][14]           = RQFBDGCTQRY.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][15]           = RQFBDGCTQRY.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strData[i][16]           = RQFBDGCTQRY.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strData[i][17]           = RQFBDGCTQRY.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
                        strData[i][18]           = RQFBDGCTQRY.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strData[i][19]           = RQFBDGCTQRY.getStringItemDef("F_OPBLUNTS",i,"0").trim();
                        strData[i][20]           = RQFBDGCTQRY.getStringItemDef("F_EXCPUNTS",i,"0").trim();
                        strData[i][21]           = RQFBDGCTQRY.getStringItemDef("F_FRMDUNTS",i,"0").trim();
                }
        catch(Exception d)
        {
        logger.fatal("Problem in Calling Service RQFBDGCTQRY and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
        }

        } // End of for Loop
        try
        {
                RQFBDGCTQRY.endSession();
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
	public String[][] getAddlData3()
	{
		return strAddlData3;
	}
	public String[][] getAddlData4()
	{
		return strAddlData4;
	}
	public String[][] getAddlData5()
	{
		return strAddlData5;
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