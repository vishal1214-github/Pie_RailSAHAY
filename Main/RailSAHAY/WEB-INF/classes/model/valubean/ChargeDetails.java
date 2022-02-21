package model.valubean;

public class ChargeDetails {
    private String PymtType="";
    private String PymtRefId="";
    private String InvcId="";
    private String PymtRefDate="";
    private String Amnt="";
    private String GSTNumb="";
    private String GSTAmnt="";
    private String WRFAjstFlag="";
    private String WRFAjstAmnt="";
    private String WRFTaxAjstAmnt="";
    private String HndgAgntCode="";
    public ChargeDetails() {
        super();
    }
    public ChargeDetails(String PymtType, String PymtRefId, String InvcId, String PymtRefDate, String Amnt, String GSTNumb, String GSTAmnt, String WRFAjstFlag, String WRFAjstAmnt, String WRFTaxAjstAmnt, String HndgAgntCode) {
        this.PymtType=PymtType;
        this.PymtRefId=PymtRefId;
        this.InvcId=InvcId;
        this.PymtRefDate=PymtRefDate;
        this.Amnt=Amnt;
        this.GSTNumb=GSTNumb;
        this.GSTAmnt=GSTAmnt;
        this.WRFAjstFlag=WRFAjstFlag;
        this.WRFAjstAmnt=WRFAjstAmnt;
        this.WRFTaxAjstAmnt=WRFTaxAjstAmnt;
        this.HndgAgntCode=HndgAgntCode;
    }

    public void setPymtType(String PymtType) {
        this.PymtType = PymtType;
    }

    public String getPymtType() {
        return PymtType;
    }

    public void setPymtRefId(String PymtRefId) {
        this.PymtRefId = PymtRefId;
    }

    public String getPymtRefId() {
        return PymtRefId;
    }

    public void setInvcId(String InvcId) {
        this.InvcId = InvcId;
    }

    public String getInvcId() {
        return InvcId;
    }

    public void setPymtRefDate(String PymtRefDate) {
        this.PymtRefDate = PymtRefDate;
    }

    public String getPymtRefDate() {
        return PymtRefDate;
    }

    public void setAmnt(String Amnt) {
        this.Amnt = Amnt;
    }

    public String getAmnt() {
        return Amnt;
    }

    public void setGSTNumb(String GSTNumb) {
        this.GSTNumb = GSTNumb;
    }

    public String getGSTNumb() {
        return GSTNumb;
    }

    public void setWRFAjstFlag(String WRFAjstFlag) {
        this.WRFAjstFlag = WRFAjstFlag;
    }

    public String getWRFAjstFlag() {
        return WRFAjstFlag;
    }

    public void setWRFAjstAmnt(String WRFAjstAmnt) {
        this.WRFAjstAmnt = WRFAjstAmnt;
    }

    public String getWRFAjstAmnt() {
        return WRFAjstAmnt;
    }

    public void setWRFTaxAjstAmnt(String WRFTaxAjstAmnt) {
        this.WRFTaxAjstAmnt = WRFTaxAjstAmnt;
    }

    public String getWRFTaxAjstAmnt() {
        return WRFTaxAjstAmnt;
    }

    public void setGSTAmnt(String GSTAmnt) {
        this.GSTAmnt = GSTAmnt;
    }

    public String getGSTAmnt() {
        return GSTAmnt;
    }

    public void setHndgAgntCode(String HndgAgntCode) {
        this.HndgAgntCode = HndgAgntCode;
    }

    public String getHndgAgntCode() {
        return HndgAgntCode;
    }
}
