<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand" href="#">Chung Zhu Temple System</span>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!-- member sub menus -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Member <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="search"><a href="<c:url value='/search'/>">Search Member</a></li>
                        <li id="addMember"><a href="<c:url value='/members/new'/>">New Member</a></li>
                    </ul>
                </li>

                <!-- statistic sub menus -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Statistic <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value='/statistic/memberInTotal'/>">1. All member statistic in total</a></li>
                        <li><a href="<c:url value='/statistic/memberByYear'/>">2. All member statistic for a selected year</a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value='/statistic/acquisition'/>">3. All membership acquisition statistic</a></li>
                        <li><a href="<c:url value='/statistic/purified'/>">4. All purified member statistic</a></li>
                        <li><a href="<c:url value='/statistic/familyTemple'/>">5. All family temple member statistic</a></li>
                    </ul>
                </li>

                <!-- users -->
                <li id="searchUser"><a href="<c:url value='/searchUser'/>">User Management</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search" method="post" action="<c:url value='/search'/>">
                <div class="form-group">
                    <input id="menuSearchName" name="name" type="text" class="form-control" placeholder="Last Chiness Name" value="${bannerSearchName}"/>
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="j_spring_security_logout">Logout</a></li>
            </ul>
        </div>
    </div>
</div>

<script>
    $(function() {
        var activeMenu = '${menu}';
        if (activeMenu) {
            $('#' + activeMenu).addClass('active');
        }
    });
</script>