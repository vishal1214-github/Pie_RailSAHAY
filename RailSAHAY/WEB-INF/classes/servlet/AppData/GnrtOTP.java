package servlet.AppData;


import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;


public class GnrtOTP extends HttpServlet {
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
    static Logger logger = FoisLogger.getLogger(GnrtOTP.class.getName());
    static Connection currentCon = null;
    static ResultSet rs = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        String rgtnid = "";
        String mesg = "";
        String mobl = "";
        String altmobl="";
        rgtnid = request.getParameter("user");
        altmobl = request.getParameter("altr");
        logger.info("User Registration ID::" + rgtnid);
        //check whether customer id exist with NN status
        //get mobile number
        //send sms
        //mask mobile number
        // write mesg to response

        currentCon = dbConnection.getConnection();
        PreparedStatement ps=null;
        try {
            if(altmobl.equals("P"))
            {
            ps =
 currentCon.prepareStatement("SELECT TANCUSTMOBLNUMB MOBL FROM TEL_FBDCUSTLGIN WHERE TAVCUSTID= ? AND TAVCUSTSTTS='NN'");
            }
            ps.setString(1, rgtnid);
            rs = ps.executeQuery();
            //  logger.info("SELECTED Record Count" + rs.getRow());
            int row = 0;
            while (rs.next()) {
                
                mobl = (rs.getString("MOBL")==null)? "NO":rs.getString("MOBL");
                logger.info("Mobile::" + mobl);
                row++;

            }
            if (rs != null) {
                logger.info("rs exists1.");
                rs.close();
            }
            if (ps != null) {
                logger.info("ps exists1.");
                ps.close();
            }
            currentCon.close();
            logger.info("SELECTED Record Count" + row);
            if (row > 0 && !(mobl.equals("NO"))) {
                efntOTP otp = new efntOTP();
                String result = otp.efntOTPSend(rgtnid, mobl, "N", "");
                if (!result.equals("Y")) {
                    logger.info("OTP NOT SENT SUCCESSFULLY");
                    mesg = "SYSTEM IS NOT AVAILABLE PLEASE TRY AFTER SOME TIME.";
                } else {
                    mesg =
"Verification code has been sent to your registered mobile number" + mobl.substring(0,2) + "XXXX" + mobl.substring(6);
                }
            }
            else {
                mesg =
                "Invalid Details.";
            }

        } catch (SQLException e) {
            mesg = "SYSTEM IS NOT READY PLEASE TRY AFTER SOMETIME.";
            try {
                currentCon.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            e.printStackTrace();
        }finally {
    try { if (rs != null) rs.close(); } catch (Exception e) {};
    try { if (ps != null) ps.close(); } catch (Exception e) {};
    try { if (currentCon != null) currentCon.close(); } catch (Exception e) {};
}


        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        out.write(mesg);
        out.close();
        NDC.pop();
        NDC.remove();
    }
}
