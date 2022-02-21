<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TWO POINT COMBINATIONS","/RailSAHAY/pages/TwoPtCombi.jsp",UserDevice,Browser);
%>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMapL&libraries=geometry&sensor=false" type="text/javascript"></script>
<script src="/RailSAHAY/resources/js/aPinCode.js"></script>
<script src="/RailSAHAY/resources/js/asttn.js"></script>
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aRakeType.js"></script>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<link href="/RailSAHAY/resources/css/twoptcombi.css" rel="stylesheet" />
<script>
	function initMapL()
	{
		//do nothing
	}
</script>


	 <nav class="navbar navbar-expand-sm bg-origprimary navbar-light"></nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("TWO-POINT/MULTI-POINT SERVICES")%></h3>
              </div>
            </div>
           </div>
          </div>

<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
					<div class="image-wrapper">
						<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
						<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;2400+ Unique combinations to make it most flexible and economical freight transportation mode</h2>
						<img src="/RailSAHAY/resources/images/twoptcombi.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
					</div>					
			</div>	
</div></div> 
<div class="container">
<h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("Explore our range of combinations for your needs")%></h3>
	
	  <hr class="mt-2 mb-2">
	
          <nav class="navbar navbar-expand-sm bg-light navbar-light mt-2">
	  <div class="col-lg-2 col-md-2 col-sm-12">
		<select name="selOWIW" class="sellist" id="selOWIW">
			  <option value="I">For Unloading</option>
			  <option value="O">For Loading</option>
			</select>
	 </div>
	  <div class="col-lg-2 col-md-3 col-sm-12">
          <input class="form-control mr-sm-2 inptcap" id="txtCmdt" list="cmdtlist"  name="txtCmdt" type="text" placeholder='<%=E.EH("Commodity")%>'>	
	  <datalist id="cmdtlist"></datalist>
	</div>
	  <div class="col-lg-2 col-md-3 col-sm-12">
          <input class="form-control mr-sm-2 inptcap" id="txtRakeType" list="raketypelist"  name="txtRakeType" type="text" placeholder='<%=E.EH("Rake Type")%>'>	
	  <datalist id="raketypelist"></datalist>
	</div>
	  <div class="col-lg-6 col-md-4 col-sm-12">
		<table>
		<tr><td>
			<select name="selLocnType" class="sellist" id="selLocnType">
			  <option value="" selected>Location Type</option>
			  <option value="S">With Station</option>
			  <option value="PC">In Pincode</option>
			  <option value="DD">In District</option>
			  <option value="ST">In State</option>
			  <option value="D">In Division</option>
			  <option value="Z">In Zone</option>
			</select>
		</td>
		<td>
			<input class="form-control mr-sm-2 inptcap" id="txtLocn" list="locnlist"  name="txtLocn" type="text" placeholder='<%=E.EH("Location")%>'>
			<datalist id="locnlist"></datalist>
		</td>
		<td>
			<button class="btn btn-data" onclick="event.preventDefault();fetchCombi();"><%=E.EH("Search")%></button>		</td>
		</tr>
		</table>
	</div>

  	     </div>
	</nav>
          <div class="row">
          <div class="col-lg-12 ml-1">          
	  	<div class="mt-4 text-center small" id="divColrInd" align="right">
	                      <span class="mr-1">
	                        <i class="fas fa-circle text-danger"></i> <span id="spnSmry"></span>
	                      </span>
	         </div>
		<div id="divTrmlList" class="trmllistdata"></div>
		<div class="container">
		<p class="mesg-cnt"><%=E.EH("For Rate Circulars on Transportation Products (Two-Point Combinations)")%> <a href="javascript:void(0)" onclick="openExtnLink('Indian Railways Commercial','https://indianrailways.gov.in/railwayboard/view_section.jsp?lang=0&id=0,1,304,366,555,1430,1589');" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Click Here")%></a></p>
		</div>

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

<script src="/RailSAHAY/resources/js/twoptcombi.js"></script>
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
