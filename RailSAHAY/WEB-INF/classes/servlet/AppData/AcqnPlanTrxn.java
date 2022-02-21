package servlet.AppData;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.AcqnPlanSrvc;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.List;
import org.apache.log4j.Logger;

import util.logger.FoisLogger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import util.GG_DBConnection;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class AcqnPlanTrxn extends HttpServlet
{
    static Logger logger = FoisLogger.getLogger(AcqnPlanTrxn.class.getName()); 
    private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "UPLOAD";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    Connection  connection=null;
    String fileName ="";
    String si_Optn="";
    
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling AcqnPlanTrxn.java...");
		HttpSession session=req.getSession();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
                Date date = new Date(); 
		String strLognFlag=((String)session.getAttribute("LognLocnFlag")).trim();
		String strLognLocn=((String)session.getAttribute("LognLocn")).trim();
                String strZone="";
                String strDvsn="";
		if(strLognFlag.equals("Z"))
			strZone=strLognLocn;
		if(strLognFlag.equals("D"))
			strDvsn=strLognLocn;
		
		si_Optn	= req.getParameter("Optn").toUpperCase().trim();

		/*req.setCharacterEncoding("UTF-8");*/
		res.setHeader("Cache-Control", "no-cache");
		String si_UserID = "";
		String si_ClntID = "";
                String strInpt="";
                
		try
		{
                    si_UserID		= (String) session.getAttribute("UserID");
                    si_ClntID		= (String) session.getAttribute("ClntID");
		}
		catch(Exception e)
		{
			si_UserID="";
			si_ClntID="";
		}
		//String si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		logger.info("si_Optn:"+ si_Optn);
        
                if(si_Optn.equals("FREEZE_DATA"))
                {
                    logger.info("Case: Freeze Data for the Month");
                                 
                    AcqnPlanSrvc obj    =	new AcqnPlanSrvc(si_UserID, si_ClntID);
                    logger.info("Freezing Data for Zone:"+strZone);
                    String strRetVal="";
                    try
                    {
                        strRetVal=obj.freezeData(strZone);
                        logger.info("Freeze Data Status:"+strRetVal);
                        res.sendRedirect("/RailSAHAY/pages/IRBDU_UpdateZonalPlan.jsp?CnfmStts="+strRetVal);
                    }
                    catch(Exception e)  
                    {
                        logger.info("In exception of Freezing Data:"+e.getMessage());
                        res.sendRedirect("/RailSAHAY/pages/IRBDU_UpdateZonalPlan.jsp?CnfmStts=FAILED (TECHNICAL ISSUE)");
                     }
                    return;
                    
                }
		if(si_Optn.equals("RESOURCE_CENTRE"))
		{
                    logger.info("Case: Resource Centre");
                    String si_Ctgr = req.getParameter("Ctgr").toUpperCase().trim();
                    String strData="";
                    logger.info("Category:"+si_Ctgr);
                    if(si_Ctgr.equals("DEL_RESOURCE"))
                    {
                        logger.info("Case: Delete Resource");
                        String si_ResId	    = (String) req.getParameter("ResId").trim().toUpperCase();                        
                        AcqnPlanSrvc obj    =	new AcqnPlanSrvc(si_UserID, si_ClntID);
                        logger.info("Working for si_ResId:"+si_ResId);
                        String strRetVal="";
                        try
                        {
                            strRetVal=obj.delResource(strZone, si_ResId);
                            logger.info("Delete Resource Status:"+strRetVal);
                            if(strRetVal.equals("SUCCESS"))
                            {
                                strData="{\"Stts\":\"SUCCESS\", \"ErorMesg\":\"\"}";
                            }
                            else 
                            {
                                strData="{\"Stts\":\"FAILED\", \"ErorMesg\":\""+strRetVal+"\"}";
                            }
                            res.getWriter().write(strData);
                        }
                        catch(Exception e)  
                        {
                             strData="{\"Stts\":\"FAILED\", \"ErorMesg\":\"Error in Deletion\"}";
                             res.getWriter().write(strData);
                         }
                        return;
                    }
                    if(si_Ctgr.equals("ADD_RESOURCE"))
                    {
                        logger.info("Case: Add Resource");
                        AcqnPlanSrvc obj    =   new AcqnPlanSrvc(si_UserID, si_ClntID);
                        String strRetVal="";
                        String si_Zone	    = (String) req.getParameter("Zone").trim().toUpperCase();
                        String si_Dvsn	    = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        String si_Cmdt	    = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        String si_Inds	    = (String) req.getParameter("Inds").trim().toUpperCase();
                        String si_Name	    = (String) req.getParameter("Name").trim().toUpperCase();
                        String si_Mobl	    = (String) req.getParameter("Mobl").trim().toUpperCase();
                        String si_Landline  = (String) req.getParameter("LandLine").trim().toUpperCase();
                        String si_Email	    = (String) req.getParameter("Email").trim().toUpperCase();
                        String si_Addr	    = (String) req.getParameter("Addr").trim().toUpperCase();
                        String si_ResId	    = (String) req.getParameter("ResId").trim().toUpperCase();
                        try
                        {
                            strRetVal=obj.addResDtls(si_Zone, si_Dvsn, si_Cmdt, si_Inds, si_Name, si_Mobl, si_Landline, si_Email, si_Addr, si_ResId);
                            logger.info("Add Resource Status:"+strRetVal);
                            if(strRetVal.equals("SUCCESS"))
                            {
                                strData="{\"Stts\":\"SUCCESS\", \"ErorMesg\":\"\"}";
                            }
                            else 
                            {
                                strData="{\"Stts\":\"FAILED\", \"ErorMesg\":\""+strRetVal+"\"}";
                            }
                            res.getWriter().write(strData);
                        }
                        catch(Exception e)  
                        {
                             strData="{\"Stts\":\"FAILED\", \"ErorMesg\":\"Error in Addition\"}";
                             res.getWriter().write(strData);
                         }
                        return;
                    }
                    if(si_Ctgr.equals("RESOURCE_LIST"))
                    {
                        logger.info("Case: Fetch Resources List");
                        
                        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
                        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();  
                        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim();                
                        logger.info("Inputs received: si_Zone-"+si_Zone+", si_Dvsn-"+si_Dvsn);
                        try
                        {
                            String strSrvcData[][];
                            strSrvcData=obj.fetchResourceDtls(si_Zone, si_Dvsn);
                            String strCallStts=obj.getCallStts();
                            String strCallMesg=obj.getCallMesg();
        
                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
                            int intDataLen=strSrvcData.length;
                            logger.info("DataList1 Length:"+intDataLen);
                            for(int i=0;i<intDataLen;i++)
                            {   
                                if(i==(intDataLen-1))
                                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Cmdt\":\""+strSrvcData[i][2]+"\",\"Inds\":\""+strSrvcData[i][3]+"\",\"Name\":\""+strSrvcData[i][4]+"\",\"Mobl\":\""+strSrvcData[i][5]+"\",\"Landline\":\""+strSrvcData[i][6]+"\",\"Email\":\""+strSrvcData[i][7]+"\",\"Addr\":\""+strSrvcData[i][8]+"\",\"UserId\":\""+strSrvcData[i][9]+"\",\"UpdtTime\":\""+strSrvcData[i][10]+"\",\"ResId\":\""+strSrvcData[i][11]+"\"}";
                                else
                                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"Cmdt\":\""+strSrvcData[i][2]+"\",\"Inds\":\""+strSrvcData[i][3]+"\",\"Name\":\""+strSrvcData[i][4]+"\",\"Mobl\":\""+strSrvcData[i][5]+"\",\"Landline\":\""+strSrvcData[i][6]+"\",\"Email\":\""+strSrvcData[i][7]+"\",\"Addr\":\""+strSrvcData[i][8]+"\",\"UserId\":\""+strSrvcData[i][9]+"\",\"UpdtTime\":\""+strSrvcData[i][10]+"\",\"ResId\":\""+strSrvcData[i][11]+"\"},";
                            }
                            strData+="]}";
                            /*logger.info(strData);*/
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
}