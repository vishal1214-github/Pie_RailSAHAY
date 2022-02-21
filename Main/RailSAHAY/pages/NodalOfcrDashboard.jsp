<%@ include file="/pages/GG_Header.jspf" %>
<%@ page import = "user.UserProfDtls" %>

<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"NODAL OFFICER DASHBOARD","/RailSAHAY/pages/NodalOfcrDashboard.jsp",UserDevice,Browser);
%>

<% 
String strUserID=(String)session.getAttribute("custuserid");  

 if(strUserID==null) strUserID="";
 
 System.out.println("strUserID::"+strUserID+"||||");
 
      if(strUserID.equals(""))
      {
   %>
        <jsp:forward page="eDmndLogin.jsp"/>
   <%
      }
      UserProfDtls userdtls = new UserProfDtls((String)session.getAttribute("custuserid"));  
   %>
<script src="/RailSAHAY/resources/js/nodlofcrdb.js"></script>

<nav class="navbar navbar-expand-sm bg-origprimary" style="margin-top:1px;max-height:10px;">
	</nav>
      <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <nav class="side-navbar">
          <!-- Sidebar Header-->
          <div class="sidebar-header d-flex align-items-center">
            <div class="title">
              <h1 class="h4">Logged In As</h1>
              <p class="pcustid"><%= userdtls.getTavcustuserid()%></p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
          <ul class="list-unstyled">
            <!--<li class="active"><a href="index.html"> <i class="icon-home"></i>Rail SAHAY Home</a></li>-->
			<li><a href="charts.html"> <i class="far fa-frown"></i>  Grievances <span class="badge badge-danger">2</span></a></li>
			<li><a href="charts.html"> <i class="fa fa-cogs"></i>  Service Requests <span class="badge badge-warning">5</span></a></li>
			<li><a href="#srvcReq" aria-expanded="false" data-toggle="collapse"><i class="fas fa-file-export" aria-hidden="true"></i>  Forwarded Concerns</a>
			  <ul id="srvcReq" class="collapse list-unstyled ">
				<li><a href="#">Query</a></li>
				<li><a href="#">Suggestion</a></li>
				<li><a href="#">Grievance</a></li>
			  </ul>
            </li>
            <li><a href="#lodgeConcern" aria-expanded="false" data-toggle="collapse"> <i class="fas fa-folder"></i>  Closed Requests</a>
              <ul id="lodgeConcern" class="collapse list-unstyled ">
                <li><a href="#">Query</a></li>
                <li><a href="#">Suggestion</a></li>
                <li><a href="#">Grievance</a></li>
              </ul>
            </li>
			<li><a href="charts.html"> <i class="fa fa-map-marker"></i>  Terminal Profile</a></li>
            <li><a href="charts.html"> <i class="fas fa-user-cog"></i>  Customer Detail</a></li>
            <!--
            <li><a href="charts.html"> <i class="far fa-copy"></i>  My Schemes</a></li>
            <li><a href="charts.html"> <i class="fa fa-phone-square"></i>  Nodal Officers</a></li>
            -->
          </ul>
        </nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header">
            <div class="container-fluid">
              <table class="headtbl"><tr><td><h3 class="no-margin-bottom">Welcome, <%= userdtls.getTavcustfrstname() %>  <%= userdtls.getTavcustlastname()%> !</h3></td><td style="width:40%;"><span id="savestts" class="float-right"></span></td></tr></table>
            </div>
          </header>
          <!-- Dashboard Counts Section-->
          <section class="dashboard-counts no-padding-bottom">
            <div class="container-fluid">
              <div class="row bg-white has-shadow">
                <!-- Item -->
                <div class="col-xl-4 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="getSRList();">
                    <div class="icon bg-violet"><i class="fa fa-cogs" aria-hidden="true"></i></div>
                    <div class="title"><span>Service<br>Requests</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 25%; height: 4px;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="pndgsrcont">0</strong></div>
                  </div>
                </div>
                
                <!-- Item -->
                <div class="col-xl-4 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="getCncrList();">
                    <div class="icon bg-red"><i class="fas fa-paste" aria-hidden="true"></i></div>
                    <div class="title"><span>Customer<br>Concerns</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 70%; height: 4px;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="pndgcncrcont">0</strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-4 col-sm-6">
                  <div class="item d-flex align-items-center" onclick="getIAList();">
                    <div class="icon bg-green"><i class="fas fa-tasks" aria-hidden="true"></i></div>
                    <div class="title"><span>Information<br>Access</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 40%; height: 4px;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="pndgiacont">0</strong></div>
                  </div>
                </div>
              </div>
            </div>
          </section>
           <section class="projects">
            <div class="container-fluid" id="divActvDtls">
              <!-- Project-->
              <div class="project">
                <div class="row bg-white has-shadow">
                  <div class="left-col col-lg-6 d-flex align-items-center justify-content-between">
                    <div class="project-title d-flex align-items-center">
                      <button class="btn btn-warning mr-3">1</button>
                      <div class="text">
                        <h3 class="h4">NIGHT UNLOADING</h3><small>JNPT 26-08(COMPANY: CIL)</small>
                      </div>
                    </div>
                    <div class="project-date"><span class="hidden-sm-down">CEMT/43/BCN</span></div>
                  </div>
                  <div class="right-col col-lg-6 d-flex align-items-center">
                    <div class="time"><i class="far fa-clock"></i> RT: 24-08 18:40 </div>
                    <button class="btn btn-sm btn-danger">View/Respond</button>&nbsp;&nbsp;
                    <button class="btn btn-sm btn-warning">Forward</button>
                  </div>
                </div>
              </div>

			<div class="project">
			  <div class="row bg-white has-shadow">
				<div class="left-col col-lg-6 d-flex align-items-center justify-content-between">
				  <div class="project-title d-flex align-items-center">
					<button class="btn btn-warning mr-3">2</button>
					<div class="text">
					  <h3 class="h4">DIVERSION</h3><small>TKD->OKA (COMPANY: NTPC)</small>
					</div>
				  </div>
				  <div class="project-date"><span class="hidden-sm-down">COAL/23/BOXNHL</span></div>
				</div>
				<div class="right-col col-lg-6 d-flex align-items-center">
				  <div class="time"><i class="far fa-clock"></i> RT: 24-08 18:40 </div>
                    <button class="btn btn-sm btn-danger">View/Respond</button>&nbsp;&nbsp;
                    <button class="btn btn-sm btn-warning">Forward</button>
				</div>
			  </div>
			</div>

			<div class="project">
			  <div class="row bg-white has-shadow">
				<div class="left-col col-lg-6 d-flex align-items-center justify-content-between">
				  <div class="project-title d-flex align-items-center">
					<button class="btn btn-warning mr-3">3</button>
					<div class="text">
					  <h3 class="h4">DEMMURAGE WAIVER</h3><small>JNPT (COMPANY: ARIL)</small>
					</div>
				  </div>
				  <div class="project-date"><span class="hidden-sm-down">CEMT/43/BCNHL</span></div>
				</div>
				<div class="right-col col-lg-6 d-flex align-items-center">
				  <div class="time"><i class="far fa-clock"></i> RT: 24-08 18:40 </div>
                    <button class="btn btn-sm btn-danger">View/Respond</button>&nbsp;&nbsp;
                    <button class="btn btn-sm btn-warning">Forward</button>

				</div>
			  </div>
			</div>

            </div>
          </section>
          </div>
          </div>
          <!-- Page Footer-->
              <%@ include file="/pages/GG_Footer.jspf" %>
