<%@include file="includes/header.jsp"%>

<div id="dialog" title="Confirmation Required">
    Are you sure?
</div>
<div id="dialog-congrats" title="Successfull">
    <h4>Congratulations!</h4><br>
    You have ordered your products.
</div>

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
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading allproducts-heading">
                            <div id="welcome-panel" style="float: left;">
                                <h4>Book library</h4>
                            </div>

                            <div id="cart-container">
                                <div id="cart">
                                    <i class="fa fa-shopping-cart fa-2x" aria-hidden="true" style="vertical-align: bottom">
                                        <div class="shopping-cart-box">
                                            <div class="basket-summary-text">Shopping cart</div>
                                            <div class="shopped-items">
                                               <!-- Loaded here from app.js -->
                                            </div>
                                            <div class="shopped-items-footer">
                                                <div class='summaryShipping'>
                                                    <div class='itemTotalName'>Total:</div>
                                                    <div class='itemTotalPrice'>0</div>
                                                </div>
                                            </div>
                                            <div>
                                                <input type="hidden" id="contextPath" value="${contextPath}"/>
                                                <input type="hidden" id="personid" value="${person.id}"/>
                                                <button class="checkout btn btn-primary">Check out</button>
                                                <button class="empty_cart btn btn-default">Empty Cart</button>
                                            </div>
                                        </div>
                                    </i>
                                </div>
                                <span id="itemCount"></span>
                            </div>
                            
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">


                                <div class="content">
                                    <!-- content here -->
                                    <div class="product-grid product-grid--flexbox">
                                        <div class="product-grid__wrapper">
                                            <!-- Product list start here -->

                                            <!-- Single product -->
                                            <c:forEach var="product" items="${products}">
                                                <div class="product-grid__product-wrapper">
                                                    <div class="product-grid__product">
                                                        <div class="product-grid__img-wrapper">
                                                            <img id="image_${product.id}" class="product-grid__img" src="${contextPath}/resources/uploads/${product.image}" />
                                                        </div>
                                                        <span class="product-grid__title">${product.name}</span>
                                                        <span class="product-grid__price">${product.price}${product.currency}</span>
                                                        <div class="product-grid__extend-wrapper">
                                                            <div class="product-grid__extend">
                                                                <p class="product-grid__description">
                                                                    ${product.product_description}
                                                                </p>
                                                                <div class="add_to_cart-div">
                                                                    <span class="product-grid__btn product-grid__add-to-cart addToCart"
                                                                     onclick="AddToCart('${product.id}', '${product.name}', '${product.price}', '${product.currency}')">
                                                                        <i class="fa fa-cart-arrow-down"></i>
                                                                        Add to cart
                                                                    </span>
                                                                    <span class="product-grid__btn product-grid__view">
                                                                        <a href="<c:url value="/view_editproduct/${product.id}/view"/> ">
                                                                            <i class="fa fa-eye"></i> View more
                                                                        </a>
                                                                    </span>
                                                                    <c:if test="${person.role == 'admin'}">
                                                                    <span class="product-grid__btn product-grid__view">
                                                                        <a href="<c:url value='/view_editproduct/${product.id}/edit'/>">
                                                                            <i class="fa fa-pencil-square-o"></i> Edit
                                                                        </a>
                                                                    </span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <!-- end Single product -->
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${person.role == 'admin'}">
                                    <div class="form-group">
                                        <a href="<c:url value="/view_editproduct/0/edit"/>" class="btn btn-primary">
                                            <i class="fa fa-plus-square">
                                                <label>Add</label>
                                            </i>
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <!--div class="col-sm-3">
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
                </div-->
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp"%>