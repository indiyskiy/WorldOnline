<%--
  Created by IntelliJ IDEA.
  User: Servcer
  Date: 13.01.14
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Text info</title>
</head>
<body>
<h1 align="center">Группа текстов [${textGroup.textGroupID}]${textGroup.textGroupName}</h1>
<br/>
<br/>
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
                ${text.textID}
        </td>
        <td>
                ${text.text}
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
</body>
</html>