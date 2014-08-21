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
//            m = document.getElementsByTagName("dl");
//            k = m.length;
            //
            /* while (k--) {
             if (m[k].className == "spoiler") {
             m[k].getElementsByTagName("dd")[0].style.display = "none";
             }
             }*/
            //
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
            }
            /* else {
             el.getElementsByTagName("dd")[0].style.display = "none";
             }*/

        }
    </SCRIPT>
    <title>complete card information</title>
</head>
<body>
<header>
    <h1>
        Карточка [${cardInfo.cardID}]-${cardInfo.name}
    </h1>
</header>
<br/>
<%--общее инфо--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Общая информация</dt>
    <dd>
        <%--Имя ${cardInfo.name}--%>
        <%--<br>--%>
        Состояние ${cardInfo.cardState.russianValue}
        <br>
        Тип карточки ${cardInfo.cardType.text}
        <br>
        Дата создания ${cardInfo.creationTime}
        <br>
        Последнее изменение ${cardInfo.updateTime}
    </dd>
</dl>
<%--Координаты--%>
<c:if test="${not empty coordinate}">
    <dl class="spoiler" onclick="clickSpoiler(this);">
        <dt>Координаты</dt>
        <dd>
                ${coordinate.latitude}:${coordinate.longitude}
            <br>
        </dd>
    </dl>
</c:if>
<c:if test="${empty coordinate}">
    Добавить координаты
</c:if>
<%--Прайс--%>
<c:if test="${not empty price}">
    <dl class="spoiler" onclick="clickSpoiler(this);">
        <dt>Прайс</dt>
        <dd>
                ${price.priceID}
            <br>
        </dd>
    </dl>
</c:if>
<c:if test="${empty price}">
    <dl class="spoiler" onclick="clickSpoiler(this);">
        <dt>Прайс</dt>
        <dd>
            Добавить прайс
            <br>
        </dd>
    </dl>
</c:if>
<%--target link--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Ссылается на</dt>
    <dd>
        <c:if test="${not empty targetCardLinks}">
            <c:forEach var="linkedCard" items="${targetCardLinks}">
                <a href="completecardinfo?CardID=${linkedCard.cardID}">[${linkedCard.cardID}]${linkedCard.cardName}</a> - ${linkedCard.linkType.russianValue}
                <br>
            </c:forEach>
        </c:if>
        <c:if test="${empty targetCardLinks}">
            Добавить ссылку
        </c:if>
    </dd>
</dl>
<%--source links--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Имеет ссылки с</dt>
    <dd>
        <c:if test="${not empty sourceCardLinks}">
            <c:forEach var="linkedCard" items="${sourceCardLinks}">
                <a href="completecardinfo?CardID=${linkedCard.cardID}">[${linkedCard.cardID}]${linkedCard.cardName}</a> - ${linkedCard.linkType.russianValue}
                <br>
            </c:forEach>
        </c:if>
        <c:if test="${empty sourceCardLinks}">
            Добавить ссылку
        </c:if>
    </dd>
</dl>

<%--menus--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Категории</dt>
    <dd>
        <c:if test="${not empty menus}">
            <c:forEach var="menu" items="${menus}">
                <a href="completemenuinfo?MenuID=${menu.menuID}">[${menu.menuID}]${menu.name}</a>
                <br>
            </c:forEach>
        </c:if>
        <c:if test="${empty menus}">
            Добавить категорию
        </c:if>
    </dd>
</dl>

<%--images--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Картинки</dt>
    <dd>
        <c:if test="${not empty images}">
            <c:forEach var="image" items="${images}">
                ${image.imageName}(${image.imageType.text})
                <br>
                <img src='http://148.251.42.117:8090/worldOnline/image/${image.imageID}'/>
                <br>
                <br>
            </c:forEach>
        </c:if>
        <c:if test="${empty images}">
            Добавить картинку
        </c:if>
    </dd>
</dl>

<%--Parameters and texts--%>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Параметры</dt>
    <dd>
        <c:forEach var="block" items="${parameterBlocks}">
            <dl class="spoiler" onclick="clickSpoiler(this);">
                <dt>${block.rusText}</dt>
                <dd>
                    <c:forEach items="${parameters}" var="parameter">
                        <c:if test="${block.value==parameter.block}">
                            ${parameter.name} - ${parameter.value}
                            <br>
                        </c:if>
                    </c:forEach>
                    <br>
                </dd>
            </dl>
        </c:forEach>
    </dd>
</dl>
</body>
</html>