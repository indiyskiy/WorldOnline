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
    <title>Создание карточки</title>
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
                    <li><a href="allcards">Все карточки</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <c:if test="${isLoaded}">
        <br/>
        Карта загружена под номером <a href="completecardinfo?cardID=${cardID}"> ${cardID}</a>
    </c:if>
    <br/>

    <form action="createcard" method="post">
        Имя карты(только для админки)<input type="text" name="cardName" value=""/>
        <br/>
        Тип карты <select name="cardType">
        <c:forEach items="${cardTypes}" var="cardTypeItem">
            <option value="${cardTypeItem.value}">
                    ${cardTypeItem.text}
            </option>
        </c:forEach>
    </select>
        <br/>
        ${hidden}
        <input type="submit" value="Сохранить"/>
    </form>
</div>
</body>
</html>
