<%-- Imports --%>

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
                    <li class="active">Instâncias por Status</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-bell-2"></i> Cadastro de Alertas</h1>
                </div>

                <div class="row">

                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-direction"></i></div>
                                <h4>Instâncias por Status</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <form class="form-horizontal" action="providerservice" method="POST">
                                    <% //ProviderService prvService = (ProviderService) session.getAttribute("prvService"); %>
                                    <% //if (session.getAttribute("action") == "atualizarProviderService") { %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">ID</label>
                                        <div class="col-lg-2">
                                            <input class="form-control" type="text" name="id" value="<% //if (prvService != null) {
                                                //out.print(prvService.getIdProviderService());
                                                //} %>" required readonly /><br />
                                        </div>
                                    </div>
                                    <% //} %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Nome do Alerta</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="alertname" value="<% //if (prvService != null) {
                                                //out.print(prvService.getProviderService());
                                                //} %>" maxlength="45" /><br />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Provedor</label>
                                        <div class="col-lg-5">
                                            <select name="provider" class="form-control">
                                                <% //ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvedores"); %>
                                                <% //for (Provider provedores : listaProvedores) { %>
                                                <option value="<% //out.print(provedores.getId()); %>" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    <% //out.print(provedores.getNome()); %>
                                                </option>
                                                <% //}%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Status da Instância</label>
                                        <div class="col-lg-5">
                                            <select name="status" class="form-control">
                                                <% //ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvedores"); %>
                                                <% //for (Provider provedores : listaProvedores) { %>
                                                <option value="<% //out.print(provedores.getId()); %>" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    <% //out.print(provedores.getNome()); %>
                                                </option>
                                                <% //}%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Métrica</label>
                                        <div class="col-lg-5">
                                            <select name="metric" class="form-control">
                                                <option value="quantidade" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    Quantidade (N)
                                                </option>
                                                <option value="percentual" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    Percentual (%)
                                                </option>
                                                <% //}%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Operação</label>
                                        <div class="col-lg-5">
                                            <select name="operation" class="form-control">
                                                <option value="igual" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    Igual (=)
                                                </option>
                                                <option value="maior" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    Maior (&gt;)
                                                </option>
                                                <option value="menor" <% //if (prvService != null && prvService.getProvider().getId() == provedores.getId()) {
                                                    //out.print("selected=\"selected\"");
                                                    //} %>>
                                                    Menor (&lt;)
                                                </option>
                                                <% //}%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Valor da Métrica</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="metricvalue" value="<% //if (prvService != null) {
                                                //out.print(prvService.getEndPoint());
                                                //} %>" maxlength="45" /><br />
                                        </div>
                                    </div>
                                    <% //session.removeAttribute("prvService"); %>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2">
                                            <div class="pad-left15">
                                                <% //if (session.getAttribute("action") == "atualizarProviderService") { %>
                                                <input type="hidden" name="action" value="atualizarProviderService" />
                                                <% //} else { %>
                                                <input type="hidden" name="action" value="criarProviderService" />
                                                <% //} %>
                                                <button type="submit" class="btn btn-success btn-xs"><i class="icon10 i-database"> <% //if (session.getAttribute("action") == "atualizarProviderService") {
                                                    //out.print("Atualizar");
                                                    //} else {
                                                    //out.print("Cadastrar");
                                                    //} %></i></button>
                                                <a href="providerservice" class="btn btn-danger btn-xs"><i class="icon10 i-close"> Cancelar</i></a>
                                                <% //session.removeAttribute("action"); %>
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
                                <h4>Alertas Cadastrados</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>XX</th>
                                            <th>XX</th>
                                            <th>XX</th>
                                            <th>XX</th>
                                            <th>XX</th>
                                            <th>Editar</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% //ArrayList<ProviderService> listaPrvServices = (ArrayList<ProviderService>) session.getAttribute("listaPrvServices"); %>
                                        <% //for (ProviderService prvServices : listaPrvServices) { %>
                                        <tr class="gradeA">
                                            <td class="center"><% //out.print(prvServices.getIdProviderService()); %></td>
                                            <td><% //out.print(prvServices.getProvider().getNome()); %></td>
                                            <td><% //out.print(prvServices.getProviderService()); %></td>
                                            <td><% //out.print(prvServices.getAcessKey()); %></td>
                                            <td><% //out.print(prvServices.getSecretKey()); %></td>
                                            <td class="center">
                                                <form action="providerservice" method="POST">
                                                    <input type="hidden" name="id" value="<% //out.print(prvServices.getIdProviderService()); %>" />
                                                    <input type="hidden" name="action" value="buscarProviderService" />
                                                    <button type="submit" class="btn btn-primary btn-xs" title="Editar"><i class="icon10 i-pencil"> Editar</i></button>&nbsp;
                                                </form>
                                            </td>
                                            <td class="center">
                                                <form action="providerservice" method="POST">
                                                    <input type="hidden" name="id" value="<% //out.print(prvServices.getIdProviderService()); %>" />
                                                    <input type="hidden" name="action" value="deletarProviderService" />
                                                    <button type="submit" class="btn btn-danger btn-xs" title="Remover"><i class="icon10 i-close"> Remover</i></button>
                                                </form>
                                            </td>
                                        </tr>
                                        <% //}%>
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
