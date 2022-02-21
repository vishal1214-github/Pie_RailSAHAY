<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"PAGES MATCHING YOUR SEARCH","/RailSAHAY/pages/SrchPageList.jsp",UserDevice,Browser);
String si_Word="";
try
	{
		si_Word=((String)request.getParameter("in")).trim();	
	}
	catch(Exception e)
	{
		si_Word="NONE";
	}

%>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>
<script>
$(document).ready(function() 
{
	fetchSrchPageList('<%=si_Word%>');
	$("#inptSearch").val('<%=si_Word%>');
});
</script>

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-2" data-aos="fade-up" data-aos-delay="100"><span class="match-cont mr-2"></span><%=E.EH("PAGES MATCHING YOUR SEARCH")%></h3>
		<p class="light-desc"><%=E.EH("Seached for")%>:&nbsp;<b><%=si_Word%></b></p>
              </div>
            </div>
           </div>
          </div>
	<div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">		
	  <h3 class="font-weight-light text-center text-lg-left mt-4 mb-0"></h3><br/>
	  <ul class="list-group list-group-flush" id="ulSrchPageList">
	  
	</ul>
	</div>
      </div>
      </div>
<input type="hidden" name="toPage1" id="toPage1" value="SrchPageList" />
      <%@ include file="/pages/GG_Footer.jspf" %>
