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
                                <h2 class="course_name">${course.coursename}</h2>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <table id="all-user-table">
                                    <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Email</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${allusers}">
                                            <tr>
                                                <td class="block">
                                                    <a href="${contextPath}/enroltocourse/${course.id}/${user.id}">
                                                        <label class="icon">
                                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                        </label>
                                                            ${user.fullName}
                                                    </a>
                                                </td>
                                                <td class="block">${user.email}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Right blocks -->
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
                <!-- Right blocks -->
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp"%>