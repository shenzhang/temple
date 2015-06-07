<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>
            年份 (Year)
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
    <c:forEach var="yearResult" items="${results}">
        <tr>
            <td>${yearResult.year}</td>

            <c:forEach items="${cities}" var="city">
                <td>
                    <c:out value="${yearResult.result[city.code]}"/>
                </td>
            </c:forEach>
            <td>
                <c:out value="${yearResult.total}"/>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <td>
            总计 (Total)
        </td>

        <c:forEach items="${cities}" var="city">
            <td>
                <c:out value="${cityTotal[city.code]}"/>
            </td>
        </c:forEach>
        <td>
            <c:out value="${total}"/>
        </td>
    </tr>
    </tbody>
</table>