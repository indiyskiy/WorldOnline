<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>All users</title>
</head>
<body>
<header>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <div class="span8">
                    <span class="brand">${title}</span>
                </div>
                <ul class="nav">
                    <li><a href="index">На главную</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
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
</div>
</body>
</html>
