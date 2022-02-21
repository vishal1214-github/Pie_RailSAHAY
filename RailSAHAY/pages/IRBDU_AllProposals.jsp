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
<link rel="stylesheet" href="/RailSAHAY/resources/css/irbdu/welcome_page.css">
<script src="/RailSAHAY/resources/js/irbdu/decision.js"></script>

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


<script>
    var GG_SelZone="";
    var GG_PropZoneFlag="Y";
    $(document).ready(function(){  
	getAllProposals();
    });
</script>
<div class="rprt-head">
    <p class="rprt-head">Business Proposals</p>
</div>
<div class="container" align="center">
<table class="table-striped">
<tr><td>                        
                            <select id="selZone" name="selZone" class="form-control">
                               <option value="" selected="true" disabled="disabled">SELECT ZONE</option>
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
                        </td> 
                        <td>         
                            <select id="PropType" name="PropType" class="form-control">
                            </select>
                            </td>
                        <td>
                        <div class="form-group text-center" id="divLdngImg" style="margin-bottom:0px;">
                            <input type="button" name="btnFetch" id="btnFetch" class="btnContact btn-info btn" value="Fetch Proposals" onclick="getAllProposals();" />
                        </div>
                        </td>
                        </tr>
	</table>
</div>
    <hr/>
<div class="container-fullwidth">
<div class="row">
<div class="col-lg-12 col-sm-12">    
    <div id='dtlsGrid' class='data-grid'></div>
</div>
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

</div>

<%@ include file="/pages/IRBDUFooter.jspf" %>