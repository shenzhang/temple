package temple.sql.config.feature;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 6:13 PM
 */
public interface GeneratedKeyFetcher {
    Object getGeneratedKey(String table, String column);
}
