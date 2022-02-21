package model;

import tuxedo.*;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;

public class GG_NodlOfcrSrvc
{
	static Logger logger = FoisLogger.getLogger(GG_NodlOfcrSrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;	
	private String strAddlData2[][]=null;
	String si_UserID="";
	String si_ClntID="";
	String si_Dvsn ="";
	String si_Zone="";
	String si_Desg="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";
        private String maskString(String strText, int start, int end, char maskChar) 
          throws Exception{
          
          if(strText == null || strText.equals(""))
              return "";
          
          if(start < 0)
              start = 0;
          
          if( end > strText.length() )
              end = strText.length();
              
          if(start > end)
              throw new Exception("start index cannot be greater than end index");
          
          int maskLength = end - start;
          
          if(maskLength == 0)
              return strText;
          
          StringBuilder sbMaskString = new StringBuilder(maskLength);
          
          for(int i = 0; i < maskLength; i++){
              sbMaskString.append(maskChar);
          }
          
          return strText.substring(0, start) 
              + sbMaskString.toString() 
              + strText.substring(start + maskLength);
         }
	public GG_NodlOfcrSrvc()
	{
		super();
	}
	public GG_NodlOfcrSrvc(String si_UserID, String si_ClntID, String si_Dvsn, String si_Zone, String si_Desg)
	{
		this.si_UserID=si_UserID;
		this.si_ClntID=si_ClntID;
		this.si_Dvsn=si_Dvsn;
		this.si_Zone=si_Zone;
		this.si_Desg=si_Desg;
	}
	public String[] getSmryStats() 
	{
		String strData[] = null;   // Variable to be returned as output of function

		logger.info("Entering getSmryStats....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",		        0,	"SAHAY_SMRYOFCR");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Dvsn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Zone);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Desg);
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
		strData = new String[6];
		
		for(int i=0;i<6;i++)
			strData[i]="";
			
		logger.info("Start reading data for OD Pairs List");
		try
		{

                strData[0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",0,"0").trim();
                strData[1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",0,"0").trim();
                strData[2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",0,"0").trim();
                strData[3]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",0,"0").trim();				
                strData[4]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",0,"0").trim();				
                strData[5]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",0,"0").trim();
        }
        catch(Exception d)
        {
            logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
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
	public String[][] getCncrList()
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getCncrList....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"SAHAY_CNCNOFCR");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Dvsn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Zone);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Desg);
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

		strData = new String[start1][14];
		
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
	public String[][] getSRList()
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getSRList....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"SAHAY_SROFCR");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Dvsn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Zone);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Desg);
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
				strData[i][14]		= RQSAHAYPRTL.getStringItemDef("F_RAKEID",i,"0").trim();	
				strData[i][15]		= RQSAHAYPRTL.getStringItemDef("F_RAKETYPE",i,"0").trim();	
				strData[i][16]		= RQSAHAYPRTL.getStringItemDef("F_CLBLUNTS",i,"0").trim();	
				strData[i][17]		= RQSAHAYPRTL.getStringItemDef("F_EXCPUNTS",i,"0").trim();	
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
	public String[][] getIAList()
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getIAList....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"SAHAY_IAOFCR");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Dvsn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Zone);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Desg);
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

		strData = new String[start1][25];
		
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
				strData[i][14]		= RQSAHAYPRTL.getStringItemDef("F_TRMNSTTN",i,"0").trim();	   
				strData[i][15]		= RQSAHAYPRTL.getStringItemDef("F_TRMNTIME",i,"0").trim();	   
				strData[i][16]		= RQSAHAYPRTL.getStringItemDef("F_TRMNTYPE",i,"0").trim();	   
				strData[i][17]		= RQSAHAYPRTL.getStringItemDef("F_TRNSTYPE",i,"0").trim();	   
				strData[i][18]		= RQSAHAYPRTL.getStringItemDef("F_TRSPDATE",i,"0").trim();	   
				strData[i][19]		= RQSAHAYPRTL.getStringItemDef("F_TRSPFLAG",i,"0").trim();	   
				strData[i][20]		= RQSAHAYPRTL.getStringItemDef("F_TRSPOWNGRLY",i,"0").trim();
				strData[i][21]		= RQSAHAYPRTL.getStringItemDef("F_TRSPRAKEID",i,"0").trim();
				strData[i][22]		= RQSAHAYPRTL.getStringItemDef("F_TRSPSQNCNUMB",i,"0").trim();
				strData[i][23]		= RQSAHAYPRTL.getStringItemDef("F_TRSPSTTN",i,"0").trim();	   
				strData[i][24]		= RQSAHAYPRTL.getStringItemDef("F_TRSPWGONID",i,"0").trim();
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
	public String[][] getFrwdOfcrList()
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getFrwdOfcrList....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");

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
			RQSAHAYPRTL.setString("F_FLAG",			0,	"FRWD_OFCR_LIST");
			RQSAHAYPRTL.setString("F_HLDGZONE",		0,	si_Dvsn);
			RQSAHAYPRTL.setString("F_HLDGZONE",		1,	si_Zone);
			RQSAHAYPRTL.setString("F_HLDGZONE",		2,	si_Desg);
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

		strData = new String[start1][3];
		
		logger.info("Start reading data for Forward Officers List");
	
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strData[i][0]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();    /*ZONE/DVSN FLAG*/
				strData[i][1]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();	/*ZONE/DVSN*/
				strData[i][2]		= RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();	/*DESIGNATION*/
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
	public String saveResponse(String si_TktId, String si_RespFrwdFlag, String si_TktType, String si_RespText, String si_FrwdDvsn, String si_FrwdDOfcr, String si_FrwdZone, String si_FrwdZOfcr)
	{
		String strData[][] = null;   // Variable to be returned as output of function
		String erorCode                        = null;
		String strRetRmrk                       = "FAIL";       
		String strMessg= "";

		logger.info("Entering saveResponse....");
		logger.info("Function called with inputs :");
		logger.info("si_UserID  #"+si_UserID+"#");
		logger.info("si_ClntID  #"+si_ClntID+"#");
		logger.info("si_TktId  #"+si_TktId+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_Zone  #"+si_Zone+"#");
		logger.info("si_Desg  #"+si_Desg+"#");
		logger.info("si_RespFrwdFlag  #"+si_RespFrwdFlag+"#");
		logger.info("si_TktType  #"+si_TktType+"#");
		logger.info("si_FrwdDvsn  #"+si_FrwdDvsn+"#");
		logger.info("si_FrwdDOfcr  #"+si_FrwdDOfcr+"#");
		logger.info("si_FrwdZone  #"+si_FrwdZone+"#");
		logger.info("si_FrwdZOfcr  #"+si_FrwdZOfcr+"#");
	        logger.info("si_RespText  #"+si_RespText+"#");


		FOISTuxedo RTSAHAYPRTL	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RTSAHAYPRTL = new FOISTuxedo();
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
			RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
			RTSAHAYPRTL.setString("F_USERID",		0,	si_UserID);
			RTSAHAYPRTL.setString("F_CLNTID",		0,	si_ClntID);
			RTSAHAYPRTL.setString("F_FLAG",			0,	"SAVE_SRVCRESP");
			RTSAHAYPRTL.setString("F_ORDRBY1",		0,	si_TktId);
			RTSAHAYPRTL.setString("F_ORDRBY2",		0,	si_Dvsn);
			RTSAHAYPRTL.setString("F_ORDRBY3",		0,	si_RespFrwdFlag);
			RTSAHAYPRTL.setString("F_ORDRBY4",		0,	si_TktType);
			RTSAHAYPRTL.setString("F_ORDRBY5",		0,	si_RespText);
			RTSAHAYPRTL.setString("F_ORDRBY6",		0,	si_Zone);
			RTSAHAYPRTL.setString("F_ORDRBY7",		0,	si_Desg);
			RTSAHAYPRTL.setString("F_ORDRBY1",		1,	si_FrwdDvsn);
			RTSAHAYPRTL.setString("F_ORDRBY1",		2,	si_FrwdDOfcr);
			RTSAHAYPRTL.setString("F_ORDRBY1",		3,	si_FrwdZone);
			RTSAHAYPRTL.setString("F_ORDRBY1",		4,	si_FrwdZOfcr);
		    RTSAHAYPRTL.setString("F_COMMITFLAG",              0,      "Y");
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
			RTSAHAYPRTL.call("N");
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
		try
		{
		        erorCode                        = RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		        logger.info("erorCode::"+erorCode);
		}
		catch(Exception e)
		{
		        // F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
		        
		        logger.fatal(erorCode);
		        strRetRmrk = strRetRmrk+"#"+erorCode; 
		    
		        
		}
                else
                {
                
                    try
                    {                                           
                    strRetRmrk                        = RTSAHAYPRTL.getStringItemDef("F_RMRK",  0,      "0").trim();
                    
                    }
                    catch(Exception d)
                    {
                    logger.fatal("Problem in Calling Service RTSAHAYPRTL and filling data into array" + d.toString());
                    strMessg = strRetRmrk+"#"+d.toString(); 
                    
                    }
                }
		try
		{
			RTSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
			strCallStts="F";
			strCallMesg="Session Problem";
			return null;
		}
		return strRetRmrk;
		
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