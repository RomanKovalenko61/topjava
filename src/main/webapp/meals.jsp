<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <jsp:useBean id="list" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
    <jsp:useBean id="formatter" scope="request" type="java.time.format.DateTimeFormatter"/>
    <c:forEach items="${list}" var="item">
        <tr style="color: ${item.excess ? 'red' : 'green'}">
            <td><c:out value="${item.dateTime.format(formatter)}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>