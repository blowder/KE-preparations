<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <form method="post" action="<%=request.getContextPath()%>/form-handler">
            <p>Enter your name</p>
            <input type="text" name="username">
            <p><input type="submit"></p>
        </form>
    </body>
</html>