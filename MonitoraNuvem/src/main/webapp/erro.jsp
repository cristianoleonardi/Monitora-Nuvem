<%-- Imports --%>

<%-- Inclusão do cabeçalho da página --%>
<jsp:include page="header.jsp" />

<div class="container-fluid">

    <div class="errorContainer">
        <div class="page-header">
            <h1 class="center">Erro</h1>
        </div>

        <h2 class="center gap20">Erro: <% out.print(request.getParameter("description")); %></h2>

        <div class="center gap-bottom5">
            <hr class="seperator">

            <a href="javascript: history.go(-1)" class="btn btn-default pull-left gap-left20"><i class="icon16 i-arrow-left-7 gap-left0"></i> Voltar</a>
            <a href="/MonitoraNuvem" class="btn btn-default"><i class="icon16 i-home-6 gap-left0"></i> Página Inicial</a>
        </div>

    </div>

</div>

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />