	<%@ include file="/pages/GG_Header.jspf" %>
        <%@ page import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>
<% 

//strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"DASHBOARD","/RailSAHAY/pages/SAHAYDashboard.jsp",UserDevice,Browser);
%>
<%@ page import = "user.UserProfDtls,model.valubean.PymtCartBean,java.util.*;" %>

<% 
String strUserID=(String)session.getAttribute("custuserid");  

String strCustType=(String)session.getAttribute("custtype");  
System.out.println("CustomerType:"+strCustType);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String strDateTo=dateFormat.format(date);
    System.out.println("strDateFrom:"+strDateTo.substring(0,8));
    String strDateFrom=strDateTo.substring(0,8)+"01";

    Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date todate1 = cal.getTime();    
        String fromdate1 = dateFormat.format(todate1);
        
 if(strUserID==null) strUserID="";
 
 System.out.println("strUserID::"+strUserID+"||||");
 
      if(strUserID.equals(""))
      {
   %>
        <jsp:forward page="eDmndLogin.jsp"/>
   <%
      } 
      UserProfDtls userdtls = new UserProfDtls((String)session.getAttribute("custuserid"));  
      
      ArrayList<String> orgList =userdtls.getCustOrgn();      

      ArrayList<ArrayList<String>> psorgList    =   userdtls.getPrimseccustOrgn();
      for(int i=0;i<psorgList.size();i++)
        System.out.println("Org"+i+":"+psorgList.get(i).get(0)+":"+psorgList.get(i).get(1));
      
      String raiseCR=(String)request.getAttribute("CR");
      String CRid=(String)request.getAttribute("CRid");
      String CRreply=(String)request.getAttribute("CRreply");
      String CRerror=(String)request.getAttribute("CRerror");
      
      String cncrctgr=(String)request.getAttribute("cncrctgr");
      String locnType=(String)request.getAttribute("locnType");
      String subctgr=(String)request.getAttribute("subctgr");
      String locncr=(String)request.getAttribute("locncr");
      String railAuth=(String)request.getAttribute("railAuth");
      String mesgcr=(String)request.getAttribute("mesgcr");
      java.util.ArrayList strCart=null;
      int intCartSize=0;
      try
      {      
      	strCart=(ArrayList)session.getAttribute("CartPymtArr");
	if(strCart==null)
		intCartSize=0;
	else
		intCartSize=strCart.size();
      }
      catch(Exception e)
      {
	intCartSize=0;
      }
		
                
   %>


<script src="/RailSAHAY/resources/js/asttn.js"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>
<script src="/RailSAHAY/resources/js/frgtpymt.js"></script>
<script src="/RailSAHAY/resources/js/pymtcart.js"></script>
<script src="/RailSAHAY/resources/js/fbdcookie.js"></script>
<script src="/RailSAHAY/resources/js/RgstSecCust.js"></script>
<script src="/RailSAHAY/resources/js/multiselect.min.js"></script>  
<script src="/RailSAHAY/resources/js/addmdfyseccust.js"></script>
<!--<script src="/RailSAHAY/resources/js/rakewgmtdtls.js"></script>-->
  
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/RailSAHAY/resources/css/custdashboard.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/frgtpymt.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/fbdcookielight.css">


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

</style>
    <script>
	var GG_SelCustCtgr="";
	var GG_CartSecChrgId="";
	var GG_CartSecSttn="";
	var GG_CartCust="";
	var GG_CartHndgAgnt="";
	var GG_CartHndgAgntName="";
	var GG_CartSecChrgType="";
	var GG_CartPymtArr=[];
	var GG_CartSize=<%=intCartSize%>;

	<% if(intCartSize>0) { %>	
		GG_CartPymtArr=[<% for(int i=0;i<intCartSize;i++) { %><% if(i==(intCartSize-1)) { %>"<%=((PymtCartBean)strCart.get(i)).getChrgId()%>" <% } else { %> "<%=((PymtCartBean)strCart.get(i)).getChrgId()%>",<% } %><% } %>];
	<% } %>
    $(document).ready(function() {
	<% if(strCustType.equals("B") || strCustType.equals("P")) { %>
	GG_SelCustCtgr="P";
	$("#custList").show();
	$("#custListSec").hide();
	fetchOtsgChrgSmry();
	fetchOtsgChrg('');
	<% } %>
	<% if(strCustType.equals("S")) { %>
	GG_SelCustCtgr="S";
	$("#custListSec").show();
	$("#custList").hide();
	fetchOtsgSecChrgSmry();
	fetchOtsgSecChrg('');
	<% } %>

});


function printTable(tableID, titleID, header)
{
   //alert(document.getElementById(tableID));
    //alert(document.getElementById(tableID).text);
    //alert(document.getElementById(tableID).innerHTML);
   //alert("inside printTable11#"+tableID);
   //alert($("#"+tableID).DataTable());
   //$("#"+tableID).dataTable().fnDestroy();
   //alert("inside printTable22");
    $("#"+tableID).DataTable( {
        dom: 'Bfrtip',
        buttons: [
            //'print',
            //alert('mid');
            {
                 extend: 'print',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }            
            },
            'copyHtml5',
            //'excelHtml5',
            {
                 extend: 'excelHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID         
            },
            //'csvHtml5',
            {
                 extend: 'csvHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID         
            },
            //'pdfHtml5', 
            {
                 extend: 'pdfHtml5',
                 orientation: 'landscape',
                 pageSize: 'A4' ,
                 title: titleID ,
                 messageTop: header
//                 exportOptions: {
//                                columns: [1, 2, 3, 4, 5, 6, 7, 8]
//                            }            
            }
        ],
        orientation: 'landscape'
    } );
    
     //alert('exit');
}    

    var fnrList="";

        var sttnstring='';
            for (var i=0; i < aSttn.length;++i){
                sttnstring += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }                
 function getCustWgmt(inptrxnid, fromdate, todate, sttncode)
{
    //alert("getCustWgmt js"+GG_SelCustCtgr);
   	$("#dataD").hide();
	$("#frmSr").hide();
	$("#frmCncrn").hide();
	$("#trmlProf").hide();
	$("#divTrmlProf").html("");
	$("#NodlOfcr").hide();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").show();
        $("#dsCont").slideUp();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
        
     if(inptrxnid=="F"){   
    var custsttnlist=document.getElementById("custsttnlist");
    custsttnlist.innerHTML = sttnstring;
    //alert(custsttnlist.innerHTML);                        
    
    var sttncode='';
    var sttndesc=$("#txtCustSttn").val();
    //alert(sttndesc);
    if(sttndesc.indexOf("-")>-1)
    {
        var sttn=sttndesc.substring(sttndesc.indexOf("-")+1);
        sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
    }	
    else
    {
        sttncode=sttndesc.toUpperCase();
    }
    //alert(sttncode);
    var datefrom="";
    var dateto="";

    datefrom=$("#txtDateFrom").val();
    dateto=$("#txtDateTo").val();
    var custsttn=sttncode; //$("#txtCustSttn").val();
    //alert(datefrom);
    //alert(dateto);
    var yyyy= dateto.substring(0, 4);
    var mm = dateto.substring(5, 7);
    var dd = dateto.substring(8);
    var dateto=dd+"-"+mm+"-"+yyyy;
    
    var yyyy= datefrom.substring(0, 4);
    var mm = datefrom.substring(5, 7);
    var dd = datefrom.substring(8);
    var datefrom=dd+"-"+mm+"-"+yyyy;
    
    GG_CrntView="CustWgmtSmry";
    var custcode='';
    var custOrgn =$("#custList").val();
    //alert(custOrgn);
    if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
       	custcode=custOrgn;
    else
	custcode=$("#custListSec").val();
        
    var myurl="/RailSAHAY/FrgtPymtJson";
    $("#divCustWgmt").html('<div class="spinner-border text-danger"></div>');



        var htmlotsgpymt='<form id="frmCW" name="frmCW"  ><input type=hidden id="TrxnId"    name="TrxnId" value=""/></form>';

	htmlotsgpymt+='<div class="row"><div class="col-lg-12 col-sm-12"><p class="ctgr-title" style="margin-bottom:2px;">Rake Weighment Detail</p>';
        //htmlotsgpymt+='<p class="sec-cust-pymt-hedr"><i class="fas fa-circle" style="color:#c6ecc6;stroke: black;stroke-width: 14px;"></i>&nbsp; Transactions done by secondary customer</p>';

        htmlotsgpymt+='<div class="col table-responsive" style="padding:0px;">'+
         '<table id="tblWgmtSmry"  style="padding:1px;text-align: center" class="display table table-striped table-bordered tabformat table-sm table-mis">'+       
        '<thead style="line-height:10px;">' +
        '<tr class="p-stts">' +
        '<th rowspan="2" style="white-space: nowrap;">S. No.</th>' +
        '<th colspan="5" class="center" >RAKE</th>' +
        '<th colspan="3" >WEIGHMENT</th>' +
        '</tr>' +
        '<tr class="p-stts">' +
        '<th style="white-space: nowrap;width:15%;"> FNR NUMBER</th>' +
        '<th style="white-space: nowrap;">STATION FROM</th>' +
       '<th style="white-space: nowrap;">STATION TO</th>' +
       '<th >CONSIGNOR</th>' +
        '<th >CONSIGNEE</th>' +
        '<th >STATION </th>' +
        // '<th >VENDOR</th>' +
        '<th >TIME</th>' +
        '<th style="white-space: nowrap;width:15%;">NO. OF LOADED WAGONS</th>' +
        '</tr>' +        
        '</thead><tbody  style="line-height:6px;"><tr></tr>';

	$.ajax({
		url : myurl,
		type : "post",
                data: {Optn:'CUST_WGMT_SMRY',CustCode:custcode,TrxnId:inptrxnid,DateFrom:datefrom,DateTo:dateto,CustLocn:custsttn},
		async : true,
		success : function(data) {

        		var obj= JSON.parse(data);                       
			var stts=obj.Stts;
                        //alert("stts");
			var erormesg=obj.ErorMesg;                        
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}                        
			if(obj.CustWgmt.length==0)
			{
                            htmlotsgpymt+='<tr><td colspan="8">NO DATA FOUND</td></tr>';
			}
                        //alert(obj.CustWgmt.length);
                        var j=0;
			for(var i=0;i<obj.CustWgmt.length;i++)
			{                
                        //alert("inside");
                        j=i+1;
                            var rakeid=obj.CustWgmt[i].RakeId;
                            var fnrnumb=obj.CustWgmt[i].FNRNumb;
                            var sttnfrom=obj.CustWgmt[i].SttnFrom;
                            var sttnto=obj.CustWgmt[i].SttnTo;
                            var sttn=obj.CustWgmt[i].Sttn;
                            var vendor=obj.CustWgmt[i].VendorCode;
                            var cnsr=obj.CustWgmt[i].CustCode;
                            var cnsg=obj.CustWgmt[i].CustName;
 
                            var wgmtid=obj.CustWgmt[i].WgmtId;
                            var trxntime=obj.CustWgmt[i].TrxnTime;
                            var wgmtcount=obj.CustWgmt[i].WgmtCount;
                            //alert("inside4#"+wgmtcount);
                            var custhead    =   "";
                            
                            var colr = "text-info";
                            var backcolor = "white";

                            htmlotsgpymt+='<tr style="vertical-align: middle;" >';
                           //htmlotsgpymt+='<td class="center" style="white-space: nowrap;vertical-align: middle;"  onclick="showWgmtDetail1(\''+rakeid+'\',\''+wgmtid+'\',\''+sttn+'\',\''+datefrom+'\',\''+dateto+'\',\''+sttnfrom+'\',\''+sttnto+'\',\''+trxntime+'\',\''+wgmtcount+'\');"  >'+rakeid+'</td><td class="center" style="vertical-align: middle;">'+sttnfrom+'</td><td class="center" style="vertical-align: middle;" >'+sttnto+'</td><td class="center" style="vertical-align: middle;" >'+sttn+'</td><td class="center" style="vertical-align: middle;" >'+vendor+'</td><td class="center" style="vertical-align: middle;" >'+trxntime+'</td><td class="center" style="vertical-align: middle;" >'+wgmtcount+'</td><td class="center '+colr+'" ><button style="line-height:8px;padding:1px;" class="btn btn-primary" ><i class="fas fa-info-circle"></i></button></td>';                           
                           htmlotsgpymt+='<td class="center" style="vertical-align: middle;">'+j+'</td><td class="trxn-success" title="Click here to get weighment detail" style="white-space: nowrap;vertical-align: middle;"  onclick="showWgmtDetail1(\''+rakeid+'\',\''+wgmtid+'\',\''+sttn+'\',\''+datefrom+'\',\''+dateto+'\',\''+sttnfrom+'\',\''+sttnto+'\',\''+trxntime+'\',\''+wgmtcount+'\',\''+fnrnumb+'\');"  >'+fnrnumb+' <i class="fas fa-info-circle"></i></td><td class="center" style="vertical-align: middle;">'+sttnfrom+'</td><td class="center" style="vertical-align: middle;" >'+sttnto+'</td><td class="center" style="vertical-align: middle;" >'+cnsr+'</td><td class="center" style="vertical-align: middle;" >'+cnsg+'</td><td class="center" style="vertical-align: middle;" >'+sttn+'</td><td class="center" style="vertical-align: middle;white-space: nowrap;" >'+trxntime+'</td><td class="center" style="vertical-align: middle;" >'+wgmtcount+'</td>';                                                      
                           htmlotsgpymt+='</tr>';
                          }
                          //alert("after for");
                        htmlotsgpymt+='</tbody></table></div>';

			$("#divCustWgmt").html(htmlotsgpymt);
                        //printTable('tblWgmtSmry','','');
                        
                        $(".table-mis").dataTable();
		}
	});
     }
}         
 
    	function fetchODRDtls()
    	{
	if(GG_SelCustCtgr=="S")
		return true;

        GG_CrntView="OtsgIndts"; 
        $("#dataD").show();
        $("#frmSr").hide();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
          $("#divDemo").hide();
    		//alert("Outstanding Indents Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
               // alert(custuserid);
               var custOrgn="";
                if('<%=orgList.size()%>'*1 >1){
                    custOrgn =document.getElementById("custList").value;
                }else{
                    custOrgn=document.getElementById("custList").value;
                }
                //alert(custOrgn);
               
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard?callfor=ODR&custuserid=" + custuserid+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                      // alert("data:"+data);
                      
                       var obj= data;
                     var htmlD="<div class='float-center'><p class='ctgr-title'>Outstanding Indents</p></div>";
                     for(var i=0;i<obj.length;i++)
			{
   			    htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="col-lg-1 col-sm-2">';
                            htmlD+='<button class="btn btn-warning mr-3">'+(i+1)+'</button></div><div class="col-lg-11 col-sm-10"><h3 class="h4">';
                            htmlD+=obj[i].sttnfrom+'/'+obj[i].dmndNo+'/'+obj[i].dmndTime+'</h3>';
			    htmlD+='<p class="p-od">'+obj[i].sttnfrom+'&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;'+obj[i].sttnto+'</p><hr/>';
                            htmlD+='<p class="p-stts">'+obj[i].rakecmdt+'/'+obj[i].raketype+'/'+obj[i].unts+'</p>';
                            htmlD+='</div></div></div>';
		<%--
                        htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-6 d-flex align-items-center justify-content-between">';
                        htmlD+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text"><h3 class="h4">';
                        htmlD+=obj[i].sttnfrom+'/'+obj[i].dmndNo+'/'+obj[i].dmndTime+'</h3></div>';   
                        htmlD+='</div><div class="project-date"><span class="hidden-sm-down">'+obj[i].sttnfrom+'->'+obj[i].sttnto+'</span></div>';
                        htmlD+='</div><div class="right-col col-lg-6 d-flex align-items-center"><div class="time"><i class="far fa-clock"></i>  '+obj[i].rakecmdt+'/'+obj[i].raketype+'/'+obj[i].unts+' </div>';
                        htmlD+='</div></div></div>';
                    --%>    
                        }
                         $("#dataD").html(htmlD);
                },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
                
    	}
    	function fetchCnmtDtls()
    	{
        
	if(GG_SelCustCtgr=="S")
		return true;

        GG_CrntView="OnRunCnsmt";
        fnrList="";
        $("#dataD").show();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
        $("#frmSr").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
          $("#divDemo").hide();   
    		//alert("Consignment Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
              //  alert(custuserid);
              var custOrgn="";
                if('<%=orgList.size()%>'*1 >1){
                    custOrgn =document.getElementById("custList").value;
                }else{
                    custOrgn=document.getElementById("custList").value;
                }
                //alert(custOrgn);
              
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard?callfor=ONRUN&custuserid=" + custuserid+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                     // alert("data:"+data);
                     var obj= data;
                     var htmlD="<div class='float-center'><p class='ctgr-title'>On Run Consignments</p></div>";
                     for(var i=0;i<obj.length;i++)
			{
                            htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="col-lg-1 col-sm-2">';
                            htmlD+='<button class="btn btn-warning mr-3">'+(i+1)+'</button></div><div class="col-lg-11 col-sm-10"><h3 class="h4"><a href="/RailSAHAY/pages/CnmtOnMap.jsp?FNR='+obj[i].cnmtid+'" target="fnrFr" class="fnr-link" onclick="showFNRRoute();">';
                            htmlD+=obj[i].rakecmdt+'/'+obj[i].loddunts+'/'+obj[i].raketype+' (FNR: '+obj[i].cnmtid+')</a></h3>';
			    htmlD+='<p class="p-od"><a href="javascript:void(0)" onclick="showTrmlProf(\''+obj[i].sttnfrom+'\');"><i class="fas fa-circle text-success"></i>&nbsp;'+obj[i].sttnfrom+'</a>&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;<a href="javascript:void(0)" onclick="showTrmlProf(\''+obj[i].sttnto+'\');"><i class="fas fa-circle text-danger"></i>&nbsp;'+obj[i].sttnto+'</a></p>';
                            htmlD+='<p class="p-stts">'+obj[i].loadstts+'/'+obj[i].sttn+'(Division: '+obj[i].dvsn+')/'+obj[i].sttstime+'</p><hr/>';
                            htmlD+='<p class="p-eta"><i class="far fa-clock"></i> ETA AT '+obj[i].sttnto+': '+obj[i].etaDstn+' </p>';
                            htmlD+='</div></div></div>';                       
                            fnrList+='<option value="'+obj[i].cnmtid+'" />';
                        }
                        $("#dataD").html(htmlD);
                        $("#fnrlist").html(fnrList);
                        
                },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
    	}
        function fetchCountDtls()
    	{
                var custuserid  = "<%= userdtls.getTavcustid()%>";
                console.log("inside fetchCountDtls <%=orgList.size()%>");
                
                var custOrgn="";
	<% if(strCustType.equals("B") || strCustType.equals("P")) { %>
        if('<%=orgList.size()%>'*1 >1){
	    console.log("inside first block");
            custOrgn =document.getElementById("custList").value;
        }else{
	    console.log("inside second block");
            custOrgn=document.getElementById("custList").value;
        }
	<% } else { %>
	
        if('<%=orgList.size()%>'*1 >1){
	    console.log("inside first block");
            custOrgn =document.getElementById("custListSec").value;
        }else{
	    console.log("inside second block");
            custOrgn=document.getElementById("custListSec").value;
        }

	<% } %>
console.log("came out");
       // alert(custOrgn);
var myurl="/RailSAHAY/UserDashboard";
                       $.ajax({
			url : myurl,
                        type: 'post',
			data: {callfor:'CONT',custuserid:custuserid,org:custOrgn},
                        dataType: 'json',                  
                        success: function (data) {
			console.log("Data Received:"+data);
                       var obj= data;
                       var fnrCont=obj[0].fnrCont;
                       var odrCont=obj[0].odrCont;
                       var srCont=obj[0].srCont;
                       var cncrn=obj[0].cnrnCont;
                       //alert("fnrCont:"+fnrCont);
                       $("#fnrCont").html('<strong>'+fnrCont+'</strong>');
                       $("#cncrn").html('<strong>'+cncrn+'</strong>');
                       $("#srCont").html('<strong>'+srCont+'</strong>');
                       $("#odrCont").html('<strong>'+odrCont+'</strong>');
		       <% if(strCustType.equals("B") || strCustType.equals("P")) { %>
		       fetchOtsgChrgSmry();
			GG_SelCustCtgr="P";
		       <% } else  { %>
		       fetchOtsgSecChrgSmry();
			GG_SelCustCtgr="S";
			<% } %>
                       
                },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
    	}
        function fetchFile(fileName,reqId)
    	{
        
    		//alert("Total Count Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
                //alert(custuserid);
        // alert(custOrgn);
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard",
                        type: 'POST',
                        data: {callfor:'FILE',custuserid:custuserid,fileName:fileName,reqId:reqId},
                        dataType: 'binary',                  
                        success: function (data) {
                       //alert("data:"+data);
                        var url = URL.createObjectURL(result);
                            var $a = $('<a />', {
                              'href': url,
                              'download': fileName,
                              'text': "click"
                            }).hide().appendTo("body")[0].click();
                       
                },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
    	}
        
    function ajax_download(fileN,reId) {
   // alert(fileN+" ID: "+reId);
    var urlS="/RailSAHAY/UserDashboard";
    var $iframe,
        iframe_doc,
        iframe_html="";

    if (($iframe = $('#download_iframe')).length === 0) {
        $iframe = $("<iframe id='download_iframe'" +
                    " style='display: none' src='about:blank'></iframe>"
                   ).appendTo("body");
    }else{
        $('#download_iframe').remove();
        $iframe = $("<iframe id='download_iframe'" +
                    " style='display: none' src='about:blank'></iframe>"
                   ).appendTo("body");
    }

    iframe_doc = $iframe[0].contentWindow || $iframe[0].contentDocument;
    if (iframe_doc.document) {
        iframe_doc = iframe_doc.document;
    }

    iframe_html = "<html><head></head><body><form method='POST' action='"+urlS+"'>" +
                  "<input type=hidden name='callfor' value='FILE'/><input type=hidden name='custuserid' value=''/><input type=hidden name='fileName' value='"+fileN+"'/><input type=hidden name='reqId' value='"+reId+"'/></form>" +
                  "</body></html>";
//alert(iframe_html);
    iframe_doc.open();
    $('#download_iframe').empty();
    iframe_doc.write("");
    iframe_doc.write(iframe_html);
    $(iframe_doc).find('form').submit();
}
    	function fetchCncnDtls()
    	{

	if(GG_SelCustCtgr=="S")
		return true;

           GG_CrntView="RgstReq";  
        $("#dataD").show();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
        $("#frmSr").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
         $("#NodlOfcr").hide();
         $("#divDemo").hide();
    		//alert("Concern Detail");
                
                var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList").value;
        }
        //alert(custOrgn);
        
        
    		//alert("Service Request Detail");
               
                 var custuserid  = "<%= userdtls.getTavcustid()%>";
               // alert(custuserid);
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard",
                        type: 'POST',
                        data: {callfor:'CR',custuserid:custuserid,org:custOrgn},
                        dataType: 'json',                  
                        success: function (data) {
                       //alert("data:"+data);
                       
                        var obj= data;
                     var htmlD="<div class='float-center'><p class='ctgr-title'>Registered Requests</p></div>";
                     for(var i=0;i<obj.length;i++)
			{

			    htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="col-lg-1 col-sm-2">';
                            htmlD+='<button class="btn btn-warning mr-3">'+(i+1)+'</button></div><div class="col-lg-11 col-sm-10"><h3 class="h4">';
                            htmlD+='<a href="javascript:void(0);" onclick="trackRequest(\''+obj[i].crId+'\')" class="fnr-link">Ref Id: '+obj[i].crId+'</a>';
			    if(obj[i].sttsDesc=="PENDING")
		 		htmlD+='<span class="badge badge-danger float-right">'+obj[i].sttsDesc+'/'+obj[i].sttsTime+'</span>';		
			    if(obj[i].sttsDesc=="UNDER CONSIDERRATION")
		 		htmlD+='<span class="badge badge-warning float-right">'+obj[i].sttsDesc+'/'+obj[i].sttsTime+'</span>';		
			    if(obj[i].sttsDesc=="CLOSED")
		 		htmlD+='<span class="badge badge-success float-right">'+obj[i].sttsDesc+'/'+obj[i].sttsTime+'</span>';
			    htmlD+='</h3><hr/>';
                            htmlD+='<p class="p-stts">'+obj[i].crCtgr+'/'+obj[i].crSubCtgr+'</p><hr/>';
                            htmlD+='<p class="p-eta"> AT '+obj[i].sttn+'</p>';
                            htmlD+='</div></div></div>';
			    htmlD+='<div id="txt'+i+'" class="collapse offset-md-1 col-md-10"><small><pre>'+obj[i].rmrk+'</pre><br/><a href="#" onclick="ajax_download(\''+obj[i].fileName+'\',\''+obj[i].crId+'\')">'+obj[i].fileName+'</a></small></div>';
			<%--
                       htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-7 d-flex align-items-center justify-content-between">';
                        htmlD+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text"><a href="javascript:void(0);" onclick="trackRequest(\''+obj[i].crId+'\')"><h3 class="h4">'
                        htmlD+=obj[i].crId+'</h3><small> AT '+obj[i].sttn+'</small></a></div>';
                        htmlD+='</div><div class="project-date"><span class="hidden-sm-down">'+obj[i].crCtgr+':'+obj[i].crSubCtgr+' </span></div>';
                        htmlD+='</div><div class="right-col col-lg-5 d-flex align-items-center"><div class="time"><i class="far fa-clock"></i>  '+obj[i].sttsDesc+'/'+obj[i].sttsTime+' </div>';
                        htmlD+='</div><div id="txt'+i+'" class="collapse offset-md-1 col-md-10"><small><pre>'+obj[i].rmrk+'</pre><br/><a href="#" onclick="ajax_download(\''+obj[i].fileName+'\',\''+obj[i].crId+'\')">'+obj[i].fileName+'</a></small></div></div></div>';                        
			--%>
                        }
                        $("#dataD").html(htmlD); 
                
                 },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
    	}
        function raiseSR(){
            GG_CrntView="RaiseSR";
            $("#dataD").hide();;
            $("#frmSr").show();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
        $("#frmCncrn").hide();
             $("#alert1").hide();
        $("#alert2").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
          $("#divDemo").hide();
        }
        function raiseCncrn(){
            GG_CrntView="RaiseCncrn";
            $("#dataD").hide();;
            $("#frmSr").hide();
        $("#frmCncrn").show();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
             $("#alert3").hide();
        $("#alert4").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
         $("#hiCont").hide();
           /*$("#dsCont").slideUp();
           $("#shCont").show();*/
            $("#divDemo").hide();
        }
        function showCont(){
           $("#shCont").hide();
           $("#dsCont").slideDown();
              $("#hiCont").show();
        }
         function hideCont(){
           $("#hiCont").hide();
           $("#dsCont").slideUp();
             $("#shCont").show();
        }
         function fetchDemo(){
            GG_CrntView="DemoVideo";
            $("#dataD").hide();;
            $("#frmSr").hide();
        $("#frmCncrn").hide();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide;
        $("#divScndCustNew").hide();
        $("#alert3").hide();
        $("#alert4").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
         $("#hiCont").hide();
          $("#divDemo").show();
           /*$("#dsCont").slideUp();
           $("#shCont").show();*/

        }

        function profTrml(){
        //alert("profTrml");
        GG_CrntView="MyTrml";
            $("#dataD").hide();;
            $("#frmSr").hide();
            $("#frmCncrn").hide();
            $("#alert1").hide();
            $("#alert2").hide();
            $("#trmlProf").show();
	    $("#divFrgtPymt").hide();
            $("#CustWgmt").hide();
            $("#divScndCust").hide();
            $("#divScndCustNew").hide();
            $("#alert3").hide();
            $("#alert4").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
          $("#divDemo").hide();
        }
        function submitSR(){
        
        $("#alert1").hide();
        $("#alert2").hide();
        var custuserid  = "<%= userdtls.getTavcustid()%>";
        var srtype=document.getElementById("srtype").value;
        var locn=document.getElementById("locn").value;
        var fnr=document.getElementById("fnr").value;
        var mesg=document.getElementById("mesg").value;
                    var sttncode='';
	            
				if(locn.indexOf("-")>-1)
				{
					var sttn=locn.substring(locn.indexOf("-")+1);
					sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
				}	
				else
				{
					sttncode=locn.toUpperCase();
				}
                                
        var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList").value;
        }
        //alert(custOrgn);
        
         $.ajax({
                        url: "/RailSAHAY/RaiseSR?custuserid="+custuserid+"&srtype="+srtype+"&locn="+sttncode+"&fnr"+fnr+"&mesg="+mesg+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                      // alert("data:"+data);
                       
                        var obj= data;//JSON.parse(data);
                        var errorC=obj[0].errorC;
                        var reply=obj[0].reply;
                        var htmlA="";
                        
                        if(reply!="FAIL") {
                           htmlA="<strong>Success!</strong>"+reply; 
                           $("#alert1").html(htmlA);
                           $("#alert1").show();
                        }else{
                            htmlA="<strong>Error!</strong>"+errorC; 
                            $("#alert2").html(htmlA);
                            $("#alert2").show();
                        }
                         

                 },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
                
            fetchCountDtls();
        }
        function submitCncrn(){
       // alert("in Raise concern");
        $("#alert3").hide();
        $("#alert4").hide();
     /*   var crType=document.getElementById('hidval').value;
        var custuserid  = "<%= userdtls.getTavcustid()%>";
        var cncrctgr=document.getElementById("cncrctgr").value;
        var locncr=document.getElementById("locncr").value;
        var subctgr=document.getElementById("subctgr").value;
        var mesgcr=document.getElementById("mesgcr").value;
                    
	          //alert("/RailSAHAY/RaiseConcern?custuserid="+ custuserid+"&crType="+crType+"&cncrctgr="+cncrctgr+"&locncr"+locncr+"&subctgr="+subctgr+"&mesgcr="+mesgcr);  
                 
                 var locncr=document.getElementById("locncr").value;
                   var sttncode='';
	            
                if(locncr.indexOf("-")>-1)
                {
                        var sttn=locncr.substring(locncr.indexOf("-")+1);
                        sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
                }	
                else
                {
                        sttncode=locncr.toUpperCase();
                }
                
                document.getElementById("locncr").value=sttncode;
		alert("locncr:::"+locncr+"::sttncode::"+sttncode); */ 
        var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList").value;
        }
        //alert(custOrgn);		
        document.getElementById("custCncrn").value=custOrgn;
       /*  $.ajax({
                        url: "/RailSAHAY/RaiseConcern?custuserid="+ custuserid+"&crType="+crType+"&cncrctgr="+cncrctgr+"&locncr="+sttncode+"&subctgr="+subctgr+"&mesgcr="+mesgcr+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                      // alert("data:"+data);
                       
                        var obj= data;//JSON.parse(data);
                        var errorC=obj[0].errorC;
                        var reply=obj[0].reply;
                        var htmlA="";
                        
                        if(reply!="FAIL") {
                           htmlA="<strong>Success!</strong>"+reply; 
                           $("#alert3").html(htmlA);
                           $("#alert3").show();
                        }else{
                            htmlA="<strong>Error!</strong>"+errorC; 
                            $("#alert4").html(htmlA);
                            $("#alert4").show();
                        }
                         

                 },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
                
            fetchCountDtls();*/
            document.frmInpt1.submit();
        }
    	function fetchSRDtls()
    	{
	
	if(GG_SelCustCtgr=="S")
		return true;

        var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList").value;
        }
        //alert(custOrgn);
        
        
    		//alert("Service Request Detail");
                $("#dataD").show();
                 $("#frmSr").hide();
                $("#trmlProf").hide();
                $("#divTrmlProf").html("");
                 $("#NodlOfcr").hide();
                  $("#divDemo").hide();
                 var custuserid  = "<%= userdtls.getTavcustid()%>";
               // alert(custuserid);
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard?callfor=SR&custuserid="+custuserid+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                     //  alert("data:"+data);
                       
                        var obj= data;
                     var htmlD="";
                     for(var i=0;i<obj.length;i++)
			{

                       htmlD+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-7 d-flex align-items-center justify-content-between">';
                        htmlD+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text"><a href="#txt'+i+'" data-toggle="collapse"><h3 class="h4">'
                        htmlD+=obj[i].srvcType+'</h3></a></div>';
                        htmlD+='</div><div class="project-date"><span class="hidden-sm-down">'+obj[i].srvcId+'(FNR:'+obj[i].fnr+') </span></div>';
                        htmlD+='</div><div class="right-col col-lg-5 d-flex align-items-center"><div class="time"><i class="far fa-clock"></i>  '+obj[i].srvcStts+'/'+obj[i].postTime+' </div>';
                        htmlD+='</div><div id="txt'+i+'" class="collapse offset-md-1 col-md-10"><small>'+obj[i].srvcTxt+'</small></div></div></div>';                        
                        }
                        $("#dataD").html(htmlD);
                
                 },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
                
                
    	}
        
    /*    function chSubCtgr(){
    var ctgr=document.getElementById('cncrctgr').value;
    //alert("Category:"+ctgr);
    if(ctgr=="Edemand_User"){
         $("#subctgr").empty();
	$("#subctgr").append('<option  selected value="A">E-demand User Registration</option>');
	$("#ctgrOth").hide();
          $("#sctgrOth").hide();
    }
    if(ctgr=="Consignment")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="B">Diversion</option>');
	$("#subctgr").append('<option value="C">Rebooking</option>');
	$("#subctgr").append('<option value="D">Short Destination Delivery</option>');
	$("#subctgr").append('<option value="E">Stacking Permission</option>');
	$("#subctgr").append('<option value="F">Demurrage Waiver</option>');
	$("#subctgr").append('<option value="G">Wharfage Waiver</option>');
	$("#subctgr").append('<option value="X">Others</option>');
    } if(ctgr=="Scheme")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="I">Long Term Tariff Contract (LTTC)</option>');
	$("#subctgr").append('<option value="J">Round Trip Traffic (RTT)</option>');
	$("#subctgr").append('<option value="K">Station To Station Rate Scheme(STS)</option>');
	$("#subctgr").append('<option value="L">Private Freight Terminal Scheme (PFT)</option>');
	$("#subctgr").append('<option value="M">Liberalized Siding Rule Scheme (LSR)</option>');
	$("#subctgr").append('<option value="N">Special Freight Train Operator (SFTO)</option>');
	$("#subctgr").append('<option value="O">Automobile Freight Train Operator (AFTO)</option>');
	$("#subctgr").append('<option value="P">Liberalized Wagon Investment Scheme (LWIS)</option>');
	$("#subctgr").append('<option value="Q">General Purpose Wagon Investment Scheme (GPWIS)</option>');
	$("#subctgr").append('<option value="R">RoadRailer (RRU)</option>');
	$("#subctgr").append('<option value="S">Freight Advance Scheme (FAS)</option>');
	$("#subctgr").append('<option value="T">Wagon Leasing Scheme (WLS)</option>');
	$("#subctgr").append('<option value="U">Merry Go Round Scheme (MGR)</option>');
	$("#subctgr").append('<option value="Y">Others</option>');
    } if(ctgr=="Others")
    {
        $("#subctgr").empty();
	//$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="Z" selected >Others</option>');
         $("#ctgrOth").show();
        $("#sctgrOth").show();
    }
}

 function chgSubCtgr(){
    var subctgr=document.getElementById('subctgr').value;
    if(subctgr=="Others")
    {
    $("#sctgrOth").show();
    }else{
       
          $("#sctgrOth").hide();
    }
    
 } */
 
 
 function chSubCtgr(){
    var ctgr=document.getElementById('cncrctgr').value;
    //alert("Category:"+ctgr);
    if(ctgr=="Edemand_User"){
         $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option   value="X">E-Payment Application</option>');
	$("#ctgrOth").hide();
          $("#sctgrOth").hide();
       // document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>'; 
       // locnSelect();
    }
    if(ctgr=="Consignment")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="B">Diversion Application</option>');
	$("#subctgr").append('<option value="C">Rebooking Application</option>');
	$("#subctgr").append('<option value="D">Short Destination Delivery Application</option>');
    }
    if(ctgr=="Demurrage")
    {
        $("#ctgrOth").hide();
        $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="E">Stacking Permission Application</option>');
	$("#subctgr").append('<option value="F">Demurrage Waiver Application</option>');
	$("#subctgr").append('<option value="G">Wharfage Waiver Application</option>');
        // document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>';
    } 
    if(ctgr=="Terminal")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="L">Application for PFT</option>');
	$("#subctgr").append('<option value="V">Application for Auto Hub</option>');
	$("#subctgr").append('<option value="W">Co-User Permission</option>');
	$("#subctgr").append('<option value="M">Application for Siding</option>');
    }
    if(ctgr=="Discount")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="I">Application for Long Term Tariff Contract (LTTC)</option>');
	$("#subctgr").append('<option value="J">Application for Round Trip Traffic (RTT)</option>');
	$("#subctgr").append('<option value="K">Application for Station To Station Rate Scheme(STS)</option>');
	$("#subctgr").append('<option value="S">Application for Freight Advance Scheme (FAS)</option>');
	$("#subctgr").append('<option value="U">Application for Merry Go Round Scheme (MGR)</option>');
    }
    if(ctgr=="Investment")
    {
    $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	//$("#subctgr").append('<option value="M">Liberalized Siding Rule Scheme (LSR)</option>');
	$("#subctgr").append('<option value="N">Application for Liberalized Special Freight Train Operator (LSFTO)</option>');
	$("#subctgr").append('<option value="O">Application for Automobile Freight Train Operator (AFTO)</option>');
	//$("#subctgr").append('<option value="P">Liberalized Wagon Investment Scheme (LWIS)</option>');
	$("#subctgr").append('<option value="Q">Application for General Purpose Wagon Investment Scheme (GPWIS)</option>');
	//$("#subctgr").append('<option value="R">RoadRailer (RRU)</option>');
	$("#subctgr").append('<option value="T">Application for Wagon Leasing Scheme (WLS)</option>');
    }
    if(ctgr=="Scheme")
    {
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="Y">Application for offering New/Additional Traffic</option>');
    }
    if(ctgr=="Couser"){
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="W">Co-User Permission</option>');
    }
    if(ctgr=="GCT"){
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
        $("#subctgr").empty();
	$("#subctgr").append('<option class="hidden" selected disabled>Sub-Category</option>');
	$("#subctgr").append('<option value="H">Application For Gati Shakti Multi-Modal Cargo Terminal (GCT)</option>');
    }
}
 function chgSubCtgr(){
      //  $( ".no-user" ).show(); 
     //  document.getElementById('sbmt').style.display='block'; 
     //  document.getElementById('proceed').style.display='none';
    var subctgr=document.getElementById('subctgr').value;
    
   
    
    if(subctgr=="A" || subctgr=="B" || subctgr=="C" || subctgr=="D" || subctgr=="E" || subctgr=="F" || subctgr=="G" || subctgr=="K" || subctgr=="L" || subctgr=="M" || subctgr=="Y" || subctgr=="H") {
        document.getElementById('locntype').innerHTML='<option value = "D" selected>Division</option>';
         $("#sctgrOth").hide();
         locnSelect();
    }
   else if(subctgr=="X")
    {
    $("#sctgrOth").hide();
    document.getElementById('locntype').innerHTML='<option value = "Z" selected>Zone</option>';
    locnSelect();
    }else if(subctgr=="N" || subctgr=="O" || subctgr=="Q" || subctgr=="T" || subctgr=="V" ){
        document.getElementById('locntype').innerHTML='<option value ="R" >Railway Board</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }else if(subctgr=="U" || subctgr=="I" || subctgr=="J" || subctgr=="W" || subctgr=="S"){
        document.getElementById('locntype').innerHTML='<option value = "Z" selected>Zone</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }else if(subctgr=="P" || subctgr=="R"   ){
        document.getElementById('locntype').innerHTML=' <option class="hidden" selected  disabled>Location Type</option><option value ="R" >Railway Board</option><option value="Z">Zone</option><option value = "D">Division</option>';
          $("#sctgrOth").hide();
          locnSelect();
    }
    
 }
      

     
        function copyVal(val)
        {
        
         document.getElementById('hidval').value = val;
          $(".btn-concern").removeClass("btn-success");
            $(".btn-concern").addClass("btn-primary");
         
         if(val=="QUERY")
                {
                        $("#btnQ").removeClass("btn-primary");	
                        $("#btnQ").addClass("btn-success");
                  /*      $("#cncrctgr").empty();
                        $("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
                        $("#cncrctgr").append('<option value="Booking">Booking</option>');
                        $("#cncrctgr").append('<option value="Delivery">Delivery</option>');
                        $("#cncrctgr").append('<option value="Track_Trace">Track and Trace Facility</option>');
                        $("#cncrctgr").append('<option value="Station_Facilities">Station Facilities</option>');
                        $("#cncrctgr").append('<option value="Station_Open_Goods_Traffic">Station Open Goods Traffic</option>');
                        $("#cncrctgr").append('<option value="Commodity">Commodity</option>');
                        $("#cncrctgr").append('<option value="Wagon_Type">Wagon Type</option>');
                        $("#cncrctgr").append('<option value="NearestGoodsShed">Nearest Goods Shed</option>');
                        $("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
                        $("#cncrctgr").append('<option value="Freight_Schemes">Freight Schemes</option>');
                        $("#cncrctgr").append('<option value="Dummurage">Demmurage</option>');
                        $("#cncrctgr").append('<option value="Wharfage">Wharfage</option>');
                        $("#cncrctgr").append('<option value="Others">Others</option>'); */
                
                }
                if(val=="SUGGESTION")
                {
                        $("#btnS").removeClass("btn-primary");	
                        $("#btnS").addClass("btn-success");
                     /*   $("#cncrctgr").empty();
                        $("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
                        $("#cncrctgr").append('<option value="Booking">Booking</option>');
                        $("#cncrctgr").append('<option value="Delivery">Delivery</option>');
                        $("#cncrctgr").append('<option value="Station_Facilities">Station Facilities</option>');
                        $("#cncrctgr").append('<option value="Station_Open_Goods_Traffic">Station Open Goods Traffic</option>');
                        $("#cncrctgr").append('<option value="Wagon_Type">Wagon Type</option>');
                        $("#cncrctgr").append('<option value="NearestGoodsShed">Nearest Goods Shed</option>');
                        $("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
                        $("#cncrctgr").append('<option value="Freight_Schemes">Freight Schemes</option>');
                        $("#cncrctgr").append('<option value="Dummurage">Demmurage</option>');
                        $("#cncrctgr").append('<option value="Wharfage">Wharfage</option>');
                        $("#cncrctgr").append('<option value="Security">Security</option>');
                        $("#cncrctgr").append('<option value="Others">Others</option>'); */
                }
                
                if(val=="GRIEVANCE")
                {
                         $("#btnG").removeClass("btn-primary");	
                        $("#btnG").addClass("btn-success");
                 /*       $("#cncrctgr").empty();
                        $("#cncrctgr").append('<option class="hidden" selected disabled>Category</option>');
                        $("#cncrctgr").append('<option value="Employee_Behaviour">Behaviour of Employee</option>');
                        $("#cncrctgr").append('<option value="Platform_Surface">Platform Surface</option>');
                        $("#cncrctgr").append('<option value="Goods_Shed_Lighting">Lighting in Goods Shed</option>');
                        $("#cncrctgr").append('<option value="Approach_Road">Approach Road Condition</option>');
                        $("#cncrctgr").append('<option value="Consignment_Damage">Damage of Consignment</option>');
                        $("#cncrctgr").append('<option value="Consignment_Loss">Loss of Consignment</option>');
                        $("#cncrctgr").append('<option value="Corruption">Corruption</option>');
                        $("#cncrctgr").append('<option value="Freight_Charges">Freight Charges</option>');
                        $("#cncrctgr").append('<option value="Security">Security</option>');
                        $("#cncrctgr").append('<option value="Others">Others</option>'); */
                }
            
        
        }
       function fetchNodlOfcr(){
       

    GG_CrntView="NodlOfcr";

       $("#dataD").hide();
            $("#frmSr").hide();
            $("#frmCncrn").hide();
            $("#alert1").hide();
            $("#alert2").hide();
            $("#trmlProf").hide();
            $("#alert3").hide();
            $("#alert4").hide();
            $("#divTrmlProf").html("");
            $("#NodlOfcr").show();
	$("#divFrgtPymt").hide();
        $("#CustWgmt").hide();
        $("#divScndCust").hide();
        $("#divScndCustNew").hide();
         $("#divDemo").hide();
           fetchNodlOfcrList('','');
       }
       
    </script>
  <!--<script type="text/javascript">
       
	$(function() {
        $( "#dob" ).datepicker({
			changeMonth: true,
                        yearRange: "-100:-14", 
			changeYear: true
		});
        $( "#dialog-modal" ).dialog({
                        autoOpen: false,
                        closeOnEscape: false,
                        draggable: false,
                    	height: 140,
                        width: 130,
                        modal: true
		});
        });
	</script>-->    
    <script>
$(document).ready(function()
{
       GG_CrntView = "PendPymt";
        //alert("Onload");
	fetchCountDtls();
	/*fetchCnmtDtls();*/
	/*fetchOtsgChrg('');*/
        
         $("#ctgrOth").hide();
          $("#sctgrOth").hide();
          $("#shCont").hide();
        if('<%=raiseCR%>'=='CR'){
            raiseCncrn();
            var htmlA="";
                        
                    
    //  alert("<%=cncrctgr%>"+"   <%=subctgr%>");
      
      
                               
                        
                        if('<%=CRreply%>'!="FAIL") {
                           htmlA='<strong>Success!</strong> Your Freight Request has been successfully registered with Reference No: <strong><%=CRid%></strong> Details are shared on your email.<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'; 
                           $("#alert3").html(htmlA);
                           $("#alert3").show();
                           custPrompt("<strong>Success!</strong> Your Freight Request has been successfully registered with Reference No:<strong>"+"<%=CRid%>"+"</strong> Details are shared on your email.");
                        }else{
                          document.getElementById('cncrctgr').value='<%=cncrctgr%>'
                          document.getElementById('subctgr').value='<%=subctgr%>'
                          document.getElementById('locntype').value='<%=locnType%>'
                          document.getElementById('locncr').value='<%=locncr%>'
                          document.getElementById('railAuth').value='<%=railAuth%>'
                          document.getElementById('mesgcr').value='<%=mesgcr%>'
                            htmlA='<strong>Error!</strong><%=CRerror%><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'; 
                            $("#alert4").html(htmlA);
                            $("#alert4").show();
                        }
            
            
        }
        
        
        $( "#custList" ).change(function() {
            fetchCountDtls();
            if(GG_CrntView == "PendPymt")
                fetchOtsgChrg('');
            else if (GG_CrntView == "PymtHist")
                getPymtHist('');
            else if (GG_CrntView == "CustWgmt")
                getCustWgmt('','','','');
            else if (GG_CrntView == "RfndDtls")
                getRfndDtls('');
            else if (GG_CrntView == "RaiseCncrn")
                raiseCncrn();
            else if (GG_CrntView == "RaiseSR")
                raiseSR();
            else if (GG_CrntView == "MyTrml")
                profTrml();
            else if (GG_CrntView == "NodlOfcr")
                fetchNodlOfcr();
            else if (GG_CrntView == "DemoVideo")
                fetchDemo(); 
            else if (GG_CrntView == "OtsgIndts")
                fetchODRDtls();
            else if (GG_CrntView == "OnRunCnsmt")
                fetchCnmtDtls();
            else if (GG_CrntView == "RgstReq")
                fetchCncnDtls();
            else if(GG_CrntView == "SecCustV")
                fetchSecCust();
              
        });
        $( "#custListSec" ).change(function() {
            fetchCountDtls();
            if(GG_CrntView == "PendPymt")
                fetchOtsgSecChrg('');
            else if (GG_CrntView == "PymtHist")
                getPymtHist('');
            else if (GG_CrntView == "RfndDtls")
                getRfndDtls('');
            else if (GG_CrntView == "RaiseCncrn")
                raiseCncrn();
            else if (GG_CrntView == "RaiseSR")
                raiseSR();
            else if (GG_CrntView == "MyTrml")
                profTrml();
            else if (GG_CrntView == "NodlOfcr")
                fetchNodlOfcr();
            else if (GG_CrntView == "DemoVideo")
                fetchDemo(); 
            else if (GG_CrntView == "OtsgIndts")
                fetchODRDtls();
            else if (GG_CrntView == "OnRunCnsmt")
                fetchCnmtDtls();
            else if (GG_CrntView == "RgstReq")
                fetchCncnDtls();
            else if(GG_CrntView == "SecCustV")
                fetchSecCust();
              
        });


});
</script>
 
<style>
.jumbotron {border-radius: 0;margin-bottom: 0;}

.jumbotron.df-bg-primary {
  background-color: #083386;
}

.df-switch {
  text-align: center;
}
.btn-toggle {
  top: 50%;
  transform: translateY(-50%);
}
.btn-toggle {
  margin: 0 4rem;
  padding: 0;
  position: relative;
  border: none;
  height: 1.5rem;
  width: 5rem;
  border-radius: 1.5rem;
  color: #6b7381;
  background: #0059b3;
}
.btn-toggle:focus,
.btn-toggle.focus,
.btn-toggle:focus.active,
.btn-toggle.focus.active {
  outline: none;
}
.btn-toggle:before,
.btn-toggle:after {
  line-height: 1.5rem;
  width: 4rem;
  text-align: center;
  font-weight: 600;
  font-size: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 2px;
  position: absolute;
  bottom: 0;
  transition: opacity 0.25s;
}
.btn-toggle:before {
  content: 'PRIMARY';
  left: -5rem;
  color: #109fff;
}
.btn-toggle:after {
  content: 'SECONDARY';
  right: -5rem;
  opacity: 0.5;
  color: #F47280;
}
.btn-toggle > .inner-handle {
  border-radius: 13px;
  width: 65px;
  height: 13px;
  position: absolute;
  top: 6px;
  left: 8px;
  background-color: #0f71bd;
  box-shadow: inset 1px 1px 2px -1px black;
}
.btn-toggle.active > .inner-handle {
  background-color: #BD4053;
}
.btn-toggle > .handle:before {
  content: "";
  position: absolute;
  height: 34px;
  width: 34px;
  top: 35%;
  left: 11px;
  background-image: radial-gradient(circle at center, #0785da 5px, transparent 5px);
  background-size: 10px 10px;
  background-repeat: no-repeat;
}
.btn-toggle.active > .handle:before {
  background-image: radial-gradient(circle at center, #b30000 5px, transparent 5px);
}
.btn-toggle > .handle {
  position: absolute;
  top: -0.2875rem;
  left: 0.3875rem;
  width: 2.125rem;
  height: 2.125rem;
  border-radius: 1.125rem;
  background: #fff;
  transition: left 0.25s;
  border: 1px solid #ccc;
}
.btn-toggle.active {
  transition: background-color 0.25s;
}
.btn-toggle.active > .handle {
  left: 2.4175rem;
  transition: left 0.25s;
}
.btn-toggle.active:before {
  opacity: 0.5;
}
.btn-toggle.active:after {
  opacity: 1;
  color: #b30000;
}
.btn-toggle.active {
  background-color: #b30000;
}
.jumbotron
{
	padding:0px !important;
	padding-top:1rem !important;
}

</style>
<nav class="navbar navbar-expand-sm bg-origprimary" style="margin-top:1px;max-height:10px;">
	</nav>
      <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <nav class="side-navbar">
          <!-- Sidebar Header-->
          <div class="sidebar-header d-flex align-items-center">
            <div class="title">
              <h1 class="h4">Logged In As</h1>
              <p class="pcustid"><%= userdtls.getTavcustuserid()%></p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
          <ul class="list-unstyled">
	   
            <li><a href="javascript:void(0)" class="db-link" onclick="toggleOn();fetchChrg('')"><i class="fas fa-angle-right"></i>&nbsp;Pending Payments <%--<img src="/RailSAHAY/resources/images/newiconblink2.gif" style="width:50px;height:27px;">--%></a></li>
	   <li><a href="javascript:void(0)" class="db-link" onclick="toggleOff();getPymtHist('');"><i class="fas fa-angle-right"></i>&nbsp;Payment History</a></li>
          <li><a href="javascript:void(0)" class="db-link" onclick="toggleOff();getRfndDtls('');"><i class="fas fa-angle-right"></i>&nbsp;Refund History</a></li>
          <%--<li><a href="#" class="db-link" onclick="showPage('REG_SEC_CUST')"><i class="fas fa-angle-right"></i>&nbsp;Manage Secondary Customers</a></li>--%>
          <% if(!strCustType.equals("S")) { %>
	    <li><a href="javascript:void(0)" class="db-link" onclick="toggleOff();fetchSecCust()"><i class="fas fa-angle-right"></i>&nbsp;My Secondary Customers<img src="/RailSAHAY/resources/images/newiconblink2.gif" style="width:50px;height:27px;"></a></li>
	  <% } %>
          <li><a href="javascript:void(0)" class="db-link" onclick="raiseCncrn()"><i class="fas fa-angle-right"></i>&nbsp;Raise A Request</a></li>
            <!--li><a href="#lodgeConcern" aria-expanded="false" data-toggle="collapse"> <i class="far fa-edit"></i>  Lodge a Concern</a>
              <ul id="lodgeConcern" class="collapse list-unstyled ">
                <li><a href="#">Query</a></li>
                <li><a href="#">Suggestion</a></li>
                <li><a href="#">Grievance</a></li>
              </ul>
            </li-->
            <% if(!strCustType.equals("S")) { %>
                <li  ><a href="javascript:void(0)" id="rakedtls" class="db-link" onclick="toggleOff();getCustWgmt('I','','','')"><i class="fas fa-angle-right"></i>&nbsp;Rake Weighment Detail<img src="/RailSAHAY/resources/images/newiconblink2.gif" style="width:50px;height:27px;"></a></li>
            <% } %>
            <li><a href="javascript:void(0)" class="db-link" onclick="profTrml()"><i class="fas fa-angle-right"></i>&nbsp;My Terminal</a></li>
           <!-- <li><a href="charts.html"> <i class="fas fa-route"></i>  My Consignments</a></li>
            <li><a href="charts.html"> <i class="far fa-copy"></i>  My Schemes</a></li>-->
            <li><a href="#" class="db-link" onclick="fetchNodlOfcr();"><i class="fas fa-angle-right"></i>&nbsp;Nodal Officers</a></li>
         </ul>	 
          <!-- Sidebar Navidation Menus--><span class="heading">Help</span>
          <ul class="list-unstyled">
            <li><a href="javascript:void(0)" class="db-link" onclick="openHelpSupport();"><i class="fas fa-angle-right"></i>&nbsp;Help & Support</a></li>
	    <li><a href="javascript:void(0)" class="db-link" onclick="gotoRailMadad();"><i class="fas fa-angle-right"></i>&nbsp;Contact Us</a></li>
	  </ul>
	  <img src="/RailSAHAY/resources/images/sbibanner.jpg" class="img-fluid img-responsive" />
        </nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header" style="padding:2px;">
            <div class="container-fluid">
              <table style="width:100%;"><tr><td><h3 class="no-margin-bottom"><button class="btn btn-light" onclick="refreshDashboard();"><i class="fas fa-sync-alt"></i></button>&nbsp;&nbsp;Welcome,<%= userdtls.getTavcustfrstname() %>  <%= userdtls.getTavcustlastname()%> !</h3></td>
<% if(strCustType.equals("B")) { %>
<td>
  <div class="jumbotron bg-white">
    <div class="df-switch">
      <button type="button" class="btn btn-lg btn-toggle btn-switch" data-toggle="button" aria-pressed="false" autocomplete="off" id="btnPrimSec">
        <div class="inner-handle"></div>
        <div class="handle"></div>
      </button>
    </div>
  </div>
</td>
<td style="width:30%;" align="right">
<select  id="custList" tabindex="5" name="custList" class="btn btn-primary btn-sm float-right" style="width:100px;height:30px;">
<option value ="ALL1">FOR ALL</option>
<% for(int i=0;i<psorgList.size();i++) { String strCustCtgr=psorgList.get(i).get(0); if(strCustCtgr.equals("PRIMARY")) { %>
<option value="<%=psorgList.get(i).get(1)%>"><%=psorgList.get(i).get(1)%></option>
<% }} %>
</select>
<select  id="custListSec" tabindex="6" name="custListSec" class="btn btn-success btn-sm float-right" style="width:100px;height:30px;">
<option value ="ALL1">FOR ALL</option>
<% for(int i=0;i<psorgList.size();i++) { String strCustCtgr=psorgList.get(i).get(0); if(strCustCtgr.equals("SECONDARY")) { %>
<option value="<%=psorgList.get(i).get(1)%>"><%=psorgList.get(i).get(1)%></option>
<% }} %>
</select>
</td>
<% } %>
<% if(strCustType.equals("P")) { %>
<td>
  <span class="cust-ctgr-prim"><i class="far fa-address-card"></i>&nbsp;&nbsp;Primary Customer</span>
</td>
<td style="width:30%;" align="right">
<select  id="custList" tabindex="5" name="custList" class="btn btn-primary btn-sm float-right" style="width:100px;height:30px;">
<option value ="ALL1">FOR ALL</option>
<% for(int i=0;i<psorgList.size();i++) { String strCustCtgr=psorgList.get(i).get(0); if(strCustCtgr.equals("PRIMARY")) { %>
<option value="<%=psorgList.get(i).get(1)%>"><%=psorgList.get(i).get(1)%></option>
<% }} %>
</select>
</td>
<% } %>
<% if(strCustType.equals("S")) { %>
<td>
  <span class="cust-ctgr-sec"><i class="far fa-address-card"></i>&nbsp;&nbsp;Secondary Customer</span>
</td>
<td style="width:30%;" align="right">
<select  id="custListSec" tabindex="6" name="custListSec" class="btn btn-success btn-sm float-right" style="width:100px;height:30px;">
<option value ="ALL1">FOR ALL</option>
<% for(int i=0;i<psorgList.size();i++) { String strCustCtgr=psorgList.get(i).get(0); if(strCustCtgr.equals("SECONDARY")) { %>
<option value="<%=psorgList.get(i).get(1)%>"><%=psorgList.get(i).get(1)%></option>
<% }} %>
</select>
</td>
<% } %>
<td>
<div id="divCartIcon" class="float-right pull-right">
<%if(intCartSize>0) { %>
	<span id="cartGroup" onclick="showCart();"><img src="/RailSAHAY/resources/images/cart-icon1.jpg" class="img-cart"><span class="badge badge-light"><%=intCartSize%></span></span>
<% } %>
</div>
</td>
</tr></table>
            </div>
          </header>
          <!-- Dashboard Counts Section-->
          <section class="dashboard-counts no-padding-bottom" style="padding:2px;">
            <div class="container-fluid" id="dsCont">
              <div class="row bg-white has-shadow">
		<!-- Item -->
                <div class="col-xl-3 col-sm-6">
		  <img src="/RailSAHAY/resources/images/cornerribbon.png" style="position:absolute;top:-15px;left:-20px;" />
                  <div class="item d-flex align-items-center" onclick="toggleOn();fetchChrg('');">
                    <div class="icon bg-green"><i class="fas fa-rupee-sign"></i></div>
                    <div class="title"><span>Pending<br>Payments</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 30%; height: 4px;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number" id="pymtOtsg"><strong></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="toggleOff();fetchODRDtls();">
                    <div class="icon bg-violet"><i class="fa fa-hourglass-start" aria-hidden="true"></i></div>
                    <div class="title"><span>Outstanding<br>Indents</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 25%; height: 4px;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                      </div>
                    </div>
                    <div class="number" id="odrCont"><strong></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="toggleOff();fetchCnmtDtls();">
                    <div class="icon bg-red"><i class="fa fa-train" aria-hidden="true"></i></div>
                    <div class="title"><span>On Run<br>Consignments</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 70%; height: 4px;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                      </div>
                    </div>
                    <div class="number" id="fnrCont"><strong></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="toggleOff();fetchCncnDtls();">
                    <div class="icon bg-orange"><i class="fas fa-paste" aria-hidden="true"></i></div>
                    <div class="title"><span>Registered<br>Requests</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 50%; height: 4px;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-orange"></div>
                      </div>
                    </div>
                    <div class="number" id="cncrn"><strong></strong></div>
                  </div>
                </div>

              </div>
            </div>
            <button id="shCont" onclick="showCont()" class="btn-light  float-right" style="width:40px;height:40px;border-radius:50%;box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 3px 10px 0 rgba(0, 0, 0, 0.19);" >
                <i class="fas fa-chevron-down fa-2x"></i>
                </button>
                 <button id="hiCont" onclick="hideCont()" class="btn-light  float-right" style="display:none;width:40px;height:40px;border-radius:50%;box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 3px 10px 0 rgba(0, 0, 0, 0.19);" >
                <i class="fas fa-chevron-up fa-2x"></i>
                </button>
          </section>
           <section class="projects" style="padding:2px;">
            <div class="container-fluid" >
            <div style="width:100%;height:auto;overflow-y:auto;" id="dataD">
            
            </div>
                        <div id="divFrgtPymt" style="width:100%;height:auto;overflow-y:auto;"></div>
                        <!--<div id="divCustWgmt" style="width:100%;height:auto;overflow-y:auto;"></div>-->
             <div id="divScndCust" style="width:100%;height:auto;overflow-y:auto;"></div>
             <!-- <div id="divAdMfScndCust" style="width:100%;height:auto;overflow-y:auto;"></div> -->
               <div style="width:100%;height:auto;overflow-y:auto;padding:20px;" id="divScndCustNew" >
        <div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12 ml-1">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><a class="card-link1" href="javascript:void(0);" onclick="showMySecCust();"><i class="fas fa-arrow-left"></i></a>&nbsp;&nbsp;<%=E.EH("Add/Modify Secondary Customer")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-12 col-md-12 col-sm-12">
	      <form class="form-horizontal" method="post" > <!--class="form-inline">form class="form-horizontal" method="post"  -->
	          <table style="width:100%;">
                  <tr>
                    <td colspan="4">
                        <input class="form-control mr-sm-2 inptcap name" size="40" id="txtCustCode_AM" onclick="fetchOrg('ORG' ,'', '', '', '')" name="txtCustCode_AM" type="text" list="orglist" placeholder='<%=E.EH("PRIMARY CUSTOMER CODE")%>'>
			          <datalist id="orglist"></datalist>
                    </td>
                  </tr>
                        <tr>
	          	<td>
                            <input class="form-control mr-sm-2 inptcap name" size="40" id="txtScCustCode" onclick="fetchOrg('GORG' ,'', '', '', '')" name="txtScCustCode" type="text" list="orgseclist" placeholder='<%=E.EH("SECONDARY CUSTOMER CODE")%>'>
			          <datalist id="orgseclist"></datalist>
			</td>
                        <td><input class="form-control mr-sm-2 inptcap numb" id="txtMoblNumb" size="10"  name="txtMoblNumb" type="text" maxlength="10"  placeholder='<%=E.EH("Mobile Number")%>'>
				 
			</td>
                        <td><input class="form-control mr-sm-2 inptcap" id="txtEmailId" name="txtEmailId" size="30" type="text"  placeholder='<%=E.EH("EmailId")%>'>
				  
			</td>
			<td>
			<div class="btn-group" role="group" aria-label="Fetch Secondary Customers">
  <button type="button" class="btn btn-success text-white" onclick="event.preventDefault();getDataAMScCust();">Fetch</button>
  <button type="reset" class="btn btn-secondary">Reset</button>
</div>
	<%--		<button class="btn btn-data" onclick="event.preventDefault();getDataAMScCust();"><%=E.EH("Fetch")%></button>--%>
</td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
	<div class="col-lg-6 col-sm-12" style="padding:5px;">
		<div id="divUserDataList" style="overflow:auto;" class="trmllistdata"></div>
      </div>
	<div class="col-lg-6 col-sm-12" style="overflow:auto;padding:5px;" id="divUserDtlsPnl">
		<!--<div class="optncard">
			<ul class="nav nav-pills">
			    <li class="nav-item">
			        <a class="nav-link active" data-toggle="tab" href="#Schd"><%=E.EH("User Profile")%></a>
			    </li>
			</ul>
		</div>-->
		<div class="optncard" style="padding:2px;margin:2px;">
			<div class="tab-content">
				  <div class="tab-pane container active" id="Schd">
					<div id="divUserDtls" class="trmllistdata mr-2 timeline-centered"></div>
                                       <div id="example"> 
                                       <table class="table table-add-sec-cust">
                                       <tr>
                                        <td style="width:50%">Zone:
						<select id="multiselect" class="form-control" name="languages[]" multiple="multiple"></select>
					</td>
                                        <td style="width:50%">Division:
						<select id="multiselectD"  class="form-control"  multiple="multiple"></select>
					</td>
					</tr>
					</tr>
                                        <td style="width:50%">Station:
						<select id="multiselectS"  class="form-control"  multiple="multiple"></select>
					</td>
                                        <td style="width:50%">ChargeType:
					   <select id="lstChrgtyp"  class="form-control"  >
                                           <!--  <option value="F">FREIGHT</option> -->
                                             <option value="D">DEMURRAGE</option>
                                             <option value="W">WHARFAGE</option>
                                             <option value="L">LOCAL</option> 
                                             <option value="A">ALL</option> 
                                            </select>
					<button class="btn btn-success text-white btn-sm mt-2" title="Add Secondary Customer" onclick="addRow();"> Continue&nbsp;<i class="fas fa-angle-double-down"></i></button>
					</td>
					</tr>					
                                         </table>                                         
                                         <table id="tblLocn" class="table table-striped table-sm table-sec-cust">
                                           <tr><th></th><th style="width:10%">Zone</th><th style="width:10%">Division</th><th style="width:10%">Station</th><th style="width:15%">Charge Type</th><th style="width:10%">Cust Id</th><th  style="width:10%">Prm Org</th><th  style="width:10%">Sec Org</th></tr>
                                        </table>
                                        <center>
<div class="btn-group" role="group" aria-label="Transaction">
  <button type="button" class="btn btn-success btn-sm text-white mr-1 font-bold" onclick="savedata();">Save</button>
  <button type="button" class="btn btn-warning btn-sm mr-1" onclick="fetchLastUserDtls();">Reset</button>
  <button type="button" class="btn btn-secondary btn-sm mr-1" onclick="fetchSecCust();">Cancel & Go Back</button>
</div>
<%--<button class="btn btn-success btn-sm text-white" title="Save Secondary Customer" onclick="savedata();"><i class="fas fa-save"></i>&nbsp;Save Data </button></center>--%>
                                        </center>
                                          </div>
				  </div>
                                      
					
			</div>
		</div>
      </div>
      </div><div align="center"><p class="mesg-cnt" style="font-size:0.9rem;">Go Back to <a class="card-link1" href="javascript:void(0);" onclick="fetchSecCust()"><i class="fas fa-angle-left"></i>&nbsp;My Secondary Customers</a></p></div>

            </div>
              </div>
            <div style="width:100%;height:auto;overflow-y:auto;" id="frmSr" >
            <form class="form-horizontal" method="post"     id="frmInpt" name="frmInpt" >
      <div class="container-fullwidth ">
	                  <div class="row">

	                      <div class="col-md-9 ">

	                          <div class="tab-content" id="myTabContent">
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                  <h3 class="register-heading">Raise Your Service Request</h3>
	                                  <div class="row ">
	                                      <div class="col-md-4">
	                                         
	                                          <div class="form-group">
                                                  <input type="hidden"  value="<%=userdtls.getTavcustid()%>" name="custuserid" />
	                                              <select  id="srtype" tabindex="5" name="srtype" class="form-control">
	                                                  <option class="hidden"  selected disabled>Category</option>
	                                                  <option value="NU">Night Unloading</option>
	                                                  <option value = "DV">Diversion</option>
	                                                  <option value ="DW">Demmurage Waiver</option>
	                                                  <option value ="O">Others</option>
	                                              </select>
	                                          </div>
                                                  </div>
                                                  <div class="col-md-4">
	                                          <div class="form-group">
	                                              <input type="text" class="form-control inptCap"  tabindex="7" id="locn" name="locn"  style="text-transform:uppercase" required="required" list="locnlist" placeholder="Location *" value=""  size="4" >
                                                      <datalist id="locnlist"></datalist></input>                                                      
	                                          </div>
                                                  </div>
                                                  <div class="col-md-4">
	                                          <div class="form-group">
	                                              <input type="number" class="form-control"  tabindex="7" id="fnr" name="fnr"  style="text-transform:uppercase" required="required" placeholder="For FNR *" value=""  maxlength="11" size="11" list="fnrlist" /><datalist id="fnrlist"></datalist>
	                                          </div>
	                                      </div>
	                                      
	                                  </div>
					<div class="row">
	                                      <div class="col-md-12">
									<div class="form-group">
									<textarea class="form-control" tabindex="9" id="mesg" name ="mesg" rows="6" cols="80" placeholder="Write your mesg (Max 1000 Words)" value=""    maxlength="1000"  onchange="validateMesg();" ></textarea>
									</div>
                                                                        
									<div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="11"  type="button" onclick="submitSR()" >Submit</button>
									</div>
                                                                       
                                                                      </div>
									</div>
	                              </div>
	                             
	                          </div>
	                      </div>
	                  </div>

            </div>
            	
            </form>
            <div class="alert alert-success" id="alert1"></div>
            <div class="alert alert-danger" id="alert2"></div>
            </div>
            <div style="form-request" id="frmCncrn" >
            <form class="form-horizontal" method="post" action="/RailSAHAY/RaiseConcern"  enctype="multipart/form-data"    id="frmInpt1" name="frmInpt1" >
      <div class="container-fullwidth register1">
	                  <div class="row">
	                      
	                      <div class="col-md-12 ">
                              <div>
	                          <p class="ctgr-title">Connect for your Request</p>
				<div class="alert alert-danger alert-dismissible fade show" id="alert4" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>

				<div class="alert alert-success alert-dismissible fade show mb-1" id="alert3" role="alert">
  </div>

								<!--<div class="btn-group col-md-6">
                                                                  <div class="alert alert-danger mb-1" id="alert4" ></div>
                                                                          <div class="alert alert-success" id="alert3" ></div>
								<!--<button type="button" id="btnQ" name="btnQ" value ="Q" onclick="copyVal('QUERY')" class="btn btn-primary btn-concern">Query</button>
								<button type="button" id="btnS" name="btnS" value ="S" onclick="copyVal('SUGGESTION')" class="btn btn-primary btn-concern">Suggestion</button>
								<button type="button" id="btn2" name="btn2" value ="G" onclick="copyVal('GRIEVANCE')" class="btn btn-success btn-concern">Grievance</button>
								</div>-->
						  	</div>

	                          <div class="tab-content" id="myTabContent">
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                 
	                                  <div class="row register-form">
	                                      <div class="col-md-6">
	                                         
	                                           <div class="form-group">
	                                              <select  id="cncrctgr" tabindex="5" name="cncrctgr" class="form-control" required onchange="chSubCtgr()">
	                                                  <option class="hidden"  selected disabled>Category</option>
	                                                  <option value="Edemand_User" >E- Registration</option>
	                                                  <option value="Consignment">Diversion/Rebooking/Short Distance Delivery</option>
	                                                  <option value="Demurrage">Stacking/Demurrage/Wharfage</option>
	                                                  <!--<option value="Terminal">Application for Siding/PFT/Auto Hub</option>-->
	                                                  <option value = "Discount">Discount Schemes</option>
	                                                  <option value = "Investment">Investment Scheme</option>
	                                                  <option value = "Scheme">Offering Traffic</option>                                     
	                                                  <option value = "Couser">Application for Co-User Permission</option>
                                                          <option value="GCT">Gati Shakti Multi-Modal Cargo Terminal (GCT)</option>
	                                              </select>
                                                       <input type="text" class="form-control" tabindex="9" placeholder="Please Provide Category" value="" id="ctgrOth" name="ctgrOth" required="required"  maxlength="30"    />
	                                          </div>
	                                           <div class="form-group">
	                                              <select  id="locntype" tabindex="7" name="locntype" class="form-control" required onchange="locnSelect()">
	                                                  <option class="hidden" selected  disabled>Location Type</option>
	                                              <!--    <option value ="R" >Railway Board</option>
	                                                  <option value="Z">Zone</option>
	                                                  <option value = "D">Division</option>
	                                                  <option value ="S" >Station</option>-->
	                                              </select>
	                                          </div>
                                                  <div class="form-group">
	                                          	  <input type="text" class="form-control inptCap" tabindex="9" placeholder="Railway Authority to be Applied" value="" id="railAuth" name="railAuth" required="required"  maxlength="30"    /> 
	                                          </div>
	                                      </div>
	                                      <div class="col-md-6">
	                                        
	                                          <div class="form-group">
	                                              <select id="subctgr" name="subctgr" tabindex="6" required class="form-control" onchange="chgSubCtgr()">
	                                                  <option  class="hidden"  selected disabled>Sub-Category</option>
	                                              </select>
                                                      <input type="text" class="form-control" tabindex="9" placeholder=" Please Provide Sub-Category" value="" id="sctgrOth" name="sctgrOth" required="required"  maxlength="30"    />
	                                          </div>
                                                   <div class="form-group">
	                                              <input type="text" class="form-control"  tabindex="8" required id="locncr" name="locncr" list="locnlist" style="text-transform:uppercase" placeholder="Location *" value=""  />
                                                      <datalist id="locnlist"></datalist></input>  
	                                          </div>
	                                          <div class="form-group">
	                                              <input type="file" id="atch" name="atch" tabindex="10" class="form-control" placeholder="Upload (If any)" value="" />
                                                       <span class="file-footer">*Note: Single PDF/TXT/JPEG/PNG File is Allowed (Max File Size 10MB)</span>
	                                          </div>
	                                      </div>
	                                  </div>
									<div class="row">
	                                      <div class="col-md-8">
									<div class="form-group">
									<textarea class="form-control" tabindex="9" id="mesgcr" name ="mesgcr" rows="6" cols="80" placeholder="Write your mesg (Max 500 Characters)" value=""    maxlength="500"  onchange="validateMesg();" ></textarea>
									</div>

									<div class="d-flex justify-content-center mb-10">
									<button class="btn btn-primary float-right" tabindex="11" onclick="submitCncrn()" type="button">Submit</button>
									</div>
                                                                        <input type=hidden id="hidval" name="hidval" value="REQUEST">
                                                                        <input type=hidden id="custCncrn" name="custCncrn" value="<%=orgList.get(0) %>">
									<br/>
                                                                       
									</div>
									</div>
	                              </div>
	                             
	                          </div>
	                      </div>
	                  </div>

            </div>
            	
            </form>
            </div>
           <div style="width:100%;height:auto;overflow-y:scroll;padding:5px;" id="trmlProf" >
            
      <div class="container-fullwidth register1">
	                  <div class="row">	                      
	                      <div class="col-md-8">
	                                              <input type="text" class="form-control"  tabindex="7" id="locnP" name="locnP"  style="text-transform:uppercase" required="required" list="locnlist" placeholder="Location *" value=""  size="4" >
                                                    <datalist id="locnlist"></datalist></input>                                                      
			      </div>
                              <div class="col-md-4">
                                                    <button class="btn btn-primary float-left" tabindex="11"  type="button" onclick="getTrmlProf()" >Get Details</button>
                               </div>
	                      </div>
                             
	                  </div>

            </div>
            	 <div id="divTrmlProf">
                  </div>
            
    <div style="width:100%;height:auto;overflow-y:auto;padding:5px;" id="CustWgmt" >
            
      <div class="container-fullwidth register1">
      
          <nav style="padding:1px;" class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr>
	          	<!--<td><input class="form-control mr-sm-2" id="txtZone" name="txtZone" type="text" list="zonelist" placeholder="Zone">
			</td>-->
                        <td><label for="incident_dt" class="labelText">From <span style="color: #f05f40;">*</span></label>
                            </td>
                         <td>
                          <input type="date" id="txtDateFrom" class="bg-white text-dark inptCap inpttext" style="width:88%;height:25px;padding:1px;" name="txtDateFrom" value="<%= fromdate1%>" placeholder=''> 
                        </td>
                        <td><label for="incident_dt" class="labelText">To <span style="color: #f05f40;">*</span></label>
                            </td>
                         <td><input type="date" id="txtDateTo" class="bg-white text-dark inptCap inpttext" style="width:88%;height:25px;padding:1px;" name="txtDateTo" value="<%=strDateTo%>"  placeholder='Date To'>		 
                        </td>
			<td>
                            <input type="text" class="form-control"  id="txtCustSttn" name="txtCustSttn"  style="text-transform:uppercase;height:25px;padding:1px;" required="required" list="custsttnlist" placeholder="Station " value=""  size="15" >
                            <datalist id="custsttnlist"></datalist></input>                                                      
			</td>
			<td><button class="btn btn-success" style="height:25px;width:80px;padding:1px;line-height:0.75" onclick="event.preventDefault();getCustWgmtData();">Get Detail</button></td></tr></table>
  	      </form>
  	     </div>
	</nav>
             <div class="row">
                <div class="col-lg-12 col-sm-12">
                        <div id="divCustWgmt" class="trmllistdata" style="padding:5px;"></div>
                </div>	
              </div>
            <!-- Project-->
            </div>
     </div>          
    
    <div style="width:100%;height:auto;overflow-y:auto;padding:5px;" id="NodlOfcr" >
            
      <div class="container-fullwidth register1">
      
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table><tr>
	          	<td><input class="form-control mr-sm-2" id="txtZone" name="txtZone" type="text" list="zonelist" placeholder="Zone">
			          <datalist id="zonelist"></datalist>
			</td>
			<td><input class="form-control mr-sm-2" id="txtDvsn" name="txtDvsn" type="text" list="dvsnlist" placeholder="Division">
				  <datalist id="dvsnlist"></datalist>
			</td>
			<td><button class="btn btn-success" onclick="event.preventDefault();getData();">Find</button></td></tr></table>
  	      </form>
  	     </div>
	</nav>
             <div class="row">
                <div class="col-lg-12 col-sm-12">
                        <div id="divNodlOfcr" class="trmllistdata" style="padding:5px;"></div>
                </div>	
              </div>

            <!-- Project-->
            </div>
     </div>
    </div>



    <div class="modal" id="divDtlsData">
      <div class="modal-dialog">
        <div class="modal-content">
    
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title" id="dtlsHedr"></h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
    
          <!-- Modal body -->
          <div class="modal-body">
            <div id="dtlsData" class="dtls-data"></div>
          </div>
    
          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
          </div>
    
        </div>
      </div>
    </div>

<div class="modal fade" id="divCustDtlsData">
  <div class="modal-dialog modal-dialog-centered mw-100 w-75">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="custModlHedr">WEIGHMENT DETAIL</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <!--<div id="custModlBdy" class="dtls-data"></div>-->
        <div class="container">
            <div class="row" id="custModlBdy">  </div>
        </div>    
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="divDtlsData1">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="dtlsHedr"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div id="divTrckCncr" class="dtls-data"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="divFnrMap">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="dtlsHedr1"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" style="padding-top:0px;padding-bottom:0px;">
        <div id="dtlsData" class="dtls-data" style="overflow:hidden;padding:1px;">
        <iframe name="fnrFr" id="fnrFr" style="width:95vw;height:65vh;overflow:hidden;border:0px;" scrolling="no">
        </iframe>        
        </div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

          </section>
          <!-- Client Section-->
         
          <!-- Feeds Section-->
         
          </div>
          </div>
          <div class="modal fade" id="divTrckCncr1">
              <div class="modal-footer"><button type="button" class="btn btn-danger" data-dismiss="modal">Close</button></div>
          </div>
          
           <script>
            var sttnstr='';
            for (var i=0; i < aSttn.length;++i){
            sttnstr += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
             var dvsnstr='';
                for (var i=0; i < aDvsn.length;++i){
                dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
                }
                var zonestr='';
                for (var i=0; i < aZone.length;++i){
                zonestr+= '<option value="'+aZone[i]+'" />'; // Helplist for Zones
                }
                var rbstr='<option value="RAILWAY BOARD" />';
            
           // var my_list=document.getElementById("locnlist");
           // my_list.innerHTML = str;
             function locnSelect(){
                var locnTp=document.getElementById('locntype').value;              
                document.getElementById("locncr").value="";
                document.getElementById("locncr").readOnly=false;
                switch(locnTp)
                  {
            
                   
                    case 'S':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = sttnstr;
                            break;
                   
                    case 'D':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = dvsnstr;
                            var subctgr1=document.getElementById('subctgr').value;
                            if(subctgr1=='L'||subctgr1=='M' ||subctgr1=='H'){
                             document.getElementById("railAuth").value="Sr DOM";
                            }else{
                            document.getElementById("railAuth").value="Sr DCM";
                            }
                           // document.getElementById("railAuth").value="Sr DCM";
                            document.getElementById("railAuth").readOnly=true;
                            break;
            
                    case 'Z':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = zonestr;                        
                            document.getElementById("railAuth").value="CCM/FM";
                            document.getElementById("railAuth").readOnly=true;
                            break;
                    case 'R':
                            var my_list=document.getElementById("locnlist");
                            my_list.innerHTML = rbstr;     
                            document.getElementById("locncr").value="RAILWAY BOARD";
                            document.getElementById("locncr").readOnly=true;                              
                            document.getElementById("railAuth").value="EDFM";
                            document.getElementById("railAuth").readOnly=true;                         
                            break;
            
                  }
            }
            
            function getData()
            {
	            var sttncode='';
	            var sttndesc=$("#txtSttn").val();
				if(sttndesc.indexOf("-")>-1)
				{
					var sttn=sttndesc.substring(sttndesc.indexOf("-")+1);
					sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
				}	
				else
				{
					sttncode=sttndesc.toUpperCase();
				}
				//fetchTrmlProf(sttncode);
            }       
            function getTrmlProf() {
                $("#divTrmlProf").show();
                var sttndesc=document.getElementById("locnP").value;
               // alert(sttndesc);
                var sttncode='';
	           // var sttndesc=$("#txtSttn").val();
				if(sttndesc.indexOf("-")>-1)
				{
					var sttn=sttndesc.substring(sttndesc.indexOf("-")+1);
					sttncode=(sttn.substring(0,sttn.indexOf("("))).trim();
				}	
				else
				{
					sttncode=sttndesc.toUpperCase();
				}
                               // alert(sttncode);
				fetchTrmlProf(sttncode);
                
            }
	
		</script>
                <script>
            var str='';
            for (var i=0; i < aZone.length;++i){
            str += '<option value="'+aZone[i]+'" />'; // Helplist for Zone
            }
            var my_list=document.getElementById("zonelist");
            my_list.innerHTML = str;
            
            str='';
	    for (var i=0; i < aDvsn.length;++i){
	      str += '<option value="'+aDvsn[i]+'" />'; // Helplist for Division
            }
	    var my_list1=document.getElementById("dvsnlist");
            my_list1.innerHTML = str;
            
            function getData()
            {
	            var zonecode='';
	            var dvsncode='';
	            var zonedesc=$("#txtZone").val();
	            var dvsndesc=$("#txtDvsn").val();
	            if(zonedesc!="")
	            {
			if(zonedesc.indexOf("-")>-1)
			{
				zonecode=zonedesc.substring(0,zonedesc.indexOf("-"));
			}	
			else
			{
				zonecode=zonedesc.toUpperCase();
			}
		    }
	            if(dvsndesc!="")
	            {
			if(dvsndesc.indexOf("-")>-1)
			{
				dvsncode=dvsndesc.substring(0,dvsndesc.indexOf("-"));
			}	
			else
			{
				dvsncode=dvsndesc.toUpperCase();
			}
		    }
		   fetchNodlOfcrList(zonecode,dvsncode);
            } 
//        var sttnstr='';
//            for (var i=0; i < aSttn.length;++i){
//                sttnstr += '<option value="'+aSttn[i]+'" />'; // Helplist for station
//            }            
       function getCustWgmtData()
            {
            //alert("getCustWgmtData");
            //alert(sttnstr);
            
	            var sttncode='';
                    var fromdate='';
                    var todate='';
	            var sttndesc=$("#txtCustSttn").val();
                    
	            if(sttndesc!="")
	            {
			if(sttndesc.indexOf("-")>-1)
			{
				sttncode=sttndesc.substring(0,sttndesc.indexOf
("-"));
			}	
			else
			{
				sttncode=sttndesc.toUpperCase();
			}
		    }
	           
		   getCustWgmt('F',fromdate,todate,sttncode);
            }           
            
            
	    function trackRequest(refid)
	    {
		trackConcern(refid);
		$("#divDtlsData1").modal('show');
	    }       
	    function showFNRRoute(){
		    
		    $("#divFnrMap").modal('show');
	    }
	    function showTrmlProf(sttn)
	    {
		var sttn1=sttn.substring(sttn.lastIndexOf("(")+1);
		var trml=sttn1.substring(0,sttn1.indexOf(")"));
		
			var htmlstr='<div class="w3-container">';
	//var myurl="/RailSAHAY/GG_TrmlDataJson?Optn=STTN_PROFILE&Sttn="+trml;
	var myurl="/RailSAHAY/GG_TrmlDataJson";
	$.ajax({
		url : myurl,
		type : "post",
        data: {Optn:'STTN_PROFILE',Sttn:trml},
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
		for(var i=0;i<obj.SttnProf.length;i++)
		{
			var sttn=obj.SttnProf[i].Sttn;
			var trfciwrd=obj.SttnProf[i].TrfcIwrd;
			if(trfciwrd=='T')
				trfciwrd="TRAIN LOAD";
			if(trfciwrd=='W')
				trfciwrd="WAGON LOAD";

			var trfcowrd=obj.SttnProf[i].TrfcOwrd;
			if(trfcowrd=='T')
				trfcowrd="TRAIN LOAD";
			if(trfcowrd=='W')
				trfcowrd="WAGON LOAD";
			var halfrake=obj.SttnProf[i].HalfRake;
			var fullrake=obj.SttnProf[i].FullRake;
			var dvsn=obj.SttnProf[i].Dvsn;
			var zone=obj.SttnProf[i].Zone;
			var diflag=obj.SttnProf[i].DIFlag;
			var srvg=obj.SttnProf[i].Srvg;
			var srvgdesc='';
			if((diflag=="D") || (diflag=="I"))
				srvgdesc='SERVED BY :'+srvg;
			var dstt=obj.SttnProf[i].Dstt;
			var dsttname=obj.SttnProf[i].DsttName;
			var smart=obj.SttnProf[i].Smart;
			var smartlist=smart.split(',');
			var fcty1=obj.SttnProf[i].Fcty1;
			var fcty1list=fcty1.split('/');
			var highplat=obj.SttnProf[i].HighPlat;
			var loossdng=obj.SttnProf[i].LoosSdng;
			var fcty3=obj.SttnProf[i].Fcty3;
			var fcty3list=fcty3.split('/');
			var cmdtiw=obj.SttnProf[i].CmdtIW;
			var cmdtow=obj.SttnProf[i].CmdtOW;
			var bsnshrs=obj.SttnProf[i].BsnsHrs;
			var wrkghrs=obj.SttnProf[i].WrkgHrs;
			htmlstr+='<div class="w3-card-5"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+sttn+'</p></th></tr>';
			if(srvgdesc!='')
				htmlstr+='<tr><td class="lbl">Description</td><td class="val">'+srvgdesc+'</td></tr>';

			htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">'+dvsn+'/'+zone+'</td></tr>';
			htmlstr+='<tr><td class="lbl">District</td><td class="val">'+dstt+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Traffic Type</td><td class="val"><b>Unloading:</b> '+trfciwrd+', <b>Loading:</b> '+trfcowrd+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Commodity</td><td class="val"><b>Unloading:</b> '+cmdtiw+'<br/><b>Loading:</b> '+cmdtow+'</td></tr>';
			htmlstr+='<tr><td class="lbl">Working Hours</td><td class="val">'+wrkghrs+'</td></tr>';
			htmlstr+='<tr><td class="lbl" sttn="\''+sttn+'\'">Logistics Facilities</td><td class="val" sttn="\''+sttn+'\'">';
			htmlstr+='<table class="tblFcty" onclick="fetchSMARTFcty(\''+sttn+'\');"  sttn="\''+sttn+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+smartlist[0];
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+smartlist[1]+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+smartlist[2]+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+smartlist[3]+'</span></td></tr></table></td></tr>';
			htmlstr+='<tr><td class="lbl">Terminal Facilities</td><td class="val"><table>';
			if(fcty1list[0]=="Y")
				htmlstr+='<tr><td><ul><li>High Mast/Lighting Available</li>';
				htmlstr+='<li>Line Count: '+fcty1list[1]+'</li>';
			if(fcty1list[2]!="")
				htmlstr+='<li>Handling Type: '+fcty1list[2]+'</li>';
			if(fcty1list[3]=="Y")
				htmlstr+='<li>Freight Charges on Through Distance Basis</li>';
			if(fcty1list[4]=="Y")
				htmlstr+='<li>Weighbridge Available</li>';
			if(fcty1list[5]!="0")
				htmlstr+='<li>Shed Length: '+fcty1list[5]+' Mtr.</li>';
			if(fcty3list[0]=="Y")
				htmlstr+='<li>Warehouse Available</li>';
			if(fcty3list[1]!="0")
				htmlstr+='<li>Distance from Serving Station: '+fcty3list[1]+' Kms</li>';
			if((fcty3list[2]!="NA") && (fcty3list[2]!=""))
				htmlstr+='<li>Alternate Weighbridge Locations: '+fcty3list[2]+'</li>';
			if(fcty3list[3]!="")
				htmlstr+='<li>Siding Owner: '+fcty3list[3]+'</li>';
			if(fcty3list[4]!="")
				htmlstr+='<li>EOL Type: '+fcty3list[4]+'</li>';
			if(fcty3list[5]=="Y")
				htmlstr+='<li>TANK Handling Available</li></ul></td></tr>';

			htmlstr+='</table></td></tr></table></div>';
			$("#dtlsData").html(htmlstr);
		}
		}
		});
		$("#divDtlsData").modal('show');
	    }

 function showWgmtDetail1(rakeid, wgmtid, sttn, datefrom, dateto, sttnfrom, sttnto, trxntime, wgmtcount, fnrnumb)
	    {      
                //alert("showWgmtDetail1");
                //var myurl="/RailSAHAY/FrgtPymtJson";
                $("#WgmtRakeID").val(rakeid);
                $("#WgmtID").val(wgmtid);
                $("#WgmtSttn").val(sttn);
                $("#WgmtdateFrom").val(datefrom);
                $("#WgmtdateTo").val(dateto);            
                $("#SttnFrom").val(sttnfrom);
                $("#SttnTo").val(sttnto);    
                $("#TrxnTime").val(trxntime);
                $("#WgmtCount").val(wgmtcount);  
		$("#FnrNumb").val(fnrnumb);                 
                //alert(rakeid);
                
                //alert($("#WgmtdateFrom").val());
                //alert($("#WgmtdateTo").val());
                $("#frmRakeWgmtDtls").submit();
                //window.location.href="/RailSAHAY/pages/RakeWgmtDtls.jsp?Sttn="+sttn+"&RakeID="+rakeid+"&WgmtID+"+wgmtid;
                //alert("123");
            }
    function showWgmtDetail(rakeid, wgmtid, sttn)
	    {
            alert("showWgmtDetail");
//            alert("rakeid"+rakeid);
            //alert("wgmtid"+wgmtid);
//            alert("sttn"+sttn);

		//var sttn1=sttn.substring(sttn.lastIndexOf("(")+1);
		//var trml=sttn1.substring(0,sttn1.indexOf(")"));
		//alert("11");
			//var htmlstr='<div class="w3-container">';
                var myurl="/RailSAHAY/FrgtPymtJson";
                //alert("22");
                //alert("sttn"+sttn);
                
                $.ajax({
                        url : myurl,
                        type : "post",
                data: {Optn:'CUST_WGMT_DTLS',Sttn:sttn,RakeID:rakeid,WgmtID:wgmtid},
                        async : true,
                        success : function(data) {
                        var obj= JSON.parse(data);
			var stts=obj.Stts;
			var erormesg=obj.ErorMesg;
			if(stts=="F")
			{
				alert("Not able to connect to Server:"+erormesg);
				return false;
			}
                //alert("33");
                var htmlstr=
                        '<div class="col table-responsive">'+
                            '<table id="tblWgmtDtls" class="display table table-striped table-bordered tabformat table-sm">'+
                            '<thead>'+
                             '       <tr>'+
                             '               <th rowspan="3" class="align-middle" class="w-2">SR.<\/th>'+
                             '               <th colspan="15" style="text-align:center;" class="w-25">WEIGH BRIDGE</th>'+
                             '               <th colspan="4" style="text-align:center;" class="w-15">TMS</th>'+
                            '               <th colspan="2" style="text-align:center;" class="w-10">RR</th>'+
                            '               <th colspan="1" style="text-align:center;" class="w-3">CHBL</th>'+                             
                            '               <th colspan="1" style="text-align:center;" class="w-3">PMBL</th>'+                           
                            '               <th colspan="2" style="text-align:center;" class="w-6">OVERWGHT</th>'+                             
                           // '               <th colspan="1" style="text-align:center;" class="w-10">POL1</th>'+                             
                           // '               <th colspan="1" style="text-align:center;" class="w-10">POL2</th>'+                             
                            '       </tr>'+
                              '       <tr>'+
                             '               <th colspan="3" style="text-align:center;" class="w-10">WAGON</th>'+
                              '  <th colspan="1" rowspan="2"  style="text-align:center;" class="w-2">LE</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-5">INVC</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-5">STTN</th>'+
                             '               <th colspan="1" rowspan="2" style="text-align:center;" class="w-5">CMDT</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-5">WGMT</th>'+
                             '               <th colspan="1" style="text-align:center;" class="w-5">TRXN</th>'+
                             '               <th colspan="1" rowspan="2" style="text-align:center;" class="w-5">TARE</th>'+
                             '               <th colspan="1" style="text-align:center;" class="w-3">ACTL</th>'+
                             '               <th colspan="1" style="text-align:center;" class="w-3">GROS</th>'+
                             '               <th colspan="1" style="text-align:center;" class="w-3">GROS</th>'+
                             '               <th colspan="1" style="text-align:center;" class="w-3">ACTL</th>'+
                             '               <th colspan="1" rowspan="2" style="text-align:center;" class="w-5">TARE</th>'+                             
                             '               <th colspan="1" style="text-align:center;" class="w-5">TRXN</th>'+
                             '               <th rowspan="2" style="text-align:center;" class="w-5">NUMB</th>'+                                
                             '               <th rowspan="2" style="text-align:center;" class="w-5">DATE</th>'+  
                             '               <th rowspan="2" style="text-align:center;" class="w-3">WGHT</th>'+                               
                             '               <th rowspan="2" style="text-align:center;" class="w-3">WGHT</th>'+  
                             '               <th rowspan="2" style="text-align:center;" class="w-3">TOTL</th>'+                               
                             '               <th rowspan="2" style="text-align:center;" class="w-3">NORM</th>'+                               '       </tr>'+
//                             '               <th rowspan="2" style="text-align:center;" class="w-5">WGHT</th>'+                               
//                             '               <th rowspan="2" style="text-align:center;" class="w-5">WGHT</th>'+  
                             '       <tr>'+
                             '               <th style="text-align:center;" class="w-3">RLY</th>'+
                             '               <th style="text-align:center;" class="w-5">TYPE</th>'+
                             '               <th style="text-align:center;" class="w-5">NUMB</th>'+
                             '               <th style="text-align:center;" class="w-5">NUMB</th>'+
                             '               <th style="text-align:center;" class="w-10">DATE</th>'+
                             '               <th style="text-align:center;" class="w-3">FROM</th>'+
                             '               <th style="text-align:center;" class="w-5">TO</th>'+
                             '               <th style="text-align:center;" class="w-10">CMNC TIME</th>'+
                             '              <th style="text-align:center;" class="w-10">CMPL TIME</th>'+
                             '               <th style="text-align:center;" class="w-10">TIME</th>'+                                
                             '               <th style="text-align:center;" class="w-3">WGHT</th>'+  
                             '               <th style="text-align:center;" class="w-3">WGHT</th>'+                               
                             '               <th style="text-align:center;" class="w-3">WGHT</th>'+  
                             '               <th style="text-align:center;" class="w-3">WGHT</th>'+                               
                             '               <th style="text-align:center;" class="w-10">TIME</th>'+  

                             '       </tr>'+
                            '</thead>'+
                            '<tbody>';
                            
                //alert(obj.WgmtDtls.length);
                var j=0;
		for(var i=0;i<obj.WgmtDtls.length;i++)
		{
                j=i+1;
                //alert("inside for");
			var owngrly=obj.WgmtDtls[i].OwngRly;
			var wgontype=obj.WgmtDtls[i].WgonType;
			//alert("55");
			var wgonnumb=obj.WgmtDtls[i].WgonNumb;
			
			var wgonid=obj.WgmtDtls[i].WgonId;
			var leflag=obj.WgmtDtls[i].LEFlag;
			var invcid=obj.WgmtDtls[i].InvcId;
			var invcsttnfrom=obj.WgmtDtls[i].InvcSttnFrom;
			var invcsttnto=obj.WgmtDtls[i].InvcSttnTo;
 			var invcnumb=obj.WgmtDtls[i].InvcNumb;
			var invcdate=obj.WgmtDtls[i].InvcDate;
                        //alert("66");
			var cmdt=obj.WgmtDtls[i].Cmdt;
			
			var cnsr=obj.WgmtDtls[i].Cnsr;
			var cnsr1=obj.WgmtDtls[i].Cnsg;
			var rkpmflag=obj.WgmtDtls[i].RKPMFlag;

			var bvdcflag=obj.WgmtDtls[i].BVDCFlag;
			var rakestts=obj.WgmtDtls[i].RakeStts;
                        //alert("77");
			
//			var rstnflag=obj.WgmtDtls[i].RstnFlag;
			var wgmtcmnctime=obj.WgmtDtls[i].wgmtcmnctime;
			var wgmtcmpltime=obj.WgmtDtls[i].wgmtcmpltime;
//
//			var strcyclstrttime=obj.WgmtDtls[i].strcyclstrttime;
//			var strcyclcmpltime=obj.WgmtDtls[i].strcyclcmpltime;
//			
			var strtare=obj.WgmtDtls[i].strtare;
			var stractlwght=obj.WgmtDtls[i].stractlwght;
			var strgroswght=obj.WgmtDtls[i].strgroswght;
//
			var strtmsactlwght=obj.WgmtDtls[i].strtmsactlwght;
			var strtmsgroswght=obj.WgmtDtls[i].strtmsgroswght;
			
			var strtrxntime=obj.WgmtDtls[i].strtrxntime;
			var strtmstare=obj.WgmtDtls[i].strtmstare;
			var strtmstrxntime=obj.WgmtDtls[i].strtmstrxntime;

			var strrrnumb=obj.WgmtDtls[i].strrrnumb;
			var strrrdate=obj.WgmtDtls[i].strrrdate;
			
			var strchblwght=obj.WgmtDtls[i].strchblwght;
			var strpmblwght=obj.WgmtDtls[i].strpmblwght;
			var stroverwghttotl=obj.WgmtDtls[i].stroverwghttotl;

			var stroverwghtnorm=obj.WgmtDtls[i].stroverwghtnorm;
			var strpol1wght=obj.WgmtDtls[i].strpol1wght;	
                        var strpol2wght=obj.WgmtDtls[i].strpol2wght;
                        
                        
                        //htmlstr+='<div class="w3-card-5"><table class="styletable">';
//			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" 
//                            ></i>&nbsp;'+owngrly+'</p></th></tr>';
                        //if(i==0)
                        //htmlstr+='<tr><th >Owning Rly</th><th >Wagon Type</th><th >Wagon Numb</th></tr>';
			htmlstr+='<tr><td class="val">'+j+'</td>';
                        htmlstr+='<td class="val">'+owngrly+'</td>';
			htmlstr+='<td class="val">'+wgontype+'</td>';
			htmlstr+='<td class="val"> '+wgonnumb+'</td>';
                        htmlstr+='<td class="val"> '+leflag+'</td>';
                        htmlstr+='<td class="val"> '+invcnumb+'</td>';
                        htmlstr+='<td class="val"> '+invcdate+'</td>';
                        htmlstr+='<td class="val"> '+invcsttnfrom+'</td>';
                        htmlstr+='<td class="val"> '+invcsttnto+'</td>';
                        htmlstr+='<td class="val"> '+cmdt+'</td>';
                        
                        htmlstr+='<td class="val"> '+wgmtcmnctime+'</td>';
                        htmlstr+='<td class="val"> '+wgmtcmpltime+'</td>';
                        htmlstr+='<td class="val"> '+strtrxntime+'</td>';
                        
                        htmlstr+='<td class="val"> '+strtare+'</td>';
                        htmlstr+='<td class="val"> '+stractlwght+'</td>';
                        htmlstr+='<td class="val"> '+strgroswght+'</td>';
//                        
//                        
//                        htmlstr+='<td class="val"> '+wgmtcmnctime+'</td>';
//                        htmlstr+='<td class="val"> '+wgmtcmpltime+'</td>';
                     //   htmlstr+='<td class="val"> '+strtrxntime+'</td>';
                        
                        htmlstr+='<td class="val"> '+strtmsgroswght+'</td>';
                        htmlstr+='<td class="val"> '+strtmsactlwght+'</td>';
                        htmlstr+='<td class="val"> '+strtmstare+'</td>';  
                       
                        htmlstr+='<td class="val"> '+strtmstrxntime+'</td>';
                        htmlstr+='<td class="val"> '+strrrnumb+'</td>';
                        htmlstr+='<td class="val"> '+strrrdate+'</td>';    
                                               
                        htmlstr+='<td class="val"> '+strchblwght+'</td>';
                        htmlstr+='<td class="val"> '+strpmblwght+'</td>';
                        htmlstr+='<td class="val"> '+stroverwghttotl+'</td>';  
                        htmlstr+='<td class="val"> '+stroverwghtnorm+'</td>';  
//                      htmlstr+='<tr><td class="lbl">Commodity</td><td class="val"> '+cmdt+'</td></tr></table></div>';
//alert("888");
			$("#custModlBdy").html(htmlstr);
		}
                //alert("44");
		}
		});
		$("#divCustDtlsData").modal('show');
	    }            
		</script>
          <!-- Page Footer-->
<form id="frmFrgtPymt" method="post" action="\RailSAHAY\FrgtPymtTrxn" target="frgtpymttrxn">
	<input type="hidden" id="txtChrgZone" name="txtChrgZone" />
	<input type="hidden" id="txtChrgType" name="txtChrgType" />
	<input type="hidden" id="txtChrgId" name="txtChrgId" />
	<input type="hidden" id="txtAmnt" name="txtAmnt" />
	<input type="hidden" id="txtCustCode" name="txtCustCode" />
	<input type="hidden" id="txtCnfmDate" name="txtCnfmDate" />
	<input type="hidden" id="Optn" name="Optn" value="PAY_NOW" />
</form>
<form id="frmFrgtPymtInit" method="post" action="\RailSAHAY\pages\FrgtPymtCnfm.jsp">
	<input type="hidden" id="Sttn" name="Sttn" />
	<input type="hidden" id="CustCode" name="CustCode" />
	<input type="hidden" id="InvcId" name="InvcId" />
	<input type="hidden" id="FNOTId" name="FNOTId" />
	<input type="hidden" id="CustDmrgId" name="CustDmrgId" />
	<input type="hidden" id="TrxnId" name="TrxnId" />
	<input type="hidden" id="ChrgType" name="ChrgType" />
	<input type="hidden" id="ChrgTypeCode" name="ChrgTypeCode" />
	<input type="hidden" id="ChrgTypeDesc" name="ChrgTypeDesc" />
	<input type="hidden" id="IWOWFlag" name="IWOWFlag" />
	<input type="hidden" id="InvcDate" name="InvcDate" />
	<input type="hidden" id="Amnt" name="Amnt"  />
	<input type="hidden" id="ChrgId" name="ChrgId"  />
	<input type="hidden" id="ChrgZone" name="ChrgZone"  />
	<input type="hidden" id="CnfmDate" name="CnfmDate"  />
	<input type="hidden" id="RlyGstIn" name="RlyGstIn"  />
	<input type="hidden" id="CustGstIn" name="CustGstIn"  />
	<input type="hidden" id="HndgAgnt" name="HndgAgnt"  />
	<input type="hidden" id="HndgAgntName" name="HndgAgntName"  />
	<input type="hidden" id="PymtStts" name="PymtStts" value="INIT"  />
</form>
<form id="frmRakeWgmtDtls" method="post" action="\RailSAHAY\pages\RakeWgmtDtls.jsp" target="_blank" >
	<input type="hidden" id="WgmtSttn" name="WgmtSttn" />
	<input type="hidden" id="CustCode" name="CustCode" />
	<input type="hidden" id="WgmtdateFrom" name="WgmtdateFrom" />
	<input type="hidden" id="WgmtdateTo" name="WgmtdateTo" />
	<input type="hidden" id="WgmtRakeID" name="WgmtRakeID" />
	<input type="hidden" id="TrxnId" name="TrxnId" />
	<input type="hidden" id="WgmtID" name="WgmtID" />
	<input type="hidden" id="SttnFrom" name="SttnFrom" />
	<input type="hidden" id="SttnTo" name="SttnTo" />        
	<input type="hidden" id="TrxnTime" name="TrxnTime" />
	<input type="hidden" id="WgmtCount" name="WgmtCount" />        
	<input type="hidden" id="FnrNumb" name="FnrNumb" />     
</form>
<div class="modal" id="gstPopUpModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="gstHeading">GST Detail</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div class="div-gst" id="divGstInpt"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
	<div class="btn-group btn-group-sm" style="margin:10px;"><button type="button" class="btn btn-dark" onclick="closeGstPopUp();"><i class="fas fa-times"></i>&nbsp;&nbsp;Cancel</button>&nbsp;&nbsp;<button type="button" class="btn btn-success text-white" onclick="makeGstPayment();" id="btnMakeGstPymt">Proceed&nbsp;&nbsp;<i class="fas fa-chevron-right"></i></button></div></div>
    </div>
  </div>
</div>

	<div id="inform-modal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">			
				<h4 class="modal-title" style="color:#2d862d;">Cart created successfully</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p><strong>One payment added!</strong> Please note that Payments of <strong>same zonal railway and same customer</strong> can only be added to the cart</p>
			</div>
			<div class="modal-footer">
                	<button  class="btn btn-info btn-sm" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>     
<div class="modal" id="cartGSTModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="cartGSTHeading"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div id="cartGSTBody"></div>
	<div id="cartGSTBody1"></div>
	<div id="cartBodyGSTIN"></div>
	<div id="cartGSTBodyFtr"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
<div class="btn-group btn-sm" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-success btn-sm text-white" id="btnAddCart" onclick="addPymtToCart();"><i class="fas fa-cart-plus"></i>&nbsp;&nbsp;Add to Cart</button>&nbsp;&nbsp;
  <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
</div>
      </div>

    </div>
  </div>
</div>

     
<div class="modal" id="cartSecGSTModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title" id="cartSecGSTHeading" style="margin:5px;font-size:0.9rem;font-weight:600;color:#000;"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div id="cartSecGSTBody"></div>
	<div id="cartSecGSTBody1"></div>
	<div id="cartSecGSTBodyFtr"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
<div class="btn-group btn-sm" role="group" aria-label="Basic example">
  <button type="button" class="btn btn-success btn-sm text-white" id="btnAddSecCart" onclick="addPymtToCart();"><i class="fas fa-cart-plus"></i>&nbsp;&nbsp;Add to Cart</button>&nbsp;&nbsp;
  <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
</div>
      </div>

    </div>
  </div>
</div>

          <%@ include file="/pages/GG_Footer.jspf" %>