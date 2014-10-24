<%-- Imports --%>

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
                                    <h4>Trabalho de Conclus�o de Curso</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>Pontif�cia Universidade Cat�lica do Rio Grande do Sul</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Faculdade de Inform�tica</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Bacharelado em Sistemas de Informa��o</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Uma ferramenta para monitora��o integrada de m�ltiplas nuvens computacionais</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>Porto Alegre - 2014</li>
                                </ul>
                                <br />
                                <div class="page-header">
                                    <h4>Alunos:</h4>
                                </div>
                                <ul class="list-unstyled">
                                    <li><i class="icon16 i-arrow-right-3"></i>Cristiano Lopes Leonardi</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>M�rcio Sonza Bolzan</li>
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
                                    <li><i class="icon16 i-arrow-right-3"></i>1� Avaliador</li>
                                    <li><i class="icon16 i-arrow-right-3"></i>2� Avaliador</li>
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

<%-- Inclus�o do rodap� da p�gina --%>
<jsp:include page="footer.jsp" />
