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

<h3>Nodal/Coordinating<br>Officer for Approval</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Nodal/Coordinating Officer for Approval</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Senior Divisional Operations Manager (Sr.DOM) of the division which is closest to the plant is the coordinating officer for all purpose of communication and inquiry</li>
  </ul>
  </div>
</div>
</fieldset>

<h3>In Principle Approval</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">In Principle Approval</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Online apply on <a href="http://ircep.gov.in/PVTSDG" class="card-link1" target="_blank">http://ircep.gov.in/PVTSDG</a> with complete details</li>
    <li class="list-group-item">Submission of Hard copy with application fee (20 thousand) in favour to Financial Advisor and Chief Accounts OFficer (FA&CAO)/Zonal Railway</li>
    <li class="list-group-item">The hard copy of the application should include preliminary survey, conceptual plan and approximate traffic projections</li>
    <li class="list-group-item">In principle approval will be communicated to the applicant by the Senior Divisional Operations Manager (Sr.DOM) within 47 days of application for the same</li>
  </ul>
  </div>
</div>
</fieldset>
<!--Block2-->
<h3>Detailed Project Report</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Detailed Project Report (DPR)</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Applicant shall to submit DPR to Senior Divisional Operations Manager (Sr.DOM) within 45 days of receiving in principle approval</li>
    <li class="list-group-item">The DPR should be submitted after depositing departmental charges. The estimated cost should be mentioned in the DPR and should exclude the cost of land acquired by the party and also the cost of track network utilized by the party for their internal use</li>
    <li class="list-group-item">1% of the estimated cost should be deposited with the DPR</li>
    <li class="list-group-item">The end user should also decide the agency executing the work  (it can be Railway or approved consultant of Railway)</li>
    <li class="list-group-item">
    	<div style="width:100%;overflow-x:scroll;">
    	<table class="table table-striped table-bordered">
    	<tr>
    		<th rowspan="2">SR.No.</th>
    		<th rowspan="2">Executing Agency for Project</th>
    		<th rowspan="2">Departmental charges (Inclusive of  Cost of Tools & Plants And Establishment Supervision)</th>
    		<th colspan="4">Stages of Payment</th>
    	</tr>
    	<tr>
    		<th>Approval of undertaking of Survey</th>
    		<th>Conveying approval to survey/[plan and estimates (Inclusive of amount deposited with Railway mentioned in Cal. 4) (w.r.t. detailed estimated cost of project)</th>
    		<th>Before commencement of execution of work (w.r.t detailed estimated cost of project)</th>
    		<th>Applying for final approval of completed works (Balance cost by adjusting already deposited w.r.t. detailed estimated cost of project)(w.r.t total completion cost of project)</th>
    	</tr>
    	<tr>
    		<td class="head-bold">1</td>
    		<td class="head-bold">2</td>
    		<td class="head-bold">3</td>
    		<td class="head-bold">4</td>
    		<td class="head-bold">5</td>
    		<td class="head-bold">6</td>
    		<td class="head-bold">7</td>
    	</tr>
    	<tr>
    		<td>1</td>
    		<td>Railways</td>
    		<td>12-1/2%</td>
    		<td>1%</td>
    		<td>2%</td>
    		<td>8-1/2%</td>
    		<td rowspan="4">% age worked out as under {(column 3) minus(Column 5) Minus (Column 6)]}</td>
    	</tr>
    	<tr>
    		<td>2</td>
    		<td>Party</td>
    		<td>6-1/4%</td>
    		<td>1%</td>
    		<td>2%</td>
    		<td>2-1/4%</td>
    	</tr>
    	<tr>
    		<td rowspan="2">3</td>
    		<td rowspan="2">Approved Consultant</td>
    		<td>4% (For all works except OHE and S&T Works)</td>
    		<td>1%</td>
    		<td>2%</td>
    		<td>Nil</td>
    	</tr>
    	<tr>
    		<td>6-1/4% (For OHE and S&T Works)</td>
    		<td>1%</td>
    		<td>2%</td>
    		<td>2-1/4%</td>
    	</tr>
	</table>
	</div>
    </li>
    <li class="list-group-item">DPR will be approved within 70 days of submission</li>
    <li class="list-group-item">On the basis of DPR after receiving of DPR, SIP (Signaling Plan)  and diagrams of sectioning, wiring, LOP, SED, Power supply  should be submitted for approval</li>
    <li class="list-group-item">1% of the final estimate should again be deposited for starting the construction</li>
  </ul>
  </div>
</div>
</fieldset>
<h3>Construction and <br/>Monitoring the progress</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Construction and Monitoring of Progress</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">The progress should be report regularly</li>
    <li class="list-group-item">Progress shall be updated online on <a href="http://ircep.gov.in/PVTSDG" class="card-link1" target="_blank">http://ircep.gov.in/PVTSDG</a> for tracking and monitoring by applicant</li>
    <li class="list-group-item">After completion of work safety trial and final inspection will be conducted by the Railway on the request of the party</li>
  </ul>
  </div>
</div>
</fieldset>
<h3>Signing of Agreements</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Signing of Agreements</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">The provisional "Integrated siding agreement" (ISA) will be signed, which would be signed after completion of work and before notification</li>
    <li class="list-group-item">"Land licensing agreement" shall be signed before start of physical work for providing connectivity to private siding</li>
    <li class="list-group-item">"Agreement for private siding" shall be signed before issue of commercial notification and operation on the siding by the Railway. Sr.DCM of the concerned division shall be signatory of the private siding agreement</li>
  </ul>
  </div>
</div>
</fieldset>
<h3>Notification of Siding</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Notification of Siding</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">After issuance of the safety & completion certificate along with the no dues certificate from concerned engineering department, notification will be issued by the Railway authorities</li>
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
