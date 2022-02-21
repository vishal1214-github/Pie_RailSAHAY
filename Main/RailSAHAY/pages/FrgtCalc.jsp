<%@ page import="util.*" %> 
<%@ include file="/pages/GG_Header.jspf" %>   
<% strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FREIGHT CALCULATOR","/RailSAHAY/pages/FrgtCalc.jsp",UserDevice,Browser); %>
    <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
    <link rel="stylesheet" href="/RailSAHAY/resources/css/trmlfcty.css"/>
    <link href="/RailSAHAY/resources/css/findwgon.css" rel="stylesheet" />
    <script src="/RailSAHAY/resources/js/trmlfctyajax.js"></script>
    <script src="/RailSAHAY/resources/js/trmlutil.js"></script>
    <link rel="stylesheet" href="/RailSAHAY/resources/css/frgtcalc.css"/>
    <link rel="stylesheet" href="/RailSAHAY/resources/css/jquery1.12.1-ui.css"/>
    <script src="/RailSAHAY/resources/js/jquery1.12.1-ui.min.js"></script>
    <script src="/RailSAHAY/resources/js/frgtcalc.js"></script>

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
String strPreFrgtCalc[] = new String[17];
for(int i=0;i<17;i++)
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
    System.out.println("strPreFrgtCalc_legth"+ strPreFrgtCalc.length);
    System.out.println("Minirake Size"+strPreFrgtCalc[10]);
    if(si_WgonNumb.equals(""))
        si_WgonNumb =   strPreFrgtCalc[8];
    else if(si_RKPM.equals("M"))
        si_WgonNumb =   strPreFrgtCalc[10];
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
      <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark"></nav>
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">
            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:2px;">
                <h3 class="mb-2" data-aos="fade-up" data-aos-delay="100"><%=E.EH("EXPECTED FREIGHT CHARGES")%></h3>
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
                    <input type="text" list="cmdtlist"  class="form-control inptcap name" id="txtCmdtName" name="txtCmdtName" onblur="clrscreen();fetchSchmList();fetchSttnFrom();fetchWgonList();fetchRakeCmdt();" required placeholder='<%=E.EH("Commodity Name")%>'  value='<%=si_CmdtName%>' autocomplete="off"/>
                    <datalist id="cmdtlist"></datalist> 
                </div>
		<div class="p-1 input-group">
                    <button type="button" class="btn btn-light btn-sm btn-block" data-toggle="modal" title="Click to view Discount Schemes on selected commodity"   data-target="#modalSchm" id="schminfo"><b>Check Discount Schemes</b></button>
                </div>
                <div class="p-1 input-group">
                    <input type="text" list="sttnfromlist" class="form-control inptcap name" id="txtSttnFrom"    name="txtSttnFrom" onblur="fetchWgonList();" required placeholder='<%=E.EH("Station From")%>' value='<%=si_SttnFrom%>' autocomplete="off"/>
                    <button class="btn btn-info" disabled="true" id="ldnSttnFrom">
                        <span class="spinner-border spinner-border-sm"></span>
                    </button>
                   <!-- <button class="btn btn-light btn-sm"  id="infoSttnFrom" data-toggle="modal" data-target="#modalSttnFrom">
                        <span class="fa fa-info-circle"></span>
                    </button>-->
                    <datalist id="sttnfromlist"></datalist>                                    
                </div> 
		<div class="p-1 input-group">
                    <input type="text" list="sttntolist"  class="form-control inptcap name" id="txtSttnTo"      name="txtSttnTo"    onblur="fetchWgonList();" required placeholder='<%=E.EH("Station To")%>'  value='<%=si_SttnTo%>' autocomplete="off"/>
                    <button class="btn btn-info" disabled="true" id="ldnSttnTo">
                        <span class="spinner-border spinner-border-sm"></span>
                    </button>
                    <datalist id="sttntolist"></datalist>
                 </div>
                <div class="p-1">                
                    <div class="custom-control custom-radio custom-control-inline" style="display: none;">
                        <input type="radio" class="custom-control-input" id="wgnType" name="wgontype"  onclick="chngWgontype('T');fetchRakeCmdt();" value="T" data-toggle="modal" data-target="#wgonModal">
                    </div>
                    <button type="button" class="btn btn-light btn-sm btn-block" data-toggle="modal" data-target="#wgonModal" id="btnWgonType"><b>Select Wagon Type</b></button>
                                <br/>
                    <input type="text" class="form-control wgntype" id="txtWgonType" required   name="txtWgonType"  value='<%=si_WgonType%>' readonly/>
                </div>
                <div class="p-1">
                     <div  class="custom-control custom-control-inline">
                        <select class="form-control form-control-sm" id="selRKPM" name="selRKPM"   onchange="chckRKPM()">
                            <option value="R" selected><%=E.EH("Rake")%></option>
                            <option value="M">Mini Rake</option>
                            <option value="P"><%=E.EH("Wagons")%></option>
                        </select>
                        <input class="form-control form-control-sm inptcap numb" type="text" name="txtWgonNumb" id="txtWgonNumb" readOnly="true" size="8" maxlength="2"  value='<%=si_WgonNumb%>'/>
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
    <%if(strPreFrgtCalc[9].equals("1.00")){%>
        <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[0][13]%>&nbsp;/Tonne<br/></strong>
    <%}else{%>
    <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[0][13]%><br/></strong>
    <%}%>
    <%if(!strValues[0][16].equals("")){%>
        <s><i style="font-size:14px" class="fa text-danger">&#xf156;</i>&nbsp;<span class="strike text-danger" id="lblfullWL"></span><br/></s>
        <em class="text-success">
            Discount:<strong> <i style="font-size:13px" class="fa">&#xf156;</i>
            <span class="text-success" id="lblRebtWL"></span></strong>(<%=strValues[0][18]%>%)
        </em>
        <br/>
    <%}%>
    </span>
    
<%if(!strPreFrgtCalc[9].equals("1.00")){%>  
    <span class="text-info"><em>(<%=E.EH("for")%> <%=si_WgonNumb%> <%=E.EH("wagons")%>)</em></span>
    <%}%>
    </p>  
    </div>    
    <div class="col">
    <span id="TranLoad"><h5 class="card-title">Train Load</h5>
    <p class="card-text">
    <%if(strPreFrgtCalc[9].equals("1.00")){%>
        <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[1][13]%>&nbsp;/Tonne<br/></strong>
    <%}else{%>
        <strong><i style="font-size:15px" class="fa">&#xf156;</i>&nbsp; <%=strValues[1][13]%><br/></strong>
    <%}%>
    <%if(!strValues[1][16].equals("")){%>
        <s><i style="font-size:14px" class="fa text-danger">&#xf156;</i>&nbsp;<span class="strike text-danger" id="lblfullTL"></span><br/></s>
        <em class="text-success">
            Discount: <strong><i style="font-size:13px" class="fa">&#xf156;</i>
            <span class="text-success" id="lblRebtTL"></span></strong>(<%=strValues[1][18]%>%)
        </em>
        <br/>
    </span>
    <%}%>
<%if(!strPreFrgtCalc[9].equals("1.00") && strPreFrgtCalc[13].equals("F")){%>  
    <span class="text-info"><em>(<%=E.EH("for full freight train")%>)</em></span>
<%}else if(strPreFrgtCalc[13].equals("RakeSize")|| strPreFrgtCalc[13].equals("MinimumRakeSize")){%>  
    <span class="text-info"><em>(<%=E.EH("for full rake load")%>)</em></span>
<%}else if(strPreFrgtCalc[13].equals("MiniRakeSize")){%>  
    <span class="text-info"><em>(<%=E.EH("for mini rake load")%>)</em></span>
<%}%>
    </p>  
  </div>
  </div>
</div>
<%if(strPreFrgtCalc[9].equals("1.00")){%>      
	<p class="colrchng"><b>Note: Per tonne rate as mentioned above is inclusive of applicable Sur Charges/Other Charges and Rebates. Actual freight will be based on actual tonnage loaded.</b></p>  
<%}%>
<%if(strPreFrgtCalc[13].equals("MiniRakeSize")){%>      
	<p class="colrchng"><b>Note: Train Load will be granted on loading of minimum <%=si_WgonNumb%> wagons. However, Mini Rake demand shall be registered for atleast <%=strPreFrgtCalc[10]%> wagons.</b></p>  
<%}%>
<%if(strPreFrgtCalc[13].equals("MinimumRakeSize")){%>      
	<p class="colrchng"><b>Note: Train Load will be granted on loading of minimum <%=strPreFrgtCalc[12]%> wagons. However, Rake demand shall be registered for atleast <%=strPreFrgtCalc[8]%> wagons.</b></p>  
<%}%>
</div>
<% 
if(!strValues[0][16].equals("")){
String strCmbRbtList[]  =   null;
String strTemp[]    =   null;
    if(!strValues[0][17].equals("")){
        strCmbRbtList = strValues[0][17].split("#");
        strTemp   =   strCmbRbtList[0].split("@");
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
            <a href="/RailSAHAY/pages/Policy.jsp" class="btn btn-info" role="button"><i class="fas fa-info-circle"></i>&nbsp;<span>Click to view more Discounts and Incentive Schemes</a>    
</div>
    <%}}else{%>
        <a href="/RailSAHAY/pages/Policy.jsp" class="btn btn-info btn-block" role="button"><i class="fas fa-info-circle"></i>&nbsp;Discounts and Incentive Schemes are available. Click to view.</a>
    <%}%>
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

</div><div class="col col-lg-4 col-md-4 col-sm-6 col-12" id="div3rdCol" style="display:none;padding-right: 0px;padding-left: 0px;">

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

 <div class="modal fade" id="modalSttnFrom">
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="mapHdr" style="font-size:12px;font-weight:600;padding:10px;"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSttnFrom"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"><%=E.EH("Close")%></button>
        </div>
        
      </div>
    </div>
  </div>


 <div class="modal fade" id="mapSttnTo">
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h5 class="modal-title" id="mapHdr" style="font-size:12px;font-weight:600;padding:10px;"></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">                
                <div id="divSttnTo"></div>
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
	  </body>
	</html>
        
        <script>        
                        
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
                    
                   //if($("#hidRKPM").val() == "P")
                   //{
                   
                    <%if(strValues[1][13].equals("")){%>
                        $('th:nth-child(3)').remove();
                        $('td:nth-child(3)').remove();
                        $("#TranLoad").css("display", "none");  
                    <%}%>
                    //}
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
                
                
		function chckRKPM()
		{
                        var valueSelected = $("#selRKPM").val();
			if(valueSelected=="R")
			{
				$('#txtWgonNumb').attr('readOnly',true);
                                $('#txtWgonNumb').val(<%=strPreFrgtCalc[8]%>);
                                //if($('#hidWgonNumb').val() != "")
                                  //  $('#txtWgonNumb').val($('#hidWgonNumb').val());
			}
                        if(valueSelected=="M")
			{
				$('#txtWgonNumb').attr('readOnly',true);
                                $('#txtWgonNumb').val(<%=strPreFrgtCalc[10]%>);
                                //if($('#hidWgonNumb').val() != "")
                                  //  $('#txtWgonNumb').val($('#hidWgonNumb').val());
			}
			if(valueSelected=="P")
			{
				$('#txtWgonNumb').attr('readOnly',false);
				$('#txtWgonNumb').focus();
			}
		} 
                
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

   
        </script>
