
$(document).ready(function(){
	$(window).scroll(function () {
			if ($(this).scrollTop() > 50) {
				$('#back-to-top').fadeIn();
				$('#cntFinder').html('<i class="far fa-comments"></i>');
				$('.btn-register').hide();
			} else {
				$('#back-to-top').fadeOut();
				$('#cntFinder').html('<p class="cntftrlink"><i class="far fa-comments"></i>&nbsp;Contact Finder</p>');
				$('.btn-register').show();
			}
		});
		// scroll body to 0px on click
		$('#back-to-top').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 400);
			return false;
		});

		// scroll body to 0px on click
		$('#cntFinder').click(function () {
			$("#cntModal").modal('show');
		});

});
function noDataFound()
{
      cuteToast({
      type: "success",
      message: "Sorry! No Data found for specified input criteria",
      timer: 5000
    })
}
function custPrompt(msg)
{
      cuteToast({
      type: "success",
      message: msg,
      timer: 5000
    })
}


function openPDF(tag)
{
	if(tag=='AFTO')
	{
		url="/RailSAHAY/resources/pdf/AFTO.pdf";
	}
	if(tag=='GPWIS')
	{
		url="/RailSAHAY/resources/pdf/GPWIS.pdf";
	}

	window.open(url,'pdflink','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}
var fontSizeChng=0;

function gotopage(pagen)
{
   var url="/RailSAHAY/Home.jsp";
   document.getElementById("toPage").value=pagen;
   $('#frmTheme').attr('action', url);
   $('#frmTheme').submit();
}
function showPage(page)
{
if(page=='POLICY')
{

	gotopage('Policy');
	//location.href="/RailSAHAY/pages/Policy.jsp";
}
if(page=='SMART')
{
	gotopage('SMART');
	//location.href="/RailSAHAY/pages/SMART.jsp";
}
if(page=='NODL_OFCR')
{
	gotopage('NodlOfcrList');
	//location.href="/RailSAHAY/pages/NodlOfcrList.jsp";
}
if(page=='RATE_SLAB')
{
	gotopage('RateSlab');
	//location.href="/RailSAHAY/pages/RateSlab.jsp";
}
if(page=='CONCERN')
{
	gotopage('LodgeConcern');
	//location.href="/RailSAHAY/pages/LodgeConcern.jsp";
}
if(page=='TIME_TABLED')
{
	gotopage('TimeTblTran');
	//location.href="/RailSAHAY/pages/TimeTblTran.jsp";
}
if(page=='OWN_TRML')
{
	gotopage('OwnTrmlGCT');
	//location.href="/RailSAHAY/pages/OwnTrmlGCT.jsp";
}
if(page=='OWN_WGON')
{
	gotopage('OwnWgonNew');
	//location.href="/RailSAHAY/pages/OwnWgonNew.jsp";
}
if(page=='TRANSPORT_WITH_US')
{
	gotopage('BkngRegProcess');
	//location.href="/RailSAHAY/pages/BkngRegProcess.jsp";
}
if(page=='BKNG_PROCESS')
{
	gotopage('BkngRegProcess');
	//location.href="/RailSAHAY/pages/BkngRegProcess.jsp";
}
if(page=='FRGT_CALC')
{
	gotopage('FrgtCalc');
	//location.href="/RailSAHAY/pages/FrgtCalc.jsp";
}
if(page=='SCREEN_READER')
{
	gotopage('ScreenReader');
	//location.href="/RailSAHAY/pages/ScreenReader.jsp";
}
if(page=='WIM')
{
	gotopage('Feedback');
	//location.href="/RailSAHAY/pages/Feedback.jsp";
}
if(page=='CONTACT_US')
{
	gotopage('TrmlDashboard');
	//location.href="/RailSAHAY/pages/TrmlDashboard.jsp";
}
if(page=='REG_SEC_CUST')
{
	gotopage('RgstSecCust');
	//location.href="/RailSAHAY/pages/TrmlDashboard.jsp";
}
if(page=='TRML_DASHBOARD')
{
	gotopage('TrmlDashboard');
	//location.href="/RailSAHAY/pages/TrmlDashboard.jsp";
}
if(page=='TRACK_TRACE')
{
	gotopage('FNREnquiryIN');
	//location.href="/RailSAHAY/pages/FNREnquiryIN.jsp";
}
if(page=='FIND_WGON')
{
	gotopage('FindWgon');
}
if(page=='RAISE_REQ')
{
	gotopage('RaiseRequest');
}
if(page=='RAISE_REQ_GCT')
{
	gotopage('RaiseRequestGCT');
}

if(page=='TRACK_REQ')
{
	gotopage('TrackRequest');
}
if(page=='BUSINESS_PARTNER')
{
	gotopage('BusinessPartner');
}
if(page=='MINI_RAKE')
{
	gotopage('MiniRake');
}
if(page=='ML_POINT')
{
	gotopage('TwoPtCombi');
}
if(page=='TRANSPORT_SERVICES')
{
	gotopage('TransportServices');
}
if(page=='STTN_HELP')
{
	gotopage('SttnList');
}
if(page=='IND_LOGIN')
{
	gotopage('eDmndLogin');
}
if(page=='CMDT_WGON_CTLG')
{
	gotopage('FindCmdtWgon');
}
if(page=='CMDT_RATE_SLAB')
{
	gotopage('CmdtRateSlab');
}
if(page=='CMDT_FRGT_CALC')
{
	gotopage('FindCmdtWgonFrgtCalc');
}
if(page=='CMDT_REBT_SCHM')
{
	gotopage('CmdtRebtSchm');
}
if(page=='CMDT_TRML_SRCH')
{
	gotopage('CmdtTrmlDashboard');
}
if(page=='CMDT_ML_POINT')
{
	gotopage('CmdtTwoPtCombi');
}
if(page=='HOME')
{
	location.href="/RailSAHAY/index.jsp";
}
if(page=='ODR_RAS')
{
	gotopage('RASODRHome');
}
if(page=='COAL_ALOT')
{
	gotopage('RASCOALAlotment');
}
if(page=='COAL_ALOT_ODR')
{
	gotopage('RASCOALAlotmentOdr');
}
if(page=='COAL_ALOT_30')
{
	gotopage('RASCOALAlotmentthrty');
}
if(page=='IORE_INDT')
{
	gotopage('RASIOREIndt');
}
if(page=='STTN_OTSG_DMND')
{
	gotopage('RASIOREOtsgIndt');
}
if(page=='RAKE_ALCN_PLAN')
{
	gotopage('RASRakeAlcnPlan');
}
if(page=='RAKE_ALOT')
{
	gotopage('RASIORakeAlotment');
}
if(page=='ODR_INDT')
{
	gotopage('ODROtsgDtls');
}
if(page=='MATURED_INDT')
{
	gotopage('ODROtsgDtls');
}
if(page=='TAX_INVC')
{
	gotopage('GSTTaxInvc');
}
if(page=='FRGT_PYMT_HELP')
{
	gotopage('FrgtPymtHelp');
}
if(page=='PRCL_TRAN')
{
	gotopage('PrclTranMvmt');
}
if(page=='GCT_ELGB')
{
	gotopage('GCTElgb');
}


}
function showIndustry(cmdt)
{
	gotopage('Industry');
}
function initFunction()
{
	console.log("Page Initialized");
}
function gotoRailMadad()
{
	/*window.open('https://railmadad.indianrailways.gov.in/madad/final/home.jsp','railmadad','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
	location.href="/RailSAHAY/pages/RailMadad.jsp";*/
	$("#frmRailMadad").submit();
}
function gotoECust()
{
	window.open('https://www.fois.indianrail.gov.in/ecbs/JSP/Login_SHY.jsp','ecust','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}
function gotoeDmnd()
{
	window.open('https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/Login_SH.jsp?TH=RD','edmnd','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}

function regUser()
{
	window.open('https://www.foistest.indianrail.gov.in/foisEfnote/OpenPages/RgtrUserN.jsf','reguser','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
}

function gotoParcel()
{
		$("#frmParcel").submit();
}
function openExtnLink(linkownr, url)
{
     cuteAlert({
      type: "question",
      title: linkownr,
      message: "This action will take you to an external <b>("+linkownr+")</b> website",
      confirmText: "Continue",
      cancelText: "Cancel"
  }).then((e)=>{
    if ( e == ("confirm")){
	window.open(url,'extnlink','fullscreen=yes,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,width='+screen.availWidth+',height='+screen.availHeight);
  } else {
    return false;
  }
  });
}
function openPopUpContent(url)
{
	var htmlstr='<img src='+url+' class="img-responsive" />';
	$("#popUpModal").modal('show');
	$("#divPopUp").html(htmlstr);
}

$(document).ready(function() {
$(".theme-dropdown a").click(function(){
  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
});
$(".lang-dropdown a").click(function(){
  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
});
});

function incFontSize()
{
    $("div").children().each(function() {
      var size = parseInt($(this).css("font-size"));
      size = size + 1 + "px";
      $(this).css({
        'font-size': size
      });
    });
    fontSizeChng+=1;
}
function decFontSize()
{
    $("div").children().each(function() {
      var size = parseInt($(this).css("font-size"));
      size = (size -1) + "px";
      $(this).css({
        'font-size': size
      });
    });
    fontSizeChng-=1;
}
function setOrigFontSize()
{
    $("div").children().each(function() {
      var size = parseInt($(this).css("font-size"));
      size = (size -fontSizeChng) + "px";
      $(this).css({
        'font-size': size
      });
    });
    fontSizeChng=0;
}
$(function()
{

	  $("#selLang a").click(function(){
	var lang=$(this).attr("lang");
	document.getElementById('txtLangFlag').value=lang;
   setLangFlag(lang);
      $("#txtLangFlag").val(lang);
	var pg=$("#toPage1").val();
	$("#toPage").val(pg);
     var url="";
     if(pg=="Home"){
        url= "/RailSAHAY/index.jsp";
    	}
    	else{
        url= "/RailSAHAY/Home.jsp";
    	}
      $('#frmTheme').attr('action', url);
      $('#frmTheme').submit();
   });
});
$(function()
{
	  $("#selTheme a").click(function(e){
	  e.preventDefault();
	  var theme=$(this).attr("theme");
	 $('#txtTheme').val(theme);
      setActlTheme(theme);
      //var url=window.location.href;
	var pg=$("#toPage1").val();
	$("#toPage").val(pg);

     var url="";
    // alert("Page:"+pg);
     if(pg=="Home"){
        url= "/RailSAHAY/index.jsp";
    	}
    	else{
        url= "/RailSAHAY/Home.jsp";
    	}
      $('#frmTheme').attr('action', url);
      $('#frmTheme').submit();
   });
});
$(document).ready(function(){
setTheme("BL");
});
function setTheme(colr)
{
	/*session.setAttribute("themeColor",colr)....using Ajax*/
	var colr1=$("#txtTheme").val();
	if(colr=="GR")
	{
		$(".navbar-inverse").css("background-color","#009933");
		$("#divBG1").css("background-color","#009933");
		$("#divBG2").css("background-color","#009933");
		$(".nav-stacked").find('a:first').css("background-color","#009933");
		$(".nav-tabs").find('a:first').css("color","#000");
	}
	if(colr=="RD")
	{

		$(".navbar-inverse").css("background-color","#ff4d4d");
		$("#divBG1").css("background-color","#ff4d4d");
		$("#divBG2").css("background-color","#ff4d4d");
		$(".nav-stacked").find('a:first').css("background-color","#ff4d4d");
		$(".nav-tabs").find('a:first').css("color","#000");
	}
	if(colr=="GY")
	{

		$("#divBG1").css("background-color","#999999");
		$("#divBG2").css("background-color","#999999");
		$(".nav-stacked").find('a:first').css("background-color","#999999");
		$(".nav-tabs").find('a:first').css("color","#000");
	}
	if(colr=="BL")
	{


		$(".navbar-inverse").css("background-color","#00446d");
		$(".sidenav").css("background-color","#00446d");
		$("#divBG1").css("background-color","#b3d9ff");
		$("#divBG2").css("background-color","#b3d9ff");
		$(".nav-stacked").find('a:first').css("background-color","#337ab7");
		$(".nav-tabs").find('a:first').css("color","#fff");
	}
	if(colr1=="OR")
	{

		$(".navbar-inverse").css("background-color","#e68a00");
		$(".sidenav").css("background-color","#e68a00");
		$("#divBG1").css("background-color","#ffb84d");
		$("#divBG2").css("background-color","#ffb84d");
		$("#divAbtFOIS").css("background-color","#ffdd99");
		$(".nav-stacked").find('a:first').css("background-color","#e68a00");
		$(".nav-tabs").find('a').css("color","#000");
	}
}

function DsttNodlOffcr(dstt){

    dstt=dstt.toUpperCase();
	var htmlstr='<div class="w3-container">';
        htmlstr+='<div class="w3-card-5"><table class="styletable">';
	htmlstr+='<tr><th>Division</th><th>Contact Person</th></tr>';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_LIST&Sttn="+locn;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'NODL_OFCR_DSTT',Dstt:dstt},
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


                        if(obj.NodlOfcr.length==0)
			{
				$("#divCntData").html('<div class="alert alert-danger" role="alert"><p class="inpt-head">Sorry! No Contact Person is available for specified location<p></div>');
				return false;
			}
			for(var i=0;i<obj.NodlOfcr.length;i++)
			{



				var dvsn=obj.NodlOfcr[i].Dvsn;
				var Ofcr=obj.NodlOfcr[i].Ofcr;
				var Mob=obj.NodlOfcr[i].Mob;
				var LL=obj.NodlOfcr[i].LL;

                                /*htmlstr+='<tr><td>'+dvsn+'</td><td>'+Ofcr+'<br/><i class="fas fa-mobile-alt"></i>&nbsp;'+Mob+'&nbsp;&nbsp;|&nbsp;&nbsp;<i class="fas fa-phone-volume"></i>&nbsp;'+LL+'</td></tr>';*/
                                htmlstr+='<tr><td>'+dvsn+'</td><td>'+Ofcr+'<br/><i class="fas fa-phone-volume"></i>&nbsp;'+LL+'</td></tr>';

			}
			htmlstr+='</table></div></div>';
			$("#divCntData").html(htmlstr);
		}
	});
}

$(document).ready(function() {
var list = $("#cntdsttlist");
$.each(aDistrict, function(index, item) {
  var dstttext=item.substring(item.indexOf('-')+1);
  var dsttval=item.substring(0, item.indexOf('-'));

  list.append(new Option(dstttext,dsttval));
});
$("#cntdsttlist").on('change', function (e) {
    var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    DsttNodlOffcr(valueSelected);
});
});

$(document).ready(function() {

let anchorlinks = document.querySelectorAll('a[href^="#"]')

for (let item of anchorlinks) { // relitere
    item.addEventListener('click', (e)=> {
        let hashval = item.getAttribute('href')
        let target = document.querySelector(hashval)
        target.scrollIntoView({
            behavior: 'smooth',
            block: 'start'
        })
        history.pushState(null, null, hashval)
        e.preventDefault()
    })
}
});

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


function searchWord()
{
	var in_word=$("#inptSearch").val();
	if(in_word=="")
		return false;

	location.href="/RailSAHAY/pages/SrchPageList.jsp?in="+in_word;
}

