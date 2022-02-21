package model;


import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import org.codehaus.jettison.json.JSONObject;

import tuxedo.FOISTuxedo;

import util.GG_Validate;

import util.logger.FoisLogger;

public class FSH_FrgtCalc {
    public FSH_FrgtCalc() {
        super();
    }

    static Logger logger = FoisLogger.getLogger(FSH_FrgtCalc.class.getName());
    
    public static String[][] getDtls(String siSttnFrom,String siSttnTo,String siCmdt,String siWgonType,String siUserType,String siRKPM,String siWgonNumb,
                                     String si_selWgonCtgy,String si_optWgontype)
    {
        //NDC.push("CalculateExpectedFreight");
        logger.info("Entering FSH_FrgtCalc..getDtls..");
        //siCmdt  =   StringEscapeUtils.unescapeJava(siCmdt);
        siCmdt = siCmdt.toUpperCase();
        siSttnFrom = siSttnFrom.toUpperCase();
        siSttnTo = siSttnTo.toUpperCase();
        logger.info("siWgonType:"+siWgonType);        
        logger.info("si_selWgonCtgy:"+si_selWgonCtgy);    //BCN,BTPN,BRN,BOXN    
        logger.info("si_optWgontype:"+si_optWgontype);      //C,T
        String strFromsttn  =   siSttnFrom;
        String strTosttn  =   siSttnTo;
        
        String strPrcnW =   "0";        
        String strPrcnT =   "0";
        try
        {
            if(siSttnFrom.indexOf("-") != -1)
                    siSttnFrom                              =       siSttnFrom.substring(siSttnFrom.lastIndexOf("-")+1,siSttnFrom.lastIndexOf("(")).trim();
            if(siSttnTo.indexOf("-") != -1)
                    siSttnTo                                =       siSttnTo.substring(siSttnTo.lastIndexOf("-")+1,siSttnTo.lastIndexOf("(")).trim();
       //     if(siCmdt.indexOf("-") != -1)
         //           siCmdt                                  =       siCmdt.substring(0,siCmdt.lastIndexOf("-")).trim();
         if(siWgonType.indexOf("-") != -1)
                 siWgonType                              =       siWgonType.substring(0,siWgonType.indexOf("-")).trim();
         /*
            if(si_optWgontype.equals("T"))
            {
                if(siWgonType.indexOf("-") != -1)
                        siWgonType                              =       siWgonType.substring(0,siWgonType.indexOf("-")).trim();
                else if(si_selWgonCtgy != null)
                    if(!si_selWgonCtgy.equals(""))
                        siWgonType  =   si_selWgonCtgy;
            }
            else
                siWgonType  =   si_selWgonCtgy;
*/
        }
        catch(Exception e){}
        logger.info("siSttnFrom:"+siSttnFrom);               
        logger.info("siSttnTo:"+siSttnTo);           
        logger.info("siWgonType:"+siWgonType);               
        logger.info("siCmdt:"+siCmdt);               
        logger.info("siRKPM:"+siRKPM);               
        logger.info("siWgonNumb:"+siWgonNumb);  
        
        String responseXml      = ""; 
        String strOutput[][]    = new String[14][19];     // 0th row is for wagon load,1st row is for train load and 3rd row is for error, after that for other charges , rebate charges & surcharge details for wagon & train load in case of rake/minirake and wagon load other charges, rebate and surcharge in case of piecemeal
                                                         // 12th row is having xml response from prefrgtcalc on its first column
        for(int i=0;i<14;i++)
                for(int j=0;j<18;j++)
                        strOutput[i][j]="";
        
        String strCmdtClssTL=       "";
        String strCmdtClssWL=        "";
        String strRateTL    =        "";
        String strRateWL    =        "";
        String strRoutInfo  =        "";
        String strViaDesc   =        "";
        String strCCInfo    =        "";
        String strDist      =        "";
        String strRakeSize  =        "";
        String strPCC       =        "";
        String strETA       =       "";
        String strMiniRake  =        "";
        String strTotlChblWght  =    "";
        String strTEFDW  =    "";
        String strTEFDT  =    "";
        String strMinimumRakeSize = "";
        
            try
            {
        //        if(!GG_Validate.chkName(siCmdt)) return null;
        //        if(!GG_Validate.chkName(siSttnFrom)) return null;
        //        if(!GG_Validate.chkName(siSttnTo)) return null;

       logger.info("aFTER vaLIDATION:");
                Date date = Calendar.getInstance().getTime();

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = formatter.format(date);
                logger.info("Today : " + strDate);

                    FOISTuxedo TQPREFRGTCALC        = null;
                    try
                    {
                            logger.info("Calling Tuxedo");
                            TQPREFRGTCALC = new FOISTuxedo();
                    }
                    catch (Exception ne)
                    {
                            logger.info("Unable to get the Client Object");
                            return null;
                    }
                    
                    try
                    {
                            TQPREFRGTCALC.tuxInit("TQPREFRGTCALC");

                            logger.info("strDate:"+strDate+":");
                            logger.info("siSttnFrom:"+siSttnFrom+":");
                            logger.info("siSttnTo:"+siSttnTo+":");
                            logger.info("siCmdt:"+siCmdt+":");
                            logger.info("siWgonType:"+siWgonType+":");
                            logger.info("siUserType:"+siUserType+":");
                        
                            TQPREFRGTCALC.setString("F_DATE",       0, strDate.trim());//java.time.LocalTime.now()+"");
                            TQPREFRGTCALC.setString("F_STTNFROM",   0, siSttnFrom.trim());
                            TQPREFRGTCALC.setString("F_STTNTO",     0, siSttnTo.trim());
                            TQPREFRGTCALC.setString("F_CMDT",       0, siCmdt.trim());
                            TQPREFRGTCALC.setString("F_WGONTYPE",   0, siWgonType.trim());
                            TQPREFRGTCALC.setString("F_USERID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_CLNTID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_FLAG",       0, siUserType);
                    }
                    catch(Exception e)
                    {
                            //e.printStackTrace();
                            logger.info("Unable to buffers");
                            return null;
                    }
    
                    String erorCode                 = null;
                    
                    try
                    {
                            logger.info("Calling Tuxedo Service TQPREFRGTCALC ...");
                            TQPREFRGTCALC.call("N");
                            logger.info("CALL COMPLETED !");
                    }
                    catch(Exception e)
                    {                               
                            //e.printStackTrace();
                            logger.info("IN Freight Calculator Service is currently unavailable.Please try after some time." + e.toString());
                            erorCode = "Freight Calculator Service is currently unavailable.Please try after some time.";
                            return null;
                    }
                    
                    try
                    {
                            logger.info("BeforeErrorCode: " + erorCode+":");
                            erorCode                = TQPREFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                            logger.info("AfterErrorCode: " + erorCode+":");
                    }
                    catch(Exception e)
                    {
                            // F_ERORNO is not set by Developer, So, it is not an Error
                    }
                    if(erorCode != null && (!erorCode.equals("")))
                    {
                            logger.info("ErrorCode: " + erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            return null;
                    }
                    strCmdtClssTL   =        TQPREFRGTCALC.getStringItemDef("F_RATECLSS",   0,      "0").trim();
                    strCmdtClssWL   =        TQPREFRGTCALC.getStringItemDef("F_PRTYCLSS",   0,      "0").trim();
                    strRateTL       =        TQPREFRGTCALC.getStringItemDef("F_RATE",       0,      "0").trim();
                    strRateWL       =        TQPREFRGTCALC.getStringItemDef("F_FRGTRATE",   0,      "0").trim();
                    strRoutInfo     =        TQPREFRGTCALC.getStringItemDef("F_ROUTID",     0,      "0").trim();
                    strViaDesc      =        TQPREFRGTCALC.getStringItemDef("F_VIA",        0,      "0").trim();
                    strCCInfo       =        TQPREFRGTCALC.getStringItemDef("F_ROUTNUMB",   0,      "0").trim();
                    strDist         =        TQPREFRGTCALC.getStringItemDef("F_CHBLDIST",   0,      "0").trim();
                    strRakeSize     =        TQPREFRGTCALC.getStringItemDef("F_NUMBWGON",   0,      "0").trim();
                    strPCC          =        TQPREFRGTCALC.getStringItemDef("F_CHBLWGHT",   0,      "0").trim();
                    strETA          =        TQPREFRGTCALC.getStringItemDef("F_CALLTIME",   0,      "0").trim();
                    strMinimumRakeSize =        TQPREFRGTCALC.getStringItemDef("F_ORDRBY1",   0,      "0").trim();
                    strMiniRake     =        TQPREFRGTCALC.getStringItemDef("F_TOTLWGON",   0,      "0").trim();                                    
                    
                    if(strPCC.equals("0.00")|| strPCC.equals(""))
                        strPCC  =   "1.00";
                    if(strRoutInfo.endsWith("R"))
                            strRoutInfo     =       "Rationalized";
                    else if(strRoutInfo.endsWith("S"))
                            strRoutInfo     =       "Shortest";
                    strETA  =   Math.round(Double.parseDouble(strETA))+"";
                    responseXml             =       strCmdtClssTL + "@" +strCmdtClssWL + "@" +strRateTL + "@" +strRateWL + "@" +strRoutInfo + "@" +strViaDesc +"@" +strCCInfo +"@" + strDist + "@" + strRakeSize + "@" + strPCC + "@" + strMiniRake + "@" + strETA + "@" + strMinimumRakeSize;
                    logger.info("Response after TQPrefrgtcalc call:"+responseXml);
                    

                String startRowCount   = null;
                int start              = 0;
                try
                {
                        startRowCount          = TQPREFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                }
                catch(Exception e){}
                logger.info("startRowCount:"+ startRowCount);
                if(startRowCount.equals("0") || startRowCount.equals(""))
                        logger.info("ErrorCode: " + "No data found");
                else
                        start = new Integer(startRowCount.trim()).intValue();
                String strAltSttnFrom="";
                String strAltSttnTo="";
                String strAltRout="[";
                for(int k=0; k<start; k++)
                {
                    try
                        {
                                strAltSttnFrom           = TQPREFRGTCALC.getStringItemDef("F_ORIGSTTNFROM",            k,      "0").trim();
                                strAltSttnTo             = TQPREFRGTCALC.getStringItemDef("F_ORIGDSTNSTTN",            k,      "0").trim();

                                if(!(strAltSttnFrom.equals(strFromsttn) && strAltSttnTo.equals(strTosttn)))
                                    if(k==0)
                                            strAltRout+="{\"SttnFrom\":\""+strAltSttnFrom+"\",\"SttnTo\":\""+strAltSttnTo+"\"}";
                                    else
                                            strAltRout+=",{\"SttnFrom\":\""+strAltSttnFrom+"\",\"SttnTo\":\""+strAltSttnTo+"\"}";                                         
                        }
                        catch(Exception e)
                        {
                                logger.info("Error In getting output buffers:"+e.toString());
                        }
                } 
                strAltRout+="]";
                
                strOutput[12][0]    =   responseXml;
                strOutput[13][0]    =   "{\"Data\":"+strAltRout+"}";   
                logger.info("Alternate Route:"+strOutput[13][0]);                 
                                
                logger.info("Entering TQ_FrgtCalc....");
                FOISTuxedo TQFRGTCALC   = null;
                
                try
                {
                    logger.info("Calling Tuxedo");
                    TQFRGTCALC = new FOISTuxedo();
                    logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                    logger.info("Tuxedo Connnection Failed.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                     return null;
                }
                // SETTING BUFFERS VALUES
                try
                {                               
                        logger.info("si_CmdtName:"+siCmdt+":");
                        logger.info("si_SttnFrom:"+siSttnFrom+":");
                        logger.info("si_SttnTo:"+siSttnTo+":");
                        logger.info("siWgonNumb:"+siWgonNumb);
                        logger.info("strRakeSize:"+strRakeSize);
                    
                        TQFRGTCALC.tuxInit("TQFRGTCALC");
                        // Passing Input
                        TQFRGTCALC.setString("F_DATE",          0, strDate);
                        TQFRGTCALC.setString("F_STTNFROM",      0, siSttnFrom.trim());
                        TQFRGTCALC.setString("F_STTNTO",        0, siSttnTo.trim());
                        TQFRGTCALC.setString("F_VIA",           0, strRoutInfo.trim());
                        TQFRGTCALC.setString("F_USERID",        0, "CRIS1");
                        TQFRGTCALC.setString("F_CLNTID",        0, "CRIS1");
                        TQFRGTCALC.setString("F_DIST",          0, strDist.trim());
                        TQFRGTCALC.setString("F_PAIDTYPE",      0, "");
                        TQFRGTCALC.setString("F_RISKRATE",      0, "");
                        TQFRGTCALC.setString("F_CMDTDESC",      0, "");
                        TQFRGTCALC.setString("F_TRFCTYPE",      0, "G");
                        TQFRGTCALC.setString("F_RATE",          0, "C");
                        TQFRGTCALC.setString("F_WGONTYPE",      0, siWgonType.trim());
                        TQFRGTCALC.setString("F_CMDTCODE",      0, siCmdt.trim());
                        logger.info("strPCC:"+strPCC);
                        TQFRGTCALC.setString("F_PMBLWGHT",      0, strPCC.trim());

                        //TQFRGTCALC.setString("F_NUMB",          0, strRakeSize);                   
                        //strTotlChblWght =       (Double.parseDouble(strPCC) * Double.parseDouble(strRakeSize)) + "";                                           
                    logger.info("WagonNumber:Before"+siWgonNumb+":"); 
                    String strFlag  =   "F";
                       // System.out.println("siWgonNumb:"+siWgonNumb+":strRakeSize:"+strRakeSize+":strMinimumRakeSize:"+strMinimumRakeSize+":strMiniRake:"+strMiniRake+":");
                    if(siRKPM.equals("M"))
                    {
                        siWgonNumb  =   strMiniRake;
                        siRKPM      =   "M";
                    }
                    else if(siWgonNumb.equals("") || siWgonNumb.equals("strRakeSize"))
                    {
                        siWgonNumb  =   strRakeSize;
                        siRKPM      =   "R";
                    }                    
                    else if(siWgonNumb.equals("") || siWgonNumb.equals("strMiniRake"))
                    {
                        siWgonNumb  =   strMiniRake;
                        siRKPM      =   "M";
                    }
                    if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strRakeSize))
                    {
                        siRKPM      =   "R";
                        strFlag     =   "RakeSize";
                    }
                    else if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strMinimumRakeSize))
                    {
                        siRKPM      =   "R";
                        strFlag     =   "MinimumRakeSize";
                    }
                    else if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strMiniRake))
                    {
                        siRKPM      =   "M";
                        strFlag     =   "MiniRakeSize";
                    }
                    if(strPCC.equals("1.00"))
                        siWgonNumb  =   "1";
                    strOutput[12][0]+= "@" + strFlag;
                    logger.info("WagonAfter:"+siWgonNumb+":");
                    logger.info("siRKPM:"+siRKPM+":");
                    logger.info("strFlag:"+strFlag+":");
                
                        TQFRGTCALC.setString("F_LOADTYPE",      0, siRKPM.trim());
                        TQFRGTCALC.setString("F_RAKETYPE",      0, siRKPM.trim());
                        TQFRGTCALC.setString("F_NUMB",          0, siWgonNumb.trim());
                        strTotlChblWght =       (Double.parseDouble(strPCC) * Double.parseDouble(siWgonNumb)) + "";
                }
                catch(Exception e)
                {
                    logger.info("There was an exception while creating and using the FOIS."+e);
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(2)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                     return null;
                }
                // Calling Service
                try
                {
                        logger.info("Calling Tuxedo Service TQFRGTCALC ...");
                        TQFRGTCALC.call("N");
                        logger.info("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                    logger.info("Errror In Tuxedo Call.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                     return null;
                }
                // Checking For Any Error from Service
                try
                {
                        erorCode                        = TQFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                    strOutput[2][0] =       erorCode;
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                      return null;
                }
                
                int intRebtTranCont     = 0;
                int intOthrTranCont     = 0;
                int intRebtWgonCont     = 0;
                int intOthrWgonCont     = 0;
                int intSurWgonCont      = 0;
                int intSurTranCont      = 0;
                
                int intOthrPcml =       0;
                int intRebtPcml =       0;
                int intSurPcml  =       0;
                
                String strRebtTranCont  = null;
                String strOthrTranCont  = null;
                String strRebtWgonCont  = null;
                String strOthrWgonCont  = null;
                String strSurWgonCont   = null;
                String strSurTranCont   = null;
                
                String strOthrPcml      = null;
                String strRebtPcml      = null;
                String strSurPcml       = null;
                
                //String strtbl="<table style='width: 300px;margin-top:0;'><tr><th><b>Code</b></th><th       ><b> Amount </b></th><th    ><b>%</b></th></tr>";
                String strtbl="<div class='container'><div class='row'><div class='col col-sm-6'><b>Code</b><hr/></div><div class='col col-sm-3'><b>Amt</b></div><div class='col col-sm-3'><b>%</b></div></div>";
                
                strOutput[9][0]         = TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0");      //surcharge codes String
                strOutput[10][0]        = TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0");      // Other Charge Codes String
                strOutput[11][0]        = TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0");      // Rebate Codes String
                if(!siRKPM.equals("P"))
                {
                        try
                        {
                                strRebtTranCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT5",0,"0");
                                strOthrTranCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                                strRebtWgonCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                                strOthrWgonCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT4",0,"0");
                                strSurWgonCont          = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");
                                strSurTranCont          = TQFRGTCALC.getStringItemDef("F_NUMBRR",0,"0");
                                
                                strOutput[9][0]         = TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0");      //surcharge codes String
                                strOutput[10][0]        = TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0");      // Other Charge Codes String
                                strOutput[11][0]        = TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0");      // Rebate Codes String
                                if(strOutput[9][0].length()>1)
                                        strOutput[9][0]         = strOutput[9][0].substring(0,strOutput[9][0].length()-1);      
                                if(strOutput[10][0].length()>1)
                                        strOutput[10][0]        = strOutput[10][0].substring(0,strOutput[10][0].length()-1);    
                                if(strOutput[11][0].length()>1)
                                        strOutput[11][0]        = strOutput[11][0].substring(0,strOutput[11][0].length()-1);
                            
                         
                                logger.info("strRebtTranCont: " + strRebtTranCont + ":");
                                logger.info("strOthrTranCont: " + strOthrTranCont + ":");
                                logger.info("strRebtWgonCont: " + strRebtWgonCont + ":");
                                logger.info("strOthrWgonCont: " + strOthrWgonCont + ":");
                                logger.info("strSurWgonCont: " + strRebtWgonCont + ":");
                                logger.info("strSurTranCont: " + strOthrWgonCont + ":");
                
                                  }
                        catch(Exception d)
                        {
                                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                                erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                                logger.info("erorCode"+erorCode);
                                logger.info("erorCode"+erorCode);
                                responseXml                     =       "Err:"+erorCode;
                                 return null;
                        }
                        
                        if(strRebtTranCont == null || strRebtTranCont.equals("0") || strRebtTranCont.equals(""))
                        {
                                strRebtTranCont         = "0";
                                 logger.info("No Rows found for Rebate Train Load");
                        }
                        if(strOthrTranCont == null || strOthrTranCont.equals("0") || strOthrTranCont.equals(""))
                        {
                                strOthrTranCont         = "0";
                                logger.info("No Rows found for Other charges Train Load");
                        }
                        if(strRebtWgonCont == null || strRebtWgonCont.equals("0") || strRebtWgonCont.equals(""))
                        {
                                strRebtWgonCont         = "0";
                                logger.info("No Rows found for Rebate Wagon Load.");
                        }
                        if(strOthrWgonCont == null || strOthrWgonCont.equals("0") || strOthrWgonCont.equals(""))
                        {
                                strOthrWgonCont         = "0";
                                 logger.info("No Rows found for Other Charges Wagon Load.");
                        }
                        if(strSurWgonCont == null || strSurWgonCont.equals("0") || strSurWgonCont.equals(""))
                        {
                                strSurWgonCont          = "0";
                                logger.info("No Rows found for Sur Charge Wagon Count");
                        }
                        if(strSurTranCont == null || strSurTranCont.equals("0") || strSurTranCont.equals(""))
                        {
                                strSurTranCont          = "0";
                                logger.info("No Rows found for Sur Charge Train Count");
                        }
                        intRebtTranCont = new Integer(strRebtTranCont.trim()).intValue();
                        intOthrTranCont = new Integer(strOthrTranCont.trim()).intValue();
                        intRebtWgonCont = new Integer(strRebtWgonCont.trim()).intValue();
                        intOthrWgonCont = new Integer(strOthrWgonCont.trim()).intValue();
                        
                        intSurWgonCont  = new Integer(strSurWgonCont.trim()).intValue();
                        intSurTranCont  = new Integer(strSurTranCont.trim()).intValue();
                        try
                        {
                                strOutput[3][0] =       "";
                                strOutput[3][1] =       "";
                //                      strOutput[9][0] =       "";
                                if(intOthrWgonCont>0)
                                {
                                        strOutput[3][0] =       strtbl;
                //                              strOutput[3][0] =       "<div align='left'><font size='2px'>Wagon Load Other Charges Details</font></div>" + strtbl;
                                        strOutput[3][1] =       "Wagon Load Other Charges Details";
                                        //<button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                  
                                }
                                   for(int i=0;i<intOthrWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD", i,      "0").trim();
                                        logger.info("In Other Wagon Count:"+i+":");
                                    strOutput[3][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                                        //strOutput[3][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[9][0]+= strDesc + ", ";
                                }
                                if(!strOutput[3][0].equals(""))
                                        strOutput[3][0] +=      "</div>";

                                strOutput[5][0] =       "";
                                strOutput[5][1] =       "";
                                if(intOthrTranCont>0)
                                {
                                        strOutput[5][0] =       strtbl;
                //                              strOutput[5][0] =       "<div align='left'><font size='2px'>Train Load Other Charges Details</font></div>" + strtbl;
                                        strOutput[5][1] =       "Train Load Other Charges Details";
                                        //"<font color='black'>Train Load Other Charges Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                  
                                }
                                for(int i=0;i<intOthrTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_ORDRBY9",              i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_FUNCDESC",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_ORDRBY10",             i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCLTD", i,      "0").trim();
                                        logger.info("In Other Train Count:"+i+":");
                                        strOutput[5][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                                        //                   strOutput[5][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[9][0].indexOf(strDesc) == -1)
                //                                      strOutput[9][0]+= strDesc + ", ";
                                 }
                                if(!strOutput[5][0].equals(""))
                                {
                                        strOutput[5][0] +=      "</div>";                     
                //                              strOutput[9][0] =       strOutput[9][0].substring(0,strOutput[9][0].length()-2);
                                }
                                
                                strOutput[4][0] =       "";
                                strOutput[4][1] =       "";
                //                      strOutput[10][0]        =       "";
                                if(intRebtWgonCont>0)
                                {
                                        strOutput[4][0] =       strtbl;
                                        //strOutput[4][0]       =       "<div align='left'><font size='2px'>Wagon Load Rebate Details</font></div>" + strtbl;
                                        strOutput[4][1] =       "Wagon Load Rebate Details";
                                        //"<font color='black'>Wagon Load Rebate Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                 
                                }
                                String strRebtData  =   "";
                                double   prcn    =   0.0;
                                HashMap<String, String> map1 = new HashMap<>();
                                HashMap<String, String> map3 = new HashMap<>();
                            
                                 for(int i=0;i<intRebtWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();
                                        logger.info("In Rebate Wagon Count:"+i+":");
                                        strOutput[4][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
//                                        strOutput[4][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[10][0]+= strDesc + ", ";
                                        strRebtData += strDesc+"-"+strAmnt+"@";
                                        prcn += Double.parseDouble(strPrcn);
                                      strPrcnW=strPrcn;
                                    map1.put(strDesc, strAmnt);
                                    map3.put(strDesc, "");
                                  }
                            if(!strRebtData.equals(""))
                                strRebtData =   strRebtData.substring(0,strRebtData.length()-1);
                                logger.info("strRebtWLData:"+strRebtData);
                                strOutput[0][17] = strRebtData; //Not using it
                            
                                strOutput[0][18] = Math.round(prcn)+"";
                                logger.info("Wagon Load Rebate Percentage:"+strOutput[0][18]);
                                                                
                                strOutput[6][0] =       "";
                                strOutput[6][1] =       "";
                                if(intRebtTranCont>0)
                                {
                                        strOutput[6][0] =       strtbl;
                //                              strOutput[6][0] =       "<div align='left'><font size='2px'>Train Load Rebate Details</font></div>" + strtbl;
                                        strOutput[6][1] =       "Train Load Rebate Details";
                                        //"<font color='black'>Train Load Rebate Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                 
                                }
                                strRebtData +="#";
                                double   prcnT    =   0.0;
                                HashMap<String, String> map2 = new HashMap<>();
                            
                                  for(int i=0;i<intRebtTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_REBKINVCNUMB",         i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_REBKPAIDTYPE",         i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_REBKSTTNFROM",         i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBKSTTNTO",           i,      "0").trim();
                                        logger.info("In Rebate Train Count:"+i+":");
                                        strOutput[6][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
//                        strOutput[6][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[10][0].indexOf(strDesc) == -1)
                //                                      strOutput[10][0]+= strDesc + ", ";
                                        prcnT   += Double.parseDouble(strPrcn);
                                    strRebtData += strDesc+"-"+strAmnt+"@";
                                    map2.put(strDesc, strAmnt);
                                    map3.put(strDesc, "");
                                        strPrcnT    =   strPrcn;
                                    }
                                strOutput[1][18] = Math.round(prcnT)+"";
                                logger.info("Wagon Load Rebate Percentage:"+strOutput[1][18]);
                            
                                strRebtData =   strRebtData.substring(0,strRebtData.length()-1);
                                logger.info("strRebtWLTLData:"+strRebtData);
                                strOutput[0][17] = strRebtData;      //Not using it
                            
                            String rbtlist  =   "";
                            for (String key : map3.keySet()) {
                                String map1value = map1.get(key);
                                if(map1value == null)
                                    map1value   =   "--";
                                else
                                    map1value   =   "Rs. "+map1value+" /T";
                                String map2value = map2.get(key);
                                if(map2value == null)
                                    map2value   =   "--";
                                else
                                    map2value   =   "Rs. "+map2value+" /T";
                                rbtlist += key +"@"+ map1value+ "@" + map2value+"#";
                            }                            
                            if(!rbtlist.equals(""))
                                rbtlist =   rbtlist.substring(0,rbtlist.length()-1);
                            strOutput[0][17] = rbtlist;
                            logger.info("Final Rebate List:"+strOutput[0][17]);
                                if(!strOutput[6][0].equals(""))
                                {
                                        strOutput[6][0] +=      "</table>";
                //                              strOutput[10][0]        =       strOutput[10][0].substring(0,strOutput[10][0].length()-2);
                                }
                                
                                strOutput[7][0] =       "";
                                strOutput[7][1] =       "";
                //                      strOutput[11][0]=       "";
                                if(intSurWgonCont>0)
                                {
                                        strOutput[7][0] =       strtbl;
                //                              strOutput[7][0] =       "<div align='left'><font size='2px'>Surcharge Wagon Load Details</font></div>" + strtbl;
                                        strOutput[7][1] =      "Surcharge Wagon Load Details";
                                        //<font color='black'>Surcharge Wagon Load Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";
                                }
                              for(int i=0;i<intSurWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",               i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",             i,      "0").trim();
                                        logger.info("In Surcharge Wagon Count:"+i+":");
                                    strOutput[7][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                //                        strOutput[7][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[11][0]+= strDesc + ", ";
                                    }
                                if(!strOutput[7][0].equals(""))
                                        strOutput[7][0] +=      "</table>";

                                strOutput[8][0] =       "";
                                strOutput[8][1] =       "";
                                if(intSurTranCont>0)
                                {
                                        strOutput[8][0] =       strtbl;
                //                              strOutput[8][0] =       "<div align='left'><font size='2px'>Surcharge Train Load Details</font></div>" + strtbl;
                                        strOutput[8][1] =      "Surcharge Train Load Details";
                                        //"<font color='black'>Surcharge Train Load Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                      
                                }
                            
                                 for(int i=0;i<intSurTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SSPNXPRYTIME", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_STAKLOCN",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_STBLLINENUMB", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_STBLYRDGFLAG", i,      "0").trim();
                                        logger.info("In Surcharge Train Count:"+i+":");
                                    strOutput[8][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
//                       strOutput[8][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[11][0].indexOf(strDesc) == -1)
                //                                      strOutput[11][0]+= strDesc + ", ";
                                     }
                               if(!strOutput[8][0].equals(""))
                                {
                                        strOutput[8][0] +=      "</table>";
                //                              strOutput[11][0]        =       strOutput[11][0].substring(0,strOutput[11][0].length()-2);
                                }
                        }
                        catch(Exception d)
                        {
                            logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                            erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                            logger.info("erorCode"+erorCode);
                            logger.info("erorCode"+erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            return null;
                        }                      
                    
                }
                else if(siRKPM.equals("P"))
                {
                        try
                        {
                                strOthrPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                                strRebtPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                                strSurPcml              = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");
                                
                                logger.info("strOthrPcml: " + strOthrPcml);
                                logger.info("strRebtPcml: " + strRebtPcml);
                                logger.info("strSurPcml: " + strSurPcml);
                              
                        }
                        catch(Exception d)
                        {
                                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                                erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                                logger.info("erorCode"+erorCode);
                                logger.info("erorCode"+erorCode);
                                responseXml                     =       "Err:"+erorCode;
                                return null;
                        }
                        
                        if(strOthrPcml == null || strOthrPcml.equals("0") || strOthrPcml.equals(""))
                        {
                                strOthrPcml             = "0";
                                  logger.info("No Rows found for other charges piecemeal");
                        }
                        if(strRebtPcml == null || strRebtPcml.equals("0") || strRebtPcml.equals(""))
                        {
                                strRebtPcml             = "0";
                                logger.info("No Rows found for rebate piecemeal");
                        }
                        if(strSurPcml == null || strSurPcml.equals("0") || strSurPcml.equals(""))
                        {
                                strSurPcml              = "0";
                                logger.info("No Rows found for surchargee piecemeal");
                        }
                        intOthrPcml     = new Integer(strOthrPcml.trim()).intValue();
                        intRebtPcml     = new Integer(strRebtPcml.trim()).intValue();
                        intSurPcml      = new Integer(strSurPcml.trim()).intValue();
                        
                        try
                        {
                                strOutput[3][0] =       "";
                //                      strOutput[9][0] =       "";
                                if(intOthrPcml>0)
                                        strOutput[3][0] =       "<div align='left'><font size='2px'>Wagon Load Other Charges Details</font></div>" + strtbl;
                                for(int i=0;i<intOthrPcml;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD",         i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD",         i,      "0").trim();
                                        logger.info("In Other Charges Picemeal Count:"+i+":");
                                    strOutput[3][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
//                        strOutput[3][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[9][0] += strDesc + ", ";

                                }
                                if(!strOutput[3][0].equals(""))
                                {
                //                        strOutput[3][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[9][0] =       strOutput[9][0].substring(0,strOutput[9][0].length()-2);
                                }
                                strOutput[4][0] =       "";
                //                      strOutput[10][0]=       "";
                                if(intRebtPcml>0)
                                        strOutput[4][0] =       "<div align='left'><font size='2px'>Wagon Load Rebate Details</font></div>" + strtbl;
                                String rbtlist  =   "";
                                double  prcnW   =   0.0;
                                for(int i=0;i<intRebtPcml;i++)
                                {
                                    String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                                    String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();
                                    strOutput[4][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
       //  strOutput[4][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                                        //logger.info("In Rebate Picemeal Count:"+i+":");
                                    rbtlist += strDesc +"@Rs. "+ strAmnt+ " /T@" + "#";
                                    prcnW   += Double.parseDouble(strPrcn);
                                    }
                                    strOutput[0][18] = Math.round(prcnW)+"";
                                    logger.info("Wagon Load Rebate Percentage:"+strOutput[0][18]);
                            
                            if(!rbtlist.equals(""))
                                rbtlist =   rbtlist.substring(0,rbtlist.length()-1);
                            strOutput[0][17] = rbtlist;
                            logger.info("Final Wagon Only Rebate List:"+strOutput[0][17]);
                                if(!strOutput[4][0].equals(""))
                                {
                 //                       strOutput[4][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[10][0]        =       strOutput[10][0].substring(0,strOutput[10][0].length()-2);
                                }
                                
                                strOutput[7][0] =       "";
                //                      strOutput[11][0]        =       "";
                                if(intSurPcml>0)
                                        strOutput[7][0] =       "<div align='left'><font size='2px'>Wagon Load Surcharge Details</font></div>" + strtbl;

                                 for(int i=0;i<intSurPcml;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",               i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",             i,      "0").trim();
                                    strOutput[7][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
//                        strOutput[7][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                                        logger.info("In Surcharge Picemeal Count:"+i+":");
                //                              strOutput[11][0]        += strDesc + ", ";

                                   }
                                if(!strOutput[7][0].equals(""))
                                {
                //                        strOutput[7][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[11][0]        =       strOutput[11][0].substring(0,strOutput[11][0].length()-2);
                                }
                        }
                        catch(Exception d)
                        {
                            logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                            erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                            logger.info("erorCode"+erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            return null;
                        }
                }

                try
                {
                        //Train Load
                        if(!siRKPM.equals("P"))
                        {
                                strOutput[1][0] = strTotlChblWght;                                                                                                                              //Total Chbl Weight
                                strOutput[1][1] = TQFRGTCALC.getStringItemDef("F_RATECLSS",             1,      "0").trim();                    //si_ClssTL;
                                strOutput[1][2] = TQFRGTCALC.getStringItemDef("F_FRGT",                 1,      "0").trim();                    //Basic Freight
                                strOutput[1][3] = TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             1,      "0").trim();                    //Surcharg
                                strOutput[1][4] = TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           1,      "0").trim();                    //ntr
                                strOutput[1][5] = TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       1,      "0").trim();                    //other charge
                                strOutput[1][6] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           1,      "0").trim();                    //rebate
                                strOutput[1][7] = TQFRGTCALC.getStringItemDef("F_TOTLAMNTCLTD",         0,      "0").trim();                    //si_FrgtTL;                                                                                                                                    //Freight Rate
                                strOutput[1][8] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         1,      "0").trim();                    //      total freight
                                strOutput[1][9] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       1,      "0").trim();                    //Other Charges
                                strOutput[1][10]= TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             1,      "0").trim();                    //Other Rebate
                                strOutput[1][11]= TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       1,      "0").trim();                    //Final Freight
                                strOutput[1][12]= TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             1,      "0").trim();                    //Service Tax
                                strOutput[1][13]= TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         1,      "0").trim().replace(".00","");                    //Final Freight + Service Tax
                                strOutput[1][14]= TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS2",         1,      "0").trim();                    //Final Freight + Service Tax IN WORDS
                                strOutput[1][15]= TQFRGTCALC.getStringItemDef("F_CDFDRMRK",             1,      "0").trim();                    //Rebate Remarks
                                logger.info(":strTotlChblWght:"+strTotlChblWght+"::strOutput[1][6]:"+strOutput[1][6]+":"+strOutput[1][10]);
                            if(!strOutput[1][6].equals("0.00") && !strOutput[1][6].equals(""))
                                strOutput[1][16] =       Math.round((Double.parseDouble(strTotlChblWght) * Double.parseDouble(strOutput[1][6])) + Double.parseDouble(strOutput[1][10])) + "";
                            if(strOutput[1][15].indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                            {
                                logger.info("In TEFD block");
                                strOutput[1][15]    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
                                //strTEFDT            =  (Double.parseDouble(strOutput[1][13].replace(",",""))/Double.parseDouble(strOutput[1][2].replace(",",""))) * Double.parseDouble(strRateTL.replace(",","")) + "";
                               // strOutput[1][16]    =  Math.round(Double.parseDouble(strTEFDT) - Double.parseDouble(strOutput[1][13].replace(",",""))) + "";
                                //strOutput[1][18]    =  Math.round(((Double.parseDouble(strOutput[1][16].replace(",",""))/Double.parseDouble(strTEFDT)) * 100 ))+ "";
                                strOutput[1][16]     =  Math.round(Double.parseDouble(strOutput[1][13].replace(",","")) * (Double.parseDouble(strPrcnT)/100)) +"";
                                strOutput[1][18]= strPrcnT.replace(".00", "");
                            }
                            
                            logger.info("train load in words:"+strOutput[1][14]);
                            logger.info("Rebate Amount for Train Load:"+strOutput[1][16]);
                            logger.info("Percentage Rebate Amount for Train Load:"+strOutput[1][18]);
                        }
                        //Wagon Load
                        strOutput[0][0] = strTotlChblWght;                                                                                                                              //Total Chbl Weight
                        strOutput[0][1] = TQFRGTCALC.getStringItemDef("F_RATECLSS",             0,      "0").trim();                    // si_ClssWL;
                        strOutput[0][2] = TQFRGTCALC.getStringItemDef("F_FRGT",                 0,      "0").trim();                    //Basic Freight
                        strOutput[0][3] = TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             0,      "0").trim();                    //Surcharg
                        strOutput[0][4] = TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           0,      "0").trim();                    //ntr
                        strOutput[0][5] = TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       0,      "0").trim();                    //other charge
                        strOutput[0][6] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           0,      "0").trim();                    //rebate
                        strOutput[0][7] = TQFRGTCALC.getStringItemDef("F_AMNTPYBL",             0,      "0").trim();                    //si_FrgtWL;                                                                                                                                    //Freight Rate
                        strOutput[0][8] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         0,      "0").trim();                    //total freight
                        strOutput[0][9] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       0,      "0").trim();                    //Other Charges
                        strOutput[0][10]= TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             0,      "0").trim();                    //Other Rebate
                        strOutput[0][11]= TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       0,      "0").trim();                    //Final Freight
                        strOutput[0][12]= TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             0,      "0").trim();                    //Service Tax
                        strOutput[0][13]= TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         0,      "0").trim().replace(".00","");                    //Final Freight + Service Tax
                        strOutput[0][14]= TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS1",         0,      "0").trim();                    //Final Freight + Service Tax IN WORDS
                        strOutput[0][15]= TQFRGTCALC.getStringItemDef("F_CDFDRMRK",             0,      "0").trim();                    //Rebate Remarks
                        
                        if(!strOutput[0][6].equals("0.00") && !strOutput[0][6].equals(""))
                            strOutput[0][16] =      Math.round(Double.parseDouble(strTotlChblWght) * Double.parseDouble(strOutput[0][6])) + Double.parseDouble(strOutput[0][10]) + "";
                            
                        if(strOutput[0][15].indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                        {
                            logger.info("In Traditional Empty Flow");
                            strOutput[0][15]    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
                            //strTEFDW            =  (Double.parseDouble(strOutput[0][13].replace(",",""))/Double.parseDouble(strOutput[0][2].replace(",",""))) * Double.parseDouble(strRateWL.replace(",","")) + "";  //Full freight without rebate
                           // strOutput[0][16]    =  Math.round(Double.parseDouble(strTEFDW) - Double.parseDouble(strOutput[0][13].replace(",",""))) + "";                            // Discounted amount
                            //strOutput[0][18]    =  Math.round(((Double.parseDouble(strOutput[0][16].replace(",",""))/Double.parseDouble(strTEFDW)) * 100 ))+ "";                    // Rebate Prcn
                            //strOutput[0][17]    =  "TRADITIONAL EMPTY FLOW DIRECTION (TEFD)"+"@Rs. "+truncateDecimal(((Double.parseDouble(strRateWL.replace(",",""))-Double.parseDouble(strOutput[0][2].replace(",","")))),2) + " /T";  //Rebate block string
                            strOutput[0][16]     =  Math.round(Double.parseDouble(strOutput[0][13].replace(",","")) * (Double.parseDouble(strPrcnW)/100)) +"";
                            strOutput[0][18]    = strPrcnW.replace(".00", "");
                            strOutput[0][17]    = "TRADITIONAL EMPTY FLOW DIRECTION (TEFD)"+"@Rs. "+strOutput[0][6] + " /T";  //Rebate block string
                            
                            if(!siRKPM.equals("P"))
                            {
                               // strOutput[0][17]    =  "TRADITIONAL EMPTY FLOW DIRECTION (TEFD)"+"@Rs. "+truncateDecimal(Double.parseDouble(strRateWL.replace(",",""))-Double.parseDouble(strOutput[0][2].replace(",","")),2)+" /T@Rs. "+truncateDecimal(Double.parseDouble(strRateTL.replace(",",""))-Double.parseDouble(strOutput[1][2].replace(",","")),2) + " /T";
                               strOutput[0][17]    =  "TRADITIONAL EMPTY FLOW DIRECTION (TEFD)"+"@Rs. "+strOutput[0][6]+" /T@Rs. "+strOutput[1][6] + " /T";
                                        
                               
                            }
                        }
                        logger.info("wagon load in words:"+strOutput[0][14]);
                        logger.info("Rebate Amount for Wagon Load:"+strOutput[0][16]);
                    logger.info("Percentage Rebate wagon load:"+strOutput[0][18]);
                        logger.info("TEFD block:"+strOutput[0][17]);

                }
                catch(Exception e)
                {
                    logger.info("Unable to write to buffer : " + e.toString());
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    return null;
                }
                
                try
                {
                        TQFRGTCALC.endSession();
                }
                catch(Exception e)
                {
                    logger.info("Error In End Session:" + e.toString());
                    logger.info("There was an exception while creating and using the FOIS."+e);
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(2)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    return null;
                }
                finally
                {
                try
                {
                        TQFRGTCALC=null;
                }
                catch(Exception e)
                {
                    logger.info("Errror In Finally.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    return null;
                }
                }
                //req.setAttribute("Details",strOutput);
                logger.info("SUCCESSFULL");

            }
            catch(Exception e)
            {
                 //logger.info( e.printStackTrace());
                logger.info("In catch");
                    responseXml                     =       "Err:SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME";
                    return null;
            }
            return strOutput;
    }
    public static JSONObject getDtlsjson(String siSttnFrom,String siSttnTo,String siCmdt,String siWgonType,String siUserType,String siRKPM,String siWgonNumb
                                     )
    {
        //NDC.push("CalculateExpectedFreight");
        logger.info("Entering FSH_FrgtCalc..getDtlsjson..");
        //siCmdt  =   StringEscapeUtils.unescapeJava(siCmdt);
        logger.info("siWgonType:"+siWgonType); 
        String strFromsttn  =   siSttnFrom;
        String strTosttn  =   siSttnTo;
        String strPrcnT =   "";
        String strPrcnW =   "";
        try
        {
            if(siSttnFrom.indexOf("-") != -1)
                    siSttnFrom                              =       siSttnFrom.substring(siSttnFrom.lastIndexOf("-")+1,siSttnFrom.lastIndexOf("(")).trim();
            if(siSttnTo.indexOf("-") != -1)
                    siSttnTo                                =       siSttnTo.substring(siSttnTo.lastIndexOf("-")+1,siSttnTo.lastIndexOf("(")).trim();
       //     if(siCmdt.indexOf("-") != -1)
         //           siCmdt                                  =       siCmdt.substring(0,siCmdt.lastIndexOf("-")).trim();
         if(siWgonType.indexOf("-") != -1)
                 siWgonType                              =       siWgonType.substring(0,siWgonType.indexOf("-")).trim();
         /*
            if(si_optWgontype.equals("T"))
            {
                if(siWgonType.indexOf("-") != -1)
                        siWgonType                              =       siWgonType.substring(0,siWgonType.indexOf("-")).trim();
                else if(si_selWgonCtgy != null)
                    if(!si_selWgonCtgy.equals(""))
                        siWgonType  =   si_selWgonCtgy;
            }
            else
                siWgonType  =   si_selWgonCtgy;
    */
        }
        catch(Exception e){}
        logger.info("siSttnFrom:"+siSttnFrom);               
        logger.info("siSttnTo:"+siSttnTo);           
        logger.info("siWgonType:"+siWgonType);               
        logger.info("siCmdt:"+siCmdt);               
        logger.info("siRKPM:"+siRKPM);               
        logger.info("siWgonNumb:"+siWgonNumb);
        
        String responseXml      = "";        
        JSONObject response =   new  JSONObject();
        String strOutput[][]    = new String[14][19];     // 0th row is for wagon load,1st row is for train load and 3rd row is for error, after that for other charges , rebate charges & surcharge details for wagon & train load in case of rake/minirake and wagon load other charges, rebate and surcharge in case of piecemeal
                                                         // 12th row is having xml response from prefrgtcalc on its first column
        for(int i=0;i<14;i++)
                for(int j=0;j<18;j++)
                        strOutput[i][j]="";
        
        String strCmdtClssTL=       "";
        String strCmdtClssWL=        "";
        String strRateTL    =        "";
        String strRateWL    =        "";
        String strRoutInfo  =        "";
        String strViaDesc   =        "";
        String strCCInfo    =        "";
        String strDist      =        "";
        String strRakeSize  =        "";
        String strPCC       =        "";
        String strETA       =       "";
        String strMiniRake  =        "";
        String strTotlChblWght  =    "";
        String strMinimumRakeSize   =   "";
        
            try
            {

                Date date = Calendar.getInstance().getTime();

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = formatter.format(date);
                logger.info("Today : " + strDate);


         //       if(!GG_Validate.chkName(siCmdt)) return null;
         //       if(!GG_Validate.chkName(siSttnFrom)) return null;
         //       if(!GG_Validate.chkName(siSttnTo)) return null;

                    FOISTuxedo TQPREFRGTCALC        = null;
                    try
                    {
                            logger.info("Calling Tuxedo");
                            TQPREFRGTCALC = new FOISTuxedo();
                    }
                    catch (Exception ne)
                    {
                            logger.info("Unable to get the Client Object");
                            return null;
                    }
                    
                    try
                    {
                            TQPREFRGTCALC.tuxInit("TQPREFRGTCALC");

                            logger.info("strDate:"+strDate+":");
                            logger.info("siSttnFrom:"+siSttnFrom+":");
                            logger.info("siSttnTo:"+siSttnTo+":");
                            logger.info("siCmdt:"+siCmdt+":");
                            logger.info("siWgonType:"+siWgonType+":");
                            logger.info("siUserType:"+siUserType+":");
                        
                            TQPREFRGTCALC.setString("F_DATE",       0, strDate.trim());//java.time.LocalTime.now()+"");
                            TQPREFRGTCALC.setString("F_STTNFROM",   0, siSttnFrom.trim());
                            TQPREFRGTCALC.setString("F_STTNTO",     0, siSttnTo.trim());
                            TQPREFRGTCALC.setString("F_CMDT",       0, siCmdt.trim());
                            TQPREFRGTCALC.setString("F_WGONTYPE",   0, siWgonType.trim());
                            TQPREFRGTCALC.setString("F_USERID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_CLNTID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_FLAG",       0, siUserType);
                    }
                    catch(Exception e)
                    {
                            //e.printStackTrace();
                            logger.info("Unable to buffers");
                            return null;
                    }
    
                    String erorCode                 = null;
                    
                    try
                    {
                            logger.info("Calling Tuxedo Service TQPREFRGTCALC ...");
                            TQPREFRGTCALC.call("N");
                            logger.info("CALL COMPLETED !");
                    }
                    catch(Exception e)
                    {                               
                            //e.printStackTrace();
                            logger.info("IN Freight Calculator Service is currently unavailable.Please try after some time." + e.toString());
                            erorCode = "Freight Calculator Service is currently unavailable.Please try after some time.";
                            return null;
                    }
                    
                    try
                    {
                            logger.info("BeforeErrorCode: " + erorCode+":");
                            erorCode                = TQPREFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                            logger.info("AfterErrorCode: " + erorCode+":");
                    }
                    catch(Exception e)
                    {
                            // F_ERORNO is not set by Developer, So, it is not an Error
                    }
                    if(erorCode != null && (!erorCode.equals("")))
                    {
                            logger.info("ErrorCode: " + erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            response.put("Err",erorCode);
                            return response;
                    }
                    strCmdtClssTL   =        TQPREFRGTCALC.getStringItemDef("F_RATECLSS",   0,      "0").trim();
                    strCmdtClssWL   =        TQPREFRGTCALC.getStringItemDef("F_PRTYCLSS",   0,      "0").trim();
                    strRateTL       =        TQPREFRGTCALC.getStringItemDef("F_RATE",       0,      "0").trim();
                    strRateWL       =        TQPREFRGTCALC.getStringItemDef("F_FRGTRATE",   0,      "0").trim();
                    strRoutInfo     =        TQPREFRGTCALC.getStringItemDef("F_ROUTID",     0,      "0").trim();
                    strViaDesc      =        TQPREFRGTCALC.getStringItemDef("F_VIA",        0,      "0").trim();
                    strCCInfo       =        TQPREFRGTCALC.getStringItemDef("F_ROUTNUMB",   0,      "0").trim();
                    strDist         =        TQPREFRGTCALC.getStringItemDef("F_CHBLDIST",   0,      "0").trim();
                    strRakeSize     =        TQPREFRGTCALC.getStringItemDef("F_NUMBWGON",   0,      "0").trim();
                    strPCC          =        TQPREFRGTCALC.getStringItemDef("F_CHBLWGHT",   0,      "0").trim();
                    strETA          =        TQPREFRGTCALC.getStringItemDef("F_CALLTIME",   0,      "0").trim();
                    strMiniRake     =        TQPREFRGTCALC.getStringItemDef("F_TOTLWGON",   0,      "0").trim();  
                strMinimumRakeSize=        TQPREFRGTCALC.getStringItemDef("F_ORDRBY1",   0,      "0").trim();                                  
                    
                    if(strRoutInfo.endsWith("R"))
                            strRoutInfo     =       "Rationalized";
                    else if(strRoutInfo.endsWith("S"))
                            strRoutInfo     =       "Shortest";
                    strETA  =   Math.round(Double.parseDouble(strETA))+"";
                    responseXml             =       strCmdtClssTL + "@" +strCmdtClssWL + "@" +strRateTL + "@" +strRateWL + "@" +strRoutInfo + "@" +strViaDesc +"@" +strCCInfo +"@" + strDist + "@" + strRakeSize + "@" + strPCC + "@" + strMiniRake + "@" + strETA;
                    logger.info("Response after TQPrefrgtcalc call:"+responseXml);
                    response.put("CmdtClssTL",strCmdtClssTL);
                    response.put("CmdtClssWL",strCmdtClssWL);
                    response.put("RateTL",strRateTL);
                    response.put("RateWL",strRateWL);
                    response.put("RoutInfo",strRoutInfo);
                    response.put("ViaDesc",strViaDesc);
                    response.put("CCInfo",strCCInfo);
                    response.put("Dist",strDist);
                    response.put("RakeSize",strRakeSize);
                    response.put("PCC",strPCC);
                    response.put("MiniRake",strMiniRake);       
                    response.put("ETA",Math.round(Double.parseDouble(strETA)));    
                    response.put("MiniRake",strMiniRake);
                    response.put("minmRake",strMinimumRakeSize);

                String startRowCount   = null;
                int start              = 0;
                try
                {
                        startRowCount          = TQPREFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                }
                catch(Exception e){}
                logger.info("startRowCount:"+ startRowCount);
                if(startRowCount.equals("0") || startRowCount.equals(""))
                        logger.info("ErrorCode: " + "No data found");
                else
                        start = new Integer(startRowCount.trim()).intValue();
                String strAltSttnFrom="";
                String strAltSttnTo="";
                String strAltRout="[";
                for(int k=0; k<start; k++)
                {
                    try
                        {
                                strAltSttnFrom           = TQPREFRGTCALC.getStringItemDef("F_ORIGSTTNFROM",            k,      "0").trim();
                                strAltSttnTo             = TQPREFRGTCALC.getStringItemDef("F_ORIGDSTNSTTN",            k,      "0").trim();

                                if(!(strAltSttnFrom.equals(strFromsttn) && strAltSttnTo.equals(strTosttn)))
                                    if(k==0)
                                            strAltRout+="{\"SttnFrom\":\""+strAltSttnFrom+"\",\"SttnTo\":\""+strAltSttnTo+"\"}";
                                    else
                                            strAltRout+=",{\"SttnFrom\":\""+strAltSttnFrom+"\",\"SttnTo\":\""+strAltSttnTo+"\"}";                                         
                        }
                        catch(Exception e)
                        {
                                logger.info("Error In getting output buffers:"+e.toString());
                        }
                } 
                strAltRout+="]";
                
                strOutput[12][0]    =   responseXml;
                strOutput[13][0]    =   "{\"Data\":"+strAltRout+"}";   
                logger.info("Alternate Route:"+strOutput[13][0]);                 
                
                logger.info("Entering TQ_FrgtCalc....");
                FOISTuxedo TQFRGTCALC   = null;
                
                try
                {
                    logger.info("Calling Tuxedo");
                    TQFRGTCALC = new FOISTuxedo();
                    logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                    logger.info("Tuxedo Connnection Failed.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                // SETTING BUFFERS VALUES
                try
                {                               
                        logger.info("si_CmdtName:"+siCmdt+":");
                        logger.info("si_SttnFrom:"+siSttnFrom+":");
                        logger.info("si_SttnTo:"+siSttnTo+":");
                        logger.info("siWgonNumb:"+siWgonNumb);
                        logger.info("strRakeSize:"+strRakeSize);
                    
                        TQFRGTCALC.tuxInit("TQFRGTCALC");
                        // Passing Input
                        TQFRGTCALC.setString("F_DATE",          0, strDate);
                        TQFRGTCALC.setString("F_STTNFROM",      0, siSttnFrom.trim());
                        TQFRGTCALC.setString("F_STTNTO",        0, siSttnTo.trim());
                        TQFRGTCALC.setString("F_VIA",           0, strRoutInfo.trim());
                        TQFRGTCALC.setString("F_USERID",        0, "CRIS1");
                        TQFRGTCALC.setString("F_CLNTID",        0, "CRIS1");
                        TQFRGTCALC.setString("F_DIST",          0, strDist.trim());
                        TQFRGTCALC.setString("F_PAIDTYPE",      0, "");
                        TQFRGTCALC.setString("F_RISKRATE",      0, "");
                        //TQFRGTCALC.setString("F_LOADTYPE",      0, siRKPM.trim());
                        TQFRGTCALC.setString("F_CMDTDESC",      0, "");
                        TQFRGTCALC.setString("F_TRFCTYPE",      0, "G");
                        //TQFRGTCALC.setString("F_RAKETYPE",      0, siRKPM.trim());
                        TQFRGTCALC.setString("F_RATE",          0, "C");
                        TQFRGTCALC.setString("F_WGONTYPE",      0, siWgonType.trim());
                        TQFRGTCALC.setString("F_CMDTCODE",      0, siCmdt.trim());
                        TQFRGTCALC.setString("F_PMBLWGHT",      0, strPCC.trim());
                    
                    logger.info("WagonNumber:Before"+siWgonNumb+":"); 
                    String strFlag  =   "F";

                    if(siWgonNumb.equals("")||siWgonNumb.equals("0") || siWgonNumb.equals("strRakeSize"))
                    {
                        siWgonNumb  =   strRakeSize;
                        siRKPM      =   "R";
                    }
                    else if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strRakeSize))
                    {
                        siRKPM      =   "R";
                        strFlag     =   "RakeSize";
                    }
                    else if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strMinimumRakeSize))
                    {
                        siRKPM      =   "R";
                        strFlag     =   "MinimumRakeSize";
                    }
                    else if(Integer.parseInt(siWgonNumb) >= Integer.parseInt(strMiniRake))
                    {
                        siRKPM      =   "R";
                        strFlag     =   "MiniRakeSize";
                    }

                    if(strPCC.equals("1.00"))
                        siWgonNumb  =   "1";
                    logger.info("WagonAfter:"+siWgonNumb+":");
                    logger.info("siRKPM:"+siRKPM+":");
                    TQFRGTCALC.setString("F_LOADTYPE",      0, siRKPM.trim());
                    TQFRGTCALC.setString("F_RAKETYPE",      0, siRKPM.trim());
                    TQFRGTCALC.setString("F_NUMB",          0, siWgonNumb.trim());
                    
                    //    TQFRGTCALC.setString("F_NUMB",          0, strRakeSize);                   
                    //    strTotlChblWght =       (Double.parseDouble(strPCC) * Double.parseDouble(strRakeSize)) + "";                                           
                                                                                      
                    strTotlChblWght =       (Double.parseDouble(strPCC) * Double.parseDouble(siWgonNumb)) + "";
                }
                catch(Exception e)
                {
                    logger.info("There was an exception while creating and using the FOIS."+e);
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(2)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                // Calling Service
                try
                {
                        logger.info("Calling Tuxedo Service TQFRGTCALC ...");
                        TQFRGTCALC.call("N");
                        logger.info("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                    logger.info("Errror In Tuxedo Call.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                // Checking For Any Error from Service
                try
                {
                        erorCode                        = TQFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                    strOutput[2][0] =       erorCode;
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                
                int intRebtTranCont     = 0;
                int intOthrTranCont     = 0;
                int intRebtWgonCont     = 0;
                int intOthrWgonCont     = 0;
                int intSurWgonCont      = 0;
                int intSurTranCont      = 0;
                
                int intOthrPcml =       0;
                int intRebtPcml =       0;
                int intSurPcml  =       0;
                
                String strRebtTranCont  = null;
                String strOthrTranCont  = null;
                String strRebtWgonCont  = null;
                String strOthrWgonCont  = null;
                String strSurWgonCont   = null;
                String strSurTranCont   = null;
                
                String strOthrPcml      = null;
                String strRebtPcml      = null;
                String strSurPcml       = null;
                
                //String strtbl="<table style='width: 300px;margin-top:0;'><tr><th      ><b>Code</b></th><th       ><b> Amount </b></th><th    ><b>%</b></th></tr>";
                String strtbl="<div class='container'><div class='row'><div class='col col-sm-6'><b>Code</b><hr/></div><div class='col col-sm-3'><b>Amt</b></div><div class='col col-sm-3'><b>%</b></div></div>";
                
                strOutput[9][0]         = TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0");      //surcharge codes String
                strOutput[10][0]        = TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0");      // Other Charge Codes String
                strOutput[11][0]        = TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0");      // Rebate Codes String
                if(!siRKPM.equals("P"))
                {
                        try
                        {
                                strRebtTranCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT5",0,"0");
                                strOthrTranCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                                strRebtWgonCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                                strOthrWgonCont         = TQFRGTCALC.getStringItemDef("F_ROWCONT4",0,"0");
                                strSurWgonCont          = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");
                                strSurTranCont          = TQFRGTCALC.getStringItemDef("F_NUMBRR",0,"0");
                                
                                strOutput[9][0]         = TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0");      //surcharge codes String
                                strOutput[10][0]        = TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0");      // Other Charge Codes String
                                strOutput[11][0]        = TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0");      // Rebate Codes String
                                if(strOutput[9][0].length()>1)
                                        strOutput[9][0]         = strOutput[9][0].substring(0,strOutput[9][0].length()-1);      
                                if(strOutput[10][0].length()>1)
                                        strOutput[10][0]        = strOutput[10][0].substring(0,strOutput[10][0].length()-1);    
                                if(strOutput[11][0].length()>1)
                                        strOutput[11][0]        = strOutput[11][0].substring(0,strOutput[11][0].length()-1);
                            
                            if(strOutput[9][0].length() > 0)
                                response.put("surchrg",strOutput[9][0].substring(0,strOutput[9][0].length()-1));
                            else
                                response.put("surchrg","");
                            if(strOutput[10][0].length() > 0)          
                                response.put("othrchrg",strOutput[10][0].substring(0,strOutput[10][0].length()-1));  
                            else
                                response.put("othrchrg","");                
                            if(strOutput[11][0].length() > 0)              
                                response.put("rebtchrg",strOutput[11][0].substring(0,strOutput[11][0].length()-1));
                            else
                                response.put("rebtchrg","");

                                logger.info("strRebtTranCont: " + strRebtTranCont + ":");
                                logger.info("strOthrTranCont: " + strOthrTranCont + ":");
                                logger.info("strRebtWgonCont: " + strRebtWgonCont + ":");
                                logger.info("strOthrWgonCont: " + strOthrWgonCont + ":");
                                logger.info("strSurWgonCont: " + strRebtWgonCont + ":");
                                logger.info("strSurTranCont: " + strOthrWgonCont + ":");
                
                                response.put("tranrebtcont",strRebtTranCont);
                                response.put("tranothrcont",strOthrTranCont);
                                response.put("transurcont",strSurTranCont);
                                response.put("wgonrebtcont",strRebtWgonCont);
                                response.put("wgonothrcont",strOthrWgonCont);
                                response.put("wgonsurcont",strSurWgonCont);       
                        }
                        catch(Exception d)
                        {
                                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                                erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                                logger.info("erorCode"+erorCode);
                                logger.info("erorCode"+erorCode);
                                responseXml                     =       "Err:"+erorCode;
                                response.put("Err",erorCode);
                                return response;
                        }
                        
                        if(strRebtTranCont == null || strRebtTranCont.equals("0") || strRebtTranCont.equals(""))
                        {
                                strRebtTranCont         = "0";
                                response.put("tranrebtcont","0");
                                logger.info("No Rows found for Rebate Train Load");
                        }
                        if(strOthrTranCont == null || strOthrTranCont.equals("0") || strOthrTranCont.equals(""))
                        {
                                strOthrTranCont         = "0";
                                response.put("tranothrcont","0");
                                logger.info("No Rows found for Other charges Train Load");
                        }
                        if(strRebtWgonCont == null || strRebtWgonCont.equals("0") || strRebtWgonCont.equals(""))
                        {
                                strRebtWgonCont         = "0";
                                response.put("wgonrebtcont","0");
                                logger.info("No Rows found for Rebate Wagon Load.");
                        }
                        if(strOthrWgonCont == null || strOthrWgonCont.equals("0") || strOthrWgonCont.equals(""))
                        {
                                strOthrWgonCont         = "0";
                                response.put("wgonothrcont","0");
                                logger.info("No Rows found for Other Charges Wagon Load.");
                        }
                        if(strSurWgonCont == null || strSurWgonCont.equals("0") || strSurWgonCont.equals(""))
                        {
                                strSurWgonCont          = "0";
                                response.put("wgonsurcont","0");
                                logger.info("No Rows found for Sur Charge Wagon Count");
                        }
                        if(strSurTranCont == null || strSurTranCont.equals("0") || strSurTranCont.equals(""))
                        {
                                strSurTranCont          = "0";
                                response.put("transurcont","0");
                                logger.info("No Rows found for Sur Charge Train Count");
                        }
                        intRebtTranCont = new Integer(strRebtTranCont.trim()).intValue();
                        intOthrTranCont = new Integer(strOthrTranCont.trim()).intValue();
                        intRebtWgonCont = new Integer(strRebtWgonCont.trim()).intValue();
                        intOthrWgonCont = new Integer(strOthrWgonCont.trim()).intValue();
                        
                        intSurWgonCont  = new Integer(strSurWgonCont.trim()).intValue();
                        intSurTranCont  = new Integer(strSurTranCont.trim()).intValue();
                        try
                        {
                                strOutput[3][0] =       "";
                                strOutput[3][1] =       "";
                //                      strOutput[9][0] =       "";
                                if(intOthrWgonCont>0)
                                {
                                        strOutput[3][0] =       strtbl;
                //                              strOutput[3][0] =       "<div align='left'><font size='2px'>Wagon Load Other Charges Details</font></div>" + strtbl;
                                        strOutput[3][1] =       "Wagon Load Other Charges Details";
                                        //<button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                  
                                }
                                JSONObject OthrWgon =   new  JSONObject();
                                OthrWgon.put("intOthrWgonCont",intOthrWgonCont);
                                for(int i=0;i<intOthrWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD", i,      "0").trim();
                                        logger.info("In Other Wagon Count:"+i+":");
                                    strOutput[3][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                                        //strOutput[3][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[9][0]+= strDesc + ", ";
                                OthrWgon.put("wgonothrcode"+i,strCode);
                                OthrWgon.put("wgonothrdesc"+i,strDesc);
                                OthrWgon.put("wgonothrprcn"+i,strPrcn);
                                OthrWgon.put("wgonothramnt"+i,strAmnt);                                
                                }
                            response.put("OthrWgon",OthrWgon);
                                if(!strOutput[3][0].equals(""))
                                        strOutput[3][0] +=      "</div>";

                                strOutput[5][0] =       "";
                                strOutput[5][1] =       "";
                                if(intOthrTranCont>0)
                                {
                                        strOutput[5][0] =       strtbl;
                //                              strOutput[5][0] =       "<div align='left'><font size='2px'>Train Load Other Charges Details</font></div>" + strtbl;
                                        strOutput[5][1] =       "Train Load Other Charges Details";
                                        //"<font color='black'>Train Load Other Charges Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                  
                                }
                            JSONObject OthrTran =   new  JSONObject();
                            OthrTran.put("intOthrTranCont",intOthrTranCont);
                                for(int i=0;i<intOthrTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_ORDRBY9",              i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_FUNCDESC",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_ORDRBY10",             i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCLTD", i,      "0").trim();
                                        logger.info("In Other Train Count:"+i+":");
                                        strOutput[5][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                                        //                   strOutput[5][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[9][0].indexOf(strDesc) == -1)
                //                                      strOutput[9][0]+= strDesc + ", ";
                                        OthrTran.put("tranothrcode"+i,strCode);
                                        OthrTran.put("tranothrdesc"+i,strDesc);
                                        OthrTran.put("tranothrprcn"+i,strPrcn);
                                        OthrTran.put("tranothramnt"+i,strAmnt);
                                }
                            response.put("OthrTran",OthrTran);
                                if(!strOutput[5][0].equals(""))
                                {
                                        strOutput[5][0] +=      "</div>";                     
                //                              strOutput[9][0] =       strOutput[9][0].substring(0,strOutput[9][0].length()-2);
                                }
                                
                                strOutput[4][0] =       "";
                                strOutput[4][1] =       "";
                //                      strOutput[10][0]        =       "";
                                if(intRebtWgonCont>0)
                                {
                                        strOutput[4][0] =       strtbl;
                                        //strOutput[4][0]       =       "<div align='left'><font size='2px'>Wagon Load Rebate Details</font></div>" + strtbl;
                                        strOutput[4][1] =       "Wagon Load Rebate Details";
                                        //"<font color='black'>Wagon Load Rebate Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                 
                                }
                                String strRebtData  =   "";
                                double   prcn    =   0.0;
                                HashMap<String, String> map1 = new HashMap<>();
                                HashMap<String, String> map3 = new HashMap<>();
                            
                                JSONObject RebtWgon =   new  JSONObject();
                                RebtWgon.put("intRebtWgonCont",intRebtWgonCont);
                                for(int i=0;i<intRebtWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();
                                        logger.info("In Rebate Wagon Count:"+i+":");
                                        strOutput[4][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
    //                                        strOutput[4][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[10][0]+= strDesc + ", ";
                                        strRebtData += strDesc+"-"+strAmnt+"@";
                                        prcn += Double.parseDouble(strPrcn);

                                    map1.put(strDesc, strAmnt);
                                    map3.put(strDesc, "");
                                    RebtWgon.put("wgonrebtcode"+i,strCode);
                                    RebtWgon.put("wgonrebtdesc"+i,strDesc);
                                    RebtWgon.put("wgonrebtprcn"+i,strPrcn);
                                    RebtWgon.put("wgonrebtamnt"+i,strAmnt);
                                    strPrcnW    =   strPrcn;
                                }
                            response.put("RebtWgon",RebtWgon);
                            if(!strRebtData.equals(""))
                                strRebtData =   strRebtData.substring(0,strRebtData.length()-1);
                                logger.info("strRebtWLData:"+strRebtData);
                                strOutput[0][17] = strRebtData; //Not using it
                            
                                strOutput[0][18] = Math.round(prcn)+"";
                                logger.info("Wagon Load Rebate Percentage:"+strOutput[0][18]);
                                                                
                                strOutput[6][0] =       "";
                                strOutput[6][1] =       "";
                                if(intRebtTranCont>0)
                                {
                                        strOutput[6][0] =       strtbl;
                //                              strOutput[6][0] =       "<div align='left'><font size='2px'>Train Load Rebate Details</font></div>" + strtbl;
                                        strOutput[6][1] =       "Train Load Rebate Details";
                                        //"<font color='black'>Train Load Rebate Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                 
                                }
                                strRebtData +="#";
                                double   prcnT    =   0.0;
                                HashMap<String, String> map2 = new HashMap<>();
                            
                                JSONObject RebtTran =   new  JSONObject();
                                RebtTran.put("intRebtTranCont",intRebtTranCont);
                                for(int i=0;i<intRebtTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_REBKINVCNUMB",         i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_REBKPAIDTYPE",         i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_REBKSTTNFROM",         i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBKSTTNTO",           i,      "0").trim();
                                        logger.info("In Rebate Train Count:"+i+":");
                                        strOutput[6][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
    //                        strOutput[6][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[10][0].indexOf(strDesc) == -1)
                //                                      strOutput[10][0]+= strDesc + ", ";
                                        prcnT   += Double.parseDouble(strPrcn);
                                    strRebtData += strDesc+"-"+strAmnt+"@";
                                    map2.put(strDesc, strAmnt);
                                    map3.put(strDesc, "");
                                    RebtTran.put("tranrebtcode"+i,strCode);
                                    RebtTran.put("tranrebtdesc"+i,strDesc);
                                    RebtTran.put("tranrebtprcn"+i,strPrcn);
                                    RebtTran.put("tranrebtamnt"+i,strAmnt);
                                    strPrcnT    =   strPrcn;
                                }
                            response.put("RebtTran",RebtTran);
                                strOutput[1][18] = Math.round(prcnT)+"";
                                logger.info("Wagon Load Rebate Percentage:"+strOutput[1][18]);
                            
                                strRebtData =   strRebtData.substring(0,strRebtData.length()-1);
                                logger.info("strRebtWLTLData:"+strRebtData);
                                strOutput[0][17] = strRebtData;      //Not using it
                            
                            String rbtlist  =   "";
                            for (String key : map3.keySet()) {
                                String map1value = map1.get(key);
                                if(map1value == null)
                                    map1value   =   "--";
                                else
                                    map1value   =   "Rs. "+map1value+" /T";
                                String map2value = map2.get(key);
                                if(map2value == null)
                                    map2value   =   "--";
                                else
                                    map2value   =   "Rs. "+map2value+" /T";
                                rbtlist += key +"@"+ map1value+ "@" + map2value+"#";
                            }                            
                            if(!rbtlist.equals(""))
                                rbtlist =   rbtlist.substring(0,rbtlist.length()-1);
                            strOutput[0][17] = rbtlist;
                            logger.info("Final Rebate List:"+strOutput[0][17]);
                                if(!strOutput[6][0].equals(""))
                                {
                                        strOutput[6][0] +=      "</table>";
                //                              strOutput[10][0]        =       strOutput[10][0].substring(0,strOutput[10][0].length()-2);
                                }
                                
                                strOutput[7][0] =       "";
                                strOutput[7][1] =       "";
                //                      strOutput[11][0]=       "";
                                if(intSurWgonCont>0)
                                {
                                        strOutput[7][0] =       strtbl;
                //                              strOutput[7][0] =       "<div align='left'><font size='2px'>Surcharge Wagon Load Details</font></div>" + strtbl;
                                        strOutput[7][1] =      "Surcharge Wagon Load Details";
                                        //<font color='black'>Surcharge Wagon Load Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";
                                }
                            
                                JSONObject SurWgon =   new  JSONObject();
                                SurWgon.put("intSurWgonCont",intSurWgonCont);
                                for(int i=0;i<intSurWgonCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",               i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",             i,      "0").trim();
                                        logger.info("In Surcharge Wagon Count:"+i+":");
                                    strOutput[7][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
                //                        strOutput[7][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[11][0]+= strDesc + ", ";
                                        SurWgon.put("wgonsurcode"+i,strCode);
                                        SurWgon.put("wgonsurdesc"+i,strDesc);
                                        SurWgon.put("wgonsurprcn"+i,strPrcn);
                                        SurWgon.put("wgonsuramnt"+i,strAmnt);
                                }
                            response.put("SurWgon",SurWgon);
                                if(!strOutput[7][0].equals(""))
                                        strOutput[7][0] +=      "</table>";

                                strOutput[8][0] =       "";
                                strOutput[8][1] =       "";
                                if(intSurTranCont>0)
                                {
                                        strOutput[8][0] =       strtbl;
                //                              strOutput[8][0] =       "<div align='left'><font size='2px'>Surcharge Train Load Details</font></div>" + strtbl;
                                        strOutput[8][1] =      "Surcharge Train Load Details";
                                        //"<font color='black'>Surcharge Train Load Details</font><button type='button' id='close' class='close' onclick='$(&quot;#example&quot;).popover(&quot;hide&quot;);'>&times;</button>";                                      
                                }
                            
                                JSONObject SurTran =   new  JSONObject();
                                SurTran.put("intSurTranCont",intSurTranCont);
                                for(int i=0;i<intSurTranCont;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SSPNXPRYTIME", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_STAKLOCN",             i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_STBLLINENUMB", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_STBLYRDGFLAG", i,      "0").trim();
                                        logger.info("In Surcharge Train Count:"+i+":");
                                    strOutput[8][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
    //                       strOutput[8][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              if(strOutput[11][0].indexOf(strDesc) == -1)
                //                                      strOutput[11][0]+= strDesc + ", ";
                                        SurTran.put("transurcode"+i,strCode);
                                        SurTran.put("transurdesc"+i,strDesc);
                                        SurTran.put("transurprcn"+i,strPrcn);
                                        SurTran.put("transuramnt"+i,strAmnt);
                                }
                            response.put("SurTran",SurTran);
                                if(!strOutput[8][0].equals(""))
                                {
                                        strOutput[8][0] +=      "</table>";
                //                              strOutput[11][0]        =       strOutput[11][0].substring(0,strOutput[11][0].length()-2);
                                }
                        }
                        catch(Exception d)
                        {
                            logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                            erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                            logger.info("erorCode"+erorCode);
                            logger.info("erorCode"+erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            response.put("Err",erorCode);
                            return response;
                        }                      
                    
                }
                else if(siRKPM.equals("P"))
                {
                        try
                        {
                                strOthrPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                                strRebtPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                                strSurPcml              = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");
                                
                                logger.info("strOthrPcml: " + strOthrPcml);
                                logger.info("strRebtPcml: " + strRebtPcml);
                                logger.info("strSurPcml: " + strSurPcml);
                                response.put("pcmlothrcont",strOthrPcml);
                                response.put("pcmlrebtcont",strRebtPcml);
                                response.put("pcmlsurcont",strSurPcml);
                        }
                        catch(Exception d)
                        {
                                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                                erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                                logger.info("erorCode"+erorCode);
                                logger.info("erorCode"+erorCode);
                                responseXml                     =       "Err:"+erorCode;
                                response.put("Err",erorCode);
                                return response;
                        }
                        
                        if(strOthrPcml == null || strOthrPcml.equals("0") || strOthrPcml.equals(""))
                        {
                                strOthrPcml             = "0";
                                response.put("pcmlothrcont","0");
                                logger.info("No Rows found for other charges piecemeal");
                        }
                        if(strRebtPcml == null || strRebtPcml.equals("0") || strRebtPcml.equals(""))
                        {
                                strRebtPcml             = "0";
                                response.put("pcmlothrcont","0");
                                logger.info("No Rows found for rebate piecemeal");
                        }
                        if(strSurPcml == null || strSurPcml.equals("0") || strSurPcml.equals(""))
                        {
                                strSurPcml              = "0";
                                response.put("pcmlothrcont","0");
                                logger.info("No Rows found for surchargee piecemeal");
                        }
                        intOthrPcml     = new Integer(strOthrPcml.trim()).intValue();
                        intRebtPcml     = new Integer(strRebtPcml.trim()).intValue();
                        intSurPcml      = new Integer(strSurPcml.trim()).intValue();
                        
                        try
                        {
                                strOutput[3][0] =       "";
                //                      strOutput[9][0] =       "";
                                if(intOthrPcml>0)
                                        strOutput[3][0] =       "<div align='left'><font size='2px'>Wagon Load Other Charges Details</font></div>" + strtbl;
                            JSONObject OthrPcml =   new  JSONObject();
                            OthrPcml.put("intOthrPcml",intOthrPcml);                                
                                for(int i=0;i<intOthrPcml;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE", i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD",         i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD",         i,      "0").trim();
                                        logger.info("In Other Charges Picemeal Count:"+i+":");
                                    strOutput[3][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
    //                        strOutput[3][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                //                              strOutput[9][0] += strDesc + ", ";

                                        OthrPcml.put("wgonothrcode"+i,strCode);
                                        OthrPcml.put("wgonothrdesc"+i,strDesc);
                                        OthrPcml.put("wgonothrprcn"+i,strPrcn);
                                        OthrPcml.put("wgonothramnt"+i,strAmnt);
                                }
                            response.put("OthrPcml",OthrPcml);
                                if(!strOutput[3][0].equals(""))
                                {
                //                        strOutput[3][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[9][0] =       strOutput[9][0].substring(0,strOutput[9][0].length()-2);
                                }
                                strOutput[4][0] =       "";
                //                      strOutput[10][0]=       "";
                                if(intRebtPcml>0)
                                        strOutput[4][0] =       "<div align='left'><font size='2px'>Wagon Load Rebate Details</font></div>" + strtbl;
                                String rbtlist  =   "";
                                double  prcnW   =   0.0;
                            JSONObject RebtPcml =   new  JSONObject();
                            RebtPcml.put("intRebtPcml",intRebtPcml);
                                for(int i=0;i<intRebtPcml;i++)
                                {
                                    String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                                    String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();
                                    strOutput[4][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
       //  strOutput[4][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                                        //logger.info("In Rebate Picemeal Count:"+i+":");
                                    rbtlist += strDesc +"@Rs. "+ strAmnt+ " /T@" + "#";
                                    prcnW   += Double.parseDouble(strPrcn);

                                    RebtPcml.put("wgonrebtcode"+i,strCode);
                                    RebtPcml.put("wgonrebtdesc"+i,strDesc);
                                    RebtPcml.put("wgonrebtprcn"+i,strPrcn);
                                    RebtPcml.put("wgonrebtamnt"+i,strAmnt);
                                }
                                response.put("RebtPcml",RebtPcml);
                                    strOutput[0][18] = Math.round(prcnW)+"";
                                    logger.info("Wagon Load Rebate Percentage:"+strOutput[0][18]);
                            
                            if(!rbtlist.equals(""))
                                rbtlist =   rbtlist.substring(0,rbtlist.length()-1);
                            strOutput[0][17] = rbtlist;
                            logger.info("Final Wagon Only Rebate List:"+strOutput[0][17]);
                                if(!strOutput[4][0].equals(""))
                                {
                 //                       strOutput[4][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[10][0]        =       strOutput[10][0].substring(0,strOutput[10][0].length()-2);
                                }
                                
                                strOutput[7][0] =       "";
                //                      strOutput[11][0]        =       "";
                                if(intSurPcml>0)
                                        strOutput[7][0] =       "<div align='left'><font size='2px'>Wagon Load Surcharge Details</font></div>" + strtbl;

                            JSONObject SurPcml =   new  JSONObject();
                            SurPcml.put("intSurPcml",intSurPcml);
                                for(int i=0;i<intSurPcml;i++)
                                {
                                        String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",               i,      "0").trim();
                                        String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",                 i,      "0").trim();
                                        String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                                        String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",             i,      "0").trim();
                                    strOutput[7][0] += "<div class='row small'><div class='col col-sm-6'>"+strDesc+"<hr/></div><div class='col col-sm-3'>"+strAmnt+"</div><div class='col col-sm-3'>"+strPrcn.replaceAll(".00","")+"</div></div>";
    //                        strOutput[7][0] += "<tr><td     border='1px' style='text-align: left'>"+strDesc+"</td><td       border='1px'>"+ strAmnt+" </td><td      border='1px'>"+strPrcn.replaceAll(".00","")+"</td></tr>";
                                        logger.info("In Surcharge Picemeal Count:"+i+":");
                //                              strOutput[11][0]        += strDesc + ", ";

                                    SurPcml.put("wgonsurcode"+i,strCode);
                                    SurPcml.put("wgonsurdesc"+i,strDesc);
                                    SurPcml.put("wgonsurprcn"+i,strPrcn);
                                    SurPcml.put("wgonsuramnt"+i,strAmnt);
                                }
                                response.put("SurPcml",SurPcml);
                                if(!strOutput[7][0].equals(""))
                                {
                //                        strOutput[7][0] +=      "</table><div align='right'><a href='#' style='font-size: 9px;' onclick='javascript:hidepopup()'> CLOSE </a></div>";
                //                              strOutput[11][0]        =       strOutput[11][0].substring(0,strOutput[11][0].length()-2);
                                }
                        }
                        catch(Exception d)
                        {
                            logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                            erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                            logger.info("erorCode"+erorCode);
                            responseXml                     =       "Err:"+erorCode;
                            response.put("Err",erorCode);
                            return response;
                        }
                }

                try
                {
                        //Train Load
                        if(!siRKPM.equals("P"))
                        {
                                strOutput[1][0] = strTotlChblWght;                                                                                                                              //Total Chbl Weight
                                strOutput[1][1] = TQFRGTCALC.getStringItemDef("F_RATECLSS",             1,      "0").trim();                    //si_ClssTL;
                                strOutput[1][2] = TQFRGTCALC.getStringItemDef("F_FRGT",                 1,      "0").trim();                    //Basic Freight
                                strOutput[1][3] = TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             1,      "0").trim();                    //Surcharg
                                strOutput[1][4] = TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           1,      "0").trim();                    //ntr
                                strOutput[1][5] = TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       1,      "0").trim();                    //other charge
                                strOutput[1][6] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           1,      "0").trim();                    //rebate
                                strOutput[1][7] = TQFRGTCALC.getStringItemDef("F_TOTLAMNTCLTD",         0,      "0").trim();                    //si_FrgtTL;                                                                                                                                    //Freight Rate
                                strOutput[1][8] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         1,      "0").trim();                    //      total freight
                                strOutput[1][9] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       1,      "0").trim();                    //Other Charges
                                strOutput[1][10]= TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             1,      "0").trim();                    //Other Rebate
                                strOutput[1][11]= TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       1,      "0").trim();                    //Final Freight
                                strOutput[1][12]= TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             1,      "0").trim();                    //Service Tax
                                strOutput[1][13]= TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         1,      "0").trim().replace(".00","");                    //Final Freight + Service Tax
                                strOutput[1][14]= TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS2",         1,      "0").trim();                    //Final Freight + Service Tax IN WORDS
                                response.put("totlchblwght",strTotlChblWght);                                                                                                                              //Total Chbl Weight
                                response.put("tranloadclss",TQFRGTCALC.getStringItemDef("F_RATECLSS",               1,      "0").trim());            //si_ClssTL;
                                response.put("tranbascfrgt",TQFRGTCALC.getStringItemDef("F_FRGT",                   1,      "0").trim());            //Basic Freight
                                response.put("transurchrg",TQFRGTCALC.getStringItemDef("F_CHRGAMNT",                1,      "0").trim());            //Surcharg
                                response.put("tranntr",TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",                  1,      "0").trim());            //ntr
                                response.put("tranchrg",TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",         1,      "0").trim());            //other charge
                                response.put("tranrebtchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",             1,      "0").trim());            //rebate
                                response.put("tranloadfrgt",TQFRGTCALC.getStringItemDef("F_TOTLAMNTCLTD",           0,      "0").trim());            //si_FrgtTL;                                                                                                                                    //Freight Rate
                                response.put("trantotlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",           1,      "0").trim());            //total freight
                                response.put("tranothrchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",         1,      "0").trim());            //Other Charges
                                response.put("tranothrrebt",TQFRGTCALC.getStringItemDef("F_ARCHFLAG",               1,      "0").trim());            //Other Rebate
                                response.put("tranfinlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",         1,      "0").trim());            //Final Freight
                                response.put("trangsttax",TQFRGTCALC.getStringItemDef("F_FRGTCLTD",                 1,      "0").trim());            //Service Tax
                                response.put("tranfrgttax",TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",            1,      "0").trim().replace(".00",""));            //Final Freight + Service Tax
                                response.put("tranfrgttaxwrds",TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS2",        1,      "0").trim());            //Final Freight + Service Tax IN WORDS
                                strOutput[1][15]= TQFRGTCALC.getStringItemDef("F_CDFDRMRK",             1,      "0").trim();                    //Rebate Remarks
                                
                                response.put("tranrebtrmrk",strOutput[1][15]);
                            response.put("tranrebtamount","");            //Rebate amount
                            response.put("tranfullamount","");            //Full amount
                            response.put("tranrebtprcen","");            //Rebate prcn
                            response.put("trantefdrebtrate","");
                            response.put("trantefdrebtname",""); 
                            
                            if(!strOutput[1][6].equals("0.00") && !strOutput[1][6].equals(""))
                            {
                                strOutput[1][16] =       Math.round((Double.parseDouble(strTotlChblWght) * Double.parseDouble(strOutput[1][6].replace(",",""))) + Double.parseDouble(strOutput[1][10].replace(",",""))) + "";
                                strOutput[1][17] =       truncateDecimal((Double.parseDouble(strOutput[1][16].replace(",",""))  + Double.parseDouble(strOutput[1][13].replace(",",""))),2) + "";
                                //strOutput[1][18] =       Math.round(((Double.parseDouble(strOutput[1][16].replace(",",""))/Double.parseDouble(strOutput[1][17].replace(",",""))) * 100 ))+ "";
                                
                                response.put("tranrebtamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[1][16]))));            //Rebate amount
                                response.put("tranfullamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[1][17]))));            //Full amount
                                response.put("tranrebtprcen",strOutput[1][18]);            //Rebate prcn            
                            }
                            if(strOutput[1][15].indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                            {
                                strOutput[1][15]    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
                              //  String strTEFDT     =  truncateDecimal((Double.parseDouble(strOutput[1][13].replace(",",""))/Double.parseDouble(strOutput[1][2].replace(",",""))) * Double.parseDouble(strRateTL.replace(",","")),2) + "";
                              //  strOutput[1][16]    =  Math.round(Double.parseDouble(strTEFDT) - Double.parseDouble(strOutput[1][13].replace(",",""))) + "";
                              //  strOutput[1][18]    =  Math.round(((Double.parseDouble(strOutput[1][16].replace(",",""))/Double.parseDouble(strTEFDT)) * 100 ))+ "";
                              strOutput[1][16]     =  Math.round(Double.parseDouble(strOutput[1][13].replace(",","")) * (Double.parseDouble(strPrcnT)/100)) +"";
                              strOutput[1][18]= strPrcnT.replace(".00", "");
                              String strTEFDT = Math.round(Double.parseDouble(strOutput[1][13].replace(",","")) + Double.parseDouble(strOutput[1][16]))+"";
                                String rebtrate     =  "";//"Rs. "+truncateDecimal(Double.parseDouble(strRateTL.replace(",",""))-Double.parseDouble(strOutput[1][2].replace(",","")),2) + " /T";
                                response.put("trantefdrebtname","TRADITIONAL EMPTY FLOW DIRECTION (TEFD)");            //Rebate Remarks
                                response.put("tranrebtprcen",strOutput[1][18]);            //Rebate prcn
                                response.put("tranrebtamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[1][16]))));            //Rebate amount
                                response.put("tranfullamount",formatIndianCurrency(Math.round(Double.parseDouble(strTEFDT))));                    //Full amount
                                response.put("trantefdrebtrate",rebtrate);                      // rebate rate 
                                response.put("tranrebtrmrk",strOutput[1][15]);
                            }
                            logger.info("train load in words:"+strOutput[1][14]);
                            logger.info("tranrebtrmrk:"+strOutput[1][15]);
                            logger.info("tranrebtamount:"+strOutput[1][16]);
                            logger.info("tranfullamount:"+strOutput[1][17]);
                            logger.info("tranrebtprcen:"+strOutput[1][18]);
                        }
                        //Wagon Load
                        strOutput[0][0] = strTotlChblWght;                                                                                                                              //Total Chbl Weight
                        strOutput[0][1] = TQFRGTCALC.getStringItemDef("F_RATECLSS",             0,      "0").trim();                    // si_ClssWL;
                        strOutput[0][2] = TQFRGTCALC.getStringItemDef("F_FRGT",                 0,      "0").trim();                    //Basic Freight
                        strOutput[0][3] = TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             0,      "0").trim();                    //Surcharg
                        strOutput[0][4] = TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           0,      "0").trim();                    //ntr
                        strOutput[0][5] = TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       0,      "0").trim();                    //other charge
                        strOutput[0][6] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           0,      "0").trim();                    //rebate
                        strOutput[0][7] = TQFRGTCALC.getStringItemDef("F_AMNTPYBL",             0,      "0").trim();                    //si_FrgtWL;                                                                                                                                    //Freight Rate
                        strOutput[0][8] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         0,      "0").trim();                    //total freight
                        strOutput[0][9] = TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       0,      "0").trim();                    //Other Charges
                        strOutput[0][10]= TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             0,      "0").trim();                    //Other Rebate
                        strOutput[0][11]= TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       0,      "0").trim();                    //Final Freight
                        strOutput[0][12]= TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             0,      "0").trim();                    //Service Tax
                        strOutput[0][13]= TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         0,      "0").trim().replace(".00","");                    //Final Freight + Service Tax
                        strOutput[0][14]= TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS1",         0,      "0").trim();                    //Final Freight + Service Tax IN WORDS
                        response.put("wgonchblwght",strTotlChblWght);                                                                              //Total Chbl Weight
                        response.put("wgonloadclss",TQFRGTCALC.getStringItemDef("F_RATECLSS",             0,      "0").trim());                    // si_ClssWL;
                        response.put("wgonbascfrgt",TQFRGTCALC.getStringItemDef("F_FRGT",                 0,      "0").trim());                    //Basic Freight
                        response.put("wgonsurchrg", TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             0,      "0").trim());                    //Surcharg
                        response.put("wgonntr",     TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           0,      "0").trim());                    //ntr
                        response.put("wgonchrg",TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       0,      "0").trim());                    //other charge
                        response.put("wgonrebtchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           0,      "0").trim());                    //rebate
                        response.put("wgonloadfrgt",TQFRGTCALC.getStringItemDef("F_AMNTPYBL",             0,      "0").trim());                    //si_FrgtWL;                                                                                                                                    //Freight Rate
                        response.put("wgontotlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         0,      "0").trim());                    //      total freight
                        response.put("wgonothrchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       0,      "0").trim());                    //Other Charges
                        response.put("wgonothrrebt",TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             0,      "0").trim());                    //Other Rebate
                        response.put("wgonfinlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       0,      "0").trim());                    //Final Freight
                        response.put("wgongsttax"  ,TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             0,      "0").trim());                    //Service Tax
                        response.put("wgonfrgttax" ,TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         0,      "0").replace(".00","").trim());                    //Final Freight + Service Tax
                        response.put("wgonfrgttaxwrds",TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS1",      0,      "0").trim());   
                        strOutput[0][15]= TQFRGTCALC.getStringItemDef("F_CDFDRMRK",             0,      "0").trim();                    //Rebate Remarks
                        response.put("wgonrebtamount","");            //Rebate amount
                        response.put("wgonfullamount","");            //Full amount
                        response.put("wgonrebtprcen","");            //Rebate prcn
                        response.put("wgonrebtrate","");
                        response.put("wgonrebtname","");
                        if(!strOutput[0][6].equals("0.00") && !strOutput[0][6].equals(""))
                        {
                            strOutput[0][16] =      Math.round(Double.parseDouble(strTotlChblWght) * Double.parseDouble(strOutput[0][6])) + Double.parseDouble(strOutput[0][10]) + "";
                            strOutput[0][17] =      truncateDecimal((Double.parseDouble(strOutput[0][16].replace(",",""))  + Double.parseDouble(strOutput[0][13].replace(",",""))),2) + "";
                            //strOutput[0][18] =      Math.round(((Double.parseDouble(strOutput[0][16].replace(",",""))/Double.parseDouble(strOutput[0][17].replace(",",""))) * 100 ))+ "";
                            
                            response.put("wgonrebtamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[0][16]))));            //Rebate amount
                            response.put("wgonfullamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[0][17]))));            //Full amount
                            response.put("wgonrebtprcen",strOutput[0][18]);            //Rebate prcn
                        }                        
                        if(strOutput[0][15].indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                        {
                            strOutput[0][15]    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
                            //String strTEFDW     =  truncateDecimal((Double.parseDouble(strOutput[0][13].replace(",",""))/Double.parseDouble(strOutput[0][2].replace(",",""))) * Double.parseDouble(strRateWL.replace(",","")),2) + "";  //Full freight without rebate
                            //strOutput[0][16]    =  Math.round(Double.parseDouble(strTEFDW) - Double.parseDouble(strOutput[0][13].replace(",",""))) + "";                            // Discounted amount
                            //strOutput[0][18]    =  Math.round(((Double.parseDouble(strOutput[0][16].replace(",",""))/Double.parseDouble(strTEFDW)) * 100 ))+ "";                    // Rebate Prcn
                            String rebtrate     =  "";//"Rs. "+truncateDecimal(Double.parseDouble(strRateWL.replace(",",""))-Double.parseDouble(strOutput[0][2].replace(",","")),2) + " /T";
                            
                            strOutput[0][16]     =  Math.round(Double.parseDouble(strOutput[0][13].replace(",","")) * (Double.parseDouble(strPrcnW)/100)) +"";
                            strOutput[0][18]    = strPrcnW.replace(".00", "");
                            String strTEFDW     = Math.round(Double.parseDouble(strOutput[0][13].replace(",","")) + Double.parseDouble(strOutput[0][16]))+"";
                            response.put("wgonrebtname","TRADITIONAL EMPTY FLOW DIRECTION (TEFD)");            //Rebate Remarks
                            response.put("wgonrebtprcen",strOutput[0][18]);            //Rebate prcn
                            response.put("wgonrebtamount",formatIndianCurrency(Math.round(Double.parseDouble(strOutput[0][16]))));            //Rebate amount
                            response.put("wgonfullamount",formatIndianCurrency(Math.round(Double.parseDouble(strTEFDW))));            //Full amount
                            response.put("wgonrebtrate",rebtrate);                      // rebate rate
                        }
                        response.put("wgonrebtrmrk",strOutput[0][15]);            //Rebate Remarks
                         
                            logger.info("wagon load in words:"+strOutput[0][14]);
                            logger.info("wgonrebtrmrk:"+strOutput[0][15]);
                            logger.info("wgonrebtamount:"+strOutput[0][16]);
                            logger.info("wgonfullamount:"+strOutput[0][17]);
                            logger.info("wgonrebtprcen:"+strOutput[0][18]);
                }
                catch(Exception e)
                {
                    logger.info("Unable to write to buffer : " + e.toString());
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                
                try
                {
                        TQFRGTCALC.endSession();
                }
                catch(Exception e)
                {
                    logger.info("Error In End Session:" + e.toString());
                    logger.info("There was an exception while creating and using the FOIS."+e);
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(2)";
                    logger.info("erorCode"+erorCode);
                    responseXml                     =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                finally
                {
                try
                {
                        TQFRGTCALC=null;
                }
                catch(Exception e)
                {
                    logger.info("Errror In Finally.");
                    erorCode="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(3)";
                    logger.info("erorCode"+erorCode);
                    responseXml      =       "Err:"+erorCode;
                    response.put("Err",erorCode);
                    return response;
                }
                }
                //req.setAttribute("Details",strOutput);
                logger.info("SUCCESSFULL");

            }
            catch(Exception e)
            {
                 //logger.info( e.printStackTrace());
                logger.info("In catch");
                    responseXml                     =       "Err:SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME";
                    return response;
            }
            logger.info("Response:"+response.toString());
            return response;
    }

    public static String formatIndianCurrency(long amount)
    {
            if(amount < 1000)
                    return "" + amount;
            long decimalPlace       = amount % 10;
            long rest                       = amount / 10;

            return (new java.text.DecimalFormat("##,##,##")).format(rest) + decimalPlace;
    }

    public static JSONObject getRoutjson(String siSttnFrom,String siSttnTo,String siQryFlag,String si_CmdtName,String si_WgonType)
    {
        //NDC.push("CalculateExpectedFreight");
        logger.info("Entering FSH_FrgtCalc..getDtls..");
        //siCmdt  =   StringEscapeUtils.unescapeJava(siCmdt);
        String strFromsttn  =   siSttnFrom;
        String strTosttn  =   siSttnTo;
        try
        {
            if(siSttnFrom.indexOf("-") != -1)
                    siSttnFrom                              =       siSttnFrom.substring(siSttnFrom.lastIndexOf("-")+1,siSttnFrom.lastIndexOf("(")).trim();
            if(siSttnTo.indexOf("-") != -1)
                    siSttnTo                                =       siSttnTo.substring(siSttnTo.lastIndexOf("-")+1,siSttnTo.lastIndexOf("(")).trim();
            if(si_CmdtName.indexOf("-") != -1)
                    si_CmdtName                              =       si_CmdtName.substring(si_CmdtName.lastIndexOf("-")+1,si_CmdtName.lastIndexOf("(")).trim();
            if(si_WgonType.indexOf("-") != -1)
                    si_WgonType                                =       si_WgonType.substring(si_WgonType.lastIndexOf("-")+1,si_WgonType.lastIndexOf("(")).trim();
      
        }
        catch(Exception e){}
        logger.info("siSttnFrom:"+siSttnFrom);               
        logger.info("siSttnTo:"+siSttnTo);
       
        JSONObject response =   new  JSONObject();
       
        String strDist      =    "";
        String strETA       =    "";
        String strVia       =   "";
        
            try
            {
                Date date = Calendar.getInstance().getTime();

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = formatter.format(date);
                logger.info("Today : " + strDate);
                

        //        if(!GG_Validate.chkName(si_CmdtName)) return null;
        //        if(!GG_Validate.chkName(siSttnFrom)) return null;
        //        if(!GG_Validate.chkName(siSttnTo)) return null;

                    FOISTuxedo TQPREFRGTCALC        = null;
                    try
                    {
                            logger.info("Calling Tuxedo");
                            TQPREFRGTCALC = new FOISTuxedo();
                    }
                    catch (Exception ne)
                    {
                            logger.info("Unable to get the Client Object");
                            return null;
                    }
                    
                    try
                    {
                            TQPREFRGTCALC.tuxInit("TQPREFRGTCALC");

                            logger.info("strDate:"+strDate+":");
                            logger.info("siSttnFrom:"+siSttnFrom+":");
                            logger.info("siSttnTo:"+siSttnTo+":");
                            logger.info("si_CmdtName:"+si_CmdtName+":");
                            logger.info("siSttnTo:"+siSttnTo+":");
                        
                            TQPREFRGTCALC.setString("F_DATE",       0, strDate.trim());//java.time.LocalTime.now()+"");
                            TQPREFRGTCALC.setString("F_STTNFROM",   0, siSttnFrom.trim());
                            TQPREFRGTCALC.setString("F_STTNTO",     0, siSttnTo.trim());
                            TQPREFRGTCALC.setString("F_CMDT",       0, si_CmdtName);
                            TQPREFRGTCALC.setString("F_WGONTYPE",   0, "");
                            TQPREFRGTCALC.setString("F_USERID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_CLNTID",     0, "CRIS1");
                            TQPREFRGTCALC.setString("F_FLAG",       0, siQryFlag);
                    }
                    catch(Exception e)
                    {
                            //e.printStackTrace();
                            logger.info("Unable to buffers");
                            return null;
                    }
    
                    String erorCode                 = null;
                    
                    try
                    {
                            logger.info("Calling Tuxedo Service TQPREFRGTCALC ...");
                            TQPREFRGTCALC.call("N");
                            logger.info("CALL COMPLETED !");
                    }
                    catch(Exception e)
                    {                               
                            //e.printStackTrace();
                            logger.info("IN Freight Calculator Service is currently unavailable.Please try after some time." + e.toString());
                            erorCode = "Freight Calculator Service is currently unavailable.Please try after some time.";
                            return null;
                    }
                    
                    try
                    {
                            logger.info("BeforeErrorCode: " + erorCode+":");
                            erorCode                = TQPREFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                            logger.info("AfterErrorCode: " + erorCode+":");
                    }
                    catch(Exception e)
                    {
                            // F_ERORNO is not set by Developer, So, it is not an Error
                    }
                    if(erorCode != null && (!erorCode.equals("")))
                    {
                            logger.info("ErrorCode: " + erorCode);
                            response.put("Err",erorCode);
                            return response;
                    }
                if(siQryFlag.equals("T")) //For Time and Distance
                {
                    strDist         =        TQPREFRGTCALC.getStringItemDef("F_CHBLDIST",   0,      "0").trim();
                    strETA          =        TQPREFRGTCALC.getStringItemDef("F_CALLTIME",   0,      "0").trim();
                    strETA  =   Math.round(Double.parseDouble(strETA))+"";
                    response.put("Dist",strDist);
                    response.put("ETA",Math.round(Double.parseDouble(strETA))); 
                }
                else  //siQryFlag = "D";  For Route
                    strVia         =        TQPREFRGTCALC.getStringItemDef("F_VIA",   0,      "0").trim();
                    response.put("route",strVia);
                logger.info("SUCCESSFULL");

            }
            catch(Exception e)
            {
                 //logger.info( e.printStackTrace());
                    logger.info("In catch");
                    return response;
            }
            logger.info("Response:"+response.toString());
            return response;
    }
    
    private static BigDecimal truncateDecimal(final double x, final int numberofDecimals) {
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_DOWN);
    }
}
