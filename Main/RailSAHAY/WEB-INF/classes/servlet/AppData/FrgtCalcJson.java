package servlet.AppData;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.codehaus.jettison.json.JSONObject;

import util.logger.FoisLogger;

import util.pdf.GG_GnrtFrgtCalcPDF;

public class FrgtCalcJson extends HttpServlet{
    
    static Logger logger = FoisLogger.getLogger(FrgtCalcJson.class.getName());
    public FrgtCalcJson() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                JSONObject resObj = new JSONObject();
                logger.info("In FrgtCalcJson");
                        String si_SttnFrom 	= (String)request.getParameter("txtSttnFrom");
                        String si_SttnTo 	= (String)request.getParameter("txtSttnTo");
                        String si_QryFlag 	= (String)request.getParameter("qryFlag");                           
                        
                        if(si_QryFlag.equals( "A"))
                        {
                            String si_CmdtName 	= (String)request.getParameter("txtCmdtName");
                            String si_WgonType 	= (String)request.getParameter("txtWgonType");
                            String si_RKPM	= (String)request.getParameter("selRKPM");
                            String si_WgonNumb	= (String)request.getParameter("txtWgonNumb");
                                    
                            resObj = model.FSH_FrgtCalc.getDtlsjson(si_SttnFrom,si_SttnTo,si_CmdtName,si_WgonType,"R",si_RKPM,si_WgonNumb);  //R is Railway User, Need to do for Public users as well
                            PrintWriter out=response.getWriter();
                            logger.info("resObj:-"+resObj.toString());
                        }
                        if(si_QryFlag.equals("T"))
                        {
                            String si_CmdtName  = (String)request.getParameter("txtCmdtName");
                            String si_WgonType  = (String)request.getParameter("txtWgonType");
                            resObj = model.FSH_FrgtCalc.getRoutjson(si_SttnFrom,si_SttnTo,si_QryFlag,si_CmdtName,si_WgonType);  //R is Railway User, Need to do for Public users as well
                            PrintWriter out=response.getWriter();
                            logger.info("resObj for rout only:-"+resObj.toString());
                        }
                response.getWriter().write(resObj.toString());
                
            }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                    throws ServletException, IOException
            {
                    doPost(request, response);
            }
}
