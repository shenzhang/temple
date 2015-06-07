<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="text-align: center; font-size:40px; padding-top:50px">
    忠 恕 道 院 系 统
</div>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div style="color:red">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </div>
</c:if>

<form class="form-horizontal" role="form" action="j_spring_security_check" method="post" style="padding-top:50px">
    <div class="form-group">
        <label for="username" class="col-sm-offset-4 col-sm-2 control-label">用户名 (User Name):</label>
        <div class="col-sm-6">
            <input id="username" type="text" name="j_username"/>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-offset-4 col-sm-2 control-label">密码 (Password):</label>
        <div class="col-sm-6">
            <input id="password" type="password" name="j_password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-6 col-sm-8">
            <input type="submit" class="btn btn-primary" value="登陆系统 (Login)"/>
        </div>
    </div>
</form>


<div style="text-align: center">
    <img src="img/templeBg.jpg"/>
</div>

<script>
    $(function() {
        $('#username').get(0).focus();
    });
</script>