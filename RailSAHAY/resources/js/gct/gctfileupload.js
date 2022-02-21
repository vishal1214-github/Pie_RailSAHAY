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
function fileUpload(rfrnid, indx) {
        $("#aFileUpload"+indx).html('<div class="spinner-border text-danger"></div>');
        $(".actn-link").attr("disabled","disabled");
        $("#FileIndx").val(indx);
        $("#frmFileUpload").submit();
    }
    function fetchFileList(rfrnid) {
        var myurl="/RailSAHAY/GCTQryJson";
        $.ajax({
        url : myurl,
        type : "post",
        data: {Optn:'FETCH_FILE_LIST', FileRfrnId:rfrnid},
        async : true,
        success : function(data) {
            console.log(data);
            var obj= JSON.parse(data);
            var stts=obj.Stts;
            if(stts=="S")
            {
                var planfile=obj.File0;
                if(planfile!='')
                {
                    $("#fileName0").html('<a href="javascript:void(0);" onclick="getFile(0);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+planfile+'</a>');
                    $("#aFileUpload0").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file1=obj.File1;
                if(file1!='')
                {
                    $("#fileName1").html('<a href="javascript:void(0);" onclick="getFile(1);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file1+'</a>');
                    $("#aFileUpload1").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file2=obj.File2;
                if(file2!='')
                {
                    $("#fileName2").html('<a href="javascript:void(0);" onclick="getFile(2);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file2+'</a>');
                    $("#aFileUpload2").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file3=obj.File3;
                if(file3!='')
                {
                    $("#fileName3").html('<a href="javascript:void(0);" onclick="getFile(3);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file3+'</a>');
                    $("#aFileUpload3").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file4=obj.File4;
                if(file4!='')
                {
                    $("#fileName4").html('<a href="javascript:void(0);" onclick="getFile(4);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file4+'</a>');
                    $("#aFileUpload4").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file5=obj.File5;
                if(file5!='')
                {
                    $("#fileName5").html('<a href="javascript:void(0);" onclick="getFile(5);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file5+'</a>');
                    $("#aFileUpload5").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file6=obj.File6;
                if(file6!='')
                {
                    $("#fileName6").html('<a href="javascript:void(0);" onclick="getFile(6);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file6+'</a>');
                    $("#aFileUpload6").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
                var file7=obj.File7;
                if(file17='')
                {
                    $("#fileName7").html('<a href="javascript:void(0);" onclick="getFile(7);" class="file-link"><i class="fas fa-check"></i>&nbsp;&nbsp;'+file7+'</a>');
                    $("#aFileUpload7").html('<i class="fas fa-file-upload"></i>&nbsp;&nbsp;Update');
                }
  var pending="N"; 
  var cntr=0;
  $(".table-upload tr").each(function () {
    $('td.file-name', this).each(function () {
        cntr++;
       if($(this).html()=="") {
           pending="Y";
       }
     });
});
        /*        var pending="";
                if($(".file-name").html()) {
                    if(pending!="Y")
                        pending="N";
                }
                else {
                    pending="Y";
                }
          */     
            if((cntr>0) && (GG_ApltCtgr!="CONCEPT"))
            {
                if(pending=="Y")
                {
                    window.parent.$("#filePendency").html("PENDING");
                    window.parent.$("#filePendency").css({"font-size":"600","color":"#b32400"});
                    window.parent.$("#DocStts").val("N");
                }
                else {
                    window.parent.$("#filePendency").html('<i class="fas fa-check"></i>&nbsp;&nbsp;COMPLETE');
                    window.parent.$("#filePendency").css({"font-size":"600","color":"#339933"});
                    window.parent.$("#DocStts").val("Y");
                }
            }
            if(GG_ApltCtgr=="CONCEPT")
            {
                if(pending=="Y")
                {
                    window.parent.$("#PlanStts").val("N");
                }
                else {
                    window.parent.$("#PlanStts").val("Y");
                }
            }
                
            }
            else
            {
                //failedToast("Failed to fetch Draft Applications Summary: "+obj.ErorMesg);
            }
        }
        });

    }
    function getFile(fileindx) {  
        $("#Indx").val(fileindx);
        $("#frmFileDownload").submit();
    }
    $(document).ready(function(){
       var ht=$(".table-upload").height();
       frmht=ht+50;
       if(GG_ApltCtgr=="CONCEPT")
        window.parent.$("#ifrmUploadPlan").css({"height":frmht+"px"});       
       else
        window.parent.$("#ifrmUpload").css({"height":frmht+"px"});       
    });