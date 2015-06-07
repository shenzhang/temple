<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            按年份统计道亲 (Display All member statistic for a selected year):

            <input type="text" value="${year}" id="inputYear" class="form-control" style="display: inline-block; width: 70px"/>

            <button id="btnQuery" class="btn btn-primary">查询 (Query)</button>
        </div>
    </div>

    <c:if test="${not empty results}">
        <div class="row">
            <div class="col-lg-12">
            <span class="label label-success" style="font-size:14px">
                统计结果 (Result):
            </span>

            </div>
        </div>

        <div class="row" style="margin-top: 10px">
            <div class="col-lg-12">
                <jsp:include page="result/memberResult.jsp"/>
            </div>
        </div>
    </c:if>
</div>

<script>
    $(function () {
        var baseUrl = '<c:url value="/statistic/memberByYear/"/>';

        $('#btnQuery').click(function () {
            var year = $('#inputYear').val();
            if (!/\d{4}/.test(year)) {
                window.alert('Please input valid year with 4 digits');
                return;
            }

            window.location.href = baseUrl + year;
        });
    });
</script>