<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FNR ENQUIRY","/RailSAHAY/pages/FNREnquiryIN.jsp",UserDevice,Browser);
%>
<style>
.ftco-cover-1.overlay:before
{
	opacity: .85 !important;
}
</style>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>
      <div class="ftco-blocks-cover-1" >
        <div class="ftco-cover-1 overlay" style="background-image: url('/RailSAHAY/resources/images/FrgtMvmt.jpg');padding-top:80px;">
          <div class="container">
            <div class="row align-items-top" >
              <div class="col-lg-6 col-sm-12">
                <h2><%=E.EH("Track status of your Consignment (FNR)")%></h2>
                <p class="mb-5"><%=E.EH("Know the recently updated status of your cargo")%> !</p>
                <form action="/RailSAHAY/pages/FNREnquiryOT.jsp" method="POST" id="FnrEnquiry">
                  <div class="form-group d-flex">
                    <input type="text" class="form-control inptcap numb" maxlength="11" id="txtFnrNumb" name="txtFnrNumb" placeholder='<%=E.EH("Enter your 11-digit FNR Number")%>'/>
                    <input type="button" class="btn btn-primary text-white px-4" value='<%=E.EH("Track Now")%>' id="subFNR" onclick="trackFNR()"/>
                  </div>
                </form>
              </div>
              <div class="col-lg-6 col-sm-12">
		<div id="divTrckCncr"></div>
              </div>

            </div>
          </div>
        </div>
        <!-- END .ftco-cover-1 -->

<%@ include file="/pages/GG_Footer.jspf" %>
