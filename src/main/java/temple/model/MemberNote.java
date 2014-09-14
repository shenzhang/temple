package temple.model;

import temple.sql.annotation.Table;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 12:46 PM
 */
@Table("T_MEMBER_NOTE")
public class MemberNote {
    private Integer noteId;
    private int memberId;
    private String note;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
