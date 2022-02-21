<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"DISCLAIMER","/RailSAHAY/pages/Disclaim.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("DISCLAIMER")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	<div class="card">
	  <div class="card-body">
	    <ul class="list-group list-group-flush">
	      <li class="list-group-item">
	      	<p class="card-text"><%=E.EH("This Portal has been designed, developed and is being maintained by")%> <strong><%=E.EH("Centre for Railway Information Systems (CRIS), Ministry of Railways, Government of India")%>.</strong></p>
	      </li>
	      <li class="list-group-item">
	    	<p class="card-text"><%=E.EH("The contents of this website are for information purposes only, enabling the public at large to have a quick and an easy access to information and do no have any legal sanctity. Every effort has been made to provide accurate and updated information")%>.</p>
	      </li>
	      <li class="list-group-item">
	      	<p class="card-text"><%=E.EH("The links are provided to other external sites in some web pages/documents. We do not take responsibility for the accuracy of the contents in those sites. The hyperlink given to external sites do not constitute an endorsement of information, products or services offered by these sites")%>.</p>
	      </li>
	      <li class="list-group-item">
	      <p class="card-text"><%=E.EH("Despite our best efforts, we do not guarantee that the documents in this site are free from infection by computer viruses etc")%>.</p>
	      </li>
	      <li class="list-group-item">
	      <p class="card-text"><%=E.EH("We welcome your suggestions to improve this website and request that error(if any) may kindly be brought to our notice")%>.</p>
	      </li>
	</ul>
	</div>
	</div>
	</div>
	</div>
	    	
	       <%@ include file="/pages/GG_Footer.jspf" %>
