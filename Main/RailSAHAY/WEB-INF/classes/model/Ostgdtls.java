package model;

public class Ostgdtls {
    private String cnmtid   = "";
    private String sttnfrom  = "";
    private String dmndNo;
    private String dmndTime;    
    private String rakecmdt = "";
    private String sttnto    = "";
    private String raketype = "";
    private String unts = "";
    private String dmndDtlsId      = "";
    private String dmndId      = "";

    public void setDmndNo(String dmndNo) {
        this.dmndNo = dmndNo;
    }

    public String getDmndNo() {
        return dmndNo;
    }

    public void setDmndTime(String dmndTime) {
        this.dmndTime = dmndTime;
    }

    public String getDmndTime() {
        return dmndTime;
    }

    public void setUnts(String unts) {
        this.unts = unts;
    }

    public String getUnts() {
        return unts;
    }

    public void setDmndDtlsId(String dmndDtlsId) {
        this.dmndDtlsId = dmndDtlsId;
    }

    public String getDmndDtlsId() {
        return dmndDtlsId;
    }

    public void setDmndId(String dmndId) {
        this.dmndId = dmndId;
    }

    public String getDmndId() {
        return dmndId;
    }
   

    public void setCnmtid(String cnmtid) {
        this.cnmtid = cnmtid;
    }

    public String getCnmtid() {
        return cnmtid;
    }

    public void setRakecmdt(String rakecmdt) {
        this.rakecmdt = rakecmdt;
    }

    public String getRakecmdt() {
        return rakecmdt;
    }

    

    public void setRaketype(String raketype) {
        this.raketype = raketype;
    }

    public String getRaketype() {
        return raketype;
    }

    public void setSttnfrom(String sttnfrom) {
        this.sttnfrom = sttnfrom;
    }

    public String getSttnfrom() {
        return sttnfrom;
    }

    public void setSttnto(String sttnto) {
        this.sttnto = sttnto;
    }

    public String getSttnto() {
        return sttnto;
    }

   
    
    
    public Ostgdtls() {
        super();
    }
}
