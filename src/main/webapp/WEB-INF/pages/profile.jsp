<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                <h4>${person.firstname} ${person.lastname}</h4>
                            </div>
                        </div>

                        <form:form action="${contextPath}/editprofile/${person.id}/preedit" modelAttribute="person" enctype="multipart/form-data">
                            <div class="panel-body">
                                <div class="container-fluid userprofile">
                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Username:</label>
                                            <span>${person.username}</span>
                                    </div>

                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> First name:</label>
                                                <span for="person_fname">${person.firstname}</span>
                                    </div>

                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-user" aria-hidden="true"></i> Last name:</label>
                                                <span for="person_fname">${person.lastname}</span>
                                    </div>

                                    <div class="form-group">
                                        <label><i class="icon-globe fa fa-globe"></i> Country:</label>
                                            <span>${person.country}</span>
                                    </div>

                                    <div class="form-group">
                                            <label class="col-2 col-form-label">
                                                <i class="fa fa-envelope" aria-hidden="true"></i> Email:</label>
                                                <span for="person_email">${person.email}</span>
                                    </div>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">Edit</button>
                                        </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>

                <!-- Right side block -->
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp"%>