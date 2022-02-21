/*
*Created By	: Kunal Malhan
*Modified By: Raman Arora (On 30-01-2014 for Hindi Messages)
*Purpose	: Showing Pop up Menu for Errors...
*Created On	: 04-03-2006
*/

function show_popup(errorFieldName, errorMsg)
{
	var langFlag=document.getElementById('hidLangFlagDiv').value;

    if(errorFieldName == null || errorFieldName == "null")
    {
		if(langFlag=="H")
		{
			alert("पूछताछ से सम्बंधित इनपुट डाटा मान्य नहीं है \n फोयस के सहायक स्टाफ से संपर्क करें");   
		}
		else
		{
        	alert(errorMsg);
		}
    }
    else
    {
        var popUpLeft	= findPosX1(document.getElementById(errorFieldName));
        var popUpTop	= findPosY1(document.getElementById(errorFieldName)) + 20;
        
        // Create an object for a DIV window using the DIV named 'myPopUpDiv'
        var win         = new PopupWindow("myPopUpDiv"); 
    
        // Specify how many pixels to the right of the anchor the popup will appear
        win.offsetX     = 5;
        win.offsetY     = 2;
    
        win.x           = popUpLeft;
        win.y           = popUpTop;
        win.autoHide();
        win.populate("&nbsp;&nbsp;" + errorMsg + "&nbsp;&nbsp;");
        win.showPopup("test");
    }
}

function findPosX1(obj)
{
	var curleft = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curleft	+= obj.offsetLeft
			obj     = obj.offsetParent;
		}
	}
	else if (obj.x)
		curleft += obj.x;
	return curleft;
}

function findPosY1(obj)
{
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop	+= obj.offsetTop
			obj     = obj.offsetParent;
		}
	}
	else if (obj.y)
		curtop	+= obj.y;
	return curtop;
}
