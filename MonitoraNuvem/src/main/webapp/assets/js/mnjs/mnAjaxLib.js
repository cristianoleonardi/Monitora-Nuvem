var ajax;

//Make Object and Request HTTP

function requestHTTP(type, url, async) {
	
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	}

	else if (window.ActiveXObject) {
		ajax = new ActiveXObject("Msxml2.XMLHTTP");

		if(!ajax) {
			ajax = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	if (ajax)
		startRequest(type, url, async);
	else
		alert("Seu navegador não possui suporte a esta aplicação!");
}

//Start Object and Send Data

function startRequest(type, url, bool) {
	ajax.onreadystatechange = processResponse;
	ajax.open(type, url, bool);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send();
}

//Process Response of Server

function processResponse() {
	if(ajax.readyState == 4) {
		if(ajax.status == 200) {
			processData();
		}
		else {
			alert("Problema na comunicacao com o objeto XMLHttpRequest.");
		}
	}
}
