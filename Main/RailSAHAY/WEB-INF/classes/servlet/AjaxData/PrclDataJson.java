package servlet.AjaxData;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
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
import user.UserProfDtls;
import model.FrgtPymtSrvc;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;

import model.PrclDataSrvc;

public class PrclDataJson extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(PrclDataJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling PrclDataJson.java...");
		HttpSession session=req.getSession();

		String si_ClntId = req.getHeader("X-FORWARDED-FOR");
		if (si_ClntId == null)
		{
				si_ClntId = req.getRemoteAddr();
		}
		if (si_ClntId.indexOf(",")>0)
		{
				si_ClntId = si_ClntId.substring(0,si_ClntId.indexOf(","));
		}

		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");

		String si_Optn  = req.getParameter("Optn").toUpperCase().trim();

		if(si_Optn.equals("PRCL_TRANLIST"))
		{
			logger.info("Case: Parcel Trains List");
			String si_FromFlag	= req.getParameter("FromFlag").toUpperCase().trim();
			String si_LocnFrom  = req.getParameter("LocnFrom").toUpperCase().trim();
			String si_CrntFlag	= req.getParameter("CrntFlag").toUpperCase().trim();
			String si_CrntLocn  = req.getParameter("CrntLocn").toUpperCase().trim();
			String si_ToFlag	= req.getParameter("ToFlag").toUpperCase().trim();
			String si_LocnTo	= req.getParameter("LocnTo").toUpperCase().trim();

			String si_TranNo  = req.getParameter("TranNo").toUpperCase().trim();
			String si_TranName  = req.getParameter("TranName").toUpperCase().trim();

			logger.info("si_FromFlag:"+si_FromFlag+", si_LocnFrom:"+si_LocnFrom+", si_CrntFlag:"+ si_CrntFlag+", si_CrntLocn:"+si_CrntLocn+", si_ToFlag:"+si_LocnTo+", si_TranNo:"+si_TranNo+", si_TranName:"+si_TranName);

            PrclDataSrvc obj=new PrclDataSrvc(si_ClntId);

			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.fetchFBDPrclData(si_FromFlag, si_LocnFrom, si_CrntFlag, si_CrntLocn, si_ToFlag, si_LocnTo, si_TranNo, si_TranName);
				String strCallStts=obj.getPrclDataStts();
				if(!strCallStts.equals(""))
				{
					strData="{\"Stts\":\""+strCallStts+"\"}";
				}
				else
				{
					strData="{\"Stts\":\"SUCCESS\",\"PrclTranList\":[";
					int intDataLen=strSrvcData.length;
					logger.info("Parcel Trains List Length:"+intDataLen);
					for(int i=0;i<intDataLen;i++)
					{
						if(i==(intDataLen-1))
							strData+="{\"TranNo\":\""+strSrvcData[i][0]+"\",\"TranType\":\""+strSrvcData[i][1]+"\",\"TranName\":\""+strSrvcData[i][2]+"\",\"SubType\":\""+strSrvcData[i][3]+"\",\"SubTypeDesc\":\""+strSrvcData[i][4]+"\",\"From\":\""+strSrvcData[i][5]+"\",\"FromName\":\""+strSrvcData[i][6]+"\",\"FromDvsn\":\""+strSrvcData[i][7]+"\",\"FromZone\":\""+strSrvcData[i][8]+"\",\"FromDstt\":\""+strSrvcData[i][9]+"\",\"FromStat\":\""+strSrvcData[i][10]+"\",\"StrtTime\":\""+strSrvcData[i][11]+"\",\"To\":\""+strSrvcData[i][12]+"\",\"ToName\":\""+strSrvcData[i][13]+"\",\"ToDvsn\":\""+strSrvcData[i][14]+"\",\"ToZone\":\""+strSrvcData[i][15]+"\",\"ToDstt\":\""+strSrvcData[i][16]+"\",\"ToStat\":\""+strSrvcData[i][17]+"\",\"Crnt\":\""+strSrvcData[i][18]+"\",\"CrntName\":\""+strSrvcData[i][19]+"\",\"CrntDvsn\":\""+strSrvcData[i][20]+"\",\"CrntZone\":\""+strSrvcData[i][21]+"\",\"CrntDstt\":\""+strSrvcData[i][22]+"\",\"CrntStat\":\""+strSrvcData[i][23]+"\",\"Lat\":\""+strSrvcData[i][24]+"\",\"Lng\":\""+strSrvcData[i][25]+"\",\"Stts\":\""+strSrvcData[i][26]+"\",\"UpdtTime\":\""+strSrvcData[i][27]+"\",\"ActlDate\":\""+strSrvcData[i][28]+"\",\"ETA\":\""+strSrvcData[i][29]+"\",\"Dly\":\""+strSrvcData[i][30]+"\",\"PtTranCont\":\""+strSrvcData[i][31]+"\"}";
						else
							strData+="{\"TranNo\":\""+strSrvcData[i][0]+"\",\"TranType\":\""+strSrvcData[i][1]+"\",\"TranName\":\""+strSrvcData[i][2]+"\",\"SubType\":\""+strSrvcData[i][3]+"\",\"SubTypeDesc\":\""+strSrvcData[i][4]+"\",\"From\":\""+strSrvcData[i][5]+"\",\"FromName\":\""+strSrvcData[i][6]+"\",\"FromDvsn\":\""+strSrvcData[i][7]+"\",\"FromZone\":\""+strSrvcData[i][8]+"\",\"FromDstt\":\""+strSrvcData[i][9]+"\",\"FromStat\":\""+strSrvcData[i][10]+"\",\"StrtTime\":\""+strSrvcData[i][11]+"\",\"To\":\""+strSrvcData[i][12]+"\",\"ToName\":\""+strSrvcData[i][13]+"\",\"ToDvsn\":\""+strSrvcData[i][14]+"\",\"ToZone\":\""+strSrvcData[i][15]+"\",\"ToDstt\":\""+strSrvcData[i][16]+"\",\"ToStat\":\""+strSrvcData[i][17]+"\",\"Crnt\":\""+strSrvcData[i][18]+"\",\"CrntName\":\""+strSrvcData[i][19]+"\",\"CrntDvsn\":\""+strSrvcData[i][20]+"\",\"CrntZone\":\""+strSrvcData[i][21]+"\",\"CrntDstt\":\""+strSrvcData[i][22]+"\",\"CrntStat\":\""+strSrvcData[i][23]+"\",\"Lat\":\""+strSrvcData[i][24]+"\",\"Lng\":\""+strSrvcData[i][25]+"\",\"Stts\":\""+strSrvcData[i][26]+"\",\"UpdtTime\":\""+strSrvcData[i][27]+"\",\"ActlDate\":\""+strSrvcData[i][28]+"\",\"ETA\":\""+strSrvcData[i][29]+"\",\"Dly\":\""+strSrvcData[i][30]+"\",\"PtTranCont\":\""+strSrvcData[i][31]+"\"},";
					}
					strData+="]}";
				}
				logger.info(strData);
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}
		if(si_Optn.equals("PSGR_TRAN_PPLN"))
		{
			logger.info("Case: Parcel Train Pipeline");

			String USER_AGENT = "Mozilla/5.0";
			String[][] strData=null;
			String url="";
			String strOutData="";
			String si_TranNo	= req.getParameter("TranNo").toUpperCase().trim();
			String si_StrtDate	= req.getParameter("StrtDate").toUpperCase().trim();
			logger.info(si_TranNo+"       "+si_StrtDate);
			StringBuffer response=null;
			logger.info("Input Detail- Train No.:"+si_TranNo+", StartDate:"+si_StrtDate);
			url="http://10.60.200.171/icmsservices/Controller?action=FOIS&service=singleTrainRunning&userName=FoIs&password=fOiS&trainNo="+si_TranNo+"&startDate="+si_StrtDate;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String urlParameters="";
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			logger.info("\nSending 'POST' request to URL : " + url);
			logger.info("Post parameters : " + urlParameters);
			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			res.getWriter().write(response.toString());
		}
		if(si_Optn.equals("PRCL_TRAN_OCPN"))
		{
			logger.info("Case: Parcel Train Occupancy");
			String USER_AGENT = "Mozilla/5.0";
			String si_TranNumb	= req.getParameter("TranName").toUpperCase().trim();
			String si_StrtDate	= req.getParameter("StrtDate").toUpperCase().trim();
			String[][] strData=null;
			String url="";
			String strOutData="";
			StringBuffer response=null;
			url="http://10.64.1.150/covidtrn/service/trainmaster/getSpaceInTrain";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			con.setDoOutput(true);
			String input = "{\"trnno\":\""+si_TranNumb+"\",\"jrnydt\":\""+si_StrtDate+"\"}";
			OutputStream os = con.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			int responseCode = con.getResponseCode();
			logger.info("\nSending 'POST' request to URL : " + url);
			logger.info("Post parameters : " + input);
			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			strData=new String[1][4];
			try{
				logger.info(response.toString());
				JSONObject jo=new JSONObject(response.toString());
				logger.info("JSONObject Created");
				String serviceStatus = jo.getString("ServiceMessage");
				logger.info("serviceStatus:"+serviceStatus);
			}
			catch(Exception e)
			{
				logger.info("Exception Occurred in DataJson");
			}

			res.getWriter().write(response.toString());
		}
		if(si_Optn.equals("PRCL_TRAN_CONSIST"))
		{
			logger.info("Case: Parcel Train Consist");
			String USER_AGENT = "Mozilla/5.0";
			String si_TranNumb	= req.getParameter("TranName").toUpperCase().trim();
			String si_StrtDate	= req.getParameter("StrtDate").toUpperCase().trim();
			String[][] strData=null;
			String url="";
			String strOutData="";
			StringBuffer response=null;
			url="http://10.64.3.36:9080/covidtrn/service/trainmaster/getWagonArray";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			con.setDoOutput(true);
			String input = "{\"trnno\":\""+si_TranNumb+"\",\"jrnydt\":\""+si_StrtDate+"\"}";
			OutputStream os = con.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			int responseCode = con.getResponseCode();
			logger.info("\nSending 'POST' request to URL : " + url);
			logger.info("Post parameters : " + input);
			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			strData=new String[1][4];
			try{
				logger.info(response.toString());
				JSONObject jo=new JSONObject(response.toString());
				logger.info("JSONObject Created");
				String serviceStatus = jo.getString("ServiceMessage");
				logger.info("serviceStatus:"+serviceStatus);
			}
			catch(Exception e)
			{
				logger.info("Exception Occurred in DataJson");
			}

			res.getWriter().write(response.toString());
		}
	}

}