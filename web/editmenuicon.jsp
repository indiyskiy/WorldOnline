<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>загрузка иконки меню [${menuID}]</title>
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
                    <li><a href="completemenuinfo?menuID=${menuID}">Меню</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="container container-lower">
    <form action="editmenuicon" method="post" enctype="multipart/form-data">
        Картинка:<input type="file" name="fileName" accept="image/*">
        <br/>
        <input type="hidden" name="menuID" value="${menuID}"/>
        <input type="submit" value="Загрузить"/>
    </form>
</div>
</body>
</html>