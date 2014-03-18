<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Complete user info</title>
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <SCRIPT LANGUAGE="JavaScript">
        function clickSpoiler(el) {//клик по dl, dt, dd
            /*alert(e1.name)*/
            var m, k, s;
            s = el.getElementsByTagName("dd")[0].style.display;
            m = document.getElementsByTagName("dl");
            k = m.length;
            /*  while (k--) {
             if (m[k].className == "spoiler") {
             m[k].getElementsByTagName("dd")[0].style.display = "none";
             }
             }*/
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
            }
        }
    </SCRIPT>
</head>
<body>
Пользователь ID[${user.userID}]
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Общая информация</dt>
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
            <input type="text" name="additionalInformation" size="20"
                   value="${user.userPersonalData.additionalInformation}"/>
            <input type="hidden" value="${user.userID}" name="userID"/>
            <input type="submit" value="Submit" name="action" title="Сохранить"/>
        </form>
    </dd>
</dl>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Техническая информация</dt>
    <dd>
        <%--<br/>--%>
        <%--<b>Модель телефона:</b> ${uset.userHardware.}--%>
        <%--<br/>--%>
        <b>Push message token:</b> ${user.userHardware.deviceTokenKey}
        <br/>
        <b>Уникальный ключ устройства:</b> ${user.userHardware.deviceUniqueKey}
    </dd>
</dl>
<br/>
<%--<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Информация о актуальности данных</dt>
    <dd>
        <b>Последняя синхронизация:</b> ${user.userContent.lastSynchronizeTimestamp}
        <br/>
        <table border="" width="70%" align="center">
            <tr>
                <td width="3%">
                    ID
                </td>
                <td>
                   имя катрочки
                </td>
                <td>
                    Дата регистрации
                </td>
                <td>
                    Последний визит
                </td>
            </tr>
            <c:forEach items="${userCards}" var="userCard">
                <tr>
                    <td width="3%">
                        ID
                    </td>
                    <td>
                        Платформа
                    </td>
                    <td>
                        Дата регистрации
                    </td>
                    <td>
                        Последний визит
                    </td>
                </tr>
            </c:forEach>
        </table>
    </dd>
</dl>--%>
</body>
</html>
