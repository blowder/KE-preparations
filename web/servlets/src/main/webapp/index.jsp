<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>CDI/JSP integration test</title>
    </head>
    <body>
        <h1>Hello from ${pingController.ping()}</h1>
        <a href="<%=request.getContextPath()%>/form.jsp">Form test</a>
    </body>
</html>