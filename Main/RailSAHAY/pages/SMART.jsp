<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SMART","/RailSAHAY/pages/SMART.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/smart.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/smart.css">
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("LOGISTICS PARTNERS")%></h3>
              </div>
            </div>
           </div>
          </div>
         
         
	 <div class="row" style="margin-left:0.2rem;margin-right:0.2rem;">
	 <div class="col-lg-8 col-md-8 col-sm-12">
	 <ul class="list-group">
	   <li class="list-group-item" align="center">
	   <div class="btn-group btn-group-justified" style="width:100%;">
	       <button type="button" class="btn btn-fcty btn-primary-new"><%=E.EH("AGGREGATOR")%></button>
	       <button type="button" class="btn btn-fcty btn-success-new"><%=E.EH("TRUCKER")%></button>
	       <button type="button" class="btn btn-fcty btn-danger-new"><%=E.EH("WAREHOUSE")%></button>
	       <button type="button" class="btn btn-fcty btn-warning-new"><%=E.EH("LABOUR")%></button>
	     </div>
	   </li>
	   <li class="list-group-item">
	   <p class="pHighlight"><%=E.EH("If you are, one of the above service providers")%> !</p>
	   <p class="pBody"><%=E.EH("Indian Railways (IR) invites you to offer your services for")%></p>
	   <table class="table table-bordered table-striped">
	   	<tr>
	   		<td align="center"><div class="dotCust" id="divCustCont"></div><p class="pcustcont"><%=E.EH("Freight Customers")%></p></td>
	   		<td align="center"><div class="dotTrml" id="divTrmlCont"></div><p class="pcustcont"><%=E.EH("Freight Terminals")%></p></td>
	   	</tr>
	   </table>
	   </li>
	   <li>
	   <p class="regstep"><%=E.EH("5-STEP PROCESS TO REGISTER AS A SERVICE PROVIDER")%></p>
	   <div class="list-group-flush">
		<div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step1">1</button></td>
			<td><p class="mb-0"><%=E.EH("Visit your Nearest Freight Terminal")%></p></td>
			</tr>
			</table>
		</div>
		<div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step2">2</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Submit a duly signed Service Request Letter")%></p>
			</td>
			</tr>
			</table>
		</div>
		<div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step3">3</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Provision yourself for one of more service(s)")%></p>
			</td>
			</tr>
			</table>
		</div>
		<div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step4">4</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Authenticate through Registered Mobile Number and Email Id")%></p>
			</td>
			</tr>
			</table>
		</div>
		<div class="list-group-item">
			<table class="table table-steplist1">
			<tr>
			<td><button class="btn btn-small-step5">5</button></td>
			<td>
			<p class="mb-0"><%=E.EH("Reach your customers digitally through IR Freight Business Portal, Rail Sugam Mobile App and FOIS Web Portal")%></p>
			</td>
			</tr>
			</table>
		</div>
	    </div>
		
	   </li>
	 </ul>
	 </div>
	 <div class="col-lg-4 col-md-4 col-sm-12" >	
	 	
	  <div class="card">
	   <div class="card-header" style="text-align:center;">
	     <b><%=E.EH("Registered Services")%>:</b> &nbsp;&nbsp;<span class="badge badge-info badge-cont" id="spnCompCont"></span> <%=E.EH("Companies")%> &nbsp;|&nbsp; <span class="badge badge-info badge-cont" id="spnIndCont"></span> <%=E.EH("Individuals")%>
	     <br/>
	   </div>
	   <div class="card-body">
	   	<div id="canvas-holder" style="width:100%">
	 		<canvas id="emarket_chrt"></canvas>
	 	</div>
	 	<br/>
	 	<div align="center" style="width:100%;">
	 		<table align="center" class="tblcolr table table-striped">
	 			<tr>
	 				<td><span class="badge badge-primary badge-cont" id="spnAgCont"></span></td>
	 				<td style="border:0pt;"><%=E.EH("AGGREGATORS")%></td>
	 				<td><span class="badge badge-success badge-cont" id="spnTrckCont"></span></td>
	 				<td style="border:0pt;nowrap:nowrap;"><%=E.EH("TRUCKERS")%></td>
				</tr><tr>
	 				<td><span class="badge badge-danger badge-cont" id="spnWHCont"></span></td>
	 				<td style="border:0pt;"><%=E.EH("WAREHOUSES")%></td>
	 				<td><span class="badge badge-warning badge-cont" id="spnLabCont"></span></td>
	 				<td style="border:0pt;"><%=E.EH("LABOURS")%></td>
	 			</tr>
	 		</table>
	 	</div>
		<br/>
	 	<hr/><br/>
	 	<div align="center" style="width:100%;">
	 		<table align="center" class="tblfctylist table table-bordered">
	 			<tr><th><%=E.EH("Terminal Facilities Detail Available at")%></th></tr>
	 			<tr><td><a href="/RailSAHAY/pages/FrgtCalc.jsp" target="_self" class="smartlink"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Expected Freight & Transit Time Estimator")%></a></td></tr>
	 			<tr><td><a href="/RailSAHAY/pages/TrmlDashboard.jsp" target="_self" class="smartlink"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Choose A Terminal")%></a></td></tr>
	 			<tr><td><a href="/RailSAHAY/pages/SttnProf.jsp" target="_self" class="smartlink"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("First & Last Mile Service Providers")%></a></td></tr>
	 			<tr><td><a href="/RailSAHAY/pages/LdngLocn.jsp" target="_self" class="smartlink"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Individual Login")%></a></td></tr>
	 		</table>
	 	</div>
	   </div>
	 </div>
	 <!-- The Modal -->
	 <div class="modal" id="fctySmryModal">
	   <div class="modal-dialog modal-lg">
	     <div class="modal-content">
	 
	       <!-- Modal Header -->
	       <div class="modal-header">
	         <h6 class="modal-title" style="font-size:13px;font-weight:500;" id="headSMARTList"></h6>
	       </div>
	 
	       <!-- Modal body -->
	       <div class="modal-body">
	         <div id="divFctyList" style="width:100%;height:300px;overflow:scroll;"></div>
	       </div>
	 
	       <!-- Modal footer -->
	       <div class="modal-footer">
	         <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><%=E.EH("Close")%></button>
	       </div>
	 
	     </div>
	   </div>
	 </div>
	 </div>
	</div>
         
<%@ include file="/pages/GG_Footer.jspf" %>
