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
                    <div class="panel">
                        <div class="panel-heading">
                            <div id="welcome-panel">
                                <h4>Report</h4>
                            </div>
                        </div>

                        <div>
                            <c:forEach var="report" items="${adminReport}">

                                <div class="person-product-container">
                                    <div class="product-image">
                                        <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                                        ${report.fullname}
                                    </div>
                                    <div class="user-courses-report">
                                        <p>Courses</p>
                                        <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
                                                ${report.coursename}
                                        <div>

                                        </div>
                                    </div>
                                </div>


                            </c:forEach>
                        </div>
                        <c:if test="${person.role == 'admin'}">
                            <div class="form-group">
                                <a href="<c:url value="/view_editcourse/0/edit"/>" class="btn btn-primary">Add</a>
                            </div>
                        </c:if>
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
    <%@include file="footer.jsp"%>