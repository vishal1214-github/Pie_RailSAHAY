var strWinProp;

strWinProp = " toolbar=no"			//Back, Forward, etc...
+ ",location=no"					//URL field
+ ",directories=no"					//"What's New", etc...
+ ",status=yes"						//Status Bar at bottom of window.
+ ",menubar=no"						//Menubar at top of window.
+ ",resizeable=no"					//Allow resizing by dragging. (Yes - Does not work with Netscape or IE)
+ ",scrollbars=no"					//Displays scrollbars is document is larger than window.
+ ",titlebar=no"					//Enable/Disable titlebar resize capability.
+ ",width="+screen.availWidth
+ ",height="+(screen.availHeight-30)
+ ",top=0"							//Offset of windows top edge from screen.
+ ",left=0"							//Offset of windows left edge from screen.  
+ "";


var strGnrlRprtFlag="";
function processRequest()
{
	// readyState of 4 signifies request is complete
				//alert("msg"+req.readyState);
	if (req.readyState == 4)
	{
		// status of 200 signifies sucessful HTTP call
		if (req.status == 200)
		{
			if (callback)
			{
				callback(req.responseXML);
			}
		}
	}
}

/* This Function performs the AJAX Communication.                   *
 * The Communication is Syncronized. i.e. while AJAX communication	*
 * User cannot perform any other action.                            *
 * Communication method is GET here.                                *
 * inputs     - 'url' indicates the Target Servlet                  *
 *              'callback' is name of function that manipulate data.*/

function AJAXInteraction(url, callback)
{
	var req = init();

	function init()
	{
		if (window.XMLHttpRequest)
		{
			return new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	req.onreadystatechange = function()
	{
		// readyState of 4 signifies request is complete
		//alert("msg"+req.readyState);
		if (req.readyState == 4)
		{
			// status of 200 signifies sucessful HTTP call
			if (req.status == 200)
			{
				if (callback)
				{
					callback(req.responseXML);
				}
			}
		}
	};
	this.doGet = function()
		{
			req.open("GET", url, true);
			req.send(null);
		}
}
var uniElementID	= "";
var isEr            = 0;

/* This Function performs the Manipulation on the data                  *
 * that is responsed by called servlet.                                 *
 *                                                                      *
 * inputs     - 'responseXML' is the response string by called servlet.	*/

function helpCallback(responseXML)
{
	document.getElementById("imgLdngData").style.display="none";
	var langFlag=document.getElementById('hidLangFlagDiv').value;
	if(responseXML.getElementsByTagName("data")[0] == null)
	{
	}
	else if(responseXML.getElementsByTagName("data")[0].firstChild == null)
	{
		
			//alert("You Don't have any Role.");
	        document.getElementById("errmsg").innerHTML = "You Don't have any Role.";
	        document.getElementById("errmsg").style.display = "block";
	}
	else
	{
		 var msg		= responseXML.getElementsByTagName("data")[0].firstChild.nodeValue;
		//var msg		= "true/RMS/TMS/ZONAL/TOPSHEET/RMS QUERIES/ARCHIVAL/DIVISIONAL";

		var moption	= msg.split("/");
		//alert("Deva Next: " + moption.length);
		if(moption.length == 1)
		{
			isEr	= 1;
			var strErorCode     = msg.substring(1, msg.indexOf('#',1));
			if((strErorCode == "70001")||(strErorCode == "70005"))
			{
				document.getElementById("txtUserId").focus();
				document.getElementById("txtUserId").select();

				
	             	document.getElementById("errmsg").innerHTML = "Not an Authentic User or User Id/Password Incorrect";
	        		document.getElementById("errmsg").style.display = "block";
					//show_popup("txtUserId", "Not an Authentic User or User Id/Password Incorrect");
				
			}
			
			else if(strErorCode == "50012" || strErorCode == "50003" || strErorCode == "70022")
			{
				document.getElementById("txtLocation").focus();
				document.getElementById("txtLocation").select();
				//show_popup("txtLocation", msg);
	            document.getElementById("errmsg").innerHTML = msg;
	        document.getElementById("errmsg").style.display = "block";
			}
			else
			{
	            document.getElementById("errmsg").innerHTML = msg;
	        document.getElementById("errmsg").style.display = "block";
				//alert("Error: " + msg);
			}
		}
		else
		{
			if(moption[1]=="ALREADY_LOGIN")
			{
				var retVal = confirm("User already Logged-In. Earlier session will be terminated. Click OK to Continue.");
				var userid=document.getElementById("txtUserId").value;
					var proj = document.getElementById("txtProj").value;
				var ret=false;
				if( retVal == true ) {
					ret=LogoutOthrSesn(userid,proj);
					if(ret)
					{
						for (i = 2; i < moption.length; i++)
						{
							if(i == 2)
								optionItem	= new Option( moption[i], moption[i], false,  true);
							else
								optionItem	= new Option( moption[i], moption[i], false, false);
					
							var tagetSelect	= document.getElementById(uniElementID);
							tagetSelect.options[i-2]	= optionItem;
						}
					}
				} else {
					ret=LogoutCrntSesn(userid,proj);
					if(ret)
					{
						location.reload();
					}
				}
			}
			else
			{
				for (i = 2; i < moption.length; i++)
				{
					if(i == 2)
						optionItem	= new Option( moption[i], moption[i], false,  true);
					else
						optionItem	= new Option( moption[i], moption[i], false, false);
			
					var tagetSelect	= document.getElementById(uniElementID);
					tagetSelect.options[i-2]	= optionItem;
				}
			}
	}
		showRoleListAfterAjax();
	}
}


var stopProsses	= 0;

/* This Function Call ATVLDTUSER service perform the User Authentication and	*
 * Authorization and Load the different Roles that a User has.			*/
function showRoleList()
{
	document.getElementById("imgLdngData").style.display = 'block';
	document.getElementById("errmsg").innerHTML = "";
	document.getElementById("errmsg").style.display = "none";
	var langFlag=document.getElementById('hidLangFlagDiv').value;
	if(document.getElementById("lblRole").style.visibility	== "hidden")
	{
		if(document.getElementById("txtUserId").value == "")
		{
			document.getElementById("txtUserId").focus();
			
				//show_popup("txtUserId", " Please enter a Valid User ID !!");
	            document.getElementById("errmsg").innerHTML = "Please enter a Valid User ID";
	        	document.getElementById("errmsg").style.display = "block";
			
			document.getElementById("imgLdngData").style.display = 'none';
			//document.getElementById('imgLdngData').style.visibility="hidden";
			return false;
		}
		else if(document.getElementById("txtPassword").value == "")
		{
			document.getElementById("txtPassword").focus();
		
				//show_popup("txtPassword", " Please enter a Valid Password !!");
	            document.getElementById("errmsg").innerHTML = "Please enter a Valid Password";
	        document.getElementById("errmsg").style.display = "block";
		

				//document.getElementById('imgLdngData').style.visibility="hidden";
			document.getElementById("imgLdngData").style.display = 'none';
			return false;
		}
		else if(document.getElementById("txtLocation").value == "")
		{
			
				if(document.getElementById("txtOptnI").checked == false)
				{
					
						//show_popup("txtLocation", " Please enter a Valid Location Code !!");
	           			document.getElementById("errmsg").innerHTML = "Please enter a Valid Location Code";
	        			document.getElementById("errmsg").style.display = "block";
				
					//document.getElementById('imgLdngData').style.visibility="hidden";
					document.getElementById("imgLdngData").style.display = 'none';
					return false;
				}
			
		}
var a	=	callValidateCaptcha();
		if(a==false)
		{
			return false;
		}

		var divTarget               = document.getElementById("divRole");
		divTarget.style.visibility	= "hidden";

		var TempTarget              = document.getElementById("selRole");
		if(TempTarget != null)
			divTarget.removeChild(TempTarget);

		var target                  = document.createElement("SELECT");
		target.name                 = "sel";
		target.id                   = "selRole";
		target.style.width          = "128";
		target.style.height          = "40";
		target.style.color          = "navy";
		target.style.fontFamily     = "monospace";
		target.style.fontWeight     = "normal";
		target.style.fontSize     = "16";


		/* This Function is Key Handler for New Select Cantaining Role List.		*
		 * Action is performed only when Enter key is pressed. It submits form		*
		 * on pressing Enter.                                                       */
	    target.onkeypress			= function(event)
		{
                   	var eve;
			try
			{
				if(event.keyCode)
					eve			= event.keyCode;
				else
					eve			= event.charCode;
			}
			catch(ex)
			{
				if(window.event.keyCode)
					eve			= window.event.keyCode;
				else
					eve			= window.event.charCode;
			}

			if(eve == 13)
			{

				projNavigation();
				//document.getElementById("frmLogin").submit();
			}
		};
		divTarget.appendChild(target);
		//Condition for All Option should be here based on txtProj List
		var varOptn	= "";
		var varVald = "";
		
		
			varVald = "Z";
			if(document.getElementById("txtOptnZ").checked == true)
				varOptn	= "Z";
			if(document.getElementById("txtOptnI").checked == true)
				varOptn	= "I";
                        if(document.getElementById("txtOptnD").checked == true)
				varOptn	= "D";        
	
		try
		{
			uniElementID	= "selRole";
			var url		=    "/RailSAHAY/GG_LognActn?txtClientId="	+ document.getElementById("txtClntId").value
								+ "&txtUserId="		+ document.getElementById("txtUserId").value
								+ "&txtPassword="	+ encodeURIComponent(document.getElementById("txtPassword").value)
								+ "&txtOptn="		+ varOptn
								+ "&txtLocation="	+ document.getElementById("txtLocation").value
								+ "&txtProj="		+ document.getElementById("txtProj").value
								+ "&txtLangFlag="		+ langFlag
								+ "&txtValdFlag="	+ varVald
								+ "&txtWidth="      + screen.availWidth
								+ "&txtHeight="     + screen.availHeight;
			var ajax	= new AJAXInteraction(url, helpCallback);
			ajax.doGet();
		}
		catch(e)
		{
			form_focus();
			document.getElementById("imgLdngData").style.display = 'none';
	        document.getElementById("errmsg").innerHTML = "Service is Not Currently Available.\nService Will Available Soon.\nContact OCC.";
	        document.getElementById("errmsg").style.display = "block";

		}
	}
	else
	{
		if(stopProsses == 1)
		{
			stopProsses	= 0;
			document.getElementById("imgLdngData").style.display = 'block';
			return false;
		}
		else
		{
			projNavigation();
			document.getElementById("imgLdngData").style.display = 'block';
			return true;
		}
	}
	return false;
}

function showRoleListAfterAjax()
{
	if(isEr == 0)
	{
		document.getElementById("txtUserId").readOnly		= true;
		document.getElementById("txtPassword").readOnly		= true;

		
			document.getElementById("txtOptnZ").disabled = true;
                        document.getElementById("txtOptnD").disabled = true;
			document.getElementById("txtOptnI").disabled = true;
		

		document.getElementById("txtLocation").readOnly		= true;
		document.getElementById("lblRole").style.visibility	= "visible";
		document.getElementById("divRole").style.visibility = "visible";
		document.getElementById("selRole").focus();
	}
	else
	{
		document.getElementById("divRole").style.visibility	= "hidden";

		isEr                        = 0;
	}
}

function ClntLogout()
{
	document.getElementById("errmsg").innerHTML = "";
	        document.getElementById("errmsg").style.display = "none";
	if(document.getElementById("lblRole").style.visibility	== "visible")
	{
		var divTarget               = document.getElementById("divRole");
		divTarget.style.visibility	= "hidden";

		var TempTarget              = document.getElementById("selRole");
		if(TempTarget != null)
			divTarget.removeChild(TempTarget);

		var target                  = document.createElement("SELECT");
		target.style.visibility     = "hidden";
		target.name                 = "sel";
		target.id                   = "selRole";

		divTarget.appendChild(target);
		uniElementID                = "selRole";

		var url     =    "/RailSAHAY/GG_LgotActn?txtClientId="	+ document.getElementById("txtClntId").value
							+ "&txtUserId="                     + document.getElementById("txtUserId").value;
		var ajax	= new AJAXInteraction(url, helpCallback);
		ajax.doGet();

		target.style.visibility		= "hidden";
		divTarget.style.visibility	= "hidden";
	}
	//Condition for All Option should be here based on txtProj List
	document.getElementById("lblRole").style.visibility	= "hidden";
	document.getElementById("txtUserId").readOnly		= false;
	document.getElementById("txtPassword").readOnly		= false;
	document.getElementById("txtLocation").readOnly		= false;

	
		document.getElementById("txtOptnZ").disabled = false;
                document.getElementById("txtOptnD").disabled = false;
		document.getElementById("txtOptnI").disabled = false;
		document.getElementById("txtLocation").style.visibility = "visible";
	
	document.getElementById("txtLocation").readOnly		= false;
	form_focus();
}

/***/
function onPasswordFocus()
{
	document.getElementById("txtPassword").select();
}

/***/
function onPasswordBlur()
{
	document.getElementById("txtPassword").value  = document.getElementById("txtPassword").value;
}

/***/
function onSubmitFocus()
{
	if(document.getElementById("txtUserId").readOnly == true)
	{
		if(document.getElementById("selRole")   != null)
		{
		    document.getElementById("selRole").focus();
        	projNavigation();
		}
	}
}


function LogoutOthrSesn(userid,proj)
{
	try
	{
		var url		=    "/RailSAHAY/GG_SesnMntc?userid="+userid+"&proj="+proj+"&flag=O";
		var ajax	= new AJAXInteraction(url, helpCallbackSesn);
		ajax.doGet();
	}
	catch(e)
	{		
			alert("Service is Not Currently Available.\nService Will Be Available Soon.\nContact OCC.");			
	}
	return true;
}
function LogoutCrntSesn(userid,proj)
{
	try
	{
		var url		=    "/RailSAHAY/GG_SesnMntc?userid="+userid+"&proj="+proj+"&flag=C";
		var ajax	= new AJAXInteraction(url, helpCallbackSesn);
		ajax.doGet();
	}
	catch(e)
	{		
			alert("Service is Not Currently Available.\nService Will Available Soon.\nContact OCC.");			
	}
	return true;
}

function helpCallbackSesn(responseXML)
{
     var msg		= responseXML.getElementsByTagName("data")[0].firstChild.nodeValue;
	 if(msg!='SUCCESS')
	 {
		 alert("Error:-"+msg);
		 return false;
	 }
	 else
	 {
		return true;
	 }
}





function projNavigation()
{

	var langFlag=document.getElementById('hidLangFlagDiv').value;
	
		
			
				document.getElementById("frmLogin").target = "_top";
				
					document.getElementById("frmLogin").action	= '/RailSAHAY/pages/Welcome.jsp';
			
                document.getElementById("frmLogin").submit();
			
				
        	
}