<%@include file="includes/header.jsp"%>

<div id="container" class="container-fluid">
    <div id="page" class="panel panel-default">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-9">

                    <div class="panel">
                        <div class="panel panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>Welcome  ${person.firstname} ${person.lastname}!</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <c:forEach var="course" items="${personCourses}">
                                    <div class="coursebox">
                                        <div class="course_image">
                                            <img src="${contextPath}/resources/uploads/${course.courseimage}"/>
                                        </div>
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
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>