<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 1/27/2017
  Time: 6:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>Your closed orders</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${closedOrders}" var="order">
            <div class="col-md-6">
                <div class="jumbotron">
                    <div class="media">
                        <form method="post" action="/pay">
                        <div class="media-body">
                            <input type="hidden" name="id" id="id" value="${order.id}">
                            <input type="hidden" name="totalprice" id="totalprice" value="${order.totalPrice}">
                            <h3 class="media-heading"><tags:localDate date="${order.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
                            <div class="row">
                                <div class="col-xs-5">
                                    <h4 class="media-middle"><fmt:message key="totalprice" bundle="${ msg }"/>: ${order.totalPrice}</h4>
                                </div>
                            </div>
                            <c:forEach items="${order.orderItems}" var="orderItem" varStatus="loo">
                                <h5>${orderItem.item}</h5>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <h5 class="media-middle"><fmt:message key="price" bundle="${ msg }"/> : ${orderItem.price}</h5>
                                    </div>
                                    <div class="col-xs-4">
                                        <h5 class="media-middle"><fmt:message key="amount" bundle="${ msg }"/> : ${orderItem.itemAmount}</h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                            <input type="submit" class="btn btn-success" style=" position:absolute;bottom:40px;right: 25px"
                                   value=<fmt:message key="pay" bundle="${ msg }"/>>
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
