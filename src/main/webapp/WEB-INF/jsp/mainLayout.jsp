<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chung Zhu Temple System</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">
    <link rel="stylesheet" href="css/temple.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
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