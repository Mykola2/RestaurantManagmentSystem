<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 1/26/2017
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <title>Title</title>

</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${menu}" var="menuItem">
        <div class="col-md-6">
            <div class="jumbotron">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object"
                                 src=""
                                 alt="...">
                        </a>
                    </div>
                    <form method="post" action="/addToOrder">
                        <div class="media-body">
                            <input type="hidden" name="id" id="id" value="${menuItem.id}">
                            <input type="hidden" name="price"  value="${menuItem.price}">
                            <h3 class="media-heading">${menuItem.name}</h3>
                            <div class="row">
                                <div class="col-xs-4">
                                    <h4 class="media-middle">Price : ${menuItem.price}</h4>
                                </div>
                                <div class="col-xs-4">
                                    <h4 class="media-middle">Weight : ${menuItem.weight}</h4>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-3">
                                    <input type="number" min="1" value="1" class="form-control" name="quantity"
                                           id="quantity">
                                </div>
                                <div class="col-xs-3">
                                    <p class="">
                                        <button class="btn btn-default"
                                                type="submit">Add
                                        </button>
                                    </p>
                                </div>
                            </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
    </c:forEach>
</div>
</div>

<%--!<c:forEach items="${menu}" var="menuItem">

    <h4 class="list-group-item-heading">${menuItem.name}</h4>
    <p class="list-group-item-text">${menuItem.price}</p>
    <span class="minus">-</span>
    <span class="plus">+</span>

</c:forEach>--%>
<script>
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });
</script>
</body>
</html>
