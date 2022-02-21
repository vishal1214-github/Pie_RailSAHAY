package servlet.AjaxData;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

import org.json.JSONArray;
import org.json.JSONObject;

import tuxedo.FOISTuxedo;

import util.exception.GG_Exception;

import util.logger.FoisLogger;

public class GG_PrclTranData extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_PrclTranData.class.getName());
	boolean bolValid=true;
	String strDtls[][];
	String strJsonData="";

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
	    if(si_QryFlag.equals("GETDRISTIDATA"))
	    {
	        String[][] strData=null;
	            logger.info("Case: Get Dristi Data");
	            

	            try
	            {
	                    strJsonData=this.getDristiData();
                        logger.info("strJsonData" + strJsonData);
                       
	            }
	            catch(Exception e)
	            {
	                    logger.info("Exception occurred while calling Dristi Service for Freight Figures :"+e.getMessage());
	                    e.printStackTrace();
	            }
	        try
	                                {
	                                JSONObject jo=new JSONObject(strJsonData.toString());
                    
	                                logger.info("JSONObject Created");        
	                                    int code = jo.getInt("errorCode");
	                                    logger.info("code:"+code);
                                       String successFlag = Integer.toString(code);
	                                String errorMesg = jo.getString("errorMssg");
	                                logger.info("successFlag:"+successFlag);
	                                    logger.info("errorMesg:"+errorMesg);
	                                if(successFlag.equals("0")) 
	                                {
	                                        JSONArray data = jo.getJSONArray("freight");
	                                        int dataLen=data.length();
                                                String strRowCount=dataLen+"";
                                                logger.info("strRowCount::"+ strRowCount);
	                                        logger.info("dataLen"+ dataLen);
	                                        strData=new String[dataLen][14];
	                                        
	                                        for(int i = 0; i < dataLen; i++) 
	                                        {
	                                                JSONObject arrElmnt = (JSONObject)data.get(i);
	                                                
	                                                strData[i][0]= arrElmnt.getString("intervalType");
	                                                 strData[i][1]= arrElmnt.getString("intervalDesc");
	                                                strData[i][2]= arrElmnt.getString("rlycode");
	                                                strData[i][3]= arrElmnt.getString("commodity");
	                                                strData[i][4]= Integer.toString(arrElmnt.getInt("rakesCurr"));
	                                                strData[i][5]= Integer.toString(arrElmnt.getInt("rakesPrev"));
	                                                strData[i][6]= Double.toString(arrElmnt.getDouble("tonnageCurr"));
	                                                strData[i][7]= Double.toString(arrElmnt.getDouble("tonnagePrev"));
	                                                strData[i][8]= Double.toString(arrElmnt.getDouble("earningsCurr"));
	                                                strData[i][9]= Double.toString(arrElmnt.getDouble("earningsPrev"));
	                                             
	                                                logger.info("data:"+"        "+strData[i][0]+"        "+strData[i][8]+"    "+strData[i][9]+"    "+i);
	                                        }
	                                    FOISTuxedo RTSAHAYPRTL    = null;
	                                                                    
                try
                {
                    logger.info("Calling Tuxedo");
                    RTSAHAYPRTL = new FOISTuxedo();
                    logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                    logger.fatal("Unable to get the Client Object: " + ne.toString());
                    throw new GG_Exception(101, ne);
                }                       
                try
                {
                    RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
                    RTSAHAYPRTL.setString("F_USERID",         0,      "SAHAY");
                    RTSAHAYPRTL.setString("F_CLNTID",         0,      "1.1.1.1");
                    RTSAHAYPRTL.setString("F_FLAG",           0,      "SAVEDRISTIDATA");                                     
                    RTSAHAYPRTL.setString("F_COMMITFLAG", 0,   "Y"); 
                    RTSAHAYPRTL.setString("F_ROWCONT", 0,   strRowCount);
                    
                    logger.info("DATA SET RTSAHAYPRTL");
                   
                    for(int i = 0; i < dataLen; i++) 
                    {
                            
                            RTSAHAYPRTL.setString("F_ORDRBY1",               i,  strData[i][0].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY2",               i,  strData[i][1].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY3",               i,  strData[i][2].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY4",               i,  strData[i][3].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY5",               i,  strData[i][4].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY6",               i,  strData[i][5].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY7",               i,  strData[i][6].trim());                                               
                            RTSAHAYPRTL.setString("F_ORDRBY8",              i,  strData[i][7].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY9",              i,  strData[i][8].trim());
                            RTSAHAYPRTL.setString("F_ORDRBY10",             i,  strData[i][9].trim());
                        
                                                    
                    }
                }
                catch(Exception e)
                {
                    logger.fatal("Unable to write to buffer : " + e.toString());
                    throw new GG_Exception(102, e);
                }       
                    
                    try
                    {
                            logger.info("Calling Tuxedo Service SRVCNAME ...");
                            RTSAHAYPRTL.call("N");
                            logger.info("CALL COMPLETED !");
                    }
                    catch(Exception e)
                    {
                            logger.fatal("Exception while call to the service is " + e.toString());
                            throw new GG_Exception(103, e);
                    }
                }
                }
	                                catch(Exception e)
	                                {
	                                        System.out.println("Error in Data Fetch");
	                                        }
	           // res.getWriter().write(strJsonData);
	    }
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

			try
			{
				strJsonData=this.getPrclTranList(si_FromFlag, strLocnFrom, si_ToFlag, strLocnTo, si_DateFrom, si_DateTo);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Trains List:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
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

			try
			{
				strJsonData=this.getNTESPrclTranList(si_FromFlag, strLocnFrom, si_ToFlag, strLocnTo, si_DateFrom, si_DateTo);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Trains List:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
		}
		if(si_QryFlag.equals("SATSTRAINLIST"))
		{
			logger.info("Case: Get SATSANG Train List");
			si_FromFlag=(String)req.getParameter("selLocnFrom").toUpperCase().trim();
			si_LocnFrom=(String)req.getParameter("txtLocnFrom").toUpperCase().trim();
			si_ToFlag=(String)req.getParameter("selLocnTo").toUpperCase().trim();
			si_LocnTo=(String)req.getParameter("txtLocnTo").toUpperCase().trim();
			si_DateFrom=(String)req.getParameter("txtDateFrom").toUpperCase().trim();
			si_DateTo=(String)req.getParameter("txtDateTo").toUpperCase().trim();
			String strLocnFrom=si_LocnFrom;
			String strLocnTo=si_LocnTo;
                        
                        if(si_FromFlag.equals("Z")){
                                    switch(strLocnFrom){
                                    case "EC" :
                                        strLocnFrom="ECR";
                                        break;
                                    case "ECO" :
                                        strLocnFrom="ECOR";
                                        break;
                                    case "NC" :
                                        strLocnFrom="NCR";
                                        break;
                                    case "NE" :
                                        strLocnFrom="NER";
                                        break;
                                    case "NF" :
                                        strLocnFrom="NFR";
                                        break;
                                    case "NW" :
                                        strLocnFrom="NWR";
                                        break;
                                    case "SC" :
                                        strLocnFrom="SCR";
                                        break;
                                    case "SE" :
                                        strLocnFrom="SER";
                                        break;
                                    case "SEC" :
                                        strLocnFrom="SECR";
                                        break;
                                    case "SW" :
                                        strLocnFrom="SWR";
                                        break;
                                    case "WC" :
                                        strLocnFrom="WCR";
                                        break;
                                    default: break;
                                        
                                    }
                        }
		    if(si_ToFlag.equals("Z")){
		                switch(strLocnTo){
		                case "EC" :
		                    strLocnTo="ECR";
		                    break;
		                case "ECO" :
		                    strLocnTo="ECOR";
		                    break;
		                case "NC" :
		                    strLocnTo="NCR";
		                    break;
		                case "NE" :
		                    strLocnTo="NER";
		                    break;
		                case "NF" :
		                    strLocnTo="NFR";
		                    break;
		                case "NW" :
		                    strLocnTo="NWR";
		                    break;
		                case "SC" :
		                    strLocnTo="SCR";
		                    break;
		                case "SE" :
		                    strLocnTo="SER";
		                    break;
		                case "SEC" :
		                    si_LocnTo="SECR";
		                    break;
		                case "SW" :
		                    strLocnTo="SWR";
		                    break;
		                case "WC" :
		                    strLocnTo="WCR";
		                    break;
		                default: break;
		                    
		                }
		    }
                    
			try
			{
				strJsonData=this.getSatsPrclTranList(si_FromFlag, strLocnFrom, si_ToFlag, strLocnTo, si_DateFrom, si_DateTo);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Trains List:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
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
				strJsonData=this.getPrclTranSchd(strTranNumb, strStrtDate);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Train Schedule:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
		}
		if(si_QryFlag.equals("TRNSCHDSATS"))
		{
			logger.info("Case: Get Train Schedule");
			si_TranNumb=(String)req.getParameter("tranid").toUpperCase().trim();
			//si_StrtDate=(String)req.getParameter("StrtDate").toUpperCase().trim();
			//String strTranNumb=(si_TranNumb.substring(0,si_TranNumb.indexOf("("))).trim();
			//String strStrtDate=icmsDateFormat(si_StrtDate);
			//String strStrtDate=ntesToICMSDateFormat(si_StrtDate);
			logger.info("Train Id:"+si_TranNumb);
			try
			{
				strJsonData=this.getPrclTranSchdSats(si_TranNumb);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Train Schedule:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
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
				strJsonData=this.getPMSPrclTranOcpn(strTranNumb, strStrtDate);
			}
			catch(Exception e)
			{
				logger.info("Exception occurred while calling ICMS Service for Parcel Train Occupancy:"+e.getMessage());
				e.printStackTrace();
			}
			res.getWriter().write(strJsonData);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
public String getPrclTranList(String si_LocnFromFlag, String si_LocnFrom, String si_LocnToFlag, String si_LocnTo, String si_DateFrom, String si_DateTo)  throws IOException
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
	return response.toString();
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
public String getNTESPrclTranList(String si_LocnFromFlag, String si_LocnFrom, String si_LocnToFlag, String si_LocnTo, String si_DateFrom, String si_DateTo)  throws IOException
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
	return response.toString();
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
    public String getSatsPrclTranList(String si_LocnFromFlag, String si_LocnFrom, String si_LocnToFlag, String si_LocnTo, String si_DateFrom, String si_DateTo)  throws IOException
    {
            logger.info("Inside getSatsPrclTranList- Inputs: "+si_LocnFromFlag+", "+si_LocnFrom+", "+si_LocnToFlag+", "+si_LocnTo+", "+si_DateFrom+", "+si_DateTo);
       /*     String response="";
                try {
                    final Context context = getInitialContext();
                    ExternalWSClientSessionEJB rpe =
                        (ExternalWSClientSessionEJB)context.lookup("EDI_RakePermit_Web-EJBWebInterface-RakePermitSessionEJB#org.cris.fois.edi.rp.EJB.RakePermitSessionEJB");
                    response=rpe.getTrainListFromSatsang(si_LocnFromFlag, si_LocnFrom, si_LocnToFlag, si_LocnTo, si_DateFrom, si_DateTo);
                    System.out.println("response from Satsang::"+response);
                } catch (CommunicationException ex) {
                    System.out.println(ex.getClass().getName());
                    System.out.println(ex.getRootCause().getLocalizedMessage());
                    System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
           */
       System.out.println("Loading Properties");
       Properties properties=new Properties();
       try{
               //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
               properties.load(new FileInputStream("FoisCustWebSrvc.properties"));


       }catch(Exception ex)
       {
               System.out.println("Could not load the property file WebsrvcLogs");
       }

       String satsIP=properties.getProperty("webIP");
        String proxyIP=properties.getProperty("proxy");
            
       String response="";
       try {

                   HttpURLConnection httpCon =null;
                   URL con=null;
                  
                   String url="http://"+satsIP+"/api/Train?Type1="+si_LocnFromFlag+"&Value1="+si_LocnFrom+"&Type2="+si_LocnToFlag+"&Value2="+si_LocnTo+"&FromDate="+si_DateFrom+"&ToDate="+si_DateTo;
                   
                   System.out.println(url);
                   System.getProperties().put("proxySet","true");
                   System.getProperties().put("proxyHost",proxyIP);
                   System.getProperties().put("proxyPort","80");
                  
                   con = new URL(null,url);
                   httpCon = (HttpURLConnection)con.openConnection();
                   httpCon.setRequestMethod("GET");
                   httpCon.setDoOutput(true);
                   httpCon.setDoInput(true);
                   httpCon.setUseCaches(false);
                  

                   System.out.println("Set All the varibales and going to get the Response....");

                   System.out.println("received Response is ::"+httpCon.getResponseCode()+":: Message is ::"+httpCon.getResponseMessage());
                  
                            BufferedReader in = null;
                                           in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                                           String inputLine;
                                           while ((inputLine = in.readLine()) != null) {
                                                   response+=inputLine;
                                           }
                                   
                   System.out.println("response:"+response);
                  
                       
               }
               catch(Exception e) {
                   e.printStackTrace();
               }
            
            return response.toString();
    }
public String getPrclTranSchd(String si_TranNumb, String si_StrtDate)  throws IOException
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
	return response.toString();
}public String getPrclTranSchdSats(String si_TranNumb)  throws IOException
{
	logger.info("Inside getPrclTranSchd- Inputs: "+si_TranNumb);

	String response="";
    System.out.println("Loading Properties");
    Properties properties=new Properties();
    try{
            //properties.load(EmailSrvcLogger.class.getClassLoader().getResourceAsStream("EmailSrvcLogs.properties"));
            properties.load(new FileInputStream("FoisCustWebSrvc.properties"));


    }catch(Exception ex)
    {
            System.out.println("Could not load the property file WebsrvcLogs");
    }

    String satsIP=properties.getProperty("webIP");
        String proxyIP=properties.getProperty("proxy");
    try {

                HttpURLConnection httpCon =null;
                URL con=null;
               
                String url="http://"+satsIP+"/api/Values?trainId="+si_TranNumb;
               
                
                System.out.println(url);
                System.getProperties().put("proxySet","true");
                System.getProperties().put("proxyHost",proxyIP);
                System.getProperties().put("proxyPort","80");
               
                con = new URL(null,url);
                httpCon = (HttpURLConnection)con.openConnection();
                httpCon.setRequestMethod("GET");
                httpCon.setDoOutput(true);
                httpCon.setDoInput(true);
                httpCon.setUseCaches(false);
               

                System.out.println("Set All the varibales and going to get the Response....");

                System.out.println("received Response is ::"+httpCon.getResponseCode()+":: Message is ::"+httpCon.getResponseMessage());
                
                         BufferedReader in = null;
                                        in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                                        String inputLine;
                                        while ((inputLine = in.readLine()) != null) {
                                                response+=inputLine;
                                        }
                                
                System.out.println("response:"+response);
               
                    
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        
        
        
	return response.toString();
}
public String getPrclTranOcpn(String si_TranNumb, String si_StrtDate)  throws IOException
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
	return response.toString();
}
public String getPMSPrclTranOcpn(String si_TranNumb, String si_StrtDate)  throws IOException
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
	return response.toString();
}

    public String getDristiData()  throws IOException
    {
            logger.info("Inside getDristiData");
            String strOutData="";

           
        ClientConfig clientConfig = new ClientConfig();
         
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("fois", "RDDM99DhuXkr2LPmm9wtrYns0RXwF8Yg0x3QqjfS3K#fFqe!TaviEegF8hO5G");
            clientConfig.register( feature) ;
         
            clientConfig.register(JacksonFeature.class);
         
            Client client = ClientBuilder.newClient( clientConfig );
            WebTarget webTarget = client.target("http://10.64.25.99:8080/edrishtiMobileServices/rest/mobileservices/freight");
             
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
             
            logger.info(response.getStatus());
            logger.info(response.getStatusInfo());
        if(response.getStatus() == 200)
            {
                strOutData=response.readEntity(String.class);
            }
        
            return strOutData;
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
    private static Context getInitialContext() throws NamingException {
           Hashtable env = new Hashtable();
           // WebLogic Server 10.x connection details
           env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
           env.put(Context.PROVIDER_URL, "t3://10.64.26.173:50004,10.64.26.174:50004");
           return new InitialContext( env );
       }
}
