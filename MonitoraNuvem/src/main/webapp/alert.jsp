<%-- Imports --%>
<%@page import="br.com.monitoranuvem.model.Alerts"%>
<%@page import="br.com.monitoranuvem.model.Provider"%>
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
                    <li class="active">Instâncias por Status</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-bell-2"></i> Cadastro de Alertas</h1>
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
                                <div class="icon"><i class="icon20 i-direction"></i></div>
                                <h4>Instâncias por Status</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <form class="form-horizontal" action="alertview" method="POST">
                                    <%
                                        Alerts alerts = (Alerts) session.getAttribute("alert");
                                        session.removeAttribute("alert");
                                    %>

                                    <% if (session.getAttribute("action") == "atualizarAlerts") { %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">ID</label>
                                        <div class="col-lg-2">
                                            <input class="form-control" type="text" name="id" value="<% if (alerts != null) {
                                                    out.print(alerts.getIdAlerts());
                                                } %>" required readonly />
                                        </div>
                                    </div>
                                    <% } %>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Nome do Alerta</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="alertname" value="<% if (alerts != null) {
                                                    out.print(alerts.getNameAlerts());
                                                } %>" maxlength="45" required />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Provedor</label>
                                        <div class="col-lg-5">
                                            <select name="provider" class="form-control" <% if (alerts != null) out.print(""); %>>
                                                <%
                                                    ArrayList<Provider> listaProvedores = (ArrayList<Provider>) session.getAttribute("listaProvedores");
                                                    session.removeAttribute("listaProvedores");
                                                %>
                                                <% for (Provider provedores : listaProvedores) { %>
                                                <option value="<% out.print(provedores.getId()); %>" <% if (alerts != null && alerts.getProv().getId() == provedores.getId()) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    <% out.print(provedores.getNome()); %>
                                                </option>
                                                <% }%>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Status da Instância</label>
                                        <div class="col-lg-5">
                                            <select name="status" class="form-control" <% if (alerts != null) out.print(""); %>>
                                                <option value="RUNNING" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("RUNNING")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Running
                                                </option>
                                                <option value="ERROR" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("ERROR")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Error
                                                </option>
                                                <option value="PENDING" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("PENDING")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Pending
                                                </option>
                                                <option value="SUSPENDED" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("SUSPENDED")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Suspended
                                                </option>
                                                <option value="TERMINATED" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("TERMINATED")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Terminated
                                                </option>
                                                <option value="UNRECOGNIZED" <% if (alerts != null && alerts.getStatusProvider().equalsIgnoreCase("UNRECOGNIZED")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Unrecognized
                                                </option>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Métrica</label>
                                        <div class="col-lg-5">
                                            <select name="metric" class="form-control" <% if (alerts != null) out.print(""); %>>
                                                <option value="n" <% if (alerts != null && alerts.getMetrics().equalsIgnoreCase("n")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Quantidade ( n )
                                                </option>
                                                <option value="%" <% if (alerts != null && alerts.getMetrics().equalsIgnoreCase("%")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Percentual ( % )
                                                </option>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Operação</label>
                                        <div class="col-lg-5">
                                            <select name="operation" class="form-control" <% if (alerts != null) out.print(""); %>>
                                                <option value="=" <% if (alerts != null && alerts.getOperation().equalsIgnoreCase("=")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Igual ( = )
                                                </option>
                                                <option value=">" <% if (alerts != null && alerts.getOperation().equalsIgnoreCase(">")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Maior que ( &gt; )
                                                </option>
                                                <option value=">=" <% if (alerts != null && alerts.getOperation().equalsIgnoreCase(">=")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Maior igual ( &ge; )
                                                </option>
                                                <option value="<" <% if (alerts != null && alerts.getOperation().equalsIgnoreCase("<")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Menor que ( &lt; )
                                                </option>
                                                <option value="<=" <% if (alerts != null && alerts.getOperation().equalsIgnoreCase("<=")) {
                                                        out.print("selected=\"selected\"");
                                                    } %>>
                                                    Menor igual ( &le; )
                                                </option>
                                            </select>  
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Valor da Métrica</label>
                                        <div class="col-lg-5">
                                            <input class="form-control" type="text" name="metricvalue" value="<% if (alerts != null) {
                                                    out.print(alerts.getValueMetrics());
                                                } %>" maxlength="45" required />
                                        </div>
                                    </div>
                                        <div class="form-group">
                                        <label class="col-lg-2 control-label" for="normal">Enviar notificação para o(s) E-mail(s)</label>
                                        <div class="col-lg-5">
                                            <textarea class="form-control" name="emaildestino" maxlength="500" rows="3"><% if (alerts != null) {out.print(alerts.getMail());} else {out.print("");} %></textarea>
                                            <span class="help-block">Ao digitar mais de um e-mail, separe-os com vírgula.</span><br />
                                        </div>
                                    </div>
                                    <% session.removeAttribute("alert"); %>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2">
                                            <div class="pad-left15">
                                                <% if (session.getAttribute("action") == "atualizarAlerts") { %>
                                                <input type="hidden" name="action" value="atualizarAlerts" />
                                                <% } else { %>
                                                <input type="hidden" name="action" value="criarAlerts" />
                                                <% } %>
                                                <button type="submit" class="btn btn-success btn-xs"><i class="icon10 i-database"> <% if (session.getAttribute("action") == "atualizarAlerts") {
                                                        out.print("Atualizar");
                                                    } else {
                                                        out.print("Cadastrar");
                                                    } %></i></button>
                                                <a href="alertview" class="btn btn-danger btn-xs"><i class="icon10 i-close"> Cancelar</i></a>
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
                                <h4>Alertas Cadastrados</h4>
                                <a href="#" class="minimize"></a>
                            </div><!-- End .panel-heading -->

                            <div class="panel-body">

                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nome do Alerta</th>
                                            <th>Provedor</th>
                                            <th>Status</th>
                                            <th>Métrica</th>
                                            <th>Operação</th>
                                            <th>Valor</th>
                                            <th>Editar</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Alerts> listaAlerts = (ArrayList<Alerts>) session.getAttribute("listaAlerts");
                                            session.removeAttribute("listaAlerts");
                                        %>
                                        <% for (Alerts alert : listaAlerts) { %>
                                        <tr class="gradeA">
                                            <td class="center"><% out.print(alert.getIdAlerts()); %></td>
                                            <td><% out.print(alert.getNameAlerts()); %></td>
                                            <td><% out.print(alert.getProv().getNome()); %></td>
                                            <td><% out.print(alert.getStatusProvider()); %></td>
                                            <td>
                                                <%
                                                    switch (alert.getMetrics()) {
                                                        case "n":
                                                            out.print("Quantidade ( n )");
                                                            break;
                                                        case "%":
                                                            out.print("Percentual ( % )");
                                                            break;
                                                        default:
                                                            out.print("Não Selecinada");
                                                    }
                                                %>
                                            </td>
                                            <td>
                                                <%
                                                    switch (alert.getOperation()) {
                                                        case "=":
                                                            out.print("Igual ( = )");
                                                            break;
                                                        case ">":
                                                            out.print("Maior ( > )");
                                                            break;
                                                        case ">=":
                                                            out.print("Maior igual ( >= )");
                                                            break;
                                                        case "<":
                                                            out.print("Menor ( < )");
                                                            break;
                                                        case "<=":
                                                            out.print("Menor Igual ( <= )");
                                                            break;
                                                        default:
                                                            out.print("Não Selecinada");
                                                    }
                                                %>
                                            </td>
                                            <td><% out.print(alert.getValueMetrics()); %></td>
                                            <td class="center">
                                                <form action="alertview" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(alert.getIdAlerts()); %>" />
                                                    <input type="hidden" name="action" value="buscarAlerts" />
                                                    <button type="submit" class="btn btn-primary btn-xs" title="Editar"><i class="icon10 i-pencil"> Editar</i></button>&nbsp;
                                                </form>
                                            </td>
                                            <td class="center">
                                                <form action="alertview" method="POST">
                                                    <input type="hidden" name="id" value="<% out.print(alert.getIdAlerts()); %>" />
                                                    <input type="hidden" name="action" value="deletarAlerts" />
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
