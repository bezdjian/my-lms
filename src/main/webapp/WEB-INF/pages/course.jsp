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
                    <div class="panel">
                        <div class="panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h2 style="text-align: center">${course.coursename}</h2>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-10">
                                <img src="${contextPath}/resources/uploads/${course.courseimage}" style="max-height: 300px;width: 100%"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-2 col-form-label">Description:</label>
                            <div class="col-10">
                                <label>${course.description}</label>
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