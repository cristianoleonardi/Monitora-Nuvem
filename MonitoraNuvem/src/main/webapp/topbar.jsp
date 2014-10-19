<%@page import="java.util.ArrayList"%>
<%@page import="br.com.monitoranuvem.model.SendAlerts"%>
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
                    <%
                        ArrayList<SendAlerts> listaSendAlerts = new ArrayList<>();
                        if (session.getAttribute("listaSendAlerts") != null) {
                            listaSendAlerts = (ArrayList<SendAlerts>) session.getAttribute("listaSendAlerts");
                            session.removeAttribute("listaSendAlerts");
                        }
                    %>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon24 i-bell-2"></i>
                        <span class="notification red"><% if (listaSendAlerts.size() > 0) {
                                out.print(listaSendAlerts.size());
                            } %></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <%
                            if (listaSendAlerts.size() > 0) {
                                for (SendAlerts sendAlerts : listaSendAlerts) { %>
                        <li role="presentation">
                            <a href="#" class="">
                                <i class="icon16 i-info"></i><% out.print(sendAlerts.getAlerts().getNameAlerts() + " Status: " + sendAlerts.getAlerts().getStatusProvider() + ". Provedor: " + sendAlerts.getAlerts().getProv().getNome()); %>
                            </a>
                        </li>
                        <% }
                            }%>
                    </ul>
                </li>
                <li class="divider-vertical"></li>
            </ul>
        </div><!--/.nav-collapse -->
    </nav>
</header> <!-- End #header  -->