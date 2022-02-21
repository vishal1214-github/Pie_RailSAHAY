var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, { 
  type: "bar", //Scatter/Line/Bar/Radar/Pie/Doughnut/Polar Area/  Bubble
  data: {
    labels: ["Python", "JavaScript", "PHP", "Java", "C#", "C++"],
    datasets: [ 
      {
        data:[14,13,10,13,12,13], 
        backgroundColor:["red","blue","pink","green","black","yellow"],
        label:"Fois"
      }
    ],
   
  },
  options:{
    responsive:false,
    layout:{
      padding:{
        left:50,
        top:50,
        right:0,
        buttom:0,
      },
    },
    tooltips:{
      enabled:true,
      backgroundColor:"red",
      titleFontFamily:"Arial",
      titleFontSize:30,
      titleFontStyle:"bold Italic",
      titleFontColor:"black",
      titleAlign:"center",
      titleSpacing:3,//
      titleMarginBottom:50,
      bodyFontFamily:"Monotype Corsiva",
      bodyFontSize:20,
      bodyFontStyle:"bold italic",
      bodyFontColor:"pink",
      bodyAlign:"center",
      bodySpacing:5,
    },
    title:
    {
      display:true,
      text:"Vishal Sonkar",
      fontSize:30,
      position:"bottom",
      fontFamily:"jokerman",
      fontColor:"blue",
      fontStyle:"italic bold",
      padding:20,
      lineHeight:2,
    },
    legend:{
      display:true,
      position:"bottom",
      align:"left",
      labels:{
        fontSize:20,
        fontColor:"violet",
        boxWidth:100,
      },
    },
    animation:{
      duration:5000,
      easing:"easeInOutBounce",
    },
   // events:["Cilck"],//    Click/MouseMoved/MouseOut/TouchStrat/TouchMoved
    // onclick:function()
    // {
    //  console.log("On Click"); 
    // },
    // onHover:function()
    // {
    //  console.log("On Hover"); 
    // },
  },
});

