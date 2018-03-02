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
        <c:if test="${listMeals.exceed}">
            <tr style="background-color: crimson">
        </c:if>
        <c:if test="${!listMeals.exceed}">
            <tr style="background-color: #00ab14">
        </c:if>
            <td>${listMeals.formatedDate}</td>
            <td>${listMeals.description}</td>
            <td>${listMeals.calories}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>