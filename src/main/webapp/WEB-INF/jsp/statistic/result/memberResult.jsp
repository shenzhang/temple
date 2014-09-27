<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>
            Member Statistics
        </th>

        <c:forEach items="${cities}" var="city">
            <th>
                <c:out value="${city.code}"/>
            </th>
        </c:forEach>

        <th>
            Total
        </th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>No. of people acquired membership</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['0'][city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${rowTotalList['0']}"/>
        </td>
    </tr>

    <tr>
        <td>No. of purified members</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['1'][city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${rowTotalList['1']}"/>
        </td>
    </tr>

    <tr>
        <td>No. of member with family temple</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['2'][city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${rowTotalList['2']}"/>
        </td>
    </tr>

    <tr>
        <td>Total</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['3'][city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${rowTotalList['3']}"/>
        </td>
    </tr>
    </tbody>
</table>