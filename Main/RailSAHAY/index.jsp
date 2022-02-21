<% 
session.setAttribute("pageVisit", "index");
     
   %>
<%@ include file="/pages/GG_Header.jspf" %>	
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SAHAY HOME PAGE","/RailSAHAY/index.jsp",UserDevice,Browser);

String strMesg ="";
try
{
	
	 strMesg = Logg.getHomePageMsgs(si_UserId,si_ClntId,si_LangFlag1,"L",strUserCheck);
      }
catch(Exception e)
{
	System.out.println("In Exception of Message:"+e.getMessage());
	strMesg=null;
	
}
%>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/scrollElement.css">
<script src="/RailSAHAY/resources/js/scrollElement.js"></script>

<script type="text/javascript" src="/RailSAHAY/resources/js/chatbot.js"></script>

<script>
$(document).ready(function()
{
	showPosition();
	fetchSAHAYStats();
        showUserPosition();

});
</script>
<style>
p.statsfig {
    font-size: 3rem;
    line-height: 1;
}
.img-shadow
{
 -webkit-box-shadow: 1px 1px 1px 1px #ddd;  /* Safari 3-4, iOS 4.0.2 - 4.2, Android 2.3+ */
  -moz-box-shadow:    1px 1px 1px 1px #ddd;  /* Firefox 3.5 - 3.6 */
  box-shadow:         1px 1px 1px 1px #ddd;  /* Opera 10.5, IE 9, Firefox 4+, Chrome 6+, iOS 5 */
  border-radius:3px;
}
.env-prop
{
	color:#000;
	font-size:1rem;
	font-weight:600;
	text-align:left;
}	
h3, .h3, h2,.h2
{
	text-transform:none !important;
}
.btn-register
{
background:transparent;
border:2px solid #fff;
font-weight:bold;
color:#fff;
position: fixed;
bottom: 25px;
left: 180px;
height:38px;
}
.btn-register:hover
{
	background:#b32400 !important;
	color:#fff !important;
	border:1px solid #fff !important;
}
.animate-zoom {animation:animatezoom 5s}@keyframes animatezoom {from{transform:scale(0)} to{transform:scale(1)}}
</style>
      <div class="ftco-blocks-cover-1">
        <div class="ftco-cover-1 overlay" style="background-image: url('/RailSAHAY/resources/images/bgcont4.jpg')">  <%--mainbg91.jpg--%>
          <div class="container">
            <div class="row align-items-top" style="padding-top:100px;">
              <div class="col-lg-12" align="center">
                <h2><%=E.EH("Welcome to Indian Railways Freight Services")%></h2>
                <p class="mb-3"><%=E.EH("Transforming Supply Chains with Technology and Innovations")%>..</p>
		<h3 class="animate-zoom"  style="margin-top:120px;font-style:italic;color:#ffffe6;font-weight:600;font-family:initial;text-shadow:1px 1px #000;"><%=E.EH("Dedicated 24x7 to serve the logistics needs of the country")%>...</h3>

	      <%--
	      <div class="row">
	      <div class="col-lg-6 offset-lg-6 col-sm-12">	
                <form action="/RailSAHAY/pages/FNREnquiryOT.jsp" method="POST" id="FnrEnquiry">
                <input type="hidden" name="toPage1" id="toPage1" value="Home" />
		                  <div class="form-group d-flex">
		                    <input type="number" class="form-control" id="txtFnrNumb" onKeyPress="if(this.value.length==11) return false;" name="txtFnrNumb" placeholder='<%=E.EH("Enter your 11-digit FNR Number")%>' />
		                    <input type="button" class="btn btn-primary text-white px-4" value='<%=E.EH("Track Now")%>' id="subFNR" onclick="trackFNR()"/>
		                  </div>
                </form>
              </div>
	      </div>
--%>
	      <div class="row">
	      <div class="col-lg-12 col-sm-12" align="center">	
		<input type="button" class="btn btn-register btn-sm" style="width:auto;padding:5px;background:transparent;border:1px solid #fff;font-weight:600;" value='<%=E.EH("New User Registration")%>' id="newReg" onclick="regUser()"/>
	      </div>
	      </div>
	      <div class="row">
	      <div class="col-lg-12 col-sm-12" align="center">			
		<div class="oxygen" onclick="showPage('TLMO_RAKE');"><table><tr><td><img src="/RailSAHAY/resources/images/emergency_icon.png" style="height:25px;width:25px;" class="img-fluid img-responsive"></td><td style="padding:10px;color:#fff;font-weight:600;">Movement of Liquid Medical Oxygen</td></tr></table></div>
	      </div>
	      </div>


</div>
            </div>
          </div>
        </div>
<br/>

<hr id="leaphead"/>
<div class="down-arrow-button bg-light btn-cntfinder d-none d-sm-none d-md-inline-block d-lg-inline-block">
<a href="#leaphead"><i class="fas fa-chevron-down fa-2x"></i></a>
</div>

<h2 class="heading-title"><%=E.EH("Lets get you started")%>..</h2>
        <!-- END .ftco-cover-1 -->
        <div class="ftco-service-image-1 pb-2 module">
          <div class="container">
            <div class="owl-carousel owl-all">
	      <%--
              <div class="service text-center" onclick="showPage('TRANSPORT_WITH_US');">
                <a href="#"><img src="/RailSAHAY/resources/images/FrgtMvmt.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Transport With Us")%></a></h3>
                  <p><%=E.EH("Transport with Indian Railways for Numerous Benefits")%></p>
                </div>
              </div>
		--%>
              <div class="service text-center" onclick="showPage('TRML_DASHBOARD');">
                <a href="#"><img src="/RailSAHAY/resources/images/TrmlSearch.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("CHOOSE TERMINAL")%></a></h3>
                  <p><%=E.EH("Search the most suitable terminal for your freight transportation")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('FIND_WGON');">
                <a href="#"><img src="/RailSAHAY/resources/images/FrgtMvmt.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("SELECT WAGON")%></a></h3>
                  <p><%=E.EH("Search the right wagon for your freight movement")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('FRGT_CALC');">
                <a href="#"><img src="/RailSAHAY/resources/images/FrgtCalc.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("GET FREIGHT & EXPECTED TRANSIT TIME")%></a></h3>
                  <p><%=E.EH("Get to know about the expected freight and transit time for your planned movement")%></p>
                </div>
              </div>
	     <%--
              <div class="service text-center" onclick="showPage('TRACK_TRACE');">
                <a href="#"><img src="/RailSAHAY/resources/images/fnrmap2.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Track & Trace")%></a></h3>
                  <p><%=E.EH("Live Monitoring & tracking of your cargo through GIS")%></p>
                </div>
              </div>
	     --%>
            </div>
          </div>
        </div>
      </div>

 <div class="site-section bg-light module" id="about-section"><br/>
<h2 class="heading-title"><%=E.EH("About Us")%></h2>
        <div class="container">
          <div class="row mb-5 justify-content-center">
            <div class="col-md-10 text-center">
              <div class="block-heading-1" data-aos1="fade-up" data-aos-delay="">
                <p class="justify">
		<%=E.EH("Indian Railways is the backbone of the country's logistics sector. We carry more than 1.2 Billon tonnes of freight traffic every year over a network of 68000 kms. With our network touching almost every nook and corner of the country, we play a crucial role in facilitating a balanced and inclusive socio economic development of the country. We carry almost all commodities including bulk commodities like Coal, Iron ore, Iron & Steel, Food grains, Cement, Petroleum products, Fertilizer and other commodities carried in containers. We are proud to serve more than 9,000 customers. We are the most environment friendly mode of land transportation. <strong>Our commitment is competitive rates and timely delivery of cargo</strong>")%>. <a class="card-link1" href="/RailSAHAY/pages/AboutUs.jsp" target="_self"><%=E.EH("View Gallery")%></a>
		</p>
              </div>
            </div>
          </div>
	<%--
	<div class="row">
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="300000">0</span>+</span>
                    <span class="caption"><%=E.EH("Wagons")%></span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="100">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="12500">0</span>+</span>
                    <span class="caption"><%=E.EH("Locomotives")%></span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="200">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="4000">0</span>+</span>
                    <span class="caption"><%=E.EH("Freight Terminals")%></span>
                  </div>
                </div>
              </div>
	--%>
	  <div class="row statsrow">
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statrakeldng"></p>
                    <p class="statslbl"><%=E.EH("Daily Rake Loadings")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statindt"></p>
                    <p class="statslbl"><%=E.EH("Indents a Day")%></p>
                </div>
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statwgoncont"></p>
                    <p class="statslbl"><%=E.EH("Wagons")%></p>
                </div>
		<%--
                <div class="col-6 col-md-4 mb-4 col-lg-2" align="center">
                    <p class="statsfig" id="statwgontype"></p>
                    <p class="statslbl"><%=E.EH("Wagon Types")%></p>
                </div>--%>
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
          </div>

        </div>

      </div>

     
      <div class="site-section module" id="services-section">
	<br/>
      <h2 class="heading-title"><%=E.EH("A glimpse of what we offer")%></h2>
	<div class="ftco-service-image-1 pb-0">
          <div class="container">
            <div class="owl-carousel owl-all">
              <div class="service text-center" onclick="showPage('POLICY');">
                <a href="#"><img src="/RailSAHAY/resources/images/innovate2.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("INCENTIVE SCHEMES")%></a></h3>
                  <p class="desc-text"><%=E.EH("Customer-centric Incentive Schemes to facilitate your logistics requirements")%></p>
                </div>
              </div>

              <div class="service text-center" onclick="showPage('TIME_TABLED');">
                <a href="#"><img src="/RailSAHAY/resources/images/timetable1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("TIMETABLED MOVEMENT")%></a></h3>
                  <p class="desc-text"><%=E.EH("Scheduled movement of your cargo over Indian Railways network")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('TRACK_TRACE');">
                <a href="#"><img src="/RailSAHAY/resources/images/fnrmap2.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("TRACK & TRACE")%></a></h3>
                  <p class="desc-text"><%=E.EH("Live track and monitor the movement of your cargo over GIS")%></p>
                </div>
              </div>


              <div class="service text-center" onclick="gotoRailMadad();">
                <a href="#"><img src="/RailSAHAY/resources/images/contactus1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("PROFESSIONAL SUPPORT")%></a></h3>
                  <p class="desc-text"><%=E.EH("Connect to Indian Railway Officials for all your concerns")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('OWN_TRML');">
                <a href="#"><img src="/RailSAHAY/resources/images/owntrml1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("OWN A TERMINAL")%></a></h3>
                  <p class="desc-text"><%=E.EH("Invest with Indian Railways for a Private Freight Terminal (PFT)")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('OWN_WGON');">
                <a href="#"><img src="/RailSAHAY/resources/images/ownwgon1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("OWN A WAGON")%></a></h3>
                  <p class="desc-text"><%=E.EH("Invest in wagons for guaranteed availability and other benefits")%></p>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>

 <div class="site-section module" id="env-section">
<h2 class="heading-title mb-2"><%=E.EH("Green & Energy-efficient Transportation")%></h2>
        <div class="container">
          <div class="row mb-5 justify-content-center">
	   <div class="col-lg-12 text-center"><p class="justify"><%=E.EH("Trains are not just the safest way to transport freight - they are also green and amongst the most energy efficient mode of transportation. Why? Because they generate up to 80% less CO2 and consume 75-90% less energy for freight traffic than road transport")%>.</p><hr class="mb-3"/></div>

            <div class="col-lg-6 text-center">
              <div class="block-heading-1" data-aos1="fade-up" data-aos-delay="">
                <img src="/RailSAHAY/resources/images/greenrail.jpg" class="img img-fluid img-responsive img-shadow" />
              </div>
            </div>
            <div class="col-lg-6 text-center">
              <div class="block-heading-1" data-aos1="fade-up" data-aos-delay="">
                <p class="justify">
		<%=E.EH("Railways carries significant amount of nation's freight, and what we transport touches individuals in all walks of life. We do this while")%></p>
		<ul><li class="env-prop"><%=E.EH("Reducing Carbon Emissions")%></li><li class="env-prop"><%=E.EH("Saving on Fuel Costs")%></li><li class="env-prop"><%=E.EH("Relieving Highway Congestion")%></li></ul><p class="justify"><%=E.EH("Transporting goods by train also provides benefits to the environment beyond fuel efficiency and sustainability, it reduces supply chain costs, allowing Indian businesses to be more competitive in the global market")%>. <a class="card-link1" href="javascript:void(0)" onclick="openExtnLink('IR Greenri','http://www.irgreenri.gov.in/');"><%=E.EH("View More")%></a></p>
		</p>
              </div>
            </div>

          </div>

        </div>

      </div>
	   <%--
            <div class="block__35630" onclick="showPage('POLICY');">
              <div class="icon mb-3 fa-icon-theme">
                <i class="far fa-lightbulb fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Innovative Schemes and Policies")%></h3>
              <p><%=E.EH("Various Freight Transporation Schemes to make your Freight Movement more Economical")%></p>
            </div>
            <div class="block__35630" onclick="showPage('TIME_TABLED');">
              <div class="icon mb-3 fa-icon-theme">
                <i class="fas fa-clock fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Timetabled Movements")%></h3>
              <p><%=E.EH("Scheduled movement of your cargo over Indian Railways network for specified routes")%> </p>
            </div>
	     --%>
	    <%--
            <div class="block__35630" onclick="showPage('SMART');">
              <div class="icon mb-3 fa-icon-theme">
                <i class="fas fa-shipping-fast fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Logistics Partners")%></h3>
              <p><%=E.EH("Detail of Aggregators, Truckers, Warehouse and Labour Providers for your End-mile Connectivity")%> </p>
            </div>
	     --%>
	   
	<%--

            <div class="block__35630" onclick="showPage('TRML_DASHBOARD');">
              <div class="icon mb-3 fa-icon-theme">
		<i class="fas fa-tachometer-alt fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Find A Terminal")%></h3>
              <p><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
            </div>

            <div class="block__35630" onclick="gotoRailMadad();">
              <div class="icon mb-3 fa-icon-theme">
                <i class="fas far fa-address-card fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Contact Us")%></h3>
              <p><%=E.EH("Connect directly to Indian Railway Officials for all your Queries/Suggestions/Grievances")%>  </p>
            </div>

            <div class="block__35630" onclick="showPage('OWN_TRML');">
              <div class="icon mb-3 fa-icon-theme">
                <i class="fas fas fa-laptop fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Own Your Terminal")%></h3>
              <p><%=E.EH("Invest in Railways for a Private Freight Terminal (PFT)")%></p>
            </div>

            <div class="block__35630" onclick="showPage('OWN_WGON');">
              <div class="icon mb-3 fa-icon-theme">
			  <i class="far fa-copy	fa-2x"></i>
              </div>
              <h3 class="mb-3"><%=E.EH("Own Your Wagon")%></h3>
              <p><%=E.EH("Invest in General Purpose Wagons and get rid of wagon shortage for your booking")%>. </p>
            </div>--%>

          </div>
        </div>
      </div>

      <div class="site-section module" id="faq-section">
        <div class="container">
                    <h2 class="heading-title"><%=E.EH("Frequently Asked Questions")%></h2><br/>
          <div class="row">
            <div class="col-lg-6">
		<div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What are different stages in cargo booking")%>?</h3>
                <p class="justify"><%=E.EH("You can register demand for wagons online from the place of your convenience using our")%> <a href='javascript:void(0)' class='card-link1' onclick='gotoeDmnd();'><%=E.EH("e-Demand Service")%></a>. <%=E.EH("First you will have to get yourself")%> <a href='javascript:void(0)' class='card-link1' onclick='regUser();'><%=E.EH("registered with us")%></a>. <%=E.EH("Alternatively, you may submit a forwarding note, containing details of Commodity, Originating and Destination points (Railway Locations) etc. at the Loading Location over Indian Railways. Know more about")%> <a href='javascript:void(0)' class='card-link1' onclick=showPage('BKNG_PROCESS')><%=E.EH("booking process")%></a>.</p>
              </div>
		<div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("How to select suitable originating and Terminating terminal for your cargo")%>?</h3>
                <p class="justify"><%=E.EH("You may find most suitable Terminal to transport your cargo from a wide network of 4000+ Terminals of Indian Railways based on your location, district, state, PINCODE, Railway Division or Railway Zone using our")%> <a href='javascript:void(0)' class='card-link1' onclick=showPage('FIND_TRML')><%=E.EH("Search Terminal Dashboard")%></a>.</p>
              </div>
		<div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("How to find a suitable wagon for my cargo")%>?</h3>
                <p class="justify"><%=E.EH("You may find a most suitable wagon for your commodity using our")%> <a href='javascript:void(0)' class='card-link1' onclick=showPage('FIND_WGON')><%=E.EH("Wagon Selector Tool")%></a>.</p>
              </div>
              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is Wagon Registration Fee")%>?</h3>
                <p class="justify"><%=E.EH("Wagon Registration Fee (WRF) is a token amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake. This amount may be adjusted in Freight")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is Siding Charge")%>?</h3>
                <p class="justify"><%=E.EH("Siding Charges are charges levied for hauling a rake (Freight Train) between Railway serving station and its siding for loading / unloading of Goods")%>. </p>
              </div>
            </div>
            <div class="col-lg-6">

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is Electronic Railway Receipt or eT-RR")%>?</h3>
                <p class="justify"><%=E.EH("Electronic Railway Receipt or eT-RR has been launched to provide paperless transaction system where Railway Receipt is generated and transmitted electronically to customer through FOIS, and even delivery of goods is given through e-surrender of eT-RR")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is the rate of Demurrage Charge")%>?</h3>
                <p class="justify"><%=E.EH("At present, rate of Demurrage Charge is Rs 150/- per wagon per hour or part of an hour")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("Whom to approach to offer traffic to rail")%>?</h3>
                <p class="justify"><%=E.EH("Step by Step guidance can be provided by Chief Goods Supervisor/Goods clerk at station.Please visit nearest Goods Shed")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("How may I know about freight policy")%>?</h3>
                <p class="justify"><%=E.EH("The information regarding freight policy are uploaded on the webpage of Traffic Commercial Dte under")%> <a href="http://www.indianrail.gov.in" target="_blank">www.indianrailways.gov.in</a></p>
              </div>


              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is through distance based charging")%>?</h3>
                <p class="justify"><%=E.EH("Through distance based charging is the system of charging freight upto the buffer end of the siding, in place of levying Siding Charge")%>.</p>
              </div>
              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What are stacking guidelines")%>?</h3>
                <p class="justify"><%=E.EH("Customers who have placed indent for wagon may apply for free advance stacking permission. Permission for advance stacking is granted keeping in view the traffic pattern, number of rakes handled, availability of space etc. at the station/goods shed concerned")%>.</p>
              </div>
	     <a href="/RailSAHAY/pages/FAQs.jsp" target="_self" class="card-link1"><%=E.EH("View More")%>..</a>
            </div>
          </div>
        </div>
      </div>

      <input type="hidden" name="toPage1" id="toPage1" value="Home" />
	
	
<%@ include file="/pages/GG_Footer.jspf" %>	
     
     