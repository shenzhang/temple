package temple.sql.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static temple.sql.config.GlobalConfiguration.getGlobalConfiguration;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 8:32 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContextTest.xml"
})
@Transactional
@TransactionConfiguration
@Ignore
public class GlobalConfigurationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        getGlobalConfiguration().reset();
    }
}