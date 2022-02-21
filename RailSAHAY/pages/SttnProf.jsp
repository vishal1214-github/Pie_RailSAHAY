<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"STATION PROFILE","/RailSAHAY/pages/SttnProf.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12 ml-1">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">STATION PROFILE</h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr><td><input class="form-control mr-sm-2 inptcap" id="txtSttn" name="txtSttn" type="text" list="locnlist" placeholder="Station">
	          <datalist id="locnlist"></datalist>
			 </td><td><button class="btn btn-success" onclick="event.preventDefault();getData();">Search</button></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
	<div class="col-lg-12">
		<div id="divTrmlProf" class="trmllistdata"></div>
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
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
 <script>
            var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("locnlist");
            my_list.innerHTML = str;
            function getData()
            {
	            var sttncode='';
	            var sttndesc=$("#txtSttn").val();
				if(sttndesc.indexOf("-")>-1)
				{
					var sttn=sttndesc.substring(sttndesc.lastIndexOf("-")+1);
					sttncode=(sttn.substring(0,sttn.lastIndexOf("("))).trim();
				}	
				else
				{
					sttncode=sttndesc.toUpperCase();
				}
				fetchTrmlProf(sttncode);
            }            
</script>

	       <%@ include file="/pages/GG_Footer.jspf" %>