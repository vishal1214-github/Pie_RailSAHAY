package servlet.AppData;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logn.LoginAction;

import model.CRDtls;
import model.Counts;
import model.OnRunDtls;
import model.Ostgdtls;
import model.SHY_UserDashboardTX;
import model.SRDtls;

import org.apache.log4j.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import user.UserProfDtls;
import util.exception.GG_Exception;
import util.logger.FoisLogger;

public class UserDashboard extends HttpServlet {
    static Logger logger = FoisLogger.getLogger(UserDashboard.class.getName());
    public UserDashboard() {
        super();
    }
    // size of byte buffer to send file
        private static final int BUFFER_SIZE = 10240000;   
         
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String strcallflag = request.getParameter("callfor");
                String strcustid = request.getParameter("custuserid");
                String strOrg = request.getParameter("org");
                String si_ClntId=request.getRemoteAddr();
                String si_UserId="system";//userprofdtls.getTavcustuserid();
                logger.info("strcallflag:-"+strcallflag);
                logger.info("strcustid:-"+strcustid);
                logger.info("si_ClntId:-"+si_ClntId);
                logger.info("si_UserId:-"+si_UserId);
                String strReply="";
                
                SHY_UserDashboardTX obj=new SHY_UserDashboardTX();
                try {
                    if(strcallflag.equals("ODR"))
                    {
                        ArrayList<Ostgdtls> list =   obj.SHY_DashboardOSTGTX(si_UserId,si_ClntId,strcallflag, strcustid,strOrg);
        
                        logger.info("list:-"+list.size());
                        //JSONArray jsAr =new JSONArray(list);
                        
                      //  StringWriter out = new StringWriter();
                       // JSONArray.writeJSONString(list, out); 
                      
                        String jsonText = new Gson().toJson(list);
                        logger.info("jsonText:-"+jsonText);
                        response.getWriter().write(jsonText); 
                        
                    }else if(strcallflag.equals("ONRUN")){
                        ArrayList<OnRunDtls> onrunL=obj.SHY_DashboardOnRunTX(si_UserId, si_ClntId, strcallflag, strcustid,strOrg);
                        logger.info("list:-"+onrunL.size());
                       // StringWriter out = new StringWriter();
                       // JSONArray.writeJSONString(onrunL, out); 
                        //String jsonText =JSONArray.toJSONString(onrunL);//out.toString();
                        String jsonText = new Gson().toJson(onrunL);
                        logger.info("jsonText:-"+jsonText);
                        response.getWriter().write(jsonText); 
                        
                    }else if(strcallflag.equals("CONT")){
                        ArrayList<Counts> countL=obj.SHY_DashboardCONTTX(si_UserId, si_ClntId, strcallflag, strcustid,strOrg);
                        logger.info("list:-"+countL.size());
                       // StringWriter out = new StringWriter();
                       // JSONArray.writeJSONString(countL, out); 
                        //String jsonText = JSONArray.toJSONString(countL);//out.toString();
                        String jsonText = new Gson().toJson(countL);
                        logger.info("jsonText:-"+jsonText);
                        response.getWriter().write(jsonText);                         
                    }else if(strcallflag.equals("SR")){
                        ArrayList<SRDtls> SRDtlsL=obj.SHY_DashboardSRTX(si_UserId, si_ClntId, strcallflag, strcustid,strOrg);
                        logger.info("list:-"+SRDtlsL.size());
                       // StringWriter out = new StringWriter();
                       // JSONArray.writeJSONString(countL, out); 
                        //String jsonText = JSONArray.toJSONString(countL);//out.toString();
                        String jsonText = new Gson().toJson(SRDtlsL);
                        logger.info("jsonText:-"+jsonText);
                        response.getWriter().write(jsonText);                         
                    }else if(strcallflag.equals("CR")){
                        ArrayList<CRDtls> CRDtlsL=obj.SHY_DashboardCRTX(si_UserId, si_ClntId, strcallflag, strcustid,strOrg);
                        logger.info("list:-"+CRDtlsL.size());
                       // StringWriter out = new StringWriter();
                       // JSONArray.writeJSONString(countL, out); 
                        //String jsonText = JSONArray.toJSONString(countL);//out.toString();
                        String jsonText = new Gson().toJson(CRDtlsL);
                        logger.info("jsonText:-"+jsonText);
                        response.getWriter().write(jsonText);                         
                    }else if(strcallflag.equals("FILE")){
                        
                        String fileName = request.getParameter("fileName");
                        String reqId = request.getParameter("reqId");
                        
                        System.out.println("FileName:"+fileName+" for request ID:"+reqId);
                        InputStream is= obj.getReqFile(fileName, reqId);
                      
                        int fileLength = is.available();
                                         
                                        System.out.println("fileLength = " + fileLength);
                         
                                        ServletContext context = getServletContext();
                         
                                        // sets MIME type for the file download
                                        String mimeType = context.getMimeType(fileName);
                                        if (mimeType == null) {        
                                            mimeType = "application/octet-stream";
                                        }              
                                         
                                        // set content properties and header attributes for the response
                                        response.setContentType(mimeType);
                                       // response.setContentLength(fileLength);
                                      // response.setHeader("Content-Length", String.valueOf(fileLength));
                                        String headerKey = "Content-Disposition";
                                        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                                        response.setHeader(headerKey, headerValue);
                         
                                        // writes the file to the client
                                        OutputStream outStream = response.getOutputStream();
                                         
                                        byte[] buffer = new byte[BUFFER_SIZE];
                                        int bytesRead = -1;
                                         
                                        while ((bytesRead = is.read(buffer)) != -1) {
                                            outStream.write(buffer, 0, bytesRead);
                                        }
                                         
                                        is.close();
                                        outStream.close(); 
                        response.flushBuffer();
                        
                    }
                    
                
                }
                catch (GG_Exception e) {
                request.setAttribute("ERROR","");
                 request.setAttribute("TRXNSTTS","FAILED");
                request.setAttribute("REF_ID","");
                RequestDispatcher rd=request.getRequestDispatcher("pages/SAHAYDashboard.jsp");
                rd.forward(request,response);
                return; 
                }
                
              
            
            }
    @ManagedProperty(value="#{userprof}")
        private UserProfDtls userprofdtls;
}
