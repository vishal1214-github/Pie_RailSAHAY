
<%@ include file="/pages/GG_Header.jspf" %>
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

String raiseCR=(String)request.getAttribute("CR");
      String CRid=(String)request.getAttribute("CRid");
      String CRreply=(String)request.getAttribute("CRreply");
      String CRerror=(String)request.getAttribute("CRerror");
      
      String cncrctgr=(String)request.getAttribute("cncrctgr");
      String locnType=(String)request.getAttribute("locnType");
      String subctgr=(String)request.getAttribute("subctgr");
      String locncr=(String)request.getAttribute("locncr");
      String railAuth=(String)request.getAttribute("railAuth");
      String mesgcr=(String)request.getAttribute("mesgcr");
      
        String fName=(String)request.getAttribute("fName");
        String lName=(String)request.getAttribute("lName");
        String email=(String)request.getAttribute("email");
        String phnoe=(String)request.getAttribute("phnoe");
        String customer=(String)request.getAttribute("customer");
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">

	<script type="text/javascript" src="/RailSAHAY/resources/js/Cust.js"></script>

<script>
$(document).ready(function() {
$("#errorMesgDiv").hide();
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
      
       document.getElementById('sbmt').style.display='block'; 
       document.getElementById('proceed').style.display='none';
        if('<%=raiseCR%>'=='CR'){
           
            var htmlA="";
                        
                     console.log('<%=raiseCR%>::<%=CRreply%>');
    //  alert("<%=cncrctgr%>"+"   <%=subctgr%>");
      
      
                               
                        
                        if('<%=CRreply%>'!="FAIL") {
                           htmlA="<strong>Success!</strong> Your Freight Request has been successfully registered with Reference No:"+"<%=CRid%>"+" Details are shared on your email."; 
                           console.log(htmlA);
                       // $("#errorMesgDiv").html(htmlA);
                         //  document.getElementById('errorMesgDiv').style.display="block";
                         custPrompt(htmlA);
                           //custPrompt("<strong>Success!</strong> Your Freight Request has been successfully registered with Reference No:"+"<%=CRid%>"+" Details are shared on your email.");
                        }else{
                        var mySelect = document.getElementById('cncrctgr');
                        setMultiSelect( mySelect , '<%=cncrctgr%>' );
                          //document.getElementById('cncrctgr').value='<%=cncrctgr%>';
                          chSubCtgr();
                        var mySelect1 = document.getElementById('subctgr');
                        setMultiSelect( mySelect1 , '<%=subctgr%>' );
                          //document.getElementById('subctgr').value='<%=subctgr%>';
                          chgSubCtgr();
                        var mySelect2 = document.getElementById('locntype');
                        setMultiSelect( mySelect2 , '<%=locnType%>' );
                          //document.getElementById('locntype').value='<%=locnType%>';
                          document.getElementById('locncr').value='<%=locncr%>';
                          document.getElementById('railAuth').value='<%=railAuth%>';
                          document.getElementById('mesg').value='<%=mesgcr%>';
                          document.getElementById('firstname').value='<%=fName%>';
                          document.getElementById('lastname').value='<%=lName%>';
                          document.getElementById('email').value='<%=email%>';
                          document.getElementById('mobile').value='<%=phnoe%>';
                          document.getElementById('customer').value='<%=customer%>';
                            htmlA="<strong>Error!</strong>"+"<%=CRerror%>"; 
                        //  custPrompt(htmlA);

                            $("#errorMesgDiv").html(htmlA);
                           document.getElementById('errorMesgDiv').style.display="block";
                        }
            
            
        }
        
        
         $("#mobile").on("blur", function(){  	$("#full_mobile").val("+" + $(".iti__country[class*='iti__active']").attr("data-dial-code") );  });


	
});

function setMultiSelect( select, values ) 
{
    for (var i = 0; i < select.options.length; i++) 
    {
        var option = select.options[i];
        if ( values.toUpperCase() == option.value.toUpperCase() ) 
        {
            option.selected = true;
        }
    }
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
        if(document.getElementById("mobVldt").value=='N'){
            //alert(document.getElementById("full_mobile").value);
             document.getElementById('errorMesgDiv').innerHTML="Please Provide Valid Mobile No.";
            document.getElementById('errorMesgDiv').style.display="block";
             return false;
        }else{
            document.frmInpt.submit();
        }
        }
        else{
            return false;
        }
      } 
      
function refcapfunc()
{
    var d = new Date();
    document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();
}

function chSubCtgr(){
    var ctgr=document.getElementById('cncrctgr').value;
    //alert("Category:"+ctgr);
    if(ctgr=="Edemand_User"){
         $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option   value="A">E-Registration</option>');
	$("#subctgr").append('<option   value="X">E-Payment Application</option>');
	$("#ctgrOth").hide();
          $("#sctgrOth").hide();
       // document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>'; 
       // locnSelect();
    }
    if(ctgr=="Consignment")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="B">Diversion Application</option>');
	$("#subctgr").append('<option value="C">Rebooking Application</option>');
	$("#subctgr").append('<option value="D">Short Destination Delivery Application</option>');
    }
    if(ctgr=="Demurrage")
    {
        $("#ctgrOth").hide();
        $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="E">Stacking Permission Application</option>');
	$("#subctgr").append('<option value="F">Demurrage Waiver Application</option>');
	$("#subctgr").append('<option value="G">Wharfage Waiver Application</option>');
        // document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>';
    } 
    if(ctgr=="Terminal")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="L">Application for PFT</option>');
	$("#subctgr").append('<option value="V">Application for Auto Hub</option>');
	$("#subctgr").append('<option value="M">Application for Siding</option>');
    }
    if(ctgr=="Discount")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="I">Application for Long Term Tariff Contract (LTTC)</option>');
	$("#subctgr").append('<option value="J">Application for Round Trip Traffic (RTT)</option>');
	$("#subctgr").append('<option value="K">Application for Station To Station Rate Scheme(STS)</option>');
	$("#subctgr").append('<option value="S">Application for Freight Advance Scheme (FAS)</option>');
	$("#subctgr").append('<option value="U">Application for Merry Go Round Scheme (MGR)</option>');
    }
    if(ctgr=="Investment")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	//$("#subctgr").append('<option value="M">Liberalized Siding Rule Scheme (LSR)</option>');
	$("#subctgr").append('<option value="N">Application for Liberalized Special Freight Train Operator (LSFTO)</option>');
	$("#subctgr").append('<option value="O">Application for Automobile Freight Train Operator (AFTO)</option>');
	//$("#subctgr").append('<option value="P">Liberalized Wagon Investment Scheme (LWIS)</option>');
	$("#subctgr").append('<option value="Q">Application for General Purpose Wagon Investment Scheme (GPWIS)</option>');
	//$("#subctgr").append('<option value="R">RoadRailer (RRU)</option>');
	$("#subctgr").append('<option value="T">Application for Wagon Leasing Scheme (WLS)</option>');
    }
    if(ctgr=="Scheme")
    {
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="Y">Application for offering New/Additional Traffic</option>');
    }
    if(ctgr=="Couser"){
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="W">Co-User Permission</option>');
    }
    if(ctgr=="GCT"){
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="H">Application For Gati Shakti Multi-Modal Cargo Terminal (GCT)</option>');
    }
}
function toggle(className, displayState){
    var elements = document.getElementsByClassName(className)

    for (var i = 0; i < elements.length; i++){
        elements[i].style.display = displayState;
    }
}
 function chgSubCtgr(){
        $( ".no-user" ).show(); 
       document.getElementById('sbmt').style.display='block'; 
       document.getElementById('proceed').style.display='none';
    var subctgr=document.getElementById('subctgr').value;
    
    if(subctgr=="A")
    {
    $( ".no-user" ).hide(); 
   document.getElementById('sbmt').style.display='none'; 
       document.getElementById('proceed').style.display='block'; 
    }
    
    if(subctgr=="A" || subctgr=="B" || subctgr=="C" || subctgr=="D" || subctgr=="E" || subctgr=="F" || subctgr=="G" || subctgr=="K" || subctgr=="L" || subctgr=="M" || subctgr=="Y"  || subctgr=="H" ) {
        document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>';
         $("#sctgrOth").hide();
         locnSelect();
    }
   else if(subctgr=="X")
    {
    $("#sctgrOth").hide();
    document.getElementById('locntype').innerHTML='<option value = "Z" selected>Zone</option>';
    locnSelect();
    }else if(subctgr=="N" || subctgr=="O" || subctgr=="Q" || subctgr=="T" || subctgr=="V" ){
        document.getElementById('locntype').innerHTML='<option value ="R" >Railway Board</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }else if(subctgr=="U" || subctgr=="I" || subctgr=="J" || subctgr=="W" || subctgr=="S"){
        document.getElementById('locntype').innerHTML='<option value = "Z" selected>Zone</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }else if(subctgr=="P" || subctgr=="R"   ){
        document.getElementById('locntype').innerHTML=' <option class="hidden" selected  disabled>Location Type</option><option value ="R" >Railway Board</option><option value="Z">Zone</option><option value = "D">Division</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }
    
 }
      



function hideMesg()
{
    document.getElementById('errorMesgDiv').style.display="none";
     document.getElementById('sucsMesgDiv').style.display="none";

}
  
/*
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
    document.frmInpt.mesg.focus();
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
    document.frmInpt.mesg.focus();
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
    document.frmInpt.mesg.focus();
    return (true)
   }
   
} */

</script>
 <link rel="stylesheet" href="/RailSAHAY/resources/js/intlDate/css/intlTelInput.css">
 <link rel="stylesheet" href="/RailSAHAY/resources/js/intlDate/css/demo.css">
 
      <form class="form-horizontal" method="post" action="/RailSAHAY/RaiseRequest" enctype="multipart/form-data"    id="frmInpt" name="frmInpt" >
      <div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3>Raise your Request!</h3>
	                         
						  	</div>
	                      <div class="col-md-9 register-right">
                             
	                          <div class="tab-content" id="myTabContent">
                                  
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                 
	                          <h3 class="register-heading d-sm-none d-md-block d-lg-block d-xl-block">Connect for your Request</h3>
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
	                                              <select  id="cncrctgr" tabindex="5" name="cncrctgr" class="form-control" required onchange="chSubCtgr()">
	                                                  <option class="hidden"  selected disabled>Category</option>
	                                                  <option value="Edemand_User" >E- Registration</option>
	                                                  <option value="Consignment">Diversion/Rebooking/Short Distance Delivery</option>
	                                                  <option value="Demurrage">Stacking/Demurrage/Wharfage</option>
	                                                 <!-- <option value="Terminal">Application for Siding/PFT/Auto Hub</option>-->
	                                                  <option value = "Discount">Discount Schemes</option>
	                                                  <option value = "Investment">Investment Scheme</option>
	                                                  <option value = "Scheme">Offering Traffic</option>
	                                                  <option value = "Couser">Application for Co-User Permission</option>
                                                          <option value="GCT">Gati Shakti Multi-Modal Cargo Terminal (GCT)</option>
	                                              </select>
                                                       <input type="text" class="form-control" tabindex="13" placeholder="Please Provide Category" value="" id="ctgrOth" name="ctgrOth" required="required"  maxlength="30"    />
	                                          </div>
                                                   <div class="form-group no-user">
	                                              <select  id="locntype" tabindex="7" name="locntype" class="form-control" onchange="locnSelect()">
	                                                  <option class="hidden" selected  disabled>Location Type</option>
	                                               <!--   <option value ="R" >Railway Board</option>
	                                                  <option value="Z">Zone</option>
	                                                  <option value = "D">Division</option>
	                                                  <option value ="S" >Station</option> -->
	                                              </select>
	                                          </div>
	                                         
                                                   <div class="form-group no-user">
	                                              <input type="text" class="form-control inptcap name"  tabindex="9" id="customer" name="customer" list="custlist" style="text-transform:uppercase" placeholder="Organization Name" />
						     		
						       <datalist id="custlist"></datalist>
	                                          </div>
                                                    <div class="form-group no-user">
	                                              <input type="file" id="atch" name="atch" tabindex="11" class="form-control" placeholder="Upload (If any)" value="" accept="text/plain, application/pdf, image/*"/>
                                                      <span>Note: Single pdf/txt/jpeg/png File is Allowed(Max Size 10MB)</span>
	                                          </div>
                                                  
	                                      </div>
	                                      <div class="col-md-6">
	                                          <div class="form-group">
	                                          	  <input type="text" class="form-control inptcap name" tabindex="2" placeholder="Last Name " value="" id="lastname" name="lastname"   maxlength="30"   /> 
	                                          </div>
	                                          <div class="form-group">
	                                              <input  type="tel"  tabindex="4" name="mobile" id ="mobile" class="form-control inptcap numb" maxlength="11" value="" required="required" />
                                                      <input type="hidden" name="full_mobile" id="full_mobile"  />
	                                          </div>
	                                          <div class="form-group">
	                                              <select id="subctgr" name="subctgr" tabindex="6" required class="form-control" onchange="chgSubCtgr()">
	                                                  <option  class="hidden"  selected disabled>Sub-Category</option>
	                                              </select>
                                                      <input type="text" class="form-control" tabindex="14" placeholder="Please Provide Sub-Category" value="" id="sctgrOth" name="sctgrOth" required="required"  maxlength="30"    />
	                                          </div>
                                                    <div class="form-group no-user">
	                                              <input type="text" class="form-control inptcap name"  tabindex="8" required id="locncr" name="locncr" list="locnlist" style="text-transform:uppercase" placeholder="Location *" value=""  />
                                                      <datalist id="locnlist"></datalist></input>  
	                                          </div>
	                                        <div class="form-group no-user">
	                                          	  <input type="text" class="form-control inptcap name" tabindex="10" placeholder="Railway Authority to be Applied" value="" id="railAuth" name="railAuth" required="required"  maxlength="30"    /> 
	                                          </div>
	                                      </div>
	                                  </div>
                                          <div class="row">
	                                      <div class="offset-md-2 col-md-8">
                                                <div class="form-group no-user">
                                                <textarea class="form-control inptcap excludechr" tabindex="12" id="mesg" name ="mesg" rows="6" cols="80" placeholder="Write your mesg (Max 500 Characters)" value=""   maxlength="500"  onchange="validateMesg();" ></textarea>
                                                </div>
                                                                        <div   class="form-label-group no-user">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="15" class="form-control" placeholder="Captcha" required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
		               <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		              </div>
									<div class="d-flex justify-content-center mb-10 ">
									<button class="btn btn-primary float-right" id="sbmt" tabindex="15"  value="Submit" onclick="return validateCaptcha();">Submit</button>
									<button class="btn btn-primary float-right" id="proceed" tabindex="15"  value="Proceed" onclick="window.open('https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/RgtrUserN.jsf')" >Proceed To New User Registration</button>
									</div>
                                                                       
                                                                        <input type=hidden id="hidval" name="hidval" value="REQUEST">
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
        <script>
            var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
                var dvsnstr='';
                for (var i=0; i < aDvsn.length;++i){
                dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
                }
                var zonestr='';
                for (var i=0; i < aZone.length;++i){
                zonestr+= '<option value="'+aZone[i]+'" />'; // Helplist for Zones
                }
                var rbstr='<option value="RAILWAY BOARD" />';
            //var my_list=document.getElementById("locnlist");
            //my_list.innerHTML = str;   
            
            function locnSelect(){
                var locnTp=document.getElementById('locntype').value;                
                document.getElementById("locncr").value="";
                document.getElementById("locncr").readOnly=false;
                
                switch(locnTp)
                  {
            
                   
                    case 'S':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = str;
                            break;
                   
                    case 'D':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = dvsnstr;  
                            var subctgr1=document.getElementById('subctgr').value;
                            if(subctgr1=='L'||subctgr1=='M' ||subctgr1=='H'){
                             document.getElementById("railAuth").value="Sr DOM";
                            }else{
                            document.getElementById("railAuth").value="Sr DCM";
                            }
                            document.getElementById("railAuth").readOnly=true;
                            
                            break;
            
                    case 'Z':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = zonestr;                              
                            document.getElementById("railAuth").value="CCM/FM";
                            document.getElementById("railAuth").readOnly=true;
                            break;
                    case 'R':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = rbstr;     
                            document.getElementById("locncr").value="RAILWAY BOARD";
                            document.getElementById("locncr").readOnly=true;                              
                            document.getElementById("railAuth").value="EDFM";
                            document.getElementById("railAuth").readOnly=true;
                            
                            break;
            
                  }
            }
		</script>
               
<script>
                var cus='';
                for (var i=0; i < gCust.length;++i){
                cus += '<option value="'+gCust[i]+'" />'; // Helplist for Customer
                }
                cus += '<option value="Others" />';
                //alert(cus);
                var my_cust=document.getElementById("custlist");
            my_cust.innerHTML = cus;
                </script>
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

