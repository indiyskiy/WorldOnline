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
    <title>Registration</title>
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
                    <c:if test="${registered==false}">
                        <li><a href="login.jsp">Вход</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    Пожалуйста,заполните форму для регистрации.
    <form action="registration" method="POST">
        ${exception}
        <br/>
        Login <input type="text" name="login"/>
        <br/>
        Password <input type="password" name="password"/>
        <br/>
        Password again <input type="password" name="password2"/>
        <br/>
        First name <input type="text" name="firstName"/>
        <br/>
        Second name <input type="text" name="secondName"/>
        <br/>
        E-mail <input type="text" name="email"/>
        <br/>
        <input type="submit" value="Enter"/>
    </form>
</div>
</body>
</html>
