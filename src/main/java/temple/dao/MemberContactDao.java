package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.MemberContact;
import com.github.shenzhang.ejdbc.dao.AutowiredJdbcEnhancementDaoSupport;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 12:24 PM
 */
@Repository
public class MemberContactDao extends AutowiredJdbcEnhancementDaoSupport {
    public void addMemberContact(int memberId, MemberContact memberContact) {
        memberContact.setMemberId(memberId);
        jdbcEnhancement.insert("T_MEMBER_CONTACT", memberContact);
    }

    public MemberContact getMemberContact(int memberId) {
        return jdbcEnhancement.queryForObject(MemberContact.class, "SELECT * FROM T_MEMBER_CONTACT WHERE MEMBER_ID = ?", memberId);
    }

    public void updateMemberContact(int memberId, MemberContact memberContact) {
        jdbcEnhancement.update("T_MEMBER_CONTACT", memberContact, newArrayList("MEMBER_ID"), "MEMBER_ID = ?", memberId);
    }

    public void deleteMemberContact(int memberId) {
        jdbcTemplate.update("DELETE FROM T_MEMBER_CONTCAT WHERE MEMBER_ID = ?", memberId);
    }
}
