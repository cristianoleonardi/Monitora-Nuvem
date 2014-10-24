<%@page import="java.util.ArrayList"%>
<%@page import="br.com.monitoranuvem.model.SendAlerts"%>
<header id="header">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <a class="navbar-brand" href="/monitoranuvem"><img src="<% out.print(request.getContextPath()); %>/assets/images/logo_monitora_nuvem.png" alt="Monitora Nuvem" class="img-responsive"></a>
        <button type="button" class="navbar-toggle btn-danger" data-toggle="collapse" data-target="#navbar-to-collapse">
            <span class="sr-only">Toggle right menu</span>
            <i class="icon16 i-arrow-8"></i>
        </button>          
        <div class="collapse navbar-collapse" id="navbar-to-collapse">
            <ul class="nav navbar-nav pull-right">
                <li class="divider-vertical"></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon24 i-bell-2"></i>
                        <span id="notification-area"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu" id="list-alerts" ></ul>
                </li>
                <li class="divider-vertical"></li>
            </ul>
        </div><!--/.nav-collapse -->
    </nav>
</header> <!-- End #header  -->