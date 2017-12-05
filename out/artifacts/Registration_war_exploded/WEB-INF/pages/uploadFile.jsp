<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomt" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>

<div id="container" class="container-fluid">
    <div id="page" class="panel panel-default">
        <div id="page-content" class="panel-body">

            <div class="row">
                <div class="col-sm-2">
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

                <div class="col-sm-8">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div id="welcome-panel" class="welcome-panelsss">
                                <h4>Upload Product image</h4>
                            </div>
                        </div>

                        <form:form modelAttribute="uploadItem" name="frm" method="post" enctype="multipart/form-data"
                                   onsubmit="return validate_image();">
                            <table>
                                <tr>
                                    <td>
                                        <fomt:label path="fileData" for="fileData">
                                            File
                                        </fomt:label>
                                    </td>
                                    <td>
                                        <form:input path="fileData" id="imagetoupload" type="file"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="submit" value="Upload"/>
                                    </td>
                                </tr>
                            </table>
                        </form:form>

                    </div>
                </div>

                <!--div class="col-sm-2">
                    <div class="block">
                        .col-sm-3 right
                    </div>
                </div-->
            </div>
        </div>
    </div>

<%@include file="footer.jsp" %>