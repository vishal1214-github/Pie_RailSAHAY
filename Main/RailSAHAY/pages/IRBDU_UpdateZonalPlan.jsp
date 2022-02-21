<%@ include file="/pages/IRBDUHeader.jspf" %>
<%@ page import="model.AcqnPlanSrvc" %>
<%@ page import="model.AcqnPlanMISSrvc" %>
<% 
String strZone="";
String strStts="";
String strErorMesg="";
String strCnfmTrxnStts="";
String strMaxAnlyDate="";
try
{
	strCnfmTrxnStts=((String)request.getParameter("CnfmStts")).trim();
}
catch(Exception e)
{
    strCnfmTrxnStts="";
}

try
{
    strZone=((String)session.getAttribute("Zone")).trim();
    strStts=((String)session.getAttribute("Stts")).trim();
    strErorMesg=((String)session.getAttribute("ErorMesg")).trim();
}
catch(Exception e)
{
    strZone="";
    strStts="";
    strErorMesg="";
}
	try
	{
		AcqnPlanMISSrvc obj=new AcqnPlanMISSrvc();
		strMaxAnlyDate=obj.getMaxAnlyDate();
	}
	catch(Exception e)
	{
		strMaxAnlyDate="";
	}



session.setAttribute("Zone","");
session.setAttribute("Stts","");
session.setAttribute("ErorMesg","");
AcqnPlanSrvc obj=new AcqnPlanSrvc(strUserId,strClntId);
String strCnfmStts="";
String strDvsnZone="";
if(strLognLocnFlag.equals("Z"))
{
	strCnfmStts=obj.getDataCnfmStts(strLognLocn,"");
}
if(strLognLocnFlag.equals("D"))
{
	strCnfmStts=obj.getDataCnfmStts("",strLognLocn);
	strDvsnZone=(String)session.getAttribute("CrntZone");
}

%>
<script src="https://phpcoder.tech/multiselect/js/jquery.multiselect.js"></script>
<link rel="stylesheet" href="https://phpcoder.tech/multiselect/css/jquery.multiselect.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bdupdateplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/aLocnAddl.js"></script>
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aGTCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aRakeType.js"></script>
<script src="/RailSAHAY/resources/js/asttn.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/aSpclLocn.js"></script>
<script src="/RailSAHAY/resources/js/Cust.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/GG_LOV.js"></script>
<script>
    var GG_SelZone="";
    var GG_CrntZone="";
    var GG_CrntLocn="";
    var GG_LognLocnFlag="<%=strLognLocnFlag%>";
    var GG_DvsnZone="<%=strDvsnZone%>";
    GG_CrntLocn="<%=strLognLocn%>";
    var GG_MaxAnlyDate="<%=strMaxAnlyDate%>";
    var GG_DataCnfmStts="<%=strCnfmStts%>";
	
    <% if(strLognLocnFlag.equals("Z")) { %>
    	GG_CrntZone="<%=strLognLocn%>";
    <% } %>
    $(document).ready(function(){  
	<%--
	$('#selZone option[value="'+GG_CrntZone+'"]').attr("selected", "selected");
	<% if(strLognLocnFlag.equals("Z")) { %>
		$("#selZone").val("<%=strLognLocn%>");
	<% } %>
--%>
    <% if(strCnfmTrxnStts.equals("SUCCESS")) { %>
	successToast("Data Confirmed/Frozen for the Month Successfully !");
    <% } %>
	$('.dropdown-menu a').click(function (e) {
    $('.active').removeClass('active');
});
    });
</script>
<script src="/RailSAHAY/resources/js/irbdu/bdupdateplan.js"></script>
<div class="rprt-head">
    <p class="rprt-head">Update Business Development and Acquisition Plan</p>
</div>
<div class="inptdiv">
    <table>
        <tr>
            <td class="inpt-val">
		<%--
                <select id="selZone" name="selZone" class="form-control inpt-control inpt-select">
                   <option value="">SELECT ZONE</option>
                   <option value="BPT">BPT - MUMBAI PORT TRUST</option>
                    <option value="CPT">CPT - CULCUTTA PORT TRUST</option>
                    <option value="CR">CR - CENTRAL RAILWAY</option>
                    <option value="DFCR">DFCR - DEDICATED FREIGHT CORRIDOR RAILWAY</option>
                    <option value="EC">EC - EAST CENTRAL RAILWAY</option>
                    <option value="ECO">ECO - EAST COAST RAILWAY</option>
                    <option value="ER">ER - EASTERN RAILWAY</option>
                    <option value="KR">KR - KONKAN RAILWAYS CORPORATION  LTD</option>
                    <option value="NC">NC - NORTH CENTRAL RAILWAY</option>
                    <option value="NE">NE - NORTH EASTERN</option>
                    <option value="NF">NF - NORTHEAST FRONTIER</option>
                    <option value="NR">NR - NORTHERN RAILWAY</option>
                    <option value="NW">NW - NORTH WESTERN RAILWAY</option>
                    <option value="PRC">PRC - PIPAVAV RAIL CORPORATION LTD.</option>
                    <option value="SC">SC - SOUTH CENTRAL</option>
                    <option value="SE">SE - SOUTH EASTERN</option>
                    <option value="SEC">SEC - SOUTH EAST CENTRAL RAILWAY</option>
                    <option value="SR">SR - SOUTHERN RAILWAY</option>
                    <option value="SW">SW - SOUTH WESTERN RAILWAY</option>
                    <option value="WC">WC - WEST CENTRAL RAILWAY </option>
                    <option value="WR">WR - WESTERN RAILWAY </option>
                </select>
		--%>
		<% if(strLognLocnFlag.equals("Z")) {%>
		<span class="inpt-lbl" style="font-weight:600;font-size:13px;">Zone: &nbsp;</span>
		<% } if(strLognLocnFlag.equals("D")) {%>
		<span class="inpt-lbl" style="font-weight:600;font-size:13px;">Division: &nbsp;</span>
		<% } %>
	</td>
	<td>
		<input type="text" id="selZone" name="selZone" class="form-control" value="<%=strLognLocn%>" style="width:80px;font-weight:600;" readonly>
		
            </td>
            <td class="inpt-val">
                <input type="text" class="form-control inpt-control disabled-text" readonly="true"  aria-label="Current Time" aria-describedby="basic-addon1" style="font-size:12px;width:180px;" value="Time: <%=strCrntTime%>">
            </td>
            <td class="inpt-val">
                <input type="submit" name="btnSubmit" id="btnSubmit" class="btn btn-danger inpt-control" value="Refresh Data >" onclick="refreshData();" />
            </td>
	   <% if(strLognLocnFlag.equals("Z")) { %>
            <td class="inpt-val">
                <button name="btnCnfmData" id="btnCnfmData" class="btn btn-success inpt-control text-white" onclick="freezeData();" ><i class="fa fa-lock" aria-hidden="true"></i>&nbsp;&nbsp;Confirm/Freeze Data</button>
            </td>
	   <% } %>
	</tr>
    </table>
</div>
<section class="container-fullwidth" style="padding:1rem;padding-top:5px;">
    <div class="row">
        <div class="col-md-12">
            <ul id="tabs" class="nav nav-tabs">
		<% if(strLognLocnFlag.equals("Z")) { %>
                <li class="nav-item"><a href="" data-target="#step0" data-toggle="tab" class="nav-link small text-uppercase active">Zonal Performance</a></li>
		<% } if(strLognLocnFlag.equals("D")) { %>
		<li class="nav-item"><a href="" data-target="#step0" data-toggle="tab" class="nav-link small text-uppercase active">Division Performance</a></li>
		<% } %>
                <li class="nav-item"><a href="" data-target="#step1" data-toggle="tab" class="nav-link small text-uppercase">Rail Cost</a></li>
                <li class="nav-item"><a href="" data-target="#step2" data-toggle="tab" class="nav-link small text-uppercase">Road Cost</a></li>
                <li class="nav-item"><a href="" data-target="#step3" data-toggle="tab" class="nav-link small text-uppercase">Industry Cluster</a></li>
		<%--
                <li class="nav-item"><a href="" data-target="#step4" data-toggle="tab" class="nav-link small text-uppercase">Marketing Strategy</a></li>
                <li class="nav-item"><a href="" data-target="#step5" data-toggle="tab" class="nav-link small text-uppercase">Gati Shakti Plan</a></li>
--%>
                <li class="nav-item"><a href="" data-target="#step5a" data-toggle="tab" class="nav-link small text-uppercase">Action Plan</a></li>
                <li class="nav-item"><a href="" data-target="#step8" data-toggle="tab" class="nav-link small text-uppercase">Innovations</a></li>
		<li class="nav-item">
			 <div class="btn-group">
			  <button type="button" class="btn btn-link dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="text-decoration:none;color:#4d4d4d;">
			    LOGISTICS PARTNERS
			  </button>
			  <div class="dropdown-menu">
			    <a href="" data-target="#step6" data-toggle="tab">CURRENT</a>
			    <div class="dropdown-divider"></div>
			    <a href="" data-target="#step7" data-toggle="tab">PROPOSED</a>
			  </div>
			</div>
		</li>

<%--             <li class="nav-item"><a href="" data-target="#step6" data-toggle="tab" class="nav-link small text-uppercase">Freight Forwarders (Current)</a></li>
                <li class="nav-item"><a href="" data-target="#step7" data-toggle="tab" class="nav-link small text-uppercase">Freight Forwarders (Proposed)</a></li>
--%>
                <li class="nav-item"><a href="" data-target="#step9" data-toggle="tab" class="nav-link small text-uppercase">SWOT Analysis</a></li>
                <li class="nav-item"><a href="" data-target="#step10" data-toggle="tab" class="nav-link small text-uppercase">Industry Outreach</a></li>
                <li class="nav-item"><a href="" data-target="#step11" data-toggle="tab" class="nav-link small text-uppercase">Gati-Shakti Target Areas</a></li>
                <%--<li class="nav-item"><a href="" data-target="#step12" data-toggle="tab" class="nav-link small text-uppercase">Others</a></li>--%>
               
            </ul>
            <br>
            <div id="tabsContent" class="tab-content">
                <div id="step0" class="tab-pane fade active show">
		   <% if(strLognLocnFlag.equals("Z")) { %>
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Earning/Loading Highlights <span class="pull-right">Zone: <span class="badge badge-warning" style="font-size:12px;"><%=strLognLocn%></span></span></p>
			<table class="table table-striped table-bordered">
			<tr>
				<td colspan="3" style="font-weight:600;">Zonal Revenue (In Rs Crores) <span class="proj-crnt" id="txtProjRevn"></span></td>			
				<td colspan="3" style="font-weight:600;">Zonal Loading (In Million Tonnes) <span class="proj-crnt" id="txtProjLdng"></span></td>
			</tr>
			<tr>			
				
				<td><div id="lblLYRevn"></div></td><td><div id="lblCYRevn"></div></td><td><div id="divLYRevn"></div></td>
				<td><div id="lblLYLdng"></div></td><td><div id="lblCYLdng"></div></td><td><div id="divLYLdng"></div></td>
			</tr>
			<tr>
				<td style="font-weight:600;" colspan="3"><div id="zonlRevnPerfBrkup"></div></td>
				<td style="font-weight:600;" colspan="3"><div id="zonlLdngPerfBrkup"></div></td>
			</tr>
			<tr>
				<td colspan="6"><div class="row"><div class="col-lg-8 offset-lg-2"><div id="divDvsnPerfFOIS"></div></div></div></td>
			</tr>
			<% if(strLognLocnFlag.equals("D")) { %>
			<tr>
				<td colspan="4" align="center">
					<div class="btn-group">
					  <button type="button" class="btn btn-info btn-editperf" onclick="editZonlPerf();">Edit</button>&nbsp;
					<button type="button" class="btn btn-success btn-saveperf" onclick="saveZonlPerf();">Save</button>&nbsp;
					  <button type="button" class="btn btn-secondary" onclick="cnclZonlPerf();">Cancel</button>
					</div>
				</td>
			</tr>
			<% } %>
			</table>
			<div id="zonlPerfRevnStat"></div>
			<hr/>
			<div id="zonlPerfLdngStat"></div>
			<% } %>
		   <% if(strLognLocnFlag.equals("D")) { %>
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Divisional Performance (Division: <%=strLognLocn%>)</p>
			<table class="table table-striped table-bordered">
			<tr>
				<td style="font-weight:600;">Divisional Projected Revenue for current Financial Year (In Rs Crores)</td>
				<td><input type="number" id="txtProjRevn" name="txtProjRevn" readonly class="zonl-perf-inpt form-control"></td>
				<td><div id="lblLYRevn"></div></td><td><div id="divLYRevn"></div></td>
			</tr>
			<tr>
				<td style="font-weight:600;">Divisional Projected Loading for current Financial Year (In Million Tonnes)</td>
				<td><input type="number" id="txtProjLdng" name="txtProjLdng" readonly class="zonl-perf-inpt form-control">
				<td rowspan="2"><div id="lblLYLdng"></div></td><td rowspan="2"><div id="divLYLdng"></div></td>
			</tr>
			<tr>
				<td style="font-weight:600;">Divisional Target Loading for current Financial Year (In Million Tonnes)</td>
				<td><input type="number" id="txtTrgtLdng" name="txtTrgtLdng" readonly class="zonl-perf-inpt form-control">
				<input type="hidden" id="txtZonlPerfRowId" name="txtZonlPerfRowId"></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<div class="btn-group">
					  <button type="button" class="btn btn-info btn-editperf" onclick="editZonlPerf();">Edit</button>&nbsp;
					<button type="button" class="btn btn-success btn-saveperf" onclick="saveZonlPerf();">Save</button>&nbsp;
					  <button type="button" class="btn btn-secondary" onclick="cnclZonlPerf();">Cancel</button>
					</div>
				</td>
			</tr>
			</table>
			<% } %>

	    	</div>
                <div id="step1" class="tab-pane fade">
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities and Comparison of costs with other modes</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th rowspan="2">Zone</th>
                        <th rowspan="2">Division</th>
                        <th rowspan="2">Industry of Freight Generation</th>
                        <th rowspan="2">Commodity</th>
                        <th rowspan="2">Railway Division/Port/Terminal</th>
                        <th rowspan="2">Total Vol of Freight being generated across all modes (MT)</th>
                        <th rowspan="2">Indicative %age of Traffic (Rail) </th>
                        <th colspan="15">Rail Costs for Distance Slabs (Per Ton Km)</th>
                        <th rowspan="2">Additional First-mile Last-Mile Cost (Ex-Rail)</th>
                        <th rowspan="2">&nbsp;</th>
                    </tr>
                    <tr>
                        <th>0-100</th>
                        <th>100-200</th>
                        <th>200-300</th>
                        <th>300-400</th>
                        <th>400-500</th>
                        <th>500-600</th>
                        <th>600-700</th>
                        <th>700-800</th>
                        <th>800-900</th>
                        <th>900-1000</th>
                        <th>1000-1100</th>
                        <th>1100-1200</th>
                        <th>1200-1300</th>
                        <th>1300-1400</th>
                        <th>1400-1500</th>
                    </tr>
                    </thead>                    
                    
                    <tfoot>
                        <tr><td colspan="25">
                        <a class="btn-add-row shtw" onclick="addRecord('CMDT_RAIL_COST');"><i class="fa fa-plus"></i>
</a>
                    </tfoot>
                    </table>
                  
    </div>
                <div id="step2" class="tab-pane fade">
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th rowspan="2">Zone</th>
                        <th rowspan="2">Division</th>
                        <th rowspan="2">Industry of Freight Generation</th>
                        <th rowspan="2">Commodity</th>
                        <th colspan="15">DISTANCE WISE TRANSPORTATION COSTS (ROAD PER TON KM)</th>
                        <th rowspan="2">&nbsp;</th>
                    </tr>
                    <tr>
                        <th>0-100</th>
                        <th>100-200</th>
                        <th>200-300</th>
                        <th>300-400</th>
                        <th>400-500</th>
                        <th>500-600</th>
                        <th>600-700</th>
                        <th>700-800</th>
                        <th>800-900</th>
                        <th>900-1000</th>
                        <th>1000-1100</th>
                        <th>1100-1200</th>
                        <th>1200-1300</th>
                        <th>1300-1400</th>
                        <th>1400-1500</th>
                    </tr>
                    </thead> 
                    <tfoot>
                        <tr><td colspan="20">
                        <a class="btn-add-row shtw" onclick="addRecord('CMDT_ROAD_COST');"><i class="fa fa-plus"></i>
</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step3" class="tab-pane fade">
        	
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Granular information Consignor Wise with the name of Industry/consignor</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Division</th>
                        <th>Name of industries/units in area transporting freight</th>
                        <th>Railway Division/Port/Terminal</th>
                        <th>Total Vol of Freight being generated across all modes (MT)</th>
                        <th>Indicative %age of Traffic (Rail) </th>
                        <th>Broad assessment of reason for competitor advantage</th>
                    </tr>
                    </thead>                    
                    
                    <tfoot>
                        <tr><td colspan="6">
                        <a class="btn-add-row shtw" onclick="addRecord('CNSR_WISE_INFO');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
            
	
                </div>
                <div id="step4" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Building a marketing strategy for Bulk and non-Bulk commodities</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th rowspan="2">Division</th>
                        <th colspan="7">Proposed Additional traffic for the year ending March 31, 2022</th>
		    </tr>
		    <tr>
                        <th>Name of the Commodity</th>
                        <th>(Bulk/non Bulk/Parcel etc)</th>
                        <th>Originating Station</th>
                        <th>Destination Station</th>
                        <th>Volume expected upto March 31,2022 (MT)</th>
			<th>Consignor(s)</th>
                        <th>Additional Revenue expected upto March 31,2022 (MT)</th>
                    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="8">
                        <a class="btn-add-row shtw" onclick="addRecord('MKTG_STRT_BULK');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                    

                </div>
                <div id="step5" class="tab-pane fade">                    
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Action Plan to augment loading and diversify commodity basket</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
		    <tr>
                        <th>Division</th>
                        <th>Action Plan with Deadline</th>
                        <th>Officer Assigned</th>
                        <th>Commodity</th>
                        <th>Volume Targeted (MT)</th>
			<th>Revenue Targeted (Rs Cr)</th>
                        <th>Cost Involved</th>
                        <th>Transportation Requirement/ Types of Rakes needed</th>
                        <th>Handholding needed from Railway Board</th>
                    </tr>
                    </thead>
                    <tfoot>
                        <tr><td colspan="9">
                        <a class="btn-add-row shtw" onclick="addRecord('AGMT_LDNG');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step5a" class="tab-pane fade">                    
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Action Plan to augment loading and diversify commodity basket</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
		    <tr>
                        <th>Zone</th>
                        <th>Division</th>
                        <th>Commodity</th>
                        <th>Consignor/ Industry</th>
                        <th>Traffic Type</th>
                        <th>Stock Type</th>
                        <th>Action Plan</th>
                        <th>Officer Nominated</th>
                        <th>Cost Involved</th>
                        <th>Volume Targeted (MT)</th>
			<th>Revenue Targeted (Rs Cr)</th>
                        <th>Handholding needed from Railway Board</th>
                    </tr>
                    </thead>
                    <tfoot>
                        <tr><td colspan="12">
                        <a class="btn-add-row shtw" onclick="addRecord('AGMT_LDNG1');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>

                <div id="step6" class="tab-pane fade">                   
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Freight forwarders and logistic partners (Current)</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
		    <tr>
                        <th>Division</th>
                        <th>Name of the Freight Forwarder</th>
                        <th>Contact Detail of the Freight Forwarder</th>
                        <th>Volume of Traffic Handled (MT)</th>
                        <th>Revenue (Rs Cr)</th>
                        <th>Originating to Destination Station</th>
			<th>Commodity Detail</th>
                        <th>When Deployed</th>
                        <th>Remarks/ Any other assessment</th>
                    </tr>
                    </thead>
                    <tfoot>
                        <tr><td colspan="9">
                        <a class="btn-add-row shtw" onclick="addRecord('FF_CRNT');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step7" class="tab-pane fade">                 
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Freight forwarders and logistic partners (Proposed)</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
		    <tr>
                        <th>Division</th>
                        <th>Name of the Freight Forwarder</th>
                        <th>Contact Detail of the Freight Forwarder</th>
                        <th>Volume of Traffic Handled (MT)</th>
                        <th>Revenue (Rs Cr)</th>
                        <th>Originating to Destination Station</th>
			<th>Commodity Detail</th>
                        <th>Status of Progress</th>
                        <th>Remarks/ Any other assessment</th>
                    </tr>
                    </thead>
                    <tfoot>
                        <tr><td colspan="9">
                        <a class="btn-add-row shtw" onclick="addRecord('FF_PROPOSED');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step8" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Cross learning from Zones: Initiatives taken to boost traffic in year ending March 31, 2021 and to date</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th rowspan="2">Division</th>
                        <th rowspan="2">List of Initiatives Taken</th>
                        <th rowspan="2">Commodity</th>
                        <th colspan="2">Increase in Traffic</th>
                        <th rowspan="2">Remarks/ Any other assessment</th>
		    </tr>
		    <tr>
                        <th>MT</th>
                        <th>Additional Revenue (Rs Cr)</th>
                    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="6">
                        <a class="btn-add-row shtw"  onclick="addRecord('INIT_TRFC_BOOST');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step9" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;SWOT Analysis of the Zone</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Division</th>
                        <th>Commodity</th>
                        <th>Strengths</th>
                        <th>Weaknesses</th>
                        <th>Opportunities</th>
                        <th>Threats</th>
		    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="6">
                        <a class="btn-add-row shtw" onclick="addRecord('SWOT_ANLY');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step10" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Industry Outreach</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Category</th>
                        <th>Industry</th>
                        <th>Commodity</th>
                        <th>Additional Revenue Expected (In Cr.)</th>
                        <th>Additional Loading Expected in the Year (In MT)</th>
                        <th>&nbsp;</th>
		    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="6">
                        <a class="btn-add-row shtw" onclick="addRecord('INDS_OUTREACH');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step11" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Target Areas for Rail Co-efficient Boost</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Areas for Immediate Growth (0-3 Months)</th>
                        <th>Areas for Medium-Term Growth (in 6 Months)</th>
                        <th>Areas for Long-Term Growth (Over 6 Months)</th>
                        <th>&nbsp;</th>
		    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="4">
                        <a class="btn-add-row shtw" onclick="addRecord('RAIL_COEF_BOOST');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
                <div id="step12" class="tab-pane fade">
			
                    <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Any other Aspect the Division wishes to highlight</p>
                    <table class="table table-input table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Aspects</th>
		    </tr>
                    </thead>                   
                    
                    <tfoot>
                        <tr><td colspan="1">
                        <a class="btn-add-row shtw" onclick="addRecord('OTHR_ASPECT');"><i class="fa fa-plus"></i>
		</a>
                    </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="z-index:9999;">
          <span aria-hidden="true">&times;</span>
        </button>        
      </div>
      <div class="modal-body" id="divAddRecord">
        <div class="form-title text-center">
          <h4 id="step1FormTitle"></h4>
        </div>
        <div class="d-flex flex-column text-center" id="divInptForm">
          <form>
            <div class="form-group">
              <select id="selDvsn" class="dvsn-list"></select>              
            </div>
            <div class="row">
            <div class="col-lg-6 col-sm-12">
            <div class="form-group">
              <input type="text" class="form-control" id="txtIndsFrgtGnrt" placeholder="Industry of Freight Generation">
            </div>
            </div>
            <div class="col-lg-6 col-sm-12">
            <div class="form-group">
              <input type="text" class="form-control" id="txtPortTrml" placeholder="Railway Division/Port/Terminal">
            </div>
            </div>
            </div>
            <div class="row">
            <div class="col-lg-6 col-sm-12">
            <div class="form-group">
              <input type="number" class="form-control" id="txtTotlVol" placeholder="Total Volume of Freight Across All Modes (MT)">
            </div>
            </div>
            <div class="col-lg-6 col-sm-12">
            <div class="form-group">
              <input type="number" class="form-control" id="txtRailPctg" placeholder="%Age of Traffic (Rail)">
            </div>
            </div></div>
            <p class="inpt-lbl">Comparative Costs (Roads Per Ton Kms)</p>
            <div class="container">
            <div class="row">
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt050" placeholder="0-50">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt50100" placeholder="50-100">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt100200" placeholder="100-200">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt200300" placeholder="200-300">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt300400" placeholder="300-400">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt400500" placeholder="400-500">
            </div>
            </div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt500600" placeholder="500-600">
            </div></div>
            <div class="col-lg-2 col-sm-4">
            <div class="form-group">
              <input type="number" class="form-control" id="txt600Plus" placeholder="600+">
            </div>
            </div>
            </div></div>
            <div class="btn-group">
  <button type="button" class="btn btn-warning">Save & Add More</button>&nbsp;
  <button type="button" class="btn btn-secondary" data-dismiss="modal" aria-label="Close" >Cancel</button>
</div>
          </form>
          
          
      </div>
    </div>
      <div class="modal-footer d-flex justify-content-center">
	<div id="divMesg"></div>
	<div class='wrap' style="overflow-y:scroll;">
	  <div class='content' id="divHelpPopUp1"></div>
	</div>
	
        <div class="signup-section">Cancel and <a href="javascript:void(0)" data-dismiss="modal" aria-label="Close"  class="text-info"> Go Back</a></div>
      </div>
  </div>
</div>
</div></div>
	<div id="confirm-modal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">			
				<h4 class="modal-title" style="color:#b32400;">Confirmation</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p id="cnfmmesg"></p>
			</div>
			<div class="modal-footer">
                	<button  class="btn btn-info btn-sm" data-dismiss="modal">Cancel</button>
			<button  class="btn btn-danger btn-sm" onclick="confirmYes();">Yes, Continue !</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="infoModal">
  <div class="modal-dialog" style="width:500px;">
    <div class="modal-content modal-info-content">

      <!-- Modal Header -->
      <div class="modal-header modal-info-header" style="padding:5px;">
        <h4 class="modal-title" id="helpHeading"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="divHelpPopUp" style="max-height:250px;overflow-y:auto;font-size:14px;background:#fff;">        
      </div>
      <!-- Modal footer -->
      <div class="modal-footer modal-info-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_PROPOSAL_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
<form id="frmTrfcPlan" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_TRFCPLAN_FILE" />
    <input name="TrfcPlanFileId" id="TrfcPlanFileId" type="hidden" value="" />
</form>
<form id="frmCnfmData" action="/RailSAHAY/AcqnPlanTrxn" method="post">
    <input name="Optn" id="Optn" type="hidden" value="FREEZE_DATA" />
</form>
<%@ include file="/pages/IRBDUFooter.jspf" %>