<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"ODR-WISE OUTSTANDING AND MATURED INDENTS","/RailSAHAY/pages/ODROtsgDtls.jsp",UserDevice,Browser);
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String strDateTo=dateFormat.format(date);
String strDateFrom=strDateTo.substring(0,8)+"01";
%>
<script>
GG_NextScndDate="";
</script>
<%@ include file="/pages/RASHeader.jspf" %>

 <div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1">
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("ODR-WISE OUTSTANDING & MATURED INDENTS")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-3 col-md-4 col-sm-6 col-inpt">
        	<select id="selOptn" class="text-dark bg-white inpttext" style="width:100%;">
        		<option value="ODR_RK_OTSG" selected>OUTSTANDING ODRs</option>
        		<option value="MATURED_INDENTS">MATURED INDENTS (LAST 30 DAYS)</option>
        	</select>
	  </div>
	    <div class="col-lg-3 col-md-4 col-sm-6 col-inpt">
	    	<table>
	    	<tr>
	    	<td>
        	<select id="selZone" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
		<input type="hidden" id="txtCmdt" value="ODR" />
        	</td>
        	<td>
        	<button class="btn btn-data float-left" onclick="event.preventDefault();fetchODRDtls();"><%=E.EH("Continue")%></button>
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
