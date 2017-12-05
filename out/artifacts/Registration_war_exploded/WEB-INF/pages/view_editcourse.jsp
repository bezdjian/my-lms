<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>
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
                        <c:if test="${view}">
                            <div class="panel-heading">
                                <div id="welcome-panel" class="welcome-panelsss">
                                    <h4>${course.coursename}</h4>
                                </div>
                            </div>
                        </c:if>
                        <form:form action="${contextPath}/editcourse/${course.id}" modelAttribute="course"
                                   enctype="multipart/form-data">
                            <div class="panel-body">
                                <div class="container-fluid">
                                    <div class="form-group">
                                        <label class="col-2 col-form-label">Name:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label for="course_name">${course.coursename}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" type="text" value="${course.coursename}"
                                                       id="course_name" name="course_name"/>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-2 col-form-label">Image:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <img src="${course.courseimage}"/>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" type="file"
                                                       value="${course.courseimage}"
                                                       src="${course.courseimage}"
                                                       id="course_image" name="course_image"/>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-2 col-form-label">Category:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label for="course_category">${course.categoryid}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" type="text" value="${course.categoryid}"
                                                       id="course_category" name="course_category"/>
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="course_idnumber" class="col-2 col-form-label">ID number:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label for="course_idnumber">${course.idnumber}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" id="course_idnumber" name="course_idnumber"
                                                       type="text" value="${course.idnumber}"/>
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="course_description"
                                               class="col-2 col-form-label">Description:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label>${course.description}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <textarea class="form-control" id="course_description"
                                                          name="course_description"
                                                          cols="100">${course.description}</textarea>
                                            </c:if>

                                        </div>
                                    </div>
                                    <c:if test="${person.role == 'admin'}">
                                        <div class="form-group">
                                            <c:if test="${not view}">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </c:if>
                                        </div>
                                    </c:if>
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

<%@include file="footer.jsp" %>