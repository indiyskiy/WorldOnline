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
    <title>All cards</title>
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
                    <li><a href="createcard">Создать карточку</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    ${pagesString}
    <br/>

    <form action="allcards" method="GET">
        Текст <input type="text" name="CardNameRe"
                     value="${cardNameRe}"/>
        ID карточки <input type="text" name="CardIDRe"
                           value="${cardIDRe}"/>
        Тип карточки <select name="CardTypeRe">
        <option value=""
                <c:if test="${''==cardTypeRe}">
                    selected
                </c:if>
                >Любой
        </option>
        <c:forEach items="${cardTypes}" var="cardType">
            <option value="${cardType.value}"
                    <c:if test="${cardType.value==cardTypeRe}">
                        selected
                    </c:if>
                    >${cardType.text}</option>
        </c:forEach>
    </select>
        <input type="submit" value="Найти"/>
    </form>

    <table border="" width="70%" align="center">
        <tr>
            <td width="3%">
                ID
            </td>
            <td>
                Имя карточки
            </td>
            <td>
                Тип карточки
            </td>
        </tr>
        <c:forEach items="${cardList}" var="card">
            <c:if test="${card.cardState==1}">
                <tr bgcolor="#d6dafc">
            </c:if>
            <c:if test="${card.cardState==2}">
                <tr bgcolor="#ffd3d3">
            </c:if>
            <c:if test="${card.cardState==3}">
                <tr>
            </c:if>
            <td>
                <a href='completecardinfo?cardID=${card.cardID}'> ${card.cardID}</a>
            </td>
            <td>
                    ${card.cardName}
            </td>
            <td>
                <c:forEach items="${cardTypes}" var="cardType">
                    <c:if test="${cardType.value==card.cardType}">
                        ${cardType.text}
                    </c:if>
                </c:forEach>
            </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    ${pagesString}
</div>
</body>
</html>