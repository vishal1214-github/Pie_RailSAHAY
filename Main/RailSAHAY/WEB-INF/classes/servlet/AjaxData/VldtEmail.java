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


public class VldtEmail extends HttpServlet {
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
        PreparedStatement ps=null;
        String email=(String)request.getParameter("email");
        System.out.println("Reached in Server::"+email);
        try {
            currentCon = dbConnection.getConnection();
             ps =
                currentCon.prepareStatement("SELECT count(1) EXST FROM TEM_FBDCUSTLGIN WHERE  TAVCUSTEMALID =? AND SUBSTR(TAVCUSTSTTS,2,1) NOT IN ('N','D','S') ");
            ps.setString(1, email.toUpperCase());
              rs = ps.executeQuery();
            int alrdexst = 0;
            while (rs.next()) {
                alrdexst = rs.getInt("EXST");
            //    logger.info("Record Count::" + alrdexst);

            }
            if (rs != null) {
         //       logger.info("rs exists.");
                rs.close();
            }
            if (ps != null) {
               // logger.info("ps exists2.");
                ps.close();
            }
            if (alrdexst > 0) {
                out.write("N");
                out.close();
            } else {
                    out.write("Y");
                    out.close();
                }
            currentCon.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {};
        try { if (ps != null) ps.close(); } catch (Exception e) {};
        try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
        }

       
    }
}
