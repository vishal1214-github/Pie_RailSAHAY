package util.exception; 
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/*
************************************************************************
*	Provide Custom Exception To User.									*
*																		*
*	File Name : GG_Exception.java										*
*																	 	*
*	Description : Provide Custom Exception To User.						*
*	This File provides all types of Error Handings like :               *
*	1) Errors occurs while Calling the Tuxedo service or                *
*           buffer read/Write                                           *
*	2) Errors Coming from Tuxedo Service.....                           *
*	3) Exceptions from Java file itself like numberformat exception     *
*	4) Errors while performing Validations on Inputs...                 *
*																		*
*	This File is Helpfull in tracing the Error and also showing error   *
*           msg to User....                     						*
*																		*
*	Name of the Developer	: Kunal Malhan								*
* 	Creation Date			: 02-03-2007								*
*																		*
*																	 	*
*	Usage : Provide Custom Exception To User.							*
************************************************************************
*/

public class GG_Exception extends Exception
{
    public GG_Exception(int errorCode, String errorMsg, Throwable throwable)
    {
        commonInitialization(errorCode, errorMsg, throwable);
    }
    
    public GG_Exception(int errorCode, String errorMsg)
    {
        commonInitialization(errorCode, errorMsg, null);
    }
    
    public GG_Exception(int errorCode, Throwable throwable)
    {
        commonInitialization(errorCode, "", throwable);
    }
    
    public GG_Exception(String errorMsg, java.lang.Throwable throwable)
    {
        commonInitialization(-1, errorMsg, throwable);
    }
    
    public GG_Exception(String errorMsg)
    {
        commonInitialization(-1, errorMsg, null);
    }
    
    public GG_Exception(int errorCode)
    {
        commonInitialization(errorCode, "", null);
    }
    
    public GG_Exception(java.lang.Throwable throwable)
    {
        commonInitialization(-1, "", throwable);
    }
    
    private void commonInitialization(int errorCode, String errorMsg, Throwable throwable)
    {
        priInt_errorCode    = errorCode;
        priStr_errorMsg     = errorMsg;
        
        if(throwable != null)
            setStacktrace(throwable);
    }
    
    public String getErrorCodeDesc(HttpServletRequest rq)
    {
        if(priInt_errorCode == -1)
            return "";
        
        Properties properties   = (Properties) rq.getSession().getServletContext().getAttribute("ErorCode");
        if(properties == null)
        {
            System.out.println("Loading ErorCode");
            properties  = loadProperties(rq);
        }
        priInt_errorCodeDesc    = properties.getProperty("" + priInt_errorCode);
        return "" + priInt_errorCode + ":" + priInt_errorCodeDesc;
    }

    public String getErrorMessage()
    {
        return priStr_errorMsg;
    }

    public String getErrorCodeMessage(HttpServletRequest rq)
    {
        if(priStr_errorMsg == null || priStr_errorMsg.equals(""))
            return "" + getErrorCodeDesc(rq);
        else
            return "" + getErrorCodeDesc(rq) + "#" + priStr_errorMsg;
    }

    public void setStacktrace(Throwable throwable)
    {
        StackTraceElement ste[] = throwable.getStackTrace();
        priStr_stackTrace       = throwable.toString();
        for(int i=0;i<ste.length;i++)
        {
             priStr_stackTrace  += "\n\t at " + ste[i].toString();
        }
        //System.out.println("Stack Trace::" + priStr_stackTrace);
    }
    
    public String getStacktrace()
    {
        return priStr_stackTrace;
    }
    
    public Properties loadProperties(HttpServletRequest rq)
    {
        Properties properties   = new Properties();
        try
        {
            properties.load(new FileInputStream(util.logger.Log4jInit.strPropertiesPath + "errorCodes.properties"));
            rq.getSession().getServletContext().setAttribute("ErorCode", properties);
        }
        catch(Exception e)
        {
            System.out.println("Error Loading the Property properties errorCodes.properties" );
        }
        return properties;
    }
    
    private int priInt_errorCode        = -1;
    private String priInt_errorCodeDesc = null;
    private String priStr_errorMsg      = null;
    private String priStr_stackTrace    = null;
} 
