var finltngestr='';
var finlfrgtstr='';
function fetchDvsnPerfStats()
{
	var zone=$("#selZone").val();
	var myurl="/RailSAHAY/AcqnPlanMIS";
	var htmlstr='';
	var dvsnlist=[];
	var dvsntngehtml=[];
	var dvsnfrgthtml=[];
	var divclss="";
	var htmlhead='<div class="container"><div class="row"><div class="col-lg-12 col-sm-12"><button class="btn btn-sm btn-light pull-right" onclick="showTngePerf();" ><i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;Tonnage-wise</button><button  onclick="showFrgtPerf();" class="btn btn-light btn-sm pull-right"><i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;Freight-wise</button></div></div></div><hr/><div class="container"><div class="row">';
	htmlstr+=htmlhead;
	finltngestr=htmlhead;
	finlfrgtstr=htmlhead;
	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'DVSN_PERF_STATS', Zone:zone},
		async : true,
		success : function(data) {
		console.log("Division Performance Statistics:"+data);
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
            {

		if(obj.DvsnList.length==2)
			divclss="col-lg-6";
		if(obj.DvsnList.length>=3)
			divclss="col-lg-4";

		for(var i=0;i<obj.DvsnList.length;i++)
		{
			dvsnlist[i]=obj.DvsnList[i].Dvsn;
			dvsntngehtml[i]='<div class='+divclss+' style="padding:10px;"><p class="dvsn-head">Division: '+dvsnlist[i]+'</p><table class="table table-striped table-bordered table-mis"><thead><tr><th rowspan="2">Month</th><th colspan="3">Tonnage (MT)</th></tr><tr><th>Current Year</th><th>Last Year</th><th>Variation %</th></tr></thead><tbody>';
			dvsnfrgthtml[i]='<div class='+divclss+' style="padding:10px;"><p class="dvsn-head">Division: '+dvsnlist[i]+'</p><table class="table table-striped table-bordered table-mis"><thead><tr><th rowspan="2">Month</th><th colspan="3">Freight (Crore)</th></tr><tr><th>Current Year</th><th>Last Year</th><th>Variation %</th></tr></thead><tbody>';
		}
		for(var j=0;j<dvsnlist.length;j++)
		{
			for(var i=0;i<obj.DvsnPerf.length;i++)
			{
				if(obj.DvsnPerf[i].Dvsn==dvsnlist[j])
				{
					if((obj.DvsnPerf[i].TngePctg).indexOf("-")>=0)
					{
						dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td></tr>';
					}
					else
					{
						if(obj.DvsnPerf[i].TngePctg=="")						
							dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-rise">&nbsp;</td></tr>';	
						else
							dvsntngehtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].TngeCY+'</td><td class="lyval">'+obj.DvsnPerf[i].TngeLY+'</td><td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].TngePctg+'</td></tr>';	
					}

					if((obj.DvsnPerf[i].FrgtPctg).indexOf("-")>=0)
					{
						dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-dip"><i class="fa fa-arrow-down" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td></tr>';
					}
					else
					{
						if(obj.DvsnPerf[i].FrgtPctg=="")
							dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-rise">&nbsp;</td></tr>';
						else
							dvsnfrgthtml[j]+='<tr><td class="mon">'+obj.DvsnPerf[i].Mnth+'</td><td class="cyval">'+obj.DvsnPerf[i].FrgtCY+'</td><td class="lyval">'+obj.DvsnPerf[i].FrgtLY+'</td><td class="pctg-var-rise"><i class="fa fa-arrow-up" aria-hidden="true"></i>&nbsp;'+obj.DvsnPerf[i].FrgtPctg+'</td></tr>';
					}
				}
			}
			dvsntngehtml[j]+='</tbody></table></div><div></div>';
			dvsnfrgthtml[j]+='</tbody></table></div><div></div>';
			finltngestr+=dvsntngehtml[j];
			finlfrgtstr+=dvsnfrgthtml[j];
		}
		$("#divDvsnPerf").html(finltngestr);
		$(".table-mis").dataTable( { "order": [],
					     "paging": false,
					     "bPaginate": false,
   					     "bFilter": false, //hide Search bar
    					     "bInfo": false, // hide showing entries
				} );
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
