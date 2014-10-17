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
    <title>complete card information</title>
    <SCRIPT LANGUAGE="JavaScript">
        function clickSpoilerCustom(el, className) {//клик по dl, dt, dd
            el = el.parentNode;
            var m, k, s;
            s = el.getElementsByTagName("dd")[0].style.display;
            m = document.getElementsByTagName("dl");
            k = m.length;

            while (k--) {
                if (m[k].className == className) {//
                    m[k].getElementsByTagName("dd")[0].style.display = "none";
                }
            }
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
//
            }
            else {
                el.getElementsByTagName("dd")[0].style.display = "none";
            }
        }

        function clickSpoilerSub(el) {//клик по dl, dt, dd
            clickSpoilerCustom(el, "spoilerSub")
        }

        function clickSpoiler(el) {
            clickSpoilerCustom(el, "spoiler")
        }
    </SCRIPT>
</head>
<body>
<header>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <div class="span6">
                    <span class="brand">${title}</span>
                </div>
                <ul class="nav">
                    <li><a href="index">На главную</a></li>
                    <li><a href="allcards">Все карточки</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
<%--общее инфо--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Общая информация</dt>
    <dd>
        <form method="post" action="changestatus">
            <%--Имя ${cardInfo.name}--%>
            <%--<br>--%>
            Состояние:
            <select name="state">
                <c:forEach var="state" items="${cardStates}">
                    <option value="${state.value}"
                            <c:if test="${state.value==cardInfo.cardState.value}">
                                selected
                            </c:if>
                            > ${state.russianValue}
                    </option>
                </c:forEach>
            </select>
            <%--${cardInfo.cardState.russianValue}--%>
            <br>
            Тип карточки: ${cardInfo.cardType.text}
            <br>
            Дата создания: ${cardInfo.creationTime}
            <br>
            Последнее изменение: ${cardInfo.updateTime}
            <br>
            <input type="hidden" value="${cardInfo.cardID}" name="cardID">
            <input type="submit" value="Сохранить"/>
        </form>
    </dd>
</dl>
<%--Координаты--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Координаты</dt>
    <dd>
        <c:if test="${not empty coordinate}">
            <form action="editcardcoordinate" method="post">
                <input type="text" name="latitude" value="${coordinate.latitude}">
                :
                <input type="text" name="longitude" value="${coordinate.longitude}">
                <input type="hidden" name="cardID" value="${cardInfo.cardID}">
                    <%--<br/>--%>
                <input type="submit" value="Сохранить"/>
            </form>
        </c:if>
        <c:if test="${empty coordinate}">
            <a href="createcardcoordinate?cardID=${cardInfo.cardID}">Добавить координаты</a>
        </c:if>
    </dd>
</dl>
<%--Прайс--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Прайс</dt>
    <dd>
        <c:if test="${not empty price.priceID}">
            <a href="completepriceinfo?priceID=${price.priceID}"> ${price.priceID} </a> (открепить)
        </c:if>
        <c:if test="${empty price.priceID}">
            <%--<a href="completepriceinfo?priceID=${price.priceID}"> ${price.priceID} </a>--%>
            <a href="addnewprice?cardID=${cardInfo.cardID}">Добавить новое меню</a>
            <br/>
            Добавить существующее меню
        </c:if>
    </dd>
</dl>
<%--target link--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Ссылается на</dt>
    <dd>
        <form method="post" action="addcardtocardlink">
            ID карты <input type="text" name="targetCardID"/>
            <br/>
            Тип связи
            <select name="cardToCardLinkType">
                <c:forEach var="cardToCardLinkType" items="${cardToCardLinkTypes}">
                    <option value="${cardToCardLinkType.value}">${cardToCardLinkType.russianValue}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="hidden" name="sourceCardID" value="${cardInfo.cardID}"/>
            <input type="hidden" name="from" value="target"/>
            <input type="submit" value="Добавить">
        </form>
        <%--<br/>--%>
        <c:forEach var="linkedCard" items="${targetCardLinks}">
            <a href="completecardinfo?cardID=${linkedCard.cardID}">
                [${linkedCard.cardID}]${linkedCard.cardName}
            </a>
            - ${linkedCard.linkType.russianValue}
            (<a href="deletecardtocardlink?cardToCardLinkID=${linkedCard.cardToCardLinkID}&cardID=${cardInfo.cardID}">удалить</a>)
            <br>
        </c:forEach>
    </dd>
</dl>
<%--source links--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Имеет ссылки с</dt>
    <dd>
        <form method="post" action="addcardtocardlink">
            ID карты <input type="text" name="sourceCardID"/>
            <br/>
            Тип связи
            <select name="cardToCardLinkType">
                <c:forEach var="cardToCardLinkType" items="${cardToCardLinkTypes}">
                    <option value="${cardToCardLinkType.value}">${cardToCardLinkType.russianValue}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="hidden" name="targetCardID" value="${cardInfo.cardID}"/>
            <input type="hidden" name="from" value="source"/>
            <input type="submit" value="Добавить">
        </form>
        <c:forEach var="linkedCard" items="${sourceCardLinks}">
            <a href="completecardinfo?cardID=${linkedCard.cardID}">
                [${linkedCard.cardID}]${linkedCard.cardName}
            </a>
            - ${linkedCard.linkType.russianValue}
            (<a href="deletecardtocardlink?cardToCardLinkID=${linkedCard.cardToCardLinkID}&cardID=${cardInfo.cardID}">удалить</a>)
            <br>
        </c:forEach>
    </dd>
</dl>

<%--menus--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Категории</dt>
    <dd>
        <form method="post" action="addcardmenu">
            Категория <select name="menuID">
            <c:forEach var="cardMenu" items="${cardMenus}">
                <option value="${cardMenu.menuID}">${cardMenu.menuName}</option>
            </c:forEach>
        </select>
            <input type="hidden" value="${cardInfo.cardID}" name="cardID">
            <input value="Добавить" type="submit">
        </form>
        <br/>
        <c:forEach var="menu" items="${menus}">
            <a href="completemenuinfo?menuID=${menu.menuID}">[${menu.menuID}]${menu.name}</a>
            (<a href="deletecardmenu?cardMenuID=${menu.cardMenuID}&cardID=${cardInfo.cardID}">удалить</a> )
            <br>
        </c:forEach>
    </dd>
</dl>

<%--images--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Картинки</dt>
    <dd>
        <a href="imagecardupload?cardID=${cardInfo.cardID}">Добавить картинку</a>
        <br/>
        <c:forEach var="image" items="${images}">
            ${image.imageName}(${image.imageType.text})
            <a href="deletecardimage?cardID=${cardInfo.cardID}&cardImageID=${image.cardImageID}">Удалить</a>
            <br>
            <img src='image/${image.imageID}'/>
            <br>
            <br>
        </c:forEach>
    </dd>
</dl>

<%--Parameters and texts 2.0 --%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Параметры</dt>
    <dd>
        <a href="addcardelement?cardID=${cardInfo.cardID}">Добавить</a>
        <br>

        <form method="POST" action="editcardparameters">
            <c:forEach var="block" items="${cardBlocks}">
                <c:if test="${not block.isEmpty}">
                    <dl class="spoilerSub">
                        <dt onclick="clickSpoilerSub(this);">${block.name}</dt>
                        <dd>
                            <c:forEach items="${block.cardParameters}" var="parameter">
                                ${parameter.name} - <input type="text" value="${parameter.value}"
                                                           name="parameter${parameter.parameterID}"/>
                                (<a href="deleteCardParameter?cardParameterID=${parameter.parameterID}&cardID=${cardInfo.cardID}">удалить</a>)
                                <br>
                            </c:forEach>
                            <c:forEach items="${block.cardTexts}" var="text">
                                <a href="completetextgroupinfo?textGroupID=${text.textID}&cardID=${cardInfo.cardID}">[${text.textID}]${text.typeName}</a>
                                (<a href="deletetextcard?textCardID=${text.textCardID}&cardID=${cardInfo.cardID}">удалить</a>)
                                <br>
                            </c:forEach>
                        </dd>
                    </dl>
                </c:if>
            </c:forEach>
            <c:if test="${not empty parameters or not empty texts}">
                <br>
                <input type="hidden" name="cardID" value="${cardInfo.cardID}">
                <input type="submit" value="Сохранить"/>
            </c:if>
        </form>
    </dd>
</dl>

<%--tags--%>
<dl class="spoiler">
    <dt onclick="clickSpoiler(this);">Тэги</dt>
    <dd>
        <a href="addtagtocard?cardID=${cardInfo.cardID}">Добавить</a>
        <br/>
        <c:forEach var="tag" items="${tags}">
            <a href="tagedit?tagID=${tag.tagID}">${tag.tagGroup} - ${tag.tagName}</a>
            (<a href="deletecardtag?cardTagID=${tag.cardTagID}&cardID=${cardInfo.cardID}">удалить</a>)
            <br>
        </c:forEach>
    </dd>
</dl>

<c:if test="${cardType==10}">
    <%--info type 10--%>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Информация</dt>
        <dd>
            <a href="addinfoelementtocard?cardID=${cardInfo.cardID}">Добавить</a>
            <br/>
            <c:forEach var="info" items="${infos}">
                <div class="spoilerElement">
                    <form method="POST" action="changecardinfoimage" name="form${info.informationElementID}"
                          id="form${info.informationElementID}" enctype="multipart/form-data">
                        <a href="completetextgroupinfo?textGroupID=${info.textGroupID}&cardID=${cardInfo.cardID}">
                            Редактировать группу текстов
                        </a>
                        <br/>
                            ${info.text}
                        <br/>
                        <br/>
                        <input type="hidden" value="${info.informationElementID}" name="informationElementID">
                        <c:if test="${not empty info.imageID and info.imageID!=0}">
                            Картинка:<input type="file" name="fileName" accept="image/*">
                            <br>
                            <input type="submit" value="Изменить картинку">
                            <br/>
                            <img src='image/${info.imageID}'/>
                        </c:if>
                        <c:if test="${empty info.imageID or info.imageID==0}">
                            Картинка:<input type="file" name="fileName" accept="image/*">
                            <br>
                            <input type="submit" value="Добавить картинку">
                        </c:if>
                    </form>
                </div>
            </c:forEach>
        </dd>
    </dl>
</c:if>
</div>
</body>
</html>