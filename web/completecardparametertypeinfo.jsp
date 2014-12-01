<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <script language="Javascript" type="text/javascript" src="js/jquery.js"></script>
    <link type="text/css" href="css/anytime.css" rel="Stylesheet"/>
    <script type="text/javascript" src="js/anytime.js"></script>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <link rel="stylesheet" media="screen" href="css/anytime.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <script type="text/javascript" src="js/spoiler.js"></script>
    <title>Complete card parameter type info</title>
</head>
<body>
<header>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <div class="span6">
                    <span class="brand">${title}</span>
                </div>
                <ul class="nav">
                    <li><a href="index">На главную</a></li>
                    <li><a href="allcardparametertypes">Все типы параметров</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container container-lower">
        <dl class="spoiler">
            <dt onclick="clickSpoiler(this);">Общая информация</dt>
            <dd>
                <form method="post" action="editcardparametertype">
                    Имя: ${cardParameterTypeInfo.name}
                    <br/>
                    <a href="completetextgroupinfo?textGroupID=${cardParameterTypeInfo.textGroupID}&cardParameterTypeID=${cardParameterTypeInfo.cardParameterTypeID}">
                        Редактировать группу текстов.
                    </a>
                    <br/>
                    <c:if test="${cardParameterTypeInfo.isTranslatable}">
                        <input type="radio" name="translate" value="true" checked> Переводится
                        <input type="radio" name="translate" value="false"> Не переводится
                    </c:if>
                    <c:if test="${not cardParameterTypeInfo.isTranslatable}">
                        <input type="radio" name="translate" value="true"> Переводится
                        <input type="radio" name="translate" value="false" checked> Не переводится
                    </c:if>
                    <br/>
                    <c:if test="${cardParameterTypeInfo.isMultiply}">
                        <input type="radio" name="multiply" value="true" checked> Несколько.
                        <input type="radio" name="multiply" value="false"> Один
                    </c:if>
                    <c:if test="${not cardParameterTypeInfo.isMultiply}">
                        <input type="radio" name="multiply" value="true"> Несколько.
                        <input type="radio" name="multiply" value="false" checked> Один
                    </c:if>
                    <br/>
                    Позиция: ${cardParameterTypeInfo.position}
                    <br/>
                    Тип данных:
                    <select name="dataType">
                        <c:forEach items="${dataTypes}" var="dataType">
                            <c:if test="${dataType.value==cardParameterTypeInfo.dataType.value}">
                                <option value="${dataType.value}" selected>
                                        ${dataType.ruName}
                                </option>
                            </c:if>
                            <c:if test="${dataType.value!=cardParameterTypeInfo.dataType.value}">
                                <option value="${dataType.value}">
                                        ${dataType.ruName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <br/>

                    Блок:
                    <select name="block">
                        <c:forEach items="${blocks}" var="block">
                            <c:if test="${block.value==cardParameterTypeInfo.block.value}">
                                <option value="${block.value}" selected>
                                        ${block.rusText}
                                </option>
                            </c:if>
                            <c:if test="${block.value!=cardParameterTypeInfo.block.value}">
                                <option value="${block.value}">
                                        ${block.rusText}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <br/>
                    <input type="hidden" name="cardParameterTypeID"
                           value="${cardParameterTypeInfo.cardParameterTypeID}">
                    <input type="submit" value="Сохранить">
                </form>
            </dd>
        </dl>
        <dl class="spoiler">
            <dt onclick="clickSpoiler(this);">Иконка</dt>
            <dd>
                <c:if test="${empty cardParameterTypeInfo.imageID or cardParameterTypeInfo.imageID==0}">
                    <form action="allcardparametertypeicon" method="post" enctype="multipart/form-data">
                        Картинка:<input type="file" name="fileName" accept="image/*">
                        <br/>
                        <input type="hidden" name="cardParameterTypeID"
                               value="${cardParameterTypeInfo.cardParameterTypeID}"/>
                        <input type="submit" value="Загрузить"/>
                    </form>
                </c:if>
                <c:if test="${not empty cardParameterTypeInfo.imageID and cardParameterTypeInfo.imageID!=0}">
                    <form action="allcardparametertypeicon" method="post" enctype="multipart/form-data">
                        Картинка:<input type="file" name="fileName" accept="image/*">
                        <br/>
                        <input type="hidden" name="cardParameterTypeID"
                               value="${cardParameterTypeInfo.cardParameterTypeID}"/>
                        <input type="submit" value="Изменить"/>
                    </form>
                    <img src='image/${cardParameterTypeInfo.imageID}'/>
                </c:if>
            </dd>
        </dl>
    </div>
</header>
</body>
</html>
