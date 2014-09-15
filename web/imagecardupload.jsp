<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>загрузка картинки для карточки [${cardID}]${cardName}</title>
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
                    <li><a href="completecardinfo?cardID=${cardID}">Карточка</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<c:if test="${isLoaded}">
    <br/>
    Файл загружен
</c:if>
<br/>

<div class="container container-lower">
    <form action="imagecardupload" method="post" enctype="multipart/form-data">
        Картинка:<input type="file" name="fileName" accept="image/*">
        <br>
        Тип картинки<select name="cardImageType">
        <c:forEach items="${cardImageTypes}" var="cardImageTypeItem">
            <option value="${cardImageTypeItem.value}">
                    ${cardImageTypeItem.text}
            </option>
        </c:forEach>
    </select>
        <br/>
        <input type="hidden" name="cardID" value="${cardID}"/>
        <input type="submit" value="Upload"/>
    </form>
</div>
</body>
</html>