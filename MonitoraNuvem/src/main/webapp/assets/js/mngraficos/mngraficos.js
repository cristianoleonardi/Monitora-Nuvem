$(document).ready(function () {

    //define chart clolors ( you maybe add more colors if you want or flot will add it automatic )
    var chartColours = ['#62aeef', '#d8605f', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'];

    //check if element exist and draw chat pie
	if($(".active-instance-by-provider").length) {
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
				legend:{
					show:true,
					labelFormatter: function(label, series) {
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
					content: "%s : %y.1"+"%",
					shifts: {
						x: -30,
						y: -50
					},
					defaultTheme: false
				}
			};
			var data = session["listaStatusProvider"];
                                [
			    { label: "USA",  data: 38, color: chartColours[0]},
			    { label: "Brazil",  data: 23, color: chartColours[1]},
			    { label: "India",  data: 15, color: chartColours[2]},
			    { label: "Turkey",  data: 9, color: chartColours[3]},
			    { label: "France",  data: 7, color: chartColours[4]},
			    { label: "China",  data: 5, color: chartColours[5]},
			    { label: "Germany",  data: 3, color: chartColours[6]}
			];

		    $.plot($(".active-instance-by-provider"), data, options);

		});

	}//End of .cart-pie
        
        });