<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RAS COAL-ARREAR OF ALLOTMENTS MORE THAN 30 DAYS","/RailSAHAY/pages/RASCOALAlotmentthrty.jsp",UserDevice,Browser);
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
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gDvsn.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gPrty.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gDvsnSpsr.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gCoal.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gPit.js"></script>
<script type="text/javascript"  src="/RailSAHAY/resources/js/ras/gCust.js"></script>
<%@ include file="/pages/RASHeader.jspf" %>

	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1">
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("ARREAR OF ALLOTMENTS MORE THAN 30 DAYS")%></h3>
		<h3 class="mb-2 subcaption" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAS-COAL")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
        	<select id="selDvsn" class="text-dark bg-white inpttext" style="width:100%;">
		<%--
        		<option value="ASN"><%=E.EH("ASN-ASANSOL")%></option>
        		<option value="CKP"><%=E.EH("CKP-CHAKRADHAR PUR")%></option>
        		<option value="KGP"><%=E.EH("KGP-KHARAGPUR")%></option>
        		<option value="KUR"><%=E.EH("KUR-KHURDA ROAD")%></option>
        		<option value="SBP"><%=E.EH("SBP-SAMBALPUR")%></option>
		--%>
        	</select>
		<select id="selSttn" class="text-dark bg-white" style="display:none;"></select>

	  </div>
	    
	    <div class="col-lg-5 col-md-6 col-sm-12 col-inpt">
	    	<table>
	    	<tr>
	    	<td>
        	<select id="selPrty" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
        	</td>
        	<td>
        	<select id="selSpsr" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
        	</td>
        	<td>        	
        	<select id="selCoalFld" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
        	</td>
        	<td>
        	<select id="selPilt" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
		<input type="hidden" id="txtCmdt" value="COAL" />
        	</td>
        	</tr>
        	</table>
	    </div>
	    <div class="col-lg-4 col-md-4 col-sm-12 col-inpt">
	    	<table>
	    	<tr>
        	<td>
        	<input type="text" class="text-dark bg-white form-field inptCap inpttext" id="txtDstn" name="txtDstn" placeholder="Destination" />
        	</td>
        	<td>
        	<input type="text" class="text-dark bg-white form-field inptCap inpttext" id="txtCust" name="txtCust" placeholder="Customer" />
        	</td>
        	<td>
        	<button class="btn btn-data float-left" onclick="event.preventDefault();fetchRASCoalAlotTh();"><%=E.EH("Continue")%></button>
        	</td>
        	</tr>
        	</table>
	    </div>
  	     </div>
	</nav>
          <div class="row">
          <div class="col-lg-12 col-sm-12">
		<div id="divData" class="trmllistdata" style="padding:5px;"></div>
      </div>
      </div>
<%@ include file="/pages/RASFooter.jspf" %>
<%@ include file="/pages/GG_Footer.jspf" %>
