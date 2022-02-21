<%@ include file="/pages/IRBDUHeader.jspf" %>
<% 
String strZone="";
String strStts="";
String strErorMesg="";
try
{
    strZone=((String)session.getAttribute("Zone")).trim();
    strStts=((String)session.getAttribute("Stts")).trim();
    strErorMesg=((String)session.getAttribute("ErorMesg")).trim();
}
catch(Exception e)
{
    strZone="";
    strStts="";
    strErorMesg="";
}
session.setAttribute("Zone","");
session.setAttribute("Stts","");
session.setAttribute("ErorMesg","");
%>

<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduuploadplan.css">
<script src="/RailSAHAY/resources/js/irbdu/bduuploadplan.js"></script>
<script>
    var GG_SelZone="";
    var GG_LognZone="<%=strLognLocn%>";
    $(document).ready(function(){        
       $('#selZone>option[value="<%=strZone%>"]').prop('selected', true); 
       <% if(strStts.equals("S")) { %>
        successToast("Plan Uploaded Successfully !");
        <% } %>
       <% if(strStts.equals("F")) { %>
        failedToast("Failed to Upload the Plan: <%=strErorMesg%>");
        <% } %>
        getPlanFileList('');
    });
</script>
<div class="rprt-head">
    <p class="rprt-head">Upload Business Acquisition Plan Document</p>
</div>
<div class="container">
<form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" method="post" enctype="multipart/form-data">
<div class="card">
	<div class="card-header" style="font-weight:600;color:#4d4d4d;font-size:13px;">
		Upload your plan document &nbsp;&nbsp;|&nbsp;&nbsp; Zone: <span class="badge badge-secondary" style="font-size:13px;"><%=strLognLocn%></span> <span class="pull-right">Current Time: <%=strCrntTime%></span>
	</div>
	<div class="card-body">
		<table class="table table-bordered" style="margin-bottom:0px;">
			<tr>				
				<td style="max-width:50%;">
					<div class="form-group">
	                    <label for="txtFile" class="text-label" style="font-weight:600;font-size:13px;">Select Plan Document (PDF, Docx) Max. 4 MB:</label><br/>
	                    <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()">
	                </div>
				</td>
				<td>				
	                <div class="form-group">
	                    <textarea name="Rmrk" id="Rmrk" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);" class="form-control" placeholder="Remarks (If any)" style="width: 100%; height: 70px;"></textarea>
			<input type="hidden" id="selZone" class="form-control" name="selZone" value="<%=strLognLocn%>" readonly>
	                    <input type="hidden" id="Optn" name="Optn" value="UPLOAD_AQSN_PLAN" />
	                </div>
				</td>
			</tr>
		</table>
	</div>
	<div class="card-footer">	
        <div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
            <input type="submit" name="btnSubmit" class="btn btn-info" id="btnSubmit" class="btnContact" value="Upload >>" />
        </div>
	</div>	
</div>
</form>
</div>
<hr/>
<div class="container-fullwidth" style="padding:1rem;">
<div class="row">
<div class="col-lg-12 col-sm-12">
    <p style="font-weight:1rem;color:#b32400;font-weight:600;">BDU-wise Uploaded Plans</p>
    <div id='dtlsGrid' class='data-grid'></div>
</div>
</div>
</div>
<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
<%@ include file="/pages/IRBDUFooter.jspf" %>