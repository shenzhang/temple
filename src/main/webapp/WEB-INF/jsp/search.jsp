<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form:form class="form-inline" role="form" method="post" action="search" commandName="info">
                <div class="form-group">
                    <label for="name">Member Name:</label>
                    <form:input type="text" class="form-control" id="name" path="name"/>
                </div>

                <div class="form-group">
                    <label for="introducerName">Introducer:</label>
                    <form:input class="form-control" type="text" id="introducerName" path="introducerName"/>
                </div>
                <div class="form-group">
                    <label for="acquisitionDate">Acquisition Date:</label>
                    <form:input type="text" class="form-control datepicker" id="acquisitionDate" path="acquisitionDate"/>
                </div>

                <button type="submit" id="btnMemberSearch" class="btn btn-primary">Search</button>
            </form:form>
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
                        <tr class="clickable-row" title="click to edit member" onclick="editMember(${member.id})">
                            <td>${member.chineseLastName}</td>
                            <td>${member.chineseFirstName}</td>
                            <td>${member.lastName}, ${member.firstName}</td>
                            <td>
                            ${member.acquisitionTemple.membershipAcquisitionTempleName}
                            </td>
                            <td>
                            <%--${member.membershipAcquisitionDate}--%>
                                <fmt:formatDate value="${member.membershipAcquisitionDate}" pattern="MM/dd/yyyy"/>
                            </td>
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
        $('#btnMemberSearch').click(function() {
            if (!temple.validateDate($('#acquisitionDate'), 'Acquisition Date')) return false;
        });
    });

    function editMember(memberId) {
        window.location.href = "editMember.jsp?memberId=" + memberId;
    }
</script>