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
                                <h4>Welcome  ${person.firstname} ${person.lastname}!</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Course name</th>
                                        <th>Description</th>
                                        <th>ID Number</th>
                                        <th>Category ID</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="course" items="${personcourses}">
                                        <tr>
                                            <td>
                                                <a href="<c:url value="/viewcourse/${course.id}"/>">
                                                    ${course.coursename}</td>
                                                </a>
                                            <td> ${course.description}</td>
                                            <td> ${course.idnumber}</td>
                                            <td> ${course.categoryid}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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

                    <div class="block company_info_block">
                        <h5>Right block</h5>
                        <div>
                            <label class="label label-success">
                                Something would go nice here...
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>