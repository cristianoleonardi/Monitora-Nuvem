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
                </ul>
            </div>

            <div class="container-fluid">
                <br />
                <br />
                <br />
                <div class="row-fluid">
                    <div class="col-lg-12">

                        <img class="img-responsive center-block" src="assets/images/logo_g.png" />

                    </div>
                </div>
        </div>
    </section>

</div>

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />