<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="includes/header.jsp"%>
<div id="container" class="container-fluid">
    <!--div id="header" class="header-panel">
        <div id="header-content" style="border: 1px solid #efefef">
            <div id="logo">
                <img src="<c:url value="/resources/img/logo.jpg"/>" />
            </div>
        </div>
    </div-->

    <div id="page" class="panel panel-default">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>${person.firstName} ${person.lastName}</h4>
                            </div>
                        </div>

                        <form:form action="${contextPath}/editprofile/0/doadd" modelAttribute="adduser">
                            <div class="panel-body">
                                <div class="container-fluid userprofile">
                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Username:</label>
                                        <input class="form-control" type="text" id="username" name="username"/>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-2 col-form-label">
                                            <i class="fa fa-user" aria-hidden="true"></i> Password:</label>
                                        <input class="form-control" type="password" id="password" name="password"/>
                                    </div>


                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> First name:</label>
                                                <input class="form-control" type="text" id="firstName"
                                                       name="firstName"/>
                                    </div>

                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Last name:</label>
                                                <input class="form-control" type="text" id="lastName" name="lastName"/>
                                    </div>

                                    <div class="form-group">
                                        <div>
                                            <input type="radio" value="male" id="male" name="gender" checked/>
                                            <label for="male" class="radio">Male</label>
                                            <input type="radio" value="female" id="female" name="gender"/>
                                            <label for="female" class="radio">Female</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="country"><i class="icon-globe fa fa-globe"></i> Country:</label>
                                            <form:select path="country" cssClass="form-control" id="country" name="country">
                                                <option value="Select" label="Select your country"></option>
                                                <form:options items="${countryNames}" />
                                            </form:select>
                                    </div>

                                    <div class="form-group">
                                            <label for="email" class="col-2 col-form-label">
                                                <i class="fa fa-envelope" aria-hidden="true"></i> Email:</label>
                                                <input class="form-control" id="email" name="email" type="text" />
                                    </div>

                                    <div class="form-group">
                                        <label for="companyName" class="col-2 col-form-label">
                                            <i class="fa fa-paper-plane" aria-hidden="true"></i> Company:</label>
                                        <input class="form-control" id="companyName" name="companyName" type="text" />
                                    </div>


                                    <div class="form-group">
                                        <label for="companyLocation" class="col-2 col-form-label">
                                            <i class="fa fa-location-arrow" aria-hidden="true"></i> Company Location:</label>
                                        <input class="form-control" id="companyLocation" name="companyLocation" type="text" />
                                    </div>


                                    <div class="form-group">
                                        <label for="companyServices" class="col-2 col-form-label">
                                            <i class="fa fa-server" aria-hidden="true"></i> Company Services:</label>
                                        <input class="form-control" id="companyServices" name="companyServices" type="text" />
                                    </div>

                                        <div class="form-group">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>

                <!-- Right side block -->
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>
</div>

    <%@include file="includes/footer.jsp"%>