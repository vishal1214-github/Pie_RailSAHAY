package logn;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import model.LoginServiceClient;

import java.util.Enumeration;

import org.apache.log4j.Logger;

import user.FBDUserProf;
import user.UserProfDtls;

import util.logger.FoisLogger;


public class FBDLogin extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(FBDLogin.class.getName());
 
    public FBDLogin() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        logger.info("email:-"+email);
        LoginServiceClient userLogin = new LoginServiceClient();
         
        try {
            Boolean uservalid = userLogin.verifyFBD(email, password);
            String destPage = "/pages/FBDLogin.jsp";
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
                FBDUserProf userdtls = new FBDUserProf( email.toUpperCase());
                String custId = userdtls.getTavcustid();
                session.setAttribute("custId", custId);  
                session.setAttribute("email", userdtls.getTavcustemalid());  
                session.setAttribute("name", userdtls.getTavcustfrstname()+  " "+ userdtls.getTavcustlastname());  
                session.setAttribute("mobile", userdtls.getTancustmoblnumb());  
                //session.setAttribute("psorglist", userdtls.getPrimseccustOrgn());    
                //session.setAttribute("custtype", userdtls.getCusttypeflag());   
                
                destPage = "/pages/AppDashboard.jsp";
            } else {
                String message = "Invalid User Name/password";
                logger.info("message:-"+message);
                session.setAttribute("message", message);
            }
            logger.info("destPage:-"+destPage);
            //RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            //dispatcher.forward(request, response);
            response.sendRedirect(request.getContextPath()+ destPage);
             
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}