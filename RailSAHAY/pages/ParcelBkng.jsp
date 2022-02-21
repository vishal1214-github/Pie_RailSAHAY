<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"PARCEL BOOKING","/RailSAHAY/pages/ParcelBkng.jsp",UserDevice,Browser);
%>

<script src="/RailSAHAY/resources/js/miscutil.js"></script>
<script>
$(document).ready(function() {
	fetchPrclBkngLocn();
});
</script>
 <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("PARCEL BOOKING")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">
	<div class="optncard">
	<ul class="nav nav-pills">
	<li class="nav-item">
	<a class="nav-link active" data-toggle="tab" href="#OnlnBkng"><%=E.EH("Online Booking")%></a>
	</li>
	<li class="nav-item">
	<a class="nav-link" data-toggle="tab" href="#ManBkng"><%=E.EH("Manual Booking")%></a>
	</li>
	<li class="nav-item">
	<a class="nav-link" data-toggle="tab" href="#TrmlList"><%=E.EH("Booking Terminals")%></a>
	</li>
	</ul>
	</div>
	<div class="optncard">
	<div class="tab-content">
	<div class="tab-pane container active" id="OnlnBkng">
	  <div class="iconbgorng"><i class="fas fa-laptop fa-lg"></i></div>
	  <div class="container">
		<h4><b><%=E.EH("Online Booking Of Parcels")%></b></h4>
		<div class="list-group-flush">
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step1">1</button></td>
			<td><p class="mb-0">Visit at <a href="http://www.parcel.indianrail.gov.in" class="card-link1" target="_blank">www.parcel.indianrail.gov.in</a></p></td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step2">2</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Register and Login to the Portal")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step3">3</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Fill Origin and Destination Stations in the Online Form")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step4">4</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Select a Train from the List of Trains suggested by the System")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step5">5</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Fill-up the Booking form (Fowarding Note)")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step6">6</button></td>
			<td>
			<p class="mb-0"><%=E.EH("System will calculate Approximate Freight Charges")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step7">7</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Submit the system generated e-Fowarding Note at godown")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step8">8</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Handover the Parcel at Originating Station with Printout of e-Fowarding Note")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step9">9</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Parcel is weighed and actual freight charges are calculated by system at booking counter")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step10">10</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Deposit the Freight Charges and Collect the Railway Receipt (RR)")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step1">11</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Parcel can be tracked using Track & Trace Facility")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step2">12</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Customer gets a notification SMS, when Parcel reaches its Destination")%></p>
			</td>
			</tr>
			</table>
		  </div>
		  <div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step3">13</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Submit the original RR and Collect Parcel from its Destination Station at delivery counter")%></p>
			</td>
			</tr>
			</table>
		  </div>
		</div>
	  </div>
	</div>
	<div class="tab-pane container fade" id="ManBkng">
	 <div class="iconbgblue"><i class="far fa-edit fa-lg"></i></div>
		  <div class="container">
			<h4><b><%=E.EH("Manual Booking of Parcel")%></b></h4>
			<div class="list-group-flush">
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step1">1</button></td>
				<td><p class="mb-0"><%=E.EH("Visit at Parcel Booking Station (Origin)")%></p></td>
				</tr>
				</table>
			  </div>
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step2">2</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Fill-up the Booking Form (Forwarding Note) Manually")%></p>
				</td>
				</tr>
				</table>
			  </div>
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step3">3</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Submit the duly filled Fowarding Note along with the Parcel")%></p>
				</td>
				</tr>
				</table>
			  </div>
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step4">4</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Parcel is Weighed and Freight Charges are calculated manually at the booking counter")%></p>
				</td>
				</tr>
				</table>
			  </div>
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step5">5</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Deposit the Freight Charges and Collect the Railway Receipt (RR)")%></p>
				</td>
				</tr>
				</table>
			  </div>
			<%--
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step6">6</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Parcel can be tracked using Track & Trace Facility")%></p>
				</td>
				</tr>
				</table>
			  </div>
			 --%>
			  <div class="list-group-item">
				<table class="table table-steplist1">
				<tr>
				<td><button class="btn btn-small-step6">6</button></td>
				<td>
				<p class="mb-0"><%=E.EH("Submit the original RR and collect the Parcel from its Destination Station")%></p>
				</td>
				</tr>
				</table>
			  </div>
	  	 </div>
	</div>
	</div>				
	<div class="tab-pane container fade" id="TrmlList">
	 <div class="iconbggreen"><i class="fas fa-route fa-lg"></i></div>
		  <div class="container">
			<h4><b><%=E.EH("List of Terminals for Online Booking of Parcel")%></b></h4>
			<div id="divPrclBkngLocn">
	  		</div>
		</div>
	</div>
	</div>
	</div>
	</div>
          </div>
      </div>
      </div>
<%@ include file="/pages/GG_Footer.jspf" %>