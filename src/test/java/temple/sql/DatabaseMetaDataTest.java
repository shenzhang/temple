package temple.sql;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import temple.sql.meta.DatabaseMetaData;
import temple.sql.meta.TableMetaData;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 10:56 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContextTest.xml"
})
@Transactional
@TransactionConfiguration
@Ignore
public class DatabaseMetaDataTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DatabaseMetaData metaData;

    @Before
    public void setUp() throws Exception {
        metaData = new DatabaseMetaData(jdbcTemplate);
    }

    @Test
    public void shouldRetireveColumnMetaDataCorrectly() throws Exception {
        TableMetaData metaData = this.metaData.getTableColumns("T_USER");

        assertThat(metaData.getColumns().size(), is(3));
        assertTrue(metaData.hasColumn("ID"));
        assertTrue(metaData.hasColumn("NAME"));
        assertTrue(metaData.hasColumn("PASSWORD"));
    }
}
