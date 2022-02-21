<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TIME TABLED ROUTES","/RailSAHAY/pages/TimeTblTran.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<link rel="stylesheet"  href="/RailSAHAY/resources/css/prcldata.css">

<script>
	$(document).ready(function(){
	$("#divTTSChdDtls").hide();
	fetchTimeTblRout('','');
	});
</script>	

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12 ml-1">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("TIME TABLED ROUTES")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr>
	          	<td><input class="form-control mr-sm-2 inptcap" id="txtSttnFrom" name="txtSttnFrom" type="text" list="locnlistfrom" placeholder='<%=E.EH("From")%>'>
			          <datalist id="locnlistfrom"></datalist>
			</td>
			<td><input class="form-control mr-sm-2 inptcap" id="txtSttnTo" name="txtSttnTo" type="text" list="locnlistto" placeholder='<%=E.EH("To")%>'>
				  <datalist id="locnlistto"></datalist>
			</td>
			<td><button class="btn btn-data" onclick="event.preventDefault();getData();"><%=E.EH("Check")%></button></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
	<div class="col-lg-6 col-sm-12">
		<div id="divTTRout" class="trmllistdata"></div>
      </div>
	<div class="col-lg-6 col-sm-12" id="divTTSChdDtls">
		<div class="optncard">
			<ul class="nav nav-pills">
			    <li class="nav-item">
			        <a class="nav-link active" data-toggle="tab" href="#Schd"><%=E.EH("Schedule")%></a>
			    </li>
			    <li class="nav-item">
			        <a class="nav-link" data-toggle="tab" href="#Mvmt"><%=E.EH("Current Train Movements")%></a>
			    </li>
			</ul>
		</div>
		<div class="optncard">
			<div class="tab-content">
				  <div class="tab-pane container active" id="Schd">
					<div id="divTTSchd" class="trmllistdata mr-2 timeline-centered"></div>
				  </div>
				  <div class="tab-pane container fade" id="Mvmt">
					<div id="divTTMvmt" class="trmllistdata mr-2"></div>
				  </div>
			</div>
		</div>
      </div>
      </div>
        <script>
            var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("locnlistfrom");
            my_list.innerHTML = str;
            var my_list1=document.getElementById("locnlistto");
            my_list1.innerHTML = str;
            function getData()
            {
	            var sttnfrom='';
	            var sttnto='';
	            var sttnfromdesc=$("#txtSttnFrom").val();
	            var sttntodesc=$("#txtSttnTo").val();
	            if(sttnfromdesc!="")
	            {
			if(sttnfromdesc.indexOf("-")>-1)
			{
				var sttn=sttnfromdesc.substring(sttnfromdesc.indexOf("-")+1);
				sttnfrom=(sttn.substring(0,sttn.indexOf("("))).trim();
			}	
			else
			{
				sttnfrom=sttnfromdesc.toUpperCase();
			}
		    }
	            if(sttntodesc!="")
	            {
			if(sttntodesc.indexOf("-")>-1)
			{
				var sttn=sttntodesc.substring(sttntodesc.indexOf("-")+1);
				sttnto=(sttn.substring(0,sttn.indexOf("("))).trim();
			}	
			else
			{
				sttnto=sttntodesc.toUpperCase();
			}
		    }
		   fetchTimeTblRout(sttnfrom, sttnto);
            }            
		</script>

<%@ include file="/pages/GG_Footer.jspf" %>
