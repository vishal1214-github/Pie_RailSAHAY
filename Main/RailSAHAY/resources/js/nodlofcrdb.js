var rlyofcrlist='';
function getSmryStats()
{
	var htmlstr='';
	var myurl="/RailSAHAY/GG_NodlOfcrJson?Optn=SMRY_STATS";
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}

			var srpndg=obj.SRPndg;
			var srfrwd=obj.SRFrwd;
			var iapndg=obj.IAPndg;
			var iafrwd=obj.IAFrwd;
			var crpndg=obj.CRPndg;
			var crfrwd=obj.CRFrwd;
			$("#pndgsrcont").html(srpndg);
			$("#pndgcncrcont").html(crpndg);
			$("#pndgiacont").html(iapndg);
		}
		});
}
function getCncrList()
{
	var myurl="/RailSAHAY/GG_NodlOfcrJson?Optn=CNCR_LIST";
	$("#divActvDtls").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlcncr='';

	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.CncrList.length;i++)
		{
			cncrid=obj.CncrList[i].CncrId;
			cncrtype=obj.CncrList[i].CncrType;
			cncrctgr=obj.CncrList[i].Ctgr;
			cncrsubctgr=obj.CncrList[i].SubCtgr;
			cncrfname=obj.CncrList[i].FName;
			cncrlname=obj.CncrList[i].LName;
			cncrphn=obj.CncrList[i].Phone;
			cncremail=obj.CncrList[i].Email;
			cncrsttn=obj.CncrList[i].Sttn;
			cncrrmrk=obj.CncrList[i].Rmrk;
			cncrupdttime=obj.CncrList[i].UpdtTime;
			
			htmlcncr+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-6 d-flex align-items-center justify-content-between">';
			htmlcncr+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text">';
			htmlcncr+='<h3 class="h4">'+cncrtype+' ('+cncrctgr+')</h3><small>'+cncrsttn+' (Ticket Id: '+ cncrid+')</small></div></div>';
			htmlcncr+='</div><div class="right-col col-lg-6 d-flex align-items-center">';
			htmlcncr+='<div class="time"><i class="far fa-clock"></i> '+cncrupdttime+' </div><button class="btn btn-sm btn-info" data-toggle="collapse" data-target="#cncrdtls'+(i+1)+'">View/Respond</button>&nbsp;&nbsp;';
			htmlcncr+='<button class="btn btn-sm btn-warning" data-toggle="collapse" data-target="#cncrfrwd'+(i+1)+'">Forward</button></div></div></div>'
			htmlcncr+='<div id="cncrdtls'+(i+1)+'" class="collapse"><div class="w3-card-5"><table class="styletable"><tr><td class="lbl">User Remarks</td><td class="val">'+cncrrmrk+'</td></tr>';
			htmlcncr+='<tr><td class="lbl">User Name</td><td class="val">'+cncrfname+' '+cncrlname+'</td></tr>';
			htmlcncr+='<tr><td class="lbl">User Mobile</td><td class="val">'+cncrphn+'</td></tr>';
			htmlcncr+='<tr><td class="lbl">User Email</td><td class="val">'+cncremail+'</td></tr>';
			htmlcncr+='<tr><td class="lbl">Response</td><td class="val"><textarea id="txtcncrresp'+i+'" rows="3" style="width:100%;"  placeholder="PLEASE TYPE YOUR RESPONSE (MAX 500 WORDS) !"></textarea></td></tr>';
			htmlcncr+='<tr><td colspan="2" align="center"><button class="btn btn-danger btn-sm">Save</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#cncrdtls'+(i+1)+'">Close</button></td></tr>';
			htmlcncr+='</table></div></div>';
			htmlcncr+='<div id="cncrfrwd'+(i+1)+'" class="collapse"><table class="table table-striped styletable"><tr><td><input type="text" class="form-control inptcap" id="txtcncrfwd'+i+'" placeholder="Forward to Officer" list="ofcrlist" /><datalist id="ofcrlist"></datalist></td>';
			htmlcncr+='<td align="center"><button class="btn btn-danger btn-sm">Forward</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#cncrfrwd'+(i+1)+'">Close</button></td></tr></table></div>';		

		}
		$("#divActvDtls").html(htmlcncr);
		var my_list=document.getElementById("ofcrlist");
		my_list.innerHTML = rlyofcrlist;

		}
		});
}
function getSRList()
{
	var myurl="/RailSAHAY/GG_NodlOfcrJson?Optn=SR_LIST";
	$("#divActvDtls").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlsr='';
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.CncrList.length;i++)
		{
			srid=obj.CncrList[i].SrvcId;
			srnumb=obj.CncrList[i].SrvcNumb;
			srcustid=obj.CncrList[i].CustId;
			srfnr=obj.CncrList[i].FNR;
			srod=obj.CncrList[i].OD;
			srfnrdtls=obj.CncrList[i].FNRDtls;
			srdmndid=obj.CncrList[i].DmndId;
			srinvcid=obj.CncrList[i].InvcId;
			srtype=obj.CncrList[i].SrvcType;
			srsttn=obj.CncrList[i].Sttn;
			srdvsn=obj.CncrList[i].Dvsn;
			srtext=obj.CncrList[i].Text;
			srmob=obj.CncrList[i].Mob;
			sremail=obj.CncrList[i].Email;
			srtime=obj.CncrList[i].Time;
			
			htmlsr+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-6 d-flex align-items-center justify-content-between">';
			htmlsr+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text">';
			htmlsr+='<h3 class="h4">'+srtype+'</h3><small>'+srsttn+' '+srtime+' (Ticket Id: '+ srid+')</small></div></div>';
			htmlsr+='<div class="project-date"><span class="hidden-sm-down">'+srfnrdtls+'</span></div></div><div class="right-col col-lg-6 d-flex align-items-center">';
			htmlsr+='<div class="time"><i class="far fa-clock"></i> '+srod+' </div><button class="btn btn-sm btn-info" data-toggle="collapse" data-target="#srdtls'+(i+1)+'">View/Respond</button>&nbsp;&nbsp;';
			htmlsr+='<button class="btn btn-sm btn-warning" data-toggle="collapse" data-target="#srfrwd'+(i+1)+'">Forward</button></div></div></div>'
			htmlsr+='<div id="srdtls'+(i+1)+'" class="collapse"><div class="w3-card-5"><table class="styletable"><tr><td class="lbl">User Remarks</td><td class="val">'+srtext+'</td></tr>';
			htmlsr+='<tr><td class="lbl">User Mobile</td><td class="val">'+srmob+'</td></tr>';
			htmlsr+='<tr><td class="lbl">User Email</td><td class="val">'+sremail+'</td></tr>';
			htmlsr+='<tr><td class="lbl">Response</td><td class="val"><textarea id="txtsrresp'+(i+1)+'" rows="3" style="width:100%;"  placeholder="PLEASE TYPE YOUR RESPONSE (MAX 500 WORDS) !"></textarea></td></tr>';
			htmlsr+='<tr><td colspan="2" align="center"><button class="btn btn-danger btn-sm" onclick="saveResponse(\''+srid+'\',\'SR\','+(i+1)+', \''+srdvsn+'\');">Save</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#srdtls'+(i+1)+'">Close</button></td></tr>';
			htmlsr+='</table></div></div>';
			htmlsr+='<div id="srfrwd'+(i+1)+'" class="collapse"><table class="table table-striped styletable"><tr><td><input type="text" class="form-control inptcap" placeholder="Forward to Officer" id="txtsrfwd'+i+'" list="ofcrlist" /><datalist id="ofcrlist"></datalist></td>';
			htmlsr+='<td align="center"><button class="btn btn-danger btn-sm">Forward</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#srfrwd'+(i+1)+'">Close</button></td></tr></table></div>';		

		   }
		$("#divActvDtls").html(htmlsr);
		var my_list=document.getElementById("ofcrlist");
		my_list.innerHTML = rlyofcrlist;
		}
		});
}
function getIAList()
{
	var myurl="/RailSAHAY/GG_NodlOfcrJson?Optn=IA_LIST";
	var htmlia='';
	$("#divActvDtls").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.IAList.length;i++)
		{
			iaid=obj.IAList[i].Id;
			ianumb=obj.IAList[i].Numb;
			iausertype=obj.IAList[i].UserType;
			ianame=obj.IAList[i].Name;
			iaphone=obj.IAList[i].Phone;
			iaemail=obj.IAList[i].Email;
			iacompname=obj.IAList[i].CompName;
			iacomptype=obj.IAList[i].CompType;
			iadsttfrom=obj.IAList[i].DsttFrom;
			iasttnfrom=obj.IAList[i].SttnFrom;
			iadsttto=obj.IAList[i].DsttTo;
			iasttnto=obj.IAList[i].SttnTo;
			iacmdt=obj.IAList[i].Cmdt;
			iatrfctype=obj.IAList[i].TrfcType;
			iatrfcqty=obj.IAList[i].TrfcQty;
			iatrfcfreq=obj.IAList[i].TrfcFreq;
			iawgontype=obj.IAList[i].WgonType;
			iarmrk=obj.IAList[i].Rmrk;
			iaupdttime=obj.IAList[i].UpdtTime;
			var locnfrom='';	
			var locnto='';
			if(iadsttfrom=='') { if(iasttnfrom=='') { locnfrom=''; } else {locnfrom=iasttnfrom; }}
			else { locnfrom=iadsttfrom; }
			if(iadsttto=='') { if(iasttnto=='') { locnto=''; } else {locnto=iasttnto; }}
			else { locnto=iadsttto; }


			htmlia+='<div class="project"><div class="row bg-white has-shadow"><div class="left-col col-lg-6 d-flex align-items-center justify-content-between">';
			htmlia+='<div class="project-title d-flex align-items-center"><button class="btn btn-warning mr-3">'+(i+1)+'</button><div class="text">';
			htmlia+='<h3 class="h4">TRANSPORTATION INTEREST</h3><small>(Ticket Id: '+iaid+')</small></div></div>';
			htmlia+='<div class="project-date"><span class="hidden-sm-down">'+locnfrom+' To '+locnto+' ('+iacmdt+')</span></div></div><div class="right-col col-lg-6 d-flex align-items-center">';
			htmlia+='<div class="time"><i class="far fa-clock"></i> '+iaupdttime+' </div><button class="btn btn-sm btn-info" data-toggle="collapse" data-target="#iadtls'+(i+1)+'">View/Respond</button>&nbsp;&nbsp;';
			htmlia+='<button class="btn btn-sm btn-warning" data-toggle="collapse" data-target="#iafrwd'+(i+1)+'">Forward</button></div></div></div>'
			htmlia+='<div id="iadtls'+(i+1)+'" class="collapse"><div class="w3-card-5"><table class="styletable"><tr><td class="lbl">User Remarks</td><td class="val">'+srtext+'</td></tr>';
			htmlia+='<tr><td class="lbl">Name</td><td class="val">'+ianame+'</td></tr>';
			htmlia+='<tr><td class="lbl">Contact</td><td class="val"><i class="fas fa-phone-square-alt"></i>&nbsp;'+iaphone+'&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; <i class="fas fa-envelope-square"></i>&nbsp;'+iaemail+'</td></tr>';
			htmlia+='<tr><td class="lbl">Company</td><td class="val">'+iacompname+' ('+iacomptype+')</td></tr>';
			htmlia+='<tr><td class="lbl">Traffic</td><td class="val">'+iatrfctype+' (Qty: '+iatrfcqty+', Freq:'+iatrfcfreq+', Period: '+iawgontype+')</td></tr>';
			htmlia+='<tr><td class="lbl">Response</td><td class="val"><textarea id="txtiaresp'+i+'" rows="3" style="width:100%;"  placeholder="PLEASE TYPE YOUR RESPONSE (MAX 500 WORDS) !"></textarea></td></tr>';
			htmlia+='<tr><td colspan="2" align="center"><button class="btn btn-danger btn-sm">Save</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#iadtls'+(i+1)+'">Close</button></td></tr>';
			htmlia+='</table></div></div>';
			htmlia+='<div id="iafrwd'+(i+1)+'" class="collapse"><table class="table table-striped styletable"><tr><td><input type="text" class="form-control inptcap" placeholder="Forward to Officer" id="txtiafwd'+i+'" list="ofcrlist" /><datalist id="ofcrlist"></datalist></td>';
			htmlia+='<td align="center"><button class="btn btn-danger btn-sm">Forward</button>&nbsp;&nbsp;<button class="btn btn-secondary btn-sm" data-toggle="collapse" data-target="#iafrwd'+(i+1)+'">Close</button></td></tr></table></div>';		

		   }
		$("#divActvDtls").html(htmlia);
		var my_list=document.getElementById("ofcrlist");
		my_list.innerHTML = rlyofcrlist;
		}
		});
}
function getFrwdOfcrList()
{
	var myurl="/RailSAHAY/GG_NodlOfcrJson?Optn=FRWD_OFCR_LIST";
	$.ajax({
		url : myurl,
		type : "post",
		async : true,
		success : function(data) {
		var obj= JSON.parse(data);
		var stts=obj.Stts;
		var erormesg=obj.ErorMesg;
		if(stts=="F")
		{
			alert("Not able to connect to Server:"+erormesg);
			return false;
		}
		for(var i=0;i<obj.OfcrList.length;i++)
		{
			locntype=obj.OfcrList[i].LocnType;
			locn=obj.OfcrList[i].Locn;
			desg=obj.OfcrList[i].Desg;
			rlyofcrlist+= '<option value="'+desg+' / '+locn+'" />'; // Helplist for Railway Officers
		}
		}
		});
}
function saveResponse(tktid, actvtype, indx,dvsn)
{
	var resp=$("#txtsrresp"+indx).val();
        
	//alert(tktid+'    '+actvtype+'   '+indx+'   '+resp+'  '+dvsn);
        
        $.ajax({
                        
                        url: "/RailSAHAY/NodalOfcrDashboard?tktid="+ tktid+"&actvtype="+actvtype+"&indx="+indx+"&resp="+resp+"&dvsn="+dvsn,
                        type: 'POST',
                        dataType: 'json',                  
                        success: function (data) {
                      // alert("data:"+data);
                       
                        var obj= data;//JSON.parse(data);                       
                        var reply=obj.reply;
                       
                        var htmlA="";
                        //alert("reply="+reply);
                        
                        /*raman code*/
                        if(reply=="SUCCESS") {
                            
                            $("#savestts").html('<i class="far fa-thumbs-up"></i>&nbsp;Response Posted Successfully !!');
                             $("#savestts").addClass("success");
                        }
                       else {
                            
                            $("#savestts").html('<i class="far fa-thumbs-down"></i>&nbsp;Failed to post the Response !!');
                             $("#savestts").addClass("failed");
                        }
                       
                 },
                        error:function(data,status,er) {
                                alert("error: "+data+" status: "+status+" er:"+er);
                        }
                }); 
                 getSmryStats();
                 getSRList();
        
}
$(document).ready(function()
{
	getSmryStats();
	getCncrList();
	getFrwdOfcrList();
});