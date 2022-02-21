package servlet.AppData;



import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import util.exception.GG_Exception;

import util.jdbc.dbConnection;

import util.logger.FoisLogger;


public class ActvtUserOtp extends HttpServlet {
    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";
    static Logger logger = FoisLogger.getLogger(ActvtUserOtp.class.getName());
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
        String user = "";
        String otp="";
        String email="";
        String dvsn ="";
        String fullname="";
        rgtnid = request.getParameter("user");
        otp = request.getParameter("otp");
        email = request.getParameter("email");
        fullname = request.getParameter("fullname");
        logger.info("User Registration ID::" + rgtnid+"::OTP::"+otp+"::EMAIL::"+email);
        currentCon = dbConnection.getConnection();
        PreparedStatement ps=null;
        int intvl=0;
        try {
            ps =
            currentCon.prepareStatement(" SELECT TO_NUMBER(SAVVALU) INTVL FROM SEM_DATEPARAM     " +
                "WHERE SADEFCTFROM <= SYSDATE         " +
                "AND NVL(SADEFCTTO,SYSDATE)>=SYSDATE    " +
                "AND  SAVCODE='OTP_VLDT_TIME'");
            rs = ps.executeQuery();
            while (rs.next()) {
                
                   intvl = rs.getInt("INTVL");
                logger.info("SELECTED Record Count" + intvl);
                    
            }
            if (rs != null) {
                logger.info("rs exists3.11.");
                rs.close();
            }
             if (ps != null) {
              //   logger.info("rs exists3.11.");
                 ps.close();
             }
             ps =
             currentCon.prepareStatement("SELECT TAVCUSTID CUST FROM TEL_FBDCUSTLGIN WHERE TAVCUSTID= ? AND UPPER(TAVCUSTEMALID) =? AND TAVCUSTSTTS='AV'");
             ps.setString(1, rgtnid);
             ps.setString(2, email.toUpperCase());
             rs = ps.executeQuery();
             //  logger.info("SELECTED Record Count" + rs.getRow());
              int row1 = 0;
             while (rs.next()) {
                 
                 user = rs.getString("CUST");
                 logger.info("USERs::" + user);
                 row1++;

             }
             if (rs != null) {
                 logger.info("rs exists1.");
                 rs.close();
             }
              if (ps != null) {
               //   logger.info("rs exists3.11.");
                  ps.close();
              }
             logger.info("SELECTED Record Count" + row1);
             if(row1>0) {
                 mesg="Your eMail and Mobile Number have been Already verified and your request for registration with IR/Freight Services has been accepted.Now you can Login in FBD Portal.";
             }else
             {
            ps =
        currentCon.prepareStatement("SELECT TAVOTP OTP FROM TED_EFNTOTP WHERE TAVCUSTID= ? AND TAVOTP =? AND TACVLDTFLAG='Y' AND ((SYSDATE-TADLASTSENDTIME) *24*60)<= ? ");
            ps.setString(1, rgtnid);
            ps.setString(2, otp);
            ps.setInt(3, intvl);
            rs = ps.executeQuery();
            //  logger.info("SELECTED Record Count" + rs.getRow());
            int row = 0;
            while (rs.next()) {
                
                user = rs.getString("OTP");
                logger.info("OTP::" + user);
                row++;

            }
            if (rs != null) {
                logger.info("rs exists1.");
                rs.close();
            }
             if (ps != null) {
              //   logger.info("rs exists3.11.");
                 ps.close();
             }
            logger.info("SELECTED Record Count" + row);
            if(row==0) {
                mesg="OTP IS NOT VALID.";
            }else {
                ps =
                currentCon.prepareStatement("SELECT TAVCUSTID CUST FROM TEL_FBDCUSTLGIN WHERE TAVCUSTID= ? AND UPPER(TAVCUSTEMALID) =? AND TAVCUSTSTTS='NN'");
                ps.setString(1, rgtnid);
                ps.setString(2, email.toUpperCase());
                rs = ps.executeQuery();
                //  logger.info("SELECTED Record Count" + rs.getRow());
                 row = 0;
                while (rs.next()) {
                    
                    user = rs.getString("CUST");
                    logger.info("USERs::" + user);
                    row++;

                }
                if (rs != null) {
                    logger.info("rs exists1.");
                    rs.close();
                }
                if (ps != null) {
                 //   logger.info("rs exists3.11.");
                    ps.close();
                }
                //currentCon.close();
                if(row>0) {
                    boolean confirmSuccess;
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("uname",rgtnid);
                    params.put("email", email.toUpperCase());
                    params.put("fullname", fullname.toUpperCase());
                    try {
                        confirmSuccess = confirmRegistration(params);
                        if(confirmSuccess) {
                            //mesg="User Account in e-Demand Module of IR/FOIS has been activated. Kindly Visit The Divisional Office and get your Identity and Documents verified.";
                            mesg="Congratulations!!Your eMail and Mobile Number have been verified and your request for registration with IR/Freight Services has been accepted. Now You can Login in FBD Portal using your Credentials.";
                        
                        }else{
                            mesg="Some error occured. Please try after some time.";
                        }
                    } catch (GG_Exception e) {
                        mesg="USER ACTIVATION FAILED.";
                    }
                    
                }else {
                    mesg="No such user is pending for Activation.";
                }
            }
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
    public String fetchCode(String desc)
    {
        try
        {
            if(desc == null)
                desc = "";
            else
            if(desc.indexOf("(") != -1)
                desc = desc.substring(desc.indexOf('(') + 1, desc.indexOf(')'));
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, (new StringBuilder()).append("Unable to fetch code from ").append(desc).toString(), null));
            e.printStackTrace();
        }
        return desc;
    }
    public static boolean confirmRegistration(Map<String, String> paramMap) throws GG_Exception {
        boolean cnfmact = false;
        PreparedStatement ps = null;
        int rowcont = 0;

        //currentCon = dbConnection.getConnection();
        
        String sql =
            "UPDATE TEL_FBDCUSTLGIN SET TAVCUSTSTTS='AV',TADLASTSTTSDATE = sysdate  WHERE TAVCUSTID ='" + paramMap.get("uname") + "' and TAVCUSTSTTS='NN' AND" +
            " UPPER(TAVCUSTEMALID) ='" + paramMap.get("email").toUpperCase() + "'";

        logger.info("UPDATE QUERY::" + sql);
        try {
            currentCon.setAutoCommit(false);
            Statement stmt = currentCon.createStatement();
            if (stmt.execute(sql)) {
                logger.info("User Registration Confirmation Failed");
                currentCon.rollback();
                stmt.close();
                currentCon.close();
                cnfmact = false;

            } else
                    {
                    logger.info("User Registration Confirmation Success");
                    ps =
                        currentCon.prepareStatement("UPDATE TED_EFNTOTP SET TACVLDTFLAG ='N'WHERE TAVCUSTID=? AND TACVLDTFLAG='Y'");
                    ps.setString(1, paramMap.get("uname"));
                    int updtcnt = ps.executeUpdate();
                    //with zero update count nothing to do
                    if (rs != null) {
                        logger.info("rs exists1.");

                        rs.close();
                    }
                    logger.info("UPDATE COMPLETE");
                    
                    try {
                        ps =
                            currentCon.prepareStatement(" INSERT INTO TEM_FBDCUSTLGIN ( " + " TAVCUSTID           ,             " +
                                                        " TAVCUSTEMALID         ,             " +
                                                        " TANCUSTMOBLNUMB         ,             " +
                                                        " TAVCUSTPSWD       ,             " +
                                                        " TAVCUSTFRSTNAME     ,             " +
                                                        " TAVCUSTLASTNAME     ,             " + " TAVCUSTSTTS     ,             " +
                                                        " TADLASTSTTSDATE         ,             " +
                                                        " TAVCUSTUSERID         ,             " +
                                                        " TADEFCTFROM      ,             " + " TADEFCTTO   ,             " +
                                                        " TADPSWDEXPYDATE ,             " + " TADLASTLGINDATE      ,             " +
                                                        " TANUNSCLGIN   )                               " +
                                                        " SELECT TAVCUSTID             ,    " +
                                                        "        TAVCUSTEMALID           ,    " +
                                                        "        TANCUSTMOBLNUMB           ,    " +
                                                        "        TAVCUSTPSWD         ,    " +
                                                        "        TAVCUSTFRSTNAME       ,    " +
                                                        "        TAVCUSTLASTNAME       ,    " + "        TAVCUSTSTTS       ,    " +
                                                        "        TADLASTSTTSDATE           ,    " +
                                                        "        TAVCUSTUSERID           ,    " +
                                                        "        TADEFCTFROM        ,    " + "        TADEFCTTO     ,    " +
                                                        "        TADPSWDEXPYDATE   ,    " + "        TADLASTLGINDATE        ,    " +
                                                        "        TANUNSCLGIN           " +
                                                        "        FROM TEL_FBDCUSTLGIN            where TAVCUSTID=?       ");
                                ps.setString(1, paramMap.get("uname"));
                                rowcont = ps.executeUpdate();
                        logger.info("Inserted Record Count" + rowcont);
                        if (ps != null) {
                            logger.info("ps exists");
                            ps.close();
                        }
                    } catch (SQLException e) {
                        logger.info("User Registration Confirmation Failed"+e.getMessage());
                        currentCon.rollback();
                        ps.close();
                        currentCon.close();
                        cnfmact = false;
                    }
                
                
                    currentCon.commit();
                 cnfmact = true;
                }
        } catch (Exception ex) {
            logger.info("Registration Confirmation Exception! " + ex);

            try {
                currentCon.rollback();
                currentCon.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new GG_Exception("ERROR IN CONFIRMATION. TRY AFTER SOME TIME.", e);
                // e.printStackTrace();
            }
            cnfmact = false;
        } finally {

            try {
                currentCon.close();
                logger.info("Call Completed.");
                NDC.pop();
                NDC.remove();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return cnfmact;
    }
}
