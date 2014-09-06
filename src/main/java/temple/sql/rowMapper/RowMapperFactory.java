package temple.sql.rowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import temple.sql.meta.DatabaseMetaData;
import temple.sql.meta.TableMetaData;
import temple.sql.rowMapper.primitive.IntegerRowMapper;
import temple.sql.rowMapper.primitive.LongRowMapper;
import temple.sql.rowMapper.primitive.StringRowMapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static temple.sql.Util.column2Property;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 9:10 PM
 */
public class RowMapperFactory {
    private DatabaseMetaData metaData;
    private RowMapperCache rowMapperCache = new RowMapperCache();
    private static Map<Class<?>, RowMapper> simpleTypes = newHashMap();

    static {
        simpleTypes.put(Integer.class, new IntegerRowMapper());
        simpleTypes.put(int.class, new IntegerRowMapper());
        simpleTypes.put(Long.class, new LongRowMapper());
        simpleTypes.put(long.class, new LongRowMapper());
        simpleTypes.put(String.class, new StringRowMapper());
    }

    public RowMapperFactory(JdbcTemplate jdbcTemplate) {
        this.metaData = new DatabaseMetaData(jdbcTemplate);
    }

    public <T> RowMapper<T> createRowMapper(QueryKey<T> queryKey) {
        Class<T> clazz = queryKey.getClazz();
        String sql = queryKey.getSql();

        RowMapper<T> rowMapper = simpleTypes.get(clazz);
        if (rowMapper != null) {
            return rowMapper;
        }

        rowMapper = rowMapperCache.get(queryKey);
        if (rowMapper == null) {
            TableMetaData sqlColumns = metaData.getSqlColumns(sql);
            final List<String> columns = sqlColumns.getColumns();
            final Map<String, Field> map = newHashMap();
            for (String column : columns) {
                String property = column2Property(column);
                try {
                    Field field = clazz.getDeclaredField(property);
                    field.setAccessible(true);
                    map.put(column, field);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("Can not find correct field from class: " + clazz.getName() + " according to column: " + column);
                }
            }

            rowMapper = new ReflectRowMapper<T>(clazz, map);
            rowMapperCache.add(queryKey, rowMapper);
        }

        return rowMapper;
    }
}
