package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.Member;
import temple.model.SearchMemberInfo;
import temple.sql.JdbcTempalteAppender;
import temple.sql.dao.AutowiredJdbcEnhancementDaoSupport;

import java.util.List;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:55 PM
 */
@Repository
public class MemberDao extends AutowiredJdbcEnhancementDaoSupport {
    public Member getMemberById(int id) {
        return jdbcEnhancement.queryForObject(Member.class, "SELECT * FROM T_MEMBER WHERE ID = ?", id);
    }

    public int addMember(Member member) {
        return (int)jdbcEnhancement.insertAndReturnGeneratedKey("id", member, "ID");
    }

    public void deleteMemberById(int id) {
        jdbcTemplate.update("DELETE FROM T_MEMBER WHERE ID = ?", id);
    }

    public List<Member> searchMember(SearchMemberInfo info) {
        JdbcTempalteAppender appender = jdbcEnhancement.createAppender();
        appender.append("SELECT * FROM T_MEMBER");
        return appender.queryForList(Member.class);
    }
}
