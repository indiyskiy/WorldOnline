<%--
  Created by IntelliJ IDEA.
  User: Servcer
  Date: 29.11.13
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>completer card information</title>
</head>
<body>
Card [${card.cardID}]-${card.cardName}
<br/>
Card was created at ${card.creationTimestamp}
<br/>
Card was changed last time at ${card.lastUpdateTimestamp}
<br/>
Card type is <c:forEach items="${cardTypes}" var="cardType">
    <c:if test="${cardType.value==card.cardType}">
        ${cardType}
    </c:if>
</c:forEach>
<br/>
Card status is <c:forEach items="${cardStates}" var="cardState">
    <c:if test="${cardState.value==card.cardState}">
        ${cardState}
    </c:if>
</c:forEach>
<br/><br/>
<c:choose>
    <c:when test="${! empty cardCoordinate}">
        card coordinate is ${cardCoordinate.longitude} : ${cardCoordinate.latitude}
    </c:when>
    <c:otherwise>
        card coordinate is empty
    </c:otherwise>
</c:choose>
<br/><br/>
Texts
<br/>
<c:forEach items="${cardTexts}" var="cardText">
    Text Group [${cardText.completeTextGroupInfo.textGroup.textGroupID}]${cardText.completeTextGroupInfo.textGroup.textGroupName} (
    <c:forEach items="${textTypes}" var="textType">
        <c:if test="${textType.value==cardText.textCardEntity.cardTextType}">
            ${textType}
        </c:if>
    </c:forEach>
    )
    <br/>
</c:forEach>
<br/>
Card tags
<c:forEach items="${tags}" var="tag">
    Text Group [${tag.completeTagInfo.tagEntity.tagID}]${tag.completeTagInfo.tagEntity.tagName} (
    <c:forEach items="${tagTypes}" var="tagType">
        <c:if test="${tagType.value==tag.completeTagInfo.tagEntity.tagType}">
            ${tagType}
        </c:if>
    </c:forEach>
    )
    <br/>
</c:forEach>
<br/>
</body>
</html>