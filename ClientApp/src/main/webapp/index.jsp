<%@ page import="org.wso2.carbon.client.app.ClientAppConstants" %>
<html>
<head>
    <title>JWT Client App</title>
</head>
<body>
<h2>JWT Client App</h2>


<form action="output.jsp" method="post">

    <table>
        <tr>
            <td>Tenant ID</td>
            <td><input type="text" name="<%=ClientAppConstants.TENANT_ID%>"/></td>
        </tr>

        <tr>
            <td>Tenant Domain</td>
            <td><input type="text" name="<%=ClientAppConstants.TENANT_DOMAIN%>"/></td>
        </tr>

        <tr>
            <td>Resource Name</td>
            <td><input type="text" name="<%=ClientAppConstants.RESOURCE_NAME%>"/></td>
        </tr>

        <tr>
            <td>Operation</td>
            <td><input type="text" name="<%=ClientAppConstants.OPERATION%>"/></td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" name="submit" value="Send Request"/></td>
        </tr>

    </table>



</form>

</body>
</html>
