<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"BOOKING AND REGISTRATION","/RailSAHAY/pages/BkngRegProcess.jsp",UserDevice,Browser);
%>
<style>
.stepwizard-step p {
    margin-top: 10px;
}
.stepwizard-row {
    display: table-row;
}
.stepwizard {
    display: table;
    width: 100%;
    position: relative;
    font-size:15px;
    font-weight:600;
}
.stepwizard-step button[disabled] {
    opacity: 1 !important;
    filter: alpha(opacity=100) !important;
}
.stepwizard-row:before {
    top: 18px;
    bottom: 0;
    position: absolute;
    content: " ";
    width: 100%;
    height: 1px;
    background-color: #ccc;
    z-order: 0;
}
.stepwizard-step {
    display: table-cell;
    text-align: center;
    position: relative;
}
.btn-circle {
    width: 40px;
    height: 40px;
    text-align: center;
    padding: 10px 0;
    font-size: 13px;
    line-height: 1.228571429;
    border-radius: 20px;
}
</style>
<script>
$(document).ready(function () {
  var navListItems = $('div.setup-panel div a'),
          allWells = $('.setup-content'),
          allNextBtn = $('.nextBtn'),
  		  allPrevBtn = $('.prevBtn');

  allWells.hide();

  navListItems.click(function (e) {
      e.preventDefault();
      var $target = $($(this).attr('href')),
              $item = $(this);

      if (!$item.hasClass('disabled')) {
          navListItems.removeClass('btn-primary').addClass('btn-default');
          $item.addClass('btn-primary');
          allWells.hide();
          $target.show();
          $target.find('input:eq(0)').focus();
      }
  });
  
  allPrevBtn.click(function(){
      var curStep = $(this).closest(".setup-content"),
          curStepBtn = curStep.attr("id"),
          prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a");

          prevStepWizard.removeAttr('disabled').trigger('click');
  });

  allNextBtn.click(function(){
      var curStep = $(this).closest(".setup-content"),
          curStepBtn = curStep.attr("id"),
          nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
          curInputs = curStep.find("input[type='text'],input[type='url']"),
          isValid = true;

      $(".form-group").removeClass("has-error");
      for(var i=0; i<curInputs.length; i++){
          if (!curInputs[i].validity.valid){
              isValid = false;
              $(curInputs[i]).closest(".form-group").addClass("has-error");
          }
      }

      if (isValid)
          nextStepWizard.removeAttr('disabled').trigger('click');
  });

  $('div.setup-panel div a.btn-primary').trigger('click');
});
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("BOOKING AND REGISTRATION")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="container mt-3">	    
	  <div class="stepwizard col-lg-10 offset-lg-1 col-sm-12">
	      <div class="stepwizard-row setup-panel">
	        <div class="stepwizard-step">
	          <a href="#Registration" type="button" class="btn btn-primary btn-circle">1</a>
	          <p>Registration</p>
	        </div>
	        <div class="stepwizard-step">
	          <a href="#Booking" type="button" class="btn btn-default btn-circle" disabled="disabled">2</a>
	          <p>Booking</p>
	        </div>
	        <div class="stepwizard-step">
	          <a href="#Movement" type="button" class="btn btn-default btn-circle" disabled="disabled">3</a>
	          <p>Movement</p>
	        </div>
	        <div class="stepwizard-step">
	          <a href="#Delivery" type="button" class="btn btn-default btn-circle" disabled="disabled">4</a>
	          <p>Delivery</p>
	        </div>
	      </div>
	    </div>	    
	    <form role="form" action="" method="post">  
	      <div class="row setup-content" id="Booking">
	        <div class="col-lg-10 offset-lg-1 col-sm-12">
	          <%--<div class="iconbgblue"><i class="far fa-edit fa-lg"></i></div>--%>
			  <div class="container" style="margin-top:50px;">
				<h4 style="text-align:center;"><b><%=E.EH("Booking Process")%></b></h4>
				<div class="list-group-flush">

				<div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step8">1</button></td>
					<td><p class="mb-0"><b><%=E.EH("DEMAND REGISTRATION")%></b>
						<br/><%=E.EH("Register your demand for empty goods train, manually at your nearest freight terminal or electronically through E-Demand")%>.<a href="https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/Login_new.jsp" target="_blank" class="card-link1"><%=E.EH("Visit Now")%>..</a></p></td>
					</tr>
					</table>
				  </div>

				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step1">2</button></td>
					<td><p class="mb-0"><b><%=E.EH("WAGON REGISTRATION FEE (WRF)")%></b><br/><%=E.EH("Please Pay the Wagon Registration Fee (WRF)")%>.</p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step2">3</button></td>
					<td><p class="mb-0"><b><%=E.EH("ALLOTMENT OF EMPTY TRAIN (RAKE)")%></b><br/><%=E.EH("Railways shall allot an empty rake(train) and customer is informed about the same")%>.</p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step3">4</button></td>
					<td><p class="mb-0"><b><%=E.EH("RAKE SUPPLY")%></b><br/><%=E.EH("The Rake(Train) is supplied for Loading by Railways and Customer is informed")%>.</p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step4">5</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("RAKE LOADING")%></b><br/><%=E.EH("The customer loads the Rake (Train) and hands over the loaded wagons/train to Railways")%>.</p>
					</td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step5">6</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("WEIGHMENT")%></b><br/><%=E.EH("The Rake is weighed by Railways on nearest weighbridge location")%></p>
					</td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step1">7</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("PAYMENT OF FREIGHT")%></b><br/><%=E.EH("The Customer is required to Pay the Freight Charges through e-Payment service or through manual modes of Payment as applicable")%>.</p>
					</td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step8">8</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("GENERATION OF RR/eT-RR")%></b><br/><%=E.EH("Railways generates a Railway Receipt and same is handed over to the Customer in Physical Form. Registered Customers can opt for Railway Receipt in Electronic Format (eT-RR) which is shared electronically with customer on Registered E-Mail ID")%>.</p>
					</td>
					</tr>
					</table>
				  </div>
			</div>
		    </div>
		    <button class="btn btn-primary prevBtn btn-sm pull-left" type="button">Registration</button>
	            <button class="btn btn-danger nextBtn btn-sm pull-right" type="button">Movement</button>
	          </div>
	      </div>
	      <div class="row setup-content" id="Movement">
	        <div class="col-lg-10 offset-lg-1 col-sm-12">
	          <%--<div class="iconbggreen"><i class="fas fa-route fa-lg"></i></div>--%>
			  <div class="container" style="margin-top:50px;">
				<h4 style="text-align:center;"><b><%=E.EH("Freight Train Movement")%></b></h4>
				<div class="list-group-flush">
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step1">1</button></td>
					<td><p class="mb-0"><b><%=E.EH("RAKE DEPARTURE")%></b><br/><%=E.EH("Loaded Freight Train departs from its source station")%></p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step2">2</button></td>
					<td><p class="mb-0"><b><%=E.EH("RAKE TRACKING")%></b><br/><%=E.EH("The Customer can Track movement of their consignment through FNR (The Freight Number Record, the 11 digit number printed on the RR)")%>.<a href="https://www.fois.indianrail.gov.in/FOISWebPortal/pages/FWP_FNREnq.jsp" target="_blank"><%=E.EH("Track Now")%>..</a> </p></td>							
					</tr>
					</table>
				  </div>

				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step3">3</button></td>
					<td><p class="mb-0"><b><%=E.EH("RAKE ARRIVAL")%></b><br/><%=E.EH("Customer is informed on Arrival of Goods at the destination")%>.</p></td>
					</tr>
					</table>
				  </div>
		  </div>
		  </div>
	            <button class="btn btn-primary prevBtn btn-sm pull-left" type="button">Booking</button>
	            <button class="btn btn-danger nextBtn btn-sm pull-right" type="button">Delivery</button>
	        </div>
	      </div>
	      <div class="row setup-content" id="Delivery">
	        <div class="col-lg-10 offset-lg-1 col-sm-12">
	          <%--<div class="iconbgblue"><i class="fas fa-handshake fa-lg"></i></div>--%>
			  <div class="container" style="margin-top:50px;">
				<h4 style="text-align:center;"><b><%=E.EH("Delivery Process at Destination")%></b></h4>
				<div class="list-group-flush">

				   <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step8">1</button></td>
					<td><p class="mb-0"><b><%=E.EH("RR TRANSFER")%></b><br/><%=E.EH("RR is transferred from Consignor of Consignment to Consignee who shall claim the cargo at destination. In case of Electronic Railway Receipt(eT-RR) transfer of Railway Receipt can be carried out Digitaly using E-Demand Application")%>.</p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step7">2</button></td>
					<td><p class="mb-0"><b><%=E.EH("TRAIN HANDOVER")%></b><br/><%=E.EH("Customer is handed over the Rake/Train for Un-Loading of Goods")%>.</p></td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step4">3</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("RAKE UN-LOADING")%></b><br/><%=E.EH("The Customer unloads the Rake/Train and hands over the Empty Rake/Train back to Railways")%>.</p>
					</td>
					</tr>
					</table>
				  </div>
				  <div class="list-group-item">
					<table class="table table-steplist">
					<tr>
					<td><button class="btn btn-step1">4</button></td>
					<td>
					<p class="mb-0"><b><%=E.EH("DELIVERY OF GOODS")%></b><br/><%=E.EH("Goods are handed over to the Customer on clearance of charges(if any applicable) like Demmurage charges etc")%>.</p>
					</td>
					</tr>
					</table>
				  </div>		  								  
			</div>
	            <button class="btn btn-primary prevBtn btn-sm pull-left" type="button">Registration</button>
	          </div>
	        </div>
	      </div>	      
	      <div class="row setup-content" id="Registration">
		<div class="col-lg-10 offset-lg-1 col-sm-12">
		  <%--<div class="iconbgorng"><i class="fas fa-user-plus fa-lg"></i></div>--%>
		  <div class="container" style="margin-top:50px;">
			<h4 style="text-align:center;"><b><%=E.EH("3-Step Process for Registration")%></b></h4>
			<div class="list-group-flush">
			  <div class="list-group-item">
				<table class="table table-steplist">
				<tr>
				<td><button class="btn btn-step1">1</button></td>
				<td>
					<p class="mb-0"><%=E.EH("Register yourself by filling your detail and uploading scanned copies of self-attested, KYC documents- Passport Size Photo, PAN Card, Aadhaar Card and ID Card")%>. <a href="https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/RgtrUserN.jsf" class="card-link1"  target="_blank"><%=E.EH("Visit Now")%>..</a> </p>
				</td>
				</tr>
				</table>
			  </div>
			<%--
			  <div class="list-group-item">
				<table class="table table-steplist">
				<tr>
				<td><button class="btn btn-step2">2</button></td>
				<td>
					<p class="mb-0"><%=E.EH("Please contact the Office of Divisional Railway Manager(DRM), as indicated during user registration process with Original KYC Documents")%>.</p>
				</td>
				</tr>
				</table>
			  </div>--%>
			  <div class="list-group-item">
				<table class="table table-steplist">
				<tr>
				<td><button class="btn btn-step3">2</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Railway shall provide login Credentials after Verification of the Documents")%></p>
				</td>
				</tr>
				</table>
			  </div>

			  <div class="list-group-item">
				<table class="table table-steplist">
				<tr>
				<td><button class="btn btn-step4">3</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Please Login (using your allotted login credentials, provided by Railways) for availing e-Demand Facility and Register your demand online for empty trains")%>.<a href="https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/Login_new.jsp"  target="_blank"><%=E.EH("Visit Now")%>..</a> 
				</p>
				</td>
				</tr>
				</table>
			  </div>
			  <div class="list-group-item">
				<table class="table table-steplist">
				<tr>
				<td><button class="btn btn-step5"><i class="far fa-lightbulb fa-lg"></i></button></td>
				<td>
				<p class="mb-0"><b><%=E.EH("Benefits of getting registered with Indian Railways")%></b></p>
				<ul>
					<li><%=E.EH("Hassle-free Booking of Indents")%></li>
					<li><%=E.EH("Innovative Freight Transportation Schemes")%></li>
					<li><%=E.EH("Secured and Convenient Payment Options")%></li>
					<li><%=E.EH("End-to-End Consignment Track 'n' Trace")%></li>
					<li><%=E.EH("Notification of consignment status")%></li>
					<li><%=E.EH("Connecting Nodal Officers for any Concern")%></li>
				</ul>
				</td>
				</tr>
				</table>
			  </div>
			</div>
	            <button class="btn btn-danger nextBtn btn-sm pull-right" type="button">Booking</button>
		  </div>
		</div>
	      </div>
	    </form>
	    
</div>
          
          
<div class="row">
<div class="col-lg-10 offset-lg-1 col-sm-12 mt-2">          
<p class="mesg-cnt"><%=E.EH("We will be happy to transport your cargo")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a>
</p>
</div>
</div>
				</div>
      </div>
      </div>

<%@ include file="/pages/GG_Footer.jspf" %>

            