var GG_GstAmnt="";
var GG_ChrgId=[];
var GG_CrntView="PendPymt";
$(document).ready(function(){
	fetchOtsgChrgSmry();
});

function fetchOtsgPymt()
{
    GG_CrntView="PendPymt";
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;
	var sttn='';
	var datefrom='';
	var dateto='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").show();
        $("#divScndCustNew").hide();
       // $("#divAdMfScndCust").hide();

	var htmlotsgpymt='';
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'OTSG_PYMT_LIST',CustCode:custcode,Sttn:sttn,DateFrom:datefrom,DateTo:dateto},
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
			for(var i=0;i<obj.OtsgPymt.length;i++)
			{
				var fnotnumb=obj.OtsgPymt[i].FNotNumb;
				var fnotid=obj.OtsgPymt[i].FNotId;
				var fnotdate=obj.OtsgPymt[i].FNotDate;
				var invcnumb=obj.OtsgPymt[i].InvcNumb;
				var invcdate=obj.OtsgPymt[i].InvcDate;
				var sttnfrom=obj.OtsgPymt[i].SttnFrom;
				var sttnto=obj.OtsgPymt[i].SttnTo;
				var cust=obj.OtsgPymt[i].Cust;
				var wrfg=obj.OtsgPymt[i].Wrfg;
				var frgtotsg=obj.OtsgPymt[i].FrgtOtsg;
				var dmrg=obj.OtsgPymt[i].Dmrg;
				var loclchrg=obj.OtsgPymt[i].LoclChrg;
				var invcid=obj.OtsgPymt[i].InvcId;
				var iwowflag=obj.OtsgPymt[i].IWOWFlag;
				var perdfrom=obj.OtsgPymt[i].PerdFrom;
				var perdto=obj.OtsgPymt[i].PerdTo;
				var chrgcode=obj.OtsgPymt[i].ChrgCode;
				var paidtype=obj.OtsgPymt[i].PaidType;

				htmlotsgpymt+='<div class="fnot-card"><div class="row">';
				htmlotsgpymt+='<div class="col-lg-4 col-sm-12"><span class="fnot-lbl">Forwarding Note No.</span><span class="fnot-val">'+fnotnumb+'</span><span class="fnot-lbl">Date</span><span class="fnot-val">'+fnotdate+'</span></div>';
				htmlotsgpymt+='<div class="col-lg-4 col-sm-12"><span class="fnot-lbl">Invoice No.</span><span class="fnot-val">'+invcnumb+'</span><span class="fnot-lbl">Date</span><span class="fnot-val">'+invcdate+'</span></div>';
				htmlotsgpymt+='<div class="col-lg-4 col-sm-12"><span class="fnot-lbl">From</span><span class="fnot-val">'+sttnfrom+'</span><span class="fnot-lbl">To</span><span class="fnot-val">'+sttnto+'</span></div>';
				htmlotsgpymt+='</div><p class="chrg-head">Pending Charges</p>';
				htmlotsgpymt+='<table class="table table-striped table-charges"><thead><tr><th>Charge Type</th><th>Amount (Rs.)</th><th>&nbsp;</th></tr></thead><tbody>';
				if((wrfg!="") && (wrfg!="0"))
					htmlotsgpymt+='<tr><td>Wharfage</td><td><td><a href="javascript:void(0)" onclick="showBrkUp(this, \'W\',\''+fnotid+'\',\''+invcid+'\');" class="link-brkup">'+wrfg+'</a></td><td><button class="btn btn-sm btn-warning"><i class="fas fa-rupee-sign"></i>&nbsp;Pay Now</button></td></tr>';
				if((frgtotsg!="") && (frgtotsg!="0"))
					htmlotsgpymt+='<tr><td>Freight Outstanding</td><td><td><a href="javascript:void(0)" onclick="showBrkUp(\'FO\',\''+fnotid+'\',\''+invcid+'\');" class="link-brkup">'+frgtotsg+'</a></td><td><button class="btn btn-sm btn-warning"><i class="fas fa-rupee-sign"></i>&nbsp;Pay Now</button></td></tr>';
				if((dmrg!="") && (dmrg!="0"))
					htmlotsgpymt+='<tr><td>Demurrage</td><td><td><a href="javascript:void(0)" onclick="showBrkUp(\'D\',\''+fnotid+'\',\''+invcid+'\');" class="link-brkup">'+dmrg+'</a></td><td><button class="btn btn-sm btn-warning"><i class="fas fa-rupee-sign"></i>&nbsp;Pay Now</button></td></tr>';
				if((loclchrg!="") && (loclchrg!="0"))
					htmlotsgpymt+='<tr><td>Local Charges</td><td><td><a href="javascript:void(0)" onclick="showBrkUp(\'LC\',\''+fnotid+'\',\''+invcid+'\');" class="link-brkup">'+loclchrg+'</a></td><td><button class="btn btn-sm btn-warning"><i class="fas fa-rupee-sign"></i>&nbsp;Pay Now</button></td></tr>';

				htmlotsgpymt+='</table></div>';
			}
			$("#divFrgtPymt").html(htmlotsgpymt);
		}
	});

}

function fetchOtsgChrgSmry()
{
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;
	var myurl="/RailSAHAY/FrgtPymtJson";

	$.ajax({
		url : myurl,
		type : "post",
        	data: {Optn:'OTSG_CHRG_SMRY',CustCode:custcode},
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
			$("#pymtOtsg").html('<strong>'+obj.OtsgChrgSmry+'</strong>');
		}
	});

}

function fetchOtsgChrg(sttn)
{
    GG_CrntView="PendPymt";
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;
	var datefrom='';
	var dateto='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").show();        
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
       // $("#divAdMfScndCust").hide();
        
	var htmlotsgpymt='<div class="row"><div class="col-lg-6 col-sm-12"><p class="ctgr-pymt-title">Pending Payments<br/><span class="span-note">*Charges shown below do not include Taxes/Adjustments</span> </p></div><div class="col-lg-6 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control text-uppercase" list="frgtpymtlocnlist" placeholder="Station" id="txtFrgtPymtSttn" value="'+sttn+'" style="width:150px;"><datalist id="frgtpymtlocnlist"></datalist><div class="input-group-append"><button class="btn btn-primary" onclick="fetchSttnOtsgPymt();">Go</button></div></div></div></div>';

	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'OTSG_CHRG',CustCode:custcode,Sttn:sttn},
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

			var prevsttn="";
			var prevfnotid="";
			var prevcustcode="";
			var cntr=0;
			for(var i=0;i<obj.OtsgChrg.length;i++)
			{
				var chrgid=obj.OtsgChrg[i].ChrgId;
				GG_ChrgId[i]=chrgid;
				var zone=obj.OtsgChrg[i].Zone;
				var dvsn=obj.OtsgChrg[i].Dvsn;
				var sttn=obj.OtsgChrg[i].Sttn;
				var sttnname=obj.OtsgChrg[i].SttnName;
				var chrgtype=obj.OtsgChrg[i].ChrgType;
				var fnotid=obj.OtsgChrg[i].FNOTId;
				var invcid=obj.OtsgChrg[i].InvcId;
				var amnt=obj.OtsgChrg[i].Amnt;
				var fnotnumb=obj.OtsgChrg[i].FNOTNumb;
				var fnotdate=obj.OtsgChrg[i].FNOTDate;
				var sttnfrom=obj.OtsgChrg[i].SttnFrom;
				var sttnto=obj.OtsgChrg[i].SttnTo;
				var cmdt=obj.OtsgChrg[i].Cmdt;
				var invcnumb=obj.OtsgChrg[i].InvcNumb;
				var invcdate=obj.OtsgChrg[i].InvcDate;
				var invcfrom=obj.OtsgChrg[i].InvcFrom;
				var totlamnt=obj.OtsgChrg[i].TotlAmnt;
				var pctg=Number(obj.OtsgChrg[i].Pctg);
				var iwowflag=obj.OtsgChrg[i].IWOWFlag;
				var chrgtypecode=obj.OtsgChrg[i].ChrgTypeCode;
				var zonecode=obj.OtsgChrg[i].ZoneCode;				
				var cnfmdate=obj.OtsgChrg[i].CnfmDate;				
				var custcode=obj.OtsgChrg[i].CustCode;			
				var custname=obj.OtsgChrg[i].CustName;				
				var trxntime=obj.OtsgChrg[i].TrxnTime;			
				var trxnstts=obj.OtsgChrg[i].TrxnStts;		
				var trxnstts=obj.OtsgChrg[i].TrxnStts;	
				var custdmrgid=obj.OtsgChrg[i].CustDmrgId;
				var trxnid=obj.OtsgChrg[i].TrxnId;
				var acrldate=obj.OtsgChrg[i].AcrlDate;

				console.log(chrgid+"   "+trxnid);
				var chrgtype1='';
				if(chrgtypecode=='05')
					chrgtype1="L";
				if(chrgtype=='FREIGHT CHARGES')
					chrgtype1="F";
				if(chrgtype=='DEMURRAGE CHARGES')
					chrgtype1="D";
				if(chrgtype=='WHARFAGE CHARGES')
					chrgtype1="W";

				var btnClass="btn-success";
				var btnText="Pay Now";
	

				if(trxnstts=="PENDING")
				{
					btnClass="btn-danger";
					btnText="Payment In Process"
				}

				//fetchDmrgBrkUp(sttn,invcid,custdmrgid),

				
				if((sttn==prevsttn) && (fnotid==prevfnotid) && (custcode==prevcustcode))
				{
					if(chrgtype=="FREIGHT CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchPopOverChrgBrkup(this, \''+sttn+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="DEMURRAGE CHARGES")						
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{	
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchLoclChrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+custcode+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/
						
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")	
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchWrfgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/
						
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}

				}
				else
				{
					if(cntr>0)
					{
						htmlotsgpymt+='</table></div>';
					}

					if((fnotid=="") && (invcid==""))
						htmlotsgpymt+='<div class="sttn-card"><div class="fnot-card-head"><span class="sttn-chrg">'+sttnname+' ('+sttn+')</span><span class="dvsn-chrg float-right">'+dvsn+'/'+zone+'</div><hr/>';
					else
						htmlotsgpymt+='<div class="fnot-card"><div class="fnot-card-head"><span class="sttn-chrg">'+sttnname+' ('+sttn+')</span><span class="dvsn-chrg float-right">'+dvsn+'/'+zone+'</div><hr/>';


					if(fnotnumb!="") 
					{
						htmlotsgpymt+='<div class="row"><div class="col-lg-8 col-sm-12"><span class="fnot-lbl">F/Note</span><span class="fnot-val">'+fnotnumb+' / '+fnotdate+'</span>&nbsp;&nbsp;<span class="fnot-od">[<i class="fas fa-circle text-success"></i>&nbsp;'+sttnfrom+'&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;<i class="fas fa-circle text-danger"></i>&nbsp;'+sttnto+']</div>';
						if(invcid!="")
							htmlotsgpymt+='<div class="col-lg-4 col-sm-12"><span class="fnot-lbl">Invoice</span><span class="fnot-val">'+invcnumb+' / '+invcdate+'</span></div>';

						htmlotsgpymt+='</div>';
					}
					
					if((fnotid=="") && (invcid==""))
						htmlotsgpymt+='<p class="chrg-head">Pending Charges for Station (Total: <i class="fas fa-rupee-sign"></i>&nbsp;'+totlamnt+')<span class="badge badge-light float-right cust-dtls">'+custcode+' ('+custname+')</span></p>';
					else
						htmlotsgpymt+='<p class="chrg-head">Pending Charges (Total: <i class="fas fa-rupee-sign"></i>&nbsp;'+totlamnt+')<span class="badge badge-light float-right cust-dtls">'+custcode+' ('+custname+')</span></p>';

					htmlotsgpymt+='<table class="table table-charges" style="width:100%;"><tbody>';

					if(chrgtype=="FREIGHT CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchPopOverChrgBrkup(this, \''+sttn+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="DEMURRAGE CHARGES")						
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{	
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchLoclChrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+custcode+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/
						
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")	
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchWrfgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/
						htmlotsgpymt+='<tr><td style="width:55%;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
				}
				cntr++;
				prevsttn=sttn;
				prevfnotid=fnotid;
				prevcustcode=custcode;
				
			}
			if(cntr>0)
			{
				htmlotsgpymt+='</table></div>';
			}

			$("#divFrgtPymt").html(htmlotsgpymt);

			 $(document).on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });
			 $("a").on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });

			
			var str='';
		        for (var i=0; i < aSttn.length;++i){
            			str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
	               }
            	      var my_list=document.getElementById("frgtpymtlocnlist");
	              my_list.innerHTML = str;

		}
	});

}
function fetchSttnOtsgPymt()
{
	var sttn='';
	var sttndesc=$("#txtFrgtPymtSttn").val();
	if(sttndesc.indexOf("-")>-1)
	{
		var sttn=sttndesc.substring(sttndesc.lastIndexOf("-")+1);
		sttn=(sttn.substring(0,sttn.lastIndexOf("("))).trim();
	}	
	else
	{
		sttn=sttndesc.toUpperCase();
	}
	fetchOtsgChrg(sttn);
}
function showBrkUp(link,sttn,chrgtype, fnotid, invcid, iwowflag)
{
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;

	var datefrom='';
	var dateto='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divChrgBrkup").html('<div class="spinner-border text-danger"></div>');

	var htmchrgbrkup='<table class="table table-striped table-bordered table-responsive" style="margin-bottom:0px;"><tbody>';

	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BRKUP',CustCode:custcode,Sttn:sttn,InvcId:invcid,FNOTId:fnotid,ChrgType:chrgtype,IWOWFlag:iwowflag},
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
			var cntr=0;
			for(var i=0;i<obj.Brkup.length;i++)
			{
				var chrgcode=obj.Brkup[i].ChrgCode;
				var chrgname=obj.Brkup[i].ChrgName;
				var amnt=obj.Brkup[i].Amnt;
				htmchrgbrkup+='<tr><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt">'+amnt+'</td></tr>';
			}
			htmchrgbrkup+='</tbody></table><a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';

   		       $('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Break-up of Charges',
        			content: htmchrgbrkup,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

		}
	});	
}
function closeChrgBrkUp()
{
	$('.link-brkup').popover('hide');
}
function payNow(custcode,zonecode,chrgtypecode,chrgid,amnt,cnfmdate)
{	
	/*
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;
	*/

	$("#txtChrgZone").val(zonecode);
	$("#txtChrgType").val(chrgtypecode);
	$("#txtChrgId").val(chrgid);
	$("#txtAmnt").val(amnt);
	$("#txtCustCode").val(custcode);
	$("#txtCnfmDate").val(cnfmdate);
	fbdPymtWindow=window.open(null,'frgtpymttrxn'); 
	$("#frmFrgtPymt").submit();
}
function makePayment(custcode,sttn,invcid,fnotid,custdmrgid,trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,chrgzone,cnfmdate,chrgtypedesc)
{	
	$("#Sttn").val(sttn);
	$("#ChrgType").val(chrgtype);
	$("#ChrgTypeCode").val(chrgtypecode);
	$("#ChrgTypeDesc").val(chrgtypedesc);
	$("#InvcId").val(invcid);
	$("#IWOWFlag").val(iwowflag);
	$("#InvcDate").val(invcdate);
	$("#Amnt").val(amnt);
	$("#ChrgId").val(chrgid);
	$("#ChrgZone").val(chrgzone);
	$("#CnfmDate").val(cnfmdate);
	$("#FNOTId").val(fnotid);
	$("#CustDmrgId").val(custdmrgid);
	$("#TrxnId").val(trxnid);
	$("#RlyGstIn").val("");
	$("#CustGstIn").val("");

	/*
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;
	*/

	$("#CustCode").val(custcode);
	$("#frmFrgtPymtInit").submit();
}
function fetchChrgDesc(chrgid)
{
	var custcode='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divChrgDesc").html('<div class="spinner-border text-danger"></div>');

	var htmchrgdesc='<div class="container"><div class="chrg-desc-card"><table class="table table-bordered table-chrgdesc" style="margin-bottom:0px;"><tbody>';


	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_DESC',CustCode:custcode,ChrgId:chrgid},
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

			var sttn=obj.Sttn;
			var sttnname=obj.SttnName;
			var dvsn=obj.Dvsn;
			var chrgtype=obj.ChrgType;
			var iwowflag=obj.IWOWFlag;
			var cust=obj.Cust;
			var amnt=obj.Amnt;
			var fnot=obj.FNOT;
			var fnotfrom=obj.FNOTFrom;
			var fnotdvsnfrom=obj.FNOTDvsnFrom;
			var fnotto=obj.FNOTTo;
			var fnotdvsnto=obj.FNOTDvsnTo;
			var invc=obj.Invc;
			var invcfrom=obj.InvcFrom;
			var invcdvsnfrom=obj.InvcDvsnFrom;
			var invcto=obj.InvcTo;
			var invcdvsnto=obj.InvcDvsnTo;
			var fnr=obj.FNR;
			var rrnumb=obj.RRNumb;
			var rrdate=obj.RRDate;
			var cnsr=obj.Cnsr;
			var cnsg=obj.Cnsg;
			var cmdt=obj.Cmdt;
			var ldunts=obj.LdUnts;
			var emunts=obj.EmUnts;

			htmchrgdesc+='<tr><td class="lbl">Customer</td><td class="val">'+cust+'</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Charge</td><td class="val">'+chrgtype+' (Amount: <i class="fas fa-rupee-sign"></i> '+amnt+')</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Station</td><td class="val">'+sttn+' ('+sttnname+')</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+dvsn+'</td></tr>';
			if(fnot!="/")
			{
				htmchrgdesc+='<tr><td class="head-desc" colspan="2">Consignment Detail</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">RR Detail</td><td class="val">'+rrnumb+' ('+rrdate+')</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">FNR</td><td class="val">'+fnr+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Consignor/Consignee</td><td class="val">'+cnsr+'/'+cnsg+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Commodity</td><td class="val">'+cmdt+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Units (Loaded/Empty)</td><td class="val">'+ldunts+'/'+emunts+'</td></tr>';

				htmchrgdesc+='<tr><td class="head-desc" colspan="2">F/Note Detail</td></tr>';				
				htmchrgdesc+='<tr><td class="lbl">Source</td><td class="val">'+fnotfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+fnotdvsnfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Destination</td><td class="val">'+fnotto+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+fnotdvsnto+'</td></tr>';

				htmchrgdesc+='<tr><td class="head-desc" colspan="2">Invoice Detail</td></tr>';				
				htmchrgdesc+='<tr><td class="lbl">Source</td><td class="val">'+invcfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+invcdvsnfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Destination</td><td class="val">'+invcto+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+invcdvsnto+'</td></tr>';

			}

			htmchrgdesc+='</tbody></table></div></div>';
			$("#divChrgDesc").html(htmchrgdesc);

		}
	});	
}
function fetchChrgDescFailed(chrgid)
{
	var custcode='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divChrgDesc").html('<div class="spinner-border text-danger"></div>');

	var htmchrgdesc='<div class="container"><div class="chrg-desc-card"><table class="table table-bordered table-chrgdesc" style="margin-bottom:0px;"><tbody>';


	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_DESC',CustCode:custcode,ChrgId:chrgid},
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

			var sttn=obj.Sttn;
			var sttnname=obj.SttnName;
			var dvsn=obj.Dvsn;
			var chrgtype=obj.ChrgType;
			var iwowflag=obj.IWOWFlag;
			var cust=obj.Cust;
			var amnt=obj.Amnt;
			var fnot=obj.FNOT;
			var fnotfrom=obj.FNOTFrom;
			var fnotdvsnfrom=obj.FNOTDvsnFrom;
			var fnotto=obj.FNOTTo;
			var fnotdvsnto=obj.FNOTDvsnTo;
			var invc=obj.Invc;
			var invcfrom=obj.InvcFrom;
			var invcdvsnfrom=obj.InvcDvsnFrom;
			var invcto=obj.InvcTo;
			var invcdvsnto=obj.InvcDvsnTo;
			var fnr=obj.FNR;
			var rrnumb=obj.RRNumb;
			var rrdate=obj.RRDate;
			var cnsr=obj.Cnsr;
			var cnsg=obj.Cnsg;
			var cmdt=obj.Cmdt;
			var ldunts=obj.LdUnts;
			var emunts=obj.EmUnts;

			htmchrgdesc+='<tr><td class="lbl">Customer</td><td class="val">'+cust+'</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Charge</td><td class="val">'+chrgtype+' (Amount: <i class="fas fa-rupee-sign"></i> '+amnt+')</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Station</td><td class="val">'+sttn+' ('+sttnname+')</td></tr>';
			htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+dvsn+'</td></tr>';
			if(fnot!="/")
			{
				htmchrgdesc+='<tr><td class="head-desc" colspan="2">Consignment Detail</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">RR Detail</td><td class="val">'+rrnumb+' ('+rrdate+')</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">FNR</td><td class="val">'+fnr+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Consignor/Consignee</td><td class="val">'+cnsr+'/'+cnsg+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Commodity</td><td class="val">'+cmdt+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Units (Loaded/Empty)</td><td class="val">'+ldunts+'/'+emunts+'</td></tr>';

				htmchrgdesc+='<tr><td class="head-desc" colspan="2">F/Note Detail</td></tr>';				
				htmchrgdesc+='<tr><td class="lbl">Source</td><td class="val">'+fnotfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+fnotdvsnfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Destination</td><td class="val">'+fnotto+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+fnotdvsnto+'</td></tr>';

				htmchrgdesc+='<tr><td class="head-desc" colspan="2">Invoice Detail</td></tr>';				
				htmchrgdesc+='<tr><td class="lbl">Source</td><td class="val">'+invcfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+invcdvsnfrom+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Destination</td><td class="val">'+invcto+'</td></tr>';
				htmchrgdesc+='<tr><td class="lbl">Division</td><td class="val">'+invcdvsnto+'</td></tr>';

			}

			htmchrgdesc+='</tbody></table></div></div>';
			$("#divChrgDesc").html(htmchrgdesc);
		}
	});	
}

function vldtTrxnStatus(link,custcode,zone,chrgtype,chrgtypecode, chrgid, amnt,cnfmdate,sttn,invcid,fnotid, custdmrgid, trxnid, iwowflag,invcdate,chrgtypedesc)
{
	$('.btn-pay').attr("disabled","disabled");
	var myurl="/RailSAHAY/FrgtPGTrxn";
	$(link).text('Please wait..');
	var htmlPymtStts="";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'VLDT_TRXN_STATUS',txtChrgZone:zone,txtChrgType:chrgtypecode,txtChrgId:chrgid,txtAmnt:amnt},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
					
			if(stts=="PENDING")
			{
				$('.btn-pay').not(link).popover('hide');
				$(link).popover({
				html:true,
				title: 'Payment Pending',
        			content: '<p style="color:#4d4d4d;font-size:12px;font-weight:600;">Payment status is <span style="font-weight:600;color:#b32400;">PENDING</span> in the System, Please check it after 10 minutes again!</p>',
        			placement: 'left', 
				sanitize:false,
				delay: {show:500, hide:100}
			       }).popover('show');
				

				$(link).removeClass("btn-success");				
				$(link).addClass("btn-warning");
				$(link).addClass("text-dark");
				$(link).text('In Process');
				$('.btn-pay').removeAttr("disabled");
	
			}
			else
			{
				if(stts=="SUCCESS")
				{
					$('.btn-pay').not(link).popover('hide');
				
					$(link).popover({
					html:true,
					title: 'Payment already Received',
        				content: '<p style="color:#4d4d4d;font-size:12px;font-weight:600;">Payment has already been <span style="font-weight:600;color:#2d862d;">SUCCESSFULLY ACCRUED</span> in the System, Please refresh your screen!</p>',
	        			placement: 'left', 
					sanitize:false,
				        delay: {show:500, hide:100}
				       }).popover('show');

					$(link).text('Payment Done');
					$('.btn-pay').removeAttr("disabled");	

				}
				else
				{
					if(stts=="FAILED")
					{
						$('.btn-pay').not(link).popover('hide');
				
						$(link).popover({
						html:true,
						title: 'Technical Issue',
        					content: '<p style="color:#4d4d4d;font-size:12px;font-weight:600;">There appears to be some technical issue with this transaction, please try after some time !</p>',
	        				placement: 'left', 
						sanitize:false,
					        delay: {show:500, hide:100}
				       		}).popover('show');
						$('.btn-pay').removeAttr("disabled");	

					}
					else
					{	

						$(link).removeClass("btn-success");				
						$(link).addClass("btn-primary");
						$(link).text('In Process..');
						if((chrgtype=="L") && (fnotid==""))
							popUpGstList(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate);
						else
							makePayment(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,chrgtypedesc);
					}
			  	}
				
			}


			 $(document).on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });
			 $("a").on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });

		}
	});	
}

function fetchPopOverChrgBrkup(link, sttn, invcid, fnotid, custdmrgid, trxnid, chrgtype, iwowflag, invcdate, amnt)
{
	var custcode='';
	var custOrgn =$("#custList").val();
	var custOrgn1=$("#custList1").html();
	if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
		custcode=custOrgn;
	else
		custcode=custOrgn1;


	$("#divChrgBrkup").html('<div class="spinner-border text-danger"></div>');
	var htmchrgbrkup='<table class="table table-striped table-bordered table-responsive" style="margin-bottom:0px;"><tbody>';

	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:'',CnsrGstin:'',CnsgGstin:''},
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
			var cntr=0;
			for(var i=0;i<obj.Breakup.length;i++)
			{
				var chrgcode=obj.Breakup[i].ChrgCode;
				var chrgname=obj.Breakup[i].ChrgName;
				var amnt=obj.Breakup[i].Amnt;

				if(chrgname=='Wagon Registration Fee (WRF) Adjustment Amount')
					chrgname='WRF Adjustment Amount';

				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount") || (chrgname=="WRF Adjustment Amount")|| (chrgname=="Amount"))					
					htmchrgbrkup+='<tr class="totl-row"><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt text-right" style="text-align:right;">'+amnt+'</td></tr>';
			}
			htmchrgbrkup+='</tbody></table><a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';

   		       $('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Break-up of Charges',
        			content: htmchrgbrkup,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

			
			 $(document).on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 $("a").on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 

		}
	});

}
function fetchGTotlChrg(chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin)
{

	$("#divChrgBrkup").html('<div class="spinner-border text-danger"></div>');
	var htmchrgbrkup='<p class="chrg-type">Breakup of Charges</p><table class="table table-bordered table-chrgbreak" style="margin-bottom:0px;"><tbody>';

	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin},
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
			var cntr=0;


			for(var i=0;i<obj.Breakup.length;i++)
			{
				var chrgcode=obj.Breakup[i].ChrgCode;
				var chrgname=obj.Breakup[i].ChrgName;
				var amnt=obj.Breakup[i].Amnt;
				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount") || (chrgname=="Wagon Registration Fee (WRF) Adjustment Amount")|| (chrgname=="Amount"))
				{
					if(chrgname=="Net Payable Amount")
					{
						if((GG_PymtStts=="SUCCESS") || (GG_PymtStts=="ALREADY_SUCCESS"))
							htmchrgbrkup+='<tr class="totl-row"><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+' <span class="amnt-paid">(Paid Successfully)</span></td></tr>';
						else
							htmchrgbrkup+='<tr class="totl-row"><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
					}
					else
					{
						htmchrgbrkup+='<tr class="totl-row"><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
					}
				}
				else
				{
					if(chrgname=="GST Remark")
						htmchrgbrkup+='<tr><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt">'+amnt+'</td></tr>';
					else
						htmchrgbrkup+='<tr><td class="chrg-code">'+chrgname+'</td><td class="chrg-amnt"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
				}

				if(chrgname=="Total GST")
					GG_GstAmnt=amnt;

				if(chrgname=="Net Payable Amount")
					$("#txtAmnt").val(amnt);

			}			
			
			htmchrgbrkup+='<tr><td class="chrg-code">Customer GSTIN</td><td class="chrg-amnt"><input type="text" id="selGSTIN" name="selGSTIN" class="form-control text-gst" readonly /></td></tr>';
			htmchrgbrkup+='<tr><td colspan="2"><p class="chrg-levy">*Transaction Charges as applicable will be levied.</p></td></tr>';
			htmchrgbrkup+='<tr><td colspan="2"><p class="chrg-levy">*In case of Corporate Banking, payment has to be approved within half-an-hour of initiating the payment.</p></td></tr>';
			htmchrgbrkup+='<tr><td colspan="2" class="text-center"><button class="btn btn-secondary btn-action" id="btnBackDB" onclick="cancelPayment();"><i class="fas fa-angle-double-left"></i>&nbsp;Go To Dashboard</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-action" onclick="cancelPayment();"><i class="fas fa-angle-double-left"></i>&nbsp;Cancel and Go Back</button>&nbsp;&nbsp;<button class="btn btn-success text-light btn-action" onclick="proceedForPayment();">Continue with Payment&nbsp;<i class="fas fa-angle-double-right"></i></button></td></tr></tbody></table>';
			$("#divChrgBrkup").html(htmchrgbrkup);
			if((chrgtype=="L") && (fnotid==""))
			{
				$("#selGSTIN").val(cnsrgstin)
			}
			else
			{
				$(".btn-action").attr("disabled", "disabled");
				fetchGSTList(chrgid);
			}
			$("#btnBackDB").hide();
			
			if((GG_PymtStts=="FAILED") || (GG_PymtStts=="INIT") || (GG_PymtStts=="CANCELLED"))
			{
				$(".btn-action").show();
				$("#btnBackDB").hide();
			}
			else
			{
				$(".btn-action").hide();
				$("#btnBackDB").show();
			}
		}
	});
}
function proceedForPayment()
{
	$("#txtGSTAmnt").val(GG_GstAmnt);
	var GstIn=$("#selGSTIN").val();
	$("#txtGSTIN").val(GstIn);
	$("#frmFrgtPymt").submit();
}
function cancelPayment()
{
	$(location).attr('href', 'https://foistest.indianrail.gov.in/RailSAHAY/pages/SAHAYDashboard.jsp')
}
function fetchGSTList(chrgid)
{
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'GST_CUST_LIST',CustCode:chrgid},
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

			var cntr=0;
			/*
			$('#selGSTIN').find('option').remove().end();
			$('#selGSTIN').children().remove().end();
			$('#selGSTIN').empty();
			for(var i=0;i<obj.GstIn.length;i++)
			{
				var crntgstin =obj.GstIn[i];
				$('#selGSTIN').append(new Option(crntgstin, crntgstin))
			}
			*/

			for(var i=0;i<obj.GstIn.length;i++)
			{
				var crntgstin =obj.GstIn[i];
				$('#selGSTIN').val(crntgstin);
			}
			$(".btn-action").removeAttr("disabled");
		}
	});
}


function fetchTrxIdHist()
{
    fetchPymtHist($("#txtTrxnId").val());
}

function showMR(trxnid) 
{
    $("#TrxnId").val(trxnid);
    document.frmMR.submit();
}

function hideotherrows(rownumber,len)
{
    for(i=0;i<len;i++)
        $('#demo'+i).collapse("hide");
    $('#'+rownumber).collapse("show");
}


function fetchPymtHist(inptrxnid)
{
    GG_CrntView="PymtHist";
    var custcode='';
    var custOrgn =$("#custList").val();
    var custOrgn1=$("#custList1").html();
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
        custcode=custOrgn1;
                
    var myurl="/RailSAHAY/FrgtPymtJson";
    $("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").show();
        $("#divScndCustNew").hide();
       // $("#divAdMfScndCust").hide();
        
        //var htmlotsgpymt="<div class='float-center'><p class='ctgr-title'>Payment Transaction History</p></div>";  
        var htmlotsgpymt='<form id="frmMR" name="frmMR" method="post" action="\\RailSAHAY\\DownloadMRpdf" target="_blank"><input type=hidden id="TrxnId"    name="TrxnId" value=""/></form>';

	htmlotsgpymt+='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">Payment Transaction History</p></div><div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control" placeholder="Transaction Id" maxlength="20" id="txtTrxnId" value="'+inptrxnid+'" style="width:180px;"><div class="input-group-append"><button class="btn btn-primary" onclick="fetchTrxIdHist();">Go</button></div></div></div></div>';

      	htmlotsgpymt+='<div class="row"><div class="col-lg-12 col-sm-12"><div class="table-responsive" style="min-height:100vh;max-height:100vh;overflow-y:auto;"><table class="table table-striped table-sm table-pymthstr" id="tblPymtHist"><thead><tr class="p-stts"><th>TRANSACTION ID / DATETIME</th><th>STATION</th><th>CUSTOMER</th><th>CHARGE TYPE</th><th>STATUS</th><th>AMOUNT</th></tr></thead><tbody><tr></tr>';
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
				var sttnfrom=obj.PymtHist[i].SttnFrom;
				var sttnfromname=obj.PymtHist[i].SttnFromName;
				var sttnto=obj.PymtHist[i].SttnTo;
				var sttntoname=obj.PymtHist[i].SttnToName;
				var pymtmode=obj.PymtHist[i].PymtMode;
				var trxnamnt=obj.PymtHist[i].TrxnAmnt;
				var trxnstts=obj.PymtHist[i].TrxnStts.toUpperCase();
                                var bankTrxnId=obj.PymtHist[i].BankTrxnId;	
                                var bankTrxnDate=obj.PymtHist[i].BankTrxnDate;	
                                var acrlchrgtime=obj.PymtHist[i].AcrlTrxnTime;
                                var colr = "text-info";
                                if (trxnstts == "SUCCESS")
                                    colr   =   "trxn-success";
                                else
                                    colr   =   "trxn-fail";
                           htmlotsgpymt+='<tr data-toggle="collapse p-od" data-target="#demo'+i+'" onclick="hideotherrows(\'demo'+i+'\',\''+obj.PymtHist.length+'\');">';
                           htmlotsgpymt+='<td class="left"><i class="fas fa-caret-down"></i>&nbsp;<b><a href="javascript:void(0)" class="'+colr+'">'+trxnid+' / '+trxntime+'</a></b></td><td class="left">'+sttn1+' ('+sttnname+')'+'</td><td class="left">'+custcode+'</td><td class="left">'+chrgtype+'</td><td class="left '+colr+'" style="font-weight:600;">'+trxnstts+'</td><td class="right"><i class="fas fa-rupee-sign"></i>'+trxnamnt+'</td></tr>';

                           //if (trxnstts == "SUCCESS")
                             //   htmlotsgpymt+='<td class="right"><button class="btn btn-outline-primary btn-sm"><i class="fas fa-download"></i></button></td></tr>';
                          //htmlotsgpymt+='<td class="right"><button class="btn btn-outline-primary" onclick="showMR(\''+bankTrxnId+'\');"><i class="fas fa-download"></i></button></td></tr>';
                           //else
                             //   htmlotsgpymt+='<td></td></tr>';
                            
			    
                            htmlotsgpymt+='<tr class="collapse in" id="demo'+i+'">';
                            htmlotsgpymt+='<td colspan="7"><div>';
			    /*
                            htmlotsgpymt+='<span class="px-2"><p class="p-stts"><span class="px-2"><i class="fas fa-credit-card"></i> Payment Gateway: '+pymtmode+'</span><span class="px-2"><i class="fas fa-user"></i> Customer: '+custname+'</span><span class="px-2"><i class="fas fa-receipt"></i> Invoice: '+invcnumb+' / '+invcdate;
                            if(bankTrxnId != "")
                                htmlotsgpymt+='</span><span class="px-2"><i class="fas fa-indent"></i> Bank Transaction Id: '+bankTrxnId+' / '+bankTrxnDate+'</span></p></span>';
                            else
                                htmlotsgpymt+='</span></p></span>';
			    */
			    htmlotsgpymt+='<ul class="list-group list-group-flush"><li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr><td><span class="lbl">Invoice:</span> <span class="val">'+invcnumb+' / '+invcdate+'</span></td><td><span class="lbl">Charges Raised At:</span> <span class="val">'+acrlchrgtime+'</span></td><td><span class="lbl">Customer:</span> <span class="val">'+custname+'</span></td></tr></table></li>';
			    htmlotsgpymt+='<li class="list-group-item"><table class="pymt-dtls-table2" style="border:2px solid '+colr+';"><tr><td><span class="lbl">Bank Transaction ID:</span> <span class="val">'+bankTrxnId+' / '+bankTrxnDate+'</span></td><td><span class="lbl">Payment Gateway:</span> <span class="val">'+pymtmode+'</span></td></tr></table></li></ul>';
                            htmlotsgpymt+='</div></td></tr>';
                            
                          }
                        htmlotsgpymt+='</tbody></table></div></div></div>';
			$("#divFrgtPymt").html(htmlotsgpymt);
		}
	});
}
function fetchDmrgBrkUp(displaytype,link, sttn,invcid,fnotid,custdmrgid,custcode,iwowflag)
{
 /*   var custcode='';
    var custOrgn =$("#custList").val();
    var custOrgn1=$("#custList1").html();
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
        custcode=custOrgn1;
   */

   var htmlstr='<table class="table table-striped table-bordered table-responsive table-brkup" style="margin-bottom:0px;border:0px;">';
   var dmrgtype='';
   if(fnotid=='')
   {
	dmrgtype='DMRG_CUST'; 
	
   }
   if(fnotid!='')
   {
	dmrgtype='DMRG_INVC'; 
   }
    
               
    var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'DMRG_BRKUP',CustCode:custcode,DmrgType:dmrgtype,Sttn:sttn,InvcId:invcid,FnotId:fnotid,CustDmrgId:custdmrgid,IWOWFlag:iwowflag},
		async : true,
		success : function(data) {
			console.log("data:"+data);
        		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
			for(var i=0;i<obj.Brkup.length;i++)
			{                                
					var acrd=obj.Brkup[i].Acrd;
					var wavd=obj.Brkup[i].Wavd;
					var otsg=obj.Brkup[i].Otsg;

					if(wavd=="")
						wavd="0";

					htmlstr+='<tr><td class="lbl">Demurrage Accrued</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+acrd+'</td></tr>';					
					htmlstr+='<tr><td class="lbl">Demurrage Waived</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+wavd+'</td></tr>';
					htmlstr+='<tr><td class="lbl">Demurrage Outstanding</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+otsg+'</td></tr>';		
        	                 
                        }
                        htmlstr+='</table>';

			if(displaytype=="POP_UP")
			{
			htmlstr+='<a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';

			$('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Charge Detail',
        			content: htmlstr,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

			
			 $(document).on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 $("a").on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			}
			else
			{
				console.log("Additing to div");
				$("#divCnfmChrgDtls").html(htmlstr);
			}
		
		}
	});
}

function fetchWrfgBrkUp(displaytype, link,sttn,invcid,fnotid,custdmrgid,custcode,iwowflag)
{
    /*var custcode='';
    var custOrgn =$("#custList").val();
    var custOrgn1=$("#custList1").html();
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
        custcode=custOrgn1;
   */
   var htmlstr='<table class="table table-striped table-bordered table-responsive table-brkup" style="margin-bottom:0px;border:0px;">';
    
               
    var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'WRFG_BRKUP',CustCode:custcode,Sttn:sttn,InvcId:invcid,FnotId:fnotid,CustDmrgId:custdmrgid,IWOWFlag:iwowflag},
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
			for(var i=0;i<obj.Brkup.length;i++)
			{                                
					var acrd=obj.Brkup[i].Acrd;
					var wavd=obj.Brkup[i].Wavd;
					var otsg=obj.Brkup[i].Otsg;
	
					if(wavd=="")
						wavd="0";

					htmlstr+='<tr><td class="lbl">Wharfage Accrued</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+acrd+'</td></tr>';
					htmlstr+='<tr><td class="lbl">Wharfage Waived</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+wavd+'</td></tr>';
					htmlstr+='<tr><td class="lbl">Wharfage Outstanding</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+otsg+'</td></tr>';		
        	                 
                        }
                        htmlstr+='</table>';
			

			if(displaytype=="POP_UP")
			{
			htmlstr+='<a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';
			$('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Charge Detail',
        			content: htmlstr,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

			
			 $(document).on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 $("a").on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			}
			else
			{
				$("#divCnfmChrgDtls").html(htmlstr);
			}
		
		}
	});
} 
function fetchLoclChrgBrkUp(displaytype, link, sttn,invcid,fnotid,custdmrgid, trxnid,custcode,chrgid)
{    
   var htmlstr='<table class="table table-striped table-bordered table-responsive table-brkup" style="margin-bottom:0px;border:0px;"><tr><th>Charge</th><th>Amount</th></tr>';
   var lctype='';
   if(fnotid=='')
   {
	lctype='LOCL_CUST'; 
	
   }
   if(fnotid!='')
   {
	lctype='LOCL_INVC'; 
   }
    
               
    var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'LOCL_CHRG_BRKUP',LCType:lctype,CustCode:custcode,TrxnId:trxnid,LCType:lctype,Sttn:sttn,InvcId:invcid,FnotId:fnotid,CustDmrgId:custdmrgid,ChrgId:chrgid},
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
			for(var i=0;i<obj.Brkup.length;i++)
			{                                
				var chrgcode=obj.Brkup[i].ChrgCode;
				var chrgname=obj.Brkup[i].ChrgName;
				var amnt=obj.Brkup[i].Amnt;

				htmlstr+='<tr><td class="val">'+chrgname+'</td><td class="val" style="text-align:right;">'+amnt+'</td></tr>';
        	                 
                        }
                        htmlstr+='</table>';
			

			if(displaytype=="POP_UP")
			{
			htmlstr+='<a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';

			$('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Charge Detail',
        			content: htmlstr,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

			
			 $(document).on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 $("a").on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			}
			else
			{
				$("#divCnfmChrgDtls").html(htmlstr);
			}
		
		}
	});
}

function popUpGstList(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate)
{    
   var 	htmlstr='<ul class="list-group list-group-flush">';
       	/*htmlstr+='<li class="list-group-item"><div class="form-check-inline"><label class="form-check-label"><input type="radio" class="form-check-input" checked name="optradio" value="C">Payment By Customer</label></div><div class="form-check-inline disabled"><label class="form-check-label"><input type="radio" class="form-check-input" name="optradio" disabled value="H">By Handling Agent</label></div></li>';*/
	htmlstr+='<li class="list-group-item"><p class="p-gstin">Select GSTIN</p><select id="selGSTIN" class="sel-gstin">';
   	
	$("#gstPopUpModal").modal('show');           
   	$("#divGstInpt").html('<div class="spinner-border text-danger"></div>');	

	$("#Sttn").val(sttn);
	$("#ChrgType").val(chrgtype);
	$("#ChrgTypeCode").val(chrgtypecode);
	$("#InvcId").val(invcid);
	$("#IWOWFlag").val(iwowflag);
	$("#InvcDate").val(invcdate);
	$("#Amnt").val(amnt);
	$("#ChrgId").val(chrgid);
	$("#ChrgZone").val(zone);
	$("#CnfmDate").val(cnfmdate);
	$("#FNOTId").val(fnotid);
	$("#CustDmrgId").val(custdmrgid);
	$("#TrxnId").val(trxnid);
	$("#RlyGstIn").val("");
	$("#CustGstIn").val("");
	$("#CustCode").val(custcode);

   var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'CUST_GSTIN',CustCode:custcode,Sttn:sttn},
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
			for(var i=0;i<obj.GSTINList.length;i++)
			{                                
				var gstin=obj.GSTINList[i].GSTIN;
				if(i==0)					
					$("#RlyGstIn").val(gstin);
				else
					htmlstr+='<option value="'+gstin+'">'+gstin+'</option>';
        	                 
                        }
                        htmlstr+='</select></li>';
			htmlstr+='</ul>';
			$("#divGstInpt").html(htmlstr);	
		}
	});

$('#gstPopUpModal').on('hide.bs.modal', function (event) {
	
	$('.btn-pay').removeAttr("disabled");          
	$('.btn-pay').removeClass("btn-primary");         
	$('.btn-pay').addClass("btn-success");
	$('.btn-pay').text('Pay Now');
   	$("#divGstInpt").html('');	
});
}
function makeGstPayment()
{
	var custgstin=$("#selGSTIN").val();
	$("#CustGstIn").val(custgstin);
	$("#frmFrgtPymtInit").submit();
}
function closeGstPopUp()
{
	$("#gstPopUpModal").modal('hide'); 
	$('.btn-pay').removeAttr("disabled");          
	$('.btn-pay').removeClass("btn-primary");         
	$('.btn-pay').addClass("btn-success");
	$('.btn-pay').text('Pay Now');
   	$("#divGstInpt").html('');		
}
function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
   
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
   
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
   
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function eraseCookie(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
}
$(document).ready(function() 
{

			 $(document).on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });
			 $("a").on("click",function() {
        			$('.btn-pay').popover('hide');
    			  });
});
function openHelpSupport()
{
	window.open('/RailSAHAY/pages/FrgtPymtHelp.jsp','FrgtPymtHelp');
}
function fetchDmrgWrfgBrkupDtls(displaytype,link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin)
{
   var htmlstr='<table class="table table-striped table-bordered table-responsive table-brkup" style="margin-bottom:0px;border:0px;">';
   var dmrgwrfgtype='';
   var optn="";

   if(chrgtype=="D")
   {
	if(fnotid=='')
		dmrgwrfgtype='DMRG_CUST'; 
	else
		dmrgwrfgtype='DMRG_INVC'; 

	optn="DMRG_BRKUP";
   } 
   if(chrgtype=="W")
   {
	optn="WRFG_BRKUP";
   }   
      
    var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:optn,CustCode:custcode,DmrgType:dmrgwrfgtype,Sttn:sttn,InvcId:invcid,FnotId:fnotid,CustDmrgId:custdmrgid,IWOWFlag:iwowflag},
		async : true,
		success : function(data) {
			console.log("data:"+data);
        		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
			var otsgamnt="";
			for(var i=0;i<obj.Brkup.length;i++)
			{                                
					var acrd=obj.Brkup[i].Acrd;
					var wavd=obj.Brkup[i].Wavd;
					var otsg=obj.Brkup[i].Otsg;

					otsgamnt=otsg;

					if(wavd=="")
						wavd="0";
					
					if(chrgtype=="D")
					{
						htmlstr+='<tr><td class="lbl">Demurrage Accrued</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+acrd+'</td></tr>';					
						htmlstr+='<tr><td class="lbl">Demurrage Waived</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+wavd+'</td></tr>';
						htmlstr+='<tr><td class="lbl">Demurrage Outstanding</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+otsg+'</td></tr>';		
					}
					if(chrgtype=="W")
					{
						htmlstr+='<tr><td class="lbl">Wharfage Accrued</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+acrd+'</td></tr>';					
						htmlstr+='<tr><td class="lbl">Wharfage Waived</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+wavd+'</td></tr>';
						htmlstr+='<tr><td class="lbl">Wharfage Outstanding</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+otsg+'</td></tr>';		
					}		
        	                 
                        }
			
			/**Calling GST Dtls**/
				var myurl="/RailSAHAY/FrgtPymtJson";
		$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:otsgamnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin},
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
			var cntr=0;


			for(var i=0;i<obj.Breakup.length;i++)
			{
				var chrgcode=obj.Breakup[i].ChrgCode;
				var chrgname=obj.Breakup[i].ChrgName;
				var amnt=obj.Breakup[i].Amnt;
				if(chrgname=="Total Amount")
					continue;

				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount"))
				{
					htmlstr+='<tr><td class="lbl">'+chrgname+'</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
				}
			}
			htmlstr+='</table>'	
			/**Finished Block for GST Dtls**/


			if(displaytype=="POP_UP")
			{
			htmlstr+='<a class="link-chrg-close" onclick="closeChrgBrkUp();" href="javascript:void(0)"><i class="fas fa-times"></i>&nbsp;Close</a>';

			$('.link-brkup').not(link).popover('hide');

			$(link).popover({
				html:true,
				title: 'Charge Detail',
        			content: htmlstr,
        			placement: 'left', 
				sanitize:false
		       }).popover('show');

			
			 $(document).on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			 $("a").on("click",function() {
        			$('.link-brkup').popover('hide');
				$('.popover').popover('hide');
				$('.popover').remove();
    			  });
			}
			else
			{
				$("#divCnfmChrgDtls").html(htmlstr);
			}
		    }
		  });
		
		}
	});

}