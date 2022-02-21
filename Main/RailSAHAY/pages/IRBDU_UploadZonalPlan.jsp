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
<div class="row">
<div class="col-lg-6 col-sm-12">
<div class="contact-form">
            <div class="contact-image">
                <img src="/RailSAHAY/resources/images/rocket_contact.png" alt="rocket_contact"/>
            </div>
            <form action="/RailSAHAY/AcqnPlan" onsubmit="onFormSubmit();" method="post" enctype="multipart/form-data">
                <h3>Upload Business Development and Acquisition Plan</h3>
               <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                        <input type="text" class="form-control" readonly="true"  aria-label="Current Time" aria-describedby="basic-addon1" style="font-size:12px;font-weight:600;" value="Upload Time: <%=strCrntTime%>">
                        <input type="hidden" id="Optn" name="Optn" value="UPLOAD_AQSN_PLAN" />
                        </div>
                        <div class="form-group">
                            <select id="selZone" name="selZone" class="form-control">
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
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="txtFile" class="text-label">Select File:</label>
                            <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()">
                        </div>
                        <br/>
                        <div class="form-group" id="divLdngImg">
                            <input type="submit" name="btnSubmit" id="btnSubmit" class="btnContact" value="Upload >>" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <textarea name="Rmrk" id="Rmrk" oncopy="return false" onpaste="return false" onkeypress="return checkKeyEntry(event);" class="form-control" placeholder="Remarks (If any)" style="width: 100%; height: 150px;"></textarea>
                        </div>
                    </div>
                </div>
            </form>
</div>
</div>
<div class="col-lg-6 col-sm-12">
    <p class="data-grid-head">BDU-wise Uploaded Plans</p>
    <hr/>
    <div id='dtlsGrid' class='data-grid'>
    
    </div>
</div>
<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>