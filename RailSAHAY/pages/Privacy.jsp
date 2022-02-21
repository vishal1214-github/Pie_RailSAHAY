<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"PRIVACY POLICY","/RailSAHAY/pages/Privacy.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"> <%=E.EH("PRIVACY POLICY")%></h3>
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
	      	<p class="card-text"><%=E.EH("As a general rule, this website does not collect Personal Information about you when you visit the site. You can generally visit the site without revealing Personal Information, unless you choose to provide such information")%>.</p>	      	
		  <div class="card bg-light text-dark">
		     <div class="card-header"><%=E.EH("Site Visit Data")%></div>
		     <div class="card-body"><p class="card-text"><%=E.EH("This portal records your visit and logs the following information for statistical purposes your server's address, the name of the top-level domain from which you access the Internet (for example, .gov, .com, .in, etc.), the type of browser you use, the date and time you access the site, the pages you have accessed and the documents downloaded and the previous Internet address from which you linked directly to the site. We will not identify users or their browsing activities, except when a law enforcement agency may exercise a warrant to inspect the service provider's logs")%>.</p></div>
  		  </div> 
  		  <div class="card bg-light text-dark">
		     <div class="card-header"><%=E.EH("Cookies")%></div>
		     <div class="card-body"><p class="card-text"><%=E.EH("A cookie is a piece of software code that an internet web site sends to your browser when you access information at that site. This portal does not use cookies")%>.</p></div>
  		  </div>
  		  <div class="card bg-light text-dark">
		     <div class="card-header"><%=E.EH("Email Management")%></div>
		     <div class="card-body"><p class="card-text"><%=E.EH("Your email address will only be recorded if you choose to send a message. It will only be used for the purpose for which you have provided it and will not be added to a mailing list. Your email address will not be used for any other purpose, and will not be disclosed, without your consent")%>.</p></div>
  		  </div>
  		  <div class="card bg-light text-dark">
		     <div class="card-header"><%=E.EH("Collection of Personal Information")%></div>
		     <div class="card-body"><p class="card-text"><%=E.EH("If you are asked for any other Personal Information you will be informed how it will be used if you choose to give it. If at any time you believe the principles referred to in this privacy statement have not been followed, or have any other comments on these principles, please notify the webmaster through the contact us page")%>.</p></div>
  		  </div>
  		  <div class="card bg-light text-dark">
		     <div class="card-header"><%=E.EH("Note")%></div>
		     <div class="card-body"><p class="card-text"><%=E.EH("The use of the term Personal Information in this privacy statement refers to any information from which your identity is apparent or can be reasonably ascertained")%>.</p></div>
  		  </div>
	      </li>
	</ul>
	</div>
	</div>
	</div>
	</div>	    	
 <%@ include file="/pages/GG_Footer.jspf" %>
