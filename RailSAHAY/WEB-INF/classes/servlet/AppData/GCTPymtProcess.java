package servlet.AppData;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cris.fois.tms.epay.params.EnquiryRequestParams;
import org.cris.fois.tms.epay.params.EnquiryResponseParams;
import org.cris.fois.tms.epay.params.PostTransactionRequestParams;
import org.cris.fois.tms.epay.params.PostTransactionResponseParams;
import org.cris.fois.tms.epay.params.TransactionRequestParams;
import org.cris.fois.tms.epay.params.TransactionResponseParams;

import java.util.Hashtable;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserProfDtls;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.ArrayList;
import model.GCTSrvcCall;

import org.cris.fois.tms.epay.ejb.FPGSessionEJB;


public class GCTPymtProcess extends HttpServlet
{	
        String strCustId="";
        String strRfrnId="";
        String strFileRfrnId="";
        String strZone="";
        String strDvsn="";
        String strSttn="";
        String strName="";
        String strDesg="";
        String strMobl="";
        String strContNo="";
        String strEmail="";
        String strAddr="";
        String strGstIn="";
        String strApltCtgr="";
        String strPAN="";
        String strTAN="";
        String strCmdtCont="";
        String strCmdtList="";
        String strCmdtDtls="";
        String strAppAmnt="";
        String strGSTAmnt="";
        String strTotlAmnt="";
        String si_ResEncdata="";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
        Date date = new Date();  
        String strCrntDate=formatter.format(date);
	static Logger logger = FoisLogger.getLogger(GCTPymtProcess.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GCTPymtProcess.java...");
		HttpSession session=req.getSession();
                String si_MainENCData=req.getParameter("encdata");
                logger.info("Main ENC:"+si_MainENCData);
                System.out.println("Main ENC:"+si_MainENCData);
                String si_ClntId = req.getHeader("X-FORWARDED-FOR");
                if (si_ClntId == null) 
                {
                        si_ClntId = req.getRemoteAddr();
                }
                if (si_ClntId.indexOf(",")>0)
                {
                        si_ClntId = si_ClntId.substring(0,si_ClntId.indexOf(","));
                }
                logger.info("Client Id from Request:"+si_ClntId);
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		try
		{
                    strCustId = (String) session.getAttribute("custId");
                    if(strCustId==null)
                        strCustId="GCTO";
                }
                catch(Exception e) 
                {
                    /*
                     logger.info("Exception in getting session attributes, redirecting to Login Screen");
                    req.setAttribute("message","Session Timed Out! Please Login Again to Make the Payments");
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FBDLogin.jsp");   
                    */
                    strCustId="GCTO";
                }
                
                if(strCustId.equals("") ||(strCustId==null)) {
                    logger.info("Exception in getting session attributes, redirecting to Login Screen");
                    req.setAttribute("message","Session Timed Out! Please Login Again to Make the Payments");
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FBDLogin.jsp");   
                }
                String si_Optn  = req.getParameter("Optn").toUpperCase().trim();
	    
                if(si_Optn.equals("VLDT_TRXN_STATUS"))
                {
                    logger.info("Case: Validate Payment Status");              
                    logger.info("Calling Payment Gateway");
                    String strData="";
                    
                    try {
                        final Context contextVldt = getInitialContext();
                        FPGSessionEJB sBISessionEJB =
                            (FPGSessionEJB) contextVldt.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                        
                        logger.info("Checking the Status of this transaction");
                        
                        EnquiryResponseParams resEnq= sBISessionEJB.trxnStatusVldtMisc(paymentEnquiryParams());
                        logger.info("Enquiry Status ErorFlag:"+resEnq.isErrorFlag()+", "+resEnq.getErrorDesc());
                        if(!resEnq.isErrorFlag())
                        {
                        if(resEnq.isPending()) 
                        {
                            logger.info("Not to Continue with Payment, as it has already been attempted:"+resEnq.getPymtStatus());    
                            strData="{\"Stts\":\""+resEnq.getPymtStatus()+"\",\"AppTrxnId\":\""+resEnq.getFoisTrxnID()+"\"}";
                            if(resEnq.getPymtStatus().equals("SUCCESS")) 
                            {
                                logger.info("Payment has already been processed for this application");    
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
                            strData="{\"Stts\":\"FRESH\",\"AppTrxnId\":\""+resEnq.getFoisTrxnID()+"\"}"; 

                            logger.info(strData);
                            res.getWriter().write(strData);
                        }
                        }
                        else {
                            strData="{\"Stts\":\"ERROR\",\"AppTrxnId\":\""+resEnq.getFoisTrxnID()+"\"}";

                            logger.info(strData);
                            res.getWriter().write(strData);
                            
                        }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
          	    
	    if(si_Optn.equals("CANCEL_USER_TRXN"))
	    {
	        logger.info("Case: Cancel User Transaction");              
	        String si_AppTrxnId = req.getParameter("AppTrxnId").toUpperCase().trim();
                String si_Rmrk="TRANSACTION DISCARDED BY USER";
	        logger.info("si_AppTrxnId:"+si_AppTrxnId+", si_Rmrk:"+si_Rmrk+",si_ClntId:"+si_ClntId);
	        logger.info("Going to Cancel the Transaction");
	        String strData=""; 
                try {
                        final Context contextVldt = getInitialContext();
                        FPGSessionEJB sBISessionEJB =
                            (FPGSessionEJB) contextVldt.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                        
                        logger.info("Checking the Status of this transaction");
	        if(sBISessionEJB.cancePendingTransaction(si_AppTrxnId, si_Rmrk))
	        {
		      	strData="{\"Stts\":\"SUCCESS\"}";    
	        }
	        else
	        {
		        strData="{\"Stts\":\"FAILED\"}";    
	        }  
            }
                catch(Exception e) 
                {
                        e.printStackTrace();
                        strData="{\"Stts\":\"FAILED\"}";
                }      
	                
            logger.info(strData);
            res.getWriter().write(strData);
	    }      
		if(si_Optn.equals("PAY_NOW"))
		{
                    logger.info("Case: Initiate GCT Application Payment");		
                    strZone	= req.getParameter("txtZone").toUpperCase().trim();
                    strDvsn	= req.getParameter("txtDvsn").toUpperCase().trim();
                    strSttn     = req.getParameter("txtSttn").toUpperCase().trim();
                    strName     = req.getParameter("txtName").toUpperCase().trim();
                    strDesg     = req.getParameter("txtDesg").toUpperCase().trim();
                    strMobl     = req.getParameter("txtMobl").toUpperCase().trim();
                    strContNo   = req.getParameter("txtContNo").toUpperCase().trim();
                    strEmail    = req.getParameter("txtEmail").toUpperCase().trim();
                    strAddr     = req.getParameter("txtAddr").toUpperCase().trim();
                    strGstIn    = req.getParameter("txtGstIn").toUpperCase().trim();
                    strApltCtgr = req.getParameter("txtApltCtgr").toUpperCase().trim();
                    strPAN      = req.getParameter("txtPAN").toUpperCase().trim();
                    strTAN      = req.getParameter("txtTAN").toUpperCase().trim();
                    strCmdtCont = req.getParameter("txtCmdtCont").toUpperCase().trim();
                    strCmdtDtls = req.getParameter("txtCmdtDtls").toUpperCase().trim();
                    strRfrnId   = req.getParameter("txtRfrnId").toUpperCase().trim();
                    strFileRfrnId  = req.getParameter("txtFileRfrnId").toUpperCase().trim();
                    strGSTAmnt  = req.getParameter("txtGstAmnt").toUpperCase().trim();
                    strAppAmnt  = req.getParameter("txtAppAmnt").toUpperCase().trim();
                    strTotlAmnt = req.getParameter("txtTotlAmnt").toUpperCase().trim();
                    logger.info("strZone:"+strZone+", strDvsn:"+strDvsn+",strSttn:"+strSttn+", strName:"+ strName+", strDesg:"+strDesg+", strMobl:"+strMobl+", strContNo:"+strContNo);
                    logger.info("strEmail:"+strEmail+", strAddr:"+strAddr+",strGstIn:"+strGstIn);
                    logger.info("strApltCtgr:"+strApltCtgr+", strPAN:"+strPAN+",strTAN:"+strTAN);
                    logger.info("strCmdtCont:"+strCmdtCont+", strCmdtDtls:"+strCmdtDtls+", strCmdtList:"+strCmdtList);
                    logger.info("strRfrnId:"+strRfrnId+", strFileRfrnId:"+strFileRfrnId+", strGSTAmnt:"+strGSTAmnt+", strAppAmnt:"+strAppAmnt+", strTotlAmnt:"+strTotlAmnt);
                    logger.info("Calling Payment Gateway");
                    
                    String strRetVal="";
                    GCTSrvcCall obj=new GCTSrvcCall(strCustId, si_ClntId);
                    strRetVal=obj.saveGCTAppl("D", strRfrnId, strDvsn, strSttn, strName, strDesg, strMobl, strContNo, strEmail, strAddr, strPAN, strTAN, strGstIn, strApltCtgr, strFileRfrnId, strCmdtCont, strCmdtDtls);
                    if(obj.getCallStts().equals("S"))
                    {
                        strRfrnId=strRetVal;
                    }
                    else {
                        logger.info("Failed to Save Application: "+strRetVal);
                        /*String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();*/
                        logger.info("Redirecting to Failed Page");
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=FAILED&ErorMesg="+strRetVal);    
                        return;
                    }
                    session.setAttribute("Zone", strZone);                    
                    session.setAttribute("Dvsn", strDvsn);              
                    session.setAttribute("Sttn", strSttn);              
                    session.setAttribute("Name", strName);              
                    session.setAttribute("Desg", strDesg);
                    session.setAttribute("Mobl", strMobl);
                    session.setAttribute("ContNo", strContNo);
                    session.setAttribute("Email", strEmail);
                    session.setAttribute("Addr", strAddr);
                    session.setAttribute("GstIn", strGstIn);
                    session.setAttribute("ApltType", strApltCtgr);
                    session.setAttribute("PAN", strPAN);
                    session.setAttribute("TAN", strTAN);
                    session.setAttribute("RfrnId", strRfrnId);
                    session.setAttribute("FileRfrnId", strFileRfrnId);
                    session.setAttribute("AppAmnt", strAppAmnt);
                    session.setAttribute("GstAmnt", strGSTAmnt);
                    session.setAttribute("TotlAmnt", strTotlAmnt);
                
                    try {
                        final Context context = getInitialContext();
                        FPGSessionEJB sBISessionEJB =
                            (FPGSessionEJB) context.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                        
                        logger.info("Checking the Status of this transaction");
                        
                        EnquiryResponseParams resEnq= sBISessionEJB.trxnStatusVldtMisc(paymentEnquiryParams());
                        if(resEnq.isErrorFlag()) {
                            logger.info("Error Flag true in Enquiry: "+resEnq.getErrorDesc());
                            /*String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();*/
                            logger.info("Redirecting to Failed Page");
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=FAILED");    
                            return;
                        }
                        if(resEnq.isPending()) 
                        {
                            logger.info("Not to Continue with Payment, as it has already been attempted:"+resEnq.getPymtStatus());    

                            String FOISTrxnId=resEnq.getFoisTrxnID();
                            String BankTrxnId=resEnq.getBankRefID();
                            String BankTrxnTime=resEnq.getBankTrxnDate();
                            String PymtStts=resEnq.getPymtStatus();
                            String SttsDesc=resEnq.getStatusDesc();
                            
                            if(resEnq.getPymtStatus().equals("SUCCESS")) 
                            {
                                logger.info("Payment has already been done for this charge");
                                res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=ALREADY_SUCCESS");
                            }
                            if(resEnq.getPymtStatus().equals("PENDING")) 
                            {
                                logger.info("Payment is pending for accrual, please wait");    
                                res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=ALREADY_PENDING");
                            }
                        }
                        else
                        {
                        //trxnPreProcessing
                        logger.info("Fresh Payment: Calling Transaction Pre-processing");
                        TransactionResponseParams trxnres= sBISessionEJB.miscTrxnPreProcessing(getPaymentRequestParams());
                        logger.info("#errorFlag#"+trxnres.isErrorFlag()+"#ErrorMsg#"+trxnres.getErrorDesc()+"#encdata#"+trxnres.getEncdata()+"#merchantCode#"+trxnres.getMerchant_code());
                        logger.info("#URL#"+  trxnres.getRedirectURL()+"#trxnID#"+trxnres.getTrxnID());
                        if(!trxnres.isErrorFlag()) 
                        {   
                            session.setAttribute("PymtTrxnID",trxnres.getTrxnID());
                            /*
                            session.setAttribute("ChrgId",si_ChrgId);
                            session.setAttribute("ChrgType",si_ChrgType);
                            session.setAttribute("ChrgAmnt",si_PymtAmnt);
                            session.setAttribute("CustCode",si_CustCode);
                            session.setAttribute("CnfmDate",si_CnfmDate);
                            session.setAttribute("GSTAmnt",si_GSTAmnt);
                            session.setAttribute("GSTIN",si_GSTIN);
                            */
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtRedirect.jsp?encdata="+trxnres.getEncdata()+"&mccode="+trxnres.getMerchant_code()+"&trxnid="+trxnres.getTrxnID()+"&url="+trxnres.getRedirectURL());
                        }
                        else 
                        {
                            /*String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();*/
                            logger.info("Failed:"+trxnres.getErrorDesc());
                            logger.info("Redirecting to Failure..");
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=FAILED&ErorMesg="+trxnres.getErrorDesc());
                        }

                        if(trxnres.isErrorFlag()) {
                            /*String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();*/
                            logger.info("Failed:"+trxnres.getErrorDesc());
                            logger.info("Redirecting to Failure..");
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=FAILED&ErorMesg="+trxnres.getErrorDesc());
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
                            logger.info("Failed: Session Status-"+req.isRequestedSessionIdValid()+",  Cookie Status-"+req.isRequestedSessionIdFromCookie());;
                            logger.info("Redirecting to Failure..");
                            res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=FAILED");
                }
                try
                {

                    si_ResEncdata=req.getParameter("encdata");
                    logger.info("Encdata:"+si_ResEncdata);
                    System.out.println("PAYMENT_RESPONSE EncData:"+si_ResEncdata);
                    final Context context1= getInitialContext();  

                    FPGSessionEJB sBISessionEJB =
                        (FPGSessionEJB) context1.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                    PostTransactionResponseParams resParam= sBISessionEJB.miscTrxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    String RfrnId=(String)session.getAttribute("RfrnId");
                    String FOISTrxnId=resParam.getFoisTrxnID();
                    String BankTrxnId=resParam.getBankRefID();
                    String BankTrxnTime=resParam.getBankTrxnDate();
                    String PymtStts=resParam.getPymtStatus();
                    String SttsDesc=resParam.getStatusDesc();
                    
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
			if((PymtStts.toUpperCase()).equals("FAILURE"))
			{
				res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=FAILED&SttsDesc="+SttsDesc);
			}
			else
			{
                        	res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts="+PymtStts);
			}
                    }                    
                    else
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=FAILED&SttsDesc="+SttsDesc);
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

                    FPGSessionEJB sBISessionEJB =
                        (FPGSessionEJB) context1.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                    PostTransactionResponseParams resParam= sBISessionEJB.miscTrxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    String RfrnId=(String)session.getAttribute("RfrnId");
                    String FOISTrxnId=resParam.getFoisTrxnID();
                    String BankTrxnId=resParam.getBankRefID();
                    String BankTrxnTime=resParam.getBankTrxnDate();
                    String PymtStts=resParam.getPymtStatus();
                    String SttsDesc=resParam.getStatusDesc();
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=CANCELLED");
                    }                    
                    else
                    {

                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/GCTDashboard.jsp?PymtStts=CANCELLED");
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
            //return new InitialContext(env);
            return new InitialContext();
        }
        
        TransactionRequestParams getPaymentRequestParams() {
            TransactionRequestParams req=new TransactionRequestParams();

            logger.info("strZone:"+strZone+", strDvsn:"+strDvsn+",strSttn:"+strSttn+", strName:"+ strName+", strDesg:"+strDesg+", strMobl:"+strMobl+", strContNo:"+strContNo);
            logger.info("strEmail:"+strEmail+", strAddr:"+strAddr+",strGstIn:"+strGstIn);
            logger.info("strApltCtgr:"+strApltCtgr+", strPAN:"+strPAN+",strTAN:"+strTAN);
            logger.info("strCmdtCont:"+strCmdtCont+", strCmdtDtls:"+strCmdtDtls+", strCmdtList:"+strCmdtList);
            logger.info("strRfrnId:"+strRfrnId+", strFileRfrnId:"+strFileRfrnId+", strGSTAmnt:"+strGSTAmnt+", strAppAmnt:"+strAppAmnt+", strTotlAmnt:"+strTotlAmnt);
            
            req.setAmount(strTotlAmnt);
            req.setAppID("02"); /*02: FBD*/
            req.setCancelURL("https://www.foistest.indianrail.gov.in/RailSAHAY/GCTPymtProcess?Optn=PAYMENT_CANCEL");
            req.setPymtRefID(strRfrnId);
            req.setPymtType("11");  /* 11 for MISC */
            req.setGstNumb(strGstIn); 
            req.setGstAmount(strGSTAmnt);
            //req.setGSTIN(si_GSTIN);
            //req.setGSTAmnt(si_GSTAmnt);
            req.setRemarks("");
            req.setResponseURL("https://www.foistest.indianrail.gov.in/RailSAHAY/GCTPymtProcess?Optn=PAYMENT_RESPONSE");
            req.setUserID(strCustId);
            req.setZonecode(strZone);
            req.setCustCode("GCTO"); /*Hardcoded*/
            req.setPymtRefDate(strCrntDate);
            req.setPGCode("02");  // for SBI 'SBI_MOPS' and for dummyPG 'DUMMY'
            //req.setPGName("99"); // For Dummy
            req.setWRFAjstFlag("N");
            req.setWRFAjstAmnt("0");
            req.setWRFTaxAjstAmnt("0");
            req.setHdlgagntcode("");
            return req;
        }
        
        PostTransactionRequestParams getSBIResponseParams(HttpSession sesn) {
            PostTransactionRequestParams req=new PostTransactionRequestParams();
            logger.info(si_ResEncdata+", "+(String)sesn.getAttribute("RfrnId")+", "+(String)sesn.getAttribute("GstIn")+", "+(String)sesn.getAttribute("GSTAmnt"));
            req.setAppID("02"); //02 for FBD
            //req.setEncdata("NqdWEh7PpMYp4KIm9jUi6Xd8r5NKXWa7bLbYT6z+SmkhIlilRj1QcdZGq4Rq97QLTtaosyVcAx7O1H6vS+CPC90AshWmQjFUD/yGfBVQraktjdIozWjB50p1MJBaZ2QrmE3hZ1Hg1/96oac24yl+Mk8PXr65bN+KMV/LSp9S4nmZMBlFuWtu6X99w5hydiWx+mPUnrE7lFZGuIb59YQ4ndLwQW2xSt0nQoRhL2E0wnN1WNyvsm0lreZ8ONuls+HWdRj0q9pBXaqviPbBcKkx4n83/SELus1iMp2Xiwn2078kX76F7XctrVPgj/lGaUerrHcBXBPU9EFKwpC6iZo/oGbaSPA6Rz7nX3GgYoyu/w8h2Rewp1E22txaAHpKobjgDCwTkghnpmmBWZGo5Gk4BzZ8z6oCzEchh0scXQ0c2hAfx1jwOA9ZMXzXqItEi2NKaKmvOXvTbSvIOcA6QF/R2LnZiz9zeesr4qsBUwhaV4h8j5bv8OJ0PpvnnfD24A1z");
            req.setEncdata(si_ResEncdata);
            req.setPymtRefID((String)sesn.getAttribute("RfrnId"));
            req.setPymtType("MISC");
            req.setPGCode("02");  // for SBI '02' and for dummyPG '99'
            //req.setPGCode("99");
            req.setPymtBreakUp(((String)sesn.getAttribute("GstIn"))+"#"+((String)sesn.getAttribute("GSTAmnt")));
            return req;
        }
        
        EnquiryRequestParams paymentEnquiryParams() {
            EnquiryRequestParams req=new EnquiryRequestParams();

            logger.info("strZone:"+strZone+", strDvsn:"+strDvsn+",strSttn:"+strSttn+", strName:"+ strName+", strDesg:"+strDesg+", strMobl:"+strMobl+", strContNo:"+strContNo);
            logger.info("strEmail:"+strEmail+", strAddr:"+strAddr+",strGstIn:"+strGstIn);
            logger.info("strApltCtgr:"+strApltCtgr+", strPAN:"+strPAN+",strTAN:"+strTAN);
            logger.info("strCmdtCont:"+strCmdtCont+", strCmdtDtls:"+strCmdtDtls+", strCmdtList:"+strCmdtList);
            logger.info("strRfrnId:"+strRfrnId+", strFileRfrnId:"+strFileRfrnId+", strGSTAmnt:"+strGSTAmnt+", strAppAmnt:"+strAppAmnt+", strTotlAmnt:"+strTotlAmnt);
            
            req.setAmount(strTotlAmnt);
            req.setPymtRefID(strRfrnId);
            req.setPymtType("MISC");
            return req;
        }
}