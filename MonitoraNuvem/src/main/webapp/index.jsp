<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 31/08/2014, 17:38:42
    Author     : Cristiano Leonardi, Márcio Bolzan
--%>
<%@page import="java.util.List"%>
<%@page import="br.com.monitoranuvem.model.Provider"%>
<%@page import="br.com.monitoranuvem.view.ProviderDialog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>Connection</div>
        <form name="Name Input Form" action="response.jsp">
            <jsp:useBean id="providerdialog" scope="session" class="br.com.monitoranuvem.view.ProviderDialog" />
            Provedor:
            <select name="provider">
                <%
                    ProviderDialog providers = new ProviderDialog();
                    List<Provider> providerList = providers.getAllProvider();
                %>
                <c:forEach items="${providerList}" var="provider">
                    <option>${provider.providerName}</option>
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
