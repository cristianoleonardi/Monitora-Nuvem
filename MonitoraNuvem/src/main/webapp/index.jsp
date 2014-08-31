<%-- 
    Document   : index
    Created on : 31/08/2014, 17:38:42
    Author     : Cristiano Leonardi, Márcio Bolzan
--%>

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
            <jsp:useBean id="providerdialog" scope="page" class="br.com.monitoranuvem.view.ProviderDialog" />
            <jsp:getProperty name="providerdialog" property="allProvider" />
            Provedor:
            <select name="provider">
                
                    <option>Teste</option>
                
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
