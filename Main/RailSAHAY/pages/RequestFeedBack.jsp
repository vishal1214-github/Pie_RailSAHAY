
<%@ include file="/pages/GG_Header.jspf" %>
<%

      String CRid=(String)request.getParameter("REF");
      
      if(CRid==null){
      CRid=(String)request.getAttribute("REF");
                    
      }
      
       if(CRid==null){
        CRid="";                    
      }
      System.out.println("ref:"+CRid);
      
     String si_ClntID		= (String) session.getAttribute("ClntID");
      	            model.GG_TrmlUtilSrvc obj=new model.GG_TrmlUtilSrvc("test",si_ClntID);
	                    String strSrvcData[][]=null;
	            try
	            {
	                    
	                    String strData="";
	                    strSrvcData=obj.trackConcern(CRid);
                            
                                                       
                            
                    }catch(Exception ex){
                    ex.printStackTrace();
                    }
                    
                    
    String CRFb=(String)request.getAttribute("CRFb");            
      String CRreply=(String)request.getAttribute("CRreply");
      String CRerror=(String)request.getAttribute("CRerror");
      
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/rating.css">

<script>
$(document).ready(function() {
$("#errorMesgDiv").hide();
if('<%=CRFb%>'=="CRFB"){
 if('<%=CRreply%>'!="FAIL") {
                           htmlA="<strong>Success!</strong> FeedBack for your Request with Reference No: <%=CRid%> has been successfully submitted."; 
                           console.log(htmlA);
                         custPrompt(htmlA);
                          $("#sucsMesgDiv").html(htmlA);
                           document.getElementById('sucsMesgDiv').style.display="block";
                           document.getElementById('sbmtBt').disabled=true;
                           
                          }else{
                          
                           htmlA="<strong>Error!</strong>"+"<%=CRerror%>"; 
                            $("#errorMesgDiv").html(htmlA);
                           document.getElementById('errorMesgDiv').style.display="block";
                        
                        }
}
});

function validateCaptcha()
      {
		          

var capt=(document.getElementById('captcha').value);
  var err=0;
        var rsltCap = $("#answer").val();
        if (rsltCap !== '') {

            function success(data, textStatus, jqXHR) {
            if (data.indexOf("N") >  - 1) {
            document.getElementById('errorMesgDiv').innerHTML="Captcha Code doesn't Match.";
            document.getElementById('errorMesgDiv').style.display="block";
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
        document.getElementById('errorMesgDiv').innerHTML="Enter Captcha Verification Code.";
            document.getElementById('errorMesgDiv').style.display="block";
            err++;
        }
     // alert(err);
    if (err == 0) {
        if(document.getElementById('star1').checked ||document.getElementById('star2').checked || document.getElementById('star3').checked || document.getElementById('star4').checked || document.getElementById('star5').checked ){
       document.frmInpt.submit();
        }else{
            //alert(document.getElementById("full_mobile").value);
             document.getElementById('errorMesgDiv').innerHTML="Please Provide Rating";
            document.getElementById('errorMesgDiv').style.display="block";
             return false;
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
</script>


 
 
<form class="form-horizontal" method="post" action="/RailSAHAY/RaiseSR"    id="frmInpt" name="frmInpt" >
      <div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3><%=E.EH("HELP US TO SERVE YOU BETTER")%> !</h3>
	                          <p><%=E.EH("Share your experience with us")%>..</p>
						  	</div>
	                      <div class="col-md-9 register-right">
	                          <div class="tab-content" id="myTabContent">
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                  <h3 class="register-heading d-sm-none d-md-block d-lg-block d-xl-block"><%=E.EH("Welcome")%> <%=strSrvcData[0][5]%> <%=strSrvcData[0][6]%></h3><br/><br/>
	                                  <div class="row" style="margin-top:50px;">
	                                     </div>
<div class="container1" style="margin-top:58px;display: flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>
<div class="row" id="divRating">
	<div class="col-md-6 offset-md-3">
		



<br/><br/>
<h1 class="text-center" alt="Simple">Based on your experience with this Request(Ref Id:<span style="color:red;"> <%=CRid%> </span>), Please Rate Us!</h1> <br/>
<div class="container">
        <div class="starrating risingstar d-flex justify-content-center flex-row-reverse">
            <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 star">5</label>
            <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 star">4</label>
            <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 star">3</label>
            <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 star">2</label>
            <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star">1</label>
        </div>
  </div>	
	</div>
</div>
                                                                       
	                                 
                                          
									<div class="row">
	                                      <div class="offset-md-2 col-md-8">
									<div class="form-group">
									<textarea class="form-control" tabindex="9" id="mesg" name ="mesg" rows="6" cols="80" placeholder='<%=E.EH("Write your message (Max 1000 Words)")%>' value=""   maxlength="1000"   ></textarea>
									</div>
                                                                        <div   class="form-label-group">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="10" class="form-control" placeholder='<%=E.EH("Captcha")%>' required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
		               <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		              </div>
									<div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="11" id="sbmtBt"  value="Submit" onclick="return validateCaptcha();"><%=E.EH("Submit")%></button>
									</div>
                                                                       <input type="hidden" id="crId" name="crId" value="<%=CRid%>" />
                                                                       <input type="hidden" id="SRFlag" name="SRFlag" value="FDB" />
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
       

   <%@ include file="/pages/GG_Footer.jspf" %>

