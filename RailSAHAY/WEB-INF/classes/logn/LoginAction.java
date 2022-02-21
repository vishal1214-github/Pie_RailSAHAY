package logn;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.LoginServiceClient;

import java.util.Enumeration;

import org.apache.log4j.Logger;

import user.UserProfDtls;

import util.logger.FoisLogger;


public class LoginAction extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(LoginAction.class.getName());
 
    public LoginAction() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        logger.info("email:-"+email);
        LoginServiceClient userLogin = new LoginServiceClient();
         
        try {
            Boolean uservalid = userLogin.verifyUser(email, password);
            String destPage = "pages/eDmndLogin.jsp";
            logger.info("uservalid:-"+uservalid);
            HttpSession session=null;
            try
            {
                    session = request.getSession(false);
                    if(session!=null)
                    {
                            session.invalidate();
                    }
            }
            catch(Exception e)
            {
                    //
            }
            session = request.getSession(true);
            
            if (uservalid) {
               // HttpSession session = request.getSession();
                session.setAttribute("custuserid", email.toUpperCase());
                logger.info("successful login");
                UserProfDtls userdtls = new UserProfDtls( email.toUpperCase());
                String custCtgr = userdtls.getTavcustctgr();
                session.setAttribute("custCtgr", custCtgr);                
                //session.setAttribute("psorglist", userdtls.getPrimseccustOrgn());    
                session.setAttribute("custtype", userdtls.getCusttypeflag());   
                
                destPage = "pages/SAHAYDashboard.jsp";
            } else {
                String message = "Invalid User Name/password";
                logger.info("message:-"+message);
                request.setAttribute("message", message);
            }
            logger.info("destPage:-"+destPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}