<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>User list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Users</h2>
    <!-- <a href="meals?action=create">Add Meal</a> -->
    <hr/>
    <h3>Select active user</h3>
    <form action="/users" method="post">
    <select name="userId">
        <c:forEach items="${users}" var="user1">
            <jsp:useBean id="user1" scope="page" type="ru.javawebinar.topjava.model.User"/>
            <option value="${user1.id}">${user1.id} | ${user1.name}</option>
        </c:forEach>
    </select><button type="submit" value="Set active">Set active</button>
    </form>
    <hr/>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Calories per day</th>
            <th>Enabled</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.caloriesPerDay}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>