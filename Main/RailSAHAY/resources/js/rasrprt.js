function getRASSttn(si_Dvsn)
{
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data: {Optn:'RAS_STTN',Dvsn:si_Dvsn},
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			return false;
		}
		var sttnlist='';
		$("#selSttn").find('option').remove().end();
		var selSttn = document.getElementById('selSttn');
		if (typeof selSttn != "undefined") {
			selSttn.options.length = 0;
                	selSttn.append(new Option("SELECT LOCATION","ALL"));
			for (var i=0; i < obj.RASSttn.length;++i){
			/*sttnlist+= '<option value="'+obj.RASSttn[i].Sttn+'" data-value="'+obj.RASSttn[i].Name+'" />'; */
	            	$("#selSttn").append(new Option(obj.RASSttn[i].Sttn+"-"+obj.RASSttn[i].Name,obj.RASSttn[i].Sttn));

			}
		}
	   }
	});
}
function getRASClstSttn(si_Dvsn,si_Clst)
{
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data: {Optn:'RAS_STTN',Dvsn:si_Dvsn,Clst:si_Clst},
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			return false;
		}
		var sttnlist='';
		$("#selSttn").find('option').remove().end();
		var selSttn = document.getElementById('selSttn');
		if (typeof selSttn != "undefined") {
			selSttn.options.length = 0;
                	selSttn.append(new Option("SELECT LOCATION","ALL"));
			for (var i=0; i < obj.RASSttn.length;++i){
			/*sttnlist+= '<option value="'+obj.RASSttn[i].Sttn+'" data-value="'+obj.RASSttn[i].Name+'" />'; */
	            	$("#selSttn").append(new Option(obj.RASSttn[i].Sttn+"-"+obj.RASSttn[i].Name,obj.RASSttn[i].Sttn));

			}
		}
	   }
	});
}
function fetchIOREIndt()
{
	var zone=$("#selZone").val();
	var dvsn=$("#selDvsn1").val();
	/*var clst=$("#selClst1").val();*/
	var clst='';
	var sttn=$("#selSttn1").val();
	var si_date=$("#txtDate").val();
	var date=si_date.split("-");
	var si_NewDate=date[2]+"-"+date[1]+"-"+date[0];
	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');

	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	inParam={Optn:'RASIRONORE',txtZone:zone,txtDvsn:dvsn,txtClst:clst,txtSttn:sttn,txtCmdt:'IORE',txtDate:si_NewDate};
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data: inParam,
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			$("#divData").html('');
			return false;
		}
		var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2" class="text-center">SR.No.</th><th rowspan="2">Division</th><th rowspan="2">Station</th><th rowspan="2">Cluster</th><th colspan="2" class="text-center">Indents</th><th rowspan="2" class="text-center">Allotments Made</th></tr><tr><th class="text-center">Outstanding</th><th class="text-center">Registered</th></tr>';
		for (var i=0; i < obj.data.length;i++){
			htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].dvsn+'</td><td>'+obj.data[i].sttn+'</td><td>'+obj.data[i].clstr+'</td><td class="text-center"><a href="javascript:void(0)" onclick="showIOREDtlsOtsg(\''+obj.data[i].indtOhref+'\');" class="dtls-link">'+obj.data[i].indtO+'</a></td><td class="text-center"><a href="javascript:void(0)" onclick="showIOREDtlsRegd(\''+obj.data[i].indtRHref+'\');" class="dtls-link">'+obj.data[i].indtR+'</a></td><td class="text-center"><a href="javascript:void(0)" onclick="showIOREDtlsAlot(\''+obj.data[i].indtAHref+'\');" class="dtls-link">'+obj.data[i].indtA+'</a></td></tr>';
		}
		htmlstr+='</table>';
		if(obj.data.length>0)
			$("#divData").html(htmlstr);
		else
			noDataFound();
	   }
	});
}
function showIOREDtlsAlot(myurl)
{
	var htmlstr='';
	$("#divDtlsData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			$("#divDtlsData").html('');
			return false;
		}
		var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2" class="text-center">SR.No.</th><th colspan="2">Station</th><th colspan="5">Demand</th><th colspan="2" class="text-center">Indented</th><th rowspan="2">Outstanding 8W</th><th colspan="2">Forwarding Note</th><th colspan="2">Allotment</th></tr><tr><th>From</th><th>To</th><th>Numb</th><th>Date</th><th>Time</th><th>Consignor</th><th>Consignee</th><th>Type</th><th>8W</th><th>Numb</th><th>Date</th><th>Date</th><th>Units</th></tr>';
		for (var i=0; i < obj.data.length;i++){
			htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].sttnfrom+'</td><td>'+obj.data[i].sttnto+'</td><td>'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].cnsr+'</td><td>'+obj.data[i].cnsg+'</td><td>'+obj.data[i].indttype+'</td><td>'+obj.data[i].indt8w+'</td><td>'+obj.data[i].ostg8w+'</td><td>'+obj.data[i].frwdnoteno+'</td><td>'+obj.data[i].frwdnotedate+'</td><td>'+obj.data[i].alotdate+'</td><td>'+obj.data[i].alotunit+'</td></tr>';
		}
		htmlstr+='</table>';
		if(obj.data.length>0)
			$("#divDtlsData").html(htmlstr);
		else
			noDataFound();
	   }
	});

}
function showIOREDtlsRegd(myurl)
{
	var htmlstr='';
	$("#divDtlsData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			$("#divDtlsData").html('');
			return false;
		}
		var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2" class="text-center">SR.No.</th><th rowspan="2">Division From</th><th rowspan="2">Cluster</th><th colspan="2">Station</th><th colspan="6">Demand</th><th rowspan="2">Special No.</th><th rowspan="2">Traffic Type</th><th rowspan="2">Priority</th><th rowspan="2">Premium Booking</th><th rowspan="2">Restriction</th><th rowspan="2">Via</th><th colspan="3">Indented</th><th rowspan="2">Load 8W</th><th rowspan="2">Cancel 8W</th><th rowspan="2">Forfeit</th><th rowspan="2">Outstanding 8W</th><th rowspan="2">Scheme Code</th><th rowspan="2">Premium Flag</th><th rowspan="2">Expected Loading Time</th></tr><tr><th>From</th><th>To</th><th>Numb</th><th>Date</th><th>Time</th><th>Consignor</th><th>Consignee</th><th>Commodity</th><th>Type</th><th>Units</th><th>8W</th></tr>';
		for (var i=0; i < obj.data.length;i++){
			htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].dvsnfrom+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].sttnfrom+'</td><td>'+obj.data[i].sttnto+'</td><td>'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].cnsr+'</td><td>'+obj.data[i].cnsg+'</td><td>'+obj.data[i].cmdt+'</td><td>'+obj.data[i].spclno+'</td><td>'+obj.data[i].trfctype+'</td><td>'+obj.data[i].prtyclas+'</td><td>'+obj.data[i].prmbkgclas+'</td><td>'+obj.data[i].rstn+'</td><td>'+obj.data[i].via+'</td><td>'+obj.data[i].indttype+'</td><td>'+obj.data[i].indtunts+'</td><td>'+obj.data[i].indt8w+'</td><td>'+obj.data[i].load8w+'</td><td>'+obj.data[i].load8w+'</td><td>'+obj.data[i].cncl8w+'</td><td>'+obj.data[i].forefit+'</td><td>'+obj.data[i].otsg8w+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td></tr>';
		}
		htmlstr+='</table>';
		if(obj.data.length>0)
			$("#divDtlsData").html(htmlstr);
		else
			noDataFound();
	   }
	});

}

function showIOREDtlsOtsg(myurl)
{
	var htmlstr='';
	$("#divDtlsData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			$("#divDtlsData").html('');
			return false;
		}
		var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2" class="text-center">SR.No.</th><th rowspan="2">Division From</th><th rowspan="2">Cluster</th><th colspan="2">Station</th><th colspan="4">Demand</th><th rowspan="2">Traffic Type</th><th rowspan="2">Priority</th><th rowspan="2">Sub Priority</th><th rowspan="2">Premium Booking</th><th rowspan="2">Restriction</th><th rowspan="2">Indented Type</th><th colspan="2">Outstanding</th><th rowspan="2">Scheme Code</th><th rowspan="2">Premium Flag</th><th rowspan="2">Expected Loading Time</th></tr><tr><th>From</th><th>To</th><th>Date</th><th>Consignor</th><th>Consignee</th><th>Commodity</th><th>Units</th><th>8W</th></tr>';
		for (var i=0; i < obj.data.length;i++){
			htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].dvsnfrom+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].sttnfrom+'</td><td>'+obj.data[i].sttnto+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].cnsr+'</td><td>'+obj.data[i].cnsg+'</td><td>'+obj.data[i].cmdt+'</td><td>'+obj.data[i].trfctype+'</td><td>'+obj.data[i].prtyclas+'</td><td>'+obj.data[i].subprty+'</td><td>'+obj.data[i].prmbkgclas+'</td><td>'+obj.data[i].rstn+'</td><td>'+obj.data[i].indttype+'</td><td>'+obj.data[i].ostgunts+'</td><td>'+obj.data[i].ostg8w+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td></tr>';
		}
		htmlstr+='</table>';
		if(obj.data.length>0)
			$("#divDtlsData").html(htmlstr);
		else
			noDataFound();
	   }
	});

}

function fetchIOREOtsgIndt()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_Sttn=$("#selSttn").val();
	var sttnparm=si_Dvsn.toLowerCase()+"Sttn";
	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam;
	switch(si_Dvsn)
	{
		case 'ASN':
			inParam={Optn:'RASIOSttnWiseOtsgDmnd',rasDvsn:si_Dvsn,asnClst:'',asnSttn:si_Sttn};
			break;
		case 'CKP':
			inParam={Optn:'RASIOSttnWiseOtsgDmnd',rasDvsn:si_Dvsn,ckpClst:'',ckpSttn:si_Sttn};
			break;
		case 'KGP':
			inParam={Optn:'RASIOSttnWiseOtsgDmnd',rasDvsn:si_Dvsn,kgpClst:'',kgpSttn:si_Sttn};
			break;
		case 'KUR':
			inParam={Optn:'RASIOSttnWiseOtsgDmnd',rasDvsn:si_Dvsn,kurClst:'',kurSttn:si_Sttn};
			break;
		case 'SBP':
			inParam={Optn:'RASIOSttnWiseOtsgDmnd',rasDvsn:si_Dvsn,sbpClst:'',sbpSttn:si_Sttn};
			break;
	}
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data: inParam,
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt(erormesg);
			$("#divData").html('');
			return false;
		}
		var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2" class="text-center">SR.No.</th><th rowspan="2">Cluster</th><th colspan="2">Station</th><th colspan="7" class="text-center">Demand Detail</th><th colspan="2">Consignment</th></tr><tr><th>From</th><th>To</th><th class="text-center">Demand No.</th><th class="text-center">Time</th><th class="text-center">Priority</th><th class="text-center">Sub Priority</th><th>Scheme Code</th><th>Premium Flag</th><th>Expected Loading Time</th><th>Consignor</th><th>Consignee</th></tr>';
		for (var i=0; i < obj.data.length;i++){
			htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].sttnfrom+'</td><td>'+obj.data[i].sttnto+'</td><td class="text-center">'+obj.data[i].dmndno+'</td><td class="text-center">'+obj.data[i].dmndtime+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].subprty+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td><td>'+obj.data[i].csnr+'</td><td>'+obj.data[i].cnsg+'</td></tr>';
		}
		htmlstr+='</table>';
		if(obj.data.length>0)
			$("#divData").html(htmlstr);
		else
			noDataFound();
	   }
	});
}

function fetchIORERakeAlcnPlan()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_DateFrom=$("#txtDateFrom").val();
	var si_DateTo=$("#txtDateTo").val();
	var datefrom=si_DateFrom.split("-");
	var dateto=si_DateTo.split("-");
	var si_NewDateFrom=datefrom[2]+"-"+datefrom[1]+"-"+datefrom[0];
	var si_NewDateTo=dateto[2]+"-"+dateto[1]+"-"+dateto[0];
	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";

	var inParam={Optn:'RASRakeAlcnPlan',Dvsn:si_Dvsn,dateFrom:si_NewDateFrom,dateTo:si_NewDateTo};
	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}
			var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center">SR.No.</th><th>Date</th><th>Station To</th><th class="text-center">Priority</th><th class="text-center">Sub Priority</th><th class="text-center">C/N</th><th>Customer Code</th><th>Customer Name</th><th>Alloted At Station/Indent No./Date</th></tr>';
			for (var i=0; i < obj.data.length;i++){
				htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].date+'</td><td>'+obj.data[i].sttnto+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].subprty+'</td><td class="text-center">'+obj.data[i].cn+'</td><td>'+obj.data[i].custcode+'</td><td>'+obj.data[i].custname+'</td><td>'+obj.data[i].odrdtls+'</td></tr>';
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});
}
function fetchIORERakeAlot()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_PerdOptn=$("input[name='optPerd']:checked").val();
	var si_Sttn=$("#selSttn").val();
	var si_DateFrom=$("#txtDateFrom").val();
	var si_DateTo=$("#txtDateTo").val();
	var datefrom=si_DateFrom.split("-");
	var dateto=si_DateTo.split("-");
	var si_NewDateFrom=datefrom[2]+"-"+datefrom[1]+"-"+datefrom[0];
	var si_NewDateTo=dateto[2]+"-"+dateto[1]+"-"+dateto[0];

	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam;
	switch(si_Dvsn)
	{
		case 'ASN':
			inParam={Optn:'RASIORakeAlotment',rasDvsn:si_Dvsn,rasClst:'',asnSttn:si_Sttn,inptradio:si_PerdOptn,FromDate:si_NewDateFrom,ToDate:si_NewDateTo,NextScndday:GG_NextScndDate};
			break;
		case 'CKP':
			inParam={Optn:'RASIORakeAlotment',rasDvsn:si_Dvsn,ckpClst:'',ckpSttn:si_Sttn,inptradio:si_PerdOptn,FromDate:si_NewDateFrom,ToDate:si_NewDateTo,NextScndday:GG_NextScndDate};
			break;
		case 'KGP':
			inParam={Optn:'RASIORakeAlotment',rasDvsn:si_Dvsn,kgpClst:'',kgpSttn:si_Sttn,inptradio:si_PerdOptn,FromDate:si_NewDateFrom,ToDate:si_NewDateTo,NextScndday:GG_NextScndDate};
			break;
		case 'KUR':
			inParam={Optn:'RASIORakeAlotment',rasDvsn:si_Dvsn,kurClst:'',kurSttn:si_Sttn,inptradio:si_PerdOptn,FromDate:si_NewDateFrom,ToDate:si_NewDateTo,NextScndday:GG_NextScndDate};
			break;
		case 'SBP':
			inParam={Optn:'RASIORakeAlotment',rasDvsn:si_Dvsn,sbpClst:'',sbpSttn:si_Sttn,inptradio:si_PerdOptn,FromDate:si_NewDateFrom,ToDate:si_NewDateTo,NextScndday:GG_NextScndDate};
			break;
	}

	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}

			var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th rowspan="2">&nbsp;</th><th rowspan="2" class="text-center">SR.No.</th><th rowspan="2" class="text-center">Date</th><th rowspan="2">Cluster</th><th rowspan="2">Loading Station</th><th colspan="2">Customer</th><th rowspan="2">Station To</th><th colspan="7">Indent Detail</th><th colspan="3">Rake Permit</th></tr><tr><th>Code</th><th>Name</th><th>Indent No.</th><th class="text-center">Indent Date</th><th class="text-center">Priority</th><th class="text-center">Sub Priority</th><th>Scheme Code</th><th>Premium Flag</th><th>Expected Loading Date</th><th>Number</th><th>Date</th><th>Valid Upto</th></tr>';
			for (var i=0; i < obj.data.length;i++){
				if(obj.data[i].color=="R")
					htmlstr+= '<tr><td><i class="fas fa-circle text-danger"></i></td><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].date+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].ldngsttn+'</td><td>'+obj.data[i].custcode+'</td><td>'+obj.data[i].custname+'</td><td>'+obj.data[i].sttnto+'</td><td class="text-center">'+obj.data[i].indtnumb+'</td><td class="text-center">'+obj.data[i].indtdate+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].subprty+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td><td class="text-center">'+obj.data[i].rpnumb+'</td><td class="text-center">'+obj.data[i].rpdate+'</td><td class="text-center">'+obj.data[i].rpvald+'</td></tr>';
				if(obj.data[i].color=="G")
					htmlstr+= '<tr><td><i class="fas fa-circle text-success"></i></td><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].date+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].ldngsttn+'</td><td>'+obj.data[i].custcode+'</td><td>'+obj.data[i].custname+'</td><td>'+obj.data[i].sttnto+'</td><td class="text-center">'+obj.data[i].indtnumb+'</td><td class="text-center">'+obj.data[i].indtdate+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].subprty+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td><td class="text-center">'+obj.data[i].rpnumb+'</td><td class="text-center">'+obj.data[i].rpdate+'</td><td class="text-center">'+obj.data[i].rpvald+'</td></tr>';
				if(obj.data[i].color=="")
					htmlstr+= '<tr><td>&nbsp;</td><td class="text-center">'+(i+1)+'</td><td>'+obj.data[i].date+'</td><td>'+obj.data[i].clstr+'</td><td>'+obj.data[i].ldngsttn+'</td><td>'+obj.data[i].custcode+'</td><td>'+obj.data[i].custname+'</td><td>'+obj.data[i].sttnto+'</td><td class="text-center">'+obj.data[i].indtnumb+'</td><td class="text-center">'+obj.data[i].indtdate+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].subprty+'</td><td>'+obj.data[i].schmcode+'</td><td>'+obj.data[i].premflag+'</td><td>'+obj.data[i].expldng+'</td><td class="text-center">'+obj.data[i].rpnumb+'</td><td class="text-center">'+obj.data[i].rpdate+'</td><td class="text-center">'+obj.data[i].rpvald+'</td></tr>';
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});
}
function fetchRASCoalAlotTh()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_Prty=$("#selPrty").val();;
	var si_Spsr=$("#selSpsr").val();
	var si_Cust=$("#txtCust").val();
	var si_Dest=$("#txtDest").val();
	var si_Fld=$("#txtFld").val();
	var si_Pilt=$("#txtPilt").val();
	var si_Supl="L";

	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam={Optn:'RASCOALAlotmentthrty',txtDvsnCode:si_Dvsn,txtPrtyCode:si_Prty,txtSpsrCode:si_Spsr,txtCustCode:si_Cust,txtDest:si_Dest,txtFeldCode:si_Fld,txtPiltCode:si_Pilt,optSupl:si_Supl};

	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}

			var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center">SR.No.</th><th class="text-center">Allotment Date</th><th class="text-center">Priority</th><th >Station</th><th>Consignor</th><th>Coal Field</th><th>Station To</th><th>Customer</th><th class="text-right">Allotment Units</th><th>Remarks</th></tr>';
			for (var i=0; i < obj.data.length;i++)
			{
				htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td class="text-center">'+obj.data[i].alotdate+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].sttn+'</td><td>'+obj.data[i].cnsr+'</td><td>'+obj.data[i].coalfield+'</td><td>'+obj.data[i].sttnto+'</td><td>'+obj.data[i].cust+'</td><td class="text-right">'+obj.data[i].alotunts+'</td><td>'+obj.data[i].rmrk+'</td></tr>';
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});
}
function fetchRASCoalAlotOdr()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_Prty=$("#selPrty").val();;
	var si_Spsr=$("#selSpsr").val();
	var si_Cust=$("#txtCust").val();
	var si_Dest=$("#txtDest").val();
	var si_Fld=$("#selCoalFld").val();
	var si_Pilt=$("#selPilt").val();
	var si_Supl="O";

	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam={Optn:'RASCOALAlotmentOdr',txtDvsnCode:si_Dvsn,txtPrtyCode:si_Prty,txtSpsrCode:si_Spsr,txtCustCode:si_Cust,txtDest:si_Dest,txtFeldCode:si_Fld,txtPiltCode:si_Pilt,optSupl:si_Supl};

	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}

			var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center">SR.No.</th><th class="text-center">Allotment Date</th><th class="text-center">Priority</th><th >Station</th><th>Consignor</th><th>Coal Field</th><th>Station To</th><th>Customer</th><th class="text-right">Rake SR. Sequence</th><th class="text-right">Allotment Units</th><th>Remarks</th></tr>';
			for (var i=0; i < obj.data.length;i++)
			{
				htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td class="text-center">'+obj.data[i].alotdate+'</td><td class="text-center">'+obj.data[i].prty+'</td><td class="text-center">'+obj.data[i].sttn+'</td><td>'+obj.data[i].cnsr+'</td><td>'+obj.data[i].coalfield+'</td><td>'+obj.data[i].sttnto+'</td><td>'+obj.data[i].cust+'</td><td class="text-right">'+obj.data[i].rakesqnc+'</td><td class="text-right">'+obj.data[i].alotunts+'</td><td>'+obj.data[i].rmrk+'</td></tr>';
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});
}

function fetchRASCoalAlot()
{
	var si_Dvsn=$("#selDvsn").val();
	var si_Prty=$("#selPrty").val();;
	var si_Spsr=$("#selSpsr").val();
	var si_Cust=$("#txtCust").val();
	var si_DateFrom=$("#txtDateFrom").val();
	var si_DateTo=$("#txtDateTo").val();
	var datefrom=si_DateFrom.split("-");
	var dateto=si_DateTo.split("-");
	var si_NewDateFrom=datefrom[2]+"-"+datefrom[1]+"-"+datefrom[0];
	var si_NewDateTo=dateto[2]+"-"+dateto[1]+"-"+dateto[0];

	var si_Supl="O";

	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam={Optn:'RASCOALAlotment',txtDvsnCode:si_Dvsn,txtPrtyCode:si_Prty,txtCustCode:si_Cust,txtDateFrom:si_NewDateFrom,txtDateTo:si_NewDateTo,optSupl:si_Supl};

	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}
			var htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center">SR.No.</th><th class="text-center">Month</th><th>Name of Consumer</th><th>Destination</th><th >C/N</th><th>Priority</th><th>Sanction Boxes</th><th>Sanction Date</th><th>Sponsor</th><th class="text-right">COAL Class</th><th>Pilot</th><th>Rake SR.No.</th><th>Validity of Program</th><th>Date of Alot</th><th>Date of Loading</th><th>Alot During Month</th><th>Supply During Month</th><th>Balance for Alot</th><th>Arrear Allotted Boxes</th><th>Arrear Allotted Date</th></tr>';
			for (var i=0; i < obj.data.length;i++)
			{
				htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td class="text-center">'+obj.data[i].mnth+'</td><td>'+obj.data[i].cnsmr+'</td><td class="text-center">'+obj.data[i].dstn+'</td><td class="text-center">'+obj.data[i].cn+'</td><td>'+obj.data[i].prty+'</td><td class="text-right">'+obj.data[i].sntdbxs+'</td><td class="text-center">'+obj.data[i].sntdate+'</td><td>'+obj.data[i].spnsrcode+'</td><td>'+obj.data[i].coalclass+'</td><td>'+obj.data[i].pilot+'</td><td>'+obj.data[i].rake+'</td><td>'+obj.data[i].pgmvldt+'</td><td>'+obj.data[i].alotdate+'</td><td>'+obj.data[i].ldngdate+'</td><td>'+obj.data[i].mnthalot+'</td><td>'+obj.data[i].splymnth+'</td><td>'+obj.data[i].alotblnc+'</td><td>'+obj.data[i].areralotbox+'</td><td>'+obj.data[i].areralotdate+'</td></tr>';
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});
}


$(document).ready(function(){
	var si_Cmdt=$("#txtCmdt").val();
	popZoneList();
	if(si_Cmdt!="COAL")
	{

		popZoneDvsnList('SE');
		if(si_Cmdt!="ODR")
			getRASSttn("ASN");

		$("#selDvsn").change(function()
		{
			var dvsn=$("#selDvsn").val();
			$("#divData").html('');
			getRASSttn(dvsn);
		});
		$("#selZone").change(function()
		{
			var zone=$("#selZone").val();
			$("#divData").html('');
			popZoneDvsnList(zone);
		});
	}
	else
	{
		gnrtDvsnList();
		gnrtPrtyList();
		var si_dvsn=$("#selDvsn").val();
		chngCoalAttr(si_dvsn);
		$("#selDvsn").change(function()
		{
			var dvsn=$("#selDvsn").val();
			$("#divData").html('');
			chngCoalAttr(dvsn);
		});
	}

	$("#selSttn").change(function()
	{
		$("#divData").html('');
	});
	$("#selSttn1").change(function()
	{
		$("#divData").html('');
	});
	$("#selClst1").change(function()
	{
		$("#divData").html('');
	});
	$("#selDvsn").change(function()
	{
		$("#divData").html('');
	});
	$("#selDvsn1").change(function()
	{
		$("#divData").html('');
	});
	$("#txtDateFrom").change(function()
	{
		$("#divData").html('');
	});
	$("#txtDateTo").change(function()
	{
		$("#divData").html('');
	});
});
function gnrtDvsnList()
{
	si_Cmdt=$("#txtCmdt").val();

	if(si_Cmdt=='COAL')
	{

		$("#selDvsn").find('option').remove().end();
		var selDvsn= document.getElementById('selDvsn');
		selDvsn.options.length = 0;
		for (var i=0; i < gDvarr.length;++i){
		var dvsnname=(gDvarr[i].substring(0,gDvarr[i].lastIndexOf("-"))).trim();
		var dvsncode=(gDvarr[i].substring(gDvarr[i].lastIndexOf("-")+1));
		$("#selDvsn").append(new Option(dvsncode+" - "+dvsnname,dvsncode));
		}
	}
}
function gnrtPrtyList()
{
	si_Cmdt=$("#txtCmdt").val();
	if(si_Cmdt=='COAL')
	{
		$("#selPrty").find('option').remove().end();
		var selPrty= document.getElementById('selPrty');
		selPrty.options.length = 0;
		$("#selPrty").append(new Option("PRIORITY",""));
		for (var i=0; i < aPrty.length;++i)
		{
			var prtyname=(aPrty[i].substring(0,aPrty[i].lastIndexOf("-"))).trim();
			var prtycode=(aPrty[i].substring(aPrty[i].lastIndexOf("-")+1));
			$("#selPrty").append(new Option(prtycode+" - "+prtyname,prtycode));
		}
	}
}
function chngSpsrList(si_dvsn)
{
	si_Cmdt=$("#txtCmdt").val();
	if(si_Cmdt=='COAL')
	{
		var arrname='aSpsr'+si_dvsn.trim();
		var obj=window[arrname];
		$("#selSpsr").find('option').remove().end();
		var selSpsr= document.getElementById('selSpsr');
		selSpsr.options.length = 0;
		$("#selSpsr").append(new Option("SPONSOR",""));
		for (var i=0; i < obj.length;++i)
		{
			var name=(obj[i].substring(0,obj[i].lastIndexOf("-"))).trim();
			var code=(obj[i].substring(obj[i].lastIndexOf("-")+1));
			$("#selSpsr").append(new Option(code+" - "+name,code));
		}
	}

}
function chngCoalFld(si_dvsn)
{
	si_Cmdt=$("#txtCmdt").val();
	if(si_Cmdt=='COAL')
	{
		var arrname='aCoalFld'+si_dvsn.trim();
		var obj=window[arrname];
		$("#selCoalFld").find('option').remove().end();
		var selCoalFld= document.getElementById('selCoalFld');
		selCoalFld.options.length = 0;
		$("#selCoalFld").append(new Option("COAL FIELD",""));
		for (var i=0; i < obj.length;++i)
		{
			var name=(obj[i].substring(0,obj[i].lastIndexOf("-"))).trim();
			var code=(obj[i].substring(obj[i].lastIndexOf("-")+1));
			$("#selCoalFld").append(new Option(code+" - "+name,code));
		}
	}

}
function chngPilt(si_dvsn)
{
	si_Cmdt=$("#txtCmdt").val();
	if(si_Cmdt=='COAL')
	{
		var arrname='aPit'+si_dvsn.trim();
		var obj=window[arrname];
		$("#selPilt").find('option').remove().end();
		var selPilt= document.getElementById('selPilt');
		selPilt.options.length = 0;
		$("#selPilt").append(new Option("PILOT",""));
		for (var i=0; i < obj.length;++i)
		{
			var name=(obj[i].substring(0,obj[i].lastIndexOf("-"))).trim();
			var code=(obj[i].substring(obj[i].lastIndexOf("-")+1));
			$("#selPilt").append(new Option(code+" - "+name,code));
		}
	}

}
function chngCoalAttr(si_dvsn)
{
	chngSpsrList(si_dvsn);
	chngCoalFld(si_dvsn);
	chngPilt(si_dvsn);
}
function popZoneList()
{
	si_Cmdt=$("#txtCmdt").val();
	if(si_Cmdt=='ODR')
	{
		$("#selZone").find('option').remove().end();
		var selZone= document.getElementById('selZone');
		selZone.options.length = 0;
		for (var i=0; i < aZone.length;++i){
		var code=(aZone[i].substring(0,aZone[i].indexOf("-"))).trim();
		var name=(aZone[i].substring(aZone[i].indexOf("-")+1));
		$("#selZone").append(new Option(code+" - "+name,code));
		}
	}
}
function popZoneDvsnList(si_Zone)
{
	si_Cmdt=$("#txtCmdt").val();
	var cntr=0;
	if(si_Cmdt=='IORE')
	{
		$("#selDvsn1").find('option').remove().end();
		var selDvsn= document.getElementById('selDvsn1');
		selDvsn.options.length = 0;
		for (var i=0; i < aZoneDvsn.length;++i){
		var zone=(aZoneDvsn[i].substring(0,aZoneDvsn[i].indexOf("-"))).trim();
		if(zone==si_Zone)
		{
			if(cntr==0)
			{
				$("#selDvsn1").append(new Option("DIVISION",""));
				$("#selSttn1").find('option').remove().end();
				var selSttn = document.getElementById('selSttn1');
				selSttn.options.length = 0;
				cntr++;
			}
			var dvsnstr=(aZoneDvsn[i].substring(aZoneDvsn[i].indexOf("-")+1)).trim();
			var code=(dvsnstr.substring(0,dvsnstr.indexOf("-"))).trim();
			var name=(dvsnstr.substring(dvsnstr.indexOf("-")+1));
			$("#selDvsn1").append(new Option(code+" - "+name,code));

		}
		}

			$("#selDvsn1").change(function() {
				var myurl="/RailSAHAY/GG_TrmlDataJson";
				var htmlstr='';
				var crntdvsn=$("#selDvsn1").val();
				$.ajax({
				url : myurl,
				type : "post",
				data: {Optn:'RAS_STTN',Dvsn:crntdvsn},
				async : true,
				success : function(data) {
				var obj= JSON.parse(data);
				var stts=obj.Stts;
				var erormesg=obj.ErorMesg;
				if(stts=="F")
				{
					custPrompt(erormesg);
					return false;
				}
				var sttnlist='';
				$("#selSttn1").find('option').remove().end();
				var selSttn = document.getElementById('selSttn1');
				selSttn.options.length = 0;
                		selSttn.append(new Option("SELECT LOCATION",""));
				for (var i=0; i < obj.RASSttn.length;++i){
	            			$("#selSttn1").append(new Option(obj.RASSttn[i].Sttn+"-"+obj.RASSttn[i].Name,obj.RASSttn[i].Sttn));
				}
	   		}
			});
		});
	}
}
function fetchODRDtls()
{
	var qrytype=($("#selOptn").val()).trim();
	var zone=($("#selZone").val()).trim();

	$("#divData").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var myurl="/RailSAHAY/SHY_OdrRasJSON";
	var inParam={Optn:'ODROtsgDtls',Qry:qrytype,Zone:zone};

	var htmlstr='';
		$.ajax({
		url : myurl,
		type : "post",
		data: inParam,
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				custPrompt(erormesg);
				$("#divData").html('');
				return false;
			}
			var htmlstr='';
			if(qrytype=="ODR_RK_OTSG")
			{
				htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center" rowspan="2">SR.No.</th><th rowspan="2">Division</th><th rowspan="2">Station From</th><th colspan="3">Demand</th><th rowspan="2">Consignor</th><th rowspan="2">Consignee</th><th rowspan="2">Commodity</th><th rowspan="2">Traffic Type</th><th rowspan="2">PC</th><th rowspan="2">PBF</th><th rowspan="2">VIA</th><th rowspan="2">Rake Commodity</th><th rowspan="2">Destination</th><th colspan="3">Indented</th><th colspan="2">Outstanding</th><th colspan="2">Supplied</th></tr><tr><th>No.</th><th>Date</th><th>Time</th><th>Type</th><th>Units</th><th>8W</th><th>Units</th><th>8W</th><th>Units</th><th>Time</th></tr>';

				for (var i=0; i < obj.data.length;i++)
				{
					if(obj.data[i].dvsn=="TOTAL")
						htmlstr+= '<tr style="font-weight:600;background:#ffd633;"><td class="text-center">&nbsp;</td><td class="text-center">'+obj.data[i].dvsn+'</td><td>'+obj.data[i].sttnfrom+'</td><td class="text-center">'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].csnr+'</td><td>'+obj.data[i].cnsg+'</td><td class="text-right">'+obj.data[i].cmdt+'</td><td class="text-center">'+obj.data[i].tt+'</td><td class="text-center">'+obj.data[i].pc+'</td><td class="text-center">'+obj.data[i].pbf+'</td><td>'+obj.data[i].via+'</td><td>'+obj.data[i].rakecmdt+'</td><td>'+obj.data[i].dstn+'</td><td class="text-center">'+obj.data[i].indttype+'</td><td class="text-right">'+obj.data[i].indtunit+'</td><td class="text-right">'+obj.data[i].indt8w+'</td><td class="text-right">'+obj.data[i].ostgunit+'</td><td class="text-right">'+obj.data[i].ostg8w+'</td><td class="text-right">'+obj.data[i].spldunit+'</td><td class="text-center">'+obj.data[i].spldtime+'</td></tr>';
					else
						htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td class="text-center">'+obj.data[i].dvsn+'</td><td>'+obj.data[i].sttnfrom+'</td><td class="text-center">'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].csnr+'</td><td>'+obj.data[i].cnsg+'</td><td class="text-right">'+obj.data[i].cmdt+'</td><td class="text-center">'+obj.data[i].tt+'</td><td class="text-center">'+obj.data[i].pc+'</td><td class="text-center">'+obj.data[i].pbf+'</td><td>'+obj.data[i].via+'</td><td>'+obj.data[i].rakecmdt+'</td><td>'+obj.data[i].dstn+'</td><td class="text-center">'+obj.data[i].indttype+'</td><td class="text-right">'+obj.data[i].indtunit+'</td><td class="text-right">'+obj.data[i].indt8w+'</td><td class="text-right">'+obj.data[i].ostgunit+'</td><td class="text-right">'+obj.data[i].ostg8w+'</td><td class="text-right">'+obj.data[i].spldunit+'</td><td class="text-center">'+obj.data[i].spldtime+'</td></tr>';
				}
			}
			else
			{
				htmlstr='<table class="display table table-striped table-bordered tabformat table-sm table-responsive w-100 d-block d-md-table"><tr><th class="text-center" rowspan="2">SR.No.</th><th rowspan="2">Division</th><th rowspan="2">Station From</th><th colspan="3">Demand</th><th rowspan="2">Consignor</th><th rowspan="2">Consignee</th><th rowspan="2">Commodity</th><th rowspan="2">Traffic Type</th><th rowspan="2">PC</th><th rowspan="2">PBF</th><th rowspan="2">VIA</th><th rowspan="2">Rake Commodity</th><th rowspan="2">Destination</th><th colspan="3">Indented</th><th rowspan="2" class="text-center">Metwith Date</th></tr><tr><th>No.</th><th>Date</th><th>Time</th><th>Type</th><th>Units</th><th>8W</th></tr>';
				for (var i=0; i < obj.data.length;i++)
				{
					if(obj.data[i].dvsn=="TOTAL")
						htmlstr+= '<tr style="font-weight:600;background:#ffd633;"><td class="text-center">&nbsp;</td><td class="text-center">'+obj.data[i].dvsn+'</td><td>'+obj.data[i].sttnfrom+'</td><td class="text-center">'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].csnr+'</td><td>'+obj.data[i].cnsg+'</td><td class="text-right">'+obj.data[i].cmdt+'</td><td class="text-center">'+obj.data[i].tt+'</td><td class="text-center">'+obj.data[i].pc+'</td><td class="text-center">'+obj.data[i].pbf+'</td><td>'+obj.data[i].via+'</td><td>'+obj.data[i].rakecmdt+'</td><td>'+obj.data[i].dstn+'</td><td class="text-center">'+obj.data[i].indttype+'</td><td class="text-right">'+obj.data[i].indtunit+'</td><td class="text-right">'+obj.data[i].indt8w+'</td><td class="text-center">'+obj.data[i].metwithdate+'</td></tr>';
					else
						htmlstr+= '<tr><td class="text-center">'+(i+1)+'</td><td class="text-center">'+obj.data[i].dvsn+'</td><td>'+obj.data[i].sttnfrom+'</td><td class="text-center">'+obj.data[i].dmndno+'</td><td>'+obj.data[i].dmnddate+'</td><td>'+obj.data[i].dmndtime+'</td><td>'+obj.data[i].csnr+'</td><td>'+obj.data[i].cnsg+'</td><td class="text-right">'+obj.data[i].cmdt+'</td><td class="text-center">'+obj.data[i].tt+'</td><td class="text-center">'+obj.data[i].pc+'</td><td class="text-center">'+obj.data[i].pbf+'</td><td>'+obj.data[i].via+'</td><td>'+obj.data[i].rakecmdt+'</td><td>'+obj.data[i].dstn+'</td><td class="text-center">'+obj.data[i].indttype+'</td><td class="text-right">'+obj.data[i].indtunit+'</td><td class="text-right">'+obj.data[i].indt8w+'</td><td class="text-right">'+obj.data[i].metwithdate+'</td></tr>';
				}
			}
			htmlstr+='</table>';
			if(obj.data.length>0)
				$("#divData").html(htmlstr);
			else
				noDataFound();
		   }
	});

}
function openNav() {
  document.getElementById("mySidenav").style.width = "100%";
}

/* Close/hide the sidenav */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
