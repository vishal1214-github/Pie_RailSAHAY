<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"GALLERY","/RailSAHAY/pages/AboutUs.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script>
$(document).ready(function()
{
	fetchSAHAYStats();
});
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("GALLERY")%></h3>
              </div>
            </div>
           </div>
          </div>
<div class="container">
	<%--
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card">
	  <div class="card-header" style="font-weight:600;">
	    <%=E.EH("Indian Railways Freight Business")%>
	  </div>
	  <div class="card-body">
	    <p class="card-text">
	    <%=E.EH("We, the Indian Railways, are the backbone of logistics sector of the country. We carry more than 1.2 Billon tonnes of freight traffic every year over 68000 km of our network.  With our very wide network touching almost every nook and corner of the country, we play a crucial role in facilitating a balanced and inclusive socio economic development of the country.  We carry almost all commodities including bulk commodities like Coal, Iron ore, Iron & Steel, Food grains, Cement, Petroleum products, Fertilizer, containers and others. We are proud of serving more than 4,000 registered customers. We are the most environment friendly mode of land transportation system.  We are committed to offer most competitive rates for transportation and timely delivery of cargo")%>.
	    </p>
	  </div>
	</div>
	  <div class="row statsrow">
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statwgoncont"></p>
                    <p class="statslbl"><%=E.EH("Wagons")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statwgontype"></p>
                    <p class="statslbl"><%=E.EH("Wagon Types")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statlococont"></p>
                    <p class="statslbl"><%=E.EH("Locomotives")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statcmdtcont"></p>
                    <p class="statslbl"><%=E.EH("Commodities")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statepaycust"></p>
                    <p class="statslbl"><%=E.EH("e-Payment Customers")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statindt"></p>
                    <p class="statslbl"><%=E.EH("Indents a Day")%></p>
                </div>
          </div>
--%>
	<div class="container">
	
	  <h3 class="font-weight-light text-center text-lg-left mt-4 mb-0"><%=E.EH("Gallery")%></h3>
	
	  <hr class="mt-2 mb-5">
	
	  <div class="row text-center text-lg-left">
	
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight1.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt1Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight2.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt2Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight3.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt3Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight4.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt4Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight5.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt5Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight6.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt6Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight7.jpg');"class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt7Thumb.jpg" alt="">
	          </a>
	    </div>
	    <div class="col-lg-3 col-md-4 col-6">
	      <a href="javascript:void(0)" onclick="openPopUpContent('/RailSAHAY/resources/images/Freight8.jpg');" class="d-block mb-4 h-100">
	            <img class="img-fluid img-thumbnail" src="/RailSAHAY/resources/images/Frgt8Thumb.jpg" alt="">
	          </a>
	    </div>
	  </div>
	
	</div>
      </div>
      </div>
</div>
<input type="hidden" name="toPage1" id="toPage1" value="AboutUs" />
	       <%@ include file="/pages/GG_Footer.jspf" %>
