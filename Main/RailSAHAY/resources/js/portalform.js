$(document).ready(function() {
    $("#imgReloadCaptcha").click(function(e) {   		
	 	refreshCaptcha();
	 	$("#captchaText").val('');
	 	$("#captchaText").focus();	 	
    });
});
function callValidateCaptcha()
{
	     var err=0;
	     var rsltCap = $("#captchaText").val();
	     if (rsltCap !== '') 
	     {
	         function success(data, textStatus, jqXHR)
	         {
	             if (data.indexOf("N") >  - 1) 
	             {
	             	document.getElementById("errmsg").innerHTML = "Captcha Code doesn't Match";  
	        		document.getElementById("errmsg").style.display = "block";
					document.getElementById("imgLdngData").style.display = 'none';
	              //   alert("Captcha Code doesn't Match.");
	                 err++;
	             }
	         }
	         function error(jqXHR, textStatus, errorThrown) 
	         {
	            document.getElementById("errmsg").innerHTML = textStatus;
	        	document.getElementById("errmsg").style.display = "block";
				document.getElementById("imgLdngData").style.display = 'none';
	            // alert("ERROR:: " + textStatus);   
	             err++;
	         }
	         $.ajax
	         ({
	             url : "/RailSAHAY/matchcaptcha", type : "post", async : false, data : 
	             {
	             result : rsltCap
	             },dataType : "text", success : success, error : error
	         });
	     }
	     else 
	     {
	         document.getElementById("errmsg").innerHTML = "Please Enter Captcha Verification Code";
	         document.getElementById("errmsg").style.display = "block";
			 document.getElementById("imgLdngData").style.display = 'none';
	         //alert('Please Enter Captcha Verification Code');
	         err++;
	     }
	     if (err == 0)
	     {
	     	return true;
	     }
	      refreshCaptcha();
	      $("#captchaText").val('');
	      $("#captchaText").focus();
	      $("#captchaText").select();  
	      return false;
	    }
 function refreshCaptcha()
 {
 	var d = new Date();
 	document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();
 }