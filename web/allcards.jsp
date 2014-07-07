<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>All cards</title>
</head>
<body>
<a href="index">Главная страница.</a> <a href="createcard">Создать карточку.</a>
<br/>
Страница <c:forEach var="i" begin="0" end="${pages}">
    <c:choose>
        <c:when test="${i==0 && (''.equals(page) || empty  page )}">
            <b> ${i} </b>
        </c:when>
        <c:when test="${i==page}">
            <b> ${i} </b>
        </c:when>
        <c:otherwise>
            <a href="allcards?Page=${i}&CardNameRe=${cardNameRe}&CardIDRe=${cardIDRe}&CardTypeRe=${cardTypeRe}">
                    ${i}
            </a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<br/>

<form action="allcards" method="GET">
    Текст <input type="text" name="CardNameRe"
                 value="${cardNameRe}"/>
    ID карточки <input type="text" name="CardIDRe"
                       value="${cardIDRe}"/>
    Тип карточки <select name="CardTypeRe">
    <option value=""
            <c:if test="${''==cardTypeRe}">
                selected
            </c:if>
            >Любой
    </option>
    <c:forEach items="${cardTypes}" var="cardType">
        <option value="${cardType.value}"
                <c:if test="${cardType.value==cardTypeRe}">
                    selected
                </c:if>
                >${cardType.text}</option>
    </c:forEach>
</select>
    <input type="submit" value="Найти"/>
</form>

<table border="" width="70%" align="center">
    <tr>
        <td width="3%">
            ID
        </td>
        <td>
            Имя карточки
        </td>
        <td>
            Тип карточки
        </td>
    </tr>
    <c:forEach items="${cardList}" var="card">
        <tr>
            <td>
                <a href='completecardinfo?CardID=${card.cardID}'> ${card.cardID}</a>
            </td>
            <td>
                    ${card.cardName}
            </td>
            <td>
                <c:forEach items="${cardTypes}" var="cardType">
                    <c:if test="${cardType.value==card.cardType}">
                        ${cardType.text}
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
Страница <c:forEach var="i" begin="0" end="${pages}">
    <c:choose>
        <c:when test="${i==0 && (''.equals(page) || empty  page )}">
            <b> ${i} </b>
        </c:when>
        <c:when test="${i==page}">
            <b> ${i} </b>
        </c:when>
        <c:otherwise>
            <a href="allcards?Page=${i}&CardNameRe=${cardNameRe}&CardIDRe=${cardIDRe}&CardTypeRe=${cardTypeRe}">
                    ${i}
            </a>
        </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>