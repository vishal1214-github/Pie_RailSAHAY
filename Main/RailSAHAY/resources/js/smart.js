var Gs_Disclaim="";
$('.btn-fcty').prop('disabled', true);
function drawFctyChrt(agcont,trckcont,whcont,labcont)
{
	var config = {
		type: 'doughnut',
		data: {
			datasets: [{
				data: [
					agcont,
					trckcont,
					whcont,
					labcont
				],
				backgroundColor: [
					'#007bff',
					'#28a745',
					'#dc3545',
					'#ffc107'
				],
				label: 'Dataset 1'
			}],
			labels: [
				'AGGREGATORS',
				'TRUCKERS',
				'WAREHOUSES',
				'LABOUR'
			]
		},
		options: {
			responsive: true,
			events: ['click'],
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};

	var ctx = document.getElementById('emarket_chrt').getContext('2d');
	window.myDoughnut = new Chart(ctx, config);
	document.getElementById('emarket_chrt').onclick = function(evt)
	{
		var activePoints = window.myDoughnut.getElementsAtEvent(evt);

		if(activePoints.length > 0)
		{
		  var clickedElementindex = activePoints[0]["_index"];
		  var label = window.myDoughnut.data.labels[clickedElementindex];
		  var value = window.myDoughnut.data.datasets[0].data[clickedElementindex];
		  console.log(label+"   "+value);
		  fetchEMrktFctyList(label);
		}
	}
}
function getEMrktSmry()
{
	var myurl="/RailSAHAY/GG_AjaxUtil?Qry=EMRKT_SMRY";
	var agcont=0;
	var trckcont=0;
	var whcont=0;
	var labcont=0;
	var custcont=0;
	var trmlcont=0;
	var indcont=0;
	var compcont=0;

	$.ajax({
	  url : myurl,
	  type : "post",
	  async : true,
	  success : function(data) {
		var obj= JSON.parse(data);
		agcont=Number(obj.Aggregator);
		trckcont=Number(obj.Trucker);
		whcont=Number(obj.Warehouse);
		labcont=Number(obj.Labour);
		custcont=Number(obj.CustCont);
		trmlcont=Number(obj.TrmlCont);
		indcont=Number(obj.IndCont);
		compcont=Number(obj.CompCont);

		$("#spnAgCont").html(agcont+"");
		$("#spnTrckCont").html(trckcont+"");
		$("#spnWHCont").html(whcont+"");
		$("#spnLabCont").html(labcont+"");
		$("#spnCompCont").html(compcont+"");
		$("#spnIndCont").html(indcont+"");
		$("#divCustCont").html(custcont);
		$("#divTrmlCont").html(trmlcont);
		drawFctyChrt(agcont,trckcont,whcont,labcont);
		}
	});
}
function fetchEMrktFctyList(ctgr)
{
	var srvcctgr='';
	$("#fctySmryModal").modal('show');
	$("#divFctyList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	switch(ctgr)
	{
		case 'AGGREGATORS':
			srvcctgr='A';
			$("#headSMARTList").html("List of Terminals with AGGREGATORS Available");
			break;
		case 'TRUCKERS':
			srvcctgr='T';
			$("#headSMARTList").html("List of Terminals with TRUCKERS Available");
			break;
		case 'WAREHOUSES':
			srvcctgr='W';
			$("#headSMARTList").html("List of Terminals with WAREHOUSE Available");
			break;
		case 'LABOUR':
			srvcctgr='L';
			$("#headSMARTList").html("List of Terminals with LABOUR Available");
			break;
		case '':
			srvcctgr='';
			$("#headSMARTList").html("List of Terminals with Logistics Facilities Available");
			break;
	}

	var myurl="/RailSAHAY/GG_AjaxUtil?Qry=EMRKT_FCTY_LIST&Ctgr="+srvcctgr;
	var zone="";
	var dvsn="";
	var stat="";
	var dstt="";
	var sttncode="";
	var sttnname="";
	var agcont="";
	var trckcont=0;
	var whcont=0;
	var labcont=0;
	var htmlFctyList='';
	$.ajax({
	  url : myurl,
	  type : "post",
	  async : true,
	  success : function(data) {
		var obj= JSON.parse(data);
		Gs_Disclaim=obj.Disclaim;
		srvccall=obj.ServiceCall;
		dataflag=obj.DataFlag;
		htmlFctyList='';
		if(dataflag=="Y")
		{
			for(var i=0;i<obj.Data.length;i++)
			{
				zone=obj.Data[i].Zone;
				dvsn=obj.Data[i].Dvsn;
				stat=obj.Data[i].Stat;
				dstt=obj.Data[i].Dstt;
				sttncode=obj.Data[i].SttnCode;
				sttnname=obj.Data[i].SttnName;
				agcont=obj.Data[i].AgCont;
				trckcont=obj.Data[i].TrckCont;
				whcont=obj.Data[i].WHCont;
				labcont=obj.Data[i].LabCont;

				htmlFctyList+='<div class="w3-card-5"><table class="styletable">';
				htmlFctyList+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttncode+' ('+sttnname+')</p></th></tr>';
				htmlFctyList+='<tr><td class="lbl">District</td><td class="val">'+dstt+' ('+stat+')</td></tr>';
				htmlFctyList+='<tr><td class="lbl">Division</td><td class="val">'+dvsn+' ('+zone+')</td></tr>';
				htmlFctyList+='<tr><td class="lbl">Facilities</td><td class="val">';
				htmlFctyList+='<table class="tblFcty1"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">&nbsp;'+agcont+'</span></td>';
				htmlFctyList+='<td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">&nbsp;'+trckcont+'</span></td>';
				htmlFctyList+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">&nbsp;'+whcont+'</span></td>';
				htmlFctyList+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">&nbsp;'+labcont+'</span></td></tr></table></td></tr></table></div>';

			}
			htmlFctyList+='<br/><div class="alert alert-info"><p class="disclaimhead">Disclaimer</p><p class="disclaim">'+Gs_Disclaim+'</p></div>';
			$("#divFctyList").html(htmlFctyList);
		}
		},error: function (jqXHR, textStatus, errorThrown)
	    {
        	alert("error in fetching facilities list:"+errorThrown);
	    }
	});
  }
$(document).ready(function() {
	getEMrktSmry();
});
