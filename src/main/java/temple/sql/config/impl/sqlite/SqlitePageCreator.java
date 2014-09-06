package temple.sql.config.impl.sqlite;

import temple.sql.config.feature.PageCreator;

/**
 * User: shenzhang
 * Date: 8/30/14
 * Time: 8:44 PM
 */
public class SqlitePageCreator implements PageCreator {
    @Override
    public String createPage(String sql, int offset, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from (").append(sql).append(")");
        sb.append(" limit ").append(limit).append(" offset ").append(offset);
        return sb.toString();
    }
}
