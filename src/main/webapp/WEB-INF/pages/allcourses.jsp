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
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>Available courses</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <c:forEach var="course" items="${course}">

                                    <div class="person-product-container">
                                        <div class="product-image">
                                            <img src="${contextPath}/resources/uploads/${course.courseimage}" style="width: 65px;height: 65px;border-radius: 50%;"/>
                                        </div>
                                        <div class="product-info">
                                            <a href="<c:url value="/view_editcourse/${course.id}/view"/>">
                                                    ${course.coursename}
                                            </a>
                                            <span class="product-date">
                                               <c:if test="${person.role == 'admin'}">
                                                    <a href="<c:url value='/view_editcourse/${course.id}/edit'/>">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                   <a href="<c:url value='/delete/course/${course.id}'/>">
                                                        <i class="fa fa-remove" aria-hidden="true"></i>
                                                    </a>
                                               </c:if>
                                            </span>
                                            <div>
                                               ${course.description}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <p>ROLE: ${person.role}</p>

                            <c:if test="${person.role == 'admin'}">
                                <div class="form-group">
                                    <a href="<c:url value="/view_editcourse/0/edit"/>" class="btn btn-primary">
                                        <i class="fa fa-plus-square">
                                            <label>Add</label>
                                        </i>
                                    </a>
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