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

String strCrntZone1="";
if(strLognLocnFlag.equals("Z"))
{
	strCrntZone1=strLognLocn;
}
if(strLognLocnFlag.equals("D"))
{
	strCrntZone1=(String)session.getAttribute("CrntZone");;
}
%>

<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduuploadplan.css">
<script src="/RailSAHAY/resources/js/irbdu/dvsnperfmis.js"></script>
<script src="/RailSAHAY/resources/js/irbdu/irdashboard.js"></script>
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<style>
.card {
  /* Add shadows to create the "card" effect */
  box-shadow: 0 2px 3px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

/* On mouse-over, add a deeper shadow */
.card:hover {
  box-shadow: 0 3px 4px 0 rgba(0,0,0,0.2);
}
</style>

<%@ include file="/pages/IRBDUMISHeader.jspf" %>
<script>
    $(document).ready(function(){     
        //getPlanFileList1();
	fetchIRDBData('','');
	if("<%=strCrntZone1%>"!="")
	{
		$('#selZone option[value="<%=strCrntZone1%>"]').prop('selected', true);
		fetchDvsnPerfStats();
	}
    });
</script>
<div class="rprt-head">
    <p class="rprt-head">Divisional Analysis</p>
</div>
<hr/>
<div class="container" align="center">
<table class="table-striped">
        <tr>
            <td>
                <select id="selZone" name="selZone" class="form-control inpt-control inpt-select">
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
                </select>
            </td>
            <td>
                <button type="button" class="btn btn-info inpt-control" onclick="fetchDvsnPerfStats();" />Continue ></button>
            </td>
	</tr>
    </table>
</div>
</div>
</div>
<div class="container-fullwidth" style="padding:1rem;">
<hr/>
    <div id='divDvsnPerf' class='row'></div>
</div>
<%@ include file="/pages/IRBDUFooter.jspf" %>