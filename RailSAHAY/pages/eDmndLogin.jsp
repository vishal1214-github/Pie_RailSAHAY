<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"EDMND LOGIN","/RailSAHAY/pages/eDmndLogin.jsp",UserDevice,Browser);
%>
<%
	String strMessage="";
        if((String)request.getAttribute("message")!=null)
            strMessage =(String)request.getAttribute("message");
            
       System.out.println(strMessage);     
      
       String strLgnThemeCSS="edmndlogin";
if(si_Theme.equals("GR")) {  strLgnThemeCSS="edmndloginGreen"; }
if(si_Theme.equals("BL")) {  strLgnThemeCSS="edmndlogin";  }
if(si_Theme.equals("RD")) { strLgnThemeCSS="edmndloginRed";  }
if(si_Theme.equals("OR")) {  strLgnThemeCSS="edmndloginOrange";  }
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/Font2.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/theme/<%=strLgnThemeCSS%>.css">
    
      <script type="text/javascript">
      function validateEmail(mail) 
        {
         if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
          {
            
            return (true)
          }
              
            return (false)
        }
      
    function refcapfunc(){
          //  alert("HareKrishna");
             var d = new Date();
      //  $("#captcha").attr("src", "/foisEfnote/simpleCaptcha.jpg?"+d.getTime());
        document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();

           //  document.getElementById("captcha").src ='/foisEfnote/simpleCaptcha.jpg';
         //    alert("AFTER CALL");
        }
        
 function submitForm()
      {
        var username=(document.getElementById('inputEmail').value);
        var pwd=(document.getElementById('inputPassword').value);
         var capt=(document.getElementById('captcha').value);
	 //alert(username);	 
		
        if((username=="")||(pwd=="")) 
        {            
		   document.getElementById('errorMesgDiv').innerHTML="Please Enter Username/Password";
		   document.getElementById('errorMesgDiv').style.display="block";
            return  false;
        }
        var err=0;
        var rsltCap = $("#answer").val();
        if (rsltCap !== '') {

            function success(data, textStatus, jqXHR) {
            if (data.indexOf("N") >  - 1) {
                    alert("Captcha Code doesn't Match.");
                    err++;
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
        else {
            alert('Enter Captcha Verification Code.');
            err++;
        }
     // alert(err);
    if (err == 0) {
            
                document.forms[0].submit();
           
                
            
        }
        else{
            return false;
        }
      } 
      
   
   </script>
<script>
$(document).ready(function()
{
        //alert("Onload");
	initFunction();
});
function initFunction() {
	<% if(!strMessage.equals(""))
        {%>
    document.getElementById('errorMesgDiv').innerHTML="<%=strMessage %>";
    document.getElementById('errorMesgDiv').style.display="block";
    <%}%>
    };
</script>
  
		<!-- This snippet uses Font Awesome 5 Free as a dependency. You can download it at fontawesome.io! -->

		  <div class="container">
		    <div class="row">
		      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
		        <div class="card card-signin my-5">
		          <div class="card-body">
		            <h5 class="card-title text-center">Customer Log-In</h5>
		            <form action="/RailSAHAY/login" name="frmLogin" id ="frmLogin"  method="post" >
		              <div class="form-label-group">
		                <input type="text" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
		                <label for="inputEmail">User Name</label>
		              </div>
		              <div class="form-label-group">
		                <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
		                <label for="inputPassword">Password</label>
		              </div>
                               <div   class="form-label-group">
		                <input type="text" name="answer" id="answer" class="form-control" placeholder="Captcha" required>
                                <label for="answer">Captcha</label>
                              </div>
		               <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" ></img> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" ></img>
		              </div>
		              <!--div class="custom-control custom-checkbox mb-3">
		                <input type="checkbox" class="custom-control-input" id="customCheck1">
		                <label class="custom-control-label" for="customCheck1">Remember password</label>
		              </div-->
                             <br/>
                            <button class="btn btn-lg btn-primary btn-block text-uppercase"  onclick="return submitForm();"  >Sign in </button>
                                <div class="alert alert-danger" id="errorMesgDiv" style="display:none;"></div>
		            </form>
                            <br/>
                           
                            <div class="btn-group" style="width: 100%; margin: 0px auto;">
                            <table class="table table-borderless" >
                            <tr>
                            <td class="align-left">
                            <a  class="btn btn-light btn-sm float-light" style="text-align:left" href="/foisEfnote/OpenPages/RgtrUserN.jsf" target="_blank">Sign Up</a>
                            </td>
                            <td class="align-right">
                            <a  class="btn btn-light btn-sm float-light" style="text-align:right"  href="/foisEfnote/OpenPages/FrgtPswd.jsf" target="_blank">Forget Password</a>
                            </td>
                            </tr>
                            </table>
                            </div>
                          
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
	    <script src="/RailSAHAY/resources/js/main.js"></script>

	     <%@ include file="/pages/GG_Footer.jspf" %>
