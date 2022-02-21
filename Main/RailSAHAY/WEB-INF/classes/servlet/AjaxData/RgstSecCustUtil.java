package servlet.AjaxData;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.log4j.spi.ErrorCode;

import tuxedo.FOISTuxedo;

import user.UserProfDtls;

import util.logger.FoisLogger;

public class RgstSecCustUtil extends HttpServlet
{
    static Logger logger = FoisLogger.getLogger(RgstSecCustUtil.class.getName());
    HttpServletResponse response=null;
    public void doGet(HttpServletRequest req, HttpServletResponse   res) throws IOException
    {
            doPost(req, res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse  res) throws IOException
    {

            req.setCharacterEncoding("UTF-8");
            /*res.setContentType("application/json");*/
            res.setHeader("Cache-Control", "no-cache");
            response=res;
            //Just To View the Information
            Enumeration enumeration = req.getParameterNames();
            while(enumeration.hasMoreElements())
            {
                    String name     = (String)enumeration.nextElement();
                    String values[] = req.getParameterValues(name);
            }
            HttpSession session = req.getSession(false);
        
            String si_QryType     = req.getParameter("Qry").toUpperCase().trim();       
            String si_moblnumb        = "";
            String si_emailid        = ""; 
            String si_chrgType       = "";
            String si_custid = "";
            String strZone="", strDvsn="",  strSttn="";
            String si_Sttn  =   "";
            String si_Cust    =   "";
            String si_prmcustid = "";
            String sarr[];
            String strrqst="";
            String si_pcust="";
        UserProfDtls userdtls = new UserProfDtls((String)session.getAttribute("custuserid")); 
        si_prmcustid=(String)userdtls.getTavcustid();
        logger.info("si_prmcustid:"+si_prmcustid+":si_QryType.trim():"+si_QryType.trim()); 
        if(si_QryType.trim().equals("PROFDTLS"))
        {       
            logger.info("si_QryType:"+si_QryType+":si_custid:"+si_custid+":");
            si_custid = req.getParameter("custid").toUpperCase().trim();            
        }
        if(si_QryType.trim().equals("SECCUST")){
            si_Cust=req.getParameter("CustCode").toUpperCase().trim();
        }
        if(si_QryType.trim().equals("CLOSESEC")){
            si_Cust=req.getParameter("cust").toUpperCase().trim();
            strrqst=req.getParameter("record").toUpperCase().trim();
        }
        if(si_QryType.trim().equals("DATA") ||si_QryType.trim().equals("ORG")  )                
         {
             si_moblnumb        = req.getParameter("moblnumb").toUpperCase().trim();
             si_emailid         = req.getParameter("emailid").toUpperCase().trim();
             si_Sttn            = req.getParameter("sttn").toUpperCase().trim();            
             si_Cust            = req.getParameter("cust").toUpperCase().trim();
             if(si_QryType.trim().equals("DATA"))
             {
                 si_pcust           = req.getParameter("pcust").toUpperCase().trim();
                 si_pcust  = si_pcust.substring(si_pcust.indexOf("(")+1,si_pcust.indexOf(")")).trim();
             }
         }
        if(si_QryType.trim().equals("LOCNHELP")) {
            String sz = req.getParameter("zone");
            String sd = req.getParameter("dvsn");
            String ss = req.getParameter("sttn");
            if(sz!=null)
                if(!sz.equals(""))
                strZone = sz.toUpperCase().trim();
            if(sd!=null)
                if(!sd.equals(""))
                strDvsn = sd.toUpperCase().trim();
            if(ss!=null)
                if(!ss.equals(""))
                strSttn = ss.toUpperCase().trim();
            logger.info("strZone:"+strZone+"strDvsn:"+strDvsn+":strSttn:"+strSttn+":"); 
        }
        if(si_QryType.trim().equals("SAVE")){
             strrqst = (String)req.getParameter("arr");            
             si_Cust            = req.getParameter("cust").toUpperCase().trim(); 
             logger.info("strrqst:"+strrqst+":");
           /* for(int h=0;h<sarr.length();h++){
                
            }*/
        }
            try
            {
                if(si_Sttn.indexOf("-") != -1)
                        si_Sttn                              =       si_Sttn.substring(si_Sttn.lastIndexOf("-")+1,si_Sttn.lastIndexOf("(")).trim();
                if(si_Cust.indexOf("-") != -1)
                        si_Cust                                =       si_Cust.substring(si_Cust.lastIndexOf("-")+1,si_Cust.lastIndexOf("(")).trim();
               
            }
            catch(Exception e){}
            logger.info("si_Sttn:"+si_Sttn);       
            logger.info("si_Cust:"+si_Cust);    
           
            String resData="";
            logger.info("Call for Mobile Number List");
            if(si_QryType.trim().equals("DATA") || si_QryType.trim().equals("ORG") )
                   resData = getData(si_Sttn, si_Cust, si_emailid, si_moblnumb, si_chrgType, si_QryType, si_prmcustid, si_pcust);
            if( si_QryType.trim().equals("GORG"))
                    resData = getData(si_Sttn, si_Cust, si_emailid, si_moblnumb, si_chrgType, si_QryType, si_prmcustid, si_pcust);
            if(si_QryType.trim().equals("PROFDTLS"))
               resData = getProfDtls(si_custid);
            if(si_QryType.trim().equals("SECCUST"))
               resData = getSecCustData(si_prmcustid,si_Cust );
            if(si_QryType.trim().equals("LOCNHELP"))
               resData = getLocnDtls(strZone, strDvsn, strSttn, si_QryType, si_prmcustid);
            if(si_QryType.trim().equals("SAVE"))
                resData = saveDtls(si_prmcustid, strrqst,si_Cust); 
            if(si_QryType.trim().equals("CLOSESEC"))
               resData = closeSecCustData(si_prmcustid,si_Cust, strrqst );
            logger.info((new StringBuilder()).append("Sending JSON Data As:").append(resData).toString());
            res.getWriter().write(resData);

    }
    public String saveDtls(String si_prmcustid,String sarr, String si_Cust ) {
        logger.info("in save dtls..");
        String strData="";
        sarr=sarr+"-";
        logger.info("sarr:"+sarr);
        String st[] = sarr.split("@");
        if(si_Cust.indexOf("(") != -1)
                si_Cust  = si_Cust.substring(si_Cust.indexOf("(")+1,si_Cust.indexOf(")")).trim();
        
        String scust="", szone="", sdv="",  sst="", schrg="", ssecid="", pcust="", seccust="";
        logger.info("st length:"+st.length+":si_Cust:"+si_Cust+":");
        logger.info("st[0]:"+st[0]);
        
        FOISTuxedo TTSCUSTSAVE = null;                                                   
                     try                                                                             
                     {                                                                               
                         TTSCUSTSAVE = new FOISTuxedo();                                              
                     }                                                                               
                     catch (Exception ne)                                                            
                     {                                                                               
                         logger.info("Unable to get the Client Object");                             
                     }                                                                               
                                                                                                     
                     try                                                                             
                     {                                                                               
                         TTSCUSTSAVE.tuxInit("TTSCUSTSAVE");
                         //logger.info("strSttn:"+strSttn+",strDvsn:"+strDvsn+",strZone:"+strZone+":");
                         TTSCUSTSAVE.setString("F_PRTFID",    0, si_prmcustid);                          
                         TTSCUSTSAVE.setString("F_CLNTID",        0, "CRIS1");                    
                         TTSCUSTSAVE.setString("F_USERID",        0, "CRIS1");
                         TTSCUSTSAVE.setString("F_ZONLICFLAG",     0,"A".trim());
                         TTSCUSTSAVE.setString("F_OPTN",        0,"W".trim());
                         
                         int j=0;
                         for(int t=0; t<st.length-1;t++)
                         {
                             logger.info("t:"+t+"::"+st[t]+":");
                             scust=st[t].substring(st[t].indexOf("$")+1, st[t].indexOf("#"));
                             int sind=st[t].indexOf(scust+"#");
                             int eind = st[t].indexOf("#Z#");
                             szone=st[t].substring(sind+1+scust.length(),  eind);
                             sdv =st[t].substring(st[t].indexOf("#Z#")+3,  st[t].indexOf("#D#")); 
                             sst = st[t].substring(st[t].indexOf("#D#")+3,  st[t].indexOf("#S#")); 
                             sind=st[t].indexOf("#S#");
                             eind = st[t].indexOf("#",sind+3);
                             schrg = st[t].substring(sind+3, eind );
                             int ip=st[t].indexOf("^");
                             int ipb=st[t].lastIndexOf("#");
                             ssecid = st[t].substring(eind+1, ipb);
                              pcust=st[t].substring(ipb+1, ip);
                             seccust=st[t].substring(ip+1);
                             logger.info("scust:"+scust+":szone:"+szone);
                             logger.info("sdv:"+sdv);
                             logger.info("sst:"+sst);
                             logger.info("schrg:"+schrg);
                             logger.info("ssecid:"+ssecid);
                             logger.info("seccust:"+seccust+":");;
                             logger.info("pcust:"+pcust+":");

                             //TTSCUSTSAVE.setString("F_CUSTCODE",  j, pcust); //si_Cust);                       
                             szone=szone.replaceAll(", ", ",");
                             sdv=sdv.replaceAll(", ", ",");
                             sst=sst.replaceAll(", ", ",");
                             if(szone!=null)
                             {    if(!szone.equals(""))
                                 {    TTSCUSTSAVE.setString("F_YARD",         j, szone);               
                                      TTSCUSTSAVE.setString("F_ZONEICFLAG",   j, "Z");   
                                      TTSCUSTSAVE.setString("F_SGID",         j, ssecid);       
                                      TTSCUSTSAVE.setString("F_RSTNFLAG",     j, schrg);
                                      TTSCUSTSAVE.setString("F_STRYCODE",     j, seccust);
                                      TTSCUSTSAVE.setString("F_CUSTCODE",  j, pcust);
                                     j++;
                                 }
                             }
                             if(sdv!=null)
                             {    if(!sdv.equals(""))
                                 {    TTSCUSTSAVE.setString("F_YARD",         j, sdv); 
                                      logger.info("F_YARD:sdv:"+sdv);
                                      TTSCUSTSAVE.setString("F_ZONEICFLAG",   j, "D");   
                                      TTSCUSTSAVE.setString("F_SGID",         j, ssecid);       
                                      TTSCUSTSAVE.setString("F_RSTNFLAG",     j, schrg);
                                      TTSCUSTSAVE.setString("F_STRYCODE",     j, seccust);
                                      TTSCUSTSAVE.setString("F_CUSTCODE",  j, pcust);                                      logger.info("F_YARD:sdv:"+sdv);
                                      logger.info("F_YARD:pcust:"+pcust+":j:"+j);
                                      j++;
                                 }
                             }
                             logger.info("after j:"+j);
                             if(sst!=null)
                             {    if(!sst.equals(""))
                                 {    TTSCUSTSAVE.setString("F_YARD",         j, sst);               
                                      TTSCUSTSAVE.setString("F_ZONEICFLAG",   j, "S");   
                                      TTSCUSTSAVE.setString("F_SGID",         j, ssecid);       
                                      TTSCUSTSAVE.setString("F_RSTNFLAG",     j, schrg);
                                      TTSCUSTSAVE.setString("F_STRYCODE",     j, seccust);
                                      TTSCUSTSAVE.setString("F_CUSTCODE",  j, pcust);
                                     j++;
                                 }
                             }
                         } 
                         logger.info("j is here:"+j);
                         TTSCUSTSAVE.setString("F_ROWCONT",   0, j+"");
                     }                                                                               
                     catch(Exception e)                                                              
                     {            
                         e.printStackTrace();
                        logger.info("Error in setstring : " + e.toString());                                        
                     }                                                                               
                     try                                                                             
                     {                                                                               
                         logger.info("Calling Tuxedo Service TTSCUSTSAVE ...");                       
                         TTSCUSTSAVE.call("N");                                                       
                         logger.info("CALL COMPLETED !");                                            
                     }                                                                               
                     catch(Exception e)                                                              
                     {                                                                               
                         logger.info("Exception while call to the service is " + e.toString());      
                     }                                                                               
                     String erorCode                 = null;                                         
                     try                                                                             
                     {                                                                               
                         erorCode               = TTSCUSTSAVE.getStringItemDef("F_ERORNO",0,"0");     
                     }                                                                               
                     catch(Exception e)                                                              
                     {
                         e.printStackTrace();
                     }                                                                              
                     if(!erorCode.equals("SUCCESS"))                                  
                     {                                                                               
                         logger.info("ErrorCode: " + erorCode);                                      
                         strData="{\"ServiceCall\":\"F\",\"ErrorCode\":\""+erorCode+"\"}";               
                         return strData;                                                             
                     }
                     else
                     {
                         try{
                             String  ss = TTSCUSTSAVE.getStringItemDef("F_DDMESG",0,"0");     
                            strData="{\"ServiceCall\":\"S\",\"response\":\""+ss+"\"}";
                         }
                         catch(Exception e){
                             e.printStackTrace();
                             logger.info("in catch2:"+e.toString());
                         }
                     }
                     logger.info("strData"+strData+":");
                     return strData;
    }
    public String getData(String si_Sttn, String si_Cust, String si_emailid,String si_moblnumb, String si_chrgType, String si_QryType, String  si_prmcustid, String si_pcust) {
        String strData="";
        logger.info("Calling TQCUSTDATA to get TQCUSTDATA list");

        FOISTuxedo TQCUSTDATA = null;
        try
        {
                TQCUSTDATA = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object");
        }

        try
        {
            TQCUSTDATA.tuxInit("TQCUSTDATA");
            TQCUSTDATA.setString("F_STTN",       0, si_Sttn.trim());
            TQCUSTDATA.setString("F_CUSTCODE",     0, si_Cust);
            if(si_QryType.trim().equals("GORG")) {
                TQCUSTDATA.setString("F_PRTFID",       0, ""); 
                //si_QryType="ORG";      
            }
            else
                TQCUSTDATA.setString("F_PRTFID",       0, si_prmcustid); 
            TQCUSTDATA.setString("F_OPTN",      0,   si_QryType.trim());
            TQCUSTDATA.setString("F_CUSTPHNO",       0, si_moblnumb);
            TQCUSTDATA.setString("F_CUSTEMAL",       0, si_emailid);
            TQCUSTDATA.setString("F_PRMYLOCN",      0,  si_pcust);
        }
        catch(Exception e)
        {
                //logger.info"Error in Servlet : " + e);
        }
        try
        {
                logger.info("Calling Tuxedo Service TQCUSTDATA ...");
                TQCUSTDATA.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.info("Exception while call to the service is " + e.toString());
        }
        String erorCode                 = "";
        try
        {
                erorCode               = TQCUSTDATA.getStringItemDef("F_ERORNO",0,"0");
        }
        catch(Exception e)
        {}
        if(erorCode != null && (!erorCode.equals("")))
        {
                logger.info("ErrorCode: " + erorCode);
                strData="{\"ServiceCall\":\"F\",\"ErrorCode\":"+erorCode+"}";
                return strData;
        }            
        String startRowCount1   = null;
        int start1              = 0;

        try
        {
                startRowCount1          = TQCUSTDATA.getStringItemDef("F_ROWCONT",0,"0");
        }
        catch(Exception e)
        {}
        logger.info("startRowCount1:"+ startRowCount1);
        if(startRowCount1.equals("0") || startRowCount1.equals(""))
        {
                logger.info("ErrorCode: " + "No data found");
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
               return strData;
        }
        else
        {
                start1 = new Integer(startRowCount1.trim()).intValue();
        }
        String strCustuserid   = "" ;
        String strCustctgr     = "" ;
        String strCustfrstname = "" ;
        String strCustlastname = "" ;
        String strCustemalid   = "" ;
        String strCustmoblnumb = "" ;
        String strTavcustid = ""; 
        String strOrgname = "";
       
        String custaddr       = "";
        String custpincode    = "";
        String strZone = "";
        String strDvsn = "";
        String strSttn  = "";
        String strGlblCustName = "";
        String strPrevGlblCust = "";
        String strPrevcustid = "";
        int c=0, og=0;
        if(si_QryType.equals("ORG"))
        {
            strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\",\"OrgData\":[";        
        }
        if(si_QryType.equals("GORG"))
        {
            strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\",\"OrgData\":[";        
        }
        if(si_QryType.equals("DATA")){
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\",\"UserData\":[";
        }
        try
        {
            for(int i=0;i<start1;i++)
            {
                if(si_QryType.equals("ORG") || si_QryType.equals("GORG"))
                {
                    strOrgname    = TQCUSTDATA.getStringItemDef( "F_CNSG",        i,"0").trim();
                    if(i==0)
                        strData+="\""+strOrgname+"\"";
                    else
                        strData+=",\""+strOrgname+"\"";
                }
                if(si_QryType.equals("DATA"))
                {
                    strCustuserid   =  TQCUSTDATA.getStringItemDef("F_USERID",      i,"0").trim();
                    strCustctgr     =  TQCUSTDATA.getStringItemDef("F_TRANCTGR",    i,"0").trim();
                    strCustfrstname =  TQCUSTDATA.getStringItemDef("F_NEWLOADNAME", i,"0").trim();
                    strCustlastname =  TQCUSTDATA.getStringItemDef("F_USERNAME",    i,"0").trim();
                    strCustemalid   =  TQCUSTDATA.getStringItemDef("F_STTNFROM",    i,"0").trim();
                    strCustmoblnumb = TQCUSTDATA.getStringItemDef( "F_STTNTO",      i,"0").trim();
                    strTavcustid    = TQCUSTDATA.getStringItemDef( "F_CNSG",        i,"0").trim();
                    custaddr         = TQCUSTDATA.getStringItemDef( "F_CNSREXSS",   i,"0").trim();
                    strZone         = TQCUSTDATA.getStringItemDef( "F_OWNGRLY",    i,"0").trim();
                    strDvsn         = TQCUSTDATA.getStringItemDef( "F_INSGDVSN",    i,"0").trim();
                    strSttn         = TQCUSTDATA.getStringItemDef( "F_INSGSTTN",    i,"0").trim();
                    strGlblCustName = TQCUSTDATA.getStringItemDef( "F_CNSRCNSGFLAG",i,"0").trim();
                    if(i==0 || !strPrevcustid.equals(strTavcustid))
                    {
                        //strPrevGlblCust = strGlblCustName;
                        //strPrevcustid = strTavcustid;
                        if(i!=0)
                            strData+="}]},";
                        strData+="{\"Custuserid\":\""+strCustuserid+"\",\"Custctgr\":\""+strCustctgr+"\",\"Custfrstname\":\""+strCustfrstname+"\",\"Custlastname\":\""+strCustlastname+"\",\"Custemalid\":\""+strCustemalid+"\",\"Custmoblnumb\":\""+strCustmoblnumb+"\",\"Tavcustid\":\""+strTavcustid+"\",\"custaddr\":\""+custaddr+"\",\"org\":[{";
                        c=0;
                    }
                    
                    //logger.info("strDvsn:"+strDvsn+":");
                   // logger.info("strPrevGlblCust:"+strPrevGlblCust+":strGlblCustName:"+strGlblCustName+":");
                    //  logger.info(strPrevcustid+"====" +strTavcustid);
                    if(!strPrevGlblCust.equals(strGlblCustName))
                    {   if(c!=0)
                           strData+="},{\"name\":\""+strGlblCustName+"\"";
                      else
                          strData+="\"name\":\""+strGlblCustName+"\"";
                    }
                    else
                        strData+="\"name\":\""+strGlblCustName+"\"";
                   
                   
                    //else //(strPrevGlblCust.equals(strGlblCustName))
                   // {    
                           /* if(og==0)
                                strData+="[\"name\":\""+strGlblCustName+"\"";
                            else
                                strData+=",";
                             */  // strData+=",[\"name\":\""+strGlblCustName+"\"";
                            if(!strZone.equals("ZONES:"))
                            {
                                strZone = strZone.substring(0,strZone.length()-1);
                                strData+=",\"zone\":\""+strZone+"\"";
                            }
                            if(!strDvsn.equals("DIVISIONS:")) {
                                strDvsn = strDvsn.substring(0,strDvsn.length()-1);
                                strData+=",\"dvsn\":\""+strDvsn+"\"";
                            }
                              if(!strSttn.equals("STATIONS:")){
                                  strSttn = strSttn.substring(0,strSttn.length()-1);
                                  strData+=",\"sttn\":\""+strSttn+"\"";
                              }
                            //strData+="]";
                            c++; og++;
                  //  }
                    strPrevGlblCust = strGlblCustName;
                      strPrevcustid = strTavcustid;
                }   
                    //strData+="{\"Custuserid\":\""+strCustuserid+"\",\"Custctgr\":\""+strCustctgr+"\",\"Custfrstname\":\""+strCustfrstname+"\",\"Custlastname\":\""+strCustlastname+"\",\"Custemalid\":\""+strCustemalid+"\",\"Custmoblnumb\":\""+strCustmoblnumb+"\",\"Tavcustid\":\""+strTavcustid+"\",\"custaddr\":\""+custaddr+"\",:\"org\":\""+strGlblCustName+"\",\"zone\":\""+strZone+"\",\"dvsn\":\""+strDvsn+"\",\"sttn\":\""+strSttn+"\"}";
                if(si_QryType.equals("LOCNHELP"))
                {
                }
            }
            if(si_QryType.equals("ORG") || si_QryType.equals("GORG"))
                strData+="]}";
            else
                strData+="}]}]}";            
        }
        catch(Exception e)
        {
                logger.info("Error In getting output buffers:"+e.toString());
        }
        //strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\"}";
        
        logger.info("strData:"+strData);
        return strData;
    }
    
    public String getLocnDtls(String strZone,String strDvsn,String strSttn, String si_QryType, String si_prmcustid) {
            String strData="";
            logger.info("Calling TQCUSTDATA to get getLocnDtls");
        
            FOISTuxedo TQCUSTDATA = null;
            try
            {
                TQCUSTDATA = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                logger.info("Unable to get the Client Object");
            }
        
            try
            {
                TQCUSTDATA.tuxInit("TQCUSTDATA");
                if(strSttn!=null)
                    if(strSttn.equals(""))
                        strSttn = strSttn.replaceAll("\'","");
                if(strDvsn!=null)
                    if(strDvsn.equals(""))
                        strDvsn = strDvsn.replaceAll("\'","");
                if(strZone!=null)
                    if(strZone.equals(""))
                        strZone = strZone.replaceAll("\'","");
                logger.info("strSttn:"+strSttn+",strDvsn:"+strDvsn+",strZone:"+strZone+":");
                TQCUSTDATA.setString("F_OWRDSTTN",       0, strSttn);
                TQCUSTDATA.setString("F_DVSNFROM",     0, strDvsn);
                TQCUSTDATA.setString("F_OWNGZONE",     0,strZone);
                TQCUSTDATA.setString("F_PRTFID",       0, si_prmcustid);
                TQCUSTDATA.setString("F_OPTN",     0,si_QryType.trim());
            
            }
            catch(Exception e)
            {
            //logger.info"Error in Servlet : " + e);
            }
            try
            {
                logger.info("Calling Tuxedo Service TQCUSTDATA ...");
                TQCUSTDATA.call("N");
                logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                logger.info("Exception while call to the service is " + e.toString());
            }
            String erorCode                 = null;
            try
            {
                erorCode               = TQCUSTDATA.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {}
            if(erorCode != null && (!erorCode.equals("")))
            {
                logger.info("ErrorCode: " + erorCode);
                strData="{\"ServiceCall\":\"F\",\"ErrorCode\":"+erorCode+"}";
                return strData;
            }
            String startRowCount1   = null;
            int start1              = 0;
        
            try
            {
                startRowCount1          = TQCUSTDATA.getStringItemDef("F_ROWCONT",0,"0");
            }
            catch(Exception e)
            {}
            logger.info("startRowCount1:"+ startRowCount1);
            if(startRowCount1.equals("0") || startRowCount1.equals(""))
            {
                logger.info("ErrorCode: " + "No data found");
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
                return strData;
            }
            else
            {
                start1 = new Integer(startRowCount1.trim()).intValue();
            }
            
            String strZoneList = "[\""+strZone+"\"]";
            strZoneList = strZoneList.replaceAll("'", "");
            String strDvsnList = "";
            String strSttnList  = "";
            String strZoneList1  = "";
            
            int c=0, og=0;
            
            if(si_QryType.equals("LOCNHELP")){
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\",\"LocnData\":{";
            }
            try
            {
                //for(int i=0;i<start1;i++)
                {
                    String strTemp = "";
                    if(si_QryType.equals("LOCNHELP"))
                    {
                        String dvcnt = "", stncnt="";
                        int id = 0, is=0;
                        dvcnt  = TQCUSTDATA.getStringItemDef( "F_ROWCONT1",    0,"0").trim();
                        stncnt = TQCUSTDATA.getStringItemDef( "F_ROWCONT2",    0,"0").trim();
                        id = Integer.parseInt(dvcnt);
                        is = Integer.parseInt(stncnt);
                        strZoneList1="[";
                        strDvsnList="[";
                        strSttnList="[";
                        if(!strZone.equals(""))
                        {
                            strZone =   strZone.replace("'","");
                            if(strZone.indexOf(",") > 0)
                            {
                                String zlist[] = strZone.split(",");
                                for(int i=0;i<zlist.length;i++) {
                                    if(i>0)
                                        strZoneList1=strZoneList1+",";
                                    strZoneList1 = strZoneList1 + "\""+zlist[i]+"\"";                            
                                }
                            }
                            else
                                strZoneList1= strZoneList1 + "\""+strZone+"\"";    
                        }
                        strZoneList1=strZoneList1+"]";
                        for(int f=0; f<id;f++){
                            strTemp     = TQCUSTDATA.getStringItemDef( "F_QLFDTIME",    f,"0").trim();
                            if(f>0)
                                strDvsnList=strDvsnList+",";
                            strDvsnList = strDvsnList+"\""+strTemp.replace("#","").replace("\\","/")+"\"";
                        }
                        strDvsnList=strDvsnList+"]";
                        for(int f=0; f<is;f++){
                            strTemp     = TQCUSTDATA.getStringItemDef( "F_QUTA",    f,"0").trim();
                            if(f>0)
                                strSttnList=strSttnList+",";
                            strSttnList = strSttnList+"\""+strTemp.replace("#","").replace("\\","/").replace("\t","").replace("\r","")+"\"";
                        }
                        strSttnList=strSttnList+"]";
                       // if(i==0 || !strPrevcustid.equals(strTavcustid))
                        {
                            //if(i!=0)                           
                            strData+="\"ZONE\":"+strZoneList1+",\"DVSN\":"+strDvsnList+",\"STTN\":"+strSttnList+"}}";                            
                        }
                        /*
                        if(!strZone.equals("ZONES:"))
                        {
                            strZone = strZone.substring(0,strZone.length()-1);
                            strData+=",\"zone\":\""+strZone+"\"";
                        }
                        if(!strDvsn.equals("DIVISIONS:")) {
                            strDvsn = strDvsn.substring(0,strDvsn.length()-1);
                            strData+=",\"dvsn\":\""+strDvsn+"\"";
                        }
                        if(!strSttn.equals("STATIONS:")){
                            strSttn = strSttn.substring(0,strSttn.length()-1);
                            strData+=",\"sttn\":\""+strSttn+"\"";
                        }*/
                    }                    
                }
            }
            catch(Exception e)
            {
            logger.info("Error In getting output buffers:"+e.toString());
            }
            //strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\"}";
            
            //logger.info("stLocnData:"+strData);
            //logger.info("LOCN DATA:"+strData.substr());
            
            return strData;
    }
    
    
    public String getProfDtls(String si_custid) {
            String strData="";
            logger.info("Calling TQCUSTPROF to get Rake Commodity list");

            FOISTuxedo TQCUSTPROF = null;
            try
            {
                    TQCUSTPROF = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object");
            }

            try
            {
                TQCUSTPROF.tuxInit("TQCUSTPROF");
                TQCUSTPROF.setString("F_CUSTEMAL",       0, si_custid.trim());
            }
            catch(Exception e)
            {
                    //logger.info"Error in Servlet : " + e);
            }
            try
            {
                    logger.info("Calling Tuxedo Service TQCUSTPROF ...");
                    TQCUSTPROF.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.info("Exception while call to the service is " + e.toString());
            }
            String erorCode                 = null;
            try
            {
                    erorCode               = TQCUSTPROF.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {}
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    strData="{\"ServiceCall\":\"F\",\"ErrorCode\":"+erorCode+"}";
                    return strData;
            }            
            String startRowCount1   = null;
            int start1              = 0;

            try
            {
                    startRowCount1          = TQCUSTPROF.getStringItemDef("F_ROWCONT",0,"0");
            }
            catch(Exception e)
            {}
            logger.info("startRowCount1:"+ startRowCount1);
            if(startRowCount1.equals("0") || startRowCount1.equals(""))
            {
                    logger.info("ErrorCode: " + "No data found");
                    strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
                   return strData;
            }
            else
            {
                    start1 = new Integer(startRowCount1.trim()).intValue();
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\"+start1+\"";
            }
                      si_custid            = "";
                    String si_custctgr         = "";
                    String si_custpswd         = "";
                    String si_custemalid       = "";
                    String si_custmoblnumb     = "";
                    String si_custfrstname     = "";
                    String si_custlastname     = "";
                    String si_custgndr         = "";
                    String si_custaddr         = "";
                    String si_custpincode      = "";
                    String si_custscndmailid   = "";
                    String si_custscndmoblnumb = "";
                    String si_custidnumb1      = "";
                    String si_custiddocid1     = "";
                    String si_custidnumb2      = "";
                    String si_custiddocid2     = "";
                    String si_custdob          = "";
                    String si_custapvldate     = "";
                    String si_custuserid       = "";
                    String si_rolecode         = "";
                    String si_dgtlsignflag     = "";
                    String si_efctfrom         = "";
                    String si_efctto           = "";
                    String si_custidnumb3      = "";
                    String si_custiddocid3     = "";
                    String si_ffsaflag         = "";
                       
           
            try
            {
                for(int i=0;i<start1;i++)
                {
                      
                       
                        si_custid              =   TQCUSTPROF.getStringItemDef(  "F_CYCLID"       , i,  "0").trim();   
                                            si_custctgr         =   TQCUSTPROF.getStringItemDef(  "F_CTGR"         , i,  "0").trim();
                                            si_custpswd         =   TQCUSTPROF.getStringItemDef(  "F_OLDPSWD"      , i,  "0").trim();
                                            si_custemalid       =   TQCUSTPROF.getStringItemDef(  "F_SQNCID"       , i,  "0").trim();
                                            si_custmoblnumb     =   TQCUSTPROF.getStringItemDef(  "F_CUSTEMAL"     , i,  "0").trim();
                                            si_custfrstname     =   TQCUSTPROF.getStringItemDef(  "F_CUSTNAME"     , i,  "0").trim();
                                            si_custlastname     =   TQCUSTPROF.getStringItemDef(  "F_CYCLNUMB"     , i,  "0").trim();
                                            si_custgndr         =   TQCUSTPROF.getStringItemDef(  "F_GTOPCONT"     , i,  "0").trim();
                                            si_custaddr         =   TQCUSTPROF.getStringItemDef(  "F_DDMESG"       , i,  "0").trim();
                                            si_custpincode      =   TQCUSTPROF.getStringItemDef(  "F_CUSTADDR"     , i,  "0").trim();
                                            si_custscndmailid   =   TQCUSTPROF.getStringItemDef(  "F_CRNTLOADID"   , i,  "0").trim();
                                            si_custscndmoblnumb =   TQCUSTPROF.getStringItemDef(  "F_CUSTPHNO"     , i,  "0").trim();
                                            si_custidnumb1      =   TQCUSTPROF.getStringItemDef(  "F_CMDTFLAG"       , i,  "0").trim();
                                            si_custiddocid1     =   TQCUSTPROF.getStringItemDef(  "F_DESC"         , i,  "0").trim();
                                            si_custidnumb2      =   TQCUSTPROF.getStringItemDef(  "F_CREWID"       , i,  "0").trim();
                                            si_custiddocid2     =   TQCUSTPROF.getStringItemDef(  "F_DLVYNUMB"     , i,  "0").trim();
                                            si_custdob          =   TQCUSTPROF.getStringItemDef(  "F_DLVYDATE"     , i,  "0").trim();
                                            si_custapvldate     =   TQCUSTPROF.getStringItemDef(  "F_DMNDFNOTDATE" , i,  "0").trim();
                                            si_custuserid       =   TQCUSTPROF.getStringItemDef(  "F_CDFDRMRK"       , i,  "0").trim();
                                            si_rolecode         =   TQCUSTPROF.getStringItemDef(  "F_ROLEID"       , i,  "0").trim();
                                            si_dgtlsignflag     =   TQCUSTPROF.getStringItemDef(  "F_DMNDAUTHNUMB" , i,  "0").trim();
                                            si_efctfrom         =   TQCUSTPROF.getStringItemDef(  "F_RSTNRMVLTIME" , i,  "0").trim();
                                            si_efctto           =   TQCUSTPROF.getStringItemDef(  "F_SCHDDATE"     , i,  "0").trim();
                                            si_custidnumb3      =   TQCUSTPROF.getStringItemDef(  "F_SESNNUMB"     , i,  "0").trim();
                                            si_custiddocid3     =   TQCUSTPROF.getStringItemDef(  "F_SGID"         , i,  "0").trim();
                                            si_ffsaflag         =   TQCUSTPROF.getStringItemDef(  "F_STCKFLAG"     , i,  "0").trim();
                        
                                            if(i>0)
                                                strData+=",";
                        strData+=",\"custid\":\""+si_custid+"\",\"custctgr\":\""+si_custctgr+"\",\"custpswd\":\""+si_custpswd+"\",\"custemalid\":\""+si_custemalid+"\",\"custmoblnumb\":\""+si_custmoblnumb+"\",\"custfrstname\":\""+si_custfrstname+"\",\"custlastname\":\""+si_custlastname+"\",\"custgndr\":\""+si_custgndr+"\",\"custaddr\":\""+si_custaddr+"\",\"custpincode\":\""+si_custpincode+"\",\"custscndmailid\":\""+si_custscndmailid+"\",\"custscndmoblnumb\":\""+si_custscndmoblnumb+"\",\"custidnumb1\":\""+si_custidnumb1+"\",\"custiddocid1\":\""+si_custiddocid1+"\",\"custiddocid2\":\""+si_custiddocid2+"\",\"custidnumb2\":\""+si_custidnumb2+"\",\"custdob\":\""+si_custdob+"\",\"custapvldate\":\""+si_custapvldate+"\",\"custuserid\":\""+si_custuserid+"\",\"rolecode\":\""+si_rolecode+"\",\"dgtlsignflag\":\""+si_dgtlsignflag+"\",\"efctfrom\":\""+si_efctfrom+"\",\"efctto\":\""+si_efctto+"\",\"custidnumb3\":\""+si_custidnumb3+"\",\"custiddocid3\":\""+si_custiddocid3+"\",\"ffsaflag\":\""+si_ffsaflag+"\""; 
                   
               } 
                  strData+="}"; 
               }        
               catch(Exception e) 
              {
                       logger.info("Error In getting output buffers:"+e.toString()); 
                  }
                               //strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\"}";
            
            logger.info("strData:"+strData);
            return strData;
    }
    public String closeSecCustData(String si_prmcustid,String si_Cust, String strrqst ){
        String result = "";
        FOISTuxedo TTSCUSTSAVE = null;                                                   
         try                                                                             
         {                                                                               
             TTSCUSTSAVE = new FOISTuxedo();                                              
         }                                                                               
         catch (Exception ne)                                                            
         {                                                                               
             logger.info("Unable to get the Client Object");                             
         }                                                                               
        logger.info("strrqst:"+strrqst+":");
       try                                                                             
         {  
             logger.info("strrqst:"+strrqst+":");
             
            String a[]=strrqst.trim().split("#"); 
           // logger.info(a[0]+":"+a[1]+":"+a[2]+":"+a[3]+":"+a[4]+":"+a[5]+":"+a[6]+":"+a[7]+":"+a.length);
               String strPrmOrg=a[0];
             String strSecOrg=a[1];
             strSecOrg=strSecOrg.substring(0,strSecOrg.indexOf("("));
             String strSecId=a[3];
             String strMob=a[4];
             String strEmail=a[5];
             String strChrgType=a[6];
           logger.info("strChrgType:"+strChrgType+":");
             if(strChrgType.equals("DEMURRAGE")  )    
                 strChrgType="D";
             if(strChrgType.equals("WHARFAGE")  )    
                 strChrgType="W";
             if(strChrgType.equals("LOCAL")  )    
                 strChrgType="L";
           String strLocn="";
             String strZLocn=a[7];
             String strDLocn=a[8];
             String strSLocn=a[9];
             String strDfrom=a[10];
             if(!strZLocn.equals(""))
                 strLocn="ZONES:"+strZLocn;
             if(!strDLocn.equals(""))
                 strLocn="DIVISIONS:"+strDLocn;
             if(!strSLocn.equals(""))
                 strLocn="STATIONS:"+strSLocn;
             TTSCUSTSAVE.tuxInit("TTSCUSTSAVE");
             //logger.info("strSttn:"+strSttn+",strDvsn:"+strDvsn+",strZone:"+strZone+":");
             TTSCUSTSAVE.setString("F_PRTFID",    0, si_prmcustid);                          
             TTSCUSTSAVE.setString("F_CLNTID",        0, "SYSTEM");                    
             TTSCUSTSAVE.setString("F_USERID",        0, "SYSTEM");
             TTSCUSTSAVE.setString("F_ZONLICFLAG",     0,"C".trim());
             TTSCUSTSAVE.setString("F_OPTN",        0,"W".trim());
             
             int j=0;
             TTSCUSTSAVE.setString("F_CUSTCODE",  j, si_Cust); //si_Cust);
             TTSCUSTSAVE.setString("F_HBSPSTTNTO",   j, strPrmOrg);   
             TTSCUSTSAVE.setString("F_PAIDFRGT",     j, strSecOrg);
             TTSCUSTSAVE.setString("F_ENTYDOMN",     j, strChrgType);
              TTSCUSTSAVE.setString("F_ICLECO",         j, strSecId); 
             logger.info("strLocn:"+strLocn+":");
              TTSCUSTSAVE.setString("F_FITTIME",         j, strLocn);       
               TTSCUSTSAVE.setString("F_FCSTLOCO",     j, strDfrom);
               TTSCUSTSAVE.setString("F_NRMLAMNT",     j, "");
                 j++;
             logger.info("j:"+j);
             TTSCUSTSAVE.setString("F_ROWCONT",   0, j+"");
         }                                                                               
         catch(Exception e)                                                              
         {            
             e.printStackTrace();
            logger.info("Error in setstring : " + e.toString());                                        
         }                                                                               
         try                                                                             
         {                                                                               
             logger.info("Calling Tuxedo Service TTSCUSTSAVE ...");                       
             TTSCUSTSAVE.call("N");                                                       
             logger.info("CALL COMPLETED !");                                            
         }                                                                               
         catch(Exception e)                                                              
         {                                                                               
             logger.info("Exception while call to the service is " + e.toString());      
         }                                                                               
         String erorCode                 = null;                                         
         try                                                                             
         {                                                                               
             erorCode               = TTSCUSTSAVE.getStringItemDef("F_ERORNO",0,"0");     
         }                                                                               
         catch(Exception e)                                                              
         {
             e.printStackTrace();
         }                                                                              
         if(!erorCode.equals("SUCCESS"))                                  
         {                                                                               
             logger.info("ErrorCode: " + erorCode);                                      
             result="{\"ServiceCall\":\"F\",\"ErrorCode\":\""+erorCode+"\"}";               
             return result;                                                             
         }
         else
         {
             try{
                 String  ss = TTSCUSTSAVE.getStringItemDef("F_DDMESG",0,"0");     
                result="{\"ServiceCall\":\"S\",\"response\":\""+ss+"\"}";
             }
             catch(Exception e){
                 e.printStackTrace();
                 logger.info("in catch2:"+e.toString());
             }
         }
         logger.info("strData"+result+":");
        return result;
    }
    public String getSecCustData(String si_prmcustid, String si_cust){
        String strData="";
        logger.info("Calling TQCUSTDATA to get TQCUSTDATA list");

        FOISTuxedo TQCUSTDATA = null;
        try
        {
                TQCUSTDATA = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object");
        }

        try
        {
            TQCUSTDATA.tuxInit("TQCUSTDATA");
            TQCUSTDATA.setString("F_PRTFID",       0, si_prmcustid);
            TQCUSTDATA.setString("F_OPTN",       0, "SECCUST");
            TQCUSTDATA.setString("F_CUSTCODE",       0, si_cust);
        }
        catch(Exception e)
        {
                //logger.info"Error in Servlet : " + e);
        }
        try
        {
                logger.info("Calling Tuxedo Service TQCUSTDATA ...");
                TQCUSTDATA.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.info("Exception while call to the service is " + e.toString());
        }
        String erorCode                 = "";
        try
        {
                erorCode               = TQCUSTDATA.getStringItemDef("F_ERORNO",0,"0");
        }
        catch(Exception e)
        {
                logger.info("errorcode exception:"+e.toString());
            }
        if(erorCode != null && (!erorCode.equals("")))
        {
                logger.info("ErrorCode: " + erorCode);
                strData="{\"ServiceCall\":\"F\",\"ErrorCode\":"+erorCode+"}";
                return strData;
        }            
        String startRowCount1   = null;
        int start1              = 0;

        try
        {
                startRowCount1          = TQCUSTDATA.getStringItemDef("F_ROWCONT",0,"0");
        }
        catch(Exception e)
        {}
        logger.info("startRowCount1:"+ startRowCount1);
        if(startRowCount1.equals("0") || startRowCount1.equals(""))
        {
                logger.info("ErrorCode: " + "No data found");
                strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"N\"}";
               return strData;
        }
        else
        {
                start1 = new Integer(startRowCount1.trim()).intValue();
        }
        String strPrmCustId   = "" ;
        String strPrmOrg     = "" ;
        String strSecCustId = "" ;
        String strSecOrgCode = "" ;
        String strLocnflag   = "" ;
        String strLocn = "" ;
        String strDateFrom = ""; 
        String strDateTo = "";
        String strChrgType = ""; 
        String strEmailId = "";
        String strCustMobNum = "";
        
        String custaddr       = "";
        String custpincode    = "";
        String strZone = "";
        String strDvsn = "";
        String strSttn  = "";
        String strGlblCustName = "";
        String strPrev = "";
        String strCurrent="";
        String strSecname= "";
        String strPrevcustid = "";
        int c=0, og=0;
        strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\",\"SecCustData\":[";
        
        try
        {
            for(int i=0;i<start1;i++)
            {
                    strPrmCustId    =  TQCUSTDATA.getStringItemDef("F_CREWID",      i,"0").trim();
                    strPrmOrg       =  TQCUSTDATA.getStringItemDef("F_COTRNAME",    i,"0").trim();
                    strSecCustId    =  TQCUSTDATA.getStringItemDef("F_CRNTLOADID", i,"0").trim();
                    strSecOrgCode   =  TQCUSTDATA.getStringItemDef("F_DSGNDESC",    i,"0").trim();
                    strSecname      =  TQCUSTDATA.getStringItemDef("F_ORIGSTTNFROM",    i,"0").trim();
                    strLocnflag     =  TQCUSTDATA.getStringItemDef("F_DUMYDMNDFLAG",    i,"0").trim();
                    strLocn         = TQCUSTDATA.getStringItemDef( "F_DVSNTO",      i,"0").trim();
                    strChrgType     = TQCUSTDATA.getStringItemDef( "F_EVNTFLAG",        i,"0").trim();
                    strDateFrom     = TQCUSTDATA.getStringItemDef( "F_DATEFROM",   i,"0").trim();
                    strDateTo       = TQCUSTDATA.getStringItemDef( "F_DATETO",    i,"0").trim();
                    strEmailId      = TQCUSTDATA.getStringItemDef( "F_EM",    i,"0").trim();
                    strCustMobNum   = TQCUSTDATA.getStringItemDef( "F_MODE",    i,"0").trim();
                    strCurrent = strPrmOrg+"-"+strSecCustId+"-"+strSecOrgCode+"-"+strDateFrom+"-"+strDateTo;
                    if(i>0)
                        strData+=",";
                     strData+="{\"PrmOrg\":\""+strPrmOrg+"\",\"SecCustId\":\""+strSecCustId+"\",\"SecOrgCode\":\""+strSecOrgCode+"\",\"SecName\":\""+strSecname+"\",\"ChrgType\":\""+strChrgType+"\",\"DateFrom\":\""+strDateFrom+"\",\"DateTo\":\""+strDateTo+"\",\"EmailId\":\""+strEmailId+"\",\"CustMobNum\":\""+strCustMobNum+"\","; //[{"; 
                     if(strLocnflag.equals("Z"))
                        strZone= "\"ZONES\":\""+strLocn+"\"";
                    else
                        strZone= "\"ZONES\":\"\"";
                     if(strLocnflag.equals("D"))
                         strDvsn= ",\"DVSN\":\""+strLocn+"\"";
                    else
                        strDvsn= ",\"DVSN\":\"\"";
                    if(strLocnflag.equals("S"))
                         strSttn= ",\"STATIONS\":\""+strLocn+"\"";
                    else
                        strSttn= ",\"STATIONS\":\"\"";
                    strData+=strZone+strDvsn+strSttn +"}";
                   /* if(i==0 || !strPrev.equals(strCurrent))
                    {
                        if(i==0)
                        {
                                 strPrev = strCurrent;
                                 strData+="\"},";
                                 strZone= "\"ZONES\":\"";
                                 strDvsn="\",\"DVSN\":\"";
                                 strSttn="\",\"STATIONS\":\"";
                        }
                        if(i!=0)
                        {
                                   if(! strZone.equals("\"ZONES\":\""))// if(!strZone.equals(""))
                                        strData+=strZone;
                                    else strData+="\"ZONES\":\""; //added on 18-08-2021
                                    if(!strDvsn.equals("\",\"DVSN\":\""))
                                        strData+=strDvsn;
                                    else strData+="\",\"DVSN\":\""; //added on 18-08-2021
                                    if(!strSttn.equals("\",\"STATIONS\":\""))
                                        strData+=strSttn;
                                    else strData+="\",\"STATIONS\":\"\""; //added on 18-08-2021
                                    strData+="\"},";
                                    strZone= "\"ZONES\":\"";
                                    strDvsn="\",\"DVSN\":\"";
                                    strSttn="\",\"STATIONS\":\"";
                        }
                            
                        /*commented on 18-08-2021 to display separate heders for zone, dvsn, station--starts---*
                         strData+="{\"PrmOrg\":\""+strPrmOrg+"\",\"SecCustId\":\""+strSecCustId+"\",\"SecOrgCode\":\""+strSecOrgCode+"\",\"ChrgType\":\""+strChrgType+"\",\"DateFrom\":\""+strDateFrom+"\",\"DateTo\":\""+strDateTo+"\",\"EmailId\":\""+strEmailId+"\",\"CustMobNum\":\""+strCustMobNum+"\",\"Locn\":\""; //[{"; 
                        /*commented on 18-08-2021 to display separate heders for zone, dvsn, station--ends---*
                        strData+="{\"PrmOrg\":\""+strPrmOrg+"\",\"SecCustId\":\""+strSecCustId+"\",\"SecOrgCode\":\""+strSecOrgCode+"\",\"ChrgType\":\""+strChrgType+"\",\"DateFrom\":\""+strDateFrom+"\",\"DateTo\":\""+strDateTo+"\",\"EmailId\":\""+strEmailId+"\",\"CustMobNum\":\""+strCustMobNum+"\","; //[{"; 
                        
                     }
                      if(strLocnflag.equals("Z")) 
                              { 
                                      if(!(strPrev).equals(strCurrent) || i==0)                           
                                           strZone += strLocn; // changed on 18-08-2021 strZone = "ZONES:"+strLocn;
                                      else 
                                           strZone += ","+strLocn;
                              }                          
                      if(strLocnflag.equals("D")) 
                              { 
                                     // if(!(strPrev).equals(strCurrent) || i==0)
                                     if((strDvsn).equals("\",\"DVSN\":\""))
                                      {                  
                                         /*commented on 18-08-2021 ---starts---*
                                          if(strZone.equals(""))
                                              strDvsn = "DVSN:"+strLocn;
                                          else                                         
                                              strDvsn = " | DVSN:"+strLocn;
                                         /*commented on 18-08-2021 ---ends---*
                                         strDvsn += strLocn;
                                      }
                                      else
                                        strDvsn += ","+strLocn;
                              }          
                      if(strLocnflag.equals("S"))
                          {
                                  //if(!(strPrev).equals(strCurrent) || i==0)
                                  if((strSttn).equals("\",\"STATIONS\":\""))
                                  {                  
                                     /*commented on 18-08-2021 ---starts---*
                                      if(strZone.equals("") &&strDvsn.equals("") )
                                          strSttn = "STATIONS:"+strLocn;
                                      else
                                         strSttn = " | STATIONS:"+strLocn;
                                     /*commented on 18-08-2021 ---ends---*
                                     strSttn += strLocn;
                                  }      
                                  else 
                                       strSttn += ","+strLocn;
                              }
                           strPrev = strCurrent; */
                 } //for end
              /*commented on 18-08-2021 to display separate heders for zone, dvsn, station--starts--*
                     if(!strZone.equals("\"ZONES\":\""))
                                strData+=strZone+"\"";
                     else strData+="\"";//ZONES\":\"\""; //added on 18-08-2021
                     if(!strDvsn.equals("\",\"DVSN\":\""))
                                strData+=strDvsn+"\"";
                     else strData+=",\"DVSN\":\"\""; //added on 18-08-2021
                     if(!strSttn.equals("\",\"STATIONS\":\""))
                                strData+=strSttn;
                    else strData+=",\"STATIONS\":\""; //added on 18-08-2021
                    
              /*commented on 18-08-2021 to display separate heders for zone, dvsn, station  ----ends --*/
                      //strData+="\"ZONES\":\""+strZone+"\",\"DVSN\":\""+strDvsn+"\",\"STATIONS\":\""+strSttn+"\""+"\"}]}";
                    //  strData+="\"}]}";
                    //strData+="{\"Custuserid\":\""+strCustuserid+"\",\"Custctgr\":\""+strCustctgr+"\",\"Custfrstname\":\""+strCustfrstname+"\",\"Custlastname\":\""+strCustlastname+"\",\"Custemalid\":\""+strCustemalid+"\",\"Custmoblnumb\":\""+strCustmoblnumb+"\",\"Tavcustid\":\""+strTavcustid+"\",\"custaddr\":\""+custaddr+"\",:\"org\":\""+strGlblCustName+"\",\"zone\":\""+strZone+"\",\"dvsn\":\""+strDvsn+"\",\"sttn\":\""+strSttn+"\"}";           
                    strData+="]}";
        }
        catch(Exception e)
        {
                logger.info("Error In getting output buffers:"+e.toString());
        }
        //strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RowCount\":\""+start1+"\"}";
        
        logger.info("strData:"+strData);
        return strData;
    }
    
    public RgstSecCustUtil() {
        super();
    }
}

