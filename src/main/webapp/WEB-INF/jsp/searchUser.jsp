<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form:form class="form-inline" role="form" method="post" action="searchUser" commandName="info">
                <form:errors path="*" class="error" element="div"/>
                <div class="form-group">
                    <label for="userName">用户名 (User Name):</label>
                    <form:input class="form-control" id="userName" name="userName" path="userName"/>
                </div>

                <button type="submit" class="btn btn-primary">查询 (Search)</button>
                <button id="btnCreateUser" type="button" class="btn btn-success">创建新用户 (Create New User)</button>
            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <hr/>
        </div>
    </div>

    <c:if test="${not empty messageStyle and not empty messageContent}">
        <div class="row">
            <div class="col-lg-12">
                <c:set var="style" value="label-success"/>
                <c:if test="${messageStyle eq 'failed'}">
                    <c:set var="style" value="label-danger"/>
                </c:if>
                <span class="label ${style}">${messageContent}</span>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty user}">
        <div class="row">
            <div class="col-lg-12">
                <form:form class="form-horizontal" role="form" action="updateUser" method="post" commandName="user">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <form:errors cssClass="error" path="id"/>
                            <form:hidden path="id"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">用户名 (User Name)</label>

                        <div class="col-sm-8">
                            <label class="control-label">${user.name}</label>
                            <form:errors cssClass="error" path="name"/>
                            <form:hidden path="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">密码 (Password)</label>

                        <div class="col-sm-8">
                            <form:password path="password"/>
                            <form:errors cssClass="error" path="password"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">确认密码 (Confirm Password)</label>

                        <div class="col-sm-8">
                            <form:password path="confirmPassword"/>
                            <form:errors cssClass="error" path="confirmPassword"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <input type="submit" class="btn btn-primary" value="保存 (Save)"/>
                            <input type="button" id="btnDelete" class="btn btn-warning" value="删除 (Delete)"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <form id="deleteForm" action="deleteUser" method="post">
            <input type="hidden" name="userId" value="${user.id}"/>
        </form>
    </c:if>
</div>
</div>
</div>

<div class="modal fade" id="userForm" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog" class="modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">创建新用户 (Create New User)</h4>
            </div>
            <div class="modal-body">
                <form:form commandName="newUser" method="post" action="addUser" id="createUserForm">
                    <form:errors path="*" cssClass="error"/>

                    <table style="border-collapse: separate; border-spacing: 5px;">
                        <tr>
                            <td>
                                <label>用户名 (User Name):</label>
                            </td>
                            <td>
                                <form:input path="name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>密码 (Password):</label>
                            </td>
                            <td>
                                <form:password path="password"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>确认密码 (Confirm Password):</label>
                            </td>
                            <td>
                                <form:password path="confirmPassword"/>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭 (Close)</button>
                <button id="btnSaveNewUser" type="button" class="btn btn-primary">保存 (Save)</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        function showDialog() {
            $('#userForm').modal({
                keyboard: false
            });
        }

        $('#btnCreateUser').click(function () {
            showDialog();
        });

        $('#btnSaveNewUser').click(function () {
            $('#createUserForm').get(0).submit();
        });

        $('#btnDelete').click(function () {
            $('#deleteForm').get(0).submit();
        });

        <c:if test="${showDialog == true}">
        showDialog();
        </c:if>
    });
</script>