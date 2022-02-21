<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ include file="/pages/GG_Header.jspf" %>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<script>
$(document).ready(function()
{
	fetchPvtWgonStats();
});
</script>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"OWN A PRIVATE WAGON","/RailSAHAY/pages/OwnWgon.jsp",UserDevice,Browser);
DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
Date dateobj = new Date();
String strCrntDate=df.format(dateobj);
%>
  	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("OWN YOUR WAGON")%></h3>
              </div>
            </div>
           </div>
          </div>
 <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
<div class="card bg-light text-dark">
  <div class="card-header"><%=E.EH("Wagons Inducted under Various Schemes")%> <span class="badge badge-info float-right">As on <%=strCrntDate%></span></div>
  <div class="card-body">
 <div class="row statsrow" id="divWgonSchmDtls"></div>
</div>
</div>
</div></div>
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card mr-3 ml-3">
	  <div class="card-header head-faq">
	    <%=E.EH("Invest in Indian Railways for Wagon(s)")%>
	  </div>
	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#WgonOne">
			   <%=E.EH("Liberalized Special Freight Train Operators (LSFTO)")%>
			 </a>
		       </div>
		       <div id="WgonOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   <%=E.EH("To promote transportation of non-traditional commodities like molasses, fly ash, edible oil, caustic soda, chemical, petrochemicals, alumina & bulk cement, by Indian Railways,  Liberalized Special Freight Train Operator (LSFTO) Scheme has been launched. The scheme encourages private entities to invest in procurement and operations of specialised wagons to transport these commodities and avail incentives")%>.
			  <div class="alert alert-success"><strong>Benefits of Liberalized Special Freight Train Operators (LSFTO)</strong><br/>
				<ul class="list-group list-group-flush">
				<li class="list-group-item">The logistics service provider/manufacturer can invest in special purpose wagons and can rope in other manufacturers for movement of their cargo.</li>
				<li class="list-group-item">Railway can help in designing such wagons.
				<li class="list-group-item">12% of freight rebate should be given to the owner for 20 years. 
				<li class="list-group-item">No freight will be charged for empty movement. 
				<li class="list-group-item">The owner of the rake can charge his customers for rail haulage terminal handling and ground rent on a market determine rate without Railway intervention.
				<li class="list-group-item">No demurrage will be charged if detained beyond free time in the private terminal.
				<li class="list-group-item">The special type of commodities which can be handled by this scheme are :
				<ol>
				<li>Bulk Fertilizers</li> 
				<li>Bulk Cement </li>
				<li>Fly Ash</li>
				<li>Bulk Chemicals </li>
				<li>Petrochemicals that includes Light Diesel Oil (LDO), Carbon Black feed Stock (CBFS), Low Sulphur Heavy Stock (LSHS), Heavy Petroleum Stock (HPS), Vacuum Gas Oil (VGO), Low Viscous Furnace Oil (LVFO), Low Sulphur Furnace Oil (LSFO), Residue Crude Oil (RCO) and Liquefied Petroleum Gas (LPG) (excluding Petroleum products like Naphtha, Aviation Turbine Fuel, High Speed Diesel, Kerosene Oil, Petrol, Furnace Oil) </li>
				<li>Caustic soda</li>
				<li>Liquid Ammonia</li>
				<li>Bulk Alumina</li>
				<li>Steel products requiring specially designed wagons</li>
				<li>Molasses</li>
				<li>Edible oil</li>
				<li>Bulk Food grain</li>
				</ul>		
			</div>

			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#WgonTwo">
			   <%=E.EH("Automobiles Freight Train Operator Scheme (AFTO)")%>
			 </a>
		       </div>
		       <div id="WgonTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">

			   <div class="row">
			   <div class="col-lg-6 col-sm-12" style="text-align: justify;">

			   <%=E.EH("To promote transportation of Automobiles by Indian Railways")%>, <b><%=E.EH("Automobile Freight Train Operator Scheme (AFTO)")%></b> <%=E.EH("scheme has been introduced. The scheme encourages private entities to invest in procurement and operation of special wagons designed to transport automobile. Indian Railways is developing Automobile hubs also where these special wagons can be handled")%>. <br/><br/>
			<div class="alert alert-success"><strong>Benefits of AFTO</strong><br/>
				<ul class="list-group list-group-flush">
				<li class="list-group-item">The logistics service provider can invest and transport auto-mobiles from production cluster to consumption centre by roping in auto manufacturers.</li>
				<li class="list-group-item">The owner of the rake can charge his customers for rail haulage terminal handling and ground rent on a market determine rate without Railway intervention.</li>
				<li class="list-group-item">No demurrage will be charged if detained beyond free time in the private terminal.</li>
				<li class="list-group-item">Separate rates shall be charged for Empty rakes and Loaded Rakes</li>
				</ul>		
			</div>
			   
			   </div>
			   <div class="col-lg-6 col-sm-12">
				<img src="/RailSAHAY/resources/images/bcacbm1.jpg" class="float-right" />

			   </div>
			   </div>
			   
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#WgonThree">
			   <%=E.EH("General Purpose Wagons")%>
			 </a>
		       </div>
		       <div id="WgonThree" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <%=E.EH("The General Purpose Wagons are the open and covered type conventional wagons, mostly owned by Indian Railways with a fleet of more than 2.5 lakh wagons. On the other hand, the special purpose wagons are designed to carry a particular commodity like BCACBM for Automobile traffic.  As there was long term demand from Railway Freight Wagons Users for better and timely availability of General Purpose Wagons (GPW), Indian Railways introduced a scheme for private investment in General Purpose Wagons in 2018.  The scheme allows procurement of General Purpose Wagons (BOX, BOXN, BCN etc.) by private entities")%>. <br/>
			   
			<div class="alert alert-success"><strong>Benefits of Investment in General Purpose Wagons</strong><br/>
				<ul class="list-group list-group-flush">
				<li class="list-group-item">Designed to carry multiple commodities.</li>
				<li class="list-group-item">Freight rebate/ credit of 10% will be given on loaded wagon movement.</li>
				<li class="list-group-item">This rebate will be granted for 15 years subject to recovery of principle amount and interest to be paid on the investment.</li>
				<li class="list-group-item">The wagons will not be merged with Indian Railways wagons and will move in pre-approved circuits.</li>
				<li class="list-group-item">No freight will be charged for empty movement. </li>
				<li class="list-group-item">No demurrage will be charged if detained beyond free time in the private terminal</li>
				</ul>		
			</div>
		       </div>
		     </div>
		</div>
	  </div>

<div class="row">		
				<div class="col-lg-12 col-sm-12">
				<div class="optncard">
				<ul class="nav nav-pills">
				    <li class="nav-item">
				        <a class="nav-link active" data-toggle="tab" href="#GPWIS"><%=E.EH("Application Process for GPWIS")%></a>
				    </li>
				    <li class="nav-item">
				        <a class="nav-link" data-toggle="tab" href="#LSFTO"><%=E.EH("Application Process for LSFTO")%></a>
				    </li>
				    <li class="nav-item">
				        <a class="nav-link" data-toggle="tab" href="#AFTO"><%=E.EH("Application Process for AFTO")%></a>
				    </li>

				</ul>
				</div>
				<div class="optncard">
				<div class="tab-content">
				  <div class="tab-pane container-fullwidth active" id="GPWIS">
					<iframe src="/RailSAHAY/pages/GPWISProcess.jsp" style="width:100%;height:75vh;border:0px;overflow:scroll;"></iframe>
				  </div>
				  <div class="tab-pane container-fullwidth fade" id="LSFTO">
					<iframe src="/RailSAHAY/pages/LSFTOProcess.jsp" style="width:100%;height:75vh;border:0px;overflow:scroll;"></iframe>
				  </div>
				  <div class="tab-pane container-fullwidth fade" id="AFTO">
					<iframe src="/RailSAHAY/pages/AFTOProcess.jsp" style="width:100%;height:75vh;border:0px;overflow:scroll;"></iframe>
				  </div>

				</div>
				</div>
			</div>
</div>
<div class="row"><div class="col-lg-6 col-sm-12">
<button class="btn btn-sm btn-info btn-madad"  onclick="gotoRailMadad();" style="margin-left:10px;"><%=E.EH("Contact Us")%>!</button>

</div></div>
</div>
      </div>
</div>
 </div>
<%@ include file="/pages/GG_Footer.jspf" %>
