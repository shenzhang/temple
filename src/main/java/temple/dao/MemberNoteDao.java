package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.MemberNote;
import temple.sql.dao.AutowiredJdbcEnhancementDaoSupport;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 12:24 PM
 */
@Repository
public class MemberNoteDao extends AutowiredJdbcEnhancementDaoSupport {
    public void addMemberNote(int memberId, MemberNote note) {
        note.setMemberId(memberId);
        jdbcEnhancement.insert(note);
    }

    public void deleteAllMemberNotes(int memberId) {
        jdbcTemplate.update("DELETE FROM T_MEMBER_NOTE WHERE MEMBER_ID = ?", memberId);
    }
}
