package temple.dao;

import com.google.common.base.Strings;
import org.springframework.stereotype.Repository;
import temple.sql.JdbcTempalteAppender;
import temple.sql.dao.AutowiredJdbcEnhancementDaoSupport;

import java.util.Date;

/**
 * User: shenzhang
 * Date: 9/23/14
 * Time: 8:12 PM
 */
@Repository
public class StatisticDao extends AutowiredJdbcEnhancementDaoSupport {
    public int getAcquiredMembershipCount(Date begin, Date end, String cityCode) {
        JdbcTempalteAppender appender = jdbcEnhancement.createAppender()
                .append("select count(*) from t_member where membership_acquisition_date is not null ");

        if (begin != null) {
            appender.append(" and membership_acquisition_date >= ? ", begin);
        }

        if (end != null) {
            appender.append(" and membership_acquisition_date < ? ", end);
        }

        if (!Strings.isNullOrEmpty(cityCode)) {
            appender.append(" and membership_acquisition_city_code = ? ", cityCode);
        }

        return appender.queryForObject(Integer.class);
    }

    public int getPurifiedMemberCount(Date begin, Date end, String cityCode) {
        JdbcTempalteAppender appender = jdbcEnhancement.createAppender()
                .append("select count(*) from t_member where member_purification_date is not null ");

        if (begin != null) {
            appender.append(" and member_purification_date >= ? ", begin);
        }

        if (end != null) {
            appender.append(" and member_purification_date < ? ", end);
        }

        if (!Strings.isNullOrEmpty(cityCode)) {
            appender.append(" and membership_acquisition_city_code = ? ", cityCode);
        }

        return appender.queryForObject(Integer.class);
    }

    public int getFamilyTempleCount(Date begin, Date end, String cityCode) {
        JdbcTempalteAppender appender = jdbcEnhancement.createAppender()
                .append("select count(*) from t_member where member_family_temple_date is not null ");

        if (begin != null) {
            appender.append(" and member_family_temple_date >= ? ", begin);
        }

        if (end != null) {
            appender.append(" and member_family_temple_date < ? ", end);
        }

        if (!Strings.isNullOrEmpty(cityCode)) {
            appender.append(" and membership_acquisition_city_code = ? ", cityCode);
        }

        return appender.queryForObject(Integer.class);
    }
}
