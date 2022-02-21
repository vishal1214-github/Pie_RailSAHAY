<%@ include file="/pages/GG_Header.jspf" %>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX-cwIo4xvSzjxyVRtxhoQy-KhLwRpgeM&callback=initMap&libraries=geometry&sensor=false" type="text/javascript"></script>
<script src="/RailSAHAY/resources/js/aPinCode.js"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script src="/RailSAHAY/resources/js/trmldashbord.js"></script>
  <!-- Custom fonts for this template-->
  <link href="/RailSAHAY/resources/css/trmldb/all.min.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="/RailSAHAY/resources/css/Font1.css">

  <!-- Custom styles for this template-->
  <link href="/RailSAHAY/resources/css/trmldb/sb-admin-2.min.css" rel="stylesheet">
  <link href="/RailSAHAY/resources/css/trmldb/trmldashbord.css" rel="stylesheet">

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark">
	</nav>

  <!-- Page Wrapper -->
  <div id="wrapper1">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
	 <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle locnflag" locn="" href="#" id="navbardrop" data-toggle="dropdown">
	          <%=E.EH("Territory Type")%>
	        </a>
	        <div class="dropdown-menu">
	          <a class="dropdown-item" href="#" value="NM"><%=E.EH("Near Me (50 Kms)")%></a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="#" value="PC"><%=E.EH("Pin Code")%></a>
	          <a class="dropdown-item" href="#" value="S"><%=E.EH("State")%></a>
	          <a class="dropdown-item" href="#" value="DD"><%=E.EH("District")%></a>
	          <a class="dropdown-item" href="#" value="Z"><%=E.EH("Zone")%></a>
	          <a class="dropdown-item" href="#" value="D"><%=E.EH("Division")%></a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="#" value="IR" onclick="setIR();"><%=E.EH("All India")%></a>

	        </div>
	      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">
      <br/>
      <!-- Heading -->
      <div class="sidebar-heading">
        <%=E.EH("Facilities")%>
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-dice-d20"></i>
          <span><%=E.EH("Terminal Facility")%></span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header"><%=E.EH("Select Facilities")%>:</h6>
	    <div class="form-check chkfilter">
		  <input class="form-check-input chktrmlfcty" type="checkbox" name="chkTrmlFcty" value="LL" id="chkLght">
		  <label class="form-check-label" for="chkLght">
		    <%=E.EH("Lighting")%>
		  </label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chktrmlfcty" type="checkbox" name="chkTrmlFcty" value="AA" id="chk247">
		<label class="form-check-label" for="chk247">
    		<%=E.EH("24x7 Operations")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chktrmlfcty" type="checkbox" name="chkTrmlFcty" value="WW" id="chkWB">
		<label class="form-check-label" for="chkWB">
    		<%=E.EH("Weighbridge")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chktrmlfcty" type="checkbox" name="chkTrmlFcty" value="EE" id="chkMechHndl">
		<label class="form-check-label" for="chkMechHndl">
    		<%=E.EH("Mechanical Handling")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chktrmlfcty" type="checkbox" name="chkTrmlFcty" value="MM" id="chkManHndl">
		<label class="form-check-label" for="chkManHndl">
    		<%=E.EH("Manual Handling")%>
		</label>
	</div>
	<button class="btn btn-sm btn-primary" style="margin-left:7px;" onclick="event.preventDefault();getSmryStats();"><%=E.EH("Apply")%></button>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-truck"></i>
          <span><%=E.EH("Logistics")%></span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header"><%=E.EH("Having")%>:</h6>

	<div class="form-check chkfilter">
	  	<input class="form-check-input chksmart" type="checkbox" name="chkSMARTFcty" value="AG" id="chkAggr">
		<label class="form-check-label" for="chkAggr">
    		<%=E.EH("Aggregator")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chksmart" type="checkbox" name="chkSMARTFcty" value="TK" id="chkTrck">
		<label class="form-check-label" for="chkTrck">
    		<%=E.EH("Trucker")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chksmart" type="checkbox" name="chkSMARTFcty" value="WH" id="chkWH">
		<label class="form-check-label" for="chkWH">
    		<%=E.EH("Warehouse")%>
		</label>
	</div>
	<div class="form-check chkfilter">
	  	<input class="form-check-input chksmart" type="checkbox" name="chkSMARTFcty" value="LB" id="chkLab">
		<label class="form-check-label" for="chkLab">
    		<%=E.EH("Labour")%>
		</label>
	</div>
	<button class="btn btn-sm btn-primary" style="margin-left:7px;" onclick="event.preventDefault();getSmryStats();"><%=E.EH("Apply")%></button>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">
      <br/>


      <!-- Heading -->
      <div class="sidebar-heading">
        <%=E.EH("Related Links")%>
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link" href="/RailSAHAY/pages/BkngRegProcess.jsp" target="_self">
          <i class="fas fa-handshake"></i>
          <span><%=E.EH("Transport with Us")%></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/RailSAHAY/pages/OwnTrmlNew.jsp" target="_self">
          <i class="fas fa-pallet"></i>
          <span><%=E.EH("Own a Terminal")%></span></a>
      </li>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="javascript:void(0)" onclick="gotoRailMadad();">
          <i class="fas fa-phone-volume"></i>
          <span><%=E.EH("Contact Us")%></span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <table>
            <tr><td>
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 medium inptcap" id="txtLocn" name="txtLocn" list="locnlist" placeholder='<%=E.EH("Territory")%>..' aria-label="Search" aria-describedby="basic-addon2">
	      <datalist id="locnlist"></datalist>
             </div>
            </td>
            <td>
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 medium inptcap"  id="txtCmdt" list="cmdtlist"  name="txtCmdt" placeholder="<%=E.EH("Commodity")%>.." aria-label="Search" aria-describedby="basic-addon2">
	      <datalist id="cmdtlist"></datalist>

            </div>
            </td>
            <td>
            	<button class="btn btn-primary btn-sm" onclick="event.preventDefault();fetchData();"><%=E.EH("Search")%></button>
            </td>
            </tr>
            </table>
          </form>
	 <%--
          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder='<%=E.EH("Search for")%>...' aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

          </ul>
--%>
        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <p class="pdbhedr"><%=E.EH("Freight Terminals Dashboard")%>&nbsp;&nbsp;&nbsp;<div class="spinner-border text-danger" role="status" id="divLdngData"><span class="sr-only">Loading...</span></div></p>
            <%--<a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Download Report</a>--%>
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2 card-mainlink" onclick="getSpecStats('LDNG');">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1"><%=E.EH("Loading Locations")%></div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800 trmlstats" id="divLdngCont"></div>
                    </div>
                    <div class="col-auto">
			<i class="fas fa-box fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2 card-mainlink" onclick="getSpecStats('ULDG');">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1"><%=E.EH("Unloading Locations")%></div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800 trmlstats" id="divUldgCont"></div>
                    </div>
                    <div class="col-auto">
			<i class="fas fa-box-open fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2 card-mainlink" onclick="getSpecStats('CRT');">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1"><%=E.EH("Container Rail Terminals (CRT)")%></div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800 trmlstats" id="divCRTCont"></div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2 card-mainlink" onclick="getSpecStats('PFT');">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1"><%=E.EH("Private Freight Terminals (PFT)")%></div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800 trmlstats" id="divPFTCont"></div>
                    </div>
                    <div class="col-auto">
		      <i class="fas fa-house-user fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Content Row -->

          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary" id="headTrmlPlot"><%=E.EH("Available Terminals")%></h6>
                  <div class="dropdown no-arrow" id="ddBrkupOptn">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Breakup View:</div>
                      <a class="dropdown-item" href="javascript:void(0)" onclick="getBrkUpStats('S');"><%=E.EH("State-wise")%></a>
                      <a class="dropdown-item" href="javascript:void(0)" onclick="getBrkUpStats('DD');"><%=E.EH("District-wise")%></a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="javascript:void(0)" onclick="getBrkUpStats('Z');"><%=E.EH("Zone-wise")%></a>
                      <a class="dropdown-item" href="javascript:void(0)" onclick="getBrkUpStats('D');"><%=E.EH("Division-wise")%></a>

                    </div>
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area" id="divTrmlPlot" style="overflow-y:scroll;",>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pie Chart -->
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary"><%=E.EH("TERMINALS LIST")%></h6>
		  <%--
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Dropdown Header:</div>
                      <a class="dropdown-item" href="#">Action</a>
                      <a class="dropdown-item" href="#">Another action</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                  </div>
		--%>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie" id="divTrmlList" style="overflow-y:scroll;">
                  </div>
                  <div class="mt-4 text-center small">
                    <span class="mr-1">
                      <i class="fas fa-circle text-primary"></i> <%=E.EH("Aggregator")%>
                    </span>
                    <span class="mr-1">
                      <i class="fas fa-circle text-success"></i> <%=E.EH("Trucker")%>
                    </span>
                    <span class="mr-1">
                      <i class="fas fa-circle text-danger"></i> <%=E.EH("Warehouse")%>
                    </span>
                    <span class="mr-1">
                      <i class="fas fa-circle text-warning"></i> <%=E.EH("Labour")%>
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Content Column -->
            <div class="col-lg-6 mb-4">

              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"><%=E.EH("TERMINAL PROFILE")%></h6>
                </div>
                <div class="card-body">
                 	<div id="divTrmlProf" style="overflow-y:scroll;"></div>
                </div>
              </div>
	     <%--
              <!-- Color System -->
              <div class="row">
                <div class="col-lg-6 mb-4">
                  <div class="card bg-primary text-white shadow">
                    <div class="card-body">
                      Primary
                      <div class="text-white-50 small">#4e73df</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-success text-white shadow">
                    <div class="card-body">
                      Success
                      <div class="text-white-50 small">#1cc88a</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-info text-white shadow">
                    <div class="card-body">
                      Info
                      <div class="text-white-50 small">#36b9cc</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-warning text-white shadow">
                    <div class="card-body">
                      Warning
                      <div class="text-white-50 small">#f6c23e</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-danger text-white shadow">
                    <div class="card-body">
                      Danger
                      <div class="text-white-50 small">#e74a3b</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-secondary text-white shadow">
                    <div class="card-body">
                      Secondary
                      <div class="text-white-50 small">#858796</div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 mb-4">
                  <div class="card bg-light text-black shadow">
                    <div class="card-body">
                      Light
                      <div class="text-black-50 small">#f8f9fc</div>
                    </div>
                  </div>
              </div>
              <div class="col-lg-6 mb-4">
                <div class="card bg-dark text-white shadow">
                  <div class="card-body">
                      Dark
                      <div class="text-white-50 small">#5a5c69</div>
                  </div>
                </div>
              </div>
            </div>
		--%>
            </div>

            <div class="col-lg-6 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"><%=E.EH("LOGISTICS FACILITIES")%></h6>
                </div>
                <div class="card-body">
                  <div id="divTrmlFcty">
                  
		  </div>
                </div>
              </div>
		<%--
              <!-- Approach -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Development Approach</h6>
                </div>
                <div class="card-body">
                  <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce CSS bloat and poor page performance. Custom CSS classes are used to create custom components and custom utility classes.</p>
                  <p class="mb-0">Before working with this theme, you should become familiar with the Bootstrap framework, especially the utility classes.</p>
                </div>
              </div>
		--%>
            </div>
          </div>

        </div>
        <input type="hidden" name="toPage1" id="toPage1" value="TrmlDashboard" />
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <%--<span>Copyright &copy; Your Website 2020</span>--%>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="/RailSAHAY/resources/js/trmldb/jquery.min.js"></script>
  <script src="/RailSAHAY/resources/js/trmldb/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/RailSAHAY/resources/js/trmldb/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/RailSAHAY/resources/js/trmldb/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="/RailSAHAY/resources/js/trmldb/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="/RailSAHAY/resources/js/trmldb/chart-area-demo.js"></script>
  <script src="/RailSAHAY/resources/js/trmldb/chart-pie-demo.js"></script>

<script>		
   var cmdtstr='';
   for (var i=0; i < aRakeCmdt.length;++i){
        cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for station
   }
   var my_list=document.getElementById("cmdtlist");
   my_list.innerHTML = cmdtstr;
</script>

<%@ include file="/pages/GG_Footer.jspf" %>