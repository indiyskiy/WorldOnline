<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <script language="Javascript" type="text/javascript" src="js/jquery.js"></script>
    <link type="text/css" href="css/anytime.css" rel="Stylesheet"/>
    <script type="text/javascript" src="js/anytime.js"></script>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <link rel="stylesheet" media="screen" href="css/anytime.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <script type="text/javascript" src="js/spoiler.js"></script>
    <title>complete card information</title>

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
            Категория
            <select name="menuID" size="">
                <c:forEach var="parentCardMenu" items="${parentCardMenus}">
                    <optgroup label="${parentCardMenu.name}">
                        <c:forEach var="cardMenu" items="${parentCardMenu.cardMenus}">
                            <option value="${cardMenu.menuID}">${cardMenu.menuName}</option>
                        </c:forEach>
                    </optgroup>
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
        <a href="addcardparameternew?cardID=${cardInfo.cardID}">Добавить</a>
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
                <dl class="spoilerSub">
                    <dt onclick="clickSpoilerSub(this);">id-${info.informationElementID}</dt>
                    <dd>
                        <a href="deletecardinfoelement?cardInfoID=${info.informationElementID}">Удалить</a>
                        <br/>

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
                            <%--</div>--%>
                    </dd>
                </dl>
            </c:forEach>
        </dd>
    </dl>
</c:if>

<%--news time--%>
<%--card type 9--%>
<c:if test="${cardType==9}">
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Время проведения</dt>
        <dd>
            <a href="addurgencytime?cardID=${cardInfo.cardID}">Добавить</a>
            <br/>
            <c:forEach var="urgencyTime" items="${urgencyTimes}">
                <c:if test="${not empty urgencyTime.end and not empty urgencyTime.start}">
                    Начало ${urgencyTime.start} - конец ${urgencyTime.end}
                    <br>

                    <div class="spoilerElement">
                        <form method="POST" action="editurgencytime">
                            <input type="hidden" value="${urgencyTime.urgencyTimeID}" name="urgencyTimeID">
                            <input type="hidden" value="${cardInfo.cardID}" name="cardID">
                            Начало события
                            <table width="50%" align="center">
                                <tr>
                                    <td>День</td>
                                    <td> Месяц</td>
                                    <td>Год</td>
                                    <td>Часы</td>
                                    <td>Минуты</td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" width="100" value="${urgencyTime.start.day}"
                                               name="startDay"/>
                                    </td>
                                    <td>
                                        <select name="startMonth"><c:forEach items="${months}" var="month">
                                            <option value="${month.value}"
                                                    <c:if test="${month.value==urgencyTime.start.month.value}"> selected</c:if>
                                                    >
                                                    ${month.name}
                                            </option>
                                        </c:forEach></select>
                                    </td>
                                    <td>
                                        <input type="text" width="100" value="${urgencyTime.start.year}"
                                               name="startYear"/>
                                    </td>
                                    <td>
                                        <input type="text" width="100" value="${urgencyTime.start.hour}"
                                               name="startHour"/>
                                    </td>
                                    <td>
                                        <input type="text" value="${urgencyTime.start.minute}"
                                               name="startMinute"/>
                                    </td>
                                </tr>
                            </table>
                            Конец события
                            <table width="50%" align="center">
                                <tr>
                                    <td>День</td>
                                    <td> Месяц</td>
                                    <td>Год</td>
                                    <td>Часы</td>
                                    <td>Минуты</td>
                                </tr>
                                <tr>
                                    <td><input type="text" width="100" value="${urgencyTime.end.day}" name="endDay"/>
                                    </td>

                                    <td>
                                        <select name="endMonth"><c:forEach items="${months}" var="month">
                                            <option value="${month.value}"
                                                    <c:if test="${month.value==urgencyTime.end.month.value}"> selected</c:if>
                                                    >
                                                    ${month.name}
                                            </option>

                                        </c:forEach></select>
                                    </td>
                                    <td><input type="text" width="100" value="${urgencyTime.end.year}" name="endYear"/>
                                    </td>
                                    <td><input type="text" width="100" value="${urgencyTime.end.hour}" name="endHour"/>
                                    </td>
                                    <td><input type="text" width="100" value="${urgencyTime.end.minute}"
                                               name="endMinute"/></td>
                                </tr>
                            </table>
                            <input type="submit" value="Сохранить">
                        </form>
                    </div>
                </c:if>
            </c:forEach>
        </dd>
    </dl>
</c:if>


<c:if test="${cardType==4}">
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Маршрут</dt>
        <dd>
            <c:if test="${empty routeCard}">
                <a href="addroute?cardID=${cardInfo.cardID}">Добавить</a>
            </c:if>
            <c:if test="${not empty routeCard}">
                Маршрут № ${routeCard.cardRouteID}
                <dl class="spoilerSub">
                    <dt onclick="clickSpoilerSub(this);">Карты маршрута</dt>
                    <dd>
                        <c:forEach var="cardOfRoute" items="${routeCard.elements}">
                            <a href="completecardinfo?cardID=${cardOfRoute.simpleCard.cardID}">
                                [${cardOfRoute.simpleCard.cardID}] ${cardOfRoute.simpleCard.name}
                            </a>
                            <a href="deleteroutecard?routeElementID=${cardOfRoute.routeElementID}">
                                Удалить
                            </a>
                            <br/>
                        </c:forEach>
                        <form method="post" action="addrouteelement">
                            ID карточки <input type="text" value="" name="cardID"/>
                            <input type="hidden" value="${routeCard.cardRouteID}" name="cardRouteID"/>
                            <input type="submit" value="Добавить"/>
                        </form>
                    </dd>
                </dl>
                <dl class="spoilerSub">
                    <dt onclick="clickSpoilerSub(this);">Точки маршрута</dt>
                    <dd>
                        <form action="editroutecoordinates" method="post">
                            <c:forEach var="coordinate" items="${routeCard.routeCoordinates}">
                                [${coordinate.position}]
                                <input type="text" name="lat${coordinate.routeCoordinateID}"
                                       value="${coordinate.latitude}">
                                -
                                <input type="text" name="lon${coordinate.routeCoordinateID}"
                                       value="${coordinate.longitude}">
                                <a href="deleteroutecoordinate?coordinateID=${coordinate.routeCoordinateID}">удалить</a>
                                <a href="updownroutecoordinate?routeCoordinateID=${coordinate.routeCoordinateID}&reposition=DOWN">
                                    <img src="images/Down_arrow.png"/>
                                </a>
                                <a href="updownroutecoordinate?routeCoordinateID=${coordinate.routeCoordinateID}&reposition=UP">
                                    <img src="images/Up_arrow.png"/>
                                </a>
                                <br/>
                            </c:forEach>
                            <input type="submit" value="Сохранить">
                        </form>
                        <form action="addroutecoordinate" method="post">
                            <input type="hidden" name="routeCardID" value="${routeCard.routeCardID}"/>:
                            <input type="text" name="latitude" value="">
                            <br/>
                            <input type="text" name="longitude" value=""/>
                            Номер <input type="hidden" name="position" value="${routeCard.coordinateSize}"/>
                            <input type="hidden" name="routeCardID" value="${routeCard.coordinateSize}"/>
                            <br/>
                            <input type="submit" value="Добавить"/>
                        </form>
                    </dd>
                </dl>
            </c:if>
        </dd>
    </dl>
</c:if>
</div>
</body>
</html>