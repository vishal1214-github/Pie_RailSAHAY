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
var validExtensions = ["pdf"];
var file = $("#inptfile").val().split('.').pop();
if (validExtensions.indexOf(file) == -1) {
    alert("File Formats Allowed : "+validExtensions.join(', '));
    $("#inptfile").val('');
    return false;
}
}
 function downloadTrfcPlan(rowid) {
	$("#RowId").val(rowid);
	$("#frmTrfcPlan").submit();
}