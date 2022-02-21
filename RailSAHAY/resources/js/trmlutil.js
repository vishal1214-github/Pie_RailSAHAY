var userLat;
var userLng;
var statstr='';


var latList=null;
var lngList=null;
var locList=null;
var dvsnList=null;
var fcltyList=null;
var agcontL=null;
var trckcontL=null;
var whcontL=null;
var labcontL=null;
var sttnN=null;

htmlAg='';
htmlTrck='';
htmlWh='';
htmlLab='';

for (var i=0; i < aState.length;++i){
statstr+= '<option value="'+aState[i]+'" />'; // Helplist for States
}
var dsttstr='';
for (var i=0; i < aDistrict.length;++i){
dsttstr+= '<option value="'+aDistrict[i]+'" />'; // Helplist for Districts
}
var dvsnstr='';
for (var i=0; i < aDvsn.length;++i){
dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
}
var zonestr='';
for (var i=0; i < aZone.length;++i){
zonestr+= '<option value="'+aZone[i]+'" />'; // Helplist for Zones
}
var pincodestr='';
if (typeof aPinCode!== 'undefined') {
 	for (var i=0; i < aPinCode.length;++i)
	{
		pincodestr+= '<option value="'+aPinCode[i]+'" />'; // Helplist for Pincodes
	}
}

    $(document).ready(function(){
     $(".dropdown-menu a").click(function(){
      var locntype=$(this).attr("value");
      $(this).parents(".dropdown").find('a.locnflag').html($(this).text() + ' <span class="caret"></span>');
      $(this).parents(".dropdown").find('a.locnflag').attr("locn",locntype);
      $("#txtLocn").removeAttr("disabled"); 

      switch(locntype)
      {

	case 'PC':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = pincodestr;
		break;

	case 'S':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = statstr;
		break;
	case 'DD':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = dsttstr;
		break;

	case 'D':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = dvsnstr;
		break;

	case 'Z':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = zonestr;
		break;

	case 'NM':
		$("#txtLocn").attr("disabled", "disabled"); 
		$("#txtLocn").val("");
		break;

      }
    });
});
function fetchTrmlList(locn)
{
	/*var locn=$("#txtSttn").val();*/
	locn=locn.toUpperCase();
	var htmlstr='<div class="w3-container">';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_LIST&Sttn="+locn;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'STTN_LIST',Sttn:locn},
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
			
			latList = new Array(obj.SttnList.length);
			lngList = new Array(obj.SttnList.length);
			locList= new Array(obj.SttnList.length);
            dvsnList = new Array(obj.SttnList.length);
            fcltyList= new Array(obj.SttnList.length);
                        if(obj.SttnList.length==0)
			{
				noDataFound();
				return false;
			}
			for(var i=0;i<obj.SttnList.length;i++)
			{
				
				latList[i]=obj.SttnList[i].Lat;
                lngList[i]=obj.SttnList[i].Lan;
                locList[i]=obj.SttnList[i].Sttn;
                dvsnList[i]=obj.SttnList[i].Dvsn+'/'+obj.SttnList[i].Zone;
                fcltyList[i]=obj.SttnList[i].Fcty;
				
				var sttn=obj.SttnList[i].Sttn;
				var dvsn=obj.SttnList[i].Dvsn;
				var zone=obj.SttnList[i].Zone;
				var stat=obj.SttnList[i].Stat;
				var fcty=obj.SttnList[i].Fcty;
				var LSPName=obj.SttnList[i].LSPName;
				var ContPers=obj.SttnList[i].ContPers;
				var Mobl=obj.SttnList[i].Mobl;
				var Email=obj.SttnList[i].Email;
				var sttncode=sttn.substring(0,sttn.indexOf("("));
				var fctylist=fcty.split(",");
				htmlstr+='<div class="w3-card-5"><table class="styletable">';
				htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttn+'<button class="btn btn-light btn-sm float-right"><i class="fas fa-map-marker-alt" onclick="initMapS('+latList[i]+','+lngList[i]+',\''+locList[i]+'\');" data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor=\'pointer\';" alt="View on Map" title="View on Map"  ></i></button></p></th></tr>';
				if(LSPName!="")
				{
					htmlstr+='<tr><td class="lbl">Owner</td><td class="val">'+LSPName+'<hr/><p class="owner"><span class="lbl">Contact:</span><span class="val">'+ContPers+'</span></p>';
					if(Mobl!="")
						htmlstr+='<i class="fas fa-mobile-alt"></i> '+Mobl;
					if(Email!="")
						htmlstr+='&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <i class="fas fa-envelope-square"></i>'+Email;
					
					htmlstr+='</td></tr>';
				}
				htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">'+dvsn+'/'+zone+'</td></tr>';
				htmlstr+='<tr><td class="lbl" sttn="\''+sttn+'\'">Logistics Facilities</td><td class="val" sttn="\''+sttn+'\'">';
				htmlstr+='<table class="tblFcty" onclick="fetchSMARTFcty(\''+sttn+'\');" sttn="\''+sttn+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+fctylist[0];
				htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+fctylist[1]+'</span></td>';
				htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+fctylist[2]+'</span></td>';
				htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+fctylist[3]+'</span></td></tr></table></td></tr>';
				htmlstr+='<tr><td colspan="2" class="val"><a href="javascript:void(0)" class="card-link1 showtrmlprof" data-toggle="collapse" data-target="#divTrmlProf'+i+'"  id="showTrmlProf'+i+'" onclick="showSttnProf(\''+sttncode+'\','+i+');">Show More..</a><a href="javascript:void(0)" class="card-link1 hidetrmlprof" data-toggle="collapse" id="hideTrmlProf'+i+'" data-target="#divTrmlProf'+i+'" onclick="hideSpecTrmlProf('+i+');">Show Less..</a></td></tr></table>';
		  		htmlstr+='<div id="divTrmlProf'+i+'" class="collapse"></div></div>';
			}
			htmlstr+='</div>';
			$("#divTrmlList").html(htmlstr);
			$(".hidetrmlprof").hide();
		}
	});
}
function fetchTrmlProf(trml)
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
			htmlstr+='<tr><td class="lbl" sttn="\''+sttn+'\'">Logistics Facilities</td><td class="val" sttn="\''+sttn+'\'">';
			htmlstr+='<table class="tblFcty" onclick="fetchSMARTFcty(\''+sttn+'\');"  sttn="\''+sttn+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+smartlist[0];
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+smartlist[1]+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+smartlist[2]+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+smartlist[3]+'</span></td></tr></table></td></tr>';
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
function fetchLocnList(locntype)
{
	var locnflag=$(".locnflag").attr("locn");
	console.log("Location Flag:"+locnflag);
	var locndesc=$("#txtLocn").val();
	var cmdtdesc=$("#txtCmdt").val();
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

	var htmlstr='<div class="w3-container">';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn="+locntype+"&LocnFlag="+locnflag+"&Locn="+locn+"&Cmdt="+cmdt+"&Lat="+userLat+"&Lng="+userLng;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	//console.log(myurl);
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:locntype,LocnFlag:locnflag,Locn:locn,Cmdt:cmdt,Lat:userLat,Lng:userLng},
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

                        if(obj.SttnList.length==0)
			{
				noDataFound();
				return false;
			}
                    latList = new Array(obj.SttnList.length);
                    lngList = new Array(obj.SttnList.length);
                    locList= new Array(obj.SttnList.length);
                    dvsnList = new Array(obj.SttnList.length);
                   
                     agcontL=new Array(obj.SttnList.length);
                     trckcontL=new Array(obj.SttnList.length);
                     whcontL=new Array(obj.SttnList.length);
                     labcontL=new Array(obj.SttnList.length);
                     sttnN=new Array(obj.SttnList.length);
		for(var i=0;i<obj.SttnList.length;i++)
		{
			
                        latList[i]=obj.SttnList[i].Lat;
                        lngList[i]=obj.SttnList[i].Lan;
                        locList[i]=obj.SttnList[i].Sttn;
                        dvsnList[i]=obj.SttnList[i].Dvsn+'/'+obj.SttnList[i].Zone;
                        agcontL[i]=obj.SttnList[i].AgCont;
                        trckcontL[i]=obj.SttnList[i].TrckCont;
                        whcontL[i]=obj.SttnList[i].WHCont;
                        labcontL[i]=obj.SttnList[i].LabCont;
                        sttnN[i]=obj.SttnList[i].SttnName;
                        
                        var zone=obj.SttnList[i].Zone;
			var zonename=obj.SttnList[i].ZoneName;
			var dvsn=obj.SttnList[i].Dvsn;
			var dvsnname=obj.SttnList[i].DvsnName;
			var sttn=obj.SttnList[i].Sttn;
			var sttnname=obj.SttnList[i].SttnName;
			var dstt=obj.SttnList[i].Dstt;
			var stat=obj.SttnList[i].Stat;
			var agcont=obj.SttnList[i].AgCont;
			var trckcont=obj.SttnList[i].TrckCont;
			var whcont=obj.SttnList[i].WHCont;
			var labcont=obj.SttnList[i].LabCont;

			htmlstr+='<div class="w3-card-5"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttn+' ('+sttnname+')</p></th></tr>';
			htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">('+dvsn+'-'+dvsnname+')/'+zonename+'</td></tr>';
			/*htmlstr+='<tr><td class="lbl">District</td><td class="val">'+dstt+'/'+stat+'</td></tr>';*/
			htmlstr+='<tr><td class="lbl" sttn="\''+sttn+'\'">Logistics Facilities</td><td class="val" onclick="fetchSMARTFcty(\''+sttn+'\');" sttn="\''+sttn+'\'">';
			htmlstr+='<table class="tblFcty" sttn="\''+sttn+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+agcont;
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+trckcont+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+whcont+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+labcont+'</span></td></tr></table></td></tr>';
			htmlstr+='<tr><td colspan="2" class="val"><a href="javascript:void(0)" class="card-link1 showtrmlprof" data-toggle="collapse" data-target="#divTrmlProf'+i+'"  id="showTrmlProf'+i+'" onclick="showSttnProf(\''+sttn+'\','+i+');">Show More..</a><a href="javascript:void(0)" class="card-link1 hidetrmlprof" data-toggle="collapse" id="hideTrmlProf'+i+'" data-target="#divTrmlProf'+i+'" onclick="hideSpecTrmlProf('+i+');">Show Less..</a></td></tr></table>';
		  	htmlstr+='<div id="divTrmlProf'+i+'" class="collapse"></div></div>';
		}
		htmlstr+='</div>';
		$("#divTrmlList").html(htmlstr);
		if(obj.SttnList.length>0)
		{
			$("#mapIm").show();
		}
		$(".hidetrmlprof").hide();

	}
	});
}
function showPosition() {
// If geolocation is available, try to get the visitor's position
	if(navigator.geolocation)
	{
		console.log("Geolocation object available");
		navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
	}
	else
	{
		alert("Sorry, your browser does not support capturing your location.");
	}
};

// Define callback function for successful attempt
function successCallback(position)
{
	userLat=position.coords.latitude;
	userLng=position.coords.longitude;
}
function errorCallback()
{
	//alert("Sorry, your location could not be identified for nearby freight terminals");
}
function fetchTimeTblRout(sttnfrom, sttnto)
{
	$("#divTTSChdDtls").hide();
	$("#divTTRout").html("");
	var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>FROM</th><th>DEP TIME</th><th>TO</th><th>ARR TIME</th><th>TOTAL TIME</th><th>DAYS OF RUN</th></tr>';
	var htmltranlist='';
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=TTROUT&SttnFrom="+sttnfrom+"&SttnTo="+sttnto;
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
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		if(obj.TTRout.length==0)
		{
			noDataFound();
			return false;
		}

		for(var i=0;i<obj.TTRout.length;i++)
		{
			var schd=obj.TTRout[i].Schd;
			var sttnfrom=obj.TTRout[i].SttnFrom;
			var sttnfromname=obj.TTRout[i].SttnFromName;
			var dvsnfrom=obj.TTRout[i].DvsnFrom;
			var sttnto=obj.TTRout[i].SttnTo;
			var sttntoname=obj.TTRout[i].SttnToName;
			var dvsnto=obj.TTRout[i].DvsnTo;
			var strttime=obj.TTRout[i].StrtTime;
			var totltime=obj.TTRout[i].TotlTime;
			var daysofrun='';
			var mon=obj.TTRout[i].Mon;
			if(mon=='Y')
				daysofrun+=' Mon';
			var tue=obj.TTRout[i].Tue;
			if(tue=='Y')
				daysofrun+=' Tue';
			var wed=obj.TTRout[i].Wed;
			if(wed=='Y')
				daysofrun+=' Wed';
			var thu=obj.TTRout[i].Thu;
			if(thu=='Y')
				daysofrun+=' Thu';
			var fri=obj.TTRout[i].Fri;
			if(fri=='Y')
				daysofrun+=' Fri';
			var sat=obj.TTRout[i].Sat;
			if(sat=='Y')
				daysofrun+=' Sat';
			var sun=obj.TTRout[i].Sun;
			if(sun=='Y')
				daysofrun+=' Sun';

			if(daysofrun==" Mon Tue Wed Thu Fri Sat Sun")
				daysofrun="ALL DAYS";

			var arvltime=obj.TTRout[i].ArvlTime;

			htmltranlist+='<div class="ptrancard" onclick="fetchSchdDtls(\''+schd+'\');"><table style="width:100%;" class="table table-striped table-ttrout">';
			htmltranlist+='<tr><td colspan="2" class="tranod">'+sttnfromname+'('+sttnfrom+') &nbsp; <i class="fas fa-long-arrow-alt-right"></i> &nbsp;';
			htmltranlist+=sttntoname+' ('+sttnto+')</td></tr><tr><td>Departs On: &nbsp; '+daysofrun+'</td><td style="width:60%;">';
			htmltranlist+='<span class="badge badge-success text-white">DEP</span>&nbsp;'+strttime+'&nbsp;&nbsp;|&nbsp;&nbsp;<span class="badge badge-danger">ARR</span>&nbsp;'+arvltime+'&nbsp;&nbsp;|&nbsp;&nbsp;<span class="badge badge-info">TOTAL TIME</span>&nbsp;'+totltime+'</td></tr></table></div>';

			/*htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttnfromname+'">'+sttnfrom+'</a> ('+dvsnfrom+')</td><td class="right">'+strttime+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttntoname+'">'+sttnto+'</a> ('+dvsnto+')</td><td class="right">'+arvltime+'</td><td class="right">'+totltime+'</td><td class="right tdlink" onclick="fetchSchdDtls(\''+schd+'\');">'+daysofrun+'</td></tr>';*/
		}
		htmlstr+='</table>';
		$("#divTTRout").html(htmltranlist);
		}
		});
}
function fetchSchdDtls(schd)
{
	$("#divTTSChdDtls").show();
	$("#divTTSchd").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlschdstr="";
	var htmltranstr='';
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=TTSCHD&Schd="+schd;
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
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.TTSchd.length;i++)
		{
			var sttn=obj.TTSchd[i].Sttn;
			var sttnname=obj.TTSchd[i].SttnName;
			var dvsn=obj.TTSchd[i].Dvsn;
			var zone=obj.TTSchd[i].Zone;
			var arvltime=obj.TTSchd[i].ArvlTime;
			var dprttime=obj.TTSchd[i].DprtTime;
			var dist=obj.TTSchd[i].Dist;
			if(i==0) {
				sttn=sttnname+" (SOURCE)";	
			}
			else if (i==(obj.TTSchd.length-1)) {
					sttn=sttnname+" (DESTINATION)";
			}
			else
			{
				if(sttnname!='')
					sttn=sttnname;
			}
			
				if(i==0)
				{
					htmlschdstr+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-success"><i class="entypo-feather"></i></div>';
				}
				else if(i==(obj.TTSchd.length-1))
				{
                                        htmlschdstr+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-danger"><i class="entypo-feather"></i></div>';
                                }
				else
				{
                                        htmlschdstr+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-primary"><i class="entypo-feather"></i></div>';
                                }
					  
		                htmlschdstr+='<div class="timeline-label"><p class="sttncode">'+sttn+'</p>';
				htmlschdstr+='<p class="arvltime">[SCHEDULE TIME]:&nbsp;&nbsp;<span class="schdarr">'+arvltime+'</span><span class="badge badge-secondary text-white ml-2" style="font-size:11px;">Distance</span>&nbsp;'+dist+' Kms</p>';
				htmlschdstr+='</div></div></article>';

		}
		$("#divTTSchd").html(htmlschdstr);
		for(var i=0;i<obj.TTTranMvmt.length;i++)
		{
			var sttnfrom=obj.TTTranMvmt[i].From;
			var sttnto=obj.TTTranMvmt[i].To;
			var rakeid=obj.TTTranMvmt[i].RakeId;
			var loco=obj.TTTranMvmt[i].Loco;
			var cmdt=obj.TTTranMvmt[i].Cmdt;
			var raketype=obj.TTTranMvmt[i].Type;
			var unts=obj.TTTranMvmt[i].Unts;
			var loadname=obj.TTTranMvmt[i].LoadName;
			var cnsr=obj.TTTranMvmt[i].Cnsr;
			var cnsg=obj.TTTranMvmt[i].Cnsg;
			var stts=obj.TTTranMvmt[i].Stts;
			var sttn=obj.TTTranMvmt[i].Sttn;
			var sttstime=obj.TTTranMvmt[i].SttsTime;
			htmltranstr+='<div class="ptrancard"><table style="width:100%;" class="table table-striped table-ttrout">';
			htmltranstr+='<tr><td colspan="2" class="tranod">'+loadname+' &nbsp; ('+sttnfrom+'&nbsp;<i class="fas fa-long-arrow-alt-right"></i> &nbsp;'+sttnto+')';
			htmltranstr+='</td></tr><tr><td>Commodity/Units/Type: &nbsp; '+cmdt+'/'+unts+'/'+raketype+'</td><td style="width:50%;">';
			htmltranstr+='<span class="badge badge-success text-white" style="font-size:11px;">STATUS</span>&nbsp;'+sttn+' ['+stts+'] '+sttstime+'</td></tr></table></div>';
		}
		/*htmltranstr+='</table>';*/
		$("#divTTMvmt").html(htmltranstr);
		}
		});
}

function fetchSMARTFcty(trml)
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
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=SMART_FCTY&Sttn="+inpttrml;
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
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		var agcont=obj.AgCont;
		var trckcont=obj.TrckCont;
		var whcont=obj.WHCont;
		var labcont=obj.LabCont;
		var disclaim=obj.Disclaim;


		htmlstr+='<div class="btn-group btn-group-sm d-flex"><button type="button" class="btn btn-primary w-100" onclick="showAggr();">Aggregators <span class="badge badge-light">'+agcont+'</span></button>';
		htmlstr+='<button type="button" class="btn btn-success w-100" onclick="showTrck();">Truckers <span class="badge badge-light">'+trckcont+'</span></button>';
  		htmlstr+='<button type="button" class="btn btn-danger w-100" onclick="showWH();">Warehouse <span class="badge badge-light">'+whcont+'</span></button>';
		htmlstr+='<button type="button" class="btn btn-warning w-100" onclick="showLab();">Labour <span class="badge badge-light">'+labcont+'</span></button></div><br/>';
		htmlstr+='<div class="card"><div class="card-header text-left lsp-header"><span class="lsptitle">Aggregators</span><span class="float-right"><span class="dot1"></span>&nbsp;Company | <span class="dotIndv"></span>&nbsp;Individual</span></div>';
		htmlstr+='<div class="card-body text-left" id="divFctyDtls"></div></div></div></div></div>';
		htmlAg='<ul class="list-group list-group-flush">';
		htmlTrck='<ul class="list-group list-group-flush">';
		htmlWh='<ul class="list-group list-group-flush">';
		htmlLab='<ul class="list-group list-group-flush">';
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
			var totlcpty=obj.SMARTFcty[i].TotlCpty;
			var avblcpty=obj.SMARTFcty[i].AvblCpty;
			var cptyason=obj.SMARTFcty[i].AsOn;

			var totlcptynumb=0;
			var avblcptynumb=0;
			var ocpdpctg=0;
			var avblpctg=0;
			if(totlcpty!="")
			{
				totlcptynumb=Number(totlcpty);
				avblcptynumb=Number(avblcpty);
				avblpctg=(avblcptynumb*100)/totlcptynumb;
				ocpdpctg=((totlcptynumb-avblcptynumb)*100)/totlcptynumb;
			}
			if(factype=="A")
			{
				htmlAg+='<li class="list-group-item" style="line-height:20px;">';
				if(lsptype=="C")
					htmlAg+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlAg+='<span class="dotIndv"></span>&nbsp;';

				htmlAg+='<span class="lspname">'+lspname+'</span>';
				htmlAg+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlAg+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlAg+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlAg+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlAg+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlAg+='</li>';
			}
			if(factype=="T")
			{
				htmlTrck+='<li class="list-group-item" style="line-height:20px;">';
				if(lsptype=="C")
					htmlTrck+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlTrck+='<span class="dotIndv"></span>&nbsp;';

				htmlTrck+='<span class="lspname">'+lspname+'</span>';
				htmlTrck+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlTrck+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlTrck+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlTrck+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlTrck+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p></td>';
				htmlTrck+='</li>';
			}
			if(factype=="W")
			{
				htmlWh+='<li class="list-group-item" style="line-height:20px;">';
				if(lsptype=="C")
					htmlWh+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlWh+='<span class="dotIndv"></span>&nbsp;';

				htmlWh+='<span class="lspname">'+lspname+'</span>';
				htmlWh+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlWh+='<p class="compfcty">'+facdesc+'</p>';
				if(totlcptynumb!=0)
				{
					htmlWh+='<hr class="lighthr"/><p class="whocpn">Rated Capacity of Warehouse:'+totlcptynumb+' MT,  Available Capacity:'+avblcptynumb+' MT (As last updated on '+cptyason+')';
					htmlWh+='<div class="progress"><div class="progress-bar progress-bar-striped progress-bar-animated bg-danger" style="width:'+ocpdpctg+'%">Occupied</div><div class="progress-bar bg-success progress-bar-striped progress-bar-animated" style="width:'+avblpctg+'%">Available</div></div><hr class="lighthr"/>';
				}

				if(url!="")
				  htmlWh+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlWh+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlWh+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlWh+='</li>';
			}
			if(factype=="L")
			{
				htmlLab+='<li class="list-group-item" style="line-height:20px;">';
				if(lsptype=="C")
					htmlLab+='<span class="dot1"></span>&nbsp;';
				if(lsptype=="I")
					htmlLab+='<span class="dotIndv"></span>&nbsp;';

				htmlLab+='<span class="lspname">'+lspname+'</span>';
				htmlLab+='<p class="lspaddr">'+addr+' ('+city+'/'+stat+' '+pin+')</p>';
				htmlLab+='<p class="compfcty">'+facdesc+'</p>';

				if(url!="")
				  htmlLab+='<p class="spnurl">For more information, visit at:&nbsp;<a href="'+url+'" target="_blank">'+url+'</a></p>';

				if(faccontpers!='')
  				  htmlLab+='<p class="person">'+faccontpers+'<br/><i class="fas fa-phone-square-alt"></i> '+facmob+'  |  <i class="fas fa-envelope-square"></i> '+facemail+'</p>';
				else
				  htmlLab+='<p class="person">'+contpers+'<br/><i class="fas fa-phone-square-alt"></i> '+mob+'  |  <i class="fas fa-envelope-square"></i> '+email+'</p>';
				htmlLab+='</li>';
			}

		}
		htmlAg+='</ul>';
		htmlTrck+='</ul>';
		htmlWh+='</ul>';
		htmlLab+='</ul>';
		$("#fctytrml").html("Logistics Facilities at: "+trml);
		$("#divTrmlFcty").html(htmlstr);
		showAggr();
		$("#fctyModal").modal('show');

		}
		});
}

function showAggr()
{
	$("#divFctyDtls").html(htmlAg);
	$(".lsptitle").html("Aggregators");
}
function showWH()
{
	$("#divFctyDtls").html(htmlWh);
	$(".lsptitle").html("Warehouse");

}
function showTrck()
{
	$("#divFctyDtls").html(htmlTrck);
	$(".lsptitle").html("Truckers");

}
function showLab()
{
	$("#divFctyDtls").html(htmlLab);
	$(".lsptitle").html("Labour");

}
function showSttnProf(trml, indx)
{
	var htmlstr='';
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_PROFILE&Sttn="+trml;
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
			var trmltype=obj.SttnProf[i].TrmlType;

			htmlstr+='<table class="styletable">';
			htmlstr+='<tr><td class="lbl">Terminal Type</td><td class="val">'+trmltype+'</td></tr>';

			if(srvgdesc!='')
				htmlstr+='<tr><td class="lbl">Description</td><td class="val">'+srvgdesc+'</td></tr>';

			htmlstr+='<tr><td class="lbl">District</td><td class="val">'+dstt+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Traffic Type</td><td class="val"><b>Unloading:</b> '+trfciwrd+', <b>Loading:</b> '+trfcowrd+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Commodity</td><td class="val"><b>Unloading:</b> '+cmdtiw+'<br/><b>Loading:</b> '+cmdtow+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Working Hours</td><td class="val">'+wrkghrs+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Terminal Facilities</td><td class="val"><table>';
			if(fcty1list[0]=="Y")
				htmlstr+='<tr><td><ul><li>High Mast/Lighting Available</li>';
			if(fcty1list[1]!="")
				htmlstr+='<li>Line Count: '+fcty1list[1]+'</li>';
			if(fcty1list[2]!="")
				htmlstr+='<li>Handling Type: '+fcty1list[2]+'</li>';
			if(fcty1list[3]=="Y")
				htmlstr+='<li>Freight Charges on Through Distance Basis</li>';
			if(fcty1list[4]=="Y")
				htmlstr+='<li>Weighbridge Available</li>';
			if(fcty1list[5]!="0" && fcty1list[5]!="")
				htmlstr+='<li>Shed Length: '+fcty1list[5]+' Mtr.</li>';
			if(fcty3list[0]=="Y")
				htmlstr+='<li>Warehouse Available</li>';
			if(fcty3list[1]!="0" && fcty3list[1]!="")
				htmlstr+='<li>Distance from Serving Station: '+fcty3list[1]+' Kms</li>';
			if((fcty3list[2]!="NA") && (fcty3list[2]!=""))
				htmlstr+='<li>Alternate Weighbridge Locations: '+fcty3list[2]+'</li>';
			if(fcty3list[3]!="" && fcty3list[3]!="IR")
				htmlstr+='<li>Siding Owner: '+fcty3list[3]+'</li>';
			if(fcty3list[4]!="")
				htmlstr+='<li>EOL Type: '+fcty3list[4]+'</li>';
			if(fcty3list[5]=="Y")
				htmlstr+='<li>TANK Handling Available</li></ul></td></tr>';
			htmlstr+='</table></td></tr>';
			
			htmlstr+='<tr><td class="lbl">Nodal Officers Contacts</td><td class="val"><ul class="list-group list-group-flush">';
			for(var j=0;j<obj.NodlOfcr.length;j++)
			{
			  htmlstr+='<li class="list-group-item"><p class="nodlofcrdesg">'+obj.NodlOfcr[j].Ofcr+'</p><i class="fas fa-phone-alt"></i>&nbsp;'+obj.NodlOfcr[j].Mob+'&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<i class="fas fa-blender-phone"></i>&nbsp;'+obj.NodlOfcr[j].OfcLL+'</li>';
			}


			htmlstr+='</ul></td></tr></table>';
			$("#divTrmlProf"+indx).html(htmlstr);
			$("#hideTrmlProf"+indx).show();
			$("#showTrmlProf"+indx).hide();
		}
		}
	});
}
function hideTrmlprof()
{
	$(".hidetrmlprof").hide();	
	$(".showtrmlprof").show();	
}
function hideSpecTrmlProf(indx)
{
	$("#hideTrmlProf"+indx).hide();
	$("#showTrmlProf"+indx).show();

}

function fetchTrmlList1(locn)
{
	/*var locn=$("#txtSttn").val();*/
	locn=locn.toUpperCase();
	var htmlstr='<div class="w3-container">';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_LIST&Sttn="+locn;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'STTN_LIST',Sttn:locn},
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
			
			latList = new Array(obj.SttnList.length);
			lngList = new Array(obj.SttnList.length);
			locList= new Array(obj.SttnList.length);
            dvsnList = new Array(obj.SttnList.length);
            fcltyList= new Array(obj.SttnList.length);
                        if(obj.SttnList.length==0)
			{
				noDataFound();
				return false;
			}
			for(var i=0;i<obj.SttnList.length;i++)
			{
				
				latList[i]=obj.SttnList[i].Lat;
                lngList[i]=obj.SttnList[i].Lan;
                locList[i]=obj.SttnList[i].Sttn;
                dvsnList[i]=obj.SttnList[i].Dvsn+'/'+obj.SttnList[i].Zone;
                fcltyList[i]=obj.SttnList[i].Fcty;
				
				var sttn=obj.SttnList[i].Sttn;
				var dvsn=obj.SttnList[i].Dvsn;
				var zone=obj.SttnList[i].Zone;
				var stat=obj.SttnList[i].Stat;
				var fcty=obj.SttnList[i].Fcty;
				var sttncode=sttn.substring(0,sttn.indexOf("("));
				var fctylist=fcty.split(",");
				htmlstr+='<div class="w3-card-5"><table class="styletable">';
				htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttn+'<button class="btn btn-light btn-sm float-right"><i class="fas fa-map-marker-alt" onclick="initMapS('+latList[i]+','+lngList[i]+',\''+locList[i]+'\');" data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor=\'pointer\';" alt="View on Map" title="View on Map"  ></i></button></p></th></tr>';
				htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">'+dvsn+'/'+zone+'</td></tr>';
				htmlstr+='<tr><td class="lbl" sttn="\''+sttn+'\'">Logistics Facilities</td><td class="val" sttn="\''+sttn+'\'">';
				htmlstr+='<table class="tblFcty" onclick="fetchSMARTFcty(\''+sttn+'\');" sttn="\''+sttn+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+fctylist[0];
				htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+fctylist[1]+'</span></td>';
				htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+fctylist[2]+'</span></td>';
				htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+fctylist[3]+'</span></td></tr></table></td></tr>';
				htmlstr+='<tr><td colspan="2" class="val"><a href="javascript:void(0)" class="card-link1 showtrmlprof" data-toggle="collapse" data-target="#divTrmlProf2"  id="showTrmlProf2" onclick="showSttnProf(\''+sttncode+'\',\'2\');">Show More..</a><a href="javascript:void(0)" class="card-link1 hidetrmlprof" data-toggle="collapse" id="hideTrmlProf2" data-target="#divTrmlProf2" onclick="hideSpecTrmlProf(\'2\');">Show Less..</a></td></tr></table>';
		  		htmlstr+='<div id="divTrmlProf2" class="collapse"></div></div>';
			}
			htmlstr+='</div>';
			$("#divTrmlList1").html(htmlstr);
			$(".hidetrmlprof").hide();
		}
	});
}