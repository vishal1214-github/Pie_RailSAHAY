package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

public class ZonalBDAqsnPlanSrvc
{
	static Logger logger = FoisLogger.getLogger(ZonalBDAqsnPlanSrvc.class.getName());
	private String strAddlData[][]=null;

	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
	public ZonalBDAqsnPlanSrvc()
	{
		super();
	}
	public ZonalBDAqsnPlanSrvc(String si_UserID, String si_ClntID)
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
			return null;
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
			return null;
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
			return null;
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
	            return null;
	    }
	    return null;
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
                        strCallStts="F";
                        strCallMesg=erorCode;
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

        strData = new String[start1][4];

        logger.info("Start reading dataset1");

        for(int i=0; i<=start1-1; i++)
        {
            try
            {
                    strData[i][0]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY1",i,"0").trim();
                    strData[i][1]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY2",i,"0").trim();
                    strData[i][2]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY3",i,"0").trim();
                    strData[i][3]           = RQBDUAQSNPLAN.getStringItemDef("F_ORDRBY4",i,"0").trim();                   
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
	public String[][] getAddlData()
	{
		return strAddlData;
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