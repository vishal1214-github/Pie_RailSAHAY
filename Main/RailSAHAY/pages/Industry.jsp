<%@ include file="/pages/GG_Header.jspf" %>
<% 
System.out.println("Commodities In the Session: Main Commodity ("+GG_MainCmdtName+"), Sub Commodity ("+GG_SubCmdtName+")");
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Commodites Main: "+GG_MainCmdtCode+"#"+GG_SubCmdtName,"/RailSAHAY/pages/Industry.jsp",UserDevice,Browser);
String strSubCmdt="";
if(GG_SubCmdtName.equals(""))
	strSubCmdt="Select Your Commodity";
else
	strSubCmdt=GG_SubCmdtName;

strSubCmdt="Select Your Commodity";

String strTrmlCont="";
String strRebtSchm="";
String strStckCont="";
String strTwoPtCombi="";
String strRateSlab="";
try
{
	strTrmlCont=(String)session.getAttribute("TrmlCont");
	strRebtSchm=(String)session.getAttribute("RebtSchm");
	strStckCont=(String)session.getAttribute("StckCont");
	strTwoPtCombi=(String)session.getAttribute("TwoPtCombi");
	strRateSlab="35";
}
catch(Exception e)
{
	strTrmlCont="";
	strRebtSchm="";
	strStckCont="";
	strTwoPtCombi="";
	strRateSlab="";
}

if(strTrmlCont==null)
{
	strTrmlCont="";
	strRebtSchm="";
	strStckCont="";
	strTwoPtCombi="";
	strRateSlab="";
}

String strBrochureFlag=((String)HtParamCntl.get("CMDT_BROCHURE")).trim();
if(strBrochureFlag==null)
	strBrochureFlag="N";

%>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<script src="/RailSAHAY/resources/js/sahayutil.js"></script>
<script src="/RailSAHAY/resources/js/app_integration.js"></script>
<script>
var GG_MainCmdtCode="<%=GG_MainCmdtCode%>";
$(document).ready(function() {
	fetchDrishtiStats();
});
</script>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
<link href="/RailSAHAY/resources/css/industry.css" rel="stylesheet" />


	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100" id="hCmdtHead"><%=GG_MainCmdtName%></h3>
              </div>
            </div>
           </div>
          </div>
          
<div class="container-fullwidth">
          <div class="row header-image">
<div class="col-sm-12" align="center">
	<div class="image-wrapper">
	<%--<h1 class="header-overlay-h1" id="statwgontype"></h1>--%>
	<h2 class="header-overlay-h2"></h2>
	<img src="/RailSAHAY/resources/images/industry/<%=GG_MainCmdtCode%>.jpg" width="90%" class="img-responsive img-fluid" data-device-desktop="desktop" id="imgCmdt" alt="">
	</div>					
</div>	
</div></div> 

          <div class="container">
		<div class="row">
		<div class="col-lg-12">
		  <p class="p-cmdtdesc mt-3">We offer multiple solutions for Transportation of <%=GG_MainCmdtName%>. <br/>There are <span id="spnSubCmdtCont" class="statsfig cmdt-cont"></span> types of <%=GG_MainCmdtName%> products being transported by Indian Railways.</p>		  
		<hr/>

		<div id="divCmdtStats">
		<p class="cmdt-stats-head"><%=E.EH("We are proud to touch new heights in million tonnes (MT) loading of")%>&nbsp;<strong><%=E.EH(GG_MainCmdtName)%></strong></p>
		<div class="row">
		<div class="col-md-3">
        <div class="stati pumpkin left">
          <i class="fas fa-train fa-2x"></i>
          <div>
            <b id="spnDlyTngeC"></b>
            <span>Yesterday</span>
          </div> 
        </div>
      </div> <div class="col-md-3">
        <div class="stati pumpkin left">
          <i class="fas fa-train fa-2x"></i>
          <div>
            <b id="spnWkTngeC"></b>
            <span>Last 7 Days</span>
          </div> 
        </div>
      </div> <div class="col-md-3">
        <div class="stati pumpkin left">
          <i class="fas fa-train fa-2x"></i>
          <div>
            <b id="spnMnthTngeC"></b>
            <span>Month to Date</span>
          </div> 
        </div>
      </div> <div class="col-md-3">
        <div class="stati pumpkin left">
          <i class="fas fa-train fa-2x"></i>
          <div>
            <b id="spnYearTngeC"></b>
            <span>Year to Date</span>
          </div> 
        </div>
      </div> 
    </div>
 </div>
<hr/>
		<h4 style="color:#000;" class="mt-4">Advantages for you</h4>
		<ul>
			<li><%=E.EH("High-performance transport built on smart transport concepts")%></li>
			<li><%=E.EH("Product purity, ensured by our fleet of specialised wagons")%></li>
			<li><%=E.EH("Simple integration of our logistical services into your logistics processes")%></li>
			<li><%=E.EH("High level of efficiency due to precisely scheduled turnaround cycles and reasonable time limits for loading and unloading")%></li>
		</ul>
		</div>
		</div>
	  <h3 class="font-weight-light text-center text-md-left mt-4 mb-0"><%=E.EH("May we know your Specific Commodity")%></h3>
	
	  <hr class="mt-2 mb-2" />
	
	  <div class="row text-center text-lg-left">
	    <div class="col col-lg-8 offset-lg-2 col-sm-12">
	    <table style="width:100%;"><tr><td>
	    <input class="form-control mr-sm-2 inptcap" id="selCmdtList" list="cmdtlist"  name="selCmdtList" type="text" placeholder='<%=strSubCmdt%>' style="width:100%;">
	    <datalist id="cmdtlist"></datalist>
	    </td><td><button class="btn btn-danger" onclick="updtSubCmdt();">Continue</button></td></tr>
	    <% if(!GG_SubCmdtCode.equals("")) { %>
		<tr><td><h4 class="mb-2 subcmdthead" data-aos="fade-up" data-aos-delay="100" id="hSubCmdtHead"><%=GG_SubCmdtName%></h4></td><td>&nbsp;</td></tr>
	    <% } %>
	</table>
	  </div>
	
	</div>
<hr class="mt-2 mb-5">
		</div>
		</div>

<div class="container animate-bottom" id="divCmdtFeature">
<h2 class="heading-title">Let's help you plan the right transportation..</h2>

<div class="row mt-3">
   <div class="col-xl-12 col-lg-12 col-sm-12">
        <div class="container-fullwidth">
		<div class="row row-right" onclick="showPage('CMDT_TRML_SRCH');">
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iTrmlSrch.jpg" class="img-responsive img-fluid img-optn" />
			</div>
			<div class="col-lg-6 col-sm-12">
				<%if(!strTrmlCont.equals("")) { %>
				<p class="statsfig" id="pTrmlStat"><%=strTrmlCont%></p>
				<% } else { %>
				<p class="statsfig" id="pTrmlStat"><div class="spinner-border text-danger statholder"></div></p>
				<% } %>
				<p class="cmdtstatdesc" id="pTrmlStatDesc">Terminals to handle your freight transportation</p>
				<hr/>
				<p class="pCmdtHeadLbl">Terminal Search</p>
				<p class="pCmdtDesc">Search for the most suitable terminal for your freight transportation through our Terminals Dashboard <a href="javascript:void(0)" class="narr-link"  onclick="showPage('CMDT_TRML_SRCH');">Learn More</a></p>

			</div>			
		</div>
		<div class="row row-left" onclick="showPage('CMDT_WGON_CTLG');">
			<div class="col-lg-6 col-sm-12">
				<%if(!strStckCont.equals("")) { %>
				<p class="statsfig" id="pWgonCtlg"><%=strStckCont%></p>
				<% } else { %>
				<p class="statsfig" id="pWgonCtlg"><div class="spinner-border text-danger statholder"></div></p>
				<% } %>
				<p class="cmdtstatdesc" id="pWgonCtlgDesc">Distinct wagon types to load your commodity</p>
				<hr/>
				<p class="pCmdtHeadLbl">Wagon Catalogue</p>
				<p class="pCmdtDesc">We offer a variety of wagon types for handling this commodity, please check our catalogue and select the most suitable wagon <a href="javascript:void(0)" class="narr-link" onclick="showPage('CMDT_WGON_CTLG');">Learn More</a></p>
			</div>
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iWgonCtlg.jpg" class="img-responsive img-fluid img-optn" />
			</div>			
		</div>
		<div class="row row-right" onclick="showPage('CMDT_REBT_SCHM');">
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iScheme.jpg" class="img-responsive img-fluid img-optn" />
			</div>
			<div class="col-lg-6 col-sm-12">
				<%if(!strRebtSchm.equals("")) { %>
				<p class="statsfig" id="pRebtSchm"><%=strRebtSchm%></p>
				<% } else { %>
				<p class="statsfig" id="pRebtSchm"><div class="spinner-border text-danger statholder"></div></p>
				<%}%>
				<p class="cmdtstatdesc" id="pRebtSchmDesc">Exclusive Rebate Schemes to avail</p>
				<hr/>
				<p class="pCmdtHeadLbl">Incentive Schemes</p>
				<p class="pCmdtDesc">We offer a range of innvoative freight incentive schemes.Get to know about your eligibility and the detail of each scheme. <a href="javascript:void(0)"  onclick="showPage('CMDT_REBT_SCHM');" class="narr-link">Learn More</a></p>
			</div>			
		</div>
		<div class="row row-left" onclick="showPage('CMDT_RATE_SLAB');">
			<div class="col-lg-6 col-sm-12">
				<%if(!strRateSlab.equals("")) { %>
				<p class="statsfig" id="pRateSlab"><%=strRateSlab%></p>
				<% } else { %>
				<p class="statsfig" id="pRateSlab"><div class="spinner-border text-danger statholder"></div></p>
				<% } %>
				<p class="cmdtstatdesc" id="pRateSlabDesc">Distinct Rate slabs for a distance range upto 3500 Kms</p>
				<hr/>
				<p class="pCmdtHeadLbl">Rate Slabs</p>
				<p class="pCmdtDesc">Competitive and Economical Rates for different distance ranges! Check our Rate Slabs for your Commodity. <a href="javascript:void(0)" class="narr-link" onclick="showPage('CMDT_RATE_SLAB');">Learn More</a></p>
			</div>
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iRateSlab.jpg" class="img-responsive img-fluid img-optn" />
			</div>			
		</div>
		<% if(!(GG_MainCmdtCode.equals("CONT") ||  GG_MainCmdtCode.equals("AUTO"))) { %>
		<div class="row row-right" onclick="showPage('CMDT_FRGT_CALC');">
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iCalc.jpg" class="img-responsive img-fluid img-optn" />
			</div>
			<div class="col-lg-6 col-sm-12">
				<p class="pCmdtHeadLbl">Smart Calculator</p>
				<p class="pCmdtDesc">Get to know about Expected Freight Charges and Estimated Transit time for your freight transportation over Indian Railways. <a href="javascript:void(0)" class="narr-link" onclick="showPage('CMDT_FRGT_CALC');">Learn More</a></p>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Alternate VIKALP Routes</li>
					<li class="list-group-item">Applicabled Incentive Schemes and Discounted Freight</li>
					<li class="list-group-item">Estimate Transit Time and more...</li>
				</ul>
			</div>			
		</div>		
		<div class="row row-left" onclick="showPage('CMDT_ML_POINT');">
			<div class="col-lg-6 col-sm-12">
				<%if(!strTwoPtCombi.equals("")) { %>
				<p class="statsfig" id="pCmdtMLPont"><%=strTwoPtCombi%></p>
				<% } else { %>
				<p class="statsfig" id="pCmdtMLPont"><div class="spinner-border text-danger statholder"></div></p>
				<%}%>
				<p class="cmdtstatdesc" id="pCmdtML">Unique TwoPoint/Multi-Point Combinations for your cargo movement</p>
				<hr/>
				<p class="pCmdtHeadLbl">Two-Point Combinations</p>
				<p class="pCmdtDesc">We offer facility to transport your cargo in a Train for more than one destinations using Two Point/multi point schemes while availing Train load freight rates. <a href="javascript:void(0)" class="narr-link" onclick="showPage('CMDT_ML_POINT');">Learn More</a></p>
			</div>
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iTwoPont1.jpg" class="img-responsive img-fluid img-optn" />
			</div>			
		</div>
		<% } else { %>				
		<div class="row row-right" onclick="showPage('CMDT_ML_POINT');">			
			<div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/industry/iTwoPont1.jpg" class="img-responsive img-fluid img-optn" />
			</div>	
			<div class="col-lg-6 col-sm-12">
				<%if(!strTwoPtCombi.equals("")) { %>
				<p class="statsfig" id="pCmdtMLPont"><%=strTwoPtCombi%></p>
				<% } else { %>
				<p class="statsfig" id="pCmdtMLPont"><div class="spinner-border text-danger statholder"></div></p>
				<%}%>
				<p class="cmdtstatdesc" id="pCmdtML">Unique TwoPoint/Multi-Point Combinations for your cargo movement</p>
				<hr/>
				<p class="pCmdtHeadLbl">Two-Point Combinations</p>
				<p class="pCmdtDesc">We offer facility to transport your cargo in a Train for more than one destinations using Two Point/multi point schemes while availing Train load freight rates. <a href="javascript:void(0)" class="narr-link" onclick="showPage('CMDT_ML_POINT');">Learn More</a></p>
			</div>		
		</div>
		<% } %>
		<p class="mesg-cnt"><%=E.EH("For any assistance regarding your commodity transportation")%> <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;<%=E.EH("Contact Us")%></a></p>
	</div>
   </div>
</div>
</div>
	             
<%--
		 <div class="row mt-3">
	                  <div class="col-lg-10 offset-lg-1 col-sm-12">
	                      <div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="main-timeline">
                <div class="timeline">
                    <a href="javascript:void(0)" onclick="showPage('CMDT_TRML_SRCH');" class="timeline-content">
                        <h3 class="title"><i class="fas fa-search-location"></i> Terminal Search</h3>
                        <p class="description">
                            Search for a most suitable terminal for your freight transportation through our Terminals Dashboard
                        </p>
                    </a>
                </div>
                <div class="timeline">
                    <a href="javascript:void(0)" onclick="showPage('CMDT_WGON_CTLG');" class="timeline-content">
                        <h3 class="title"><i class="fas fa-train"></i> Wagon Catalogue</h3>
                        <p class="description">
                            We offer a variety of wagon types for handling this commodity, please check our catalogue and select the most suitable wagon.
                        </p>
                    </a>
                </div>
                <div class="timeline">
                    <a href="javascript:void(0)" onclick="showPage('CMDT_REBT_SCHM');" class="timeline-content">
                        <h3 class="title"><i class="fas fa-piggy-bank"></i> Incentive Schemes</h3>
                        <p class="description">
                            We offer a range of innvoative freight incentive schemes.Get to know about your eligibility and the detail of each scheme.
                        </p>
                    </a>
                </div>
                <div class="timeline">
                    <a href="javascript:void(0)" onclick="showPage('CMDT_RATE_SLAB');" class="timeline-content">
                        <h3 class="title"><i class="fas fa-tags"></i> Rate Slabs</h3>
                        <p class="description">
                           Competitive and Economical Rates for different distance ranges! Check our Rate Slabs for your Commodity
                        </p>
                    </a>
                </div>
                <div class="timeline">
                    <a href="javascript:void(0)" onclick="showPage('CMDT_FRGT_CALC');" class="timeline-content">
                        <h3 class="title"><i class="fas fa-calculator"></i> Smart Calculator</h3>
                        <p class="description">
                            Get to know about Expected Freight Charges and Estimated Transit time for your freight transportation over Indian Railways
                        </p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>	    
--%>

<% if(strBrochureFlag.equals("Y")) { %>
<hr/>
<div class="container">
<div class="card-brochure" onclick="downloadMainCmdtPDF();">
		<p class="brochure-head">Brochure: <%=GG_MainCmdtName%></p>
		<p class="brochure-dn">Download PDF | 2MB</p>
</div>
<form id="frmMainBrochure" name="frmMainBrochure" action="/RailSAHAY/CommodityPDF" method="post" target="MainCmdtBrochure">
	<input type="hidden" id="MainCmdtCode" name="MainCmdtCode" value="<%=GG_MainCmdtCode%>" />
	 <input type="hidden" id="MainCmdtName" name="MainCmdtName" value="<%=GG_MainCmdtName%>" />
	 <input type="hidden" id="SubCmdtCode" name="SubCmdtCode" value="" />
        <input type="hidden" id="SubCmdtName" name="SubCmdtName"  value="" />
</form>
</div>
<% } %>

              </div>
	              </div>
	          </div>
<script src="/RailSAHAY/resources/js/cmdtview.js"></script>
<script>
$(document).ready(function() {
	<% if(GG_SubCmdtCode.equals("")) {%>
	$("#divCmdtFeature").hide();
	<% } %>
	fetchNumCmdtList('<%=GG_MainCmdtCode%>','CMDT_HOME');
});
</script>
<%@ include file="/pages/GG_Footer.jspf" %>

            