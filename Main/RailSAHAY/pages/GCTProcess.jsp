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
<script>
function showRaise()
{
	window.parent.location.href = "/RailSAHAY/pages/RaiseRequestGCT.jsp";
}
</script>
</head>
<body>
<div class="main">
<div class="container-step">
<form method="POST" id="signup-form" class="signup-form" action="#">
<div>

<!--Block1-->

<h3>About GCT Policy</h3>
<fieldset style="margin-top:0px;">
<div class="card">
  <div class="card-header headstep">About GCT Policy</div>
  <div class="card-body">
	<ul class="list-group list-group-flush">
		<li class="list-group-item">1.1 This policy seeks to promote proliferation of new Cargo Terminals and improve existing Cargo Terminals to accelerate the growth in Railways cargo traffic. All new as well as under-construction/under-approval Cargo Terminals (approved/proposed under <strong>Private Siding</strong> or <strong>Private Freight Terminal</strong> policies) shall be covered by this Gati Shakti Multi-Modal Cargo Terminal (GCT) policy.</li>
		<li class="list-group-item">1.2 Existing Cargo Terminals, governed by <strong>Private Siding</strong> or <strong>Private Freight Terminal</strong> policies, shall have the option to continue with their existing Agreements, or to migrate to this new GCT policy</li>
	</ul>
  </div>
</div>
</fieldset>
<h3>Eligibility for GCT Operators (GCTO)</h3>
<fieldset style="margin-top:0px;">
<div class="card">
  <div class="card-header headstep">Eligibility for GCT Operators (GCTO)</div>
  <div class="card-body">
                            <table id="tblSchm" class="display table table-striped table-bordered tabformat table-sm table-responsive">
                            <thead>
                             <tr>
                             <th class="align-middle" class="w-2"> Category</th>
                             <th tyle="text-align:center;" class="w-28">Supporting Documents(self-attested copies to be submitted/uploaded)</th>
                             </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Individual/sole Proprietorship Firm</td>
                                    <td>PAN and TAN</td>
                                </tr>
                                <tr>
                                    <td>Hindu Undivided Family (HUF)</td>
                                    <td>
                                        <ul>
                                            <li>PAN</li>
                                            <li>Notarized Affidavit declaring that the signatory is the &apos;Karta&apos; of the HUF and has the authority, power and consent of other members</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Partnership Firm</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Notarized copy of the Partnership Deed/Partnership Deed registered with the Registrar</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of the individual to sign on behalf of, and to create liability against the firm</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company registered under Companies Act 2013</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Memorandum of Association (MoA)/Articles of Association (AoA)</li>
                                            <li>Certificate of Incorporation</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the company</li>
                                            <li>Resolution of the Directors of the Company, permitting the company to setup and/or operate a GCT</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Limited Liability Partnership (LLP)</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>LLP Agreement</li>
                                            <li>Certificate of Incorporation</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the LLP</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Registered Society/Registered Trust</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Certificate of Registration</li>
                                            <li>Memorandum of Association of Society/Trust Deed</li>
                                            <li>Rules & Regulations of the Society</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the Society/Trust</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Joint venture (JV) / Consortium</td>
                                    <td>
                                        <ul>
                                            <li>JV/Consortium Agreement</li>
                                            <li>Name and authorization of lead member</li>
                                        </ul>
                                        (Note. On receipt of approval of Railway Administration for setting- up and/or operation of GCT, JV/Consortium shall have to convert into a legal entity &minus; Firm/ Company/ LLP. Agreement shall be entered into by RA with the above legal entity only.)
                                    </td>
                                </tr>
                            </tbody>
                </table>
  </div>
</div>
</fieldset>

<h3>Procedure</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">Procedure for GCT (on Railway Land /Non Railway land)</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
	<!--<li class="list-group-item">Online apply on <a href="http://ircep.gov.in/PVTSDG" class="card-link1" target="_blank">http://ircep.gov.in/PVTSDG</a> with complete details.</li>-->
	<li class="list-group-item">Online apply <a href="javascript:void(0);" onclick="showRaise();" class="card-link1" >here</a> with complete details.</li>
	<li class="list-group-item">Divisional Railway Manager (DRM) of the concerned Division shall be the Nodal Officer for all issues regarding setting up and/or operation of a GCT.</li>
	<li class="list-group-item">During approval and construction stage for setting up a new GCT. Senior Divisional Operations Manager (Sr.DOM) of the concerned divsion shall be the co-ordinating officer and after commissioning of the GCT, Senior Divisional Commercial Manager (Sr.DCM) shall be the co-coordinating officer.</li>
	<li class="list-group-item">The applicant will be required to submit an application to the DRM through on-line mode, along with an Application Fee of Rs. Twenty Thousand(Rs.20,000/-)(+ GST as applicable) in case of non-Railway land  - either on-line or through a Demand Draft in favour of Senior Divisional Finance Manager (Sr.DFM) of the concerned Division. The online application shall be accepted with complete details only.</li>
	<li class="list-group-item">Selection for the GCTO (for entirely /partially Railway land) will be based on open tender. The tenders for which will be invited separately. </li>
	<!--<li class="list-group-item">The Tender committee shall comprise Sr. DOM/Sr.DCM (convenor), Sr.DFM (finance member), and Sr.DEN third member). DRM/ADRM shall be the Tender Accepting Authority.</li>
	<li class="list-group-item">Earnest Money for participation shall be 10% of the estimated project cost of the terminal. For the successful bidder, this Earnest Money shall be converted into Performance Guarantee, while the Earnest Money for unsuccessful bidders shall be refunded</li>-->
  </ul>
  </div>
</div>
</fieldset>
<!--Block2-->
<h3>In Principle Approval</h3>
<fieldset>
<div class="card">
  <div class="card-header headstep">In Principle Approval</div>
  <div class="card-body">
  <ul class="list-group list-group-flush">
	<li class="list-group-item">In-Principle Approval (IPA) will be granted to the applicant with the approval of DRM- within twenty (20) days of the receipt of the application.</li>
	<li class="list-group-item">Within four (04) weeks of the receipt of IPA, the applicant will submit details of the project along with Engineering Scale Plan(ESP)of the serving station/new Block Hut/Block station to the DRM along with complete details.</li>
	<li class="list-group-item">Security Deposit- The applicant will be required to submit a Security Deposit of Rs.Ten (10) lakh only, which shall be refunded* on the date of commercial notification of the GCT.</li>
	<li class="list-group-item">NO departmental charges levied on the Applicant.</li>
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
	<li class="list-group-item">The progress of the approval process would be reported regularly by Railway Authority on this portal.</li>
	<li class="list-group-item">After completion of work safety trial and final inspection will be conducted by the Railway on the request of the party.</li>
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
    <li class="list-group-item">An Agreement for GCT shall be signed be for the issue of commercial notification and operation of the GCT by Railway. Sr.DCM of the concerned Division shall be the signatory of the Agreement.</li>
	<li class="list-group-item">For existing Terminals migrating to this Policy, the period of Supplementary Agreement (to be signed as per the provisions of this Policy) shall be co-terminus with the existing Agreement.</li>
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
