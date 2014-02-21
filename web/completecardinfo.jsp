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
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <SCRIPT LANGUAGE="JavaScript">

        function clickSpoiler(el) {//клик по dl, dt, dd
            var m, k, s;
            s = el.getElementsByTagName("dd")[0].style.display;
            m = document.getElementsByTagName("dl");
            k = m.length;
            while (k--) {
                if (m[k].className == "spoiler") {
                    m[k].getElementsByTagName("dd")[0].style.display = "none";
                }
            }
            ;
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
            }
            ;
        }
        ;
    </SCRIPT>

    <title>complete card information</title>
</head>
<body>
<header>
    <h1>
        Карточка [${card.cardID}]-${card.cardName}
    </h1>
</header>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Общая информация</dt>
    <dd>
        <br/>
        <b>Имя:</b> ${simpleCard.name}
        <br/>
        <b>Адрес:</b> ${simpleCard.address}
        <br/>
        <b>Описание:</b> ${simpleCard.description}
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Дополнительная информация</dt>
    <dd>
        <br/>
        Дата созданая ${card.creationTimestamp}
        <br/>
        Дата последнего изменения ${card.lastUpdateTimestamp}
        <br/>
        Тип
        <c:forEach items="${cardTypes}" var="cardType">
            <c:if test="${cardType.value==card.cardType}">
                &quot;${cardType.text}&quot;
            </c:if>
        </c:forEach>
        <br/>
        Статус:
        <c:forEach items="${cardStates}" var="cardState">
            <c:if test="${cardState.value==card.cardState}">
                ${cardState}.
            </c:if>
        </c:forEach>
        <br/><br/>
        <c:choose>
            <c:when test="${! empty cardCoordinate}">
                Координаты ${cardCoordinate.longitude} : ${cardCoordinate.latitude}.
            </c:when>
            <c:otherwise>
                Координаты не указаны.
            </c:otherwise>
        </c:choose>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Категории</dt>
    <dd>
        <c:forEach var="menu" items="${menus}">
            [${menu.menuEntity.menuID}] ${menu.simpleMenu.menuName}
            <a href="allcards?menu=${menu.menuEntity.menuID}">(все карточки категории)</a>
        </c:forEach>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Тексты</dt>
    <dd>
        <br/>
        <c:forEach items="${cardTexts}" var="cardText">
            Тексты
            <a href='completetextgroupinfo?TextGroupID=${cardText.completeTextGroupInfo.textGroup.textGroupID}'>
                [${cardText.completeTextGroupInfo.textGroup.textGroupID}]
                    ${cardText.completeTextGroupInfo.textGroup.textGroupName}
            </a>
            (
            <c:forEach items="${textTypes}" var="textType">
                <c:if test="${textType.value==cardText.textCardEntity.cardTextType}">
                    ${textType}
                </c:if>
            </c:forEach>
            )
            <br/>
        </c:forEach>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Тэги</dt>
    <dd>
        <br/>
        <c:forEach items="${tags}" var="tag">
            Текст [${tag.completeTagInfo.tagEntity.tagID}]${tag.completeTagInfo.tagEntity.tagName} (
            <c:forEach items="${tagTypes}" var="tagType">
                <c:if test="${tagType.value==tag.completeTagInfo.tagEntity.tagType}">
                    ${tagType}
                </c:if>
            </c:forEach>
            )
            <br/>
        </c:forEach>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Ссылки</dt>
    <dd>
        <br/>
        <c:forEach items="${links}" var="link">
            <a href='completecardinfo?CardID=${link.targetCard.cardID}'>
                [${link.targetCard.cardID}]${link.targetCard.cardName}
            </a>
            (
            <c:forEach items="${cardToCardLinkTypes}" var="cardToCardLinkType">
                <c:if test="${cardToCardLinkType.value==link.cardToCardLinkType}">
                    ${cardToCardLinkType}
                </c:if>
            </c:forEach>
            )
            <br/>
        </c:forEach>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Ссылается на...</dt>
    <dd>
        <br/>
        <c:forEach items="${linkedOn}" var="link">
            <a href='completecardinfo?CardID=${link.sourceCard.cardID}'>
                [${link.sourceCard.cardID}]${link.sourceCard.cardName}
            </a>
            (
            <c:forEach items="${cardToCardLinkTypes}" var="cardToCardLinkType">
                <c:if test="${cardToCardLinkType.value==link.cardToCardLinkType}">
                    ${cardToCardLinkType}
                </c:if>
            </c:forEach>
            )
            <br/>
        </c:forEach>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Картинки</dt>
    <dd>
        <br/>
        <c:forEach items="${images}" var="image">
            [${image.cardImageEntity.image.imageID}]${image.cardImageEntity.cardImageName} (
            <c:forEach items="${imageTypes}" var="imageType">
                <c:if test="${imageType.value==image.cardImageEntity.cardImageType}">
                    ${imageType}
                </c:if>
            </c:forEach>
            )
            <br/>
            <img src='http://148.251.42.117:8080/worldOnline/image/${image.cardImageEntity.image.imageID}'/>
            <br/>
        </c:forEach>
    </dd>
</dl>
</body>
</html>