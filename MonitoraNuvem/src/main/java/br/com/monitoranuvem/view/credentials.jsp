<%-- 
    Document   : credentials
    Created on : 31/08/2014, 17:38:42
    Author     : Cristiano Leonardi, Márcio Bolzan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monitora Nuvem - jclouds</title>
    </head>
    <body>
        <div><h1>Conexão - Efetue uma conexão com o provedor desejado::</h1></div>
        <form name="Name Input Form" action="startmon.jsp">
            <jsp:useBean id="providerdialog" scope="page" class="br.com.monitoranuvem.view.ProviderDialog" />
            Provedor:
            <select name="providers">
                <c:forEach var="provider" items="${providerdialog.allProvider}">
                    <option value="${provider}"><c:out value="${provider.providerName}" /></option>
                </c:forEach>
            </select>
            <br />
            Access Key ID:
            <input type="text" name="login" size="30" />
            <br />
            Secret Access Key:
            <input type="text" name="password" size="50" />
            <br /><br />
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
