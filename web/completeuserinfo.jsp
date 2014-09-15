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
    <title>Complete user info</title>
    <SCRIPT LANGUAGE="JavaScript">
        function clickSpoiler(el) {//клик по dl, dt, dd
            el = el.parentNode;
            var m, k, s;
            s = el.getElementsByTagName("dd")[0].style.display;
            m = document.getElementsByTagName("dl");
            k = m.length;

            while (k--) {
                if (m[k].className == "spoiler") {
                    m[k].getElementsByTagName("dd")[0].style.display = "none";
                }
            }
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
            }
            else {
                el.getElementsByTagName("dd")[0].style.display = "none";
            }
        }
    </SCRIPT>
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
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Общая информация</dt>
        <dd>
            <b>Платформа:</b> <c:forEach items="${mobilePlatforms}" var="platform">
            <c:if test="${user.userHardware.mobilePlatform==platform.value}">
                ${platform}
            </c:if>
        </c:forEach>
            <br/>
            <b>Язык:</b> <c:forEach items="${languages}" var="language">
            <c:if test="${user.userPersonalData.userLanguage==language.value}">
                ${language}
            </c:if>
        </c:forEach>
            <br/>
            <b>Последний визит:</b> ${user.userContent.lastConnectionTimestamp}
            <br/>

            <form method="POST">
                <b>Дополнительная информация:</b>
                <input type="text" name="additionalInformation" size="20" rows="5"
                       value="${user.userPersonalData.additionalInformation}"/>
                <input type="hidden" value="${user.userID}" name="userID"/>
                <input type="submit" value="Submit" name="action" title="Сохранить"/>
            </form>
        </dd>
    </dl>
    <br/>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Техническая информация</dt>
        <dd>
            <b>Push message token:</b> ${user.userHardware.deviceTokenKey}
            <br/>
            <b>Уникальный ключ устройства:</b> ${user.userHardware.deviceUniqueKey}
        </dd>
    </dl>
    <br/>
</div>
</body>
</html>
