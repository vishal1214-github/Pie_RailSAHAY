<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TIME TABLED ROUTES","/RailSAHAY/pages/TimeTblTran.jsp",UserDevice,Browser);
%>
<link rel="stylesheet"  href="/RailSAHAY/resources/css/prcldata.css">
<!--<link rel="stylesheet" href="/RailSAHAY/resources/css/MSFmultiSelect.css" />
<script src="/RailSAHAY/resources/js/msfmultiselect.js"></script> -->
<link href="/RailSAHAY/resources/css/multiselect.css" rel="stylesheet"/>
<script src="/RailSAHAY/resources/js/multiselect.min.js"></script>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script src="/RailSAHAY/resources/js/RgstSecCust.js"></script>


<script>
	$(document).ready(function(){
	//$("#divUserDataList").hide();
        fetchOrg("ORG",'', '', '', '') ;
        fetchOrg("GORG",'', '', '', '') ;
	//fetchTimeTblRout('','');
	});
</script>	

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-light">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12 ml-1">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("Add/Modify Secondary Customer")%></h3>
              </div>
            </div>
           </div>
          </div>
          <nav class="navbar navbar-expand-sm bg-light navbar-light">
	    <div class="col-lg-4 col-md-5 col-sm-12">
	      <form class="form-inline">
	          <table>
                  <tr>
                    <td>
                        <input class="form-control mr-sm-2 inptcap name" size="40" id="txtCustCode" onclick="fetchOrg('ORG' ,'', '', '', '')" name="txtCustCode" type="text" list="orglist" placeholder='<%=E.EH("CURRENT OWNER ORGANIZATION")%>'>
			          <datalist id="orglist"></datalist>
                    </td>
                  </tr>
                        <tr>
	          	<td>
                            <input class="form-control mr-sm-2 inptcap name" size="40" id="txtScCustCode" onclick="fetchOrg('GORG' ,'', '', '', '')" name="txtScCustCode" type="text" list="orgseclist" placeholder='<%=E.EH("SELECT SECONDARY CUSTOMER ORGANIZATION")%>'>
			          <datalist id="orgseclist"></datalist>
			</td>
                        <td><input class="form-control mr-sm-2 inptcap numb" id="txtMoblNumb" size="10"  name="txtMoblNumb" type="text" maxlength="10"  placeholder='<%=E.EH("Mobile Number")%>'>
				 
			</td>
                        <td><input class="form-control mr-sm-2 inptcap" id="txtEmailId" name="txtEmailId" size="30" type="text"  placeholder='<%=E.EH("EmailId")%>'>
				  
			</td>
			<td><button class="btn btn-data" onclick="event.preventDefault();getData();"><%=E.EH("Fetch")%></button></td></tr></table>
  	      </form>
  	     </div>
	</nav>
          <div class="row">
	<div class="col-lg-6 col-sm-12">
		<div id="divUserDataList" class="trmllistdata"></div>
      </div>
	<div class="col-lg-6 col-sm-12" id="divUserDtlsPnl">
		<div class="optncard">
			<ul class="nav nav-pills">
			    <li class="nav-item">
			        <a class="nav-link active" data-toggle="tab" href="#Schd"><%=E.EH("User Profile")%></a>
			    </li>
			</ul>
		</div>
		<div class="optncard">
			<div class="tab-content">
				  <div class="tab-pane container active" id="Schd">
					<div id="divUserDtls" class="trmllistdata mr-2 timeline-centered"></div>
                                       <div id="example"> 
                                       <table>
                                       <tr>
                                        <th style="width:25%">Zone:</th>
                                        <th style="width:25%">Division:</th>
                                        <th style="width:25%">Station:</th>
                                        <th style="width:25%">ChargeType:</th></tr>
                                       <tr>
                                        <td><select id="multiselect"  class="form-control" name="languages[]" multiple="multiple"></select></td>
                                        <td> <select id="multiselectD"  class="form-control"  multiple="multiple"></select></td>
                                        <td> <select id="multiselectS"  class="form-control"  multiple="multiple"></select></td>
                                        <td> <select id="lstChrgtyp"  class="form-control"  >
                                             <option value="F">FREIGHT</option>
                                             <option value="D">DEMURRAGE</option>
                                             <option value="W">WHARFAGE</option>
                                             <option value="L">LOCAL</option> 
                                            </select></td>
                                        <td style="width:0"><label id="lblOrg"></label></td>
                                        <td><button class="btn btn-outline-primary btn-sm" style="padding: 0.1rem 0.3rem;" title="Add Secondary Customer" onclick="addRow();"><i class="fas fa-plus"></i></button></td>
                                    
                                        </tr>
                                        <tr>
                                         </tr>
                                       <!--  <td><button id="butadd" onclick="addRow();" >Add</button><br>
                                         <button id="butsave" onclick="savedata();" >Save</button></td></tr>  -->
                                         </table>                                         
                                         <table id="tblLocn" class="table table-striped table-sm table-pymthstr">
                                           <tr><th style="width:45%">ORGANIZATION</th><th style="width:10%">Zone</th><th style="width:10%">Division</th><th style="width:10%">Station</th><th style="width:15%">Charge Type</th><th style="width:10%">Cust Id</th></tr>
                                        </table>
                                        <center><button class="btn btn-outline-primary btn-sm" style="padding: 0.1rem 0.3rem;" title="Save Secondary Customer" onclick="savedata();"><i class="fas fa-save"></i></button></center>
                                        
                                          </div>
                                        <!-- <div class="form-group">
	                                              <input type="text" class="form-control" disabled placeholder="Customer Id" value="" tabindex="1" id="custid" name="custid" ; />
	                                          </div>
                                         <div class="form-group">
	                                              <input type="text" class="form-control" disabled placeholder="Name" value="" tabindex="1" id="firstname" name="firstname" ; />
	                                          </div>
	                                          <div class="form-group">
                                                             <input type="email" id="email" name="email" tabindex="3" placeholder="Email" value=""    style="text-transform:lowercase;" class="form-control">
	                                            </div>
	                                          <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Category" value="" id="ctgrOth" name="ctgrOth" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Gender" value="" id="gndr" name="gndr" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Address" value="" id="address" name="address" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Pincode" value="" id="pincode" name="pincode" disabled  />
	                                          </div>
                                                   <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Date Of Birth" value="" id="dob" name="Dob" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Sec. emailId" value="" id="secemailid" name="secemailid" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Sec. Mobile Numb" value="" id="smoblnumb" name="smoblnumb" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="id1" value="" id="id1" name="id1" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="doc1" value="" id="doc1" name="doc1" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="id2" value="" id="id2" name="id2" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="doc2" value="" id="doc2" name="doc2" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="id3" value="" id="id3" name="id3" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="doc3" value="" id="doc3" name="doc3" disabled  />
	                                          </div>
                                                   <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="apvldate" value="" id="apvldate" name="apvldate" disabled  />
	                                          </div>
                                                   <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="rolecode" value="" id="rolecode" name="rolecode" disabled  />
	                                          </div>
                                                   <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="Digital Sign flag" value="" id="dgtlsignflag" name="dgtlsignflag" disabled  />
	                                          </div>
                                                  <div class="form-group">
	                                              <input type="text" class="form-control" tabindex="13" placeholder="FFS flag" value="" id="ffsflag" name="ffsflag" disabled  />
	                                          </div>-->
                                                  
				  </div>
				 <!-- <div class="tab-pane container fade" id="Mvmt">
					<div id="divTTMvmt" class="trmllistdata mr-2"></div>
				  </div>-->
			</div>
		</div>
      </div>
      </div>
        <script>
            
           var str='';
           for (var i=0; i < lstOrg.length;++i){
            str += '<option value="'+lstOrg[i]+'" />'; // Helplist for station
            }
            var my_list=document.getElementById("orglist");
            my_list.innerHTML = str;
            //alert("SS:"+str);
           // var my_list1=document.getElementById("locnlistto");
            //my_list1.innerHTML = str;*/f +
            
            function getData()
            {
                    if($("#txtCustCode").val() == "")
                    {
                            cuteToast({
                              type: "info",
                              message: "Specify Primary Customer Organization",
                              timer: 5000
                            });    
                            return;
                    }
	            var cust=$("#txtScCustCode").val();
                    var custcode = '';
	            var emailid=$("#txtEmailId").val();
                    var moblnumb=$("#txtMoblNumb").val();
	            var station = '';
	            if(cust=="" && emailid=="" && moblnumb=="")
                    {
                        
                                 cuteToast({
                              type: "info",
                              message: "Specify filters to select Secondary Customer Organization",
                              timer: 5000
                            })          
                    }
                    else
	            {
                        if(cust.indexOf("(")>-1)
			{
				//var c=cust.substring(cust.indexOf("-")+1);
				custcode=(cust.substring(1,cust.indexOf(")"))).trim();
			}	
			else
			{
				custcode=cust.toUpperCase();
			}
                        fetchData("DATA",moblnumb, emailid, station, custcode) ;
		    }
                
            }            
             </script>
           <script src="/RailSAHAY/resources/js/multiselect.min.js"></script>    		
           

<%@ include file="/pages/GG_Footer.jspf" %>
