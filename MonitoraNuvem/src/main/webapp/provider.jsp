<%-- Inclusão do cabeçalho da página --%>
<%@page import="br.com.monitoranuvem.model.Provider"%>
<%@page import="java.util.ArrayList"%>
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
                    <li class="active">Cadastro de Provedores</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-cloud-download"></i> Cadastro de Provedores</h1>
                </div>
                <div class="row">

                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-cloud-download"></i></div>
                                <h4>Cadastro de Provedores</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">
                                <form class="form-horizontal" action="provider" method="POST">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Provedor</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="text" name="provider" required /><br />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2">
                                            <div class="pad-left15">
                                                <input type="hidden" name="action" value="criarProvider" />
                                                <button type="submit" class="btn btn-primary">Cadastrar</button>
                                                <button type="button" class="btn">Cancelar</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-table"></i></div> 
                                <h4>Provedores Cadastrados</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Descrição</th>
                                            <th>Ações</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvedores"); %>
                                        <% for (Provider provedor : listaProvedores) { %>
                                        <tr class="gradeA">
                                            <td class="center"><% out.print(provedor.getId()); %></td>
                                            <td><% out.print(provedor.getNome()); %></td>
                                            <td class="center">
                                                <a href="" class="btn btn-xs" title="Editar"><i class="icon20 i-pencil"></i></a>&nbsp;
                                                <a href="" class="btn btn-xs" title="Remover"><i class="icon20 i-close"></i></a>
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
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
