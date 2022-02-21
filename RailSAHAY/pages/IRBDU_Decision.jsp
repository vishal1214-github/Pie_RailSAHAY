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
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<script src="/RailSAHAY/resources/js/irbdu/decision.js"></script>
<script>
    var GG_SelZone="";
    var GG_PropZoneFlag="Y";
    var GG_LognLocnFlag="<%=strLognLocnFlag%>";
    var GG_LognLocn="<%=strLognLocn%>";
    $(document).ready(function(){  
	/*getProposalList();*/
       <% if(strStts.equals("S")) { %>
        successToast("Decision Letter Uploaded Successfully !");
        <% } %>
       <% if(strStts.equals("F")) { %>
        failedToast("Failed to Upload the Decision Letter: <%=strErorMesg%>");
        <% } %>
    });
</script>
<div class="rprt-head">
    <p class="rprt-head">Received Business Proposals</p>
</div>
<div class="container">
<div class="card">
<div class="card-body">
            <form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post" enctype="multipart/form-data">
               <div class="row">
                    <div class="col-lg-6 col-sm-12">
                        <div class="form-group" style="margin-bottom:0px;">
                            <select id="selZone" name="selZone" class="form-control">
                               <option value="">SELECT ZONE</option>
                               <option value="BPT">BPT - MUMBAI PORT TRUST</option>
                                <option value="CPT">CPT - CULCUTTA PORT TRUST</option>
                                <option value="CR">CR - CENTRAL RAILWAY</option>
                                <option value="DFCR">DFCR - DEDICATED FREIGHT CORRIDOR RAILWAY</option>
                                <option value="EC">EC - EAST CENTRAL RAILWAY</option>
                                <option value="ECO">ECO - EAST COAST RAILWAY</option>
                                <option value="ER">ER - EASTERN RAILWAY</option>
                                <option value="KR">KR - KONKAN RAILWAYS CORPORATION  LTD</option>
                                <option value="NC">NC - NORTH CENTRAL RAILWAY</option>
                                <option value="NE">NE - NORTH EASTERN</option>
                                <option value="NF">NF - NORTHEAST FRONTIER</option>
                                <option value="NR">NR - NORTHERN RAILWAY</option>
                                <option value="NW">NW - NORTH WESTERN RAILWAY</option>
                                <option value="PRC">PRC - PIPAVAV RAIL CORPORATION LTD.</option>
                                <option value="SC">SC - SOUTH CENTRAL</option>
                                <option value="SE">SE - SOUTH EASTERN</option>
                                <option value="SEC">SEC - SOUTH EAST CENTRAL RAILWAY</option>
                                <option value="SR">SR - SOUTHERN RAILWAY</option>
                                <option value="SW">SW - SOUTH WESTERN RAILWAY</option>
                                <option value="WC">WC - WEST CENTRAL RAILWAY </option>
                                <option value="WR">WR - WESTERN RAILWAY </option>
                               <option value="">ALL ZONE</option>

                            </select>
                        </div> 
                        </div>
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">                            
                            <select id="PropType" name="PropType" class="form-control">
                            </select>

                            </div>
                        </div>
                        </div>
                        <div class="card-footer">
                        <div class="col-lg-12 col-sm-12">
                        <div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
                            <input type="button" name="btnFetch" id="btnFetch" class="btnContact btn-info btn" value="Fetch Proposals" onclick="getProposalList();" />
                        </div>
                        </div>
                        </div>
            </form>
</div>
</div>
</div>
    <hr/>

<div class="col-lg-12 col-sm-12">    
    <div id='dtlsGrid' class='data-grid'></div>
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

<div class="modal fade" id="respModal" tabindex="-1" role="dialog" aria-labelledby="ResponseModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0" style="padding: 0.6rem 1rem;background-color: #ffffff;background-image: linear-gradient(315deg, #ffffff 0%, #d7e1ec 74%);">
	 <div class="form-title1 text-center">
          <h4 id="respFormTitl" style="font-size:0.9rem;font-weight:600;color:#000;"></h4>
        </div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="divAddResponse">
       
        <div class="d-flex flex-column text-center" id="divInptForm">
          <form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post" enctype="multipart/form-data">
            <div class="row">
            <div class="col-lg-6 col-sm-12">
            <div class="form-group">
              <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()" >
            </div>
            </div>
            <div class="col-lg-6 col-sm-6">
            <div class="form-group">
              <textarea class="form-control" id="Rmrk" name="Rmrk" placeholder="Decision Subject/Detail/Remarks"></textarea>
	      <input type="hidden" class="form-control" id="PropId1" name="PropId1">
  	      <input type="hidden" id="Optn" name="Optn" value="UPLOAD_DECISION" />
            </div>
            </div>
	    </div>
            <div class="row">
            <div class="col-lg-12 col-sm-12 text-center">
            <div class="btn-group">
  <button type="submit" class="btn btn-info btn-submit" id="btnSubmit">Save Response</button>
</div>
</div>
</div>
          </form>
          
          
      </div>
    </div>
      <div class="modal-footer d-flex justify-content-center">
	<div id="divMesg"></div>
        <div class="signup-section">Cancel and <a href="javascript:void(0)" data-dismiss="modal" aria-label="Close"  class="text-info"> Go Back</a></div>
      </div>
  </div>
</div>

<%@ include file="/pages/IRBDUFooter.jspf" %>