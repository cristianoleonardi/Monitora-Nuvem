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
                </ul>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="col-lg-12">
                        <img class="img-responsive center-block" src="assets/images/logo_g.png" />
                    </div>
                </div>
            </div>
    </section>

</div>

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />