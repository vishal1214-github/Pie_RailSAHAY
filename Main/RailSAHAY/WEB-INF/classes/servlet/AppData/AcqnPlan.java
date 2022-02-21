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

public class AcqnPlan extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(AcqnPlan.class.getName()); 
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
		logger.info("Calling AcqnPlan.java...");
		HttpSession session=req.getSession();
		boolean isMultiPart		= ServletFileUpload.isMultipartContent(req);
		logger.info("Received isMultipart");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String si_Optn="";
		String si_Rmrk="";
		FileItem file = null;
		String strFileName="";
		long li_filesize=0;
		InputStream fi=null;
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
                Date date = new Date(); 
		String strZone="";
		String strDvsn="";
		String strLognFlag=((String)session.getAttribute("LognLocnFlag")).trim();
		String strLognLocn=((String)session.getAttribute("LognLocn")).trim();
                String si_DataZone="";
                String si_PropType="";
                String si_PropTypeInpt="";
                String si_OthrTypeDtls="";
                String si_DurnCtgr="";
                String si_PropId="";
                String si_PropId1="";
                String si_PropCmdt="";
                String si_PropLoadType="";
                logger.info(":strLognFlag:"+strLognFlag+":strLognLocn:"+strLognLocn);
		if(strLognFlag.equals("Z"))
			strZone=strLognLocn;
		if(strLognFlag.equals("D"))
			strDvsn=strLognLocn;
		
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
					strFileName=fileItem.getName();
					li_filesize=fileItem.getSize();
				}
				if (isFormField) {				
					logger.info("formfeild:-"+fileItem.getFieldName().toUpperCase());
					if(fileItem.getFieldName().equals("Optn"))
					{
						si_Optn=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("Rmrk"))
					{
						si_Rmrk=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("selZone"))
					{
						si_DataZone=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropType"))
					{
						si_PropType=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropTypeInpt"))
					{
						si_PropTypeInpt=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropCmdt"))
					{
						si_PropCmdt=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropLoadType"))
					{
						si_PropLoadType=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("OthrTypeDtls"))
					{
						si_OthrTypeDtls=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("DurnCtgr"))
					{
						si_DurnCtgr=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropId"))
					{
						si_PropId=fileItem.getString().toUpperCase();
					}
					if(fileItem.getFieldName().equals("PropId1"))
					{
						si_PropId1=fileItem.getString().toUpperCase();
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
		
		if(si_Optn.equals("DOWNLOAD_FILE"))
		{
            logger.info("Case: Download Acquisition Plan File");
	        String si_FileId  = req.getParameter("FileId").toUpperCase().trim();
            logger.info("si_FileId:"+si_FileId);
            try
    	    {
    			GG_DBConnection db = new GG_DBConnection();
		    	connection=db.funcGetConn("RMSFIELD");           
		    	logger.debug("Got Connection");
    	        Statement statement = connection.createStatement();
                logger.debug("Created Statement");
    	        String strQuery	= "SELECT RAVFILE,RAVFILENAME RAVFILENAME FROM REC_BDUAQSNPLAN WHERE RAVFILEID = '" + si_FileId +"' AND RACVALDFLAG = 'Y' ";
                
                logger.debug("Query:"+strQuery);
                
                ResultSet rs 	= 	statement.executeQuery(strQuery);
                
                if(rs.next())
                {
                    String strFileName1             = rs.getString(2).trim();
                    
                    res.setContentType( "application/octet-stream" );
                    res.addHeader( "Content-Disposition", "filename="+strFileName1 );

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
		if(si_Optn.equals("UPLOAD_AQSN_PLAN"))
		{
            logger.info("Case: Upload Acquisition Plan File");
            factory = new DiskFileItemFactory();
            ServletFileUpload sfu  	= new ServletFileUpload(factory);
			isMultiPart		= ServletFileUpload.isMultipartContent(req);
			logger.info("3:isMultiPart:-"+isMultiPart);
			logger.info("Request raised by Zone #"+strZone+"#, Data Zone #"+si_DataZone+"#, UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
			
			String strFileId="";
                        if(si_DataZone.equals(""))
                                strFileId="IR_"+formatter.format(date);
                        else
                                strFileId=si_DataZone+"_"+formatter.format(date);
			String strCrntTime=formatter1.format(date);
			logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
			if(isMultiPart)
	        {
                logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
                        try
                        {	
                                logger.info("Getting DBConnection Object");
                    
                                GG_DBConnection db = new GG_DBConnection();
                                logger.info("6");
                                connection=db.funcGetConn("RMSFIELD");

                                logger.info("6a");
                                String sqlstmt="";
			   		
		        	logger.info("Insert Statement for REC_BDUAQSNPLAN");
		        	sqlstmt="INSERT INTO RMS3T.REC_BDUAQSNPLAN VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?,?,?,?,?)";
		        	PreparedStatement ps =  connection.prepareStatement(sqlstmt);
		        	ps.setString(1, si_DataZone);
		        	ps.setString(2, strDvsn);
		        	ps.setString(3, strFileId);
		        	ps.setString(4, strCrntTime);
		        	ps.setString(5, strFileName);
		        	//ps.setBinaryStream(3, null,1);
		        	ps.setBinaryStream(6, fi, (long) li_filesize);
		        	ps.setString(7, "Y");
		        	ps.setString(8, si_Rmrk);
		        	ps.setString(9, si_UserID);
		        	ps.setString(10, si_ClntID);
		        	
		        	ps.executeUpdate();
					logger.info("File Uploaded");
					ps.close();
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
					session.setAttribute("Zone",si_DataZone);
					res.sendRedirect("/RailSAHAY/pages/IRBDUUploadPlan.jsp");
				}
			}
			logger.info("3c");
                        session.setAttribute("ErorMesg","");
                        session.setAttribute("Stts","S");
                        session.setAttribute("Zone",si_DataZone);
			
			try
			{

			    res.sendRedirect("/RailSAHAY/pages/IRBDUUploadPlan.jsp");
			}
			catch(Exception e)
			{
				return;
			}
			return;
		}
		if(si_Optn.equals("DELETE_FILE"))
		{
                    String strData="";
                    logger.info("Case: Delete File");
                    String si_FileId			= (String) req.getParameter("FileId").trim().toUpperCase();
                    AcqnPlanSrvc obj	=	new AcqnPlanSrvc(si_UserID, si_ClntID);
                    logger.info("Working for RqstId:"+si_FileId);
                    String strRetVal="";
            try
            {
                strRetVal=obj.delFile(si_FileId);
                logger.info("Delete File Status:"+strRetVal);
                if(strRetVal.equals("SUCCESS"))
                {
                    strData="{\"Stts\":\"SUCCESS\", \"ErorMesg\":\"\"}";
                }
                else {
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

	    if(si_Optn.equals("UPLOAD_PROPOSAL"))
	    {
                logger.info("Case: Upload Business Proposal File");
                factory = new DiskFileItemFactory();
                ServletFileUpload sfu       = new ServletFileUpload(factory);
                isMultiPart             = ServletFileUpload.isMultipartContent(req);
                logger.info("3:isMultiPart:-"+isMultiPart);
                String strCrntZone="";
                String strCrntDvsn="";
                if(strLognFlag.equals("Z"))
                        strCrntZone=strLognLocn;
                if(strLognFlag.equals("D"))
                {
                       strCrntDvsn=strLognLocn;
                       strCrntZone=(String)session.getAttribute("CrntZone");   
                }
                logger.info("Request raised by Zone #"+strCrntZone+"#, Data Zone #"+strCrntZone+"#, Data Division #"+strCrntDvsn+",  UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
                        
                String strFileId=strCrntZone+"_"+formatter.format(date);
                String strCrntTime=formatter1.format(date);
                logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
                if(isMultiPart)
                {
                    logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
	            try
	            {       
	                    logger.info("Getting DBConnection Object");
	        
	                    GG_DBConnection db = new GG_DBConnection();
	                    logger.info("6");
	                    connection=db.funcGetConn("RMSFIELD");

	                    logger.info("6a");
	                    String sqlstmt="";
	                    logger.info("Insert Statement for REC_BDUPROPOSAL");
	                    sqlstmt="INSERT INTO RMS3T.REC_BDUPROPOSAL VALUES (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
	                    PreparedStatement ps =  connection.prepareStatement(sqlstmt);
	                    ps.setString(1, strFileId);
                            ps.setString(2, strCrntZone);
                            ps.setString(3, strCrntDvsn);
	                    ps.setBinaryStream(4, fi, (long) li_filesize);
	                    ps.setString(5, strFileName);
	                    ps.setString(6, si_PropType);
	                    ps.setString(7, si_OthrTypeDtls);
	                    ps.setString(8, si_PropCmdt);
	                    ps.setString(9, si_PropLoadType);
	                    ps.setString(10, si_Rmrk);
	                    ps.setString(11, si_UserID);
	                    ps.setString(12, si_ClntID);
	                    ps.setString(13, strCrntTime);
	                    
	                    ps.executeUpdate();
                            logger.info("File Uploaded");
                            ps.close();
                            connection.commit();
                            connection.close();
                            req.setAttribute("strMesgSave","File Uploaded Successfully");                           
                            logger.info("File Uploaded Successfully");
                    }
                    catch(Exception e)
                    {
                            logger.info("Exception while getting the file !");
                            System.out.println("Exception in uploading the file:"+e.getMessage());
                            session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
                            session.setAttribute("Stts","F");
                            session.setAttribute("Zone",si_DataZone);
                            res.sendRedirect("/RailSAHAY/pages/IRBDU_Proposal.jsp");
                    }
            }
            logger.info("3c");
            session.setAttribute("ErorMesg","");
            session.setAttribute("Stts","S");
            session.setAttribute("Zone",strCrntZone);
            session.setAttribute("Dvsn",strCrntDvsn);
            
            try
            {

                res.sendRedirect("/RailSAHAY/pages/IRBDU_Proposal.jsp");
            }
            catch(Exception e)
            {
                    return;
            }
            return;
    }
	    if(si_Optn.equals("UPLOAD_PROPOSAL_LIVE"))
	    {
	        logger.info("Case: Upload Business Proposal File Real-time basis");
	        factory = new DiskFileItemFactory();
	        ServletFileUpload sfu       = new ServletFileUpload(factory);
	        isMultiPart             = ServletFileUpload.isMultipartContent(req);
	        logger.info("3:isMultiPart:-"+isMultiPart);
	        logger.info("Request raised by Zone #"+strZone+"#, Data Zone #"+si_DataZone+"#, UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
	                
	        String strFileId=((String)session.getAttribute("LognLocn")).trim()+"_"+formatter.format(date);
	        String strCrntTime=formatter1.format(date);
	        logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
                String strCrntZone="";
                String strCrntDvsn="";
                if(strLognFlag.equals("Z"))
                        strCrntZone=strLognLocn;
                if(strLognFlag.equals("D"))
                {
                       strCrntDvsn=strLognLocn;
                       strCrntZone=(String)session.getAttribute("CrntZone");   
                }
                logger.info("Request raised by Zone #"+strCrntZone+"#, Data Zone #"+strCrntZone+"#, Data Division #"+strCrntDvsn+",  UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
                        
	        if(isMultiPart)
	        {
	            logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
	            try
	            {       
	                    logger.info("Getting DBConnection Object");
	        
	                    GG_DBConnection db = new GG_DBConnection();
	                    logger.info("6");
	                    connection=db.funcGetConn("RMSFIELD");

	                    logger.info("6a");
	                    String sqlstmt="";
	                    logger.info("Insert Statement for REC_BDUPROPOSAL");
                        /*
	                    sqlstmt="INSERT INTO RMS3T.REC_BDUPROPOSAL VALUES (?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
	                    PreparedStatement ps =  connection.prepareStatement(sqlstmt);
	                    ps.setString(1, strFileId);
	                    ps.setString(2, ((String)session.getAttribute("LognLocn")).trim());
	                    ps.setBinaryStream(3, fi, (long) li_filesize);
	                    ps.setString(4, strFileName);
	                    ps.setString(5, si_PropType);
	                    ps.setString(6, si_OthrTypeDtls);
	                    ps.setString(7, si_PropCmdt);
	                    ps.setString(8, si_PropLoadType);
	                    ps.setString(9, si_Rmrk);
	                    ps.setString(10, si_UserID);
	                    ps.setString(11, si_ClntID);
	                    ps.setString(12, strCrntTime);
                        */
	                sqlstmt="INSERT INTO RMS3T.REC_BDUPROPOSAL VALUES (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
	                PreparedStatement ps =  connection.prepareStatement(sqlstmt);
	                ps.setString(1, strFileId);
	                ps.setString(2, strCrntZone);
	                ps.setString(3, strCrntDvsn);
	                ps.setBinaryStream(4, fi, (long) li_filesize);
	                ps.setString(5, strFileName);
	                ps.setString(6, si_PropType);
	                ps.setString(7, si_OthrTypeDtls);
	                ps.setString(8, si_PropCmdt);
	                ps.setString(9, si_PropLoadType);
	                ps.setString(10, si_Rmrk);
	                ps.setString(11, si_UserID);
	                ps.setString(12, si_ClntID);
	                ps.setString(13, strCrntTime);
	                    
	                    ps.executeUpdate();
	                    logger.info("File Uploaded");
	                    ps.close();
	                    connection.commit();
	                    connection.close();
	                    req.setAttribute("strMesgSave","File Uploaded Successfully");                           
	                    logger.info("File Uploaded Successfully");
	            }
	            catch(Exception e)
	            {
	                    logger.info("Exception while getting the file !");
	                    System.out.println("Exception in uploading the file:"+e.getMessage());
	                    session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
	                    session.setAttribute("Stts","F");
	                    session.setAttribute("Zone",si_DataZone);
	                    res.sendRedirect("/RailSAHAY/pages/IRBDU_Proposal1.jsp?FileId="+strFileId);
	            }
	    }
	    logger.info("3c");
	    session.setAttribute("ErorMesg","");
	    session.setAttribute("Stts","S");
	    session.setAttribute("Zone",si_DataZone);
	    
	    try
	    {

	        res.sendRedirect("/RailSAHAY/pages/IRBDU_Proposal1.jsp?FileId="+strFileId);
	    }
	    catch(Exception e)
	    {
	            return;
	    }
	    return;
	    }

	    if(si_Optn.equals("LIST_PROPOSAL_FILES"))
	    {
	        logger.info("Case: Fetch Business Proposal Files List");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();                
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim();
	        String si_PropType1  = req.getParameter("PropType").toUpperCase().trim();
                logger.info("Inputs received: si_Zone-"+si_Zone+", si_Dvsn-"+si_Dvsn+", si_PropType1-"+si_PropType1);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
                    strSrvcData=obj.getProposalList(si_Zone,si_Dvsn,si_PropType1);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"FileName\":\""+strSrvcData[i][3]+"\",\"PropType\":\""+strSrvcData[i][4]+"\",\"PropDtls\":\""+strSrvcData[i][5]+"\",\"Cmdt\":\""+strSrvcData[i][6]+"\",\"LoadType\":\""+strSrvcData[i][7]+"\",\"Rmrk\":\""+strSrvcData[i][8]+"\",\"UserId\":\""+strSrvcData[i][9]+"\",\"ClntId\":\""+strSrvcData[i][10]+"\",\"UpdtTime\":\""+strSrvcData[i][11]+"\",\"RespFile\":\""+strSrvcData[i][12]+"\",\"RespRmrk\":\""+strSrvcData[i][13]+"\",\"RespUserId\":\""+strSrvcData[i][14]+"\",\"RespTime\":\""+strSrvcData[i][15]+"\"}";
	                else
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"Dvsn\":\""+strSrvcData[i][2]+"\",\"FileName\":\""+strSrvcData[i][3]+"\",\"PropType\":\""+strSrvcData[i][4]+"\",\"PropDtls\":\""+strSrvcData[i][5]+"\",\"Cmdt\":\""+strSrvcData[i][6]+"\",\"LoadType\":\""+strSrvcData[i][7]+"\",\"Rmrk\":\""+strSrvcData[i][8]+"\",\"UserId\":\""+strSrvcData[i][9]+"\",\"ClntId\":\""+strSrvcData[i][10]+"\",\"UpdtTime\":\""+strSrvcData[i][11]+"\",\"RespFile\":\""+strSrvcData[i][12]+"\",\"RespRmrk\":\""+strSrvcData[i][13]+"\",\"RespUserId\":\""+strSrvcData[i][14]+"\",\"RespTime\":\""+strSrvcData[i][15]+"\"},";
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
	    if(si_Optn.equals("DVSN_PERF"))
	    {
	        logger.info("Case: Divisional Performance");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();    
	        logger.info("Inputs received: si_Zone-"+si_Zone);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchDvsnPerf(si_Zone);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DvsnList\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\"}";
	                else
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\"},";
	            }
	            strData+="],\"DvsnPerf\":[";
	            strSrvcData=obj.getAddlData();
	            intDataLen=strSrvcData.length;
	            logger.info("DvsnPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {  
                        if(strSrvcData[i][3].equals("0") && strSrvcData[i][6].equals("0"))
                        {
                            strSrvcData[i][3]="";
                            strSrvcData[i][4]="";
                            strSrvcData[i][6]="";
                            strSrvcData[i][7]="";
                        }
	                if(i==(intDataLen-1))
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"Month\":\""+strSrvcData[i][1]+"\",\"TngeLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"TngePctg\":\""+strSrvcData[i][4]+"\",\"FrgtLY\":\""+strSrvcData[i][5]+"\",\"FrgtCY\":\""+strSrvcData[i][6]+"\",\"FrgtPctg\":\""+strSrvcData[i][7]+"\"}";
	                else
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"Month\":\""+strSrvcData[i][1]+"\",\"TngeLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"TngePctg\":\""+strSrvcData[i][4]+"\",\"FrgtLY\":\""+strSrvcData[i][5]+"\",\"FrgtCY\":\""+strSrvcData[i][6]+"\",\"FrgtPctg\":\""+strSrvcData[i][7]+"\"},";
	            }
	            strData+="],\"ZonlPerf\":[";
	            strSrvcData=obj.getAddlData1();
	            intDataLen=strSrvcData.length;
	            logger.info("ZonlPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"}";
	                else
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"},";
	            }
	            strData+="]";
	            strSrvcData=obj.getAddlData2();
	            intDataLen=strSrvcData.length;
	            logger.info("Zonal Yearly Statistics:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {
                        if(i==0)
                            strData+=",\"ZonlTnge\":\""+strSrvcData[i][0]+"\", \"ZonlFrgt\":\""+strSrvcData[i][1]+"\"";
                    }
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

	    if(si_Optn.equals("ZONL_PERF"))
	    {
	        logger.info("Case: Zonal Performance");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();    
	        logger.info("Inputs received: si_Zone-"+si_Zone);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchZonlPerf(si_Zone);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"ZonlPerf\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("ZonlPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"}";
	                else
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"},";;
	            }
	            strData+="],\"DvsnPerf\":[";
	            strSrvcData=obj.getAddlData();
	            intDataLen=strSrvcData.length;
	            logger.info("DvsnPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"CmdtPerf\":[";
	            strSrvcData=obj.getAddlData1();
	            intDataLen=strSrvcData.length;
	            logger.info("CmdtPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"CmdtShare\":[";
	            strSrvcData=obj.getAddlData2();
	            intDataLen=strSrvcData.length;
	            logger.info("CmdtShare Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"FrgtLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"FrgtLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"ZonlProj\":[";
	            strSrvcData=obj.getAddlData3();
	            intDataLen=strSrvcData.length;
	            logger.info("ZonlProj Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"FrgtCY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"TngeLY\":\""+strSrvcData[i][5]+"\"}";
	                else
	                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"FrgtCY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"TngeLY\":\""+strSrvcData[i][5]+"\"},";
	            }
	            strData+="],\"PropStat\":[";
	            strSrvcData=obj.getAddlData4();
	            intDataLen=strSrvcData.length;
	            logger.info("Proposal Statistics:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"RecvCont\":\""+strSrvcData[i][0]+"\",\"RespCont\":\""+strSrvcData[i][1]+"\",\"FrwdCont\":\""+strSrvcData[i][2]+"\"}";
	                else
	                strData+="{\"RecvCont\":\""+strSrvcData[i][0]+"\",\"RespCont\":\""+strSrvcData[i][1]+"\",\"FrwdCont\":\""+strSrvcData[i][2]+"\"},";
	            }
	            strData+="],";
	            String strStats[]=obj.getStats();
	            intDataLen=strStats.length;
	            logger.info("Basic Statistics:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="\"Stat"+(i+1)+"\":\""+strStats[i]+"\"";                        
	                else
	                strData+="\"Stat"+(i+1)+"\":\""+strStats[i]+"\",";
	            }
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


	    if(si_Optn.equals("DVSN_PERF_STATS"))
	    {
	        logger.info("Case: Divisional Performance Statistics");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim();    
	        logger.info("Inputs received: si_Dvsn-"+si_Dvsn);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchDvsnPerfStats(si_Dvsn);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DvsnPerf\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DvsnPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"}";
	                else
	                strData+="{\"Month\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"TngePctg\":\""+strSrvcData[i][3]+"\",\"FrgtLY\":\""+strSrvcData[i][4]+"\",\"FrgtCY\":\""+strSrvcData[i][5]+"\",\"FrgtPctg\":\""+strSrvcData[i][6]+"\"},";;
	            }
	            strData+="],\"DvsnPerf1\":[";
	            strSrvcData=obj.getAddlData();
	            intDataLen=strSrvcData.length;
	            logger.info("DvsnPerf Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"TngeCY\":\""+strSrvcData[i][2]+"\",\"FrgtLY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"CmdtShare\":[";
	            strSrvcData=obj.getAddlData1();
	            intDataLen=strSrvcData.length;
	            logger.info("CmdtShare Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"FrgtLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"TngeLY\":\""+strSrvcData[i][1]+"\",\"FrgtLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"FrgtCY\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],";
	            String strStats[]=obj.getStats();
	            intDataLen=strStats.length;
	            logger.info("Basic Statistics:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="\"Stat"+(i+1)+"\":\""+strStats[i]+"\"";                        
	                else
	                strData+="\"Stat"+(i+1)+"\":\""+strStats[i]+"\",";
	            }
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
	    if(si_Optn.equals("DOWNLOAD_PROPOSAL_FILE"))
	    {
	    logger.info("Case: Download Business Proposal File");
	    String si_FileId  = req.getParameter("FileId").toUpperCase().trim();
	    logger.info("si_FileId:"+si_FileId);  
	    try
	    {  
                logger.info("inside try11");
                GG_DBConnection db = new GG_DBConnection();
                logger.info("inside try21");
		con=db.funcGetConn("RMSFIELD");           
		logger.info("Got Connection");
    	        Statement statement = con.createStatement();
	        logger.info("Created Statement");
	    String strQuery = "SELECT RABFILE,RAVFILENAME RAVFILENAME FROM REC_BDUPROPOSAL WHERE RAVPROPID = '" + si_FileId +"'  ";
	    
	    logger.info("Query:"+strQuery);
	    
	    ResultSet rs    =       statement.executeQuery(strQuery);
	        logger.info("Got resultset");
	    if(rs.next())
	    {
	        String strFileName1             = rs.getString(2).trim();
	        
	        res.setContentType( "application/octet-stream" );
	        res.addHeader( "Content-Disposition", "filename="+strFileName1 );

	        Blob  b = rs.getBlob("RABFILE");
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
	        out.println("<title>IR.BDU Dashboard...Freight Business Development Portal</title>");
	        out.println("</head>");
	        out.println("<body bgcolor=\"white\">");
	        out.println("<h1 style='font-size:1rem;font-weight:600;color:#4d4d4d;'>Sorry! Proposal file does not exist in the system.</h1>");
	        out.println("</body>");
	        out.println("</html>");                    
	    }
	        logger.debug("Closing OS");
	        rs.close();
	        statement.close();
	        con.close();        

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
	                    if (con != null) 
	                            con.close();
	            }
	            catch (SQLException e)
	            {
	                    logger.fatal("Connection Closing Exception: " + e.toString());
	                    e.printStackTrace();  
	            }        
	    }
	    }

        if(si_Optn.equals("DOWNLOAD_TRFCPLAN_FILE"))
        {
        logger.info("Case: Download Traffic Plan File");
        String si_FileId  = req.getParameter("TrfcPlanFileId").toUpperCase().trim();
        logger.info("si_FileId:"+si_FileId);  
        try
        {  
            GG_DBConnection db = new GG_DBConnection();
            con=db.funcGetConn("RMSFIELD");  
            Statement statement = con.createStatement();
        String strQuery = "SELECT RABTRFCPLANFILE,RAVFILENAME RAVFILENAME FROM REC_BDUTRFCPLANFILE WHERE RAVFILEID = (SELECT RAVTRFCPLANID FROM REC_BDURAILCOEFBOST WHERE RAVROWID= '" + si_FileId +"') ";
        
        logger.info("Query:"+strQuery);
        
        ResultSet rs    =       statement.executeQuery(strQuery);
            logger.info("Got resultset");
        if(rs.next())
        {
            String strFileName1             = rs.getString(2).trim();
            
            res.setContentType( "application/octet-stream" );
            res.addHeader( "Content-Disposition", "filename="+strFileName1 );

            Blob  b = rs.getBlob("RABTRFCPLANFILE");
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
            out.println("<title>IR.BDU Dashboard...Freight Business Development Portal</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<h1 style='font-size:1rem;font-weight:600;color:#4d4d4d;'>Sorry! Traffic Plan File does not exist in the system.</h1>");
            out.println("</body>");
            out.println("</html>");                    
        }
            logger.debug("Closing OS");
            rs.close();
            statement.close();
            con.close();        

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
                        if (con != null) 
                                con.close();
                }
                catch (SQLException e)
                {
                        logger.fatal("Connection Closing Exception: " + e.toString());
                        e.printStackTrace();  
                }        
        }
        }
	    if(si_Optn.equals("DOWNLOAD_DECISION_FILE"))
	    {
	    logger.info("Case: Download Decision Letter File");
	    String si_FileId  = req.getParameter("PropId").toUpperCase().trim();
	    logger.info("si_FileId:"+si_FileId);
	    try
	    {
	            GG_DBConnection db = new GG_DBConnection();
	            connection=db.funcGetConn("RMSFIELD");           
	            logger.debug("Got Connection");
	    Statement statement = connection.createStatement();
	    logger.debug("Created Statement");
	    String strQuery = "SELECT RABDECNLETR,RAVFILENAME RAVFILENAME FROM REC_BDUBSNSDECISION WHERE RAVPROPID = '" + si_FileId +"'  ";
	    
	    logger.debug("Query:"+strQuery);
	    
	    ResultSet rs    =       statement.executeQuery(strQuery);
	    
	    if(rs.next())
	    {
	        String strFileName1             = rs.getString(2).trim();
	        
	        res.setContentType( "application/octet-stream" );
	        res.addHeader( "Content-Disposition", "filename="+strFileName1 );

	        Blob  b = rs.getBlob("RABDECNLETR");
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
	    if(si_Optn.equals("DELETE_PROPOSAL_FILE"))
	    {
	        String strData="";
	        logger.info("Case: Delete Proposal File");
	        si_PropId    = (String) req.getParameter("PropId").trim().toUpperCase();
	        AcqnPlanSrvc obj    =       new AcqnPlanSrvc(si_UserID, si_ClntID);
	        logger.info("Working for si_PropId:"+si_PropId);
	        String strRetVal="";
	    try
	    {
	    strRetVal=obj.delProposalFile(si_PropId);
	    logger.info("Delete File Status:"+strRetVal);
	    if(strRetVal.equals("SUCCESS"))
	    {
	        strData="{\"Stts\":\"SUCCESS\", \"ErorMesg\":\"\"}";
	    }
	    else {
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
    if(si_Optn.equals("DEL_ROW"))
	    {
	        String strData="";
	        logger.info("Case: Delete Plan Data");
	        String si_Ctgr  = (String) req.getParameter("Ctgr").trim().toUpperCase();
	        String si_RowId  = (String) req.getParameter("RowId").trim().toUpperCase();
	        AcqnPlanSrvc obj    =       new AcqnPlanSrvc(si_UserID, si_ClntID);
	        logger.info("Working for si_Ctgr:"+si_Ctgr+", RowId:"+si_RowId);
                
	        String strRetVal="";
                try
                {
                    strRetVal=obj.delPlanData(si_Ctgr,si_RowId);
                    logger.info("Delete Plan Data Status:"+strRetVal);
                    if(strRetVal.equals("SUCCESS"))
                    {
                        strData="{\"Stts\":\"S\", \"ErorMesg\":\"\"}";
                    }
                    else {
                        strData="{\"Stts\":\"F\", \"ErorMesg\":\""+strRetVal+"\"}";
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
	    if(si_Optn.equals("LIST_UPLOADED_FILES"))
	    {
	        logger.info("Case: Uploaded Files List");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();
	        String si_Date  = req.getParameter("Date").toUpperCase().trim();	        
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.getUploadedFiles(si_Zone, si_Date);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"FileId\":\""+strSrvcData[i][2]+"\",\"FileName\":\""+strSrvcData[i][3]+"\",\"UpldTime\":\""+strSrvcData[i][4]+"\",\"Rmrk\":\""+strSrvcData[i][5]+"\",\"UserId\":\""+strSrvcData[i][6]+"\"}";
	                else
	                strData+="{\"Zone\":\""+strSrvcData[i][0]+"\",\"Dvsn\":\""+strSrvcData[i][1]+"\",\"FileId\":\""+strSrvcData[i][2]+"\",\"FileName\":\""+strSrvcData[i][3]+"\",\"UpldTime\":\""+strSrvcData[i][4]+"\",\"Rmrk\":\""+strSrvcData[i][5]+"\",\"UserId\":\""+strSrvcData[i][6]+"\"},";
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

	    if(si_Optn.equals("DATE_WISE_PROPOSALS"))
	    {
	        logger.info("Case: Uploaded Proposal Files as on particular date");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
                String si_Zone="";
                String si_Dvsn="";
                if(strLognFlag.equals("Z")) {
                    si_Zone  = strLognLocn;
                    si_Dvsn="";
                }
                if(strLognFlag.equals("D")) {
                    si_Zone  = (String)session.getAttribute("CrntZone");
                    si_Dvsn=strLognLocn;
                }
                
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.getDateWiseProposal(si_Zone, si_Dvsn);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"FileName\":\""+strSrvcData[i][1]+"\",\"PropDesc\":\""+strSrvcData[i][2]+"\",\"PropDtls\":\""+strSrvcData[i][3]+"\",\"Rmrk\":\""+strSrvcData[i][4]+"\",\"UpldTime\":\""+strSrvcData[i][5]+"\"}";
	                else
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"FileName\":\""+strSrvcData[i][1]+"\",\"PropDesc\":\""+strSrvcData[i][2]+"\",\"PropDtls\":\""+strSrvcData[i][3]+"\",\"Rmrk\":\""+strSrvcData[i][4]+"\",\"UpldTime\":\""+strSrvcData[i][5]+"\"},";
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
	    if(si_Optn.equals("SAVE_RECORD"))
	    {
	        String strData="";
	        logger.info("Case: Save Record for Zonal Acquisition Plan");
	        String si_Ctgr = (String) req.getParameter("Ctgr").trim().toUpperCase();
	        AcqnPlanSrvc obj= new AcqnPlanSrvc(si_UserID, si_ClntID);
	        logger.info("Start for Category:"+si_Ctgr);
                logger.info("strLognFlag:"+strLognFlag);
                String si_Zone="";
                logger.info("strLognLocn:"+strLognLocn);
	        if(strLognFlag.equals("Z"))
	            si_Zone=strLognLocn;
                else
                    try
                    {
                        si_Zone=(String) req.getParameter("Zone").trim().toUpperCase();
                    }
                    catch(Exception e) {
                        si_Zone="";
                    }
                logger.info("Zone:"+si_Zone);
                String si_Param1="";
                String si_Param2="";
                String si_Param3="";
                String si_Param4="";
                String si_Param5="";
                String si_Param6="";
                String si_Param7="";
                String si_Param8="";
                String si_Param9="";
                String si_Param10="";
                String si_Param11="";
                String si_Param12="";
                String si_Param13="";
                String si_Param14="";
                String si_Param15="";
                String si_Param16="";
                String si_Param17="";
                String si_Param18="";
                String si_Param19="";
                String si_Param20="";
                String si_Param21="";
                String si_Param22="";
                String si_Param23="";
                String si_Param24="";
                String si_Param25="";
                String si_Param26="";
                String si_Param27="";
                
                try
                {
                switch (si_Ctgr) 
                {
                    case "CMDT_COST_CMPR": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Inds").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("OthrDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("LocnType").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Locn").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("TotlVol").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("RailPctg").trim().toUpperCase();                    
                        si_Param8 = (String) req.getParameter("Slab1").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Slab2").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("Slab3").trim().toUpperCase();
                        si_Param11 = (String) req.getParameter("Slab4").trim().toUpperCase();
                        si_Param12 = (String) req.getParameter("Slab5").trim().toUpperCase();
                        si_Param13 = (String) req.getParameter("Slab6").trim().toUpperCase();
                        si_Param14 = (String) req.getParameter("Slab7").trim().toUpperCase();
                        si_Param15 = (String) req.getParameter("Slab8").trim().toUpperCase();
                        si_Param16 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("CMDT_COST_CMPR",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "CMDT_RAIL_COST": 
                        logger.info("0");
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        logger.info("1");
                        si_Param2 = (String) req.getParameter("Inds").trim().toUpperCase();
                        logger.info("2");
                        si_Param3 = (String) req.getParameter("OthrDtls").trim().toUpperCase();
                        logger.info("3");
                        si_Param4 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        logger.info("4");
                        si_Param5 = (String) req.getParameter("LocnType").trim().toUpperCase();
                        logger.info("5");
                        si_Param6 = (String) req.getParameter("Locn").trim().toUpperCase();
                        logger.info("6");
                        si_Param7 = (String) req.getParameter("TotlVol").trim().toUpperCase();
                        logger.info("7");
                        si_Param8 = (String) req.getParameter("RailPctg").trim().toUpperCase();                    
                        logger.info("8");
                        si_Param9 = (String) req.getParameter("Slab1").trim().toUpperCase();
                        logger.info("9");
                        si_Param10 = (String) req.getParameter("Slab2").trim().toUpperCase();
                        logger.info("10");
                        si_Param11 = (String) req.getParameter("Slab3").trim().toUpperCase();
                        logger.info("11");
                        si_Param12 = (String) req.getParameter("Slab4").trim().toUpperCase();
                        logger.info("12");
                        si_Param13 = (String) req.getParameter("Slab5").trim().toUpperCase();
                        logger.info("13");
                        si_Param14 = (String) req.getParameter("Slab6").trim().toUpperCase();
                        logger.info("14");
                        si_Param15 = (String) req.getParameter("Slab7").trim().toUpperCase();
                        logger.info("15");
                        si_Param16 = (String) req.getParameter("Slab8").trim().toUpperCase();
                        logger.info("16");
                        si_Param17 = (String) req.getParameter("Slab9").trim().toUpperCase();
                        logger.info("17");
                        si_Param18 = (String) req.getParameter("Slab10").trim().toUpperCase();
                        logger.info("18");
                        si_Param19 = (String) req.getParameter("Slab11").trim().toUpperCase();
                        logger.info("19");
                        si_Param20 = (String) req.getParameter("Slab12").trim().toUpperCase();
                        logger.info("20");
                        si_Param21 = (String) req.getParameter("Slab13").trim().toUpperCase();
                        logger.info("1");
                        si_Param22 = (String) req.getParameter("Slab14").trim().toUpperCase();
                        logger.info("21");
                        si_Param23 = (String) req.getParameter("Slab15").trim().toUpperCase();
                        logger.info("22");
                        /*
                        si_Param24 = (String) req.getParameter("LMCost").trim().toUpperCase();
                        si_Param26 = (String) req.getParameter("LdngCost").trim().toUpperCase();
                        si_Param27 = (String) req.getParameter("UldgCost").trim().toUpperCase();
                        String si_Costs=si_Param24+"/"+si_Param26+"/"+si_Param27;
                        */
                        si_Param24 = (String) req.getParameter("AddlCost").trim().toUpperCase();
                        logger.info("Addl Cost:    "+si_Param24);
                    
                        si_Param25 = (String) req.getParameter("RowId").trim().toUpperCase();
                        logger.info("si_Param25:"+si_Param3);
                        obj.addBDPlanData("CMDT_RAIL_COST",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "CMDT_ROAD_COST": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Inds").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("OthrDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Slab1").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("Slab2").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("Slab3").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("Slab4").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Slab5").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("Slab6").trim().toUpperCase();
                        si_Param11 = (String) req.getParameter("Slab7").trim().toUpperCase();
                        si_Param12 = (String) req.getParameter("Slab8").trim().toUpperCase();
                        si_Param13 = (String) req.getParameter("Slab9").trim().toUpperCase();
                        si_Param14 = (String) req.getParameter("Slab10").trim().toUpperCase();
                        si_Param15 = (String) req.getParameter("Slab11").trim().toUpperCase();
                        si_Param16 = (String) req.getParameter("Slab12").trim().toUpperCase();
                        si_Param17 = (String) req.getParameter("Slab13").trim().toUpperCase();
                        si_Param18 = (String) req.getParameter("Slab14").trim().toUpperCase();
                        si_Param19 = (String) req.getParameter("Slab15").trim().toUpperCase();
                        si_Param20 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("CMDT_ROAD_COST",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "END_MILE_COST": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Inds").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("OthrDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Slab1").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Slab2").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("Slab3").trim().toUpperCase();                    
                        si_Param7 = (String) req.getParameter("Slab4").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("Slab5").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Slab6").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("Slab7").trim().toUpperCase();
                        si_Param11 = (String) req.getParameter("Slab8").trim().toUpperCase();
                        si_Param12 = (String) req.getParameter("EndMileSlab1").trim().toUpperCase();
                        si_Param13 = (String) req.getParameter("EndMileSlab2").trim().toUpperCase();
                        si_Param14 = (String) req.getParameter("EndMileSlab3").trim().toUpperCase();
                        si_Param15 = (String) req.getParameter("EndMileSlab4").trim().toUpperCase();
                        si_Param16 = (String) req.getParameter("EndMileSlab5").trim().toUpperCase();
                        si_Param17 = (String) req.getParameter("EndMileSlab6").trim().toUpperCase();
                        si_Param18 = (String) req.getParameter("EndMileSlab7").trim().toUpperCase();
                        si_Param19 = (String) req.getParameter("EndMileSlab8").trim().toUpperCase();
                        si_Param20 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("END_MILE_COST",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "CNSR_WISE_INFO": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Inds").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("OthrDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("LocnType").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("Locn").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("TrfcType").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("StckType").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("TotlVol").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("RailPctg").trim().toUpperCase();                    
                        si_Param11 = (String) req.getParameter("CmptAdvt").trim().toUpperCase();                   
                        si_Param12 = (String) req.getParameter("OthrAdvtDtls").trim().toUpperCase();                 
                        si_Param13 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("CNSR_WISE_INFO",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "MKTG_STRT_BULK": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("TrfcType").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Orgn").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Dstn").trim().toUpperCase();                    
                        si_Param6 = (String) req.getParameter("CmptAdvt").trim().toUpperCase();  
                        si_Param7 = (String) req.getParameter("Cnsr").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("AddlRevn").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("MKTG_STRT_BULK",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "AGMT_LDNG": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("ActnPlan").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("OthrPlanDesc").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Ofcr").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("VolTrgt").trim().toUpperCase();                    
                        si_Param7 = (String) req.getParameter("RevnExpd").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("Cost").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("TranRqmt").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("HandHoldRB").trim().toUpperCase();
                        si_Param11 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("AGMT_LDNG",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "AGMT_LDNG1": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("Cnsr").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("TrfcType").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Stck").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("ActnPlan").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("OthrPlanDesc").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("Ofcr").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Cost").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("VolTrgt").trim().toUpperCase();                    
                        si_Param11 = (String) req.getParameter("RevnExpd").trim().toUpperCase();
                        si_Param12 = (String) req.getParameter("HandHoldRB").trim().toUpperCase();
                        si_Param13 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("AGMT_LDNG1",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "FF_CRNT": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("FFName").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("ContDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("TrfcVol").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Revn").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("OD").trim().toUpperCase();                    
                        si_Param7 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("DeplDate").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Rmrk").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("FF_CRNT",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "FF_PROPOSED": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("FFName").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("ContDtls").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("TrfcVol").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Revn").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("OD").trim().toUpperCase();                    
                        si_Param7 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("Prgs").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Rmrk").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("FF_PROPOSED",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "INIT_TRFC_BOOST": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Init").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("Ldng").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Revn").trim().toUpperCase();                    
                        si_Param6 = (String) req.getParameter("Rmrk").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("INIT_TRFC_BOOST",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "SWOT_ANLY": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param3 = ((String) req.getParameter("Strg"));
                        si_Param4 = (String) req.getParameter("StrgOthr").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("StrgDtls").trim().toUpperCase();
                        si_Param6 = ((String) req.getParameter("Weak")).trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("WeakOthr").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("WeakDtls").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("Oprt").trim().toUpperCase();  
                        si_Param10 = (String) req.getParameter("OprtOthr").trim().toUpperCase();
                        si_Param11 = (String) req.getParameter("OprtDtls").trim().toUpperCase();                    
                        si_Param12 = (String) req.getParameter("Thrt").trim().toUpperCase();                  
                        si_Param13 = (String) req.getParameter("ThrtOthr").trim().toUpperCase();               
                        si_Param14 = (String) req.getParameter("ThrtDtls").trim().toUpperCase();
                        si_Param15 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("SWOT_ANLY",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "INDS_OUTREACH": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("IndsType").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("Inds").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("IndsPers").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("Desg").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("ContDate").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("Cmdt").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("AddlRevn").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("AddlLdng").trim().toUpperCase();
                        si_Param10 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("INDS_OUTREACH",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "RAIL_COEF_BOOST":  
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Immed").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("ImmedOthr").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("MidTerm").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("MidTermOthr").trim().toUpperCase();
                        si_Param6 = (String) req.getParameter("LongTerm").trim().toUpperCase();
                        si_Param7 = (String) req.getParameter("LongTermOthr").trim().toUpperCase();
                        si_Param8 = (String) req.getParameter("TrfcPlanId").trim().toUpperCase();
                        si_Param9 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("RAIL_COEF_BOOST",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);                        
                        break;
                    case "OTHR_ASPECT": 
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("Aspect").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("OTHR_ASPECT",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "ZONL_PERF": 
                        si_Param1 = (String) req.getParameter("ProjRevn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("ProjLdng").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("ZONL_PERF",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                    case "DVSN_PERF": 
                    logger.info("Entered in DVSN_PERF");
                        si_Param1 = (String) req.getParameter("Dvsn").trim().toUpperCase();
                        si_Param2 = (String) req.getParameter("ProjRevn").trim().toUpperCase();
                        si_Param3 = (String) req.getParameter("ProjLdng").trim().toUpperCase();
                        si_Param4 = (String) req.getParameter("TrgtLdng").trim().toUpperCase();
                        si_Param5 = (String) req.getParameter("RowId").trim().toUpperCase();
                        obj.addBDPlanData("DVSN_PERF",si_Zone,si_Param1,si_Param2,si_Param3,si_Param4,si_Param5,si_Param6,si_Param7,si_Param8,si_Param9,si_Param10,si_Param11,si_Param12,si_Param13,si_Param14,si_Param15,si_Param16,si_Param17,si_Param18,si_Param19,si_Param20,si_Param21,si_Param22,si_Param23,si_Param24,si_Param25);
                        break;
                }
                }
                catch(Exception e) {
                    logger.info("In Exception:"+e.getMessage());
                    e.printStackTrace();
                }
                String strStts=obj.getCallStts();
                if((!strStts.equals("F")) && si_Ctgr.equals("RAIL_COEF_BOOST")) {
                    session.removeAttribute("Stts");
                    session.removeAttribute("ErorMesg");
                    session.removeAttribute("FileId");
                    session.removeAttribute("FileName");
                }
	        strData="{\"Stts\":\""+obj.getCallStts()+"\", \"ErorMesg\":\""+obj.getCallMesg()+"\"}";
	        res.getWriter().write(strData);
	    return;
	    }

	    if(si_Optn.equals("FETCH_PLAN_DATA"))
	    {
	        logger.info("Case: Fetch Plan Data");
	        
	        AcqnPlanSrvc obj=new AcqnPlanSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim();
	        String si_Date  = req.getParameter("Date").toUpperCase().trim();   
	        String si_Ctgr  = req.getParameter("Ctgr").toUpperCase().trim();   
                logger.info("si_Zone:"+si_Zone+", si_Date:"+si_Date+", si_Ctgr:"+si_Ctgr+", si_Dvsn:"+si_Dvsn);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchBDPlanData(si_Zone, si_Dvsn,si_Date, si_Ctgr);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
                            strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\",\"Val13\":\""+strSrvcData[i][12]+"\",\"Val14\":\""+strSrvcData[i][13]+"\",\"Val15\":\""+strSrvcData[i][14]+"\",\"Val16\":\""+strSrvcData[i][15]+"\",\"Val17\":\""+strSrvcData[i][16]+"\",\"Val18\":\""+strSrvcData[i][17]+"\",\"Val19\":\""+strSrvcData[i][18]+"\",\"Val20\":\""+strSrvcData[i][19]+"\",\"Val21\":\""+strSrvcData[i][20]+"\",\"Val22\":\""+strSrvcData[i][21]+"\",\"Val23\":\""+strSrvcData[i][22]+"\",\"Val24\":\""+strSrvcData[i][23]+"\",\"RowId\":\""+strSrvcData[i][24]+"\"}";
	                else
                            strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\",\"Val13\":\""+strSrvcData[i][12]+"\",\"Val14\":\""+strSrvcData[i][13]+"\",\"Val15\":\""+strSrvcData[i][14]+"\",\"Val16\":\""+strSrvcData[i][15]+"\",\"Val17\":\""+strSrvcData[i][16]+"\",\"Val18\":\""+strSrvcData[i][17]+"\",\"Val19\":\""+strSrvcData[i][18]+"\",\"Val20\":\""+strSrvcData[i][19]+"\",\"Val21\":\""+strSrvcData[i][20]+"\",\"Val22\":\""+strSrvcData[i][21]+"\",\"Val23\":\""+strSrvcData[i][22]+"\",\"Val24\":\""+strSrvcData[i][23]+"\",\"RowId\":\""+strSrvcData[i][24]+"\"},";
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

	    if(si_Optn.equals("UPLOAD_DECISION"))
	    {
	        logger.info("Case: Upload Decision Letter for Business Proposal");
	        factory = new DiskFileItemFactory();
	        ServletFileUpload sfu       = new ServletFileUpload(factory);
	        isMultiPart             = ServletFileUpload.isMultipartContent(req);
	        logger.info("3:isMultiPart:-"+isMultiPart);
	        logger.info("Decision Letter Uploading UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
	                
	        String strFileId="IRDCSN"+formatter.format(date);
	        String strCrntTime=formatter1.format(date);
	        logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
                logger.info("si_PropId1:"+si_PropId1+", si_DurnCtgr:"+si_DurnCtgr+", strFileName:"+strFileName+", si_UserID:"+si_UserID);
	        if(isMultiPart)
	        {
	            logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
	            try
	            {       
	                    logger.info("Getting DBConnection Object");
	        
	                    GG_DBConnection db = new GG_DBConnection();
	                    logger.info("6");
	                    connection=db.funcGetConn("RMSFIELD");
	                    logger.info("6a");
	                    String sqlstmt="";
	                    logger.info("Delete Statement for REC_BDUBSNSDECISION");
                            sqlstmt="UPDATE RMS3T.REC_BDUBSNSDECISION SET RACVALDFLAG='N' WHERE RAVPROPID=?";
                            PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                            ps.setString(1, si_PropId1);                            
                            ps.executeUpdate();
                            logger.info("Existing Decision File Updated");
                            ps.close();
                            logger.info("Insert Statement for REC_BDUBSNSDECISION");
	                    sqlstmt="INSERT INTO RMS3T.REC_BDUBSNSDECISION VALUES (?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?)";
	                    ps =  connection.prepareStatement(sqlstmt);
	                    ps.setString(1, si_PropId1);
	                    ps.setString(2, si_DurnCtgr);
	                    ps.setString(3, si_Rmrk);
	                    ps.setBinaryStream(4, fi, (long) li_filesize);
	                    ps.setString(5, strFileName);
	                    ps.setString(6, si_UserID);
	                    ps.setString(7, si_ClntID);
	                    ps.setString(8, strCrntTime);
	                    ps.setString(9,"Y");
	                    
	                    ps.executeUpdate();
	                    logger.info("Decision File Uploaded");
	                    ps.close();
	                    connection.commit();
	                    connection.close();
	                    req.setAttribute("strMesgSave","Decision Letter Uploaded Successfully");                           
	                    logger.info("Decision File Uploaded Successfully");
	            }
	            catch(Exception e)
	            {
	                    logger.info("Exception while getting the file !");
	                    System.out.println("Exception in uploading the file:"+e.getMessage());
	                    session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
	                    session.setAttribute("Stts","F");
	                    session.setAttribute("Zone",si_DataZone);
	                    res.sendRedirect("/RailSAHAY/pages/IRBDU_Decision.jsp");
	            }
	    }
	    logger.info("3c");
	    session.setAttribute("ErorMesg","");
	    session.setAttribute("Stts","S");
	    session.setAttribute("Zone",si_DataZone);
	    
	    try
	    {

	        res.sendRedirect("/RailSAHAY/pages/IRBDU_Decision.jsp");
	    }
	    catch(Exception e)
	    {
	            return;
	    }
	    return;
	    }

        if(si_Optn.equals("UPLOAD_TRFC_PLAN"))
        {
            logger.info("Case: Upload Traffic Plan");
            factory = new DiskFileItemFactory();
            ServletFileUpload sfu   = new ServletFileUpload(factory);
            isMultiPart             = ServletFileUpload.isMultipartContent(req);
            logger.info("3:isMultiPart:-"+isMultiPart);
            logger.info("Traffic Plan Uploading UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
            String strCrntZone="";
            String strCrntDvsn="";
            if(strLognFlag.equals("Z"))
                    strCrntZone=strLognLocn;
            if(strLognFlag.equals("D"))
            {
                   strCrntDvsn=strLognLocn;
                   strCrntZone=(String)session.getAttribute("CrntZone");   
            }
            logger.info("Request raised by Zone #"+strCrntZone+"#, Data Zone #"+strCrntZone+"#, Data Division #"+strCrntDvsn+",  UserId #"+si_UserID+"#, Client Id #"+si_ClntID);
                    
            String strFileId=strCrntZone+"_"+formatter.format(date);
            String strCrntTime=formatter1.format(date);
            logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
            logger.info("strFileName:"+strFileName+", si_UserID:"+si_UserID);
            if(!strFileName.endsWith(".pdf")) {
                logger.info("Exception while uploading the file !");
                session.setAttribute("Stts","F");
                session.setAttribute("Zone",si_DataZone);
                session.setAttribute("FileId","");
                session.setAttribute("FileName",strFileName);
                session.setAttribute("ErorMesg","File upload failed: Only PDF format is supported !");
                res.sendRedirect("/RailSAHAY/pages/IRBDU_TrfcPlanUpload.jsp");
                return;
            }
            if(isMultiPart)
            {
                logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
                try
                {       
                        logger.info("Getting DBConnection Object");            
                        GG_DBConnection db = new GG_DBConnection();
                        logger.info("6");
                        connection=db.funcGetConn("RMSFIELD");
                        logger.info("6a");
                        String sqlstmt="";
                        logger.info("Insert Statement for REC_BDUTRFCPLANFILE");
                        sqlstmt="INSERT INTO RMS3T.REC_BDUTRFCPLANFILE VALUES (?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?)";
                       PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                        ps.setString(1, strFileId);
                        ps.setString(2, strCrntZone);
                        ps.setString(3, strCrntDvsn);
                        ps.setString(4, strFileName);
                        ps.setBinaryStream(5, fi, (long) li_filesize);
                        ps.setString(6, si_UserID);
                        ps.setString(7, si_ClntID);
                        ps.setString(8, strCrntTime);
                        ps.setString(9,"Y");                        
                        ps.executeUpdate();
                        logger.info("Traffic Plan File Uploaded");
                        ps.close();
                        connection.commit();
                        connection.close();
                        req.setAttribute("strMesgSave","Traffic Plan Uploaded Successfully");                           
                        logger.info("Traffic Plan File Uploaded Successfully");
                }
                catch(Exception e)
                {
                        logger.info("Exception while getting the file !");
                        System.out.println("Exception in uploading the file:"+e.getMessage());
                        session.setAttribute("ErorMesg","File Upload Failed"+e.getMessage());
                        session.setAttribute("Stts","F");
                        session.setAttribute("Zone",si_DataZone);
                        session.setAttribute("FileId","");
                        session.setAttribute("FileName",strFileName);
                        res.sendRedirect("/RailSAHAY/pages/IRBDU_TrfcPlanUpload.jsp");
                }
        }
        logger.info("3c");
        session.setAttribute("ErorMesg","");
        session.setAttribute("Stts","S");
        session.setAttribute("FileId",strFileId);
        session.setAttribute("FileName",strFileName);
        try
        {
    
            res.sendRedirect("/RailSAHAY/pages/IRBDU_TrfcPlanUpload.jsp");
        }
        catch(Exception e)
        {
                return;
        }
        return;
        }
    }
}