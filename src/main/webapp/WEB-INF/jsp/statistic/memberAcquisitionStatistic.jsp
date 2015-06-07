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
                按全部年份统计道亲 (Display member statistic in total listed by years for Member):
            </span>

        </div>
    </div>

    <div class="row" style="margin-top: 10px">
        <div class="col-lg-12">
            <jsp:include page="result/memberResultByYear.jsp"/>
        </div>
    </div>
</div>