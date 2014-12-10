<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>main page</title>
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
                    <li><a href="createcard">Создать карточку</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <p>
        <c:if test="${registered==true}">
            <img src="images/welcome/1-h-${imgID}.png" alt="Иллюстрация" align="left"
                 vspace="5" hspace="5"/>
            <br/>
            <a href="allcards">Все карточки</a>
            <br/>
            <a href="alltaggroups">Все группы тегов</a>
            <br/>
            <a href="alltags">Все тэги</a>
            <br/>
            <a href="allmenus">Все категории</a>
            <br/>
            <a href="allcardparametertypes">Все типы параметров</a>
            <br/>
            <a href="allusers">Все пользователи</a>
        </c:if>
        <c:if test="${registered==false}">
            <a href="login">Вход</a>
        </c:if>
    </p>
</div>
</body>
</html>