package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.MemberNote;
import temple.sql.dao.AutowiredJdbcEnhancementDaoSupport;

import java.util.List;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 12:24 PM
 */
@Repository
public class MemberNoteDao extends AutowiredJdbcEnhancementDaoSupport {
    public int addMemberNote(int memberId, MemberNote note) {
        note.setMemberId(memberId);
        return (int)jdbcEnhancement.insertAndReturnGeneratedKey("note_id", note, "note_id");
    }

    public MemberNote getMemberNote(int noteId) {
        return jdbcEnhancement.queryForObject(MemberNote.class, "select * from t_member_note where note_id = ?", noteId);
    }

    public void updateMemberNote(int noteId, MemberNote note) {
        jdbcTemplate.update("update t_member_note set note = ? where note_id = ?", note.getNote(), noteId);
    }

    public void deleteAllMemberNotes(int memberId) {
        jdbcTemplate.update("DELETE FROM T_MEMBER_NOTE WHERE MEMBER_ID = ?", memberId);
    }

    public void deleteMemberNote(int noteId) {
        jdbcTemplate.update("DELETE FROM T_MEMBER_NOTE WHERE NOTE_ID = ?", noteId);
    }

    public List<MemberNote> listMemberNotes(int memberId) {
        return jdbcEnhancement.queryForList(MemberNote.class, "select * from t_member_note where member_id = ?", memberId);
    }
}
