var lstOrg=[];
var objMainData = null;
var selOrg="";
var lcnhtmlstr="";
var selSeccustid = "";
var GG_LastCustId="";
function toggleSelected(comp){
    var elements = document.getElementById(comp).options;
    selectElem= document.getElementById(comp);
     selectElem.addEventListener('change', function() {
      var index = selectElem.selectedIndex;
      // Add that data to the <p>
       var d = index; //$(this).index();
        //alert("d:"+d);
           if(elements[d].selected)
               elements[d].selected = false;
            else
                elements[d].selected=true;
    });    
  }
 var select = document.getElementById('multiselect');
/*select.addEventListener('mousedown', function(e){
    var opt = e.target;
    if (opt.selected){
        opt.removeAttribute('selected');
        opt.selected = false;
    } else {
        opt.setAttribute('selected', '');
        opt.selected = true;
    }
    e.preventDefault();
});*/
function addRow(){
                var znlist =  "", dvlist="", sttnlist="";
                var selected = [];
                var selChrg="";
                var htmlstr="";
                var t = "";
                var pcust = document.getElementById("txtCustCode_AM").value;
                //alert("pcust:"+pcust);
                pcust=pcust.substring(1,pcust.indexOf(")"));
                var scust = document.getElementById("txtScCustCode").value;
                 scust=scust.substring(1,scust.indexOf(")"));
                //alert("scust:"+scust);
                var zmulti= document.getElementById('multiselect');
                for (var op=0; op<zmulti.options.length;  op++)
                {
                    if (zmulti.options[op].selected) {                    
                       //alert("zn:"+zmulti.options[op].value+":znlist:"+znlist+":");
                        t=zmulti.options[op].value;
                       // t=t.substring(0,t.indexOf('('));
                         if(znlist!="")
                            znlist=znlist+", "+t;
                        else
                            znlist=t;
                    }
                }
                var dvmulti= document.getElementById('multiselectD');
                for ( op=0; op<dvmulti.options.length;  op++)
                {
                    if (dvmulti.options[op].selected) {
                      // alert("dv:"+dvmulti.options[op].value+":dvlist:"+dvlist+":");
                      t=dvmulti.options[op].value;
                      t=t.substring(0,t.indexOf('('));
                         if(dvlist!="")
                            dvlist=dvlist+", "+t;
                        else
                            dvlist=t;
                    }
                }
                var smulti= document.getElementById('multiselectS');
                for ( op=0; op<smulti.options.length;  op++)
                {
                    if (smulti.options[op].selected) {
                      //  alert("ss:"+smulti.options[op].value+":sttnlist:"+sttnlist+":");
                      t=smulti.options[op].value;
                      t=t.substring(0,t.indexOf('('));
                        if(sttnlist!="")
                            sttnlist=sttnlist+", "+t;
                        else
                            sttnlist=t;
                    }
                }
                zmulti= document.getElementById('lstChrgtyp');
                for ( op=0; op<zmulti.options.length;  op++)
                {
                    if(zmulti.options[op].selected)
                       selChrg=zmulti.options[op].value;
                }
                var tbl= document.getElementById("tblLocn");
                if(selOrg.indexOf('(')>-1)
                    selOrg=selOrg.substring(0,selOrg.indexOf('('));
              var cst =  $("#txtCustCode").val();
               lcnhtmlstr="<tr><td><button class='btn btn-warning btn-sm' style='padding:4px;border-radius:0px;line-height:10px;font-size:11px;cursor:pointer;' onclick='removeSecCust(this);'><i class='fas fa-times'></i></button></td><td>"+znlist+"</td><td>"+dvlist+"</td><td>"+sttnlist+"</td><td>"+selChrg+"</td><td>"+selSeccustid+"</td><td>"+pcust+"</td><td>"+scust+"</td></tr>";
               //lcnhtmlstr="<tr><td>"+selOrg+"</td><td>"+znlist+"</td><td>"+dvlist+"</td><td>"+sttnlist+"</td><td>"+selChrg+"</td><td>"+selSeccustid+"</td></tr>";
                //alert(selected);
                tbl.innerHTML += lcnhtmlstr;
            }
function removeSecCust(tt){
            var tbl= document.getElementById("tblLocn");
            //var index = $('table tr').index(tr);
            var index = $('tr', $(this).closest("table")).index(this);
            /*    if(selOrg.indexOf('(')>-1)
                    selOrg=selOrg.substring(0,selOrg.indexOf('('));
              var cst =  $("#txtCustCode").val();
               lcnhtmlstr="<tr><td>"+znlist+"</td><td>"+dvlist+"</td><td>"+sttnlist+"</td><td>"+selChrg+"</td><td>"+selSeccustid+"</td></tr>";
               //lcnhtmlstr="<tr><td>"+selOrg+"</td><td>"+znlist+"</td><td>"+dvlist+"</td><td>"+sttnlist+"</td><td>"+selChrg+"</td><td>"+selSeccustid+"</td></tr>";
                //alert(selected);
                tbl.innerHTML += lcnhtmlstr;
                */
               /* var s = tbl.innerHTML; // += lcnhtmlstr;//
                var temp = s.substring(0, s.indexOf("<tr>") );
                tbl.innerHTML = temp;
                var y=tbl.innerHTML.length;
                alert("index:"+index);
                for(var t=0;t<tbl.rows.length;t++) {
                    if(index==t) {
                        y = s.indexOf("</tr>",y);
                        y=y+5;
                        //var n = s.substring(temp.length, );
                    }
                    else
                    {
                        tbl.innerHtml+=s.substring(y, s.indexOf("</tr>", temp.length) );
                        tbl.innerHtml+="</tr>";
                        y=tbl.innerHTML.length;
                    }
                }*/
               
            if (typeof(tt) == "object") {
                $(tt).closest("tr").remove();
            } else {
                return false;
            }
}
function cleanTable(){
     var tbl= document.getElementById("tblLocn");
     for(var c=0;c<tbl.childNodes.length;c++) {
         tbl.childNodes[c].remove();
     }
}
function fetchLastUserDtls()
{
	fetchUserDtls(GG_LastCustId);
}
function fetchUserDtls(custid){
        var htmltranlist='';
	GG_LastCustId=custid;
        //var select = document.createElement("multiselect");
        var zn="";
        var dv = ""; var st="";
        var flag=0;
        console.log("custid:"+custid+", Data Length:"+objMainData.UserData.length);
         for(var i=0;i<objMainData.UserData.length;i++)
         { 
                var Temptavcustid=objMainData.UserData[i].Tavcustid;
               console.log("TT:"+Temptavcustid+":"+custid);
                if(Temptavcustid==custid) 
                {
                        selSeccustid=custid;
                        var org=objMainData.UserData[i].org;
			
                       console.log(selOrg+"=="+org[0].name);
                        var tt = '1234';
                         //alert("jh:"+org.length);
                         for(var m=0;m<org.length;m++)
                         {
                               console.log("ORG:"+selOrg+"=="+org[m].name+":");
                                if(selOrg==org[m].name)
                                {
                                        //alert("ff");
                                        tt="1234";
                                        zn=org[m].zone;
                                        console.log("Zone:"+zn);
                                        dv=org[m].dvsn;
                                        console.log("Dvsn:"+dv);
                                        st=org[m].sttn;
                                        console.log("Sttn:"+st);
                                        if(zn!='' && zn!=undefined)
                                             tt=tt.concat(zn);
                                        if(dv!='' && dv!=undefined)
                                             tt=tt.concat(' ' +dv);
                                        if(st!='' && st!=undefined)
                                            tt=tt.concat(' '+st);
                                        tt=tt.replace('1234','');  
                                            
                                        if(zn!='' &&  zn!=undefined)
                                        {
                                            zn=zn.replace(/,/g, "\',\'");
                                            zn=zn.replace('ZONES: ','');
                                            zn=zn.trim();
                                        }
                                        if(dv!='' &&  dv!=undefined)
                                        {
                                           // dv=dv.replace(",", "\',\'");
                                             dv=dv.replace(/,/g, "\',\'");
                                            dv=dv.replace('DIVISIONS: ','')
                                            dv=dv.trim();
                                            //dv="'"+dv;
                                            //dv=dv+"'";
                                        }                                
                                        if(st!='' &&  st!=undefined)
                                        {
                                            st=st.replace(/,/g, "\',\'");
                                            st=st.replace('STATIONS: ','');
                                            st=st.trim();
                                            //st="'"+st;
                                            //st=st+"'";
                                        }
                                         //alert("2345:"+zn.replace(",", "\',\'"));
                                        // alert("kjkj:"+zn+":"+dv+":"+st+":"); 
                                         flag=1;
                                         break;
                                    }                                    
                                } 
                               console.log("zn:"+zn+":dv:"+dv+":st:"+st+":");
                                if(flag==1)
                                    break;
                        }
                }
         console.log("Came for location help");
         var myurl="/RailSAHAY/RgstSecCustUtil";  
             var cmdt="";            
            $.ajax({
                    url : myurl,
                    type : "post",
                    data:  {Qry:'LOCNHELP',zone:zn,dvsn:dv,sttn:st},
                    async : true,
                    success : function(data) 
                    {
                    console.log("mydata:"+data);
                        //alert("in fetchorg");
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                           // document.getElementById("ldnSttnFrom").style.display = "none";
                            return false;
                        }
                       // alert("DataFlag:"+DataFlag);
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
                            /*var zlist = obj.LocnData.ZONE;
                            var dlist = obj.LocnData.DVSN;
                            var slist = obj.LocnData.STTN;*/
                            
                             $("#Schd").show();
                             var selectZ = document.getElementById("multiselect");
                             var selectD = document.getElementById("multiselectD");
                             var selectS = document.getElementById("multiselectS");    
                            // alert("tttt");
                             var st="";
                             //alert(obj.LocnData.ZONE);
                             var zdata = obj.LocnData.ZONE;
                             //alert("Zdata:"+zdata+":");    
                             //alert("Comma Index:"+zdata.indexOf(","));
                             var zarr=null;                             
                             if( obj.LocnData.ZONE!=null &&  obj.LocnData.ZONE!=undefined && obj.LocnData.ZONE!='')
                             {
                               // if(zdata.indexOf(",") != -1)
                                 //   zarr = zdata.split(",");
                                //else                                
                                  //  zarr = zdata;
                                 //alert("zarr:"+zarr.length);
                                 for(var i=0;i<obj.LocnData.ZONE.length;i++)
                                     {
                                        st += "<option value='"+obj.LocnData.ZONE[i]+"'>"+obj.LocnData.ZONE[i]+"</option>";
                                            //st += "<option value='"+zarr[i]+"'>"+zarr[i]+"</option>";
                                     
                                     }
                             }
                         /*     $('.dropdown-asctwb').dropdown({
                                    data: st,
                                    input: '<input type="text" maxLength="20" placeholder="Search">'
                             });
                             $('.spnwb').html('');

                             alert("rgstseccust.st:"+st);*/
                             
                            selectZ.innerHTML = st;    
                            st="";  
                            //alert("DVSN:"+obj.LocnData.DVSN+":");
                            if( obj.LocnData.DVSN!=null &&  obj.LocnData.DVSN!=undefined  && obj.LocnData.DVSN!='')
                                if( obj.LocnData.DVSN.length>0)
                                     for(var i=0;i<obj.LocnData.DVSN.length;i++)
                                     {
                                            st += "<option value='"+obj.LocnData.DVSN[i]+"'>"+obj.LocnData.DVSN[i]+"</option>";                             
                                     }
                             selectD.innerHTML = st;
                             st="";
                            if( obj.LocnData.STTN!=null &&  obj.LocnData.STTN!=undefined && obj.LocnData.STTN!='')
                                if( obj.LocnData.STTN.length>0)
                                     for(var i=0;i<obj.LocnData.STTN.length;i++)
                                     {
                                          st += "<option value='"+obj.LocnData.STTN[i]+"'>"+obj.LocnData.STTN[i]+"</option>";
                                     }
                            selectS.innerHTML = st;
                           // var lbl=document.getElementById("lblOrg");
                           // var seccust=document.getElementById("lblSecCust");
                            //lbl.innerHTML=selOrg;
                            //seccust.innerHTML=custid;
                            //var arrD = obj.LocnData.DVSN;
                            //var arrS = obj.LocnData.STTN;
                            //logger.info(arrS.);
                            //alert("LOCNLIST:"+zlist+ ' ' + obj.dvsns+" "+ ojj.sttn)
                            
                             /*if(zlist!=null)
                                if(zlist!="")
                                    arrZ = zlist.split(",");
                             if(dlist!=null)
                                if(dlist!="")
                                    arrD = dlist.split("#");
                             if(slist!=null)
                                if(slist!="")
                                    arrS = slist.split("#");*/
                           
                           /* for(var i=0;i<arrD.length;i++)
                            {
	                           var st = "<option value='"+arrD[i]+"'>"+arrD[i]+"</option>";
                                   selectD.innerHTML = st;
                            }
                            for(var i=0;i<arrS.length;i++)
                            {
	                           var st = "<option value='"+arrS[i]+"'>"+arrS[i]+"</option>";
                                   selectS.innerHTML = st;
                            }*/
                           // var my_list=document.getElementById("orglist");
                           // my_list.innerHTML = str;
			//htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttnfromname+'">'+sttnfrom+'</a> ('+dvsnfrom+')</td><td class="right">'+strttime+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttntoname+'">'+sttnto+'</a> ('+dvsnto+')</td><td class="right">'+arvltime+'</td><td class="right">'+totltime+'</td><td class="right tdlink" onclick="fetchSchdDtls(\''+schd+'\');">'+daysofrun+'</td></tr>';//
		}
		
		}
		});
      
        /* htmltranlist+='<div class="ptrancard" onclick="fetchUserDtls(\''+Tavcustid+'\');"><table style="width:100%;" class="table table-striped table-ttrout">';
                                   htmltranlist+='<tr><td class="tranod">'+Custfrstname + ' ' + Custlastname+'</td><td>';
                                    htmltranlist+='&nbsp;'+Custemalid+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Custmoblnumb+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Tavcustid+'&nbsp;&nbsp;|&nbsp;&nbsp;'+custaddr+'</td></tr>';
        htmltranlist+='</table>';
        $("#divUserDataList").html(htmltranlist);*/
         /*htmltranlist+='<div id="example"> <select id="multiselect"  class="form-control" name="languages[]" multiple="multiple">';
         htmltranlist+='<option value=\"1" selected>JavaScript</option>';
         htmltranlist+='<option value="2">CSS</option>';
         htmltranlist+='<option value="3" selected>HTML</option>';
        htmltranlist+='<option value="4">Ruby</option>';
        htmltranlist+='                  <option value="5">Go</option>';
         htmltranlist+='                 <option value="6">PHP</option>';
         htmltranlist+='                 <option value="7">ASP.Net</option>';
        htmltranlist+='                 <option value="8">Java</option>';         
        htmltranlist+='                 </select>';
        htmltranlist+='                 </div>';
        $("#divUserDtls").html(htmltranlist);*/
}
function savedata(){
        var arrdata="";
        var k=0;
        var tbl = document.getElementById("tblLocn");
        // var cst =  $("#txtCustCode").val();
        var cst = document.getElementById("txtCustCode_AM").value;
         var scst = document.getElementById("txtScCustCode").value;
        var nrow = tbl.rows.length;
        if(nrow<2)
        {
            cuteToast({
              type: "success",
              message: "No Data to Save, Use Continue button to add data to the table first",
              timer: 5000
            });
            return;
        }
        var ncols = tbl.rows[0].cells.length;
       // alert("nrow:"+nrow+":ncols:"+ncols);
        for(var r=1; r<nrow; r++) {
           // for(var c=0; c<ncols; c++){
                if(r>0)
                    arrdata=arrdata+"$";
               // if(c==0)
                   // arrdata+=tbl.rows.item(r).cells.item(0).innerHTML+"#"+tbl.rows.item(r).cells.item(1).innerHTML+"#Z#"+tbl.rows.item(r).cells.item(2).innerHTML+"#D#"+tbl.rows.item(r).cells.item(3).innerHTML+"#S#"+tbl.rows.item(r).cells.item(4).innerHTML+"@"; /* "+"#"+tbl.rows.item(r).cells.item(5).innerHTML+"@"; */
                     arrdata+=scst+"#"+tbl.rows.item(r).cells.item(1).innerHTML+"#Z#"+tbl.rows.item(r).cells.item(2).innerHTML+"#D#"+tbl.rows.item(r).cells.item(3).innerHTML+"#S#"+tbl.rows.item(r).cells.item(4).innerHTML+"#"+tbl.rows.item(r).cells.item(5).innerHTML+"#"+tbl.rows.item(r).cells.item(6).innerHTML+"^"+tbl.rows.item(r).cells.item(7).innerHTML+"@";
           // }
          //alert("arrdata:"+arrdata+":")
        }
       // alert("arrdata:"+arrdata+":");
         var myurl="/RailSAHAY/RgstSecCustUtil";  
             var cmdt="";
            $.ajax({
                    url : myurl,
                    type : "post",
                    data:  {Qry:'SAVE',arr:arrdata, cust:cst},
                    async : true,
                    success : function(data) 
                    {
                        //alert("in fetchorg");
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var respns    =   obj.response;
                        //var DataFlag=obj.DataFlag;
                        if(ServiceCall == "S") 
                        {                        
                           // alert("Data Saved Successfully");
                            cuteToast({
                              type: "success",
                              message: "Data saved successfully",
                              timer: 5000
                            });
                            cleanTable();
                            $( "#Schd").hide();
                            fetchSecCust();
                            return false;
                        }
                        else if(ServiceCall == "F")
                        {
                                 cuteToast({
                              type: "success",
                              message: obj.ErrorCode,
                              timer: 5000
                            });
                            return false;
                        }
                    }
		});        
}
 function fetchUserDtls_old(custid){
        //var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>CUSTUSERID</th><th>FIRST NAME</th><th>LAST NAME</th><th>EMAIL ID</th><th>MOBILE NUMB</th><th>CUSTID</th></tr>';
	//var htmltranlist='';
	    // alert("IN fetchUserDtls_old");
             var myurl="/RailSAHAY/RgstSecCustUtil";  
            // alert("KLKL:"+lstOrg.length);
            var cmdt="";
            
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'PROFDTLS',custid:custid},
                    async : true,
                    success : function(data) 
                    {
                        //alert("in fetchorg");
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var DataFlag=obj.DataFlag;
                        if(ServiceCall == "F")
                        {
                           // document.getElementById("ldnSttnFrom").style.display = "none";
                            return false;
                        }
                       // alert("DataFlag:"+DataFlag);
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
                        //alert("FDFD:"+obj.custfrstname+ ' ' + obj.custlastname)
                            for(var i=0;i<parseInt(obj.RowCount);i++)
                            {
	                            document.getElementById("firstname").value=obj.custfrstname + ' ' + obj.custlastname;
                                   // alert();
                            }
                           // var my_list=document.getElementById("orglist");
                           // my_list.innerHTML = str;
			/*htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttnfromname+'">'+sttnfrom+'</a> ('+dvsnfrom+')</td><td class="right">'+strttime+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttntoname+'">'+sttnto+'</a> ('+dvsnto+')</td><td class="right">'+arvltime+'</td><td class="right">'+totltime+'</td><td class="right tdlink" onclick="fetchSchdDtls(\''+schd+'\');">'+daysofrun+'</td></tr>';*/
		}
		
		}
		});    
    }

$(document).ready(function(){
   // alert("11234 in onready" );
   //fetchOrg("ORG",'', '', '', '') ;
    
   // var my_list=document.getElementById("orglist");
	//	my_list.innerHTML = lstOrg;
       
     $(".dropdown-menu a").click(function(){
            var str='';
           for (var i=0; i < lstOrg.length;++i){
            str += '<option value="'+lstOrg[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("orglist");
            my_list.innerHTML = str;
           // alert("SS:"+str);
      });
      
      $(document).ready(function(){
	//$("#divUserDataList").hide();
        //fetchOrg("ORG",'', '', '', '') ;
       // fetchOrg("GORG",'', '', '', '') ;
	//fetchTimeTblRout('','');
	});
      
      /* var mySelect = new MSFmultiSelect(
                    document.querySelector('#multiselect'),
                    {
                        selectAll: true,
                        readOnly: true,
                      appendTo: '#example',
                       value:"C++", text:"C++" 
                    }
                );
          */      
        var dvsnstr='';
        for (var i=0; i < aDvsn.length;++i){
        dvsnstr+= '<option value="'+aDvsn[i]+'" />'; // Helplist for Divisions
        }
        var zonestr='';
        for (var v=0; v < aZone.length;++v){
        zonestr+= '<option value="'+aZone[v]+'" />'; // Helplist for Zones
        }

});
function fetchOrg(qryoptn,mnumb, eid, station, custcode) 
    {
        //var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>CUSTUSERID</th><th>FIRST NAME</th><th>LAST NAME</th><th>EMAIL ID</th><th>MOBILE NUMB</th><th>CUSTID</th></tr>';
	//var htmltranlist='';
	     var myurl="/RailSAHAY/RgstSecCustUtil";  
            // alert("KLKL:"+lstOrg.length);
            var cmdt="";
            if(lstOrg.length>0)
            {
                return;
            }
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:qryoptn,moblnumb:mnumb, emailid:eid , sttn:station , cust:custcode},
                    async : true,
                    success : function(data) 
                    {
                        //alert("in fetchorg");
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
                                console.log("rgst.:"+obj+":");
                                alert("rgst.:"+obj+":")
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
                        //alert("FDFD:"+obj.RowCount)
                        var str="";
                        lstOrg=[];
                            for(var i=0;i<parseInt(obj.RowCount);i++)
                            {
                                //alert("bvbvbv:"+obj.OrgData[i]);
                                   lstOrg.push(obj.OrgData[i]);
                            }
                           // alert("lstOrg.length:"+lstOrg.length);
                            //alert("LEN::"+lstOrg.length);
                            for (var j=0; j < lstOrg.length;++j){
                                str += '<option value="'+lstOrg[j]+'" />'; // Helplist for station
                            }
                            var my_list=document.getElementById("orglist");
                            if(qryoptn=="ORG")
                            {
                                //my_list=document.getElementById("orglist");
                                if(parseInt(obj.RowCount)==1)
                                    document.getElementById("txtCustCode_AM").value=lstOrg[0];                                    
                            }
                            else
                            {
                                my_list=document.getElementById("orgseclist"); 
                                if(parseInt(obj.RowCount)==1)
                                    document.getElementById("txtScCustCode").value=lstOrg[0];
                            }
                            my_list.innerHTML = str;
			/*htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttnfromname+'">'+sttnfrom+'</a> ('+dvsnfrom+')</td><td class="right">'+strttime+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttntoname+'">'+sttnto+'</a> ('+dvsnto+')</td><td class="right">'+arvltime+'</td><td class="right">'+totltime+'</td><td class="right tdlink" onclick="fetchSchdDtls(\''+schd+'\');">'+daysofrun+'</td></tr>';*/
		}		
		}
		});    
    }
    function resetvalues(){
        cleanTable();
        document.getElementById("txtCustCode_AM").value="";
        document.getElementById("txtScCustCode").value="";
        document.getElementById("txtMoblNumb").value="";
        document.getElementById("txtEmailId").value="";
        $("#divUserDataList").html("");
        $("#multiselect").Items.Clear();
        $("#multiselectD").Items.Clear();
        $("#multiselectS").Items.Clear();
    }
function fetchData(qryoptn,mnumb, eid, station, custcode, pcustcode) 
    {
        // alert("in fetchdata");
	    $("#divUserDtls").hide();
	$("#divUserDataList").html("");
       var htmlstr='<table class="table table-striped table-bordered tabformat"><tr><th style="max-width:80px;">SR.</th><th>CUSTUSERID</th><th>FIRST NAME</th><th>LAST NAME</th><th>EMAIL ID</th><th>MOBILE NUMB</th><th>CUSTID</th></tr>';
	var htmltranlist='';
	
            var myurl="/RailSAHAY/RgstSecCustUtil";                    
            var cmdt="";
                                        
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:qryoptn,moblnumb:mnumb, emailid:eid , sttn:station , cust:custcode, pcust:pcustcode},
                    async : true,
                    success : function(data) 
                    {
                        var obj= JSON.parse(data);
                        objMainData = obj;
                        //alert(objMainData+"::"+obj);
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
                              message: "No Data Found",
                              timer: 5000
                            })                    
                             //   document.getElementById("ldnSttnFrom").style.display = "none";
                                return false;
                        }
                        else
                        {
                            //alert("hello:"+obj.UserData.length);                            
                            for(var i=0;i<obj.UserData.length;i++)
                            {
                                    var Custuserid=obj.UserData[i].Custuserid;
                                    var Custctgr=obj.UserData[i].Custctgr;
                                    var Custfrstname=obj.UserData[i].Custfrstname;
                                    var Custlastname=obj.UserData[i].Custlastname;
                                    var Custemalid=obj.UserData[i].Custemalid;
                                    var Custmoblnumb=obj.UserData[i].Custmoblnumb;
                                    var custaddr = obj.UserData[i].custaddr;
                                    var Tavcustid=obj.UserData[i].Tavcustid;
                                    var org=obj.UserData[i].org;
                                    //alert("ORG:"+Tavcustid+"  "+org.length);
                                   var zone="";
                                    var dvsn="";
                                    var sttn="";
                                    var orgname="";
                                   /* htmltranlist+='<div class="ptrancard" onclick="fetchUserDtls(\''+Custemalid+'\');"><table style="width:100%;" class="table table-striped table-ttrout">';
                                    htmltranlist+='<tr><td colspan="2" class="tranod">'+Custuserid + '&nbsp; <i class="fas fa-long-arrow-alt-right"></i> &nbsp;';
                                    htmltranlist+='</td></tr><tr><td>&nbsp; '+Custfrstname + ' ' + Custlastname+'</td><td style="width:60%;">';
                                    htmltranlist+='&nbsp;'+Custemalid+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Custmoblnumb+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Tavcustid+'</td></tr></table></div>';
                                    */
                                    htmltranlist+='<div class="ptrancard" onclick="fetchUserDtls(\''+Tavcustid+'\');"><table style="width:100%;" class="table table-striped table-ttrout">';
                                   /* htmltranlist+='<tr><td colspan="2" class="tranod">'+Custuserid + '';*/
                                    htmltranlist+='<tr><td class="tranod" colspan="3">'+Custfrstname + ' ' + Custlastname+'<span class="badge badge-info hndg-agnt-val float-right">'+custcode+'</span></td></tr>';
				    htmltranlist+='<tr><td><i class="fas fa-envelope"></i>&nbsp;'+Custemalid+'</td><td><i class="fas fa-mobile-alt"></i>&nbsp;'+Custmoblnumb+'</td><td><i class="fas fa-portrait"></i>&nbsp;'+Tavcustid+'</td></tr>';
                                    /*htmltranlist+='&nbsp;'+Custemalid+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Custmoblnumb+'&nbsp;&nbsp;|&nbsp;&nbsp;'+Tavcustid+'</td></tr>';*/ //'&nbsp;&nbsp;|&nbsp;&nbsp;'+custaddr+'</td></tr>';
                                    var tt = '1234';
                                    for(var m=0;m<org.length;m++)
                                    {
                                        tt="1234";
                                        //zone=""; dvsn="";  sttn="";
                                        zone=org[m].zone;
                                        dvsn=org[m].dvsn;
                                        sttn=org[m].sttn;
                                       // orgname=org[m].name;
                                      //  alert("zone:"+zone+":"+"dvsn:"+dvsn+":"+"sttn:"+sttn);
                                        if(zone!='' && zone!=undefined)
                                             tt=tt.concat(zone);
                                        if(dvsn!='' && dvsn!=undefined)
                                             tt=tt.concat(' ' +dvsn);
                                        if(sttn!='' && sttn!=undefined)
                                            tt=tt.concat(' '+sttn);
                                        tt=tt.replace('1234','');
                                      //  alert("tt:"+tt+":");
                                        htmltranlist+=' <tr onclick=trclick(this);><td colspan="3">&nbsp;'+tt+'</td></tr>';                                    
                                    }                                    
                                    htmltranlist+='</table></div>';
                            }
			/*htmlstr+='<tr><td style="max-width:80px;" class="center">'+(i+1)+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttnfromname+'">'+sttnfrom+'</a> ('+dvsnfrom+')</td><td class="right">'+strttime+'</td><td class="left"><a href="#" data-toggle="tooltip" title="'+sttntoname+'">'+sttnto+'</a> ('+dvsnto+')</td><td class="right">'+arvltime+'</td><td class="right">'+totltime+'</td><td class="right tdlink" onclick="fetchSchdDtls(\''+schd+'\');">'+daysofrun+'</td></tr>';*/
		}
		htmlstr+='</table>';
               // alert("htmltranlist:"+htmltranlist);
		$("#divUserDataList").html(htmltranlist);
		}
		});    
    }
    function trclick(trow){
        /*selOrg = trow.childNodes[0].innerHTML;
        var start = selOrg.indexOf("<b>");
        var end  = selOrg.indexOf("</b>");
        selOrg = selOrg.substring(start+3, end);*/        
        selOrg=document.getElementById("txtScCustCode").value;
       // alert("selOrg:"+selOrg+":");
        //selSeccustid = 
    }
    function closeSecRecord(rr) {
        var ri;
	$(rr).closest('tr').addClass("mark-delete");
        /*alert("in closeSecRecord");*/
	var cnfmflag=confirm("This action will deactivate the Secondary Customer! Click OK to Continue");
	if(!cnfmflag)
	{
	        $(rr).closest('tr').removeClass("mark-delete");
		return true;
	 }

      /*  if (typeof(rr) == "object") {
            ri=$(rr).closest("tr").rowIndex;
        } else {
            return false;
        }*/
        var s = '';
        var tt=rr.parentNode.parentNode.childNodes.length;
        //alert("nodeName:"+rr.parentNode.parentNode.nodeName+":tt:"+tt);
        for(var h=0;h<tt-1;h++){
            s+=rr.parentNode.parentNode.childNodes[h].innerHTML+"#";
        }
        var custOrgn =$("#custList").val();
       // alert("11");
        var custOrgn1=$("#custList1").html();
       // alert("12");
        if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
            custcode=custOrgn;
        else
            custcode=custOrgn1;
        //alert("ss:"+s+":");
        var myurl="/RailSAHAY/RgstSecCustUtil";  
             var cmdt="";
            $.ajax({
                    url : myurl,
                    type : "post",
                    data:  {Qry:'CLOSESEC',record:s, cust:custcode},
                    async : true,
                    success : function(data) 
                    {
                        //alert("in fetchorg");
                        var obj= JSON.parse(data);
                        var ServiceCall=obj.ServiceCall;
                        var respns    =   obj.response;
                        //var DataFlag=obj.DataFlag;
                        if(ServiceCall == "S") 
                        {                        
                           // alert("Data Saved Successfully");
                            cuteToast({
                              type: "success",
                              message: "Data saved successfully",
                              timer: 5000
                            });
                            fetchSecCust();
                            return false;
                        }
                        else if(ServiceCall == "F")
                        {
                                 cuteToast({
                              type: "success",
                              message: obj.ErrorCode,
                              timer: 5000
                            });
                            return false;
                        }
                    }
		});   
   }
    function fetchSecCust()
    {
        GG_CrntView="SecCustV";
       // alert("in fetchSecCust");
        $("#dataD").hide();
            $("#frmSr").hide();
            $("#frmCncrn").hide();
           // alert("0");
            $("#trmlProf").hide();
            $("#divTrmlProf").html("");
            $("#NodlOfcr").hide();
            $("#divFrgtPymt").hide();
            $("#divScndCust").show();

	   $('#divScndCust').scrollTop(0);
            $("#divScndCustNew").hide();
            $( "#Schd").hide();
           // $("#divAdMfScndCust").hide();
        //alert("Consignment Detail");
        var custuserid  = "<%= userdtls.getTavcustid()%>";
        //  alert(custuserid);
       // var custOrgn="";
       /* if('<%=orgList.size()%>'*1 >1){
            custOrgn =document.getElementById("custList").value;
        }else{
            custOrgn=document.getElementById("custList1").innerHTML;
        }*/
        var custcode='';
        var custOrgn =$("#custList").val();
       // alert("11");
        var custOrgn1=$("#custList1").html();
       // alert("12");
        if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
            custcode=custOrgn;
        else
            custcode=custOrgn1;
        // alert("14:"+custcode);           
        var myurl="/RailSAHAY/RgstSecCustUtil";
        $("#divScndCust").html('<div class="spinner-border text-danger"></div>');
    //alert("00");
           
          //alert("1");  
	var htmlNoSecCust='<p class="no-sec-cust mesg-cnt"><i class="fas fa-exclamation-triangle"></i>&nbsp;&nbsp;No Secondary Customer Exists! <a href="javascript:void(0);" class="card-link1" onclick="addMdfyScCst_New();"> Click Here </a> to add a new Customer (Handling Agent)</p>';
        //var htmlseccust="<div class='float-center'><p class='ctgr-title'>Secondary Customer</p></div>";  
        var htmlseccust='<form id="frmMR" name="frmMR" method="post" action="\\RailSAHAY\\DownloadMRpdf" target = "_blank" ></form>';
    
            htmlseccust+='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">My Secondary Customers</p></div>';
	    htmlseccust+='<div class="col-lg-8 col-sm-12" align="right"><div class="btn-group" role="group" aria-label="Add Secondary Customer"><button type="button" onclick="addMdfyScCst_New();" class="btn btn-success btn-sm text-white"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;Add New Customer</button></div></div></div>';
  	    /*htmlseccust+='<div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><div class="input-group-append"><button class="btn btn-primary" onclick="addMdfyScCst_New();"></button></div></div></div></div>';*/
    
        htmlseccust+='<div class="row"><div class="col-lg-12 col-sm-12"><div class="table-responsive" style="min-height:100vh;max-height:100vh;overflow-y:auto;"><table class="table table-striped table-bordered table-responsive table-sm table-sec-cust" style="table-layout:fixed;" id="tblSecCust"><thead><tr><th align="center" colspan="2">CUSTOMER</th><th style="display:none;"> </th><th colspan="3">Agent Detail</th><th rowspan="2">CHARGE Type</th><th colspan="3">Registration</th><th colspan="2">Validity</th><th rowspan="2">&nbsp;</th></tr><tr><th>PRIMARY</th><th >SECONDARY</th><th>NAME</th><th>NUMBER</th><th>EMAIL ID</th><th>ZONE</th><th>DIVISION</th><th>STATION</th><th>FROM</th><th>TO</th></tr></thead><tbody>';
            //	var htmlseccust='<div class="row"><div class="col-lg-4 col-sm-12"><p class="ctgr-title">Payments Transaction History</p></div><div class="col-lg-8 col-sm-12"><div class="input-group mb-3 float-right" style="width:300px;font-size:12px;"><input type="text" class="form-control text-uppercase" list="frgtpymtlocnlist" placeholder="Station" id="txtFrgtPymtSttn" value="'+sttn+'" style="width:150px;"><datalist id="frgtpymtlocnlist"></datalist><div class="input-group-append"><button class="btn btn-primary" onclick="fetchSttnOtsgPymt();">Go</button></div></div></div></div>';
         //alert("2");
         var myurl="/RailSAHAY/RgstSecCustUtil";
            $.ajax({
                    url : myurl,
                    type : "post",
                    data: {Qry:'SECCUST',CustCode:custcode},
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
                            var ln = 0;
                            if(obj.DataFlag=="N") {
                                /*htmlseccust+='<tr><td colspan="8">NO DATA FOUND</td></tr>';*/
				$("#divScndCust").html(htmlNoSecCust);
				return true;
                            }
                            else
                                ln=obj.SecCustData.length;
                            //if(obj.SecCustData.length==0)
                           // {
                           // htmlseccust+='<tr><td colspan="8">NO DATA FOUND</td></tr>';
                           // }
                            for(var i=0;i<ln;i++)
                            {                                
                                    var porg=obj.SecCustData[i].PrmOrg;
                                    var sorg=obj.SecCustData[i].SecOrgCode;
                                    var sname=obj.SecCustData[i].SecName;
                                    var seccustid=obj.SecCustData[i].SecCustId;
                                   var smobnum = obj.SecCustData[i].CustMobNum;
                                    var emalid=obj.SecCustData[i].EmailId;
                                    var chrgtyp=obj.SecCustData[i].ChrgType;
                                    //chnaged on 18-08-2021 var locn=obj.SecCustData[i].Locn;
                                    var locn=obj.SecCustData[i].ZONES; //added on 18-08-2021
                                    var dvsn=obj.SecCustData[i].DVSN; //added on 18-08-2021
                                    var sttn=obj.SecCustData[i].STATIONS; //added on 18-08-2021
                                    var dfrom=obj.SecCustData[i].DateFrom;
                                    var dto=obj.SecCustData[i].DateTo;
                                    var colr = "text-success";
                                     
                                   htmlseccust+='<tr >'; //data-toggle="collapse p-od" data-target="#demo'+i+'" onclick="hideAltrows(\'demo'+i+'\',\''+obj.SecCustData.length+'\');">';
                                   htmlseccust+='<td style="width:5%;" class="left">'+porg+'</td><td style="width:20%;" class="left">'+sorg+'</td><td class="left">'+sname+'</td><td style="display:none;" class="left" >'+seccustid+'</td><td class="left" style="width:10%;">'+smobnum+'</td><td style="width:10%;" class="left" >'+emalid+'</td><td style="width:5%;"class="left " >'+chrgtyp+'</td><td class="left" style="word-wrap: break-word;width:5%;font-weight:600;">'+locn+'</td><td class="left" style="word-wrap: break-word;width:5%;font-weight:600;">'+dvsn+'</td><td class="left" style="word-wrap: break-word;width:5%;font-weight:600;">'+sttn+'</td><td class="left" style="width:10%;font-weight:600;">'+dfrom+'</td><td style="width:10%;" class="left " >'+dto+'</td>';
                                   if(dto==null || dto=='')
                                        htmlseccust+='<td><button class="btn btn-warning btn-sm" onclick="closeSecRecord(this);"><i class="fas fa-times"></i></button></td></tr>';
                                   else
                                              htmlseccust+='<td></td></tr>';
                                  
                                   // alert("hh:"+htmlseccust);
                                    /* htmlseccust+='<tr class="collapse in" id="demo'+i+'">';
                                    htmlseccust+='<td colspan="7"><div>';*/
                                }
                                    htmlseccust+='<ul class="list-group list-group-flush"><li class="list-group-item"><table class="pymt-dtls-table1" style="border:2px solid '+colr+';"><tr>';
                                   htmlseccust+='</div></td></tr>';
                    
                  
                htmlseccust+='</tbody></table></div></div></div>';
                            $("#divScndCust").html(htmlseccust);
			$('#divScndCust').scrollTop(0);
                    }
            });
    }
function showMySecCust()
{
	$("#divScndCust").slideDown();
	$("#divScndCustNew").hide();
}