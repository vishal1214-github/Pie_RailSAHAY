function scrollCounter()
{
	$('.statsfig').each(function () {
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
function fetchSAHAYStats()
{
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=SAHAY_STATS";
	$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
		var obj= JSON.parse(data);
		console.log(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}		
		var PFTCont=obj.Stat0;
		var CRTCont=obj.Stat1;
		var PvtSdng=obj.Stat2;
		var GShed=obj.Stat3;
		var PvtWgon=obj.Stat4;
		var IRWgon=obj.Stat5;
		var PvtLoco=obj.Stat6;
		var IRLoco=obj.Stat7;
		var WgonType=obj.Stat8;
		var ECust=obj.Stat9;
		var Cmdt=obj.Stat10;
		var IndtCont=obj.Stat11;
		var RakeLdng=obj.Stat12;
		$("#statwgoncont").html(IRWgon);
		$("#statwgontype").html(WgonType);
		$("#statlococont").html(IRLoco);
		$("#statcmdtcont").html(Cmdt);
		$("#statepaycust").html(ECust);
		$("#statindt").html(IndtCont);
		$("#statpft").html(PFTCont);
		$("#statpvtsdng").html(PvtSdng);
		$("#statrakeldng").html(RakeLdng);
		scrollCounter();

	}
	});
}
function fetchPvtWgonStats()
{
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=PVT_WGON_STATS";
	var htmlstr='';
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
	
		for(var i=0;i<obj.Stats.length;i++)
		{
			var schm=obj.Stats[i].Schm;
			var wgoncont=obj.Stats[i].WgonCont;

                	htmlstr+='<div class="col-6 col-md-4 col-lg-4" align="center"><p class="statsfig" id="statwgoncont">'+wgoncont+'</p>';
                    	htmlstr+='<p class="statslbl">'+schm+'</p></div>';
		}
		$('#divWgonSchmDtls').html(htmlstr);
		scrollCounter();
	}
	});
}
