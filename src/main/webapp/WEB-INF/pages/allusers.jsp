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
                                <h4>Users</h4>
                            </div>
                        </div>

                       <div>
                           <table id="all-user-table" class="table table-striped" width="100%" cellspacing="0">
                               <thead>
                               <tr>
                                   <th>Name</th>
                                   <th>Email</th>
                                   <th>Country</th>
                                   <th>Company</th>
                                   <th>Action</th>
                               </tr>
                               </thead>
                               <tbody>
                               <c:forEach var="user" items="${allusers}">
                                   <tr>
                                       <td>
                                           <a href="<c:url value="/profile/${user.id}" />">
                                               <span>${user.firstname} ${user.lastname}</span>
                                           </a>
                                       </td>
                                       <td>${user.email}</td>
                                       <td>${user.country}</td>
                                       <td>${user.companyname}</td>
                                       <td>
                                           <a href="<c:url value="/editprofile/${user.id}/preedit" />">
                                               <i class="fa fa-edit" aria-hidden="true"></i>
                                           </a>
                                           <a href="<c:url value="/delete/${user.id}" />">
                                               <i class="fa fa-remove" aria-hidden="true"></i>
                                           </a>
                                       </td>
                                   </tr>
                               </c:forEach>
                               </tbody>
                           </table>
                        </div>
                    </div>
                    <c:if test="${person.role == 'admin'}">
                        <div class="form-group">
                            <a href="<c:url value="/adduser"/>" class="btn btn-primary">
                                <i class="fa fa-plus-square">
                                    <label>Add new user</label>
                                </i>
                            </a>
                            <a href="<c:url value="/downloadCSV/users"/>" class="btn btn-primary">
                                <i class="fa fa-download">
                                    <label>Download as Excel (CSV)</label>
                                </i>
                            </a>
                            <a href="<c:url value="/uploadUsersForm"/>" class="btn btn-primary">
                                <i class="fa fa-upload">
                                    <label>Upload users CSV</label>
                                </i>
                            </a>
                            <c:if test="${delete_msg != null}">
                                <p class="alert alert-success">
                                    ${delete_msg}
                                </p>
                            </c:if>

                        </div>
                    </c:if>
                </div>

                <!-- Right side block -->
                <%@include file="includes/blocks.jsp"%>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp"%>