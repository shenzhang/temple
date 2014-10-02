package temple.sql;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import temple.model.Member;
import temple.model.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 9:43 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContextTest.xml"
})
@Transactional
@TransactionConfiguration
@Ignore
public class JdbcTemplateEnhancementTest {
    private static final String USER_TABLE = "T_USER";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplateEnhancement helper;
    private User user;

    @Before
    public void setUp() throws Exception {
        helper = new JdbcTemplateEnhancement(jdbcTemplate);
        user = new User();
    }

    @Test
    public void shouldParseTableNameFromObject() throws Exception {
        assertThat(Util.getTableName(User.class).toUpperCase(), is(USER_TABLE));
    }

    @Test
    public void shoudParseTableNameFromParentClass() throws Exception {
        assertThat(Util.getTableName(User.class).toUpperCase(), is(USER_TABLE));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionIfTheObjectIsNotAnnotatedWithTable() throws Exception {
        Util.getTableName(Object.class);
    }

    @Test
    public void shouldInsertObjectCorrectlyWithoutExcludes() throws Exception {
        clearAllRows();

        user.setId(100);
        user.setName("name");
        user.setPassword("123");

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(0));
        helper.insert(user);

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(1));
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM T_USER WHERE ID = ?", 100);

        assertTrue(sqlRowSet.next());
        assertThat(sqlRowSet.getInt("ID"), is(100));
        assertThat(sqlRowSet.getString("NAME"), is("name"));
        assertThat(sqlRowSet.getString("PASSWORD"), is("123"));
    }

    @Test
    public void shouldInsertObjectCorrectlyWithExcludes() throws Exception {
        clearAllRows();

        user.setName("name");
        user.setPassword("123");

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(0));
        helper.insert(user, "id");

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(1));
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM T_USER");

        assertTrue(sqlRowSet.next());
        assertThat(sqlRowSet.getInt("ID"), is(not(0)));
        assertThat(sqlRowSet.getString("NAME"), is("name"));
        assertThat(sqlRowSet.getString("PASSWORD"), is("123"));
    }

    @Test
    public void shouldInsertAndReturnGeneratedKey() throws Exception {
        clearAllRows();

        user.setName("name");
        user.setPassword("123");

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(0));
        long key = helper.insertAndReturnGeneratedKey("id", user, "id");

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE), is(1));
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM T_USER");

        assertTrue(sqlRowSet.next());
        assertThat(sqlRowSet.getInt("ID"), is((int)key));
        assertThat(sqlRowSet.getString("NAME"), is("name"));
        assertThat(sqlRowSet.getString("PASSWORD"), is("123"));
    }

    @Test
    public void shouldCeateNewJdbcTemplateAppender() throws Exception {
        JdbcTempalteAppender appender = helper.createAppender();
        assertNotNull(appender);
    }

    @Test
    public void shouldQueryListByClass() throws Exception {
        List<Member> members = helper.queryForList(Member.class, "SELECT * FROM T_MEMBER");
        assertThat(members.size(), is(2));
    }


    @Test
    public void shouldRollback() throws Exception {
        clearAllRows();
    }

    private void clearAllRows() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, USER_TABLE);
    }

    private void clearAllRows(String table) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, table);
    }
}
