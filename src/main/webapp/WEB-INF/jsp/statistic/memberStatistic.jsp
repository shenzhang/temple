<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">

        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <span class="label label-success" style="font-size:14px">
                全部道亲统计 (All member statistic in total)
            </span>

        </div>
    </div>

    <div class="row" style="margin-top: 10px">
        <div class="col-lg-12">
            <jsp:include page="result/memberResult.jsp"/>
        </div>
    </div>
</div>