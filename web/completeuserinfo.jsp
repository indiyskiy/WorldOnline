<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <script type="text/javascript" src="js/spoiler.js"></script>
    <title>Complete user info</title>
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
                    <li><a href="allusers">Все пользователи</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <%--<dl class="spoiler">--%>
    <%--<dt onclick="clickSpoiler(this);">Общая информация</dt>--%>
    <%--<dd>--%>
    <%--<b>Платформа:</b> <c:forEach items="${mobilePlatforms}" var="platform">--%>
    <%--<c:if test="${user.userHardware.mobilePlatform==platform.value}">--%>
    <%--${platform}--%>
    <%--</c:if>--%>
    <%--</c:forEach>--%>
    <%--<br/>--%>
    <%--<b>Язык:</b> <c:forEach items="${languages}" var="language">--%>
    <%--<c:if test="${user.userPersonalData.userLanguage==language.value}">--%>
    <%--${language}--%>
    <%--</c:if>--%>
    <%--</c:forEach>--%>
    <%--<br/>--%>
    <%--<b>Последний визит:</b> ${user.userContent.lastConnectionTimestamp}--%>
    <%--<br/>--%>

    <%--<form method="POST">--%>
    <%--<b>Дополнительная информация:</b>--%>
    <%--<input type="text" name="additionalInformation" size="20" rows="5"--%>
    <%--value="${user.userPersonalData.additionalInformation}"/>--%>
    <%--<input type="hidden" value="${user.userID}" name="userID"/>--%>
    <%--<input type="submit" value="Submit" name="action" title="Сохранить"/>--%>
    <%--</form>--%>
    <%--</dd>--%>
    <%--</dl>--%>
    <%--<br/>--%>
    <%--<dl class="spoiler">--%>
    <%--<dt onclick="clickSpoiler(this);">Техническая информация</dt>--%>
    <%--<dd>--%>
    <%--<b>Push message token:</b> ${user.userHardware.deviceTokenKey}--%>
    <%--<br/>--%>
    <%--<b>Уникальный ключ устройства:</b> ${user.userHardware.deviceUniqueKey}--%>
    <%--</dd>--%>
    <%--</dl>--%>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Информация о пользователе</dt>
        <dd>
            <table border="" width="70%" align="center">
                <tr>
                    <td>ID пользователя</td>
                    <td>${completeUserCardInfo.userID}</td>
                </tr>
                <tr>
                    <td>Device Unique Key</td>
                    <td>${completeUserCardInfo.deviceUniqueKey}</td>
                </tr>
                <tr>
                    <td>Device Token Key</td>
                    <td>${completeUserCardInfo.deviceTokenKey}</td>
                </tr>
                <tr>
                    <td>Платформа</td>
                    <td>${completeUserCardInfo.mobilePlatform}</td>
                </tr>
                <tr>
                    <td>Язык</td>
                    <td>${completeUserCardInfo.languageType}</td>
                </tr>
                <tr>
                    <td>Время регистрации</td>
                    <td>${completeUserCardInfo.userRegistration}</td>
                </tr>
                <tr>
                    <td>Время последнего обновления</td>
                    <td>${completeUserCardInfo.lastSynchronized}</td>
                </tr>
            </table>
            <br/>

            <div class="spoilerElement">
                <form action="completeuserinfo" method="post">
                    Дополнительная информация
                    <br/>
                    <textarea type="text"
                              name="additionalInformation">${completeUserCardInfo.additionalInformation}</textarea>
                    <input type="hidden" name="userID" value="${completeUserCardInfo.userID}"/>
                    <br/>
                    <input type="submit" value="Сохранить"/>
                </form>
            </div>
        </dd>
    </dl>
    <br/>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Карточки пользователя</dt>
        <dd>
            <c:forEach items="${userCardStates}" var="userCardState">
                <dl class="spoilerSub">
                    <dt onclick="clickSpoilerSub(this);">
                            ${userCardState.groupName}
                    </dt>
                    <dd>
                        <table border="" width="70%" align="center">
                            <tr>
                                <td width="3%">
                                    ID
                                </td>
                                <td>
                                    Имя карточки
                                </td>
                            </tr>
                            <c:forEach var="userCard" items="${completeUserCardInfo.simpleUserCards}">
                                <c:if test="${userCard.userCardState.value==userCardState.value}">
                                    <tr>
                                        <td>
                                            <a href="completecardinfo?cardID=${userCard.cardID}">${userCard.cardID}</a>
                                        </td>
                                        <td>
                                                ${userCard.name}
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </dd>
                </dl>
            </c:forEach>
        </dd>
    </dl>
</div>
</body>
</html>
