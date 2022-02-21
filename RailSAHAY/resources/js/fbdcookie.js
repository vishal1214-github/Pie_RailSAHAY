// --- Config --- //
/*var fbdcookieTitle = "Permission to store Cookies"; // Title
var fbdcookieDesc = "Payment of Charges through this Portal requires cookies to be stored on your local machine."; // Description
var fbdcookieLink = '<a href="/RailSAHAY/pages/Privacy.jsp" target="_self">Cookie Policy</a>'; // Cookiepolicy link
var fbdcookieButton = "I Agree"; // Button text
var fbdcookieButtonDeny = "I Disagree";*/

var fbdcookieTitle = ""; // Title
var fbdcookieDesc = "We use cookies to improve your experience on our website. By browsing this website, you agree to our use of cookies."; // Description
var fbdcookieLink = '<a href="/RailSAHAY/pages/Privacy.jsp" target="_self">MORE INFO</a>'; // Cookiepolicy link
var fbdcookieButton = "Accept"; // Button text
// ---        --- //


function fbdFadeIn(elem, display){
  var el = document.getElementById(elem);
  el.style.opacity = 0;
  el.style.display = display || "block";

  (function fade() {
    var val = parseFloat(el.style.opacity);
    if (!((val += .02) > 1)) {
      el.style.opacity = val;
      requestAnimationFrame(fade);
    }
  })();
};
function fbdFadeOut(elem){
  var el = document.getElementById(elem);
  el.style.opacity = 1;

  (function fade() {
    if ((el.style.opacity -= .02) < 0) {
      el.style.display = "none";
    } else {
      requestAnimationFrame(fade);
    }
  })();
};

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
   
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
   
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
   
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
}
function fbdcookieDisagree()
{
	 $(".btn-pay").attr("disabled","disabled");
	fbdFadeOut("cookieConsentContainer");
}

function cookieConsent() {
  if (getCookie('fbdcookie')==null || getCookie('fbdcookie')=="") {
 	var cookieDiv = document.createElement('div');
	cookieDiv.id = 'cookieConsentContainer';
	cookieDiv.classList.add('cookieConsentContainer');
	/*cookieDiv.innerHTML += '<div class="cookieTitle"><a>' + fbdcookieTitle + '</a></div><div class="cookieDesc"><p>' + fbdcookieDesc + ' ' + fbdcookieLink + '</p></div>'
	cookieDiv.innerHTML +='<div class="cookieButton"><a style="margin:5px;" class="accept" onClick="fbdcookieDismiss();">' + fbdcookieButton + '</a><a  class="deny" onClick="fbdcookieDisagree();">' + fbdcookieButtonDeny + '</a></div>';*/
	  cookieDiv.innerHTML += '<div class="cookieDesc"><p style="font-family: OpenSans, arial, sans-serif;color:#3A3B3C;font-size: 14px;">'+ fbdcookieDesc +'</p></div>';
	  cookieDiv.innerHTML += '<div class="cookieButton" > <a onClick="fbdcookieDismiss();">' + fbdcookieButton + '</a></div>';
	 cookieDiv.innerHTML += '<div class="cookieDesc" style="float:right;"><p>'+ fbdcookieLink +'</p></div>';
		
	document.body.appendChild(cookieDiv); 
 	fbdFadeIn("cookieConsentContainer");
  }
}

function fbdcookieDismiss() {
  setCookie('fbdcookie','Y',7);
  fbdFadeOut("cookieConsentContainer");
}

window.onload = function() { cookieConsent(); };
$(".btn-pay").attr("disabled","disabled");
