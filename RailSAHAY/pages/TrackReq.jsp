<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TRACK REQUEST","/RailSAHAY/pages/TrackReq.jsp",UserDevice,Browser);

 String CRid=(String)request.getParameter("REF");
      
      if(CRid==null){
        CRid="";      
      }
      System.out.println("ref:"+CRid);

%>
<style>
.ftco-cover-1.overlay:before
{
	opacity: .85 !important;
}
</style>
<script src="/RailSAHAY/resources/js/miscutil.js"></script>
<script>
$(document).ready(function()
{
trackConcern('<%=CRid%>');
});



function ajax_download(fileN,reId) {
   // alert(fileN+" ID: "+reId);
    var urlS="/RailSAHAY/UserDashboard";
    var $iframe,
        iframe_doc,
        iframe_html="";

    if (($iframe = $('#download_iframe')).length === 0) {
        $iframe = $("<iframe id='download_iframe'" +
                    " style='display: none' src='about:blank'></iframe>"
                   ).appendTo("body");
    }else{
        $('#download_iframe').remove();
        $iframe = $("<iframe id='download_iframe'" +
                    " style='display: none' src='about:blank'></iframe>"
                   ).appendTo("body");
    }

    iframe_doc = $iframe[0].contentWindow || $iframe[0].contentDocument;
    if (iframe_doc.document) {
        iframe_doc = iframe_doc.document;
    }

    iframe_html = "<html><head></head><body><form method='POST' action='"+urlS+"'>" +
                  "<input type=hidden name='callfor' value='FILE'/><input type=hidden name='custuserid' value=''/><input type=hidden name='fileName' value='"+fileN+"'/><input type=hidden name='reqId' value='"+reId+"'/></form>" +
                  "</body></html>";
//alert(iframe_html);
    iframe_doc.open();
    $('#download_iframe').empty();
    iframe_doc.write("");
    iframe_doc.write(iframe_html);
    $(iframe_doc).find('form').submit();
}
</script>
      <div class="ftco-blocks-cover-1" >
        <div class="ftco-cover-1 overlay" style="background-image: url('/RailSAHAY/resources/images/FrgtMvmt.jpg');padding-top:80px;">
          <div class="container">
            <div class="row align-items-top" >
             <div class="col-lg-6 col-sm-12">
		<div id="divTrckCncr"></div>
              </div>

            </div>
          </div>
        </div>
        <!-- END .ftco-cover-1 -->

<%@ include file="/pages/GG_Footer.jspf" %>
