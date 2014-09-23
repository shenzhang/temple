<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chung Zhu Temple System</title>

    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/datepicker.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/temple.css'/>">

    <script src="<c:url value='/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/js/lodash.js'/>"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/js/bootstrap-datepicker.js'/>"></script>

    <script src="<c:url value='/js/temple.js'/>"></script>
</head>
<body>
<div>
    <div id="menu">
        <tiles:insertAttribute name="menu" defaultValue="/WEB-INF/jsp/menu.jsp" />
    </div>

    <div id="content">
        <tiles:insertAttribute name="content" />
    </div>
</div>

</body>
</html>