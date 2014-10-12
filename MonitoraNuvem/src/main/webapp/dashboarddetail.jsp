<%-- Imports --%>

<%-- Inclus�o do cabe�alho da p�gina --%>
<jsp:include page="header.jsp" />

<%-- Inclus�o da barra superior da p�gina --%>
<jsp:include page="topbar.jsp" />

<%-- �rea principal da p�gina --%>
<div class="main">

    <%-- Inclus�o do menu lateral --%>
    <jsp:include page="sidebar.jsp" />

    <%-- Sess�o do conte�do da p�gina --%>
    <%
        //Captura dados
        String provider = request.getParameter("provider");
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
                    <h1><i class="icon20 i-cloud"></i> Inst�ncias (<% out.print(provider); %>)</h1>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h4><i class="icon10 i-screen-4"></i> Compute Service - Lista de Inst�ncias por Status</h4>
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
                                <% // ArrayList<MNComputeService> listaComputeService = (ArrayList<MNComputeService>) session.getAttribute("listaComputeService"); %>
                                <% // for (MNComputeService cs : listaComputeService) { %>
                                <tr>
                                    <td class="center" bgcolor="#CAFF70">
                                        <% // if (cs.getStatus().equalsIgnoreCase("RUNNING")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                    <td class="center" bgcolor="#E9967A">
                                        <% // if (cs.getStatus().equalsIgnoreCase("ERROR")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                    <td class="center" bgcolor="#FFFACD">
                                        <% // if (cs.getStatus().equalsIgnoreCase("PENDING")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                    <td class="center" bgcolor="#EEE5DE">
                                        <% // if (cs.getStatus().equalsIgnoreCase("SUSPENDED")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                    <td class="center" bgcolor="#EEE9BF">
                                        <% // if (cs.getStatus().equalsIgnoreCase("TERMINATED")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                    <td class="center" bgcolor="#FFFFF0">
                                        <% // if (cs.getStatus().equalsIgnoreCase("UNRECOGNIZED")) { %>
                                        <% // out.print(cs.getProvedor()); %><br />
                                        <% // out.print(cs.getId()); %>
                                        <% // } %>
                                    </td>
                                </tr>
                                <% // }%>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h4><i class="icon10 i-screen-4"></i> Detalhamento das inst�ncias NONONONONO</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-3">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>ID:</th>
                                    <td class="center"><% out.print(provider); %></td>
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

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />
