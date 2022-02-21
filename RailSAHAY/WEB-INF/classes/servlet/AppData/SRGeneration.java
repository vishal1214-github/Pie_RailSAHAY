package servlet.AppData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SRDtls;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.exception.GG_Exception;

import util.logger.FoisLogger;

public class SRGeneration extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(SRGeneration.class.getName());
    public SRGeneration() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                String strFlag=(String)request.getParameter("SRFlag");

        
            if(!strFlag.equals("FDB"))
            {
                String strcustid = request.getParameter("custuserid");
                String locn = request.getParameter("locn");
                String fnr = request.getParameter("fnr");
                String srtype = request.getParameter("srtype");
                String mesg = request.getParameter("mesg");
                String strOrg = request.getParameter("org");
                 String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                logger.info("strcustid:-"+strcustid);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                if(locn!=null) locn=locn.toUpperCase();
                System.out.println("strOrg"+strOrg);
                logger.info("locn:-"+locn+":fnr:"+fnr+":srtype:"+srtype);
                
                String erorCode                        = null;
                String strReply                       = "FAIL";       
                String strMessg= "";
                
                
                
                
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
                   // return strReply;
                }
                
                try
                {
                        RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
                
                RTSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
                RTSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
                RTSAHAYPRTL.setString("F_FLAG",     0,   "SAVE_SAHYSRVC");
               RTSAHAYPRTL.setString("F_COMMITFLAG",     0,   "Y");
                RTSAHAYPRTL.setString("F_ORDRBY2",     0,   "WORKFLOWID");
                RTSAHAYPRTL.setString("F_ORDRBY4",     0,   strcustid);
                RTSAHAYPRTL.setString("F_ORDRBY5",     0,   fnr);        
                RTSAHAYPRTL.setString("F_ORDRBY6",     0,   srtype); 
                RTSAHAYPRTL.setString("F_ORDRBY7",     0,   locn);
                RTSAHAYPRTL.setString("F_ORDRBY10",     0,   mesg);
               RTSAHAYPRTL.setString("F_CUSTCODE",     0,   strOrg);
                //RTSAHAYPRTL.setString("F_RPWBNUMB1",     0,   strPhone);
                
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
                        RTSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
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
                    
                        
                }
                 
                
                        
                 
                     try
                    {                                           
                        strReply                        = RTSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RTSAHAYPRTL and filling data into array" + d.toString());
                        strMessg = strReply+"#"+d.toString(); 
                        
                    }
                        
                
                        try
                        {
                                RTSAHAYPRTL.endSession();
                        }
                        catch(Exception e)
                        {
                                logger.fatal("Error In End Session:" + e.toString());
                   
                        }
                        logger.info("Sucessfull Execution of SRGeneration ||RTSAHAYPRTL");
                logger.info("strReply:"+strReply);
                String jsontxt="[{\"reply\":\""+strReply+"\",\"errorC\":\""+erorCode+"\"}]";
                System.out.println("jsontxt"+jsontxt);
                response.getWriter().write(jsontxt);
            }else{
                
                
               
                String mesg = request.getParameter("mesg");
                String strRtng = request.getParameter("rating");
                String strCrId = request.getParameter("crId");
                 String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                logger.info("strCrId:-"+strCrId);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                
                System.out.println("strCrId:-"+strCrId+":strRtng:"+strRtng+":si_ClntId:"+si_ClntId);
                
                String erorCode                        = null;
                String strReply                       = "FAIL";       
                String strMessg= "";
                
                
                
                
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
                    request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Error Connecting, Please Try After Sometime.");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
                    rd.forward(request,response);
                    return;
                   // return strReply;
                }
                
                try
                {
                        RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
                
                RTSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
                RTSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
                RTSAHAYPRTL.setString("F_FLAG",     0,   "SAVE_SAHYRQSTFDBK");
                RTSAHAYPRTL.setString("F_COMMITFLAG",     0,   "Y");
                RTSAHAYPRTL.setString("F_ORDRBY1",     0,   strCrId);
                RTSAHAYPRTL.setString("F_ORDRBY2",     0,   mesg);
                RTSAHAYPRTL.setString("F_ORDRBY3",     0,   strRtng);  
                
                
                }
                catch(Exception e)
                {
                        logger.fatal("Unable to write to buffer : " + e.toString());
                    request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Error Connecting, Please Try After Sometime.");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
                    rd.forward(request,response);
                    return;
                   // return strReply;
                }

                try
                {
                        logger.info("Calling Tuxedo Service RTSAHAYPRTL ...");
                        RTSAHAYPRTL.call("N");
                        logger.info("CALL COMPLETED !");
                        System.out.println("CALL COMPLETED !");
                }
                catch(Exception e)
                {
                        logger.fatal("Exception while call to the service is " + e.toString());
                    request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror","Error Connecting, Please Try After Sometime.");
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
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
                    request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                    request.setAttribute("CRreply",strReply);
                    request.setAttribute("CRerror",strMessg);
                    RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
                    rd.forward(request,response);
                    return;
                    
                        
                }
                 
                
                        
                 
                     try
                    {                                           
                        strReply                        = RTSAHAYPRTL.getStringItemDef("F_RMRK",                        0,      "0").trim();
                                                
                                }
                    catch(Exception d)
                    {
                        logger.fatal("Problem in Calling Service RTSAHAYPRTL and filling data into array" + d.toString());
                        strMessg = strReply+"#"+d.toString(); 
                        
                        request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                        request.setAttribute("CRreply",strReply);
                        request.setAttribute("CRerror",strMessg);
                        RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
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
                            
                            request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                            request.setAttribute("CRreply",strReply);
                            request.setAttribute("CRerror","Error Connecting, Please Try After Sometime.");
                            RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
                            rd.forward(request,response);
                            return;
                   
                        }
                        logger.info("Sucessfull Execution of SRGeneration ||RTSAHAYPRTL");
                logger.info("strReply:"+strReply);
                request.setAttribute("REF",strCrId);
                    request.setAttribute("CRFb","CRFB");
                request.setAttribute("CRreply",strReply);
                request.setAttribute("CRerror","SUCCESS");
                RequestDispatcher rd=request.getRequestDispatcher("pages/RequestFeedBack.jsp");
                rd.forward(request,response);
                return;
                
                
                
            }
            
        }
    
}
