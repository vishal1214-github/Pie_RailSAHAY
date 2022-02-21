<%
	String ChrgId=(String)request.getParameter("ChrgId").trim();
	String PymtStts=(String)request.getParameter("PymtStts").trim();
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
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/homepage.js"></script>
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/all.min.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/sahaydb/font-awesome/css/font-awesome.min.css">

<script>
$(document).ready(function()
{
	fetchChrgDescFailed('<%=ChrgId%>');
	 var ChrgId='<%=ChrgId%>';
	var bc = new BroadcastChannel('fbd_channel');
	bc.postMessage(ChrgId+':FAILED'); 	 	
});
</script>
</head>
<body>
<div class="container">
<p class="p-direct-head" style="margin-top:1rem;">Indian Railways- Freight Business Development Portal</p>
<hr/>
<p class="p-direct-head1" style="margin-top:0.5rem;">Payment of Outstanding Charges</p>
<div class="alert alert-danger alert-dismissible">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
<table class="table" style="margin-bottom:0px;">
	<tr><td>Payment Status</td><td><%=PymtStts%></td></tr>
</table>
</div>
<div id="divErorHead">
<p class="trxn-failed-head"><i class="fas fa-exclamation-triangle"></i>&nbsp;&nbsp;Something went wrong with this payment transaction technically !</p>
<p class="trxn-failed-head1">Please try after some time</p>
</div>
<p class="mesg-cnt">For any query/assistance <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;Contact Us</a></p>

<div id="divChrgDesc" class="chrg-desc" style="padding:10px;"></div>
</div>
</body>
</html>