<%-- Imports --%>

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
        Provider p = (Provider)session.getAttribute("provider");
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
                                    <% Map<String, Integer> mapQtdStatusProvider = (Map<String, Integer>) session.getAttribute("mapQtdStatusProvider"); %>
                                    <td class="center" bgcolor="#CAFF70">
                                        <% if (mapQtdStatusProvider.containsKey("RUNNING")) {
                                                out.print(mapQtdStatusProvider.get("RUNNING"));
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#E9967A">
                                        <% if (mapQtdStatusProvider.containsKey("ERROR")) {
                                                out.print(mapQtdStatusProvider.get("ERROR"));
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#FFFACD">
                                        <% if (mapQtdStatusProvider.containsKey("PENDING")) {
                                                out.print(mapQtdStatusProvider.get("PENDING"));
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#EEE5DE">
                                        <% if (mapQtdStatusProvider.containsKey("SUSPENDED")) {
                                                out.print(mapQtdStatusProvider.get("SUSPENDED"));
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#EEE9BF">
                                        <% if (mapQtdStatusProvider.containsKey("TERMINATED")) {
                                                out.print(mapQtdStatusProvider.get("TERMINATED"));
                                            } else {
                                                out.print("0");
                                            }
                                        %>
                                    </td>

                                    <td class="center" bgcolor="#FFFFF0">
                                        <% if (mapQtdStatusProvider.containsKey("UNRECOGNIZED")) {
                                                out.print(mapQtdStatusProvider.get("UNRECOGNIZED"));
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
                        <div class="page-header">
                            <h4><i class="icon10 i-screen-4"></i> Detalhamento das instâncias NONONONONO</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-3">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>ID:</th>
                                    <td class="center"></td>
                                </tr>
                                <tr>
                                    <th>Nome:</th>
                                    <td class="center"></td>
                                </tr>
                                <tr>
                                    <th>Status:</th>
                                    <td class="center"></td>
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
                                <%%>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </section>
</div>

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />
