<!doctype html>
<html lang="en">

<head>
<title>Welcome To Indian Railways' Freight Services</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%
   util.HindiLocaleUtil E =new util.HindiLocaleUtil("E");
     
        
     String strFnrNumb1=  (String)request.getParameter("FNR");
                    %>

    <!-- Google font (font-family: 'Roboto', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto Condensed', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700" rel="stylesheet">


<style>
.fnrmapdiv
{
width:100%;
height:75vh;
z-index:10;
position:fixed;
margin-top:0px;
margin-left:10px;
margin-right:10px;
border:1px solid #ababab;
}
.alert-footer
{
	margin:10px;
	font-size:13px;
}
.ftrmesg
{
	font-size:11px;
	font-weight:600;
	padding-left:4px;
	padding-right:4px;	
	border-right:1px solid #ddd;
	font-family:'Roboto Condensed';
}
.mapftr
{
	width:100%;
	margin-left:10px;
	margin-right:10px;	
}
.hedrgrad
{
background: rgba(255,255,255,1);
background: -moz-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -webkit-gradient(left top, right top, color-stop(0%, rgba(255,255,255,1)), color-stop(47%, rgba(246,246,246,1)), color-stop(100%, rgba(237,237,237,1)));
background: -webkit-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -o-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -ms-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: linear-gradient(to right, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed', GradientType=1 );
}
.card-desc
{
	padding:0.4rem !important;
background: rgba(255,255,255,1);
background: -moz-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -webkit-gradient(left top, right top, color-stop(0%, rgba(255,255,255,1)), color-stop(47%, rgba(246,246,246,1)), color-stop(100%, rgba(237,237,237,1)));
background: -webkit-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -o-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: -ms-linear-gradient(left, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
background: linear-gradient(to right, rgba(255,255,255,1) 0%, rgba(246,246,246,1) 47%, rgba(237,237,237,1) 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed', GradientType=1 );

}
.head-desc
{
	font-family:'Roboto Condensed';
	font-weight:600;
	color:#4d4d4d;
	font-size:1rem;
}
</style>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>    
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
</head>
<body>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>
      <div class="container-fullwidth">
	        <div class="row align-items-center justify-content-center text-center">
	          <div class="col-lg-12">
	            <div class="box-shadow-content">
	              <div class="block-heading-1">
	                <h3 class="head-desc mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("Status of your Consignment with FNR no")%>. <%=strFnrNumb1%></h3>
	              </div>
	            </div>
	           </div>
	          </div>
         <div class="row">
	 <div class="col-lg-10 col-sm-12">
		<div id="divSpecLocn" class="fnrmapdiv"></div>
		<div class="card border border-info mapftr" >
				   <div class="card-header card-desc">
					<table><tr>
					<td class="ftrmesg"><img src="/RailSAHAY/resources/images/src.png" />&nbsp;<%=E.EH("Source")%></td>
					<td class="ftrmesg"><img src="/RailSAHAY/resources/images/trvdflag.png" />&nbsp;<%=E.EH("Travelled Path")%></td>
					<td class="ftrmesg"><img src="/RailSAHAY/resources/images/crntflag.png" />&nbsp;<%=E.EH("Current Location")%></td>
					<td class="ftrmesg"><img src="/RailSAHAY/resources/images/futrflag.png" />&nbsp;<%=E.EH("Yet to Travel")%></td>
					<td class="ftrmesg"><img src="/RailSAHAY/resources/images/dstn.png" />&nbsp;<%=E.EH("Destination")%></td>
					</tr></table>
				   </div>
			</div>
	</div>
	<div class="col-lg-2 col-sm-12">
 		<%
                                        int lastcount   =   0;
                                        String strDesc[]  = null;
                                        String strCoordinates  =   "";
					String strFNRNumb=(String)request.getParameter("FNR");
                                        if(strFNRNumb==null ||strFNRNumb.equals("")){
                                        strFNRNumb=(String)request.getAttribute("FNR");}
                                        
                                        
					System.out.println("FNRNumb:"+strFNRNumb);
                                        int intDataSize =0;
					java.util.ArrayList fnrlist   =   (java.util.ArrayList) model.FSH_FNREnqTX.getFRNEnquiry(strFNRNumb);
					if(fnrlist!=null)
					System.out.println("fnrlist.size():"+fnrlist.size());
					if(fnrlist.size()>0)
					{
					    String strDtls[][]      =  (String[][]) model.FSH_FNREnqTX.getFRNEnquiry(strFNRNumb).get(0); //FNR Details
					    String strMapDtls[][]   =  (String [][]) model.FSH_FNREnqTX.getFRNEnquiry(strFNRNumb).get(1); //FNR Map Details
					    //String strLatLng[][]   =  (String [][]) model.FSH_FNREnqTX.getFRNEnquiry(strFNRNumb).get(2); //FNR Map all points Details
                                            //String strLatLng[][]    =   (String [][]) model.GG_TrmlUtilSrvc.getFNRRoute(strFNRNumb);
                                            
                                            strCoordinates	=	"[";
                                            intDataSize         =   strMapDtls.length;
                                            lastcount	        =   strMapDtls.length -1;
                                            for(int i=0; i<intDataSize; i++)
                                                    if(i==0)
                                                            strCoordinates	=	strCoordinates + "{lat:"+strMapDtls[i][4]+",lng:"+strMapDtls[i][5]+"}";
                                                    else
                                                            strCoordinates	=	strCoordinates + ","+"{lat:"+strMapDtls[i][4]+",lng:"+strMapDtls[i][5]+"}";
                                            strCoordinates=strCoordinates+"]";	
                                            System.out.println("DataSize:"+intDataSize);        
					    if(strDtls != null)
					    {
					        System.out.println("No. of rows got:"+strDtls.length);
					%> 
					
				<div class="card border border-info mr-1" style="height:75vh;overflow-y:scroll;">
				   <div class="card-header hedrgrad"><%=E.EH("Last Reported Detail")%></div>					  
				 	   <div class="card-body text-primary">
						<div class="panel panel-primary">
						<div class="form-card">
				 		<table align="center" class="table table-striped1">
										<tbody>
										<% for (int i=0;i<strDtls.length;i++){ %>
											<tr>
												<td style="text-align:left;"><b><%=E.EH("STATION FROM")%></b></td>
												<td style="text-align:left;"><%=strDtls[i][11] %></td>
											</tr>
											<tr>
												<td style="text-align:left;"><b><%=E.EH("STATION TO")%></b></td>
												<td style="text-align:left;"><%=strDtls[i][12]%></td>
											</tr>
											<tr>
												<td style="text-align:left;"><b><%=E.EH("LAST REPORTED STATUS")%></b></td>
												<td style="text-align:left;"><%=strDtls[i][13]%></td>
											</tr>
											
											<tr>
												<td style="text-align:left;"><b><%=E.EH("EXPECTED AT DESTINATION")%></b></td>
												<td style="text-align:left;"><%=strDtls[i][9]%></td>
											</tr>
										<%}%>
										</tbody>
								</table>
					</div>
					</div>
					</div>
					</div>
<% }
%>
</div>
</div>
<div class="alert alert-info alert-footer" align="left">
<%=E.EH("DISCLAIMER")%>:<br/>
1. <%=E.EH("DETAILS SHOWN ABOVE ARE FOR INFORMATION PURPOSE")%>.<br/>
2. <%=E.EH("DETAILS SHOULD BE VERIFIED FROM CONCERNED RAILWAY")%>.
</div>
<script>
	//getFNRMapData('<%=strFNRNumb%>');
        
function initMap() 
{
	var srcIcon="/RailSAHAY/resources/images/src.png";
	var pastIcon="/RailSAHAY/resources/images/trvdflag.png";	
	var destinationIcon="/RailSAHAY/resources/images/dstn.png";
	var currentIcon="/RailSAHAY/resources/images/crntflag.png";
	var futureIcon="/RailSAHAY/resources/images/futrflag.png";
	
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:6,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divSpecLocn"),mapProp);
	<%if(strMapDtls != null){%>
	<%for(int i=0;i<strMapDtls.length;i++){
            if(strMapDtls[i][10].equals("C")) ;
                if(i==0)		
                    strDesc=strMapDtls[0][9].split("#");
        %>                
		console.log("<%=strMapDtls[i][10]%> <%=strMapDtls[i][6]%>");
                                if("<%=strMapDtls[i][10]%>"=="C")
				{
									
					 var contentString<%=i%> = '<div id="content">'+
                                                                    '<table class="tabformat">'+
                                                                    '<tr style="border:0px;font-size:16px;color: blue;font-weight:bold;" align="center"><td colspan="2" align="center"><b><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</b></td></tr>'+					
                                                                    '<tr><td colspan="2"></td></tr>'+											
                                                                    '<tr><td><b>Detail</b></td><td><%=strDesc[2]%>/<%=strDesc[3]%>/<%=strDesc[4]%></td></tr>'+
                                                                    '<tr><td><b>From</b></td><td><%=strDesc[0]%></td></tr>'+
                                                                    '<tr><td><b>To</b></td><td><%=strDesc[1]%></td></tr>'+
                                                                    '<tr><td><b>Station</b></td><td><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</td></tr>'+
                                                                    '<tr><td><b>Zone (Division) </b></td><td><%=strMapDtls[i][8]%> (<%=strMapDtls[i][7]%>)</td></tr>'+																										
                                                                    '<tr><td><b>Arrival</b></td><td><%=strMapDtls[i][1]%></td></tr>'+
                                                                    '<tr><td><b>Departure</b></td><td><%=strMapDtls[i][2]%></td></tr>'+
                                                                    '<tr><td><b>ETA At Destination</b></td><td><%=strMapDtls[i][11]%></td></tr>'+
                                                                    '</table>'+
                                                                    '</div>';	
                                            
                                        var image = {
                                        url: currentIcon,
                                        size: new google.maps.Size(30, 30)
                                      };
				}
				else if("<%=strMapDtls[i][10]%>"=="F")
				{				
					 var contentString<%=i%> = '<div id="content">'+
                                                                    '<table class="tabformat">'+
                                                                    '<tr style="border:0px;font-size:12px;color: blue;font-weight:bold;" align="center"><td colspan="2" align="center"><b><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</b></td></tr>'+					
                                                                    '<tr><td><b>Station</b></td><td><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</td></tr>'+
                                                                    '<tr><td><b>Zone (Division) </b></td><td><%=strMapDtls[i][8]%> (<%=strMapDtls[i][7]%>)</td></tr>'+																										
                                                                    '</table>'+
                                                                    '</div>';	
                                        var image = {
                                        url: futureIcon,
                                        size: new google.maps.Size(12, 12)
                                      };						 
		         }
		        else if("<%=strMapDtls[i][10]%>"=="S")
				{	
					 var contentString<%=i%> = '<div id="content">'+
                                                                '<table class="tabformat">'+
                                                                '<tr style="border:0px;font-size:16px;color: blue;font-weight:bold;" align="center"><td colspan="2" align="center"><b><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</b></td></tr>'+					
                                                                '<tr><td colspan="2"></td></tr>'+							
                                                                '<tr><td><b>Station</b></td><td><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</td></tr>'+
                                                                '<tr><td><b>Zone (Division) </b></td><td><%=strMapDtls[i][8]%> (<%=strMapDtls[i][7]%>)</td></tr>'+																										
                                                                '<tr><td><b>Departure Time</b></td><td><%=strMapDtls[i][2]%></td></tr>'+
                                                                '</table>'+
                                                                '</div>';	
                                        var image = {
                                        url: srcIcon,
                                        size: new google.maps.Size(30, 30)
                                      };		
		         }
		        else if("<%=strMapDtls[i][10]%>"=="P")
                        {				
                                 var contentString<%=i%> = '<div id="content">'+
                                                            '<table class="tabformat">'+
                                                            '<tr style="border:0px;font-size:12px;color: blue;font-weight:bold;" align="center"><td colspan="2" align="center"><b><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</b></td></tr>'+					
                                                            '<tr><td><b>Station</b></td><td><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</td></tr>'+
                                                            '<tr><td><b>Zone (Division) </b></td><td><%=strMapDtls[i][8]%> (<%=strMapDtls[i][7]%>)</td></tr>'+																										
                                                            '</table>'+
                                                            '</div>';	
                                        var image = {
                                        url: pastIcon,
                                        size: new google.maps.Size(12, 12)
                                      };							 
		         }
                        else if("<%=strMapDtls[i][10]%>"=="D")
                        {
                                 var contentString<%=i%> = '<div id="content">'+
                                                            '<table class="tabformat">'+
                                                            '<tr style="border:0px;font-size:16px;color: blue;font-weight:bold;" align="center"><td colspan="2" align="center"><b><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</b></td></tr>'+					
                                                            '<tr><td colspan="2"></td></tr>'+																	
                                                            '<tr><td><b>Station</b></td><td><%=strMapDtls[i][0]%>(<%=strMapDtls[i][6]%>)</td></tr>'+
                                                            '<tr><td><b>Zone (Division) </b></td><td><%=strMapDtls[i][8]%> (<%=strMapDtls[i][7]%>)</td></tr>'+																										
                                                            '<tr><td><b>ETA At Destination</b></td><td><%=strMapDtls[i][11]%></td></tr>'+
                                                            '</table>'+
                                                            '</div>';
                                    var image = {
                                url: destinationIcon,
                                size: new google.maps.Size(30,30)
                              };
					
		         }
		marker<%=i%> = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: <%=strMapDtls[i][4]%>, lng:<%=strMapDtls[i][5]%>},
		title: 'FNR No. <%=strFNRNumb%>',		
		icon: image
		});
                
		var infowindow<%=i%>  	= new google.maps.InfoWindow({content: contentString<%=i%> });

	         marker<%=i%>.addListener('click', function() {
				<%for(int j=0;j<strMapDtls.length;j++)
				{%>
			    	infowindow<%=j%>.open( null, null ); /* To remove any opened info window */
			    <%}%>
	         	infowindow<%=i%>.open(map, marker<%=i%>);
 			 });
	<%}%>
	
	/* To remove any opened info window start*/
	google.maps.event.addListener( map, 'click', function() { 
		<%for(int j=0;j<strMapDtls.length;j++)
		{%>
	    	infowindow<%=j%>.open( null, null ); 
	    <%}%>
	});
	/* To remove any opened info window end*/

	var Coordinates=	<%=strCoordinates%>;
	var Path = new google.maps.Polyline({
	    path: Coordinates,
	    geodesic: true,
	    strokeColor: '#404040',
	    strokeOpacity: 1.0,
	    strokeWeight: 2
	  });	
	Path.setMap(map);
	map.setCenter(new google.maps.LatLng(<%=strMapDtls[0][4]%>, <%=strMapDtls[0][5]%>));
	<%}%>
}

</script>
<%} else {
%>
<div class="alert alert-danger col col-lg-8 col-md-8 col-sm-12" align="center">
<%=E.EH("No Detail Found for the Specified FNR")%>.</div>
							<br/>
							<br/>
<% } %>				
              </div>
            </div>
        </div>
		</div>
		</div>
	  </div>
	</div>
		
</body>
</html>
