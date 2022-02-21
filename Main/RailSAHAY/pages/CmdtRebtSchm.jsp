<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import = "javax.servlet.*, javax.servlet.http.*"%>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Commodities: REBATE SCHEMES","/RailSAHAY/pages/CmdtRebtSchm.jsp",UserDevice,Browser);
String strCmdtName="";
if(GG_SubCmdtCode.equals(""))
	strCmdtName=GG_MainCmdtName;
else
	strCmdtName=GG_SubCmdtName;
	
String strBrochureFlag=((String)HtParamCntl.get("CMDT_BROCHURE")).trim();
if(strBrochureFlag==null)
	strBrochureFlag="N";
%>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<style>
.pschmname
{
	font-size:1rem;
	font-weight:600;
	color:#000;
	margin-block-end:0px !important;
}
.pmstrrc
{
	font-size:0.8rem;
	font-weight:600;
	color:#b32400;
	margin-block-end:0px !important;
}
.pltstrc
{
	font-size:0.8rem;
	font-weight:600;
	color:#b32400;
	margin-block-end:0px !important;
}
.mstrrcdate
{
	color:#4d4d4d;
	font-weight:500;
	margin-left:10px;
}
.ltstrcdate
{
	color:#4d4d4d;
	font-weight:500;
	margin-left:10px;
}
</style>
<div class="container-fullwidth">

<nav aria-label="breadcrumb">
  <ol class="breadcrumb"  style="background:#efefef;padding-left:10px;font-weight:600;">
    <li class="breadcrumb-item" style="font-weight:600;"><a href="javascript:void(0)" onclick="showIndustry('<%=GG_MainCmdtCode%>');"><%=GG_MainCmdtName%> (<%=GG_SubCmdtName%>)</a></li>
    <li class="breadcrumb-item active" style="font-weight:600;" aria-current="page"><%=E.EH("Incentive Schemes")%></li>
  </ol>
</nav>
<div class="row align-items-center justify-content-center text-center">
  <div class="col-lg-12">

    <div class="box-shadow-content">
      <div class="block-heading-1" style="margin-top:15px;">
	<h3 class="mb-2" data-aos="fade-up" data-aos-delay="100"><%=E.EH("INCENTIVE SCHEMES")%></h3>
	<h4 class="mb-4 subcmdthead" data-aos="fade-up" data-aos-delay="100" id="hSubCmdtHead"><%=strCmdtName%></h4>
      </div>
    </div>
   </div>
  </div>
<div class="container-fullwidth">
  <div class="row header-image">
<div class="col-sm-12" align="center">
	<div class="image-wrapper">
		<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;Offering a number of Freight Incentive Schemes to serve your logistics needs for <%=strCmdtName%></h2>
		<img src="/RailSAHAY/resources/images/industry/rebt2.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	</div>					
</div>	
</div></div>
<hr class="mt-2 mb-2">

<div class="container">
<%--
  <div class="row">
  <div class="col-lg-12 ml-1">         
	<div class="mt-2 mb-3 text-center small" id="divColrInd" align="right">
	      <span class="mr-1">
		<i class="fas fa-circle text-primary"></i> Master Rate Circular (RC)
	      </span>
	      <span class="mr-1">
		<i class="fas fa-circle text-success"></i> Latest Rate Circular (RC)
	      </span>	                     
	 </div>
	</div>--%>
  <div class="row">
  <div class="col-lg-10 offset-lg-1 col-sm-12">         

<div id="divRebtSchm">

</div>
</div>
</div>
</div>
</div>

<% if(strBrochureFlag.equals("Y")) { %>
<hr/>
<div class="container">
<div class="card-brochure" onclick="downloadMainCmdtPDF();">
		<p class="brochure-head">Brochure: <%=GG_MainCmdtName%></p>
		<p class="brochure-dn">Download PDF | 2MB</p>
</div> 
<% if(!GG_SubCmdtName.equals("")) { %>
	<div class="card-brochure" onclick="downloadSubCmdtPDF();">
		<p class="brochure-head">Brochure: <%=GG_SubCmdtName%></p>
		<p class="brochure-dn">Download PDF | 2MB</p>
	</div>

<form id="frmSubBrochure" name="frmSubBrochure" action="/RailSAHAY/CommodityPDF" method="post" target="SubCmdtBrochure">
	<input type="hidden" id="MainCmdtCode" name="MainCmdtCode" value="<%=GG_MainCmdtCode%>" />
	 <input type="hidden" id="MainCmdtName" name="MainCmdtName" value="<%=GG_MainCmdtName%>" />
	 <input type="hidden" id="SubCmdtCode" name="SubCmdtCode" value="<%=GG_SubCmdtCode%>" />
        <input type="hidden" id="SubCmdtName" name="SubCmdtName"  value="<%=GG_SubCmdtName%>" />
</form>
<% } %>
</div>
<form id="frmMainBrochure" name="frmMainBrochure" action="/RailSAHAY/CommodityPDF" method="post" target="MainCmdtBrochure">
	<input type="hidden" id="MainCmdtCode" name="MainCmdtCode" value="<%=GG_MainCmdtCode%>" />
	 <input type="hidden" id="MainCmdtName" name="MainCmdtName" value="<%=GG_MainCmdtName%>" />
	 <input type="hidden" id="SubCmdtCode" name="SubCmdtCode" value="" />
        <input type="hidden" id="SubCmdtName" name="SubCmdtName"  value="" />
</form>
<% } %>

<script src="/RailSAHAY/resources/js/cmdtview.js"></script>
<script>
$(document).ready(function() {
	<% if(!GG_SubCmdtCode.equals("")) { %>
	getCmdtRebtSchm('<%=GG_SubCmdtCode%>');
	<% } else { %>
	fetchNumCmdtList('<%=GG_MainCmdtCode%>','REBT_SCHM');
	$("#hSubCmdtHead").html('<%=GG_MainCmdtName%>');
	<% } %>
});
</script>

<input type="hidden" name="toPage1" id="toPage1" value="FindCmdtWgon" />
       <%@ include file="/pages/GG_Footer.jspf" %>
