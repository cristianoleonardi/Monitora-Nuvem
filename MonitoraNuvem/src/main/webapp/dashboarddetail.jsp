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
                                    <td class="center"></td>
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
                                            <a class="btn btn-xs" title="Storage"><i class="icon20 i-storage"></i></a>
                                            <a class="btn btn-xs" title="Hardware"><i class="icon20 i-cogs"></i></a>
                                            <a class="btn btn-xs" title="Desempenho"><i class="icon20 i-health"></i></a>
                                            <a class="btn btn-xs" title="Alertas"><i class="icon20 i-bell-2"></i></a>
                                        </div>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
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
