$(document).ready(function(){
	$('#selDvsn').find('option').remove().end();
	$('#selDvsn').append($('<option>', {
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
	refreshData();
});
function refreshData() {
    var zone=$("#selZone").val();
    var dvsn=$("#selDvsn").val();
    var cmdt=$("#selCmdt").val();
    fetchResource(zone,dvsn,cmdt);
}
function fetchResource(zone,dvsn,cmdt)
{
	var myurl="/RailSAHAY/AcqnPlanMIS";
        var str='';
        if(zone!='')
            str+='&nbsp;&nbsp;&nbsp;Zone: '+zone;
        if(dvsn!='')
            str+='&nbsp;&nbsp;&nbsp;Division: '+dvsn;
        if(cmdt!='')
            str+='&nbsp;&nbsp;&nbsp;Cmdt: '+cmdt;


        $(".head-lbl").html("Resource Centre"+str);

        $("#divResource").html('<div class="spinner-border text-danger"></div>');

        var htmlres='<table id="tblResource" class="table table-striped table-mis"><thead><tr><th rowspan="2">Zone</th><th rowspan="2">Division</th><th rowspan="2">Commodity</th><th colspan="6">Resource Detail</th></tr><tr><th>Industry</th><th>Name</th><th>Mobile</th><th>Landline</th><th>Email Id</th><th>Address</th></tr></thead>';

	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'FETCH_RESOURCE',Zone:zone,Dvsn:dvsn,Cmdt:cmdt},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
                {
                    for(var i=0;i<obj.DataList.length;i++)
                    {
                            htmlres+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+obj.DataList[i].Val4+'</td><td>'+obj.DataList[i].Val5+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+obj.DataList[i].Val7+'</td><td>'+obj.DataList[i].Val8+'</td><td>'+obj.DataList[i].Val9+'</td></tr>';
                    }
                    htmlres+='</table>';
                    $("#divResource").html(htmlres);
                    printTable("tblResource");
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
});

function printTable(tableID)
{
   $("#"+tableID).dataTable().fnDestroy();
    $("#"+tableID).DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'print',
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
            //'pdfHtml5',
            {
                 extend: 'pdfHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' //,
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }
            }
        ],
        orientation: 'landscape'
    } );

}