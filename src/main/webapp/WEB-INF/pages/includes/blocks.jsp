<!-- Right side block -->
<c:if test="${person.role == 'admin'}">

    <c:if test="${course != null}">
        <div class="col-sm-3">
            <div class="block course_admin_block">
                <h5>Course Administration</h5>

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
        <div class="block admin-block">
            <h5>Administration</h5>
            <div>
                <label class="icon">
                    <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                </label>
                <a href="<c:url value="/allusers"/>">
                    Users
                </a>
            </div>

            <div>
                <label class="icon">
                    <i class="fa fa-upload" aria-hidden="true"></i>
                </label>
                <a href="<c:url value="/uploadUsersForm"/>">
                    Upload users
                </a>
            </div>

            <hr>

            <p>Reports</p>
            <div>
                <label class="icon">
                    <i class="fa fa-list" aria-hidden="true"></i>
                </label>
                <a href="<c:url value="/reports/usercourses"/>">
                    User Courses reports
                </a>
            </div>

            <div>
                <label class="icon">
                    <i class="fa fa-list" aria-hidden="true"></i>
                </label>
                <a href="<c:url value="/reports/users"/>">
                    Users Report
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