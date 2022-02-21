var GG_LOV=[];

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
$(document).ready(function(){
    fetchLOVs();
});
function refreshData() {
    var zone=$("#selZone").val();
    var cmdt=$("#selCmdt").val();    
    var measure=$("#selType").val();
    
    if((measure=="") || (measure==null)) {
        alert("Please select Progress Measure Type !");
        return false;
    }
    fetchPrgsMeasure(zone,cmdt,measure);
}
function fetchPrgsMeasure(zone,cmdt,measure)
{
	var myurl="/RailSAHAY/AcqnPlanMIS";
        var str='';
        if(zone!='')
            str+='&nbsp;&nbsp;&nbsp;Zone: '+zone;            
        if(cmdt!='')
            str+='&nbsp;&nbsp;&nbsp;Cmdt: '+cmdt;
            
            
        switch(measure) {
            case 'AP':
                $(".head-lbl").html("Action Plans"+str);
                break;
            case 'IO':
                $(".head-lbl").html("Industry Outreach"+str);
                break;
            case 'IN':
                $(".head-lbl").html("Initiatives Taken"+str);
                break;
            case 'IC':
                $(".head-lbl").html("Industry Cluster"+str);
                break;
            case 'FC':
                $(".head-lbl").html("Freight Forwarders (Current)"+str);
                break;
            case 'FP':
                $(".head-lbl").html("Freight Forwarders (Proposed)"+str);
                break;
        }
        $("#divMeasure").html('<div class="spinner-border text-danger"></div>');
                    
        var htmlap='<table id="example1" class="table table-striped table-mis"><thead><tr><th rowspan="2">Zone</th><th rowspan="2">Division</th><th rowspan="2">Commodity</th><th colspan="6">Action Points</th></tr><tr><th>Action Point</th><th>Officer Assigned</th><th>Cost Involved (Cr)</th><th>Target Revenue (Cr)</th><th>Target Volume (MT)</th><th>Handholding needed from Railway Board</th></tr></thead>';
        var htmlin='<table id="example2" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Initiatives Taken</th><th>Additional Revenue (Cr)</th><th>Additional Loading (MT)</th></tr></thead>';
        var htmlio='<table id="example3" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Traffic Type</th><th>Industry</th><th>Additional Revenue (Cr)</th><th>Additional Loading (MT)</th></tr></thead>';
        var htmlic='<table id="example4" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Stock Type</th><th>Freight Volume across all modes (MT)</th><th>Rail Share (%)</th><th>Reason for Competitors Advantage</th></tr></thead>';
        var htmlfc='<table id="example5" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Freight Forwarder Name</th><th>Freight Forwarder Contact</th><th>OD</th><th>Deployed On</th><th>Traffic Vol (MT)</th><th>Revenue (Cr)</th></tr></thead>';
        var htmlfp='<table id="example5" class="table table-striped table-mis"><thead><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Freight Forwarder Name</th><th>Freight Forwarder Contact</th><th>OD</th><th>Progress</th><th>Traffic Vol (MT)</th><th>Revenue (Cr)</th></tr></thead>';

	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'FETCH_PRGS_MESR', Zone:zone, Cmdt:cmdt, MesrType:measure},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
                {
                    for(var i=0;i<obj.DataList.length;i++)
                    {
                         if(measure=="AP")
                        {
                            if(obj.DataList[i].Val8!="")
                                htmlap+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+fetchCodeDesc('AP',obj.DataList[i].Val4)+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+fixDecimal(obj.DataList[i].Val10)+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td><td><a href="#" style="color:#b32400 !important;font-weight:600;" onclick="downloadProposalFile(\''+obj.DataList[i].Val8+'\');">View Proposal</a></td></tr>';
                            else
                                htmlap+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+fetchCodeDesc('AP',obj.DataList[i].Val4)+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+fixDecimal(obj.DataList[i].Val10)+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td><td>'+obj.DataList[i].Val8+'</td></tr>';
                        }
                        if(measure=="IC")
                            htmlic+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td><td>'+obj.DataList[i].Val4+'</td></tr>';
                        if(measure=="IN")
                            htmlin+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+obj.DataList[i].Val4+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td></tr>';
                        if(measure=="IO")
                            htmlio+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+obj.DataList[i].Val3+'</td><td>'+obj.DataList[i].Val4+'</td><td>'+obj.DataList[i].Val5+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td></tr>';
                        if(measure=="FC")
                            htmlfc+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+fetchNumCmdtName(obj.DataList[i].Val3)+'</td><td>'+obj.DataList[i].Val4+'</td><td>'+obj.DataList[i].Val5+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+obj.DataList[i].Val7+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td></tr>';
                        if(measure=="FP")
                            htmlfp+='<tr><td>'+obj.DataList[i].Val1+'</td><td>'+obj.DataList[i].Val2+'</td><td>'+fetchNumCmdtName(obj.DataList[i].Val3)+'</td><td>'+obj.DataList[i].Val4+'</td><td>'+obj.DataList[i].Val5+'</td><td>'+obj.DataList[i].Val6+'</td><td>'+obj.DataList[i].Val7+'</td><td>'+fixDecimal(obj.DataList[i].Val11)+'</td><td>'+fixDecimal(obj.DataList[i].Val12)+'</td></tr>';
                    }
                    htmlap+='</table>';
                    htmlic+='</table>';
                    htmlin+='</table>';
                    htmlio+='</table>';
                    
                    if(measure=="AP"){
                        $("#divMeasure").html(htmlap);  
                        printTable("example1");
                    }
                    if(measure=="IN"){
                        $("#divMeasure").html(htmlin);
                        printTable("example2");
                    }
                    if(measure=="IC"){
                        $("#divMeasure").html(htmlic);
                        printTable("example4");
                    }
                    if(measure=="IO"){
                        $("#divMeasure").html(htmlio);
                        printTable("example3");
                    }
                    if(measure=="FC"){
                        $("#divMeasure").html(htmlfc);
                        printTable("example5");
                    }
                    if(measure=="FP"){
                        $("#divMeasure").html(htmlfp);
                        printTable("example6");
                    }


                    
                        
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

function downloadProposalFile(fileid) 
{
        $("#FileId").val(fileid);
        $("#frmGetFile").submit();
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