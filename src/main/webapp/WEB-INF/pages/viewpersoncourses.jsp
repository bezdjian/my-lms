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
                                <h4>My courses</h4>
                            </div>
                        </div>

                        <div>
                            <c:forEach var="course" items="${personcourses}">

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
    <%@include file="includes/footer.jsp"%>