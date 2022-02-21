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

<link rel="stylesheet"  type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<link rel="stylesheet"  type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"></script>

<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" class="init"></script>


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
<script src="/RailSAHAY/resources/js/irbdu/resdtls.js"></script>

<div class="rprt-head">
    <p class="rprt-head">Resource Centre</p>
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
                <select id="selDvsn" name="selDvsn" class="form-control inpt-control inpt-select">
                </select>
            </td>
            <td class="inpt-val">
                <input type="submit" name="btnSubmit" id="btnSubmit" class="btn btn-danger inpt-control" value="Continue" onclick="refreshData();" />
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
                <div class="card-body data-grid" id="divResource">
                    
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/IRBDUFooter.jspf" %>