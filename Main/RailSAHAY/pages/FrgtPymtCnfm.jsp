<%
String PymtStts=((String)request.getParameter("PymtStts")).toUpperCase();
String Sttn="";
String CustCode="";
String InvcId="";
String FNOTId="";
String CustDmrgId="";
String TrxnId="";
String ChrgType="";
String ChrgTypeCode="";
String ChrgTypeDesc="";
String IWOWFlag="";
String InvcDate="";
String BaseAmnt="";
String ChrgId="";
String ChrgZone="";
String CnfmDate="";
String FOISTrxnId="";
String BankTrxnId="";
String BankTrxnTime="";
String RlyGstIn="";
String CustGstIn="";
String HndgAgnt="";
String HndgAgntName="";
String strErorMesg="";
String strErorDispFlag="N";

if(PymtStts.equals("INIT"))
{
	Sttn=(String)request.getParameter("Sttn");
	CustCode=(String)request.getParameter("CustCode");
	InvcId=(String)request.getParameter("InvcId");
	FNOTId=(String)request.getParameter("FNOTId");
	CustDmrgId=(String)request.getParameter("CustDmrgId");
	TrxnId=(String)request.getParameter("TrxnId");
	ChrgType=(String)request.getParameter("ChrgType");
	ChrgTypeCode=(String)request.getParameter("ChrgTypeCode");
	ChrgTypeDesc=(String)request.getParameter("ChrgTypeDesc");
	IWOWFlag=(String)request.getParameter("IWOWFlag");
	InvcDate=(String)request.getParameter("InvcDate");
	BaseAmnt=(String)request.getParameter("Amnt");
	ChrgId=(String)request.getParameter("ChrgId");
	ChrgZone=(String)request.getParameter("ChrgZone");
	CnfmDate=(String)request.getParameter("CnfmDate");
	RlyGstIn=(String)request.getParameter("RlyGstIn");
	CustGstIn=(String)request.getParameter("CustGstIn");
	HndgAgnt=(String)request.getParameter("HndgAgnt");
	HndgAgntName=(String)request.getParameter("HndgAgntName");
}
else
{
	Sttn=(String)session.getAttribute("Sttn");
	CustCode=(String)session.getAttribute("CustCode");
	InvcId=(String)session.getAttribute("InvcId");
	FNOTId=(String)session.getAttribute("FNOTId");
	CustDmrgId=(String)session.getAttribute("CustDmrgId");
	TrxnId=(String)session.getAttribute("TrxnId");
	ChrgType=(String)session.getAttribute("ChrgType");
	ChrgTypeCode=(String)session.getAttribute("ChrgTypeCode");
	ChrgTypeDesc=(String)session.getAttribute("ChrgTypeDesc");
	IWOWFlag=(String)session.getAttribute("IWOWFlag");
	InvcDate=(String)session.getAttribute("InvcDate");
	BaseAmnt=(String)session.getAttribute("BaseAmnt");
	ChrgId=(String)session.getAttribute("ChrgId");
	ChrgZone=(String)session.getAttribute("ChrgZone");
	CnfmDate=(String)session.getAttribute("CnfmDate");
	RlyGstIn=(String)session.getAttribute("RlyGstIn");
	CustGstIn=(String)session.getAttribute("CustGstIn");
	HndgAgnt=(String)session.getAttribute("HndgAgnt");
	HndgAgntName=(String)session.getAttribute("HndgAgntName");

	if(PymtStts.equals("SUCCESS"))
	{
		FOISTrxnId=(String)request.getParameter("FOISTrxnId");
		BankTrxnId=(String)request.getParameter("BankTrxnId");
		BankTrxnTime=(String)request.getParameter("BankTrxnTime");
	}
	if(PymtStts.equals("FAILED"))
	{
		try
		{
			strErorMesg=(String)request.getParameter("ErorMesg");

			if(strErorMesg==null)
				strErorMesg="";
		}
		catch(Exception e)
		{
			strErorMesg="";
		}

		if(strErorMesg.equals("ChargeID not confirmed.Please Try Again!"))
		{
			strErorDispFlag="Y";
			strErorMesg="Charge is Not Confirmed in FOIS/TMS System, Payment can not be done";
		}
		if(strErorMesg.contains("Freetime alloted for RR Payment has expired"))
		{
			strErorDispFlag="Y";
			strErorMesg="Free-time allotted for the payment has expired! Payment can not be processed";
		}
		if(strErorMesg.contains("Payment already in process through other payment mode!"))
		{
			strErorDispFlag="Y";
			strErorMesg="Payment already in process through other payment mode!";
		}

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
	ChrgDesc=ChrgTypeDesc;

%>
<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Freight Payment Services","/RailSAHAY/pages/FrgtPymtCnfm.jsp",UserDevice,Browser);
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<style>
.table-brkup td.lbl
{
	font-size:13px;
	color:#4d4d4d;
	font-weight:500;
}
.table-brkup td.val
{
	font-size:13px;
	color:#000;
	font-weight:600;
}

</style>
<script>
	var GG_PymtStts='<%=PymtStts%>';

	$(document).ready(function()
	{
		 $(window).scrollTop(0);
		console.log('<%=PymtStts%> , <%=ChrgId%> ,  <%=Sttn%>  , <%=CustCode%> ,  <%=InvcId%> ,<%=FNOTId%>, <%=CustDmrgId%>,<%=TrxnId%>, <%=ChrgType%> , <%=ChrgTypeCode%> , <%=IWOWFlag%> , <%=InvcDate%> , <%=BaseAmnt%> , <%=IWOWFlag%> , <%=FOISTrxnId%> , <%=BankTrxnId%> , <%=BankTrxnTime%> , <%=RlyGstIn%>, <%=CustGstIn%>');
		fetchGTotlChrg('<%=ChrgId%>','<%=Sttn%>', '<%=CustCode%>', '<%=InvcId%>','<%=FNOTId%>','<%=CustDmrgId%>','<%=TrxnId%>', '<%=ChrgType%>', '<%=IWOWFlag%>', '<%=InvcDate%>', '<%=BaseAmnt%>', '<%=RlyGstIn%>', '<%=CustGstIn%>', '<%=CustGstIn%>', '<%=HndgAgnt%>');
		if('<%=ChrgType%>'=='F')
		{
			$("#pChrgType").html('Payment of Freight Charges<span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span>');
		}
		if('<%=ChrgType%>'=='W')
		{
			$("#pChrgType").html('Payment of Wharfage Charges<span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span>');
			fetchWrfgBrkUp("DTLS",null, "<%=Sttn%>","<%=InvcId%>","<%=FNOTId%>","<%=CustDmrgId%>","<%=CustCode%>","<%=IWOWFlag%>");
		}
		if('<%=ChrgType%>'=='D')
		{
			$("#pChrgType").html('Payment of Demurrage Charges<span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span>');
			fetchDmrgBrkUp("DTLS",null, "<%=Sttn%>","<%=InvcId%>","<%=FNOTId%>","<%=CustDmrgId%>","<%=CustCode%>","<%=IWOWFlag%>");
		}
		
		if('<%=ChrgType%>'=='L')
		{
			$("#pChrgType").html('Payment of Local Charges<span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span>');
		/*	fetchLoclChrgBrkUp("DTLS",null, "<%=Sttn%>","<%=InvcId%>","<%=FNOTId%>","<%=CustDmrgId%>","<%=TrxnId%>","<%=CustCode%>","<%=ChrgId%>");*/
		
		}
		
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
			<table class="table table-chrgdesc table-responsive" style="margin-bottom:0px;"><tr><td class="lbl">Station</td><td class="val"><%=Sttn%></td>
			<% if(!InvcDate.equals("")) { %>
			<td class="lbl">Invoice Date</td><td class="val"><%=InvcDate%></td>
			<% } %>
			<td class="lbl">Charge Type</td><td class="val"><%=ChrgDesc%> ( <i class="fas fa-rupee-sign"></i>&nbsp;<%=BaseAmnt%> )</td>
			<% if(!HndgAgnt.equals("")) { %>
			<td class="lbl">Secondary Customer</td><td class="val"><%=HndgAgnt%> (<%=HndgAgntName%>)</td>
			<% } %>
			</tr></table>
			<div id="inpt-deco" class="inpt-deco"></div>
		</div>
		<% if(PymtStts.equals("SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Successful!</strong> The payment for the outstanding charge has been collected successfully.		  
		  <table class="table table-striped table-bordered table-pymtstts">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("ALREADY_SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment Already Collected!</strong> The payment for the outstanding charge has already been collected.
		  <table class="table table-striped table-bordered table-pymtstts">
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
		  <% if(strErorDispFlag.equals("Y")) { %>
  		   <strong>Transaction Failed!</strong> <%=strErorMesg%>
		  <% } else { %>
		  <strong>Transaction Failed!</strong> It's due to some technical issue at this time, please try again after some time.
		  <% } %>
		</div>
		<% } %>
		<% if(PymtStts.equals("CANCELLED")) { %>
		<div class="alert alert-warning alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Cancelled!</strong> May try it again. In case of any assistance/query regarding this payment service <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a>
		</div>
		<% } %>
		<% if(PymtStts.equals("PENDING")) { %>
		<div class="alert alert-info alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment In Process!</strong> The payment for the outstanding charge is in Process. Please check the status after 10 Minutes.
		  <table class="table table-striped table-bordered table-pymtstts">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>		
		<hr/>
		<br/>
		</div>
		</div>
		</div>
		<div class="container">
		<div class="row">
		<% if(!ChrgType.equals("F") && !ChrgType.equals("L")) { %>
		<div class="col-lg-3 col-md-3 col-sm-12 col-xl-3">
			<p class="chrg-type">Charge Detail</p>
			<div id="divCnfmChrgDtls" class="cnfm-chrg-dtls"></div>
		</div>
		<div class="col-lg-7 col-md-7 col-sm-12 col-xl-7">
		<% } else {%>
		<div class="col-lg-10 col-md-12 col-sm-12 col-xl-10">
		<% } %>
		<div id="divChrgBrkup" style="width:100%;height:auto;"></div>
		</div>
		<div class="d-none col-lg-2 d-lg-block d-xl-block col-xl-2">
		<img src="/RailSAHAY/resources/images/sbihelpdesk.jpg" class="img-fluid img-responsive" />
		</div>
		</div>
	<br/>
	<ul class="list-group list-group-flush">
	  <li class="list-group-item d-xl-none d-lg-none d-sm-block d-md-block"><p class="mesg-cnt1">For any Online Payment related assistance, issues or complaints, please contact SBI Helpline at 022-27560266/67 or email at <a class="card-link1" href="mailto:merchant@sbi.co.in">merchant@sbi.co.in</a></p></li>
	  <li class="list-group-item"><p class="mesg-cnt1"><a href="javascript:void(0)" onclick="openHelpSupport();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Help & Support")%></a></p></li>
	  <li class="list-group-item"><p class="mesg-cnt1"><%=E.EH("For any assistance regarding Freight Payment Services")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p></li>
	</ul>
          
	</div></div>
	<form id="frmFrgtPymt" method="post" action="\RailSAHAY\FrgtPGTrxn" target="_self">
		<input type="hidden" id="txtChrgZone" name="txtChrgZone" value="<%=ChrgZone%>" />
		<input type="hidden" id="txtInvcId" name="txtInvcId" value="<%=InvcId%>" />
		<input type="hidden" id="txtFNOTId" name="txtFNOTId" value="<%=FNOTId%>" />
		<input type="hidden" id="txtCustDmrgId" name="txtCustDmrgId" value="<%=CustDmrgId%>" />
		<input type="hidden" id="txtTrxnId" name="txtTrxnId" value="<%=TrxnId%>" />
		<input type="hidden" id="txtInvcDate" name="txtInvcDate" value="<%=InvcDate%>" />
		<input type="hidden" id="txtIWOWFlag" name="txtIWOWFlag" value="<%=IWOWFlag%>" />
		<input type="hidden" id="txtChrgType" name="txtChrgTypeCode"  value="<%=ChrgTypeCode%>" />
		<input type="hidden" id="txtChrgType" name="txtChrgTypeDesc"  value="<%=ChrgTypeDesc%>" />
		<input type="hidden" id="txtChrgType" name="txtChrgType"  value="<%=ChrgType%>" />
		<input type="hidden" id="txtChrgId" name="txtChrgId" value="<%=ChrgId%>"  />
		<input type="hidden" id="txtAmnt" name="txtAmnt"  />
		<input type="hidden" id="txtBaseAmnt" name="txtBaseAmnt" value="<%=BaseAmnt%>" />
		<input type="hidden" id="txtCustCode" name="txtCustCode"  value="<%=CustCode%>" />
		<input type="hidden" id="txtCnfmDate" name="txtCnfmDate" value="<%=CnfmDate%>"  />
		<input type="hidden" id="txtGSTAmnt" name="txtGSTAmnt"   />
		<input type="hidden" id="txtGSTIN" name="txtGSTIN" />
		<input type="hidden" id="txtRlyGSTIN" name="txtRlyGSTIN" value="<%=RlyGstIn%>" />
		<input type="hidden" id="txtCustGSTIN" name="txtCustGSTIN" value="<%=CustGstIn%>" />
		<input type="hidden" id="txtHndgAgnt" name="txtHndgAgnt" value="<%=HndgAgnt%>" />
		<input type="hidden" id="txtHndgAgntName" name="txtHndgAgntName" value="<%=HndgAgntName%>" />
		<input type="hidden" id="txtSttn" name="txtSttn" value="<%=Sttn%>" />
		<input type="hidden" id="Optn" name="Optn" value="PAY_NOW" />
	</form>
</div>
<%@ include file="/pages/GG_Footer.jspf" %>

            