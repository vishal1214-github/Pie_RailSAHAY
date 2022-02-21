<!doctype html>
<%
String strStts="";
String strRefID="";
String strEror="";
int wid=20;
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

model.Ratings rtngs=model.FeedBackRatings.SHY_RatingContTX("TEST");



int otlRatings=rtngs.getOtlRatings() ;
    double avgRating= rtngs.getAvgRating();
    int oneStar= rtngs.getOneStar();
    double oneStarP= rtngs.getOneStarP();
    int twoStar= rtngs.getTwoStar();
    double twoStarP= rtngs.getTwoStarP();
    int threeStar= rtngs.getThreeStar();
    double threeStarP= rtngs.getThreeStarP();
    int fourStart= rtngs.getFourStart();
    double fourStarP= rtngs.getFourStarP();
    int fiveStar= rtngs.getFiveStar();
    double fiveStarP= rtngs.getFiveStarP();
    
    System.out.println("fiveStarP:"+fiveStarP+"::fourStarP:"+fourStarP);

%>
<%@ include file="/pages/GG_Header.jspf" %>

<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FEEDBACK","/RailSAHAY/pages/Feedback.jsp",UserDevice,Browser);
%>

<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/rating.css">
	

<script>
$(document).ready(function() {

$("#divConcern").hide();
$("#divRating").hide();
fetchReviews('0');
$( "input" ).keypress(function() {
  $("#errorMesgDiv").val("");
    document.getElementById('errorMesgDiv').style.display="none";
  $("#sucsMesgDiv").val("");
    document.getElementById('sucsMesgDiv').style.display="none";

});
	copyVal("COMPLIMENT");
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
           // alert("Submiting form");
            //document.forms[0].submit();
            document.frmInpt.submit();
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
	$(".register-heading").html("May we please know your Query!");
	$("#btnQ").removeClass("btn-primary");	
	$("#btnQ").addClass("btn-success");
	$("#divConcern").show();
	$("#divRating").hide();
}
if(val=="SUGGESTION")
{
	$(".register-heading").html("May we know about your Suggestion");
	$("#btnS").removeClass("btn-primary");	
	$("#btnS").addClass("btn-success");
	$("#divConcern").show();
	$("#divRating").hide();
}
if(val=="GRIEVANCE")
{
	$(".register-heading").html("Please share your Grievance with us !");
	$("#btnG").removeClass("btn-primary");	
	$("#btnG").addClass("btn-success");
	$("#divConcern").show();
	$("#divRating").hide();
}
if(val=="COMPLIMENT")
{
	$(".register-heading").html("&nbsp;&nbsp;");
	$("#btnC").removeClass("btn-primary");	
	$("#btnC").addClass("btn-success");
	$("#divConcern").hide();
	$("#divRating").show();
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
       document.getElementById('errorMesgDiv').innerHTML='<%=E.EH("Please enter a valid email address!")%>';
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
   document.getElementById('errorMesgDiv').innerHTML='<%=E.EH("Please enter a valid mobile number!")%>';
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
    document.getElementById('errorMesgDiv').innerHTML='<%=E.EH("Please enter a valid Name!")%>';
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
    document.getElementById('errorMesgDiv').innerHTML='<%=E.EH("Please enter a valid Message!")%>';
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.mesg.value = "";
    document.frmInpt.mesg.focus();
    return (false)
  }
  if (len1 < 5)
  {
    document.getElementById('errorMesgDiv').innerHTML='<%=E.EH("Message is too short!")%>';
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
 <script>
 function fetchReviews(rtn)
{
	
	var htmlstr='<div class="w3-container">';
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'SAHAY_RTNG',Rating:rtn},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
			
			
                        if(obj.Review.length==0)
			{
				noDataFound();
				return false;
			}
			for(var i=0;i<obj.Review.length;i++)
			{
				
				
				
				var Name=obj.Review[i].Name;
				var Rating=obj.Review[i].Rating;
				var Text=obj.Review[i].Text;
                                var rat="";
                                for(var rt=0;rt<Rating*1;rt++){
                                rat+='<span class="fa fa-star checked float-right"></span>';
                                }
                                console.log(rat);
                                console.log(Name);
				htmlstr+='<div class="w3-card-5"><table class="styletable">';
				htmlstr+='<tr><th><p class="sttnhead">'+Name+'</p></th><th>'+rat+'</th></tr>';
				htmlstr+='<tr><td class="lbl"  colspan="2">'+Text+'</td></tr></table></div>';
			}
			htmlstr+='</div>';
			$("#divReview").html(htmlstr);
		}
	});
}
 </script>
      <form class="form-horizontal" method="post" action="/RailSAHAY/SHY_LodgeConcern" enctype="multipart/form-data"    id="frmInpt" name="frmInpt" >
      <div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3><%=E.EH("HELP US TO SERVE YOU BETTER")%> !</h3>
	                          <p><%=E.EH("Share your IR Freight Business Development Portal experience with us")%>..</p>
						  	</div>
	                      <div class="col-md-9 register-right">
	                          <div class="tab-content" id="myTabContent">
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                  <h3 class="register-heading d-sm-none d-md-block d-lg-block d-xl-block"><%=E.EH("Connect for your Grievance")%></h3>
	                                  <div class="row" style="margin-top:50px;">
	                                  <div class="col-md-12" align="center">
								<div class="btn-group">
								<button type="button" id="btnC" name="btnC" value ="C" onclick="copyVal('COMPLIMENT')" class="btn btn-primary btn-concern"><%=E.EH("Rate Us")%></button>
									<%--<button type="button" id="btnQ" name="btnQ" value ="Q" onclick="copyVal('SUGGESTION')" class="btn btn-primary btn-concern"><%=E.EH("Feedback")%></button>--%>
							<button type="button" id="btnS" name="btnS" value ="S" onclick="copyVal('SUGGESTION')" class="btn btn-primary btn-concern"><%=E.EH("Suggestion")%></button>
								<button type="button" id="btnG" name="btnG" value ="G" onclick="copyVal('GRIEVANCE')" class="btn btn-success btn-concern"><%=E.EH("Grievance")%></button>

								</div>
</div>
	                                     </div>
<div class="container1" style="margin-top:58px;display: flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>
<div class="row" id="divRating">
	<div class="col-md-6 offset-md-3">
		<span class="heading"><%=E.EH("User Rating")%></span>
                <% for(int i=1;i<=5;i++){
                    if(i<=avgRating){ %>
<span class="fa fa-star checked"></span>
<% }else{ %>
<span class="fa fa-star"></span>
<% }} %>
<p><%=avgRating%> <%=E.EH("average based on")%> <%=otlRatings%> <%=E.EH("reviews")%>.</p>
<hr style="border:3px solid #f1f1f1">

<div class="row">
  <div class="side">
    <div>5 <%=E.EH("star")%></div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-5" style="width:<%=fiveStarP%>%" onclick="fetchReviews('5')"></div>
    </div>
  </div>
  <div class="side right">
    <div><%=fiveStar%></div>
  </div>
  <div class="side">
    <div>4 <%=E.EH("star")%></div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-4" style="width:<%=fourStarP%>%" onclick="fetchReviews('4')"></div>
    </div>
  </div>
  <div class="side right">
    <div><%=fourStart%></div>
  </div>
  <div class="side">
    <div>3 <%=E.EH("star")%></div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-3" style="width:<%=threeStarP%>%" onclick="fetchReviews('3')"></div>
    </div>
  </div>
  <div class="side right">
    <div><%=threeStar%></div>
  </div>
  <div class="side">
    <div>2 <%=E.EH("star")%></div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-2" style="width:<%=twoStarP%>%" onclick="fetchReviews('2')"></div>
    </div>
  </div>
  <div class="side right">
    <div><%=twoStar%></div>
  </div>
  <div class="side">
    <div>1 <%=E.EH("star")%></div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-1" style="width:<%=oneStarP%>%" onclick="fetchReviews('1')"></div>
    </div>
  </div>
  <div class="side right">
    <div><%=oneStar%></div>
  </div>
</div>
<div>
 <div id="divReview" style="height:300px;max-height:300px;overflow-y: scroll" ></div> 
 
 </div>
<br/><br/>
<h1 class="text-center" alt="Simple"><%=E.EH("Based on your experience with this Portal, Please Rate Us")%>!</h1> 
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
                                                                       
	                                  <div class="row register-form" id="divConcern">
	                                      <div class="col-md-6">
	                                         
	                                          <div class="form-group">
	                                              <select  id="cncrctgr" tabindex="5" name="cncrctgr" class="form-control">
	                                                  <option class="hidden"  selected disabled><%=E.EH("Category")%></option>
	                                                  <option value="Content"><%=E.EH("Contents of this Portal")%></option>
	                                                  <option value = "Design"><%=E.EH("Look and Feel of this Portal")%></option>
	                                                  <option value ="Response"><%=E.EH("Response Time of this Portal")%></option>
	                                                  <option value ="Security"><%=E.EH("Security Measures in this Portal")%></option>
	                                                  <option value ="Others"><%=E.EH("Others")%></option>
	                                              </select>
	                                          </div>
	                                      </div>
	                                      <div class="col-md-6">
	                                         <div class="form-group">
	                                              <input type="file" id="atch" name="atch" tabindex="8" class="form-control" placeholder='<%=E.EH("Upload (If any)")%>' value="" />
	                                          </div>
	                                      </div>
	                                  </div>
                                          <div class="row register-form" >
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                              <input type="text" class="form-control" placeholder='<%=E.EH("First Name ")%>' value="" tabindex="1" id="firstname" name="firstname"   maxlength="30"  onchange="validateName();"  onclick="hideMesg()"; />
	                                          </div>
	                                          <div class="form-group">
                                                             <input type="email" id="email" name="email" tabindex="3" placeholder='<%=E.EH("Your Email ")%>' value=""   maxlength="40"   onchange="validateEmail();" style="text-transform:lowercase;" class="form-control">
	                                            </div>
	                                      </div>
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                          	  <input type="text" class="form-control" tabindex="2" placeholder='<%=E.EH("Last Name ")%>' value="" id="lastname" name="lastname"   maxlength="30"  onchange="validateName();"  /> 
	                                          </div>
	                                          <div class="form-group">
	                                              <input type="number"  maxlength="10" tabindex="4" name="mobile" id ="mobile" class="form-control" placeholder='<%=E.EH("Your Phone ")%>' value=""  onchange="validateMob();"/>
	                                          </div>
	                                      </div>
	                                  </div>
                                          
									<div class="row">
	                                      <div class="offset-md-2 col-md-8">
									<div class="form-group">
									<textarea class="form-control" tabindex="9" id="mesg" name ="mesg" rows="6" cols="80" placeholder='<%=E.EH("Write your message (Max 1000 Words)")%>' value=""   maxlength="1000"  onchange="validateMesg();" ></textarea>
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
									<button class="btn btn-primary float-right" tabindex="11"  value="Submit" onclick="return validateCaptcha();"><%=E.EH("Submit")%></button>
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
   <%@ include file="/pages/GG_Footer.jspf" %>

