<%-- Imports --%>

<%@page import="br.com.monitoranuvem.model.MNComputeService"%>
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
    <section id="content">
        <div class="wrapper">
            <div class="crumb">
                <ul class="breadcrumb">
                    <li><a href="/MonitoraNuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active">Dashboard</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-dashboard"></i> Dashboard</h1>
                </div>

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
                            <% ArrayList<MNComputeService> listaComputeService = (ArrayList<MNComputeService>) session.getAttribute("listaComputeService"); %>
                            <% for (MNComputeService cs : listaComputeService) { %>
                            <tr>
                                <td class="center" bgcolor="#CAFF70">
                                    <% if (cs.getStatus().equalsIgnoreCase("RUNNING")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                                <td class="center" bgcolor="#E9967A">
                                    <% if (cs.getStatus().equalsIgnoreCase("ERROR")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                                <td class="center" bgcolor="#FFFACD">
                                    <% if (cs.getStatus().equalsIgnoreCase("PENDING")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                                <td class="center" bgcolor="#EEE5DE">
                                    <% if (cs.getStatus().equalsIgnoreCase("SUSPENDED")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                                <td class="center" bgcolor="#EEE9BF">
                                    <% if (cs.getStatus().equalsIgnoreCase("TERMINATED")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                                <td class="center" bgcolor="#FFFFF0">
                                    <% if (cs.getStatus().equalsIgnoreCase("UNRECOGNIZED")) { %>
                                        <% out.print(cs.getProvedor()); %><br />
                                        <% out.print(cs.getId()); %>
                                    <% } %>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>

                    <div class="page-header">
                        <h4><i class="icon10 i-database"></i> Blob Store - Lista de Storages por Status</h4>
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
                                <td class="center" bgcolor="#CAFF70"></td>
                                <td class="center" bgcolor="#E9967A"></td>
                                <td class="center" bgcolor="#FFFACD"></td>
                                <td class="center" bgcolor="#EEE5DE"></td>
                                <td class="center" bgcolor="#EEE9BF"></td>
                                <td class="center" bgcolor="#FFFFF0"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</div>

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />
