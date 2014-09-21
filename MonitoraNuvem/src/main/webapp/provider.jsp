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
                    <li><a href="#"><i class="icon16 i-home-4"></i>Home</a></li>
                </ul>
            </div>

            <div class="container-fluid">
                <form action="provider" method="POST">
                    Provedor: <input type="text" name="provider" /><br />
                    <input type="hidden" name="action" value="criarProvider" />
                    <input class="btn" type="submit" value="Gravar" />
                </form>
            </div>

        </div>
    </section>

</div>

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />
