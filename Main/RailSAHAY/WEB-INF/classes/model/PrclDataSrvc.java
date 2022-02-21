package model;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import tuxedo.*;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;


import util.exception.GG_Exception;
public class PrclDataSrvc
{
	static Logger logger = FoisLogger.getLogger(PrclDataSrvc.class.getName());
	String si_ClntId="";
	String strRetStts="";

	public PrclDataSrvc()
	{
		super();
	}
	public PrclDataSrvc(String si_ClntId)
	{
		this.si_ClntId=si_ClntId;
	}
	public  String[][] fetchFBDPrclData(String si_FromFlag, String si_LocnFrom, String si_CrntFlag, String si_CrntLocn, String si_ToFlag, String si_LocnTo, String si_TranNo, String si_TranName) throws GG_Exception
	{
		String[][] strPrclData  = null;
		String strKey="FBD"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date());
		logger.info("Entering fetchFBDPrclData....");
		logger.info("Function called with inputs :");
		logger.info("si_FromFlag  #"+si_FromFlag+"#");
		logger.info("si_LocnFrom  #"+si_LocnFrom  +"#");
		logger.info("si_CrntFlag  #"+si_CrntFlag  +"#");
		logger.info("si_CrntLocn  #"+si_CrntLocn  +"#");
		logger.info("si_ToFlag    #"+si_ToFlag  +"#");
		logger.info("si_LocnTo    #"+si_LocnTo  +"#");
		logger.info("si_TranNo    #"+si_TranNo    +"#");
		logger.info("si_TranName  #"+si_TranName    +"#");

		int dataLen=0;
		String strRowCount2="";
		String[][] strData=null;
		String strPrclTranData[][]=null;
			try{
				logger.info("CALLING ICMS BLOCK");
				String params	=	"userName=FoIs&password=fOiS";
				if(!si_LocnFrom.equals(""))
				{
					if(si_FromFlag.equals("S"))
						params	=	params + "&fromStation="+	si_LocnFrom;
					if(si_FromFlag.equals("D"))
						params	=	params + "&fromDivision="+	si_LocnFrom;
					if(si_FromFlag.equals("Z"))
						params	=	params + "&fromZone="+	si_LocnFrom;
					if(si_FromFlag.equals("DS"))
						params	=	params + "&fromDistrict="+	si_LocnFrom;
					if(si_FromFlag.equals("ST"))
						params	=	params + "&fromState="+	si_LocnFrom;
				}
				if(!si_CrntLocn.equals(""))
				{
					if(si_CrntFlag.equals("S"))
						params	=	params + "&curStation="+	si_LocnFrom;
					if(si_CrntFlag.equals("D"))
						params	=	params + "&curDivision="+	si_LocnFrom;
					if(si_CrntFlag.equals("Z"))
						params	=	params + "&curZone="+	si_LocnFrom;
					if(si_CrntFlag.equals("DS"))
						params	=	params + "&curDistrict="+	si_LocnFrom;
					if(si_CrntFlag.equals("ST"))
						params	=	params + "&curState="+	si_LocnFrom;
				}
				if(!si_LocnTo.equals(""))
				{
					if(si_ToFlag.equals("S"))
						params	=	params + "&toStation="+	si_LocnFrom;
					if(si_ToFlag.equals("D"))
						params	=	params + "&toDivision="+	si_LocnFrom;
					if(si_ToFlag.equals("Z"))
						params	=	params + "&toZone="+	si_LocnFrom;
					if(si_ToFlag.equals("DS"))
						params	=	params + "&toDistrict="+	si_LocnFrom;
					if(si_ToFlag.equals("ST"))
						params	=	params + "&toState="+	si_LocnFrom;
				}

				logger.info("URL Parameters :"+params);
				strData=getPrclData(params);
				dataLen=strData.length;
				logger.info("dataLen"+ dataLen);
				strRowCount2=dataLen+"";
				logger.info("strRowCount2"+ strRowCount2);
			}
			catch(Exception ne)
			{
				logger.fatal("Unable to get the Client Object: " + ne.toString());
				throw new GG_Exception(101, ne);
			}
			FOISTuxedo RTMAPPSGR	= null;
			try
			{
				logger.info("Calling Tuxedo");
				RTMAPPSGR = new FOISTuxedo();
				logger.info("Client Object Got.");
			}
			catch (Exception ne)
			{
				logger.fatal("Unable to get the Client Object: " + ne.toString());
				throw new GG_Exception(101, ne);
			}

			logger.info("strRowCount2"+ strRowCount2);
			try
			{
				RTMAPPSGR.tuxInit("RTMAPPSGR");
				RTMAPPSGR.setString("F_USERID",		0,	"FBD");
				RTMAPPSGR.setString("F_CLNTID",		0,	si_ClntId);
				RTMAPPSGR.setString("F_FLAG",			0,	"S1");
				RTMAPPSGR.setString("F_CREWID",			0,	strKey);
				RTMAPPSGR.setString("F_ROWCONT2",  0,   strRowCount2);
				RTMAPPSGR.setString("F_COMMITFLAG",  0,   "Y");

			logger.info("DATA SET");
			for(int i = 0; i < dataLen; i++)
			{

				RTMAPPSGR.setString("F_ORDRBY1",               i,  strData[i][0].trim());
				RTMAPPSGR.setString("F_ORDRBY2",               i,  strData[i][1].trim());
				RTMAPPSGR.setString("F_ORDRBY3",               i, strData[i][2].trim());
				RTMAPPSGR.setString("F_ORDRBY4",               i,  strData[i][3].trim());
				RTMAPPSGR.setString("F_ORDRBY5",               i,  strData[i][4].trim());
				RTMAPPSGR.setString("F_ORDRBY6",               i,  strData[i][5].trim());
				RTMAPPSGR.setString("F_ORDRBY7",               i, strData[i][6].trim());
				RTMAPPSGR.setString("F_ORDRBY8",               i,  strData[i][7].trim());
				RTMAPPSGR.setString("F_ORDRBY9",               i,  strData[i][8].trim());
				RTMAPPSGR.setString("F_ORDRBY10",               i,  strData[i][9].trim());
				RTMAPPSGR.setString("F_RPWBNUMB1",               i, strData[i][10].trim());
				RTMAPPSGR.setString("F_RPWBNUMB2",               i,  strData[i][11].trim());
				RTMAPPSGR.setString("F_HLDGZONE",               i,  strData[i][12].trim());
				RTMAPPSGR.setString("F_HLDGZONE1",               i,  strData[i][13].trim());
				RTMAPPSGR.setString("F_HLDGZONE2",               i,  strData[i][14].trim());
				RTMAPPSGR.setString("F_HLDGZONE3",               i,  strData[i][15].trim());
				RTMAPPSGR.setString("F_CLBLUNTS",               i,  strData[i][16].trim());
				RTMAPPSGR.setString("F_OPBLUNTS",               i,  strData[i][17].trim());
			}
			}
			catch(Exception e)
			{
				logger.fatal("Unable to write to buffer : " + e.toString());
				throw new GG_Exception(102, e);
			}

			try
			{
				logger.info("Calling Tuxedo Service SRVCNAME ..wewwe.");
				RTMAPPSGR.call("N");
				logger.info("CALL COMPLETED !");
			}
			catch(Exception e)
			{
				logger.fatal("Exception while call to the service is " + e.toString());
				throw new GG_Exception(103, e);
			}
	    try
	    {
	            RTMAPPSGR.endSession();
	    }
	    catch(Exception e)
	    {
	            logger.fatal("Error In End Session:" + e.toString());
	    throw new GG_Exception(106, e);
	    }

		FOISTuxedo RQSAHAYMNTR	= null;

		try
		{
			logger.info("Calling Tuxedo");
			RQSAHAYMNTR = new FOISTuxedo();
			logger.info("Client Object Got.");
		}
		catch (Exception ne)
		{
			logger.fatal("Unable to get the Client Object: " + ne.toString());
			throw new GG_Exception(101, ne);
		}

		try
		{
			RQSAHAYMNTR.tuxInit("RQSAHAYMNTR");
			RQSAHAYMNTR.setString("F_USERID",		0,	"FBD");
			RQSAHAYMNTR.setString("F_CLNTID",		0,	si_ClntId);
			RQSAHAYMNTR.setString("F_FLAG",			0,	"PRCL_TRAINS");
			RQSAHAYMNTR.setString("F_HLDGZONE",		0,	si_FromFlag);
			RQSAHAYMNTR.setString("F_HLDGZONE",		1,	si_ToFlag);
			RQSAHAYMNTR.setString("F_HLDGZONE",		3,	si_LocnTo);
			RQSAHAYMNTR.setString("F_HLDGZONE",		4,	si_CrntFlag);
			RQSAHAYMNTR.setString("F_HLDGZONE",		5,	si_CrntLocn);
			RQSAHAYMNTR.setString("F_HLDGZONE",		6,	si_TranNo);
			RQSAHAYMNTR.setString("F_HLDGZONE",		7,	si_TranName);
			RQSAHAYMNTR.setString("F_HLDGZONE",		8,	strKey);
		}
		catch(Exception e)
		{
			logger.fatal("Unable to write to buffer : " + e.toString());
			throw new GG_Exception(102, e);
		}
		try
		{
			logger.info("Calling Tuxedo Service SRVCNAME ...");
			RQSAHAYMNTR.call("N");
			logger.info("CALL COMPLETED !");
		}
		catch(Exception e)
		{
			logger.fatal("Exception while call to the service is " + e.toString());
			throw new GG_Exception(103, e);
		}
		String erorCode1			= null;
		try
		{
			erorCode1				= RQSAHAYMNTR.getStringItemDef("F_ERORNO",0,"0");
		}
		catch(Exception e)
		{
			// F_ERORNO is not set by Developer, So, it is not an Error
		}
		if(erorCode1 != null && (!erorCode1.equals("")))
		{
			logger.fatal(erorCode1);
			throw new GG_Exception(erorCode1);
		}
		String startRowCount1   = null;
		int start1				= 0;

		try
		{
			logger.info("Came her");
			startRowCount1		= RQSAHAYMNTR.getStringItemDef("F_ROWCONT",0,"0");
		}
		catch(Exception d)
		{
			logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
			throw new GG_Exception(104, d);
		}

		if(startRowCount1.equals("0") || startRowCount1.equals(""))
		{
			logger.warn("NO ROWS in BUFFER");
			return strPrclData ;
		}
		else
		{
			start1 = new Integer(startRowCount1.trim()).intValue();
		}
		logger.info("start1 : " + start1);

		strPrclTranData=new String[start1][32];

		logger.info("Start reading data for Parcel Trains List");
		for(int i=0; i<=start1-1; i++)
		{
			try
			{
				strPrclTranData[i][0] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY1",	  	i,	"0").trim();
				strPrclTranData[i][1] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY2",	  	i,	"0").trim();
				strPrclTranData[i][2] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY3",	  	i,	"0").trim();
				strPrclTranData[i][3] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY4",	  	i,	"0").trim();
				strPrclTranData[i][4] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY5",	  	i,	"0").trim();
				strPrclTranData[i][5] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY6",	  	i,	"0").trim();
				strPrclTranData[i][6] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY7",	  	i,	"0").trim();
				strPrclTranData[i][7] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY8",	  	i,	"0").trim();
				strPrclTranData[i][8] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY9",	  	i,	"0").trim();
				strPrclTranData[i][9] = RQSAHAYMNTR.getStringItemDef("F_ORDRBY10",	  	i,	"0").trim();
				strPrclTranData[i][10] = RQSAHAYMNTR.getStringItemDef("F_OWNGZONE",	  	i,	"0").trim();
				strPrclTranData[i][11] = RQSAHAYMNTR.getStringItemDef("F_OWNGZONE1",	i,	"0").trim();
				strPrclTranData[i][12] = RQSAHAYMNTR.getStringItemDef("F_OWNGZONE2",	i,	"0").trim();
				strPrclTranData[i][13] = RQSAHAYMNTR.getStringItemDef("F_OWNGZONE3",	i,	"0").trim();
				strPrclTranData[i][14] = RQSAHAYMNTR.getStringItemDef("F_RPWBNUMB",	  	i,	"0").trim();
				strPrclTranData[i][15] = RQSAHAYMNTR.getStringItemDef("F_RPWBNUMB1",	i,	"0").trim();
				strPrclTranData[i][16] = RQSAHAYMNTR.getStringItemDef("F_RPWBNUMB2",	i,	"0").trim();
				strPrclTranData[i][17] = RQSAHAYMNTR.getStringItemDef("F_RPWBNUMB3",	i,	"0").trim();
				strPrclTranData[i][18] = RQSAHAYMNTR.getStringItemDef("F_RAKEID",	  	i,	"0").trim();
				strPrclTranData[i][19] = RQSAHAYMNTR.getStringItemDef("F_RAKETYPE",	  	i,	"0").trim();
				strPrclTranData[i][20] = RQSAHAYMNTR.getStringItemDef("F_CLBLUNTS",	  	i,	"0").trim();
				strPrclTranData[i][21] = RQSAHAYMNTR.getStringItemDef("F_EXCPUNTS",	  	i,	"0").trim();
				strPrclTranData[i][22] = RQSAHAYMNTR.getStringItemDef("F_DSPTUNTS",	  	i,	"0").trim();
				strPrclTranData[i][23] = RQSAHAYMNTR.getStringItemDef("F_OPBLUNTS",	  	i,	"0").trim();
				strPrclTranData[i][24] = RQSAHAYMNTR.getStringItemDef("F_UNTS",	  		i,	"0").trim();
				strPrclTranData[i][25] = RQSAHAYMNTR.getStringItemDef("F_FRWH",	  		i,	"0").trim();
				strPrclTranData[i][26] = RQSAHAYMNTR.getStringItemDef("F_STTNTO",	  	i,	"0").trim();
				strPrclTranData[i][27] = RQSAHAYMNTR.getStringItemDef("F_DSTN",	  		i,	"0").trim();
				strPrclTranData[i][28] = RQSAHAYMNTR.getStringItemDef("F_STTNFROM",	  	i,	"0").trim();
				strPrclTranData[i][29] = RQSAHAYMNTR.getStringItemDef("F_RELSFRWH",	  	i,	"0").trim();
				strPrclTranData[i][30] = RQSAHAYMNTR.getStringItemDef("F_FRMDFRWH",	  	i,	"0").trim();
				strPrclTranData[i][31] = RQSAHAYMNTR.getStringItemDef("F_DVSNTO",	  	i,	"0").trim();
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYMNTR and filling data into array" + d.toString());
                throw new GG_Exception(105, d);
            }
        }
		try
		{
			RQSAHAYMNTR.endSession();
		}
		catch(Exception e)
		{
			logger.fatal("Error In End Session:" + e.toString());
            throw new GG_Exception(106, e);
		}
		return strPrclTranData ;
	}
	public String[][] getPrclData(String strURLParams)throws Exception
	{
		String USER_AGENT = "Mozilla/5.0";
		String[][] strData=null;
		String url="";

		url = "http://10.60.200.171/icmsservices/Controller?action=FOIS&service=currentTrainsPositions&userName=FoIs&password=fOiS&trainType=PEXP";

		URL obj = new URL(url);
                logger.info("URL Object Created");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                logger.info("Connection Created");
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                logger.info("Connection Properties set");
		String urlParameters = strURLParams;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                logger.info("Output stream received");
		wr.writeBytes(urlParameters);
                logger.info("POST Parameters set on output stream");
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		logger.info("\nSending 'POST' request to URL : " + url);
		logger.info("Post parameters : " + urlParameters);
		logger.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		logger.info(response.toString());
		JSONObject jo=new JSONObject(response.toString());
		logger.info("JSONObject Created");
		boolean successFlag = jo.getBoolean("successFlag");
		logger.info("Success:"+successFlag);
		if(!successFlag)
		{
			this.strRetStts="Error in Getting Parcel Trains Data";
		}
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
				strData[i][2]= arrElmnt.getString("arrDepFlag");
				strData[i][3]= arrElmnt.getString("updateTime");
				strData[i][4]= arrElmnt.getString("trainNo");
				strData[i][5]= arrElmnt.getString("trainType");
				strData[i][6]= arrElmnt.getString("startDate");
				strData[i][7]= arrElmnt.getString("trainSrc");
				strData[i][8]= arrElmnt.getString("startDate");
				strData[i][9]= arrElmnt.getString("trainDstn");
				strData[i][10]= arrElmnt.getString("etaAtDstn");
				strData[i][11]= arrElmnt.getString("delayAtStn");
				strData[i][12]= arrElmnt.getString("lat");
				strData[i][13]= arrElmnt.getString("lng");
				strData[i][14]= arrElmnt.getString("trainName");
				strData[i][15]= arrElmnt.getString("rakeType");
				strData[i][16]= arrElmnt.getString("trainSubType");
				strData[i][17]= arrElmnt.getString("trainSubTypeDesc");
			}
		}
		return strData;
	}
	public String getPrclDataStts()
	{
		return this.strRetStts;
	}
}