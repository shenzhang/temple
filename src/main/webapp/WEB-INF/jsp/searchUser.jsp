<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form class="form-inline" role="form" method="post" action="searchUser">
                <div class="form-group">
                    <label for="userId">User ID:</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="User ID"
                           value="${info.userId}">
                </div>

                <button type="submit" class="btn btn-primary">Search</button>
                <button id="btnCreateUser" type="button" class="btn btn-success">Create New User</button>
            </form>

            <c:if test="${not empty user}">

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
                    <form:errors/>

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

        <c:if test="${showDialog}">
        showDialog();
        </c:if>
    });
</script>