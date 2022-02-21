<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"RAS IRON ORE-IRON ORE INDENTS","/RailSAHAY/pages/RASIOREIndt.jsp",UserDevice,Browser);
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
                <h3 class="mb-1" data-aos="fade-up" data-aos-delay="100"><%=E.EH("IRON ORE INDENTS")%></h3>
		<h3 class="mb-2 subcaption" data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAS-IRON ORE")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	  <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selZone" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        		<option value="SE"><%=E.EH("SE-SOUTH EAST RAILWAY")%></option>
        		<option value="ECO"><%=E.EH("ECO-EAST COAST RAILWAY")%></option>
        		<option value="SW"><%=E.EH("SW-SOUTH WEST RAILWAY")%></option>
        	</select>
	  </div>
	  <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selDvsn1" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
	  </div>
	  <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">
        	<select id="selSttn1" class="text-dark bg-white inptCap inpttext" style="width:100%;">
        	</select>
		<select id="selSttn" class="text-dark bg-white inptCap inpttext" style="width:100%;display:none;">
        	</select>
		<input type="hidden" id="txtCmdt" value="IORE" />
	  </div>
	    <div class="col-lg-3 col-md-3 col-sm-12 col-inpt">        	
		<table><tr><td>
		   <input type="date" id="txtDate" class="bg-white text-dark inptCap inpttext" style="width:100%;" name="txtDate" value="<%=strDate1%>" placeholder='<%=E.EH("Date")%>'>
		 </td><td>
		<button class="btn btn-data float-left" onclick="event.preventDefault();fetchIOREIndt();"><%=E.EH("Continue")%></button>
		</td>
		</tr></table>
	    </div>
  	     </div>
	</nav>
	 <div class="container-fullwidth">
          <div class="row">
          <div class="col-lg-5 col-sm-12">
		<div id="divData" class="trmllistdata" style="padding:5px;"></div>
      </div>
          <div class="col-lg-7 col-sm-12">
		<div id="divDtlsData" class="trmllistdata" style="padding:5px;"></div>
      </div>
      </div>
	</div>
<%@ include file="/pages/RASFooter.jspf" %>
<%@ include file="/pages/GG_Footer.jspf" %>
