package servlet.AppData;


import ice.util.net.URLEncoder;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.text.DateFormat;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import java.util.Base64;

import javax.crypto.SecretKey;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;


public class UserRgtn extends HttpServlet {
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
    static Connection currentCon = null;
    static ResultSet rs = null;
    static Logger logger = FoisLogger.getLogger(UserRgtn.class.getName());
    private static SecretKeySpec secretKey;
       private static byte[] key;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
      //  logger.info("Inside chkAvailability" + tavcustuserid);


        String strfrwd = "";
        PreparedStatement ps=null;
        String registrationSuccess = "N";
        String plainDatacustid="",plainDataemail="",plainDataname="",cipherTextcustid="",cipherTextemail="",cipherTextname="",decryptedText="";
                  
        String firstname=(String)request.getParameter("firstname").toUpperCase();
        String lastname=(String)request.getParameter("lastname").toUpperCase();
        String email=(String)request.getParameter("email").toUpperCase();
        String mobile=(String)request.getParameter("mobile");
        String Pswd=(String)request.getParameter("Pswd");
        String CPswd=(String)request.getParameter("CPswd");
        logger.info("firstname::"+firstname);
        logger.info("lastname::"+lastname);
        logger.info("email::"+email);
        logger.info("mobile::"+mobile);
        currentCon = dbConnection.getConnection();

        DecimalFormat mFormat = new DecimalFormat("00");
        DecimalFormat yFormat = new DecimalFormat("0000");
        String strYear = yFormat.format(Double.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        String strMnth = mFormat.format(Double.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1));
        String strUserId = "";
        int intLastNumb = 0;
        String slctefntLastNumb =
            "select SANLASTNUMB from  SEM_LASTEFNTNUMB where SANYEAR=" + strYear + " and SANMNTH=" + strMnth +
            " for update ";
        try {
            Statement stmt = currentCon.createStatement();

            ResultSet rsQry = stmt.executeQuery(slctefntLastNumb);
            while (rsQry.next()) {
                intLastNumb = rsQry.getInt("SANLASTNUMB");
            }

            intLastNumb++;
            /* UPDATE OR INSERT LAST NUMBER RECORD STARTS */
            String updtLastNumb =
                "UPDATE SEM_LASTEFNTNUMB SET SANLASTNUMB=SANLASTNUMB+1 WHERE " + " SANYEAR=" + strYear +
                " and SANMNTH=" + strMnth;
            logger.info("updtLastNumb::" + updtLastNumb);

            int rsupdt = stmt.executeUpdate(updtLastNumb);
            if (rsupdt == 0) {
                logger.info("Update Failed");
                String insrtLastNumb =
                    "insert into SEM_LASTEFNTNUMB values(" + strYear + "," + strMnth + "," + intLastNumb + ")";
                logger.info("insrtLastNumb::" + insrtLastNumb);
                if (stmt.execute(insrtLastNumb)) {
                    logger.info("Insertion Failed");
                    currentCon.rollback();
                    currentCon.close();
                }
            }

            /* UPDATE OR INSERT LAST NUMBER RECORD ENDS */
            strUserId = "FD" + strYear + strMnth + yFormat.format(Double.valueOf(intLastNumb));
        
        
        try {
            
            
            String sql =
                "INSERT INTO TEL_FBDCUSTLGIN (TAVCUSTID , " + "TAVCUSTEMALID ," + "TANCUSTMOBLNUMB , " + "TAVCUSTPSWD ,          " +
                "TAVCUSTFRSTNAME   ,     " + "TAVCUSTLASTNAME  ,     " + "TAVCUSTSTTS  ,     " +
                "TADLASTSTTSDATE  ,    " + "TAVCUSTUSERID   ,      " + "TADEFCTFROM   ,     " + "TADEFCTTO  ,   " +
                "TADPSWDEXPYDATE ,  " + "TADLASTLGINDATE , " + "TANUNSCLGIN ) " + "Values ('" + strUserId  +
                "','" + email + "','" + mobile + "',LTRIM(TO_CHAR(DBMS_UTILITY.GET_HASH_VALUE(UPPER('" + email.toUpperCase() + "')||'/'||UPPER('" + Pswd + "'), 1000000000, POWER(2,30) ),RPAD( 'X',29,'X')||'X' )),'" + firstname + "','" + lastname +
                "','NN',SYSDATE,'" + email + "',SYSDATE,NULL," +
                "SYSDATE+15,NULL,NULL)";
            if (stmt.execute(sql)) {
                logger.info("User Registration Failed");
                currentCon.rollback();
                stmt.close();
                currentCon.close();
                registrationSuccess = "User Registration Failed";
            }
            else {
                registrationSuccess = "Y";
                currentCon.commit();
                currentCon.close();
            }
            if (registrationSuccess.equals("Y")) {
                   logger.info("Need to send the confirmation mail to the Email");
                     efntOTP otp = new efntOTP();
                  String result=otp.efntOTPSend(strUserId,mobile,"N","");
                    if(!result.equals("Y")) {
                        logger.info("OTP NOT SENT SUCCESSFULLY");
                    }
                    //Select OTP from data base and fill it in register bean then mail Temporary required
                    logger.info("Sending OTP On E-MAIL");
                    currentCon = dbConnection.getConnection();
                    ps=null;
                    String strOtp="";
                    try {
                        ps =
                    currentCon.prepareStatement("SELECT TAVOTP PASSWORD FROM TED_EFNTOTP WHERE TAVCUSTID=? AND TACVLDTFLAG='Y'");
                        ps.setString(1, strUserId);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            logger.info("SELECTED Record Count" + rs.getRow());
                               // this.otp = rs.getString("PASSWORD");
                            strOtp=rs.getString("PASSWORD");
                                logger.info("Password::"+strOtp);
                                
                        }
                        if (rs != null) {
                            logger.info("rs exists3.");
                            rs.close();
                        }
                        currentCon.close();
                        //register.setOtp("Your One Time Password(OTP) for confirming the transaction is"+ strOtp+" It is valid for 15 mins. PLEASE DO NOT SHARE THE OTP WITH ANYONE. CRIS/IR");
                    }
                    catch (Exception e){
                        logger.info("Error in getting OTP Details");
                    }finally {
                        try { if (rs != null) rs.close(); } catch (Exception e) {};
                        try { if (ps != null) ps.close(); } catch (Exception e) {};
                        try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
                    }

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                          //get current date time with Date()
                          plainDataemail=email;
                          plainDatacustid=strUserId;
                          plainDataname= firstname +" "+ lastname;
                         // plainDataaddress=register.getAddress();
                         // plainDatapincode=register.getPincode();
                          String emailkey = "harekrishnaharer";
                         setKey(emailkey);
                          Cipher aesCipher = Cipher.getInstance("AES");
                          aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
                          byte[] byteDataToEncryptemail = plainDataemail.getBytes("UTF-8");
                          byte[] byteDataToEncryptcustid = plainDatacustid.getBytes("UTF-8");
                          byte[] byteDataToEncryptname = plainDataname.getBytes("UTF-8");
                byte[] byteCipherTextemail = aesCipher.doFinal(byteDataToEncryptemail); 
                byte[] byteCipherTextcustid = aesCipher.doFinal(byteDataToEncryptcustid); 
                byte[] byteCipherTextname = aesCipher.doFinal(byteDataToEncryptname);  
                                    cipherTextemail = Base64.getUrlEncoder().encodeToString(byteCipherTextemail);
                                  //cipherTextemail=  URLEncoder.encode(cipherTextemail,"UTF-8");
                                    cipherTextcustid = Base64.getUrlEncoder().encodeToString(byteCipherTextcustid);
                                    //cipherTextcustid=  URLEncoder.encode(cipherTextcustid,"UTF-8");
                                    cipherTextname = Base64.getUrlEncoder().encodeToString(byteCipherTextname);
                                    //cipherTextname=  URLEncoder.encode(cipherTextname,"UTF-8");
                                   
                                
                                String actvtlink= "https://www.foistest.indianrail.gov.in/RailSAHAY/ConfirmRegistration";
                               // String actvtlink= "http://172.16.4.45:7101/RailSAHAY/ConfirmRegistration";
                               
                                logger.info("\n Plain Data : "+plainDataemail+" \n Cipher Data : "+cipherTextemail+" \n Decrypted Data : "+decryptedText);
                String mainMsg="Thank you "+ firstname + " for Registration in FBD Module of IR/FOIS, "+
                                                               "Following are the details of your account,"+
                                                               "<br/>*************************************** "
                                                               +"<br/>Customer Registration ID : <b>" +strUserId +"</b>"+
                                                               "<br/>Name : <b>" +firstname +" "+lastname+"</b>"+
                                                               "<br/>Click the link to activate your account : " +
                                                               "<a href='"+actvtlink+"?uname="+cipherTextcustid+"&email="+cipherTextemail+"&name="+cipherTextname+"'> Activate Account</a>"+
                                                               "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>---OR COPY AND PASTE THE BELOW WEB LINK IN YOUR BROWSER ---</b>" +
                                                               "<br/>"+actvtlink+"?uname="+cipherTextcustid+"&email="+cipherTextemail+"&name="+cipherTextname+ 
                                                               "<br/><br/><br/><b>Note: </b>To start receiving SMS alerts from FOIS/IR please deactivate DND services for FOIS/IR by sending an SMS in below mentioned format :" +
                                                               "<br/><br/>Type <b>\"SMS START\"</b> in message text and send to <b>9821736069/7065266111</b>.<br/>" +
                                                               "This is as per TRAI Regulations. <br/><br/>***************************************<br/><br/> ";
                                     String disclaimer="<table style='border: none;'><tr style='border: none;'><td style='border: none;'><fieldset><legend>Disclaimer:</legend><p style='font-family: sans-serif;font-size: x-small;font-weight: lighter;color: gray;' align='justify'>This e-mail and any files transmitted with it are for the sole use of the intended recipient(s) and may contain confidential and privileged information. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies and the original message. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in reliance on this e-mail is strictly prohibited and may be unlawful. The recipient acknowledges that INDIAN RAILWAYS or CENTRE FOR RAILWAY INFORMATION SYSTEMS (CRIS), are unable to exercise control or ensure or guarantee the integrity of/over the contents of the information contained in e-mail transmissions and further acknowledges that any views expressed in this message are those of the individual sender and no binding nature of the message shall be implied or assumed unless the sender does so expressly with due authority of Indian Railways. Before opening any attachments please check them for viruses and defects.</p></fieldset></td></tr></table>";
                                     
                
                logger.info("Sending Registration mail");
                SMSNotification rgtnmail = new SMSNotification();
                                   rgtnmail.SendMail(email, "", "Indian Railway – FOIS/FBD ACCOUNT ACTIVATION", mainMsg + disclaimer, null, "N", logger, "");
               // logger.fatal("Register Again");
                request.setAttribute("errr",
                                "User Registration in FBD Module of IR/FOIS Completed.Verification credentials has been sent to Your Registered e-Mail Id and Mobile Number.");
                    strfrwd = "/pages/UserRgstn.jsp?errr=User Registration in FBD Module of IR/FOIS Completed.Verification credentials has been sent to Your Registered e-Mail Id and Mobile Number.";

            } else {
                    logger.fatal("Register Again");
                    request.setAttribute("errr",
                                    "Some Error Occured. Register Again.");
                    strfrwd = "/pages/UserRgstn.jsp?errr=Some Error Occured. Register Again.";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {};
        try { if (ps != null) ps.close(); } catch (Exception e) {};
        try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
        }
        response.sendRedirect(request.getContextPath()+ strfrwd);
       
    } catch (SQLException e) {
        }
    }
    
    public static void setKey(String myKey) 
       {
           MessageDigest sha = null;
           try {
               key = myKey.getBytes("UTF-8");
               sha = MessageDigest.getInstance("SHA-1");
               key = sha.digest(key);
               key = Arrays.copyOf(key, 16); 
               secretKey = new SecretKeySpec(key, "AES");
           } 
           catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
           } 
           catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
   
}
