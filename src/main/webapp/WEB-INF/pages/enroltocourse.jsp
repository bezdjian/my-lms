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
                                        <c:forEach var="user" items="${allUnerolledUsers}">
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


                        <hr>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <table id="all-enrolled-user-table">
                                    <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Email</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" items="${allEnrolledUsers}">
                                        <tr>
                                            <td class="block">
                                                <a href="#">
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

                <!-- Right side block -->
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp"%>