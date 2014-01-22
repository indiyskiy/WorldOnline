<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>All menus</title>
</head>
<body>
<div style="margin-left:20px">
    <c:if test="${not empty parent}">
        <a href="allmenus?MenuID=${parent.menuID}">
            [${parent.menuID}]${parent.menuName}
        </a>
    </c:if>
    <c:if test="${empty parent}">
        ...
    </c:if>
</div>

<div style="margin-left:40px">
    <c:if test="${not empty menu}">
        [${menu.menuID}]${menu.menuName}
        <br/>
    </c:if>
</div>

<div style="margin-left:60px">
    <c:forEach var="submenu" items="${submenus}">
        <a href="allmenus?MenuID=${submenu.menuID}">
            [${submenu.menuID}]${submenu.menuName}
        </a>
        <br/>
    </c:forEach>
</div>
</body>
</html>
