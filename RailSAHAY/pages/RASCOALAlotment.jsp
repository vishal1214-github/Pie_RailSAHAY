<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RAS COAL-COAL ALLOTMENT","/RailSAHAY/pages/RASCOALAlotment.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDateTo=dateFormat.format(date);
String strDateFrom=strDateTo.substring(0,8)+"01";
%>
<script>
GG_NextScndDate="";
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
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("COAL ALLOTMENT")%></h3>
		<h3 class="mb-2 subcaption" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAS-COAL")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-2 col-md-2 col-sm-12 col-inpt">
        	<select id="selDvsn" class="text-dark bg-white inpttext" style="width:100%;">
        	</select>
		<select id="selSttn" class="text-dark bg-white" style="display:none;"></select>

	  </div>
	    <div class="col-lg-4 col-md-4 col-sm-12 col-inpt">
	    	<table>
	    	<tr>
	    	<td>
        	<select id="selPrty" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
        	</td>
        	<td>        	
        	<input type="text" class="text-dark bg-white form-field inptCap inpttext" id="txtCust" name="txtCust" placeholder='<%=E.EH("Customer")%>' />
        	</td>
        	<td>  
		</tr>
		</table>
	  </div>
	    <div class="col-lg-4 col-md-4 col-sm-12 col-inpt">
	    	<table>
	    	<tr>      	
        	<td>
		<input type="date" id="txtDateFrom" class="bg-white text-dark inptCap inpttext" style="width:100%;" name="txtDateFrom" value="<%=strDateFrom%>" placeholder='<%=E.EH("Date From")%>'>
		</td>
		<td>&nbsp;<%=E.EH("To")%>&nbsp;</td><td>
		<input type="date" id="txtDateTo" class="bg-white text-dark inptCap inpttext" style="width:100%;" name="txtDateTo" value="<%=strDateTo%>"  placeholder='<%=E.EH("Date To")%>'>		 
		</td>
        	</td>
        	<td>
		<input type="hidden" id="txtCmdt" value="COAL" />
		<button class="btn btn-data float-left" onclick="event.preventDefault();fetchRASCoalAlot();"><%=E.EH("Continue")%></button>
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
