                               
                function onFormSubmit() 
                {
                    if($('#txtCmdtName').val().trim() == "")
                    {
                        cuteToast({
                              type: "success",
                              message: "Please Select Commodity Name",
                              timer: 5000
                        });
                        return false;
                    }
                    else if($('#txtSttnFrom').val().trim() == "")
                    {
                        cuteToast({
                              type: "success",
                              message: "Please Select Station From",
                              timer: 5000
                        });
                        return false;
                    }
                    
                    else if($('#txtSttnTo').val().trim() == "")
                    {
                        cuteToast({
                              type: "success",
                              message: "Please Select Station To",
                              timer: 5000
                        });
                        return false;
                    }
                    else if($('#txtWgonType').val().trim() == "")
                    {
                        cuteToast({
                              type: "success",
                              message: "Please Select Wagon Type",
                              timer: 5000
                        });
                        return false;
                    }
                  /*  self.location="/RailSAHAY/pages/FrgtCalc.jsp?txtSttnFrom=" + document.getElementById("txtSttnFrom").value
                                    + "&txtSttnTo="		+ document.getElementById("txtSttnTo").value
                                    + "&txtCmdtName="		+ document.getElementById("txtCmdtName").value
                                    + "&txtWgonType="		+ document.getElementById("txtWgonType").value
                                    + "&selRKPM="		+ $("#selRKPM").val()
                                    + "&txtWgonNumb="		+ document.getElementById("txtWgonNumb").value
                                    + "&selWgon="		+ $('#selWgon').val()
                                    + "&wgontype="		+ $('input[name="wgontype"]:checked').val()
                                    + "&hidWgonNumb="		+ document.getElementById("hidWgonNumb").value;
                    return true; */
                    
                    document.frmFrgtC.submit();
                }    
                    
                
                function getAltRout(fromsttn, tosttn) 
                {
                    $('#txtSttnFrom').val(fromsttn);
                    $('#txtSttnTo').val(tosttn);
                    onFormSubmit();
                }
                
                function chngWgontype(flag) 
                {
                    if(flag == 'C')
                    {
                        document.getElementById("txtWgonType").value = "";
                        //document.getElementById("txtWgonType").readOnly = true;
                        $('#selWgon option:not(:selected)').attr('disabled', false);
                        $("#txtWgonNumb").val('');
                        $("#selRKPM").val($("#selRKPM option:first").val());
                    }
                    else
                    {
                        //document.getElementById("txtWgonType").readOnly = false;                        
                        $('#selWgon option:not(:selected)').attr('disabled', true);
                        $("#selWgon").val($("#selWgon option:first").val());
                    }   
                }                
                

    function fetchRakeCmdt() 
    {
        if($("#txtCmdtName").val() != "") 
        {
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";                    
            var cmdt=$("#txtCmdtName").val();
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'RAKE_CMDT',cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                            return false;
                        }
                        if(DataFlag == "N")
                        {
                            cuteToast({
                              type: "success",
                              message: "Wagon Type Help is not available for the given inputs",
                              timer: 5000
                            })
                            return false;
                        }
                        else
                        {
                            fetchCmdtWgonMpng(obj.RakeCmdt);
                        }
                    }
                }); //Ajax func end            
        } //End of main if
        else 
        {
            $('#wgonModal').modal('hide');
            cuteToast({
              type: "success",
              message: "Select Commodity Name",
              timer: 5000
            });
        }
    }

    function fetchSchmList() 
    {
        if($("#txtCmdtName").val() != "")
        {
            $("#divSchmList").html('');
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";
            var cmdt=document.getElementById("txtCmdtName").value;
            $("#hdSchm").html('Schemes available for Commodity - '+cmdt);                            
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'SCHM_LIST',cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                                return false;
                        }
                        if(DataFlag == "N")
                        {                                
                              cuteToast({
                              type: "success",
                              message: "Schemes are not available for the Selected Commodity",
                              timer: 5000
                            })
                                
                                return false;
                        }
                        else
                        {
                        
                        var htmlstr=
                        '<div class="col table-responsive">'+
                            '<table id="tblSchm" class="display table table-striped table-bordered tabformat table-sm">'+
                            '<thead>'+
                             '       <tr>'+
                             '               <th rowspan="2" class="align-middle" class="w-2">SR.<\/th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-28">Scheme</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-35">Master Rate Circular</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-35">Latest Rate Circular</th>'+
                             '       </tr>'+
                             '       <tr>'+
                             '               <th style="text-align:center;" class="w-3">Code</th>'+
                             '               <th style="text-align:center;" class="w-25">Description</th>'+
                             '               <th style="text-align:center;" class="w-15">No.</th>'+
                             '              <th style="text-align:center;" class="w-20">Date</th>'+
                             '               <th style="text-align:center;" class="w-15">No.</th>'+
                             '               <th style="text-align:center;" class="w-20">Date</th>'+
                             '       </tr>'+
                            '</thead>'+
                            '<tbody>';
                    
                            for(var i=0;i<obj.Data.length;i++)
                            {
                                    var Code    =   obj.Data[i].Code;
                                    var SchmDesc    =   obj.Data[i].SchmDesc;
                                    var MstrCirNo         =   obj.Data[i].MstrCirNo;
                                    var MstrCirDate      =   obj.Data[i].MstrCirDate;
                                    var MstrCirPDF    =   obj.Data[i].MstrCirPDF;
                                    var CrntCirNo    =   obj.Data[i].CrntCirNo;
                                    var CrntCirDate    =   obj.Data[i].CrntCirDate;
                                    var CrntCirPDF    =   obj.Data[i].CrntCirPDF;
                               
                               //MstrCirPDF="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-2K11/RC_05_11.pdf";
                                    
                                    htmlstr+="<tr><td>"+(i+1)+"</td><td>"+Code+"</td><td>"+SchmDesc+"</td>" +
                                    "<td><a href='"+MstrCirPDF+"' style='color:blue;' target='_blank'>"+MstrCirNo+"</a></td><td>"+MstrCirDate+"</td>" +
                                    "<td><a href='"+CrntCirPDF+"' style='color:blue;' target='_blank'>"+CrntCirNo+"</a></td><td>"+CrntCirDate+"</td></tr>";
                            }
                            $('#htmlstr').append("</tbody></table></div>");
                            $("#divSchmList").html(htmlstr);
                        }
                    }
                }); //Ajax func end
        }//end of main if
    }                
    
    function fetchWgonList() 
    {
/*        if($("#txtSttnFrom").val() != "" && $("#txtSttnTo").val() != "" && $("#txtCmdtName").val() != "")
        {
            $("#tblwgonlist").find("tr:gt(0)").remove();
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";                    
            var sttnFrom=document.getElementById("txtSttnFrom").value;
            var sttnto=document.getElementById("txtSttnTo").value;
            var cmdt=document.getElementById("txtCmdtName").value;
                                        
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'WGON_LIST',sttnfrom:sttnFrom,sttnto:sttnto,cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                        //        alert("Wagon Type Help is not available at this moment");
                                return false;
                        }
                        if(DataFlag == "N")
                        {
                               // alert("Wagon Type Help is not available for the given inputs");
                                
                              cuteToast({
                              type: "success",
                              message: "Wagon Type Help is not available for the given inputs",
                              timer: 5000
                            })
                                
                                return false;
                        }
                        else
                        {
                            for(var i=0;i<obj.Data.length;i++)
                            {
                                    var wgoncode    =   obj.Data[i].Code;
                                    var wgondesc    =   obj.Data[i].Desc;
                                    var pcc         =   obj.Data[i].Pcc;
                                    var pccunt      =   obj.Data[i].PccUnit;
                                    var rakesize    =   obj.Data[i].RakeSize;
                                
                                    $('#tblwgonlist').append("<tr><td data-toggle='tooltip' title='"+wgondesc+"'><a href='#' onclick=\"setWagonType('"+wgoncode+"- "+wgondesc+"','"+rakesize+"');\">"+wgoncode+"</a></td><td>"+pcc+" "+pccunt+"</td><td>"+rakesize+"</td></tr>");                                
                            }
                        }
                    }
                }); //Ajax func end
        }//end of main if
*/    }                
    
    function fetchCmdtList() 
    {
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";                    
            var cmdt="";
                                        
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'CMDT_LIST',cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                           // document.getElementById("ldnSttnFrom").style.display = "none";
                            return false;
                        }
                        if(DataFlag == "N")
                        {
                              cuteToast({
                              type: "success",
                              message: obj.ErrorCode,
                              timer: 5000
                            })                    
                             //   document.getElementById("ldnSttnFrom").style.display = "none";
                                return false;
                        }
                        else
                        {
                         /*   $("#cmdtlist").empty();                         
                            var my_list=document.getElementById("cmdtlist");
                            my_list.innerHTML = obj.Data;//str;
                         */
                           var commodity = obj.Data.split("@");
                                                
                            $('#txtCmdtName').autocomplete({
                                source: commodity,
                                minLength: 0,
                            }).focus(function() {
                                $(this).autocomplete("search", "");
                            });
                         
                         }
                    }
                }); //Ajax func end for sttnfrom    
    }
        
    function fetchSttnFrom() 
    {
        if($("#txtCmdtName").val() != "")
        {
            document.getElementById("ldnSttnFrom").style.display = "block";
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";                    
            var cmdt=document.getElementById("txtCmdtName").value;
                                        
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'STTNFROM_LIST',cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                            document.getElementById("ldnSttnFrom").style.display = "none";
                            return false;
                        }
                        if(DataFlag == "N")
                        {
                              cuteToast({
                              type: "success",
                              message: obj.ErrorCode,
                              timer: 5000
                            })                    
                                document.getElementById("ldnSttnFrom").style.display = "none";
                                return false;
                        }
                        else
                        {/*
                            $("#sttnfromlist").empty();                         
                            //alert( $('#sttnfromlist option').length);
                            var my_list=document.getElementById("sttnfromlist");
                            my_list.innerHTML = obj.Data;//str;
                            */
                            
                           var stationfrom = obj.Data.split("@");
                                                
                            $('#txtSttnFrom').autocomplete({
                                source: stationfrom,
                                minLength: 0,
                            }).focus(function() {
                                $(this).autocomplete("search", "");
                            });
                            
                            document.getElementById("ldnSttnFrom").style.display = "none";
                            //$('#txtSttnFrom').val('');
                            fetchSttnTo();
                        }
                    }
                }); //Ajax func end for sttnfrom
               // fetchSttnTo();
            }
    }    
    function fetchSttnTo() 
    {
        if($("#txtCmdtName").val() != "")
        {
            document.getElementById("ldnSttnTo").style.display  = 'block';
            
            var myurl="/RailSAHAY/FSH_FrgtCalcUtil";                    
            var cmdt=document.getElementById("txtCmdtName").value;
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'STTNTO_LIST',cmdt:cmdt},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                            document.getElementById("ldnSttnTo").style.display  = 'none';
                            return false;
                        }
                        if(DataFlag == "N")
                        {
                            cuteToast({
                              type: "success",
                              message: obj.ErrorCode,
                              timer: 5000
                            })
                            document.getElementById("ldnSttnTo").style.display  = 'none';
                            return false;
                        }
                        else
                        {
                            /*
                            $("#sttntolist").empty();
                            var my_list=document.getElementById("sttntolist");
                           my_list.innerHTML = obj.Data;//str;                           
            */
            
                           var stationto = obj.Data.split("@");
                                                
                            $('#txtSttnTo').autocomplete({
                                source: stationto,
                                minLength: 1,
                            }).focus(function() {
                                $(this).autocomplete("search", "");
                            });
                           document.getElementById("ldnSttnTo").style.display  = 'none';
                            
                            //$('#txtSttnTo').val('');
                        }
                    }
                }); //Ajax func end sttnto
               
        }//end of main if
    }                

    function setWagonType(wagonname,rakesize) 
    {
        $("#txtWgonType").val(wagonname);
        $("#txtWgonNumb").val(rakesize);
        $('#hidWgonNumb').val(rakesize);
        $("#selRKPM").val($("#selRKPM option:first").val());
    }
    
    function initFunction () {}
    
function numberWithCommas(x) {
    return x.toString().split('.')[0].length > 3 ? x.toString().substring(0,x.toString().split('.')[0].length-3).replace(/\B(?=(\d{2})+(?!\d))/g, ",") + "," + x.toString().substring(x.toString().split('.')[0].length-3): x.toString();
}

function roundAmount(n) {
 var s = "" + Math.round(n * 100) / 100
 var i = s.indexOf('.')
 if (i < 0) return s + ".00"
 var t = s.substring(0, i + 1) +
    s.substring(i + 1, i + 3)
 if (i + 2 == s.length) t += "0"
 return t
}

function flchk(a,b){
        a=a.replaceAll(",","");
        b=b.replaceAll(",","");
          var timin=parseFloat(a);
         var timout=parseFloat(b);
          var Tottim=(timin+timout);
          Tottim = roundAmount(Tottim);
          Tottim = Tottim.replaceAll(".00","");
  return Tottim;
}

function clrscreen() {
    //$("#sttnfromlist").empty();
    //$("#sttntolist").empty();
    $('#txtSttnFrom').val('');
    $('#txtSttnTo').val('');
    $("#txtWgonType").val('');
    $("#txtWgonNumb").val('');
    $("#selRKPM").val($("#selRKPM option:first").val());
    if($("#txtCmdtName").val() == "")
    {
        document.getElementById("btnWgonType").disabled  =   true;
        document.getElementById("schminfo").disabled  =   true;  
    }
    else
    {
        document.getElementById("btnWgonType").disabled  =   false;
        document.getElementById("schminfo").disabled  =   false;
    }
    $("#div2ndCol").css("display", "none");
    $("#div3rdCol").css("display", "none");
    $("#othrDtls").css("display", "none");
    $("#divWgonTypeList").html('');
    $("#divSchmList").html('');
}

function setPrefWgonType(wgontype, wgondesc) {
    document.getElementById("hwgntype").innerHTML = "  : "+wgontype+"- "+wgondesc;
    $("#txtWgonType").val(wgontype+"- "+wgondesc);
    $('#txtWgonNumb').val('');
    $('#wgonModal').modal('hide');
}
/*
function fetchCmdtWgonMpng(cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#divWgonTypeList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'CMDT_WGON_MPNG',Cmdt:cmdt},
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
	if(obj.WgonList.length==0)
	{
		noDataFound();
		$("#divWgonTypeList").html('');
		return false;
	}
	var prefflag="N";
	var cvrdcont=0;
	var opencont=0;
	for(var i=0;i<obj.WgonList.length;i++)
	{
		var wgontype=obj.WgonList[i].WgonType;
		var wgondesc=obj.WgonList[i].WgonDesc;
		var brak=Number(obj.WgonList[i].Brak);
		var cotype=obj.WgonList[i].COType;
		var wgoncont=obj.WgonList[i].WgonCont;
		var tare=obj.WgonList[i].Tare;
		var cc=obj.WgonList[i].CC;
		var pvtowngflag=obj.WgonList[i].WGONFLAG;
		var stdrksize=obj.WgonList[i].SIZE;
		var wgoncontfortl=obj.WgonList[i].FORTL;
		var stdminirksize=obj.WgonList[i].SIZEBG;
		var miniwgoncontfortl=obj.WgonList[i].FORTLBG;

		if(brak>=0)
		{
			prefflag="Y";
		}
		if(prefflag=="Y")
		{
			if(brak<0)
			{
				break;
			}
		}
		if(prefflag=="N")
		{
			if(i>4)
			{
				break;
			}
		}
                
		if(cotype=="C") { cvrdcont++; }
		if(cotype=="O") { opencont++; }

		if(pvtowngflag=="P")
			htmlstr+='<div class="col-lg-4 col-md-5 col-sm-7"><div class="card wgon-card" onclick="setPrefWgonType(\''+wgontype+'\',\''+wgondesc+'\');"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><span class="prvt-wgon">Privately Owned</span><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';
		else
			htmlstr+='<div class="col-lg-4 col-md-5 col-sm-7"><div class="card wgon-card" onclick="setPrefWgonType(\''+wgontype+'\',\''+wgondesc+'\');"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';

		var stdrksizestr='';

		if(stdminirksize!='')
			stdrksizestr='<p class="paramlbl">Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/><p class="paramlbl">Mini Rake Size<span class="paramval">'+stdminirksize+'</span>  [For Train Load:<span class="paramval">'+miniwgoncontfortl+'</span>]</p><hr/>';
		else
			stdrksizestr='<p class="paramlbl">Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/>';
	
		if(brak>0)
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Loading Preference<span class="paramval">'+brak+'%</span></p><hr/><p class="paramlbl">Stock Available';		
		else
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Stock Available';

			if(cotype=="C")
				htmlstr+='<span class="paramval badge badge-blue text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else if(cotype=="O")
				htmlstr+='<span class="paramval badge badge-success text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else
				htmlstr+='<span class="paramval badge badge-secondary text-white float-right">'+wgoncont+'</span></p></div></div></div>';
	}
        htmlstr+='</div>';
	$("#divWgonTypeList").html(htmlstr);
	$('img.card-img-top').on("error", function() {
  		$(this).attr('src', '/RailSAHAY/resources/images/wagons/NOIMAGE.jpg');
	});
	$("#aToggle").on('click',function() {
		var linktext=$(this).text();
		if(linktext=="Show More..")
			$(this).text('Show Less');
		if(linktext=="Show Less")
			$(this).text('Show More..');
		return true;

	});
	$("#spnCvdCont").html(cvrdcont);
	$("#spnOpnCont").html(opencont);
	}
});
}*/

function fetchCmdtWgonMpng(cmdt)
{
	var myurl="/RailSAHAY/GG_MiscQryJson";
	$("#spnCvdCont").html('');
	$("#spnOpnCont").html('');

	$("#divWgonTypeList").html('<img src="/RailSAHAY/resources/images/loadingIcon2.gif" style="top:10%;width:230px;height:150px;" />');
	var htmlstr='';
	$.ajax({
	url : myurl,
	type : "post",
    	data: {Optn:'CMDT_WGON_MPNG',Cmdt:cmdt},
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
	if(obj.WgonList.length==0)
	{
		noDataFound();
		$("#divWgonTypeList").html('');
		return false;
	}
	var prefflag="N";
	var cvrdcont=0;
	var opencont=0;
	for(var i=0;i<obj.WgonList.length;i++)
	{
		var wgontype=obj.WgonList[i].WgonType;
		var wgondesc=obj.WgonList[i].WgonDesc;
		var brak=Number(obj.WgonList[i].Brak);
		var cotype=obj.WgonList[i].COType;
		var wgoncont=obj.WgonList[i].WgonCont;
		var tare=obj.WgonList[i].Tare;
		var cc=obj.WgonList[i].CC;
		var pvtowngflag=obj.WgonList[i].WGONFLAG;
		var stdrksize=obj.WgonList[i].SIZE;
		var wgoncontfortl=obj.WgonList[i].FORTL;
		var stdminirksize=obj.WgonList[i].SIZEBG;
		var miniwgoncontfortl=obj.WgonList[i].FORTLBG;

		if(brak>0)
		{
			prefflag="Y";
		}
		if(prefflag=="Y")
		{
			if((brak<=0) || (i>4))
			{
				break;
			}
		}
		if(prefflag=="N")
		{
			if(i>4)
			{
				break;
			}
		}

		if(cotype=="C") { cvrdcont++; }
		if(cotype=="O") { opencont++; }


		if(i==8)
		{
			htmlstr+='</div>';
			htmlstr+='<a href="javascript:void(0)" class="card-link1" data-toggle="collapse" data-target="#wgontype" id="aToggle">Show More..</a><br/>';
			htmlstr+='<div id="wgontype" class="collapse"><div class="row" style="margin-top:14px;">';
		}

		if(pvtowngflag=="P")
			htmlstr+='<div class="col-lg-4 col-md-5 col-sm-7"><div class="card" onclick="setPrefWgonType(\''+wgontype+'\',\''+wgondesc+'\');"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><span class="prvt-wgon">Privately Owned</span><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';
		else
			htmlstr+='<div class="col-lg-4 col-md-5 col-sm-7"><div class="card" onclick="setPrefWgonType(\''+wgontype+'\',\''+wgondesc+'\');"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';

		var stdrksizestr='';

		if(stdminirksize!='')
			stdrksizestr='<p class="paramlbl">Std. Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/><p class="paramlbl">Std. Mini Rake Size<span class="paramval">'+stdminirksize+'</span>  [For Train Load:<span class="paramval">'+miniwgoncontfortl+'</span>]</p><hr/>';
		else
			stdrksizestr='<p class="paramlbl">Std. Rake Size<span class="paramval">'+stdrksize+'</span>  [For Train Load:<span class="paramval">'+wgoncontfortl+'</span>]</p><hr/>';



		if(brak>0)
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Loading Preference<span class="paramval">'+brak+'%</span></p><hr/><p class="paramlbl">Stock Available';
		else
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/>'+stdrksizestr+'<p class="paramlbl">Stock Available';

			if(cotype=="C")
				htmlstr+='<span class="paramval badge badge-blue text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else if(cotype=="O")
				htmlstr+='<span class="paramval badge badge-success text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else
				htmlstr+='<span class="paramval badge badge-secondary text-white float-right">'+wgoncont+'</span></p></div></div></div>';


	}
	if(i>8)
	{
		htmlstr+='</div></div>';
	}
	$("#divWgonTypeList").html(htmlstr);
	$('img.card-img-top').on("error", function() {
  		$(this).attr('src', '/RailSAHAY/resources/images/wagons/NOIMAGE.jpg');
	});
	$("#aToggle").on('click',function() {
		var linktext=$(this).text();
		if(linktext=="Show More..")
			$(this).text('Show Less');
		if(linktext=="Show Less")
			$(this).text('Show More..');
		return true;

	});
	$("#spnCvdCont").html(cvrdcont);
	$("#spnOpnCont").html(opencont);
	}
});
}
