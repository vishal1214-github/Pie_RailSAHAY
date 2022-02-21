<%@ include file="/pages/GG_Header.jspf" %>
<script src="/RailSAHAY/resources/js/trmlutil.js"></script>
<script src="/RailSAHAY/resources/js/prcldata.js"></script>
<script type="text/javascript">
  function iframeLoaded() {
      var iFrameID = document.getElementById('iFrmRailMadad');
      if(iFrameID) {
            // here you can make the height, I delete it first, then I make it again
            iFrameID.height = "";
            iFrameID.height = iFrameID.contentWindow.document.body.scrollHeight + "px";
      }   
  }
</script>   
<link rel="stylesheet"  href="/RailSAHAY/resources/css/prcldata.css">
<iframe src="https://railmadad.indianrailways.gov.in/madadtest/final/home.jsp" id="iFrmRailMadad" style="min-height:250vh;width:100vw;border:0pt;margin-top:10px;"></iframe>
<%@ include file="/pages/GG_Footer.jspf" %>
