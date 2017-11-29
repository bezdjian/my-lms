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
                                <h4>Welcome  ${person.firstname} ${person.lastname}</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <table id="all_courses_table" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Course name</th>
                                        <th>Description</th>
                                        <th>ID Number</th>
                                        <c:if test="${person.role == 'admin'}">
                                            <th>Edit</th>
                                        </c:if>
                                    </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="course" items="${course}">
                                            <td>
                                                <a href="<c:url value="/viewcourse/${course.id}"/>">
                                                    ${course.coursename}</td>
                                                </a>
                                            <td> ${course.description}</td>
                                            <td> ${course.idnumber}</td>
                                            <c:if test="${person.role == 'admin'}">
                                                <td>
                                                    <a href="<c:url value='/view_editcourse/${course.id}/edit'/>">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </c:if>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <c:if test="${person.role == 'admin'}">
                                    <div class="form-group row">
                                        <a href="<c:url value="/view_editcourse/0/edit"/>" class="btn btn-primary">Add</a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

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

                <!--div class="col-sm-2">
                    <div class="block">
                        .col-sm-3 right
                    </div>
                </div-->
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>