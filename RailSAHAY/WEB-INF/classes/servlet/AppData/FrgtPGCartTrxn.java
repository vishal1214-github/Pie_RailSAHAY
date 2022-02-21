package servlet.AppData;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cris.fois.tms.epay.params.EnquiryRequestParams;
import org.cris.fois.tms.epay.params.EnquiryResponseParams;
import org.cris.fois.tms.epay.params.CartPostTransactionRequestParams;
import org.cris.fois.tms.epay.params.PostTransactionResponseParams;
import org.cris.fois.tms.epay.params.CartTransactionRequestParams;
import org.cris.fois.tms.epay.params.TransactionResponseParams;
import org.cris.fois.tms.epay.params.ChargeDetails;

import java.util.Hashtable;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserProfDtls;
import java.util.ArrayList;
import java.util.List;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;
/*import model.valubean.ChargeDetails;*/
import model.valubean.PymtCartBean;

import java.util.ArrayList;

import org.cris.fois.tms.epay.ejb.FPGSessionEJB;


public class FrgtPGCartTrxn extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(FrgtPGCartTrxn.class.getName());
        UserProfDtls userdtls=null;
        String si_CustId="";
        String si_ResEncdata="";
        String si_HndgAgnt="";
        String si_HndgAgntName="";
        String si_ReqClntId="";
        String si_SesnClntId="";
        
        ArrayList<PymtCartBean> pymtList=null;
        List<ChargeDetails> pymtChrgList=null;
        String si_CartPymtZone="";
        String si_CartPymtCust="";
        String si_CartPymtNetAmnt="";
        ArrayList<ChargeDetails> chargeList=null;
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling FrgtPGCartTrxn.java...");
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
                si_ReqClntId=si_ClntId;
                logger.info("Client Id from Request:"+si_ClntId);
                String strSesnClntId="";
                try {
                    strSesnClntId=(String)session.getAttribute("ClntID");
                    logger.info("Client Id from Session:"+strSesnClntId);
                }
                catch(Exception e) {
                    logger.info("Exception in getting Client Id from Session");
                }
                si_SesnClntId=strSesnClntId;
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
                    logger.info("Exception in getting session attributes, redirecting to Login Screen");
                    req.setAttribute("message","Session Timed Out! Please Login Again to Make the Payments");
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/eDmndLogin.jsp");   
                }
                
                if(si_CustId.equals("") ||(si_CustId==null)) {
                    logger.info("Exception in getting session attributes, redirecting to Login Screen");
                    req.setAttribute("message","Session Timed Out! Please Login Again to Make the Payments");
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/eDmndLogin.jsp");   
                }
                String si_Optn  = req.getParameter("Optn").toUpperCase().trim();
                
                try
                {
                    pymtList = (ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr");
                }
                catch(Exception e) 
                {
                    logger.info("Exception in getting list of payments, redirecting to Payments Screen");
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=FAILED&ErorMesg=Payment Cart does not exist! Please create your cart first");   
                }
	    
	    if(si_Optn.equals("CANCEL_USER_TRXN"))
	    {
	        logger.info("Case: Cancel User Transaction");              
	        String si_AppTrxnId = req.getParameter("AppTrxnId").toUpperCase().trim();
                String si_Rmrk="TRANSACTION DISCARDED BY USER";
	        logger.info("si_AppTrxnId:"+si_AppTrxnId+", si_Rmrk:"+si_Rmrk+",si_ClntId:"+si_ClntId);
	        logger.info("Going to Cancel the Transaction");
	        String strData=""; 
                try 
                {
                    final Context contextVldt = getInitialContext();
                    FPGSessionEJB sBISessionEJB =(FPGSessionEJB) contextVldt.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                    
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
                logger.info("Case: Initiate Freight Payment");
                logger.info("Calling Payment Gateway");
                si_CartPymtNetAmnt=(String)req.getParameter("NetAmnt");
                logger.info("Net Amount:"+si_CartPymtNetAmnt);
                
                logger.info("Size of payment list:"+pymtList.size());
                pymtChrgList=new ArrayList<ChargeDetails>();
                for(int i=0;i<pymtList.size();i++) 
                {
                    PymtCartBean bn=(PymtCartBean)pymtList.get(i);
                    logger.info("Object derived for PymtCartBean");
                    
                    ChargeDetails obj=new ChargeDetails();
                    logger.info("Object ChargeDetails created");
                    obj.setAmount(bn.getNetAmnt());
                    obj.setPymtType(bn.getChrgTypeCode());
                    obj.setGstAmount(bn.getGstAmnt());
                    obj.setPymtRefID(bn.getChrgId());
                    obj.setInvcID(bn.getInvcId());
                    obj.setPymtRefDate(bn.getCnfmDate());
                    obj.setGstNumb(bn.getCnsrGstin());
                    obj.setWRFAjstFlag(bn.getWRFAjstFlag());
                    obj.setWRFAjstAmnt(bn.getWRFAjstAmnt());
                    obj.setWRFTaxAjstAmnt("");
                    obj.setHdlgagntcode(bn.getHndgAgnt());
                    logger.info("Populated Values");
                    pymtChrgList.add(obj);
                    logger.info("Adding Payment: Charge Type:"+bn.getChrgTypeCode()+", Charge Id:"+bn.getChrgId()+", Amount:"+bn.getAmnt()+", GSTIN:"+bn.getCnsrGstin()+", GST Amount:"+bn.getGstAmnt()+", Handling Agent:"+bn.getHndgAgnt()+", WRFFlag:"+bn.getWRFAjstFlag()+", WRFAmount:"+bn.getWRFAjstAmnt());
                    if(i==0) 
                    {
                        si_CartPymtZone=bn.getZone();
                        si_CartPymtCust=bn.getCustCode();
                        logger.info("Cart Payments Zone:"+si_CartPymtZone);
                        logger.info("Cart Payments Customer:"+si_CartPymtCust);
                    }
                } 
                logger.info("Setting Session Attributes");
                session.setAttribute("PymtChrgList",pymtChrgList);
                session.setAttribute("CartPymtZone",si_CartPymtZone);
                session.setAttribute("CartPymtCust",si_CartPymtCust);
                session.setAttribute("CartPymtNetAmnt",si_CartPymtNetAmnt);
                    
                logger.info("Cart Payments Net Amount:"+si_CartPymtNetAmnt);
                
                try {
                    final Context context = getInitialContext();
                    FPGSessionEJB sBISessionEJB =
                        (FPGSessionEJB) context.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                    
                    //trxnPreProcessing
                    logger.info("Fresh Payment: Calling Transaction Pre-processing");
                    TransactionResponseParams trxnres= sBISessionEJB.groupTrxnPreProcessing(getPaymentRequestParams());
                    logger.info("#errorFlag#"+trxnres.isErrorFlag()+"#ErrorMsg#"+trxnres.getErrorDesc()+"#encdata#"+trxnres.getEncdata()+"#merchantCode#"+trxnres.getMerchant_code());
                    logger.info("#URL#"+  trxnres.getRedirectURL()+"#trxnID#"+trxnres.getTrxnID());
                    if(!trxnres.isErrorFlag()) 
                    {   
                        session.setAttribute("PymtTrxnID",trxnres.getTrxnID());
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtRedirect.jsp?encdata="+trxnres.getEncdata()+"&mccode="+trxnres.getMerchant_code()+"&trxnid="+trxnres.getTrxnID()+"&url="+trxnres.getRedirectURL());
                    }
                    else 
                    {
                        logger.info("Failed:"+trxnres.getErrorDesc());
                        logger.info("Charge Failed:"+trxnres.getErrorPymtRefID());
                        logger.info("Redirecting to Failure..");
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=FAILED&ChrgId="+trxnres.getErrorPymtRefID()+"&ErorMesg="+trxnres.getErrorDesc());
                    }

                    if(trxnres.isErrorFlag()) {
                        /*String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();*/
                        logger.info("Failed:"+trxnres.getErrorDesc());
                        logger.info("Redirecting to Failure..");
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=FAILED&ChrgId="+trxnres.getErrorPymtRefID()+"&ErorMesg="+trxnres.getErrorDesc());
                    }
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
                    res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=FAILED");
                }
                try
                {

                    si_ResEncdata=req.getParameter("encdata");
                    logger.info("Encdata:"+si_ResEncdata);
                    System.out.println("PAYMENT_RESPONSE EncData:"+si_ResEncdata);
                    final Context context1= getInitialContext();  

                    FPGSessionEJB sBISessionEJB =(FPGSessionEJB) context1.lookup("FPGSessionEJB#org.cris.fois.tms.epay.ejb.FPGSessionEJB");
                    PostTransactionResponseParams resParam= sBISessionEJB.groupTrxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    
                    String FOISTrxnId=resParam.getFoisTrxnID();
                    String BankTrxnId=resParam.getBankRefID();
                    String BankTrxnTime=resParam.getBankTrxnDate();
                    String PymtStts=resParam.getPymtStatus();
                    String SttsDesc=resParam.getStatusDesc();
                    String erorDesc=resParam.getErrorDesc();
                    
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
			if((PymtStts.toUpperCase()).equals("FAILURE"))
			{
				res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=FAILED&SttsDesc="+SttsDesc+"&ErorMesg="+erorDesc);
			}
			else
			{
                        	res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts="+PymtStts);
			}
                    }                    
                    else
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?FOISTrxnId="+FOISTrxnId+"&BankTrxnId="+BankTrxnId+"&BankTrxnTime="+BankTrxnTime+"&PymtStts=FAILED&SttsDesc="+SttsDesc+"&ErorMesg="+erorDesc);
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
                    PostTransactionResponseParams resParam= sBISessionEJB.groupTrxnPostProcessing(getSBIResponseParams(session));
                    logger.info("#errorFlag#"+resParam.isErrorFlag()+"#ErrorMsg#"+resParam.getErrorDesc());
                    logger.info("#txnAmount#"+resParam.getAmount()+"#BankTrxnId#"+resParam.getBankRefID()+"#bankTxnTime#"+resParam.getBankTrxnDate()+"#foisTrxnId#"+resParam.getFoisTrxnID()+"#pymtRefId#"+resParam.getPymtRefID()+"#pymtStatus#"+resParam.getPymtStatus()+"#statusDesc#"+resParam.getStatusDesc());
                    String FOISTrxnId=resParam.getFoisTrxnID();
                    String BankTrxnId=resParam.getBankRefID();
                    String BankTrxnTime=resParam.getBankTrxnDate();
                    String PymtStts=resParam.getPymtStatus();
                    String SttsDesc=resParam.getStatusDesc();
                    if(!resParam.isErrorFlag()) 
                    {
                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=CANCELLED");
                    }                    
                    else
                    {

                        String strSesnPymtTrxnId=((String)session.getAttribute("PymtTrxnID")).trim();
                        res.sendRedirect("https://foistest.indianrail.gov.in/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=CANCELLED");
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
        
        CartTransactionRequestParams getPaymentRequestParams() {
            CartTransactionRequestParams req=new CartTransactionRequestParams();
            logger.info("Setting Request Parameters in Preprocessing");
            logger.info("Client Id: Request:"+si_ReqClntId+", Session:"+si_SesnClntId);
            logger.info("si_CustId:"+si_CustId+", si_CartPymtZone:"+si_CartPymtZone+", si_CartPymtCust:"+si_CartPymtCust+", si_CartPymtNetAmnt:"+si_CartPymtNetAmnt+", List Size:"+pymtChrgList.size());
            req.setAppID("02");
            req.setUserID(si_CustId);
            req.setResponseURL("https://www.foistest.indianrail.gov.in/RailSAHAY/FrgtPGCartTrxn?Optn=PAYMENT_RESPONSE");
            req.setCancelURL("https://www.foistest.indianrail.gov.in/RailSAHAY/FrgtPGCartTrxn?Optn=PAYMENT_CANCEL");
            req.setZonecode(si_CartPymtZone);
            req.setCustCode(si_CartPymtCust);
            req.setRemarks("");
            req.setPGCode("02");
            req.setPymtType("10");
            req.setAmount(si_CartPymtNetAmnt);
            req.setChrgList(pymtChrgList);
            return req;
        }
        
        CartPostTransactionRequestParams getSBIResponseParams(HttpSession sesn) {
            CartPostTransactionRequestParams req=new CartPostTransactionRequestParams();
            List pymtChrgList1=(List<ChargeDetails>)sesn.getAttribute("PymtChrgList");
            logger.info("Client Id: Request:"+si_ReqClntId+", Session:"+si_SesnClntId);
            
            logger.info(si_ResEncdata+", Zone:"+(String)sesn.getAttribute("CartPymtZone")+", Customer:"+(String)sesn.getAttribute("CartPymtCust")+", Net Amount:"+(String)sesn.getAttribute("CartPymtNetAmnt"));
            req.setAppID("02"); //02 for FBD
            req.setEncdata(si_ResEncdata);
            req.setPymtType("10");
            req.setPGCode("02");  // for SBI '02' and for dummyPG '99'
            req.setChrgList(pymtChrgList1);
            return req;
        }
        
}