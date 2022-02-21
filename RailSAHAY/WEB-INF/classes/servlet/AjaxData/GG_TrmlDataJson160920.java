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

import model.GG_TrmlUtilSrvc;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;
import java.util.HashMap;

public class GG_TrmlDataJson extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(GG_TrmlDataJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GG_TrmlDataJson.java...");
		HttpSession session=req.getSession();
			
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		String si_UserID = "";
		String si_ClntID = "";
		try
		{
			si_UserID		= (String) session.getAttribute("UserID");
        	si_ClntID		= (String) session.getAttribute("ClntID");
		}
		catch(Exception e)
		{
			si_UserID="";
			si_ClntID="";
		}
        
		String si_Optn	= req.getParameter("Optn").toUpperCase().trim();	
		logger.info("si_Optn:"+ si_Optn);
		
		if(si_Optn.equals("STTN_LIST"))
		{
			logger.info("Case: Station List");			
			String si_Sttn		= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("si_Sttn:"+si_Sttn);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getTrmlList(si_Sttn);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();
				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				String strSttn="";
				String strDvsn="";
				String strZone="";
				String strStat="";
				String strFcty="";
				
				for(int i=0;i<intDataLen;i++)
				{							
					strSttn=strSrvcData[i][0];
					strDvsn=strSrvcData[i][1];
					strZone=strSrvcData[i][2];
					strStat=strSrvcData[i][3];
					strFcty=strSrvcData[i][4];

					if(i==(intDataLen-1))
						strData+="{\"Sttn\":\""+strSttn+"\",\"Dvsn\":\""+strDvsn+"\",\"Zone\":\""+strZone+"\",\"Stat\":\""+strStat+"\",\"Fcty\":\""+strFcty+"\"}";
					else
						strData+="{\"Sttn\":\""+strSttn+"\",\"Dvsn\":\""+strDvsn+"\",\"Zone\":\""+strZone+"\",\"Stat\":\""+strStat+"\",\"Fcty\":\""+strFcty+"\"},";				
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
		if(si_Optn.equals("STTN_PROFILE"))
		{
			logger.info("Case: Station Profile");			
			String si_Sttn		= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("si_Sttn:"+si_Sttn);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getTrmlProf(si_Sttn);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnProf\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"TrfcIwrd\":\""+strSrvcData[i][1]+"\",\"TrfcOwrd\":\""+strSrvcData[i][2]+"\",\"HalfRake\":\""+strSrvcData[i][3]+"\",\"FullRake\":\""+strSrvcData[i][4]+"\",\"Dvsn\":\""+strSrvcData[i][5]+"\",\"Zone\":\""+strSrvcData[i][6]+"\",\"DIFlag\":\""+strSrvcData[i][7]+"\",\"Srvg\":\""+strSrvcData[i][8]+"\",\"Dstt\":\""+strSrvcData[i][9]+"\",\"DsttName\":\""+strSrvcData[i][10]+"\",\"Smart\":\""+strSrvcData[i][11]+"\",\"Fcty1\":\""+strSrvcData[i][12]+"\",\"HighPlat\":\""+strSrvcData[i][13]+"\",\"LoosSdng\":\""+strSrvcData[i][14]+"\",\"Fcty3\":\""+strSrvcData[i][15]+"\",\"CmdtIW\":\""+strSrvcData[i][16]+"\",\"CmdtOW\":\""+strSrvcData[i][17]+"\",\"BsnsHrs\":\""+strSrvcData[i][18]+"\",\"WrkgHrs\":\""+strSrvcData[i][19]+"\",\"TrmlType\":\""+strSrvcData[i][20]+"\"}";
					else
                                                strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"TrfcIwrd\":\""+strSrvcData[i][1]+"\",\"TrfcOwrd\":\""+strSrvcData[i][2]+"\",\"HalfRake\":\""+strSrvcData[i][3]+"\",\"FullRake\":\""+strSrvcData[i][4]+"\",\"Dvsn\":\""+strSrvcData[i][5]+"\",\"Zone\":\""+strSrvcData[i][6]+"\",\"DIFlag\":\""+strSrvcData[i][7]+"\",\"Srvg\":\""+strSrvcData[i][8]+"\",\"Dstt\":\""+strSrvcData[i][9]+"\",\"DsttName\":\""+strSrvcData[i][10]+"\",\"Smart\":\""+strSrvcData[i][11]+"\",\"Fcty1\":\""+strSrvcData[i][12]+"\",\"HighPlat\":\""+strSrvcData[i][13]+"\",\"LoosSdng\":\""+strSrvcData[i][14]+"\",\"Fcty3\":\""+strSrvcData[i][15]+"\",\"CmdtIW\":\""+strSrvcData[i][16]+"\",\"CmdtOW\":\""+strSrvcData[i][17]+"\",\"BsnsHrs\":\""+strSrvcData[i][18]+"\",\"WrkgHrs\":\""+strSrvcData[i][19]+"\",\"TrmlType\":\""+strSrvcData[i][20]+"\"},";
				}
				strData+="]";
				strSrvcData=obj.getAddlData();				
                                intDataLen=strSrvcData.length;
				logger.info("Nodal Officers List:"+intDataLen);
                                strData+=",\"NodlOfcr\":[";
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Ofcr\":\""+strSrvcData[i][0]+"\",\"Mob\":\""+strSrvcData[i][1]+"\",\"OfcLL\":\""+strSrvcData[i][2]+"\"}";
    					else
                                                strData+="{\"Ofcr\":\""+strSrvcData[i][0]+"\",\"Mob\":\""+strSrvcData[i][1]+"\",\"OfcLL\":\""+strSrvcData[i][2]+"\"},";
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
		if(si_Optn.equals("SMART_FCTY"))
		{
			logger.info("Case: Station Profile");			
			String si_Sttn		= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("si_Sttn:"+si_Sttn);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getSMARTFcty(si_Sttn);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SMARTFcty\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=1;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"LSPName\":\""+strSrvcData[i][0]+"\",\"GSTN\":\""+strSrvcData[i][1]+"\",\"Addr\":\""+strSrvcData[i][2]+"\",\"City\":\""+strSrvcData[i][3]+"\",\"Stat\":\""+strSrvcData[i][4]+"\",\"Pin\":\""+strSrvcData[i][5]+"\",\"ContPers\":\""+strSrvcData[i][6]+"\",\"Mob\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"FacType\":\""+strSrvcData[i][9]+"\",\"FacDesc\":\""+strSrvcData[i][10]+"\",\"AddlInfo\":\""+strSrvcData[i][11]+"\",\"FctyContPers\":\""+strSrvcData[i][12]+"\",\"FctyMob\":\""+strSrvcData[i][13]+"\",\"FctyEmail\":\""+strSrvcData[i][14]+"\",\"SrvgCont\":\""+strSrvcData[i][15]+"\",\"LSPType\":\""+strSrvcData[i][16]+"\",\"URL\":\""+strSrvcData[i][17]+"\"}";
					else
						strData+="{\"LSPName\":\""+strSrvcData[i][0]+"\",\"GSTN\":\""+strSrvcData[i][1]+"\",\"Addr\":\""+strSrvcData[i][2]+"\",\"City\":\""+strSrvcData[i][3]+"\",\"Stat\":\""+strSrvcData[i][4]+"\",\"Pin\":\""+strSrvcData[i][5]+"\",\"ContPers\":\""+strSrvcData[i][6]+"\",\"Mob\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"FacType\":\""+strSrvcData[i][9]+"\",\"FacDesc\":\""+strSrvcData[i][10]+"\",\"AddlInfo\":\""+strSrvcData[i][11]+"\",\"FctyContPers\":\""+strSrvcData[i][12]+"\",\"FctyMob\":\""+strSrvcData[i][13]+"\",\"FctyEmail\":\""+strSrvcData[i][14]+"\",\"SrvgCont\":\""+strSrvcData[i][15]+"\",\"LSPType\":\""+strSrvcData[i][16]+"\",\"URL\":\""+strSrvcData[i][17]+"\"},";				
				}
				strData+="], \"AgCont\":\""+strSrvcData[0][0]+"\", \"TrckCont\":\""+strSrvcData[0][1]+"\", \"WHCont\":\""+strSrvcData[0][2]+"\", \"LabCont\":\""+strSrvcData[0][3]+"\", \"Disclaim\":\""+strSrvcData[0][4]+"\"}";
				/*logger.info(strData);*/
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}
		if(si_Optn.equals("NEAR_LOCN_FCTY"))
		{
			logger.info("Case: Station Profile");			
			String si_Sttn		= req.getParameter("Sttn").toUpperCase().trim();
			logger.info("si_Sttn:"+si_Sttn);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getNearLocnFcty(si_Sttn);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"NearLocnFcty\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Dstt\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Dist\":\""+strSrvcData[i][5]+"\",\"Stat\":\""+strSrvcData[i][6]+"\",\"AgCont\":\""+strSrvcData[i][7]+"\",\"TrckCont\":\""+strSrvcData[i][8]+"\",\"WHCont\":\""+strSrvcData[i][9]+"\",\"LabCont\":\""+strSrvcData[i][10]+"\"}";
					else
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Dstt\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Dist\":\""+strSrvcData[i][5]+"\",\"Stat\":\""+strSrvcData[i][6]+"\",\"AgCont\":\""+strSrvcData[i][7]+"\",\"TrckCont\":\""+strSrvcData[i][8]+"\",\"WHCont\":\""+strSrvcData[i][9]+"\",\"LabCont\":\""+strSrvcData[i][10]+"\"},";				
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
		if(si_Optn.equals("PMS_BKNG"))
		{
			logger.info("Case: CRT Locations");	
			
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getPMSBkngLocn();
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Corridor\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\"}";
					else
						strData+="{\"Corridor\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\"},";				
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
		if(si_Optn.equals("CRT"))
		{
			logger.info("Case: CRT Locations");			
			String si_LocnFlag		= req.getParameter("LocnFlag").toUpperCase().trim();
			String si_Locn		= req.getParameter("Locn").toUpperCase().trim();
			String si_Cmdt		= req.getParameter("Cmdt").toUpperCase().trim();
			String si_Lat		= req.getParameter("Lat").toUpperCase().trim();
			String si_Lng		= req.getParameter("Lng").toUpperCase().trim();
			
			logger.info("si_LocnFlag:"+si_LocnFlag+", si_Locn:"+si_Locn+", si_Cmdt:"+si_Cmdt+", si_Lat:"+si_Lat+", si_Lng:"+si_Lng);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getCRTLocn(si_LocnFlag,si_Locn,si_Cmdt,si_Lat,si_Lng);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"}";
					else
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"},";				
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
		if(si_Optn.equals("PFT"))
		{
			logger.info("Case: PFT Locations");			
			String si_LocnFlag		= req.getParameter("LocnFlag").toUpperCase().trim();
			String si_Locn		= req.getParameter("Locn").toUpperCase().trim();
			String si_Cmdt		= req.getParameter("Cmdt").toUpperCase().trim();
			String si_Lat		= req.getParameter("Lat").toUpperCase().trim();
			String si_Lng		= req.getParameter("Lng").toUpperCase().trim();
			
			logger.info("si_LocnFlag:"+si_LocnFlag+", si_Locn:"+si_Locn+", si_Cmdt:"+si_Cmdt+", si_Lat:"+si_Lat+", si_Lng:"+si_Lng);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getPFTLocn(si_LocnFlag,si_Locn,si_Cmdt,si_Lat,si_Lng);

				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"}";
					else
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"},";				
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
		if(si_Optn.equals("LDNG"))
		{
			logger.info("Case: LDNG Locations");			
			String si_LocnFlag		= req.getParameter("LocnFlag").toUpperCase().trim();
			String si_Locn		= req.getParameter("Locn").toUpperCase().trim();
			String si_Cmdt		= req.getParameter("Cmdt").toUpperCase().trim();
			String si_Lat		= req.getParameter("Lat").toUpperCase().trim();
			String si_Lng		= req.getParameter("Lng").toUpperCase().trim();
			
			logger.info("si_LocnFlag:"+si_LocnFlag+", si_Locn:"+si_Locn+", si_Cmdt:"+si_Cmdt+", si_Lat:"+si_Lat+", si_Lng:"+si_Lng);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getLdngLocn(si_LocnFlag,si_Locn,si_Cmdt,si_Lat,si_Lng);

				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"}";
					else
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"},";				
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
		if(si_Optn.equals("ULDG"))
		{
			logger.info("Case: ULDG Locations");			
			String si_LocnFlag		= req.getParameter("LocnFlag").toUpperCase().trim();
			String si_Locn		= req.getParameter("Locn").toUpperCase().trim();
			String si_Cmdt		= req.getParameter("Cmdt").toUpperCase().trim();
			String si_Lat		= req.getParameter("Lat").toUpperCase().trim();
			String si_Lng		= req.getParameter("Lng").toUpperCase().trim();
			
			logger.info("si_LocnFlag:"+si_LocnFlag+", si_Locn:"+si_Locn+", si_Cmdt:"+si_Cmdt+", si_Lat:"+si_Lat+", si_Lng:"+si_Lng);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getUldgLocn(si_LocnFlag,si_Locn,si_Cmdt,si_Lat,si_Lng);

				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SttnList\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Station List Length:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{							
					if(i==(intDataLen-1))
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"}";
					else
						strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"ZoneName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"DvsnName\":\""+strSrvcData[i][3]+"\",\"Sttn\":\""+strSrvcData[i][4]+"\",\"SttnName\":\""+strSrvcData[i][5]+"\",\"Dstt\":\""+strSrvcData[i][6]+"\",\"Stat\":\""+strSrvcData[i][7]+"\",\"AgCont\":\""+strSrvcData[i][8]+"\",\"TrckCont\":\""+strSrvcData[i][9]+"\",\"WHCont\":\""+strSrvcData[i][10]+"\",\"LabCont\":\""+strSrvcData[i][11]+"\"},";				
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
		if(si_Optn.equals("TTROUT"))
		{
			logger.info("Case: Time Tabled Routes");			
			String si_SttnFrom		= req.getParameter("SttnFrom").toUpperCase().trim();
			String si_SttnTo		= req.getParameter("SttnTo").toUpperCase().trim();
			
			logger.info("si_SttnFrom:"+si_SttnFrom+", si_SttnTo:"+si_SttnTo);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getTimeTblRout(si_SttnFrom,si_SttnTo);

				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TTRout\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Total Timetabled Routes:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{	
					if(i==(intDataLen-1))
						strData+="{\"Schd\":\""+strSrvcData[i][0]+"\",\"SttnFrom\":\""+strSrvcData[i][1]+"\",\"SttnFromName\":\""+strSrvcData[i][2]+"\",\"DvsnFrom\":\""+strSrvcData[i][3]+"\",\"SttnTo\":\""+strSrvcData[i][4]+"\",\"SttnToName\":\""+strSrvcData[i][5]+"\",\"DvsnTo\":\""+strSrvcData[i][6]+"\",\"StrtTime\":\""+strSrvcData[i][7]+"\",\"TotlTime\":\""+strSrvcData[i][8]+"\",\"Mon\":\""+strSrvcData[i][9]+"\",\"Tue\":\""+strSrvcData[i][10]+"\",\"Wed\":\""+strSrvcData[i][11]+"\",\"Thu\":\""+strSrvcData[i][12]+"\",\"Fri\":\""+strSrvcData[i][13]+"\",\"Sat\":\""+strSrvcData[i][14]+"\",\"Sun\":\""+strSrvcData[i][15]+"\",\"ArvlTime\":\""+strSrvcData[i][16]+"\",\"DprtTime\":\""+strSrvcData[i][17]+"\"}";
					else
						strData+="{\"Schd\":\""+strSrvcData[i][0]+"\",\"SttnFrom\":\""+strSrvcData[i][1]+"\",\"SttnFromName\":\""+strSrvcData[i][2]+"\",\"DvsnFrom\":\""+strSrvcData[i][3]+"\",\"SttnTo\":\""+strSrvcData[i][4]+"\",\"SttnToName\":\""+strSrvcData[i][5]+"\",\"DvsnTo\":\""+strSrvcData[i][6]+"\",\"StrtTime\":\""+strSrvcData[i][7]+"\",\"TotlTime\":\""+strSrvcData[i][8]+"\",\"Mon\":\""+strSrvcData[i][9]+"\",\"Tue\":\""+strSrvcData[i][10]+"\",\"Wed\":\""+strSrvcData[i][11]+"\",\"Thu\":\""+strSrvcData[i][12]+"\",\"Fri\":\""+strSrvcData[i][13]+"\",\"Sat\":\""+strSrvcData[i][14]+"\",\"Sun\":\""+strSrvcData[i][15]+"\",\"ArvlTime\":\""+strSrvcData[i][16]+"\",\"DprtTime\":\""+strSrvcData[i][17]+"\"},";				
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
		if(si_Optn.equals("TTSCHD"))
		{
			logger.info("Case: Time Tabled Schedule");			
			String si_Schd		= req.getParameter("Schd").toUpperCase().trim();
			
			logger.info("si_Schd:"+si_Schd);
				
			GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getTimeTblSchd(si_Schd);

				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();				
				strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TTSchd\":[";
				int intDataLen=strSrvcData.length;
				logger.info("Total Timetabled Schedule:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{	
					if(i==(intDataLen-1))
						strData+="{\"Sqnc\":\""+strSrvcData[i][0]+"\",\"Sttn\":\""+strSrvcData[i][1]+"\",\"SttnName\":\""+strSrvcData[i][2]+"\",\"Sctn\":\""+strSrvcData[i][3]+"\",\"Dvsn\":\""+strSrvcData[i][4]+"\",\"Zone\":\""+strSrvcData[i][5]+"\",\"ArvlTime\":\""+strSrvcData[i][6]+"\",\"DprtTime\":\""+strSrvcData[i][7]+"\",\"Dist\":\""+strSrvcData[i][8]+"\"}";
					else
						strData+="{\"Sqnc\":\""+strSrvcData[i][0]+"\",\"Sttn\":\""+strSrvcData[i][1]+"\",\"SttnName\":\""+strSrvcData[i][2]+"\",\"Sctn\":\""+strSrvcData[i][3]+"\",\"Dvsn\":\""+strSrvcData[i][4]+"\",\"Zone\":\""+strSrvcData[i][5]+"\",\"ArvlTime\":\""+strSrvcData[i][6]+"\",\"DprtTime\":\""+strSrvcData[i][7]+"\",\"Dist\":\""+strSrvcData[i][8]+"\"},";				
				}
				strData+="],";	

				strSrvcData=obj.getAddlData();
				strData+="\"TTTranMvmt\":[";
				intDataLen=strSrvcData.length;
				logger.info("Total Trains Moving over Timetabled Routes:"+intDataLen);
				
				for(int i=0;i<intDataLen;i++)
				{	
					if(i==(intDataLen-1))
						strData+="{\"From\":\""+strSrvcData[i][0]+"\",\"To\":\""+strSrvcData[i][1]+"\",\"RakeId\":\""+strSrvcData[i][2]+"\",\"Loco\":\""+strSrvcData[i][3]+"\",\"Cmdt\":\""+strSrvcData[i][4]+"\",\"Type\":\""+strSrvcData[i][5]+"\",\"Unts\":\""+strSrvcData[i][6]+"\",\"LoadName\":\""+strSrvcData[i][7]+"\",\"Cnsr\":\""+strSrvcData[i][8]+"\",\"Cnsg\":\""+strSrvcData[i][9]+"\",\"Stts\":\""+strSrvcData[i][10]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"SttsTime\":\""+strSrvcData[i][12]+"\"}";
					else
						strData+="{\"From\":\""+strSrvcData[i][0]+"\",\"To\":\""+strSrvcData[i][1]+"\",\"RakeId\":\""+strSrvcData[i][2]+"\",\"Loco\":\""+strSrvcData[i][3]+"\",\"Cmdt\":\""+strSrvcData[i][4]+"\",\"Type\":\""+strSrvcData[i][5]+"\",\"Unts\":\""+strSrvcData[i][6]+"\",\"LoadName\":\""+strSrvcData[i][7]+"\",\"Cnsr\":\""+strSrvcData[i][8]+"\",\"Cnsg\":\""+strSrvcData[i][9]+"\",\"Stts\":\""+strSrvcData[i][10]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"SttsTime\":\""+strSrvcData[i][12]+"\"},";				
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

	    if(si_Optn.equals("TRACK_CONCERN"))
	    {
	            logger.info("Case: Tracking a Concern");                    
	            String si_ConcernId     = req.getParameter("CnrnId").toUpperCase().trim();
	            
	            logger.info("si_ConcernId:"+si_ConcernId);
	                    
	            GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.trackConcern(si_ConcernId);

	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();                           
	                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"ConcernDtls\":[";
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Concern Detail:"+intDataLen);
	                    
	                    for(int i=0;i<intDataLen;i++)
	                    {                                                       
	                            if(i==(intDataLen-1))
                                    strData+="{\"Type\":\""+strSrvcData[i][2]+"\",\"Ctgr\":\""+strSrvcData[i][3]+"\",\"SubCtgr\":\""+strSrvcData[i][4]+"\",\"FName\":\""+strSrvcData[i][5]+"\",\"LName\":\""+strSrvcData[i][6]+"\",\"Phone\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"Zone\":\""+strSrvcData[i][9]+"\",\"Dvsn\":\""+strSrvcData[i][10]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"Rmrk\":\""+strSrvcData[i][12]+"\",\"UpdtTime\":\""+strSrvcData[i][13]+"\",\"CncrStts\":\""+strSrvcData[i][14]+"\",\"SttsRmrk\":\""+strSrvcData[i][15]+"\",\"RespText\":\""+strSrvcData[i][16]+"\",\"RespTime\":\""+strSrvcData[i][17]+"\"}";
	                            else
                                    strData+="{\"Type\":\""+strSrvcData[i][2]+"\",\"Ctgr\":\""+strSrvcData[i][3]+"\",\"SubCtgr\":\""+strSrvcData[i][4]+"\",\"FName\":\""+strSrvcData[i][5]+"\",\"LName\":\""+strSrvcData[i][6]+"\",\"Phone\":\""+strSrvcData[i][7]+"\",\"Email\":\""+strSrvcData[i][8]+"\",\"Zone\":\""+strSrvcData[i][9]+"\",\"Dvsn\":\""+strSrvcData[i][10]+"\",\"Sttn\":\""+strSrvcData[i][11]+"\",\"Rmrk\":\""+strSrvcData[i][12]+"\",\"UpdtTime\":\""+strSrvcData[i][13]+"\",\"CncrStts\":\""+strSrvcData[i][14]+"\",\"SttsRmrk\":\""+strSrvcData[i][15]+"\",\"RespText\":\""+strSrvcData[i][16]+"\",\"RespTime\":\""+strSrvcData[i][17]+"\"},";
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
	    if(si_Optn.equals("FNR_TRACK_MAP"))
	    {
	            logger.info("Case: Tracking FNR for Map");                    
	            String si_FNRNumb     = req.getParameter("FNR").toUpperCase().trim();
	            
	            logger.info("si_FNRNumb:"+si_FNRNumb);
	                    
	            GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getFNRRoute(si_FNRNumb);

	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();                           
	                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"FNRRoute\":[";
	                    int intDataLen=strSrvcData.length;
	                    logger.info("FNR Route Detail:"+intDataLen);
	                    
	                    for(int i=0;i<intDataLen;i++)
	                    {                                                       
	                            if(i==(intDataLen-1))
	                            strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"ArvlTime\":\""+strSrvcData[i][1]+"\",\"DprtTime\":\""+strSrvcData[i][2]+"\",\"Ordr\":\""+strSrvcData[i][3]+"\",\"Lat\":\""+strSrvcData[i][4]+"\",\"Lng\":\""+strSrvcData[i][5]+"\",\"SttnName\":\""+strSrvcData[i][6]+"\",\"Dvsn\":\""+strSrvcData[i][7]+"\",\"Zone\":\""+strSrvcData[i][8]+"\"}";
	                            else
                                    strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"ArvlTime\":\""+strSrvcData[i][1]+"\",\"DprtTime\":\""+strSrvcData[i][2]+"\",\"Ordr\":\""+strSrvcData[i][3]+"\",\"Lat\":\""+strSrvcData[i][4]+"\",\"Lng\":\""+strSrvcData[i][5]+"\",\"SttnName\":\""+strSrvcData[i][6]+"\",\"Dvsn\":\""+strSrvcData[i][7]+"\",\"Zone\":\""+strSrvcData[i][8]+"\"},";
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

	    if(si_Optn.equals("NODL_OFCR_LIST"))
	    {
	            logger.info("Case: NODL_OFCR_LIST");                    
	            String si_Zone     = req.getParameter("Zone").toUpperCase().trim();
                    String si_Dvsn     = req.getParameter("Dvsn").toUpperCase().trim();
	            
	            logger.info("si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn);
	                    
	            GG_TrmlUtilSrvc obj=new GG_TrmlUtilSrvc(si_UserID,si_ClntID);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getNodlOfcrList(si_Zone, si_Dvsn);

	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();                           
	                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"NodlOfcr\":[";
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Nodal Officers List:"+intDataLen);
	                    
	                    for(int i=0;i<intDataLen;i++)
	                    {                                                       
	                            if(i==(intDataLen-1))
	                            strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Ofcr\":\""+strSrvcData[i][2]+"\",\"Mob\":\""+strSrvcData[i][3]+"\",\"LL\":\""+strSrvcData[i][4]+"\"}";
	                            else
                                    strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Ofcr\":\""+strSrvcData[i][2]+"\",\"Mob\":\""+strSrvcData[i][3]+"\",\"LL\":\""+strSrvcData[i][4]+"\"},";
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
	}
}