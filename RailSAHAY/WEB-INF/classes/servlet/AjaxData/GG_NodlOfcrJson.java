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

import model.GG_NodlOfcrSrvc;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;
import java.util.HashMap;

public class GG_NodlOfcrJson extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(GG_NodlOfcrJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GG_NodlOfcrJson.java...");
		HttpSession session=req.getSession();
			
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		String si_UserID = "";
		String si_ClntID = "";
		String si_Dvsn = "";
		String si_Zone = "";
		String si_Desg = "";
		try
		{
                    si_UserID		        = (String) session.getAttribute("custuserid");
                }
                catch(Exception e) 
                {
            
                }
                try { 
                    si_ClntID		        = (String) session.getAttribute("ClntID");
                    si_Dvsn			= (String) session.getAttribute("ClntID");
                    si_Zone			= (String) session.getAttribute("ClntID");
                    si_Desg			= (String) session.getAttribute("ClntID");
		}
		catch(Exception e)
		{
			si_ClntID="";
			si_Dvsn= "";
			si_Zone= "";
			si_Desg= "";
		}
        
		String si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		logger.info("si_UserID:"+si_UserID+", si_ClntID:"+si_ClntID+", si_Optn:"+ si_Optn+", si_Dvsn:"+si_Dvsn+", si_Zone:"+si_Zone+", si_Desg:"+si_Desg);
		
		if(si_Optn.equals("SMRY_STATS"))
		{
			logger.info("Case: Summary Statistics");		
				
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);
				
			try
			{
				String strSrvcData[];
				String strData="";
				strSrvcData=obj.getSmryStats();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();
				
				strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"SRPndg\":\""+strSrvcData[0]+"\",\"SRFrwd\":\""+strSrvcData[1]+"\",\"IAPndg\":\""+strSrvcData[2]+"\",\"IAFrwd\":\""+strSrvcData[3]+"\",\"CRPndg\":\""+strSrvcData[4]+"\",\"CRFrwd\":\""+strSrvcData[5]+"\"}";				
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}
		if(si_Optn.equals("CNCR_LIST"))
		{
			logger.info("Case: List of Concerns");
				
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);			
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getCncrList();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"CncrList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"CncrId\":\""+strSrvcData[i][0]+"\",\"CncrType\":\""+strSrvcData[i][2]+"\",\"Ctgr\":\""+strSrvcData[i][3]+"\",\"SubCtgr\":\""+strSrvcData[i][4]+"\",\"FName\":\""+strSrvcData[i][5]+"\",\"LName\":\""+strSrvcData[i][6]+"\",\"Phone\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"Rmrk\":\""+strSrvcData[i][12]+"\",\"UpdtTime\":\""+strSrvcData[i][13]+"\"}";
					else
						strData+="{\"CncrId\":\""+strSrvcData[i][0]+"\",\"CncrType\":\""+strSrvcData[i][2]+"\",\"Ctgr\":\""+strSrvcData[i][3]+"\",\"SubCtgr\":\""+strSrvcData[i][4]+"\",\"FName\":\""+strSrvcData[i][5]+"\",\"LName\":\""+strSrvcData[i][6]+"\",\"Phone\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"Rmrk\":\""+strSrvcData[i][12]+"\",\"UpdtTime\":\""+strSrvcData[i][13]+"\"},";
				}
				strData+="]}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}		
		if(si_Optn.equals("SR_LIST"))
		{
			logger.info("Case: List of Service Requests");
				
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);			
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getSRList();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"CncrList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"SrvcId\":\""+strSrvcData[i][0]+"\",\"SrvcNumb\":\""+strSrvcData[i][2]+"\",\"CustId\":\""+strSrvcData[i][3]+"\",\"FNR\":\""+strSrvcData[i][4]+"\",\"OD\":\""+strSrvcData[i][5]+"\",\"FNRDtls\":\""+strSrvcData[i][6]+"\",\"DmndId\":\""+strSrvcData[i][7]+"\",\"InvcId\":\""+strSrvcData[i][8]+"\",\"SrvcType\":\""+strSrvcData[i][9]+"\",\"Sttn\":\""+strSrvcData[i][10]+"\",\"Dvsn\":\""+strSrvcData[i][11]+"\",\"Text\":\""+strSrvcData[i][13]+"\",\"Mob\":\""+strSrvcData[i][14]+"\",\"Email\":\""+strSrvcData[i][15]+"\",\"Time\":\""+strSrvcData[i][17]+"\"}";
					else
						strData+="{\"SrvcId\":\""+strSrvcData[i][0]+"\",\"SrvcNumb\":\""+strSrvcData[i][2]+"\",\"CustId\":\""+strSrvcData[i][3]+"\",\"FNR\":\""+strSrvcData[i][4]+"\",\"OD\":\""+strSrvcData[i][5]+"\",\"FNRDtls\":\""+strSrvcData[i][6]+"\",\"DmndId\":\""+strSrvcData[i][7]+"\",\"InvcId\":\""+strSrvcData[i][8]+"\",\"SrvcType\":\""+strSrvcData[i][9]+"\",\"Sttn\":\""+strSrvcData[i][10]+"\",\"Dvsn\":\""+strSrvcData[i][11]+"\",\"Text\":\""+strSrvcData[i][13]+"\",\"Mob\":\""+strSrvcData[i][14]+"\",\"Email\":\""+strSrvcData[i][15]+"\",\"Time\":\""+strSrvcData[i][17]+"\"},";
				}
				strData+="]}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}				
		if(si_Optn.equals("IA_LIST"))
		{
			logger.info("Case: List of Information Access");
				
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);			
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getIAList();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"IAList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Id\":\""+strSrvcData[i][0]+"\",\"Numb\":\""+strSrvcData[i][1]+"\",\"UserType\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Phone\":\""+strSrvcData[i][6]+"\",\"Email\":\""+strSrvcData[i][7]+"\",\"CompName\":\""+strSrvcData[i][8]+"\",\"CompType\":\""+strSrvcData[i][9]+"\",\"DsttFrom\":\""+strSrvcData[i][13]+"\",\"SttnFrom\":\""+strSrvcData[i][14]+"\",\"DsttTo\":\""+strSrvcData[i][16]+"\",\"SttnTo\":\""+strSrvcData[i][17]+"\",\"Cmdt\":\""+strSrvcData[i][18]+"\",\"TrfcType\":\""+strSrvcData[i][19]+"\",\"TrfcQty\":\""+strSrvcData[i][20]+"\",\"TrfcFreq\":\""+strSrvcData[i][20]+"\",\"WgonType\":\""+strSrvcData[i][21]+"\",\"Rmrk\":\""+strSrvcData[i][22]+"\",\"UpdtTime\":\""+strSrvcData[i][23]+"\"}";
					else
                                                strData+="{\"Id\":\""+strSrvcData[i][0]+"\",\"Numb\":\""+strSrvcData[i][1]+"\",\"UserType\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Phone\":\""+strSrvcData[i][6]+"\",\"Email\":\""+strSrvcData[i][7]+"\",\"CompName\":\""+strSrvcData[i][8]+"\",\"CompType\":\""+strSrvcData[i][9]+"\",\"DsttFrom\":\""+strSrvcData[i][13]+"\",\"SttnFrom\":\""+strSrvcData[i][14]+"\",\"DsttTo\":\""+strSrvcData[i][16]+"\",\"SttnTo\":\""+strSrvcData[i][17]+"\",\"Cmdt\":\""+strSrvcData[i][18]+"\",\"TrfcType\":\""+strSrvcData[i][19]+"\",\"TrfcQty\":\""+strSrvcData[i][20]+"\",\"TrfcFreq\":\""+strSrvcData[i][20]+"\",\"WgonType\":\""+strSrvcData[i][21]+"\",\"Rmrk\":\""+strSrvcData[i][22]+"\",\"UpdtTime\":\""+strSrvcData[i][23]+"\"},";
				}
				strData+="]}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}						
		if(si_Optn.equals("FRWD_OFCR_LIST"))
		{
			logger.info("Case: List of IR Officers");
				
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);			
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getFrwdOfcrList();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"OfcrList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"LocnType\":\""+strSrvcData[i][0]+"\",\"Locn\":\""+strSrvcData[i][1]+"\",\"Desg\":\""+strSrvcData[i][2]+"\"}";
					else
						strData+="{\"LocnType\":\""+strSrvcData[i][0]+"\",\"Locn\":\""+strSrvcData[i][1]+"\",\"Desg\":\""+strSrvcData[i][2]+"\"},";
				}
				strData+="]}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}								
		if(si_Optn.equals("SAVE_RESPONSE"))
		{
			logger.info("Case: Save Response");
			
			String si_TktId			= req.getParameter("TktId").toUpperCase().trim();	
			String si_RespFrwdFlag	= req.getParameter("Flag").toUpperCase().trim();	
			String si_TktType		= req.getParameter("TktType").toUpperCase().trim();	
			String si_RespText		= req.getParameter("RespText").toUpperCase().trim();
			String si_FrwdDvsn		= req.getParameter("FwdDvsn").toUpperCase().trim();
			String si_FrwdDOfcr		= req.getParameter("FwdDOfcr").toUpperCase().trim();
			String si_FrwdZone		= req.getParameter("FwdZone").toUpperCase().trim();
			String si_FrwdZOfcr		= req.getParameter("FwdZOfcr").toUpperCase().trim();
			
			GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc(si_UserID, si_ClntID, si_Dvsn, si_Zone, si_Desg);			
				
			try
			{
				String strSrvcData;
				String strData="";
				strSrvcData=obj.saveResponse(si_TktId,si_RespFrwdFlag, si_TktType,si_RespText,si_FrwdDvsn,si_FrwdDOfcr, si_FrwdZone, si_FrwdZOfcr);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"RetVal\":\""+strSrvcData+"\"}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}
	}
}