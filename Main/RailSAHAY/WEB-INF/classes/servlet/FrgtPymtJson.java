package servlet.AjaxData;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserProfDtls;
import model.FrgtPymtSrvc;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.HashMap;

import model.GG_NodlOfcrSrvc;

public class FrgtPymtJson extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(FrgtPymtJson.class.getName());
        UserProfDtls userdtls=null;
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling FrgtPymtJson.java...");
		HttpSession session=req.getSession();

                String si_ClntId = req.getHeader("X-FORWARDED-FOR");
                if (si_ClntId == null) 
                {
                        si_ClntId = req.getRemoteAddr();
                }
                if (si_ClntId.indexOf(",")>0)
                {
                        si_ClntId = si_ClntId.substring(0,si_ClntId.indexOf(","));
                }
                
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		String si_CustId = "";
                ArrayList<String> orgList=null;
		try
		{
                    si_CustId = (String) session.getAttribute("custuserid");
                    userdtls = new UserProfDtls(si_CustId);  
                    orgList = userdtls.getCustOrgn();
                }
                catch(Exception e) 
                {
                        
                }
                
                String si_Optn  = req.getParameter("Optn").toUpperCase().trim();
		
		if(si_Optn.equals("OTSG_PYMT_LIST"))
		{
                    logger.info("Case: List of Outstanding Payments");		
                    String si_Sttn	= req.getParameter("Sttn").toUpperCase().trim();
                    String si_DateFrom  = req.getParameter("DateFrom").toUpperCase().trim();
                    String si_DateTo  = req.getParameter("DateTo").toUpperCase().trim();
                    String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
                    //String si_InvcFlag  = req.getParameter("InvcFlag").toUpperCase().trim();
                    String si_InvcFlag  = "I";
                    logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_DateFrom:"+ si_DateFrom+", si_DateTo:"+si_DateTo+", si_InvcFlag:"+si_InvcFlag);
				
                    FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
				
			try
			{
				String strSrvcData[][];
				String strData="";
				strSrvcData=obj.getOtsgPymtList(si_Sttn, si_DateFrom, si_DateTo, si_InvcFlag);
				String strCallStts=obj.getCallStts();
				String strCallMesg=obj.getCallMesg();
                            
				strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"OtsgPymt\":[";							
                                int intDataLen=strSrvcData.length;
                                logger.info("Outstanding Payments List Length:"+intDataLen);
                                for(int i=0;i<intDataLen;i++)
                                {
                                    if(i==(intDataLen-1))
                                        strData+="{\"FNotNumb\":\""+strSrvcData[i][0]+"\",\"FNotId\":\""+strSrvcData[i][1]+"\",\"FNotDate\":\""+strSrvcData[i][2]+"\",\"InvcNumb\":\""+strSrvcData[i][3]+"\",\"InvcDate\":\""+strSrvcData[i][4]+"\",\"SttnFrom\":\""+strSrvcData[i][5]+"\",\"SttnTo\":\""+strSrvcData[i][6]+"\",\"Cust\":\""+strSrvcData[i][7]+"\",\"Wrfg\":\""+strSrvcData[i][8]+"\",\"FrgtOtsg\":\""+strSrvcData[i][9]+"\",\"Dmrg\":\""+strSrvcData[i][10]+"\",\"LoclChrg\":\""+strSrvcData[i][11]+"\",\"InvcId\":\""+strSrvcData[i][12]+"\",\"IWOWFlag\":\""+strSrvcData[i][13]+"\",\"PerdFrom\":\""+strSrvcData[i][14]+"\",\"PerdTo\":\""+strSrvcData[i][15]+"\",\"ChrgCode\":\""+strSrvcData[i][16]+"\",\"PaidType\":\""+strSrvcData[i][17]+"\"}";
                                    else
                                        strData+="{\"FNotNumb\":\""+strSrvcData[i][0]+"\",\"FNotId\":\""+strSrvcData[i][1]+"\",\"FNotDate\":\""+strSrvcData[i][2]+"\",\"InvcNumb\":\""+strSrvcData[i][3]+"\",\"InvcDate\":\""+strSrvcData[i][4]+"\",\"SttnFrom\":\""+strSrvcData[i][5]+"\",\"SttnTo\":\""+strSrvcData[i][6]+"\",\"Cust\":\""+strSrvcData[i][7]+"\",\"Wrfg\":\""+strSrvcData[i][8]+"\",\"FrgtOtsg\":\""+strSrvcData[i][9]+"\",\"Dmrg\":\""+strSrvcData[i][10]+"\",\"LoclChrg\":\""+strSrvcData[i][11]+"\",\"InvcId\":\""+strSrvcData[i][12]+"\",\"IWOWFlag\":\""+strSrvcData[i][13]+"\",\"PerdFrom\":\""+strSrvcData[i][14]+"\",\"PerdTo\":\""+strSrvcData[i][15]+"\",\"ChrgCode\":\""+strSrvcData[i][16]+"\",\"PaidType\":\""+strSrvcData[i][17]+"\"},";
                                }
                                strData+="]}";
				logger.info(strData);
				res.getWriter().write(strData);
			}
			catch(Exception e)
			{
				System.out.println("Error in Data Fetch");
				res.getWriter().write("Data Contains Error! or No Records");
			}
		}
	    
	    if(si_Optn.equals("OTSG_CHRG_SMRY"))
	    {
	        logger.info("Case: Summary of Outstanding Charges from RQFBDFRGTPYMT");          
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
                
	        logger.info("si_CustId:"+si_CustId+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData;
	                    String strData="";
	                    strSrvcData=obj.getOtsgChrgSmry();
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"OtsgChrgSmry\":\""+strSrvcData+"\"}";                                                        
                            logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("OTSG_CHRG"))
	    {
	        logger.info("Case: List of Outstanding Charges from RQFBDFRGTPYMT");          
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getOtsgChrg(si_Sttn);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"OtsgChrg\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Outstanding Payments List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        if(i==(intDataLen-1))
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\"}";
	                        else
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\"},";
	                    }
	                    strData+="]}";
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("CHRG_BRKUP"))
	    {
	        logger.info("Case: Breakup of Outstanding Charges from RQFBDFRGTPYMT");          
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
	        String si_FNOTId  = req.getParameter("FNOTId").toUpperCase().trim();
	        String si_ChrgType  = req.getParameter("ChrgType").toUpperCase().trim();
	        String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	        logger.info("si_InvcId:"+si_InvcId+", si_FNOTId:"+si_FNOTId+", si_ChrgType:"+ si_ChrgType+", si_IWOWFlag:"+si_IWOWFlag);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getOtsgChrgBrkup(si_Sttn, si_InvcId, si_FNOTId, si_ChrgType, si_IWOWFlag);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Brkup\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Breakup List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        if(i==(intDataLen-1))
	                        strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"}";
	                        else
	                        strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"},";
	                    }
	                    strData+="]}";
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("CHRG_DESC"))
	    {
	        logger.info("Case: Description of Charge Id from RQFBDFRGTPYMT");          
	        String si_ChrgId      = req.getParameter("ChrgId").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_ChrgId:"+si_ChrgId);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[];
	                    String strData="";
	                    strSrvcData=obj.getChrgDesc(si_ChrgId);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\"";                                                        
                            strData+=",\"Sttn\":\""+strSrvcData[0]+"\",\"SttnName\":\""+strSrvcData[1]+"\",\"Dvsn\":\""+strSrvcData[2]+"\",\"ChrgType\":\""+strSrvcData[3]+"\",\"IWOWFlag\":\""+strSrvcData[4]+"\",\"Cust\":\""+strSrvcData[5]+"\",\"Amnt\":\""+strSrvcData[6]+"\",\"FNOT\":\""+strSrvcData[7]+"\",\"FNOTFrom\":\""+strSrvcData[8]+"\",\"FNOTDvsnFrom\":\""+strSrvcData[9]+"\",\"FNOTTo\":\""+strSrvcData[10]+"\",\"FNOTDvsnTo\":\""+strSrvcData[11]+"\",\"Invc\":\""+strSrvcData[12]+"\",\"InvcFrom\":\""+strSrvcData[13]+"\",\"InvcDvsnFrom\":\""+strSrvcData[14]+"\",\"InvcTo\":\""+strSrvcData[15]+"\",\"InvcDvsnTo\":\""+strSrvcData[16]+"\",\"FNR\":\""+strSrvcData[17]+"\",\"RRNumb\":\""+strSrvcData[18]+"\",\"RRDate\":\""+strSrvcData[19]+"\",\"Cnsr\":\""+strSrvcData[20]+"\",\"Cnsg\":\""+strSrvcData[21]+"\",\"Cmdt\":\""+strSrvcData[22]+"\",\"LdUnts\":\""+strSrvcData[23]+"\",\"EmUnts\":\""+strSrvcData[24]+"\"";
	                    strData+="}";
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("PYMT_TRXN_HIST"))
	    {
	        logger.info("Case: PAYMENT TRANSACTION HISTORY from RQFBDFRGTPYMT");          
	        String si_TrxnId      = req.getParameter("TrxnId").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_TrxnId:"+si_TrxnId+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getPymtHist(si_TrxnId);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"PymtHist\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Payment History List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"ChrgId\":\""+strSrvcData[i][2]+"\",\"TrxnId\":\""+strSrvcData[i][3]+"\",\"TrxnTime\":\""+strSrvcData[i][4]+"\",\"InvcNumb\":\""+strSrvcData[i][5]+"\",\"InvcDate\":\""+strSrvcData[i][6]+"\",\"ChrgType\":\""+strSrvcData[i][7]+"\",\"SttnFrom\":\""+strSrvcData[i][8]+"\",\"SttnFromName\":\""+strSrvcData[i][9]+"\",\"SttnTo\":\""+strSrvcData[i][10]+"\",\"SttnToName\":\""+strSrvcData[i][11]+"\",\"PymtMode\":\""+strSrvcData[i][12]+"\",\"TrxnAmnt\":\""+strSrvcData[i][13]+"\",\"TrxnStts\":\""+strSrvcData[i][14]+"\",\"BankTrxnId\":\""+strSrvcData[i][15]+"\",\"BankTrxnDate\":\""+strSrvcData[i][16]+"\"}";
	                        
	                        if(i!=(intDataLen-1))
	                            strData+=",";
	                        
	                        //strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"}";
	                        //else
	                        //strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"},";
	                    }
	                    strData+="]}";
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }

	    if(si_Optn.equals("CHRG_BREAKUP"))
	    {
	        logger.info("Case: ADJUSTED CHARGES BREAKUP");          
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
	        String si_ChrgType  = req.getParameter("ChrgType").toUpperCase().trim();
	        String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
	        String si_Date=req.getParameter("InvcDate").toUpperCase().trim();
	        String si_Amnt=req.getParameter("Amnt").toUpperCase().trim();
	        String si_RlyGstin  = req.getParameter("RlyGstin").toUpperCase().trim();
	        String si_CnsrGstin=req.getParameter("CnsrGstin").toUpperCase().trim();
	        String si_CnsgGstin=req.getParameter("CnsgGstin").toUpperCase().trim();
	   
	        logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	        logger.info("si_InvcId:"+si_InvcId+", si_ChrgType:"+ si_ChrgType+", si_IWOWFlag:"+si_IWOWFlag);
	        logger.info("si_RlyGstin:"+si_RlyGstin+", si_CnsrGstin:"+si_CnsrGstin+", si_CnsgGstin:"+ si_CnsgGstin+", si_Date:"+si_Date);

	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	        try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getChrgBreakup(si_Sttn,si_Date,si_ChrgType,si_IWOWFlag,si_InvcId,si_Amnt,si_RlyGstin,si_CnsrGstin,si_CnsgGstin);
 						String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Breakup\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Breakup List Length:"+intDataLen);
	                    
	                   for(int i=0;i<intDataLen;i++)
	                    {
	                        if(i==(intDataLen-1))
	                        strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"}";
	                        else
	                        strData+="{\"ChrgCode\":\""+strSrvcData[i][0]+"\",\"ChrgName\":\""+strSrvcData[i][1]+"\",\"Amnt\":\""+strSrvcData[i][2]+"\"},";
	                    }
	                    strData+="]}";
	                    
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("GST_LIST"))
	    {
	        logger.info("Case: List of Cnsr/Cnsg GST ");          
	        String si_RptgSttn      = req.getParameter("RptgSttn").toUpperCase().trim();
	        String si_SttnFrom  = req.getParameter("SttnFrom").toUpperCase().trim();
	        String si_SttnTo  = req.getParameter("SttnTo").toUpperCase().trim();
	        String si_Cnsr  = req.getParameter("Cnsr").toUpperCase().trim();
	        String si_Cnsg  = req.getParameter("Cnsg").toUpperCase().trim();
	        String si_TrxnFlag  = req.getParameter("TrxnFlag").toUpperCase().trim();
	        String si_TrxnID  = req.getParameter("TrxnId").toUpperCase().trim();
                String si_HndlFlag=req.getParameter("HndlFlag").toUpperCase().trim();
	        String si_InvcFlag  = "I";
	        logger.info("si_RptgSttn:"+si_RptgSttn+", si_SttnFrom:"+si_SttnFrom+", si_SttnTo:"+ si_SttnTo+", si_Cnsr:"+si_Cnsr+", si_Cnsr:"+si_Cnsr+", si_TrxnFlag:"+si_TrxnFlag+", si_TrxnID:"+si_TrxnID);
	                    
	            FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_Cnsr, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getGSTList(si_RptgSttn,si_SttnFrom,si_SttnTo,si_Cnsr,si_Cnsg,si_TrxnFlag,si_TrxnID,si_HndlFlag);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                   
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"GSTList\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                String strDataRail="{\"Rail\":[";
	                String strDataCnsr="{\"Cnsr\":[";
	                String strDataCnsg ="{\"Cnsg\":[";  
	                int intRailCnt=0;
	                int intCnsrCnt=0;
	                int intCnsgCnt=0;
	                int intCntrRail=0;
	                int intCntrCnsr=0;
	                int intCntrCnsg=0;
	                logger.info("GST  List Length:"+intDataLen);
	                for(int i=0;i<intDataLen;i++)
	                {
	                    logger.info("GST Data:"+strSrvcData[i][0]);
	                    if(strSrvcData[i][0].equals("RLY") && strSrvcData[i][9].equals("R")) 
	                        intRailCnt+=1;
	                    if(strSrvcData[i][0].equals("CNSR") && strSrvcData[i][9].equals("R"))
	                        intCnsrCnt+=1;
	                    if(strSrvcData[i][0].equals("CNSG")&& strSrvcData[i][9].equals("R"))
	                        intCnsgCnt+=1;
	                }
	                logger.info("GST RAIL  List Length:"+intRailCnt);
	                logger.info("GST  CNSR List Length:"+intCnsrCnt);
	                logger.info("GST  CNSG List Length:"+intCnsgCnt);
	                    
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        if(strSrvcData[i][0].equals("RLY")&& strSrvcData[i][9].equals("R"))
	                        {
	                            if(intCntrRail==(intRailCnt-1))
	                            strDataRail+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"}";
	                            else
	                            strDataRail+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"},";
	                            intCntrRail+=1;
	                        }
	                        if(strSrvcData[i][0].equals("CNSR")&& strSrvcData[i][9].equals("R")) 
	                        {
	                            if(intCntrCnsr==(intCnsrCnt-1))
	                            strDataCnsr+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"}";
	                            else
	                            strDataCnsr+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"},";
	                            intCntrCnsr+=1;
	                        }
	                        if(strSrvcData[i][0].equals("CNSG")&& strSrvcData[i][9].equals("R")) 
	                        {
	                            if(intCntrCnsg==(intCnsgCnt-1))
	                            strDataCnsg+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"}";
	                            else
	                            strDataCnsg+="{\"CustCode\":\""+strSrvcData[i][1]+"\",\"CustName\":\""+strSrvcData[i][2]+"\",\"CustAddr\":\""+strSrvcData[i][3]+"\",\"CustMob\":\""+strSrvcData[i][4]+"\",\"CustEmail\":\""+strSrvcData[i][5]+"\",\"CustGST\":\""+strSrvcData[i][6]+"\",\"StateNumb\":\""+strSrvcData[i][7]+"\",\"StateName\":\""+strSrvcData[i][8]+"\",\"TrxnFlag\":\""+strSrvcData[i][9]+"\"},";
	                            intCntrCnsg+=1;
	                        }
	                    }
	                logger.info("GST RAIL  List :"+strDataRail);
	                logger.info("GST  CNSR List :"+strDataCnsr);
	                logger.info("GST  CNSG List :"+strDataCnsg);
	                strDataRail+="]},";
	                strDataCnsr+="]},";
	                strDataCnsg+="]}";
	                logger.info("Final GST RAIL  List :"+strDataRail);
	                    strData+=strDataRail+strDataCnsr+strDataCnsg+"]}";
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("GST_CUST_LIST"))
	    {
	        logger.info("Case: GST Customer List from RQFBDFRGTPYMT");          
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[];
	                    String strData="";
	                    strSrvcData=obj.getGSTCustList(si_CustCode);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"GstIn\":\""+strSrvcData+"\"}";                                                        
	                    logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	}
    
}