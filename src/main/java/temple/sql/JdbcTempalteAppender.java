package temple.sql;

import com.google.common.base.Preconditions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 8/30/14
 * Time: 7:52 PM
 */
class JdbcTempalteAppender {
    private JdbcTemplateHelper helper;
    private JdbcTemplate jdbcTemplate;
    private StringBuilder sql = new StringBuilder();
    private List<Object> parameters = newArrayList();

    public JdbcTempalteAppender(JdbcTemplateHelper jdbcTemplateHelper) {
        this.helper = jdbcTemplateHelper;
        this.jdbcTemplate = jdbcTemplateHelper.getJdbcTemplate();
    }

    public void append(String sql, Object... parameters) {
        this.sql.append(sql);
        this.parameters.addAll(newArrayList(parameters));
    }

    String getSql() {
        return sql.toString();
    }

    Object[] getParameters() {
        return parameters.toArray(new Object[parameters.size()]);
    }

    public void execute() {
        jdbcTemplate.update(getSql(), getParameters());
    }

    public <T> List<T> queryForList(RowMapper<T> rowMapper) {
        Preconditions.checkNotNull(rowMapper);
        return jdbcTemplate.query(getSql(), getParameters(), rowMapper);
    }

    public <T> List<T> queryForList(Class<T> clazz) {
        return helper.queryForList(clazz, getSql(), getParameters());
    }

    public <T> T queryForObject(RowMapper<T> rowMapper) {
        List<T> list = queryForList(rowMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    public <T> T queryForObject(Class<T> clazz) {
        List<T> list = queryForList(clazz);
        return list.isEmpty() ? null : list.get(0);
    }
}
