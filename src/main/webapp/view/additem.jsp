<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 2/3/2017
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<jsp:include page="/view/menu.jsp"/>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
            ${error}
    </div>
</c:if>
<div class="container">
    <form action="/additem" method="post">
    <div class="form-group row">
        <label class="col-2 col-form-label">Name</label>
        <div class="col-10">
            <input class="form-control" type="text" id="name" name="name">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-2 col-form-label">Price</label>
        <div class="col-10">
            <input class="form-control" min="0" type="number" name="price">
        </div>
    </div>
    <div class="form-group row">
        <label  class="col-2 col-form-label">Weight</label>
        <div class="col-10">
            <input class="form-control" min="0" type="number"  id="weight" name="weight">
        </div>
    </div>
        <button class="btn btn-default"
                type="submit">Add
        </button>
    </form>
</div>
</body>
</html>
