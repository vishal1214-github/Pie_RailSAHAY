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
	    <form class="form-inline" action="/action_page.php">
	      <input class="form-control input-control mr-sm-2" type="text" placeholder="TKD (TUGHLAKABAD)">
	      <button class="btn btn-success" type="submit">Continue</button>
	    </form>
	</nav>

      <div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">TERMINAL ANALYSIS</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
          <div class="col-lg-8 col-sm-12">
			<div class="card">
			  <div class="card-header text-left">Profile<button class="btn btn-primary btn-sm float-right" style="color:#fff;"></button><a href="#smart" style="color:#fff;">SMART Facilities</a></button></div>
			  <div class="card-body text-left">
			  	<table class="table table-striped table-condensed table-data">
			  		<tr>
			  			<td class="proplbl"><b>Terminal</b></td>
			  			<td class="propval"><b>TKD (TUGHLAKABAD)</b></td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Type</td>
			  			<td class="propval">PFT (PRIVATE FREIGHT TERMINAL)</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Division/Zone</td>
			  			<td class="propval">(DLI- DELHI)/NR</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">District</td>
			  			<td class="propval">NEW DELHI/DELHI</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Commodity Allowed</td>
			  			<td class="propval"><b>Inward:</b> COAL, FG, CEMT, ORES, IS<br/>
			  			<b>Outward:</b> COAL, FG, CEMT, ORES, IS</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Traffic Open</td>
			  			<td class="propval"><b>Inward:</b> TRAIN LOAD<br/>
			  			<b>Outward:</b> WAGON LOAD</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Working Hours</td>
			  			<td class="propval">06:00 TO 22:00</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Facilities Available</td>
			  			<td class="propval">
			  			<ul class="list-group list-group-flush">
			  				<li class="list-group-item">3 LINES</li>
			  				<li class="list-group-item">HIGH MAST LIGHTING</li>
			  				<li class="list-group-item">SHED LENGTH (200 MTRS)</li>
			  				<li class="list-group-item">LOOSE SIDING</li>
			  				<li class="list-group-item">HIGH LEVEL PLATFORM</li>
			  				<li class="list-group-item">HANDLING TYPE (MANUAL & MECHANICAL)</li>
			  				<li class="list-group-item">WAREHOUSE AVAILABLE</li>
			  				<li class="list-group-item">WEIGHBRIDGE LOCATION: SSB, TICD</li>
			  			</ul>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="proplbl">Contact Detail</td>
			  			<td class="propval">SRDCM/DLI (MOB NO 9717631951, OFFICE NO 011-23345686)</td>
			  		</tr>
			  	</table>
			  </div>
			</div>
          </div>
		<div class="col-lg-4 col-sm-12">

			<div class="w3-card-5">
				<div style="width: 95%">
					<canvas id="cmdtRakeHndl"></canvas>
					<div align="center" style="width:100%;">
						<table align="center" class="tblcolr">
							<tr>
								<td><span class="dotCmdt1">  </span></td>
								<td style="border:0pt;">COAL</td>
								<td><span class="dotCmdt2"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">FG</td>
								<td><span class="dotCmdt3"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">CEMT</td>
								<td><span class="dotCmdt4"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">ORES</td>
								<td><span class="dotCmdt5"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">IS</td>
							</tr>
						</table>
				</div>
				</div>
			</div>
          	<div class="w3-card-5">
				<div style="width: 95%">
					<canvas id="canvasLdng"></canvas>
					<div align="center" style="width:100%;">
						<table align="center" class="tblcolr">
							<tr>
								<td><span class="dotLdng" id="spnAgCont"></span></td>
								<td style="border:0pt;">Loading Time</td>
								<td><span class="dotLdngDprt" id="spnTrckCont"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">Loading-Departure</td>
							</tr>
						</table>
				</div>
			</div>
			</div>
			<div class="w3-card-5">
				<div style="width: 95%">
					<canvas id="canvasUldg"></canvas>
				</div>
					<div align="center" style="width:100%;">
						<table align="center" class="tblcolr">
							<tr>
								<td><span class="dotArvlPlct" id="spnAgCont">  </span></td>
								<td style="border:0pt;">Arrival-Placement</td>
								<td><span class="dotUldg" id="spnTrckCont"> </span></td>
								<td style="border:0pt;nowrap:nowrap;">Unloading Time</td>
							</tr>
						</table>
				</div>
				<script>
					var ldngData = {
						labels: ['COAL', 'FG', 'CEMT', 'ORES', 'IS'],
						datasets: [{
							label: 'Loading Time',
							backgroundColor: '#dc3545',
							stack: 'Stack 0',
							data: [
								12.5,
								13.3,
								11.3,
								16.5,
								18.4,
								14.5,
								11.35
							]
						}, {
							label: 'Loading-Departure',
							backgroundColor: '#28a745',
							stack: 'Stack 0',
							data: [
								4.5,
								3.4,
								3.3,
								5.3,
								6.2,
								2.4,
								1.45
							]
						}]

					};

					var uldgData = {
						labels: ['COAL', 'FG', 'CEMT', 'ORES', 'IS'],
						datasets: [{
							label: 'Arrival-Placement',
							backgroundColor: '#007bff',
							stack: 'Stack 0',
							data: [
								12.5,
								13.3,
								11.3,
								16.5,
								18.4,
								14.5,
								11.35
							]
						}, {
							label: 'Unloading',
							backgroundColor: '#ffc107',
							stack: 'Stack 0',
							data: [
								4.5,
								3.4,
								3.3,
								5.3,
								6.2,
								2.4,
								1.45
							]
						}]

					};
					window.onload = function() {
						var ctx = document.getElementById('canvasLdng').getContext('2d');
						window.myBar = new Chart(ctx, {
							type: 'bar',
							data: ldngData,
							options: {
								title: {
									display: true,
									text: 'Loading Performance (Hrs:Min)',
									fontColor:'#000',
									fontSize:13,
									fontStyle:'normal'
								},
								legend: {
									display: false
								},
								tooltips: {
									mode: 'index',
									intersect: false
								},
								responsive: true,
								scales: {
									xAxes: [{
										stacked: true,
									}],
									yAxes: [{
										stacked: true
									}]
								}
							}
						});

						var ctx1 = document.getElementById('canvasUldg').getContext('2d');
						window.myBar = new Chart(ctx1, {
							type: 'bar',
							data: uldgData,
							options: {
								title: {
									display: true,
									text: 'Unloading Performance (Hrs:Min)',
									fontColor:'#000',
									fontSize:13,
									fontStyle:'normal'
								},
								legend: {
									display: false
								},
								tooltips: {
									mode: 'index',
									intersect: false
								},
								responsive: true,
								scales: {
									xAxes: [{
										stacked: true,
									}],
									yAxes: [{
										stacked: true
									}]
								}
							}
						});

							var config = {
									type: 'doughnut',
									data: {
										datasets: [{
											data: [
												120,
												21,
												80,
												12,
												34,
											],
											backgroundColor: [
												'#993399',
												'#339933',
												'#ff9900',
												'#8a00e6',
												'#0039e6',
											],
											label: 'Commodity Handling'
										}],
										labels: [
											'COAL',
											'FG',
											'CEMT',
											'ORES',
											'IS'
										]
									},
									options: {
										responsive: true,
										legend: {
											position: 'top',
											display:false
										},
										title: {
											display: true,
											text: 'Commodity Rake Handling',
											fontColor:'#000',
											fontSize:13,
											fontStyle:'normal'
										},
										animation: {
											animateScale: true,
											animateRotate: true
										}
									}
						};

						var ctx2 = document.getElementById('cmdtRakeHndl').getContext('2d');
						window.myDoughnut = new Chart(ctx2, config);
					};

	</script>
		  </div>
		</div>
        </div>
      </div>
      <br name="smart" id="smart"/>
	  <div class="card">
	    <div class="card-header text-left lsp-header">Logistics Service Providers <b>(SMART)</b><span class="float-right"><span class="dot1"></span>&nbsp;Company | <span class="dotIndv"></span>&nbsp;Individual</span></div>
		 <div class="card-body text-left">
      <div class="row">
      	<div class="col-lg-3 col-sm-12">
			<table class="table table-bordered tbl-fcty">
				<tr>
					<td>
						<div class="courses-container">
							<div class="course">
								<div class="cardaggr">
									<i class="fas fa-store-alt fa-lg"></i>
								</div>
								<div class="course-info">
									<h2>3</h2>
									<button class="btn">AGGREGATORS</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="courses-container">
							<div class="course">
								<div class="cardtrck">
									<i class="fas fa-truck fa-lg"></i>
								</div>
								<div class="course-info">
									<h2>2</h2>
									<button class="btn">TRUCKERS</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="courses-container">
							<div class="course">
								<div class="cardwh">
									<i class="fas fa-warehouse fa-lg"></i>
								</div>
								<div class="course-info">
									<h2>4</h2>
									<button class="btn">WAREHOUSE</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="courses-container">
							<div class="course">
								<div class="cardlab">
									<i class="fas fa-people-carry fa-lg"></i>
								</div>
								<div class="course-info">
									<h2>7</h2>
									<button class="btn">LABOURS</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
      	<div class="col-lg-9 col-sm-12">
			<ul class="list-group list-group-flush">
			<li class="list-group-item"><table class="table table-bordered tbl-lspdtls" style="width:100%;border:0;"><tbody><tr><td><p class="compname"><span class="dot1"></span>&nbsp;M/s LEADERCROP Private Limited</p><p class="compaddr">Flat No-125, Mangalam Tower, Main Road, Gamharia Seraikela-Kharsawan, Jharkhand 832108</p><p class="compfcty">Enlisted and contact with various companies</p><p class="person">Manish Kumar Pandey<br><svg class="svg-inline--fa fa-phone-square-alt fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="phone-square-alt" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48A48 48 0 0 0 0 80v352a48 48 0 0 0 48 48h352a48 48 0 0 0 48-48V80a48 48 0 0 0-48-48zm-16.39 307.37l-15 65A15 15 0 0 1 354 416C194 416 64 286.29 64 126a15.7 15.7 0 0 1 11.63-14.61l65-15A18.23 18.23 0 0 1 144 96a16.27 16.27 0 0 1 13.79 9.09l30 70A17.9 17.9 0 0 1 189 181a17 17 0 0 1-5.5 11.61l-37.89 31a231.91 231.91 0 0 0 110.78 110.78l31-37.89A17 17 0 0 1 299 291a17.85 17.85 0 0 1 5.91 1.21l70 30A16.25 16.25 0 0 1 384 336a17.41 17.41 0 0 1-.39 3.37z"></path></svg><!-- <i class="fas fa-phone-square-alt"></i> --> 7979934428  |  <svg class="svg-inline--fa fa-envelope-square fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="envelope-square" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V80c0-26.51-21.49-48-48-48zM178.117 262.104C87.429 196.287 88.353 196.121 64 177.167V152c0-13.255 10.745-24 24-24h272c13.255 0 24 10.745 24 24v25.167c-24.371 18.969-23.434 19.124-114.117 84.938-10.5 7.655-31.392 26.12-45.883 25.894-14.503.218-35.367-18.227-45.883-25.895zM384 217.775V360c0 13.255-10.745 24-24 24H88c-13.255 0-24-10.745-24-24V217.775c13.958 10.794 33.329 25.236 95.303 70.214 14.162 10.341 37.975 32.145 64.694 32.01 26.887.134 51.037-22.041 64.72-32.025 61.958-44.965 81.325-59.406 95.283-70.199z"></path></svg><!-- <i class="fas fa-envelope-square"></i> --> info@leadercrop.com</p></td><td align="right"><a data-toggle="collapse" href="#collapseExample0" role="button" aria-expanded="false" aria-controls="collapseExample0"><svg class="svg-inline--fa fa-info-circle fa-w-16 fa-lg" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle fa-lg"></i> --></a></td></tr></tbody></table><div class="collapse" id="collapseExample0"><div class="card card-body"><table><tbody><tr><td><span class="spngst">GST No.: 20AADCL3741K1ZR</span><p class="spnaddlinfo"><svg class="svg-inline--fa fa-info-circle fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle"></i> -->&nbsp;Few examples are Tufcon Steel</p></td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample0" aria-expanded="false" aria-controls="collapseExample0" class="float-right closeicon"><svg class="svg-inline--fa fa-times-circle fa-w-16 fa-sm" aria-hidden="true" focusable="false" data-prefix="far" data-icon="times-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm0 448c-110.5 0-200-89.5-200-200S145.5 56 256 56s200 89.5 200 200-89.5 200-200 200zm101.8-262.2L295.6 256l62.2 62.2c4.7 4.7 4.7 12.3 0 17l-22.6 22.6c-4.7 4.7-12.3 4.7-17 0L256 295.6l-62.2 62.2c-4.7 4.7-12.3 4.7-17 0l-22.6-22.6c-4.7-4.7-4.7-12.3 0-17l62.2-62.2-62.2-62.2c-4.7-4.7-4.7-12.3 0-17l22.6-22.6c4.7-4.7 12.3-4.7 17 0l62.2 62.2 62.2-62.2c4.7-4.7 12.3-4.7 17 0l22.6 22.6c4.7 4.7 4.7 12.3 0 17z"></path></svg><!-- <i class="far fa-times-circle fa-sm"></i> --></a></span></td></tr></tbody></table></div></div></li>
			<li class="list-group-item"><table class="table table-bordered tbl-lspdtls" style="width:100%;border:0;"><tbody><tr><td><p class="compname"><span class="dot1"></span>&nbsp;M/s LEADERCROP Private Limited</p><p class="compaddr">Flat No-125, Mangalam Tower, Main Road, Gamharia Seraikela-Kharsawan, Jharkhand 832108</p><p class="compfcty">All types of Trucks/Trailers/Loading Vehicles available.(More than 250 nos)</p><p class="person">Manish Kumar Pandey<br><svg class="svg-inline--fa fa-phone-square-alt fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="phone-square-alt" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48A48 48 0 0 0 0 80v352a48 48 0 0 0 48 48h352a48 48 0 0 0 48-48V80a48 48 0 0 0-48-48zm-16.39 307.37l-15 65A15 15 0 0 1 354 416C194 416 64 286.29 64 126a15.7 15.7 0 0 1 11.63-14.61l65-15A18.23 18.23 0 0 1 144 96a16.27 16.27 0 0 1 13.79 9.09l30 70A17.9 17.9 0 0 1 189 181a17 17 0 0 1-5.5 11.61l-37.89 31a231.91 231.91 0 0 0 110.78 110.78l31-37.89A17 17 0 0 1 299 291a17.85 17.85 0 0 1 5.91 1.21l70 30A16.25 16.25 0 0 1 384 336a17.41 17.41 0 0 1-.39 3.37z"></path></svg><!-- <i class="fas fa-phone-square-alt"></i> --> 7979934428  |  <svg class="svg-inline--fa fa-envelope-square fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="envelope-square" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V80c0-26.51-21.49-48-48-48zM178.117 262.104C87.429 196.287 88.353 196.121 64 177.167V152c0-13.255 10.745-24 24-24h272c13.255 0 24 10.745 24 24v25.167c-24.371 18.969-23.434 19.124-114.117 84.938-10.5 7.655-31.392 26.12-45.883 25.894-14.503.218-35.367-18.227-45.883-25.895zM384 217.775V360c0 13.255-10.745 24-24 24H88c-13.255 0-24-10.745-24-24V217.775c13.958 10.794 33.329 25.236 95.303 70.214 14.162 10.341 37.975 32.145 64.694 32.01 26.887.134 51.037-22.041 64.72-32.025 61.958-44.965 81.325-59.406 95.283-70.199z"></path></svg><!-- <i class="fas fa-envelope-square"></i> --> info@leadercrop.com</p></td><td align="right"><a data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample2"><svg class="svg-inline--fa fa-info-circle fa-w-16 fa-lg" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle fa-lg"></i> --></a></td></tr></tbody></table><div class="collapse" id="collapseExample2"><div class="card card-body"><table><tbody><tr><td><span class="spngst">GST No.: 20AADCL3741K1ZR</span><p class="spnaddlinfo"><svg class="svg-inline--fa fa-info-circle fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle"></i> -->&nbsp;Entered into agreement with Other Transport Agencies also</p></td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample2" aria-expanded="false" aria-controls="collapseExample2" class="float-right closeicon"><svg class="svg-inline--fa fa-times-circle fa-w-16 fa-sm" aria-hidden="true" focusable="false" data-prefix="far" data-icon="times-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm0 448c-110.5 0-200-89.5-200-200S145.5 56 256 56s200 89.5 200 200-89.5 200-200 200zm101.8-262.2L295.6 256l62.2 62.2c4.7 4.7 4.7 12.3 0 17l-22.6 22.6c-4.7 4.7-12.3 4.7-17 0L256 295.6l-62.2 62.2c-4.7 4.7-12.3 4.7-17 0l-22.6-22.6c-4.7-4.7-4.7-12.3 0-17l62.2-62.2-62.2-62.2c-4.7-4.7-4.7-12.3 0-17l22.6-22.6c4.7-4.7 12.3-4.7 17 0l62.2 62.2 62.2-62.2c4.7-4.7 12.3-4.7 17 0l22.6 22.6c4.7 4.7 4.7 12.3 0 17z"></path></svg><!-- <i class="far fa-times-circle fa-sm"></i> --></a></span></td></tr></tbody></table></div></div></li>
			<li class="list-group-item"><table class="table table-bordered tbl-lspdtls" style="width:100%;border:0;"><tbody><tr><td><p class="compname"><span class="dot1"></span>&nbsp;M/s LEADERCROP Private Limited</p><p class="compaddr">Flat No-125, Mangalam Tower, Main Road, Gamharia Seraikela-Kharsawan, Jharkhand 832108</p><p class="compfcty">Ultra modern tech equipped warehouse with space 50,000 SqFt available</p><p class="person">Manish Kumar Pandey<br><svg class="svg-inline--fa fa-phone-square-alt fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="phone-square-alt" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48A48 48 0 0 0 0 80v352a48 48 0 0 0 48 48h352a48 48 0 0 0 48-48V80a48 48 0 0 0-48-48zm-16.39 307.37l-15 65A15 15 0 0 1 354 416C194 416 64 286.29 64 126a15.7 15.7 0 0 1 11.63-14.61l65-15A18.23 18.23 0 0 1 144 96a16.27 16.27 0 0 1 13.79 9.09l30 70A17.9 17.9 0 0 1 189 181a17 17 0 0 1-5.5 11.61l-37.89 31a231.91 231.91 0 0 0 110.78 110.78l31-37.89A17 17 0 0 1 299 291a17.85 17.85 0 0 1 5.91 1.21l70 30A16.25 16.25 0 0 1 384 336a17.41 17.41 0 0 1-.39 3.37z"></path></svg><!-- <i class="fas fa-phone-square-alt"></i> --> 7979934428  |  <svg class="svg-inline--fa fa-envelope-square fa-w-14" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="envelope-square" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M400 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V80c0-26.51-21.49-48-48-48zM178.117 262.104C87.429 196.287 88.353 196.121 64 177.167V152c0-13.255 10.745-24 24-24h272c13.255 0 24 10.745 24 24v25.167c-24.371 18.969-23.434 19.124-114.117 84.938-10.5 7.655-31.392 26.12-45.883 25.894-14.503.218-35.367-18.227-45.883-25.895zM384 217.775V360c0 13.255-10.745 24-24 24H88c-13.255 0-24-10.745-24-24V217.775c13.958 10.794 33.329 25.236 95.303 70.214 14.162 10.341 37.975 32.145 64.694 32.01 26.887.134 51.037-22.041 64.72-32.025 61.958-44.965 81.325-59.406 95.283-70.199z"></path></svg><!-- <i class="fas fa-envelope-square"></i> --> info@leadercrop.com</p></td><td align="right"><a data-toggle="collapse" href="#collapseExample3" role="button" aria-expanded="false" aria-controls="collapseExample3"><svg class="svg-inline--fa fa-info-circle fa-w-16 fa-lg" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle fa-lg"></i> --></a></td></tr></tbody></table><div class="collapse" id="collapseExample3"><div class="card card-body"><table><tbody><tr><td><span class="spngst">GST No.: 20AADCL3741K1ZR</span><p class="spnaddlinfo"><svg class="svg-inline--fa fa-info-circle fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="info-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"></path></svg><!-- <i class="fas fa-info-circle"></i> -->&nbsp;Additonal agreement entered into, for more space, if required.</p></td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample3" aria-expanded="false" aria-controls="collapseExample3" class="float-right closeicon"><svg class="svg-inline--fa fa-times-circle fa-w-16 fa-sm" aria-hidden="true" focusable="false" data-prefix="far" data-icon="times-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm0 448c-110.5 0-200-89.5-200-200S145.5 56 256 56s200 89.5 200 200-89.5 200-200 200zm101.8-262.2L295.6 256l62.2 62.2c4.7 4.7 4.7 12.3 0 17l-22.6 22.6c-4.7 4.7-12.3 4.7-17 0L256 295.6l-62.2 62.2c-4.7 4.7-12.3 4.7-17 0l-22.6-22.6c-4.7-4.7-4.7-12.3 0-17l62.2-62.2-62.2-62.2c-4.7-4.7-4.7-12.3 0-17l22.6-22.6c4.7-4.7 12.3-4.7 17 0l62.2 62.2 62.2-62.2c4.7-4.7 12.3-4.7 17 0l22.6 22.6c4.7 4.7 4.7 12.3 0 17z"></path></svg><!-- <i class="far fa-times-circle fa-sm"></i> --></a></span></td></tr></tbody></table></div></div></li>
			</ul>
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

    <script src="/RailSAHAY/resources/js/main.js"></script>


  </body>

</html>
