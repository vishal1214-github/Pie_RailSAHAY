<!doctype html>
<html lang="en">

  <head>
    <title>RAIL SAHAY: Parcel Booking</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,700|Oswald:400,700" rel="stylesheet">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    
    <link rel="stylesheet" href="/RailSAHAY/resources/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/RailSAHAY/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/aos.css">

    <!-- MAIN CSS -->
    <link rel="stylesheet" href="/RailSAHAY/resources/css/style.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/stepform.css">

  </head>

  <body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

    <div id="overlayer"></div>
    <div class="loader">
      <div class="spinner-border text-primary" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>

    <div class="site-wrap" id="home-section">

      <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close mt-3">
            <span class="icon-close2 js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>
      <header class="site-navbar js-sticky-header site-navbar-target" role="banner">

        <div class="container">
          <div class="row align-items-center position-relative">


            <div class="site-logo">
              <a href="/RailSAHAY/index.jsp" class="text-black"><span class="text-primary">RAIL SAHAY</a>
            </div>

            <div class="col-12">
                <nav class="site-navigation text-right ml-auto " role="navigation">
				  <ul class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
				 <li><a href="/RailSAHAY/pages/AboutUs.jsp" class="nav-link">About Us</a></li>
				<li class="has-children">
				  <a href="#about-section" class="nav-link">Booking</a>
				  <ul class="dropdown arrow-top">
					<li><a href="https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/Login_new.jsp" class="nav-link" target="_blank">e-Demand</a></li>
					<li><a href="/RailSAHAY/pages/FrgtCalc.jsp" class="nav-link">Freight Calculator</a></li>
					<li><a href="#" class="nav-link">Freight Rates</a></li>
					<li><a href="/RailSAHAY/pages/ParcelBkng.jsp" class="nav-link">Parcel Booking</a></li>

					<%--
					<li class="has-children">
					  <a href="#">More Links</a>
					  <ul class="dropdown">
						<li><a href="#">Menu One</a></li>
						<li><a href="#">Menu Two</a></li>
						<li><a href="#">Menu Three</a></li>
					  </ul>
					</li>
					--%>
				  </ul>
					</li>
				<li class="has-children">
				  <a href="#about-section" class="nav-link">Facilities</a>
				  <ul class="dropdown arrow-top">
					<li><a href="/RailSAHAY/pages/TerminalAnalysis.jsp" class="nav-link">Station Profile</a></li>
					<li><a href="LoadingLocations.jsp" class="nav-link">Loading Locations</a></li>
					<li><a href="#" class="nav-link">Unloading Locations</a></li>
					<li><a href="#" class="nav-link">CRT Locations</a></li>
					<li><a href="#" class="nav-link">PFT Locations</a></li>
				  </ul>
					</li>
					<li><a href="/RailSAHAY/pages/SMART.jsp"  class="nav-link">Partners</a></li>
					<li><a href="/RailSAHAY/pages/FNREnquiryIN.jsp" class="nav-link">Track n Trace</a></li>
					<li><a href="/RailSAHAY/pages/FAQs.jsp" class="nav-link">FAQs</a></li>
					<li><a href="/RailSAHAY/pages/LodgeConcern.jsp" class="nav-link">Contact Us</a></li>
					<li><a href="/RailSAHAY/pages/eDmndLogin.jsp" class="nav-link">Sign In</a></li>
				  </ul>
              </nav>
            </div>

            <div class="toggle-button d-inline-block d-lg-none"><a href="#" class="site-menu-toggle py-5 js-menu-toggle text-black"><span class="icon-menu h3"></span></a></div>

          </div>
        </div>
      </header>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">FREQUENTLY ASKED QUESTIONS</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	<div class="card">
	  <div class="card-header head-faq">
	    Booking
	  </div>
	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#BkngOne">
			   What is the procedure of booking of a traffic?
			 </a>
		       </div>
		       <div id="BkngOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   Customer is required to furnish a filled forwarding note containing details of commodity, O-D points etc. to the goods clerk/ chief goods supervisor.			   
			   Now customer can register its demand electronically from the place of his convenience on TMS. (FOIS website).
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngTwo">
			   How can we place indent for wagons?
			 </a>
		       </div>
		       <div id="BkngTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   Customers may place indent for wagons by making application and payment of Wagon Registration Fee at goods booking office.  Now customer can place indent electronically also.
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngThree">
			   How the distance between O-D pairs can be seen?
			 </a>
		       </div>
		       <div id="BkngThree" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   The distance / routes can be seen at Rates Branch System on FOIS website.
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngFour">
			   What is Wagon Registration Fee?
			 </a>
		       </div>
		       <div id="BkngFour" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   Wagon Registration Fee (WRF) is a token amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake. This amount is adjusted in Freight. 
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngFive">
			   How the Railway customer can book their rake/wagon?
			 </a>
		       </div>
		       <div id="BkngFive" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   Customer can book their rake through FOIS website electronically, which is simpler, convenient and transparent process.
			 </div>
		       </div>
		     </div>
		</div>
	  </div>	  
	  	<div class="card">
	  	  <div class="card-header head-faq">
	  	    Payments
	  	  </div>
	  	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#PymtOne">
			   What is e-payment system? 
			 </a>
		       </div>
		       <div id="PymtOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   Railway has introduced Electronic payment system. It enables collection of freight charges directly from customer’s bank account. Customers can avail this facility by executing agreement with railway administration and bank. Handling Agent of customers are also permitted for making e-payment on behalf of its customers
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtTwo">
			   What is Wagon Registration Fee?
			 </a>
		       </div>
		       <div id="PymtTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   Wagon Registration Fee (WRF) is a token amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake. This amount is adjusted in Freight
			 </div>
		       </div>
		     </div>
		</div>
		</div>
			  
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    Freight and Other Charges
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 <a class="card-link" data-toggle="collapse" href="#FctChrg1">
					   What are the guidelines for waiver of Demurrage/Wharfage?
					 </a>
				       </div>
				       <div id="FctChrg1" class="collapse show" data-parent="#accordion">
					 <div class="card-body">
					   In case the consignor/consignee feels that Demurrage or Wharfage was imposed due to reasons beyond his control, he can prefer application for waiver to the station master/goods supervisor.
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg2">
					   What is Siding Charge?
					 </a>
				       </div>
				       <div id="FctChrg2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Siding Charge is levied for hauling a rake between serving station and siding. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg3">
					   How Siding Charge is calculated?
					 </a>
				       </div>
				       <div id="FctChrg3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Siding Charge is worked out on the basis of Engine Hour Cost and average trip time for movement of rake between serving station and siding. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg4">
					  What is Engine Hour Cost?
					 </a>
				       </div>
				       <div id="FctChrg4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   All India Engine Hour cost is notified by Railway Board for Diesel and Electric Locomotive.
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg5">
					  What is Shunting Charge?
					 </a>
				       </div>
				       <div id="FctChrg5" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Shunting Charge is levied for the utilization of Railway Loco to perform shunting operation. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg6">
					  What is through distance based charging?
					 </a>
				       </div>
				       <div id="FctChrg6" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Through distance based charging is the system of charging freight upto the buffer end of the siding, in place of levying Siding Charge. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg7">
					  What are the conditions for through distance based charging?
					 </a>
				       </div>
				       <div id="FctChrg7" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   The system of charging freight on through distance basis is extended to all block rakes going into the siding directly or indirectly with the engine pulling or pushing, provided (a) there is no detention to engines except for change of ends and (b) no separate shunting staff is required exclusively for this purpose.  
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg8">
					 Why Railway levies Demurrage Charge?
					 </a>
				       </div>
				       <div id="FctChrg8" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Demurrage Charge is levied for the detention of railway’s rolling stock beyond prescribed free time. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg9">
					 What does free time mean?
					 </a>
				       </div>
				       <div id="FctChrg9" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					  Railway has permitted certain time as free time for loading and unloading of wagons. Free time are prescribed for different types of wagons at  goods sheds, sidings, old steel plants; other steel plants and freight terminals/sidings/steel plants/goods sheds which work on Engine-on-Load basis.
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg10">
					  What is the rate of Demurrage Charge?
					 </a>
				       </div>
				       <div id="FctChrg10" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   At present, rate of Demurrage Charge is Rs 150/- per wagon per hour or part of an hour.
					 </div>
				       </div>
				     </div>
				</div>
			  </div>
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    Policies and Rules
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 <a class="card-link" data-toggle="collapse" href="#Pol1">
					    What are the policies for weighbridge? 
					 </a>
				       </div>
				       <div id="Pol1" class="collapse show" data-parent="#accordion">
					 <div class="card-body">
					   All loading points should be covered by weighbridges. All in-motion weighbridges may be linked to FOIS. Freight customers are permitted for installation of weighbridges on railway land at private siding/goods sheds. Guidelines are laid down for Weightometer/Pre-weighbin System of weighment in private sidings
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol2">
					   What are the guidelines for weighment of rake? 
					 </a>
				       </div>
				       <div id="Pol2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   The traffic loaded from any terminals are required to be weighed at weighbridges. Zonal Railway notifies associate weighbridge for each loading point and alternate associate weighbridge where weighment will be done if the associate weighbridge is defective. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol3">
					   What commodities are exempted from mandatory weighment?
					 </a>
				       </div>
				       <div id="Pol3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Dispensation from mandatory weighment has been permitted in following cases – 
					   •	Commodities loaded in standard bags of uniform size like foodgrains, sugar, fertilizers, cement etc 
					   •	HR Coil, CR Coil and other steel consignment which are pre-weighed on certified mill scale and bear marking/label of Central Excise/Customs
					   •	Low density commodities namely Petroleum Coke, Metallurgical Coke, Chuni, De-Oiled Cake 
					   •	Containerized Import traffic where SMTP details are transmitted though EDI from Customs server to FOIS
					   •	Nepal bound containerized Import traffic on submission of Custom’s document					   

					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol4">
					   What are stacking guidelines?
					 </a>
				       </div>
				       <div id="Pol4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Customers who have placed indent for wagon may apply for free advance stacking permission. Permission for advance stacking is granted keeping in view the traffic pattern, number of rakes handled, availability of space etc. at the station/goods shed concerned. 
					 </div>
				       </div>
				     </div>
		</div>
		</div>
			  
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    Freight Operations Information System (FOIS)
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 <a class="card-link" data-toggle="collapse" href="#FOIS1">
					   What steps have been taken to promote digital working in freight business?
					 </a>
				       </div>
				       <div id="FOIS1" class="collapse show" data-parent="#accordion">
					 <div class="card-body">
					   A number of initiatives have been taken to promote digital working in freight business e.g. Registration of demand for wagons electronically (e-RD), Electronic transmission of Railway Receipt (eT-RR), Terminal Management System, Online Interface to Freight Customers, Electronic payment system, Freight Advance Scheme, Online Goods Balance sheet and system generated Money Receipt. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS2">
					   What is e-RD? 
					 </a>
				       </div>
				       <div id="FOIS2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Railway has implemented electronic registration of demand for wagons (e-RD). It provides electronic demand note facility through FOIS website wherein, customer can register their demand for wagons electronically. They need not to visit goods booking office.
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS3">
					   What is electronic Railway Receipt? 
					 </a>
				       </div>
				       <div id="FOIS3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   eT-RR has been launched to provide paperless transaction system where Railway Receipt is generated and transmitted electronically to customer through FOIS, and even delivery of goods is given through e-surrender of eT-RR.  
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS4">
					   What is Terminal Management System?
					 </a>
				       </div>
				       <div id="FOIS4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   TMS provides system based preparation of Railway Receipt for freight traffic. In recent past, two new features have been added to promote digital working at field level (a) Preparation and submission of online goods balance sheet and (b) System generated Money Receipt which includes preparation of  bill for miscellaneous charges i.e. Demurrage/Wharfage etc. 
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS5">
					   Please tell about Online interface to Freight Customers?
					 </a>
				       </div>
				       <div id="FOIS5" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   Online interface through Freight Operation Information system(FOIS) provide all requisite information e.g. freight class and rate for all commodities; position of indent/demand for each station; outstanding indents, shortest/popular routes, terminal handling facilities and freight calculator are available. Customers can track and trace their rakes while login their accounts in FOIS. 
					 </div>
				       </div>
				     </div>
		</div>
	  </div>
	  <div class="card">
			  <div class="card-header head-faq">
			    How to Reach Us
			  </div>
			  <div class="card-body">
			   <div id="accordion">	   
			     <div class="card">
			       <div class="card-header">
				 <a class="card-link" data-toggle="collapse" href="#Reach1">
				   Who can be approached if I want to offer traffic to rail?
				 </a>
			       </div>
			       <div id="Reach1" class="collapse show" data-parent="#accordion">
				 <div class="card-body">
				   Chief Goods Supervisor/Goods clerk at station may be approached in this regard.  He will guide the customers step by step.
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach2">
				   Who fix the rates for Goods Traffic?
				 </a>
			       </div>
			       <div id="Reach2" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   Central Government /Railway Board is vested with the power to fix rates for transportation of goods and passengers.

				   However, Zonal Railway administration is empowered to quote Station to Station rates and lumpsum rates.

				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach3">
				   How the Railway customer can book their rake/wagon?
				 </a>
			       </div>
			       <div id="Reach3" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   Customer can book their rake through FOIS website electronically, which is simpler, convenient and transparent process.
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach4">
				   How can we get information about freight policy?
				 </a>
			       </div>
			       <div id="Reach4" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   The information regarding freight policy are uploaded on the webpage of Traffic Commercial Dte under <a href="http://www.indianrail.gov.in" target="_blank">www.indianrailways.gov.in</a> 						   

				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach5">
				   If a customer wants to request Railway administration to revise the rates or classification of any commodity, who can be approached?
				 </a>
			       </div>
			       <div id="Reach5" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   Customer may represent to Railway Board (MT) for any such relaxation.  They may also approach Zonal Railway who in turn forwards the same to Railway Board along with analysis and their recommendations.
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach6">
				   For availing any benefit, who is the point of contact at Zonal and Divisional level?
				 </a>
			       </div>
			       <div id="Reach6" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   CCM/FM/FS and Dy.CCM/FS/FM at Zonal level and Sr. DCM at Divisional level are the point of contact for the customers.
				 </div>
			       </div>
			     </div>
	  		</div>
	  </div>
	</div>
      </div>
</div>
      </div>
	</div>
      </div>
</div>
      </div>

	    <footer class="site-footer">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-6">
	            <div class="row">
	              <div class="col-md-7">
	                <h2 class="footer-heading mb-4">About Us</h2>
	                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
	              </div>
	              <div class="col-md-4 ml-auto">
	                <h2 class="footer-heading mb-4">Features</h2>
	                <ul class="list-unstyled">
	                  <li><a href="#">About Us</a></li>
	                  <li><a href="#">Testimonials</a></li>
	                  <li><a href="#">Terms of Service</a></li>
	                  <li><a href="#">Privacy</a></li>
	                  <li><a href="#">Contact Us</a></li>
	                </ul>
	              </div>

	            </div>
	          </div>
	          <div class="col-md-4 ml-auto">

	            <div class="mb-5">
	              <h2 class="footer-heading mb-4">Subscribe to Newsletter</h2>
	              <form action="#" method="post" class="footer-suscribe-form">
	                <div class="input-group mb-3">
	                  <input type="text" class="form-control border-secondary text-white bg-transparent" placeholder="Enter Email" aria-label="Enter Email" aria-describedby="button-addon2">
	                  <div class="input-group-append">
	                    <button class="btn btn-primary text-white" type="button" id="button-addon2">Subscribe</button>
	                  </div>
	                </div>
	            </div>


	            <h2 class="footer-heading mb-4">Follow Us</h2>
	            <a href="#about-section" class="smoothscroll pl-0 pr-3"><span class="icon-facebook"></span></a>
	            <a href="#" class="pl-3 pr-3"><span class="icon-twitter"></span></a>
	            <a href="#" class="pl-3 pr-3"><span class="icon-instagram"></span></a>
	            <a href="#" class="pl-3 pr-3"><span class="icon-linkedin"></span></a>
	            </form>
	          </div>
	        </div>
	        <div class="row pt-5 mt-5 text-center">
	          <div class="col-md-12">
	            <div class="border-top pt-5">
	              <p class="copyright">
	            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" >Colorlib</a>
	            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	            </p>
	            </div>
	          </div>

	        </div>
	      </div>
	    </footer>

	    </div>

	    <script src="/RailSAHAY/resources/js/jquery-3.3.1.min.js"></script>
	    <script src="/RailSAHAY/resources/js/popper.min.js"></script>
	    <script src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
	    <script src="/RailSAHAY/resources/js/owl.carousel.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.sticky.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.waypoints.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.animateNumber.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.fancybox.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.easing.1.3.js"></script>
	    <script src="/RailSAHAY/resources/js/aos.js"></script>
	    <script src="/RailSAHAY/resources/js/stepform.js"></script>

	    <script src="/RailSAHAY/resources/js/main.js"></script>


	  </body>

	</html>
