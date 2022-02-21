function refreshData() {
    var zone=$("#selZone").val();
    var dvsn=$("#selDvsn").val();
    var cmdt=$("#selCmdt").val();
    var mnth=$("#txtMnth").val();
    console.log(mnth);
    fetchRailRoadCmpr(zone,dvsn,cmdt,mnth);
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
        
        if(GG_CrntZone=='')
        {
            $('#selDvsn').append($('<option>', {
                                    value: '',
                                    text: 'SELECT DIVISION'
                            }));
            for(var i=0;i<aDvsn.length;i++)
            {
                var crntdvsn=aDvsn[i].substring(0,aDvsn[i].indexOf("-"));
                $('#selDvsn').append($('<option>', {
                        value: crntdvsn,
                        text: aDvsn[i]
                }));
            }
        }
        else
        {
            $('#selDvsn').append($('<option>', {
                                    value: '',
                                    text: 'SELECT DIVISION (ALL)'
                            }));
            for(var i=0;i<aZoneDvsn.length;i++)
            {
                    var crntdvsn=aZoneDvsn[i].split("-");
                    if(crntdvsn[0]==GG_CrntZone)
                    {				
                            $('#selDvsn').append($('<option>', {
                                    value: crntdvsn[1],
                                    text: crntdvsn[1]+'-'+crntdvsn[2]
                            }));
                    }
            }
            if(GG_LognLocnFlag=="D") {
                $('#selDvsn option[value="'+GG_CrntLocn+'"]').attr("selected", "selected");
            }
        }
        $("#selZone").change(function()
	{
		var crntzone=$("#selZone").val();
		$('#selDvsn').find('option').remove().end();
		$('#selDvsn').append($('<option>', {
                                    	value: '',
                                    	text: 'SELECT DIVISION (ALL)'
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
                                    	text: crntdvsn[1]+'-'+crntdvsn[2]
                                }));
			}
		}
		}
	});

        let date = new Date();
        let month = `${date.getMonth() + 1}`.padStart(0, 2);
        let year = date.getFullYear();
        $('#txtMnth').val(`${year}-${month}`); 
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
function fetchRailRoadCmpr(zone,dvsn,cmdt,mnth)
{
    var myurl="/RailSAHAY/AcqnPlanMIS";        
    $("#divRailRoadCmpr").html('<div class="spinner-border text-danger"></div>');
    htmlstr='<table class="table table-mis1 table-striped"><thead><tr><th rowspan="2">Zone</th><th rowspan="2">Division</th><th rowspan="2">Commodity</th><th rowspan="2">Mode</th><th colspan="15">Rail Road Cost Comparison (Rate Per Ton Km) for Distance Slabs </th><th>Additional Costs (Per Ton)</th></tr><tr><th>0-100</th><th>100-200</th><th>200-300</th><th>300-400</th><th>400-500</th><th>500-600</th><th>600-700</th><th>700-800</th><th>800-900</th><th>900-1000</th><th>1000-1100</th><th>1100-1200</th><th>1200-1300</th><th>1300-1400</th><th>1400-1500</th><th>Incl. FMLM, Loading, Unloading etc.</th></tr>'            
    var roadcost=[];
    $.ajax({
            url : myurl,
            type : "post",
            data: {Optn:'RAIL_ROAD_COMPARISON',Zone:zone,Dvsn:dvsn,Cmdt:cmdt,Mnth:mnth},
            async : true,
            success : function(data) {
            console.log(data);
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="S")
            {
                var crntcmdt='';
                var prvscmdt='';
		var crntzone='';
		var prvszone='';
		var crntdvsn='';
		var prvsdvsn='';
                for(var i=0;i<obj.DataList1.length;i++) {
		    crntzone=obj.DataList1[i].Val1;
		    crntdvsn=obj.DataList1[i].Val2;
                    crntcmdt=obj.DataList1[i].Val3;
                    var ctgr='';
                    htmlstr+='<tr><td>'+obj.DataList1[i].Val1+'</td><td>'+obj.DataList1[i].Val2+'</td>';
                    if((crntcmdt==prvscmdt) && (crntzone==prvszone) && (crntdvsn==prvsdvsn))
		    {			
                        htmlstr+='<td>'+obj.DataList1[i].Val20+'</td><td>'+obj.DataList1[i].Val4+'<br/>'+fetchVarn(obj.DataList1[i].Val4,roadcost[0])+'</td><td>'+obj.DataList1[i].Val5+'<br/>'+fetchVarn(obj.DataList1[i].Val5,roadcost[1])+'</td><td>'+obj.DataList1[i].Val6+'<br/>'+fetchVarn(obj.DataList1[i].Val6,roadcost[2])+'</td><td>'+obj.DataList1[i].Val7+'<br/>'+fetchVarn(obj.DataList1[i].Val7,roadcost[3])+'</td><td>'+obj.DataList1[i].Val8+'<br/>'+fetchVarn(obj.DataList1[i].Val8,roadcost[4])+'</td><td>'+obj.DataList1[i].Val9+'<br/>'+fetchVarn(obj.DataList1[i].Val9,roadcost[5])+'</td><td>'+obj.DataList1[i].Val10+'<br/>'+fetchVarn(obj.DataList1[i].Val10,roadcost[6])+'</td><td>'+obj.DataList1[i].Val11+'<br/>'+fetchVarn(obj.DataList1[i].Val11,roadcost[7])+'</td><td>'+obj.DataList1[i].Val12+'<br/>'+fetchVarn(obj.DataList1[i].Val12,roadcost[8])+'</td><td>'+obj.DataList1[i].Val13+'<br/>'+fetchVarn(obj.DataList1[i].Val13,roadcost[9])+'</td><td>'+obj.DataList1[i].Val14+'<br/>'+fetchVarn(obj.DataList1[i].Val14,roadcost[10])+'</td><td>'+obj.DataList1[i].Val15+'<br/>'+fetchVarn(obj.DataList1[i].Val15,roadcost[11])+'</td><td>'+obj.DataList1[i].Val16+'<br/>'+fetchVarn(obj.DataList1[i].Val16,roadcost[12])+'</td><td>'+obj.DataList1[i].Val17+'<br/>'+fetchVarn(obj.DataList1[i].Val17,roadcost[13])+'</td><td>'+obj.DataList1[i].Val18+'<br/>'+fetchVarn(obj.DataList1[i].Val18,roadcost[14])+'</td><td>'+obj.DataList1[i].Val19+'</td></tr>';
		    }
                    else
                    {
                        roadcost=[];
                        htmlstr+='<td rowspan="3" style="vertical-align:middle;text-align:center;">'+obj.DataList1[i].Val3+'</td><td>'+obj.DataList1[i].Val20+'</td><td>'+obj.DataList1[i].Val4+'</td><td>'+obj.DataList1[i].Val5+'</td><td>'+obj.DataList1[i].Val6+'</td><td>'+obj.DataList1[i].Val7+'</td><td>'+obj.DataList1[i].Val8+'</td><td>'+obj.DataList1[i].Val9+'</td><td>'+obj.DataList1[i].Val10+'</td><td>'+obj.DataList1[i].Val11+'</td><td>'+obj.DataList1[i].Val12+'</td><td>'+obj.DataList1[i].Val13+'</td><td>'+obj.DataList1[i].Val14+'</td><td>'+obj.DataList1[i].Val15+'</td><td>'+obj.DataList1[i].Val16+'</td><td>'+obj.DataList1[i].Val17+'</td><td>'+obj.DataList1[i].Val18+'</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
                        roadcost[0]=obj.DataList1[i].Val4;
                        roadcost[1]=obj.DataList1[i].Val5;
                        roadcost[2]=obj.DataList1[i].Val6;
                        roadcost[3]=obj.DataList1[i].Val7;
                        roadcost[4]=obj.DataList1[i].Val8;
                        roadcost[5]=obj.DataList1[i].Val9;
                        roadcost[6]=obj.DataList1[i].Val10;
                        roadcost[7]=obj.DataList1[i].Val11;
                        roadcost[8]=obj.DataList1[i].Val12;
                        roadcost[9]=obj.DataList1[i].Val13;
                        roadcost[10]=obj.DataList1[i].Val14;
                        roadcost[11]=obj.DataList1[i].Val15;
                        roadcost[12]=obj.DataList1[i].Val16;
                        roadcost[13]=obj.DataList1[i].Val17;
                        roadcost[14]=obj.DataList1[i].Val18;
                    }
                        
                    prvscmdt=crntcmdt;
		    prvszone=crntzone;
		    prvsdvsn=crntdvsn;
                }
            }
            htmlstr+='</table>';
            $("#divRailRoadCmpr").html(htmlstr);
            }                 
        });
}
function fetchVarn(railcost1, roadcost1) {
    var htmlstr='';
    if((roadcost1=='') || (roadcost1==null)|| (roadcost1=="0"))
        return '';
        
    var railcost=Number(railcost1);
    var roadcost=Number(roadcost1);
    
    if(railcost<roadcost) {
        pctg=(((roadcost-railcost)*100)/roadcost).toFixed(1);
        htmlstr+='<span class="badge badge-success"><i class="fa fa-arrow-down" aria-hidden="true"></i>'+pctg+'%</span>';
    }
    if(railcost>roadcost) {
        pctg=(((roadcost-railcost)*100)/roadcost).toFixed(1);
        htmlstr+='<span class="badge badge-danger"><i class="fa fa-arrow-up" aria-hidden="true"></i>'+pctg+'%</span>';
    }
    return htmlstr;
}