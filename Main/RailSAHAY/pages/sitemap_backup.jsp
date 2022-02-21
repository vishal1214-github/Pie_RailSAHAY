<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SITEMAP","/RailSAHAY/pages/sitemap.jsp",UserDevice,Browser);
%>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<script src="/RailSAHAY/resources/js/sahayutil.js"></script>
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
          
	  <div class="container-fullwidth">
	            <div class="row header-image">
	  <div class="col-sm-12" align="center">
	  	<div class="image-wrapper">
	  		<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
	  		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statcmdtcont"></span>&nbsp;<%=E.EH("Access the Freight Business Information and Services with One Click")%></h2>
	  		<img src="/RailSAHAY/resources/images/SiteMapHead.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	  	</div>					
	  </div>	
	  </div></div>
	<div class="container-fullwidth">
		<div class="row">
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("HOME")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRML_DASHBOARD");' class="item-link"><%=E.EH("Choose Terminal")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FIND_WGON");' class="item-link"><%=E.EH("Select Wagon")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FRGT_CALC");' class="item-link"><%=E.EH("Get Freight & Expected Transit Time")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("POLICY");' class="item-link"><%=E.EH("Incentive Schemes")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TIME_TABLED");' class="item-link"><%=E.EH("Timetabled Movement")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRACK_TRACE");' class="item-link"><%=E.EH("Track & Trace")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoRailMadad();' class="item-link"><%=E.EH("Professional Support")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("OWN_TRML");' class="item-link"><%=E.EH("Own A Terminal")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("OWN_WGON");' class="item-link"><%=E.EH("Own A Wagon")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("NEW TO RAIL")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("BOOKING_PROCESS");' class="item-link"><%=E.EH("Booking Process")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("TRML_DASHBOARD");' class="item-link"><%=E.EH("Find a Suitable Terminal")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FIND_WGON");' class="item-link"><%=E.EH("Select the Right Wagon")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FRGT_CALC");' class="item-link"><%=E.EH("Get Expected Freight Charges & Transit Time")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("POLICY");' class="item-link"><%=E.EH("Explore Rebate Schemes & Long-term Contracts")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("STTN_HELP");' class="item-link"><%=E.EH("Connect to First & Last Mile Service Providers")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='regUser();' class="item-link"><%=E.EH("User Registration")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoeDmnd();' class="item-link"><%=E.EH("Register your Demand Online")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("EXISTING CUSTOMERS")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoECust();' class="item-link"><%=E.EH("Corporate Login")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('IND_LOGIN');' class="item-link"><%=E.EH("Individual Login")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('TRACK_TRACE');' class="item-link"><%=E.EH("Track & Trace")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage("FRGT_CALC");' class="item-link"><%=E.EH("Freight Charges & Transit Time Estimator")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('RAISE_REQ');' class="item-link"><%=E.EH("Raise A Request")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('TRACK_REQ');' class="item-link"><%=E.EH("Track Your Request")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('ODR_RAS');' class="item-link"><%=E.EH("Indents Outstanding & Rake Allotments")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('TAX_INVC');' class="item-link"><%=E.EH("Download GST Tax Invoice")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('POLICY');' class="item-link"><%=E.EH("Rebate Schemes & Long Term Contracts")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='showPage('STTN_HELP');' class="item-link"><%=E.EH("First & Last Mile Service Providers")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick='gotoeDmnd();' class="item-link"><%=E.EH("e-Registration of Indents")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("COMMODITIES")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('COAL','COAL & COKE');" class="item-link"><%=E.EH("Coal & Coke")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('ORES','MINERALS & ORES');" class="item-link"><%=E.EH("Minerals & Ores")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('FG','FOOD GRAINS, FLOURS & PULSES');" class="item-link"><%=E.EH("Food Grains, Flours & Pulses")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CEMT','CEMENT & CLINKER');" class="item-link"><%=E.EH("Cement & Clinker")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CHEM','CHEMICAL MANURES');" class="item-link"><%=E.EH("Chemical Manures")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('IS','IRON & STEEL');" class="item-link"><%=E.EH("Iron & Steel")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('POL','PETROLEUM PRODUCTS & GASES');" class="item-link"><%=E.EH("Petroleum Products & Gases")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('CONT','CONTAINER SERVICES');" class="item-link"><%=E.EH("Container Services")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('AUTO','AUTOMOBILES');" class="item-link"><%=E.EH("Automobiles")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('SSSO','SUGAR, SALT, SPICES, OILS AND MORE..');" class="item-link"><%=E.EH("Sugar, Salt, Spices, Oils")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('PIGI','PIG, SPONGE, WROUGHT IRON AND MORE..');" class="item-link"><%=E.EH("Pig, Sponge, Wrought Iron")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('STON','STONE/BAMBOO CHIPS, GRANITE AND MORE..');" class="item-link"><%=E.EH("Stone/Bamboo Chips, Granite")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="setMainCmdt('OTHR','OTHERS');" class="item-link"><%=E.EH("More")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("TOOLS & SERVICES")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRML_DASHBOARD');" class="item-link"><%=E.EH("Terminal Selector")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('FIND_WGON');" class="item-link"><%=E.EH("Wagon Selector")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('FRGT_CALC');" class="item-link"><%=E.EH("Freight & Transit Time Estimator")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TRACK_TRACE');" class="item-link"><%=E.EH("Track & Trace")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('RATE_SLAB');" class="item-link"><%=E.EH("Rate Slabs")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('ML_POINT');" class="item-link"><%=E.EH("Two-Point/Multi-Point (Permitted Combinations)")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('TIME_TABLED');" class="item-link"><%=E.EH("Timetabled Services")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('MINI_RAKE');" class="item-link"><%=E.EH("Mini Rake Services")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("PARTNER WITH US")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('OWN_TRML');" class="item-link"><%=E.EH("Join our Network")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('OWN_WGON');" class="item-link"><%=E.EH("Invest in Rolling Stock")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('SMART');" class="item-link"><%=E.EH("First & Last Mile Service Providers")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('BUSINESS_PARTNER');" class="item-link"><%=E.EH("Business Partnerships")%></a></li>
				</ul>
			</div>
			<div class="col-sm-6 col-lg-3 col-md-6">
				<p class="sitemap-menu-head"><%=E.EH("CONTACT US")%></p>
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="gotoRailMadad();" class="item-link"><%=E.EH("Raise a Concern")%></a></li>
				  <li class="list-group-item"><a href="javascript:void(0)" onclick="showPage('NODL_OFCR');" class="item-link"><%=E.EH("Nodal Officers Contacts")%></a></li>
				</ul>
			</div>
		</div></div>
      <%@ include file="/pages/GG_Footer.jspf" %>
