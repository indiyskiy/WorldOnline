<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>All users</title>
</head>
<body>
Страница <c:forEach var="i" begin="0" end="${pages}">
    <c:choose>
        <c:when test="${i==0 && (''.equals(page) || empty  page )}">
            <b> ${i} </b>
        </c:when>
        <c:when test="${i==page}">
            <b> ${i} </b>
        </c:when>
        <c:otherwise>
            <a href="allusers?Page=${i}">
                    ${i}
            </a>
        </c:otherwise>
    </c:choose>

</c:forEach>
<br/>

<form action="allusers" method="GET">
    поиск
</form>
<table border="" width="70%" align="center">
    <tr>
        <td width="3%">
            ID
        </td>
        <td>
            Платформа
        </td>
        <td>
            Дата регистрации
        </td>
        <td>
            Последний визит
        </td>
    </tr>
    <c:forEach items="${userList}" var="user">
    <tr>
        <td width="3%">
            <a href="completeuserinfo?userID=${user.userID}">${user.userID}</a>
        </td>
        <td>
            <c:if test="${not empty user.userHardware}">
                <c:forEach items="${mobilePlatforms}" var="platform">
                    <c:if test="${user.userHardware.mobilePlatform==platform.value}">
                        ${platform}
                    </c:if>
                </c:forEach>
                <%--${user.userHardware.mobilePlatform}--%>
            </c:if>
        </td>
        <td>
                <%--${user.userContent.registrationTimeStamp}--%>udk
        </td>
        <td>
                ${user.userContent.lastConnectionTimestamp}
        </td>
    <tr>
        </c:forEach>
</table>
</body>
</html>
