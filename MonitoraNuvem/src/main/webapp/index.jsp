<%-- 
    Document   : index
    Created on : 31/08/2014, 17:38:42
    Author     : Cristiano Leonardi, Márcio Bolzan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="br.com.monitoranuvem.model.Provider"%>
<%@page import="br.com.monitoranuvem.view.ProviderDialog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monitora Nuvem - jclouds</title>
    </head>
    <body>
        <div>Conexão - Efetue uma conexão com o provedor desejado</div>
        <form name="Name Input Form" action="response.jsp">
            Provedor:
            <select name="provider">
                <jsp:useBean id="providerdialog" scope="page" class="br.com.monitoranuvem.view.ProviderDialog" />
                <c:forEach var="provider" items="${providerdialog.allProvider}">
                    <option value="${provider}"><c:out value="${provider.providerName}" /></option>
                </c:forEach>
            </select>
            <br />
            Access Key ID:
            <input type="text" name="name" size="30" />
            <br />
            Secret Access Key:
            <input type="text" name="endereco" size="50" />
            <br />
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
