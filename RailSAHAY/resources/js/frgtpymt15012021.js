var GG_GstAmnt="";
var GG_ChrgId=[];
var GG_CrntView="PendPymt";

function successToast(msg)
{
      cuteToast({
      type: "success",
      message: msg,
      timer: 5000
    })
};
function successToastFast(msg)
{
      cuteToast({
      type: "success",
      message: msg,
      timer: 3000
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
function toggleOff()
{
	/*$("#btnPrimSec").removeClass("active");
	GG_SelCustCtgr="P";
	$(".btn-switch").attr("disabled",true);*/
	return true;
}
function toggleOn()
{
	/*$(".btn-switch").removeAttr("disabled");*/
	return true;
}
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
        $("#CustWgmt").hide();
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

function fetchOtsgSecChrgSmry()
{
	var custcode=$("#custListSec").val();
	var myurl="/RailSAHAY/FrgtPymtJson";

	$.ajax({
		url : myurl,
		type : "post",
        	data: {Optn:'OTSG_SEC_CHRG_SMRY',CustCode:custcode},
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
	fetchCartChrgList();
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');

	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
        $("#CustWgmt").hide();
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
				var freetime=obj.OtsgChrg[i].FreeTime;
				var freetimestr='';

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
				var zonedvsn=dvsn+"/"+zone;

				if(trxnstts=="PENDING")
				{
					btnClass="btn-danger";
					btnText="Payment In Process"
				}

				//fetchDmrgBrkUp(sttn,invcid,custdmrgid,hndgagnt),

				var cartbtnclss="btn-cart";
				var cartpaybtnclss='';
				if(GG_CartSize>0)
				{
					if(checkCartPymt(chrgid))
					{
						cartbtnclss="btn-cart-yes";
						cartpaybtnclss='disabled';
					}
				}

				if((sttn==prevsttn) && (fnotid==prevfnotid) && (custcode==prevcustcode))
				{
					if(chrgtype=="FREIGHT CHARGES")
					{
						freetimestr='';

						if(freetime!='')
							freetimestr='<br/><span class="freetime-anim"><i class="far fa-hand-point-right"></i></span><span class="freetime-lbl"> *Pay before <b style="color:#000;">'+freetime+'</b> to avoid Late Payment Charges</span>';

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="fetchCartFrgtChrgDtls(this, \''+chrgid+'\',\''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span>'+freetimestr+'</td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchPopOverChrgBrkup(this, \''+sttn+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'"  paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';

					}
					else if (chrgtype=="DEMURRAGE CHARGES")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartChrgDtls(\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\',\''+amnt+'\', \'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'"  paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchLoclChrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+custcode+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartLoclChrgDtls(this,\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\',\''+chrgtype+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\', \'\',\'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay '+cartpaybtnclss+' float-right text-light '+btnClass+'"  paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchWrfgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartChrgDtls(\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\', \'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'"  paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
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
						freetimestr='';

						if(freetime!='')
							freetimestr='<br/><span class="freetime-anim"><i class="far fa-hand-point-right"></i></span><span class="freetime-lbl">*Pay before <b style="color:#000;">'+freetime+'</b> to avoid Late Payment Charges</span>';

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="fetchCartFrgtChrgDtls(this, \''+chrgid+'\',\''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span>'+freetimestr+'</td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchPopOverChrgBrkup(this, \''+sttn+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'"  paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="DEMURRAGE CHARGES")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartChrgDtls(\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\', \'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchLoclChrgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+custcode+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/

						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartLoclChrgDtls(this,\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\',\''+chrgtype+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\', \'\',\'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'"  paychrgid="'+chrgid+'"onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")
					{
						/*htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><i class="fas fa-caret-right"></i>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchWrfgBrkUp(\'POP_UP\',this, \''+sttn+'\', \''+invcid+'\',\''+fnotid+'\', \''+custdmrgid+'\', \''+custcode+'\', \''+iwowflag+'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';*/
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-light btn-sm '+cartbtnclss+'" chrgid="'+chrgid+'" onclick="showCartChrgDtls(\''+chrgid+'\', \''+sttn+'\',\''+sttnname+'\',\''+zonedvsn+'\',\''+zonecode+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+fnotnumb+'\', \''+fnotdate+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\',\''+cnfmdate+'\', \''+acrldate+'\', \''+amnt+'\', \'\',\'\',\'\');"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><a href="javascript:void(0)" onclick="fetchDmrgWrfgBrkupDtls(\'POP_UP\',this, \''+chrgid+'\', \''+sttn+'\', \''+custcode+'\', \''+invcid+'\', \''+fnotid+'\', \''+custdmrgid+'\', \''+trxnid+'\', \''+chrgtype1+'\', \''+iwowflag+'\', \''+invcdate+'\', \''+amnt+'\', \'\',\'\',\'\');" class="link-brkup"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</a></td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right '+cartpaybtnclss+' text-light '+btnClass+'" paychrgid="'+chrgid+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+chrgtype+'\');">'+btnText+'</button></td></tr>';
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

function fetchOtsgSecChrg(sttn)
{
    GG_CrntView="PendPymt";
	var custcode=$("#custListSec").val();
	var datefrom='';
	var dateto='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$("#divFrgtPymt").html('<div class="spinner-border text-danger"></div>');
	
	fetchCartChrgList();
	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
        $("#CustWgmt").hide();
        $("#divFrgtPymt").show();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
       // $("#divAdMfScndCust").hide();

	var htmlotsgpymt='<div class="row"><div class="col-lg-6 col-sm-12"><p class="ctgr-pymt-title">Pending Payments<br/><span class="span-note">*Charges shown below do not include Taxes/Adjustments</span> </p></div><div class="col-lg-6 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control text-uppercase" list="frgtpymtlocnlist" placeholder="Station" id="txtFrgtPymtSttn" value="'+sttn+'" style="width:150px;"><datalist id="frgtpymtlocnlist"></datalist><div class="input-group-append"><button class="btn btn-primary" onclick="fetchSttnOtsgPymt();">Go</button></div></div></div></div>';
	
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'OTSG_CHRG_SEC_CUSTOMER',CustCode:custcode,Sttn:sttn},
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
			var prevhdngagnt="";
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
				var hndgagnt=obj.OtsgChrg[i].HndgAgnt;
				var hndgagntname=obj.OtsgChrg[i].HndgAgntName;
				var zonedvsn=dvsn+"/"+zone;


				
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

				var cartbtnclss="btn-cart";
				var cartpaybtnclss='';
				if(GG_CartSize>0)
				{
					if(checkCartPymt(chrgid))
					{
						cartbtnclss="btn-cart-yes";
						cartpaybtnclss='disabled';
					}
				}

				if((sttn==prevsttn) && (fnotid==prevfnotid) && (custcode==prevcustcode) && (hndgagnt==prevhndgagnt))
				{
					if(chrgtype=="FREIGHT CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="DEMURRAGE CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}

				}
				else
				{
					if(cntr>0)
					{
						htmlotsgpymt+='</table></div>';
					}					

					if((fnotid=="") && (invcid==""))
						htmlotsgpymt+='<div class="sttn-card"><div class="fnot-card-head"><span class="sttn-chrg">'+sttnname+' ('+sttn+')</span><span class="hndg-agnt-lbl"><i class="far fa-check-square"></i>&nbsp;Secondary:</span><span class="badge badge-info hndg-agnt-val">'+hndgagnt+'</span><span class="dvsn-chrg float-right">'+dvsn+'/'+zone+'</div><hr/>';
					else
						htmlotsgpymt+='<div class="fnot-card"><div class="fnot-card-head"><span class="sttn-chrg">'+sttnname+' ('+sttn+')</span><span class="hndg-agnt-lbl"><i class="far fa-check-square"></i>&nbsp;Secondary:</span><span class="badge badge-info hndg-agnt-val">'+hndgagnt+'</span><span class="dvsn-chrg float-right">'+dvsn+'/'+zone+'</div><hr/>';


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
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatus(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="DEMURRAGE CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';

					}
					else if (chrgtypecode=="05")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td style="font-weight:600;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}
					else if (chrgtype=="WHARFAGE CHARGES")
					{
						htmlotsgpymt+='<tr><td style="width:55%;line-height:18px;"><button class="btn btn-sec-cart btn-light btn-sm '+cartbtnclss+'" trxnid="'+trxnid+'" chrgid="'+chrgid+'" sttn="'+sttn+'" sttnname="'+sttnname+'" invcid="'+invcid+'" invcnumb="'+invcnumb+'" invcdate="'+invcdate+'" fnotid="'+fnotid+'" fnotnumb="'+fnotnumb+'" fnotdate="'+fnotdate+'" iwowflag="'+iwowflag+'" custdmrgid="'+custdmrgid+'" amnt="'+amnt+'" cnfmdate="'+cnfmdate+'" acrldate="'+acrldate+'" dvsn="'+zonedvsn+'" zone="'+zonecode+'" cust="'+custcode+'" hndgagnt="'+hndgagnt+'" hndgagntname="'+hndgagntname+'" chrgtype="'+chrgtype1+'" chrgtypecode="'+chrgtypecode+'" chrgtypedesc="'+chrgtype+'"><i class="fas fa-cart-plus"></i></button>&nbsp;'+chrgtype+' <span class="bill-date">(Accrued:<b style="color:#000;"> '+acrldate+'</b>  |   Raised: <b style="color:#000;">'+trxntime+'</b>)</span></td><td style="width:30%;"><table><tr><td><div class="progress d-none d-lg-block d-xl-block" style="width:100px;margin-top:10px;height:4px;"><div role="progressbar" style="width:'+pctg+'%;height:4px;" aria-valuenow="'+pctg+'" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div></div></td><td><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr></table></td><td style="width:15%;"><button class="btn btn-sm btn-pay float-right text-light '+btnClass+'" onclick="vldtTrxnStatusSec(this,\''+custcode+'\',\''+zonecode+'\',\''+chrgtype1+'\',\''+chrgtypecode+'\',\''+chrgid+'\',\''+amnt+'\',\''+cnfmdate+'\',\''+sttn+'\',\''+invcid+'\',\''+fnotid+'\',\''+custdmrgid+'\',\''+trxnid+'\',\''+iwowflag+'\',\''+invcdate+'\',\''+chrgtype+'\',\''+hndgagnt+'\',\''+hndgagntname+'\');" paychrgid="'+chrgid+'">'+btnText+'</button></td></tr>';
					}
				}
				cntr++;
				prevsttn=sttn;
				prevfnotid=fnotid;
				prevcustcode=custcode;
				prevhndgagnt=hndgagnt;

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
			$(".btn-sec-cart").on('click',function() {
				var sttn=$(this).attr("sttn");
				var sttnname=$(this).attr("sttnname");
				var dvsn=$(this).attr("dvsn");
				var zone=$(this).attr("zone");
				var chrgid=$(this).attr("chrgid");
				var cust=$(this).attr("cust");
				var hndgagnt=$(this).attr("hndgagnt");
				var hndgagntname=$(this).attr("hndgagntname");
				var chrgtypedesc=$(this).attr("chrgtypedesc");
				var chrgtype=$(this).attr("chrgtype");
				var chrgtypecode=$(this).attr("chrgtypecode");
				var invcid=$(this).attr("invcid");
				var fnotid=$(this).attr("fnotid");
				var fnotnumb=$(this).attr("fnotnumb");
				var fnotdate=$(this).attr("fnotdate");
				var custdmrgid=$(this).attr("custdmrgid");
				var iwowflag=$(this).attr("iwowflag");
				var invcdate=$(this).attr("invcdate");
				var invcnumb=$(this).attr("invcnumb");
				var cnfmdate=$(this).attr("cnfmdate");
				var acrldate=$(this).attr("acrldate");
				var amnt=$(this).attr("amnt");
				processCartForSec(sttn,sttnname,dvsn,zone,chrgid,cust, hndgagnt, hndgagntname, chrgtype, chrgtypecode,chrgtypedesc,trxnid,invcid,fnotid,fnotnumb, fnotdate, custdmrgid, iwowflag, invcdate, invcnumb,cnfmdate, acrldate, amnt,hndgagnt,hndgagntname);
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
        //alert(GG_SelCustCtgr);
	if(GG_SelCustCtgr=="P")
		fetchOtsgChrg(sttn);
	if(GG_SelCustCtgr=="S")
		fetchOtsgSecChrg(sttn);

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
function makePayment(custcode,sttn,invcid,fnotid,custdmrgid,trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,chrgzone,cnfmdate,chrgtypedesc,hndgagnt,hndgagntname)
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
	$("#HndgAgnt").val(hndgagnt);
	$("#HndgAgntName").val(hndgagntname);



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
	if($(link).hasClass("disabled"))
	{
		return false;
	}
	$('.btn-pay').attr("disabled","disabled");
	var myurl="/RailSAHAY/FrgtPGTrxn";
	$(link).text('Please wait..');
	var htmlPymtStts="";

	/*content: '<table class="table table-striped table-pending"><tr><td><p style="color:#4d4d4d;font-size:12px;font-weight:600;">Payment status is <span style="font-weight:600;color:#b32400;">PENDING</span> in the System, Please check it after 10 minutes again!</p></td></tr><tr><td>Do you want to cancel the previous transaction ? <a href="javascript:void(0)" onclick="cancelUserPayment(\''+apptrxnid+'\')" class="link-cancel">YES</a> / <a href="javascript:void(0)" onclick="closePendingMsg()" class="link-not-cancel">NO</a></td></tr><tr><td id="tdTrxnCnclMesg"></td></tr></table>',*/

	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'VLDT_TRXN_STATUS',txtChrgZone:zone,txtChrgType:chrgtypecode,txtChrgId:chrgid,txtAmnt:amnt},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
                       
			var apptrxnid=obj.AppTrxnId;
			if(stts=="PENDING")
			{
				$('.btn-pay').not(link).popover('hide');
				$(link).popover({
				html:true,
				title: 'Payment Pending',
        			content: '<table class="table table-striped table-pending"><tr><td><p style="color:#4d4d4d;font-size:12px;font-weight:600;">Payment status is <span style="font-weight:600;color:#b32400;">PENDING</span> in the System, Please check it after 10 minutes again!</p></td></tr></table>',
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
							popUpGstList(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,'');
						else
							makePayment(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,chrgtypedesc,'','');
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
function vldtTrxnStatusSec(link,custcode,zone,chrgtype,chrgtypecode, chrgid, amnt,cnfmdate,sttn,invcid,fnotid, custdmrgid, trxnid, iwowflag,invcdate,chrgtypedesc,hndgagnt,hndgagntname)
{
	if($(link).hasClass("disabled"))
	{
		return false;
	}
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
						popUpGstListSec(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,chrgtypedesc,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,hndgagnt,hndgagntname);
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
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:'',CnsrGstin:'',CnsgGstin:'',HndgAgnt:''},
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
function fetchGTotlChrg(chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin,hndgagnt)
{

	$("#divChrgBrkup").html('<div class="spinner-border text-danger"></div>');
	var htmchrgbrkup='<p class="chrg-type">Breakup of Charges</p><table class="table table-bordered table-chrgbreak" style="margin-bottom:0px;"><tbody>';

	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin,HndgAgnt:hndgagnt},
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
			if(((chrgtype=="L") && (fnotid=="")) || (hndgagnt!=""))
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
        $("#CustWgmt").hide();
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

function popUpGstList(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,hndgagnt)
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
function popUpGstListSec(custcode, sttn,invcid,fnotid, custdmrgid, trxnid,chrgtype,chrgtypecode,chrgtypedesc,iwowflag,invcdate,amnt,chrgid,zone,cnfmdate,hndgagnt,hndgagntname)
{
   var 	htmlstr='<ul class="list-group list-group-flush">';
       	/*htmlstr+='<li class="list-group-item"><div class="form-check-inline"><label class="form-check-label"><input type="radio" class="form-check-input" checked name="optradio" value="C">Payment By Customer</label></div><div class="form-check-inline disabled"><label class="form-check-label"><input type="radio" class="form-check-input" name="optradio" disabled value="H">By Handling Agent</label></div></li>';*/
	htmlstr+='<li class="list-group-item"><p class="p-gstin">Select GSTIN <span class="span-gstin">('+hndgagnt+' - '+hndgagntname+')</span></p><select id="selGSTIN" class="sel-gstin">';
	$("#btnMakeGstPymt").hide();
	$("#gstPopUpModal").modal('show');
   	$("#divGstInpt").html('<div class="spinner-border text-danger"></div>');

	$("#Sttn").val(sttn);
	$("#ChrgType").val(chrgtype);
	$("#ChrgTypeCode").val(chrgtypecode);
	$("#ChrgTypeDesc").val(chrgtypedesc);
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
	$("#HndgAgnt").val(hndgagnt);
	$("#HndgAgntName").val(hndgagntname);

   var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'HNDG_AGNT_GSTIN',CustCode:hndgagnt,Sttn:sttn},
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
			$("#btnMakeGstPymt").show();
		}
	});

$('#gstPopUpModal').on('hide.bs.modal', function (event) {

	$('.btn-pay').removeAttr("disabled");
	$('.btn-pay').removeClass("btn-primary");
	$('.btn-pay').addClass("btn-success");
	$("#btnMakeGstPymt").hide();
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
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:otsgamnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin,HndgAgnt:''},
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
$(document).ready(function()
{
//alert("frgtpaymt ready");
	$("#btnPrimSec").on('click',function() {
			if($(this).hasClass("active"))
			{
					GG_SelCustCtgr="P";
					$("#custList").show();
					$("#custListSec").hide();
					fetchCountDtls();
					fetchOtsgChrgSmry();
					fetchOtsgChrg('');
                                        $("#rakedtls").show();
			}
			else
			{
				GG_SelCustCtgr="S";
				$("#custListSec").show();
				$("#custList").hide();
				fetchOtsgSecChrgSmry();
				fetchOtsgSecChrg('');

                                 $("#rakedtls").hide();
                                //alert("done");
	                       $("#fnrCont").html('<strong>0</strong>');
        	               $("#cncrn").html('<strong>0</strong>');
                	       $("#srCont").html('<strong>0</strong>');
                       		$("#odrCont").html('<strong>0</strong>');

			}

	});
});
function fetchChrg(sttn)
{
	if(GG_SelCustCtgr=="P")
		fetchOtsgChrg(sttn);
	if(GG_SelCustCtgr=="S")
		fetchOtsgSecChrg(sttn);

}

/* Open when someone clicks on the span element */
function openNav() {
  document.getElementById("myNav").style.width = "100%";
}

/* Close when someone clicks on the "x" symbol inside the overlay */
function closeNav() {
  document.getElementById("myNav").style.width = "0%";
}
function refreshDashboard()
{
	location.reload();
}
function cancelUserPayment(apptrxnid)
{
	var myurl="/RailSAHAY/FrgtPGTrxn";
	$("#tdTrxnCnclMesg").html('<div class="spinner-border text-danger"></div>');
	var htmlPymtStts="";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CANCEL_USER_TRXN',AppTrxnId:apptrxnid},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var apptrxnid=obj.AppTrxnId;
			if(stts=="SUCCESS")
			{
				$('.btn-pay').popover('hide');
				$('.btn-pay').removeClass("btn-warning");
				$('.btn-pay').removeClass("btn-danger");
				$('.btn-pay').removeClass("btn-success");
				$('.btn-pay').addClass("btn-success text-white");
				$('.btn-pay').text('Pay Now');
				successToast("Transaction Cancelled! Please proceed with the payment now");
				$("#tdTrxnCnclMesg").html('');
			}
			if(stts=="FAILED")
			{
				$('.btn-pay').popover('hide');
				$('.btn-pay').addClass("btn-success text-white");
				$('.btn-pay').text('Pay Now');
				failedToast("Sorry! Transaction can't be Cancelled! Please wait for its completion");
				$("#tdTrxnCnclMesg").html('');
			}
		}
	});

}
function closePendingMsg()
{
	$('.btn-pay').popover('hide');
	$('.btn-pay').removeClass("btn-danger");
	$('.btn-pay').removeClass("btn-success");
	$('.btn-pay').removeClass("btn-warning");
	$('.btn-pay').addClass("btn-success text-white");
	$('.btn-pay').text('Pay Now');
	return true;
}
function checkCartPymt(chrgid)
{
	var retVal=false;

	if(GG_CartSize==0)
		return false;
	else
	{
		for(var i=0;i<GG_CartSize;i++)
		{
			if(chrgid==GG_CartPymtArr[i])
			{
				retVal=true;
				break;
			}	
		}
	}
	return retVal;
}



var G_ChrgId='';
var G_Sttn='';
var G_SttnName='';
var G_Dvsn='';
var G_Zone='';
var G_CustCode='';
var G_InvcId='';
var G_FNotId='';
var G_FNotNumb='';
var G_FNotDate='';
var G_CustDmrgId='';
var G_TrxnId='';
var G_ChrgType='';
var G_ChrgTypeDesc='';
var G_IWOWFlag='';
var G_InvcDate='';
var G_CnfmDate='';
var G_AcrlDate='';
var G_Amnt='';
var G_GstAmnt=0;
var G_Waiver=0;
var G_NetAmnt=0;
var G_RlyGstin='';
var G_CnsrGstin='';
var G_CnsgGstin='';
var G_HndgAgnt='';
var G_WRFAjstFlag='';
var G_WRFAjstAmnt=0;

var GG_DelChrgId="";

function resetGlblVars()
{
	G_ChrgId='';
	G_Sttn='';
	G_SttnName='';
	G_Dvsn='';
	G_Zone='';
	G_CustCode='';
	G_InvcId='';
	G_FNotId='';
	G_FNotNumb='';
	G_FNotDate='';
	G_CustDmrgId='';
	G_TrxnId='';
	G_ChrgType='';
	G_ChrgTypeDesc='';
	G_IWOWFlag='';
	G_InvcDate='';
	G_CnfmDate='';
	G_AcrlDate='';
	G_Amnt='';
	G_GstAmnt=0;
	G_Waiver=0;
	G_NetAmnt=0;
	G_RlyGstin='';
	G_CnsrGstin='';
	G_CnsgGstin='';
	G_WRFAjstFlag='';
	G_WRFAjstAmnt=0;
	G_HndgAgnt='';
	$("#cartBodyGSTIN").html('');
	$("#cartGSTBodyFtr").html('');
}
function showCartChrgDtls(chrgid, sttn, sttnname, dvsn, zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, iwowflag, invcdate,cnfmdate, acrldate, amnt, rlygstin, cnsrgstin, cnsggstin)
{
	$("#cartGSTModal").modal('show');
	$("#cartGSTBody1").html('');
	$("#btnAddCart").hide();
	var chrgtypedesc='';

	var htmlhead='<span class="sttn-cart-head">'+sttn+'</span>';
	$("#cartGSTBody").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');
	htmlstr='';
	var htmlstr='<table class="table table-striped table-bordered table-cart" style="margin-bottom:0px;border:0px;">';
   var dmrgwrfgtype='';
   var optn="";

   if(chrgtype=="D")
   {
	if(fnotid=='')
	{
		dmrgwrfgtype='DMRG_CUST';
	}
	else
	{
		dmrgwrfgtype='DMRG_INVC';
	}
	optn="DMRG_BRKUP";
	htmlhead+=' (DEMURRAGE CHARGES: <i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+')';
	chrgtypedesc='DEMURRAGE CHARGES';
   }
   if(chrgtype=="W")
   {
	optn="WRFG_BRKUP";
	htmlhead+=' (WHARFAGE CHARGES: <i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+')';
	chrgtypedesc='WHARFAGE CHARGES';
   }

   $("#cartGSTHeading").html(htmlhead);

	resetGlblVars();

	G_ChrgId=chrgid;
	G_Sttn=sttn;
	G_SttnName=sttnname;
	G_Dvsn=dvsn;
	G_Zone=zone;
	G_CustCode=custcode;
	G_InvcId=invcid;
	G_FNotId=fnotid;
	G_FNotNumb=fnotnumb;
	G_FNotDate=fnotdate;
	G_CustDmrgId=custdmrgid;
	G_TrxnId=trxnid;
	G_ChrgType=chrgtype;
	G_ChrgTypeDesc=chrgtypedesc;
	G_IWOWFlag=iwowflag;
	G_InvcDate=invcdate;
	G_CnfmDate=cnfmdate;
	G_AcrlDate=acrldate;
	G_Amnt=amnt;
	G_RlyGstin=rlygstin;
	G_CnsrGstin=cnsrgstin;
	G_CnsgGstin=cnsggstin;
	G_HndgAgnt='';


    var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:optn,CustCode:custcode,DmrgType:dmrgwrfgtype,Sttn:sttn,InvcId:invcid,FnotId:fnotid,CustDmrgId:custdmrgid,IWOWFlag:iwowflag},
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
			var otsgamnt="";
			G_Waiver=0;
			for(var i=0;i<obj.Brkup.length;i++)
			{
					var acrd=obj.Brkup[i].Acrd;
					var wavd=obj.Brkup[i].Wavd;
					var otsg=obj.Brkup[i].Otsg;

					otsgamnt=otsg;

					if(wavd=="")
						wavd="0";

					if(wavd!="0")
					{
						G_Waiver+=Number(wavd);
					}

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
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:otsgamnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin,HndgAgnt:''},
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

				if(chrgname=="Total GST")
					G_GstAmnt+=Number(amnt);
				if(chrgname=="Net Payable Amount")
					G_NetAmnt+=Number(amnt);


				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount"))
				{
					htmlstr+='<tr><td class="lbl">'+chrgname+'</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
				}
			}
			htmlstr+='</table>'
			/**Finished Block for GST Dtls**/
			$("#cartGSTBody").html(htmlstr);

    		      	fetchCartPymtGSTNumb(chrgid);


		    }
		  });

		}
	});

}

function showCartLoclChrgDtls(link, chrgid, sttn, sttnname, dvsn,zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate,cnfmdate,acrldate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt)
{
	$("#cartGSTModal").modal('show');
	$("#btnAddCart").hide();
	$("#cartGSTBody").html('');
	$("#cartGSTBody1").html('');

	resetGlblVars();

	G_ChrgId=chrgid;
	G_Sttn=sttn;
	G_SttnName=sttnname;
	G_Dvsn=dvsn;
	G_Zone=zone;
	G_CustCode=custcode;
	G_InvcId=invcid;
	G_FNotId=fnotid;
	G_FNotNumb=fnotnumb;
	G_FNotDate=fnotdate;
	G_CustDmrgId=custdmrgid;
	G_TrxnId=trxnid;
	G_ChrgType=chrgtype;
	G_ChrgTypeDesc=chrgtypedesc;
	G_IWOWFlag=iwowflag;
	G_InvcDate=invcdate;
	G_CnfmDate=cnfmdate;
	G_AcrlDate=acrldate;
	G_Amnt=amnt;
	G_RlyGstin=rlygstin;
	G_CnsrGstin=cnsrgstin;
	G_CnsgGstin=cnsggstin;
	G_HndgAgnt=hndgagnt;

	if(fnotid!='')
	{
		fetchCartGSTLoclChrgDtls(link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt);
		return true;
	}

	$("#cartGSTBody").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');

	var htmlstr='<table class="table table-responsive table-cartgst"><tr><td style="border:0;"><select id="selGSTINCart" class="sel-gstin" style="height:auto;padding:8px;width:auto;"><option value="#">Select GSTIN</option>';
	var htmlhead='<span class="sttn-cart-head">'+sttn+'</span>';
	htmlhead+=' ('+chrgtypedesc+': &nbsp;<i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+')';
	$("#cartGSTHeading").html(htmlhead);

	
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
					rlygstin=gstin;
				else
					htmlstr+='<option value="'+gstin+'">'+gstin+'</option>';

                        }
			G_RlyGstin=rlygstin;
                        htmlstr+='</select></td><td style="border:0;"><button class="btn btn-danger btn-sm" id="btnProceed">Continue&nbsp;&nbsp;<i class="fas fa-angle-right"></i></button>';
			htmlstr+='</ul>';
			$("#cartGSTBody").html(htmlstr);
	
			$("#btnProceed").on("click",function() {			
			   fetchCartGSTLoclChrgDtls(link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt);
			});
		}
	});

}
function fetchCartGSTLoclChrgDtls(link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin,hndgagnt)
{
	var loclchrggstin='';
	var htmchrgbrkup='<table class="table table-striped table-cart">';
	$("#cartGSTBody1").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');

	if(fnotid=='')
	{
		loclchrggstin=$("#selGSTINCart").val();
		var length1 = $('#selGSTINCart> option').length;
		if((length1>1) && (loclchrggstin=="#"))
		{
			$("#cartGSTBody1").html('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Select GSTIN!</strong> to proceed.</div>');
			return false;
		}
	
		G_CnsrGstin=loclchrggstin;
		G_CnsgGstin=loclchrggstin;
	}

	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:rlygstin,CnsrGstin:loclchrggstin,CnsgGstin:loclchrggstin,HndgAgnt:hndgagnt},
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
				
				if(chrgname=="Total GST")
					G_GstAmnt+=Number(amnt);
				if(chrgname=="Net Payable Amount")
					G_NetAmnt+=Number(amnt);
				if(chrgname=="Wagon Registration Fee (WRF) Adjustment Amount")
					G_Waiver+=Number(amnt);

				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount") || (chrgname=="Wagon Registration Fee (WRF) Adjustment Amount")|| (chrgname=="Amount"))
				{
					if(chrgname=="Net Payable Amount")
					{						
						htmchrgbrkup+='<tr><td class="lbl">'+chrgname+'</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
					}
					else
					{
						htmchrgbrkup+='<tr><td class="lbl">'+chrgname+'</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
					}
				}				

			}
			htmchrgbrkup+='</table>';
			
			$("#cartGSTBody1").html(htmchrgbrkup);
			
			if(fnotid!='')
			{
	    		      	fetchCartPymtGSTNumb(chrgid);
			}
			else
			{			
				$("#btnAddCart").show();
			}

		}
	});
	
}
function fetchCartFrgtChrgDtls(link, chrgid, sttn,sttnname, dvsn,zone,custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, iwowflag, invcdate, cnfmdate, acrldate,amnt)
{	
	$("#cartGSTModal").modal('show');
	$("#btnAddCart").hide();
	$("#cartGSTBody1").html('');
	$("#cartGSTBody").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');
	var htmchrgbrkup='<table class="table table-striped table-bordered table-cart" style="margin-bottom:0px;"><tbody>';

	var htmlhead='<span class="sttn-cart-head">'+sttn+'</span>';
	htmlhead+=' (FREIGHT CHARGES: <i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+')';
	$("#cartGSTHeading").html(htmlhead);

	resetGlblVars();

	G_ChrgId=chrgid;
	G_Sttn=sttn;
	G_SttnName=sttnname;
	G_Dvsn=dvsn;
	G_Zone=zone;
	G_CustCode=custcode;
	G_InvcId=invcid;
	G_FNotId=fnotid;
	G_FNotNumb=fnotnumb;
	G_FNotDate=fnotdate;
	G_CustDmrgId=custdmrgid;
	G_TrxnId=trxnid;
	G_ChrgType=chrgtype;
	G_ChrgTypeDesc="FREIGHT CHARGES";
	G_IWOWFlag=iwowflag;
	G_InvcDate=invcdate;
	G_CnfmDate=cnfmdate;
	G_AcrlDate=acrldate;
	G_Amnt=amnt;
	G_RlyGstin='';
	G_CnsrGstin='';
	G_CnsgGstin='';
	G_HndgAgnt='';


	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CHRG_BREAKUP',Sttn:sttn,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,IWOWFlag:iwowflag,InvcDate:invcdate,Amnt:amnt,RlyGstin:'',CnsrGstin:'',CnsgGstin:'',HndgAgnt:''},
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
				{
					chrgname='WRF Adjustment Amount';
					if(Number(amnt)>0)
					{
						G_WRFAjstFlag='Y';
						G_WRFAjstAmnt=Number(amnt);
					}					
					G_Waiver+=Number(amnt);
				}
				if(chrgname=="Total GST")
					G_GstAmnt+=Number(amnt);
				if(chrgname=="Net Payable Amount")
					G_NetAmnt+=Number(amnt);

				if((chrgname=="Total GST") || (chrgname=="Total Amount") || (chrgname=="Net Payable Amount") || (chrgname=="WRF Adjustment Amount")|| (chrgname=="Amount"))
					htmchrgbrkup+='<tr><td class="lbl">'+chrgname+'</td><td class="val" style="text-align:right;"><i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+'</td></tr>';
			}
			htmchrgbrkup+='</tbody></table>';

   		      $("#cartGSTBody").html(htmchrgbrkup);

    		      fetchCartPymtGSTNumb(chrgid);
		}
	});

}
function addToCart(chrgid, sttn, sttnname, dvsn, zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, cnfmdate, acrldate, amnt,waiver, gst,  netamnt, rlygstin, cnsrgstin, cnsggstin,wrfajstflag, wrfajstamnt, hndgagnt)
{

	var cartIconhtml=$("#divCartIcon").html();
	if(cnsrgstin=='')
	{
		var htmlstr1=$("#cartBodyGSTIN").html();
		var gstin=htmlstr1.substring(htmlstr1.indexOf(":")+1);
		cnsrgstin=gstin.trim();
		cnsggstin=gstin.trim();
		console.log("Sending GSTIN as:"+gstin.trim());
	}
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'ADD_TO_CART', ChrgId:chrgid,Sttn:sttn,SttnName:sttnname, Dvsn:dvsn, Zone:zone,CustCode:custcode,InvcId:invcid,FNOTId:fnotid,FNOTNumb:fnotnumb, FNOTDate:fnotdate, CustDmrgId:custdmrgid,TrxnId:trxnid,ChrgType:chrgtype,ChrgTypeDesc:chrgtypedesc,IWOWFlag:iwowflag,InvcDate:invcdate,CnfmDate:cnfmdate,AcrlDate:acrldate,Amnt:amnt,Waiver:G_Waiver,GstAmnt:G_GstAmnt,NetAmnt:G_NetAmnt,RlyGstin:rlygstin,CnsrGstin:cnsrgstin,CnsgGstin:cnsggstin,WRFAjstFlag:wrfajstflag,WRFAjstAmnt:wrfajstamnt,HndgAgnt:hndgagnt,CustCtgr:GG_SelCustCtgr},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				$("#cartGSTBodyFtr").html('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Cart Update Problem!</strong><br/>'+erormesg+'</div>');
				return false;
			}
			else
			{	if(cartIconhtml.trim()!='')
				{
					successToastFast("Payment Added to the Cart !");
				}

				$('.btn-pay[paychrgid="'+chrgid+'"]').addClass("disabled");
				$('.btn-cart[chrgid="'+chrgid+'"]').addClass("btn-cart-yes").removeClass("btn-cart");

				var htmlstr='';
				if(cartIconhtml.trim()=='')
				{
					htmlstr='<span id="cartGroup"  onclick="showCart();"><img src="/RailSAHAY/resources/images/cart-icon1.jpg" data-toggle="tooltip"  data-placement="bottom" title="Click to view your Payment Cart" class="img-cart"><span class="badge badge-light">1</span></span>';
				}
				else
				{
					var val = parseInt($('#cartGroup').find('.badge').text());
					var newval=val+1;
					htmlstr='<span id="cartGroup" onclick="showCart();"><img src="/RailSAHAY/resources/images/cart-icon1.jpg" data-toggle="tooltip"  data-placement="bottom" title="Click to view your Payment Cart" class="img-cart"><span class="badge badge-light">'+newval+'</span></span>';
				}
				if(cartIconhtml.trim()=='')
				{
					$("#inform-modal").modal('show');
				}	
				$("#divCartIcon").html(htmlstr);
			}

			$("#cartGSTModal").modal('hide');
		}
	});

}
function delFromCart(chrgid)
{
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'DEL_FROM_CART', ChrgId:chrgid},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				failedToast('Problem in Deleting from Cart: '+erormesg);
				return false;
			}
			else
			{
				successToastFast("Payment removed from the Cart !");
				var cartIconhtml=$("#divCartIcon").html();
				var val = parseInt($('#cartGroup').find('.badge').text());
				if(val==1)
				{
					$("#divCartIcon").html('');
				}
				else
				{
					htmlstr='<span id="cartGroup" onclick="showCart();"><img src="/RailSAHAY/resources/images/cart-icon1.jpg" data-toggle="tooltip"  data-placement="bottom" title="Click to view your Payment Cart" class="img-cart"><span class="badge badge-light">'+(val-1)+'</span></span>';

				}
				$("#divCartIcon").html(htmlstr);
			}

		}
	});

}
function fetchCartPymtGSTNumb(chrgid)
{
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'GST_CUST_LIST',CustCode:chrgid},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			console.log(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				$("#cartBodyGSTIN").html('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Failed!</strong> to fetch GSTIN Detail, please try again.</div>');
				return false;
			}

			for(var i=0;i<obj.GstIn.length;i++)
			{
				var crntgstin =obj.GstIn[i];				
				$("#cartBodyGSTIN").html("<hr/>GSTIN:"+crntgstin);
				/*			
				G_CnsrGstin=crntgstin;
				G_CnsgGstin=crntgstin;
				*/
			}
			$("#btnAddCart").show();
		}
	});
}

function addPymtToCart()
{
	addToCart(G_ChrgId, G_Sttn, G_SttnName, G_Dvsn, G_Zone, G_CustCode, G_InvcId, G_FNotId, G_FNotNumb, G_FNotDate,G_CustDmrgId, G_TrxnId, G_ChrgType, G_ChrgTypeDesc, G_IWOWFlag, G_InvcDate, G_CnfmDate, G_AcrlDate, G_Amnt,G_Waiver, G_GstAmnt,G_NetAmnt, G_RlyGstin, G_CnsrGstin, G_CnsgGstin,G_WRFAjstFlag,G_WRFAjstAmnt,G_HndgAgnt);
}
function showCart()
{
	window.location.href="/RailSAHAY/pages/FrgtPymtCart.jsp?PymtStts=INIT";
}
function showDelConfirmationBox(chrgid)
{
	GG_DelChrgId=chrgid;
	$("#confirm-modal").modal('show');
}
function delPymtFromCart()
{
	var chrgid=GG_DelChrgId;
	$("#confirm-modal").modal('hide');

	$("#divAmntDtls").html('<div class="spinner-border text-danger"></div>');
	var myurl="/RailSAHAY/FrgtPymtJson";
	var htmlstr='';
	var htmlPymtSmry='';
	var totlamnt=0;
	var totlwaiver=0;
	var totlgst=0;
	var netamnt=0;
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'DEL_PYMT_FROM_CART', ChrgId:chrgid},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				failedToast('Problem in Deleting from Cart: '+erormesg);
				return false;
			}
			else
			{
				successToastFast("Payment removed from the Cart !");
				if(obj.Data.length==0)
				{
					$("#divPymtList").html('<div align="center"><img src="/RailSAHAY/resources/images/empty-cart.jpg" class="img img-responsive img-fluid" /><h3 class="mb-4 aos-init aos-animate" data-aos="fade-up" data-aos-delay="100">YOUR PAYMENT CART IS EMPTY</h3></div>');
				}
				else 
				{
					for(var i=0;i<obj.Data.length;i++)
					{
						
						totlamnt+=Number(obj.Data[i].Amnt);
						totlwaiver+=Number(obj.Data[i].Waiver);
						totlgst+=Number(obj.Data[i].GstAmnt);
						netamnt+=Number(obj.Data[i].NetAmnt);

						htmlstr+='<div class="cart-pymt-card card">';
						htmlstr+='<div class="card-body">';
						htmlstr+='<table class="table table-cart-pymt">';
						htmlstr+='<tr>';
						htmlstr+='<td style="border-bottom:1px solid #eee;"><i class="fas fa-caret-right"></i>&nbsp;<span class="pymt-type">'+obj.Data[i].ChrgTypeDesc+'</span>&nbsp;&nbsp;<span class="fnot-val" style="font-size:0.8em;font-weight:500;">(Accrued: '+obj.Data[i].AcrlDate+', Raised: '+obj.Data[i].CnfmDate+')</span></td>';
						htmlstr+='<td rowspan="2"><span class="amnt float-right"><i class="fas fa-rupee-sign"></i>&nbsp;'+obj.Data[i].Amnt+'</span></td>';
						htmlstr+='</tr>';
						htmlstr+='<tr>';
						if(obj.Data[i].FNOTNumb=="")
						{
							htmlstr+='<td style="background:#fff5e6;">';
							htmlstr+='<p class="sttn">'+obj.Data[i].SttnName+' ('+obj.Data[i].Sttn+')<span class="dvsn float-right">'+obj.Data[i].Dvsn+'</span></p>';                              				
							htmlstr+='</td>';
						}
						else
						{
							htmlstr+='<td>';
							htmlstr+='<p class="sttn">'+obj.Data[i].SttnName+' ('+obj.Data[i].Sttn+')<span class="dvsn float-right">'+obj.Data[i].Dvsn+'</span></p>';
							htmlstr+='<p class="fnot-dtls"><span class="fnot-lbl">F/Note Detail</span><span class="fnot-val">'+obj.Data[i].FNOTNumb+'/'+obj.Data[i].FNOTDate+'</span></p>';
							htmlstr+='</td>';
						}
						htmlstr+='</tr></table>';		
						htmlstr+='<button class="btn btn-sm btn-warning del-icon" onclick="showDelConfirmationBox(\''+obj.Data[i].ChrgId+'\');"><i class="fas fa-minus"></i></button>';
						htmlstr+='</div><div class="card-footer">';
						htmlstr+='<p class="cust-dtls">'+obj.Data[i].CustCode+' (GSTIN: '+obj.Data[i].CnsrGstin+') <span class="view-dtls-icon float-right" onclick="showChrgGSTBrkUp(this,\''+obj.Data[i].ChrgId+'\');"><i class="fas fa-chevron-down"></i></span></p>';
						htmlstr+='</div></div><div class="collapse" id="div'+obj.Data[i].ChrgId+'"><div class="card card-body chrg-gst-brkup" id="divBody'+obj.Data[i].ChrgId+'"></div></div>';
					}
					$("#divPymtList").html(htmlstr);
				}

					htmlPymtSmry+='<table class="table table-bordered table-pymt-smry">';
			                htmlPymtSmry+='<tr><td class="lbl">Total Amount</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;'+totlamnt+'</td></tr>';
                            		htmlPymtSmry+='<tr><td class="lbl">Waiver(s)/Adjustment(s)</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;'+totlwaiver+'</td></tr>';
                            		htmlPymtSmry+='<tr><td class="lbl">GST Applicable</td><td class="val"><i class="fas fa-rupee-sign"></i>&nbsp;'+totlgst+'</td></tr>';
                            		htmlPymtSmry+='<tr><td class="lbl-net">Net Payable</td><td class="val-net"><i class="fas fa-rupee-sign"></i>&nbsp;'+netamnt+'</td></tr>';
					$("#NetAmnt").val(netamnt);
                        		htmlPymtSmry+='</table>';
					if(netamnt>0)
	                        		htmlPymtSmry+='<button class="btn btn-danger text-white btn-trxn" style="border-radius:0px;width:100%;" onclick="makeCartPayment();">Continue with Payment&nbsp;<i class="fas fa-chevron-right"></i></button>';

					htmlPymtSmry+='<button class="btn btn-secondary text-white btn-trxn" style="border-radius:0px;width:100%;margin-top:15px;" onclick="backDashboard();"><i class="fas fa-chevron-left"></i>&nbsp;Back to Dashboard</button>';
					htmlPymtSmry+='<img src="/RailSAHAY/resources/images/sbibanner.jpg" class="img-responsive img-fluid img mt-3">';
					$("#headPymtCont").html('<span class="badge badge-primary text-white" style="font-size:1.2em;">'+obj.Data.length+'</span>');
					$("#headPymtAmnt").html('<i class="fas fa-rupee-sign"></i>&nbsp;'+netamnt);
					$("#divAmntDtls").html(htmlPymtSmry);
				
			}
			GG_DelChrgId='';
		}
	});
}
function backDashboard()
{
	window.location.href="/RailSAHAY/pages/SAHAYDashboard.jsp";
}
function showChrgGSTBrkUp(link, chrgid)
{
	$('.collapse').collapse('hide');
	$('#div'+chrgid).collapse('toggle');
	$(".view-dtls-icon").html('<i class="fas fa-chevron-down"></i>');
	$('#div'+chrgid).on('shown.bs.collapse', function () {
  		$(link).html('<i class="fas fa-chevron-up"></i>');
	});
	$('#divBody'+chrgid).html('<div class="spinner-border text-danger"></div>');
	var htmchrgbrkup='<p class="chrg-type-gst">Breakup of Charges</p><table class="table table-bordered table-chrgbreak" style="margin-bottom:0px;"><tbody>';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Optn:'CART_CHRG_GST_BREAKUP',ChrgId:chrgid},
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
			htmchrgbrkup+='</table>';
			$('#divBody'+chrgid).html(htmchrgbrkup);
			
		}
	});
}
function processCartForSec(sttn,sttnname,dvsn,zone,chrgid,cust, hndgagnt, hndgagntname, chrgtype, chrgtypecode, chrgtypedesc,trxnid,invcid,fnotid,fnotnumb, fnotdate, custdmrgid, iwowflag, invcdate, invcnumb, cnfmdate, acrldate, amnt,hndgagnt,hndgagntname)
{
	var htmlstr='<table class="table"><tr><td style="border:0px;padding:0px;" colspan="2"><p class="p-gstin" style="margin-block-end:0px;">Select GSTIN <span class="span-gstin">('+hndgagnt+' - '+hndgagntname+')</span></p></td></tr><tr><td class="border:0px;">';
	htmlstr+='<select id="selSecCartGSTIN" class="sel-gstin" style="height:40px;">';

	$("#cartSecGSTModal").modal('show');
	$("#btnAddSecCart").hide();
	$("#cartSecGSTBody").html('');
	$("#cartSecGSTBody1").html('');
	$("#cartSecGSTHeading").html(sttn+' ('+chrgtypedesc+')');
	$("#cartSecGSTBody").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');	
	var rlygstin='';
	var gstin='';
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'HNDG_AGNT_GSTIN',CustCode:hndgagnt,Sttn:sttn},
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
					rlygstin=gstin;
				else
					htmlstr+='<option value="'+gstin+'">'+gstin+'</option>';

                        }
                        htmlstr+='</select>';
			htmlstr+='<input type="hidden" id="SecCartRlyGstIn" name="SecCartRlyGstIn"><input type="hidden" id="SecCartSttn" name="SecCartSttn"><input type="hidden" id="SecCartChrgId" name="SecCartChrgId"><input type="hidden" id="SecCartHndgAgnt" name="SecCartHndgAgnt"></td><td class="border:0px;"><button class="btn btn-info btn-sec-data">Continue&nbsp;<i class="fas fa-chevron-right"></i></button>';
			htmlstr+='<input type="hidden" id="SecCartCustCode" name="SecCartCustCode"><input type="hidden" id="SecCartTrxnId" name="SecCartTrxnId"><input type="hidden" id="SecCartChrgTypeDesc" name="SecCartChrgTypeDesc"><input type="hidden" id="SecCartChrgType" name="SecCartChrgType"><input type="hidden" id="SecCartChrgTypeCode" name="SecCartChrgTypeCode"><input type="hidden" id="SecCartSttnName" name="SecCartSttnName"><input type="hidden" id="SecCartDvsn" name="SecCartDvsn"><input type="hidden" id="SecCartZone" name="SecCartZone"><input type="hidden" id="SecCartInvcId" name="SecCartInvcId"><input type="hidden" id="SecCartFnotId" name="SecCartFnotId"><input type="hidden" id="SecCartFnotNumb" name="SecCartFnotNumb"><input type="hidden" id="SecCartFnotDate" name="SecCartFnotDate"><input type="hidden" id="SecCartCustDmrgId" name="SecCartCustDmrgId"><input type="hidden" id="SecCartIWOWFlag" name="SecCartIWOWFlag"><input type="hidden" id="SecCartInvcDate" name="SecCartInvcDate"><input type="hidden" id="SecCartCnfmDate" name="SecCartCnfmDate"><input type="hidden" id="SecCartAcrlDate" name="SecCartAcrlDate"><input type="hidden" id="SecCartAmnt" name="SecCartAmnt">';
			$("#cartSecGSTBody").html(htmlstr);
			$("#SecCartRlyGstIn").val(rlygstin);
			$("#SecCartSttn").val(sttn);
			$("#SecCartCustCode").val(cust);
			$("#SecCartChrgId").val(chrgid);
			$("#SecCartHndgAgnt").val(hndgagnt);
			$("#SecCartSttnName").val(sttnname);
			$("#SecCartDvsn").val(dvsn);
			$("#SecCartZone").val(zone);
			$("#SecCartChrgType").val(chrgtype);
			$("#SecCartChrgTypeCode").val(chrgtypecode);
			$("#SecCartChrgTypeDesc").val(chrgtypedesc);
			$("#SecCartInvcId").val(invcid);
			$("#SecCartFnotId").val(fnotid);
			$("#SecCartFnotNumb").val(fnotnumb);
			$("#SecCartFnotDate").val(fnotdate);
			$("#SecCartCustDmrgId").val(custdmrgid);
			$("#SecCartIWOWFlag").val(iwowflag);
			$("#SecCartInvcDate").val(invcdate);
			$("#SecCartInvcNumb").val(invcnumb);
			$("#SecCartCnfmDate").val(cnfmdate);
			$("#SecCartAcrlDate").val(acrldate);
			$("#SecCartAmnt").val(amnt);
			$("#SecCartTrxnId").val(trxnid);
		}
	});


}
function fetchCartChrgList()
{
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
        	data: {Optn:'CART_CHRG_LIST'},
		async : true,
		success : function(data) {
        	var obj= JSON.parse(data);
		var bolAvbl='N';
        	for(var i=0;i<obj.ChrgList.length;i++)
        	{
			bolAvbl='N';
			for(var j=0;j<GG_CartPymtArr.length;j++)
			{
				if(GG_CartPymtArr[j]==obj.ChrgList[i].ChrgId)
				{
					bolAvbl='Y';
					break;
				}
			}
			if(bolAvbl=='N')
			{
		        	GG_CartPymtArr.push(obj.ChrgList[i].ChrgId);	
				GG_CartSize++;
			}
        	}
	}
    });
}