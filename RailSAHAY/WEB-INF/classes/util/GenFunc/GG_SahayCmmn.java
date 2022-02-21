package util.GenFunc;

import java.io.File;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class GG_SahayCmmn 
{
	static Logger logger = FoisLogger.getLogger(GG_SahayCmmn.class.getName());
    public  String getHomePageMsgs(String strUserId,String strClntId,String si_Flag, String si_Type, String si_UserType)
               {
                   
                    String strError="";
                    String strProxyMesg[];
                    String strMesgList="";
                   strProxyMesg = (String[])null;
                                        
                    String strDetails[]=null;
                   logger.info("strUserId"+strUserId);
                   logger.info("strClntId"+strClntId);
                    logger.info("si_Flag"+si_Flag);
                    logger.info("si_Type"+si_Type);
                    logger.info("si_UserType"+si_UserType);
                    
                    FOISTuxedo RQSAHAYMISC = null;
                           try
                           {
                                   logger.info("Calling Tuxedo");
                                   RQSAHAYMISC = new FOISTuxedo();
                                   logger.info("Client Object Got.");
                           }
                           catch (Exception ne)
                           {
                                   logger.info("Tuxedo Connnection Failed.");
                                   strError="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
                                   logger.info("strError"+strError);
                           }

                           try
                           {
                                   logger.info("Calling tuxInit.");

                                   RQSAHAYMISC.tuxInit("RQSAHAYMISC");
                                   RQSAHAYMISC.setString("F_USERID",   0, strUserId);
                                   RQSAHAYMISC.setString("F_CLNTID",               0, strClntId);
                                   RQSAHAYMISC.setString("F_FLAG",         0, "SAHAY_MSGS");
                                   RQSAHAYMISC.setString("F_ACINFLAG",                          si_Flag.trim());
                                   RQSAHAYMISC.setString("F_ORDRBY1",                           si_Type.trim());
                                   RQSAHAYMISC.setString("F_CTRDFLAG",                          si_UserType.trim());
                                   


                                   logger.info("Called Tux_Fchg.");
                                   RQSAHAYMISC.call("N");
                                   logger.info("Called Tux_Call.");
                                   String strData = RQSAHAYMISC.getStringItemDef("F_ROWCONT", 0);
                                   logger.info("Called Tux_Fchg.");
                                   logger.info("F_ROWCONT :"+ strData);
                                   int intRowCount=Integer.parseInt(strData);
                                   
                                   strProxyMesg = new String[intRowCount];
                                   for (int i=0;i<intRowCount;i++)
                                           {
                                                                                   
                                           strProxyMesg[i]    =    RQSAHAYMISC.getStringItemDef("F_MESGTEXT",               i).trim();
                                           String strCrntMesgType = RQSAHAYMISC.getStringItemDef("F_FLAG",                  i).trim();
                                            
                                           String strCrntMesg = strProxyMesg[i];
                                           //String strCrntMesgType = "N";
                                           if(strCrntMesgType.equals("N"))
                                               strMesgList = (new StringBuilder(String.valueOf(strMesgList))).append("<img src='/RailSAHAY/resources/images/9.png' alt='mesg' width='10pt' height='10pt'>&nbsp;<font color='#e60000'>").append(strCrntMesg).append("</font>&nbsp;").toString();
                                           else
                                               strMesgList = (new StringBuilder(String.valueOf(strMesgList))).append("<img src='/RailSAHAY/resources/images/9.png' alt='mesg' width='10pt' height='10pt'>&nbsp;<font color='#003399'>").append(strCrntMesg).append("</font>&nbsp;").toString();
                                       


                                           }
                                   
                                   RQSAHAYMISC.endSession();
                                   logger.info("End RQSAHAYPRTL.");
                           }
                           catch (Exception e)
                           {
                                   logger.info("There was an exception while creating and using the FOIS."+e);
                                   strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(2)";
                                   logger.info("strError"+strError);

                           }
                           finally
                           {
                           try
                           {
                                   RQSAHAYMISC=null;
                           }
                           catch(Exception e)
                           {
                                   logger.info("Errror In Finally.");
                                   strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(3)";
                                   logger.info("strError"+strError);
                           }
                  }
                   return strMesgList;
               }
                
    public String getAccessCount(String strUserId,String strClntId)
    {
        String strAccessCount ="";
            logger.info("IN GG_SahayCmmn For Access Counts");
                    FOISTuxedo RQSAHAYMISC  = null;
            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYMISC = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object");
            }
            
            try
            {

                    RQSAHAYMISC.tuxInit("RQSAHAYMISC");

                    RQSAHAYMISC.setString("F_USERID",   0, strUserId);
                    RQSAHAYMISC.setString("F_CLNTID",               0, strClntId);
                    RQSAHAYMISC.setString("F_FLAG",         0, "ACCESS_COUNT");
                    
                    
            }
            catch(Exception e)
            {
                    //logger.info("Error in Servlet : " + e);
            }

            String erorCode                 = null;
            
            try
            {
                    logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
                    RQSAHAYMISC.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.info("Exception while call to the service is " + e.toString());
                    e.printStackTrace();
                    erorCode = "Exception while call to the service";
            }
            
            try
            {
                    erorCode            = RQSAHAYMISC.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode); 
            
            }
            
        try
                        {
                                strAccessCount         =  RQSAHAYMISC.getStringItemDef("F_ORDRBY1",0,"0");
                }
                catch(Exception d)
                {
                    logger.fatal("Problem in Calling Service RQSAHAYMISC and filling data into array" + d.toString());
               
                }
                        try
                        {
                                RQSAHAYMISC.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                  
                        }
                         logger.info("strAccessCount" + strAccessCount);
                        return strAccessCount ;
    
    
    }
	
	public String setPortal(String strUserId,String strClntId,String strTheme, String strLangFlag,String strRprtName,String strRprtPath, String strUserDevice, String strBrowser)
	{
	    strLangFlag= strLangFlag.trim();
            String strSesnID="";
            String strLangFlag1="";
            if(strLangFlag.length()>1)
            {
	    strLangFlag1=strLangFlag.substring(0, 1);
	    strSesnID=strLangFlag.substring(1, strLangFlag.length());
            }
            else {
                strLangFlag1=strLangFlag;
            }
                
            logger.info("strLangFlag1"+ strLangFlag1);
	    logger.info("strSesnID"+ strSesnID);
            logger.info("strRprtName" + strRprtName);
		logger.info("IN GG_SahayCmmn TO SET PORTAL");
			FOISTuxedo RTSAHAYPRTL	= null;
		try
		{
			logger.info("Calling Tuxedo");
			RTSAHAYPRTL = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}
		
		try
		{

			RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");

			RTSAHAYPRTL.setString("F_USERID",   0, strUserId);
			RTSAHAYPRTL.setString("F_CLNTID",		0, strClntId);
			RTSAHAYPRTL.setString("F_FLAG",		0, "LOGSAHAY");
			RTSAHAYPRTL.setString("F_COMMITFLAG",		0, "Y");	
			RTSAHAYPRTL.setString("F_ORDRBY1",		0,strLangFlag1 );
			RTSAHAYPRTL.setString("F_ORDRBY2",		0,strTheme );				
			RTSAHAYPRTL.setString("F_ORDRBY3",		0, strRprtName);
			RTSAHAYPRTL.setString("F_ORDRBY4",		0, strRprtPath);
			RTSAHAYPRTL.setString("F_ORDRBY5",		0, strUserDevice);
			RTSAHAYPRTL.setString("F_ORDRBY6",		0, strBrowser);
		        RTSAHAYPRTL.setString("F_ORDRBY7",              0, "");
		        RTSAHAYPRTL.setString("F_ORDRBY8",              0, strSesnID);
			
			
		}
		catch(Exception e)
		{
			//logger.info("Error in Servlet : " + e);
		}

		String erorCode			= null;
		
		try
		{
			logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
			RTSAHAYPRTL.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
			e.printStackTrace();
			erorCode = "Exception while call to the service";
		}
		
		try
		{
			erorCode            = RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode); 
		
		}
		
		logger.info("RRRRRRRRRRRR Format");
		try
		{
			RTSAHAYPRTL.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		String strfile="";
		String strfilepath="";
		String strfilename=(strRprtPath.substring(strRprtPath.lastIndexOf("/")+1)).trim();
		logger.info("strfilename"+strfilename);
		if (strfilename.equals("index.jsp"))
		{
		
			//strfilepath ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/";/**FOR LOCAL*/
			strfilepath ="/switch/Applications/RailSAHAY/";/**FOR TEST*/
			//strfilepath ="/switch/applications/FOISPORTAL/RailSAHAY/";/**FOR ONLINE*/	
			
		}
		else
		{	
		
		//strfilepath ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/pages/";/**FOR LOCAL*/
		strfilepath ="/switch/Applications/RailSAHAY/pages/";/**FOR TEST*/
		//strfilepath ="/switch/applications/FOISPORTAL/RailSAHAY/pages/";/**FOR Online*/
		}
		logger.info("strfilepath"+ strfilepath);
		strfile= strfilepath+ strfilename;
		logger.info("strfile"+ strfile);
		File file = new File(strfile);
		logger.info("Before Format : " + file.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
         String strReturn=  sdf.format(file.lastModified());
		logger.info("After Format : " + strReturn);
         return(strReturn);

  
	
	}
    public String setSahay(String strUserId,String strClntId,String strTheme, String strLangFlag,String strRprtName,String strRprtPath, String strUserDevice, String strBrowser, String strInpt)
    {
            logger.info("IN GG_SahayCmmn FOR SET SAHAY");
        strLangFlag= strLangFlag.trim();
        String strSesnID="";
        String strLangFlag1="";
        if(strLangFlag.length()>1)
        {
        strLangFlag1=strLangFlag.substring(0, 1);
        strSesnID=strLangFlag.substring(1, strLangFlag.length());
        }
        else {
            strLangFlag1=strLangFlag;
        }
            
        logger.info("strLangFlag1"+ strLangFlag1);
        logger.info("strSesnID"+ strSesnID);
                    FOISTuxedo RTSAHAYPRTL  = null;
            try
            {
                    logger.info("Calling Tuxedo");
                    RTSAHAYPRTL = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object");
            }
            
            try
            {

                    RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");

                    RTSAHAYPRTL.setString("F_USERID",   0, strUserId);
                    RTSAHAYPRTL.setString("F_CLNTID",               0, strClntId);
                    RTSAHAYPRTL.setString("F_FLAG",         0, "LOGSAHAY");
                    RTSAHAYPRTL.setString("F_COMMITFLAG",           0, "Y");        
                    RTSAHAYPRTL.setString("F_ORDRBY1",              0,strLangFlag1 );
                    RTSAHAYPRTL.setString("F_ORDRBY2",              0,strTheme );                           
                    RTSAHAYPRTL.setString("F_ORDRBY3",              0, strRprtName);
                    RTSAHAYPRTL.setString("F_ORDRBY4",              0, strRprtPath);
                    RTSAHAYPRTL.setString("F_ORDRBY5",              0, strUserDevice);
                    RTSAHAYPRTL.setString("F_ORDRBY6",              0, strBrowser);
                    RTSAHAYPRTL.setString("F_ORDRBY7",              0, strInpt);
                    RTSAHAYPRTL.setString("F_ORDRBY8",              0, strSesnID);
                    
                    
            }
            catch(Exception e)
            {
                    //logger.info("Error in Servlet : " + e);
            }

            String erorCode                 = null;
            
            try
            {
                    logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
                    RTSAHAYPRTL.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.info("Exception while call to the service is " + e.toString());
                    e.printStackTrace();
                    erorCode = "Exception while call to the service";
            }
            
            try
            {
                    erorCode            = RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode); 
            
            }
            
 
            try
            {
                    RTSAHAYPRTL.endSession();
            }
            catch(Exception e)
            {
                    //logger.info("Error In End Session:"+e.toString());
            }
          return "Y";

    
    
    }
	public String getbrowserinfo(String browserDetails)
	{
		String  userAgent       =   browserDetails;
		String  user            =   userAgent.toLowerCase();

		String os = "";
		String browser = "";
		String device="W";

		logger.info("User Agent for the request is===>"+browserDetails);
		//=================OS=======================
		if(userAgent.toLowerCase().indexOf("ucbrowser") >= 0)
		 {
		     os = "unknown";
		     device="N";
		     
		 } 
		else if(userAgent.toLowerCase().indexOf("ubrowser") >= 0)
		 {
		     os = "unknown";
		     device="N";
		     
		 } 
		else if(userAgent.toLowerCase().indexOf("android") >= 0)
		 {
		     os = "Android";
		     device="M";
		     
		 } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
		 {
		     os = "IPhone";
		     device="M";
		 }
		 else if(userAgent.toLowerCase().indexOf("windows phone") >= 0)
		 {
		     os = "Windows Phone";
		     device="M";
		 }
		 else if(userAgent.toLowerCase().indexOf("ipad") >= 0)
		 {
		     os = "Ipad";
		     device="M";
		 }
		 else if(userAgent.toLowerCase().indexOf("blackberry") >= 0)
		 {
		     os = "Black Berry";
		     device="M";
		 }
		 else if (userAgent.toLowerCase().indexOf("windows") >= 0 )
		 {
		     os = "Windows";
		     device="W";
		 } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
		 {
		     os = "Mac";
		     device="W";
		 } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
		 {
		     os = "Unix";
		     device="W";
		 } 
		 else
		
		 {
			 device="W";
		     os = "UnKnown, More-Info: "+userAgent;
		 }
		 //===============Browser===========================
		if (user.contains("ucbrowser"))
		{
		     browser="UCBrowser";
		}
		else if (user.contains("ubrowser"))
		{
		     browser="UCBrowser";
		}
		else if (user.contains("msie"))
		{
		    String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
		    browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version"))
		{
		    browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if ( user.contains("opr") || user.contains("opera"))
		{
		    if(user.contains("opera"))
		        browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		    else if(user.contains("opr"))
		        browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		} else if (user.contains("chrome"))
		{
		    browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
		{
		    //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
		    browser = "Netscape-?";

		} else if (user.contains("firefox"))
		{
		    browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if(user.contains("rv"))
		{
		    browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
		} else
		{
		    browser = "UnKnown, More-Info: "+userAgent;
		}
		logger.info("Operating System======>"+os);
		logger.info("Device======>"+device);
		logger.info("Browser Name==========>"+browser);
		
		String strReturn=device+"#"+browser;
		return(strReturn);
	}
		

}
