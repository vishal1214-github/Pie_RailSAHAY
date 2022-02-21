package model;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;


import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.exception.GG_Exception;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;


public class SHY_UserDashboardTX extends ArrayList
{ 
    static Logger logger = FoisLogger.getLogger(SHY_UserDashboardTX.class.getName());
    String erorCode			= null;
    
    public  ArrayList<Ostgdtls> SHY_DashboardOSTGTX(String si_UserId,String si_ClntId, String strcallflag, String strcustid,String strOrg )
 throws GG_Exception
    {
    		 ArrayList<Ostgdtls> list = new ArrayList<Ostgdtls>();					
        logger.info("Entering SHY_UserDashboardTX");
        
		FOISTuxedo RQSAHAYPRTL	= null;

		//*************************************************************************************
								 //Calling Tuxedo Service from WTC
		//**************************Calling Service RQSAHAYPRTL*********************************
		
		
		
		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYPRTL = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
		   // return strReply;
		}
		
		try
		{
			RQSAHAYPRTL.tuxInit("RQSAHAYPRTL1");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "CUST_LOGIN");
            RQSAHAYPRTL.setString("F_HLDGZONE",     0,   "OSTG");
            RQSAHAYPRTL.setString("F_HLDGZONE",     1,   strcustid);
            RQSAHAYPRTL.setString("F_CNSR",     0,   strOrg);
		
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
		   // return strReply;
		}

		try
		{
			logger.info("Calling Tuxedo Service RQSAHAYPRTL ...");
			RQSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
			System.out.println("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
                      //  return strReply;
		}
	
		// Checking For Any Error from Service
		
	/*	try
		{
			erorCode			= RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
			logger.info("erorCode::"+erorCode);
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			
			logger.fatal(erorCode);
		  //  return strReply;
			
		}*/
		 
	      
			
	         
	             try
	            {						
	            	//strReply			= RQSAHAYPRTL.getStringItemDef("F_RMRK",			0,	"0").trim();
                                    int start1         = 0;
                                    String startRowCount1   = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                                    if(startRowCount1.equals("0") || startRowCount1.equals(""))
                                    {
                                            System.out.println("NO ROWS in BUFFER");
                                            System.out.println("Help Not Found");
                                    }
                                    else
                                    {
                                            start1 = new Integer(startRowCount1.trim()).intValue();
                                    }
                                    logger.info("start1 : " + start1);
            
                                    String loadStr1                 = "";
            
                                    String strReport1       = "?%HELPCODE%";
            
				    String sttnfrom  = "";
				    String dmndNo= "";
				    String dmndTime= "";    
				    String rakecmdt = "";
				    String sttnto    = "";
				    String raketype = "";
				    String unts = "";
				    String dmndDtlsId      = "";
				    String dmndId      = "";
            
                                    for(int k=0; k<=start1-1; k++)
                                    {
                                        Ostgdtls ostg   = new Ostgdtls(); 
                                        sttnfrom         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   k,      "0").trim();
                                        dmndNo       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   k,      "0").trim();
                                        dmndTime       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   k,      "0").trim();
                                        rakecmdt       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",   k,      "0").trim();
                                        sttnto       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",   k,      "0").trim();
                                        raketype         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",   k,      "0");
                                        unts           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",   k,      "0").trim();
                                        dmndDtlsId           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",   k,      "0").trim();
                                        dmndId       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",   k,      "0").trim();
                                       
                                      ostg.setDmndDtlsId(dmndDtlsId);
                                        ostg.setDmndId(dmndId);
                                      ostg.setDmndNo(dmndNo);
                                      ostg.setDmndTime(dmndTime);
                                      ostg.setRakecmdt(rakecmdt);
                                      ostg.setRaketype(raketype);
                                      ostg.setSttnfrom(sttnfrom);
                                      ostg.setSttnto(sttnto);
                                      ostg.setUnts(unts);
                                       
                                       
                                        list.add(ostg);
                                  } // End of for Loop
					        
				}
	            catch(Exception d)
	            {
	                logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
	                //return strReply;
	            }
	          	
	 
			try
			{
				RQSAHAYPRTL.endSession();
			}
			catch(Exception e)
			{
				logger.fatal("Error In End Session:" + e.toString());
	            throw new GG_Exception(106, e);
			}
			logger.info("Sucessfull Execution of SHY_UserDashboardTX ||RQSAHAYPRTL");
                       // return strReply;
                        return list;
    }
    
    public  ArrayList<OnRunDtls> SHY_DashboardOnRunTX(String si_UserId,String si_ClntId, String strcallflag, String strcustid,String strOrg )
    throws GG_Exception
    {
                 ArrayList<OnRunDtls> list = new ArrayList<OnRunDtls>();                                  
        logger.info("Entering SHY_UserDashboardTX");
        
                FOISTuxedo RQSAHAYPRTL  = null;

                //*************************************************************************************
                                                                 //Calling Tuxedo Service from WTC
                //**************************Calling Service RQSAHAYPRTL*********************************
                
                
                
                try
                {
                        logger.info("Calling Tuxedo");
                        RQSAHAYPRTL = new FOISTuxedo();
                        logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                        logger.fatal("Unable to get the Client Object: " + ne.toString());
                   // return strReply;
                }
                
                try
                {
                        RQSAHAYPRTL.tuxInit("RQSAHAYPRTL1");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "CUST_LOGIN");
            RQSAHAYPRTL.setString("F_HLDGZONE",     0,   "ONRUN");
            RQSAHAYPRTL.setString("F_HLDGZONE",     1,   strcustid);
            RQSAHAYPRTL.setString("F_CNSR",     0,   strOrg);
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RQSAHAYPRTL ...");
                        RQSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                      //  return strReply;
                }
        
                // Checking For Any Error from Service
                
        /*      try
                {
                        erorCode                        = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
                        logger.info("erorCode::"+erorCode);
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                        
                        logger.fatal(erorCode);
                  //  return strReply;
                        
                }*/
                 
              
                        
                 
                     try
                    {                                           
                        //strReply                      = RQSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                    int start1         = 0;
                                    String startRowCount1   = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                                    if(startRowCount1.equals("0") || startRowCount1.equals(""))
                                    {
                                            System.out.println("NO ROWS in BUFFER");
                                            System.out.println("Help Not Found");
                                    }
                                    else
                                    {
                                            start1 = new Integer(startRowCount1.trim()).intValue();
                                    }
                                    logger.info("start1 : " + start1);
            
                                    String loadStr1                 = "";
            
                                    String strReport1       = "?%HELPCODE%";
            
                                    String strcnmtid        = "";  
                                    String strrakecmdt      = "";
                                    String strloddunts      = "";
                                    String strraketype      = "";
                                    String strsttnfrom  = "";
                                    String strsttnto    = "";
                                    String strsttn      = "";
                                    String strdvsn      = "";
                                    String strloadstts  = "";  
                                    String strsttstime  = "";
                                    String strloadid    = "";
                                    String strarvctime  = "";
            
                                    for(int k=0; k<=start1-1; k++)
                                    {
                                        OnRunDtls ostg   = new OnRunDtls(); 
                                        strcnmtid         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   k,      "0").trim();
                                        strrakecmdt       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   k,      "0").trim();
                                        strloddunts       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   k,      "0").trim();
                                        strraketype       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",   k,      "0").trim();
                                        strsttnfrom       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",   k,      "0").trim();
                                        strsttnto         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",   k,      "0");
                                        strsttn           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",   k,      "0").trim();
                                        strdvsn           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",   k,      "0").trim();
                                        strloadstts       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",   k,      "0").trim();
                                        strsttstime       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",   k,      "0").trim();
                                        strloadid         = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",   k,      "0").trim();
                                        strarvctime       = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",   k,      "0").trim();
                                       
                                        ostg.setCnmtid(strcnmtid);
                                        ostg.setRakecmdt(strrakecmdt);
                                        ostg.setLoddunts(strloddunts);
                                        ostg.setRaketype(strraketype);
                                        ostg.setSttnfrom(strsttnfrom);
                                        ostg.setSttnto(strsttnto);
                                        ostg.setSttn(strsttn);
                                        ostg.setDvsn(strdvsn);
                                        ostg.setLoadstts(strloadstts);
                                        ostg.setSttstime(strsttstime);
                                        ostg.setLoadid(strloadid);
                                        ostg.setEtaDstn(strarvctime);
                                        list.add(ostg);
                                  } // End of for Loop
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
                        //return strReply;
                    }
                        
         
                        try
                        {
                                RQSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                    throw new GG_Exception(106, e);
                        }
                        logger.info("Sucessfull Execution of SHY_UserDashboardTX ||RQSAHAYPRTL");
                       // return strReply;
                        return list;
    }
    
    public  ArrayList<Counts> SHY_DashboardCONTTX(String si_UserId,String si_ClntId, String strcallflag, String strcustid ,String strOrg)
    throws GG_Exception
    {
                 ArrayList<Counts> list = new ArrayList<Counts>();                                  
        logger.info("Entering SHY_UserDashboardTX");
        
                FOISTuxedo RQSAHAYPRTL  = null;

                //*************************************************************************************
                                                                 //Calling Tuxedo Service from WTC
                //**************************Calling Service RQSAHAYPRTL*********************************
                
                
                
                try
                {
                        logger.info("Calling Tuxedo");
                        RQSAHAYPRTL = new FOISTuxedo();
                        logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                        logger.fatal("Unable to get the Client Object: " + ne.toString());
                   // return strReply;
                }
                
                try
                {
                        RQSAHAYPRTL.tuxInit("RQSAHAYPRTL1");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "CUST_LOGIN");
            RQSAHAYPRTL.setString("F_HLDGZONE",     0,   "CONT");
            RQSAHAYPRTL.setString("F_HLDGZONE",     1,   strcustid);
            RQSAHAYPRTL.setString("F_CNSR",     0,   strOrg);
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RQSAHAYPRTL ...");
                        RQSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                      //  return strReply;
                }
        
                // Checking For Any Error from Service
                
        /*      try
                {
                        erorCode                        = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
                        logger.info("erorCode::"+erorCode);
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                        
                        logger.fatal(erorCode);
                  //  return strReply;
                        
                }*/
                 
              
                        
                 
                     try
                    {                                           
                        //strReply                      = RQSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                    int start1         = 0;
                                    String startRowCount1   = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                                    if(startRowCount1.equals("0") || startRowCount1.equals(""))
                                    {
                                            System.out.println("NO ROWS in BUFFER");
                                            System.out.println("Help Not Found");
                                    }
                                    else
                                    {
                                            start1 = new Integer(startRowCount1.trim()).intValue();
                                    }
                                    logger.info("start1 : " + start1);
            
                                    String loadStr1                 = "";
            
                                    String strReport1       = "?%HELPCODE%";
            
                                    String fnrCont="";
                                    String odrCont="";
                                    String srCont="";
                                    String cnrnCont="";
            
                                    for(int k=0; k<=start1-1; k++)
                                    {
                                        Counts cont   = new Counts(); 
                                        fnrCont         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   k,      "0").trim();
                                        odrCont       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   k,      "0").trim();
                                        srCont       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   k,      "0").trim();
                                        cnrnCont       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",   k,      "0").trim();
                                          
                                       cont.setCnrnCont(cnrnCont);
                                      cont.setFnrCont(fnrCont);
                                      cont.setOdrCont(odrCont);
                                      cont.setSrCont(srCont);
                                       
                                        list.add(cont);
                                  } // End of for Loop
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
                        //return strReply;
                    }
                        
         
                        try
                        {
                                RQSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                    throw new GG_Exception(106, e);
                        }
                        logger.info("Sucessfull Execution of SHY_UserDashboardTX ||RQSAHAYPRTL");
                       // return strReply;
                        return list;
    }
    
    public  ArrayList<SRDtls> SHY_DashboardSRTX(String si_UserId,String si_ClntId, String strcallflag, String strcustid ,String strOrg)
    throws GG_Exception
    {
                 ArrayList<SRDtls> list = new ArrayList<SRDtls>();                                  
        logger.info("Entering SHY_UserDashboardTX");
        
                FOISTuxedo RQSAHAYPRTL  = null;

                //*************************************************************************************
                                                                 //Calling Tuxedo Service from WTC
                //**************************Calling Service RQSAHAYPRTL*********************************
                
                
                
                try
                {
                        logger.info("Calling Tuxedo");
                        RQSAHAYPRTL = new FOISTuxedo();
                        logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                        logger.fatal("Unable to get the Client Object: " + ne.toString());
                   // return strReply;
                }
                
                try
                {
                        RQSAHAYPRTL.tuxInit("RQSAHAYPRTL1");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "SAHAY_SRVCRQST");
            RQSAHAYPRTL.setString("F_HLDGZONE",     0,   strcustid);
           
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RQSAHAYPRTL ...");
                        RQSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                      //  return strReply;
                }
        
                // Checking For Any Error from Service
                
        /*      try
                {
                        erorCode                        = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
                        logger.info("erorCode::"+erorCode);
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                        
                        logger.fatal(erorCode);
                  //  return strReply;
                        
                }*/
                 
              
                        
                 
                     try
                    {                                           
                        //strReply                      = RQSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                    int start1         = 0;
                                    String startRowCount1   = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                                    if(startRowCount1.equals("0") || startRowCount1.equals(""))
                                    {
                                            System.out.println("NO ROWS in BUFFER");
                                            System.out.println("Help Not Found");
                                    }
                                    else
                                    {
                                            start1 = new Integer(startRowCount1.trim()).intValue();
                                    }
                                    logger.info("start1 : " + start1);
            
                                    String loadStr1                 = "";
            
                                    String strReport1       = "?%HELPCODE%";
            
                                    String srvcId ="";
                                    String srSqncNo ="";
                                    String wrkFLowId ="";
                                    String fnr ="";
                                    String srvcType ="";
                                    String sttn ="";
                                    String srvcTxt ="";
                                    String srvcStts ="";
                                    String postTime ="";
            
                                    for(int k=0; k<=start1-1; k++)
                                    {
                                        SRDtls srvc   = new SRDtls(); 
                                        srvcId         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   k,      "0").trim();
                                        srSqncNo       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   k,      "0").trim();
                                        wrkFLowId       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   k,      "0").trim();
                                        fnr       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",   k,      "0").trim();
                                        srvcType       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",   k,      "0").trim();
                                        sttn         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",   k,      "0");
                                        srvcTxt           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",   k,      "0").trim();
                                        srvcStts           = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",   k,      "0").trim();
                                        postTime       = RQSAHAYPRTL.getStringItemDef("F_OWNGZONE3",   k,      "0").trim();
                                       
                                       srvc.setFnr(fnr);
                                      srvc.setPostTime(postTime);
                                      srvc.setSrSqncNo(srSqncNo);
                                      srvc.setSrvcId(srvcId);
                                      srvc.setSrvcStts(srvcStts);
                                      srvc.setSrvcTxt(srvcTxt);
                                      srvc.setSrvcType(srvcType);
                                      srvc.setSttn(sttn);
                                      srvc.setWrkFLowId(wrkFLowId);
                                        list.add(srvc);
                                  } // End of for Loop
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
                        //return strReply;
                    }
                        
         
                        try
                        {
                                RQSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                    throw new GG_Exception(106, e);
                        }
                        logger.info("Sucessfull Execution of SHY_UserDashboardTX ||RQSAHAYPRTL");
                       // return strReply;
                        return list;
    }
    
    public  ArrayList<CRDtls> SHY_DashboardCRTX(String si_UserId,String si_ClntId, String strcallflag, String strcustid ,String strOrg)
    throws GG_Exception
    {
                 ArrayList<CRDtls> list = new ArrayList<CRDtls>();                                  
        logger.info("Entering SHY_UserDashboardTX");
        
                FOISTuxedo RQSAHAYPRTL  = null;

                //*************************************************************************************
                                                                 //Calling Tuxedo Service from WTC
                //**************************Calling Service RQSAHAYPRTL*********************************
                
                
                
                try
                {
                        logger.info("Calling Tuxedo");
                        RQSAHAYPRTL = new FOISTuxedo();
                        logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                        logger.fatal("Unable to get the Client Object: " + ne.toString());
                   // return strReply;
                }
                
                try
                {
                        RQSAHAYPRTL.tuxInit("RQSAHAYPRTL1");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "SAHAY_CNDB");
            RQSAHAYPRTL.setString("F_HLDGZONE",     0,   strcustid);
           
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RQSAHAYPRTL ...");
                        RQSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                      //  return strReply;
                }
        
                // Checking For Any Error from Service
                
        /*      try
                {
                        erorCode                        = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
                        logger.info("erorCode::"+erorCode);
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                        
                        logger.fatal(erorCode);
                  //  return strReply;
                        
                }*/
                 
              
                        
                 
                     try
                    {                                           
                        //strReply                      = RQSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                    int start1         = 0;
                                    String startRowCount1   = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                                    if(startRowCount1.equals("0") || startRowCount1.equals(""))
                                    {
                                            System.out.println("NO ROWS in BUFFER");
                                            System.out.println("Help Not Found");
                                    }
                                    else
                                    {
                                            start1 = new Integer(startRowCount1.trim()).intValue();
                                    }
                                    logger.info("start1 : " + start1);
            
                                   
            
                                    String crId="";
                                     String wrkFLowId="";
                                     String crType="";
                                     String crCtgr="";
                                     String crSubCtgr="";
                                     String frstName="";
                                     String lastName="";
                                     String phNo="";
                                     String emailId="";
                                     String zone="";
                                     String dvsn="";
                                     String sttn="";
                                     String rmrk="";
                                     String sttsTime="";
                                     String stts="";
                                     String sttsDesc="";
                                     String fileName="";
                                     String rgtrTime="";
            
                                    for(int k=0; k<=start1-1; k++)
                                    {
                                        CRDtls cncrn   = new CRDtls(); 
                                          crId=RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   k,      "0");
                                          wrkFLowId=RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   k,      "0");
                                          crType=RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   k,      "0");
                                          crCtgr=RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",   k,      "0");
                                          crSubCtgr=RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",   k,      "0");
                                          frstName=RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",   k,      "0");
                                          lastName=RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",   k,      "0");
                                          phNo=RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",   k,      "0");
                                          emailId=RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",   k,      "0");
                                          zone=RQSAHAYPRTL.getStringItemDef("F_ORDRBY10",   k,      "0");
                                          dvsn=RQSAHAYPRTL.getStringItemDef("F_OWNGZONE",   k,      "0");
                                          sttn=RQSAHAYPRTL.getStringItemDef("F_OWNGZONE1",   k,      "0");
                                          rmrk=RQSAHAYPRTL.getStringItemDef("F_OWNGZONE2",   k,      "0");
                                          sttsTime=RQSAHAYPRTL.getStringItemDef("F_OWNGZONE3",   k,      "0");
                                          stts=RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB",   k,      "0");
                                          sttsDesc=RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB1",   k,      "0");
                                          fileName=RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB2",   k,      "0");
                                          rgtrTime=RQSAHAYPRTL.getStringItemDef("F_RPWBNUMB3",   k,      "0");
                                       
                                      cncrn.setCrCtgr(crCtgr);
                                      cncrn.setCrId(crId);
                                      cncrn.setCrSubCtgr(crSubCtgr);
                                      cncrn.setCrType(crType);
                                      cncrn.setEmailId(emailId);
                                      cncrn.setDvsn(dvsn);
                                      cncrn.setFrstName(frstName);
                                      cncrn.setLastName(lastName);
                                      cncrn.setPhNo(phNo);
                                      cncrn.setRmrk(rmrk);
                                      cncrn.setSttn(sttn);
                                      cncrn.setSttsTime(sttsTime);
                                      cncrn.setWrkFLowId(wrkFLowId);
                                      cncrn.setZone(zone);
                                        cncrn.setSttsDesc(sttsDesc);
                                        cncrn.setStts(stts);
                                      cncrn.setFileName(fileName);
                                      cncrn.setRgtrTime(rgtrTime);
                                        list.add(cncrn);
                                  } // End of for Loop
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
                        //return strReply;
                    }
                        
         
                        try
                        {
                                RQSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                    throw new GG_Exception(106, e);
                        }
                        logger.info("Sucessfull Execution of SHY_UserDashboardTX ||RQSAHAYPRTL");
                       // return strReply;
                        return list;
    }
    
    public InputStream getReqFile(String strFileName,String strReqId){
        
        InputStream baos= null;
        
        Connection  connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
           // dbConnection db = new dbConnection();
            connection=dbConnection.getConnection();
            String sqlstmt="SELECT RAVFILE FROM REM_SHAUSERATCH WHERE RAVFILENAME=? AND RAVCNCRID =? ";
            ps =  connection.prepareStatement(sqlstmt);
            
            ps.setString(1, strFileName);
            ps.setString(2, strReqId);
           
            rs=ps.executeQuery();
            while(rs.next())
            {
                Blob bs=rs.getBlob(1);
                baos= bs.getBinaryStream();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }                finally
                {
                        try
                        {
                                if (rs != null)
                                        rs.close();
                        }catch(Exception ex)
                        {System.out.println("Error in finally rs"+ex);}
                        try
                        {
                                if (ps != null)
                                        ps.close();
                        }catch(Exception ex1)
                        {System.out.println("Error in finally stmt "+ex1);}
                        try
                        {
                                if (connection != null)
                                        connection.close();
                        }catch(Exception ex2)
                        {System.out.println("Error in finally conn"+ex2);}
                }
        
        return baos;
    }
       
    
} 
