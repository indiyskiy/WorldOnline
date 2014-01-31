<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Menu info</title>
</head>
<body>
Меню [${menu.menuID}]${textGroup.textGroupName}
</br>
Номер в списке меню ${menu.number}
</br>
<a href="completetextgroupinfo?TextGroupID=${textGroup.textGroupID}">
    Перевод названия группы
</a>
</br>
<a href="allmenus?MenuID=${menu.menuID}">к навигации по меню</a>
</body>
</html>