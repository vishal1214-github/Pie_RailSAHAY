var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, { 
  type: "bar", 
  data: {
    labels: ["Python", "JavaScript", "PHP", "Java", "C#", "C++"],
    datasets: [
      {
        
        data: [13, 15, 8, 10, 9, 10], 
        label:"Software",
        backgroundColor :["red"],
      }, 
      {
        data:[14,13,10,13,12,13],
        label:"Enginner",
        backgroundColor:["yellow"],
      }
    ],
  },
  options:{
	  responsive:false,
	  },
});

