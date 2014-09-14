package temple.dao;

import org.springframework.stereotype.Repository;
import temple.model.User;
import temple.sql.dao.AutowiredJdbcEnhancementDaoSupport;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:55 PM
 */
@Repository
public class UserDao extends AutowiredJdbcEnhancementDaoSupport {
    public User getUserById(int id) {
        return jdbcEnhancement.queryForObject(User.class, "SELECT * FROM T_USER WHERE ID = ? AND ENABLED = ?", id, true);
    }

    public void updateUserById(int id, User user) {
        jdbcEnhancement.update(user, newArrayList("id"), "ID = ?", id);
    }

    public void addUser(User user) {
        jdbcEnhancement.insert(user, "id");
    }

    public void deleteUserById(int id) {
        jdbcTemplate.update("UPDATE T_USER SET ENABLED = ? WHERE ID = ?", false, id);
    }
}
