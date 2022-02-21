<%@ include file="/pages/GG_Header.jspf" %>  
<%
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"DOWNLOAD GST TAX INVOICE","/RailSAHAY/pages/GSTTaxInvc.jsp",UserDevice,Browser);
String strEror="";
String strGSTTaxInvc="";
try
{
	strEror=((String)request.getAttribute("ERROR")).trim();
	if(strEror==null)
	{
		strEror="";
	}
}
catch(Exception e)
{
	strEror="";
}
try
{
	strGSTTaxInvc=((String)request.getAttribute("GSTTAXINVC")).trim();
	if(strGSTTaxInvc==null)
	{
		strGSTTaxInvc="";
	}
}
catch(Exception e)
{
	strGSTTaxInvc="";
}

%>
<style>
.gst-rmrk
{
	font-size:12px;
	font-weight:600;
	color:#4d4d4d;
	font-family:'Roboto Condensed';
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
</style>
<% if(!strEror.equals("")) { %>
<script>
	$(document).ready(function() {
		custPrompt("<%=strEror%>");
		$("#txtTaxInvc").focus();
		$("#txtTaxInvc").select();
	
        });
</script>
<% } %>
 <nav class="navbar navbar-expand-sm bg-origprimary navbar-light">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("DOWNLOAD GST TAX INVOICE")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline" action="/RailSAHAY/GSTTaxInvc" method="post">
	          <table style="width:90vw;">
	          	<tr><td style="width:250px;"><input class="form-control mr-sm-2 inptcap" required value="<%=strGSTTaxInvc%>" id="txtTaxInvc" name="txtTaxInvc" type="number" style="width:98%;" placeholder='<%=E.EH("TAX INVOICE NUMBER")%>' >
		       </td><td><button class="btn btn-data float-left btn-submit" type="submit"><%=E.EH("Download")%></button></td></tr>
		       <tr><td colspan="2"><span class="gst-rmrk"><%=E.EH("Enter GST Tax Invoice No. printed on Railway Receipt(RR)/Money Receipt(MR)")%></span></td>
		       </tr>
		  </table>
  	      </form>
  	     </div>
	</nav>
	
	 <div class="container">
	  <div class="row">
	  <div class="col-lg-12 col-sm-12">
		<div id="divData" class="trmllistdata" style="padding:5px;">
			
		</div>
      </div>
      </div>
	</div>
<%@ include file="/pages/GG_Footer.jspf" %>