<%--
  Created by IntelliJ IDEA.
  User: bezdj
  Date: 19/01/2017
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:if test="${empty person.id}">
    <c:redirect url="/"/>
</c:if>
<!DOCTYPE html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery.cookie.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery-ui.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery.dataTables.js"/> "></script>
    <script src="<c:url value="/resources/js/app.js"/> "></script>
</head>
<body>

<!-- Top page, navbar -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid navbar-inner">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring MVC</a>
            <!--div id="header" class="header-panel">
                <div id="header-content">
                    <div id="logo">
                        <img src="<c:url value="/resources/img/logo.jpg"/>" />
                    </div>
                </div>
            </div-->
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/home"/>">Home</a></li>
            <li><a href="<c:url value="/viewpersoncourses/${person.id}"/>">My Courses</a></li>
            <li><a href="<c:url value="/viewpersonproducts/${person.id}"/>">My Books</a></li>
            <li><a href="<c:url value="/allcourses"/>">Available courses</a></li>
            <li><a href="<c:url value="/allproducts"/>">Book Library</a></li>
        </ul>
        <c:if test="${person.id == null}">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<c:url value="/" /> ">
                        <i class="fa fa-sign-in" aria-hidden="true"></i>
                        Login/Sign Up
                    </a>
                </li>
            </ul>
        </c:if>
        <c:if test="${person.id != null}">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<c:url value="/profile/${person.id}" />" class="user_header_info">
                        <span>${person.firstname} ${person.lastname}, ${person.role}</span>
                    </a>
                    <a href="<c:url value="/" />" class="signout">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                    </a>
                </li>
            </ul>
        </c:if>
    </div>
</nav>