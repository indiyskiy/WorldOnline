<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Servcer
  Date: 16.01.14
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main page</title>
</head>
<body>
Добро пожаловать в админку Петербурга на ладони!
<br/>
<c:if test="${registered==true}">
    <br/>
    <a href="allcards">Все карточки</a>
    <br/>
    <a href="alltags">Все тэги</a>
    <br/>
    <a href="allmenus">Все категории</a>
    <br/>
    <a href="logout">Вход</a>
    <br/>
    <a href="logout">Выход</a>
</c:if>
<c:if test="${registered==false}">
    <a href="login">Вход</a>
</c:if>
</body>
</html>