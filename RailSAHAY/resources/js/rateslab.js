$(document).ready(function()
{
	$(".card-timeline").hide();
});
function fetchCmdtFrgtClss(si_cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divRateClss").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'CMDT_FRGT_CLSS',Cmdt:si_cmdt},
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
	var cmdt=obj.Cmdt;
	var gst=obj.GST;
	var wl=obj.WL;
	var tl=obj.TL;
	var rr=obj.RR;
	fetchRateClssDtls(wl,"WL");
	fetchRateClssDtls(tl,"TL");
	htmlstr+='<div align="center" id="cmdtratediv"><div class="cmdtcard"><div class="container"><p class="cmdtname">'+cmdt+'</p><hr/>';
    	htmlstr+='<p class="lbl">Rate Class</p><table class="ratetbl"><tr><td><p class="rateclss">'+tl+'</p>';
   	htmlstr+='<p class="rateclssdesc">TRAIN LOAD (Full Train)</p></td></tr><tr><td><p class="rateclss">'+wl+'</p>';
    	htmlstr+='<p class="rateclssdesc">WAGON LOAD (Wagons)</p></td></tr></table>';	
	if(gst!='')
	    	htmlstr+='<hr/><table class="ratetbl"><tr><td><p class="lbl"><b>'+rr+'</b></p></td><td><span class="badge badge-success text-white">*GST Exempted</span></td></tr></table>';
	else
		htmlstr+='<hr/><div align="center"><p class="lbl"><b>'+rr+'</b></p></div>';
    	htmlstr+='</div></div></div>';
	$("#divRateClss").html(htmlstr);

	}
});
}
function fetchRateClssDtls(si_rateclss, si_clsstype)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	var htmlstr='<div style="height:70vh;overflow-y:scroll;">';
	htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    data: {Optn:'RATE_CLSS_DTLS',RateClss:si_rateclss},
	async : true,
	success : function(data) {
	var obj= JSON.parse(data);
	$(".card-timeline").show();
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	if(obj.DistSlab.length==0)
	{
		noDataFound();
		$(".card-timeline").hide();
		$("#divRateClss").html('');
		return false;
	}
	for(var i=0;i<obj.DistSlab.length;i++)
	{
		var rateclss=obj.DistSlab[i].RateClss;
		var distfrom=obj.DistSlab[i].DistFrom;
		var distto=obj.DistSlab[i].DistTo;
		var rate=obj.DistSlab[i].Rate;
		htmlstr+='<li class="event" data-date=""><h3>'+distfrom+'-'+distto+' Kms </h3><p class="distrate">Rs. '+rate+' Per Tonne</p></li>';
	}
	/*htmlstr+='</div>';*/
	if(si_clsstype=="TL") {
		$("#ulTLTimeline").html(htmlstr);
		$("#tlHeader").html("Train Load (Class:"+rateclss+")");
	}
	if(si_clsstype=="WL") {
		$("#ulWLTimeline").html(htmlstr);
		$("#wlHeader").html("Wagon Load (Class:"+rateclss+")");
	}

	}
});
}
function fetchSubCmdtList(si_RakeCmdt)
{
	var list1 = $("#txtCmdt");
	$('#txtCmdt').children().remove();
	$('#txtCmdt').empty();
	var cntr=0;
	var initcmdt='';
	$.each(aGTNumCmdt, function(index, item) {
	var rakecmdt=item.substring(0, item.indexOf('-'));
	if(si_RakeCmdt==rakecmdt)
	{
  		var numcmdtstr=item.substring(item.indexOf('-')+1);
		var numCmdt=numcmdtstr.substring(0, numcmdtstr.indexOf('-'));
		var numcmdtdesc=numcmdtstr.substring(numcmdtstr.indexOf('-')+1);
		if(cntr==0) { initcmdt=numCmdt; }

		list1.append(new Option(numcmdtdesc,numCmdt));
		cntr++;
	}
   });
   if(cntr==0)
   {
	$(".card-timeline").hide();
	$("#divRateClss").html('');
   }
   $('#txtCmdt').focus();
   fetchCmdtFrgtClss(initcmdt);
   $("#txtCmdt").on('change', function (e) {
    var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    fetchCmdtFrgtClss(valueSelected);
   });
}
$(document).ready(function() {
fetchSAHAYStats();
var list = $("#txtRakeCmdt");
$.each(aGTCmdtGrup, function(index, item) {
  var cmdttext=item.substring(item.indexOf('-')+1);
  var cmdtval=item.substring(0, item.indexOf('-'));
  list.append(new Option(cmdttext,cmdtval));
});

$("#txtRakeCmdt").on('change', function (e) {
var optionSelected = $("option:selected", this);
var valueSelected = this.value;
fetchSubCmdtList(valueSelected);
});

});