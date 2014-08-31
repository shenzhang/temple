<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form class="form-inline" role="form" method="post" action="search">
                <div class="form-group">
                    <label for="name">Member Name:</label>
                    <input type="text" class="form-control" id="name" value="${info.name}">
                </div>

                <div class="form-group">
                    <label for="introducerName">Introducer:</label>
                    <input class="form-control" type="text" id="introducerName" value="${info.introducerName}">
                </div>
                <div class="form-group">
                    <label for="acquisitionDate">Acquisition Date:</label>
                    <input type="text" class="form-control" id="acquisitionDate" readonly="readonly" style="width: 100px">
                </div>

                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <hr/>
        </div>
    </div>

    <c:if test="${not empty result}">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Chinese Last Name</th>
                        <th>Chinese First Name</th>
                        <th>Name</th>
                        <th>Acquisition Temple Name</th>
                        <th>Acquisition Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${result}" var="member">
                        <tr>
                            <td>${member.chineseLastName}</td>
                            <td>${member.chineseFirstName}</td>
                            <td>aa</td>
                            <td>aa</td>
                            <td>aa</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>

<script>
    $(function () {
        $('#acquisitionDate').datepicker();
    });
</script>