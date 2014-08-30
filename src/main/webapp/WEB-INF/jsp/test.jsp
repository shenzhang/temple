<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form id="form" commandName="fish" method="post">
    <form:input path="name"/>
    <button type="submit">submit</button>
</form:form>
