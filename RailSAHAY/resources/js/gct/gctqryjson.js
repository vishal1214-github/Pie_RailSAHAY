var DRAFTAPPL=[];
var APPLLIST=[];
var GG_DelRfrnId='';
function fetchAppSmry()
{
var myurl="/RailSAHAY/GCTQryJson";
$("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
var htmlstr='<ul class="list-group list-group-flush">';
$("#draftCont").hide();
$("#acptCont").hide();
$("#rjctCont").hide();
$("#ndaCont").hide();

$.ajax({
url : myurl,
type : "post",
data: {Optn:'APP_SMRY'},
async : true,
success : function(data) {
    console.log(data);
    var obj= JSON.parse(data);
    var stts=obj.Stts;
    if(stts=="S")
    {
        var appcont1=obj.AppCont1;
        var appcont2=obj.AppCont2;
        var appcont3=obj.AppCont3;
        var appcont4=obj.AppCont4;
        var appcont5=obj.AppCont5;
        if(Number(appcont1)>0)
        {
            $("#draftCont").show();
            $("#draftCont").html(appcont1);
        }
        if(Number(appcont2)>0)
        {
            $("#acptCont").show();
            $("#acptCont").html(appcont2);
        }
        if(Number(appcont3)>0)
        {
            $("#rjctCont").show();
            $("#rjctCont").html(appcont3);
        }
        if(Number(appcont4)>0)
        {
            $("#rjctCont").show();
            $("#ndaCont").html(appcont4);
        }
    }
    else
    {
        //failedToast("Failed to fetch Draft Applications Summary: "+obj.ErorMesg);
    }
}
});

}
function fetchDraftApplList()
{
clearInterval(saveDraftNotification);
var myurl="/RailSAHAY/GCTQryJson";
$("#divDraftList").html('<div class="spinner-border text-danger" style="margin:1rem;"></div>');
var htmlstr='<ul class="list-group list-group-flush">';
$.ajax({
url : myurl,
type : "post",
data: {Optn:'DRAFT_APPL_LIST'},
async : true,
success : function(data) {
    console.log(data);
    var obj= JSON.parse(data);
    var stts=obj.Stts;
    if(stts=="S")
    {
        DRAFTAPPL=[];
        for(var i=0;i<obj.ApplList.length;i++)
        {
                DRAFTAPPL[i]=new Array(27);
                var rfrnid=obj.ApplList[i].RfrnId;
                var filerfrnid=obj.ApplList[i].FileRfrnId;
                var dvsn=obj.ApplList[i].Dvsn;
                var sttn=obj.ApplList[i].Sttn;
                var sttnname=obj.ApplList[i].SttnName;
                var name=obj.ApplList[i].Name;
                var desg=obj.ApplList[i].Desg;
                var mobl=obj.ApplList[i].Mobl;
                var contno=obj.ApplList[i].ContNo;
                var email=obj.ApplList[i].Email;
                var addr=obj.ApplList[i].Addr;
                var gstin=obj.ApplList[i].GstIn;
                var aplttype=obj.ApplList[i].ApltType;
                var pan=obj.ApplList[i].PAN;
                var tan=obj.ApplList[i].TAN;
                var filename1=obj.ApplList[i].FileName1;
                var filename2=obj.ApplList[i].FileName2;
                var filename3=obj.ApplList[i].FileName3;
                var filename4=obj.ApplList[i].FileName4;
                var filename5=obj.ApplList[i].FileName5;
                var filename6=obj.ApplList[i].FileName6;
                var filename7=obj.ApplList[i].FileName7;
                var planfile=obj.ApplList[i].PlanFile;
                var cmdtcont=obj.ApplList[i].CmdtCont;
                var cmdtdtls=obj.ApplList[i].CmdtDtls;
                var updttime=obj.ApplList[i].UpdtTime;
                DRAFTAPPL[i][0]=rfrnid;
                DRAFTAPPL[i][1]=filerfrnid;
                DRAFTAPPL[i][2]=dvsn;
                DRAFTAPPL[i][3]=sttn;
                DRAFTAPPL[i][4]=sttnname;
                DRAFTAPPL[i][5]=name;
                DRAFTAPPL[i][6]=desg;
                DRAFTAPPL[i][7]=mobl;
                DRAFTAPPL[i][8]=contno;
                DRAFTAPPL[i][9]=email;
                DRAFTAPPL[i][10]=addr;
                DRAFTAPPL[i][11]=gstin;
                DRAFTAPPL[i][12]=aplttype;
                DRAFTAPPL[i][13]=pan;
                DRAFTAPPL[i][14]=tan;
                DRAFTAPPL[i][15]=filename1;
                DRAFTAPPL[i][16]=filename2;
                DRAFTAPPL[i][17]=filename3;
                DRAFTAPPL[i][18]=filename4;
                DRAFTAPPL[i][19]=filename5;
                DRAFTAPPL[i][20]=filename6;
                DRAFTAPPL[i][21]=filename7;
                DRAFTAPPL[i][22]=planfile;
                DRAFTAPPL[i][23]=cmdtcont;
                DRAFTAPPL[i][24]=updttime;
                DRAFTAPPL[i][25]=cmdtdtls;
                var filelist='<ul class="list-group list-group-flush">';
                for(var k=15;k<22;k++) {
                    if(DRAFTAPPL[i][k]!='') {
                           filelist+='<li class="list-group-item" style="padding:5px;font-weight:500;font-size:13px;"><i class="fas fa-file"></i>&nbsp;&nbsp;'+DRAFTAPPL[i][k]+'</li>';                     
                    }
                }
                filelist+='</ul>';
                DRAFTAPPL[i][26]=filelist;
                htmlstr+='<li class="list-group-item">';
                htmlstr+='<div class="appl-item row">';
                htmlstr+='<div class="col-lg-8 col-sm-12"><p class="sttnname">'+sttn+ ' ('+sttnNameFmt(sttnname)+') </p></div><div class="col-lg-4 col-sm-12"><span class="pull-right dvsn">DIVISION: '+dvsn+'</span></div>';
                htmlstr+='<div class="col-lg -10 col-sm-12"><p class="aplyinfo"><span class="info-lbl">Applicant:</span><span class="info-val">'+name+' ('+desg+')</span></p><hr/><p class="aplt-info"><span class="info-lbl">Type:</span><span class="info-val">'+getApltType(aplttype)+' (</span><span class="info-lbl">GSTIN:</span><span class="info-val">'+gstin+'</span>)</p><a class="more" href="#app'+i+'" role="button" data-toggle="collapse"  aria-expanded="false" aria-controls="app'+i+'" onclick="toggleApplDtls(this,'+i+');return true;">Show More..</a><span class="info-val pull-right">'+updttime+'</span><span class="info-lbl pull-right">Applied At:</span></div><div class="col-lg-2 col-sm-12"><table class="table" style="border:0px;"><tr style="border:0px;"><td style="border:0px;"><span class="btn-edit" onclick="resumeDraftApp('+i+');"><i class="fas fa-edit"></i></span></td><td style="border:0px;"><span class="btn-del" onclick="delDraftAppl(\''+rfrnid+'\');"><i class="fas fa-trash-alt"></i></span><td></td></tr></table></div>';
                htmlstr+='</div>';
                htmlstr+='<div class="collapse" id="app'+i+'">';
                htmlstr+='<div class="card card-body" id="app'+i+'body">';
                htmlstr+='</div></div>';
                htmlstr+='</li>';
        }
        htmlstr+='</ul>';
        $("#divDraftList").html(htmlstr);    
    }
    else
    {
        failedToast("Failed to fetch Draft Applications List: "+obj.ErorMesg);
    }

}
});

}
function fetchSttsWiseAppList(stts)
{
var myurl="/RailSAHAY/GCTQryJson";
clearInterval(saveDraftNotification);
$("#divSttsWiseAppList").html('<div class="spinner-border text-danger"></div>');

if(stts=="A")
    $("#appListHedr").html('Accepted <span>Applications</span>');
if(stts=="AI")
    $("#appListHedr").html('Awaiting IPA <span>Applications</span>');
if(stts=="II")
    $("#appListHedr").html('IPA Issued <span>Applications</span>');
if(stts=="AA")
    $("#appListHedr").html('Awaiting Approval <span>Applications</span>');
if(stts=="AP")
    $("#appListHedr").html('Approved <span>Applications</span>');
if(stts=="RA")
    $("#appListHedr").html('Returned for Action <span>Applications</span>');
if(stts=="RJ")
    $("#appListHedr").html('Rejected <span>Applications</span>');
if(stts=="W")
    $("#appListHedr").html('Withdrawn <span>Applications</span>');
    
var htmlstr='<ul class="list-group list-group-flush">';
$.ajax({
url : myurl,
type : "post",
data: {Optn:'STTSWISE_APPL_LIST',AppStts:stts},
async : true,
success : function(data) {
    console.log(data);
    var obj= JSON.parse(data);
    var stts=obj.Stts;
    if(stts=="S")
    {
        APPLLIST=[];
        if(obj.ApplList.length==0) {
            $("#divSttsWiseAppList").html('<p style="font-weight:600;font-size:15px;margin:1rem;">No Application Exists !</p>');
            return false;
        }
        for(var i=0;i<obj.ApplList.length;i++)
        {
                APPLLIST[i]=new Array(28);
                var rfrnid=obj.ApplList[i].RfrnId;
                var filerfrnid=obj.ApplList[i].FileRfrnId;
                var dvsn=obj.ApplList[i].Dvsn;
                var sttn=obj.ApplList[i].Sttn;
                var sttnname=obj.ApplList[i].SttnName;
                var name=obj.ApplList[i].Name;
                var desg=obj.ApplList[i].Desg;
                var gstin=obj.ApplList[i].GstIn;
                var aplttype=obj.ApplList[i].ApltType;
                var filename1=obj.ApplList[i].FileName1;
                var filename2=obj.ApplList[i].FileName2;
                var filename3=obj.ApplList[i].FileName3;
                var filename4=obj.ApplList[i].FileName4;
                var filename5=obj.ApplList[i].FileName5;
                var filename6=obj.ApplList[i].FileName6;
                var filename7=obj.ApplList[i].FileName7;
                var planfile=obj.ApplList[i].PlanFile;
                var cmdtcont=obj.ApplList[i].CmdtCont;
                var cmdtdtls=obj.ApplList[i].CmdtDtls;
                var aplytime=obj.ApplList[i].AplyTime;
                var clostime=obj.ApplList[i].ClosTime;
                var crntstts=obj.ApplList[i].CrntStts;
                var sttstime=obj.ApplList[i].SttsTime;
                var pymtstts=obj.ApplList[i].PymtStts;
                var pymttime=obj.ApplList[i].PymtTime;
                var pndgwith=obj.ApplList[i].PndgWith;
                var rmrk=obj.ApplList[i].Rmrk;
                APPLLIST[i][0]=rfrnid;
                APPLLIST[i][1]=filerfrnid;
                APPLLIST[i][2]=dvsn;
                APPLLIST[i][3]=sttn;
                APPLLIST[i][4]=sttnname;
                APPLLIST[i][5]=name;
                APPLLIST[i][6]=desg;
                APPLLIST[i][7]=gstin;
                APPLLIST[i][8]=aplttype;
                APPLLIST[i][9]=filename1;
                APPLLIST[i][10]=filename2;
                APPLLIST[i][11]=filename3;
                APPLLIST[i][12]=filename4;
                APPLLIST[i][13]=filename5;
                APPLLIST[i][14]=filename6;
                APPLLIST[i][15]=filename7;
                APPLLIST[i][16]=planfile;
                APPLLIST[i][17]=cmdtcont;
                APPLLIST[i][18]=cmdtdtls;
                APPLLIST[i][19]=aplytime;
                APPLLIST[i][20]=clostime;
                APPLLIST[i][21]=crntstts;
                APPLLIST[i][22]=sttstime;
                APPLLIST[i][23]=pymtstts;
                APPLLIST[i][24]=pymttime;
                APPLLIST[i][25]=pndgwith;
                APPLLIST[i][26]=rmrk;
                
                var filelist='<ul class="list-group list-group-flush">';
                for(var k=9;k<16;k++) {
                    if(APPLLIST[i][k]!='') {
                           filelist+='<li class="list-group-item" style="padding:5px;font-weight:500;"><i class="fas fa-file"></i>&nbsp;&nbsp;'+APPLLIST[i][k]+'</li>';                     
                    }
                }
                filelist+='</ul>';
                APPLLIST[i][27]=filelist;
                htmlstr+='<li class="list-group-item">';
                htmlstr+='<div class="appl-item row">';
                htmlstr+='<div class="col-lg-8 col-sm-12"><p class="sttnname">'+sttn+ ' ('+sttnNameFmt(sttnname)+') </p></div><div class="col-lg-4 col-sm-12"><span class="pull-right dvsn">DIVISION: '+dvsn+'</span></div>';
                htmlstr+='<div class="col-lg -10 col-sm-12"><p class="aplyinfo"><span class="info-lbl">Applicant:</span><span class="info-val">'+name+' ('+desg+')</span></p><hr/><p class="aplt-info"><span class="info-lbl">Type:</span><span class="info-val">'+getApltType(aplttype)+' (</span><span class="info-lbl">GSTIN:</span><span class="info-val">'+gstin+'</span>)</p><a class="more" href="#app'+i+'" role="button" data-toggle="collapse"  aria-expanded="false" aria-controls="app'+i+'" onclick="toggleApplDtls(this,'+i+');return true;">Show More..</a><span class="info-val pull-right">'+updttime+'</span><span class="info-lbl pull-right">Applied At:</span></div><div class="col-lg-2 col-sm-12"><table class="table" style="border:0px;"><tr style="border:0px;"><td style="border:0px;"><span class="btn-edit"><i class="fas fa-edit"></i></span></td><td style="border:0px;"><span class="btn-del" onclick="delDraftAppl(\''+rfrnid+'\');"><i class="fas fa-trash-alt"></i></span><td></td></tr></table></div>';
                htmlstr+='</div>';
                htmlstr+='<div class="collapse" id="app'+i+'">';
                htmlstr+='<div class="card card-body" id="app'+i+'body">';
                htmlstr+='</div></div>';
                htmlstr+='</li>';
        }
        htmlstr+='</ul>';
        $("#divSttsWiseAppList").html(htmlstr);    
    }
    else
    {
        failedToast("Failed to fetch Draft Applications List: "+obj.ErorMesg);
    }
}
});

}
function getApltType(aplttype) {
    for(var i=0;i<APLTTYPE.length;i++) {
        if(aplttype==APLTTYPE[i][0])
        {
            return APLTTYPE[i][1];
        }
    }
    return aplttype;
}
function sttnNameFmt(sttnname) {
     return (sttnname.length > 30) 
    ? jQuery.trim(sttnname).substring(0, 30).split(" ").slice(0, -1).join(" ") + "..."
    : sttnname;
}
function toggleApplDtls(link,indx) {
    if($(link).text()=="Show More..")
        $(link).text("Show Less..");
    else
        $(link).text("Show More..");
    $('#app'+indx+'body').html(getApplDtls(DRAFTAPPL[indx][25],indx,'D'));   
}
function getApplDtls(cmdtlist, indx, appltype) 
{
    var cmdtdtls='';
    if((cmdtlist=='') || (typeof cmdtlist == 'undefined'))
    {
        if((appltype=='D') && (DRAFTAPPL[indx][26]!='')) {
            cmdtdtls+='<div class="row"><div class="col-lg-8 col-sm-12">&nbsp;</div><div class="col-lg-4 col-sm-12"><span class="dvsn">Files Attached</span><br/>'+DRAFTAPPL[indx][26]+'</div></div>';
            return cmdtdtls;
        }
        if((appltype!='D') && (APPLLIST[indx][27]!='')) {
            cmdtdtls+='<div class="row"><div class="col-lg-8 col-sm-12">&nbsp;</div><div class="col-lg-4 col-sm-12"><span class="dvsn">Files Attached</span><br/>'+APPLLIST[indx][27]+'</div></div>';
            return cmdtdtls;
        }
        
        return cmdtlist;
    }
    else
    {
        var cmdtdtls='<div class="row"><div class="col-lg-8 col-sm-12"><span class="dvsn">Traffic Projection</span><table class="table table-striped table-cnfm"><thead><tr><th>Commodity</th><th>Expected Volume</th><th>Expected Rakes</th><th>Traffic Type</th><th>Wagon Type</th></tr>';
        var cmdtarr1=cmdtlist.split("@");
        for(var i=0;i<cmdtarr1.length;i++)
        {
            var cmdtarr=cmdtarr1[i].split("#");
            var crntcmdt=cmdtarr[0];
            var cmdtname='';
            for(var j=0;j<CMDTARR.length;j++) {
                if(crntcmdt==CMDTARR[j][0])
                {
                    cmdtname=CMDTARR[j][1];
                    break;
                }
            }
            cmdtdtls+='<tr><td>'+cmdtname+'</td><td>'+cmdtarr[1]+'</td><td>'+cmdtarr[2]+'</td><td>'+cmdtarr[4]+'</td><td>'+cmdtarr[3]+'</td></tr>';
        }
        if(appltype=='D')
            cmdtdtls+='</table></div><div class="col-lg-4 col-sm-12"><span class="dvsn">Files Attached</span><br/>'+DRAFTAPPL[indx][26]+'</div></div>';
        else
            cmdtdtls+='</table></div><div class="col-lg-4 col-sm-12"><span class="dvsn">Files Attached</span><br/>'+APPLLIST[indx][27]+'</div></div>';
}
return cmdtdtls;
}
function resumeDraftApp(i) 
{
  $(".data-holder").hide();
  $("#rms-wizard").show();  
  $("#discButton").show();                
        $('#selDvsn [value='+DRAFTAPPL[i][2]+']').attr('selected', 'true');        
        $('#selSttn').find('option').remove().end();
        
        $('#selSttn').append($('<option>', {
                value: '',
                text: 'Select Terminal'
        }));
        for (var j=0; j < aTrml.length;j++)
        {
                var sttnarr=aTrml[j].split('#');
                if(sttnarr[1]==DRAFTAPPL[i][2])
                {
                        var sttncode=sttnarr[2];
                        $('#selSttn').append($('<option>', {
                                value: sttncode,
                                text: sttnarr[2]+"-"+sttnarr[3]
                        }));
                }
        }
        $('#selSttn [value='+DRAFTAPPL[i][3]+']').attr('selected', 'true');
        $('#ApltType [value='+DRAFTAPPL[i][12]+']').attr('selected', 'true');
        $('#Name').val(DRAFTAPPL[i][5]);
        $('#Desg').val(DRAFTAPPL[i][6]);
        $('#Mobl').val(DRAFTAPPL[i][7]);
        $('#ContNo').val(DRAFTAPPL[i][8]);
        $('#Email').val(DRAFTAPPL[i][9]);
        $('#Addr').val(DRAFTAPPL[i][10]);
        $('#GstIn').val(DRAFTAPPL[i][11]);
        $('#PAN').val(DRAFTAPPL[i][13]);
        $('#TAN').val(DRAFTAPPL[i][14]);
        setCmdtList(DRAFTAPPL[i][25]);
        $('#RfrnId').val(DRAFTAPPL[i][0]);
        $('#GCTFileRfrnId').val(DRAFTAPPL[i][1]);        
        $("#ifrmUpload").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+DRAFTAPPL[i][12]+"&FileRfrnId="+DRAFTAPPL[i][1]);
        $("#ifrmUploadPlan").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr=CONCEPT&FileRfrnId="+DRAFTAPPL[i][1]);
saveDraftNotification = setInterval(function() {
Toastify({
  text: "It's always a good idea to Save your Application as Draft !",
  duration: 4500,
  newWindow: true,
  gravity: "top",
  position: 'right',
  style: {
      background: "linear-gradient(to right, #ff5f6d, #ffc371)",
    }
}).showToast();
}, 45000);
}
function delDraftAppl(rfrnid) {
    GG_DelRfrnId=rfrnid;
    showConfirmationBox("This action will discard this draft GCT application permanently ! Do you wish to continue..");
}
function confirmYes() {
$("#confirm-modal").modal('hide');
var myurl="/RailSAHAY/GCTDataSave";
$.ajax({
url : myurl,
type : "post",
data: {Optn:'DELETE_DRAFT', RfrnId:GG_DelRfrnId},
async : true,
success : function(data) {
    console.log(data);
    var obj= JSON.parse(data);
    var stts=obj.Stts;
    if(stts=="S")
    {
        successToast("Application Deleted Successfully !");
        fetchDraftApplList();
        fetchAppSmry();
        return;
    }
    else
    {
        
        failedToast("Failed to Delete Application ! "+obj.ErorMesg);
        return;
    }
}
});
}
function showConfirmationBox(mesg)
{
	$("#cnfmmesg").html(mesg);
	$("#confirm-modal").modal('show');
}
$(document).ready(function (){
    $(".app-smry").hide();
    fetchAppSmry();
});
function fetchNodlOfcrList()
{
  	$(".data-holder").hide();
        clearInterval(saveDraftNotification);
	$("#divNodlOfcrHolder").show();
	var si_Zone='';
	var si_Dvsn='';
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=NODL_OFCR_LIST&Zone="+si_Zone+"&Dvsn="+si_Dvsn;
	/*var htmlnodlofcr='<table class="table table-striped table-bordered table-data" id="tblNodlOfcrList"><tr><th>ZONE</th><th>DIVISION</th><th>NODAL OFFICER</th><th>MOBILE NO.</th><th>OFFICE LANDLINE</th></tr>';*/
	var htmlnodlofcr='<table class="table table-striped table-bordered table-data" id="tblNodlOfcrList"><tr><th>ZONE</th><th>DIVISION</th><th>NODAL OFFICER</th><th>CONTACT NUMBER</th></tr>';
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
		for(var i=0;i<obj.NodlOfcr.length;i++)
		{
			var zone=obj.NodlOfcr[i].Zone;
			var dvsn=obj.NodlOfcr[i].Dvsn;
			var ofcr=obj.NodlOfcr[i].Ofcr;
			var mobl=obj.NodlOfcr[i].Mob;
			var ofcphn=obj.NodlOfcr[i].LL;
			htmlnodlofcr+='<tr><td class="left">'+zone+'</td><td class="left">'+dvsn+'</td><td class="left">'+ofcr+'</td><td class="left">'+ofcphn+'</td></tr>';
		}
		htmlnodlofcr+='</table>';
		$("#divNodlOfcr").html(htmlnodlofcr);
		$(".table-data").DataTable();
		}
		});
}