<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>
</head>

<body>
Hi there. It is User page!
<br>
Size=${size}
<br>
<table border="" width="70%" align="center">
    <tr>
        <td>
            user id
        </td>
        <td>
            global Version
        </td>
        <td>
            last Connection Time
        </td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                    ${user.userId}
            </td>
            <td>
                    ${user.userContent.globalVersion.globalVersionID}
            </td>
            <td>
                    ${user.userContent.lastConnectionTimestamp}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>