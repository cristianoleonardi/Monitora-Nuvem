<%-- Imports --%>

<%@page import="java.util.ArrayList"%>
<%@page import="br.com.monitoranuvem.model.HistoryProvider"%>
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
                    <li><a href="/monitoranuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active">Hist�rico</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-archive"></i> Hist�rico de Monitora��es</h1>
                </div>

                <div class="row">

                    <div class="col-lg-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-archive"></i></div> 
                                <h4>Hist�rico das Inst�ncias Monitoradas</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID Inst�ncia</th>
                                            <th>Inst�ncia</th>
                                            <th>Status</th>
                                            <th>Data Monitora��o</th>
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
                                            <th>ID Inst�ncia</th>
                                            <th>Inst�ncia</th>
                                            <th>Status</th>
                                            <th>Data Monitora��o</th>
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

