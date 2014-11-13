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
    <title>Add card parameter or text</title>
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
                    <li><a href="completecardinfo?cardID="${cardID}>К карточке</a></li>
                    <%--<li><a href="createаfield">Создать поле</a></li>--%>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="container container-lower">
    <c:forEach items="${blocks}" var="block">
        <dl class="spoiler">
            <dt onclick="clickSpoiler(this);">${block.rusText}</dt>
            <dd>
                <c:forEach items="${parameterTypes}" var="parameterType">
                    <c:if test="${parameterType.block.value==block.value}">
                        <a href="addcardparameter?cardParameterType=${parameterType.parameterTypeID}&cardID=${cardID}">${parameterType.name}</a>
                        <br>
                    </c:if>
                </c:forEach>
            </dd>
        </dl>
    </c:forEach>
</div>
</body>
</html>
