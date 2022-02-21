<!doctype html>
<html lang="en">

  <head>
    <title>Cargo &mdash; Website Template by Colorlib</title>
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
     <style>
	       /* Set the size of the div element that contains the map */
	      #map {
	        height:250px;  /* The height is 400 pixels */
	        width: 100%;  /* The width is the width of the web page */
	       }
    </style>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1QmcXEXKkGqi2H3JVyYSMAhg2q8c-jkM&callback=initMap"
  type="text/javascript"></script>
<script>
    // Set up global variable
    var result;
	var userLat=21;
	var userLng=78;
    function showPosition() {
        // Store the element where the page displays the result
        result = document.getElementById("result");

        // If geolocation is available, try to get the visitor's position
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
            result.innerHTML = "Getting the position information...";
        } else {
            alert("Sorry, your browser does not support HTML5 geolocation.");
        }
    };

    // Define callback function for successful attempt
    function successCallback(position) {
    	userLat=position.coords.latitude;
    	userLng=position.coords.longitude;
		initMap();
        result.innerHTML = "Your current position is (" + "Latitude: " + position.coords.latitude + ", " + "Longitude: " + position.coords.longitude + ")";
    }

    // Define callback function for failed attempt
    function errorCallback(error) {
        if(error.code == 1) {
            result.innerHTML = "You've decided not to share your position, but it's OK. We won't ask you again.";
        } else if(error.code == 2) {
            result.innerHTML = "The network is down or the positioning service can't be reached.";
        } else if(error.code == 3) {
            result.innerHTML = "The attempt timed out before it could get the location data.";
        } else {
            result.innerHTML = "Geolocation failed due to unknown error.";
        }
    }
    function initMap() {
	  // The location of Uluru
	  var uluru = {lat: userLat, lng: userLng};
	  // The map, centered at Uluru
	  var map = new google.maps.Map(
	      document.getElementById('map'), {zoom: 4, center: uluru});
	  // The marker, positioned at Uluru
	  var marker = new google.maps.Marker({position: uluru, map: map});
}
</script>
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
				  <!--
					<li class="has-children">
					  <a href="#about-section" class="nav-link">Service Request</a>
					  <ul class="dropdown arrow-top">
						<li><a href="#team-section" class="nav-link">Diversion</a></li>
						<li><a href="#pricing-section" class="nav-link">Demmurage Waiver</a></li>
						<li><a href="#faq-section" class="nav-link">Night Unloading</a></li>
						<li class="has-children">
						  <a href="#">More Links</a>
						  <ul class="dropdown">
							<li><a href="#">Menu One</a></li>
							<li><a href="#">Menu Two</a></li>
							<li><a href="#">Menu Three</a></li>
						  </ul>
						</li>
					  </ul>
					</li>-->
					<li><a href="/RailSAHAY/pages/LodgeConcern.jsp" class="nav-link">Lodge A Concern</a></li>
				<li class="has-children">
				  <a href="#about-section" class="nav-link">Booking Information</a>
				  <ul class="dropdown arrow-top">
					<li><a href="#team-section" class="nav-link">Freight Calculator</a></li>
					<li><a href="#pricing-section" class="nav-link">Freight Rates</a></li>
					<li><a href="#faq-section" class="nav-link">FNR Track n Trace</a></li>
					<li class="has-children">
					  <a href="#">More Links</a>
					  <ul class="dropdown">
						<li><a href="#">Menu One</a></li>
						<li><a href="#">Menu Two</a></li>
						<li><a href="#">Menu Three</a></li>
					  </ul>
					</li>
				  </ul>
					</li>
				<li class="has-children">
				  <a href="#about-section" class="nav-link">General Enquiry</a>
				  <ul class="dropdown arrow-top">
					<li><a href="/RailSAHAY/pages/TerminalAnalysis.jsp" class="nav-link">Station Profile</a></li>
					<li><a href="/RailSAHAY/pages/LoadingLocations.jsp" class="nav-link">Loading Locations</a></li>
					<li><a href="#faq-section" class="nav-link">Unloading Locations</a></li>
					<li><a href="#faq-section" class="nav-link">CRT Locations</a></li>
					<li><a href="#faq-section" class="nav-link">PFT Locations</a></li>
					<li class="has-children">
					  <a href="#">More Links</a>
					  <ul class="dropdown">
						<li><a href="#">Menu One</a></li>
						<li><a href="#">Menu Two</a></li>
						<li><a href="#">Menu Three</a></li>
					  </ul>
					</li>
				  </ul>
					</li>
					<li><a href="#contact-section" class="nav-link">SMART Connect</a></li>
					<li><a href="/RailSAHAY/pages/eDmndLogin.jsp" class="nav-link">Sign In</a></li>
				  </ul>
              </nav>

            </div>

            <div class="toggle-button d-inline-block d-lg-none"><a href="#" class="site-menu-toggle py-5 js-menu-toggle text-black"><span class="icon-menu h3"></span></a></div>

          </div>
        </div>
      </header>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	    <form class="form-inline" >
	      <div class="dropdown">
		    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
		      State
		    </button>
		    <div class="dropdown-menu">
		      <a class="dropdown-item" href="#">Near Me</a>
		      <div class="dropdown-divider"></div>
		      <a class="dropdown-item" href="#">Sate</a>
		      <a class="dropdown-item" href="#">District</a>
		      <a class="dropdown-item" href="#">Zone</a>
		      <a class="dropdown-item" href="#">Division</a>

		    </div>
		</div>&nbsp;&nbsp;
	      <input class="form-control input-control mr-sm-2" type="text" placeholder="TKD (TUGHLAKABAD)">

		  	      <div class="dropdown">
		  		    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
		  		      Commodity
		  		    </button>
		  		    <div class="dropdown-menu">
		  		      <a class="dropdown-item" href="#">COAL</a>
		  		      <a class="dropdown-item" href="#">CEMT</a>
		  		      <a class="dropdown-item" href="#">FG</a>
		  		      <a class="dropdown-item" href="#">ORES</a>
		  		      <a class="dropdown-item" href="#">CONT</a>
		  		      <a class="dropdown-item" href="#">IS</a>
		  		    </div>
		</div>&nbsp;&nbsp;
	      <button class="btn btn-success" type="button" onclick="showPosition();">Continue</button>
	    </form>
	</nav>

      <div class="container-fullwidth">
       		<div id="map"></div>
			<div id="result" class="row"></div>
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

    <script src="/RailSAHAY/resources/js/main.js"></script>


  </body>

</html>
