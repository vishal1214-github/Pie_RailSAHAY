package servlet.AppData;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;


import model.SHY_OwnWgonTX;

import org.apache.log4j.Logger;

import tuxedo.*;
import util.logger.FoisLogger;
import util.GenFunc.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.exception.GG_Exception;

public class SHY_OwnWgon extends HttpServlet
{
	
	static Logger logger = FoisLogger.getLogger(SHY_OwnWgon.class.getName());
	boolean bolValid=true;
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		logger.info("In Sahay Lodge Concern");	
          

                
	    HttpSession session = req.getSession();   
	    String si_UserID                =(String) session.getAttribute("UserID");
	    String si_ClntID                =(String) session.getAttribute("ClntID");
	    logger.info("si_UserID"+ si_UserID);
	    logger.info("si_ClntID"+ si_ClntID);
                String strReply="";
		String strError="";
		String strRefID="";
	        String strMesg="";
               
		String strName = "";
	        String strCmpyName = "";
	        String strEmail = "";
                String strPhone = "";
                String strCmdt = "";
                String strWgonType ="";
		
		
                String strDesc ="";
	
             
					
					
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
                                                    if(fieldName.equalsIgnoreCase("txtName"))
                                                    {
                                                            strName = strDesc.toUpperCase().trim();
                                                    }
				                    if(fieldName.equalsIgnoreCase("txtCompName"))
				                    {
				                            strCmpyName = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("txtEmail"))
				                    {
				                            strEmail = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("txtMobl"))
				                    {
				                            strPhone = strDesc.toUpperCase().trim();
				                    }
				                   
				                    if(fieldName.equalsIgnoreCase("txtCmdt"))
				                    {
				                            strCmdt = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("selWgonType"))
				                    {
				                            strWgonType = strDesc.toUpperCase().trim();
				                    }
				                    if(fieldName.equalsIgnoreCase("mesg"))
				                    {
				                            strMesg = strDesc.toUpperCase().trim();
				                    }
				                    
				                   
				                }
				              }
				          }
                                    
				    logger.info("strName"+ strName);
				    logger.info("strCmpyName"+ strCmpyName);
				    logger.info("strEmail"+ strEmail); 
				    logger.info("strPhone"+ strPhone);
				    logger.info("strCmdt"+ strCmdt); 
				    logger.info("strWgonType"+ strWgonType);
				    logger.info("strMesg"+ strMesg); 
				   
				    
				 
				
                                
				
				if (((strName.equals(""))||(strName==null)))
				{
					logger.info("Name Is Mandatory");
					strError="Name Is Mandatory";
					logger.info("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
					rd.forward(req,res);
					return;
				}
				    if (((strCmpyName.equals(""))||(strCmpyName==null)))
				    {
				            logger.info("Company Name Is Mandatory");
				            strError="Company Name Is Mandatory";
				            logger.info("strError"+strError);
				            req.setAttribute("ERROR",strError);
				             req.setAttribute("TRXNSTTS","FAILED");
				          req.setAttribute("REF_ID","");
				                    
				            RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
				            rd.forward(req,res);
				            return;
				    }   
				if (((strEmail.equals(""))||(strEmail==null)))
				{
					logger.info("Email Is Mandatory");
					strError="Email Is Mandatory";
					logger.info("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
					rd.forward(req,res);
					return;
				}
				if (((strPhone.equals(""))||(strPhone==null)))
				{
					logger.info("Mobile Is Mandatory");
					strError="Mobile Is Mandatory";
					logger.info("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
					rd.forward(req,res);
					return;
				}
				
				int intLen=strCmpyName.length();
				
				if (intLen < 5)
				{
					logger.info("Company Name Is too Short");
					strError="Company Name Is too Short";
					logger.info("strError"+strError);
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
						
					RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
					rd.forward(req,res);
					return;
				}
				    if (((strMesg.equals(""))||(strMesg==null)))
				    {
				            logger.info("Message Is Mandatory");
				            strError="Message Is Mandatory";
				            logger.info("strError"+strError);
				            req.setAttribute("ERROR",strError);
				             req.setAttribute("TRXNSTTS","FAILED");
				          req.setAttribute("REF_ID","");
				                    
				            RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
				            rd.forward(req,res);
				            return;
				    }
				    intLen=strMesg.length();
				    
				    if (intLen < 5)
				    {
				            logger.info("Message Is too Short");
				            strError="Message Is too Short";
				            logger.info("strError"+strError);
				            req.setAttribute("ERROR",strError);
				             req.setAttribute("TRXNSTTS","FAILED");
				          req.setAttribute("REF_ID","");
				                    
				            RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
				            rd.forward(req,res);
				            return;
				    }
				util.GenFunc.GG_SahayUtil vldt  = new util.GenFunc.GG_SahayUtil();
				   bolValid=true;
				   bolValid=   vldt.valdInpt(strName, "A");
				   if (bolValid==true)
				   {
					   
					}
					else 
					{
						logger.info("Only Alphabetic Characters  are  Allowed In UserName");
						strError="Only Alphabetic Characters  are  Allowed In UserName";
						logger.info("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
						rd.forward(req,res);
						return;
					}
				
				   bolValid=true;
				   bolValid=   vldt.valdInpt(strCmpyName, "C");
				   if (bolValid==true)
				   {
					   
					}
					else 
					{
						logger.info("Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Company Name");
						strError="Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Company Name";
						logger.info("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
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
						logger.info("INVALID CHARACTERS ARE NOT ALLOWED IN");
						strError="PLEASE ENTER A VALID EMAIL ADDRESS!";
						logger.info("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
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
						logger.info("INVALID CHARACTERS ARE NOT ALLOWED IN");
						strError="PLEASE ENTER A VALID MOBILE NUMBER!";
						logger.info("strError"+strError);
						req.setAttribute("ERROR",strError);
						 req.setAttribute("TRXNSTTS","FAILED");
					      req.setAttribute("REF_ID","");
							
						RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
						rd.forward(req,res);
						return;
					}
				    bolValid=true;
				    bolValid=   vldt.valdInpt(strMesg, "C");
				    if (bolValid==true)
				    {
				            
				         }
				         else 
				         {
				                 logger.info("Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Message");
				                 strError="Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed In Message";
				                 logger.info("strError"+strError);
				                 req.setAttribute("ERROR",strError);
				                  req.setAttribute("TRXNSTTS","FAILED");
				               req.setAttribute("REF_ID","");
				                         
				                 RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
				                 rd.forward(req,res);
				                 return;
				         }
				    
				   
				
				}
				catch(Exception e)
				
				{
					req.setAttribute("ERROR",strError);
					 req.setAttribute("TRXNSTTS","FAILED");
				      req.setAttribute("REF_ID","");
					RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
					rd.forward(req,res);
					return;	
				}
				
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
				strRefID = "WG_"+ft.format(dNow);
                                
                                
	    SHY_OwnWgonTX obj=new SHY_OwnWgonTX();
        try {
            strReply =   obj.SHY_OwnWgonTX(si_UserID, si_ClntID, strRefID, strName,
                                       strCmpyName, strPhone,strEmail,strCmdt,strWgonType, strMesg);
            logger.info("strReply" + strReply);
        } 
        catch (GG_Exception e) {
            logger.info("strReply" + strReply);
            strError = strReply.substring(strReply.indexOf("#")+1);
            req.setAttribute("ERROR",strError);
             req.setAttribute("TRXNSTTS","FAILED");
            req.setAttribute("REF_ID","");
            RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
            rd.forward(req,res);
            return; 
        }
        logger.info("strReply" + strReply);
        if(strReply.startsWith("SUCCESS"))
        {
            strMesg = strReply.substring(strReply.indexOf("#")+1);
        req.setAttribute("TRXNSTTS","SUCCESS"); 
			req.setAttribute("ERROR","");   
	        req.setAttribute("REF_ID",strMesg);
	        logger.info("SUCCESSFULL");
			RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
			rd.forward(req,res);
        }
        else
        {
                req.setAttribute("TRXNSTTS","FAILED"); 
                                req.setAttribute("ERROR","");   
                        req.setAttribute("REF_ID","");
                        logger.info("FAILED");
                                RequestDispatcher rd=req.getRequestDispatcher("pages/OwnWgon.jsp");
                                rd.forward(req,res);
            }
		
	}



public void doGet(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException
	{
		doPost(request, response);
	}

}

