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
    ${pagesString}
    <br/>

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
                Последний выход в он-лайн
            </td>
            <td>
                Последняя синхронизация
            </td>
            <td>
                Язык
            </td>
        </tr>
        <c:forEach items="${userList}" var="user">
        <tr>
            <td width="3%">
                <a href="completeuserinfo?userID=${user.userID}">${user.userID}</a>
                <c:if test="${not empty user.additionalInformation}">*</c:if>
            </td>
            <td>
                <c:if test="${not empty user.mobilePlatform}">
                    ${user.mobilePlatform}
                </c:if>
            </td>
            <td>
                    ${user.userRegistrationTimestamp}
            </td>
            <td>
                    ${user.lastConnectionTimestamp}
            </td>
            <td>
                    ${user.lastSynchronizeTimestamp}
            </td>
            <td>
                    ${user.userLanguage}
            </td>
        <tr>
            </c:forEach>
    </table>
    <br/>
    ${pagesString}
</div>
</body>
</html>
