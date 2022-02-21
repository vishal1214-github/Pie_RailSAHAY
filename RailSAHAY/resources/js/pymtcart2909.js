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
function showCartChrgDtls(chrgid, sttn, sttnname, dvsn, zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, iwowflag, invcdate,cnfmdate, acrldate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt)
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
	G_HndgAgnt=hndgagnt;


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

	var qryoptn='';
	var qrycust='';
	qryoptn='CUST_GSTIN';
	qrycust=custcode;

	console.log("Query Option:"+qryoptn
	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:qryoptn,CustCode:qrycust,Sttn:sttn},
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
/**Payments for Secondary Customers**/
function showCartSecChrgDtls1(link, chrgid, sttn, sttnname, dvsn,zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate,cnfmdate,acrldate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt)
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

	$("#cartGSTBody").html('<div class="spinner-border text-danger mt-2 ml-2"></div>');

	var htmlstr='<table class="table table-responsive table-cartgst"><tr><td style="border:0;"><select id="selGSTINCart" class="sel-gstin" style="height:auto;padding:8px;width:auto;"><option value="#">Select GSTIN</option>';
	var htmlhead='<span class="sttn-cart-head">'+sttn+'</span>';
	htmlhead+=' ('+chrgtypedesc+': &nbsp;<i class="fas fa-rupee-sign"></i>&nbsp;'+amnt+')';
	$("#cartGSTHeading").html(htmlhead);

	var qryoptn='';
	var qrycust='';
	qryoptn='HNDG_AGNT_GSTIN';
	qrycust=hndgagnt;

	var myurl="/RailSAHAY/FrgtPymtJson";
	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:qryoptn,CustCode:qrycust,Sttn:sttn},
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
			   fetchCartGSTSecChrgDtls(link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin, hndgagnt);
			});
		}
	});

}
function fetchCartGSTSecChrgDtls(link, chrgid, sttn, custcode, invcid, fnotid, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, amnt, rlygstin, cnsrgstin, cnsggstin,hndgagnt)
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

/**Finished block for Secondary Customers**/
function addToCart(chrgid, sttn, sttnname, dvsn, zone, custcode, invcid, fnotid, fnotnumb, fnotdate, custdmrgid, trxnid, chrgtype, chrgtypedesc, iwowflag, invcdate, cnfmdate, acrldate, amnt,waiver, gst,  netamnt, rlygstin, cnsrgstin, cnsggstin,wrfajstflag, wrfajstamnt, hndgagnt)
{

	var cartIconhtml=$("#divCartIcon").html();
	/*
	if(cartIconhtml.trim()=='')
	{
		alert("Please note! Payments of Same Zonal Railway and same Customer can only be added to cart");
	}
	*/
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
							htmlstr+='<p class="sttn">'+obj.Data[i].SttnName+' ('+obj.Data[i].Sttn+')<span class="dvsn float-right">'+obj.Data[i].Dvsn+'/'+obj.Data[i].Zone+'</span></p>';                              				
							htmlstr+='</td>';
						}
						else
						{
							htmlstr+='<td>';
							htmlstr+='<p class="sttn">'+obj.Data[i].SttnName+' ('+obj.Data[i].Sttn+')<span class="dvsn float-right">'+obj.Data[i].Dvsn+'/'+obj.Data[i].Zone+'</span></p>';
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
function makeCartPayment()
{	
	$(".btn-trxn").attr("disabled","disabled");
	$("#frmCartFrgtPymt").submit();
}