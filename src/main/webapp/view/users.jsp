<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicko
  Date: 2/3/2017
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
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
        <c:forEach items="${users}" var="user">
            <div class="col-md-6">
                <div class="jumbotron">
                    <div class="media">
                        <form method="post" action="/setbalance">
                            <div class="media-body">
                                <input type="hidden" name="id" id="id" value="${user.id}">
                                <h2>${user.login}</h2>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <h4 class="media-middle ">Balance : ${user.balance}</h4>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-3">
                                        <input type="number" min="1" value="1" class="form-control" name="balance"
                                               id="balance">
                                    </div>
                                    <div class="col-xs-3">
                                        <p class="">
                                            <button class="btn btn-default"
                                                    type="submit">Set
                                            </button>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </form>
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

</body>
</html>
