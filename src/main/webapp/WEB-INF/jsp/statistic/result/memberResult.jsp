<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>
            Member Statistics
        </th>

        <c:forEach items="${cities}" var="city">
            <th>
                <c:out value="${city.membershipAcquisitionCityCode}"/>
            </th>
        </c:forEach>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>No. of people acquired membership</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['0'][city.membershipAcquisitionCityCode]}"/>
            </td>
        </c:forEach>
    </tr>

    <tr>
        <td>No. of purified members</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['1'][city.membershipAcquisitionCityCode]}"/>
            </td>
        </c:forEach>
    </tr>

    <tr>
        <td>No. of member with family temple</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['2'][city.membershipAcquisitionCityCode]}"/>
            </td>
        </c:forEach>
    </tr>

    <tr>
        <td>Total</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['3'][city.membershipAcquisitionCityCode]}"/>
            </td>
        </c:forEach>
    </tr>
    </tbody>
</table>