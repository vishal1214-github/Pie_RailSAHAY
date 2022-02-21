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
<script src="https://phpcoder.tech/multiselect/js/jquery.multiselect.js"></script>
<link rel="stylesheet" href="https://phpcoder.tech/multiselect/css/jquery.multiselect.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduuploadplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aRakeType.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/proposal.js"></script>
<script>
    var GG_CrntZone="";
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
        <% } %>
       <% if(strStts.equals("F")) { %>
        failedToast("Failed to Upload the Proposal: <%=strErorMesg%>");
        <% } %>
        getProposalList();
	var raketypestr='';
   	for (var i=0; i < aRakeType.length;++i)
	{
        	raketypestr+= '<option value="'+aRakeType[i]+'" />'; // Helplist for Rake Type
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
<div class="rprt-head">
    <p class="rprt-head">Upload Business Proposals</p>
</div>
<div class="container">
            <form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post" enctype="multipart/form-data">
	      <div class="card mt-2" id="divUpload">
		<% if(strLognLocnFlag.equals("Z")) { %>
		<div class="card-header" style="padding:8px;font-size:13px;font-weight:600;">Upload Business Proposal <span class="pull-right">Zone: <span class="badge badge-secondary" style="font-size:13px;"><%=strLognLocn%></span><span></div>
		<%} if(strLognLocnFlag.equals("D")) { %>
		<div class="card-header" style="padding:8px;font-size:13px;font-weight:600;">Upload Business Proposal <span class="pull-right">Division: <span class="badge badge-secondary" style="font-size:13px;"><%=strLognLocn%></span></span></div>
		<% } %>
		<div class="card-body">
			 <div class="row">
                    <div class="col-lg-6 col-sm-12">
			<select id="PropTypeList" class="form-control" name="PropTypeList[]" multiple>
			</select><input type="hidden" name="PropType" id="PropType" />		
		    </div>
		<div class="col-lg-6 col-sm-12">
			<textarea id="OthrTypeDtls" name="OthrTypeDtls" class="form-control" placeholder="Proposal Detail (Description)" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);" style="height:50px;width:95%;"></textarea>
			<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>
		</div>
		</div>
		<hr/>
		<div class="row">
                    <div class="col-lg-6 col-sm-12">
			<input type="text" id="txtPropCmdt" list="cmdtlist" name="txtPropCmdt" class="form-control" placeholder="Commodity"><datalist id="cmdtlist"></datalist>
			<input type="hidden" id="PropCmdt" name="PropCmdt" class="form-control">
		    </div>
                    <div class="col-lg-6 col-sm-12">
			<input type="text" id="txtPropLoadType" onfocus="updtPropType();" list="raketypelist" name="txtPropLoadType" class="form-control" placeholder="Stock Type"><datalist id="raketypelist"></datalist>			
			<input type="hidden" id="PropLoadType" name="PropLoadType" class="form-control">
		    </div>
		</div>
		</div>
		<div class="row">
                    <div class="col-lg-6 col-sm-12">
			   <div class="form-group" style="margin-bottom:0px;padding:1rem;">
                            <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()" >
                        </div>
                        </div> 
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">
                                <textarea name="Rmrk" id="Rmrk" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);" class="form-control" placeholder="Remarks (If any)" style="width: 95%; height: 50px;"></textarea>
				<span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>
                                <input type="hidden" id="Optn" name="Optn" value="UPLOAD_PROPOSAL" />
                            </div>                        
                        </div>
                        </div>
		   </div>
                        <div class="card-footer">
                        <div class="col-lg-12 col-sm-12">
                        <div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
                            <input type="submit" name="btnSubmit" id="btnSubmit" class="btnContact btn-danger btn" value="Upload >" />
			    <%--
                            <input type="button" name="btnFetch" id="btnFetch" class="btnContact btn-info btn" value="Fetch Proposals" onclick="getProposalList();" />
			--%>
                        </div>
                        </div>
                        </div>
</div>
            </form>
</div>
		</div>		

<div class="col-lg-12 col-sm-12">    
<div class="rprt-head">
    <p class="rprt-head">Business Proposals</p>
</div>
    <hr/>
    <div id='dtlsGrid' class='data-grid'>
    
    </div>
</div>
<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_PROPOSAL_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
<form id="frmGetDcsnLetr" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_DECISION_FILE" />
    <input name="PropId" id="PropId" type="hidden" value="" />
</form>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>