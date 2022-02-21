package servlet.AppData;

import java.io.IOException;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import model.SRDtls;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import user.UserProfDtls;

import util.exception.GG_Exception;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;

public class RequestGeneration extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(RequestGeneration.class.getName());
    public RequestGeneration() {
        super();
    }
    Connection  connection=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       /*         HttpSession session = request.getSession();
                String strUserID=(String)session.getAttribute("custuserid");  

                 if(strUserID==null) strUserID="";
                 
                 logger.info("strUserID::"+strUserID+"||||");
                 
                      if(strUserID.equals(""))
                      {
                          RequestDispatcher rd=request.getRequestDispatcher("pages/eDmndLogin.jsp");
                          rd.forward(request,response);
                   
                      }
                      UserProfDtls userdtls = new UserProfDtls((String)session.getAttribute("custuserid"));  
               */
               
                String strcustid ="";// userdtls.getTavcustid();
                
                String crType="";//request.getParameter("crType");
                String cncrctgr="";//request.getParameter("cncrctgr");
                String locncr="";//request.getParameter("locncr");
                String subctgr="";//request.getParameter("subctgr");
                String mesgcr="";//request.getParameter("mesgcr");
                String strOrg = "";//request.getParameter("org");
                String locnType="";
                String railAuth="";
                String strDesc ="";
                
                String strFirstName = "";
                String strLastName = "";
                String strEmail = "";
                String strPhone = "";
                String strIsdCode = "";
                  String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                logger.info("strcustid:-"+strcustid);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                logger.info("::::::::::::::::::::::::::::::::In RAISE REQUEST::::::::::::::::::");
                String fileName="";
                String contentType ="";
                boolean isInMemory;
                long sizeInBytes =0;
                
                String erorCode                        = null;
                String strReply                       = "FAIL";       
                String strMessg= "";
                String cncrnId="";
                InputStream fileContent = null;
                
                String ctgrOth="";
                String sctgrOth="";
                
                try{
                    
                    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                          FileItemFactory factory = new DiskFileItemFactory();
                          ServletFileUpload upload = new ServletFileUpload(factory);
                           
                          /* CHECK FORM = MULTIPART */
                          if(isMultipart) {
                              List<FileItem> uploadItems = upload.parseRequest(request);
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
                                    if(fieldName.equalsIgnoreCase("full_mobile"))
                                    {
                                            strIsdCode = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("mobile"))
                                    {
                                            strPhone = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("subctgr"))
                                    {
                                            subctgr = strDesc.toUpperCase().trim();
                                    }
                                     if(fieldName.equalsIgnoreCase("mesg"))
                                    {
                                            mesgcr = strDesc;
                                    }
                                    if(fieldName.equalsIgnoreCase("cncrctgr"))
                                    {
                                            cncrctgr = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("hidval"))
                                    {
                                            crType = strDesc.toUpperCase().trim();
                                    }
                                   
                                    if(fieldName.equalsIgnoreCase("locntype"))
                                    {
                                            locnType = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("customer"))
                                    {
                                            strOrg = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("railAuth"))
                                    {
                                            railAuth = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("locncr"))
                                    {
                                            locncr = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("ctgrOth"))
                                    {
                                            ctgrOth = strDesc.toUpperCase().trim();
                                    }
                                    if(fieldName.equalsIgnoreCase("sctgrOth"))
                                    {
                                            sctgrOth = strDesc.toUpperCase().trim();
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
                    logger.info("--------------------------------strPhone::"+strPhone+"::strIsdCode"+strIsdCode+"---------------------------------------------------------");
          /*          if (((mesgcr.equals(""))||(mesgcr==null)))
                    {
                            logger.info("Message Is Mandatory");
                            erorCode="Message Is Mandatory";
                            logger.info("strError"+erorCode);
                            request.setAttribute("cncrctgr",cncrctgr);
                             request.setAttribute("locnType",locnType);
                            request.setAttribute("subctgr",subctgr);
                             request.setAttribute("locncr",locncr);
                            request.setAttribute("railAuth",railAuth);
                             request.setAttribute("mesgcr",mesgcr);                             
                            request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                          request.setAttribute("REF_ID","");
                        request.setAttribute("CR", "CR");
                        request.setAttribute("CRid",cncrnId);
                        request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",erorCode);
                            RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                            rd.forward(request,response);
                            return;
                    }
                    int intLen=mesgcr.length();
                    
                    if (intLen < 5)
                    {
                            logger.info("Message Is too Short");
                            erorCode="Message Is too Short";
                            logger.info("strError"+erorCode);
                            request.setAttribute("cncrctgr",cncrctgr);
                             request.setAttribute("locnType",locnType);
                            request.setAttribute("subctgr",subctgr);
                             request.setAttribute("locncr",locncr);
                            request.setAttribute("railAuth",railAuth);
                             request.setAttribute("mesgcr",mesgcr);                            
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                        request.setAttribute("CR", "CR");
                        request.setAttribute("CRid",cncrnId);
                        request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",erorCode);
                            RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                            rd.forward(request,response);
                            return;
                    } */
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
                
                if(cncrctgr==null || cncrctgr.equals("")){
                    

                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please select Category");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
                if(subctgr==null || subctgr.equals("")){
                    

                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please select Sub-Category");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
                
                if(locnType==null || locnType.equals("")){
                    

                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please select Location Type");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }               
                if(locncr==null || locncr.equals("")){

                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please select Location");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
                if (((strFirstName.equals(""))||(strFirstName==null)))
                {
                        logger.info("First Name Is Mandatory");
                       
                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","First Name Is Mandatory");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
              /*      if (((strLastName.equals(""))||(strLastName==null)))
                    {
                            logger.info("Last Name Is Mandatory");
                        request.setAttribute("cncrctgr",cncrctgr);
                         request.setAttribute("locnType",locnType);
                        request.setAttribute("subctgr",subctgr);
                         request.setAttribute("locncr",locncr);
                        request.setAttribute("railAuth",railAuth);
                         request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                        request.setAttribute("CR", "CR");
                        request.setAttribute("CRid",cncrnId);
                        request.setAttribute("CRreply",strReply);
                        request.setAttribute("CRerror","Last Name Is Mandatory");
                        RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                        rd.forward(request,response);
                        return;
                    }   */
                if (((strEmail.equals(""))||(strEmail==null)))
                {
                        logger.info("Email Is Mandatory");
                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Email Is Mandatory");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }else if(!isValid(strEmail)){
                    logger.info("Please provide valid Email Id");
                    request.setAttribute("cncrctgr",cncrctgr);
                    request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                    request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                    request.setAttribute("mesgcr",mesgcr);
                         request.setAttribute("fName",strFirstName);
                         request.setAttribute("lName",strLastName);
                        request.setAttribute("email",strEmail);
                         request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please provide valid Email Id");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                    
                }
                if (((strPhone.equals(""))||(strPhone==null)))
                {
                        logger.info("Mobile Is Mandatory");
                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Mobile Is Mandatory");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
               /*
                else if(strPhone.length()!=10){
                    logger.info("Please provide valid Mobile No");
                    request.setAttribute("cncrctgr",cncrctgr);
                    request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                    request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                    request.setAttribute("mesgcr",mesgcr);
                         request.setAttribute("fName",strFirstName);
                         request.setAttribute("lName",strLastName);
                        request.setAttribute("email",strEmail);
                         request.setAttribute("phnoe",strPhone);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Please provide valid Mobile No");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                }
                */
                if(cncrctgr.equals("OTHERS")){
                    cncrctgr=cncrctgr+" ("+ctgrOth+")";
                }
                
                if(subctgr.equals("OTHERS")){
                    subctgr=subctgr+" ("+sctgrOth+")";
                }
                
                
                
                if(locncr!=null) locncr=locncr.toUpperCase();
                
                logger.info("locn:-"+locncr+":cncrctgr:"+cncrctgr+":crType:"+crType);
                logger.info("locn:-"+locncr+":cncrctgr:"+cncrctgr+":crType:"+crType+":strOrg:"+strOrg+":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
               
                String sttnCode="";
                
                if(!locnType.equals("S")){
                
                    if(locncr.contains("-"))
                    {
                    sttnCode= locncr.substring(0, locncr.indexOf("-"));
                    }else{
                        sttnCode=locncr;
                    }
                }else{
                    
                    if(locncr.contains("-") && locncr.contains("("))
                    {
                    sttnCode= locncr.substring(locncr.indexOf("-")+2, locncr.indexOf("("));
                    }else{
                        sttnCode=locncr;
                    }
                }
                
                if(strOrg!=null) strOrg=strOrg.toUpperCase();
                String strOrgCode="";
                
                if(strOrg.contains("-"))
                {
                strOrgCode= strOrg.substring( strOrg.indexOf("-")+1);
                }else{
                    strOrgCode=strOrg;
                }
                logger.info("locncode:-"+sttnCode+":subctgr:"+subctgr+":locnType:"+locnType+":strOrgCode:"+strOrgCode);
                
/*
                if (((strOrgCode.equals(""))||(strOrgCode==null)))
                {
                        logger.info("Organization Is Mandatory");
                    request.setAttribute("cncrctgr",cncrctgr);
                     request.setAttribute("locnType",locnType);
                    request.setAttribute("subctgr",subctgr);
                     request.setAttribute("locncr",locncr);
                    request.setAttribute("railAuth",railAuth);
                     request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Organization Is Mandatory");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                } */
                String strRefID="";
                String strMesgSave="N";
                String strError="";
                try
                  {
                          Date dNow = new Date();
                          SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                          strRefID = "CR_"+ft.format(dNow);
                          
                          logger.info("IN FILE ATTACH");
                         
                                      
                                       logger.info("strRefID" + strRefID);
                                       logger.info("fileName" + fileName);

                                                          
                                                          
                                           if(sizeInBytes>0)
                                           {
                                                   
                                                      logger.info("Entering in File Upload");
                                                   strMesgSave ="Y" ;     
                                                      int FileSize=  (int) sizeInBytes/1024;
                                                                    logger.info("size In KB" +FileSize);
                                                                    String CType =contentType.substring(contentType.indexOf("/")+1).toUpperCase();
                                                                    logger.info("CType"+ CType);
                                                                  
                                                              if(FileSize > 10000)
                                                              {
                                                                   strMesgSave ="N" ;       
                                                                  logger.info("File Size Should Be Less than 10 MB");
                                                                  strError="File Size Should Be Less than 10 MB!";
                                                                  logger.info("strError"+strError);
                                                                  request.setAttribute("ERROR",strError);
                                                                   request.setAttribute("TRXNSTTS","FAILED");
                                                                  request.setAttribute("REF_ID","");

                                                                  request.setAttribute("cncrctgr",cncrctgr);
                                                                   request.setAttribute("locnType",locnType);
                                                                  request.setAttribute("subctgr",subctgr);
                                                                   request.setAttribute("locncr",locncr);
                                                                  request.setAttribute("railAuth",railAuth);
                                                                   request.setAttribute("mesgcr",mesgcr);
                                                                  request.setAttribute("CR", "CR");
                                                                  request.setAttribute("CRid",cncrnId);
                                                                  request.setAttribute("CRreply",strReply);
                                                                  request.setAttribute("CRerror",strError);
                                                                  RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                                                                  rd.forward(request,response);
                                                                  return;
                                                                  
                                                              }
                                                              
                                                              if ( !((CType.equals("PDF")) || (CType.equals("PLAIN")) || (CType.equals("TXT")) || (CType.equals("JPEG")) || (CType.equals("JPG")) || (CType.equals("PNG"))  ))
                                                              {
                                                                   strMesgSave ="N" ;             
                                                                    logger.info(" Only PDF/TXT/JPEG/PNG Formats Allowed");
                                                                  strError=" Only PDF/TXT/JPEG/PNG Formats Allowed";
                                                                  logger.info("strError"+strError);
                                                                  request.setAttribute("ERROR",strError);
                                                                   request.setAttribute("TRXNSTTS","FAILED");
                                                                  request.setAttribute("REF_ID","");

                                                                  request.setAttribute("cncrctgr",cncrctgr);
                                                                   request.setAttribute("locnType",locnType);
                                                                  request.setAttribute("subctgr",subctgr);
                                                                   request.setAttribute("locncr",locncr);
                                                                  request.setAttribute("railAuth",railAuth);
                                                                   request.setAttribute("mesgcr",mesgcr);                          
                                                                 request.setAttribute("fName",strFirstName);
                                                                 request.setAttribute("lName",strLastName);
                                                                request.setAttribute("email",strEmail);
                                                                 request.setAttribute("phnoe",strPhone);
                                                                request.setAttribute("customer", strOrg);
                                                                  request.setAttribute("CR", "CR");
                                                                  request.setAttribute("CRid",cncrnId);
                                                                  request.setAttribute("CRreply",strReply);
                                                                  request.setAttribute("CRerror",strError);
                                                                  RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                                                                  rd.forward(request,response);
                                                                  return;
                                                              }
                                                              
                                                              if(strMesgSave.equals("Y"))
                                                              {
                                                                  dbConnection db = new dbConnection();
                                                          connection=db.getConnection();
                                                          String sqlstmt="";
                                                          
                                                                  logger.info("INSERT");
                                                                   sqlstmt="INSERT INTO REM_SHAUSERATCH(RAVCNCRID,RAVRFRNID,RAVCNCRFLAG,RAVFILENAME,RAVFILE,RAVATCHBYUSERID,RAVATCHBYCLNTID,RAVATCHBY) VALUES (?,?,?,?,?,?,?,?)";
                                                                   PreparedStatement ps =  connection.prepareStatement(sqlstmt);
                                                                   ps.setString(1,null);
                                                                   ps.setString(2, strRefID);
                                                                   ps.setString(3, "CR");
                                                                   ps.setString(4, fileName);
                                                                   ps.setBinaryStream(5, fileContent);
                                                                   ps.setString(6, "FBD");
                                                                   ps.setString(7, si_ClntId);
                                                                   ps.setString(8, "C");
                                                                   ps.executeUpdate();
                                                                          logger.info("File Uploaded");
                                                                          ps.close();
                                                                          connection.commit();
                                                                          connection.close();
                                                                          
                                                                  
                                                                       logger.info("File Uploaded SUCESSFULLY");
                                                               }
                                           }      
                      }
                     
                      catch(SQLException e)
                      {
                          strMesgSave ="N";
                          //logger.fatal("SQL Exception: " + e.toString());
                          logger.info("SQL Exception: " + e.getMessage());
                          e.printStackTrace();
                          strError=" SQL Exception:";
                          logger.info("strError"+strError);
                            request.setAttribute("cncrctgr",cncrctgr);
                             request.setAttribute("locnType",locnType);
                            request.setAttribute("subctgr",subctgr);
                             request.setAttribute("locncr",locncr);
                            request.setAttribute("railAuth",railAuth);
                             request.setAttribute("mesgcr",mesgcr);                          
                             request.setAttribute("fName",strFirstName);
                             request.setAttribute("lName",strLastName);
                            request.setAttribute("email",strEmail);
                             request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                          request.setAttribute("CR", "CR");
                          request.setAttribute("CRid",cncrnId);
                          request.setAttribute("CRreply",strReply);
                          request.setAttribute("CRerror",strError);
                          RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                          rd.forward(request,response);
                          return;
                      }
                      catch (Exception e)
                      {
                          strMesgSave ="N";
                          //logger.fatal("Other Exception: " + e.toString());
                          logger.info("Other Exception: " + e.getMessage());
                          e.printStackTrace();
                          strError=" Other Exception:";
                          logger.info("strError"+strError);
                            request.setAttribute("cncrctgr",cncrctgr);
                             request.setAttribute("locnType",locnType);
                            request.setAttribute("subctgr",subctgr);
                             request.setAttribute("locncr",locncr);
                            request.setAttribute("railAuth",railAuth);
                             request.setAttribute("mesgcr",mesgcr);
                          
                          request.setAttribute("fName",strFirstName);
                          request.setAttribute("lName",strLastName);
                          request.setAttribute("email",strEmail);
                          request.setAttribute("phnoe",strPhone);
                            request.setAttribute("customer", strOrg);
                          request.setAttribute("CR", "CR");
                          request.setAttribute("CRid",cncrnId);
                          request.setAttribute("CRreply",strReply);
                          request.setAttribute("CRerror",strError);
                          RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                          rd.forward(request,response);
                          return;
                      } 
                
                
                FOISTuxedo RTSAHAYPRTL  = null;

                //*************************************************************************************
                                                                 //Calling Tuxedo Service from WTC
                //**************************Calling Service RTSAHAYPRTL*********************************
                
                
                
                try
                {
                        logger.info("Calling Tuxedo");
                        RTSAHAYPRTL = new FOISTuxedo();
                        logger.info("Client Object Got.");
                }
                catch (Exception ne)
                {
                        logger.fatal("Unable to get the Client Object: " + ne.toString());
                    strError="Error connecting to FOIS system.";
                    logger.info("strError"+strError);
                      request.setAttribute("cncrctgr",cncrctgr);
                       request.setAttribute("locnType",locnType);
                      request.setAttribute("subctgr",subctgr);
                       request.setAttribute("locncr",locncr);
                      request.setAttribute("railAuth",railAuth);
                       request.setAttribute("mesgcr",mesgcr);                          
                       request.setAttribute("fName",strFirstName);
                       request.setAttribute("lName",strLastName);
                      request.setAttribute("email",strEmail);
                       request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",strError);
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                   // return strReply;
                }
                
                try
                {
                        RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
                
                RTSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
                RTSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
                RTSAHAYPRTL.setString("F_FLAG",     0,   "SAVE_SAHYCNCRN");
               RTSAHAYPRTL.setString("F_COMMITFLAG",     0,   "Y");
                RTSAHAYPRTL.setString("F_ORDRBY1",     0,   strRefID);
                RTSAHAYPRTL.setString("F_ORDRBY2",     0,   "WORKFLOWID");
                RTSAHAYPRTL.setString("F_ORDRBY3",     0,   crType);
                RTSAHAYPRTL.setString("F_ORDRBY4",     0,   cncrctgr);
                RTSAHAYPRTL.setString("F_ORDRBY5",     0,   subctgr);        
                RTSAHAYPRTL.setString("F_ORDRBY6",     0,   strFirstName); 
                RTSAHAYPRTL.setString("F_ORDRBY7",     0,   strLastName);
                RTSAHAYPRTL.setString("F_ORDRBY8",     0,   strPhone);
                RTSAHAYPRTL.setString("F_ORDRBY9",     0,   strEmail);
                RTSAHAYPRTL.setString("F_ORDRBY10",     0,   sttnCode);
                RTSAHAYPRTL.setString("F_RPWBNUMB",     0,   mesgcr);
                RTSAHAYPRTL.setString("F_RPWBNUMB1",     0,   "");
               RTSAHAYPRTL.setString("F_CUSTCODE",     0,   strOrgCode);
               RTSAHAYPRTL.setString("F_LOCNFLAG",     0,   locnType);
               RTSAHAYPRTL.setString("F_AUTHBY",     0,   railAuth);
               RTSAHAYPRTL.setString("F_LOADCTGR",     0,   "U");
               RTSAHAYPRTL.setString("F_HELPCODE",     0,  strIsdCode);
                
                    

                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                    strError="Error connecting to FOIS system.";
                    logger.info("strError"+strError);
                      request.setAttribute("cncrctgr",cncrctgr);
                       request.setAttribute("locnType",locnType);
                      request.setAttribute("subctgr",subctgr);
                       request.setAttribute("locncr",locncr);
                      request.setAttribute("railAuth",railAuth);
                       request.setAttribute("mesgcr",mesgcr);                          
                       request.setAttribute("fName",strFirstName);
                       request.setAttribute("lName",strLastName);
                      request.setAttribute("email",strEmail);
                       request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",strError);
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
                        RTSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        logger.info("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                    strError="Error connecting to FOIS system.";
                    logger.info("strError"+strError);
                      request.setAttribute("cncrctgr",cncrctgr);
                       request.setAttribute("locnType",locnType);
                      request.setAttribute("subctgr",subctgr);
                       request.setAttribute("locncr",locncr);
                      request.setAttribute("railAuth",railAuth);
                       request.setAttribute("mesgcr",mesgcr);                          
                       request.setAttribute("fName",strFirstName);
                       request.setAttribute("lName",strLastName);
                      request.setAttribute("email",strEmail);
                       request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",strError);
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                      //  return strReply;
                }
                
                try
                {
                        erorCode                        = RTSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
                        logger.info("erorCode::"+erorCode);
                }
                catch(Exception e)
                {
                        // F_ERORNO is not set by Developer, So, it is not an Error
                    
                }
                if(erorCode != null && (!erorCode.equals("")))
                {
                        
                        logger.fatal(erorCode);
                    strMessg = strReply+"#"+erorCode; 
                    strError="Error connecting to FOIS system.";
                    logger.info("strError"+strError);
                      request.setAttribute("cncrctgr",cncrctgr);
                       request.setAttribute("locnType",locnType);
                      request.setAttribute("subctgr",subctgr);
                       request.setAttribute("locncr",locncr);
                      request.setAttribute("railAuth",railAuth);
                       request.setAttribute("mesgcr",mesgcr);                          
                       request.setAttribute("fName",strFirstName);
                       request.setAttribute("lName",strLastName);
                      request.setAttribute("email",strEmail);
                       request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                    request.setAttribute("CR", "CR");
                    request.setAttribute("CRid",cncrnId);
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",erorCode);
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                    rd.forward(request,response);
                    return;
                    
                        
                }
                 
                
                        
                 
                     try
                    {                                           
                        strReply                        = RTSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                        cncrnId                        = RTSAHAYPRTL.getStringItemDef("F_SET",                        0,      "0").trim();
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RTSAHAYPRTL and filling data into array" + d.toString());
                        strMessg = strReply+"#"+d.toString(); 
                        strError="Error connecting to FOIS system.";
                        logger.info("strError"+strError);
                          request.setAttribute("cncrctgr",cncrctgr);
                           request.setAttribute("locnType",locnType);
                          request.setAttribute("subctgr",subctgr);
                           request.setAttribute("locncr",locncr);
                          request.setAttribute("railAuth",railAuth);
                           request.setAttribute("mesgcr",mesgcr);                          
                           request.setAttribute("fName",strFirstName);
                           request.setAttribute("lName",strLastName);
                          request.setAttribute("email",strEmail);
                           request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                        request.setAttribute("CR", "CR");
                        request.setAttribute("CRid",cncrnId);
                        request.setAttribute("CRreply",strReply);
                        request.setAttribute("CRerror",strError);
                        RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                        rd.forward(request,response);
                        return;
                    
                        
                    }
                        
                
                        try
                        {
                                RTSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                            strError="Error connecting to FOIS system.";
                            logger.info("strError"+strError);
                              request.setAttribute("cncrctgr",cncrctgr);
                               request.setAttribute("locnType",locnType);
                              request.setAttribute("subctgr",subctgr);
                               request.setAttribute("locncr",locncr);
                              request.setAttribute("railAuth",railAuth);
                               request.setAttribute("mesgcr",mesgcr);                          
                               request.setAttribute("fName",strFirstName);
                               request.setAttribute("lName",strLastName);
                              request.setAttribute("email",strEmail);
                               request.setAttribute("phnoe",strPhone);
                    request.setAttribute("customer", strOrg);
                            request.setAttribute("CR", "CR");
                            request.setAttribute("CRid",cncrnId);
                            request.setAttribute("CRreply",strReply);
                            request.setAttribute("CRerror",strError);
                            RequestDispatcher rd=request.getRequestDispatcher("pages/RaiseRequest.jsp");
                            rd.forward(request,response);
                            return;
                   
                        }
                        logger.info("Sucessfull Execution of ConcernGeneration ||RTSAHAYPRTL");
                logger.info("strReply:"+strReply);
                
              
                
                
                
                request.setAttribute("CR", "CR");
                request.setAttribute("CRid",cncrnId);
                request.setAttribute("CRreply",strReply);
                request.setAttribute("CRerror",erorCode);
                RequestDispatcher rd=request.getRequestDispatcher("pages/SuccessRequest.jsp");
                rd.forward(request,response);
            /*    String jsontxt="[{\"reply\":\""+strReply+"\",\"errorC\":\""+erorCode+"\"}]";
                logger.info("jsontxt"+jsontxt);
                response.getWriter().write(jsontxt);*/
            }
    
    public static boolean isValid(String email) 
        { 
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                                "[a-zA-Z0-9_+&*-]+)*@" + 
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                                "A-Z]{2,7}$"; 
                                  
            Pattern pat = Pattern.compile(emailRegex); 
            if (email == null) 
                return false; 
            return pat.matcher(email).matches(); 
        } 
    
}
