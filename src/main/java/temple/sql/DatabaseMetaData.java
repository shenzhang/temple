package temple.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import static com.google.common.collect.Maps.newConcurrentMap;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 10:56 PM
 */
public class DatabaseMetaData {
    private static Map<String, TableMetaData> metaDataCache = newConcurrentMap();
    private MetaDataExtractor extractor = new MetaDataExtractor();
    private JdbcTemplate jdbcTemplate;

    public DatabaseMetaData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TableMetaData getTableColumns(String table) {
        if (metaDataCache.containsKey(table)) {
            return metaDataCache.get(table);
        }

        TableMetaData values = jdbcTemplate.query("SELECT * FROM " + table, extractor);
        metaDataCache.put(table, values);

        return values;
    }

    private static class MetaDataExtractor implements ResultSetExtractor<TableMetaData> {
        @Override
        public TableMetaData extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<String, ColumnMetaData> value = newHashMap();

            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                String name = metaData.getColumnName(i);
//                int type = metaData.getColumnType(i);
                value.put(name, new ColumnMetaData(name, null));
            }

            return new TableMetaData(value);
        }
    }
}