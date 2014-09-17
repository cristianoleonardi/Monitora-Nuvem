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
                    <li><a href="#"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li><a href="#">Dashboard</a></li>
                    <li class="active">Detalhes</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="page-header">
                            <h3>Instâncias - Provedor</h3>
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
                                    <th>Localização:</th>
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

<%-- Inclusão do rodapé da página --%>
<jsp:include page="footer.jsp" />