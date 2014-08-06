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
            m = document.getElementsByTagName("dl");
            k = m.length;
            //
            while (k--) {
                if (m[k].className == "spoiler") {
                    m[k].getElementsByTagName("dd")[0].style.display = "none";
                }
            }
            //
            if (s == "none" || s == "") {
                el.getElementsByTagName("dd")[0].style.display = "block";
            }
        }
    </SCRIPT>
    <title>complete card information</title>
</head>
<body>
<header>
    <h1>
        Карточка [${card.cardID}]-${card.cardName}
    </h1>
</header>
<br/>
<dl class="spoiler" onclick="clickSpoiler(this);">
    <dt>Общая информация</dt>
    <dd>
        <br/>
        <b>Имя:</b> ${simpleCard.name}
        <br/>
        <b>Адрес:</b> ${simpleCard.address}
        <br/>
        <b>Описание:</b> ${simpleCard.description}
    </dd>
</dl>
<br/>
</body>
</html>