<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RATE SLABS","/RailSAHAY/pages/RateSlab.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/aGTCmdt.js"></script>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script src="/RailSAHAY/resources/js/rateslab.js"></script>
<link href="/RailSAHAY/resources/css/rateslab.css" rel="stylesheet" />
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RATE SLABS")%></h3>
              </div>
            </div>
           </div>
          </div>
<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
	<div class="image-wrapper">
		<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statcmdtcont"></span>&nbsp;Unique commodities being handled by Indian Railways Freight Operations</h2>
		<img src="/RailSAHAY/resources/images/cmdtimage.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	</div>					
</div>	
</div></div>
	<div class="container">
	
	  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Rate Slabs for your planned freight transportation")%></h3>
	
	  <hr class="mt-2 mb-2">
	
	  <div class="row text-center text-lg-left">
	    <div class="col col-lg-8 offset-lg-2 col-sm-12">
	    <div class="search_wrap" style="width:100%;">
	     <div class="container">			
		<div class="block_wrap clearfix row">                          
		 <select data-size="10" data-live-search="true" class="custom_selectbox selectpicker btn-primary fill_selectbtn_in own_selectbox inptcap" data-title='<%=E.EH("Select a Group Commodity")%>' id="txtRakeCmdt" data-width="100%">
		 </select> 
		 <select id="txtCmdt" class="btn-secondary select-cmdt fill_selectbtn_in own_selectbox inptcap mt-3">
		 </select> 

                </div>
             </div>
            </div>
	  </div>
	  </div>
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
</div>
</div></div>
<input type="hidden" name="toPage1" id="toPage1" value="RateSlab" />
	       <%@ include file="/pages/GG_Footer.jspf" %>
