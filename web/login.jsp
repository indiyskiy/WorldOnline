<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Please log in</title>
</head>
<body>
Please log in
<br/>

<form action="login" method="POST">
    ${exception}
    <br/>
    Login <input type="text" name="firstField"/> <input type="text" name="login" hidden>
    <br/>
    Password <input type="password" name="secondField"/> <input type="password" name="password" hidden>
    <br/>
    <input type="submit" value="Enter"/>
</form>

</body>
</html>
