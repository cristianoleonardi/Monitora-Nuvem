<%-- 
    Document   : index
    Created on : 14/09/2014, 19:46:30
    Author     : Cristiano Leonardi, Márcio Bolzan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <title>Monitora Nuvem</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="application-name" content="Monitora Nuvem" />

        <!-- Headings -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700' rel='stylesheet' type='text/css'>
        <!-- Text -->
        <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css' />

        <!--[if lt IE 9]>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:700" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:800" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Droid+Sans:400" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Droid+Sans:700" rel="stylesheet" type="text/css" />
        <![endif]-->

        <!-- Core stylesheets do not remove -->
        <link href="<% out.print(request.getContextPath()); %>/assets/css/bootstrap/bootstrap.css" rel="stylesheet" />
        <link href="<% out.print(request.getContextPath()); %>/assets/css/bootstrap/bootstrap-theme.css" rel="stylesheet" />
        <link href="<% out.print(request.getContextPath()); %>/assets/css/icons.css" rel="stylesheet" />

        <!-- Plugins stylesheets -->
        <link href="<% out.print(request.getContextPath()); %>/assets/js/plugins/forms/uniform/uniform.default.css" rel="stylesheet" />

        <!-- app stylesheets -->
        <link href="<% out.print(request.getContextPath()); %>/assets/css/app.css" rel="stylesheet" />

        <!-- Custom stylesheets ( Put your own changes here ) -->
        <link href="<% out.print(request.getContextPath()); %>/assets/css/custom.css" rel="stylesheet" />

        <!--[if IE 8]><link href="<% out.print(request.getContextPath()); %>/assets/css/ie8.css" rel="stylesheet" type="text/css" /><![endif]-->

        <!-- Force IE9 to render in normal mode -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="<% out.print(request.getContextPath()); %>/assets/js/html5shiv.js"></script>
          <script src="<% out.print(request.getContextPath()); %>/assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<% out.print(request.getContextPath()); %>/assets/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<% out.print(request.getContextPath()); %>/assets/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<% out.print(request.getContextPath()); %>/assets/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<% out.print(request.getContextPath()); %>/assets/images/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="<% out.print(request.getContextPath()); %>/assets/images/ico/favicon.png">

        <!-- Le javascript
        ================================================== -->
        <!-- Important plugins put in all pages -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/bootstrap/bootstrap.js"></script>  
        <script src="<% out.print(request.getContextPath()); %>/assets/js/conditionizr.min.js"></script>  
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/core/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/core/jrespond/jRespond.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/jquery.genyxAdmin.js"></script>

        <!-- Charts plugins -->
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.pie.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.resize.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.tooltip.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.orderBars.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/jquery.flot.time.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/peity/jquery.peity.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/charts/flot/date.js"></script>
        
        <!-- Form plugins -->
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/forms/uniform/jquery.uniform.min.js"></script>
        <script src="<% out.print(request.getContextPath()); %>/assets/js/plugins/forms/validation/jquery.validate.js"></script>

        <!-- Init plugins -->
        <script src="<% out.print(request.getContextPath()); %>/assets/js/app.js"></script><!-- Core js functions -->
        <script src="<% out.print(request.getContextPath());%>/assets/js/pages/login.js"></script><!-- Init plugins only for page -->
        <script src="<% out.print(request.getContextPath());%>/assets/js/mngraficos/mngraficos.js"></script><!-- Init plugins only for page -->

    </head>
    <body>