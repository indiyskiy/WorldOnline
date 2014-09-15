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
    <title>All menus</title>
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
    <div style="margin-left:20px">
        <c:if test="${not empty parent}">
            <a href="allmenus?menuID=${parent.menuID}">
                [${parent.menuID}]${parent.menuName}
            </a>
        </c:if>
        <c:if test="${empty parent}">
            <a href="allmenus">
                ...
            </a>
        </c:if>
    </div>

    <div style="margin-left:40px">
        <c:if test="${not empty menu}">
            [${menu.menuID}]${menu.menuName}<a href="completemenuinfo?menuID=${menu.menuID}">(подробнее)</a>
            <br/>
        </c:if>
    </div>

    <div style="margin-left:60px">
        <c:forEach var="submenu" items="${submenus}">
            <a href="allmenus?menuID=${submenu.menuID}">
                [${submenu.menuID}]${submenu.menuName}
            </a>
            <br/>
        </c:forEach>
    </div>
</div>
</body>
</html>
