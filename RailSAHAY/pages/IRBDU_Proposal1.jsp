<%@ include file="/pages/IRBDUHeaderNoMenu.jspf" %>
<% 
String strZone="";
String strStts="";
String strErorMesg="";
String strFileId="";
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
try
{

    strFileId=((String)request.getParameter("FileId")).trim();
}
catch(Exception e)
{
	strFileId="";
}
session.setAttribute("Zone","");
session.setAttribute("Stts","");
session.setAttribute("ErorMesg","");
%>

<script src="https://phpcoder.tech/multiselect/js/jquery.multiselect.js"></script>
<link rel="stylesheet" href="https://phpcoder.tech/multiselect/css/jquery.multiselect.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduuploadplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<script src="/RailSAHAY/resources/js/irbdu/proposal.js"></script>
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aRakeType.js"></script>

<script>
    var GG_SelZone="";
    var GG_PropZoneFlag="Y";
    var GG_LognLocnFlag="<%=strLognLocnFlag%>";
    var GG_CrntDvsn="";
    var GG_DVsnZone="";

    $(document).ready(function(){  
	<% if(strLognLocnFlag.equals("Z")) { %>      
       	GG_CrntZone="<%=strLognLocn%>";
	<% } %>
	<% if(strLognLocnFlag.equals("D")) { %>      
       	GG_CrntDvsn="<%=strLognLocn%>";
	GG_DvsnZone="<%=(String)session.getAttribute("CrntZone")%>";
	<% } %>

       <% if(strStts.equals("S")) { %>
        successToast("Proposal Uploaded Successfully !");
	   window.parent.document.getElementById('txtPropId').value='<%=strFileId%>';
        <% } %>
       <% if(strStts.equals("F")) { %>
        failedToast("Failed to Upload the Proposal: <%=strErorMesg%>");
        <% } %>
	var raketypestr='';
   	for (var i=0; i < aRakeType.length;++i)
	{
        	raketypestr+= '<option value="'+aRakeType[i]+'" />'; // Helplist for Grup Rake Type
   	}
   	$("#raketypelist").html(raketypestr);
	
 		var cmdtstr='';
   		for (var i=0; i < aRakeCmdt.length;++i)
		{
        		cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for Rake Commodity
   		}
   		$("#cmdtlist").html(cmdtstr);

    });

function onFormSubmit()
{
	$("#PropCmdt").val(refineValue($("#txtPropCmdt").val(),'CM'));
	$("#PropLoadType").val(refineValue($("#txtPropLoadType").val(),'R'));
	return true;
}

</script>

<div class="container-fullwidth" style="padding:10px;">
            <form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post" enctype="multipart/form-data">
              
               <div class="row">
                    <div class="col-lg-12 col-sm-12">
                        <div class="form-group" style="margin-bottom:0px;">
                            <select id="selZone" name="selZone" class="form-control" value="<%=strLognLocn%>" style="display:none;">
                            </select>
			</div>
		    </div>
		</div>
		<div class="row mt-1">
                    <div class="col-lg-5 col-sm-12">
			<label for="PropTypeList" style="font-weight:600;color:#4d4d4d;font-size:13px;">Select Proposal Type(s):</label>
			<select id="PropTypeList" class="form-control" name="PropTypeList[]" multiple>
			</select>			
			<input type="hidden" value="" name="PropType" id="PropType" />
			<input type="text" id="OthrTypeDtls" name="OthrTypeDtls" class="form-control mt-2" placeholder="Proposal Description (Detail)" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);">
		    </div>
		<div class="col-lg-4 col-sm-12">			
			<input type="text" id="txtPropCmdt" list="cmdtlist" name="txtPropCmdt" class="form-control mt-1" placeholder="Commodity"><datalist id="cmdtlist"></datalist>
			<input type="hidden" id="PropCmdt" name="PropCmdt" class="form-control">
			<input type="text" id="txtPropLoadType" list="raketypelist" name="txtPropLoadType" class="form-control mt-1" placeholder="Stock Type"><datalist id="raketypelist"></datalist>			
			<input type="hidden" id="PropLoadType" name="PropLoadType" class="form-control">
                            <div class="form-group mt-1">
                                <textarea name="Rmrk" id="Rmrk" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);" class="form-control" placeholder="Remarks (If any)" style="height: 50px;"></textarea>
                                <input type="hidden" id="Optn" name="Optn" value="UPLOAD_PROPOSAL_LIVE" />
                            </div>
		</div>
		<div class="col-lg-3 col-sm-12">
			   <div class="form-group" style="margin-bottom:0px;padding:1rem;">
                            <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()" >
                        </div>
			<hr/>
                        <div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
                            <input type="submit" name="btnSubmit" id="btnSubmit" class="btnContact btn-danger btn" value="Upload Proposal" />
                        </div>
		</div>
		</div>
</div>
            </form>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>