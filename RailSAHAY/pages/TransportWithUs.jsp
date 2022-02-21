<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TRANSPORT WITH US","/RailSAHAY/pages/TransportWithUs.jsp",UserDevice,Browser);
%>
<%
	String strMessage="";
        if((String)request.getAttribute("message")!=null)
            strMessage =(String)request.getAttribute("message");
            
       System.out.println(strMessage);     
%>
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
<script>
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
          var capt=(document.getElementById('captcha').value);
	 	 
		
        
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
      
      jQuery(function ($) {
    var $inputs = $('input[name=txtEmail],input[name=txtMob]');
    $inputs.on('input', function () {
        // Set the required property of the other input to false if this input is not empty.
        $inputs.not(this).prop('required', !$(this).val().length);
    });
});
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">HELP US TO SERVE YOU BETTER !</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	 	<div class="card">
	          
	  	  <div class="card-header head-faq">
	  	    May we know..
	  	  </div>
	  	  <div class="card-body">
		   <form id="twus" method="POST" action="/RailSAHAY/NewCustomer">
		   <div id="accordion">	   
		     <div class="card" >
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#contact">
			   CONTACT DETAILS <span class="badge badge-blue float-right">A bit about You</span>
			 </a>
		       </div>
		       <div id="contact" class="collapse show">
			 <div class="card-body ">
                         <div class="form-group">
			 <table class="table table-striped formtable">
			 <tr><td><input type="text" class="form-control inptcap"  name="txtName" required  placeholder="Name" /></td></tr>
                         <tr><td><input type="email"  class="form-control" name="txtEmail" placeholder="Email" required /></td></tr>
                         <tr><td><input type="number"  class="form-control"  name="txtMob" placeholder="Mobile No." required /></td></tr>
		         </table>
	                   </div>                                                 
			   
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#Profile">
			  COMPANY PROFILE <span class="badge badge-green float-right">A bit about your Company</span>
			 </a>
		       </div>
		       <div id="Profile" class="collapse">
			 <div class="card-body">
                         <div class="form-group">
			<table class="table table-striped formtable">
                         <tr><td><input type="text" class="form-control inptcap"  name="txtCompName" placeholder="Company Name" /></td></tr>
                         
                         <tr><td>
                          <select  id="compType" tabindex="5" name="compType" class="form-control" >
                              <option class="hidden"  selected disabled>Company Type</option>
                              <option value="Shipper">Shipper</option>
                              <option value = "Receiver">Receiver</option>
                              <option value ="Logistics_Provider">Logistics Provider</option>
                              <option value ="Freight_Forwarder">Freight Forwarder</option>
                              <option value ="Other">Other</option>
                          </select>
                         </td></tr>
			   </table>
                           </div>
			 </div>
		       </div>
		     </div>
                    <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#cargo">
			  CARGO DETAIL  <span class="badge badge-red float-right">A bit about your Cargo</span>
			 </a>
		       </div>
		       <div id="cargo" class="collapse">
			 <div class="card-body">
			 <table class="table table-striped formtable">
			 <tr>
			 <td>
			    <select class="custom-select mr-sm-2" id="selOrigClstType" name="selOrigClstType">
                              <option selected value="D">Origin District</option>
                              <option value="S">Origin Station</option>
                            </select>				
			 </td>
			 <td colspan="2">
                         <input type="text" class="form-control inptcap"  list="orgnlist" id="txtOrgnLoc" name="txtOrgnLoc" placeholder="Origin Location"/>
	  		<datalist id="orgnlist"></datalist>
			 </td></tr>
			<tr>
			<td>
			    <select class="custom-select mr-sm-2" id="selDstnClstType" name="selDstnClstType">
                              <option selected value="D">Destination District</option>
                              <option value="S">Destination Station</option>
                            </select>
			 </td>
			 <td colspan="2">
                         <input type="text" class="form-control inptcap"  list="dstnlist" id="txtDstnLoc" name="txtDstnLoc" placeholder="Destination Location"/>
	                     <datalist id="dstnlist"></datalist>
			</td></tr>
			<tr><td colspan="3">
                        <input type="text" class="form-control inptcap"  list="cmdtlist" name="txtRakeCmdt" placeholder="Commodity" />
	                  <datalist id="cmdtlist"></datalist>
                        </td></tr>
			<tr><td colspan="3">
                           <label class="mr-sm-2" for="selPkgCndn">Packaging Condition</label>
                                <select class="form-control"    id="selPkgCndn" >
                                  <option selected>Choose</option>
                                  <option value="BL">Bulk Loose</option>
                                  <option value="BAG">Bagged</option>
                                  <option value="PBC">Pallet/Box/Carton</option>
                                  <option value="BLQ">Bulk Liquid</option>
                                  <option value="BG">Bulk Gas</option>
                                  <option value="LS">Live Stock</option>
                                  <option value="OT">Other</option>
                                  <option value="NA">Not Known</option>
                                 </select>
                        </td></tr>
			<tr><td style="width:33%;"> 
                         <input type="number" name="txtVol" class="form-control" id="txtVol" style="width:100%;" placeholder="Volume of Traffic"/>
			</td>
			<td style="width:33%;">
                          <select class="custom-select mr-sm-2" id="selVol" name="selVol">
                              <option selected>Unit</option>
                              <option value="T">Tonnes</option>
                              <option value="Q">Quintals</option>
                              <option value="P">Pieces</option>
                            </select>
			</td>
			<td style="width:33%;">
                          <select class="custom-select mr-sm-2" id="selPerd" name="selPerd">
                              <option selected>Period</option>
                              <option value="O">Only Once</option>
                              <option value="W">Per Week</option>
                              <option value="M">Per Month</option>
                              <option value="A">Per Year</option>
                              <option value="NA">Not Known</option>

                            </select>
			</td></tr>
			<tr><td colspan="3"> 
                        <span class="label label-default">Movement Type: </span>
                          <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="MvmtScheduled" name="mvmttype">
                            <label class="custom-control-label" for="MvmtScheduled">Scheduled</label>
                          </div>
                          <!-- Default inline 2-->
                          <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="MvmtOnDmnd" name="mvmttype">
                            <label class="custom-control-label" for="MvmtOnDmnd">On-Demand</label>
                          </div>
                          
                          <!-- Default inline 3-->
                          <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="AnyMvmt" name="mvmttype" checked>
                            <label class="custom-control-label" for="AnyMvmt">Any</label>
                          </div>
			   </td></tr>
			   
			   </table>
                           </div>
			 </div>
		       </div>
		     </div>

                <textarea name="txtRmrk" class="form-control"  placeholder="Remarks (Max 1000 Words)" rows="8" style="margin-right:10px;width:100%;"></textarea>
		<br/>
                <div style="margin-right:10px;width:100%;">
		<table class="table table-striped styletable"><tr><td>                
		    <div   class="form-label-group">
                    <input type="text" name="answer" id="answer" class="form-control" placeholder="Captcha" required>
                    </div>
		    </td>
	            <td>
                    <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
                    <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" ></img> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" ></img>
                  </div>
		    </td>
		    </tr>
		   </table>
                  </div>
                  <br/>
		<div align="center"><button type="button" class="btn btn-primary" onclick="return submitForm();">Submit</button></div><br/>
		</div>
		</div>
			  
			  	
	
      </div>      
      <br/>
      <div class="alert alert-danger" id="errorMesgDiv" style="display:none;"></div>

        </form>
</div>
      </div>
</div>
 <script>
                var strsttn='';
                for (var i=0; i < aSttn.length;++i)
		{
                	strsttn += '<option value="'+aSttn[i]+'" />'; // Storing options in variable
                }
		var strdstt='';
                for (var i=0; i < aDistrict.length;++i)
		{
                	strdstt+= '<option value="'+aDistrict[i]+'" />'; // Storing options in variable
                }


                var orgnlist=document.getElementById("orgnlist");
                orgnlist.innerHTML = strdstt;
                var dstnlist=document.getElementById("dstnlist");
                dstnlist.innerHTML = strdstt;
                
                var str="";            
                
                for (var i=0; i < gCNCode.length;++i)
		{
	                str += '<option value="'+gCNCode[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("cmdtlist");
                my_list.innerHTML = str;
                str="";            
                
                for (var i=0; i < gStck.length;++i)
		{
        	        str += '<option value="'+gStck[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("wgonlist");
                my_list.innerHTML = str;                
               
</script>
<script>
	$(document).ready(function(){
		$( "#selOrigClstType").change(function() {
  			var origclsttype=$( "#selOrigClstType").val();
			if(origclsttype=="S")
			{                
				var orgnlist=document.getElementById("orgnlist");
		                orgnlist.innerHTML = strsttn;
			}
			if(origclsttype=="D")
			{                
				var orgnlist=document.getElementById("orgnlist");
		                orgnlist.innerHTML = strdstt;
			}
		});
		$( "#selDstnClstType").change(function() {
  			var dstnclsttype=$( "#selDstnClstType").val();
			if(dstnclsttype=="S")
			{                
				var dstnlist=document.getElementById("dstnlist");
		                dstnlist.innerHTML = strsttn;
			}
			if(dstnclsttype=="D")
			{                
				var dstnlist=document.getElementById("dstnlist");
		                dstnlist.innerHTML = strdstt;
			}
		});
		
	});
</script>

<%@ include file="/pages/GG_Footer.jspf" %>
