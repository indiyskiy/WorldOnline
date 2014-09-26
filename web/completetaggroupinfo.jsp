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
    <title>Menu info</title>
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
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
                    <li><a href="alltaggroups">Группы тэгов</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Основная информация</dt>
        <dd>
            [${tagGroup.tagGroupID}] ${tagGroup.name}
            <br/>
            <a href="completetextgroupinfo?textGroupID=${tagGroup.textGroupID}&tagGroupID=${tagGroup.tagGroupID}">
                Переводы названия группы тегов
            </a>
            <br/>

            <form method="post" action="taggroupcardedit">
                <c:if test="${not empty tagGroup.tagCard}">
                    Карта тэга(id)
                    <input type="text" name="cardID" value="${tagGroup.tagCard.cardID}"> ${tagGroup.tagCard.name}
                </c:if>
                <c:if test="${empty tagGroup.tagCard}">
                    Добавить карту тега(id)
                    <input type="text" name="cardID" value="">
                </c:if>
                <input type="hidden" value="${tagGroup.tagGroupID}" name="tagGroupID">
                <br/>
                <input type="submit" value="Сохранить">
            </form>
        </dd>
    </dl>

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Тэги</dt>
        <dd>
            <c:forEach items="${tagGroup.simpleTags}" var="tag">
                <a href="tagedit?tagID=${tag.tagID}">
                    [${tag.tagID}] ${tag.name}
                </a> (<a href="deletetag?tagID=${tag.tagID}">удалить</a>)
                <br/>
            </c:forEach>
        </dd>
    </dl>

</div>
</body>
</html>

