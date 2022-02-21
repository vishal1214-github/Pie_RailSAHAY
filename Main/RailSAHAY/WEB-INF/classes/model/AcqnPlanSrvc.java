package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

public class AcqnPlanSrvc
{
	static Logger logger = FoisLogger.getLogger(AcqnPlanSrvc.class.getName());
        private String strStats[]=null;
        private String strStats1[]=null;
        private String strStats2[]=null;
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
	public AcqnPlanSrvc()
	{
		super();
	}
	public AcqnPlanSrvc(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
	public String delFile(String si_FileId)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering delFile....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
                logger.info("si_FileId  #"+si_FileId+"#");
        
		FOISTuxedo RQBDUAQSNPLAN	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQBDUAQSNPLAN = new FOISTuxedo();
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
			RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
			RQBDUAQSNPLAN.setString("F_USERID",		0,	si_UserID);
			RQBDUAQSNPLAN.setString("F_CLNTID",		0,	si_ClntID);
			RQBDUAQSNPLAN.setString("F_FLAG",		0,	"DEL_FILE");
			RQBDUAQSNPLAN.setString("F_HLDGZONE",	0,	si_FileId);
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
			RQBDUAQSNPLAN.call("N");
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
			erorCode1				= RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
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
	            RQBDUAQSNPLAN.endSession();
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

    public String delProposalFile(String si_PropId)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering delFile....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_PropId  #"+si_PropId+"#");
    
            FOISTuxedo RQBDUAQSNPLAN        = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQBDUAQSNPLAN = new FOISTuxedo();
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
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",             0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",             0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",               0,      "DEL_PROPOSAL");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_PropId);
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
                    RQBDUAQSNPLAN.call("N");
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
                    erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
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
                RQBDUAQSNPLAN.endSession();
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
	public String[][] getUploadedFiles(String si_Zone,String si_Date)
	{
		logger.info("Entered in getUploadedFiles");
		logger.info("si_Zone:"+si_Zone);
		logger.info("si_Date:"+si_Date);
		String strData[][]=null;
		FOISTuxedo RQBDUAQSNPLAN	= null;
		try
		{
			RQBDUAQSNPLAN = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
		}
		try
		{
			RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
			RQBDUAQSNPLAN.setString("F_USERID",		0,	si_UserID);
			RQBDUAQSNPLAN.setString("F_CLNTID",		0,	si_ClntID);
			RQBDUAQSNPLAN.setString("F_FLAG",		0,	"GET_FILES");
			RQBDUAQSNPLAN.setString("F_HLDGZONE",	0,	si_Zone);
			RQBDUAQSNPLAN.setString("F_HLDGZONE",	1,	si_Date);
		}
		catch(Exception e)
		{}		

		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQBDUAQSNPLAN.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());			
			return null;
		}
		String erorCode			= null;
		try
		{
			erorCode		= RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
            return null;
		}

        String startRowCount1   = null;
        int start1=0;
        try
        {
                startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
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

        strData = new String[start1][7];

        logger.info("Start reading dataset1");

        for(int i=0; i<=start1-1; i++)
        {
            try
            {
                    strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                    strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                    strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                    strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                    strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                    strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                    strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
        try
        {
        	RQBDUAQSNPLAN.endSession();
        }
        catch(Exception e)
        {
                logger.fatal("Error In End Session:" + e.toString());
                return null;
        }
        return strData;
	}
    public String[][] getProposalList(String si_Zone,String si_Dvsn,String si_PropType)
    {
            logger.info("Entered in getProposalList");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            logger.info("si_PropType:"+si_PropType);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",             0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",             0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",               0,      "LIST_PROPOSAL_FILES");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   2,      si_PropType);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
        return null;
            }

    String startRowCount1   = null;
    int start1=0;
    try
    {
            startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
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

    strData = new String[start1][18];

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
                strData[i][8]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY9",i,"0").trim();
                strData[i][9]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY10",i,"0").trim();
                strData[i][10]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE",i,"0").trim();
                strData[i][11]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                strData[i][12]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                strData[i][13]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                strData[i][14]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                strData[i][15]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                strData[i][16]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                strData[i][17]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNPLAN.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
    public String[][] getDateWiseProposal(String si_Zone,String si_Dvsn)
    {
            logger.info("Entered in getDateWiseProposal");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",             0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",             0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",               0,      "DATE_WISE_PROPOSALS");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
        return null;
            }

    String startRowCount1   = null;
    int start1=0;
    try
    {
            startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
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

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNPLAN.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
    public String[][] fetchBDPlanData(String si_Zone, String si_Dvsn,String si_Date,String si_Ctgr)
    {
            logger.info("Entered in fetchBDPlanData");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            logger.info("si_Date:"+si_Date);
            logger.info("si_Ctgr:"+si_Ctgr);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",       0,      "FETCH_PLAN_DATA");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Date);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   2,      si_Ctgr);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   3,      si_Dvsn);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    return null;
            }

            String startRowCount1   = null;
            int start1=0;
            try
            {
                    startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
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
        
            strData = new String[start1][25];
        
            logger.info("Start reading dataset1");
        
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                        strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][11]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strData[i][12]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strData[i][13]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
                        strData[i][14]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][15]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][16]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strData[i][17]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strData[i][18]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNFROM",i,"0").trim();
                        strData[i][19]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNTO",i,"0").trim();
                        strData[i][20]           = RQBDUAQSNPLAN.getStringItemDef("F_DVSNFROM",i,"0").trim();
                        strData[i][21]           = RQBDUAQSNPLAN.getStringItemDef("F_ZONEFROM",i,"0").trim();
                        strData[i][22]           = RQBDUAQSNPLAN.getStringItemDef("F_ZONETO",i,"0").trim();
                        strData[i][23]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strData[i][24]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLFRWH",i,"0").trim();
                    
                    for(int j=0;j<25;j++) {
                        strData[i][j]=fixDecimal(strData[i][j]);
                    }
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }

    public String[][] fetchDvsnPerf(String si_Zone)
    {
            logger.info("Entered in fetchDvsnPerf");
            logger.info("si_Zone:"+si_Zone);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",       0,      "DVSN_PERF_STATS");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    return null;
            }

            String startRowCount1   = null;
            String startRowCount2   = null;
            String startRowCount3   = null;
            int start1=0;
            int start2=0;
            int start3=0;
            try
            {
                    startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();                    
                    startRowCount2 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",1,"0");
                    start2 = new Integer(startRowCount2.trim()).intValue();               
                    startRowCount3 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",2,"0");
                    start3 = new Integer(startRowCount3.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
        
            logger.info("start1 : " + start1+", start2 : " + start2);
            strData= new String[start1][1];
            strAddlData = new String[start2][9];
            strAddlData1 = new String[start3][8];
            strAddlData2=new String[1][2];

        try
        {
                strAddlData2[0][0] = RQBDUAQSNPLAN.getStringItemDef("F_UNTS",0,"0");
                strAddlData2[0][1] = RQBDUAQSNPLAN.getStringItemDef("F_UNTS",1,"0");
        }
        catch(Exception d)
        {
                logger.fatal("Problem in Calling Service SRVCNAME and filling Yearly Zonal Loading Count F_UNTS into array" + d.toString());
                strCallStts="F";
                strCallMesg="Zonal Loading Fetch Issue";
                return null;
        }
            logger.info("Start reading dataset1");
        
        for(int i=0; i<=start1-1; i++)
        {
            try
            {
                    strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
            
            logger.info("Fetching Second Set of Data");
            for(int i=0; i<=start2-1; i++)
            {
                try
                {
                        strAddlData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strAddlData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strAddlData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();
                        strAddlData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();                   
                        strAddlData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strAddlData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strAddlData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strAddlData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strAddlData[i][8]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY10",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            logger.info("Fetching Third Set of Data");
            for(int i=0; i<=start3-1; i++)
            {
                try
                {
                        strAddlData1[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strAddlData1[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strAddlData1[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strAddlData1[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB3",i,"0").trim();                   
                        strAddlData1[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strAddlData1[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strAddlData1[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strAddlData1[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }
    public String sendNotification(String si_Ctgr, String si_Zone, String si_Dvsn, String si_Cmdt, String si_PropId) 
    {
        logger.info("Enter in sendNotification method");
        String retVal="";
        logger.info("Received inputs as si_Ctgr:"+si_Ctgr+", si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn+", si_Cmdt:"+si_Cmdt+", si_PropId:"+si_PropId);
        
        FOISTuxedo RTBDUAQSNPLAN        = null;
        try
        {
                RTBDUAQSNPLAN = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object from RTBDUAQSNPLAN");
        }
        try
        {
                RTBDUAQSNPLAN.tuxInit("RTBDUAQSNPLAN");
                RTBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                RTBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                RTBDUAQSNPLAN.setString("F_FLAG",       0,      "SEND_NOTIFICATION");
                RTBDUAQSNPLAN.setString("F_CTGR",       0,      si_Ctgr);
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   2,      si_Cmdt);
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   3,      "");
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   4,      "");
                RTBDUAQSNPLAN.setString("F_HLDGZONE",   5,      si_PropId);
        }
        catch(Exception e)
        {}              

        try
        {
                logger.info("Calling Tuxedo Service SRVCNAME ...");
                RTBDUAQSNPLAN.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.fatal("Exception while call to the service is " + e.toString());    
                return "FAILED: "+e.toString();
        }
        String erorCode                 = null;
        try
        {
                erorCode                = RTBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
        }
        catch(Exception e)
        {
                // F_ERORNO is not set by Developer, So, it is not an Error
        }
        if(erorCode != null && (!erorCode.equals("")))
        {
                logger.info("ErrorCode: " + erorCode);
                return "FAILED: "+erorCode;
        }
        try
        {
                RTBDUAQSNPLAN.endSession();
        }
        catch(Exception e)
        {
                logger.fatal("Error In End Session:" + e.toString());
                return "FAILED: "+e.toString();
        }
        return "SUCCESS";
    }
    public String[][] fetchZonlPerf(String si_Zone)
    {
            logger.info("Entered in fetchZonlPerf");
            logger.info("si_Zone:"+si_Zone);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",       0,      "ZONL_PERF_STATS");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    return null;
            }

            String startRowCount1   = null;
            String startRowCount2   = null;
            String startRowCount3   = null;
            String startRowCount4   = null;
            String startRowCount5   = null;
            int start1=0;
            int start2=0;
            int start3=0;
            int start4=0;
            int start5=0;
            
            String strRecvCont="";            
            String strRespCont="";            
            String strFrwdCont="";
            
            try
            {
                    startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();                    
                    startRowCount2 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",1,"0");
                    start2 = new Integer(startRowCount2.trim()).intValue();                      
                    startRowCount3 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",2,"0");
                    start3 = new Integer(startRowCount3.trim()).intValue();
                    startRowCount4 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",3,"0");
                    start4 = new Integer(startRowCount4.trim()).intValue();
                    startRowCount5 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",4,"0");
                    start5 = new Integer(startRowCount5.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
        
            logger.info("start1 : " + start1+", start2 : " + start2+", start3 : " + start3+", start4 : " + start4+", start5 : " + start5);
            
            try
            {
                    strRecvCont = RQBDUAQSNPLAN.getStringItemDef("F_UNTS",0,"0");
                    strRespCont = RQBDUAQSNPLAN.getStringItemDef("F_UNTS",1,"0");
                    strFrwdCont = RQBDUAQSNPLAN.getStringItemDef("F_UNTS",2,"0");
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
            logger.info("strRecvCont : " + strRecvCont+", strRespCont : " + strRespCont+", strFrwdCont : " + strFrwdCont);
            strStats=new String[7];
            try
            {
                    strStats[0] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",0,"0");
                    strStats[1] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",1,"0");
                    strStats[2] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",2,"0");
                    strStats[3] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",3,"0");
                    strStats[4] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",4,"0");
                    strStats[5] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",5,"0");
                    strStats[6] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",6,"0");
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
            strData= new String[start1][8];
            strAddlData = new String[start2][5];
            strAddlData1 = new String[start3][5];
            strAddlData2 = new String[start4][5];
            strAddlData3 = new String[start5][6];
            strAddlData4 = new String[1][3];
            
            strAddlData4[0][0]=strRecvCont;
            strAddlData4[0][1]=strRespCont;
            strAddlData4[0][2]=strFrwdCont;
            
            logger.info("Start reading dataset1");
        
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();
                strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
            
            logger.info("Fetching Second Set of Data");
            for(int i=0; i<=start2-1; i++)
            {
                try
                {
                        strAddlData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strAddlData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strAddlData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strAddlData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB3",i,"0").trim();                   
                        strAddlData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }            
            logger.info("Fetching Third Set of Data");
            for(int i=0; i<=start3-1; i++)
            {
                try
                {
                        strAddlData1[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strAddlData1[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strAddlData1[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strAddlData1[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNFROM",i,"0").trim();                   
                        strAddlData1[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNTO",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }            
            logger.info("Fetching Fourth Set of Data");
            for(int i=0; i<=start4-1; i++)
            {
                try
                {
                        strAddlData2[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strAddlData2[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLFRWH",i,"0").trim();
                        strAddlData2[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_EXCPUNTS",i,"0").trim();
                        strAddlData2[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_OPBLUNTS",i,"0").trim();                   
                        strAddlData2[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_DSPTUNTS",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }            
            logger.info("Fetching Fifth Set of Data");
            for(int i=0; i<=start5-1; i++)
            {
                try
                {
                        strAddlData3[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_SCTNFROM",i,"0").trim();
                        strAddlData3[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_SCTNTO",i,"0").trim();
                        strAddlData3[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_DVSNFROM",i,"0").trim();
                        strAddlData3[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_DVSNTO",i,"0").trim();                   
                        strAddlData3[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ZONEFROM",i,"0").trim();
                        strAddlData3[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ZONETO",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }


    public String[][] fetchDvsnPerfStats(String si_Dvsn)
    {
            logger.info("Entered in fetchDvsnPerfStats");
            logger.info("si_Dvsn:"+si_Dvsn);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",       0,      "DVSN_PERF_STATS");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Dvsn);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    return null;
            }

            String startRowCount1   = null;
            String startRowCount2   = null;
            String startRowCount3   = null;
            int start1=0;
            int start2=0;
            int start3=0;
            try
            {
                    startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();                    
                    startRowCount2 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",1,"0");
                    start2 = new Integer(startRowCount2.trim()).intValue();                      
                    startRowCount3 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",2,"0");
                    start3 = new Integer(startRowCount3.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
        
            logger.info("start1 : " + start1+", start2 : " + start2+", start3 : " + start3);
            strData= new String[start1][8];
            strAddlData = new String[start2][5];
            strAddlData1 = new String[start3][5];
            strStats=new String[7];
            try
            {
                    strStats[0] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",0,"0");
                    strStats[1] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",1,"0");
                    strStats[2] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",2,"0");
                    strStats[3] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",3,"0");
                    strStats[4] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",4,"0");
                    strStats[5] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",5,"0");
                    strStats[6] = RQBDUAQSNPLAN.getStringItemDef("F_RAKEID",6,"0");
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
            logger.info("Start reading dataset1");
        
        for(int i=0; i<=start1-1; i++)
        {
            try
            {
                strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();
                strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
                    
            logger.info("Fetching Second Set of Data");
            for(int i=0; i<=start2-1; i++)
            {
                try
                {
                        strAddlData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strAddlData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strAddlData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strAddlData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNFROM",i,"0").trim();                   
                        strAddlData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_STTNTO",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }            
            logger.info("Fetching Third Set of Data");
            for(int i=0; i<=start3-1; i++)
            {
                try
                {
                        strAddlData1[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strAddlData1[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_CLBLFRWH",i,"0").trim();
                        strAddlData1[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_EXCPUNTS",i,"0").trim();
                        strAddlData1[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_OPBLUNTS",i,"0").trim();                   
                        strAddlData1[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_DSPTUNTS",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }
        public void addBDPlanData(String si_Optn, String si_Zone, String si_Param1, String si_Param2, String si_Param3, String si_Param4,String si_Param5,String si_Param6,String si_Param7,String si_Param8,String si_Param9,String si_Param10,String si_Param11,String si_Param12,String si_Param13,String si_Param14,String si_Param15,String si_Param16,String si_Param17,String si_Param18, String si_Param19,String si_Param20,String si_Param21,String si_Param22,String si_Param23,String si_Param24,String si_Param25) 
        {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering addBDPlanData....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_Optn  #"+si_Optn+"#");
            logger.info("si_Zone  #"+si_Zone+"#");
            logger.info("si_Param1  #"+si_Param1+"#");
            logger.info("si_Param2  #"+si_Param2+"#");
            logger.info("si_Param3  #"+si_Param3+"#");
            logger.info("si_Param4  #"+si_Param4+"#");
            logger.info("si_Param5  #"+si_Param5+"#");
            logger.info("si_Param6  #"+si_Param6+"#");
            logger.info("si_Param7  #"+si_Param7+"#");
            logger.info("si_Param8  #"+si_Param8+"#");
            logger.info("si_Param9  #"+si_Param9+"#");
            logger.info("si_Param10  #"+si_Param10+"#");
            logger.info("si_Param11  #"+si_Param11+"#");
            logger.info("si_Param12  #"+si_Param12+"#");
            logger.info("si_Param13  #"+si_Param13+"#");
            logger.info("si_Param14  #"+si_Param14+"#");
            logger.info("si_Param15  #"+si_Param15+"#");
            logger.info("si_Param16  #"+si_Param16+"#");
            logger.info("si_Param17  #"+si_Param17+"#");
            logger.info("si_Param18  #"+si_Param18+"#");
            logger.info("si_Param19  #"+si_Param19+"#");
            logger.info("si_Param20  #"+si_Param20+"#");
            logger.info("si_Param21  #"+si_Param21+"#");
            logger.info("si_Param22  #"+si_Param22+"#");
            logger.info("si_Param23  #"+si_Param23+"#");
            logger.info("si_Param24  #"+si_Param24+"#");
            logger.info("si_Param25  #"+si_Param25+"#");
            
            FOISTuxedo RQBDUAQSNPLAN        = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQBDUAQSNPLAN = new FOISTuxedo();
                    logger.info("Client Object Got.");
            }
            catch (Exception ne)
            {
                    logger.fatal("Unable to get the Client Object: " + ne.toString());
                    strCallStts="F";
                    strCallMesg="Unable to get Client Object";
            }

            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",     0,      "SAVE_RECORD");
                    RQBDUAQSNPLAN.setString("F_AJSTFLAG",   0,      si_Optn);
                    RQBDUAQSNPLAN.setString("F_ZONE",       0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Param1);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Param2);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   2,      si_Param3);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   3,      si_Param4);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   4,      si_Param5);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   5,      si_Param6);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   6,      si_Param7);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   7,      si_Param8);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   8,      si_Param9);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   9,      si_Param10);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   10,     si_Param11);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   11,     si_Param12);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   12,     si_Param13);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   13,     si_Param14);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   14,     si_Param15);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   15,     si_Param16);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   16,     si_Param17);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   17,     si_Param18);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   18,     si_Param19);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   19,     si_Param20);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   20,     si_Param21);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   21,     si_Param22);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   22,     si_Param23);
                    /*
                    if(si_Optn.equals("CMDT_RAIL_COST")) {
                        String strCosts[]=si_Param24.split("/");
                        RQBDUAQSNPLAN.setString("F_HLDGZONE",   23,     strCosts[0]);
                        RQBDUAQSNPLAN.setString("F_EXCPUNTS",   0,     strCosts[1]);
                        RQBDUAQSNPLAN.setString("F_EXCPUNTS",   1,     strCosts[2]);                        
                    }
                    else
                    {
                        RQBDUAQSNPLAN.setString("F_HLDGZONE",   23,     si_Param24);
                    }
                    */
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   23,     si_Param24);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   24,     si_Param25);
            }
            catch(Exception e)
            {
                    logger.fatal("Unable to write to buffer : " + e.toString());
                    strCallStts="F";
                    strCallMesg="Unable to write to buffer";
            }
            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Exception in Service Call";
            }
                    //*************************************************************************************
                                                            //END of WTC calling
                    //*************************************************************************************
            // Checking For Any Error from Service
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
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
            }

            try
            {
                RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                logger.fatal("Error In End Session:" + e.toString());
                strCallStts="F";
                strCallMesg="Session Problem for the Service";
            }
            return;
        }

    public String[][] fetchResourceDtls(String si_Zone, String si_Dvsn)
    {
            logger.info("Entered in fetchResourceDtls");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN        = null;
            try
            {
                    RQBDUAQSNPLAN = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                    RQBDUAQSNPLAN.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",       0,      "RESOURCE_CENTRE");
                    RQBDUAQSNPLAN.setString("F_AJSTFLAG",       0,  "FETCH_DATA");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNPLAN.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());                 
                    return null;
            }
            String erorCode                 = null;
            try
            {
                    erorCode                = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    return null;
            }

            String startRowCount1   = null;
            int start1=0;
            try
            {
                    startRowCount1 = RQBDUAQSNPLAN.getStringItemDef("F_ROWCONT",0,"0");
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
        
            strData = new String[start1][12];
        
            logger.info("Start reading dataset1");
        
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                        strData[i][4]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][11]           = RQBDUAQSNPLAN.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNPLAN and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNPLAN.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }
    public String delPlanData(String si_Ctgr, String si_RowId) 
    {
        String strData[][] = null;   // Variable to be returned as output of function

        logger.info("Entering delPlanData....");
        logger.info("Function called with inputs :");
        logger.info("si_UserID  #"+si_UserID+"#");
        logger.info("si_ClntID  #"+si_ClntID+"#");
        logger.info("si_Ctgr  #"+si_Ctgr+"#");
        logger.info("si_RowId  #"+si_RowId+"#");
        
        FOISTuxedo RQBDUAQSNPLAN        = null;

        try
        {
                logger.info("Calling Tuxedo");
                RQBDUAQSNPLAN = new FOISTuxedo();
                logger.info("Client Object Got.");
        }
        catch (Exception ne)
        {
                logger.fatal("Unable to get the Client Object: " + ne.toString());
                strCallStts="F";
                strCallMesg="Unable to get Client Object";
        }

        try
        {
                RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
                RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
                RQBDUAQSNPLAN.setString("F_FLAG",     0,      "DEL_PLAN_DATA");
                RQBDUAQSNPLAN.setString("F_AJSTFLAG",   0,      si_Ctgr);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_RowId);
        }
        catch(Exception e)
        {
                logger.fatal("Unable to write to buffer : " + e.toString());
                strCallStts="F";
                strCallMesg="Unable to write to buffer";
        }
        try
        {
                logger.info("Calling Tuxedo Service SRVCNAME ...");
                RQBDUAQSNPLAN.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.fatal("Exception while call to the service is " + e.toString());
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
                erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
        }
        catch(Exception e)
        {
                // F_ERORNO is not set by Developer, So, it is not an Error
        }
        if(erorCode1 != null && (!erorCode1.equals("")))
        {
                logger.fatal(erorCode1);
                strCallMesg=erorCode1;
                return strCallMesg;
        }

        try
        {
            RQBDUAQSNPLAN.endSession();
        }
        catch(Exception e)
        {
            logger.fatal("Error In End Session:" + e.toString());
            strCallMesg="Session Problem for the Service";
            return strCallMesg;
        }
        return "SUCCESS";
    }
    
    public String addResDtls(String si_Zone, String si_Dvsn, String si_Param1, String si_Param2, String si_Param3, String si_Param4,String si_Param5,String si_Param6,String si_Param7, String si_Param8) 
    {
        String strData[][] = null;   // Variable to be returned as output of function

        logger.info("Entering addResDtls....");
        logger.info("Function called with inputs :");
        logger.info("si_UserID  #"+si_UserID+"#");
        logger.info("si_ClntID  #"+si_ClntID+"#");
        logger.info("si_Zone  #"+si_Zone+"#");
        logger.info("si_Dvsn  #"+si_Dvsn+"#");
        logger.info("si_Param1  #"+si_Param1+"#");
        logger.info("si_Param2  #"+si_Param2+"#");
        logger.info("si_Param3  #"+si_Param3+"#");
        logger.info("si_Param4  #"+si_Param4+"#");
        logger.info("si_Param5  #"+si_Param5+"#");
        logger.info("si_Param6  #"+si_Param6+"#");
        logger.info("si_Param7  #"+si_Param7+"#");
        logger.info("si_Param8  #"+si_Param8+"#");
        
        FOISTuxedo RQBDUAQSNPLAN        = null;

        try
        {
                logger.info("Calling Tuxedo");
                RQBDUAQSNPLAN = new FOISTuxedo();
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
                RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
                RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
                RQBDUAQSNPLAN.setString("F_FLAG",     0,      "RESOURCE_CENTRE");
                RQBDUAQSNPLAN.setString("F_AJSTFLAG",   0,      "ADD_RESOURCE");
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   2,      si_Param1);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   3,      si_Param2);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   4,      si_Param3);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   5,      si_Param4);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   6,      si_Param5);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   7,      si_Param6);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   8,      si_Param7);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   9,      si_Param8);
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
                RQBDUAQSNPLAN.call("N");
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
                erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
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
                return strCallMesg;
        }

        try
        {
            RQBDUAQSNPLAN.endSession();
        }
        catch(Exception e)
        {
            logger.fatal("Error In End Session:" + e.toString());
            strCallStts="F";
            strCallMesg="Session Problem for the Service";
            return strCallMesg;
        }
        return "SUCCESS";
    }

    public String delResource(String si_Zone, String si_ResId)
    {
    String strData[][] = null;   // Variable to be returned as output of function

    logger.info("Entering delPlanData....");
    logger.info("Function called with inputs :");
    logger.info("si_UserID  #"+si_UserID+"#");
    logger.info("si_ClntID  #"+si_ClntID+"#");
    logger.info("si_Zone  #"+si_Zone+"#");
    logger.info("si_ResId  #"+si_ResId+"#");
    
    FOISTuxedo RQBDUAQSNPLAN        = null;

    try
    {
            logger.info("Calling Tuxedo");
            RQBDUAQSNPLAN = new FOISTuxedo();
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
            RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
            RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
            RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
            RQBDUAQSNPLAN.setString("F_FLAG",     0,      "RESOURCE_CENTRE");
            RQBDUAQSNPLAN.setString("F_AJSTFLAG",   0,      "DEL_RESOURCE");
            RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
            RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_ResId);
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
            RQBDUAQSNPLAN.call("N");
            logger.info("CALL COMPLETED !");
    }
    catch(Exception e)
    {
            logger.fatal("Exception while call to the service is " + e.toString());
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
            erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
    }
    catch(Exception e)
    {
            // F_ERORNO is not set by Developer, So, it is not an Error
    }
    if(erorCode1 != null && (!erorCode1.equals("")))
    {
            logger.fatal(erorCode1);
            strCallMesg=erorCode1;
            return strCallMesg;
    }

    try
    {
        RQBDUAQSNPLAN.endSession();
    }
    catch(Exception e)
    {
        logger.fatal("Error In End Session:" + e.toString());
        strCallMesg="Session Problem for the Service";
        return strCallMesg;
    }
    return "SUCCESS";
    }
    public String getDataCnfmStts(String si_Zone, String si_Dvsn) {
        logger.info("Entering getDataCnfmStts....");
        logger.info("Function called with inputs :");
        logger.info("si_UserID  #"+si_UserID+"#");
        logger.info("si_ClntID  #"+si_ClntID+"#");
        logger.info("si_Zone  #"+si_Zone+"#");
        logger.info("si_Dvsn  #"+si_Dvsn+"#");
        
        FOISTuxedo RQBDUAQSNPLAN        = null;

        try
        {
                logger.info("Calling Tuxedo");
                RQBDUAQSNPLAN = new FOISTuxedo();
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
                RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
                RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
                RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
                RQBDUAQSNPLAN.setString("F_FLAG",     0,      "FETCH_CNFM_FLAG");
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
                RQBDUAQSNPLAN.setString("F_HLDGZONE",   1,      si_Dvsn);
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
                RQBDUAQSNPLAN.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.fatal("Exception while call to the service is " + e.toString());
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
                erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
        }
        catch(Exception e)
        {
                // F_ERORNO is not set by Developer, So, it is not an Error
        }
        if(erorCode1 != null && (!erorCode1.equals("")))
        {
                logger.fatal(erorCode1);
                strCallMesg=erorCode1;
                return strCallMesg;
        }
        String strCnfmFlag="N";
        try
        {
                strCnfmFlag                               = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",0,"0");
        }
        catch(Exception e)
        {
                // F_ERORNO is not set by Developer, So, it is not an Error
        }
        try
        {
            RQBDUAQSNPLAN.endSession();
        }
        catch(Exception e)
        {
            logger.fatal("Error In End Session:" + e.toString());
            strCallMesg="Session Problem for the Service";
            return strCallMesg;
        }
        return strCnfmFlag; 
    }

    public String freezeData(String si_Zone)
    {
    logger.info("Entering freezeData....");
    logger.info("Function called with inputs :");
    logger.info("si_UserID  #"+si_UserID+"#");
    logger.info("si_ClntID  #"+si_ClntID+"#");
    logger.info("si_Zone  #"+si_Zone+"#");
    
    FOISTuxedo RQBDUAQSNPLAN        = null;

    try
    {
            logger.info("Calling Tuxedo");
            RQBDUAQSNPLAN = new FOISTuxedo();
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
            RQBDUAQSNPLAN.tuxInit("RQBDUAQSNPLAN");
            RQBDUAQSNPLAN.setString("F_USERID",   0,      si_UserID);
            RQBDUAQSNPLAN.setString("F_CLNTID",   0,      si_ClntID);
            RQBDUAQSNPLAN.setString("F_FLAG",     0,      "FREEZE_DATA");
            RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_Zone);
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
            RQBDUAQSNPLAN.call("N");
            logger.info("CALL COMPLETED !");
    }
    catch(Exception e)
    {
            logger.fatal("Exception while call to the service is " + e.toString());
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
            erorCode1                               = RQBDUAQSNPLAN.getStringItemDef("F_ERORNO",0,"0");
    }
    catch(Exception e)
    {
            // F_ERORNO is not set by Developer, So, it is not an Error
    }
    if(erorCode1 != null && (!erorCode1.equals("")))
    {
            logger.fatal(erorCode1);
            strCallMesg=erorCode1;
            return strCallMesg;
    }

    try
    {
        RQBDUAQSNPLAN.endSession();
    }
    catch(Exception e)
    {
        logger.fatal("Error In End Session:" + e.toString());
        strCallMesg="Session Problem for the Service";
        return strCallMesg;
    }
    return "SUCCESS";
    }
	public String[] getStats()
	{
		return strStats;
	}
	public String[] getStats1()
	{
		return strStats1;
	}
        public String[] getStats2(){
            return strStats2;
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

    public String fixDecimal(String str) {
        if(str.equals(""))
            return str;
        else
        {
            if(str.indexOf(".")==0) {
                str="0"+str;
            }
        }
        return str;
        
    }
}