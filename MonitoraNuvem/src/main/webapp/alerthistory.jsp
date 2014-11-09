<%-- Imports --%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.monitoranuvem.model.SendAlerts"%>
<%@page import="java.util.ArrayList"%>
<%-- Inclus�o do cabe�alho da p�gina --%>
<jsp:include page="header.jsp" />

<%-- Inclus�o da barra superior da p�gina --%>
<jsp:include page="topbar.jsp" />

<%-- �rea principal da p�gina --%>
<div class="main">

    <%-- Inclus�o do menu lateral --%>
    <jsp:include page="sidebar.jsp" />

    <%-- Sess�o do conte�do da p�gina --%>
    <section id="content">
        <div class="wrapper">
            <div class="crumb">
                <ul class="breadcrumb">
                    <li><a href="/monitoranuvem"><i class="icon16 i-bell-2"></i>Home</a></li>
                    <li class="active">Hist�rico de Alertas</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-bell-2"></i> Hist�rico de Alertas</h1>
                </div>

                <div class="row">

                    <div class="col-lg-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-coin"></i></div> 
                                <h4>Custo Atual</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>Alerta</th>
                                            <th>Data - Hora</th>
                                            <th>Notificado E-mail</th>
                                            <th>Status</th>
                                            <th>Dura��o</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<SendAlerts> listaSendAlerts = (ArrayList<SendAlerts>) session.getAttribute("listaSendAlerts");
                                            session.removeAttribute("listaSendAlerts");
                                        %>
                                        <% for (SendAlerts alerta : listaSendAlerts) { %>
                                        <tr class="gradeA">
                                            <td><% out.print(alerta.getAlerts().getNameAlerts()); %></td>
                                            <td class="center">
                                                <%
                                                    SimpleDateFormat dt;
                                                    dt = new SimpleDateFormat("dd/MM/yyyy ' - ' HH:mm");
                                                    out.print(dt.format(alerta.getDateSendAlerts()));
                                                %>
                                            </td>
                                            <td class="center">
                                                <%
                                                    if (alerta.getSend() == 0) {
                                                        out.print("<span class=\"label label-important\">N�o</span>");
                                                    } else {
                                                        out.print("<span class=\"label label-success\">Sim</span>");
                                                    }
                                                %>
                                            </td>
                                            <td class="center">
                                                <%
                                                    if (alerta.getStatus() == 0) {
                                                        out.print("<span class=\"label label-important\">Pendente</span>");
                                                    } else {
                                                        out.print("<span class=\"label label-success\">Resolvido</span>");
                                                    }
                                                %>
                                            </td>
                                            <td class="center"><% out.print(""); %></td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Alerta</th>
                                            <th>Data - Hora</th>
                                            <th>Notificado E-mail</th>
                                            <th>Status</th>
                                            <th>Dura��o</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>

                    </div>                     

                </div>

            </div>
        </div>
    </section>
</div>

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />
