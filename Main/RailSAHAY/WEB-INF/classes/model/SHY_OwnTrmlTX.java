package model; 

import java.util.ArrayList;


import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.exception.GG_Exception;
import util.logger.FoisLogger;


public class SHY_OwnTrmlTX extends ArrayList
{ 
    static Logger logger = FoisLogger.getLogger(SHY_OwnTrmlTX.class.getName());
    String erorCode			= null;
    
    public  String SHY_OwnTrmlTX( String si_UserID, String si_ClntID,String strRefID,String strName,
                                       String strCmpyName, String strPhone,String strEmail,String strCmdt,String strLocnType, String strLocn, String strMesg )
 throws GG_Exception
    {
    		  String strReply			= "FAIL";	
                  String strMessg= "";
        String strReplyMesg= "";
        logger.info("Entering SHY_OwnTrmlTX");
        
		FOISTuxedo RTSAHAYPRTL	= null;

		//*************************************************************************************
								 //Calling Tuxedo Service from WTC
		//**************************Calling Service RTSAHAYPRTL*********************************
		
		
		
		try
		{
			logger.info("Calling Tuxedo");
			RTSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
		    strMessg = strReply+"#"+ ne.toString(); 
		    return strMessg;
		}
		
		try
		{
			RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
            
            RTSAHAYPRTL.setString("F_USERID",   0,   si_UserID);
            RTSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntID);
            RTSAHAYPRTL.setString("F_ORDRBY1",     0,   strRefID);
            RTSAHAYPRTL.setString("F_ORDRBY2",     0,   "WORKFLOW1");
            RTSAHAYPRTL.setString("F_ORDRBY3",     0,   strName);        
            RTSAHAYPRTL.setString("F_ORDRBY4",     0,   strCmpyName); 
            RTSAHAYPRTL.setString("F_ORDRBY5",     0,   strPhone);
            RTSAHAYPRTL.setString("F_ORDRBY6",     0,   strEmail);
            RTSAHAYPRTL.setString("F_ORDRBY7",     0,   strCmdt);
            RTSAHAYPRTL.setString("F_ORDRBY8",     0,   strLocnType);
            RTSAHAYPRTL.setString("F_ORDRBY9",     0,   strLocn);      
            RTSAHAYPRTL.setString("F_ORDRBY10",     0,   strMesg);              
            RTSAHAYPRTL.setString("F_COMMITFLAG",     0,   "Y");
            RTSAHAYPRTL.setString("F_FLAG", 0,   "SAVE_SAHYPFT");
		
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
		    strMessg = strReply+"#"+ e.toString(); 
		    return strMessg;
		}

		try
		{
			logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
			RTSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
			System.out.println("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
		    strMessg = strReply+"#"+ e.toString(); 
		    return strMessg;
		}
	
		// Checking For Any Error from Service
		
		try
		{
			erorCode			= RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
			logger.info("erorCode::"+erorCode);
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			
			logger.fatal(erorCode);
		    strMessg = strReply+"#"+erorCode; 
		    return strMessg;
			
		}
		 
	      
			
	         
	             try
	            {						
	            	strReply			= RTSAHAYPRTL.getStringItemDef("F_RMRK",			0,	"0").trim();
				    strReplyMesg                        = RTSAHAYPRTL.getStringItemDef("F_GNRLRMRK",                        0,      "0").trim();
                         
					        
				}
	            catch(Exception d)
	            {
	                logger.fatal("Problem in Calling Service RTSAHAYPRTL and filling data into array" + d.toString());
	                strMessg = strReply+"#"+d.toString(); 
	                return strMessg;
	            }
	          	
	 
			try
			{
				RTSAHAYPRTL.endSession();
			}
			catch(Exception e)
			{
				logger.fatal("Error In End Session:" + e.toString());
	            throw new GG_Exception(106, e);
			}
			logger.info("Sucessfull Execution of SHY_OwnTrmlTX ||RTSAHAYPRTL");
        strMessg = strReply+"#"+strReplyMesg; 
        return strMessg;
    }
    
   
       
    
} 
