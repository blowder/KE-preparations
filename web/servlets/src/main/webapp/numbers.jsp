<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    <html>

    <head>
        <title>Numbers</title>
    </head>

    <body>
        <c:forEach items="${pingController.getNumbers()}" var = "item" >
             <c:if test = "${item % 2 == 0}">
                Item <c:out value = "${item}"/><p>
             </c:if>
        </c:forEach>
        <c:import url = "image.jsp"/>
    </body>

    </html>