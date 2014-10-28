/* 
 * Tornar graficos independentes.
 */

//define chart clolors ( you maybe add more colors if you want or flot will add it automatic )
var chartColours = ['#62aeef', '#d8605f', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'];

function plotActiveInstanceByProvider(dataForGraph) {
    var options = {
        series: {
            pie: {
                show: true,
                highlight: {
                    opacity: 0.1
                },
                radius: 9 / 10,
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
                return '<a href="dashboarddetailview?provider=' + label + '\">' + label + '</a>';
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
    //Dados para montar o gráfico retornado pelo Ajax
    var dados = dataForGraph;

    //Transforma a string em array
    var arrDados = [];
    arrDados = dados.split(";");

    //Converte os dados do array arrDados em Objetos e insere no array data
    var data = [];
    for (var i = 0; i < arrDados.length; i++) {
        eval('var temp= ' + arrDados[i]);
        data.push(temp);
    }

    $.plot($(".active-instance-by-provider"), data, options);
}

function plotInstanceByStatus(dataForGraph, dataLabels) {
    //Dados para montar o gráfico retornado pelo Ajax
    var dados = dataForGraph;
    var labels = dataLabels;

    //Monta array de dados
    var data = [];
    eval('var temp= ' + dados);
    data = temp;

    //Monta array de labels
    var arrLabels = [];
    eval('var temp= ' + labels);
    arrLabels = temp;

    var options = {
        bars: {
            show: true,
            barWidth: 0.2,
            fill: 1
        },
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
            autoHighlight: false,
            mouseActiveRadius: 20
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
}

function plotHistoryInstancesByStatus(firstDay, lastDay, history) {
    //Dados para montar o gráfico retornado pelo Ajax
    var chartMinDate = firstDay;
    var chartMaxDate = lastDay;
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
            position: "ne", //Suporta: ne, nw, se, sw
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

    //Dados para montar o gráfico retornado pelo Ajax
    var dados = history;

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
}