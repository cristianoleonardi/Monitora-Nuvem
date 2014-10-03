$(document).ready(function () {

    //define chart clolors ( you maybe add more colors if you want or flot will add it automatic )
    var chartColours = ['#62aeef', '#d8605f', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'];

    //Percentual de Instâncias Ativas por Provedor##############################
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
            //Busca dados para o gráfico da página dashboard.jsp
            var dados = document.getElementById("dadosgrafico1").value;

            //Transforma a string em array
            var arrDados = [];
            arrDados = dados.split(";");

            //Converte os dados do array arrDados em Objetos e insere no array data
            var data = [];
            for (i = 0; i < arrDados.length; i++) {
                eval('var temp= ' + arrDados[i]);
                data.push(temp);
            }

            $.plot($(".active-instance-by-provider"), data, options);

        });

    }//End of Percentual de Instâncias Ativas por Provedor######################

    //Total de Instâncias por Provedor por Status###############################
    if ($(".chart-bars-stacked").length) {
        $(function () {
            //Busca dados para o gráfico da página dashboard.jsp
            var dados = document.getElementById("dadosgrafico2").value;
            var labels = document.getElementById("dadosgrafico3").value;

            //Transforma a string em array
            var arrDados = [];
            arrDados = dados.split(";");
            var arrLabels = [];
            eval('var temp= ' + labels);
            arrLabels = temp;

            //Converte os dados do array arrDados em Objetos e insere no array data
            var data = [];
            for (i = 0; i < arrDados.length - 1; i++) {
                eval('var temp= ' + arrDados[i]);

                data.push(temp);
            }

            var stack = 0, bars = true, lines = false, steps = false;

            var options = {
                grid: {
                    show: true,
                    aboveData: false,
                    color: "#3f3f3f",
                    labelMargin: 5,
                    axisMargin: 0,
                    borderWidth: 0,
                    borderColor: null,
                    minBorderMargin: 5,
                    clickable: true,
                    hoverable: true,
                    autoHighlight: true,
                    mouseActiveRadius: 20
                },
                series: {
                    stack: stack,
                    lines: {show: lines, fill: true, steps: steps},
                    bars: {show: bars, barWidth: 0.5, fill: 1}
                },
                xaxis: {ticks: arrLabels, tickDecimals: 0},
                legend: {
                    position: "ne",
                    margin: [0, -25],
                    noColumns: 0,
                    labelBoxBorderColor: null,
                    labelFormatter: function (label, series) {
                        // just add some space to labes
                        return label + '&nbsp;&nbsp;';
                    },
                    width: 40,
                    height: 1
                },
                colors: chartColours,
                shadowSize: 1,
                tooltip: true, //activate tooltip
                tooltipOpts: {
                    content: "%s : %y.0",
                    shifts: {
                        x: -30,
                        y: -50
                    }
                }
            };

            $.plot($(".chart-bars-stacked"), data, options);
        });

    }//End of Total de Instâncias por Provedor por Status#######################

});