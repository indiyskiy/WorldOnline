<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <meta charset="utf-8">
    <title>All tags list</title>
</head>

<body>
Hi there! It is "All Tags Page". Here they are:
<br>
<table border="" width="70%" align="center">
    <tr>
        <td width="3%">
            ID
        </td>
        <td>
            Имя тега
        </td>
        <td>
            Тип тега
        </td>
        <td width="15%>

        </td>
    </tr>
<c:if test="${!empty tags_unknown}">
        <c:forEach items="${tags_unknown}" var="tag">
            <tr>
                <td>
                        ${tag.tagID}
                </td>
                <td>
                        ${tag.tagName}
                </td>
                <td>
                        ${tag.tagType} - unknown
                </td>
                <td>
                    <a href="tagedit?TagID=${tag.tagID}">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
</c:if>
<c:if test="${!empty tags_cuisine}">

        <c:forEach items="${tags_cuisine}" var="tag">
            <tr>
                <td>
                        ${tag.tagID}
                </td>
                <td>
                        ${tag.tagName}
                </td>
                <td>
                        ${tag.tagType} - cuisine
                </td>
                <td>
                    <a href="tagedit?TagID=${tag.tagID}">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
</c:if>

<c:if test="${!empty tags_categories}">
            <c:forEach items="${tags_categories}" var="tag">
            <tr>
                <td>
                        ${tag.tagID}
                </td>
                <td>
                        ${tag.tagName}
                </td>
                <td>
                        ${tag.tagType}  - categories
                </td>
                <td>
                    <a href="tagedit?TagID=${tag.tagID}">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
</c:if>
<c:if test="${!empty tags_ribbons}">

        <c:forEach items="${tags_ribbons}" var="tag">
            <tr>
                <td>
                        ${tag.tagID}
                </td>
                <td>
                        ${tag.tagName}
                </td>
                <td>
                        ${tag.tagType} - ribbons
                </td>
                <td>
                    <a href="tagedit?TagID=${tag.tagID}">Редактировать</a>
                </td>
            </tr>
        </c:forEach>
</c:if>
</table>
</body>
</html>