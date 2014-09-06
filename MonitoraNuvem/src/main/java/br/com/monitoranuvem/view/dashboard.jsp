<%-- 
    Document   : dashboard
    Created on : 06/09/2014, 16:35:32
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
    </head>
    <body>
        <h1>Instancia ${param.instancia}</h1>
        <p>ID: ${param.id}</p>
        <p>Provedor: ${param.provedor}</p>
        <p>Localização: ${param.localizacao}</p>
        <p>Familia: ${param.familia}</p>
        <p>Status: ${param.status}</p>
        <p>Host/IP: ${param.ip}</p>
        <p>Sistema Operacional: ${param.so}</p>
        
        <p>Ativo desde ${param.start}</p>
        
    </body>
</html>
