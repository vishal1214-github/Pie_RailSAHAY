<%
String WgmtRakeID=((String)request.getParameter("WgmtRakeID")).toUpperCase();
String WgmtID=((String)request.getParameter("WgmtID")).toUpperCase();
String WgmtSttn=((String)request.getParameter("WgmtSttn")).toUpperCase();
String WgmtdateFrom=((String)request.getParameter("WgmtdateFrom")).toUpperCase();
String WgmtdateTo=((String)request.getParameter("WgmtdateTo")).toUpperCase();
String SttnFrom=((String)request.getParameter("SttnFrom")).toUpperCase();
String SttnTo=((String)request.getParameter("SttnTo")).toUpperCase();
String WgmtCount=((String)request.getParameter("WgmtCount")).toUpperCase();
String WgmtTime=((String)request.getParameter("TrxnTime")).toUpperCase();

%>

<%@ include file="/pages/GG_Header_Wgmt.jspf" %>

<script src="/RailSAHAY/resources/js/aLocn.js"></script>
<script src="/RailSAHAY/resources/js/aRakeCmdt.js"></script>
<script src="/RailSAHAY/resources/js/aGTCmdt.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/irbdu/welcome_page.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bdupdateplan.css">
<link rel="stylesheet"  href="/RailSAHAY/resources/css/irbdu/bduinput.css">

<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">



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
    overflow-x:scroll;
    padding:5px;
}
td.popup-row
{
    padding:0px !important;
}
input, select {
    height: 40px;
    line-height: 50px;
    /* vertical-align: middle; */
    width: 70%;
    padding: 0 20px;
}
</style>
<!--<style>
.table-brkup td.lbl
{
	font-size:13px;
	color:#4d4d4d;
	font-weight:500;
}
.table-brkup td.val
{
	font-size:13px;
	color:#000;
	font-weight:600;
}

</style>-->
<script>
	
    var htmlstr="";
    $(document).ready(function()
    {
        //alert("reeeady");
        fetchWgmtDetail('<%=WgmtRakeID%>','<%=WgmtID%>','<%=WgmtSttn%>','<%=WgmtdateFrom%>','<%=WgmtdateTo%>');
        //alert("readyyyy");
    });
</script>        
<script src="/RailSAHAY/resources/js/rakewgmtdtls.js"></script>
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

	<!--  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
	<div class="container-fullwidth">-->	
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12" style="margin:1px;padding:1px;">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:5px;">
                <h3 data-aos="fade-up" data-aos-delay="100"><%=E.EH("RAKE WEIGHMENT DETAIL")%></h3>
                <h6 data-aos="fade-up" data-aos-delay="100"><%=E.EH("DATE FROM: "+WgmtdateFrom+"| DATE TO: "+WgmtdateTo+"| RAKE ID: "+WgmtRakeID+"| STTN FROM: "+SttnFrom+"| STTN TO: "+SttnTo+"| WGMT STTN: "+WgmtSttn+"| WGMT TIME: "+WgmtTime+"| WAGON COUNT: "+WgmtCount)%></h6>
                <!--<span class="float-right"><a href="/RailSAHAY/pages/SAHAYDashboard.jsp" class="card-link1"><i class="fas fa-arrow-left"></i>&nbsp;Back to Dashboard</a></span>;-->
              </div>
            </div>
           </div>
          </div>
 <!--<form id="frmTest" target="_blank" > -->	       
          <div class="container-fullwidth" style="padding:1px;">
		<div class="row">
		<div class="col-lg-12 col-sm-12">
                    <div class="card">
                        <!--<div class="card-header head-lbl">
                         </div>-->
                        <div class="card-body data-grid" id="divMeasure">
                           
                        </div>
                        <div>
                           <h6 class="footer-heading mb-4">Note: All figures are in Tonnes</h6> 
                        </div>
                    </div>
		</div>
		</div>
          </div>
<!--</form>-->	
	
<!--</div>-->		


<%@ include file="/pages/GG_Footer_Wgmt.jspf" %>

            