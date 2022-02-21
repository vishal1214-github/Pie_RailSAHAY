package model;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.exception.GG_Exception;

import util.logger.FoisLogger;

public class FeedBackRatings {
    public FeedBackRatings() {
        super();
    }

    private String strCallStts="S";
    private String strCallMesg="";
    static Logger logger = FoisLogger.getLogger(SHY_UserDashboardTX.class.getName());
    String erorCode                     = null;
    public static Ratings SHY_RatingContTX(String si_ClntId)
    throws GG_Exception
    {
                 
        Ratings cont   = new Ratings(); 
        
        logger.info("Entering SHY_RatingContTX");
        
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
                        RQSAHAYPRTL.tuxInit("RQSAHAYMISC");
            
            RQSAHAYPRTL.setString("F_USERID",   0,   "TEST");
            RQSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
            RQSAHAYPRTL.setString("F_FLAG",     0,   "SAHAY_FDBK");
                
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
                                    int start1         = 1;
                                 
                                    logger.info("start1 : " + start1);
            
                                 
                                    String totlCont="";
                                    String avgCont="";
                                    String Os="";
                                    String Ts="";
                                    String ThS="";
                                    String Fs="";
                                    String FvS="";
                         
                                        int otlRatings= 0;
                                        double avgRating= 0.0;
                                        int oneStar= 0;
                                        double oneStarP= 0.0;
                                        int twoStar= 0;
                                        double twoStarP= 0.0;
                                        int threeStar= 0;
                                        double threeStarP= 0.0;
                                        int fourStart= 0;
                                        double fourStarP= 0.0;
                                        int fiveStar= 0;
                                        double fiveStarP= 0.0;
            
                                    
                                        
                                        totlCont         = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",   0,      "0").trim();
                                        avgCont       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",   0,      "0").trim();
                                        Os       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",   0,      "0").trim();
                                        Ts       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",   0,      "0").trim();
                                        ThS       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",   0,      "0").trim();
                                        Fs       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",   0,      "0").trim();
                                        FvS       = RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",   0,      "0").trim();
                                          
                                      otlRatings= Integer.parseInt(totlCont);
                                      avgRating=Double.parseDouble(avgCont);
                                      oneStar= Integer.parseInt(Os);
                                      twoStar=Integer.parseInt(Ts);
                                      threeStar=Integer.parseInt(ThS);
                                      fourStart=Integer.parseInt(Fs);
                                      fiveStar=Integer.parseInt(FvS);
                                      
                                      int totlRatings=0;
                                        
                         if(otlRatings==0){
                             totlRatings=1;
                         }else{
                             totlRatings=otlRatings;
                         }
                                        
                                      oneStarP= ((double)oneStar/(double)totlRatings)*100;
                                      twoStarP= ((double)twoStar/(double)totlRatings)*100;
                                      threeStarP= ((double)threeStar/(double)totlRatings)*100;
                                      fourStarP= ((double)fourStart/(double)totlRatings)*100;
                                      fiveStarP= ((double)fiveStar/(double)totlRatings)*100;
                         
                             System.out.println("oneStarP="+oneStarP+"||oneStar="+oneStar+"||otlRatings"+otlRatings);
                                        
                                        cont.setAvgRating(avgRating);
                                      cont.setFiveStar(fiveStar);
                                      cont.setFiveStarP(fiveStarP);
                                      cont.setFourStarP(fourStarP);
                                      cont.setFourStart(fourStart);
                                      cont.setOneStar(oneStar);
                                      cont.setOneStarP(oneStarP);
                                      cont.setOtlRatings(otlRatings);
                                      cont.setThreeStar(threeStar);
                                      cont.setThreeStarP(threeStarP);
                                      cont.setTwoStar(twoStar);
                                      cont.setTwoStarP(twoStarP);
                                      
                                       
                                   // End of for Loop
                                                
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
                        return cont;
    }
    
    public String[][] getRatingComments(String si_UserID,String si_ClntID, String si_Rtng) 
    {
            String strData[][] = null;   // Variable to be returned as output of function

            
            String strRmrk="";
            logger.info("Entering getLdngLocn....");
            logger.info("Function called with inputs :");
            logger.info("si_UserID  #"+si_UserID+"#");
            logger.info("si_ClntID  #"+si_ClntID+"#");
            logger.info("si_Rtng  #"+si_Rtng+"#");

            FOISTuxedo RQSAHAYPRTL  = null;

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
                    RQSAHAYPRTL.tuxInit("RQSAHAYMISC");
                    RQSAHAYPRTL.setString("F_USERID",               0,      si_UserID);
                    RQSAHAYPRTL.setString("F_CLNTID",               0,      si_ClntID);
                    RQSAHAYPRTL.setString("F_FLAG",         0,      "SAHAY_RTNG");
                    RQSAHAYPRTL.setString("F_HLDGZONE",             0,      si_Rtng);
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
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
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
                    startRowCount1          = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
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

            strData = new String[start1][3];
            
            logger.info("Start reading data for Loading Locations");
    
            for(int i=0; i<=start1-1; i++)
            {
                    try
                    {
                            strData[i][0]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData[i][1]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
                            strData[i][2]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
                        System.out.println("strData[i][0]"+strData[i][0]);
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

    public void setStrCallStts(String strCallStts) {
        this.strCallStts = strCallStts;
    }

    public String getStrCallStts() {
        return strCallStts;
    }

    public void setStrCallMesg(String strCallMesg) {
        this.strCallMesg = strCallMesg;
    }

    public String getStrCallMesg() {
        return strCallMesg;
    }
}
