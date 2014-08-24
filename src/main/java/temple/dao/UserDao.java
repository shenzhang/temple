package temple.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import temple.model.User;
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
public class UserDao extends AutowiredJdbcDaoSupport {
    public static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setName(rs.getString("NAME"));
            user.setPassword(rs.getString("PASSWORD"));
            return user;
        }
    };

    public User getUserById(int id) {
        List<User> users = getJdbcTemplate().query("SELECT * FROM T_USER WHERE ID = ?", USER_ROW_MAPPER, id);
        return users.isEmpty() ? null : users.get(0);
    }

    public void updateUserById(int id, User user) {
        getJdbcTemplate().update("UPDATE T_USER SET NAME = ?, PASSWORD = ? WHERE ID = ?",
                user.getName(), user.getPassword(), user.getId());
    }

    public void addUser(User user) {
        new JdbcTemplateHelper(getJdbcTemplate()).insert(user, "id");
    }

    public void deleteUserById(int id) {
        getJdbcTemplate().update("DELETE FROM T_USER WHERE ID = ?", id);
    }
}
