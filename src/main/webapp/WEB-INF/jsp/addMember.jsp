<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .tr-border-bottom td {
        border-bottom:1px #5dacff solid
    }

    .td-border-top {
        border-top:1px #5dacff solid
    }

    .td-border-left {
        border-left:1px #5dacff solid
    }

    .td-border-right {
        border-right:1px #5dacff solid
    }

    .td-border-bottom {
        border-bottom:1px #5dacff solid
    }

    #memberForm td {
        padding:5px;
    }

</style>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form:form id="memberForm" commandName="member" method="post" action="addMember">
                <table style="width:100%; border-collapse: collapse;">
                    <tr>
                        <td colspan="4" class="border-bottom">
                            Add Member
                        </td>
                    </tr>

                    <tr><td colspan="4" class="td-border-bottom"></td></tr>

                    <tr>
                        <td style="width:150px">Chinese Last Name:</td>
                        <td><form:input path="chineseLastName"/></td>
                        <td style="width:260px">Chinese First Name:</td>
                        <td><form:input path="chineseFirstName"/></td>
                    </tr>
                    <tr>
                        <td>English First Name:</td>
                        <td><form:input path="firstName"/></td>
                        <td>English Last Name:</td>
                        <td><form:input path="lastName"/></td>
                    </tr>

                    <tr><td colspan="4" class="td-border-bottom"></td></tr>

                    <tr>
                        <td colspan="2">
                            Member Details
                        </td>
                        <td colspan="2">
                            Temple Details
                        </td>
                    </tr>

                    <tr><td colspan="4" class="td-border-bottom"></td></tr>

                    <tr>
                        <td>DOB:</td>
                        <td><form:input class="datepicker" id="dob" path="dob"/></td>
                        <td>Membership Acquisition Template:</td>
                        <td>
                            <form:select path="membershipAcquisitionTempleCode">
                                <form:options items="${allTemples}" itemValue="membershipAcquisitionTempleCode"
                                              itemLabel="membershipAcquisitionTempleName"/>
                            </form:select>
                        </td>
                    </tr>

                    <tr>
                        <td>Home:</td>
                        <td></td>
                        <td>Membership Acquisition Date:</td>
                        <td></td>
                    </tr>

                    <tr>
                        <td>Mobile:</td>
                        <td></td>
                        <td>Membership Acquisition Lunar Date:</td>
                        <td></td>
                    </tr>

                    <tr>
                        <td>Email:</td>
                        <td></td>
                        <td>Membership Acquisition City:</td>
                        <td></td>
                    </tr>

                    <tr>
                        <td colspan="4">
                            Member Notes
                        </td>
                    </tr>

                    <tr><td colspan="4" class="td-border-bottom"></td></tr>

                    <tr>
                        <td colspan="4">
                            <button type="submit" class="btn btn-primary">Save Member</button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>