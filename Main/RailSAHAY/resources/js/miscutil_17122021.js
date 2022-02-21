var fnrmapdata=[];
function fetchPrclBkngLocn()
{
	var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>CORRIDOR</th><th>ZONE</th><th>DIVISION</th><th>LOCATION</th></tr>';
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=PMS_BKNG";
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}

		for(var i=0;i<obj.SttnList.length;i++)
		{
			var cord=obj.SttnList[i].Corridor;
			var zone=obj.SttnList[i].Zone;
			var dvsn=obj.SttnList[i].Dvsn;
			var sttn=obj.SttnList[i].Sttn;
			htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left">'+cord+'</td><td class="left">'+zone+'</td><td class="left">'+dvsn+'</td><td class="left">'+sttn+'</td></tr>';

		}
		htmlstr+='</table>';
		$("#divPrclBkngLocn").html(htmlstr);
		}
		});
}
function trackConcern(tktid)
{
$("#divTrckCncr").html("");
var htmlstr='<div class="w3-container">';
var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=TRACK_CONCERN&CnrnId="+tktid;
$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
        // preserve newlines, etc - use valid JSON
            data = data.replace(/\\n/g, "\\n")  
               .replace(/\\'/g, "\\'")
               .replace(/\\"/g, '\\"')
               .replace(/\\&/g, "\\&")
               .replace(/\\r/g, "\\r")
               .replace(/\\t/g, "\\t")
               .replace(/\\b/g, "\\b")
               .replace(/\\f/g, "\\f");
// remove non-printable and other non-valid JSON chars
data = data.replace(/[\u0000-\u0019]+/g,""); 
	var obj= JSON.parse(data);
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
 if(( document.getElementById("divTrckCncr").className).includes('modal')){
           htmlstr+='<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" onclick="closeModal()" data-dismiss="modal">&times;</button></div>';
       }

	for(var i=0;i<obj.ConcernDtls.length;i++)
	{
		var typ=obj.ConcernDtls[i].Type;
		var ctgr=obj.ConcernDtls[i].Ctgr;
		var subctgr=obj.ConcernDtls[i].SubCtgr;
		var fname=obj.ConcernDtls[i].FName;
		var lname=obj.ConcernDtls[i].LName;
		var phone=obj.ConcernDtls[i].Phone;
		var email=obj.ConcernDtls[i].Email;
		var zone=obj.ConcernDtls[i].Zone;
		var dvsn=obj.ConcernDtls[i].Dvsn;
		var sttn=obj.ConcernDtls[i].Sttn;
		var rmrk=obj.ConcernDtls[i].Rmrk;
		var updttime=obj.ConcernDtls[i].UpdtTime;
		var cncrstts=obj.ConcernDtls[i].CncrStts;
		var sttsrmrk=obj.ConcernDtls[i].SttsRmrk;
		var filename=obj.ConcernDtls[i].FileName;
		var resptime=obj.ConcernDtls[i].RespTime;
		var respfile=obj.ConcernDtls[i].RespFile;
		var resptext=obj.ConcernDtls[i].RespText;

		htmlstr+='<div class="w3-card-5"><table class="styletable" style="background:#fff;">';
		htmlstr+='<tr><th colspan="2"><p class="sttnhead" style="color:#000;"><i class="fas fa-caret-right" ></i>&nbsp;'+tktid+' ('+ctgr+'|'+subctgr+'), Dated: '+updttime+'</p></th></tr>';
		htmlstr+='<tr><td class="lbl">Name</td><td class="val">'+fname+' '+lname+'</td></tr>';
		htmlstr+='<tr><td class="lbl">Phone</td><td class="val">'+phone+'</td></tr>';
		htmlstr+='<tr><td class="lbl">Email</td><td class="val">'+email+'</td></tr>';
		htmlstr+='<tr><td class="lbl">Station</td><td class="val">'+sttn+'</td></tr>';
		htmlstr+='<tr><td class="lbl">Concern</td><td class="val"><pre>'+rmrk+'</pre></td></tr>';
		if(cncrstts=='P')
			htmlstr+='<tr><td class="lbl">Status</td><td class="val sttspndg">'+sttsrmrk+'</td></tr>';
		if(cncrstts=='C')
			htmlstr+='<tr><td class="lbl">Status</td><td class="val sttscmpl">'+sttsrmrk+'</td></tr>';
		if(cncrstts=='O')
			htmlstr+='<tr><td class="lbl">Status</td><td class="val sttsfrwd">'+sttsrmrk+'</td></tr>';
                htmlstr+='<tr><td class="lbl">Attached File</td><td class="val"><a href="#" onclick="ajax_download(\''+filename+'\',\''+tktid+'\')">'+filename+'</a></td></tr>';

		if(cncrstts=='C')
		{
			
			htmlstr+='<tr><td class="lbl">Updated At</td><td class="val">'+resptime+'</td></tr>';
                        htmlstr+='<tr><td class="lbl">Attached File (Railway)</td><td class="val"><a href="#" onclick="ajax_download(\''+respfile+'\',\''+tktid+'\')">'+respfile+'</a></td></tr>';
                        htmlstr+='<tr><td class="lbl">Response(Railway)</td><td class="val"><pre>'+resptext+'</pre></td></tr>';
		}
		htmlstr+='</div>';

	}
	htmlstr+='</div>';
       if(( document.getElementById("divTrckCncr").className).includes('modal')){
           htmlstr+='</div></div>';
       }
      
	$("#divTrckCncr").html(htmlstr);
	}
	});
}
function closeModal(){
    $("#divTrckCncr").modal('hide');
}
 function trackFNR() {
        var fnrnumb =  $("#txtFnrNumb").val();
	if(isNaN(fnrnumb))
	{
		/*if((fnrnumb.substring(0,2)=="CR") || (fnrnumb.substring(0,2)=="SR")|| (fnrnumb.substring(0,2)=="IA")|| (fnrnumb.substring(0,2)=="PF")|| (fnrnumb.substring(0,2)=="WG"))
		{
			if(isNaN(fnrnumb.substring(2)))
				alert("Please Enter a Valid Concern Ticket Id/ FNR Number");
			else
				trackConcern(fnrnumb);
		}
		else
		{
			alert("Please Enter a Valid Concern Ticket Id/ FNR Number");
			return false;
		}*/
		//alert('Please Enter a Valid FNR Number, as printed on Railway Receipt');

		                        cuteToast({
		                              type: "success",
		                              message: "Please Enter a Valid FNR Number, as printed on Railway Receipt",
		                              timer: 5000
		                            })

	}
	else
	{
		if(fnrnumb.length==11)
                {
		 //       self.location="/RailSAHAY/pages/FNREnquiryOT.jsp?FNRNumb="+fnrnumb;

				document.getElementById("FnrEnquiry").submit();
                }
		else
		{

				                        cuteToast({
				                              type: "success",
				                              message: "Please Enter a Valid FNR Number, as printed on Railway Receipt",
				                              timer: 5000
		                            })
			//alert('Please Enter a Valid FNR Number, as printed on Railway Receipt');
		}
	}
		return false;
	}

function getFNRMapData(si_fnrnumb)
{
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=FNR_TRACK_MAP&FNR="+si_fnrnumb;
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		/*alert(data);*/
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.FNRRoute.length;i++)
		{
			fnrmapdata[i]=new Array(9);
			fnrmapdata[i][0]=obj.FNRRoute[i].Sttn;
			fnrmapdata[i][1]=obj.FNRRoute[i].ArvlTime;
			fnrmapdata[i][2]=obj.FNRRoute[i].DprtTime;
			fnrmapdata[i][3]=obj.FNRRoute[i].Ordr;
			fnrmapdata[i][4]=obj.FNRRoute[i].Lat;
			fnrmapdata[i][5]=obj.FNRRoute[i].Lng;
			fnrmapdata[i][6]=obj.FNRRoute[i].SttnName;
			fnrmapdata[i][7]=obj.FNRRoute[i].Dvsn;
			fnrmapdata[i][8]=obj.FNRRoute[i].Zone;
		}
		}
		});
}

function fetchNodlOfcrList(si_Zone, si_Dvsn)
{
	var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=NODL_OFCR_LIST&Zone="+si_Zone+"&Dvsn="+si_Dvsn;
	/*var htmlnodlofcr='<table class="table table-striped table-bordered tabformat" id="tblNodlOfcrList"><tr><th>ZONE</th><th>DIVISION</th><th>NODAL OFFICER</th><th>MOBILE NO.</th><th>OFFICE LANDLINE</th></tr>';*/
	var htmlnodlofcr='<table class="table table-striped table-bordered tabformat" id="tblNodlOfcrList"><tr><th>ZONE</th><th>DIVISION</th><th>NODAL OFFICER</th><th>CONTACT NUMBER</th></tr>';
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.NodlOfcr.length;i++)
		{
			var zone=obj.NodlOfcr[i].Zone;
			var dvsn=obj.NodlOfcr[i].Dvsn;
			var ofcr=obj.NodlOfcr[i].Ofcr;
			var mobl=obj.NodlOfcr[i].Mob;
			var ofcphn=obj.NodlOfcr[i].LL;
			/*htmlnodlofcr+='<tr><td class="left">'+zone+'</td><td class="left">'+dvsn+'</td><td class="left">'+ofcr+'</td><td class="right">'+mobl+'</td><td class="right">'+ofcphn+'</td></tr>';*/
			htmlnodlofcr+='<tr><td class="left">'+zone+'</td><td class="left">'+dvsn+'</td><td class="left">'+ofcr+'</td><td class="left">'+ofcphn+'</td></tr>';
		}
		htmlnodlofcr+='</table>';
		$("#divNodlOfcr").html(htmlnodlofcr);
		}
		});
}
function fetchSrchPageList(si_word)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divPageList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'SRCH_PAGE_LIST',Word:si_word},
	async : true,
	success : function(data) {
	var obj= JSON.parse(data);
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	if(obj.PageList.length==0)
	{
		noDataFound();
		$("#divPageList").html('');
		return false;
	}
	$("#spnPageListCont").html(obj.PageList.length);
	for(var i=0;i<obj.PageList.length;i++)
	{
		var titl=obj.PageList[i].PageTitle;
		var url=obj.PageList[i].Url;
		var mtchcont=obj.PageList[i].MatchCont;
		var wordlist=obj.PageList[i].WordList;
		var arrtags=wordlist.split('#');
		htmlstr+='<li class="list-group-item">';
		htmlstr+='<p class="head-list-item">'+titl+'&nbsp;&nbsp;<a href="'+url+'" class="card-link1">Visit Now..</a></p>';
		htmlstr+='<p class="light-desc">';

		for(var j=0;j<arrtags.length;j++)
			htmlstr+=arrtags[j];

		htmlstr+='</p></li>';
	}
	$("#ulSrchPageList").html(htmlstr);
	$(".match-cont").html(obj.PageList.length);

	}
});
}
function fetchCmdtFrgtClss(si_cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divPageList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'CMDT_FRGT_CLSS',Cmdt:si_cmdt},
	async : true,
	success : function(data) {
	console.log("Commodity Freight Data:"+data);
	var obj= JSON.parse(data);
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	var cmdt=obj.Cmdt;
	var gst=obj.GST;
	var wl=obj.WL;
	var tl=obj.TL;
	var rr=obj.RR;
	}
});
}
function fetchRateClssDtls(si_rateclss)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divPageList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    data: {Optn:'RATE_CLSS_DTLS',RateClss:si_rateclss},
	async : true,
	success : function(data) {
	console.log("Rate Class Data:"+data);
	var obj= JSON.parse(data);
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	if(obj.DistSlab.length==0)
	{
		noDataFound();
		return false;
	}
	for(var i=0;i<obj.DistSlab.length;i++)
	{
		var rateclss=obj.DistSlab[i].RateClss;
		var distfrom=obj.DistSlab[i].DistFrom;
		var distto=obj.DistSlab[i].DistTo;
		var rate=obj.DistSlab[i].Rate;
	}
    }
});
}

function getTrxIdHist()
{
    getPymtHist($("#txtTrxnId").val());
}

function showMRcpt(trxnid) 
{
    $("#TrxnId").val(trxnid);
    document.frmMR.submit();
}

function hideAltrows(rownumber,len)
{
    for(i=0;i<len;i++)
        $('#demo'+i).collapse("hide");
    $('#'+rownumber).collapse("show");
}


function getPymtHist(inptrxnid)
{
    GG_CrntView="PymtHist";
    var custcode='';
    var custOrgn =$("#custList").val();
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
	custcode=$("#custListSec").val();
    
    var myurl="/RailSAHAY/FrgtPymtJson";
    $("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").show();
        
        var htmlotsgpymt='<form id="frmMR" name="frmMR" method="post" action="\\RailSAHAY\\DownloadMRpdf" target = "_blank" ><input type=hidden id="TrxnId"    name="TrxnId" value=""/></form>';

	htmlotsgpymt+='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">Payment Transaction History</p>';
        htmlotsgpymt+='<p class="sec-cust-pymt-hedr"><i class="fas fa-circle" style="color:#c6ecc6;stroke: black;stroke-width: 14px;"></i>&nbsp; Transactions done by secondary customer</p>';
        htmlotsgpymt+='</div><div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control" placeholder="Transaction Id" maxlength="20" id="txtTrxnId" value="'+inptrxnid+'" style="width:180px;"><div class="input-group-append"><button class="btn btn-primary" onclick="getTrxIdHist();">Go</button></div></div></div></div>';
        

      	htmlotsgpymt+='<div class="row"><div class="col-lg-12 col-sm-12"><div class="table-responsive" style="min-height:100vh;max-height:100vh;overflow-y:auto;"><table class="table table-striped table-sm table-pymthstr" id="tblPymtHist"><thead><tr class="p-stts"><th  style="white-space: nowrap;width:20%;">TRANSACTION ID / DATE TIME</th><th style="width:20%;">STATION</th><th style="width:10%;">CUSTOMER</th><th style="width:20%;">CHARGE TYPE</th><th style="width:8%;">STATUS</th><th style="width:13%;">AMOUNT</th><th style="width:9%;"></th></tr></thead><tbody><tr></tr>';
//	var htmlotsgpymt='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">Payments Transaction History</p></div><div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control text-uppercase" list="frgtpymtlocnlist" placeholder="Station" id="txtFrgtPymtSttn" value="'+sttn+'" style="width:150px;"><datalist id="frgtpymtlocnlist"></datalist><div class="input-group-append"><button class="btn btn-primary" onclick="fetchSttnOtsgPymt();">Go</button></div></div></div></div>';
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'PYMT_TRXN_HIST',CustCode:custcode,TrxnId:inptrxnid},
		async : true,
		success : function(data) {

        		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
                        
			if(obj.PymtHist.length==0)
			{
                            htmlotsgpymt+='<tr><td colspan="8">NO DATA FOUND</td></tr>';
			}
			for(var i=0;i<obj.PymtHist.length;i++)
			{                                
				var sttn1=obj.PymtHist[i].Sttn;
				var sttnname=obj.PymtHist[i].SttnName;
				var custname=obj.PymtHist[i].ChrgId;
				//var custname=custcode.substring(custcode.indexOf("(")+1,custcode.indexOf(")")) ;
                                var custcode =   custname.substring(0,custname.indexOf(" "));
				var trxnid=obj.PymtHist[i].TrxnId;
				var trxntime=obj.PymtHist[i].TrxnTime;
				var invcnumb=obj.PymtHist[i].InvcNumb;
				var invcdate=obj.PymtHist[i].InvcDate;
				var chrgtype=obj.PymtHist[i].ChrgType;
				var seccust=obj.PymtHist[i].SttnFrom;
                                var custhead    =   "";
				var sttnfromname=obj.PymtHist[i].SttnFromName;
				var sttnto=obj.PymtHist[i].SttnTo;
				var sttntoname=obj.PymtHist[i].SttnToName;
				var pymtmode=obj.PymtHist[i].PymtMode;
				var trxnamnt=obj.PymtHist[i].TrxnAmnt;
				var trxnstts=obj.PymtHist[i].TrxnStts.toUpperCase();
                                var bankTrxnId=obj.PymtHist[i].BankTrxnId;	
                                var bankTrxnDate=obj.PymtHist[i].BankTrxnDate;
                                var acrlchrgtime    =   obj.PymtHist[i].AcrlTrxnTime;                                
                                var colr = "text-info";
                                var backcolor = "white";
                                if (trxnstts == "SUCCESS")
                                    colr   =   "trxn-success";
                                else
                                    colr   =   "trxn-fail";
                                if(seccust != "")
                                {
                                    custhead    =   "sec-cust-pymt";
                                    backcolor   =   "#fffae6";
                                }
                           htmlotsgpymt+='<tr class="'+custhead+'" style="line-height: 18px;" data-toggle="collapse p-od" data-target="#demo'+i+'" onclick="hideAltrows(\'demo'+i+'\',\''+obj.PymtHist.length+'\');">';
                           htmlotsgpymt+='<td class="left" style="white-space: nowrap;"><i class="fas fa-caret-down"></i>&nbsp;<b><a href="javascript:void(0)" class="'+colr+'">'+trxnid+' / '+trxntime+'</a></b></td><td class="left">'+sttn1+' ('+sttnname+')'+'</td><td class="left">'+custcode+'</td><td class="left" style="line-height:15px;">'+chrgtype+'</td><td class="left '+colr+'" style="font-weight:600;">'+trxnstts+'</td><td class="right" style="font-weight:600;"><i class="fas fa-rupee-sign"></i>'+trxnamnt+'</td>';
                            
                           if (trxnstts == "SUCCESS" && chrgtype != "FREIGHT COLLECTION")
                                htmlotsgpymt+='<td class="text-left" style="padding-left:0"><button class="btn btn-outline-success btn-sm" style="width:27px;height:24px;" title="Document password is user\'s Login ID" onclick="showMRcpt(\''+bankTrxnId+'\');"><i class="fas fa-download"></i></button></td></tr>';
                           else
                                htmlotsgpymt+='<td></td></tr>';
                            
			    
                            htmlotsgpymt+='<tr class="collapse in" id="demo'+i+'">';
                            htmlotsgpymt+='<td colspan="7"><div>';
			    
			    htmlotsgpymt+='<ul class="list-group list-group-flush"><li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr style="line-height: 15px;">';
                            if(invcnumb != "")
                                htmlotsgpymt+='<td nowrap><span class="lbl">Invoice:</span> <span class="val">'+invcnumb+' / '+invcdate+'</span></td>';
                            htmlotsgpymt+='<td nowrap><span class="lbl">Charges Raised At:</span> <span class="val">'+acrlchrgtime+'</span></td><td nowrap><span class="lbl">Customer:</span> <span class="val">'+custname+'</span></td></tr></table></li>';
			    htmlotsgpymt+='<li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr style="line-height: 15px;"><td nowrap><span class="lbl">Bank Transaction ID:</span> <span class="val">'+bankTrxnId+' / '+bankTrxnDate+'</span></td><td nowrap><span class="lbl">Payment Gateway:</span> <span class="val">'+pymtmode+'</span></td>';
                            if(seccust != "")
                                htmlotsgpymt+='<td nowrap><span class="lbl">Secondary Customer:</span> <span class="val">'+seccust+'</span></td>';
                            htmlotsgpymt+='</tr></table></li></ul></div></td></tr>';
                            
                          }
                        htmlotsgpymt+='</tbody></table></div></div></div>';
                        htmlotsgpymt+='<br/><div class="col-lg-12 col-sm-12"><p style="font-size:13px;padding:0px;margin: 0px;"><b>Note: To acces Money Receipt document, use the Login Id of the person who has made the payment(in lower case) as password.</b><p></div>';
        
			$("#divFrgtPymt").html(htmlotsgpymt);
		}
	});
}

function getRfndDtls(inptrxnid)
{
    GG_CrntView="RfndDtls";
    var custcode='';
    var custOrgn =$("#custList").val();
    var custOrgn1=$("#custList1").html();
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
	custcode=$("#custListSec").val(); 
                
    var myurl="/RailSAHAY/FrgtPymtJson";
    $("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").show();
        
        var htmlotsgpymt='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">Refund History</p></div><div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control" placeholder="Refund Id" maxlength="20" id="txtTrxnId" value="'+inptrxnid+'" style="width:180px;"><div class="input-group-append"><button class="btn btn-primary" onclick="getTrxIdHist();">Go</button></div></div></div></div>';

      	htmlotsgpymt+='<div class="row"><div class="col-lg-12 col-sm-12"><div class="table-responsive" style="min-height:100vh;max-height:100vh;overflow-y:auto;"><table class="table table-striped table-sm table-pymthstr"><thead><tr class="p-stts"><th  style="white-space: nowrap;width:10%;">REFUND ID/DATE TIME</th><th style="width:10%;">BANK REFUND ID</th><th style="width:20%;">CHARGE TYPE</th><th style="width:10%;">STATUS</th><th style="width:20%;">DESCRIPTION</th><th style="width:10%;">AMOUNT</th><th style="width:30%;">PAYMENT MODE</th></tr></thead><tbody><tr></tr>';
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'PYMT_RFND',CustCode:custcode,TrxnId:inptrxnid},
		async : true,
		success : function(data) {
        		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
                        
			if(obj.RfndDtls.length==0)
			{
                            htmlotsgpymt+='<tr><td colspan="8">NO DATA FOUND</td></tr>';
			}
			for(var i=0;i<obj.RfndDtls.length;i++)
			{          
				var RfndID=obj.RfndDtls[i].RfndID;
				var RfndTrxnTime=obj.RfndDtls[i].RfndTrxnTime;
				var BankRfndId=obj.RfndDtls[i].BankRfndId;
				var ChrgType=obj.RfndDtls[i].ChrgType;
				var BankStts=obj.RfndDtls[i].BankStts.toUpperCase();
                                var BankSttsDesc=obj.RfndDtls[i].BankSttsDesc.toUpperCase();	
				var BankAmnt=obj.RfndDtls[i].BankAmnt;
                                var BankPymtMode=obj.RfndDtls[i].BankPymtMode;    
                                var AppTrxnId    =   obj.RfndDtls[i].AppTrxnId;               
                                var AppTrxnTime    =   obj.RfndDtls[i].AppTrxnTime;  
                                var Sttn    =   obj.RfndDtls[i].Sttn;                     
                                var Cust    =   obj.RfndDtls[i].Cust;                        
                                var PymtGateway    =   obj.RfndDtls[i].PymtGateway;    
                                
                                var colr = "text-info";
                                if (BankStts == "SUCCESS")
                                    colr   =   "trxn-success";
                                else
                                    colr   =   "trxn-fail";
                         
                           htmlotsgpymt+='<tr data-toggle="collapse p-od" data-target="#demo'+i+'" onclick="hideAltrows(\'demo'+i+'\',\''+obj.RfndDtls.length+'\');">';
                           htmlotsgpymt+='<td class="left" style="white-space: nowrap;"><i class="fas fa-caret-down"></i>&nbsp;<b><a href="javascript:void(0)" class="'+colr+'">'+RfndID+'/'+RfndTrxnTime+'</a></b></td><td class="left">'+BankRfndId+'</td><td class="left">'+ChrgType+'</td><td class="left '+colr+'" style="font-weight:600;">'+BankStts+'</td><td class="left" style="font-weight:600;">'+BankSttsDesc+'</td><td class="right" style="font-weight:600;"><i class="fas fa-rupee-sign"></i>'+BankAmnt+'</td><td style="font-weight:600;">'+BankPymtMode+'</td></tr>';
                            			    
                            htmlotsgpymt+='<tr class="collapse in" id="demo'+i+'">';
                            htmlotsgpymt+='<td colspan="7"><div>';
		
			    htmlotsgpymt+='<ul class="list-group list-group-flush">';
                                                     
                            //if(BankRfndTrxnTime != "")
                            //    htmlotsgpymt+='<li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr><td><span class="lbl">Bank Refund Id:</span> <span class="val">'+BankRfndId+' Transaction Id:'+BankTrxnId+' Transaction Time:'+BankRfndTrxnTime+' Amount:'+BankAmnt+' Status:'+BankStts+' Status Description:'+BankSttsDesc+' Payment Mode:'+BankPymtMode+'</span></td></tr></table></li></ul>';
                            htmlotsgpymt+='<li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr><td><span class="lbl">Transaction Id/Time:</span><span class="val">'+AppTrxnId+' / '+AppTrxnTime+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="lbl">Station:</span><span class="val">'+Sttn+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="lbl">Customer:</span><span class="val">'+Cust+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="lbl">Payment Gateway:</span> <span class="val">'+PymtGateway+'</span></td></tr></table></ul>';
			    htmlotsgpymt+='</div></td></tr>';
                            
                          }
                        htmlotsgpymt+='</tbody></table></div></div></div>';
			$("#divFrgtPymt").html(htmlotsgpymt);
		}
	});
}
