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

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"OWN A PRIVATE WAGON","/RailSAHAY/pages/OwnWgon.jsp",UserDevice,Browser);
%>
<script>	
            $(document).ready(function() {
$( "input" ).keypress(function() {
  $("#errorMesgDiv").val("");
    document.getElementById('errorMesgDiv').style.display="none";
  $("#sucsMesgDiv").val("");
    document.getElementById('sucsMesgDiv').style.display="none";

});
});


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
    document.frmInpt.txtEmail.focus();
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

  	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">OWN A WAGON</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card mr-3 ml-3">
	  <div class="card-header head-faq">
	    Invest in Indian Railways for Wagon(s)
	  </div>
	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#WgonOne">
			   Special Freight Train Operators (SFTO)
			 </a>
		       </div>
		       <div id="WgonOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   To promote transportation of non-traditional commodities like molasses, fly ash, edible oil, caustic soda, chemical, petrochemicals, alumina & bulk cement, by Indian Railways,  Special Freight Train Operator (SFTO) Scheme has been launched. The scheme encourages private entities to invest in procurement and operations of specialised wagons to transport these commodities and avail incentives.
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#WgonTwo">
			   Automobiles Freight Train Operator Scheme (AFTO)
			 </a>
		       </div>
		       <div id="WgonTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">

			   <div class="row">
			   <div class="col-lg-6 col-sm-12" style="text-align: justify;">

			   To promote transportation of Automobiles by Indian Railways, <b>Automobile Freight Train Operator Scheme (AFTO)</b> scheme has been introduced. The scheme encourages private entities to invest in procurement and operation of special wagons designed to transport automobile. Indian Railways is developing Automobile hubs also where these special wagons can be handled. <br/><br/><button data-toggle="collapse" class="btn btn-info btn-sm" data-target="#divAFTO">Application Process</button>

			   </div>
			   <div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/bcacbm1.jpg" class="float-right" />

			   </div>
			   </div>
			   <div class="row collapse" id="divAFTO">
				<div class="col-lg-12">
					<br/><br/><hr/>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
					Indian Railways has notified new Automobile Freight Train Operator (AFTO) Scheme under which logistic service providers can induct special wagon on Railways. It was launched in July-10-2013 and allowed private sector to induct and operate the wagons on network.					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Eligibility Criteria</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item"><b>Applicant should be one of the following:</b></li>
								<li class="list-group-item">A registered company in India as per Companies Act 1956 </li>
								<li class="list-group-item">A Subsidiary Company</li>
								<li class="list-group-item">A Joint Venture Company Or Partnership</li>
								<li class="list-group-item">A Public sector entity in the business of logistics</li>
								<li class="list-group-item">The application should have Minimum 1 year experience in any one of the following fields</li>
								<li class="list-group-item">Transport and logistics </li>
								<li class="list-group-item">Port and land terminal operations</li>
								<li class="list-group-item">Warehousing</li>
								<li class="list-group-item">Container Train Operation</li>
								<li class="list-group-item">Manufacturers of Automobiles</li>
								<li class="list-group-item">Wagon leasing company</li>
								<li class="list-group-item">The company should have a net worth of Minimum Rs 20 Crores an annual turnover of Minimum 30 crores as on 31st March of the previous financial Year</li>
								<li class="list-group-item">Any company which has been declared sick under sick Industrial Companies (Special Provision) Act 1985 shall not be eligible to apply under these rules and operate trains either individually or association with other companies</li>
								<li class="list-group-item">In case the applicant is subsidiary company, experience and net worth of the holding company, owning more than 50% equity in the subsidiary company, may be reckoned for the purpose S.N.06 & 07 as above. However, in such case, the applicant company must have more than 25% of the prescribed net worth of Rs. 20 Crore</li>
								<li class="list-group-item">There shall be no change of control of the AFTO through transfer of direct or indirect legal or beneficial ownership or control of equity or other contractual arrangement before the completion of one year from commencement of commercial operation of the AFTO�s train pursuant to the Concession Agreement, in which after wards there may be approval from MOR, and same may be rejected or approved</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Registration Fee and Validity</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Apply to ADV/EDFM with 1% of registration fee Rs. (3 Crores) along with the application</li>
								<li class="list-group-item">The applicant has to apply for a minimum 3 rakes under this scheme</li>
								<li class="list-group-item">If application is rejected by MOR than amount will be refunded within one month of rejection of the proposal</li>
								<li class="list-group-item">The applicant shall furnish following details while submitting the application
								<ul>
									<li>Name of the Applicant (Firm)</li>
									<li>Address of the Applicant (Firm)</li>
									<li>Details of experience and activities of the applicant</li>
									<li>Document in support of the eligibility criteria as above</li>
									<li>Document in support of net worth/ turnover of the company as on 31st March of the last financial year like audited balanced sheet and / or document duly certified by a CA</li>
									<li>PAN of the applicant</li>
									<li>Number of rakes planned</li>
									<li>Type of wagon</li>
									<li>Anticipated Traffic volume with identification of possible circuits</li>
									<li>Proposed loading terminal & destination terminals</li>
									<li>Any other relevant information</li>
								</ul>
								</li>
								<li class="list-group-item">MOR will study the proposal and approval may be comminuted to party to deposit the balance registration amount through Demand draft/ Banker�s cheque/ Pay order only, favour principal Financial Adviser, Northern Railway</li>
								<li class="list-group-item">After that CCM/FM of the Zonal Railway will signed the agreement. The applicants will procure full rake composition with 4% additional wagon as spare</li>
								<li class="list-group-item">The AFTO intends to induct additional rakes under this scheme for which the registration fee has been paid, the same may be permitted by Railways without payment of additional fee. Conversely if he wants to withdraw any number of rakes he will be permitted to do so without any refund of any refund of registration fee</li>

							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Procurement of Wagon</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Procurement of wagons will be allowed only with prior administrative approval of MOR and must conform to application IRS designs and specification same will be inspection by RDSO</li>
								<li class="list-group-item">The Applicant will be required to incorporate warranty clause in their purchase contract</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Signing of Agreement</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">After all approvals and before induction of rake, AFTO shall sign an agreement with Railway Administration as per format prescribed by MoR</li>
								<li class="list-group-item">No movement of the rakes shall be permitted prior to the signing of this concession agreement</li>
								<li class="list-group-item">An option is provided for AFTO agreement may be terminated before expiry and transferred to another valid AFTO</li>
							</ul>
						</div>
  					</div>
					</li>
				</ul>				
				 <br/><div align="center"><button data-toggle="collapse" class="btn btn-info btn-sm" data-target="#divAFTO">Close</button></div>
				</div>
			   </div>
				
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#WgonThree">
			   General Purpose Wagons
			 </a>
		       </div>
		       <div id="WgonThree" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   The General Purpose Wagons are the open and covered type conventional wagons which are mostly used to transport bulk commodities like coal, cement, food grain, fertilizer, ores etc by Indian Railways.  These types of wagons are mostly owned by Indian Railways with a fleet of more than 2.5 lakh wagons.  On the other hand, the special purpose wagons are designed to carry a particular commodity like BCACBM for Automobile traffic.  As there was long term demand from Railway Freight Wagons Users for better and timely availability of General Purpose Wagons (GPW), Indian Railways introduced a scheme for private investment in General Purpose Wagons in 2018.  The scheme allows procurement of General Purpose Wagons (BOX, BOXN, BCN etc.) by private entities. <br/><br/><button data-toggle="collapse" class="btn btn-info btn-sm" data-target="#divGPWIS">Application Process</button>
			   <div id="divGPWIS" class="collapse">
				<br/><br/><hr/>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
					General Purpose wagons (BOX, BOXN, BCN etc.) approved by RDSO to run over the routes approved by Indian Railways. Procurement of Wagons will be allowed with prior Administrative approval of Ministry of RAILWAYS (MOR).
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Eligibility Criteria</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Producers or consumers of the Goods to be transported by Rail</li>
								<li class="list-group-item">PSUs, Central Public Sector Enterprises</li>
								<li class="list-group-item">Logistics providers</li>
								<li class="list-group-item">Port Owners /Poet Rail companies</li>
								<li class="list-group-item">Mine Owners</li>
								<li class="list-group-item">Wagon Leasing Company (WLC) for use of end users</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Details of Applicant</div>
    						<div class="card-body">
							Application, along with specific details Number of rakes required, Types of Wagons, Loading Station (S) Destination Station(S), proposed specific route (S) or Close circuit and any other information relevant to the proposal.
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Application Registration</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Application, along with specific details of the proposal, should be submitted to Executive Director/ Freight Marketing (EDFM) Railway Board. The proposal shall be examined in consultation with Traffic Transportation (TT) Directorate of Railway Board</li>
								<li class="list-group-item">If the proposal is found operationally feasible, an approval letter from Ministry of Railways permitting Procurement of Rakes under GPWIS on the approved circuit shall be issued for Principal Chief Operating Manager (PCOM) and Principal Chief Commercial Manager (PCCM) of Concerned Zonal Railways</li>
								<li class="list-group-item">On the basis of the approval of the Railway Board, an agreement will be signed between the PCCM of the concerned Zonal Railway and the applicant within 06( six) months from the date of approval from Railway Board</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Procurement of Wagon</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Wagon should be procured in units of full rake with 4% maintenance spares and on brake van. A minimum of one Rake has to be invested in, to participate in the scheme</li>
								<li class="list-group-item">Supplies shall be guaranteed against any manufacturing defect/poor workmanship from the date of delivery, whichever is later</li>
								<li class="list-group-item">Information regarding placement of order for procurement of rakes may be advised by party (signatory to agreement) to the PCOM of the concerned Zonal Railway as well as EDFM, Railway Board. The Zonal Railway will keep the details of the wagons and brake van procured for each rake by the Party (signatory to agreement</li>
								<li class="list-group-item">The rakes procured under GPWIS will operate between Private siding/ terminals or Private Freight terminal (PFTs) or Inland Container Depots(ICDs ) or Ports or Mines equipped to handle the traffic for which GPWIS end users must have a tie-up with such  private siding/terminal , PFTs, ICDs, Ports, mines or own its private terminals/ siding for handling of such wagons</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Signing of Agreement</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">After approval from Railway Board, an agreement will be signed between Zonal Railway and Applicant within 6months from date of approval from Railway Board</li>
								<li class="list-group-item">The period of agreement for each rake will be for the codal life of the specific stock as specified at the time of induction of the rake by the Ministry of Railways</li>
							</ul>
						</div>
  					</div>
					</li>
					<li class="list-group-item">
  					<div class="card">
						<div class="card-header headcard headbgcard"><i class="fas fa-caret-right" ></i>&nbsp;Operation/Maintenance of Wagon</div>
    						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">The Rake inducted under GPW Schemes shall not be merged in IR�s pool of wagons and will be indicated through a colour scheme. The rake so inducted shall run on pre- approved circuits</li>
								<li class="list-group-item">A rebate of 10% shall be given on the base freight on each loaded wagon. Such rebate shall, however, be ordinarily for a period of 15 years subject to a cap to the extent of the lease charges payable by IR to IRFC for procurement of Rolling Stock</li>
								<li class="list-group-item">The period of agreement for each rake will be for the codal life of the specific stock as specified at the time of induction of the rake by the Ministry of Railways</li>
							</ul>
						</div>
  					</div>
					</li>
						
				</ul>
					<br/><div align="center"><button data-toggle="collapse"  class="btn btn-info btn-sm" data-target="#divGPWIS">Close</button></div>
			   </div>
			 </div>
		       </div>
		     </div>
		</div>
	  </div>	  
	  	</div>
      </div>
</div>
      </div>
<br/>
<form class="form-horizontal" method="post" action="/RailSAHAY/SHY_OwnWgon" enctype="multipart/form-data"    id="frmInpt" name="frmInpt" >
<div class="row">
<div class="col-lg-10 offset-lg-1 col-sm-12">
<div class="card">
  <div class="card-header headcard">For further details and support for owning wagons please contact us</div>
  <div class="card-body">
  <div class="container1" style="margin-top:10px;display: flex;justify-content:center;">
<div class="alert alert-danger" id="errorMesgDiv" style="display:none;width:400px;"></div>
<div class="alert alert-success" id="sucsMesgDiv" style="display:none;width:400px;"></div>
</div>
	<table class="table table-striped table-bordered tabformat">
		<tr><td class="lbl">Name</td><td><input type="text" class="form-control inptcap" id="txtName" name="txtName" placeholder="Name *" tabindex="1" required="required"  maxlength="30"  onchange="validateName();"  onclick="hideMesg()"; /></td></tr>
		<tr><td class="lbl">Company Name</td><td><input type="text" class="form-control inptcap" id="txtCompName" name="txtCompName" placeholder="Company Name *" tabindex="2" required="required"  maxlength="100"  onchange="validateCmpName();"/></td></tr>
		<tr><td class="lbl">Email Id</td><td><input type="email" class="form-control" id="txtEmail" name="txtEmail"  tabindex="3" placeholder="Your Email *" value=""   maxlength="40" required="required"   onchange="validateEmail();" style="text-transform:lowercase;"/></td></tr>
		<tr><td class="lbl">Mobile No.</td><td><input type="number" class="form-control" id="txtMobl" name="txtMobl"  tabindex="4"  placeholder="Your Phone *" value="" required="required"  onchange="validateMob();" /></td></tr>
		<tr><td class="lbl">Commodity</td><td><input type="text" class="form-control inptcap" id="cmdtdesc" list="cmdtlist" name="cmdtdesc"  tabindex="5" placeholder="Commodity"  onblur="validateCmdt();" /><datalist id="cmdtlist"></datalist></td></tr>
		<input type="hidden" class="form-control"  id="txtCmdt" name="txtCmdt" style="text-transform:uppercase"  >
              <?audit suppress oracle.ide.xml.validation-error?>
            </input>
            		
		<tr><td class="lbl">Wagon Type</td><td>
                <div class="form-group">
	                       <select  id="selWgonType" tabindex="6" name="selWgonType" class="form-control">                         
			  <option class="hidden"  selected disabled>Wagon Type</option>
				<option value="SPECIAL WAGON">SPECIAL WAGON</option>
				<option value="AUTOMOBILE">AUTOMOBILE</option>
				<option value="GENERAL PURPOSE">GENERAL PURPOSE</option>
			</select>                        
	                                          </div>
                        
		</td></tr>
                  <tr><td class="lbl">Message</td><td><textarea class="form-control" tabindex="7" id="mesg" name ="mesg" rows="6" cols="80" placeholder="Write your mesg (Max 1000 Words)" value=""   maxlength="1000"  onchange="validateMesg();" ></textarea></td></tr>
	</table>
        <div   class="form-label-group">
<table><tr><td>
		                <input type="text" name="answer" id="answer" tabindex="8" class="form-control" placeholder="Captcha" required>
</td><td>		                <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" /> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" />

</td></tr></table>
                                           </div>
                                           </br>
                                           <div style="width: 60%; margin: 0px auto;"  class="form-label-group">
		              </div>
                               <div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="9"  value="Submit" onclick="return validateCaptcha();">Submit</button>
									</div>
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
<%@ include file="/pages/GG_Footer.jspf" %>
