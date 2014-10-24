<%-- Imports --%>

<%@page import="br.com.monitoranuvem.model.QtdStatusProvider"%>
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
                    <li class="active">Dashboard</li>
                </ul>
            </div>

            <div class="container-fluid">
                <div id="heading" class="page-header">
                    <h1><i class="icon20 i-dashboard"></i> Dashboards</h1>
                </div>

                <div class="row">
                    <div class="page-header">
                        <h4><i class="icon10 i-screen-4"></i> Compute Service</h4>
                    </div>

                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-pie-5"></i></div> 
                                <h4>Percentual de Instâncias Ativas por Provedor</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <input type="hidden" id="dadosgrafico1" value='<% out.print(session.getAttribute("listaActiveInstanceProvider")); %>' />
                                <div class="active-instance-by-provider" style="width: 100%; height:250px; margin-top:10px;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-bars"></i></div> 
                                <h4>Total de Instâncias por Provedor por Status</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <input type="hidden" id="dadosgrafico2" value='<% out.print(session.getAttribute("listaStatusInstanceProvider")); %>' />
                                <input type="hidden" id="dadosgrafico3" value='<% out.print(session.getAttribute("labels")); %>' />
                                <div class="instance-by-status" style="width: 100%; height:250px; margin-top:10px;"></div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="icon"><i class="icon20 i-stats"></i></div>
                                <h4>Histórico de Instâncias por Data e Status - Últimos 30 Dias</h4>
                                <a href="#" class="minimize"></a>
                            </div>

                            <div class="panel-body">
                                <input type="hidden" id="dadosgrafico4" value='<% out.print(session.getAttribute("historyLastThirtyDays")); %>' />
                                <input type="hidden" id="dadosgrafico5" value='<% out.print(session.getAttribute("firstDay")); %>' />
                                <input type="hidden" id="dadosgrafico6" value='<% out.print(session.getAttribute("lastDay")); %>' />
                                <div class="history-instances-by-status" style="width:100%; height:250px; margin-top:10px;"></div>
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
