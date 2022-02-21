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

public class GCTQryJson extends HttpServlet
{	
	static Logger logger = FoisLogger.getLogger(GCTQryJson.class.getName());

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
		logger.info("Calling GCTQryJson.java...");
		HttpSession session=req.getSession();
			
		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
                String strData="";
		try
		{
                    si_CustId		        = ((String)session.getAttribute("custId")).trim();
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
                try {
                    si_Optn=(String)req.getParameter("Optn").toUpperCase().trim();
                }
                catch(Exception e) {
                    logger.info("Invalid Input Optn");
                    strData="{\"Stts\":\"F\",\"ErorMesg\":\"Invalid Input\"}";                          
                    res.getWriter().write(strData);
                    return;
                }
                if(si_Optn.equals("DRAFT_APPL_LIST"))
                {
                    try
                    {
                            String strSrvcData[][];
                            GCTSrvcCall obj=new GCTSrvcCall(si_CustId, si_ClntId);
                            strSrvcData=obj.fetchDraftAppl();
                            
                            strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\"";				
                            
                            strData+=",\"ApplList\":[";
                            
                            int intDataLen=strSrvcData.length;
                            logger.info("Fetching data..");
                            for(int i=0;i<intDataLen;i++)
                            {                               
                                    if(i==(intDataLen-1))
                                        strData+="{\"RfrnId\":\""+strSrvcData[i][0]+"\",\"FileRfrnId\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Desg\":\""+strSrvcData[i][6]+"\",\"Mobl\":\""+strSrvcData[i][7]+"\",\"ContNo\":\""+strSrvcData[i][8]+"\",\"Email\":\""+strSrvcData[i][9]+"\",\"Addr\":\""+strSrvcData[i][10]+"\",\"GstIn\":\""+strSrvcData[i][11]+"\",\"ApltType\":\""+strSrvcData[i][12]+"\",\"PAN\":\""+strSrvcData[i][13]+"\",\"TAN\":\""+strSrvcData[i][14]+"\",\"FileName1\":\""+strSrvcData[i][15]+"\",\"FileName2\":\""+strSrvcData[i][16]+"\",\"FileName3\":\""+strSrvcData[i][17]+"\",\"FileName4\":\""+strSrvcData[i][18]+"\",\"FileName5\":\""+strSrvcData[i][19]+"\",\"FileName6\":\""+strSrvcData[i][20]+"\",\"FileName7\":\""+strSrvcData[i][21]+"\",\"PlanFile\":\""+strSrvcData[i][22]+"\",\"CmdtCont\":\""+strSrvcData[i][23]+"\",\"UpdtTime\":\""+strSrvcData[i][24]+"\",\"CmdtDtls\":\""+strSrvcData[i][25]+"\"}";
                                    else
                                        strData+="{\"RfrnId\":\""+strSrvcData[i][0]+"\",\"FileRfrnId\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Desg\":\""+strSrvcData[i][6]+"\",\"Mobl\":\""+strSrvcData[i][7]+"\",\"ContNo\":\""+strSrvcData[i][8]+"\",\"Email\":\""+strSrvcData[i][9]+"\",\"Addr\":\""+strSrvcData[i][10]+"\",\"GstIn\":\""+strSrvcData[i][11]+"\",\"ApltType\":\""+strSrvcData[i][12]+"\",\"PAN\":\""+strSrvcData[i][13]+"\",\"TAN\":\""+strSrvcData[i][14]+"\",\"FileName1\":\""+strSrvcData[i][15]+"\",\"FileName2\":\""+strSrvcData[i][16]+"\",\"FileName3\":\""+strSrvcData[i][17]+"\",\"FileName4\":\""+strSrvcData[i][18]+"\",\"FileName5\":\""+strSrvcData[i][19]+"\",\"FileName6\":\""+strSrvcData[i][20]+"\",\"FileName7\":\""+strSrvcData[i][21]+"\",\"PlanFile\":\""+strSrvcData[i][22]+"\",\"CmdtCont\":\""+strSrvcData[i][23]+"\",\"UpdtTime\":\""+strSrvcData[i][24]+"\",\"CmdtDtls\":\""+strSrvcData[i][25]+"\"},";
                            }
                            strData+="]}";
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
            if(si_Optn.equals("STTSWISE_APPL_LIST"))
            {
                try
                {
                        String strSrvcData[][];
                        String si_Stts=(String)req.getParameter("AppStts");
                        GCTSrvcCall obj=new GCTSrvcCall(si_CustId, si_ClntId);
                        strSrvcData=obj.fetchSttsWiseAppl(si_Stts);
                        
                        strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\"";                               
                        
                        strData+=",\"ApplList\":[";
                        
                        int intDataLen=strSrvcData.length;
                        logger.info("Fetching data..");
                        for(int i=0;i<intDataLen;i++)
                        {                               
                                if(i==(intDataLen-1))
                                    strData+="{\"RfrnId\":\""+strSrvcData[i][0]+"\",\"FileRfrnId\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Desg\":\""+strSrvcData[i][6]+"\",\"GstIn\":\""+strSrvcData[i][7]+"\",\"ApltType\":\""+strSrvcData[i][8]+"\",\"FileName1\":\""+strSrvcData[i][9]+"\",\"FileName2\":\""+strSrvcData[i][10]+"\",\"FileName3\":\""+strSrvcData[i][11]+"\",\"FileName4\":\""+strSrvcData[i][12]+"\",\"FileName5\":\""+strSrvcData[i][13]+"\",\"FileName6\":\""+strSrvcData[i][14]+"\",\"FileName7\":\""+strSrvcData[i][15]+"\",\"PlanFile\":\""+strSrvcData[i][16]+"\",\"CmdtCont\":\""+strSrvcData[i][17]+"\",\"AplyTime\":\""+strSrvcData[i][18]+"\",\"ClosTime\":\""+strSrvcData[i][19]+"\",\"CrntStts\":\""+strSrvcData[i][20]+"\",\"SttsTime\":\""+strSrvcData[i][21]+"\",\"PymtStts\":\""+strSrvcData[i][22]+"\",\"PymtTime\":\""+strSrvcData[i][23]+"\",\"PndgWith\":\""+strSrvcData[i][24]+"\",\"Rmrk\":\""+strSrvcData[i][25]+"\",\"CmdtDtls\":\""+strSrvcData[i][26]+"\"}";
                                else
                                    strData+="{\"RfrnId\":\""+strSrvcData[i][0]+"\",\"FileRfrnId\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"Sttn\":\""+strSrvcData[i][3]+"\",\"SttnName\":\""+strSrvcData[i][4]+"\",\"Name\":\""+strSrvcData[i][5]+"\",\"Desg\":\""+strSrvcData[i][6]+"\",\"GstIn\":\""+strSrvcData[i][7]+"\",\"ApltType\":\""+strSrvcData[i][8]+"\",\"FileName1\":\""+strSrvcData[i][9]+"\",\"FileName2\":\""+strSrvcData[i][10]+"\",\"FileName3\":\""+strSrvcData[i][11]+"\",\"FileName4\":\""+strSrvcData[i][12]+"\",\"FileName5\":\""+strSrvcData[i][13]+"\",\"FileName6\":\""+strSrvcData[i][14]+"\",\"FileName7\":\""+strSrvcData[i][15]+"\",\"PlanFile\":\""+strSrvcData[i][16]+"\",\"CmdtCont\":\""+strSrvcData[i][17]+"\",\"AplyTime\":\""+strSrvcData[i][18]+"\",\"ClosTime\":\""+strSrvcData[i][19]+"\",\"CrntStts\":\""+strSrvcData[i][20]+"\",\"SttsTime\":\""+strSrvcData[i][21]+"\",\"PymtStts\":\""+strSrvcData[i][22]+"\",\"PymtTime\":\""+strSrvcData[i][23]+"\",\"PndgWith\":\""+strSrvcData[i][24]+"\",\"Rmrk\":\""+strSrvcData[i][25]+"\",\"CmdtDtls\":\""+strSrvcData[i][26]+"\"},";
                        }
                        strData+="]}";
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
            if(si_Optn.equals("APP_SMRY"))
            {
                try
                {
                        String strSrvcData[];
                        GCTSrvcCall obj=new GCTSrvcCall(si_CustId, si_ClntId);
                        strSrvcData=obj.fetchAppSmry();
                        
                        strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\"";                               
                        if((obj.getCallStts()).equals("S"))
                        {
                                strData+=",\"AppCont1\":\""+strSrvcData[0]+"\"";    
                                strData+=",\"AppCont2\":\""+strSrvcData[1]+"\"";    
                                strData+=",\"AppCont3\":\""+strSrvcData[2]+"\"";    
                                strData+=",\"AppCont4\":\""+strSrvcData[3]+"\"";    
                                strData+=",\"AppCont5\":\""+strSrvcData[4]+"\"}";    
                        }
                        else 
                        {
                            strData+="}";
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
            if(si_Optn.equals("FETCH_FILE_LIST"))
            {
                String strFileRfrnId=(String)req.getParameter("FileRfrnId");
                try
                {
                        String strSrvcData[];
                        GCTSrvcCall obj=new GCTSrvcCall(si_CustId, si_ClntId);
                        strSrvcData=obj.fetchFileList(strFileRfrnId);
                        
                        strData="{\"Stts\":\""+obj.getCallStts()+"\",\"ErorMesg\":\""+obj.getCallMesg()+"\",";                               
                        strData+="\"File0\":\""+strSrvcData[0]+"\",\"File1\":\""+strSrvcData[1]+"\",\"File2\":\""+strSrvcData[2]+"\",\"File3\":\""+strSrvcData[3]+"\",\"File4\":\""+strSrvcData[4]+"\",\"File5\":\""+strSrvcData[5]+"\",\"File6\":\""+strSrvcData[6]+"\",\"File7\":\""+strSrvcData[7]+"\"}";
                        
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
        }

}