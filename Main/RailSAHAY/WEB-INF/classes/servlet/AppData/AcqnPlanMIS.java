package servlet.AppData;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.AcqnPlanMISSrvc;

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

import model.AcqnPlanMISSrvc;
import model.AcqnPlanSrvc;

public class AcqnPlanMIS extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(AcqnPlanMIS.class.getName()); 
	private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "UPLOAD";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    Connection  connection=null;
    String fileName ="";
    
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling AcqnPlanMIS.java...");
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
                String si_OthrTypeDtls="";
                String si_DurnCtgr="";
                String si_PropId="";
                String si_PropId1="";
                String si_PropCmdt="";
                String si_PropLoadType="";
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
					res.sendRedirect("/RailSAHAY/pages/IRBDU_UploadZonalPlan.jsp");
				}
			}
			logger.info("3c");
                        session.setAttribute("ErorMesg","");
                        session.setAttribute("Stts","S");
                        session.setAttribute("Zone",si_DataZone);
			
			try
			{

			    res.sendRedirect("/RailSAHAY/pages/IRBDU_UploadZonalPlan.jsp");
			}
			catch(Exception e)
			{
				return;
			}
			return;
		}
	    if(si_Optn.equals("DVSN_PERF_STATS"))
	    {
	        logger.info("Case: Divisional Performance Statistics");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();    
	        logger.info("Inputs received: si_Zone-"+si_Zone);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchDvsnPerfStats(si_Zone);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DvsnList\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("Divisions Length:"+intDataLen);
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
	            logger.info("Divisions Monthly Performance Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"Mnth\":\""+strSrvcData[i][1]+"\",\"TngeLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"TngePctg\":\""+strSrvcData[i][4]+"\",\"FrgtLY\":\""+strSrvcData[i][5]+"\",\"FrgtCY\":\""+strSrvcData[i][6]+"\",\"FrgtPctg\":\""+strSrvcData[i][7]+"\"}";
	                else
	                strData+="{\"Dvsn\":\""+strSrvcData[i][0]+"\",\"Mnth\":\""+strSrvcData[i][1]+"\",\"TngeLY\":\""+strSrvcData[i][2]+"\",\"TngeCY\":\""+strSrvcData[i][3]+"\",\"TngePctg\":\""+strSrvcData[i][4]+"\",\"FrgtLY\":\""+strSrvcData[i][5]+"\",\"FrgtCY\":\""+strSrvcData[i][6]+"\",\"FrgtPctg\":\""+strSrvcData[i][7]+"\"},";
	            }
	            strData+="]}";
	            logger.info(strData);
	            res.getWriter().write(strData);
	        }
	        catch(Exception e)
	        {
	        System.out.println("Error in Data Fetch");
	        res.getWriter().write("Data Contains Error! or No Records");
	        }
	    }

	    if(si_Optn.equals("IR_DASHBOARD"))
	    {
	        logger.info("Case: IR Dashboard Statistics");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();
	        String si_Cmdt  = req.getParameter("Cmdt").toUpperCase().trim();    
	        logger.info("Inputs received: si_Zone-"+si_Zone+", si_Cmdt:"+si_Cmdt);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            logger.info("Calling fetchIRDashbordData");
	            strSrvcData=obj.fetchIRDashbordData(si_Zone, si_Cmdt);
                    logger.info("Called fetchIRDashbordData");
	            String strCallStts=obj.getCallStts();
	            logger.info("strCallStts:"+strCallStts);
	            String strCallMesg=obj.getCallMesg();
	            logger.info("strCallMesg:"+strCallMesg);

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"ZonlPerf\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("Dataset1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\"}";
	                else
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\"},";
	            }
	            strData+="],\"ActnPlan\":[";
	            strSrvcData=obj.getAddlData();
	            intDataLen=strSrvcData.length;
	            logger.info("Dataset2 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\"}";
	                else
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\"},";
	            }
	            strData+="],\"Init\":[";
	            strSrvcData=obj.getAddlData1();
	            intDataLen=strSrvcData.length;
	            logger.info("Dataset3 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"IndsOut\":[";
	            strSrvcData=obj.getAddlData2();
	            intDataLen=strSrvcData.length;
	            logger.info("Dataset4 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\"}";
	                else
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\"},";
	            }
	            strData+="],\"CmdtCoef\":[";
	            strSrvcData=obj.getAddlData3();
	            intDataLen=strSrvcData.length;
	            logger.info("Dataset5 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\"}";
	                else
	                strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\"},";
	            }
	            strData+="]}";
	            logger.info(strData);
	            res.getWriter().write(strData);
	        }
	        catch(Exception e)
	        {
                    System.out.println("Error in Data Fetch");
                    res.getWriter().write("Data Contains Error! or No Records");
	        }
	    }
	    if(si_Optn.equals("FETCH_PLAN_DATA"))
	    {
	        logger.info("Case: Fetch Plan Data");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();   
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim(); 
	        String si_Cmdt  = req.getParameter("Cmdt").toUpperCase().trim(); 
	        String si_Mnth  = req.getParameter("Mnth").toUpperCase().trim(); 
	        String si_Ctgr  = req.getParameter("Ctgr").toUpperCase().trim();
	        logger.info("si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn+", si_Cmdt:"+si_Cmdt+", si_Mnth:"+si_Mnth+", si_Ctgr:"+si_Ctgr);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchBDPlanData(si_Zone, si_Dvsn, si_Cmdt, si_Mnth, si_Ctgr);
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

	    if(si_Optn.equals("FETCH_RESOURCE"))
	    {
	        logger.info("Case: Fetch Resource Detail");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();   
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim(); 
	        String si_Cmdt  = req.getParameter("Cmdt").toUpperCase().trim(); 
	        logger.info("si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn+", si_Cmdt:"+si_Cmdt);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchResource(si_Zone, si_Dvsn, si_Cmdt);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\"}";
	                else
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\"},";
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
	    if(si_Optn.equals("CMDT_RAIL_COEF"))
	    {
	        logger.info("Case: Fetch Commodity Rail Coefficient");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();   
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim(); 
	        logger.info("si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchCmdtRailCoef(si_Zone, si_Dvsn);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\"}";
	                else
                            strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\"},";
	            }
	            strData+="]}";
	            logger.info(strData);
	            res.getWriter().write(strData);
	        }
	        catch(Exception e)
	        {
	        System.out.println("Error in Data Fetch");
	        res.getWriter().write("Data Contains Error! or No Records");
	        }
	    }
	    if(si_Optn.equals("FETCH_PRGS_MESR"))
	    {
	        logger.info("Case: Fetch Progress Measure");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim(); 
	        String si_Cmdt  = req.getParameter("Cmdt").toUpperCase().trim(); 
	        String si_MesrType  = req.getParameter("MesrType").toUpperCase().trim(); 
	        logger.info("si_Zone:"+si_Zone+",  si_Cmdt:"+si_Cmdt+", si_MesrType:"+si_MesrType);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchPrgsMeasure(si_Zone, si_Cmdt, si_MesrType);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\"}";
	                else
                            strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\"},";
	            }
	            strData+="]}";
	            logger.info(strData);
	            res.getWriter().write(strData);
	        }
	        catch(Exception e)
	        {
	        System.out.println("Error in Data Fetch");
	        res.getWriter().write("Data Contains Error! or No Records");
	        }
	    }

	    if(si_Optn.equals("RAIL_ROAD_COMPARISON"))
	    {
	        logger.info("Case: Rail Road Comparison");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();   
	        String si_Dvsn  = req.getParameter("Dvsn").toUpperCase().trim(); 
	        String si_Cmdt  = req.getParameter("Cmdt").toUpperCase().trim(); 
	        String si_Mnth  = req.getParameter("Mnth").toUpperCase().trim(); 
	        logger.info("si_Zone:"+si_Zone+", si_Dvsn:"+si_Dvsn+", si_Cmdt:"+si_Cmdt+", si_Mnth:"+si_Mnth);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchRailRoadCmpr(si_Zone, si_Dvsn, si_Cmdt, si_Mnth);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\",\"Val13\":\""+strSrvcData[i][12]+"\",\"Val14\":\""+strSrvcData[i][13]+"\",\"Val15\":\""+strSrvcData[i][14]+"\",\"Val16\":\""+strSrvcData[i][15]+"\",\"Val17\":\""+strSrvcData[i][16]+"\",\"Val18\":\""+strSrvcData[i][17]+"\",\"Val19\":\""+strSrvcData[i][18]+"\",\"Val20\":\""+strSrvcData[i][19]+"\"}";
	                else
	                    strData+="{\"Val1\":\""+strSrvcData[i][0]+"\",\"Val2\":\""+strSrvcData[i][1]+"\",\"Val3\":\""+strSrvcData[i][2]+"\",\"Val4\":\""+strSrvcData[i][3]+"\",\"Val5\":\""+strSrvcData[i][4]+"\",\"Val6\":\""+strSrvcData[i][5]+"\",\"Val7\":\""+strSrvcData[i][6]+"\",\"Val8\":\""+strSrvcData[i][7]+"\",\"Val9\":\""+strSrvcData[i][8]+"\",\"Val10\":\""+strSrvcData[i][9]+"\",\"Val11\":\""+strSrvcData[i][10]+"\",\"Val12\":\""+strSrvcData[i][11]+"\",\"Val13\":\""+strSrvcData[i][12]+"\",\"Val14\":\""+strSrvcData[i][13]+"\",\"Val15\":\""+strSrvcData[i][14]+"\",\"Val16\":\""+strSrvcData[i][15]+"\",\"Val17\":\""+strSrvcData[i][16]+"\",\"Val18\":\""+strSrvcData[i][17]+"\",\"Val19\":\""+strSrvcData[i][18]+"\",\"Val20\":\""+strSrvcData[i][19]+"\"},";
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

	    if(si_Optn.equals("FETCH_LOVS"))
	    {
	        logger.info("Case: Fetch List of Values");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
	            strSrvcData=obj.fetchLOVs();
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"Ctgr\":\""+strSrvcData[i][0]+"\",\"Code\":\""+strSrvcData[i][1]+"\",\"Desc\":\""+strSrvcData[i][2]+"\"}";
	                else
	                strData+="{\"Ctgr\":\""+strSrvcData[i][0]+"\",\"Code\":\""+strSrvcData[i][1]+"\",\"Desc\":\""+strSrvcData[i][2]+"\"},";
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
	    if(si_Optn.equals("LIST_PROPOSAL_FILES"))
	    {
	        logger.info("Case: Fetch Business Proposal Files List");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_Zone  = req.getParameter("Zone").toUpperCase().trim();                
	        String si_PropType1  = req.getParameter("PropType").toUpperCase().trim();
                logger.info("Inputs received: si_Zone-"+si_Zone+", si_PropType1-"+si_PropType1);
	        try
	        {
	            String strSrvcData[][];
	            String strData="";
                    strSrvcData=obj.getProposalList(si_Zone,si_PropType1);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DataList1\":[";
	            int intDataLen=strSrvcData.length;
	            logger.info("DataList1 Length:"+intDataLen);
	            for(int i=0;i<intDataLen;i++)
	            {   
	                if(i==(intDataLen-1))
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"PropType\":\""+strSrvcData[i][3]+"\",\"OthrTypeDtls\":\""+strSrvcData[i][4]+"\",\"Cmdt\":\""+strSrvcData[i][5]+"\",\"LoadType\":\""+strSrvcData[i][6]+"\",\"FileName\":\""+strSrvcData[i][2]+"\",\"Rmrk\":\""+strSrvcData[i][7]+"\",\"UserId\":\""+strSrvcData[i][8]+"\",\"ClntId\":\""+strSrvcData[i][9]+"\",\"UpldTime\":\""+strSrvcData[i][10]+"\",\"DurnCtgr\":\""+strSrvcData[i][11]+"\",\"RespRmrk\":\""+strSrvcData[i][12]+"\",\"LetrName\":\""+strSrvcData[i][13]+"\",\"RespUserId\":\""+strSrvcData[i][14]+"\",\"UpdtTime\":\""+strSrvcData[i][15]+"\",\"DurnCtgrCode\":\""+strSrvcData[i][16]+"\"}";
	                else
	                strData+="{\"PropId\":\""+strSrvcData[i][0]+"\",\"Zone\":\""+strSrvcData[i][1]+"\",\"PropType\":\""+strSrvcData[i][3]+"\",\"OthrTypeDtls\":\""+strSrvcData[i][4]+"\",\"Cmdt\":\""+strSrvcData[i][5]+"\",\"LoadType\":\""+strSrvcData[i][6]+"\",\"FileName\":\""+strSrvcData[i][2]+"\",\"Rmrk\":\""+strSrvcData[i][7]+"\",\"UserId\":\""+strSrvcData[i][8]+"\",\"ClntId\":\""+strSrvcData[i][9]+"\",\"UpldTime\":\""+strSrvcData[i][10]+"\",\"DurnCtgr\":\""+strSrvcData[i][11]+"\",\"RespRmrk\":\""+strSrvcData[i][12]+"\",\"LetrName\":\""+strSrvcData[i][13]+"\",\"RespUserId\":\""+strSrvcData[i][14]+"\",\"UpdtTime\":\""+strSrvcData[i][15]+"\",\"DurnCtgrCode\":\""+strSrvcData[i][16]+"\"},";
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
	    if(si_Optn.equals("FETCH_CMDT_RAIL_COST"))
	    {
	        logger.info("Case: Fetch Commodity Rail Cost");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
	        String si_NumCmdt  = req.getParameter("NumCmdt").toUpperCase().trim();                
	        logger.info("Inputs received: si_NumCmdt-"+si_NumCmdt);
	        try
	        {
	            String strSrvcData[];
	            String strData="";
	            strSrvcData=obj.fetchCmdtRailCost(si_NumCmdt);
	            String strCallStts=obj.getCallStts();
	            String strCallMesg=obj.getCallMesg();

	            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TLCost\":\""+strSrvcData[0]+"\",\"WLCost\":\""+strSrvcData[1]+"\"}";
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
	        
	        logger.info("inside try");
	        GG_DBConnection db = new GG_DBConnection();
	        connection=null;
	        connection=db.funcGetConn("RMSFIELD");           
	        logger.debug("Got Connection");
	        Statement statement = connection.createStatement();
	        logger.debug("Created Statement");
	    String strQuery = "SELECT RABFILE,RAVFILENAME RAVFILENAME FROM REC_BDUPROPOSAL WHERE RAVPROPID = '" + si_FileId +"'  ";
	    
	    logger.debug("Query:"+strQuery);
	    
	    ResultSet rs    =       statement.executeQuery(strQuery);
	    
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
	    if(si_Optn.equals("LIST_UPLOADED_FILES"))
	    {
	        logger.info("Case: Uploaded Files List");
	        
	        AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc(si_UserID, si_ClntID);
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
	                    logger.info("Insert Statement for REC_BDUBSNSDECISION");
	                    sqlstmt="INSERT INTO RMS3T.REC_BDUBSNSDECISION VALUES (?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
	                    PreparedStatement ps =  connection.prepareStatement(sqlstmt);
	                    ps.setString(1, si_PropId1);
	                    ps.setString(2, si_DurnCtgr);
	                    ps.setString(3, si_Rmrk);
	                    ps.setBinaryStream(4, fi, (long) li_filesize);
	                    ps.setString(5, strFileName);
	                    ps.setString(6, si_UserID);
	                    ps.setString(7, si_ClntID);
	                    ps.setString(8, strCrntTime);
	                    
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
	}
}