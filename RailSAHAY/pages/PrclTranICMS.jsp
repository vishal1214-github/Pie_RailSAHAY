<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Parcel Trains","/RailSAHAY/pages/PrclTranICMS.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDateTo=dateFormat.format(date);
String strDateFrom=strDateTo.substring(0,8)+"01";
%>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/aLocn.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/asttn.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/app_integration.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/prcltranlist.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/prcltranlist.css"></script>


<style>
.col-lg-1, .col-lg-2
{
	padding-left:5px;
	padding-right:5px;
}
</style>
<nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:1px;"></nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1">
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("PARCEL TRAINS MOVEMENT")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light" style="padding-left:1rem;padding-right:1rem;">
          <div class="row">
	  <div class="col-lg-1 col-md-2 col-sm-12 col-inpt">
        	<select id="fromFlag" class="text-dark bg-white inpttext" style="width:100%;padding-left:3px;height:40px;">
        		<option value="">Source</option>
        		<option value="ST">State</option>
        		<option value="DS">District</option>
        		<option value="Z">Zone</option>
        		<option value="D">Division</option>
        		<option value="S">Station</option>
        	</select>
	  </div>  
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
	  <input class="form-control mr-sm-2 inptcap" id="txtLocnFrom" name="txtLocnFrom" type="text" list="locnlistfrom" placeholder='<%=E.EH("Source")%>'>
		<datalist id="locnlistfrom"></datalist>
	  </div>
	  <div class="col-lg-1 col-md-1 col-sm-12 col-inpt">
        	<select id="crntFlag" class="text-dark bg-white inpttext" style="width:100%;padding-left:3px;height:40px;">
        		<option value="">Current</option>
        		<option value="ST">State</option>
        		<option value="DS">District</option>
        		<option value="Z">Zone</option>
        		<option value="D">Division</option>
        		<option value="S">Station</option>
        	</select>
	  </div>
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
	  <input class="form-control mr-sm-2 inptcap" id="txtCrntLocn" name="txtCrntLocn" type="text" list="crntlocnlist" placeholder='<%=E.EH("Current Location")%>'>
		<datalist id="crntlocnlist"></datalist>
	  </div>	  
	  <div class="col-lg-1 col-md-1 col-sm-12 col-inpt">
        	<select id="toFlag" class="text-dark bg-white inpttext" style="width:100%;padding-left:3px;height:40px;">
        		<option value="">Destination</option>
        		<option value="ST">State</option>
        		<option value="DS">District</option>
        		<option value="Z">Zone</option>
        		<option value="D">Division</option>
        		<option value="S">Station</option>
        	</select>
	  </div>
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
	  <input class="form-control mr-sm-2 inptcap" id="txtLocnTo" name="txtLocnTo" type="text" list="locnlistto" placeholder='<%=E.EH("Destination")%>'>
		<datalist id="locnlistto"></datalist>
	  </div>
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
	  <input class="form-control mr-sm-2 inptcap" id="txtTranName" name="txtTranName" type="text" list="locntranlist" placeholder='<%=E.EH("Train Name")%>'>
		<datalist id="locntranlist"></datalist>
	  </div>
	  <div class="col-lg-1 col-md-2 col-sm-12 col-inpt">
		<input type="hidden" id="txtCmdt" value="COAL" />
		<button class="btn btn-data float-left" onclick="event.preventDefault();fetchICMSPrclTrans();"><%=E.EH("Go")%></button>
	  </div>
	    </div>
	</nav>
</row>
          <div class="row">
          <div class="col-lg-4 col-sm-12" style="padding-right:1px;">
		<div id="divPrclTranList" class="trmllistdata" style="padding:5px;"></div>
      </div>
          <div class="col-lg-8 col-sm-12" style="padding-left:1px;">
		<div id="divPrclTranPlot" class="trmllistdata" style="padding:2px;"></div>
      </div>
<div class="modal fade" id="tranRoutModal" tabindex="-1" role="dialog" aria-labelledby="tranRoutModal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
	<table style="width:100%;"><tr><td><p id="pPrclHedr"></p></td><td><button type="button" class="btn btn-light btn-sm float-right" data-dismiss="modal">&times;</button></td></tr></table>
      </div>
      <div class="modal-body">
        <div id="divTranRout" style="height:70vh;overflow-y:scroll;"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
      </div>
<%@ include file="/pages/GG_Footer.jspf" %>
