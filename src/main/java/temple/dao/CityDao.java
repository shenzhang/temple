package temple.dao;

import com.github.shenzhang.ejdbc.dao.AutowiredJdbcEnhancementDaoSupport;
import org.springframework.stereotype.Repository;
import temple.model.City;

import java.util.List;

/**
 * User: shenzhang
 * Date: 9/7/14
 * Time: 2:20 PM
 */
@Repository
public class CityDao extends AutowiredJdbcEnhancementDaoSupport {
    public List<City> listAllCities() {
        return jdbcEnhancement.queryForList(City.class, "select * from t_city");
    }
}
