var GG_AnlyCtgr="T";
var zonlperfmnth=[];
var zonlperftngecy=[];
var zonlperftngely=[];
var zonlperffrgtcy=[];
var zonlperffrgtly=[];

var dvsnperfmnth=[];
var dvsnperftngecy=[];
var dvsnperftngely=[];
var dvsnperffrgtcy=[];
var dvsnperffrgtly=[];

var cmdtperf=[];
var cmdtperftngecy=[];
var cmdtperftngely=[];
var cmdtperffrgtcy=[];
var cmdtperffrgtly=[];

var cmdtsharetngecy=[];
var cmdtsharetngely=[];
var cmdtsharefrgtcy=[];
var cmdtsharefrgtly=[];

var zonelist=[];
var projtngecy=[];
var tngely=[];
var projfrgtcy=[];
var frgtly=[];

var propstatlbl=[];
var propstatval=[];

var zonlldngperf;
var cmdtldngperf;
var cmdtsharecy;
var cmdtsharely;
var dvsnldngcy;
var dvsnldngly;
var zonlproj;
var propstat;
var cmdtRailCoefChrt;
var htmlhedr='<span class="cy-ind"><i class="fa fa-bandcamp" aria-hidden="true"></i></span> Current Year (Till '+GG_MaxAnlyDate+')<span class="ly-ind"><i class="fa fa-bandcamp" aria-hidden="true"></i></span>Last Year';
var colrarr=['#ff9900','#ff6600','#339933','#cc6699','#0066ff','#9900ff','#666699','#990099','#993333','#ff6600','#996633'];

$(document).ready(function(){
	popDvsnList(GG_CrntZone,GG_CrntDvsn);
	$(".zoneboundaries").mouseenter(function()
	{
		$(this).removeClass("zoneboundaries");
		$(this).addClass("activezone");
	});
	$(".zoneboundaries").mouseleave(function()
	{
		$(this).removeClass("activezone");
		$(this).addClass("zoneboundaries");
	});
	$(".zoneboundaries").click(function()
	{
		$(".zoneboundaries").removeClass("selectedzone");
		$(this).addClass("selectedzone");
		var zone=$(this).attr("id");
		CrntAnlyLocnFlag="Z";
		CrntAnlyLocn=zone;
		$("#spnLocnHrcy").html("<a href='#' onclick='showIRData();' style='color:#b32400 !important;'>INDIAN RAILWAY</a> / "+CrntAnlyLocn);		
		popDvsnList(zone,GG_CrntDvsn);
		drawZonlAnly(CrntAnlyLocn,'T');
		drawCmdtRailCoef(zone,'');
	});
});
function showIRData() {
   drawZonlAnly('','T'); 
}
function drawZonlAnly(zone,ctgr)
{
	var seldvsn=$("#selDvsn").val();
	if((seldvsn!="") && (seldvsn!=null))
	{
		$("#tblDvsnShare").hide();
		drawDvsnAnlyStat(ctgr);
		return false;	
	}
        $("#cardZonlProj").show();
	$("#tblDvsnShare").show();
	var myurl="/RailSAHAY/AcqnPlan";
	var dvsnarr=[];
	var cmdtarr=[];
	var dvsntngecy=[];
	var dvsntngely=[];
	var dvsnfrgtcy=[];
	var dvsnfrgtly=[];
	GG_AnlyCtgr=ctgr;
	var divclss="";
	var lbl='';
	var lblCmdt='';
        if(zone=="") {           
            if(ctgr=='T')
            {
                    lbl='Month-wise Loading (MT) for IR';
                    lblCmdt='Commodity-wise Loading (MT) for IR';
                    $('#tngefrgtsmryhead').html('Tonnage (MT) Summary for IR');
            }
            if(ctgr=='F')
            {
                    lbl='Month-wise Freight (Cr) for IR'
                    lblCmdt='Commodity-wise Freight (Cr) for IR';
                    $('#tngefrgtsmryhead').html('Freight (Cr) Summary for IR');
            } 
        }
        else
        {
            if(ctgr=='T')
            {
                    lbl='Month-wise Loading (MT) for Zone: '+zone;
                    lblCmdt='Commodity-wise Loading (MT) for Zone: '+zone;
                    $('#tngefrgtsmryhead').html('Tonnage (MT) Summary for Zone: '+zone);
            }
            if(ctgr=='F')
            {
                    lbl='Month-wise Freight (Cr) for Zone: '+zone;
                    lblCmdt='Commodity-wise Freight (Cr) for Zone: '+zone;
                    $('#tngefrgtsmryhead').html('Freight (Cr) Summary for Zone: '+zone);
            }
        }
	
	$('#zonlldnghead').html(lbl+htmlhedr);
	$('#cmdtldnghead').html(lblCmdt);
	$.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'ZONL_PERF', Zone:zone},
     async : true,
     success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
		{
			
			zonlperfmnth=[];
			zonlperftngecy=[];
			zonlperftngely=[];
			zonlperffrgtcy=[];
			zonlperffrgtly=[];

			dvsnperfmnth=[];
			dvsnperftngecy=[];
			dvsnperftngely=[];
			dvsnperffrgtcy=[];
			dvsnperffrgtly=[];

			cmdtperf=[];
			cmdtperftngecy=[];
			cmdtperftngely=[];
			cmdtperffrgtcy=[];
			cmdtperffrgtly=[];

			cmdtsharetngecy=[];
			cmdtsharetngely=[];
			cmdtsharefrgtcy=[];
			cmdtsharefrgtly=[];                       
                        
			zonelist=[];
			projtngecy=[];
			tngely=[];
			projfrgtcy=[];
			frgtly=[];
                        
                        if(ctgr=='T')
                        {   
                            var num1=Number(obj.Stat1);
                            var num2=Number(obj.Stat3);
                            var num3=Number(obj.Stat5);
                            var num4=Number(obj.Stat7);

                            var largest = Math.max(num1,num2,num3,num4);
                            /*
                            if(num1 >= num2 && num1 >= num3) {
                                largest = num1;
                            }
                            else if (num2 >= num1 && num2 >= num3) {
                                largest = num2;
                            }
                            else {
                                largest = num3;
                            }
			   */
                            var lypctg=((Number(obj.Stat1)*100)/largest);
                            var cypctg=((Number(obj.Stat3)*100)/largest);
                            var cyprojpctg=((Number(obj.Stat5)*100)/largest);
		            var cytrgtpctg=0;
			    if(obj.Stat7!='')
				cytrgtpctg=((Number(obj.Stat7)*100)/largest);
                            $("#prgsLY").css("width", lypctg+"%");
                            $("#prgsRptd").css("width", cypctg+"%");
                            $("#prgsProj").css("width",cyprojpctg+"%");
                            $("#prgsTrgt").css("width",cytrgtpctg+"%");
                            if(Number(obj.Stat5)>Number(obj.Stat1)) 
                            {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-success");
                            }
                            else 
                            {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-danger");                                
                            }
                            $("#lysmry").html(obj.Stat1);
                            $("#cysmry").html(obj.Stat3);
                            $("#cyproj").html(obj.Stat5);
                            $("#cytrgt").html(obj.Stat7);
                        }
                        if(ctgr=='F')
                        {
                            var num1=Number(obj.Stat2);
                            var num2=Number(obj.Stat4);
                            var num3=Number(obj.Stat6);
                            var largest;
                            
                            if(num1 >= num2 && num1 >= num3) {
                                largest = num1;
                            }
                            else if (num2 >= num1 && num2 >= num3) {
                                largest = num2;
                            }
                            else {
                                largest = num3;
                            }
                            var lypctg=((Number(obj.Stat2)*100)/largest);
                            var cypctg=((Number(obj.Stat4)*100)/largest);
                            var cyprojpctg=((Number(obj.Stat6)*100)/largest);
                            $("#prgsLY").css("width", lypctg+"%");
                            $("#prgsRptd").css("width", cypctg+"%");
                            $("#prgsProj").css("width",cyprojpctg+"%");
                            $("#prgsTrgt").css("width","0%");
                            
                            if(Number(obj.Stat6)>Number(obj.Stat2)) {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-success");
                            }
                            else {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-danger");                                
                            }
                            $("#lysmry").html(obj.Stat2);
                            $("#cysmry").html(obj.Stat4);
                            $("#cyproj").html(obj.Stat6);
                            $("#cytrgt").html('');

                        }
                        if(zone=='') {
                            $("#tblDvsnShare").hide();
                            $("#selDvsn").hide();
                        }
			for(var i=0;i<obj.ZonlPerf.length;i++)
			{
				zonlperfmnth[i]=obj.ZonlPerf[i].Month;
				zonlperftngecy[i]=Number(obj.ZonlPerf[i].TngeCY);
				zonlperftngely[i]=Number(obj.ZonlPerf[i].TngeLY);
				zonlperffrgtcy[i]=Number(obj.ZonlPerf[i].FrgtCY);
				zonlperffrgtly[i]=Number(obj.ZonlPerf[i].FrgtLY);
			}
			if(ctgr=='T')
				drawZonlLdngPerf(zonlperfmnth,zonlperftngecy,zonlperftngely);	
			if(ctgr=='F')
				drawZonlLdngPerf(zonlperfmnth,zonlperffrgtcy,zonlperffrgtly);

                        if(zone!="")
                        {
                            $("#tblDvsnShare").show();
                            $("#selDvsn").show();
                            for(var i=0;i<obj.DvsnPerf.length;i++)
                            {
                                    dvsnarr[i]=obj.DvsnPerf[i].Dvsn;
                                    dvsntngecy[i]=Number(obj.DvsnPerf[i].TngeCY);
                                    dvsntngely[i]=Number(obj.DvsnPerf[i].TngeLY);
                                    dvsnfrgtcy[i]=Number(obj.DvsnPerf[i].FrgtCY);
                                    dvsnfrgtly[i]=Number(obj.DvsnPerf[i].FrgtLY);
			        
                            }
                            if(ctgr=='T')
                            {
                                    drawDvsnLdngPerfCY(dvsnarr,dvsntngecy);	
                                    drawDvsnLdngPerfLY(dvsnarr,dvsntngely);
                            }
                            if(ctgr=='F')
                            {
                                    drawDvsnLdngPerfCY(dvsnarr,dvsnfrgtcy);	
                                    drawDvsnLdngPerfLY(dvsnarr,dvsnfrgtly);
                            }
                        }
			for(var i=0;i<obj.CmdtPerf.length;i++)
			{
				cmdtperf[i]=obj.CmdtPerf[i].Cmdt;
				cmdtperftngecy[i]=Number(obj.CmdtPerf[i].TngeCY);
				cmdtperftngely[i]=Number(obj.CmdtPerf[i].TngeLY);
				cmdtperffrgtcy[i]=Number(obj.CmdtPerf[i].FrgtCY);
				cmdtperffrgtly[i]=Number(obj.CmdtPerf[i].FrgtLY);
			}
			if(ctgr=='T')
				drawCmdtLdngPerf(cmdtperf,cmdtperftngecy,cmdtperftngely);	
			if(ctgr=='F')
				drawCmdtLdngPerf(cmdtperf,cmdtperffrgtcy,cmdtperffrgtly);

			for(var i=0;i<obj.CmdtShare.length;i++)
			{
				cmdtarr[i]=obj.CmdtShare[i].Cmdt;
				cmdtsharetngecy[i]=Number(obj.CmdtShare[i].TngeCY);
				cmdtsharetngely[i]=Number(obj.CmdtShare[i].TngeLY);
				cmdtsharefrgtcy[i]=Number(obj.CmdtShare[i].FrgtCY);
				cmdtsharefrgtly[i]=Number(obj.CmdtShare[i].FrgtLY);
			}
			if(ctgr=='T')
			{
				drawCmdtShareCY(cmdtarr,cmdtsharetngecy);	
				drawCmdtShareLY(cmdtarr,cmdtsharetngely);
			}
			if(ctgr=='F')
			{
				drawCmdtShareCY(cmdtarr,cmdtsharefrgtcy);	
				drawCmdtShareLY(cmdtarr,cmdtsharefrgtly);
			}   
                        var cntr=0;
			for(var i=0;i<obj.ZonlProj.length;i++)
			{
                            if((zone=="") && (obj.ZonlProj[i].Dvsn==""))
                            {
				zonelist[cntr]=obj.ZonlProj[i].Zone;
				projtngecy[cntr]=Number(obj.ZonlProj[i].TngeCY);
				tngely[cntr]=Number(obj.ZonlProj[i].TngeLY);
				projfrgtcy[cntr]=Number(obj.ZonlProj[i].FrgtCY);
				frgtly[cntr]=Number(obj.ZonlProj[i].FrgtLY);
                                cntr++;
                            }
                            if((zone!="") && (obj.ZonlProj[i].Zone==zone) && (obj.ZonlProj[i].Dvsn!=""))
                            {
				zonelist[cntr]=obj.ZonlProj[i].Dvsn;
				projtngecy[cntr]=Number(obj.ZonlProj[i].TngeCY);
				tngely[cntr]=Number(obj.ZonlProj[i].TngeLY);
				projfrgtcy[cntr]=Number(obj.ZonlProj[i].FrgtCY);
				frgtly[cntr]=Number(obj.ZonlProj[i].FrgtLY);
                                cntr++;                            
                            }
			}
			if(ctgr=='T')
			{
                                drawZonlProj('T', zonelist,projtngecy,tngely);
			}
			if(ctgr=='F')
			{
				drawZonlProj('F',zonelist,projfrgtcy,frgtly);
			}
                        
                        propstatlbl[0]='Received';
                        propstatlbl[0]='Decision Taken by RB';
                        propstatlbl[0]='Forwarded to RB';
                        for(var i=0;i<obj.PropStat.length;i++)
			{
                                propstatval[i]=obj.PropStat[i].RecvCont;
                                propstatval[i]=obj.PropStat[i].RespCont;
                                propstatval[i]=obj.PropStat[i].FrwdCont;
			}
		}
	}
	});

}

function drawDvsnAnlyStat(ctgr)
{
	var myurl="/RailSAHAY/AcqnPlan";
	$("#tblDvsnShare").hide();
	var dvsnarr=[];
	var cmdtarr=[];
	var dvsntngecy=[];
	var dvsntngely=[];
	var dvsnfrgtcy=[];
	var dvsnfrgtly=[];
	var dvsn=$("#selDvsn").val();
	var divclss="";
	var lbl='';
	var lblCmdt='';
        $("#cardZonlProj").hide();	
	drawCmdtRailCoef('',dvsn);
	if(ctgr=='T')
	{
		lbl='Monthly Loading (MT) Performance for Division: '+dvsn;
		lblCmdt='Commodity-wise Loading (MT) Performance for Division: '+dvsn;
                $('#tngefrgtsmryhead').html('Tonnage (MT) Summary for Division: '+dvsn);
	}
	if(ctgr=='F')
	{
		lbl='Monthly Freight (Cr) Performance for Division: '+dvsn;
		lblCmdt='Commodity-wise Freight (Cr) Performance for Division: '+dvsn;
                $('#tngefrgtsmryhead').html('Freight (Cr) Summary for Division: '+dvsn);
	}

	$('#zonlldnghead').html(lbl); 	
	$('#cmdtldnghead').html(lblCmdt);
	$.ajax({
     url : myurl,
     type : "post",
     data: {Optn:'DVSN_PERF_STATS', Dvsn:dvsn},
     async : true,
     success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		if(stts=="S")
		{
			
			zonlperfmnth=[];
			zonlperftngecy=[];
			zonlperftngely=[];
			zonlperffrgtcy=[];
			zonlperffrgtly=[];

			dvsnperfmnth=[];
			dvsnperftngecy=[];
			dvsnperftngely=[];
			dvsnperffrgtcy=[];
			dvsnperffrgtly=[];

			cmdtperf=[];
			cmdtperftngecy=[];
			cmdtperftngely=[];
			cmdtperffrgtcy=[];
			cmdtperffrgtly=[];

			cmdtsharetngecy=[];
			cmdtsharetngely=[];
			cmdtsharefrgtcy=[];
			cmdtsharefrgtly=[];
                        
                        if(ctgr=='T')
                        {   
                            var num1=Number(obj.Stat1);
                            var num2=Number(obj.Stat3);
                            var num3=Number(obj.Stat5);
                            var num4=Number(obj.Stat7);
                            var largest = Math.max(num1,num2,num3,num4);                            
			/*
                            if(num1 >= num2 && num1 >= num3) {
                                largest = num1;
                            }
                            else if (num2 >= num1 && num2 >= num3) {
                                largest = num2;
                            }
                            else {
                                largest = num3;
                            }
			*/
                            var lypctg=((Number(obj.Stat1)*100)/largest);
                            var cypctg=((Number(obj.Stat3)*100)/largest);
                            var cyprojpctg=((Number(obj.Stat5)*100)/largest);
                            var cytrgtpctg=((Number(obj.Stat7)*100)/largest);
                            $("#prgsLY").css("width", lypctg+"%");
                            $("#prgsRptd").css("width", cypctg+"%");
                            $("#prgsProj").css("width",cyprojpctg+"%");
                            $("#prgsTrgt").css("width",cytrgtpctg+"%");
                            if(Number(obj.Stat5)>Number(obj.Stat1)) 
                            {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-success");
                            }
                            else 
                            {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-danger");                                
                            }
                            $("#lysmry").html(obj.Stat1);
                            $("#cysmry").html(obj.Stat3);
                            $("#cyproj").html(obj.Stat5);
                            $("#cytrgt").html(obj.Stat7);
                        }
                        if(ctgr=='F')
                        {
                            var num1=Number(obj.Stat2);
                            var num2=Number(obj.Stat4);
                            var num3=Number(obj.Stat6);
                            var largest;
                            
                            if(num1 >= num2 && num1 >= num3) {
                                largest = num1;
                            }
                            else if (num2 >= num1 && num2 >= num3) {
                                largest = num2;
                            }
                            else {
                                largest = num3;
                            }
                            var lypctg=((Number(obj.Stat2)*100)/largest);
                            var cypctg=((Number(obj.Stat4)*100)/largest);
                            var cyprojpctg=((Number(obj.Stat6)*100)/largest);
                            $("#prgsLY").css("width", lypctg+"%");
                            $("#prgsRptd").css("width", cypctg+"%");
                            $("#prgsProj").css("width",cyprojpctg+"%");
                            $("#prgsTrgt").css("width","0%");
                            
                            if(Number(obj.Stat6)>Number(obj.Stat2)) {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-success");
                            }
                            else {
                                $("#prgsProj").removeClass("bg-danger");
                                $("#prgsProj").removeClass("bg-secondary");
                                $("#prgsProj").addClass("bg-danger");                                
                            }
                            $("#lysmry").html(obj.Stat2);
                            $("#cysmry").html(obj.Stat4);
                            $("#cyproj").html(obj.Stat6);
                            $("#cytrgt").html("");

                        }
			for(var i=0;i<obj.DvsnPerf.length;i++)
			{
				dvsnperfmnth[i]=obj.DvsnPerf[i].Month;
				dvsnperftngecy[i]=Number(obj.DvsnPerf[i].TngeCY);
				dvsnperftngely[i]=Number(obj.DvsnPerf[i].TngeLY);
				dvsnperffrgtcy[i]=Number(obj.DvsnPerf[i].FrgtCY);
				dvsnperffrgtly[i]=Number(obj.DvsnPerf[i].FrgtLY);
			}
			if(ctgr=='T')
				drawZonlLdngPerf(dvsnperfmnth,dvsnperftngecy,dvsnperftngely);	
			if(ctgr=='F')
				drawZonlLdngPerf(dvsnperfmnth,dvsnperffrgtcy,dvsnperffrgtly);

			for(var i=0;i<obj.DvsnPerf1.length;i++)
			{
				cmdtperf[i]=obj.DvsnPerf1[i].Cmdt;
				cmdtperftngecy[i]=Number(obj.DvsnPerf1[i].TngeCY);
				cmdtperftngely[i]=Number(obj.DvsnPerf1[i].TngeLY);
				cmdtperffrgtcy[i]=Number(obj.DvsnPerf1[i].FrgtCY);
				cmdtperffrgtly[i]=Number(obj.DvsnPerf1[i].FrgtLY);
			}
			if(ctgr=='T')
				drawCmdtLdngPerf(cmdtperf,cmdtperftngecy,cmdtperftngely);	
			if(ctgr=='F')
				drawCmdtLdngPerf(cmdtperf,cmdtperffrgtcy,cmdtperffrgtly);

			for(var i=0;i<obj.CmdtShare.length;i++)
			{
				cmdtarr[i]=obj.CmdtShare[i].Cmdt;
				cmdtsharetngecy[i]=Number(obj.CmdtShare[i].TngeCY);
				cmdtsharetngely[i]=Number(obj.CmdtShare[i].TngeLY);
				cmdtsharefrgtcy[i]=Number(obj.CmdtShare[i].FrgtCY);
				cmdtsharefrgtly[i]=Number(obj.CmdtShare[i].FrgtLY);
			}
			if(ctgr=='T')
			{
				drawCmdtShareCY(cmdtarr,cmdtsharetngecy);	
				drawCmdtShareLY(cmdtarr,cmdtsharetngely);
			}
			if(ctgr=='F')
			{
				drawCmdtShareCY(dvsnarr,cmdtsharefrgtcy);	
				drawCmdtShareLY(dvsnarr,cmdtsharefrgtly);
			}
		}
	}
	});

}
$(document).ready(function()
{
	$("#btnLdng").on('click',function() {
		drawZonlAnly(CrntAnlyLocn,'T');
	});

	$("#btnFrgt").on('click',function() {
		drawZonlAnly(CrntAnlyLocn,'F');
	});
});
function drawZonlLdngPerf(lblarr,cydata,lydata)
{
	var data = {
		labels:lblarr,
		datasets: [
			{
				label: 'Current Year',
				borderColor: 'rgba(0,51,204, 1)',
				backgroundColor: 'rgba(0,51,204, 0.7)',
				borderWidth:1,
				data: cydata
			},
			{
				label: 'Last Year',
				borderColor: 'rgba(255,0,0, 1)',
				backgroundColor: 'rgba(255,0,0, 0.7)',
				borderWidth:1,
				data: lydata
			}
		]
	};

	var ctx = document.getElementById('zonlLdngPerf').getContext('2d');

  if (zonlldngperf!=undefined) {
	zonlldngperf.destroy();
  }
	zonlldngperf = new Chart(ctx, {
		type: 'bar',
		data: data,
		options: {
			barValueSpacing: 25,  
			events: false,
			tooltips: {
			    enabled: true
			},
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			hover: {
			        animationDuration: 0
			},
			animation: {
				duration: 5,
				onComplete: function () {
				    // render the value of the chart above the bar
				    var ctx = this.chart.ctx;
				    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
				    ctx.fillStyle = this.chart.config.options.defaultFontColor;
				    ctx.textAlign = 'center';
				    ctx.textBaseline = 'bottom';
				    this.data.datasets.forEach(function (dataset) {
					for (var i = 0; i < dataset.data.length; i++) {
				            var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
					    ctx.fillText(dataset.data[i], model.x, model.y - 5);
				        }
				    });
				}}
		}
	});
}
function drawZonlProj(ctgr, lblarr,cydata,lydata)
{
    if(ctgr=='T')
        $("#projhead").html("Loading Tonnage (MT) Projections"+htmlhedr);
    if(ctgr=='F')
        $("#projhead").html("Freight Revenue (Cr) Projections"+htmlhedr);
        
	var data = {
		labels:lblarr,
		datasets: [
			{
				label: 'Current Year Projection',
				borderColor: 'rgba(0,51,204, 1)',
				backgroundColor: 'rgba(0,51,204, 0.7)',
				borderWidth:1,
				data: cydata
			},
			{
				label: 'Last Year',
				borderColor: 'rgba(255,0,0, 1)',
				backgroundColor: 'rgba(255,0,0, 0.7)',
				borderWidth:1,
				data: lydata
			}
		]
	};

	var ctx = document.getElementById('zonlProj').getContext('2d');

  if (zonlproj!=undefined) {
	zonlproj.destroy();
  }
	zonlproj = new Chart(ctx, {
		type: 'bar',
		data: data,
		options: {
			barValueSpacing: 25,  
			events: false,
			tooltips: {
			    enabled: true
			},
			legend: {
				position: 'top',
				display:true
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			hover: {
			        animationDuration: 0
			},
			animation: {
				duration: 5,
				onComplete: function () {
				    // render the value of the chart above the bar
				    var ctx = this.chart.ctx;
				    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
				    ctx.fillStyle = this.chart.config.options.defaultFontColor;
				    ctx.textAlign = 'center';
				    ctx.textBaseline = 'bottom';
				    this.data.datasets.forEach(function (dataset) {
					for (var i = 0; i < dataset.data.length; i++) {
				            var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
					    ctx.fillText(dataset.data[i], model.x, model.y - 5);
				        }
				    });
				}}
		}
	});
}
function drawCmdtLdngPerf(lblarr,cydata,lydata)
{
	var data = {
		labels:lblarr,
		datasets: [
			{
				label: 'Current Year (Till '+GG_MaxAnlyDate+')',
				borderColor: 'rgba(0,51,204, 1)',
				backgroundColor: 'rgba(0,51,204, 0.7)',
				borderWidth:1,
				data: cydata
			},
			{
				label: 'Last Year',
				borderColor: 'rgba(255,0,0, 1)',
				backgroundColor: 'rgba(255,0,0, 0.7)',
				borderWidth:1,
				data: lydata
			}
		]
	};

	var ctx = document.getElementById('cmdtLdngPerf').getContext('2d');

  if (cmdtldngperf!=undefined) {
	cmdtldngperf.destroy();
  }
	cmdtldngperf= new Chart(ctx, {
		type: 'bar',
		data: data,
		options: {
			barValueSpacing: 25,  
			events: false,
			tooltips: {
			    enabled: true
			},
			hover: {
			        animationDuration: 0
			},
			legend: {
				position: 'top',
				display:true
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				duration: 5,
				onComplete: function () {
				    // render the value of the chart above the bar
				    var ctx = this.chart.ctx;
				    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, 'normal', Chart.defaults.global.defaultFontFamily);
				    ctx.fillStyle = this.chart.config.options.defaultFontColor;
				    ctx.textAlign = 'center';
				    ctx.textBaseline = 'bottom';
				    this.data.datasets.forEach(function (dataset) {
					for (var i = 0; i < dataset.data.length; i++) {
				            var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model;
					    ctx.fillText(dataset.data[i], model.x, model.y - 5);
				        }
				    });
				}}
		}
	});
}
function drawDvsnLdngPerfCY(lblarr,dataarr)
{
	
	var config = {
		type: 'doughnut',
		data: {
			datasets: [{
				data: dataarr,
				backgroundColor: colrarr,
				label: 'Dataset 1'
			}],
			labels: lblarr
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};

	var htmlstr='<ul class="list-group">';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlstr+='<li class="list-group-item d-flex justify-content-between align-items-center" style="padding:2px;font-size:12px;font-weight:500;">'+lblarr[i]+'<span class="badge badge-primary badge-pill">'+dataarr[i]+'</span></li>';
	}
	htmlstr+='</ul>';
	$("#dvsnldnglblcy").html(htmlstr);
	var ctx = document.getElementById('dvsnLdngCY').getContext('2d');
  	if (dvsnldngcy!=undefined) {
		dvsnldngcy.destroy();
  	}

	var canvas = document.getElementById("dvsnLdngCY");
	var $td = $('#dvsnLdngCY').parent();
	canvas.width = $td.width();
	canvas.height = $td.height();

	dvsnldngcy = new Chart(ctx, config);
	var htmlind='';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlind+='<span class="dot" style="background:'+colrarr[i]+';"></span>&nbsp;'+lblarr[i];
	}


	$("#dvsnLdngFtr").html(htmlind);
}

function drawDvsnLdngPerfLY(lblarr,dataarr)
{	
	
	var config = {
		type: 'doughnut',
		data: {
			datasets: [{
				data: dataarr,
				backgroundColor: colrarr,
				label: 'Dataset 1'
			}],
			labels: lblarr
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};
	var htmlstr='<ul class="list-group">';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlstr+='<li class="list-group-item d-flex justify-content-between align-items-center" style="padding:2px;font-size:12px;font-weight:500;">'+lblarr[i]+'<span class="badge badge-primary badge-pill">'+dataarr[i]+'</span></li>';
	}
	htmlstr+='</ul>';
	$("#dvsnldnglblly").html(htmlstr);
	var ctx = document.getElementById('dvsnLdngLY').getContext('2d');
  	if (dvsnldngly!=undefined) {
		dvsnldngly.destroy();
  	}

	var canvas = document.getElementById("dvsnLdngLY");
	var $td = $('#dvsnLdngLY').parent();
	canvas.width = $td.width();
	canvas.height = $td.height();

	dvsnldngly= new Chart(ctx, config);
}


function drawCmdtShareCY(lblarr,dataarr)
{
	var config = {
		type: 'pie',
		data: {
			datasets: [{
				data: dataarr,
				backgroundColor: colrarr,
				label: 'Dataset 1'
			}],
			labels: lblarr
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};

	var htmlstr='<ul class="list-group">';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlstr+='<li class="list-group-item d-flex justify-content-between align-items-center" style="padding:2px;font-size:12px;font-weight:500;">'+lblarr[i]+'<span class="badge badge-primary badge-pill">'+dataarr[i]+'</span></li>';
	}
	htmlstr+='</ul>';
	$("#cmdtshlblcy").html(htmlstr);
	var ctx = document.getElementById('cmdtShareCY').getContext('2d');
  	if (cmdtsharecy!=undefined) {
		cmdtsharecy.destroy();
  	}

	var canvas = document.getElementById("cmdtShareCY");
	var $td = $('#cmdtShareCY').parent();
	canvas.width = $td.width();
	canvas.height = $td.height();

	cmdtsharecy= new Chart(ctx, config);
	var htmlind='';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlind+='<span class="dot" style="background:'+colrarr[i]+';"></span>&nbsp;'+lblarr[i];
	}

	$("#cmdtShareFtr").html(htmlind);
}

function drawProposalStat(lblarr,dataarr)
{
	var config = {
		type: 'pie',
		data: {
			datasets: [{
				data: dataarr,
				backgroundColor: colrarr,
				label: 'Dataset 1'
			}],
			labels: lblarr
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};

	var ctx = document.getElementById('propStat').getContext('2d');
  	if (propstat!=undefined) {
		propstat.destroy();
  	}

	propstat= new Chart(ctx, config);
	var htmlind='';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlind+='<span class="dot" style="background:'+colrarr[i]+';"></span>&nbsp;'+lblarr[i];
	}
	$("#propStatFtr").html(htmlind);
}
function drawCmdtShareLY(lblarr,dataarr)
{	
	var config = {
		type: 'pie',
		data: {
			datasets: [{
				data: dataarr,
				backgroundColor: colrarr,
				label: 'Dataset 1'
			}],
			labels: lblarr
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};
	var htmlstr='<ul class="list-group">';
	for(var i=0;i<lblarr.length;i++)
	{
		htmlstr+='<li class="list-group-item d-flex justify-content-between align-items-center" style="padding:2px;font-size:12px;font-weight:500;">'+lblarr[i]+'<span class="badge badge-primary badge-pill">'+dataarr[i]+'</span></li>';
	}
	htmlstr+='</ul>';
	$("#cmdtshlblly").html(htmlstr);
	var ctx = document.getElementById('cmdtShareLY').getContext('2d');
  	if (cmdtsharely!=undefined) {
		cmdtsharely.destroy();
  	}

	var canvas = document.getElementById("cmdtShareLY");
	var $td = $('#cmdtShareLY').parent();
	canvas.width = $td.width();
	canvas.height = $td.height();

	cmdtsharely= new Chart(ctx, config);
}
function popDvsnList(zone,dvsn)
{
	$("#selDvsn option").remove(); 
	$("#selDvsn").empty();
	$('#selDvsn').append($('<option>', {
                         	value: '',
                               	text: 'Select Division'
                         }));
	for(var i=0;i<aZoneDvsn.length;i++)
	{				
		if(aZoneDvsn[i].split('-')[0]==zone)
		{
			var crntval=aZoneDvsn[i].split('-');
			$('#selDvsn').append($('<option>', {
                                    	value: crntval[1],
                                    	text: crntval[2]
                                }));
		}
	}
	$('#selDvsn').append($('<option>', {
                         	value: '',
                               	text: 'Zone: '+zone
                         }));
	$("#selDvsn").on('change',function() {
		var crntdvsn=$(this).val();
		if(crntdvsn=="")
		{
                    var zone1=($(this).text()).split(':')[1];
                    drawZonlAnly(zone1,GG_AnlyCtgr)			
		}
		else
		{
			drawDvsnAnlyStat(GG_AnlyCtgr);		
		}
	});
	$('#selDvsn option[value="'+dvsn+'"]').prop('selected', true);
}
function drawCmdtRailCoef(zone,dvsn)
{
	return true;
	var myurl="/RailSAHAY/AcqnPlanMIS";
	var lblarr=[];
	var datarail=[];
	var dataall=[];
	$.ajax({
	     url : myurl,
	     type : "post",
	     data: {Optn:'CMDT_RAIL_COEF',Zone:zone,Dvsn:dvsn},
	     async : true,
	     success : function(data) {
	            var obj= JSON.parse(data);
	            var stts=obj.Stts;
	            if(stts=="S")
	            {				

			for(var i=0;i<obj.DataList.length;i++)
                        {
				lblarr[i]=obj.DataList[i].Val1;				
				dataall[i]=Number(obj.DataList[i].Val2);
				datarail[i]=Number(obj.DataList[i].Val3);
				
			}
			var data1 = {
  labels: lblarr,
  datasets: [{
    label: 'Freight Across All Modes (MT)',
    data: dataall,
    fill: true,
    backgroundColor: 'rgba(255, 99, 132, 0.2)',
    borderColor: 'rgb(255, 99, 132)',
    pointBackgroundColor: 'rgb(255, 99, 132)',
    pointBorderColor: '#fff',
    pointHoverBackgroundColor: '#fff',
    pointHoverBorderColor: 'rgb(255, 99, 132)'
  }, {
    label: 'Rail Share',
    data: datarail,
    fill: true,
    backgroundColor: 'rgba(54, 162, 235, 0.2)',
    borderColor: 'rgb(54, 162, 235)',
    pointBackgroundColor: 'rgb(54, 162, 235)',
    pointBorderColor: '#fff',
    pointHoverBackgroundColor: '#fff',
    pointHoverBorderColor: 'rgb(54, 162, 235)'
  }]
};
			var ctx = document.getElementById('cmdtRailCoef').getContext('2d');

  	if (cmdtRailCoefChrt !=undefined) {
		cmdtRailCoefChrt .destroy();
  	}

			var cmdtRailCoefChrt = new Chart(ctx, {
			    type: 'radar',
			    data: data1 ,
			    options: {
			responsive: true,
			legend: {
				position: 'top',
				display:false
			},
			title: {
				display: false,
				text: 'SMART Module'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			} 
			}
			});
	            }
	            else
	            {
	                failedToast("Failed to Fetch List of Values"+obj.ErorMesg);
	            }
		}
		});
}