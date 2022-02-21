package user;

import java.io.Serializable;

public class orgnsttn implements Serializable {
    @SuppressWarnings("compatibility:6359147649695270097")
    private static final long serialVersionUID = 1L;
    private String orgcode   ;
    private String orgname   ;
    private String orgtin    ;
    private String orghq     ;
    private String orgcntt   ;
    private String orgregn   ;
    private String ldnglocn;
    private String locntype;
    private String rowid;
    
    public orgnsttn() {
        super();
    }

    public orgnsttn(String orgcode, String orgname, String orgtin, String orghq, String orgcntt, String orgregn,
                    String ldnglocn, String locntype,String rowid) {
        super();
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.orgtin = orgtin;
        this.orghq = orghq;
        this.orgcntt = orgcntt;
        this.orgregn = orgregn;
        this.ldnglocn = ldnglocn;
        this.locntype = locntype;
        this.rowid =  rowid;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgtin(String orgtin) {
        this.orgtin = orgtin;
    }

    public String getOrgtin() {
        return orgtin;
    }

    public void setOrghq(String orghq) {
        this.orghq = orghq;
    }

    public String getOrghq() {
        return orghq;
    }

    public void setOrgcntt(String orgcntt) {
        this.orgcntt = orgcntt;
    }

    public String getOrgcntt() {
        return orgcntt;
    }

    public void setOrgregn(String orgregn) {
        this.orgregn = orgregn;
    }

    public String getOrgregn() {
        return orgregn;
    }

    public void setLdnglocn(String ldnglocn) {
        this.ldnglocn = ldnglocn;
    }

    public String getLdnglocn() {
        return ldnglocn;
    }

    public void setLocntype(String locntype) {
        this.locntype = locntype;
    }

    public String getLocntype() {
        return locntype;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getRowid() {
        return rowid;
    }

    @Override
            public String toString() {
                    return "orgnsttn [orgcode=" + orgcode + ", orgname=" + orgname
                                    + ", orgtin=" + orgtin + ", orghq=" + orghq + ", orgcntt="
                                    + orgcntt + ", orgregn=" + orgregn + ", ldnglocn=" + ldnglocn
                                    + ", locntype=" + locntype + ", rowid=" + rowid + "]";
            }
}
