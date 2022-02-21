<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Commodities: TWO POINT COMBINATIONS","/RailSAHAY/pages/CmdtTwoPtCombi.jsp",UserDevice,Browser);
String strBrochureFlag=((String)HtParamCntl.get("CMDT_BROCHURE")).trim();
if(strBrochureFlag==null)
	strBrochureFlag="N";
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

<div class="container-fullwidth">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb"  style="background:#efefef;padding-left:10px;font-weight:600;">
    <li class="breadcrumb-item" style="font-weight:600;"><a href="javascript:void(0)" onclick="showIndustry('<%=GG_MainCmdtCode%>');"><%=GG_MainCmdtName%> (<%=GG_SubCmdtName%>)</a></li>
    <li class="breadcrumb-item active" style="font-weight:600;" aria-current="page"><%=E.EH("Incentive Schemes")%></li>
  </ol>
</nav>	
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("TWO-POINT/MULTI-POINT SERVICES")%></h3>
		<h4 class="mb-4 subcmdthead" data-aos="fade-up" data-aos-delay="100" id="hSubCmdtHead"><%=GG_SubCmdtName%></h4>
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
          <input class="form-control mr-sm-2 inptcap" id="txtCmdt" value="<%=GG_SubCmdtName%>" name="txtCmdt" type="hidden">	
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
			  <option value="IR">All IR</option>
			</select>
		</td>
		<td>
			<input class="form-control mr-sm-2 inptcap" id="txtLocn" list="locnlist"  name="txtLocn" type="text" placeholder='<%=E.EH("Location")%>'>
			<datalist id="locnlist"></datalist>
		</td>
		<td>
			<button class="btn btn-data" onclick="event.preventDefault();fetchCombi();"><%=E.EH("Search")%></button></td>
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

<% if(strBrochureFlag.equals("Y")) { %>
<hr/>
<div class="container">
<div class="card-brochure" onclick="downloadMainCmdtPDF();">
		<p class="brochure-head">Brochure: <%=GG_MainCmdtName%></p>
		<p class="brochure-dn">Download PDF | 2MB</p>
</div> 
<% if(!GG_SubCmdtName.equals("")) { %>
	<div class="card-brochure" onclick="downloadSubCmdtPDF();">
		<p class="brochure-head">Brochure: <%=GG_SubCmdtName%></p>
		<p class="brochure-dn">Download PDF | 2MB</p>
	</div>

<form id="frmSubBrochure" name="frmSubBrochure" action="/RailSAHAY/CommodityPDF" method="post" target="SubCmdtBrochure">
	<input type="hidden" id="MainCmdtCode" name="MainCmdtCode" value="<%=GG_MainCmdtCode%>" />
	 <input type="hidden" id="MainCmdtName" name="MainCmdtName" value="<%=GG_MainCmdtName%>" />
	 <input type="hidden" id="SubCmdtCode" name="SubCmdtCode" value="<%=GG_SubCmdtCode%>" />
        <input type="hidden" id="SubCmdtName" name="SubCmdtName"  value="<%=GG_SubCmdtName%>" />
</form>
<% } %>
</div>
<form id="frmMainBrochure" name="frmMainBrochure" action="/RailSAHAY/CommodityPDF" method="post" target="MainCmdtBrochure">
	<input type="hidden" id="MainCmdtCode" name="MainCmdtCode" value="<%=GG_MainCmdtCode%>" />
	 <input type="hidden" id="MainCmdtName" name="MainCmdtName" value="<%=GG_MainCmdtName%>" />
	 <input type="hidden" id="SubCmdtCode" name="SubCmdtCode" value="" />
        <input type="hidden" id="SubCmdtName" name="SubCmdtName"  value="" />
</form>
<% } %>

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
<script src="/RailSAHAY/resources/js/cmdttwoptcombi.js"></script>
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
