<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 1/26/2017
  Time: 5:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<c:set var="name" value="id" />
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Restaurant</a>
        </div>

        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <c:if test="${sessionScope.role == 'User'}">
            <li><a href="/menu">Menu</a></li>
            <li><a href="/order">Order</a></li>
            </c:if>
            <c:if test="${sessionScope.role == 'Admin'}">
                <li><a href="/orders">Orders</a></li>
                <li><a href="/old">Closed Orders</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <c:choose>
                <c:when test="${empty sessionScope.id}">
                    <li><a href="/view/signup.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="/view/signin.jsp"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
                </c:when>
                <c:otherwise>
                    <li><a>Hello ${sessionScope.login}!</a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</nav>
</body>
</html>
