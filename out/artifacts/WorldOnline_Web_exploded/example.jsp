<%--<!doctype html>--%>
<%--<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>
</head>

<body>
Hi there. It is AuthorizationServlet page!
<br>
error ${error}
<br>
Size=${size}
<br>
<c:forEach items="${users}" var="user">
    <br>
    user name =${user}
</c:forEach>
</body>
</html>