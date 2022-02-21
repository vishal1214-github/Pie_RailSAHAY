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

import util.logger.FoisLogger;

public class FSH_FrgtCalcUtil extends HttpServlet
{
    static Logger logger = FoisLogger.getLogger(FSH_FrgtCalcUtil.class.getName());
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
          //  HttpSession session = req.getSession();
        
            String si_QryType     = req.getParameter("Qry").toUpperCase().trim();       
            String si_cmdt        = req.getParameter("cmdt").toUpperCase().trim();    
        String si_SttnFrom  =   "";
        String si_SttnTo    =   "";
        logger.info("si_cmdt:"+si_cmdt);       
        logger.info("si_QryType:"+si_QryType);       
        
            if(si_QryType.equals("WGON_LIST"))
            {
                si_SttnFrom    = req.getParameter("sttnfrom").toUpperCase().trim();            
                si_SttnTo      = req.getParameter("sttnto").toUpperCase().trim();     
            try
            {
                if(si_SttnFrom.indexOf("-") != -1)
                        si_SttnFrom                              =       si_SttnFrom.substring(si_SttnFrom.lastIndexOf("-")+1,si_SttnFrom.lastIndexOf("(")).trim();
                if(si_SttnTo.indexOf("-") != -1)
                        si_SttnTo                                =       si_SttnTo.substring(si_SttnTo.lastIndexOf("-")+1,si_SttnTo.lastIndexOf("(")).trim();
            }
            catch(Exception e){}
            logger.info("si_SttnFrom:"+si_SttnFrom);       
            logger.info("si_SttnTo:"+si_SttnTo);    
            }
        String resData="";

            if(si_QryType.equals("WGON_LIST"))
            {
                    logger.info("Call for Wagon List");
                    resData=this.getWgonTypeList(si_SttnFrom,si_SttnTo,si_cmdt,si_QryType);
            }
            if(si_QryType.equals("RAKE_CMDT"))
            {
                    logger.info("Call for RAKE_CMDT");
                    resData=this.getRakeCmdt(si_cmdt,si_QryType);
            }

            else if(si_QryType.equals("STTNFROM_LIST"))
            {
                    logger.info("Call for Station From List");
                    resData=this.getSttnList(si_cmdt,si_QryType);
                    //resData=this.getSttnFromList(si_cmdt,si_QryType);
            }
            else if(si_QryType.equals("STTNTO_LIST"))
            {
                    logger.info("Call for Station To List");
                    //resData=this.getSttnFromList(si_cmdt,si_QryType);
                    resData=this.getSttnList(si_cmdt,si_QryType);
            }
            else if(si_QryType.equals("CMDT_LIST"))
            {
                    logger.info("Call for Commodity List");
                    //resData=this.getSttnFromList(si_cmdt,si_QryType);
                    resData=this.getSttnList(si_cmdt,si_QryType);
            }
            else if(si_QryType.equals("SCHM_LIST"))
            {
                    logger.info("Call for Scheme List");
                    resData=this.getSchmList(si_cmdt,si_QryType);
            }
            
            logger.info("Sending JSON Data As:"+resData);
            res.getWriter().write(resData);
    }
    public String getRakeCmdt(String si_cmdt,String si_QryType) {
        String strData="";
        logger.info("Calling TQMISCHELP to get Rake Commodity list");

        FOISTuxedo TQMISCHELP = null;
        try
        {
                TQMISCHELP = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object");
        }

        try
        {
            TQMISCHELP.tuxInit("TQMISCHELP");
            TQMISCHELP.setString("F_CMDT",       0, si_cmdt.trim());
            TQMISCHELP.setString("F_USERID",     0, "CRIS1");
            TQMISCHELP.setString("F_CLNTID",     0, "CRIS1");
            TQMISCHELP.setString("F_FLAG",       0, "RCT");
        }
        catch(Exception e)
        {
                //logger.info"Error in Servlet : " + e);
        }
        try
        {
                logger.info("Calling Tuxedo Service TQMISCHELP ...");
                TQMISCHELP.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.info("Exception while call to the service is " + e.toString());
        }
        String erorCode                 = null;
        try
        {
                erorCode               = TQMISCHELP.getStringItemDef("F_ERORNO",0,"0");
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
                startRowCount1          = TQMISCHELP.getStringItemDef("F_ROWCONT",0,"0");
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
        String strRakeCmdt="";
        try
        {
            strRakeCmdt = TQMISCHELP.getStringItemDef("F_CMDTCODE", 1,"0").trim();
        }
        catch(Exception e)
        {
                logger.info("Error In getting output buffers:"+e.toString());
        }
        strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"RakeCmdt\":\""+strRakeCmdt+"\"}";
        
        logger.info("strRakeCmdt:"+strRakeCmdt);
        return strData;
    }
    public String getSttnFromList(String si_cmdt,String si_QryType) {
        String strData="";
        logger.info("Calling TQMISCHELP for Station help list");

        FOISTuxedo TQMISCHELP = null;
        try
        {
                TQMISCHELP = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object");
        }

        try
        {
            TQMISCHELP.tuxInit("TQMISCHELP");
            TQMISCHELP.setString("F_CMDT",       0, si_cmdt.trim());
            TQMISCHELP.setString("F_USERID",     0, "CRIS1");
            TQMISCHELP.setString("F_CLNTID",     0, "CRIS1");
            if(si_QryType.equals("STTNFROM_LIST"))
                TQMISCHELP.setString("F_FLAG",       0, "SF");
            else if(si_QryType.equals("CMDT_LIST"))
                TQMISCHELP.setString("F_FLAG",       0, "GC");
            else
                TQMISCHELP.setString("F_FLAG",       0, "ST");
        }
        catch(Exception e)
        {
                //logger.info"Error in Servlet : " + e);
        }
        try
        {
                logger.info("Calling Tuxedo Service TQMISCHELP ...");
                TQMISCHELP.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.info("Exception while call to the service is " + e.toString());
        }
        String erorCode                 = null;
        try
        {
                erorCode                                = TQMISCHELP.getStringItemDef("F_ERORNO",0,"0");
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
                startRowCount1          = TQMISCHELP.getStringItemDef("F_ROWCONT",0,"0");
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
        String strSttn="";
        String strArry="";//"[";
        
        //StringBuffer str = new StringBuffer();
        for(int k=0; k<start1; k++)
        {
            try
                {
                    strSttn = TQMISCHELP.getStringItemDef("F_STTNFROM", k,      "0").trim();
                    strArry+="<option value='"+strSttn+"' />";
                  /*      if(k==0)
                                strArry+="{\"Sttn\":\""+strSttn+"\"}";
                        else
                                strArry+=",{\"Sttn\":\""+strSttn+"\"}";*/
                }
                catch(Exception e)
                {
                        logger.info("Error In getting output buffers:"+e.toString());
                }
        } //
       // strArry+="]";
        strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":\""+strArry+"\"}";
        
        logger.info("strArry:"+strArry);
        return strData;
    
    }
    public String getSttnList(String si_cmdt,String si_QryType) {
        String strData="";
        logger.info("Calling TQMISCHELP for Station help list");

        FOISTuxedo TQMISCHELP = null;
        try
        {
                TQMISCHELP = new FOISTuxedo();
        }
        catch (Exception ne)
        {
                logger.info("Unable to get the Client Object");
        }

        try
        {
            TQMISCHELP.tuxInit("TQMISCHELP");
            TQMISCHELP.setString("F_CMDT",       0, si_cmdt.trim());
            TQMISCHELP.setString("F_USERID",     0, "CRIS1");
            TQMISCHELP.setString("F_CLNTID",     0, "CRIS1");
            if(si_QryType.equals("STTNFROM_LIST"))
                TQMISCHELP.setString("F_FLAG",       0, "SF");
            else if(si_QryType.equals("CMDT_LIST"))
                TQMISCHELP.setString("F_FLAG",       0, "GC");
            else
                TQMISCHELP.setString("F_FLAG",       0, "ST");
        }
        catch(Exception e)
        {
                //logger.info"Error in Servlet : " + e);
        }
        try
        {
                logger.info("Calling Tuxedo Service TQMISCHELP ...");
                TQMISCHELP.call("N");
                logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
                logger.info("Exception while call to the service is " + e.toString());
        }
        String erorCode                 = null;
        try
        {
                erorCode                                = TQMISCHELP.getStringItemDef("F_ERORNO",0,"0");
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
                startRowCount1          = TQMISCHELP.getStringItemDef("F_ROWCONT",0,"0");
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
        String strSttn="";
        String strArry="";//"[";
        
        //StringBuffer str = new StringBuffer();
        for(int k=0; k<start1; k++)
        {
            try
                {
                    strSttn = TQMISCHELP.getStringItemDef("F_STTNFROM", k,      "0").trim();
                strArry+=strSttn+"@";
                    //strArry+="<option value='"+strSttn+"' />";
                  /*      if(k==0)
                                strArry+="{\"Sttn\":\""+strSttn+"\"}";
                        else
                                strArry+=",{\"Sttn\":\""+strSttn+"\"}";*/
                }
                catch(Exception e)
                {
                        logger.info("Error In getting output buffers:"+e.toString());
                }
        } 
        strArry=strArry.substring(0,strArry.length()-1);
        strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":\""+strArry+"\"}";
        
       // strArry+="]";
        
        logger.info("strArry:"+strArry);
        return strData;
    
    }

    public String getWgonTypeList(String si_SttnFrom,String si_SttnTo,String si_cmdt,String si_QryType)
    {
            String strData="";
            logger.info("Calling TQPREFRGTCALC for wagon list)");

            FOISTuxedo TQPREFRGTCALC = null;
            try
            {
                    TQPREFRGTCALC = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object");
            }

            try
            {
                Date date = Calendar.getInstance().getTime();
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = formatter.format(date);
                logger.info("Today : " + strDate);

                TQPREFRGTCALC.tuxInit("TQPREFRGTCALC");
                
                TQPREFRGTCALC.setString("F_DATE",       0, strDate.trim());//java.time.LocalTime.now()+"");
                TQPREFRGTCALC.setString("F_STTNFROM",   0, si_SttnFrom.trim());
                TQPREFRGTCALC.setString("F_STTNTO",     0, si_SttnTo.trim());
                TQPREFRGTCALC.setString("F_CMDT",       0, si_cmdt.trim());
                TQPREFRGTCALC.setString("F_WGONTYPE",   0, "BCN");
                TQPREFRGTCALC.setString("F_USERID",     0, "CRIS1");
                TQPREFRGTCALC.setString("F_CLNTID",     0, "CRIS1");
                TQPREFRGTCALC.setString("F_FLAG",       0, "L");  //L- Calling for wagon list,R- Call from Railway ip, P- Call from Public ip
            }
            catch(Exception e)
            {
                    //logger.info"Error in Servlet : " + e);
            }
            try
            {
                    logger.info("Calling Tuxedo Service TQPREFRGTCALC ...");
                    TQPREFRGTCALC.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.info("Exception while call to the service is " + e.toString());
            }
            String erorCode                 = null;
            try
            {
                    erorCode                                = TQPREFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {}
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode: " + erorCode);
                    strData="{\"ServiceCall\":\"F\"}";
                    return strData;
            }            
            String startRowCount1   = null;
            int start1                              = 0;

            try
            {
                    startRowCount1          = TQPREFRGTCALC.getStringItemDef("F_ROWCONT1",0,"0");
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
            String strWgonCode="";
            String strWgonDesc="";
            String strPCC="";
            String strPCCUnit="";
            String strRakeSize="";
            String strMiniSize="";
            String strArry="[";
            for(int k=0; k<start1; k++)
            {
                try
                    {
                            strWgonCode             = TQPREFRGTCALC.getStringItemDef("F_HLDGZONE",            k,      "0").trim();
                            strWgonDesc             = TQPREFRGTCALC.getStringItemDef("F_NUMBSPCL",            k,      "0").trim();
                            strPCC                  = TQPREFRGTCALC.getStringItemDef("F_COTRNAME",            k,      "0").trim();
                            strPCCUnit              = TQPREFRGTCALC.getStringItemDef("F_LOCNFLAG",            k,      "0").trim();
                            strRakeSize             = TQPREFRGTCALC.getStringItemDef("F_CUSTPHNO",            k,      "0").trim();
                            strMiniSize             = TQPREFRGTCALC.getStringItemDef("F_CUSTEMAL",              k,      "0").trim();
                    
                            if(k==0)
                                    strArry+="{\"Code\":\""+strWgonCode+"\",\"Desc\":\""+strWgonDesc+"\",\"Pcc\":\""+strPCC+"\",\"PccUnit\":\""+strPCCUnit+"\",\"RakeSize\":\""+strRakeSize+"\",\"MiniSize\":\""+strMiniSize+"\"}";
                            else
                                    strArry+=",{\"Code\":\""+strWgonCode+"\",\"Desc\":\""+strWgonDesc+"\",\"Pcc\":\""+strPCC+"\",\"PccUnit\":\""+strPCCUnit+"\",\"RakeSize\":\""+strRakeSize+"\",\"MiniSize\":\""+strMiniSize+"\"}";                                         
                    }
                    catch(Exception e)
                    {
                            logger.info("Error In getting output buffers:"+e.toString());
                    }
    } //
            strArry+="]";
            strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
            
            logger.info("strArry:"+strArry);
            return strData;
    }


    public String getSchmList(String si_cmdt,String si_QryType)
    {
            String strData="";
            logger.info("Calling RQSAHAYPRTLCMDT for Scheme list for commodity :"+si_cmdt+": and QueryType :"+si_QryType+":");

            FOISTuxedo RQSAHAYPRTLCMDT = null;
            try
            {
                    RQSAHAYPRTLCMDT = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                    logger.info("Unable to get the Client Object");
            }

            try
            {

                RQSAHAYPRTLCMDT.tuxInit("RQSAHAYPRTLCMDT");
                
                RQSAHAYPRTLCMDT.setString("F_HLDGZONE",     1, si_cmdt);
                RQSAHAYPRTLCMDT.setString("F_HLDGZONE",     0, "CMDTNAME");
                RQSAHAYPRTLCMDT.setString("F_FLAG",         0, "CMDT_SCHM");
                RQSAHAYPRTLCMDT.setString("F_USERID",       0, "CRIS1");
                RQSAHAYPRTLCMDT.setString("F_CLNTID",       0, "CRIS1");
            }
            catch(Exception e)
            {
                    //logger.info"Error in Servlet : " + e);
            }
            try
            {
                    logger.info("Calling Tuxedo Service RQSAHAYPRTLCMDT ...");
                    RQSAHAYPRTLCMDT.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.info("Exception while call to the service is " + e.toString());
            }
            logger.info("Input buffers set");
            String erorCode                 = null;
            try
            {
                    erorCode                = RQSAHAYPRTLCMDT.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {}
            if(erorCode != null && (!erorCode.equals("")))
            {
                    logger.info("ErrorCode from RQSAHAYPRTLCMDT : " + erorCode);
                    strData="{\"ServiceCall\":\"F\", \"ErrorCode\":\""+erorCode+"\"}";
                    return strData;
            }            
            String startRowCount1   = null;
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTLCMDT.getStringItemDef("F_ROWCONT",0,"0");
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
            String strSchmCode="";
            String strSchmDesc="";
            String MstrCirNo="";
            String MstrCirDate="";
            String MstrCirPDF="";
            String CrntCirNo="";
            String CrntCirDate="";
            String CrntCirPDF="";
            String strArry="[";
            for(int k=0; k<start1; k++)
            {
                try
                    {
                            strSchmCode             = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY1",            k,      "0").trim();
                            strSchmDesc             = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY2",            k,      "0").trim();
                            MstrCirNo               = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY3",            k,      "0").trim();
                            MstrCirDate             = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY4",            k,      "0").trim();
                            MstrCirPDF              = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY5",            k,      "0").trim();
                            CrntCirNo               = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY6",            k,      "0").trim();
                            CrntCirDate             = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY7",            k,      "0").trim();
                            CrntCirPDF              = RQSAHAYPRTLCMDT.getStringItemDef("F_ORDRBY8",            k,      "0").trim();
                        
                            if(k==0)
                                    strArry+="{\"Code\":\""+strSchmCode+"\",\"SchmDesc\":\""+strSchmDesc+"\",\"MstrCirNo\":\""+MstrCirNo+"\",\"MstrCirDate\":\""+MstrCirDate+"\",\"MstrCirPDF\":\""+MstrCirPDF+"\",\"CrntCirNo\":\""+CrntCirNo+"\",\"CrntCirDate\":\""+CrntCirDate+"\",\"CrntCirPDF\":\""+CrntCirPDF+"\"}";
                            else
                                    strArry+=",{\"Code\":\""+strSchmCode+"\",\"SchmDesc\":\""+strSchmDesc+"\",\"MstrCirNo\":\""+MstrCirNo+"\",\"MstrCirDate\":\""+MstrCirDate+"\",\"MstrCirPDF\":\""+MstrCirPDF+"\",\"CrntCirNo\":\""+CrntCirNo+"\",\"CrntCirDate\":\""+CrntCirDate+"\",\"CrntCirPDF\":\""+CrntCirPDF+"\"}";                                         
                    }
                    catch(Exception e)
                    {
                            logger.info("Error In getting output buffers:"+e.toString());
                    }
    }
            strArry+="]";
            strData="{\"ServiceCall\":\"S\", \"DataFlag\":\"Y\",\"Data\":"+strArry+"}";
            
            logger.info("strArry:"+strArry);
            return strData;
    }
    
    public FSH_FrgtCalcUtil() {
        super();
    }
}
