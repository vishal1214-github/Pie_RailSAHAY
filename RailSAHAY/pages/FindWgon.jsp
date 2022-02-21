<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FIND THE RIGHT WAGON","/RailSAHAY/pages/FindWgon.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/aGTCmdt.js"></script>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script src="/RailSAHAY/resources/js/findwgon.js"></script>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("FIND THE RIGHT WAGON")%></h3>
              </div>
            </div>
           </div>
          </div>
<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
					<div class="image-wrapper">
						<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
						<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;Unique Wagon Types to serve your Freight Transportation needs with Indian Railways</h2>
						<img src="/RailSAHAY/resources/images/wgonimage5.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
					</div>					
			</div>	
</div></div>
	<div class="container">
	
	  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Search a Right Wagon for your freight transportation")%></h3>
	
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
<div class="row" id="divWgonTypeList">
	
</div>
</div>
      </div>
      </div>
</div>

<input type="hidden" name="toPage1" id="toPage1" value="FindWgon" />
	       <%@ include file="/pages/GG_Footer.jspf" %>
