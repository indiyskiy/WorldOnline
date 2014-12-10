<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>Card Popularity</title>
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
    <%--<form action="cardpopularity" method="GET">--%>
    <%--Тип карточки <select name="state">--%>
    <%--<option value=""--%>
    <%--<c:if test="${''==cardTypeRe}">--%>
    <%--selected--%>
    <%--</c:if>--%>
    <%-->Любой--%>
    <%--</option>--%>
    <%--<c:forEach items="${cardTypes}" var="cardType">--%>
    <%--<option value="${cardType.value}"--%>
    <%--<c:if test="${cardType.value==cardTypeRe}">--%>
    <%--selected--%>
    <%--</c:if>--%>
    <%-->${cardType.text}</option>--%>
    <%--</c:forEach>--%>
    <%--</select>--%>
    <%--<input type="submit" value="Найти"/>--%>
    <%--</form>--%>
    <table border="" width="70%" align="center">
        <tr>
            <td width="3%">
                ID Карточки
            </td>
            <td>
                Название карточки
            </td>
            <td>
                Продолжительность посещения карточки (общая, мин)
            </td>
        </tr>
        <c:forEach items="${cardPopularities}" var="cardEntry">
            <tr>
                <td>
                        ${cardEntry.cardID}
                </td>
                <td>
                        ${cardEntry.cardName}
                </td>
                <td>
                        ${cardEntry.allTime}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>