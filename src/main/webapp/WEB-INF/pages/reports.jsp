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
                    <div class="panel">
                        <div class="panel-heading">
                            <div id="welcome-panel">
                                <h4>Upload Users CSV</h4>
                            </div>
                        </div>

                        <div>
                            <c:if test="${userCoursesReport != null}">
                                <div>
                                <c:forEach var="report" items="${userCoursesReport}">
                                    <div class="person-product-container">
                                        <div class="product-image">
                                            <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                                            <a href="<c:url value="/profile/${report.personId}" />"
                                               class="report-user-name">
                                                <span>${report.fullname}, ${report.role}</span>
                                            </a>
                                        </div>
                                        <div class="user-courses-report">
                                            <div class="report-course-name">
                                                Course: ${report.coursename}
                                            </div>
                                            <div class="report-course-name">
                                                Enrolled: ${report.enrolldate}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                                    <div class="form-group">
                                        <a href="<c:url value="/downloadCSV/usercourses"/>" class="btn btn-primary">Download as Excel (CSV)</a>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${userReport != null}">
                                <div>
                                <c:forEach var="report" items="${userReport}">
                                    <div class="person-product-container">
                                        <div class="product-image">
                                            <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                                            <a href="<c:url value="/profile/${report.id}" />" class="report-user-name">
                                                <span>${report.firstName} ${report.lastName}, ${report.role}</span>
                                            </a>
                                        </div>
                                        <div class="user-courses-report">
                                            <div class="report-course-name">
                                                Email: ${report.email}
                                            </div>
                                            <div class="report-course-name">
                                                Country: ${report.country}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                                    <div class="form-group">
                                        <a href="<c:url value="/downloadCSV/users"/>" class="btn btn-primary">Download as Excel (CSV)</a>
                                    </div>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>

                <!-- Right side block -->
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp"%>