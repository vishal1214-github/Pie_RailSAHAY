package servlet.AppData;

import commonj.work.Work;
import commonj.work.WorkItem;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import tuxedo.FOISTuxedo;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;


public class efntOTP {
    static Logger logger = FoisLogger.getLogger(efntOTP.class.getName());
    static Connection currentCon = null;
    static ResultSet rs = null;
    static PreparedStatement ps=null;

    public efntOTP() {
           super();
               vldtTime    =   "OTP_VLDT_TIME";
       }    

       public efntOTP(String eFlag) {
           if(eFlag.equals("etrr"))
               this.vldtTime = "ETRR_OTP_VLDT";
           else
               this.vldtTime    =   "OTP_VLDT_TIME";
       }
    private String tavcustid;
    private String onflag; //O-for repeating last OTP and N for new otp M for simple message
    private String mesg;
    private String otp;
    private String tavmobl;
    private String result = "";
    private String vldtTime;
    public String efntOTPSend(String tavcustid,String tavmobl, String onflag, String mesg) {
        // in case of otp mesg will not be used and for other cases where 'M' value comes in  onflag mesg will be considered and send to the user
        
        this.tavcustid = tavcustid;
        this.onflag = onflag;
        this.mesg = mesg;
        this.tavmobl=tavmobl;
        if (getOnflag() != null && (getOnflag().equals("N"))) {
            result = createnewotp();
     //       System.out.println("Inside generate new OTP ");
        }
        logger.info(":result:"+result+":");
        if (getOnflag() != null &&  result.equals("Y")) {
            result="";
            result = sendotp();
           logger.info("Inside after generate new OTP ");
            if( result.equals("Y")) {
                logger.info("Inside after send otp new OTP ");
                result="";
                result=savedata();
            }
        } else {
            if (!result.equals("Y"))
                logger.info("OTP NOT GENERATED");
            //Other cases can be handeled in this section for future use
            logger.info("Invalid call Made");
            result = "Invalid Call";
        }
        NDC.pop();
        NDC.remove(); 
        logger.info("efnotpsend:result:"+result+":");
        return result;
    }


    public void setTavcustid(String tavcustid) {
        this.tavcustid = tavcustid;
    }

    public String getTavcustid() {
        return tavcustid;
    }

    public void setOnflag(String onflag) {
        this.onflag = onflag;
    }

    public String getOnflag() {
        return onflag;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getMesg() {
        return mesg;
    }

private String createnewotp() {
        String result = "";
        logger.info("Creating New OTP");
        //set all old OTP for th customer at invalid state
        currentCon = dbConnection.getConnection();
         try {
            ps =
 currentCon.prepareStatement("UPDATE TED_EFNTOTP SET TACVLDTFLAG ='N' WHERE TAVCUSTID=? AND TACVLDTFLAG='Y'");
            ps.setString(1, this.tavcustid);
            int updtrow= ps.executeUpdate();
            //with zero update count nothing to do
            if (rs != null) {
                logger.info("rs exists1.");

                rs.close();
            }
            logger.info("UPDATE COMPLETE");
            //add new record with new otp with given message at valid state
            ps =
 currentCon.prepareStatement("INSERT INTO TED_EFNTOTP (TAVCUSTID ,TAVOTP,TACVLDTFLAG ,TACSCSSFLAG ,TAVMESG ,TADTRXNTIME ,TAVRMRK ,  TADLASTSENDTIME ,TAVSRVCTYPE)VALUES  (?,TRUNC(DBMS_RANDOM.VALUE(10000,99999)),'Y','N',?,SYSDATE,NULL,NULL, NULL)");
            ps.setString(1, getTavcustid());
            ps.setString(2, "OTP GENERATED");
            logger.info(ps.toString());
            int insrttrow= ps.executeUpdate();
            logger.info("INSERTRED ROW COMPLETE::"+insrttrow);
            if (rs != null) {
                logger.info("rs exists2.");
                rs.close();
            }
            if (ps != null) {
                logger.info("rs exists2.");
                ps.close();
            }
            logger.info("INSERT COMPLETE");
            currentCon.commit();
            currentCon.close();
            result="Y";
        } catch (SQLException e) {
            result = "ERROR IN GENERATING OTP";
            e.printStackTrace();
          try {
                currentCon.rollback();
                currentCon.close();
            } catch (SQLException f) {
                result = "ERROR IN CLOSING CONNECTION";
                f.printStackTrace();
            }
          }finally {
    try { if (rs != null) rs.close(); } catch (Exception e) {};
    try { if (ps != null) ps.close(); } catch (Exception e) {};
    try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
}

        return result;
    }


    public String getOtp() {
        return otp;
    }
    public boolean verifyOTP(String tavcustid,String userotp) {
           boolean otpverifiedflag = false;
           String otpvaldflag   = "";
           String otprmrk       ="";
           logger.info("Verifying OTP:" + userotp +":tavcustid:"+tavcustid+":");
         /*  currentCon = EfntConHandler.getLocalConnection();
           PreparedStatement ps=null;
           int intvl=0;
           try {
               ps =
               currentCon.prepareStatement("   SELECT TO_NUMBER(SAVVALU) INTVL FROM SEM_DATEPARAM     " +
                   "WHERE SADEFCTFROM <= SYSDATE         " +
                   "AND NVL(SADEFCTTO,SYSDATE)>=SYSDATE    " +
                   "AND  SAVCODE='"+vldtTime+"'");
               rs = ps.executeQuery();
               while (rs.next()) {
                      intvl = rs.getInt("INTVL");
                   logger.info("SELECTED Record Count" + intvl);
               }
               if (rs != null) {
                   logger.info("rs exists3.11.");
                   rs.close();
               }
                if (ps != null) {
                 //   logger.info("rs exists3.11.");
                    ps.close();
                }
               ps =
           currentCon.prepareStatement("SELECT TAVOTP OTP FROM TED_EFNTOTP WHERE TAVCUSTID= ? AND TAVOTP =? AND TACVLDTFLAG='Y' AND ((SYSDATE-TADLASTSENDTIME) *24*60)<= ? ");
               ps.setString(1, tavcustid);
               ps.setString(2, userotp);
               ps.setInt(3, intvl);
               rs = ps.executeQuery();
               //  logger.info("SELECTED Record Count" + rs.getRow());
               int row = 0;
               while (rs.next()) {
                   
                   String user = rs.getString("OTP");
                   logger.info("OTP::" + user);
                   row++;

               }
               if (rs != null) {
                   logger.info("rs exists1.");
                   rs.close();
               }
                if (ps != null) {
                 //   logger.info("rs exists3.11.");
                    ps.close();
                }
               logger.info("SELECTED Record Count" + row);
               if(row==0) 
                   otpverifiedflag =   false;
               else    
                   otpverifiedflag =   true;
           } catch (SQLException e) {
                   otpverifiedflag =   false;
                   try {
                       currentCon.close();
                   } catch (SQLException f) {
                       f.printStackTrace();
                   }
                   e.printStackTrace();
                   }finally {
           try { if (rs != null) rs.close(); } catch (Exception e) {};
           try { if (ps != null) ps.close(); } catch (Exception e) {};
           try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
           }
           return otpverifiedflag;
         */
         FacesMessage msg = null;
         FOISTuxedo TTVRFYOTP = null;
          try {
                  logger.info("295:Calling Tuxedo");
                  TTVRFYOTP = new FOISTuxedo();
                  logger.info("295:Cliet Object Got.");
                  TTVRFYOTP.tuxInit("TTVRFYOTP");
          } catch (Exception ne) {
                  logger.info("295:Unable to get the Client Object: " + ne.toString());
          }
          try
          {
                  TTVRFYOTP.setString("F_TRXNUSERID",0, tavcustid.toUpperCase());
                  TTVRFYOTP.setString("F_USERID"    ,0, "SYSTEM");
                  TTVRFYOTP.setString("F_CLNTID"    ,0, "SYSTEM");
                  TTVRFYOTP.setString("F_SRVCNAME"    ,0, "ETRR");
                  TTVRFYOTP.setString("F_DOMN"    ,0, userotp);
                  TTVRFYOTP.setString("F_DMNDDTLSGRUPID"    ,0,"");
                  TTVRFYOTP.setString("F_STTNFROM"    ,0, "");
                  TTVRFYOTP.setString("F_ERORNO"    ,0, "");
          }
          catch (Exception e) {
                  logger.info("295:Unable to write to buffer : " + e.toString());
                  throw new FacesException("Unable to write to buffer");
          }
          try {
                  logger.info("295::Calling Tuxedo Service TTVRFYOTP ...");
                  TTVRFYOTP.call("N");
                  logger.info("295::CALL COMPLETED !");
          } catch (Exception e) {
                  logger.info("295:Exception while call to the service is " + e.toString());
                  e.printStackTrace();
                  throw new FacesException(e);
          }
          String erorCode = null;
          try {
                  erorCode = TTVRFYOTP.getStringItemDef("F_ERORNO", 0, "0");
          }
          catch (Exception e) {
                         logger.info("295::Exception while call to the service is " + e.toString());
                         e.printStackTrace();
                         //throw new FacesException(e);
                 }
         /*try {
                 erorCode = TTVRFYOTP.getStringItemDef("F_ERORNO", 0, "0");
                 logger.info("295:"+getReqsessionid().substring(0,25)+":erorCode:"+erorCode);
                 //msg = new FacesMessage("Demand Registered Successfully");
         }
         catch (Exception e) {
             logger.info("295:"+getReqsessionid().substring(0,25)+":in DmndDtlsTableBean erorCode..");
             msg = new FacesMessage("ERROR in Verification");
             e.printStackTrace();
         }*/
         logger.info("295:erorCode:"+erorCode); 
         if(erorCode==null)
         {       
         try{
                         otpvaldflag = TTVRFYOTP.getStringItemDef("F_VALDFLAG", 0, "0");
                         otprmrk = TTVRFYOTP.getStringItemDef("F_RMRK", 0, "0");
                         logger.info("295:otpvaldflag:"+otpvaldflag);
                     }
                     catch(Exception e) {
                         e.printStackTrace();
                     }

                 /*else
                 {
                     otpvaldflag="N";
                     otprmrk = erorCode;
                 }*/
         }
         else
         {
             if(erorCode.equals("")) {
                 try{
                     otpvaldflag = TTVRFYOTP.getStringItemDef("F_VALDFLAG", 0, "0");
                     otprmrk = TTVRFYOTP.getStringItemDef("F_RMRK", 0, "0");
                     logger.info("295:otpvaldflag:"+otpvaldflag);
                 }
                 catch(Exception e) {
                     e.printStackTrace();
                 }
             }
             else{
             otpvaldflag="N";
             otprmrk = erorCode;
             }
         }
         if(otpvaldflag.equals("N")) 
                otpverifiedflag =   false;
            else    
                otpverifiedflag =   true;
         
         return otpverifiedflag;
       }
    private static Context getInitialContext() throws NamingException {
        
            Properties properties=new Properties();
            try{
                    //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
                    properties.load(new FileInputStream("EmailSrvcLogs.properties"));


            }catch(Exception ex)  
            {
                    System.out.println("Could not load the property file WebsrvcLogs");
            }
            
           String beanurl=properties.getProperty("url");
            
            Hashtable env = new Hashtable();
            // WebLogic Server 10.x connection details
            env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
         //   env.put(Context.PROVIDER_URL, "t3://172.16.4.62:7711");
           env.put(Context.PROVIDER_URL, beanurl);
            return new InitialContext( env );
        }
    public String sendMail(String mailto, String mailcc, String subject, String content, String mailid, String password,
                    String smtpHost, ByteArrayOutputStream baos,String atmtFlag, Logger logger,String fileName){
        String response="N";
        try {
            Context context = getInitialContext();
             logger.info("Reached to send OTP as Email");
            try{
                commonj.work.WorkManager wm = (commonj.work.WorkManager)context.lookup("java:comp/env/wm/MailWM");
            Work work =
            new SendMail(mailto,mailcc,subject,content,mailid,password,
                    smtpHost, baos,atmtFlag, logger,fileName);
             WorkItem workItem = wm.schedule(work);
            }
           catch (IllegalArgumentException iae) {
                        // TODO: Add catch code
                        iae.printStackTrace();
            } 
            catch (NamingException ne) {
                        // TODO: Add catch code
                        ne.printStackTrace();
            }  
           
          //  response=sendmail(mailto, "", "OTP For FBD Transaction", "<b>Dear User,</b><br/>"+this.mesg+"<br/>", "N", "", "");
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            } finally{
                return response;
            }
    }
    private String sendotp() {
        String result = "";
        //send otp from table of last valid state otp
        logger.info("Sending OTP");
        currentCon = dbConnection.getConnection();
        PreparedStatement ps=null;
        int intvl=0;
        logger.info("Getting Validation Interval for "+vldtTime);
        try {
            ps =
            currentCon.prepareStatement("            SELECT TO_NUMBER(SAVVALU) INTVL FROM SEM_DATEPARAM     " +
                            "WHERE SADEFCTFROM <= SYSDATE         " +
                            "AND NVL(SADEFCTTO,SYSDATE)>=SYSDATE    " +
                            "AND  SAVCODE='"+vldtTime+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                
                   intvl = rs.getInt("INTVL");
                logger.info("SELECTED Record Count" + intvl);
                    
            }
            if (rs != null) {
                logger.info("rs exists3.11.");
                rs.close();
            }
            if (ps != null) {
                logger.info("ps exists2.");
                ps.close();
            }
            ps =
        currentCon.prepareStatement("SELECT TAVOTP PASSWORD FROM TED_EFNTOTP WHERE TAVCUSTID=? AND TACVLDTFLAG='Y'");
            ps.setString(1, this.tavcustid);
            rs = ps.executeQuery();
            while (rs.next()) {
                logger.info("SELECTED Record Count" + rs.getRow());
                    this.otp = rs.getString("PASSWORD");
                    logger.info("Password::"+this.otp);
                    
            }
            if (rs != null) {
                logger.info("rs exists3.");
                rs.close();
            }
            if (ps != null) {
                logger.info("ps exists2.");
                ps.close();
            }
            currentCon.close();
            this.mesg ="Your One Time Password(OTP) for confirming the transaction is "+ this.otp+" . It is valid for 15 mins. PLEASE DO NOT SHARE THE OTP WITH ANYONE. CRIS/IR";
            
            result=sendSMS();
            
            
        } catch (SQLException e) {
            result="ERROR IN SENDING SMS";
            try {
                currentCon.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            e.printStackTrace();
            }finally {
    try { if (rs != null) rs.close(); } catch (Exception e) {};
    try { if (ps != null) ps.close(); } catch (Exception e) {};
    try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
}

       
       
        
       
        return result;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    private String sendSMS() {
        String 	result="";
        String REG_TIME="\\d{2}-\\d{2}-\\d{4}\\s+\\d{2}:\\d{2}:\\d{2}";
         Matcher matcher;
        Pattern pattern;
        try {
            logger.info("HareKrishna::Reached to send SMS1");
            SendSMS sendSMS = new SendSMS(logger);
            result = sendSMS.callBean(this.tavmobl, this.mesg);
            if(result.contains("success"))
                    result = "Y";
            logger.info("HareKrishna::Reached to send SMS3::"+result);
         
        }
        catch (Exception e) {
            logger.info("HareKrishna::Reached to Naming CreateException error SMS");
            e.printStackTrace();
        } 

        return result;
    }

    public void setTavmobl(String tavmobl) {
        this.tavmobl = tavmobl;
    }

    public String getTavmobl() {
        return tavmobl;
    }

    private String savedata() {
        String result="";
        // update last send time,remark,scss flag as per case
        logger.info("Updating DataBase");
        currentCon = dbConnection.getConnection();
        PreparedStatement ps=null;
     try {
            ps =
        currentCon.prepareStatement("UPDATE TED_EFNTOTP SET TACSCSSFLAG='Y',TAVMESG=?,TADLASTSENDTIME=SYSDATE,TAVRMRK='SEND SUCCESSFULLY' WHERE TAVCUSTID=? AND TAVOTP=? AND TACVLDTFLAG='Y'");
            ps.setString(1, this.mesg);
            ps.setString(2, this.tavcustid);
            ps.setString(3, this.otp);
           int rowCont = ps.executeUpdate();
         logger.info("ROWCOUNT UPDATED::"+rowCont);
            if (rs != null) {
                    logger.info("rs exists2.");
                    rs.close();
            }
            if (ps != null) {
                logger.info("ps exists2.");
                ps.close();
            }
            currentCon.commit();
            currentCon.close();
         result="Y";
        } catch (SQLException e) {
            result="ERROR IN UPDATING DATABASE";
         e.printStackTrace();
            try {
                currentCon.rollback();
                currentCon.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            
        }finally {
    try { if (rs != null) rs.close(); } catch (Exception e) {};
    try { if (ps != null) ps.close(); } catch (Exception e) {};
    try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
}

       
        
        return result;
    }

    public void setVldtTime(String vldtTime) {
        this.vldtTime = vldtTime;
    }

    public String getVldtTime() {
        return vldtTime;
    }
}
