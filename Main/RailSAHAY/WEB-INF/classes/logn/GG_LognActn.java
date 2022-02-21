package logn;
import tuxedo.*;
import java.io.*;
import java.util.Enumeration;
import javax.servlet.http.*;
import Cris.util.*;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import util.exception.GG_Exception;
import util.logger.FoisLogger;

/*
************************************************************************
*	Validate users and client status at the time of login in an applica-*
* 	tion to given user id and client id and login location.				*
*	File Name : LoginAction.java										*
*																		*
*	Name of The Service : ATVLDTUSER									*
*	Description : Validate user and client status at the time of login.	*
*																		*
*	Name of the Developer	: Kunal Malhan								*
*	Creation Date			: 02-12-2006								*
*																		*
*																		*
*	Usage : This Servlet is Used to Validate user and client status		*
*			 at the time of login.										*
************************************************************************
*/

public class GG_LognActn extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_LognActn.class.getName());
	String strPswdVldt		= "";
	String strPswdExpy		= "";
	String strOtpExst       ="";
        String strUserID ="";
        String strUserName ="";
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		NDC.push((String)req.getParameter("txtClientId"));
		logger.info("Entering GG_LognActn...");
		String responseXml	= "<data>%Error%%Data%</data>";
		HttpSession session=(HttpSession)req.getSession();

		try
		{
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/xml");
			//res.setContentType("text/html");
			res.setHeader("Cache-Control", "no-cache");
            String strInptType="";
            try
            {
                strInptType=req.getParameter("InptType");
                if (strInptType==null)
                strInptType="S";
            }
            catch(Exception e)
            {
                 strInptType="S";
            }
            System.out.println("strInptType :"+strInptType);
            
            String url = req.getRequestURL().toString();
            System.out.println(url);

			String strUserID	= "";
			String strPasswd	= "";
			String strData1		= "";
			String strMsg		= "";
			String strLocn		= "";
			String strClntId	= "";
			String strLoginMode	= "";
			String strRole		= "";
			String strName		= "";
			String strProj		= "";
	//		String strVrtlClntId= "";
			String strValdFlag	= "";

            String strDeviceFlag	= "";
			String strLoginArea 	= "";
			String strLoginSttn		= "";
			String strLoginDvsn		= "";
			String strLoginZone		= "";
			String strSttnPhase		= "";
			String strCurrentSctn	= "";
			String strGOGDFlag		= "";
			String strCTFlag		= "";
			String strTrspFlag		= "";
			String strSrvgSttn		= "";

			String strMesgText			= "";
			String strUserLocnFLag		= "";
			
			
			



            if((strInptType.equals("S"))||(strInptType.equals("M")) )
            {

	//		strVrtlClntId		= req.getRemoteAddr();

			Enumeration enumeration = req.getParameterNames();
			do
			{
				if(!enumeration.hasMoreElements())
					break;
				String name= (String)enumeration.nextElement();
				String value = req.getParameterValues(name)[0];

				if(name.compareTo("txtClientId") == 0)
					strClntId	= (value.trim()).toUpperCase();
				if(name.compareTo("txtUserId") == 0)
					strUserID	= (value.trim()).toUpperCase();
				if(name.compareTo("txtPassword") == 0)
					strPasswd	= (value.trim()).toUpperCase();
				if(name.compareTo("txtOptn") == 0)
					strLoginMode		= (value.trim()).toUpperCase();
				if(name.compareTo("txtLocation") == 0)
					strLocn		= (value.trim()).toUpperCase();
				if(name.compareTo("txtProj") == 0)
					strProj		= (value.trim()).toUpperCase();
				if(name.compareTo("txtValdFlag") == 0)
					strValdFlag	= (value.trim()).toUpperCase();
			} while(true);

			//*************************************************************************************
									//Calling Tuxedo Service from WTC
			//**************************Calling Service ATVLDTUSER******************************

			FOISTuxedo ATVLDTUSER = null;
			try
			{
				logger.info("Calling Tuxedo");
				ATVLDTUSER = new FOISTuxedo();
				logger.info("Cliet Object Got.");
			}
			catch (Exception ne)
			{
				logger.fatal("Unable to get the Client Object: " + ne.toString());
				throw new GG_Exception(101, ne);
			}

			logger.info("strUserID :"		+ strUserID);
			logger.info("strClntId :"		+ strClntId);
			logger.info("strPasswd :"		+ strPasswd);
			logger.info("strLoginMode :"	+ strLoginMode);
			logger.info("strLocn :"			+ strLocn);
			logger.info("F_ROLEID :"		+ strRole);
			logger.info("Project :"			+ strProj);
			logger.info("ValdFlag :"		+ strValdFlag);

			try
			{
                if (strProj.equals("RMS ARCHQ") || strProj.equals("RMS ARCHZ"))
                {
                 ATVLDTUSER.tuxInit("ATVLDTUSERARCH");
                 }
                 else
                 {
				ATVLDTUSER.tuxInit("ATVLDTUSER");
                 }
				ATVLDTUSER.setString("F_USERID",	0,	strUserID);
				ATVLDTUSER.setString("F_CLNTID",	0,	strClntId);
				ATVLDTUSER.setString("F_PSWD",		0,	strPasswd);
				ATVLDTUSER.setString("F_OPTN",		0,	strLoginMode);
				ATVLDTUSER.setString("F_VRSN",		0,	"5.4");
				ATVLDTUSER.setString("F_LOCN",		0,	strLocn);
				ATVLDTUSER.setString("F_RMSTMSFLAG",0,	strProj.substring(0, 1));
				ATVLDTUSER.setString("F_FLAG",		0,	"N");			// Always "N"
				ATVLDTUSER.setString("F_FLINFLAG",		0,	"W");			// Always "W"
				ATVLDTUSER.setString("F_VALDFLAG",	0,	strValdFlag);	// Fixed Value For All Projets
				ATVLDTUSER.setString("F_COMMITFLAG",0,	"Y");			// Not Found In VB Project
				ATVLDTUSER.setString("F_ENTYSRVC",0,	strProj);			// Add for Tracking records of user login
				ATVLDTUSER.setString("F_SESNNUMB",0,	session.getId());			// Add for Tracking records of user login
			}
			catch(Exception e)
			{
				logger.fatal("Unable to write to buffer : " + e.toString());
				throw new GG_Exception(102, e);
			}

			try
			{
				logger.info("Calling Tuxedo Service ATVLDTUSER ...");
				ATVLDTUSER.call("N");
				logger.info("CALL COMPLETED !");
			}
			catch(Exception e)
			{
				logger.fatal("Exception while call to the service is " + e.toString());
				throw new GG_Exception(107, e);
			}

			//********************************************************************************
											//END of WTC calling
			//********************************************************************************

			// Checking For Any Error from Service
			String erorCode			= null;
			try
			{
				erorCode			= ATVLDTUSER.getStringItemDef("F_ERORNO",0,"0");
			}
			catch(Exception e)
			{
				// F_ERORNO is not set by Developer, So, it is not an Error
			}
			if(erorCode != null && (!erorCode.equals("")))
			{
				logger.fatal(erorCode);
				throw new GG_Exception(erorCode);
			}

			int start1				= 0;
			String startRowCount1	= null;
			// getting Row Count
			try
			{
				startRowCount1		= ATVLDTUSER.getStringItemDef("F_ROWCONT",0,"0");
			}
			catch(Exception d)
			{
				logger.fatal("Problem in Calling Service RQLOADSRUN and filling Row Count into array" + d.toString());
				throw new GG_Exception(104, d);
			}

			if(startRowCount1 == null || startRowCount1.equals("0") || startRowCount1.equals(""))
			{
				startRowCount1		= "0";
				logger.warn("NO ROWS in BUFFER");
			}
			start1 = new Integer(startRowCount1.trim()).intValue();
			logger.info("No. of Records : " + start1);

			String strRoleID		= "";



			String loadStr1			= "/%Excp%/";
			String strReport1		= "%RoleCode%/";	
			
			try
			{
				
				//strOtpExst			= ATVLDTUSER.getStringItemDef("F_ACINFLAG", 0,"0");
				strOtpExst="N";
				logger.info("strOtpExst"+strOtpExst);
				strPswdVldt			= ATVLDTUSER.getStringItemDef("F_PSWDBLNKFLAG", 0,"0");
				logger.info("strPswdVldt"+strPswdVldt);
				
				if(strPswdVldt.equals("Y"))
				{
					strPswdExpy="N";
				}
				else
				{
					strPswdExpy="Y";
				}
				
				
			}
			catch(Exception d)
			{
				logger.fatal("Problem in Calling Service ATVLDTUSER and filling data into array" + d.toString());
				throw new GG_Exception(105, d);
			}
			

			try
			{
				strMesgText			= ATVLDTUSER.getStringItemDef("F_MESGTEXT", 0,"0");	// To Be Displayed on Status Bar
				strUserLocnFLag		= ATVLDTUSER.getStringItemDef("F_LOCNFLAG", 0,"0");	// To Be Displayed on Status Bar
			}
			catch(Exception d)
			{
				logger.fatal("Problem in Calling Service ATVLDTUSER and filling data into array" + d.toString());
				throw new GG_Exception(105, d);
			}
                try
                {
                        strUserName                     = ATVLDTUSER.getStringItemDef("F_CNSGNAME", 0,"0");     // To Be Displayed on Status Bar
                }
                catch(Exception d)
                {
                        logger.fatal("Problem in Calling Service ATVLDTUSER and filling data into array" + d.toString());
                        throw new GG_Exception(105, d);
                }
			String strCrntStts;
			if(strProj.substring(0, 1).equals("T"))
			{
				try
				{
					strCrntStts		= ATVLDTUSER.getStringItemDef("F_CRNTSTTS", 0,"0");	// To Be initialize Globally
				}
				catch(Exception d)
				{
					logger.fatal("Problem in Calling Service RQLOADSRUN and filling data into array" + d.toString());
					throw new GG_Exception(105, d);
				}
			}
			logger.info("Start1:"+start1);
			for(int k=0; k<start1; k++)
			{
				try
				{
					strRoleID		= ATVLDTUSER.getStringItemDef("F_ROLEID", k, "0").trim();
				}
				catch(Exception d)
				{
					logger.fatal("Problem in Calling Service RQLOADSRUN and filling data into array" + d.toString());
					throw new GG_Exception(105, d);
				}

				if(loadStr1.indexOf("/" + strRoleID + "/") == -1)
				{
					loadStr1	+=strReport1;
					loadStr1	= CrisFileStringOpr.replace(loadStr1,"%RoleCode%",	strRoleID);
				}
			}
			String strActvSesn="";
			try
			{
				strActvSesn	= ATVLDTUSER.getStringItemDef("F_SESNSTTS", 	0, "0");	// Previously Active Session
			}
			catch(Exception d)
			{
				logger.fatal("Problem in Calling Service ATVLDTUSER and filling data into Active Session Id" + d.toString());
				throw new GG_Exception(105, d);
			}
			
			if(!strActvSesn.equals(""))
			{
				loadStr1	= CrisFileStringOpr.replace(loadStr1,"%Excp%",	"ALREADY_LOGIN");
			}
			else
			{
				loadStr1	= CrisFileStringOpr.replace(loadStr1,"%Excp%",	"");
			}
			if(strLoginMode.equals("S"))
			{
				try
				{
					strDeviceFlag	= ATVLDTUSER.getStringItemDef("F_AJSTFLAG", 	0, "0");	// DeviceFlag
					strLoginDvsn	= ATVLDTUSER.getStringItemDef("F_DVSN",			0, "0");	// CurrentDvsn
					strLoginZone	= ATVLDTUSER.getStringItemDef("F_ZONE",			0, "0");	// CurrentZone
					strSttnPhase	= ATVLDTUSER.getStringItemDef("F_STTNPHSGFLAG",	0, "0");	// SttnPhase
					strCurrentSctn	= ATVLDTUSER.getStringItemDef("F_SCTN",			0, "0");	// CurrentSctn
					strGOGDFlag		= ATVLDTUSER.getStringItemDef("F_FLAG",			0, "0");	// GOGDFlag
					strCTFlag		= ATVLDTUSER.getStringItemDef("F_CTGR",			0, "0");	// CTFlag
					strTrspFlag		= ATVLDTUSER.getStringItemDef("F_DEADFLAG",		0, "0");	// TrspFlag
					strSrvgSttn		= ATVLDTUSER.getStringItemDef("F_SRVGSTTN",		0, "0");	// SrvgSttn
					strLoginSttn	= strLocn;
				}
				catch(Exception d)
				{
					logger.fatal("Problem in Calling Service RQLOADSRUN and filling data into array" + d.toString());
					throw new GG_Exception(105, d);
				}
			}

			if(strLoginMode.equals("A"))
			{
				try
				{
					strLoginDvsn	= ATVLDTUSER.getStringItemDef("F_DVSN", 0,"0");		// CurrentDvsn
					strLoginZone	= ATVLDTUSER.getStringItemDef("F_ZONE", 0,"0");		// CurrentZone
					strLoginArea	= strLocn;
				}
				catch(Exception d)
				{
					logger.fatal("Problem in Calling Service RQLOADSRUN and filling data into array" + d.toString());
					throw new GG_Exception(105, d);
				}
			}

			if(strLoginMode.equals("D"))
			{
				try
				{
					strLoginZone	= ATVLDTUSER.getStringItemDef("F_ZONE", 0,"0");		// CurrentZone
				}
				catch(Exception d)
				{
					logger.fatal("Problem in Calling Service RQLOADSRUN and filling data into array" + d.toString());
					throw new GG_Exception(105, d);
				}
				strLoginDvsn	= strLocn;
			}

			if(strLoginMode.equals("Z"))
			{
				strLoginZone	= strLocn;
			}
			loadStr1			= loadStr1.substring(0, loadStr1.length() - 1);
			responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	loadStr1);
			responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"");

			try
			{
				ATVLDTUSER.endSession();
			}
			catch(Exception e)
			{
				logger.fatal("Error In End Session:" + e.toString());
				throw new GG_Exception(106, e);
			}
            }

			String si_ClntHght			= req.getParameter("txtHeight").trim().toUpperCase();
			String si_ClntWidth			= req.getParameter("txtWidth").trim().toUpperCase();
			//Double di_ClntHght		= new Double(Double.parseDouble(si_ClntHght)/30);
			//int ii_NumbRows			= di_ClntHght.intValue();
			int ii_ClntHght				= Integer.parseInt(si_ClntHght);
			int ii_NumbRows				= 20;
			String strScrollDivHeight	= "";
			if(ii_ClntHght <= 610)
			{
				ii_NumbRows				= 15;
				strScrollDivHeight		= "194pt";
			}
			else if(ii_ClntHght >= 611)
			{
				ii_NumbRows				= 25;
				strScrollDivHeight		= "307pt";
			}
			logger.info("Resolution::" +ii_ClntHght + "::" +ii_NumbRows);
			logger.info("Resolutionwidth::" +si_ClntWidth );

			/* All session level variables are initlized here */

			session	 = req.getSession(true);
			  String strLangFlag          = req.getParameter("txtLangFlag").trim().toUpperCase();

            if(strInptType.equals("S"))/*ALL PROJECTS WITH LOGIN*/
            {
                session.setAttribute("ProjName",	strProj.trim());
               // GG_AppFunc.CrntAppType=strProj.trim();

                session.setAttribute("UserID",		strUserID.trim());
                session.setAttribute("ClntID",		strClntId.trim());
                session.setAttribute("LognLocnFlag",strLoginMode.trim());
                session.setAttribute("CrntLocnFlag",strLoginMode.trim());
                session.setAttribute("LognLocn",	strLocn.trim());
                session.setAttribute("LognZone",	strLoginZone.trim());
                session.setAttribute("LognDvsn",	strLoginDvsn.trim());
                session.setAttribute("LognSttn",	strLoginSttn.trim());
                session.setAttribute("LognArea",	strLoginArea.trim());
                session.setAttribute("CrntZone",	strLoginZone.trim());
                session.setAttribute("CrntDvsn",	strLoginDvsn.trim());
                session.setAttribute("CrntSttn",	strLoginSttn.trim());
                session.setAttribute("CrntArea",	strLoginArea.trim());
                session.setAttribute("SttnPhse",	strSttnPhase.trim());
                session.setAttribute("UserLocnFlag",strUserLocnFLag.trim());
                session.setAttribute("LangFlag",	strLangFlag.trim());
                session.setAttribute("SessionID",	session.getId());
                session.setAttribute("NumbRows",	"" + ii_NumbRows);
                session.setAttribute("ScrollDivHeight",	strScrollDivHeight);
                session.setAttribute("ClientWidth",	si_ClntWidth);
			
                session.setAttribute("PswdExpd",	strPswdExpy);
                session.setAttribute("OtpFlag",	strOtpExst);
                session.setAttribute("UserName", strUserName);
            }
            else
            {
                   if(strInptType.equals("T"))/*TPMS*/
                    {
                        session.setAttribute("ProjName",	"TPMS");
                        session.setAttribute("UserID",		"RARORA");
                        session.setAttribute("ClntID",		"1.1.1.1");
                        session.setAttribute("LognLocnFlag","I");
                        session.setAttribute("CrntLocnFlag","Z");
                        session.setAttribute("LognLocn",	"");
                        session.setAttribute("LognZone",	"");
                        session.setAttribute("LognDvsn",	"");
                        session.setAttribute("LognSttn",	"");
                        session.setAttribute("LognArea",	"");
                        session.setAttribute("CrntZone",	"NR");
                        session.setAttribute("CrntDvsn",	"");
                        session.setAttribute("CrntSttn",	"");
                        session.setAttribute("CrntArea",	"");
                        session.setAttribute("SttnPhse",	"");
                        session.setAttribute("UserLocnFlag","");
                        session.setAttribute("SessionID",	session.getId());
                        session.setAttribute("NumbRows",	"" + ii_NumbRows);
                        session.setAttribute("ScrollDivHeight",	strScrollDivHeight);
                        session.setAttribute("ClientWidth",	si_ClntWidth);
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Data%",	"/HELP-DESK/");
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
                    }
                    if(strInptType.equals("C"))/*CPG*/
					                    {
					                        session.setAttribute("ProjName",	"CPG");
					                        session.setAttribute("UserID",		"RARORA");
					                        session.setAttribute("ClntID",		"1.1.1.1");
					                        session.setAttribute("LognLocnFlag","I");
					                        session.setAttribute("CrntLocnFlag","Z");
					                        session.setAttribute("LognLocn",	"");
					                        session.setAttribute("LognZone",	"");
					                        session.setAttribute("LognDvsn",	"");
					                        session.setAttribute("LognSttn",	"");
					                        session.setAttribute("LognArea",	"");
					                        session.setAttribute("CrntZone",	"NR");
					                        session.setAttribute("CrntDvsn",	"");
					                        session.setAttribute("CrntSttn",	"");
					                        session.setAttribute("CrntArea",	"");
					                        session.setAttribute("SttnPhse",	"");
					                        session.setAttribute("UserLocnFlag","");
					                        session.setAttribute("SessionID",	session.getId());
					                        session.setAttribute("NumbRows",	"" + ii_NumbRows);
					                        session.setAttribute("LangFlag",	strLangFlag);
					                        session.setAttribute("ScrollDivHeight",	strScrollDivHeight);
					                        session.setAttribute("ClientWidth",	si_ClntWidth);
					                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Data%",	"/HELP-DESK/");
					                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
                    }
                    if(strInptType.equals("M"))/*CMS*/
                    {
                    	logger.info("INPUT"+strInptType);
                    	logger.info("USERID"+strUserID);
                        session.setAttribute("ProjName",	"PARICHAALAN");
                        session.setAttribute("UserID",		strUserID.trim());
                        session.setAttribute("ClntID",		strClntId.trim());
                        session.setAttribute("LognLocnFlag",strLoginMode.trim());
                        session.setAttribute("CrntLocnFlag",strLoginMode.trim());                        
                        session.setAttribute("LognLocn",	strLocn.trim());
                        session.setAttribute("LognZone",	strLoginZone.trim());
                        session.setAttribute("LognDvsn",	strLoginDvsn.trim());
                        session.setAttribute("LognSttn",	strLoginSttn.trim());
                        session.setAttribute("LognArea",	strLoginArea.trim());
                        session.setAttribute("CrntZone",	strLoginZone.trim());
                        session.setAttribute("CrntDvsn",	strLoginDvsn.trim());
                        session.setAttribute("CrntSttn",	strLoginSttn.trim());
                        session.setAttribute("CrntArea",	strLoginArea.trim());
                       
                        session.setAttribute("SttnPhse",	"");
                        session.setAttribute("UserLocnFlag","");
                        session.setAttribute("LangFlag",	strLangFlag.trim());
                        session.setAttribute("SessionID",	session.getId());
                        session.setAttribute("NumbRows",	"" + ii_NumbRows);
                        session.setAttribute("ScrollDivHeight",	strScrollDivHeight);
                        session.setAttribute("ClientWidth",	si_ClntWidth);
                        
                       
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Data%",	"/HELP-DESK/");
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
}
                   else /* I FOR IRONORE INDENTS*/
                    {
                        session.setAttribute("ProjName",	"RMS-INDENT");
                        //GG_AppFunc.CrntAppType=strProj.trim();

                        session.setAttribute("UserID",		"SECUST");
                        session.setAttribute("ClntID",		"10.30.1.24");
                        session.setAttribute("LognLocnFlag","D");
                        session.setAttribute("CrntLocnFlag","D");
                        session.setAttribute("LognLocn",	"CKP");
                        session.setAttribute("LognZone",	"SE");
                        session.setAttribute("LognDvsn",	"CKP");
                        session.setAttribute("LognSttn",	"");
                        session.setAttribute("LognArea",	"");
                        session.setAttribute("CrntZone",	"SE");
                        session.setAttribute("CrntDvsn",	"CKP");
                        session.setAttribute("CrntSttn",	"");
                        session.setAttribute("CrntArea",	"");
                        session.setAttribute("SttnPhse",	"");
                        session.setAttribute("UserLocnFlag","");
                        session.setAttribute("SessionID",	session.getId());
                        session.setAttribute("NumbRows",	"" + ii_NumbRows);
                        session.setAttribute("LangFlag",	strLangFlag);
                        session.setAttribute("ScrollDivHeight",	strScrollDivHeight);
                        session.setAttribute("ClientWidth",	si_ClntWidth);
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Data%",	"/HELP-DESK/");
                        responseXml	= CrisFileStringOpr.replace(responseXml,"%Error%",	"");
                    }
            }
			Hashtable htbRprtName	= new Hashtable();
			session.setAttribute("RprtName",	htbRprtName);

			Hashtable htbRprtInpt	= new Hashtable();
			session.setAttribute("RprtInpt",	htbRprtInpt);

			ArrayList arrArryName	= new ArrayList();
			session.setAttribute("ArryName",	arrArryName);
			//session.setAttribute("Sttn",		strSrvgSttn);

			/* All Application level variables are initlized here */
			//Not initialized here, because null cannot be set
			//session.getServletContext().setAttribute("VldtCode", null);
			//session.getServletContext().setAttribute("ErorCode", null);

			logger.info("Sucessfull Execution of GG_LognActn||ATVLDTUSER");
		}
		catch(Exception e)
		{
			if(e instanceof GG_Exception)
			{
				GG_Exception ek		= (GG_Exception)e;

				String strErorDesc	= ek.getErrorCodeMessage(req).replaceAll("\"", "'");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	"");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"" + strErorDesc);
			}
			else
			{
				GG_Exception ge		= new GG_Exception(e.toString(), e);
				logger.error(e.toString() + "\n" + ge.getStacktrace());

				String strErorDesc	= e.toString().replaceAll("\"", "'");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Data%",	"");
				responseXml			= CrisFileStringOpr.replace(responseXml,"%Error%",	"" + strErorDesc);
			}
		}
		finally
		{
			PrintWriter out		= res.getWriter();
			logger.debug("Output::" + responseXml);
			out.println(responseXml);
			logger.info("Exiting GG_LognActn.");
			NDC.pop();
			NDC.remove();
		}
	}
} // end of main