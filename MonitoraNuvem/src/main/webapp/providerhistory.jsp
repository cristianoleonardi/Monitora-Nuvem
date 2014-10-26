<%-- Imports --%>

<%@page import="java.util.ArrayList"%>
<%@page import="br.com.monitoranuvem.model.HistoryProvider"%>
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
                    <li><a href="/monitoranuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active">Histórico</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-archive"></i> Histórico de Monitorações</h1>
                </div>

                <div class="row">

                    <div class="col-lg-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-archive"></i></div> 
                                <h4>Histórico das Instâncias Monitoradas</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID Instância</th>
                                            <th>Instância</th>
                                            <th>Status</th>
                                            <th>Data Monitoração</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                                ArrayList<HistoryProvider> listaHistoryProvider = (ArrayList<HistoryProvider>) session.getAttribute("listaHistoryProvider");
                                                session.removeAttribute("listaHistoryProvider");
                                        %>
                                        <% for (HistoryProvider historico : listaHistoryProvider) { %>
                                        <tr class="gradeA">
                                            <td><% out.print(historico.getInstanceProvider().getIdInstance()); %></td>
                                            <td><% out.print(historico.getInstanceProvider().getInstanceProvider()); %></td>
                                            <td><% out.print(historico.getStatus()); %></td>
                                            <td class="center"><% out.print(historico.getDataUpdate()); %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>ID Instância</th>
                                            <th>Instância</th>
                                            <th>Status</th>
                                            <th>Data Monitoração</th>
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

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />

