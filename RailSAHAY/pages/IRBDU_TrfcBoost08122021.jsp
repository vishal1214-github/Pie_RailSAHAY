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

String strDvsnZone="";
if(strLognLocnFlag.equals("D"))
{
	strDvsnZone=(String)session.getAttribute("CrntZone");
}

session.setAttribute("Zone","");
session.setAttribute("Stts","");
session.setAttribute("ErorMesg",""); 
%>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<%@ include file="/pages/IRBDUMISHeader.jspf" %>
<%--
<link rel="stylesheet" href="https://phpcoder.tech/multiselect/css/jquery.multiselect.css">
<script src="https://phpcoder.tech/multiselect/js/jquery.multiselect.js"></script>
--%>
<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/irbdu/welcome_page.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bdupdateplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<style>
.ms-options-wrap {
 margin-top: 0px; 
}
.ms-options
{
	width:250px !important;
}
.ms-options-wrap ul {
    text-transform: uppercase;
    margin-left: -20px;
}
.head-lbl
{
    font-size:13px;
    font-weight:600;
}
.data-grid
{
    max-height:450px;
    overflow-y:scroll;
    overflow-x:hidden;
    padding:5px;
}
td.popup-row
{
    padding:0px !important;
}
</style>
<script>
    var GG_SelZone="";
    var GG_CrntZone="";
    var GG_CrntLocn="";
    var GG_LognLocnFlag="<%=strLognLocnFlag%>";
    var GG_DvsnZone="<%=strDvsnZone%>";
    GG_CrntLocn="<%=strLognLocn%>";
	
    <% if(strLognLocnFlag.equals("Z")) { %>
    	GG_CrntZone="<%=strLognLocn%>";
    <% } %>  
    <% if(strLognLocnFlag.equals("D")) { %>
    	GG_CrntZone='<%=(String)session.getAttribute("CrntZone")%>';
    <% } %>
    $(document).ready(function(){  
	$('#selZone option[value="'+GG_CrntZone+'"]').attr("selected", "selected");
        $("#selZone").val(GG_CrntZone);
	$('.dropdown-menu a').click(function (e) {
    $('.active').removeClass('active');
});
    });
</script>
<script src="/RailSAHAY/resources/js/irbdu/prgsmeasure.js"></script>
<div class="rprt-head">
    <p class="rprt-head">Measures for Progress</p>
</div>
<div class="container" align="center">
    <table class="table-striped">
        <tr>
            <td class="inpt-val">
                <select id="selCmdt" name="selCmdt" class="form-control inpt-control inpt-select">
                </select>
            </td>
            <td class="inpt-val">
                <select id="selZone" name="selZone" class="form-control inpt-control inpt-select">
                   <option value="">SELECT ZONE</option>
                   <option value="BPT">BPT - MUMBAI PORT TRUST</option>
                    <option value="CPT">CPT - CULCUTTA PORT TRUST</option>
                    <option value="CR">CR - CENTRAL RAILWAY</option>
                    <option value="DFCR">DFCR - DEDICATED FREIGHT CORRIDOR RAILWAY</option>
                    <option value="EC">EC - EAST CENTRAL RAILWAY</option>
                    <option value="ECO">ECO - EAST COAST RAILWAY</option>
                    <option value="ER">ER - EASTERN RAILWAY</option>
                    <option value="KR">KR - KONKAN RAILWAYS CORPORATION LTD</option>
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
            </td>
            <td class="inpt-val">
               <select id="selType" name="selType" class="form-control">
               <option selected="true" checked="true" disabled="disabled" value="" >SELECT MEASURE</option>
               <option value="AP">ACTION PLAN</option>
               <option value="IN">INITIATIVES TAKEN</option>
               <option value="IO">INDUSTRY OUTREACH</option>
               <option value="IC" style="border-top:1px solid #4d4d4d;">RAIL COEFFICIENT</option>
               </select>
            </td>
            <td class="inpt-val">
                <input type="submit" name="btnSubmit" id="btnSubmit" class="btn btn-danger inpt-control" value="Continue" onclick="refreshData();" />
            </td>
            <td class="inpt-val">
                <div class="btn-group">
		  <button type="button" class="btn btn-secondary dropdown-toggle inpt-control" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    Action
		  </button>
		  <div class="dropdown-menu">
		    <a class="dropdown-item" href="#" onclick="downloadPDF();">Download PDF</a>
		    <div class="dropdown-divider"></div>
		    <a class="dropdown-item" href="#" onclick="downloadExcel();">Download Excel</a>
		    <div class="dropdown-divider"></div>
		    <a class="dropdown-item" href="#" onclick="printCrnt();">Print Current</a>
		    <div class="dropdown-divider"></div>
		    <a class="dropdown-item" href="#" onclick="printAll();">Print All</a>
		  </div>
		</div>
            </td>
	</tr>
    </table>
</div>
<hr/>
<div class="container-fullwidth" style="padding:1rem;">
    <div class="row">
        <div class="col-lg-12 col-sm-12">
            <div class="card">
                <div class="card-header head-lbl">
                    
                </div>
                <div class="card-body data-grid" id="divMeasure">
                    
                </div>
            </div>
        </div>
    </div>
</div>

<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_PROPOSAL_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
<%@ include file="/pages/IRBDUFooter.jspf" %>