<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"LOADING LOCATIONS","/RailSAHAY/pages/LdngLocn.jsp",UserDevice,Browser);
%>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMapL&libraries=geometry&sensor=false" type="text/javascript"></script>
<script src="/RailSAHAY/resources/js/aPinCode.js"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script>
$(document).ready(function() {

//document.getElementById('mapIm').style.display="none";
$("#mapIm").hide();
});
</script>
<script>
	showPosition();
</script>
<script src="/RailSAHAY/resources/js/locnmapview.js"></script>

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("LIST OF LOADING LOCATIONS")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-2 col-md-3 col-sm-12">
          <input class="form-control mr-sm-2 inptcap" id="txtCmdt" list="cmdtlist"  name="txtCmdt" type="text" onchange="maphide()" placeholder='<%=E.EH("Commodity")%>'>	
	  <datalist id="cmdtlist"></datalist>
	</div>
	  <div class="col-lg-2 col-md-3 col-sm-12">
	    <!-- Links -->
	    <ul class="navbar-nav">

	      <!-- Dropdown -->
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle locnflag" locn="" href="#" id="navbardrop" data-toggle="dropdown">
	          <%=E.EH("Location Type")%>
	        </a>
	        <div class="dropdown-menu">
	          <a class="dropdown-item" href="#" value="NM"><%=E.EH("Near Me")%></a>
	          <div class="dropdown-divider"></div>
		  <a class="dropdown-item" href="#" value="PC"><%=E.EH("Pin Code")%></a>
	          <a class="dropdown-item" href="#" value="S"><%=E.EH("State")%></a>
	          <a class="dropdown-item" href="#" value="DD"><%=E.EH("District")%></a>
	          <a class="dropdown-item" href="#" value="Z"><%=E.EH("Zone")%></a>
	          <a class="dropdown-item" href="#" value="D"><%=E.EH("Division")%></a>
	        </div>
	      </li>
	    </ul>
	    </div>
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr><td><input class="form-control mr-sm-2 inptcap" id="txtLocn" name="txtLocn" list="locnlist" type="text" placeholder='<%=E.EH("Search")%>'>
		  <datalist id="locnlist"></datalist>
		</td><td><button class="btn btn-data" onclick="event.preventDefault();fetchLocnList('LDNG');mapshow();"><%=E.EH("Search")%></button>&nbsp;&nbsp;&nbsp;<span id="mapIm" class="float-right"> <img src="/RailSAHAY/resources/images/crntflag.png" onclick="initMapL(); " data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor='pointer';" alt=<%=E.EH("AA")%>"View on Map" title=<%=E.EH("AA")%>"View on Map"  ></span></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
          <div class="col-lg-12 ml-1">
		<div id="divTrmlList" class="trmllistdata"></div>
      </div>
      </div>

 <div class="modal fade" id="fctyModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="fctytrml"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
		<div id="divTrmlFcty" style="width:100%;height:400px;overflow-y:scroll;">
		</div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>
  <div class="modal fade" id="mapModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="mapHdr"><span id="mapHeader" class="mapheader"></span></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSpecLocn" style="width:100%;height:70vh;z-index:10;position:fixed;top:0;left:0;"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>
  
<script>		
   var cmdtstr='';
   for (var i=0; i < aRakeCmdt.length;++i){
        cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for station
   }
   var my_list=document.getElementById("cmdtlist");
   my_list.innerHTML = cmdtstr;
</script>
<script>
 function maphide(){
               // alert("lat: "+latLngList[0][0]+", lng:"+latLngList[1][0]+"lat1: "+latList[0]+", lng1:"+lngist[0]+", length:"+lngist.length);
                 $("#mapIm").hide();
            }
            function mapshow(){
               // alert("lat: "+latLngList[0][0]+", lng:"+latLngList[1][0]+"lat1: "+latList[0]+", lng1:"+lngist[0]+", length:"+lngist.length);
            }
  </script>
<%@ include file="/pages/GG_Footer.jspf" %>
