<%@include file="includes/header.jsp"%>

<div id="container" class="container-fluid">
    <!--div id="header" class="header-panel">
        <div id="header-content" style="border: 1px solid #efefef">
            <div id="logo">
                <img src="<c:url value="/resources/img/logo.jpg"/>" />
            </div>
        </div>
    </div-->

    <div id="page" class="panel">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-9">
                    <div class="panel">
                        <div class="panel-heading">
                            <div id="welcome-panel">
                                <h2>My courses</h2>
                            </div>
                        </div>

                        <div class="container panel-body">
                            <div>
                                <c:forEach var="products" items="${personproducts}">

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
    <%@include file="includes/footer.jsp"%>