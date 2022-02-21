package servlet.AppData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
 
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import tuxedo.*;
import util.logger.FoisLogger;
import util.GenFunc.*;
import java.util.StringTokenizer;

public class GG_PrclTranData extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(GG_PrclTranData.class.getName());
	boolean bolValid=true;
	String strDtls[][];

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		logger.info("In Parcel Movement Detail");
		String si_QryFlag=(String)req.getParameter("Qry").toUpperCase().trim();
		HttpSession sesn=req.getSession();
		String si_FromFlag="";
		String si_ToFlag="";
		String si_LocnFrom="";
		String si_LocnTo="";
		String si_DateFrom="";
		String si_DateTo="";
		String si_TranNumb="";
		String si_StrtDate="";
		RequestDispatcher rd=null;
		logger.info("Query Flag:"+si_QryFlag);
		if(si_QryFlag.equals("TRNLIST"))
		{
			logger.info("Case: Get Train List");
			si_FromFlag=(String)req.getParameter("selLocnFrom").toUpperCase().trim();
			si_LocnFrom=(String)req.getParameter("txtLocnFrom").toUpperCase().trim();
			si_ToFlag=(String)req.getParameter("selLocnTo").toUpperCase().trim();
			si_LocnTo=(String)req.getParameter("txtLocnTo").toUpperCase().trim();
			si_DateFrom=(String)req.getParameter("txtDateFrom").toUpperCase().trim();
			si_DateTo=(String)req.getParameter("txtDateTo").toUpperCase().trim();
			String strLocnFrom=si_LocnFrom;
			String strLocnTo=si_LocnTo;
			/*
			if(si_FromFlag.equals("ST"))
			{
				strLocnFrom=si_LocnFrom.substring(0,si_LocnFrom.indexOf("-"));
				si_LocnFrom=si_LocnFrom.substring(si_LocnFrom.indexOf("-")+1);
			}
			if(si_ToFlag.equals("ST"))
			{
				strLocnTo=si_LocnTo.substring(0,si_LocnTo.indexOf("-"));
				si_LocnTo=si_LocnTo.substring(si_LocnTo.indexOf("-")+1);
			}
                        */
			
			try
			{
				strDtls=this.getPrclTranList(si_FromFlag, strLocnFrom, si_ToFlag, strLocnTo, si_DateFrom, si_DateTo);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Trains List:"+e.getMessage());
				e.printStackTrace();
			}
			sesn.setAttribute("PrclTranList", strDtls);
			sesn.setAttribute("PrclDateFrom", si_DateFrom);
			sesn.setAttribute("PrclDateTo", si_DateTo);
			sesn.setAttribute("PrclFrom", si_LocnFrom);
			sesn.setAttribute("PrclTo", si_LocnTo);
	        	logger.info("SUCCESSFULL");
			rd=req.getRequestDispatcher("pages/PrclMvmtDtls.jsp");
		}
		if(si_QryFlag.equals("NTESTRNLIST"))
		{
			logger.info("Case: Get NTES Train List");
			si_FromFlag=(String)req.getParameter("selLocnFrom").toUpperCase().trim();
			si_LocnFrom=(String)req.getParameter("txtLocnFrom").toUpperCase().trim();
			si_ToFlag=(String)req.getParameter("selLocnTo").toUpperCase().trim();
			si_LocnTo=(String)req.getParameter("txtLocnTo").toUpperCase().trim();
			si_DateFrom=(String)req.getParameter("txtDateFrom").toUpperCase().trim();
			si_DateTo=(String)req.getParameter("txtDateTo").toUpperCase().trim();
			String strLocnFrom=si_LocnFrom;
			String strLocnTo=si_LocnTo;
			/*
			if(si_FromFlag.equals("ST"))
			{
				strLocnFrom=si_LocnFrom.substring(0,si_LocnFrom.indexOf("-"));
				si_LocnFrom=si_LocnFrom.substring(si_LocnFrom.indexOf("-")+1);
			}
			if(si_ToFlag.equals("ST"))
			{
				strLocnTo=si_LocnTo.substring(0,si_LocnTo.indexOf("-"));
				si_LocnTo=si_LocnTo.substring(si_LocnTo.indexOf("-")+1);
			}
                        */
			
			try
			{
				strDtls=this.getNTESPrclTranList(si_FromFlag, strLocnFrom, si_ToFlag, strLocnTo, si_DateFrom, si_DateTo);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Trains List:"+e.getMessage());
				e.printStackTrace();
			}
			sesn.setAttribute("PrclTranList", strDtls);
			sesn.setAttribute("PrclDateFrom", si_DateFrom);
			sesn.setAttribute("PrclDateTo", si_DateTo);
			sesn.setAttribute("PrclFrom", si_LocnFrom);
			sesn.setAttribute("PrclTo", si_LocnTo);
	        	logger.info("SUCCESSFULL");
			rd=req.getRequestDispatcher("pages/PrclMvmtDtls.jsp");
		}
		if(si_QryFlag.equals("TRNSCHD"))
		{
			logger.info("Case: Get Train Schedule");
			si_TranNumb=(String)req.getParameter("TranNumb").toUpperCase().trim();
			si_StrtDate=(String)req.getParameter("StrtDate").toUpperCase().trim();
			String strTranNumb=(si_TranNumb.substring(0,si_TranNumb.indexOf("("))).trim();
			//String strStrtDate=icmsDateFormat(si_StrtDate);
			String strStrtDate=ntesToICMSDateFormat(si_StrtDate);
			logger.info("Train Number:"+strTranNumb+", Start Date:"+strStrtDate);
			try
			{
				strDtls=this.getPrclTranSchd(strTranNumb, strStrtDate);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Train Schedule:"+e.getMessage());
				e.printStackTrace();
			}
			req.setAttribute("Details",strDtls);
			req.setAttribute("TranName",si_TranNumb);
			req.setAttribute("StrtDate",si_StrtDate);
			logger.info("SUCCESSFULL");
			rd=req.getRequestDispatcher("pages/PrclTranSchd.jsp");
		}
		if(si_QryFlag.equals("TRNOCPN"))
		{
			logger.info("Case: Get Train Occupancy");
			si_TranNumb=(String)req.getParameter("TranNumb").toUpperCase().trim();
			si_StrtDate=(String)req.getParameter("StrtDate").toUpperCase().trim();
			String strTranNumb=(si_TranNumb.substring(0,si_TranNumb.indexOf("("))).trim();
			String strStrtDate=pmsDateFormat(si_StrtDate);
			logger.info("Train Number:"+strTranNumb+", Start Date:"+strStrtDate);
			try
			{
				strDtls=this.getPMSPrclTranOcpn(strTranNumb, strStrtDate);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Train Occupancy:"+e.getMessage());
				e.printStackTrace();
			}
			req.setAttribute("Details",strDtls);
			req.setAttribute("TranName",si_TranNumb);
			req.setAttribute("StrtDate",si_StrtDate);
			logger.info("SUCCESSFULL");
			rd=req.getRequestDispatcher("pages/PrclTranOcpn.jsp");
		}
		rd.forward(req,res);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
public String[][] getPrclTranList(String si_LocnFromFlag, String si_LocnFrom, String si_LocnToFlag, String si_LocnTo, String si_DateFrom, String si_DateTo)  throws IOException
{
	logger.info("Inside getPrclTranList- Inputs: "+si_LocnFromFlag+", "+si_LocnFrom+", "+si_LocnToFlag+", "+si_LocnTo+", "+si_DateFrom+", "+si_DateTo);

	String USER_AGENT = "Mozilla/5.0";
	String[][] strData=null;
	String url="";
	String strOutData="";
	
	StringBuffer response=null;			
	url = "http://10.60.200.171/icmsservices/Controller?action=FOIS&service=currentTrainsPositions&userName=FoIs&password=fOiS&trainType=PEXP";
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

	try{
	logger.info(response.toString());
	JSONObject jo=new JSONObject(response.toString());
	logger.info("JSONObject Created");		
	boolean successFlag = jo.getBoolean("successFlag");
	logger.info("Success:"+successFlag);
	if(successFlag) 
	{
				JSONArray data = jo.getJSONArray("data");
				int dataLen=data.length();
				logger.info("dataLen"+ dataLen);
				strData=new String[dataLen][18];
				for(int i = 0; i < dataLen; i++) 
				{
					JSONObject arrElmnt = (JSONObject)data.get(i);

					strData[i][0]= arrElmnt.getString("stnCode");
					strData[i][1]= arrElmnt.getString("actualDate");
					if(((String)arrElmnt.getString("arrDepFlag")).equals("A"))
						strData[i][2]= "AR";
					else
					{
						if(((String)arrElmnt.getString("arrDepFlag")).equals("D"))
							strData[i][2]= "DP";
						else
							strData[i][2]=arrElmnt.getString("arrDepFlag");
					}
					strData[i][3]= arrElmnt.getString("updateTime");
					strData[i][4]= arrElmnt.getString("trainNo");
					strData[i][5]= arrElmnt.getString("trainType");
					strData[i][6]= foisDateFormat(arrElmnt.getString("startDate"));
					strData[i][7]= arrElmnt.getString("trainSrc");
					strData[i][8]= foisDateFormat(arrElmnt.getString("startDate"));
					strData[i][9]= arrElmnt.getString("trainDstn");
					strData[i][10]= arrElmnt.getString("etaAtDstn");
					strData[i][11]= arrElmnt.getString("delayAtStn");
					strData[i][12]= arrElmnt.getString("lat");
					strData[i][13]= arrElmnt.getString("lng");
					strData[i][14]= arrElmnt.getString("trainName");
					strData[i][15]= arrElmnt.getString("rakeType");
					strData[i][16]= arrElmnt.getString("trainSubType");
					strData[i][17]= arrElmnt.getString("trainSubTypeDesc");
					
					/*logger.info("Data Received:"+strData[i][0]+","+strData[i][1]+", "+strData[i][2]+","+strData[i][3]+","+strData[i][4]+","+strData[i][5]+","+strData[i][6]+","+strData[i][7]+","+strData[i][8]+","+strData[i][9]+","+strData[i][10]+","+strData[i][11]+","+strData[i][12]);*/
				}
			}
	}
	catch(Exception e)
	{
		logger.info("Exception Occurred in DataJson");
	}
	
	return strData;
}

/**
 * @param si_LocnFromFlag
 * @param si_LocnFrom
 * @param si_LocnToFlag
 * @param si_LocnTo
 * @param si_DateFrom
 * @param si_DateTo
 * @return
 * @throws IOException
 */
public String[][] getNTESPrclTranList(String si_LocnFromFlag, String si_LocnFrom, String si_LocnToFlag, String si_LocnTo, String si_DateFrom, String si_DateTo)  throws IOException
{
	logger.info("Inside getNTESPrclTranList- Inputs: "+si_LocnFromFlag+", "+si_LocnFrom+", "+si_LocnToFlag+", "+si_LocnTo+", "+si_DateFrom+", "+si_DateTo);

	String USER_AGENT = "Mozilla/5.0";
	String[][] strData=null;
	String url="";
	String strOutData="";
	
	String strDateFrom=ntesDateFormat(si_DateFrom);
	String strDateTo=ntesDateFormat(si_DateTo);
	logger.info("Converted Dates:"+strDateFrom+", "+ strDateTo);
	StringBuffer response=null;			
	url = "http://10.64.26.75/crisntes/Services?type=Covid&fDate="+strDateFrom+"&tDate="+strDateTo+"&from="+si_LocnFrom+"&to="+si_LocnTo+"&levelFrom="+si_LocnFromFlag+"&levelTo="+si_LocnToFlag+"&usrId=FOISINTGUSR&paswd=Covid19@20";
	logger.info("URL:"+url);
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

	try{
	logger.info(response.toString());
	JSONObject jo=new JSONObject(response.toString());
	logger.info("JSONObject Created");		
	String serviceDataFlag = jo.getString("ServiceDataFlag");
	String serviceMessage = jo.getString("ServiceMessage");
	String serviceCallFlag = jo.getString("ServiceCallFlag");
	
	logger.info("serviceDataFlag:"+serviceDataFlag+", serviceMessage:"+serviceMessage+", serviceCallFlag:"+serviceCallFlag);
	if(serviceDataFlag.equals("Y")) 
	{
				JSONArray data = jo.getJSONArray("trains");
				int dataLen=data.length();
				logger.info("dataLen"+ dataLen);
				strData=new String[dataLen+1][21];
				strData[0][0]=serviceDataFlag;
				strData[0][1]=serviceMessage;
				strData[0][2]=serviceCallFlag;
				for(int i=3;i<21;i++)
					strData[0][i]="";
				
				for(int i = 0; i < dataLen; i++) 
				{
					JSONObject arrElmnt = (JSONObject)data.get(i);

					strData[i+1][0]= arrElmnt.getString("TrainNo");
					strData[i+1][1]= arrElmnt.getString("TrainName");
					strData[i+1][2]= arrElmnt.getString("TrainType");
					strData[i+1][3]= arrElmnt.getString("Source");
					strData[i+1][4]= arrElmnt.getString("Destination");
					strData[i+1][5]= arrElmnt.getString("From");
					strData[i+1][6]= arrElmnt.getString("To");
					strData[i+1][7]= arrElmnt.getString("DepTimeFrom");
					strData[i+1][8]= arrElmnt.getString("ArrTimeTo");
					strData[i+1][9]= arrElmnt.getString("StartDate");
					strData[i+1][10]= arrElmnt.getString("StartFlag");
					strData[i+1][11]= arrElmnt.getString("ETA");
					strData[i+1][12]= arrElmnt.getString("ETD");
					strData[i+1][13]= arrElmnt.getString("DaysOfRun");
					strData[i+1][14]= arrElmnt.getString("ValidFrom");
					strData[i+1][15]= arrElmnt.getString("ValidTo");
					strData[i+1][16]= arrElmnt.getString("TravelTime");
					if(strData[i+1][10].equals("ON RUN"))
					{
						strData[i+1][17]= arrElmnt.getString("LastLocation");
						strData[i+1][18]= arrElmnt.getString("LastLocTime");
						strData[i+1][19]= arrElmnt.getString("LastLocEvent");
						if(strData[i+1][19].equals("DR"))
							strData[i+1][19]="DEP";
						if(strData[i+1][19].equals("AR"))
							strData[i+1][19]="ARR";
						
						try
						{
							strData[i+1][20]= arrElmnt.getString("LastLocDelay");
						}
						catch(Exception e)
						{
							strData[i+1][20]= "";
						}
					}
					else
					{
						strData[i+1][17]="";
						strData[i+1][18]="";
						strData[i+1][19]="";
						strData[i+1][20]="";
					}
					logger.info("Data Received:"+strData[i+1][0]+","+strData[i+1][1]+", "+strData[i+1][2]+","+strData[i+1][3]+","+strData[i+1][4]+","+strData[i+1][5]+","+strData[i+1][6]+","+strData[i+1][7]+","+strData[i+1][8]+","+strData[i+1][9]+","+strData[i+1][10]+","+strData[i+1][11]+","+strData[i+1][12]);
				}
	 		}
	}
	catch(Exception e)
	{
		logger.info("Exception Occurred in DataJson");
	}
	
	return strData;
}
public String[][] getPrclTranSchd(String si_TranNumb, String si_StrtDate)  throws IOException
{
	logger.info("Inside getPrclTranSchd- Inputs: "+si_TranNumb+", "+si_StrtDate);
	
	String USER_AGENT = "Mozilla/5.0";
	String[][] strData=null;
	String url="";
	String strOutData="";
	String strStrtDate=si_StrtDate;
	StringBuffer response=null;			
	url="http://10.60.200.171/icmsservices/Controller?action=FOIS&service=singleTrainRunning&userName=FoIs&password=fOiS&trainNo="+si_TranNumb+"&startDate="+strStrtDate;
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

	try{
	logger.info(response.toString());
	JSONObject jo=new JSONObject(response.toString());
	logger.info("JSONObject Created");		
	boolean successFlag = jo.getBoolean("successFlag");
	logger.info("Success:"+successFlag);
	if(successFlag) 
	{
				JSONArray data = jo.getJSONArray("data");
				int dataLen=data.length();
				logger.info("dataLen"+ dataLen);
				strData=new String[dataLen][16];
				for(int i = 0; i < dataLen; i++) 
				{
					JSONObject arrElmnt = (JSONObject)data.get(i);
					logger.info(si_TranNumb);
					strData[i][0]= si_TranNumb;
					strData[i][1]= foisDateFormat(arrElmnt.getString("startDate"));
					strData[i][2]= arrElmnt.getInt("srlNo")+"";
					strData[i][3]= arrElmnt.getString("stnCode");
					strData[i][4]= arrElmnt.getString("schArrTime");
					strData[i][5]= arrElmnt.getString("actArrTime");
					strData[i][6]= arrElmnt.getString("delayArr");
					strData[i][7]= arrElmnt.getString("schDepTime");
					strData[i][8]= arrElmnt.getString("actDepTime");
					strData[i][9]= arrElmnt.getString("delayDep");
					strData[i][10]= arrElmnt.getString("travelling");
					strData[i][11]= arrElmnt.getString("lat");
					strData[i][12]= arrElmnt.getString("lng");
					
					if(!strData[i][6].equals(""))
					{
						if(Integer.parseInt(strData[i][6])>0)
						{
							strData[i][13]="DL";  /*Delayed*/
						}
						if(Integer.parseInt(strData[i][6])<0)
						{
							strData[i][13]="AD";   /*Advanced*/
						}
						if(Integer.parseInt(strData[i][6])==0)
						{
							strData[i][13]="OT";   /*OnTime*/
						}
					}
					else
					{						
						strData[i][13]="";
					}
					
					if(!strData[i][9].equals(""))
					{
						if(Integer.parseInt(strData[i][9])>0)
						{
							strData[i][14]="DL";   /*Delayed*/
						}
						if(Integer.parseInt(strData[i][9])<0)
						{
							strData[i][14]="AD";   /*Advanced*/
						}
						if(Integer.parseInt(strData[i][9])==0)
						{
							strData[i][14]="OT";  /*OnTime*/
						}
					}
					else
					{
						strData[i][14]="";
					}
					
					if(strData[i][5].equals("") && strData[i][8].equals(""))
						strData[i][15]="NR";  /*Not Reported*/
					else
						strData[i][15]="R";   /*Reported*/
					
					//logger.info("Data Received:"+strData[i][0]+","+strData[i][1]+", "+strData[i][2]+","+strData[i][3]+","+strData[i][4]+","+strData[i][5]+","+strData[i][6]+","+strData[i][7]+","+strData[i][8]+","+strData[i][9]+","+strData[i][10]+","+strData[i][11]+","+strData[i][12]);
				}
			}
	}
	catch(Exception e)
	{
		logger.info("Exception Occurred in DataJson");
	}
	
	return strData;
}
public String[][] getPrclTranOcpn(String si_TranNumb, String si_StrtDate)  throws IOException
{
	logger.info("Inside getPrclTranList- Inputs: "+si_TranNumb+", "+si_StrtDate);
	
	String USER_AGENT = "Mozilla/5.0";
	String[][] strData=null;
	String url="";
	String strOutData="";
	String strDate=si_StrtDate;
	StringBuffer response=null;			
	url="http://10.64.1.150/covidtrn/service/trainmaster/getSpaceInTrain";
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	con.setDoOutput(true);
	String input = "{\"trnno\":\""+si_TranNumb+"\",\"jrnydt\":\""+strDate+"\"}";
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
		if(serviceStatus.equals("Success")) 
		{	
			strData[0][0] = jo.getInt("wagoncnt")+"";
			strData[0][1] = jo.getInt("loadedwt")+"";
			strData[0][2] = jo.getInt("spaceavailable")+"";
			strData[0][3]=jo.getInt("unloadedwt")+"";
			
			logger.info(strData[0][0]+"   "+strData[0][1]+"   "+strData[0][2]+"    "+strData[0][3]);
		}
	}
	catch(Exception e)
	{
		logger.info("Exception Occurred in DataJson");
	}
	
	return strData;
}
public String[][] getPMSPrclTranOcpn(String si_TranNumb, String si_StrtDate)  throws IOException
{
	logger.info("Inside getPrclTranList- Inputs: "+si_TranNumb+", "+si_StrtDate);
	
	String USER_AGENT = "Mozilla/5.0";
	String[][] strData=null;
	String url="";
	String strOutData="";
	String strDate=si_StrtDate;
	StringBuffer response=null;			
	url="http://10.64.3.36:9080/covidtrn/service/trainmaster/getWagonArray";
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	con.setDoOutput(true);
	String input = "{\"trnno\":\""+si_TranNumb+"\",\"jrnydt\":\""+strDate+"\"}";
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
	try{
		logger.info(response.toString());
		JSONObject jo=new JSONObject(response.toString());
		logger.info("JSONObject Created");		
		String serviceStatus = jo.getString("ServiceMessage");
		logger.info("serviceStatus:"+serviceStatus);
		if(serviceStatus.equals("Success")) 
		{
			strData=new String[1][4];
			JSONArray data = jo.getJSONArray("wagonarray");
			int dataLen=data.length();
			logger.info("dataLen"+ dataLen);
			
			int intWgonCont=0;
			int intTotlCpty=0;
			int intLdedWght=0;
			int intAvblSpc=0;
			
			for(int i = 0; i < dataLen; i++) 
			{
				JSONObject arrElmnt = (JSONObject)data.get(i);
				intTotlCpty+= arrElmnt.getInt("wagoncapacity");
				intLdedWght+= arrElmnt.getInt("loadedwt");
				intAvblSpc+= arrElmnt.getInt("spaceavailable");
			}
			strData[0][0] = dataLen+"";
			strData[0][1] = intTotlCpty+"";
			strData[0][2] = intLdedWght+"";
			strData[0][3] = intAvblSpc+"";
			
			logger.info(strData[0][0]+"   "+strData[0][1]+"   "+strData[0][2]+"    "+strData[0][3]);
		}
	}
	catch(Exception e)
	{
		logger.info("Exception Occurred in DataJson");
	}
	
	return strData;
}
public String foisDateFormat(String strInptDate)
{
	StringTokenizer st=new StringTokenizer(strInptDate,"-");
	String strYear=st.nextToken();
	String strMnth=st.nextToken();
	String strDay=st.nextToken();
	return strDay+"-"+strMnth+"-"+strYear;	
}
public String icmsDateFormat(String strInptDate)
{
	StringTokenizer st=new StringTokenizer(strInptDate,"-");
	String strDay=st.nextToken();
	String strMnth=st.nextToken();
	String strYear=st.nextToken();
	return strYear+"-"+strMnth+"-"+strDay;	
}
public String ntesDateFormat(String strInptDate)
{
	StringTokenizer st=new StringTokenizer(strInptDate,"-");
	String strDay=st.nextToken();
	String strMnth=st.nextToken();
	String strYear=st.nextToken();
	String strMonth="";
	if(strMnth.equals("01") || strMnth.equals("1"))
		strMonth="Jan";
	if(strMnth.equals("02") || strMnth.equals("2"))
		strMonth="Feb";
	if(strMnth.equals("03") || strMnth.equals("3"))
		strMonth="Mar";
	if(strMnth.equals("04") || strMnth.equals("4"))
		strMonth="Apr";
	if(strMnth.equals("05") || strMnth.equals("5"))
		strMonth="May";
	if(strMnth.equals("06") || strMnth.equals("6"))
		strMonth="Jun";
	if(strMnth.equals("07") || strMnth.equals("7"))
		strMonth="Jul";
	if(strMnth.equals("08") || strMnth.equals("8"))
		strMonth="Aug";
	if(strMnth.equals("09") || strMnth.equals("9"))
		strMonth="Sep";
	if(strMnth.equals("10"))
		strMonth="Oct";
	if(strMnth.equals("11"))
		strMonth="Nov";
	if(strMnth.equals("12"))
		strMonth="Dec";
	
	return strDay+"-"+strMonth+"-"+strYear;	
}
public String ntesToICMSDateFormat(String strInptDate)
{
	StringTokenizer st=new StringTokenizer(strInptDate,"-");
	String strDay=st.nextToken();
	String strMnth=st.nextToken();
	String strYear=st.nextToken();
	String strMonth="";
	logger.info("Inside ntesToICMSDateFormat"+strInptDate);
	if(strMnth.equalsIgnoreCase("Jan"))
		strMonth="01";
	if(strMnth.equalsIgnoreCase("Feb"))
		strMonth="02";
	if(strMnth.equalsIgnoreCase("Mar"))
		strMonth="03";
	if(strMnth.equalsIgnoreCase("Apr"))
		strMonth="04";
	if(strMnth.equalsIgnoreCase("May"))
		strMonth="05";
	if(strMnth.equalsIgnoreCase("Jun"))
		strMonth="06";
	if(strMnth.equalsIgnoreCase("Jul"))
		strMonth="07";
	if(strMnth.equalsIgnoreCase("Aug"))
		strMonth="08";
	if(strMnth.equalsIgnoreCase("Sep"))
		strMonth="09";
	if(strMnth.equalsIgnoreCase("Oct"))
		strMonth="10";
	if(strMnth.equalsIgnoreCase("Nov"))
		strMonth="11";
	if(strMnth.equalsIgnoreCase("Dec"))
		strMonth="12";
	
	logger.info("Month Converted to:"+strMonth);
	return strYear+"-"+strMonth+"-"+strDay;	
}
public String pmsDateFormat(String strInptDate)
{

	StringTokenizer st=new StringTokenizer(strInptDate,"-");
	String strDay=st.nextToken();
	String strMnth=st.nextToken();
	String strYear=st.nextToken();
	String strMonth="";
	
	logger.info("Inside pmsDateFormat"+strInptDate);
	if(strMnth.equalsIgnoreCase("Jan"))
		strMonth="01";
	if(strMnth.equalsIgnoreCase("Feb"))
		strMonth="02";
	if(strMnth.equalsIgnoreCase("Mar"))
		strMonth="03";
	if(strMnth.equalsIgnoreCase("Apr"))
		strMonth="04";
	if(strMnth.equalsIgnoreCase("May"))
		strMonth="05";
	if(strMnth.equalsIgnoreCase("Jun"))
		strMonth="06";
	if(strMnth.equalsIgnoreCase("Jul"))
		strMonth="07";
	if(strMnth.equalsIgnoreCase("Aug"))
		strMonth="08";
	if(strMnth.equalsIgnoreCase("Sep"))
		strMonth="09";
	if(strMnth.equalsIgnoreCase("Oct"))
		strMonth="10";
	if(strMnth.equalsIgnoreCase("Nov"))
		strMonth="11";
	if(strMnth.equalsIgnoreCase("Dec"))
		strMonth="12";
	
	return strDay+"/"+strMonth+"/"+strYear;
}
}
