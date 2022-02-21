<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RAS IRON ORE-RAKE ALLOTMENT","/RailSAHAY/pages/RASIORakeAlotment.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDF=dateFormat.format(date);
String strDateFrom=strDF.substring(0,8)+"01";

Calendar calendar = Calendar.getInstance();
calendar.setTime(new Date());

calendar.add(Calendar.DATE, +1);
Date date1= calendar.getTime();
String strDateTo=dateFormat.format(date1);


DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
calendar.setTime(new Date());
calendar.add(Calendar.DATE, +2);
Date date2= calendar.getTime();
String strNextScndDay=dateFormat1.format(date2);

%>
<script>
GG_NextScndDate="<%=strNextScndDay%>";
</script>
<style>
input, select {
    height:20px;
    line-height: 50px;
    vertical-align: middle;
    width: 100%;
    padding: 0 20px;
}
</style>
<%@ include file="/pages/RASHeader.jspf" %>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1">
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAKE ALLOTMENT")%></h3>
		<h3 class="mb-2 subcaption" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAS-IRON ORE")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selDvsn" class="text-dark bg-white inptCap inpttext" style="width:100%;height:40px;">
        		<option value="ASN"><%=E.EH("ASN-ASANSOL")%></option>
        		<option value="CKP"><%=E.EH("CKP-CHAKRADHAR PUR")%></option>
        		<option value="KGP"><%=E.EH("KGP-KHARAGPUR")%></option>
        		<option value="KUR"><%=E.EH("KUR-KHURDA ROAD")%></option>
        		<option value="SBP"><%=E.EH("SBP-SAMBALPUR")%></option>
        	</select>
	  </div>
	    <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selSttn" class="text-dark bg-white" style="width:100%;height:40px;">
        	</select>
	    </div>
	    <div class="col-lg-5 col-md-5 col-sm-12 col-inpt">        	
		<table><tr><td>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="radio" class="form-check-input" value="A" id="optAll" checked name="optPerd"><%=E.EH("All")%>
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="radio" class="form-check-input" value="S" id="optSpec" name="optPerd"><%=E.EH("Period")%>
		  </label>
		</div>
		</td><td>
		   <input type="date" id="txtDateFrom" class="bg-white text-dark inptCap inpttext" style="width:100%;height:40px;" name="txtDateFrom" value="<%=strDateFrom%>" placeholder='<%=E.EH("Date From")%>'>
		 </td><td>&nbsp;<%=E.EH("To")%>&nbsp;</td><td>
		   <input type="date" id="txtDateTo" class="bg-white text-dark inptCap inpttext" style="width:100%;height:40px;" name="txtDateTo" value="<%=strDateTo%>"  placeholder='<%=E.EH("Date To")%>'>		 
		   <input type="hidden" id="txtNSDay" value="<%=strNextScndDay%>" />
		   </td></tr></table>
	    </div>
	    <div class="col-lg-1 col-md-1 col-sm-12 col-inpt">
		<button class="btn btn-data float-left" onclick="event.preventDefault();fetchIORERakeAlot();"><%=E.EH("Continue")%></button>
	    </div>
  	     </div>
	</nav>
	<div class="row">
	  <div class="col-lg-10 offset-lg-1 col-sm-12">         
	<div class="mt-2 mb-3 text-center small" id="divColrInd" align="right">
	      <span class="mr-1">
		<i class="fas fa-circle text-danger"></i> <%=E.EH("Restricted Indent(s)")%>
	      </span>
	      <span class="mr-1">
		<i class="fas fa-circle text-success"></i> <%=E.EH("Allotted Indent(s)")%>
	      </span>	                     
	 </div>
	</div>	
  </div>
          <div class="row">
          <div class="col-lg-12 col-sm-12">
		<div id="divData" class="trmllistdata" style="padding:5px;"></div>
		<div class="col-lg-10 offset-lg-1 col-sm-12">
		<ul class="list-group list-group-flush" style="font-weight:600;font-size:12px;color:#4d4d4d;text-align:center;">
		  <li class="list-group-item">* <%=E.EH("Disclaimer : Allotment is valid subject to restrictions and validity of indents")%></li>
		  <li class="list-group-item">* <%=E.EH("Allotment Available Upto")%> <%=strDateTo%></li>
		  <li class="list-group-item">* <%=E.EH("Supplied/Canceled Allotments will not be shown")%></li>
		</ul>
	</div>
      </div>
      </div>
<%@ include file="/pages/RASFooter.jspf" %>
<%@ include file="/pages/GG_Footer.jspf" %>
