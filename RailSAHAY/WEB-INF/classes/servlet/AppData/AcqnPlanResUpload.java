package servlet.AppData;

import com.tangosol.dev.assembler.Case;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.AcqnPlanResBN;
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

import java.math.BigDecimal;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.collections4.MultiValuedMap;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
                String tempfile="";
		String strZone="";
		String strDvsn="";
		String strLognFlag=((String)session.getAttribute("LognLocnFlag")).trim();
		String strLognLocn=((String)session.getAttribute("LognLocn")).trim(); 
                List <String> ZoneArr= new ArrayList<String>();
                List <String> DvsnArr= new ArrayList<String>();
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
            logger.info("File name extension"+strFileName.substring(strFileName.lastIndexOf(".") + 1,strFileName.length()));
            switch (strFileName.substring(strFileName.lastIndexOf(".") + 1,strFileName.length())) 
            {
                case "csv":
                logger.info("In csv case");
                   si_Optn ="UPLOAD_RES_FILE";
                    tempfile="IRBDU_SampleResourceFile.csv";
                    break;
                case "xlsx":
                    logger.info("In xlsx case");
                    si_Optn ="UPLOAD_EXCELSX_FILE";
                    tempfile="IRBDU_SampleResourceFile.xlsx";
                    break;
                 case "xls":
                    logger.info("In xls case");
                    si_Optn ="UPLOAD_EXCEL_FILE";
                    tempfile="IRBDU_SampleResourceFile.xls";
                    break;
              
                default:
                String msg="No CSV/XLS/XLSX file chosen";
                logger.info("Exception in uploading the file:"+msg);
                System.out.println("No CSV/XLS/XLSX file chosen"+msg);
                session.setAttribute("ErorMesg","File Upload Failed"+msg);
                session.setAttribute("Stts","F");
                session.setAttribute("Zone",strZone);
                res.sendRedirect("/RailSAHAY/pages/IRBDU_Resource.jsp");
                break;   
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
            
                if(si_Optn.equals("UPLOAD_EXCEL_FILE")||si_Optn.equals("UPLOAD_EXCELSX_FILE"))
                {
                    logger.info("Case: Upload Resource File");
                    factory = new DiskFileItemFactory();
                    ServletFileUpload sfu   = new ServletFileUpload(factory);
                    isMultiPart             = ServletFileUpload.isMultipartContent(req);
                     logger.info("isMultiPart:-"+isMultiPart);
                     String strFileId="";
                     String[] oarr = new String[9];
                     int i=0;
                     if(strZone.equals(""))
                        strFileId="IR_"+formatter.format(date);
                      else
                         strFileId=strZone+"_"+formatter.format(date);
                         String strCrntTime=formatter1.format(date);
                         logger.info("FileId #"+strFileId+"#, Current Time:#"+strCrntTime+"#");
                         if(isMultiPart)
                          {
                                logger.info("File Attached#Size:"+li_filesize+", strFileName:"+strFileName);
                                try
                                {       
                                logger.info("Getting DBConnection Object");
                    
                                GG_DBConnection db = new GG_DBConnection();
                                connection=db.funcGetConn("RMSFIELD");
                                MultiValuedMap<String, String> map= getZoneDvsnList(connection);
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
                                   int len;
                                   // write bytes from the buffer into the output stream
                                        logger.info("byearray created:"+buffer.length);
                                     os.write(buffer, 0, buffer.length);
                                    try (FileOutputStream fos = new FileOutputStream(tempfile)) 
                                    {
                                        fos.write(buffer);
                                        fos.close();
                                    }
                      logger.info("scanner now");
                       FileInputStream fis= new FileInputStream(tempfile); 
                       List<List<String>> sheetDataTable=null;
                     int cntr=0;
                  
                     //Reading from excel files and converting to object list   and inserting in database table            
                     if(  si_Optn.equals("UPLOAD_EXCELSX_FILE"))
                     {
                        //step1 get nested arraylist of data of file
                         sheetDataTable =ReadExcelsx(fis);
                    }
                    if(  si_Optn.equals("UPLOAD_EXCEL_FILE"))
                         { 
                             //step1 get nested arraylist of data of file
                            sheetDataTable =ReadExcel(fis);
                        }
                      //step2 get object arraylist of data of file
                        ArrayList<AcqnPlanResBN> objarrlist=getobjectList(sheetDataTable,si_UserID,si_ClntID,strFileId,strLognFlag,strZone,strDvsn,map); 
                         //step3 insert into database
                        InsertRes(objarrlist,connection);   
                     
                      fis.close();
                      File file1=new File(tempfile);
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
        public static List<List<String>> ReadExcelsx(FileInputStream fis )
        {
            int  cntr=0;
            int i=0;
            String data="";
            List<List<String>> ret = new ArrayList<List<String>>();
            List<String> errlist=new ArrayList<String>();
            try {
                //Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook xlsworkbook;
        
                xlsworkbook = new XSSFWorkbook(fis);
        
                //Get first/desired sheet from the workbook
                XSSFSheet xlssheet = xlsworkbook.getSheetAt(0);
                //Iterate through each rows one by one
                Iterator<Row> rowIterator = xlssheet.iterator();
                 while (rowIterator.hasNext())
                {
                 Row row = rowIterator.next();
                 //For each row, iterate through all the columns
                 Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowDataList = new ArrayList<String>();
                cntr++;
                 while (cellIterator.hasNext()) 
                 {
                     i=1;
                         Cell cell = cellIterator.next();
                         //Check the cell type and format accordingly
                         switch (cell.getCellType()) 
                         {
                                 case Cell.CELL_TYPE_NUMERIC:
                                     //logger.info(new BigDecimal(String.valueOf(cell.getNumericCellValue())).toPlainString()+ "\t");
                                      data=new BigDecimal(String.valueOf(cell.getNumericCellValue())).toPlainString();
                                      break;
                         
                                 case Cell.CELL_TYPE_STRING:
                                         // logger.info(cell.getStringCellValue() + "\t");
                                         data=cell.getStringCellValue();
                                         break;
                                 case Cell.CELL_TYPE_BLANK:
                                     //logger.info("Blank data" + "\t");
                                    data="";
                                    break;
                         default:
                             //logger.info("in default" + "\t");
                             data="";
                             break;
                         }
                    
                     rowDataList.add(data); 
                 }
                   // oarr.add(resource);
                    ret.add(rowDataList);
                 logger.info("");
                }
            } catch (IOException e) {
                logger.info("Exception in reading  the file data:"+e.getMessage());
               
                return ret;
            }
        return ret;
        }
        
    public static List<List<String>> ReadExcel(FileInputStream fis ) throws Exception
    {
        int  cntr=0;
        int i=0;
        String data="";
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> errlist=new ArrayList<String>();
        try {
            //Create Workbook instance holding reference to .xls file
             HSSFWorkbook xlworkbook =  new HSSFWorkbook(fis); 
                                          
              //Get first/desired sheet from the workbook
                HSSFSheet xlsheet = xlworkbook.getSheetAt(0);
                HSSFRow row; 
                HSSFCell cell;
                                     
                //Iterate through each rows one by one
                 Iterator rowIterator = xlsheet.rowIterator();

                  while (rowIterator.hasNext())
                  {
                          row=(HSSFRow) rowIterator.next();
                          Iterator cells = row.cellIterator();
                           i=0;
                       
                           List<String> rowDataList = new ArrayList<String>();
                          while (cells.hasNext())
                          {        data="";
                                 cell=(HSSFCell) cells.next();
                                 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                                 {
                                      data=String.valueOf(cell.getStringCellValue());
                                     
                                   }
                                    else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                                    {
                                       data=new BigDecimal(String.valueOf(cell.getNumericCellValue())).toPlainString();;
                                      
                                  }
                                else if (cell.getCellType() ==HSSFCell.CELL_TYPE_BLANK)
                                 {
                                        data = "";
                                }                
                                  else {
                                       }
                                rowDataList.add(data);          
                            }
               
                ret.add(rowDataList);
              logger.info("RowDataList zone"+ret.get(cntr).get(0));
                logger.info("RowDataList dvsn"+ret.get(cntr).get(1));
                logger.info("RowDataList name"+ret.get(cntr).get(4));
                cntr++;
            }
        } catch (IOException e) {
            logger.info("Exception in reading  the file data:"+e.getMessage());
           
            return ret;
        }
    return ret;
    } 

        ArrayList<AcqnPlanResBN> getobjectList( List<List<String>> datatbl,String strUserId,String strClntId, String strFileName,String strCrntFlag,String strZone, String strDvsn,MultiValuedMap<String, String> map) throws Exception {
            
            ArrayList<AcqnPlanResBN> oarrlist= new ArrayList();
            Connection conn=null;
           logger.info("Login Zone is"+strZone+  "and Dvsn is"+ strDvsn );
             for(int j=1; j<datatbl.size(); j++)
              {
                  AcqnPlanResBN res= new AcqnPlanResBN();
                // Get current row data.
                List<String> rowdata = datatbl.get(j);
                  logger.info("File Zone is"+rowdata.get(0)+  "and Dvsn is"+ rowdata.get(1) );
                 if (!(rowdata.get(0).equals(strZone)))
                 {
                     throw new CustomException("Zone should be same Login Zone-"+strZone+"but found  "+rowdata.get(0));   
                 }
                
                  if(!(rowdata.get(1) == null ||rowdata.get(1).length() == 0))
                  {   
                      if(strCrntFlag=="D")
                      { 
                          if (!(rowdata.get(1).equals(strDvsn)))
                          {
                              throw new CustomException("Division should be same Login Zone-"+strDvsn+"but found  "+rowdata.get(1));   
                          }
                      }
                      
                      if(!map.containsMapping(rowdata.get(0),rowdata.get(1)))
                      { 
                          throw new CustomException("Zone and Dvsn pair not found-"+rowdata.get(0)+"|"+rowdata.get(1));
                      }
                  }
                 
                 
                 logger.info("Name"+rowdata.get(4));
                for (int i = 0; i < rowdata.size(); i++) {
                  
                      
                       switch(i) 
                         {
                               case 0:
                                   res.setStrZone(rowdata.get(i));
                                   break;
                               case 1:
                                   res.setStrDvsn(rowdata.get(i));
                                   break;
                               case 2:
                                   res.setStrCmdt(rowdata.get(i));
                                   break;
                               case 3:
                                   res.setStrIndst(rowdata.get(i));
                                   break;
                               case 4:
                                   res.setStrResName(rowdata.get(i));
                                   break;
                               case 5:
                                   res.setStrResMobl(rowdata.get(i));
                                   break;
                               case 6:                            
                                   res.setStrResLdLn(rowdata.get(i));
                                   break;                         
                               case 7:                            
                                   res.setStrResEmail(rowdata.get(i));
                                   break;                         
                               case 8:                            
                                   res.setStrResAddr(rowdata.get(i));
                                   break;                         
                               
                           }
                      
                      
                   }
                  res.setStrResid(strFileName+j);
                  res.setStrClntId(strClntId);
                  res.setStrUserId(strUserId);
                  logger.info(j+""+ res.getStrResName());
                  oarrlist.add(res);
                 
              }
           
        return oarrlist;
       }
        
        
        
        public void InsertRes(List<AcqnPlanResBN> Reslist,Connection conn) throws Exception {
            String sqlstmt="";
            int cnt=0;
            String strCrntTime= new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
            sqlstmt="INSERT INTO RMS3T.REC_BDURESOURCE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";
            PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement(sqlstmt);
                for (AcqnPlanResBN reslist : Reslist) {
                               ps.setString(1, reslist.getStrResid());
                               ps.setString(2,  reslist.getStrZone());
                               ps.setString(3,  reslist.getStrDvsn());
                               ps.setString(4, reslist.getStrCmdt());
                               ps.setString(5, reslist.getStrIndst());
                               logger.info(reslist.getStrResName());
                               ps.setString(6, reslist.getStrResName());
                               ps.setString(7, reslist.getStrResMobl());
                               ps.setString(8, reslist.getStrResLdLn()); 
                               ps.setString(9, reslist.getStrResEmail());
                               ps.setString(10, reslist.getStrResAddr());
                               ps.setString(11, reslist.getStrUserId());
                               ps.setString(12, reslist.getStrClntId());
                               ps.setString(13, strCrntTime);
                              
                               ps.addBatch();
                               cnt++;
                               // execute every 100 rows or less
                               if (cnt % 10 == 0 || cnt == Reslist.size()) {
                                   logger.info("execute batch");
                                   ps.executeBatch();
                               }
                           }
            ps.close();
            conn.commit();
                logger.info("conn commited");
            } catch (SQLException e) {
                logger.info("Exception in inserting  the file data:"+e.getMessage());
                ps.close();
                conn.close();
            }
       
        }
        
    public  MultiValuedMap<String, String> getZoneDvsnList(Connection conn) throws Exception {
        String sqlstmt="";
        int cnt=0;
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        
        sqlstmt="SELECT MAVZONECODE, MAVDVSNCODE FROM MEMDVSN ORDER BY MAVZONECODE";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sqlstmt);
            ResultSet rs = ps.executeQuery(sqlstmt);
            while (rs.next()) {
                   String strZone = rs.getString("MAVZONECODE");
                   String strDvsn = rs.getString("MAVDVSNCODE");
                   map.put(strZone,strDvsn);
                 
                 }
    
        } catch (SQLException e) {
            logger.info("Exception in inserting  the file data:"+e.getMessage());
           
        }
        return map;
    }
    
  
}
