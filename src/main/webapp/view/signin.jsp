<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <title>Admin</title>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
    body, html {
        height: 100%;
        background-repeat: no-repeat;
        background-color: #d3d3d3;
        font-family: 'Oxygen', sans-serif;
    }

    .main {
        margin-top: 70px;
    }

    h1.title {
        font-size: 50px;
        font-family: 'Passion One', cursive;
        font-weight: 400;
    }

    hr {
        width: 10%;
        color: #fff;
    }

    .form-group {
        margin-bottom: 15px;
    }

    label {
        margin-bottom: 15px;
    }

    input,
    input::-webkit-input-placeholder {
        font-size: 11px;
        padding-top: 3px;
    }

    .main-login {
        background-color: #fff;
        /* shadows and rounded borders */
        -moz-border-radius: 2px;
        -webkit-border-radius: 2px;
        border-radius: 2px;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

    }

    .main-center {
        margin-top: 30px;
        margin: 0 auto;
        max-width: 330px;
        padding: 40px 40px;

    }

    .login-button {
        margin-top: 5px;
    }

    .login-register {
        font-size: 11px;
        text-align: center;
    }
</style>
<body>

<jsp:include page="/view/navbar.jsp"/>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/signin" htmlE>
                <div class="form-group">
                    <label for="login" class="cols-sm-2 control-label"><fmt:message key="login"
                                                                                    bundle="${ msg }"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="login" id="login"
                                   placeholder="Enter your login"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label"><fmt:message key="password"
                                                                                       bundle="${ msg }"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock fa-lg"
                                                                   aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Enter your Password"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><fmt:message key="login"
                                                                                                             bundle="${ msg }"/></button>
                </div>
                <div class="login-register">
                    <a href="/view/signup.jsp"><fmt:message key="signup" bundle="${ msg }"/></a>
                </div>
            </form>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                        ${error}
                </div>
            </c:if>
        </div>
    </div>
</div>


<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>