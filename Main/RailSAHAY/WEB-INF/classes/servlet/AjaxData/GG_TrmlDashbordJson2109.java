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

import model.GG_TrmlDashbord;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;
import java.util.HashMap;

public class GG_TrmlDashbordJson extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_TrmlDashbordJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GG_TrmlDashbordJson.java...");
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
		if(si_Optn.equals("SMRY_STATS"))
		{
				logger.info("Case: Summary Statistics");
				String si_LocnType	= req.getParameter("LocnType").toUpperCase().trim();
				String si_Locn	= req.getParameter("Locn").toUpperCase().trim();
				String si_Cmdt	= req.getParameter("Cmdt").toUpperCase().trim();
				String si_Lat	= req.getParameter("Lat").toUpperCase().trim();
				String si_Lng	= req.getParameter("Lng").toUpperCase().trim();
				logger.info("LocnType:"+si_LocnType+", Locn:"+si_Locn+", Cmdt:"+si_Cmdt+", Lat:"+si_Lat+", Lng:"+si_Lng);
				GG_TrmlDashbord obj=new GG_TrmlDashbord(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[];
						String strData="";
						strSrvcData=obj.getSmryStats(si_LocnType, si_Locn, si_Cmdt, si_Lat, si_Lng);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"";
						for(int i=0;i<strSrvcData.length;i++)
						{
							strData+=",\"Stat"+i+"\":\""+strSrvcData[i]+"\"";
						}
						strData+="}";
						/*logger.info(strData);*/
						res.getWriter().write(strData);
				}
				catch(Exception e)
				{
						System.out.println("Error in Data Fetch");
						res.getWriter().write("Data Contains Error! or No Records");
				}
		}
		if(si_Optn.equals("TRML_LIST"))
		{
				logger.info("Case: Terminal List");
				String si_LocnType	= req.getParameter("LocnType").toUpperCase().trim();
				String si_Locn	= req.getParameter("Locn").toUpperCase().trim();
				String si_Cmdt	= req.getParameter("Cmdt").toUpperCase().trim();
				String si_Lat	= req.getParameter("Lat").toUpperCase().trim();
				String si_Lng	= req.getParameter("Lng").toUpperCase().trim();
				String si_TrmlType	= req.getParameter("TrmlType").toUpperCase().trim();
				logger.info("LocnType:"+si_LocnType+", Locn:"+si_Locn+", Cmdt:"+si_Cmdt+", Lat:"+si_Lat+", Lng:"+si_Lng+", TrmlType:"+si_TrmlType);

				GG_TrmlDashbord obj=new GG_TrmlDashbord(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						strSrvcData=obj.getTrmlList(si_LocnType, si_Locn, si_Cmdt, si_Lat, si_Lng, si_TrmlType);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TrmlList\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Terminal List Length:"+intDataLen);

						for(int i=0;i<intDataLen;i++)
						{
								if(i==(intDataLen-1))
										strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Dstt\":\""+strSrvcData[i][3]+"\",\"AgCont\":\""+strSrvcData[i][4]+"\",\"TrckCont\":\""+strSrvcData[i][5]+"\",\"WHCont\":\""+strSrvcData[i][6]+"\",\"LabCont\":\""+strSrvcData[i][7]+"\",\"Lat\":\""+strSrvcData[i][8]+"\",\"Lng\":\""+strSrvcData[i][9]+"\",\"Fcty\":\""+strSrvcData[i][10]+"\"}";
								else
										strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Dstt\":\""+strSrvcData[i][3]+"\",\"AgCont\":\""+strSrvcData[i][4]+"\",\"TrckCont\":\""+strSrvcData[i][5]+"\",\"WHCont\":\""+strSrvcData[i][6]+"\",\"LabCont\":\""+strSrvcData[i][7]+"\",\"Lat\":\""+strSrvcData[i][8]+"\",\"Lng\":\""+strSrvcData[i][9]+"\",\"Fcty\":\""+strSrvcData[i][10]+"\"},";
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
		if(si_Optn.equals("BRKUP_STATS"))
		{
				logger.info("Case: Breakup Statistics");
				String si_TrmlType	= req.getParameter("TrmlType").toUpperCase().trim();
				String si_BrkType	= req.getParameter("BrkupType").toUpperCase().trim();
				String si_Cmdt		= req.getParameter("Cmdt").toUpperCase().trim();
				logger.info("si_TrmlType:"+si_TrmlType+", si_BrkType:"+si_BrkType+", Cmdt:"+si_Cmdt);

				GG_TrmlDashbord obj=new GG_TrmlDashbord(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						strSrvcData=obj.getBrkUpList(si_BrkType, si_TrmlType, si_Cmdt);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TrmlList\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Terminal List Length:"+intDataLen);

						for(int i=0;i<intDataLen;i++)
						{
								if(i==(intDataLen-1))
                                                                    strData+="{\"Trty1\":\""+strSrvcData[i][0]+"\",\"Trty2\":\""+strSrvcData[i][1]+"\",\"PFT\":\""+strSrvcData[i][2]+"\",\"CRT\":\""+strSrvcData[i][3]+"\",\"LDNG\":\""+strSrvcData[i][4]+"\",\"ULDG\":\""+strSrvcData[i][5]+"\"}";
								else
                                                                    strData+="{\"Trty1\":\""+strSrvcData[i][0]+"\",\"Trty2\":\""+strSrvcData[i][1]+"\",\"PFT\":\""+strSrvcData[i][2]+"\",\"CRT\":\""+strSrvcData[i][3]+"\",\"LDNG\":\""+strSrvcData[i][4]+"\",\"ULDG\":\""+strSrvcData[i][5]+"\"},";
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