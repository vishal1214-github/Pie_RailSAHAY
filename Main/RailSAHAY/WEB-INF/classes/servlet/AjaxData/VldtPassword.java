package servlet.AjaxData;


import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.jdbc.dbConnection;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VldtPassword extends HttpServlet {
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
    static Connection currentCon = null; 
    static ResultSet rs = null;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
      //  System.out.println("Inside chkAvailability" + tavcustuserid);
       
        String password=(String)request.getParameter("password");
        System.out.println("Reached in Server::"+password);
        if(!isValidPassword(password)) {
            out.write("N");
            out.close();
        }
        else{
            out.write("Y");
            out.close();
        }
                

       
    }
    // Function to validate the password.
        public static boolean
        isValidPassword(String password)
        {
      
            // Regex to check valid password.
            String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
      
            // Compile the ReGex
            Pattern p = Pattern.compile(regex);
      
            // If the password is empty
            // return false
            if (password == null) {
                return false;
            }
      
            // Pattern class contains matcher() method
            // to find matching between given password
            // and regular expression.
            Matcher m = p.matcher(password);
      
            // Return if the password
            // matched the ReGex
            return m.matches();
        }
}
