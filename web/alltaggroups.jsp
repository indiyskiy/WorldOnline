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
    <title>All tag groups list</title>
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
                    <li><a href="createnewtaggroup">Создать</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <table border="" width="70%" align="center">
        <tr>
            <td width="3%">
                ID
            </td>
            <td>
                Имя группы тегов
            </td>
            <td>
                Блок
            </td>
            <%--<td width="10%">--%>

            <%--</td>--%>
        </tr>
        <c:forEach items="${tagGroups}" var="tagGroup">
            <tr>
                <td>
                    <a href="completetaggroupinfo?tagGroupID=${tagGroup.tagGroupID}">${tagGroup.tagGroupID}</a>
                </td>
                <td>
                        ${tagGroup.name}
                </td>
                <td>
                        ${tagGroup.block.rusText}
                </td>
                    <%--<td>--%>
                    <%--<a href="updowntaggroup?tagGroup=${tagGroup.tagGroupID}&reposition=DOWN">--%>
                    <%--<img src="images/Down_arrow.png"/>--%>
                    <%--</a>--%>
                    <%--<a href="updowntaggroup?tagGroup=${tagGroup.tagGroupID}&reposition=UP">--%>
                    <%--<img src="images/Up_arrow.png"/>--%>
                    <%--</a>--%>

                    <%--<a href="deletetaggroup?tagGroup=${tagGroup.tagGroupID}">--%>
                    <%--<img src="images/Close_cross.png"/>--%>
                    <%--</a>--%>
                    <%--</td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>