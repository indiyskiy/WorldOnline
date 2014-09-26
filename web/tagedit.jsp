<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit tag ${tag.tagID}-"${tag.tagName}"</title>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
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
                    <li><a href="alltags">Все тэги</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Основная информация</dt>
        <dd>
            Группа текстов тега ( <a
                href="completetextgroupinfo?textGroupID=${tag.textGroupID}&tagID=${tag.tagID}">Редактировать</a>)
            <br/>
            Группа тегов <a
                href="completetaggroupinfo?tagGroupID=${tag.tagGroupID}">[${tag.tagGroupID}]${tag.tagGroupName}</a>
            <br/>

        </dd>
    </dl>

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Иконка</dt>
        <dd>
            <c:if test="${not empty tag.iconID && tag.iconID!=0}">
                Иконка тэга [${tag.iconID}] <a href="edittagimage?tagID=${tag.tagID}">Изменить</a>
                <br/>
                <img src='image/${tag.iconID}'/>
            </c:if>
            <c:if test="${empty tag.iconID || tag.iconID==0}">
                <a href="edittagimage?tagID=${tag.tagID}">Загрузить иконку тега</a>
            </c:if>
        </dd>
    </dl>


    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Карточки (${fn:length(tag.cards)})</dt>
        <dd>
            <c:forEach items="${tag.cards}" var="card">
                <a href="completecardinfo?cardID=${card.cardID}"> [${card.cardID}]${card.name}</a>
                <br/>
            </c:forEach>
        </dd>
    </dl>

</div>
</body>
</html>