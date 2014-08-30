<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div style="color:red">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </div>
</c:if>

<form class="form-horizontal" role="form" action="j_spring_security_check" method="post" style="padding-top:50px">
    <div class="form-group">
        <label for="username" class="col-sm-offset-2 col-sm-2 control-label">User ID</label>
        <div class="col-sm-8">
            <input id="username" type="text" name="j_username" placeholder="User ID"/>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-offset-2 col-sm-2 control-label">Password</label>
        <div class="col-sm-8">
            <input id="password" type="password" name="j_password" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-8">
            <input type="submit" class="btn btn-primary" value="Login"/>
        </div>
    </div>
</form>

<script>
    $(function() {
        $('#username').get(0).focus();
    });
</script>