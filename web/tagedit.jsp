<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Servcer
  Date: 20.11.13
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit tag ${tag.tagID}-"${tag.tagName}"</title>
</head>
<body>
<form method="POST">
    ID= <input type="text" name="TagID" size="20" value="${tag.tagID}" disabled/>
    <br/>
    Имя тета <input type="text" name="TagName" size="20" value="${tag.tagName}"/>
    <br/>
    Тип тега <select name="TagType">
    <c:forEach var="tagType" items="${TagTypes}">
        <option value="${tagType.value}"
                <c:if test="${tagType.value==tag.tagType}">
                    selected
                </c:if>
                >
            <c:out value="${tagType}"/>
        </option>
    </c:forEach>
    </value>
</select>
    <br/>
    Группа текстов тега [ID${tag.tagTextGroup.textGroupID}]${tag.tagTextGroup.textGroupName} ( <a
        href="texteroupedit?TextGroupID=${tag.tagTextGroup.textGroupID}">Редактировать</a>)
    <br/>
    <input type="submit" value="Submit" name="action"/>
</form>
</body>
</html>