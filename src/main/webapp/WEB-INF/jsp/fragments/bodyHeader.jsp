<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message code="app.title"/></a>
        <form:form class="form-inline my-2" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                </sec:authorize>
                <a class="btn btn-info mr-1" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                <button class="btn btn-primary" type="submit">
                    <span class="fa fa-sign-out"></span>
                </button>
            </sec:authorize>
        </form:form>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <spring:message code="locale.menus"/>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <a class="dropdown-item" href="?lang=en">en</a>
                <a class="dropdown-item" href="?lang=ru">ru</a>
            </div>
        </div>
    </div>
</nav>