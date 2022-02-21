


function initMapL() 
{
	var infowindow1=new Array(500);
       //alert(latList.length);
	
	var locnflag=$(".locnflag").attr("locn");
	console.log("Location Type:"+locnflag+", User Coordinates:"+userLat+","+userLng);
        var mapIcon="/RailSAHAY/resources/images/mapnew3.png";
	var currentIcon="/RailSAHAY/resources/images/crntflag.png";
	$("#mapHeader").html("Available Locations");
	
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:7,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divSpecLocn"),mapProp);
	if(locnflag=="NM")
	{
		const myCircle = new google.maps.Circle({
      		strokeColor: "#FF0000",
		strokeOpacity: 0.6,
      		strokeWeight: 2,
      		fillColor: "#FF0000",
      		fillOpacity: 0.20,
      		map,
      		center: { lat: userLat, lng: userLng },
      		radius: 50000
    		});
		$("#mapHeader").html("Locations Available <strong>Within 50 Kms</strong> of Radius");
	}
                  
                        var htmlstr='';

                var marker1 =new Array(latList.length);
                var contentString1 =new Array(latList.length);
                /*infowindow1 =new Array(latList.length);*/
                
                console.log("in Init map"+lngList.length);
                
		for(var r=0;r<=latList.length;r++){
                
                if(r==0){
                var image = {
		    url: currentIcon,
		    size: new google.maps.Size(21, 30)
		  };
                }else{
                   var image = {
		    url: mapIcon,
		    size: new google.maps.Size(16, 21)
		  }; 
                }
                    htmlstr="";
                    
		//var custom_title=r+"/"+locList[r];
               // var fctylist=fcltyList[r].split(",");
               if(r==0){
                marker1[r] = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: userLat*1, lng:userLng*1},
		title:  '0/MY LOCATION',		
		icon: image
		});
                
                contentString1[r] =  'MY LOCATION';		
		 
                
               }else{
	               
	               console.log("inside initMap "+lngList[r-1]);
               var custom_title=r+"/"+locList[r-1];
                    marker1[r] = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: latList[r-1]*1, lng:lngList[r-1]*1},
		title:  custom_title,		
		icon: image
		});
               
                        htmlstr+='<div class="w3-card-5" style="cursor:default;"><table class="styletable">';
			htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+locList[r-1]+' ('+sttnN[r-1]+')</p></th></tr>';
			htmlstr+='<tr><td class="lbl">Division/Zone</td><td class="val">('+dvsnList[r-1]+')</td></tr>';
			htmlstr+='<tr><td class="lbl" sttn="\''+locList[r-1]+'\'">Logistics Facilities</td><td class="val"  sttn="\''+locList[r-1]+'\'">';
			htmlstr+='<table class="tblFcty" sttn="\''+locList[r-1]+'\'"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+agcontL[r-1];
			htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+trckcontL[r-1]+'</span></td>';
			htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+whcontL[r-1]+'</span></td>';
			htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+labcontL[r-1]+'</span></td></tr></table></td></tr></table></div>';
			
		 contentString1[r] =  htmlstr;
                }
		 infowindow1[r]  	= new google.maps.InfoWindow({content: contentString1[r] });
          	//console.log(r+"  "+infowindow1[r]);
	         marker1[r].addListener('click', function() {	
			for(var s=0;s<latList.length;s++){
			    	infowindow1[s].close(); 
	                }

			var titl=this.getTitle();
			var indx=parseInt(titl.substring(0,titl.indexOf("/")));
	         	infowindow1[indx].open(map, marker1[indx]);
 			 });
                }
	/* To remove any opened info window start*/
	google.maps.event.addListener( map, 'click', function() { 
		for(var s=0;s<latList.length;s++){
	    	infowindow1[s].close(); 
                }
	});
	/* To remove any opened info window end*/
	map.setCenter(new google.maps.LatLng(latList[0]*1, lngList[0]*1));
}	