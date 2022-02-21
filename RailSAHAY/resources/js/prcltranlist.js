var statstr='';
var dsttstr='';
var dvsnstr='';
var zonestr='';

for (var i=0; i < aState.length;++i){
statstr+= '<option value="'+aState[i]+'" />'; // Helplist for States
}
var dsttstr='';
for (var i=0; i < aDistrict.length;++i){
dsttstr+= '<option value="'+aDistrict[i]+'" />'; // Helplist for Districts
}
var dvsnstr='';
for (var i=0; i < aDvsn.length;++i){
dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
}
var zonestr='';
for (var i=0; i < aZone.length;++i){
zonestr+= '<option value="'+aZone[i]+'" />'; // Helplist for Zones
}

$(document).ready(function(){
	$("#fromFlag").change(function() {
		var fromflag=$("#fromFlag").val();
		var my_list=document.getElementById("locnlistfrom");
		switch(fromflag)
		{
			case 'ST':
				my_list.innerHTML = statstr;
				break;
			case 'DS':
				my_list.innerHTML = dsttstr;
				break;
			case 'Z':
				my_list.innerHTML = zonestr;
				break;
			case 'D':
				my_list.innerHTML = dvsnstr;
				break;
			case 'S':
				var sttnstr='';
				for (var i=0; i < aSttn.length;++i){
					sttnstr+= '<option value="'+aSttn[i]+'" />'; 
				}
				my_list.innerHTML = sttnstr;
				break;
		}
	});	
	$("#crntFlag").change(function() {
		var crntflag=$("#crntFlag").val();
		var my_list=document.getElementById("crntlocnlist");
		switch(crntflag)
		{
			case 'ST':
				my_list.innerHTML = statstr;
				break;
			case 'DS':
				my_list.innerHTML = dsttstr;
				break;
			case 'Z':
				my_list.innerHTML = zonestr;
				break;
			case 'D':
				my_list.innerHTML = dvsnstr;
				break;
			case 'S':
				var sttnstr='';
				for (var i=0; i < aSttn.length;++i){
					sttnstr+= '<option value="'+aSttn[i]+'" />'; 
				}
				my_list.innerHTML = sttnstr;
				break;
		}
	});
	$("#toFlag").change(function() {
		var toflag=$("#toFlag").val();
		var my_list=document.getElementById("locnlistto");
		switch(toflag)
		{
			case 'ST':
				my_list.innerHTML = statstr;
				break;
			case 'DS':
				my_list.innerHTML = dsttstr;
				break;
			case 'Z':
				my_list.innerHTML = zonestr;
				break;
			case 'D':
				my_list.innerHTML = dvsnstr;
				break;
			case 'S':
				var sttnstr='';
				for (var i=0; i < aSttn.length;++i){
					sttnstr+= '<option value="'+aSttn[i]+'" />'; 
				}
				my_list.innerHTML = sttnstr;
				break;
		}
	});
});

function initMap()
{
	/*do nothing*/
}

var infowindow1=new Array(500);

function plotMap() 
{	
        var mapLIcon="/RailSAHAY/resources/images/mapLoadedTran.png";
	var mapEIcon="/RailSAHAY/resources/images/mapEmptyTran.png";
	var crntIcon="/RailSAHAY/resources/images/crntflag.png";
	var locnflag=$(".locnflag").attr("locn");
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:5,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divPrclTranPlot"),mapProp);

	var crntImage = {
		    url: crntIcon,
		    size: new google.maps.Size(21, 30)
		  };
                  
                      var htmlstr='';
                	var marker1 =new Array(PrclSttnList.length);
                	var contentString1 =new Array(PrclSttnList.length);

		for(var r=0;r<PrclSttnList.length;r++){
		    var image;
                    htmlstr="";
		if(PrclTranList[r][4].indexOf("LOADED")>0)
		{
			
		   image = {
		    url: mapLIcon,
		    size: new google.maps.Size(16, 21)
		  };
		}
		else
		{
			
		   image = {
		    url: mapEIcon,
		    size: new google.maps.Size(16, 21)
		  };

		}
                    
		var custom_title=r+"/"+PrclSttnList[r][0];
                marker1[r] = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: PrclSttnList[r][3]*1, lng:PrclSttnList[r][4]*1},
		title:  custom_title,		
		icon: image
		});
                var sttn=PrclSttnList[r][5];	
		var htmlstr='';	
                htmlstr+='<div class="w3-card-51">';
		htmlstr+='<p class="popup-sttn">'+PrclSttnList[r][0]+' ('+PrclSttnList[r][1]+'/'+PrclSttnList[r][2]+')';
		htmlstr+='<table class="table table-striped table-bordered popup-table"><tr><th>Train</th><th>From-To</th><th>Last Reported</th><th>ETA at Destination</th></tr>';
		for(var i=0;i<PrclTranList.length;i++)
		{
			
			if(PrclTranList[i][18]==sttn)
			{
				htmlstr+='<tr><td class="tran" onclick="openPsgrRoute(\''+PrclTranList[i][0]+'\',\''+PrclTranList[i][2]+'\',\''+PrclTranList[i][11]+'\');">'+PrclTranList[i][0]+' ('+PrclTranList[i][2]+')</td><td>'+PrclTranList[i][6]+'&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;'+PrclTranList[i][13]+'</td><td>'+PrclTranList[i][19]+' ['+PrclTranList[i][26]+'] '+PrclTranList[i][27]+'</td><td>'+PrclTranList[i][29]+'</td></tr>';			
			}
		}
                htmlstr+='</table></div>';
                
		 contentString1[r] =  htmlstr;		
		 infowindow1[r]  	= new google.maps.InfoWindow({content: contentString1[r] });
	         marker1[r].addListener('click', function() {	
			for(var s=0;s<PrclSttnList.length;s++){
			    	infowindow1[s].close(); 
	                }

			var titl=this.getTitle();
			var indx=parseInt(titl.substring(0,titl.indexOf("/")));
	         	infowindow1[indx].open(map, marker1[indx]);
 			 });
                }
	google.maps.event.addListener( map, 'click', function() { 
		for(var s=0;s<PrclSttnList.length;s++){
	    	infowindow1[s].close(); 
                }
	});
	map.setCenter(new google.maps.LatLng(PrclSttnList[0][3]*1, PrclSttnList[0][4]*1));
}
