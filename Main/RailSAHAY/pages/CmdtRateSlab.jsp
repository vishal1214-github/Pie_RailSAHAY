<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Commodities: RATE SLABS","/RailSAHAY/pages/CmdtRateSlab.jsp",UserDevice,Browser);
String strBrochureFlag=((String)HtParamCntl.get("CMDT_BROCHURE")).trim();
if(strBrochureFlag==null)
	strBrochureFlag="N";

%>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script src="/RailSAHAY/resources/js/cmdtview.js"></script>
<link href="/RailSAHAY/resources/css/rateslab.css" rel="stylesheet" />
<script>
$(document).ready(function() {
	<% if(!GG_SubCmdtCode.equals("")) { %>
	fetchCmdtFrgtClss('<%=GG_SubCmdtCode%>');
	$("#hSubCmdtHead").html('<%=GG_SubCmdtName%>');
	<% } else { %>
	fetchNumCmdtList('<%=GG_MainCmdtCode%>','RATE_SLAB');
	$("#hSubCmdtHead").html('<%=GG_MainCmdtName%>');
	<% } %>
});
</script>
<div class="container-fullwidth">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb" style="background:#efefef;padding-left:10px;font-weight:600;">
    <li class="breadcrumb-item" style="font-weight:600;"><a href="javascript:void(0)" onclick="showIndustry('<%=GG_MainCmdtCode%>');"><%=GG_MainCmdtName%> (<%=GG_SubCmdtName%>)</a></li>
    <li class="breadcrumb-item active" style="font-weight:600;" aria-current="page"><%=E.EH("Rate Slabs")%></li>
  </ol>
</nav>
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-2" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RATE SLABS")%></h3>
      		<h4 class="mb-4 subcmdthead" data-aos="fade-up" data-aos-delay="100" id="hSubCmdtHead"></h4>
              </div>
            </div>
           </div>
          </div>
<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
	<div class="image-wrapper">
		<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statcmdtcont"></span>&nbsp;Economical and competitive Rate Slabs across varied distance range for your <%=GG_MainCmdtName%> transportation</h2>
		<img src="/RailSAHAY/resources/images/industry/RS1.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	</div>					
</div>	
</div></div>

<% if(GG_SubCmdtCode.equals("")) { %>
<div class="container">

  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Rate Slabs for your planned freight transportation")%></h3>

  <hr class="mt-2 mb-2">

  <div class="row text-center text-lg-left">
    <div class="col col-lg-8 offset-lg-2 col-sm-12">
    <div class="search_wrap" style="width:100%;">
     <div class="container">			
	<div class="block_wrap clearfix row">                          
	 <select data-size="10" data-live-search="true" class="custom_selectbox selectpicker btn-primary fill_selectbtn_in own_selectbox inptcap" data-title='<%=E.EH("Select your Commodity")%>' id="txtCmdt" data-width="100%">
	 </select> 
	</div>
     </div>
    </div>

  </div>

</div>
<% } %>
<hr class="mt-2 mb-2">
</div>

<div class="container">
<div class="row">
<div class="col-lg-4 col-sm-12 mb-2" id="divRateClss"></div>
<div class="col col-lg-4 col-sm-6 col-timeline">
<div class="card card-timeline">
  <div class="card-header" id="tlHeader"></div>
  <div class="card-body">
<div id="content">
  <ul class="timeline" id="ulTLTimeline">
  </ul>
</div>
  </div>
</div>
</div>
<div class="col col-lg-4 col-sm-6 col-timeline">

<div class="card card-timeline">
  <div class="card-header" id="wlHeader"></div>
  <div class="card-body">
<div id="content">
  <ul class="timeline" id="ulWLTimeline">
  </ul>
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

</div>
</div></div>
<input type="hidden" name="toPage1" id="toPage1" value="RateSlab" />
	       <%@ include file="/pages/GG_Footer.jspf" %>
