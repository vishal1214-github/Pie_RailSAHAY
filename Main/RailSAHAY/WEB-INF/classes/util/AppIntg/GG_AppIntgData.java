package util.AppIntg;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import tuxedo.FOISTuxedo;
import util.exception.GG_Exception;
import util.logger.FoisLogger;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class GG_AppIntgData  implements Serializable
{
	private static final long serialVersionUID = 1L;
	static Logger logger = FoisLogger.getLogger(GG_AppIntgData.class.getName());
	public  String[][] getPrclData(String si_LocnFromFlag, String si_LocnFrom, String si_CrntLocnFlag, String si_CrntLocn, String si_LocnToFlag, String si_LocnTo)throws Exception
	{
		String USER_AGENT = "Mozilla/5.0";
		String[][] strData=null;
		String url = "http://10.60.200.171/icmsservices/Controller?action=FOIS&service=currentTrainsPositions&userName=FoIs&password=fOiS&trainType=PEXP";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		logger.info("Calling ICMS Service for Parcel Express Trains");
		String params	=	"userName=FoIs&password=fOiS";
		if(!si_LocnFrom.equals(""))
		{
			if(si_LocnFromFlag.equals("Z"))
				params	=	params + "&fromZone="+si_LocnFrom;
			if(si_LocnFromFlag.equals("D"))
				params	=	params + "&fromDivision="+si_LocnFrom;
			if(si_LocnFromFlag.equals("S"))
				params	=	params + "&fromStation="+si_LocnFrom;
		}
		if(!si_CrntLocn.equals(""))
		{
			if(si_CrntLocnFlag.equals("Z"))
				params	=	params + "&curZone="+si_CrntLocn;
			if(si_CrntLocnFlag.equals("D"))
				params	=	params + "&curDivision="+si_CrntLocn;
			if(si_CrntLocnFlag.equals("S"))
				params	=	params + "&curStation="+si_CrntLocn;
		}
		if(!si_LocnTo.equals(""))
		{
			if(si_LocnToFlag.equals("Z"))
				params	=	params + "&toZone="+si_LocnTo;
			if(si_LocnToFlag.equals("D"))
				params	=	params + "&toDivision="+si_LocnTo;
			if(si_LocnToFlag.equals("S"))
				params	=	params + "&toStation="+	si_LocnTo;
		}
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		logger.info("\nSending 'POST' request to URL : " + url);
		logger.info("Post parameters : " + params);
		logger.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
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
}