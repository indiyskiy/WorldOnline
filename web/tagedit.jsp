<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>edit tag ${tag.tagID}-"${tag.tagName}"</title>
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
                    <li><a href="alltags">Все тэги</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    <form method="POST">
        ID= <input type="text" name="tagID" size="20" value="${tag.tagID}" disabled/>
        <br/>
        Имя тета <input type="text" name="TagName" size="20" value="${tag.tagName}"/>
        <br/>
        <%--  Тип тега <select name="TagType">
          <c:forEach var="tagType" items="${TagTypes}">
              <option value="${tagType.value}"
                      <c:if test="${tagType.value==tag.tagType}">
                          selected
                      </c:if>
                      >
                  <c:out value="${tagType}"/>
              </option>
          </c:forEach>
         --%>
        </select>
        <br/>
        Группа текстов тега [ID${tag.tagTextGroup.textGroupID}]${tag.tagTextGroup.textGroupName} ( <a
            href="completetextgroupinfo?TextGroupID=${tag.tagTextGroup.textGroupID}">Редактировать</a>)
        <br/>
        <input type="submit" value="Submit" name="action"/>
    </form>
</div>
</body>
</html>