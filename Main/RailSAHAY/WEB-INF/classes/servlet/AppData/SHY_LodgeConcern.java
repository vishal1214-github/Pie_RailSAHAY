package servlet.AppData;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;


import model.SHY_LodgeConcernTX;

import model.SHY_LodgeConcernTX;

import org.apache.log4j.Logger;

import tuxedo.*;
import util.logger.FoisLogger;
import util.GenFunc.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.struts.upload.FormFile;

import util.exception.GG_Exception;


import util.jdbc.dbConnection;

public class SHY_LodgeConcern extends HttpServlet
{
    Connection  connection=null;
	static Logger logger = FoisLogger.getLogger(SHY_LodgeConcern.class.getName());
	boolean bolValid=true;
    String strMesgSave="Y";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("In Sahay Lodge Concern");	
          

	    HttpSession session = req.getSession();   
	    String si_UserID                =(String) session.getAttribute("UserID");
            String si_ClntID                =(String) session.getAttribute("ClntID");
	    System.out.println("si_UserID"+ si_UserID);
	    System.out.println("si_ClntID"+ si_ClntID);
                String strReply="";
		String strError="";
		String strRefID="";
                String strCncrType ="";
		String strCncrCtgr = "";
                String strCncrSubCtgr = "";
		String strFirstName = "";
	        String strLastName = "";
                String strLocn ="";
		String strEmail = "";
		String strPhone = "";
		String strMesg = "";
                String strDesc ="";
	        String strReplyMesg="";
                String rating="";
                    String fileName="";
	            String contentType ="";
	            boolean isInMemory;
	            long sizeInBytes =0;
	   
	    InputStream fileContent = null;
		
            
               
                                  
				try
				{
				         
				    
				    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
				          FileItemFactory factory = new DiskFileItemFactory();
				          ServletFileUpload upload = new ServletFileUpload(factory);
				           
				          /* CHECK FORM = MULTIPART */
				          if(isMultipart) {
				              List<FileItem> uploadItems = upload.parseRequest(req);
				              for(FileItem uploadItem : uploadItems)
				              {
				                if(uploadItem.isFormField())
				                {
				                  String fieldName = uploadItem.getFieldName();
				                  strDesc = uploadItem.getString();
                                                    if(fieldName.equalsIgnoreCase("firstname"))
                                                    {
                                                            strFirstName = strDesc.toUpperCase().trim();
                                                    }
				                    if(fieldName.equalsIgnoreCase("lastname"))
				                    {
				                            strLastName = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("email"))
				                    {
				                            strEmail = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("mobile"))
				                    {
				                            strPhone = strDesc.toUpperCase().trim();
				                    }
				                     if(fieldName.equalsIgnoreCase("mesg"))
				                    {
				                            strMesg = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("cncrctgr"))
				                    {
				                            strCncrCtgr = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("hidval"))
				                    {
				                            strCncrType = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("rating"))
				                    {
				                            rating = strDesc.trim();
				                    }
				                }
                                                  else           
                                                 {
                                                     
                                                         fileContent = uploadItem.getInputStream();
                                                   
                                                         fileName = uploadItem.getName();
                                                         contentType = uploadItem.getContentType();
                                                         isInMemory = uploadItem.isInMemory();
                                                         sizeInBytes = uploadItem.getSize();
                                                      
                                                    }
				              }
				          }
                                    
				    System.out.println("strFirstName"+ strFirstName);
				    System.out.println("strLastName"+ strLastName);
				    System.out.println("strEmail"+ strEmail); 
				    System.out.println("strPhone"+ strPhone); 
				   // System.out.println("strLocn"+ strLocn); 
				    System.out.println("strMesg"+ strMesg); 
				    System.out.println("strCncrCtgr"+ strCncrCtgr);
				   // System.out.println("strCncrSubCtgr"+ strCncrSubCtgr);
				    System.out.println("strCncrType"+ strCncrType);
                                    
				    System.out.println("fileName" + fileName);
                                     System.out.println("contentType" + contentType);
                                   System.out.println("sizeInBytes" + sizeInBytes);
                                    

                    System.out.println("strCncrType:::::::"+ strCncrType);       
                                    
                            if(!strCncrType.equals("COMPLIMENT")) {	
                                
				if (((strCncrCtgr.equals(""))||(strCncrCtgr==null)))
				{
					System.out.println("Concern Category Is Mandatory");
					strError="Concern Category Is Mandatory";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
				if (((strFirstName.equals(""))||(strFirstName==null)))
				{
					System.out.println("First Name Is Mandatory");
					strError="First Name Is Mandatory";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
				    if (((strLastName.equals(""))||(strLastName==null)))
				    {
				            System.out.println("First Name Is Mandatory");
				            strError="First Name Is Mandatory";
				            System.out.println("strError"+strError);
				            req.setAttribute("ERROR",strError);
				             req.setAttribute("TRXNSTTS","FAILED");
				          req.setAttribute("REF_ID","");
				                    
				            RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
				            rd.forward(req,res);
				            return;
				    }   
				if (((strEmail.equals(""))||(strEmail==null)))
				{
					System.out.println("Email Is Mandatory");
					strError="Email Is Mandatory";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
				if (((strPhone.equals(""))||(strPhone==null)))
				{
					System.out.println("Mobile Is Mandatory");
					strError="Mobile Is Mandatory";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
                            }else{
                                if (((rating.equals(""))||(rating==null)))
                                {
                                        System.out.println("Rating Is Mandatory");
                                        strError="Please provide Rating between 1 to 5";
                                        System.out.println("strError"+strError);
                                        req.setAttribute("ERROR",strError);
                                         req.setAttribute("TRXNSTTS","FAILED");
                                      req.setAttribute("REF_ID","");
                                                
                                        RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
                                        rd.forward(req,res);
                                        return;
                                }
                                
                            }
				if (((strMesg.equals(""))||(strMesg==null)))
				{
					System.out.println("Message Is Mandatory");
					strError="Message Is Mandatory";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
				int intLen=strMesg.length();
				
				if (intLen < 5)
				{
					System.out.println("Message Is too Short");
					strError="Message Is too Short";
					System.out.println("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
					rd.forward(req,res);
					return;
				}
				
				util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
			/*	    bolValid=true;
				    bolValid=   vldt.valdInpt(strMesg, "C");
				    if (bolValid==true)
				    {
				            
				         }
				         else 
				         {
				                 System.out.println("Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Message");
				                 strError="Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Message";
				                 System.out.println("strError"+strError);
				                 req.setAttribute("ERROR",strError);
				                  req.setAttribute("TRXNSTTS","FAILED");
				               req.setAttribute("REF_ID","");
				                         
				                 RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
				                 rd.forward(req,res);
				                 return;
				         }
				    */
		if(!strCncrType.equals("COMPLIMENT")) {    
                    
				   bolValid=true;
				   bolValid=   vldt.valdInpt(strFirstName, "A");
				   if (bolValid==true)
				   {
					   
					}
					else 
					{
						System.out.println("Only Alphabetic Characters  are  Allowed In UserName");
						strError="Only Alphabetic Characters  are  Allowed In UserName";
						System.out.println("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
						rd.forward(req,res);
						return;
					}
				
				  
				
					   bolValid=true;
				   bolValid=   vldt.valdInpt(strEmail, "E");
				   if (bolValid==true)
				   {
					   
					}
					else 
					{
						System.out.println("INVALID CHARACTERS ARE NOT ALLOWED IN");
						strError="PLEASE ENTER A VALID EMAIL ADDRESS!";
						System.out.println("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
						rd.forward(req,res);
						return;
					}
				   bolValid=true;
				   bolValid=   vldt.valdInpt(strPhone, "M");
				   if (bolValid==true)
				   {
					   
					}
					else 
					{
						System.out.println("INVALID CHARACTERS ARE NOT ALLOWED IN");
						strError="PLEASE ENTER A VALID MOBILE NUMBER!";
						System.out.println("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
						rd.forward(req,res);
						return;
					}
                            }
				   
				
                }
                catch(Exception e)            
                {
                        req.setAttribute("ERROR",strError);
                         req.setAttribute("TRXNSTTS","FAILED");
                      req.setAttribute("REF_ID","");
                        RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
                        rd.forward(req,res);
                        return;	
                }
	if(!strCncrType.equals("COMPLIMENT")) {		
	    try
              {
                      Date dNow = new Date();
                      SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                      strRefID = "CR_"+ft.format(dNow);
                      
                      System.out.println("IN FILE ATTACH");
                     
                                  
                                   System.out.println("strRefID" + strRefID);
                                   System.out.println("fileName" + fileName);

	                                              
	                                              
	                               if(sizeInBytes>0)
	                               {
	                                       
	                                          System.out.println("Entering in File Upload");
	                                       strMesgSave ="Y" ;     
	                                          int FileSize=  (int) sizeInBytes/1024;
	                                                        System.out.println("size In KB" +FileSize);
	                                                        String CType =contentType.substring(contentType.indexOf("/")+1).toUpperCase();
	                                                        System.out.println("CType"+ CType);
	                                                      
	                                                  if(FileSize > 500)
	                                                  {
	                                                       strMesgSave ="N" ;       
	                                                      System.out.println("File Size Should Be Less than 500 KB");
	                                                      strError="File Size Should Be Less than 500 KB!";
	                                                      System.out.println("strError"+strError);
	                                                      req.setAttribute("ERROR",strError);
	                                                       req.setAttribute("TRXNSTTS","FAILED");
	                                                      req.setAttribute("REF_ID","");
	                                                              
	                                                      RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
	                                                      rd.forward(req,res);
	                                                      return;
	                                                      
	                                                  }
	                                                  
	                                                  if ( !((CType.equals("PDF")) || (CType.equals("TXT")) || (CType.equals("JPEG")) || (CType.equals("JPG")) || (CType.equals("PNG"))  ))
	                                                  {
	                                                       strMesgSave ="N" ;             
	                                                        System.out.println(" Only PDF/TXT/JPEG/PNG Formats Allowed");
	                                                      strError=" Only PDF/TXT/JPEG/PNG Formats Allowed";
	                                                      System.out.println("strError"+strError);
	                                                      req.setAttribute("ERROR",strError);
	                                                       req.setAttribute("TRXNSTTS","FAILED");
	                                                      req.setAttribute("REF_ID","");
	                                                              
	                                                      RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
	                                                      rd.forward(req,res);
	                                                      return;
	                                                  }
	                                                  
	                                                  if(strMesgSave.equals("Y"))
	                                                  {
	                                                      dbConnection db = new dbConnection();
	                                              connection=db.getConnection();
	                                              String sqlstmt="";
	                                              
	                                                      System.out.println("INSERT");
	                                                       sqlstmt="INSERT INTO REM_SHAUSERATCH VALUES (?,?,?,?,?)";
	                                                       PreparedStatement ps =  connection.prepareStatement(sqlstmt);
	                                                       ps.setString(1,null);
	                                                       ps.setString(2, strRefID);
	                                                       ps.setString(3, "CR");
	                                                       ps.setString(4, fileName);
	                                                       ps.setBinaryStream(5, fileContent);
	                                                       ps.executeUpdate();
	                                                              System.out.println("File Uploaded");
	                                                              ps.close();
	                                                              connection.commit();
	                                                              connection.close();
	                                                              
	                                                      
	                                                           System.out.println("File Uploaded SUCESSFULLY");
	                                                   }
	                               }      
                  }
                 
                  catch(SQLException e)
                  {
                      strMesgSave ="N";
                      //logger.fatal("SQL Exception: " + e.toString());
                      System.out.println("SQL Exception: " + e.getMessage());
                      e.printStackTrace();
                      strError=" SQL Exception:";
                      System.out.println("strError"+strError);
                      req.setAttribute("ERROR",strError);
                       req.setAttribute("TRXNSTTS","FAILED");
                      req.setAttribute("REF_ID","");
                      RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
                      rd.forward(req,res);
                      return;
                  }
                  catch (Exception e)
                  {
                      strMesgSave ="N";
                      //logger.fatal("Other Exception: " + e.toString());
                      System.out.println("Other Exception: " + e.getMessage());
                      e.printStackTrace();
                      strError=" Other Exception:";
                      System.out.println("strError"+strError);
                      req.setAttribute("ERROR",strError);
                       req.setAttribute("TRXNSTTS","FAILED");
                      req.setAttribute("REF_ID","");
                      RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
                      rd.forward(req,res);
                      return;
                  }   
        }                
                                
	    SHY_LodgeConcernTX obj=new SHY_LodgeConcernTX();
        try {
            if(!strCncrType.equals("COMPLIMENT")) { 
            strReply =   obj.SHY_LodgeConcernTX(si_UserID, si_ClntID, strRefID,strCncrType, strCncrCtgr, strCncrSubCtgr, strFirstName,
                                       strLastName, "DLI", strEmail, strPhone, strMesg);
            }else{
                strReply = obj.SHY_FeedbackTX(si_UserID, si_ClntID, strFirstName, strLastName, strEmail, strPhone, strMesg, rating);
                //"SUCCESS#with Rating:"+rating+":: Feedback="+strMesg+"::";
            }
            System.out.println("strReply" + strReply);
        } 
        catch (GG_Exception e) {
            System.out.println("strReply" + strReply);
            strError = strReply.substring(strReply.indexOf("#")+1);
            req.setAttribute("ERROR",strError);
             req.setAttribute("TRXNSTTS","FAILED");
            req.setAttribute("REF_ID","");
            RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
            rd.forward(req,res);
            return; 
        }
        System.out.println("strReply" + strReply);
        if(strReply.startsWith("SUCCESS"))
        {
                strReplyMesg = strReply.substring(strReply.indexOf("#")+1);
        req.setAttribute("TRXNSTTS","SUCCESS"); 
			req.setAttribute("ERROR","");   
	        req.setAttribute("REF_ID",strReplyMesg);
	        req.setAttribute("CRTYPE",strCncrType);
	        System.out.println("SUCCESSFULL");
			RequestDispatcher rd=req.getRequestDispatcher("pages/FeedbackResponse.jsp");
			rd.forward(req,res);
        }
        else
        {
                req.setAttribute("TRXNSTTS","FAILED"); 
                                req.setAttribute("ERROR","");   
                        req.setAttribute("REF_ID","");
                        System.out.println("FAILED");
                                RequestDispatcher rd=req.getRequestDispatcher("pages/Feedback.jsp");
                                rd.forward(req,res);
          }
		
        
    }



public void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException
	{
		doPost(request, response);
	}

}

