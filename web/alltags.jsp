<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>All tags list</title>
</head>

<body>

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
        <td width="15%">

        </td>
    </tr>
    <c:forEach items="${tags}" var="tag">
        <tr>
            <td>
                    ${tag.tagID}
            </td>
            <td>
                    ${tag.tagName}
            </td>
            <td>
                    <%--${tag.tagType} - unknown--%>
            </td>
            <td>
                <a href='tagedit?TagID=${tag.tagID}'>Редактировать</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>