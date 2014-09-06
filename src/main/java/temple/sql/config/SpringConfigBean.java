package temple.sql.config;

import org.springframework.beans.factory.InitializingBean;
import temple.sql.config.feature.PageCreator;

import javax.sql.DataSource;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 5:53 PM
 */
public class SpringConfigBean implements InitializingBean {
    private DataSource dataSource;
    private PageCreator pageCreator;

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = GlobalConfiguration.getConfiguration(dataSource);
        configuration.setPageCreator(pageCreator);
    }
}
