<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp"%>
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
                                <h4>${product.name}</h4>
                            </div>
                        </div>
                        <form:form action="${contextPath}/editproduct/${product.id}" modelAttribute="product">
                            <div class="panel-body">
                                <div class="container-fluid">
                                    <div class="form-group row">
                                        <c:if test="${not view}">
                                            <label for="product_name" class="col-2 col-form-label">Name:</label>
                                        </c:if>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label>
                                                    ${product.name}
                                                </label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" type="text" value="${product.name}" id="product_name" name="product_name"/>
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <c:if test="${not view}">
                                            <label for="product_image" class="col-2 col-form-label">Image:</label>
                                        </c:if>
                                        <div class="col-10 product-image-view">
                                            <c:if test="${view}">
                                                <img src="/resources/uploads/${product.image}" />
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" type="text" value="${product.image}" id="product_image" name="product_image"/>
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="product_date" class="col-2 col-form-label">Creat/Edit date:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label for="product_date">${product.createDate}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" id="product_date" name="product_date" type="date" value="${product.createDate}" />
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="product_price" class="col-2 col-form-label">Price:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label>${product.price}${product.currency}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <input class="form-control" id="product_price" name="product_price" type="text" value="${product.price}" />
                                                <select id="product_price_currency" name="product_price_currency" class="currency-selector">
                                                    <option value=":-">SEK :-</option>
                                                    <option value="$">USD &dollar;</option>
                                                    <option value="€">EURO &euro;</option>
                                                </select>
                                            </c:if>

                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label for="product_description" class="col-2 col-form-label">Description:</label>
                                        <div class="col-10">
                                            <c:if test="${view}">
                                                <label>${product.product_description}</label>
                                            </c:if>
                                            <c:if test="${not view}">
                                                <textarea class="form-control" id="product_description" name="product_description" cols="100">${product.product_description}</textarea>
                                            </c:if>

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <c:if test="${not view}">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </form:form>
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
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp"%>