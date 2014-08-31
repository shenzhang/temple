package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.Member;
import temple.model.SearchMemberInfo;
import temple.sql.JdbcTempalteAppender;
import temple.sql.JdbcTemplateHelper;

import java.util.List;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:55 PM
 */
@Repository
public class MemberDao extends AutowiredJdbcDaoSupport {
    public Member getMemberById(int id) {
        return new JdbcTemplateHelper(getJdbcTemplate()).queryForObject(Member.class, "SELECT * FROM T_MEMBER WHERE ID = ?", id);
    }

    public int addMember(Member member) {
        new JdbcTemplateHelper(getJdbcTemplate()).insert(member, "ID");
        return 1;
    }

    public void deleteMemberById(int id) {
        getJdbcTemplate().update("DELETE FROM T_MEMBER WHERE ID = ?", id);
    }

    public List<Member> searchMember(SearchMemberInfo info) {
        JdbcTempalteAppender appender = new JdbcTemplateHelper(getJdbcTemplate()).createAppender();
        appender.append("SELECT * FROM T_MEMBER");
        return appender.queryForList(Member.class);
    }
}
