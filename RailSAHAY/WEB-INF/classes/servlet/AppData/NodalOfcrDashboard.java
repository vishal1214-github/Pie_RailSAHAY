package servlet.AppData;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logn.LoginAction;

import model.GG_NodlOfcrSrvc;
import model.NodalOfcrCountDtls;
import model.NodalOfcrCustCncrnDtls;
import model.NodalOfcrIADtls;
import model.NodalOfcrSRDtls;
import model.OnRunDtls;
import model.Ostgdtls;
import model.SHY_NodalOfcrDashboardTX;
import model.SRDtls;

import org.apache.log4j.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import user.UserProfDtls;
import util.exception.GG_Exception;
import util.logger.FoisLogger;

public class NodalOfcrDashboard extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(NodalOfcrDashboard.class.getName());
    public NodalOfcrDashboard() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String si_TktId = request.getParameter("tktid");
                String si_TktType=request.getParameter("actvtype");
                String strIndx = request.getParameter("indx");
                String si_RespText = request.getParameter("resp").toUpperCase();
                String si_Dvsn = request.getParameter("dvsn");
                String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                
                
                logger.info("si_TktId="+si_TktId);
                logger.info("si_TktType="+si_TktType);
                logger.info("strIndx="+strIndx);
                logger.info("si_RespText="+si_RespText);
                logger.info("si_Dvsn="+si_Dvsn);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                String strReply="";
                String si_RespFrwdFlag="R";
                String si_FrwdDvsn="";
                String si_FrwdDOfcr="";
                String si_FrwdZone="";
                String si_FrwdZOfcr="";
                
                GG_NodlOfcrSrvc obj=new GG_NodlOfcrSrvc( si_UserId,  si_ClntId,  si_Dvsn,  "",  "");
                try {
                        strReply=(String)obj.saveResponse(si_TktId, si_RespFrwdFlag, si_TktType, si_RespText, si_FrwdDvsn, si_FrwdDOfcr, si_FrwdZone, si_FrwdZOfcr);
                        logger.info("strReply:"+strReply);
                        String jsontxt="{\"reply\":\""+strReply+"\"}";
                        System.out.println("jsontxt"+jsontxt);
                        response.getWriter().write(jsontxt);
                }
                catch(Exception e) {
                request.setAttribute("ERROR","");
                 request.setAttribute("TRXNSTTS","FAILED");
                request.setAttribute("REF_ID","");
                RequestDispatcher rd=request.getRequestDispatcher("pages/NodalOfcrDashboard.jsp");
                rd.forward(request,response);
                return; 
                }
                
              
            
            }
    @ManagedProperty(value="#{userprof}")
        private UserProfDtls userprofdtls;
}
