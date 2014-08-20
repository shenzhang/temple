<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div style="color:red">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </div>
</c:if>

<form action="j_spring_security_check" method="post">
    <input type="text" name="j_username"/>
    <input type="text" name="j_password"/>
    <input type="submit" class="btn btn-primary" value="Login"/>
</form>