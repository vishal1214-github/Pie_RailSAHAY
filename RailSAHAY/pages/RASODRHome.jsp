<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"INDENTS OUTSTANDING AND RAKE ALLOTMENTS","/RailSAHAY/pages/RASODRHome.jsp",UserDevice,Browser);
%>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<link href="/RailSAHAY/resources/css/rasrprt.css" rel="stylesheet" />

<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("INDENTS OUTSTANDING AND RAKE ALLOTMENTS")%></h3>
              </div>
            </div>
           </div>
          </div>

<div class="container-fullwidth">
  <div class="row header-image">
<div class="col-sm-12" align="center">
	<div class="image-wrapper">
		<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
		<h2 class="header-overlay-h2"><span class="statsfig wgontypefig" id="statwgontype"></span>&nbsp;<%=E.EH("We provide absolute transparency in allocations and allotment of Rakes through our Rake Allocation System (RAS)! Checkout the status through various queries")%></h2>
		<img src="/RailSAHAY/resources/images/RASIcon.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="header-image" alt="">
	</div>					
</div>	
</div></div>
<div class="container animate-bottom" id="divCmdtFeature">
<h2 class="heading-title"><%=E.EH("Let's help you make an optimized plan")%></h2>

<div class="row mt-3">
   <div class="col-lg-12 col-sm-12">
        <div class="container">
		<div class="row row-right">
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/RASCoalIcon.jpg" class="img-responsive img-fluid img-optn" />
			</div>
			<div class="col-lg-6 col-sm-12">
				<p class="pCmdtHeadLbl"><%=E.EH("RAS Coal")%></p>
				<hr/>
				<p class="pCmdtDesc"><%=E.EH("Rake Allocation & Allotment System for Coal Demands")%></p>
				<ul class="list-group list-group-flush">
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('COAL_ALOT');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Coal Allotments")%></a></li>
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('COAL_ALOT_ODR');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Arrear of Allotments (ODR-Wise)")%></a></li>
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('COAL_ALOT_30');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Arrear of Allotments (More than 30 Days)")%></a></li>
				</ul>
			</div>			
		</div>
		<div class="row row-left">
			<div class="col-lg-6 col-sm-12">
				<p class="pCmdtHeadLbl"><%=E.EH("RAS Iron Ore")%></p>
				<hr/>
				<p class="pCmdtDesc"><%=E.EH("Rake Allocation & Allotment System for Iron Ore Demands")%></p>
				<ul class="list-group list-group-flush">
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('IORE_INDT');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Iron Ore Indents")%></a></li>
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('STTN_OTSG_DMND');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Station-wise Outstanding Demands")%></a></li>
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('RAKE_ALOT');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Rake Allotments")%></a></li>
				</ul>
			</div>
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/RASIOREIcon.jpg" class="img-responsive img-fluid img-optn" />
			</div>			
		</div>
		<div class="row row-right">
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/ODRIcon.jpg" class="img-responsive img-fluid img-optn" />
			</div>
			<div class="col-lg-6 col-sm-12">
				<p class="pCmdtHeadLbl"><%=E.EH("Indents Status")%></p>
				<hr/>				
				<p class="pCmdtDesc"><%=E.EH("Pendency of Indents across Indian Railways Freight Terminals")%></p>
				<ul class="list-group list-group-flush">
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('ODR_INDT');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("ODR-Wise Outstanding Indents")%></a></li>
				<li class="list-group-item"><a href="javascript:void(0);" onclick="showPage('MATURED_INDT');" class="ras-link"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Matured Indents")%></a></li>
				</ul>
			</div>			
		</div>
		<p class="mesg-cnt"><%=E.EH("For any query regarding rake allocation or indents pendency")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p>
	</div>
   </div>
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
<%@ include file="/pages/GG_Footer.jspf" %>
