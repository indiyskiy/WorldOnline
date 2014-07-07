<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Card creation</title>
</head>
<body>
Создание карточки
<br/>
<c:if test="${isLoaded}">
    <br/>
    Карта загружена под номером <a href="completecardinfo?CardID=${cardID}"> ${cardID}</a>
</c:if>
<br/>

<form action="createcard" method="post">
    <input type="text" name="cardName" value=""/>
    <br/>
    <select name="cardType">
        <c:forEach items="${cardTypes}" var="cardTypeItem">
            <option value="${cardTypeItem.value}">
                    ${cardTypeItem.text}
            </option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Upload"/>
</form>
</body>
</html>
