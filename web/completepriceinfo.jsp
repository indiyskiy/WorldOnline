<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <title>Price info ${price.priceID}</title>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <SCRIPT LANGUAGE="JavaScript">
        var formID = 0;
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

        function addOneMore(el) {
            formID++;
            el.removeAttribute('onclick');
            var div = document.createElement("div");
            var innerHtml = "";
            innerHtml += "<div class=\"spoilerElement\" onclick=\"addOneMore(this)\">";
            innerHtml += "<div class=\"form\">";
            <c:forEach var="language" items="${languages}">
            innerHtml += "<div class=\"field\">";
            innerHtml += "<label for=\"dishName${language}" + formID + "\">${language.stringValue}</label>";
            innerHtml += "<input name=\"dishName${language}" + formID + "\" value=\"\"  id=\"dishName${language}" + formID + "\"/>";
            innerHtml += "</div>"
            </c:forEach>
            innerHtml += "<div class=\"field\">";
            innerHtml += "<label for=\"dishCoast" + formID + "\"> Цена</label>";
            innerHtml += "<input name=\"dishCoast" + formID + "\" value=\"\" id=\"dishCoast" + formID + "\"/>";
            innerHtml += "</div>";
            innerHtml += "</div>";
            innerHtml += "</div>";
            div.innerHTML = innerHtml;
            document.getElementById('priceEditForm').appendChild(div);
            return false;
        }

        Element.prototype.remove = function () {
            this.parentElement.removeChild(this);
            return false;
        }

        NodeList.prototype.remove = HTMLCollection.prototype.remove = function () {
            for (var i = 0, len = this.length; i < len; i++) {
                if (this[i] && this[i].parentElement) {
                    this[i].parentElement.removeChild(this[i]);
                }
            }
            return false;
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
        <dt onclick="clickSpoiler(this);">Основная информация</dt>
        <dd>
            ID: ${price.priceID}
            <br/>
            Имя прайса: ${price.name}
            <br/>
            <a href="completetextgroupinfo?textGroupID=${price.textGroupID}&priceID=${price.priceID}">
                Изменить группу текстов
            </a>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Прайс</dt>
        <dd>
            <c:forEach var="category" items="${price.simpleCategories}">
                <dl class="spoilerSub">
                    <dt onclick="clickSpoilerSub(this);"> ${category.name}</dt>
                    <dd>
                        <c:forEach var="dish" items="${category.simpleDishes}">
                            <div style="width: 95%; text-align: right" class="spoilerElement">
                                <span style="float: left">  <a href="editdish?dishID=${dish.dishID}">
                                    [${dish.dishID}] ${dish.name} </a>
                                </span>
                                    ${dish.coast} <a href="deletedish?dihsID=${dish.dishID}">(удалить) </a>
                            </div>
                        </c:forEach>
                    </dd>
                </dl>
            </c:forEach>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Добавление блюд</dt>
        <dd>
            <form action="adddishes" method="POST">
                <input type="hidden" name="priceID" value="${price.priceID}">

                <div id="priceEditForm">
                    <div class="spoilerElement" onclick="addOneMore(this)">
                        <div class="form">
                            <c:forEach var="language" items="${languages}">
                                <div class="field">
                                    <label for="dishName${language}0">${language.stringValue}</label>
                                    <input name="dishName${language}0" value=""
                                           id="dishName${language}0"/>
                                </div>
                            </c:forEach>
                            <div class="field">
                                <label for="dishCoast0"> Цена</label>
                                <input name="dishCoast0" value="" id="dishCoast0"/>
                            </div>
                            <br/>
                        </div>
                    </div>
                    <%-->> place to insert html from JS--%>
                </div>
                <div class="form">
                    <div class="field" id="selectForm">
                        <label for="categorySelect">Категория</label>
                        <select name="categorySelect" id="categorySelect">
                            <c:forEach items="${price.simpleCategories}" var="category">
                                <option value="${category.categoryID}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="Сохранить" id="saveButton">
                </div>
            </form>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Карты</dt>
        <dd>
            <c:forEach var="card" items="${price.cards}">
                <a href="completecardinfo?cardID=${card.cardID}">[${card.cardID}]${card.name}</a>
            </c:forEach>
        </dd>
    </dl>
    <dl class="spoiler">
        <dt onclick="clickSpoiler(this);">Добавить scv</dt>
        <dd>
            <form action="addscvtoprice" method="post" enctype="multipart/form-data">
                Файл прайса :<input type="file" name="fileName" accept=".csv">
            </form>
            <input type="hidden" name="priceID" value="${price.priceID}"/>
            <input type="submit" value="Загрузить"/>
        </dd>
    </dl>
</div>
</body>
</html>

