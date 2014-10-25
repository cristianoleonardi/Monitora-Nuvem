<aside id="sidebar">
    <div class="side-options">
        <ul class="list-unstyled">
            <li>
                <a href="#" id="collapse-nav" class="act act-primary tip" title="">
                    <i class="icon16 i-arrow-left-7"></i>
                </a>
            </li>
        </ul>
    </div>

    <div class="sidebar-wrapper">
        <nav id="mainnav">
            <ul class="nav nav-list">
                <li>
                    <a href="dashboardview">
                        <span class="icon"><i class="icon20 i-dashboard"></i></span>
                        <span class="txt">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="icon"><i class="icon20 i-cloud"></i></span>
                        <span class="txt">Cadastros</span>
                    </a>
                    <ul class="sub">
                        <li>
                            <a href="provider">
                                <span class="icon"><i class="icon20 i-cloud-download"></i></span>
                                <span class="txt">Provedores</span>
                            </a>
                        </li>
                        <li>
                            <a href="providerservice">
                                <span class="icon"><i class="icon20 i-key"></i></span>
                                <span class="txt">Credenciais</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="icon"><i class="icon20 i-stack-checkmark"></i></span>
                                <span class="txt">Monitorações</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <span class="icon"><i class="icon20 i-bell-2"></i></span>
                        <span class="txt">Alertas</span>
                    </a>
                    <ul class="sub">
                        <li>
                            <a href="alertview">
                                <span class="icon"><i class="icon20 i-direction"></i></span>
                                <span class="txt">Instâncias por Status</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="icon"><i class="icon20 i-coins"></i></span>
                                <span class="txt">Custo das Instâncias</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <span class="icon"><i class="icon20 i-archive"></i></span>
                        <span class="txt">Histórico</span>
                    </a>
                </li>
                <li>
                    <a href="about.jsp">
                        <span class="icon"><i class="icon20  i-question-5"></i></span>
                        <span class="txt">Sobre</span>
                    </a>
                </li>
            </ul>
        </nav>

        <div class="sidebar-widget center">
            <h4 class="sidebar-widget-header"><i class="icon i-cogs"></i> Monitoração</h4>
            <div class="spark-stats">
                <form class="form-horizontal" name="formstartstop">
                    <div class="form-group">
                        <div class="switch" data-on="success" data-off="warning">
                            <!--<input type="hidden" name="action" value="stopstartthread" />-->
                            <input class="toggle" type="checkbox" name="btnmonitoring" id="btnmonitoring" onchange="doStartStopThread();" <% if (session.getAttribute("monitoringstatus") == "started") {
                                    out.print("checked");
                                }%> />
                        </div>
                    </div>
                </form>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Provedor</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id="auto-row">

                    </tbody>
                </table>
            </div>
        </div>

        <div class="sidebar-widget center">
            <h4 class="sidebar-widget-header"><i class="icon i-clock"></i> Próxima Atualização</h4>
            <h3 id="cronometro">Iniciando Contador...</h3>
        </div>

    </div>
</aside>