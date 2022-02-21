package model;

import tuxedo.*;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;

public class FrgtPymtSrvc
{
	static Logger logger = FoisLogger.getLogger(FrgtPymtSrvc.class.getName());
	private String strAddlData[][]=null;
	private String strAddlData1[][]=null;	
	private String strAddlData2[][]=null;
	String si_CustID="";
        String si_ClntId="";
	String si_CustCode="";
	private String strCallStts="S";
	private String strCallMesg="";
        
	public FrgtPymtSrvc()
	{
		super();
	}
	public FrgtPymtSrvc(String si_CustID, String si_CustCode, String si_ClntId)
	{
		this.si_CustID=si_CustID;
		this.si_CustCode=si_CustCode;
                this.si_ClntId=si_ClntId;
	}	
	public String[][] getOtsgPymtList(String si_Sttn, String si_DateFrom, String si_DateTo, String si_InvcFlag)
	{
		String strData[][] = null;   // Variable to be returned as output of function

		logger.info("Entering getOtsgPymtList....");
		logger.info("Function called with inputs :");
		logger.info("si_CustID  #"+si_CustID+"#");
		logger.info("si_CustCode  #"+si_CustCode+"#");
		logger.info("si_Sttn  #"+si_Sttn+"#");
		logger.info("si_DateFrom  #"+si_DateFrom+"#");
		logger.info("si_DateTo  #"+si_DateTo+"#");
		logger.info("si_InvcFlag  #"+si_InvcFlag+"#");

		FOISTuxedo TQOSTDCHRGS	= null;

		try
		{
			logger.info("Calling Tuxedo");
			TQOSTDCHRGS = new FOISTuxedo();
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
			TQOSTDCHRGS.tuxInit("TQOSTDCHRGS");
			TQOSTDCHRGS.setString("F_USERID",	0,	si_CustID);
			TQOSTDCHRGS.setString("F_STTN",		0,	si_Sttn);
			TQOSTDCHRGS.setString("F_CUSTCODE",	0,	si_CustCode);
			TQOSTDCHRGS.setString("F_DATEFROM",	0,	si_DateFrom);
			TQOSTDCHRGS.setString("F_DATETO",       0,	si_DateTo);
			TQOSTDCHRGS.setString("F_INVCFLAG",     0,	si_InvcFlag);
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
			TQOSTDCHRGS.call("N");
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
			erorCode1				= TQOSTDCHRGS.getStringItemDef("F_ERORNO",0,"0");
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
			startRowCount1 = TQOSTDCHRGS.getStringItemDef("F_ROWCONT",0,"0");
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
                logger.info("Start reading data for Outstanding Payments");
                
                if(si_InvcFlag.equals("I"))
                {
                    strData = new String[start1][18];
                    for(int i=0; i<=start1-1; i++)
                    {
                            try
                            {
                                    
                                    strData[i][0]           = TQOSTDCHRGS.getStringItemDef("F_FNOTNUMB",i,"0").trim();
                                    strData[i][1]           = TQOSTDCHRGS.getStringItemDef("F_FNOTDATE",i,"0").trim();
                                    strData[i][2]           = TQOSTDCHRGS.getStringItemDef("F_INVCNUMB",i,"0").trim();
                                    strData[i][3]           = TQOSTDCHRGS.getStringItemDef("F_INVCDATE",i,"0").trim();                               
                                    strData[i][4]           = TQOSTDCHRGS.getStringItemDef("F_STTNFROM",i,"0").trim();                               
                                    strData[i][5]           = TQOSTDCHRGS.getStringItemDef("F_STTNTO",i,"0").trim();                               
                                    strData[i][6]           = TQOSTDCHRGS.getStringItemDef("F_CNSG",i,"0").trim();                               
                                    strData[i][7]           = TQOSTDCHRGS.getStringItemDef("F_WRFGOTSG",i,"0").trim();                               
                                    strData[i][8]           = TQOSTDCHRGS.getStringItemDef("F_FRGTOTSG",i,"0").trim();                               
                                    strData[i][9]           = TQOSTDCHRGS.getStringItemDef("F_DMRGOTSG",i,"0").trim();                              
                                    strData[i][10]          = TQOSTDCHRGS.getStringItemDef("F_LOCLCHRGOTSG",i,"0").trim();                              
                                    strData[i][11]          = TQOSTDCHRGS.getStringItemDef("F_FNOTID",i,"0").trim();                             
                                    strData[i][12]          = TQOSTDCHRGS.getStringItemDef("F_INVCID",i,"0").trim();                             
                                    strData[i][13]          = TQOSTDCHRGS.getStringItemDef("F_CCHGFLAG",i,"0").trim();                                  
                                    strData[i][14]          = TQOSTDCHRGS.getStringItemDef("F_CHNGDATE",i,"0").trim();                             
                                    strData[i][15]          = TQOSTDCHRGS.getStringItemDef("F_CHRGACRDDATE",i,"0").trim();                             
                                    strData[i][16]          = TQOSTDCHRGS.getStringItemDef("F_CHRGCODE",i,"0").trim();                             
                                    strData[i][17]          = TQOSTDCHRGS.getStringItemDef("F_PAIDTYPE",i,"0").trim();                             
                                }
                                catch(Exception d)
                                {
                                    logger.fatal("Problem in Calling Service TQOSTDCHRGS and filling data into array" + d.toString());
                                    strCallStts="F";
                                    strCallMesg="Data Problem";
                                    return null;
                                }
                    
                        } // End of for Loop
                }
                else
                {
                    strData = new String[start1][6];

                    for(int i=0; i<=start1-1; i++)
                    {
                            try
                            {
                                strData[i][0]           = TQOSTDCHRGS.getStringItemDef("F_FNOTID",i,"0").trim();
                                strData[i][1]           = TQOSTDCHRGS.getStringItemDef("F_FNOTDATE",i,"0").trim();
                                strData[i][2]           = TQOSTDCHRGS.getStringItemDef("F_FRSTPLCTTIME",i,"0").trim();
                                strData[i][3]           = TQOSTDCHRGS.getStringItemDef("F_FITTIME",i,"0").trim();                               
                                strData[i][4]           = TQOSTDCHRGS.getStringItemDef("F_GOVTAMNT",i,"0").trim();                               
                                strData[i][5]           = TQOSTDCHRGS.getStringItemDef("F_INKMRDNG",i,"0").trim();                               
                            }
                            catch(Exception d)
                            {
                                logger.fatal("Problem in Calling Service TQOSTDCHRGS and filling data into array" + d.toString());
                                strCallStts="F";
                                strCallMesg="Data Problem";
                                return null;
                            }
                
                    } // End of for Loop
                }
		try
		{
			TQOSTDCHRGS.endSession();
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
    public String getOtsgChrgSmry()
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getOtsgChrgSmry....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "OTSG_CHRG_SMRY");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
            logger.info("Payments Count:"+startRowCount1);
            return startRowCount1;            
        }
    public String[][] getOtsgChrg(String si_Sttn)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getOtsgChrg....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "OTSG_CHRG");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_Sttn);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
            logger.info("Start reading data for Outstanding Payments");
            
            strData = new String[start1][31];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        
                        strData[i][0]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY4",i,"0").trim();                               
                        strData[i][4]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY5",i,"0").trim();                               
                        strData[i][5]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY6",i,"0").trim();                               
                        strData[i][6]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY7",i,"0").trim();                               
                        strData[i][7]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY8",i,"0").trim();                               
                        strData[i][8]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY9",i,"0").trim();                               
                        strData[i][9]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY10",i,"0").trim();                              
                        strData[i][10]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE",i,"0").trim();                              
                        strData[i][11]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE1",i,"0").trim();                             
                        strData[i][12]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE2",i,"0").trim();                             
                        strData[i][13]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE3",i,"0").trim();                                  
                        strData[i][14]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB",i,"0").trim();                             
                        strData[i][15]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB1",i,"0").trim();                             
                        strData[i][16]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB2",i,"0").trim();                             
                        strData[i][17]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB3",i,"0").trim();                             
                        strData[i][18]          = RQFBDFRGTPYMT.getStringItemDef("F_RAKEID",i,"0").trim();                             
                        strData[i][19]          = RQFBDFRGTPYMT.getStringItemDef("F_RAKETYPE",i,"0").trim();                             
                        strData[i][20]          = RQFBDFRGTPYMT.getStringItemDef("F_UNTS",i,"0").trim();                           
                        strData[i][21]          = RQFBDFRGTPYMT.getStringItemDef("F_FRWH",i,"0").trim(); 
                        strData[i][22]          = RQFBDFRGTPYMT.getStringItemDef("F_CLBLUNTS",i,"0").trim();                           
                        strData[i][23]          = RQFBDFRGTPYMT.getStringItemDef("F_CLBLFRWH",i,"0").trim(); 
                        strData[i][24]          = RQFBDFRGTPYMT.getStringItemDef("F_OPBLUNTS",i,"0").trim(); 
                        strData[i][25]          = RQFBDFRGTPYMT.getStringItemDef("F_DSPTUNTS",i,"0").trim(); 
                        strData[i][26]          = RQFBDFRGTPYMT.getStringItemDef("F_FRMDUNTS",i,"0").trim(); 
                        strData[i][27]          = RQFBDFRGTPYMT.getStringItemDef("F_STTNTO",i,"0").trim(); 
                        strData[i][28]          = RQFBDFRGTPYMT.getStringItemDef("F_STTNFROM",i,"0").trim(); 
                        strData[i][29]          = RQFBDFRGTPYMT.getStringItemDef("F_DVSNFROM",i,"0").trim(); 
                        strData[i][30]          = RQFBDFRGTPYMT.getStringItemDef("F_ZONEFROM",i,"0").trim(); 
                        }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            return strData;
        }
    public String[][] getOtsgChrgBrkup(String si_Sttn, String si_InvcId, String si_FNOTId, String si_ChrgType, String si_IWOWFlag)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getOtsgChrgBrkup....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            logger.info("si_InvcId  #"+si_InvcId+"#");
            logger.info("si_FNOTId  #"+si_FNOTId+"#");
            logger.info("si_ChrgType  #"+si_ChrgType+"#");
            logger.info("si_IWOWFlag  #"+si_IWOWFlag+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "CHRG_BRKUP");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_Sttn);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     2,      si_InvcId);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     3,      si_FNOTId);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     4,      si_ChrgType);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     5,      si_IWOWFlag);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
            logger.info("Start reading data for Breakup of Outstanding Payments");
            
            strData = new String[start1][3];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        
                        strData[i][0]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                    }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            return strData;
        }

    public String[] getChrgDesc(String si_ChrgId)
    {
            String strData[] = null;   // Variable to be returned as output of function

            logger.info("Entering getChrgDesc....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_ChrgId  #"+si_ChrgId+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "CHRG_DESC");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_ChrgId);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
            
            strData = new String[25];
            for(int i=0; i<=24; i++)
            {
                try
                {       
                        strData[i]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                }
                catch(Exception d)
                {
                  logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());
                  strCallStts="F";
                  strCallMesg="Data Problem";
                  return null;
                 }
        
            } // End of for Loop
            return strData;
        }
        
    public String[][] getCustWgmt(String si_TrxnId)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            System.out.println("Entering getCustWgmt....");

            logger.info("Entering getCustWgmt..Function called with inputs :");
            System.out.println("si_CustID  #"+si_CustID+"#");
            System.out.println("si_CustCode  #"+si_CustCode+"#");
            System.out.println("si_TrxnId  #"+si_TrxnId+"#");
            
            FOISTuxedo RQCUSTWISEWGMT  = null;

            try
            {
                    System.out.println("Calling Tuxedo");
                    RQCUSTWISEWGMT = new FOISTuxedo();
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
                    RQCUSTWISEWGMT.tuxInit("RQCUSTWISEWGMT");
                    RQCUSTWISEWGMT.setString("F_USERID",       0,      si_CustID);
                    RQCUSTWISEWGMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQCUSTWISEWGMT.setString("F_TEMPFLAG",         0,      "S");
                    RQCUSTWISEWGMT.setString("F_DATEFROM",     0,      "01/12/2020");
                    RQCUSTWISEWGMT.setString("F_DATETO",     0,        "01/12/2021");
                    RQCUSTWISEWGMT.setString("F_CUSTCODE",     0,      "ACB");
                    RQCUSTWISEWGMT.setString("F_STTN",     0,      "");
                //RQCUSTWISEWGMT.setString("F_STTN",     1,      si_TrxnId);
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
                    System.out.println("Calling Tuxedo Service SRVCNAME ...");
                    RQCUSTWISEWGMT.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Service Call Problem";
                    return null;
            }
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQCUSTWISEWGMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQCUSTWISEWGMT.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }

            System.out.println("start1 : " + start1);

            logger.info("start1 : " + start1);
            logger.info("Start reading data for Payment Transaction History");
            
            strData = new String[start1][7];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {                        
                        strData[i][0]           = RQCUSTWISEWGMT.getStringItemDef("F_RAKEID",i,"0").trim();
                        strData[i][1]           = RQCUSTWISEWGMT.getStringItemDef("F_STTNFROM",i,"0").trim();
                        strData[i][2]           = RQCUSTWISEWGMT.getStringItemDef("F_STTNTO",i,"0").trim();
                        strData[i][3]           = RQCUSTWISEWGMT.getStringItemDef("F_STTN",i,"0").trim();
                        strData[i][4]           = RQCUSTWISEWGMT.getStringItemDef("F_SUBJID",i,"0").trim();
                        strData[i][5]           = RQCUSTWISEWGMT.getStringItemDef("F_TIME",i,"0").trim();
                        strData[i][6]           = RQCUSTWISEWGMT.getStringItemDef("F_ACDLCONT",i,"0").trim();
                        //strData[i][7]           = RQCUSTWISEWGMT.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        //strData[i][8]           = RQCUSTWISEWGMT.getStringItemDef("F_ORDRBY9",i,"0").trim();
//                        strData[i][9]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY10",i,"0").trim();
//                        strData[i][10]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE",i,"0").trim();
//                        strData[i][11]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE1",i,"0").trim();
//                        strData[i][12]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE2",i,"0").trim();
//                        strData[i][13]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE3",i,"0").trim();
//                        strData[i][14]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB",i,"0").trim();
//                        strData[i][15]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
//                        strData[i][16]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
//                        strData[i][17]           = RQFBDFRGTPYMT.getStringItemDef("F_DATE",i,"0").trim();
                    }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQCUSTWISEWGMT and filling data into array for Payment History" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            return strData;
        }
    public String[][] getCustWgmtDtls(String si_RakeID, String si_WgmtID, String si_Sttn)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            System.out.println("Entering getCustWgmtDtls....");
            logger.info("Function called with inputs :");
            System.out.println("si_CustID  #"+si_CustID+"#");
            System.out.println("si_CustCode  #"+si_CustCode+"#");
            System.out.println("si_RakeID  #"+si_RakeID+"#");
            System.out.println("si_WgmtID  #"+si_WgmtID+"#");
            System.out.println("si_Sttn  #"+si_Sttn+"#");
            
            logger.info("si_RakeID  #"+si_RakeID+"#");
            logger.info("si_WgmtID  #"+si_WgmtID+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            
            FOISTuxedo RQCUSTWISEWGMT  = null;

            try
            {
                    System.out.println("Calling Tuxedo");
                    RQCUSTWISEWGMT = new FOISTuxedo();
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
                    RQCUSTWISEWGMT.tuxInit("RQCUSTWISEWGMT");
                    RQCUSTWISEWGMT.setString("F_USERID",       0,      si_CustID);
                    RQCUSTWISEWGMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQCUSTWISEWGMT.setString("F_TEMPFLAG",         0,      "D");
                    RQCUSTWISEWGMT.setString("F_DATEFROM",     0,      "01/12/2020");
                    RQCUSTWISEWGMT.setString("F_DATETO",     0,        "01/12/2021");
                    RQCUSTWISEWGMT.setString("F_CUSTCODE",     0,      "ACB");
                    RQCUSTWISEWGMT.setString("F_STTN",     0,      si_Sttn);
                RQCUSTWISEWGMT.setString("F_WAVRBLCKID",     0,      si_WgmtID);
                RQCUSTWISEWGMT.setString("F_RAKEID",     0,      si_RakeID);
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
                    System.out.println("Calling Tuxedo Service SRVCNAME ...");
                    RQCUSTWISEWGMT.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Service Call Problem";
                    return null;
            }
            String erorCode1                        = null;
            try
            {
                    erorCode1            = RQCUSTWISEWGMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQCUSTWISEWGMT.getStringItemDef("F_ROWCONT3",0,"0");
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
            logger.info("Start reading data for Payment Transaction History");
            
            strData = new String[start1][42];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {                        
                        strData[i][0]           = RQCUSTWISEWGMT.getStringItemDef("F_OWNGRLY",i,"0").trim();
                        strData[i][1]           = RQCUSTWISEWGMT.getStringItemDef("F_WGONTYPE",i,"0").trim();
                        strData[i][2]           = RQCUSTWISEWGMT.getStringItemDef("F_WGONNUMB",i,"0").trim();
                        strData[i][3]           = RQCUSTWISEWGMT.getStringItemDef("F_WGONID",i,"0").trim();
                        strData[i][4]           = RQCUSTWISEWGMT.getStringItemDef("F_NEWLEFLAG",i,"0").trim();
                        strData[i][5]           = RQCUSTWISEWGMT.getStringItemDef("F_INVCID",i,"0").trim();
                        strData[i][6]           = RQCUSTWISEWGMT.getStringItemDef("F_INVCSTTNFROM",i,"0").trim();
                        strData[i][7]           = RQCUSTWISEWGMT.getStringItemDef("F_INVCSTTNTO",i,"0").trim();
                        strData[i][8]           = RQCUSTWISEWGMT.getStringItemDef("F_RAKECMDT",i,"0").trim();
                        strData[i][9]           = RQCUSTWISEWGMT.getStringItemDef("F_RAKECNSR",i,"0").trim();
                        strData[i][10]           = RQCUSTWISEWGMT.getStringItemDef("F_RAKECNSG",i,"0").trim();
                        strData[i][11]           = RQCUSTWISEWGMT.getStringItemDef("F_RKPMFLAG",i,"0").trim();
                        strData[i][12]           = RQCUSTWISEWGMT.getStringItemDef("F_BVDCFLAG",i,"0").trim();
                        strData[i][13]           = RQCUSTWISEWGMT.getStringItemDef("F_RAKESTTS",i,"0").trim();
                        strData[i][14]           = RQCUSTWISEWGMT.getStringItemDef("F_CRNTLOADID",i,"0").trim();
                        strData[i][15]           = RQCUSTWISEWGMT.getStringItemDef("F_PLCTRESN",i,"0").trim();
                        strData[i][16]           = RQCUSTWISEWGMT.getStringItemDef("F_RSTNFLAG",i,"0").trim();
                        strData[i][17]           = RQCUSTWISEWGMT.getStringItemDef("F_STTSCHNGTIME",i,"0").trim();
                        strData[i][18]           = RQCUSTWISEWGMT.getStringItemDef("F_STTSTIME",i,"0").trim();
                        strData[i][19]           = RQCUSTWISEWGMT.getStringItemDef("F_STRTTIME",i,"0").trim();
                        strData[i][20]           = RQCUSTWISEWGMT.getStringItemDef("F_RFENDTIME",i,"0").trim();
                        strData[i][21]           = RQCUSTWISEWGMT.getStringItemDef("F_TARE",i,"0").trim();
                        strData[i][22]           = RQCUSTWISEWGMT.getStringItemDef("F_GROSWGHT",i,"0").trim();
                        strData[i][23]           = RQCUSTWISEWGMT.getStringItemDef("F_TOTLWGHT",i,"0").trim();
                        strData[i][24]           = RQCUSTWISEWGMT.getStringItemDef("F_UNTS",i,"0").trim();
                        strData[i][25]           = RQCUSTWISEWGMT.getStringItemDef("F_INCMLOCONUMB",i,"0").trim();
                    
                        strData[i][26]           = RQCUSTWISEWGMT.getStringItemDef("F_CHBLWGHT",i,"0").trim();
                        strData[i][27]           = RQCUSTWISEWGMT.getStringItemDef("F_CHRDWGHT",i,"0").trim();
                        strData[i][28]           = RQCUSTWISEWGMT.getStringItemDef("F_TNGE",i,"0").trim();
                        
                        strData[i][29]           = RQCUSTWISEWGMT.getStringItemDef("F_FLAG",i,"0").trim();
                        strData[i][30]           = RQCUSTWISEWGMT.getStringItemDef("F_TRXNTIME",i,"0").trim();
                        strData[i][31]           = RQCUSTWISEWGMT.getStringItemDef("F_INVCNUMB",i,"0").trim();
                        
                        strData[i][32]           = RQCUSTWISEWGMT.getStringItemDef("F_INVCDATE",i,"0").trim();
                        strData[i][33]           = RQCUSTWISEWGMT.getStringItemDef("F_RRNUMB",i,"0").trim();
                        strData[i][34]           = RQCUSTWISEWGMT.getStringItemDef("F_RRDATE",i,"0").trim();
                        
                        strData[i][35]           = RQCUSTWISEWGMT.getStringItemDef("F_LDNGWGHT",i,"0").trim();
                        strData[i][36]           = RQCUSTWISEWGMT.getStringItemDef("F_PMBLWGHT",i,"0").trim();
                        strData[i][37]           = RQCUSTWISEWGMT.getStringItemDef("F_OVERWGHTCHRDTOTL",i,"0").trim();
    
                        strData[i][38]           = RQCUSTWISEWGMT.getStringItemDef("F_OVERWGHTCHRDNRML",i,"0").trim();
                        strData[i][39]           = RQCUSTWISEWGMT.getStringItemDef("F_POLCLSS1",i,"0").trim();
                        strData[i][40]           = RQCUSTWISEWGMT.getStringItemDef("F_POLCLSS2",i,"0").trim();
                        strData[i][41]           = RQCUSTWISEWGMT.getStringItemDef("F_TRANORDGTIME",i,"0").trim();
                    }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQCUSTWISEWGMT and filling data into array for Payment History" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            logger.info("end : ");
            return strData;
        }            
    public String[][] getPymtHist(String si_TrxnId)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getPymtHist....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_TrxnId  #"+si_TrxnId+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "PYMT_TRXN_HIST");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_TrxnId);
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
                    RQFBDFRGTPYMT.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Service Call Problem";
                    return null;
            }
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
            logger.info("Start reading data for Payment Transaction History");
            
            strData = new String[start1][18];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {                        
                        strData[i][0]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY4",i,"0").trim();
                        strData[i][4]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][11]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][12]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                        strData[i][13]           = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE3",i,"0").trim();
                        strData[i][14]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB",i,"0").trim();
                        strData[i][15]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB1",i,"0").trim();
                        strData[i][16]           = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB2",i,"0").trim();
                        strData[i][17]           = RQFBDFRGTPYMT.getStringItemDef("F_DATE",i,"0").trim();
                    }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array for Payment History" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            return strData;
        }
 
    public String[][] getRfndDtls(String si_TrxnId)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getRfndDtls....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_TrxnId  #"+si_TrxnId+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "PYMT_RFND");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_TrxnId);
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
                    RQFBDFRGTPYMT.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Service Call Problem";
                    return null;
            }
            String erorCode1                        = null;
            try
            {
                    erorCode1                       = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
            logger.info("Start reading data for Refund Details");
            
            strData = new String[start1][13];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {                        
                        strData[i][0]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY4",i,"0").trim();
                        strData[i][4]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY5",i,"0").trim();
                        strData[i][5]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY6",i,"0").trim();
                        strData[i][6]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY7",i,"0").trim();
                        strData[i][7]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY8",i,"0").trim();
                        strData[i][8]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY9",i,"0").trim();
                        strData[i][9]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY10",i,"0").trim();
                        strData[i][10]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE",i,"0").trim();
                        strData[i][11]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE1",i,"0").trim();
                        strData[i][12]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE2",i,"0").trim();
                    }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array for Payment History" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
            return strData;
        }
      
    public String[][] getChrgBreakup(String si_Sttn,String si_Date,String si_ChrgType,String si_IWOWFlag,String si_InvcId,String si_FNOTId, String si_CustDmrgId, String si_TrxnId, String si_Amnt,String si_RlyGstin,String si_CnsrGstin,String si_CnsgGstin, String si_HndgAgnt)
        {                                                                                                                                                                                           
            String strData[][] = null;   // Variable to be returned as output of function                                                                                                           
            String strTotal=null;                                                                                                                                                                   
            String arrData[] = null;                                                                                                                                                                
            logger.info("Entering getChrgBreakup....");                                                                                                                                             
            logger.info("Function called with inputs :");                                                                                                                                           
            logger.info("si_Invcid  #"+si_InvcId+"#");                                                                                                                                                                                                                                                                                       
            logger.info("si_FNOTid  #"+si_FNOTId+"#");                                                                                                                                              
            logger.info("si_CustDmrgId  #"+si_CustDmrgId+"#");                                                                                                                                              
            logger.info("si_TrxnId  #"+si_TrxnId+"#");     
            logger.info("si_InvcDate  #"+si_Date+"#");                                                                                                                                              
            logger.info("si_Sttn  #"+si_Sttn+"#");                                                                                                                                                  
            logger.info("si_Amnt  #"+si_Amnt+"#");                                                                                                                                                  
            logger.info("si_InvcDate  #"+si_Date+"#");                                                                                                                                              
            logger.info("si_ChrgType  #"+si_ChrgType+"#");                                                                                                                                          
            logger.info("si_IWOWFlag  #"+si_IWOWFlag+"#");                                                                                                                                          
            logger.info("si_RlyGstin  #"+si_RlyGstin+"#");                                                                                                                                          
            logger.info("si_CnsrGstin  #"+si_CnsrGstin+"#");                                                                                                                                        
            logger.info("si_CnsgGstin  #"+si_CnsgGstin+"#");                                                                                                                                         
            logger.info("si_HndgAgnt  #"+si_HndgAgnt+"#"); 
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
            Date date = new Date();  
            String si_CrntDate=formatter.format(date);
            FOISTuxedo TQGST  = null;                                                                                                                                                               
                                                                                                                                                                                                    
            try                                                                                                                                                                                     
            {                                                                                                                                                                                       
                    logger.info("Calling Tuxedo");                                                                                                                                                  
                    TQGST = new FOISTuxedo();                                                                                                                                                       
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
                /*
                 * F_OPTN - Y 
F_PRTYWISE - RLY GSTIN
F_CNSRWISE - HANDLING AGENT GSTIN
F_CNSGWISE - NULL VALUE

                 */

                    TQGST.tuxInit("TQGST");                                                                                                                                                         
                    TQGST.setString("F_STTN",         0,    si_Sttn);                                                                                                                           
                    TQGST.setString("F_DATE",         0,    si_CrntDate);  
                
                    if(!si_HndgAgnt.equals("")) 
                    {          
                        logger.info("Setting Handling Agent Parameters..");
                        TQGST.setString("F_OPTN",         0,    "Y");                                                                                                                       
                        TQGST.setString("F_PRTYWISE",     0,    si_RlyGstin);                                                                                                                     
                        TQGST.setString("F_CNSRWISE",     0,    si_CnsrGstin);                                                                                                                    
                        TQGST.setString("F_CNSGWISE",     0,    "");     
                    }
                    else 
                    {   
                         logger.info("Setting Primary Customer Parameters..");
                        TQGST.setString("F_PRTYWISE",     0,    si_RlyGstin);                                                                                                                     
                        TQGST.setString("F_CNSRWISE",     0,    si_CnsrGstin);                                                                                                                    
                        TQGST.setString("F_CNSGWISE",     0,    si_CnsgGstin);   
                     }
                        if(si_ChrgType.equals("F")) 
                        {
                             logger.info("Case: Freight Charges");                                                                                                                      
                             
                             TQGST.setString("F_TYPE",         0,    "I");                                                                                                                             
                             TQGST.setString("F_CSGTID",       0,    si_InvcId);                                                                                                                                
                             TQGST.setString("F_CHRGTYPE",     0,    "F");                                                                                                                            
                        }                                                                                                                                                                                                                           
                        if(si_ChrgType.equals("W")) 
                        {
                             logger.info("Case: Wharfage Charges");                                                                                                                      
                             
                             TQGST.setString("F_TYPE",         0,    "I");                                                                                                                             
                             TQGST.setString("F_CSGTID",       0,    si_InvcId);                                                                                                                                
                             TQGST.setString("F_CHRGTYPE",     0,    "W");                                                                                                                            
                        }
                       if(si_ChrgType.equals("L") && si_FNOTId.equals("")) 
                       {
                            logger.info("Case: Local Charge Against Customer");                                                                                                                      
                            
                            TQGST.setString("F_TYPE",         0,    "T");                                                                                                                             
                            TQGST.setString("F_CSGTID",       0,    si_TrxnId);                                                                                                                                
                            TQGST.setString("F_CHRGTYPE",     0,    "L");                                                                                                                            
                       }                                                                                                                         
                       if(si_ChrgType.equals("L") && (!si_FNOTId.equals(""))) 
                       {
                            logger.info("Case: Local Charge Against FNote");                                                                                                                      
                            
                            if(si_IWOWFlag.equals("O"))  /*Outward*/
                            {
                                TQGST.setString("F_TYPE",         0,    "F");                                                                                                                             
                                TQGST.setString("F_CSGTID",       0,    si_FNOTId);                                                                                                                                
                            }
                            if(si_IWOWFlag.equals("I"))  /*Inward*/
                            {
                                TQGST.setString("F_TYPE",         0,    "I");                                                                                                                           
                                TQGST.setString("F_CSGTID",       0,    si_InvcId);                                                                                                                                
                            }
                            
                            
                            TQGST.setString("F_CHRGTYPE",     0,    "L");                                                                                                                            
                       }                                                                                                                      
                       if(si_ChrgType.equals("D") && si_FNOTId.equals("")) 
                       {
                             logger.info("Case: Demurrage Charge Against Customer");                                                                                                                      
                             
                             TQGST.setString("F_TYPE",         0,    "C");                                                                                                                             
                             TQGST.setString("F_CSGTID",       0,    si_CustDmrgId);                                                                                                                                
                             TQGST.setString("F_CHRGTYPE",     0,    "D");                                                                                                                            
                       }                                                                                                                        
                       if(si_ChrgType.equals("D") && (!si_FNOTId.equals(""))) 
                       {  
                            if(si_InvcId.equals("")) 
                            {
                                logger.info("Invoice Id is null");                                                                                                                  
                             
                                 TQGST.setString("F_TYPE",         0,    "F");                                                                                                                             
                                 TQGST.setString("F_CSGTID",       0,    si_FNOTId);                                                                                                                                
                                 TQGST.setString("F_CHRGTYPE",     0,    "D");              
                            }
                            else 
                            {
                                logger.info("Invoice Id is not null");                                                                                                                  
                             
                                 TQGST.setString("F_TYPE",         0,    "I");                                                                                                                             
                                 TQGST.setString("F_CSGTID",       0,    si_InvcId);                                                                                                                                
                                 TQGST.setString("F_CHRGTYPE",     0,    "D");              
                            }
                       }
                    TQGST.setString("F_FLAG",         0,    si_IWOWFlag);                                                                                                                     
                    TQGST.setString("F_AMNT",         0,    si_Amnt);                                                                                                                   
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
                    TQGST.call("N");                                                                                                                                                                
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
                    //*************************************************************************************/                                                                                        
                    /* Checking For Any Error from Service      */                                                                                                                                  
            String erorCode1 = null;                                                                                                                                                                
            try                                                                                                                                                                                     
            {                                                                                                                                                                                       
                    erorCode1  = TQGST.getStringItemDef("F_ERORNO",0,"0");                                                                                                                          
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
           arrData=new String[11];                                                                                                                                                      
           strData = new String[10][3];                                                                                                                                                             
                                                                                                                                                                                                    
                try                                                                                                                                                                                 
                {                                                                                                                                                                                   
                        arrData[0]       = TQGST.getStringItemDef("F_AMNTDUE",0,"0").trim();   /*IGST */                                                                                            
                        arrData[1]       = TQGST.getStringItemDef("F_GOVTAMNT",0,"0").trim();  /*CGST */                                                                                            
                        arrData[2]       = TQGST.getStringItemDef("F_ACDL", 0,"0").trim();      /*SGST */                                                                                       
                        arrData[3]       = TQGST.getStringItemDef("F_ACDLCONT",0,"0").trim();  /*UGST */                                                                                            
                        arrData[4]       = TQGST.getStringItemDef("F_KMRUN",0,"0").trim();     /*totalGST*/                                                                                         
                        arrData[5]       = TQGST.getStringItemDef("F_EXSSAMNT",0,"0").trim();   /*gstrmrk*/                                                                                         
                        arrData[6]       = TQGST.getStringItemDef("F_AJSTFLAG",0,"0").trim();   /*wrfadjstflag*/                                                                                    
                        arrData[7]       = TQGST.getStringItemDef("F_ARFRFND",0,"0").trim();     /*wrfadjstamnt*/                                                                                   
                        arrData[8]       = TQGST.getStringItemDef("F_AMNTRFND",0,"0").trim();    /*wrfadjsttax*/                                                                                    
                        arrData[9]       = TQGST.getStringItemDef("F_ARFAMNT",0,"0").trim();    /*totwrfadjstamnt*/                                                                                 
                        arrData[10]      = TQGST.getStringItemDef("F_AMNTOTSG",0,"0").trim(); /*total amount without adjustment*/                                                                   
                        logger.info("si_Amnt is: "+si_Amnt);                                                                                                                                        
                        strTotal=String.valueOf(Integer.parseInt(arrData[4]) + Integer.parseInt(si_Amnt));                                                                                          
                                                                                                                                                                                                    
                        logger.info("strTotal is: "+strTotal+"adjstflag"+arrData[6]);                                                                                                               
                        if(arrData[6].equals("Y"))                                                                                                                                                  
                         {                                                                                                                                                                          
                           strData = new String[11][3];                                                                                                                                             
                         strTotal=String.valueOf(Integer.parseInt(strTotal)-(Integer.parseInt(arrData[7])+Integer.parseInt(arrData[8]) ));                                                          
                         }                                                                                                                                                                          
                    else {                                                                                                                                                                          
                            strData = new String[9][3];                                                                                                                                             
                        }                                                                                                                                                                           
                            logger.info("strTotal is: "+strTotal);                                                                                                                                  
                                                                                                                                                                                                    
                                int i=0;                                                                                                                                                            
                               strData[i][0]="Amt";                                                                                                                                                 
                               strData[i][1]="Amount";                                                                                                                                              
                               strData[i][2]=si_Amnt;                                                                                                                                               
                                i=i+1;                                                                                                                                                              
                                 strData[i][0]="IGST";                                                                                                                                              
                                 strData[i][1]="IGST";                                                                                                                                              
                                 strData[i][2]=arrData[0];                                                                                                                                          
                                 i =i+1;                                                                                                                                                            
                                 strData[i][0]="CGST";                                                                                                                                              
                                 strData[i][1]="CGST";                                                                                                                                              
                                 strData[i][2]=arrData[1];                                                                                                                                          
                                 i =i+1;                                                                                                                                                            
                                 strData[i][0]="SGST";                                                                                                                                              
                                 strData[i][1]="SGST";                                                                                                                                              
                                 strData[i][2]=arrData[2];                                                                                                                                          
                                 i =i+1;                                                                                                                                                            
                                 strData[i][0]="UGST";                                                                                                                                              
                                 strData[i][1]="UGST";                                                                                                                                              
                                 strData[i][2]=arrData[3];                                                                                                                                          
                                 i =i+1;                                                                                                                                                            
                                 strData[i][0]="TotlGST";                                                                                                                                           
                                 strData[i][1]="Total GST";                                                                                                                                         
                                 strData[i][2]=arrData[4];                                                                                                                                          
                                 i =i+1;                                                                                                                                                            
                                 strData[i][0]="GSTRMRK";                                                                                                                                           
                                 strData[i][1]="GST Remark";                                                                                                                                        
                                 strData[i][2]=arrData[5];                                                                                                                                          
                                i =i+1;                                                                                                                                                             
                                 strData[i][0]="Total";                                                                                                                                             
                                 strData[i][1]="Total Amount";                                                                                                                                      
                                 strData[i][2]=arrData[10];                                                                                                                                         
                               

				logger.info("AdjstFlag"+arrData[6]);                                                                                                                                
                                if(arrData[6].equals("Y"))                                                                                                                                          
                                {                                                                                                                                                                   
                                        i=i+1;                                                                                                                                                      
                                        strData[i][0]="WRF";                                                                                                                                        
                                        strData[i][1]="Wagon Registration Fee (WRF) Adjustment Amount";                                                                                                                     
                                        strData[i][2]=arrData[7];                                                                                                                                   
                                        i=i+1;                                                                                                                                                      
                                        strData[i][0]="WRFTAX";                                                                                                                                     
                                        strData[i][1]="WRF GST Adjustment Amount";                                                                                                                 
                                        strData[i][2]=arrData[8];                                                                                                                                   
                                      /*  i=i+1;                                                                                                                                                      
                                        strData[i][0]="Total Weighment Registration";                                                                                                               
                                        strData[i][1]="Total WRF Adjustment Amount";                                                                                                           
                                        strData[i][2]=arrData[9];  */                                                                                                                                 
                                    } 
 				i=i+1;                                                                                                                                                              
                                strData[i][0]="GTotl";                                                                                                                                              
                                strData[i][1]="Net Payable Amount";                                                                                                                                        
                                strData[i][2]=strTotal;                                                                                                                                                                  
                                                                                                                                                                                                    
                   }                                                                                                                                                                                
                    catch(Exception d)                                                                                                                                                           
                    {                                                                                                                                                                               
                        logger.fatal("Problem in Calling Service TQGST and filling data into array" + d.toString());                                                                                
                        strCallStts="F";                                                                                                                                                            
                        strCallMesg="Data Problem";                                                                                                                                                 
                        return null;                                                                                                                                                                
                    }                                                                                                                                                                               
                                                                                                                                                                                                    
                                                                                                                                                                                                    
            return strData;                                                                                                                                                                         
        }                                                                                                                                                                                           
   
    public String[][] getGSTList( String si_RptgSttn,String si_SttnFrom,String si_SttnTo,String si_Cnsr,String si_Cnsg,String si_TrxnFlag,String si_TrxnID,String si_HndlFlag)
       {                                                                                                                                                                 
               String strData[][] = null;   // Variable to be returned as output of function                                                                                                           
                                                                                                                                                                       
               logger.info("Entering getGSTList....");                                                                                                                                             
               logger.info("Function called with inputs :");                                                                                                                                           
               logger.info("si_RptgSttn  #"+si_RptgSttn+"#");                                                                                                                                              
               logger.info("si_SttnFrom  #"+si_SttnFrom+"#");                                                                                                                                              
               logger.info("si_SttnTo  #"+si_SttnTo+"#");                                                                                                                                                  
               logger.info("si_Cnsr  #"+si_Cnsr+"#");                                                                                                                                                  
               logger.info("si_Cnsg  #"+si_Cnsg+"#");                                                                                                                                              
               logger.info("si_TrxnFlag  #"+si_TrxnFlag+"#");                                                                                                                                          
               logger.info("si_TrxnID  #"+si_TrxnID+"#");                                                                                                                                          
                                                                                                                                     
               FOISTuxedo TQCUSTGST  = null;                                                                                                                                                               
                                                                                                                                                                                                       
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       logger.info("Calling Tuxedo");                                                                                                                                                  
                       TQCUSTGST = new FOISTuxedo();                                                                                                                                                       
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
                       TQCUSTGST.tuxInit("TQCUSTGSTDTLS");                                                                                                                                                         
                       TQCUSTGST.setString("F_RPTGSTTN",         0,    si_RptgSttn);                                                                                                                           
                       TQCUSTGST.setString("F_STTNFROM",         0,    si_SttnFrom);                                                                                                                           
                       TQCUSTGST.setString("F_STTNTO",           0,    si_SttnTo);                                                                                                                         
                       TQCUSTGST.setString("F_CNSR",             0,    si_Cnsr);                                                                                                                               
                       TQCUSTGST.setString("F_CNSG",                 0,    si_Cnsg);                                                                                                                      
                       TQCUSTGST.setString("F_TRXNTYPE",             0,    si_TrxnFlag);                 /*F in case of FNOTID*/                                                                                                    
                       TQCUSTGST.setString("F_NEXTTRANID",       0,    si_TrxnID);                   /*FNOTID*/ 
                       TQCUSTGST.setString("F_FLAG",                0,    si_HndlFlag);      
                }                                                                                                                                                                                   
                    catch(Exception e)                                                                                                                                                                 
                    {                     logger.fatal("Unable to write to buffer : " + e.toString());                                                                                                 
                       strCallStts="F";                                                                                                                                                                
                       strCallMesg="Problem in Writing Buffer";                                                                                                                                        
                       return null;                                                                                                                                                                    
               }                                                                                                                                                                                       
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       logger.info("Calling Tuxedo Service SRVCNAME ...");                                                                                                                             
                       TQCUSTGST.call("N");                                                                                                                                                                
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
                       //*************************************************************************************/                                                                                        
                       /* Checking For Any Error from Service      */                                                                                                                                  
               String erorCode1 = null;                                                                                                                                                                
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       erorCode1  = TQCUSTGST.getStringItemDef("F_ERORNO",0,"0");                                                                                                                          
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
                       startRowCount1 = TQCUSTGST.getStringItemDef("F_HELPCOUNT",0,"0");
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
               logger.info("Start reading data for GSTList");
               
               strData = new String[start1][10];
               for(int i=0; i<=start1-1; i++)
               {                                                                                          
                   try                                                                                                                                                                                 
                   {                                                                                                                                                                                   
                           strData[i][0]       = TQCUSTGST.getStringItemDef("F_TYPE",0,"0").trim();   /*lc_type */                                                                                            
                           logger.info("Type:"+ strData[i][0] );
                           strData[i][1]       = TQCUSTGST.getStringItemDef("F_CUSTCODE",i,"0").trim();  /*lc_custcode */                                                                                            
                           strData[i][2]       = TQCUSTGST.getStringItemDef("F_CUSTNAME",     i,"0").trim();      /*lc_custname */                                                                                       
                           strData[i][3]       = TQCUSTGST.getStringItemDef("F_CUSTADDR",i,"0").trim();  /*lc_custaddr */                                                                                            
                           strData[i][4]       = TQCUSTGST.getStringItemDef("F_NUMB",i,"0").trim();     /*lc_custmoblnumb*/                                                                                         
                           strData[i][5]       = TQCUSTGST.getStringItemDef("F_CNSRADDR",i,"0").trim();   /*lc_custcustemalid*/                                                                                         
                           strData[i][6]       = TQCUSTGST.getStringItemDef("F_DMNDID",i,"0").trim();   /*lc_custGSTIN*/                                                                                    
                           strData[i][7]       = TQCUSTGST.getStringItemDef("F_LOCN",i,"0").trim();     /*lc_custstatnumb*/                                                                                   
                           strData[i][8]       = TQCUSTGST.getStringItemDef("F_COTRNAME",i,"0").trim();    /*lc_custstatname*/                                                                                    
                           strData[i][9]       = TQCUSTGST.getStringItemDef("F_USERDELTFLAG",i,"0").trim();    /*lc_trxnrfrnflag*/                                                                                 
                            logger.info("F_CUSTCODE:"+ strData[i][1] );
                            logger.info("GSTIN:"+ strData[i][6] );
                         }                                                                                                               
                     catch(Exception d)                                                                                                                                                              
                       {                                                                                                                                                                               
                           logger.fatal("Problem in Calling Service TQCUSTGST and filling data into array" + d.toString());                                                                                
                           strCallStts="F";                                                                                                                                                            
                           strCallMesg="Data Problem";                                                                                                                                                 
                           return null;                                                                                                                                                                
                       }                                                                                                                                                                               
              }                                                                                                                                                                                     
               logger.info("strData:"+ strData );                                                                                                                                                                                     
               return strData;                                                                                                                                                                         
           } 
    public String[] getGSTCustList( String si_CustCode)
       {                                                                                                                                                                 
               String strData[] = null;   // Variable to be returned as output of function                                                                                                           
                                                                                                                                                                       
               logger.info("Entering getGSTList....");                                                                                                                                             
               logger.info("Function called with inputs :");                                                                                                                                           
                                                                                                                                                           
               logger.info("si_CustCode  #"+si_CustCode+"#");                                                                                                                                                  
                                                                                    
               FOISTuxedo RQFBDFRGTPYMT  = null;                                                                                                                                                               
                                                                                                                                                                                                       
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       logger.info("Calling Tuxedo");                                                                                                                                                  
                       RQFBDFRGTPYMT = new FOISTuxedo();                                                                                                                                                       
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "GST_CUST_LIST");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);      
                }                                                                                                                                                                                   
                    catch(Exception e)                                                                                                                                                                 
                    {                     logger.fatal("Unable to write to buffer : " + e.toString());                                                                                                 
                       strCallStts="F";                                                                                                                                                                
                       strCallMesg="Problem in Writing Buffer";                                                                                                                                        
                       return null;                                                                                                                                                                    
               }                                                                                                                                                                                       
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       logger.info("Calling Tuxedo Service SRVCNAME ...");                                                                                                                             
                       RQFBDFRGTPYMT.call("N");                                                                                                                                                                
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
                       //*************************************************************************************/                                                                                        
                       /* Checking For Any Error from Service      */                                                                                                                                  
               String erorCode1 = null;                                                                                                                                                                
               try                                                                                                                                                                                     
               {                                                                                                                                                                                       
                       erorCode1  = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");                                                                                                                          
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
                       startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
               logger.info("Start reading data for GSTList");
               
               strData = new String[start1];
               for(int i=0; i<=start1-1; i++)
               {                                                                                          
                   try                                                                                                                                                                                 
                   {                                                                                                                                                                                   
                           strData[i]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();   /*lc_type */                                                                                            
                           logger.info("Type:"+ strData[i] );
                         
                         }                                                                                                               
                     catch(Exception d)                                                                                                                                                              
                       {                                                                                                                                                                               
                           logger.fatal("Problem in Calling Service TQCUSTGST and filling data into array" + d.toString());                                                                                
                           strCallStts="F";                                                                                                                                                            
                           strCallMesg="Data Problem";                                                                                                                                                 
                           return null;                                                                                                                                                                
                       }                                                                                                                                                                               
              }                                                                                                                                                                                     
               logger.info("strData:"+ strData );                                                                                                                                                                                     
               return strData;                                                                                                                                                                         
           } 
    public String[] getDmrgBrkUp(String si_DmrgType, String si_Sttn, String si_InvcId, String si_FnotId, String si_CustDmrgId, String si_IWOWFlag)
        {
                String strData[] = null;   // Variable to be returned as output of function

                logger.info("Entering getDmrgBrkUp....");
                logger.info("Function called with inputs :");
                logger.info("si_CustID  #"+si_CustID+"#");
                logger.info("si_CustCode  #"+si_CustCode+"#");
                logger.info("si_DmrgType  #"+si_DmrgType+"#");
                logger.info("si_Sttn  #"+si_Sttn+"#");
                logger.info("si_InvcId  #"+si_InvcId+"#");
                logger.info("si_FnotId  #"+si_FnotId+"#");
                logger.info("si_CustDmrgId  #"+si_CustDmrgId+"#");
                logger.info("si_IWOWFlag  #"+si_IWOWFlag+"#");
                
                FOISTuxedo RQFBDFRGTPYMT  = null;

                try
                {
                        logger.info("Calling Tuxedo");
                        RQFBDFRGTPYMT = new FOISTuxedo();
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
                        RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                        RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                        RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                        RQFBDFRGTPYMT.setString("F_FLAG",         0,      "ANCL_CHRG_BRKUP");
                        RQFBDFRGTPYMT.setString("F_FLAG",         1,      si_DmrgType);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_Sttn);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_InvcId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     2,      si_CustDmrgId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     3,      si_FnotId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     4,      si_IWOWFlag);
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
                        RQFBDFRGTPYMT.call("N");
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
                        erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                
                strData = new String[3];
                logger.info("Start reading data for Demurrage breakups");
                try
                {
                        strData[0] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",0,"0");
                        strData[1] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",0,"0");
                        strData[2] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",0,"0");
                    
                }
                catch(Exception d)
                {
                        logger.fatal("Problem in Calling Service SRVCNAME and filling Data into array" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                }

                return strData;
            }
    public String[] getWrfgBrkUp(String si_Sttn, String si_InvcId, String si_FnotId, String si_CustDmrgId, String si_IWOWFlag)
        {
                String strData[] = null;   // Variable to be returned as output of function

                logger.info("Entering getWrfgBrkUp....");
                logger.info("Function called with inputs :");
                logger.info("si_CustID  #"+si_CustID+"#");
                logger.info("si_CustCode  #"+si_CustCode+"#");
                logger.info("si_Sttn  #"+si_Sttn+"#");
                logger.info("si_InvcId  #"+si_InvcId+"#");
                logger.info("si_FnotId  #"+si_FnotId+"#");
                logger.info("si_CustDmrgId  #"+si_CustDmrgId+"#");
                logger.info("si_IWOWFlag  #"+si_IWOWFlag+"#");
                
                FOISTuxedo RQFBDFRGTPYMT  = null;

                try
                {
                        logger.info("Calling Tuxedo");
                        RQFBDFRGTPYMT = new FOISTuxedo();
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
                        RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                        RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                        RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                        RQFBDFRGTPYMT.setString("F_FLAG",         0,      "ANCL_CHRG_BRKUP");
                        RQFBDFRGTPYMT.setString("F_FLAG",         1,      "WRFG_CHRG");
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_Sttn);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_InvcId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     2,      si_CustDmrgId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     3,      si_FnotId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     4,      si_IWOWFlag);
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
                        RQFBDFRGTPYMT.call("N");
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
                        erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                
                strData = new String[3];
                logger.info("Start reading data for Demurrage breakups");
                try
                {
                        strData[0] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",0,"0");
                        strData[1] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",0,"0");
                        strData[2] = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",0,"0");
                }
                catch(Exception d)
                {
                        logger.fatal("Problem in Calling Service SRVCNAME and filling Data into array" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                }

                return strData;
            }

    public String[][] getLoclChrgBrkUp(String si_LCType, String si_Sttn, String si_InvcId, String si_FnotId, String si_CustDmrgId, String si_TrxnId)
        {
                String strData[][] = null;   // Variable to be returned as output of function

                logger.info("Entering getLoclChrgBrkUp....");
                logger.info("Function called with inputs :");
                logger.info("si_CustID  #"+si_CustID+"#");
                logger.info("si_CustCode  #"+si_CustCode+"#");
                logger.info("si_LCType  #"+si_LCType+"#");
                logger.info("si_Sttn  #"+si_Sttn+"#");
                logger.info("si_InvcId  #"+si_InvcId+"#");
                logger.info("si_FnotId  #"+si_FnotId+"#");
                logger.info("si_CustDmrgId  #"+si_CustDmrgId+"#");
                logger.info("si_TrxnId  #"+si_TrxnId+"#");
                
                FOISTuxedo RQFBDFRGTPYMT  = null;

                try
                {
                        logger.info("Calling Tuxedo");
                        RQFBDFRGTPYMT = new FOISTuxedo();
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
                        RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                        RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                        RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                        RQFBDFRGTPYMT.setString("F_FLAG",         0,      "ANCL_CHRG_BRKUP");
                        RQFBDFRGTPYMT.setString("F_FLAG",         1,      si_LCType);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_Sttn);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_InvcId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     2,      si_CustDmrgId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     3,      si_FnotId);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     4,      si_CustCode);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     5,      si_TrxnId);
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
                        RQFBDFRGTPYMT.call("N");
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
                        erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                        startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
                logger.info("Start reading data for Local Charges");
                
                strData = new String[start1][3];
                for(int i=0; i<=start1-1; i++)
                {                                                                                          
                    try                                                                                                                                                                                 
                    {                                                                                                                                                                                   
                            strData[i][0]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0");
                            strData[i][1]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0");
                            strData[i][2]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0");
                    }                                                                                                               
                      catch(Exception d)                                                                                                                                                              
                        {                                                                                                                                                                               
                            logger.fatal("Problem in Calling Service TQCUSTGST and filling data into array" + d.toString());                                                                                
                            strCallStts="F";                                                                                                                                                            
                            strCallMesg="Data Problem";                                                                                                                                                 
                            return null;                                                                                                                                                                
                        }                                                                                                                                                                               
                }
                logger.info("strData:"+ strData );                                                                                                                                                                                     
                return strData;                                                                                                                                                                         
            }
    public String[] getAnclChrgGSTIN(String si_Sttn)
        {
                String strData[] = null;   // Variable to be returned as output of function

                logger.info("Entering getAnclChrgGSTIN....");
                logger.info("Function called with inputs :");
                logger.info("si_CustID  #"+si_CustID+"#");
                logger.info("si_CustCode  #"+si_CustCode+"#");
                logger.info("si_Sttn  #"+si_Sttn+"#");
                
                FOISTuxedo RQFBDFRGTPYMT  = null;

                try
                {
                        logger.info("Calling Tuxedo");
                        RQFBDFRGTPYMT = new FOISTuxedo();
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
                        RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                        RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                        RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                        RQFBDFRGTPYMT.setString("F_FLAG",         0,      "GST_LIST_ANCL_CHRG");
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_Sttn);
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
                        RQFBDFRGTPYMT.call("N");
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
                        erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                String strRlyGSTIN ="";

                try
                {
                        startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
                        strRlyGSTIN = RQFBDFRGTPYMT.getStringItemDef("F_UNTS",0,"0");
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
                logger.info("Start reading data for Customer GSTIN");
                
                strData = new String[start1+1];
                strData[0]=strRlyGSTIN;
                for(int i=0; i<=start1-1; i++)
                {                                                                                          
                    try                                                                                                                                                                                 
                    {                                                                                                                                                                                   
                            strData[i+1]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0");
                    }                                                                                                               
                    catch(Exception d)                                                                                                                                                              
                    {                                                                                                                                                                               
                            logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());                                                                                
                            strCallStts="F";                                                                                                                                                            
                            strCallMesg="Data Problem";                                                                                                                                                 
                            return null;                                                                                                                                                                
                    }                                                                                                                                                                               
                }
                logger.info("strData:"+ strData );                                                                                                                                                                                     
                return strData;                                                                                                                                                                         
            }

    public String[] getHndgAgntGSTIN(String si_Sttn, String si_HndgAgnt)
        {
                String strData[] = null;   // Variable to be returned as output of function

                logger.info("Entering getHndgAgntGSTIN....");
                logger.info("Function called with inputs :");
                logger.info("si_CustID  #"+si_CustID+"#");
                logger.info("si_HndgAgnt  #"+si_HndgAgnt+"#");
                logger.info("si_Sttn  #"+si_Sttn+"#");
                
                FOISTuxedo RQFBDFRGTPYMT  = null;

                try
                {
                        logger.info("Calling Tuxedo");
                        RQFBDFRGTPYMT = new FOISTuxedo();
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
                        RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                        RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                        RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                        RQFBDFRGTPYMT.setString("F_FLAG",         0,      "GST_LIST_HNDL_AGNT");
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_HndgAgnt);
                        RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_Sttn);
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
                        RQFBDFRGTPYMT.call("N");
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
                        erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                String strRlyGSTIN ="";

                try
                {
                        startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
                        strRlyGSTIN = RQFBDFRGTPYMT.getStringItemDef("F_UNTS",0,"0");
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
                logger.info("Start reading data for Customer GSTIN");
                
                strData = new String[start1+1];
                strData[0]=strRlyGSTIN;
                for(int i=0; i<=start1-1; i++)
                {                                                                                          
                    try                                                                                                                                                                                 
                    {                                                                                                                                                                                   
                            strData[i+1]       = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0");
			    logger.info("Received Handling Agent GST:"+strData[i+1]);
                    }                                                                                                               
                    catch(Exception d)                                                                                                                                                              
                    {                                                                                                                                                                               
                            logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());                                                                                
                            strCallStts="F";                                                                                                                                                            
                            strCallMesg="Data Problem";                                                                                                                                                 
                            return null;                                                                                                                                                                
                    }                                                                                                                                                                               
                }
                return strData;                                                                                                                                                                         
            }

    public String getSecOtsgChrgSmry()
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getOtsgChrgSmry....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "OTSG_SEC_CHRG_SMRY");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    strCallStts="F";
                    strCallMesg="Row Count Problem";
                    return null;
            }
            logger.info("Payments Count:"+startRowCount1);
            return startRowCount1;            
        }
    public String[][] getSecOtsgChrg(String si_Sttn)
    {
            String strData[][] = null;   // Variable to be returned as output of function

            logger.info("Entering getOtsgChrg....");
            logger.info("Function called with inputs :");
            logger.info("si_CustID  #"+si_CustID+"#");
            logger.info("si_CustCode  #"+si_CustCode+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            
            FOISTuxedo RQFBDFRGTPYMT  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQFBDFRGTPYMT = new FOISTuxedo();
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
                    RQFBDFRGTPYMT.tuxInit("RQFBDFRGTPYMT");
                    RQFBDFRGTPYMT.setString("F_USERID",       0,      si_CustID);
                    RQFBDFRGTPYMT.setString("F_CLNTID",       0,      si_ClntId);
                    RQFBDFRGTPYMT.setString("F_FLAG",         0,      "OTSG_CHRG_SEC_CUSTOMER");
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     0,      si_CustCode);
                    RQFBDFRGTPYMT.setString("F_HLDGZONE",     1,      si_Sttn);
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
                    RQFBDFRGTPYMT.call("N");
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
                    erorCode1                               = RQFBDFRGTPYMT.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1 = RQFBDFRGTPYMT.getStringItemDef("F_ROWCONT",0,"0");
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
            logger.info("Start reading data for Outstanding Payments");
            
            strData = new String[start1][33];
            for(int i=0; i<=start1-1; i++)
            {
                try
                {
                        
                        strData[i][0]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY1",i,"0").trim();
                        strData[i][1]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY2",i,"0").trim();
                        strData[i][2]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        strData[i][3]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY4",i,"0").trim();                               
                        strData[i][4]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY5",i,"0").trim();                               
                        strData[i][5]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY6",i,"0").trim();                               
                        strData[i][6]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY7",i,"0").trim();                               
                        strData[i][7]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY8",i,"0").trim();                               
                        strData[i][8]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY9",i,"0").trim();                               
                        strData[i][9]           = RQFBDFRGTPYMT.getStringItemDef("F_ORDRBY10",i,"0").trim();                              
                        strData[i][10]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE",i,"0").trim();                              
                        strData[i][11]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE1",i,"0").trim();                             
                        strData[i][12]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE2",i,"0").trim();                             
                        strData[i][13]          = RQFBDFRGTPYMT.getStringItemDef("F_OWNGZONE3",i,"0").trim();                                  
                        strData[i][14]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB",i,"0").trim();                             
                        strData[i][15]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB1",i,"0").trim();                             
                        strData[i][16]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB2",i,"0").trim();                             
                        strData[i][17]          = RQFBDFRGTPYMT.getStringItemDef("F_RPWBNUMB3",i,"0").trim();                             
                        strData[i][18]          = RQFBDFRGTPYMT.getStringItemDef("F_RAKEID",i,"0").trim();                             
                        strData[i][19]          = RQFBDFRGTPYMT.getStringItemDef("F_RAKETYPE",i,"0").trim();                             
                        strData[i][20]          = RQFBDFRGTPYMT.getStringItemDef("F_UNTS",i,"0").trim();                           
                        strData[i][21]          = RQFBDFRGTPYMT.getStringItemDef("F_FRWH",i,"0").trim(); 
                        strData[i][22]          = RQFBDFRGTPYMT.getStringItemDef("F_CLBLUNTS",i,"0").trim();                           
                        strData[i][23]          = RQFBDFRGTPYMT.getStringItemDef("F_CLBLFRWH",i,"0").trim(); 
                        strData[i][24]          = RQFBDFRGTPYMT.getStringItemDef("F_OPBLUNTS",i,"0").trim(); 
                        strData[i][25]          = RQFBDFRGTPYMT.getStringItemDef("F_DSPTUNTS",i,"0").trim(); 
                        strData[i][26]          = RQFBDFRGTPYMT.getStringItemDef("F_FRMDUNTS",i,"0").trim(); 
                        strData[i][27]          = RQFBDFRGTPYMT.getStringItemDef("F_STTNTO",i,"0").trim(); 
                        strData[i][28]          = RQFBDFRGTPYMT.getStringItemDef("F_STTNFROM",i,"0").trim(); 
                        strData[i][29]          = RQFBDFRGTPYMT.getStringItemDef("F_DVSNFROM",i,"0").trim(); 
                        strData[i][30]          = RQFBDFRGTPYMT.getStringItemDef("F_DVSNTO",i,"0").trim(); 
                        strData[i][31]          = RQFBDFRGTPYMT.getStringItemDef("F_ZONEFROM",i,"0").trim(); 
                        strData[i][32]          = RQFBDFRGTPYMT.getStringItemDef("F_ZONETO",i,"0").trim(); 
                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQFBDFRGTPYMT and filling data into array" + d.toString());
                        strCallStts="F";
                        strCallMesg="Data Problem";
                        return null;
                    }
        
            } // End of for Loop
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
	public String getCallStts()
	{
		return strCallStts;
	}	
	public String getCallMesg()
	{
		return strCallMesg;
	}

  
}
