package model;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.exception.GG_Exception;

import util.logger.FoisLogger;


public class SHY_OdrRasTX
{
	
	static Logger logger = FoisLogger.getLogger(SHY_OdrRasTX.class.getName());
        
    String[][] strDetails = null;   // Variable to be returned as output of function
    private String strCallStts="S";
    private String strCallMesg="";
    String strError = "";
	public  String[][] getRASIOSttnWiseOtsgDmnd( String si_UserId, String si_ClntId,String si_Dvsn, String si_sttn, String si_Clst) throws GG_Exception
	{
            
		logger.info("Entering getRASIOSttnWiseOtsgDmnd....");
		logger.info("Function called with inputs :");
		logger.info("si_UserId  #"+si_UserId+"#");
		logger.info("si_ClntId  #"+si_ClntId+"#");
		logger.info("si_Dvsn  #"+si_Dvsn+"#");
		logger.info("si_sttn  #"+si_sttn+"#");
                logger.info("si_Clst  #"+si_Clst+"#");
                
            FOISTuxedo RQFOISPRTLRAS  = null;
            try
            {
                    logger.info("Calling Tuxedo");
                    RQFOISPRTLRAS = new FOISTuxedo();
                    logger.info("Client Object Got.");
            }
            catch (Exception e)
            {
                    logger.fatal("Unable to get the Client Object: " + e.toString());
                strCallStts="F";
                strCallMesg="Unable to get Client Object";
                return null;
            
            }
	    try
	    {
	            logger.info("Calling tuxInit.");

	            RQFOISPRTLRAS.tuxInit("RQFOISPRTLRAS");
	            RQFOISPRTLRAS.setString("F_USERID", 0, si_UserId);
	            RQFOISPRTLRAS.setString("F_CLNTID", 0,  si_ClntId);
	            RQFOISPRTLRAS.setString("F_FLAG", 0,  "RAS_OSTGSTTN");
	            RQFOISPRTLRAS.setString("F_HLDGZONE", 0, si_Dvsn.trim());
	           RQFOISPRTLRAS.setString("F_HLDGZONE1", 0, si_sttn.trim());
                RQFOISPRTLRAS.setString("F_SPCLSTTS", 0, si_Clst.trim()); /* for cluster*/
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
	                logger.info("Calling Tuxedo Service RQFOISPRTLRAS ...");
	                RQFOISPRTLRAS.call("N");
	                logger.info("CALL COMPLETED !");;
	        }
	        catch(Exception e)
	        {
	                logger.fatal("Exception while call to the service is " + e.toString());
	            strCallStts="F";
	            strCallMesg="Exception in Service Call";
	            return null;
	         
	        }
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQFOISPRTLRAS.getStringItemDef("F_ERORNO",0,"0");
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
	         try
             {
	            String strOutData = RQFOISPRTLRAS.getStringItemDef("F_ROWCONT", 0);
	            logger.info("Called Tux_Fchg.");
	            logger.info("F_ROWCONT :"+ strOutData);
	            int intRowCount=Integer.parseInt(strOutData);
	            strDetails = new String[intRowCount][15];
	            for (int i=0;i<intRowCount;i++)
	                    {
	                    strDetails[i][0]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY1",i);
	                    strDetails[i][1]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY2",i);
	                    strDetails[i][2]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY3", i);
	                    strDetails[i][3]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY4",i);
	                    strDetails[i][4]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY5",i);
	                    strDetails[i][5]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY6",i);
	                    strDetails[i][6]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY7", i);
	                    strDetails[i][7]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY8", i);
	                    strDetails[i][8]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY9", i);
	                    strDetails[i][9]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY10", i);
                        
	                        strDetails[i][10]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOAMNT",i);
                                strDetails[i][11]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOARVL",i);
                                strDetails[i][12]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOCHRG",i);
                                strDetails[i][13]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTODPRT",i);

	                    }




	          
	    }
	    catch (Exception e)
	    {
	        logger.info("There was an exception while creating and using the FOIS."+e);
	        strCallStts="F";
	        strCallMesg="Problem in Data Filling";
	        return null;
	    }
	    try
        {
            RQFOISPRTLRAS.endSession();
                
            logger.info("End RQFOISPRTLRAS.");
        }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                strCallStts="F";
                strCallMesg="Problem in Session End";
                return null;
            }
            logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQFOISPRTLRAS");
            return strDetails;
        }
    public  String[][] geRASRakeAlcnPlan( String si_UserId, String si_ClntId,String si_Dvsn, String si_DateFrom, String si_DateTo) throws GG_Exception
    {
        
            logger.info("Entering geRASRakeAlcnPlan....");
            logger.info("Function called with inputs :");
            logger.info("si_UserId  #"+si_UserId+"#");
            logger.info("si_ClntId  #"+si_ClntId+"#");
            logger.info("si_Dvsn  #"+si_Dvsn+"#");
            logger.info("si_DateFrom  #"+si_DateFrom+"#");
            logger.info("si_DateTo  #"+si_DateTo+"#");
            
        
        FOISTuxedo RQRASALLOCATION = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RQRASALLOCATION = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                strCallMesg="Tuxedo Connnection Failed";
                return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RQRASALLOCATION.tuxInit("RQRASALLOCATION");
                     RQRASALLOCATION.setString("F_USERID", 0, si_UserId);
                     RQRASALLOCATION.setString("F_CLNTID", 0,  si_ClntId);
                     RQRASALLOCATION.setString("F_DVSN", 0,si_Dvsn.trim().toUpperCase());
                     RQRASALLOCATION.setString("F_DATEFROM", 0, si_DateFrom.trim());
                     RQRASALLOCATION.setString("F_DATETO", 0, si_DateTo.trim());
                     logger.info("Called Tux_Fchg.");
                     RQRASALLOCATION.call("N");
                     logger.info("Called Tux_Call.");
                     String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQRASALLOCATION.getStringItemDef("F_ERORNO",0,"0");
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
                     String strData = RQRASALLOCATION.getStringItemDef("F_ROWCONT", 0);
                     logger.info("Called Tux_Fchg.");
                     logger.info("F_ROWCONT :"+ strData);
                     int intRowCount=Integer.parseInt(strData);
                     
                     strDetails = new String[intRowCount][14];
                     for (int i=0;i<intRowCount;i++)
                     {
                     
                             strDetails[i][0]        = RQRASALLOCATION.getStringItemDef("F_CYCLNUMB",                        i);
                               strDetails[i][1]        = RQRASALLOCATION.getStringItemDef("F_ORDRBY1",                 i);
                             strDetails[i][2]        = RQRASALLOCATION.getStringItemDef("F_STTNTO",          i);
                             if(strDetails[i][2].indexOf(",")>0)
                               {
                                       strDetails[i][2]="MULTI";
                                    
                              }
                             strDetails[i][3]        = RQRASALLOCATION.getStringItemDef("F_ORDRBY2",         i);
                             strDetails[i][4]        = RQRASALLOCATION.getStringItemDef("F_ORDRBY3",         i);
                             strDetails[i][5]        = RQRASALLOCATION.getStringItemDef("F_CUTINFLAG",               i);
                             strDetails[i][6]        = RQRASALLOCATION.getStringItemDef("F_FLAG",        i);
                             strDetails[i][7]        = RQRASALLOCATION.getStringItemDef("F_ORDRBY4",                 i);
                             
                              if(strDetails[i][7].indexOf(",")>0)
                             {
                                     strDetails[i][7]= strDetails[i][7].substring(0,strDetails[i][7].indexOf(","))+" & Others";
                                 
                           }
                             strDetails[i][8]        = RQRASALLOCATION.getStringItemDef("F_ORDRBY5", i);
                             if(strDetails[i][8].indexOf("/L")>0)
                             {
                                     strDetails[i][8]= strDetails[i][8].substring(0,strDetails[i][8].indexOf("/L"))+" & Group";
                              }
                             
                             strDetails[i][9]        = RQRASALLOCATION.getStringItemDef("F_DELTRESN", i);
                             strDetails[i][10]       = RQRASALLOCATION.getStringItemDef("F_ORDRBY6",   i);
                             strDetails[i][11]       = RQRASALLOCATION.getStringItemDef("F_RMRK",   i);
                             strDetails[i][12]       = RQRASALLOCATION.getStringItemDef("F_CYCLID",   i);

                     }
                     
                     RQRASALLOCATION.endSession();
                     logger.info("End RQRASALLOCATION.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                 strCallMesg="There was an exception while creating and using the FOIS.";
                 return null;

             }
             finally
             {
             try
             {
                     RQRASALLOCATION=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                 strCallMesg="There was an exception while creating and using the FOIS.";
                 return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQRASALLOCATION");
    return strDetails;
    }
    public  String[][] geRASIORakeAlotment( String si_UserId, String si_ClntId,String si_Dvsn,String si_Sttn, String si_DateFrom, String si_DateTo, String si_Clst) throws GG_Exception
    {
        
            logger.info("Entering geRASIORakeAlotment....");
            logger.info("Function called with inputs :");
            logger.info("si_UserId  #"+si_UserId+"#");
            logger.info("si_ClntId  #"+si_ClntId+"#");
            logger.info("si_Dvsn  #"+si_Dvsn+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            logger.info("si_Clst  #"+si_Clst+"#");
            logger.info("si_DateFrom  #"+si_DateFrom+"#");
            logger.info("si_DateTo  #"+si_DateTo+"#");
            
        
        FOISTuxedo RQFOISPRTLRAS = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RQFOISPRTLRAS = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                               strCallMesg="Tuxedo Connnection Failed.";
                               return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RQFOISPRTLRAS.tuxInit("RQFOISPRTLRAS");
                     RQFOISPRTLRAS.setString("F_USERID", 0, si_UserId);
                     RQFOISPRTLRAS.setString("F_CLNTID", 0,  si_ClntId);
                     RQFOISPRTLRAS.setString("F_FLAG", 0,  "RAS_RAKEALOT");
                                        RQFOISPRTLRAS.setString("F_HLDGZONE", 0, si_Dvsn.trim().toUpperCase());
                            RQFOISPRTLRAS.setString("F_HLDGZONE1", 0, si_Sttn.trim().toUpperCase());
                            RQFOISPRTLRAS.setString("F_HLDGZONE2", 0, si_DateFrom.trim());
                            RQFOISPRTLRAS.setString("F_HLDGZONE3", 0, si_DateTo.trim());
                          
                 RQFOISPRTLRAS.setString("F_SPCLSTTS", 0, si_Clst.trim()); /* for cluster*/
                                        logger.info("Called Tux_Fchg.");
                                        RQFOISPRTLRAS.call("N");
                                        logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                            try
                            {
                                    erorCode1                               = RQFOISPRTLRAS.getStringItemDef("F_ERORNO",0,"0");
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
                                        String strData = RQFOISPRTLRAS.getStringItemDef("F_ROWCONT", 0);
                                        logger.info("Called Tux_Fchg.");
                                        logger.info("F_ROWCONT :"+ strData);
                                        int intRowCount=Integer.parseInt(strData);
                                        strDetails = new String[intRowCount][30];
                                        for (int i=0;i<intRowCount;i++)
                                                {
                                                strDetails[i][0]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY1",i);
                                                strDetails[i][1]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY2",i);
                                                strDetails[i][2]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY3", i);
                                                strDetails[i][3]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY4",i);
                                                strDetails[i][4]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY5",i);
                                                strDetails[i][5]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY6",i);
                                                strDetails[i][6]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY7", i);
                                                strDetails[i][7]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY8", i);
                                                strDetails[i][8]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY9", i);
                                                strDetails[i][9]        =RQFOISPRTLRAS.getStringItemDef("F_ORDRBY10", i);
                                                strDetails[i][10]       =RQFOISPRTLRAS.getStringItemDef("F_ACDL",i);
                                                strDetails[i][11]       =RQFOISPRTLRAS.getStringItemDef("F_ACDLCONT", i);
                                                strDetails[i][12]       =RQFOISPRTLRAS.getStringItemDef("F_ACDLFRGN",i);
                                                strDetails[i][13]       =RQFOISPRTLRAS.getStringItemDef("F_ACDLFRGNCONT",i);
                                                strDetails[i][14]       =RQFOISPRTLRAS.getStringItemDef("F_ACINFLAG",i);
                                                strDetails[i][15]       =RQFOISPRTLRAS.getStringItemDef("F_ACKNTIME", i);
                                                strDetails[i][16]       =RQFOISPRTLRAS.getStringItemDef("F_ACMLKM", i);
                                                strDetails[i][17]       =RQFOISPRTLRAS.getStringItemDef("F_ACNTHEAD", i);
                                                strDetails[i][18]       =RQFOISPRTLRAS.getStringItemDef("F_ACNTNUMB", i);
                                                strDetails[i][19]       =RQFOISPRTLRAS.getStringItemDef("F_ACNTOFCR", i);
                                                strDetails[i][20]       =RQFOISPRTLRAS.getStringItemDef("F_ACNTTYPE", i);
                                                strDetails[i][21]       =RQFOISPRTLRAS.getStringItemDef("F_ACRDFROMTIME",i);
                                                    strDetails[i][22]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOAMNT",i);
                                                                                                    strDetails[i][23]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOARVL",i);
                                                                                                    strDetails[i][24]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTOCHRG",i);
                                                                                                    strDetails[i][25]       =RQFOISPRTLRAS.getStringItemDef("F_STTNTODPRT",i);
          
                                                }

                     
                     RQFOISPRTLRAS.endSession();
                     logger.info("End RQFOISPRTLRAS.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     RQFOISPRTLRAS=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQFOISPRTLRAS");
    return strDetails;
    }
    public  String[][] geRASIRONORE( String si_UserId, String si_ClntId,String si_Zone, String si_Dvsn,String si_Sttn, String si_Cmdt, String si_Date, String si_Clstr) throws GG_Exception
    {
        
            logger.info("Entering geRASIRONORE....");
            logger.info("Function called with inputs :");
            logger.info("si_UserId  #"+si_UserId+"#");
            logger.info("si_ClntId  #"+si_ClntId+"#");
            logger.info("si_Zone  #"+si_Zone+"#");
            logger.info("si_Dvsn  #"+si_Dvsn+"#");
            logger.info("si_Sttn  #"+si_Sttn+"#");
            logger.info("si_Cmdt  #"+si_Cmdt+"#");
            logger.info("si_Date  #"+si_Date+"#");
             logger.info("si_Clstr  #"+si_Clstr+"#");
            
        
        FOISTuxedo RQSTTNWISEINDT = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RQSTTNWISEINDT = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                                strCallMesg="Unable to get Client Object";
                                return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RQSTTNWISEINDT.tuxInit("RQSTTNWISEINDT");
                     RQSTTNWISEINDT.setString("F_USERID", 0, si_UserId);
                     RQSTTNWISEINDT.setString("F_CLNTID", 0,  si_ClntId);
                    RQSTTNWISEINDT.setString("F_ZONE",      0,  si_Zone);
                            RQSTTNWISEINDT.setString("F_DVSN",          0,      si_Dvsn);
                            RQSTTNWISEINDT.setString("F_STTN",      0,  si_Sttn);
                                        RQSTTNWISEINDT.setString("F_CMDT",      0,      si_Cmdt);
                            RQSTTNWISEINDT.setString("F_DATE",      0,   si_Date);
                 RQSTTNWISEINDT.setString("F_SPCLSTTS",      0, si_Clstr);
                                logger.info("Called Tux_Fchg.");
                                RQSTTNWISEINDT.call("N");
                                logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                           try
                           {
                                   erorCode1                               = RQSTTNWISEINDT.getStringItemDef("F_ERORNO",0,"0");
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
                                String strData = RQSTTNWISEINDT.getStringItemDef("F_ROWCONT", 0);
                                logger.info("Called Tux_Fchg.");
                                logger.info("F_ROWCONT :"+ strData);
                                int intRowCount=Integer.parseInt(strData);
                                strDetails = new String[intRowCount][16];
                                        int intIndtRptdSum     = 0;
                                 int intIndtOtsgSum     = 0;
                                 int intAlltMadeSum     = 0;
                                 
                                 String strIndtRptdSum     = "";
                                 String strIndtOtsgSum     = "";
                                 String strAlltMadeSum     = "";
                                 
                                 String strIndtRptdHref     = "";
                                 String strIndtOtsgHref     = "";
                                 String strAlltMadeHref     = "";
                                 
                                 String strIndtRptdSumHref     = "";
                                 String strIndtOtsgSumHref     = "";
                                 String strAlltMadeSumHref     = "";
                                 
                             if(intRowCount==0) 
                             {
                                 logger.info("in zero");
                             }
                             else{
                                 logger.info("in non zero");
                                for (int i=0;i<intRowCount;i++)
                                        {
                                        strDetails[i][0]     = RQSTTNWISEINDT.getStringItemDef("F_DVSNFROM",i).trim();  //strDvsn
                        strDetails[i][1]     = RQSTTNWISEINDT.getStringItemDef("F_STTNFROM",i).trim(); //strSttn
                        strDetails[i][2]     = RQSTTNWISEINDT.getStringItemDef("F_ACDLCONT"  ,i  ).trim();//strIndtRptd
                        strDetails[i][3]     = RQSTTNWISEINDT.getStringItemDef("F_RAKECONT",i ).trim();//strIndtOtsg
                        strDetails[i][4]     = RQSTTNWISEINDT.getStringItemDef("F_EMCONT"   ,i ).trim();//strAlltMade
                        
                        intIndtRptdSum          = intIndtRptdSum + Integer.parseInt((strDetails[i][2].equals(""))?"0":strDetails[i][2]);
                        intIndtOtsgSum          = intIndtOtsgSum + Integer.parseInt((strDetails[i][3].equals(""))?"0":strDetails[i][3]);
                        intAlltMadeSum          = intAlltMadeSum + Integer.parseInt((strDetails[i][4].equals(""))?"0":strDetails[i][4]);  
                        
                        strDetails[i][5]=si_Zone;
                        strDetails[i][6]=si_Dvsn;
                        strDetails[i][7]=si_Sttn;
                        strDetails[i][8]=si_Cmdt;
                        strDetails[i][10]= si_Date;
                        
                                            strDetails[i][14]       =RQSTTNWISEINDT.getStringItemDef("F_STTNTOAMNT",i);
                        
                

                                        }
                             }
                             
                             logger.info("in after loop");
                                
                                strIndtRptdSum          =intIndtRptdSum + ""; 
                        strIndtOtsgSum          =intIndtOtsgSum + ""; 
                        strAlltMadeSum          =intAlltMadeSum + ""; 
                        
                        logger.info("in after sum loop");
                        if(intRowCount==0) 
                             {
                                 logger.info("in zero");
                             }
                             else{
                                 logger.info("in non zero");

                        strDetails[0][11]=strIndtRptdSum;
                        strDetails[0][12]=strIndtOtsgSum;
                        strDetails[0][13]=strAlltMadeSum;
                             }

                     
                     RQSTTNWISEINDT.endSession();
                     logger.info("End RQSTTNWISEINDT.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     RQSTTNWISEINDT=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQSTTNWISEINDT");
    return strDetails;
    }
    public  String[][] geRASIRONOREDtls( String si_UserId, String si_ClntId,String strZoneCode,String strDvsnCode,String strSttnCode,String strCmdt,String strDate,String strFlag,String strSttnFrom,String si_Clstr) throws GG_Exception
    {
        
            logger.info("Entering geRASIRONOREDtls....");
            logger.info("Function called with inputs :");
            logger.info("si_UserId  #"+si_UserId+"#");
            logger.info("si_ClntId  #"+si_ClntId+"#");
          logger.info("strZoneCode"+strZoneCode);
                logger.info("strDvsnCode"+strDvsnCode);
                logger.info("strSttnCode"+strSttnCode);
                logger.info("strCmdt"+strCmdt);
                logger.info("strDate"+strDate);
                logger.info("strFlag"+strFlag);
                logger.info("strSttnFrom"+strSttnFrom);
        logger.info("si_Clstr"+si_Clstr);
        
        
        FOISTuxedo RQSTTNINDTDTLS = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RQSTTNINDTDTLS = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                                strCallMesg="Unable to get Client Object";
                                return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RQSTTNINDTDTLS.tuxInit("RQSTTNINDTDTLS");
                     RQSTTNINDTDTLS.setString("F_USERID", 0, si_UserId);
                     RQSTTNINDTDTLS.setString("F_CLNTID", 0,  si_ClntId);
                        
                        RQSTTNINDTDTLS.setString("F_STTN", 0, strSttnFrom);
                        RQSTTNINDTDTLS.setString("F_FLAG", 0, strFlag);
                        RQSTTNINDTDTLS.setString("F_DATE", 0, strDate);
                        RQSTTNINDTDTLS.setString("F_CMDT", 0, strCmdt);
                 RQSTTNINDTDTLS.setString("F_SPCLSTTS",      0,  si_Clstr);
                                logger.info("Called Tux_Fchg.");
                                RQSTTNINDTDTLS.call("N");
                                logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                           try
                           {
                                   erorCode1                               = RQSTTNINDTDTLS.getStringItemDef("F_ERORNO",0,"0");
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
                                String strData = RQSTTNINDTDTLS.getStringItemDef("F_ROWCONT", 0);
                                logger.info("Called Tux_Fchg.");
                                logger.info("F_ROWCONT :"+ strData);
                                int intRowCount=Integer.parseInt(strData);
                              strDetails = new String[intRowCount][35];
                        

                    String strid         ="";
                    String strDvsnFrom   ="";
                    String strSttnTo     ="";
                    String strCnsg       ="";
                    String strCnsr       ="";

                    String strDstnSttn   ="";
                    String strRakeNumb   ="";
                    String strFrwh       ="";
                    String strOdr        ="";
                    String strPrtyClss   ="";
                    String strStckTyp    ="";
                    String strRtfrFlag   ="";
                    String strDmndNumb   ="";
                    String strDmndDate   ="";
                    String strDmndTime   ="";
                    String strSpclNumb   ="";
                    String strTrfcType   ="";
                    String strPrntFlag   ="";
                    String strQuta       ="";
                    String strODC            ="";
                    String strVia        ="";
                    String strUnits      ="";
                    String strOtsgFrwh   ="";
                    String strDmndDtlsid ="";
                    String strLoadFrwh   ="";
                    String strCnclFrwh   ="";
                    String strFrftFrwh   ="";
                    String strData1      =null;
                    String strPrvsSttnTo               = "";
                    String strPrvsCnsr                 = "";
                    String strPrvsCnsg                 = "";
                    String strPrvsStckTyp         = "";
                    String strPrvsSttnFrom        = "";
                    String strPrvsDmndNumb        = "";
                    String strPrvsDmndDate        = "";
                    String strPrvsDmndTime        = "";
                    String strPrvsOtsgFrwh        = "";
                    String strPrvsFrwh                 = "";
                    String strSubPrtyClss ="";
                    int    intSrNo       = 0;
                        
                         if (strFlag.equals("R") )
                    {
                                 for (int i=0;i<intRowCount;i++)
                                        {
                                         intSrNo = i+1;
                                         strDetails[i][0]                               = "" + intSrNo; 
                                         strDetails[i][1]               = RQSTTNINDTDTLS.getStringItemDef("F_DVSNFROM", i, "0").trim();
                                         strDetails[i][2]               = RQSTTNINDTDTLS.getStringItemDef("F_PRTYCLSS", i, "0").trim();
                                         strDetails[i][3]                               = RQSTTNINDTDTLS.getStringItemDef("F_CMDT", i, "0").trim();
                                         strDetails[i][4]                       = RQSTTNINDTDTLS.getStringItemDef("F_STTNTO", i, "0").trim();
                                         strDetails[i][5]                       = RQSTTNINDTDTLS.getStringItemDef("F_CNSR", i, "0").trim();
                                         strDetails[i][6]                       = RQSTTNINDTDTLS.getStringItemDef("F_CNSG", i, "0").trim();
                                         strDetails[i][7]                       = RQSTTNINDTDTLS.getStringItemDef("F_STCKTYPE", i, "0").trim();
                                         strDetails[i][8]                       = RQSTTNINDTDTLS.getStringItemDef("F_RTFRFLAG", i, "0").trim();
                                         strDetails[i][9]                       = RQSTTNINDTDTLS.getStringItemDef("F_STTNFROM", i, "0").trim();
                                         strDetails[i][10]                      = RQSTTNINDTDTLS.getStringItemDef("F_DMNDNUMB", i, "0").trim();
                                         strDetails[i][11]                      = RQSTTNINDTDTLS.getStringItemDef("F_DMNDDATE", i, "0").trim();
                                         strDetails[i][12]                      = RQSTTNINDTDTLS.getStringItemDef("F_DMNDTIME", i, "0").trim();
                                         strDetails[i][13]                      = RQSTTNINDTDTLS.getStringItemDef("F_SPCLNUMB", i, "0").trim();
                                         strDetails[i][14]                      = RQSTTNINDTDTLS.getStringItemDef("F_TRFCTYPE", i, "0").trim();
                                         strDetails[i][15]                      = RQSTTNINDTDTLS.getStringItemDef("F_PRNTFLAG", i, "0").trim();
                                         strDetails[i][16]                      = RQSTTNINDTDTLS.getStringItemDef("F_QUTA", i, "0").trim();
                                         strDetails[i][17]                      = RQSTTNINDTDTLS.getStringItemDef("F_ODC", i, "0").trim();
                                         strDetails[i][18]                      = RQSTTNINDTDTLS.getStringItemDef("F_VIA", i, "0").trim();
                                         strDetails[i][19]                      = RQSTTNINDTDTLS.getStringItemDef("F_UNTS", i, "0").trim();
                                         strDetails[i][20]                      = RQSTTNINDTDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim();
                                         strDetails[i][21]                      = RQSTTNINDTDTLS.getStringItemDef("F_FRWH", i, "0").trim();
                                         strDetails[i][22]                      = RQSTTNINDTDTLS.getStringItemDef("F_DMNDDTLSID", i, "0").trim();
                                         strDetails[i][23]                      = RQSTTNINDTDTLS.getStringItemDef("F_LOADFRWH", i, "0").trim();
                                         strDetails[i][24]                      = RQSTTNINDTDTLS.getStringItemDef("F_CNCLFRWH", i, "0").trim();
                                         strDetails[i][25]                      = RQSTTNINDTDTLS.getStringItemDef("F_FRFTFRWH", i, "0").trim();
                                         strDetails[i][26]                      = RQSTTNINDTDTLS.getStringItemDef("F_DATE", i, "0").trim();
                     
                                            strDetails[i][28]                     =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOAMNT",i).trim();
                                            strDetails[i][29]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOARVL",i).trim();
                                            strDetails[i][30]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOCHRG",i).trim();
                                            strDetails[i][31]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTODPRT",i).trim();
                                        }
                                 
                    }
                         else if (strFlag.equals("O") )
                    {
                                 for (int i=0;i<intRowCount;i++)
                                        {
                                         
                                         
                                        
                                         
                                         intSrNo = i+1;
                                         strDetails[i][0]                               = "" + intSrNo;
                                         strDetails[i][1]       = RQSTTNINDTDTLS.getStringItemDef("F_DVSNFROM", i, "0").trim();                        
                                         strDetails[i][2]       = RQSTTNINDTDTLS.getStringItemDef("F_PRTYCLSS", i, "0").trim();   
                                         strDetails[i][3]                       = RQSTTNINDTDTLS.getStringItemDef("F_CMDT", i, "0").trim();              
                                         strDetails[i][4]                   = RQSTTNINDTDTLS.getStringItemDef("F_STTNTO", i, "0").trim();        
                                         strDetails[i][5]                   = RQSTTNINDTDTLS.getStringItemDef("F_CNSR", i, "0").trim();           
                                         strDetails[i][6]                = RQSTTNINDTDTLS.getStringItemDef("F_CNSG", i, "0").trim();            
                                         strDetails[i][7]               = RQSTTNINDTDTLS.getStringItemDef("F_STCKTYPE", i, "0").trim();       
                                         strDetails[i][8]               = RQSTTNINDTDTLS.getStringItemDef("F_RTFRFLAG", i, "0").trim();      
                                         strDetails[i][9]               = RQSTTNINDTDTLS.getStringItemDef("F_STTNFROM", i, "0").trim();      
                                         strDetails[i][10]              = "";                                                                
                                         strDetails[i][11]              = RQSTTNINDTDTLS.getStringItemDef("F_DMNDDATE", i, "0").trim();      
                                         strDetails[i][12]              = "";                                                                
                                         strDetails[i][13]              = "";
                                         strDetails[i][14]              = RQSTTNINDTDTLS.getStringItemDef("F_TRFCTYPE", i, "0").trim();      
                                         strDetails[i][15]              = RQSTTNINDTDTLS.getStringItemDef("F_PRNTFLAG", i, "0").trim();      
                                         strDetails[i][16]              = "";            
                                         strDetails[i][17]              = RQSTTNINDTDTLS.getStringItemDef("F_ODC", i, "0").trim();               
                                         strDetails[i][18]              = "";            
                                         strDetails[i][19]              = RQSTTNINDTDTLS.getStringItemDef("F_OTSGUNTS", i, "0").trim();            
                                         strDetails[i][20]              = RQSTTNINDTDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim();      
                                         strDetails[i][21]              = "";          
                                         strDetails[i][22]              = "";
                                         strDetails[i][23]              = "";    
                                         strDetails[i][24]              = "";     
                                         strDetails[i][25]              = "";     
                                         strDetails[i][26]              = ""; 
                                         strDetails[i][27]              = RQSTTNINDTDTLS.getStringItemDef("F_PRTYNUMB", i, "0").trim();  
                                        
                                         strDetails[i][28]              =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOAMNT",i).trim();
                                         strDetails[i][29]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOARVL",i).trim();
                                         strDetails[i][30]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTOCHRG",i).trim();
                                         strDetails[i][31]                         =RQSTTNINDTDTLS.getStringItemDef("F_STTNTODPRT",i).trim();
                                        }
                                 
                    }
                         else 
                    {
                                 for (int i=0;i<intRowCount;i++)
                                        {
                                         
                                         if (i==0)
                                {
                                    strPrvsSttnTo         =    strSttnTo        ;  
                                    strPrvsCnsr           =    strCnsr          ;  
                                    strPrvsCnsg           =    strCnsg          ;  
                                    strPrvsStckTyp    =    strStckTyp  ;
                                    strPrvsSttnFrom   =    strSttnFrom ;
                                    strPrvsDmndNumb   =    strDmndNumb ;
                                    strPrvsDmndDate   =    strDmndDate ;
                                    strPrvsDmndTime   =    strDmndTime ;
                                    strPrvsOtsgFrwh   =    strOtsgFrwh ;
                                    strPrvsFrwh           =    strFrwh          ;  
                                  }
                                        
                                         
                                         
                                         strSttnTo                  = RQSTTNINDTDTLS.getStringItemDef("F_STTNTO", i, "0").trim(); 
                                         strCnsr                        = RQSTTNINDTDTLS.getStringItemDef("F_CNSR", i, "0").trim();  
                     strCnsg                    = RQSTTNINDTDTLS.getStringItemDef("F_CNSG", i, "0").trim();      
                     strStckTyp                 = RQSTTNINDTDTLS.getStringItemDef("F_STCKTYPE", i, "0").trim();                                                           
                     strSttnFrom                = RQSTTNINDTDTLS.getStringItemDef("F_STTNFROM", i, "0").trim();
                     strDmndNumb                = RQSTTNINDTDTLS.getStringItemDef("F_DMNDNUMB", i, "0").trim();
                     strDmndDate                = RQSTTNINDTDTLS.getStringItemDef("F_DMNDDATE", i, "0").trim();
                     strDmndTime                = RQSTTNINDTDTLS.getStringItemDef("F_DMNDTIME", i, "0").trim();      
                     strOtsgFrwh                = RQSTTNINDTDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim();
                     strFrwh                    = RQSTTNINDTDTLS.getStringItemDef("F_FRWH", i, "0").trim();
                                                                                       
                     
                     if (
                             (strSttnTo.equals(strPrvsSttnTo))&& (strCnsr.equals(strPrvsCnsr)) && (strCnsg.equals(strPrvsCnsg)) && (strStckTyp.equals(strPrvsStckTyp))
                             && (strSttnFrom.equals(strPrvsSttnFrom)) && (strDmndNumb.equals(strPrvsDmndNumb)) && (strDmndDate.equals(strPrvsDmndDate)) && (strDmndTime.equals(strPrvsDmndTime))
                             && (strOtsgFrwh.equals(strPrvsOtsgFrwh))&& (strFrwh.equals(strPrvsFrwh))
                             )
                         {
                         
                         }
                         else
                         {
                                 
                                                 strDetails[i][0]                               = "" + (++intSrNo);
                            
                         } 
                                         
                     strDetails[i][1]           = "";                                                                            
                     strDetails[i][2]           = "";                                                       
                     strDetails[i][3]                   = RQSTTNINDTDTLS.getStringItemDef("F_CMDT", i, "0").trim();        
                     strDetails[i][4]               = RQSTTNINDTDTLS.getStringItemDef("F_STTNTO", i, "0").trim();  
                     strDetails[i][5]               = RQSTTNINDTDTLS.getStringItemDef("F_CNSR", i, "0").trim();     
                     strDetails[i][6]           = RQSTTNINDTDTLS.getStringItemDef("F_CNSG", i, "0").trim();      
                     strDetails[i][7]           = RQSTTNINDTDTLS.getStringItemDef("F_STCKTYPE", i, "0").trim(); 
                     strDetails[i][8]           = "";                                                          
                     strDetails[i][9]           = RQSTTNINDTDTLS.getStringItemDef("F_STTNFROM", i, "0").trim();
                     strDetails[i][10]                  = RQSTTNINDTDTLS.getStringItemDef("F_DMNDNUMB", i, "0").trim();
                     strDetails[i][11]                  = RQSTTNINDTDTLS.getStringItemDef("F_DMNDDATE", i, "0").trim();
                     strDetails[i][12]                  = RQSTTNINDTDTLS.getStringItemDef("F_DMNDTIME", i, "0").trim();
                     strDetails[i][13]                  = "";                                                          
                     strDetails[i][14]                  = "";                                                          
                     strDetails[i][15]                  = "";                                                          
                     strDetails[i][16]                  = "";                                      
                     strDetails[i][17]                  = "";                                                              
                     strDetails[i][18]                  = "";      
                     strDetails[i][19]                  = RQSTTNINDTDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim();
                     strDetails[i][20]                  = RQSTTNINDTDTLS.getStringItemDef("F_FRWH", i, "0").trim();       
                     strDetails[i][21]          = "";                                                      
                     strDetails[i][22]                  = RQSTTNINDTDTLS.getStringItemDef("F_FNOTNUMB", i, "0").trim();
                     strDetails[i][23]                  = RQSTTNINDTDTLS.getStringItemDef("F_FNOTDATE", i, "0").trim();
                     strDetails[i][24]                  = RQSTTNINDTDTLS.getStringItemDef("F_ALOTTIME", i, "0").trim();
                     strDetails[i][25]                  = "";
                     strDetails[i][27]                  = RQSTTNINDTDTLS.getStringItemDef("F_TOTLWGON", i, "0").trim(); 
                     
                                         
                                                strPrvsSttnTo     =    strSttnTo        ;  
                            strPrvsCnsr           =    strCnsr          ;  
                            strPrvsCnsg           =    strCnsg          ;  
                            strPrvsStckTyp    =    strStckTyp  ;
                            strPrvsSttnFrom   =    strSttnFrom ;
                            strPrvsDmndNumb   =    strDmndNumb ;
                            strPrvsDmndDate   =    strDmndDate ;
                            strPrvsDmndTime   =    strDmndTime ;
                            strPrvsOtsgFrwh   =    strOtsgFrwh ;
                            strPrvsFrwh           =    strFrwh ; 
                            
                            
                                        }
                                 
                    }
                         
                
                     
                     RQSTTNINDTDTLS.endSession();
                     logger.info("End RQSTTNINDTDTLS.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     RQSTTNINDTDTLS=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQSTTNINDTDTLS");
    return strDetails;
    }
    public  String[][] geRASCOALAlotment( String si_UserId, String si_ClntId,String siDvsn,String siCustCode,String siDateFrom,String siDateTo,String siPrty) throws GG_Exception
    {
        
            logger.info("Entering geRASCOALAlotment....");
            logger.info("Function called with inputs :");
            logger.info("si_UserId  #"+si_UserId+"#");
            logger.info("si_ClntId  #"+si_ClntId+"#");
           logger.info("siDvsn"+ siDvsn);
                logger.info("siCustCode" + siCustCode);
                logger.info("siDateFrom"+ siDateFrom);
                logger.info("siDateTo" + siDateTo);
                logger.info("siPrty" + siPrty);
            
        
        FOISTuxedo RCQARREARRPRT = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RCQARREARRPRT = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                                 strCallMesg="Unable to get Client Object";
                                 return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RCQARREARRPRT.tuxInit("RCQARREARRPRT");
                     RCQARREARRPRT.setString("F_USERID", 0, si_UserId);
                     RCQARREARRPRT.setString("F_CLNTID", 0,  si_ClntId);
                     
                                RCQARREARRPRT.setString("F_DVSN",                               siDvsn);
                                RCQARREARRPRT.setString("F_CMDT",                               "COAL");
                                RCQARREARRPRT.setString("F_FLAG",                               siCustCode);
                                RCQARREARRPRT.setString("F_DATEFROM",                   siDateFrom);
                                RCQARREARRPRT.setString("F_DATETO",                             siDateTo);
                                RCQARREARRPRT.setString("F_PRTYCLSS",                   siPrty);
                                RCQARREARRPRT.setString("F_ACDL",                               "");
                                
                     logger.info("Called Tux_Fchg.");
                     RCQARREARRPRT.call("N");
                     logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                             try
                             {
                                     erorCode1                               = RCQARREARRPRT.getStringItemDef("F_ERORNO",0,"0");
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
                     String strData = RCQARREARRPRT.getStringItemDef("F_ROWCONT", 0);
                     logger.info("Called Tux_Fchg.");
                     logger.info("F_ROWCONT :"+ strData);
                     int intRowCount=Integer.parseInt(strData);
                     
                     strDetails = new String[intRowCount][26];
                                for (int i=0;i<intRowCount;i++)
                                        {
                                                                                
                                        strDetails[i][0]    =   RCQARREARRPRT.getStringItemDef("F_ACDL",                    i).trim();
                                        strDetails[i][1]        =       RCQARREARRPRT.getStringItemDef("F_ACDLFRGNCONT",        i).trim();
                                            strDetails[i][1] = strDetails[i][1].replaceAll("/"," ");
                                        strDetails[i][2]        =       RCQARREARRPRT.getStringItemDef("F_ACINFLAG",            i).trim();
                                        strDetails[i][3]        =       RCQARREARRPRT.getStringItemDef("F_ACKNTIME",            i).trim();
                                        strDetails[i][4]    =   RCQARREARRPRT.getStringItemDef("F_ACMLKM",                      i).trim();
                                        strDetails[i][5]        =       RCQARREARRPRT.getStringItemDef("F_ACNTHEAD",        i).trim();
                                        strDetails[i][6]        =       RCQARREARRPRT.getStringItemDef("F_ACNTNUMB",            i).trim();
                                        strDetails[i][7]    =   RCQARREARRPRT.getStringItemDef("F_ACRDFROMTIME",        i).trim();
                                        strDetails[i][8]        =       RCQARREARRPRT.getStringItemDef("F_ACRDTOTIME",          i).trim();
                                        strDetails[i][9]        =       RCQARREARRPRT.getStringItemDef("F_ACRLAMNT",            i).trim();
                                        strDetails[i][10]       =       RCQARREARRPRT.getStringItemDef("F_ACNTTYPE",            i).trim();
                                        strDetails[i][11]       =       RCQARREARRPRT.getStringItemDef("F_ACRLDATE",            i).trim();
                                        strDetails[i][12]       =       RCQARREARRPRT.getStringItemDef("F_ACTLFRWH",            i).trim();
                                        strDetails[i][13]       =       RCQARREARRPRT.getStringItemDef("F_ACTLICTIME",          i).trim();
                                        strDetails[i][14]       =       RCQARREARRPRT.getStringItemDef("F_ACTLUNTS",            i).trim();
                                        strDetails[i][15]       =   RCQARREARRPRT.getStringItemDef("F_ACTLWGHT",                i).trim();
                                        strDetails[i][16]       =   RCQARREARRPRT.getStringItemDef("F_ACTLWGHTARVD",    i).trim();
                                        strDetails[i][17]       =   RCQARREARRPRT.getStringItemDef("F_ZONE",                    i).trim();
                                        strDetails[i][18]   =   RCQARREARRPRT.getStringItemDef("F_ACTLWGHTUNIT",        i).trim();
                                        strDetails[i][19]   =   RCQARREARRPRT.getStringItemDef("F_ADVSTIME",            i).trim();
                                        strDetails[i][20]   =   RCQARREARRPRT.getStringItemDef("F_AIRBRAKTYPE",         i).trim();
                                        strDetails[i][21]   =   RCQARREARRPRT.getStringItemDef("F_ALOTTIME",            i).trim();
                                        strDetails[i][22]       =   RCQARREARRPRT.getStringItemDef("F_AMNT",                    i).trim();
                                        strDetails[i][23]       =       RCQARREARRPRT.getStringItemDef("F_ACDLFRGN",            i).trim();
                                        strDetails[i][24]       =       RCQARREARRPRT.getStringItemDef("F_STTN",                        i).trim();


                                        }
                     
                     RCQARREARRPRT.endSession();
                     logger.info("End RCQARREARRPRT.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     RCQARREARRPRT=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RCQARREARRPRT");
    return strDetails;
    }
    public  String[][] geRASCOALAlotmentOdr( String si_UserId, String si_ClntId,String strDvsnCode,String strPrtyCode,String strSpsrCode,String strCustCode,String strFeldCode,String strDest,String strPilotCode,String strOptnSupl) throws GG_Exception
    {
        
            logger.info("Entering geRASCOALAlotmentOdr....");
                  logger.info("strDvsn"+ strDvsnCode);
                logger.info("strPrtyCode" + strPrtyCode);
                logger.info("strSpsrCode" + strSpsrCode);               
                logger.info("strCustCode"+ strCustCode);
                logger.info("strFeldCode" + strFeldCode);
                logger.info("strDest" + strDest);
                logger.info("strPilotCode" + strPilotCode);
                logger.info("strOptnSupl" + strOptnSupl);
            
        
        FOISTuxedo RCQCOALPRGMALSP = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RCQCOALPRGMALSP = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                                strCallMesg="Unable to get Client Object";
                                return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RCQCOALPRGMALSP.tuxInit("RCQCOALPRGMALSP");
                     RCQCOALPRGMALSP.setString("F_USERID", 0, si_UserId);
                     RCQCOALPRGMALSP.setString("F_CLNTID", 0,  si_ClntId);
                     
                        RCQCOALPRGMALSP.setString("F_DVSN",                             strDvsnCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CMDT",                             "COAL");
                                        RCQCOALPRGMALSP.setString("F_FLAG",                             strOptnSupl.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CNSRWISE",                 strSpsrCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNAT",                   strFeldCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CUSTCONT",                 strCustCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNTO",                   strDest.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNFROM",                 strPilotCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_PRTYHALFYEAR",             strPrtyCode.trim().toUpperCase());
                                
                     logger.info("Called Tux_Fchg.");
                     RCQCOALPRGMALSP.call("N");
                     logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                            try
                            {
                                    erorCode1                               = RCQCOALPRGMALSP.getStringItemDef("F_ERORNO",0,"0");
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
                     String strData = RCQCOALPRGMALSP.getStringItemDef("F_ROWCONT", 0);
                     logger.info("Called Tux_Fchg.");
                     logger.info("F_ROWCONT :"+ strData);
                     int intRowCount=Integer.parseInt(strData);
                     
                   strDetails = new String[intRowCount][14];
                                for (int i=0;i<intRowCount;i++)
                                        {
                                                                                
                                        strDetails[i][0]                =       RCQCOALPRGMALSP.getStringItemDef("F_ALOTTIME",                  i).trim();
                                        strDetails[i][1]                =       RCQCOALPRGMALSP.getStringItemDef("F_STTN",                              i).trim();
                                        strDetails[i][2]                =       RCQCOALPRGMALSP.getStringItemDef("F_CNSR",                              i).trim();
                                        strDetails[i][3]                =       RCQCOALPRGMALSP.getStringItemDef("F_DSTN",                              i).trim();
                                        strDetails[i][4]                =       RCQCOALPRGMALSP.getStringItemDef("F_CUSTCODE",                  i).trim();
                                        strDetails[i][5]                =       RCQCOALPRGMALSP.getStringItemDef("F_PRTYCLSS",          i).trim();
                                        strDetails[i][6]                        =       RCQCOALPRGMALSP.getStringItemDef("F_CMDTCODE",              i).trim();
                                        strDetails[i][7]                =       RCQCOALPRGMALSP.getStringItemDef("F_ALOTUNTS",                  i).trim();
                                        strDetails[i][8]                =       RCQCOALPRGMALSP.getStringItemDef("F_DATE",                              i).trim();
                                        strDetails[i][9]                        =       RCQCOALPRGMALSP.getStringItemDef("F_STTNAPPLAT",                i).trim();
                                        strDetails[i][10]                       =       RCQCOALPRGMALSP.getStringItemDef("F_ACDLFRGNCONT",              i).trim();
                                        strDetails[i][11]                       =       RCQCOALPRGMALSP.getStringItemDef("F_RMRK",                              i).trim();


                                        }
                     
                     RCQCOALPRGMALSP.endSession();
                     logger.info("End RCQCOALPRGMALSP.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                 strCallMesg="Problem in Data Filling";
                                 return null;

             }
             finally
             {
             try
             {
                     RCQCOALPRGMALSP=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                 strCallMesg="Problem in Data Filling";
                                 return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RCQCOALPRGMALSP");
    return strDetails;
    }
    public  String[][] geRASCOALAlotmentthrty( String si_UserId, String si_ClntId,String strDvsnCode,String strPrtyCode,String strSpsrCode,String strCustCode,String strFeldCode,String strDest,String strPilotCode,String strOptnSupl) throws GG_Exception
    {
        
            logger.info("Entering geRASCOALAlotmentthrty....");
                  logger.info("strDvsn"+ strDvsnCode);
                logger.info("strPrtyCode" + strPrtyCode);
                logger.info("strSpsrCode" + strSpsrCode);               
                logger.info("strCustCode"+ strCustCode);
                logger.info("strFeldCode" + strFeldCode);
                logger.info("strDest" + strDest);
                logger.info("strPilotCode" + strPilotCode);
                logger.info("strOptnSupl" + strOptnSupl);
            
        
        FOISTuxedo RCQCOALPRGMALSP = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     RCQCOALPRGMALSP = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                               strCallMesg="Unable to get Client Object";
                               return null;
             }

             try
             {
                     logger.info("Calling tuxInit.");

                     RCQCOALPRGMALSP.tuxInit("RCQCOALPRGMALSP");
                     RCQCOALPRGMALSP.setString("F_USERID", 0, si_UserId);
                     RCQCOALPRGMALSP.setString("F_CLNTID", 0,  si_ClntId);
                     
                        RCQCOALPRGMALSP.setString("F_DVSN",                             strDvsnCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CMDT",                             "COAL");
                                        RCQCOALPRGMALSP.setString("F_FLAG",                             strOptnSupl.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CNSRWISE",                 strSpsrCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNAT",                   strFeldCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_CUSTCONT",                 strCustCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNTO",                   strDest.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_STTNFROM",                 strPilotCode.trim().toUpperCase());
                                        RCQCOALPRGMALSP.setString("F_PRTYHALFYEAR",             strPrtyCode.trim().toUpperCase());
                                
                     logger.info("Called Tux_Fchg.");
                     RCQCOALPRGMALSP.call("N");
                     logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                             try
                             {
                                     erorCode1                               = RCQCOALPRGMALSP.getStringItemDef("F_ERORNO",0,"0");
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
                     String strData = RCQCOALPRGMALSP.getStringItemDef("F_ROWCONT", 0);
                     logger.info("Called Tux_Fchg.");
                     logger.info("F_ROWCONT :"+ strData);
                     int intRowCount=Integer.parseInt(strData);
                     
                   strDetails = new String[intRowCount][14];
                                for (int i=0;i<intRowCount;i++)
                                        {
                                                                                
                                        strDetails[i][0]                =       RCQCOALPRGMALSP.getStringItemDef("F_ALOTTIME",                  i).trim();
                                        strDetails[i][1]                =       RCQCOALPRGMALSP.getStringItemDef("F_STTN",                              i).trim();
                                        strDetails[i][2]                =       RCQCOALPRGMALSP.getStringItemDef("F_CNSR",                              i).trim();
                                        strDetails[i][3]                =       RCQCOALPRGMALSP.getStringItemDef("F_DSTN",                              i).trim();
                                        strDetails[i][4]                =       RCQCOALPRGMALSP.getStringItemDef("F_CUSTCODE",                  i).trim();
                                        strDetails[i][5]                =       RCQCOALPRGMALSP.getStringItemDef("F_PRTYCLSS",          i).trim();
                                        strDetails[i][6]                        =       RCQCOALPRGMALSP.getStringItemDef("F_CMDTCODE",              i).trim();
                                        strDetails[i][7]                =       RCQCOALPRGMALSP.getStringItemDef("F_ALOTUNTS",                  i).trim();
                                        strDetails[i][8]                =       RCQCOALPRGMALSP.getStringItemDef("F_DATE",                              i).trim();
                                        strDetails[i][9]                        =       RCQCOALPRGMALSP.getStringItemDef("F_STTNAPPLAT",                i).trim();
                                        strDetails[i][10]                       =       RCQCOALPRGMALSP.getStringItemDef("F_ACDLFRGNCONT",              i).trim();
                                        strDetails[i][11]                       =       RCQCOALPRGMALSP.getStringItemDef("F_RMRK",                              i).trim();


                                        }
                     
                     RCQCOALPRGMALSP.endSession();
                     logger.info("End RCQCOALPRGMALSP.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     RCQCOALPRGMALSP=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||RCQCOALPRGMALSP");
    return strDetails;
    }
    public  String[][] getODROtsgDtls( String si_UserId, String si_ClntId,String strZone, String strQryType) throws GG_Exception
    {
        
            logger.info("Entering getODROtsgDtls....");
                  logger.info("si_UserId"+ si_UserId);
                logger.info("si_ClntId" + si_ClntId);
                logger.info("strZone" + strZone);               
                logger.info("strQryType"+ strQryType);
            
        
        FOISTuxedo SRVCNAME = null;
             try
             {
                     logger.info("Calling Tuxedo");
                     SRVCNAME = new FOISTuxedo();
                     logger.info("Client Object Got.");
             }
             catch (Exception e)
             {
                     logger.info("Tuxedo Connnection Failed.");
                 strCallStts="F";
                                   strCallMesg="Unable to write to buffer";
                                   return null;
             }
            if(strQryType.equals("ODR_RK_OTSG"))
                    {   
                             logger.info("IN ODR QUERY");
                        String strRowCont1="0";
                        String strRowCont2="0";
             try
             {
                     logger.info("Calling tuxInit.");

                     SRVCNAME.tuxInit("RQODRRKOTSGDTLS");
                     SRVCNAME.setString("F_USERID", 0, si_UserId);
                     SRVCNAME.setString("F_CLNTID", 0,  si_ClntId);
                     
                        SRVCNAME.setString("F_DVSN", strZone);
                                        SRVCNAME.setString("F_FLAG", "Z");
                                        SRVCNAME.setString("F_OPTN", "OD");
                                        SRVCNAME.setString("F_ORGNGAUG", "1");
                                        SRVCNAME.setString("F_PRTYCLSS", "");
                                        SRVCNAME.setString("F_CMDT", "");
                                        SRVCNAME.setString("F_STTNTO", "");
                                        SRVCNAME.setString("F_CNSR", "");
                                        SRVCNAME.setString("F_CNSG", "");
                                        SRVCNAME.setString("F_STCKTYPE", "");
                                        SRVCNAME.setString("F_RTFRFLAG", "");
                                
                     logger.info("Called Tux_Fchg.");
                     SRVCNAME.call("N");
                     logger.info("Called Tux_Call.");
                 String erorCode1                        = null;
                            try
                            {
                                    erorCode1                               = SRVCNAME.getStringItemDef("F_ERORNO",0,"0");
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
                    strRowCont1         = SRVCNAME.getStringItemDef("F_ROWCONT",        0,"0");
                                strRowCont2             = SRVCNAME.getStringItemDef("F_ROWCONT1",       0,"0");
                                        
                                logger.info("F_ROWCONT1 :"+ strRowCont1);
                                logger.info("F_ROWCONT2 :"+ strRowCont2);
                                
                                int intRowCount=Integer.parseInt(strRowCont1);  
                                
                                strDetails = new String[intRowCount+1][41];
                                String strTotal1="0";
                                String strTotal2="0";
                                String strTotal3="0";
                                String strTotal4="0";
                                String strTotal5="0";
                                
                                for (int k=0;k<intRowCount;k++)
                                {
                                        strDetails[k][0] = SRVCNAME.getStringItemDef("F_DVSNFROM", k, "0").trim();
                                        strDetails[k][1] = SRVCNAME.getStringItemDef("F_STTNFROM", k, "0").trim();
                                        strDetails[k][2] = SRVCNAME.getStringItemDef("F_DMNDNUMB", k, "0").trim();
                                        strDetails[k][3] = SRVCNAME.getStringItemDef("F_DMNDDATE", k, "0").trim();
                                        strDetails[k][4] = SRVCNAME.getStringItemDef("F_DMNDTIME", k, "0").trim();
                                        strDetails[k][5] = SRVCNAME.getStringItemDef("F_CNSR", k, "0").trim();
                                        strDetails[k][6] = SRVCNAME.getStringItemDef("F_CNSG", k, "0").trim();
                                        strDetails[k][7] = SRVCNAME.getStringItemDef("F_CMDT", k, "0").trim();
                                        strDetails[k][8] = SRVCNAME.getStringItemDef("F_SPCLNUMB", k, "0").trim();
                                        strDetails[k][9] = SRVCNAME.getStringItemDef("F_TRFCTYPE", k, "0").trim();
                                        strDetails[k][10] = SRVCNAME.getStringItemDef("F_PRTYCLSS", k, "0").trim();
                                        strDetails[k][11] = SRVCNAME.getStringItemDef("F_PRNTFLAG", k, "0").trim();
                                        strDetails[k][12] = SRVCNAME.getStringItemDef("F_QUTA", k, "0").trim();
                                        strDetails[k][13] = SRVCNAME.getStringItemDef("F_ODC", k, "0").trim();
                                        strDetails[k][14] = SRVCNAME.getStringItemDef("F_RTFRFLAG", k, "0").trim();
                                        strDetails[k][15] = SRVCNAME.getStringItemDef("F_VIA", k, "0").trim();
                                        strDetails[k][16] = SRVCNAME.getStringItemDef("F_STTNTO", k, "0").trim();
                                        strDetails[k][17] = SRVCNAME.getStringItemDef("F_STCKTYPE", k, "0").trim();
                                        strDetails[k][18] = SRVCNAME.getStringItemDef("F_UNTS", k, "0").trim();
                                        strDetails[k][19] = SRVCNAME.getStringItemDef("F_FRWH", k, "0").trim();
                                        strDetails[k][20] = SRVCNAME.getStringItemDef("F_LOADFRWH", k, "0").trim();
                                        strDetails[k][21] = SRVCNAME.getStringItemDef("F_CNCLFRWH", k, "0").trim();
                                        strDetails[k][22] = SRVCNAME.getStringItemDef("F_FRFTFRWH", k, "0").trim();
                                        strDetails[k][23] = SRVCNAME.getStringItemDef("F_OTSGUNTS", k, "0").trim();
                                        strDetails[k][24] = SRVCNAME.getStringItemDef("F_OTSGFRWH", k, "0").trim();
                                        strDetails[k][25] = SRVCNAME.getStringItemDef("F_PLCDUNTS", k, "0").trim();
                                        strDetails[k][26] = SRVCNAME.getStringItemDef("F_PLCTTIME", k, "0").trim();
                                        strDetails[k][27] = SRVCNAME.getStringItemDef("F_DMNDDTLSID", k, "0").trim();
                                        strDetails[k][28] = SRVCNAME.getStringItemDef("F_DMNDDATE", k, "0").trim();
                                        strDetails[k][29] = SRVCNAME.getStringItemDef("F_RAKECMDT", k, "0").trim();

                                 strTotal1=(Integer.parseInt(strTotal1)+Integer.parseInt(strDetails[k][18]))+"";
                                 strTotal2=(Integer.parseInt(strTotal2)+Integer.parseInt(strDetails[k][19]))+"";
                                 strTotal3=(Integer.parseInt(strTotal3)+Integer.parseInt(strDetails[k][23]))+"";
                                 strTotal4=(Integer.parseInt(strTotal4)+Integer.parseInt(strDetails[k][24]))+"";
                                 strTotal5=(Integer.parseInt(strTotal5)+Integer.parseInt(strDetails[k][25]))+"";
                                        
                                }
                                strDetails[intRowCount][18] =strTotal1;
                                strDetails[intRowCount][19] =strTotal2;
                                strDetails[intRowCount][23] =strTotal3;
                                strDetails[intRowCount][24] =strTotal4;
                                strDetails[intRowCount][25] =strTotal5;
                     
                     SRVCNAME.endSession();
                     logger.info("End SRVCNAME.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                 strCallMesg="Problem in Data Filling";
                                 return null;

             }
             finally
             {
             try
             {
                     SRVCNAME=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                 strCallMesg="Problem in Data Filling";
                                 return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||SRVCNAME");
  
     }
     if(strQryType.equals("MATURED_INDENTS"))
                    {   
                             logger.info("IN MATUERD INDENTS QUERY");
                        String strRowCont1="0";
                        String strRowCont2="0";
             try
             {
                     logger.info("Calling tuxInit.");

                     SRVCNAME.tuxInit("RQODRRKOTSGDTLS");
                     SRVCNAME.setString("F_USERID", 0, si_UserId);
                     SRVCNAME.setString("F_CLNTID", 0,  si_ClntId);
                     
                            SRVCNAME.setString("F_DVSN", strZone);
                                        SRVCNAME.setString("F_FLAG", "Z");
                                        SRVCNAME.setString("F_OPTN", "MI");
                                        SRVCNAME.setString("F_ORGNGAUG", "1");
                                        SRVCNAME.setString("F_PRTYCLSS", "");
                                        SRVCNAME.setString("F_CMDT", "");
                                        SRVCNAME.setString("F_STTNTO", "");
                                        SRVCNAME.setString("F_CNSR", "");
                                        SRVCNAME.setString("F_CNSG", "");
                                        SRVCNAME.setString("F_STCKTYPE", "");
                                        SRVCNAME.setString("F_RTFRFLAG", "");
                                
                     logger.info("Called Tux_Fchg.");
                     SRVCNAME.call("N");
                 String erorCode1                        = null;
                            try
                            {
                                    erorCode1                               = SRVCNAME.getStringItemDef("F_ERORNO",0,"0");
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
                     logger.info("Called Tux_Call.");
                   strRowCont1          = SRVCNAME.getStringItemDef("F_ROWCONT1",       0,"0");
                                        
                                logger.info("F_ROWCONT1 :"+ strRowCont1);
                                
                                int intRowCount=Integer.parseInt(strRowCont1);  
                                
                                strDetails = new String[intRowCount+1][41];
                                String strTotal1="0";
                                String strTotal2="0";
                                
                                for (int k=0;k<intRowCount;k++)
                                {
                                        strDetails[k][0] = SRVCNAME.getStringItemDef("F_EXITSCTN", k, "0").trim();
                                        strDetails[k][1] = SRVCNAME.getStringItemDef("F_EXITSTTN", k, "0").trim();
                                        strDetails[k][2] = SRVCNAME.getStringItemDef("F_DMNDNUMBRAKE", k, "0").trim();
                                        strDetails[k][3] = SRVCNAME.getStringItemDef("F_ENTYDATE", k, "0").trim();
                                        strDetails[k][4] = SRVCNAME.getStringItemDef("F_ENTYDOMN", k, "0").trim();
                                        strDetails[k][5] = SRVCNAME.getStringItemDef("F_ENTYEVNT", k, "0").trim();
                                        strDetails[k][6] = SRVCNAME.getStringItemDef("F_ENTYKM", k, "0").trim();
                                        strDetails[k][7] = SRVCNAME.getStringItemDef("F_ENTYKMS", k, "0").trim();
                                        strDetails[k][8] = SRVCNAME.getStringItemDef("F_ENTYLOAD", k, "0").trim();
                                        strDetails[k][9] = SRVCNAME.getStringItemDef("F_ENTYMLUN", k, "0").trim();
                                        strDetails[k][10] = SRVCNAME.getStringItemDef("F_ENTYSCTN", k, "0").trim();
                                        strDetails[k][11] = SRVCNAME.getStringItemDef("F_ENTYSRVC", k, "0").trim();
                                        strDetails[k][12] = SRVCNAME.getStringItemDef("F_ENTYSTTN", k, "0").trim();
                                        strDetails[k][13] = SRVCNAME.getStringItemDef("F_ENTYSTTS", k, "0").trim();
                                        strDetails[k][14] = SRVCNAME.getStringItemDef("F_ENTYTIME", k, "0").trim();
                                        strDetails[k][15] = SRVCNAME.getStringItemDef("F_ENTYTRAN", k, "0").trim();
                                        strDetails[k][16] = SRVCNAME.getStringItemDef("F_EOLFLAG", k, "0").trim();
                                        strDetails[k][17] = SRVCNAME.getStringItemDef("F_EOTTIME", k, "0").trim();
                                        strDetails[k][18] = SRVCNAME.getStringItemDef("F_EVNTFLAG", k, "0").trim();
                                        strDetails[k][19] = SRVCNAME.getStringItemDef("F_EXAGFLAG", k, "0").trim();
                                        strDetails[k][20] = SRVCNAME.getStringItemDef("F_EXAMSTTN", k, "0").trim();
                                        strDetails[k][21] = SRVCNAME.getStringItemDef("F_EXAMTYPE", k, "0").trim();
                                        strDetails[k][22] = SRVCNAME.getStringItemDef("F_EXCPFRWH", k, "0").trim();
                                        strDetails[k][23] = SRVCNAME.getStringItemDef("F_EVNTTIME", k, "0").trim();
                                        strDetails[k][24] = SRVCNAME.getStringItemDef("F_EXAGCODE", k, "0").trim();
                                        strDetails[k][25] = SRVCNAME.getStringItemDef("F_EXAMDATE", k, "0").trim();
                                        strDetails[k][26] = SRVCNAME.getStringItemDef("F_METWITHDATE", k, "0").trim();
                                        strDetails[k][27] = SRVCNAME.getStringItemDef("F_ORDRBY1", k, "0").trim();
                                        strDetails[k][28] = SRVCNAME.getStringItemDef("F_ORDRBY2", k, "0").trim();
                                        strDetails[k][29] = SRVCNAME.getStringItemDef("F_INSGRAKECMDT", k, "0").trim();
                                strTotal1=(Integer.parseInt(strTotal1)+Integer.parseInt(strDetails[k][18]))+"";
                                strTotal2=(Integer.parseInt(strTotal2)+Integer.parseInt(strDetails[k][19]))+"";

                                 }
                                strDetails[intRowCount][18] =strTotal1;
                                strDetails[intRowCount][19] =strTotal2;
        
                     
                     SRVCNAME.endSession();
                     logger.info("End SRVCNAME.");
             }
             catch (Exception e)
             {
                     logger.info("There was an exception while creating and using the FOIS."+e);
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;

             }
             finally
             {
             try
             {
                     SRVCNAME=null;
             }
             catch(Exception e)
             {
                     logger.info("Errror In Finally.");
                 strCallStts="F";
                                strCallMesg="Problem in Data Filling";
                                return null;
             }
        }
             
             logger.info("SUCCESSFULL");
    logger.info("Sucessfull Execution of SHY_OdrRasTX ||SRVCNAME");
    
     }
        return strDetails;
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
