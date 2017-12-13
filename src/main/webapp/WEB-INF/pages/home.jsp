<%@include file="header.jsp"%>

<div id="container" class="container-fluid">
    <div id="page" class="panel panel-default">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-9">

                    <div class="panel">
                        <div class="panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>Welcome  ${person.firstname} ${person.lastname}!</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <c:forEach var="course" items="${personCourses}">
                                    <div class="coursebox">
                                        <a href="<c:url value="/view_editcourse/${course.id}/view"/>">
                                                ${course.coursename}
                                        </a>
                                        <div>
                                                ${course.description}
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div style="height: 5px;margin: 1em;"></div>

                            <div class="container-fluid">
                                <h4>Products</h4>
                                <c:forEach var="products" items="${personProducts}">

                                    <div class="person-product-container">
                                        <div class="product-image">
                                            <img src="${contextPath}/resources/uploads/${products.image}" style="width: 65px;height: 65px;border-radius: 50%;"/>
                                        </div>
                                        <div class="product-info">
                                            <a href="<c:url value="/view_editproduct/${products.id}/view"/>">
                                                    ${products.name}
                                            </a>
                                            <span class="product-date">
                                                    ${products.createDate}
                                            </span>
                                            <div>
                                                    ${products.product_description}
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Right side block -->
                <c:if test="${person.role == 'admin'}">
                    <div class="col-sm-3">
                        <div class="block admin-block">
                            <h5>Administration</h5>
                            <p>Reports</p>
                            <div>
                                <label class="icon">
                                    <i class="fa fa-list" aria-hidden="true"></i>
                                </label>
                                <a href="<c:url value="${contextPath}/reports/usercourses"/>">
                                    User Courses reports
                                </a>
                            </div>

                            <div>
                                <label class="icon">
                                    <i class="fa fa-list" aria-hidden="true"></i>
                                </label>
                                <a href="<c:url value="${contextPath}/reports/users"/>">
                                    Users Report
                                </a>
                            </div>

                            <hr>
                            <p>Other stuff</p>
                            <div>
                                <label class="icon">
                                    <i class="fa fa-file-video-o" aria-hidden="true"></i>
                                </label>
                                <a href="<c:url value="${contextPath}/reports/usercourses"/>">
                                    stuff1
                                </a>
                            </div>

                            <div>
                                <label class="icon">
                                    <i class="fa fa-viacoin" aria-hidden="true"></i>
                                </label>
                                <a href="<c:url value="${contextPath}/reports/users"/>">
                                    stuff2
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
</div>
<%@include file="footer.jsp"%>