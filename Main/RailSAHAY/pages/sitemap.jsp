<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SITE MAP","/RailSAHAY/pages/sitemap.jsp",UserDevice,Browser);
%>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<link href="/RailSAHAY/resources/css/rateslab.css" rel="stylesheet" />
<style>
.item-link
{
	font-weight:600;
	color:#4d4d4d;
	font-size:1rem;
}
.sitemap-menu-head
{
	font-weight:600;
	color:#b32400;
	font-family:'Josefin Sans','Roboto Condensed';
	margin-top:1rem;
}
.img-sitemap
{
	height:200px;
	box-shadow: 0 2px 4px 0 rgba(0,0,0,0.2);
	margin-top:1rem;
	margin-bottom:1rem;
	border-radius:2px;
}
</style>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("SITE MAP")%></h3>
              </div>
            </div>
           </div>
          </div>
	<div class="container">
	
	  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Navigation Scheme for Freight Business Development Portal")%></h3>
	
	  <hr class="mt-2 mb-2">
	<br/>
	 <div class="container-fullwidth">
	 		<div class="row">
	 			<div class="col-sm-6 col-lg-3 col-md-6 module">
					<img src="/RailSAHAY/resources/images/sitemap/home.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("HOME")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRML_DASHBOARD");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Choose Terminal")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FIND_WGON");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Select Wagon")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FRGT_CALC");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Get Freight & Expected Transit Time")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("POLICY");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Incentive Schemes")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TIME_TABLED");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Timetabled Movement")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRACK_TRACE");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Track & Trace")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoRailMadad();' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Professional Support")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("OWN_TRML");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Own A Terminal")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("OWN_WGON");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Own A Wagon")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6 module">
					<img src="/RailSAHAY/resources/images/sitemap/newcust.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("NEW TO RAIL")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("BKNG_PROCESS");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Booking Process")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRML_DASHBOARD");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Find a Suitable Terminal")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FIND_WGON");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Select the Right Wagon")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FRGT_CALC");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Get Expected Freight Charges & Transit Time")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("POLICY");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Explore Rebate Schemes & Long-term Contracts")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("STTN_HELP");' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Connect to First & Last Mile Service Providers")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='regUser();' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("User Registration")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoeDmnd();' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Register your Demand Online")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6 module">
					<img src="/RailSAHAY/resources/images/sitemap/ecust.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("EXISTING CUSTOMERS")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoECust();' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Corporate Login")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('IND_LOGIN');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Individual Login")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRACK_TRACE');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Track & Trace")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage("FRGT_CALC");" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Freight Charges & Transit Time Estimator")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('RAISE_REQ');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Raise A Request")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRACK_REQ');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Track Your Request")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('ODR_RAS');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Indents Outstanding & Rake Allotments")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TAX_INVC');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Download GST Tax Invoice")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('POLICY');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Rebate Schemes & Long Term Contracts")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('STTN_HELP');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("First & Last Mile Service Providers")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoeDmnd();' class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("e-Registration of Indents")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6">
					<img src="/RailSAHAY/resources/images/sitemap/cmdt.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("COMMODITIES")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('COAL','COAL & COKE');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Coal & Coke")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('ORES','MINERALS & ORES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Minerals & Ores")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('FG','FOOD GRAINS, FLOURS & PULSES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Food Grains, Flours & Pulses")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CEMT','CEMENT & CLINKER');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Cement & Clinker")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CHEM','CHEMICAL MANURES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Chemical Manures")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('IS','IRON & STEEL');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Iron & Steel")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('POL','PETROLEUM PRODUCTS & GASES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Petroleum Products & Gases")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CONT','CONTAINER SERVICES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Container Services")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('AUTO','AUTOMOBILES');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Automobiles")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('SSSO','SUGAR, SALT, SPICES, OILS AND MORE..');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Sugar, Salt, Spices, Oils")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('PIGI','PIG, SPONGE, WROUGHT IRON AND MORE..');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Pig, Sponge, Wrought Iron")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('STON','STONE/BAMBOO CHIPS, GRANITE AND MORE..');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Stone/Bamboo Chips, Granite")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('OTHR','OTHERS');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("More")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6">
					<img src="/RailSAHAY/resources/images/sitemap/toolsrvc.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("TOOLS & SERVICES")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRML_DASHBOARD');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Terminal Selector")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('FIND_WGON');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Wagon Selector")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('FRGT_CALC');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Freight & Transit Time Estimator")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRACK_TRACE');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Track & Trace")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('RATE_SLAB');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Rate Slabs")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('ML_POINT');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Two-Point/Multi-Point (Permitted Combinations)")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TIME_TABLED');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Timetabled Services")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('MINI_RAKE');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Mini Rake Services")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6 module">
					<img src="/RailSAHAY/resources/images/sitemap/partnership.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("PARTNER WITH US")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('OWN_TRML');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Join our Network")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('OWN_WGON');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Invest in Rolling Stock")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('SMART');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("First & Last Mile Service Providers")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('BUSINESS_PARTNER');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Business Partnerships")%></a></li>
	 				</ul>
	 			</div>
	 			<div class="col-sm-6 col-lg-3 col-md-6 module">
					<img src="/RailSAHAY/resources/images/sitemap/contact.jpg" class="img img-fluid img-responsive mb-1 img-sitemap" />
	 				<p class="sitemap-menu-head"><%=E.EH("CONTACT US")%></p>
	 				<ul class="list-group list-group-flush">
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="gotoRailMadad();" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Raise a Concern")%></a></li>
	 				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('NODL_OFCR');" class="item-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Nodal Officers Contacts")%></a></li>
	 				</ul>
	 			</div>
	 		</div></div>

</div>
<input type="hidden" name="toPage1" id="toPage1" value="sitemap" />
<%@ include file="/pages/GG_Footer.jspf" %>
