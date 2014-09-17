<header id="header">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <a class="navbar-brand" href="/MonitoraNuvem"><img src="<% out.print(request.getContextPath()); %>/assets/images/logo_monitora_nuvem.png" alt="Monitora Nuvem" class="img-responsive"></a>
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
                        <span class="notification red">3</span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li role="presentation"><a href="#" class=""><i class="icon16 i-info"></i> Alerta de informação.</a></li>
                        <li role="presentation"><a href="#" class=""><i class="icon16 i-notification"></i> Alerta de Notificação.</a></li>
                        <li role="presentation"><a href="#" class=""><i class="icon16 i-cancel-circle"></i> Alerta de Erro.</a></li>
                    </ul>
                </li>
                <li class="divider-vertical"></li>
            </ul>
        </div><!--/.nav-collapse -->
    </nav>
</header> <!-- End #header  -->