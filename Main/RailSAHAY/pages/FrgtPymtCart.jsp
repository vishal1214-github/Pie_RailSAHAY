<%@ page import = "model.valubean.PymtCartBean,java.util.*;" %>
<%
String PymtStts=((String)request.getParameter("PymtStts")).toUpperCase();
String strErorMesg="";
String strErorChrgId="";
String strSttsDesc="";
String strErorDispFlag="N";
String FOISTrxnId="";
String BankTrxnId="";
String BankTrxnTime="";
ArrayList lstCart=null;
int dblTotlBaseAmnt=0;
int dblTotlWaiver=0;
int dblTotlGst=0;
int dblNetAmnt=0;
int intCartSize=0;
String strSecCustRmrk="";
String strErorClss="";
try
{
    lstCart=(ArrayList<PymtCartBean>)session.getAttribute("CartPymtArr");
    if(lstCart!=null)
    {
        if(lstCart.size()>0)
        {
   	    intCartSize=lstCart.size();
            for(int i=0;i<lstCart.size();i++)
            {
                dblTotlBaseAmnt+=Integer.parseInt(((PymtCartBean)lstCart.get(i)).getAmnt());
                dblTotlWaiver+=Integer.parseInt(((PymtCartBean)lstCart.get(i)).getWaiver());
                dblTotlGst+=Integer.parseInt(((PymtCartBean)lstCart.get(i)).getGstAmnt());
                dblNetAmnt+=Integer.parseInt(((PymtCartBean)lstCart.get(i)).getNetAmnt());
            }
	    if((((PymtCartBean)lstCart.get(0)).getCustCtgr()).equals("S"))	
	    {
		strSecCustRmrk="<p class='sec-cust-rmrk'><i class='fas fa-caret-right'></i>&nbsp;&nbsp;Payments By Secondary Customers (Handling Agents)</p>";
     	    }
        }
    }
}
catch(Exception e)
{
    e.printStackTrace();
    System.out.println("Exception in getting Cart:"+e.getMessage());
    lstCart=null;
}
if(PymtStts.equals("INIT"))
{
   //do nothing 	
}
else
{	

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
		}
		catch(Exception e)
		{
			strErorMesg="";
		}
		try
		{
			strErorChrgId=(String)request.getParameter("ChrgId");
		}
		catch(Exception e)
		{
			strErorChrgId="";
		}
		try
		{
			strSttsDesc=(String)request.getParameter("SttsDesc");
		}
		catch(Exception e)
		{
			strSttsDesc="";
		}


		if(strErorMesg.equals("ChargeID not confirmed.Please Try Again!"))
		{
			strErorDispFlag="Y";
			strErorMesg="Charge is Not Confirmed in FOIS/TMS System, Payment can not be done! Please contact concerned railway Staff";
		}
		if(strErorMesg.equals("TRANSACTION PENDING AGAINST GIVEN CHARGE ID"))
		{
			strErorDispFlag="Y";
			strErorMesg="Transaction already attempted for the charge! Currently is in PENDING state";
		}

	}
}

%>
<%@ include file="/pages/GG_Header.jspf" %>
<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/pymtcart.js"></script>
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
	var GG_CartSize=<%=intCartSize%>;

	<% if(intCartSize>0) { %>	
		var GG_CartPymtArr=[<% for(int i=0;i<intCartSize;i++) { %><% if(i==(intCartSize-1)) { %>'<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>' <% } else { %> '<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>',<% } %><% } %>];
	<% } %>
	var GG_PymtStts='<%=PymtStts%>';

	$(document).ready(function()
	{
                $(window).scrollTop(0);
	});
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">FREIGHT PAYMENT SERVICES</h3>		
              </div>

		<p class="chrg-type" style="margin-right:15px;"><span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span></p>
            </div>
           </div>
          </div>
          <div class="container">
          <%=strSecCustRmrk%>
		<div class="row">
		<div class="col-lg-12 col-sm-12">
		<p class="chrg-type" id="pChrgType"></p>
		<div id="divInptDtls">
			<table class="table table-chrgdesc table-responsive" style="margin-bottom:0px;"><tr>
			<td class="lbl">Payments in the Cart</td><td class="val" id="headPymtCont"><span class="badge badge-primary text-white" style="font-size:1.2em;"><%=lstCart.size()%></span></td>
			<td class="lbl">Net Payable Amount</td><td class="val" id="headPymtAmnt"><i class="fas fa-rupee-sign"></i>&nbsp;<%=dblNetAmnt%> </td>			
			<% if(PymtStts.equals("SUCCESS")) { %>
			<td class="lbl-success"><i class="fas fa-check"></i>&nbsp;&nbsp;Paid Successfully</td>
			<% } %>
			</tr></table>
			<div id="inpt-deco" class="inpt-deco"></div>
		</div>
		<% if(PymtStts.equals("SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible alert-trxn-stts">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Successful!</strong> The payment for the outstanding charge has been collected successfully.		  
		  <table class="table table-striped table-bordered table-pymtstts">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("ALREADY_SUCCESS")) { %>
		<div class="alert alert-success alert-dismissible alert-trxn-stts">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment Already Collected!</strong> The payment for the outstanding charge has already been collected.
		  <table class="table table-striped table-bordered table-pymtstts">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("ALREADY_PENDING")) { %>
		<div class="alert alert-info alert-dismissible alert-trxn-stts">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Payment Already In Process!</strong> The payment for the outstanding charge is in Process. Please check the status after 10 Minutes.
		  <table class="table table-striped table-bordered">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=PymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
		<% } %>
		<% if(PymtStts.equals("FAILED")) { %>
		<div class="alert alert-danger alert-dismissible alert-trxn-stts">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <% if(strErorDispFlag.equals("Y")) { %>
  		   <strong>Transaction Failed!</strong> <%=strErorMesg%>
		  <% } else { %>
		  <strong>Transaction Failed!</strong> It's due to some technical issue at this time, please try again after some time.
		  <% } %>
		</div>
		<% } %>
		<% if(PymtStts.equals("CANCELLED")) { %>
		<div class="alert alert-warning alert-dismissible alert-trxn-stts">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Cancelled!</strong> May try it again. In case of any assistance/query regarding this payment service <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a>
		</div>
		<% } %>
		<% if(PymtStts.equals("PENDING")) { %>
		<div class="alert alert-info alert-dismissible alert-trxn-stts">
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
          <div class="container-fullwidth mr-3 ml-3">
		<div class="row">
                    <div class="col-lg-9 col-sm-12" id="divPymtList">
                    <% if((lstCart==null) || (lstCart.size()==0)) { %>
                        <div align="center"><img src="/RailSAHAY/resources/images/empty-cart.jpg" class="img img-responsive img-fluid" /></div>
                    <% } else { %>
                        <% for (int i=0;i<lstCart.size();i++) {  %>
			<% if((((PymtCartBean)lstCart.get(i)).getChrgId()).equals(strErorChrgId)) {  %>
				<div class="cart-pymt-card card error-card">
			<% } else { %>
				<div class="cart-pymt-card card">
			<% } %>
                        
			    <div class="card-body">
                            <table class="table table-cart-pymt">
                            <tr>
                                <td style="border-bottom:1px solid #eee;"><i class="fas fa-caret-right"></i>&nbsp;<span class="pymt-type"><%=((PymtCartBean)lstCart.get(i)).getChrgTypeDesc()%></span>&nbsp;&nbsp;<span class="fnot-val" style="font-size:0.8em;font-weight:500;">(Accrued: <%=((PymtCartBean)lstCart.get(i)).getAcrlDate()%>, Raised: <%=((PymtCartBean)lstCart.get(i)).getCnfmDate()%>)</span></td>
                                <td rowspan="2"><span class="amnt float-right"><i class="fas fa-rupee-sign"></i>&nbsp;<%=((PymtCartBean)lstCart.get(i)).getNetAmnt()%></span></td>
                            </tr>
                            <tr>
				<% if((((PymtCartBean)lstCart.get(i)).getFNOTNumb()).equals("")) { %>
                                <td style="background:#fff5e6;">
                                <p class="sttn"><%=((PymtCartBean)lstCart.get(i)).getSttnName()%> (<%=((PymtCartBean)lstCart.get(i)).getSttn()%>)<span class="dvsn float-right"><%=((PymtCartBean)lstCart.get(i)).getDvsn()%></span></p>                              				
				</td>
				<% } else {%>
                                <td>
                                <p class="sttn"><%=((PymtCartBean)lstCart.get(i)).getSttnName()%> (<%=((PymtCartBean)lstCart.get(i)).getSttn()%>)<span class="dvsn float-right"><%=((PymtCartBean)lstCart.get(i)).getDvsn()%></span></p>                              				
	                        <p class="fnot-dtls"><span class="fnot-lbl">F/Note Detail</span><span class="fnot-val"><%=((PymtCartBean)lstCart.get(i)).getFNOTNumb()%>/<%=((PymtCartBean)lstCart.get(i)).getFNOTDate()%></span></p>
				</td>
				<% } %>
                            </tr>
                            <tr>
			</table>		
                            <button class="btn btn-sm btn-warning del-icon" onclick="showDelConfirmationBox('<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>');"><i class="fas fa-times"></i></button>
			</div>
			<div class="card-footer">
				<% if((((PymtCartBean)lstCart.get(i)).getHndgAgnt()).equals("")) { %>
                                    <p class="cust-dtls"><%=((PymtCartBean)lstCart.get(i)).getCustCode()%> (GSTIN: <%=((PymtCartBean)lstCart.get(i)).getCnsrGstin()%>) <span class="view-dtls-icon float-right" onclick="showChrgGSTBrkUp(this,'<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>');"><i class="fas fa-chevron-down"></i></span></p>                              
				<% } else { %>
					<p class="cust-dtls"><%=((PymtCartBean)lstCart.get(i)).getCustCode()%> (HANDLING AGENT: <%=((PymtCartBean)lstCart.get(i)).getHndgAgnt()%>, GSTIN: <%=((PymtCartBean)lstCart.get(i)).getCnsrGstin()%>) <span class="view-dtls-icon float-right" onclick="showChrgGSTBrkUp(this,'<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>');"><i class="fas fa-chevron-down"></i></span></p>
				<% } %>
			</div>
                        </div>
			<div class="collapse" id="div<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>"><div class="card card-body chrg-gst-brkup" id="divBody<%=((PymtCartBean)lstCart.get(i)).getChrgId()%>"></div></div>
                        <% } %>
                    <%}%>
                    </div>
                    <div class="col-lg-3 col-sm-12" id="divAmntDtls">
                        <% if((lstCart==null) || (lstCart.size()==0)) { } else {%>
                        <table class="table table-bordered table-pymt-smry">
                            <tr><td class="lbl">Total Amount</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;<%=dblTotlBaseAmnt%></td></tr>
                            <tr><td class="lbl">Waiver(s)/Adjustment(s)</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;<%=dblTotlWaiver%></td></tr>
                            <tr><td class="lbl">GST Applicable</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;<%=dblTotlGst%></td></tr>
                            <tr><td class="lbl-net">Net Payable</td><td class="val-net"><i class="fas fa-rupee-sign"></i>&nbsp;<%=dblNetAmnt%></td></tr>
                        </table>
			<% if(!PymtStts.equals("SUCCESS")) { %>
                        <button class="btn btn-danger text-white btn-trxn" style="border-radius:0px;width:100%;" onclick="makeCartPayment();">Continue with Payment&nbsp;<i class="fas fa-chevron-right"></i></button>
			<% } %>
			<button class="btn btn-secondary text-white btn-trxn" style="border-radius:0px;width:100%;margin-top:15px;"  onclick="backDashboard();"><i class="fas fa-chevron-left"></i>&nbsp;Back to Dashboard</button>
			<img src="/RailSAHAY/resources/images/sbibanner.jpg" class="img-responsive img-fluid img mt-3">
                        <% } %>
                    </div>
                </div>
		<% if(lstCart!=null) { %>
		<div class="row">
			<div class="col-lg-12 col-sm-12">
			<table class="table">	
				<tr><td><p class="chrg-levy">*Transaction Charges as applicable will be levied.</p></td></tr>
				<tr><td><p class="chrg-levy">*In case of Corporate Banking, payment has to be approved within half-an-hour of initiating the payment.</p></td></tr>
			</table>
			</div>
		</div>
		<% } %>
            </div>
		<br/>
            <br/>
	<ul class="list-group list-group-flush">
	  <li class="list-group-item d-xl-none d-lg-none d-sm-block d-md-block"><p class="mesg-cnt1">For any Online Payment related assistance, issues or complaints, please contact SBI Helpline at 022-27560266/67 or email at <a class="card-link1" href="mailto:merchant@sbi.co.in">merchant@sbi.co.in</a></p></li>
	  <li class="list-group-item"><p class="mesg-cnt1"><a href="javascript:void(0)" onclick="openHelpSupport();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;Help & Support</a></p></li>
	  <li class="list-group-item"><p class="mesg-cnt1">For any assistance regarding Freight Payment Services <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;Contact Us</a></p></li>
	</ul>          
	</div></div>
	<div id="confirm-modal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">			
				<h4 class="modal-title" style="color:#b32400;">Confirmation</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p><strong>Are you sure you want to remove this payment from the cart?</strong> You may add this payment again from the payments dashboard.</p>
			</div>
			<div class="modal-footer">
                	<button  class="btn btn-info btn-sm" data-dismiss="modal">Cancel</button>
			<button  class="btn btn-danger btn-sm" onclick="delPymtFromCart();">Yes, remove it!</button>
			</div>
		</div>
	</div>
</div>
	<form id="frmCartFrgtPymt" method="post" action="\RailSAHAY\FrgtPGCartTrxn" target="_self">
		<input type="hidden" id="Optn" name="Optn" value="PAY_NOW" />
		<input type="hidden" id="NetAmnt" name="NetAmnt" value="<%=dblNetAmnt%>" />
	</form>
</div>
<% if(PymtStts.equals("SUCCESS")) {   session.removeAttribute("CartPymtArr"); } %>
<%@ include file="/pages/GG_Footer.jspf" %>

            