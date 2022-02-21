<%@ include file="/pages/IRBDUHeaderNoMenu.jspf" %>
<% 
String strInptZone="";
String strInptDvsn="";
String strStts="";
String strFileId="";
String strFileName="";
String strErorMesg="";
String strInptFlag="";
try
{
    strInptZone=((String)request.getParameter("Zone")).trim();
    strInptDvsn=((String)request.getParameter("Dvsn")).trim();
}
catch(Exception e)
{
	strInptZone="";
	strInptDvsn="";
}

try
{
    strInptFlag=((String)request.getParameter("CallFlag")).trim();
}
catch(Exception e)
{
	strInptFlag="";
}
if(strInptFlag.equals("NEW"))
{
	
        session.removeAttribute("Stts");
        session.removeAttribute("ErorMesg");
        session.removeAttribute("FileId");
        session.removeAttribute("FileName");
}
try
{

    strStts=((String)session.getAttribute("Stts")).trim();
    strErorMesg=((String)session.getAttribute("ErorMesg")).trim();
    strFileId=((String)session.getAttribute("FileId")).trim();
    strFileName=((String)session.getAttribute("FileName")).trim();
}
catch(Exception e)
{
	strStts="";
	strFileId="";
	strErorMesg="";
	strFileName="";
}
%>

<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduuploadplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<script src="/RailSAHAY/resources/js/irbdu/trfcplanupload.js"></script>

<script>
    $(document).ready(function(){  
	<% if(!strInptFlag.equals("NEW")) { %>
       <% if(strStts.equals("S")) { %>
	   window.parent.document.getElementById('txtTrfcPlanId').value='<%=strFileId%>';
	   window.parent.document.getElementById('planMesg').innerHTML='<span class="success-plan">Uploaded Successfully</span>';
	   window.parent.document.getElementById('planFileName').innerHTML='<i class="fa fa-file" aria-hidden="true"></i>&nbsp;&nbsp;<%=strFileName%>';
	   window.parent.document.getElementById('iFrmTrfcPlan').style.display='none';
        <% } %>
       <% if(strStts.equals("F")) { %>
	   window.parent.document.getElementById('planMesg').innerHTML='<span class="failed-plan"><%=strErorMesg%></span>';
        <% }} %>
    });
    function onFormSubmit()
    {
	return true;
    }
</script>

<div class="container-fullwidth">
	<form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post" enctype="multipart/form-data">              
		<table>
			<tr style="border:0px;"><td style="border:0px;">
			<div class="form-group" style="margin-bottom:0px;padding:1rem;">
				<label for="inptfile" style="font-size:13px;">Select Traffic Plan File <b>(*PDF Format Only)</b></label>
			        <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()">
			</div>
			</td>
			<td style="border:0px;">
			<div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
			<input type="hidden" name="Optn" id="Optn" value="UPLOAD_TRFC_PLAN" />
			<input type="hidden" name="Zone" id="Zone" value="<%=strInptZone%>" />
			<input type="hidden" name="Dvsn" id="Dvsn" value="<%=strInptDvsn%>" />
			<input type="submit" name="btnSubmit" id="btnSubmit" class="btnContact btn-danger btn-sm" value="Upload >" />
			</div>
			</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>