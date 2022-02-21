<%@ include file="/pages/GG_Header.jspf" %>  

<%strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Freight Rates","/RailSAHAY/pages/FrgtRates.jsp",UserDevice,Browser);%>

<link rel="stylesheet" href="/RailSAHAY/resources/css/jquery.dataTables.min.css"></style>
<script type="text/javascript" src="/RailSAHAY/resources/js/jquery.dataTables.min.js"></script>
<% String strDtls[][]=(String[][])request.getAttribute("Details");%>
 
<META CONTENT="text/html; charset=iso-8859-1" HTTP-EQUIV="Content-Type">
<nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;"></nav>
    <div class="row align-items-center justify-content-center text-center">
        <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4e" data-aos="fade-up" data-aos-delay="100"><%=E.EH("Freight Rates")%></h3>
              </div>
            </div>
       </div>
  </div>    
</nav>
        
<div class="row mx-auto">
    <div class="col-lg-7 col-sm-12" style="height:75vh;overflow-y:scroll;padding:5px;">
        <table id="myTable" class="display table table-striped table-bordered tabformat table-sm">
            <thead>
                    <tr>
                            <th rowspan="2" class="align-middle"><%=E.EH("SR")%>.</th>
                            <th rowspan="2" class="align-middle"><%=E.EH("Commodity Name")%></th>
                            <th colspan="2" style="text-align:center;"><%=E.EH("Rate Class")%></th>
                    </tr>
                    <tr>
                            <th class="text-nowrap"><%=E.EH("Train Load")%></th>
                            <th  class="text-nowrap"><%=E.EH("Wagon Load")%></th>
                    </tr>
            </thead>
            <tbody>
            <%for(int i=0;i<strDtls.length;i++){%>
                    <tr>
                            <td><%=(i+1)%></td>
                            <td style="text-align:left;"><%=strDtls[i][1] %></td>
                            <td class="tdlink" onclick="fetchDtls('<%=strDtls[i][3]%>');" onmouseover="this.style.cursor='pointer';"><%=strDtls[i][3]%></a></td>
                            <td class="tdlink" onclick="fetchDtls('<%=strDtls[i][4]%>');" onmouseover="this.style.cursor='pointer';"><%=strDtls[i][4]%></a></td>
                    </tr>
                    <% } %>
            </tbody>
            <tfoot><tr><th colspan="4"><%=E.EH("Click on Rate Class to Get Detail")%></th></tr></tfoot>
        </table>
    </div>
    <div class="col-lg-5 col-sm-12 .h-100 table-responsive" id="divClassDtls" style="overflow:scroll;overflow-x:hidden;max-height:520px;">        
    </div>
</div><!-- row end-->     

<%@ include file="/pages/GG_Footer.jspf" %>

<script>
$(document).ready(function(){
    $('#myTable').dataTable({
    "bSort": false
  });
});
</script>
<script>
function fetchDtls(rateclass) 
{
    $("#divClassDtls").show();
    var htmlrateclass='<table class="table table-striped table-bordered tabformat table-sm"><tr><th rowspan="2" class="align-middle">Rate Class</th><th colspan="2" style="text-align:center;">Distance (in Kms)</th><th>Rate (In Rs)</th></tr><tr><th>From</th><th>To</th><th>Per Ton</th></tr>';
    var myurl="/RailSAHAY/SHY_FrgtRatesDtls?RateClss="+rateclass;
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var erormesg=obj.ErorMesg;
		if(erormesg != "")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.SttnList.length;i++)
		{
			var cls=obj.SttnList[i].Class;
			var distfrom=obj.SttnList[i].DistFrom;
			var distto=obj.SttnList[i].DistTo;
			var rate=obj.SttnList[i].RatePerTon;
			htmlrateclass+='<tr><td>'+cls+'</td><td>'+distfrom+'</td><td>'+distto+'</td><td>'+rate+'</td></tr>';
		}
		htmlrateclass+='</table>';
		$("#divClassDtls").html(htmlrateclass);          
                }
            });
}
</script>