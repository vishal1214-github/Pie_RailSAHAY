<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="/pages/GG_Header.jspf" %>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/process-step.css">
<script>
$(document).ready(function()
{
	fetchSAHAYStats();
});
</script>
<% 
	strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"OWN A PRIVATE FREIGHT TERMINAL","/RailSAHAY/pages/OwnTrmlGCT.jsp",UserDevice,Browser);
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date dateobj = new Date();
	String strCrntDate=df.format(dateobj);
%>

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("OWN YOUR TERMINAL")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card mr-3 ml-3">
	  <div class="card-header head-faq">
	    <%=E.EH("Invest in Indian Railways for a Terminal")%>
	  </div>
	  <div class="card-body">
		   <div class="row">
		   <div class="col-lg-6 col-sm-12" style="text-align: justify;">
		    <b><%=E.EH("Railway Freight Terminals</b>")%></b> 
		     <%=E.EH("Loading and Unloading of goods transported by rail is done at freight terminals")%><br/>
		     <%=E.EH("There are following four types of Railway Freight Terminals")%>:
		   <ul>
		   <li><b style="color:#000;"><%=E.EH("Railway owned Goods sheds")%></b> <%=E.EH("or railway sidings on Railway land")%>: <%=E.EH("Indian Railways encourage private participation in Development of Goods-sheds at small/road-side stations.  This policy is aimed at augmenting terminal capacity through private participation by allowing setting up of new goods-shed facilities (viz.goods wharf, loading/unloading facilities, facilities for labour (resting space with shade, drinking water, bathing facilities, etc) approach road, covered shed and other related infrastructure) and developing existing goods-sheds at a larger number of stations")%></li>
		   <li><b style="color:#000;"><%=E.EH("Private Sidings")%></b> <%=E.EH("are built on private land in the manufacturing/mining plant of the company for convenience so that Railway wagons get loaded and unloaded in the premises under a special arrangement. To promote use of these sidings by other rail users, Indian Railways has allowed loading/unloading in these private sidings by any user with the permission of the private siding owner")%></li>
		   <li><b style="color:#000;"><%=E.EH("Privately owned Container Handling Terminals")%></b> <%=E.EH("set up on private land by Container Train Operators (CTOs) in accordance with the provisions of <a href='javascript:void(0)' class='card-link1' onclick=openExtnLink('Master Concession Agreement','https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_tran/downloads/2012/MCA Master Copy.pdf')>Master Concession Agreement (MCA)</a> (including those created by CONCOR on private land before introduction of MCA)")%></li>
		   <li><b style="color:#000;"><%=E.EH("Privately Freight Terminals (PFT)")%></b> <%=E.EH("set up by the Terminal Management Company (TMC) on private land through private investment in terms of PFT policy for handling of all types of traffic unless and otherwise notified under the policy")%></li>
		   <li><b style="color:#000;"><%=E.EH("Gati-Shakti Multimodal Cargo Terminal (GCTO)")%></b> <%=E.EH("Gati-Shakti Multimodal Cargo Terminals of railways will be terminals where different modes of transportation will be integrated seamlessly with the railway transportation network.With introduction of GCTO policy in December 2021 by Indian Railways, the Private sidings as well as PFTs will be governed by GCTO policy. The GCTO will be set up by private logistics partners under the <strong>GCT Policy</strong>")%>
		   <%--<br/>
		   <b style="color:#000;"><%=E.EH("What is Gati-Shakti Multimodal Cargo Terminal")%></b> <br/><%=E.EH("1.1 This policy seeks to promote proliferation of new Cargo Terminals and improve existing Cargo Terminals to accelerate the growth in Railways cargo traffic. All new as well as under-construction/under-approval Cargo Terminals (approved/proposed under Private Siding or Private Freight Terminal policies) shall be covered by this Gati Shakti Multi-Modal Cargo Terminal (GCT) policy")%>.<br/><%=E.EH("1.2 Existing Cargo Terminals, governed by Private Siding or Private Freight Terminal policies, shall have the option to continue with their existing Agreements, or to migrate to this new GCT policy")%>.<!--<br/><p style="font-weight:600;color:#000;">Check eligibility for GCT Operators (GCTO) <a href="javascript:void(0)" onclick="showPage('GCT_ELGB');" style="font-weight:600;color:#b32400;">here </a></p>--></li>
			--%>
		   </ul>
		
			</div>
	            <div class="col-lg-6 col-sm-12" align="center">
			
			<div class="card bg-light text-dark">
  <div class="card-header"><%=E.EH("Indian Railways Statistics")%> <span class="badge badge-info float-right">As on <%=strCrntDate%></span></div>
  <div class="card-body">
 <div class="row statsrow">                
	<div class="col-6 col-md-6 col-lg-6" align="center">
                    <p class="statsfig" id="statpft"></p>
                    <p class="statslbl"><%=E.EH("Private Freight Terminals (PFTs)")%></p>
                </div>
                <div class="col-6 col-md-6 col-lg-6" align="center">
                    <p class="statsfig" id="statpvtsdng"></p>
                    <p class="statslbl"><%=E.EH("Private Sidings")%></p>
                </div>
	</div>
 </div></div>
<img src="/RailSAHAY/resources/images/pft1.jpg" />
		    </div>
		 </div>
		<br/>
		<%--
<div class="row">		
				<div class="col-lg-12 col-sm-12">
				<div class="optncard">
				<ul class="nav nav-pills">
				    <li class="nav-item">
				        <a class="nav-link active" data-toggle="tab" href="#PFTProcess"><%=E.EH("Application Process for PFT")%></a>
				    </li>
				    <li class="nav-item">
				        <a class="nav-link" data-toggle="tab" href="#PvtSdngProcess"><%=E.EH("Application Process for Private Siding")%></a>
				    </li>
				</ul>
				</div>
				<div class="optncard">
				<div class="tab-content">
				  <div class="tab-pane container-fullwidth active" id="PFTProcess">
					<iframe src="/RailSAHAY/pages/PFTProcess.jsp" style="width:100%;height:75vh;border:0px;overflow-y:scroll;margin-left:5px;margin-right:5px;position:relative;"></iframe>
				  </div>
				  <div class="tab-pane container-fullwidth fade" id="PvtSdngProcess">
					<iframe src="/RailSAHAY/pages/PvtSdngProcess.jsp" style="width:100%;height:75vh;border:0px;overflow:scroll;"></iframe>
				  </div>
				</div>
				</div>
			</div>
</div>
--%>

<div class="row">		
				<div class="col-lg-12 col-sm-12">
				<div class="optncard">
				<ul class="nav nav-pills">
				    <li class="nav-item">
				        <a class="nav-link active" data-toggle="tab" href="#GCTProcess"><%=E.EH("Application Process for GCT")%></a>
				    </li>
				</ul>
				</div>
				<div class="optncard">
				<div class="tab-content">
				  <div class="tab-pane container-fullwidth active" id="GCTProcess">
					<iframe src="/RailSAHAY/pages/GCTProcess.jsp" style="width:98vw;height:75vh;border:0px;overflow-y:scroll;margin-left:5px;margin-right:5px;position:relative;"></iframe>
				  </div>
				</div>
				</div>
			</div>
</div>
<p style="font-family:Roboto Condensed;font-size:1.1rem;font-weight:600;color:#4d4d4d;margin:1rem;">Raise a Request for GCT <a href="javascript:void(0);" onclick="showPage('RAISE_REQ_GCT');" style="font-weight:600;color:#b32400;"> here ></a></p>
<p style="font-family:Roboto Condensed;font-size:1.1rem;font-weight:600;color:#4d4d4d;margin:1rem;">Apply for GCT <a href="/RailSAHAY/pages/FBDLogin.jsp" target="_self" style="font-weight:600;color:#b32400;"> here ></a></p>

<br/>
<button class="btn btn-sm btn-info btn-madad"  onclick="gotoRailMadad();" style="margin-left:10px;"><%=E.EH("Contact Us")%>!</button>
</div></div></div></div>
<%@ include file="/pages/GG_Footer.jspf" %>
