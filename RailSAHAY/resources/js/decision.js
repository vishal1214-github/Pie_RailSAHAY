
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
                if (file >= 4096) {
                    alert("File is too Big, please select a file less than 4MB");
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
      var htmlstr='<table class="table table-bordered table-data"><thead><tr><th>SR.No.</th><th>Zone</th><th>Proposal Type</th><th>File Uploaded</th><th>Remarks</th><th>Upload Time</th><th>User Id</th><th>&nbsp;</th></tr></thead><tbody>';
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_PROPOSAL_FILES',Zone:zone,PropType:proptype1},
     async : true,
     success : function(data) {
	console.log(data);
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {
					var proptype=obj.DataList1[i].PropType;
					if(obj.DataList1[i].PropType=="OTHER")
						proptype=obj.DataList1[i].PropType+ ' ('+obj.DataList1[i].OthrTypeDtls+')';
			if(GG_PropZoneFlag=="Y")
		                    htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptype+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UpldTime+'</td><td>'+obj.DataList1[i].UserId+'</td><td><button class="btn btn-sm btn-warning btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\');">Respond ></button></td></tr>';
			else
		                    htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td class="text-bold">'+proptype+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadProposalFile(\''+obj.DataList1[i].PropId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UpldTime+'</td><td>'+obj.DataList1[i].UserId+'</td><td><button class="btn btn-sm btn-warning btn-icon" onclick="respondProposal(this,\''+obj.DataList1[i].PropId+'\');">Respond ></button></td></tr>';
            }

            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);
            $(".table-data").dataTable();
		$("#frmInpt").attr("enctype","multipart/form-data");
	}
	});

    }
    function respondProposal(propid)
    {
		$("#respModal").modal('show');
	}