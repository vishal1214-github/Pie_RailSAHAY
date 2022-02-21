var CMDTARR=[];
CMDTARR.push(["CEMT","CEMENT AND CLINKER"]);
CMDTARR.push(["COAL","COAL AND COKE"]);
CMDTARR.push(["CONT","CONTAINERS"]);
CMDTARR.push(["ORES","MINERALS AND ORES"]);
CMDTARR.push(["POL","PETROLEUM PRODUCTS AND GASES"]);
CMDTARR.push(["FG","FOOD GRAINS, FLOURS AND PULSES"]);
CMDTARR.push(["IS","IRON AND STEEL"]);
CMDTARR.push(["CHEM","CHEMICAL MANURES"]);
CMDTARR.push(["OTHR","OTHERS"]);

var APLTTYPE=[];
APLTTYPE.push(["IND","INDIVIDUAL"]);
APLTTYPE.push(["HUF","HINDU UNDIVIDED FAMILY (HUF)"]);
APLTTYPE.push(["PRF","PARTNERSHIP FIRM"]);
APLTTYPE.push(["COM","COMPANIES REGISTERED UNDER COMPANIES ACT, 2013"]);
APLTTYPE.push(["LLP","LIMITED LIABILITY PARTNERSHIP (LLP)"]);
APLTTYPE.push(["SOC","REGISTERED SOCIETY/TRUST"]);
APLTTYPE.push(["JV","JOINT VENTURE"]);

function successToast(msg)
{
      cuteToast({
      type: "success",
      message: msg,
      timer: 5000
    })
};
function failedToast(msg)
{
      cuteToast({
      type: "warning",
      message: msg,
      timer: 5000
    })
};
function warningToast(msg)
{
      cuteToast({
      type: "warning",
      message: msg,
      timer: 5000
    })
};


function printTable(tableID, titleID, header)
{
   //alert(document.getElementById(tableID));
   //alert("inside printTable11");
   $("#"+tableID).dataTable().fnDestroy();
    $("#"+tableID).DataTable( {
        dom: 'Bfrtip',
        buttons: [
            //'print',
            {
                 extend: 'print',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }            
            },
            'copyHtml5',
            //'excelHtml5',
            {
                 extend: 'excelHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID         
            },
            //'csvHtml5',
            {
                 extend: 'csvHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID         
            },
            //'pdfHtml5', 
            {
                 extend: 'pdfHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID ,
		customize: function(doc) {
    		doc.styles.title = {
      		color: '#33334d',
      		fontSize: '14',
      		background: 'white',
      		alignment: 'center'
    		},
		doc.pageMargins = [10,10,10,10];
  		},
                 messageTop: header
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }            
            }
        ],
        orientation: 'landscape'
    } );
    
     //alert('Hello');
}    
function gotoPrevious()
{
$("html, body").animate({ scrollTop: 0 }, "slow");
return true;
}

function showDraftAppl()
{
    $(".data-holder").hide();
    $("#divDraftAppl").show();
    fetchDraftApplList();
}
function showSttsAppl(stts)
{
    $(".data-holder").hide();
    $("#divSttsAppList").show();
    fetchSttsWiseAppList(stts);
}
function updtCnfmDtls()
{
var filename='';
 $("html, body").animate({ scrollTop: 0 }, "slow");
 var sttn=$("#selSttn").val();
 var dvsn=$("#selDvsn").val();
 if(sttn==null)
    sttn='';
 if(dvsn==null)
    dvsn='';
if(dvsn!='')  
$("#cnfmlocn").html(sttn+" (Division: "+dvsn+")");

$("#cnfmname").html(($("#Name").val()).toUpperCase());
$("#cnfmdesg").html(($("#Desg").val()).toUpperCase());
$("#cnfmgstin").html(($("#GstIn").val()).toUpperCase());
var aplttype=$( "#ApltType option:selected" ).text();
var aplttypecode=$("#ApltType").val();
if(aplttype=="SELECT TYPE")
    aplttype="";
$("#cnfmappltype").html(aplttype);
var cmdtlist=($("#cmdt").val()).join(",");
var cmdtarr=cmdtlist.split(",");
if(cmdtlist!='')
{
var cmdtdtls='<table class="table table-striped table-cnfm"><thead><tr><th>Commodity</th><th>Expected Volume</th><th>Expected Rakes</th><th>Traffic Type</th><th>Wagon Type</th></tr>';
for(var i=0;i<cmdtarr.length;i++) {
    var crntcmdt=cmdtarr[i];
    var expdvol=$("#expdVol"+crntcmdt).val();
    var expdRake=$("#expdRake"+crntcmdt).val();
    var trfcType=$("#selTrfc"+crntcmdt).val();
    var wgonType=$("#selStck"+crntcmdt).val();
    if(trfcType==null)
        trfcType='';
    if(wgonType==null)
        wgonType='';
    var cmdtname='';
    for(var j=0;j<CMDTARR.length;j++) {
        if(crntcmdt==CMDTARR[j][0])
        {
            cmdtname=CMDTARR[j][1];
            break;
        }
    }
    cmdtdtls+='<tr><td>'+cmdtname+'</td><td>'+expdvol+'</td><td>'+expdRake+'</td><td>'+trfcType+'</td><td>'+wgonType+'</td></tr>';
}
cmdtdtls+='</table>';

$("#cnfmcmdt").html(cmdtdtls);		
}
filename = $('#planfile')[0].files.length ? $('#planfile')[0].files[0].name : "";
$("#cnfmsketch").html(filename);
$("#cnfmfileatch").html(fetchFileList($( "#ApltType option:selected" ).val()));
$("#cnfmaddr").html(($("#Addr").val()).toUpperCase());		
$("#cnfmcont").html('<i class="fas fa-mobile-alt"></i>&nbsp;&nbsp;'+($("#Mobl").val()).toUpperCase()+'     |       <i class="fas fa-phone-volume"></i>&nbsp;&nbsp;'+($("#ContNo").val()).toUpperCase()+'      |      <i class="fas fa-envelope"></i>&nbsp;&nbsp;'+($("#Email").val()).toUpperCase());		
$("#cnfmPAN").html(($("#PAN").val()).toUpperCase());	
if(aplttypecode=="HUF")
    $("#cnfmTAN").html("-NOT APPLICABLE-");		
else
    $("#cnfmTAN").html(($("#TAN").val()).toUpperCase());
    
var htmlamnt=fetchAppAmnt();    
$("#cnfmappamnt").html(htmlamnt);
return true;
}
$(document).ready(function() {
$("#discButton").hide();
$('#rms-wizard').stepWizard({
stepTheme: 'defaultTheme',/*defaultTheme,steptheme1,steptheme2*/
allstepClickable: false,
compeletedStepClickable: true,
stepCounter: true,
StepImage: true,
animation: true,
animationClass: "fadeIn",
stepValidation: true,
validation : true,
field: {
     name : {
        required : true,
        minlength: 2,
        Regex: /^[a-zA-Z]+$/,
    },
     desg : {
        required : true,
        minlength : 5,
        maxlength : 20,
        Regex: /^(?=.*[0-9_\W]).+$/,
    },
    gstin:{
        required : true,
        Regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    },
},
message: {
    name: "Please Enter Name",
    desg: "Please Enter Designation",
    GSTIN: "Please Enter GSTIN",
    appltype: "Please Select Applicant Type",
}

});
$(".data-holder").hide();
$("#rms-wizard").show();
var sttnstr='';
for (var i=0; i < aTrml.length;++i)
{
        var sttnarr=aTrml[i].split('#');
        sttnstr+= '<option value="'+sttnarr[2]+'">'+sttnarr[2]+'-'+sttnarr[3]+'</option>'; // Helplist for Stations
}
$("#sttnlist").html(sttnstr);
});
$(document).ready(function(){
                $('#selDvsn').find('option').remove().end();
                $('#selDvsn').append($('<option>', {
                                value: '',
                                text: 'Select Division'
                        }));
                for(var i=0;i<aDvsn.length;i++)
                {
                        var dvsn=aDvsn[i];
                        var dvsncode=dvsn.substring(0,dvsn.indexOf('-'));
                        $('#selDvsn').append($('<option>', {
                                value: dvsncode,
                                text: dvsn
                        }));
                }
                $('#selDvsn').on('change',function() {
                        var crntdvsn=$('#selDvsn').val();
                        $('#selSttn').find('option').remove().end();
                        
                        $('#selSttn').append($('<option>', {
                                value: '',
                                text: 'Select Terminal'
                        }));
                        for (var i=0; i < aTrml.length;++i)
                        {
                                var sttnarr=aTrml[i].split('#');
                                if(sttnarr[1]==crntdvsn)
                                {
                                        var sttncode=sttnarr[2];
                                        $('#selSttn').append($('<option>', {
                                                value: sttncode,
                                                text: sttnarr[2]+"-"+sttnarr[3]
                                        }));
                                }
                        }
                });
                
                for(var i=0;i<CMDTARR.length;i++)
                {
                        $('#cmdt').append($('<option>', {
                                value: CMDTARR[i][0],
                                text: CMDTARR[i][1]
                        }));
                }
                
                jQuery('#cmdt').multiselect({
                        columns: 1,
                        placeholder: 'SELECT COMMODITY',
                });
                 $(".ms-options li input[type='checkbox']").change(function(){                 
                    var htmlcmdtstr='';
                     var cmdtlist=($("#cmdt").val()).join(",");
                     var selcmdt=cmdtlist.split(",");
                     $("#spnCmdtCont").html('Commodities <b>['+selcmdt.length+']</b>');
                     for(var i=0;i<selcmdt.length;i++) {                     
                        var cmdtname='';
                        for(var j=0;j<CMDTARR.length;j++) {
                            if(CMDTARR[j][0]==selcmdt[i])
                            {
                                cmdtname=CMDTARR[j][1];
                                break;
                            }
                        }
                         htmlcmdtstr+='<div class="cmdt-inpt" style="border:1px solid #e6f2ff;margin-top:2px;padding:5px;"><p class="cmdt-head" style="font-weight:600;font-size:13px;color:#4d4d4d;margin-block-end:5px;"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;'+cmdtname+'</p>';
                         htmlcmdtstr+='<div class="row"><div class="col-lg-3 col-sm-12"><input type="hidden" id="txtCmdt'+(i+1)+'" value="'+selcmdt[i]+'"><label>Expected Volume (Tonnes)</label><input type="number" id="expdVol'+(selcmdt[i])+'"  min="0" class="form-control"></div><div class="col-lg-3 col-sm-12"><label>Expected Rakes</label><input class="form-control" type="number" id="expdRake'+selcmdt[i]+'" min="0" /></div><div class="col-lg-3 col-sm-12"><label>Traffic Type</label><select id="selTrfc'+selcmdt[i]+'" class="form-control" style="height:30px;"><option value="" disabled="disabled" default="true" selected="true" >TRAFFIC TYPE</option><option value="I">INWARD (UNLOADING ONLY)</option><option value="O">OUTWARD (LOADING ONLY)</option><option value="B">BOTH</option></select></div><div class="col-lg-3 col-sm-12"><label>Wagon Type</label><select id="selStck'+selcmdt[i]+'" name="selStck'+selcmdt[i]+'[]" multiple class="form-control stck-list" style="height:30px;"><option value="OPEN">OPEN</option><option value="COVERED">COVERED</option><option value="FLAT">FLAT</option></select></div></div></div>';
                     }
                     $("#cmdtdtls").html(htmlcmdtstr+'<div style="height:120px;">&nbsp;</div>');                     
                        jQuery('.stck-list').multiselect({
                                columns: 1,
                                placeholder: 'WAGON TYPE',
                        });
                     });
        $("#ApltType").change(function(){
                var appltype=$(this).val();
                var htmlstr='';
                switch(appltype)
                {                                       
                        case 'IND':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><label for="tannumb">TAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td></tr></table>';
                                break;
                        case 'HUF':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number" style="display:none;"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload Affidavit</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)"  name="file2" id="file2" class="form-control" /></td></tr></table>';
                                break;
                        case 'PRF':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><label for="tannumb">TAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file2" id="file2" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="deed">Partnership Deed</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file3" id="file3" class="form-control" /></td>';
                                htmlstr+='<td><label for="attr">Power of Attorney</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file4" id="file4" class="form-control" /></td></tr></table>';
                                break;
                        case 'COM':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><label for="tannumb">TAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file2" id="file2" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="moa">MOA</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file3" id="file3" class="form-control" /></td>';
                                htmlstr+='<td><label for="inc">Certificate of Incorporation</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file4" id="file4" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="poa">Power of Attorney</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file5" id="file5" class="form-control" /></td>';
                                htmlstr+='<td><label for="res">Resolution of Directors of the Company</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file6" id="file6" class="form-control" /></td></tr></table>';
                                break;
                        case 'LLP':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><label for="tannumb">TAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file2" id="file2" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="llp">LLP Agreement</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file3" id="file3" class="form-control" /></td>';
                                htmlstr+='<td><label for="inc">Certificate of Incorporation</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file4" id="file4" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="poa">Power of Attorney</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file5" id="file5" class="form-control" /></td>';
                                htmlstr+='<td>&nbsp;</td></tr></table>';
                                break;
                        case 'SOC':
                                htmlstr+='<table class="table"><tr><td><label for="pannumb">PAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="PAN" id="PAN" class="inpt-control required" placeholder="PAN Number"></div></td><td><label for="tannumb">TAN Number &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label><div class="inpt-group"><input  type="text" name="TAN" id="TAN" class="inpt-control required" placeholder="TAN Number"></div></td></tr><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file2" id="file2" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="reg">Certificate of Registration</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file3" id="file3" class="form-control" /></td>';
                                htmlstr+='<td><label for="moa">MOA</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file4" id="file4" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="rule">Rules and Regulations of Society</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file5" id="file5" class="form-control" /></td>';
                                htmlstr+='<td><label for="attr">Power of Attorney</label><div class="inpt-group"><input type="file"  onchange="fileValidate(this)" name="file6" id="file6" class="form-control" /></td></tr></table>';
                                break;
                        case 'JV':
                                htmlstr+='<table class="table"><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" name="pan" id="pan" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="tan" id="tan" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="llp">LLP Agreement</label><div class="inpt-group"><input type="file" name="llp" id="llp" class="form-control" /></td>';
                                htmlstr+='<td><label for="inc">Certificate of Incorporation</label><div class="inpt-group"><input type="file" name="inc" id="inc" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="poa">Power of Attorney</label><div class="inpt-group"><input type="file" name="poa" id="poa" class="form-control" /></td>';
                                htmlstr+='<td>&nbsp;</td></tr></table>';
                                break;
                }
                        $("#divCompDtls").html(htmlstr);
        });
        
                        if(GG_FileRfrnId!="")
                            restoreSessionVals();
});
function onFormSubmit() {
    //do nothing
}
function formSubmit() {
     if($.trim($('.required').val()) === '') {
        failedToast('Please fill out all required fields before payment');
        return false;
    } else {
        $(".submit").attr("disabled","disabled");
        $(".submit .btn").attr("disabled","disabled");
        return true;
    }

}
function saveDraft() {
    var cmdtlist=($("#cmdt").val()).join(",");
    console.log("cmdtlist:"+cmdtlist);
    var cmdtarr=cmdtlist.split(",");  
    console.log("cmdtarr:"+cmdtarr.length);
    var cmdtdtls='';
    if(cmdtlist!='')
    {
        for(var i=0;i<cmdtarr.length;i++) {
            var crntcmdt=cmdtarr[i];
            var expdvol=$("#expdVol"+crntcmdt).val();
            var expdRake=$("#expdRake"+crntcmdt).val();
            var trfcType=$("#selTrfc"+crntcmdt).val();
            var wgonType=$("#selStck"+crntcmdt).val();
            if(trfcType==null)
                trfcType='';
            if(wgonType==null)
                wgonType='';
                
            if(i==0)
                cmdtdtls+=crntcmdt+"#"+expdvol+"#"+expdRake+"#"+trfcType+"#"+wgonType;
            else
                cmdtdtls+="@"+crntcmdt+"#"+expdvol+"#"+expdRake+"#"+trfcType+"#"+wgonType;
        }
    }
    $("#CmdtList").val(cmdtlist);
    $("#CmdtDtls").val(cmdtdtls);
    $("#Optn").val("SAVE_DRAFT");
    if((cmdtlist=='') || (cmdtlist==null))
        $("#CmdtCont").val("0");
    else
        $("#CmdtCont").val(cmdtarr.length);
        
    $(".btn-savedraft").attr("disabled","disabled");
    $(".draft").html("Saving Draft, Please wait..");
    $(".draft").css({"font-weight":"600","position":"absolute","left":"46%"});
    $("#RfrnId").val(GG_RfrnId);
    $("#FileRfrnId").val(GG_FileRfrnId);
    $("#frmInpt").submit();
}
function fileValidate(cntl) {
       var fi = cntl;
        // Check if any file is selected.
        if (fi.files.length > 0) {
            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fsize = fi.files.item(i).size;
                var file = Math.round((fsize / 1024));
                // The size of the file.
                if (file >= 4096) {
                    alert("Files of upto 4MB can only be attached ! Please reduce the file size");
                    $(cntl).val('');
                    return false;
                }
            }
        }
         var validExtensions = ["pdf","jpg","jpeg","docx","doc"];
        var file = $(cntl).val().split('.').pop();
        if (validExtensions.indexOf(file) == -1) {
            alert("File Formats Allowed : "+validExtensions.join(', '));
            $(cntl).val('');
            return false;
        }
    }
    function setPlanFile() {
        if(GG_PlanFileName!="")
            $("#divPlanFile").html('<div class="inpt-form-group"><label for="locn">Concept Plan</label><br/><a href="javascript:void(0);" onclick="downloadFile(0);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_PlanFileName+'</a>');
        else
            $("#divPlanFile").html('<div class="inpt-form-group"><label for="locn">Upload Concept Plan</label><input type="file" id="planfile" name="planfile" class="form-control" onchange="fileValidate(this)" /></div>');
    }
    function setFileReq(ctgr) {
    var htmlstr='<table class="table"><tr>';
    switch(ctgr)
                {
            
                        case 'IND':                                
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">TAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                break;
                        case 'HUF':
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">Affidavit Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload Affidavit</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                break;
                        case 'PRF':
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">TAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                
                                if(GG_FileName3!="")
                                    htmlstr+='<tr><td><label for="pan">Partnership Deed Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">Partnership Deed</label><div class="inpt-group"><input type="file" name="file3" id="file3"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName4!="")
                                    htmlstr+='<td><label for="pan">Power of Attorney Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Power of Attorney</label><div class="inpt-group"><input type="file" name="file4" id="file4"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                
                                break;
                        case 'COM':
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">TAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                
                                if(GG_FileName3!="")
                                    htmlstr+='<tr><td><label for="pan">MOA Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">MOA</label><div class="inpt-group"><input type="file" name="file3" id="file3"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName4!="")
                                    htmlstr+='<td><label for="pan">Certificate of Incorporation</label><br/><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Certificate of Incorporation</label><div class="inpt-group"><input type="file" name="file4" id="file4"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                if(GG_FileName5!="")
                                    htmlstr+='<tr><td><label for="pan">Power of Attorney Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">Power of Attorney</label><div class="inpt-group"><input type="file" name="file5" id="file5"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName6!="")
                                    htmlstr+='<td><label for="pan">Resolution of Directors of the Company</label><br/><a href="javascript:void(0);" onclick="downloadFile(6);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName6+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Resolution of Directors of the Company</label><div class="inpt-group"><input type="file" name="file6" id="file6"  onchange="fileValidate(this)" class="form-control" /></td></tr>';

                                break;
                        case 'LLP':
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">TAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                
                                if(GG_FileName3!="")
                                    htmlstr+='<tr><td><label for="pan">LLP Agreement</label><br/><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">LLP Agreement</label><div class="inpt-group"><input type="file" name="file3" id="file3"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName4!="")
                                    htmlstr+='<td><label for="pan">Certificate of Incorporation</label><br/><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Certificate of Incorporation</label><div class="inpt-group"><input type="file" name="file4" id="file4"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                if(GG_FileName5!="")
                                    htmlstr+='<tr><td><label for="pan">Power of Attorney Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">Power of Attorney</label><div class="inpt-group"><input type="file" name="file5" id="file5"  onchange="fileValidate(this)" class="form-control" /></td>';

                                break;
                        case 'SOC':
                                if(GG_FileName1!="")
                                    htmlstr+='<td><label for="pan">PAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></td>';
                                else
                                    htmlstr+='<td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" onchange="fileValidate(this)" name="file1" id="file1" class="form-control" /></td>';
                                
                                if(GG_FileName2!="")
                                    htmlstr+='<td><label for="pan">TAN Uploaded</label><br/><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="file2" id="file2"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                
                                if(GG_FileName3!="")
                                    htmlstr+='<tr><td><label for="pan">Certificate of Registration</label><br/><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">Certificate of Registration</label><div class="inpt-group"><input type="file" name="file3" id="file3"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName4!="")
                                    htmlstr+='<td><label for="pan">MOA</label><br/><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">MOA</label><div class="inpt-group"><input type="file" name="file4" id="file4"  onchange="fileValidate(this)" class="form-control" /></td></tr>';
                                if(GG_FileName5!="")
                                    htmlstr+='<tr><td><label for="pan">Rules and Regulations of Society</label><br/><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></td>';
                                else
                                    htmlstr+='<tr><td><label for="tan">Rules and Regulations of Society</label><div class="inpt-group"><input type="file" name="file5" id="file5"  onchange="fileValidate(this)" class="form-control" /></td>';
                                
                                if(GG_FileName6!="")
                                    htmlstr+='<td><label for="pan">Power of Attorney</label><br/><a href="javascript:void(0);" onclick="downloadFile(6);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName6+'</a></td>';
                                else
                                    htmlstr+='<td><label for="tan">Power of Attorney</label><div class="inpt-group"><input type="file" name="file6" id="file6"  onchange="fileValidate(this)" class="form-control" /></td></tr>';

                                break;
                        case 'JV':
                                htmlstr+='<table class="table"><tr><td><label for="pan">Upload PAN</label><div class="inpt-group"><input type="file" name="pan" id="pan" class="form-control" /></td>';
                                htmlstr+='<td><label for="tan">Upload TAN</label><div class="inpt-group"><input type="file" name="tan" id="tan" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="llp">LLP Agreement</label><div class="inpt-group"><input type="file" name="llp" id="llp" class="form-control" /></td>';
                                htmlstr+='<td><label for="inc">Certificate of Incorporation</label><div class="inpt-group"><input type="file" name="inc" id="inc" class="form-control" /></td></tr>';
                                htmlstr+='<tr><td><label for="poa">Power of Attorney</label><div class="inpt-group"><input type="file" name="poa" id="poa" class="form-control" /></td>';
                                htmlstr+='<td>&nbsp;</td></tr></table>';
                                break;
                }
                htmlstr+='</tr></table>'
                $("#divCompDtls").html(htmlstr);
    }
    
    function restoreSessionVals() {
        $("#discButton").show();
        $('#selDvsn [value='+GG_Dvsn+']').attr('selected', 'true');        
        $('#selSttn').find('option').remove().end();
        
        $('#selSttn').append($('<option>', {
                value: '',
                text: 'Select Terminal'
        }));
        for (var i=0; i < aTrml.length;++i)
        {
                var sttnarr=aTrml[i].split('#');
                if(sttnarr[1]==GG_Dvsn)
                {
                        var sttncode=sttnarr[2];
                        $('#selSttn').append($('<option>', {
                                value: sttncode,
                                text: sttnarr[2]+"-"+sttnarr[3]
                        }));
                }
        }
        $('#selSttn [value='+GG_Trml+']').attr('selected', 'true');
        $('#ApltType [value='+GG_ApltType+']').attr('selected', 'true');
        $('#Name').val(GG_Name);
        $('#Desg').val(GG_Desg);
        $('#Mobl').val(GG_Mobl);
        $('#ContNo').val(GG_ContNo);
        $('#Email').val(GG_Email);
        $('#Addr').val(GG_Addr);
        $('#PAN').val(GG_PAN);
        $('#TAN').val(GG_TAN);
        $('#GstIn').val(GG_GstIn);
        setFileReq(GG_ApltType);
        setPlanFile();
        setCmdtList(GG_CmdtDtls);
    }
    function saveAppl() {
        var myurl="/RailSAHAY/GCTDataSave";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'DISCARD_APPL'},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
			successToast("Application added in your Drafts List");
                        $("#discButton").hide();  
                        GG_RfrnId="";
                        GG_FileRfrnId  ="";
                        GG_Dvsn        ="";
                        GG_Trml        ="";
                        GG_Name        ="";
                        GG_Desg        ="";
                        GG_Mobl        ="";
                        GG_ContNo        ="";
                        GG_Email        ="";
                        GG_Addr        ="";
                        GG_PAN        ="";
                        GG_TAN        ="";
                        GG_GstIn       ="";
                        GG_ApltType    ="";
                        GG_FileName1   ="";
                        GG_FileName2   ="";
                        GG_FileName3   ="";
                        GG_FileName4   ="";
                        GG_FileName5   ="";
                        GG_FileName6   ="";
                        GG_FileName7   ="";
                        GG_PlanFileName="";
                        GG_CmdtList    ="";
                        GG_CmdtCont    ="";
                        GG_CmdtDtls    ="";
                        resetAppl();
	            }
	            else
	            {
	                failedToast("Failed to Save the application, please login again"+obj.ErorMesg);
	            }
		}
		});
    }
    function resetAppl() {        
        $('#selDvsn').find('option').remove().end();
        $('#selDvsn').append($('<option>', {
                        value: '',
                        text: 'Select Division'
                }));
        for(var i=0;i<aDvsn.length;i++)
        {
                var dvsn=aDvsn[i];
                var dvsncode=dvsn.substring(0,dvsn.indexOf('-'));
                $('#selDvsn').append($('<option>', {
                        value: dvsncode,
                        text: dvsn
                }));
        }
        $('#selSttn').find('option').remove().end();
        $("#Name").val("");
        $("#Desg").val("");
        $("#GstIn").val("");
        $('#ApltType').find('option:eq(0)').prop('selected', true);
        $("#divCompDtls").html('');     
        setPlanFile();
    }
    function fetchFileList(aplttype) {        
        htmlstr='<ul class="list-group list-group-flush">';
        switch(aplttype) {
            case 'IND':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                break;
            case 'HUF':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Affidavit</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Affidavit</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                break;
            case 'PRF':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                    
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName3!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Partnership Deed</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Partnership Deed</span><span class="file-val">'+ ($('#file3')[0].files.length ? $('#file3')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName4!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val">'+ ($('#file4')[0].files.length ? $('#file4')[0].files[0].name : "")+'</li>';
                break;
            case 'COM':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                    
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName3!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;MOA</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;MOA</span><span class="file-val">'+ ($('#file3')[0].files.length ? $('#file3')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName4!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certificate of Incorporation</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certificate of Incorporation</span><span class="file-val">'+ ($('#file4')[0].files.length ? $('#file4')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName5!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val">'+ ($('#file5')[0].files.length ? $('#file5')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName6!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Resolution of Directors of the Company</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(6);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName6+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Resolution of Directors of the Company</span><span class="file-val">'+ ($('#file6')[0].files.length ? $('#file6')[0].files[0].name : "")+'</li>';
                break;
                
            case 'LLP':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                    
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName3!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;LLP Agreement</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;LLP Agreement</span><span class="file-val">'+ ($('#file3')[0].files.length ? $('#file3')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName4!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certificate of Incorporation</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certificate of Incorporation</span><span class="file-val">'+ ($('#file4')[0].files.length ? $('#file4')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName5!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val">'+ ($('#file5')[0].files.length ? $('#file5')[0].files[0].name : "")+'</li>';
                
                break;
            case 'SOC':
                if((GG_FileName1!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(1);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName1+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;PAN</span><span class="file-val">'+ ($('#file1')[0].files.length ? $('#file1')[0].files[0].name : "")+'</li>';
                    
                if((GG_FileName2!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(2);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName2+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;TAN</span><span class="file-val">'+ ($('#file2')[0].files.length ? $('#file2')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName3!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certification of Registration</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(3);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName3+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Certificate of Registration</span><span class="file-val">'+ ($('#file3')[0].files.length ? $('#file3')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName4!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;MOA</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(4);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName4+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;MOA</span><span class="file-val">'+ ($('#file4')[0].files.length ? $('#file4')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName5!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Rules & Regulations of Society</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(5);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName5+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Rules & Regulations of Society</span><span class="file-val">'+ ($('#file5')[0].files.length ? $('#file5')[0].files[0].name : "")+'</li>';
                
                if((GG_FileName6!="") && (GG_ApltType==aplttype))
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val"><a href="javascript:void(0);" onclick="downloadFile(6);" class="file-link"><i class="fas fa-file-download"></i>&nbsp;&nbsp;'+GG_FileName6+'</a></span></li>';
                else
                    htmlstr+='<li class="list-group-item"><span class="file-lbl"><i class="fas fa-caret-right"></i>&nbsp;&nbsp;Power of Attorney</span><span class="file-val">'+ ($('#file6')[0].files.length ? $('#file6')[0].files[0].name : "")+'</li>';
                break;
        }
        htmlstr+='</ul>';
        return htmlstr;
    }
    function showCmdtRef() {
      $("#refModalHedr").html("Commodity Reference");
      var htmlstr='<table class="table table-striped table-data"><thead><tr><th>Commodity Head</th><th>Commodity</th></tr></thead>';
for(var i=0;i<CMDTMPNG7A.length;i++)
{
	var cmdtstr=CMDTMPNG7A[i];
	var maincmdtcode=cmdtstr.substring(0,cmdtstr.indexOf("-"));
	for(var j=0;j<CMDTARR.length;j++)
	{
		if(maincmdtcode==CMDTARR[j][0])
		{
			maincmdtname=CMDTARR[j][1];
			break;
		}
	}
	var cmdt=cmdtstr.substring(cmdtstr.indexOf("-")+1);
	htmlstr+='<tr><td>'+maincmdtname+'</td><td>'+cmdt+'</td></tr>';
}
htmlstr+='</table>';
$("#refModalBody").html(htmlstr);
$(".table-data").DataTable();
      $("#refModal").modal('show');  
    }
    function setCmdtList(cmdtdtls) {
        if((cmdtdtls=='') || (cmdtdtls==null))
            return '';
        var cmdtarr1=cmdtdtls.split("@");
        var cmdtlist='';
        var cmdtcntr=0;
        
        for(var i=0;i<cmdtarr1.length;i++) {
            var cmdtarr=cmdtarr1[i].split("#");
            if(i==0)
                cmdtlist=cmdtarr[0];
            else
                cmdtlist+=','+cmdtarr[0];
        }
        
        for(var i=0;i<cmdtarr1.length;i++) {
            var cmdtarr=cmdtarr1[i].split("#");
            var cmdtname=getCmdtName(cmdtarr[0]);
            var expdvol=cmdtarr[1];
            var expdrake=cmdtarr[2];
            var trfctype=cmdtarr[3];
            var wgontype=cmdtarr[4];  
            $("#txtCmdt"+(i+1)).val(cmdtarr[0]);
            $("#expdVol"+(cmdtarr[0])).val(cmdtarr[1]);
            $("#expdRake"+(cmdtarr[0])).val(cmdtarr[2]);
            $("#trfcType"+(cmdtarr[0])).val(cmdtarr[3]);
            $("#wgonType"+(cmdtarr[0])).val(cmdtarr[4]);
        }
    }
    function getCmdtName(cmdtcode) {
        for(var i=0;i<CMDTARR.length;i++) {
            if(cmdtcode==CMDTARR[i][0])
                return CMDTARR[i][1];
                break;
        }
    }
    function fetchAppAmnt() {
        htmlstr='<table class="table table-striped table-bordered">';
        htmlstr+='<tr><td>Application Amount</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;100000</td></tr>';
        htmlstr+='<tr><td>GST (@18%)</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;18000</td></tr>';
        htmlstr+='<tr><td>Total Amount Payable</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;118000</td></tr>';
        htmlstr+='<tr><td colspan="2" style="font-weight:600;color:#b32400;text-align:center;">*Payment made towards this GCT Application if Non-Refundable</td></tr>';
        htmlstr+='</table>';
        return htmlstr;
    }