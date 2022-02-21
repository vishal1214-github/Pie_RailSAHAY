<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"COPYRIGHT POLICY","/RailSAHAY/pages/Copyright.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("COPYRIGHT POLICY")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row" style="min-height:70vh;">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	<div class="card" >
	  <div class="card-body">
	    <ul class="list-group list-group-flush">
	      <li class="list-group-item">
	      	<p class="card-text"><%=E.EH("The contents of this portal may not be reproduced partially or fully, without due permission from Centre for Railway Information Systems, Ministry of Railways, Govt. of India. If referred to as a part of another publication, the source must be appropriately acknowledged. The contents of this website can not be used in any misleading or objectionable context")%>.</p>	      	
	      </li>
	</ul>
	</div>
	</div>
	</div>
	</div>    	
 <%@ include file="/pages/GG_Footer.jspf" %>
