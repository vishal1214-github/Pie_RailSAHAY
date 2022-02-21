package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.GenFunc.GG_SahayCmmn;


public class GG_ProjUtil
{
  	public static void setSahayInpt(HttpServletRequest request, String si_RprtTitl, String si_RprtPath, String si_Inpt)
    {
        HttpSession session = request.getSession();
       
       	String si_UserID		=(String) session.getAttribute("UserID");
		String si_ClntID		=(String) session.getAttribute("ClntID");
		String si_Theme			= (String) session.getAttribute("Theme");
		String si_LangFlag		=(String) session.getAttribute("LangFlag");
	     
        String sesid=request.getSession().getId();
        si_LangFlag=si_LangFlag+sesid;
		GG_SahayCmmn objRprt=new GG_SahayCmmn();
		String  browserDetails  =   request.getHeader("User-Agent");
		String Info=objRprt.getbrowserinfo(browserDetails);
        String UserDevice=(Info.substring(0,Info.indexOf('#'))).trim();
        String Browser=(Info.substring(Info.indexOf('#')+1)).trim();
				try
			{
					objRprt.setSahay(si_UserID,si_ClntID,si_Theme,si_LangFlag,si_RprtTitl,si_RprtPath,UserDevice,Browser,si_Inpt);

				}
			catch(Exception e)
			{
				System.out.println("Exception in setting Report Access Log:"+e.getMessage());
			}
		
    }

   
}
