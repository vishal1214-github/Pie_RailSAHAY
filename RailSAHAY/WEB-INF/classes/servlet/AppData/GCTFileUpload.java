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


public class GCTFileUpload extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GCTFileUpload.class.getName()); 
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
		logger.info("Calling GCTFileUpload.java...");
		HttpSession session=req.getSession();
		boolean isMultiPart		= ServletFileUpload.isMultipartContent(req);
		logger.info("Received isMultipart");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String si_Optn="";
		String si_Rmrk="";
                String si_FileRfrnId="";
                String si_ApltCtgr="";
                String si_FileIndx="0";
                String si_DownloadFileIndx="";
                int fileIndx=0;
                int downloadIndx=0;
		FileItem file = null;
		String strFileName[]=new String[10];
		long li_filesize[]=new long[10];
		InputStream fi[]=new InputStream[10];
                InputStream planFile=null;
                String strPlanFileName="";
                long li_planfilesize=0;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
                Date date = new Date(); 
		/*String CustId=((String)session.getAttribute("custId")).trim();*/
                String CustId="FBD USER";
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
			while (it.hasNext()) {
				// out.println("<tr>");
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
                                logger.info("Input Received:"+fileItem.getFieldName());
                                
				if (isFormField) {				
					logger.info("formfield:-"+fileItem.getFieldName().toUpperCase());
					if(fileItem.getFieldName().equals("Optn"))
					{
						si_Optn=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("FileRfrnId"))
					{
						si_FileRfrnId=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("FileIndx"))
					{
						si_FileIndx=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Indx"))
					{
						si_DownloadFileIndx=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("ApltCtgr"))
					{
						si_ApltCtgr=fileItem.getString().toUpperCase();
					}
				}
			}
                    logger.info("Input fields already received");
                    logger.info("si_Optn:"+si_Optn);
                    fileIndx=Integer.parseInt(si_FileIndx);
		    it = fields.iterator();
		    while (it.hasNext()) {
		            // out.println("<tr>");
		            FileItem fileItem = it.next();
		            boolean isFormField = fileItem.isFormField();
		            logger.info("Input Received:"+fileItem.getFieldName());
		            int cntr=0;
		            if(fileItem.getFieldName().equals("file"+fileIndx))
		            {
		                    fi[fileIndx]=fileItem.getInputStream();
		                    strFileName[fileIndx]=fileItem.getName();
		                    li_filesize[fileIndx]=fileItem.getSize();
		                    cntr++;
		            }
                    }
                    logger.info("Files received");
		}
		else
		{
			si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		}
                logger.info("si_Optn:"+si_Optn);
                logger.info("si_FileRfrnId:"+si_FileRfrnId);
                logger.info("si_ApltCtgr:"+si_ApltCtgr);
                logger.info("si_FileIndx:"+si_FileIndx);
                logger.info("si_DownloadFileIndx:"+si_DownloadFileIndx);
		/*req.setCharacterEncoding("UTF-8");*/
		res.setHeader("Cache-Control", "no-cache");
                String strInpt="";	
		
		if(si_Optn.equals("UPLOAD_FILE"))
		{
                    logger.info("Case: Save Draft");
                    factory = new DiskFileItemFactory();
                    ServletFileUpload sfu  	= new ServletFileUpload(factory);
		    isMultiPart		= ServletFileUpload.isMultipartContent(req);
		    logger.info("SAVE_DRAFT isMultiPart:-"+isMultiPart);
                    String strFileId="";
                    if(!si_FileRfrnId.equals(""))
                        strFileId=si_FileRfrnId;  
                    else
                        strFileId="GCTF"+si_ApltCtgr+formatter.format(date);  
                    String strCrntTime=formatter1.format(date);
                    
		    logger.info("FileRfrnId #"+si_FileRfrnId+"#, FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
                    logger.info("si_FileIndx:"+si_FileIndx+" strFileName:"+strFileName[fileIndx]);
		    String strExstFlag="N";
		    if(isMultiPart)
		    {
		        GG_DBConnection db=null;
		        try
		        {       
		                logger.info("Getting DBConnection Object");
		    
		                db = new GG_DBConnection();
		                logger.info("Got db object");
		                connection=db.funcGetConn("RMSFIELD");

		                logger.info("Got RMSFIELD Connection");
		        }
		        catch(Exception e) {
		            logger.info("Exception in getting connection:"+e.getMessage());
		            e.printStackTrace();
		            session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
		            session.setAttribute("Stts","F");
		            res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?Stts=F&ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+si_FileRfrnId+"&ErorMesg=File Upload Failed"+e.getMessage());
		        }
		        try
		        {
		                String sqlstmt="SELECT 'Y' flag FROM RMS3T.REC_GCTAPPLFILE WHERE RAVRFRNID='"+strFileId+"'";
		                logger.info("Statement to check for existing record:"+sqlstmt);
		                Statement stmt = connection.createStatement();
		                ResultSet rs = stmt.executeQuery(sqlstmt);
		                 while(rs.next())
		                 {
		                     strExstFlag= rs.getString("flag");
		                 }
		          }                    
		            catch (Exception e) 
		            {
		                logger.info("Exception in selecting data:"+e.getMessage());
		                e.printStackTrace();
		                session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
		                session.setAttribute("Stts","F");
		                res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?Stts=F&ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+si_FileRfrnId+"&ErorMesg=File Upload Failed"+e.getMessage());
		            } 
		        
		            if(strExstFlag.equals("Y")) {
		                logger.info("Case of Update");
		                try
		                {
		                 
                                    String sqlstmt="";   
		                    if(si_FileIndx.equals("0"))
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVPLANFILENAME=?, RABPLANFILE=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("1"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME1=?, RABFILE1=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("2"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME2=?, RABFILE2=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("3"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME3=?, RABFILE3=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("4"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME4=?, RABFILE4=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("5"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME5=?, RABFILE5=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("6"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME6=?, RABFILE6=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("7"))                                   
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME7=?, RABFILE7=? WHERE RAVRFRNID=?";
		                    
		                    PreparedStatement ps1 =  connection.prepareStatement(sqlstmt);
		                    ps1.setString(1, strFileName[fileIndx]);
		                    ps1.setBinaryStream(2,fi[fileIndx],(long) li_filesize[fileIndx]); 
		                    ps1.setString(3, strFileId);                                
		                    ps1.executeUpdate();
		                    logger.info("File Uploaded");
		                    ps1.close();                                
		                    connection.commit();
                                    logger.info("Connection committed");
		                    req.setAttribute("Stts","S");
		                    req.setAttribute("ErorMesg","");
		                }
		                catch(Exception e) 
		                {
		                    logger.info("Exception in updating data:"+e.getMessage());
		                    try
		                    {
		                        connection.rollback();
		                    }
		                    catch(Exception e1) {
		                        logger.info("Exception in rollback data:"+e.getMessage());
		                        e1.printStackTrace();
		                    }
		                    e.printStackTrace();
		                    req.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
		                    req.setAttribute("Stts","F");
		                    res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+si_FileRfrnId+"&Stts=F&ErorMesg=File Upload Failed"+e.getMessage());
		                }
		                try
		                {
		                    connection.close();
		                }
		                catch(Exception e) {
		                    logger.info("Exception in Closing connection:"+e.getMessage());
		                    e.printStackTrace();
		                }
		                
		                req.setAttribute("ErorMesg","");
		                req.setAttribute("Stts","S");
		                try
		                {
		                    res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+strFileId+"&Stts=S&ErorMesg=");
		                }
		                catch(Exception e)
		                {
		                        return;
		                }
                                return;
		            }                    
		            if(strExstFlag.equals("N")) {
		                logger.info("Case of Insert");
		                logger.info("Insert Statement for REC_GCTAPPLFILE");
		                try
		                {
		                    String sqlstmt="INSERT INTO RMS3T.REC_GCTAPPLFILE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?)";
		                    PreparedStatement ps =  connection.prepareStatement(sqlstmt);
		                    ps.setString(1, strFileId);
		                    ps.setString(2, si_ApltCtgr); 
		                    ps.setNull(3, Types.NULL);
		                    ps.setNull(4,Types.NULL);
		                    ps.setNull(5, Types.NULL);
		                    ps.setNull(6,Types.NULL);
		                    ps.setNull(7, Types.NULL);
		                    ps.setNull(8,Types.NULL);
		                    ps.setNull(9, Types.NULL);
		                    ps.setNull(10,Types.NULL);
		                    ps.setNull(11, Types.NULL);
		                    ps.setNull(12,Types.NULL);
		                    ps.setNull(13, Types.NULL);
		                    ps.setNull(14,Types.NULL);
		                    ps.setNull(15, Types.NULL);
		                    ps.setNull(16,Types.NULL);
		                    ps.setNull(17, Types.NULL);
		                    ps.setNull(18,Types.NULL);                                  
		                    String strCrntTime1=formatter1.format(date);
		                    ps.setString(19, strCrntTime1);
		                    ps.setString(20, CustId);
		                    ps.setString(21, "Y");
		                    
		                    ps.executeUpdate();
		                    logger.info("Blank Record Inserted");
		                    ps.close();
		                    
                                        if(si_FileIndx.equals("0"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVPLANFILENAME=?, RABPLANFILE=? WHERE RAVRFRNID=?";                                
                                        if(si_FileIndx.equals("1"))
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME1=?, RABFILE1=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("2"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME2=?, RABFILE2=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("3"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME3=?, RABFILE3=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("4"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME4=?, RABFILE4=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("5"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME5=?, RABFILE5=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("6"))                                    
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME6=?, RABFILE6=? WHERE RAVRFRNID=?";                                
		                        if(si_FileIndx.equals("7"))                                   
		                        sqlstmt="UPDATE RMS3T.REC_GCTAPPLFILE SET RAVFILENAME7=?, RABFILE7=? WHERE RAVRFRNID=?";
		                    
		                    PreparedStatement ps1 =  connection.prepareStatement(sqlstmt);
		                    ps1.setString(1, strFileName[fileIndx]);
		                    ps1.setBinaryStream(2,fi[fileIndx],(long) li_filesize[fileIndx]); 
		                    ps1.setString(3, strFileId);                                
		                    ps1.executeUpdate();
		                    logger.info("File Uploaded");
		                    ps1.close();                                
		                    connection.commit();
		                    req.setAttribute("Stts","S");
		                }
		                catch(Exception e) 
		                {
		                    logger.info("Exception in inserting/updating data:"+e.getMessage());
		                    try
		                    {
		                        connection.rollback();
		                    }
		                    catch(Exception e1) {
		                        logger.info("Exception in rollback data:"+e.getMessage());
		                        e1.printStackTrace();
		                    }
		                    e.printStackTrace();
		                    req.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
		                    req.setAttribute("Stts","F");
		                    res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+si_FileRfrnId+"&Stts=F&ErorMesg=File Upload Failed"+e.getMessage());
		                }
		                try
		                {
		                    connection.close();
		                }
		                catch(Exception e) {
		                    logger.info("Exception in Closing connection:"+e.getMessage());
		                    e.printStackTrace();
		                }
		            
		                req.setAttribute("ErorMesg","");
		                req.setAttribute("Stts","S");
		                try
		                {
		                    res.sendRedirect("/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+si_ApltCtgr+"&FileRfrnId="+strFileId+"&Stts=S&ErorMesg=");
		                }
		                catch(Exception e)
		                {
		                        return;
		                }
		                return;
		        }
                    }
                }
            
            if(si_Optn.equals("DOWNLOAD_FILE"))
            {
            logger.info("Case: Download Application File");
            logger.info("si_FileRfrnId:"+si_FileRfrnId);
            logger.info("si_DownloadFileIndx:"+si_DownloadFileIndx);
            try
            {
                    GG_DBConnection db = new GG_DBConnection();
                    connection=db.funcGetConn("RMSFIELD");           
                    logger.debug("Got Connection");
                    Statement statement = connection.createStatement();
                    logger.debug("Created Statement");
                    
                    String strQuery ="";
                    if(si_DownloadFileIndx.equals("0"))
                        strQuery="SELECT RAVPLANFILENAME,RABPLANFILE RAVFILE FROM REC_GCTAPPLFILE WHERE RAVRFRNID = '" + si_FileRfrnId+"'";
                    else
                        strQuery="SELECT RAVFILENAME"+si_DownloadFileIndx+",RABFILE"+si_DownloadFileIndx+" RAVFILE FROM REC_GCTAPPLFILE WHERE RAVRFRNID = '" + si_FileRfrnId+"'";
            
                    logger.debug("Query:"+strQuery);
            
                    ResultSet rs    =       statement.executeQuery(strQuery);
            
                    if(rs.next())
                    {
                        String strFileName1             = rs.getString(1).trim();
                        
                        res.setContentType( "application/octet-stream" );
                        res.addHeader( "Content-Disposition", "filename="+strFileName1);
        
                        Blob  b = rs.getBlob("RAVFILE");
                        res.setContentLength( (int) b.length());
                        InputStream is = b.getBinaryStream();
                        OutputStream os = res.getOutputStream();
                        byte buf[] = new byte[(int) b.length()];
                        is.read(buf);
                        os.write(buf);
                    }
                    else 
                    {
                            res.setContentType("text/html");
                        PrintWriter out = res.getWriter();
        
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>File</title>");
                        out.println("</head>");
                        out.println("<body bgcolor=\"white\">");
                        out.println("<h1>No File Exists.</h1>");
                        out.println("</body>");
                        out.println("</html>");                    
                    }
                logger.debug("Closing OS");
                rs.close();
                statement.close();
                connection.close();        
                logger.debug("Exiting successfully.");
            }
            catch (Exception e)
            {
                logger.fatal("Other Exception: " + e.toString());
                System.out.println("Other Exception: " + e.getMessage());
                e.printStackTrace();  
            }
            finally 
            {
                    try 
                    {
                            if (connection != null) 
                                    connection.close();
                    }
                    catch (SQLException e)
                    {
                            logger.fatal("Connection Closing Exception: " + e.toString());
                            e.printStackTrace();  
                    }        
            }
            }
        }
}