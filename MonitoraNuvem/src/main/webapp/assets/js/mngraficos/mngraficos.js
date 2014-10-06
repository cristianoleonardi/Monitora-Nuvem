$(document).ready(function () {

    //define chart clolors ( you maybe add more colors if you want or flot will add it automatic )
    var chartColours = ['#62aeef', '#d8605f', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'];

    //generate random number for charts
    randNum = function () {
        return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
    }

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
    if ($(".instance-by-status").length) {
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

            $.plot($(".instance-by-status"), data, options);
        });

    }//End of Total de Instâncias por Provedor por Status#######################

    //check if element exist and draw chart
    if ($(".history-instances-by-status").length) {
        $(function () {
            //var d1 = [];
            //var d2 = [];

            //here we generate data for chart
            //for (var i = 0; i < 32; i++) {
            //    d1.push([new Date(Date.today().add(i).days()).getTime(), randNum() + i + i]);
            //    d2.push([new Date(Date.today().add(i).days()).getTime(), randNum()]);
            //}

            var chartMinDate = "2014-09-15 18:20:02";//d1[0][0]; //first day
            var chartMaxDate = "2014-09-24 19:10:37";//d1[31][0];//last day
            var tickSize = [1, "day"];
            var tformat = "%d/%m/%y";

            //graph options
            var options = {
                grid: {
                    show: true,
                    aboveData: true,
                    color: "#3f3f3f",
                    labelMargin: 5,
                    axisMargin: 0,
                    borderWidth: 0,
                    borderColor: null,
                    minBorderMargin: 5,
                    clickable: true,
                    hoverable: true,
                    autoHighlight: true,
                    mouseActiveRadius: 100
                },
                series: {
                    lines: {
                        show: true,
                        fill: true,
                        lineWidth: 2,
                        steps: false
                    },
                    points: {
                        show: true,
                        radius: 2.8,
                        symbol: "circle",
                        lineWidth: 2.5
                    }
                },
                legend: {
                    position: "ne",//Suporta: ne, nw, se, sw
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
                shadowSize: 0,
                tooltip: true, //activate tooltip
                tooltipOpts: {
                    content: "%s: %y.0",
                    xDateFormat: "%d/%m",
                    shifts: {
                        x: -30,
                        y: -50
                    },
                    defaultTheme: false
                },
                yaxis: {min: 0},
                xaxis: {
                    mode: "time",
                    minTickSize: tickSize,
                    timeformat: tformat,
                    min: chartMinDate,
                    max: chartMaxDate
                }
            };
            
            //Busca dados para o gráfico da página dashboard.jsp
            var dados = document.getElementById("dadosgrafico4").value;

            //Transforma a string em array
            var arrDados = [];
            arrDados = dados.split(";");

            //Converte os dados do array arrDados em Objetos e insere no array data
            var data = [];
            for (i = 0; i < arrDados.length; i++) {
                eval('var temp= ' + arrDados[i]);
                data.push(temp);
            }
            
            var plot = $.plot($(".history-instances-by-status"), data, options);
//                    [{
//                            label: "Visitors",
//                            data: d1,
//                            lines: {fillColor: "#f3faff"},
//                            points: {fillColor: "#fff"}
//                        },
//                        {
//                            label: "Unique Visits",
//                            data: d2,
//                            lines: {fillColor: "#fff8f7"},
//                            points: {fillColor: "#fff"}
//                        }], options);
        });
    }//End .chart if  

});