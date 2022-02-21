<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RAS IRON ORE-RAKE ALLOCATION PLAN","/RailSAHAY/pages/RASIORERakeAlcnPlan.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDate1=dateFormat.format(date);
%>
<%@ include file="/pages/RASHeader.jspf" %>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1">
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAKE ALLOCATION PLAN")%></h3>
		<h3 class="mb-2 subcaption" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAS-IRON ORE")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selDvsn" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        		<option value="ASN"><%=E.EH("ASN-ASANSOL")%></option>
        		<option value="CKP"><%=E.EH("CKP-CHAKRADHAR PUR")%></option>
        		<option value="KGP"><%=E.EH("KGP-KHARAGPUR")%></option>
        		<option value="KUR"><%=E.EH("KUR-KHURDA ROAD")%></option>
        		<option value="SBP"><%=E.EH("SBP-SAMBALPUR")%></option>
        	</select>
	  </div>
	    <div class="col-lg-4 col-md-3 col-sm-12 col-inpt">        	
		<table><tr><td>
		   <input type="date" id="txtDateFrom" class="bg-white text-dark inptCap inpttext" style="width:100%;" name="txtDateFrom" value="<%=strDate1%>" placeholder='<%=E.EH("Date From")%>'>
		 </td><td>&nbsp;<%=E.EH("To")%>&nbsp;</td><td>
		   <input type="date" id="txtDateTo" class="bg-white text-dark inptCap inpttext" style="width:100%;" name="txtDateTo" value="<%=strDate1%>"  placeholder='<%=E.EH("Date To")%>'>		 </td></tr></table>
	    </div>
	    <div class="col-lg-2 col-md-3 col-sm-4 col-inpt">
		<button class="btn btn-data float-left" onclick="event.preventDefault();fetchIORERakeAlcnPlan();"><%=E.EH("Continue")%></button>
	    </div>
  	     </div>
	</nav>
	 <div class="container">
          <div class="row">
          <div class="col-lg-12 col-sm-12">
		<div id="divData" class="trmllistdata" style="padding:5px;"></div>
      </div>
      </div>
	</div>
<%@ include file="/pages/RASFooter.jspf" %>
<%@ include file="/pages/GG_Footer.jspf" %>
