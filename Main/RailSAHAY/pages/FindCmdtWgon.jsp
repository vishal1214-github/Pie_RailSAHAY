<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import = "javax.servlet.*, javax.servlet.http.*"%>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"WAGON CATALOGUE","/RailSAHAY/pages/FindCmdtWgon.jsp",UserDevice,Browser);
String strCmdtName="";
if(GG_SubCmdtCode.equals(""))
	strCmdtName=GG_MainCmdtName;
else
	strCmdtName=GG_SubCmdtName;
%>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<div class="container-fullwidth">

<nav aria-label="breadcrumb">
  <ol class="breadcrumb"  style="background:#efefef;padding-left:10px;font-weight:600;">
    <li class="breadcrumb-item" style="font-weight:600;"><a href="javascript:void(0)" onclick="showIndustry('<%=GG_MainCmdtCode%>');"><%=GG_MainCmdtName%> (<%=GG_SubCmdtName%>)</a></li>
    <li class="breadcrumb-item active" style="font-weight:600;" aria-current="page"><%=E.EH("Wagon Catalogue")%></li>
  </ol>
</nav>
<div class="row align-items-center justify-content-center text-center">
  <div class="col-lg-12">

    <div class="box-shadow-content">
      <div class="block-heading-1" style="margin-top:15px;">
	<h3 class="mb-2" data-aos="fade-up" data-aos-delay="100"><%=E.EH("WAGON CATALOGUE")%></h3>
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
		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;Offering a number of unique Wagon Types to serve your <%=GG_MainCmdtName%> transportation with Indian Railways</h2>
		<img src="/RailSAHAY/resources/images/industry/WC2.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	</div>					
</div>	
</div></div>
<% if(GG_SubCmdtCode.equals("")) { %>
<div class="container">

  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Search a Right Wagon for your freight transportation")%></h3>

  <hr class="mt-2 mb-2">


	  <div class="row text-center text-lg-left">
	    <div class="col col-lg-8 offset-lg-2 col-sm-12">
	    <table style="width:100%;"><tr><td>
	    <input class="form-control mr-sm-2 inptcap" id="selCmdtList" list="cmdtlist"  name="selCmdtList" type="text" placeholder='Select Your Commodity' style="width:100%;">
	    <datalist id="cmdtlist"></datalist>
	    </td><td><button class="btn btn-danger" onclick="updtSubCmdt();">Continue</button></td></tr></table>
	  </div>
	
	</div>
<% } %>
<hr class="mt-2 mb-2">

<div class="container">

  <div class="row">
  <div class="col-lg-12 ml-1">         
	<div class="mt-2 mb-3 text-center small" id="divColrInd" align="right">
	      <span class="mr-1">
		<svg class="svg-inline--fa fa-circle fa-w-16 text-primary-orig" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-primary"></i> --> <span id="spnCvdCont" style="font-weight:600;"></span> Covered Wagon(s) 
	      </span>
	      <span class="mr-1">
		<svg class="svg-inline--fa fa-circle fa-w-16 text-success" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-success"></i> --> <span id="spnOpnCont" style="font-weight:600;"></span> Open Wagon(s) 
	      </span>	                     
	 </div>
	</div>
  </div>
 
  <div class="row">
  <div class="col-lg-12 ml-1">
	<div class="alert alert-info alert-dismissible alert-custmesg">Let's help you find out the <strong>Expected Freight Charges</strong> and <strong>Expected Transit Time</strong>! Please select a Wagon Type to proceed</div>
  </div></div><br/>
<div class="row" id="divWgonTypeList">

</div>
</div>
</div>
</div>
</div>

<script src="/RailSAHAY/resources/js/cmdtview.js"></script>
<script>
$(document).ready(function() {
	<% if(!GG_SubCmdtCode.equals("")) { %>
	fetchRakeCmdt('<%=GG_SubCmdtName%>');
	$("#hSubCmdtHead").html('<%=GG_SubCmdtName%>');
	<% } else { %>
	fetchNumCmdtList('<%=GG_MainCmdtCode%>','WGON_CTLG');
	$("#hSubCmdtHead").html('<%=GG_MainCmdtName%>');
	<% } %>
});
</script>

<input type="hidden" name="toPage1" id="toPage1" value="FindCmdtWgon" />
       <%@ include file="/pages/GG_Footer.jspf" %>
