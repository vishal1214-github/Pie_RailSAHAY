<%
	String strENCData=(String)request.getParameter("encdata").trim();
	String strMerchantCode=(String)request.getParameter("mccode").trim();
	String trxnID=(String)request.getParameter("trxnid").trim();
	String strURL=(String)request.getParameter("url").trim();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"  href="/RailSAHAY/resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">

    <!-- Google font (font-family: 'Roboto', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto Condensed', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700" rel="stylesheet">

<link rel="stylesheet"  href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/jquery-3.3.1.min.js"></script>
<script src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
<script>
$(document).ready(function()
{
/*setTimeout(function(){ $("#frmFrgtPymt").submit(); }, 5000);*/
	$("#frmFrgtPymt").submit();
});
</script>
</head>
<body>
	<form id="frmFrgtPymt" name="frmFrgtPymt" action="<%=strURL%>" method="post">
		<input type="hidden" id="encdata" name="encdata" value="<%=strENCData%>" />
		<input type="hidden" id="merchant_code" name="merchant_code" value="<%=strMerchantCode%>" />
		<input type="hidden" id="trxnID" name="trxnID" value="<%=trxnID%>" />
	</form>
	<table class="table table-striped table-bordered" style="margin:1.5rem;width:100%;">
	<thead>
	<tr><td class="p-direct-head">Indian Railways- Freight Business Development Portal</td></tr>
	</thead>
	<tbody>
	<tr>
	<td>
	<div class="text-center">
	<div class="spinner-border text-danger"></div>
	<p class="p-direct">You're being redirected to Payment Gateway, Please wait...</p>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
</body>
</html>