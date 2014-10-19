<%-- Imports --%>

<%@page import="br.com.monitoranuvem.model.InstanceProvider"%>
<%@page import="br.com.monitoranuvem.model.Provider"%>
<%@page import="br.com.monitoranuvem.model.QtdStatusProvider"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%-- Inclusão do cabeçalho da página --%>
<jsp:include page="header.jsp" />

<%-- Inclusão da barra superior da página --%>
<jsp:include page="topbar.jsp" />

<%-- Área principal da página --%>
<div class="main">

    <%-- Inclusão do menu lateral --%>
    <jsp:include page="sidebar.jsp" />

    <%-- Sessão do conteúdo da página --%>
    <%
        //Captura dados
        Provider p = (Provider) session.getAttribute("provider");
    %>
    <section id="content">
        <div class="wrapper">
            <div class="crumb">
                <ul class="breadcrumb">
                    <li><a href="/MonitoraNuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active"></li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-cloud"></i> Instâncias do Provedor: <% out.print(p.getNome()); %></h1>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h4><i class="icon10 i-screen-4"></i> Compute Service - Lista de Instâncias por Status</h4>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th class="center" bgcolor="#CAFF70">Running</th>
                                    <th class="center" bgcolor="#E9967A">Error</th>
                                    <th class="center" bgcolor="#FFFACD">Pending</th>
                                    <th class="center" bgcolor="#EEE5DE">Suspended</th>
                                    <th class="center" bgcolor="#EEE9BF">Terminated</th>
                                    <th class="center" bgcolor="#FFFFF0">Unrecognized</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <%
                                        Map<String, Integer> mapQtdStatusProvider = (Map<String, Integer>) session.getAttribute("mapQtdStatusProvider");
                                        String status = "";
                                    %>


                                    <td class="center" bgcolor="#CAFF70">

                                        <%
                                            status = "RUNNING";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#E9967A">
                                        <%
                                            status = "ERROR";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#FFFACD">
                                        <%
                                            status = "PENDING";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#EEE5DE">
                                        <%
                                            status = "SUSPENDED";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#EEE9BF">
                                        <%
                                            status = "TERMINATED";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#FFFFF0">
                                        <%
                                            status = "UNRECOGNIZED";
                                            if (mapQtdStatusProvider.containsKey(status)) {
                                                out.print("<a href=\"dashboarddetailview?action=listarInstanceByStatus&provider=" + p.getNome() + "&status=" + status + "\" title=\"Visualizar\">" + mapQtdStatusProvider.get(status) + " <i class=\"icon10 i-eye\"></i></a>");
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header" id="cabecalho">

                        </div>
                    </div>
                </div>

                <div class="row" id="instancias">
                    <%
                        if (session.getAttribute("listaInstanceProvider") != null) {
                            ArrayList<InstanceProvider> listaInstanceProvider = (ArrayList<InstanceProvider>) session.getAttribute("listaInstanceProvider");
                            session.removeAttribute("listaInstanceProvider");
                    %>

                    <script>
                        document.getElementById("cabecalho").innerHTML = "<h4><i class=\"icon10 i-screen-4\"></i> Detalhamento das instâncias do provedor:" + <% out.print(p.getNome());%> + "</h4>";
                    </script>

                    <% for (InstanceProvider ip : listaInstanceProvider) { %>
                    <div class="col-lg-4">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>ID:</th>
                                    <td class="center"><% out.print(ip.getIdInstance()); %></td>
                                </tr>
                                <tr>
                                    <th>Nome:</th>
                                    <td class="center"><% out.print(ip.getInstanceProvider()); %></td>
                                </tr>
                                <tr>
                                    <th>Status:</th>
                                    <td class="center"><% out.print(ip.getStatus()); %></td>
                                </tr>
                                <tr>
                                    <th>Alertas:</th>
                                    <td class="center"></td>
                                </tr>
                                <tr>
                                    <th>Detalhes:</th>
                                    <td class="center">
                                        <div class="btn-group">
                                            <a href="#modalStorage" class="btn btn-xs gap-right20" data-toggle="modal" title="Storage"><i class="icon20 i-storage"></i></a>
                                            <a href="#modalHardware" class="btn btn-xs gap-right20" data-toggle="modal" title="Hardware"><i class="icon20 i-cogs"></i></a>
                                            <a href="#modalDesempenho" class="btn btn-xs gap-right20" data-toggle="modal" title="Desempenho"><i class="icon20 i-health"></i></a>
                                            <a href="#modalAlertas" class="btn btn-xs gap-right20" data-toggle="modal" title="Alertas"><i class="icon20 i-bell-2"></i></a>
                                        </div>
                                    </td>
                                </tr>

                            </tbody>
                        </table>

                        <!-- Modais com detalhamento das instancias -->
                        <div class="modal fade" id="modalStorage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><i class="icon20 i-storage"></i> Detalhes do Storage</h4>
                                    </div>
                                    <div class="modal-body">
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="modal fade" id="modalHardware" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><i class="icon20 i-cogs"></i> Detalhes do Hardware</h4>
                                    </div>
                                    <div class="modal-body">
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="modal fade" id="modalDesempenho" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><i class="icon20 i-health"></i> Detalhes de Desempenho</h4>
                                    </div>
                                    <div class="modal-body">
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="modal fade" id="modalAlertas" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><i class="icon20 i-bell-2"></i> Detalhes dos Alertas</h4>
                                    </div>
                                    <div class="modal-body">
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Fim Modais com detalhamento das instancias -->
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <script>
                        document.getElementById("cabecalho").innerHTML = "";
                        document.getElementById("instancias").innerHTML = "";
                    </script>
                    <% }%>
                </div>

            </div>
        </div>
    </section>
</div>

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />
