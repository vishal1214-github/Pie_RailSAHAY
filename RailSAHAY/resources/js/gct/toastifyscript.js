var bgColors = [
    "linear-gradient(to right, #00b09b, #96c93d)",
    "linear-gradient(to right, #ff5f6d, #ffc371)",
  ],
  i = 0;
  
var saveDraftNotification = setInterval(function() {
Toastify({
  text: "It's always a good idea to Save your Application as Draft !",
  duration: 4500,
  newWindow: true,
  gravity: "top",
  position: 'right',
  style: {
      background: "linear-gradient(to right, #ff5f6d, #ffc371)",
    }
}).showToast();
}, 45000);
/*
setTimeout(function() {
  Toastify({
    text: "Simple JavaScript Toasts",
    gravity: "top",
    position: 'center',
    style: {
      background: '#0f3443'
    }
  }).showToast();
}, 1000);
*/
// Options for the toast
/*
var options = {
  text: "It's always a good idea to Save your Application as Draft !",
  duration: 2500,
  callback: function() {
    Toastify.reposition();
  },
  close: true,
  style: {
    background: "linear-gradient(to right, #00b09b, #96c93d)",
  }
};
*/
// Initializing the toast
var myToast = Toastify(options);

// Toast after delay
/*
setTimeout(function() {
  myToast.showToast();
}, 4500);
*/
/*
setTimeout(function() {
  Toastify({
    text: "Highly customizable",
    gravity: "bottom",
    position: 'left',
    close: true,
    style: {
      background: "linear-gradient(to right, #ff5f6d, #ffc371)",
    }
  }).showToast();
}, 3000);
*/
// Displaying toast on manual action `Try`
/*
document.getElementById("new-toast").addEventListener("click", function() {
  Toastify({
    text: "I am a toast",
    duration: 3000,
    close: i % 3 ? true : false,
    style: {
      background: bgColors[i % 2],
    }
  }).showToast();
  i++;
});
*/