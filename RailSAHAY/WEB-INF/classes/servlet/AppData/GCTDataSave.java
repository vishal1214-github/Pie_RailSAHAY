package servlet.AppData;

import java.io.IOException;
import java.lang.Math;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

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
import java.sql.Types;
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
import model.GCTSrvcCall;

public class GCTDataSave extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GCTDataSave.class.getName()); 
	private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "UPLOAD";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    Connection  connection=null;
    Connection  con=null;
    String fileName ="";
    
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GCTDataSave.java...");
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
                
		boolean isMultiPart		= ServletFileUpload.isMultipartContent(req);
		logger.info("Received isMultipart");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String si_Optn="";
		String si_Rmrk="";
		FileItem file = null;
		String strFileName[]=null;
		long li_filesize[]=null;
		InputStream fi[]=null;
                InputStream planFile=null;
                String strPlanFileName="";
                long li_planfilesize=0;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
                Date date = new Date(); 
                String CustId="";
                try
                {
                    CustId=((String)session.getAttribute("custId")).trim();
                }
                catch(Exception e) {
                    CustId="FBD USER";
                }
                String si_Dvsn="";
                String si_Trml="";
                String si_Name="";
                String si_Desg="";
                String si_GstIn="";
                String si_Mobl="";
                String si_ContNo="";
                String si_Email="";
                String si_Addr="";
                String si_PAN="";
                String si_TAN="";
                String si_ApltType="";
                String si_CmdtList="";
                String si_CmdtCont="";
                String si_CmdtDtls="";
                String si_RfrnId="";
                String si_FileRfrnId="";
                String si_FileMode="";
                int filecntr=0;
                logger.info(":strCustId:"+CustId);
		
		if(isMultiPart)
		{
			logger.info("Multipart is true");	
			List<FileItem> fields;		
			try{
			fields = upload.parseRequest(req);
			}
			catch(Exception e)
			{
				logger.info("Exception in getting fields");
				return;
			}
			// out.println("Number of fields: " + fields.size() + "<br/><br/>");
			Iterator<FileItem> it = fields.iterator();
			Iterator<FileItem> it1 = fields.iterator();
			FileItem fileItem1 = null;
                        int FILE_COUNT=7;
                        fi=new InputStream[FILE_COUNT];
                        strFileName=new String[FILE_COUNT];
                        li_filesize=new long[FILE_COUNT];
                        for(int i=0;i<FILE_COUNT;i++) 
                        {
                            fi[i]=null;
                            strFileName[i]="";
                            li_filesize[i]=0;
                        }
			while (it.hasNext()) {
				// out.println("<tr>");
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				logger.info("file:-"+fileItem.getFieldName().toUpperCase());
				if(fileItem.getFieldName().equals("file"+(filecntr+1)))
				{
					fi[filecntr]=fileItem.getInputStream();
					strFileName[filecntr]=fileItem.getName();
					li_filesize[filecntr]=fileItem.getSize();
                                        filecntr++;
				}
				if(fileItem.getFieldName().equals("planfile"))
				{
					planFile=fileItem.getInputStream();
					strPlanFileName=fileItem.getName();
					li_planfilesize=fileItem.getSize();
				}
				if (isFormField) {				
					logger.info("formfield:-"+fileItem.getFieldName().toUpperCase());
					if(fileItem.getFieldName().equals("Optn"))
					{
						si_Optn=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("selDvsn"))
					{
						si_Dvsn=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("selSttn"))
					{
						si_Trml=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Name"))
					{
						si_Name=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Desg"))
					{
						si_Desg=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("GstIn"))
					{
						si_GstIn=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Mobl"))
					{
						si_Mobl=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("ContNo"))
					{
						si_ContNo=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Email"))
					{
						si_Email=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Addr"))
					{
						si_Addr=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PAN"))
					{
						si_PAN=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("TAN"))
					{
						si_TAN=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("ApltType"))
					{
						si_ApltType=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("CmdtList"))
					{
						si_CmdtList=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("CmdtCont"))
					{
						si_CmdtCont=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("CmdtDtls"))
					{
						si_CmdtDtls=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("RfrnId"))
					{
						si_RfrnId=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("FileRfrnId"))
					{
						si_FileRfrnId=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("FileMode"))
					{
						si_FileMode=fileItem.getString().toUpperCase();
					}
				}
			}
		}
		else
		{
			si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		}
                logger.info("si_Optn:"+si_Optn);
                logger.info("si_Dvsn:"+si_Dvsn);
                logger.info("si_Trml:"+si_Trml);
                logger.info("si_Name:"+si_Name);
                logger.info("si_Desg:"+si_Desg);
                logger.info("si_GstIn:"+si_GstIn);
                logger.info("si_Mobl:"+si_Mobl);
                logger.info("si_ContNo:"+si_ContNo);
                logger.info("si_Email:"+si_Email);
                logger.info("si_Addr:"+si_Addr);
                logger.info("si_PAN:"+si_PAN);
                logger.info("si_TAN:"+si_TAN);
                logger.info("si_ApltType:"+si_ApltType);
                logger.info("si_CmdtList:"+si_CmdtList);
                logger.info("si_CmdtCont:"+si_CmdtCont);
                logger.info("si_CmdtDtls:"+si_CmdtDtls);
                logger.info("si_RfrnId:"+si_RfrnId);
                logger.info("si_FileRfrnId:"+si_FileRfrnId);
                logger.info("si_FileMode:"+si_FileMode);
		/*req.setCharacterEncoding("UTF-8");*/
		res.setHeader("Cache-Control", "no-cache");
                String strInpt="";	
                if(si_Optn.equals("DISCARD_APPL"))
                {
                    logger.info("Entered in Discard Draft and Start Application Afresh");
                    String strData="";                        
                    try
                    {
                        session.removeAttribute("Dvsn");
                        session.removeAttribute("Trml");
                        session.removeAttribute("Name");
                        session.removeAttribute("Desg");
                        session.removeAttribute("ApltMobl");
                        session.removeAttribute("ApltEmail");
                        session.removeAttribute("ApltContNo");
                        session.removeAttribute("ApltAddr");
                        session.removeAttribute("PAN");
                        session.removeAttribute("TAN");
                        session.removeAttribute("GstIn");
                        session.removeAttribute("ApltType");
                        session.removeAttribute("FileName1");
                        session.removeAttribute("FileName2");
                        session.removeAttribute("FileName3");
                        session.removeAttribute("FileName4");
                        session.removeAttribute("FileName5");
                        session.removeAttribute("FileName6");
                        session.removeAttribute("FileName7");
                        session.removeAttribute("PlanFileName");
                        session.removeAttribute("CmdtList");
                        session.removeAttribute("CmdtCont");
                        session.removeAttribute("CmdtDtls");
                        session.removeAttribute("FileRfrnId");
                        session.removeAttribute("RfrnId");
                        strData+="{\"Stts\":\"S\",\"ErorMesg\":\"\"}";
                    }
                    catch(Exception e) {
                        strData+="{\"Stts\":\"F\",\"ErorMesg\":\""+e.getMessage()+"\"}";    
                    }
                    logger.info(strData);
                    res.getWriter().write(strData);
                }
            
            if(si_Optn.equals("DELETE_DRAFT"))
            {
                logger.info("Entered in Delete Draft from Database");
                String strData="";                        
                try
                {
                    si_RfrnId = req.getParameter("RfrnId").toUpperCase().trim();
                }
                catch(Exception e) {
                    strData+="{\"Stts\":\"F\",\"ErorMesg\":\"Not a valid Application, please try again\"}";
                logger.info(strData);
                res.getWriter().write(strData);
                return;
                }
                try
                {
                    GCTSrvcCall obj=new GCTSrvcCall(CustId,si_ClntId);
                    if((si_RfrnId==null)) {
                        si_RfrnId=""; 
                        logger.info(strData);
                        res.getWriter().write(strData);
                        return;
                    }
                    
                    logger.info("si_RfrnId:"+si_RfrnId+",  si_FileRfrnId:"+si_FileRfrnId);
                    
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
		if(si_Optn.equals("SAVE_DRAFT"))
		{
                    logger.info("Case: Save Draft");
                    factory = new DiskFileItemFactory();
                    ServletFileUpload sfu  	= new ServletFileUpload(factory);
		    isMultiPart		= ServletFileUpload.isMultipartContent(req);
		    logger.info("SAVE_DRAFT isMultiPart:-"+isMultiPart);
                    String strFileId="";
                    si_FileRfrnId="GCTF"+formatter.format(date)+ (int)(Math.random()*(999-100+1)+100);
                
                    String strCrntTime=formatter1.format(date);
		    logger.info("FileRfrnId #"+si_FileRfrnId+"#, Current Time:#"+strCrntTime+"#");
                    logger.info("Setting session parameters");
                    session.setAttribute("Dvsn",si_Dvsn);
                    session.setAttribute("Trml",si_Trml);
                    session.setAttribute("Name",si_Name);
                    session.setAttribute("Desg",si_Desg);
                    session.setAttribute("ApltMobl",si_Mobl);
                    session.setAttribute("ApltEmail",si_Email);
                    session.setAttribute("ApltContNo",si_ContNo);
                    session.setAttribute("ApltAddr",si_Addr);
                    session.setAttribute("PAN",si_PAN);
                    session.setAttribute("TAN",si_TAN);
                    session.setAttribute("GstIn",si_GstIn);
                    session.setAttribute("ApltType",si_ApltType);
                    session.setAttribute("FileName1",strFileName[0]);
                    session.setAttribute("FileName2",strFileName[1]);
                    session.setAttribute("FileName3",strFileName[2]);
                    session.setAttribute("FileName4",strFileName[3]);
                    session.setAttribute("FileName5",strFileName[4]);
                    session.setAttribute("FileName6",strFileName[5]);
                    session.setAttribute("FileName7",strFileName[6]);
                    session.setAttribute("PlanFileName",strPlanFileName);
                    session.setAttribute("CmdtList",si_CmdtList);
                    session.setAttribute("CmdtCont",si_CmdtCont);
                    session.setAttribute("CmdtDtls",si_CmdtDtls);
                    session.setAttribute("FileRfrnId",si_FileRfrnId);
                    session.setAttribute("RfrnId",si_RfrnId);
                    logger.info("Finished Setting session parameters");
                    if(isMultiPart)
                    {
                        try
                        {	
                                logger.info("Getting DBConnection Object");
                    
                                GG_DBConnection db = new GG_DBConnection();
                                logger.info("Got db object");
                                connection=db.funcGetConn("RMSFIELD");

                                logger.info("Got RMSFIELD Connection");
                                String sqlstmt="";
                                /*
                                if(!si_FileMode.equals("P"))
                                {	
                                    if(si_FileMode.equals("C")) {
                                        logger.info("Delete Statement from REC_GCTAPPLFILE");
                                        sqlstmt="DELETE FROM RMS3T.REC_GCTAPPLFILE WHERE RAVRFRNID=?";
                                        PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                                        ps.setString(1, si_FileRfrnId);                                    
                                        ps.executeUpdate();
                                        logger.info("File Reference Deleted");
                                        ps.close();
                                    }
                                */
                                    logger.info("Insert Statement for REC_GCTAPPLFILE");
                                    sqlstmt="INSERT INTO RMS3T.REC_GCTAPPLFILE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?)";
                                    PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                                    ps.setString(1, si_FileRfrnId);
                                    ps.setString(2, si_ApltType);    
                                    if(strPlanFileName.equals("")) {
                                        ps.setNull(3, Types.NULL);
                                        ps.setNull(4,Types.NULL);
                                    }
                                    else
                                    {
                                        ps.setString(3, strPlanFileName);
                                        ps.setBinaryStream(4,planFile,(long) li_planfilesize);
                                    }
                                   if(!strFileName[0].equals(""))
                                   {
                                        ps.setString(5, strFileName[0]);
                                        ps.setBinaryStream(6,fi[0],(long) li_filesize[0]);                        
                                   }
                                   else {
                                        ps.setNull(5, Types.NULL);
                                        ps.setNull(6,Types.NULL);
                                   }
                                   if(!strFileName[1].equals(""))
                                   {
                                        ps.setString(7, strFileName[1]);
                                        ps.setBinaryStream(8,fi[1],(long) li_filesize[1]);                        
                                   }
                                   else {
                                        ps.setNull(7, Types.NULL);
                                        ps.setNull(8,Types.NULL);
                                   }
                                   if(!strFileName[2].equals(""))
                                   {
                                        ps.setString(9, strFileName[2]);
                                        ps.setBinaryStream(10,fi[2],(long) li_filesize[2]);                        
                                   }
                                   else {
                                        ps.setNull(9, Types.NULL);
                                        ps.setNull(10,Types.NULL);
                                   }
                                   if(!strFileName[3].equals(""))
                                   {
                                        ps.setString(11, strFileName[3]);
                                        ps.setBinaryStream(12,fi[3],(long) li_filesize[3]);                        
                                   }
                                   else {
                                        ps.setNull(11, Types.NULL);
                                        ps.setNull(12,Types.NULL);
                                   }
                                   if(!strFileName[4].equals(""))
                                   {
                                        ps.setString(13, strFileName[4]);
                                        ps.setBinaryStream(14,fi[4],(long) li_filesize[4]);                        
                                   }
                                   else {
                                        ps.setNull(13, Types.NULL);
                                        ps.setNull(14,Types.NULL);
                                   }
                                   if(!strFileName[5].equals(""))
                                   {
                                        ps.setString(15, strFileName[5]);
                                        ps.setBinaryStream(16,fi[5],(long) li_filesize[5]);                        
                                   }
                                   else {
                                        ps.setNull(15, Types.NULL);
                                        ps.setNull(16,Types.NULL);
                                   }
                                   if(!strFileName[6].equals(""))
                                   {
                                        ps.setString(17, strFileName[6]);
                                        ps.setBinaryStream(18,fi[6],(long) li_filesize[6]);                        
                                   }
                                   else {
                                        ps.setNull(17, Types.NULL);
                                        ps.setNull(18,Types.NULL);
                                   }
                                       
                                    ps.setString(19, strCrntTime);
                                    ps.setString(20, CustId);
                                    ps.setString(21, "D");
                                    
                                    ps.executeUpdate();
                                    logger.info("File Uploaded");
                                    ps.close();
                                /*}*/
                                /*Calling Tuxedo Service*/
                                logger.info("Calling GCTSrvcCall for saving rest of the fields");
                                GCTSrvcCall obj=new GCTSrvcCall(CustId,si_ClntId);
                                if((si_RfrnId==null)) {
                                    si_RfrnId="";                                           
                                }
                                if((si_FileRfrnId==null)) {
                                    si_FileRfrnId="";                                           
                                }
                                 logger.info("si_RfrnId:"+si_RfrnId+",  si_FileRfrnId:"+si_FileRfrnId);
				String strRfrnId="";
                                String strRetVal="";
                                strRetVal=obj.saveGCTAppl("D", si_RfrnId, si_Dvsn, si_Trml, si_Name, si_Desg, si_Mobl, si_ContNo, si_Email, si_Addr, si_PAN, si_TAN, si_GstIn, si_ApltType, si_FileRfrnId, si_CmdtCont, si_CmdtDtls);
                                logger.info("Service Call Status:"+obj.getCallStts()+", Return Value:"+strRetVal);
                                /*Finished calling Tuxedo Service*/
                                if((obj.getCallStts()).equals(("S")))
                                {
                                    strRfrnId=strRetVal;
                                    logger.info("setting Reference Id: "+strRfrnId);
                                    connection.commit();
                                    session.setAttribute("RfrnId", strRfrnId);
                                    req.setAttribute("DraftSaveStts","S");
                                }
                                else {
                                    strRfrnId="";
                                    connection.rollback();
                                    session.setAttribute("RfrnId", "");
                                    req.setAttribute("DraftSaveStts","F");
                                }
                                connection.close();
                                req.setAttribute("strMesgSave","File Uploaded Successfully");				
                   	        logger.info("File Uploaded Successfully");
				}
				catch(Exception e)
				{
					logger.info("Exception in uploading the file:"+e.getMessage());
					System.out.println("Exception in uploading the file:"+e.getMessage());
					session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
					session.setAttribute("Stts","F");
					res.sendRedirect("/RailSAHAY/pages/AppDashboard.jsp");
				}
			}
			logger.info("3c");
                        
                        session.setAttribute("ErorMesg","");
                        session.setAttribute("Stts","S");
			try
			{
			    res.sendRedirect("/RailSAHAY/pages/AppDashboard.jsp");
			}
			catch(Exception e)
			{
				return;
			}
			return;
		}
        }
}