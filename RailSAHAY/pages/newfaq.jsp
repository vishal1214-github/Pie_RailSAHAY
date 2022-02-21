<%@ include file="/pages/GG_Header.jspf" %>
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/sidebar1.css" />
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/sidebar2.css" />
<script src="/RailSAHAY/resources/js/modernizer.custom.js"></script>
	<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
		<h3>Menu</h3>
		<a href="#"><i class="fas fa-plus-square"></i>&nbsp;&nbsp;<%=E.EH("Booking")%></a>
		<a href="#"><i class="far fa-credit-card"></i>&nbsp;&nbsp;<%=E.EH("Payments")%></a>
		<a href="#"><i class="fas fa-file-invoice"></i>&nbsp;&nbsp;<%=E.EH("Freight & Other Charges")%></a>
		<a href="#"><i class="fas fa-tags"></i>&nbsp;&nbsp;<%=E.EH("Policies & Rules")%></a>
		<a href="#"><i class="fas fa-laptop-code"></i>&nbsp;&nbsp;<%=E.EH("Freight Operations Information System (FOIS)")%></a>
		<a href="#"><i class="far fa-address-card"></i>&nbsp;&nbsp;<%=E.EH("How to Reach Us")%></a>
	</nav>
	<div class="container">
		<div class="main">
			<section class="buttonset">
				<button id="showLeftPush"><i class="fas fa-bars"></i></button>
			</section>
		</div>
	</div>
	<!-- Classie - class helper functions by @desandro https://github.com/desandro/classie -->
	<script src="/RailSAHAY/resources/js/classie.js"></script>
	<script>
		var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
			showLeftPush = document.getElementById( 'showLeftPush' ),
			body = document.body;

		showLeftPush.onclick = function() {
			classie.toggle( this, 'active' );
			classie.toggle( body, 'cbp-spmenu-push-toright' );
			classie.toggle( menuLeft, 'cbp-spmenu-open' );
		};
		showLeftPush.click();
	</script>
<%@ include file="/pages/GG_Footer.jspf" %>
