
    var fnrList="";
    	function fetchODRDtls()
    	{
        $("#dataD").show();
        $("#frmSr").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
    		//alert("Outstanding Indents Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
               // alert(custuserid);
               var custOrgn="";
                if('<%=orgList.size()%>'*1 >1){
                    custOrgn =document.getElementById("custList").value;
                }else{
                    custOrgn=document.getElementById("custList1").innerHTML;
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
        fnrList="";
        $("#dataD").show();
        $("#frmSr").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
            
    		//alert("Consignment Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
              //  alert(custuserid);
              var custOrgn="";
                if('<%=orgList.size()%>'*1 >1){
                    custOrgn =document.getElementById("custList").value;
                }else{
                    custOrgn=document.getElementById("custList1").innerHTML;
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
                            htmlD+='<button class="btn btn-warning mr-3">'+(i+1)+'</button></div><div class="col-lg-11 col-sm-10"><h3 class="h4"><a href="javascript:void(0)" class="fnr-link" onclick="showFNRRoute('+obj[i].cnmtid+');">';
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
      //  $("#dataD").show();
      //  $("#frmSr").hide();
    		//alert("Total Count Detail");
                var custuserid  = "<%= userdtls.getTavcustid()%>";
                //alert(custuserid);
                
                
                var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList1").innerHTML;
        }
       // alert(custOrgn);
                       $.ajax({
                        url: "/RailSAHAY/UserDashboard?callfor=CONT&custuserid=" + custuserid+"&org="+custOrgn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                       //alert("data:"+data);
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
                       
                },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
    	}
        function fetchFile(fileName,reqId)
    	{
        
        alert(fileName+" Id:"+reqId);
      //  $("#dataD").show();
      //  $("#frmSr").hide();
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
        $("#dataD").show();
        $("#frmSr").hide();
        $("#frmCncrn").hide();
        $("#trmlProf").hide();
         $("#NodlOfcr").hide();
        
    		//alert("Concern Detail");
                
                var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList1").innerHTML;
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
            $("#dataD").hide();;
            $("#frmSr").show();
        $("#frmCncrn").hide();
             $("#alert1").hide();
        $("#alert2").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
        }
        function raiseCncrn(){
            $("#dataD").hide();;
            $("#frmSr").hide();
        $("#frmCncrn").show();
             $("#alert3").hide();
        $("#alert4").hide();
        $("#trmlProf").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
         $("#hiCont").hide();
           /*$("#dsCont").slideUp();
           $("#shCont").show();*/

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
        
        function profTrml(){
            $("#dataD").hide();;
            $("#frmSr").hide();
            $("#frmCncrn").hide();
            $("#alert1").hide();
            $("#alert2").hide();
            $("#trmlProf").show();
            $("#alert3").hide();
            $("#alert4").hide();
            $("#divTrmlProf").html("");
         $("#NodlOfcr").hide();
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
            custOrgn=document.getElementById("custList1").innerHTML;
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
            custOrgn=document.getElementById("custList1").innerHTML;
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
        var custOrgn="";
        if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList1").innerHTML;
        }
        //alert(custOrgn);
        
        
    		//alert("Service Request Detail");
                $("#dataD").show();
                 $("#frmSr").hide();
                $("#trmlProf").hide();
                $("#divTrmlProf").html("");
                 $("#NodlOfcr").hide();
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
        
        function chSubCtgr(){
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
       
       $("#dataD").hide();;
            $("#frmSr").hide();
            $("#frmCncrn").hide();
            $("#alert1").hide();
            $("#alert2").hide();
            $("#trmlProf").hide();
            $("#alert3").hide();
            $("#alert4").hide();
            $("#divTrmlProf").html("");
            $("#NodlOfcr").show();
           fetchNodlOfcrList('','');
       }
       
    