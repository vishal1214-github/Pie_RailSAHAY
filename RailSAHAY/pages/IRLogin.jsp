<%@ include file="/pages/GG_HeaderLogin.jspf" %>
<% 
 si_LangFlag ="E";  
strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FBD RAILWAY LOGIN","/RailSAHAY/pages/fbdRailwayLogin.jsp",UserDevice,Browser);
%>
<%

	String strMessage="";
        if((String)request.getAttribute("message")!=null)
            strMessage =(String)request.getAttribute("message");
            
       System.out.println(strMessage);     
       
       String strProj = "FBD RAIL";
	String strProject = "";	
	if(strProj.equals("FBD RAIL"))
		strProject = "FBD RAILWAY LOGIN";

%>
<script language="JavaScript" src="/RailSAHAY/resources/js/GG_LoginPrtl.js"></script>
<script language="JavaScript1.2" src="/RailSAHAY/resources/js/GG_StopRefresh.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/portalform.js"></script>
<script language="JavaScript" src="/RailSAHAY/resources/js/GG_PopUpMsg.js"></script>
<script language="JavaScript" src="/RailSAHAY/resources/js/PopupWindow.js"></script>

<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/portalhomefoisweb.css" />
<link rel="stylesheet" href="/RailSAHAY/resources/css/theme/edmndloginRed.css" />
    
      <script type="text/javascript">  

function onSubmitForm()
{
 	onSubmitFocus();
 	return	showRoleList();
}
  
	function form_focus()
	{
		var si_ClntId	=	"<%=si_ClntId%>";
		var strLang = "<%=si_LangFlag%>";
                $("#txtUserId").val("");
                $("#txtPassword").val("");
                $("#txtLocation").val("");
                $("#txtUserId").focus();
                
		var strProjList     = "<%=strProj%>";
		<% String strEror   = (String)request.getParameter("Error");
		if(strEror != null)
		{%>
			show_popup("Error: ", "<%=strEror%>");
		<%} %>
                $("#txtUserId").focus();      
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
                $("#txtUserId").val("");
                $("#txtPassword").val("");
                $("#txtLocation").val("");
                $("#txtUserId").focus();
}
</script>
<script>
	$(document).ready(function() {
                form_focus();
		$("#imgLdngData").hide();
	});
	
</script>
    
    
		  <div class="container">
		    <div class="row">
		      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
		        <div class="card card-signin my-5">
		          <div class="card-body">
		            <h5 class="card-title text-center">IR Log-In</h5>
<div name="myPopUpDiv" id="myPopUpDiv" style="background-color:white;font-size:smaller;border: 1px solid #87B3FF;padding:2px;color:red;position:absolute;visibility:hidden;"> </div>
<form name="frmLogin" id="frmLogin" autocomplete="off" class="form-inline" target="_self" method="post" onsubmit="return onSubmitForm();">
                 
   <div class="row">
     <div class="col-lg-12 col-sm-12">
 
 <div class="panel panel-default" style="margin-top:5%;margin-right:5%;margin-left:5%;z-index: -1;">
 	<div class="panel-body">
	<div class="input-group" style="margin-top:10px;width:100%;">
		<input class="form-control input-md" id="txtUserId" name="txtUserId"  style="text-transform:uppercase"   maxlength="8" type="text" placeholder='<%=E.EH("User Name")%>' >
	</div>
	<div class="input-group"  style="margin-top:10px;width:100%;"> 
		<input class="form-control input-md" id="txtPassword" name="txtPassword"  style="text-transform:uppercase" onfocus="onPasswordFocus()" maxlength="20" type="password" placeholder='<%=E.EH("Password")%>' >
	</div>
    <table class="table mt-1 mb-1">
    <tr><td style="width:33%;"><label class="radio-inline"><input type="radio" name="txtOptn" style="height:1rem;" id="txtOptnD" value="D" onclick="lock_sttn();" checked="checked" ><%=E.EH("Division")%></label></td>
        <td style="width:33%;"><label class="radio-inline"><input type="radio" name="txtOptn" style="height:1rem;" id="txtOptnZ" value="Z" onclick="lock_sttn();" ><%=E.EH("Zone")%></label></td>
        <td style="width:33%;"><label class="radio-inline"><input type="radio" name="txtOptn" style="height:1rem;" id="txtOptnI"  onclick="lock_sttn();" value="I"><%=E.EH("IR")%></label></td>
        </tr></table>
	<div class="input-group mt-2"> 
		<input class="form-control  input-md" id="txtLocation" name="txtLocation"  style="text-transform:uppercase" maxlength="4"  type="text"  placeholder='<%=E.EH("location")%>' >
	</div>
	<div align="center" class="mt-2">
            <table>
            <tr><td>
		<div class="form-group"> 	  					
			<input  type="text" class="form-control input-md"  id="captchaText" name="captchaText" placeholder='<%=E.EH("Captcha")%>' required="true"  maxlength="6" size="8" onkeypress="chkValue(event,'B');" title='<%=E.EH("Enter The Captcha Text in Proper Case")%>' ></input>
		</div>
                </td>
                <td>
		<div class="form-group"> 	  					
			<img id="captcha" name="captcha"    height="32px"  width="110"  src='/RailSAHAY/SahayCaptcha.jpg' align="middle" alt="reload"></img><img vspace="5px" src="/RailSAHAY/resources/images/reload.gif" alt="reload" id="imgReloadCaptcha"></img>
		</div>
                </td>
                </tr>
                </table>
	</div>
	<div align="center" class="mt-1 mb-2">
	<div class="input-group" id="divRole" name="divRole"> 
		  <span class="input-group-addon" id="lblRole" style="visibility:hidden"><%=E.EH("Role")%> </span>		
	</div>
	</div>
	<div align="center">
        <div class="btn-group btn-group-sm">
  <button type="submit" class="btn btn-primary"><%=E.EH("Submit")%></button>&nbsp;&nbsp;
  <button type="reset" class="btn btn-secondary" onclick="ClntLogout()"><%=E.EH("Reset")%></button>
</div>
	</div>
	<br/>
    <div class="alert alert-danger" align="center" id="errmsg" style="display:none;"></div>
	<div align="center" style="margin-top:10px;" id="divLdngData" name="divLdngData" > 
		<img src="/RailSAHAY/resources/images/LoadingData33.gif" id="imgLdngData" style="display :none;">
	</div>
</div>
</div>
<table><tr><td><input type="text" id="txtProj" name="txtProj" value="<%=strProj%>" style="visibility:hidden;text-transform=uppercase;" ></td>
	<td><input type="text" id="txtClntId" name="txtClntId" value="<%=si_ClntId%>" style="visibility:hidden;text-transform=uppercase;"></td>
    <td><input type="text" id="txtLangFlag" name="txtLangFlag" value="<%=si_LangFlag%>" style="visibility:hidden;text-transform=uppercase;" ></td>
    <td><input type="hidden" id="hidLangFlagDiv" name="hidLangFlagDiv" value="<%=si_LangFlag%>" style="visibility:hidden;text-transform=uppercase;" ></td></tr></table>
    </div>
    </div>
</form>
</div>
</div>
</div>
</div>
</div>



	     <%@ include file="/pages/GG_FooterLogin.jspf" %>
