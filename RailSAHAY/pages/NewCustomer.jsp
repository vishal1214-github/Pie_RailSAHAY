 <%@ include file="/pages/GG_Header.jspf" %>
 <% 
 
 strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"NEW CUSTOMER","/RailSAHAY/pages/NewCustomer.jsp",UserDevice,Browser);
%>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
 <script>
                var str='';
                for (var i=0; i < aSttn.length;++i){
                str += '<option value="'+aSttn[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("sttnfromlist");
                my_list.innerHTML = str;
                
                str="";            
                
                for (var i=0; i < gCNCode.length;++i){
                str += '<option value="'+gCNCode[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("cmdtlist");
                my_list.innerHTML = str;
                str="";            
                
                for (var i=0; i < gStck.length;++i){
                str += '<option value="'+gStck[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("wgonlist");
                my_list.innerHTML = str;
</script>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">HELP US TO SERVE YOU BETTER !</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
          
	  				<div class="col-lg-12 col-sm-12 ml-2 mr-2">
	  				<!-- MultiStep Form -->
	  				<div class="container-fluid" id="grad1">
	  				    <div class="row justify-content-center mt-0">
	  				        <div class="col-11 col-sm-11 col-md-11 col-lg-11 text-center p-0 mt-3 mb-2">
	  				            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
	  				                <p class="helpus">May we know a bit about you?</p>
	  				                <div class="row">
	  				                    <div class="col-md-12 mx-0">
	  				                        <form id="msform">
	  				                            <!-- progressbar -->
	  				                            <ul id="progressbar">
	  				                                <li class="active" id="account"><strong>Contact Detail</strong></li>
	  				                                <li id="personal"><strong>Company Profile</strong></li>
	  				                                <li id="confirm"><strong>Cargo Detail</strong></li>
	  				                            </ul> <!-- fieldsets -->
	  				                            <fieldset>
	  				                                <div class="form-card">
	  				                                    <h2 class="fs-title">Contact Detail</h2>
	  				                                    <input type="text" name="txtName" placeholder="Name" />
	  				                                    <input type="email" name="txtEmail" placeholder="Email" />
	  				                                    <input type="number" name="txtMob" placeholder="Mobile No." />
	                                                                      <input type="text" name="answer" id="answer" class="form-control" placeholder="Captcha" required>
	                                                                      <label for="answer">Captcha</label>
	                                                                      <img id="captcha" height="32px" style="background:#004d99;"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" ></img> <img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" onclick="refcapfunc();" ></img>
	  				                                </div>
	  				                                <input type="button" name="next" class="next action-button" value="Next Step" />
	  				                            </fieldset>
	  				                            <fieldset>
	  				                                <div class="form-card">
	  				                                    <h2 class="fs-title">Company Profile</h2>
	  				                                    <input type="text" name="txtCompName" placeholder="Company Name" />
	  				                                   <div class="dropdown">
	                                                                           <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Company Type</button>
	                                                                           <div class="dropdown-menu">
	                                                                             <a class="dropdown-item" href="#">Shipper</a>
	                                                                             <a class="dropdown-item" href="#">Receiver</a>
	                                                                             <a class="dropdown-item" href="#">Logistics Provider</a>
	                                                                             <a class="dropdown-item" href="#">Freight Forwarder</a>
	                                                                             <a class="dropdown-item" href="#">Other</a>
	                                                                           </div>
	                                                                      </div>												     <hr/>
	  				                                </div> 
	                                                                  <input type="button" name="previous" class="previous action-button-previous" value="Previous" /> <input type="button" name="next" class="next action-button" value="Next Step" />
	  				                            </fieldset>
	  				                            <fieldset>
	  				                                <div class="form-card">
	  				                                    <h2 class="fs-title">Cargo Detail</h2>
	                                                                          <div class="form-inline">
	  										<input type="text" list="sttnfromlist" id="txtOrgnLoc" name="txtOrgnLoc" placeholder="Origin Location"/>
	  										<datalist id="sttnfromlist"></datalist>
	                                                                      	</div>
	                                                                      <div class="form-inline">
	                                                                          <input type="text" list="sttnfromlist" id="txtDstnLoc" name="txtDstnLoc" placeholder="Destination Location"/>
	                                                                          <datalist id="sttnfromlist"></datalist>
	                                                                      </div>
	                                                                      <input type="text" list="cmdtlist" name="txtRakeCmdt" placeholder="Commodity" />
	                                                                      <datalist id="cmdtlist"></datalist>                                                                    
	                                                                      <div class="form-inline">
	                                                                        <label class="mr-sm-2" for="selPkgCndn">Packaging Condition</label>
	                                                                        <select class="custom-select mr-sm-2" id="selPkgCndn">
	                                                                          <option selected>Choose</option>
	                                                                          <option value="BL">Bulk Loose</option>
	                                                                          <option value="BAG">Bagged</option>
	                                                                          <option value="PBC">Pallet/Box/Carton</option>
	                                                                          <option value="BLQ">Bulk Liquid</option>
	                                                                          <option value="BG">Bulk Gas</option>
	                                                                          <option value="LS">Live Stock</option>
	                                                                          <option value="OT">Other</option>
	                                                                          <option value="NA">Not Known</option>



	                                                                        </select>
	                                                                      </div>
	                                                                      <br/>
	                                                                      <div  class="custom-control custom-control-inline">
	                                                                          <input type="number" name="txtVol" id="txtVol" style="width:180px;" placeholder="Volume of Traffic"/>
	                                                                          <select class="custom-select mr-sm-2" id="selVol">
	                                                                              <option selected>Unit</option>
	                                                                              <option value="T">Tonnes</option>
	                                                                              <option value="Q">Quintals</option>
	                                                                              <option value="P">Pieces</option>
	                                                                            </select>
	                                                                          <select class="custom-select mr-sm-2" id="selPerd">
	                                                                              <option selected>Period</option>
	                                                                              <option value="O">Only Once</option>
	                                                                              <option value="W">Per Week</option>
	                                                                              <option value="M">Per Month</option>
	                                                                              <option value="A">Per Year</option>
	                                                                              <option value="NA">Not Known</option>

	                                                                            </select>
	                                                                      </div>
	                                                                      <br/>
	                                                                      <span class="label label-default">Movement Type: </span>
	                                                                      <div class="custom-control custom-radio custom-control-inline">
	                                                                        <input type="radio" class="custom-control-input" id="MvmtScheduled" name="mvmttype">
	                                                                        <label class="custom-control-label" for="MvmtScheduled">Scheduled</label>
	                                                                      </div>
	                                                                      
	                                                                      <!-- Default inline 2-->
	                                                                      <div class="custom-control custom-radio custom-control-inline">
	                                                                        <input type="radio" class="custom-control-input" id="MvmtOnDmnd" name="mvmttype">
	                                                                        <label class="custom-control-label" for="MvmtOnDmnd">On-Demand</label>
	                                                                      </div>
	                                                                      
	                                                                      <!-- Default inline 3-->
	                                                                      <div class="custom-control custom-radio custom-control-inline">
	                                                                        <input type="radio" class="custom-control-input" id="AnyMvmt" name="mvmttype" checked>
	                                                                        <label class="custom-control-label" for="AnyMvmt">Any</label>
	                                                                      </div>
	                                                                      <br/><br/>
									      <%--
	                                                                      <input type="text" list="wgonlist" name="txtTrfcQty" placeholder="Wagon Type" />
	                                                                      <datalist id="wgonlist"></datalist>
									      --%>
	  				                                    <textarea name="txtRmrk" placeholder="Remark (Max 1000 Words)" rows="8"></textarea>
	  				                                </div>
	  				                                <input type="button" name="previous" class="previous action-button-previous" value="Previous" /> 
	                                                                  <input type="button" name="make_payment" class="next action-button" value="Submit" />
	  				                            </fieldset>
	  				                           <fieldset>
	  				                                <div class="form-card">
	  				                                    <h2 class="fs-title text-center">Success !</h2> <br><br>
	  				                                    <div class="row justify-content-center">
	  				                                        <div class="col-3"> <img src="https://img.icons8.com/color/96/000000/ok--v2.png" class="fit-image"> </div>
	  				                                    </div> <br><br>
	  				                                    <div class="row justify-content-center">
	  				                                        <div class="col-7 text-center">
	  				                                            <p class="scssmesg">Thank you for contacting us. For detail, kindly check your mail. We will reach you short</p>
	  				                                            <p class="tktnumb">PLEASE SAVE YOUR REFERENCE NUMBER AS : <b>SHA_NC_123456677</b></p>
	  				                                            <p class="bkngregprocess"><a href="/RailSAHAY/pages/BkngRegProcess.jsp" target="_self">BOOKING AND REGISTRATION PROCESS</a></p>
	  				                                            <p class="bkngregprocess"><a href="/RailSAHAY/pages/FrgtCalc.jsp" target="_self">FREIGHT CALCULATOR</a></p>
	  				                                            <p class="bkngregprocess"><a href="/RailSAHAY/pages/Policy.jsp" target="_self">SCHEMES AND DISCOUNTS</a></p>


	  				                                        </div>
	  				                                    </div>
	  				                                </div>
	  				                            </fieldset>
	  				                        </form>
	  				                    </div>
	  				                </div>
	  				            </div>
	  				        </div>
	  				    </div>
	  </div>
	  				</div>
          </div>
				</div>
				</div>
				</div>
      </div>
      </div>

	         <%@ include file="/pages/GG_Footer.jspf" %>

           
	  </body>

	</html>
