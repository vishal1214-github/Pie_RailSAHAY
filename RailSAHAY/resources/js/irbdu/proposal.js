
var GG_LOV=[];
$(document).ready(function(){
fetchLOVs();
});
    function onFormSubmit()
    {
        var inptfile=$("#inptfile").val();
        if(inptfile=="") {
            alert("Please Upload the Proposal File");
            return false;
        }
	    
            $("#btnSubmit").attr('disabled','disabled');
            $("#divLdngImg").html('<div class="spinner-border text-danger"></div>');
	    GG_PropZoneFlag="Y";
            return true;
    }
    function checkKeyEntry(e) {
         var k = e.keyCode;
         return ((k > 64 && k < 94) ||  (k > 96 && k < 123)|| (k > 39 && k < 47) || (k == 8)|| (k == 16)|| (k == 20) || (k == 219)|| (k == 221)||(k==32)|| (k==13)|| (k==61)  || (k >= 48 && k <= 57));
    }
    function downloadProposalFile(fileid) {
        $("#FileId").val(fileid);
        $("#frmGetFile").submit();
    }
    function fileValidate() {
       var fi = document.getElementById('inptfile');
        // Check if any file is selected.
        if (fi.files.length > 0) {
            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fsize = fi.files.item(i).size;
                var file = Math.round((fsize / 1024));
                // The size of the file.
                if (file >= 10240) {
                    alert("File is too Big, please select a file less than 10MB");
                    $("#inptfile").val('');
                    return false;
                }
            }
        }
         var validExtensions = ["pdf","xls","xlsx","docx","doc"];
        var file = $("#inptfile").val().split('.').pop();
        if (validExtensions.indexOf(file) == -1) {
            alert("File Formats Allowed : "+validExtensions.join(', '));
            $("#inptfile").val('');
            return false;
        }
    }
    function delFile(delitem, propid) {
    $(delitem).closest('tr').addClass("selected-row");
    if (confirm('The selected proposal will get deleted! You want to continue')) {
     var myurl="/RailSAHAY/AcqnPlan";
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'DELETE_PROPOSAL_FILE',PropId:propid},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="SUCCESS")
            {
               successToast("Proposal Deleted Successfully !");
               getProposalList();
            }
            else
            {
                failedToast("Failed to Delete! "+obj.ErorMesg);
            }
	}
	});
        }
        else {
            $(delitem).closest('tr').removeClass('selected-row');
        }
    }
    function getProposalList()
    {
      	var zone='';
	var dvsn='';
	if(GG_LognLocnFlag=="Z")
		zone=GG_CrntZone;
	if(GG_LognLocnFlag=="D")
	{
		zone=GG_DvsnZone;
		dvsn=GG_CrntDvsn;
	}
	var proptype2=$("#PropType").val();
	/*
	var proptype2='';
	if(proptype1!=null)
		proptype2=proptype1.join("#");
*/
	$("#frmInpt").attr("enctype","application/x-www-form-urlencoded");
      var myurl="/RailSAHAY/AcqnPlan";
      $("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
      var htmlstr='';
	if(GG_LognLocnFlag=="Z")
	{
		htmlstr+='<table class="table table-bordered table-data"><thead><tr><th rowspan="2">SR.No.</th><th colspan="9">Business Proposal Detail</th><th colspan="3">Decision Taken</th></tr><tr><th>Zone</th><th>Proposal Type</th><th>File Attached</th><th>Proposal Detail (Description)</th><th>Commodity</th><th>Stock Type</th><th>Remarks</th><th>Uploaded By</th><th>&nbsp;</th><th>Decision Letter</th><th>Detail</th><th>Updated By</th></tr></thead><tbody>';
	}
	if(GG_LognLocnFlag=="D")
	{
		htmlstr+='<table class="table table-bordered table-data"><thead><tr><th rowspan="2">SR.No.</th><th colspan="10">Business Proposal Detail</th><th colspan="3">Decision Taken</th></tr><tr><th>Zone</th><th>Division</th><th>Proposal Type</th><th>File Attached</th><th>Proposal Detail (Description)</th><th>Commodity</th><th>Stock Type</th><th>Remarks</th><th>Uploaded By</th><th>&nbsp;</th><th>Decision Letter</th><th>Detail</th><th>Updated By</th></tr></thead><tbody>';
	}
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_PROPOSAL_FILES',Zone:zone,Dvsn:dvsn,PropType:proptype2},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {

			var proptype=obj.DataList1[i].PropType;
			var propdtls=obj.DataList1[i].PropDtls;
			var proptypedesc=fetchCodeDesc('PR',proptype);
			var respuser='';
			if(GG_LognLocnFlag=="Z")
			{
				if(obj.DataList1[i].Dvsn=="")
				{
					if(obj.DataList1[i].RespUserId!="")
					{
						respuser=obj.DataList1[i].RespUserId+' ('+obj.DataList1[i].RespTime+')';
						htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" style="color:#b32400 !important;" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td><button class="btn btn-sm btn-secondary btn-icon" onclick="delFile(this,\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-times" aria-hidden="true"></i></button></td><td style="background:#b3e6b3;font-weight:600;text-align:center;"><a href="javascript:void(0)" class="file-link" onclick="downloadDecisionLetr(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].RespFile+'</a></td><td style="background:#b3e6b3;font-weight:600;text-align:center;">'+obj.DataList1[i].RespRmrk+'</td><td style="background:#b3e6b3;font-weight:600;text-align:center;">'+respuser+'</td></tr>';		
					}
					else
					{	
	                			htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" style="color:#b32400 !important;"  onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td><button class="btn btn-sm btn-secondary btn-icon" onclick="delFile(this,\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-times" aria-hidden="true"></i></button></td><td colspan="3">&nbsp;</td></tr>';
					}	
				}
			}
			if(GG_LognLocnFlag=="D")
			{
				if(obj.DataList1[i].Dvsn==GG_CrntDvsn)
				{
					if(obj.DataList1[i].RespUserId!="")
					{
						respuser=obj.DataList1[i].RespUserId+' ('+obj.DataList1[i].RespTime+')';
						htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" style="color:#b32400 !important;" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td><button class="btn btn-sm btn-secondary btn-icon" onclick="delFile(this,\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-times" aria-hidden="true"></i></button></td><td style="background:#b3e6b3;font-weight:600;text-align:center;"><a href="javascript:void(0)" class="file-link" onclick="downloadDecisionLetr(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].RespFile+'</a></td><td style="background:#b3e6b3;font-weight:600;text-align:center;">'+obj.DataList1[i].RespRmrk+'</td><td style="background:#b3e6b3;font-weight:600;text-align:center;">'+respuser+'</td></tr>';		
					}
					else
					{	
	                			htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" style="color:#b32400 !important;"  onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td><button class="btn btn-sm btn-secondary btn-icon" onclick="delFile(this,\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-times" aria-hidden="true"></i></button></td><td colspan="3">&nbsp;</td></tr>';
					}	
				}
			}
            }

            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);
	    $("#frmInpt").attr("enctype","multipart/form-data");
	}
	});

    }

    function downloadDecisionLetr(fileid) {
        $("#PropId").val(fileid);
        $("#frmGetDcsnLetr").submit();
    }

function fetchLOVs()
{
	var myurl="/RailSAHAY/AcqnPlanMIS";
		$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'FETCH_LOVS'},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {
				/*$('#PropType').append($('<option>', { 
        				value: '',
				        text : 'Select Proposal Type(s)' 
    				}));*/

			for(var i=0;i<obj.DataList1.length;i++)
                        {
				GG_LOV[i]=new Array(3);
				GG_LOV[i][0]=obj.DataList1[i].Ctgr;
				GG_LOV[i][1]=obj.DataList1[i].Code;
				GG_LOV[i][2]=obj.DataList1[i].Desc;
				if(GG_LOV[i][0]=="PR")
				{
					$('#PropTypeList').append($('<option>', { 
        					value: obj.DataList1[i].Code,
					        text : obj.DataList1[i].Desc 
    					}));
				}
			}
			
		jQuery('#PropTypeList').multiselect({
		    columns: 1,
		    placeholder: 'SELECT PROPOSAL TYPE',
		});
	$('input[type=checkbox]').click(function(){
        var valstr='';
        var cntr=0;
        $('input[type=checkbox]:checked').each(function() {
            if(cntr==0)
                valstr=this.value;
            else
                valstr+=","+this.value;
                
          cntr++;
        });
        $("#PropType").val(valstr);
        });

	            }
	            else
	            {
	                failedToast("Failed to Fetch List of Values"+obj.ErorMesg);
	            }
		}
		});
}
function updtPropType()
{
	$("#PropType").val($("#PropTypeList").val());
	return true;
}

function fetchCodeDesc(ctgr,code_str)
{
	if(code_str=="")
		return code_str;

	var codearr=[];
	var htmlstr='';
	if(code_str.indexOf(",")>0)
	{
		codearr=code_str.split(",");
	}
	else
	{
		codearr[0]=code_str;		
	}
	htmlstr+='<ul class="list-group list-group-flush">';
	for(var i=0;i<codearr.length;i++)
	{
		for(var j=0;j<GG_LOV.length;j++)
		{
			if((GG_LOV[j][0]==ctgr) && (GG_LOV[j][1]==codearr[i]))
			{
					htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;font-weight:600;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+GG_LOV[j][2]+'</li>';
			}
		}
	}
	htmlstr+='</ul>';
	return htmlstr;	
}
$(document).on("keydown", "form", function(event) { 
    return event.key != "Enter";
});
$(document).ready(function() {
  $(window).keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
  });
});