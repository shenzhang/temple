package temple.sql.config;

import temple.sql.config.feature.PageCreator;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 5:54 PM
 */
public class GlobalConfiguration {
    private static GlobalConfiguration globalConfiguration = new GlobalConfiguration();
    private static Object addConfigurationLocker = new Object();
    private Map<DataSource, Configuration> configurations = new HashMap<DataSource, Configuration>();

    private GlobalConfiguration() {
    }

    public static GlobalConfiguration getGlobalConfiguration() {
        return globalConfiguration;
    }

    public static Configuration getConfiguration(DataSource dataSource) {
        Configuration configuration = globalConfiguration.configurations.get(dataSource);
        if (configuration == null) {
            synchronized (addConfigurationLocker) {
                if (configuration == null) {
                    configuration = new Configuration();
                    globalConfiguration.configurations.put(dataSource, configuration);
                }
            }
        }

        return configuration;
    }

    public static void setConfiguration(DataSource dataSource, Configuration configuration) {
        synchronized (addConfigurationLocker) {
            globalConfiguration.configurations.put(dataSource, configuration);
        }
    }

    public void setPageCreator(DataSource dataSource, PageCreator pageCreator) {
        getConfiguration(dataSource).setPageCreator(pageCreator);
    }
}