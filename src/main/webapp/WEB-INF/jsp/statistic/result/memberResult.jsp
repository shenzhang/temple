<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>
            道亲统计 (Member Statistics)
        </th>

        <c:forEach items="${cities}" var="city">
            <th>
                <c:out value="${city.code}"/>
            </th>
        </c:forEach>

        <th>
            总计 (Total)
        </th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>求道人数 (No. of people acquired membership)</td>

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
        <td>清口人数 (No. of purified members)</td>

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
        <td>安堂人数 (No. of member with family temple)</td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${results['2'][city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${rowTotalList['2']}"/>
        </td>
    </tr>
    </tbody>
</table>