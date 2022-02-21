var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, { 
  type: "bar", 
  data: {
    labels: ["Python", "JavaScript", "PHP", "Java", "C#", "C++"],
    datasets: [
      {
        label: "Programming Languages",
        data: [13, 15, 8, 10, 9, 10],
        label:"Programming Language",
        backgroundColor:["red","blue","Pink","yellow","green","orange"],
      }, 
    ],
  },
});

 