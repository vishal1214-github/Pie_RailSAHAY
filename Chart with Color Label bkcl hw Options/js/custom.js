var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, { 
  type: "bar", 
  data: {
    labels: ["Python", "JavaScript", "PHP", "Java", "C#", "C++"],
    datasets: [
       
      {
        data:[14,13,10,13,12,13],
        label:"Enginner",
        backgroundColor:["yellow","red","pink","green","blue","violet"],
        borderColor:["black"],
        borderWidth:2,
      }
    ], 
  },
  Options:{
    responsive:false,
  },
});

