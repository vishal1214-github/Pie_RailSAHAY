//import util.XMLLocalization;
package util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tuxedo.FOISTuxedo;
import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;


public class HindiLocaleUtil
{
	
	private String strLangFlag="";
	private HttpSession session=null;
	private HttpServletRequest request=null;
	static Logger logger = FoisLogger.getLogger(HindiLocaleUtil.class.getName());
	public HindiLocaleUtil(String strLangFlag)
	{
		this.strLangFlag=strLangFlag;
	}
	public HindiLocaleUtil(HttpSession s)
	{
		
		this.session=s;
	}
	public HindiLocaleUtil(HttpServletRequest req)
	{
		this.request=req;
	}
	public void convertToHindi(ArrayList lst)
	{
		Class objClass=lst.getClass();
		String strClassName=objClass.getName();
		//System.out.println("strClassName"+ strClassName);
		int intListSize=lst.size();
		Field flds[]=null;
		Method mthd[]=null;
		String str[]=null;
		int intLoopCntr=0;
		int intLoopCntr2=0;
		int intLoopCntr3=0;
		int intLoopCntr4=0;
		String strCrntVal="";
        String strHindiVal="";
		Object retObj=null;
		Object arglist[] = new Object[1];
        Class itemClass=null;
		XMLLocalization xmlObj=new XMLLocalization();
		xmlObj.initHindi("CODES");
		Class modelClass=null;
		for(intLoopCntr=0;intLoopCntr<intListSize;intLoopCntr++)
		{
				Object objItem=lst.get(intLoopCntr);
				itemClass=objItem.getClass();
				if(intLoopCntr==0)
				{
					flds=itemClass.getDeclaredFields();
					mthd=itemClass.getDeclaredMethods();
				}
				str=new String[flds.length];
				String strTempField="";
				String strTempMethod="";
				for(intLoopCntr2=0;intLoopCntr2<flds.length;intLoopCntr2++)
				{
					strTempField=(flds[intLoopCntr2].toString()).substring(((flds[intLoopCntr2].toString()).lastIndexOf(".")+1));
                    if((strTempField.toUpperCase()).indexOf("HREF") >0)
                    {
                        //System.out.println("Href Found");
                    }
                    else
                    {
                        if(strTempField.startsWith("str"))
                        {
                            str[intLoopCntr2]=strTempField.substring(3);
                        }
                        else
                        {
                            str[intLoopCntr2]=flds[intLoopCntr2].toString();
                        }

                        for(intLoopCntr3=0;intLoopCntr3<mthd.length;intLoopCntr3++)
                        {
                            strTempMethod=(mthd[intLoopCntr3].toString()).substring(((mthd[intLoopCntr3].toString()).lastIndexOf(".")+1));
                            //System.out.println(strTempMethod.toUpperCase()+"       "+"GET"+str[intLoopCntr2].toUpperCase()+"()");
                            if((strTempMethod.toUpperCase()).equals("GET"+str[intLoopCntr2].toUpperCase()+"()"))
                            {
                                try
                                {
                                	strCrntVal="";
                                    retObj=mthd[intLoopCntr3].invoke(objItem, null);
                                    strCrntVal=""+retObj.toString();
                                    if(((strCrntVal.toUpperCase()).indexOf("/RailSAHAY/") >= 0) )
                                    {
                                     strHindiVal=strCrntVal;
                                    }
                                    else
                                    {
                                    strHindiVal=xmlObj.localize(strCrntVal.trim());
                                    }
                                   //System.out.println("Converted Value  "+strHindiVal);
                                    //strHindiVal="12";
                                    break;
                                }
                                catch(Exception e)
                                {
                                	strHindiVal=strCrntVal;
                                    //System.out.println("Exception Thrown"+e.toString());
                                }
                            }
                        }
                        arglist[0]="";
                        arglist[0]=strHindiVal;
                        strTempMethod="";
                        //System.out.println("Calling Setters for Bean");
                        for(intLoopCntr3=0;intLoopCntr3<mthd.length;intLoopCntr3++)
                        {
                            //System.out.println((mthd[intLoopCntr3].toString())+"     "+(mthd[intLoopCntr3].toString()).lastIndexOf(".")+1);
                            strTempMethod=(mthd[intLoopCntr3].toString()).substring((mthd[intLoopCntr3].toString()).lastIndexOf("BN.")+3);
                            //System.out.println(strTempMethod.toUpperCase()+"       "+"SET"+str[intLoopCntr2].toUpperCase()+"()");
                            if(((strTempMethod.toString()).toUpperCase()).equals("SET"+str[intLoopCntr2].toUpperCase()+"(JAVA.LANG.STRING)"))
                            {
                                try
                                {
                                    retObj=mthd[intLoopCntr3].invoke(objItem, arglist);
                                    break;
                                }
                                catch(Exception e)
                                {
                                    //System.out.println("Second Exception Thrown !!");
                                }
                            }
                        }
                      //  System.out.println("Called Setters for Bean Successfully");
                    }
                }
		}
		//return list;
	}

	/**Following Function Has been created to return back both Hindi and Well as English value,
	in the format <HindiVal>$#$<EngVal>
	if English value needs to be referred in frontend**/
	public void convertToHindi(ArrayList lst, String strFlag)
	{
		Class objClass=lst.getClass();
		String strClassName=objClass.getName();
		//System.out.println("strClassName"+ strClassName);
		int intListSize=lst.size();
		Field flds[]=null;
		Method mthd[]=null;
		String str[]=null;
		int intLoopCntr=0;
		int intLoopCntr2=0;
		int intLoopCntr3=0;
		int intLoopCntr4=0;
		String strCrntVal="";
		String strHindiVal="";
		Object retObj=null;
		Object arglist[] = new Object[1];
		Class itemClass=null;
		XMLLocalization xmlObj=new XMLLocalization();
		xmlObj.initHindi("CODES");
		Class modelClass=null;
		for(intLoopCntr=0;intLoopCntr<intListSize;intLoopCntr++)
		{
				Object objItem=lst.get(intLoopCntr);
				itemClass=objItem.getClass();
				if(intLoopCntr==0)
				{
					flds=itemClass.getDeclaredFields();
					mthd=itemClass.getDeclaredMethods();
				}
				str=new String[flds.length];
				String strTempField="";
				String strTempMethod="";
				for(intLoopCntr2=0;intLoopCntr2<flds.length;intLoopCntr2++)
				{
					strTempField=(flds[intLoopCntr2].toString()).substring(((flds[intLoopCntr2].toString()).lastIndexOf(".")+1));
					if((strTempField.toUpperCase()).indexOf("HREF") >0)
					{
						//System.out.println("Href Found");
					}
					else
					{
						if(strTempField.startsWith("str"))
						{
							str[intLoopCntr2]=strTempField.substring(3);
						}
						else
						{
							str[intLoopCntr2]=flds[intLoopCntr2].toString();
						}

						for(intLoopCntr3=0;intLoopCntr3<mthd.length;intLoopCntr3++)
						{
							strTempMethod=(mthd[intLoopCntr3].toString()).substring(((mthd[intLoopCntr3].toString()).lastIndexOf(".")+1));
							if((strTempMethod.toUpperCase()).equals("GET"+str[intLoopCntr2].toUpperCase()+"()"))
							{
								try
								{
									strCrntVal="";
									retObj=mthd[intLoopCntr3].invoke(objItem, null);
									strCrntVal=""+retObj.toString();
									//System.out.println("English Value  "+strCrntVal);
									//Conversion to Hindi
									//System.out.println(strCrntVal);
									if(((strCrntVal.toUpperCase()).indexOf("/RailSAHAY/") >= 0) )
                                    {
										strHindiVal=strCrntVal;
                                    }
                                    else
                                    {
                                    	strHindiVal=xmlObj.localize(strCrntVal.trim());
                                    }

								 //  System.out.println("Converted Value  "+strHindiVal);
									//strHindiVal="12";
									break;
								}
								catch(Exception e)
								{
									//System.out.println("IN EXCEPTION"+strCrntVal);
									strHindiVal=strCrntVal;
								}
							}
						}

						arglist[0]="";
						if(strFlag.equals("Y"))
						{
							arglist[0]=strHindiVal+"$#$"+strCrntVal;
							//System.out.println("FINAL BEAN"+arglist[0]);
						}
						strTempMethod="";
						//System.out.println("Calling Setters for Bean");
						for(intLoopCntr3=0;intLoopCntr3<mthd.length;intLoopCntr3++)
						{
							//System.out.println((mthd[intLoopCntr3].toString())+"     "+(mthd[intLoopCntr3].toString()).lastIndexOf(".")+1);
							strTempMethod=(mthd[intLoopCntr3].toString()).substring((mthd[intLoopCntr3].toString()).lastIndexOf("BN.")+3);
							//System.out.println(strTempMethod.toUpperCase()+"       "+"SET"+str[intLoopCntr2].toUpperCase()+"()");
							if(((strTempMethod.toString()).toUpperCase()).equals("SET"+str[intLoopCntr2].toUpperCase()+"(JAVA.LANG.STRING)"))
							{
								try
								{
									retObj=mthd[intLoopCntr3].invoke(objItem, arglist);
									break;
								}
								catch(Exception e)
								{
									//System.out.println("Second Exception Thrown !!");
								}
							}
						}
					  //  System.out.println("Called Setters for Bean Successfully");
					}
				}
		}
		//return list;
	}
	public String convertToHindi(String strInpt)
	{
		//System.out.println("Came for converting value :"+strInpt);
		String strHindiVal ="";
		String strReturn ="";
		XMLLocalization xmlObj=null;
		try
        {
			xmlObj=new XMLLocalization();
			xmlObj.initHindi("CODES");
			strHindiVal=xmlObj.localize(strInpt.trim());
			strReturn = strHindiVal;
        }
        catch(Exception e)
        {

            strReturn = strInpt;
        }
        return strReturn;
	}
	/**Following Function Has been created to return back both Hindi and Well as English value,
	in the format <HindiVal>$#$<EngVal>
	if English value needs to be referred in frontend**/
	public String convertToHindi(String strInpt, String strFlag)
	{
		//System.out.println("Came for converting value :"+strInpt);
		String strHindiVal ="";
		String strReturn ="";
		XMLLocalization xmlObj=null;
		try
		{
			xmlObj=new XMLLocalization();
			xmlObj.initHindi("CODES");
			strHindiVal=xmlObj.localize(strInpt.trim());
			strReturn = strHindiVal;
		}
		catch(Exception e)
		{
			strReturn = strInpt;
		}
		if(strFlag.equals("Y"))
		{
			strReturn=strReturn+"$#$"+strInpt;
		}
		return strReturn;
	}

public String EHSPACE(String strInpt)
{
	String strHindiVal ="";
	String strReturn1 ="";
	String strTemp ="";
	strInpt=strInpt.trim();
	String strSplitLine="";
	XMLLocalization xmlObj=null;
	 try
     {
		xmlObj=new XMLLocalization();
		xmlObj.initHindi("HEAD_LABEL");
		try
		{
			strHindiVal=xmlObj.localize(strInpt.trim());
			strReturn1=strHindiVal;
		}
		catch(Exception e)
		{
			try
			{
				strInpt=strInpt.replace(" ", "^");
				StringTokenizer stSplitLine=new StringTokenizer(strInpt,"^");
				while(stSplitLine.hasMoreTokens())
				{
					strSplitLine = stSplitLine.nextToken();
					try
					{
						strHindiVal=xmlObj.localize(strSplitLine.trim());
						strTemp = strTemp+strHindiVal;
					    strTemp = strTemp+" ";
					}
					catch(Exception e1)
					{

						strTemp = strTemp+strSplitLine;
					    strTemp = strTemp+" ";
					}
				}
				strReturn1 =   strTemp.trim();
			}
			catch(Exception e1)
		     {
				 strInpt=strInpt.replace("^", " ");
		         strReturn1 = strInpt;
		     }
		}
		return strReturn1;
     }
     catch(Exception e)
     {
         strReturn1 = strInpt;
     }
     return strReturn1;
}
public String EHE(String strInpt)
{
    //System.out.println("Input"+strInpt);
	String strHindiVal="";
	String strDlmtr="$#$";
	String strRetVal="";
	if ((this.strLangFlag).equals("H"))
	{
		if((strInpt==null)||(strInpt.equals("")))
		{
			return strDlmtr;
		}
		else
		{
			strHindiVal=EH(strInpt);
			//System.out.println("EH_HINDI"+strHindiVal);
			strRetVal=strHindiVal+strDlmtr+strInpt;
		}
		//System.out.println("strRetVal"+strRetVal);
	}
	else
	{
		strRetVal=strInpt;
	}
	return strRetVal;
}
public String EH(String strInpt)
{
		//System.out.println("Came for converting value :"+strInpt);
		//System.out.println("Came for converting value LANGUAGE:"+strLangFlag);
		String strHindiVal ="";
		String strReturn ="";
		String strReturn1 ="";
		String strTemp ="";
		strInpt=strInpt.trim();
		XMLLocalization xmlObj=null;
	//	System.out.println("Entered in EH with :"+strInpt+": LangFlag"+this.strLangFlag);
	if ((this.strLangFlag).equals("H"))
	{
	 try
        {
			xmlObj=new XMLLocalization();
			xmlObj.initHindi("HEAD_LABEL");
			 String strSplitLine="";
			 if (strInpt.indexOf(";")>0)
			 {
	         StringTokenizer stSplitLine=new StringTokenizer(strInpt,";");
	            while(stSplitLine.hasMoreTokens())
	            {
	            	strSplitLine = stSplitLine.nextToken();
	            	 strSplitLine=strSplitLine.trim();
	            	 //System.out.println("Find String :"+strSplitLine);
	            	 try
					 {
	            		 strHindiVal=xmlObj.localize(strSplitLine);
	            		 strTemp=strTemp+strHindiVal;
					 }
					 catch (Exception e)
					 {
						 //System.out.println("Dint find entire string");
						 strHindiVal=strSplitLine;
					 }
	            	if(strHindiVal.equals(strSplitLine))
	            	{
	            	    if (strSplitLine.indexOf(" ")>0)
	            	    {
	            	    	strReturn1=EHSPACE(strSplitLine);
	            	    	strTemp = strTemp+strReturn1;
	            	    }
		            	else
		            	{
							try
							 {
									strHindiVal=xmlObj.localize(strSplitLine.trim());
									strTemp = strTemp+strHindiVal;
							 }
						 catch (Exception e)
						 {
							 //System.out.println("Dint find entire string");
							   strHindiVal=strSplitLine;
							   strTemp = strTemp+strSplitLine;

						 }
		            	}

		            	strTemp = strTemp+";";
	            	}
	            	else
	            	{
	            		strTemp = strTemp+";";
	            	}
	            }
	            strReturn =   strTemp.substring(0,strTemp.length()-1);
	            return strReturn;
	          }
			 else
			 {
				 strInpt= strInpt.trim();
				 String strInpt1=strInpt.toLowerCase().trim();
				 try
				 {
	             strHindiVal=xmlObj.localize(strInpt1);
				 }
				 catch (Exception e)
				 {
					 strHindiVal=strInpt1;
				 }
	             if(strInpt1.equals(strHindiVal))
	             {
				  if (strInpt1.indexOf(" ")>0)
            	    {
			          strHindiVal=EHSPACE(strInpt1.trim());
            	    }
				  else
				  {
					  strHindiVal=xmlObj.localize(strInpt1.trim());
				  }
	             }
	             else
	             {
	            	 return strHindiVal;
	             }
				strReturn = strHindiVal;
			 }
        }
        catch(Exception e)
        {
            strReturn = strInpt;
        }
	}
	else
	{
		 strReturn = strInpt;
	}
  return strReturn;
}
	public String EHR(String strInpt)
	{
		String strHindiVal ="";
		String strReturn ="";
		String strTemp ="";
		XMLLocalization xmlObj=null;
		if ((this.strLangFlag).equals("H"))
		{
				try
		        {
					xmlObj=new XMLLocalization();
					xmlObj.initHindi("REPORT_HEAD");

					 String strSplitLine="";
					 if (strInpt.indexOf(";")>0)
					 {
			         StringTokenizer stSplitLine=new StringTokenizer(strInpt,";");
			            while(stSplitLine.hasMoreTokens())
			            {
			            	strSplitLine = stSplitLine.nextToken();
			            	strHindiVal=xmlObj.localize(strSplitLine.trim());
			            	strTemp = strTemp+strHindiVal;
			            	strTemp = strTemp+";";
			            }
			            strReturn =   strTemp.substring(0,strTemp.length()-1);
			          }
					 else
					 {
						 strHindiVal=xmlObj.localize(strInpt.trim());
						strReturn = strHindiVal;
					 }
		        }
		        catch(Exception e)
		        {
		            strReturn = strInpt;
		        }
		}
		else
		{
			 strReturn = strInpt;
		}
  return strReturn;
	}
	public String HTAGVLDT(String strInpt)
	{
		String strHindiVal ="";
		String strReturn ="";
		String strTemp ="";
		XMLLocalization xmlObj=null;
		if ((this.strLangFlag).equals("H"))
		{
				try
		        {
					xmlObj=new XMLLocalization();
					xmlObj.initHindi("TAG_VLDT");
					 strHindiVal=xmlObj.localize(strInpt.trim());
					strReturn = strHindiVal;
		        }
		        catch(Exception e)
		        {
		              strReturn = strInpt;
		        }
		}
		else
		{
			 strReturn = strInpt;
		}
        return strReturn;
	}
	public String EHD(String strInpt)
	{
		String strHindiVal ="";
		String strReturn ="";
		String strTemp ="";
		XMLLocalization xmlObj=null;
		if ((this.strLangFlag).equals("H"))
		{
				try
		        {
					xmlObj=new XMLLocalization();
					xmlObj.initHindi("HEAD_DATA");
					 strHindiVal=xmlObj.localize(strInpt.trim());
					strReturn = strHindiVal;
		        }
		        catch(Exception e)
		        {
		              strReturn = strInpt;
		        }
		}
		else
		{
			 strReturn = strInpt;
		}
        return strReturn;
	}

	public String PRNTHD(String strInpt)
	{
		String strFormatedInpt	= "";
		if (strInpt == null)

		{
			return strFormatedInpt;
		}
		int strtIndx			= 0;
		int endIndx				= 0;

		String strParamHead   ="";
		String strHeadConvert   = "";
		//System.out.println("::" +strInpt +"::");
		while(true)
		{
			strtIndx			= strInpt.indexOf(":", endIndx);
			if(strtIndx == -1)
				break;

			strParamHead=strInpt.substring(endIndx, strtIndx + 1);
			//System.out.println("strParamHead"+ strParamHead);
			if (strParamHead.indexOf(", ") >= 0)
					{
					int indx1=strParamHead.indexOf(",");
					strParamHead = strParamHead.substring(indx1+1);
					//System.out.println("strParamHead1"+ strParamHead);
					}
				strParamHead=strParamHead.trim();
				if(strParamHead.indexOf(":")>0)
				{
					strHeadConvert=strParamHead.substring(0,strParamHead.length()-1);
					//System.out.println("strHeadConvert BEfore"+ strHeadConvert);
					strHeadConvert=EH(strHeadConvert);
					//System.out.println("strHeadConvert After"+ strHeadConvert);

					if(endIndx==0)
					{
						strParamHead=strHeadConvert+":";
						//System.out.println("strParamHead3"+ strParamHead);
					}
					else
					{
						strParamHead=" ,"+strHeadConvert+":";
						//System.out.println("strParamHead4"+ strParamHead);
					}

				}


			strFormatedInpt		= strFormatedInpt + strParamHead;
			//System.out.println("strFormatedInpt"+ strFormatedInpt);

			endIndx				= strInpt.indexOf(", ", strtIndx + 1);
			if(endIndx == -1)
			{
				strFormatedInpt	+= strInpt.substring(strtIndx + 1, strInpt.length()) ;
				break;
			}
			strFormatedInpt		+=  strInpt.substring(strtIndx + 1, endIndx);

			//System.out.println("strFormatedInpt2"+ strFormatedInpt);
		}
		//System.out.println("strFormatedInptFinal"+ strFormatedInpt);
		return strFormatedInpt;
	}
	
	
	
}