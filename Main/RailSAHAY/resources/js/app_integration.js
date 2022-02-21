var DrishtiStats=[];
function fetchDrishtiStats()
{
	$("#divCmdtStats").hide();
	var myurl="/RailSAHAY/GG_CmdtViewJson?Help=DRISTI_DATA";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	async : true,
	success : function(data) {
	var obj= data;
	var stts=obj.Stts;
	var erormesg=obj.ErorMesg;
	if(stts=="F")
 	{
		alert("Not able to connect to Server:"+erormesg);
		return false;
	}
	if(obj.DristiData.length==0)
	{
		return false;
	}
	for(var i=0;i<obj.DristiData.length;i++)
	{
		DrishtiStats[i]=new Array(10);
		switch(obj.DristiData[i].Cmdt)
		{
			case 'CEMENT AND CLINKER':
				DrishtiStats[i][0]='CEMT';
				break;
			case 'CHEMICAL MANURES':
				DrishtiStats[i][0]='CHEM';
				break;
			case 'COAL AND COKE':
				DrishtiStats[i][0]='COAL';
				break;
			case 'CONTAINER':
				DrishtiStats[i][0]='CONT';
				break;
			case 'FOODGRAINS, FLOURS AND PULSES':
				DrishtiStats[i][0]='FG';
				break;
			case 'IRON OR STEEL':
				DrishtiStats[i][0]='IS';
				break;
			case 'MINERALS AND ORES':
				DrishtiStats[i][0]='MINO';
				break;
			case 'PETROLEUM PRODUCTS AND GASES':
				DrishtiStats[i][0]='POL';
				break;
			case 'OTHERS*':
				DrishtiStats[i][0]='OTHR';
				break;
		}
		DrishtiStats[i][1]=obj.DristiData[i].Cmdt;
		DrishtiStats[i][2]=obj.DristiData[i].LdngTngeDlyC;
		DrishtiStats[i][3]=obj.DristiData[i].LdngTngeDlyP;
		DrishtiStats[i][4]=obj.DristiData[i].LdngTngeWklyC;
		DrishtiStats[i][5]=obj.DristiData[i].LdngTngeWklyP;
		DrishtiStats[i][6]=obj.DristiData[i].LdngTngeMnthC;
		DrishtiStats[i][7]=obj.DristiData[i].LdngTngeMnthP;
		DrishtiStats[i][8]=obj.DristiData[i].LdngTngeYrC;
		DrishtiStats[i][9]=obj.DristiData[i].LdngTngeYrP;

		if (DrishtiStats[i][2].charAt(0)==".")
		{
		DrishtiStats[i][2] ="0"+DrishtiStats[i][2];
		}

		if (DrishtiStats[i][3].charAt(0)==".")
		{
		 DrishtiStats[i][3] ="0"+DrishtiStats[i][3];
		}

		if (DrishtiStats[i][4].charAt(0)==".")
		{
		 DrishtiStats[i][4] ="0"+DrishtiStats[i][4];
		}

		if (DrishtiStats[i][5].charAt(0)==".")
		{
		 DrishtiStats[i][5] ="0"+DrishtiStats[i][5];
		}

		if (DrishtiStats[i][6].charAt(0)==".")
		{
		 DrishtiStats[i][6] ="0"+DrishtiStats[i][6];
		}

		if (DrishtiStats[i][7].charAt(0)==".")
		{
		 DrishtiStats[i][7] ="0"+DrishtiStats[i][7];
		}

		if (DrishtiStats[i][8].charAt(0)==".")
		{
		 DrishtiStats[i][8] ="0"+DrishtiStats[i][8];
		}

		if (DrishtiStats[i][9].charAt(0)==".")
		{
		 DrishtiStats[i][9] ="0"+DrishtiStats[i][9];
		}




		if(DrishtiStats[i][0]==GG_MainCmdtCode)
		{
			$("#divCmdtStats").show();
			$("#spnDlyTngeC").html(DrishtiStats[i][2]);
			$("#spnDlyTngeP").html(DrishtiStats[i][3]);
			$("#spnWkTngeC").html(DrishtiStats[i][4]);
			$("#spnWkTngeP").html(DrishtiStats[i][5]);
			$("#spnMnthTngeC").html(DrishtiStats[i][6]);
			$("#spnMnthTngeP").html(DrishtiStats[i][7]);
			$("#spnYearTngeC").html(DrishtiStats[i][8]);
			$("#spnYearTngeP").html(DrishtiStats[i][9]);
		}
	}
	}
});
}
var PrclTranList=[];
var PrclSttnList=[];
function fetchNTESPrclData()
{
	var locnfrom="";
	var locnto="";
	var fromflag="";
	var toflag="";
	var datefrom="";
	var dateto="";

	$("#divPrclTranList").html('<div class="spinner-border text-danger"></div>');

	var myurl="https://enquiry.indianrail.gov.in/ntessrvc/TrainService?action=ParcelTrainsPosition";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data:{"fromStation":locnfrom,"toStation":locnto,"fromDate":datefrom,"toDate":dateto,"levelFrom":fromflag,"levelTo":toflag},
	async : true,
	success : function(data) {
	var obj= data;
	var callflag=obj.restServiceMessage.serviceCallFlag;
	var dataflag=obj.restServiceMessage.serviceDataFoundFlag;
	var erormesg=obj.restServiceMessage.serviceMessage;
	if(callflag=="N")
 	{
		custPrompt(erormesg);
		return false;
	}
	if(dataflag=="N")
	{
		noDataFound();
		return false;
	}
	for(var i=0;i<obj.vTrainList.length;i++)
	{
		PrclTranList[i]=new Array(28);
		PrclSttnList[i]=new Array(3);

		PrclTranList[i][0]=obj.vTrainList[i].trainNo;
		PrclTranList[i][1]=obj.vTrainList[i].trainName;
		PrclTranList[i][2]=obj.vTrainList[i].trainSource;
		PrclTranList[i][3]=obj.vTrainList[i].trainSourceName;
		PrclTranList[i][4]=obj.vTrainList[i].trainDestination;
		PrclTranList[i][5]=obj.vTrainList[i].trainDestinationName;
		PrclTranList[i][6]=obj.vTrainList[i].trainType;
		PrclTranList[i][7]=obj.vTrainList[i].trainTypeDesc;
		PrclTranList[i][8]=obj.vTrainList[i].runDays;
		PrclTranList[i][9]=obj.vTrainList[i].startDate;
		PrclTranList[i][10]=obj.vTrainList[i].gauge;
		PrclTranList[i][11]=obj.vTrainList[i].depTimeSource;
		PrclTranList[i][12]=obj.vTrainList[i].arrTimeDestination;
		PrclTranList[i][13]=obj.vTrainList[i].cancelFrom;
		PrclTranList[i][14]=obj.vTrainList[i].cancelFromName;
		PrclTranList[i][15]=obj.vTrainList[i].cancelTo;
		PrclTranList[i][16]=obj.vTrainList[i].cancelToName;
		PrclTranList[i][17]=obj.vTrainList[i].cancelFromSr;
		PrclTranList[i][18]=obj.vTrainList[i].cancelToSr;
		PrclTranList[i][19]=obj.vTrainList[i].delaySource;
		PrclTranList[i][20]=obj.vTrainList[i].EXCEPTION_TYPE;
		PrclTranList[i][21]=obj.vTrainList[i].validFrom;
		PrclTranList[i][22]=obj.vTrainList[i].validTo;
		PrclTranList[i][23]=obj.vTrainList[i].ETA;
		PrclTranList[i][24]=obj.vTrainList[i].ETD;
		PrclTranList[i][25]=obj.vTrainList[i].Status;
		PrclTranList[i][26]=obj.vTrainList[i].LastLoc;
		PrclTranList[i][27]=obj.vTrainList[i].LastLocTime;
		PrclTranList[i][28]=obj.vTrainList[i].LastEvent;

		PrclSttnList[i][0]=obj.vTrainList[i].LastLoc;
	}
	$("#divPrclTranList").html(htmlstr);
	}
});
}
function fetchICMSPrclTrans()
{
	var fromflag="";
	var locnfrom="";
	var crntflag="";
	var crntlocn="";
	var toflag="";
	var locnto="";
	var tranno="";
	var tranname="";
	var htmlstr="";
	$("#divPrclTranList").html('<br/><br/><div class="spinner-border text-danger"></div>');

	var myurl="/RailSAHAY/PrclDataJson";
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
	data:{"Optn":"PRCL_TRANLIST","FromFlag":fromflag,"LocnFrom":locnfrom,"CrntFlag":crntflag,"CrntLocn":crntlocn,"ToFlag":toflag,"LocnTo":locnto,"TranNo":tranno,"TranName":tranname},
	async : true,
	success : function(data) {
	var obj= JSON.parse(data);
	var stts=obj.Stts;
	if(stts!="SUCCESS")
 	{
		custPrompt("Error in Fetching Parcel Data");
		return false;
	}

	htmlstr+='<div id="divPrclList">';
	htmlstr+='<table class="table table-bordered table-striped">';
	htmlstr+='<thead><tr><th class="pPrclHead"></th></tr></thead>';
	htmlstr+='<tbody>';
	var ldcont=0;
	var emcont=0;
	for(var i=0;i<obj.PrclTranList.length;i++)
	{
		PrclTranList[i]=new Array(31);
		PrclSttnList[i]=new Array(6);
		PrclTranList[i][0]=obj.PrclTranList[i].TranNo;
		PrclTranList[i][1]=obj.PrclTranList[i].TranType;
		PrclTranList[i][2]=obj.PrclTranList[i].TranName;
		PrclTranList[i][3]=obj.PrclTranList[i].SubType;
		PrclTranList[i][4]=obj.PrclTranList[i].SubTypeDesc;
		PrclTranList[i][5]=obj.PrclTranList[i].From;
		PrclTranList[i][6]=obj.PrclTranList[i].FromName;
		PrclTranList[i][7]=obj.PrclTranList[i].FromDvsn;
		PrclTranList[i][8]=obj.PrclTranList[i].FromZone;
		PrclTranList[i][9]=obj.PrclTranList[i].FromDstt;
		PrclTranList[i][10]=obj.PrclTranList[i].FromStat;
		PrclTranList[i][11]=obj.PrclTranList[i].StrtTime;
		PrclTranList[i][12]=obj.PrclTranList[i].To;
		PrclTranList[i][13]=obj.PrclTranList[i].ToName;
		PrclTranList[i][14]=obj.PrclTranList[i].ToDvsn;
		PrclTranList[i][15]=obj.PrclTranList[i].ToZone;
		PrclTranList[i][16]=obj.PrclTranList[i].ToDstt;
		PrclTranList[i][17]=obj.PrclTranList[i].ToStat;
		PrclTranList[i][18]=obj.PrclTranList[i].Crnt;
		PrclTranList[i][19]=obj.PrclTranList[i].CrntName;
		PrclTranList[i][20]=obj.PrclTranList[i].CrntDvsn;
		PrclTranList[i][21]=obj.PrclTranList[i].CrntZone;
		PrclTranList[i][22]=obj.PrclTranList[i].CrntDstt;
		PrclTranList[i][23]=obj.PrclTranList[i].CrntStat;
		PrclTranList[i][24]=obj.PrclTranList[i].Lat;
		PrclTranList[i][25]=obj.PrclTranList[i].Lng;
		PrclTranList[i][26]=obj.PrclTranList[i].Stts;
		PrclTranList[i][27]=obj.PrclTranList[i].UpdtTime;
		PrclTranList[i][28]=obj.PrclTranList[i].ActlTime;
		PrclTranList[i][29]=obj.PrclTranList[i].ETA;
		PrclTranList[i][30]=obj.PrclTranList[i].Dly;
		PrclTranList[i][31]=obj.PrclTranList[i].PtTranCont;

		PrclSttnList[i][0]=obj.PrclTranList[i].CrntName;
		PrclSttnList[i][1]=obj.PrclTranList[i].CrntDstt;
		PrclSttnList[i][2]=obj.PrclTranList[i].CrntStat;
		PrclSttnList[i][3]=obj.PrclTranList[i].Lat;
		PrclSttnList[i][4]=obj.PrclTranList[i].Lng;
		PrclSttnList[i][5]=obj.PrclTranList[i].Crnt;


		var crntstts='';
		var ldclass='';
		if(PrclTranList[i][4].indexOf("LOADED")>0)
		{
			ldclass="tran-loaded";
			ldcont++;
		}
		else
		{
			ldclass="tran-empty";
			emcont++;
		}

		htmlstr+='<tr><td>';
		htmlstr+='<div class="'+ldclass+'" style="max-height:100px;" onmouseover="showPoint('+PrclSttnList[i][4]+','+PrclSttnList[i][3]+');" onclick="gotoPoint('+PrclSttnList[i][4]+','+PrclSttnList[i][3]+');">';
		htmlstr+='<p class="pPrclTran" onmouseover="showPoint(75.67360467,21.02316283);" onclick="openPsgrRoute(\''+PrclTranList[i][0]+'\',\''+PrclTranList[i][2]+'\',\''+PrclTranList[i][11]+'\');">'+PrclTranList[i][0]+' ('+PrclTranList[i][2]+')  <span class="badge badge-danger float-right" style="font-size:11px;padding:4px;">Delay: '+PrclTranList[i][30]+' Mi </span> </p>';
		htmlstr+='<p class="pTranStts">'+PrclTranList[i][6]+'&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;<span class="crnt-stts">'+PrclTranList[i][19]+' ['+PrclTranList[i][26]+'] '+PrclTranList[i][27]+'</span>&nbsp;<i class="fas fa-long-arrow-alt-right"></i>&nbsp;'+PrclTranList[i][13]+'</p>';
		htmlstr+='</div>';
		htmlstr+='</td></tr>';
	}
	htmlstr+='</tbody></table></div>';
	$("#divPrclTranList").html(htmlstr);
	var gridhead=`PARCEL EXP (${obj.PrclTranList.length}=${ldcont} LOADED + ${emcont} EMPTY)
<div id="divColrInd" align="left"><span class="mr-1"><i class="fas fa-circle text-success"></i> Loaded</span>
	                      <span class="mr-1"> <i class="fas fa-circle text-warning"></i> Empty</span></div>
`;
	$(".pPrclHead").html(gridhead);
	plotMap();
	}
});
}
function openPsgrRoute(tranno,trnname,strtdate)
		{	
		   var htmlRout="";
   		  $('#tranRoutModal').modal('show');
		  $("#divTranRout").html('<br/><br/><div class="spinner-border text-danger"></div>');
		   $("#pPrclHedr").html(trnname+" (Start:"+strtdate+")");	
		   $("#divPrclOcpn").html("");	
		   $("#divPrclCnst").html("");
		   $("#divTranRout").show();
		htmlRout=`<div class="row d-flex justify-content-center">
		    <div class="col-lg-12 col-sm-12">
		        <div class="main-card mb-1 card">
		            <div class="card-body">
		                <div class="vertical-timeline vertical-timeline--animate vertical-timeline--one-column">`;

		   var myurl="/RailSAHAY/PrclDataJson?Optn=PSGR_TRAN_PPLN&TranNo="+tranno+"&StrtDate="+strtdate;
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
					   if(arrdely=="")
						arrdely="0";
					   if(depdely=="")
						depdely="0";

					   if(Number(depdely)<0)
						depdely="0";
					   if(Number(arrdely)<0)
						arrdely="0";


					  var sttnclss='';
					  
					  if((arrdely!="0") || (depdely!="0"))
						sttnclss="badge-danger";
					  else	
					  {
					        if((actlarr=="") && (actldep==""))
							sttnclss="badge-secondary";
						else
							sttnclss="badge-success";
					  }

					   if(i==0)
					   {
					   		sttn=sttn+" (SOURCE)";	
						htmlRout+=`<div class="vertical-timeline-item vertical-timeline-element">
                        <div> <span class="vertical-timeline-element-icon bounce-in"> <i class="badge badge-dot badge-dot-xl ${sttnclss}"> </i> </span>
                            <div class="vertical-timeline-element-content bounce-in">
                                <h4 class="timeline-title">${sttn}</h4>
                                <p class="schd-time"><span class="timeline-lbl">Schedule DEP:</span><span class="timeline-val">${schddep}</span><span class="timeline-lbl">Actual DEP:</span><span class="timeline-val">${actldep}</span>`;

				if(depdely!="0")
				htmlRout+=`<span class="badge badge-danger float-right" style="font-size:11px;padding:3px;">${depdely} Mi</span>`;
			
			 htmlRout+=`</p>
                            </div>
                        </div>
                    </div>`;
					   }
					   else if(i==(obj.data.length-1))
					   {
					   		sttn=sttn+" (DESTINATION)";
							htmlRout+=`<div class="vertical-timeline-item vertical-timeline-element">
                        <div> <span class="vertical-timeline-element-icon bounce-in"> <i class="badge badge-dot badge-dot-xl ${sttnclss}"> </i> </span>
                            <div class="vertical-timeline-element-content bounce-in">
                                <h4 class="timeline-title">${sttn}</h4>
                                <p class="schd-time"><span class="timeline-lbl">Schedule ARR:</span><span class="timeline-val">${schdarr}</span><span class="timeline-lbl">Actual ARR:</span><span class="timeline-val">${actlarr}</span>`;

				if(arrdely!="0")
				htmlRout+=`<span class="badge badge-danger float-right" style="font-size:11px;padding:3px;">${arrdely} Mi</span>`;
			
			 htmlRout+=`</p>
                            </div>
                        </div>
                    </div>`;
					   }
					else
{

					
					   var arvltimeclss="";
					   var deptimeclss="";

					htmlRout+=`<div class="vertical-timeline-item vertical-timeline-element">
                        <div> <span class="vertical-timeline-element-icon bounce-in"> <i class="badge badge-dot badge-dot-xl ${sttnclss}"> </i> </span>
                            <div class="vertical-timeline-element-content bounce-in">
                                <h4 class="timeline-title">${sttn}</h4>
                                <p class="schd-time"><span class="timeline-lbl">Schedule ARR:</span><span class="timeline-val">${schdarr}</span><span class="timeline-lbl">Schedule DEP:</span><span class="timeline-val">${schddep}</span></p>`;

				if((actlarr!="") && (actldep!=""))
				{
				htmlRout+=`<p class="schd-time"><span class="timeline-lbl">Actual ARR:</span><span class="timeline-val">${actlarr}</span>`;
				
				if(arrdely!="0")
				htmlRout+=`<span class="badge badge-danger" style="font-size:11px;padding:3px;margin-left:10px;">${arrdely} Mi</span>`;

htmlRout+=`<span class="timeline-lbl">Actual DEP:</span><span class="timeline-val">${actldep}</span>`;
				
				if(depdely!="0")
				htmlRout+=`<span class="badge badge-danger" style="font-size:11px;padding:3px;margin-left:10px;">${depdely} Mi</span>`;


htmlRout+=`</p>`;
}
htmlRout+=`
                            </div>
                        </div>
                    </div>`;
}
		            }

                    
htmlRout+=`</div>
            </div>
        </div>
    </div>
</div>`;
					$("#divTranRout").html(htmlRout);
					$('#divTranRout').scrollTop(0);
				}
		          });
		}