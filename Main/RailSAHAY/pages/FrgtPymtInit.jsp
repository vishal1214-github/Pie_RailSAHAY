<%
String Sttn=(String)request.getParameter("Sttn");
String CustCode=(String)request.getParameter("CustCode");
String InvcId=(String)request.getParameter("InvcId");
String ChrgType=(String)request.getParameter("ChrgType");
String ChrgTypeCode=(String)request.getParameter("ChrgTypeCode");
String IWOWFlag=(String)request.getParameter("IWOWFlag");
String InvcDate=(String)request.getParameter("InvcDate");
String Amnt=(String)request.getParameter("Amnt");
String ChrgId=(String)request.getParameter("ChrgId");
String ChrgZone=(String)request.getParameter("ChrgZone");
String CnfmDate=(String)request.getParameter("CnfmDate");
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
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Freight Payment Services","/RailSAHAY/pages/FrgtPymtInit.jsp",UserDevice,Browser);
%>
<script>
var fbdPymtWindow;
</script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">

<script>
	var GG_Message='';

	$(document).ready(function(){
		
		var bc = new BroadcastChannel('fbd_channel');
		bc.onmessage = function(e) {
    		const message1 = e.data;
   		console.log("Broadcast Message"+message1);
		var message=message1.split(":")[1];
		GG_Message=message;
		console.log("Message is:"+message);
		 $(window).scrollTop(0);
		if(message=="FAILED")
		{
			$("#divMessage").html('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Failed!</strong> Transaction has Failed due to some technical issue! Please try after some time</div>');
			$(".btn-action").show();
			$("#btnBackDB").hide();
			fbdPymtWindow.close();
		}
		if(message=="SUCCESS")
		{
			$("#divMessage").html('<div class="alert alert-success alert-dismissible"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Successful!</strong> Transaction is Successful, Please go <a href="javascript:void(0)" onclick="cancelPayment();" class="back-link">back</a> to your Dashboard</div>');
			$(".btn-action").hide();
			$("#btnBackDB").show();
		}
		};

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
		<div id="divMessage" class="div-mesg"></div>
		<hr/>
		<br/>
		<div id="divChrgBrkup" style="width:100%;height:auto;"></div>
		
	<hr/>
          <p class="mesg-cnt"><%=E.EH("For any assistance regarding Freight Payments")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p>	
	</div></div>
	<form id="frmFrgtPymt" method="post" action="\RailSAHAY\FrgtPymtTrxn" target="frgtpymttrxn">
		<input type="hidden" id="txtChrgZone" name="txtChrgZone" value="<%=ChrgZone%>" />
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

            