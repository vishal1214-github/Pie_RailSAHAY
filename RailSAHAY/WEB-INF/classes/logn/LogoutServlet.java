package logn;

import java.io.IOException;  
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.logger.FoisLogger;

public class LogoutServlet extends HttpServlet {  
        static Logger logger = FoisLogger.getLogger(LogoutServlet.class.getName());
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
                
            String destPage = "index.jsp";
            HttpSession session=request.getSession();  
            session.invalidate();  
              
            String message = "You are successfully logged out!";
            logger.info("message:-"+message);
            request.setAttribute("message", message);
            
            logger.info("destPage:-"+destPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
              
             
    }  
}  
