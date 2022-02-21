var statstr='';
var dsttstr='';
var dvsnstr='';
var zonestr='';
var pincodestr='';
var sttnstr='';
   var raketypestr='';
   for (var i=0; i < aRakeType.length;++i){
        raketypestr+= '<option value="'+aRakeType[i]+'" />'; // Helplist for station
   }
   var my_list1=document.getElementById("raketypelist");
   my_list1.innerHTML = raketypestr;
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

var sttnstr='';
for (var i=0; i < aSttn.length;++i){
sttnstr+= '<option value="'+aSttn[i]+'" />'; // Helplist for Stations
}
var pincodestr='';
if (typeof aPinCode!== 'undefined') {
 	for (var i=0; i < aPinCode.length;++i)
	{
		pincodestr+= '<option value="'+aPinCode[i]+'" />'; // Helplist for Pincodes
	}
}

$(document).ready(function(){
     $(".sellist").change(function(){
      var locntype=$(this).val();

      switch(locntype)
      {

	case 'PC':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = pincodestr;
		break;

	case 'ST':
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

	case 'S':
		var my_list=document.getElementById("locnlist");
		my_list.innerHTML = sttnstr;
		break;

      }
    });
});
function fetchCombi()
{
	$("#divTrmlList").html('');
      	var locntype=$("#selLocnType").val();
	var owiwflag=$("#selOWIW").val();
	var locn='';
	var cmdt='';
	var raketype='';
	if(locntype=="")
	{
		custPrompt("Please select Location Type/Location to Proceed");
		return false;
	}

	var locndesc=$("#txtLocn").val();
	if((locndesc===undefined) || (locndesc==""))
	{
		custPrompt("Please select a Location to Proceed");
		return false;
	}
	else
	{

		if(locndesc.indexOf("-")>-1)
		{
		locn=locndesc.substring(0,locndesc.indexOf("-"));
		}
		else
		{
		locn=locndesc.toUpperCase();
		}
	}
	var cmdtcode=$("#txtCmdt").val();
	var rtypedesc=$("#txtRakeType").val();
	if((rtypedesc===undefined) || (rtypedesc==""))
	{
		//do nothing
	}
	else
	{

		if(rtypedesc.indexOf("-")>-1)
		{
		raketype=rtypedesc.substring(0,rtypedesc.indexOf("-"));
		}
		else
		{
		raketype=rtypedesc.toUpperCase();
		}
	}
	var myurl="/RailSAHAY/FSH_FrgtCalcUtil";
	$.ajax({
		url : myurl,
		type : "post",
		data: {Qry:'RAKE_CMDT',cmdt:cmdtcode},
		async : true,
		success : function(data)
		{
			var obj= JSON.parse(data);
			var ServiceCall=obj.ServiceCall;
			var DataFlag=obj.DataFlag;
			if(ServiceCall == "F")
			{
				console.log("failed");
				return false;
			}
			if(DataFlag == "N")
			{
				cuteToast({
				  type: "success",
				  message: "No Combination Exists for selected commodity",
				  timer: 5000
				})
				return false;
			}
			else
			{
				fetchData(locntype,locn,obj.RakeCmdt,raketype,owiwflag);
			}
		}
	}); //Ajax func end

}
function fetchData(locntype,locn,cmdt,raketype,owiwflag)
{
	var myurl="/RailSAHAY/GG_MiscQryJson?Optn=TWO_POINT_COMBI&LocnFlag="+locntype+"&Locn="+locn+"&Cmdt="+cmdt+"&Type="+raketype+"&OWIW="+owiwflag;
	$("#divTrmlList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	$("#spnSmry").html("");
	var htmltwoptcombi='<div class="row"><div class="col-lg-10 offset-lg-1 col-sm-12">';
	var prevslnumb='';
	var prevcmdt='';
	var prevcmdttype='';
	var prevraketype='';
	var combcntr=0;
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
		if(obj.TwoPtCombi.length==0)
		{
			custPrompt("No Two-Point/Multi-Point Combination Exists for your commodity");
		}
		for(var i=0;i<obj.TwoPtCombi.length;i++)
		{
			var slnumb=obj.TwoPtCombi[i].SLNumb;
			var sttn=obj.TwoPtCombi[i].Sttn;
			var sttnname=obj.TwoPtCombi[i].SttnName;
			var dvsn=obj.TwoPtCombi[i].Dvsn;
			var dstt=obj.TwoPtCombi[i].Dstt;
			var owiw=obj.TwoPtCombi[i].OWIW;
			var raketype1=obj.TwoPtCombi[i].RakeType;
			var cmdttype=obj.TwoPtCombi[i].CmdtType;
			var cmdt1=obj.TwoPtCombi[i].Cmdt;
			var lat=obj.TwoPtCombi[i].Lat;
			var lng=obj.TwoPtCombi[i].Lng;
			if(prevslnumb==slnumb)
			{
				htmltwoptcombi+='<li class="list-group-item"><p class="sttncode">'+sttn+'<span class="sttnname">('+sttnname+')</span></p><p class="trty"><span class="trtylbl">District</span><span class="trtyval">'+dstt+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="trtylbl">Division</span><span class="trtyval">'+dvsn+'</span></p></li>';
			}
			else
			{
				combcntr++;
				if(i>0)
				htmltwoptcombi+='</ul></div><div class="col-lg-4 col-sm-12 cmdttype"><i class="fas fa-exclamation-circle"></i>&nbsp;<span class="trtyval">VALID FOR</span><hr/><p class="trty"><span class="trtylbl">Commodity</span><span class="trtyval">'+prevcmdttype+' '+prevcmdt+'</p><p class="trty"><span class="trtylbl">Rake Type</span><span class="trtyval">'+prevraketype+'</p></div></div></div>';

				htmltwoptcombi+='<div class="twoptcard"><div class="row"><div class="col-lg-8 col-sm-12"><ul class="list-group list-group-flush"><li class="list-group-item"><p class="sttncode">'+sttn+'<span class="sttnname">('+sttnname+')</span></p><p class="trty"><span class="trtylbl">District</span><span class="trtyval">'+dstt+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="trtylbl">Division</span><span class="trtyval">'+dvsn+'</span></p></li>';
			}
			prevslnumb=slnumb;
			prevcmdt=cmdt1;
			prevcmdttype=cmdttype;
			prevraketype=raketype1;
		}
		if(obj.TwoPtCombi.length>0)
		{
			htmltwoptcombi+='</ul></div><div class="col-lg-4 col-sm-12 cmdttype"><p class="trty"><span class="trtylbl">Commodity</span><span class="trtyval">'+prevcmdttype+' '+prevcmdt+'</p><p class="trty"><span class="trtylbl">Rake Type</span><span class="trtyval">'+prevraketype+'</p></div></div></div></div></div>';
			$("#divTrmlList").html(htmltwoptcombi);
			$("#spnSmry").html("<strong>"+combcntr+"</strong> Combinations match your Search Criteria");
		}
		else
			$("#divTrmlList").html('');
		}
		});

}

function downloadMainCmdtPDF()
{
	$("#frmMainBrochure").submit();
}
function downloadSubCmdtPDF()
{
	$("#frmSubBrochure").submit();
}