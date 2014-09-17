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
                    <li><a href="#">Dashboard</a></li>
                    <li class="active">Detalhes</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="page-header">
                            <h3>Inst�ncias - Provedor</h3>
                        </div>
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>ID:</th>
                                    <td class="center">${param}</td>
                                </tr>
                                <tr>
                                    <th>Nome:</th>
                                    <td class="center">${param}</td>
                                </tr>
                                <tr>
                                    <th>Tipo:</th>
                                    <td class="center">${param}</td>
                                </tr>
                                <tr>
                                    <th>Localiza��o:</th>
                                    <td class="center">${param}</td>
                                </tr>
                                <tr>
                                    <th>Status:</th>
                                    <td class="center">${param}</td>
                                </tr>
                                <tr>
                                    <th>Detalhes:</th>
                                    <td class="center"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>                    
                </div>
            </div>

        </div>
    </section>

</div>

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />