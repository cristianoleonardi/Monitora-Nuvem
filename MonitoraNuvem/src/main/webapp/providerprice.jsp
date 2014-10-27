<%-- Imports --%>

<%@page import="br.com.monitoranuvem.model.Provider"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.monitoranuvem.model.ProviderPrice"%>
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
                    <li class="active">Preço dos Serviços</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-coins"></i> Cadastro de Preço dos Serviços</h1>
                </div>

                <%-- Alertas de cadastro --%>
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
                                <div class="icon"><i class="icon20 i-coins"></i></div>
                                <h4>Cadastro de Preço dos Serviços</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <form class="form-horizontal" action="providerpriceview" method="POST">
                                    <%
                                        ProviderPrice providerPrice = (ProviderPrice) session.getAttribute("providerPrice");
                                        session.removeAttribute("providerPrice");
                                    %>

                                    <% if (session.getAttribute("action") == "atualizarProviderPrice") { %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">ID</label>
                                        <div class="col-lg-2">
                                            <input class="form-control" type="text" name="id" value="<% if (providerPrice != null) {
                                                    out.print(providerPrice.getIdProviderPrice());
                                                } %>" required readonly />
                                        </div>
                                    </div>
                                    <% } %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Tipo de Instância</label>
                                        <div class="col-lg-5">
                                            <select name="instancetype" class="form-control" <% if (providerPrice != null) {
                                                    out.print("");
                                                } %>>
                                                <%
                                                    ArrayList<String> listaInstanceType = (ArrayList<String>) session.getAttribute("listaInstanceType");
                                                    session.removeAttribute("listaInstanceType");
                                                %>
                                                <% for (String instanceType : listaInstanceType) { %>
                                                <option value="<% out.print(instanceType); %>" <% if (providerPrice != null && providerPrice.getInstanceType().equalsIgnoreCase(instanceType)) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    <% out.print(instanceType); %>
                                                </option>
                                                <% }%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Provedor</label>
                                        <div class="col-lg-5">
                                            <select name="provider" class="form-control" <% if (providerPrice != null) {
                                                    out.print("");
                                                } %>>
                                                <%
                                                    ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvider");
                                                    session.removeAttribute("listaProvider");
                                                %>
                                                <% for (Provider provedores : listaProvedores) { %>
                                                <option value="<% out.print(provedores.getId()); %>" <% if (providerPrice != null && providerPrice.getProvider().getId() == provedores.getId()) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    <% out.print(provedores.getNome()); %>
                                                </option>
                                                <% }%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Valor por Hora</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="price" value="<% if (providerPrice != null) {
                                                    out.print(providerPrice.getPrice());
                                                } %>" maxlength="45" required />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2">
                                            <div class="pad-left15">
                                                <% if (session.getAttribute("action") == "atualizarProviderPrice") { %>
                                                <input type="hidden" name="action" value="atualizarProviderPrice" />
                                                <% } else { %>
                                                <input type="hidden" name="action" value="criarProviderPrice" />
                                                <% } %>
                                                <button type="submit" class="btn btn-success btn-xs"><i class="icon10 i-database"> <% if (session.getAttribute("action") == "atualizarProviderPrice") {
                                                        out.print("Atualizar");
                                                    } else {
                                                        out.print("Cadastrar");
                                                    } %></i></button>
                                                <a href="providerpriceview" class="btn btn-danger btn-xs"><i class="icon10 i-close"> Cancelar</i></a>
                                                <% session.removeAttribute("action");%>
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
                                <h4>Preços Cadastrados</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Provedor</th>
                                            <th>Tipo da Instancia</th>
                                            <th>Preço por Hora</th>
                                            <th>Editar</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<ProviderPrice> listaProviderPrice = (ArrayList<ProviderPrice>) session.getAttribute("listaProviderPrice");
                                            session.removeAttribute("listaProviderPrice");
                                        %>
                                        <% for (ProviderPrice prvPrice : listaProviderPrice) { %>
                                        <tr class="gradeA">
                                            <td class="center"><% out.print(prvPrice.getIdProviderPrice()); %></td>
                                            <td><% out.print(prvPrice.getProvider().getNome()); %></td>
                                            <td><% out.print(prvPrice.getInstanceType()); %></td>
                                            <td><% out.print(prvPrice.getPrice()); %></td>
                                            <td class="center">
                                                <form action="providerpriceview" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(prvPrice.getIdProviderPrice()); %>" />
                                                    <input type="hidden" name="action" value="buscarProviderPrice" />
                                                    <button type="submit" class="btn btn-primary btn-xs" title="Editar"><i class="icon10 i-pencil"> Editar</i></button>&nbsp;
                                                </form>
                                            </td>
                                            <td class="center">
                                                <form action="providerpriceview" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(prvPrice.getIdProviderPrice()); %>" />
                                                    <input type="hidden" name="action" value="deletarProviderPrice" />
                                                    <button type="submit" class="btn btn-danger btn-xs" title="Remover"><i class="icon10 i-close"> Remover</i></button>
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
