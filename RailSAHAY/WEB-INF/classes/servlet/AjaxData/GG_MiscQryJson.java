package servlet.AjaxData;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GG_MiscQrySrvc;

import org.apache.log4j.Logger;

import util.logger.FoisLogger;

public class GG_MiscQryJson extends HttpServlet
{
	static Logger logger = FoisLogger.getLogger(GG_MiscQryJson.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse	res) throws IOException
	{
		logger.info("Calling GG_MiscQryJson.java...");
		HttpSession session=req.getSession();

		req.setCharacterEncoding("UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		String si_UserID = "";
		String si_ClntID = "";
	        String strInpt="";
		try
		{
			si_UserID		= (String) session.getAttribute("UserID");
        	si_ClntID		= (String) session.getAttribute("ClntID");
		}
		catch(Exception e)
		{
			si_UserID="";
			si_ClntID="";
		}

		String si_Optn	= req.getParameter("Optn").toUpperCase().trim();
		logger.info("si_Optn:"+ si_Optn);
		if(si_Optn.equals("CMDT_WGON_MPNG"))
		{
				logger.info("Case: Commodity Wagon Mapping");

				GG_MiscQrySrvc obj=new GG_MiscQrySrvc(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						String si_Cmdt	= req.getParameter("Cmdt").toUpperCase().trim();
						strSrvcData=obj.getCmdtWgonMpng(si_Cmdt);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"WgonList\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Commodity WagonType Mapping Length:"+intDataLen);
						int intCvrdCont=0;
						int intOpenCont=0;
						for(int i=0;i<intDataLen;i++)
						{
							if(strSrvcData[i][3].equals("C")) intCvrdCont++;
							if(strSrvcData[i][3].equals("O")) intOpenCont++;

								if(i==(intDataLen-1))
										strData+="{\"WgonType\":\""+strSrvcData[i][0]+"\",\"WgonDesc\":\""+strSrvcData[i][1]+"\",\"Brak\":\""+strSrvcData[i][2]+"\",\"COType\":\""+strSrvcData[i][3]+"\",\"WgonCont\":\""+strSrvcData[i][4]+"\",\"Tare\":\""+strSrvcData[i][5]+"\",\"CC\":\""+strSrvcData[i][6]+"\",\"GPCC\":\""+strSrvcData[i][7]+"\",\"WGONFLAG\":\""+strSrvcData[i][8]+"\",\"SIZE\":\""+strSrvcData[i][9]+"\",\"FORTL\":\""+strSrvcData[i][10]+"\",\"SIZEBG\":\""+strSrvcData[i][11]+"\",\"FORTLBG\":\""+strSrvcData[i][12]+"\"}";
								else
										strData+="{\"WgonType\":\""+strSrvcData[i][0]+"\",\"WgonDesc\":\""+strSrvcData[i][1]+"\",\"Brak\":\""+strSrvcData[i][2]+"\",\"COType\":\""+strSrvcData[i][3]+"\",\"WgonCont\":\""+strSrvcData[i][4]+"\",\"Tare\":\""+strSrvcData[i][5]+"\",\"CC\":\""+strSrvcData[i][6]+"\",\"GPCC\":\""+strSrvcData[i][7]+"\",\"WGONFLAG\":\""+strSrvcData[i][8]+"\",\"SIZE\":\""+strSrvcData[i][9]+"\",\"FORTL\":\""+strSrvcData[i][10]+"\",\"SIZEBG\":\""+strSrvcData[i][11]+"\",\"FORTLBG\":\""+strSrvcData[i][12]+"\"},";
						}
						strData+="],\"CvrdCont\":"+intCvrdCont+",\"OpenCont\":"+intOpenCont+"}";
						/*logger.info(strData);*/
						res.getWriter().write(strData);
				}
				catch(Exception e)
				{
						System.out.println("Error in Data Fetch");
						res.getWriter().write("Data Contains Error! or No Records");
				}
		}
		if(si_Optn.equals("SRCH_PAGE_LIST"))
		{
				logger.info("Case: Commodity Wagon Mapping");

				GG_MiscQrySrvc obj=new GG_MiscQrySrvc(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						String si_Word	= req.getParameter("Word").toUpperCase().trim();
						strSrvcData=obj.getSrchPageList(si_Word);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"PageList\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Word in Page List Length:"+intDataLen);
						int intCvrdCont=0;
						int intOpenCont=0;
						for(int i=0;i<intDataLen;i++)
						{
								if(i==(intDataLen-1))
										strData+="{\"PageTitle\":\""+strSrvcData[i][0]+"\",\"Url\":\""+strSrvcData[i][1]+"\",\"MatchCont\":\""+strSrvcData[i][2]+"\",\"WordList\":\""+strSrvcData[i][3]+"\"}";
								else
										strData+="{\"PageTitle\":\""+strSrvcData[i][0]+"\",\"Url\":\""+strSrvcData[i][1]+"\",\"MatchCont\":\""+strSrvcData[i][2]+"\",\"WordList\":\""+strSrvcData[i][3]+"\"},";
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
	if(si_Optn.equals("CMDT_FRGT_CLSS"))
	{
			logger.info("Case: Commodity Freight Class");

			GG_MiscQrySrvc obj=new GG_MiscQrySrvc(si_UserID,si_ClntID);

			try
			{
					String strSrvcData[];
					String strData="";
					String si_Cmdt	= req.getParameter("Cmdt").toUpperCase().trim();
					strSrvcData=obj.getCmdtRateClss(si_Cmdt);
					String strCallStts=obj.getCallStts();
					String strCallMesg=obj.getCallMesg();

					strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\"";
					int intDataLen=strSrvcData.length;
					logger.info("Data for Freight Class Length:"+intDataLen);
					if(intDataLen>0)
						strData+=",\"Cmdt\":\""+strSrvcData[0]+"\",\"GST\":\""+strSrvcData[1]+"\",\"WL\":\""+strSrvcData[2]+"\",\"TL\":\""+strSrvcData[3]+"\",\"RR\":\""+strSrvcData[4]+"\"}";
					/*logger.info(strData);*/
					res.getWriter().write(strData);
			}
			catch(Exception e)
			{
					System.out.println("Error in Data Fetch");
					res.getWriter().write("Data Contains Error! or No Records");
			}
		}
		if(si_Optn.equals("RATE_CLSS_DTLS"))
		{
				logger.info("Case: Rate Class Detail");

				GG_MiscQrySrvc obj=new GG_MiscQrySrvc(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						String si_RateClss= req.getParameter("RateClss").toUpperCase().trim();
						strSrvcData=obj.getRateClssDtls(si_RateClss);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"DistSlab\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Rate Class Detail Length:"+intDataLen);

						for(int i=0;i<intDataLen;i++)
						{
								if(i==(intDataLen-1))
										strData+="{\"RateClss\":\""+strSrvcData[i][0]+"\",\"DistFrom\":\""+strSrvcData[i][1]+"\",\"DistTo\":\""+strSrvcData[i][2]+"\",\"Rate\":\""+strSrvcData[i][3]+"\"}";
								else
										strData+="{\"RateClss\":\""+strSrvcData[i][0]+"\",\"DistFrom\":\""+strSrvcData[i][1]+"\",\"DistTo\":\""+strSrvcData[i][2]+"\",\"Rate\":\""+strSrvcData[i][3]+"\"},";
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

		if(si_Optn.equals("TWO_POINT_COMBI"))
		{
				logger.info("Case: Two Point Combination");

				GG_MiscQrySrvc obj=new GG_MiscQrySrvc(si_UserID,si_ClntID);

				try
				{
						String strSrvcData[][];
						String strData="";
						String si_IWOWFlag= req.getParameter("OWIW").toUpperCase().trim();
						String si_LocnFlag= req.getParameter("LocnFlag").toUpperCase().trim();
						String si_Locn= req.getParameter("Locn").toUpperCase().trim();
						String si_Cmdt= req.getParameter("Cmdt").toUpperCase().trim();
						String si_Type= req.getParameter("Type").toUpperCase().trim();

						strSrvcData=obj.getTwoPtCombi(si_IWOWFlag,si_LocnFlag, si_Locn, si_Cmdt, si_Type);
						String strCallStts=obj.getCallStts();
						String strCallMesg=obj.getCallMesg();

						strData="{\"Stts\":\""+strCallStts+"\", \"ErorMesg\":\""+strCallMesg+"\",\"TwoPtCombi\":[";
						int intDataLen=strSrvcData.length;
						logger.info("Two Point Combination Data Length:"+intDataLen);

						for(int i=0;i<intDataLen;i++)
						{
								if(i==(intDataLen-1))
										strData+="{\"SLNumb\":\""+strSrvcData[i][0]+"\",\"Sttn\":\""+strSrvcData[i][1]+"\",\"SttnName\":\""+strSrvcData[i][2]+"\",\"Dvsn\":\""+strSrvcData[i][3]+"\",\"Dstt\":\""+strSrvcData[i][4]+"\",\"OIFlag\":\""+strSrvcData[i][5]+"\",\"RakeType\":\""+strSrvcData[i][6]+"\",\"CmdtType\":\""+strSrvcData[i][7]+"\",\"Cmdt\":\""+strSrvcData[i][8]+"\",\"Lat\":\""+strSrvcData[i][9]+"\",\"Lng\":\""+strSrvcData[i][10]+"\"}";
								else
										strData+="{\"SLNumb\":\""+strSrvcData[i][0]+"\",\"Sttn\":\""+strSrvcData[i][1]+"\",\"SttnName\":\""+strSrvcData[i][2]+"\",\"Dvsn\":\""+strSrvcData[i][3]+"\",\"Dstt\":\""+strSrvcData[i][4]+"\",\"OIFlag\":\""+strSrvcData[i][5]+"\",\"RakeType\":\""+strSrvcData[i][6]+"\",\"CmdtType\":\""+strSrvcData[i][7]+"\",\"Cmdt\":\""+strSrvcData[i][8]+"\",\"Lat\":\""+strSrvcData[i][9]+"\",\"Lng\":\""+strSrvcData[i][10]+"\"},";
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
	}
}