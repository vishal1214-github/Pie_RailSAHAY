<?audit suppress oracle.ide.xml.wellformedness-error?>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

String si_SttnFrom        = request.getParameter("txtSttnFrom");
String si_SttnTo        =   request.getParameter("txtSttnTo");
        try
        {
            if(si_SttnFrom.indexOf("-") != -1)
                    si_SttnFrom                              =       si_SttnFrom.substring(si_SttnFrom.lastIndexOf("-")+1,si_SttnFrom.lastIndexOf("(")).trim();
            if(si_SttnTo.indexOf("-") != -1)
                    si_SttnTo                                =       si_SttnTo.substring(si_SttnTo.lastIndexOf("-")+1,si_SttnTo.lastIndexOf("(")).trim();
      
        }
        catch(Exception e){}

    System.out.println("si_SttnFrom:"+si_SttnFrom);
    System.out.println("si_SttnTo:"+si_SttnTo);
    
String[][] strDtls      =   null;
String strCoordinates   =   "";
int intDataSize 	=   0;
int lastcount		=   -1;
if(si_SttnFrom != null && si_SttnTo != null)
{
    try
    {
    org.codehaus.jettison.json.JSONObject resObj = new org.codehaus.jettison.json.JSONObject();		
    resObj = model.FSH_FrgtCalc.getRoutjson(si_SttnFrom,si_SttnTo,"D"); 
    String route = resObj.getString("route");
    System.out.println("Route:"+route);
        util.GG_AppFuncMap obj	=	new util.GG_AppFuncMap();
	strDtls      =	obj.mapGetFrgtCalcLatLong(si_SttnFrom+"-"+route+"-"+si_SttnTo);
	strCoordinates	=	"[";
	if (strDtls == null)
		intDataSize =0;
	else
	{
		intDataSize=strDtls.length;
		lastcount	=	strDtls.length -1;
		for(int i=0; i < intDataSize; i++)
			if(i==0)
				strCoordinates	=	strCoordinates + "{lat:"+strDtls[i][4]+",lng:"+strDtls[i][5]+"}";
			else
				strCoordinates	=	strCoordinates + ","+"{lat:"+strDtls[i][4]+",lng:"+strDtls[i][5]+"}";
		strCoordinates=strCoordinates+"]";	
	}
	System.out.println("DataSize:"+intDataSize);   
        }
        catch(Exception e){}
} 
    %>
    <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>

<script>
function initMap() 
{
	var srcIcon="/RailSAHAY/resources/images/src.png";
	var currentIcon="/RailSAHAY/resources/images/greenicon.png";	
	var destinationIcon="/RailSAHAY/resources/images/dstn.png";
	
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:6,
        gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divSpecLocn"),mapProp);
	<%if(strDtls != null){%>
	<%for(int i=0;i<strDtls.length;i++)
	{%>
		if(<%=i%>==0)
		{
                    var image = {
		    url: srcIcon,
		    size: new google.maps.Size(30, 30)
		  };
		}
		else if(<%=i%> == <%=lastcount%>)
		{
                    var image = {
		    url: destinationIcon,
		    size: new google.maps.Size(30, 30)
		  };
		}
		else
		{
			var image = {
		    url: currentIcon,
		    size: new google.maps.Size(15, 15)
		  };
		}
		
		marker<%=i%> = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: <%=strDtls[i][4]%>, lng:<%=strDtls[i][5]%>},
		title: '<%=strDtls[i][1]%> (<%=strDtls[i][0]%>) /<%=strDtls[i][3]%>/<%=strDtls[i][2]%>',		
		icon: image
		});
		var contentString<%=i%> = '<%=strDtls[i][0]%> (<%=strDtls[i][1]%>) /<%=strDtls[i][3]%>/<%=strDtls[i][2]%>';		
		var infowindow<%=i%>  	= new google.maps.InfoWindow({content: contentString<%=i%> });

	         marker<%=i%>.addListener('click', function() {
				<%for(int j=0;j<strDtls.length;j++)
				{%>
			    	infowindow<%=j%>.open( null, null ); /* To remove any opened info window */
			    <%}%>
	         	infowindow<%=i%>.open(map, marker<%=i%>);
 			 });
	<%}%>
	
	/* To remove any opened info window start*/
	google.maps.event.addListener( map, 'click', function() { 
		<%for(int j=0;j<strDtls.length;j++)
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
	map.setCenter(new google.maps.LatLng(<%=strDtls[0][4]%>, <%=strDtls[0][5]%>));
	<%}%>
}
        </script>
<html>
<body>
<div class="row">
	<div id="divSpecLocn" style="width:100%;height:100vh;z-index:10;position:fixed;top:0;left:0;">
      <%if(strDtls == null){%>
     <label>Route Not Found</label>
	<%}%>
    </div>
</div>
</body>
</html>