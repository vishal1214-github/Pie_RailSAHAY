<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Temprory Issue In RAIL SAHAY</title>
</head>

<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="/RailSAHAY/resources/js/jquery_bootstrap.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
<script>
function gotoHome()
{

	$(location).attr('href', '/RailSAHAY/index.jsp');
}
</script>
</head>

<body>

<div align="center">
&nbsp;<p>&nbsp;</p>
	<table border="0" width="1000px" cellspacing="5" cellpadding="5">
		<tr>
			<td rowspan="2">
			<img border="0" src="/RailSAHAY/resources/images/Warning.png"></td> 
			<td><font face="Arial" style="font-size:16px;font-weight:bold;"><i>The system has encountered some server issue at the moment that your request could not be fulfilled!!</i></font><br/>
			<font style="font-size:17px;font-family:arial;">Please try after some time, if the issue persists for long, please contact OCC/CRIS.</font>
			<br/><br/>
			<button class="btn btn-danger" onclick="gotoHome();">Goto Home Page</button>
			</td>
		</tr>
		<tr>
			 
		</tr>
	</table>
</div>

</body>

</html>

