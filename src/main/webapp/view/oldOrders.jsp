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
    <title>Orders</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${orders}" var="order">
            <div class="col-md-6">
                <div class="jumbotron">
                    <div class="media">
                            <div class="media-body">
                                <input type="hidden" name="id" id="id" value="${order.id}">
                                <h3 class="media-heading">${order.dateCreated}</h3>
                                <div class="row">
                                    <div class="col-xs-5">
                                        <h4 class="media-middle">Total Price : ${order.totalPrice}</h4>
                                    </div>
                                    <div class="col-xs-5">
                                        <h4 class="media-middle">User login : ${order.user}</h4>
                                    </div>
                                </div>
                                <c:forEach items="${order.orderItems}" var="orderItem" varStatus="loo">
                                    <h5>${orderItem.item}</h5>
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <h5 class="media-middle">Price : ${orderItem.price}</h5>
                                        </div>
                                        <div class="col-xs-4">
                                            <h5 class="media-middle">Amount : ${orderItem.itemAmount}</h5>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>
</div>


<script>
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });
</script>
</body>
</html>
