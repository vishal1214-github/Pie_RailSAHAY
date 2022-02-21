package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/********************************************************************************
*	The file is Used to convert the engish data to any Localized data...		*
*	depending on filename or Hashtable containing the manipulation....			*
*	Created By - Kunal Malhan, Vinay Yadav, Mitesh Saini						*
*	Created on - 20-02-2008

	Modified by - Raman Arora
	Modified On	- 01-05-2008													*
********************************************************************************/
public class XMLLocalization
{
	 String splitRegularExpr	= "@|/|-|:|;|\\[|\\]|,|#| |=|\\.|\\(|\\)";
	 Hashtable htbProps		= null;
	 ArrayList arrProps		= null;
    public XMLLocalization()
	{
			 htbProps		= new Hashtable(10);
			 arrProps		= new ArrayList(10); 
	}
	public void initHindi(String strPropHead)
	{
		LoadPropsList("hindi", htbProps, arrProps,strPropHead);
	}
	public String localize(String strData)
	{
		String strNewData		= "";
		if(strData.equals("") || strData==null)
		{
			return "";
		}
		strNewData=lookUpData(strData, htbProps,  arrProps);
		return strNewData;
	}
	public void LoadPropsList(String strLangCode, Hashtable htbProps, ArrayList arrProps, String strPropHead)
	{
        String SWITCH_HOME		= "";
		try
		{
			// ArrayList Acc to Pripority...
			// The string indicates Search made on Tagname...
			// Getting new Unicodes for all available Words...

           //htbProps.put("typecodes", 	LoadProps("/switch/applications/foisweb_application/propertyfiles/" + strLangCode + "_typecodes.properties"));
           //htbProps.put("cmdtcodes", 	LoadProps("/switch/applications/foisweb_application/propertyfiles/" + strLangCode + "_cmdtcodes.properties"));
           //htbProps.put("sttncodes", 	LoadProps("/switch/applications/foisweb_application/propertyfiles/" + strLangCode + "_sttncodes.properties"));
           //htbProps.put("codes", 		LoadProps("/switch/applications/foisweb_application/propertyfiles/" + strLangCode + "_codes.properties"));

            if(strPropHead.equals("CODES"))
            {
	          //htbProps.put("codes", 		LoadProps("D:/propertyfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));
	         // htbProps.put("codes", 		LoadProps("/switch/applications/FOISPORTAL/propertfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));
	        htbProps.put("codes", 		LoadProps("/switch/Applications/propertyfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));

            }
            if(strPropHead.equals("REPORT_HEAD"))
            {
    			arrProps.add("heading");
            	//htbProps.put("headingcodes", 	LoadProps("D:/propertyfiles/SahayLangCodes/" + strLangCode + "_headingcodes.properties"));
            	//htbProps.put("headingcodes", 	LoadProps("/switch/applications/FOISPORTAL/propertfiles/SahayLangCodes/" + strLangCode + "_headingcodes.properties"));
            	htbProps.put("headingcodes", 	LoadProps("/switch/Applications/propertyfiles/SahayLangCodes/" + strLangCode + "_headingcodes.properties"));
            }
            if(strPropHead.equals("TAG_VLDT"))
            {
    			arrProps.add("tagvldt");
            	//htbProps.put("tagvldtcodes", 	LoadProps("D:/propertyfiles/SahayLangCodes/" + strLangCode + "_tagvldtcodes.properties"));
            	//htbProps.put("tagvldtcodes", 	LoadProps("/switch/applications/FOISPORTAL/propertfiles/SahayLangCodes/" + strLangCode + "_tagvldtcodes.properties"));
            	htbProps.put("tagvldtcodes", 	LoadProps("/switch/Applications/propertyfiles/SahayLangCodes/" + strLangCode + "_tagvldtcodes.properties"));
            }
            if(strPropHead.equals("HEAD_LABEL"))
            {
    			arrProps.add("headlbl");
            	//htbProps.put("headlblcodes", 	LoadProps("D:/propertyfiles/SahayLangCodes/" + strLangCode + "_headlblcodes.properties"));
            	//htbProps.put("headlblcodes", 	LoadProps("/switch/applications/FOISPORTAL/propertfiles/SahayLangCodes/" + strLangCode + "_headlblcodes.properties"));
            	htbProps.put("headlblcodes", 	LoadProps("/switch/Applications/propertyfiles/SahayLangCodes/" + strLangCode + "_headlblcodes.properties"));
            }
            if(strPropHead.equals("HEAD_DATA"))
            {
            	//htbProps.put("codes", 		LoadProps("D:/propertyfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));
            	//htbProps.put("codes", 		LoadProps("/switch/applications/FOISPORTAL/propertfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));
            	htbProps.put("codes", 		LoadProps("/switch/Applications/propertyfiles/SahayLangCodes/" + strLangCode + "_codes.properties"));
            }

		}
		catch(Exception e)
		{
			System.out.println("Error Loading the Property properties" );
			e.printStackTrace();
		}
	}
	public String updateTagData(String strXML, Hashtable htbProps, ArrayList arrProps)
	{
		writeOutput(strXML, "OLD_XML.xml");
		java.util.Date startTime	= new java.util.Date();
		//System.out.println("Start Time :: " + startTime);
		int intCurrPosn			= 0;
		int intNextPosn			= 1;
		int intStrtTagPosn		= 0;
		int intEndTagPosn		= 0;
		int intStrtPosn			= 0;
		int intEndPosn			= 0;
		int intPrevStrtPosn		= 0;
		int intPrevEndPosn		= 0;
		String strStartPattern 	= ">";
		String strEndPattern	= "</";
		String strResultString	= "";
		String strLocalCodeTemp = "";
		String strTagName		= "";
		String strToBeLookUp	= "";
		StringBuffer strNewXML	= new StringBuffer();
		StringBuffer strNewXML1	= new StringBuffer();
		StringBuffer strXMLB	= new StringBuffer();

		strXMLB		= new StringBuffer(strXML);
		//strXML	= ReplaceHeader(strXML, headerProperties);
		//strXMLB	= new StringBuffer(ReplaceHeader(strXML, headerProperties));

		//int l = 0;
		while(true) //l <= 100)
		{
			intStrtTagPosn		= strXMLB.indexOf(strEndPattern, intCurrPosn) + 2;
			intEndTagPosn		= strXMLB.indexOf(strStartPattern, intStrtTagPosn);
			strTagName			= strXMLB.substring(intStrtTagPosn, intEndTagPosn);

			intStrtPosn			= strXMLB.indexOf("<" + strTagName + ">", intCurrPosn) + strTagName.length() + 2;
			intEndPosn			= strXMLB.indexOf("</" + strTagName + ">", intCurrPosn);

			if(intEndPosn + strTagName.length() + 3 >= strXMLB.length())
			{
				strNewXML1.append(strXMLB.substring(intPrevEndPosn, strXMLB.length()));
				break;
			}

			if(intStrtPosn <= intEndPosn)
			{
				/*if(intPrevEndPosn > intStrtPosn)
				{
					System.out.println("exiting::"+strNewXML1);
					System.out.println("exiting::"+strTagName);
					strNewXML1.append(strXMLB.substring(intPrevEndPosn, strXMLB.length()));
					break;
				}*/
				if(intPrevEndPosn <= intStrtPosn)
				{
					if(strTagName.toLowerCase().indexOf("rakename") >= 0 ||
						strTagName.toLowerCase().indexOf("loadname") >= 0 ||
						strTagName.toLowerCase().indexOf("id") >= 0)
							strToBeLookUp	= strXMLB.substring(intStrtPosn, intEndPosn);
					else
						//strToBeLookUp		= lookUpData(strXMLB.substring(intStrtPosn, intEndPosn), strTagName, htbProps, arrProps);
					strNewXML1.append(strXMLB.substring(intPrevEndPosn, intStrtPosn)).append(strToBeLookUp);
					intPrevEndPosn	= intEndPosn;
				}
			}
			//System.out.println("::" + strTagName + "::" + strToBeLookUp + "::");

			intCurrPosn			= intEndPosn + strTagName.length() + 3;
			//l++;
		}

		//System.out.println("##" + strNewXML1);
		/*int l = 0;
		whilew(true) //l < 100)
		{
			// Getting new start data index
			intCurrPosn 	= strXMLB.indexOf(strStartPattern, intNextPosn);
			// Put same Tag Names.....in new XML string..
			if(intNextPosn < intCurrPosn)
			{
				if((intCurrPosn+1)>intPrevEndPosn)
					strNewXML1	= strNewXML.append(strXMLB.substring(intPrevEndPosn, (intCurrPosn+1)));
				//System.out.println("return sucess");
			}
			// Getting end posn for data
			intEndPosn		= strXMLB.indexOf(strEndPattern, intCurrPosn );
			//System.out.println("String: " + intEndPosn );
			if (intEndPosn > 1)
			{
				if(intEndPosn>(intCurrPosn+1))
					if(strXML.charAt(intCurrPosn+1)!='=')
						strResultString	= strXMLB.substring((intCurrPosn+1), intEndPosn);
				if (!(strResultString.equals(">") || strResultString.equals("")))
				{
					// Got the string now do the manipulation
					if(strXML.charAt(intCurrPosn+1)!='=')
						strNewXML	= strNewXML.append(lookUpData(strResultString,properties));
				}
				else
				{
					if(strXML.charAt(intCurrPosn+1)!='=')
						strNewXML	= strNewXML.append(strResultString);
				}
			}
			else
				break;
			intNextPosn			= intCurrPosn+1;
			intPrevEndPosn		= intEndPosn;
			l++;
		}*/
		java.util.Date endTime	= new java.util.Date();
		//System.out.println("End Time :: " + endTime);
		//System.out.println("Total Time (msec) :: " + (endTime.getTime()-startTime.getTime()));

		writeOutput(strNewXML1.toString(), "LATEST_XML.xml");
		return strNewXML1.toString();
	}

	public String ReplaceHeader(String strFinalXml, Hashtable propertiesHeader)
	{
		java.util.Date startTime= new java.util.Date();
		//System.out.println("INSIDE REPLACE HEADER :: " + startTime);
		String key				= "";
		String value			= "";
		Enumeration keys		= propertiesHeader.keys();
		while (keys.hasMoreElements())
		{
			key					= (String)keys.nextElement();
			value				= (String)propertiesHeader.get(key);
			{
				strFinalXml		= strFinalXml.replaceAll(doublecase(key),value);
			}
		}
		java.util.Date endTime	= new java.util.Date();
		//System.out.println("EXIT REPLACE HEADER :: " + endTime);
		//System.out.println("Total Time (msec) :: " + (endTime.getTime()-startTime.getTime()));
		return strFinalXml;
	}

	public String doublecase(String str)
	{
		String retstr	= "";
		for(int i=0;i<str.length();i++)
		{
			String c	= "" + str.charAt(i);
			retstr		+="[" + c.toLowerCase() + "|" + c.toUpperCase() + "]";
		}
		return retstr;
	}

	public String lookUpData(String strData, Hashtable htbProps, ArrayList arrProps)
	{
		//System.out.println("*****************" + strData + "::" + strTagName);
		String strLocalCode		= null;
		for(int k=0; k<arrProps.size(); k++)
		{
			//if(strTagName.indexOf((String)arrProps.get(k)) >= 0)
			//{
				//System.out.println("SPCL SRCH::" + strData);
				strLocalCode	= (String)((Hashtable)htbProps.get(((String)arrProps.get(k)) + "codes")).get(strData.toLowerCase().trim());
				//System.out.println("strLocalCode"+ strLocalCode);
				if(strLocalCode != null)
					break;
			//}
		}
		if(strLocalCode == null)
		{
			//System.out.println("finl SRCH::" + strData);
			strLocalCode		= (String)((Hashtable)htbProps.get("codes")).get(strData.toLowerCase().trim());
		}
	if(strLocalCode == null)
		{


			strLocalCode			= "";
			int intSplitCharPosn	= 0;
			String[] strDataList	= strData.split(splitRegularExpr);

			for(int i=0; i<strDataList.length; i++)
			{
				intSplitCharPosn	= intSplitCharPosn + strDataList[i].length() + 1;
				String strTemp		= null;
				for(int k=0; k<arrProps.size(); k++)
				{

						strTemp		= (String)((Hashtable)htbProps.get(((String)arrProps.get(k)) + "codes")).get(strDataList[i].toLowerCase().trim());
						if(strTemp != null)
							break;

				}
				if(strTemp == null)
					strTemp			= (String)((Hashtable)htbProps.get("codes")).get(strDataList[i].toLowerCase().trim());

				if(strTemp != null)
				{
					strLocalCode	= strLocalCode + strTemp;
				}
				else
				{
					strLocalCode	= strLocalCode + strDataList[i];
				}
				if(intSplitCharPosn <= strData.length())
					strLocalCode	= strLocalCode + strData.charAt(intSplitCharPosn - 1);
			}
			if(intSplitCharPosn <= strData.length() && intSplitCharPosn >= 0)
				strLocalCode		= strLocalCode + strData.substring(intSplitCharPosn, strData.length());


		}

		return strLocalCode.trim();
	}


	public String lookUpHeader(String strData, Hashtable properties)
	{
		String strLocalCode		= (String)properties.get(strData.toLowerCase().trim());
		if(strLocalCode == null)
			return strData;
		else
			return strLocalCode.trim();
	}

	public void writeOutput(String str, String strFileName)
	{
		try
		{
			FileOutputStream fos	= new FileOutputStream(strFileName);
			Writer out				= new OutputStreamWriter(fos, "UTF8");
			out.write(str);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Hashtable LoadProps(String strFileName)
    {
        Hashtable prp			= null;

        /* Added by Vinay to read propery file in utf-8 format
		and store the data in String array to make manipulation*/
		try
		{
			InputStream is		= new BufferedInputStream(new FileInputStream(strFileName));
			BufferedReader in	= new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String line;
			while ((line = in.readLine()) != null)
			{
				//System.out.println(line);
				String[] pieces	= line.split("=");

				//if(pieces[0].length() > 0 && pieces[0].substring(1, pieces[0].length()).equals("length"))
				if(prp == null)
					prp			=	new Hashtable(Integer.parseInt(pieces[1]));

				if(pieces.length != 2)
				{
					//System.out.println(strFileName + ":: is not in Proper format. Cant understand :: " + line);
				}
				else
				{
					prp.put(pieces[0], new String(pieces[1].getBytes("UTF-8"), "UTF-8"));
				}
			}
			in.close();
			is.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
        return prp;
    }

	public String[] split(String str, String token)
	{
		StringTokenizer st	= new StringTokenizer(str, token);
		String[] strArray	= new String[st.countTokens()];
			int k			= 0;
		while (st.hasMoreTokens())
		{
			strArray[k]	= st.nextToken();
			k++;
		}
		return strArray;
	}
}
