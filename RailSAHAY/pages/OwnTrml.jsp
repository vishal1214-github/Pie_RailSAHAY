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

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"OWN A PRIVATE FRIEGHT TERMINAL","/RailSAHAY/pages/OwnTrml.jsp",UserDevice,Browser);
%>
 <script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
	    <script>	
            $(document).ready(function() {
$( "input" ).keypress(function() {
  $("#errorMesgDiv").val("");
    document.getElementById('errorMesgDiv').style.display="none";
  $("#sucsMesgDiv").val("");
    document.getElementById('sucsMesgDiv').style.display="none";

});
});


function refcapfunc(){
              var d = new Date();
          document.getElementById("captcha").src = "/RailSAHAY/SahayCaptcha.jpg?"+d.getTime();

            }
        
      

function hideMesg()
{
    document.getElementById('errorMesgDiv').style.display="none";
     document.getElementById('sucsMesgDiv').style.display="none";

}
  

function validateEmail(mail) 
{
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(frmInpt.txtEmail.value))
  {
    document.frmInpt.txtMobl.focus();
    return (true)
  }
       document.getElementById('errorMesgDiv').innerHTML="Please enter a valid email address!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtEmail.value = "";
    document.frmInpt.txtEmail.focus();
    return (false)
}
function validateMob(mob)
{
  val = frmInpt.txtMobl.value;
  if(/^\d{10}$/.test(val))
  {
    document.frmInpt.cmdtdesc.focus();
    return (true)
  }
   document.getElementById('errorMesgDiv').innerHTML="Please enter a valid mobile number!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtMobl.value = "";
    document.frmInpt.txtMobl.focus();
    return (false)
}
function validateName()
{
 var val = frmInpt.txtName.value;
 val1=val.trim();

  if ((val1=='')||(val1=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Name!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtName.value = "";
    document.frmInpt.txtName.focus();
    return (false)
  }
  else
   {
    document.frmInpt.txtCompName.focus();
    return (true)
   }
   
}
function validateCmpName(compname)
{
	  var val = frmInpt.txtCompName.value;
   val1=val.trim();
 
  var len1=val1.length;
 
  if ((val1=='')||(val1=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Company Name!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtCompName.value = "";
    document.frmInpt.txtCompName.focus();
    return (false)
  }
  if (len1 < 5)
  {
    document.getElementById('errorMesgDiv').innerHTML="Company Name is too short!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtCompName.value = "";
    document.frmInpt.txtCompName.focus();
    return (false)
  }
  else
   {
    document.frmInpt.email.focus();
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
	    
	    <script>
$(document).ready(function() {
$( "input" ).keypress(function() {
  $("#errorMesgDiv").val("");
    document.getElementById('errorMesgDiv').style.display="none";
  $("#sucsMesgDiv").val("");
    document.getElementById('sucsMesgDiv').style.display="none";

});
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
		$("#txtLocn").val(sttncode);

}
function validateCmdt()
{
		var cmdtdesc=$("#cmdtdesc").val();
		var cmdtcode='';
		if(cmdtdesc.indexOf("-")>-1)
		{
			 cmdtcode=cmdtdesc.substring(0,cmdtdesc.indexOf("-"));
                         cmdtcode = cmdtcode.trim();
		}	
		else
		{
			cmdtcode=cmdtdesc.toUpperCase();
		}
              
		$("#txtCmdt").val(cmdtcode);

}
 function validateCaptcha()
      {
var name = (document.getElementById('txtName').value);	
 if ((name=='')||(name=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Name!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtName.value = "";
    document.frmInpt.txtName.focus();
    return (false)
  }
var cmpyname = (document.getElementById('txtCompName').value);	
 if ((cmpyname=='')||(cmpyname=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Company Name!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtCompName.value = "";
    document.frmInpt.txtCompName.focus();
    return (false)
  }
 var mob = (document.getElementById('txtMobl').value);	
 if ((mob=='')||(mob=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Mobile Number!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtMobl.value = "";
    document.frmInpt.txtMobl.focus();
    return (false)
  }
 var mail = (document.getElementById('txtEmail').value);	
 if ((mail=='')||(mail=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid email!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.txtEmail.value = "";
    document.frmInpt.txtEmail.focus();
    return (false)
  }   
  
   var mesg = (document.getElementById('mesg').value);	
 if ((mesg=='')||(mesg=null))
  {
    document.getElementById('errorMesgDiv').innerHTML="Please enter a valid Message!";
    document.getElementById('errorMesgDiv').style.display="block";
    document.frmInpt.mesg.value = "";
    document.frmInpt.mesg.focus();
    return (false)
  }  

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


	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">OWN A TERMINAL</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card mr-3 ml-3">
	  <div class="card-header head-faq">
	    Invest in Indian Railways for a Private Freight Terminal (PFT)
	  </div>
	  <div class="card-body">
		   <div class="row">
		   <div class="col-lg-6 col-sm-12" style="text-align: justify;">
		    To facilitate rapid development of a network of freight terminals with private investment and to provide efficient and cost effective logistics services with warehousing solution to end users, a scheme namely Private Freight Terminal (PFT) was introduced and further liberalized in 2015.
		   <br/><br/>Following two types of PFTs are permitted: <ul>
		   <li>Green Field PFT- Completely new project</li>
		   <li>Brown field PFT- Conversion of existing Private sidings into PFT</li>
		   </ul>
			</div>
	            <div class="col-lg-6 col-sm-12">
			<img src="/RailSAHAY/resources/images/pft1.jpg" class="float-right" />
		    </div>
		 </div>
		<br/>
		<span class="badge badge-blue1">Salient features of the scheme are as under</span>
<ul class="list-group list-group-flush">
<li class="list-group-item">
PFTs are permitted in private land through private investment</li>

<li class="list-group-item">
Development of appropriate rail infrastructure and other services. </li>

<li class="list-group-item">
Railways provide rail-connectivity to the rail network. </li>

<li class="list-group-item">
Permission for handling multiple consignors/ consignees cargo from that terminal. Terminal Management Company gets rail access to handle third party cargo in the logistic chain.</li>

<li class="list-group-item">
Permission to load outward traffic of priority-D coal, coke and iron ore without additional fee of Rs 5crore. </li>

<li class="list-group-item">
Application fee of Rs. 10 Lakh and security deposit (refundable) of Rs. 10 Lakh each to ensure timely commissioning of PFT. </li>

<li class="list-group-item">
Terminal Management Company is free to fix tariff and other charges for providing value added logistics related services. </li>

<li class="list-group-item">
In Principle Approval by Railway within 45 days.</li>
 
<li class="list-group-item">
PFTs can enter into Long term contract with the Railways. Period of agreement for 30 years; extendable by one period of 10 years.  Further extension may be considered on the basis of extant policy at the time of such extension. </li>

<li class="list-group-item">
Underutilized/un-utilized sidings get opportunity for commercial utilization for their facility by converting it into Brownfield PFT. Thus creating opportunity for Customers for converting sunk assets into productive assets. At the same time, it creates additional freight potential for Railways at no extra cost.</li>

	</li>
</ul> 

<br/><br/><button data-toggle="collapse" class="btn btn-info btn-sm" data-target="#divPFT">Application Process</button>
			   <div id="divPFT" class="collapse">
				<br/><br/><hr/>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Freight Terminal</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<b>Loading and Unloading of goods transported by rail is done at freight terminals. Following categories are included in Freight Terminals</b>
									<ul>
										<li>Railway owned Goods sheds or railway sidings on Railway land</li>
										<li>Private sidings built on private land in terms of the private siding policy for exclusive use of its owner for its own cargo</li>
										<li>Private sidings built on private land where rail users other than the owners of Private sidings have been permitted to use the siding under the provisions for co-use facility in the private siding agreement</li>
										<li>Privately owned container handling terminals set up on private land in accordance with the provisions of MCA (including those created by CONCOR on private land before introduction of MCA)</li>
										<li>Private Freight Terminal set up by the Terminal Management Company (TMC) on private land through private investment in terms of PFT policy for handling of all types of traffic unless and otherwise notified under the policy</li>
									</ul>
								</li>
								<li class="list-group-item">
								<a href="/RailSAHAY/resources/pdf/PFT_Application_Process.pdf" class="card-link1" target="_blank">Click to view Detailed Process (PFT)</a><br/>
								<a href="/RailSAHAY/resources/pdf/ApplicationPrivateSiding.pdf" class="card-link1" target="_blank">Click to view Detailed Process (Private Siding)</a>
			
								</li>
							</ul>
						</div>
						
  					</div>
					</li>					
				</ul>
					<br/><div align="center"><button data-toggle="collapse"  class="btn btn-info btn-sm" data-target="#divPFT">Close</button></div>
			   </div>
	
	  </div>	  
	  	</div>
      </div>
</div>
      </div>
<hr/>
<br/>
 <form class="form-horizontal" method="post" action="/RailSAHAY/SHY_OwnTrml" enctype="multipart/form-data"    id="frmInpt" name="frmInpt" >
<div class="row">
<div class="col-lg-10 offset-lg-1 col-sm-12">
<div class="card">
  <div class="card-header headcard">For further details and support in setting up a PFT or Private Siding, please contact us</div>
  <div class="card-body">
  <div class="container1" style="margin-top:10px;display: flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>
	<table class="table table-striped table-bordered tabformat">
		<tr><td class="lbl"><%=E.EH("Name")%></td><td><input type="text" class="form-control inptcap" id="txtName" name="txtName"  placeholder="Name *" tabindex="1" required="required"  maxlength="30"  onchange="validateName();"  onclick="hideMesg()"; /></td></tr>
		<tr><td class="lbl"><%=E.EH("Company Name")%></td><td><input type="text" class="form-control inptcap" id="txtCompName" name="txtCompName" placeholder="Company Name *" tabindex="2" required="required"  maxlength="100"  onchange="validateCmpName();"/></td></tr>
		<tr><td class="lbl"><%=E.EH("Email Id")%></td><td><input type="email" class="form-control" id="txtEmail" name="txtEmail"  tabindex="3" placeholder="Your Email *" value=""   maxlength="40" required="required"   onchange="validateEmail();" style="text-transform:lowercase;"/></td></tr>
		<tr><td class="lbl"><%=E.EH("Mobile No")%>.</td><td><input type="number" class="form-control" id="txtMobl" name="txtMobl"  tabindex="4"  placeholder="Your Phone *" value="" required="required"  onchange="validateMob();" /></td></tr>
		<tr><td class="lbl"><%=E.EH("Commodity")%></td><td><input type="text" class="form-control inptcap" id="cmdtdesc" list="cmdtlist" name="cmdtdesc"  tabindex="5" placeholder="Commodity"  onblur="validateCmdt();" /><datalist id="cmdtlist"></datalist></td></tr>
		<input type="hidden" class="form-control"  id="txtCmdt" name="txtCmdt" style="text-transform:uppercase"  />		
                <tr><td class="lbl"><%=E.EH("Proposed Location Type")%></td><td>
                 <div class="form-group">
	                                              <select  id="locntype" tabindex="6" name="locntype" class="form-control">
	                                                  <option class="hidden"  selected disabled>Location Type</option>
	                                                  <option value="Station">Station</option>
	                                                  <option value = "District">District</option>
	                                                  <option value ="Division">Division</option>
	                                              </select>
	                                          </div>
			
		</td></tr>
		<tr><td class="lbl"><%=E.EH("Location")%></td><td><input type="text" class="form-control inptcap" id="locndesc" list="locnlist"  tabindex="7" name="locndesc" placeholder="Location"  onblur="validateLocn();" /><datalist id="locnlist"></datalist></td></tr>
               <input type="hidden" class="form-control"  id="txtLocn" name="txtLocn" style="text-transform:uppercase"  />		
                <tr><td class="lbl"><%=E.EH("Message")%></td><td><textarea class="form-control" tabindex="8" id="mesg" name ="mesg" rows="6" cols="80" placeholder="Write your mesg (Max 1000 Words)" value=""   maxlength="1000"  onchange="validateMesg();" ></textarea></td></tr>
</table><div   class="form-label-group">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="9" class="form-control" placeholder="Captcha" required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
                                            </table><div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		              </div>
									</table><div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="10"  value="Submit" onclick="return validateCaptcha();"><%=E.EH("Submit")%></button>
									</div>
	</table>
  </div>
</div>
</div>
</div>
</form>

<script>
                var cmdtstr='';
                for (var i=0; i < aRakeCmdt.length;++i){
                cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for station
                }
                var my_list=document.getElementById("cmdtlist");
                my_list.innerHTML = cmdtstr;

</script>
 <script>
            var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("locnlist");
            my_list.innerHTML = str;         
		</script>
<%@ include file="/pages/GG_Footer.jspf" %>
