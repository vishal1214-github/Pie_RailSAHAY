<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RELATED LINKS","/RailSAHAY/pages/RelatedLinks.jsp",UserDevice,Browser);
%>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RELATED LINKS")%></h3>
              </div>
            </div>
           </div>
          </div>
	<div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">		
	  <h3 class="font-weight-light text-center text-lg-left mt-4 mb-0"><%=E.EH("Other Important Links")%>: </h3><br/>
	  <ul class="list-group list-group-flush">
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Centre for Railway Information Systems")%> (<%=E.EH("CRIS")%>)&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('CRIS','http://www.cris.org.in');"><%=E.EH("Visit Now")%>..</a></p>
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Indian Railways")%>&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('INDIAN RAILWAYS','http://www.indianrail.gov.in');"><%=E.EH("Visit Now")%>..</a></p>
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Rates Branch System (RBS)")%>&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('RATES BRANCH SYSTEM','http://www.rbs.indianrail.gov.in/ShortPath/');"><%=E.EH("Visit Now")%>..</a></p>
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Freight Rate Circulars")%>&nbsp;&nbsp;	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('RATE CIRCULARS','http://www.indianrailways.gov.in/railwayboard/view_section.jsp?lang=0&id=0,1,304,366,555,765');"><%=E.EH("Visit Now")%>..</a></p>	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Railway Claims Tribunal (RCT)")%>&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('CLAIMS TRIBUNAL','	http://www.rct.indianrail.gov.in/');"><%=E.EH("Visit Now")%>..</a></p>	  
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Goods and Services Tax (GST)")%>&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('GST','http://www.cbec.gov.in/htdocs-cbec/gst/index');"><%=E.EH("Visit Now")%>..</a></p>	  
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item"><%=E.EH("Centralized Public Grievance Redress and Monitoring System")%> (CPGRAMS)&nbsp;&nbsp;<a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('CPGRAMS','	https://pgportal.gov.in/');"><%=E.EH("Visit Now")%>..</a></p>	  
	  <p class="light-desc">(<%=E.EH("External website that opens in a new window")%>)</p>
	  </li>
	</ul>
	</div>
      </div>
      </div>

      <%@ include file="/pages/GG_Footer.jspf" %>
