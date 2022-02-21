<%@ include file="/pages/GG_Header.jspf" %>
<% 
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"NODAL OFFICER LIST","/RailSAHAY/pages/NodlOfcrList.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>
<script>
	$(document).ready(function(){
	fetchNodlOfcrList('','');
	});
function exportExcel(tblid, dataname)
{
    var tab_text = '<table border="1px" style="font-size:20px" ">';
    var textRange;
    var j = 0;
    var tab = document.getElementById(tblid); // id of table
    var lines = tab.rows.length;

    // the first headline of the table
    if (lines > 0) {
        tab_text = tab_text + '<tr style="background:#b32400;color:#fff;font-weight:600;">' + tab.rows[0].innerHTML + '</tr>';
    }

    // table data lines, loop starting from 1
    for (j = 1 ; j < lines; j++) {
        tab_text = tab_text + "<tr>" + tab.rows[j].innerHTML + "</tr>";
    }

    tab_text = tab_text + "</table>";
    tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");             //remove if u want links in your table
    tab_text = tab_text.replace(/<img[^>]*>/gi,"");                 // remove if u want images in your table
    tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");    // reomves input params
    // console.log(tab_text); // aktivate so see the result (press F12 in browser)

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

     // if Internet Explorer
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus();
        sa = txtArea1.document.execCommand("SaveAs", true, dataname+".xls");
    }
    else // other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));

    return (sa);
}
</script>	
<style>
nav.navbar .dropdown-menu span {
    position: static;
    font-size: 1rem !important;
    color: #333;
}
</style>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12 ml-1">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("NODAL OFFICERS")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-sm-12">

        <form class="form-inline">
	<table style="width:100%;"><tr><td>
           <div class="search_wrap" style="width:100%;">
	     <div class="container">			
		<div class="block_wrap clearfix row">                          
		 <select data-size="7" data-live-search="true" class="custom_selectbox selectpicker btn-primary fill_selectbtn_in own_selectbox" data-title='<%=E.EH("Zone")%>' id="txtZone" data-width="100%">
		 </select> 
                </div>
             </div>
            </div>
	</td><td>
           <div class="search_wrap" style="width:100%;">
	     <div class="container">			
		<div class="block_wrap clearfix row">                          
		 <select data-size="7" data-live-search="true" class="custom_selectbox selectpicker btn-primary fill_selectbtn_in own_selectbox" data-title='<%=E.EH("Division")%>' id="txtDvsn" data-width="100%">
		 </select> 
                </div>
             </div>
            </div>
	</td></tr></table>
      </form>
  	     </div>
	</nav>
          <div class="row">
	<div class="col-lg-10 col-sm-12 offset-lg-1">
		<button class="btn btn-sm btn-primary" onclick="exportExcel('tblNodlOfcrList','exported_data');">Export to Excel</button>
		<div id="divNodlOfcr" class="trmllistdata"></div>
        </div>	
      </div>
        <script>
	    $(document).ready(function() {		
var list = $("#txtZone");
$.each(aZone, function(index, item) {
  var zonetext=item.substring(item.indexOf('-')+1);
  var zoneval=item.substring(0, item.indexOf('-'));

  list.append(new Option(zonetext,zoneval));
});
$("#txtZone").on('change', function (e) {
    var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    $("button[data-id=txtDvsn] span.filter-option").html("Division");
    fetchNodlOfcrList(valueSelected,"");
});
var list1 = $("#txtDvsn");
$.each(aDvsn, function(index, item) {
  var dvsntext=item.substring(item.indexOf('-')+1);
  var dvsnval=item.substring(0, item.indexOf('-'));

  list1.append(new Option(dvsntext,dvsnval));
});
$("#txtDvsn").on('change', function (e) {
    var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    $("button[data-id=txtZone] span.filter-option").html("Zone");
    fetchNodlOfcrList("",valueSelected);
});
      	    });
		</script>

<%@ include file="/pages/GG_Footer.jspf" %>
