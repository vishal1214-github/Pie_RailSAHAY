<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SCREEN READER","/RailSAHAY/pages/ScreenReader.jsp",UserDevice,Browser);
%>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">SCREEN READER</h3>
              </div>
            </div>
           </div>
          </div>
<div class="container">
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card">
	  <div class="card-header">
	    GIGW Compliance
	  </div>
	  <div class="card-body">
	    <p class="card-text">
	    Indian Railways Freight Business Portal complies with Guidelines for Indian Government Websites (GIGW) and World Wide Web Consortium (W3C) Web Content Accessibility Guidelines (WCAG) 2.0 level A. This will enable people with visual impairments access the website using technologies, such as screen readers. The information of the website is accessible with different screen readers, such as JAWS, NVDA, SAFA, Supernova and Window-Eyes.
	    </p>
	  </div>
	</div>
</div>
</div>
	<div class="row">
	<div class="col-lg-12 col-sm-12">		
	  <h3 class="font-weight-light text-center text-lg-left mt-4 mb-0">Available Screen Readers: </h3><br/>
		<ul class="list-group list-group-flush">
	  <li class="list-group-item">
	  <p class="head-list-item">Non Visual Desktop Access (NVDA)<span class="badge badge-green float-right">FREE</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('NVDA','http://www.nvda-project.org/');">http://www.nvda-project.org/</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">System Access To Go<span class="badge badge-green float-right">FREE</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('SYSTEM ACCESS TO GO','http://www.satogo.com/');">http://www.satogo.com/</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">Web Anywhere<span class="badge badge-green float-right">FREE</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('WEB ANYWHERE','http://webanywhere.cs.washington.edu/wa.php');">http://webanywhere.cs.washington.edu/wa.php</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">Hal<span class="badge badge-red float-right">COMMERCIAL</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('HAL','http://www.yourdolphin.co.uk/productdetail.asp?id=5');">http://www.yourdolphin.co.uk/productdetail.asp?id=5</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">JAWS<span class="badge badge-red float-right">COMMERCIAL</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('JAWS','http://www.freedomscientific.com/jaws-hq.asp');">http://www.freedomscientific.com/jaws-hq.asp</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">Supernova<span class="badge badge-red float-right">COMMERCIAL</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('SUPERNOVA','http://www.yourdolphin.co.uk/productdetail.asp?id=1');">http://www.yourdolphin.co.uk/productdetail.asp?id=1</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	  <li class="list-group-item">
	  <p class="head-list-item">Windows Eyes<span class="badge badge-red float-right">COMMERCIAL</span></p>
	  <a href="javascript:void(0)" class="card-link1" onclick="openExtnLink('WINDOWS EYES','http://www.gwmicro.com/Window-Eyes/');">http://www.gwmicro.com/Window-Eyes/</a>
	  <p class="light-desc">(External website that opens in a new window)</p>
	  </li>
	</ul>
	</div>
      </div>
      </div>

      <%@ include file="/pages/GG_Footer.jspf" %>
