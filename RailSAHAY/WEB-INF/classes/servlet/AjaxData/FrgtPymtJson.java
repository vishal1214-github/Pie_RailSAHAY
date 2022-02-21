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
import servlet.AppData.FrgtPGTrxn;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import model.valubean.PymtCartBean;
import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.HashMap;

import model.GG_NodlOfcrSrvc;

import model.valubean.SecCustChrg;
import model.valubean.SecCustChrgList;

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
	                    logger.info("Outstanding Charges Secondary List :"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        if(i==(intDataLen-1))
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\",\"CustCode\":\""+strSrvcData[i][23]+"\",\"CustName\":\""+strSrvcData[i][24]+"\",\"TrxnTime\":\""+strSrvcData[i][25]+"\",\"TrxnStts\":\""+strSrvcData[i][26]+"\",\"CustDmrgId\":\""+strSrvcData[i][27]+"\",\"TrxnId\":\""+strSrvcData[i][28]+"\",\"AcrlDate\":\""+strSrvcData[i][29]+"\",\"FreeTime\":\""+strSrvcData[i][30]+"\"}";
	                        else
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\",\"CustCode\":\""+strSrvcData[i][23]+"\",\"CustName\":\""+strSrvcData[i][24]+"\",\"TrxnTime\":\""+strSrvcData[i][25]+"\",\"TrxnStts\":\""+strSrvcData[i][26]+"\",\"CustDmrgId\":\""+strSrvcData[i][27]+"\",\"TrxnId\":\""+strSrvcData[i][28]+"\",\"AcrlDate\":\""+strSrvcData[i][29]+"\",\"FreeTime\":\""+strSrvcData[i][30]+"\"},";
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
	    if(si_Optn.equals("OTSG_SEC_CHRG_SMRY"))
	    {
	        logger.info("Case: Summary of Outstanding Secondary Charges from RQFBDFRGTPYMT");          
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("Secondary si_CustId:"+si_CustId+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData;
	                    String strData="";
	                    strSrvcData=obj.getSecOtsgChrgSmry();
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
	    if(si_Optn.equals("OTSG_CHRG_SEC_CUSTOMER"))
	    {
	        logger.info("Case: List of Outstanding Secondary Charges from RQFBDFRGTPYMT");          
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("Secondary Charges si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getSecOtsgChrg(si_Sttn);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
                            ArrayList<SecCustChrg> secCustChrgList=new ArrayList<SecCustChrg>();
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"OtsgChrg\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Outstanding Seconadry Charges Payments List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        if(i==(intDataLen-1))
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\",\"CustCode\":\""+strSrvcData[i][23]+"\",\"CustName\":\""+strSrvcData[i][24]+"\",\"TrxnTime\":\""+strSrvcData[i][25]+"\",\"TrxnStts\":\""+strSrvcData[i][26]+"\",\"CustDmrgId\":\""+strSrvcData[i][27]+"\",\"TrxnId\":\""+strSrvcData[i][28]+"\",\"AcrlDate\":\""+strSrvcData[i][29]+"\",\"HndgAgnt\":\""+strSrvcData[i][30]+"\",\"TrxnTimeFull\":\""+strSrvcData[i][31]+"\",\"HndgAgntName\":\""+strSrvcData[i][32]+"\"}";
	                        else
	                        strData+="{\"ChrgId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"ChrgType\":\""+strSrvcData[i][5]+"\",\"FNOTId\":\""+strSrvcData[i][6]+"\",\"InvcId\":\""+strSrvcData[i][7]+"\",\"Amnt\":\""+strSrvcData[i][8]+"\",\"FNOTNumb\":\""+strSrvcData[i][9]+"\",\"FNOTDate\":\""+strSrvcData[i][10]+"\",\"SttnFrom\":\""+strSrvcData[i][11]+"\",\"SttnTo\":\""+strSrvcData[i][12]+"\",\"Cmdt\":\""+strSrvcData[i][13]+"\",\"InvcNumb\":\""+strSrvcData[i][14]+"\",\"InvcDate\":\""+strSrvcData[i][15]+"\",\"InvcFrom\":\""+strSrvcData[i][16]+"\",\"TotlAmnt\":\""+strSrvcData[i][17]+"\",\"Pctg\":\""+strSrvcData[i][18]+"\",\"IWOWFlag\":\""+strSrvcData[i][19]+"\",\"ChrgTypeCode\":\""+strSrvcData[i][20]+"\",\"ZoneCode\":\""+strSrvcData[i][21]+"\",\"CnfmDate\":\""+strSrvcData[i][22]+"\",\"CustCode\":\""+strSrvcData[i][23]+"\",\"CustName\":\""+strSrvcData[i][24]+"\",\"TrxnTime\":\""+strSrvcData[i][25]+"\",\"TrxnStts\":\""+strSrvcData[i][26]+"\",\"CustDmrgId\":\""+strSrvcData[i][27]+"\",\"TrxnId\":\""+strSrvcData[i][28]+"\",\"AcrlDate\":\""+strSrvcData[i][29]+"\",\"HndgAgnt\":\""+strSrvcData[i][30]+"\",\"TrxnTimeFull\":\""+strSrvcData[i][31]+"\",\"HndgAgntName\":\""+strSrvcData[i][32]+"\"},";
                                
	                        //secCustChrgList.add(new SecCustChrg(strSrvcData[i][0],strSrvcData[i][1],strSrvcData[i][2]))
                                
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
	    if(si_Optn.equals("CUST_WGMT_SMRY"))
	    {
	        logger.info("Case: CUSTOMER WEIGMENT SUMMARY from RQCUSTWISEDTLS");          
	        System.out.println("Case: CUSTOMER WEIGMENT DETAIL from RQCUSTWISEDTLS");          
	        String si_TrxnId      = req.getParameter("TrxnId").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        String si_DateFrom      = req.getParameter("DateFrom").toUpperCase().trim();
	        String si_DateTo  = req.getParameter("DateTo").toUpperCase().trim();
	        String si_Sttn      = req.getParameter("CustLocn").toUpperCase().trim();
	        
	        System.out.println("si_CustId:"+si_CustId+", si_TrxnId:"+si_TrxnId+", si_CustCode:"+ si_CustCode);
	        logger.info("si_CustId:"+si_CustId+", si_TrxnId:"+si_TrxnId+", si_CustCode:"+ si_CustCode);     
	        logger.info("si_DateFrom:"+si_DateFrom+", si_DateTo:"+si_DateTo+", si_Sttn:"+ si_Sttn);     
                
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getCustWgmt(si_TrxnId,si_DateFrom,si_DateTo,si_Sttn);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"CustWgmt\":[";                                                        
	                    int intDataLen=strSrvcData.length;
                        
	                    logger.info("Customer Weighment List Length:"+intDataLen);
                            System.out.println("Customer Weighment List Length:"+intDataLen);
                        
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        //strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"ChrgId\":\""+strSrvcData[i][2]+"\",\"TrxnId\":\""+strSrvcData[i][3]+"\",\"TrxnTime\":\""+strSrvcData[i][4]+"\",\"InvcNumb\":\""+strSrvcData[i][5]+"\",\"InvcDate\":\""+strSrvcData[i][6]+"\",\"ChrgType\":\""+strSrvcData[i][7]+"\",\"SttnFrom\":\""+strSrvcData[i][8]+"\",\"SttnFromName\":\""+strSrvcData[i][9]+"\",\"SttnTo\":\""+strSrvcData[i][10]+"\",\"SttnToName\":\""+strSrvcData[i][11]+"\",\"PymtMode\":\""+strSrvcData[i][12]+"\",\"TrxnAmnt\":\""+strSrvcData[i][13]+"\",\"TrxnStts\":\""+strSrvcData[i][14]+"\",\"AcrlTrxnTime\":\""+strSrvcData[i][17]+"\",\"BankTrxnId\":\""+strSrvcData[i][15]+"\",\"BankTrxnDate\":\""+strSrvcData[i][16]+"\"}";
	                        strData+="{\"RakeId\":\""+strSrvcData[i][0]+"\",\"SttnFrom\":\""+strSrvcData[i][1]+"\",\"SttnTo\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"WgmtId\":\""+strSrvcData[i][4]+"\",\"TrxnTime\":\""+strSrvcData[i][5]+"\",\"WgmtCount\":\""+strSrvcData[i][6]+"\",\"VendorCode\":\""+strSrvcData[i][7]+"\",\"VendorDesc\":\""+strSrvcData[i][8]+"\",\"CustCode\":\""+strSrvcData[i][9]+"\",\"CustName\":\""+strSrvcData[i][10]+"\",\"FNRNumb\":\""+strSrvcData[i][11]+"\"}";
	                        if(i!=(intDataLen-1))
	                            strData+=",";	                        
	                    }
	                    strData+="]}";
	                    System.out.println(strData);
	                logger.info(strData);
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch cust");
	                logger.info("Error in Data Fetch cust");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }     
	    if(si_Optn.equals("CUST_WGMT_DTLS"))
	    {
	        logger.info("Case: CUSTOMER WEIGMENT DETAIL from RQCUSTWISEDTLS");          
	        System.out.println("Case: CUSTOMER WEIGMENT DETAIL from RQCUSTWISEDTLS");          
	        String si_RakeID      = req.getParameter("RakeID").toUpperCase().trim();
	        String si_WgmtID      = req.getParameter("WgmtID").toUpperCase().trim();
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_DateFrom      = req.getParameter("DateFrom").toUpperCase().trim();
	        String si_DateTo      = req.getParameter("DateTo").toUpperCase().trim();
	        //String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_DateFrom:"+si_DateFrom+"si_DateTo:"+si_DateTo);
	        logger.info("si_CustId:"+si_CustId+", si_RakeID:"+si_RakeID);
	        logger.info("si_WgmtID:"+si_WgmtID+", si_Sttn:"+ si_Sttn);
                
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, "", si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getCustWgmtDtls(si_RakeID,si_WgmtID,si_Sttn,si_DateFrom,si_DateTo);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"WgmtDtls\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Customer Weighment List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        //strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"ChrgId\":\""+strSrvcData[i][2]+"\",\"TrxnId\":\""+strSrvcData[i][3]+"\",\"TrxnTime\":\""+strSrvcData[i][4]+"\",\"InvcNumb\":\""+strSrvcData[i][5]+"\",\"InvcDate\":\""+strSrvcData[i][6]+"\",\"ChrgType\":\""+strSrvcData[i][7]+"\",\"SttnFrom\":\""+strSrvcData[i][8]+"\",\"SttnFromName\":\""+strSrvcData[i][9]+"\",\"SttnTo\":\""+strSrvcData[i][10]+"\",\"SttnToName\":\""+strSrvcData[i][11]+"\",\"PymtMode\":\""+strSrvcData[i][12]+"\",\"TrxnAmnt\":\""+strSrvcData[i][13]+"\",\"TrxnStts\":\""+strSrvcData[i][14]+"\",\"AcrlTrxnTime\":\""+strSrvcData[i][17]+"\",\"BankTrxnId\":\""+strSrvcData[i][15]+"\",\"BankTrxnDate\":\""+strSrvcData[i][16]+"\"}";
	                        strData+="{\"OwngRly\":\""+strSrvcData[i][0]+"\",\"WgonType\":\""+strSrvcData[i][1]+"\",\"WgonNumb\":\""+strSrvcData[i][2]+"\",\"WgonId\":\""+strSrvcData[i][3]+"\",\"LEFlag\":\""+strSrvcData[i][4]+"\",\"InvcId\":\""+strSrvcData[i][5]+"\",\"InvcSttnFrom\":\""+strSrvcData[i][6]+"\",\"InvcSttnTo\":\""+strSrvcData[i][7]+"\",\"Cmdt\":\""+strSrvcData[i][8]+"\",\"Cnsr\":\""+strSrvcData[i][9]+"\",\"Cnsg\":\""+strSrvcData[i][10]+"\",\"RKPMFlag\":\""+strSrvcData[i][11]+"\",\"BVDCFlag\":\""+strSrvcData[i][12]+"\",\"RakeStts\":\""+strSrvcData[i][13]+"\",\"LoadId\":\""+strSrvcData[i][14]+"\",\"PlctResn\":\""+strSrvcData[i][15]+"\",\"RstnFlag\":\""+strSrvcData[i][16]+"\",\"wgmtcmnctime\":\""+strSrvcData[i][17]+"\",\"wgmtcmpltime\":\""+strSrvcData[i][18]+"\",\"strcyclstrttime\":\""+strSrvcData[i][19]+"\",\"strcyclcmpltime\":\""+strSrvcData[i][20]+"\",\"strtare\":\""+strSrvcData[i][21]+"\",\"stractlwght\":\""+strSrvcData[i][26]+"\",\"strgroswght\":\""+strSrvcData[i][23]+"\",\"strtmsactlwght\":\""+strSrvcData[i][27]+"\",\"strtmsgroswght\":\""+strSrvcData[i][22]+"\",\"strtrxntime\":\""+strSrvcData[i][30]+"\",\"strtmstare\":\""+strSrvcData[i][28]+"\",\"strtmstrxntime\":\""+strSrvcData[i][41]+"\",\"strrrnumb\":\""+strSrvcData[i][33]+"\",\"strrrdate\":\""+strSrvcData[i][34]+"\",\"strchblwght\":\""+strSrvcData[i][35]+"\",\"strpmblwght\":\""+strSrvcData[i][36]+"\",\"stroverwghttotl\":\""+strSrvcData[i][37]+"\",\"stroverwghtnorm\":\""+strSrvcData[i][38]+"\",\"strpol1wght\":\""+strSrvcData[i][39]+"\",\"strpol2wght\":\""+strSrvcData[i][40]+"\",\"InvcNumb\":\""+strSrvcData[i][31]+"\",\"InvcDate\":\""+strSrvcData[i][32]+"\",\"AcrlTrxnTime\":\""+strSrvcData[i][17]+"\",\"BankTrxnId\":\""+strSrvcData[i][15]+"\",\"BankTrxnDate\":\""+strSrvcData[i][16]+"\"}";
	                        if(i!=(intDataLen-1))
	                            strData+=",";
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
	                        strData+="{\"Sttn\":\""+strSrvcData[i][0]+"\",\"SttnName\":\""+strSrvcData[i][1]+"\",\"ChrgId\":\""+strSrvcData[i][2]+"\",\"TrxnId\":\""+strSrvcData[i][3]+"\",\"TrxnTime\":\""+strSrvcData[i][4]+"\",\"InvcNumb\":\""+strSrvcData[i][5]+"\",\"InvcDate\":\""+strSrvcData[i][6]+"\",\"ChrgType\":\""+strSrvcData[i][7]+"\",\"SttnFrom\":\""+strSrvcData[i][8]+"\",\"SttnFromName\":\""+strSrvcData[i][9]+"\",\"SttnTo\":\""+strSrvcData[i][10]+"\",\"SttnToName\":\""+strSrvcData[i][11]+"\",\"PymtMode\":\""+strSrvcData[i][12]+"\",\"TrxnAmnt\":\""+strSrvcData[i][13]+"\",\"TrxnStts\":\""+strSrvcData[i][14]+"\",\"AcrlTrxnTime\":\""+strSrvcData[i][17]+"\",\"BankTrxnId\":\""+strSrvcData[i][15]+"\",\"BankTrxnDate\":\""+strSrvcData[i][16]+"\"}";
	                        
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
	    if(si_Optn.equals("PYMT_RFND"))
	    {
	        logger.info("Case: PAYMENT REFUND DETAILS from RQFBDFRGTPYMT");          
	        String si_TrxnId      = req.getParameter("TrxnId").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        
	        logger.info("si_CustId:"+si_CustId+", si_TrxnId:"+si_TrxnId+", si_CustCode:"+ si_CustCode);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                    
	            try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getRfndDtls(si_TrxnId);
	                    String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"RfndDtls\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Refund Details List Length:"+intDataLen);
	                    for(int i=0;i<intDataLen;i++)
	                    {
	                        strData+="{\"RfndID\":\""+strSrvcData[i][0]+"\",\"RfndTrxnTime\":\""+strSrvcData[i][1]+"\",\"BankRfndId\":\""+strSrvcData[i][2]+"\",\"ChrgType\":\""+strSrvcData[i][3]+"\",\"BankStts\":\""+strSrvcData[i][4]+"\",\"BankSttsDesc\":\""+strSrvcData[i][5]+"\",\"BankAmnt\":\""+strSrvcData[i][6]+"\",\"BankPymtMode\":\""+strSrvcData[i][7]+"\",\"AppTrxnId\":\""+strSrvcData[i][8]+"\",\"AppTrxnTime\":\""+strSrvcData[i][9]+"\",\"Sttn\":\""+strSrvcData[i][10]+"\",\"Cust\":\""+strSrvcData[i][11]+"\",\"PymtGateway\":\""+strSrvcData[i][12]+"\"}";
	                        
	                        if(i!=(intDataLen-1))
	                            strData+=",";
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
                String si_FNOTId  = req.getParameter("FNOTId").toUpperCase().trim();
	        String si_CustDmrgId  = req.getParameter("CustDmrgId").toUpperCase().trim();
	        String si_TrxnId  = req.getParameter("TrxnId").toUpperCase().trim();
                String si_ChrgType  = req.getParameter("ChrgType").toUpperCase().trim();
	        String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
	        String si_Date=req.getParameter("InvcDate").toUpperCase().trim();
	        String si_Amnt=req.getParameter("Amnt").toUpperCase().trim();
	        String si_RlyGstin  = req.getParameter("RlyGstin").toUpperCase().trim();
	        String si_CnsrGstin=req.getParameter("CnsrGstin").toUpperCase().trim();
	        String si_CnsgGstin=req.getParameter("CnsgGstin").toUpperCase().trim();
	        String si_HndgAgnt=req.getParameter("HndgAgnt").toUpperCase().trim();
	   
	        logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	        logger.info("si_InvcId:"+si_InvcId+", si_ChrgType:"+ si_ChrgType+", si_IWOWFlag:"+si_IWOWFlag);
	        logger.info("si_RlyGstin:"+si_RlyGstin+", si_CnsrGstin:"+si_CnsrGstin+", si_CnsgGstin:"+ si_CnsgGstin+", si_Date:"+si_Date);
	        logger.info("si_FNOTId:"+si_FNOTId+", si_CustDmrgId:"+si_CustDmrgId+", si_TrxnId:"+ si_TrxnId+", si_HndgAgnt:"+ si_HndgAgnt);
	                    
	        FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	        try
	            {
	                    String strSrvcData[][];
	                    String strData="";
	                    strSrvcData=obj.getChrgBreakup(si_Sttn,si_Date,si_ChrgType,si_IWOWFlag,si_InvcId,si_FNOTId,si_CustDmrgId,si_TrxnId, si_Amnt,si_RlyGstin,si_CnsrGstin,si_CnsgGstin,si_HndgAgnt);
 						String strCallStts=obj.getCallStts();
	                    String strCallMesg=obj.getCallMesg();
	                
	                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Breakup\":[";                                                        
	                    int intDataLen=strSrvcData.length;
	                    logger.info("Breakup List Length:"+intDataLen);
                    

                            session.setAttribute("WRFAjstFlag","N"); 
                            session.setAttribute("WRFAmnt","");                                   
                            session.setAttribute("WRFTaxAmnt","");
                    
	                   for(int i=0;i<intDataLen;i++)
	                    {
                                if(strSrvcData[i][0].equals("WRF")) {
                                   session.setAttribute("WRFAjstFlag","Y"); 
                                   session.setAttribute("WRFAmnt",strSrvcData[i][2]);
                                }
                                if(strSrvcData[i][0].equals("WRFTAX")) {
                                   session.setAttribute("WRFTaxAmnt",strSrvcData[i][2]);
                                   continue;
                                }
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

	    if(si_Optn.equals("CART_CHRG_GST_BREAKUP"))
	    {
	        logger.info("Case: CART_CHRG_GST_BREAKUP (GST break of a charge, lying in the cart)");          
	        String si_ChrgId    = req.getParameter("ChrgId").toUpperCase().trim();

	        int chrgIndex=-1;
	        ArrayList<PymtCartBean> strCart=null;
	        String strData="";
	        if(!req.isRequestedSessionIdValid()) {
	            strData="{\"Stts\":\"F\",\"ErorMesg\":\"Not a Valid Session! Please Login Again\"}";                   
	            logger.info(strData);
	            res.getWriter().write(strData);  
	            return;
	        }
	            
	        try
	        {
	            strCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr"); 
	            if(strCart!=null)
	            {
	                int intOrigCartSize=strCart.size();
	                logger.info("Original List Size: "+intOrigCartSize);
	                if(intOrigCartSize>0) 
                        {
                            for(int i=0;i<intOrigCartSize;i++)
                            {
                                PymtCartBean bn=(PymtCartBean)strCart.get(i);
                                if((bn.getChrgId()).equals(si_ChrgId))
                                {
                                    chrgIndex=i; 
                                    break;
                                }
                            }
                            logger.info("Charge detail exists at index:"+chrgIndex);
                         }  
                     }
                    else 
                    {
                        strData="{\"Stts\":\"F\",\"ErorMesg\":\"Payment does not exist in the Cart\"}";                   
                        logger.info(strData);
                        res.getWriter().write(strData);  
                        return;
                    }
                }
                catch(Exception e) 
                {
                    strData="{\"Stts\":\"F\",\"ErorMesg\":\"Payment does not exist in the Cart\"}";                   
                    logger.info(strData);
                    res.getWriter().write(strData);  
                    return;
                }
                logger.info("Fetching parameters..");
                String si_Sttn =((PymtCartBean)strCart.get(chrgIndex)).getSttn();
                String si_CustCode  = ((PymtCartBean)strCart.get(chrgIndex)).getCustCode();
                String si_InvcId  = ((PymtCartBean)strCart.get(chrgIndex)).getInvcId();
                String si_FNOTId  = ((PymtCartBean)strCart.get(chrgIndex)).getFNOTId();
                String si_CustDmrgId  = ((PymtCartBean)strCart.get(chrgIndex)).getCustDmrgId();
                String si_TrxnId  = ((PymtCartBean)strCart.get(chrgIndex)).getTrxnId();
                String si_ChrgType  =((PymtCartBean)strCart.get(chrgIndex)).getChrgType();
                String si_IWOWFlag  = ((PymtCartBean)strCart.get(chrgIndex)).getIWOWFlag();
                String si_Date=((PymtCartBean)strCart.get(chrgIndex)).getDate();
                String si_Amnt=((PymtCartBean)strCart.get(chrgIndex)).getAmnt();
                String si_RlyGstin  = ((PymtCartBean)strCart.get(chrgIndex)).getRlyGstin();
                String si_CnsrGstin=((PymtCartBean)strCart.get(chrgIndex)).getCnsrGstin();
                String si_CnsgGstin=((PymtCartBean)strCart.get(chrgIndex)).getCnsgGstin();
                String si_HndgAgnt=((PymtCartBean)strCart.get(chrgIndex)).getHndgAgnt();
	    
                logger.info("si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
                logger.info("si_InvcId:"+si_InvcId+", si_ChrgType:"+ si_ChrgType+", si_IWOWFlag:"+si_IWOWFlag);
                logger.info("si_RlyGstin:"+si_RlyGstin+", si_CnsrGstin:"+si_CnsrGstin+", si_CnsgGstin:"+ si_CnsgGstin+", si_Date:"+si_Date);
                logger.info("si_FNOTId:"+si_FNOTId+", si_CustDmrgId:"+si_CustDmrgId+", si_TrxnId:"+ si_TrxnId+", si_HndgAgnt:"+ si_HndgAgnt);
                    
                FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
                try
                {
                    String strSrvcData[][];
                    strSrvcData=obj.getChrgBreakup(si_Sttn,si_Date,si_ChrgType,si_IWOWFlag,si_InvcId,si_FNOTId,si_CustDmrgId,si_TrxnId, si_Amnt,si_RlyGstin,si_CnsrGstin,si_CnsgGstin,si_HndgAgnt);
                                        String strCallStts=obj.getCallStts();
                    String strCallMesg=obj.getCallMesg();
                
                    strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Breakup\":[";                                                        
                    int intDataLen=strSrvcData.length;
                    logger.info("Breakup List Length:"+intDataLen);
            

                    session.setAttribute("WRFAjstFlag","N"); 
                    session.setAttribute("WRFAmnt","");                                   
                    session.setAttribute("WRFTaxAmnt","");
                        
                   for(int i=0;i<intDataLen;i++)
                    {
                        if(strSrvcData[i][0].equals("WRF")) {
                           session.setAttribute("WRFAjstFlag","Y"); 
                           session.setAttribute("WRFAmnt",strSrvcData[i][2]);
                        }
                        if(strSrvcData[i][0].equals("WRFTAX")) {
                           session.setAttribute("WRFTaxAmnt",strSrvcData[i][2]);
                           continue;
                        }
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
	                int intDataLen=strSrvcData.length;
	                logger.info("Breakup List Length:"+intDataLen);
	                strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"GstIn\":["; 
	                
	                for(int i=0;i<intDataLen;i++)
	                {
                            if(i==(intDataLen-1))
                                strData+="\""+strSrvcData[i]+"\"";  
                            else
                                strData+="\""+strSrvcData[i]+"\""+",";                                                        
	                    logger.info(strData);
                        }
	                strData+="]}";
	                    res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                    System.out.println("Error in Data Fetch");
	                    res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_Optn.equals("DMRG_BRKUP"))
            {
                    logger.info("Case: Breakup of Demurrage Charges from RQFBDFRGTPYMT");          
                    String si_DmrgType      = req.getParameter("DmrgType").toUpperCase().trim();      
                    String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
                    String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
                    String si_FnotId  = req.getParameter("FnotId").toUpperCase().trim();
                    String si_CustDmrgId  = req.getParameter("CustDmrgId").toUpperCase().trim();
                    String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
                    String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
                                    
                    logger.info("si_DmrgType:"+si_DmrgType+", si_Sttn:"+si_Sttn+", si_InvcId:"+si_InvcId+", si_CustDmrgId:"+ si_CustDmrgId+", si_FnotId:"+ si_FnotId+", si_IWOWFlag:"+ si_IWOWFlag);
                                
                    FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
                                
                        try
                        {
                                String strSrvcData[];
                                String strData="";
                                strSrvcData=obj.getDmrgBrkUp(si_DmrgType, si_Sttn, si_InvcId, si_FnotId, si_CustDmrgId, si_IWOWFlag);
                                String strCallStts=obj.getCallStts();
                                String strCallMesg=obj.getCallMesg();
                            
                                strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Brkup\":[";                                                        
                                int intDataLen=strSrvcData.length;
                                logger.info("Breakup List Length:"+intDataLen);
                                
                                
                                if(si_DmrgType.equals("DMRG_INVC"))
                                {
                                    strData+="{\"Acrd\":\""+strSrvcData[0]+"\",\"Wavd\":\""+strSrvcData[1]+"\",\"Otsg\":\""+strSrvcData[2]+"\"}";
                                }                       
                                if(si_DmrgType.equals("DMRG_CUST"))
                                {
                                    strData+="{\"Acrd\":\""+strSrvcData[0]+"\",\"Wavd\":\""+strSrvcData[1]+"\",\"Otsg\":\""+strSrvcData[2]+"\"}";
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

	    if(si_Optn.equals("WRFG_BRKUP"))
	    {
	            logger.info("Case: Breakup of Wharfage Charges from RQFBDFRGTPYMT");      
	            String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	            String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
	            String si_FnotId  = req.getParameter("FnotId").toUpperCase().trim();
	            String si_CustDmrgId  = req.getParameter("CustDmrgId").toUpperCase().trim();
	            String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
                    String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
	                            
	            logger.info("si_Sttn:"+si_Sttn+", si_InvcId:"+si_InvcId+", si_CustDmrgId:"+ si_CustDmrgId+", si_IWOWFlag:"+ si_IWOWFlag);
	                        
	            FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                        
	                try
	                {
	                        String strSrvcData[];
	                        String strData="";
	                        strSrvcData=obj.getWrfgBrkUp(si_Sttn, si_InvcId, si_FnotId, si_CustDmrgId,si_IWOWFlag);
	                        String strCallStts=obj.getCallStts();
	                        String strCallMesg=obj.getCallMesg();
	                    
	                        strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"Brkup\":[";                                                        
	                        int intDataLen=strSrvcData.length;
	                        logger.info("Breakup List Length:"+intDataLen);                        
	                        
                                strData+="{\"Acrd\":\""+strSrvcData[0]+"\",\"Wavd\":\""+strSrvcData[1]+"\",\"Otsg\":\""+strSrvcData[2]+"\"}";
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

	    if(si_Optn.equals("LOCL_CHRG_BRKUP"))
	    {
	            logger.info("Case: Breakup of Local Charges from RQFBDFRGTPYMT");          
	            String si_LCType      = req.getParameter("LCType").toUpperCase().trim();      
	            String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	            String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
	            String si_FnotId  = req.getParameter("FnotId").toUpperCase().trim();
	            String si_CustDmrgId  = req.getParameter("CustDmrgId").toUpperCase().trim();
	            String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	            String si_TrxnId  = req.getParameter("TrxnId").toUpperCase().trim();
	                            
	            logger.info("si_LCType:"+si_LCType+", si_Sttn:"+si_Sttn+", si_InvcId:"+si_InvcId+", si_CustDmrgId:"+ si_CustDmrgId+", si_TrxnId:"+si_TrxnId);
	                        
	            FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                        
	                try
	                {
	                        String strSrvcData[][];
	                        String strData="";
	                        strSrvcData=obj.getLoclChrgBrkUp(si_LCType, si_Sttn, si_InvcId, si_FnotId, si_CustDmrgId,si_TrxnId);
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

	    if(si_Optn.equals("CUST_GSTIN"))
	    {
	            logger.info("Case: List of Customer GSTIN from RQFBDFRGTPYMT");        
	            String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	            String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	                            
	            logger.info("si_Sttn:"+si_Sttn+", si_CustCode:"+si_CustCode);
	                        
	            FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                        
	                try
	                {
	                        String strSrvcData[];
	                        String strData="";
	                        strSrvcData=obj.getAnclChrgGSTIN(si_Sttn);
	                        String strCallStts=obj.getCallStts();
	                        String strCallMesg=obj.getCallMesg();
	                    
	                        strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"GSTINList\":[";                                                        
	                        int intDataLen=strSrvcData.length;
	                        logger.info("GSTIN List Length:"+intDataLen);
	                     
                               
	                        for(int i=0;i<intDataLen;i++)
	                         {
	                             if(i==(intDataLen-1))
	                             strData+="{\"GSTIN\":\""+strSrvcData[i]+"\"}";
	                             else
	                             strData+="{\"GSTIN\":\""+strSrvcData[i]+"\"},";
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

	    if(si_Optn.equals("HNDG_AGNT_GSTIN"))
	    {
	            logger.info("Case: List of Handling Agent GSTIN from RQFBDFRGTPYMT");        
	            String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	            String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	                            
	            logger.info("si_Sttn:"+si_Sttn+", si_CustCode:"+si_CustCode);
	                        
	            FrgtPymtSrvc obj=new FrgtPymtSrvc(si_CustId, si_CustCode, si_ClntId);
	                        
	                try
	                {
	                        String strSrvcData[];
	                        String strData="";
	                        strSrvcData=obj.getHndgAgntGSTIN(si_Sttn,si_CustCode);
	                        String strCallStts=obj.getCallStts();
	                        String strCallMesg=obj.getCallMesg();
	                    
	                        strData="{\"Stts\":\""+strCallStts+"\",\"ErorMesg\":\""+strCallMesg+"\",\"GSTINList\":[";                                                        
	                        int intDataLen=strSrvcData.length;
	                        logger.info("GSTIN List Length:"+intDataLen);
	                     
                               
	                        for(int i=0;i<intDataLen;i++)
	                         {
	                             if(i==(intDataLen-1))
	                             strData+="{\"GSTIN\":\""+strSrvcData[i]+"\"}";
	                             else
	                             strData+="{\"GSTIN\":\""+strSrvcData[i]+"\"},";
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
	    if(si_Optn.equals("ADD_TO_CART"))
	    {
	        logger.info("Case: ADD TO CART (New Payment to be added to cart)");          
	        String si_ChrgId      = req.getParameter("ChrgId").toUpperCase().trim();
	        String si_Sttn      = req.getParameter("Sttn").toUpperCase().trim();
	        String si_SttnName      = req.getParameter("SttnName").toUpperCase().trim();
	        String si_Dvsn      = req.getParameter("Dvsn").toUpperCase().trim();
	        String si_Zone      = req.getParameter("Zone").toUpperCase().trim();
	        String si_CustCode  = req.getParameter("CustCode").toUpperCase().trim();
	        String si_InvcId  = req.getParameter("InvcId").toUpperCase().trim();
	        String si_FNOTId  = req.getParameter("FNOTId").toUpperCase().trim();
	        String si_FNOTNumb  = req.getParameter("FNOTNumb").toUpperCase().trim();
	        String si_FNOTDate  = req.getParameter("FNOTDate").toUpperCase().trim();
	        String si_CustDmrgId  = req.getParameter("CustDmrgId").toUpperCase().trim();
	        String si_TrxnId  = req.getParameter("TrxnId").toUpperCase().trim();
	        String si_ChrgType  = req.getParameter("ChrgType").toUpperCase().trim();
	        String si_ChrgTypeDesc  = req.getParameter("ChrgTypeDesc").toUpperCase().trim();
	        String si_IWOWFlag  = req.getParameter("IWOWFlag").toUpperCase().trim();
	        String si_Date=req.getParameter("InvcDate").toUpperCase().trim();
	        String si_CnfmDate=req.getParameter("CnfmDate").toUpperCase().trim();
	        String si_AcrlDate=req.getParameter("AcrlDate").toUpperCase().trim();
	        String si_Amnt=req.getParameter("Amnt").toUpperCase().trim();
	        String si_Waiver=req.getParameter("Waiver").toUpperCase().trim();
	        String si_GstAmnt=req.getParameter("GstAmnt").toUpperCase().trim();
	        String si_NetAmnt=req.getParameter("NetAmnt").toUpperCase().trim();
	        String si_RlyGstin  = req.getParameter("RlyGstin").toUpperCase().trim();
	        String si_CnsrGstin=req.getParameter("CnsrGstin").toUpperCase().trim();
	        String si_CnsgGstin=req.getParameter("CnsgGstin").toUpperCase().trim();
	        String si_WRFAjstFlag=req.getParameter("WRFAjstFlag").toUpperCase().trim();
	        String si_WRFAjstAmnt=req.getParameter("WRFAjstAmnt").toUpperCase().trim();
	        String si_WRFTaxAjstAmnt=req.getParameter("WRFTaxAjstAmnt").toUpperCase().trim();
                
	        String si_HndgAgnt=req.getParameter("HndgAgnt").toUpperCase().trim();
	        String si_CustCtgr=req.getParameter("CustCtgr").toUpperCase().trim();
	    
	        logger.info("si_ChrgId:"+si_ChrgId+",si_CustId:"+si_CustId+", si_Sttn:"+si_Sttn+", si_CustCode:"+ si_CustCode);
	        logger.info("si_InvcId:"+si_InvcId+", si_ChrgType:"+ si_ChrgType+", si_ChrgTypeDesc: "+si_ChrgTypeDesc+", si_IWOWFlag:"+si_IWOWFlag);
	        logger.info("si_RlyGstin:"+si_RlyGstin+", si_CnsrGstin:"+si_CnsrGstin+", si_CnsgGstin:"+ si_CnsgGstin+", si_Date:"+si_Date);
	        logger.info("si_FNOTId:"+si_FNOTId+",si_FNOTNumb:"+si_FNOTNumb+",si_FNOTDate:"+si_FNOTDate+", si_CustDmrgId:"+si_CustDmrgId+", si_TrxnId:"+ si_TrxnId+", si_HndgAgnt:"+ si_HndgAgnt);
                logger.info("si_Amnt:"+si_Amnt+",si_Waiver:"+si_Waiver+", si_GstAmnt:"+si_GstAmnt+", si_NetAmnt:"+ si_NetAmnt);
                logger.info("si_Sttn:"+si_Sttn+",si_SttnName:"+si_SttnName+",si_Dvsn:"+si_Dvsn+", si_Zone:"+si_Zone);
                logger.info("si_CnfmDate:"+si_CnfmDate+",si_AcrlDate:"+si_AcrlDate); 
                logger.info("si_WRFAjstFlag:"+si_WRFAjstFlag+",si_WRFAjstAmnt:"+si_WRFAjstAmnt+",si_WRFTaxAjstAmnt:"+si_WRFTaxAjstAmnt); 
                logger.info("si_CustCtgr:"+si_CustCtgr);
                
                logger.info("Validating Payment Status from EJBClient");
                
                FrgtPGTrxn pgVldt=new FrgtPGTrxn();
                String strVldtStts=pgVldt.validateTrxnStatus(req,si_Zone, si_ChrgType, si_ChrgId, si_Amnt); 
                       
                logger.info("Validation Status:"+strVldtStts);

                String strData="";
                String strChrgTypeCode="";
                if(si_ChrgType.equals("F"))
                    strChrgTypeCode="02";
                if(si_ChrgType.equals("D"))
                    strChrgTypeCode="03";
                if(si_ChrgType.equals("W"))
                    strChrgTypeCode="04";
                if(si_ChrgType.equals("L"))
                    strChrgTypeCode="05";
                
                if(!strVldtStts.equals("FRESH")) {
                    if(strVldtStts.equals("SUCCESS"))
                    {
                        strData="{\"Stts\":\"F\",\"ErorMesg\":\"Payment has already been made! Please refresh the main screen\"}";                   
                        logger.info(strData);
                        res.getWriter().write(strData);  
                        return;
                    }
                    if(strVldtStts.equals("PENDING"))
                    {
                        strData="{\"Stts\":\"F\",\"ErorMesg\":\"Payment is PENDING in the system! Please retry after 10 minutes\"}";                   
                        logger.info(strData);
                        res.getWriter().write(strData);  
                        return;
                    }
                    if(strVldtStts.equals("ERROR"))
                    {
                        strData="{\"Stts\":\"F\",\"ErorMesg\":\"Error in fetching Transaction Status, Please try after some time\"}";                   
                        logger.info(strData);
                        res.getWriter().write(strData);  
                        return;
                    }
                }
                
	        ArrayList<PymtCartBean> strCart=null;
                if(!req.isRequestedSessionIdValid()) {
                    strData="{\"Stts\":\"F\",\"ErorMesg\":\"Not a Valid Session! Please Login Again\"}";                   
                    logger.info(strData);
                    res.getWriter().write(strData);  
                    return;
                }
                    
                try
                {
                    strCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr"); 
                    if(strCart!=null)
                    {
                        int intOrigCartSize=strCart.size();
                        logger.info("Original List Size: "+intOrigCartSize);
                        if(intOrigCartSize>0) {
                            PymtCartBean bn=(PymtCartBean)strCart.get(0);
                            if((!si_Zone.equals(bn.getZone())) || (!si_CustCode.equals(bn.getCustCode()))){
                                String strChrgDvsn=bn.getDvsn();
                                String strChrgZone=strChrgDvsn.substring(strChrgDvsn.indexOf("/")+1);
                                String strMesg="Payment of Zone: "+ strChrgZone+", Customer: "+bn.getCustCode()+" already exists in your Cart! Payments of Other Zone and/or Customer can not be added";
                                strData="{\"Stts\":\"F\",\"ErorMesg\":\""+strMesg+"\"}";                   
                                logger.info(strData);
                                res.getWriter().write(strData);  
                                return;     
                            }                            
                            if(!si_CustCtgr.equals(bn.getCustCtgr())){
                                String strMesg="Primary and Secondary Payments can not be clubbed in a Cart";
                                strData="{\"Stts\":\"F\",\"ErorMesg\":\""+strMesg+"\"}";                   
                                logger.info(strData);
                                res.getWriter().write(strData);  
                                return;     
                            }
                            
                            for(int i=0;i<intOrigCartSize;i++) {
                                PymtCartBean bn1=(PymtCartBean)strCart.get(i);
                                if(si_ChrgId.equals(bn1.getChrgId())) {
                                    String strMesg="Payment already added in the cart";
                                    strData="{\"Stts\":\"F\",\"ErorMesg\":\""+strMesg+"\"}";                   
                                    logger.info(strData);
                                    res.getWriter().write(strData);  
                                    return;     
                                }
                            }
                        }
                        strCart.add(new PymtCartBean(si_ChrgId,si_Sttn,si_SttnName,si_Dvsn, si_Zone,si_CustCode,si_InvcId,si_FNOTId,si_FNOTNumb, si_FNOTDate, si_CustDmrgId,si_TrxnId,si_ChrgType,strChrgTypeCode, si_ChrgTypeDesc,si_IWOWFlag,si_Date,si_CnfmDate, si_AcrlDate,si_Amnt,si_Waiver, si_GstAmnt, si_NetAmnt, si_RlyGstin,si_CnsrGstin,si_CnsgGstin,si_WRFAjstFlag,si_WRFAjstAmnt,si_WRFTaxAjstAmnt,si_HndgAgnt,si_CustCtgr));
                       
                        session.removeAttribute("CartPymtArr");
                        session.setAttribute("CartPymtArr",strCart);
                    }
                    else 
                    {
                        strCart=new ArrayList<PymtCartBean>();
                        strCart.add(new PymtCartBean(si_ChrgId,si_Sttn,si_SttnName,si_Dvsn, si_Zone,si_CustCode,si_InvcId,si_FNOTId,si_FNOTNumb, si_FNOTDate, si_CustDmrgId,si_TrxnId,si_ChrgType,strChrgTypeCode,  si_ChrgTypeDesc,si_IWOWFlag,si_Date,si_CnfmDate, si_AcrlDate,si_Amnt,si_Waiver, si_GstAmnt, si_NetAmnt,si_RlyGstin,si_CnsrGstin,si_CnsgGstin,si_WRFAjstFlag,si_WRFAjstAmnt,si_WRFTaxAjstAmnt,si_HndgAgnt,si_CustCtgr));
                        
                        session.setAttribute("CartPymtArr",strCart);  
                    }
                }
                catch(Exception e) 
                {
                    strCart=new ArrayList<PymtCartBean>();
                    strCart.add(new PymtCartBean(si_ChrgId,si_Sttn,si_SttnName, si_Dvsn, si_Zone, si_CustCode,si_InvcId,si_FNOTId,si_FNOTNumb, si_FNOTDate, si_CustDmrgId,si_TrxnId,si_ChrgType,strChrgTypeCode,  si_ChrgTypeDesc,si_IWOWFlag,si_Date,si_CnfmDate, si_AcrlDate,si_Amnt,si_Waiver, si_GstAmnt, si_NetAmnt,si_RlyGstin,si_CnsrGstin,si_CnsgGstin,si_WRFAjstFlag,si_WRFAjstAmnt,si_WRFTaxAjstAmnt,si_HndgAgnt,si_CustCtgr));
                    
                    session.setAttribute("CartPymtArr",strCart);  
                }                       
                strData="{\"Stts\":\"S\",\"ErorMesg\":\"\"}";	                    
                logger.info(strData);
	        res.getWriter().write(strData);
                return;
            }
	    if(si_Optn.equals("CART_CHRG_LIST"))
	    {
	        logger.info("Case: CART_CHRG_LIST (Get Cart Chrg List)");          
                String strData="";  
                try
                {
                    ArrayList<PymtCartBean> strCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr"); 
                    if(strCart!=null)
                    {
                        int intOrigCartSize=strCart.size();
                        logger.info("Current Size of Cart:"+intOrigCartSize);
                        strData="{\"ChrgList\":[";
                        for(int i=0;i<intOrigCartSize;i++) 
                        {
                            logger.info("Current Charge Id:"+((PymtCartBean)strCart.get(i)).getChrgId());
                            
                            if(i==0)
                                strData+="{\"ChrgId\":\""+((PymtCartBean)strCart.get(i)).getChrgId()+"\"}";                         
                            else
                                strData+=",{\"ChrgId\":\""+((PymtCartBean)strCart.get(i)).getChrgId()+"\"}"; 
                        }
                        strData+="]}";       
                    }
                    else 
                    {                   
                        strData="{\"ChrgList\":[]}";
                    }
                }
                catch(Exception e) 
                {
                    strData="{\"ChrgList\":[]}";
                }  
                logger.info(strData);
                res.getWriter().write(strData);
                return;
            }
	    if(si_Optn.equals("DEL_FROM_CART"))
	    {
	        logger.info("Case: DEL_FROM_CART (Delete from Cart)");          
	        String si_ChrgId      = req.getParameter("ChrgId").toUpperCase().trim();	    
	        logger.info("si_ChrgId:"+si_ChrgId);
                
	        ArrayList<PymtCartBean> strCart=null;
	        String strData="";
	        if(!req.isRequestedSessionIdValid()) {
	            strData="{\"Stts\":\"F\",\"ErorMesg\":\"Not a Valid Session! Please Login Again\"}";                   
	            logger.info(strData);
	            res.getWriter().write(strData);  
	            return;
	        }	            
	        try
	        {
	            strCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr"); 
	            if(strCart!=null)
	            {
	                int intOrigCartSize=strCart.size();
                        logger.info("Current Size of Cart:"+intOrigCartSize);
                        
                        for(int i=0;i<intOrigCartSize;i++) 
                        {
                            PymtCartBean bn=(PymtCartBean)strCart.get(i);  
                            if(bn.getChrgId().equals(si_ChrgId)) {
                                strCart.remove(i);
                                break;
                            }                            
                        }
	                session.removeAttribute("CartPymtArr");
	                session.setAttribute("CartPymtArr",strCart);                      
                        strData="{\"Stts\":\"S\",\"ErorMesg\":\"\"}";       
	            }
	            else 
	            {	                
	                strData="{\"Stts\":\"F\",\"ErorMesg\":\"Cart is Empty or does not exist\"}";      
	            }
                }
                catch(Exception e) 
                {
                    strData="{\"Stts\":\"F\",\"ErorMesg\":\"Cart is Empty or does not exist\"}";  
                }                      
	        logger.info(strData);
	        res.getWriter().write(strData);
	        return;
	    }
	    if(si_Optn.equals("DEL_PYMT_FROM_CART"))
	    {
	        logger.info("Case: DEL_PYMT_FROM_CART (Delete a payment from Cart)");          
	        String si_ChrgId      = req.getParameter("ChrgId").toUpperCase().trim();            
	        logger.info("si_ChrgId:"+si_ChrgId);
	        
	        ArrayList<PymtCartBean> strCart=null;
	        String strData="";
	        if(!req.isRequestedSessionIdValid()) {
	            strData="{\"Stts\":\"F\",\"ErorMesg\":\"Not a Valid Session! Please Login Again\"}";                   
	            logger.info(strData);
	            res.getWriter().write(strData);  
	            return;
	        }                   
	        try
	        {
	            strCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr"); 
	            if(strCart!=null)
	            {
	                int intOrigCartSize=strCart.size();
	                logger.info("Current Size of Cart:"+intOrigCartSize);
	                
	                for(int i=0;i<intOrigCartSize;i++) 
	                {
	                    PymtCartBean bn=(PymtCartBean)strCart.get(i);  
	                    if(bn.getChrgId().equals(si_ChrgId)) {
	                        strCart.remove(i);
	                        break;
	                    }                            
	                }
	                session.removeAttribute("CartPymtArr");
	                session.setAttribute("CartPymtArr",strCart);                      
	                strData="{\"Stts\":\"S\",\"ErorMesg\":\"\",\"Data\":[";       
                        for(int i=0;i<strCart.size();i++) 
                        {
                            PymtCartBean bn=strCart.get(i);
                            if(i==(strCart.size()-1))
                                strData+="{\"ChrgId\":\""+bn.getChrgId()+"\",\"Sttn\":\""+bn.getSttn()+"\",\"SttnName\":\""+bn.getSttnName()+"\",\"Dvsn\":\""+bn.getDvsn()+"\",\"Zone\":\""+bn.getZone()+"\",\"CustCode\":\""+bn.getCustCode()+"\",\"InvcId\":\""+bn.getInvcId()+"\",\"FNOTId\":\""+bn.getFNOTId()+"\",\"FNOTNumb\":\""+bn.getFNOTNumb()+"\",\"FNOTDate\":\""+bn.getFNOTDate()+"\",\"CustDmrgId\":\""+bn.getCustDmrgId()+"\",\"TrxnId\":\""+bn.getTrxnId()+"\",\"ChrgType\":\""+bn.getChrgType()+"\",\"ChrgTypeDesc\":\""+bn.getChrgTypeDesc()+"\",\"IWOWFlag\":\""+bn.getIWOWFlag()+"\",\"Date\":\""+bn.getDate()+"\",\"CnfmDate\":\""+bn.getCnfmDate()+"\",\"AcrlDate\":\""+bn.getAcrlDate()+"\",\"Amnt\":\""+bn.getAmnt()+"\",\"Waiver\":\""+bn.getWaiver()+"\",\"GstAmnt\":\""+bn.getGstAmnt()+"\",\"NetAmnt\":\""+bn.getNetAmnt()+"\",\"RlyGstin\":\""+bn.getRlyGstin()+"\",\"CnsrGstin\":\""+bn.getCnsrGstin()+"\",\"CnsgGstin\":\""+bn.getCnsgGstin()+"\",\"HndgAgnt\":\""+bn.getHndgAgnt()+"\"}";
                            else
                                strData+="{\"ChrgId\":\""+bn.getChrgId()+"\",\"Sttn\":\""+bn.getSttn()+"\",\"SttnName\":\""+bn.getSttnName()+"\",\"Dvsn\":\""+bn.getDvsn()+"\",\"Zone\":\""+bn.getZone()+"\",\"CustCode\":\""+bn.getCustCode()+"\",\"InvcId\":\""+bn.getInvcId()+"\",\"FNOTId\":\""+bn.getFNOTId()+"\",\"FNOTNumb\":\""+bn.getFNOTNumb()+"\",\"FNOTDate\":\""+bn.getFNOTDate()+"\",\"CustDmrgId\":\""+bn.getCustDmrgId()+"\",\"TrxnId\":\""+bn.getTrxnId()+"\",\"ChrgType\":\""+bn.getChrgType()+"\",\"ChrgTypeDesc\":\""+bn.getChrgTypeDesc()+"\",\"IWOWFlag\":\""+bn.getIWOWFlag()+"\",\"Date\":\""+bn.getDate()+"\",\"CnfmDate\":\""+bn.getCnfmDate()+"\",\"AcrlDate\":\""+bn.getAcrlDate()+"\",\"Amnt\":\""+bn.getAmnt()+"\",\"Waiver\":\""+bn.getWaiver()+"\",\"GstAmnt\":\""+bn.getGstAmnt()+"\",\"NetAmnt\":\""+bn.getNetAmnt()+"\",\"RlyGstin\":\""+bn.getRlyGstin()+"\",\"CnsrGstin\":\""+bn.getCnsrGstin()+"\",\"CnsgGstin\":\""+bn.getCnsgGstin()+"\",\"HndgAgnt\":\""+bn.getHndgAgnt()+"\"},";
                        }
	                strData+="]}";
	            }
	            else 
	            {                   
	                strData="{\"Stts\":\"F\",\"ErorMesg\":\"Cart is Empty or does not exist\"}";      
	            }
	        }
	        catch(Exception e) 
	        {
	            strData="{\"Stts\":\"F\",\"ErorMesg\":\"Cart is Empty or does not exist\"}";  
	        }                      
	        logger.info(strData);
	        res.getWriter().write(strData);
	        return;
	    } 

	}
    
}