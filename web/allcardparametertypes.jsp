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
    <title>All Card Parameter Types</title>
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
                    <li><a href="createcardparametertype">Создать</a></li>
                    <li><a href="index">На главную</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <table border="" width="70%" align="center">
        <tr>
            <td width="3%">
                ID
            </td>
            <td>
                Имя типа
            </td>
            <td>
                Блок
            </td>
        </tr>
        <c:forEach items="${cardParameterTypes}" var="type">
            <tr>
                <td>
                    <a href='completecardparametertypeinfo?cardParameterTypeID=${type.parameterTypeID}'> ${type.parameterTypeID}</a>
                </td>
                <td>
                        ${type.name}
                </td>
                <td>
                        ${type.block.rusText}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
