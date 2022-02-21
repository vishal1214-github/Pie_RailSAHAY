   var GG_LOV=[];
   fetchLOVs();
    function onFormSubmit()
    {
        var inptfile=$("#inptfile").val();
	/*
        if(inptfile=="") {
            alert("Please Upload the Decision Letter");
            return false;
        }
	*/
            $("#btnSubmit").attr('disabled','disabled');
            $("#divLdngImg").html('<div class="spinner-border text-danger"></div>');
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
    function downloadDecisionLetr(fileid) {
        $("#PropId").val(fileid);
        $("#frmGetDcsnLetr").submit();
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
      var zone=$("#selZone").val();
	var proptype1=$("#PropType").val();
	$("#frmInpt").attr("enctype","application/x-www-form-urlencoded");
      var myurl="/RailSAHAY/AcqnPlan";
      $("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
	var htmlstr='';
	if(GG_LognLocnFlag=="I")	
		htmlstr+='<table class="table table-bordered table-data"><thead><tr><th rowspan="2">SR.No.</th><th colspan="8">Business Proposal Detail</th><th colspan="4">Decision Taken</th></tr><tr><th>Zone</th><th>Proposal Type</th><th>File Attached</th><th>Proposal Detail (Description)</th><th>Commodity</th><th>Stock Type</th><th>Remarks</th><th>Uploaded By</th><th>Decision Letter</th><th>Subject/Remarks</th><th>Updated By</th><th>&nbsp;</th></tr></thead><tbody>';
	else
		htmlstr+='<table class="table table-bordered table-data"><thead><tr><th rowspan="2">SR.No.</th><th colspan="9">Business Proposal Detail</th><th colspan="4">Decision Taken</th></tr><tr><th>Zone</th><th>Division</th><th>Proposal Type</th><th>File Attached</th><th>Proposal Detail (Description)</th><th>Commodity</th><th>Stock Type</th><th>Remarks</th><th>Uploaded By</th><th>Decision Letter</th><th>Subject/Remarks</th><th>Updated By</th><th>&nbsp;</th></tr></thead><tbody>';

	if(GG_LognLocnFlag=="Z")
	{
		$('#selZone').change(function() {
        		$(this).data('lastSelected').attr('selected', true);
		});
	}
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_PROPOSAL_FILES',Zone:zone,Dvsn:'',PropType:proptype1},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {
			var proptype=obj.DataList1[i].PropType;
			var propdtls=obj.DataList1[i].PropDtls;
			var respuser='';
			var proptypedesc=fetchCodeDesc('PR',proptype);
			if(GG_LognLocnFlag=="I") 
			{
				if(obj.DataList1[i].Dvsn=="")
				{				
					if(obj.DataList1[i].RespUserId!="")
					{
						respuser=obj.DataList1[i].RespUserId+' ('+obj.DataList1[i].RespTime+')';
						htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td style="background:#b3e6b3;font-weight:600;text-align:center;"><a href="javascript:void(0)" class="file-link" onclick="downloadDecisionLetr(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].RespFile+'</a></td><td style="background:#b3e6b3;text-align:center;">'+obj.DataList1[i].RespRmrk+'</td><td style="background:#b3e6b3;text-align:center;">'+respuser+'</td><td class="text-center"><button class="btn btn-sm btn-light btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\',\''+obj.DataList1[i].Zone+'\',\''+proptype+'\');"><i class="fa fa-reply"></i></button></td></tr>';		
					}
					else	
					{	
			                	htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="text-center"><button class="btn btn-sm btn-light btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\',\''+obj.DataList1[i].Zone+'\',\''+proptype+'\');"><i class="fa fa-reply"></i></button></td></tr>';
					}
				}
			}
			else
			{
				if(obj.DataList1[i].Dvsn!="")
				{
					if(obj.DataList1[i].RespUserId!="")
					{
						respuser=obj.DataList1[i].RespUserId+' ('+obj.DataList1[i].RespTime+')';
						htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td style="background:#b3e6b3;font-weight:600;text-align:center;"><a href="javascript:void(0)" class="file-link" onclick="downloadDecisionLetr(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].RespFile+'</a></td><td style="background:#b3e6b3;text-align:center;">'+obj.DataList1[i].RespRmrk+'</td><td style="background:#b3e6b3;text-align:center;">'+respuser+'</td><td class="text-center"><button class="btn btn-sm btn-light btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\',\''+obj.DataList1[i].Zone+'\',\''+proptype+'\');"><i class="fa fa-reply"></i></button></td></tr>';		
					}
					else
					{
			                	htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="text-center"><button class="btn btn-sm btn-light btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\',\''+obj.DataList1[i].Zone+'\',\''+proptype+'\');"><i class="fa fa-reply"></i></button></td></tr>';
					}
				}
			}
             }

            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);
            $(".table-data").dataTable();
		$("#frmInpt").attr("enctype","multipart/form-data");
	}
	});

    }
function getAllProposals()
    {
    //alert("getAllProposals");
      var zone=$("#selZone").val();
	var proptype1=$("#PropType").val();
      var myurl="/RailSAHAY/AcqnPlan";
      $("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
	var htmlstr='<table id="example" class="table table-bordered table-mis"><thead><tr><th rowspan="2">SR.No.</th><th colspan="9">Business Proposal Detail</th><th colspan="4">Decision Taken</th></tr><tr><th>Zone</th><th>Division</th><th>Proposal Type</th><th>File Attached</th><th>Proposal Detail (Description)</th><th>Commodity</th><th>Stock Type</th><th>Remarks</th><th>Uploaded By</th><th>Decision Letter</th><th>Subject/Remarks</th><th>Updated By</th></tr></thead><tbody>';
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_PROPOSAL_FILES',Zone:zone,Dvsn:'',PropType:proptype1},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {
			var proptype=obj.DataList1[i].PropType;
			var propdtls=obj.DataList1[i].PropDtls;
			var proptypedesc=fetchCodeDesc('PR',proptype);
			var respuser='';
				if(obj.DataList1[i].RespUserId!="")
				{
					respuser=obj.DataList1[i].RespUserId+' ('+obj.DataList1[i].RespTime+')';
					htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" style="font-weight:600;color:#b32400 !important;" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td style="background:#b3e6b3;font-weight:600;text-align:center;"><a href="javascript:void(0)" class="file-link" onclick="downloadDecisionLetr(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].RespFile+'</a></td><td style="background:#b3e6b3;text-align:center;">'+obj.DataList1[i].RespRmrk+'</td><td style="background:#b3e6b3;text-align:center;">'+respuser+'</td></tr>';		
				}
				else
				{
	                		htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+obj.DataList1[i].Dvsn+'</td><td class="text-bold">'+proptypedesc+'</td><td><a href="javascript:void(0)" style="font-weight:600;color:#b32400 !important;"  class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp;'+obj.DataList1[i].FileName+'</a></td><td>'+propdtls+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].LoadType+'</td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UserId+' ('+obj.DataList1[i].UpdtTime+')</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
				}
            }

            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);

	    /*Code for print functionality*/
	    var printstr='';
	    if((zone!="") && (zone!=null))
		printstr+="Zone: "+zone;
	    if((proptype1!="") && (proptype1!=null))
		printstr+="  |  Proposal Type: "+fetchCodeDesc('PR',proptype1);
	    
            printTable("example","BUSINESS PROPOSALS", printstr);	    
	   /*Code for print functionality finished*/

            $(".table-mis").dataTable();
	}
	});

    }

    
    function respondProposal(link,propid, zone, proptype)
    {
    		$(link).closest('tr').addClass("selected-row");
		$("#respModal").modal('show');
		$("#PropId1").val(propid);
		$("#respFormTitl").html("Proposal: "+zone+" ("+proptype+")");
    }
    $(document).ready(function() {
	if(GG_LognLocnFlag=="Z")
	{
		$('#selZone').empty().append('<option selected="selected" value="'+GG_LognLocn+'">'+GG_LognLocn+'</option>');		
	}

	$('#respModal').on('hidden.bs.modal', function (e) {
  		$(".table-data tr").removeClass("selected-row");
	})
  });

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
				$('#PropType').append($('<option>', { 
        				value: '',
				        text : 'Proposal Type' 
    				}));

			for(var i=0;i<obj.DataList1.length;i++)
                        {
				GG_LOV[i]=new Array(3);
				GG_LOV[i][0]=obj.DataList1[i].Ctgr;
				GG_LOV[i][1]=obj.DataList1[i].Code;
				GG_LOV[i][2]=obj.DataList1[i].Desc;
				if(GG_LOV[i][0]=="PR")
				{
					$('#PropType').append($('<option>', { 
        					value: obj.DataList1[i].Code,
					        text : obj.DataList1[i].Desc 
    					}));
				}

			}
				$('#PropType').append($('<option>', { 
        				value: '',
				        text : 'ALL' 
    				}));
			getProposalList();
	            }
	            else
	            {
	                failedToast("Failed to Fetch List of Values"+obj.ErorMesg);
	            }
		}
		});
}

function fetchCodeDesc(ctgr,code_str)
{
	console.log("Called fetchCodeDesc:"+ctgr+"    "+code_str);
	if(code_str=="")
		return code_str;

	var codearr=[];
	var htmlstr='';
	var htmlstrt='';
	var htmlend='';
	if(code_str.indexOf(",")>0)
	{
		codearr=code_str.split(",");
	}
	else
	{
		codearr[0]=code_str;		
	}

	if(codearr.length>1)
	{
		htmlstr+='<ul class="list-group list-group-flush">';
		for(var i=0;i<codearr.length;i++)
		{
			for(var j=0;j<GG_LOV.length;j++)
			{
				if((GG_LOV[j][0]==ctgr) && (GG_LOV[j][1]==codearr[i]))
				{
					if(i==0)
						htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+GG_LOV[j][2]+'</li>'
					else
						htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+GG_LOV[j][2]+'</li>'
				}
			}
		}
		htmlstr+='</ul>';
	}	
	else
	{		
		for(var i=0;i<codearr.length;i++)
		{
			for(var j=0;j<GG_LOV.length;j++)
			{
				if((GG_LOV[j][0]==ctgr) && (GG_LOV[j][1]==codearr[i]))
				{
					htmlstr+=GG_LOV[j][2];
				}
			}
		}
	}
	console.log("Returning codedesc:"+htmlstr);
	return htmlstr;	
}
