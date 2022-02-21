<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>FBD Registration User Activation</title>

<link href="/RailSAHAY/resources/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/RailSAHAY/resources/js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/dynsections.js"></script>
<link href="/RailSAHAY/resources/css/navtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/RailSAHAY/resources/js/resize.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/navtree.js"></script>
<link href="/RailSAHAY/resources/css/mainpage.css" rel="stylesheet" type="text/css" />
<link href="/RailSAHAY/resources/css/extra.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(initResizable);
	$(window).load(resizeHeight);
</script>




</head>
<body>
	<div id="top">
			<!-- do not remove this div, it is closed by shailesh! -->
		<div id="titlearea">
			<table class="pageheader" cellpadding="0" cellspacing="0" style="height: 70px;">
                        <tbody>
                            <tr style="height: 70px;">
                                <td id="projectlogo">
                                    <img src="/RailSAHAY/resources/images/head_left1.jpg" alt="" height="70px;"  border="0"/>
                                </td>
                                <td align="center" valign="middle" width="100%"  >
                                    <!--<img src="/RailSAHAY/resources/images/head_main.jpg" alt="" height="70px;"  border="0"/>-->
                                    <h3>Welcome to Indian Railways Freight Services</h3>
                                </td>
                                <td align="right" valign="middle">
                                     <img src="/RailSAHAY/resources/images/head_right1.jpg" alt="CRIS" height="70px;" border="0"/>
                                 </td>
                            </tr>
                         </tbody>
                    </table>
			
		</div>
		<!-- end header part -->
		
	</div>
	<!-- top -->

<div  class="contents">
	<div style="margin-left: 189px; height: 402px;width:100%" id="doc-content">
		<div class="header" align="center">
			<div class="headertitle" style="width:100%">
				<div class="title">Welcome,${name}
				 
				
				</div>
			</div>
		</div>
		<!--header-->
		
			<div class="textblock">
			
				<h2 id="personal" style="background-image:url('/RailSAHAY/resources/images/gradiant.png') ;
  					background-size:100% 20px;
   					background-repeat: repeat-y;">
				Activate Your Account </h2>
				<table align="center" >
				<tr>
				<td >Welcome Back,</td>
				<td align="left"></td>
				<td align="left"></td>
				<td></td>
				<td></td>
                                </tr>
				<tr>
				<td></td>
				<td align="left"></td>
				<td align="left">Your Account Details are as follows:-</td>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td></td>
				<td align="left">1.</td>
				<td align="left">Registration Number:</td>
				<td align="left">${user}</td>
				<td></td>
				</tr>
				<tr>
				<td></td>
				<td align="left">2.</td>
				<td align="left">Registered EmailID:</td>
				<td align="left">${email}</td>
				<td></td>
				</tr>
				<tr>
				<td></td>
				<td align="left"></td>
				<td align="left">Please Enter Verification code sent to your mobile number</td>
				<td></td>
				<td></td>
				</tr>
                                <tr>
				<td></td>
				<td align="left">OTP:</td>
				<td align="left"><input type="password" name="txtotp" id="txtotp" /></td>
				<td>
                              </td>
				<td></td>
				</tr>
                                 <tr>
				<td align="center" colspan="5">
                                </td>
                                </tr>
                                <tr>
				<td align="center" colspan="5">&nbsp;
                                </td>
                                </tr>
                                <tr>
				<td align="center" colspan="5">
                                </td>
                                </tr>
                                <tr>
				<td align="center" colspan="5">
                                </td>
                                </tr>
				 <tr>
				<td align="center" colspan="5">
                                   <input type="button" value="Activate Account" name="btsbmt" id="btsbmt" ></input>
                                <input type="button" value="Re-Generate OTP" name="btotp" id="btotp" ></input>
                               
                                </td>
                                
				
				</tr>
				</table>
                                
			</div>
			
	</div>
        </div>
	<script type="text/javascript">
         function success(data, textStatus, jqXHR)
            {
                    alert(data);
                    $("#txtotp").prop('disabled', false);
                    $("#btsbmt").prop('disabled', false);
                    $("#btotp").prop('disabled', false);
            }

            function error(jqXHR, textStatus, errorThrown)
            {
                alert("ERROR:: "+textStatus);
                  $("#txtotp").prop('disabled', false);
                    $("#btsbmt").prop('disabled', false);
                    $("#btotp").prop('disabled', false);
            }
        (function() {
            $("#btotp").click(function() {
           //alert('HareKrishna');
           $("#btotp").prop('disabled', true);
         
                var user = '${user}';
                var alt = 'P';
                //alert(user);
                $.ajax({
                    url : "/RailSAHAY/gnrtotp",
                    type: "post",
                    data:{user: user, altr:alt},                  
                    dataType: "text",                   
                    success: success,
                    error: error
                });
            });
            
            $("#btsbmt").click(function() {
              $("#txtotp").prop('disabled', true);
                    $("#btsbmt").prop('disabled', true);
                    $("#btotp").prop('disabled', true);
                var otp = $("#txtotp").val();
                var user = '${user}';
                var email = '${email}';
                
                var fullname = '${name}';
                $.ajax({
                    url : "/RailSAHAY/actvtuserotp",
                    type: "post",
                    data: {user: user, otp: otp, email: email,fullname: fullname},                 
                    dataType: "text",                   
                    success: success,
                    error: error
                });
            });

           
        })();
    </script>

	
</body>
</html>