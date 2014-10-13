<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form:form class="form-inline" role="form" method="post" action="searchUser" commandName="info">
                <form:errors path="*" class="error" element="div"/>
                <div class="form-group">
                    <label for="userId">User ID:</label>
                    <form:input class="form-control" id="userId" name="userId" path="userId"/>
                </div>

                <button type="submit" class="btn btn-primary">Search User</button>
                <button id="btnCreateUser" type="button" class="btn btn-success">Create New User</button>
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
                        <label class="col-sm-2 control-label">User ID</label>

                        <div class="col-sm-10">
                            <label class="control-label">${user.id}</label>
                            <form:errors cssClass="error" path="id"/>
                            <form:hidden path="id"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">User Name</label>

                        <div class="col-sm-10">
                            <form:input path="name"/>
                            <form:errors cssClass="error" path="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Password</label>

                        <div class="col-sm-10">
                            <form:password path="password"/>
                            <form:errors cssClass="error" path="password"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Confirm Password</label>

                        <div class="col-sm-10">
                            <form:password path="confirmPassword"/>
                            <form:errors cssClass="error" path="confirmPassword"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-8">
                            <input type="submit" class="btn btn-primary" value="Save"/>
                            <input type="button" id="btnDelete" class="btn btn-warning" value="Delete"/>
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
                <h4 class="modal-title" id="myModalLabel">Create New User</h4>
            </div>
            <div class="modal-body">
                <form:form commandName="newUser" method="post" action="addUser" id="createUserForm">
                    <form:errors path="*" cssClass="error"/>

                    <table style="border-collapse: separate; border-spacing: 5px;">
                        <tr>
                            <td>
                                <label>User Name:</label>
                            </td>
                            <td>
                                <form:input path="name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Password:</label>
                            </td>
                            <td>
                                <form:password path="password"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Confirm Password:</label>
                            </td>
                            <td>
                                <form:password path="confirmPassword"/>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="btnSaveNewUser" type="button" class="btn btn-primary">Save</button>
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

        <c:if test="${showDialog}">
        showDialog();
        </c:if>
    });
</script>