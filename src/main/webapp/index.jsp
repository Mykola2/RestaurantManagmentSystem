<!DOCTYPE html>
<!-- saved from url=(0044)https://getbootstrap.com/examples/jumbotron/ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" var="msg"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/favicon.ico">

    <title>Main Page</title>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css" class="--apng-checked">.fancybox-margin {
        margin-right: 0px;
    }</style>
    <style type="text/css" class="--apng-checked">
        :root .carbonad,
        :root #carbonads-container,
        :root #content > #right > .dose > .dosesingle,
        :root #content > #center > .dose > .dosesingle {
            display: none !important;
        }</style>
</head>
<style>
    <%@include file="/bootstrap.min.css" %>
    <%@include file="/jumbotron.css" %>
</style>
<body>
<jsp:include page="/view/navbar.jsp"/>
<div class="container">
    <h1>Restaurant Managment System</h1>
    <h2><fmt:message key="description"
                     bundle="${ msg }"/></h2>
</div>

<script src="/jquery.min.js.download"></script>
<script src="/bootstrap.min.js.download"></script>

<script aria-hidden="true" type="application/x-lastpass" id="hiddenlpsubmitdiv" style="display: none;"></script>
<script>try {
    (function () {
        for (var lastpass_iter = 0; lastpass_iter < document.forms.length; lastpass_iter++) {
            var lastpass_f = document.forms[lastpass_iter];
            if (typeof(lastpass_f.lpsubmitorig2) == "undefined") {
                lastpass_f.lpsubmitorig2 = lastpass_f.submit;
                if (typeof(lastpass_f.lpsubmitorig2) == 'object') {
                    continue;
                }
                lastpass_f.submit = function () {
                    var form = this;
                    var customEvent = document.createEvent("Event");
                    customEvent.initEvent("lpCustomEvent", true, true);
                    var d = document.getElementById("hiddenlpsubmitdiv");
                    if (d) {
                        for (var i = 0; i < document.forms.length; i++) {
                            if (document.forms[i] == form) {
                                if (typeof(d.innerText) != 'undefined') {
                                    d.innerText = i.toString();
                                } else {
                                    d.textContent = i.toString();
                                }
                            }
                        }
                        d.dispatchEvent(customEvent);
                    }
                    form.lpsubmitorig2();
                }
            }
        }
    })()
} catch (e) {
}</script>
</body>
</html>