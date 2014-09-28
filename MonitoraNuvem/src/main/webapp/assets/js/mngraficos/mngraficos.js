$(document).ready(function () {

    //check if element exist and draw chat pie
    if ($(".active-instance-by-provider").length) {
        $(function () {
            var options = {
                series: {
                    pie: {
                        show: true,
                        highlight: {
                            opacity: 0.1
                        },
                        radius: 1,
                        stroke: {
                            width: 2
                        },
                        startAngle: 2,
                        border: 30 //darken the main color with 30
                    }
                },
                legend: {
                    show: true,
                    labelFormatter: function (label, series) {
                        // series is the series object for the label
                        return '<a href="#' + label + '">' + label + '</a>';
                    },
                    margin: 50,
                    width: 20,
                    padding: 1
                },
                grid: {
                    hoverable: true,
                    clickable: true
                },
                tooltip: true, //activate tooltip
                tooltipOpts: {
                    content: "%s : %y.1" + "%",
                    shifts: {
                        x: -30,
                        y: -50
                    },
                    defaultTheme: false
                }
            };
            var dados = document.getElementById("dadosgrafico").value;

            var arrDados = [];
            arrDados = dados.split(";");

            var data = [];
            for (i = 0; i < arrDados.length; i++) {
                eval('var temp= ' + arrDados[i]);
                data.push(temp);
            }

            $.plot($(".active-instance-by-provider"), data, options);

        });

    }//End of .cart-pie

});