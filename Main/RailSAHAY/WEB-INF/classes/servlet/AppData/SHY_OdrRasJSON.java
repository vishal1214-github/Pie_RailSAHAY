package servlet.AppData;


import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SHY_OdrRasTX;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;


public class SHY_OdrRasJSON extends HttpServlet
{
   	static Logger logger = FoisLogger.getLogger(SHY_OdrRasJSON.class.getName());
	boolean bolValid=true;
   
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		logger.info("In ODR RAS JSON ");	
          

	    HttpSession session = req.getSession(); 
	    req.setCharacterEncoding("UTF-8");
            res.setHeader("Cache-Control", "no-cache");
            String si_UserID                ="SAHAY";//(String) session.getAttribute("UserID");
            String si_ClntID                ="1.1.1.1";//(String) session.getAttribute("ClntID");
	    logger.info("si_UserID"+ si_UserID);
	    logger.info("si_ClntID"+ si_ClntID);
	    String si_Optn  = req.getParameter("Optn").trim();                
            logger.info("si_Optn"+ si_Optn);
	    String strDvsn ="";
	    String strSttn = "";
            String strDateFrom ="";
            String strDateTo="";
	    String strError="";
            String strCallStts=""; 
            String strCallMesg="";
            String strCallMesgFinal="";
            String strData="";
            
if (si_Optn.equals("RASIOSttnWiseOtsgDmnd")) /*RAS-IORE STATION WISE OUTSTANDING DEMANDS*/
    {
strDvsn = (String)req.getParameter("rasDvsn").toUpperCase().trim();
    String strClst = "";
logger.info("strDvsn"+ strDvsn);
if (strDvsn.equals("CKP"))
{

        strSttn = (String)req.getParameter("ckpSttn").toUpperCase().trim();
    strClst = (String)req.getParameter("ckpClst").toUpperCase().trim();
}
if (strDvsn.equals("KUR"))
{

        strSttn = (String)req.getParameter("kurSttn").toUpperCase().trim();
    strClst = (String)req.getParameter("kurClst").toUpperCase().trim();
}
if (strDvsn.equals("KGP"))
{

        strSttn = (String)req.getParameter("kgpSttn").toUpperCase().trim();
    strClst = (String)req.getParameter("kgpClst").toUpperCase().trim();
}
if (strDvsn.equals("ASN"))
{

        strSttn = (String)req.getParameter("asnSttn").toUpperCase().trim();
    strClst = (String)req.getParameter("asnClst").toUpperCase().trim();
}
if (strDvsn.equals("SBP"))
{

        strSttn = (String)req.getParameter("sbpSttn").toUpperCase().trim();
    strClst = (String)req.getParameter("sbpClst").toUpperCase().trim();
}
logger.info("strSttn" + strSttn);
    logger.info("strClst" + strClst);
                    
if (((strDvsn.equals(""))||(strDvsn==null)))
{
        logger.info("Division Is Mandatory");
     strCallStts="F";
    strCallMesg="Division Is Mandatory";
    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
    res.getWriter().write(strData);
    return;
}
    if (((strSttn.equals(""))||(strSttn==null)))
    {
            logger.info("Station Is Mandatory");
        strCallStts="F";
        strCallMesg="Station Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
                    
util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
bolValid=true;
bolValid=   vldt.valdInpt(strDvsn, "A");
if (bolValid==true)
{
   
}
else 
{
        logger.info("Only Alphabetic Characters  are  Allowed In Division");
    strCallStts="F";
    strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
    res.getWriter().write(strData);
    return;
}
bolValid=true;
bolValid=   vldt.valdInpt(strSttn, "A");
if (bolValid==true)
{
        
     }
     else 
     {
             logger.info("Only Alphabetic Characters  are  Allowed In Station");
         strCallStts="F";
         strCallMesg="Only Alphabetic Characters  are  Allowed In Station";
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
         res.getWriter().write(strData);
         return;
     }
                    
                    
                    
SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
try
{
    String strRASIOSttnWiseOtsgDmnd[][]=obj.getRASIOSttnWiseOtsgDmnd(si_UserID, si_ClntID,strDvsn,strSttn,strClst);
    
     strCallStts=obj.getCallStts();
     strCallMesg=obj.getCallMesg();
      strData="";
    int intDataLen=strRASIOSttnWiseOtsgDmnd.length;
    if (intDataLen>0)
    {
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
      
    for(int i=0;i<intDataLen;i++)
    {                               
            if(i==(intDataLen-1))
                    strData+="{\"clstr\":\""+strRASIOSttnWiseOtsgDmnd[i][10]+"\",\"sttnfrom\":\""+strRASIOSttnWiseOtsgDmnd[i][4]+"\",\"prty\":\""+strRASIOSttnWiseOtsgDmnd[i][0]+"\",\"subprty\":\""+strRASIOSttnWiseOtsgDmnd[i][1]+"\",\"schmcode\":\""+strRASIOSttnWiseOtsgDmnd[i][11]+"\",\"premflag\":\""+strRASIOSttnWiseOtsgDmnd[i][12]+"\",\"expldng\":\""+strRASIOSttnWiseOtsgDmnd[i][13]+"\",\"dmndtime\":\""+strRASIOSttnWiseOtsgDmnd[i][2]+"\",\"dmndno\":\""+strRASIOSttnWiseOtsgDmnd[i][3]+"\",\"csnr\":\""+strRASIOSttnWiseOtsgDmnd[i][6]+"\",\"cnsg\":\""+strRASIOSttnWiseOtsgDmnd[i][7]+"\",\"sttnto\":\""+strRASIOSttnWiseOtsgDmnd[i][5]+"\"}";
            else
                strData+="{\"clstr\":\""+strRASIOSttnWiseOtsgDmnd[i][10]+"\",\"sttnfrom\":\""+strRASIOSttnWiseOtsgDmnd[i][4]+"\",\"prty\":\""+strRASIOSttnWiseOtsgDmnd[i][0]+"\",\"subprty\":\""+strRASIOSttnWiseOtsgDmnd[i][1]+"\",\"schmcode\":\""+strRASIOSttnWiseOtsgDmnd[i][11]+"\",\"premflag\":\""+strRASIOSttnWiseOtsgDmnd[i][12]+"\",\"expldng\":\""+strRASIOSttnWiseOtsgDmnd[i][13]+"\",\"dmndtime\":\""+strRASIOSttnWiseOtsgDmnd[i][2]+"\",\"dmndno\":\""+strRASIOSttnWiseOtsgDmnd[i][3]+"\",\"csnr\":\""+strRASIOSttnWiseOtsgDmnd[i][6]+"\",\"cnsg\":\""+strRASIOSttnWiseOtsgDmnd[i][7]+"\",\"sttnto\":\""+strRASIOSttnWiseOtsgDmnd[i][5]+"\"},";
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="No Data Found";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
           
        }
    logger.info(strData);
    res.getWriter().write(strData);
}
catch(Exception e)
{
logger.info("Error In Data");
    strCallStts="F";
    strCallMesgFinal="Error In Data "+strCallMesg;
    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
}

}
if (si_Optn.equals("RASRakeAlcnPlan")) /*RAS-IORE        RAKE ALLOCATION PLAN*/
    {
              strDvsn = (String)req.getParameter("Dvsn").toUpperCase();
                    logger.info("strDvsn"+ strDvsn);
                     strDateFrom = (String)req.getParameter("dateFrom").toUpperCase();
                    logger.info("strDateFrom"+ strDateFrom);
                     strDateTo= (String)req.getParameter("dateTo").toUpperCase();
                    logger.info("strDateTo"+ strDateTo);
                    
       
                    
    if (((strDvsn.equals(""))||(strDvsn==null)))
    {
    logger.info("Fill at least Division Code or Part Full Name");
        strCallStts="F";
        strCallMesg="Fill at least Division Code or Part Full Name";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
    if (((strDateFrom.equals(""))||(strDateFrom==null)))
    {
            logger.info("Date From Is Mandatory");
        strCallStts="F";
        strCallMesg="Date From Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
    if (((strDateTo.equals(""))||(strDateTo==null)))
    {
            logger.info("Date To Is Mandatory");
        strCallStts="F";
        strCallMesg="Date To Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
                    
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(strDvsn, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Division");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
       
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
       
    try
    {
    String strRASRakeAlcnPlan[][]=obj.geRASRakeAlcnPlan(si_UserID, si_ClntID,strDvsn,strDateFrom,strDateTo);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
      
    
    int intDataLen=strRASRakeAlcnPlan.length;
    if (intDataLen>0)
    {
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {                               
            if(i==(intDataLen-1))
                    strData+="{\"date\":\""+strRASRakeAlcnPlan[i][1]+"\",\"sttnto\":\""+strRASRakeAlcnPlan[i][2]+"\",\"prty\":\""+strRASRakeAlcnPlan[i][3]+"\",\"subprty\":\""+strRASRakeAlcnPlan[i][4]+"\",\"cn\":\""+strRASRakeAlcnPlan[i][6]+"\",\"custcode\":\""+strRASRakeAlcnPlan[i][7]+"\",\"custname\":\""+strRASRakeAlcnPlan[i][8]+"\",\"odrdtls\":\""+strRASRakeAlcnPlan[i][10]+"\"}";
            else
                    strData+="{\"date\":\""+strRASRakeAlcnPlan[i][1]+"\",\"sttnto\":\""+strRASRakeAlcnPlan[i][2]+"\",\"prty\":\""+strRASRakeAlcnPlan[i][3]+"\",\"subprty\":\""+strRASRakeAlcnPlan[i][4]+"\",\"cn\":\""+strRASRakeAlcnPlan[i][6]+"\",\"custcode\":\""+strRASRakeAlcnPlan[i][7]+"\",\"custname\":\""+strRASRakeAlcnPlan[i][8]+"\",\"odrdtls\":\""+strRASRakeAlcnPlan[i][10]+"\"},";                          
    }
    strData+="]}";
    }
    else
     {
            strCallStts="F";
            strCallMesg="NO DATA AVAILABLE";
            logger.info("No Data Found");
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASIORakeAlotment")) /*RAS-IORE        RAKE ALLOTMENT*/
    {
        String strFinalDate1 ="";
        String strFinalDate2 ="";
        String strDate ="";
        String strClst = 
        strDvsn = (String)req.getParameter("rasDvsn").toUpperCase();
            logger.info("strDvsn"+ strDvsn);
        if (strDvsn.equals("CKP"))
                        {
                                
                                strSttn = (String)req.getParameter("ckpSttn").toUpperCase().trim();
                            strClst = (String)req.getParameter("ckpClst").toUpperCase().trim();
                        }
                        if (strDvsn.equals("KUR"))
                        {
                                
                                strSttn = (String)req.getParameter("kurSttn").toUpperCase().trim();
                            strClst = (String)req.getParameter("kurClst").toUpperCase().trim();
                        }
                        if (strDvsn.equals("KGP"))
                        {
                                
                                strSttn = (String)req.getParameter("kgpSttn").toUpperCase().trim();
                            strClst = (String)req.getParameter("kgpClst").toUpperCase().trim();
                        }
                        
    
                        if (strDvsn.equals("ASN"))
                        {
                                
                                strSttn = (String)req.getParameter("asnSttn").toUpperCase().trim();
                            strClst = (String)req.getParameter("asnClst").toUpperCase().trim();
                        }
                        if (strDvsn.equals("SBP"))
                        {
                                
                                strSttn = (String)req.getParameter("sbpSttn").toUpperCase().trim();
                            strClst = (String)req.getParameter("sbpClst").toUpperCase().trim();
                        }
                        
                        
                        logger.info("strSttn" + strSttn);
        logger.info("strClst" + strClst);
                        
                        String strCtgy = (String)req.getParameter("inptradio").toUpperCase().trim();
                        logger.info("strCtgy" + strCtgy);
                        if (strCtgy.equals("S"))
                        {
                                 strDateFrom = (String)req.getParameter("FromDate").toUpperCase().trim();
                                 strDateTo = (String)req.getParameter("ToDate").toUpperCase().trim();
                                 logger.info("strDateFrom" + strDateFrom);
                                 logger.info("strDateTo" + strDateTo);
                                 strFinalDate1 = strDateFrom;
                                 strFinalDate2 = strDateTo;
                        }       
                        else
                        {
                                strDate = (String)req.getParameter("NextScndday").toUpperCase().trim();
                                 logger.info("strDate" + strDate);
                                 strFinalDate1 = "01-01-2010";
                                 strFinalDate2 = strDate; 
                                
                        }       
            
    if (((strDvsn.equals(""))||(strDvsn==null)))
    {
    logger.info("Division Is Mandatory");
        strCallStts="F";
        strCallMesg="Division Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
        if (((strSttn.equals(""))||(strSttn==null)))
        {
                logger.info("Station Is Mandatory");
            strCallStts="F";
            strCallMesg="Station Is Mandatory";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
            res.getWriter().write(strData);
            return;
        }
    if (((strFinalDate1.equals(""))||(strFinalDate1==null)))
    {
    logger.info("Date From Is Mandatory");
        strCallStts="F";
        strCallMesg="Date From Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
    if (((strFinalDate2.equals(""))||(strFinalDate2==null)))
    {
    logger.info("Date To Is Mandatory");
        strCallStts="F";
        strCallMesg="Date To Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
            
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(strDvsn, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
    logger.info("Only Alphabetic Characters  are  Allowed In Division");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
        bolValid=true;
        bolValid=   vldt.valdInpt(strSttn, "A");
        if (bolValid==true)
        {
        
        }
        else 
        {
        logger.info("Only Alphabetic Characters  are  Allowed In Station");
            strCallStts="F";
            strCallMesg="Only Alphabetic Characters  are  Allowed In Station";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
            res.getWriter().write(strData);
            return;
        }
    
            
            
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                
    try
    {
    String strRASIORakeAlotment[][]=obj.geRASIORakeAlotment(si_UserID, si_ClntID,strDvsn,strSttn,strFinalDate1,strFinalDate2,strClst);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
    
    
    int intDataLen=strRASIORakeAlotment.length;
    if (intDataLen>0)
    {
          strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {   

        String codes=strRASIORakeAlotment[i][3];
        String names=strRASIORakeAlotment[i][4];
        String code=strRASIORakeAlotment[i][15];
        String name=strRASIORakeAlotment[i][16];
        String custs[]={"",""};
        String custnames[]={"",""};
        if(codes.indexOf("-L") >0)
        {
                custs=codes.split(code);                                
                if(custs.length ==1)
                {
                        String c=custs[0];
                        custs=new String[2];
                        custs[0]=c;
                        custs[1]="";
                }
                custnames=names.split(name);
        
                if(custnames.length ==1)
                {
                        String c=custnames[0];
                        custnames=new String[2];
                        custnames[0]=c;
                        custnames[1]="";
                }
        }
        String strCode="";
        String strName="";
        if(codes.indexOf("-L") >0)
        {strCode=custs[1];
         }
        else
        {
                strCode=code;
            }
        if(names.indexOf("-L") >0)
            {strName=custnames[1];
             }
            else
            {
                    strName=name;
                }
        
    if(i==(intDataLen-1))
            strData+="{\"date\":\""+strRASIORakeAlotment[i][1]+"\",\"clstr\":\""+strRASIORakeAlotment[i][22]+"\",\"ldngsttn\":\""+strRASIORakeAlotment[i][2]+"\",\"custcode\":\""+strCode+"\",\"custname\":\""+strName+"\",\"sttnto\":\""+strRASIORakeAlotment[i][5]+"\",\"indtnumb\":\""+strRASIORakeAlotment[i][6]+"\",\"color\":\""+strRASIORakeAlotment[i][17]+"\",\"indtdate\":\""+strRASIORakeAlotment[i][7]+"\",\"prty\":\""+strRASIORakeAlotment[i][12]+"\",\"subprty\":\""+strRASIORakeAlotment[i][8]+"\",\"schmcode\":\""+strRASIORakeAlotment[i][23]+"\",\"premflag\":\""+strRASIORakeAlotment[i][24]+"\",\"expldng\":\""+strRASIORakeAlotment[i][25]+"\",\"rpnumb\":\""+strRASIORakeAlotment[i][19]+"\",\"rpdate\":\""+strRASIORakeAlotment[i][20]+"\",\"rpvald\":\""+strRASIORakeAlotment[i][21]+"\"}";
    else
        strData+="{\"date\":\""+strRASIORakeAlotment[i][1]+"\",\"clstr\":\""+strRASIORakeAlotment[i][22]+"\",\"ldngsttn\":\""+strRASIORakeAlotment[i][2]+"\",\"custcode\":\""+strCode+"\",\"custname\":\""+strName+"\",\"sttnto\":\""+strRASIORakeAlotment[i][5]+"\",\"indtnumb\":\""+strRASIORakeAlotment[i][6]+"\",\"color\":\""+strRASIORakeAlotment[i][17]+"\",\"indtdate\":\""+strRASIORakeAlotment[i][7]+"\",\"prty\":\""+strRASIORakeAlotment[i][12]+"\",\"subprty\":\""+strRASIORakeAlotment[i][8]+"\",\"schmcode\":\""+strRASIORakeAlotment[i][23]+"\",\"premflag\":\""+strRASIORakeAlotment[i][24]+"\",\"expldng\":\""+strRASIORakeAlotment[i][25]+"\",\"rpnumb\":\""+strRASIORakeAlotment[i][19]+"\",\"rpdate\":\""+strRASIORakeAlotment[i][20]+"\",\"rpvald\":\""+strRASIORakeAlotment[i][21]+"\"},";
    }
    strData+="]}";
    }
    else
    {
    logger.info("No Data Found");
        strCallStts="F";
        strCallMesg="NO DATA FOUND";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
    }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASIRONORE")) /*RAS-IORE        IORE INDENTS*/
    {
             String strZoneCode                 = req.getParameter("txtZone").trim().toUpperCase();
                logger.info("strZoneCode"+ strZoneCode);
                String strDvsnCode                      = req.getParameter("txtDvsn").trim().toUpperCase();
                logger.info("strDvsnCode"+ strDvsnCode);
                String strSttnCode                      = req.getParameter("txtSttn").trim().toUpperCase();
                logger.info("strSttnCode" + strSttnCode);
        String strCmdt              = req.getParameter("txtCmdt").trim().toUpperCase();
        logger.info("strCmdt" + strCmdt);
                String strDate              = req.getParameter("txtDate").trim().toUpperCase();
                logger.info("strDate" + strDate);

        String strCluster                   = req.getParameter("txtClst").trim().toUpperCase();
        logger.info("strCluster" + strCluster);
                
                req.setAttribute("txtzone1", strZoneCode);
                req.setAttribute("txtdate1", strDate);
        
        if(strDvsnCode.equals("ALL"))
        {
                strDvsnCode="";   
        } 
        if(strSttnCode.equals("ALL"))
        {
                strSttnCode="";   
        }
       
                    
    if (((strZoneCode.equals(""))||(strZoneCode==null)))
    {
    logger.info("Zone Is Mandatory");
        strCallStts="F";
        strCallMesg="Zone Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
   
                    
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(strZoneCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In ZONE");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In ZONE";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
       
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
    try
    {
    String strRASIRONORE[][]=obj.geRASIRONORE(si_UserID, si_ClntID,strZoneCode,strDvsnCode,strSttnCode,strCmdt,strDate,strCluster);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
        String strHrefO="";  
        String strHrefR="";
        String strHrefA="";
        String strHrefAll="";
       
    
    int intDataLen=strRASIRONORE.length;
    if (intDataLen>0)
    {
        
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {       
        
        if(strRASIRONORE[i][3].equals("0"))
                        {                       
                            strHrefO ="#";
                        }
              else
                      {
                strHrefO = "/RailSAHAY/SHY_OdrRasJSON?Optn=RASIRONOREDtls&strZoneCode="+strRASIRONORE[i][5]+"&strDvsnCode="+strRASIRONORE[i][6]+"&strSttnCode="+strRASIRONORE[i][7]+"&strCmdt="+strRASIRONORE[i][8]+"&strDate="+strRASIRONORE[i][10]+"&txtFlag=O&txtSttn1="+strRASIRONORE[i][1]+"&txtClst="+strRASIRONORE[i][14];
                   }
        if(strRASIRONORE[i][2].equals("0"))
                        {                       
                            strHrefR ="#";
                        }
              else
                      {
                strHrefR = "/RailSAHAY/SHY_OdrRasJSON?Optn=RASIRONOREDtls&strZoneCode="+strRASIRONORE[i][5]+"&strDvsnCode="+strRASIRONORE[i][6]+"&strSttnCode="+strRASIRONORE[i][7]+"&strCmdt="+strRASIRONORE[i][8]+"&strDate="+strRASIRONORE[i][10]+"&txtFlag=R&txtSttn1="+strRASIRONORE[i][1]+"&txtClst="+strRASIRONORE[i][14];
                   }
        if(strRASIRONORE[i][4].equals("0"))
                        {                       
                            strHrefA ="#";
                        }
              else
                      {
                strHrefA = "/RailSAHAY/SHY_OdrRasJSON?Optn=RASIRONOREDtls&strZoneCode="+strRASIRONORE[i][5]+"&strDvsnCode="+strRASIRONORE[i][6]+"&strSttnCode="+strRASIRONORE[i][7]+"&strCmdt="+strRASIRONORE[i][8]+"&strDate="+strRASIRONORE[i][10]+"&txtFlag=A&txtSttn1="+strRASIRONORE[i][1]+"&txtClst="+strRASIRONORE[i][14];
                   }
        strHrefAll="/RailSAHAY/SHY_OdrRasJSON?Optn=RASIRONOREALLDtls&txtzone1="+strZoneCode+"&txtdate1="+strDate+"&txtClst="+strRASIRONORE[i][14];
                      
            if(i==(intDataLen-1))
                    strData+="{\"dvsn\":\""+strRASIRONORE[i][0]+"\",\"sttn\":\""+strRASIRONORE[i][1]+"\",\"clstr\":\""+strRASIRONORE[i][14]+"\",\"indtO\":\""+strRASIRONORE[i][3]+"\",\"indtOhref\":\""+strHrefO+"\",\"indtR\":\""+strRASIRONORE[i][2]+"\",\"indtRHref\":\""+strHrefR+"\",\"indtA\":\""+strRASIRONORE[i][4]+"\",\"indtAHref\":\""+strHrefA+"\"},";
            else
                    strData+="{\"dvsn\":\""+strRASIRONORE[i][0]+"\",\"sttn\":\""+strRASIRONORE[i][1]+"\",\"clstr\":\""+strRASIRONORE[i][14]+"\",\"indtO\":\""+strRASIRONORE[i][3]+"\",\"indtOhref\":\""+strHrefO+"\",\"indtR\":\""+strRASIRONORE[i][2]+"\",\"indtRHref\":\""+strHrefR+"\",\"indtA\":\""+strRASIRONORE[i][4]+"\",\"indtAHref\":\""+strHrefA+"\"},";                          
    }
        strData+="{\"dvsn\":\"G.TOTAL"+"\",\"clstr\":\""+"\",\"sttn\":\""+"\",\"indtO\":\""+strRASIRONORE[0][12]+"\",\"indtOhref\":\"#"+"\",\"indtR\":\""+strRASIRONORE[0][11]+"\",\"indtRHref\":\"#"+"\",\"indtA\":\""+strRASIRONORE[0][13]+"\",\"indtAHref\":\"#"+"\",\"totalAllAHref\":\""+strHrefAll+"\"}";
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASIRONOREDtls")) /*RAS-IORE       IORE INDENTS DETAILS*/
    {
        logger.info("In iron ore details");
                        String strZoneCode = (String)req.getParameter("strZoneCode").toUpperCase(); 
                        String strDvsnCode = (String)req.getParameter("strDvsnCode").toUpperCase(); 
                        String strSttnCode = (String)req.getParameter("strSttnCode").toUpperCase(); 
                        String strCmdt = (String)req.getParameter("strCmdt").toUpperCase(); 
                        String strDate = (String)req.getParameter("strDate").toUpperCase(); 
                        String strFlag = (String)req.getParameter("txtFlag").toUpperCase(); 
                        String strSttnFrom = (String)req.getParameter("txtSttn1").toUpperCase(); 
        String strCluster                   = req.getParameter("txtClst").trim().toUpperCase();
        logger.info("strCluster" + strCluster);
                        
                        logger.info("strZoneCode"+strZoneCode);
                        logger.info("strDvsnCode"+strDvsnCode);
                        logger.info("strSttnCode"+strSttnCode);
                        logger.info("strCmdt"+strCmdt);
                        logger.info("strDate"+strDate);
                        logger.info("strFlag"+strFlag);
                        logger.info("strSttnFrom"+strSttnFrom);
                    
   
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
    try
    {
    String strRASIRONOREDtls[][]=obj.geRASIRONOREDtls(si_UserID, si_ClntID,strZoneCode,strDvsnCode,strSttnCode,strCmdt,strDate,strFlag,strSttnFrom, strCluster);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
      
    String strnoData="";
    int intDataLen=strRASIRONOREDtls.length;
    if (intDataLen>0)
    {
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {      
            
            if (strFlag.equals("O"))
               {    
                                  
            if(i==(intDataLen-1))
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"dvsnfrom\":\""+strRASIRONOREDtls[i][1]+"\",\"clstr\":\""+strRASIRONOREDtls[i][28]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"cmdt\":\""+strRASIRONOREDtls[i][3]+"\",\"trfctype\":\""+strRASIRONOREDtls[i][14]+"\",\"prtyclas\":\""+strRASIRONOREDtls[i][2]+"\",\"subprty\":\""+strRASIRONOREDtls[i][27]+"\",\"prmbkgclas\":\""+strRASIRONOREDtls[i][15]+"\",\"rstn\":\""+strnoData+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"ostgunts\":\""+strRASIRONOREDtls[i][19]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][20]+"\",\"schmcode\":\""+strRASIRONOREDtls[i][29]+"\",\"premflag\":\""+strRASIRONOREDtls[i][30]+"\",\"expldng\":\""+strRASIRONOREDtls[i][31]+"\"}";
            else
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"dvsnfrom\":\""+strRASIRONOREDtls[i][1]+"\",\"clstr\":\""+strRASIRONOREDtls[i][28]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"cmdt\":\""+strRASIRONOREDtls[i][3]+"\",\"trfctype\":\""+strRASIRONOREDtls[i][14]+"\",\"prtyclas\":\""+strRASIRONOREDtls[i][2]+"\",\"subprty\":\""+strRASIRONOREDtls[i][27]+"\",\"prmbkgclas\":\""+strRASIRONOREDtls[i][15]+"\",\"rstn\":\""+strnoData+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"ostgunts\":\""+strRASIRONOREDtls[i][19]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][20]+"\",\"schmcode\":\""+strRASIRONOREDtls[i][29]+"\",\"premflag\":\""+strRASIRONOREDtls[i][30]+"\",\"expldng\":\""+strRASIRONOREDtls[i][31]+"\"},";
        }    
               if (strFlag.equals("R"))
               {    
                                  
            if(i==(intDataLen-1))
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"dvsnfrom\":\""+strRASIRONOREDtls[i][1]+"\",\"clstr\":\""+strRASIRONOREDtls[i][28]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmndno\":\""+strRASIRONOREDtls[i][10]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"dmndtime\":\""+strRASIRONOREDtls[i][12]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"cmdt\":\""+strRASIRONOREDtls[i][3]+"\",\"spclno\":\""+strRASIRONOREDtls[i][13]+"\",\"trfctype\":\""+strRASIRONOREDtls[i][14]+"\",\"prtyclas\":\""+strRASIRONOREDtls[i][2]+"\",\"prmbkgclas\":\""+strRASIRONOREDtls[i][15]+"\",\"rstn\":\""+strnoData+"\",\"via\":\""+strRASIRONOREDtls[i][18]+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"indtunts\":\""+strRASIRONOREDtls[i][19]+"\",\"indt8w\":\""+strRASIRONOREDtls[i][20]+"\",\"load8w\":\""+strRASIRONOREDtls[i][23]+"\",\"cncl8w\":\""+strRASIRONOREDtls[i][24]+"\",\"forefit\":\""+strRASIRONOREDtls[i][25]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][20]+"\",\"schmcode\":\""+strRASIRONOREDtls[i][29]+"\",\"premflag\":\""+strRASIRONOREDtls[i][30]+"\",\"expldng\":\""+strRASIRONOREDtls[i][31]+"\"}";
            else
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"dvsnfrom\":\""+strRASIRONOREDtls[i][1]+"\",\"clstr\":\""+strRASIRONOREDtls[i][28]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmndno\":\""+strRASIRONOREDtls[i][10]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"dmndtime\":\""+strRASIRONOREDtls[i][12]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"cmdt\":\""+strRASIRONOREDtls[i][3]+"\",\"spclno\":\""+strRASIRONOREDtls[i][13]+"\",\"trfctype\":\""+strRASIRONOREDtls[i][14]+"\",\"prtyclas\":\""+strRASIRONOREDtls[i][2]+"\",\"prmbkgclas\":\""+strRASIRONOREDtls[i][15]+"\",\"rstn\":\""+strnoData+"\",\"via\":\""+strRASIRONOREDtls[i][18]+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"indtunts\":\""+strRASIRONOREDtls[i][19]+"\",\"indt8w\":\""+strRASIRONOREDtls[i][20]+"\",\"load8w\":\""+strRASIRONOREDtls[i][23]+"\",\"cncl8w\":\""+strRASIRONOREDtls[i][24]+"\",\"forefit\":\""+strRASIRONOREDtls[i][25]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][20]+"\",\"schmcode\":\""+strRASIRONOREDtls[i][29]+"\",\"premflag\":\""+strRASIRONOREDtls[i][30]+"\",\"expldng\":\""+strRASIRONOREDtls[i][31]+"\"},";
        }   
         if (strFlag.equals("A"))
               {    
                                  
              if(i==(intDataLen-1))
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmndno\":\""+strRASIRONOREDtls[i][10]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"dmndtime\":\""+strRASIRONOREDtls[i][12]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"indt8w\":\""+strRASIRONOREDtls[i][20]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][19]+"\",\"frwdnoteno\":\""+strRASIRONOREDtls[i][22]+"\",\"frwdnotedate\":\""+strRASIRONOREDtls[i][23]+"\",\"alotdate\":\""+strRASIRONOREDtls[i][24]+"\",\"alotunit\":\""+strRASIRONOREDtls[i][27]+"\"}";
            else
                    strData+="{\"srno\":\""+strRASIRONOREDtls[i][0]+"\",\"sttnfrom\":\""+strRASIRONOREDtls[i][9]+"\",\"sttnto\":\""+strRASIRONOREDtls[i][4]+"\",\"dmndno\":\""+strRASIRONOREDtls[i][10]+"\",\"dmnddate\":\""+strRASIRONOREDtls[i][11]+"\",\"dmndtime\":\""+strRASIRONOREDtls[i][12]+"\",\"cnsr\":\""+strRASIRONOREDtls[i][5]+"\",\"cnsg\":\""+strRASIRONOREDtls[i][6]+"\",\"indttype\":\""+strRASIRONOREDtls[i][7]+"\",\"indt8w\":\""+strRASIRONOREDtls[i][20]+"\",\"ostg8w\":\""+strRASIRONOREDtls[i][19]+"\",\"frwdnoteno\":\""+strRASIRONOREDtls[i][22]+"\",\"frwdnotedate\":\""+strRASIRONOREDtls[i][23]+"\",\"alotdate\":\""+strRASIRONOREDtls[i][24]+"\",\"alotunit\":\""+strRASIRONOREDtls[i][27]+"\"},";                          
        }   
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASIRONOREALLDtls")) /*RAS-IORE       IORE INDENTS ALL DETAILS*/
    {
        logger.info("In iron ore details");
         strCallStts="";
         strCallMesg="";
                
        ArrayList<String[]> list;     
        list=new ArrayList<String[]>();
                String si_Locn          = "";
        String si_ZDS           = "";
        String si_Prty          = "";
        String si_Cmdt          = "";
        String si_Cnsr                  = "";
        String si_Cnsg          = "";
        String si_RFlag         = "";
        String si_RakeTyp       = "";
        String si_Dstn          = "";
        String si_Gaug          = "";
        String si_Flag ="";
        String si_Date="";
        String strCoreFlag ="A";
        String strLpsbFlag ="A";
        String strDmndFlag ="A"; 
                
                
                String si_ExtZone= (String)req.getParameter("txtzone1").toUpperCase();
        String si_ExtDvsn= "";
       String si_ExtSttn= "";
       String si_ExtCmdt= "";
        String si_ExtDate= (String)req.getParameter(("txtdate1"));
                   logger.info("si_ExtDate"+si_ExtDate);
        
        
        if(si_ExtDvsn.equals("ALL"))
        {
            si_ExtDvsn="";   
        }
        if(si_ExtSttn.equals("ALL"))
        {
            si_ExtSttn="";   
        }
        
        
        if((si_ExtSttn.equals("")) || (si_ExtSttn==null))
        {
            if((si_ExtDvsn.equals("")) || (si_ExtDvsn==null))
            {  
                si_Flag="Z"; 
                si_ZDS= si_ExtZone;
                si_Locn=si_ExtZone;
                     
            }
            else
            {
                si_Flag="D"; 
                si_ZDS= si_ExtDvsn;
                si_Locn=si_ExtDvsn;  
               
            }
        }
        
        si_Prty = "#";
        si_Cmdt=si_ExtCmdt;
        si_Dstn = "#";
        si_Cnsr = "#";
        si_Cnsg = "#";
        si_RFlag = "#";
        si_RakeTyp = "#";
        si_Gaug="1";
        si_Date=si_ExtDate;
       
        
        
        
      
                
                logger.info("si_Date"+si_Date);
                
                logger.info("si_ZDS"+si_ZDS.toUpperCase());
                logger.info("si_Flag"+si_Flag);
                    
   
        FOISTuxedo RQODRRKOTSGDTLS = null;
                   try
                   {
                           logger.info("Calling Tuxedo");
                           RQODRRKOTSGDTLS = new FOISTuxedo();
                           logger.info("Client Object Got.");
                   }
                   catch (Exception e)
                   {
                           logger.info("Tuxedo Connnection Failed.");
                       strCallStts="F";
                                      strCallMesg="Unable to get Client Object";
                                      
                   }

                   try
                   {
                           logger.info("Calling tuxInit.");

                         RQODRRKOTSGDTLS.tuxInit("RQIRODRRKOTSGDTLS");                  
                              RQODRRKOTSGDTLS.setString("F_USERID", 0, "WEBPRTL");
                              RQODRRKOTSGDTLS.setString("F_CLNTID", 0,  "1.1.1.1");
                              RQODRRKOTSGDTLS.setString("F_DVSN", 0, si_ExtZone);
                              RQODRRKOTSGDTLS.setString("F_FLAG", 0, "Z");
                              RQODRRKOTSGDTLS.setString("F_ORGNGAUG", 0, si_Gaug);
                  RQODRRKOTSGDTLS.setString("F_PRTYCLSS", 0, si_Prty);
                              RQODRRKOTSGDTLS.setString("F_CMDT", 0, si_Cmdt);
                              RQODRRKOTSGDTLS.setString("F_STTNTO", 0, si_Dstn);
                              RQODRRKOTSGDTLS.setString("F_CNSR", 0, si_Cnsr);
                              RQODRRKOTSGDTLS.setString("F_CNSG", 0, si_Cnsg);
                  RQODRRKOTSGDTLS.setString("F_STCKTYPE", 0, si_RFlag);
                  RQODRRKOTSGDTLS.setString("F_RTFRFLAG", 0, si_RakeTyp);
                  RQODRRKOTSGDTLS.setString("F_DATE", 0, si_Date);
                  RQODRRKOTSGDTLS.setString("F_CCHGFLAG", 0, strCoreFlag);
                  RQODRRKOTSGDTLS.setString("F_BVDCFLAG", 0, strLpsbFlag);
                  RQODRRKOTSGDTLS.setString("F_ARVLFLAG", 0, strDmndFlag);
                                      logger.info("Called Tux_Fchg.");
                                      RQODRRKOTSGDTLS.call("N");
                       String erorCode1                        = null;
                                  try
                                  {
                                          erorCode1                               = RQODRRKOTSGDTLS.getStringItemDef("F_ERORNO",0,"0");
                                  }
                                  catch(Exception e)
                                  {
                                          // F_ERORNO is not set by Developer, So, it is not an Error
                                  }
                                  if(erorCode1 != null && (!erorCode1.equals("")))
                                  {
                                          logger.fatal(erorCode1);
                                          strCallStts="F";
                                          strCallMesg=erorCode1;
                                  }
                                      logger.info("Called Tux_Call.");
                                       strData = RQODRRKOTSGDTLS.getStringItemDef("F_ROWCONT", 0);
                                      logger.info("Called Tux_Fchg.");
                                      logger.info("F_ROWCONT :"+ strData);
                                      int intRowCount=Integer.parseInt(strData);
                                    
                                  String strFlag       ="";
                                  String strGaug       ="";
                                  String strUserName   ="";
                                      String strClntId     ="";
                                      
                                      
                                  //here is the tuxedo coding
                                  int    intSrNo       = 0;
                                  String strDstnSttn   ="";
                                  String strSttnFrom   ="";
                                  String strRakeNumb   ="";
                                  String strFrwh       ="";
                                  String strOdr        ="";
                                  String strPrtyClss   ="";
                                  String strCmdt       ="";
                                  String strStckTyp    ="";
                                  String strRtfrFlag   ="";
                                  String strDmndNumb   ="";
                                  String strDmndDate   ="";
                                  String strDmndTime   ="";
                                  String strSpclNumb   ="";
                                  String strTrfcType   ="";
                                  String strPrntFlag   ="";
                                  String strQuta       ="";
                                  String strODC            ="";
                                  String strVia        ="";
                                  String strUnits      ="";
                                  String strOtsgFrwh   ="";
                                  String strDmndDtlsid ="";
                                  String strPlctUnts   ="";
                                  String strPlctTime   ="";
                                  String strLoadFrwh   ="";
                                  String strCnclFrwh   ="";
                                  String strFrftFrwh   ="";
                                  String strDate       ="";
                                  String strData1      =null;
                                  String strid         ="";
                                  String strDvsnFrom   ="";
                                  String strPrevDvsnFrom ="";
                                  String strDmndStts ="";
                                  String strDvsnTo   ="";
                                  String strZoneTo ="";

                                  double fltTotUnts    = 0;
                                  double fltTotFrwh    = 0;
                                  double fltTotLoadFrwh= 0;
                                  double fltTotCnclFrwh= 0;
                                  double fltTotFrftFrwh= 0;
                                  double fltTotOtsgFrwh= 0;
                                  double fltTotPlctUnts= 0;
                                  String strTotUnts    ="";
                                  String strTotFrwh    ="";
                                  String strTotLoadFrwh="";
                                  String strTotCnclFrwh="";
                                  String strTotFrftFrwh="";
                                  String strTotOtsgFrwh="";
                                  String strTotPlctUnts="";
                                  
                                  double fltSubTotUnts    = 0;
                                  double fltSubTotFrwh    = 0;
                                  double fltSubTotLoadFrwh= 0;
                                  double fltSubTotCnclFrwh= 0;
                                  double fltSubTotFrftFrwh= 0;
                                  double fltSubTotOtsgFrwh= 0;
                                  double fltSubTotPlctUnts= 0;
                                  String strSubTotUnts    ="";
                                  String strSubTotFrwh    ="";
                                  String strSubTotLoadFrwh="";
                                  String strSubTotCnclFrwh="";
                                  String strSubTotFrftFrwh="";
                                  String strSubTotOtsgFrwh="";
                                  String strSubTotPlctUnts="";
                                  String strColorFlag="";
                                  String strRASAlotFlag="";
                                  String strRASAlotDate="";
                                  String strCoreNonCore="";
                                  String strLpsblFlag="";
                                  String strrakecmdt="";
                                  String strCoalCtgr="";
                                  String strSttnTo="";
                                  String strCnsr="";
                                  String strCnsg="";
                                  int iCounter = 0;
                                  
                                   iCounter = 0;            
                  
                  
                  
                                       for (int i=0;i<intRowCount;i++)
                                              {
                                               String[] strCol= new String[50];
                                               
                                               strPlctUnts                            =RQODRRKOTSGDTLS.getStringItemDef("F_PLCDUNTS", i, "0").trim(); 
                                               strUnits                                       =RQODRRKOTSGDTLS.getStringItemDef("F_UNTS", i, "0").trim(); 
                                               strFrwh                                        =RQODRRKOTSGDTLS.getStringItemDef("F_FRWH", i, "0").trim(); 
                                               strPlctUnts                            =RQODRRKOTSGDTLS.getStringItemDef("F_PLCDUNTS", i, "0").trim();
                                               strLoadFrwh                            =RQODRRKOTSGDTLS.getStringItemDef("F_LOADFRWH", i, "0").trim(); 
                                               strCnclFrwh                            =RQODRRKOTSGDTLS.getStringItemDef("F_CNCLFRWH", i, "0").trim(); 
                                               strFrftFrwh                            =RQODRRKOTSGDTLS.getStringItemDef("F_FRFTFRWH", i, "0").trim();
                                               strOtsgFrwh                            =RQODRRKOTSGDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim(); 
                                               
                                               if((strPlctUnts).equals(""))
                           {
                              strPlctUnts=""+0;
                           }  
                           
                           fltTotUnts         += Double.valueOf(strUnits).doubleValue();
                           fltTotFrwh         += Double.valueOf(strFrwh).doubleValue();
                           fltTotPlctUnts     += Double.valueOf(strPlctUnts).doubleValue();
                           fltTotLoadFrwh     += Double.valueOf(strLoadFrwh).doubleValue();
                           fltTotCnclFrwh     += Double.valueOf(strCnclFrwh).doubleValue();
                           fltTotFrftFrwh     += Double.valueOf(strFrftFrwh).doubleValue();
                           fltTotOtsgFrwh     += Double.valueOf(strOtsgFrwh).doubleValue();
                           
                          fltSubTotUnts         += Double.valueOf(strUnits).doubleValue();      
                          fltSubTotFrwh         += Double.valueOf(strFrwh).doubleValue(); 
                          fltSubTotPlctUnts     += Double.valueOf(strPlctUnts).doubleValue();       
                          fltSubTotLoadFrwh     += Double.valueOf(strLoadFrwh).doubleValue();
                          fltSubTotCnclFrwh     += Double.valueOf(strCnclFrwh).doubleValue();
                          fltSubTotFrftFrwh     += Double.valueOf(strFrftFrwh).doubleValue();
                          fltSubTotOtsgFrwh     += Double.valueOf(strOtsgFrwh).doubleValue();
                          
                          
                                                              strDvsnFrom                             = RQODRRKOTSGDTLS.getStringItemDef("F_DVSNFROM", i, "0").trim();
                                                              
                                                      if((strDvsnFrom.equals(strPrevDvsnFrom)) || i==0)
                                                              {
                                                       
                                                       /*strPlctUnts=strCol[i][24],strUnits=strCol[i][20],strFrwh=strCol[i][22],strPlctUnts=strCol[i][24]*/                                                                 
                                                              /* strLoadFrwh=strCol[i][26],strCnclFrwh=strCol[i][27],strFrftFrwh=strCol[i][28],strOtsgFrwh=strCol[i][21]*/
                                                              
                                                              strSubTotUnts     = "" + fltSubTotUnts;       
                                      strSubTotFrwh     = "" + fltSubTotFrwh;
                                      strSubTotPlctUnts = "" + fltSubTotPlctUnts;        
                                      strSubTotLoadFrwh = "" + fltSubTotLoadFrwh;
                                      strSubTotCnclFrwh = "" + fltSubTotCnclFrwh;
                                      strSubTotFrftFrwh = "" + fltSubTotFrftFrwh;
                                      strSubTotOtsgFrwh = "" + fltSubTotOtsgFrwh;
                                      intSrNo             = iCounter +1;
                                      
                                                       
                                                               strCol[0]                              = RQODRRKOTSGDTLS.getStringItemDef("F_DVSNFROM", i, "0").trim();                                                                                          
                                                               strCol[1]                      = RQODRRKOTSGDTLS.getStringItemDef("F_PRTYCLSS", i, "0").trim();                                                                                       
                                                               strCol[2]                      = RQODRRKOTSGDTLS.getStringItemDef("F_CMDT", i, "0").trim();                                                                                           
                                                               strCol[3]                              = RQODRRKOTSGDTLS.getStringItemDef("F_STTNTO", i, "0").trim();                                                                                            
                                                               strCol[4]                      = RQODRRKOTSGDTLS.getStringItemDef("F_ARVLDVSNTO", i, "0").trim();                                                                                   
                                                               strCol[5]                      = RQODRRKOTSGDTLS.getStringItemDef("F_HLDGZONE", i, "0").trim();                                                                                    
                                                               strCol[6]                      = RQODRRKOTSGDTLS.getStringItemDef("F_CNSR", i, "0").trim();                                                                                          
                                                               strCol[7]                      = RQODRRKOTSGDTLS.getStringItemDef("F_CNSG", i, "0").trim();                                                                                           
                                                               strCol[8]                      = RQODRRKOTSGDTLS.getStringItemDef("F_STCKTYPE", i, "0").trim();                                                                                       
                                                               strCol[9]                      = RQODRRKOTSGDTLS.getStringItemDef("F_RTFRFLAG", i, "0").trim();                                                                                       
                                                               strCol[10]                     = RQODRRKOTSGDTLS.getStringItemDef("F_STTNFROM", i, "0").trim();                                                                                      
                                                               strCol[11]                     = RQODRRKOTSGDTLS.getStringItemDef("F_DMNDNUMB", i, "0").trim();                                                                                      
                                                               strCol[12]                     = RQODRRKOTSGDTLS.getStringItemDef("F_DMNDDATE", i, "0").trim();                                                                                      
                                                               strCol[13]                     = RQODRRKOTSGDTLS.getStringItemDef("F_DMNDTIME", i, "0").trim();                                                                                      
                                                               strCol[14]                     = RQODRRKOTSGDTLS.getStringItemDef("F_SPCLNUMB", i, "0").trim();                                                                                      
                                                               strCol[15]                     = RQODRRKOTSGDTLS.getStringItemDef("F_TRFCTYPE", i, "0").trim();                                                                                      
                                                               strCol[16]                     = RQODRRKOTSGDTLS.getStringItemDef("F_PRNTFLAG", i, "0").trim();                                                                                      
                                                               strCol[17]                     = RQODRRKOTSGDTLS.getStringItemDef("F_QUTA", i, "0").trim();                                                                                          
                                                               strCol[18]                     = RQODRRKOTSGDTLS.getStringItemDef("F_ODC", i, "0").trim();  
                                                               strCol[19]                     = RQODRRKOTSGDTLS.getStringItemDef("F_VIA", i, "0").trim();                                                                                           
                                                               strCol[20]                     = RQODRRKOTSGDTLS.getStringItemDef("F_UNTS", i, "0").trim();                                                                                          
                                                               strCol[21]                     = RQODRRKOTSGDTLS.getStringItemDef("F_OTSGFRWH", i, "0").trim();                                                                                      
                                                               strCol[22]                     = RQODRRKOTSGDTLS.getStringItemDef("F_FRWH", i, "0").trim();                                                                                       
                                                               strCol[23]                     = RQODRRKOTSGDTLS.getStringItemDef("F_DMNDDTLSID", i, "0").trim();                                                                                    
                                                               strCol[24]                     = RQODRRKOTSGDTLS.getStringItemDef("F_PLCDUNTS", i, "0").trim();                                                                          
                                                               strCol[25]                     = RQODRRKOTSGDTLS.getStringItemDef("F_PLCTTIME", i, "0").trim();                                                                                      
                                                               strCol[26]                     = RQODRRKOTSGDTLS.getStringItemDef("F_LOADFRWH", i, "0").trim();                                                                                      
                                           strCol[27]             = RQODRRKOTSGDTLS.getStringItemDef("F_CNCLFRWH", i, "0").trim();                                                               
                                           strCol[28]             = RQODRRKOTSGDTLS.getStringItemDef("F_FRFTFRWH", i, "0").trim();                                                               
                                           strCol[29]             = RQODRRKOTSGDTLS.getStringItemDef("F_DATE", i, "0").trim();                                                                   
                                           strCol[30]             = RQODRRKOTSGDTLS.getStringItemDef("F_ARFFLAG"        , i,  "0").trim();                                                        
                                           strCol[31]             = RQODRRKOTSGDTLS.getStringItemDef("F_AJSTFLAG"        , i, "0").trim();                                                       
                                           strCol[32]             = RQODRRKOTSGDTLS.getStringItemDef("F_ACRLDATE"        , i, "0").trim();                                                       
                                           strCol[33]             = RQODRRKOTSGDTLS.getStringItemDef("F_ATDTFLAG"        , i, "0").trim();                                                       
                                           strCol[34]             = RQODRRKOTSGDTLS.getStringItemDef("F_BVANFLAG"        , i, "0").trim();                                                       
                                           strCol[35]             = RQODRRKOTSGDTLS.getStringItemDef("F_CTGR"        , i,     "0").trim();                                                           
                                           strCol[36]             = RQODRRKOTSGDTLS.getStringItemDef("F_RAKECMDT"        , i, "0").trim();                                                       
                                           strCol[37]             = RQODRRKOTSGDTLS.getStringItemDef("F_CRNTSTTS"        , i, "0").trim();
                                           strCol[38]                         =intSrNo+"";
                                             strCol[39]             = RQODRRKOTSGDTLS.getStringItemDef("F_CMDTCODE"        , i,    "0").trim(); /*SCHEME CODE*/
                                              strCol[40]             = RQODRRKOTSGDTLS.getStringItemDef("F_EXPDLDNGDATE"        , i,   "0").trim(); /*EXPECTED LOAD*/
                                              strCol[41]             = RQODRRKOTSGDTLS.getStringItemDef("F_BVDMFLAG"        , i,       "0").trim(); /*PREMIUM NON PREMIUM  CODE*/
                                              strCol[42]             = RQODRRKOTSGDTLS.getStringItemDef("F_STTNTOAMNT"        , i,     "0").trim(); /*FOR CLUSTER*/
                                                                                
                                           list.add(i,strCol);
                                         }
                                             
                                         else
                                         {
                                                 
                                                       strCol[0]                                      = "";                                                                                          
                                                               strCol[1]                              = "";                                                                                       
                                                               strCol[2]                              = "";                                                                                           
                                                               strCol[3]                                      = "";                                                                                            
                                                               strCol[4]                              = "";                                                                                   
                                                               strCol[5]                              = "";                                                                                    
                                                               strCol[6]                              = "";                                                                                          
                                                               strCol[7]                              = "";                                                                                           
                                                               strCol[8]                              = "";                                                                                       
                                                               strCol[9]                              ="";                                                                                       
                                                               strCol[10]                             = "";                                                                                      
                                                               strCol[11]                             = "";                                                                                      
                                                               strCol[12]                             = "";                                                                                      
                                                               strCol[13]                             = "";                                                                                      
                                                               strCol[14]                             = "";                                                                                      
                                                               strCol[15]                             = "";                                                                                      
                                                               strCol[16]                             = "";                                                                                      
                                                               strCol[17]                             = "";                                                                                          
                                                               strCol[18]                             = "";  
                                                               strCol[19]                             = "";                                                                                           
                                                               strCol[20]                             = strSubTotUnts;                                                                                          
                                                               strCol[21]                             = strSubTotOtsgFrwh;/*FOR OUTSTANDING FOUR WHELER*/                                                                                      
                                                               strCol[22]                             = strSubTotFrwh;                                                                                   
                                                               strCol[23]                             = "";                                                                                    
                                                               strCol[24]                             = "";                                                                          
                                                               strCol[25]                             = "";                                                                                      
                                                               strCol[26]                             = strSubTotLoadFrwh;                                                                                     
                                           strCol[27]                 = strSubTotCnclFrwh;                                                               
                                           strCol[28]                 = strSubTotFrftFrwh;                                                               
                                           strCol[29]                 = "";                                                                   
                                           strCol[30]                 = "";                                                        
                                           strCol[31]                 = "";                                                       
                                           strCol[32]                         = "";                                                       
                                           strCol[33]                 = "";                                                       
                                           strCol[34]                         = "";                                                       
                                           strCol[35]                 = "";                                                           
                                           strCol[36]                 = "";                                                       
                                           strCol[37]                 = "";                                      
                                                       strCol[38]                                     ="SUB TOTAL";
                                             
                                             strCol[39]              = "";/*SCHEME CODE*/
                                             strCol[40]                  = "";   /*EXPECTED LOAD*/
                                             strCol[41]                  = "";   /*PREMIUM NON PREMIUM  CODE*/
                                             strCol[42]                  = "";   /*FOR CLUSTER*/
                                           list.add(i,strCol);
                                           
                                              fltSubTotUnts         = Double.valueOf(strUnits).doubleValue();      
                                              fltSubTotFrwh         = Double.valueOf(strFrwh).doubleValue();
                                              fltSubTotPlctUnts     = Double.valueOf(strPlctUnts).doubleValue();     
                                              fltSubTotLoadFrwh     = Double.valueOf(strLoadFrwh).doubleValue();
                                              fltSubTotCnclFrwh     = Double.valueOf(strCnclFrwh).doubleValue();
                                              fltSubTotFrftFrwh     = Double.valueOf(strFrftFrwh).doubleValue();
                                              fltSubTotOtsgFrwh     = Double.valueOf(strOtsgFrwh).doubleValue();
                                              strSubTotUnts             = "" + fltSubTotUnts;       
                                              strSubTotFrwh             = "" + fltSubTotFrwh; 
                                              strSubTotPlctUnts         = "" + fltSubTotPlctUnts;     
                                              strSubTotLoadFrwh         = "" + fltSubTotLoadFrwh;
                                              strSubTotCnclFrwh         = "" + fltSubTotCnclFrwh;
                                              strSubTotFrftFrwh         = "" + fltSubTotFrftFrwh;
                                              strSubTotOtsgFrwh         = "" + fltSubTotOtsgFrwh;
                                             iCounter = 0;
                                             intSrNo = 1;
                                         }   
                                         strPrevDvsnFrom = strDvsnFrom; 
                                         strid = "" + intSrNo;
                                         iCounter++;
                           
                                              }
                                       
                                                      String[] strCol= new String[45];
                                                      strTotUnts     = "" + fltTotUnts;
                                                      strTotFrwh     = "" + fltTotFrwh;
                                                      strTotPlctUnts = "" + fltTotPlctUnts;
                                                      strTotLoadFrwh = "" + fltTotLoadFrwh;
                                                      strTotCnclFrwh = "" + fltTotCnclFrwh;
                                                      strTotFrftFrwh = "" + fltTotFrftFrwh;
                                                      strTotOtsgFrwh = "" + fltTotOtsgFrwh;
                                                      
                                                       logger.info("in last  : " + intRowCount);
                                                                      
                                                       
                                                       strCol[0]                              = "";                                                                                          
                                                       strCol[1]                      = "";                                                                                       
                                                       strCol[2]                      = "";                                                                                           
                                                       strCol[3]                              = "";                                                                                            
                                                       strCol[4]                              = "";                                                                                   
                                                       strCol[5]                      = "";                                                                                    
                                                       strCol[6]                      = "";                                                                                          
                                                       strCol[7]                      = "";                                                                                           
                                                       strCol[8]                      = "";                                                                                       
                                                       strCol[9]                      ="";                                                                                       
                                                       strCol[10]                     = "";                                                                                      
                                                       strCol[11]                     = "";                                                                                      
                                                       strCol[12]                     = "";                                                                                      
                                                       strCol[13]                     = "";                                                                                      
                                                       strCol[14]                     = "";                                                                                      
                                                       strCol[15]                     = "";                                                                                      
                                                       strCol[16]                     = "";                                                                                      
                                                       strCol[17]                     = "";                                                                                          
                                                       strCol[18]                     = "";
                                                       strCol[19]                     = "";                                                                                           
                                                       strCol[20]                     = strSubTotUnts;                                                                                          
                                                       strCol[21]                     = strSubTotOtsgFrwh;/*FOR OUTSTANDING FOUR WHELER*/                                                                                      
                                                       strCol[22]                     = strSubTotFrwh;                                                                                   
                                                       strCol[23]                     = "";                                                                                    
                                                       strCol[24]                     = "";                                                                          
                                                       strCol[25]                     = "";                                                                                      
                                                       strCol[26]                     = strSubTotLoadFrwh;                                                                                     
                                   strCol[27]             = strSubTotCnclFrwh;                                                               
                                   strCol[28]             = strSubTotFrftFrwh;                                                               
                                   strCol[29]             = "";                                                                   
                                   strCol[30]             = "";                                                        
                                   strCol[31]             = "";                                                       
                                   strCol[32]             = "";                                                       
                                   strCol[33]             = "";                                                       
                                   strCol[34]             = "";                                                       
                                   strCol[35]             = "";                                                           
                                   strCol[36]             = "";                                                       
                                   strCol[37]             = "";                                          
                                               strCol[38]                             ="SUB TOTAL";
                       strCol[39]              = "";/*SCHEME CODE*/
                                           strCol[40]                  = "";   /*EXPECTED LOAD*/
                                           strCol[41]                  = "";   /*PREMIUM NON PREMIUM  CODE*/
                                           strCol[42]                  = "";   /*FOR CLUSTER*/
                                                   list.add(intRowCount,strCol);
                                                   
                                              logger.info("strTotUnts"+strTotUnts)  ;
                                              logger.info("strTotFrftFrwh"+strTotFrftFrwh)  ;
                                              logger.info("strTotFrwh"+strTotFrwh)  ;
                                              logger.info("strTotPlctUnts"+strTotPlctUnts)  ;
                                              logger.info("strTotLoadFrwh"+strTotLoadFrwh)  ;
                                              logger.info("strTotCnclFrwh"+strTotCnclFrwh)  ;
                                              logger.info("strTotCnclFrwh"+strTotCnclFrwh)  ;
                                              String[] strCol1= new String[45];
                                                   
                                                   strCol1[0]                                 = "";                                                                                          
                                                                       strCol1[1]                     = "";                                                                                       
                                                                       strCol1[2]                     = "";                                                                                           
                                                                       strCol1[3]                             = "";                                                                                            
                                                                       strCol1[4]                             = "";                                                                                   
                                                                       strCol1[5]                     = "";                                                                                    
                                                                       strCol1[6]                     = "";                                                                                          
                                                                       strCol1[7]                     = "";                                                                                           
                                                                       strCol1[8]                     = "";                                                                                       
                                                                       strCol1[9]                     ="";                                                                                       
                                                                       strCol1[10]                    = "";                                                                                      
                                                                       strCol1[11]                    = "";                                                                                      
                                                                       strCol1[12]                    = "";                                                                                      
                                                                       strCol1[13]                    = "";                                                                                      
                                                                       strCol1[14]                    = "";                                                                                      
                                                                       strCol1[15]                    = "";                                                                                      
                                                                       strCol1[16]                    = "";                                                                                      
                                                                       strCol1[17]                    = "";                                                                                          
                                                                       strCol1[18]                    = "";
                                                                       strCol1[19]                    = "";                                                                                           
                                                                       strCol1[20]                    = strTotUnts;                                                                                          
                                                                       strCol1[21]                    = strTotOtsgFrwh;/*FOR OUTSTANDING FOUR WHELER*/                                                                                      
                                                                       strCol1[22]                    = strTotFrwh;                                                                                   
                                                                       strCol1[23]                    = "";                                                                                    
                                                                       strCol1[24]                    = "";                                                                          
                                                                       strCol1[25]                    = "";                                                                                      
                                                                       strCol1[26]                    = strTotLoadFrwh;                                                                                     
                                                   strCol1[27]             = strTotCnclFrwh;                                                               
                                                   strCol1[28]             = strTotFrftFrwh;                                                               
                                                   strCol1[29]             = "";                                                                   
                                                   strCol1[30]             = "";                                                        
                                                   strCol1[31]             = "";                                                       
                                                   strCol1[32]             = "";                                                       
                                                   strCol1[33]             = "";                                                       
                                                   strCol1[34]             = "";                                                       
                                                   strCol1[35]             = "";                                                           
                                                   strCol1[36]             = "";                                                       
                                                   strCol1[37]             = "";                                         
                                                               strCol1[38]                            ="G. TOTAL";
                       
                       strCol1[39]                     = "";/*SCHEME CODE*/
                       strCol1[40]                     = "";   /*EXPECTED LOAD*/
                       strCol1[41]                     = "";   /*PREMIUM NON PREMIUM  CODE*/
                       strCol1[42]                     = "";   /*FOR CLUSTER*/                                                    
                                                   list.add(intRowCount+1,strCol1);

                           
                           RQODRRKOTSGDTLS.endSession();
                           logger.info("End RQODRRKOTSGDTLS.");
                   }
                   catch (Exception e)
                   {
                           logger.info("There was an exception while creating and using the FOIS."+e);
                       strCallStts="F";
                                      strCallMesg="Problem in Data Filling";
                                      

                   }
                   finally
                   {
                   try
                   {
                           RQODRRKOTSGDTLS=null;
                   }
                   catch(Exception e)
                   {
                           logger.info("Errror In Finally.");
                       strCallStts="F";
                                      strCallMesg="Problem in Data Filling";
                       
                   }
              }
                   
                   logger.info("SUCCESSFULL");
          logger.info("Sucessfull Execution of SHY_OdrRasTX ||RQODRRKOTSGDTLS"); 
       
      strData="";
      
   try
   {
    int intDataLen=list.size();
    if (intDataLen>0)
    {
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for (int i=0; i<list.size(); i++)
    {      
    String[] strCols=new String[45];
    strCols=list.get(i);
              
                              
        if(i==(intDataLen-1))
                strData+="{\"srno\":\""+strCols[38]+"\",\"dvsnfrom\":\""+strCols[0]+"\",\"clstr\":\""+strCols[42]+"\",\"sttnfrom\":\""+strCols[10]+"\",\"sttnto\":\""+strCols[3]+"\",\"dmndno\":\""+strCols[11]+"\",\"dmnddate\":\""+strCols[12]+"\",\"dmndtime\":\""+strCols[13]+"\",\"cnsr\":\""+strCols[6]+"\",\"cnsg\":\""+strCols[7]+"\",\"cmdt\":\""+strCols[2]+"\",\"spclno\":\""+strCols[14]+"\",\"trfctype\":\""+strCols[15]+"\",\"prtyclas\":\""+strCols[1]+"\",\"prmbkgclas\":\""+strCols[16]+"\",\"rstn\":\""+strCols[9]+"\",\"via\":\""+strCols[19]+"\",\"indttype\":\""+strCols[8]+"\",\"indtunts\":\""+strCols[20]+"\",\"indt8w\":\""+strCols[22]+"\",\"load8w\":\""+strCols[26]+"\",\"cncl8w\":\""+strCols[27]+"\",\"forefit\":\""+strCols[28]+"\",\"ostg8w\":\""+strCols[21]+"\",\"schmcode\":\""+strCols[39]+"\",\"premflag\":\""+strCols[41]+"\",\"expldng\":\""+strCols[40]+"\"}";
        else
        strData+="{\"srno\":\""+strCols[38]+"\",\"dvsnfrom\":\""+strCols[0]+"\",\"clstr\":\""+strCols[42]+"\",\"sttnfrom\":\""+strCols[10]+"\",\"sttnto\":\""+strCols[3]+"\",\"dmndno\":\""+strCols[11]+"\",\"dmnddate\":\""+strCols[12]+"\",\"dmndtime\":\""+strCols[13]+"\",\"cnsr\":\""+strCols[6]+"\",\"cnsg\":\""+strCols[7]+"\",\"cmdt\":\""+strCols[2]+"\",\"spclno\":\""+strCols[14]+"\",\"trfctype\":\""+strCols[15]+"\",\"prtyclas\":\""+strCols[1]+"\",\"prmbkgclas\":\""+strCols[16]+"\",\"rstn\":\""+strCols[9]+"\",\"via\":\""+strCols[19]+"\",\"indttype\":\""+strCols[8]+"\",\"indtunts\":\""+strCols[20]+"\",\"indt8w\":\""+strCols[22]+"\",\"load8w\":\""+strCols[26]+"\",\"cncl8w\":\""+strCols[27]+"\",\"forefit\":\""+strCols[28]+"\",\"ostg8w\":\""+strCols[21]+"\",\"schmcode\":\""+strCols[39]+"\",\"premflag\":\""+strCols[41]+"\",\"expldng\":\""+strCols[40]+"\"},";
        
         
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    } 
    
    }
if (si_Optn.equals("RASCOALAlotment")) /*RAS-COAL COAL ALLOTMENT*/
    {
         logger.info("IN RAScoaL ");

                String siDvsn                   = req.getParameter("txtDvsnCode").trim().toUpperCase();
                String siCustCode               = req.getParameter("txtCustCode").trim().toUpperCase();
                String siDateFrom               = req.getParameter("txtDateFrom");
                String siDateTo                 = req.getParameter("txtDateTo");
                String siPrty                   = req.getParameter("txtPrtyCode").trim().toUpperCase();
       logger.info("siDvsn"+ siDvsn);
                logger.info("siCustCode" + siCustCode);
                logger.info("siDateFrom"+ siDateFrom);
                logger.info("siDateTo" + siDateTo);
                logger.info("siPrty" + siPrty);
                    
   if (((siDvsn.equals(""))||(siDvsn==null)))
                {
                        logger.info("Division Is Mandatory");
                    strCallStts="F";
                    strCallMesg="Division Is Mandatory";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }
                  if (((siPrty.equals(""))||(siPrty==null)))
                {
                        logger.info("Priority Is Mandatory");
                    strCallStts="F";
                    strCallMesg="Priority Is Mandatory";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }
    if (((siDateFrom.equals(""))||(siDateFrom==null)))
    {
            logger.info("Date  From Is Mandatory");
        strCallStts="F";
        strCallMesg="Date From Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
      if (((siDateTo.equals(""))||(siDateTo==null)))
    {
            logger.info("Date  To Is Mandatory");
        strCallStts="F";
        strCallMesg="Date To Is Mandatory";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
   
                    
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(siDvsn, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Division");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
        bolValid=true;
     bolValid=   vldt.valdInpt(siPrty, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Priority");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Priority";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
        bolValid=true;
     bolValid=   vldt.valdInpt(siCustCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In CustCode");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In CustCode";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
       
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
    try
    {
    String strRASCOALAlotment[][]=obj.geRASCOALAlotment(si_UserID, si_ClntID,siDvsn,siCustCode,siDateFrom,siDateTo,siPrty);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
    int intDataLen=strRASCOALAlotment.length;
    if (intDataLen>0)
    {
        
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {       
        
        
        if(i==(intDataLen-1))
                               strData+="{\"mnth\":\""+strRASCOALAlotment[i][0]+"\",\"cnsmr\":\""+strRASCOALAlotment[i][1]+"\",\"dstn\":\""+strRASCOALAlotment[i][2]+"\",\"cn\":\""+strRASCOALAlotment[i][3]+"\",\"prty\":\""+strRASCOALAlotment[i][4]+"\",\"sntdbxs\":\""+strRASCOALAlotment[i][5]+"\",\"sntdate\":\""+strRASCOALAlotment[i][6]+"\",\"spnsrcode\":\""+strRASCOALAlotment[i][7]+"\",\"coalclass\":\""+strRASCOALAlotment[i][8]+"\",\"pilot\":\""+strRASCOALAlotment[i][9]+"\",\"rake\":\""+strRASCOALAlotment[i][10]+"\",\"pgmvldt\":\""+strRASCOALAlotment[i][11]+"\",\"alotdate\":\""+strRASCOALAlotment[i][13]+"\",\"ldngdate\":\""+strRASCOALAlotment[i][14]+"\",\"mnthalot\":\""+strRASCOALAlotment[i][15]+"\",\"splymnth\":\""+strRASCOALAlotment[i][16]+"\",\"alotblnc\":\""+strRASCOALAlotment[i][19]+"\",\"areralotbox\":\""+strRASCOALAlotment[i][18]+"\",\"areralotdate\":\""+strRASCOALAlotment[i][17]+"\"}";
                       else
                       strData+="{\"mnth\":\""+strRASCOALAlotment[i][0]+"\",\"cnsmr\":\""+strRASCOALAlotment[i][1]+"\",\"dstn\":\""+strRASCOALAlotment[i][2]+"\",\"cn\":\""+strRASCOALAlotment[i][3]+"\",\"prty\":\""+strRASCOALAlotment[i][4]+"\",\"sntdbxs\":\""+strRASCOALAlotment[i][5]+"\",\"sntdate\":\""+strRASCOALAlotment[i][6]+"\",\"spnsrcode\":\""+strRASCOALAlotment[i][7]+"\",\"coalclass\":\""+strRASCOALAlotment[i][8]+"\",\"pilot\":\""+strRASCOALAlotment[i][9]+"\",\"rake\":\""+strRASCOALAlotment[i][10]+"\",\"pgmvldt\":\""+strRASCOALAlotment[i][11]+"\",\"alotdate\":\""+strRASCOALAlotment[i][13]+"\",\"ldngdate\":\""+strRASCOALAlotment[i][14]+"\",\"mnthalot\":\""+strRASCOALAlotment[i][15]+"\",\"splymnth\":\""+strRASCOALAlotment[i][16]+"\",\"alotblnc\":\""+strRASCOALAlotment[i][19]+"\",\"areralotbox\":\""+strRASCOALAlotment[i][18]+"\",\"areralotdate\":\""+strRASCOALAlotment[i][17]+"\"},"; 
                     
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
       
        logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASCOALAlotmentOdr")) /*RAS-COAL Arrear of  Coal Allotment ODRWISE*/
    {
         logger.info("IN RAScoaL Allotment OdrWise ");

            String strDvsnCode          =       "";
                String strPrtyCode              =       "";
                String strSpsrCode              =       "";
                String strDest                  =       "";
                String strPilotCode             =       "";
                String strFeldCode              =       "";
                String strCustCode              =       "";
                
                if(req.getParameter("txtDvsnCode")!= null)
                        strDvsnCode =   req.getParameter("txtDvsnCode").trim();
                if(req.getParameter("txtPrtyCode")!= null)
                        strPrtyCode =   req.getParameter("txtPrtyCode").trim();
                if(req.getParameter("txtSpsrCode")!= null)
                        strSpsrCode =   req.getParameter("txtSpsrCode").trim();
                if(req.getParameter("txtCustCode")!= null)
                        strCustCode =   req.getParameter("txtCustCode").trim();
                if(req.getParameter("txtDest")!= null)
                        strDest =       req.getParameter("txtDest").trim();
                if(req.getParameter("txtFeldCode")!= null)
                        strFeldCode =   req.getParameter("txtFeldCode").trim();
                if(req.getParameter("txtPiltCode")!= null)
                        strPilotCode =  req.getParameter("txtPiltCode").trim();                 


                String strOptnSupl              = req.getParameter("optSupl").trim().toUpperCase();
                String siCmdt                   =       "COAL";

                
       logger.info("strDvsn"+ strDvsnCode);
                logger.info("strPrtyCode" + strPrtyCode);
                logger.info("strSpsrCode" + strSpsrCode);               
                logger.info("strCustCode"+ strCustCode);
                logger.info("strFeldCode" + strFeldCode);
                logger.info("strDest" + strDest);
                logger.info("strPilotCode" + strPilotCode);
                logger.info("strOptnSupl" + strOptnSupl);
                    
   if (((strDvsnCode.equals(""))||(strDvsnCode==null)))
                {
                        logger.info("Division Is Mandatory");
                    strCallStts="F";
                    strCallMesg="Division Is Mandatory";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }
               
   
                    
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(strDvsnCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Division");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
     bolValid=   vldt.valdInpt(strPrtyCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Priority");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Priority";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
     bolValid=   vldt.valdInpt(strSpsrCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Sponsor Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Sponsor Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
       bolValid=   vldt.valdInpt(strCustCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Customer Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Customer Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
       bolValid=   vldt.valdInpt(strFeldCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Field Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Field Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }  
    bolValid=true;
     bolValid=   vldt.valdInpt(strDest, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Destination");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Destination";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }   
    bolValid=true;
    bolValid=   vldt.valdInpt(strPilotCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Pilot Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Pilot Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
       
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
    try
    {
    String strRASCOALAlotmentOdr[][]=obj.geRASCOALAlotmentOdr(si_UserID, si_ClntID,strDvsnCode,strPrtyCode,strSpsrCode,strCustCode,strFeldCode,strDest,strPilotCode,strOptnSupl);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
    int intDataLen=strRASCOALAlotmentOdr.length;
    if (intDataLen>0)
    {
        
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {       
        
        
        if(i==(intDataLen-1))
        strData+="{\"alotdate\":\""+strRASCOALAlotmentOdr[i][0]+"\",\"prty\":\""+strRASCOALAlotmentOdr[i][5]+"\",\"sttn\":\""+strRASCOALAlotmentOdr[i][1]+"\",\"cnsr\":\""+strRASCOALAlotmentOdr[i][2]+"\",\"coalfield\":\""+strRASCOALAlotmentOdr[i][9]+"\",\"sttnto\":\""+strRASCOALAlotmentOdr[i][3]+"\",\"cust\":\""+strRASCOALAlotmentOdr[i][4]+"\",\"rakesqnc\":\""+strRASCOALAlotmentOdr[i][10]+"\",\"alotunts\":\""+strRASCOALAlotmentOdr[i][7]+"\",\"rmrk\":\""+strRASCOALAlotmentOdr[i][11]+"\"}";
       else
           strData+="{\"alotdate\":\""+strRASCOALAlotmentOdr[i][0]+"\",\"prty\":\""+strRASCOALAlotmentOdr[i][5]+"\",\"sttn\":\""+strRASCOALAlotmentOdr[i][1]+"\",\"cnsr\":\""+strRASCOALAlotmentOdr[i][2]+"\",\"coalfield\":\""+strRASCOALAlotmentOdr[i][9]+"\",\"sttnto\":\""+strRASCOALAlotmentOdr[i][3]+"\",\"cust\":\""+strRASCOALAlotmentOdr[i][4]+"\",\"rakesqnc\":\""+strRASCOALAlotmentOdr[i][10]+"\",\"alotunts\":\""+strRASCOALAlotmentOdr[i][7]+"\",\"rmrk\":\""+strRASCOALAlotmentOdr[i][11]+"\"},"; 
                        
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("RASCOALAlotmentthrty")) /*RAS-COAL Arrear of Allotment More Than 30 Days*/
    {
         logger.info("IN RAScoaL Allotment OdrWise ");

            String strDvsnCode          =       "";
                String strPrtyCode              =       "";
                String strSpsrCode              =       "";
                String strDest                  =       "";
                String strPilotCode             =       "";
                String strFeldCode              =       "";
                String strCustCode              =       "";
                
                if(req.getParameter("txtDvsnCode")!= null)
                        strDvsnCode =   req.getParameter("txtDvsnCode").trim();
                if(req.getParameter("txtPrtyCode")!= null)
                        strPrtyCode =   req.getParameter("txtPrtyCode").trim();
                if(req.getParameter("txtSpsrCode")!= null)
                        strSpsrCode =   req.getParameter("txtSpsrCode").trim();
                if(req.getParameter("txtCustCode")!= null)
                        strCustCode =   req.getParameter("txtCustCode").trim();
                if(req.getParameter("txtDest")!= null)
                        strDest =       req.getParameter("txtDest").trim();
                if(req.getParameter("txtFeldCode")!= null)
                        strFeldCode =   req.getParameter("txtFeldCode").trim();
                if(req.getParameter("txtPiltCode")!= null)
                        strPilotCode =  req.getParameter("txtPiltCode").trim();                 


                String strOptnSupl              = req.getParameter("optSupl").trim().toUpperCase();
                String siCmdt                   =       "COAL";

                
       logger.info("strDvsn"+ strDvsnCode);
                logger.info("strPrtyCode" + strPrtyCode);
                logger.info("strSpsrCode" + strSpsrCode);               
                logger.info("strCustCode"+ strCustCode);
                logger.info("strFeldCode" + strFeldCode);
                logger.info("strDest" + strDest);
                logger.info("strPilotCode" + strPilotCode);
                logger.info("strOptnSupl" + strOptnSupl);
                    
   if (((strDvsnCode.equals(""))||(strDvsnCode==null)))
                {
                        logger.info("Division Is Mandatory");
                    strCallStts="F";
                    strCallMesg="Division Is Mandatory";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }
               
   
                    
    util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
    bolValid=true;
    bolValid=   vldt.valdInpt(strDvsnCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Division");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Division";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
     bolValid=   vldt.valdInpt(strPrtyCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Priority");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Priority";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
     bolValid=   vldt.valdInpt(strSpsrCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Sponsor Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Sponsor Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
       bolValid=   vldt.valdInpt(strCustCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Customer Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Customer Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
     bolValid=true;
       bolValid=   vldt.valdInpt(strFeldCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Field Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Field Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }  
    bolValid=true;
     bolValid=   vldt.valdInpt(strDest, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Destination");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Destination";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }   
    bolValid=true;
    bolValid=   vldt.valdInpt(strPilotCode, "A");
    if (bolValid==true)
    {
    
    }
    else 
    {
        logger.info("Only Alphabetic Characters  are  Allowed In Pilot Code");
        strCallStts="F";
        strCallMesg="Only Alphabetic Characters  are  Allowed In Pilot Code";
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        res.getWriter().write(strData);
        return;
    }
       
                    
                    
    SHY_OdrRasTX obj=new SHY_OdrRasTX();
                        
    try
    {
    String strRASCOALAlotmentthrty[][]=obj.geRASCOALAlotmentthrty(si_UserID, si_ClntID,strDvsnCode,strPrtyCode,strSpsrCode,strCustCode,strFeldCode,strDest,strPilotCode,strOptnSupl);
         strCallStts=obj.getCallStts();
         strCallMesg=obj.getCallMesg();
         strData="";
    int intDataLen=strRASCOALAlotmentthrty.length;
    if (intDataLen>0)
    {
        
         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
    for(int i=0;i<intDataLen;i++)
    {       
        
        
        if(i==(intDataLen-1))
        strData+="{\"alotdate\":\""+strRASCOALAlotmentthrty[i][0]+"\",\"prty\":\""+strRASCOALAlotmentthrty[i][5]+"\",\"sttn\":\""+strRASCOALAlotmentthrty[i][1]+"\",\"cnsr\":\""+strRASCOALAlotmentthrty[i][2]+"\",\"coalfield\":\""+strRASCOALAlotmentthrty[i][9]+"\",\"sttnto\":\""+strRASCOALAlotmentthrty[i][3]+"\",\"cust\":\""+strRASCOALAlotmentthrty[i][4]+"\",\"alotunts\":\""+strRASCOALAlotmentthrty[i][7]+"\",\"rmrk\":\""+strRASCOALAlotmentthrty[i][11]+"\"}";
       else
        strData+="{\"alotdate\":\""+strRASCOALAlotmentthrty[i][0]+"\",\"prty\":\""+strRASCOALAlotmentthrty[i][5]+"\",\"sttn\":\""+strRASCOALAlotmentthrty[i][1]+"\",\"cnsr\":\""+strRASCOALAlotmentthrty[i][2]+"\",\"coalfield\":\""+strRASCOALAlotmentthrty[i][9]+"\",\"sttnto\":\""+strRASCOALAlotmentthrty[i][3]+"\",\"cust\":\""+strRASCOALAlotmentthrty[i][4]+"\",\"alotunts\":\""+strRASCOALAlotmentthrty[i][7]+"\",\"rmrk\":\""+strRASCOALAlotmentthrty[i][11]+"\"},";
                        
    }
    strData+="]}";
    }
    else
     {
            logger.info("No Data Found");
            strCallStts="F";
            strCallMesg="NO DATA FOUND";
            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
        }
    logger.info(strData);
    res.getWriter().write(strData);
    }
    catch(Exception e)
    {
    logger.info("Error In Data");
        strCallStts="F";
        strCallMesgFinal="Error In Data "+strCallMesg;
        strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
    res.getWriter().write(strData);
    return;
    
    }
    
    }
if (si_Optn.equals("ODROtsgDtls")) /*ODRWISE Rake Outstanding and Matuered Indents*/
    {
                logger.info("In ODR OUTSTANDING QUERY");         
                String strQryType = (String)req.getParameter("Qry").toUpperCase();
                String strZone = (String)req.getParameter("Zone").toUpperCase();       
                logger.info("Qry: "+strQryType);
                logger.info("strZone"+ strZone);
                

                if (((strZone.equals(""))||(strZone==null)))
                {
                        logger.info("Zone Is Mandatory");
                    strCallStts="F";
                    strCallMesg="Zone Is Mandatory";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }
              
                util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
                bolValid=true;
                bolValid=   vldt.valdInpt(strZone, "A");
                if (bolValid==true)
                {
                   
                }
                else 
                {
                        logger.info("Only Alphabetic Characters  are  Allowed In Zone");
                    strCallStts="F";
                    strCallMesg="Only Alphabetic Characters  are  Allowed In Zone";
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                    res.getWriter().write(strData);
                    return;
                }

                    
                    
                    
                SHY_OdrRasTX obj=new SHY_OdrRasTX();
                                        
                try
                {
                    String strODROtsgDtls[][]=obj.getODROtsgDtls(si_UserID, si_ClntID,strZone,strQryType);
                     strCallStts=obj.getCallStts();
                     strCallMesg=obj.getCallMesg();
                      
                      strData="";
                    int intDataLen=strODROtsgDtls.length;
                    logger.info("intDataLen" + intDataLen);
                    if (intDataLen>0)
                    {
                        logger.info("strQryType" + strQryType);
                        if(strQryType.equals("ODR_RK_OTSG"))
                        {
                         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
                            for(int i=0;i<intDataLen-1;i++)
                            {                               
                                strData+="{\"dvsn\":\""+strODROtsgDtls[i][0]+"\",\"sttnfrom\":\""+strODROtsgDtls[i][1]+"\",\"dmndno\":\""+strODROtsgDtls[i][2]+"\",\"dmnddate\":\""+strODROtsgDtls[i][3]+"\",\"dmndtime\":\""+strODROtsgDtls[i][4]+"\",\"csnr\":\""+strODROtsgDtls[i][5]+"\",\"cnsg\":\""+strODROtsgDtls[i][6]+"\",\"cmdt\":\""+strODROtsgDtls[i][7]+"\",\"tt\":\""+strODROtsgDtls[i][9]+"\",\"pc\":\""+strODROtsgDtls[i][10]+"\",\"pbf\":\""+strODROtsgDtls[i][11]+"\",\"via\":\""+strODROtsgDtls[i][15]+"\",\"rakecmdt\":\""+strODROtsgDtls[i][29]+"\",\"dstn\":\""+strODROtsgDtls[i][16]+"\",\"indttype\":\""+strODROtsgDtls[i][17]+"\",\"indtunit\":\""+strODROtsgDtls[i][18]+"\",\"indt8w\":\""+strODROtsgDtls[i][19]+"\",\"ostgunit\":\""+strODROtsgDtls[i][23]+"\",\"ostg8w\":\""+strODROtsgDtls[i][24]+"\",\"spldunit\":\""+strODROtsgDtls[i][25]+"\",\"spldtime\":\""+strODROtsgDtls[i][26]+"\"},";
                            }
                           strData+="{\"dvsn\":\"TOTAL"+"\",\"sttnfrom\":\""+"\",\"dmndno\":\""+"\",\"dmnddate\":\""+"\",\"dmndtime\":\""+"\",\"csnr\":\""+"\",\"cnsg\":\""+"\",\"cmdt\":\""+"\",\"tt\":\""+"\",\"pc\":\""+"\",\"pbf\":\""+"\",\"via\":\""+"\",\"rakecmdt\":\""+"\",\"dstn\":\""+"\",\"indttype\":\""+"\",\"indtunit\":\""+strODROtsgDtls[intDataLen-1][18]+"\",\"indt8w\":\""+strODROtsgDtls[intDataLen-1][19]+"\",\"ostgunit\":\""+strODROtsgDtls[intDataLen-1][23]+"\",\"ostg8w\":\""+strODROtsgDtls[intDataLen-1][24]+"\",\"spldunit\":\""+strODROtsgDtls[intDataLen-1][25]+"\",\"spldtime\":\""+"\"}";
                                strData+="]}";
                       }
                        if(strQryType.equals("MATURED_INDENTS"))
                        {
                         strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"data\":[";
                            for(int i=0;i<intDataLen-1;i++)
                            {                               
                                strData+="{\"dvsn\":\""+strODROtsgDtls[i][0]+"\",\"sttnfrom\":\""+strODROtsgDtls[i][1]+"\",\"dmndno\":\""+strODROtsgDtls[i][2]+"\",\"dmnddate\":\""+strODROtsgDtls[i][3]+"\",\"dmndtime\":\""+strODROtsgDtls[i][4]+"\",\"csnr\":\""+strODROtsgDtls[i][5]+"\",\"cnsg\":\""+strODROtsgDtls[i][6]+"\",\"cmdt\":\""+strODROtsgDtls[i][7]+"\",\"tt\":\""+strODROtsgDtls[i][9]+"\",\"pc\":\""+strODROtsgDtls[i][10]+"\",\"pbf\":\""+strODROtsgDtls[i][11]+"\",\"via\":\""+strODROtsgDtls[i][15]+"\",\"rakecmdt\":\""+strODROtsgDtls[i][29]+"\",\"dstn\":\""+strODROtsgDtls[i][16]+"\",\"indttype\":\""+strODROtsgDtls[i][17]+"\",\"indtunit\":\""+strODROtsgDtls[i][18]+"\",\"indt8w\":\""+strODROtsgDtls[i][19]+"\",\"metwithdate\":\""+strODROtsgDtls[i][26]+"\"},";
                            }
                            strData+="{\"dvsn\":\"TOTAL"+"\",\"sttnfrom\":\""+"\",\"dmndno\":\""+"\",\"dmnddate\":\""+"\",\"dmndtime\":\""+"\",\"csnr\":\""+"\",\"cnsg\":\""+"\",\"cmdt\":\""+"\",\"tt\":\""+"\",\"pc\":\""+"\",\"pbf\":\""+"\",\"via\":\""+"\",\"rakecmdt\":\""+"\",\"dstn\":\""+"\",\"indttype\":\""+"\",\"indtunit\":\""+strODROtsgDtls[intDataLen-1][18]+"\",\"indt8w\":\""+strODROtsgDtls[intDataLen-1][19]+"\",\"metwithdate\":\""+"\"}";
                            strData+="]}";
                        }
                    }
                    else
                     {
                            logger.info("No Data Found");
                            strCallStts="F";
                            strCallMesg="NO DATA FOUND";
                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"}";
                        }
                    logger.info(strData);
                    res.getWriter().write(strData);
                }
                catch(Exception e)
                {
                logger.info("Error In Data");
                    strCallStts="F";
                    strCallMesgFinal="Error In Data "+strCallMesg;
                    strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesgFinal+"\"}";
                res.getWriter().write(strData);
                return;
                    
                }
                
                }


        }
public void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException
	{
		doPost(request, response);
	}

}

