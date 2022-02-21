package servlet.AppData;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;

import java.rmi.RemoteException;

import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.CreateException;

import javax.ejb.RemoveException;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import org.cris.fois.notifications.generic.web.SMSRequestParams;
import org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB;

public class SendSMS {
    String SMSProvider = "";
    Logger logger;
    String strMessage;
    String strResponse;
    String strMobNumb;
    String strSuccessFlag;
    String beanurl = "";
  
    public SendSMS() {
        super();
    }

    public SendSMS(Logger logger) {
        this.logger=logger;
        logger.info("SendSMS called");
        

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
    public String callBean(String strMobNo, String strMsg) throws CreateException, RemoteException, RemoveException {
            String response = "";
            this.strMessage=strMsg;
            this.strMobNumb=strMobNo;
            logger.info("strMobNumb "+strMobNumb);
            if (this.strMobNumb==null)
                this.strMobNumb="";
            if (this.strMessage==null)
                this.strMessage="Details Not Given";
            strMessage=strMessage.replaceAll(" ","%20");
            HttpURLConnection httpCon =null;
            URL con=null;
            String cmsurl="";
            FileInputStream fileSMS = null;
           /* try
                            {
                              Properties p = new Properties();
                                fileSMS = new FileInputStream("WebSrvcLogs.properties");
                                p.load(fileSMS);

                                cmsurl = p.getProperty("cmsurl");
                           String url="http://"+cmsurl+"/CMSSMS/JSP/sms/WebSMS.do?hmode=SMS_Rec_URL_CRIS&user=FOIS&pass=7aC09F&mobile=91"+strMobNumb.trim()+"&msg="+strMessage.trim();
                            con = new URL(null,url);
                            httpCon = (HttpURLConnection)con.openConnection();
                            
                            logger.info("Set All the varibales and going to get the Response...."+url);
                                
                            logger.info("received Response is ::"+httpCon.getResponseCode()+":: Message is ::"+httpCon.getResponseMessage());


                            }catch(Exception ex)

                            {
                            ex.printStackTrace();
                            logger.error("Unable to connect to SMS service."+ex.toString());

                            } finally {
                                try {
                                    if (fileSMS != null) {
                                        fileSMS.close();
                                    }
                                } catch (Exception e) {
                                    logger.error("Error in closing file", e);
                                }
                            }
                            */
                            logger.info("Trying to get the BufferReader");
                                    try {

                                 /*   BufferedReader in = null;
                                    in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                                    logger.info("Got the Stream Object and going to get the Buffer ");
                                    String inputLine;
                                    while ((inputLine = in.readLine()) != null)
                                    {
                                    response+=inputLine;
                                    }
                                    */
                                    response = callnotificationSMS();
                                    logger.info("Got the Buffer Thanks "+response);
                                    }

                                    catch(Exception io)
                                    {
                                    io.printStackTrace();
                                    logger.error("Error Reading Response from server!!!"+ io.toString());
                    }

                            return response;
                }
            public String callnotificationSMS() {
                            String smsresponse = "";
                            Properties properties=new Properties();
                            try{
                                    //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
                                    InputStream is = this.getClass()
                                                         .getClassLoader()
                                                         .getResourceAsStream("EmailSrvcLogs.properties");
                                    
                                    properties.load(is);
                                beanurl=properties.getProperty("smsurl");
                                logger.info("beanurlsms:-"+beanurl);
                                Context context = getInitialContext(beanurl);
                                GenericNotificationSessionEJB genericNotificationSessionEJB1 =
                                               (GenericNotificationSessionEJB) context.lookup("GenericNotificationSessionEJB#org.cris.fois.notifications.generic.web.ejb.GenericNotificationSessionEJB");
                                           System.out.println("Got EJB Object#"+genericNotificationSessionEJB1);
                              
                                smsresponse = genericNotificationSessionEJB1.sendSMSResp(getSMSParams());
                                
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
                            logger.info("smsresponse:-"+smsresponse);  
                          return smsresponse;  
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
    }

