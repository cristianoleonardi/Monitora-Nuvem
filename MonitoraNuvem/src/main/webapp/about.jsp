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
                    <li><a href="/monitoranuvem"><i class="icon16 i-home-4"></i>Home</a></li>
                    <li class="active">Sobre o Sistema</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-question-5"></i> Sobre</h1>
                </div>
                
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="page-header">
                                    <h4>Trabalho de Conclusão de Curso</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>Pontifícia Universidade Católica do Rio Grande do Sul</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Faculdade de Informática</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Bacharelado em Sistemas de Informação</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Uma ferramenta para monitoração integrada de múltiplas nuvens computacionais</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Porto Alegre - 2014</li>
                                </ul>
                                <br />
                                <div class="page-header">
                                    <h4>Alunos:</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>Cristiano Lopes Leonardi</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Márcio Sonza Bolzan</li>
                                </ul>
                                <br />
                                <div class="page-header">
                                    <h4>Orientador:</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>Prof. Dr. Tiago Ferreto</li>
                                </ul>
                                <br />
                                <div class="page-header">
                                    <h4>Avaliadores:</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>1º Avaliador</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>2º Avaliador</li>
                                </ul>
                            </div>
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
