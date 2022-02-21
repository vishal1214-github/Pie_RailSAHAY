package util;

public class GG_Validate 
{
    public GG_Validate() 
    {
        super();
    }
    
    public static boolean chkName(String strName)
    {
        if(!strName.matches("[a-zA-Z .]*"))
            return false;
        return true;
    }    
    public static boolean chkNumb(String strName)
    {
        if(!strName.matches("[0-9]*"))
            return false;
        return true;
    }    
    public static boolean chkAlpha(String strName)
    {
        if(!strName.matches("[a-zA-Z]*"))
            return false;
        return true;
    }
    public static boolean chkAlphaNum(String strName)
    {
        if(!strName.matches("[a-zA-Z0-9]*"))
            return false;
        return true;
    }
}
