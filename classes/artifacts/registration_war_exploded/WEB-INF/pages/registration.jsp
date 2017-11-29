<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>


    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/> "></script>
    <script src="<c:url value="/resources/js/login.js"/> "></script>
</head>
<body>

<div class="registrationbox">
    <h1 class="header-panel block">${message}</h1>
    <c:if test="${message_err}">
        <span class="alert alert-danger center-block">${message_err}</span>
    </c:if>

    <div class="registration_form">
        <!--c:url var="homeURL" value="home.jsp"/-->

        <!--form:form id="regForm" modelAttribute="user" action="registerProcess" method="post"-->
        <form action="${contextPath}/registerProcess" method="post">
            <hr>

            <div class="accounttype">
                <input type="radio" value="personal" id="radioOne" name="accounttype" checked/>
                <label for="radioOne" class="radio">Personal</label>
                <input type="radio" value="company" id="radioTwo" name="accounttype"/>
                <label for="radioTwo" class="radio">Company</label>
            </div>
            <hr>

            <div class="userinfo">
                <label class="icon" for="username">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </label>
                <input type="text" name="username" id="username" placeholder="User name" required/>

                <label class="icon" for="password">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </label>
                <input type="password" name="password" id="password" placeholder="Password" required/>


                <label class="icon" for="firstname">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </label>
                <input type="text" name="firstname" id="firstname" placeholder="Firstname" required/>

                <label class="icon" for="lastname">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </label>
                <input type="text" name="lastname" id="lastname" placeholder="Lastname" required/>

                <label class="icon" for="email">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                </label>
                <input type="text" name="email" id="email" placeholder="Email" required/>

                <label class="icon" for="countryNames"><i class="icon-globe fa fa-globe"></i></label>
                <form:select path="countryNames" cssClass="countryNames" id="country" name="country">
                    <option value="Select" label="Select your country"></option>
                    <form:options items="${countryNames}" />
                </form:select>

            </div>

            <div class="companyforms">
                <!-- Company -->
                <label class="icon" for="companyname">
                    <i class="fa fa-building-o" aria-hidden="true"></i>
                </label>
                <input type="text" name="companyname" id="companyname" placeholder="Company name" required/>

                <label class="icon" for="companylocation">
                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                </label>
                <input type="text" name="companylocation" id="companylocation" placeholder="Company location" required/>

                <label class="icon" for="companyservices">
                    <i class="fa fa-server" aria-hidden="true"></i>
                </label>
                <input type="text" name="companyservices" id="companyservices" placeholder="Company services" required/>
                <!-- EO: Company -->

                <div class="gender">
                    <input type="radio" value="male" id="male" name="gender" checked/>
                    <label for="male" class="radio">Male</label>
                    <input type="radio" value="female" id="female" name="gender"/>
                    <label for="female" class="radio">Female</label>
                </div>
            </div>


            <input type="submit" class="button" value="Register"/>
        </form>
    </div>

    <hr>

    <div class="login_form">
        <form action="${contextPath}/loginProcess" method="post">
            <div>
                <label class="icon" for="username">
                    <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                </label>
                <input type="text" name="username" id="username_login" placeholder="Username" required/>

                <label class="icon" for="password">
                    <i class="fa fa-key" aria-hidden="true"></i>
                </label>
                <input type="password" name="password" id="password_login" placeholder="Password" required/>
            </div>
            <input type="submit" class="button" value="Login"/>
        </form>
    </div>



    <div style="float: left;padding: 10px;min-height: 1em;width: 96%">
        <a href="#" id="ajax" onclick="onClickAjax('${contextPath}');">AJAX</a>
        <div id="showdiv">

        </div>
    </div>
</div>

</body>
</html>


