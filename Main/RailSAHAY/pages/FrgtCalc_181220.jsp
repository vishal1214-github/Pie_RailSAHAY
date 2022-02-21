<%@ page import="util.*" %> 
<%@ include file="/pages/GG_Header.jspf" %>   
<% strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FREIGHT CALCULATOR","/RailSAHAY/pages/FrgtCalc.jsp",UserDevice,Browser); %>
    <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
    <link rel="stylesheet" href="/RailSAHAY/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/RailSAHAY/resources/css/trmlfcty.css"/>
<link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
            <script src="/RailSAHAY/resources/js/trmlfctyajax.js"></script>
	    <script src="/RailSAHAY/resources/js/trmlutil.js"></script>
	    <script src="/RailSAHAY/resources/js/cmdtfrgtcalc.js"></script>
   
    <style>
    .modal-dialog{
    overflow-y: initial !important
}
.modal-body{
    height: 80vh;
    overflow-y: auto;
}
    a {
        color:#000;
    }
    .strike {
        text-decoration: line-through;
    }
    p.card-text {
        text-align: left;
    }    
    .wgntype
    {
        border:0;
        font-size: 12px;
        margin-top: -1em;
    }
    .table-condensed{
      font-size: 13px;
    }
    .lblcolor {
        color:black;
    border-color:#CCC;
    outline:0;
    -webkit-box-shadow:none;
    box-shadow:none;
    }
    input[type=radio] {
    margin-top: 0.8rem;
}    
    .removepadding {
    padding-left: 0rem;
    padding-right: 0rem;
}
    .custom-control {
    padding-left: 0rem;
}
    legend{
        font-size: .9rem;
    }
    .wgonbox {
        width:90%;
    }
    
    .custom-control-input {
    opacity: 1;
    z-index: 1;
    }
tr.hide-table-padding td {
  padding: 0;
}

.expand-button {
	position: relative;
}

.accordion-toggle .expand-button:after
{
  position: absolute;
  left:.75rem;
  top: 50%;
  transform: translate(0, -50%);
  content: '-';
}
.accordion-toggle.collapsed .expand-button:after
{
  content: '+';
}
.dropdown-menu {
	min-width: 200px;
}
.dropdown-menu.columns-2 {
	min-width: 400px;
}
.dropdown-menu.columns-3 {
	min-width: 600px;
}
.dropdown-menu li a {
	padding: 5px 15px;
	font-weight: 300;
}
.multi-column-dropdown {
	list-style: none;
  margin: 0px;
  padding: 0px;
}
.multi-column-dropdown li a {
	display: block;
	clear: both;
	line-height: 1.428571429;
	color: #333;
	white-space: normal;
}
.multi-column-dropdown li a:hover {
	text-decoration: none;
	color: #262626;
	background-color: #999;
}
 
@media (max-width: 767px) {
	.dropdown-menu.multi-column {
		min-width: 240px !important;
		overflow-x: hidden;
	}
}
.wgon-card {
cursor:pointer;
}
    </style>
<%
String si_SttnFrom 	= (String)request.getAttribute("txtSttnFrom");
String si_SttnTo 	= (String)request.getAttribute("txtSttnTo");
String si_CmdtName 	= (String)request.getAttribute("txtCmdtName");
String si_WgonType 	= (String)request.getAttribute("txtWgonType");
String si_RKPM		= (String)request.getAttribute("selRKPM");
String si_WgonNumb	= (String)request.getAttribute("txtWgonNumb");
String si_selWgonCtgy	= (String)request.getAttribute("selWgon");
String si_optWgontype	= (String)request.getAttribute("wgontype");
String si_RakeSize      = (String)request.getAttribute("hidWgonNumb");
if(si_RakeSize == null)
    si_RakeSize =   "";
	int intDataSize 	=	0;
	int lastcount		=	-1;
String strValues[][]    = new String[14][19];     // 0th row is for wagon load,1st row is for train load and 3rd row is for error, after that for other charges , rebate charges & surcharge details for wagon & train load in case of rake/minirake and wagon load other charges, rebate and surcharge in case of piecemeal
                                                 // 12th row is having xml response from prefrgtcalc on its first column
for(int i=0;i<14;i++)
        for(int j=0;j<19;j++)
                strValues[i][j]="";
String strPreFrgtCalc[] = new String[13];
for(int i=0;i<13;i++)
    strPreFrgtCalc[i]  =   "";
String[][] strDtls = null;
String strCoordinates = "";
if(si_SttnFrom != null)
{
    System.out.println("station from+"+si_SttnFrom);
    System.out.println("si_SttnTo:"+si_SttnTo); 
    System.out.println("si_CmdtName:"+si_CmdtName); 
    System.out.println("si_WgonType:"+si_WgonType); 
    System.out.println("si_RKPM:"+si_RKPM); 
    System.out.println("si_WgonNumb:"+si_WgonNumb);
    System.out.println("si_selWgonCtgy:"+si_selWgonCtgy); 
    System.out.println("si_optWgontype:"+si_optWgontype);
    
    
    String strInpt ="";
    strInpt="STTN FROM="+si_SttnFrom;
    strInpt = strInpt+"#";
    strInpt=strInpt+"STTN TO="+si_SttnTo;
    strInpt = strInpt+"#";
    strInpt=strInpt+"COMMODITY="+si_CmdtName;
    strInpt = strInpt+"#";
    strInpt=strInpt+"WGON TYPE="+si_WgonType;
    strInpt = strInpt+"#";
    strInpt=strInpt+"RKPM="+si_RKPM;
    strInpt = strInpt+"#";
    strInpt=strInpt+"WGON NUMB="+si_WgonNumb;
    strInpt = strInpt+"#";
    strInpt=strInpt+"WGON CTGY="+si_selWgonCtgy;
    strInpt = strInpt+"#";
    strInpt=strInpt+"OPTN WGONTYPE="+si_optWgontype;
    strInpt = strInpt+"#";
        
    strInpt = strInpt.trim().toUpperCase();
    GG_ProjUtil objlog = new GG_ProjUtil();
    objlog.setSahayInpt(request, "FREIGHT CALCULATOR","/RailSAHAY/pages/FrgtCalc.jsp", strInpt);

     strValues=(String[][])model.FSH_FrgtCalc.getDtls(si_SttnFrom,si_SttnTo,si_CmdtName,si_WgonType,"R",si_RKPM,si_WgonNumb,si_selWgonCtgy,si_optWgontype);  //R is Railway User, Need to do for Public users as well
     //System.out.println("strValues:"+strValues);
     
     if(strValues == null)
     {
        strValues= new String[14][19];
        for(int i=0;i<strValues.length;i++)
            for(int j=0;j<strValues[i].length;j++)
                strValues[i][j] =   "";
    }
    else
    {
        strPreFrgtCalc =  strValues[12][0].split("@");
         String SttnFrom = "";
         String SttnTo  =   "";
        if(si_SttnFrom.indexOf("-") != -1)
                SttnFrom                              =       si_SttnFrom.substring(si_SttnFrom.lastIndexOf("-")+1,si_SttnFrom.lastIndexOf("(")).trim();
        if(si_SttnTo.indexOf("-") != -1)
                SttnTo                                =       si_SttnTo.substring(si_SttnTo.lastIndexOf("-")+1,si_SttnTo.lastIndexOf("(")).trim();
	util.GG_AppFuncMap obj	=	new util.GG_AppFuncMap();
	strDtls      =	obj.mapGetFrgtCalcLatLong(SttnFrom+"-"+strPreFrgtCalc[5]+"-"+SttnTo);
	strCoordinates	=	"[";
	if (strDtls == null)
		intDataSize =0;
	else
	{
		intDataSize=strDtls.length;
		lastcount	=	strDtls.length -1;
		for(int i=0; i<intDataSize; i++)
			if(i==0)
				strCoordinates	=	strCoordinates + "{lat:"+strDtls[i][4]+",lng:"+strDtls[i][5]+"}";
			else
				strCoordinates	=	strCoordinates + ","+"{lat:"+strDtls[i][4]+",lng:"+strDtls[i][5]+"}";
		strCoordinates=strCoordinates+"]";	
	}
	System.out.println("DataSize:"+intDataSize);        
        
    }
    System.out.println("strPreFrgtCalc:"+strValues[12][0]);
    if(si_WgonNumb.equals(""))
        si_WgonNumb =   strPreFrgtCalc[8];
}
else
{
    System.out.println("SttnFrom is null"); 
    si_SttnFrom 	= "";
    si_SttnTo 	        = "";
    si_CmdtName 	= "";
    si_WgonType 	= "";
    si_RKPM		= "R";
    si_WgonNumb	        = "";  
    si_selWgonCtgy	= "Wagon_Category";
    si_optWgontype	= "T";
    si_RakeSize         = "";
}
%>
      <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;"></nav>
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4e" data-aos="fade-up" data-aos-delay="100"><%=E.EH("Expected Freight Charges")%></h3>
              </div>
            </div>
           </div>
          </div>
<div class="p-3 mb-2 bg-white text-dark">
<form role="form-inline" action="/RailSAHAY/FrgtCalc" METHOD="post" id="frmFrgtC" name="frmFrgtC">
<div class="row" style="margin-left:5px;margin-right:5px;">
	<div class="col col-lg-3 col-md-3 col-sm-6 col-xs-6 padding-2" style="padding-right: 0px;padding-left: 0px;">
        
        <div class="card border border-info mb-1">
            <div class="card-header"><%=E.EH("Cargo Description")%>
                 <!--<button type="button" class="btn btn-outline-light" data-toggle="modal" data-target="#modalSchm" id="schminfo"></button>-->
            </div>
            <div class="card-body text-primary">
        
		<div class="panel panel-primary">
		<div class="form-card">
		<div class="p-1 input-group">
                    <input type="text" list="cmdtlist"  class="form-control inptcap" id="txtCmdtName" name="txtCmdtName"    onchange="clrscreen();fetchSchmList();fetchSttnFrom();fetchWgonList();fetchRakeCmdt();" required placeholder='<%=E.EH("Commodity Name")%>'  value='<%=si_CmdtName%>' autocomplete="off"/>
                    <!-- <button type="button" class="btn btn-outline-info" data-toggle="modal" title="Click to view Schemes available on Selected Commodity" data-target="#modalSchm" id="schminfo">
                       <i class="fa" style="font-size:22px;color:#0275d8">&#xf05a;</i>
                    </button> -->
                    <datalist id="cmdtlist"></datalist> 
                </div>
		<div class="p-1 input-group">
                    <button type="button" class="btn btn-info btn-sm btn-block" data-toggle="modal" title="Click to view Discount Schemes on selected commodity"   data-target="#modalSchm" id="schminfo"><b>Discount Schemes</b></button>
                </div>
                <div class="p-1 input-group">
                    <input type="text" list="sttnfromlist" class="form-control inptcap" id="txtSttnFrom"    name="txtSttnFrom" onblur="fetchWgonList();" required placeholder='<%=E.EH("Station From")%>' value='<%=si_SttnFrom%>' autocomplete="off"/>
                    <button class="btn btn-info" disabled="true" id="ldnSttnFrom">
                        <span class="spinner-border spinner-border-sm"></span>
                    </button>
                    <datalist id="sttnfromlist"></datalist>                                    
                </div> 
		<div class="p-1 input-group">
                    <input type="text" list="sttntolist"  class="form-control inptcap" id="txtSttnTo"      name="txtSttnTo"    onblur="fetchWgonList();" required placeholder='<%=E.EH("Station To")%>'  value='<%=si_SttnTo%>' autocomplete="off"/>
                    <button class="btn btn-info" disabled="true" id="ldnSttnTo">
                        <span class="spinner-border spinner-border-sm"></span>
                    </button>
                    <datalist id="sttntolist"></datalist>
                 </div>
                <div class="p-1">                
                    <div class="custom-control custom-radio custom-control-inline" style="display: none;">
                        <input type="radio" class="custom-control-input" id="wgnType" name="wgontype"  onclick="chngWgontype('T');fetchRakeCmdt();" value="T" data-toggle="modal" data-target="#wgonModal">
                    </div>
             <!--   <div style="display: inline-block;">
                        <label class="lblcolor"><%=E.EH("Select Wagon Type")%></label>
                    </div>
            -->        <!--<datalist id="wgonlist"></datalist>-->
             <!--   <nav class="navbar navbar-default" role="navigation" style="box-shadow: none;">
                    <div class="navbar-header">
                        <label class="dropdown-toggle lblcolor" data-toggle="dropdown" data-target="#wgonPccList"><%=E.EH("Wagon Type")%></label>
                    </div>
                    <div class="collapse navbar-collapse" id="wgonPccList">
                        <ul class="nav navbar-nav">
                                <li class="dropdown">
                                <div style="height: 250px;overflow-y: auto;">
                                <table class="table table-striped" id="tblwgonlist">
                                      <thead>
                                        <tr>
                                          <th><%=E.EH("Wagon Type")%></th>
                                          <th><%=E.EH("Capacity /Wagon")%></th>
                                          <th><%=E.EH("Rake Size")%></th>
                                        </tr>
                                      </thead>
                                      <tbody></tbody>
                                    </table
                                    </div>
                                   </li>
                        </ul>
                    </div>   
                   </nav> -->
                <button type="button" class="btn btn-info btn-sm btn-block" data-toggle="modal" data-target="#wgonModal" id="btnWgonType"><b>Select Wagon Type</b></button>
                <br/>
                    <input type="text" class="form-control wgntype" id="txtWgonType" required   name="txtWgonType"  value='<%=si_WgonType%>' readonly/>

                </div>
<!--
<fieldset class="border p-2">
   <legend  class="w-auto fc-legend"><%=E.EH("Select Wagon_Category/Wagon Type")%></legend>
   
            <div class="p-1 d-flex ">                
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" class="custom-control-input" id="wgnCtgy" name="wgontype" checked onclick="chngWgontype('C');fetchWgonList();fetchRakeCmdt();" value="C">
                </div>
              <select class="custom-select mr-sm-2 wgonbox" id="selWgon" name="selWgon">
                  <option selected><%=E.EH("Wagon_Category")%></option>
                  <option value="BOXN"><%=E.EH("Open - BOXN")%></option>
                  <option value="BCN"><%=E.EH("Covered - BCN")%></option>
                  <option value="BRN"><%=E.EH("Flat - BRN")%></option>
                  <option value="BTPN"><%=E.EH("Tank - BTPN")%></option>
             </select>
             </div>   
                <div class="p-1">                
                    <div class="custom-control custom-radio custom-control-inline" style="display: inline-block;">
                        <input type="radio" class="custom-control-input" id="wgnType" name="wgontype"  onclick="chngWgontype('T');fetchRakeCmdt();" value="T" data-toggle="modal" data-target="#wgonModal">
                    </div>
                    <div style="display: inline-block;">
                        <label class="lblcolor"><%=E.EH("Wagon Type")%></label>
                    </div>
                </div>
                    <input type="text" class="form-control wgntype" id="txtWgonType" required   name="txtWgonType"  value='<%=si_WgonType%>' readonly/>

              </fieldset>
             <input type="hidden" name="toPage1" id="toPage1" value="FrgtCalc" />
-->                <div class="p-1">
                     <div  class="custom-control custom-control-inline">
                        <select class="form-control form-control-sm" id="selRKPM" name="selRKPM"   onchange="chckRKPM()">
                            <option value="R" selected><%=E.EH("Rake")%></option>
                            <!--<option value="M">Mini Rake</option>-->
                            <option value="P"><%=E.EH("Wagons")%></option>
                        </select>
                        <input class="form-control form-control-sm" type="text" name="txtWgonNumb" id="txtWgonNumb" readOnly="true" size="8" maxlength="2"  value='<%=si_WgonNumb%>'/>
                    </div>
                </div>
                </div>
                </div>
            </div>                            
            <div class="row p-2 d-flex justify-content-center" id="divbut">
                <button class="btn btn-danger btn-submit" id="Calc" onclick="event.preventDefault();onFormSubmit(); return true"><%=E.EH("Calculate Freight")%></button>
            </div>
            </div>
	</div>
        
<!--<div style="inline-block;" id="mydiv">---->

<!--<div class="alert alert-danger" align="center" id="errmsg" style="display:none;"><%=strValues[2][0]%></div>-->
      
<div class="col col-lg-5 col-md-5 col-sm-8 col-12" id="div2ndCol" style="display:none;padding-right: 0px;padding-left: 0px;">
<div class="card border border-info mb-1">
  <div class="card-header"><span class="pull-left"><b><%=E.EH("Freight")%></b></span>
      <span class="pull-right"><a class="linkDownload" href='/RailSAHAY/GG_GnrtFrgtCalcPDF?Param={"sttnfrom":"<%=si_SttnFrom%>","sttnto":"<%=si_SttnTo%>","wagontype":"<%=si_WgonType%>","cmdtname":"<%=si_CmdtName%>","rkpm":"<%=si_RKPM%>","wgonnumbs":"<%=si_WgonNumb%>","wgnType":"<%=si_selWgonCtgy%>","wgnCtgy":"<%=si_optWgontype%>"}'>
    <i class="fas fa-download"></i> <%=E.EH("Download Freight Details")%>
    </a></span>
  </div>
  <div class="card-body text-info">
    <div class="row">
    <div class="col">
    <span>
    <h5 class="card-title"><%=E.EH("Wagon Load")%></h5>
    <p class="card-text">
    <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[0][13]%><br/></strong>
    <%if(!strValues[0][16].equals("")){%>
        <s><i style="font-size:14px" class="fa text-danger">&#xf156;</i>&nbsp;<span class="strike text-danger" id="lblfullWL"></span><br/></s>
        <em class="text-success">
            Discount:<strong> <i style="font-size:13px" class="fa">&#xf156;</i>
            <span class="text-success" id="lblRebtWL"></span></strong>(<%=strValues[0][18]%>%)
        </em>
        <br/>
    <%}%>
    </span>
    <span class="text-info"><em>(<%=E.EH("for")%> <%=si_WgonNumb%> <%=E.EH("wagons")%>)</em></span>
    </p>  
    </div>    
    <div class="col">
    <span id="TranLoad"><h5 class="card-title">Train Load</h5>
    <p class="card-text">
        <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[1][13]%><br/></strong>
        
    <%if(!strValues[1][16].equals("")){%>
        <s><i style="font-size:14px" class="fa text-danger">&#xf156;</i>&nbsp;<span class="strike text-danger" id="lblfullTL"></span><br/></s>
        <em class="text-success">
            Discount: <strong><i style="font-size:13px" class="fa">&#xf156;</i>
            <span class="text-success" id="lblRebtTL"></span></strong>(<%=strValues[1][18]%>%)
        </em>
        <br/>
    </span>
    <%}%>
    <span class="text-info"><em>(<%=E.EH("for full freight train")%>)</em></span>
    </p>  
  </div>
    
  </div>
</div>
</div>

<% 
if(!strValues[0][16].equals("")){
String strCmbRbtList[]  =   null;
String strTemp[]    =   null;
    if(!strValues[0][17].equals("")){
        strCmbRbtList = strValues[0][17].split("#");
        strTemp   =   strCmbRbtList[0].split("@");;

%>
    <div class="card border border-info mb-1">
    <div class="card-header bg-warning blink" id="#Rebates"><b><%=E.EH("Concessions & Rebates")%></b></div>  
    <div class="card-body">
    
    <%if(strTemp.length>2){%>
        <div class="row small">
            <div class="col col-sm-1"></div>
            <div class="col col-sm-5"><b><%=E.EH("Rebate Type")%></b></div>
            <div class="col col-sm-3"><b><%=E.EH("Wagon Load")%></b></div>
            <div class="col col-sm-3"><b><%=E.EH("Train Load")%></b></div>
        </div>
        <hr/>
    <%for(int i=0;i<strCmbRbtList.length;i++){
    String strRbts[]  =   strCmbRbtList[i].split("@");%>
                <div class="row small">
                    <div class="col col-sm-1"><%=i+1%>. </div>
                    <div class="col col-sm-5"><%=strRbts[0]%></div>
                    <div class="col col-sm-3"><%=strRbts[1]%></div>
                    <div class="col col-sm-3"><%=strRbts[2]%></div>
                </div>
                <hr/>
    <%}}else{%> 
        <div class="row small">
            <div class="col col-sm-1"></div>
            <div class="col col-sm-8"><b><%=E.EH("Rebate Type")%></b></div>
            <div class="col col-sm-3"><b><%=E.EH("Wagon Load")%></b></div>
        </div>
        <hr/>
    <%for(int i=0;i<strCmbRbtList.length;i++){
    String strRbts[]  =   strCmbRbtList[i].split("@");%>
                <div class="row small">
                    <div class="col col-sm-1"><%=i+1%>. </div>
                    <div class="col col-sm-8"><%=strRbts[0]%> </div>
                    <div class="col col-sm-3"><%=strRbts[1]%></div>
                </div>
                <hr/>
    <%}}%> 

            </div>
 <!--       <div class="card-body row no-gutters">
            <b><%=E.EH("Total Discount Applicable")%></b>
        </div>
        <%if(!strValues[0][16].equals("")){%>
            <div class="col">
                <%=E.EH("On Loading")%> <%=si_WgonNumb%> <%=E.EH("Wagons")%>:&nbsp;<b><a href="#Rebates" data-trigger="hover" data-toggle="popover" title="<%=strValues[4][1]%>" data-content="<%=strValues[4][0]%>" data-placement="right" id="rebtWLVal"></a></b>
            </div>
        <%}if(!strValues[1][16].equals("")){%>
            <div class="col"><%=E.EH("On  Loading Full Train")%>:&nbsp;<b><a href="#Rebates" data-trigger="hover" data-toggle="popover" title="<%=strValues[6][1]%>" data-content="<%=strValues[6][0]%>" data-placement="right" id="rebtTLVal"></a></b>
            </div>
            <%}%>
            <br/>
 -->       <a href="/RailSAHAY/pages/Policy.jsp" class="btn btn-info" role="button"><i class="fas fa-info-circle"></i>&nbsp;<span>Click to view more Discounts and Incentive Schemes</a>    
</div>
    <%}}else{%>
        <a href="/RailSAHAY/pages/Policy.jsp" class="btn btn-info btn-block" role="button"><i class="fas fa-info-circle"></i>&nbsp;Discounts and Incentive Schemes are available. Click to view.</a>
    <%}%>
 <!--   
    <a class="linkDownload" href='/RailSAHAY/GG_GnrtFrgtCalcPDF?Param={"sttnfrom":"<%=si_SttnFrom%>","sttnto":"<%=si_SttnTo%>","wagontype":"<%=si_WgonType%>","cmdtname":"<%=si_CmdtName%>","rkpm":"<%=si_RKPM%>","wgonnumbs":"<%=si_WgonNumb%>","wgnType":"<%=si_selWgonCtgy%>","wgnCtgy":"<%=si_optWgontype%>"}'>
    <span><i class="fas fa-download"></i> <%=E.EH("Download Freight Details")%></span>
    </a>
 -->   
<div class="table-responsive mb-1">
  <table class="table mb-1">
    <thead>
    </thead>
    <tbody>
      <tr class="accordion-toggle collapsed" id="accordion1" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">
            <td class="expand-button"></td>
            <td colspan="3"><b><%=E.EH("Details of Charges")%></b></td>
</tr>
<tr class="hide-table-padding">
<td></td>
<td colspan="3">
<div id="collapseOne1" class="collapse in p-3">
  <div class="row .table-responsive-md">

<table id="tblFrgtCalcDtls" class="table table-striped" name="tblFrgtCalcDtls">
<thead class="thead-light">
	<tr>
		<th scope="col"><%=E.EH("Charge Name")%></th>
		<th scope="col" style="text-align:right;"><%=E.EH("Wagon Load")%></th>
		<th scope="col" style="text-align:right;"><%=E.EH("Train Load")%></th>
	</tr>
</thead>
<tbody>
		<tr>
			<td style="text-align:left;"><%=E.EH("Charged Rate Class")%>:</td>
			<td style="text-align:right;"><%=strValues[0][1]%></td>
			<td style="text-align:right;"><%=strValues[1][1]%></td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Basic Freight Rate (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][2]%></td>
			<td style="text-align:right;"><%=strValues[1][2]%></td>
		</tr>
		<tr>
			<td style="text-align:left;">*<%=E.EH("Surcharge (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][3]%>
				<%if(!strValues[0][3].equals("0.00") && !strValues[0][3].equals("")){%>	
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[7][1]%>" 
                                            data-content="<%=strValues[7][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
				<%}%>
			</td>
			<td style="text-align:right;"><%=strValues[1][3]%>
				<%if(!strValues[1][3].equals("0.00") && !strValues[1][3].equals("")){%>
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[8][1]%>" 
                                            data-content="<%=strValues[8][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
				<%}%>
			</td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Normal Tariff Rate (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][4]%></td>
			<td style="text-align:right;"><%=strValues[1][4]%></td>
		</tr>
		<tr>
			<td style="text-align:left;">**<%=E.EH("Other Charge (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][5]%>
				<%if(!strValues[0][5].equals("0.00") && !strValues[0][5].equals("")){%>
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[3][1]%>" 
                                            data-content="<%=strValues[3][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
                                <%}%>
			</td>
			<td style="text-align:right;"><%=strValues[1][5]%>
				<%if(!strValues[1][5].equals("0.00") && !strValues[1][5].equals("")){%>
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[5][1]%>" 
                                            data-content="<%=strValues[5][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
				 <%}%>
			</td>
		</tr>
		<tr>
			<td style="text-align:left;">***<%=E.EH("Rebate (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][6]%>
				<%if(!strValues[0][6].equals("0.00") && !strValues[0][6].equals("")){%>
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[4][1]%>" 
                                            data-content="<%=strValues[4][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
				 <%}%>
			</td>
			<td style="text-align:right;"><%=strValues[1][6]%>
				<%if(!strValues[1][6].equals("0.00") && !strValues[1][6].equals("")){%>
                                    <div style="display:inline">
                                        <i class="fa"
                                            style="font-size:18px;color:blue" 
                                            class="fa" 
                                            data-trigger="hover" 
                                            data-toggle="popover" 
                                            title="<%=strValues[6][1]%>" 
                                            data-content="<%=strValues[6][0]%>" 
                                        data-placement="right" >&#xf05a;</i>
				    </div>
				 <%}%>
			</td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Freight Rate (Rs./T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][7]%></td>
			<td style="text-align:right;"><%=strValues[1][7]%></td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Total Chargeable Weight (T)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][0]%></td>
			<td style="text-align:right;"><%=strValues[1][0]%></td>
		</tr>		
		<tr>
			<td style="text-align:left;"><%=E.EH("Total Freight  (Rs.)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][8]%></td>
			<td style="text-align:right;"><%=strValues[1][8]%></td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Other Lumpsum Charges (Rs.)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][9]%></td>
			<td style="text-align:right;"><%=strValues[1][9]%></td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Other Lumpsum Rebate (Rs.)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][10]%></td>
			<td style="text-align:right;"><%=strValues[1][10]%></td>
		</tr>
		<tr>
			<td style="text-align:left;"><%=E.EH("Indicative Freight (Rs.)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][11]%></td>
			<td style="text-align:right;"><%=strValues[1][11]%></td>
		</tr>
		<tr>
			<td style="text-align:left;">+<%=E.EH("GST @5% (if app.) (Rs.)")%>:</td>
			<td style="text-align:right;"><%=strValues[0][12]%></td>
			<td style="text-align:right;"><%=strValues[1][12]%></td>
		</tr>
	</tbody>
</table>
  </div>
</div></td>
</tr>
    </tbody>
  </table>
</div>
        
<div class="card border border-info mb-1">
  <div class="card-header"><b><%=E.EH("Freight Rate/Tonne")%></b></div>
  <div class="card-body"><b><%=E.EH("Wagon Load")%></b>&nbsp;(Class-<%=strPreFrgtCalc[1]%>):&nbsp;<%=strPreFrgtCalc[3]%> <%=E.EH("Rs/Tonne")%></div>
  <div class="card-body"><b><%=E.EH("Train Load")%></b>&nbsp;(Class-<%=strPreFrgtCalc[0]%>):&nbsp;<%=strPreFrgtCalc[2]%> <%=E.EH("Rs/Tonne")%></div>
</div>

</div>
<!--<div class="alert alert-danger" align="center" id="errmsg" style="display:none;"><%=strValues[2][0]%></div>-->
<div class="col col-lg-4 col-md-4 col-sm-6 col-12" id="div3rdCol" style="display:none;padding-right: 0px;padding-left: 0px;">

<div class="card border border-info mb-1" id="divAltRout" style="padding-right: 0px;padding-left: 0px;">
  <div class="card-header"><b><%=E.EH("VIKALP (Alternate Routes)")%></b></div>
  <div class="card-body text-info">  
    <div style="height: 130px;overflow-y: auto;">
        <table class="table table-striped table-condensed" id="tblAltRoute"><thead></thead><tbody></tbody></table>
    </div>
    </div>
</div>        
        
<div class="card border border-info mb-1"  style="padding-right: 0px;padding-left: 0px;">
  <div class="card-header"><b><%=E.EH("Route")%>&nbsp;(<%=strPreFrgtCalc[4]%>)</b>
  <img src="/RailSAHAY/resources/images/mapicon2.png" data-toggle="modal" data-target="#mapModal" onmouseover="this.style.cursor='pointer';" alt='<%=E.EH("Show Route on Map")%>' title='<%=E.EH("Show Route on Map")%>'>
  </div>
<!--  <div class="card-body">
				<div class="border lbldata" style="overflow:scroll;overflow-x:hidden;max-height:120px;" id="txtRoute">
                                    <%=strPreFrgtCalc[5]%>
                                </div>
    </div>
 --> <div class="card-body fc-card-body"><b><%=E.EH("Distance")%>: &nbsp;</b><%=strPreFrgtCalc[7]%> <%=E.EH("Kms")%></div>
  <div class="card-body fc-card-body"><b><%=E.EH("Carrying Capacity Info")%>: &nbsp;</b><%=strPreFrgtCalc[6]%> <%=E.EH("Tonnes")%></div>
  <div class="card-body fc-card-body"><b><%=E.EH("Permissible Carrying Capacity")%>: &nbsp;</b><%=strPreFrgtCalc[9]%> <%=E.EH("Tonnes/Wagon")%></div>
  <div class="card-body fc-card-body"><b><%=E.EH("Expected Travel Time")%>: &nbsp;</b><%=strPreFrgtCalc[11]%> <%=E.EH("Hrs")%></div>
</div>
  </div>
</div>
</div>
</div>

</div>
</div>
</div>
<div id="othrDtls">
<div class="row mb-1">
	<div class="col col-lg-3 col-md-3 col-sm-6 col-12 padding-0 mb-1">
        </div>
        <div class="col col-lg-5 col-md-5 col-sm-8 col-12 padding-0 mb-1">
            <div id="divTrmlList"></div>
        </div>        
        <div class="col col-lg-4 col-md-4 col-sm-6 col-12 padding-0 mb-1" id="div3rdCol">
            <div id="divTrmlList1"></div>
        </div>
</div>
<br/>    
<div class="row" style="margin-left:5px;margin-right:5px;">
	<div class="col col-lg-3 col-md-3 col-sm-6 col-12 padding-2">
        </div>
<div class="col-lg-9 col-md-9 col-sm-6 col-12 padding-2">
	<%if(!strValues[0][14].equals("")){%>
	<p style="text-align:justify;color:#0059b3;font-size:12px;font-weight:600;">
        <b><%=E.EH("Indicative Freight (incl. GST) for Wagon Load (in words)")%>:</b>&nbsp;<%=strValues[0][14]%><br/>
	<%}if(!strValues[1][14].equals("")){%>
	<b><%=E.EH("Indicative Freight (incl. GST) for Train Load (in words)")%>:</b> &nbsp;<%=strValues[1][14]%></font><br/>
	<%}%>
	</p>
	+<%=E.EH("GST if applicable, will be leivied as per extant rules")%>.<br/>
	<font size="1">
		<%if(!strValues[9][0].equals("")){%>
		*<b><%=E.EH("Surcharge includes")%>:</b> &nbsp;<%=strValues[9][0]%><br/>
		<%}if(!strValues[10][0].equals("")){%>
		**<b><%=E.EH("Other Charge includes")%>:</b> &nbsp;<%=strValues[10][0]%><br/>
		<%}if(!strValues[11][0].equals("")){%>
		***<b><%=E.EH("Rebate includes")%>:</b> &nbsp;<%=strValues[11][0]%><br/>
		<%}%>
		<%if(!strValues[0][15].equals("")){%>
		<b><%=E.EH("Wagon Load Remarks")%>:</b> &nbsp;<%=strValues[0][15]%><br/>
		<%}if(!strValues[1][15].equals("")){%>
		<b><%=E.EH("Train Load Remarks")%>:</b> &nbsp;<%=strValues[1][15]%><br/>
		<%}%>
	</font>
    </div>
</div>
</div>

<input type="hidden" id="hidRKPM" value="<%=si_RKPM%>"/>
<input type="hidden" id="hidWgonNumb" value="<%=si_RakeSize%>"/>
<input type="hidden" id="frgtValues" value="<%=strValues[0][13]%>"/>
</form>
</div>
         <div class="modal fade" id="wgonModal">
            <div class="modal-dialog modal-dialog-centered modal-lg">
              <div class="modal-content">
              
                <!-- Modal Header -->
                <div class="modal-header">
                  <h5 class="modal-title">Select Wagon</h5><h5 class="modal-title" id="hwgntype"></h5>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body">
                    <div class="container">
                              <div class="row">
                                  <div class="col-lg-12 ml-1">         
                                        <div class="mt-2 mb-3 text-center small" id="divColrInd" align="right">
                                                      <span class="mr-1">
                                                        <svg class="svg-inline--fa fa-circle fa-w-16 text-primary-orig" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-primary"></i> --> <span id="spnCvdCont" style="font-weight:600;"></span> Covered Wagon(s) 
                                                      </span>
                                                      <span class="mr-1">
                                                        <svg class="svg-inline--fa fa-circle fa-w-16 text-success" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8z"></path></svg><!-- <i class="fas fa-circle text-success"></i> --> <span id="spnOpnCont" style="font-weight:600;"></span> Open Wagon(s) 
                                                      </span>	                     
                                         </div>
                                 </div>
                                <div class="row" id="divWgonTypeList">                            
                                </div>
                            </div>
                    </div>
                </div>
                
                <!-- Modal footer -->
                <div class="modal-footer">
                  <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>-->
                </div>
                
              </div>
            </div>
        </div>

 <div class="modal fade" id="mapModal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="mapHdr" style="font-size:12px;font-weight:600;padding:10px;"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSpecLocn" style="width:100%;height:100vh;z-index:10;position:fixed;top:0;left:0;"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
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
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                
              </div>
            </div>
        </div>


<div class"container" style="padding:10px;">
<div class="card border border-info">
    <div class="card-header"><b><%=E.EH("Information")%></b></div>
    <div class="card-body text-info">  
        <b><%=E.EH("Peak Season")%>:</b> <%=E.EH("Apr-Jun, Oct-Mar")%><br/>
        <b><%=E.EH("Lean Season")%>:</b> <%=E.EH("Jul-Sep")%><br/>
        *<%=E.EH("All Concessions/ Discounts on Commodity/ Routes are as per the Rate Circulars issued/ posted time to time on www.indianrailways.gov.in")%><br/>
        <font color="red"><b><%=E.EH("Disclaimer")%>:</b> <%=E.EH("The Freight Calculator only provides indicative and approximate freight to be charged")%>. <%=E.EH("The Actual Chargeable Freight would depend on number of other factors including type of wagons, actual weight of cargo, exact classification of commodity etc")%>. <%=E.EH(" It is hereby informed that the information provided herein is indicative in nature and cannot be used as evidence in any Court of Law or cited as evidence for RTI")%>.</font><br/>
    </div>
</div>
</div>

<%@ include file="/pages/GG_Footer.jspf" %>

         <div class="modal fade" id="modalSchm">
            <div class="modal-dialog modal-dialog-centered mw-100 w-75">
              <div class="modal-content">
              
                <!-- Modal Header -->
                <div class="modal-header">
                  <h5 id="hdSchm">Schemes</h5>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body">
                    <label><b>Please Note:</b> Latest Rate Circular, wherever available, should be read along with the Master Rate Circular.</label>
                    <div class="container">
                    
                                <div class="row" id="divSchmList">                            
                                </div>
                                
                        
                </div><!-- row end-->
            </div><!--model body end -->
                
                <!-- Modal footer -->
                <div class="modal-footer">
                  <center><label><b>Legal Disclaimer:</b> While all efforts has been made for accuracy and updation, these Schemes are subject to change as per the instructions of Railway Board. For details/errors noticed, please contact the Railway Official at the nearest Goodshed.</label></center>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                
              </div>
            </div>
        </div>


            <script>
                var str='';
//                for (var i=0; i < aSttn.length;++i){
//                str += '<option value="'+aSttn[i]+'" />'; // Helplist for station
//                }
//                var my_list=document.getElementById("sttnfromlist");
//                my_list.innerHTML = str;
                
//                var my_list=document.getElementById("sttntolist");
//                my_list.innerHTML = str;
                
//                str="";            
                
//                for (var i=0; i < cmdtname.length;++i){
//                str += '<option value="'+cmdtname[i]+'" />'; // Commodity help list
//                }
 //               var my_list=document.getElementById("cmdtlist");
//                my_list.innerHTML = str;  
                                
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

		function chckRKPM()
		{
                        var valueSelected = $("#selRKPM").val();
			if(valueSelected=="R" || valueSelected=="M")
			{
				$('#txtWgonNumb').attr('readOnly',true);
                                $('#txtWgonNumb').val(<%=strPreFrgtCalc[8]%>);
                                //if($('#hidWgonNumb').val() != "")
                                  //  $('#txtWgonNumb').val($('#hidWgonNumb').val());
			}
			if(valueSelected=="P")
			{
				$('#txtWgonNumb').attr('readOnly',false);
				$('#txtWgonNumb').focus();
			}
		}                
                                
                $(document).ready(function() 
                {
                    document.getElementById("ldnSttnFrom").style.display  = 'none';
                    document.getElementById("ldnSttnTo").style.display  = 'none';
                    
                    $('[data-toggle="popover"]').popover({
                        html: true,
                        animation: false,
                        trigger : 'hover',
                        placement: "right"
                                });		                                    
                    
                    if($("#frgtValues").val().trim() != "" && $("#frgtValues").val().trim() != "0.00")
                    {
                        $("#div2ndCol").css("display", "inline");
                        $("#div3rdCol").css("display", "inline");
                        $("#othrDtls").css("display", "inline");
                        
                        <%if(!strValues[0][16].equals("")){   
                            strValues[0][16]=""+(long)Double.parseDouble(strValues[0][16]); %>
                            var n = numberWithCommas('<%=strValues[0][16]%>');                        
                            /*n=n.substring(0,n.indexOf(".")+3);*/
                            $('#rebtWLVal').text("Rs. "+n);
                            $('#lblRebtWL').text(n);
                            var diff   =   flchk('<%=strValues[0][13]%>',n);
                            $('#lblfullWL').text(numberWithCommas(diff));
                        <%}%>
                        <%if(!strValues[1][16].equals("")){ strValues[1][16]=""+(long)Double.parseDouble(strValues[1][16]); %>
                            var n = numberWithCommas('<%=strValues[1][16]%>');                        
/*                            n=n.substring(0,n.indexOf(".")+3);*/
                            $('#rebtTLVal').text("Rs. "+n);
                            $('#lblRebtTL').text(n);
                            var diff1   =   flchk('<%=strValues[1][13]%>',n);
                            $('#lblfullTL').text(numberWithCommas(diff1));
                        <%}%>
                    }

                    $("#selRKPM").val($("#hidRKPM").val());
                    chckRKPM();                 
                    
                    if($("#hidRKPM").val() == "P")
                    {
                        $('th:nth-child(3)').remove();
                        $('td:nth-child(3)').remove();
                        $("#TranLoad").css("display", "none");                        
                    }
                    /*
                    $("#selWgon").val('<%=si_selWgonCtgy%>');
                    $('input[name="wgontype"]:checked').val('<%=si_optWgontype%>');
                    if($('input[name="wgontype"]:checked').val() == "T")
                    {
                        document.getElementById("wgnType").checked = true;
                        chngWgontype('T');
                    }
                    else
                    {
                        document.getElementById("wgnCtgy").checked = true;
                        chngWgontype('C');
                    }
                    
                    if($("#frgtValues").val().trim() != "")
                    {
                        $("#div2ndCol").css("display", "inline");
                        $("#div3rdCol").css("display", "inline");
                        $("#othrDtls").css("display", "inline");
                    }
                    */
                  <%if(!strValues[13][0].equals("")){%>	
                  try
                  {
                    var obj= JSON.parse('<%=strValues[13][0]%>');
                        if(obj.Data.length>0)
                        {
                            //document.getElementById('divAltRout').style.display =   "block";
                            for(var i=0;i<obj.Data.length;i++)
                            {
                                    var fromsttn  =   obj.Data[i].SttnFrom;
                                    var tosttn    =   obj.Data[i].SttnTo;
                                    $('#tblAltRoute').append("<tr><td><a href='#' onclick=\"getAltRout('"+fromsttn+"','"+tosttn+"');\">"+fromsttn+"  <i class='fas fa-exchange-alt'></i>  "+tosttn+"</a></td></tr>");                                
                            }
                        }
                        else
                            $('#tblAltRoute').append("<tr><td><i class='fas fa-info-circle'></i> No alternate route exists for selected source and destination terminals</td></tr>");                                
                            
                       //     document.getElementById('divAltRout').style.display =   "none";
                  }
                  catch(err){}
                <%}%>
                    
                    
                    var sttnfrom	=	document.getElementById('txtSttnFrom').value.trim();
                    sttnfrom	        =	sttnfrom.substring(sttnfrom.lastIndexOf("-")+1,sttnfrom.lastIndexOf("(")).toUpperCase().trim();	
                    var sttnto	        =	document.getElementById('txtSttnTo').value.trim();
                    sttnto		=	sttnto.substring(sttnto.lastIndexOf("-")+1,sttnto.lastIndexOf("(")).toUpperCase().trim();
                    
                    var dtls		=	"Route From " + sttnfrom + " To " + sttnto;     
                    $("#mapHdr").text(dtls);
                    
                    
                  <%if(!si_SttnFrom.equals("")){%>	
                     if($("#frgtValues").val().trim() == "")
                    {
                        noDataFound();
                    }
                    else {
                        fetchTrmlList(sttnfrom);
                        fetchTrmlList1(sttnto);
                    }
                <%}%>
                
                
                  $( ".card" ).hover(
                  function() {
                    $(this).addClass('shadow-lg').css('cursor', 'pointer'); 
                  }, function() {
                    $(this).removeClass('shadow-lg');
                  }
                );
                
                fetchCmdtList();
                fetchSttnFrom();
                if($("#txtCmdtName").val() != "")
                {
                    fetchSchmList();
                    fetchRakeCmdt();
                }
                else                
                {
                    document.getElementById("btnWgonType").disabled  =   true;  
                    document.getElementById("schminfo").disabled  =   true;  
                }
                
                
                }); //End of Ready               
                
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
                             '               <th rowspan="2" class="align-middle" class="w-2"><%=E.EH("SR")%>.</th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-28"><%=E.EH("Scheme")%></th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-35"><%=E.EH("Master Rate Circular")%></th>'+
                             '               <th colspan="2" style="text-align:center;" class="w-35"><%=E.EH("Latest Rate Circular")%></th>'+
                             '       </tr>'+
                             '       <tr>'+
                             '               <th style="text-align:center;" class="w-3"><%=E.EH("Code")%></th>'+
                             '               <th style="text-align:center;" class="w-25"><%=E.EH("Description")%></th>'+
                             '               <th style="text-align:center;" class="w-15"><%=E.EH("No.")%></th>'+
                             '              <th style="text-align:center;" class="w-20"><%=E.EH("Date")%></th>'+
                             '               <th style="text-align:center;" class="w-15"><%=E.EH("No.")%></th>'+
                             '               <th style="text-align:center;" class="w-20"><%=E.EH("Date")%></th>'+
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
                            $("#cmdtlist").empty();                         
                            //alert( $('#sttnfromlist option').length);
                            var my_list=document.getElementById("cmdtlist");
                            my_list.innerHTML = obj.Data;//str;
                         //   document.getElementById("ldnSttnFrom").style.display = "none";
                            //$('#txtSttnFrom').val('');
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
                        {
                            $("#sttnfromlist").empty();                         
                            //alert( $('#sttnfromlist option').length);
                            var my_list=document.getElementById("sttnfromlist");
                            my_list.innerHTML = obj.Data;//str;
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
                            $("#sttntolist").empty();
                            var my_list=document.getElementById("sttntolist");
                           my_list.innerHTML = obj.Data;//str;                           
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
<%--
		if(i==8)
		{
			htmlstr+='</div>';
			htmlstr+='<a href="javascript:void(0)" class="card-link1" data-toggle="collapse" data-target="#wgontype" id="aToggle">Show More..</a><br/>';
			htmlstr+='<div id="wgontype" class="collapse"><div class="row" style="margin-top:14px;">';
		}
                --%>

		htmlstr+='<div class="col-lg-4 col-md-5 col-sm-7"><div class="card wgon-card" onclick="setPrefWgonType(\''+wgontype+'\',\''+wgondesc+'\');"><img class="card-img-top img-responsive img-fluid" src="/RailSAHAY/resources/images/wagons/'+wgontype+'.jpg" alt="Wagon image" style="margin-bottom:10px;"><div class="card-head"><h2 class="card_title">'+wgontype+'</h2>';

		if(brak>0)
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/><p class="paramlbl">Loading Preference<span class="paramval">'+brak+'%</span></p><hr/><p class="paramlbl">Stock Available';
		else
			htmlstr+='<p class="card_text">'+wgondesc+'</p></div><div class="card-desc"><p class="paramlbl">Tare<span class="paramval">'+tare+'</span> Tonnes</p><hr/><p class="paramlbl">Carrying Capacity<span class="paramval">'+cc+'</span> Tonnes</p><hr/><p class="paramlbl">Stock Available';

			if(cotype=="C")
				htmlstr+='<span class="paramval badge badge-blue text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else if(cotype=="O")
				htmlstr+='<span class="paramval badge badge-success text-white float-right">'+wgoncont+'</span></p></div></div></div>';
			else
				htmlstr+='<span class="paramval badge badge-secondary text-white float-right">'+wgoncont+'</span></p></div></div></div>';
	}
        <%--
	if(i>8)
	{
		htmlstr+='</div></div>';
	}
        --%>
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
}

            </script>            
	  </body>
	</html>
