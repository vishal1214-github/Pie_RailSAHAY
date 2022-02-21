package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

public class AcqnPlanMISSrvc
{
	static Logger logger = FoisLogger.getLogger(AcqnPlanMISSrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;
        private String strAddlData2[][]=null;
        private String strAddlData3[][]=null;
        private String strAddlData4[][]=null;
        private String strAddlData5[][]=null;
        private String strAddlData6[][]=null;

	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public AcqnPlanMISSrvc()
	{
		super();
	}
	public AcqnPlanMISSrvc(String si_UserID, String si_ClntID)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
	}
        public String getZoneCode(String si_Dvsn) {            

            logger.info("Entered in getZoneCode");
            logger.info("si_Dvsn:"+si_Dvsn);
            
            FOISTuxedo RQBDUAQSNMIS = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",      0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",      0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",        0,      "GET_DVSN_ZONE");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",    0,      si_Dvsn);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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

            String strZone   = null;
            try
            {
                strZone = RQBDUAQSNMIS.getStringItemDef("F_ZONE",0,"0");
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                strCallStts="F";
                strCallMesg="Row Count Problem";
                return null;
            }

            logger.info("strZone : " + strZone);
            try
            {
                RQBDUAQSNMIS.endSession();
            }
            catch(Exception e)
            {
                logger.fatal("Error In End Session:" + e.toString());
                return null;
            }
            return strZone;
        }

    public String[][] fetchLOVs()
    {
            logger.info("Entered in fetchLOVs");
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",              0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",              0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",                0,      "FETCH_LOVS");
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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

    strData = new String[start1][3];

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNMIS.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
    public String[][] fetchCmdtRailCoef(String si_Zone, String si_Dvsn)
    {
            logger.info("Entered in fetchCmdtRailCoef");
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNPLAN = null;
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
                    RQBDUAQSNPLAN.setString("F_USERID",              0,      si_UserID);
                    RQBDUAQSNPLAN.setString("F_CLNTID",              0,      si_ClntID);
                    RQBDUAQSNPLAN.setString("F_FLAG",                0,      "CMDT_RAIL_COEF");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",            0,      si_Zone);
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",            1,      si_Dvsn);
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

    strData = new String[start1][3];

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
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
    public String[][] fetchResource(String si_Zone, String si_Dvsn, String si_Cmdt)
    {
            logger.info("Entered in fetchResource");
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",              0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",              0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",                0,      "FETCH_RESOURCE");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",            0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",            1,      si_Dvsn);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",            2,      si_Cmdt);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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

    strData = new String[start1][9];

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();
                strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                strData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNMIS.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
	public String[][] getUploadedFiles(String si_Zone,String si_Date)
	{
		logger.info("Entered in getUploadedFiles");
		logger.info("si_Zone:"+si_Zone);
		logger.info("si_Date:"+si_Date);
		String strData[][]=null;
		FOISTuxedo RQBDUAQSNMIS	= null;
		try
		{
			RQBDUAQSNMIS = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
		}
		try
		{
			RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
			RQBDUAQSNMIS.setString("F_USERID",		0,	si_UserID);
			RQBDUAQSNMIS.setString("F_CLNTID",		0,	si_ClntID);
			RQBDUAQSNMIS.setString("F_FLAG",		0,	"GET_FILES");
			RQBDUAQSNMIS.setString("F_HLDGZONE",	0,	si_Zone);
			RQBDUAQSNMIS.setString("F_HLDGZONE",	1,	si_Date);
		}
		catch(Exception e)
		{}		

		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQBDUAQSNMIS.call("N");
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
			erorCode		= RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
                startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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
                    strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                    strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                    strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                    strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                    strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                    strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                    strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
        try
        {
        	RQBDUAQSNMIS.endSession();
        }
        catch(Exception e)
        {
                logger.fatal("Error In End Session:" + e.toString());
                return null;
        }
        return strData;
	}
    public String[][] fetchPrgsMeasure(String si_Zone,String si_Cmdt, String si_MesrType)
    {
            logger.info("Entered in fetchPrgsMeasure");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Cmdt:"+si_Cmdt);
            logger.info("si_MesrType:"+si_MesrType);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",              0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",              0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",                0,      "PRGS_MEASURE");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",    0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",    1,      si_Cmdt);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",    2,      si_MesrType);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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
                strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                strData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                strData[i][9]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                strData[i][10]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE",i,"0").trim();
                strData[i][11]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE1",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNMIS.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
    public String[][] getProposalList(String si_Zone,String si_PropType)
    {
            logger.info("Entered in getProposalList");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_PropType:"+si_PropType);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS        = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",             0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",             0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",               0,      "LIST_PROPOSAL_FILES");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   1,      si_PropType);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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

    strData = new String[start1][17];

    logger.info("Start reading dataset1");

    for(int i=0; i<=start1-1; i++)
    {
        try
        {
                strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                strData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                strData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                strData[i][9]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                strData[i][10]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE",i,"0").trim();
                strData[i][11]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                strData[i][12]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                strData[i][13]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                strData[i][14]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                strData[i][15]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                strData[i][16]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
            strCallStts="F";
            strCallMesg="Data Problem";
            return null;
        }
    }
    try
    {
            RQBDUAQSNMIS.endSession();
    }
    catch(Exception e)
    {
            logger.fatal("Error In End Session:" + e.toString());
            return null;
    }
    return strData;
    }
    public String[][] fetchBDPlanData(String si_Zone, String si_Dvsn,String si_Cmdt,String si_Mnth, String si_Ctgr)
    {
            logger.info("Entered in fetchBDPlanData:"+si_Ctgr);
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            logger.info("si_Cmdt:"+si_Cmdt);
            logger.info("si_Mnth:"+si_Mnth);
            logger.info("si_Ctgr:"+si_Ctgr);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS        = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",       0,      "FETCH_PLAN_DATA");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   1,      si_Dvsn);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   2,      si_Cmdt);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   3,      si_Mnth);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   4,      si_Ctgr);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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
                        strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                        strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][11]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strData[i][12]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strData[i][13]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
                        strData[i][14]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][15]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][16]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strData[i][17]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strData[i][18]           = RQBDUAQSNMIS.getStringItemDef("F_STTNFROM",i,"0").trim();
                        strData[i][19]           = RQBDUAQSNMIS.getStringItemDef("F_STTNTO",i,"0").trim();
                        strData[i][20]           = RQBDUAQSNMIS.getStringItemDef("F_DVSNFROM",i,"0").trim();
                        strData[i][21]           = RQBDUAQSNMIS.getStringItemDef("F_ZONEFROM",i,"0").trim();
                        strData[i][22]           = RQBDUAQSNMIS.getStringItemDef("F_ZONETO",i,"0").trim();
                        strData[i][23]           = RQBDUAQSNMIS.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strData[i][24]           = RQBDUAQSNMIS.getStringItemDef("F_CLBLFRWH",i,"0").trim();
                    
                    for(int j=0;j<25;j++) {
                        strData[i][j]=fixDecimal(strData[i][j]);
                    }
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNMIS.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }

    public String[] fetchCmdtRailCost(String si_NumCmdt)
    {
            logger.info("Entered in fetchCmdtRailCost");
            logger.info("si_NumCmdt:"+si_NumCmdt);
            String strData[]=null;
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
                    RQBDUAQSNPLAN.setString("F_FLAG",               0,      "FETCH_CMDT_RAIL_COST");
                    RQBDUAQSNPLAN.setString("F_HLDGZONE",   0,      si_NumCmdt);
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

    String strTLRates   = null;
    String strWLRates   = null;
    try
    {
            strTLRates = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",0,"0");
            strWLRates = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",1,"0");
    }
    catch(Exception d)
    {
            logger.fatal("Problem in Calling Service SRVCNAME and fetching Trainload/Wagonload" + d.toString());
            strCallStts="F";
            strCallMesg="Row Count Problem";
            return null;
    }

    logger.info("strTLRates : " + strTLRates+", strWLRates: "+strWLRates);

    strData = new String[2];
    strData[0]=strTLRates;
    strData[1]=strWLRates;
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
    public String[][] fetchDvsnPerfStats(String si_Zone)
    {
            logger.info("Entered in fetchDvsnPerfStats");
            logger.info("si_Zone:"+si_Zone);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS        = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",       0,      "DVSN_PERF_STATS");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   0,      si_Zone);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            int start1=0;
            int start2=0;
            
            try
            {
                    startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();                    
                    startRowCount2 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",1,"0");
                    start2 = new Integer(startRowCount2.trim()).intValue(); 
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
        
            logger.info("Start reading dataset1");
        
        for(int i=0; i<=start1-1; i++)
        {
            try
            {
                strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
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
                        strAddlData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strAddlData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strAddlData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();
                        strAddlData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();                   
                        strAddlData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strAddlData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strAddlData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strAddlData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strAddlData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }   
            try
            {
                    RQBDUAQSNMIS.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }
    public String[][] fetchIRDashbordData(String si_Zone, String si_Cmdt)
    {
            logger.info("Entered in fetchIRDashbordData");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Cmdt:"+si_Cmdt);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS        = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNMIS");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",       0,      "IR_DASHBOARD");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   1,      si_Cmdt);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
            String strTotlProp="";
            String strPropCmpl="";
            
            try
            {
                    strTotlProp = RQBDUAQSNMIS.getStringItemDef("F_UNTS",0,"0");
                    strPropCmpl = RQBDUAQSNMIS.getStringItemDef("F_UNTS",1,"0");
                    startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();                    
                    startRowCount2 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",1,"0");
                    start2 = new Integer(startRowCount2.trim()).intValue();              
                    startRowCount3 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",2,"0");
                    start3 = new Integer(startRowCount3.trim()).intValue();              
                    startRowCount4 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",3,"0");
                    start4 = new Integer(startRowCount4.trim()).intValue();              
                    startRowCount5 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",4,"0");
                    start5 = new Integer(startRowCount5.trim()).intValue(); 
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
        
            logger.info("strTotlProp: "+strTotlProp+", strPropCmpl: "+strPropCmpl+", start1 : " + start1+", start2 : " + start2+", start3 : " + start3+", start4 : " + start4+", start5 : " + start5);
            strData= new String[start1][7];
            strAddlData = new String[start2][6];
            strAddlData1 = new String[start3][5];
            strAddlData2 = new String[start4][5];
            strAddlData3 = new String[start5][6];
            strAddlData4 = new String[1][2];
            
            strAddlData4[0][0]=strTotlProp;
            strAddlData4[0][1]=strPropCmpl;
        
            logger.info("Start reading dataset1");
        
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                    strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                    strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                    strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                    strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();
                    strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                    strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_RAKETYPE",i,"0").trim();
                    strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_RAKEID",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
        
            logger.info("Start reading dataset2");
        
            for(int i=0; i<=start2-1; i++)
            {
                try
                {
                    strAddlData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                    strAddlData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                    strAddlData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                    strAddlData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                    strAddlData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                    strAddlData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
        
            logger.info("Start reading dataset3");
        
            for(int i=0; i<=start3-1; i++)
            {
                try
                {
                    strAddlData1[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                    strAddlData1[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                    strAddlData1[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                    strAddlData1[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                    strAddlData1[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
        
            logger.info("Start reading dataset4");
        
            for(int i=0; i<=start4-1; i++)
            {
                try
                {
                    strAddlData2[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                    strAddlData2[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_OPBLUNTS",i,"0").trim();
                    strAddlData2[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_DSPTUNTS",i,"0").trim();
                    strAddlData2[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_EXCPUNTS",i,"0").trim();
                    strAddlData2[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_FRMDUNTS",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            } 
        
            logger.info("Start reading dataset5");
        
            for(int i=0; i<=start5-1; i++)
            {
                try
                {
                    strAddlData3[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_STTNFROM",i,"0").trim();
                    strAddlData3[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_STTNTO",i,"0").trim();
                    strAddlData3[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_DVSNFROM",i,"0").trim();
                    strAddlData3[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_DVSNTO",i,"0").trim();
                    strAddlData3[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ZONEFROM",i,"0").trim();
                    strAddlData3[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ZONETO",i,"0").trim();
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }   
            try
            {
                    RQBDUAQSNMIS.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            logger.info("Returning final set of data");
            return strData;
    }
    public String[][] fetchRailRoadCmpr(String si_Zone, String si_Dvsn,String si_Cmdt,String si_Mnth)
    {
            logger.info("Entered in fetchRailRoadCmpr");
            logger.info("si_Zone:"+si_Zone);
            logger.info("si_Dvsn:"+si_Dvsn);
            logger.info("si_Cmdt:"+si_Cmdt);
            logger.info("si_Mnth:"+si_Mnth);
            String strData[][]=null;
            FOISTuxedo RQBDUAQSNMIS        = null;
            try
            {
                    RQBDUAQSNMIS = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object from RQBDUAQSNPLAN");
            }
            try
            {
                    RQBDUAQSNMIS.tuxInit("RQBDUAQSNMIS");
                    RQBDUAQSNMIS.setString("F_USERID",     0,      si_UserID);
                    RQBDUAQSNMIS.setString("F_CLNTID",     0,      si_ClntID);
                    RQBDUAQSNMIS.setString("F_FLAG",       0,      "RAIL_ROAD_COMPARISON");
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   0,      si_Zone);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   1,      si_Dvsn);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   2,      si_Cmdt);
                    RQBDUAQSNMIS.setString("F_HLDGZONE",   3,      si_Mnth);
            }
            catch(Exception e)
            {}              

            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQBDUAQSNMIS.call("N");
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
                    erorCode                = RQBDUAQSNMIS.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQBDUAQSNMIS.getStringItemDef("F_ROWCONT",0,"0");
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
        
            logger.info("Start reading dataset1");
        
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        strData[i][0]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
                        strData[i][4]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQBDUAQSNMIS.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][11]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][12]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strData[i][13]           = RQBDUAQSNMIS.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strData[i][14]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][15]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strData[i][16]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strData[i][17]           = RQBDUAQSNMIS.getStringItemDef("F_RPWBNUMB3",i,"0").trim();
                        strData[i][18]           = RQBDUAQSNMIS.getStringItemDef("F_CLBLUNTS",i,"0").trim();
                        strData[i][19]           = RQBDUAQSNMIS.getStringItemDef("F_OPBLUNTS",i,"0").trim();
                   
                    if(!strData[i][19].equals("ROAD")) {                        
                        logger.info("Checking for additional costs:"+strData[i][18]);
                        /*
                        String strAddlCosts[]=strData[i][18].split("/");
                        for(int k=3;k<17;k++) {
                            double dblVal=((Double.parseDouble(strData[i][k])*(k+1)*100)+getAddlCost(strAddlCosts[0])+getAddlCost(strAddlCosts[1])+getAddlCost(strAddlCosts[2]))/((k+1)*100);
                            strData[i][k]=String.format("%.2f", dblVal);
                        }
                    */

                    for(int k=3;k<17;k++) {
                        double dblVal=((Double.parseDouble(strData[i][k])*(k-2)*100)+getAddlCost(strData[i][18]))/((k-2)*100);
                        /*logger.info(strData[i][k]+"   "+strData[i][18]+"   "+((k-2)*100));*/
                        strData[i][k]=String.format("%.2f", dblVal);
                    }
                        /*
                        for(int j=3;j<8;j++) {
                            double dblVal=((Double.parseDouble(strData[i][j])*(j+1)*100)+(100*Double.parseDouble(strData[i][18])))/(((j+1)*100)+100);
                            strData[i][j]=String.format("%.2f", dblVal);
                        }
                        for(int j=8;j<17;j++) {
                            double dblVal=((Double.parseDouble(strData[i][j])*(j+1)*100)+(200*Double.parseDouble(strData[i][18])))/(((j+1)*100)+200);
                            strData[i][j]=String.format("%.2f", dblVal);
                        }
                        */
                    }
                    
                    for(int j=0;j<19;j++) {
                        strData[i][j]=fixDecimal(strData[i][j]);
                    }
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQBDUAQSNMIS and filling data into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Data Problem";
                    return null;
                }
            }
            try
            {
                    RQBDUAQSNMIS.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return null;
            }
            return strData;
    }

    public String getMaxAnlyDate() {
        logger.info("Entering getMaxAnlyDate....");
        logger.info("Function called with inputs :");
        logger.info("si_UserID  #"+si_UserID+"#");
        logger.info("si_ClntID  #"+si_ClntID+"#");
        
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
                RQBDUAQSNPLAN.setString("F_FLAG",     0,      "FETCH_MAX_ANLY_DATE");
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
        String strMaxAnlyDate="";
        try
        {
                strMaxAnlyDate                               = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",0,"0");
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
        return strMaxAnlyDate; 
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
	public String[][] getAddlData6()
	{
		return strAddlData6;
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
        public double getAddlCost(String str) {
            double dblVal=0;
            if(str.equals("") || (str==null))
                return 0;
            else
                return Double.parseDouble(str);
        }
}