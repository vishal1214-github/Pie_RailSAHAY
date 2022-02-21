package servlet.AjaxData;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GG_CmdtViewSrvc;

import org.apache.log4j.Logger;

import util.logger.FoisLogger;

public class GG_CmdtViewJson extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_CmdtViewJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException, ServletException
	{
		logger.info("Calling GG_CmdtViewJson...");

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		res.setHeader("Cache-Control", "no-cache");
	    RequestDispatcher rd=null;
		//Just To View the Information
		Enumeration enumeration = req.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			String name		= (String)enumeration.nextElement();
			String values[]	= req.getParameterValues(name);
		}
		HttpSession session = req.getSession();

		String si_DtlsType	= req.getParameter("Help").toUpperCase().trim();
                logger.info("si_DtlsType" + si_DtlsType);
		String resData="";
		GG_CmdtViewSrvc objSrvc=new GG_CmdtViewSrvc();
		String strData="";
		if(si_DtlsType.equals("SETMAINCMDT"))
		{
			String strMainCmdtCode	= req.getParameter("MainCmdtCode").toUpperCase().trim();
			String strMainCmdtName	= req.getParameter("MainCmdtName").toUpperCase().trim();
			logger.info("Setting Main Commodity in Session:"+strMainCmdtCode+","+strMainCmdtName);
	        session.setAttribute("MainCmdtCode", strMainCmdtCode);
	        session.setAttribute("MainCmdtName", strMainCmdtName);			
                session.setAttribute("SubCmdtCode", "");
                        session.setAttribute("SubCmdtName", "");
	        logger.info("Attributes Set, Forwarding the Request now..");
	        rd=req.getRequestDispatcher("pages/Industry.jsp");
            rd.forward(req,res);
		}
		if(si_DtlsType.equals("SETSUBCMDT"))
		{
			String strSubCmdtCode	= req.getParameter("SubCmdtCode").toUpperCase().trim();
			String strSubCmdtName	= req.getParameter("SubCmdtName").toUpperCase().trim();
			logger.info("Setting Sub Commodity in Session:"+strSubCmdtCode+","+strSubCmdtName);
			session.setAttribute("SubCmdtCode", strSubCmdtCode);
                        session.setAttribute("SubCmdtName", strSubCmdtName);
                        logger.info("Attributes Set, Forwarding the Request now..");
                        rd=req.getRequestDispatcher("pages/Industry.jsp");
                        rd.forward(req,res);
		}
		if(si_DtlsType.equals("NUM_CMDT_LIST"))
		{
			String si_CmdtCode	= ((String)session.getAttribute("MainCmdtCode")).trim();
			logger.info("Main Commodity Received:"+si_CmdtCode);
			try
			{
					String strSrvcData[][];
					strSrvcData=objSrvc.getNumCmdtList(si_CmdtCode);
					String strCallStts=objSrvc.getCallStts();
					String strCallMesg=objSrvc.getCallMesg();

					strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"WgonList\":[";
					int intDataLen=strSrvcData.length;
					logger.info("Numeric Commodity List Length:"+intDataLen);

					for(int i=0;i<intDataLen;i++)
					{
							if(i==(intDataLen-1))
									strData+="{\"NumCode\":\""+strSrvcData[i][0]+"\",\"CmdtName\":\""+strSrvcData[i][1]+"\"}";
							else
									strData+="{\"NumCode\":\""+strSrvcData[i][0]+"\",\"CmdtName\":\""+strSrvcData[i][1]+"\"},";
					}
					strData+="]}";
					/*logger.info(strData);*/
					res.getWriter().write(strData);
			}
			catch(Exception e)
			{
					System.out.println("Error in Data Fetch");
					res.getWriter().write("Data Contains Error! or No Records");
			}
		}
	    if(si_DtlsType.equals("CMDT_SCHM")) /******ADDED FOR GETTING SCHEME RELATED DETAILS AGAINST THE SELECTED COMMODITY**/
	    {
	        String si_CmdtLevel      = req.getParameter("CmdtLevl").toUpperCase().trim();
	        String si_Cmdt      = req.getParameter("Cmdt").toUpperCase().trim();
	            logger.info("Commodity Level:"+si_CmdtLevel);
	        logger.info("Commodity :"+si_Cmdt);
	            try
	            {
	                            String strSrvcData[][];
	                            strSrvcData=objSrvc.getCmdtSchm(si_CmdtLevel,si_Cmdt);
	                            String strCallStts=objSrvc.getCallStts();
	                            String strCallMesg=objSrvc.getCallMesg();

	                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SchmDtls\":[";
	                            int intDataLen=strSrvcData.length;
	                            logger.info("Scheme Details Length:"+intDataLen);

	                            for(int i=0;i<intDataLen;i++)
	                            {
	                                            if(i==(intDataLen-1))
	                                                            strData+="{\"SchmCode\":\""+strSrvcData[i][0]+"\",\"SchmDesc\":\""+strSrvcData[i][1]+"\",\"SchmMstrRc\":\""+strSrvcData[i][2]+"\",\"SchmMstrRcDate\":\""+strSrvcData[i][3]+"\",\"SchmMstrRcLink\":\""+strSrvcData[i][4]+"\",\"SchmLtstRc\":\""+strSrvcData[i][5]+"\",\"SchmLtstRcDate\":\""+strSrvcData[i][6]+"\",\"SchmLtstRcLink\":\""+strSrvcData[i][7]+"\"}";
	                                            else
	                                                strData+="{\"SchmCode\":\""+strSrvcData[i][0]+"\",\"SchmDesc\":\""+strSrvcData[i][1]+"\",\"SchmMstrRc\":\""+strSrvcData[i][2]+"\",\"SchmMstrRcDate\":\""+strSrvcData[i][3]+"\",\"SchmMstrRcLink\":\""+strSrvcData[i][4]+"\",\"SchmLtstRc\":\""+strSrvcData[i][5]+"\",\"SchmLtstRcDate\":\""+strSrvcData[i][6]+"\",\"SchmLtstRcLink\":\""+strSrvcData[i][7]+"\"},";
	                            }
	                            strData+="]}";
	                            /*logger.info(strData);*/
	                            res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                            System.out.println("Error in Data Fetch");
	                            res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
	    if(si_DtlsType.equals("CMDT_STATS"))
	    {
	        String si_RakeCmdt      = req.getParameter("RakeCmdt").toUpperCase().trim();
	        String si_NumCmdt       = req.getParameter("NumCmdt").toUpperCase().trim();
	            logger.info("Rake Cmdt:"+si_RakeCmdt+", Num Cmdt:"+si_NumCmdt);
	            try
	            {
	                            String strSrvcData[];
	                            strSrvcData=objSrvc.getCmdtStats(si_RakeCmdt, si_NumCmdt);
	                            String strCallStts=objSrvc.getCallStts();
	                            String strCallMesg=objSrvc.getCallMesg();

	                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"";
                                    if(strSrvcData.length>0) {
                                        strData+=",\"TrmlCont\":\""+strSrvcData[0]+"\",\"RebtSchm\":\""+strSrvcData[1]+"\",\"StckCont\":\""+strSrvcData[2]+"\",\"TwoPtCombi\":\""+strSrvcData[3]+"\"}";
					
				    	session.setAttribute("TrmlCont", strSrvcData[0]);
                        	    	session.setAttribute("RebtSchm", strSrvcData[1]);
                        	    	session.setAttribute("StckCont", strSrvcData[2]);
                        	    	session.setAttribute("TwoPtCombi", strSrvcData[3]);
                                    }
                                    else {
                                        strData+="}";
                                    }
	                            /*logger.info(strData);*/
	                            res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                            System.out.println("Error in Data Fetch");
	                            res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }

	    if(si_DtlsType.equals("SCHM_CMDT")) /******ADDED FOR GETTING COMMODITY DETAILS AGAINST THE SELECTED SCHEME**/
	    {
	        String si_SchmCode      = req.getParameter("SchmCode").toUpperCase().trim();
	            logger.info("Scheme Code:"+si_SchmCode);
	            try
	            {
	                            String strSrvcData[][];
	                            strSrvcData=objSrvc.getSchmCmdt(si_SchmCode);
	                            String strCallStts=objSrvc.getCallStts();
	                            String strCallMesg=objSrvc.getCallMesg();

	                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"SchmCmdtDtls\":[";
	                            int intDataLen=strSrvcData.length;
	                            logger.info("Commodity Details Length:"+intDataLen);

	                            for(int i=0;i<intDataLen;i++)
	                            {
	                                            if(i==(intDataLen-1))
	                                                            strData+="{\"SchmCode\":\""+strSrvcData[i][0]+"\",\"SchmDesc\":\""+strSrvcData[i][1]+"\",\"SchmMstrRc\":\""+strSrvcData[i][2]+"\",\"SchmMstrRcDate\":\""+strSrvcData[i][3]+"\",\"SchmMstrRcLink\":\""+strSrvcData[i][4]+"\",\"SchmLtstRc\":\""+strSrvcData[i][5]+"\",\"SchmLtstRcDate\":\""+strSrvcData[i][6]+"\",\"SchmLtstRcLink\":\""+strSrvcData[i][7]+"\",\"CmdtFlag\":\""+strSrvcData[i][8]+"\",\"CmdtDesc\":\""+strSrvcData[i][9]+"\"}";
	                                            else
	                                                strData+="{\"SchmCode\":\""+strSrvcData[i][0]+"\",\"SchmDesc\":\""+strSrvcData[i][1]+"\",\"SchmMstrRc\":\""+strSrvcData[i][2]+"\",\"SchmMstrRcDate\":\""+strSrvcData[i][3]+"\",\"SchmMstrRcLink\":\""+strSrvcData[i][4]+"\",\"SchmLtstRc\":\""+strSrvcData[i][5]+"\",\"SchmLtstRcDate\":\""+strSrvcData[i][6]+"\",\"SchmLtstRcLink\":\""+strSrvcData[i][7]+"\",\"CmdtFlag\":\""+strSrvcData[i][8]+"\",\"CmdtDesc\":\""+strSrvcData[i][9]+"\"},";
	                            }
	                            strData+="]}";
	                            /*logger.info(strData);*/
	                            res.getWriter().write(strData);
	            }
	            catch(Exception e)
	            {
	                            System.out.println("Error in Data Fetch");
	                            res.getWriter().write("Data Contains Error! or No Records");
	            }
	    }
		
    if(si_DtlsType.equals("DRISTI_DATA")) /******FOR E_DRISTI DATA FROM FOIS TABLES**/
    {
              logger.info("GET DRISTI DATA");
            try
            {
                            String strSrvcData[][];
                            strSrvcData=objSrvc.getDristiData();
                            String strCallStts=objSrvc.getCallStts();
                            String strCallMesg=objSrvc.getCallMesg();

                            strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DristiData\":[";
                            int intDataLen=strSrvcData.length;
                            logger.info("Data Details Length:"+intDataLen);

                            for(int i=0;i<intDataLen;i++)
                            {
                                            if(i==(intDataLen-1))
                                                            strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"LdngTngeDlyC\":\""+strSrvcData[i][1]+"\",\"LdngTngeDlyP\":\""+strSrvcData[i][2]+"\",\"LdngTngeWklyC\":\""+strSrvcData[i][3]+"\",\"LdngTngeWklyP\":\""+strSrvcData[i][4]+"\",\"LdngTngeMnthC\":\""+strSrvcData[i][5]+"\",\"LdngTngeMnthP\":\""+strSrvcData[i][6]+"\",\"LdngTngeYrC\":\""+strSrvcData[i][7]+"\",\"LdngTngeYrP\":\""+strSrvcData[i][8]+"\"}";
                                            else
                                strData+="{\"Cmdt\":\""+strSrvcData[i][0]+"\",\"LdngTngeDlyC\":\""+strSrvcData[i][1]+"\",\"LdngTngeDlyP\":\""+strSrvcData[i][2]+"\",\"LdngTngeWklyC\":\""+strSrvcData[i][3]+"\",\"LdngTngeWklyP\":\""+strSrvcData[i][4]+"\",\"LdngTngeMnthC\":\""+strSrvcData[i][5]+"\",\"LdngTngeMnthP\":\""+strSrvcData[i][6]+"\",\"LdngTngeYrC\":\""+strSrvcData[i][7]+"\",\"LdngTngeYrP\":\""+strSrvcData[i][8]+"\"},";
                            }
                strData+="]";
                strSrvcData=objSrvc.getDristiHead();                          
                intDataLen=strSrvcData.length;
                logger.info("Dristi Head Details:"+intDataLen);
                strData+=",\"DristiHead\":[";
                for(int i=0;i<intDataLen;i++)
                {                                                       
                        if(i==(intDataLen-1))
                                strData+="{\"IntrvlType\":\""+strSrvcData[i][0]+"\",\"IntrvlDesc\":\""+strSrvcData[i][1]+"\"}";
                        else
                    strData+="{\"IntrvlType\":\""+strSrvcData[i][0]+"\",\"IntrvlDesc\":\""+strSrvcData[i][1]+"\"},";
                }
                strData+="]}";
                            res.getWriter().write(strData);
            }
            catch(Exception e)
            {
                            System.out.println("Error in Data Fetch");
                            res.getWriter().write("Data Contains Error! or No Records");
            }
    }
        PrintWriter out                 = res.getWriter();
        out.println(resData);
    }
        
}