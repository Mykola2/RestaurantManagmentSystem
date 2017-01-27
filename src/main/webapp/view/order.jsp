<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 1/27/2017
  Time: 6:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <div class="row">
        <c:forEach items="${orderItems}" var="orderItem">
            <div class="col-md-6">
                <div class="jumbotron">
                    <div class="media">
                        <div class="media-body">
                            <h3 class="media-heading">${orderItem.item}</h3>
                            <div class="row">
                                <div class="col-xs-4">
                                    <h4 class="media-middle">Price : ${orderItem.price}</h4>
                                </div>
                                <div class="col-xs-4">
                                    <h4 class="media-middle">Amount : ${orderItem.itemAmount}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <form method="post", action="/confirm">
            <input type="submit" class="btn btn-info" value="Confirm"></input>
        </form>
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
