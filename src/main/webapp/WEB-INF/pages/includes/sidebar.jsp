    <!-- Sidebar -->
    <div id="sidebar-wrapper">

        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a class="navbar-brand" href="<c:url value="/home"/>">Spring MVC</a>
                <!--div id="header" class="header-panel">
                    <div id="header-content">
                        <div id="logo">
                            <img src="<c:url value="../../../../resources/static/img/logo.jpg"/>" />
                        </div>
                    </div>
                </div-->
                <hr>
                <div class="userinfo_container">
                    <c:if test="${not empty person.profileImage}">
                        <img src="${contextPath}/resources/profile_pictures/${person.profileImage}" id="profile-image"/>
                    </c:if>
                    <c:if test="${empty person.profileImage}">
                        <img src="${contextPath}/resources/images/no-user.png" id="profile-image"/>
                    </c:if>

                    <div class="dropdown my-dropdown">
                        <button class="btn btn-primary btn-transparent dropdown-toggle" type="button" data-toggle="dropdown">
                            ${person.firstname} ${person.lastname}
                            <span class="caret"></span></button>

                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/profile/${person.id}" />">Profile</a></li>
                            <li><a href="#">Stuff</a></li>
                            <li><div class="dropdown-divider"></div></li>
                            <li><a href="<c:url value="/static" />">Log out</a></li>
                        </ul>
                    </div>

                    <!--a href="<c:url value="/static" />" class="signout">
                            <i class="fa fa-sign-out" aria-hidden="true"></i>
                        </a-->
                </div>
            </li>

            <li><a href="<c:url value="/home"/>">Home</a></li>
            <li><a href="<c:url value="/viewpersoncourses/${person.id}"/>">My Courses</a></li>
            <li><a href="<c:url value="/viewpersonproducts/${person.id}"/>">My Books</a></li>
            <li><a href="<c:url value="/allcourses"/>">Available courses</a></li>
            <li><a href="<c:url value="/allproducts"/>">Book Library</a></li>
        </ul>
    </div>
    <div class="toggle-wrap">
        <i class="fa fa-bars fa-3x" id="menu-toggle"></i>
    </div>


<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    //By default
    if($(window).width() <= 992){
        $("#wrapper").removeClass("toggled");
    }else{
        $("#wrapper").addClass("toggled");
    }
    //By resize
    $(window).resize(function(){
        if($(window).width() <= 992){
            $("#wrapper").removeClass("toggled");
        }else{
            $("#wrapper").addClass("toggled");
        }
    })
</script>

