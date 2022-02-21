<!doctype html>
<%
String strStts="";
String strRefID="";
String strEror="";
try
{
	strStts=((String)request.getAttribute("TRXNSTTS")).trim();
	strRefID=((String)request.getAttribute("REF_ID")).trim();
	strEror=((String)request.getAttribute("ERROR")).trim();
}
catch(Exception e)
{
	strStts="";
	strRefID="";
	strEror="";
	
}
%>
<%@ include file="/pages/GG_Header.jspf" %>

<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"LODGE YOUR CONCERN","/RailSAHAY/pages/LodgeConcern.jsp",UserDevice,Browser);
%>

<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">

	

<script>
$(document).ready(function() {
$( "input" ).keypress(function() {
  $("#errorMesgDiv").val("");
    document.getElementById('errorMesgDiv').style.display="none";
  $("#sucsMesgDiv").val("");
    document.getElementById('sucsMesgDiv').style.display="none";

});
	copyVal("QUERY");
});
function validateLocn()
{
	
        	var sttndesc=$("#locndesc").val();
		var sttncode='';
		if(sttndesc.indexOf("-")>-1)
		{
			var sttn=sttndesc.substring(sttndesc.indexOf("-")+1);
			sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
		}	
		else
		{
			sttncode=sttndesc.toUpperCase();
		}
		$("#locn").val(sttncode);

}
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
            document.forms[0].submit();
        }
        else{
            return false;
        }
      } 
</script>
<script>
function refcapfunc(){
              var d = new Date();
          document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();

            }
        
      
function copyVal(val)
{
document.getElementById('errorMesgDiv').style.display="none";
     document.getElementById('sucsMesgDiv').style.display="none";
 document.getElementById('hidval').value = val;
 $(".btn-concern").removeClass("btn-success");
 $(".btn-concern").addClass("btn-primary");

if(val=="QUERY")
{
	$(".register-heading").html("Connect for your Query");
	$("#btnQ").removeClass("btn-primary");	
	$("#btnQ").addClass("btn-success");
	$("#cncrctgr").empty();
	$("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
	$("#cncrctgr").append('<option value="Booking">Booking</option>');
	$("#cncrctgr").append('<option value="Delivery">Delivery</option>');
	$("#cncrctgr").append('<option value="Track_Trace">Track and Trace Facility</option>');
	$("#cncrctgr").append('<option value="Station_Facilities">Station Facilities</option>');
	$("#cncrctgr").append('<option value="Station_Open_Goods_Traffic">Station Open Goods Traffic</option>');
	$("#cncrctgr").append('<option value="Commodity">Commodity</option>');
	$("#cncrctgr").append('<option value="Wagon_Type">Wagon Type</option>');
	$("#cncrctgr").append('<option value="NearestGoodsShed">Nearest Goods Shed</option>');
	$("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
	$("#cncrctgr").append('<option value="Freight_Schemes">Freight Schemes</option>');
	$("#cncrctgr").append('<option value="Dummurage">Demmurage</option>');
	$("#cncrctgr").append('<option value="Wharfage">Wharfage</option>');
	$("#cncrctgr").append('<option value="Others">Others</option>');

}
if(val=="SUGGESTION")
{
	$(".register-heading").html("Connect for your Suggestion");
	$("#btnS").removeClass("btn-primary");	
	$("#btnS").addClass("btn-success");
	$("#cncrctgr").empty();
	$("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
	$("#cncrctgr").append('<option value="Booking">Booking</option>');
	$("#cncrctgr").append('<option value="Delivery">Delivery</option>');
	$("#cncrctgr").append('<option value="Station_Facilities">Station Facilities</option>');
	$("#cncrctgr").append('<option value="Station_Open_Goods_Traffic">Station Open Goods Traffic</option>');
	$("#cncrctgr").append('<option value="Wagon_Type">Wagon Type</option>');
	$("#cncrctgr").append('<option value="NearestGoodsShed">Nearest Goods Shed</option>');
	$("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
	$("#cncrctgr").append('<option value="Freight_Schemes">Freight Schemes</option>');
	$("#cncrctgr").append('<option value="Dummurage">Demmurage</option>');
	$("#cncrctgr").append('<option value="Wharfage">Wharfage</option>');
	$("#cncrctgr").append('<option value="Security">Security</option>');
	$("#cncrctgr").append('<option value="Others">Others</option>');
}

if(val=="GRIEVANCE")
{
	$(".register-heading").html("Connect for your Grievance");
	$("#btnG").removeClass("btn-primary");	
	$("#btnG").addClass("btn-success");
	$("#cncrctgr").empty();
	$("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
	$("#cncrctgr").append('<option value="Employee_Behaviour">Behaviour of Employee</option>');
	$("#cncrctgr").append('<option value="Platform_Surface">Platform Surface</option>');
	$("#cncrctgr").append('<option value="Goods_Shed_Lighting">Lighting in Goods Shed</option>');
	$("#cncrctgr").append('<option value="Approach_Road">Approach Road Condition</option>');
	$("#cncrctgr").append('<option value="Consignment_Damage">Damage of Consignment</option>');
	$("#cncrctgr").append('<option value="Consignment_Loss">Loss of Consignment</option>');
	$("#cncrctgr").append('<option value="Corruption">Corruption</option>');
	$("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
	$("#cncrctgr").append('<option value="Security">Security</option>');
	$("#cncrctgr").append('<option value="Others">Others</option>');
}
}


function hideMesg()
{
    document.getElementById('errorMesgDiv').style.display="none";
     document.getElementById('sucsMesgDiv').style.display="none";

}
  

function validateEmail(mail) 
{
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(frmInpt.email.value))
  {
    document.frmInpt.mobile.focus();
    return (true)
  }
       document.getElementById('errorMesgDiv').innerHTML="Please enter a valid email address!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.email.value = "";
    document.frmInpt.email.focus();
    return (false)
}
function validateMob(mob)
{
  val = frmInpt.mobile.value;
  if(/^\d{10}$/.test(val))
  {
    document.frmInpt.cncrctgr.focus();
    return (true)
  }
   document.getElementById('errorMesgDiv').innerHTML="Please enter a valid mobile number!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.mobile.value = "";
    document.frmInpt.mobile.focus();
    return (false)
}
function validateName()
{
 var val = frmInpt.firstname.value;
 val1=val.trim();

  if ((val1=='')||(val1=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Name!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.firstname.value = "";
    document.frmInpt.firstname.focus();
    return (false)
  }
  else
   {
    document.frmInpt.lastname.focus();
    return (true)
   }
   
}
function validateMesg(mesg)
{
  var val = frmInpt.mesg.value;
   val1=val.trim();
 
  var len1=val1.length;
 
  if ((val1=='')||(val1=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Message!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.mesg.value = "";
    document.frmInpt.mesg.focus();
    return (false)
  }
  if (len1 < 5)
  {
    document.getElementById('errorMesgDiv').innerHTML="Message is too short!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.mesg.value = "";
    document.frmInpt.mesg.focus();
    return (false)
  }
  else
   {
    document.frmInpt.answer.focus();
    return (true)
   }
   
}
function initFunction() {
	<% if(strStts.equals("FAILED"))
        {%>
    document.getElementById('errorMesgDiv').innerHTML="Failed to Post Your Concern! <strong>"+ "<%=strEror%>"+"</strong>  Please Try Again.";
    document.getElementById('errorMesgDiv').style.display="block";
    <%}%>
    <% if(strStts.equals("SUCCESS"))
        {%>
     document.getElementById('sucsMesgDiv').innerHTML="Successfully posted the Concern ! <strong>"+ "<%=strRefID%>"+"</strong> ";
    document.getElementById('sucsMesgDiv').style.display="block";
    
    <%}%>
    };
</script>
 
      <form class="form-horizontal" method="post" action="/RailSAHAY/SHY_LodgeConcern" enctype="multipart/form-data"    id="frmInpt" name="frmInpt" >
      <div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3>IR Connect</h3>
	                          <p>Share your concern directly to IR Officials!</p>
						  	</div>
	                      <div class="col-md-9 register-right">
	                          <div class="tab-content" id="myTabContent">
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                  <h3 class="register-heading d-sm-none d-md-block d-lg-block d-xl-block">Connect for your Grievance</h3>
	                                  <div class="row" style="margin-top:50px;">
	                                      <div class="col-md-12" align="center">
								<div class="btn-group">
								<button type="button" id="btnQ" name="btnQ" value ="Q" onclick="copyVal('QUERY')" class="btn btn-primary btn-concern">Query</button>
								<button type="button" id="btnS" name="btnS" value ="S" onclick="copyVal('SUGGESTION')" class="btn btn-primary btn-concern">Suggestion</button>
								<button type="button" id="btnG" name="btnG" value ="G" onclick="copyVal('GRIEVANCE')" class="btn btn-success btn-concern">Grievance</button>
								</div>
</div></div>
<div class="container1" style="margin-top:58px;display: flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>

                                                                         
	                                  <div class="row register-form">
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                              <input type="text" class="form-control" placeholder="First Name *" value="" tabindex="1" id="firstname" name="firstname" required="required"  maxlength="30"  onchange="validateName();"  onclick="hideMesg()"; />
	                                          </div>
	                                          <div class="form-group">
                                                             <input type="email" id="email" name="email" tabindex="3" placeholder="Your Email *" value=""   maxlength="40" required="required"   onchange="validateEmail();" style="text-transform:lowercase;" class="form-control">
	                                            </div>
	                                          <div class="form-group">
	                                              <select  id="cncrctgr" tabindex="5" name="cncrctgr" class="form-control">
	                                                  <option class="hidden"  selected disabled>Category</option>
	                                                  <option value="Behaviour">Behaviour of People</option>
	                                                  <option value = "Platform">Platform Surface</option>
	                                                  <option value ="Lighting">Lighting in Goods Shed</option>
	                                                  <option value ="Road">Approach Road Condition</option>
	                                                  <option value ="Consignment_Damage">Damage of Consignment</option>
	                                                  <option value ="Consignment_Loss">Loss of Consignment</option>
	                                                  <option value ="Corruption">Corruption</option>
	                                                  <option value ="Freight">Freight Charges</option>
	                                                  <option value ="Security">Security</option>
	                                                  <option value ="Others">Others</option>
	                                              </select>
	                                          </div>
	                                          <div class="form-group">
	                                              <input type="text" class="form-control"  tabindex="7" id="locndesc" name="locndesc" list="locnlist" style="text-transform:uppercase" placeholder="Location *" value="" onblur="validateLocn();" />
						     <input type="hidden" class="form-control"  id="locn" name="locn" style="text-transform:uppercase"  />		
						       <datalist id="locnlist"></datalist>
	                                          </div>
	                                      </div>
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                          	  <input type="text" class="form-control" tabindex="2" placeholder="Last Name *" value="" id="lastname" name="lastname" required="required"  maxlength="30"  onchange="validateName();"  /> 
	                                          </div>
	                                          <div class="form-group">
	                                              <input type="number"  maxlength="10" tabindex="4" name="mobile" id ="mobile" class="form-control" placeholder="Your Phone *" value="" required="required"  onchange="validateMob();"/>
	                                          </div>
	                                          <div class="form-group">
	                                              <select id="subctgr" name="subctgr" tabindex="6" class="form-control">
	                                                  <option  class="hidden"  selected disabled>Sub-Category</option>
	                                              </select>
	                                          </div>
	                                          <div class="form-group">
	                                              <input type="file" id="atch" name="atch" tabindex="8" class="form-control" placeholder="Upload (If any)" value="" />
	                                          </div>
	                                      </div>
	                                  </div>
									<div class="row">
	                                      <div class="offset-md-2 col-md-8">
									<div class="form-group">
									<textarea class="form-control" tabindex="9" id="mesg" name ="mesg" rows="6" cols="80" placeholder="Write your mesg (Max 1000 Words)" value=""   maxlength="1000"  onchange="validateMesg();" ></textarea>
									</div>
                                                                        <div   class="form-label-group">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="10" class="form-control" placeholder="Captcha" required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
		               <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		              </div>
									<div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="11"  value="Submit" onclick="return validateCaptcha();">Submit</button>
									</div>
                                                                        <input type=hidden id="hidval" name="hidval" value="QUERY">
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
        <script>
            var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("locnlist");
            my_list.innerHTML = str;         
		</script>

   <%@ include file="/pages/GG_Footer.jspf" %>

