var locnlist=null;
var brkuplist=null;
var actvtrmltype="LDNG";
var actvbrkuptype="S";
var fcltyList=null;
var agcontL=null;
var trckcontL=null;
var whcontL=null;
var labcontL=null;
var sttnN=null;
var activeMode="IR";
var infowindow1=new Array(500);

htmlAg1='';
htmlTrck1='';
htmlWh1='';
htmlLab1='';
showPosition();
$("#divLdngData").hide();
function scrollCounter()
{
	$('.trmlstats').each(function () {
	  $(this).prop('Counter', 0).animate({
	    Counter: Number($(this).text()) },
	  {
	    duration: 2000,
	    easing: 'swing',
	    step: function (now) {
	      $(this).text(Math.ceil(now));
	  } 
	});
	});
}

function getTrmlFctyFiltList()
{
	var trmlfctyfltr='';
	var cntr=0;
        $('.chktrmlfcty:checked').each(function() {
	    if(cntr==0)
	            trmlfctyfltr+=$(this).val();
	    else
		    trmlfctyfltr+=","+$(this).val();

	  cntr++;
        });
	return trmlfctyfltr;
}
function getSMARTFctyFiltList()
{
	var smartfctyfltr='';
	var cntr=0;
        $('.chksmart:checked').each(function() {
	    if(cntr==0)
	            smartfctyfltr+=$(this).val();
	    else
 		    smartfctyfltr+=","+$(this).val();

	  cntr++;
        });
	return smartfctyfltr;
}
/*
function getTrmlFctyFiltList()
{
	var trmlfctyfltr='';
	var cntr=0;
        $('.chktrmlfcty').each(function() {
	    if(cntr==0)
	    {
	    	if($(this).is(":checked"))
	            trmlfctyfltr+='Y';
	    	else
		    trmlfctyfltr+='N'
	    }
	    else
	    {
	    	if($(this).is(":checked"))
	            trmlfctyfltr+=',Y';
	    	else
		    trmlfctyfltr+=',N'
	    }
	    cntr++;
        });
	return trmlfctyfltr;
}
function getSMARTFctyFiltList()
{
	var smartfctyfltr='';
	var cntr=0;
        $('.chksmart').each(function() {
	    if(cntr==0)
	    {
	    	if($(this).is(":checked"))
	            smartfctyfltr+='Y';
	    	else
 		    smartfctyfltr+='N';
	    }
	    else
	    {
	    	if($(this).is(":checked"))
	            smartfctyfltr+=',Y';
	    	else
 		    smartfctyfltr+=',N';
	    }
	    cntr++;
        });
	return smartfctyfltr;
}
*/

function refreshDtlsStats()
{
	var locnflag=$(".locnflag").attr("locn");
	if((locnflag=="IR") || (locnflag==""))
	{
		getBrkUpStats('S');
		$('#divTrmlPlot').css({
		    overflowY: 'scroll'
		});
		$("#ddBrkupOptn").show();
	}
	else
	{
		getMainTrmlList('LDNG');
		$("#ddBrkupOptn").hide();

	}
}
function getSmryStats()
{
	var locnflag=$(".locnflag").attr("locn");
	if(locnflag=="IR") { locnflag=""; }
	var locndesc=$("#txtLocn").val();
	var cmdtdesc=$("#txtCmdt").val();
	$("#divLdngData").show();
	var fl_trmlfcty=getTrmlFctyFiltList();
	var fl_smartfcty=getSMARTFctyFiltList();
	var fl_fctylist='';
	if(fl_trmlfcty!='') {
		if(fl_smartfcty!='') { fl_fctylist=fl_trmlfcty+','+fl_smartfcty; }
		else { fl_fctylist=fl_trmlfcty; }
	}
	else {
	if(fl_smartfcty!='') { fl_fctylist=fl_smartfcty; }
		else { fl_fctylist=''; }
	}
		
	var cmdt='';
	var locn='';
	if((cmdtdesc===undefined) || (cmdtdesc==""))
	{

	}
	else
	{

		if(cmdtdesc.indexOf("-")>-1)
		{
		cmdt=cmdtdesc.substring(0,cmdtdesc.indexOf("-"));
		}
		else
		{
		cmdt=cmdtdesc.toUpperCase();
		}
	}
	if(locndesc.indexOf("-")>-1)
	{
		locn=locndesc.substring(0,locndesc.indexOf("-"));
	}
	else
	{
		locn=locndesc.toUpperCase();
	}
	if(locnflag=="NM")
	{
		console.log("Going to get Position");
	}

	if(userLat===undefined)
		userLat='';
	if(userLng===undefined)
		userLng='';

	refreshDtlsStats();
	//var myurl="/RailSAHAY/GG_TrmlDashbordJson?Optn=SMRY_STATS&LocnType="+locnflag+"&Locn="+locn+"&Cmdt="+cmdt+"&Lat="+userLat+"&Lng="+userLng+"&TrmlFcty="+fl_fctylist+"&SMARTFcty="+fl_smartfcty;
	var myurl="/RailSAHAY/GG_TrmlDashbordJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'SMRY_STATS',LocnType:locnflag,Locn:locn,Cmdt:cmdt,Lat:userLat,Lng:userLng,TrmlFcty:fl_fctylist,SMARTFcty:fl_smartfcty},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		var pftcont=obj.Stat0;
		var crtcont=obj.Stat1;
		var ldngcont=obj.Stat2;
		var uldgcont=obj.Stat3;
		$("#divPFTCont").html(pftcont);
		$("#divCRTCont").html(crtcont);
		$("#divLdngCont").html(ldngcont);
		$("#divUldgCont").html(uldgcont);
		$("#divLdngData").hide();
		scrollCounter();
		
		}
		});
}
function getMainTrmlList(trmltype)
{
	var locnflag=$(".locnflag").attr("locn");
	var locndesc=$("#txtLocn").val();
	var cmdtdesc=$("#txtCmdt").val();
	$("#divLdngData").show();
	var cmdt='';
	var locn='';
	if((cmdtdesc===undefined) || (cmdtdesc==""))
	{

	}
	else
	{

		if(cmdtdesc.indexOf("-")>-1)
		{
		cmdt=cmdtdesc.substring(0,cmdtdesc.indexOf("-"));
		}
		else
		{
		cmdt=cmdtdesc.toUpperCase();
		}
	}
	if(locndesc.indexOf("-")>-1)
	{
		locn=locndesc.substring(0,locndesc.indexOf("-"));
	}
	else
	{
		locn=locndesc.toUpperCase();
	}
	if(locnflag=="NM")
	{
		console.log("Going to get Position");
	}

	if(userLat===undefined)
		userLat='';
	if(userLng===undefined)
		userLng='';

	var fl_trmlfcty=getTrmlFctyFiltList();
	var fl_smartfcty=getSMARTFctyFiltList();
	var fl_fctylist='';
	if(fl_trmlfcty!='') {
		if(fl_smartfcty!='') { fl_fctylist=fl_trmlfcty+','+fl_smartfcty; }
		else { fl_fctylist=fl_trmlfcty; }
	}
	else {
	if(fl_smartfcty!='') { fl_fctylist=fl_smartfcty; }
		else { fl_fctylist=''; }
	}
	console.log('Facility List in Main:'+fl_fctylist);

	var htmlstr='';
	//var myurl="/RailSAHAY/GG_TrmlDashbordJson?Optn=TRML_LIST&LocnType="+locnflag+"&Locn="+locn+"&Cmdt="+cmdt+"&Lat="+userLat+"&Lng="+userLng+"&TrmlType="+trmltype+"&TrmlFcty="+fl_fctylist+"&SMARTFcty="+fl_smartfcty;
	var myurl="/RailSAHAY/GG_TrmlDashbordJson";
	//console.log('Main Terminal List:'+myurl);
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'TRML_LIST',LocnType:locnflag,Locn:locn,Cmdt:cmdt,Lat:userLat,Lng:userLng,TrmlType:trmltype,TrmlFcty:fl_fctylist,SMARTFcty:fl_smartfcty},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}	
		locnlist = new Array(obj.TrmlList.length);
		latList=new Array(locnlist);
		lngList=new Array(locnlist);
		locList=new Array(locnlist);
		fcltyList=new Array(locnlist);
		dvsnList=new Array(locnlist);


		for(var i=0;i<obj.TrmlList.length;i++)
		{
			locnlist[i]=new Array(11);
			locnlist[i][0]=obj.TrmlList[i].Sttn;
			locnlist[i][1]=obj.TrmlList[i].SttnName;
			locnlist[i][2]=obj.TrmlList[i].Dvsn;
			locnlist[i][3]=obj.TrmlList[i].Dstt;
			locnlist[i][4]=obj.TrmlList[i].AgCont;
			locnlist[i][5]=obj.TrmlList[i].TrckCont;
			locnlist[i][6]=obj.TrmlList[i].WHCont;
			locnlist[i][7]=obj.TrmlList[i].LabCont;
			locnlist[i][8]=obj.TrmlList[i].Lat;
			locnlist[i][9]=obj.TrmlList[i].Lng;
			locnlist[i][10]=obj.TrmlList[i].Fcty;
			latList[i]=Number(obj.TrmlList[i].Lat);
			lngList[i]=Number(obj.TrmlList[i].Lng);
			locList[i]=obj.TrmlList[i].Sttn+'-'+obj.TrmlList[i].SttnName;
			dvsnList[i]=obj.TrmlList[i].Dvsn;
			fcltyList[i]=obj.TrmlList[i].AgCont+','+obj.TrmlList[i].TrckCont+','+obj.TrmlList[i].WHCont+','+obj.TrmlList[i].LabCont;
			
			htmlstr+='<div class="w3-card-5 fcty-card" onclick="fetchSMART(\''+locnlist[i][0]+'\');"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+locnlist[i][0]+' ('+locnlist[i][1]+')</p></th></tr>';
			htmlstr+='<tr><td class="lbl">Division</td><td class="val">'+locnlist[i][2]+'</td></tr>';
			htmlstr+='<tr><td class="lbl">District</td><td class="val">'+locnlist[i][3]+'</td></tr>';
			htmlstr+='<tr><td class="lbl" sttn="\''+locnlist[i][0]+'\'">Logistics</td><td class="val" sttn="\''+locnlist[i][0]+'\'">';
			htmlstr+='<table class="tblFcty" sttn="\''+locnlist[i][0]+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+locnlist[i][4];
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+locnlist[i][5]+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+locnlist[i][6]+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+locnlist[i][7]+'</span></td></tr></table></td></tr></table>';
		  	htmlstr+='</div>';
		}
		$("#divTrmlList").html(htmlstr);
		plotMap();
		$("#divTrmlList").scrollTop(0);
		$('#divTrmlProf').html('');
		$('#divTrmlFcty').html('');
		$("#divLdngData").hide();

		}
		});
}
function getBrkUpStats(brktype)
{
	var cmdtdesc=$("#txtCmdt").val();
	actvbrkuptype=brktype;
	var cmdt='';
	if((cmdtdesc===undefined) || (cmdtdesc==""))
	{

	}
	else
	{

		if(cmdtdesc.indexOf("-")>-1)
		{
		cmdt=cmdtdesc.substring(0,cmdtdesc.indexOf("-"));
		}
		else
		{
		cmdt=cmdtdesc.toUpperCase();
		}
	}

	var fl_trmlfcty=getTrmlFctyFiltList();
	var fl_smartfcty=getSMARTFctyFiltList();
	var fl_fctylist='';
	if(fl_trmlfcty!='') {
		if(fl_smartfcty!='') { fl_fctylist=fl_trmlfcty+','+fl_smartfcty; }
		else { fl_fctylist=fl_trmlfcty; }
	}
	else {
	if(fl_smartfcty!='') { fl_fctylist=fl_smartfcty; }
		else { fl_fctylist=''; }
	}
	
	//alert("fl_fctylist"+fl_fctylist+" fl_smartfcty"+fl_smartfcty);

	//var myurl="/RailSAHAY/GG_TrmlDashbordJson?Optn=BRKUP_STATS&TrmlType=&BrkupType="+brktype+"&Cmdt="+cmdt+"&TrmlFcty="+fl_fctylist+"&SMARTFcty="+fl_smartfcty;
	var myurl="/RailSAHAY/GG_TrmlDashbordJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'BRKUP_STATS',BrkupType:brktype,Cmdt:cmdt,TrmlType:'',TrmlFcty:fl_fctylist,SMARTFcty:fl_smartfcty},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		brkuplist = new Array(obj.TrmlList.length);
		for(var i=0;i<obj.TrmlList.length;i++)
		{
			brkuplist[i]=new Array(6);
			brkuplist[i][0]=obj.TrmlList[i].Trty1;
			brkuplist[i][1]=obj.TrmlList[i].Trty2;
			brkuplist[i][2]=obj.TrmlList[i].PFT;
			brkuplist[i][3]=obj.TrmlList[i].CRT;
			brkuplist[i][4]=obj.TrmlList[i].LDNG;
			brkuplist[i][5]=obj.TrmlList[i].ULDG;
		}
		showBrkup(brktype,actvtrmltype);
		}
		});
}
function showBrkup(brktype,trmltype)
{
	var htmlstr='';
	var trmltypeindx=0;
	var trmltitl='';
	var cntr=0;	
	if(trmltype=='')
		trmltype=actvtrmltype;
	else
		actvtrmltype=trmltype;	
	
	switch(trmltype)
	{
		case 'PFT': 
			trmltypeindx=2;
			trmltitl="PFT";
			break;
		case 'CRT': 
			trmltypeindx=3;
			trmltitl="CRT";
			break;
		case 'LDNG': 
			trmltypeindx=4;
			trmltitl="LOADING LOCATION";
			break;
		case 'ULDG': 
			trmltypeindx=5;
			trmltitl="UNLOADING LOCATION";
			break;
	}
	$("#headTrmlPlot").html("AVAILABLE "+trmltitl);
	switch(brktype)
	{
		case 'S':
			htmlstr+='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>STATE</th><th>'+trmltitl+' COUNT</th></tr>';
			for(var i=0;i<brkuplist.length;i++)
			{
				if(brkuplist[i][trmltypeindx]!="0") 
				{
					htmlstr+='<tr class="dtls-row" onclick="getDtlsTrmlList(\'SN\',\''+brkuplist[i][0]+'\');"><td class="center">'+(cntr+1)+'</td><td>'+brkuplist[i][0]+'</td><td class="right">'+brkuplist[i][trmltypeindx]+'</td></tr>';
					cntr++;
				}

			}
			htmlstr+='</table>';
			break;
		case 'DD':
			htmlstr+='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>STATE</th><th>DISTRICT</th><th>'+trmltitl+' COUNT</th></tr>';
			for(var i=0;i<brkuplist.length;i++)
			{
				if(brkuplist[i][trmltypeindx]!="0") 
				{
					htmlstr+='<tr class="dtls-row" onclick="getDtlsTrmlList(\'DDN\',\''+brkuplist[i][1]+'\');"><td class="center">'+(cntr+1)+'</td><td>'+brkuplist[i][0]+'</td><td>'+brkuplist[i][1]+'</td><td class="right">'+brkuplist[i][trmltypeindx]+'</td></tr>';
					cntr++;
				}

			}
			htmlstr+='</table>';
			break;
		case 'Z':
			htmlstr+='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>ZONE</th><th>'+trmltitl+' COUNT</th></tr>';
			for(var i=0;i<brkuplist.length;i++)
			{
				if(brkuplist[i][trmltypeindx]!="0") 
				{
					htmlstr+='<tr class="dtls-row" onclick="getDtlsTrmlList(\'Z\',\''+brkuplist[i][0]+'\');"><td class="center">'+(cntr+1)+'</td><td>'+brkuplist[i][0]+'</td><td class="right">'+brkuplist[i][trmltypeindx]+'</td></tr>';
					cntr++;
				}

			}
			htmlstr+='</table>';
			break;
		case 'D':
			htmlstr+='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>ZONE</th><th>DIVISION</th><th>'+trmltitl+' COUNT</th></tr>';
			for(var i=0;i<brkuplist.length;i++)
			{
				if(brkuplist[i][trmltypeindx]!="0") 
				{
					var dvsncode=brkuplist[i][1].substring(0,brkuplist[i][1].indexOf("-"));
					htmlstr+='<tr class="dtls-row" onclick="getDtlsTrmlList(\'D\',\''+dvsncode+'\');"><td class="center">'+(cntr+1)+'</td><td>'+brkuplist[i][0]+'</td><td>'+brkuplist[i][1]+'</td><td class="right">'+brkuplist[i][trmltypeindx]+'</td></tr>';
					cntr++;
				}

			}
			htmlstr+='</table>';
			break;
		}
	$('#divTrmlPlot').html(htmlstr);
	$("#divTrmlPlot").scrollTop(0);
	$(".dtls-row").click(function() {
		$(".dtls-row").removeClass('row-hglt');
		$(this).addClass('row-hglt');
	});
	$('#divTrmlProf').html('');
	$('#divTrmlFcty').html('');
	$('#divTrmlList').html('');
}

function getDtlsTrmlList(locnflag,locn)
{
	var cmdtdesc=$("#txtCmdt").val();
	var cmdt='';
	var htmlstr='';
	if((cmdtdesc===undefined) || (cmdtdesc==""))
	{

	}
	else
	{

		if(cmdtdesc.indexOf("-")>-1)
		{
		cmdt=cmdtdesc.substring(0,cmdtdesc.indexOf("-"));
		}
		else
		{
		cmdt=cmdtdesc.toUpperCase();
		}
	}

	var fl_trmlfcty=getTrmlFctyFiltList();
	var fl_smartfcty=getSMARTFctyFiltList();
	var fl_fctylist='';
	if(fl_trmlfcty!='') {
		if(fl_smartfcty!='') { fl_fctylist=fl_trmlfcty+','+fl_smartfcty; }
		else { fl_fctylist=fl_trmlfcty; }
	}
	else {
	if(fl_smartfcty!='') { fl_fctylist=fl_smartfcty; }
		else { fl_fctylist=''; }
	}
	
console.log("LocnType:"+locnflag+" locn:"+locn+" TrmlType:"+actvtrmltype+" TrmlFcty:"+fl_fctylist+" SMARTFcty:"+fl_smartfcty);

	//var myurl="/RailSAHAY/GG_TrmlDashbordJson?Optn=TRML_LIST&LocnType="+locnflag+"&Locn="+locn+"&Cmdt="+cmdt+"&Lat=&Lng=&TrmlType="+actvtrmltype+"&TrmlFcty="+fl_fctylist+"&SMARTFcty="+fl_smartfcty;
	var myurl="/RailSAHAY/GG_TrmlDashbordJson";
	console.log('Terminal List:'+myurl);
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'TRML_LIST',LocnType:locnflag,Locn:locn,Cmdt:cmdt,Lat:'',Lng:'',TrmlType:actvtrmltype,TrmlFcty:fl_fctylist,SMARTFcty:fl_smartfcty},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		console.log('Terminal List length:'+obj.TrmlList.length);
		locnlist = new Array(obj.TrmlList.length);
		for(var i=0;i<obj.TrmlList.length;i++)
		{
			locnlist[i]=new Array(11);
			locnlist[i][0]=obj.TrmlList[i].Sttn;
			locnlist[i][1]=obj.TrmlList[i].SttnName;
			locnlist[i][2]=obj.TrmlList[i].Dvsn;
			locnlist[i][3]=obj.TrmlList[i].Dstt;
			locnlist[i][4]=obj.TrmlList[i].AgCont;
			locnlist[i][5]=obj.TrmlList[i].TrckCont;
			locnlist[i][6]=obj.TrmlList[i].WHCont;
			locnlist[i][7]=obj.TrmlList[i].LabCont;
			locnlist[i][8]=obj.TrmlList[i].Lat;
			locnlist[i][9]=obj.TrmlList[i].Lng;
			locnlist[i][10]=obj.TrmlList[i].Fcty;

			htmlstr+='<div class="w3-card-5 fcty-card" onclick="fetchSMART(\''+locnlist[i][0]+'\');"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+locnlist[i][0]+' ('+locnlist[i][1]+')</p></th></tr>';
			htmlstr+='<tr><td class="lbl">Division</td><td class="val">'+locnlist[i][2]+'</td></tr>';
			htmlstr+='<tr><td class="lbl">District</td><td class="val">'+locnlist[i][3]+'</td></tr>';
			htmlstr+='<tr><td class="lbl" sttn="\''+locnlist[i][0]+'\'">Logistics</td><td class="val" sttn="\''+locnlist[i][0]+'\'">';
			htmlstr+='<table class="tblFcty" sttn="\''+locnlist[i][0]+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+locnlist[i][4];
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+locnlist[i][5]+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+locnlist[i][6]+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+locnlist[i][7]+'</span></td></tr></table></td></tr></table>';
		  	htmlstr+='</div>';

		}
		$("#divTrmlList").html(htmlstr);
		$("#divTrmlList").scrollTop(0);
		$('#divTrmlProf').html('');
		$('#divTrmlFcty').html('');
		}
		});
}

function getSpecStats(trmltype)
{
	actvtrmltype=trmltype;	
	
	var locnflag=$(".locnflag").attr("locn");

	if((locnflag=="IR") || (locnflag==""))
		getBrkUpStats(actvbrkuptype);
	else
		getMainTrmlList(actvtrmltype);
}
$(document).ready(function(){
	getSmryStats();
	getBrkUpStats('S');
		
});

function fetchSMART(trml)
{
	var htmlstr='';
	var inpttrml='';
	if(trml.indexOf(' ')>-1)
	{
		inpttrml=trml.substring(0,trml.indexOf(' '));
	}
	else
	{
		inpttrml=trml;
	}
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=SMART_FCTY&Sttn="+inpttrml;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'SMART_FCTY',Sttn:inpttrml},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		var agcont=obj.AgCont;
		var trckcont=obj.TrckCont;
		var whcont=obj.WHCont;
		var labcont=obj.LabCont;
		htmlstr+='<div class="btn-group btn-group-sm d-flex"><button type="button" class="btn btn-primary w-100" onclick="showAggr1();">Aggregators <span class="badge badge-light">'+agcont+'</span></button>';
		htmlstr+='<button type="button" class="btn btn-success w-100" onclick="showTrck1();">Truckers <span class="badge badge-light">'+trckcont+'</span></button>';
  		htmlstr+='<button type="button" class="btn btn-danger w-100" onclick="showWH1();">Warehouse <span class="badge badge-light">'+whcont+'</span></button>';
		htmlstr+='<button type="button" class="btn btn-warning w-100" onclick="showLab1();">Labour <span class="badge badge-light">'+labcont+'</span></button></div><br/>';
		htmlstr+='<div class="card"><div class="card-header text-left lsp-header"><span class="lsptitle">Aggregators</span><span class="float-right"><span class="dot1"></span>&nbsp;Company | <span class="dotIndv"></span>&nbsp;Individual</span></div>';
		htmlstr+='<div class="card-body text-left" id="divFctyDtls"></div></div></div></div></div>';
/*
		htmlstr+='<div class="row"><div class="col-lg-12 col-sm-12"><table class="table tbl-fcty" style="border:0pt;"><tr style="border:0pt;"><td><div class="courses-container"><div class="course" onclick="showAggr1();">';
		htmlstr+='<div class="cardaggr"><i class="fas fa-store-alt fa-sm"></i></div><div class="course-info"><h2 id="headAgCont">'+agcont+'</h2><p class="fctyhead">AGGREGATORS</p></div></div></div></td>';
		htmlstr+='<td><div class="courses-container"><div class="course" onclick="showTrck1();"><div class="cardtrck"><i class="fas fa-truck fa-sm"></i></div><div class="course-info"><h2 id="headTrckCont">'+trckcont+'</h2><p class="fctyhead">TRUCKERS</p>';
		htmlstr+='</div></div></div></td><td><div class="courses-container"><div class="course" onclick="showWH1();"><div class="cardwh"><i class="fas fa-warehouse fa-sm"></i></div><div class="course-info"><h2 id="headWHCont">'+whcont+'</h2><p class="fctyhead">WAREHOUSE</p>';
		htmlstr+='</div></div></div></td><td><div class="courses-container"><div class="course" onclick="showLab1();"><div class="cardlab"><i class="fas fa-people-carry fa-sm"></i></div><div class="course-info"><h2 id="headLabCont">'+labcont+'</h2><p class="fctyhead">LABOURS</p>';
		htmlstr+='</div></div></div></td></tr></table></div></div><div class="row"><div class="col-lg-12 col-sm-12">';
		htmlstr+='<div class="card"><div class="card-header text-left lsp-header"><span class="lsptitle">Aggregators</span><span class="float-right"><span class="dot1"></span>&nbsp;Company | <span class="dotIndv"></span>&nbsp;Individual</span></div>';
		htmlstr+='<div class="card-body text-left" id="divFctyDtls"></div></div></div></div></div>';
*/
		htmlAg1='<ul class="list-group list-group-flush">';
		htmlTrck1='<ul class="list-group list-group-flush">';
		htmlWh1='<ul class="list-group list-group-flush">';
		htmlLab1='<ul class="list-group list-group-flush">';
		for(var i=0;i<obj.SMARTFcty.length;i++)
		{
			var lspname=obj.SMARTFcty[i].LSPName;
			var gstn=obj.SMARTFcty[i].GSTN;
			var addr=obj.SMARTFcty[i].Addr;
			var city=obj.SMARTFcty[i].City;
			var stat=obj.SMARTFcty[i].Stat;
			var pin=obj.SMARTFcty[i].Pin;
			var contpers=obj.SMARTFcty[i].ContPers;
			var mob=obj.SMARTFcty[i].Mob;
			var email=obj.SMARTFcty[i].Email;
			var factype=obj.SMARTFcty[i].FacType;
			var facdesc=obj.SMARTFcty[i].FacDesc;
			var addlinfo=obj.SMARTFcty[i].AddlInfo;
			var faccontpers=obj.SMARTFcty[i].FctyContPers;
			var facmob=obj.SMARTFcty[i].FctyMob;
			var facemail=obj.SMARTFcty[i].FctyEmail;
			var srvgcont=obj.SMARTFcty[i].SrvgCont;
			var lsptype=obj.SMARTFcty[i].LSPType;
			var url=obj.SMARTFcty[i].URL;

			if(factype=="A")
			{
				htmlAg1+='<li class="list-group-item">';
				if(lsptype=="C")
					htmlAg1+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlAg1+='<span class="dotIndv"></span>&nbsp;';

				htmlAg1+='<span class="lspname">'+lspname+'</span>';
				htmlAg1+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlAg1+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlAg1+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlAg1+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlAg1+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlAg1+='</li>';
			}
			if(factype=="T")
			{
				htmlTrck1+='<li class="list-group-item">';
				if(lsptype=="C")
					htmlTrck1+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlTrck1+='<span class="dotIndv"></span>&nbsp;';

				htmlTrck1+='<span class="lspname">'+lspname+'</span>';
				htmlTrck1+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlTrck1+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlTrck1+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlTrck+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlTrck1+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p></td>';
				htmlTrck1+='</li>';
			}
			if(factype=="W")
			{
				htmlWh1+='<li class="list-group-item">';
				if(lsptype=="C")
					htmlWh1+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlWh1+='<span class="dotIndv"></span>&nbsp;';

				htmlWh1+='<span class="lspname">'+lspname+'</span>';
				htmlWh1+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlWh1+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlWh1+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlWh1+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlWh1+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlWh1+='</li>';
			}
			if(factype=="L")
			{
				htmlLab1+='<li class="list-group-item">';
				if(lsptype=="C")
					htmlLab1+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlLab1+='<span class="dotIndv"></span>&nbsp;';

				htmlLab1+='<span class="lspname">'+lspname+'</span>';
				htmlLab1+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlLab1+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlLab1+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlLab1+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlLab1+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlLab1+='</li>';
			}

		}
		htmlAg1+='</ul>';
		htmlTrck1+='</ul>';
		htmlWh1+='</ul>';
		htmlLab1+='</ul>';
		$("#fctytrml").html("Logistics Facilities at: "+trml);
		$("#divTrmlFcty").html(htmlstr);
		fetchProf(trml);
		showAggr1();
		}
		});
}
function showAggr1()
{
	$("#divFctyDtls").html(htmlAg1);
	$(".lsptitle").html("Aggregators");
}
function showWH1()
{
	$("#divFctyDtls").html(htmlWh1);
	$(".lsptitle").html("Warehouse");

}
function showTrck1()
{
	$("#divFctyDtls").html(htmlTrck1);
	$(".lsptitle").html("Truckers");

}
function showLab1()
{
	$("#divFctyDtls").html(htmlLab1);
	$(".lsptitle").html("Labour");

}
function fetchProf(trml)
{
	var htmlstr='<div class="w3-container">';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_PROFILE&Sttn="+trml;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'STTN_PROFILE',Sttn:trml},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
		for(var i=0;i<obj.SttnProf.length;i++)
		{
			var sttn=obj.SttnProf[i].Sttn;
			var trfciwrd=obj.SttnProf[i].TrfcIwrd;
			if(trfciwrd=='T')
				trfciwrd="TRAIN LOAD";
			if(trfciwrd=='W')
				trfciwrd="WAGON LOAD";

			var trfcowrd=obj.SttnProf[i].TrfcOwrd;
			if(trfcowrd=='T')
				trfcowrd="TRAIN LOAD";
			if(trfcowrd=='W')
				trfcowrd="WAGON LOAD";
			var halfrake=obj.SttnProf[i].HalfRake;
			var fullrake=obj.SttnProf[i].FullRake;
			var dvsn=obj.SttnProf[i].Dvsn;
			var zone=obj.SttnProf[i].Zone;
			var diflag=obj.SttnProf[i].DIFlag;
			var srvg=obj.SttnProf[i].Srvg;
			var srvgdesc='';
			if((diflag=="D") || (diflag=="I"))
				srvgdesc='SERVED BY :'+srvg;
			var dstt=obj.SttnProf[i].Dstt;
			var dsttname=obj.SttnProf[i].DsttName;
			var smart=obj.SttnProf[i].Smart;
			var smartlist=smart.split(',');
			var fcty1=obj.SttnProf[i].Fcty1;
			var fcty1list=fcty1.split('/');
			var highplat=obj.SttnProf[i].HighPlat;
			var loossdng=obj.SttnProf[i].LoosSdng;
			var fcty3=obj.SttnProf[i].Fcty3;
			var fcty3list=fcty3.split('/');
			var cmdtiw=obj.SttnProf[i].CmdtIW;
			var cmdtow=obj.SttnProf[i].CmdtOW;
			var bsnshrs=obj.SttnProf[i].BsnsHrs;
			var wrkghrs=obj.SttnProf[i].WrkgHrs;
			var LSPName=obj.SttnProf[i].LSPName;
			var ContPers=obj.SttnProf[i].ContPers;
			var Mobl=obj.SttnProf[i].Mobl;
			var Email=obj.SttnProf[i].Email;

			htmlstr+='<div class="w3-card-5"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttn+'</p></th></tr>';
			if(LSPName!="")
			{
				htmlstr+='<tr><td class="lbl">Owner</td><td class="val">'+LSPName+'<hr/><p class="owner"><span class="lbl">Contact:</span><span class="val">'+ContPers+'</span></p>';
				if(Mobl!="")
					htmlstr+='<i class="fas fa-mobile-alt"></i> '+Mobl;
				if(Email!="")
					htmlstr+='&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <i class="fas fa-envelope-square"></i>'+Email;
				
				htmlstr+='</td></tr>';
			}
			if(srvgdesc!='')
				htmlstr+='<tr><td class="lbl">Description</td><td class="val">'+srvgdesc+'</td></tr>';

			htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">'+dvsn+'/'+zone+'</td></tr>';
			htmlstr+='<tr><td class="lbl">District</td><td class="val">'+dstt+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Traffic Type</td><td class="val"><b>Unloading:</b> '+trfciwrd+', <b>Loading:</b> '+trfcowrd+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Commodity</td><td class="val"><b>Unloading:</b> '+cmdtiw+'<br/><b>Loading:</b> '+cmdtow+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Working Hours</td><td class="val">'+wrkghrs+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Terminal Facilities</td><td class="val"><table>';
			if(fcty1list[0]=="Y")
				htmlstr+='<tr><td><ul><li>High Mast/Lighting Available</li>';
				htmlstr+='<li>Line Count: '+fcty1list[1]+'</li>';
			if(fcty1list[2]!="")
				htmlstr+='<li>Handling Type: '+fcty1list[2]+'</li>';
			if(fcty1list[3]=="Y")
				htmlstr+='<li>Freight Charges on Through Distance Basis</li>';
			if(fcty1list[4]=="Y")
				htmlstr+='<li>Weighbridge Available</li>';
			if(fcty1list[5]!="0")
				htmlstr+='<li>Shed Length: '+fcty1list[5]+' Mtr.</li>';
			if(fcty3list[0]=="Y")
				htmlstr+='<li>Warehouse Available</li>';
			if(fcty3list[1]!="0")
				htmlstr+='<li>Distance from Serving Station: '+fcty3list[1]+' Kms</li>';
			if((fcty3list[2]!="NA") && (fcty3list[2]!=""))
				htmlstr+='<li>Alternate Weighbridge Locations: '+fcty3list[2]+'</li>';
			if(fcty3list[3]!="")
				htmlstr+='<li>Siding Owner: '+fcty3list[3]+'</li>';
			if(fcty3list[4]!="")
				htmlstr+='<li>EOL Type: '+fcty3list[4]+'</li>';
			if(fcty3list[5]=="Y")
				htmlstr+='<li>TANK Handling Available</li></ul></td></tr>';

			htmlstr+='</table></td></tr></table></div>';
			$("#divTrmlProf").html(htmlstr);
		}
		}
	});
}

function fetchData()
{
	getSmryStats();
		
}
function initMap()
{
	/*do nothing*/
}
function plotMap() 
{
	
        var mapIcon="/RailSAHAY/resources/images/mapnew3.png";
	var crntIcon="/RailSAHAY/resources/images/crntflag.png";
	var locnflag=$(".locnflag").attr("locn");
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:5,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divTrmlPlot"),mapProp);
	if(locnflag=="NM")
	{
		const myCircle = new google.maps.Circle({
      		strokeColor: "#FF0000",
		strokeOpacity: 0.6,
      		strokeWeight: 2,
      		fillColor: "#FF0000",
      		fillOpacity: 0.20,
      		map,
      		center: { lat: userLat, lng: userLng },
      		radius: 50000
    		});
	}
	var image = {
		    url: mapIcon,
		    size: new google.maps.Size(16, 21)
		  };

	var crntImage = {
		    url: crntIcon,
		    size: new google.maps.Size(21, 30)
		  };
                  
                        var htmlstr='';
                	var marker1 =new Array(latList.length);
                	var contentString1 =new Array(latList.length);
		if(locnflag=="NM")
		{
                	var markerCrnt = new google.maps.Marker({
			map: map,
			image: crntImage,
			animation: google.maps.Animation.DROP,
			position: {lat: userLat, lng:userLng},
			title:  'My Location',		
			icon: crntImage 
			});
		}

		for(var r=0;r<latList.length;r++){
                    htmlstr="";
                    
		var custom_title=r+"/"+locList[r];
                var fctylist=fcltyList[r].split(",");
                marker1[r] = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: latList[r]*1, lng:lngList[r]*1},
		title:  custom_title,		
		icon: image
		});
                var sttn=locList[r].substring(0,locList[r].indexOf('-'));
                htmlstr+='<div class="w3-card-5"><table class="styletable">';
                htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+locList[r]+'</p></th></tr>';
                htmlstr+='<tr><td class="lbl">Division</td><td class="val">'+dvsnList[r]+'</td></tr>';
                htmlstr+='<tr><td class="lbl" sttn="\''+locList[r]+'\'">Logistics</td><td class="val" sttn="\''+locList[r]+'\'">';
                htmlstr+='<table class="tblFcty" onclick="fetchSMART(\''+sttn+'\');" ><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+fctylist[0];
                htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+fctylist[1]+'</span></td>';
                htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+fctylist[2]+'</span></td>';
                htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+fctylist[3]+'</span></td></tr></table></td></tr></table></div>';
                
		 contentString1[r] =  htmlstr;		
		 infowindow1[r]  	= new google.maps.InfoWindow({content: contentString1[r] });
	         marker1[r].addListener('click', function() {	
			for(var s=0;s<latList.length;s++){
			    	infowindow1[s].close(); 
	                }

			var titl=this.getTitle();
			var indx=parseInt(titl.substring(0,titl.indexOf("/")));
	         	infowindow1[indx].open(map, marker1[indx]);
 			 });
                }
	/* To remove any opened info window start*/
	google.maps.event.addListener( map, 'click', function() { 
		for(var s=0;s<latList.length;s++){
	    	infowindow1[s].close(); 
                }
	});
	/* To remove any opened info window end*/
	map.setCenter(new google.maps.LatLng(latList[0]*1, lngList[0]*1));
	if(locnflag=="NM")
	{
		map.setZoom(8);
	}

}
function setIR()
{
	$("#txtLocn").val('');
	$("#txtCmdt").val('');
	return true;
}
