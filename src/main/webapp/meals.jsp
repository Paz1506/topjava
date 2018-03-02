<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL for HW01 -->
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table>
    <thead>
    <th>DateTime</th>
    <th>Description</th>
    <th>Calories</th>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.get('mylist')}" var="listMeals">
        <tr style="background-color: ${listMeals.exceed ? '#d63104' : '#00ab14'}">
            <td><javatime:parseLocalDateTime value="${listMeals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/><javatime:format value="${parsedDate}" style="MS"/></td>
            <td>${listMeals.description}</td>
            <td>${listMeals.calories}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>