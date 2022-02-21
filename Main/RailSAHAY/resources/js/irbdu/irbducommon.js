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

function refineValue(strVal,ctgr)
{	
	var strFinl=strVal;
	if(ctgr=="S") /*Station*/
	{
		if(strVal.indexOf("-")>0)
		{
			var strSttn1=strVal.substring(strVal.lastIndexOf("-")+1);
			var strSttn2=strSttn1.substring(0,strSttn1.indexOf("("));
			var strFinl=strSttn2.trim();		
		}
	}
	if(ctgr=="R") /*Rake Type*/
	{
		if(strVal.indexOf("-")>0)
		{
			var strRakeType=strVal.substring(0,strVal.indexOf("-"));
			var strFinl=strRakeType.trim();		
		}
	}
	if(ctgr=="C") /*Customer Code*/
	{
		if(strVal.indexOf("-")>0)
		{
			var strCustCode=strVal.substring(strVal.indexOf("-")+1);
			var strFinl=strCustCode.trim();		
		}
	}
	if(ctgr=="CM") /*Cmdt*/
	{
		if(strVal.indexOf("-")>0)
		{
			var strCmdt=strVal.substring(0,strVal.indexOf("-"));
			var strFinl=strCmdt.trim();		
		}
	}
	return strFinl;
}

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