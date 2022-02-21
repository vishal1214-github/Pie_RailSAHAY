CMDTARR=[];
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

function saveDraft()
{
var myurl="/RailSAHAY/GCTSaveAppData";
var dvsn=$("#selDvsn").val();
var sttn=$("#selSttn").val();
var name=$("#Name").val();
var desg=$("#Desg").val();
var mobl=$("#Mobl").val();
var contno=$("#ContNo").val();
var email=$("#Email").val();
var addr=$("#Addr").val();
var gstin=$("#GstIn").val();
var apltctgr=$("#ApltType").val();
var pan=$("#PAN").val();
var tan=$("#TAN").val();
var filerfrnid=$("#GCTFileRfrnId").val();
var rfrnid=$("#RfrnId").val();
var cmdtlist=($("#cmdt").val()).join(",");
$("#CmdtList").val(cmdtlist);

if(!chkValidations()) 
{
return false;
}

   
 /*if(!$("#frmInpt").valid())
 {
        failedToast("Please fill required fields before saving Draft");
	return false;
   
 }*/

var cmdtarr=cmdtlist.split(",");  
var cmdtdtls='';
if(cmdtlist!='')
{
    for(var i=0;i<cmdtarr.length;i++) {
        var crntcmdt=cmdtarr[i];
        console.log("Current Commodity:"+crntcmdt);
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
        $("#txtCmdtList").val(cmdtlist);
        $("#txtCmdtDtls").val(cmdtdtls);
        $("#CmdtDtls").val(cmdtdtls);
        if((cmdtlist=='') || (cmdtlist==null))
            $("#CmdtCont").val("0");
        else
            $("#CmdtCont").val(cmdtarr.length);
        
var cmdtcont=$("#CmdtCont").val();
var cmdtlist=$("#CmdtList").val();
var cmdtdtls=$("#CmdtDtls").val();

$.ajax({
url : myurl,
type : "post",
data: {Optn:'SAVE_DRAFT',Dvsn:dvsn,Sttn:sttn,Name:name,Desg:desg,Mobl:mobl,ContNo:contno,Email:email,Addr:addr,GstIn:gstin,ApltCtgr:apltctgr,PAN:pan,TAN:tan,CmdtCont:cmdtcont,CmdtList:cmdtlist,CmdtDtls:cmdtdtls,RfrnId:rfrnid,GCTFileRfrnId:filerfrnid},
async : true,
success : function(data) {
    console.log(data);
    var obj= JSON.parse(data);
    var stts=obj.Stts;
    if(stts=="S")
    {
        var rfrnid=obj.RfrnId;
        $("#RfrnId").val(rfrnid);
        successToast("Draft Saved Successfully !");
        fetchAppSmry();
    }
    else
    {
        failedToast("Failed to save Draft: "+obj.ErorMesg);
    }
}
});

}
function gotoPrevious()
{
$("html, body").animate({ scrollTop: 0 }, "slow");
var appltype=$("#ApltType").val();
var filerfrnid=$("#GCTFileRfrnId").val();
$("#ifrmUpload").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+appltype+"&FileRfrnId="+filerfrnid);
$("#ifrmUploadPlan").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr=CONCEPT&FileRfrnId="+filerfrnid);

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
 
var appltype1=$("#ApltType").val();
var filerfrnid1=$("#GCTFileRfrnId").val();
$("#ifrmUpload").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+appltype1+"&FileRfrnId="+filerfrnid1);
$("#ifrmUploadPlan").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr=CONCEPT&FileRfrnId="+filerfrnid1);

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
$("#cnfmaddr").html(($("#Addr").val()).toUpperCase());		
var docstts=$("#DocStts").val();
var planstts=$("#PlanStts").val();
if(docstts!="Y")
{
    $("#cnfmfileatch").html("PENDING");
    $("#cnfmfileatch").css({"color":"#b32400"});
}
else 
{
    $("#cnfmfileatch").html("COMPLETE");
    $("#cnfmfileatch").css({"color":"#339933"});
}
if(planstts!="Y")
{
    $("#cnfmsketch").html("PENDING");
    $("#cnfmsketch").css({"color":"#b32400"});
}
else 
{
    $("#cnfmsketch").html("UPLOADED");
    $("#cnfmsketch").css({"color":"#339933"});
}
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
        htmlstr+='<tr><td>Application Amount</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;20000</td></tr>';
        htmlstr+='<tr><td>GST (@18%)</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;3600</td></tr>';
        htmlstr+='<tr><td>Total Amount Payable</td><td><i class="fas fa-rupee-sign"></i>&nbsp;&nbsp;23600</td></tr>';
        htmlstr+='<tr><td colspan="2" style="font-weight:600;color:#b32400;text-align:center;">*Payment made towards this GCT Application if Non-Refundable</td></tr>';
        htmlstr+='</table>';
        
        $("#txtGstAmnt").val("3600");
        $("#txtAppAmnt").val("20000");
        $("#txtTotlAmnt").val("23600");
        return htmlstr;
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
     Name : {
        required : true,
        minlength: 2,
        Regex: /^[a-zA-Z]+$/
    },
     Desg : {
        required : true,
        minlength : 5,
        maxlength : 20,
        Regex: /^(?=.*[0-9_\W]).+$/
    },
    gstin:{
        required : true,
        Regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    }
},
message: {
    Name: "Please Enter Name",
   Desg: "Please Enter Designation",
    GSTIN: "Please Enter GSTIN",
    appltype: "Please Select Applicant Type"
}

});
$(".data-holder").hide();
$("#rms-wizard").show();



 $('.alpha').on('input', function() {
  $(this).val($(this).val().replace(/[^a-z\s]/gi, ''))
});

 $('.numeric').on('input', function() {
  $(this).val($(this).val().replace(/[^0-9.]/gi, ''));
});
 $('.alphanum').on('input', function() {
  $(this).val($(this).val().replace(/[^a-z0-9._,-\s]/gi, ''));
});
$('.alphanumStrct').on('input', function() {
  $(this).val($(this).val().replace(/[^a-z0-9]/gi, ''));
});
$('.digit').on('input', function() {
  $(this).val($(this).val().replace(/[^0-9]/gi, ''));
});
   
    
$('.keyup-email').keyup(function() {
    $('span.error-keyup').remove();
    $(this).removeClass("emailError");
    var inputVal = $(this).val();
     var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
 
    if(!emailReg.test(inputVal)) {
        $(this).after('<span class="error error-keyup" style="color:red">Invalid Email Format.<\/span>');
        $(this).addClass("emailError");
    }
});
 $('input.required').keyup(function() {
  var elem="req"+this.id;
    $('#'+elem).remove();
   $ ('span.error-req').removeClass();
     $(this).removeClass("inpError");
    } )

 $('input').focus(function () {
    var elem="span"+this.id;
   
   // $('span.error-len').remove();
    $('#'+elem).remove();
    $(this).removeClass('inpError');
    
});
$('select').click(function () {
    var elem="span"+this.id;
    $('span.error-len').remove();
    $('#'+elem).remove();
    $(this).removeClass('inpError');
    
});
$.validator.addMethod(
            "regex",
            function(value, element, regexp) 
            {
                if (regexp.constructor != RegExp)
                    regexp = new RegExp(regexp);
                else if (regexp.global)
                    regexp.lastIndex = 0;
                return this.optional(element) || regexp.test(value);
            },
            "Please check your input."
    );

$("#frmInpt").validate({
    rules: {
      Name : {
        required: true,
        minlength: 3
      },
      Desg: {
        required: true,
         minlength: 2
     },
      Email: {
        required: true,
        email: true
      },
      Mobl: {
        required: true
        },
      ContNo: {
      digit: true,
         minlength: 10
      }
     
    },
    messages : {
      Name: {
        required:   "Please Enter Name",
        minlength: "Name should be at least 3 characters"
      },
      Desg: {
        required: "Please Enter Designation",
        minlength: "Designation should be at least 2 characters"
      },
      Email: {
        email: "The email should be in the format: abc@domain.tld"
      },
      Mobl: {
       required: "Please Enter Mobile Number",
        minlength: "Mobile Number should be 10 characters",
        digit: "Mobile Number should be 10 digits"
      },
      ContNo: {
       minlength: "Contact Number should be 10 digits",
        digit: "Contact Number should be 10 digits"
      }
    }
  });



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
                        placeholder: 'SELECT COMMODITY'
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
                                placeholder: 'WAGON TYPE'
                        });
                     });
        $("#ApltType").change(function(){
                var appltype=$(this).val();
                var filerfrnid=$("#GCTFileRfrnId").val();
                $("#ifrmUpload").attr("src","/RailSAHAY/pages/GCTFileUpload.jsp?ApltCtgr="+appltype+"&FileRfrnId="+filerfrnid);
        });
});
function fileValidate(cntl) {
       var fi = cntl;
        // Check if any file is selected.
        if (fi.files.length > 0) {
            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fsize = fi.files.item(i).size;
                var file = Math.round((fsize / 1024));
                // The size of the file.
                if (file >= 5120) {
                    alert("Files of upto 5MB can only be attached ! Please reduce the file size");
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
    
    
function onFormSubmit() {    
    $("#frmGCTPymtProcess").submit();
}
function formSubmit() {
     /*if($.trim($('.required').val()) === '') {
        failedToast('Please fill out all required fields before payment');
        return false;
    } */
    
  var reqflag = true;
  $('.required').each(function() {
    if ($.trim($(this).val())=== '') {
        $(this).after('<span class="error error-req" style="color:red">Please Enter Value<\/span>');
         $(this).addClass("inpError");
        reqflag=false;
    } })
    
   if (reqflag==false) {
   failedToast("Please fill out all required fields before payment");
	return false;
   }
    var docstts=$("#DocStts").val();
    var planstts=$("#PlanStts").val();
    if(docstts!="Y") {
        failedToast('Please upload all the required documents before payment');
        return false;
    }
    if(planstts!="Y") {
        failedToast('Please upload concept plan before payment');
        return false;
    }
    $(".submit").attr("disabled","disabled");
    $(".submit .btn").attr("disabled","disabled");
    $("#txtZone").val("04");
    $("#txtDvsn").val($("#selDvsn").val());
    $("#txtSttn").val($("#selSttn").val());
    $("#txtName").val($("#Name").val());
    $("#txtDesg").val($("#Desg").val());
    $("#txtMobl").val($("#Mobl").val());
    $("#txtContNo").val($("#ContNo").val());
    $("#txtEmail").val($("#Email").val());
    $("#txtAddr").val($("#Addr").val());
    $("#txtGstIn").val($("#GstIn").val());
    $("#txtApltCtgr").val($("#ApltType").val());
    $("#txtPAN").val($("#PAN").val());
    $("#txtTAN").val($("#TAN").val());
    $("#txtFileList").val($("#FileList").val());
    $("#txtRfrnId").val($("#RfrnId").val());
    $("#txtFileRfrnId").val($("#GCTFileRfrnId").val());
     var cmdtlist=($("#cmdt").val()).join(",");
    var cmdtarr=cmdtlist.split(",");  
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
    $("#txtCmdtList").val(cmdtlist);
    $("#txtCmdtDtls").val(cmdtdtls);
    if((cmdtlist=='') || (cmdtlist==null))
        $("#txtCmdtCont").val("0");
    else
        $("#txtCmdtCont").val(cmdtarr.length);
    onFormSubmit();
}
function openGCTOPolicy()
{
	$("#refDocument").modal('show');	
}
function chkValidations() {
var dvsn=$("#selDvsn").val();
var sttn=$("#selSttn").val();
    if((dvsn=='') || (sttn==''))
{
	successToast("Please select Location of the Terminal, before saving Draft");
	return false;
}
 $('span.error-req').remove();
 
  var reqflag = true;
  $('input.required').each(function() {
  var elem="req"+this.id;
 
    if ($.trim($(this).val())=== '') {
        $(this).after('<span id="'+elem+'"class="error error-req" style="color:red">Please Enter Value<\/span>');
         $(this).addClass("inpError");
        reqflag=false;
    } })
    
   if (reqflag==false) {
   failedToast("Please fill out all required fields before save");
	return false;
   }
 
 var flag="";
 
 var emailptrn = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);

 
        var emailtxt=$("#Email").val();
        if (!emailptrn.test(emailtxt)) {
        $('span.error-email').remove();
          //  failedToast("Please provide valid Email before saving Draft");
            $("#Email").after('<span class="error error-email" style="color:red">Please Enter Valid Email<\/span>');
            $("#Email").addClass("emailError");
            flag="F";
	//return false;
        }
       
     

   if ($("#Mobl").val().length != 10 ) {
    $('#spanMobl').remove();
     $("#Mobl").after('<span id="spanMobl" class="error" style="color:red">Please Enter Valid Mobile<\/span>');
    $("#Mobl").addClass("inpError");
     flag="F";
	}
   
    if ($("#PAN").val())
    {
        if ($("#PAN").val().length != 10 ) {
        $('#spanPAN').remove();
        $("#PAN").after('<span id="spanPAN" class="error" style="color:red">Please Enter Valid PAN<\/span>');
        $("#PAN").addClass("inpError");
        flag="F";
        }   
    }
   
    if ($("#TAN").val())
    {
        if ($("#TAN").val().length != 10 ) { 
        $('#spanTAN').remove();
          $("#TAN").after('<span  id="spanTAN" class="error" style="color:red">Please Enter Valid TAN<\/span>');
            $("#TAN").addClass("inpError");
	 flag="F";
        }   
    }
    
    
     if ($("#GstIn").val())
    {
        if ($("#GstIn").val().length != 15 ) {
          $('#spanGstIn').remove();
         $("#GstIn").after('<span   id="spanGstIn" class="error" style="color:red">Please Enter Valid GstIn<\/span>');
            $("#GstIn").addClass("inpError");
	
         flag="F";
        }   
    }
   
  
  if( !$("#ApltType").val())
  {
   $('#spanApltType').remove();
    $("#ApltType").after('<span id="spanApltType" class="error" style="color:red">Please select Application Category<\/span>');
            $("#ApltType").addClass("inpError");
            flag="F";
  }
  

if(flag==="F") {
 failedToast("Please provide Valid Inputs");
 return false;
 }
 return true;
}

 

