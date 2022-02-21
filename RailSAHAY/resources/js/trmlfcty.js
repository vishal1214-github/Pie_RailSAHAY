var aglist=[];
var trcklist=[];
var whlist=[];
var lablist=[];
var agcont=0;
var trckcont=0;
var whcont=0;
var labcont=0;
var srvccall="";
var dataflag="";
var Gs_Trml="";
var Gs_TrmlName="";
var Gs_Disclaim="";
var htmlAg='';
var htmlTrck='';
var htmlWH='';
var htmlLab='';
var htmlBkngProf='';

$(document).ready(function(){
	fetchTrmlFcty();
});

function showDisclaim()
{
	var htmlDisclaim='<table class="table table-striped table-bordered"><thead><tr><th class="card-title"><img src="/RailSAHAY/resources/images/SugamIconsOrng/emktdisclaim3.png" class="emkgdisclaim" />DISCLAIMER</th></tr></thead><tbody><tr><td class="disclaim">'+Gs_Disclaim+'</td></tr></tbody></table>';
	$("#compAddlInfoBody").html(htmlDisclaim);
	$("#compAddlInfo").modal('show');
}
function setInptTrml(sttn,sttnname)
{
	Gs_Trml=sttn;
	Gs_TrmlName=sttnname;
}
function showFcty(factype)
{
	switch(factype)
	{
		case 'A':
			$(".list-group-flush").html(htmlAg);
			$(".cardhead").removeClass("icondivsel");
			$(".cardAg").addClass("icondivsel");
			$(".spnheadlbl").html("AGGREGATOR(S)");
			break;
		case 'T':
			$(".list-group-flush").html(htmlTrck);
			$(".cardhead").removeClass("icondivsel");
			$(".cardTrck").addClass("icondivsel");
			$(".spnheadlbl").html("TRUCKER(S)");
			break;
		case 'W':
			$(".list-group-flush").html(htmlWH);
			$(".cardhead").removeClass("icondivsel");
			$(".cardWH").addClass("icondivsel");
			$(".spnheadlbl").html("WAREHOUSE(S)");
			break;
		case 'L':
			$(".list-group-flush").html(htmlLab);
			$(".cardhead").removeClass("icondivsel");
			$(".cardLab").addClass("icondivsel");
			$(".spnheadlbl").html("LABOUR(S)");
			break;
	}
}
function fetchTrmlFcty()
{
	var myurl="/RailSAHAY/GG_AjaxUtil?Qry=TRML_FCTY_SMRY&Sttn="+Gs_Trml;
	var factype="";
	var company="";
	var addr="";
	var srvgcont="";
	var person="";
	var facavbl="";
	var addlinfo="";
	var agcntr=0;
	var trckcntr=0;
	var whcntr=0;
	var labcntr=0;
	$.ajax({
	  url : myurl,
	  type : "post",
	  async : true,
	  success : function(data) {
		var obj= JSON.parse(data);
		trckcont=Number(obj.TrckCont);
		whcont=Number(obj.WHCont);
		labcont=Number(obj.LbrCont);
		agcont=Number(obj.AggrCont);
		Gs_Disclaim=obj.Disclaim;
		srvccall=obj.ServiceCall;
		dataflag=obj.DataFlag;
		$("#pAgCont").html(agcont);
		$("#pTrckCont").html(trckcont);
		$("#pWhCont").html(whcont);
		$("#pLabCont").html(labcont);
		$("#disclaimMesg").html(Gs_Disclaim);
		$("#pAgCont1").html(agcont);
		$("#pTrckCont1").html(trckcont);
		$("#pWhCont1").html(whcont);
		$("#pLabCont1").html(labcont);
		$("#disclaimMesg1").html(Gs_Disclaim);

		if(dataflag=="Y")
		{
			for(var i=0;i<obj.Data.length;i++)
			{
				factype=obj.Data[i].FacType;

				if(factype=="T")
				{
					trcklist[trckcntr]=new Array(17);
					trcklist[trckcntr][0]=obj.Data[i].LSP;
					trcklist[trckcntr][1]=obj.Data[i].GST;
					trcklist[trckcntr][2]=obj.Data[i].Addr;
					trcklist[trckcntr][3]=obj.Data[i].City;
					trcklist[trckcntr][4]=obj.Data[i].Stat;
					trcklist[trckcntr][5]=obj.Data[i].Pin;
					trcklist[trckcntr][6]=obj.Data[i].ContPers;
					trcklist[trckcntr][7]=obj.Data[i].LSPMob;
					trcklist[trckcntr][8]=obj.Data[i].LSPEmail;
					trcklist[trckcntr][9]=obj.Data[i].FacType;
					trcklist[trckcntr][10]=obj.Data[i].FacDesc;
					trcklist[trckcntr][11]=obj.Data[i].AddlInfo;
					trcklist[trckcntr][12]=obj.Data[i].FacPers;
					trcklist[trckcntr][13]=obj.Data[i].FacMob;
					trcklist[trckcntr][14]=obj.Data[i].FacEmail;
					trcklist[trckcntr][15]=obj.Data[i].CompSrvg;
					trcklist[trckcntr][16]=obj.Data[i].LSPType;
					htmlTrck+='<li class="list-group-item"><table class="table table-striped" style="width:100%;border:0;"><tr><td><p class="compname">';
					if(obj.Data[i].LSPType=="I")
					htmlTrck+='<span class="dotIndv"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					else
					htmlTrck+='<span class="dot1"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					
					htmlTrck+='<p class="compaddr">'+obj.Data[i].Addr+' '+obj.Data[i].City+', '+obj.Data[i].Stat+' '+obj.Data[i].Pin+'</p><p class="compfcty">'+obj.Data[i].FacDesc+'</p>';
					
					if(obj.Data[i].FacPers!='')
					htmlTrck+='<p class="person">'+obj.Data[i].FacPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].FacMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].FacEmail+'</p></td><td align="right">';
					else
					htmlTrck+='<p class="person">'+obj.Data[i].ContPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].LSPMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].LSPEmail+'</p></td><td align="right">';
					
					htmlTrck+='<a data-toggle="collapse" href="#collapseExample'+i+'" role="button" aria-expanded="false" aria-controls="collapseExample'+i+'"><i class="fas fa-info-circle fa-lg"></i></a>';
					htmlTrck+='</td></tr></table><div class="collapse" id="collapseExample'+i+'"><div class="card card-body"><table><tr><td>';
					htmlTrck+='<span class="spngst">GST No.: '+obj.Data[i].GST+'</span><p class="spnaddlinfo"><i class="fas fa-info-circle"></i>&nbsp;'+obj.Data[i].AddlInfo+'</p>';
					htmlTrck+='</td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample'+i+'" aria-expanded="false" aria-controls="collapseExample'+i+'" class="float-right closeicon"><i class="far fa-times-circle fa-sm"></i></a></span>';
					htmlTrck+='</td></tr></table></div></div></li>';
					
					trckcntr++;
				}
				if(factype=="A")
				{
					aglist[agcntr]=new Array(17);
					aglist[agcntr][0]=obj.Data[i].LSP;
					aglist[agcntr][1]=obj.Data[i].GST;
					aglist[agcntr][2]=obj.Data[i].Addr;
					aglist[agcntr][3]=obj.Data[i].City;
					aglist[agcntr][4]=obj.Data[i].Stat;
					aglist[agcntr][5]=obj.Data[i].Pin;
					aglist[agcntr][6]=obj.Data[i].ContPers;
					aglist[agcntr][7]=obj.Data[i].LSPMob;
					aglist[agcntr][8]=obj.Data[i].LSPEmail;
					aglist[agcntr][9]=obj.Data[i].FacType;
					aglist[agcntr][10]=obj.Data[i].FacDesc;
					aglist[agcntr][11]=obj.Data[i].AddlInfo;
					aglist[agcntr][12]=obj.Data[i].FacPers;
					aglist[agcntr][13]=obj.Data[i].FacMob;
					aglist[agcntr][14]=obj.Data[i].FacEmail;
					aglist[agcntr][15]=obj.Data[i].CompSrvg;
					aglist[agcntr][16]=obj.Data[i].LSPType;
					
					htmlAg+='<li class="list-group-item"><table class="table table-striped" style="width:100%;border:0;"><tr><td><p class="compname">';
					if(obj.Data[i].LSPType=="I")
					htmlAg+='<span class="dotIndv"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					else
					htmlAg+='<span class="dot1"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					
					htmlAg+='<p class="compaddr">'+obj.Data[i].Addr+' '+obj.Data[i].City+', '+obj.Data[i].Stat+' '+obj.Data[i].Pin+'</p><p class="compfcty">'+obj.Data[i].FacDesc+'</p>';
					
					if(obj.Data[i].FacPers!='')
					htmlAg+='<p class="person">'+obj.Data[i].FacPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].FacMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].FacEmail+'</p></td><td align="right">';
					else
					htmlAg+='<p class="person">'+obj.Data[i].ContPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].LSPMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].LSPEmail+'</p></td><td align="right">';
					
					htmlAg+='<a data-toggle="collapse" href="#collapseExample'+i+'" role="button" aria-expanded="false" aria-controls="collapseExample'+i+'"><i class="fas fa-info-circle fa-lg"></i></a>';
					htmlAg+='</td></tr></table><div class="collapse" id="collapseExample'+i+'"><div class="card card-body"><table><tr><td>';
					htmlAg+='<span class="spngst">GST No.: '+obj.Data[i].GST+'</span><p class="spnaddlinfo"><i class="fas fa-info-circle"></i>&nbsp;'+obj.Data[i].AddlInfo+'</p>';
					htmlAg+='</td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample'+i+'" aria-expanded="false" aria-controls="collapseExample'+i+'" class="float-right closeicon"><i class="far fa-times-circle fa-sm"></i></a></span>';
					htmlAg+='</td></tr></table></div></div></li>';
					
					agcntr++;
				}
				if(factype=="W")
				{
					whlist[whcntr]=new Array(17);
					whlist[whcntr][0]=obj.Data[i].LSP;
					whlist[whcntr][1]=obj.Data[i].GST;
					whlist[whcntr][2]=obj.Data[i].Addr;
					whlist[whcntr][3]=obj.Data[i].City;
					whlist[whcntr][4]=obj.Data[i].Stat;
					whlist[whcntr][5]=obj.Data[i].Pin;
					whlist[whcntr][6]=obj.Data[i].ContPers;
					whlist[whcntr][7]=obj.Data[i].LSPMob;
					whlist[whcntr][8]=obj.Data[i].LSPEmail;
					whlist[whcntr][9]=obj.Data[i].FacType;
					whlist[whcntr][10]=obj.Data[i].FacDesc;
					whlist[whcntr][11]=obj.Data[i].AddlInfo;
					whlist[whcntr][12]=obj.Data[i].FacPers;
					whlist[whcntr][13]=obj.Data[i].FacMob;
					whlist[whcntr][14]=obj.Data[i].FacEmail;
					whlist[whcntr][15]=obj.Data[i].CompSrvg;
					whlist[whcntr][16]=obj.Data[i].LSPType;
					
					htmlWH+='<li class="list-group-item"><table class="table table-striped" style="width:100%;border:0;"><tr><td><p class="compname">';
					if(obj.Data[i].LSPType=="I")
					htmlWH+='<span class="dotIndv"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					else
					htmlWH+='<span class="dot1"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					
					htmlWH+='<p class="compaddr">'+obj.Data[i].Addr+' '+obj.Data[i].City+', '+obj.Data[i].Stat+' '+obj.Data[i].Pin+'</p><p class="compfcty">'+obj.Data[i].FacDesc+'</p>';
					
					if(obj.Data[i].FacPers!='')
					htmlWH+='<p class="person">'+obj.Data[i].FacPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].FacMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].FacEmail+'</p></td><td align="right">';
					else
					htmlWH+='<p class="person">'+obj.Data[i].ContPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].LSPMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].LSPEmail+'</p></td><td align="right">';
					
					htmlWH+='<a data-toggle="collapse" href="#collapseExample'+i+'" role="button" aria-expanded="false" aria-controls="collapseExample'+i+'"><i class="fas fa-info-circle fa-lg"></i></a>';
					htmlWH+='</td></tr></table><div class="collapse" id="collapseExample'+i+'"><div class="card card-body"><table><tr><td>';
					htmlWH+='<span class="spngst">GST No.: '+obj.Data[i].GST+'</span><p class="spnaddlinfo"><i class="fas fa-info-circle"></i>&nbsp;'+obj.Data[i].AddlInfo+'</p>';
					htmlWH+='</td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample'+i+'" aria-expanded="false" aria-controls="collapseExample'+i+'" class="float-right closeicon"><i class="far fa-times-circle fa-sm"></i></a></span>';
					htmlWH+='</td></tr></table></div></div></li>';
					whcntr++;
				}
				if(factype=="L")
				{
					lablist[labcntr]=new Array(17);
					lablist[labcntr][0]=obj.Data[i].LSP;
					lablist[labcntr][1]=obj.Data[i].GST;
					lablist[labcntr][2]=obj.Data[i].Addr;
					lablist[labcntr][3]=obj.Data[i].City;
					lablist[labcntr][4]=obj.Data[i].Stat;
					lablist[labcntr][5]=obj.Data[i].Pin;
					lablist[labcntr][6]=obj.Data[i].ContPers;
					lablist[labcntr][7]=obj.Data[i].LSPMob;
					lablist[labcntr][8]=obj.Data[i].LSPEmail;
					lablist[labcntr][9]=obj.Data[i].FacType;
					lablist[labcntr][10]=obj.Data[i].FacDesc;
					lablist[labcntr][11]=obj.Data[i].AddlInfo;
					lablist[labcntr][12]=obj.Data[i].FacPers;
					lablist[labcntr][13]=obj.Data[i].FacMob;
					lablist[labcntr][14]=obj.Data[i].FacEmail;
					lablist[labcntr][15]=obj.Data[i].CompSrvg;
					lablist[labcntr][16]=obj.Data[i].LSPType;
					htmlLab+='<li class="list-group-item"><table class="table table-striped" style="width:100%;border:0;"><tr><td><p class="compname">';
					
					if(obj.Data[i].LSPType=="I")
					htmlLab+='<span class="dotIndv"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					else
					htmlLab+='<span class="dot1"></span>&nbsp;'+obj.Data[i].LSP+'</p>';
					
					htmlLab+='<p class="compaddr">'+obj.Data[i].Addr+' '+obj.Data[i].City+', '+obj.Data[i].Stat+' '+obj.Data[i].Pin+'</p><p class="compfcty">'+obj.Data[i].FacDesc+'</p>';
					
					if(obj.Data[i].FacPers!='')
					htmlLab+='<p class="person">'+obj.Data[i].FacPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].FacMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].FacEmail+'</p></td><td align="right">';
					else
					htmlLab+='<p class="person">'+obj.Data[i].ContPers+'<br/><i class="fas fa-phone-square-alt"></i> '+obj.Data[i].LSPMob+'  |  <i class="fas fa-envelope-square"></i> '+obj.Data[i].LSPEmail+'</p></td><td align="right">';
					
					htmlLab+='<a data-toggle="collapse" href="#collapseExample'+i+'" role="button" aria-expanded="false" aria-controls="collapseExample'+i+'"><i class="fas fa-info-circle fa-lg"></i></a>';
					htmlLab+='</td></tr></table><div class="collapse" id="collapseExample'+i+'"><div class="card card-body"><table><tr><td>';
					htmlLab+='<span class="spngst">GST No.: '+obj.Data[i].GST+'</span><p class="spnaddlinfo"><i class="fas fa-info-circle"></i>&nbsp;'+obj.Data[i].AddlInfo+'</p>';
					htmlLab+='</td><td><span class="spnclose"><a data-toggle="collapse" data-target="#collapseExample'+i+'" aria-expanded="false" aria-controls="collapseExample'+i+'" class="float-right closeicon"><i class="far fa-times-circle fa-sm"></i></a></span>';
					htmlLab+='</td></tr></table></div></div></li>';
					labcntr++;
				}
			}
			htmlTrck+='</div>';
			htmlAg+='</div>';
			htmlWH+='</div>';
			htmlLab+='</div>';
			showFcty('A')
		}
		},error: function (jqXHR, textStatus, errorThrown)
	    {
        	alert("error in fetching:"+errorThrown);
	    }
	});
  }