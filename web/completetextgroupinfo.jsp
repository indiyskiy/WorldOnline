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
    <title>Text info</title>
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
    <form action="textgroupedit" method="POST">
        <input type="hidden" value="${textGroup.textGroupID}" name="textGroupID">
        <table border="" width="70%" align="center">
            <tr>
                <td width="5%">
                    ID
                </td>
                <td>
                    Текст
                </td>
                <td width="15%">
                    Язык
                </td>
            </tr>
            <c:forEach items="${textes}" var="text">
                <tr>
                    <td width="5%">
                            ${text.textID}<input type="hidden" value="${text.textID}" name="id${text.textID}">
                    </td>
                    <td>
                        <textarea name="text${text.textID}">${text.text}</textarea>
                    </td>
                    <td width="15%">
                        <c:forEach items="${languages}" var="language">
                            <c:if test="${language.value==text.languageID}">
                                ${language}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Сохранить"/>
    </form>
</div>
</body>
</html>