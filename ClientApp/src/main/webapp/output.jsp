<%@ page import="org.wso2.carbon.client.app.ClientAppConstants" %>
<%@ page import="org.wso2.carbon.client.app.ServiceConsumer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JWT Client App</title>
</head>
<body>

<%
    String serverHostPort = request.getParameter(ClientAppConstants.SERVER_HOST_PORT);
    String tenantId = request.getParameter(ClientAppConstants.TENANT_ID);
    String  tenantDomain = request.getParameter(ClientAppConstants.TENANT_DOMAIN);
    String  resourceName = request.getParameter(ClientAppConstants.RESOURCE_NAME);
    String  operation = request.getParameter(ClientAppConstants.OPERATION);
    String output;

    if("create".equals(operation)){
        output = ServiceConsumer.createResource(serverHostPort, tenantId, tenantDomain, resourceName);

    } else {
        output = ServiceConsumer.readResource(serverHostPort, tenantId, tenantDomain, resourceName);
    }
%>

<h2><%=output%></h2>

</body>
</html>
