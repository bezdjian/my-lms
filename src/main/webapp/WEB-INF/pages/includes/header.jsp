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
    <c:redirect url="/" />
</c:if>
<!DOCTYPE html>
<head>
    <title>${mylmstitle}</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/sidebar.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery.cookie.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery-ui.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery.dataTables.js"/> "></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> "></script>
    <script src="<c:url value="/resources/js/app.js"/> "></script>

</head>
<body>

<!-- Top page, navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container navbar-inner">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#lms-navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/home"/>">Spring MVC</a>
            <!--div id="header" class="header-panel">
                <div id="header-content">
                    <div id="logo">
                        <img src="<c:url value="/resources/img/logo.jpg"/>" />
                    </div>
                </div>
            </div-->
        </div>

        <div id="lms-navbar-collapse" class="navbar-collapse collapse">
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
                        <div class="userinfo_container">
                        <c:if test="${not empty person.profileImage}">
                            <img src="${contextPath}/resources/profile_pictures/${person.profileImage}" id="profile-image"/>
                        </c:if>
                        <c:if test="${empty person.profileImage}">
                            <img src="${contextPath}/resources/images/no-user.png" id="profile-image"/>
                        </c:if>

                            <div class="dropdown my-dropdown">
                                <button class="btn btn-primary btn-transparent dropdown-toggle" type="button" data-toggle="dropdown">
                                        ${person.firstname} ${person.lastname}
                                    <span class="caret"></span></button>

                                <ul class="dropdown-menu">
                                    <li><a href="<c:url value="/profile/${person.id}" />">Profile</a></li>
                                    <li><a href="#">Stuff</a></li>
                                    <li><div class="dropdown-divider"></div></li>
                                    <li><a href="<c:url value="/" />">Log out</a></li>
                                </ul>
                            </div>

                        <!--a href="<c:url value="/" />" class="signout">
                            <i class="fa fa-sign-out" aria-hidden="true"></i>
                        </a-->
                        </div>
                    </li>
                </ul>
            </c:if>
        </div>

    </div>
</nav>