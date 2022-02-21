package util.GenFunc;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import util.GenFunc.*;
import org.apache.log4j.Logger;
import tuxedo.*;
import util.logger.FoisLogger;

public class GG_SahayUtil 
{
	static Logger logger = FoisLogger.getLogger(GG_SahayUtil.class.getName());
        public String setEmpty(String str) {
            if(str==null)
                return "";
            else
                return str;
        }
	public String getCrntDateTime()
	{
		java.util.Date date=new java.util.Date();
		logger.info("DATE"+date);  
		return date.toString();		  
	}
	public String getCrntDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);  
	}
	public  String CDateNoTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
	 public  String YdateNoTime()
	    {
	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DATE, -1);
	        Date yesterday= calendar.getTime();
	        return dateFormat.format(yesterday);
	    }
	 public  String NextDayNoTime()
	    {
	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DATE, +1);
	        Date date1= calendar.getTime();
	        return dateFormat.format(date1);
	    }
  public String getFirstDay(Date d) 
	    {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(d);
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        Date dddd = calendar.getTime();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	        return sdf1.format(dddd);
	    }
	 public  String SecdDayNoTime()
	    {
	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DATE, +2);
	        Date date2= calendar.getTime();
	        return dateFormat.format(date2);
	    }
	 public  String TimeHr()
	    {
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH24:mm");
	      String st=dateFormat.format(new Date());
	  	  String time=st.substring(11,13);
	  	  return time;
	    }
	 
	public String createCaptchaImage()
	{
		String strPath="";
		GG_HelperFunc objHelp=new GG_HelperFunc();
		strPath=objHelp.createCaptchaImage();
		return strPath;
		
	}
	
	/*Validating Input String:
		 * N: Only Numeric
		 * D: Decimal Number
		 * A: Only Alphabet
		 * B: Only Alphanumeric
		 * S: Special Character
		 */
		public boolean valdInpt(String si_Inpt,String si_chckFlag)
		{
			boolean bolValid=true;
			String regex ="";
			si_Inpt=si_Inpt.trim();
			
			if(si_Inpt.equals(""))
			{
				return true;
			}
			if(si_chckFlag.equals("S")) 
			{
				String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
			    String str2[]=si_Inpt.split("");
			    int count=0;
			    for (int i=0;i<str2.length;i++)
			    {
			        if (specialCharacters.contains(str2[i]))
			        {
			            return false;
			        }
			    } 
			}
			if(si_chckFlag.equals("D")) 
			{
				 regex="-?\\d+(.\\d+)?";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("N")) 
			{
				 regex="[0-9]+";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("A"))
			{
				 regex="[a-zA-Z ]+";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("B"))
			{
				 regex="^[a-zA-Z0-9 ]*$";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("C"))
			{   
				 regex="^[ A-Za-z0-9_.,/;+-<>]*$";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("E"))
			{
				 regex="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				 return si_Inpt.matches(regex);
			}
			if(si_chckFlag.equals("M"))
			{
				System.out.println("MOBILE"+si_Inpt);
				 regex="\\d{10}";
				 return si_Inpt.matches(regex);
			}
			return bolValid;
		}
		

}
