package temple.sql.config;

import temple.sql.config.feature.GeneratedKeyFetcher;
import temple.sql.config.feature.PageCreator;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 5:58 PM
 */
public class Configuration {
    private PageCreator pageCreator;
    private GeneratedKeyFetcher keyFetcher;

    public PageCreator getPageCreator() {
        return pageCreator;
    }

    public void setPageCreator(PageCreator pageCreator) {
        this.pageCreator = pageCreator;
    }

    public GeneratedKeyFetcher getKeyFetcher() {
        return keyFetcher;
    }

    public void setKeyFetcher(GeneratedKeyFetcher keyFetcher) {
        this.keyFetcher = keyFetcher;
    }
}
