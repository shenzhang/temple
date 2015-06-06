package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.Temple;
import com.github.shenzhang.ejdbc.dao.AutowiredJdbcEnhancementDaoSupport;

import java.util.List;

/**
 * User: shenzhang
 * Date: 9/7/14
 * Time: 2:20 PM
 */
@Repository
public class TempleDao extends AutowiredJdbcEnhancementDaoSupport {
    public List<Temple> listAllTemples() {
        return jdbcEnhancement.queryForList(Temple.class, "select * from t_temple");
    }

    public Temple getTempleByCode(String code) {
        return jdbcEnhancement.queryForObject(Temple.class, "select * from t_temple where membership_acquisition_temple_code = ?", code);
    }
}
