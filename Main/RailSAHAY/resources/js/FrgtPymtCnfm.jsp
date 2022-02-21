<%
String PymtStts=(String)request.getParameter("PymtStts");
String Sttn="";
String CustCode="";
String InvcId="";
String ChrgType="";
String ChrgTypeCode="";
String IWOWFlag="";
String InvcDate="";
String Amnt="";
String ChrgId="";
String ChrgZone="";
String CnfmDate="";
String FOISTrxnId="";
String BankTrxnId="";
String BankTrxnTime="";

if(PymtStts.equals("INIT"))
{
	Sttn=(String)request.getParameter("Sttn");
	CustCode=(String)request.getParameter("CustCode");
	InvcId=(String)request.getParameter("InvcId");
	ChrgType=(String)request.getParameter("ChrgType");
	ChrgTypeCode=(String)request.getParameter("ChrgTypeCode");
	IWOWFlag=(String)request.getParameter("IWOWFlag");
	InvcDate=(String)request.getParameter("InvcDate");
	Amnt=(String)request.getParameter("Amnt");
	ChrgId=(String)request.getParameter("ChrgId");
	ChrgZone=(String)request.getParameter("ChrgZone");
	CnfmDate=(String)request.getParameter("CnfmDate");
}
else
{
	Sttn=(String)session.getAttribute("Sttn");
	CustCode=(String)session.getAttribute("CustCode");
	InvcId=(String)session.getAttribute("InvcId");
	ChrgType=(String)session.getAttribute("ChrgType");
	ChrgTypeCode=(String)session.getAttribute("ChrgTypeCode");
	IWOWFlag=(String)session.getAttribute("IWOWFlag");
	InvcDate=(String)session.getAttribute("InvcDate");
	Amnt=(String)session.getAttribute("Amnt");
	ChrgId=(String)session.getAttribute("ChrgId");
	ChrgZone=(String)session.getAttribute("ChrgZone");
	CnfmDate=(String)session.getAttribute("CnfmDate");
	if(PymtStts.equals("SUCCESS"))
	{
		FOISTrxnId=(String)request.getParameter("FOISTrxnId");
		BankTrxnId=(String)request.getParameter("BankTrxnId");
		BankTrxnTime=(String)request.getParameter("BankTrxnTime");
	}
}
String ChrgDesc="";
if(ChrgType.equals("F"))
	ChrgDesc="FREIGHT CHARGES";
if(ChrgType.equals("D"))
	ChrgDesc="DEMURRAGE CHARGES";
if(ChrgType.equals("W"))
	ChrgDesc="WHARFAGE CHARGES";
if(ChrgType.equals("L"))
	ChrgDesc="LOCAL CHARGES";

%>
<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Freight Payment Services","/RailSAHAY/pages/FrgtPymtCnfm.jsp",UserDevice,Browser);
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">

<script>
	var GG_PymtStts='<%=PymtStts%>';

	$(document).ready(function()
	{
		 $(window).scrollTop(0);
		fetchGTotlChrg('<%=ChrgId%>','<%=Sttn%>', '<%=CustCode%>', '<%=InvcId%>', '<%=ChrgType%>', '<%=IWOWFlag%>', '<%=InvcDate%>', '<%=Amnt%>', '', '', '');
		if('<%=ChrgType%>'=='F')
			$("#pChrgType").html("Payment of Freight Charges");
		if('<%=ChrgType%>'=='W')
			$("#pChrgType").html("Payment of Wharfage Charges");
		if('<%=ChrgType%>'=='D')
			$("#pChrgType").html("Payment of Demurrage Charges");
		if('<%=ChrgType%>'=='L')
			$("#pChrgType").html("Payment of Local Charges");
	});
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("FREIGHT PAYMENT SERVICES")%></h3>
              </div>
            </div>
           </div>
          </div>
          
          <div class="container">
		<div class="row">
		<div class="col-lg-12 col-sm-12">
		<p class="chrg-type" id="pChrgType"></p>
		<div id="divInptDtls">
			<table class="table table-chrgdesc table-responsive" style="margin-bottom:0px;"><tr><td class="lbl">Station</td><td class="val"><%=Sttn%></td><td class="lbl">Invoice Date</td><td class="val"><%=InvcDate%></td><td class="lbl">Charge Type</td><td class="val"><%=ChrgDesc%> (<i class="fas fa-rupee-sign"></i>&nbsp;<%=Amnt%>)</td></tr></table>
			<div id="inpt-deco" class="inpt-deco"></div>
		</div>
		<% if(PymtStts.equals("SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Successful!</strong> The payment for the outstanding charge has been successfully collected.		  
		  <table class="table table-striped table-bordered">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("ALREADY_SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment Already Collected!</strong> The payment for the outstanding charge has already been collected.
		  <table class="table table-striped table-bordered">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("ALREADY_PENDING")) { %>
		<div class="alert alert-info alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment Already In Process!</strong> The payment for the outstanding charge is in Process. Please check the status after 10 Minutes.
		  <table class="table table-striped table-bordered">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("FAILED")) { %>
		<div class="alert alert-danger alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Failed!</strong> It's due to some technical issue at this time, please try again after some time.
		</div>
		<% } %>
		<% if(PymtStts.equals("PENDING")) { %>
		<div class="alert alert-info alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment In Process!</strong> The payment for the outstanding charge is in Process. Please check the status after 10 Minutes.
		  <table class="table table-striped table-bordered">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<hr/>
		<br/>
		<div id="divChrgBrkup" style="width:100%;height:auto;"></div>
		
	<hr/>
          <p class="mesg-cnt"><%=E.EH("For any assistance regarding Freight Payments")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p>	
	</div></div>
	<form id="frmFrgtPymt" method="post" action="\RailSAHAY\FrgtPymtTrxn" target="frgtpymttrxn">
		<input type="hidden" id="txtChrgZone" name="txtChrgZone" value="<%=ChrgZone%>" />
		<input type="hidden" id="txtInvcId" name="txtInvcId" value="<%=InvcId%>" />
		<input type="hidden" id="txtInvcDate" name="txtInvcDate" value="<%=InvcDate%>" />
		<input type="hidden" id="txtIWOWFlag" name="txtIWOWFlag" value="<%=IWOWFlag%>" />
		<input type="hidden" id="txtChrgType" name="txtChrgType"  value="<%=ChrgTypeCode%>" />
		<input type="hidden" id="txtChrgId" name="txtChrgId" value="<%=ChrgId%>"  />
		<input type="hidden" id="txtAmnt" name="txtAmnt"  />
		<input type="hidden" id="txtCustCode" name="txtCustCode"  value="<%=CustCode%>" />
		<input type="hidden" id="txtCnfmDate" name="txtCnfmDate" value="<%=CnfmDate%>"  />
		<input type="hidden" id="txtGSTAmnt" name="txtGSTAmnt"   />
		<input type="hidden" id="txtGSTIN" name="txtGSTIN" />
		<input type="hidden" id="Optn" name="Optn" value="PAY_NOW" />
	</form>
</div>
<%@ include file="/pages/GG_Footer.jspf" %>

            