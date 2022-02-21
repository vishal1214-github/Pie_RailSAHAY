<%@ page import="util.GenFunc.GG_SahayUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
 String strAccessCount ="";
String strLastUpdate= "Thu, 10 Sep 2020 11:48:56";    
String strTheme="";
String strLangFlag="";
String strClntId="";

String strUserURL =   request.getRequestURL().toString();
System.out.println("strUserURL"+ strUserURL );
String strUserCheck = "";
if (strUserURL.trim().toUpperCase().contains("INDIANRAIL")) 
{
	strUserCheck = "P";
} else {
	strUserCheck = "R";
}
System.out.println("strUserCheck"+ strUserCheck );


GG_SahayUtil SahayUtil=new GG_SahayUtil();

strTheme = (String)session.getAttribute("Theme");
String strThemeClass="";
if(strTheme==null)
{	
	try
	{
		strTheme=((String)request.getParameter("txtTheme")).trim();	
	}
	catch(Exception e)
	{
			strTheme="BL";
	}
}
String strThemeCSS="style";
if(strTheme.equals("GR")) {  strThemeClass="btn-success"; strThemeCSS="style_green"; }
if(strTheme.equals("BL")) { strThemeClass="btn-primary-blue"; strThemeCSS="style";  }
if(strTheme.equals("RD")) { strThemeClass="btn-danger"; strThemeCSS="style_red";  }
if(strTheme.equals("OR")) { strThemeClass="btn-warning"; strThemeCSS="style_orange";  }
if(strTheme.equals("GY")) { strThemeClass="btn-default"; strThemeCSS="style_grey";  }
strLangFlag = (String)session.getAttribute("LangFlag"); 
if(strLangFlag==null)
	 {
		try
		{
		strLangFlag=((String)request.getParameter("txtLangFlag")).trim();
		}
		catch(Exception e)
		{
				strLangFlag="E";
		}
	 }
System.out.println("strLangFlag" + strLangFlag);         
strClntId = request.getHeader("X-FORWARDED-FOR");
if (strClntId == null) 
{
	strClntId = request.getRemoteAddr();
}
if (strClntId.indexOf(",")>0)
{
	strClntId = strClntId.substring(0,strClntId.indexOf(","));
}
String si_ClntId=strClntId;
String si_UserId="SAHAYPRTL";
String si_Theme =strTheme;

String L = "";
String si_LangFlag=strLangFlag;
if(si_LangFlag!=null)
		L=si_LangFlag;
util.HindiLocaleUtil E =new util.HindiLocaleUtil(L);



util.GenFunc.GG_SahayCmmn Logg  = new util.GenFunc.GG_SahayCmmn();
String  browserDetails  =   request.getHeader("User-Agent");
String Info=Logg.getbrowserinfo(browserDetails);
String UserDevice=(Info.substring(0,Info.indexOf('#'))).trim();
String Browser=(Info.substring(Info.indexOf('#')+1)).trim();

strAccessCount = Logg.getAccessCount(si_UserId,si_ClntId);
System.out.println("strAccessCount"+ strAccessCount);
session.setAttribute("UserID",		si_UserId.trim());
session.setAttribute("ClntID",		si_ClntId.trim());
session.setAttribute("Theme",		si_Theme.trim());
session.setAttribute("LangFlag",		si_LangFlag.trim());
session.setAttribute("AccessCount",		strAccessCount.trim());


String LangFlag=(String)session.getAttribute("LangFlag");  
String strMesg ="";
try
{
	
	 strMesg = Logg.getHomePageMsgs(si_UserId,si_ClntId,si_LangFlag,"L",strUserCheck);
      }
catch(Exception e)
{
	System.out.println("In Exception of Message:"+e.getMessage());
	strMesg=null;
	
}


%>
<% 
String strCustID=(String)session.getAttribute("custuserid");  
if(strCustID==null) strCustID="";
String custCtgr=(String)session.getAttribute("custCtgr");  
if(custCtgr==null) custCtgr="";
System.out.println("strCustID in ggheader::"+strCustID+":::::");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="colorlib.com">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Application Process for PFT</title>
<link rel="stylesheet" href="/RailSAHAY/resources/css/process-step.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="/RailSAHAY/resources/js/bootstrap.min.js"></script>

</head>
<body>
<div class="main">
<div class="container-step">
<form method="POST" id="signup-form" class="signup-form" action="#">
<div>
<!--Block1-->
<h3>Eligibility Criteria</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Eligibility Criteria</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    		<li class="list-group-item">Producers or consumers of the Goods to be transported by Rail</li>
		<li class="list-group-item">PSUs, Central Public Sector Enterprises</li>
		<li class="list-group-item">Logistics providers</li>
		<li class="list-group-item">Port Owners /Poet Rail companies</li>
		<li class="list-group-item">Mine Owners</li>
		<li class="list-group-item">Wagon Leasing Company (WLC) for use of end users</li>
  </ul>
  </div>
</div>
</fieldset>
<!--Block2-->
<h3>Applicant's Detail</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Applicant's Detail</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Application, along with specific details Number of rakes required, Types of Wagons, Loading Station(s) Destination Station(s), proposed specific route(s) or Close circuit and any other information relevant to the proposal</li>
  </ul>
  </div>
</div>
</fieldset>
<h3>Application/Registration</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Application/Registration</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Application, along with specific details of the proposal, should be submitted to Executive Director/ Freight Marketing (EDFM) Railway Board. The proposal shall be examined in consultation with Traffic Transportation (TT) Directorate of Railway Board</li>
    <li class="list-group-item">If the proposal is found operationally feasible, an approval letter from Ministry of Railways permitting Procurement of Rakes under GPWIS on the approved circuit shall be issued for Principal Chief Operating Manager (PCOM) and Principal Chief Commercial Manager (PCCM) of Concerned Zonal Railways</li>
    <li class="list-group-item">On the basis of the approval of the Railway Board, an agreement will be signed between the PCCM of the concerned Zonal Railway and the applicant within 06(Six) months from the date of approval from Railway Board</li>    
  </ul>
  </div>
</div>
</fieldset>
<h3>Procurement of Wagon</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Procurement of Wagon</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Wagon should be procured in units of full rake with 4% maintenance spares and on brake van. A minimum of one Rake has to be invested in, to participate in the scheme</li>
    <li class="list-group-item">Information regarding placement of order for procurement of rakes may be advised by party (signatory to agreement) to the PCOM of the concerned Zonal Railway as well as EDFM, Railway Board. The Zonal Railway will keep the details of the wagons and brake van procured for each rake by the Party (signatory to agreement)</li>
    <li class="list-group-item">The rakes procured under GPWIS will operate between Private siding/ terminals or Private Freight terminal (PFTs) or Inland Container Depots(ICDs) or Ports or Mines equipped to handle the traffic for which GPWIS end users must have a tie-up with such  private siding/terminal , PFTs, ICDs, Ports, mines or own its private terminals/ siding for handling of such wagons</li>
    <li class="list-group-item">Can also operate from Railway goods-sheds/terminals, if approved by Principal Chief Operations Manager (PCOM) after recommendation of Principal Chief Commercial Manager (PCCM)</li>

  </ul>
  </div>
</div>
</fieldset>
<h3>Signing of Agreement</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Signing of Agreement</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">After approval from Railway Board, an agreement will be signed between Zonal Railway and Applicant within 6months from date of approval from Railway Board</li>
    <li class="list-group-item">The period of agreement for each rake will be for the codal life of the specific stock as specified at the time of induction of the rake by the Ministry of Railways</li>
  </ul>
  </div>
</div>
</fieldset>
<h3>Operation/Maintenance <br>of Wagon</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Operation/Maintenance of Wagon</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">The Rake inducted under GPW Schemes shall not be merged in IR's pool of wagons and will be indicated through a colour scheme. The rake so inducted shall run on pre- approved circuits</li>
    <li class="list-group-item">A rebate of 10% shall be given on the base freight on each loaded wagon. Such rebate shall, however, be ordinarily for a period of 15 years subject to a cap to the extent of the lease charges payable by IR to IRFC for procurement of Rolling Stock</li>
  </ul>
  </div>
</div>
</fieldset>
</div>
</form>
</div>
</div>
<script src="/RailSAHAY/resources/js/jquery-3.3.1.min.js"></script>
<script src="/RailSAHAY/resources/js/jquery-validate.min.js" type="091c7803da4b7e22aa0d05c9-text/javascript"></script>
<script src="/RailSAHAY/resources/js/jquery-steps.min.js" type="091c7803da4b7e22aa0d05c9-text/javascript"></script>
<script src="/RailSAHAY/resources/js/process-step.js" type="091c7803da4b7e22aa0d05c9-text/javascript"></script>
<script src="/RailSAHAY/resources/js/rocket-loader.min.js" data-cf-settings="091c7803da4b7e22aa0d05c9-|49" defer=""></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/cute-alert.js"></script>
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/homepage.js"></script>
</body>
</html>
