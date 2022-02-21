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
@ManagedBean(name = "userprof")
public class UserProfDtls {
    static Logger logger = FoisLogger.getLogger(UserProfDtls.class.getName());
    private static final long serialVersionUID = 1L;
    private String pswdstts;
    private String tavcustid;
    private String tavcustctgr;
    private String tavcustpswd;
    private String tavcustemalid;
    private String tancustmoblnumb;
    private String tavcustfrstname;    
    private String tavcustlastname;
    private String taccustgndr;
    private String tavcustaddr;
    private String tancustpincode;
    private String tavcustscndmailid;
    private String tancustscndmoblnumb;
    private String tavcustidnumb1;
    private String tavcustiddocid1;
    private String tavcustidnumb2;
    private String tavcustiddocid2;
    private String tavcustidnumb3;
    private String tavcustiddocid3;
    private String tadcustdob;
    private String tavcusthintquestid1;
    private String tavcusthintans1;
    private String tavcusthintquestid2;
    private String tavcusthintans2;
    private String tadcustapvldate;
    private String tavcuststts;
    private String tadlaststtsdate;
    private String tavcustuserid;
    private String tavrolecode;
    private String tavdgtlsignflag;
    private String tadefctfrom;
    private String tadefctto;
    private String tadpswdexpydate;
    private String tadcustdeltdate;
    private String tadcustsuspdate;
    private String uploadedmsgcont="";
    private transient ArrayList<orgnsttn> lstorgnsttn;
    private transient orgnsttn objorgnsttn;
    private transient ArrayList<orgndoc> lstorgndoc;
    private transient orgndoc objorgndoc;
    static Connection currentCon = null;
    static ResultSet rs = null;
    private ArrayList<String> custOrgn;
    private ArrayList<ArrayList<String>> primseccustOrgn;
    private String custtypeflag;
    public UserProfDtls() {
        super();
    }

    public void setCustOrgn(ArrayList<String> custOrgn) {
        this.custOrgn = custOrgn;
    }

    public ArrayList<String> getCustOrgn() {
        return custOrgn;
    }

    public UserProfDtls(String tavcustuserid){
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
                currentCon.prepareStatement("SELECT COUNT(1) CNT FROM TEM_CUSTMSTR WHERE TADPSWDEXPYDATE>SYSDATE AND   TAVCUSTUSERID=?     ");

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
        PreparedStatement ps1 =null;
        PreparedStatement ps2 =null;
        try {
            currentCon = dbConnection.getConnection();
             ps =
                currentCon.prepareStatement("SELECT TAVCUSTID         ," + "TAVCUSTCTGR              ," +
                                            "TAVCUSTPSWD              ," + "TAVCUSTEMALID            ," +
                                            "TANCUSTMOBLNUMB          ," + "TAVCUSTFRSTNAME          ," +
                                            "TAVCUSTLASTNAME          ," + "TACCUSTGNDR              ," +
                                            "TAVCUSTADDR              ," + "TANCUSTPINCODE           ," +
                                            "TAVCUSTSCNDMAILID        ," + "TANCUSTSCNDMOBLNUMB      ," +
                                            "TAVCUSTIDNUMB1           ," + "TAVCUSTIDDOCID1          ," +
                                            "TAVCUSTIDNUMB2           ," + "TAVCUSTIDDOCID2          ," +
                                            "TO_CHAR(TADCUSTDOB,'DD-MM-YYYY')  TADCUSTDOB             ," +
                                            "M1.MAVQUESTDESC TAVCUSTHINTQUESTID1      ," +
                                            "TAVCUSTHINTANS1          ," +
                                            "M2.MAVQUESTDESC TAVCUSTHINTQUESTID2      ," +
                                            "TAVCUSTHINTANS2          ," + "TADCUSTAPVLDATE          ," +
                                            "TAVCUSTSTTS              ," + "TADLASTSTTSDATE          ," +
                                            "TAVCUSTUSERID            ," + "TAVROLECODE              ," +
                                            "TAVDGTLSIGNFLAG          ," + "TADEFCTFROM              ," +
                                            "TADEFCTTO                ," + "TADPSWDEXPYDATE          ," +
                                            "TADCUSTDELTDATE          ," + "TAVCUSTIDNUMB3          ," +
                                            "TAVCUSTIDDOCID3          ," +
                                            "TADCUSTSUSPDATE FROM TEM_CUSTMSTR,MEMHINTQUEST M1,MEMHINTQUEST M2    WHERE TAVCUSTHINTQUESTID1=M1.MAVQUESTID  AND TAVCUSTHINTQUESTID2=M2.MAVQUESTID AND SUBSTR(TAVCUSTSTTS,2,1) NOT IN ('N','D','S') AND   TAVCUSTUSERID=?     ");

            ps.setString(1, tavcustuserid);

            rs = ps.executeQuery();

            while (rs.next()) {
                this.tavcustid = rs.getString("TAVCUSTID");
                this.tavcustctgr = rs.getString("TAVCUSTCTGR");

                this.tavcustemalid = rs.getString("TAVCUSTEMALID");
                this.tancustmoblnumb = rs.getString("TANCUSTMOBLNUMB");
                this.tavcustfrstname = rs.getString("TAVCUSTFRSTNAME");
                this.tavcustlastname = rs.getString("TAVCUSTLASTNAME");
                this.taccustgndr = rs.getString("TACCUSTGNDR").equals("M") ? "MALE" : "FEMALE";
                this.tavcustaddr = rs.getString("TAVCUSTADDR");
                this.tancustpincode = rs.getString("TANCUSTPINCODE");
                this.tavcustscndmailid =
                        (rs.getString("TAVCUSTSCNDMAILID") == null) ? "" : rs.getString("TAVCUSTSCNDMAILID");
                this.tancustscndmoblnumb =
                        (rs.getString("TANCUSTSCNDMOBLNUMB") == null) ? "" : rs.getString("TANCUSTSCNDMOBLNUMB");
                this.tavcustidnumb1 = rs.getString("TAVCUSTIDNUMB1");
                this.tavcustiddocid1 = rs.getString("TAVCUSTIDDOCID1");
                this.tavcustidnumb3 = rs.getString("TAVCUSTIDNUMB3");
                this.tavcustiddocid3 = rs.getString("TAVCUSTIDDOCID3");
                this.tavcustidnumb2 = rs.getString("TAVCUSTIDNUMB2");
                this.tavcustiddocid2 = rs.getString("TAVCUSTIDDOCID2");
                this.tadcustdob = rs.getString("TADCUSTDOB");
                this.tavcusthintquestid1 = rs.getString("TAVCUSTHINTQUESTID1");
                this.tavcusthintans1 = rs.getString("TAVCUSTHINTANS1");
                this.tavcusthintquestid2 = rs.getString("TAVCUSTHINTQUESTID2");
                this.tavcusthintans2 = rs.getString("TAVCUSTHINTANS2");
                this.tadcustapvldate = rs.getString("TADCUSTAPVLDATE");
                this.tavcuststts = rs.getString("TAVCUSTSTTS");
                this.tadlaststtsdate = rs.getString("TADLASTSTTSDATE");

                this.tavrolecode = rs.getString("TAVROLECODE");
                this.tavdgtlsignflag = rs.getString("TAVDGTLSIGNFLAG");
                this.tadefctfrom = rs.getString("TADEFCTFROM");
                this.tadefctto = rs.getString("TADEFCTTO");
                this.tadpswdexpydate = rs.getString("TADPSWDEXPYDATE");
                this.tadcustdeltdate = rs.getString("TADCUSTDELTDATE");
                this.tadcustsuspdate = rs.getString("TADCUSTSUSPDATE");
                setTavcustuserid(tavcustuserid);
           //     logger.info("Data Filled in variables.." + this.tavcustuserid);

            }
            if (rs != null) {
             //   logger.info("NO NO");
                rs.close();
            }
            /* retriving Contract List and Tin  Data from document tables and organization tables */
       /*      ps1 =
                currentCon.prepareStatement("SELECT DISTINCT TAVORGNCODE ORGCODE,TAVDOCID DOCID, MAVORGFULLNAME ORGN,                             " +
                                            "'Authority Contract Letter' DOCNAME,                         " +
                                            "TAVDOCIDNUMB DOCNUMB,                                        " +
                                            "TAVDOCNAME FILENAME,                                         " +
                                            "TAVDOCCNTTTYPE FILECNTT,                                     " +
                                            "TANCNTTSQNC SQNC                                             " +
                                            "FROM TED_CUSTIDDOC  ,MEMORG                                         " +
                                            "WHERE TAVCUSTID =?                           " +
                                            "AND TAVDOCID='CN01'   AND TAVORGNCODE=MAVORGCODE      " +
                                            "AND TAVCUSTID = NVL(MAVCUSTID,TAVCUSTID)                                 " +
                                            "AND TADVLDTFROM    <=SYSDATE                                 " +
                                            "AND NVL(TADVLDTTO,SYSDATE) >=SYSDATE                         " +
                                            "UNION                                                        " +
                                            "SELECT DISTINCT M1.MAVORGCODE ORGCODE,'TIN' DOCID, M1.MAVORGFULLNAME ORGN,                          " +
                                            "'Tin Number' DOCNAME,                                        " +
                                            "M1.MAVORGTIN DOCNUMB ,                                       " +
                                            "M1.MAVORGCODE || '_TIN.pdf' FILENAME ,                       " +
                                            "NULL FILECNTT,                                               " +
                                            "NULL SQNC                                                    " +
                                            " FROM MEMORG M1,MEMCUSTLOCN M2                                " +
                                            " WHERE M1.MAVORGCODE=M2.MAVORGCODE                            " +
                                            " AND M2.MAVCUSTID=?                             " +
                                            " AND M2.MAVCUSTID=NVL( M1.MAVCUSTID,M2.MAVCUSTID)    " +
                                            " AND M2.MACCUSTVERF='Y'                                       " +
                                            " AND ( SUBSTR(M2.MAVCUSTSTTS,2,1) NOT IN ('N','D','S') OR     " +
                                            " (SUBSTR(M2.MAVCUSTSTTS,1,1)='N' ))                           " +
                                            " AND M1.MADEFCTFROM    <=SYSDATE                              " +
                                            " AND NVL(M1.MADEFCTTO,SYSDATE) >= SYSDATE                     " +
                                            " AND M2.MADCUSTEFCTFROM         <=SYSDATE                     " +
                                            " AND NVL(M2.MADCUSTEFCTTO,SYSDATE)          >= SYSDATE        ");
         //   logger.info("this.tavcustid.." + this.tavcustid);
            ps1.setString(1, this.tavcustid);
            ps1.setString(2, this.tavcustid);

            rs = ps1.executeQuery();
            this.lstorgndoc = new ArrayList<orgndoc>();
          //  logger.info("NO NO2");
            int i = 0;
            while (rs.next()) {
                this.objorgndoc = new orgndoc();
                this.objorgndoc.setId("" + ++i);
                this.objorgndoc.setOrgcode(rs.getString("ORGCODE"));
                this.objorgndoc.setOrgn(rs.getString("ORGN"));
                this.objorgndoc.setDocname(rs.getString("DOCNAME"));
                this.objorgndoc.setDocnumb(rs.getString("DOCNUMB"));
                this.objorgndoc.setFilename(rs.getString("FILENAME"));
                this.objorgndoc.setFilecntt(rs.getString("FILECNTT"));
                this.objorgndoc.setSqnc(rs.getString("SQNC"));
                this.objorgndoc.setDocid(rs.getString("DOCID"));
                this.lstorgndoc.add(objorgndoc);

            }
            if (rs != null)
                rs.close(); */

            /* Getting Organization and Loading Station details */
         /*    ps2 =
                currentCon.prepareStatement("SELECT distinct  M1.MAVORGCODE  ORGCODE,                   " + "           M1.MAVORGFULLNAME ORGNAME ,                " +
                                            "        M1.MAVORGTIN     ORGTIN,                  " +
                                            "        M1.MAVORGHQADDR  ORGHQ,                   " +
                                            "        M1.MAVORGHQCNTCNUMB  ORGCNTT  ,           " +
                                            "        M2.MAVORGREGN ORGREGN  ,                  " +
                                            "        M3.MAVORGLDNGLOCN LDNGLOCN  ,           " +
                                            "        DECODE(M3.MEMORGLDNGLOCNTYPE,'Z','ZONE','D','DIVISION','S','STATION') LOCNTYPE             " +
                                            " FROM MEMORG M1,MEMORGDTLS M2,MEMCUSTLOCN M3     " +
                                            " WHERE  M3.MAVCUSTID= ?                           " +
                                            " AND       M3.MACCUSTVERF='Y' " +
                                            " AND M3.MAVCUSTID=NVL( M1.MAVCUSTID,M3.MAVCUSTID)    " +
                                            " AND M3.MAVCUSTID=NVL( M2.MAVCUSTID,M3.MAVCUSTID)    " +
                                            " AND       M3.MAVCUSTSTTS NOT IN ('AN','NN')                " +
                                            " AND       M3.MAVORGCODE=M2.MAVORGCODE                " +
                                            " AND       M3.MAVORGCODE=M1.MAVORGCODE                " +
                                            " AND    M1.MAVORGCODE=M2.MAVORGCODE               " +
                                            " AND       M3.MADCUSTEFCTFROM <= SYSDATE              " +
                                            " AND       NVL(M3.MADCUSTEFCTTO,SYSDATE) >= SYSDATE   " +
                                            " AND       M1.MADEFCTFROM   <= SYSDATE                " +
                                            " AND    NVL(M1.MADEFCTTO,SYSDATE) >= SYSDATE " +
                                            " ORDER BY 1,7,8");
          //  logger.info("For retreving Organtions details custid.." + this.tavcustid);
            ps2.setString(1, this.tavcustid);

            rs = ps2.executeQuery();
            lstorgnsttn = new ArrayList<orgnsttn>();
          //  logger.info("NO NO21");
            while (rs.next()) {
                this.objorgnsttn = new orgnsttn();
                this.objorgnsttn.setLocntype(rs.getString("LOCNTYPE"));
                this.objorgnsttn.setLdnglocn(rs.getString("LDNGLOCN"));
                this.objorgnsttn.setOrgcntt(rs.getString("ORGCNTT"));
                this.objorgnsttn.setOrgcode(rs.getString("ORGCODE"));
                this.objorgnsttn.setOrghq(rs.getString("ORGHQ"));
                this.objorgnsttn.setOrgname(rs.getString("ORGNAME"));
                this.objorgnsttn.setOrgregn(rs.getString("ORGREGN"));
                this.objorgnsttn.setOrgtin(rs.getString("ORGTIN"));
             //   logger.info("LDNGSTTNOBJECT::" + objorgnsttn);
                this.lstorgnsttn.add(objorgnsttn);

            } 
            if (rs != null)
                rs.close(); */
         ps2 = currentCon.prepareStatement("select distinct MAVGLBLCUSTCODE FROM MEMCUSTLOCN,memglblcust "
                                            +" where MAVCUSTID = NVL(?, MAVCUSTID) AND MACORGSTTSFLAG IS NULL  AND MAVGLBLCUSTCODE =  mavorgcode AND MAVEDMNDFLAG='Y' "
                                            +" AND MAVGLBLCUSTCODE  NOT IN ( 'SELF','PUB') AND MACCUSTVERF='Y' AND MAVCUSTSTTS NOT IN ('AN','NN')  AND MADCUSTEFCTFROM <= SYSDATE AND	NVL(MADCUSTEFCTTO,SYSDATE) >= SYSDATE "
            +" UNION ALL"
            +"  SELECT DISTINCT MAVHNDGAGNTCODE FROM MEMHNDGAGNTLOCN,MEMGLBLHNDGAGNT  "
            +"  WHERE MAVCUSTID = NVL(?, MAVCUSTID) AND MACORGSTTSFLAG IS NULL  AND MAVHNDGAGNTCODE =  MAVORGCODE AND MAVEDMNDFLAG='Y'"
            +"  AND MAVHNDGAGNTCODE  NOT IN ( 'SELF','PUB')  AND MACCUSTVERF='Y' AND MAVCUSTSTTS NOT IN ('AN','NN')  AND MADCUSTEFCTFROM <= SYSDATE AND	NVL(MADCUSTEFCTTO,SYSDATE) >= SYSDATE ORDER BY 1");
         logger.info("For retreving Organizations details custid.." + this.tavcustid);
            if(((String)this.tavcustid).equals("CRISDEMO"))
            {
                     ps2.setString(1, "");
                     ps2.setString(2, "");
            }
            else
            {
                ps2.setString(1, this.tavcustid);
                ps2.setString(2, this.tavcustid);
        }
            
                     rs = ps2.executeQuery();
                     custOrgn = new ArrayList<String>();
                   //  logger.info("NO NO21");
                     while (rs.next()) {
                         
                         this.custOrgn.add(rs.getString(1));

                     } 
                     if (rs != null)
                         rs.close();
            
            ps1 = currentCon.prepareStatement
            ("SELECT DISTINCT 'PRIMARY',MAVGLBLCUSTCODE FROM MEMCUSTLOCN,MEMGLBLCUST "
            +" WHERE MAVCUSTID = NVL(?, MAVCUSTID) AND MACORGSTTSFLAG IS NULL  AND MAVGLBLCUSTCODE =  MAVORGCODE AND MAVEDMNDFLAG='Y' "
            +" AND MAVGLBLCUSTCODE  NOT IN ( 'SELF','PUB') AND MACCUSTVERF='Y' AND MAVCUSTSTTS NOT IN ('AN','NN')  AND MADCUSTEFCTFROM <= SYSDATE AND	NVL(MADCUSTEFCTTO,SYSDATE) >= SYSDATE"
            +" UNION ALL"
            +"  SELECT DISTINCT 'SECONDARY',MAVHNDGAGNTCODE FROM MEMHNDGAGNTLOCN,MEMGLBLHNDGAGNT  "
            +"  WHERE MAVCUSTID = NVL(?, MAVCUSTID) AND MACORGSTTSFLAG IS NULL  AND MAVHNDGAGNTCODE =  MAVORGCODE AND MAVEDMNDFLAG='Y'"
            +"  AND MAVHNDGAGNTCODE  NOT IN ( 'SELF','PUB')  AND MACCUSTVERF='Y' AND MAVCUSTSTTS NOT IN ('AN','NN')  AND MADCUSTEFCTFROM <= SYSDATE AND	NVL(MADCUSTEFCTTO,SYSDATE) >= SYSDATE ORDER BY 1,2");
            
            logger.info("For retreving Organizations details tavcustedid.." + this.tavcustid);
            if(((String)this.tavcustid).equals("CRISDEMO"))
            {
                     ps1.setString(1, "");
                     ps1.setString(2, "");
            }
            else
            {
                ps1.setString(1, this.tavcustid);
                ps1.setString(2, this.tavcustid);
            }
            
                     rs = ps1.executeQuery();
                     primseccustOrgn = new ArrayList<ArrayList<String>>();
                    int count = 0;
                    int primarycount        =   0;
                    int secondarycount      =   0;
                     while (rs.next()) {
                         this.primseccustOrgn.add(new ArrayList<String>());
                         this.primseccustOrgn.get(count).add(rs.getString(1));
                         this.primseccustOrgn.get(count).add(rs.getString(2));
            logger.info("rs.getString(1):"+rs.getString(1)+":rs.getString(2):"+rs.getString(2));
                         if(rs.getString(1).equals("PRIMARY"))
                             primarycount ++;
                         else if(rs.getString(1).equals("SECONDARY")) 
                             secondarycount ++;
                         count++;
                     } 
            logger.info("count:"+count);
            logger.info("primarycount:"+primarycount);
            logger.info("secondarycount:"+secondarycount);
                     if (rs != null)
                         rs.close();
                    if(primarycount > 0 && secondarycount > 0)
                        custtypeflag    =   "B";
                    else if(primarycount > 0)
                        custtypeflag    =   "P";
                    else if(secondarycount > 0)
                        custtypeflag    =   "S";
                logger.info("custtypeflag:"+custtypeflag);

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

    public void setTavcustctgr(String tavcustctgr) {
        this.tavcustctgr = tavcustctgr;
    }

    public String getTavcustctgr() {
        return tavcustctgr;
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

    public void setTaccustgndr(String taccustgndr) {
        this.taccustgndr = taccustgndr;
    }

    public String getTaccustgndr() {
        return taccustgndr;
    }

    public void setTavcustaddr(String tavcustaddr) {
        this.tavcustaddr = tavcustaddr;
    }

    public String getTavcustaddr() {
        return tavcustaddr;
    }

    public void setTancustpincode(String tancustpincode) {
        this.tancustpincode = tancustpincode;
    }

    public String getTancustpincode() {
        return tancustpincode;
    }

    public void setTavcustscndmailid(String tavcustscndmailid) {
        this.tavcustscndmailid = tavcustscndmailid;
    }

    public String getTavcustscndmailid() {
        return tavcustscndmailid;
    }

    public void setTancustscndmoblnumb(String tancustscndmoblnumb) {
        this.tancustscndmoblnumb = tancustscndmoblnumb;
    }

    public String getTancustscndmoblnumb() {
        return tancustscndmoblnumb;
    }

    public void setTavcustidnumb1(String tavcustidnumb1) {
        this.tavcustidnumb1 = tavcustidnumb1;
    }

    public String getTavcustidnumb1() {
        return tavcustidnumb1;
    }

    public void setTavcustiddocid1(String tavcustiddocid1) {
        this.tavcustiddocid1 = tavcustiddocid1;
    }

    public String getTavcustiddocid1() {
        return tavcustiddocid1;
    }

    public void setTavcustidnumb2(String tavcustidnumb2) {
        this.tavcustidnumb2 = tavcustidnumb2;
    }

    public String getTavcustidnumb2() {
        return tavcustidnumb2;
    }

    public void setTavcustiddocid2(String tavcustiddocid2) {
        this.tavcustiddocid2 = tavcustiddocid2;
    }

    public String getTavcustiddocid2() {
        return tavcustiddocid2;
    }

    public void setTavcustidnumb3(String tavcustidnumb3) {
        this.tavcustidnumb3 = tavcustidnumb3;
    }

    public String getTavcustidnumb3() {
        return tavcustidnumb3;
    }

    public void setTavcustiddocid3(String tavcustiddocid3) {
        this.tavcustiddocid3 = tavcustiddocid3;
    }

    public String getTavcustiddocid3() {
        return tavcustiddocid3;
    }

    public void setTadcustdob(String tadcustdob) {
        this.tadcustdob = tadcustdob;
    }

    public String getTadcustdob() {
        return tadcustdob;
    }

    public void setTavcusthintquestid1(String tavcusthintquestid1) {
        this.tavcusthintquestid1 = tavcusthintquestid1;
    }

    public String getTavcusthintquestid1() {
        return tavcusthintquestid1;
    }

    public void setTavcusthintans1(String tavcusthintans1) {
        this.tavcusthintans1 = tavcusthintans1;
    }

    public String getTavcusthintans1() {
        return tavcusthintans1;
    }

    public void setTavcusthintquestid2(String tavcusthintquestid2) {
        this.tavcusthintquestid2 = tavcusthintquestid2;
    }

    public String getTavcusthintquestid2() {
        return tavcusthintquestid2;
    }

    public void setTavcusthintans2(String tavcusthintans2) {
        this.tavcusthintans2 = tavcusthintans2;
    }

    public String getTavcusthintans2() {
        return tavcusthintans2;
    }

    public void setTadcustapvldate(String tadcustapvldate) {
        this.tadcustapvldate = tadcustapvldate;
    }

    public String getTadcustapvldate() {
        return tadcustapvldate;
    }

    public void setTavcuststts(String tavcuststts) {
        this.tavcuststts = tavcuststts;
    }

    public String getTavcuststts() {
        return tavcuststts;
    }

    public void setTadlaststtsdate(String tadlaststtsdate) {
        this.tadlaststtsdate = tadlaststtsdate;
    }

    public String getTadlaststtsdate() {
        return tadlaststtsdate;
    }

    public void setTavrolecode(String tavrolecode) {
        this.tavrolecode = tavrolecode;
    }

    public String getTavrolecode() {
        return tavrolecode;
    }

    public void setTavdgtlsignflag(String tavdgtlsignflag) {
        this.tavdgtlsignflag = tavdgtlsignflag;
    }

    public String getTavdgtlsignflag() {
        return tavdgtlsignflag;
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

    public void setUploadedmsgcont(String uploadedmsgcont) {
        this.uploadedmsgcont = uploadedmsgcont;
    }

    public String getUploadedmsgcont() {
        return uploadedmsgcont;
    }

    public void setLstorgnsttn(ArrayList<orgnsttn> lstorgnsttn) {
        this.lstorgnsttn = lstorgnsttn;
    }

    public ArrayList<orgnsttn> getLstorgnsttn() {
        return lstorgnsttn;
    }

    public void setObjorgnsttn(orgnsttn objorgnsttn) {
        this.objorgnsttn = objorgnsttn;
    }

    public orgnsttn getObjorgnsttn() {
        return objorgnsttn;
    }

    public void setLstorgndoc(ArrayList<orgndoc> lstorgndoc) {
        this.lstorgndoc = lstorgndoc;
    }

    public ArrayList<orgndoc> getLstorgndoc() {
        return lstorgndoc;
    }

    public void setObjorgndoc(orgndoc objorgndoc) {
        this.objorgndoc = objorgndoc;
    }

    public orgndoc getObjorgndoc() {
        return objorgndoc;
    }

    public static void setCurrentCon(Connection currentCon) {
        UserProfDtls.currentCon = currentCon;
    }

    public static Connection getCurrentCon() {
        return currentCon;
    }

    public static void setRs(ResultSet rs) {
        UserProfDtls.rs = rs;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public void setPrimseccustOrgn(ArrayList<ArrayList<String>> primseccustOrgn) {
        this.primseccustOrgn = primseccustOrgn;
    }

    public ArrayList<ArrayList<String>> getPrimseccustOrgn() {
        return primseccustOrgn;
    }

    public void setCusttypeflag(String custtypeflag) {
        this.custtypeflag = custtypeflag;
    }

    public String getCusttypeflag() {
        return custtypeflag;
    }
}
