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
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <title>Order</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<div class="container">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
                ${error}
        </div>
    </c:if>
    <div class="row">
        <c:forEach items="${orderItems}" var="orderItem">
            <div class="col-md-6">
                <div class="jumbotron">
                    <div class="media">
                        <div class="media-body">
                            <h3 class="media-heading">${orderItem.item}</h3>
                            <div class="row">
                                <div class="col-xs-4">
                                    <h4 class="media-middle"><fmt:message key="price"
                                                                          bundle="${ msg }"/>: ${orderItem.price}</h4>
                                </div>
                                <div class="col-xs-4">
                                    <h4 class="media-middle"><fmt:message key="amount" bundle="${ msg }"/>
                                        : ${orderItem.itemAmount}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
    <c:if test="${ (not empty order) && (empty error)}">
        <h2><fmt:message key="totalprice" bundle="${ msg }"/>: ${totalprice}</h2>
        <div class="row">
            <div class="col-lg-1">
                <form method="post" , action="/confirm">
                    <input type="submit" class="btn btn-info" value=<fmt:message key="confirm"
                                                                                 bundle="${ msg }"/>></input>
                </form>
            </div>
            <div class="col-lg-1">
                <form method="post" , action="/remove">
                    <input type="submit" class="btn btn-danger" value=<fmt:message key="remove"
                                                                                   bundle="${ msg }"/>></input>
                </form>
            </div>
        </div>
    </c:if>

</div>

<script>
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });
</script>
</body>
</html>
