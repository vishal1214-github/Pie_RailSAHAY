/*for help*/
$(document).ready(function(){

	$("#txtDvsnCode").autocomplete(gDvarr,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$("#txtPrtyCode").autocomplete(aPrty,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
$("#txtCustCode").autocomplete(aCust,{max:3000,matchContains: true,width: 280}).result(function(evt,item){

         document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	
	$('#gDvarr1').val(gDvarr);
	$('#aPrty1').val(aPrty);
	$('#aCust1').val(aCust);
	
	//var vales=$("#aCust1").val();
	//alert("vales"+vales);
});


function helpdvsnsttn()
{
var dvsncode=""+$("#txtDvsn").val();
var dvsncode1=dvsncode.trim();
//alert("in zonecode1"+zonecode1);

if(dvsncode1==="ADRA")
{
//alert("in adra");
	$("#txtSttn").autocomplete(aDvsnSttnADRA,{max:200,matchContains: true,width: 280}).result(function(evt,item){
	
		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnADRA);
}

if(dvsncode1==="CKP")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnCKP,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnCKP);
}

if(dvsncode1==="KGP")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnKGP,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnKGP);
}

if(dvsncode1==="KUR")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnKUR,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnKUR);
}

if(dvsncode1==="MYS")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnMYS,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnMYS);
}

if(dvsncode1==="RNC")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnRNC,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	$('#gSttn').val(aDvsnSttnRNC);
}

if(dvsncode1==="SBC")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnSBC,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSttn').val(aDvsnSttnSBC);
}
if(dvsncode1==="SBP")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnSBP,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	$('#gSttn').val(aDvsnSttnSBP);
}

if(dvsncode1==="UBL")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnUBL,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	$('#gSttn').val(aDvsnSttnUBL);
}

if(dvsncode1==="WAT")
{
//alert("in adra");
$('#txtSttn').unautocomplete();
	$("#txtSttn").autocomplete(aDvsnSttnWAT,{max:200,matchContains: true,width: 280}).result(function(evt,item){
 	
 		  document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	$('#gSttn').val(aDvsnSttnWAT);
}


return false;
}

function change()
{
$('#txtDvsn').val("");
$('#txtSttn').val("");
	document.getElementById("errmsg").innerHTML="";
    document.getElementById("errmsg").style.display = "none";
return false;
}
function changecoal()
{
$('#txtPrtyCode').val("");
$('#txtSpsrCode').val("");
$('#txtFeldCode').val("");
$('#txtPiltCode').val("");
$('#txtDest').val("");
$('#txtCustCode').val("");
		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
return false;
}

function helpzonedvsn()
{


var zonecode=""+$("#txtZone").val();
var zonecode1=zonecode.trim();
//alert("in zonecode1"+zonecode1);
$('#txtSttn').val("");
if(zonecode==="ECO")
{
//alert("in adra");
	$("#txtDvsn").autocomplete(aZoneDvsnECO,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gDvsn').val(aZoneDvsnECO);
}

if(zonecode==="SE")
{
//alert("in adra");
         
$('#txtDvsn').unautocomplete();
	$("#txtDvsn").autocomplete(aZoneDvsnSE,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";	
		 
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gDvsn').val(aZoneDvsnSE);
}

if(zonecode==="SW")
{
//alert("in adra");
$('#txtDvsn').unautocomplete();
	$("#txtDvsn").autocomplete(aZoneDvsnSW,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gDvsn').val(aZoneDvsnSW);
}



return false;
}


function helppit()
{
var dvsncode=""+$("#txtDvsnCode").val();
var dvsncode1=dvsncode.trim();
if(dvsncode1==="ADRA")
{
//alert("in adra");
	$("#txtPiltCode").autocomplete(aPitADRA,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	
$('#gPit').val(aPitADRA);

}
else if(dvsncode1==="ASN")
{
//alert("in adra");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitASN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitASN);
}
else if(dvsncode1==="BSP")
{
//alert("in adra");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitBSP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 		
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitBSP);
}
else if(dvsncode1==="CKP")
{
//alert("in adra");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitCKP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitCKP);
}
else if(dvsncode1==="DHN")
{
//alert("in adra");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitDHN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitDHN);
}
//else if(dvsncode1.localeCompare('DHN'))
else if(dvsncode1==="HWH")
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitHWH,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitHWH);
}
else if(dvsncode1==="KGP")
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitKGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitKGP);
}
else if(dvsncode1==="KUR")
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitKUR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitKUR);
}
else if(dvsncode1==='MLDT')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitMLDT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitMLDT);
}
else if(dvsncode1==='NAG')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitNAG,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitNAG);
}
else if(dvsncode1==='NGP')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitNGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitNGP);
}
else if(dvsncode1==='R')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitR);
}
else if(dvsncode1==='RNC')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitRNC,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitRNC);
}
else if(dvsncode1==='SBP')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitSBP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gPit').val(aPitSBP);
}
else if(dvsncode1==='WAT')
{
//alert("in DHN");
$('#txtPiltCode').unautocomplete();
	$("#txtPiltCode").autocomplete(aPitWAT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gPit').val(aPitWAT);
}
else 
{
//alert("in last");
			$('#txtPiltCode').unautocomplete();
			$('#txtPiltCode').val("");
			document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		$('#gPit').val("");	
}


return false;
}


function helpcoal()
{
var dvsncode=""+$("#txtDvsnCode").val();
var dvsncode1=dvsncode.trim();
if(dvsncode1==="ADRA")
{
//alert("in adra");
	$("#txtFeldCode").autocomplete(aCoalFldADRA,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
		document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	
$('#gCoal').val(aCoalFldADRA);

}
else if(dvsncode1==="ASN")
{
//alert("in adra");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldASN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	  document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldASN);
}
else if(dvsncode1==="BSP")
{
//alert("in adra");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldBSP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldBSP);
}
else if(dvsncode1==="CKP")
{
//alert("in adra");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldCKP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldCKP);
}
else if(dvsncode1==="DHN")
{
//alert("in adra");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldDHN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldDHN);
}
//else if(dvsncode1.localeCompare('DHN'))
else if(dvsncode1==="HWH")
{
//alert("in DHN");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldHWH,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldHWH);
}
else if(dvsncode1==="KGP")
{
//alert("in DHN");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldKGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldKGP);
}
else if(dvsncode1==="KUR")
{
//alert("in DHN");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldKUR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldKUR);
}
else if(dvsncode1==='MLDT')
{
//alert("in DHN");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldMLDT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldMLDT);
}
else if(dvsncode1==='NAG')
{
//alert("in DHN");
$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldNAG,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldNAG);
}
else if(dvsncode1==='NGP')
{

$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldNGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldNGP);
}
else if(dvsncode1==='R')
{

$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	
 	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldR);
}
else if(dvsncode1==='RNC')
{

$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldRNC,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldRNC);
}
else if(dvsncode1==='SBP')
{

$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldSBP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gCoal').val(aCoalFldSBP);
}
else if(dvsncode1==='WAT')
{

$('#txtFeldCode').unautocomplete();
	$("#txtFeldCode").autocomplete(aCoalFldWAT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		 
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gCoal').val(aCoalFldWAT);
}
else 
{
//alert("in last");
			$('#txtFeldCode').unautocomplete();
			$('#txtFeldCode').val("");
		$('#gCoal').val("");	
		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
}


return false;
}

function helpspsr()
{
var dvsncode=""+$("#txtDvsnCode").val();
var dvsncode1=dvsncode.trim();
if(dvsncode1==="ADRA")
{
//alert("in adra");
	$("#txtSpsrCode").autocomplete(aSpsrADRA,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
	 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	
$('#gSpsr').val(aSpsrADRA);

}
else if(dvsncode1==="ASN")
{
//alert("in adra");
$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrASN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrASN);
}
else if(dvsncode1==="BSP")
{
//alert("in adra");
$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrBSP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 	document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrBSP);
}
else if(dvsncode1==="CKP")
{
//alert("in adra");
$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrCKP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrCKP);
}
else if(dvsncode1==="DHN")
{
//alert("in adra");
$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrDHN,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrDHN);
}
//else if(dvsncode1.localeCompare('DHN'))
else if(dvsncode1==="HWH")
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrHWH,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrHWH);
}
else if(dvsncode1==="KGP")
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrKGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrKGP);
}
else if(dvsncode1==="KUR")
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrKUR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrKUR);
}
else if(dvsncode1==='MLDT')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrMLDT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrMLDT);
}
else if(dvsncode1==='NAG')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrNAG,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	    document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrNAG);
}
else if(dvsncode1==='NGP')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrNGP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrNGP);
}
else if(dvsncode1==='R')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrR,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	
 	     document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrR);
}
else if(dvsncode1==='RNC')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrRNC,{max:35,matchContains: true,width: 280}).result(function(evt,item){
 	
 	
 	      document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrRNC);
}
else if(dvsncode1==='SBP')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrSBP,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
$('#gSpsr').val(aSpsrSBP);
}
else if(dvsncode1==='WAT')
{

$('#txtSpsrCode').unautocomplete();
	$("#txtSpsrCode").autocomplete(aSpsrWAT,{max:35,matchContains: true,width: 280}).result(function(evt,item){
	
	 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";
 	
		if(item!=null && item.length==1){
			var tar=item[0].split("-");
		evt.target.value=tar[1];
		}
	});
	
	$('#gSpsr').val(aSpsrWAT);
}
else 
{
		$('#txtSpsrCode').unautocomplete();
		$('#txtSpsrCode').val("");
		$('#gSpsr').val("");
		 document.getElementById("errmsg").innerHTML="";
		 document.getElementById("errmsg").style.display = "none";	
}


return false;
}
function vldtInput (str, strArray) 
		{
		var array1 = strArray.split(",");		
		for (var j=0; j<array1.length; j++)
		    {
		        if (array1[j].indexOf(str) != -1) 
		        	return j;
		    }
		    return -1;
		    
		}
	$(document).ready(function(){
	
	$("#submitForm").click(function(e){	
		e.preventDefault();
		   var formname = $(this).closest('form').attr('name');
		    var langFlag=document.getElementById('hidLangFlagDiv').value;
		   //alert("langFlag"+langFlag);
		 if (formname=="FWP_IRONORE") /*IRON ORE INDENTS*/
	      {
	       var dvsncode=$("#txtDvsn").val().toUpperCase();
	       var zonecode=$("#txtZone").val().toUpperCase();
	       var sttncode=$("#txtSttn").val();
	       var fd = $("#txtDate").val();
	       var cd = $("#Cday").val();
	      // alert("dvsncode"+dvsncode);
	      var dvsnarr=$("#gDvsn").val();
	       var sttnarr=$("#gSttn").val();
	     // alert("dvsnarr"+dvsnarr);
	     
	     		if (compareDate(cd,fd)<0)
				      {
				      	//alert("Date Can Not Be Greater"+cd);				      	
				      	if(langFlag=="H")
						{
					        document.getElementById("errmsg").innerHTML = "तिथि निर्धारित तिथि से अधिक न हो: निर्धारित तिथि - "+cd;
					        document.getElementById("errmsg").style.display = "block";
						}
						else
						{
					        document.getElementById("errmsg").innerHTML = "Date Can Not Be Greater  "+cd;
					        document.getElementById("errmsg").style.display = "block";
						}
				      	$('#txtDate').focus();
				       top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
				       }
				       else
				       {	      
				      if( $("#txtDvsn").val().length == 0 )
							{
							 document.getElementById("errmsg").innerHTML = "";
					        document.getElementById("errmsg").style.display = "none";
							     FWP_IRONORE.submit();
							}  
			    			else
			    			{
			    			var valueoffunc=vldtInput(dvsncode,dvsnarr);
				     			//alert("valueoffunc"+valueoffunc);
							      if(valueoffunc === -1)
									{
										//alert("Please Enter A Valid Divsion");
										
									if(langFlag=="H")
									{
								        document.getElementById("errmsg").innerHTML = "कृपया एक मान्य मण्डल कोड भरें  ";
								        document.getElementById("errmsg").style.display = "block";
									}
									else
									{
								        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Divsion  ";
								        document.getElementById("errmsg").style.display = "block";
									}
										$('#txtDvsn').focus();
										 
										top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
										return false;
									}
									else{
									
									if( $("#txtSttn").val().length == 0 )
										{
										 document.getElementById("errmsg").innerHTML = "";
					                     document.getElementById("errmsg").style.display = "none";
							     			FWP_IRONORE.submit();
										} 
										else 
										{
											var valueoffunc=vldtInput(sttncode,sttnarr);
											if(valueoffunc === -1)
											{
												//alert("Please Enter A Valid Station");
												
											if(langFlag=="H")
											{
										        document.getElementById("errmsg").innerHTML = "कृपया एक मान्य स्टेशन कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Station  ";
										        document.getElementById("errmsg").style.display = "block";
											}
									
												$('#txtSttn').focus();							 
												top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
												return false;
											}
											else{
											 document.getElementById("errmsg").innerHTML = "";
					        				document.getElementById("errmsg").style.display = "none";
											FWP_IRONORE.submit();
											}
										}
									
									}
						
		    			}
		    			}
	      
	      }
		 if (formname=="FWP_RasCoAlmt")  /*ARREAR OF ALLOTMENTS ODR WISE*/
	      {
	      		var flag1="Y"
	      		var sttn=$("#txtDvsnCode").val();
	      		var sttn=sttn.toUpperCase();
	      		var prty=$("#txtPrtyCode").val();
	      		var custcode=$("#txtCustCode").val();
	      		var spsr=$("#txtSpsrCode").val();
	      		var coalfld=$("#txtFeldCode").val();
	      		var coalpit=$("#txtPiltCode").val();
	      		
	      		
	      		 var dvsnarr=$("#gDvarr1").val();
	      		 var prtyarr=$("#aPrty1").val();
	      		 var custcodearr=$("#aCust1").val();
	      		 var spsrarr=$("#gSpsr").val();
	      		 var coalfldarr=$("#gCoal").val();
	      		 var coalpitarr=$("#gPit").val();
	      		 
	      		 
				//alert("spsrarr"+spsrarr);     
	     
	      
			if( $("#txtDvsnCode").val().length == 0 )
				{		           
				       flag1="N";
				       //alert("Please Fill The Division");
				       
				       	if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "मण्डल प्रविष्ट करना अनिवार्य है";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Division is Mandatory";
										        document.getElementById("errmsg").style.display = "block";
											}
											
				       $('#txtDvsnCode').focus();
				       return false;
				}  
    			else
    			{
    			    var valueoffunc=vldtInput(sttn,dvsnarr);
	     			//alert("valueoffunc"+valueoffunc);
				      if(valueoffunc == -1)
						{
							//alert("Please Enter A Valid Divsion");
								if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य मण्डल कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Division ";
										        document.getElementById("errmsg").style.display = "block";
											}
							$('#txtDvsnCode').focus();
							 
							top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
							return false;
						}
						else   
	      				{
	      				 document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
				      	  FWP_RasCoAlmt.submit();
		 	 			}
    			}
   
    		if ($("#txtPrtyCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc1=vldtInput(prty,prtyarr);
		         if(valueoffunc1 == -1)
					{
					//alert("Please Enter A Valid Priority");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्राथमिकता भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Priority ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
					$('#txtPrtyCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt.submit();
					}
	      		
		      }	 
	     	 
	     	 if ($("#txtCustCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc1=vldtInput(custcode,custcodearr);
		         if(valueoffunc1 == -1)
					{
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य ग्राहक कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Customer ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
					//alert("Please Enter A Valid Customer");
					$('#txtCustCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt.submit();
					}
	      		
		      }	
		      if ($("#txtSpsrCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(spsr,spsrarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid SponSor");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्रायोजक कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid SponSor ";
										        document.getElementById("errmsg").style.display = "block";
											}
					$('#txtSpsrCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 			document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt.submit();
					}
	      		
		      }	
		      if ($("#txtFeldCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(coalfld,coalfldarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid coalField");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य कोलफील्ड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid coalField ";
										        document.getElementById("errmsg").style.display = "block";
											}
					$('#txtFeldCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt.submit();
					}
	      		
		      }
		      if ($("#txtPiltCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(coalpit,coalpitarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid Pilot");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य पायलट कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Pilot ";
										        document.getElementById("errmsg").style.display = "block";
											}
					$('#txtPiltCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt.submit();
					}
	      		
		      }		
	      }
	      if (formname=="FWP_RasCoAlmt30")  /*ARREAR OF ALLOTMENTS MORE THAN 30 DAYS*/
	      {
	      		var flag1="Y"
	      		var sttn=$("#txtDvsnCode").val();
	      		var sttn=sttn.toUpperCase();
	      		var prty=$("#txtPrtyCode").val();
	      		var custcode=$("#txtCustCode").val();
	      		var spsr=$("#txtSpsrCode").val();
	      		var coalfld=$("#txtFeldCode").val();
	      		var coalpit=$("#txtPiltCode").val();
	      		
	      		
	      		 var dvsnarr=$("#gDvarr1").val();
	      		 var prtyarr=$("#aPrty1").val();
	      		 var custcodearr=$("#aCust1").val();
	      		 var spsrarr=$("#gSpsr").val();
	      		 var coalfldarr=$("#gCoal").val();
	      		 var coalpitarr=$("#gPit").val();
	      		 
	      		 
				//alert("spsrarr"+spsrarr);     
	     
	      
			if( $("#txtDvsnCode").val().length == 0 )
				{		           
				       flag1="N";
				       //alert("Please Fill The Division");
				       
				       if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "मण्डल प्रविष्ट करना अनिवार्य है ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Division is Mandatory ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
				       $('#txtDvsnCode').focus();
				       return false;
				}  
    			else
    			{
    			    var valueoffunc=vldtInput(sttn,dvsnarr);
	     			//alert("valueoffunc"+valueoffunc);
				      if(valueoffunc == -1)
						{
							//alert("Please Enter A Valid Divsion");
							
							if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य मण्डल कोड भरें ";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Division ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
							$('#txtDvsnCode').focus();
							 
							top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
							return false;
						}
						else   
	      				{
	      				document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
				      	  FWP_RasCoAlmt30.submit();
		 	 			}
    			}
   
    		if ($("#txtPrtyCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc1=vldtInput(prty,prtyarr);
		         if(valueoffunc1 == -1)
					{
					//alert("Please Enter A Valid Priority");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्राथमिकता भरें";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Priority ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
					$('#txtPrtyCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt30.submit();
					}
	      		
		      }	 
	     	 
	     	 if ($("#txtCustCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc1=vldtInput(custcode,custcodearr);
		         if(valueoffunc1 == -1)
					{
					//alert("Please Enter A Valid Customer");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य ग्राहक कोड भरें";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Customer ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
					$('#txtCustCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt30.submit();
					}
	      		
		      }	
		      if ($("#txtSpsrCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(spsr,spsrarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid SponSor");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्रायोजक कोड भरें";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid SponSor ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
					$('#txtSpsrCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt30.submit();
					}
	      		
		      }	
		      if ($("#txtFeldCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(coalfld,coalfldarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid coalField");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य कोलफील्ड भरें";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid coalField ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
											
					$('#txtFeldCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt30.submit();
					}
	      		
		      }
		      if ($("#txtPiltCode").val().length == 0)
		      	{	
		      	}  
		      else
		      {
		      
		       var valueoffunc2=vldtInput(coalpit,coalpitarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid Pilot");
					
					if(langFlag=="H"){
				          						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य पायलट कोड भरें";
										        document.getElementById("errmsg").style.display = "block";
											}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Pilot ";
										        document.getElementById("errmsg").style.display = "block";
											}
											
											
					$('#txtPiltCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else   
   					{
   					document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
      				FWP_RasCoAlmt30.submit();
					}
	      		
		      }		
	      }
	      
	      	 if (formname=="FWP_RasIoRake")/*rake allocation plan*/
	      		{
	        	  	
	     		var fd = $("#dateFrom").val();
	     		var td = $("#dateTo").val();
	     		var cd = $("#Cday").val();
	     		if (compareDate(td,fd)<0)
	     		{
	     		 //alert("To Date should be Greater than From Date!!");
	     		 
	     		 if(langFlag=="H"){
	     		 						 document.getElementById("errmsg").innerHTML = "समाप्त तिथि प्रारम्भ तिथि से अधिक होनी चाहिए";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "To Date should be Greater than From Date";
				 								document.getElementById("errmsg").style.display = "block";
											}
											
											
	     		 
			     top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
			     }
			     else
			     {
			     	if (compareDate(cd,td)<0)
				      {
				     // alert("To Date should be Greater than"+cd);
				     
				      if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "समाप्त तिथि निर्धारित तिथि से अधिक न हो: निर्धारित तिथि  "+cd;
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "To Date should not be Greater than  "+cd;
				 							document.getElementById("errmsg").style.display = "block";
											}
											
				      	
				       top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
				       }
				       else
				       {
				       document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
				          FWP_RasIoRake.submit();
				        }  
			     }
	       
	       
	      }
	 
	      if (formname=="FWP_RasCoal") /*coal allotment*/
	      {
	      //alert("222");
	        	var dvsn = $("#txtDvsnCode").val();
	        	var prty = $("#txtPrtyCode").val();
	        	var cust = $("#txtCustCode").val();
	        	var fd = $("#txtDateFrom").val();
	     		var td = $("#txtDateTo").val();
	     		var cd = $("#Cday").val();
	     			      		
	      		
	      		 var dvsnarr=$("#gDvarr1").val();
	      		 var prtyarr=$("#aPrty1").val();
	      		 var custarr=$("#aCust1").val();
	      		 //alert("dvsnarr"+dvsnarr);
	      		 
	        	if ( dvsn.length == 0 )
	        	{
	        	//alert("Please Enter Division");
	        	
	        	
	        	 if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "मण्डल प्रविष्ट करना अनिवार्य है";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Division is Mandatory";
				 							document.getElementById("errmsg").style.display = "block";
											}
	        	$("#txtDvsnCode").focus();
	        	 top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
	        	 return false;
	        	}
	        	 else
				    {
				    
				    var valueoffunc2=vldtInput(dvsn,dvsnarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid DvsnCode");
					
					
					if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य मण्डल कोड भरें";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid DvsnCode";
				 							document.getElementById("errmsg").style.display = "block";
											}
											
					$('#txtDvsnCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
				else 
				{
				
				if ( prty.length == 0 )
	        	{
	        	//alert("Please Enter Priority");
	        	
	        	if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्राथमिकता कोड भरें";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter Priority";
				 							document.getElementById("errmsg").style.display = "block";
											}
											
	        	$("#txtPrtyCode").focus();
	        	 top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
	        	 return false;
	        	 
	        	}
	        	else{  
				       
				    var valueoffunc2=vldtInput(prty,prtyarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid Priority Code");
					
					if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य प्राथमिकता कोड भरें";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Priority Code";
				 							document.getElementById("errmsg").style.display = "block";
											}
					$('#txtPrtyCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
					
					
			else {
			
			
			
			 var valueoffunc2=vldtInput(cust,custarr);
		         if(valueoffunc2 == -1)
					{
					//alert("Please Enter A Valid Customer Code");
					
					if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "कृपया एक मान्य ग्राहक कोड भरें";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "Please Enter A Valid Customer Code";
				 							document.getElementById("errmsg").style.display = "block";
											}
					$('#txtCustCode').focus();
					 
					top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
					return false;
					
					}
					
					
			else 
			{
			if (compareDate(td,fd)<0)
	     		{
	     		// alert("To Date should be Greater than From Date!!");
	     		 
	     		 if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "समाप्त तिथि प्रारम्भ तिथि से अधिक होनी चाहिए";
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "To Date should be Greater than From Date";
				 							document.getElementById("errmsg").style.display = "block";
											}
											
											
			     top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
			     return false;
			     //alert("12");
			     }
			     else
			     {
			     	if (compareDate(cd,td)<0)
				      {
				      //alert("To Date should be Greater than"+cd);
				      
				       if(langFlag=="H"){
	     		 						document.getElementById("errmsg").innerHTML = "समाप्त तिथि निर्धारित तिथि से अधिक न हो: निर्धारित तिथि"+cd;
				 						document.getElementById("errmsg").style.display = "block";
									}
											else
											{
										        document.getElementById("errmsg").innerHTML = "To Date should not be Greater than"+cd;
				 							document.getElementById("errmsg").style.display = "block";
											}
				       top.frames['frmDtls'].location.href = '/FOISWebPortal/pages/FWP_Blank.jsp';
				       return false;
				       }
				       else
				       {
				       document.getElementById("errmsg").innerHTML="";
		 				 document.getElementById("errmsg").style.display = "none";
				          FWP_RasCoal.submit();
				        }  
			     }
			     }
			     }
				 }     
				}         
			}  
	      }
	      
	     
	      
	     });
});


