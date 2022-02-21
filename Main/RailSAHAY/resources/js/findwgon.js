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
				fetchCmdtWgonMpng(obj.RakeCmdt);
			}
		}
	}); //Ajax func end
}

function fetchCmdtWgonMpng(cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#spnCvdCont").html('');
	$("#spnOpnCont").html('');

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
			htmlstr+='<div class="col-lg-3 col-md-4 col-sm-6"><div class="card"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><span class="prvt-wgon">Privately Owned</span><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';
		else
			htmlstr+='<div class="col-lg-3 col-md-4 col-sm-6"><div class="card"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';

		var stdrksizestr='';

		if(stdminirksize!='')
			stdrksizestr='<p class="paramlbl">Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/><p class="paramlbl">Mini Rake Size<span class="paramval">'+stdminirksize+'</span>  [For Train Load:<span class="paramval">'+miniwgoncontfortl+'</span>]</p><hr/>';
		else
			stdrksizestr='<p class="paramlbl">Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/>';

		
		
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
	$("#spnCvdCont").html(cvrdcont);
	$("#spnOpnCont").html(opencont);
	}
});
}
$(document).ready(function() {
fetchSAHAYStats();
var list = $("#txtCmdt");
$.each(aNumCmdtName, function(index, item) {
  var cmdttext=item;
  var cmdtval=item;
  list.append(new Option(cmdttext,cmdtval));
});

$("#txtCmdt").on('change', function (e) {
var optionSelected = $("option:selected", this);
var valueSelected = this.value;
fetchRakeCmdt(valueSelected);
});

});