package servlet.AppData;

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

import model.GCTSrvcCall;
import util.logger.FoisLogger;
import org.apache.log4j.Logger;

import Cris.util.CrisFileStringOpr;
import tuxedo.FOISTuxedo;
import java.io.BufferedReader;
import java.util.HashMap;

public class GCTSaveAppData extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(GCTSaveAppData.class.getName());

	String si_CustId = "";
        String si_ClntId = "";
        String strCallStts="";
        String strCallMesg="";
        String si_Optn="";
        
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GCTSaveAppData.java...");
		HttpSession session=req.getSession();
			
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
                String strData="";
		try
		{
                    si_CustId	 = ((String)session.getAttribute("custId")).trim();
                    if(si_CustId==null)
                        si_CustId="FBD USER";
                    logger.info("si_CustId:"+si_CustId);
                    si_ClntId = req.getHeader("X-FORWARDED-FOR");
                    if (si_ClntId == null) 
                    {
                            si_ClntId = req.getRemoteAddr();
                    }
                    if (si_ClntId.indexOf(",")>0)
                    {
                            si_ClntId = si_ClntId.substring(0,si_ClntId.indexOf(","));
                    }
                    logger.info("Client Id:"+si_ClntId);

                }
                catch(Exception e) 
                {
                    /*strData="{\"Stts\":\"F\",\"ErorMesg\":\"Unauthorized Acccess\"}";                          
                    res.getWriter().write(strData);
                    return;
                    */
                    si_CustId="FBD USER";
                    si_ClntId="1.1.1.1";
                }
                logger.info("Customer Id:"+si_CustId);
                try {
                    si_Optn=(String)req.getParameter("Optn").toUpperCase().trim();
                }
                catch(Exception e) {
                    logger.info("Invalid Input Optn");
                    strData="{\"Stts\":\"F\",\"ErorMesg\":\"Invalid Input\"}";                          
                    res.getWriter().write(strData);
                    return;
                }
                logger.info("si_Optn:"+si_Optn);
                if(si_Optn.equals("SAVE_DRAFT"))
                {
                    logger.info("Case of Save Draft");
                    String si_RfrnId=(String)req.getParameter("RfrnId").toUpperCase().trim();
                    logger.info(si_RfrnId);
                    String si_FileRfrnId=(String)req.getParameter("GCTFileRfrnId").toUpperCase().trim();
                logger.info(si_FileRfrnId);
                    String si_Dvsn=(String)req.getParameter("Dvsn").toUpperCase().trim();
                logger.info("Dvsn:"+si_Dvsn);
                    String si_Sttn=(String)req.getParameter("Sttn").toUpperCase().trim();
                logger.info("Sttn:"+si_Sttn);
                    String si_Name=(String)req.getParameter("Name").toUpperCase().trim();
                logger.info("Name:"+si_Name);
                    String si_Desg=(String)req.getParameter("Desg").toUpperCase().trim();
                logger.info("Desg:"+si_Desg);
                    String si_Mobl=(String)req.getParameter("Mobl");
                logger.info("Mobl:"+si_Mobl);
                    String si_ContNo=(String)req.getParameter("ContNo");
                logger.info("ContNo:"+si_ContNo);
                    String si_Email=(String)req.getParameter("Email");
                logger.info("Email:"+si_Email);
                    String si_Addr=(String)req.getParameter("Addr").toUpperCase().trim();
                logger.info("Addr:"+si_Addr);
                    String si_GstIn=(String)req.getParameter("GstIn").toUpperCase().trim();
                logger.info("GstIn:"+si_GstIn);
                    String si_ApltCtgr=(String)req.getParameter("ApltCtgr").toUpperCase().trim();
                logger.info("ApltCtgr:"+si_ApltCtgr);
                    String si_PAN=(String)req.getParameter("PAN").toUpperCase().trim();
                logger.info("PAN:"+si_PAN);
                    String si_TAN=(String)req.getParameter("TAN").toUpperCase().trim();
                logger.info("TAN:"+si_TAN);
                    String si_CmdtCont=(String)req.getParameter("CmdtCont");
                logger.info("Cmdtcont:"+si_CmdtCont);
                    String si_CmdtList=(String)req.getParameter("CmdtList").toUpperCase().trim();
                logger.info("CmdtList:"+si_CmdtList);
                    String si_CmdtDtls=(String)req.getParameter("CmdtDtls").toUpperCase().trim();
                logger.info("CmdtDtls:"+si_CmdtDtls);
                    try
                    {
                            String strRfrnId="";
                            String strRetVal="";
                            GCTSrvcCall obj=new GCTSrvcCall(si_CustId, si_ClntId);
                            strRetVal=obj.saveGCTAppl("D", si_RfrnId, si_Dvsn, si_Sttn, si_Name, si_Desg, si_Mobl, si_ContNo, si_Email, si_Addr, si_PAN, si_TAN, si_GstIn, si_ApltCtgr, si_FileRfrnId, si_CmdtCont, si_CmdtDtls);
                            if(obj.getCallStts().equals("S"))
                            {
                                strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\",\"RfrnId\":\""+strRetVal+"\"}";				
                            }
                            else {
                                strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\",\"RfrnId\":\"\"}";                             
                            }
                            logger.info("Data Received: "+strData);
                            res.getWriter().write(strData);
                    }
                    catch(Exception e)
                    {
                            System.out.println("Error in Data Fetch");
                            res.getWriter().write("Data Contains Error! or No Records");
                    }
                return;
            }
            if(si_Optn.equals("DELETE_DRAFT"))
            {
                logger.info("Entered in Delete Draft from Database");
                strData="";                        
                String si_RfrnId="";
                try
                {
                    si_RfrnId = req.getParameter("RfrnId").toUpperCase().trim();
                    if((si_RfrnId==null) || (si_RfrnId.equals(""))) 
                    {      
                        strData+="{\"Stts\":\"F\",\"ErorMesg\":\"Not a valid Application, please try again\"}";
                        logger.info(strData);
                        res.getWriter().write(strData);
                        return;
                    }
                }
                catch(Exception e) 
                {
                    strData+="{\"Stts\":\"F\",\"ErorMesg\":\"Not a valid Application, please try again\"}";
                    logger.info(strData);
                    res.getWriter().write(strData);
                    return;
                }
                try
                {
                    GCTSrvcCall obj=new GCTSrvcCall(si_CustId,si_ClntId);
                    
                    logger.info("si_RfrnId:"+si_RfrnId);
                    
                    obj.delDraftApp(si_RfrnId);
                    logger.info("Service Call Status:"+obj.getCallStts()+", Error Description:"+obj.getCallMesg());
                    /*Finished calling Tuxedo Service*/
                    if((obj.getCallStts()).equals(("S")))
                    {
                        strData+="{\"Stts\":\"S\",\"ErorMesg\":\"\"}";
                    }
                    else 
                    {
                        strData+="{\"Stts\":\"F\",\"ErorMesg\":\""+obj.getCallMesg()+"\"}";
                    }
                }
                catch(Exception e) {
                    strData+="{\"Stts\":\"F\",\"ErorMesg\":\""+e.getMessage()+"\"}";    
                }
                logger.info(strData);
                res.getWriter().write(strData);
            }
        }

}