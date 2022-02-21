	history.forward();
  /*
Function : fmtTime
Created By : Raman Arora
On Dated : 19-01-2010
Purpose : To Validate and Apply A Mask On Time Factor
Use: Call This Function on the 'onkeyup' event of TextBox as 
	 onkeyup="fmtTime('txt1',event);"
*/
function fmtTime(cntl, entity) //Added for Checking the input fields type validation
{
    var unicode		= entity.keyCode ? entity.keyCode : entity.charCode;  
    var tempdate;
    var tempdate2;
    var langFlag="";
    try
    {
	langFlag=document.getElementById('hidLangFlagDiv').value;
    }
    catch(err)
    {
	langFlag="E";
    }


    
    if((unicode==46) || (unicode==8))   //Delete Pressed
	{
		entity.returnValue =  true;	
	}
    if(entity.srcElement.readOnly == false)
    {	       
        if((unicode<=48 && unicode>=57) && (unicode!=13) && (unicode!=58)  )
        {
            entity.returnValue =  false;
        }
        else
        {
	     	if((document.getElementById(cntl).value).length==5)
	     	{
		     	if(((document.getElementById(cntl).value).substr(3,2)) > 59 ||((document.getElementById(cntl).value).substr(3,2)) < 0 )
		     	{
					if(langFlag=="H")
					{
			     		alert('इनपुट समय का फॉर्मेट मान्य नहीं है');
					}
					else
					{
			     		alert('Not A Valid Time');
					}
		    		document.getElementById(cntl).select();
			     	entity.returnValue =  false;
		     	}
	     	}	     		     	
	     	if(((document.getElementById(cntl).value).length==3) && ((document.getElementById(cntl).value).indexOf(':')<0))
	     	{
		     	tempdate=(document.getElementById(cntl).value).substr(0,2);
		     	tempdate2=(document.getElementById(cntl).value).substr(2,1);
		     	document.getElementById(cntl).value=tempdate+':'+tempdate2;
	     	}
        }
        if((document.getElementById(cntl).value).length==2)
        {
	       if(document.getElementById(cntl).value > 23 || document.getElementById(cntl).value <0)
	       {
					if(langFlag=="H")
					{
			     		alert('इनपुट समय का फॉर्मेट मान्य नहीं है');
					}
					else
					{
			     		alert('Not A Valid Time');
					}
		    	document.getElementById(cntl).select();
		    	entity.returnValue = false;   
	       }
	       else
	       {
		       if(!((unicode==46) || (unicode==8)))
		       {
	       			document.getElementById(cntl).value=document.getElementById(cntl).value+':'; 
       		   }
	       		entity.returnValue =  true;
       	   }
       	   entity.returnValue =  true;
        }
       else
       {  
         if((document.getElementById(cntl).value).length==5)
         { 
	        if(document.getElementById(cntl).value > 59 || document.getElementById(cntl).value <0)
	        {  
					if(langFlag=="H")
					{
			     		alert('इनपुट समय का फॉर्मेट मान्य नहीं है');
					}
					else
					{
			     		alert('Not A Valid Time');
					}   	
		    	document.getElementById(cntl).select();
		    	entity.returnValue = false;               	
     	 	}
     	 	 entity.returnValue =  true;
 	 	}
      }	    
  }
}
	function keyPressed(evt)
	{
		var F1			= 112;
		var F2			= 113;
		var F3			= 114;
		var F4			= 115;
		var F5          = 116;
		var F6			= 117;
		var F7			= 118;
		var F8			= 119;
		var F9			= 120;
		var F10			= 121;
		var F11			= 122;
		var F12			= 123;
		
		var ctrl        = 17;
		var arrowLeft   = 37;
		var arrowRight  = 39;
    		var langFlag="";
    		try
    		{
			langFlag=document.getElementById('hidLangFlagDiv').value;
    		}
    		catch(err)
    		{
			langFlag="E";
    		}

		
		//alert("key pressed mitesh");
		if(document.layers)
		{
			if (evt.which == F5 || evt.which == F11 || evt.which == F1|| evt.which == F2 || evt.which == F3 || evt.which == F4 || evt.which == F6 || evt.which == F7 || evt.which == F8 || evt.which == F9 || evt.which == F10 || evt.which == F12  )
			{
				return false;
			}
			else if(evt.which = ctrl)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
			else if (evt.altKey == 1 && evt.which == arrowLeft )
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
			else if (evt.altKey == 1 && evt.which == arrowRight)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
		}
		else if(document.all)
		{
			if (event.keyCode == F5 || event.keyCode == F11 || event.keyCode == F1 || event.keyCode == F2 || event.keyCode == F3 || event.keyCode == F4 || event.keyCode == F6 || event.keyCode == F7 || event.keyCode == F8 || event.keyCode == F9 || event.keyCode == F10 || event.keyCode == F12)
			{
				event.keyCode = 0;
				window.event.returnValue = false;
			}
			else if (event.keyCode == ctrl)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
			else if (event.altKey == 1 && event.keyCode == arrowLeft )
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
			else if (event.altKey == 1 &&  event.keyCode == arrowRight)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("This Key Press is Not Allowed");
					} 
			}
		}
	}
	
	function click(e)
	{
    		var langFlag="";
    		try
    		{
			langFlag=document.getElementById('hidLangFlagDiv').value;
    		}
    		catch(err)
    		{
			langFlag="E";
    		}

		//alert("clicked on page  mitesh");
		if (document.all)
		{
			if (event.button == 2)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("Sorry, that function is disabled");
					} 
				return false;
			}
		}
		
		if (document.layers)
		{
			if (e.which == 3)
			{
					if(langFlag=="H")
					{
			     		alert("इस बटन का प्रयोग निषेध है, असुविधा के लिए खेद है");
					}
					else
					{  	
						alert("Sorry, that function is disabled");
					} 
				return false;
			}
		}
	}
	
	function hideMenus()
	{
		if(document.body.id != "mainknabsdocument")
		{
			if(window.parent.FoisMenu != null)
				window.parent.FoisMenu._closeOtherMenus(null);
		}
	}
function isSpclChar(entity)
	{
   		var pattern = /^[0-9@!#\$\^%&*()+=\-\[\]\\\';,\.\/\{\}\|\":<>\? ]+$/;

//alert(entity.srcElement.value.indexOf(iChars));
   		if (pattern.test(entity.srcElement.value)) 
		{
  	   		alert ("Special characters are not allowed.");
   	  		entity.srcElement.value="";
    	 		return false;
   		}
	}
	function chkValue(entity, type) //Added for Checking the input fields type validation
	{
		var unicode		= entity.keyCode ? entity.keyCode : entity.charCode;
    		var langFlag="";

    		try
    		{
			langFlag=document.getElementById('hidLangFlagDiv').value;
    		}
    		catch(err)
    		{
			langFlag="E";
    		}

		
		if(entity.srcElement.readOnly == false)
		{
			if(type=="S")  //only for Character field and Space
			{
			   if((unicode>=65 && unicode<=90) || (unicode>=97 && unicode<=122)|| (unicode==32) || (unicode==13))
			   {
				  entity.returnValue =  true;
			   }
			   else
			   {
					if(langFlag=="H")
					{
			     		alert("केवल अंग्रेजीअक्षर का ही प्रयोग करें");
					}
					else
					{  	
						alert("Only Character Value is Allowed Here.");
					} 
				  entity.returnValue = false; 
			   }
			}
			if(type=="N") //only for Numeric Fileds
			{    
			   if((unicode>=48 && unicode<=57) || (unicode==13))
			   {
				 entity.returnValue =  true;
			   }
			   else
			   { 
					if(langFlag=="H")
					{
			     		alert("केवल अंक प्रविष्ट करें");
					}
					else
					{  	
						alert("Only Numeric Value is Allowed Here.");
					} 
				 entity.returnValue = false; 
			   }
			}
			if(type=="B") //for Alphanumeric Fields
			{    
			   if((unicode>=48 && unicode<=57)|| (unicode>=65 && unicode<=90) || (unicode>=97 && unicode<=122) || (unicode==32) || (unicode==13) )
			   {
				 entity.returnValue =  true;
			   }
			   else
			   {
					if(langFlag=="H")
					{
			     		alert("विशिष्ट चिन्हों का प्रयोग निषेध है");
					}
					else
					{  	
						alert("No Any Special Character is Allowed Here.");
					} 
				  entity.returnValue = false; 
			   }
			}
            if(type=="C") //for DataBase Fields
			{ 
            //alert(unicode);    
			    if( (unicode==92) || (unicode==42)|| (unicode==64)|| (unicode==34)|| (unicode==35)|| (unicode==39) || (unicode==38) || (unicode==59) ||  (unicode==95))
			   {
					if(langFlag=="H")
					{
			     		alert("विशिष्ट चिन्हो, जैसे कि ( * @ & '  \"  _  ; \\  #), का प्रयोग निषेध है");
					}
					else
					{  	
						alert("Special Characters ( * @ & '  \"  _  ; \\  #) are not Allowed Here.");
					} 
				
				entity.returnValue = false;
			   }
			   else
			   {
				entity.returnValue =  true;
			   }

			}
			if(type=="D") //for Date Fields (DD-MM-YYYY)
			{    
			   if((unicode>=48 && unicode<=57) || (unicode==45) || (unicode==13))
			   {
				 entity.returnValue =  true;
			   }
			   else
			   {
					if(langFlag=="H")
					{
			     		alert("कृपया मान्य दिनांक फॉर्मेट (DD-MM-YYYY) ही भरें.");
					}
					else
					{  	
						alert("Enter Valid Date(DD-MM-YYYY).");
					} 
				  
				  entity.returnValue = false; 
			   }
			}
			if(type=="R") //only for Real Numbers		Added by Neha on 070111
			{
				if((unicode>=48 && unicode<=57) || (unicode==46))
				{
					entity.returnValue =  true;
					if(unicode==46)
						if(entity.srcElement.value.indexOf(".") == -1)
							entity.returnValue =  true;
						else 
							entity.returnValue = false;
				}
				else
				{
					
					if(langFlag=="H")
					{
			     		alert("केवल अंक प्रविष्ट करें");
					}
					else
					{  	
						alert("Only Numeric Value is Allowed Here.");
					} 
					entity.returnValue = false; 
				}
			}
		}
	}
	
	function chkViaValue(entity,strVia)
	{
		var unicode		= entity.keyCode ? entity.keyCode : entity.charCode;
		var strSubVia = strVia.substring((strVia.length-4),strVia.length);
    		var langFlag="";
    		try
    		{
			langFlag=document.getElementById('hidLangFlagDiv').value;
    		}
    		catch(err)
    		{
			langFlag="E";
    		}


		
		if(!((unicode>=65 && unicode<=90) || (unicode>=97 && unicode<=122)|| (unicode==13)|| (unicode==45)))	//For character & "-" input
		{					
					if(langFlag=="H")
					{
			     		alert("????? ?? ????? ???? ?? ????");
					}
					else
					{  	
						alert("Enter valid Via.");
					} 
			
			entity.returnValue = false; 
		}
		else if(strVia.length ==0 && (unicode==45))	//For "-" not as first character
		{			
					if(langFlag=="H")
					{
			     		alert("कृपया एक मान्य वाया ही भरें");
					}
					else
					{  	
						alert("Enter valid Via.");
					} 
			entity.returnValue = false; 
		}
		else if(((strVia.substring((strVia.length-1),strVia.length))=="-") && (unicode==45))	//For consecutive "--"
		{			
					if(langFlag=="H")
					{
			     		alert("कृपया एक मान्य वाया ही भरें");
					}
					else
					{  	
						alert("Enter valid Via.");
					} 
			entity.returnValue = false; 
		}
		else if((strSubVia.indexOf("-") == -1) && (unicode !=45 ) && (strVia.length > 3))
		{			
					if(langFlag=="H")
					{
			     		alert("कृपया एक मान्य वाया ही भरें");
					}
					else
					{  	
						alert("Enter valid Via.");
					} 
			entity.returnValue = false;
		}
		else
		{
			entity.returnValue = true; 
		}
	}
	
	function chkDateValue(entity, strDate)
	{
		var unicode		= entity.keyCode ? entity.keyCode : entity.charCode;
    		var langFlag="";
    		try
    		{
			langFlag=document.getElementById('hidLangFlagDiv').value;
    		}
    		catch(err)
    		{
			langFlag="E";
    		}


		
		if(!((unicode>=48 && unicode<=57) || (unicode==45) || (unicode==13)))
		{
			entity.returnValue =  false;	
			if(langFlag=="H")
			{
			 		alert("कृपया मान्य दिनांक फॉर्मेट (DD-MM-YYYY) ही भरें.");
			}
			else
			{  	
				alert("Enter Valid Date(DD-MM-YYYY).");
			} 
		}
		else
			entity.returnValue =  true;
	}
	
	function IsValidDate(day, month, year)
	{
		var date = new Date(month + "-" + day + "-" + year);
		return date.getDate() == day && date.getMonth() == month - 1 && date.getFullYear() == year;
	}
	
	if(document.layers)
	{
	document.captureEvents(Event.MOUSEDOWN);
	document.captureEvents (Event.KEYDOWN);
	}
	
	document.onkeydown=keyPressed;
	document.onmousedown=click;
	document.onclick=hideMenus;