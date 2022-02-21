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
  <p style="font-weight:600;">Applicant should be:</p>
  <ul class="list-group list-group-flush">
    		<li class="list-group-item">A registered company in India as per Companies Act 2013</li>
		<li class="list-group-item">A Subsidiary Company </li>
		<li class="list-group-item">A Joint Venture Company Or Partnership of companies</li>
		<li class="list-group-item">A Public sector entity in the business of logistics</li>
		<li class="list-group-item">The application should have Minimum 1 year experience in any one of the following fields
		<ol>
		<li>Transport and logistics </li>
		<li>Port and land terminal operations</li>
		<li>Warehousing </li>
		<li>Container Train Operation </li>
		<li>Manufacturers of Automobiles</li>
		<li>Wagon leasing company</li>
		</ol>
		</li>
		<li class="list-group-item">Any company which has been declared sick under sick Industrial Companies (Special Provision) Act 1985 shall not be eligible to apply under these rules and operate trains either individually or association with other companies</li>
		<li class="list-group-item">In case the applicant is subsidiary company, experience and net worth of the holding company, owning more than 50% equity in the subsidiary company, may be reckoned for the purpose of Para 3.2 and 3.3 above.</li>
		<li class="list-group-item">There shall be no change of control of the LSFTO through transfer of direct or indirect legal or beneficial ownership or control of equity or other contractual arrangement before the completion of one year from commencement of commercial operation of the LSFTO's train pursuant to the Concession Agreement, in which after wards there may be approval from MOR, and same may be rejected or approved</li>		
  </ul>
  </div>
</div>
</fieldset>
<!--Block2-->
<h3>Registration & Validity</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Registration & Validity</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Company/Party shall apply to the Principal Executive Director (PED), Executive Director-Freight Marketing (EDFM) to register as LSFTO and must invest in a minimum of 01 rake for a commodity under this scheme</li>    
    <li class="list-group-item">The concession agreement for operation of SFT would be valid for 35 years or till rakes are operational, whichever is earlier. This can be renewed by submitting an application, atleast one month before the expiry of agreement.</li>
    <li class="list-group-item">The applicant has to apply for each category separately in case he is willing to opt for more than 01 category</li>
    <li class="list-group-item">Freight charges have to be paid to the railways as per a very simple rate structure. There will be two freight rates one for loaded trains and another for the empty trains</li>
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
    <li class="list-group-item">The applicant has to apply for a minimum 1 (one) rake under this scheme</li>
    <li class="list-group-item">If application is rejected by MOR than amount will be refunded within one month of rejection of the proposal</li>    
    <li class="list-group-item">Apply to PED/EDFM along with following detail while submitting the application:
    	<ol>
    		<li>Name and address of the Applicant (firm)</li>
    		<li>Details of experience and activities of the applicant</li>
    		<li>Document in support of the eligibility criteria as above</li>
    		<li>Number of rake (Minimum 01 rake)</li>
    		<li>Type of wagon</li>
    		<li>Anticipated Traffic volume</li>
    		<li>Proposed loading terminal & destination terminals along with circuits</li>
    		<li>Any other relevant information</li>
    	</ol>
    </li>  
    <li class="list-group-item">MOR will study the proposal and grant necessary approval for registration and procurement as per the provisions of the policy</li>
    <li class="list-group-item">After that CCM/FM of the Nodal Zonal Railway will signed the agreement. The applicants will procure full rake composition with 4% additional wagon as spare and brake van</li>
    <li class="list-group-item">The registered LSFTO intends to induct additional shall apply to Executive Director, Freight Marketing (EDFM) along with detail. Conversely if he wants to withdraw any number of rakes he will be permitted to do so without any refund of any refund of registration fee</li>
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
    <li class="list-group-item">Procurement of wagons will be allowed only with prior administrative approval of MOR and must conform to applicable IRS designs and specification same will be inducted after inspection by Research Design and Standards Organization (RDSO)</li>
    <li class="list-group-item">The Applicant will be required to incorporate warranty clause in their purchase contract with wagon manufacturer</li>
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
