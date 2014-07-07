<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>upload image to card [${cardID}]${cardName}</head>
<body>
upload image to card [${cardID}]${cardName}
<c:if test="${isLoaded}">
    <br/>
    Файл загружен
</c:if>
<br/>

<form action="imagecardupload" method="post" enctype="multipart/form-data">
    Select File to Upload:<input type="file" name="fileName" accept="image/*">
    <br>
    <select name="cardImageType">
        <c:forEach items="${cardImageTypes}" var="cardImageTypeItem">
            <option value="${cardImageTypeItem.value}">
                    ${cardImageTypeItem.text}
            </option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Upload"/>
    <input type="text" name="cardID" value="${cardID}" hidden/>
</form>
</body>
</html>