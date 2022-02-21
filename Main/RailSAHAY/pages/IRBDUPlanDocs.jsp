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
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">

<%@ include file="/pages/IRBDUMISHeader.jspf" %>

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
    $(document).ready(function(){   
        getPlanFileList1();
    });
</script>
<div class="rprt-head">
    <p class="rprt-head">Business Acquisition Plan Documents</p>
</div>
<hr/>
<div class="container-fullwidth" style="padding:1rem;">
<div class="row">
<div class="col-lg-12 col-sm-12">
    <div id='dtlsGrid' class='data-grid'></div>
</div>
</div>
</div>
<form id="frmGetFile" action="/RailSAHAY/AcqnPlan" method="post" target="FileDownload">
    <input name="Optn" id="Optn" type="hidden" value="DOWNLOAD_FILE" />
    <input name="FileId" id="FileId" type="hidden" value="" />
</form>
<%@ include file="/pages/IRBDUFooter.jspf" %>