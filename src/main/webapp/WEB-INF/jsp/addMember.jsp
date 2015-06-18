<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .tr-border-bottom td {
        border-bottom: 1px #5dacff solid
    }

    .td-border-top {
        border-top: 1px #5dacff solid
    }

    .td-border-left {
        border-left: 1px #5dacff solid
    }

    .td-border-right {
        border-right: 1px #5dacff solid
    }

    .td-border-bottom {
        border-bottom: 1px #5dacff solid
    }

    #memberForm td {
        padding: 5px;
    }

</style>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form:form id="memberForm" commandName="member" method="post">
                <table style="width:100%; border-collapse: collapse;">
                    <tr class="table-title-row">
                        <td colspan="4" class="border-bottom">
                            添加道亲 (Add Member)
                        </td>
                    </tr>

                    <tr>
                        <td style="width:150px">姓 (Last Name):</td>
                        <td colspan="3">
                            <form:input path="chineseLastName"/>
                            <form:errors path="chineseLastName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>名 (First Name):</td>
                        <td colspan="3">
                            <form:input path="chineseFirstName"/>
                            <form:errors path="chineseFirstName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr class="table-title-row">
                        <td colspan="2">
                            道亲信息
                        </td>
                        <td colspan="2">
                            佛堂信息
                        </td>
                    </tr>

                    <tr>
                        <td>性别 (Gender):</td>
                        <td>
                            <form:select path="gender" cssStyle="width:50px">
                                <form:option value="乾" label="乾"/>
                                <form:option value="坤" label="坤"/>
                            </form:select>
                        </td>

                        <td>求道地点 (Temple):</td>
                        <td>
                            <form:select path="membershipAcquisitionTempleCode">
                                <form:options items="${allTemples}" itemValue="membershipAcquisitionTempleCode"
                                              itemLabel="membershipAcquisitionTempleName"/>
                            </form:select>
                            <form:errors path="membershipAcquisitionTempleCode" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>出生日期 (DOB):</td>
                        <td>
                            <form:input class="datepicker" id="dob" path="dob"/>
                            <form:errors path="dob" cssClass="error"/>
                        </td>

                        <td>求道日期 (Date):</td>
                        <td>
                            <form:input class="datepicker" id="membershipAcquisitionDate" path="membershipAcquisitionDate"/>
                            <form:errors path="membershipAcquisitionDate" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>座机 (Home):</td>
                        <td>
                            <form:input path="memberContact.homePhone"/>
                            <form:errors path="memberContact.homePhone" cssClass="error"/>
                        </td>

                        <td>求道日期-民国 (Date):</td>
                        <td>
                            <form:input path="membershipAcquisitionLunarDate"/>
                            <form:errors path="membershipAcquisitionLunarDate" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>手机 (Mobile):</td>
                        <td>
                            <form:input path="memberContact.mobilePhone"/>
                            <form:errors path="memberContact.mobilePhone" cssClass="error"/>
                        </td>

                        <td>求道城市 (City):</td>
                        <td>
                            <form:select path="membershipAcquisitionCityCode">
                                <form:options items="${allCities}" itemValue="membershipAcquisitionCityCode"
                                              itemLabel="membershipAcquisitionCityName"/>
                            </form:select>
                        </td>
                    </tr>

                    <tr>
                        <td>邮箱 (Email):</td>
                        <td>
                            <form:input path="memberContact.email"/>
                        </td>

                        <td>点传师 (Master):</td>
                        <td>
                            <form:input path="masterName"/>
                            <form:errors path="masterName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td valign="top" rowspan="4">地址 (Address):</td>
                        <td>
                            <form:input path="memberContact.addressLine1" placeholder="Address Line1"/>
                        </td>

                        <td>引师 (Introducer):</td>
                        <td>
                            <form:input path="introducerName"/>
                            <form:errors path="introducerName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.addressLine2" placeholder="Address Line2"/>
                        </td>

                        <td>保师 (Guarantor):</td>
                        <td>
                            <form:input path="guarantorName"/>
                            <form:errors path="guarantorName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.suburb" placeholder="Suburb"/>
                        </td>

                        <td>清口日期 (Date):</td>
                        <td>
                            <form:input class="datepicker" id="memberPurificationDate" path="memberPurificationDate"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.state" cssStyle="width: 100px" placeholder="State"/>
                            <form:input path="memberContact.postcode" cssStyle="width: 100px;" placeholder="Postcode"/>
                        </td>

                        <td>安堂日期 (Date):</td>
                        <td>
                            <form:input class="datepicker" id="memberFamilyTempleDate" path="memberFamilyTempleDate"/>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td></td>

                        <td>组别 (Group Number):</td>
                        <td>
                            <form:input path="groupNumber"/>
                            <form:errors path="groupNumber" cssClass="error"/>
                        </td>
                    </tr>

                    <tr class="table-title-row">
                        <td colspan="4">
                            备注 (Notes)
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-primary">保存 (Save)</button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>