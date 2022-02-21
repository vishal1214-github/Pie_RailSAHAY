
<%@ include file="/pages/GG_Header.jspf" %>
<%


String raiseCR=(String)request.getAttribute("CR");
      String CRid=(String)request.getAttribute("CRid");
      String CRreply=(String)request.getAttribute("CRreply");
      String CRerror=(String)request.getAttribute("CRerror");
      
%>
<link rel="stylesheet" href="/RailSAHAY/resources/css/concern.css">

	<script type="text/javascript" src="/RailSAHAY/resources/js/Cust.js"></script>


 
 
<div class="container-fullwidth register">
	                  <div class="row">
	                       <div class="col-md-3 register-left">
	                          <h3>Raise your Request!</h3>
	                         
						  	</div>
	                      <div class="col-md-9 register-right">
                             
	                          <div class="tab-content" id="myTabContent">
                                  
	                              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	                                 
	                        
                               
                    <div class="form-card">
                    <br/><br/>
                        <h2 class="fs-title text-center"><%=E.EH("Success")%> !</h2> <br><br>
                        <div class="row justify-content-center">
                            <div > <img src="https://img.icons8.com/color/96/000000/ok--v2.png" class="fit-image"> </div>
                        </div> <br><br>
                        <div class="row justify-content-center">
                            <div class="col-7 text-center">
                            
                                <p class="tktnumb"><%=E.EH(" Your Freight Request has been successfully registered with Reference No")%>: <b><%=CRid %></b></p>
                                <p class="scssmesg"><%=E.EH("Details are shared on your email.")%></p>
                            
    
                            </div>
                        </div>
                    </div>
              


                                                                         
	                                  
									
	                              </div>
	                             
	                          </div>
	                      </div>
	                  </div>

            </div>
            	
           
   <script type="text/javascript" src="/RailSAHAY/resources/js/GG_StopRefresh.js"></script>
       

   <%@ include file="/pages/GG_Footer.jspf" %>

