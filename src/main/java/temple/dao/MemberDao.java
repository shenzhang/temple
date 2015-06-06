package temple.dao;

import com.github.shenzhang.ejdbc.JdbcTempalteAppender;
import com.github.shenzhang.ejdbc.dao.AutowiredJdbcEnhancementDaoSupport;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import temple.model.Member;
import temple.model.SearchMemberInfo;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:55 PM
 */
@Repository
public class MemberDao extends AutowiredJdbcEnhancementDaoSupport {
    public Member getMemberById(int id) {
        return jdbcEnhancement.queryForObject(Member.class, "SELECT * FROM T_MEMBER WHERE ID = ? ORDER BY ID", id);
    }

    public int addMember(Member member) {
        return (int)jdbcEnhancement.insertAndReturnGeneratedKey("T_MEMBER", member, "id");
    }

    public void updateMember(Member member) {
        jdbcEnhancement.update("T_MEMBER", member, newArrayList("id"), "id = ?", member.getId());
    }

    public void deleteMemberById(int id) {
        jdbcTemplate.update("DELETE FROM T_MEMBER WHERE ID = ?", id);
    }

    public List<Member> searchMember(SearchMemberInfo info) {
        JdbcTempalteAppender appender = jdbcEnhancement.createAppender();
        appender.append("SELECT M.* FROM T_MEMBER M WHERE 1=1 ");
        if (info != null) {
            if (!isNullOrEmpty(info.getName())) {
                appender.append(" AND M.CHINESE_LAST_NAME = ? ", info.getName());
            }
            if (!isNullOrEmpty(info.getIntroducerName())) {
                appender.append(" AND M.INTRODUCER_NAME = ? ", info.getIntroducerName());
            }
            if (info.getAcquisitionYear() != null) {
                DateTime begin = new DateTime(info.getAcquisitionYear(), 1, 1, 0, 0);
                DateTime end = begin.plusYears(1);
                appender.append(" AND M.MEMBERSHIP_ACQUISITION_DATE >= ? AND M.MEMBERSHIP_ACQUISITION_DATE < ?", begin.toDate(), end.toDate());
            }
        }
        appender.append(" ORDER BY M.ID");
        return appender.queryForList(Member.class);
    }
}
