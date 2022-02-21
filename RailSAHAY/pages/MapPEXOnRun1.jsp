<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"PARCEL EXPRESS ON RUN","/RailSAHAY/pages/MapPEXOnRun1.jsp",UserDevice,Browser);
%>
<%@page import="java.util.*" %>
<script type="text/javascript" src="/RailSAHAY/resources/js/OpenLayers-2.13.1/OpenLayers.js"></script>
<%
util.AppIntg.GG_AppIntgData obj  = new util.AppIntg.GG_AppIntgData();
	String strZone="IR";
	String strZoneFrom="";
	String strDvsnFrom="";
	String strSttnFrom="";
	String strCrntZone="";
	String strCrntDvsn="";
	String strCrntSttn="";
	String strZoneTo="";
	String strDvsnTo="";
	String strSttnTo="";
	String strCmdt="";
    String strRakeType="";
    String strCnsg="";
    String strCnsr="";
    String strCtgy ="";
    String strBaseMapDomn="";
    String strTwnFlag="";
    double lonCent=78;
	double latCent=21;
	String strSctnFrom	=	"";
	String strCrntSctn	=	"";
	String strSctnTo	=	"";
	String  strCoaLnkg=	"";
	String strLE		=	"";
	String strTranType	=	"";
	int intCntr=0;
	int intLCntr=0;
	int intECntr=0;
	int intDlyCntr=0;
	int intOTCntr=0;
	String strLinkClss="";

	try
	{
		strZoneFrom=(String)request.getParameter("ZoneFrom").toUpperCase().trim();
		strDvsnFrom=(String)request.getParameter("DvsnFrom").toUpperCase().trim();
		strSttnFrom=(String)request.getParameter("SttnFrom").toUpperCase().trim();
		strCrntZone=(String)request.getParameter("CrntZone").toUpperCase().trim();
		strCrntDvsn=(String)request.getParameter("CrntDvsn").toUpperCase().trim();
		strCrntSttn=(String)request.getParameter("CrntSttn").toUpperCase().trim();
		strZoneTo=(String)request.getParameter("ZoneTo").toUpperCase().trim();
		strDvsnTo=(String)request.getParameter("DvsnTo").toUpperCase().trim();
		strSttnTo=(String)request.getParameter("SttnTo").toUpperCase().trim();
	}
	catch(Exception e)
	{
		strZoneFrom="";
		strDvsnFrom="";
		strSttnFrom="";
		strCrntZone="";
		strCrntDvsn="";
		strCrntSttn="";
		strZoneTo="";
		strDvsnTo="";
		strSttnTo="";
		strCmdt="";
		strRakeType="";
		strCnsg="";
		strCnsr="";
		strCtgy ="";
		strTwnFlag="";
		strCoaLnkg="";
		strSctnFrom="";
		strCrntSctn="";
		strSctnTo="";
		strLE ="";
		strTranType="";
	}
	
	strBaseMapDomn="BHUVAN";
	String strUserID= "";
    String strClntID= "";
    String strUserType="";
    if((String)request.getParameter("UserType")==null)
    {
    	strUserType="PUBLIC";   
    }
    else
    {
    	strUserType=(String)request.getParameter("UserType");   
    }
    System.out.println("strUserType"+strUserType);
    if (strUserType.equals("MROFC"))
    {
    	strUserID="MROFC";  
    	strClntID="1.1.1.1";
    }
    else
    {
    	strUserID=(String)session.getAttribute("UserID"); 
    	strClntID=(String)session.getAttribute("ClntID"); 
    }
    
    int intDataSize =0;
    String Call ="A";
    String strData[][] = null;
    	strData = obj.getPrclData("Z","","Z","","Z","");
	    if (strData == null)
	    {
	    	intDataSize =0;
	    	Call ="B";
	    }
	    else
	    {
	    	intDataSize=strData.length;
	    }
  System.out.println("Data Size:"+intDataSize);
%>
<script>
var map;
var vectorLayer;
var htmlPrclList;
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#legend").slideUp();
        var ht=screen.availHeight;
        $("#TestMap1").css("height",ht+"px");
    });
</script>
<script>

	 $(document).ready(function(){
        $('#imgLoctIcon').bind('click', function(){
        	$("#legend").slideToggle();
        });
    });
</script>
<script>
var markers;
var bolSmryView=true;

function toggleSmry()
{
	if(bolSmryView)
	{
		bolSmryView=false;
		$("#gridSmryView").hide();
		$(".btn-smry").removeClass("btn-success");
		$(".btn-smry").addClass("btn-warning");
		$(".btn-smry").html("&rarr;");
		$("#divMapView").removeClass();
		$("#divMapView").addClass("col col-xs-12 col-sm-12 col-md-12");
		markers.clearMarkers();
		var zoomlevel = map.getZoom();
		map.zoomTo(zoomlevel+1);
		map.zoomTo(zoomlevel-1);
		
	}
	else
	{
		bolSmryView=true;
		$("#gridSmryView").show();
		$(".btn-smry").removeClass("btn-warning");
		$(".btn-smry").addClass("btn-success");
		$(".btn-smry").html("X");
		$("#divMapView").addClass("col col-xs-12 col-sm-8 col-md-9");		
		var zoomlevel = map.getZoom();
		map.zoomTo(zoomlevel-1);
		markers.clearMarkers();
	}	
}
function gotoPoint(lng1,lat1)
{
	var lng=Number(lng1);	
	var lat=Number(lat1);
	map.zoomTo(7);
	map.panTo(new OpenLayers.LonLat(lng,lat));	
}
function showPoint(lng1,lat1)
{
	markers.clearMarkers();
	var lng=Number(lng1);	
	var lat=Number(lat1);
	var size = new OpenLayers.Size(24,38);
	var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	var icon = new OpenLayers.Icon('/foisweb/resources/images/mapmarker2.png', size, offset);
	var marker=new OpenLayers.Marker(new OpenLayers.LonLat(lng,lat),icon);
	markers.addMarker(marker);
}
 $(document).ready(function() { 
	init();
      $("#frmGoTo").submit(function(e){
      var locn=($("#txtGotoLocn").val()).trim().toUpperCase();
      getLocnCoord(locn);
      var locnCoord=($("#txtAjaxData").val()).trim();
      var lat=parseFloat((locnCoord.substr(0,locnCoord.indexOf("#"))).trim());
      var lng=parseFloat((locnCoord.substr(locnCoord.indexOf("#")+1)).trim());
      var sourcePrj1 = new OpenLayers.Projection("EPSG:4326");
     <%if(strBaseMapDomn.equals("GOOGLE"))
	 {%>   		
		map.zoomTo(5);
      	map.panTo(new OpenLayers.LonLat(lng,lat).transform(sourcePrj1, map.getProjectionObject()));
    <%}
	else
	{%>
		map.zoomTo(5);
		map.panTo(new OpenLayers.LonLat(lng,lat));
	<%}%>
      e.preventDefault();
      });
    });    
    
      function init() 
    {     
       window.parent.document.getElementById('waitImage').style.visibility   = "hidden";
       <% if(strZone.equals("") || strZone == null ){%>
        	<%} else if ((intDataSize ==0 )&& (Call.equals("B")))
        {%>
        alert("Data Not Available For the Given Input");
        <%}%>
        var geographic = new OpenLayers.Projection("EPSG:4326");
        var mercator = new OpenLayers.Projection("EPSG:900913");
         //Defining bounds    
        var world = new OpenLayers.Bounds(-180, -89, 180, 89).transform(
            geographic, mercator
        );

        var options = {
            projection: mercator,
            displayProjection: geographic,
            units: "m",
            maxExtent: world,
            maxResolution: 156543.0399,
        };
	   
       
      var wms;  
      if("<%=strBaseMapDomn%>"=="BHUVAN")
      {
      map = new OpenLayers.Map("TestMap1");  
      
      	wms = new OpenLayers.Layer.WMS("OpenLayers WMS",
 											"https://bhuvan-vec1.nrsc.gov.in/bhuvan/gwc/service/wms/", 
 											{layers: 'india3'
 											},									
 											{
 											opacity:0.6,
 											transitionEffect:'resize'
 											} 	
 											);
 	  }
 	  else
 	  {	
 	  map = new OpenLayers.Map("TestMap1",options);  						
 	 wms  = new OpenLayers.Layer.Google("Google India Base" , {type : google.maps.MapTypeId.ROADMAP, sphericalMercator : true });
 	  }
 	
      map.events.register("zoomend", map, function(){
	  var zoomlevel = map.getZoom();
		if(zoomlevel <= 5)
			map.zoomTo(5);
		else if(zoomlevel > 15)
			map.zoomTo(15);
		});
		
 		 var dm_wms = new OpenLayers.Layer.WMS("Rail Track",
           								 "http://fois.indianrail.gov.in/geoserver/wms/",
			            				{
						                layers: 'foisws:rail_track_clip',
						                transparent: 'true'
			            				},
            							{opacity : 1,
            							isBaseLayer: false,            							
 										transitionEffect:'resize'}
 										,{ numZoomLevels: 15,minZoomLevel: 1, maxZoomLevel: 15 }
       									 );							
								
  		 var wms_sttnname = new OpenLayers.Layer.WMS("IR Stations",
           								 "http://fois.indianrail.gov.in/geoserver/wms/",
			            				{
            							 layers: 'foisws:sttnnamenew',
						                transparent: 'true'
			            				},
            							{
            							opacity: 1,
            							isBaseLayer: false,            							
 										transitionEffect:'resize'}
       									 );         
		
		
		markers = new OpenLayers.Layer.Markers( "Markers" );
       	var vectorLayer = new OpenLayers.Layer.Vector("Loads Run Points");	
        map.addLayers([wms, dm_wms,wms_sttnname,markers,vectorLayer]);
        var pointFeatures 	= [];
        var pointGeometry;
        var lng;
        var lat; 
        
      map.events.register("zoomend", map, function()
      {
      	 vectorLayer.destroyFeatures();
		    while( map.popups.length ) 
		         map.removePopup(map.popups[0]);      	 
      	 var zoomlevel = map.getZoom();
      	 if(zoomlevel < 5)	
      	 	map.zoomTo(5);
      	 else if (zoomlevel > 15)
      	 	map.zoomTo(15);
      	 zoomlevel = map.getZoom();
    	htmlPrclList='<table class="table table-bordered table-responsive table-striped"><thead><tr><th class="pPrclHead"></th></tr></thead><tbody>';  	        
         <%for(int i=0;i<intDataSize-1;)
         {
        	 intCntr++;
        	 int count = Integer.parseInt("1");%> /*Index 24*/
	        lng=Number(<%=strData[i][3]%>);
	        lat=Number(<%=strData[i][2]%>);
	        var ptColr="";
				        
			<% if(strData[i][16].equals("0") || strData[i][16].equals("")) { intOTCntr++; } else { intDlyCntr++; }%>
			  			
	        if("<%=strBaseMapDomn%>"=="BHUVAN")
	        	pointGeometry = new OpenLayers.Geometry.Point(lng,lat);
			else
				pointGeometry = new OpenLayers.Geometry.Point(lng,lat).transform(geographic, mercator);  			
			var id = "Station:<b><%=strData[i][10]%></b>";/*Index 26*/
			
	    	var pointstyle = OpenLayers.Util.extend();	    	
	        pointstyle.strokeColor = '#ffffff';
	        pointstyle.label		 = '1';   /*Index 24*/
	        pointstyle.fontColor 	 = "#ffffff";
	        pointstyle.fillOpacity = "1";    
	        
	        if(zoomlevel == 5 || zoomlevel == 6 )
	        {
	        	pointstyle.pointRadius = 10;
	        	pointstyle.fontSize    = "8px";
	        }     
	 		else if(zoomlevel == 7 || zoomlevel == 8)
	        {
	        	pointstyle.pointRadius = 12;
	        	pointstyle.fontSize    = "10px";
	        }
	   		else if(zoomlevel == 9 || zoomlevel > 9)
	        {
	        	pointstyle.pointRadius = 14;
	        	pointstyle.fontSize    = "12px";
	        }     
	        
		    var popupmsg="<%=count%>##<div style='overflow:scroll;'><table class='table dtlstbl table-striped'><thead><tr><td colspan='3'><b><%=strData[i][0]%></b></td>";
		    popupmsg+="<td colspan='2'><span class='label label-success'>LOADED</span> / <span class='label label-empty'>EMPTY</span></td></tr>";
			popupmsg+="<tr><th>Train</th><th>Type/Start</th><th>From-To</th><th>Status</th><th>ETA(Dstn)</th><% if(strTranType.equals("A") || strTranType.equals("F")) { %> <th>Linkage</th><% } %></tr></thead><tbody>";
				<%for(int k=0;k<count;k++,i++){%>
				
	        <% if(strData[i][14].indexOf("LOADED")>0) { intLCntr++; %>	        
	        htmlPrclList+='<tr><td><div class="prcllded" style="max-height:60px;"  onmouseover="showPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);" onclick="gotoPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);"><p class="pPrclTran" onmouseover="showPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);"><%=strData[i][5]%> <%if(!strData[i][16].equals("0")) {%> <span class="label label-danger pull-right">Delay: <%=strData[i][16]%> Mi </span> <% } else { %><span class="label label-success pull-right">On Time</span><% }%></p>';
	        ptColr="#2d862d";
	        <% } else { if(strData[i][14].indexOf("EMPTY")>0) { intECntr++; %>
	        htmlPrclList+='<tr><td><div class="prclempt" onmouseover="showPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);" onclick="gotoPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);" style="max-height:60px;"><p class="pPrclTran"><%=strData[i][5]%><%if(!strData[i][16].equals("0")) {%> <span class="label label-danger pull-right">Delay: <%=strData[i][16]%> Mi </span> <% } else { %><span class="label label-success pull-right">On Time</span><% }%></p>';
	        
	        if(ptColr=="")
	        	ptColr="#e65c00";
	        	
	        <% } else { %>
	        	htmlPrclList+='<tr><td><p class="pPrclTran" onmouseover="showPoint(<%=strData[i][3]%>,<%=strData[i][2]%>);"><%=strData[i][5]%><%if(!strData[i][16].equals("0")) {%> <span class="label label-danger pull-right">Delay: <%=strData[i][16]%> Mi </span> <% } else { %><span class="label label-success pull-right">On Time</span><% }%></p>';
	        	ptColr="#595959";
	        <% }} %>
	        htmlPrclList+='<p class="pTranStts"><%=strData[i][6]%> &rarr; <button type="button" class="btn btn-default btn-xs" style="font-size:10px;"><%=strData[i][10]%> / [<%=strData[i][12]%>] <%=strData[i][13]%></button> &rarr;<%=strData[i][8]%></p></div></td></tr>'
							
					<% if(strData[i][14].indexOf("LOADED")>0) {strLinkClss="linkLd" ;} %>
					<% if(strData[i][14].indexOf("EMPTY")>0) {strLinkClss="linkEm" ;} %>
 					popupmsg+='<tr><td style="color:#005ce6;font-weight:bold;"><a href="#" class="<%=strLinkClss%>" onClick="openPsgrRoute(\'<%=strData[i][5]%>\',\'<%=strData[i][15]%>\');return false;"><b><%=strData[i][5]%></b></a></td>';
 						popupmsg+='<td><a href="#" onClick="openPrclOcpn(\'<%=strData[i][5]%>\',\'<%=strData[i][15]%>\');return false;"><%=strData[i][15]%></a></td>';
 						popupmsg+='<td><%=strData[i][6]%> &rarr; <%=strData[i][8]%></td>'; 					 
 						popupmsg+='<td ><%=strData[i][10]%> (<%=strData[i][12]%>) <%=strData[i][13]%></td><td ><%=strData[i][19]%></td><% if(strTranType.equals("F")||strTranType.equals("A")) { %><td ><%=strData[i][27]%> </td><% } %>'; 						 
 				<%}%>
  				popupmsg+='</tbody></table></div>';
		                                
		    pointstyle.fillColor	 = ptColr;
		    
	 		var pointFeature = new OpenLayers.Feature.Vector(pointGeometry,{sttncode: id,message:popupmsg},  pointstyle);
	 		pointFeatures.push(pointFeature);
		<%}%>
		
		htmlPrclList+='</tbody></table>';
		$("#divPrclList").html(htmlPrclList);
		$(".pPrclHead").html("PARCEL EXPRESS TRAINS (<%=intCntr%>= <%=intLCntr%>/L + <%=intECntr%>/E)");
		drawPrclStat(<%=intLCntr%>,<%=intECntr%>,<%=intOTCntr%>,<%=intDlyCntr%>);
        vectorLayer.addFeatures(pointFeatures);
	    var selector = new OpenLayers.Control.SelectFeature(vectorLayer,{                                                                                                                      
            callbacks: { 
               click: functionC,
               touchstart: functionC <%--,
               over: functionD       --%>
		    }                                                                                                                                                                      
			});	
			
		function functionC(feature) 
		{ 
		    while( map.popups.length ) 
		         map.removePopup(map.popups[0]);
			var strHTML=feature.attributes.message;
			var rakecont=strHTML.substr(0, strHTML.indexOf('##'));
			var finalHTML=strHTML.substring(strHTML.indexOf("##")+2);
			
		
			if(parseInt(rakecont)>7)
			{
			     var targeted_popup_class = "popup-2";
			     $(".popup-inner1").html(finalHTML);
				$('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);
			}
			else
			{
          		var popup = new OpenLayers.Popup.FramedCloud("popup",
              	OpenLayers.LonLat.fromString(feature.geometry.toShortString()),
              	new OpenLayers.Size(100,100),
              	finalHTML,
              	null,
              	true,
              	null
          		);
          	
	          popup.autoSize = true;
	          popup.maxSize = new OpenLayers.Size(5100,2500);
	          popup.fixedRelativePosition = true;
	          feature.popup = popup;
	          map.addPopup(popup);
			}
		}                              
		/*function functionD(feature) 
		{		
			$("#sttninfo").html(feature.attributes.sttncode);
		} */                                                                                                                        
		map.addControl(selector);
		selector.activate();	       
		});
	 
		map.addControl(new OpenLayers.Control.LayerSwitcher());
		if("<%=strBaseMapDomn%>"=="BHUVAN")
      	{
			map.setCenter(new OpenLayers.LonLat(78,21),5);
      	}
      	else
      	{
			var sourcePrj1 = new OpenLayers.Projection("EPSG:4326");
			map.setCenter(new OpenLayers.LonLat(78,21).transform(sourcePrj1, map.getProjectionObject()),5);
		}	 
 }
</script>
 <script>
    	$(document).ready(function(){
    	$("#SmryTabl").hide();
    	$(".dropdown-menu li a").click(function(){
  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
});
    	});
    </script>
    <script>
    var alldata;
    var flag;
    var locn; 
        var chrtprclocpn;
        var chrtprclstat;
        function openPrclOcpn(trnname, strtdate)
        {
        	$("#divTranRout").hide();
        	$("#divPrclOcpn").html('<div align="center"><img src="/foisweb/resources/images/sfrtdb/loadingIcon2.gif" style="width:210px;height:140px;" /></div>');
        	var trannumb=trnname.substring(0,trnname.indexOf("(")).trim();
        	var startdate=strtdate.substring(strtdate.indexOf("/")+1).trim();
        	var newDate="";
        	if(startdate!="")
        	{
        		var newarr=startdate.split("-");
        		newDate=newarr[2]+"/"+newarr[1]+"/"+newarr[0];
        	}
        	else
        	{
        		newDate=startdate;
        	} 
        	$("#pPrclHedr").html(trnname +" (Start:"+startdate+")");
        	openPrclConsist(trannumb, newDate);
        	
        	<%--
        	var htmlPrclOcpn='<div class="panel panel-default" ><div class="panel-heading">Current Occupancy Detail (PMS)</div><div class="panel-body"><div class="row"><div class="col col-sm-6 col-xs-12" align="right"><canvas id="chrtPrclOcpn" height="115" ></div><div class="col col-sm-6 col-xs-12" align="left"><table class="table table-bordered table-striped" style="min-width:140px;max-width:300px;"><tbody>';
        	$("#pPrclHedr").html(trnname);
		    var myurl="/foisweb/GG_FOISMapJsonData?Optn=PRCL_TRAN_OCPN&TranName="+trannumb+"&StrtDate="+newDate;
		    var iLdWght=0;
		    var iAvblSpc=0;
		    $("#divTranRout").html('<div align="center"><img src="/foisweb/resources/images/sfrtdb/loadingIcon2.gif" style="width:210px;height:140px;" /></div>');	
			$('[data-popup="popup-3"]').fadeIn(350);
		   	$.ajax({
		        url : myurl,
		        type : "post",
		        async : true,
		        success : function(data) {
			 		var obj = JSON.parse(data);
				   var wgoncont=obj.wagoncnt;
				   var ldwght=obj.loadedwt;
				   var spcavbl=obj.spaceavailable;
				   htmlPrclOcpn+='<tr><td>No. of Coaches</td><td style="font-weight:600;">'+wgoncont+'</td></tr>';
				   htmlPrclOcpn+='<tr><td>Loaded Weight (Quintals)</td><td style="font-weight:600;border-left:3px solid #ff6600;">'+ldwght+'</td></tr>';
				   htmlPrclOcpn+='<tr><td>Space Available (Quintals)</td><td style="font-weight:600;border-left:3px solid #339933;">'+spcavbl+'</td></tr>';
				   htmlPrclOcpn+='</tbody></table>';
				   iLdWght=Number(ldwght);
				   iAvblSpc=Number(spcavbl);
				   htmlPrclOcpn+='</div></div></div><p class="pmsftr">Occupancy Detail / Coach Composition is per the information available in <b>Parcel Management System (PMS)</b> of Indian Railways</p>';
				   $("#divTranRout").html("");
					$("#divPrclOcpn").html(htmlPrclOcpn);
					$('[data-popup="popup-3"]').fadeIn(350);
					
					var config = {
					type: 'pie',
					data: {
						datasets: [{
							data: [iLdWght, iAvblSpc],
					backgroundColor: ['#ff6600','#339933'],
					label: 'Parcel Train Occupancy'
						}],
						labels: ['Loaded','Available']
							},
							options: {
								responsive: true
							}
						};
				
				      if (chrtprclocpn!=undefined) {
				        chrtprclocpn.destroy();
				      }
						var ctx = document.getElementById('chrtPrclOcpn').getContext('2d');
						chrtprclocpn = new Chart(ctx, config);
				}
	       });
	       --%>        	 
        }        
        function openPrclConsist(trannumb, strtdate)
        {        	 
        	var htmlPrclConsist='<p class="pCnstHead">Coach Composition (PMS)</p><table class="table table-bordered table-striped table-style" style="width:100%;"><thead><tr><th>SR.No.</th><th>Type</th><th>Capacity</th><th>Loaded Wt.</th><th>Available</th></tr></thead><tbody>';
		    var myurl="/foisweb/GG_FOISMapJsonData?Optn=PRCL_TRAN_CONSIST&TranName="+trannumb+"&StrtDate="+strtdate;	
		    	    	
			$("#divTranRout").html("");	
			$("#divPrclCnst").html("");
			var totlldwght=0;
			var totlcpty=0;
			var totluldgwght=0;
			var totlavblcpty=0;
			var iLdWght=0;
			var iAvblSpc=0;
				  
		   	$.ajax({
		        url : myurl,
		        type : "post",
		        async : true,
		        success : function(data) {
			 		var obj = JSON.parse(data);
			 		for(var i=0;i<obj.wagonarray.length;i++)
			 		{
			 			var wgontype=obj.wagonarray[i].wagontype;
			 			var wgoncpty=Number(obj.wagonarray[i].wagoncapacity);
			 			var wgonldwght=Number(obj.wagonarray[i].loadedwt);
			 			var wgonuldgwght=Number(obj.wagonarray[i].unloadedwt);
			 			var wgonavblcpty=Number(obj.wagonarray[i].spaceavailable);
			 			totlcpty+=wgoncpty;
			 			totlldwght+=wgonldwght;
			 			totluldgwght+=wgonuldgwght;
			 			totlavblcpty+=wgonavblcpty;
			 			
			 			htmlPrclConsist+='<tr><td>'+(i+1)+'</td><td>'+wgontype+'</td><td>'+wgoncpty+'</td><td>'+wgonldwght+'</td><td>'+wgonavblcpty+'</td></tr>';
			 		}
			 		htmlPrclConsist+='<tr style="font-weight:600;background:#abb3ed;"><td>&nbsp;</td><td>TOTAL</td><td>'+totlcpty+'</td><td>'+totlldwght+'</td><td>'+totlavblcpty+'</td></tr>';
			 		htmlPrclConsist+='</tbody></table><p class="pCnstFtr">*All the weights are in Quintals</p>';
			 		$("#divPrclCnst").html(htmlPrclConsist);
   				   
   				   var htmlPrclOcpn='<div class="panel panel-default" ><div class="panel-heading">Current Occupancy Detail (PMS)</div><div class="panel-body"><div class="row"><div class="col col-sm-6 col-xs-12" align="right"><canvas id="chrtPrclOcpn" height="115" ></div><div class="col col-sm-6 col-xs-12" align="left"><table class="table table-bordered table-striped" style="min-width:140px;max-width:300px;"><tbody>';
			 	   htmlPrclOcpn+='<tr><td>No. of Coaches</td><td style="font-weight:600;">'+obj.wagonarray.length+'</td></tr>';
			 	   htmlPrclOcpn+='<tr><td>Total Capacity (Quintals)</td><td style="font-weight:600;">'+totlcpty+'</td></tr>';
				   htmlPrclOcpn+='<tr><td>Loaded Weight (Quintals)</td><td style="font-weight:600;border-left:3px solid #ff6600;">'+totlldwght+'</td></tr>';
				   htmlPrclOcpn+='<tr><td>Space Available (Quintals)</td><td style="font-weight:600;border-left:3px solid #339933;">'+totlavblcpty+'</td></tr>';
				   htmlPrclOcpn+='</tbody></table>';
				   
				   iLdWght=Number(totlldwght);
				   iAvblSpc=Number(totlavblcpty);
				   
				   htmlPrclOcpn+='</div></div></div><p class="pmsftr">Occupancy Detail / Coach Composition is per the information available in <b>Parcel Management System (PMS)</b> of Indian Railways</p>';
				   $("#divTranRout").html("");
		
					$("#divPrclOcpn").html(htmlPrclOcpn);
					$('[data-popup="popup-3"]').fadeIn(350);
					
					var config = {
					type: 'pie',
					data: {
						datasets: [{
							data: [iLdWght, iAvblSpc],
					backgroundColor: ['#ff6600','#339933'],
					label: 'Parcel Train Occupancy'
						}],
						labels: ['Loaded','Available']
							},
							options: {
								responsive: true
							}
						};
				
				      if (chrtprclocpn!=undefined) {
				        chrtprclocpn.destroy();
				      }
						var ctx = document.getElementById('chrtPrclOcpn').getContext('2d');
						chrtprclocpn = new Chart(ctx, config);
			 		
			 	}
			 });
        }
		function openPsgrRoute(trnname,strtdate)
		{	
		   var htmlRout="";
		   var startdate=strtdate.substring(strtdate.indexOf("/")+1);
		   $("#pPrclHedr").html(trnname+" (Start:"+startdate+")");	
		   $("#divPrclOcpn").html("");	
		   $("#divPrclCnst").html("");
		   $("#divTranRout").show();
		   var myurl="/foisweb/GG_FOISMapJsonData?Optn=PSGR_TRAN_PPLN&TranName="+trnname+"&StrtDate="+strtdate;
		   	$.ajax({
		        url : myurl,
		        type : "post",
		        async : true,
		        success : function(data) {
			 		var obj = JSON.parse(data);
					for(var i=0;i<obj.data.length;i++)
					{
					   var sttn=obj.data[i].stnCode;
					   var schdarr=obj.data[i].schArrTime;
					   var actlarr=obj.data[i].actArrTime;
					   var arrdely=obj.data[i].delayArr;
					   var schddep=obj.data[i].schDepTime;
					   var actldep=obj.data[i].actDepTime;
					   var depdely=obj.data[i].delayDep;
					   var trvl=obj.data[i].travelling;
					   if(i==0)
					   		sttn=sttn+" (SOURCE)";	
					   if(i==(obj.data.length-1))
					   		sttn=sttn+" (DESTINATION)";
					   var arvltimeclss="";
					   var deptimeclss="";
					   if((actlarr=="") && (actldep==""))
					   {
				   			htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-primary"><i class="entypo-feather"></i></div>';
					   }
					   else
					   {
					 		if(((arrdely=="")||(arrdely="0")) && ((depdely=="")||(depdely=="0")))
					 		{
					 			htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-success"><i class="entypo-feather"></i></div>';
					 			arvltimeclss="ontimerptg";
					 			deptimeclss="ontimerptg";
					 		}
					 		else
					 		{
					 			if((arrdely.indexOf("-")>-1)||(depdely.indexOf("-")>-1))
					 			{
					 				if(arrdely.indexOf("-")>-1)
					 				{					 					
					 					arvltimeclss="ontimerptg";
					 				}
					 				if(depdely.indexOf("-")>-1)
					 				{					 					
					 					deptimeclss="ontimerptg";
					 				}
					 				htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-success"><i class="entypo-feather"></i></div>';
					 			}
					 			else
					 			{					 			
					 				if(arrdely!="")
					 				{					 					
					 					arvltimeclss="delyrptg";
					 				}
					 				if(depdely!="")
					 				{					 					
					 					deptimeclss="delyrptg";
					 				}
					 				htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-danger"><i class="entypo-feather"></i></div>';
					 			}
					 		}
				 		}
		                htmlRout+='<div class="timeline-label"><p class="sttncode">'+sttn+'</p>';
		                if(i>0)
		                {		                	
		                	if((arrdely!="") && (arrdely!="0"))
		                	{
		                		htmlRout+='<p class="arvltime">[AR]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schdarr+'</span>Actual:<span class="'+arvltimeclss+'">'+actlarr+'</span><span class="arrtimedely"></span> <span class="'+arvltimeclss+'" style="font-size:12px;font-weight:400;">[Delay: '+arrdely+' Mins]';
		                	}
		                	else
		                	{
								htmlRout+='<p class="arvltime">[AR]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schdarr+'</span>Actual:<span class="'+arvltimeclss+'">'+actlarr+'</span><span class="arrtimedely"></span>';		                	
		                	}
		                }
		                if(i<(obj.data.length-1))
		                {
							if((depdely!="") && (depdely!="0"))
		                	{		                		                	
			                	htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>Actual:<span class="'+deptimeclss+'">'+actldep+'</span><span class="deptimedely"></span> <span class="'+deptimeclss+'" style="font-size:12px;font-weight:400;">[Delay: '+depdely+' Mins]';
			                }
			                else
			                {
			                	htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>Actual:<span class="'+deptimeclss+'">'+actldep+'</span><span class="deptimedely"></span>';
			                }
			                htmlRout+='</div></div></article>';
			             }
		            }
					$("#divTranRout").html(htmlRout);
					$('[data-popup="popup-3"]').fadeIn(350);
				}
		          });
		}
</script>
 <div class="container-fluid">
 <div class="row">
 
 <% if(intDataSize>0) { %>
 <div class="col hidden-xs col-sm-4 col-md-3" id="gridSmryView">
  <div id="divPrclStat" >
 <p class="pPrclHead1">STATISTICS</p>
 	<canvas id="chrtPrclStat" height="120" ></canvas>
 	<div  align="center">
    <table style='border:0pt;margin-top:10px;'><tr>
					<td style='border:0pt solid;width:14px;height:10px;background:#339933;'></td>
					<td style='border:0pt;font-size:10px;font-weight:300;'>&nbsp;Loaded&nbsp;&nbsp;</td>
					<td style='border:0pt solid;width:14px;height:10px;background:#e65c00'></td>
					<td style='border:0pt;font-size:10px;font-weight:300;'>&nbsp;Empty&nbsp;&nbsp;</td>
					<td style='border:0pt solid;width:14px;height:10px;background:#99cc00;'></td>
					<td style='border:0pt;font-size:10px;font-weight:300;'>&nbsp;On Time&nbsp;&nbsp;</td>
					<td style='border:0pt solid;width:14px;height:10px;background:#e62e00'></td>
					<td style='border:0pt;font-size:10px;font-weight:300;'>&nbsp;Delayed</td>
				</tr></table>
				</div>
				
 </div> 
 <div id="divPrclList"></div>
 </div>
 <% } %>
 <% if(intDataSize>0) { %>
 <div class="col col-xs-12 col-sm-8 col-md-9" id="divMapView" >
 <% } else { %>
 <div class="col col-xs-12 col-sm-12 col-md-12"  id="divMapView" >
 <% } %>
<div style="width:100%;height:90vh;position:absolute; display:flex;" id="TestMap1" >
	<% if(intDataSize>0) { %>
	<button class="btn btn-sm btn-success btn-smry" onclick="toggleSmry();">X</button>
	<% } %>
     <div id="legend" class="legend" style="position:absolute;top:210px;right:0px;z-index:1500;">
		   <form id="frmGoTo" class="navbar-form navbar-right form-inline">
		    <input type="text" id="txtGotoLocn" class="form-control" style="text-transform:uppercase;" size="6" placeholder="Location">
		   
		 <button type="submit" class="btn btn-success pull-right">Find</button>   
		  <input type="hidden" id="txtAjaxData"  class="form-control" />   
		</form>
    </div>
     <img src="/foisweb/resources/images/LocateIcon2.png" id="imgLoctIcon" style="cursor:pointer;position:absolute;top:180px;right:0px;z-index:1200;">
</div>
</div>

</div>
<% if(intDataSize>0) { %>
 <% } %>
		<div class="popup" data-popup="popup-2" style="z-index:10000;width:90%;height:250px;overflow-y:hidden;top:20%;left:5%;background:#fff;">
				<p><a data-popup-close="popup-2" class="pull-right" href="#"><b>Close</b></a></p>
			<div class="popup-inner1"  style="height:200px;overflow:scroll;">
			</div>
		</div>
    <div class="main">
		<div class="content">
			<p></p>
		</div>
	
		<div class="popup" data-popup="popup-1">
			<div class="popup-inner">
				<div class="alert alert-info" id="lblRakeID">&nbsp;</div>
				 
				<p><iframe id="frmLoadRout" src="/foisweb/view/qry/blank_loading.jsp" width="100%" height="340px" ></iframe></p>
				<p><a data-popup-close="popup-1" href="#">Close</a></p>
				<a class="popup-close" data-popup-close="popup-1" href="#">x</a>
			</div>
		</div>
	</div>
	
    <div class="main">
		<div class="popup" data-popup="popup-3">
			<div class="popup-inner">
			<p class="prclhedr" id="pPrclHedr"></p>
				 <div id="divPsgrRout" style="width:100%;height:370px;overflow-y:scroll;overflow-x:hidden;padding:10px;">
					<div class="row">
        				<div class="timeline-centered" id="divTranRout"></div>
        				<div id="divPrclOcpn"></div>
        				<div id="divPrclCnst"></div>
        			</div>
				</div>
				<p><a data-popup-close="popup-3" href="#">Close</a></p>
				<a class="popup-close" data-popup-close="popup-3" href="#">x</a>
			</div>
		</div>
	</div>
	
 </div>
<script>
<% if(intDataSize>0) { %>
function drawPrclStat(lcont, econt, ontimecont, dlycont)
{
	var config = {
			type: 'doughnut',
			data: {
				datasets: [{
					data: [lcont,econt],
					backgroundColor: ['#339933','#e65c00','#99cc00','#e62e00'],
					labels:['Loaded','Empty']
				},
				{
					data: [ontimecont,dlycont],
					backgroundColor: ['#99cc00','#e62e00','#339933','#e62e00'],
					labels:['On Time','Delayed']
				}]
			},
			options: {
				responsive: true,
				legend: {
					position: 'right',					
				},
				title: {
					display: false,
					text: 'On-Run Statistics'
				},
				animation: {
					animateScale: true,
					animateRotate: true
				},
        tooltips: {
            callbacks: {
                label: function(tooltipItem, data) {
                    var dataset = data.datasets[tooltipItem.datasetIndex];
                    var index = tooltipItem.index;
                    return dataset.labels[index] + ': ' + dataset.data[index];
                }
            }
        }
			}
		};
		
      if (chrtprclstat!=undefined) {
        chrtprclstat.destroy();
      }
		var ctx = document.getElementById('chrtPrclStat').getContext('2d');
		chrtprclstat = new Chart(ctx, config);
}
<% } %>
</script> 
<%@ include file="/pages/GG_Footer.jspf" %>