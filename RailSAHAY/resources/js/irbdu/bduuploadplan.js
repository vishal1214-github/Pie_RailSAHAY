
    function onFormSubmit()
    {   
        var inptfile=$("#inptfile").val();
        if(inptfile=="") {
            alert("Please Upload the Business Development and Acquistion Plan File");
            return false;
        }
            $("#btnSubmit").attr('disabled','disabled');
            $("#divLdngImg").html('<div class="spinner-border text-danger"></div>');
            return true;
    }
    function checkKeyEntry(e) {
         var k = e.keyCode;
         return ((k > 64 && k < 94) ||  (k > 96 && k < 123)|| (k > 39 && k < 47) || (k == 8)|| (k == 16)|| (k == 20) || (k == 219)|| (k == 221)||(k==32)|| (k==13)|| (k==61)  || (k >= 48 && k <= 57));
    }
    function downloadFile(fileid) {
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
    function delFile(delitem, fileid) {
    $(delitem).closest('tr').addClass("selected-row");
    if (confirm('The selected plan will get deleted! You want to continue')) {
     var myurl="/RailSAHAY/AcqnPlan";
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'DELETE_FILE',FileId:fileid},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="SUCCESS")
            {
               successToast("Plan Deleted Successfully !"); 
               getPlanFileList(GG_SelZone);
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
    function getPlanFileList(zone) 
    { 
      GG_SelZone=zone;
      var myurl="/RailSAHAY/AcqnPlan";
      $("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
      var htmlstr='<table class="table table-bordered table-data"><thead><tr><th rowspan="2">SR.No.</th><th rowspan="2">Zone</th><th rowspan="2">File</th><th rowspan="2">Remarks</th><th colspan="2">Upload Detail</th><th rowspan="2">&nbsp;</th></tr><tr><th>Time</th><th>User</th></tr></thead><tbody>';
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_UPLOADED_FILES',Zone:zone,Date:''},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {	
		   if(GG_LognZone==obj.DataList1[i].Zone)
                    htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadFile(\''+obj.DataList1[i].FileId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UpldTime+'</td><td>'+obj.DataList1[i].UserId+'</td><td><button class="btn btn-sm btn-secondary btn-icon" onclick="delFile(this,\''+obj.DataList1[i].FileId+'\');"><i class="fa fa-times" aria-hidden="true"></i></button></td></tr>';
		   else
                    htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadFile(\''+obj.DataList1[i].FileId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UpldTime+'</td><td>'+obj.DataList1[i].UserId+'</td><td>&nbsp;</td></tr>';
            }
            
            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);
            $(".table-data").dataTable();
	}
	});

    }
    function getPlanFileList1() 
    { 
      //alert("getPlanFileList1");
      GG_SelZone='';
      var myurl="/RailSAHAY/AcqnPlan";
      $("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
      var htmlstr='<table id="example" class="table table-bordered table-data table-mis"><thead><tr><th rowspan="2">SR.No.</th><th rowspan="2">Zone</th><th rowspan="2">File</th><th rowspan="2">Remarks</th><th colspan="2">Upload Detail</th></tr><tr><th>Time</th><th>User</th></tr></thead><tbody>';
     $.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'LIST_UPLOADED_FILES',Zone:'',Date:''},
     async : true,
     success : function(data) {
            var obj= JSON.parse(data);
            for(var i=0;i<obj.DataList1.length;i++)
            {	
                    htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td class="text-bold">'+obj.DataList1[i].Zone+'</td><td><a href="javascript:void(0)" class="file-link" onclick="downloadFile(\''+obj.DataList1[i].FileId+'\');">'+obj.DataList1[i].FileName+'</a></td><td>'+obj.DataList1[i].Rmrk+'</td><td>'+obj.DataList1[i].UpldTime+'</td><td>'+obj.DataList1[i].UserId+'</td></tr>';
            }
            
            htmlstr+='</tbody></table>';
            $("#dtlsGrid").html(htmlstr);
            printTable("example", "DEVELOPMENT PLANS","");
            $(".table-mis").dataTable();
	}
	});

    }
    
