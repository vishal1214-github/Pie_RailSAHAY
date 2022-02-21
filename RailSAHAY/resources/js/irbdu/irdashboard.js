function refreshData() {
    var zone=$("#selZone").val();
    var cmdt=$("#selCmdt").val();
    fetchIRDBData(zone,cmdt);
}
function fetchIRDBData(zone,cmdt)
{
	var myurl="/RailSAHAY/AcqnPlanMIS";
        
        $("#divYrlyLdng").html('<div class="spinner-border text-danger"></div>');
        $("#divActnPlan").html('<div class="spinner-border text-danger"></div>');
        $("#divInit").html('<div class="spinner-border text-danger"></div>');
        $("#divIndsOut").html('<div class="spinner-border text-danger"></div>');
        $("#divRailCoef").html('<div class="spinner-border text-danger"></div>');
                    
        var htmlyrlyldng='<table class="table table-striped table-mis1"><thead><tr><th rowspan="2">Zone</th><th colspan="3">Revenue (In Cr)</th><th colspan="4">Loading (In MT)</th></tr><tr><th>Last Year</th><th>Current Year Projection</th><th>Variation %</th><th>Last Year</th><th>Current Target</th><th>Current Year Projection</th><th>Variation %</th></tr></thead>';
        var htmlactnplan='<table class="table table-striped table-mis1"><thead><tr><th>Zone</th><th>Cost Involved</th><th>Target Revenue (Cr)</th><th>Target Loading (MT)</th></tr></thead>';
        var htmlinit='<table class="table table-striped table-mis1"><thead><tr><th rowspan="2">Zone</th><th colspan="2">Increase in Loading</th></tr><tr><th>Revenue (Cr)</th><th>Loading (MT)</th></tr></thead>';
        var htmlindsout='<table class="table table-striped table-mis1"><thead><tr><th rowspan="2">Zone</th><th colspan="2">Expected Additional Loading</th></tr><tr><th>Revenue (Cr)</th><th>Loading (MT)</th></tr></thead>';
        var htmlrailcoef='<table id="railcoef" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Stock</th><th>Total Freight Generation (MT)</th><th>Rail Share (%)</th></tr></thead>';
	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'IR_DASHBOARD', Zone:zone, Cmdt:cmdt},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
                {
                    for(var i=0;i<obj.ZonlPerf.length;i++)
                    {
                        if(obj.ZonlPerf[i].Val2=="")
                        {
                            var crntrevn=obj.ZonlPerf[i].Val3;
                            var prevrevn=obj.ZonlPerf[i].Val5;
                            var crntldng=obj.ZonlPerf[i].Val4;
                            var prevldng=obj.ZonlPerf[i].Val6;
        
                                if(crntrevn=="")
                                {
                                        htmlyrlyldng+='<tr><td>'+obj.ZonlPerf[i].Val1+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val5)+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val3)+'</td><td>&nbsp;</td><td>'+fixDecimal(obj.ZonlPerf[i].Val6)+'</td><td style="background:#d1d1e0;">'+fixDecimal(obj.ZonlPerf[i].Val7)+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val4)+'</td><td>&nbsp;</td></tr>';
                                }
                                else
                                {
                                        var revn=(((Number(crntrevn)-Number(prevrevn))*100)/Number(prevrevn)).toFixed(2);
                                        var ldng=(((Number(obj.ZonlPerf[i].Val4)-Number(obj.ZonlPerf[i].Val6))*100)/Number(obj.ZonlPerf[i].Val6)).toFixed(2);
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
                                        htmlyrlyldng+='<tr><td><a href="javascript:void(0)" style="font-weight:600;color:#0059b3 !important;" data-toggle="collapse" data-target="#divSmry'+obj.ZonlPerf[i].Val1+'" aria-expanded="false" aria-controls="collapseExample">'+obj.ZonlPerf[i].Val1+'</a></td><td>'+fixDecimal(obj.ZonlPerf[i].Val5)+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val3)+'</td><td>'+revn1+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val6)+'</td><td style="background:#d1d1e0;">'+fixDecimal(obj.ZonlPerf[i].Val7)+'</td><td>'+fixDecimal(obj.ZonlPerf[i].Val4)+'</td><td>'+ldng1+'</td></tr>';                                       
                                        htmlyrlyldng+='<tr><td colspan="9" class="popup-row"><div class="collapse" class="card" id="divSmry'+obj.ZonlPerf[i].Val1+'"><div class="card-header head-lbl">Divisional Statistics</div><div class="card-body"><table class="table table-striped table-mis-popup"><thead><tr><th rowspan="2">Division</th><th colspan="3">Revenue (In Cr)</th><th colspan="4">Loading (In MT)</th></tr><tr><th>Last Year</th><th>Current Year Projection</th><th>Variation %</th><th>Last Year</th><th>Current Target</th><th>Current Year Projection</th><th>Variation %</th></tr></thead>';
                                         for(var j=0;j<obj.ZonlPerf.length;j++)
                                            {
                                                if((obj.ZonlPerf[j].Val1==obj.ZonlPerf[i].Val1) && (obj.ZonlPerf[j].Val2!=""))
                                                {                                           
                                                    var crntrevn=obj.ZonlPerf[j].Val3;
                                                    var prevrevn=obj.ZonlPerf[j].Val5;
                                                    var crntldng=obj.ZonlPerf[j].Val4;
                                                    var prevldng=obj.ZonlPerf[j].Val6;
                    
                                                    if(crntrevn=="")
                                                    {
                                                            htmlyrlyldng+='<tr><td>'+obj.ZonlPerf[j].Val2+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val5)+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val3)+'</td><td>&nbsp;</td><td>'+fixDecimal(obj.ZonlPerf[j].Val6)+'</td><td style="background:#d1d1e0;">'+fixDecimal(obj.ZonlPerf[j].Val7)+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val3)+'</td><td>&nbsp;</td></tr>';
                                                    }
                                                    else
                                                    {
                                                            var revn=(((Number(crntrevn)-Number(prevrevn))*100)/Number(prevrevn)).toFixed(2);
                                                            var ldng=(((Number(obj.ZonlPerf[j].Val4)-Number(obj.ZonlPerf[j].Val6))*100)/Number(obj.ZonlPerf[j].Val6)).toFixed(2);
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
                                                            htmlyrlyldng+='<tr><td>'+obj.ZonlPerf[j].Val2+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val5)+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val3)+'</td><td>'+revn1+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val6)+'</td><td style="background:#d1d1e0;">'+fixDecimal(obj.ZonlPerf[j].Val7)+'</td><td>'+fixDecimal(obj.ZonlPerf[j].Val4)+'</td><td>'+ldng1+'</td></tr>';                                       
                                                    }  
                                                }
                                            }
                                    htmlyrlyldng+='</table></div></div></td></tr>';
                                }
                                
                        }
                    }
                    htmlyrlyldng+='</table>';
                    
                    for(var i=0;i<obj.ActnPlan.length;i++)
                    {
                        if(obj.ActnPlan[i].Val2=="")
                        {
                            htmlactnplan+='<tr><td><a href="javascript:void(0)" style="font-weight:600;color:#0059b3 !important;" data-toggle="collapse" data-target="#divAcnPlan'+obj.ActnPlan[i].Val1+'" aria-expanded="false" aria-controls="collapseExample">'+obj.ActnPlan[i].Val1+'</a></td><td>'+fixDecimal(obj.ActnPlan[i].Val4)+'</td><td>'+fixDecimal(obj.ActnPlan[i].Val5)+'</td><td>'+fixDecimal(obj.ActnPlan[i].Val6)+'</td></tr>';
                            htmlactnplan+='<tr><td colspan="4" class="popup-row"><div class="collapse" class="card" id="divAcnPlan'+obj.ActnPlan[i].Val1+'"><div class="card-header head-lbl">Divisional Statistics</div><div class="card-body"><table class="table table-striped table-mis-popup"><thead><tr><th>Division</th><th>Commodity</th><th>Cost</th><th>Target Revenue</th><th>Target Loading</th></tr></thead>';
                            for(var j=0;j<obj.ActnPlan.length;j++)
                            {
                                if((obj.ActnPlan[j].Val1==obj.ActnPlan[i].Val1) && (obj.ActnPlan[j].Val2!=""))
                                { 
                                    htmlactnplan+='<tr><td>'+obj.ActnPlan[j].Val2+'</td><td>'+obj.ActnPlan[j].Val3+'</td><td>'+fixDecimal(obj.ActnPlan[j].Val4)+'</td><td>'+fixDecimal(obj.ActnPlan[j].Val5)+'</td><td>'+fixDecimal(obj.ActnPlan[j].Val6)+'</td></tr>';
                                }
                            }
                            htmlactnplan+='</table></div></div></td></tr>';
                        }
                    }
                    htmlactnplan+='</tr></table>';
                    
                    for(var i=0;i<obj.Init.length;i++)
                    {
                        if(obj.Init[i].Val2=="")
                        {
                            htmlinit+='<tr><td><a href="javascript:void(0)" style="font-weight:600;color:#0059b3 !important;" data-toggle="collapse" data-target="#divInit'+obj.Init[i].Val1+'" aria-expanded="false" aria-controls="collapseExample">'+obj.Init[i].Val1+'</a></td><td>'+fixDecimal(obj.Init[i].Val4)+'</td><td>'+fixDecimal(obj.Init[i].Val5)+'</td></tr>';
                            htmlinit+='<tr><td colspan="4" class="popup-row"><div class="collapse" class="card" id="divInit'+obj.Init[i].Val1+'"><div class="card-header head-lbl">Divisional Statistics</div><div class="card-body"><table class="table table-striped table-mis-popup"><thead><tr><th>Division</th><th>Commodity</th><th>Increase in Revenue</th><th>Increase in Loading</th></tr></thead>';
                            for(var j=0;j<obj.Init.length;j++)
                            {
                                if((obj.Init[j].Val1==obj.Init[i].Val1) && (obj.Init[j].Val2!=""))
                                { 
                                    htmlinit+='<tr><td>'+obj.Init[j].Val2+'</td><td>'+obj.Init[j].Val3+'</td><td>'+fixDecimal(obj.Init[j].Val4)+'</td><td>'+fixDecimal(obj.Init[j].Val5)+'</td></tr>';
                                }
                            }
                            htmlinit+='</table></div></div></td></tr>';
                        }
                    }
                    htmlinit+='</tr></table>';
                    
                    for(var i=0;i<obj.IndsOut.length;i++)
                    {
                        if(obj.IndsOut[i].Val2=="")
                        {
                            htmlindsout+='<tr><td><a href="javascript:void(0)" style="font-weight:600;color:#0059b3 !important;" data-toggle="collapse" data-target="#divIndsOut'+obj.IndsOut[i].Val1+'" aria-expanded="false" aria-controls="collapseExample">'+obj.IndsOut[i].Val1+'</a></td><td>'+fixDecimal(obj.IndsOut[i].Val4)+'</td><td>'+fixDecimal(obj.IndsOut[i].Val5)+'</td></tr>';
                            htmlindsout+='<tr><td colspan="4" class="popup-row"><div class="collapse" class="card" id="divIndsOut'+obj.IndsOut[i].Val1+'"><div class="card-header head-lbl">Divisional Statistics</div><div class="card-body"><table class="table table-striped table-mis-popup"><thead><tr><th>Division</th><th>Commodity</th><th>Expected Revenue</th><th>Expected Loading</th></tr></thead>';
                            for(var j=0;j<obj.IndsOut.length;j++)
                            {
                                if((obj.IndsOut[j].Val1==obj.IndsOut[i].Val1) && (obj.IndsOut[j].Val2!=""))
                                { 
                                    htmlindsout+='<tr><td>'+obj.IndsOut[j].Val2+'</td><td>'+obj.IndsOut[j].Val3+'</td><td>'+fixDecimal(obj.IndsOut[j].Val4)+'</td><td>'+fixDecimal(obj.IndsOut[j].Val5)+'</td></tr>';
                                }
                            }
                            htmlindsout+='</table></div></div></td></tr>';
                        }
                    }
                    htmlindsout+='</tr></table>';
                    
                    for(var i=0;i<obj.CmdtCoef.length;i++)
                    {
                        htmlrailcoef+='<tr><td>'+obj.CmdtCoef[i].Val1+'</td><td>'+obj.CmdtCoef[i].Val2+'</td><td>'+obj.CmdtCoef[i].Val3+'</td><td>'+obj.CmdtCoef[i].Val4+'</td><td>'+fixDecimal(obj.CmdtCoef[i].Val5)+'</td><td>'+fixDecimal(obj.CmdtCoef[i].Val6)+'</td></tr>';
                    }
                    htmlrailcoef+='</tr></table>';
                    
                    $("#divYrlyLdng").html(htmlyrlyldng);
                    $("#divActnPlan").html(htmlactnplan);
                    $("#divInit").html(htmlinit);
                    $("#divIndsOut").html(htmlindsout);
                    $("#divRailCoef").html(htmlrailcoef);
                    printTable("railcoef","BUSINESS DEVELOPMENT ANALYSIS: RAIL COEFFICIENT","ZONE: "+zone+", COMMODITY: "+cmdt);

                    $(".table-mis").dataTable();
		}
	}
	});

}
$(document).ready(function(){	
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
        let date = new Date();
        let month = `${date.getMonth() + 1}`.padStart(0, 2);
        let year = date.getFullYear();
        console.log("Year to be fetched:"+`${year}-${month}`);
        $('#txtMnth').val(`${year}-${month}`);
*/
 
});


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
