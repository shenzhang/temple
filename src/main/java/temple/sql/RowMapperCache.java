package temple.sql;

import org.springframework.jdbc.core.RowMapper;

import java.util.Map;

import static com.google.common.collect.Maps.newConcurrentMap;

/**
 * User: shenzhang
 * Date: 8/31/14
 * Time: 10:15 AM
 */
public class RowMapperCache {
    private static Map<QueryKey, RowMapper> map = newConcurrentMap();

    public <T> void add(QueryKey<T> key, RowMapper<T> mapper) {
        map.put(key, mapper);
    }

    public <T> RowMapper<T> get(QueryKey<T> key) {
        return (RowMapper<T>)map.get(key);
    }
}
