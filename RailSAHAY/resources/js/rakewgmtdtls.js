var GG_LOV=[];

//function fetchLOVs()
//{
//	var myurl="/RailSAHAY/FrgtPymtJson";
//		$.ajax({
//	     url : myurl,
//	     type : "post",
//            data: {Optn:'CUST_WGMT_DTLS',Sttn:sttn, RakeID:rakeid, WgmtID:wgmtid},
//	     //data: {Optn:'CUST_WGMT_DTLS'},
//	     async : true,
//	     success : function(data) {
//	            var obj= JSON.parse(data);
//	            var stts=obj.Stts;
////	            if(stts=="S")
////	            {
////			for(var i=0;i<obj.WgmtDtls.length;i++)
////                        {
////				GG_LOV[i]=new Array(3);
////				GG_LOV[i][0]=obj.DataList1[i].Ctgr;
////				GG_LOV[i][1]=obj.DataList1[i].Code;
////				GG_LOV[i][2]=obj.DataList1[i].Desc;
////			}
////	            }
////	            else
//                    if(stts=="F")
//	            {
//	                failedToast("Failed to Fetch List of Values"+obj.ErorMesg);
//	            }
//		}
//		});
//}
//$(document).ready(function(){
//    fetchLOVs();
//});
function refreshData() {
    var zone=$("#selZone").val();
    var cmdt=$("#selCmdt").val();    
    var measure=$("#selType").val();
    
    if((measure=="") || (measure==null)) {
        alert("Please select Progress Measure Type !");
        return false;
    }
    fetchPrgsMeasure(zone,cmdt,measure);
}
function fetchWgmtDetail(rakeid,wgmtid,sttn,datefrom,dateto,wgmttime,fnrnumb)
{
        //alert("fetchWgmtDetail");
	var myurl="/RailSAHAY/FrgtPymtJson";
        var str='';         

        $("#divMeasure").html('<div class="spinner-border text-danger"></div>');
                   
        var htmlap='<table id="example1" style="line-height:15px;text-align: center" class="display table table-striped table-bordered tabformat table-sm table-mis"><thead><tr>' +
                             '<th >SR.</th>'+
                             '<th  class="tmscolor">WAGON RLY</th>'+
                             '<th  class="tmscolor">WAGON TYPE</th>'+
                             '<th  class="tmscolor">WAGON NUMB</th>'+
                             '<th  class="tmscolor">L/E</th>'+
                             '<th  class="tmscolor">INVC NUMB</th>'+
                             '<th  class="tmscolor">INVC DATE</th>'+
                             '<th  class="tmscolor">STTN FROM</th>'+
                             '<th  class="tmscolor">STTN TO</th>'+
                             '<th  class="tmscolor">CMDT</th>'+
                             '<th style="white-space: nowrap;">WGMT START TIME</th>'+
                             '<th style="white-space: nowrap;">WGMT END TIME</th>'+
                             '<th >WGMT PUBLISH TIME</th>'+   
                             '<th >TARE (T)</th>'+   
                             '<th >ACTUAL WT. (T)</th>'+  
                             '<th >WB. GROSS WT. (T)</th>'+                               
                             '<th class="tmscolor" >RR GROSS WT.(T)</th>'+  
                             '<th class="tmscolor">RR ACTUAL WT. (T)</th>'+  
                             '<th class="tmscolor">WAGON TARE (T)</th>'+  
                             '<th class="tmscolor">REPORTING TIME</th>'+   
                             '<th class="tmscolor">RR NUMB</th>'+
                             '<th class="tmscolor">RR DATE</th>'+
                             '<th class="tmscolor">CHBL WT. (T)</th>'+  
                             '<th class="tmscolor">PERM WT. (T)</th>'+   
                             '<th class="tmscolor">OVER WT. TOTL (T)</th>'+  
                             '<th class="tmscolor">OVER WT. NORM (T)</th>'+                        
                             '</tr>'+
                            '</thead>';

	$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'CUST_WGMT_DTLS', Sttn:sttn, RakeID:rakeid, WgmtID:wgmtid, DateFrom:datefrom, DateTo:dateto},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
                var j=0;
		if(stts=="S")
                {
    
                    for(var i=0;i<obj.WgmtDtls.length;i++)
                    {j=i+1;
                        htmlap+='<tr><td>'+j+'</td>' +
                        '<td>'+obj.WgmtDtls[i].OwngRly+'</td>' +
                        '<td>'+obj.WgmtDtls[i].WgonType+'</td>' +
                        '<td>'+obj.WgmtDtls[i].WgonNumb+'</td>' +
                        '<td>'+obj.WgmtDtls[i].LEFlag+'</td>' +
                        '<td>'+obj.WgmtDtls[i].InvcNumb+'</td>' +
                        '<td style="white-space: nowrap;">'+obj.WgmtDtls[i].InvcDate+'</td>' +
                        '<td>'+obj.WgmtDtls[i].InvcSttnFrom+'</td>' +
                        '<td>'+obj.WgmtDtls[i].InvcSttnTo+'</td>' +
                        '<td>'+obj.WgmtDtls[i].Cmdt+'</td>' +
                        '<td style="white-space: nowrap;">'+obj.WgmtDtls[i].wgmtcmnctime+'</td>' +
                        '<td style="white-space: nowrap;">'+obj.WgmtDtls[i].wgmtcmpltime+'</td>' +
                        '<td style="white-space: nowrap;">'+obj.WgmtDtls[i].strtrxntime+'</td>' +
                        '<td>'+obj.WgmtDtls[i].strtare+'</td><td>'+obj.WgmtDtls[i].stractlwght+'</td><td>'+obj.WgmtDtls[i].strgroswght+'</td>' +
                        '<td>'+obj.WgmtDtls[i].strtmsgroswght+'</td><td>'+obj.WgmtDtls[i].strtmsactlwght+'</td><td>'+obj.WgmtDtls[i].strtmstare+'</td><td style="white-space: nowrap;">'+obj.WgmtDtls[i].strtmstrxntime+'</td><td>'+obj.WgmtDtls[i].strrrnumb+'</td>' +
                        '<td>'+obj.WgmtDtls[i].strrrdate+'</td><td>'+obj.WgmtDtls[i].strchblwght+'</td><td>'+obj.WgmtDtls[i].strpmblwght+'</td><td>'+obj.WgmtDtls[i].stroverwghttotl+'</td><td>'+obj.WgmtDtls[i].stroverwghtnorm+'</td></tr>';
                    }
                    htmlap+='</table>';
         
                    $("#divMeasure").html(htmlap);  
                    
                    //alert("aaa");
                    //$(".table-mis").dataTable();
                    //alert("bbb");
                    printTable("example1","CONSIGNMENT WEIGHMENT DETAIL","RAKE ID: "+rakeid+" | FNR NUMBER: "+fnrnumb+" | WEIGHMENT TIME: "+wgmttime+" | WEIGHMENT STATION: "+sttn);
                        //alert("ccc");
                    
		}
	}
	});
        //alert("exit fetchWgmtDetailb");
        
        //window.location.href="/RailSAHAY/pages/RakeWgmtDtls.jsp?Sttn="+sttn+"&RakeID="+rakeid+"&WgmtID+"+wgmtid;
        //$("#frmTest").submit();
        
        //onclick="window.location.href='http://www.domain.com/','_blank'";
        //window.open("/RailSAHAY/pages/RakeWgmtDtls.jsp")
        //alert("exit fetchWgmtDetaila");
}
function printTable(tableID, titleID, header)
{
   //alert(document.getElementById(tableID));
    //alert(document.getElementById(tableID).text);
    //alert(document.getElementById(tableID).innerHTML);
   //alert("inside printTable11#"+tableID);
   //alert($("#"+tableID).DataTable());
   $("#"+tableID).dataTable().fnDestroy();
   //alert("inside printTable22");
    $("#"+tableID).DataTable( {
        dom: 'Bfrtip',
        "paging":   false,
        "ordering": false,
        "info":     false,
        buttons: [
            //'print',
            //alert('mid');
            {
                 extend: 'print',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID
//                  action: function ( e, dt, node, config ) {
//                    alert( 'Button activated' );
//                    alert( 'e'+e );
//                    alert( 'dt'+dt );
//                    alert( 'node'+node );
//                    alert( 'dt'+dt );
//                }
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
                 pageSize: 'A3' ,
                 title: titleID ,
                 messageTop: header
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }            
            }
        ],
        orientation: 'landscape'
    } );
    
     //alert('exit');
}    


$(document).ready(function(){	

            //alert("ready");
    
//            $('#selCmdt').append($('<option>', {
//                                    value: '',
//                                    text: 'SELECT COMMODITY'
//                            }));
//            for(var i=0;i<aRakeCmdt.length;i++)
//            {
//                    var crntcmdt=aRakeCmdt[i].substring(0,aRakeCmdt[i].indexOf("-"));
//                            $('#selCmdt').append($('<option>', {
//                                    value: crntcmdt,
//                                    text: aRakeCmdt[i]
//                            }));
//            }
                /*
        let date = new Date();
        let month = `${date.getMonth() + 1}`.padStart(0, 2);
        let year = date.getFullYear();
        console.log("Year to be fetched:"+`${year}-${month}`);
        $('#txtMnth').val(`${year}-${month}`);
*/
 
});


function downloadProposalFile(fileid) 
{
        $("#FileId").val(fileid);
        $("#frmGetFile").submit();
}

function fetchCodeDesc(ctgr,code_str)
{
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
	return htmlstr;	
}

function fixDecimal(val)
{
	var newval=val;
	if(val=='')
	{
		return val;
	}
	else
	{
		if(val.indexOf('.')==0)
		{
			newval='0'+val;			
			return newval;
		}
		else
		{
			return val;
		}
	}
	return val;
}

function fetchNumCmdtName(data)
{
	var htmlstr='';
	if(data=='')
		return data;
 	

	if(data.indexOf(',')>0)
	{
		var numcmdtarr=data.split(',');
		htmlstr+='<ul class="list-group list-group-flush">';
		for(var i=0;i<numcmdtarr.length;i++)
		{
			for(var j=0;j<aNumCmdt.length;j++)
			{
				if(numcmdtarr[i]==aNumCmdt[j].substring(0,aNumCmdt[j].indexOf('-')))
				{
					htmlstr+='<li class="list-group-item" style="padding:0px;background:inherit;"><i class="fa fa-caret-right" aria-hidden="true"></i>&nbsp;'+aNumCmdt[j].substring(aNumCmdt[j].indexOf('-')+1)+'</li>';
					break;
				}
			}
		}
		htmlstr+='</ul>';
	}
	else
	{
		if (isNaN(data)) {
		    return data;
  		}
		for(var j=0;j<aNumCmdt.length;j++)
			{
				if(data==aNumCmdt[j].substring(0,aNumCmdt[j].indexOf('-')))
				{
					htmlstr+=aNumCmdt[j].substring(aNumCmdt[j].indexOf('-')+1);
				}
			}
	}
	return htmlstr;
}