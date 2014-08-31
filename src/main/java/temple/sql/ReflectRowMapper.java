package temple.sql;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: shenzhang
 * Date: 8/31/14
 * Time: 10:26 AM
 */
class ReflectRowMapper<T> implements RowMapper<T> {
    private static final Map<Class<?>, Method> map = newHashMap();

    static {
        try {
            map.put(String.class, ResultSet.class.getMethod("getString", String.class));
            map.put(Date.class, ResultSet.class.getMethod("getDate", String.class));
            map.put(int.class, ResultSet.class.getMethod("getInt", String.class));
            map.put(Integer.class, ResultSet.class.getMethod("getInt", String.class));
            map.put(long.class, ResultSet.class.getMethod("getLong", String.class));
            map.put(Long.class, ResultSet.class.getMethod("getLong", String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Initialize class ReflectRowMapper error", e);
        }
    }

    private Class<T> clazz;
    private Map<String, Field> column2FieldMapper;

    ReflectRowMapper(Class<T> clazz, Map<String, Field> column2FieldMapper) {
        this.clazz = clazz;
        this.column2FieldMapper = column2FieldMapper;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T object;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can not instance query object: " + clazz.toString(), e);
        }

        for (String column : column2FieldMapper.keySet()) {
            Field field = column2FieldMapper.get(column);
            Object value = getTargetValue(rs, column, field.getType());
            try {
                field.set(object, value);
            } catch (Exception e) {
                throw new RuntimeException("Set value error for column: " + column, e);
            }
        }

        return object;
    }

    private Object getTargetValue(ResultSet rs, String columnName, Class<?> fieldClass) throws SQLException {
        Method method = map.get(fieldClass);
        if (method == null) {
            throw new RuntimeException("Can not find ResultSet value extractor for class: " + fieldClass.getName());
        }

        Object value;
        try {
            value = method.invoke(rs, columnName);
        } catch (Exception e) {
            throw new RuntimeException("Extract value from ResultSet failed for class: " + fieldClass.getName(), e);
        }
        return value;
    }
}
