package temple.sql.builder;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 8:09 PM
 */
public class InsertSqlBuilderTest {
    private static final String TABLE = "T_USER";
    private InsertSqlBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new InsertSqlBuilder(TABLE);
    }

    @Test
    public void shouldCreateCorrectInsertSqlWithSingleColumn() throws Exception {
        builder.appendColumn("name");
        String sql = builder.create();

        assertThat(sql.toUpperCase(), is("INSERT INTO T_USER(NAME) VALUES(?);"));
    }

    @Test
    public void shouldCreateCorrectInsertSqlWithMultiColumns() throws Exception {
        builder.appendColumn("name");
        builder.appendColumn("age");
        String sql = builder.create();

        assertThat(sql.toUpperCase(), is("INSERT INTO T_USER(NAME,AGE) VALUES(?,?);"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionIfThereIsNoColumnAdded() throws Exception {
        builder.create();
    }
}
