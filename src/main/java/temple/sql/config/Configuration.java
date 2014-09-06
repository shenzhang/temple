package temple.sql.config;

import temple.sql.config.feature.PageCreator;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 5:58 PM
 */
public class Configuration {
    private PageCreator pageCreator;

    public PageCreator getPageCreator() {
        return pageCreator;
    }

    public void setPageCreator(PageCreator pageCreator) {
        this.pageCreator = pageCreator;
    }
}
