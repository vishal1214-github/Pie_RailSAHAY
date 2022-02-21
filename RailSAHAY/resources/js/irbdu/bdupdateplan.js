var CMDT_RAIL_COST=[];
var CMDT_COST_CMPR=[];
var CMDT_ROAD_COST=[];
var END_MILE_COST=[];
var CNSR_WISE_INFO=[];
var MKTG_STRT_BULK=[];
var AGMT_LDNG=[];
var AGMT_LDNG1=[];
var FF_CRNT=[];
var FF_PROPOSED=[];
var INIT_TRFC_BOOST=[];
var SWOT_ANLY=[];
var INDS_OUTREACH=[];
var RAIL_COEF_BOOST=[];
var OTHR_ASPECT=[];
var ZONL_PERF=[];
var DVSN_PERF=[];
var DVSN_PERF_TNGE_LY=[];

var CMDT_TL_COST=[];
var CMDT_WL_COST=[];

var GG_LOV=[];

var finltngestr='';
var finlfrgtstr='';
function infoMesg(ctgr)
{
	var hedg='';
	switch(ctgr)
	{
		case 'ZONL_PERF':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Zonal Performance</p>';
			break;
		case 'CMDT_RAIL_COST':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Rail Cost</p>';
			break;
		case 'CMDT_ROAD_COST':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Road Cost</p>';
			break;			
		case 'CNSR_WISE_INFO':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Industry Cluster</p>';
			break;
		case 'MKTG_STRT_BULK':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Marketing Strategy</p>';
			break;
		case 'AGMT_LDNG1':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Action Plan</p>';
			break;
		case 'FF_CRNT':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Logistics Partners (Current)</p>';
			break;
		case 'FF_PROPOSED':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Logistics Partners (Proposed)</p>';
			break;
		case 'INIT_TRFC_BOOST':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Innovations</p>';
			break;
		case 'SWOT_ANLY':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! SWOT Analysis</p>';
			break;
		case 'INDS_OUTREACH':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Industry Outreach</p>';
			break;
		case 'RAIL_COEF_BOOST':
			hedg='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Gati-Shakti Target Areas</p>';
			break;
	}
	$("#helpHeading").html(hedg);
	var htmlstr='<ul class="list-group list-group-flush">';
	if(ctgr=='ZONL_PERF')
	{
		htmlstr+='<li class="list-group-item">Divisional/ Zonal Projected Revenue for current Financial Year (In Rs Crores) is to be mentioned</li>';
		htmlstr+='<li class="list-group-item">Divisional/ Zonal Projected Loading for current Financial Year (In Million Tonnes) is to be mentioned</li>';
		htmlstr+='<li class="list-group-item">By clicking the Edit tab, the details submitted can be modified/rectified</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Save</strong> tab, the details submitted will be saved</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, no detail shall be submitted</li>';
	}
	if(ctgr=='CMDT_RAIL_COST')
	{
		htmlstr+='<li class="list-group-item">Division/Zone has to select a commodity for which traffic has been generated from the Industry of freight generation</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other" from the given options under the Industry of freight generation list,  then specific details needs to be mentioned under  the "Detail of industry column". The said column will only be activated once "Other" is selected from the list</li>';
		htmlstr+='<li class="list-group-item">Type of Traffic is to be selected from the given options</li>';
		htmlstr+='<li class="list-group-item">Type of Load is to be selected</li>';
		htmlstr+='<li class="list-group-item">Destination "From" is to be selected. For Example- Ex-DLI</li>';
		htmlstr+='<li class="list-group-item">Total value freight  in Million Tonne has to be entered</li>';
		htmlstr+='<li class="list-group-item">%age of Rail Traffic to be entered.<strong>(Traffic carried by the division or zone/Total traffic of the product over concerned Railway or Total production of the product over concerned Railway *100)</strong></li>';
		htmlstr+='<li class="list-group-item">After filling up the following fields the details of rates per tonne per KM will be appeared automatically</li>';
		htmlstr+='<li class="list-group-item">Additional Cost per Ton (Including First-mile Last-mile, Loading Cost, Unloading Cost etc.) for handling the selected commodity traffic is to be mentioned</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data for the commodity selected</li>';
		htmlstr+='<li class="list-group-item">Add next commodities by using the same method</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab</li>';
	}
	if(ctgr=='CMDT_ROAD_COST')
	{
		htmlstr+='<li class="list-group-item">Division/Zone selected commodity under Rail Cost tab will appear in this sheet along with the rates per ton per km</li>';
		htmlstr+='<li class="list-group-item">User has to enter the <strong>per ton per km</strong> cost of Road (inclusive of GST) for the selected commodity</li>';
		htmlstr+='<li class="list-group-item">Rail Cost for transporting same commodity is available on <strong>per ton per km</strong> basis.</li>';
		htmlstr+='<li class="list-group-item">After entering the requisite details the variation will be calculated automatically and reflected in <strong>Rail Road Comparison View of MIS Queries</strong><br/>Rail Cost (Per Ton Km): [Cost Per Ton Km (Ex-Rail) of mentioned slab + (Addtional Cost Per Ton/Maximum Distance Range of mentioned slab)]</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab</li>';
	}
	if(ctgr=='CNSR_WISE_INFO')
	{
		htmlstr+='<li class="list-group-item">Division/Zone has to select the commodity for which traffic has been generated from the Industry of freight generation.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other" from the given options under Industry of frieght generation list, then specific details needs to be mentioned under detail of industry column. The said column will only be activated once "Other" is selected from the list.</li>';
		htmlstr+='<li class="list-group-item">Total value freight in Million Tonne has to be entered. % age of Rail Traffic to be entered. (i.e.Traffic carried by the division or zone/Total traffic of the product over concerned Railway or Total production of the product over concerned Railway *100)</li>';
		htmlstr+='<li class="list-group-item">Reason for competitor advantage has been selected from the options.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other" from the given options under Reason for competitor advantage list,  then specific details needs to be mentioned under the "Detail of competitor advantage column".  The said column will only be activated once ìOtherî is selected from the list.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data for the commodity selected.</li>';
		htmlstr+='<li class="list-group-item">Add next commodities by using the same method. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab</li>';
	}
	if(ctgr=='MKTG_STRT_BULK')
	{
		htmlstr+='<li class="list-group-item">Select the commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the type of traffic from the given options.</li>';
		htmlstr+='<li class="list-group-item">Originating Station has to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">Destination Station has to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">Additional Volume in Million Tonne has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Name of the consigner is to be selected.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other" from the given options under Consigners list,  then specific details needs to be mentioned under the "Detail of Consigner" column. The said column will only be activated once "Other" is selected from the list.</li>';
		htmlstr+='<li class="list-group-item">Additional Revenue expected (Rs in Crore) has to be entered.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data for the commodity selected.</li>';
		htmlstr+='<li class="list-group-item">Add next commodities by using the same method. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab.</li>';
	}
	if(ctgr=='AGMT_LDNG1')
	{
		htmlstr+='<li class="list-group-item">Action with Deadline has to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other" from the given options under Action with Deadline list,  then specific details needs to be mentioned under the "Detail of Action Plan" column. The said column will only be activated once "Other" is selected from the list.</li>';
		htmlstr+='<li class="list-group-item">Officer nominated has to be mentioned.</li>';
		htmlstr+='<li class="list-group-item">Designation of the officer assign has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Select the commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Volume in Million Tonne has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Revenue targeted (Rs in Crore) has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Cost involved (Rs in Crore) for this action has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Transportation requirement or types of rakes needed has to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">After entering the requisite details selection Handholding required from Railway Board.</li>';
		htmlstr+='<li class="list-group-item">If Handholding required, then select the option i.e. proposal already uploaded or upload a new proposal for handholding.</li>';
		htmlstr+='<li class="list-group-item">Fetch the already uploaded proposal file for the reference.</li>';
		htmlstr+='<li class="list-group-item">Upload a new proposal, Give a brief of proposal and select the proposal type and click on upload. </li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data and upload fresh proposal/existing proposal if any.  </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab</li>';

	}
	if(ctgr=='FF_CRNT')
	{
		htmlstr+='<li class="list-group-item">Enter the name of the existing freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Enter the contact details of the existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the volume of traffic (in Million Tonne) of the existing freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Enter the revenue generated (Rs in Crore) by existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the originating and destination station of the traffic generated by freight forwarder/aggregator or logistics partner. </li>';
		htmlstr+='<li class="list-group-item">Select the type of commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the date of commissioning of freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Provide any other assessment or remarks in brief (i.e. not more than 30 words).</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab</li>';

	}
	if(ctgr=='FF_PROPOSED')
	{
		htmlstr+='<li class="list-group-item">Enter the name of the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the contact details of the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the volume of traffic to be handled (in Million Tonne) by the proposed freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Enter the expected revenue generated (Rs in Crore) by the proposed freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Enter the originating and destination station of the traffic generated by freight forwarder/aggregator or logistics partner. </li>';
		htmlstr+='<li class="list-group-item">Select the type of commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Provide the current status of proposed freight forwarder/aggregator or logistics partner.</li>';
		htmlstr+='<li class="list-group-item">Provide any other assessment or remarks in brief (i.e. not more than 30 words).</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab.</li>';

	}
	if(ctgr=='INIT_TRFC_BOOST')
	{
		htmlstr+='<li class="list-group-item">The details of initiatives taken has to be submitted in brief ( i.e. not more than 30 words).</li>';
		htmlstr+='<li class="list-group-item">Select the commodity for which initiatives has been taken.</li>';
		htmlstr+='<li class="list-group-item">Increase in traffic (in million tonne) due to initiative taken has to be submitted in the increase in traffic field.</li>';
		htmlstr+='<li class="list-group-item">Revenue increase (Rs in Crore) due to initiative taken has to be submitted in the Additional Revenue field.</li>';
		htmlstr+='<li class="list-group-item">Remarks or any other assessment is to be mentioned, if any (i.e. not more than 30 words).</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more initiatives by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab.</li>';

	}
	if(ctgr=='SWOT_ANLY')
	{
		htmlstr+='<li class="list-group-item">Select the commodity from the given options. </li>';
		htmlstr+='<li class="list-group-item">Select the strength of Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the weakness of Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the opportunity for Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the threats for Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab.</li>';

	}
	if(ctgr=='INDS_OUTREACH')
	{
		htmlstr+='<li class="list-group-item">Select the existing comodity from the given options. </li>';
		htmlstr+='<li class="list-group-item">Enter the name of the Industry approached or being approached by the respective division or zone. </li>';
		htmlstr+='<li class="list-group-item">Enter  the detail of commodity. </li>';
		htmlstr+='<li class="list-group-item">Enter the expected revenue (Rs in Crore) from the selected industry. </li>';
		htmlstr+='<li class="list-group-item">Enter the loading expected (in Million Tonne) from the selected industry. </li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab. </li>';

	}
	if(ctgr=='RAIL_COEF_BOOST')
	{
		htmlstr+='<li class="list-group-item">Select the areas by which <strong>Immediate Growth</strong> is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the areas by which <strong>Medium Term Growth</strong> is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the areas by which <strong>Long Term Growth</strong> is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go Back</strong> tab, it will take you back the selected current tab.</li>';
	}
	htmlstr+='</ul>';
	$("#divHelpPopUp").html(htmlstr);
	$("#infoModal").modal('show');
}
function infoHelpMesg(catgr)
{
	var hedge='';
	switch(catgr)
	{
		case 'BSNS_DVLPNT_ACQ_PLN':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Bussiness Development & Acquisition Plan</p>';
			break;
		case 'DSVN_ZONL_PERF':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Divisional/ Zonal Performance</p>';
			break;
		case 'CMDT_RAILCOST':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Rail Cost</p>';
			break;
		case 'CMDT_ROAD_COST':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Road Cost</p>';
			break;			
		case 'CNSR_WISE_INFO':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Industry Cluster</p>';
			break;
		case 'AGMT_LDNG1':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Action Plan</p>';
			break;
		case 'INIT_TRFC_BOOST':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Initiatives Taken</p>';
			break;
		case 'FF_CURRENT':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Logistics Partners (Current)</p>';
			break;
		case 'FF_PROPOSED':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Logistics Partners (Proposed)</p>';
			break;
		case 'SWOT_ANLY':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! SWOT Analysis</p>';
			break;
		case 'INDS_OUTREACH':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Industry Outreach</p>';
			break;
		case 'RAIL_COEF_BOOST':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Gati Shakti Target Growth Areas</p>';
			break;
		case 'PROPOSAL':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Bussiness Proposals</p>';
			break;
		case 'RESOURCE_CENTRE':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Resource Centers</p>';
			break;
		case 'RDRSS_DVNL_PROP':
			hedge='<p style="font-size:14px;font-weight:600;margin-block-end:0px;color:#fff;"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;Information on! Redress Divisional Proposals</p>';
			break;
	}
	$("#helpHeading").html(hedge);
	var htmlstr='<ul class="list-group list-group-flush">';
	if(catgr=='DSVN_ZONL_PERF')
	{
		htmlstr+='<li class="list-group-item">Divisional Projected Revenue for current Financial Year (In Rs Crore) is to be mentioned.</li>';
		htmlstr+='<li class="list-group-item">Divisional Projected loading for current Financial Year (In Million Tonne) is to be mentioned.</li>';
		htmlstr+='<li class="list-group-item">A divisional target in loading is to be mentioned as per target assigned by Zonal Railway for the current financial year.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Edit</strong> tab, the details submitted can be modified/rectified.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Save</strong> tab, the details submitted will be saved</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab,the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
	}
	if(catgr=='CMDT_RAIL_COST')
	{
		htmlstr+='<li class="list-group-item">Division has to select the commodity for which traffic has been generated from the Industry of freight generation.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting ‚ÄúOther‚Äù from the given options under the Industry of freight generation list, then specific details needs to be mentioned under the ‚ÄúDetail of industry column‚Äù. The said column will only be activated once ‚ÄúOther‚Äù is selected from the list.</li>';
		htmlstr+='<li class="list-group-item">Type of Traffic from the originating point is to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">Port/terminal is to be selected from the list. </li>';
		htmlstr+='<li class="list-group-item">Total value freight in Million Tonne has to be entered. </li>';
		htmlstr+='<li class="list-group-item">%age of Rail Traffic to be entered.  <u>(Traffic carried by the division or zone/Total traffic of the product over concerned Railway or Total production of the product over concerned Railway *100)</u> </li>';
		htmlstr+='<li class="list-group-item">%age of Rail Traffic to be entered.<strong>(Traffic carried by the division or zone/Total traffic of the product over concerned Railway or Total production of the product over concerned Railway *100)</strong></li>';
		htmlstr+='<li class="list-group-item">After filling up the above required fields, the details of rates per tonne per KM will be appeared automatically in Rail Road comparison under the head of MIS Queries.</li>';
		htmlstr+='<li class="list-group-item">All additional cost, such as First-Mile/Last-Mile Cost, loading, unloading any other charges of taxes is to be entered by the respective division/zone collectively. The formula for calculating per tonne per KM cost is <u> TOTAL COST (including all expenses as FMLM/Loading unloading etc.)/TOTAL KM </u> </li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data for the commodity selected.</li>';
		htmlstr+='<li class="list-group-item"Add next commodities by using the same method. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
	}
	if(catgr=='CMDT_ROAD_COST')
	{
		htmlstr+='<li class="list-group-item">Division needs to select the commodity under Rail Cost tab and it will show the commodity name and rates per ton per km.</li>';
		htmlstr+='<li class="list-group-item">The division has to entered the <u>per ton per km costù</u> of Road for the selected commodity which will be inclusive of all taxes and additional charges as applicable for the comparison of Railway rates. </li>';
		htmlstr+='<li class="list-group-item">Rail Cost(Per tonne KM)= ((Cost per tonne KM (Ex-Rail) of mentioned slab )+ (Additional cost per tonne/Maximum Distance range of mentioned slab)).</li>';
		htmlstr+='<li class="list-group-item">After entering the requisite details/cost, the variation will be calculated automatically under MIS report of Rail Road Comparison.  </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
	}
	if(catgr=='CNSR_WISE_INFO')
	{
		htmlstr+='<li class="list-group-item">Division/Zone has to select the Industry of commodity for which traffic has been generated from the Industry of freight generation.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting ‚ÄúOther‚Äù from the given options under Industry of freight generation list, then specific details needs to be mentioned under detail of industry column. The said column will only be activated once ‚ÄúOther‚Äù is selected from the list.</li>';
		htmlstr+='<li class="list-group-item">Commodity is to be selected from the given drop down menu.</li>';
		htmlstr+='<li class="list-group-item">Type of traffic is to be selected.</li>';
		htmlstr+='<li class="list-group-item">Type of stock is to be selected for the above mentioned/selected commodity.</li>';
		htmlstr+='<li class="list-group-item">Total value freight in Million Tonne has to be entered.  % age of Rail Traffic to be entered. (i.e. Traffic carried by the division or zone/Total traffic of the product over concerned Railway or Total production of the product over concerned Railway *100)</li>';
		htmlstr+='<li class="list-group-item">Reason for competitor advantage has been selected from the options.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting OTHERù from the given options under Reason for competitor advantage list, then specific details needs to be mentioned under the "Detail of competitor advantage" column. The said column will only be activated once OTHER is selected from the list.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the Data for the commodity selected.</li>';
		htmlstr+='<li class="list-group-item">Add next commodities by using the same method. </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
	
	}
	if(catgr=='AGMT_LDNG1')
	{
		htmlstr+='<li class="list-group-item">Select the commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Enter the name of the industry target /consignor under action plan.</li>';
		htmlstr+='<li class="list-group-item">Select the type of traffic.</li>';
		htmlstr+='<li class="list-group-item">Action with Deadline has to be selected from the given options.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other"ù from the given options under Action with Deadline list, then specific details needs to be mentioned under the "Detail of Action Plan"ù column. The said column will only be activated once ‚ÄúOther‚Äù is selected from the list..</li>';
		htmlstr+='<li class="list-group-item">Select the Officer nominated from the list which also has multiple selection option.</li>';
		htmlstr+='<li class="list-group-item">Cost involved if any under the action plan is to be mentioned (Rs  in Crore).</li>';
		htmlstr+='<li class="list-group-item">Volume targeted in Million Tonne has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Revenue targeted (Rs in Crore) has to be entered.</li>';
		htmlstr+='<li class="list-group-item">Cost involved (Rs in Crore) for this action has to be entered.</li>';
		htmlstr+='<li class="list-group-item">After entering the requisite details, select Handholding required from Railway Board.</li>';
		htmlstr+='<li class="list-group-item">If Handholding required, then select the option i.e. proposal already uploaded or upload a new proposal for handholding.</li>';
		htmlstr+='<li class="list-group-item">Fetch the already uploaded proposal file for the reference. or</li>';
		htmlstr+='<li class="list-group-item">Upload a new proposal, Give a brief of proposal and select the proposal type and click on upload. </li>';
		htmlstr+='<li class="list-group-item">Select Proposal type and enter brief description below.</li>';
		htmlstr+='<li class="list-group-item">Transportation requirement or types of rakes needed has to be selected from the given options under stock type.</li>';
		htmlstr+='<li class="list-group-item"><strong>Submit</strong> your remarks regarding the proposal uploaded, if any.</li>';
		htmlstr+='<li class="list-group-item">Click on <strong>upload</strong> proposal.</li>';
		htmlstr+='<li class="list-group-item">Click on <strong>"Save the Data"</strong> after uploading is successful.</li>';
		htmlstr+='<li class="list-group-item">Upload fresh proposal/existing proposal, if any by using the same method.  </li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item">Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
	}
	if(catgr=='INIT_TRFC_BOOST')
	{
		htmlstr+='<li class="list-group-item">Select the commodity for which initiatives has been taken.</li>';
		htmlstr+='<li class="list-group-item">The details of initiatives taken has to be submitted in brief ( i.e. not more than 50 words) for one commodity.</li>';
		htmlstr+='<li class="list-group-item">Increase in traffic (in million tonne) due to initiative taken has to be submitted in the increase in traffic field.</li>';
		htmlstr+='<li class="list-group-item"> Revenue increase (Rs in Crore) due to initiative taken has to be submitted in the Additional Revenue field.</li>';
		htmlstr+='<li class="list-group-item">Remarks or any other assessment is to be mentioned, if any (i.e. not more than 50words).</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more initiatives commodity wise by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
		

	}
	if(catgr=='FF_CRNT')
	{
		htmlstr+='<li class="list-group-item">Enter the name of the existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the contact details of the existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the volume of traffic (in Million Tonne) under existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the revenue generated (Rs in Crore) by existing freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the originating and destination station of the traffic generated by freight forwarder.  </li>';
		htmlstr+='<li class="list-group-item">Select the type of commodity from the given options..</li>';
		htmlstr+='<li class="list-group-item">Select the date of commissioning of freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Provide any other assessment or remarks in brief (i.e. not more than 50 words).</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel </strong>tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';


	}
	if(catgr=='FF_PROPOSED')
	{
		htmlstr+='<li class="list-group-item">Enter the name of the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the contact details of the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the expected volume of traffic to be handled (in Million Tonne) by the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the expected revenue (Rs in Crore) from the proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Enter the originating and destination station of the traffic under freight forwarder. </li>';
		htmlstr+='<li class="list-group-item">Select the type of commodities from the given options..</li>';
		htmlstr+='<li class="list-group-item">Provide the current status of proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item">Provide the current status of proposed freight forwarder.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel </strong>tab, the details submitted will be cancelled and need to be re-submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
		htmlstr+='<li class="list-group-item"></li>';

	}

	if(catgr=='SWOT_ANLY')
	{
		htmlstr+='<li class="list-group-item">Select the commodity first from the given list under drop down menu.</li>';
		htmlstr+='<li class="list-group-item">Select the "Strength"ù of Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the "Weakness"ù of Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the "Opportunity"ù for Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the "Threats"ù for Railway in particular division or zone from the given options.</li>';
		htmlstr+='<li class="list-group-item">In case of selecting "Other"ù from the given options, the details need to be mentioned under the head of ‚Äúany other strength‚Äù.   The said column will only be activated once ‚ÄúOther‚Äù is selected from the list. It is similarly used for Weaknesses, Opportunities and Threat.</li>';
		htmlstr+='<li class="list-group-item">Enter divisional brief descriptions of each head (in 100words) which is mandatory.  </li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more commodities details by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel</strong> tab, the details submitted will be cancelled and need to be re- submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';

	}
	if(catgr=='INDS_OUTREACH')
	{
		htmlstr+='<li class="list-group-item">Select the existing commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Enter the name of the Industry Targeted/approached by division or zone.</li>';
		htmlstr+='<li class="list-group-item">The name and designation of the person interacted with is to be mentioned. </li>';
		htmlstr+='<li class="list-group-item">Select the date and time of the meeting.</li>';
		htmlstr+='<li class="list-group-item">Enter the detail of commodity for which the meeting has been organized.</li>';
		htmlstr+='<li class="list-group-item">Enter the expected revenue (Rs in Crore) from the selected industry.</li>';
		htmlstr+='<li class="list-group-item">Enter the loading expected (in Million Tonne) from the selected industry.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Cancel </strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>Go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';

		

	}
	if(catgr=='RAIL_COEF_BOOST')
	{
		htmlstr+='<li class="list-group-item">Select the areas by which "Immediate Growth"ù is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the areas by which "Medium Term Growth"ù is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item">Select the areas by which "Long Term Growth"ù is expected in respective division or zone by the given options.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the data and add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">Traffic plan of works for the current financial year (is to be executed by the divisions and zone will upload the consolidated traffic plan o their respective divisions) is to be uploaded in prescribed format only. </li>';
		htmlstr+='<li class="list-group-item">By clicking the<strong> Cancel </strong> tab, the details submitted will be cancelled and need to be submit again.</li>';
		htmlstr+='<li class="list-group-item">By clicking the <strong>go back</strong> tab, it will take you back the selected current tab.</li>';
		htmlstr+='<li class="list-group-item"><u>Zonal Railways can see and amend the data fed by the divisions and they can also add new data if required on behalf of their divisions.<u></li>';
		htmlstr+='<li class="list-group-item"><u>Refresh Data tab</u>- This is for the refreshing the data.</li>';
		htmlstr+='<li class="list-group-item"><u>Freeze/Confirming the data</u>:- This action is for the freezing the data. After freezing it could not be editable.</li>';
		htmlstr+='<li class="list-group-item"></li>';

	}
	if(catgr=='PROPOSAL')
	{		
		htmlstr+='<li class="list-group-item">Select the proposal type from the given options.</li>';
		htmlstr+='<li class="list-group-item">Enter the description about the proposal.</li>';
		htmlstr+='<li class="list-group-item">Select the commodity for proposal.</li>';
		htmlstr+='<li class="list-group-item">Select the stock type.</li>';
		htmlstr+='<li class="list-group-item">Select/choose the proposal file for uploading.</li>';
		htmlstr+='<li class="list-group-item">Enter remarks, if any.</li>';
		htmlstr+='<li class="list-group-item">Click on upload for button.</li>';
		htmlstr+='<li class="list-group-item"></li>';

	}
	if(catgr=='RESOURCE_CENTRE')
	{
		htmlstr+='<li class="list-group-item">Select the commodity from the given options.</li>';
		htmlstr+='<li class="list-group-item">Enter the name of the industry.</li>';
		htmlstr+='<li class="list-group-item">Select the commodity for proposal.</li>';
		htmlstr+='<li class="list-group-item">Enter the resource name.</li>';
		htmlstr+='<li class="list-group-item">Enter the mobile number.</li>';
		htmlstr+='<li class="list-group-item">Enter the landline number.</li>';
		htmlstr+='<li class="list-group-item">Enter the e-mail id</li>';
		htmlstr+='<li class="list-group-item">Enter the communication address.</li>';
		htmlstr+='<li class="list-group-item">Click on <strong>Save</strong> and add more for saving the entered data. Add more by using the same method.</li>';
		htmlstr+='<li class="list-group-item">Click on <strong>Close</strong> button, if not required or for closing the page.</li>';
		htmlstr+='<li class="list-group-item"></li>';



	}
	if(catgr=='RDRSS_DVNL_PROP')
	{
		htmlstr+='<li class="list-group-item">By clicking the arrow key at the right end side, a screen will pop up to enter the remarks/ response under subject/remark column for the respective zone only.</li>';
		htmlstr+='<li class="list-group-item">In case of decision, a decision letter need to be uploaded the clicking the button for ‚Äúchoose file‚Äù and title/subject of the decision letter need to be mentioned under subject/remark column.</li>';
		htmlstr+='<li class="list-group-item"><strong>Save</strong> the response after submission of requisite details.</li>';
		htmlstr+='<li class="list-group-item"><u>Refresh Tab</u>: - To refresh the data entered.</li>';
		htmlstr+='<li class="list-group-item"><u>Confirm/Freeze Tab<u>:- This will stop the divisions to make any changes in the existing data/ data entered and saved prior to the click freeze button.  </li>';
		htmlstr+='<li class="list-group-item">It will only used by the zones, once the divisions are done for submission of requisite details/ data at the portal in prescribed timelines. </li>';
		htmlstr+='<li class="list-group-item"></li>';
		
	}


	htmlstr+='</ul>';
	$("#divHelpPopUp").html(htmlstr);
	$("#infoModal").modal('show');
}

function refreshData()
{
	finltngestr='';
	finlfrgtstr='';
	$("#divDvsnPerf").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');
	var selzone=$("#selZone").val();
	if((GG_LognLocnFlag=="Z") && (GG_DataCnfmStts!="Y"))
	{
		$("#btnCnfmData").show();
	}
	else
	{
		$("#btnCnfmData").hide();
	}
    fetchDvsnPerfStats(selzone);

    if(GG_LognLocnFlag=="Z")
	    fetchPlanData('','ZONL_PERF');
    if(GG_LognLocnFlag=="D")
	    fetchPlanData('','DVSN_PERF');

    /*fetchPlanData('','CMDT_COST_CMPR');*/
    fetchPlanData('','CMDT_RAIL_COST');
    /*fetchPlanData('','END_MILE_COST');*/
    fetchPlanData('','CMDT_ROAD_COST');
    fetchPlanData('','CNSR_WISE_INFO');
    fetchPlanData('','MKTG_STRT_BULK');
    fetchPlanData('','AGMT_LDNG');
    fetchPlanData('','AGMT_LDNG1');
    fetchPlanData('','FF_CRNT');
    fetchPlanData('','FF_PROPOSED');
    fetchPlanData('','INIT_TRFC_BOOST');
    fetchPlanData('','SWOT_ANLY');
    fetchPlanData('','INDS_OUTREACH');
    fetchPlanData('','RAIL_COEF_BOOST');
    fetchPlanData('','OTHR_ASPECT');
}
$(document).ready(function(){
    fetchLOVs();
    refreshData();
/*
    $("#selZone").change(function(){
	var selzone=$(this).val();
	if((selzone==GG_CrntZone) && (GG_DataCnfmStts!="Y"))
	{
		$("#btnCnfmData").show();
	}
	else
	{
		$("#btnCnfmData").hide();
	}

    });
*/
});
function fetchPlanData(date,inpt_ctgr)
{
	var selZone=$("#selZone").val();
	var dvsn='';
	if(GG_LognLocnFlag=="D")
	{
		selZone=GG_DvsnZone;
		dvsn=GG_CrntLocn;
	}

	var myurl="/RailSAHAY/AcqnPlan";
	var htmlstr='';
	$.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'FETCH_PLAN_DATA',Ctgr:inpt_ctgr, Zone:selZone, Dvsn:dvsn, Date: date},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="S")
            {        
	            switch (inpt_ctgr)
	            {
		       		case 'CMDT_COST_CMPR' :
                                    htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities and Comparison of costs with other modes</p>';
                                    htmlstr+='<table class="table table-input table-striped table-bordered">';
                                    htmlstr+='<thead>';
                                    htmlstr+='<tr>';
                                    htmlstr+='<th rowspan="2">Division</th>';
                                    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
                                    htmlstr+='<th rowspan="2">Division/Port/Terminal</th>';
                                    htmlstr+='<th rowspan="2">Total Vol of Freight being generated across all modes (MT)</th>';
                                    htmlstr+='<th rowspan="2">Indicative %age of Traffic (Rail) </th>';
                                    htmlstr+='<th colspan="8">Comparative Costs (Roads Per Ton Km)</th>';
                                    htmlstr+='<th rowspan="2">&nbsp;</th>';
                                    htmlstr+='</tr>';
                                    htmlstr+='<tr>';
					htmlstr+='<th>0-50</th>';
					   htmlstr+='<th>50-100</th>';
					   htmlstr+='<th>100-200</th>';
					   htmlstr+='<th>200-300</th>';
					   htmlstr+='<th>300-400</th>';
					   htmlstr+='<th>400-500</th>';
					   htmlstr+='<th>500-600</th>';
					   htmlstr+='<th>Above 600</th>';
                                    htmlstr+='</tr>';
                                    htmlstr+='</thead>';                  							
                                    
                                    for(var i=0;i<obj.DataList1.length;i++)
                                    {
                                            CMDT_COST_CMPR[i]=new Array(14);
                                            CMDT_COST_CMPR[i][0]=obj.DataList1[i].Val1;
                                            CMDT_COST_CMPR[i][1]=obj.DataList1[i].Val2;
                                            CMDT_COST_CMPR[i][2]=obj.DataList1[i].Val3;
                                            CMDT_COST_CMPR[i][3]=obj.DataList1[i].Val4;
                                            CMDT_COST_CMPR[i][4]=obj.DataList1[i].Val5;
                                            CMDT_COST_CMPR[i][5]=obj.DataList1[i].Val6;
                                            CMDT_COST_CMPR[i][6]=obj.DataList1[i].Val7;
                                            CMDT_COST_CMPR[i][7]=obj.DataList1[i].Val8;
                                            CMDT_COST_CMPR[i][8]=obj.DataList1[i].Val9;
                                            CMDT_COST_CMPR[i][9]=obj.DataList1[i].Val10;
                                            CMDT_COST_CMPR[i][10]=obj.DataList1[i].Val11;
                                            CMDT_COST_CMPR[i][11]=obj.DataList1[i].Val12;
                                            CMDT_COST_CMPR[i][12]=obj.DataList1[i].Val13;
                                            CMDT_COST_CMPR[i][13]=obj.DataList1[i].Val14;
					    
                                            htmlstr+='<tr><td>'+CMDT_COST_CMPR[i][0]+'</td><td>'+CMDT_COST_CMPR[i][1]+'</td><td>'+CMDT_COST_CMPR[i][2]+'</td><td>'+CMDT_COST_CMPR[i][3]+'</td><td>'+CMDT_COST_CMPR[i][4]+'</td><td>'+CMDT_COST_CMPR[i][5]+'</td><td>'+CMDT_COST_CMPR[i][6]+'</td><td>'+CMDT_COST_CMPR[i][7]+'</td><td>'+CMDT_COST_CMPR[i][8]+'</td><td>'+CMDT_COST_CMPR[i][9]+'</td><td>'+CMDT_COST_CMPR[i][10]+'</td><td>'+CMDT_COST_CMPR[i][11]+'</td><td>'+CMDT_COST_CMPR[i][12]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'CMDT_COST_CMPR\', \''+CMDT_COST_CMPR[i][13]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'CMDT_COST_CMPR\',\''+CMDT_COST_CMPR[i][13]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
                                    }
				if(GG_DataCnfmStts!="Y")
				{
                                    htmlstr+='<tfoot>';
                                    htmlstr+='<tr><td colspan="13">';
                                    htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'CMDT_COST_CMPR\');return true;"><i class="fa fa-plus"></i>';
                                    htmlstr+='</a>';
                                    htmlstr+='</tfoot>';
				}
                                    htmlstr+='</table>';
                                    $("#step1").html(htmlstr);                                            
                                    break;
				case 'CMDT_RAIL_COST' :
                                    htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities and Comparison of costs with other modes</p>';
                                    htmlstr+='<table class="table table-input table-striped table-bordered">';
                                    htmlstr+='<thead>';
                                    htmlstr+='<tr>';
                                    htmlstr+='<th rowspan="2">Zone</th>';
                                    htmlstr+='<th rowspan="2">Division</th>';
                                    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
                                    htmlstr+='<th rowspan="2">Commodity</th>';
                                    htmlstr+='<th rowspan="2">Originating From</th>';
                                    htmlstr+='<th rowspan="2">Total Vol of Freight being generated across all modes (in Million Tonnes)</th>';
                                    htmlstr+='<th rowspan="2">Indicative %age of Traffic (Rail) </th>';
                                    htmlstr+='<th colspan="15">Distance wise Transportation Costs (Rail Per Ton Km) [Full Rake Load/Piecemeal Load]</th>';
				    htmlstr+='<th>Additional Costs (Per Ton)</th>';
                                    htmlstr+='<th rowspan="2">&nbsp;</th>';
                                    htmlstr+='</tr>';
                                    htmlstr+='<tr>';
                                    htmlstr+='<th>0-100</th>';
                                    htmlstr+='<th>100-200</th>';
                                    htmlstr+='<th>200-300</th>';
                                    htmlstr+='<th>300-400</th>';
                                    htmlstr+='<th>400-500</th>';
                                    htmlstr+='<th>500-600</th>';
                                    htmlstr+='<th>600-700</th>';
                                    htmlstr+='<th>700-800</th>';
                                    htmlstr+='<th>800-900</th>';	
                                    htmlstr+='<th>900-1000</th>';
                                    htmlstr+='<th>1000-1100</th>';
                                    htmlstr+='<th>1100-1200</th>';
                                    htmlstr+='<th>1200-1300</th>';
                                    htmlstr+='<th>1300-1400</th>';
                                    htmlstr+='<th>1400-1500</th>';
                                    htmlstr+='<th>Incl. FMLM, Loading, Unloading etc.</th>';
                                    htmlstr+='</tr>';
                                    htmlstr+='</thead>';                  							
                                    
                                    for(var i=0;i<obj.DataList1.length;i++)
                                    {
                                            CMDT_RAIL_COST[i]=new Array(24);
                                            CMDT_RAIL_COST[i][0]=obj.DataList1[i].Val1;
                                            CMDT_RAIL_COST[i][1]=obj.DataList1[i].Val2;
                                            CMDT_RAIL_COST[i][2]=obj.DataList1[i].Val3;
                                            CMDT_RAIL_COST[i][3]=obj.DataList1[i].Val4;
                                            CMDT_RAIL_COST[i][4]=obj.DataList1[i].Val5;
                                            CMDT_RAIL_COST[i][5]=obj.DataList1[i].Val6;
                                            CMDT_RAIL_COST[i][6]=obj.DataList1[i].Val7;
                                            CMDT_RAIL_COST[i][7]=obj.DataList1[i].Val8;
                                            CMDT_RAIL_COST[i][8]=obj.DataList1[i].Val9;
                                            CMDT_RAIL_COST[i][9]=obj.DataList1[i].Val10;
                                            CMDT_RAIL_COST[i][10]=obj.DataList1[i].Val11;
                                            CMDT_RAIL_COST[i][11]=obj.DataList1[i].Val12;
                                            CMDT_RAIL_COST[i][12]=obj.DataList1[i].Val13;
                                            CMDT_RAIL_COST[i][13]=obj.DataList1[i].Val14;
                                            CMDT_RAIL_COST[i][14]=obj.DataList1[i].Val15;
                                            CMDT_RAIL_COST[i][15]=obj.DataList1[i].Val16;
                                            CMDT_RAIL_COST[i][16]=obj.DataList1[i].Val17;
                                            CMDT_RAIL_COST[i][17]=obj.DataList1[i].Val18;
                                            CMDT_RAIL_COST[i][18]=obj.DataList1[i].Val19;
                                            CMDT_RAIL_COST[i][19]=obj.DataList1[i].Val20;
                                            CMDT_RAIL_COST[i][20]=obj.DataList1[i].Val21;
                                            CMDT_RAIL_COST[i][21]=obj.DataList1[i].Val22;
                                            CMDT_RAIL_COST[i][22]=obj.DataList1[i].Val23;
                                            CMDT_RAIL_COST[i][23]=obj.DataList1[i].Val24;
					    
                                            htmlstr+='<tr><td>'+CMDT_RAIL_COST[i][0]+'</td><td>'+CMDT_RAIL_COST[i][1]+'</td><td>'+CMDT_RAIL_COST[i][2]+'</td><td>'+CMDT_RAIL_COST[i][3].substring(CMDT_RAIL_COST[i][3].indexOf("-")+1)+'</td><td>'+CMDT_RAIL_COST[i][4]+'</td><td>'+fixDecimal(CMDT_RAIL_COST[i][5])+'</td><td>'+fixDecimal(CMDT_RAIL_COST[i][6])+'%</td><td>'+fixDecimal(CMDT_RAIL_COST[i][7])+'</td><td>'+fixDecimal(CMDT_RAIL_COST[i][8])+'</td><td>'+CMDT_RAIL_COST[i][9]+'</td><td>'+CMDT_RAIL_COST[i][10]+'</td><td>'+CMDT_RAIL_COST[i][11]+'</td><td>'+CMDT_RAIL_COST[i][12]+'</td><td>'+CMDT_RAIL_COST[i][13]+'</td><td>'+CMDT_RAIL_COST[i][14]+'</td><td>'+CMDT_RAIL_COST[i][15]+'</td><td>'+CMDT_RAIL_COST[i][16]+'</td><td>'+CMDT_RAIL_COST[i][17]+'</td><td>'+CMDT_RAIL_COST[i][18]+'</td><td>'+CMDT_RAIL_COST[i][19]+'</td><td>'+CMDT_RAIL_COST[i][20]+'</td><td>'+CMDT_RAIL_COST[i][21]+'</td><td>'+fixDecimal(CMDT_RAIL_COST[i][22])+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'CMDT_RAIL_COST\', \''+CMDT_RAIL_COST[i][23]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'CMDT_RAIL_COST\',\''+CMDT_RAIL_COST[i][23]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
                                    }
									if(GG_DataCnfmStts!="Y")
									{
	                                    htmlstr+='<tfoot>';
	                                    htmlstr+='<tr><td colspan="26">';
	                                    htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'CMDT_RAIL_COST\');return true;"><i class="fa fa-plus"></i>';
	                                    htmlstr+='</a>';
	                                    htmlstr+='</tfoot>';
									}
                                    htmlstr+='</table>';
                                    $("#step1").html(htmlstr);                                            
                                    break;
		       			case 'CMDT_ROAD_COST' :
			       		htmlstr='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
								    htmlstr+='<th rowspan="2">Commodity</th>';
								    htmlstr+='<th colspan="15">Distance wise Transportation Costs (Road Per Ton Km)</th>';
								    htmlstr+='<th rowspan="2">&nbsp;</th>';
									htmlstr+='</tr>';
									htmlstr+='<tr>';
                                    htmlstr+='<th>0-100</th>';
                                    htmlstr+='<th>100-200</th>';
                                    htmlstr+='<th>200-300</th>';
                                    htmlstr+='<th>300-400</th>';
                                    htmlstr+='<th>400-500</th>';
                                    htmlstr+='<th>500-600</th>';
                                    htmlstr+='<th>600-700</th>';
                                    htmlstr+='<th>700-800</th>';
                                    htmlstr+='<th>800-900</th>';
                                    htmlstr+='<th>900-1000</th>';
                                    htmlstr+='<th>1000-1100</th>';
                                    htmlstr+='<th>1100-1200</th>';
                                    htmlstr+='<th>1200-1300</th>';
                                    htmlstr+='<th>1300-1400</th>';
                                    htmlstr+='<th>1400-1500</th>';
				    htmlstr+='</tr>';
				    htmlstr+='</thead>'; 
  				    for(var i=0;i<obj.DataList1.length;i++)
                                    {
                                        CMDT_ROAD_COST[i]=new Array(20);
                                        CMDT_ROAD_COST[i][0]=obj.DataList1[i].Val1;
                                        CMDT_ROAD_COST[i][1]=obj.DataList1[i].Val2;
                                        CMDT_ROAD_COST[i][2]=obj.DataList1[i].Val3;
                                        CMDT_ROAD_COST[i][3]=obj.DataList1[i].Val4;
                                        CMDT_ROAD_COST[i][4]=obj.DataList1[i].Val5;
                                        CMDT_ROAD_COST[i][5]=obj.DataList1[i].Val6;
                                        CMDT_ROAD_COST[i][6]=obj.DataList1[i].Val7;
                                        CMDT_ROAD_COST[i][7]=obj.DataList1[i].Val8;
                                        CMDT_ROAD_COST[i][8]=obj.DataList1[i].Val9;
                                        CMDT_ROAD_COST[i][9]=obj.DataList1[i].Val10;
                                        CMDT_ROAD_COST[i][10]=obj.DataList1[i].Val11;
                                        CMDT_ROAD_COST[i][11]=obj.DataList1[i].Val12;
                                        CMDT_ROAD_COST[i][12]=obj.DataList1[i].Val13;
                                        CMDT_ROAD_COST[i][13]=obj.DataList1[i].Val14;
                                        CMDT_ROAD_COST[i][14]=obj.DataList1[i].Val15;
                                        CMDT_ROAD_COST[i][15]=obj.DataList1[i].Val16;
                                        CMDT_ROAD_COST[i][16]=obj.DataList1[i].Val17;
                                        CMDT_ROAD_COST[i][17]=obj.DataList1[i].Val18;
                                        CMDT_ROAD_COST[i][18]=obj.DataList1[i].Val19;
                                        CMDT_ROAD_COST[i][19]=obj.DataList1[i].Val20;
					    
                                        htmlstr+='<tr><td>'+CMDT_ROAD_COST[i][0]+'</td><td>'+CMDT_ROAD_COST[i][1]+'</td><td>'+CMDT_ROAD_COST[i][2]+'</td><td>'+CMDT_ROAD_COST[i][3].substring(CMDT_ROAD_COST[i][3].indexOf("-")+1)+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][4])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][5])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][6])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][7])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][8])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][9])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][10])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][11])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][12])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][13])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][14])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][15])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][16])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][17])+'</td><td>'+fixDecimal(CMDT_ROAD_COST[i][18])+'</td><td><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'CMDT_ROAD_COST\', \''+CMDT_ROAD_COST[i][19]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></td></tr>';
                                    }
								
					htmlstr+='</table>';
					$("#step2").html(htmlstr);
	
					break;
		       			case 'END_MILE_COST' :
			       		htmlstr='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
								    htmlstr+='<th colspan="8">Costs (Road Per Ton Km)</th>';
								    htmlstr+='<th colspan="8">Additional Last and First Mile cost ex-Rail Per Ton Km</th>';
								    htmlstr+='<th rowspan="2">&nbsp;</th>';
									htmlstr+='</tr>';
									htmlstr+='<tr>';
								    htmlstr+='<th>0-50</th>';
								    htmlstr+='<th>50-100</th>';
								    htmlstr+='<th>100-200</th>';
								    htmlstr+='<th>200-300</th>';
								    htmlstr+='<th>300-400</th>';
								    htmlstr+='<th>400-500</th>';
								    htmlstr+='<th>500-600</th>';
								    htmlstr+='<th>Above 600</th>';
								    htmlstr+='<th>0-50</th>';
								    htmlstr+='<th>50-100</th>';
								    htmlstr+='<th>100-200</th>';
								    htmlstr+='<th>200-300</th>';
								    htmlstr+='<th>300-400</th>';
								    htmlstr+='<th>400-500</th>';
								    htmlstr+='<th>500-600</th>';
								    htmlstr+='<th>Above 600</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';                    							
								for(var i=0;i<obj.DataList1.length;i++)
								{
									END_MILE_COST[i]=new Array(20);	
									END_MILE_COST[i][0]=obj.DataList1[i].Val1;
									END_MILE_COST[i][1]=obj.DataList1[i].Val2;
									END_MILE_COST[i][2]=obj.DataList1[i].Val3;
									END_MILE_COST[i][3]=obj.DataList1[i].Val4;
									END_MILE_COST[i][4]=obj.DataList1[i].Val5;
									END_MILE_COST[i][5]=obj.DataList1[i].Val6;
									END_MILE_COST[i][6]=obj.DataList1[i].Val7;
									END_MILE_COST[i][7]=obj.DataList1[i].Val8;
									END_MILE_COST[i][8]=obj.DataList1[i].Val9;
									END_MILE_COST[i][9]=obj.DataList1[i].Val10;
									END_MILE_COST[i][10]=obj.DataList1[i].Val11;
									END_MILE_COST[i][11]=obj.DataList1[i].Val12;
									END_MILE_COST[i][12]=obj.DataList1[i].Val13;
									END_MILE_COST[i][13]=obj.DataList1[i].Val14;
									END_MILE_COST[i][14]=obj.DataList1[i].Val15;
									END_MILE_COST[i][15]=obj.DataList1[i].Val16;
									END_MILE_COST[i][16]=obj.DataList1[i].Val17;
									END_MILE_COST[i][17]=obj.DataList1[i].Val18;
									END_MILE_COST[i][18]=obj.DataList1[i].Val19;
									END_MILE_COST[i][19]=obj.DataList1[i].Val20;

									htmlstr+='<tr><td>'+END_MILE_COST[i][0]+'</td><td style="background:#e0e0eb;font-weight:600;">'+END_MILE_COST[i][1]+'</td><td>'+END_MILE_COST[i][2]+'</td><td>'+END_MILE_COST[i][3]+'</td><td>'+END_MILE_COST[i][4]+'</td><td>'+END_MILE_COST[i][5]+'</td><td>'+END_MILE_COST[i][6]+'</td><td>'+END_MILE_COST[i][7]+'</td><td>'+END_MILE_COST[i][8]+'</td><td>'+END_MILE_COST[i][9]+'</td><td>'+END_MILE_COST[i][10]+'</td><td>'+END_MILE_COST[i][11]+'</td><td>'+END_MILE_COST[i][12]+'</td><td>'+END_MILE_COST[i][13]+'</td><td>'+END_MILE_COST[i][14]+'</td><td>'+END_MILE_COST[i][15]+'</td><td>'+END_MILE_COST[i][16]+'</td><td>'+END_MILE_COST[i][17]+'</td><td>'+END_MILE_COST[i][18]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'END_MILE_COST\', \''+END_MILE_COST[i][19]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'END_MILE_COST\',\''+END_MILE_COST[i][19]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								}
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="19">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'END_MILE_COST\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step2").html(htmlstr);	
							break;

		       			case 'CNSR_WISE_INFO' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Granular information Consignor Wise with the name of Industry/consignor</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of industries/units in area transporting freight</th>';
								    htmlstr+='<th>Commodity</th>';
								    /*
								    htmlstr+='<th>Port/Terminal</th>';
								    */
								    htmlstr+='<th>Traffic Type</th>';
								    htmlstr+='<th>Stock Type</th>';
								    htmlstr+='<th>Total volume freight being generated across all modes (in Million Tonnes)</th>';
								    htmlstr+='<th>Indicative percentage of traffic moving by Rail</th>';
								    htmlstr+='<th>Broad assessment of reason for competitors advantage</th>';
								    htmlstr+='<th class="other-col">Detail of OTHER Reason</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';                    							  
								for(var i=0;i<obj.DataList1.length;i++)
								{
									CNSR_WISE_INFO[i]=new Array(12);	
									CNSR_WISE_INFO[i][0]=obj.DataList1[i].Val1;
									CNSR_WISE_INFO[i][1]=obj.DataList1[i].Val2;
									CNSR_WISE_INFO[i][2]=obj.DataList1[i].Val3;
									CNSR_WISE_INFO[i][3]=obj.DataList1[i].Val4;
									CNSR_WISE_INFO[i][4]=obj.DataList1[i].Val5;
									CNSR_WISE_INFO[i][5]=obj.DataList1[i].Val6;
									CNSR_WISE_INFO[i][6]=obj.DataList1[i].Val7;
									CNSR_WISE_INFO[i][7]=obj.DataList1[i].Val8;
									CNSR_WISE_INFO[i][8]=obj.DataList1[i].Val9;
									CNSR_WISE_INFO[i][9]=obj.DataList1[i].Val10;
									CNSR_WISE_INFO[i][10]=obj.DataList1[i].Val11;
									CNSR_WISE_INFO[i][11]=obj.DataList1[i].Val12;
									var compt_advt=formatMultVals(CNSR_WISE_INFO[i][9]);
									htmlstr+='<tr><td>'+CNSR_WISE_INFO[i][0]+'</td><td>'+CNSR_WISE_INFO[i][1]+'</td><td>'+CNSR_WISE_INFO[i][2]+'</td><td>'+CNSR_WISE_INFO[i][3]+'</td><td>'+CNSR_WISE_INFO[i][5]+'</td><td>'+CNSR_WISE_INFO[i][6]+'</td><td>'+fixDecimal(CNSR_WISE_INFO[i][7])+'</td><td>'+fixDecimal(CNSR_WISE_INFO[i][8])+'</td><td>'+compt_advt+showOtherVal(CNSR_WISE_INFO[i][10])+'</td><td class="other-col">'+CNSR_WISE_INFO[i][10]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'CNSR_WISE_INFO\', \''+CNSR_WISE_INFO[i][11]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'CNSR_WISE_INFO\',\''+CNSR_WISE_INFO[i][11]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>'
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="12">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'CNSR_WISE_INFO\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step3").html(htmlstr);	
							break;
		       			case 'MKTG_STRT_BULK' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Building a marketing strategy for Bulk and non-Bulk commodities</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th colspan="7">Proposed Additional traffic for the year ending March 31, 2022</th>';
								    htmlstr+='<th rowspan="2">&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Name of the Commodity</th>';
								    htmlstr+='<th>(Bulk/ Non Bulk/ Parcel/ Goods/ Container)</th>';
								    htmlstr+='<th>Originating Station</th>';
								    htmlstr+='<th>Destination Station</th>';
								    htmlstr+='<th>Volume Expected Upto March 31, 2022 (in Million Tonnes)</th>';
								    htmlstr+='<th>Consignor(s)</th>';
								    htmlstr+='<th>Revenue Expected Upto March 31, 2022 (in Cr)</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									MKTG_STRT_BULK[i]=new Array(10);	
									MKTG_STRT_BULK[i][0]=obj.DataList1[i].Val1;
									MKTG_STRT_BULK[i][1]=obj.DataList1[i].Val2;
									MKTG_STRT_BULK[i][2]=obj.DataList1[i].Val3;
									MKTG_STRT_BULK[i][3]=obj.DataList1[i].Val4;
									MKTG_STRT_BULK[i][4]=obj.DataList1[i].Val5;
									MKTG_STRT_BULK[i][5]=obj.DataList1[i].Val6;
									MKTG_STRT_BULK[i][6]=obj.DataList1[i].Val7;
									MKTG_STRT_BULK[i][7]=obj.DataList1[i].Val8;
									MKTG_STRT_BULK[i][8]=obj.DataList1[i].Val9;
									MKTG_STRT_BULK[i][9]=obj.DataList1[i].Val10;
									htmlstr+='<tr><td>'+MKTG_STRT_BULK[i][0]+'</td><td>'+MKTG_STRT_BULK[i][1]+'</td><td>'+MKTG_STRT_BULK[i][2]+'</td><td>'+MKTG_STRT_BULK[i][3]+'</td><td>'+MKTG_STRT_BULK[i][4]+'</td><td>'+MKTG_STRT_BULK[i][5]+'</td><td>'+fixDecimal(MKTG_STRT_BULK[i][6])+'</td><td>'+MKTG_STRT_BULK[i][7]+'</td><td>'+fixDecimal(MKTG_STRT_BULK[i][8])+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'MKTG_STRT_BULK\', \''+MKTG_STRT_BULK[i][9]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'MKTG_STRT_BULK\',\''+MKTG_STRT_BULK[i][9]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								}  
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="9">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'MKTG_STRT_BULK\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step4").html(htmlstr);	
							break;
		       			case 'AGMT_LDNG' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Action Plan to augment loading and diversify commodity basket</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Action plan with deadline</th>';
								    htmlstr+='<th>Officer Assigned</th>';
								    htmlstr+='<th>Commodity</th>';
								    htmlstr+='<th>Volume Targeted (MT)</th>';
								    htmlstr+='<th>Revenue Targeted (In Cr)</th>';
								    htmlstr+='<th>Cost Involved (In Cr)</th>';
								    htmlstr+='<th>Transportation Requirements (Types of Rakes needed)</th>';
								    htmlstr+='<th>Handholding needed from Railway Board</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';                   							  
								for(var i=0;i<obj.DataList1.length;i++)
								{
									AGMT_LDNG[i]=new Array(11);	
									AGMT_LDNG[i][0]=obj.DataList1[i].Val1;
									AGMT_LDNG[i][1]=obj.DataList1[i].Val2;
									AGMT_LDNG[i][2]=obj.DataList1[i].Val3;
									AGMT_LDNG[i][3]=obj.DataList1[i].Val4;
									AGMT_LDNG[i][4]=obj.DataList1[i].Val5;
									AGMT_LDNG[i][5]=obj.DataList1[i].Val6;
									AGMT_LDNG[i][6]=obj.DataList1[i].Val7;
									AGMT_LDNG[i][7]=obj.DataList1[i].Val8;
									AGMT_LDNG[i][8]=obj.DataList1[i].Val9;
									AGMT_LDNG[i][9]=obj.DataList1[i].Val10;
									AGMT_LDNG[i][10]=obj.DataList1[i].Val11;
									var link='';
									if(AGMT_LDNG[i][9]!="")
									{
										link='<a href="javascript:void(0)" class="download-file-link" onclick="downloadProposalFile(\''+AGMT_LDNG[i][9]+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;View Proposal</a>';
									}

									htmlstr+='<tr><td>'+AGMT_LDNG[i][0]+'</td><td>'+AGMT_LDNG[i][1]+'</td><td>'+AGMT_LDNG[i][2]+'</td><td>'+AGMT_LDNG[i][3]+'</td><td>'+AGMT_LDNG[i][4]+'</td><td>'+AGMT_LDNG[i][5]+'</td><td>'+AGMT_LDNG[i][6]+'</td><td>'+AGMT_LDNG[i][7]+'</td><td>'+AGMT_LDNG[i][8]+'</td><td class="text-center">'+link+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'AGMT_LDNG\', \''+AGMT_LDNG[i][10]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'AGMT_LDNG\',\''+AGMT_LDNG[i][10]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="10">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'AGMT_LDNG\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step5").html(htmlstr);	
							break;

		       			case 'AGMT_LDNG1' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Action Plan to augment loading and diversify commodity basket</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Commodity</th>';
								    htmlstr+='<th>Consignor/Industry Targeted</th>';
								    htmlstr+='<th>Traffic Type</th>';
								    htmlstr+='<th>Stock Type</th>';
								    htmlstr+='<th>Action Points</th>';
								    htmlstr+='<th class="other-col">Description of Other</th>';
								    htmlstr+='<th>Officer Nominated</th>';
								    htmlstr+='<th>Cost Involved (In Cr)</th>';
								    htmlstr+='<th>Volume Targeted (in Million Tonnes)</th>';
								    htmlstr+='<th>Revenue Targeted (In Cr)</th>';
								    htmlstr+='<th>Handholding needed from Railway Board</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';                   							  
								for(var i=0;i<obj.DataList1.length;i++)
								{
									AGMT_LDNG1[i]=new Array(15);	
									AGMT_LDNG1[i][0]=obj.DataList1[i].Val1;
									AGMT_LDNG1[i][1]=obj.DataList1[i].Val2;
									AGMT_LDNG1[i][2]=obj.DataList1[i].Val3;
									AGMT_LDNG1[i][3]=obj.DataList1[i].Val4;
									AGMT_LDNG1[i][4]=obj.DataList1[i].Val5;
									AGMT_LDNG1[i][5]=obj.DataList1[i].Val6;
									AGMT_LDNG1[i][6]=obj.DataList1[i].Val7;
									AGMT_LDNG1[i][7]=obj.DataList1[i].Val8;
									AGMT_LDNG1[i][8]=obj.DataList1[i].Val9;
									AGMT_LDNG1[i][9]=obj.DataList1[i].Val10;
									AGMT_LDNG1[i][10]=obj.DataList1[i].Val11;
									AGMT_LDNG1[i][11]=obj.DataList1[i].Val12;
									AGMT_LDNG1[i][12]=obj.DataList1[i].Val13;
									AGMT_LDNG1[i][13]=obj.DataList1[i].Val14;
									AGMT_LDNG1[i][14]=obj.DataList1[i].Val15;
									var link='';
									if(AGMT_LDNG1[i][12]!="")
									{
										link='<a href="javascript:void(0)" class="download-file-link" onclick="downloadProposalFile(\''+AGMT_LDNG1[i][14]+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;View Proposal</a>';
									}
									var actnplandesc=fetchCodeDesc('AP',AGMT_LDNG1[i][6]);
									htmlstr+='<tr><td>'+AGMT_LDNG1[i][0]+'</td><td>'+AGMT_LDNG1[i][1]+'</td><td>'+AGMT_LDNG1[i][2]+'</td><td>'+AGMT_LDNG1[i][3]+'</td><td>'+AGMT_LDNG1[i][4]+'</td><td>'+AGMT_LDNG1[i][5]+'</td><td>'+actnplandesc+showOtherVal(AGMT_LDNG1[i][7])+'</td><td class="other-col">'+AGMT_LDNG1[i][7]+'</td><td>'+AGMT_LDNG1[i][8]+'</td><td>'+fixDecimal(AGMT_LDNG1[i][9])+'</td><td>'+fixDecimal(AGMT_LDNG1[i][10])+'</td><td>'+fixDecimal(AGMT_LDNG1[i][11])+'</td><td class="text-center">'+link+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'AGMT_LDNG1\', \''+AGMT_LDNG1[i][13]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'AGMT_LDNG1\',\''+AGMT_LDNG1[i][13]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="13">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'AGMT_LDNG1\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step5a").html(htmlstr);	
							break;
		       			case 'FF_CRNT' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight Forwarders, Aggregators and Logistic Partners (Current)</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of Freight Forwarder/ Aggregator/ Logistics Partner</th>';
								    htmlstr+='<th>Contact Detail</th>';
								    htmlstr+='<th>Volume of Traffic handled (in Million Tonnes)</th>';
								    htmlstr+='<th>Revenue (In Cr)</th>';
								    htmlstr+='<th>Originating to Destination Station</th>';
								    htmlstr+='<th>Commodity Detail</th>';
								    htmlstr+='<th>Deployed On Date</th>';
								    htmlstr+='<th>Remarks/Any Other Assessment</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
							
								for(var i=0;i<obj.DataList1.length;i++)
								{
									FF_CRNT[i]=new Array(11);	
									FF_CRNT[i][0]=obj.DataList1[i].Val1;
									FF_CRNT[i][1]=obj.DataList1[i].Val2;
									FF_CRNT[i][2]=obj.DataList1[i].Val3;
									FF_CRNT[i][3]=obj.DataList1[i].Val4;
									FF_CRNT[i][4]=obj.DataList1[i].Val5;
									FF_CRNT[i][5]=obj.DataList1[i].Val6;
									FF_CRNT[i][6]=obj.DataList1[i].Val7;
									FF_CRNT[i][7]=obj.DataList1[i].Val8;
									FF_CRNT[i][8]=obj.DataList1[i].Val9;
									FF_CRNT[i][9]=obj.DataList1[i].Val10;
									FF_CRNT[i][10]=obj.DataList1[i].Val11;
									htmlstr+='<tr><td>'+FF_CRNT[i][0]+'</td><td>'+FF_CRNT[i][1]+'</td><td>'+FF_CRNT[i][2]+'</td><td>'+FF_CRNT[i][3]+'</td><td>'+fixDecimal(FF_CRNT[i][4])+'</td><td>'+fixDecimal(FF_CRNT[i][5])+'</td><td>'+FF_CRNT[i][6]+'</td><td>'+fetchNumCmdtName(FF_CRNT[i][7])+'</td><td>'+FF_CRNT[i][8]+'</td><td>'+FF_CRNT[i][9]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'FF_CRNT\', \''+FF_CRNT[i][10]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'FF_CRNT\',\''+FF_CRNT[i][10]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="10">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'FF_CRNT\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step6").html(htmlstr);	
							break;
							
		       			case 'FF_PROPOSED' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight Forwarders, Aggregators and Logistic Partners (Proposed)</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of Freight Forwarder/ Aggregator/ Logistics Partner</th>';
								    htmlstr+='<th>Contact Detail</th>';
								    htmlstr+='<th>Volume of Traffic Proposed to be handled (in Million Tonnes)</th>';
								    htmlstr+='<th>Revenue (In Cr)</th>';
								    htmlstr+='<th>Originating to Destination Station</th>';
								    htmlstr+='<th>Commodity Detail</th>';
								    htmlstr+='<th>Progress Status</th>';
								    htmlstr+='<th>Remarks/Any Other Assessment</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									FF_PROPOSED[i]=new Array(11);	
									FF_PROPOSED[i][0]=obj.DataList1[i].Val1;
									FF_PROPOSED[i][1]=obj.DataList1[i].Val2;
									FF_PROPOSED[i][2]=obj.DataList1[i].Val3;
									FF_PROPOSED[i][3]=obj.DataList1[i].Val4;
									FF_PROPOSED[i][4]=obj.DataList1[i].Val5;
									FF_PROPOSED[i][5]=obj.DataList1[i].Val6;
									FF_PROPOSED[i][6]=obj.DataList1[i].Val7;
									FF_PROPOSED[i][7]=obj.DataList1[i].Val8;
									FF_PROPOSED[i][8]=obj.DataList1[i].Val9;
									FF_PROPOSED[i][9]=obj.DataList1[i].Val10;
									FF_PROPOSED[i][10]=obj.DataList1[i].Val11;
									htmlstr+='<tr><td>'+FF_PROPOSED[i][0]+'</td><td>'+FF_PROPOSED[i][1]+'</td><td>'+FF_PROPOSED[i][2]+'</td><td>'+FF_PROPOSED[i][3]+'</td><td>'+fixDecimal(FF_PROPOSED[i][4])+'</td><td>'+fixDecimal(FF_PROPOSED[i][5])+'</td><td>'+FF_PROPOSED[i][6]+'</td><td>'+fetchNumCmdtName(FF_PROPOSED[i][7])+'</td><td>'+FF_PROPOSED[i][8]+'</td><td>'+FF_PROPOSED[i][9]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'FF_PROPOSED\', \''+FF_PROPOSED[i][10]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'FF_PROPOSED\',\''+FF_PROPOSED[i][10]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								}	 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="10">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'FF_PROPOSED\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step7").html(htmlstr);	
							break;
							
		       			case 'INIT_TRFC_BOOST' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Cross learning from Zones: Initiatives taken to boost traffic in year ending March 31, 2021 and to date</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">List of Initiatives Taken</th>';
								    htmlstr+='<th rowspan="2">Commodity</th>';
								    htmlstr+='<th colspan="2">Increase in traffic</th>';
								    htmlstr+='<th rowspan="2">Remarks/Any other assessment</th>';
								    htmlstr+='<th rowspan="2">&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Additional Loading (in Million Tonnes)</th>';
								    htmlstr+='<th>Additional Revenue (Cr)</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									INIT_TRFC_BOOST[i]=new Array(8);	
									INIT_TRFC_BOOST[i][0]=obj.DataList1[i].Val1;
									INIT_TRFC_BOOST[i][1]=obj.DataList1[i].Val2;
									INIT_TRFC_BOOST[i][2]=obj.DataList1[i].Val3;
									INIT_TRFC_BOOST[i][3]=obj.DataList1[i].Val4;
									INIT_TRFC_BOOST[i][4]=obj.DataList1[i].Val5;
									INIT_TRFC_BOOST[i][5]=obj.DataList1[i].Val6;
									INIT_TRFC_BOOST[i][6]=obj.DataList1[i].Val7;
									INIT_TRFC_BOOST[i][7]=obj.DataList1[i].Val8;
									htmlstr+='<tr><td>'+INIT_TRFC_BOOST[i][0]+'</td><td>'+INIT_TRFC_BOOST[i][1]+'</td><td>'+INIT_TRFC_BOOST[i][2]+'</td><td>'+INIT_TRFC_BOOST[i][3]+'</td><td>'+fixDecimal(INIT_TRFC_BOOST[i][4])+'</td><td>'+fixDecimal(INIT_TRFC_BOOST[i][5])+'</td><td>'+INIT_TRFC_BOOST[i][6]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'INIT_TRFC_BOOST\', \''+INIT_TRFC_BOOST[i][7]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'INIT_TRFC_BOOST\',\''+INIT_TRFC_BOOST[i][7]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="7">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'INIT_TRFC_BOOST\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step8").html(htmlstr);	
							break;
							
		       			case 'SWOT_ANLY' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  SWOT Analysis</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Commodity</th>';
								    htmlstr+='<th>Strengths</th>';
								    htmlstr+='<th class="other-col">Other Strength</th>';
								    htmlstr+='<th>Brief Description of Strengths</th>';
								    htmlstr+='<th>Weaknesses</th>';
								    htmlstr+='<th class="other-col">Other Weakness</th>';
								    htmlstr+='<th>Brief Description of Weaknesses</th>';
								    htmlstr+='<th>Opportunities</th>';
								    htmlstr+='<th class="other-col">Other Opportunity</th>';
								    htmlstr+='<th>Brief Description of Opportunities</th>';
								    htmlstr+='<th>Threats</th>';
								    htmlstr+='<th class="other-col">Other Threat</th>';
								    htmlstr+='<th>Brief Description of Threats</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									SWOT_ANLY[i]=new Array(16);	
									SWOT_ANLY[i][0]=obj.DataList1[i].Val1;
									SWOT_ANLY[i][1]=obj.DataList1[i].Val2;
									SWOT_ANLY[i][2]=obj.DataList1[i].Val3;
									SWOT_ANLY[i][3]=obj.DataList1[i].Val4;
									SWOT_ANLY[i][4]=obj.DataList1[i].Val5;
									SWOT_ANLY[i][5]=obj.DataList1[i].Val6;
									SWOT_ANLY[i][6]=obj.DataList1[i].Val7;
									SWOT_ANLY[i][7]=obj.DataList1[i].Val8;
									SWOT_ANLY[i][8]=obj.DataList1[i].Val9;
									SWOT_ANLY[i][9]=obj.DataList1[i].Val10;
									SWOT_ANLY[i][10]=obj.DataList1[i].Val11;
									SWOT_ANLY[i][11]=obj.DataList1[i].Val12;
									SWOT_ANLY[i][12]=obj.DataList1[i].Val13;
									SWOT_ANLY[i][13]=obj.DataList1[i].Val14;
									SWOT_ANLY[i][14]=obj.DataList1[i].Val15;
									SWOT_ANLY[i][15]=obj.DataList1[i].Val16;

									var strg=fetchCodeDesc('S',SWOT_ANLY[i][3]);									
									var weak=fetchCodeDesc('W',SWOT_ANLY[i][6]);
									var oprt=fetchCodeDesc('O',SWOT_ANLY[i][9]);
									var thrt=fetchCodeDesc('T',SWOT_ANLY[i][12]);
									htmlstr+='<tr><td>'+SWOT_ANLY[i][0]+'</td><td>'+SWOT_ANLY[i][1]+'</td><td>'+SWOT_ANLY[i][2]+'</td><td>'+strg+showOtherVal(SWOT_ANLY[i][4])+'</td><td class="other-col">'+SWOT_ANLY[i][4]+'</td><td>'+SWOT_ANLY[i][5]+'</td><td>'+weak+showOtherVal(SWOT_ANLY[i][7])+'</td><td class="other-col">'+SWOT_ANLY[i][7]+'</td><td>'+SWOT_ANLY[i][8]+'</td><td>'+oprt+showOtherVal(SWOT_ANLY[i][10])+'</td><td class="other-col">'+SWOT_ANLY[i][10]+'</td><td>'+SWOT_ANLY[i][11]+'</td><td>'+thrt+showOtherVal(SWOT_ANLY[i][13])+'</td><td class="other-col">'+SWOT_ANLY[i][13]+'</td><td>'+SWOT_ANLY[i][14]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'SWOT_ANLY\', \''+SWOT_ANLY[i][15]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'SWOT_ANLY\',\''+SWOT_ANLY[i][15]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									
										htmlstr+='<tfoot>';
									    	htmlstr+='<tr><td colspan="16">';
									    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'SWOT_ANLY\');return true;"><i class="fa fa-plus"></i>';
										htmlstr+='</a>';
										htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step9").html(htmlstr);	
							break;
							
		       			case 'INDS_OUTREACH' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Industry Outreach for Zone/Division</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">Category</th>';
								    htmlstr+='<th rowspan="2">Targeted Industry</th>';
								    htmlstr+='<th colspan="3">Interaction Detail</th>';
								    htmlstr+='<th rowspan="2">Commodity</th>';
								    htmlstr+='<th colspan="2">Additional Traffic Expected</th>';
								    htmlstr+='<th rowspan="2">&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Representative Name</th>';
								    htmlstr+='<th>Designation</th>';
								    htmlstr+='<th>Interacted On</th>';
								    htmlstr+='<th>Additional Revenue Expected (in Cr)</th>';
								    htmlstr+='<th>Additional Loading Expected (in Million Tonnes)</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									INDS_OUTREACH[i]=new Array(8);	
									INDS_OUTREACH[i][0]=obj.DataList1[i].Val1;
									INDS_OUTREACH[i][1]=obj.DataList1[i].Val2;
									INDS_OUTREACH[i][2]=obj.DataList1[i].Val3;
									INDS_OUTREACH[i][3]=obj.DataList1[i].Val4;
									INDS_OUTREACH[i][4]=obj.DataList1[i].Val5;
									INDS_OUTREACH[i][5]=obj.DataList1[i].Val6;
									INDS_OUTREACH[i][6]=obj.DataList1[i].Val7;
									INDS_OUTREACH[i][7]=obj.DataList1[i].Val8;
									INDS_OUTREACH[i][8]=obj.DataList1[i].Val9;
									INDS_OUTREACH[i][9]=obj.DataList1[i].Val10;
									INDS_OUTREACH[i][10]=obj.DataList1[i].Val11;
									htmlstr+='<tr><td>'+INDS_OUTREACH[i][0]+'</td><td>'+INDS_OUTREACH[i][1]+'</td><td>'+INDS_OUTREACH[i][2]+'</td><td>'+INDS_OUTREACH[i][3]+'</td><td>'+INDS_OUTREACH[i][4]+'</td><td>'+INDS_OUTREACH[i][5]+'</td><td>'+INDS_OUTREACH[i][6]+'</td><td>'+INDS_OUTREACH[i][7]+'</td><td>'+fixDecimal(INDS_OUTREACH[i][8])+'</td><td>'+fixDecimal(INDS_OUTREACH[i][9])+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'INDS_OUTREACH\', \''+INDS_OUTREACH[i][10]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'INDS_OUTREACH\',\''+INDS_OUTREACH[i][10]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="10">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'INDS_OUTREACH\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step10").html(htmlstr);	
							break;							
							
		       			case 'RAIL_COEF_BOOST' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Target Areas for Rail Co-efficient Boost and Gati-Shakti</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Areas leading to Immediate Growth (0-3 Months)</th>';
								    htmlstr+='<th class="other-col">Areas leading to Immediate Growth (OTHER)</th>';
								    htmlstr+='<th>Areas leading to Medium-term Growth (3-6 Months)</th>';
								    htmlstr+='<th class="other-col">Areas leading to Medium-term Growth (OTHER)</th>';
								    htmlstr+='<th>Areas leading to Long-term Growth (More than 6 Months)</th>';
								    htmlstr+='<th class="other-col">Areas leading to Long-term Growth (OTHER)</th>';
								    htmlstr+='<th>Traffic Plan</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									RAIL_COEF_BOOST[i]=new Array(10);	
									RAIL_COEF_BOOST[i][0]=obj.DataList1[i].Val1;
									RAIL_COEF_BOOST[i][1]=obj.DataList1[i].Val2;
									RAIL_COEF_BOOST[i][2]=obj.DataList1[i].Val3;
									RAIL_COEF_BOOST[i][3]=obj.DataList1[i].Val4;
									RAIL_COEF_BOOST[i][4]=obj.DataList1[i].Val5;
									RAIL_COEF_BOOST[i][5]=obj.DataList1[i].Val6;
									RAIL_COEF_BOOST[i][6]=obj.DataList1[i].Val7;
									RAIL_COEF_BOOST[i][7]=obj.DataList1[i].Val8;
									RAIL_COEF_BOOST[i][8]=obj.DataList1[i].Val9;
									RAIL_COEF_BOOST[i][9]=obj.DataList1[i].Val10;

									htmlstr+='<tr><td>'+RAIL_COEF_BOOST[i][0]+'</td><td>'+RAIL_COEF_BOOST[i][1]+'</td><td style="background:#ccffcc;">'+formatMultVals(RAIL_COEF_BOOST[i][2])+showOtherVal(RAIL_COEF_BOOST[i][3])+'</td><td style="background:#ccffcc;" class="other-col">'+RAIL_COEF_BOOST[i][3]+'</td><td style="background:#fff0b3;">'+formatMultVals(RAIL_COEF_BOOST[i][4])+showOtherVal(RAIL_COEF_BOOST[i][5])+'</td><td  class="other-col" style="background:#fff0b3;">'+RAIL_COEF_BOOST[i][5]+'</td><td style="background:#ffcccc;">'+formatMultVals(RAIL_COEF_BOOST[i][6])+showOtherVal(RAIL_COEF_BOOST[i][7])+'</td><td  class="other-col" style="background:#ffcccc;">'+RAIL_COEF_BOOST[i][7]+'</td><td><a href="javascript:void(0)" onclick="downloadTrfcPlan(\''+RAIL_COEF_BOOST[i][9]+'\');" style="color:#b32400 !important;font-weight:600;">'+RAIL_COEF_BOOST[i][8]+'</a></td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'RAIL_COEF_BOOST\', \''+RAIL_COEF_BOOST[i][9]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'RAIL_COEF_BOOST\',\''+RAIL_COEF_BOOST[i][9]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if(GG_DataCnfmStts!="Y")
								{
									if((GG_LognLocnFlag=="D") && (obj.DataList1.length>0))
									{
										//do nothing
									}
									else
									{
										htmlstr+='<tfoot>';
								    		htmlstr+='<tr><td colspan="9">';
								    		htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'RAIL_COEF_BOOST\');return true;"><i class="fa fa-plus"></i>';
										htmlstr+='</a>';
										htmlstr+='</tfoot>';
									}
								}
								htmlstr+='</table>';
							$("#step11").html(htmlstr);	
							break;
		       			case 'OTHR_ASPECT' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Any other Aspect the Division wishes to highlight</p>';
			       				htmlstr+='<table class="table table-input table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Aspect</th>';
								    htmlstr+='<th>&nbsp;</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									OTHR_ASPECT[i]=new Array(3);	
									OTHR_ASPECT[i][0]=obj.DataList1[i].Val1;
									OTHR_ASPECT[i][1]=obj.DataList1[i].Val2;
									OTHR_ASPECT[i][2]=obj.DataList1[i].Val3;
									htmlstr+='<tr><td>'+OTHR_ASPECT[i][0]+'</td><td>'+OTHR_ASPECT[i][1]+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editRecord(this, \'OTHR_ASPECT\', \''+OTHR_ASPECT[i][2]+'\','+i+');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button type="button" class="btn btn-light btn-edit" onclick="delRecord(this,\'OTHR_ASPECT\',\''+OTHR_ASPECT[i][2]+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
								} 
								if((selZone==GG_CrntZone) && (GG_DataCnfmStts!="Y"))
								{
									htmlstr+='<tfoot>';
								    	htmlstr+='<tr><td colspan="3">';
								    	htmlstr+='<a class="btn-add-row shtw" data-toggle="modal" data-target="#loginModal" onclick="addRecord(\'OTHR_ASPECT\');return true;"><i class="fa fa-plus"></i>';
									htmlstr+='</a>';
									htmlstr+='</tfoot>';
								}
								htmlstr+='</table>';
							$("#step12").html(htmlstr);	
							break;
		       			case 'ZONL_PERF' :
								var htmldvsnrevn='<table class="table table-striped table-brkup"><thead><tr><th rowspan="2">Division</th><th colspan="2">Revenue (In Rs Crores)</th><th rowspan="2">Variation %</th><th rowspan="2">Reported Till Date (In Rs Crores)</th></tr><tr><th>Last Year</th><th>Current Year Projection</th></tr></thead>';
								var htmldvsnldng='<table class="table table-striped table-brkup"><thead><tr><th rowspan="2">Division</th><th colspan="2">Loading (In Million Tonnes)</th><th rowspan="2">Variation %</th><th colspan="2">Progress (In Million Tonnes)</th><th rowspan="2">Progress (%)</th></tr><tr><th>Last Year</th><th>Current Year Projection</th><th>Current Year Target</th><th>Reported Till Date</th></tr></thead>';
								var htmltotlrevn='';
								var htmltotlldng='';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									ZONL_PERF[i]=new Array(9);	
									ZONL_PERF[i][0]=obj.DataList1[i].Val1;
									ZONL_PERF[i][1]=obj.DataList1[i].Val2;
									ZONL_PERF[i][2]=obj.DataList1[i].Val3;
									ZONL_PERF[i][3]=obj.DataList1[i].Val4;
									ZONL_PERF[i][4]=obj.DataList1[i].Val5;
									ZONL_PERF[i][5]=obj.DataList1[i].Val6;
									ZONL_PERF[i][6]=obj.DataList1[i].Val7;
									ZONL_PERF[i][7]=obj.DataList1[i].Val8;
									ZONL_PERF[i][8]=obj.DataList1[i].Val9;
									if(ZONL_PERF[i][1]!="")
									{
										var crntrevn=ZONL_PERF[i][2];
										var prevrevn=ZONL_PERF[i][4];
										var crntldng=ZONL_PERF[i][3];
										var prevldng=ZONL_PERF[i][5];
										var prgspctg='';
										if(ZONL_PERF[i][6]!="")
										{
											if(ZONL_PERF[i][8]!="")
											{
												prgspctg=((Number(ZONL_PERF[i][8])*100)/Number(ZONL_PERF[i][6])).toFixed(2)+"%";
											}
										}
										if(crntrevn=="")
										{
											htmldvsnrevn+='<tr><td>'+ZONL_PERF[i][1]+'</td><td>'+ZONL_PERF[i][4]+'</td><td>'+ZONL_PERF[i][2]+'</td><td>&nbsp;</td><td>'+ZONL_PERF[i][7]+'</td></tr>';
											htmldvsnldng+='<tr><td>'+ZONL_PERF[i][1]+'</td><td>'+ZONL_PERF[i][5]+'</td><td>'+ZONL_PERF[i][3]+'</td><td>&nbsp;</td><td>'+ZONL_PERF[i][6]+'</td><td>'+ZONL_PERF[i][8]+'</td><td>'+prgspctg+'</td></tr>';
										}
										else
										{
											var revn=(((Number(crntrevn)-Number(prevrevn))*100)/Number(prevrevn)).toFixed(2);
											var ldng=(((Number(ZONL_PERF[i][3])-Number(ZONL_PERF[i][5]))*100)/Number(ZONL_PERF[i][5])).toFixed(2);
											var revn1='';
											var ldng1='';
											if(Number(crntrevn)>=Number(prevrevn))
											{
												revn1='<span class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+revn+'%</span>';
											}
											else
											{
												revn1='<span class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+revn+'%</span>';
											}

											if(Number(crntldng)>=Number(prevldng))
											{
												ldng1='<span class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+ldng+'%</span>';
											}
											else
											{
												ldng1='<span class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+ldng+'%</span>';
											}

											htmldvsnrevn+='<tr><td><a href="javascript:void(0)" onclick="fetchDvsnPerfFOIS(\''+ZONL_PERF[i][1]+'\');" style="color:#b32400;">'+ZONL_PERF[i][1]+'</a></td><td>'+ZONL_PERF[i][4]+'</td><td>'+ZONL_PERF[i][2]+'</td><td>'+revn1+'</td><td>'+ZONL_PERF[i][7]+'</td></tr>';
											htmldvsnldng+='<tr><td><a href="javascript:void(0)" onclick="fetchDvsnPerfFOIS(\''+ZONL_PERF[i][1]+'\');" style="color:#b32400;">'+ZONL_PERF[i][1]+'</a></td><td>'+ZONL_PERF[i][5]+'</td><td>'+ZONL_PERF[i][3]+'</td><td>'+ldng1+'</td><td>'+ZONL_PERF[i][6]+'</td><td>'+ZONL_PERF[i][8]+'</td><td>'+prgspctg+'</td></tr>';

										}
									}
									if(ZONL_PERF[i][1]=="")
									{
										var prgspctg='';
										if(ZONL_PERF[i][6]!="")
										{
											if(ZONL_PERF[i][8]!="")
											{
												prgspctg=((Number(ZONL_PERF[i][8])*100)/Number(ZONL_PERF[i][6])).toFixed(2)+"%";
											}
										}
										if(ZONL_PERF[i][2]!="")
										{
										
										}
										else
										{
											htmltotlrevn+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][4]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][2]+'</td><td style="background:#d9f2e6;">&nbsp;</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][7]+'</td></tr>';
											htmltotlldng+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][5]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][3]+'</td><td style="background:#d9f2e6;">&nbsp;</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][6]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][8]+'</td><td style="background:#d9f2e6;">'+prgspctg+'</td></tr>';
										}
										$("#lblCYRevn").html('Current Year Projection:&nbsp;<span class="perf-val">'+ZONL_PERF[i][2]+' Cr</span>');
										$("#lblCYLdng").html('Current Year Projection:&nbsp;<span class="perf-val">'+ZONL_PERF[i][3]+' MT</span>');
										$("#lblLYRevn").html('Last Year:&nbsp;<span class="perf-val">'+ZONL_PERF[i][4]+' Cr</span>');
										$("#lblLYLdng").html('Last Year:&nbsp;<span class="perf-val">'+ZONL_PERF[i][5]+' MT</span>');


										if(ZONL_PERF[i][2]!="")
										{
											var revnvarn=((Number(ZONL_PERF[i][2])-Number(ZONL_PERF[i][4]))*100)/Number(ZONL_PERF[i][4]);
											var ldngvarn=((Number(ZONL_PERF[i][3])-Number(ZONL_PERF[i][5]))*100)/Number(ZONL_PERF[i][5]);
											if(revnvarn<0)
											{
												$("#divLYRevn").html('<p class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</p>');
												htmltotlrevn+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][4]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][2]+'</td><td style="background:#d9f2e6;"><span class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</span></td><td style="background:#d9f2e6;">'+ZONL_PERF[i][7]+'</td></tr>';
											}
											else
											{
												$("#divLYRevn").html('<p class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</p>');
												htmltotlrevn+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][4]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][2]+'</td><td style="background:#d9f2e6;"><span class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</span></td><td style="background:#d9f2e6;">'+ZONL_PERF[i][7]+'</td></tr>';
											}

											if(ldngvarn<0)
											{
												$("#divLYLdng").html('<p class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</p>');
												htmltotlldng+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][5]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][3]+'</td><td style="background:#d9f2e6;"><span class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</span></td><td style="background:#d9f2e6;">'+ZONL_PERF[i][6]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][8]+'</td><td style="background:#d9f2e6;">'+prgspctg+'</td></tr>';
											}
											else
											{
												$("#divLYLdng").html('<p class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</p>');
												htmltotlldng+='<tr><td style="background:#d9f2e6;">TOTAL</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][5]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][3]+'</td><td style="background:#d9f2e6;"><span class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</span></td><td style="background:#d9f2e6;">'+ZONL_PERF[i][6]+'</td><td style="background:#d9f2e6;">'+ZONL_PERF[i][8]+'</td><td style="background:#d9f2e6;">'+prgspctg+'</td></tr>';
											}
										}
									}
									$("#txtZonlPerfRowId").val(ZONL_PERF[i][7]);					
			
								}
								htmldvsnrevn+=htmltotlrevn+'</table>';
								htmldvsnldng+=htmltotlldng+'</table>';

								$('#zonlRevnPerfBrkup').html(htmldvsnrevn);
								$('#zonlLdngPerfBrkup').html(htmldvsnldng);
								$(".btn-saveperf").hide();
								$(".btn-editperf").show();
							break;
		       			case 'DVSN_PERF' :
								for(var i=0;i<obj.DataList1.length;i++)
								{
									DVSN_PERF[i]=new Array(8);	
									DVSN_PERF[i][0]=obj.DataList1[i].Val1;
									DVSN_PERF[i][1]=obj.DataList1[i].Val2;
									DVSN_PERF[i][2]=obj.DataList1[i].Val3;
									DVSN_PERF[i][3]=obj.DataList1[i].Val4;
									DVSN_PERF[i][4]=obj.DataList1[i].Val5;
									DVSN_PERF[i][5]=obj.DataList1[i].Val6;
									DVSN_PERF[i][6]=obj.DataList1[i].Val7;
									DVSN_PERF[i][7]=obj.DataList1[i].Val8;

									$("#txtProjRevn").val(DVSN_PERF[i][2]);
									$("#txtProjLdng").val(DVSN_PERF[i][3]);
									$("#txtTrgtLdng").val(DVSN_PERF[i][6]);
									$("#lblLYRevn").html('Last Year:&nbsp;<span class="perf-val">'+DVSN_PERF[i][4]+' Cr</span>');
									$("#lblLYLdng").html('Last Year:&nbsp;<span class="perf-val">'+DVSN_PERF[i][5]+' MT</span>');
									var revnvarn=((Number(DVSN_PERF[i][2])-Number(DVSN_PERF[i][4]))*100)/Number(DVSN_PERF[i][4]);
									var ldngvarn=((Number(DVSN_PERF[i][3])-Number(DVSN_PERF[i][5]))*100)/Number(DVSN_PERF[i][5]);
									if(revnvarn<0)
									{
										$("#divLYRevn").html('<p class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</p>');
									}
									else
									{
										$("#divLYRevn").html('<p class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(revnvarn*10)/10+'%</p>');
									}

									if(ldngvarn<0)
									{
										$("#divLYLdng").html('<p class="perf-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</p>');
									}
									else
									{
										$("#divLYLdng").html('<p class="perf-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+Math.round(ldngvarn*10)/10+'%</p>');
									}
									$("#txtZonlPerfRowId").val(DVSN_PERF[i][7]);					
			
								}
								$(".btn-saveperf").hide();
								$(".btn-editperf").show();
							break;

						}
				
            }
            else
            {
                trxnFailed("Failed to Fetch Data! "+obj.ErorMesg);
            }
	}
	});	
}
function addRecord(inpt_ctgr)
{
	var content='';
	$("#divMesg").removeClass();
	$("#divMesg").html('');
	
	switch(inpt_ctgr)
	{
		case 'CMDT_COST_CMPR' :
			content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities and Comparison of costs with other modes</p>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm">';
					content+='<form>';
					   content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="txtIndsFrgtGnrt" class="form-control"><option selected="true" disabled="disabled">Industry of Freight Generation</option><option value="COAL AND COKE">COAL AND COKE</option><option value="MINERALS AND ORES">MINERALS AND ORES</option><option value="FOOD GRAINS, FLOURS AND PULSES">FOOD GRAINS, FLOURS AND PULSES</option><option value="CEMENT AND CLINKER">CEMENT AND CLINKER</option><option value="CHEMICAL MANURES">CHEMICAL MANURES</option><option value="IRON AND STEEL">IRON AND STEEL</option><option value="PETROLEUM PRODUCTS AND GASES">PETROLEUM PRODUCTS AND GASES</option><option value="CONTAINER SERVICE">CONTAINER SERVICE</option><option value="AUTOMOBILE">AUTOMOBILE</option><option value="SUGAR, SALT, SPICES AND OILS">SUGAR, SALT, SPICES AND OILS</option><option value="PIG, SPONGE, WROUGHT IRON">PIG, SPONGE, WROUGHT IRON</option><option value="STONE/BAMBOO CHIPS, GRANITE">STONE/BAMBOO CHIPS, GRANITE</option><option value="OTHER">OTHER</option></select>';
					    /*content+='<input type="text" class="form-control" id="txtIndsFrgtGnrt" placeholder="Industry of Freight Generation">';*/
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="text" class="form-control" id="txtIndsFrgtGnrtOthr" readonly placeholder="Detail of Other Industry">';
					    content+='</div>';
					    content+='</div>';

					    content+='<div class="col-lg-2 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<select id="selLocnType" class="form-control"><option value="D">DIVISION</option><option value="P">PORT</option><option value="T">TERMINAL</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<input type="text" class="form-control" id="txtPortTrml" list="porttrmllist" placeholder="Division/Port/Terminal"><datalist id="porttrmllist"></datalist>';
					    content+='</div>';
					    content+='</div>';

					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0" class="form-control" id="txtTotlVol" placeholder="Total Volume of Freight Across All Modes (in Million Tonnes)">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtRailPctg" min="1" max="100" placeholder="%Age of Traffic (Rail)">';
					    content+='</div>';
					    content+='</div></div>';
					    content+='<p class="inpt-lbl">Comparative Costs (Roads Per Ton Kms)</p>';
					    content+='<div class="container">';
					    content+='<div class="row">';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt050" placeholder="0-50">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt50100" placeholder="50-100">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number"  min="0" class="form-control" id="txt100200" placeholder="100-200">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt200300" placeholder="200-300">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt300400" placeholder="300-400">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt400500" placeholder="400-500">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt500600" placeholder="500-600">';
					   content+=' </div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt600Plus" placeholder="600+">';
					    content+='</div>';
					    content+='</div>';
					    content+='</div></div>';
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'CMDT_COST_CMPR\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					 content+='</div>';
				break;
		case 'CMDT_RAIL_COST' :
			content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities with their Rail Transportation Costs</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm">';
					content+='<form enctype="application/x-www-form-urlencoded">';
					   content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="txtIndsFrgtGnrt"  class="form-control required"><option selected="true" disabled="disabled">Industry of Freight Generation</option><option value="COAL AND COKE">COAL AND COKE</option><option value="MINERALS AND ORES">MINERALS AND ORES</option><option value="FOOD GRAINS, FLOURS AND PULSES">FOOD GRAINS, FLOURS AND PULSES</option><option value="CEMENT AND CLINKER">CEMENT AND CLINKER</option><option value="CHEMICAL MANURES">CHEMICAL MANURES</option><option value="IRON AND STEEL">IRON AND STEEL</option><option value="PETROLEUM PRODUCTS AND GASES">PETROLEUM PRODUCTS AND GASES</option><option value="CONTAINER SERVICE">CONTAINER SERVICE</option><option value="AUTOMOBILE">AUTOMOBILE</option><option value="SUGAR, SALT, SPICES AND OILS">SUGAR, SALT, SPICES AND OILS</option><option value="PIG, SPONGE, WROUGHT IRON">PIG, SPONGE, WROUGHT IRON</option><option value="STONE/BAMBOO CHIPS, GRANITE">STONE/BAMBOO CHIPS, GRANITE</option><option value="OTHER">OTHER</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="text" class="form-control" id="txtIndsFrgtGnrtOthr" readonly placeholder="Detail of Other Industry">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="selNumCmdt" class="form-control required" name="selNumCmdt"><option selected="true" disabled="disabled">Select Commodity</option>';
					    for(var i=0;i<aNumCmdt.length;i++)
					    {
						var numcode=aNumCmdt[i].substring(0,aNumCmdt[i].indexOf('-'));
						var cmdtname=aNumCmdt[i].substring(aNumCmdt[i].indexOf('-')+1);
						content+='<option value="'+numcode+'">'+cmdtname+'</option>';
					    }
					    content+='</select>';
					    content+='</div>';
					    content+='</div>';

					    content+='<div class="col-lg-3 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<select id="selLocnType" class="form-control required"><option selected="true" disabled="disabled">ORIGINATING TYPE</option><option value="P">PORT</option><option value="T">TERMINAL</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-6">';
					    content+='<div class="form-group" id="divPortTrml">';
					     /* content+='<input type="text" class="form-control required" id="txtPortTrml" list="porttrmllist" placeholder="Port/Terminal"><datalist id="porttrmllist"></datalist>';*/
					   /* content+='<select id="selPort" name="selPort[]" class="form-control required" multiple></select>';	
					    content+='<select id="selTrml" name="selTrml[]" class="form-control required" multiple></select>';*/
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0" class="form-control required" id="txtTotlVol" placeholder="Total Vol of Freight Across All Modes (in Million Tonnes)">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control required" id="txtRailPctg" min="1" max="100" placeholder="%Age Of Rail">';
					    content+='</div>';
					    content+='</div></div>';					
					    content+='<div class="container" id="divCmdtRailCost">';
					    content+='</div>';
					    content+='<p style="font-size:14px;font-weight:600;color:#b32400;text-align:left;">Enter Additional Cost Per Ton (Including First-Mile Last-Mile, Loading Cost, Unloading Cost etc.):</p><div class="row">';
					    /*
					    content+='<div class="col-lg-3 col-xl-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<div class="input-group input-group-lg"><div class="input-group-prepend"><span class="input-group-text" id="inputGroup-sizing-lg" style="font-weight:600;font-size:13px;">Loading Cost</span></div><input type="number" min="0" class="form-control required" id="txtLdngCost" aria-label="Large" aria-describedby="inputGroup-sizing-sm" style="font-size:14px;">&nbsp;&nbsp;<span style="font-size:13px;font-weight:600;padding-top:10px;">Rs.</span>';
					    content+='</div></div></div>';
					    content+='<div class="col-lg-3 col-xl-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<div class="input-group input-group-lg"><div class="input-group-prepend"><span class="input-group-text" id="inputGroup-sizing-lg" style="font-weight:600;font-size:13px;">Unloading Cost</span></div><input type="number" min="0" class="form-control required" id="txtUldgCost" aria-label="Large" aria-describedby="inputGroup-sizing-sm" style="font-size:14px;">&nbsp;&nbsp;<span style="font-size:13px;font-weight:600;padding-top:10px;">Rs.</span>';
					    content+='</div></div></div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<div class="input-group input-group-lg"><div class="input-group-prepend"><span class="input-group-text" id="inputGroup-sizing-lg" style="font-weight:600;font-size:13px;">Additional First-Mile/Last-Mile Cost</span></div><input type="number" min="0" class="form-control required" id="txtLMFM" aria-label="Large" aria-describedby="inputGroup-sizing-sm" style="font-size:14px;">&nbsp;&nbsp;<span style="font-size:13px;font-weight:600;padding-top:10px;">Rs.</span>';
					    content+='</div></div></div>';
					    */
					    content+='<div class="col-lg-6 col-xl-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="number" min="0" class="form-control required" id="txtAddlCost" aria-label="Large" aria-describedby="inputGroup-sizing-sm" style="font-size:14px;">&nbsp;&nbsp;<span style="font-size:13px;font-weight:600;padding-top:10px;">Rs.</span>';
					    content+='</div></div>';
					    content+='</div>';
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'CMDT_RAIL_COST\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					 content+='</div>';
				break;

		case 'CMDT_ROAD_COST' :
			content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities with their Comparative Road Transportation Costs</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm">';
					content+='<form>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-1 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<label for="txtZone" style="font-size:13px;font-weight:600;color:#4d4d4d;">Zone</label><input type="text" readonly id="txtZone" class="form-control" />';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<label for="txtDvsn" style="font-size:13px;font-weight:600;color:#4d4d4d;">Division</label><input type="text" readonly id="txtDvsn" class="form-control" />';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<label for="txtIndsRoadCost" style="font-size:13px;font-weight:600;color:#4d4d4d;">Industry of Freight Generation</label><input type="text" readonly id="txtIndsRoadCost" class="form-control" />';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<label for="txtOthrIndsRoadCost" style="font-size:13px;font-weight:600;color:#4d4d4d;">Description (OTHER)</label><input type="text" readonly id="txtOthrIndsRoadCost" class="form-control" />';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<label for="txtCmdtRoadCost" style="font-size:13px;font-weight:600;color:#4d4d4d;">Commodity</label><input type="text" readonly id="txtCmdtRoadCost" class="form-control">';
					    content+='</div></div></div>';					
					    content+='<div class="container" id="divCmdtRailCost">';
					    content+='</div>';
					    content+='<div class="row"><div class="col-lg-12">';				
					    content+='<table class="table table-input"><thead><tr><th colspan="15">Road Transportation Cost (Per Ton Km) for listed distance slabs (Incl GST)</th></tr><tr><th>0-100</th><th>100-200</th><th>200-300</th><th>300-400</th><th>400-500</th><th>500-600</th><th>600-700</th><th>700-800</th><th>800-900</th><th>900-1000</th><th>1000-1100</th><th>1100-1200</th><th>1200-1300</th><th>1300-1400</th><th>1400-1500</th></tr></thead>';
					    content+='<tr><td><input type="number" min="0" id="slab1" style="width:60px;"></td><td><input type="number" min="0" id="slab2" style="width:60px;"></td><td><input type="number" min="0" id="slab3" style="width:60px;"></td><td><input type="number" min="0" id="slab4" style="width:60px;"></td><td><input type="number" min="0" id="slab5" style="width:60px;"></td><td><input type="number" min="0" id="slab6" style="width:60px;"></td><td><input type="number" min="0" id="slab7" style="width:60px;"></td><td><input type="number" min="0" id="slab8" style="width:60px;"></td><td><input type="number" min="0" id="slab9" style="width:60px;"></td><td><input type="number" min="0" id="slab10" style="width:60px;"></td><td><input type="number" min="0" id="slab11" style="width:60px;"></td><td><input type="number" min="0" id="slab12" style="width:60px;"></td><td><input type="number" min="0" id="slab13" style="width:60px;"></td><td><input type="number" min="0" id="slab14" style="width:60px;"></td><td><input type="number" min="0" id="slab15" style="width:60px;"></td></tr>';
					    content+='</table></div></div>';
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'CMDT_ROAD_COST\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					 content+='</div>';
				break;
				case 'END_MILE_COST':
  					content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Comparative Transportation and End-mile Costs</p>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
					  content+='<form>';
					    content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="txtIndsFrgtGnrt" class="form-control"><option selected="true" disabled="disabled">Industry of Freight Generation</option><option value="COAL AND COKE">COAL AND COKE</option><option value="MINERALS AND ORES">MINERALS AND ORES</option><option value="FOOD GRAINS, FLOURS AND PULSES">FOOD GRAINS, FLOURS AND PULSES</option><option value="CEMENT AND CLINKER">CEMENT AND CLINKER</option><option value="CHEMICAL MANURES">CHEMICAL MANURES</option><option value="IRON AND STEEL">IRON AND STEEL</option><option value="PETROLEUM PRODUCTS AND GASES">PETROLEUM PRODUCTS AND GASES</option><option value="CONTAINER SERVICE">CONTAINER SERVICE</option><option value="AUTOMOBILE">AUTOMOBILE</option><option value="SUGAR, SALT, SPICES AND OILS">SUGAR, SALT, SPICES AND OILS</option><option value="PIG, SPONGE, WROUGHT IRON">PIG, SPONGE, WROUGHT IRON</option><option value="STONE/BAMBOO CHIPS, GRANITE">STONE/BAMBOO CHIPS, GRANITE</option><option value="OTHER">OTHER</option></select>';
					    /*content+='<input type="text" class="form-control required" id="txtIndsFrgtGnrt" placeholder="Industry of Freight Generation">';*/
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="text" class="form-control" readonly id="txtIndsFrgtGnrtOthr" placeholder="Detail of Other Industry">';
					    content+='</div>';
					    content+='</div>';
					    content+='</div>';
					    content+='<p class="inpt-lbl">Comparative Costs for Rail (Per Ton Kms)</p>';
					    content+='<div class="container">';
					    content+='<div class="row">';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt050" placeholder="0-50">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt50100" placeholder="50-100">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt100200" placeholder="100-200">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt200300" placeholder="200-300">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number"  min="0" class="form-control" id="txt300400" placeholder="300-400">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number"  min="0" class="form-control" id="txt400500" placeholder="400-500">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt500600" placeholder="500-600">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txt600Plus" placeholder="600+">';
					    content+='</div>';
					    content+='</div>';
					    content+='</div></div>';
					    content+='<p class="inpt-lbl">Additional Last & First Mile Cost Ex-Rail (Per Ton Km)</p>';
					    content+='<div class="container">';
					    content+='<div class="row">';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					     content+=' <input type="number" min="0"  class="form-control" id="txtEndMile050" placeholder="0-50">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile50100" placeholder="50-100">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile100200" placeholder="100-200">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile200300" placeholder="200-300">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile300400" placeholder="300-400">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile400500" placeholder="400-500">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile500600" placeholder="500-600">';
					    content+='</div></div>';
					    content+='<div class="col-lg-2 col-sm-4">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control" id="txtEndMile600Plus" placeholder="600+">';
					    content+='</div>';
					    content+='</div>';
					    content+='</div></div>';
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'END_MILE_COST\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					  content+='</div>';
  					break;
  					
  				case 'CNSR_WISE_INFO':
  					content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Granular information Consignor Wise with the name of Industry/consignor</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
					  content+='<form>';
					    content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="txtIndsFrgtGnrt" class="form-control"><option selected="true" disabled="disabled">Industry of Freight Generation</option><option value="COAL AND COKE">COAL AND COKE</option><option value="MINERALS AND ORES">MINERALS AND ORES</option><option value="FOOD GRAINS, FLOURS AND PULSES">FOOD GRAINS, FLOURS AND PULSES</option><option value="CEMENT AND CLINKER">CEMENT AND CLINKER</option><option value="CHEMICAL MANURES">CHEMICAL MANURES</option><option value="IRON AND STEEL">IRON AND STEEL</option><option value="PETROLEUM PRODUCTS AND GASES">PETROLEUM PRODUCTS AND GASES</option><option value="CONTAINER SERVICE">CONTAINER SERVICE</option><option value="AUTOMOBILE">AUTOMOBILE</option><option value="SUGAR, SALT, SPICES AND OILS">SUGAR, SALT, SPICES AND OILS</option><option value="PIG, SPONGE, WROUGHT IRON">PIG, SPONGE, WROUGHT IRON</option><option value="STONE/BAMBOO CHIPS, GRANITE">STONE/BAMBOO CHIPS, GRANITE</option><option value="OTHER">OTHER</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="text" class="form-control" readonly id="txtIndsFrgtGnrtOthr" placeholder="Detail of Other Industry">';
					    content+='</div>';
					    content+='</div>';
					   	content+='<div class="col-lg-4 col-sm-12">';
					    	content+='<div class="form-group">';
					      	content+='<input type="text" class="form-control required" id="txtCmdt" list="cmdtlist" placeholder="Name of the Commodity"><datalist id="cmdtlist"></datalist>';
					    	content+='</div>';
					    	content+='</div>';
					   /*
					    content+='<div class="col-lg-2 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<select id="selLocnType" class="form-control required"><option selected="true" disabled="disabled">ORIGINATING TYPE</option><option value="P">PORT</option><option value="T">TERMINAL</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-2 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<input type="text" class="form-control required" id="txtPortTrml"  list="porttrmllist" placeholder="Port/Terminal"><datalist id="porttrmllist"></datalist>';
					    content+='</div>';
					    content+='</div>';
					*/
					   content+=' </div>';
					   content+=' <div class="row">';
					    content+='<div class="col-lg-2 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<select id="selTrfcType" class="form-control required"><option selected="true" disabled="disabled">Traffic Type</option><option value="PARCEL">PARCEL</option><option value="GOODS">GOODS</option><option value="CONTAINER">CONTAINER</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-6">';
					    content+='<div class="form-group">';
					      content+='<select id="selStckType1" name="selStckType1[]" multiple class="form-control required">';
						 for(var i=0;i<aRakeType.length;i++)
					   {
							content+='<option value="'+aRakeType[i].substring(0,aRakeType[i].indexOf('-'))+'">'+aRakeType[i]+'</option>';
					    }

					content+='</select>';
					    content+='</div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control required" id="txtTotlVol" placeholder="Total Vol of Freight across all modes (in Million Tonnes)">';
					   content+=' </div>';
					   content+=' </div>';
					   content+=' <div class="col-lg-3 col-sm-12">';
					   content+=' <div class="form-group">';
					    content+='  <input type="number" min="0"  class="form-control required" id="txtRailPctg" min="1" max="100" placeholder="%Age of Traffic (Rail)">';
					   content+=' </div>';
					    content+='</div></div>';
					   /* content+='<textarea id="txtCmptAdvt" placeholder="Broad assessment of reason for competitor" class="form-control text-area-input required"></textarea>';*/
					   content+='<div class="row"><div class="col-lg-6 col-sm-12"><select id="selCmptAdvt" multiple class="form-control" name="selCmptAdvt[]"><option value="FIRST AND LAST MILE COST">First and Last Mile Cost</option><option value="MULTIPLE HANDLING">Multiple Handling</option><option value="LACK OF WAREHOUSES">Lack of Warehouses</option><option value="LACK OF FACILITIES AT GOODS SHEDS">Lack of Facilities at Goods Sheds</option><option value="DOOR TO DOOR CONNECTIVITY">Door to Door Connectivity</option><option value="LOCAL USAGE/CONSUMPTION (SHORT LEAD)">Local Usage/Consumption (Short Lead)</option><option value="SEASONAL SURCHARGES IMPOSED">Seasonal Surcharges Imposed</option><option value="TRANSIT TIME COMMITMENT">Transit Time Commitment</option><option value="INSTANT CLAIMS/INSURANCE">Instant Claims/Insurance</option><option value="PILFERAGE/THEFT">Pilferage/Theft</option><option value="IMPROVEMENT IN ROAD INFRASTRUCTURE AND INTRODUCTION OF HIGHWAYS">Improvement in Road Infrastructure and Introduction of Highways</option><option value="MOVEMENT OF SMALL QUANTITIES BY ROAD ON DAILY BASIS">Movement of Small Quntitites by Road on Daily Basis</option><option value="SHORTER DISTANCE VIA ROAD">Shorter Distance Via Road</option><option value="TIMELY AVAILABILITY OF RAKES EVEN IN BUSY SEASON">Timely Availability even in Busy Season</option><option value="USE OF REFRIGERATED TRUCKS">Use of Refrigerated Trucks</option><option value="ABSENCE OF LOCAL AGGREGATORS">Absence of Local Aggregators</option><option value="LESS PRODUCTION">Less Production</option><option value="OTHER">OTHER</option></select></div>';
					   content+='<div class="col-lg-6 col-sm-12"><input type="text" id="txtOthrCmptAdvt" name="txtOthrCmptAdvt" class="form-control" placeholder="Detail of Competitors Advantage (if OTHER)"></div></div>'; 
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div align="center"><div class="btn-group" style="margin-top:1rem;">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'CNSR_WISE_INFO\');">Save & Add More</button>';
						content+='</div></div>';
					  content+='</form>';
					  content+='</div>';
  					
  					break;
  			case 'MKTG_STRT_BULK':
  					content='<div class="form-title text-center">';
				  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Building a Marketing Strategy for Bulk & Non-Bulk Commodities</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				  content+='<form>';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div>';
				    content+='<p class="inpt-lbl">Proposed Additional traffic for the year ending March 31, 2022</p>';
				    content+='<div class="row">';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required" id="txtCmdt" list="cmdtlist" placeholder="Name of the Commodity"><datalist id="cmdtlist"></datalist>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				     /* content+='<input type="text" class="form-control" id="txtType" placeholder="BULK/ NON-BULK/ PARCEL/ GOODS/ CONTAINER">';*/
				    content+='<select id="txtType" class="form-control required"><option selected="true" disabled="disabled">TRAFFIC TYPE</option><option value="BULK">BULK</option><option value="NON-BULK">NON-BULK</option><option value="PARCEL">PARCEL</option><option value="GOODS">GOODS</option><option value="CONTAINER">CONTAINER</option></select>';
				   content+=' </div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required" id="txtOrgn" placeholder="Originating Station" list="sttnlist"><datalist id="sttnlist"></datalist>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required" id="txtDstn" placeholder="Destination Station" list="sttnlist">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
                                    content+='<input type="number"  min="0" class="form-control required" id="txtVolExpd" placeholder="Volume Expected Upto Mar 31, 2022 (in Million Tonnes)">';

				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control" id="txtCnsr required" list="custlist" placeholder="Consignor"><datalist id="custlist"></datalist>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				    content+='<input type="number" min="0"  class="form-control required" id="txtAddlRevn" placeholder="Additional Revenue Expected Upto March 31,2022">';
				    content+='</div>';
				    content+='</div>';
				    
				    content+='</div>';
				    
				    content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
				   content+='<div class="btn-group">';
                                  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'MKTG_STRT_BULK\');">Save & Add More</button>';
				content+='</div>';
				content+='</form>';
				content+='</div>';
  				break;
  			case 'AGMT_LDNG':
  				content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Action plan to augment loading and diversify commodity basket</p>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
					  content+='<form>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div></div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="text" class="form-control required" id="txtCmdt" list="cmdtlist" placeholder="Commodity"><datalist id="cmdtlist"></datalist>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    /*content+='<textarea id="txtActnPlan" class="form-control text-area-input" placeholder="Action Plan with Deadline"></textarea>';*/
					    content+='<select id="txtActnPlan" class="form-control required" name="txtActnPlan"><option selected="true" disabled="disabled">Action Plan with Deadline</option>';
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="AP")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
    
					    content+='</select></div></div>';
   					    content+='<div class="col-lg-6 col-sm-12"><div class="form-group"><input type="text" class="form-control disablecopypaste" id="txtOthrActnDtls" name="txtOthrActnDtls" placeholder="Detail of Action Plan (OTHER)" readonly>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					   /*   content+='<input type="text" class="form-control" id="txtOfcr" placeholder="Officer Assigned">';*/
					    content+='<select id="txtOfcr" class="form-control required"><option selected="true" disabled="disabled">OFFICER ASSIGNED</option><option value="ADRM">ADRM</option><option value="ARM">ARM</option><option value="Sr.DME">Sr.DME</option><option value="Sr.DCM/Coaching">Sr.DCM/Coaching</option><option value="Sr.DCM/Freight">Sr.DCM/Freight </option><option value="Sr.DFM">Sr.DFM</option><option value="Sr.DEN/C">Sr.DEN/C</option><option value="Sr.DEN/G">Sr.DEN/G</option><option value="Sr.DOM">Sr.DOM</option><option value="DOM/IC">DOM/IC</option><option value="DOM/M">DOM/M</option><option value="DOM/G">DOM/G</option><option value="DCM/Freight">DCM/Freight</option><option value="DCM/PL">DCM/PL</option>option value="DCM/G">DCM/G</option><option value="ACM/Coaching">ACM/Coaching</option><option value="ACM/ Freight">ACM/ Freight</option><option value="ACM/TC">ACM/TC</option><option value="ACM/Res">ACM/Res </option><option value="ACM/Goods">ACM/Goods</option><option value="ACM-I">ACM-I</option><option value="ACM-II">ACM-II</option><option value="AOM/Coaching">AOM/Coaching</option><option value="AOM/M">AOM/M</option><option value="AOM/C">AOM/C</option><option value="AOM/Goods">AOM/Goods</option><option value="Other">Other</option></select>';;
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control required" id="txtVolTrgt" placeholder="Volume Targeted (in Million Tonnes)">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="number" min="0"  class="form-control required" id="txtRevnExpd" placeholder="Revenue Targeted">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					     content+='<input type="number" min="0"  class="form-control required" id="txtCost" placeholder="Cost Involved">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="text" class="form-control" id="txtTranRqmt" placeholder="Transportation Requirement (Type of Rakes needed)" list="rakelist"><datalist id="rakelist"></datalist>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-12 col-sm-12">';
					    content+='<div class="form-group">';
					    /*content+='<textarea class="form-control text-area-input" id="txtHandHoldRB" placeholder="Handholding needed from Railway Board"></textarea>';*/
					    content+='<p class="rb-handhold" style="text-align:left;">Proposal for Handholding needed from Railway Board</p> <span id="spanFileName" style="font-size:13px;font-weight:600;" class="pull-right"></span>';
					    content+='<table class="table rb-handhold pull-left table-striped"><tr><td><select id="selHandHoldNeed" class="form-control"><option value="YES">Handholding required from Railway Board</option><option value="NO">No Handholding is required</option></select></td><td class="handholding-inputs"><div class="form-check-inline"><label class="form-check-label" style="font-weight:600;font-size:13px;"><input type="radio" class="form-check-input opt-handholding" id="optPropYes" name="optradio" checked onclick="showLinkProp();">Proposal Already Uploaded</label></div><div class="form-check-inline" style="font-weight:600;font-size:13px;"><label class="form-check-label"><input type="radio" id="optPropNow" class="form-check-input opt-handholding" name="optradio" onclick="openUploadProp();">Upload New Proposal</label></div></td><td>&nbsp;</td></tr>';
	   				    content+='<tr id="trPropDtls" class="handholding-inputs"><td><input type="date" name="txtPropDate" id="txtPropDate" />&nbsp;<button class="btn btn-primary btn-sm" onclick="event.preventDefault();fetchDateWiseProposals();">Fetch Proposals ></button><td><select id="selPropList" name="selPropList" class="form-control"></select></td><td>&nbsp;</td></tr>';
					    content+='<tr id="trPropUpload"><td colspan="3"><input type="hidden" id="txtPropId" name="txtPropId" value="DUMMY" /><iframe  class="handholding-inputs" src="/RailSAHAY/pages/IRBDU_Proposal1.jsp" style="width:100%;height:220px;border:0px;"></iframe></td></tr>';
					    content+='</table>';
					    content+='</div>';
					    content+='</div>';
					    
					    content+='</div>';
					    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					  content+='<div class="btn-group">';
					content+='<button type="button" class="btn btn-success text-white mt-2 btn-save" onclick="saveRecord(\'AGMT_LDNG\');">Save & Add More</button>';
					content+='</div>';
					content+='</form>';
					content+='</div>';
  					break;
  			case 'AGMT_LDNG1':
  				content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Action plan to augment loading and diversify commodity basket</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
					  content+='<form>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-2 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div></div>';
					    content+='<div class="col-lg-4 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="text" class="form-control required" id="txtCmdt" list="cmdtlist" placeholder="Commodity"><datalist id="cmdtlist"></datalist>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="text" class="form-control required" id="txtCnsr" placeholder="Consignor/Industry Targeted">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<select id="selTrfcType" class="form-control required"><option selected="true" disabled="disabled">Type of Traffic</option><option value="GOODS">GOODS</option><option value="PARCEL">PARCEL</option><option value="CONTAINER">CONTAINER</option></select>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="text" id="txtStckType" list="rakelist" class="form-control required" placeholder="Type of Stock"><datalist id="rakelist"></datalist>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="selActnPlan" class="form-control required" multiple name="selActnPlan[]">';
					    for(var i=0;i<GG_LOV.length;i++)
					    {
						if(GG_LOV[i][0]=="AP")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
    
					    content+='</select></div></div>';
   					    content+='<div class="col-lg-6 col-sm-12"><div class="form-group"><input type="text" class="form-control" id="txtActnPlanOthr" name="txtActnPlanOthr" placeholder="Detail of Action Plan (OTHER)">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<select id="selOfcr" class="form-control required" name="selOfcr[]" multiple><option value="ADRM">ADRM</option><option value="ARM">ARM</option><option value="Sr.DME">Sr.DME</option><option value="Sr.DCM/Coaching">Sr.DCM/Coaching</option><option value="Sr.DCM/Freight">Sr.DCM/Freight </option><option value="Sr.DFM">Sr.DFM</option><option value="Sr.DEN/C">Sr.DEN/C</option><option value="Sr.DEN/G">Sr.DEN/G</option><option value="Sr.DOM">Sr.DOM</option><option value="DOM/IC">DOM/IC</option><option value="DOM/M">DOM/M</option><option value="DOM/G">DOM/G</option><option value="DCM/Freight">DCM/Freight</option><option value="DCM/PL">DCM/PL</option>option value="DCM/G">DCM/G</option><option value="ACM/Coaching">ACM/Coaching</option><option value="ACM/ Freight">ACM/ Freight</option><option value="ACM/TC">ACM/TC</option><option value="ACM/Res">ACM/Res </option><option value="ACM/Goods">ACM/Goods</option><option value="ACM-I">ACM-I</option><option value="ACM-II">ACM-II</option><option value="AOM/Coaching">AOM/Coaching</option><option value="AOM/M">AOM/M</option><option value="AOM/C">AOM/C</option><option value="AOM/Goods">AOM/Goods</option><option value="Other">Other</option></select>';;
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					     content+='<input type="number" min="0"  class="form-control required" id="txtCost" placeholder="Cost Involved (Cr)">';
					    content+='</div></div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					      content+='<input type="number" min="0"  class="form-control required" id="txtVolTrgt" placeholder="Volume Targeted (in Million Tonnes)">';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='<input type="number" min="0"  class="form-control required" id="txtRevnExpd" placeholder="Revenue Targeted (Cr)">';
					    content+='</div>';
					    content+='</div>';
					    content+='</div>';
					    content+='<div class="col-lg-12 col-sm-12">';
					    content+='<div class="form-group">';
					    /*content+='<textarea class="form-control text-area-input" id="txtHandHoldRB" placeholder="Handholding needed from Railway Board"></textarea>';*/
					    content+='<p class="rb-handhold" style="text-align:left;">Proposal for Handholding needed from Railway Board</p> <span id="spanFileName" style="font-size:13px;font-weight:600;" class="pull-right"></span>';
					    content+='<table class="table rb-handhold pull-left table-striped"><tr><td><select id="selHandHoldNeed" class="form-control"><option value="YES">Handholding required from Railway Board</option><option value="NO">No Handholding is required</option></select></td><td class="handholding-inputs"><div class="form-check-inline"><label class="form-check-label" style="font-weight:600;font-size:13px;"><input type="radio" class="form-check-input opt-handholding" id="optPropYes" name="optradio" checked onclick="showLinkProp();">Proposal Already Uploaded</label></div><div class="form-check-inline" style="font-weight:600;font-size:13px;"><label class="form-check-label"><input type="radio" id="optPropNow" class="form-check-input opt-handholding" name="optradio" onclick="openUploadProp();">Upload New Proposal</label></div></td><td>&nbsp;</td></tr>';
	   				    content+='<tr id="trPropDtls" class="handholding-inputs"><td colspan="3"><label for="selPropList" style="font-weight:600;font-size:12px;color:#4d4d4d;">Select the Uploaded Proposal</label><select id="selPropList" name="selPropList" class="form-control"></select></td></tr>';
					    content+='<tr id="trPropUpload"><td colspan="3"><input type="hidden" id="txtPropId" name="txtPropId" value="DUMMY" /><iframe  class="handholding-inputs" src="/RailSAHAY/pages/IRBDU_Proposal1.jsp" style="width:100%;height:220px;border:0px;"></iframe></td></tr>';
					    content+='</table>';
					    content+='</div>';
					    content+='</div>';
					    
					    content+='</div>';
					    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					  content+='<div class="btn-group">';
					content+='<button type="button" class="btn btn-success text-white mt-2 btn-save" onclick="saveRecord(\'AGMT_LDNG1\');">Save & Add More</button>';
					content+='</div>';
					content+='</form>';
					content+='</div>';
  					break;
  			case 'FF_CRNT':
  				content='<div class="form-title text-center">';
				  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight Forwarders, Aggregators and Logistics Partners (Current)</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				  content+='<form>';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div>';
				    content+='<div class="row">';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required disablecopypaste" id="txtFFName" placeholder="Name of Freight Forwarder/Aggregator/Logistics Partner">';
				   content+=' </div>';
				    content+='</div>';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required disablecopypaste" id="txtFFContDtls" placeholder="Contact Detail">';
				   content+=' </div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="number" min="0"  class="form-control required" id="txtTrfcVol" placeholder="Volume of Traffic (in Million Tonnes)">';
				   content+=' </div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="number" min="0"  class="form-control required" id="txtRevn" placeholder="Revenue (Rs Cr)">';
				   content+=' </div>';
				   content+=' </div>';
				   content+=' <div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required disablecopypaste" id="txtOD" placeholder="Originating to Destination">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				    /*  content+='<input type="text" class="form-control required" id="txtCmdt" name="txtCmdt" list="cmdtlist" placeholder="Commodity"><datalist id="cmdtlist"></datalist>';*/
				    content+='<select id="selCmdtFF" name="selCmdtFF[]" multiple class="form-control required">';
					for(var i=0;i<aNumCmdt.length;i++)
					    {
						var numcode=aNumCmdt[i].substring(0,aNumCmdt[i].indexOf('-'));
						var cmdtname=aNumCmdt[i].substring(aNumCmdt[i].indexOf('-')+1);
						content+='<option value="'+numcode+'">'+cmdtname+'</option>';
					    }
				    content+='</select>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<label for="txtDeplDate" style="font-weight:600;font-size:13px;">When Deployed:</label><input type="date" class="form-control" id="txtDeplDate" name="txtDeplDate" >';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				   content+=' <div class="form-group">';
				     content+=' <textarea class="form-control text-area-input" id="txtRmrk" onpaste="return false;" placeholder="Remarks/Any Other Assessment"></textarea>';
				    content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>';
				    content+='</div>';
				    content+='</div>';
				    
				    content+='</div>';
				    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
				    content+='<div class="btn-group">';
					  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'FF_CRNT\');">Save & Add More</button>';
					content+='</div>';
				  content+='</form>';
				  content+='</div>';
  			break;
  			
  		case 'FF_PROPOSED':
  			content='<div class="form-title text-center">';
				  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight Forwarders, Aggregators and Logistics Partners (Proposed)</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				  content+='<form>';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div>';
				    content+='<div class="row">';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required disablecopypaste" id="txtFFName" placeholder="Name of Freight Forwarder/Aggregator/Logistics Partner">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required disablecopypaste" id="txtFFContDtls" placeholder="Contact Detail">';
				   content+=' </div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="number" min="0"  class="form-control required" id="txtTrfcVol" placeholder="Volume of Traffic Proposed (in Million Tonnes)">';
				    content+='</div>';
				   content+=' </div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				     content+=' <input type="number" min="0"  class="form-control required" id="txtRevn" placeholder="Revenue (Rs Cr)">';
				    content+='</div>';
				    content+='</div>';
				   content+=' <div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				    content+='<input type="text" class="form-control required" onpaste="return false;" id="txtOD" placeholder="Originating to Destination">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-3 col-sm-12">';
				    content+='<div class="form-group">';
				    /*content+='<input type="text" class="form-control required" id="txtCmdt" name="txtCmdt" list="cmdtlist" placeholder="Commodity"><datalist id="cmdtlist"></datalist>';*/
				   content+='<select id="selCmdtFF" name="selCmdtFF[]" multiple class="form-control required">';
					for(var i=0;i<aNumCmdt.length;i++)
					    {
						var numcode=aNumCmdt[i].substring(0,aNumCmdt[i].indexOf('-'));
						var cmdtname=aNumCmdt[i].substring(aNumCmdt[i].indexOf('-')+1);
						content+='<option value="'+numcode+'">'+cmdtname+'</option>';
					    }
				    content+='</select>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				    content+='<select id="txtPrgsStts" class="form-control"><option selected="true" disabled="disabled">Status of Progress</option><option value="RECEIVED">Received</option><option value="INITIATED">Initiated</option><option value="UNDER APPROVAL">Under Approval</option></select>';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-6 col-sm-12">';
				    content+='<div class="form-group">';
				    content+='<textarea class="form-control text-area-input" id="txtRmrk" onpaste="return false;" placeholder="Remarks/Any Other Assessment"></textarea>';
				    content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>';
				    content+='</div>';
				    content+='</div>';			    
				    content+='</div>';
				    content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
				    content+='<div class="btn-group">';
                                    content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'FF_PROPOSED\');">Save & Add More</button>';
                                    content+='</div>';
                                    content+='</form>';
                                    content+='</div>';
                                    
  				break;
  
  		case 'INIT_TRFC_BOOST':
  			content='<div class="form-title text-center">';
				  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Cross learning from Zones: Initiatives taken to boost traffic in year ending March 31, 2021 and to date</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm">';
				  content+='<form>';
				    content+='<div class="row">';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div></div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="text" class="form-control required" id="txtCmdt" placeholder="Commodity" list="cmdtlist"><datalist id="cmdtlist"></datalist>';
				   content+=' </div>';
				   content+=' </div></div>';
				    content+='<div class="row">';
				    content+='<div class="col-lg-12 col-sm-12">';
				    content+='<div class="form-group">';
				     content+=' <textarea class="form-control text-area-input-50 required" onpaste="return false;" id="txtInitTaken" placeholder="List Initatives Taken"></textarea>';
				     content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 50 Words</span>';
				    content+='</div>';
				   content+=' </div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				   content+=' <div class="form-group">';
				    content+='  <input type="number" min="0"  class="form-control required" id="txtLdng" placeholder="Increase in Traffic (in Million Tonnes)">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				      content+='<input type="number" min="0"  class="form-control required" id="txtRevn" placeholder="Additional Revenue (Cr)">';
				    content+='</div>';
				    content+='</div>';
				    content+='<div class="col-lg-4 col-sm-12">';
				    content+='<div class="form-group">';
				    content+='<textarea class="form-control text-area-input" id="txtRmrk" onpaste="return false;" placeholder="Remark/Any Other Assessment"></textarea>';
				    content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>';
				    content+='</div>';
				    content+='</div>';
				    
				   content+=' </div>';
				    
				    content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
				    content+='<div class="btn-group">';
				    content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'INIT_TRFC_BOOST\');">Save & Add More</button>';
					content+='</div>';
				  content+='</form>';
				  content+='</div>';
  				break;
  			case 'SWOT_ANLY':
  				content='<div class="form-title text-center">';
				content+=' <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; SWOT Analysis</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				content+='<form>';
					    content+='<div class="form-group">';
					    content+=' <div class="row">';
					    content+='<div class="col-lg-6 col-sm-12">';
					    content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="col-lg-6 col-sm-12">';
				    	    content+='<div class="form-group">';
				      	    content+='<input type="text" class="form-control" id="txtCmdt" placeholder="Commodity (Leave Blank, in case of All Commodities SWOT)" list="cmdtlist"><datalist id="cmdtlist"></datalist>';
				   	    content+=' </div>';
					    content+='</div>';

					    content+='</div>';
					   
					    content+=' <div class="row">';
					   content+=' <div class="col-lg-5 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<select id="selStrg" class="form-control required" name="selStrg[]" multiple>';
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="S")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-3 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<textarea class="form-control text-area-input dtls-box" id="txtStrg" placeholder="Other Strength, if any" onkeydown="verifySWOTInpt(event,this,\'S\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<textarea class="form-control text-area-input-100 required dtls-box" id="txtStrgDtls" placeholder="Brief Description of Strengths" onkeydown="verifySWOTInptDtls(event,this,\'SD\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="SD_word_left">Maximum 100 Words</span>';
					   content+=' </div>';
					    content+='</div>';

					   content+=' <div class="col-lg-5 col-sm-12">';
					   content+=' <div class="form-group">';					   
					   content+='<select id="selWeak" name="selWeak[]" class="form-control required" multiple>';
					    for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="W")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					    content+='</div>';
					    content+='</div>';

					   content+=' <div class="col-lg-3 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='   <textarea class="form-control text-area-input dtls-box" id="txtWeak" placeholder="Other Weakness, if any" onkeydown="verifySWOTInpt(event,this,\'W\');"></textarea>';
						content+='<span class="pull-right badge badge-danger word-left" id="W_word_left">Maximum 30 Words</span>';
					    content+='</div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<textarea class="form-control text-area-input-100 required dtls-box" id="txtWeakDtls" placeholder="Brief Description of Weaknesses" onkeydown="verifySWOTInptDtls(event,this,\'WD\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="WD_word_left">Maximum 100 Words</span>';
					   content+=' </div>';
					    content+='</div>';

					   content+=' <div class="col-lg-5 col-sm-12">';
					   content+=' <div class="form-group">';					   
					   content+='<select id="selOprt" name="selOprt[]" class="form-control required" multiple>';
					    for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="O")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					   content+=' </div>';
					   content+=' </div>';
					   content+=' <div class="col-lg-3 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='   <textarea class="form-control text-area-input dtls-box" id="txtOprt" placeholder="Other Opportunity, if any" onkeydown="verifySWOTInpt(event,this,\'O\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="O_word_left">Maximum 30 Words</span>';
					   content+=' </div>';
					   content+=' </div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<textarea class="form-control text-area-input-100 required dtls-box" id="txtOprtDtls" placeholder="Brief Description of Opportunities" onkeydown="verifySWOTInptDtls(event,this,\'OD\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="OD_word_left">Maximum 100 Words</span>';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-5 col-sm-12">';
					    content+='<div class="form-group">';					   
					   content+='<select id="selThrt" name="selThrt[]" class="form-control required" multiple>';
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="T")
							content+='<option value="'+GG_LOV[i][1]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					    content+='</div>';
					    content+='</div>';

					   content+=' <div class="col-lg-3 col-sm-12">';
					    content+='<div class="form-group">';
					    content+='  <textarea class="form-control text-area-input dtls-box" id="txtThrt" placeholder="Other Threat, if any" onkeydown="verifySWOTInpt(event,this,\'T\');"></textarea>';
					    content+='<span class="pull-right badge badge-danger word-left" id="T_word_left">Maximum 30 Words</span>';
					    content+='</div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<textarea class="form-control text-area-input-100 required dtls-box" id="txtThrtDtls" placeholder="Brief Description of Threats" onkeydown="verifySWOTInptDtls(event,this,\'TD\');"></textarea>';
					   content+='<span class="pull-right badge badge-danger word-left" id="TD_word_left">Maximum 100 Words</span>';
					   content+=' </div>';
					    content+='</div>';
					    
					    content+='</div>';
					    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'SWOT_ANLY\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					  content+='</div>';
  				break;
  			case 'INDS_OUTREACH':
  				content='<div class="form-title text-center">';
				content+=' <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Industry Outreach</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				content+='<form>';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div>';
					   content+=' <div class="row">';
					    content+='<div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='   <select id="selIndsType" class="form-control required"><option value="EXISTING TRAFFIC">EXISTING TRAFFIC</option><option value="NEW TRAFFIC">NEW TRAFFIC</option></select>';
					   content+=' </div>';
					   content+=' </div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="text" id="txtInds" name="txtInds" class="form-control required disablecopypaste" placeholder="Name of Targeted Industry" />';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="text" id="txtCmdt" name="txtCmdt" list="cmdtlist" class="form-control required" placeholder="Commodity" /><datalist id="cmdtlist"></datalist>';
					   content+=' </div>';
					    content+='</div></div>';
					   content+='<p style="font-weight:600;color:#000;font-size:14px;text-align:left;">Detail of Interaction Held</p>';
					   content+=' <div class="row" style="background-color: #ffffff;background-image: linear-gradient(315deg, #ffffff 0%, #d7e1ec 74%);border:1px solid #e6e6e6;padding:10px;">';
					   content+='<div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="text" id="txtIndsPers" name="txtIndsPers" class="form-control required" placeholder="Name of Representative" />';
					   content+=' </div>';
					   content+=' </div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="text" id="txtDesg" name="txtDesg" class="form-control required" placeholder="Designation" />';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-4 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="date" id="txtContDate" name="txtContDate" class="form-control required"/>';
					   content+=' </div>';
					    content+='</div></div><br/>';
					   content+=' <div class="row">';
					   content+=' <div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="number" min="0"  id="txtAddlLdng" name="txtAddlLdng" class="form-control required" placeholder="Additional Loading Expected in Year(in Million Tonnes)" />';
					   content+=' </div>';
					    content+='</div>';
					   content+=' <div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<input type="number" min="0"  id="txtAddlRevn" name="txtAddlRevn" class="form-control required" placeholder="Additional Revenue Expected (In Cr.)" />';
					   content+=' </div>';
					    content+='</div>';
					    content+='</div>';
					    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'INDS_OUTREACH\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					  content+='</div>';
  				break;
  			case 'RAIL_COEF_BOOST':
  				content='<div class="form-title text-center">';
				content+=' <p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Target Areas for Rail Co-efficient Boost and Gati-Shakti</p><span onclick="infoHelpMesg(\''+inpt_ctgr+'\');" class="pull-left help-icon"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;How to Feed !</span>';
				content+='</div>';
				content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
				content+='<form>';
				    content+='<div class="form-group">';
				      content+='<select id="selDvsn" class="dvsn-list"></select>';
				    content+='</div>';
					   content+=' <div class="row">';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<select id="selImmed" name="selImmed[]" multiple class="form-control required" style="border-left:5px solid #339933;">';						
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="IM")
							content+='<option value="'+GG_LOV[i][2]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					   content+=' </div>';
					   content+=' </div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+=' <input type="text" id="txtImmed" name="txtImmed" class="form-control disablecopypaste" placeholder="Detail of OTHER Area for Immediate Growth, If any" style="border-left:5px solid #339933;">';
					   content+=' </div>';
					   content+=' </div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<select id="selMed" name="selMed" name="selMed[]" class="form-control required" multiple style="border-left:5px solid #ffcc00;">';						
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="MD")
							content+='<option value="'+GG_LOV[i][2]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					   content+=' </div>';
					   content+=' </div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+=' <input type="text" id="txtMed" name="txtMed" class="form-control disablecopypaste" placeholder="Detail of OTHER Area for Medium Term Growth, If any" style="border-left:5px solid #ffcc00;">';
					   content+=' </div>';
					   content+=' </div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+='<select id="selLong" name="selLong" name="selLong[]" class="form-control required" multiple  style="border-left:5px solid #cc0000;">';						
					   for(var i=0;i<GG_LOV.length;i++)
					   {
						if(GG_LOV[i][0]=="LT")
							content+='<option value="'+GG_LOV[i][2]+'">'+GG_LOV[i][2]+'</option>';
					    }
					    content+='</select>';
					   content+=' </div>';
					   content+=' </div>';
					    content+='<div class="col-lg-6 col-sm-12">';
					   content+=' <div class="form-group">';
					   content+=' <input type="text" id="txtLong" name="txtLong" class="form-control disablecopypaste" placeholder="Detail of OTHER Area for Long Term Growth, If any" style="border-left:5px solid #cc0000;">';
					   content+=' </div>';
					   content+=' </div>';
					    content+='</div>';
					    content+='<p style="font-weight:600;color:#000;font-size:14px;text-align:left;">Upload Traffic Plan in Prescribed Format (<a href="/RailSAHAY/resources/pdf/TrafficPlanFormat.xlsx" target="trfcplanfrmt" style="color:#b32400 !important;"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;Prescribed Format</a>)</p>';
					    content+='<p id="planFileName" style="font-weight:600;color:#000;font-size:13px;text-align:left;"></p><p id="planMesg" style="text-align:left;"></p>';
				            content+='<input type="hidden" id="txtTrfcPlanId" name="txtTrfcPlanId" />';
				    	    content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<iframe src="/RailSAHAY/pages/IRBDU_TrfcPlanUpload.jsp?CallFlag=NEW" style="width:100%;height:100px;border:0px;overflow:hidden;" id="iFrmTrfcPlan"></iframe>';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'RAIL_COEF_BOOST\');">Save</button>';
						content+='</div>';
					  content+='</form>';
					  content+='</div>';
  				break;

  			case 'OTHR_ASPECT':
  				content='<div class="form-title text-center">';
					  content+='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Any other aspect(s), divisions wish to highlight</p>';
					content+='</div>';
					content+='<div class="d-flex flex-column text-center" id="divInptForm"> ';
					  content+='<form>';
					    content+='<div class="form-group">';
					      content+='<select id="selDvsn" class="dvsn-list"></select>';
					    content+='</div>';
					    content+='<div class="row">';
					    content+='<div class="col-lg-12 col-sm-12">';
					    content+='<div class="form-group">';
					     content+=' <textarea class="form-control text-area-input" id="txtAspect" placeholder="Aspects to be highlighted"></textarea>';
					    content+='<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 50 Words</span>';
					    content+='</div>';
					    content+='</div>';
					    content+='</div>';
					    
				    	content+='<input type="hidden" id="txtRowId" name="txtRowId" />';
					    content+='<div class="btn-group">';
						  content+='<button type="button" class="btn btn-success text-white btn-save" onclick="saveRecord(\'OTHR_ASPECT\');">Save & Add More</button>';
						content+='</div>';
					  content+='</form>';
					  content+='</div>';
					 break;
		}
		$("#divAddRecord").html(content);
		$("#loginModal").modal('show');
		$("#imgFormula").hide();
		$('#loginModal').on('hidden.bs.modal', function () {		    
		    $(".table-input>tr").removeClass("selected-row");
		    $("tr").removeClass("selected-row");
		});

                $('#selDvsn').find('option').remove().end();
		if(GG_LognLocnFlag=="D")
		{
			for(var i=0;i<aDvsn.length;i++)
			{
				var crntval=aDvsn[i];
				if(crntval.substring(0,crntval.indexOf('-'))==GG_CrntLocn)
				{
					var dvsnstr=aDvsn[i];
					var dvsn=dvsnstr.substring(0,dvsnstr.indexOf('-'));
                                	$('#selDvsn').append($('<option>', {
                                    	value: dvsn,
                                    	text: dvsnstr
                                }));
				}
			}	
		}
		if(GG_LognLocnFlag=="Z")
		{
			$('#selDvsn').append($('<option>', {
                                    value: "",
                                    text: "ZONE: "+GG_CrntZone 
                                }));
			for(var i=0;i<aZoneDvsn.length;i++)
			{
				var crntval=aZoneDvsn[i];
				if(crntval.substring(0,crntval.indexOf('-'))==GG_CrntZone)
				{
					var dvsnstr=crntval.substring(crntval.indexOf('-')+1);
					var dvsn=dvsnstr.substring(0,dvsnstr.indexOf('-'));
                                	$('#selDvsn').append($('<option>', {
                                	    value: dvsn,
                                	    text: dvsnstr
                               	 }));
				}
			}	
		}

		if(inpt_ctgr=="SWOT_ANLY")
		{
			  $('.dtls-box').on('keypress keydown',function(event){
				if((event.keyCode == 8) || (event.keyCode == 46)|| (event.keyCode == 37)|| (event.keyCode == 39))
					return true;

				var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
				var expr = new RegExp("^[a-zA-Z0-9._+\\-\\,\b\t ]*$");

			        if (expr.test(key)) {
			            return true;
			        }
			        else
        			{
				        return false;
			        }
		         });
			 $('.dtls-box').on('change',function(event){
				var crntval=$(this).val();
				$(this).val(crntval.toString().replace(/[^a-zA-Z 0-9\n\r+\\-\\._]+/g, ''));
		         });
		}
		
 		var cmdtstr='';
   		for (var i=0; i < aRakeCmdt.length;++i)
		{
        		cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for Rake Commodity
   		}
   		$("#cmdtlist").html(cmdtstr);

 		var custstr='';
   		for (var i=0; i < gCust.length;++i)
		{
        		custstr+= '<option value="'+gCust[i]+'" />'; // Helplist for Global Customers
   		}
   		$("#custlist").html(custstr);


 		var raketypestr='';
   		for (var i=0; i < aRakeType.length;++i)
		{
        		raketypestr+= '<option value="'+aRakeType[i]+'" />'; // Helplist for Grup Rake Type
   		}
   		$("#rakelist").html(raketypestr);
		$("#stcktypelist").html(raketypestr);


 		var sttnstr='';
   		for (var i=0; i < aSttn.length;++i)
		{
        		sttnstr+= '<option value="'+aSttn[i]+'" />'; // Helplist for Stations
   		}
   		$("#sttnlist").html(sttnstr);

 		var dvsnstr='';
   		for (var i=0; i < aDvsn.length;++i)
		{
        		dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
   		}
 		var portstr='';
   		for (var i=0; i < portLocn.length;++i)
		{
			var crntport=(portLocn[i].substring(0,portLocn[i].indexOf("-"))).trim();
        		portstr+= '<option value="'+portLocn[i]+'" />'; // Helplist for Port Locations
   		}
		$("#porttrmllist").html(dvsnstr);
		
		$("#selNumCmdt").change(function() 
		{		
			var cmdtname=$("#selNumCmdt option:selected").text();	
			var numcode=$("#selNumCmdt").val();
			fetchCmdtRailCost(numcode,cmdtname);
		});
		$("#selHandHoldNeed").change(function() {
			var crntval=$(this).val();
			if(crntval=="YES")
			{
				$(".opt-handholding").attr("readonly","readonly");
				$(".handholding-inputs").show();
			}
			if(crntval=="NO")
			{
				$(".opt-handholding").removeAttr("readonly");
				$(".handholding-inputs").hide();
			}
		});
		$("#txtIndsFrgtGnrt").change(function() {
			var crntval=$(this).val();
			if(crntval=="OTHER")
			{
				$("#txtIndsFrgtGnrtOthr").removeAttr("readonly");
				$("#txtIndsFrgtGnrtOthr").val("");
				$("#txtIndsFrgtGnrtOthr").focus();
			}
			else
			{
				$("#txtIndsFrgtGnrtOthr").attr("readonly","readonly");
				$("#txtIndsFrgtGnrtOthr").val("");
			}
		});
		$("#txtActnPlan").change(function() {
			var crntval=$("#txtActnPlan option:selected" ).text();
			if(crntval=="OTHER")
			{
				$("#txtOthrActnDtls").removeAttr("readonly");
				$("#txtOthrActnDtls").val("");
				$("#txtOthrActnDtls").focus();
			}
			else
			{
				$("#txtOthrActnDtls").attr("readonly","readonly");
				$("#txtOthrActnDtls").val("");
			}
		});
		/*
		$("#selActnPlan").change(function() {
			var crntval=$("#selActnPlan option:selected" ).text();
			if(crntval=="OTHER")
			{
				$("#txtActnPlanOthr").removeAttr("readonly");
				$("#txtActnPlanOthr").val("");
				$("#txtActnPlanOthr").focus();
			}
			else
			{
				$("#txtActnPlanOthr").attr("readonly","readonly");
				$("#txtActnPlanOthr").val("");
			}
		});
		*/
		/*
		$("#selImmed").change(function() {
			var crntval=$(this).val();
			var crnttext=$("#selImmed option:selected" ).text();
			if((crntval=="OTHER") || (crnttext=="OTHER"))
			{
				$("#txtImmed").removeAttr("readonly");
				$("#txtImmed").val("");
				$("#txtImmed").focus();
			}
			else
			{
				$("#txtImmed").attr("readonly","readonly");
				$("#txtImmed").val("");
			}
		});
		$("#selMed").change(function() {
			var crntval=$(this).val();
			var crnttext=$("#selMed option:selected" ).text();
			if((crntval=="OTHER") || (crnttext=="OTHER"))
			{
				$("#txtMed").removeAttr("readonly");
				$("#txtMed").val("");
				$("#txtMed").focus();
			}
			else
			{
				$("#txtMed").attr("readonly","readonly");
				$("#txtMed").val("");
			}
		});
		$("#selLong").change(function() {
			var crntval=$(this).val();
			var crnttext=$("#selLong option:selected" ).text();
			if((crntval=="OTHER") || (crnttext=="OTHER"))
			{
				$("#txtLong").removeAttr("readonly");
				$("#txtLong").val("");
				$("#txtLong").focus();
			}
			else
			{
				$("#txtLong").attr("readonly","readonly");
				$("#txtLong").val("");
			}
		});
		*/
		$("#divPortTrml").html('');

		if(inpt_ctgr=="RAIL_COEF_BOOST")
		{
			$("#planFileName").html("");
			$("#planMesg").html("");
			$("#iFrmTrfcPlan").show();
			$("#iFrmTrfcPlan").css("display","block");
		}
		if(inpt_ctgr=="CMDT_RAIL_COST")
		{
			$("#selDvsn").change(function() {
			$("#divPortTrml").html('');
			var crntval=$("#selLocnType").val();
			if(crntval=="D")
			{
				$("#porttrmllist").html(dvsnstr);
			}
			var locnarr;
			var locn=$("#selDvsn").val();
			var datalocn='';
			var cntl;
			if(locn=='')
				datalocn=GG_CrntLocn;
			else
				datalocn=locn;

			if(crntval=="P")
			{				
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');


				if(GG_LognLocnFlag=="D")
					locnarr=aPortDvsn;
				if((GG_LognLocnFlag=="Z") && (locn!=""))
					locnarr=aPortDvsn;
				if((GG_LognLocnFlag=="Z") && (locn==""))
					locnarr=aPortZone;
				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
				jQuery('#selPortTrml').multiselect({
			    	columns: 1,
			    	placeholder: 'SELECT LOCATION',
				});

			}
			if(crntval=="T")
			{			
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');

				if(GG_LognLocnFlag=="D")
					locnarr=aSttnDvsn;
				if((GG_LognLocnFlag=="Z") && (locn!=""))
					locnarr=aSttnDvsn;
				if((GG_LognLocnFlag=="Z") && (locn==""))
					locnarr=aSttnZone;

				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
			jQuery('#selPortTrml').multiselect({
		    	columns: 1,
		    	placeholder: 'SELECT LOCATION',
			});
			}
		});

		}
		$("#selLocnType").change(function() {
			$("#divPortTrml").html('');
			var crntval=$(this).val();
			if(crntval=="D")
			{
				$("#porttrmllist").html(dvsnstr);
			}
			var locnarr;
			var locn=$("#selDvsn").val();
			var datalocn='';
			var cntl;
			if(locn=='')
				datalocn=GG_CrntLocn;
			else
				datalocn=locn;

			if(crntval=="P")
			{				
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');


				if(GG_LognLocnFlag=="D")
					locnarr=aPortDvsn;
				if((GG_LognLocnFlag=="Z") && (locn!=""))
					locnarr=aPortDvsn;
				if((GG_LognLocnFlag=="Z") && (locn==""))
					locnarr=aPortZone;
				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
				jQuery('#selPortTrml').multiselect({
			    	columns: 1,
			    	placeholder: 'SELECT LOCATION',
				});

			}
			if(crntval=="T")
			{			
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');

				if(GG_LognLocnFlag=="D")
					locnarr=aSttnDvsn;
				if((GG_LognLocnFlag=="Z") && (locn!=""))
					locnarr=aSttnDvsn;
				if((GG_LognLocnFlag=="Z") && (locn==""))
					locnarr=aSttnZone;

				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
			jQuery('#selPortTrml').multiselect({
		    	columns: 1,
		    	placeholder: 'SELECT LOCATION',
			});
			}
		});
		jQuery('#selStrg').multiselect({
		    columns: 1,
		    placeholder: 'SELECT STRENGTHS',
		});
		jQuery('#selWeak').multiselect({
		    columns: 1,
		    placeholder: 'SELECT WEAKNESSES',
		});
		jQuery('#selOprt').multiselect({
		    columns: 1,
		    placeholder: 'SELECT OPPORTUNITIES',
		});
		jQuery('#selThrt').multiselect({
		    columns: 1,
		    placeholder: 'SELECT THREATS',
		});
		jQuery('#selOfcr').multiselect({
		    columns: 1,
		    placeholder: 'OFFICERS NOMINATED',
		});
		jQuery('#selImmed').multiselect({
		    columns: 1,
		    placeholder: 'TARGET AREAS FOR IMMEDIATE GROWTH',
		});
		jQuery('#selMed').multiselect({
		    columns: 1,
		    placeholder: 'TARGET AREAS FOR MEDIUM TERM GROWTH',
		});
		jQuery('#selLong').multiselect({
		    columns: 1,
		    placeholder: 'TARGET AREAS FOR LONG-TERM GROWTH',
		});
		jQuery('#selCmptAdvt').multiselect({
		    columns: 1,
		    placeholder: 'REASON FOR COMPETITOR ADVANTAGE',
		});
		jQuery('#selActnPlan').multiselect({
		    columns: 1,
		    placeholder: 'ACTION PLAN WITH DEADLINE',
		});
		jQuery('#selCmdtFF').multiselect({
		    columns: 1,
		    placeholder: 'SELECT COMMODITY',
		});
		jQuery('#selStckType1').multiselect({
		    columns: 1,
		    placeholder: 'STOCK TYPE',
		});
		$(".text-area-input").keydown(function(evt) {
		    var crntval=$(this).val();
		    var words = crntval.length ? crntval.match(/\S+/g).length : 0;
    		    if (words >= 30) {
			if (evt.which !== 8) evt.preventDefault();
		    }
		});
		$(".text-area-input-50").keydown(function(evt) {
		    var crntval=$(this).val();
		    var words = crntval.length ? crntval.match(/\S+/g).length : 0;
    		    if (words >= 50) {
			if (evt.which !== 8) evt.preventDefault();
		    }
		});
		$(".text-area-input-100").keydown(function(evt) {
		    var crntval=$(this).val();
		    var words = crntval.length ? crntval.match(/\S+/g).length : 0;
    		    if (words >= 100) {
			if (evt.which !== 8) evt.preventDefault();
		    }
		});
	 	$('input.disablecopypaste').bind('copy paste', function (e) {
	       e.preventDefault();
	    });
		if(inpt_ctgr=="AGMT_LDNG1")
			fetchDateWiseProposals();
		$('[data-toggle="popover"]').popover();
		showLinkProp();

}
function openUploadProp()
{
	$("#trPropDtls").hide();
	$("#trPropUpload").show();
}
function showLinkProp()
{
	$("#trPropUpload").hide();
	$("#trPropDtls").show();
}
function fetchBDPlanData(zone, date, ctgr) 
{
    var myurl="/RailSAHAY/AcqnPlan"
    $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'FETCH_PLAN_DATA',Zone:zone,Date:date,Ctgr:ctgr},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            var erormesg=obj.ErorMesg;
            if(stts=="S")
            {
               trxnSuccess("Saved Successfully !"); 
               fetchData(inpt_ctgr,GG_CrntZone);               
            }
            else
            {
                trxnFailed("Failed to Save! "+obj.ErorMesg);
            }
        }
    });   
}
function saveRecord(inpt_ctgr)
{	
	var si_Zone='';
	if(GG_LognLocnFlag=="Z")
		si_Zone=$("#selZone").val();

	var si_RowId=$("#txtRowId").val();
	var bolValid="Y";
	$(".required").each(function() {
	    if($(this).val()=="")
	    {
		bolValid="N";
	    }
	});
	if(bolValid=="N")
	{
		alert("Please enter values for all the required fields !!");
		return false;
	}
	var myurl="/RailSAHAY/AcqnPlan";
	var inpt_pkt;
	switch (inpt_ctgr)
	{
		case 'CMDT_COST_CMPR':
			var dvsn=$("#selDvsn").val();
			var inds=$("#txtIndsFrgtGnrt").val();
			var othrdtls=$("#txtIndsFrgtGnrtOthr").val();
			var locntype=$("#selLocnType").val();
			var locn=$("#txtPortTrml").val();
			var totlvol=$("#txtTotlVol").val();
			var railpctg=$("#txtRailPctg").val();
			var slab1=$("#txt050").val();
			var slab2=$("#txt50100").val();
			var slab3=$("#txt100200").val();
			var slab4=$("#txt200300").val();
			var slab5=$("#txt300400").val();
			var slab6=$("#txt400500").val();
			var slab7=$("#txt500600").val();
			var slab8=$("#txt600Plus").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Inds:inds,OthrDtls:othrdtls,LocnType:locntype,Locn:locn, TotlVol:totlvol,RailPctg:railpctg,Slab1:slab1,Slab2:slab2,Slab3:slab3,Slab4:slab4,Slab5:slab5,Slab6:slab6,Slab7:slab7,Slab8:slab8,RowId:si_RowId};
			break;
		case 'CMDT_RAIL_COST':
			var dvsn=$("#selDvsn").val();
			var inds=$("#txtIndsFrgtGnrt").val();
			var othrdtls=$("#txtIndsFrgtGnrtOthr").val();
			var cmdt=$("#selNumCmdt").val();
			var locntype=$("#selLocnType").val();
			/*var locn=refineValue($("#txtPortTrml").val(),'S');*/
			var locn=($("#selPortTrml").val()).join(",");
			var totlvol=fixDecimal($("#txtTotlVol").val());
			var railpctg=fixDecimal($("#txtRailPctg").val());
			/*
			var lmcost=fixDecimal($("#txtLMFM").val());
			var ldngcost=fixDecimal($("#txtLdngCost").val());
			var uldgcost=fixDecimal($("#txtUldgCost").val());
			*/
			var addlcost=fixDecimal($("#txtAddlCost").val());
			var slab1=CMDT_TL_COST[0]+"/"+CMDT_WL_COST[0];
			var slab2=CMDT_TL_COST[1]+"/"+CMDT_WL_COST[1];
			var slab3=CMDT_TL_COST[2]+"/"+CMDT_WL_COST[2];
			var slab4=CMDT_TL_COST[3]+"/"+CMDT_WL_COST[3];
			var slab5=CMDT_TL_COST[4]+"/"+CMDT_WL_COST[4];
			var slab6=CMDT_TL_COST[5]+"/"+CMDT_WL_COST[5];
			var slab7=CMDT_TL_COST[6]+"/"+CMDT_WL_COST[6];
			var slab8=CMDT_TL_COST[7]+"/"+CMDT_WL_COST[7];
			var slab9=CMDT_TL_COST[8]+"/"+CMDT_WL_COST[8];
			var slab10=CMDT_TL_COST[9]+"/"+CMDT_WL_COST[9];
			var slab11=CMDT_TL_COST[10]+"/"+CMDT_WL_COST[10];
			var slab12=CMDT_TL_COST[11]+"/"+CMDT_WL_COST[11];
			var slab13=CMDT_TL_COST[12]+"/"+CMDT_WL_COST[12];
			var slab14=CMDT_TL_COST[13]+"/"+CMDT_WL_COST[13];
			var slab15=CMDT_TL_COST[14]+"/"+CMDT_WL_COST[14];
			var si_RowId=$("#txtRowId").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Inds:inds,OthrDtls:othrdtls,Cmdt:cmdt,LocnType:locntype,Locn:locn, TotlVol:totlvol,RailPctg:railpctg,Slab1:slab1,Slab2:slab2,Slab3:slab3,Slab4:slab4,Slab5:slab5,Slab6:slab6,Slab7:slab7,Slab8:slab8,Slab9:slab9,Slab10:slab10,Slab11:slab11,Slab12:slab12,Slab13:slab13,Slab14:slab14,Slab15:slab15,AddlCost:addlcost,RowId:si_RowId};			

			console.log(JSON.stringify(inpt_pkt));
			break;
		case 'CMDT_ROAD_COST':
			var zone=$("#txtZone").val();
			var dvsn=$("#txtDvsn").val();
			var inds=$("#txtIndsRoadCost").val();
			var othrdtls=$("#txtOthrIndsRoadCost").val();
			var cmdt=$("#txtCmdtRoadCost").val();
			var cmdtcode=cmdt.substring(0,cmdt.indexOf("-"));
			var slab1=fixDecimal($("#slab1").val());
			var slab2=fixDecimal($("#slab2").val());
			var slab3=fixDecimal($("#slab3").val());
			var slab4=fixDecimal($("#slab4").val());
			var slab5=fixDecimal($("#slab5").val());
			var slab6=fixDecimal($("#slab6").val());
			var slab7=fixDecimal($("#slab7").val());
			var slab8=fixDecimal($("#slab8").val());
			var slab9=fixDecimal($("#slab9").val());
			var slab10=fixDecimal($("#slab10").val());
			var slab11=fixDecimal($("#slab11").val());
			var slab12=fixDecimal($("#slab12").val());
			var slab13=fixDecimal($("#slab13").val());
			var slab14=fixDecimal($("#slab14").val());
			var slab15=fixDecimal($("#slab15").val());
			var si_RowId=$("#txtRowId").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Inds:inds,OthrDtls:othrdtls,Cmdt:cmdtcode,Slab1:slab1,Slab2:slab2,Slab3:slab3,Slab4:slab4,Slab5:slab5,Slab6:slab6,Slab7:slab7,Slab8:slab8,Slab9:slab9,Slab10:slab10,Slab11:slab11,Slab12:slab12,Slab13:slab13,Slab14:slab14,Slab15:slab15,RowId:si_RowId};
		break;
		case 'END_MILE_COST':
			var dvsn=$("#selDvsn").val();
			var inds=$("#txtIndsFrgtGnrt").val();
			var othrdtls=$("#txtIndsFrgtGnrtOthr").val();
			var slab1=$("#txt050").val();
			var slab2=$("#txt50100").val();
			var slab3=$("#txt100200").val();
			var slab4=$("#txt200300").val();
			var slab5=$("#txt300400").val();
			var slab6=$("#txt400500").val();
			var slab7=$("#txt500600").val();
			var slab8=$("#txt600Plus").val();
			var endmileslab1=$("#txtEndMile050").val();
			var endmileslab2=$("#txtEndMile50100").val();
			var endmileslab3=$("#txtEndMile100200").val();
			var endmileslab4=$("#txtEndMile200300").val();
			var endmileslab5=$("#txtEndMile300400").val();
			var endmileslab6=$("#txtEndMile400500").val();
			var endmileslab7=$("#txtEndMile500600").val();
			var endmileslab8=$("#txtEndMile600Plus").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Inds:inds,OthrDtls:othrdtls,Slab1:slab1,Slab2:slab2,Slab3:slab3,Slab4:slab4,Slab5:slab5,Slab6:slab6,Slab7:slab7,Slab8:slab8,EndMileSlab1:endmileslab1,EndMileSlab2:endmileslab2,EndMileSlab3:endmileslab3,EndMileSlab4:endmileslab4,EndMileSlab5:endmileslab5,EndMileSlab6:endmileslab6,EndMileSlab7:endmileslab7,EndMileSlab8:endmileslab8,RowId:si_RowId};
		
		break;
		case 'CNSR_WISE_INFO':
			var dvsn=$("#selDvsn").val();
			var inds=$("#txtIndsFrgtGnrt").val();
			var othrdtls=$("#txtIndsFrgtGnrtOthr").val();
			/*
			var locntype=$("#selLocnType").val();
			var locn=refineValue($("#txtPortTrml").val(),'S');
			*/
			var locntype='';
			var locn='';
			var trfctype=$("#selTrfcType").val();
			/*var stcktype=refineValue($("#txtStckType").val(),'R');*/
			var stcktype=($("#selStckType1").val()).join(",");
			var cmdt=refineValue($("#txtCmdt").val(),'CM');
			var totlvol=fixDecimal($("#txtTotlVol").val());
			var railpctg=fixDecimal($("#txtRailPctg").val());
			var cmptadvt=($("#selCmptAdvt").val()).join(",");
			var othrdtls1=$("#txtOthrCmptAdvt").val();			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Inds:inds,Cmdt:cmdt,OthrDtls:othrdtls,LocnType:locntype,Locn:locn,TrfcType:trfctype,StckType:stcktype,TotlVol:totlvol,RailPctg:railpctg,CmptAdvt:cmptadvt,OthrAdvtDtls:othrdtls1,RowId:si_RowId};
		break;
		case 'MKTG_STRT_BULK':
			var dvsn=$("#selDvsn").val();
			var cmdt=$("#txtCmdt").val();
			var trfctype=$("#txtType").val();
			var orgn=refineValue($("#txtOrgn").val(),'S');
			var dstn=refineValue($("#txtDstn").val(),'S');
			var volexpd=fixDecimal($("#txtVolExpd").val());
			var cnsr=refineValue($("#txtCnsr").val(),'C');
			var addlrevn=fixDecimal($("#txtAddlRevn").val());
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Cmdt:cmdt,TrfcType:trfctype, Orgn:orgn,Dstn:dstn,CmptAdvt:volexpd,Cnsr:cnsr,AddlRevn:addlrevn,RowId:si_RowId};
		
		break;
		case 'AGMT_LDNG':
			var dvsn=$("#selDvsn").val();
			var actnplan=$("#txtActnPlan").val();
			var othrdtls=$("#txtOthrActnDtls").val();
			var ofcr=$("#txtOfcr").val();
			var cmdt=$("#txtCmdt").val();
			var voltrgt=fixDecimal($("#txtVolTrgt").val());
			var revnexpd=fixDecimal($("#txtRevnExpd").val());
			var cost=fixDecimal($("#txtCost").val());
			var tranrqmt=$("#txtTranRqmt").val();
			var handholdrb='';
			if($('#optPropYes').is(':checked'))
				handholdrb=$("#selPropList").val();
			if($('#optPropNow').is(':checked'))
				handholdrb=$("#txtPropId").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,ActnPlan:actnplan,OthrPlanDesc:othrdtls,Ofcr:ofcr, Cmdt:cmdt,VolTrgt:voltrgt,RevnExpd:revnexpd,Cost:cost,TranRqmt:tranrqmt,HandHoldRB:handholdrb,RowId:si_RowId};
		
		break;

		case 'AGMT_LDNG1':
			var dvsn=$("#selDvsn").val();
			var cmdt=refineValue($("#txtCmdt").val(),'CM');
			var cnsr=$("#txtCnsr").val();
			var trfctype=$("#selTrfcType").val();
			var stck=refineValue($("#txtStckType").val(),'R');
			var actnplan=($("#selActnPlan").val()).join(",");
			var othrdtls=$("#txtActnPlanOthr").val();
			var ofcr=($("#selOfcr").val()).join(",");
			var cost=fixDecimal($("#txtCost").val());
			var voltrgt=fixDecimal($("#txtVolTrgt").val());
			var revnexpd=fixDecimal($("#txtRevnExpd").val());
			var handholdrb='';
			if($('#optPropYes').is(':checked'))
				handholdrb=$("#selPropList").val();
			if($('#optPropNow').is(':checked'))
				handholdrb=$("#txtPropId").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,ActnPlan:actnplan,OthrPlanDesc:othrdtls,Ofcr:ofcr,Cmdt:cmdt,Cnsr:cnsr,TrfcType:trfctype,VolTrgt:voltrgt,RevnExpd:revnexpd,Cost:cost,Stck:stck,HandHoldRB:handholdrb,RowId:si_RowId};
			
			break;
		case 'FF_CRNT':
			var dvsn=$("#selDvsn").val();
			var ffname=$("#txtFFName").val();
			var ffcontdtls=$("#txtFFContDtls").val();
			var trfcvol=fixDecimal($("#txtTrfcVol").val());
			var revn=fixDecimal($("#txtRevn").val());
			var od=$("#txtOD").val();
			/*var cmdt=refineValue($("#txtCmdt").val(),'CM');*/
			var cmdt=($("#selCmdtFF").val()).join(",");
			var depldate=$("#txtDeplDate").val();
			var depldatearr=depldate.split("-");
			var depldate1=depldatearr[2]+"-"+depldatearr[1]+"-"+depldatearr[0];
			var rmrk=$("#txtRmrk").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,FFName:ffname,ContDtls:ffcontdtls,TrfcVol:trfcvol, Revn:revn,OD:od,Cmdt:cmdt,DeplDate:depldate1,Rmrk:rmrk,RowId:si_RowId};
		
		break;
		case 'FF_PROPOSED':
			var dvsn=$("#selDvsn").val();
			var ffname=$("#txtFFName").val();
			var ffcontdtls=$("#txtFFContDtls").val();
			var trfcvol=fixDecimal($("#txtTrfcVol").val());
			var revn=fixDecimal($("#txtRevn").val());
			var od=$("#txtOD").val();
			/*var cmdt=refineValue($("#txtCmdt").val(),'CM');*/

			var cmdt=($("#selCmdtFF").val()).join(",");

			var prgsstts=$("#txtPrgsStts").val();
			var rmrk=$("#txtRmrk").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,FFName:ffname,ContDtls:ffcontdtls,TrfcVol:trfcvol, Revn:revn,OD:od,Cmdt:cmdt,Prgs:prgsstts,Rmrk:rmrk,RowId:si_RowId};
		
		break;
		case 'INIT_TRFC_BOOST':
			var dvsn=$("#selDvsn").val();
			var init=$("#txtInitTaken").val();
			var cmdt=refineValue($("#txtCmdt").val(),'CM');
			var ldng=$("#txtLdng").val();
			var revn=$("#txtRevn").val();
			var rmrk=$("#txtRmrk").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Init:init,Cmdt:cmdt, Ldng:ldng,Revn:revn,Rmrk:rmrk,RowId:si_RowId};
		
		break;
		case 'SWOT_ANLY':
			var dvsn=$("#selDvsn").val();
			var cmdt=refineValue($("#txtCmdt").val(),'CM');
			var strg=$("#selStrg").val();
			var strglist=strg.join(",");

			var weak=$("#selWeak").val();
			var weaklist=weak.join(",");

			var oprt=$("#selOprt").val();
			var oprtlist=oprt.join(',');

			var thrt=$("#selThrt").val();
			var thrtlist=thrt.join(",");

			var strg1=$("#txtStrg").val();
			var weak1=$("#txtWeak").val();
			var oprt1=$("#txtOprt").val();
			var thrt1=$("#txtThrt").val();

			var strgdtls=$("#txtStrgDtls").val();
			var weakdtls=$("#txtWeakDtls").val();
			var oprtdtls=$("#txtOprtDtls").val();
			var thrtdtls=$("#txtThrtDtls").val();

			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Cmdt:cmdt,Strg:strglist,StrgOthr:strg1,StrgDtls:strgdtls,Weak:weaklist,WeakOthr:weak1,WeakDtls:weakdtls,Oprt:oprtlist,OprtOthr:oprt1,OprtDtls:oprtdtls,Thrt:thrtlist,ThrtOthr:thrt1,ThrtDtls:thrtdtls,RowId:si_RowId};
			
		
		break;
		case 'INDS_OUTREACH':
			var dvsn=$("#selDvsn").val();
			var indstype=$("#selIndsType").val();
			var inds=$("#txtInds").val();
			var cmdt=refineValue($("#txtCmdt").val(),'CM');
			var contpers=$("#txtIndsPers").val();
			var desg=$("#txtDesg").val();
			var dat1=$("#txtContDate").val();
			var contdate='';
			if(dat1!='')
				contdate=dat1.split("-")[2]+'-'+dat1.split("-")[1]+'-'+dat1.split("-")[0];
			var addlrevn=$("#txtAddlRevn").val();
			var addlldng=$("#txtAddlLdng").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,IndsType:indstype,Inds:inds,Cmdt:cmdt,IndsPers:contpers,Desg:desg,ContDate:contdate,AddlRevn:addlrevn,AddlLdng:addlldng,RowId:si_RowId};
		
		break;
		case 'RAIL_COEF_BOOST':
			var dvsn=$("#selDvsn").val();
			var immed=$("#selImmed").val();
			var immed1=immed.join(",");
			var immedothr=$("#txtImmed").val();
			var midterm=$("#selMed").val();
			var midterm1=midterm.join(",");
			var midtermothr=$("#txtMed").val();
			var longterm=$("#selLong").val();
			var longterm1=longterm.join(",");			
			var longtermothr=$("#txtLong").val();
			var trfcplanid=$("#txtTrfcPlanId").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Immed:immed1,ImmedOthr:immedothr, MidTerm:midterm1,MidTermOthr:midtermothr,LongTerm:longterm1,LongTermOthr:longtermothr,TrfcPlanId:trfcplanid,RowId:si_RowId};
			console.log(JSON.stringify(inpt_pkt));
		break;
		case 'OTHR_ASPECT':
			var dvsn=$("#selDvsn").val();
			var aspect=$("#txtAspect").val();
			
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,Aspect:aspect,RowId:si_RowId};
		
		break;
		case 'ZONL_PERF':
			var projrevn=$("#txtProjRevn").val();
			var projldng=$("#txtProjLdng").val();
			si_RowId=$("#txtZonlPerfRowId").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,ProjRevn:projrevn,ProjLdng:projldng,RowId:si_RowId};
		break;
		case 'DVSN_PERF':
			var dvsn=GG_CrntLocn;
			var projrevn=$("#txtProjRevn").val();
			var projldng=$("#txtProjLdng").val();
			var trgtldng=$("#txtTrgtLdng").val();
			si_RowId=$("#txtZonlPerfRowId").val();
			inpt_pkt={Optn:'SAVE_RECORD',Zone:si_Zone,Ctgr:inpt_ctgr,Dvsn:dvsn,ProjRevn:projrevn,ProjLdng:projldng,TrgtLdng:trgtldng,RowId:si_RowId};		
			
		break;
	}	
	
	$.ajax({
     url : myurl,
     type : "post",
     data: inpt_pkt,
     async : true,
     success : function(data1) {
            var obj= JSON.parse(data1);
            var stts=obj.Stts;
            if(stts=="S")
            {
	       if((inpt_ctgr!="ZONL_PERF") && (inpt_ctgr!="DVSN_PERF"))
	               trxnSuccess("Saved Successfully !"); 
		else
			successToast("Saved Successfully");
  	       	
		fetchPlanData('',inpt_ctgr);
		if(inpt_ctgr=="CMDT_RAIL_COST")
			fetchPlanData('','CMDT_ROAD_COST');
            }
            else
            {
	       if((inpt_ctgr!="ZONL_PERF") && (inpt_ctgr!="DVSN_PERF"))
	                trxnFailed("Failed to Save! "+obj.ErorMesg);
		else
			failedToast("Failed to Save! "+obj.ErorMesg);
			
            }
	}
	});
}
function trxnSuccess(mesg)
{
	$("#divMesg").removeClass();
	$("#divMesg").addClass("alert alert-success alert-mesg");
	$("#divMesg").html("Data Added/Updated Successfully");
	$("#divAddRecord input[type='text']").val("");
	$("#divAddRecord textarea").val("");
}
function trxnFailed(mesg)
{
	$("#divMesg").removeClass();
	$("#divMesg").addClass("alert alert-danger alert-mesg");
	$("#divMesg").html(mesg);

}
function delRecord(delitem,inpt_ctgr,rowid)
{	
    if(GG_DataCnfmStts=="Y")
    {
	alert("Data has already been marked CONFIRMED/FREEZE for the month! Can't be changed now")
	return false;
    }
    $(delitem).closest('tr').addClass("selected-row");
    if (confirm('This action will delete the selected plan! Do you want to continue')) 
    {
	    var myurl="/RailSAHAY/AcqnPlan";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'DEL_ROW',Ctgr:inpt_ctgr,RowId:rowid},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
	               successToast("Plan Deleted Successfully !"); 
	               fetchPlanData('',inpt_ctgr);
		       if(inpt_ctgr=="CMDT_RAIL_COST")
				fetchPlanData('','CMDT_ROAD_COST');
	            }
	            else
	            {
	                failedToast("Failed to Delete! "+obj.ErorMesg);
			$(delitem).closest('tr').removeClass("selected-row");
	            }
		}
		});
	}
	else
	{
		return false;	
	}
}
function editRecord(delitem, inpt_ctgr, rowid, sqnc)
{
    if(GG_DataCnfmStts=="Y")
    {
	alert("Data has already been marked CONFIRMED/FREEZE for the month! Can't be changed now")
	return false;
    }
	$(delitem).closest('tr').addClass("selected-row");		
	addRecord(inpt_ctgr);
	$("#txtRowId").val(rowid);
	$(".btn-save").text("Update & Save");
	$("#divMesg").removeClass();
	$("#divMesg").html('');
	
		if(inpt_ctgr=="RAIL_COEF_BOOST")
		{
			$("#planFileName").html("");
			$("#planMesg").html("");
			$("#iFrmTrfcPlan").show();
			$("#iFrmTrfcPlan").css("display","block");
		}

	switch (inpt_ctgr)
	{
		case 'CMDT_COST_CMPR':
			$("#selDvsn").val(CMDT_COST_CMPR[sqnc][0]);

			var indstype=(CMDT_COST_CMPR[sqnc][1].split("(")[0]).trim();
			$("#txtIndsFrgtGnrt").val(indstype);
			if(CMDT_COST_CMPR[sqnc][1].indexOf("(")>0)
			{
				$("#txtIndsFrgtGnrtOthr").val(CMDT_COST_CMPR[sqnc][1].substring(CMDT_COST_CMPR[sqnc][1].indexOf("(")+1,CMDT_COST_CMPR[sqnc][1].length-1))	;
				$("#txtIndsFrgtGnrtOthr").removeAttr('readonly');
			}

			var locntype=CMDT_COST_CMPR[sqnc][2].split(":")[0];
			if(locntype=='DIVISION') 
				$("#selLocnType").val("D");
			if(locntype=='PORT') 
				$("#selLocnType").val("P");
			if(locntype=='TERMINAL') 
				$("#selLocnType").val("T");

			/*$("#txtPortTrml").val(CMDT_COST_CMPR[sqnc][2].split(":")[1]);*/
			$("#txtTotlVol").val(CMDT_COST_CMPR[sqnc][3]);
			$("#txtRailPctg").val(CMDT_COST_CMPR[sqnc][4]);
			$("#txt050").val(CMDT_COST_CMPR[sqnc][5]);
			$("#txt50100").val(CMDT_COST_CMPR[sqnc][6]);
			$("#txt100200").val(CMDT_COST_CMPR[sqnc][7]);
			$("#txt200300").val(CMDT_COST_CMPR[sqnc][8]);
			$("#txt300400").val(CMDT_COST_CMPR[sqnc][9]);
			$("#txt400500").val(CMDT_COST_CMPR[sqnc][10]);
			$("#txt500600").val(CMDT_COST_CMPR[sqnc][11]);
			$("#txt600Plus").val(CMDT_COST_CMPR[sqnc][12]);			
			break;

		case 'CMDT_RAIL_COST' :
			$('#selDvsn option[value="'+CMDT_RAIL_COST[sqnc][1]+'"]').prop('selected', true);
			var inds=CMDT_RAIL_COST[sqnc][2];
			var othrdtls='';
			if(CMDT_RAIL_COST[sqnc][2].indexOf("OTHER")==0)
			{
				inds=CMDT_RAIL_COST[sqnc][2].substring(0,CMDT_RAIL_COST[sqnc][2].indexOf("-"));
				othrdtls=CMDT_RAIL_COST[sqnc][2].substring(CMDT_RAIL_COST[sqnc][2].indexOf("-")+1);
			}
			$("#txtIndsFrgtGnrt").val(inds);
			$("#txtIndsFrgtGnrtOthr").val(othrdtls);

			$('#txtIndsFrgtGnrt').on('mousedown', function(e) {
			   e.preventDefault();
			   this.blur();
			   window.focus();
			});
			var cmdtcode=CMDT_RAIL_COST[sqnc][3].substring(0,CMDT_RAIL_COST[sqnc][3].indexOf("-"));
			$('#selNumCmdt option[value="'+cmdtcode+'"]').prop('selected', true);
			$('#selNumCmdt').on('mousedown', function(e) {
			   e.preventDefault();
			   this.blur();
			   window.focus();
			});
			var trmltype=CMDT_RAIL_COST[sqnc][4].substring(0,CMDT_RAIL_COST[sqnc][4].indexOf(":"));
			var trml=CMDT_RAIL_COST[sqnc][4].substring(CMDT_RAIL_COST[sqnc][4].indexOf(":")+1);
			if(trmltype=="PORT")
				$('#selLocnType option[value="P"]').prop('selected', true);
			if(trmltype=="TERMINAL")
				$('#selLocnType option[value="T"]').prop('selected', true);
			if(trmltype=="DIVISION")
				$('#selLocnType option[value="D"]').prop('selected', true);
			
			setPortTrmlList(trmltype.substring(0,1),CMDT_RAIL_COST[sqnc][0],CMDT_RAIL_COST[sqnc][1]);
			
			/*$("#txtPortTrml").val(trml);*/
			$("#txtTotlVol").val(CMDT_RAIL_COST[sqnc][5]);
			$("#txtRailPctg").val(CMDT_RAIL_COST[sqnc][6]);
			/*
			var costarr=CMDT_RAIL_COST[sqnc][22].split("/");
			$("#txtLMFM").val(costarr[0]);
			$("#txtLdngCost").val(costarr[1]);
			$("#txtUldgCost").val(costarr[2]);
			*/
			$("#txtAddlCost").val(CMDT_RAIL_COST[sqnc][22]);
			$("#txtRowId").val(CMDT_RAIL_COST[sqnc][23]);
			fetchCmdtRailCost(cmdtcode,CMDT_RAIL_COST[sqnc][3].substring(CMDT_RAIL_COST[sqnc][3].indexOf("-")+1));
			break; 	

		case 'CMDT_ROAD_COST' :
			$("#txtZone").val(CMDT_ROAD_COST[sqnc][0]);
			$("#txtDvsn").val(CMDT_ROAD_COST[sqnc][1]);
			var inds=CMDT_RAIL_COST[sqnc][2];
			var othrdtls='';
			if(CMDT_ROAD_COST[sqnc][2].indexOf("OTHER")==0)
			{
				inds=CMDT_ROAD_COST[sqnc][2].substring(0,CMDT_ROAD_COST[sqnc][2].indexOf("-"));
				othrdtls=CMDT_ROAD_COST[sqnc][2].substring(CMDT_ROAD_COST[sqnc][2].indexOf("-")+1);
			}
			$("#txtIndsRoadCost").val(inds);
			$("#txtOthrIndsRoadCost").val(othrdtls);
			var cmdtcode=CMDT_ROAD_COST[sqnc][3].substring(0,CMDT_ROAD_COST[sqnc][3].indexOf("-"));
			var cmdtname=CMDT_ROAD_COST[sqnc][3].substring(CMDT_ROAD_COST[sqnc][3].indexOf("-")+1);
			$("#txtCmdtRoadCost").val(CMDT_ROAD_COST[sqnc][3]);
			var railcntr=-1;
			for(var i=0;i<CMDT_RAIL_COST.length;i++)
			{
				if((CMDT_ROAD_COST[sqnc][0]==CMDT_RAIL_COST[i][0]) && (CMDT_ROAD_COST[sqnc][1]==CMDT_RAIL_COST[i][1]) && (CMDT_ROAD_COST[sqnc][3]==CMDT_RAIL_COST[i][3]))
				{
					railcntr=i;
					break;
				}
			}
			fetchCmdtRailCostTotl(cmdtcode,CMDT_RAIL_COST[railcntr][22]);
			$("#slab1").val(CMDT_ROAD_COST[sqnc][4]);
			$("#slab2").val(CMDT_ROAD_COST[sqnc][5]);
			$("#slab3").val(CMDT_ROAD_COST[sqnc][6]);
			$("#slab4").val(CMDT_ROAD_COST[sqnc][7]);
			$("#slab5").val(CMDT_ROAD_COST[sqnc][8]);
			$("#slab6").val(CMDT_ROAD_COST[sqnc][9]);
			$("#slab7").val(CMDT_ROAD_COST[sqnc][10]);
			$("#slab8").val(CMDT_ROAD_COST[sqnc][11]);
			$("#slab9").val(CMDT_ROAD_COST[sqnc][12]);
			$("#slab10").val(CMDT_ROAD_COST[sqnc][13]);
			$("#slab11").val(CMDT_ROAD_COST[sqnc][14]);
			$("#slab12").val(CMDT_ROAD_COST[sqnc][15]);
			$("#slab13").val(CMDT_ROAD_COST[sqnc][16]);
			$("#slab14").val(CMDT_ROAD_COST[sqnc][17]);
			$("#slab15").val(CMDT_ROAD_COST[sqnc][18]);
			$("#txtRowId").val(CMDT_ROAD_COST[sqnc][19]);
			$("#slab1").focus();
			$("#slab1").select();
				break; 	
		case 'END_MILE_COST':		
			$("#selDvsn").val(END_MILE_COST[sqnc][0]);

			var indstype=(END_MILE_COST[sqnc][1].split("(")[0]).trim();
			$("#txtIndsFrgtGnrt").val(indstype);
			if(END_MILE_COST[sqnc][1].indexOf("(")>0)
			{
				$("#txtIndsFrgtGnrtOthr").val(END_MILE_COST[sqnc][1].substring(END_MILE_COST[sqnc][1].indexOf("(")+1,END_MILE_COST[sqnc][1].length-1))	;
				$("#txtIndsFrgtGnrtOthr").removeAttr('readonly');
			}
			$("#txt050").val(END_MILE_COST[sqnc][2]);
			$("#txt50100").val(END_MILE_COST[sqnc][3]);
			$("#txt100200").val(END_MILE_COST[sqnc][4]);
			$("#txt200300").val(END_MILE_COST[sqnc][5]);
			$("#txt300400").val(END_MILE_COST[sqnc][6]);
			$("#txt400500").val(END_MILE_COST[sqnc][7]);
			$("#txt500600").val(END_MILE_COST[sqnc][8]);
			$("#txt600Plus").val(END_MILE_COST[sqnc][9]);
			$("#txtEndMile050").val(END_MILE_COST[sqnc][10]);
			$("#txtEndMile50100").val(END_MILE_COST[sqnc][11]);
			$("#txtEndMile100200").val(END_MILE_COST[sqnc][12]);
			$("#txtEndMile200300").val(END_MILE_COST[sqnc][13]);
			$("#txtEndMile300400").val(END_MILE_COST[sqnc][14]);
			$("#txtEndMile400500").val(END_MILE_COST[sqnc][15]);
			$("#txtEndMile500600").val(END_MILE_COST[sqnc][16]);
			$("#txtEndMile600Plus").val(END_MILE_COST[sqnc][17]);
			break; 			
		case 'CNSR_WISE_INFO':
			$('#selDvsn option[value="'+CNSR_WISE_INFO[sqnc][1]+'"]').prop('selected', true);
			$('#selTrfcType option[value="'+CNSR_WISE_INFO[sqnc][5]+'"]').prop('selected', true);

			var indstype=(CNSR_WISE_INFO[sqnc][1].split("")[0]);
			var inds=CNSR_WISE_INFO[sqnc][2];
			var othrdtls='';
			if(CNSR_WISE_INFO[sqnc][2].indexOf("OTHER")==0)
			{
				inds=CNSR_WISE_INFO[sqnc][2].substring(0,CNSR_WISE_INFO[sqnc][2].indexOf("-"));
				othrdtls=CNSR_WISE_INFO[sqnc][2].substring(CNSR_WISE_INFO[sqnc][2].indexOf("-")+1);
			}
			$("#txtIndsFrgtGnrt").val(inds);
			$("#txtIndsFrgtGnrtOthr").val(othrdtls);
			$("#txtCmdt").val(CNSR_WISE_INFO[sqnc][3]);
			$("#txtStckType").val(CNSR_WISE_INFO[sqnc][6]);
			/*
			var locntype=CNSR_WISE_INFO[sqnc][4].split(":")[0];
			if(locntype=='DIVISION') 
				$('#selLocnType option[value="D"]').prop('selected', true);
			if(locntype=='PORT') 
				$('#selLocnType option[value="P"]').prop('selected', true);
			if(locntype=='TERMINAL') 
				$('#selLocnType option[value="T"]').prop('selected', true);

			$("#txtPortTrml").val(CNSR_WISE_INFO[sqnc][4].split(":")[1]);*/
			$("#txtTotlVol").val(CNSR_WISE_INFO[sqnc][7]);
			$("#txtRailPctg").val(CNSR_WISE_INFO[sqnc][8]);

			var cmptadvt=CNSR_WISE_INFO[sqnc][9];
			var othradvt=CNSR_WISE_INFO[sqnc][10];
			$("#txtOthrCmptAdvt").val(othradvt);
			$("#txtRowId").val(CNSR_WISE_INFO[sqnc][11]);

			break;			
		case 'MKTG_STRT_BULK':
			$('#selDvsn option[value="'+MKTG_STRT_BULK[sqnc][1]+'"]').prop('selected', true);
			$("#txtCmdt").val(MKTG_STRT_BULK[sqnc][2]);
			$('#txtType option[value="'+MKTG_STRT_BULK[sqnc][3]+'"]').prop('selected', true);
			$("#txtOrgn").val(MKTG_STRT_BULK[sqnc][4]);
			$("#txtDstn").val(MKTG_STRT_BULK[sqnc][5]);
			$("#txtVolExpd").val(MKTG_STRT_BULK[sqnc][6]);
			$("#txtCnsr").val(MKTG_STRT_BULK[sqnc][7]);
			$("#txtAddlRevn").val(MKTG_STRT_BULK[sqnc][8]);		
			$("#txtRowId").val(MKTG_STRT_BULK[sqnc][9]);
			break;
		case 'AGMT_LDNG':
			$('#selDvsn option[value="'+AGMT_LDNG[sqnc][1]+'"]').prop('selected', true);

			var actnplan=AGMT_LDNG[sqnc][2];
			var othrplan='';
			if(actnplan.indexOf("OTHER")==0)
			{
				actnplan=AGMT_LDNG[sqnc][2].substring(0,AGMT_LDNG[sqnc][2].indexOf("-"));
				othrplan=AGMT_LDNG[sqnc][2].substring(AGMT_LDNG[sqnc][2].indexOf("-")+1);
			}

			$("#txtActnPlan").val(actnplan);
			$("#txtOthrActnPlan").val(othrplan);
			$('#txtOfcr option[value="'+AGMT_LDNG[sqnc][3]+'"]').prop('selected', true);
			$("#txtCmdt").val(AGMT_LDNG[sqnc][4]);
			$("#txtVolTrgt").val(AGMT_LDNG[sqnc][5]);
			$("#txtRevnExpd").val(AGMT_LDNG[sqnc][6]);
			$("#txtCost").val(AGMT_LDNG[sqnc][7]);
			$("#txtTranRqmt").val(AGMT_LDNG[sqnc][8]);
			if(AGMT_LDNG[sqnc][9]!="")
			{
				$('#chkBusinessProp').prop('checked', true);
				$("#spanFileName").html("Proposal File: "+AGMT_LDNG[sqnc][9]);
			}	
			$("#txtRowId").val(AGMT_LDNG[sqnc][10]);
			break;
		case 'AGMT_LDNG1':
			$('#selDvsn option[value="'+AGMT_LDNG1[sqnc][1]+'"]').prop('selected', true);
			$('#selActnPlan option[value="'+AGMT_LDNG1[sqnc][6]+'"]').prop('selected', true);
			$("#txtActnPlanOthr").val(AGMT_LDNG1[sqnc][7]);
			$('#txtOfcr option[value="'+AGMT_LDNG1[sqnc][7]+'"]').prop('selected', true);
			$("#txtCmdt").val(AGMT_LDNG1[sqnc][2]);
			$("#txtVolTrgt").val(AGMT_LDNG1[sqnc][10]);
			$("#txtRevnExpd").val(AGMT_LDNG1[sqnc][11]);
			$("#txtCost").val(AGMT_LDNG1[sqnc][9]);
			$("#txtStckType").val(AGMT_LDNG1[sqnc][5]);
			$("#txtCnsr").val(AGMT_LDNG1[sqnc][3]);
			$('#selTrfcType option[value="'+AGMT_LDNG1[sqnc][4]+'"]').prop('selected', true);
			if(AGMT_LDNG1[sqnc][12]!="")
			{
				$('#chkBusinessProp').prop('checked', true);
				$("#spanFileName").html("Proposal File: "+AGMT_LDNG1[sqnc][12]);
			}	
			$("#txtRowId").val(AGMT_LDNG1[sqnc][13]);
			break;
		case 'FF_CRNT':
			$('#selDvsn option[value="'+FF_CRNT[sqnc][1]+'"]').prop('selected', true);
			$("#txtFFName").val(FF_CRNT[sqnc][2]);
			$("#txtFFContDtls").val(FF_CRNT[sqnc][3]);
			$("#txtTrfcVol").val(FF_CRNT[sqnc][4]);
			$("#txtRevn").val(FF_CRNT[sqnc][5]);
			$("#txtOD").val(FF_CRNT[sqnc][6]);
			$("#txtCmdt").val(FF_CRNT[sqnc][7]);
			$("#txtDeplDate").val(FF_CRNT[sqnc][8]);
			$("#txtRmrk").val(FF_CRNT[sqnc][9]);	
			$("#txtRowId").val(FF_CRNT[sqnc][10]);			
			break;
		case 'FF_PROPOSED':
			$('#selDvsn option[value="'+FF_PROPOSED[sqnc][1]+'"]').prop('selected', true);
			$("#txtFFName").val(FF_PROPOSED[sqnc][2]);
			$("#txtFFContDtls").val(FF_CRNT[sqnc][3]);
			$("#txtTrfcVol").val(FF_PROPOSED[sqnc][4]);
			$("#txtRevn").val(FF_PROPOSED[sqnc][5]);
			$("#txtOD").val(FF_PROPOSED[sqnc][6]);
			$("#txtCmdt").val(FF_PROPOSED[sqnc][7]);
			$("#txtPrgsStts").val(FF_PROPOSED[sqnc][8]);
			$("#txtRmrk").val(FF_PROPOSED[sqnc][9]);
			$("#txtRowId").val(FF_PROPOSED[sqnc][10]);
			break;
		case 'INIT_TRFC_BOOST':
			$('#selDvsn option[value="'+INIT_TRFC_BOOST[sqnc][1]+'"]').prop('selected', true);
			$("#txtInitTaken").val(INIT_TRFC_BOOST[sqnc][2]);
			$("#txtCmdt").val(INIT_TRFC_BOOST[sqnc][3]);
			$("#txtLdng").val(INIT_TRFC_BOOST[sqnc][4]);
			$("#txtRevn").val(INIT_TRFC_BOOST[sqnc][5]);
			$("#txtRmrk").val(INIT_TRFC_BOOST[sqnc][6]);
			$("#txtRowId").val(INIT_TRFC_BOOST[sqnc][7]);
			break;
		case 'SWOT_ANLY':
			$('#selDvsn option[value="'+SWOT_ANLY[sqnc][1]+'"]').prop('selected', true);
			$("#txtCmdt").val(SWOT_ANLY[sqnc][2]);
			selHighlight("selStrg", SWOT_ANLY[sqnc][3]);
			selHighlight("selWeak", SWOT_ANLY[sqnc][6]);
			selHighlight("selOprt", SWOT_ANLY[sqnc][9]);
			selHighlight("selThrt", SWOT_ANLY[sqnc][12]);
			$("#txtStrg").val(SWOT_ANLY[sqnc][4]);
			$("#txtStrgDtls").val(SWOT_ANLY[sqnc][5]);
			$("#txtWeak").val(SWOT_ANLY[sqnc][7]);
			$("#txtWeakDtls").val(SWOT_ANLY[sqnc][8]);
			$("#txtOprt").val(SWOT_ANLY[sqnc][10]);
			$("#txtOprtDtls").val(SWOT_ANLY[sqnc][11]);
			$("#txtThrt").val(SWOT_ANLY[sqnc][13]);
			$("#txtThrtDtls").val(SWOT_ANLY[sqnc][14]);
			$("#txtRowId").val(SWOT_ANLY[sqnc][15]);
			break;
		case 'INDS_OUTREACH':
			$('#selDvsn option[value="'+INDS_OUTREACH[sqnc][1]+'"]').prop('selected', true);
			$('#selIndsType option[value="'+INDS_OUTREACH[sqnc][2]+'"]').prop('selected', true);
			$("#txtInds").val(INDS_OUTREACH[sqnc][3]);
			$("#txtIndsPers").val(INDS_OUTREACH[sqnc][4]);
			$("#txtDesg").val(INDS_OUTREACH[sqnc][5]);
			$("#txtContDate").val(INDS_OUTREACH[sqnc][6]);
			$("#txtCmdt").val(INDS_OUTREACH[sqnc][7]);
			$("#txtAddlRevn").val(INDS_OUTREACH[sqnc][8]);
			$("#txtAddlLdng").val(INDS_OUTREACH[sqnc][9]);
			$("#txtRowId").val(INDS_OUTREACH[sqnc][10]);
			break;
		case 'RAIL_COEF_BOOST':
			$("#planFileName").html("");
			$("#planMesg").html("");
			$("#iFrmTrfcPlan").show();
			$("#iFrmTrfcPlan").css("display","block");
			$('#selDvsn option[value="'+RAIL_COEF_BOOST[sqnc][1]+'"]').prop('selected', true);
			$('#selImmed option[value="'+RAIL_COEF_BOOST[sqnc][2]+'"]').prop('selected', true);
			$("#txtImmed").val(RAIL_COEF_BOOST[sqnc][3]);
			$('#selMed option[value="'+RAIL_COEF_BOOST[sqnc][4]+'"]').prop('selected', true);
			$("#txtMed").val(RAIL_COEF_BOOST[sqnc][5]);
			$('#selLong option[value="'+RAIL_COEF_BOOST[sqnc][6]+'"]').prop('selected', true);
			$("#txtLong").val(RAIL_COEF_BOOST[sqnc][7]);
			$("#planFileName").html('<i class="fa fa-file" aria-hidden="true"></i>&nbsp;&nbsp;'+RAIL_COEF_BOOST[sqnc][8]);
			$("#txtRowId").val(RAIL_COEF_BOOST[sqnc][9]);
			break;
		case 'OTHR_ASPECT':
			$("#selDvsn").val(OTHR_ASPECT[sqnc][0]);
			$("#txtAspect").val(OTHR_ASPECT[sqnc][1]);
			break;
	}	

	$(".table-input>tr").removeClass("selected-row");	
}
$('body').on('keypress', '.form-control', function() {
	$("#divMesg").removeClass();
	$("#divMesg").html("");
	
});
$('body').on('change', 'select', function() {
	$("#divMesg").removeClass();
	$("#divMesg").html("");
	
});
function verifySWOTInpt(evt,swotinpt,flag)
{
	
    var words = $.trim(swotinpt.value).length ? swotinpt.value.match(/\S+/g).length : 0;
    if (words <= 30) {
        $('#'+flag+'_word_left').text((30-words)+" words left")
    }else{
        if (evt.which !== 8) evt.preventDefault();
    }
}
function verifySWOTInptDtls(evt,swotinpt,flag)
{

    var words = $.trim(swotinpt.value).length ? swotinpt.value.match(/\S+/g).length : 0;
    if (words <= 100) {
        $('#'+flag+'_word_left').text((100-words)+" words left")
    }else{
        if (evt.which !== 8) evt.preventDefault();
    }
}
function fetchDateWiseProposals()
{
	var myurl="/RailSAHAY/AcqnPlan";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'DATE_WISE_PROPOSALS'},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
	               	for(var i=0;i<obj.DataList1.length;i++)
			{
				optionText = obj.DataList1[i].PropDesc+" (File: "+obj.DataList1[i].FileName+")";
            			optionValue = obj.DataList1[i].PropId;  
			        $('#selPropList').append(new Option(optionText, optionValue));
			}
	            }
	            else
	            {
	                failedToast("Failed to Fetch Proposals! "+obj.ErorMesg);
	            }
		}
		});

}
function downloadProposalFile(fileid)
{
        $("#FileId").val(fileid);
        $("#frmGetFile").submit();
}
function freezeData()
{
	showConfirmationBox("The action will <strong>Freeze the Data (Zone: "+GG_CrntZone+") for current Month</strong>! No Change is permissible in the data after that");
	/*
	if(confirm("This will Freeze the Data for the Month! No Change is allowed after that"))
	{
		$('#frmCnfmData').submit();
	}
	else
	{
		return true;
	}*/

}

function showConfirmationBox(mesg)
{
	$("#cnfmmesg").html(mesg);
	$("#confirm-modal").modal('show');
}
function confirmYes()
{
	$('#frmCnfmData').submit();
}
function editZonlPerf()
{
    if(GG_DataCnfmStts=="Y")
    {
	alert("Data has already been marked CONFIRMED/FREEZE for the month! Can't be changed now")
	return false;
    }

	$(".zonl-perf-inpt").removeAttr("readonly");
	$(".btn-editperf").hide();
	$(".btn-saveperf").show();
	$("#txtProjRevn").focus();
	$("#txtProjRevn").select();
}
function cnclZonlPerf()
{
	$(".zonl-perf-inpt").attr("readonly","readonly");
	$(".btn-editperf").show();
	$(".btn-saveperf").hide();
	$("#txtProjRevn").val(ZONL_PERF[0][1]);
	$("#txtProjLdng").val(ZONL_PERF[0][2]);

}
function saveZonlPerf()
{    
   if(GG_DataCnfmStts=="Y")
    {
	alert("Data has already been marked CONFIRMED/FREEZE for the month! Can't be changed now")
	return false;
    }
    if(GG_LognLocnFlag=="Z")
	    saveRecord('ZONL_PERF');
    if(GG_LognLocnFlag=="D")
    	saveRecord('DVSN_PERF');

    $(".btn-editperf").show();
    $(".btn-saveperf").hide();
    $(".zonl-perf-inpt").attr("readonly","readonly");
}
function fetchDvsnPerfStats(zone)
{
	var myurl="/RailSAHAY/AcqnPlan";
	var htmlstr='';
	var dvsnlist=[];
	var dvsntngehtml=[];
	var dvsnfrgthtml=[];
	var divclss="";
	$.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'DVSN_PERF', Zone:zone},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
 	    if(stts=="S")
            {
		
		$("#lblLYRevn").html('Last Year:&nbsp;<span class="perf-val">'+obj.ZonlFrgt+' Cr</span>');
		$("#lblLYLdng").html('Last Year:&nbsp;<span class="perf-val">'+obj.ZonlTnge+' MT</span>');

		if(obj.DvsnList.length==2)
			divclss="col-lg-6";
		if(obj.DvsnList.length>=3)
			divclss="col-lg-4";

		for(var i=0;i<obj.DvsnList.length;i++)
		{
			dvsnlist[i]=obj.DvsnList[i].Dvsn;
			dvsntngehtml[i]='<div class='+divclss+' style="padding:10px;"><p class="dvsn-head">Division: '+dvsnlist[i]+'</p><table class="table table-striped table-bordered table-input"><thead><tr><th rowspan="2">Month</th><th colspan="3">Tonnage (in Million Tonnes)</th></tr><tr><th>Current Year</th><th>Last Year</th><th>Variation %</th></tr></thead><tbody>';
			dvsnfrgthtml[i]='<div class='+divclss+' style="padding:10px;"><p class="dvsn-head">Division: '+dvsnlist[i]+'</p><table class="table table-striped table-bordered table-input"><thead><tr><th rowspan="2">Month</th><th colspan="3">Freight (Crore)</th></tr><tr><th>Current Year</th><th>Last Year</th><th>Variation %</th></tr></thead><tbody>';
		}
		for(var j=0;j<dvsnlist.length;j++)
		{
			for(var i=0;i<obj.DvsnPerf.length;i++)
			{
				if(obj.DvsnPerf[i].Dvsn==dvsnlist[j])
				{
					if((obj.DvsnPerf[i].TngePctg).indexOf("-")>=0)
					{
						dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td></tr>';
					}
					else
					{
						if(obj.DvsnPerf[i].TngePctg=="")						
							dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-rise">&nbsp;</td></tr>';	
						else
							dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td></tr>';	
					}

					if((obj.DvsnPerf[i].FrgtPctg).indexOf("-")>=0)
					{
						dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td></tr>';
					}
					else
					{
						if(obj.DvsnPerf[i].FrgtPctg=="")
							dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-rise">&nbsp;</td></tr>';
						else
							dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Month+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td></tr>';
					}
				}
			}
			dvsntngehtml[j]+='</tbody></table></div>';
			dvsnfrgthtml[j]+='</tbody></table></div>';
			finltngestr+=dvsntngehtml[j];
			finlfrgtstr+=dvsnfrgthtml[j];
		}
		$("#divDvsnPerf").html(finltngestr);
	    }
	}
	});
	
}
function showTngePerf()
{
	$("#divDvsnPerf").html(finltngestr);
}
function showFrgtPerf()
{
	$("#divDvsnPerf").html(finlfrgtstr);
}
function downloadPDF()
{
    var htmlInit='<html><head><title>IR.BDU- BUSINESS DEVELOPMENT AND ACQUISITION PLAN</title>';
    var htmlEnd='</body></html>';
    var htmlToPrint = 
        '<style type="text/css">' +
        'table thead tr th, table tbody tr td {' +
        'border:1px solid #000;' +
        '} table thead tr th { background:#969fe9;font-weight:bold; }' +
        '</style></head><body>';
    for(var i=0;i<12;i++)
	htmlToPrint+=$("#step"+i).html();

    newWin = window.open("");
    newWin.document.write(htmlInit);    
    newWin.document.write("<h4 align='center' style='color:#004d99;font-family:Roboto;'>IR.BDU- BUSINESS DEVELOPMENT AND ACQUISITION PLAN</h4>");
    newWin.document.write("<h3 align='center'>"+hedr+"</h3>");

    newWin.document.write(htmlToPrint);
    newWin.document.write("<br/><h4 align='center' style='color:#595959;font-family:Roboto;'>INDIAN RAILWAYS- FREIGHT BUSINESS DEVELOPMENT (FBD) PORTAL</h4>");
    newWin.document.write(htmlEnd);
    newWin.print();
    newWin.close();
}
function fetchCmdtRailCost(numcmdt,cmdtname)
{
	var htmlstr='<table class="table table-borederd table-mis"><thead><tr><th rowspan="2">Commodity</th><th rowspan="2">Type</th><th colspan="15">Rail Cost (Per Ton Km) for Distance Slabs</tr><tr><th>0-100</th><th>100-200</th><th>200-300</th><th>300-400</th><th>400-500</th><th>500-600</th><th>600-700</th><th>700-800</th><th>800-900</th><th>900-1000</th><th>1000-1100</th><th>1100-1200</th><th>1200-1300</th><th>1300-1400</th><th>1400-1500</th></tr></thead><tbody>';
	var myurl="/RailSAHAY/AcqnPlanMIS";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'FETCH_CMDT_RAIL_COST',NumCmdt:numcmdt},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
			CMDT_TL_COST=obj.TLCost.split(",");
			CMDT_WL_COST=obj.WLCost.split(",");
			var crnttlcost='';
			var crntwlcost='';
			if (cmdtname.length > 25) {
 				 cmdtname= cmdtname.substring(0, 24) + "...";
			}
			htmlstr+='<tr><td rowspan="2">'+cmdtname+'</td><td>Full Rake Load</td>';
			for(var i=0;i<CMDT_TL_COST.length;i++)
			{
				crnttlcost=CMDT_TL_COST[i];
				if(crnttlcost.indexOf('.')==0)
					crnttlcost='0'+crnttlcost;

				htmlstr+='<td>'+crnttlcost+'</td>';
			}
			htmlstr+='</tr>';
			htmlstr+='<tr><td>Piecemeal Load</td>';
			for(var i=0;i<CMDT_WL_COST.length;i++)
			{
				crntwlcost=CMDT_WL_COST[i];
				if(crntwlcost.indexOf('.')==0)
					crntwlcost='0'+crntwlcost;

				htmlstr+='<td>'+crntwlcost+'</td>';
			}
			htmlstr+='</tr></table>';
			$("#divCmdtRailCost").html(htmlstr);
	            }
	            else
	            {
	                failedToast("Failed to Fetch Commdity Costs"+obj.ErorMesg);
	            }
		}
		});	
}

function fetchCmdtRailCostTotl(numcmdt,lmcost)
{
	var htmlstr='<table class="table table-borederd table-mis"><thead><tr><th rowspan="2">Type</th><th colspan="15">Rail Cost (Per Ton Km)+ Additional Cost [Rs '+lmcost+'] (Per Ton Km)</tr><tr><th>0-100</th><th>100-200</th><th>200-300</th><th>300-400</th><th>400-500</th><th>500-600</th><th>600-700</th><th>700-800</th><th>800-900</th><th>900-1000</th><th>1000-1100</th><th>1100-1200</th><th>1200-1300</th><th>1300-1400</th><th>1400-1500</th></tr></thead><tbody>';
	var slab=[100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500];
	var myurl="/RailSAHAY/AcqnPlanMIS";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'FETCH_CMDT_RAIL_COST',NumCmdt:numcmdt},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
			CMDT_TL_COST=obj.TLCost.split(",");
			CMDT_WL_COST=obj.WLCost.split(",");
			htmlstr+='<tr><td>Full Rake Load</td>';
			for(var i=0;i<CMDT_TL_COST.length;i++)
			{
				htmlstr+='<td>'+(((Number(CMDT_TL_COST[i])*slab[i])+(Number(lmcost)))/(slab[i])).toFixed(2)+'</td>';
			}
			htmlstr+='</tr>';
			htmlstr+='<tr><td>Piecemeal Load</td>';
			for(var i=0;i<CMDT_WL_COST.length;i++)
			{
				htmlstr+='<td>'+(((Number(CMDT_WL_COST[i])*slab[i])+(Number(lmcost)))/(slab[i])).toFixed(2)+'</td>';
			}
			htmlstr+='</tr></table>';
			$("#divCmdtRailCost").html(htmlstr);
	            }
	            else
	            {
	                failedToast("Failed to Fetch Commdity Costs"+obj.ErorMesg);
	            }
		}
		});	
}
function fetchLOVs()
{
	var myurl="/RailSAHAY/AcqnPlanMIS";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'FETCH_LOVS'},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
			for(var i=0;i<obj.DataList1.length;i++)
                        {
				GG_LOV[i]=new Array(3);
				GG_LOV[i][0]=obj.DataList1[i].Ctgr;
				GG_LOV[i][1]=obj.DataList1[i].Code;
				GG_LOV[i][2]=obj.DataList1[i].Desc;
			}
	            }
	            else
	            {
	                failedToast("Failed to Fetch List of Values"+obj.ErorMesg);
	            }
		}
		});
}
function showFormula()
{	
	$("#imgFormula").show();
}
function fetchCodeDesc(ctgr,code_str)
{
	if(code_str=="")
		return code_str;

	var codearr=[];
	var htmlstr='';
	var htmlstrt='';
	var htmlend='';
	if(code_str.indexOf(",")>0)
	{
		codearr=code_str.split(",");
	}
	else
	{
		codearr[0]=code_str;		
	}

	if(codearr.length>1)
	{
		htmlstr+='<ul class="list-group list-group-flush">';
		for(var i=0;i<codearr.length;i++)
		{
			for(var j=0;j<GG_LOV.length;j++)
			{
				if((GG_LOV[j][0]==ctgr) && (GG_LOV[j][1]==codearr[i]))
				{
					if(i==0)
						htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+GG_LOV[j][2]+'</li>'
					else
						htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+GG_LOV[j][2]+'</li>'
				}
			}
		}
		htmlstr+='</ul>';
	}	
	else
	{		
		for(var i=0;i<codearr.length;i++)
		{
			for(var j=0;j<GG_LOV.length;j++)
			{
				if((GG_LOV[j][0]==ctgr) && (GG_LOV[j][1]==codearr[i]))
				{
					htmlstr+=GG_LOV[j][2];
				}
			}
		}
	}
	return htmlstr;	
}
function fixDecimal(val)
{
	var newval=val;
	if(val=='')
	{
		return val;
	}
	else
	{
		if(val.indexOf('.')==0)
		{
			newval='0'+val;			
			return newval;
		}
		else
		{
			return val;
		}
	}
	return val;
}
function selHighlight(cntl, val)
{
	var listarr=val.split(",");
	for(var i=0;i<listarr.length;i++)
	{
		$('#'+cntl+' option[value="'+listarr[i]+'"]').attr("selected", "selected");
		$('#'+cntl+' option[value="'+listarr[i]+'"]').prop("selected", true);
	}
}
function showOtherVal(data)
{
	htmlstr='';
	if(data=='')
	{
		return data;
	}
	else
	{
		htmlstr+='<br/><span class="other-lbl">OTHER</span><span class="other-val">:&nbsp;'+data+'</span>';
	}
	return htmlstr;
}
function fetchNumCmdtName(data)
{
	var htmlstr='';
	if(data=='')
		return data;
 	

	if(data.indexOf(',')>0)
	{
		var numcmdtarr=data.split(',');
		htmlstr+='<ul class="list-group list-group-flush">';
		for(var i=0;i<numcmdtarr.length;i++)
		{
			for(var j=0;j<aNumCmdt.length;j++)
			{
				if(numcmdtarr[i]==aNumCmdt[j].substring(0,aNumCmdt[j].indexOf('-')))
				{
					htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+aNumCmdt[j].substring(aNumCmdt[j].indexOf('-')+1)+'</li>';
					break;
				}
			}
		}
		htmlstr+='</ul>';
	}
	else
	{
		if (isNaN(data)) {
		    return data;
  		}
		for(var j=0;j<aNumCmdt.length;j++)
			{
				if(data==aNumCmdt[j].substring(0,aNumCmdt[j].indexOf('-')))
				{
					htmlstr+=aNumCmdt[j].substring(aNumCmdt[j].indexOf('-')+1);
				}
			}
	}
	return htmlstr;
}

function formatMultVals(str)
{
	htmlstr='';
	if(str.indexOf(",")<0)
	{
		return str;
	}
	else
	{
		var arr=str.split(",");	
		if(arr.length>1)
		{	
			htmlstr+='<ul class="list-group list-group-flush">';
			for(var i=0;i<arr.length;i++)
				htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+arr[i]+'</li>';				

			htmlstr+='</ul>';
			return htmlstr;
		}
		else
		{
			return str;
		}
	}
}
function setPortTrmlList(trmltype,zonecode,dvsncode)
{
	if((dvsncode==null) || (dvsncode==''))
	{		
			$("#divPortTrml").html('');
			var locnarr;
			var datalocn=zonecode;

			if(trmltype=="P")
			{				
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');
				locnarr=aPortZone;
				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
				jQuery('#selPortTrml').multiselect({
			    	columns: 1,
			    	placeholder: 'SELECT LOCATION',
				});

			}
			if(trmltype=="T")
			{			
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');
				locnarr=aSttnZone;
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
			jQuery('#selPortTrml').multiselect({
		    	columns: 1,
		    	placeholder: 'SELECT LOCATION',
			});
			}
	}
	else
	{	
			$("#divPortTrml").html('');
			var locnarr;
			var datalocn=dvsncode;

			if(trmltype=="P")
			{				
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');
				locnarr=aPortDvsn;
				
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
				jQuery('#selPortTrml').multiselect({
			    	columns: 1,
			    	placeholder: 'SELECT LOCATION',
				});

			}
			if(trmltype=="T")
			{			
				$("#divPortTrml").html('<select id="selPortTrml" name="selPortTrml[]" class="form-control required" multiple></select>');
				locnarr=aSttnDvsn;
		                $('#selPortTrml').find('option').remove().end();
				for(var i=0;i<locnarr.length;i++)
				{
					var locnindx=locnarr[i];
					if(locnindx.substring(0,locnindx.indexOf('-'))==datalocn)
					{
						var trmlstr=locnindx.substring(locnindx.indexOf('-')+1);
						var trmlcode=trmlstr.substring(0,trmlstr.indexOf('-'));
                                		$('#selPortTrml').append($('<option>', {
                                    		value: trmlcode,
                                    		text: trmlstr
                                		}));
					}
				}
			jQuery('#selPortTrml').multiselect({
		    	columns: 1,
		    	placeholder: 'SELECT LOCATION',
			});
			}


	}
}
function fetchDvsnPerfFOIS(dvsn)
{
	var zone='';
	if(GG_LognLocnFlag=="Z")
	{
		zone=GG_CrntLocn;
	}
	else
	{
		return;
	}
	var myurl="/RailSAHAY/AcqnPlanMIS";
	var htmlstr='<p style="font-size:1rem;color:#000;font-weight:600;">Monthly Loading/Revenue Statistics for Division: '+dvsn+'</p> <table class="table table-striped table-brkup"><thead><tr><th rowspan="2">Month</th><th colspan="3">Loading Tonnage (MT)</th><th colspan="3">Freight Revenue (Cr)</th></tr><tr><th>Current Year</th><th>Last Year</th><th>Variation %</th><th>Current Year</th><th>Last Year</th><th>Variation %</th></tr></thead>';
	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'DVSN_PERF_STATS', Zone:zone},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
            	{	
			for(var i=0;i<obj.DvsnPerf.length;i++)
			{
				if(obj.DvsnPerf[i].Dvsn==dvsn)
				{
					var tngehtml='';
					var frgthtml='';
					if((obj.DvsnPerf[i].TngePctg).indexOf("-")>=0)
					{
						tngehtml='<td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td>';
					}
					else
					{
						if(obj.DvsnPerf[i].TngePctg=="")
							tngehtml='<td>&nbsp;</td>';
						else
							tngehtml='<td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td>';
					}
	
					if((obj.DvsnPerf[i].FrgtPctg).indexOf("-")>=0)
					{
						frgthtml='<td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td>';
					}
					else
					{						
						if(obj.DvsnPerf[i].FrgtPctg=="")
							frgthtml='<td>&nbsp;</td>';
						else
							frgthtml='<td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td>';
					}

					htmlstr+='<tr><td>'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td>'+tngehtml+'<td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td>'+frgthtml+'</tr>';
				}
			}
			htmlstr+='</tbody></table><p style="font-size:13px;font-weight:600;color:#4d4d4d;">*Data for current year is available upto: '+GG_MaxAnlyDate+'</p>';
		}
		$("#divDvsnPerfFOIS").html(htmlstr);
		$('html, body').animate({
		        scrollTop: $("#divDvsnPerfFOIS").offset().top
		}, 1000);
	    }
	});
	
}
function downloadTrfcPlan(rowid) 
{
	$("#TrfcPlanFileId").val(rowid);
	$("#frmTrfcPlan").submit();
}