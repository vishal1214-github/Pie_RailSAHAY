package servlet.AppData;


import java.io.FileInputStream;

import java.rmi.RemoteException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.io.ByteArrayOutputStream;

import java.io.InputStream;

import java.util.Date;
import java.util.Hashtable;

import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import org.cris.fois.notifications.generic.web.MailRequestParams;
import org.cris.fois.notifications.generic.web.SMSRequestParams;
import org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB;

import util.logger.FoisLogger;


public class SMSNotification {
    String beanurl = "";
   // String beanurl = "t3://10.60.200.53:50010,10.60.200.49:50010";
    //  String beanurl="t3://203.176.113.50:50002,203.176.113.51:50002";
    //  String beanurl="t3://203.176.113.50:50002";
    // String beanurl="t3://10.60.201.195:50010,10.60.201.196:50010";
    GenericNotificationSessionEJB genericNotificationSessionEJB = null;
    private String login="N";
    String strMessage;
    String strResponse;
    String strMobNumb;
    String mailto = "";
    String mailcc = "";
    String subject = "";
    String content = "";
    String mailid = "";
    String password = "";
    String smtpHost = "";
    String atmtFlag="";
    ByteArrayOutputStream baos;
    String fileName="";
    static Logger logger = FoisLogger.getLogger(SMSNotification.class.getName());

    public SMSNotification() throws NamingException {
        super();
         lookupHome();
    }
    public SMSNotification(String opt) throws NamingException {
        super();
        this.login=opt;
        lookupHome();
    }

    private void lookupHome() throws NamingException {
        try {
            
            Properties properties=new Properties();
            try{
                    //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
                InputStream is = this.getClass()
                                     .getClassLoader()
                                     .getResourceAsStream("EmailSrvcLogs.properties");
                
                properties.load(is);


            }catch(Exception ex)  
            {
                    System.out.println("Could not load the property file EmailSrvcLogs");
            }
            
            beanurl=properties.getProperty("smsurl");
            logger.info("beanurl:-"+beanurl);
            Context context = getInitialContext(beanurl);
            logger.info("1 Look UP");
            genericNotificationSessionEJB = (GenericNotificationSessionEJB) context.lookup("GenericNotificationSessionEJB#org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB");
            logger.info("2 Look UP " + genericNotificationSessionEJB);
        } catch (NamingException namingexception) {
            logger.info("The client was unable to lookup the EJBHome.  Please make sure ");
            logger.info("that you have deployed the ejb with the JNDI name SMSAlertHome on the WebLogic server at " +
                        beanurl);
            throw namingexception;
        }
    }

    private Context getInitialContext(String url) throws NamingException {

        Hashtable hashtable;
        hashtable = new Hashtable();
        hashtable.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        hashtable.put("java.naming.provider.url", url);
     /*   if (this.login.equals("Y"))
        {
       hashtable.put(Context.SECURITY_PRINCIPAL, "foisweblogic");
       hashtable.put(Context.SECURITY_CREDENTIALS, "weblogic123");
        }
        else {
            hashtable.put(Context.SECURITY_PRINCIPAL, "foisweblogic");
            hashtable.put(Context.SECURITY_CREDENTIALS, "weblogic123");
        }
       */
        try {
            return new InitialContext(hashtable);
        } catch (NamingException namingexception) {
            logger.error("We were unable to get a connection to the WebLogic server at " + url);
            logger.error("Please make sure that the server is running.");
            throw namingexception;
        }
    }

    public String sendSMS(String strMobNo, String strMsg) throws RemoteException, CreateException, RemoveException,
                                                                 NamingException {

        strMsg = strMsg.replaceAll(" ", "%20");
        this.strMessage =   strMsg;
        this.strMobNumb =   strMobNo;
         

           logger.info("Hare Krishna strMesg::"+strMsg+"::"+strMobNo);
                          String response=genericNotificationSessionEJB.sendSMSResp(getSMSParams());
                        logger.info("Hare Krishna Reached to get response::"+response+"::");
        return response;
        
    }
    public void SendMail(String mailto, String mailcc, String subject, String content, ByteArrayOutputStream baos,String atmtFlag, Logger logger,String fileName) {
        
                        this.mailto = mailto;
                        this.mailcc = mailcc;
                        this.subject = subject;
                        this.content = content;
                        this.baos=baos;
                        this.atmtFlag=atmtFlag;
                        this.fileName=fileName; 
                        
                        try{
                            genericNotificationSessionEJB.sendMail(getParams());
                        }                
                        
                        catch(Exception ex)  
                        {
                            logger.error("Exception in callbean#",ex);
                        }
        
        
                    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
    private SMSRequestParams getSMSParams()
        {

            logger.info("strMobNumb111 "+strMobNumb);
            SMSRequestParams params=new SMSRequestParams();
            params.setClientModuleName("EDMND");
            params.setMessage(strMessage);
            params.setMobileNo(strMobNumb);
            //params.setSmsProvider("WA");
            return params;
        }
    private MailRequestParams getParams()
       {
           MailRequestParams params=new MailRequestParams();
           if(atmtFlag.equals("Y"))
           {
            params.setAttachmenName(fileName);
            params.setAttachment(true);
            //params.setAttachment(false);
            params.setAttachmentByteArray(baos.toByteArray());
            params.setAttachmentMimeType("application/pdf");
           }
           params.setClientModuleName("EDMND");
           //String disclaimer="<table style='border: none;'><tr style='border: none;'><td style='border: none;'><fieldset><legend>Disclaimer:</legend><p style='font-family: sans-serif;font-size: x-small;font-weight: lighter;color: gray;' align='justify'>This e-mail and any files transmitted with it are for the sole use of the intended recipient(s) and may contain confidential and privileged information. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies and the original message. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in reliance on this e-mail is strictly prohibited and may be unlawful. The recipient acknowledges that INDIAN RAILWAYS or CENTRE FOR RAILWAY INFORMATION SYSTEMS (CRIS), are unable to exercise control or ensure or guarantee the integrity of/over the contents of the information contained in e-mail transmissions and further acknowledges that any views expressed in this message are those of the individual sender and no binding nature of the message shall be implied or assumed unless the sender does so expressly with due authority of Indian Railways. Before opening any attachments please check them for viruses and defects.</p></fieldset></td></tr></table>";
           params.setContent(content);
          // params.setContentText(true);
           params.setContentText(false);
           params.setContentMimeType( "text/html");
           params.setSenderMailId("");
           params.setDebug("false");
          // params.setDebug("true");
           params.setMailbcc("");
           params.setMailcc(mailcc);
           params.setMailto(mailto);
           //params.setMailpassword("");
           //params.setMailusername(mailusername);
           params.setSalutation("");
           params.setSmtpHost("");
           params.setSubject(subject);
          // params.setFooter("This is System Generated Mail.\n\nThanks & Regards \n\n FOIS ");
           return params;           
       } 
}
