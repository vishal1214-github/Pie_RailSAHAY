package servlet.AppData;

import java.util.Hashtable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.net.URLDecoder;

import java.security.MessageDigest;

import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cris.fois.tms.epay.sbi.enquiry.EnquiryRequestParams;
import org.cris.fois.tms.epay.sbi.enquiry.EnquiryResponseParams;
import org.cris.fois.tms.epay.sbi.trxn.PostTransactionRequestParams;
import org.cris.fois.tms.epay.sbi.trxn.PostTransactionResponseParams;
import org.cris.fois.tms.epay.sbi.trxn.TransactionRequestParams;
import org.cris.fois.tms.epay.sbi.trxn.TransactionResponseParams;

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

import org.cris.fois.tms.epay.sbi.ejb.SBISessionEJB;

import servlet.AjaxData.FrgtPymtJson;

public class FrgtPymtTrxn extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(FrgtPymtTrxn.class.getName());
        UserProfDtls userdtls=null;
        String si_CustId="";
        String si_PymtAmnt="";
        String si_ChrgId="";
        String si_PymtZone="";
        String si_ChrgType="";
        String si_ResEncdata="";
        String si_CnfmDate="";
        String si_CustCode="";
        
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling FrgtPymtTrxn.java...");
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
	    
                if(si_Optn.equals("VLDT_TRXN_STATUS"))
                {
                    logger.info("Case: Validate Payment Status");              
                    si_PymtZone = req.getParameter("txtChrgZone").toUpperCase().trim();
                    si_ChrgType = req.getParameter("txtChrgType").toUpperCase().trim();
                    si_ChrgId  = req.getParameter("txtChrgId").toUpperCase().trim();
                    si_PymtAmnt  = req.getParameter("txtAmnt").toUpperCase().trim();
                    logger.info("si_CustId:"+si_CustId+", si_PymtZone:"+si_PymtZone+",si_ChrgType:"+si_ChrgType+", si_ChrgId:"+ si_ChrgId+", si_PymtAmnt:"+si_PymtAmnt);
                    logger.info("Calling Payment Gateway");
                    String strData="";
                    
                    try {
                        final Context contextVldt = getInitialContext();
                        SBISessionEJB sBISessionEJB =
                            (SBISessionEJB) contextVldt.lookup("SBISessionEJB#org.cris.fois.tms.epay.sbi.ejb.SBISessionEJB");
                        
                        logger.info("Checking the Status of this transaction");
                        
                        EnquiryResponseParams resEnq= sBISessionEJB.trxnStatusVldt(paymentEnquiryParams());
                        if(resEnq.isPending()) 
                        {
                            logger.info("Not to Continue with Payment, as it has already been attempted:"+resEnq.getPymtStatus());    
                            strData="{\"Stts\":\""+resEnq.getPymtStatus()+"\"}";                                                        
                            if(resEnq.getPymtStatus().equals("SUCCESS")) 
                            {
                                logger.info("Payment has already been processed for this case");    
                            }
                            if(resEnq.getPymtStatus().equals("PENDING")) 
                            {
                                logger.info("Payment is pending for accrual, please wait");    
                            }
	                    logger.info(strData);
	                    res.getWriter().write(strData);
                        }
                        else
                        {
                            strData="{\"Stts\":\"FRESH\"}";  

                            logger.info(strData);
                            res.getWriter().write(strData);
                        }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                
		if(si_Optn.equals("PAY_NOW"))
		{
                    logger.info("Case: Initiate Freight Payment");		
                    si_PymtZone	= req.getParameter("txtChrgZone").toUpperCase().trim();
                    si_ChrgType	= req.getParameter("txtChrgType").toUpperCase().trim();
                    si_ChrgId  = req.getParameter("txtChrgId").toUpperCase().trim();
                    si_PymtAmnt  = req.getParameter("txtAmnt").toUpperCase().trim();
                    si_CustCode  = req.getParameter("txtCustCode").toUpperCase().trim();
                    si_CnfmDate  = req.getParameter("txtCnfmDate").toUpperCase().trim();
                    logger.info("si_CustId:"+si_CustId+", si_PymtZone:"+si_PymtZone+",si_ChrgType:"+si_ChrgType+", si_ChrgId:"+ si_ChrgId+", si_PymtAmnt:"+si_PymtAmnt+", si_CustCode:"+si_CustCode+", si_CnfmDate:"+si_CnfmDate);
                    logger.info("Calling Payment Gateway");
                    
                    try {
                        final Context context = getInitialContext();
                        SBISessionEJB sBISessionEJB =
                            (SBISessionEJB) context.lookup("SBISessionEJB#org.cris.fois.tms.epay.sbi.ejb.SBISessionEJB");
                        
                        logger.info("Checking the Status of this transaction");
                        
                        EnquiryResponseParams resEnq= sBISessionEJB.trxnStatusVldt(paymentEnquiryParams());
                        if(resEnq.isPending()) 
                        {
                            logger.info("Not to Continue with Payment, as it has already been attempted:"+resEnq.getPymtStatus());    
                            
                            if(resEnq.getPymtStatus().equals("SUCCESS")) 
                            {
                                logger.info("Payment has already been done for this charge");    
                            }
                            if(resEnq.getPymtStatus().equals("PENDING")) 
                            {
                                logger.info("Payment is pending for accrual, please wait");    
                            }
                        }
                        else
                        {
                        //trxnPreProcessing
                        logger.info("Fresh Payment: Calling Transaction Pre-processing");
                        TransactionResponseParams trxnres= sBISessionEJB.trxnPreProcessing(getPaymentRequestParams());
                        logger.info("#errorFlag#"+trxnres.isErrorFlag()+"#ErrorMsg#"+trxnres.getErrorDesc()+"#encdata#"+trxnres.getEncdata()+"#merchantCode#"+trxnres.getMerchant_code());
                        logger.info("#URL#"+  trxnres.getRedirectURL()+"#trxnID#"+trxnres.getTrxnID());
                        
                        if(!trxnres.isErrorFlag()) 
                        {   
                            session.setAttribute("PymtTrxnID",trxnres.getTrxnID());
                            session.setAttribute("ChrgId",si_ChrgId);
                            session.setAttribute("ChrgType",si_ChrgType);
                            session.setAttribute("ChrgAmnt",si_PymtAmnt);
                            session.setAttribute("CustCode",si_PymtAmnt);
                            session.setAttribute("CnfmDate",si_CnfmDate);
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtRedirect.jsp?encdata="+trxnres.getEncdata()+"&mccode="+trxnres.getMerchant_code()+"&trxnid="+trxnres.getTrxnID()+"&url="+trxnres.getRedirectURL());
                        }
                    }
                    
                    //trxnSatusEnquiry
                        /*
                    EnquiryResponseParams res= sBISessionEJB.trxnSatusEnquiry(paymentEnquiryParams());
                                  System.out.println("#errorFlag#"+res.isErrorFlag()+"#ErrorMsg#"+res.getErrorDesc());
                                System.out.println("#txnAmount#"+res.getAmount()+"#BankTrxnId#"+res.getBankRefID()+"#bankTxnTime#"+res.getBankTrxnDate()+"#foisTrxnId#"+res.getFoisTrxnID()+"#pymtRefId#"+res.getPymtRefID()+"#pymtStatus#"+res.getPymtStatus()+"#statusDesc#"+res.getStatusDesc());
                        */   
                    
                    } 
                    catch (CommunicationException ex) 
                    {
                        logger.info("In exception");
                        logger.error(ex.getClass().getName());
                        logger.error(ex.getRootCause().getLocalizedMessage());
                        logger.error("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
                    }
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                }
	    if(si_Optn.equals("PAYMENT_RESPONSE"))
	    {
	        logger.info("Case: Payment Response");    

	        logger.info("inside PaymentResponse dopost");
                logger.info("valid session#"+req.isRequestedSessionIdValid());
	        logger.info("isRequestedSessionIdFromCookie#"+req.isRequestedSessionIdFromCookie());
                if((!req.isRequestedSessionIdValid()) || (!req.isRequestedSessionIdFromCookie())) {
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtError.jsp");
                }
                try
                {

                    si_ResEncdata=req.getParameter("encdata");
                    logger.info("Encdata:"+si_ResEncdata);
                    System.out.println("PAYMENT_RESPONSE EncData:"+si_ResEncdata);
                    final Context context1= getInitialContext();  

                    SBISessionEJB sBISessionEJB =
                        (SBISessionEJB) context1.lookup("SBISessionEJB#org.cris.fois.tms.epay.sbi.ejb.SBISessionEJB");
                    PostTransactionResponseParams resParam= sBISessionEJB.trxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    String ChrgId=(String)session.getAttribute("ChrgId");
                    String FOISTrxnId=resParam.getFoisTrxnID();
                    String BankTrxnId=resParam.getBankRefID();
                    String BankTrxnTime=resParam.getBankTrxnDate();
                    String PymtStts=resParam.getPymtStatus();
                    String SttsDesc=resParam.getStatusDesc();
                    
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtSuccess.jsp?ChrgId="+ChrgId+"&FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts="+PymtStts+"&SttsDesc="+SttsDesc);
                    }                    
                    else
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtFailed.jsp?ChrgId="+ChrgId+"&FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts="+PymtStts+"&SttsDesc="+SttsDesc);
                    }
                    
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
	    if(si_Optn.equals("PAYMENT_CANCEL"))
	    {
	        logger.info("Case: Response Cancel");    

	        logger.info("inside Response Cancel dopost");
                logger.info("valid session#"+req.isRequestedSessionIdValid());
	        logger.info("isRequestedSessionIdFromCookie#"+req.isRequestedSessionIdFromCookie());
                try
                {
                    final Context context1= getInitialContext();  

                    SBISessionEJB sBISessionEJB =
                        (SBISessionEJB) context1.lookup("SBISessionEJB#org.cris.fois.tms.epay.sbi.ejb.SBISessionEJB");
                    PostTransactionResponseParams resParam= sBISessionEJB.trxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCancel.jsp");
                    }                    
                    else
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtError.jsp");
                    }
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
                
	}
    private Context getInitialContext() throws NamingException {
            Hashtable env = new Hashtable();
            // WebLogic Server 10.x/12.x connection details
            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            //env.put(Context.PROVIDER_URL, "t3://172.16.4.79:7101");
            env.put(Context.PROVIDER_URL, "t3://10.60.201.195:50004");
            return new InitialContext(env);
        }
        
        TransactionRequestParams getPaymentRequestParams() {
            TransactionRequestParams req=new TransactionRequestParams();
            logger.info("Sending parameters to EJB: si_PymtAmnt:"+si_PymtAmnt+", si_ChrgType:"+si_ChrgType+", si_CustId:"+si_CustId+", si_PymtZone:"+si_PymtZone);
            req.setAmount(si_PymtAmnt);
            req.setAppID("02"); /*02: FBD*/
            req.setCancelURL("cancel");
            req.setPymtRefID(si_ChrgId);
            req.setPymtType(si_ChrgType);
            req.setRemarks("");
            req.setResponseURL("https://foistest.indianrail.gov.in/RailSAHAY/FrgtPymtTrxn?Optn=PAYMENT_RESPONSE");
            req.setUserID(si_CustId);
            req.setZonecode(si_PymtZone);
            req.setCustCode(si_CustCode);
            req.setPymtRefDate(si_CnfmDate);
            req.setPGName("SBI_MOPS");  // for SBI 'SBI_MOPS' and for dummyPG 'DUMMY'
           // req.setPGName("DUMMY");
            return req;
        }
        
        PostTransactionRequestParams getSBIResponseParams(HttpSession sesn) {
            PostTransactionRequestParams req=new PostTransactionRequestParams();
            req.setAppID("02");
            //req.setEncdata("NqdWEh7PpMYp4KIm9jUi6Xd8r5NKXWa7bLbYT6z+SmkhIlilRj1QcdZGq4Rq97QLTtaosyVcAx7O1H6vS+CPC90AshWmQjFUD/yGfBVQraktjdIozWjB50p1MJBaZ2QrmE3hZ1Hg1/96oac24yl+Mk8PXr65bN+KMV/LSp9S4nmZMBlFuWtu6X99w5hydiWx+mPUnrE7lFZGuIb59YQ4ndLwQW2xSt0nQoRhL2E0wnN1WNyvsm0lreZ8ONuls+HWdRj0q9pBXaqviPbBcKkx4n83/SELus1iMp2Xiwn2078kX76F7XctrVPgj/lGaUerrHcBXBPU9EFKwpC6iZo/oGbaSPA6Rz7nX3GgYoyu/w8h2Rewp1E22txaAHpKobjgDCwTkghnpmmBWZGo5Gk4BzZ8z6oCzEchh0scXQ0c2hAfx1jwOA9ZMXzXqItEi2NKaKmvOXvTbSvIOcA6QF/R2LnZiz9zeesr4qsBUwhaV4h8j5bv8OJ0PpvnnfD24A1z");
            req.setEncdata(si_ResEncdata);
            req.setPymtRefID((String)sesn.getAttribute("ChrgId"));
            req.setPymtType((String)sesn.getAttribute("ChrgType"));
            req.setPGName("SBI_MOPS");  // for SBI 'SBI_MOPS' and for dummyPG 'DUMMY'
            //req.setPGName("DUMMY");
            return req;
        }
        
        EnquiryRequestParams paymentEnquiryParams() {
            EnquiryRequestParams req=new EnquiryRequestParams();
            req.setAmount(si_PymtAmnt);
            req.setPymtRefID(si_ChrgId);
            req.setPymtType(si_ChrgType);
            return req;
        }
}