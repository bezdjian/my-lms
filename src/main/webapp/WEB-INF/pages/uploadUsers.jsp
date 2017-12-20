<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomt" uri="http://www.springframework.org/tags/form" %>
<%@include file="includes/header.jsp"%>

<div id="container" class="container-fluid">
    <div id="page" class="panel panel-default">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-9">

                    <div class="panel">
                        <div class="panel panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>Upload users CSV</h4>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="container-fluid">
                                <form action="${contextPath}/uploadUsers" id="uploadusers" method="post" enctype="multipart/form-data">
                                    <label for="userCSV">
                                        CSV:
                                    </label>
                                    <input type="file" id="userCSV" name="userCSV" />
                                    <input type="submit" value="Upload" class="btn btn-primary"/>
                                </form>
                                <c:if test="${error_msg != null}">
                                    <p class="alert alert-danger">
                                            ${error_msg}
                                    </p>
                                </c:if>

                                <c:if test="${success_msg != null}">
                                <p class="alert alert-success">
                                    ${success_msg}
                                </p>
                                </c:if>
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