<%@ page import="util.GenFunc.GG_SahayUtil" %>
<%
    String strCustId="";
    String strEmail="";
    String strCustName="";
    String strMoblNumb="";
    String strPymtStts="";
    String strErorMesg="";
    
    String FOISTrxnId="";
    String BankTrxnId="";
    String BankTrxnTime="";

    try
    {
        strCustId=(String)session.getAttribute("custId");
        strEmail=(String)session.getAttribute("email");
        strCustName=(String)session.getAttribute("name");
        strMoblNumb=(String)session.getAttribute("mobile");
        if(strCustId==null)
        {
            strCustId="";
            strEmail="";
            strCustName="FBD USER";
            strMoblNumb="";
        }
    }
    catch(Exception e)
    {
        strCustId="";
        strEmail="";
        strCustName="FBD USER";
        session.setAttribute("custId","FBD USER");
        strMoblNumb="";
    }
    
    try
    {
            strPymtStts=(String)request.getParameter("PymtStts").trim();
            strErorMesg=(String)request.getParameter("ErorMesg").trim();
    }
    catch(Exception e)
    {
        strPymtStts="";
        strErorMesg="";
    }
    if(strPymtStts.equals("SUCCESS"))
    {
        FOISTrxnId=(String)request.getParameter("FOISTrxnId");
        BankTrxnId=(String)request.getParameter("BankTrxnId");
        BankTrxnTime=(String)request.getParameter("BankTrxnTime");
    }
    GG_SahayUtil obj=new GG_SahayUtil();
    String RfrnId="";
    
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Gati-Shakti Multimodal Cargo Terminal</title>
<!-- HTML5 Shim and Respond.js IE9 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="Gradient Able Bootstrap admin template made using Bootstrap 4. The starter version of Gradient Able is completely free for personal project." />
<meta name="keywords" content="free dashboard template, free admin, free bootstrap template, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
<meta name="author" content="codedthemes">

<script type="text/javascript" src="/RailSAHAY/resources/js/cute-alert.js"></script>
<link rel="stylesheet" href="/RailSAHAY/resources/css/sticky-alert.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/cute-alert.css">
<!-- Favicon icon -->
<link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
<!-- Google font-->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/bootstrap.min.css">
<!-- themify-icons line icon -->
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/themify-icons.css">
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/font-awesome.min.css" />
<!-- ico font -->
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/icofont.css" />
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/toastify.css" />
<!-- Style.css -->
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/style.css">
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/jquery.mCustomScrollbar.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,500i,700,700i,900" rel="stylesheet">
<link rel="stylesheet" href="/RailSAHAY/resources/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto Condensed', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700" rel="stylesheet">
<link href="/RailSAHAY/resources/css/gct/bootstrap-grid.css" rel="stylesheet">
<link href="/RailSAHAY/resources/css/gct/multistep.css" rel="stylesheet">
<link href="/RailSAHAY/resources/css/gct/abc.css" rel="stylesheet">
<link href="/RailSAHAY/resources/css/gct/animate.css" rel="stylesheet">
<link href="/RailSAHAY/resources/css/gct/fontawesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.1/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/gct/jquery.multiselect.css" />

</head>
<body>
        <?audit suppress oracle.ide.xml.mismatched-end-tag?>
        <!-- Pre-loader start -->
<div class="theme-loader">
<div class="loader-track">
<div class="loader-bar"></div>
</div>
</div>
<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
<div class="pcoded-overlay-box"></div>
<div class="pcoded-container navbar-wrapper">

<nav class="navbar header-navbar pcoded-header">
<div class="navbar-wrapper">
   <div class="navbar-logo">
       <a class="mobile-menu" id="mobile-collapse" href="#!">
           <i class="ti-menu"></i>
       </a>
       <%--
       <div class="mobile-search">
           <div class="header-search">
               <div class="main-search morphsearch-search">
                   <div class="input-group">
                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                       <input type="text" class="form-control" placeholder="Enter Keyword">
                       <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                   </div>
               </div>
           </div>
       </div>
       --%>
       <a href="index.html" style="font-weight:600;font-size:14px;margin-left:1rem;">
           <img src="/RailSAHAY/resources/images/gct_logo.png" alt="logo.png">
       </a>
       <a class="mobile-options">
           <i class="ti-more"></i>
       </a>
   </div>

   <div class="navbar-container container-fluid">
       <ul class="nav-left">
           <li>
               <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
           </li>
           <%--
           <li class="header-search">
               <div class="main-search morphsearch-search">
                   <div class="input-group">
                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                       <input type="text" class="form-control">
                       <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                   </div>
               </div>
           </li>
           --%>
           <li>
               <a href="#!" onclick="javascript:toggleFullScreen()">
                   <i class="ti-fullscreen"></i>
               </a>
           </li>
       </ul>
       <ul class="nav-right">
           <li class="header-notification">
               <a href="#!">
                   <i class="ti-bell"></i>
                   <span class="badge bg-c-pink"></span>
               </a>
               <ul class="show-notification">
                   <li>
                       <h6>Notifications</h6>
                       <label class="label label-danger">New</label>
                   </li>
                   <li>
                       <div class="media">
                           <img class="d-flex align-self-center img-radius" src="/RailSAHAY/resources/images/avatar-2.jpg" alt="Generic placeholder image">
                           <div class="media-body">
                               <h5 class="notification-user">SR.DCM Ahmedabad</h5>
                               <p class="notification-msg">Application for PFT at ADI needs attention</p>
                               <span class="notification-time">30 minutes ago</span>
                           </div>
                       </div>
                   </li>
                   <li>
                       <div class="media">
                           <img class="d-flex align-self-center img-radius" src="/RailSAHAY/resources/images/avatar-4.jpg" alt="Generic placeholder image">
                           <div class="media-body">
                               <h5 class="notification-user">SR.DCM Ajmer</h5>
                               <p class="notification-msg">Application for PFT at UDZ has been accepted.</p>
                               <span class="notification-time">30 minutes ago</span>
                           </div>
                       </div>
                   </li>
                   <li>
                       <div class="media">
                           <img class="d-flex align-self-center img-radius" src="/RailSAHAY/resources/images/avatar-3.jpg" alt="Generic placeholder image">
                           <div class="media-body">
                               <h5 class="notification-user">SR.DCM Delhi</h5>
                               <p class="notification-msg">Application for Private Siding at ANVR has been rejected.</p>
                               <span class="notification-time">30 minutes ago</span>
                           </div>
                       </div>
                   </li>
               </ul>
           </li>

           <li class="user-profile header-notification">
               <a href="#!">
                   <img src="/RailSAHAY/resources/images/avatar-4.png" class="img-radius" alt="User-Profile-Image">
                   <span><%=strCustName%></span>
                   <i class="ti-angle-down"></i>
               </a>
               <ul class="show-notification profile-notification">
                   <li>
                       <a href="#!">
                           <i class="ti-settings"></i> My Profile
                       </a>
                   </li>
                   <li>
                       <a href="user-profile.html">
                           <i class="ti-user"></i> Change Password
                       </a>
                   </li>
                   <li>
                       <a href="/RailSAHAY/Logout">
                       <i class="ti-layout-sidebar-left"></i> Logout
                   </a>
                   </li>
                   <li>
                       <a href="/RailSAHAY/index.jsp">
                       <i class="ti-layout-sidebar-left"></i> Back to FBD Portal
                   </a>
                   </li>
               </ul>
           </li>
       </ul>
   </div>
</div>
</nav>
<div class="pcoded-main-container">
<div class="pcoded-wrapper">
    <nav class="pcoded-navbar">
        <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
        <div class="pcoded-inner-navbar main-menu">


            <div class="pcoded-navigatio-lavel" data-i18n="nav.category.navigation">Options</div>
            <ul class="pcoded-item pcoded-left-item">
                <li class="active">
                    <a href="/RailSAHAY/pages/GCTDashboard.jsp">
                        <span class="pcoded-micon"><i class="fas fa-edit"></i><b>D</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.dash.main">New Application</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>
                <li class="pcoded-hasmenu pcoded-trigger">
                    <a href="javascript:void(0)">
                        <span class="pcoded-micon"><i class="fas fa-list"></i></span>
                        <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">My Applications</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                    <ul class="pcoded-submenu">
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showDraftAppl();">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Drafts</span><span id="draftCont" class="badge badge-warning pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('AI');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Awaiting IPA</span><span id="aiCont"  style="display:none !important;" class="badge badge-primary pull-right app-smry"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('II');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">IPA Issued</span><span id="iiCont" class="badge badge-primary pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('AA');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Awaiting Approval</span><span id="aaCont" class="badge badge-success pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('AP');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Approved</span><span id="apCont" class="badge badge-success pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('RA');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Returned for Action</span><span id="raCont" class="badge badge-secondary pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('RJ');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Rejected</span><span id="rjctCont" class="badge badge-danger pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class=" ">
                            <a href="javascript:void(0)" onclick="showSttsAppl('W');">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Withdrawn</span><span id="wCont" class="badge badge-danger pull-right app-smry" style="display:none !important;"></span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="form-elements-component.html">
                        <span class="pcoded-micon"><i class="far fa-bell"></i><b>NT</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Notifications</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>

            </ul>

            <div class="pcoded-navigatio-lavel" data-i18n="nav.category.other">Other</div>
            <ul class="pcoded-item pcoded-left-item">
                <li>
                    <a href="javascript:void(0)" onclick="openGCTOPolicy();">
                        <span class="pcoded-micon"><i class="far fa-clone"></i><b>FC</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">GCTO Policy</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" onclick="showUserManual();">
                        <span class="pcoded-micon"><i class="fas fa-hands-helping"></i><b>FC</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Help & Support</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" onclick="fetchNodlOfcrList();">
                        <span class="pcoded-micon"><i class="fas fa-phone-alt"></i><b>FC</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Contact Us</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>
                <!--
                <li class="pcoded-hasmenu ">
                    <a href="javascript:void(0)">
                        <span class="pcoded-micon"><i class="ti-direction-alt"></i><b>M</b></span>
                        <span class="pcoded-mtext" data-i18n="nav.menu-levels.main">Contact Us</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                    <ul class="pcoded-submenu">
                        <li class="">
                            <a href="javascript:void(0)">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.menu-levels.menu-level-21">Menu Level 2.1</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>
                        <li class="pcoded-hasmenu ">
                            <a href="javascript:void(0)">
                                <span class="pcoded-micon"><i class="ti-direction-alt"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.menu-levels.menu-level-22.main">Menu Level 2.2</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                            <ul class="pcoded-submenu">
                                <li class="">
                                    <a href="javascript:void(0)">
                                        <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                        <span class="pcoded-mtext" data-i18n="nav.menu-levels.menu-level-22.menu-level-31">Menu Level 3.1</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="">
                            <a href="javascript:void(0)">
                                <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                <span class="pcoded-mtext" data-i18n="nav.menu-levels.menu-level-23">Menu Level 2.3</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                        </li>

                    </ul>
                </li>
                -->
            </ul>
        </div>
    </nav>
    <div class="pcoded-content">
        <div class="pcoded-inner-content">
            <div class="main-body">
                <div class="page-wrapper">

                    <div class="page-body">
                    <% if(strPymtStts.equals("SUCCESS")){ %>
    		<div class="alert alert-success alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Successful!</strong> Application has been successfully submitted.		  
		  <table class="table table-striped table-bordered table-pymtstts">
		  	<tr><td>Payment Status</td><td class="pymt-stts"><%=strPymtStts%></td><td>Reference Id</td><td class="pymt-stts"><%=FOISTrxnId%></td></tr>
		  	<tr><td>Bank Transaction Id</td><td class="pymt-stts"><%=BankTrxnId%></td><td>Transaction Time</td><td class="pymt-stts"><%=BankTrxnTime%></td></tr>
		  </table>
		</div>
                    <% } %>
                    
                    <% if(strPymtStts.equals("FAILED")){ %>
    		<div class="alert alert-danger alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
  		   <strong>Transaction Failed!</strong> <%=strErorMesg%>
		</div>

                    <% } %>
                    
		<% if(strPymtStts.equals("CANCELLED")) { %>
		<div class="alert alert-warning alert-dismissible">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>Transaction Cancelled!</strong> May try it again. In case of any assistance/query regarding this payment service <a href="javascript:void(0)" onclick="gotoRailMadad();" class="card-link1"><i class="fas fa-angle-right"></i>&nbsp;Contact Us</a>
		</div>
		<% } %>
                    <form action="/RailSAHAY/GCTDataSave" onsubmit="onFormSubmit();" id="frmInpt" name="frmInpt" method="post">

                    <!--Multi Step Wizard Start-->
                                                           <div id="rms-wizard" class="rms-wizard data-holder" >
                                                           <!--<div class="row" style="margin-bottom:1rem;">
                                                           
                                                           <table class="table table-striped">
                                                           <tr><td><select id="selDvsn" class="form-control"></select></td><td><select id="selSttn" class="form-control"></select></td></tr>
                                                           </table>
                                                           </div></div>-->
                                                           <!--Wizard Container-->
                                                            <div class="rms-container">
                                                               <!--Wizard Header-->
                                                                <div class="rms-wizard-header">
                                                                    <h2 class="title">New <span>Application</span></h2>
                                                                </div>
                                                                <!--Wizard Header Close-->
                                                                <div class="rms-form-wizard">
                                                                   <!--Wizard Step Navigation Start-->
                                                                    <div class="rms-step-section" data-step-counter="false" data-step-image="false">
                                                                        <ul class="rms-multistep-progressbar">
                                                                            <li class="rms-step rms-current-step">
                                                                                <span class="step-icon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                                                                <span class="step-title">Applicant Detail</span>
                                                                                <span class="step-info">Enter individual/company detail</span>
                                                                            </li>
                                                                            <li class="rms-step ">
                                                                                <span class="step-icon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                                                                <span class="step-title">Concept Plan & Traffic Projection</span>
                                                                                <span class="step-info">Concept and Expected Traffic</span>
                                                                            </li>
                                                                            <li class="rms-step ">
                                                                                <span class="step-icon"><i class="fa fa-file-text" aria-hidden="true"></i></span>
                                                                                <span class="step-title">Preview and Payment</span>
                                                                                <span class="step-info">Continue with the payment</span>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                    <!--Wizard Navigation Close-->
                                                                                
                                                                    <!--Wizard Content Section Start-->
                                                                    <div class="rms-content-section">
                                                                        <div class="rms-content-box rms-current-section">
                                                                            <div class="rms-content-area">
                                                                                                        <div class="row" >
                                                                                                                <div class=" col-lg-12">	
                                                                                                                
                                                                                <div class="rms-content-title">
                                                                                    <div class="leftside-title"><b>Location</b> of the Terminal</div>
                                                                                    <div class="step-label">Step 1 - 3 </div>
                                                                                </div>
                                                                                                                <table class="table table-striped">
                                                                                                                        <tr >
                                                                                                                        <td style="border-top: none !important;margin-left: 5px;width: 50%;"><select id="selDvsn" name="selDvsn" class="form-control" style="height:35px;"></select></td><td style="border-top: none !important;width: 50%"><select id="selSttn" class="form-control" style="height:35px;" name="selSttn"></select></td>
                                                                                                                </tr>
                                                                                                                </table>
                                                                                                                </div></div>
                                                                                                                <hr style="margin-top:8px;margin-bottom:8px;" />
                                                                                <div class="rms-content-title">
                                                                                    <div class="leftside-title"><b>Applicant</b> Information</div>
                                                                                </div>
                                                                                <div class="rms-content-body">
                                                                                    <div class="row">
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="username">Name &nbsp; </label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                        <input type="text" name="Name" id="Name" class="inpt-control required alpha" required="required"  placeholder="NAME" value="<%=strCustName%>"> 
                                                                                                        <!--
                                                                                                            <div class="form-tooltip">
                                                                                                                <span class="tooltip-title">Why do we need this info?</span>
                                                                                                                <p class="tooptip-content">Lorem Ipsum is simply dummy text of the printing and
                                                                                                                                                                        typesetting industry. Lorem Ipsum has been the industry's
                                                                                                                                                                        standard dummy text ever since the 1500s</p>
                                                                                                                <span class="tooltip-info">Your information is Safe here & never shared.</span>
                                                                                                            </div>
                                                                                                            -->
                                                                                                        </div>
                                                                                                    </div>
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="desg">Designation &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                        <div class="inpt-group">
                                                                                                            <input type="text" name="Desg" id="Desg" class="inpt-control required alphanum" required="required" placeholder="DESIGNATION">
                                                                                                            <!--
                                                                                                            <div class="form-tooltip">
                                                                                                                <span class="tooltip-title">Why do we need this info?</span>
                                                                                                                <p class="tooptip-content">Lorem Ipsum is simply dummy text of the printing and
                                                                                                                                                                        typesetting industry. Lorem Ipsum has been the industry's
                                                                                                                                                                        standard dummy text ever since the 1500s</p>
                                                                                                                <span class="tooltip-info">Your information is Safe here & never shared.</span>
                                                                                                            </div>
                                                                                                            -->
                                                                                                        </div>
                                                                                                    </div>
                                                                                                 </div>
                                                                                            </div>
                                                                                    <div class="row">
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="Mobl">Mobile Number &nbsp; </label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                        <input type="text"    maxlength="10" name="Mobl" id="Mobl" class="inpt-control required digit" placeholder="Mobile Number digit" value="<%=strMoblNumb%>"> 
                                                                                                        <!--
                                                                                                            <div class="form-tooltip">
                                                                                                                <span class="tooltip-title">Why do we need this info?</span>
                                                                                                                <p class="tooptip-content">Lorem Ipsum is simply dummy text of the printing and
                                                                                                                                                                        typesetting industry. Lorem Ipsum has been the industry's
                                                                                                                                                                        standard dummy text ever since the 1500s</p>
                                                                                                                <span class="tooltip-info">Your information is Safe here & never shared.</span>
                                                                                                            </div>
                                                                                                            -->
                                                                                                        </div>
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="username">Email Id &nbsp; </label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                        <input type="email" name="Email" id="Email" class="inpt-control required keyup-email" required="required" placeholder="Email Id" value="<%=strEmail%>"> 
                                                                                                        <input type="hidden" name="RfrnId" id="RfrnId" value="" /> 
                                                                                                        <input type="hidden" name="GCTFileRfrnId" id="GCTFileRfrnId" value="" /> 
                                                                                                        <input type="hidden" name="DocStts" id="DocStts" value="" />  
                                                                                                        <input type="hidden" name="PlanStts" id="PlanStts" value="" /> 
                                                                                                        <input type="hidden" name="GCTFileRfrnId" id="GCTFileRfrnId" value="" /> 
                                                                                                        </div>
                                                                                                    </div>
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="username">Contact Number &nbsp; </label>
                                                                                                        <input type="text"  maxlength="11"  name="ContNo" id="ContNo" class="inpt-control digit" placeholder="Contact Number" value="<%=strMoblNumb%>"> 
                                                                                                    </div>
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="desg">Address &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                        <div class="inpt-group">
                                                                                                            <input  type="text" name="Addr" id="Addr" class="inpt-control required alphanum" placeholder="Address">
                                                                                                            <!--<div class="form-tooltip">
                                                                                                                <span class="tooltip-title">Why do we need this info?</span>
                                                                                                                <p class="tooptip-content">Lorem Ipsum is simply dummy text of the printing and
                                                                                                                                                                        typesetting industry. Lorem Ipsum has been the industry's
                                                                                                                                                                        standard dummy text ever since the 1500s</p>
                                                                                                                <span class="tooltip-info">Your information is Safe here & never shared.</span>
                                                                                                            </div>
                                                                                                            -->
                                                                                                        </div>
                                                                                                    </div>
                                                                                                 </div>
                                                                                            </div>
                                                                                                    
                                                                                            <div class="row">
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="gstin">GSTIN &nbsp;</label>
                                                                                                        <div class="inpt-group">
                                                                                                            <input type="text"    maxlength="15"  name="GstIn" id="GstIn" class="inpt-control alphanumStrct" placeholder="GSTIN">
                                                                                                        </div>
                                                                                                    </div>
                                                                                                 </div>
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                       <label for="AppType">Applicant Category &nbsp;</label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                       <div class="inpt-group">
                                                                                                          <select id="ApltType" name="ApltType" class="inpt-control required">
                                                                                                                <option value="" selected="selected" disabled="disabled">SELECT TYPE</option>
                                                                                                                <option value="IND">INDIVIDUAL</option>
                                                                                                                <option value="HUF">HINDU UNDIVIDED FAMILY (HUF)</option>
                                                                                                                <option value="PRF">PARTNERSHIP FIRM</option>
                                                                                                                <option value="COM">COMPANIES REGISTERED UNDER COMPANIES ACT, 2013</option>
                                                                                                                <option value="LLP">LIMITED LIABILITY PARTNERSHIP (LLP)</option>
                                                                                                                <option value="SOC">REGISTERED SOCIETY/TRUST</option>
                                                                                                                <option value="JV">JOINT VENTURE</option>
                                                                                                          </select>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                 </div>
                                                                                            </div>
                                                                                            <div class="row">                                                                                                                                       
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="username">PAN &nbsp; </label>
                                                                                                        <input type="text" name="PAN" id="PAN" maxlength="10" class="inpt-control alphanumStrct" placeholder="PAN Number" > 
                                                                                                    </div>
                                                                                                </div>                                                                                                                                      
                                                                                                <div class="col-md-6">
                                                                                                    <div class="inpt-form-group">
                                                                                                        <label for="username">TAN &nbsp; </label>
                                                                                                        <input type="text" name="TAN" id="TAN" maxlength="10" class="inpt-control alphanumStrct" placeholder="TAN Number" > 
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                            <hr style="margin:10px;" />
                                                                                            <div class="row">                                                                                                                                       
                                                                                                <div class="col-md-6">
                                                                                                        <span  class="file-stts">Files Uploads </label>
                                                                                                        <span id="filePendency" class="ml-2"></span>
                                                                                                    </span></div>                                                                                                                                  
                                                                                                <div class="col-md-6">
                                                                                                   <%-- <span data-toggle="collapse" data-target="#fUpload" class="file-link-open pull-right" onclick="openFileUpload();"><i class="fas fa-chevron-circle-down"></i></span>--%>
                                                                                                </div>
                                                                                            </div>  
                                                                                            <div id="fUpload" class="collapse show">
                                                                                                <iframe id="ifrmUpload" src="/RailSAHAY/pages/GCTFileUpload.jsp" style="border:0px;width:100%;"></iframe>
                                                                                                 </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                        <div class="rms-content-box">
                                                                             <div class="rms-content-area">
                                                                                <div class="rms-content-title">
                                                                                    <div class="leftside-title"><b>Concept</b> Plan</div>
                                                                                    <div class="step-label">Step 2 - 3 </div>
                                                                                </div>
                                                                                 <div class="rms-content-body">
                                                                                 <div class="row">
                                                                                                                        <div class="col-md-12" id="divPlanFile">
                                                                                                                                <iframe id="ifrmUploadPlan" src="/RailSAHAY/pages/GCTFileUpload.jsp" style="border:0px;width:100%;"></iframe>
                                                                                                                         </div>
                                                                                </div>
                                                                                                                <table class="table table-striped"><tr><td style="font-weight:600;font-size:13px;">Traffic Projection (Annual)</td><td><a class="pull-right link-data" href="javascript:void(0)" style="font-weight:500;margin-right:10px;" onclick="showCmdtRef();"><i class="fas fa-th-list"></i>&nbsp;&nbsp;View Commodity Reference</a></td></tr></table>
                                                                                    <div class="container" style="min-height:150px;">
                                                                                     <div class="row">
                                                                                     <div class="col-lg-6">
                                                                                      <div class="inpt-form-group">
                                                                                                   <label for="cmdt" id="spnCmdtCont">Commodities</label><label style="color: rgb(231, 142, 142);"> *</label>
                                                                                                           <select name="cmdt[]" id="cmdt" class="form-control required" multiple style="z-index:9999;">
                                                                                                               
                                                                                                           </select>
                                                                                                           <input type="hidden" name="CmdtList" id="CmdtList" />
                                                                                                           <input type="hidden" name="CmdtCont" id="CmdtCont" />
                                                                                                           <input type="hidden" name="CmdtDtls" id="CmdtDtls" />
                                                                                                           <%--
                                                                                                           <input type="hidden" name="FileRfrnId" id="FileRfrnId" value="<%=FileRfrnId%>" />
                                                                                                           <input type="hidden" name="RfrnId" id="RfrnId"  value="<%=RfrnId%>" />
                                                                                                           --%>
                                                                                                           <input type="hidden" name="Optn" id="Optn" />
                                                                                                </div>
                                                                                        </div>
                                                                                        </div>
                                                                                     <div class="row">
                                                                                        <div class="col-lg-10" id="cmdtdtls">
                                                                                        </div>
                                                                                     </div>
                                                                                                                        
                                                                                     </div>
                                                                                    
                                                                                
                                                                                </div>
                                                                        </div>
                                                                        </div>
                                                                        <div class="rms-content-box">
                                                                            <div class="rms-content-area">
                                                                                <div class="rms-content-title">
                                                                                    <div class="leftside-title"><b>Confirm</b> Details</div>
                                                                                    <div class="step-label">Step 3 - 3 </div>
                                                                                </div>
                                                                                <div class="rms-content-body">
                                                                                    <div class="row">
                                                                                        <div class="col-md-12">
                                                                                                    <div class="desc-container">
                                                                                                        <div class="desc-table">
                                                                                                            <table class="table tabled-striped">
                                                                                                                <tbody>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Location</td>
                                                                                                                        <td class="desc-val" id="cnfmlocn"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Name</td>
                                                                                                                        <td class="desc-val" id="cnfmname"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Designation</td>
                                                                                                                        <td class="desc-val" id="cnfmdesg"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Contact Detail</td>
                                                                                                                        <td class="desc-val" id="cnfmcont"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Address</td>
                                                                                                                        <td class="desc-val" id="cnfmaddr"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">GSTIN</td>
                                                                                                                        <td class="desc-val" id="cnfmgstin"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Applicant Category</td>
                                                                                                                        <td class="desc-val" id="cnfmappltype"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">PAN Number</td>
                                                                                                                        <td class="desc-val" id="cnfmPAN"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">TAN Number</td>
                                                                                                                        <td class="desc-val" id="cnfmTAN"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Files Upload</td>
                                                                                                                        <td class="desc-val" id="cnfmfileatch"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Concept Plan</td>
                                                                                                                        <td class="desc-val" id="cnfmsketch"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="desc-label">Commodity Detail(s)</td>
                                                                                                                        <td class="desc-val" id="cnfmcmdt"></td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="pymt-label">Amount Payable</td>
                                                                                                                        <td class="pymt-val" id="cnfmappamnt"></td>
                                                                                                                    </tr>
                                                                                                                </tbody>
                                                                                                            </table>
                                                                                                        </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <!--Wizard Content Section Close-->
                                                                    <!--Wizard Footer section Start-->
                                                                    <div class="rms-footer-section">
                                                                        <div class="button-section">
                                                                            <span class="next">
                                                                                <a href="javascript:void(0)" onclick="updtCnfmDtls();return true;" class="btn">Next Step
                                                                                    <small>Your information</small>
                                                                                </a>
                                                                            </span>
                                                                            <span class="draft text-center" >
                                                                            <a href="javascript:void(0)" class="btn-savedraft" onclick="saveDraft();">
                                                                                <i class="fas fa-save"></i>&nbsp;&nbsp;Save Draft
                                                                            </a>
                                                                            </span>
                                                                            <span class="prev">
                                                                                <a href="javascript:void(0)" class="btn" onclick="gotoPrevious();">Previous Step
                                                                                     <small>Your information</small>
                                                                                </a>
                                                                            </span>
                                                                            <span class="submit">
                                                                                <a href="javascript:void(0)" class="btn" onclick="formSubmit();">Proceed for Payment
                                                                                     <small>Continue with the Payment Process</small>
                                                                                </a>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                    <!--Wizard Footer Close-->
                                                                </div>
                                                            </div>
                                                            <!--Wizard Container Close-->
                                                            </div>
<!--Multi Step Wizard Close-->
</form>
<div class="container-fullwidth data-holder" id="divDraftAppl" style="margin:0.3rem;">
<div class="rms-wizard">
<div class="rms-wizard-header">
<h2 class="title">Draft <span>Applications</span></h2>
</div>
<div class="row">
<div class="col-lg-12" style="overflow-x:auto;overflow-y:auto;" id="divDraftList">
</div>
</div>
</div>
</div>
<div class="container-fullwidth data-holder" id="divNodlOfcrHolder" style="margin:0.3rem;">
<div class="rms-wizard">
<div class="rms-wizard-header">
<h2 class="title">Nodal <span>Officers</span></h2>
</div>
<div class="row">
<div class="col-lg-12" style="overflow-x:auto;overflow-y:auto;" id="divNodlOfcr">
</div>
</div>
</div>
</div>


<div class="container-fullwidth data-holder" id="divSttsAppList" style="margin:0.3rem;">
<div class="rms-wizard">
<div class="rms-wizard-header">
<h2 class="title" id="appListHedr"></h2>
</div>
<div class="row">
<div class="col-lg-12" style="overflow-x:auto;overflow-y:auto;" id="divSttsWiseAppList">
</div>
</div>
</div>
</div>
<div class="modal fade" id="refModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="refModalHedr"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="refModalBody" style="max-height:350px;overflow-y:scroll;padding:1rem;">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div><div class="modal fade" id="refDocument" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="refDocumentHedr"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="refDocumentBody" style="max-height:350px;overflow-y:scroll;padding:1rem;">
        <iframe src="/RailSAHAY/resources/pdf/GCTO_Policy.pdf" id="ifrmExtnDoc" style="border:0px;width:100%;height:500px;overflow-y:auto;"></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
	<div id="confirm-modal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">			
				<h4 class="modal-title" style="color:#000;font-size:16px;">Confirmation</h4>	
                                <hr/>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p id="cnfmmesg"></p>
			</div>
			<div class="modal-footer">
                	<button  class="btn btn-info btn-sm" data-dismiss="modal">Cancel</button>
			<button  class="btn btn-danger btn-sm" onclick="confirmYes();">Yes, Continue !</button>
			</div>
		</div>
	</div>
</div>
<form id="frmGCTPymtProcess" method="post" action="\RailSAHAY\GCTPymtProcess" target="_self">
        <input type="hidden" id="txtZone" name="txtZone" value="" />
        <input type="hidden" id="txtDvsn" name="txtDvsn" value="" />
        <input type="hidden" id="txtSttn" name="txtSttn" value="" />
        <input type="hidden" id="txtName" name="txtName" value="" />
        <input type="hidden" id="txtDesg" name="txtDesg" value="" />
        <input type="hidden" id="txtMobl" name="txtMobl" value="" />
        <input type="hidden" id="txtContNo" name="txtContNo" value="" />
        <input type="hidden" id="txtEmail" name="txtEmail" value="" />
        <input type="hidden" id="txtAddr" name="txtAddr"  value="" />
        <input type="hidden" id="txtGstIn" name="txtGstIn"  value="" />
        <input type="hidden" id="txtApltCtgr" name="txtApltCtgr"  value="" />
        <input type="hidden" id="txtPAN" name="txtPAN" value=""  />
        <input type="hidden" id="txtTAN" name="txtTAN"  />
        <input type="hidden" id="txtFileList" name="txtFileList" value="" />
        <input type="hidden" id="txtCmdtCont" name="txtCmdtCont"  value="" />
        <input type="hidden" id="txtCmdtList" name="txtCmdtList" value=""  />
        <input type="hidden" id="txtCmdtDtls" name="txtCmdtDtls"   />
        <input type="hidden" id="txtRfrnId" name="txtRfrnId" />
        <input type="hidden" id="txtFileRfrnId" name="txtFileRfrnId" value="" />
        <input type="hidden" id="txtGstAmnt" name="txtGstAmnt" value="" />
        <input type="hidden" id="txtAppAmnt" name="txtAppAmnt" value="" />
        <input type="hidden" id="txtTotlAmnt" name="txtTotlAmnt" value="" />
        <input type="hidden" id="Optn" name="Optn" value="PAY_NOW" />
</form>
<!-- Required Jquery -->
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/jquery.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/jquery-ui.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/popper.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
<!-- jquery slimscroll js -->
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/jquery.slimscroll.js"></script>
<!-- modernizr js -->
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/modernizr.js"></script>
<!-- Custom js -->
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/custom-dashboard.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/script.js"></script>
<script type="text/javascript " src="/RailSAHAY/resources/js/gct/SmoothScroll.js"></script>
<script src="/RailSAHAY/resources/js/gct/pcoded.min.js"></script>
<script src="/RailSAHAY/resources/js/gct/vartical-demo.js"></script>
<script src="/RailSAHAY/resources/js/gct/toastify.js"></script>
<script src="/RailSAHAY/resources/js/gct/toastifyscript.js"></script>
<script src="/RailSAHAY/resources/js/gct/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="https://cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.1/js/dataTables.bootstrap4.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/aLocn.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/aTrmlList.js"></script>	
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/multistep.js"></script>
<script src="/RailSAHAY/resources/js/gct/jquery.multiselect.js"></script>
<script src="/RailSAHAY/resources/js/gct/cmdtmpng7a.js"></script>
<script src="/RailSAHAY/resources/js/gct/gctdashboard.js"></script>
<script src="/RailSAHAY/resources/js/gct/gctqryjson.js"></script>

</body>

</html>
