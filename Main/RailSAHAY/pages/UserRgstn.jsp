
<%@ include file="/pages/GG_Header.jspf" %>
<%
String strStts="";
String strerr="";
try
{
//	strStts=((String)request.getAttribute("TRXNSTTS")).trim();
//	strRefID=((String)request.getAttribute("REF_ID")).trim();
//	strEror=((String)request.getAttribute("ERROR")).trim();

        strerr =(String)request.getParameter("errr");
        System.out.println(strerr);
        
}
catch(Exception e)
{
	
	strerr="";
	
}
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">
<link rel="stylesheet" href="/RailSAHAY/resources/js/intlDate/css/intlTelInput.css">
<link rel="stylesheet" href="/RailSAHAY/resources/js/intlDate/css/demo.css"> 
<script type="text/javascript"> 
window.onload=function() { 
 
	document.body.scroll = "yes";
            <% String strEror   = (String) request.getAttribute("Error");
            String strErorField = (String) request.getAttribute("ErrorField");%>
          //alert('<%=strerr%>');
           <% if(strerr != null)
            {%>
            
                //show_popup("<%=strErorField%>", "<%=strerr%>");
                alert('<%=strerr%>');
                var rply='<%=strerr%>';
                if(rply.indexOf("User Registration in FBD Module of IR/FOIS Completed")>-1)
                {
                window.close();
                }
            <%}%>
            <% if(strEror != null)
            {%>
            
                show_popup("<%=strErorField%>", "<%=strEror%>");
            <%}
            else
            {%>
        //    document.getElementById('progressImage').style.visibility   = "hidden";
           // initValues();                       
            <%}%>
} 
</script>
<script>
$(document).ready(function() {
$("#errorMesgDiv").hide();
       document.getElementById('sbmt').style.display='block'; 
      $("#mobile").on("blur", function(){  	$("#full_mobile").val("+" + $(".iti__country[class*='iti__active']").attr("data-dial-code") );  });
});

function validateCaptcha()
{
    var capt        = (document.getElementById('captcha').value);
    var email       = (document.getElementById('email').value);
    var mobile      = (document.getElementById('mobile').value);
    var firstname   = (document.getElementById('firstname').value);
    var lasttname   = (document.getElementById('lastname').value);
    var password = $("#Pswd").val();
    var confirmPassword = $("#CPswd").val();
    var err=0;
    var rsltCap = $("#answer").val();
    if (firstname == ''){
         document.getElementById('errorMesgDiv').innerHTML="First Name is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (lasttname == ''){
         document.getElementById('errorMesgDiv').innerHTML="Last Name is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (email == ''){
         document.getElementById('errorMesgDiv').innerHTML="Email is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (mobile == ''){
         document.getElementById('errorMesgDiv').innerHTML="Mobile is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (password == ''){
         document.getElementById('errorMesgDiv').innerHTML="Password is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (confirmPassword == ''){
         document.getElementById('errorMesgDiv').innerHTML="Confirm Password is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
    if (password != confirmPassword) {
        //alert("Passwords do not match.");
        document.getElementById('errorMesgDiv').innerHTML="Passwords do not match.";
        document.getElementById('errorMesgDiv').style.display="block";
        return false;
    }
    if(email !== '')
        {
            //alert("Reached to:-"+$('#email').val());
            function successemail(data, textStatus, jqXHR) {
                  //alert("Reached Here email:-"+data);
                if (data.indexOf("N") >  - 1) {
                
                 document.getElementById('errorMesgDiv').innerHTML="This Email Is Already registered.";
                 document.getElementById('errorMesgDiv').style.display="block";
                 //alert("This Email Is Already registered.");
                    err++;
                    return false;
                }
    
            }
    
            function erroremail(jqXHR, textStatus, errorThrown) {
                alert("ERROR:: " + textStatus);
                err++;
            }
            try {
                $.ajax( {
                    url : "/RailSAHAY/VldtEmail", type : "post", async : false, data :  {
                        email : $('#email').val()
                    },
                    dataType : "text", success : successemail, error : erroremail
                });
            }
            catch (e) {
                alert("Error11::" + e);
            }
        }
        
    if(mobile !== '')
        {
            //alert("Reached to:-"+$('#email').val());
            function successemobile(data, textStatus, jqXHR) {
                //alert("Reached Here mobile:-"+data);
                if (data.indexOf("N") >  - 1) {
                
                 document.getElementById('errorMesgDiv').innerHTML="This Mobile Number Is Already registered.";
                 document.getElementById('errorMesgDiv').style.display="block";
                 //alert("This Mobile Number Is Already registered.");
                    err++;
                    return false;
                }
    
          }
    
            function erroremobile(jqXHR, textStatus, errorThrown) {
                alert("ERROR:: " + textStatus);
                err++;
            }
            try {
                $.ajax( {
                    url : "/RailSAHAY/VldtMobile", type : "post", async : false, data :  {
                        mobile : $('#mobile').val()
                    },
                    dataType : "text", success : successemobile, error : erroremobile
                });
            }
            catch (e) {
                alert("Error11::" + e);
            }
        }
    if(password !== '')
    {
            //alert("Reached to:-"+$('#email').val());
            function successepass(data, textStatus, jqXHR) {
                //alert("Reached Here mobile:-"+data);
                if (data.indexOf("N") >  - 1) {
                
                 document.getElementById('errorMesgDiv').innerHTML="Password is Not valid.";
                 document.getElementById('errorMesgDiv').style.display="block";
                 //alert("This Mobile Number Is Already registered.");
                    err++;
                    return false;
                }
    
          }
    
            function errorepass(jqXHR, textStatus, errorThrown) {
                alert("ERROR:: " + textStatus);
                err++;
            }
            try {
                $.ajax( {
                    url : "/RailSAHAY/VldtPassword", type : "post", async : false, data :  {
                        password : $("#Pswd").val()
                    },
                    dataType : "text", success : successepass, error : errorepass
                });
            }
            catch (e) {
                alert("Error11::" + e);
            }
        }
   
    if (rsltCap == ''){
         document.getElementById('errorMesgDiv').innerHTML="Captcha Code is Mandotry.";
         document.getElementById('errorMesgDiv').style.display="block";
         return false;  
    }
        if (rsltCap !== '') {
           function success(data, textStatus, jqXHR) {
                if (data.indexOf("N") >  - 1) {
                document.getElementById('errorMesgDiv').innerHTML="Captcha Code doesn't Match.";
                document.getElementById('errorMesgDiv').style.display="block";
                        err++;
                        return false;
                    }
                }
            function error(jqXHR, textStatus, errorThrown) {
                alert("ERROR:: " + textStatus);
                err++;
            }
            $.ajax( {
                url : "/RailSAHAY/matchcaptcha", type : "post", async : false, data :  {
                    result : rsltCap
                },
                dataType : "text", success : success, error : error
            });
        }
      //alert(err);
    if (err == 0) {
        if(document.getElementById("mobVldt").value=='N'){
            //alert(document.getElementById("full_mobile").value);
            document.getElementById('errorMesgDiv').innerHTML="Please Provide Valid Mobile No.";
            document.getElementById('errorMesgDiv').style.display="block";
             return false;
        }
            else{
                document.frmInpt.submit();
            }
        }
    else{
        return false;
        }
} 
function refcapfunc(){
    var d = new Date();
    document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();
}
function hideMesg(){
    document.getElementById('errorMesgDiv').style.display="none";
    document.getElementById('sucsMesgDiv').style.display="none";

}
</script>
     <form class="form-horizontal" method="post" action="/RailSAHAY/UserRgtn"  id="frmrgtn" name="frmrgtn" >
      <div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3>User Registration !</h3>
	                         
						  	</div>
	                      <div class="col-md-9 register-right">
                             
	                          <div class="tab-content" id="myTabContent">
                                  
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                 
	                          <h3 class="register-heading d-sm-none d-md-block d-lg-block d-xl-block">FBD User Registration Form</h3>
                                <br/>
<div class="container1" style="display:flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>

                                                                         
	                                  <div class="row register-form">
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                              <input type="text" class="form-control inptcap name" placeholder="First Name *" value="" tabindex="1" id="firstname" name="firstname" required="required"  maxlength="30"  onclick="hideMesg()"; />
	                                          </div>
	                                          <div class="form-group">
                                                             <input type="email" id="email" name="email" tabindex="3" placeholder="Your Email *" value=""   maxlength="40" required="required"    style="text-transform:lowercase;" class="form-control inptcap excludechr">
	                                            </div>
	                                        <div class="form-group">
	                                          	  <input type="password" class="form-control inptcap excludechr" tabindex="5" placeholder="Password *" value="" id="Pswd" name="Pswd"   required="required"  minlength="8"  maxlength="20"    /> 
	                                          </div>
	                                      </div>
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                          	  <input type="text" class="form-control inptcap name" tabindex="2" placeholder="Last Name *" value="" id="lastname" name="lastname"    required="required"   maxlength="30"   /> 
	                                          </div>
	                                          <div class="form-group">
	                                              <input  type="tel"  tabindex="4" name="mobile" id ="mobile" class="form-control inptcap numb" maxlength="10" value="" required="required" />
                                                      <input type="hidden" name="full_mobile" id="full_mobile"  />
	                                          </div>
	                                        <div class="form-group">
	                                          	  <input type="password" class="form-control inptcap excludechr" tabindex="6" placeholder="Confirm Password *" value="" id="CPswd" name="CPswd" required="required" minlength="8"  maxlength="20"    /> 
	                                          </div>
	                                      </div>
                                              <ul>
                                                <li>Password is case-sensitive.</li>
                                                <li>A password needs to be between 8-20 characters. It should be a combination of upper
                                    and lower case letters and must contain atleast one UpperCase alphabet,one LowerCase alphabet one number and one
                                    special character from the list "@#$%".</li>
                                                <li>Space is not allowed in password.</li>
                                                <li>The New Password and the Confirm Password should be same.</li>
                                                </ul>
	                                  </div>
                                          <div class="row">
	                                      <div class="offset-md-2 col-md-8">
                                           <div   class="form-label-group no-user">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="7" class="form-control" placeholder="Captcha" required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
		               <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
                               
		              </div>
                              
									<div class="d-flex justify-content-center mb-10 ">
									<button class="btn btn-primary float-right" id="sbmt" tabindex="8"  value="Submit" onclick="return validateCaptcha();">Submit</button>
									</div>
                                                                         <input type=hidden id="mobVldt" name="mobVldt" value="N">
									<br/>
									</div>
									</div>
	                              </div>
	                             
	                          </div>
	                      </div>
	                  </div>

            </div>
            	
            </form>
   <script type="text/javascript" src="/RailSAHAY/resources/js/GG_StopRefresh.js"></script>
   <script src="/RailSAHAY/resources/js/intlDate/js/intlTelInput.js"></script>
  <script>
    var input = document.querySelector("#mobile");
    var itii= window.intlTelInput(input, {
      // allowDropdown: false,
      // autoHideDialCode: false,
      // autoPlaceholder: "off",
      // dropdownContainer: document.body,
      // excludeCountries: ["us"],
      // formatOnDisplay: false,
      // geoIpLookup: function(callback) {
      //   $.get("http://ipinfo.io", function() {}, "jsonp").always(function(resp) {
      //     var countryCode = (resp && resp.country) ? resp.country : "";
      //     callback(countryCode);
      //   });
      // },
       hiddenInput: "full_mobile",
       initialCountry: "in",
      // localizedCountries: { 'de': 'Deutschland' },
      // nationalMode: false,
      // onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
      // placeholderNumberType: "MOBILE",
      // preferredCountries: ['cn', 'jp'],
       separateDialCode: true,
      utilsScript: "/RailSAHAY/resources/js/intlDate/js/utils.js"
    });
    
    
    var reset = function() {
  document.getElementById("mobVldt").value="N";
};

// on blur: validate
input.addEventListener('blur', function() {
  reset();
  if (input.value.trim()) {
    if (itii.isValidNumber()) {
     document.getElementById("mobVldt").value="Y";
    } else {
      document.getElementById("mobVldt").value="N";
    }
  }
});

// on keyup / change flag: reset
//input.addEventListener('change', reset);
//input.addEventListener('keyup', reset);
  </script>
   <%@ include file="/pages/GG_Footer.jspf" %>

