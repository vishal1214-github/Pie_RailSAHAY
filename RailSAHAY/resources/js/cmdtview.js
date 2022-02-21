/*General Functions*/
function fetchData(numcmdtcode,numcmdtname, rprtflag)
{
	switch(rprtflag)
	{
		case 'RATE_SLAB':
			fetchCmdtFrgtClss(numcmdtcode);
			break;
		case 'WGON_CTLG':
			fetchRakeCmdt(numcmdtcode);
			break;
		case 'REBT_SCHM':
			fetchRebtSchm(numcmdtcode);
			break;
		case 'FRGT_CALC':
			setFrgtCalc(numcmdtcode);
			break;
		case 'TRML_SRCH':
			fetchTrmlList(numcmdtcode);
			break;
		case 'CMDT_HOME':
			$("#hSubCmdtHead").html(numcmdtname);
			fetchCmdtStats(numcmdtcode,numcmdtname);
			break;

	}
}
function setSesnSubCmdt(numcmdtcode,numcmdtname, rprtflag)
{
	var myurl =  "/RailSAHAY/GG_CmdtViewJson";
	$.ajax({
		url : myurl,
		type : "post",
	    	data: {Help:'SETSUBCMDT',SubCmdtCode:numcmdtcode, SubCmdtName:numcmdtname},
		async : true,
		success : function(data) {
			console.log('inside success');
				fetchData(numcmdtcode,numcmdtname, rprtflag);
			}
	});
}
function updtSubCmdt()
{
	var numcmdtname = document.getElementById("selCmdtList").value;
	var numcmdtcode = document.querySelector("#cmdtlist option[value='"+numcmdtname+"']").dataset.value;
	setSesnSubCmdt(numcmdtcode,numcmdtname,'CMDT_HOME');
	$("#divCmdtFeature").show();
}
function fetchNumCmdtList(maincmdt, rprtflag)
{
	var myurl="/RailSAHAY/GG_CmdtViewJson";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data: {Help:'NUM_CMDT_LIST',MainCmdt:maincmdt},
	async : true,
	success : function(data) {
		var obj=data;
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			custPrompt("Not able to connect to Server:"+erormesg);
			return false;
		}
		var list = $("#selNumCmdt");

		var numcmdtlist='';
		for (var i=0; i < obj.WgonList.length;++i){
			numcmdtlist+= '<option value="'+obj.WgonList[i].CmdtName+'" data-value="'+obj.WgonList[i].NumCode+'" />'; // Helplist for Commodity
		}
		
		var my_list=document.getElementById("cmdtlist");
		my_list.innerHTML = numcmdtlist;
		$("#spnSubCmdtCont").html(obj.WgonList.length);
		scrollCounter();
		/*
		for(var i=0;i<obj.WgonList.length;i++)
		{
			var numcode=obj.WgonList[i].NumCode;
			var cmdtname=obj.WgonList[i].CmdtName;
			list.append(new Option(cmdtname,numcode));
		}
		$("#spnSubCmdtCont").html(obj.WgonList.length);
		$("#selNumCmdt").on('change', function (e) {
		var optionSelected = $("option:selected", this);
		var numCmdtCode = this.value;
		var numCmdtName = this.text;
		setSesnSubCmdt(numCmdtCode, numCmdtName, rprtflag);
		});
		*/
	   }
	});
}
/*Functions for Commodity WagonType Mapping*/
function fetchRakeCmdt(numcmdt)
{
	var myurl="/RailSAHAY/FSH_FrgtCalcUtil";
	$.ajax({
		url : myurl,
		type : "post",
		data: {Qry:'RAKE_CMDT',cmdt:numcmdt},
		async : true,
		success : function(data)
		{
			console.log("successfull:"+data);
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
				  message: "Wagon Type is Not Available for Select Commodity",
				  timer: 5000
				})
				return false;
			}
			else
			{
				console.log("fetching wagon mapping:"+obj.RakeCmdt);
				fetchCmdtWgonMpng(obj.RakeCmdt);
			}
		}
	}); //Ajax func end
}
function fetchCmdtWgonMpng(cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divWgonTypeList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'CMDT_WGON_MPNG',Cmdt:cmdt},
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
	if(obj.WgonList.length==0)
	{
		noDataFound();
		$("#divWgonTypeList").html('');
		return false;
	}
	var prefflag="N";
	var cvrdcont=0;
	var opencont=0;
	for(var i=0;i<obj.WgonList.length;i++)
	{
		var wgontype=obj.WgonList[i].WgonType;
		var wgondesc=obj.WgonList[i].WgonDesc;
		var brak=Number(obj.WgonList[i].Brak);
		var cotype=obj.WgonList[i].COType;
		var wgoncont=obj.WgonList[i].WgonCont;
		var tare=obj.WgonList[i].Tare;
		var cc=obj.WgonList[i].CC;
		var pvtowngflag=obj.WgonList[i].WGONFLAG;
		var stdrksize=obj.WgonList[i].SIZE;
		var wgoncontfortl=obj.WgonList[i].FORTL;
		var stdminirksize=obj.WgonList[i].SIZEBG;
		var miniwgoncontfortl=obj.WgonList[i].FORTLBG;

		if(brak>0)
		{
			prefflag="Y";	
		}
		if(prefflag=="Y") 
		{
			if(brak<=0)
			{
				break;
			}
		}
		if(prefflag=="N") 
		{
			if(i>4)
			{
				break;
			}
		}

		if(cotype=="C") { cvrdcont++; }
		if(cotype=="O") { opencont++; }	


		if(i==8)
		{
			htmlstr+='</div>';
			htmlstr+='<a href="javascript:void(0)" class="card-link1" data-toggle="collapse" data-target="#wgontype" id="aToggle">Show More..</a><br/>';
			htmlstr+='<div id="wgontype" class="collapse"><div class="row" style="margin-top:14px;">';
		} 
		
		if(pvtowngflag=="P")
			htmlstr+='<div class="col-lg-3 col-md-4 col-sm-6"><div class="card card-fc" wtype="'+wgontype+'" wdesc="'+wgondesc+'"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><span class="prvt-wgon">Privately Owned</span><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';
		else
			htmlstr+='<div class="col-lg-3 col-md-4 col-sm-6"><div class="card card-fc" wtype="'+wgontype+'" wdesc="'+wgondesc+'"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';

		var stdrksizestr='';

		if(stdminirksize!='')
			stdrksizestr='<p class="paramlbl">Std. Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/><p class="paramlbl">Std. Mini Rake Size<span class="paramval">'+stdminirksize+'</span>  [For Train Load:<span class="paramval">'+miniwgoncontfortl+'</span>]</p><hr/>';
		else
			stdrksizestr='<p class="paramlbl">Std. Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/>';

		
		
		if(brak>0)
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Loading Preference<span class="paramval">'+brak+'%</span></p><hr/><p class="paramlbl">Stock Available';		
		else
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Stock Available';

			if(cotype=="C")
				htmlstr+='<span class="paramval badge badge-blue text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else if(cotype=="O")
				htmlstr+='<span class="paramval badge badge-success text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else
				htmlstr+='<span class="paramval badge badge-secondary text-white float-right">'+wgoncont+'</span></p></div></div></div>';


	}
	if(i>8)
	{
		htmlstr+='</div></div>';
	}
	$("#divWgonTypeList").html(htmlstr);
	$('img.card-img-top').on("error", function() {
  		$(this).attr('src', '/RailSAHAY/resources/images/wagons/NOIMAGE.jpg');
	});
	$("#aToggle").on('click',function() {
		var linktext=$(this).text();
		if(linktext=="Show More..")
			$(this).text('Show Less');
		if(linktext=="Show Less")
			$(this).text('Show More..');
		return true;

	});
	$(".card-fc").on('click', function() {		
		var wtype=$(this).attr('wtype');
		var wdesc=$(this).attr('wdesc');
		var wdtls=wtype+"-"+wdesc;
		location.href="/RailSAHAY/pages/FindCmdtWgonFrgtCalc.jsp?FromWgonFlag=W&txtWgonType="+wdtls;
	});
	$("#spnCvdCont").html(cvrdcont);
	$("#spnOpnCont").html(opencont);
	$(".alert-custmesg").show();
	}
});
}

/*Functions for Rate Slabs*/
$(document).ready(function()
{
	$(".card-timeline").hide();
	$(".alert-custmesg").hide();
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

/*Functions for Incentive Schemes*/
function getCmdtRebtSchm(cmdtcode)
{
	var myurl="/RailSAHAY/GG_CmdtViewJson";
	$("#divRebtSchm").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='<ul class="list-group list-group-flush">';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Help:'CMDT_SCHM',CmdtLevl:'CMDTCODE',Cmdt:cmdtcode},
	async : true,
	success : function(data) {
	var obj=data;
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	if(obj.SchmDtls.length==0)
	{
		custPrompt('Sorry! No Incentive Scheme Currently Exists for the Selected Commodity');
		$("#divRebtSchm").html('');
		return false;
	}
	for(var i=0;i<obj.SchmDtls.length;i++)
	{
		var schmcode=obj.SchmDtls[i].SchmCode;
		var schmdesc=obj.SchmDtls[i].SchmDesc;
		var schmmstrrc=obj.SchmDtls[i].SchmMstrRc;
		var schmmstrrcdate=obj.SchmDtls[i].SchmMstrRcDate;
		var schmmstrrclink=obj.SchmDtls[i].SchmMstrRcLink;
		var schmltstrc=obj.SchmDtls[i].SchmLtstRc;
		var schmltstrcdate=obj.SchmDtls[i].SchmLtstRcDate;
		var schmltstrclink=obj.SchmDtls[i].SchmLtstRcLink;

		htmlstr+='<li class="list-group-item"><p class="pschmname">'+schmdesc+'</p><p class="pmstrrc"><i class="fas fa-file-download"></i>&nbsp;<a href="javascript:void(0)" onclick=openSchmRC("'+schmmstrrclink+'") class="rclink">'+schmmstrrc+'<span class="mstrrcdate"><strong>(Dated: '+schmmstrrcdate+')</strong></span></a></p>';
		if(schmltstrc!="")
			htmlstr+='<p class="pltstrc"><i class="fas fa-file-download"></i>&nbsp;<a href="javascript:void(0)" onclick=openLtstRC("'+schmltstrclink+'") class="ltstrclink">'+schmltstrc+'<span class="ltstrcdate"><strong>(Dated: '+schmltstrcdate+')</strong></span></a></p></li>';
	}
	htmlstr+='</ul>';
	$("#divRebtSchm").html(htmlstr);
	}
});
	
}

function fetchCmdtStats(numcmdtcode,numcmdtname)
{
	var myurl="/RailSAHAY/FSH_FrgtCalcUtil";
	$.ajax({
		url : myurl,
		type : "post",
		data: {Qry:'RAKE_CMDT',cmdt:numcmdtname},
		async : true,
		success : function(data)
		{
			console.log("successfull:"+data);
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
				  message: "Server connectivity failed! Please try again after some time",
				  timer: 5000
				})
				return false;
			}
			else
			{
				cmdtStatsInpt(obj.RakeCmdt,numcmdtcode);
			}
		}
	}); //Ajax func end
}

function cmdtStatsInpt(si_RakeCmdt, si_NumCmdt)
{
	var myurl="/RailSAHAY/GG_CmdtViewJson";
	$("#pTrmlStat").html("");
	$("#pWgonCtlg").html("");
	$("#pRebtSchm").html("");
	$("#pCmdtMLPont").html("");
	$("#pRateSlab").html("");

	$.ajax({
	url : myurl,
	type : "post",
    	data: {Help:'CMDT_STATS',RakeCmdt:si_RakeCmdt,NumCmdt:si_NumCmdt},
	async : true,
	success : function(data) {
	var obj= data;
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	var trmlcont=obj.TrmlCont;
	var rebtschm=obj.RebtSchm;
	var stcktypecont=obj.StckCont;
	var twoptcombi=obj.TwoPtCombi;
	$(".statholder").hide();
	$("#pTrmlStat").html(trmlcont);
	$("#pWgonCtlg").html(stcktypecont);
	$("#pRebtSchm").html(rebtschm);
	$("#pCmdtMLPont").html(twoptcombi);
	$("#pRateSlab").html("35");
	scrollCounter();
	}
	});
	
}
function openSchmRC(url)
{
		window.open(url,'schmrc','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}
function openLtstRC(url)
{
		window.open(url,'ltstrc','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}
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
function downloadMainCmdtPDF()
{
	$("#frmMainBrochure").submit();
}
function downloadSubCmdtPDF()
{
	$("#frmSubBrochure").submit();
}