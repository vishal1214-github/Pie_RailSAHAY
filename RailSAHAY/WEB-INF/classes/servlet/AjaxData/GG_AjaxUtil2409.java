package servlet.AjaxData;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import org.apache.log4j.Logger;
import util.logger.FoisLogger;

public class GG_AjaxUtil extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_AjaxUtil.class.getName());
	HttpServletResponse response=null;
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{

		req.setCharacterEncoding("UTF-8");
		/*res.setContentType("application/json");*/
		res.setHeader("Cache-Control", "no-cache");
		response=res;
		//Just To View the Information
		Enumeration enumeration = req.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			String name		= (String)enumeration.nextElement();
			String values[]	= req.getParameterValues(name);
		}
		HttpSession session = req.getSession();
		
		String si_DtlsType	= req.getParameter("Qry").toUpperCase().trim();
		String resData="";

		if(si_DtlsType.equals("EMRKT_FCTY_LIST"))
		{
			logger.info("Case: E-Market Facilities List");
			resData=this.getEMrktFctyList();
		}
		if(si_DtlsType.equals("EMRKT_SMRY"))
		{
			logger.info("Case: E-Market Summary");
			resData=this.getEMrktSmry();
		}
		if(si_DtlsType.equals("TRML_FCTY"))
		{
			logger.info("Case: Terminal Facility Search");
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("Station:"+strSttn);
			resData=this.getTrmlFcty(strSttn);
		}
		if(si_DtlsType.equals("TRML_FCTY_SMRY"))
		{
			logger.info("Case: Terminal Facility Search");
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("Station:"+strSttn);
			resData=this.getTrmlFctySmry(strSttn);
		}
		if(si_DtlsType.equals("TRML_FCTY_SMRY_ONLY"))
		{
			logger.info("Case: Terminal Facility Search");
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("Station:"+strSttn);
			resData=this.getTrmlFctySmryOnly(strSttn);
		}
		if(si_DtlsType.equals("NEARLOCN_FCTY"))
		{
			logger.info("Case: Near Location Facility Search");
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("Station:"+strSttn);
			resData=this.getNearLocnFctySmry(strSttn);
		}
		if(si_DtlsType.equals("NEARBY_FCTY"))
		{
			logger.info("Case: Nearby Location Facility Search");
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("Station:"+strSttn);
			resData=this.getNearByFctySmry(strSttn);
		}
		if(si_DtlsType.equals("COMP_FCTY_SRVG"))
		{
			logger.info("Case: Company Facility Serving");
			
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			String strFcty	= req.getParameter("Fcty").toUpperCase().trim();
			String strComp	= req.getParameter("Comp").toUpperCase().trim();
			
			logger.info("Station:"+strSttn+", Facility:"+strFcty+", Company:"+strComp);
			
			resData=this.getCompFctySrvg(strSttn, strFcty, strComp);
		}
		if(si_DtlsType.equals("LSP_FCTY_SRVG"))
		{
			logger.info("Case: LSP Facility Serving");
			
			String strSttn	= req.getParameter("Sttn").toUpperCase().trim();
			String strComp	= req.getParameter("Comp").toUpperCase().trim();
			
			logger.info("Station:"+strSttn+", Company:"+strComp);
			
			resData=this.getLSPFctySrvg(strSttn, strComp);
		}
		logger.info("Sending JSON Data As:"+resData);
		res.getWriter().write(resData);
	}
	public String getEMrktSmry()
	{
		String strData="";
		logger.info("Entered in E-Market Summary (getEMrktSmry)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"EMRKT_SMRY");
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		String strAgCont="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";
		String strCustCont="";
		String strTrmlCont="";
		String strIndCont="";
		String strCompCont="";
		try
		{
			strAgCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",0,"0");
			strTrckCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",1,"0");
			strWHCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",2,"0");
			strLabCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",3,"0");
			strCustCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",4,"0");
			strTrmlCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",5,"0");
			strIndCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",6,"0");
			strCompCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",7,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("strAgCont:"+ strAgCont+", strTrckCont:"+strTrckCont+", strWHCont:"+strWHCont+", strLabCont:"+strLabCont+", strCustCont:"+strCustCont+", strTrmlCont:"+strTrmlCont);
		
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Aggregator\":"+strAgCont+",\"Trucker\":"+strTrckCont+",\"Warehouse\":"+strWHCont+",\"Labour\":"+strLabCont+",\"CustCont\":"+strCustCont+",\"TrmlCont\":"+strTrmlCont+",\"IndCont\":"+strIndCont+",\"CompCont\":"+strCompCont+"}";

		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getEMrktFctyList()
	{
		String strData="";
		logger.info("Entered in Terminal Facility List (getEMrktFctyList)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"EMRKT_FCTY_LIST");
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;
		String strDisclaim = "";

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
			strDisclaim		= RQFGAMISCQRY.getStringItemDef("F_DESC",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strZone="";
		String strDvsn="";
		String strStat="";
		String strDstt="";
		String strSttnCode="";
		String strSttnName="";
		String strAgCont="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";
		String strArry="[";
		for(int k=0; k<=start1-1; k++)
		{
			try
			{
				strZone 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDvsn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strStat 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strDstt 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strSttnCode		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strSttnName		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strAgCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strTrckCont 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strWHCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();
				strLabCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY10",	  	k,	"0").trim();
				
				if(k==0)
					strArry+="{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Stat\":\""+strStat+"\",\"Dstt\":\""+strDstt+"\",\"SttnCode\":\""+strSttnCode+"\",\"SttnName\":\""+strSttnName+"\",\"AgCont\":\""+strAgCont+"\",\"TrckCont\":\""+strTrckCont+"\",\"WHCont\":\""+strWHCont+"\",\"LabCont\":\""+strLabCont+"\"}";
				else
					strArry+=",{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Stat\":\""+strStat+"\",\"Dstt\":\""+strDstt+"\",\"SttnCode\":\""+strSttnCode+"\",\"SttnName\":\""+strSttnName+"\",\"AgCont\":\""+strAgCont+"\",\"TrckCont\":\""+strTrckCont+"\",\"WHCont\":\""+strWHCont+"\",\"LabCont\":\""+strLabCont+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+",\"Disclaim\":\""+strDisclaim+"\"}";
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getTrmlFcty(String si_Trml)
	{
		String strData="";
		logger.info("Entered in Terminal Facility (getTrmlFcty)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"TRML_FACILITY");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strFctyType="";
		String strCompName="";
		String strCompAddr="";
		String strPersDtls="";
		String strFctyAvbl="";
		String strAddlInfo="";
		String strSrvgCont="";
		String strArry="[";
		String strPersArry="[";
		int intPersCntr=0;
		int intTrckCont=0;
		int intWHCont=0;
		int intLabCont=0;
		
		for(int k=0; k<=start1-1; k++)
		{
			intPersCntr=0;
			try
			{
				strFctyType 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strCompName 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strCompAddr 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strPersDtls 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strFctyAvbl 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strAddlInfo 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strSrvgCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				
				if(strFctyType.equals("T"))
					intTrckCont++;
				if(strFctyType.equals("W"))
					intWHCont++;
				if(strFctyType.equals("L"))
					intLabCont++;
				/*
				StringTokenizer st=new StringTokenizer(strPersDtls,"$");
				while(st.hasMoreTokens())
				{
					StringTokenizer st1=new StringTokenizer(st.nextToken(),"#");
					if(intPersCntr==0)
						strPersArry+="{\"Name\":\""+st1.nextToken()+"\",\"Phone\":\""+st1.nextToken()+"\"\"Email\":\""+st1.nextToken()+"\"}";
					else
						strPersArry+=",{\"Name\":\""+st1.nextToken()+"\",\"Phone\":\""+st1.nextToken()+"\"\"Email\":\""+st1.nextToken()+"\"}";
					
					intPersCntr++;
				}
				strPersArry+="]";
				*/
				
				if(k==0)
					strArry+="{\"Type\":\""+strFctyType+"\",\"Comp\":\""+strCompName+"\",\"Addr\":\""+strCompAddr+"\",\"SrvgCont\":\""+strSrvgCont+"\",\"Pers\":\""+strPersDtls+"\",\"FacAvbl\":\""+strFctyAvbl+"\",\"AddlInfo\":\""+strAddlInfo+"\"}";
				else
					strArry+=",{\"Type\":\""+strFctyType+"\",\"Comp\":\""+strCompName+"\",\"Addr\":\""+strCompAddr+"\",\"SrvgCont\":\""+strSrvgCont+"\",\"Pers\":\""+strPersDtls+"\",\"FacAvbl\":\""+strFctyAvbl+"\",\"AddlInfo\":\""+strAddlInfo+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Trucker\":"+intTrckCont+",\"Warehouse\":"+intWHCont+",\"Labour\":"+intLabCont+",\"Data\":"+strArry+"}";
		
		logger.info("Facilities for Terminal ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getTrmlFctySmry(String si_Trml)
	{
		String strData="";
		logger.info("Entered in Terminal Facility Summary (getTrmlFctySmry)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"TRML_FCTY_SMRY");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}

		String strAgCont="0";
		String strTrckCont="0";
		String strWHCont="0";
		String strLbrCont="0";
		String strDisclaim="";
		try
		{
			strAgCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",0,"0");
			strTrckCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",1,"0");
			strWHCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",2,"0");
			strLbrCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",3,"0");
			strDisclaim		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",4,"0");
		}
		catch(Exception e)
		{
			logger.info("Exception in getting Summary Parameters..");
		}
		String strLSPName="";
		String strGST="";
		String strAddr="";
		String strCity="";
		String strStatName="";
		String strPin="";
		String strContPers="";
		String strLSPMob="";
		String strLSPEmail="";
		String strFacType="";
		String strFacDesc="";
		String strAddlInfo="";
		String strFacPers="";
		String strFacMob="";
		String strFacEmail="";
		String strCompSrvg="";
		String strLSPType="";
		String strCompUrl="";
		String strArry="[";
		
		for(int k=0; k<=start1-1; k++)
		{
			try
			{
				strLSPName 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strGST 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strAddr 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strCity 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strStatName = RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strPin 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strContPers = RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strLSPMob 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();
				strLSPEmail = RQFGAMISCQRY.getStringItemDef("F_ORDRBY10",	  	k,	"0").trim();
				strFacType 	= RQFGAMISCQRY.getStringItemDef("F_CLBLUNTS",	  	k,	"0").trim();
				strFacDesc 	= RQFGAMISCQRY.getStringItemDef("F_DSPTUNTS",	  	k,	"0").trim();
				strAddlInfo = RQFGAMISCQRY.getStringItemDef("F_FRMDUNTS",	  	k,	"0").trim();
				strFacPers 	= RQFGAMISCQRY.getStringItemDef("F_EXCPUNTS",	  	k,	"0").trim();
				strFacMob 	= RQFGAMISCQRY.getStringItemDef("F_UNTS",	  	k,	"0").trim();
				strFacEmail = RQFGAMISCQRY.getStringItemDef("F_FRWH",	  	k,	"0").trim();
				strCompSrvg = RQFGAMISCQRY.getStringItemDef("F_OPBLUNTS",	  	k,	"0").trim();
				strLSPType = RQFGAMISCQRY.getStringItemDef("F_RAKEID",	  	k,	"0").trim();
				strCompUrl = RQFGAMISCQRY.getStringItemDef("F_CHRGDESC",	  	k,	"0").trim();
								
				if(k==0)
					strArry+="{\"LSPType\":\""+strLSPType+"\",\"LSP\":\""+strLSPName+"\",\"GST\":\""+strGST+"\",\"Addr\":\""+strAddr+"\",\"City\":\""+strCity+"\",\"Stat\":\""+strStatName+"\",\"Pin\":\""+strPin+"\",\"ContPers\":\""+strContPers+"\",\"LSPMob\":\""+strLSPMob+"\",\"LSPEmail\":\""+strLSPEmail+"\",\"FacType\":\""+strFacType+"\",\"FacDesc\":\""+strFacDesc+"\",\"AddlInfo\":\""+strAddlInfo+"\",\"FacPers\":\""+strFacPers+"\",\"FacMob\":\""+strFacMob+"\",\"FacEmail\":\""+strFacEmail+"\",\"CompSrvg\":\""+strCompSrvg+"\",\"Url\":\""+strCompUrl+"\"}";
				else
					strArry+=",{\"LSPType\":\""+strLSPType+"\",\"LSP\":\""+strLSPName+"\",\"GST\":\""+strGST+"\",\"Addr\":\""+strAddr+"\",\"City\":\""+strCity+"\",\"Stat\":\""+strStatName+"\",\"Pin\":\""+strPin+"\",\"ContPers\":\""+strContPers+"\",\"LSPMob\":\""+strLSPMob+"\",\"LSPEmail\":\""+strLSPEmail+"\",\"FacType\":\""+strFacType+"\",\"FacDesc\":\""+strFacDesc+"\",\"AddlInfo\":\""+strAddlInfo+"\",\"FacPers\":\""+strFacPers+"\",\"FacMob\":\""+strFacMob+"\",\"FacEmail\":\""+strFacEmail+"\",\"CompSrvg\":\""+strCompSrvg+"\",\"Url\":\""+strCompUrl+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"AggrCont\":"+strAgCont+",\"TrckCont\":"+strTrckCont+",\"WHCont\":"+strWHCont+",\"LbrCont\":"+strLbrCont+",\"Disclaim\":\""+strDisclaim+"\",\"Data\":"+strArry+"}";
		logger.info("Facilities for Terminal ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getTrmlFctySmryOnly(String si_Trml)
	{
		String strData="";
		logger.info("Entered in Terminal Facility Summary (getTrmlFctySmryOnly)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"TRML_FCTY_SMRY_ONLY");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String strAgCont="0";
		String strTrckCont="0";
		String strWHCont="0";
		String strLbrCont="0";
		String strDisclaim="";
		try
		{
			strAgCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",0,"0");
			strTrckCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",1,"0");
			strWHCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",2,"0");
			strLbrCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",3,"0");
			strDisclaim		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",4,"0");
		}
		catch(Exception e)
		{
			logger.info("Exception in getting Summary Parameters..");
		}
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"AggrCont\":"+strAgCont+",\"TrckCont\":"+strTrckCont+",\"WHCont\":"+strWHCont+",\"LbrCont\":"+strLbrCont+",\"Disclaim\":\""+strDisclaim+"\"}";
		logger.info("Facilities for Terminal ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getNearByFctySmry(String si_Trml)
	{
		String strData="";
		logger.info("Entered in Nearby Location Facility Summary (getNearByFctySmry)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"NEARBY_FCTY");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strZone="";
		String strDvsn="";
		String strDistrict="";
		String strSttn="";
		String strSttnName="";
		String strDist="";
		String strAgCont="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";		
		String strArry="[";
		
		for(int k=0; k<=start1-1; k++)
		{
			try
			{
				strZone 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDvsn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strDistrict 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strSttn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strSttnName 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strDist 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strAgCont	 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strTrckCont		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strWHCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();
				strLabCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY10",	  	k,	"0").trim();

				if(k==0)
					strArry+="{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Aggregator\":\""+strAgCont+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";
				else
					strArry+=",{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Aggregator\":\""+strAgCont+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
		
		logger.info("Facilities for Terminal ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}

	public String getNearLocnFctySmry(String si_Trml)
	{
		String strData="";
		logger.info("Entered in Near Location Facility Summary (getNearLocnFctySmry)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"NEARLOCN_FACILITY");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strZone="";
		String strDvsn="";
		String strDistrict="";
		String strSttn="";
		String strSttnName="";
		String strDist="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";		
		String strArry="[";
		
		for(int k=0; k<=start1-1; k++)
		{

			try
			{
				strZone 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDvsn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strDistrict 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strSttn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strSttnName 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strDist 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strTrckCont 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strWHCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strLabCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();

				if(k==0)
					strArry+="{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";
				else
					strArry+=",{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
		
		logger.info("Facilities for Terminal ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getCompFctySrvg(String si_Trml, String si_Fcty, String si_Comp)
	{
		String strData="";
		logger.info("Entered in Company Facility Serving (getCompFctySrvg)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"COMP_FCTY_SRVG");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
			RQFGAMISCQRY.setString("F_HLDGZONE1",	0,	si_Fcty);
			RQFGAMISCQRY.setString("F_HLDGZONE2",	0,	si_Comp);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strZone="";
		String strDvsn="";
		String strDistrict="";
		String strSttn="";
		String strSttnName="";
		String strDist="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";		
		String strArry="[";
		
		for(int k=0; k<=start1-1; k++)
		{

			try
			{
				strZone 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDvsn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strDistrict 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strSttn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strSttnName 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strDist 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strTrckCont 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strWHCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strLabCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();

				if(k==0)
					strArry+="{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";
				else
					strArry+=",{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
		
		logger.info("Company Serving Facilities ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
	public String getLSPFctySrvg(String si_Trml, String si_LSP)
	{
		String strData="";
		logger.info("Entered in Company Facility Serving (getLSPFctySrvg)");

		FOISTuxedo RQFGAMISCQRY	= null;
		try
		{
			RQFGAMISCQRY = new FOISTuxedo();
		}
		catch (Exception ne)
		{
			logger.info("Unable to get the Client Object");
		}

		try
		{
			RQFGAMISCQRY.tuxInit("RQFGAMISCQRY");
			RQFGAMISCQRY.setString("F_USERID",		0,	"APP");
			RQFGAMISCQRY.setString("F_CLNTID",		0,	"1.1.1.1");
			RQFGAMISCQRY.setString("F_FLAG",		0,	"LSP_SRVG");
			RQFGAMISCQRY.setString("F_HLDGZONE",	0,	si_Trml);
			RQFGAMISCQRY.setString("F_HLDGZONE1",	0,	si_LSP);
		}
		catch(Exception e)
		{
			//logger.info"Error in Servlet : " + e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQFGAMISCQRY.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.info("Exception while call to the service is " + e.toString());
		}
			//*************************************************************************************
								//END of WTC calling
			//*************************************************************************************
		// Checking For Any Error from Service
		String erorCode			= null;
		try
		{
			erorCode				= RQFGAMISCQRY.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode != null && (!erorCode.equals("")))
		{
			logger.info("ErrorCode: " + erorCode);
			strData="{\"ServiceCall\":\"F\"}";
			return strData;
		}
		
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			startRowCount1		= RQFGAMISCQRY.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
        logger.info("startRowCount1"+ startRowCount1);
		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.info("ErrorCode: " + "No data found");
			strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
			return strData;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		String strZone="";
		String strDvsn="";
		String strDistrict="";
		String strSttn="";
		String strSttnName="";
		String strDist="";
		String strAgCont="";
		String strTrckCont="";
		String strWHCont="";
		String strLabCont="";		
		String strArry="[";
		
		for(int k=0; k<=start1-1; k++)
		{

			try
			{
				strZone 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY1",	  	k,	"0").trim();
				strDvsn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY2",	  	k,	"0").trim();
				strDistrict 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY3",	  	k,	"0").trim();
				strSttn 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY4",	  	k,	"0").trim();
				strSttnName 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY5",	  	k,	"0").trim();
				strDist 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY6",	  	k,	"0").trim();
				strAgCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY7",	  	k,	"0").trim();
				strTrckCont 	= RQFGAMISCQRY.getStringItemDef("F_ORDRBY8",	  	k,	"0").trim();
				strWHCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY9",	  	k,	"0").trim();
				strLabCont 		= RQFGAMISCQRY.getStringItemDef("F_ORDRBY10",	  	k,	"0").trim();

				if(k==0)
					strArry+="{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Aggregator\":\""+strAgCont+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";
				else
					strArry+=",{\"Zone\":\""+strZone+"\",\"Dvsn\":\""+strDvsn+"\",\"Dstt\":\""+strDistrict+"\",\"Sttn\":\""+strSttn+"\",\"SttnName\":\""+strSttnName+"\",\"Dist\":\""+strDist+"\",\"Aggregator\":\""+strAgCont+"\",\"Trucker\":\""+strTrckCont+"\",\"Warehouse\":\""+strWHCont+"\",\"Labour\":\""+strLabCont+"\"}";						
			}
			catch(Exception e)
			{
				//logger.info("Error In End Session:"+e.toString());
			}
        } // End o
		strArry+="]";
		strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
		
		logger.info("Company Serving Facilities ("+si_Trml+"):"+strData);
		try
		{
			RQFGAMISCQRY.endSession();
		}
		catch(Exception e)
		{
			//logger.info("Error In End Session:"+e.toString());
		}
		return strData;
	}
}