<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp"%>
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
                                <h4>${person.firstname} ${person.lastname}</h4>
                            </div>
                        </div>

                        <form:form action="${contextPath}/editprofile/${person.id}/doedit" commandName="person">
                            <input type="hidden" name="accounttype" id="accounttype" value="${person.accounttype}"/>
                            <input type="hidden" name="role" id="role" value="${person.role}"/>
                            <div class="panel-body">
                                <div class="container-fluid userprofile">
                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Username:</label>
                                        <input class="form-control" type="text" value="${person.username}" id="person_uname" name="person_uname"/>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-2 col-form-label">
                                            <i class="fa fa-user" aria-hidden="true"></i> Password:</label>
                                        <input class="form-control" type="password" value="${person.password}" id="person_password" name="person_password"/>
                                    </div>


                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> First name:</label>
                                                <input class="form-control" type="text" value="${person.firstname}" id="person_fname" name="person_fname"/>
                                    </div>

                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Last name:</label>
                                                <input class="form-control" type="text" value="${person.lastname}" id="person_lname" name="person_lname"/>
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
                                            <label for="person_email" class="col-2 col-form-label">
                                                <i class="fa fa-envelope" aria-hidden="true"></i> Email:</label>
                                                <input class="form-control" id="person_email" name="person_email" type="text" value="${person.email}" />
                                    </div>

                                    <div class="form-group">
                                        <label for="person_email" class="col-2 col-form-label">
                                            <i class="fa fa-envelope" aria-hidden="true"></i> Company:</label>
                                        <input class="form-control" id="person_company" name="person_company" type="text" value="${person.companyname}" />
                                    </div>


                                    <div class="form-group">
                                        <label for="person_email" class="col-2 col-form-label">
                                            <i class="fa fa-envelope" aria-hidden="true"></i> Company Location:</label>
                                        <input class="form-control" id="person_clocation" name="person_clocation" type="text" value="${person.companylocation}" />
                                    </div>


                                    <div class="form-group">
                                        <label for="person_email" class="col-2 col-form-label">
                                            <i class="fa fa-envelope" aria-hidden="true"></i> Company Services:</label>
                                        <input class="form-control" id="person_cservices" name="person_cservices" type="text" value="${person.companyservices}" />
                                    </div>

                                        <div class="form-group">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>

                <c:if test="${person.role == 'admin'}">
                    <div class="col-sm-3">
                        <div class="block course_admin_block">
                            <h5>Administration</h5>

                            <div>
                                <a href="${contextPath}/view_editcourse/${course.id}/edit">
                                    <label class="icon">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </label>
                                    Edit course
                                </a>
                            </div>

                            <div>
                                <a href="${contextPath}/enroltocourse/${course.id}/enrol">
                                    <label class="icon">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                    </label>
                                    Enrol user
                                </a>
                            </div>

                            <div>
                                <a href="${contextPath}/delete/course/${course.id}">
                                    <label class="icon">
                                        <i class="fa fa-remove" aria-hidden="true"></i>
                                    </label>
                                    Delete course
                                </a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="col-sm-3">
                    <div class="block company_info_block">
                        <h5>Company Information</h5>

                        <div>
                            <label class="icon" for="companyName">
                                <i class="fa fa-building-o" aria-hidden="true"></i>
                            </label>
                            <p id="companyName">Company: ${person.companyname}</p>
                        </div>

                        <div>
                            <label class="icon" for="companyLocation">
                                <i class="fa fa-map-marker" aria-hidden="true"></i>
                            </label>
                            <p id="companyLocation">Location: ${person.companylocation}</p>
                        </div>

                        <div>
                            <label class="icon" for="companyServices">
                                <i class="fa fa-server" aria-hidden="true"></i>
                            </label>
                            <p id="companyServices">Services: ${person.companyservices}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp"%>