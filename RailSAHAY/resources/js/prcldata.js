function getPrclTranList()
{
$("#divPrclTranList").html("");
	var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th>SR.</th><th>TRAIN NO.</th><th>NAME</th><th>FROM</th><th>DEP</th><th>TO</th><th>ARR</th><th>TRAVEL TIME (HRS:MIN)</th><th>DAYS OF RUN</th><th>START DATE</th></tr>';
	var htmltranlist='';
	var qry="SATSTRAINLIST";//"NTESTRNLIST";
	var locnfromflag=$('#selLocnFrom').val();
	var locntoflag=$('#selLocnTo').val();
	var locnfromdesc=$('#txtLocnFrom').val();
	var locnfrom='';
	var locntodesc=$('#txtLocnTo').val();
	var locnto='';
	var datefromdesc=$('#txtDateFrom').val();
	var datetodesc=$('#txtDateTo').val();
	var date1=datefromdesc.split('-');
	var date2=datetodesc.split('-');
	var datefrom=date1[2]+'-'+date1[1]+'-'+date1[0];
	var dateto=date2[2]+'-'+date2[1]+'-'+date2[0];

	if(locnfromdesc.indexOf('-')>=-1)
	{
		switch(locnfromflag)
		{
			case 'S':
				var sttn=locnfromdesc.substring(locnfromdesc.lastIndexOf("-")+1);
				locnfrom=(sttn.substring(0,sttn.indexOf("("))).trim();
			case 'D':
				locnfrom=(locnfromdesc.substring(0,locnfromdesc.indexOf("-"))).trim();

			case 'Z':
				locnfrom=(locnfromdesc.substring(0,locnfromdesc.indexOf("-"))).trim();

			case 'DT':
				locnfrom=(locnfromdesc.substring(locnfromdesc.indexOf("-")+1)).trim();

			case 'ST':
				locnfrom=(locnfromdesc.substring(0,locnfromdesc.indexOf("-"))).trim();
				
		}
	}
	else
	{
		locnfrom=locnfromdesc;
	}

	if(locntodesc.indexOf('-')>=-1)
	{
		switch(locntoflag)
		{
			case 'S':
				var sttn=locntodesc.substring(locntodesc.lastIndexOf("-")+1);
				locnto=(sttn.substring(0,sttn.indexOf("("))).trim();
			case 'D':
				locnto=(locntodesc.substring(0,locntodesc.indexOf("-"))).trim();

			case 'Z':
				locnto=(locntodesc.substring(0,locntodesc.indexOf("-"))).trim();

			case 'DT':
				locnto=(locntodesc.substring(locntodesc.indexOf("-")+1)).trim();

			case 'ST':
				locnto=(locntodesc.substring(0,locntodesc.indexOf("-"))).trim();
				
		}
	}
	else
	{
		locnto=locntodesc;
	}

	var myurl="/RailSAHAY/GG_PrclTranData?Qry="+qry+"&selLocnFrom="+locnfromflag+"&selLocnTo="+locntoflag+"&txtLocnFrom="+locnfrom+"&txtLocnTo="+locnto+"&txtDateFrom="+datefrom+"&txtDateTo="+dateto;
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
                console.log("Total Trains:"+obj.TrainList.length);
                
		var srvcdataflag=obj.DataAvailable;		
		var srvcmesg=obj.Remarks;
		var srvccallflag=obj.ServiceCall;
		if(srvccallflag=="SUCCESS")
		{
			if(obj.TrainList.length==0) {
                            noDataFound();
                        }else{
                        if(srvcdataflag=="Y")
			{
				for(var i=0;i<obj.TrainList.length;i++)
				{
					var src=obj.TrainList[i].ORIG_STTN;
					var dstn=obj.TrainList[i].DSTN_STTN;
					var tranname=obj.TrainList[i].TRAINNAME;
					var trannumb=obj.TrainList[i].TRAINNUMBER;
					var tranid=obj.TrainList[i].TRAINID;
					var strtdate=obj.TrainList[i].StrtDate;
					//var strtflag=obj.TrainList[i].StartFlag;
					var deptime=obj.TrainList[i].Departure_from_source;
					var trvltime=obj.TrainList[i].TravelTime;
					var arvltime=obj.TrainList[i].Arrival_at_destination;
					var daysofrun=obj.TrainList[i].DaysOfSERVICE;
					//var etd=obj.TrainList[i].ETD;
					htmlstr+='<tr><td>'+(i+1)+'</td><td><button class="btn btn-info btn-sm" onclick="getTranSchdSats(\''+tranid+'\',\''+strtdate+'\',\''+tranname+'\');">'+trannumb+'</button></td><td>'+tranname+'</td><td>'+src+'</td><td>'+deptime+'</td><td>'+dstn+'</td><td>'+arvltime+'</td><td>'+trvltime+'</td><td>'+daysofrun+'</td><td>'+strtdate+'</td></tr>';

					htmltranlist+='<div class="ptrancard" onclick="getTranSchdSats(\''+tranid+'\',\''+strtdate+'\',\''+tranname+'\');"><table style="width:100%;"><tr><td colspan="2"><p class="ptrandtls"><i class="fas fa-train"></i> &nbsp;';
					htmltranlist+=tranname+' ('+trannumb+')</p></td></tr><tr><td style="width:50%;"><p class="ptranod">'+src+' &nbsp; <i class="fas fa-long-arrow-alt-right"></i> &nbsp;';
					htmltranlist+=dstn+'</p><p class="pdor">Departs On: &nbsp; '+daysofrun+'</p></td><td style="width:50%;"><table class="tbl-prclschd"><tr><td>';
					htmltranlist+='<i class="far fa-bell"></i><br/>Departs</td><td><i class="far fa-bell"></i><br/>Arrives</td><td><i class="far fa-clock"></i><br/>Duration</td></tr>';
					htmltranlist+='<tr><td>'+deptime+'</td><td>'+arvltime+'</td><td>'+trvltime+'</td></tr></table></td></tr></table></div>';
				}
				htmlstr+='</table>';
				$("#divPrclTranList").html(htmltranlist);
			}
			else
			{
				
                                 noDataFound();
                                 //$("#divDataMesg").html("Sorry! No trains available between selected Origin-Destination Pair for the specified Period");
			}
                    }
		}
		else
		{
			$("#divDataMesg").html("Sorry! Failed to fetch Parcel Trains detail, please try after some time");

		}

	
		
		
		}
		});
}
function getTranSchd(trannumb, strtdate, trnname)
{
	var myurl="/RailSAHAY/GG_PrclTranData?Qry=TRNSCHD&TranNumb="+trannumb+"()&StrtDate="+strtdate;
	var htmlRout="";
	var startdate=strtdate.substring(strtdate.indexOf("/")+1);
	$("#pPrclHedr").html(trnname+" (Start:"+startdate+")");	
	$("#divPrclOcpn").html("");	
	$("#divPrclCnst").html("");
	$("#divTranRout").show();

	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
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
						if(actlarr=="")		
							htmlRout+='<p class="arvltime">[AR]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schdarr+'</span>';		                	
						else
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
						if(actldep!="")
				                	htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>Actual:<span class="'+deptimeclss+'">'+actldep+'</span><span class="deptimedely"></span>';
						else
							htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>';

			                }
			                htmlRout+='</div></div></article>';
			             }
		            }
					$("#divTranRout").html(htmlRout);
					$("#prclTranSchd").modal('show');
				}		});

}
function getTranSchdSats(tranid,strtdate, trnname)
{
	var myurl="/RailSAHAY/GG_PrclTranData?Qry=TRNSCHDSATS&tranid="+tranid;
	var htmlRout="";
	var startdate=strtdate.substring(strtdate.indexOf("/")+1);
	$("#pPrclHedr").html(trnname+" (Start:"+startdate+")");	
	$("#divPrclOcpn").html("");	
	$("#divPrclCnst").html("");
	$("#divTranRout").show();

	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		for(var i=0;i<obj.trainSchedule.length;i++)
					{
					   var sttn=obj.trainSchedule[i].STTNNAME+" ("+obj.trainSchedule[i].STTNCODE+")";
					   var schdarr=obj.trainSchedule[i].ARVLTIME;
					   var actlarr="";
					   var arrdely="";
					   var schddep=obj.trainSchedule[i].DPRTTIME;
					   var actldep="";
					   var depdely="";
					   var trvl=obj.trainSchedule[i].DAYOFRUN;
					   if(i==0)
					   		sttn=sttn+" (SOURCE)";	
					   if(i==(obj.trainSchedule.length-1))
					   		sttn=sttn+" (DESTINATION)";
					   var arvltimeclss="";
					   var deptimeclss="";
					   if(i==0 )
					   {
				   			htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-success"><i class="entypo-feather"></i></div>';
					   }else if(i==(obj.trainSchedule.length-1)){
                                               htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-danger"><i class="entypo-feather"></i></div>';
                                           }else{
                                               htmlRout+='<article class="timeline-entry"><div class="timeline-entry-inner"><div class="timeline-icon bg-primary"><i class="entypo-feather"></i></div>';
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
						if(actlarr=="")		
							htmlRout+='<p class="arvltime">[AR]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schdarr+'</span>';		                	
						else
							htmlRout+='<p class="arvltime">[AR]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schdarr+'</span>Actual:<span class="'+arvltimeclss+'">'+actlarr+'</span><span class="arrtimedely"></span>';		                	
					

		                	}
		                }
		                if(i<(obj.trainSchedule.length-1))
		                {
							if((depdely!="") && (depdely!="0"))
		                	{		                		                	
			                	htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>Actual:<span class="'+deptimeclss+'">'+actldep+'</span><span class="deptimedely"></span> <span class="'+deptimeclss+'" style="font-size:12px;font-weight:400;">[Delay: '+depdely+' Mins]';
			                }
			                else
			                {
						if(actldep!="")
				                	htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>Actual:<span class="'+deptimeclss+'">'+actldep+'</span><span class="deptimedely"></span>';
						else
							htmlRout+='<p class="deptime">[DP]:&nbsp;&nbsp;<span class="schdarr">Schedule:'+schddep+'</span>';

			                }
			                htmlRout+='</div></div></article>';
			             }
		            }
					$("#divTranRout").html(htmlRout);
					$("#prclTranSchd").modal('show');
				}		});

}