package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.jdbc.dbConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import logn.LoginAction;

import org.apache.log4j.Logger;

import util.logger.FoisLogger;

@ViewScoped
@ManagedBean(name = "fbduserprof")
public class FBDUserProf {
    static Logger logger = FoisLogger.getLogger(FBDUserProf.class.getName());
    private static final long serialVersionUID = 1L;
    private String pswdstts;
    private String tavcustid;
    private String tavcustpswd;
    private String tavcustemalid;
    private String tancustmoblnumb;
    private String tavcustfrstname;    
    private String tavcustlastname;
    private String tavcuststts;
    private String tavcustuserid;
    private String tadefctfrom;
    private String tadefctto;
    private String tadpswdexpydate;
    private String tadcustdeltdate;
    private String tadcustsuspdate;
    static Connection currentCon = null;
    static ResultSet rs = null;
    public FBDUserProf() {
        super();
    }


    public FBDUserProf(String tavcustuserid){
        this.tavcustuserid = tavcustuserid.toUpperCase();
        /*
                 * Call database and retrieve all values of user Profile and registered
                 * Documents
                 */
        if (!(getUserPassChck(this.tavcustuserid))) {
            this.pswdstts = "N";
        }       
        logger.info("tavcustuserid1:-"+tavcustuserid);
        getUserProfdata(tavcustuserid);
    }
    private boolean getUserPassChck(String userid) {
        PreparedStatement ps =null;
        
        try {
            currentCon = dbConnection.getConnection();
             ps =
                currentCon.prepareStatement("SELECT COUNT(1) CNT FROM TEM_FBDCUSTLGIN WHERE TADPSWDEXPYDATE>SYSDATE AND   TAVCUSTUSERID=?     ");

            ps.setString(1, this.tavcustuserid);

            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i = rs.getInt("CNT");
            }
           // logger.info("VALID ROW." + i+"::UserId::"+this.tavcustuserid);
            if (rs != null) {
            //    logger.info("NO NO");
                rs.close();
            }
            currentCon.close();
            if (i == 0) {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
              try { if (ps != null) ps.close(); } catch (Exception e) {};
            try {
                if (currentCon != null)
                    currentCon.close();
            //    logger.info("FINALLY CLOSED THE CONNECTION");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }
    private void getUserProfdata(String tavcustuserid) {
        /**
         * Getting master data of a user from data tables User categaory is important for retriveing
         * Organization Infromation*/
        logger.info("tavcustuserid:-"+tavcustuserid);
        PreparedStatement ps =null;
        try {
            currentCon = dbConnection.getConnection();
             ps =
                currentCon.prepareStatement("SELECT TAVCUSTID         ,"  +
                                            "TAVCUSTPSWD              ," + "TAVCUSTEMALID            ," +
                                            "TANCUSTMOBLNUMB          ," + "TAVCUSTFRSTNAME          ," +
                                            "TAVCUSTLASTNAME          ,"  +
                                            "TAVCUSTSTTS              ," +
                                            "TAVCUSTUSERID            ,"  +
                                            "TADEFCTFROM              ," +
                                            "TADEFCTTO                ," + "TADPSWDEXPYDATE          " +
                                            "FROM TEM_FBDCUSTLGIN   WHERE SUBSTR(TAVCUSTSTTS,2,1) NOT IN ('N','D','S') AND   TAVCUSTUSERID=?     ");

            ps.setString(1, tavcustuserid);

            rs = ps.executeQuery();

            while (rs.next()) {
                this.tavcustid = rs.getString("TAVCUSTID");
                this.tavcustemalid = rs.getString("TAVCUSTEMALID");
                this.tancustmoblnumb = rs.getString("TANCUSTMOBLNUMB");
                this.tavcustfrstname = rs.getString("TAVCUSTFRSTNAME");
                this.tavcustlastname = rs.getString("TAVCUSTLASTNAME");
                this.tavcuststts = rs.getString("TAVCUSTSTTS");
                this.tadefctfrom = rs.getString("TADEFCTFROM");
                this.tadefctto = rs.getString("TADEFCTTO");
                this.tadpswdexpydate = rs.getString("TADPSWDEXPYDATE");
                setTavcustuserid(tavcustuserid);
           //     logger.info("Data Filled in variables.." + this.tavcustuserid);

            }
            if (rs != null) {
             //   logger.info("NO NO");
                rs.close();
            }
            currentCon.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
              try { if (ps != null) ps.close(); } catch (Exception e) {};
          //  try { if (ps1 != null) ps1.close(); } catch (Exception e) {};
          //  try { if (ps2 != null) ps2.close(); } catch (Exception e) {};
            try {
                if (currentCon != null)
                    currentCon.close();
             //   logger.info("FINALLY CLOSED THE CONNECTION");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void setTavcustuserid(String tavcustuserid) {
        this.tavcustuserid = tavcustuserid;
    }

    public String getTavcustuserid() {
        return tavcustuserid;
    }
    public void setPswdstts(String pswdstts) {
        this.pswdstts = pswdstts;
    }

    public String getPswdstts() {
        return pswdstts;
    }

    public void setTavcustid(String tavcustid) {
        this.tavcustid = tavcustid;
    }

    public String getTavcustid() {
        return tavcustid;
    }

    public void setTavcustpswd(String tavcustpswd) {
        this.tavcustpswd = tavcustpswd;
    }

    public String getTavcustpswd() {
        return tavcustpswd;
    }

    public void setTavcustemalid(String tavcustemalid) {
        this.tavcustemalid = tavcustemalid;
    }

    public String getTavcustemalid() {
        return tavcustemalid;
    }

    public void setTancustmoblnumb(String tancustmoblnumb) {
        this.tancustmoblnumb = tancustmoblnumb;
    }

    public String getTancustmoblnumb() {
        return tancustmoblnumb;
    }

    public void setTavcustfrstname(String tavcustfrstname) {
        this.tavcustfrstname = tavcustfrstname;
    }

    public String getTavcustfrstname() {
        return tavcustfrstname;
    }

    public void setTavcustlastname(String tavcustlastname) {
        this.tavcustlastname = tavcustlastname;
    }

    public String getTavcustlastname() {
        return tavcustlastname;
    }
    public void setTavcuststts(String tavcuststts) {
        this.tavcuststts = tavcuststts;
    }

    public String getTavcuststts() {
        return tavcuststts;
    }

    public void setTadefctfrom(String tadefctfrom) {
        this.tadefctfrom = tadefctfrom;
    }

    public String getTadefctfrom() {
        return tadefctfrom;
    }

    public void setTadefctto(String tadefctto) {
        this.tadefctto = tadefctto;
    }

    public String getTadefctto() {
        return tadefctto;
    }

    public void setTadpswdexpydate(String tadpswdexpydate) {
        this.tadpswdexpydate = tadpswdexpydate;
    }

    public String getTadpswdexpydate() {
        return tadpswdexpydate;
    }

    public void setTadcustdeltdate(String tadcustdeltdate) {
        this.tadcustdeltdate = tadcustdeltdate;
    }

    public String getTadcustdeltdate() {
        return tadcustdeltdate;
    }

    public void setTadcustsuspdate(String tadcustsuspdate) {
        this.tadcustsuspdate = tadcustsuspdate;
    }

    public String getTadcustsuspdate() {
        return tadcustsuspdate;
    }

    public static void setCurrentCon(Connection currentCon) {
        FBDUserProf.currentCon = currentCon;
    }

    public static Connection getCurrentCon() {
        return currentCon;
    }

    public static void setRs(ResultSet rs) {
        FBDUserProf.rs = rs;
    }

    public static ResultSet getRs() {
        return rs;
    }



}
