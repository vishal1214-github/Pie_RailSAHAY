
function showUserPosition() {
// If geolocation is available, try to get the visitor's position
	if(navigator.geolocation)
	{
		console.log("Geolocation object available");
		navigator.geolocation.getCurrentPosition(successCallback1, errorCallback1);
	}
	else
	{
		alert("Sorry, your browser does not support capturing your location.");
	}
};

// Define callback function for successful attempt
function successCallback1(position)
{
	localStorage.setItem("userLat", position.coords.latitude);
	localStorage.setItem("userLng", position.coords.longitude);
}
function errorCallback1()
{
	//alert("Sorry, your location could not be identified for nearby freight terminals");
}
