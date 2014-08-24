package temple.sql;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import temple.model.NewUser;
import temple.model.User;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
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
        "classpath:applicationContextDao.xml",
        "classpath:applicationContextTest.xml"
})
@TransactionConfiguration
public class JdbcTemplateHelperTest {
    private static final String USER_TABLE = "T_USER";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplateHelper helper;
    private User user;

    @Before
    public void setUp() throws Exception {
        helper = new JdbcTemplateHelper(jdbcTemplate);
        user = new User();
    }

    @Test
    public void shouldParseTableNameFromObject() throws Exception {
        assertThat(helper.getTableName(user).toUpperCase(), is(USER_TABLE));
    }

    @Test
    public void shoudParseTableNameFromParentClass() throws Exception {
        assertThat(helper.getTableName(new NewUser()).toUpperCase(), is(USER_TABLE));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionIfTheObjectIsNotAnnotatedWithTable() throws Exception {
        helper.getTableName(new Object());
    }

    @Test
    public void shouldTranslateProperty2ColumnCorrectly() throws Exception {
        assertThat(helper.property2Column("").toLowerCase(), is(""));
        assertThat(helper.property2Column("abc").toLowerCase(), is("abc"));
        assertThat(helper.property2Column("aBc").toLowerCase(), is("a_bc"));
        assertThat(helper.property2Column("ABC").toLowerCase(), is("a_b_c"));
        assertThat(helper.property2Column("abC").toLowerCase(), is("ab_c"));
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


    private void clearAllRows() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, USER_TABLE);
    }
}
