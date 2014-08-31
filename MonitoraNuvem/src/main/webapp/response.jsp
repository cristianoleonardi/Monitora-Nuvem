<%-- 
    Document   : response
    Created on : 24/08/2014, 23:22:06
    Author     : Cristiano Leonardi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monitora Nuvem - jclouds</title>
    </head>
    <body>
        <%--Cria um instância para a classe NomeHandler--%>
        <jsp:useBean id="mybean" scope="session" class="tcc.monitoranuvem.hellowebjspmaven.NameHandler" />
        <%--Faz um Set do nome--%>
        <jsp:setProperty name="mybean" property="name" />
        <%--Obtem o nome através de um Get--%>
        <h1>Hello <jsp:getProperty name="mybean" property="name" />!</h1>
        <jsp:setProperty name="mybean" property="endereco" />
        <p>Meu endereço é: <jsp:getProperty name="mybean" property="endereco" /></p>
    </body>
</html>
