<%-- Imports --%>

<%@page import="br.com.monitoranuvem.model.Custo"%>
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
                    <li><a href="/monitoranuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active">Custo Atual</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-coin"></i> Custo Atual</h1>
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
                                            <th>Provedor</th>
                                            <th>Tempo de Utilização</th>
                                            <th>Custo Atual</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                                ArrayList<Custo> listaCusto = (ArrayList<Custo>) session.getAttribute("listaCusto");
                                                session.removeAttribute("listaCusto");
                                        %>
                                        <% for (Custo custo : listaCusto) { %>
                                        <tr class="gradeA">
                                            <td><% out.print(custo.getProv().getNome()); %></td>
                                            <td><% out.print(custo.getTotalHoras()); %></td>
                                            <td><% out.print(custo.getCusto()); %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Provedor</th>
                                            <th>Tempo de Utilização</th>
                                            <th>Custo Atual</th>
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
