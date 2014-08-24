package temple.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import temple.model.Member;
import temple.model.SearchMemberInfo;
import temple.sql.JdbcTemplateHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:55 PM
 */
@Repository
public class MemberDao extends AutowiredJdbcDaoSupport {
    public static final RowMapper<Member> MEMBER_ROW_MAPPER = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            return member;
        }
    };

    public Member getMemberById(int id) {
        List<Member> members = getJdbcTemplate().query("SELECT * FROM T_MEMBER WHERE ID = ?", MEMBER_ROW_MAPPER, id);
        return members.isEmpty() ? null : members.get(0);
    }

    public int addMember(Member member) {
        new JdbcTemplateHelper(getJdbcTemplate()).insert(member, "ID");
        return 1;
    }

    public void updateMemberById(int id, Member memeber) {
//        getJdbcTemplate().update("UPDATE T_USER SET NAME = ?, PASSWORD = ? WHERE ID = ?",
//                user.getName(), user.getPassword(), user.getId());
    }

    public void deleteMemberById(int id) {
        getJdbcTemplate().update("DELETE FROM T_MEMBER WHERE ID = ?", id);
    }

    public List<Member> searchMember(SearchMemberInfo info) {
        return null;
    }
}
