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
if(strLognLocnFlag.equals("Z"))
{
	strCrntZone=strLognLocn;
	strCrntDvsn="";	
}
if(strLognLocnFlag.equals("D"))
{
	strCrntZone=(String)session.getAttribute("CrntZone");
	strCrntDvsn=strLognLocn;	
}
%>

<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bdupdateplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/resource_centre.js"></script>
<script>
    var GG_SelZone="";
    $(document).ready(function(){ 
		fetchResourceList();	
        <% if(strStts.equals("S")) { %>
            successToast("Resource File Uploaded Successfully!");
        <% } %>
    });
    function onFormSubmit() {
        $("#divLdngImg").html('<div class="spinner-border text-danger"></div>');
        return true;
    }
</script>
<style>
         
        label {
            float: left;
        }
          
        span {
            display: block;
            overflow: hidden;
            padding: 0px 4px 0px 6px;
        }
</style>
<div class="rprt-head">
    <p class="rprt-head">Resource Centre</p>
</div>
<div class="inptdiv">
    <table>
        <tr>
            <td class="inpt-val">
		<label for="txtZone" style="font-weight:600;font-size:13px;margin-right:5px;margin-top:6px;">Zone:</label>
    		<span>		  
		<input type="text" id="txtZone" name="txtZone" class="form-control" readonly value="<%=strCrntZone%>" >
		</span>
            </td>
	   <%if(strLognLocnFlag.equals("D")) { %>
            <td class="inpt-val">
		<label for="txtDvsn" style="font-weight:600;font-size:13px;margin-right:5px;margin-top:6px;">Division:</label>
    		<span><input type="text" id="txtDvsn" name="txtDvsn" class="form-control" readonly value="<%=strCrntDvsn%>" /></span>
            </td>
	   <% } else { %>
		<input type="hidden" id="txtDvsn" name="txtDvsn" value="" />
	   <% } %>
            <td class="inpt-val">
                <input type="submit" name="btnSubmit" id="btnSubmit" class="btn btn-danger inpt-control" value="Refresh" />
            </td>
      <td>      
  <a class="btn-link" style="font-weight:600;font-size:13px;color:#b32400 !important;margin-left:1rem;" data-toggle="collapse" href="#uploadResFile" role="button" aria-expanded="false" aria-controls="collapseExample">
    <i class="fa fa-upload" aria-hidden="true"></i>&nbsp;&nbsp;Upload Resource File
  </a>
  </td>
</tr>
</table>
<div class="collapse" id="uploadResFile">
<table class="table table-striped">
<tr>
<td>
   <p>
</p>
  <div class="card card-body">
    <form id="frmResFile" action="/RailSAHAY/AcqnPlanResUpload" onsubmit="onFormSubmit();" method="post" enctype="multipart/form-data">
         <h3 style="font-weight:600;color:#4d4d4d;font-size:14px;">Upload Resource(s) File <span style="color:#b32400;display:inline;">(*Delimiter (#) Based Format Only)</span></h3>
         <table class="table table-striped"><tr><td rowspan="2" style="font-size:12px;background:#ffeb99;font-weight:600;padding:2px;text-align:center;vertical-align:middle;">Reference Format</td><td style="font-size:12px;font-weight:600;padding:2px;">Rake_Commodity_Code#Industry#Resource_Name#Mobile_Number#Landline_Number#Email_Id#Address</td></tr>
         <tr><td style="font-size:12px;padding:2px;">CEMT#ULTRATECH CEMENT#DEEPAK CHATURVEDI#9999112233#23456789#DEEPAK.CHATURVEDI@GMAIL.COM#B-45,ZOR BAGH, NEW DELHI-110034</td></tr>
         </table>
         <hr/>
               <div class="row">
               <div class="col-lg-4">
                    <div class="form-group">
                        <input type="file" id="inptfile" name="inptfile" onchange="fileValidate()">
                    </div>
                </div>
               <div class="col-lg-2">
                        <div class="form-group" id="divLdngImg">
                            <button class="btn-submit btn btn-info inpt-control" name="btnSubmit" id="btnSubmit">Upload</button>
                        </div>
                </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                        <input type="hidden" id="Optn" name="Optn" value="UPLOAD_RES_FILE" />
                        </div>
                    </div>
                    </div>
    </form>
  </div>
</td>
</tr>
    </table>
</div>
</div>
<section class="container-fullwidth" style="padding:1rem;">
    <div id="dtlsGrid"></div>
    </div>
<div class="modal fade" id="resModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
	<b>Add Resource Detail</b>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="divAddRecord">
        <div class="d-flex flex-column text-center mt-1" id="divInptForm">
          <form>
            <div class="row mt-3">
            <div class="col-lg-4 col-sm-12">
            <div class="form-group">
		<input type="text" id="txtCmdt" list="cmdtlist" name="txtCmdt" class="form-control" placeholder="Commodity"><datalist id="cmdtlist"></datalist>
            </div>
            </div>
            <div class="col-lg-4 col-sm-12">
            <div class="form-group">
              <input type="text" class="form-control" id="txtInds" name="txtInds" placeholder="Name of Target Industry" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" />
	      <input type="hidden" class="form-control" id="txtResId" name="txtResId"/>
            </div>
            </div>
            <div class="col-lg-4 col-sm-12">
            <div class="form-group">
              <input type="text" class="form-control" id="txtName" name="txtName" placeholder="Resource Name" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" />
            </div>
            </div>
            <div class="col-lg-3 col-sm-12">
            <div class="form-group">
              <input type="number" class="form-control" id="txtMobl" name="txtMobl" placeholder="Mobile Number" maxlength="10" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" />
            </div>
            </div>
            <div class="col-lg-3 col-sm-12">
            <div class="form-group">
              <input type="number" class="form-control" id="txtLandline" name="txtLandline" placeholder="Landline Number" maxlength="11" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" />
            </div>
            </div>
            <div class="col-lg-3 col-sm-12">
            <div class="form-group">
              <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email Id" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" />
            </div>
            </div>
            <div class="col-lg-3 col-sm-12">
            <div class="form-group">
              <textarea id="txtAddr" name="txtAddr" class="form-control" placeholder="Communication Address"onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false"></textarea>
  	      <span class="pull-right badge badge-danger word-left" id="S_word_left">Maximum 30 Words</span>
            </div>
            </div>
      </div></div>
            <div class="btn-group">
  <button type="button" class="btn btn-warning" onclick="addResourceDtls();">Save & Add More</button>&nbsp;
  <button type="button" class="btn btn-secondary" data-dismiss="modal" aria-label="Close" >Close</button>
</div>
          </form>
          
          
      </div>
    </div>
  </div>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>