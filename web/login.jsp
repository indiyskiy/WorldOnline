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
    <title>Please log in</title>
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
                    <li><a href="registration">Регистрация</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    Please log in
    <br/>

    <form action="login" method="POST">
        ${exception}
        <br/>
        Login <input type="text" name="firstField"/>
        <br/>
        Password <input type="password" name="secondField"/>
        <br/>
        <input type="submit" value="Enter"/>
    </form>
</div>
</body>
</html>
