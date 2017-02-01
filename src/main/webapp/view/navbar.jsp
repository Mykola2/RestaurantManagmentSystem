
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>

    <title>Title</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<c:set var="name" value="id"/>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Restaurant</a>
        </div>

        <ul class="nav navbar-nav">
            <li class="active"><a href="/"></span><fmt:message key="home" bundle="${ msg }"/></a></li>
            <c:if test="${sessionScope.role == 'User'}">
                <li><a href="/menu"></span><fmt:message key="menu" bundle="${ msg }"/></a></li>
                <li><a href="/order"></span><fmt:message key="order" bundle="${ msg }"/></a></li>
                <li><a href="/closed"></span><fmt:message key="closed" bundle="${ msg }"/></a></li>
            </c:if>
            <c:if test="${sessionScope.role == 'Admin'}">
                <li><a href="/orders"></span><fmt:message key="orders" bundle="${ msg }"/></a></li>
                <li><a href="/old"></span><fmt:message key="old" bundle="${ msg }"/></a></li>
            </c:if>
            <li><a href="/enlang">EN</a></li>
            <li><a href="/ualang">UA</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${empty sessionScope.id}">
                    <li><a href="/view/signup.jsp"><span class="glyphicon glyphicon-user"></span><fmt:message key="signup" bundle="${ msg }"/></a></li>
                    <li><a href="/view/signin.jsp"><span class="glyphicon glyphicon-log-in"></span><fmt:message key="login" bundle="${ msg }"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a></span><fmt:message key="hello" bundle="${ msg }"/> ${sessionScope.login}!</a></li>
                    <c:if test="${sessionScope.role == 'User'}">
                        <li><a></span><fmt:message key="balance" bundle="${ msg }"/> ${sessionScope.balance}</a></li>
                    </c:if>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span></span><fmt:message key="logout" bundle="${ msg }"/></a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</nav>
</body>
</html>
