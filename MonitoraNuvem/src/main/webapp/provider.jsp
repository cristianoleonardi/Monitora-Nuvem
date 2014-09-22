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

                <% if (session.getAttribute("responseAction") == "Ok") { %>
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <% out.print(session.getAttribute("responseMsg")); %>
                </div>
                <% session.removeAttribute("responseAction"); %>
                <% session.removeAttribute("responseMsg"); %>
                <% } else if (session.getAttribute("responseAction") == "Erro") { %>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <% out.print(session.getAttribute("responseMsg")); %>
                </div>
                <% session.removeAttribute("responseAction"); %>
                <% session.removeAttribute("responseMsg"); %>
                <% } %>

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
                                    <% Provider provedor = (Provider) session.getAttribute("provedor"); %>
                                    <% if (session.getAttribute("action") == "atualizarProvider") { %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">ID</label>
                                        <div class="col-lg-10">
                                            <input class="form-control" type="text" name="id" value="<% if (provedor != null) {
                                                    out.print(provedor.getId());
                                                } %>" required readonly /><br />
                                        </div>
                                    </div>
                                    <% } %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Provedor</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="provider" value="<% if (provedor != null) {
                                                    out.print(provedor.getNome());
                                                } %>" required maxlength="45" /><br />
                                        </div>
                                    </div>
                                    <% session.removeAttribute("provedor"); %>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2">
                                            <div class="pad-left15">
                                                <% if (session.getAttribute("action") == "atualizarProvider") { %>
                                                <input type="hidden" name="action" value="atualizarProvider" />
                                                <% } else { %>
                                                <input type="hidden" name="action" value="criarProvider" />
                                                <% } %>
                                                <button type="submit" class="btn btn-success btn-xs"><i class="icon10 i-database"> <% if (session.getAttribute("action") == "atualizarProvider") out.print("Atualizar"); else out.print("Cadastrar"); %></i></button>
                                                <a href="provider" class="btn btn-danger btn-xs"><i class="icon10 i-close"> Cancelar</i></a>
                                                <% session.removeAttribute("action"); %>
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
                                            <th>Editar</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvedores"); %>
                                        <% for (Provider provedores : listaProvedores) { %>
                                        <tr class="gradeA">
                                            <td class="center"><% out.print(provedores.getId()); %></td>
                                            <td><% out.print(provedores.getNome()); %></td>
                                            <td class="center">
                                                <form action="provider" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(provedores.getId()); %>" />
                                                    <input type="hidden" name="action" value="buscarProvider" />
                                                    <button type="submit" class="btn btn-primary btn-xs" title="Editar"><i class="icon10 i-pencil"> Editar</i></button>&nbsp;
                                                </form>
                                            </td>
                                            <td class="center">
                                                <form action="provider" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(provedores.getId()); %>" />
                                                    <input type="hidden" name="action" value="deletarProvider" />
                                                    <button type="submit" class="btn btn-danger btn-xs" title="Deletar"><i class="icon10 i-close"> Remover</i></button>
                                                </form>
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
