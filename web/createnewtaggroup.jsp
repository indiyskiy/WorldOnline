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
    <title>Создание группы тегов</title>
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
                    <li><a href="alltaggroups">Все группы тегов</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <br/>

    <form action="createnewtaggroup" method="post">
        Блок в приложении <select name="block">
        <c:forEach items="${block}" var="blockElem">
            <option value="${blockElem.value}">
                    ${blockElem.rusText}
            </option>
        </c:forEach>
    </select>
        <br/>
        Название <input type="text" value="" name="nameRu">
        <input type="hidden" value="${tagView.value}" name="tagView">
        <br/>
        <input type="submit" value="Создать"/>
    </form>
</div>
</body>
</html>
