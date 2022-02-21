package servlet.AppData;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.AcqnPlanSrvc;
import java.util.Scanner;  
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.List;
import org.apache.log4j.Logger;

import util.logger.FoisLogger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.InputStreamReader;

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

import java.util.ArrayList;
import java.util.Date;  

public class AcqnPlanResUpload extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(AcqnPlanResUpload.class.getName()); 
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
		logger.info("Calling AcqnPlanResUpload.java...");
		HttpSession session=req.getSession();
		boolean isMultiPart= ServletFileUpload.isMultipartContent(req);
		logger.info("Received isMultipart");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String si_Optn="";
		String si_Rmrk="";
		FileItem file = null;
		String strFileName="";
		long li_filesize=0;
		InputStream fi=null;
                byte[] buffer=null;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
                Date date = new Date(); 
		String strZone="";
		String strDvsn="";
		String strLognFlag=((String)session.getAttribute("LognLocnFlag")).trim();
		String strLognLocn=((String)session.getAttribute("LognLocn")).trim();
                logger.info(":strLognFlag:"+strLognFlag+":strLognLocn:"+strLognLocn);
		if(strLognFlag.equals("Z"))
			strZone=strLognLocn;
		if(strLognFlag.equals("D"))
                {
			strDvsn=strLognLocn;
                        strZone=(String)session.getAttribute("CrntZone");
                }
		
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
			while (it.hasNext()) {
				// out.println("<tr>");
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				logger.info("formfeild:-"+fileItem.getFieldName().toUpperCase());
				if(fileItem.getFieldName().equals("inptfile"))
				{
					fi=fileItem.getInputStream();
                                        buffer=IOUtils.toByteArray(fi);
					strFileName=fileItem.getName();
					li_filesize=fileItem.getSize();
				}
				if (isFormField) {				
					logger.info("formfeild:-"+fileItem.getFieldName().toUpperCase());
					if(fileItem.getFieldName().equals("Optn"))
					{
						si_Optn=fileItem.getString().toUpperCase();
					}
				}
			}
		}
		else
		{
			si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		}

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
		
		if(si_Optn.equals("UPLOAD_RES_FILE"))
		{
                        logger.info("Case: Upload Resource File");
                        factory = new DiskFileItemFactory();
                        ServletFileUpload sfu  	= new ServletFileUpload(factory);
			isMultiPart		= ServletFileUpload.isMultipartContent(req);
			logger.info("isMultiPart:-"+isMultiPart);
			
			String strFileId="";
                        if(strZone.equals(""))
                                strFileId="IR_"+formatter.format(date);
                        else
                                strFileId=strZone+"_"+formatter.format(date);
			String strCrntTime=formatter1.format(date);
			logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
			if(isMultiPart)
	        {
                logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
                BufferedReader br = null;
                try
                {	
                        logger.info("Getting DBConnection Object");
            
                        GG_DBConnection db = new GG_DBConnection();
                        connection=db.funcGetConn("RMSFIELD");

                        String sqlstmt="";
                                
                        logger.info("Insert Statement for REC_BDURESFILE");
                        sqlstmt="INSERT INTO RMS3T.REC_BDURESFILE VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?,?,?,?)";
                        PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                        ps.setString(1, strZone);
                        ps.setString(2, strDvsn);
                        ps.setString(3, strFileId);
                        ps.setString(4, strCrntTime);
                        ps.setString(5, strFileName);
                        ps.setBinaryStream(6, fi, (long) li_filesize);
                        ps.setString(7, "Y");
                        ps.setString(8, si_UserID);
                        ps.setString(9, si_ClntID);		        	
                        ps.executeUpdate();
                        logger.info("File Uploaded");
                        ps.close();
                    
                        logger.info("Reading data from the uploaded file");
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                             
                                    //byte[] buffer1 = IOUtils.toByteArray(fi);
                                    int len;
                                        // write bytes from the buffer into the output stream
                    logger.info("byearray created:"+buffer.length);
                                        os.write(buffer, 0, buffer.length);
                            try (FileOutputStream fos = new FileOutputStream("resFile.csv")) {
                                        fos.write(buffer);
                                        fos.close();
                                    }
                        logger.info("scanner now");
                            FileInputStream fs = new FileInputStream("resFile.csv");  
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(fs));
                                   String line = "";
                                   int Counter1 = 0;
                                  
                                   logger.info("reading line by line");
                    int cntr=0;
                                 while(( line = br1.readLine()) != null) //getting inside file
                              {                                                                 
                                  String[] split = line.split("#"); 
                                  sqlstmt="INSERT INTO RMS3T.REC_BDURESOURCE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
                                  ps = connection.prepareStatement(sqlstmt);
                                  cntr++;
                                  ps.setString(1, strFileId+cntr);
                                  ps.setString(2, strZone);
                                  ps.setString(3, strDvsn);
                                  ps.setString(4, split[0]); /*Commodity*/
                                  ps.setString(5, split[1]); /*Industry*/
                                  ps.setString(6, split[2]); /*Resource Name*/
                                  ps.setString(7, split[3]); /*Mobile*/
                                  ps.setString(8, split[4]); /*Landline*/
                                  ps.setString(9, split[5]); /*Email*/
                                  ps.setString(10, split[6]); /*Address*/
                                  ps.setString(11, si_UserID);
                                  ps.setString(12, si_ClntID);    
                                  ps.setString(13, strCrntTime);          
                                  ps.executeUpdate();
                                  logger.info("Resource Added");
                                  ps.close();                                
                              }
                    br1.close();
                    fs.close();
                    File file1=new File("resFile.csv");
                    file1.delete();
                            //targetFile.delete();
                        logger.info("Finished reading data from the file");  
                        connection.commit();
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
                                session.setAttribute("Zone",strZone);
                                res.sendRedirect("/RailSAHAY/pages/IRBDU_Resource.jsp");
                        }
                }
                logger.info("3c");
                session.setAttribute("ErorMesg","");
                session.setAttribute("Stts","S");
                session.setAttribute("Zone",strZone);
                
                try
                {

                    res.sendRedirect("/RailSAHAY/pages/IRBDU_Resource.jsp");
                }
                catch(Exception e)
                {
                        return;
                }
                return;
        }
    }
}