<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"BUSINESS PARTNERSHIPS","/RailSAHAY/pages/BusinessPartner.jsp",UserDevice,Browser);
%>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<link href="/RailSAHAY/resources/css/partner.css" rel="stylesheet" />

 <nav class="navbar navbar-expand-sm bg-origprimary navbar-light"></nav>
<div class="container-fullwidth">
<div class="row align-items-center justify-content-center text-center">
  <div class="col-lg-12">

    <div class="box-shadow-content">
      <div class="block-heading-1" style="margin-top:15px;">
	<h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("BUSINESS PARTNERSHIPS")%></h3>
      </div>
    </div>
   </div>
  </div>

<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
		<div class="image-wrapper">
			<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
			<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;Explore our range of partnership options to avail numerous benefits</h2>
			<img src="/RailSAHAY/resources/images/partner1.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
		</div>					
</div>	
</div></div> <br/>
<hr/>
<div class="container">
<div class="container animate-bottom" id="divCmdtFeature">
<h2 class="heading-title">Please have a look at our partnership schemes...</h2>
</div>
    <div class="row">
        <div class="col-lg-12 mx-auto">
            <!-- Accordion -->
            <div id="accordionExample" class="accordion shadow">

                <!-- Accordion item 1 -->
                <div class="card">
                    <div id="headingOne" class="card-header card-head-yl">
                        <h2 class="mb-0">
                            <button type="button" data-toggle="collapse" style="font-family:'Josefin Sans','Roboto Condensed';" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" class="btn btn-link text-dark font-weight-bold collapsible-link">Long Term Tariff Contract (LTTC)</button>
                        </h2>
                    </div>
                    <div id="collapseOne" aria-labelledby="headingOne" data-parent="#accordionExample" class="collapse show">
                        <div class="card-body p-5">
                            <p class="font-weight-light m-0">
				<ul class="list-group list-group-flush">									
				<li class="list-group-item">Indian Railways has introduced LTTC to establish long-term contracts with customers with guaranteed incremental revenue for Indian Railways. LTTC provides freight stability to its customer in the current year of the agreement. It also offers rebate in freight based on incremental growth in Gross Freight Revenue (GFR) as well as for retention of traffic volumes in each year of contract.</li>
				<li class="list-group-item">Customers are required to apply to and sign contracts with Zonal Railways to avail this scheme.The contract can be for a duration of three to five years.</li>
				<li class="list-group-item"><strong>The main objectives of LTTC includes</strong><ul><li>Long-term revenue commitment from customers</li><li>Preferential treatment to customer for supply of wagons</li><li>Generation of additional traffic volumes and revenues for railways</li><li>freight concession on retention of traffic as well as on incremental traffic</li></ul>.</li>
				<li class="list-group-item">The Freight customer can approach  Zonal Railway of the originating station to apply for LTTC.</li>
				<li class="list-group-item"><p class="mesg-cnt">For any assistance/query regarding application for LTTC Scheme <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"> <i class="fas fa-angle-right"></i>&nbsp;Contact Us</a></p></p></li>
				</ul>
			    </p>
                        </div>
                    </div>
                </div><!-- End -->

                <!-- Accordion item 2 -->
                <div class="card">
                    <div id="headingTwo" class="card-header card-head-yl">
                        <h2 class="mb-0">
                            <button type="button" data-toggle="collapse"  style="font-family:'Josefin Sans','Roboto Condensed';"  data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" class="btn btn-link collapsed text-dark font-weight-bold collapsible-link">Station to Station (STS) Rates</button>
                        </h2>
                    </div>
                    <div id="collapseTwo" aria-labelledby="headingTwo" data-parent="#accordionExample" class="collapse">
                        <div class="card-body p-5">
                            <p class="font-weight-light m-0">
				<ul class="list-group list-group-flush">									
				<li class="list-group-item">Under STS scheme, freight customers can apply for concession in freight charges for a particular commodity for movement between originating and destination stations/clusters.</li>
				<li class="list-group-item">Incremental or New traffic is eligible to get a discount of maximum 30% (maximum discount for  Container traffic is up to 15%, and only traffic at CCR is eligible for this scheme) after crossing Benchmark Net Tonne Kilometres (NTKM), which is the average NTKM of the corresponding period of previous 24 months. There is also provision for discount on retention of traffic.</li>
				<li class="list-group-item">The percentage discount is over the Normal Tariff Rate (NTR) and is given on RR (Railway Receipt) or can be claimed at end of given period.</li>
				<li class="list-group-item">The agreement  can be signed for one  to three years.  <strong>The scheme is applicable to block rake, two point/multi point and mini rakes</strong>. </li>
				<li class="list-group-item">To avail benefit under the scheme, the desirous customer will have to submit application to Divisional Railway Manager of the concerned Division with full details of traffic offered, pair of stations between which traffic is offered along with details of previous year traffic, if any.</li>
				<li class="list-group-item"><p class="mesg-cnt">For any assistance/query regarding application for STS Scheme <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"> <i class="fas fa-angle-right"></i>&nbsp;Contact Us</a></p></p></li>
				</ul>
			     </p>
                        </div>
                    </div>
                </div><!-- End -->
        </div>
    </div>
</div>
</div>	
<script>
(function($) {
	'use strict';
	
	jQuery(document).on('ready', function(){
	
			$('a.page-scroll').on('click', function(e){
				var anchor = $(this);
				$('html, body').stop().animate({
					scrollTop: $(anchor.attr('href')).offset().top - 50
				}, 1500);
				e.preventDefault();
			});		

	}); 	

				
})(jQuery);

</script>
  

	
<%@ include file="/pages/GG_Footer.jspf" %>
