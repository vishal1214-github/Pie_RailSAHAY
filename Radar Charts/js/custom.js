var ctx = document.getElementById("myChart").getContext("2d");

var data = {
    labels: ["Chocolate", "Vanilla", "Strawberry"],
    datasets: [
        {
            label: "Blue",
            backgroundColor: "blue",
            data: [3,7,4]
        }, 
        {
            label: "Red",
            backgroundColor: "red",
            data: [4,3,5]
        },
        {
            label: "Green",
            backgroundColor: "green",
            data: [7,2,6]
        }
    ]
};

var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: {
        barValueSpacing: 20,
        scales: {
            yAxes: [{
                ticks: {
                    min: 0,
                }
            }]
        }
    }
});