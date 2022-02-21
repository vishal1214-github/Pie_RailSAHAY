package servlet.AppData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdtFrgtCalc extends HttpServlet{
    public CmdtFrgtCalc() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	            
	            String si_SttnFrom 	= (String)request.getParameter("txtSttnFrom");
				String si_SttnTo 	= (String)request.getParameter("txtSttnTo");
				String si_CmdtName 	= (String)request.getParameter("txtCmdtName");
				String si_WgonType 	= (String)request.getParameter("txtWgonType");
				String si_RKPM		= (String)request.getParameter("selRKPM");
				String si_WgonNumb	= (String)request.getParameter("txtWgonNumb");
				String si_selWgonCtgy	= (String)request.getParameter("selWgon");
				String si_optWgontype	= (String)request.getParameter("wgontype");
				String si_RakeSize      = (String)request.getParameter("hidWgonNumb");
				String si_FromWgonFlag     = (String)request.getParameter("FromWgonFlag");
				
               			request.setAttribute("txtSttnFrom",si_SttnFrom);				
				request.setAttribute("txtSttnTo",si_SttnTo);				
				request.setAttribute("txtCmdtName",si_CmdtName);				
				request.setAttribute("txtWgonType",si_WgonType);				
				request.setAttribute("selRKPM",si_RKPM);				
				request.setAttribute("txtWgonNumb",si_WgonNumb);				
				request.setAttribute("selWgon",si_selWgonCtgy);				
				request.setAttribute("wgontype",si_optWgontype);		
				request.setAttribute("hidWgonNumb",si_RakeSize);		
				request.setAttribute("FromWgonFlag",si_FromWgonFlag);
               
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/FindCmdtWgonFrgtCalc.jsp");
                dispatcher.forward(request, response);
        
            }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                    throws ServletException, IOException
            {
                    doPost(request, response);
            }
}
