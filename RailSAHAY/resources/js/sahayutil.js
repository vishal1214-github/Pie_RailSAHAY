function getSrchRprt(varText)
{
	var url =  "/RailSAHAY/GG_SahayAjaxData?Help=SRCHRPRT&Srch=" + varText;
	var ajax1	= new AJAXInteraction1(url, helpCallback1);
	ajax1.doPost();
}
function getCodeHelp(varText)
{
	var url =  "/RailSAHAY/GG_SahayAjaxData?Help=CODEHELP&Srch=" + varText;
	var ajax1	= new AJAXInteraction1(url, helpCallback1);
	ajax1.doPost();
}
function setLangFlag(varText)
{

	var url =  "/RailSAHAY/GG_SahayAjaxData?Help=SETLANGFLAG&Srch=" + varText;
	var ajax1	= new AJAXInteraction1(url, helpCallback1);
	ajax1.doPost();
}
function setActlTheme(varText)
{
	var url =  "/RailSAHAY/GG_SahayAjaxData?Help=SE TTHEME&Srch=" + varText;
	var ajax1	= new AJAXInteraction1(url, helpCallback1);
	ajax1.doPost();
}
function setMainCmdt(cmdtcode, cmdtname)
{
	$("#MainCmdtCode").val(cmdtcode);
	$("#MainCmdtName").val(cmdtname);
	$("#Help").val("SETMAINCMDT");
	$( "#frmCmdt" ).submit();

}
function setMainCmdtCode(cmdtcode)
{
	$("#MainCmdtCode").val(cmdtcode);
	var cmdtname=getCmdtDesc(cmdtcode);
	$("#MainCmdtName").val(cmdtname);
	$("#Help").val("SETMAINCMDT");
	$( "#frmCmdt" ).submit();

}
function setSubCmdt(varText)
{
	$("#Srch").val(varText);
	$("#Help").val("SETSUBCMDT");
	$( "#frmSubCmdt" ).submit();
	/*
	var myurl =  "/RailSAHAY/GG_SahayAjaxData";
	
	$.ajax({
		url : myurl,
		type : "post",
	        data: {Help:'SETSUBCMDT',Srch:varText},
		async : true,
		success : function(data) {
				console.log("Through");
			}
	});*/

}

function getCmdtDesc(cmdt)
{
	var cmdtdesc='';
	var imgcmdt='/RailSAHAY/resources/images/industry/'+cmdt+'.jpg';
	$("#imgCmdt").attr("src",imgcmdt);
	switch(cmdt)
	{
		case 'COAL':
			cmdtdesc='Coal & Coke';
			break;
		case 'ORES':
			cmdtdesc='Minerals & Ores';
			break;
		case 'FG':
			cmdtdesc='Food Grains, Flours & Pulses';
			break;
		case 'CEMT':
			cmdtdesc='Cement & Clinker';
			break;
		case 'CHEM':
			cmdtdesc='Chemical Manures';
			break;
		case 'IS':
			cmdtdesc='Iron & Steel';
			break;
		case 'POL':
			cmdtdesc='Petroleum Products & Gases';
			break;
		case 'CONT':
			cmdtdesc='Container Services';
			break;
		case 'AUTO':
			cmdtdesc='Automobiles';
			break;
		case 'OTHR':
			cmdtdesc='Others';
			break;
	}
	return cmdtdesc;
}

function AJAXInteraction1(url, callback)
{
	var req = initAJAX1();
	function initAJAX1()
	{

		if (window.XMLHttpRequest)
		{
			return new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	req.onreadystatechange = function()
	{
		if (req.readyState == 4)
		{
			if (req.status == 200)
			{
				if (callback)
				{
					callback(req.responseXML);
				}
			}
		}
	}

	this.doPost = function(params)
	{
			if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15)
			{
				req.open("POST", url, false);
			}
			else
			{
				req.open("POST", url, false);
			}
			req.send(null);
	}
}
function helpCallback1(responseXML)
{
	var xmlDoc		= responseXML.documentElement;
	var xmlDataErr	= xmlDoc.getElementsByTagName("Error")[0].childNodes[0].nodeValue;
	if(xmlDataErr != "%Error%")
	{
		alert(xmlDataErr);
		return;
	}
	var xmlData		= xmlDoc.getElementsByTagName("Data")[0].childNodes[0].nodeValue;
	var i		= xmlDoc.getElementsByTagName("Count")[0].childNodes[0].nodeValue;
	document.getElementById('txtAjaxData').value=xmlData;
}
