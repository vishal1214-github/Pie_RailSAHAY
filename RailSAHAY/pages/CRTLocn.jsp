<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"CRT LOCATIONS","/RailSAHAY/pages/CRTLocn.jsp",UserDevice,Browser);
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
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("LIST OF CONTAINER RAIL TERMINALS (CRT)")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-2 col-md-3 col-sm-12">
	    <!-- Links -->
	    <ul class="navbar-nav">
	      <!-- Dropdown -->
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle locnflag dropdown-toggle-split" locn="" href="#" id="navbardrop" data-toggle="dropdown">
	          <%=E.EH("Location Type")%>
	        </a>
	        <div class="dropdown-menu">
	          <a class="dropdown-item text-right" href="#" value="NM"><%=E.EH("Near Me")%></a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item text-right" href="#" value="PC"><%=E.EH("Pin Code")%></a>
	          <a class="dropdown-item text-right" href="#" value="S"><%=E.EH("State")%></a>
	          <a class="dropdown-item text-right" href="#" value="DD"><%=E.EH("District")%></a>
	          <a class="dropdown-item text-right" href="#" value="Z"><%=E.EH("Zone")%></a>
	          <a class="dropdown-item text-right" href="#" value="D"><%=E.EH("Division")%></a>
	        </div>
	      </li>
	    </ul>
	    </div>
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr><td><input class="form-control mr-sm-2 inptcap" type="text" id="txtLocn" name="txtLocn" list="locnlist" placeholder='<%=E.EH("Location")%>'>
		  <datalist id="locnlist"></datalist>
		</td><td><button class="btn btn-data" onclick="event.preventDefault();mapshow();"><%=E.EH("Search")%></button>&nbsp;&nbsp;&nbsp;<span id="mapIm" class="float-right"> <img src="/RailSAHAY/resources/images/crntflag.png" onclick="initMapL(); " data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor='pointer';" alt="View on Map" title="View on Map"  ></span></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
          <div class="col-lg-12 ml-1 mr-1">
	<div class="mt-4 text-center small" id="divColrInd" align="right">
                    <span class="mr-1">
                      <svg class="svg-inline--fa fa-circle fa-w-16 text-primary-orig" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-primary"></i> --> Aggregator
                    </span>
                    <span class="mr-1">
                      <svg class="svg-inline--fa fa-circle fa-w-16 text-success" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-success"></i> --> Trucker
                    </span>
                    <span class="mr-1">
                      <svg class="svg-inline--fa fa-circle fa-w-16 text-danger" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-danger"></i> --> Warehouse
                    </span>
                    <span class="mr-1">
                      <svg class="svg-inline--fa fa-circle fa-w-16 text-warning" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-warning"></i> --> Labour
                    </span>
                  </div>
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
          <h5 class="modal-title"><span id="mapHeader" class="mapheader"></span></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSpecLocn" style="width:100%;height:70vh;z-index:10;position:fixed;top:0;left:0;"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  <script>
 function maphide(){
                 $("#mapIm").hide();
            }
            function mapshow(){
            
            fetchLocnList('CRT');
	}
  </script>

<%@ include file="/pages/GG_Footer.jspf" %>
