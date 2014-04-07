<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>
<body>
Please log in
<br/>

<form action="registration" method="POST">
    ${exception}
    <br/>
    Login <input type="text" name="login"/>
    <br/>
    Password <input type="password" name="password"/>
    <br/>
    Password again <input type="password" name="password2"/>
    <br/>
    First name <input type="text" name="firstName"/>
    <br/>
    Second name <input type="text" name="secondName"/>
    <br/>
    E-mail <input type="text" name="email"/>
    <br/>
    <input type="submit" value="Enter"/>
</form>

</body>
</html>
