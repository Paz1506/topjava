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
    <th>Id</th>
    <th>DateTime</th>
    <th>Description</th>
    <th>Calories</th>
    <th>Edit</th>
    <th>Delete</th>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.get('mylist')}" var="listMeals">
        <tr style="color: ${listMeals.exceed ? '#d63104' : '#00ab14'}">
            <td>${listMeals.id}</td>
            <td><javatime:parseLocalDateTime value="${listMeals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/><javatime:format value="${parsedDate}" style="MS"/></td>
            <td>${listMeals.description}</td>
            <td>${listMeals.calories}</td>
            <td><a href="meals?id=${listMeals.id}&edit=true">Edit</a></td>
            <td><a href="meals?id=${listMeals.id}&delete=true">Del</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<form action="meals" method="post">
    <p><input type="hidden" name="id_meal" id="id_meal" value="${requestScope.get('edited_id')}"></p>
    <p><input type="text" name="desc_meal" id="desc_meal" value="${requestScope.get('edited_description')}" />Description:</p>
    <p><input type="text" name="cals_meal" id="cals_meal" value="${requestScope.get('edited_calories')}" />Calories:</p>
    <p><input type="date" name="date_meal" id="date_meal" value="<javatime:parseLocalDateTime value="${requestScope.get('edited_dateTime')}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/><javatime:format value="${parsedDate}" style="MS"/>" />Date:</p>
    <c:if test="${requestScope.get('editedMeal')}"><input type="submit" value="Edit!"/></c:if>
    <c:if test="${!requestScope.get('editedMeal')}"><input type="submit" value="Add!"/></c:if>
</form>

</body>
</html>