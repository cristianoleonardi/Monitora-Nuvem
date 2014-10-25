var req1;
var req2;
var req3;
var isIE;
var completeField;
var completeTable;
var statusTable;
var autoRow;
var timeToRefresh = 15;
var countToRefresh = timeToRefresh;
var btnStartStopThread;
var notificationArea;
var listAlerts;

function init() {
    //statusTable = document.getElementById("status-table");
    autoRow = document.getElementById("auto-row");
    notificationArea = document.getElementById("notification-area");
    listAlerts = document.getElementById("list-alerts");

    //Atualiza Status
    doUpdate();

    //Atualiza Alerts
    doSendAlertView();

    //Dispara relogio de atualizações
    setInterval("timerRefresh()", 1000);
}

function timerRefresh() {
    if (countToRefresh == 0) {
        document.getElementById('cronometro').innerHTML = 'Atualizando...';
        doUpdate();
        doSendAlertView();
        countToRefresh = timeToRefresh;
    } else {
        countToRefresh--;
        document.getElementById('cronometro').innerHTML = countToRefresh + ' segundos';
    }
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf("MSIE") != -1) {
            isIE = false;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function doUpdate() {
    var url = "monitoringstartstop?action=updatestatus";
    req1 = initRequest();
    req1.open("GET", url, true);
    req1.onreadystatechange = callbackUpdate;
    req1.send(null);
}

function doStartStopThread() {
    btnStartStopThread = document.getElementById("btnmonitoring").checked;
    var url = "monitoringstartstop?action=startstopthread&btnmonitoring=" + btnStartStopThread;
    req2 = initRequest();
    req2.open("GET", url, true);
    req2.onreadystatechange = callbackStartStopThread;
    req2.send(null);
}

function doSendAlertView() {
    var url = "sendalertview";
    req3 = initRequest();
    req3.open("GET", url, true);
    req3.onreadystatechange = callbackSendAlertView;
    req3.send(null);
}

function callbackUpdate() {

    clearTableUpdate();

    if (req1.readyState == 4) {
        if (req1.status == 200) {
            parseUpdate(req1.responseXML);
        }
    }
}

function callbackStartStopThread() {
    if (req2.readyState == 4) {
        if (req2.status == 200) {
            parseStartStopThread(req2.responseXML);
        }
    }
}

function callbackSendAlertView() {

    clearListAlerts();

    if (req3.readyState == 4) {
        if (req3.status == 200) {
            parseSendAlertView(req3.responseXML);
        }
    }
}

function parseUpdate(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var statusThread = responseXML.getElementsByTagName("statusThread")[0];
        if (statusThread.childNodes.length > 0) {
            for (loop = 0; loop < statusThread.childNodes.length; loop++) {
                var thread = statusThread.childNodes[loop];
                var name = thread.getElementsByTagName("name")[0];
                var status = thread.getElementsByTagName("status")[0];
                appendUpdate(name.childNodes[0].nodeValue, status.childNodes[0].nodeValue);
            }
        }
    }
}

function parseStartStopThread(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var startStopThread = responseXML.getElementsByTagName("statusThread")[0];
        if (startStopThread.childNodes.length > 0) {
            for (loop = 0; loop < startStopThread.childNodes.length; loop++) {
                var thread = startStopThread.childNodes[loop];
                var status = thread.getElementsByTagName("status")[0];
                appendStartStopThread(status.childNodes[0].nodeValue);
            }
        }
    }
}

function parseSendAlertView(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var sendAlerts = responseXML.getElementsByTagName("sendAlerts")[0];
        var qtdSendAlerts = sendAlerts.childNodes.length;
        if (sendAlerts.childNodes.length > 0) {
            for (loop = 0; loop < sendAlerts.childNodes.length; loop++) {
                var alert = sendAlerts.childNodes[loop];
                var name = alert.getElementsByTagName("name")[0];
                var status = alert.getElementsByTagName("status")[0];
                var provider = alert.getElementsByTagName("provider")[0];
                appendSendAlertView(
                        name.childNodes[0].nodeValue,
                        status.childNodes[0].nodeValue,
                        provider.childNodes[0].nodeValue,
                        qtdSendAlerts
                        );
            }
        }
    }
}

function appendUpdate(name, status) {
    var row;
    var cell0;
    var cell1;
    var linkElement;
    var statusPrint;
    var srcIcon;
    var img;

    if (isIE) {
        //statusTable.style.display = "block";
        //row.completeTable.insertRow(statusTable.row.length);
        //cell = row.insertCell(0);
        //cell1 = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        cell0 = document.createElement("td");
        cell1 = document.createElement("td");
        row.appendChild(cell0);
        row.appendChild(cell1);
        autoRow.appendChild(row);
    }
    if(status == "null"){
        row.className = "error";
        statusPrint = "Thread Parada";
        srcIcon = "assets/images/icons/bullet_red.png";
    }else if(status == "NEW"){
        row.className = "info";
        statusPrint = "Thread Iniciando";
        srcIcon = "assets/images/icons/bullet_blue.png";
    } else if(status == "TIMED_WAITING" || status == "WAITING" || status == "RUNNABLE") {
        row.className = "success";
        statusPrint = "Thread em Execução";
        srcIcon = "assets/images/icons/bullet_green.png";
    } else if(status == "BLOCKED") {
        row.className = "warning";
        statusPrint = "Thread com Possível Falha";
        srcIcon = "assets/images/icons/bullet_orange.png";
    } else if(status == "TERMINATED") {
        row.className = "error";
        statusPrint = "Thread com Erro";
        srcIcon = "assets/images/icons/bullet_red.png";
    }

    linkElement = document.createElement("a");
    linkElement.className = "statusItem";
    linkElement.setAttribute("href", "#");
    linkElement.appendChild(document.createTextNode(name));
    cell0.appendChild(linkElement);
    
    //cell1.appendChild(document.createTextNode(statusPrint));
    
    img = document.createElement("img");
    img.setAttribute("src", srcIcon);
    img.setAttribute("title", statusPrint);
    
    cell1.appendChild(img);
}

function appendStartStopThread(status) {
    if (status == "started") {
        //btnStartStopThread.setAttribute("checked", "true");
    } else if (status == "stoped") {
        //btnStartStopThread.setAttribute("checked", "false");
    }
}

function appendSendAlertView(name, status, provider, qtdSendAlerts) {
    var li;
    var linkElement;
    var i;

    li = document.createElement("li");
    li.setAttribute("role", "presentation");
    listAlerts.appendChild(li);

    if (qtdSendAlerts > 0) {
        notificationArea.className = "notification red";
        notificationArea.innerHTML = qtdSendAlerts;

        i = document.createElement("i");
        i.className = "icon16 i-warning";

        linkElement = document.createElement("a");
        linkElement.className = "";
        linkElement.setAttribute("href", "#");
        linkElement.appendChild(i);
        linkElement.appendChild(document.createTextNode(name + " (Status - " + status + "; Provedor - " + provider + ")"));
        li.appendChild(linkElement);
    } else {
        notificationArea.className = "";
        notificationArea.innerHTML = "";

        i = document.createElement("i");
        i.className = "icon16 i-info";

        linkElement = document.createElement("a");
        linkElement.className = "";
        linkElement.setAttribute("href", "#");
        linkElement.appendChild(i);
        linkElement.appendChild(document.createTextNode("Nenhum alerta registrado."));
        li.appendChild(linkElement);
    }
}

function clearTableUpdate() {
    if (autoRow.getElementsByTagName("tr").length > 0) {
        for (loop = autoRow.childNodes.length - 1; loop >= 0; loop--) {
            autoRow.removeChild(autoRow.childNodes[loop]);
        }
    }
}

function clearListAlerts() {
    if (listAlerts.getElementsByTagName("li").length > 0) {
        for (loop = listAlerts.childNodes.length - 1; loop >= 0; loop--) {
            listAlerts.removeChild(listAlerts.childNodes[loop]);
        }
        notificationArea.className = "";
        notificationArea.innerHTML = "";
    }
}