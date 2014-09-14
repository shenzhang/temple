<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <c:if test="${not empty messageStyle and not emptymessageContent}">
        <div class="row" style="margin-bottom: 10px">
            <div class="col-lg-12">
                <c:set var="style" value="label-success"/>
                <c:if test="${messageStyle eq 'failed'}">
                    <c:set var="style" value="label-danger"/>
                </c:if>
                <span class="label ${style}">${messageContent}</span>
            </div>
        </div>
    </c:if>

    <div class="row">
        <div class="col-lg-12">
            <form:form id="memberForm" commandName="member" method="post">
                <table style="width:100%; border-collapse: collapse;">
                    <tr class="table-title-row">
                        <td colspan="4" class="border-bottom">
                            Edit Member
                            <form:hidden path="id"/>
                        </td>
                    </tr>

                    <tr>
                        <td style="width:150px">Chinese Last Name:</td>
                        <td>
                            <form:input path="chineseLastName"/>
                            <form:errors path="chineseLastName" cssClass="error"/>
                        </td>
                        <td style="width:260px">Chinese First Name:</td>
                        <td>
                            <form:input path="chineseFirstName"/>
                            <form:errors path="chineseFirstName" cssClass="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>English First Name:</td>
                        <td>
                            <form:input path="firstName"/>
                            <form:errors path="firstName" cssClass="error"/>
                        </td>
                        <td>English Last Name:</td>
                        <td>
                            <form:input path="lastName"/>
                            <form:errors path="lastName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr class="table-title-row">
                        <td colspan="2">
                            Member Details
                        </td>
                        <td colspan="2">
                            Temple Details
                        </td>
                    </tr>

                    <tr>
                        <td>DOB:</td>
                        <td>
                            <form:input class="datepicker" id="dob" path="dob"/>
                            <form:errors path="dob" cssClass="error"/>
                        </td>
                        <td>Membership Acquisition Template:</td>
                        <td>
                            <form:select path="membershipAcquisitionTempleCode">
                                <form:options items="${allTemples}" itemValue="membershipAcquisitionTempleCode"
                                              itemLabel="membershipAcquisitionTempleName"/>
                            </form:select>
                            <form:errors path="membershipAcquisitionTempleCode" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Home:</td>
                        <td>
                            <form:input path="memberContact.homePhone"/>
                            <form:errors path="memberContact.homePhone" cssClass="error"/>
                        </td>
                        <td>Membership Acquisition Date:</td>
                        <td>
                            <form:input class="datepicker" id="membershipAcquisitionDate" path="membershipAcquisitionDate"/>
                            <form:errors path="membershipAcquisitionDate" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Mobile:</td>
                        <td>
                            <form:input path="memberContact.mobilePhone"/>
                            <form:errors path="memberContact.mobilePhone" cssClass="error"/>
                        </td>
                        <td>Membership Acquisition Lunar Date:</td>
                        <td>

                        </td>
                    </tr>

                    <tr>
                        <td>Email:</td>
                        <td>
                            <form:input path="memberContact.email"/>
                        </td>
                        <td>Membership Acquisition City:</td>
                        <td>
                            <form:select path="membershipAcquisitionCityCode">
                                <form:options items="${allCities}" itemValue="membershipAcquisitionCityCode"
                                              itemLabel="membershipAcquisitionCityName"/>
                            </form:select>
                        </td>
                    </tr>

                    <tr>
                        <td rowspan="4">Address:</td>
                        <td>
                            <form:input path="memberContact.addressLine1" placeholder="Address Line1"/>
                        </td>

                        <td>Master:</td>
                        <td>
                            <form:input path="masterName"/>
                            <form:errors path="masterName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.addressLine2" placeholder="Address Line2"/>
                        </td>

                        <td>Introducer:</td>
                        <td>
                            <form:input path="introducerName"/>
                            <form:errors path="introducerName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.suburb" placeholder="Suburb"/>
                        </td>

                        <td>Guarantor:</td>
                        <td>
                            <form:input path="guarantorName"/>
                            <form:errors path="guarantorName" cssClass="error"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:input path="memberContact.state" cssStyle="width: 100px" placeholder="State"/>
                            <form:input path="memberContact.postcode" cssStyle="width: 100px;" placeholder="Postcode"/>
                        </td>

                        <td>Member Purification Date:</td>
                        <td>
                            <form:input class="datepicker" id="memberPurificationDate" path="memberPurificationDate"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Last Update Date:</td>
                        <td>
                            <fmt:formatDate value="${member.lastUpdateDate}" pattern="MM/dd/yyyy"/>
                        </td>

                        <td>Member Family Temple Date:</td>
                        <td>
                            <form:input class="datepicker" id="memberFamilyTempleDate" path="memberFamilyTempleDate"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Last Update By:</td>
                        <td>
                            <c:out value="${member.lastModifyUser.name}" escapeXml="true"/>
                        </td>

                        <td>Member Group Number:</td>
                        <td>
                            <form:input path="groupNumber"/>
                            <form:errors path="groupNumber" cssClass="error"/>
                        </td>
                    </tr>

                    <tr class="table-title-row">
                        <td colspan="4">
                            Member Notes
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4">
                            <div style="margin-bottom: 5px" title="click to add new note">
                                <span id="btnAddNote" class="label label-success" style="cursor: pointer">Add Note</span>
                            </div>

                            <table id="noteTable" class="table table-hover">
                                <c:forEach items="${member.memberNotes}" var="note">
                                    <tr id="note-${note.noteId}">
                                        <td>
                                            <span class="note-content">
                                                <c:out value="${note.note}" escapeXml="true"/>
                                            </span>
                                            <span class="label label-warning btnEditNote" style="cursor: pointer">Edit</span>
                                            <span class="label label-danger btnDeleteNote" style="cursor: pointer">Delete</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>

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

<div id="noteDialog" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 id="noteDialogTitle" class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <textarea id="noteContent" style="width: 100%; height: 100px"></textarea>
            </div>
            <div class="modal-footer">
                <button id="btnOk" type="button" class="btn btn-primary">Save Note</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $(function() {
        var memberId = ${member.id};

        function addNote() {
            $('#noteDialogTitle').text('Add Note');

            var button = $('#btnOk');
            button.off('click', '**');
            button.on('click', function() {
                var note = $('#noteContent').val();
                var url = '/members/' + memberId + '/notes';
                $.post(url, {
                    note: note
                }, function(data) {
                    console.log(data);
                }, 'json');

            });
            $('#noteDialog').modal('show');
        }

        function updateNote(noteId, note) {
            $('#noteDialogTitle').text('Edit Note');
            $('#noteContent').val(note);

            var button = $('#btnOk');
            button.off('click', '**');
            button.on('click', function() {

            });
            $('#noteDialog').modal('show');
        }

        function deleteNote(noteId, $row) {
            if (!window.confirm('Are you sure to delete this note?')) {
                return;
            }

            $.ajax({
                type: 'delete',
                url: '/members/' + memberId + '/notes/' + noteId,
                success: function() {
                    $row && $row.remove();
                    $('#noteDialog').modal('hide');
                }
            });
        }

        $('#btnAddNote').click(function() {
            addNote();
        });

        $('#noteTable .btnEditNote').click(function() {
            var $span = $(this);
            var $tr = $(this).parent().parent();
            var noteId = $tr.attr('id').substring(5);
            var note = $tr.find('.note-content').text();
            updateNote(noteId, $.trim(note));
        });

        $('#noteTable .btnDeleteNote').click(function() {
            var $span = $(this);
            var $tr = $(this).parent().parent();
            var noteId = $tr.attr('id').substring(5);
            deleteNote(noteId, $tr);
        });
    });
</script>