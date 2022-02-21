<%@ include file="/pages/GG_Header.jspf" %>  
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FIND A STATION","/RailSAHAY/pages/SttnList.jsp",UserDevice,Browser);
%>
<script>
$(document).ready(function() {

//document.getElementById('mapIm').style.display="none";
$("#mapIm").hide();
});
</script>
<style>
span.lbl
{
	font-size:12px;
	color:#4d4d4d;
	margin-right:5px;
}
span.val
{
	font-size:12px;
	color:#000;
	font-weight:600;
}
</style>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
 <nav class="navbar navbar-expand-sm bg-origprimary navbar-light">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("FIND A STATION")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	      
  <input type="hidden" name="toPage1" id="toPage1" value="SttnList" />
	          <table><tr><td><input class="form-control mr-sm-2 inptcap" id="txtSttn" name="txtSttn" type="text" placeholder='<%=E.EH("Code/Name")%>' onchange="maphide()" list="locnlist"><datalist id="locnlist"></datalist>
			 
                         </td><td><button class="btn btn-data" onclick="event.preventDefault();getData();"><%=E.EH("Search")%></button>&nbsp;&nbsp;&nbsp;&nbsp;
                         </td><td><span id="mapIm" class="float-right"> <img src="/RailSAHAY/resources/images/crntflag.png" onclick="initMap(); " data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor='pointer';" alt='<%=E.EH("View on Map")%>' title='<%=E.EH("View on Map")%>'  ><!--i class="fas fa-map-marker-alt" onclick="initMap(); " data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor='pointer';" alt="View on Map" title="View on Map"  ></i--></span></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
          <div class="col-lg-12 ml-1">
		<div id="divTrmlList" class="trmllistdata"></div>
      </div>
      </div>

 <div class="modal fade" id="fctyModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="fctytrml"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
		<div id="divTrmlFcty" style="width:100%;height:400px;overflow-y:scroll;">
		</div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>

 <div class="modal fade" id="mapModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="mapHdr"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSpecLocn" style="width:100%;height:70vh;z-index:10;position:fixed;top:0;left:0;"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>
<script>
var infowindow1=new Array(500);
</script>
<script>
function initMap() 
{
	
        var mapIcon="/RailSAHAY/resources/images/greenicon.png";
	console.log("inside initMap again");

	
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:6,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divSpecLocn"),mapProp);
	var image = {
		    url: mapIcon,
		    size: new google.maps.Size(15, 15)
		  };
                  
                        var htmlstr='';

                var marker1 =new Array(latList.length);
                var contentString1 =new Array(latList.length);
                /*infowindow1 =new Array(latList.length);*/
                
		for(var r=0;r<latList.length;r++){
                    htmlstr="";
                    
		var custom_title=r+"/"+locList[r];
                var fctylist=fcltyList[r].split(",");
                marker1[r] = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: latList[r]*1, lng:lngList[r]*1},
		title:  custom_title,		
		icon: image
		});
                
                htmlstr+='<div class="w3-card-5"><table class="styletable">';
                htmlstr+='<tr><th colspan="2"><p class="sttnhead"><i class="fas fa-caret-right" ></i>&nbsp;'+locList[r]+'</p></th></tr>';
                htmlstr+='<tr><td class="lbl">Division</td><td class="val">'+dvsnList[r]+'</td></tr>';
                htmlstr+='<tr><td class="lbl" sttn="\''+locList[r]+'\'">Logistics</td><td class="val" sttn="\''+locList[r]+'\'">';
                htmlstr+='<table class="tblFcty" ><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span><span class="smryval">'+fctylist[0];
                htmlstr+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span><span class="smryval">'+fctylist[1]+'</span></td>';
                htmlstr+='<td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span><span class="smryval">'+fctylist[2]+'</span></td>';
                htmlstr+='<td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span><span class="smryval">'+fctylist[3]+'</span></td></tr></table></td></tr></table></div>';
               // htmlstr+='<tr><td colspan="2" class="val"><a href="javascript:void(0)" class="card-link1 showtrmlprof" data-toggle="collapse" data-target="#divTrmlProf'+i+'"  id="showTrmlProf'+i+'" onclick="showSttnProf(\''+sttncode+'\','+i+');">Show More..</a><a href="javascript:void(0)" class="card-link1 hidetrmlprof" data-toggle="collapse" id="hideTrmlProf'+i+'" data-target="#divTrmlProf'+i+'" onclick="hideSpecTrmlProf('+i+');">Show Less..</a></td></tr></table>';
                //htmlstr+='<div id="divTrmlProf'+i+'" class="collapse"></div></div>';
                
		 contentString1[r] =  htmlstr;		
		 infowindow1[r]  	= new google.maps.InfoWindow({content: contentString1[r] });
          	console.log(r+"  "+infowindow1[r]);
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

function initMapS(lat,lan,sttn) 
{
	
        var mapIcon="/RailSAHAY/resources/images/greenicon.png";
	console.log("inside initMaps");
	var mapProp= 
	{
	    center:new google.maps.LatLng(28.512148,77.294226),
	    zoom:6,
            gestureHandling: 'greedy'
	};
	var map=new google.maps.Map(document.getElementById("divSpecLocn"),mapProp);
	var image = {
		    url: mapIcon,
		    size: new google.maps.Size(15, 15)
		  };
                    
                
               var marker1 = new google.maps.Marker({
		map: map,
		image: image,
		animation: google.maps.Animation.DROP,
		position: {lat: lat*1, lng:lan*1},
		title:  sttn,		
		icon: image
		});
		var contentString1 =  sttn;		
		var infowindow1  	= new google.maps.InfoWindow({content: contentString1 });
           
	         marker1.addListener('click', function() {	
	         	infowindow1.open(map, marker1);
 			 });
	
	/* To remove any opened info window start*/
	google.maps.event.addListener( map, 'click', function() { 
		
	    	infowindow1.open( null, null ); 
	});
	/* To remove any opened info window end*/
	map.setCenter(new google.maps.LatLng(lat*1, lan*1));
}
</script>  
 
<script>

var str='';
            for (var i=0; i < aSttn.length;++i){
            str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("locnlist");
            my_list.innerHTML = str;
            function getData()
            {
	            var sttncode='';
	            var sttndesc=$("#txtSttn").val();
				if(sttndesc.indexOf("-")>-1)
				{
					var sttn=sttndesc.substring(sttndesc.lastIndexOf("-")+1);
					sttncode=(sttn.substring(0,sttn.lastIndexOf("("))).trim();
				}	
				else
				{
					sttncode=sttndesc.toUpperCase();
				}
				fetchTrmlList(sttncode);
                               // document.getElementById('mapIm').style.display="block";
                               $("#mapIm").show();
            }   
            
           
            
</script> 
<script>
 function maphide(){
               // alert("lat: "+latLngList[0][0]+", lng:"+latLngList[1][0]+"lat1: "+latList[0]+", lng1:"+lngist[0]+", length:"+lngist.length);
                 $("#mapIm").hide();
            }
            </script>
<%@ include file="/pages/GG_Footer.jspf" %>