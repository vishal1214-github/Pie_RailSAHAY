<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"PARCEL TRAIN MOVEMENT","/RailSAHAY/pages/PrclTranMvmt.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDate1=dateFormat.format(date);
%>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script src="/RailSAHAY/resources/js/prcldata.js"></script>
<link rel="stylesheet"  href="/RailSAHAY/resources/css/prcldata.css">

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("PARCEL TRAINS MOVEMENT")%></h3>
              </div>
            </div>
           </div>
          </div>
	<div class="row">
	<div class="col-lg-4 col-sm-12">
	 <form role="form-inline"  style="margin:10px;">
	         <div class="card border border-info mb-3">
	             <div class="card-header"><%=E.EH("Please Enter Detail")%></div>
	             <div class="card-body text-primary">	         
	 		<div class="panel panel-primary">
	 		<div class="form-card">
	                 <div class="p-1">
	                      <select class="btn btn-light" style="width:100%;" id="selLocnFrom" name="selLocnFrom">
				   <option value=""><%=E.EH("Origin Type")%></option>
				   <option selected  value="S"><%=E.EH("Station")%></option>
				   <option value="D"><%=E.EH("Division")%></option>
				   <option value="Z"><%=E.EH("Zone")%></option>
			      </select>                               
	                 </div> 
	                 <div class="p-1">
	                     <input type="text" list="locnfromlist" class="form-control inptcap" id="txtLocnFrom"    name="txtLocnFrom" required placeholder='<%=E.EH("Origin")%>' required />
	                     <datalist id="locnfromlist"></datalist>                                    
	                 </div> 
	                 <div class="p-1">
	                      <select class="btn btn-light" style="width:100%;" id="selLocnTo" name="selLocnTo">
				   <option value=""><%=E.EH("Destination Type")%></option>
				   <option selected  value="S"><%=E.EH("Station")%></option>
				   <option value="D"><%=E.EH("Division")%></option>
				   <option value="Z"><%=E.EH("Zone")%></option>
			      </select>                               
	                 </div> 
	 		<div class="p-1">
	                     <input type="text" list="locntolist"  class="form-control inptcap" id="txtLocnTo"  name="txtLocnTo" required placeholder='<%=E.EH("Destination")%>' required   />
	                     <datalist id="locntolist"></datalist>
	                  </div>
	 		<div class="p-1">
	                     <span class="lbl"><%=E.EH("From")%>:</span>&nbsp;<input type="date" id="txtDateFrom" style="width:100%;" name="txtDateFrom" value="<%=strDate1%>" placeholder='<%=E.EH("Date From")%>'>   
	                 </div>
	 		<div class="p-1">
	                     <span class="lbl"><%=E.EH("To")%>:</span>:&nbsp;<input type="date" id="txtDateTo" style="width:100%;" name="txtDateTo" value="<%=strDate1%>"  placeholder='<%=E.EH("Date To")%>'>   
	                 </div>
	                 </div>
	                 </div>
	             </div>                            
	             <div class="row p-2 d-flex justify-content-center" id="divbut">
	                 <button class="btn btn-data" id="Calc" onclick="event.preventDefault();getPrclTranList();"><%=E.EH("Show Trains")%></button>
	             </div>
	             </div>
</form>
	</div>
<div class="col-lg-8 col-sm-12">
	<div id="divPrclTranList" class="tranlistdiv"   style="margin:10px;overflow-y:scroll;height:75vh;"></div>
</div>
</div>


  <!-- The Modal -->
  <div class="modal fade" id="prclTranSchd">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title prclhedr" id="pPrclHedr"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
		<div id="divPsgrRout" style="width:100%;height:370px;overflow-y:scroll;overflow-x:hidden;padding:10px;">
			<div class="row">
   			<div class="timeline-centered" id="divTranRout"></div>
        	</div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>
<script>		
   var sttnstr='';
   for (var i=0; i < aSttn.length;++i){
        sttnstr+= '<option value="'+aSttn[i]+'" />'; // Helplist for station
   }
   var locnfromlist=document.getElementById("locnfromlist");
   var locntolist=document.getElementById("locntolist");

   $(document).ready(function() {
	$('#selLocnFrom').on('change', function () {
     		var selectedValue = this.selectedOptions[0].value;
		if(selectedValue=="S")
			locnfromlist.innerHTML =sttnstr;
		if(selectedValue=="D")
			locnfromlist.innerHTML =dvsnstr;
		if(selectedValue=="Z")
			locnfromlist.innerHTML =zonestr;
		if(selectedValue=="DT")
			locnfromlist.innerHTML =dsttstr;
		if(selectedValue=="ST")
			locnfromlist.innerHTML =statstr;
			
	});
	$('#selLocnTo').on('change', function () {
     		var selectedValue = this.selectedOptions[0].value;
		if(selectedValue=="S")
			locntolist.innerHTML =sttnstr;
		if(selectedValue=="D")
			locntolist.innerHTML =dvsnstr;
		if(selectedValue=="Z")
			locntolist.innerHTML =zonestr;
		if(selectedValue=="DT")
			locntolist.innerHTML =dsttstr;
		if(selectedValue=="ST")
			locntolist.innerHTML =statstr;
			
	});

   });
</script>

<%@ include file="/pages/GG_Footer.jspf" %>
