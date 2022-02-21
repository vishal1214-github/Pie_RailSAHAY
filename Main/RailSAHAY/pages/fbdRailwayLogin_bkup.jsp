<%@ include file="/pages/GG_Header.jspf" %>
<% 
 si_LangFlag ="E";  
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FBD RAILWAY LOGIN","/RailSAHAY/pages/fbdRailwayLogin.jsp",UserDevice,Browser);
%>
<%

String strBGColor="#337ab7";
	String strMessage="";
        if((String)request.getAttribute("message")!=null)
            strMessage =(String)request.getAttribute("message");
            
       System.out.println(strMessage);     
       
       String strProj = "FBD RAIL";
	String strProject = "";	
	if(strProj.equals("FBD RAIL"))
		strProject = "FBD RAILWAY LOGIN";

	String strLoadFunc="form_focus();";
	String strOnSubmitFunc="showRoleList()";
%>
<script language="JavaScript" src="/RailSAHAY/resources/js/GG_LoginPrtl.js"></script>
<script language="JavaScript1.2" src="/RailSAHAY/resources/js/GG_StopRefresh.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/portalform.js"></script>
<script language="JavaScript" src="/RailSAHAY/resources/js/GG_PopUpMsg.js"></script>
<script language="JavaScript" src="/RailSAHAY/resources/js/PopupWindow.js"></script>

<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/portalhomefoisweb.css" />
    
      <script type="text/javascript">  

function onSubmitForm()
{
 	onSubmitFocus();
 	return	<%=strOnSubmitFunc%>;
}
  
	function form_focus()
	{
		var si_ClntId	=	"<%=si_ClntId%>";
		var strLang = "<%=si_LangFlag%>";
		document.frmLogin.txtUserId.value               = "";
		document.frmLogin.txtPassword.value             = "";
		document.frmLogin.txtLocation.value             = "";
		document.frmLogin.txtUserId.focus();
		var strProjList     = "<%=strProj%>";
		<% String strEror   = (String)request.getParameter("Error");
		if(strEror != null)
		{%>
			show_popup("Error: ", "<%=strEror%>");
		<%}
		else
		{%>
		<%}%>
		document.frmLogin.txtUserId.focus();
	}
	function lock_sttn()
	{
		if(document.getElementById("txtOptnI").checked == true)
		{
			$("#txtLocation").val('IR');
			$("#txtLocation").attr('disabled', true);
		} 
		else
		{	
			$("#txtLocation").attr('disabled', false);
		} 
	}
	
</script>

<script language="JavaScript">
function resetForm()
{
	document.getElementById('txtUserName').value="";
	document.getElementById('txtPwd').value="";
	document.getElementById('txtLocation').value="";
	document.getElementById('txtUserName').focus();
}
</script>
<script>
	$(document).ready(function() {
		$("#imgLdngData").hide();
	});
	
</script>
    
    <%
 String msg=null;

try
		{
			java.util.Properties prop = new java.util.Properties();
			//java.io.FileInputStream propertyfile = new java.io.FileInputStream("/switch/Applications/foisweb_application/propertyfiles/WTCProperties.properties");
			java.io.FileInputStream propertyfile = new java.io.FileInputStream("/switch/Applications/foisweb_application/propertyfiles/WTCProperties.properties");
			prop.load(propertyfile);
			msg = prop.getProperty("msg");
			System.out.println("msg - "+msg);
		}catch(Exception e)
		{
			System.out.println("Error in reading property file "+e.getMessage());
		}
    
%>
     <body onload="<%=strLoadFunc%>">
        <div class="container-fullwidth">
            <?audit suppress oracle.ide.xml.mismatched-end-tag?><nav class="nav navbar-inverse" id="nav_bar">
	<div class="container-fluid">	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="mainNavBar">
			 <div class="navbar-header">
		      <a class="navbar-brand" href="#"><%=E.EH("FBD")%></a>
		    </div>
			
                 <ul class="nav navbar-nav navbar-right">
                     <li>
                     <a href="#"><span class="glyphicon"></span><img src="/RailSAHAY/resources/images/crismaplogo.png"></a>
                     </li>
                 </ul>
				
		</div>
	</div>
	<div>
	</div>
</nav>
<div name="myPopUpDiv" id="myPopUpDiv" style="background-color:white;font-size:smaller;border: 1px solid #87B3FF;padding:2px;color:red;position:absolute;visibility:hidden;"> </div>
<form name="frmLogin" id="frmLogin" autocomplete="off" class="form-inline" target="_self" method="post" onsubmit="return onSubmitForm()">
                 
   <div class="row">
                <div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-10 col-sm-offset-1 col-xs-12">

 
 <div class="panel panel-default" style="margin-top:5%;margin-right:5%;margin-left:5%;z-index: -1;">
 	<div class="panel-heading">
 		<b><%=strProject%></b>
 	</div>
 	<div class="panel-body">
	<div class="input-group" style="margin-top:10px;width:100%;">
		 <span class="input-group-addon" id="basic-addon2"><img src="/RailSAHAY/resources/images/UserIcon.png"></span>
		<input class="form-control input-md" id="txtUserId" name="txtUserId"  style="text-transform:uppercase"   maxlength="8" type="text" placeholder='<%=E.EH("Enter User Name")%>' >
	</div>
	<div class="input-group"  style="margin-top:10px;width:100%;"> 
		 <span class="input-group-addon" id="basic-addon2"><img src="/RailSAHAY/resources/images/pwdIcon2.png"> </span>
		<input class="form-control input-md" id="txtPassword" name="txtPassword"  style="text-transform:uppercase" onfocus="onPasswordFocus()" maxlength="20" type="password" placeholder='<%=E.EH("Enter Password")%>' >
	</div>
  <div align="center"  style="margin-top:10px;">
	<label class="radio-inline"><input type="radio" name="txtOptn" id="txtOptnD" value="D" onclick="lock_sttn();" checked="checked" ><%=E.EH("Division")%></label>
        <label class="radio-inline"><input type="radio" name="txtOptn" id="txtOptnZ" value="Z" onclick="lock_sttn();" ><%=E.EH("Zone")%></label>
        <label class="radio-inline"><input type="radio" name="txtOptn" id="txtOptnI"  onclick="lock_sttn();" value="I"><%=E.EH("IR")%></label>
	
       
	
	
	</div>
	<div class="input-group" style="margin-top:10px;width:100%;"> 
		 <span class="input-group-addon" id="basic-addon2"><img src="/RailSAHAY/resources/images/pin.png"> </span>
		<input class="form-control  input-md" id="txtLocation" name="txtLocation"  style="text-transform:uppercase" maxlength="4"  type="text"  placeholder='<%=E.EH("Locn")%>' >
	</div>
	<div align="center">
	<div class="input-group" style="margin-top:30px;" id="divRole" name="divRole"> 
		  <span class="input-group-addon" id="lblRole" style="visibility:hidden"><%=E.EH("Role")%> </span>		
	</div>
	</div>
	<br/>
	
	<div align="center">	
		<div class="form-group"> 	  					
			<input  type="text" class="form-control input-md"  id="captchaText" name="captchaText" placeholder='<%=E.EH("Captcha")%>' required="true"  maxlength="6" size="8" onkeypress="chkValue(event,'B');" title='<%=E.EH("Enter The Captcha Text in Proper Case")%>' ></input>
		</div>
		<div class="form-group"> 	  					
			<img id="captcha" name="captcha"    height="32px"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" alt="reload"></img><img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" id="imgReloadCaptcha"></img>
		</div>
	</div>
	
	<div align="center">
	<div class="input-group" style="margin-top:30px;"> 
		<input type="submit" class="btn btn-success btn-md" value='<%=E.EH("Submit")%>' id="Submit">&nbsp;
		<input type="reset" class="btn btn-danger btn-md" id="Reset" value='<%=E.EH("Reset")%>' onclick="ClntLogout()">
	</div>
	</div>
	<br/>
    <div class="alert alert-danger" align="center" id="errmsg" style="display:none;"></div>
	<div align="center" style="margin-top:10px;" id="divLdngData" name="divLdngData" > 
		<img src="/RailSAHAY/resources/images/LoadingData33.gif" id="imgLdngData" style="display :none;">
	</div>
</div>
</div>
	<input type="text" id="txtProj" name="txtProj" value="<%=strProj%>" style="visibility:hidden;text-transform=uppercase;" >
	<input type="text" id="txtClntId" name="txtClntId" value="<%=si_ClntId%>" style="visibility:hidden;text-transform=uppercase;">
    <input type="text" id="txtLangFlag" name="txtLangFlag" value="<%=si_LangFlag%>" style="visibility:hidden;text-transform=uppercase;" >
    <input type="hidden" id="hidLangFlagDiv" name="hidLangFlagDiv" value="<%=si_LangFlag%>" style="visibility:hidden;text-transform=uppercase;" >
</form>
 

</div>


</div>


	     <%@ include file="/pages/GG_Footer.jspf" %>

	    </div>

	    <script src="/RailSAHAY/resources/js/jquery-3.3.1.min.js"></script>
	    <script src="/RailSAHAY/resources/js/popper.min.js"></script>
	    <script src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
	    <script src="/RailSAHAY/resources/js/owl.carousel.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.sticky.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.waypoints.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.animateNumber.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.fancybox.min.js"></script>
	    <script src="/RailSAHAY/resources/js/jquery.easing.1.3.js"></script>
	    <script src="/RailSAHAY/resources/js/aos.js"></script>
	    <script src="/RailSAHAY/resources/js/stepform.js"></script>

	    <script src="/RailSAHAY/resources/js/main.js"></script>


	  </body>

	</html>
