
var GG_LOV=[];

var CMDT_COST_CMPR=[];
var CMDT_RAIL_COST=[];
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
var DVSN_PERF_TNGE_LY=[];

function fetchPlanData(date,inpt_ctgr)
{
	var zone=$("#selZone").val();
	var zone1=zone;
	if(zone1=='')
	{
		zone1='ALL (IR)';
	}
	var dvsn=$("#selDvsn").val();
	var cmdt=$("#selCmdt").val();
	var mnth1=$("#txtMnth").val();
	var mnth='';
	if(mnth1!="")
	{
		mnth=mnth1.split("-")[1]+"-"+mnth1.split("-")[0];
	}
	var headcaption='';
	if(dvsn!="")
	{
		headcaption='Zone:&nbsp;<span class="badge badge-warning" style="font-weight:13px;">'+zone1+'</span>&nbsp;&nbsp;Division;&nbsp;<span class="badge badge-warning" style="font-weight:13px;">'+dvsn+'</span>';
	}
	else
	{
		headcaption='Zone:&nbsp;<span class="badge badge-warning" style="font-weight:13px;">'+zone1+'</span>';
	}
	if(cmdt!="")
	{
		headcaption+='&nbsp;&nbsp;Commodity:&nbsp;<span class="badge badge-warning" style="font-weight:13px;">'+cmdt+'</span>';
	}
	headcaption+='&nbsp;&nbsp;Month:&nbsp;<span class="badge badge-warning" style="font-weight:13px;">'+mnth+'</span>';
	$("#pHead").show();
	$("#pHead").html(headcaption);

	var myurl="/RailSAHAY/AcqnPlanMIS";
	var htmlstr='';
	$.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'FETCH_PLAN_DATA',Ctgr:inpt_ctgr, Zone:zone, Dvsn:dvsn, Cmdt: cmdt,Mnth:mnth},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="S")
            {        
	            switch (inpt_ctgr)
	            {
				case 'CMDT_RAIL_COST' :
                                    htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;Overview of all freight commodities and Comparison of costs with other modes</p>';
                                    htmlstr+='<table id="example1" class="table table-mis table-striped table-bordered">';
                                    htmlstr+='<thead>';
                                    htmlstr+='<tr>';
                                    htmlstr+='<th rowspan="2">Zone</th>';
                                    htmlstr+='<th rowspan="2">Division</th>';
                                    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
                                    htmlstr+='<th rowspan="2">Commodity</th>';
                                    htmlstr+='<th rowspan="2">Originating From</th>';
                                    htmlstr+='<th rowspan="2">Total Vol of Freight being generated across all modes (MT)</th>';
                                    htmlstr+='<th rowspan="2">Indicative %age of Traffic (Rail) </th>';
                                    htmlstr+='<th colspan="15">Distance wise Transportation Costs (Rail Per Ton Km) [Full Rake Load/Piecemeal Load]</th>';
				    htmlstr+='<th rowspan="2">Addl First Mile/Last Mile Cost (Ex-Rail Per Ton Km)</th>';
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
					    
                                            htmlstr+='<tr><td>'+CMDT_RAIL_COST[i][0]+'</td><td>'+CMDT_RAIL_COST[i][1]+'</td><td>'+CMDT_RAIL_COST[i][2]+'</td><td>'+CMDT_RAIL_COST[i][3].substring(CMDT_RAIL_COST[i][3].indexOf("-")+1)+'</td><td>'+CMDT_RAIL_COST[i][4]+'</td><td>'+CMDT_RAIL_COST[i][5]+'</td><td>'+CMDT_RAIL_COST[i][6]+'%</td><td>'+CMDT_RAIL_COST[i][7]+'</td><td>'+CMDT_RAIL_COST[i][8]+'</td><td>'+CMDT_RAIL_COST[i][9]+'</td><td>'+CMDT_RAIL_COST[i][10]+'</td><td>'+CMDT_RAIL_COST[i][11]+'</td><td>'+CMDT_RAIL_COST[i][12]+'</td><td>'+CMDT_RAIL_COST[i][13]+'</td><td>'+CMDT_RAIL_COST[i][14]+'</td><td>'+CMDT_RAIL_COST[i][15]+'</td><td>'+CMDT_RAIL_COST[i][16]+'</td><td>'+CMDT_RAIL_COST[i][17]+'</td><td>'+CMDT_RAIL_COST[i][18]+'</td><td>'+CMDT_RAIL_COST[i][19]+'</td><td>'+CMDT_RAIL_COST[i][20]+'</td><td>'+CMDT_RAIL_COST[i][21]+'</td><td>'+CMDT_RAIL_COST[i][22]+'</td></tr>';
                                    }
									
                                    htmlstr+='</table>';
                                    $("#step1").html(htmlstr);   
                                    printTable("example1");
                                    break;
		       			case 'CMDT_ROAD_COST' :
			       		htmlstr='<table id="example2" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">Industry of Freight Generation</th>';
								    htmlstr+='<th rowspan="2">Commodity</th>';
								    htmlstr+='<th colspan="15">Distance wise Transportation Costs (Road Per Ton Km)</th>';
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
					    
                                        htmlstr+='<tr><td>'+CMDT_ROAD_COST[i][0]+'</td><td>'+CMDT_ROAD_COST[i][1]+'</td><td>'+CMDT_ROAD_COST[i][2]+'</td><td>'+CMDT_ROAD_COST[i][3].substring(CMDT_ROAD_COST[i][3].indexOf("-")+1)+'</td><td>'+CMDT_ROAD_COST[i][4]+'</td><td>'+CMDT_ROAD_COST[i][5]+'</td><td>'+CMDT_ROAD_COST[i][6]+'</td><td>'+CMDT_ROAD_COST[i][7]+'</td><td>'+CMDT_ROAD_COST[i][8]+'</td><td>'+CMDT_ROAD_COST[i][9]+'</td><td>'+CMDT_ROAD_COST[i][10]+'</td><td>'+CMDT_ROAD_COST[i][11]+'</td><td>'+CMDT_ROAD_COST[i][12]+'</td><td>'+CMDT_ROAD_COST[i][13]+'</td><td>'+CMDT_ROAD_COST[i][14]+'</td><td>'+CMDT_ROAD_COST[i][15]+'</td><td>'+CMDT_ROAD_COST[i][16]+'</td><td>'+CMDT_ROAD_COST[i][17]+'</td><td>'+CMDT_ROAD_COST[i][18]+'</td></tr>';
                                    }
								
					htmlstr+='</table>';
					$("#step2").html(htmlstr);
                                        printTable("example2");
					break;
		       			case 'CNSR_WISE_INFO' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Granular information Consignor Wise with the name of Industry/consignor</p>';
			       				htmlstr+='<table id="example3" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of industries/units in area transporting freight</th>';
								    htmlstr+='<th>Commodity</th>';
								    htmlstr+='<th>Traffic Type</th>';
								    htmlstr+='<th>Stock Type</th>';
								    htmlstr+='<th>Total volume freight being generated across all modes (MT)</th>';
								    htmlstr+='<th>Indicative percentage of traffic moving by Rail</th>';
								    htmlstr+='<th>Broad assessment of reason for competitors advantage</th>';
								    htmlstr+='<th class="other-col">Detail of OTHER Reason</th>';
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

									htmlstr+='<tr><td>'+CNSR_WISE_INFO[i][0]+'</td><td>'+CNSR_WISE_INFO[i][1]+'</td><td>'+CNSR_WISE_INFO[i][2]+'</td><td>'+CNSR_WISE_INFO[i][3]+'</td><td>'+CNSR_WISE_INFO[i][5]+'</td><td>'+CNSR_WISE_INFO[i][6]+'</td><td>'+fixDecimal(CNSR_WISE_INFO[i][7])+'</td><td>'+fixDecimal(CNSR_WISE_INFO[i][8])+'</td><td>'+CNSR_WISE_INFO[i][9]+showOtherVal(CNSR_WISE_INFO[i][10])+'</td><td class="other-col">'+CNSR_WISE_INFO[i][10]+'</td></tr>'
								} 
								htmlstr+='</table>';
							$("#step3").html(htmlstr);	
							printTable("example3");
                                                        break;
		       			case 'MKTG_STRT_BULK' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Building a marketing strategy for Bulk and non-Bulk commodities</p>';
			       				htmlstr+='<table id="example4" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th colspan="7">Proposed Additional traffic for the year ending March 31, 2022</th>';
								htmlstr+='</tr>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Name of the Commodity</th>';
								    htmlstr+='<th>(Bulk/ Non Bulk/ Parcel/ Goods/ Container)</th>';
								    htmlstr+='<th>Originating Station</th>';
								    htmlstr+='<th>Destination Station</th>';
								    htmlstr+='<th>Volume Expected Upto March 31, 2022 (in MT)</th>';
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
									htmlstr+='<tr><td>'+MKTG_STRT_BULK[i][0]+'</td><td>'+MKTG_STRT_BULK[i][1]+'</td><td>'+MKTG_STRT_BULK[i][2]+'</td><td>'+MKTG_STRT_BULK[i][3]+'</td><td>'+MKTG_STRT_BULK[i][4]+'</td><td>'+MKTG_STRT_BULK[i][5]+'</td><td>'+MKTG_STRT_BULK[i][6]+'</td><td>'+MKTG_STRT_BULK[i][7]+'</td><td>'+MKTG_STRT_BULK[i][8]+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step4").html(htmlstr);	
							printTable("example4");
                                                        break;

		       			case 'AGMT_LDNG1' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Action Plan to augment loading and diversify commodity basket</p>';
			       				htmlstr+='<table id="example5" class="table table-mis table-striped table-bordered">';
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
								    htmlstr+='<th>Cost Involved</th>';
								    htmlstr+='<th>Volume Targeted (MT)</th>';
								    htmlstr+='<th>Revenue Targeted (In Cr)</th>';
								    htmlstr+='<th>Handholding needed from Railway Board</th>';
								htmlstr+='</tr>';
								htmlstr+='</thead>';                   							  
								for(var i=0;i<obj.DataList1.length;i++)
								{
									AGMT_LDNG1[i]=new Array(16);	
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
									AGMT_LDNG1[i][15]=obj.DataList1[i].Val16;
									var link='';
									if(AGMT_LDNG1[i][12]!="")
									{
										link=AGMT_LDNG1[i][13]+'<br/><a href="javascript:void(0)" class="download-file-link" onclick="downloadProposalFile(\''+AGMT_LDNG1[i][15]+'\');">View Proposal</a>';
									}
									var actnplandesc=fetchCodeDesc('AP',AGMT_LDNG1[i][6]);

									htmlstr+='<tr><td>'+AGMT_LDNG1[i][0]+'</td><td>'+AGMT_LDNG1[i][1]+'</td><td>'+AGMT_LDNG1[i][2]+'</td><td>'+AGMT_LDNG1[i][3]+'</td><td>'+AGMT_LDNG1[i][4]+'</td><td>'+AGMT_LDNG1[i][5]+'</td><td>'+actnplandesc+showOtherVal(AGMT_LDNG1[i][7])+'</td><td class="other-col">'+AGMT_LDNG1[i][7]+'</td><td>'+AGMT_LDNG1[i][8]+'</td><td>'+AGMT_LDNG1[i][9]+'</td><td>'+AGMT_LDNG1[i][10]+'</td><td>'+AGMT_LDNG1[i][11]+'</td><td class="text-center">'+link+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step5a").html(htmlstr);	
							printTable("example5");
                                                        break;
		       			case 'FF_CRNT' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight forwarders and logistic partners (Current)</p>';
			       				htmlstr+='<table id="example6" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of Freight Forwarder</th>';
								    htmlstr+='<th>Contact Detail</th>';
								    htmlstr+='<th>Volume of Traffic handled (MT)</th>';
								    htmlstr+='<th>Revenue (In Cr)</th>';
								    htmlstr+='<th>Originating to Destination Station</th>';
								    htmlstr+='<th>Commodity Detail</th>';
								    htmlstr+='<th>Deployed On Date</th>';
								    htmlstr+='<th>Remarks/Any Other Assessment</th>';
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
									htmlstr+='<tr><td>'+FF_CRNT[i][0]+'</td><td>'+FF_CRNT[i][1]+'</td><td>'+FF_CRNT[i][2]+'</td><td>'+FF_CRNT[i][3]+'</td><td>'+FF_CRNT[i][4]+'</td><td>'+FF_CRNT[i][5]+'</td><td>'+FF_CRNT[i][6]+'</td><td>'+fetchNumCmdtName(FF_CRNT[i][7])+'</td><td>'+FF_CRNT[i][8]+'</td><td>'+FF_CRNT[i][9]+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step6").html(htmlstr);	
							printTable("example6");
                                                        break;
							
		       			case 'FF_PROPOSED' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp; Freight forwarders and logistic partners (Proposed)</p>';
			       				htmlstr+='<table id="example7" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Name of Freight Forwarder</th>';
								    htmlstr+='<th>Contact Detail</th>';
								    htmlstr+='<th>Volume of Traffic Proposed to be handled (MT)</th>';
								    htmlstr+='<th>Revenue (In Cr)</th>';
								    htmlstr+='<th>Originating to Destination Station</th>';
								    htmlstr+='<th>Commodity Detail</th>';
								    htmlstr+='<th>Progress Status</th>';
								    htmlstr+='<th>Remarks/Any Other Assessment</th>';
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
									htmlstr+='<tr><td>'+FF_PROPOSED[i][0]+'</td><td>'+FF_PROPOSED[i][1]+'</td><td>'+FF_PROPOSED[i][2]+'</td><td>'+FF_PROPOSED[i][3]+'</td><td>'+FF_PROPOSED[i][4]+'</td><td>'+FF_PROPOSED[i][5]+'</td><td>'+FF_PROPOSED[i][6]+'</td><td>'+fetchNumCmdtName(FF_PROPOSED[i][7])+'</td><td>'+FF_PROPOSED[i][8]+'</td><td>'+FF_PROPOSED[i][9]+'</td></tr>';
								}
								htmlstr+='</table>';
							$("#step7").html(htmlstr);	
							printTable("example7");
                                                        break;
							
		       			case 'INIT_TRFC_BOOST' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Cross learning from Zones: Initiatives taken to boost traffic in year ending March 31, 2021 and to date</p>';
			       				htmlstr+='<table id="example8" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th rowspan="2">Zone</th>';
								    htmlstr+='<th rowspan="2">Division</th>';
								    htmlstr+='<th rowspan="2">List of Initiatives Taken</th>';
								    htmlstr+='<th rowspan="2">Commodity</th>';
								    htmlstr+='<th colspan="2">Increase in traffic</th>';
								    htmlstr+='<th rowspan="2">Remarks/Any other assessment</th>';
								htmlstr+='</tr>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Additional Loading (MT)</th>';
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
									htmlstr+='<tr><td>'+INIT_TRFC_BOOST[i][0]+'</td><td>'+INIT_TRFC_BOOST[i][1]+'</td><td>'+INIT_TRFC_BOOST[i][2]+'</td><td>'+INIT_TRFC_BOOST[i][3]+'</td><td>'+INIT_TRFC_BOOST[i][4]+'</td><td>'+INIT_TRFC_BOOST[i][5]+'</td><td>'+INIT_TRFC_BOOST[i][6]+'</td></tr>';
								}
								htmlstr+='</table>';
							$("#step8").html(htmlstr);	
							printTable("example8");
                                                        break;
							
		       			case 'SWOT_ANLY' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  SWOT Analysis</p>';
			       				htmlstr+='<table id="example9" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
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
									htmlstr+='<tr><td>'+SWOT_ANLY[i][0]+'</td><td>'+SWOT_ANLY[i][1]+'</td><td>'+strg+showOtherVal(SWOT_ANLY[i][4])+'</td><td class="other-col">'+SWOT_ANLY[i][4]+'</td><td>'+SWOT_ANLY[i][5]+'</td><td>'+weak+showOtherVal(SWOT_ANLY[i][7])+'</td><td class="other-col">'+SWOT_ANLY[i][7]+'</td><td>'+SWOT_ANLY[i][8]+'</td><td>'+oprt+showOtherVal(SWOT_ANLY[i][10])+'</td><td class="other-col">'+SWOT_ANLY[i][10]+'</td><td>'+SWOT_ANLY[i][11]+'</td><td>'+thrt+showOtherVal(SWOT_ANLY[i][13])+'</td><td class="other-col">'+SWOT_ANLY[i][13]+'</td><td>'+SWOT_ANLY[i][14]+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step9").html(htmlstr);	
							printTable("example9");
                                                        break;
							
		       			case 'INDS_OUTREACH' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Industry Outreach for Zone/Division</p>';
			       				htmlstr+='<table id="example10" class="table table-mis table-striped table-bordered">';
								htmlstr+='<thead>';
								htmlstr+='<tr>';
								    htmlstr+='<th>Zone</th>';
								    htmlstr+='<th>Division</th>';
								    htmlstr+='<th>Category</th>';
								    htmlstr+='<th>Industry</th>';
								    htmlstr+='<th>Commodity</th>';
								    htmlstr+='<th>Additional Revenue Expected (In Cr.)</th>';
								    htmlstr+='<th>Additional Loading Expected in the year (in MT)</th>';
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
									htmlstr+='<tr><td>'+INDS_OUTREACH[i][0]+'</td><td>'+INDS_OUTREACH[i][1]+'</td><td>'+INDS_OUTREACH[i][2]+'</td><td>'+INDS_OUTREACH[i][3]+'</td><td>'+INDS_OUTREACH[i][4]+'</td><td>'+INDS_OUTREACH[i][5]+'</td><td>'+INDS_OUTREACH[i][6]+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step10").html(htmlstr);	
							printTable("example10");
                                                        break;							
							
		       			case 'RAIL_COEF_BOOST' :
			       		htmlstr='<p class="tab-head-caption"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;&nbsp;  Target Areas for Rail Co-efficient Boost</p>';
			       				htmlstr+='<table id="example11" class="table table-mis table-striped table-bordered">';
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
								htmlstr+='</tr>';
								htmlstr+='</thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									RAIL_COEF_BOOST[i]=new Array(9);	
									RAIL_COEF_BOOST[i][0]=obj.DataList1[i].Val1;
									RAIL_COEF_BOOST[i][1]=obj.DataList1[i].Val2;
									RAIL_COEF_BOOST[i][2]=obj.DataList1[i].Val3;
									RAIL_COEF_BOOST[i][3]=obj.DataList1[i].Val4;
									RAIL_COEF_BOOST[i][4]=obj.DataList1[i].Val5;
									RAIL_COEF_BOOST[i][5]=obj.DataList1[i].Val6;
									RAIL_COEF_BOOST[i][6]=obj.DataList1[i].Val7;
									RAIL_COEF_BOOST[i][7]=obj.DataList1[i].Val8;
									RAIL_COEF_BOOST[i][8]=obj.DataList1[i].Val9;

									htmlstr+='<tr><td>'+RAIL_COEF_BOOST[i][0]+'</td><td>'+RAIL_COEF_BOOST[i][1]+'</td><td style="background:#ccffcc;">'+RAIL_COEF_BOOST[i][2]+showOtherVal(RAIL_COEF_BOOST[i][3])+'</td><td style="background:#ccffcc;" class="other-col">'+RAIL_COEF_BOOST[i][3]+'</td><td style="background:#fff0b3;">'+RAIL_COEF_BOOST[i][4]+showOtherVal(RAIL_COEF_BOOST[i][5])+'</td><td style="background:#fff0b3;" class="other-col">'+RAIL_COEF_BOOST[i][5]+'</td><td style="background:#ffcccc;">'+RAIL_COEF_BOOST[i][6]+showOtherVal(RAIL_COEF_BOOST[i][7])+'</td><td style="background:#ffcccc;" class="other-col">'+RAIL_COEF_BOOST[i][7]+'</td></tr>';
								} 
								htmlstr+='</table>';
							$("#step11").html(htmlstr);	
							printTable("example11");
                                                        break;
		       			case 'ZONL_PERF' :
								console.log("Inside Zonal Perf");
								var htmlzonlstats='<table id="example" class="table table-striped table-mis"><thead><tr><th rowspan="2">Zone</th><th colspan="3">Revenue (In Rs Crores)</th><th colspan="3">Loading (In Million Tonnes)</th><th rowspan="2">&nbsp;</th></tr><tr><th>Last Year</th><th>Current Year Projection</th><th>Variation</th><th>Last Year</th><th>Current Year Projection</th><th>Variation</th></tr></thead>';
								for(var i=0;i<obj.DataList1.length;i++)
								{
									ZONL_PERF[i]=new Array(7);	
									ZONL_PERF[i][0]=obj.DataList1[i].Val1;
									ZONL_PERF[i][1]=obj.DataList1[i].Val2;
									ZONL_PERF[i][2]=obj.DataList1[i].Val3;
									ZONL_PERF[i][3]=obj.DataList1[i].Val4;
									ZONL_PERF[i][4]=obj.DataList1[i].Val5;
									ZONL_PERF[i][5]=obj.DataList1[i].Val6;
									ZONL_PERF[i][6]=obj.DataList1[i].Val7;
									var crntrevn=ZONL_PERF[i][2];
									var prevrevn=ZONL_PERF[i][4];
									var crntldng=ZONL_PERF[i][3];
									var prevldng=ZONL_PERF[i][5];

										if(crntrevn=="")
										{
											htmlzonlstats+='<tr><td>'+ZONL_PERF[i][0]+'</td><td>'+fixDecimal(ZONL_PERF[i][4])+'</td><td>'+fixDecimal(ZONL_PERF[i][2])+'</td><td>&nbsp;</td><td>'+fixDecimal(ZONL_PERF[i][5])+'</td><td>'+fixDecimal(ZONL_PERF[i][3])+'</td><td>&nbsp;</td></tr>';
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
											htmlzonlstats+='<tr><td>'+ZONL_PERF[i][0]+'</td><td>'+fixDecimal(ZONL_PERF[i][4])+'</td><td>'+fixDecimal(ZONL_PERF[i][2])+'</td><td>'+revn1+'</td><td>'+fixDecimal(ZONL_PERF[i][5])+'</td><td>'+fixDecimal(ZONL_PERF[i][3])+'</td><td>'+ldng1+'</td></tr>';

										}
								}
								htmlzonlstats+='</table>';
								$("#step0").html(htmlzonlstats);
                                                                //alert("zonal");
                                                                //printTable("example");
                                                                //alert("zonal1");
							break;
		       			case 'DVSN_PERF' :
								for(var i=0;i<obj.DataList1.length;i++)
								{
									DVSN_PERF[i]=new Array(7);	
									DVSN_PERF[i][0]=obj.DataList1[i].Val1;
									DVSN_PERF[i][1]=obj.DataList1[i].Val2;
									DVSN_PERF[i][2]=obj.DataList1[i].Val3;
									DVSN_PERF[i][3]=obj.DataList1[i].Val4;
									DVSN_PERF[i][4]=obj.DataList1[i].Val5;
									DVSN_PERF[i][5]=obj.DataList1[i].Val6;
									DVSN_PERF[i][6]=obj.DataList1[i].Val7;

									$("#txtProjRevn").val(DVSN_PERF[i][2]);
									$("#txtProjLdng").val(DVSN_PERF[i][3]);
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
									$("#txtZonlPerfRowId").val(DVSN_PERF[i][6]);					
			
								}
								$(".btn-saveperf").hide();
								$(".btn-editperf").show();
							break;

						}

                $(".table-mis").dataTable().fnDestroy();
		$(".table-mis").DataTable();

            }
            else
            {
                trxnFailed("Failed to Fetch Data! "+obj.ErorMesg);
            }
	}
	});	
}
function refreshData()
{

	$("#dataGrid").show();
	$("#divDvsnPerf").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');
	var selzone=$("#selZone").val();
    fetchPlanData('','ZONL_PERF');
    fetchPlanData('','CMDT_RAIL_COST');
    fetchPlanData('','CMDT_ROAD_COST');
    fetchPlanData('','CNSR_WISE_INFO');
    fetchPlanData('','MKTG_STRT_BULK');
    fetchPlanData('','AGMT_LDNG1');
    fetchPlanData('','FF_CRNT');
    fetchPlanData('','FF_PROPOSED');
    fetchPlanData('','INIT_TRFC_BOOST');
    fetchPlanData('','SWOT_ANLY');
    fetchPlanData('','INDS_OUTREACH');
    fetchPlanData('','RAIL_COEF_BOOST');
}
$(document).ready(function()
{
    fetchLOVs();
    refreshData();
});
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
$(document).ready(function(){
	$("#pHead").hide();
	$("#dataGrid").hide();
	$('#selDvsn').find('option').remove().end();
               $('#selDvsn').append($('<option>',{ 
		       	        value: '',
                       	text: 'SELECT DIVISION'
                }));
		for(var i=0;i<aDvsn.length;i++)
		{
			var crntdvsn=aDvsn[i].substring(0,aDvsn[i].indexOf('-'));	
                                $('#selDvsn').append($('<option>', 
				{
                                    	value: crntdvsn,
                                    	text: aDvsn[i]
                                }));
			}
	$("#selZone").change(function()
	{
		$("#pHead").hide();
		var crntzone=$("#selZone").val();
		$('#selDvsn').find('option').remove().end();
		$('#selDvsn').append($('<option>', {
                                    	value: '',
                                    	text: 'SELECT DIVISION'
                                }));	
		if(crntzone=='')
		{
			
			for(var i=0;i<aDvsn.length;i++)
			{
				var crntdvsn=aDvsn[i].substring(0,aDvsn[i].indexOf('-'));	
                                $('#selDvsn').append($('<option>', 
				                {
                                    	value: crntdvsn,
                                    	text: aDvsn[i]
                                }));
			}
		}
		else
		{
		for(var i=0;i<aZoneDvsn.length;i++)
		{
			var crntdvsn=aZoneDvsn[i].split("-");
			if(crntdvsn[0]==crntzone)
			{				
                                $('#selDvsn').append($('<option>', {
                                    	value: crntdvsn[1],
                                    	text: crntdvsn[2]
                                }));
			}
		}
		}
	});
	
		$('#selCmdt').append($('<option>', {
                                    	value: '',
                                    	text: 'SELECT COMMODITY'
                                }));
		for(var i=0;i<aRakeCmdt.length;i++)
		{
			var crntcmdt=aRakeCmdt[i].substring(0,aRakeCmdt[i].indexOf("-"));
                                $('#selCmdt').append($('<option>', {
                                    	value: crntcmdt,
                                    	text: aRakeCmdt[i]
                                }));
		}
/*
		jQuery('#selCmdt').multiselect({
		    columns: 1,
		    placeholder: 'SELECT COMMODITY',
		});
*/
		let date = new Date();
		let month = `${date.getMonth() + 1}`.padStart(0, 2);
		let year = date.getFullYear();
		console.log("Year to be fetched:"+`${year}-${month}`);
		$('#txtMnth').val(`${year}-${month}`);

});


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
function downloadProposalFile(fileid)
{
        $("#FileId").val(fileid);
        $("#frmGetFile").submit();
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