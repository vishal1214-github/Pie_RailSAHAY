package servlet.AppData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class NewCustomer extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(NewCustomer.class.getName());
    public NewCustomer() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


                String custName = request.getParameter("txtName");
                String mailId = request.getParameter("txtEmail");
                String mob = request.getParameter("txtMob");
                String compName = request.getParameter("txtCompName");
                String compType = request.getParameter("compType");
                String txtOrgnLoc = request.getParameter("txtOrgnLoc");
                String txtDstnLoc = request.getParameter("txtDstnLoc");
                String txtRakeCmdt = request.getParameter("txtRakeCmdt");
                String selPkgCndn = request.getParameter("selPkgCndn");
                String txtVol = request.getParameter("txtVol");
                String selVol = request.getParameter("selVol");
                String selPerd = request.getParameter("selPerd");
                String mvmttype = request.getParameter("mvmttype");
                String txtRmrk = request.getParameter("txtRmrk");
                String selOrigClstType = request.getParameter("selOrigClstType");
                String selDstnClstType = request.getParameter("selDstnClstType");
                 String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                logger.info("strcustid:-"+custName);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                logger.info("mailId:-"+mailId+"mob:-"+mob+"compName:-"+compName+"compType:-"+compType+"txtOrgnLoc:-"+txtOrgnLoc+"txtRmrk:="+txtRmrk);
                logger.info("txtDstnLoc:-"+txtDstnLoc+"txtRakeCmdt:-"+txtRakeCmdt+"selPkgCndn:-"+selPkgCndn+"txtVol:-"+txtVol+"mvmttype:-"+mvmttype);
                  
                String erorCode                        = null;
                String strReply                       = "FAIL";       
                String strMessg= "";
                
                if(custName==null || custName.equals("")){
                    request.setAttribute("message", "Please provide Name");
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/TransportWithUs.jsp");
                    dispatcher.forward(request, response);
                    
                }
                if(mailId==null) mailId="";
                if(mob==null) mob="";
                
                if(mailId.equals("") && mob.equals("")){
                    request.setAttribute("message", "Please provide either Mobile No. or E-mail ID.");
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/TransportWithUs.jsp");
                    dispatcher.forward(request, response);
                }
                
                String sttnFromCode=txtOrgnLoc;
                String sttnToCode=txtDstnLoc;
                
                if(txtOrgnLoc!=null && !txtOrgnLoc.equals("")){
                    System.out.println("txtOrgnLoc"+txtOrgnLoc);
                    if(selOrigClstType.equals("S")){
                        if(txtOrgnLoc.contains("(") && txtOrgnLoc.contains(")") && txtOrgnLoc.contains("-"))
                        {
                            sttnFromCode=txtOrgnLoc.substring(txtOrgnLoc.indexOf("-")+2,txtOrgnLoc.indexOf("("));
                            System.out.println("sttnFromCode"+sttnFromCode);
                           
                        }
                    }else if(selOrigClstType.equals("D")){
                        if(txtOrgnLoc.contains("-"))
                        {
                            sttnFromCode=txtOrgnLoc.substring(txtOrgnLoc.indexOf("-")+1);
                            System.out.println("sttnFromCode"+sttnFromCode);
                          
                        }
                    }
                }
                
                if(txtDstnLoc!=null && !txtDstnLoc.equals("")){
                    System.out.println("txtDstnLoc"+txtDstnLoc);
                    if(selDstnClstType.equals("S")){
                        if(txtDstnLoc.contains("(") && txtDstnLoc.contains(")") && txtDstnLoc.contains("-"))
                        {
                            sttnToCode=txtDstnLoc.substring(txtDstnLoc.indexOf("-")+2,txtDstnLoc.indexOf("("));
                            System.out.println("sttnToCode"+sttnToCode);
                           
                        }
                    }else if(selDstnClstType.equals("D")){
                        if(txtDstnLoc.contains("-"))
                        {
                            sttnToCode=txtDstnLoc.substring(txtDstnLoc.indexOf("-")+1);
                            System.out.println("sttnToCode"+sttnToCode);
                          
                        }
                    }
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
                   // return strReply;
                }
                
                try
                {
                        RTSAHAYPRTL.tuxInit("RTSAHAYPRTL");
                
                RTSAHAYPRTL.setString("F_USERID",   0,   si_UserId);
                RTSAHAYPRTL.setString("F_CLNTID",   0,   si_ClntId);
                RTSAHAYPRTL.setString("F_FLAG",     0,   "SAVE_SAHYIA");
               RTSAHAYPRTL.setString("F_COMMITFLAG",     0,   "Y");
                RTSAHAYPRTL.setString("F_ORDRBY1",     0,   custName);
                RTSAHAYPRTL.setString("F_ORDRBY2",     0,   mob);
                RTSAHAYPRTL.setString("F_ORDRBY3",     0,   mailId);
                RTSAHAYPRTL.setString("F_ORDRBY4",     0,   compName);
                RTSAHAYPRTL.setString("F_ORDRBY5",     0,   compType);        
                RTSAHAYPRTL.setString("F_ORDRBY6",     0,   sttnFromCode); 
                RTSAHAYPRTL.setString("F_ORDRBY7",     0,   sttnToCode);     
                RTSAHAYPRTL.setString("F_ORDRBY8",     0,   txtRakeCmdt); 
                RTSAHAYPRTL.setString("F_ORDRBY9",     0,   selPkgCndn);
                RTSAHAYPRTL.setString("F_ORDRBY10",     0,   txtVol+" "+selVol);
                RTSAHAYPRTL.setString("F_RPWBNUMB",     0,   selPerd);
                RTSAHAYPRTL.setString("F_RPWBNUMB1",     0,   "");
                RTSAHAYPRTL.setString("F_RPWBNUMB2",     0,   txtRmrk);
                RTSAHAYPRTL.setString("F_CHRGDESC",     0,   selOrigClstType);
                RTSAHAYPRTL.setString("F_CHRGTYPE",     0,   selDstnClstType);
                
                
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
                        logger.info("Sucessfull Execution of ConcernGeneration ||RTSAHAYPRTL");
                logger.info("strReply:"+strReply);
                String jsontxt="[{\"reply\":\""+strReply+"\",\"errorC\":\""+erorCode+"\"}]";
                System.out.println("jsontxt"+jsontxt);
                String message="";
                if(erorCode==null) erorCode="";
                
                if(erorCode.equals(""))
                {
                    message=strReply;
                    logger.info("message:-"+message);
                    request.setAttribute("message", message);
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/TransportResponse.jsp");
                    dispatcher.forward(request, response);
                    
                }else{
                        message=erorCode;
                        logger.info("message:-"+message);
                        request.setAttribute("message", message);
                        
                        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/TransportWithUs.jsp");
                        dispatcher.forward(request, response);
                    
                    }
                
               // response.getWriter().write(jsontxt);
            }
    
}
