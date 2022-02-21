package util.help; 

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/*
************************************************************************
*	Provide Description of Tag, when Validation Fails.      			*
*																		*
*	File Name : GQ_Help.java											*
*																	 	*
*	Name of The Service : WEBHELP										*
*																		*
*	Description : Provide Valdation Error Description To User.			*
*	This files read the Description of each Vldt Tag (same as previously*
*	in VB), which are stored in validationCodes.properties in           *
*	propertyfiles directory....                                         *
*                                                                       *
*	Thess description are read by server only once when it starts and   *
*	remains available to whole application  							*
*																		*
*	Name of the Developer	: Kunal Malhan								*
* 	Creation Date			: 12-03-2006								*
*																		*
*																	 	*
*	Usage : Description of Tag, when Validation Fails.  				*
************************************************************************
*/

public class GG_VldtDesc
{
    static public Properties loadTagCodes(ServletContext context)
    {
        Properties properties   = new Properties();
        try
        {
            properties.load(new FileInputStream(util.logger.Log4jInit.strPropertiesPath + "validationCodes.properties"));
            context.setAttribute("VldtCode", properties);
            System.out.println("Loading VldtCode");
        }
        catch(Exception e)
        {
            System.out.println("Error Loading the Property properties validationCodes.properties" );
        }
        return properties;
    }
    
    static public String getVldtCodeDesc(HttpServletRequest rq, String tagCode)
    {
        if(tagCode == null || tagCode.equals(""))
            return "";
        else
            return ((Properties) rq.getSession().getServletContext().getAttribute("VldtCode")).getProperty(tagCode);
    }
} 
