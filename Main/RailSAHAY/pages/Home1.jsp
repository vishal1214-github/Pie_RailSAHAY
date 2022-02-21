<% 

session.setAttribute("pageVisit", "index");
     
   %>
<%@ include file="/pages/GG_Header.jspf" %>	
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SAHAY HOME PAGE","/RailSAHAY/index.jsp",UserDevice,Browser);
%>

    <script>
    function trackFNR() {
        var fnrnmub =  $("#txtFnrNumb"). val();
        self.location="/RailSAHAY/pages/FNREnquiryOT.jsp?FNRNumb="+fnrnmub;
    }
   
    </script>

      <div class="ftco-blocks-cover-1">
        <div class="ftco-cover-1 overlay" style="background-image: url('/RailSAHAY/resources/images/contbg.jpg')">
          <div class="container">
            <div class="row align-items-center">
              <div class="col-lg-12" align="right">
                <h2><%=E.EH("WELCOME TO INDIAN RAILWAYS FREIGHT SERVICES")%></h2>
                <p class="mb-5"><%=E.EH("Simplifying your Freight and Logistics needs with Technology and Innovations")%>..</p>
	      <div class="row">
	      <div class="col-lg-6 offset-lg-6 col-sm-12">	
                <form action="#">
                  <div class="form-group d-flex">
                    <input type="text" class="form-control" id="txtFnrNumb" name="txtFnrNumb" placeholder="Enter your 11 Digit FNR No."/>
                    <input type="button" class="btn btn-primary text-white px-4" value="Track Now" id="subFNR" onclick="trackFNR()"/>
                  </div>
                </form>
              </div>
	      </div>
</div>
            </div>
          </div>
        </div>
<br/>

<hr/>
<div class="down-arrow-button bg-light btn-cntfinder">
<i class="fas fa-chevron-down fa-2x"></i></i>
</div>

<h2 class="heading-title"><%=E.EH("Take a leap forward")%></h2>
        <!-- END .ftco-cover-1 -->
        <div class="ftco-service-image-1 pb-5">
          <div class="container">
            <div class="owl-carousel owl-all">
              <div class="service text-center" onclick="showPage('TRANSPORT_WITH_US');">
                <a href="#"><img src="/RailSAHAY/resources/images/FrgtMvmt.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Transport With Us")%></a></h3>
                  <p><%=E.EH("Transport with Indian Railways for Endless Benefits")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('TRML_DASHBOARD');">
                <a href="#"><img src="/RailSAHAY/resources/images/TrmlSearch.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Find a Terminal")%></a></h3>
                  <p><%=E.EH("Search a most suitable terminal for your freight transportation")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('FRGT_CALC');">
                <a href="#"><img src="/RailSAHAY/resources/images/FrgtCalc.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Expected Freight")%></a></h3>
                  <p><%=E.EH("Get to know about the expected freight for your planned movement")%></p>
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
<%--
        <div class="ftco-service-image-1 pb-5">
          <div class="container">
            <div class="owl-carousel owl-all">
              <div class="service text-center" onclick="showPage('TRANSPORT_WITH_US');">
		<div class="card">
	          <img src="/RailSAHAY/resources/images/FrgtMvmt.jpg" alt="Avatar" style="width:100%">
		  </div>
	      </div>
              <div class="service text-center" onclick="showPage('FRGT_CALC');">
		<div class="card">
	          <img src="/RailSAHAY/resources/images/FrgtCalc.jpg" alt="Avatar" style="width:100%">
		  </div>
	      </div>

              <div class="service text-center" onclick="showPage('TRACK_TRACE');">
		<div class="card">
	          <img src="/RailSAHAY/resources/images/fnrmap2.jpg" alt="Avatar" style="width:100%">
		  </div>
	      </div>

            </div>
	  </div>
        </div>
--%>


 <div class="site-section bg-light" id="about-section"><br/>
<h2 class="heading-title"><%=E.EH("About Us")%></h2>
        <div class="container">
          <div class="row mb-5 justify-content-center">
            <div class="col-md-10 text-center">
              <div class="block-heading-1" data-aos1="fade-up" data-aos-delay="">
                <p class="justify">
		<%=E.EH("We, the Indian Railways, are the backbone of logistics sector of the country. We carry more than 1.2 Billon tonnes of freight traffic every year over 68000 km of our network. We carry almost all commodities including bulk commodities like Coal, Iron ore, Iron & Steel, Food grains, Cement, Petroleum products, Fertilizer, containers and others")%>. <a class="card-link1" href="/RailSAHAY/pages/AboutUs.jsp" target="_self"><i class="fas fa-angle-right"></i> <%=E.EH("Read More")%></a>
		</p>
              </div>
            </div>
          </div>
	<div class="row">
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="284000">0</span>+</span>
                    <span class="caption">Wagons</span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="100">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="11000">0</span>+</span>
                    <span class="caption">Locomotives</span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-4" data-aos1="fade-up" data-aos-delay="200">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="3000">0</span>+</span>
                    <span class="caption">Freight Terminals</span>
                  </div>
                </div>
              </div>
        </div>

      </div>

      <div class="site-section bg-light" id="about-section">
        <div class="container">
          <div class="row">
            <div class="col-12">
              
            </div>
          </div>
        </div>
      </div>


      <div class="site-section" id="services-section">

      <h2 class="heading-title"><%=E.EH("What We Offer")%></h2><br/>
	<div class="ftco-service-image-1 pb-5">
          <div class="container">
            <div class="owl-carousel owl-all">
              <div class="service text-center" onclick="showPage('POLICY');">
                <a href="#"><img src="/RailSAHAY/resources/images/innovate2.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Innovative Schemes")%></a></h3>
                  <p><%=E.EH("Number of Innovative Schemes and Policies to make your Freight Transportation more Economical")%></p>
                </div>
              </div>

              <div class="service text-center" onclick="showPage('TIME_TABLED');">
                <a href="#"><img src="/RailSAHAY/resources/images/timetable1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("TIMETABLED MOVEMENT")%></a></h3>
                  <p><%=E.EH("Scheduled Movement of your Cargo over Indian Railways Network")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('TRACK_TRACE');">
                <a href="#"><img src="/RailSAHAY/resources/images/fnrmap2.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("TRACK & TRACE")%></a></h3>
                  <p><%=E.EH("Live Track and Monitor the Movement of your cargo over GIS")%></p>
                </div>
              </div>


              <div class="service text-center" onclick="gotoRailMadad();">
                <a href="#"><img src="/RailSAHAY/resources/images/contactus1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Contact Us")%></a></h3>
                  <p><%=E.EH("Connect to Indian Railway Officials for all your concerns")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('OWN_TRML');">
                <a href="#"><img src="/RailSAHAY/resources/images/owntrml1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Own A Terminal")%></a></h3>
                  <p><%=E.EH("Invest with Indian Railways for a Private Freight Terminal (PFT)")%></p>
                </div>
              </div>
              <div class="service text-center" onclick="showPage('OWN_WGON');">
                <a href="#"><img src="/RailSAHAY/resources/images/ownwgon1.jpg" alt="Image" class="img-fluid"></a>
                <div class="px-md-3">
                  <h3><a href="javascript:void(0)"><%=E.EH("Own A Wagon")%></a></h3>
                  <p><%=E.EH("Invest in Wagons for Guaranteed Availability and Other Benefits")%></p>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>

	<%--
        <div class="container">
          <div class="owl-carousel owl-all">
	   <img src="/RailSAHAY/resources/images/SCHEMES.jpg" class="img-fluid img-responsive img-main"  onclick="showPage('POLICY');" />
	   <img src="/RailSAHAY/resources/images/TTTran.jpg" class="img-fluid img-responsive img-main"  onclick="showPage('TIME_TABLED');" />
	   <img src="/RailSAHAY/resources/images/TrmlDB.jpg" class="img-fluid img-responsive img-main"  onclick="showPage('TRML_DASHBOARD');" />
	   <img src="/RailSAHAY/resources/images/ContactUs.jpg" class="img-fluid img-responsive img-main"  onclick="gotoRailMadad();" />
	   <img src="/RailSAHAY/resources/images/OwnTrml.jpg" class="img-fluid img-responsive img-main"  onclick="showPage('OWN_TRML');" />
	   <img src="/RailSAHAY/resources/images/OwnWgon.jpg" class="img-fluid img-responsive img-main"  onclick="showPage('OWN_WGON');" />
        --%>
	<%--



	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A2.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Innovative Schemes and Policies")%></h4>
		<p class="card-text"><%=E.EH("Various Freight Transporation Schemes to make your Freight Movement more Economical")%></p>
	  	</div>
	   </div>
	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A1.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Timetabled Movements")%></h4>
		<p class="card-text"><%=E.EH("Scheduled movement of your cargo over Indian Railways network for specified routes")%></p>
	  	</div>
	   </div>
	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A32.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Find A Terminal")%></h4>
		<p class="card-text"><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
	  	</div>
	   </div>
	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A4.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Find A Terminal")%></h4>
		<p class="card-text"><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
	  	</div>
	   </div>
	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A5.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Find A Terminal")%></h4>
		<p class="card-text"><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
	  	</div>
	   </div>
	   <div class="card" style="height:600px;">
	   	<img src="/RailSAHAY/resources/images/A61.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Find A Terminal")%></h4>
		<p class="card-text"><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
	  	</div>
	   </div>
--%>



	    <%--

	    <img src="/RailSAHAY/resources/images/A2.jpg" class="img-fluid img-responsive" />
	    <img src="/RailSAHAY/resources/images/A1.jpg" class="img-fluid img-responsive" />
	    <img src="/RailSAHAY/resources/images/A32.jpg" class="img-fluid img-responsive" />
	    <div class="block__35630" onclick="showPage('POLICY');">
              <h3 class="mb-3"><%=E.EH("Innovative Schemes and Policies")%></h3>
              <p><%=E.EH("Various Freight Transporation Schemes to make your Freight Movement more Economical")%></p>
            </div>
            <div class="block__35630" onclick="showPage('TIME_TABLED');">
              <h3 class="mb-3"><%=E.EH("Timetabled Movements")%></h3>
              <p><%=E.EH("Scheduled movement of your cargo over Indian Railways network for specified routes")%> </p>
            </div>
            <div class="block__35630" onclick="showPage('TRML_DASHBOARD');">
              <h3 class="mb-3"><%=E.EH("Find A Terminal")%></h3>
              <p><%=E.EH("Find a most suitable terminal for your cargo transportation with our Terminals Dashboard")%> </p>
            </div>
	   <div class="card">
	   	<img src="/RailSAHAY/resources/images/A2.jpg" class="img-fluid img-responsive" />
	  	<div class="card-body">
		<h4 class="card-title"><%=E.EH("Innovative Schemes and Policies")%></h4>
		<p class="card-text"><%=E.EH("Various Freight Transporation Schemes to make your Freight Movement more Economical")%></p>
	  	</div>
	   </div>


	    <img src="/RailSAHAY/resources/images/A4.jpg" class="img-fluid img-responsive" />
	    <img src="/RailSAHAY/resources/images/A5.jpg" class="img-fluid img-responsive" />
	    <img src="/RailSAHAY/resources/images/A61.jpg" class="img-fluid img-responsive" />--%>
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


<%--

      <div class="site-section" id="about-section">

        <div class="container">
          <div class="row mb-5 justify-content-center">
            <div class="col-md-7 text-center">
              <div class="block-heading-1" data-aos1="fade-up" data-aos-delay="">
                <h2>About Us</h2>
                <p>We are the Fourth largest Railways in the World</p>
              </div>
            </div>
          </div>
        </div>

      </div>



      <div class="site-section bg-light" id="about-section">
        <div class="container">
          <div class="row justify-content-center mb-4 block-img-video-1-wrap">
            <div class="col-md-12 mb-5">
                <span class="icon"><span class="icon-play"></span></span>
                <img src="/RailSAHAY/resources/images/bcacbm.jpg" alt="Image" class="img-fluid">
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="row">
                <div class="col-6 col-md-6 mb-4 col-lg-0 col-lg-3" data-aos1="fade-up" data-aos-delay="">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="150">0</span>+</span>
                    <span class="caption">Types of Wagons</span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-0 col-lg-3" data-aos1="fade-up" data-aos-delay="100">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="11000">0</span>+</span>
                    <span class="caption">Locomotives</span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-0 col-lg-3" data-aos1="fade-up" data-aos-delay="200">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="11500">0</span>+</span>
                    <span class="caption">Freight Terminals</span>
                  </div>
                </div>
                <div class="col-6 col-md-6 mb-4 col-lg-0 col-lg-3" data-aos1="fade-up" data-aos-delay="300">
                  <div class="block-counter-1">
                    <span class="number"><span data-number="2500">0</span>+</span>
                    <span class="caption">Indents a Day</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
--%>
<%--
      <div class="site-section" id="faq-section">
        <div class="container">
          <div class="row mb-5">
            <div class="block-heading-1 col-12 text-center">
              <h2><%=E.EH("Frequently Asked Questions")%></h2>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-6">

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is Wagon Registration Fee")%>?</h3>
                <p class="justify"><%=E.EH("Wagon Registration Fee (WRF) is a token amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake. This amount may be adjusted in Freight")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is Siding Charge")%>?</h3>
                <p class="justify"><%=E.EH("Siding Charges are charges levied for hauling a rake (Freight Train) between Railway serving station and its siding for loading / unloading of Goods")%>. </p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What is through distance based charging")%>?</h3>
                <p class="justify"><%=E.EH("Through distance based charging is the system of charging freight upto the buffer end of the siding, in place of levying Siding Charge")%>.</p>
              </div>

              <div class="mb-5" data-aos1="fade-up" data-aos-delay="100">
                <h3 class="text-black h4 mb-4"><i class="far fa-question-circle"></i>&nbsp;<%=E.EH("What are stacking guidelines")%>?</h3>
                <p class="justify"><%=E.EH("Customers who have placed indent for wagon may apply for free advance stacking permission. Permission for advance stacking is granted keeping in view the traffic pattern, number of rakes handled, availability of space etc. at the station/goods shed concerned")%>.</p>
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
	     <a href="/RailSAHAY/pages/FAQs.jsp" target="_self" class="card-link1"><%=E.EH("View More")%>..</a>
            </div>
          </div>
        </div>
      </div>
--%>
<%@ include file="/pages/GG_Footer.jspf" %>	