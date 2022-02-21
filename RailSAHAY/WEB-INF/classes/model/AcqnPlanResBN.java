package model;

public class AcqnPlanResBN {
    String strResid="";
    String strZone="";
    String strDvsn="";
    String strCmdt="";
    String strIndst="";
    String strResName="";
    String strResMobl="";
    String strResLdLn="";
    String strResEmail="";
    String strResAddr="";
    String strUserId="";
    String strClntId="";

    public AcqnPlanResBN() {
        super();
    }
   
    public AcqnPlanResBN(String strResid ,String strZone,String strDvsn,String strCmdt,String strIndst,String strResName,
                         String strResMobl,String strResLdLn,String strResEmail,String strResAddr,
                         String strUserId,String strClntId)
    {
           this.strResid       = strResid  ;;
           this.strZone        = strZone   ; 
           this.strDvsn        = strDvsn   ; 
           this.strCmdt        = strCmdt   ; 
           this.strIndst       = strIndst  ; 
           this.strResName     = strResName; 
           this.strResMobl     = strResMobl; 
           this.strResLdLn     = strResLdLn; 
           this.strResEmail    = strResEmail;
           this.strResAddr     = strResAddr; 
           this.strUserId      = strUserId;  
           this.strClntId      = strClntId;  
        
    }
    public void setStrZone(String strZone) {
        this.strZone = strZone;
    }

    public String getStrZone() {
        return strZone;
    }

    public void setStrDvsn(String strDvsn) {
        this.strDvsn = strDvsn;
    }

    public String getStrDvsn() {
        return strDvsn;
    }

    public void setStrCmdt(String strCmdt) {
        this.strCmdt = strCmdt;
    }

    public String getStrCmdt() {
        return strCmdt;
    }

    public void setStrIndst(String strIndst) {
        this.strIndst = strIndst;
    }

    public String getStrIndst() {
        return strIndst;
    }

    public void setStrResName(String strResName) {
        this.strResName = strResName;
    }

    public String getStrResName() {
        return strResName;
    }

    public void setStrResMobl(String strResMobl) {
        this.strResMobl = strResMobl;
    }

    public String getStrResMobl() {
        return strResMobl;
    }

    public void setStrResLdLn(String strResLdLn) {
        this.strResLdLn = strResLdLn;
    }

    public String getStrResLdLn() {
        return strResLdLn;
    }

    public void setStrResEmail(String strResEmail) {
        this.strResEmail = strResEmail;
    }

    public String getStrResEmail() {
        return strResEmail;
    }

    public void setStrResAddr(String strResAddr) {
        this.strResAddr = strResAddr;
    }

    public String getStrResAddr() {
        return strResAddr;
    }

    public void setStrUserId(String strUserId) {
        this.strUserId = strUserId;
    }

    public String getStrUserId() {
        return strUserId;
    }

    public void setStrClntId(String strClntId) {
        this.strClntId = strClntId;
    }

    public String getStrClntId() {
        return strClntId;
    }

   

    public void setStrResid(String strResid) {
        this.strResid = strResid;
    }

    public String getStrResid() {
        return strResid;
    }
}
