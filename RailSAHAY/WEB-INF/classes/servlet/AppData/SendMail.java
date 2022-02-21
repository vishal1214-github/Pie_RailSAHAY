package servlet.AppData;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import commonj.work.Work;

import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;

import java.util.Hashtable;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import javax.mail.Folder;
import javax.mail.util.ByteArrayDataSource;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cris.fois.notifications.generic.web.MailRequestParams;
import org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB;


/*From manish*/
public class SendMail implements Work {

    String mailto = "";
    String mailcc = "";
    String subject = "";
    String content = "";
    String mailid = "";
    String password = "";
    String smtpHost = "";
    Logger logger = null;
    String atmtFlag="";
    ByteArrayOutputStream baos;
    String fileName="";
    String beanurl = "";
    
    

    public SendMail(String mailto, String mailcc, String subject, String content, String mailid, String password,
                    String smtpHost, ByteArrayOutputStream baos,String atmtFlag, Logger logger,String fileName) {
        super();
        // TODO Auto-generated constructor stub
        this.mailto = mailto;
        this.mailcc = mailcc;
        this.subject = subject;
        this.content = content;
        this.mailid = mailid;
        this.password = password;
        this.smtpHost = smtpHost;
        this.logger = logger;
        this.baos=baos;
        this.atmtFlag=atmtFlag;
        this.fileName=fileName;       
        
    }
   

    /*From manish*/

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "no-reply.fois@cris.org.in";
            String password = "edemand";
            return new PasswordAuthentication(username, password);
        }
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
           params.setClientModuleName("FBD");
           
           MimeBodyPart mbp1 = new MimeBodyPart();
           MimeBodyPart mbp2 = new MimeBodyPart();
        try {
            mbp1.setContent(content, "text/html");
        
        String disclaimer =
               "<table style='border: none;'><tr style='border: none;'><td style='border: none;'><fieldset><legend>Disclaimer:</legend><p style='font-family: sans-serif;font-size: x-small;font-weight: lighter;color: gray;' align='justify'>This e-mail and any files transmitted with it are for the sole use of the intended recipient(s) and may contain confidential and privileged information. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies and the original message. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in reliance on this e-mail is strictly prohibited and may be unlawful. The recipient acknowledges that INDIAN RAILWAYS or CENTRE FOR RAILWAY INFORMATION SYSTEMS (CRIS), are unable to exercise control or ensure or guarantee the integrity of/over the contents of the information contained in e-mail transmissions and further acknowledges that any views expressed in this message are those of the individual sender and no binding nature of the message shall be implied or assumed unless the sender does so expressly with due authority of Indian Railways. Before opening any attachments please check them for viruses and defects.</p></fieldset></td></tr></table>";
           mbp2.setContent(disclaimer, "text/html");
           Multipart mp = new MimeMultipart();
           mp.addBodyPart(mbp1);
           mp.addBodyPart(mbp2);
           params.setContent(content+disclaimer);
           } catch (MessagingException e) {
           }          
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
          params.setFooter("");
           return params;
           
       }       
     
    public void release() {
        // TODO Auto-generated method stub

    }

    public boolean isDaemon() {
        // TODO Auto-generated method stub
        return false;
    }
    private Context getInitialContext(String url) throws NamingException {
           Hashtable env = new Hashtable();
           // WebLogic Server 10.x/12.x connection details
           env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
           //env.put(Context.PROVIDER_URL, "t3://172.16.4.79:7101");
           env.put(Context.PROVIDER_URL, url);
           //env.put(Context.PROVIDER_URL, "t3://10.60.200.94:50050,10.60.200.95:50050,10.60.200.96:50050,10.60.200.97:50050");
           return new InitialContext(env);
       }
    public void run() {
        logger.info("calling fois notification::start");
                callnotificationMAIL();
        logger.info("calling fois notification::end");
    }
    
public void callnotificationMAIL() {
                Properties properties=new Properties();
                try{
                        //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
                        properties.load(new FileInputStream("EmailSrvcLogs.properties"));
                    beanurl=properties.getProperty("smsurl");
                    logger.info("beanurl:-"+beanurl);
                    Context context = getInitialContext(beanurl);
                    GenericNotificationSessionEJB genericNotificationSessionEJB =
                                   (GenericNotificationSessionEJB) context.lookup("GenericNotificationSessionEJB#org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB");
                               System.out.println("Got EJB Object#"+genericNotificationSessionEJB);

                    genericNotificationSessionEJB.sendMail(getParams());

                }
                catch (CommunicationException ex) {
                           logger.error(ex.getClass().getName());
                           logger.error(ex.getRootCause().getLocalizedMessage());
                           logger.error("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
                       }
                catch(Exception ex)  
                {
                    logger.error("Exception in callbean#",ex);
                }
                
                
            }
   

}
