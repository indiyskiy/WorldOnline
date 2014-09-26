<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <title>Menu info</title>
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
                    <li><a href="allmenus?menuID=${menu.menuID}">К дереву меню</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Меню-родитель</dt>
        <dd>
            <form action="changeparent" method="post">
                <a href="completemenuinfo?menuID=${menu.parentID}">[${menu.parentID}]- ${menu.parentName}</a>
                <br/>
                <input size="4" type="text" value="${menu.parentID}" name="parentID">
                <input type="hidden" value="${menu.menuID}" name="menuID">
                <input type="submit" value="Изменить">
            </form>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Положение меню</dt>
        <dd>
            Номер в списке меню ${menu.number}
            <%--ближайшие соседи, опустить, поднять--%>
        </dd>
    </dl>

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Тексты</dt>
        <dd>
            <a href="completetextgroupinfo?textGroupID=${menu.textGroupID}&menuID=${menu.menuID}">
                Перевод названия меню
            </a>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Картинки</dt>
        <dd>
            <c:if test="${not empty menu.iconID and menu.iconID!=0}">
                Иконка меню
                <br/>
                <a href="editmenuicon?menuID=${menu.menuID}">Изменить</a>
                <br>
                <img src='image/${menu.iconID}'/>
            </c:if>
            <c:if test="${empty menu.iconID or menu.iconID==0}">
                <a href="editmenuicon?menuID=${menu.menuID}">Добавить иконку меню</a>
            </c:if>
        </dd>
    </dl>

    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Подменю</dt>
        <dd>
            <a href="createsubmenu?menuID=${menu.menuID}">Создать новое подменю</a>
            <%--<a href="deletesubmenu?menuID=${menu.menuID}">Открепить все подменю</a>--%>

            <%--<form action="redirectallsubmenus" method="post">--%>
            <%--<input size="4" type="text" value="${menu.menuID}" name="parentID">--%>
            <%--<input type="hidden" value="${menu.menuID}" name="menuID">--%>
            <%--<input type="submit" value="Изменить">--%>
            <%--</form>--%>

        </dd>
    </dl>


    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Карточки меню</dt>
        <dd>
            <a href="createcard?menuID=${menu.menuID}">Создать новую карточку</a>
            <%--<a href="unlinkallcards?menuID=${menu.menuID}">Открепить все карточки</a>--%>
            <%--<a href="deletesubmenu?menuID=${menu.menuID}">Выключить все карточки</a>--%>

            <%--<form action="redirectallsubmenus" method="post">--%>
            <%--<input size="4" type="text" value="${menu.menuID}" name="parentID">--%>
            <%--<input type="hidden" value="${menu.menuID}" name="menuID">--%>
            <%--<input type="submit" value="Изменить">--%>
            <%--</form>--%>

        </dd>
    </dl>
    <%--<dl class="spoiler">--%>
    <%--<dt onclick="clickSpoiler(this);">Title</dt>--%>
    <%--<dd>--%>

    <%--</dd>--%>
    <%--</dl>--%>
</div>
</body>
</html>

