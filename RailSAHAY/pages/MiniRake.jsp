<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"COAL","/RailSAHAY/pages/MiniRake.jsp",UserDevice,Browser);
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/miniraketimeline.css">
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("MINI RAKE SERVICES")%></h3>
              </div>
            </div>
           </div>
          </div>
          
          <div class="container">
		<div class="row">
		<div class="col-lg-10 offset-lg-1 col-sm-12">
		<%--<img src="/RailSAHAY/resources/images/agri1.jpg" class="img-fluid img-responsive" style="height:200px;">--%>
		<h4 style="color:#000;">Features</h4>
		<ul>
			<li><%=E.EH("Facilitating lesser freight (Train Load Rate) for a composition of 20 wagons (covered stock and for minimum 1260 tonne.)")%></li>
			<li><%=E.EH("Mini Rake services are applicable for any distance")%></li>
			<li><%=E.EH("Service is not available for loading of <strong>Coal, Ores and Raw Material for Steel Plant</strong>")%></li>
		</ul>
		<p class="mesg-cnt"><%=E.EH("For Rate Circulars on Transportation Products (Mini Rake Services)")%> <a href="javascript:void(0)" onclick="openExtnLink('Indian Railways Commercial','https://indianrailways.gov.in/railwayboard/view_section.jsp?lang=0&id=0,1,304,366,555,1430,1589');" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Click Here")%></a></p>

		<p style="color:#000;font-weight:600;margin-bottom:1rem;">Rate Slabs for Mini Rake Services</p>
		</div>
		</div>
	              <div class="page1">
		        <div class="timeline">
		          <div class="timeline__group">
		            <span class="timeline__year time" aria-hidden="true">0 Kms</span>
		            <div class="timeline__cards">
		              <div class="timeline__card card">
		                <header class="card__header">
		                  <time class="time" datetime="2008-02-02">
		                    <span class="time__day"><%=E.EH("Slab")%></span>
		                    <span class="time__month">1 (Upto 1500 Kms)</span>
		                  </time>
		                </header>
		                <div class="card__content">
		                  <p><ul class='list-group list-group-flush'><li class='list-group-item li-strong'><a href="javascript:void(0)" onclick='showPage("RATE_SLAB");'><%=E.EH("Train Load Class Base Freight (Click to check the Base Freight)")%></a></li></ul></p>
		                </div>
		              </div>
		            </div>
		          </div>
		          <div class="timeline__group">
		            <span class="timeline__year time" aria-hidden="true">1500 Kms</span>
		            <div class="timeline__cards">
		              <div class="timeline__card card">
		                <header class="card__header">
		                  <time class="time" datetime="2008-07-14">
		                    <span class="time__day"><%=E.EH("Slab")%></span>
		                    <span class="time__month">2 (1500 To 2000 Kms)</span>
		                  </time>
		                </header>
		                <div class="card__content">
		                  <p><ul class='list-group list-group-flush'><li class='list-group-item li-strong'><a href="javascript:void(0)" onclick='showPage("RATE_SLAB");'><%=E.EH("Train Load Class Base Freight for Total Distance (Click to check the Base Freight)")%></a></li>
				     <li class='list-group-item'><i class='fas fa-plus'></i>&nbsp;<%=E.EH("Supplementary Charge @ 7.5% (on difference of base freight of respective distance and base freight of 1500 km)")%></li>
				</ul></p>
		                </div>
		              </div>
		            </div>
		          </div>
		          <div class="timeline__group">
		            <span class="timeline__year time" aria-hidden="true">2000 Kms</span>
		            <div class="timeline__cards">
		              <div class="timeline__card card">
		                <header class="card__header">
		                  <time class="time" datetime="2008-08-18">
		                    <span class="time__day"><%=E.EH("Slab")%></span>
		                    <span class="time__month">3 (Beyond 2000 Kms)</span>
		                  </time>          
		                </header>
		                <div class="card__content">
		                  <p><ul class='list-group list-group-flush'><li class='list-group-item li-strong'>
				     <a href="javascript:void(0)" onclick='showPage("RATE_SLAB");'><%=E.EH("Train Load Class Base Freight for Total Distance (Click to check the Base Freight)")%></a></li>
				     <li class='list-group-item'><i class='fas fa-plus'></i>&nbsp;<%=E.EH("Supplementary Charge @ 7.5% (on difference of base freight of 2000 km. and base freight of 1500 km)")%></li>
				     <li class='list-group-item'><i class='fas fa-plus'></i>&nbsp;<%=E.EH("Supplementary Charge @ 10% (on difference of freight of distance beyond 2000 km and base freight of 2000 kms)")%></li>
				    </ul></p>
		                </div>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
          <p class="mesg-cnt"><%=E.EH("For any assistance regarding Mini Rake Services")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p>

<%@ include file="/pages/GG_Footer.jspf" %>

            