package temple.sql;

import com.google.common.base.Strings;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import temple.sql.annotation.Table;
import temple.sql.builder.InsertSqlBuilder;
import temple.sql.builder.UpdateSqlBuilder;
import temple.sql.meta.DatabaseMetaData;
import temple.sql.meta.TableMetaData;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

/**
 * User: shenzhang
 * Date: 8/23/14
 * Time: 7:48 PM
 */
public class JdbcTemplateEnhancement {
    private DatabaseMetaData metaData;
    private RowMapperCache rowMapperCache = new RowMapperCache();
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateEnhancement(JdbcTemplate template) {
        this.jdbcTemplate = template;
        this.metaData = new DatabaseMetaData(jdbcTemplate);
    }

    JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void insert(Object object, String... excludeColumns) {
        Set<String> excludesSet = newHashSet();
        if (excludeColumns != null) {
            for (String column : excludeColumns) {
                excludesSet.add(column.toUpperCase());
            }
        }

        Map<String, String> properties;
        try {
            properties = BeanUtils.describe(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String table = getTableName(object.getClass());
        InsertSqlBuilder build = new InsertSqlBuilder(table);
        List<Object> parameters = newArrayList();
        TableMetaData tableColumns = metaData.getTableColumns(table);
        Set<String> exitingColumns = newHashSet(tableColumns.getColumns());
        try {
            for (String property : properties.keySet()) {
                String column = property2Column(property);
                if (!excludesSet.contains(column) && exitingColumns.contains(column)) {
                    build.appendColumn(column);
                    parameters.add(BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        jdbcTemplate.update(build.create(), parameters.toArray());
    }

    public <T extends Number> T insertAndReturnGeneratedKey(Class<T> keyClass, String keyColumn, Object object, String... excludeColumns) {
        Set<String> excludesSet = newHashSet();
        if (excludeColumns != null) {
            for (String column : excludeColumns) {
                excludesSet.add(column.toUpperCase());
            }
        }

        Map<String, String> properties;
        try {
            properties = BeanUtils.describe(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String table = getTableName(object.getClass());
        TableMetaData tableColumns = metaData.getTableColumns(table);
        Set<String> exitingColumns = newHashSet(tableColumns.getColumns());

        Map<String, Object> parameters = newHashMap();
        try {
            for (String property : properties.keySet()) {
                String column = property2Column(property);
                if (!excludesSet.contains(column) && exitingColumns.contains(column)) {
                    parameters.put(column, BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName(table);
        insert.setGeneratedKeyName(keyColumn);
        insert.usingColumns(parameters.keySet().toArray(new String[parameters.size()]));

        Number key = insert.executeAndReturnKey(parameters);
        if (keyClass.isAssignableFrom(key.getClass())) {
            return keyClass.cast(key);
        }

        return (T)(Integer)(-1);
    }

    public void update(Object object, String where, Object... whereParameters) {
        this.update(object, null, where, whereParameters);
    }

    public void update(Object object, Collection<String> excludeColumns, String where, Object... whereParameters) {
        Set<String> excludesSet = newHashSet();
        if (excludeColumns != null) {
            for (String column : excludeColumns) {
                excludesSet.add(column.toUpperCase());
            }
        }

        Map<String, String> properties;
        try {
            properties = BeanUtils.describe(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String table = getTableName(object.getClass());
        UpdateSqlBuilder build = new UpdateSqlBuilder(table);
        List<Object> parameters = newArrayList();
        TableMetaData tableColumns = metaData.getTableColumns(table);
        Set<String> exitingColumns = newHashSet(tableColumns.getColumns());
        try {
            for (String property : properties.keySet()) {
                String column = property2Column(property);
                if (!excludesSet.contains(column) && exitingColumns.contains(column)) {
                    build.appendColumn(column);
                    parameters.add(BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!Strings.isNullOrEmpty(where)) {
            build.setWhere(where);
            parameters.addAll(newArrayList(whereParameters));
        }

        jdbcTemplate.update(build.create(), parameters.toArray());
    }

    public <T> List<T> queryForList(Class<T> clazz, String sql, Object... parameters) {
        QueryKey<T> queryKey = new QueryKey<T>(jdbcTemplate.getDataSource(), clazz, sql);
        RowMapper<T> rowMapper = rowMapperCache.get(queryKey);
        if (rowMapper == null) {
            rowMapper = generateRowMapper(clazz, sql);
            rowMapperCache.add(queryKey, rowMapper);
        }

        return jdbcTemplate.query(sql, parameters, rowMapper);
    }

    public <T> T queryForObject(Class<T> clazz, String sql, Object... parameters) {
        List<T> list = queryForList(clazz, sql, parameters);
        return list.isEmpty() ? null : list.get(0);
    }

    public JdbcTempalteAppender createAppender() {
        return new JdbcTempalteAppender(this);
    }

    <T> RowMapper<T> generateRowMapper(final Class<T> clazz, String sql) {
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

        return new ReflectRowMapper<T>(clazz, map);
    }

    String property2Column(String property) {
        StringBuilder sb = new StringBuilder(property.length());
        for (char c : property.toCharArray()) {
            if (c >= 'A' && c <= 'Z' && sb.length() > 0) {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString().toUpperCase();
    }

    String column2Property(String column) {
        StringBuilder sb = new StringBuilder();
        boolean underscore = false;
        for (char c : column.toCharArray()) {
            if (c == '_') {
                underscore = true;
                continue;
            }

            sb.append(underscore ? Character.toUpperCase(c) : Character.toLowerCase(c));
            underscore = false;
        }

        return sb.toString();
    }

    String getTableName(Class<?> clazz) {
        Table table = null;
        while (table == null && !clazz.equals(Object.class)) {
            table = clazz.getAnnotation(Table.class);
            clazz = clazz.getSuperclass();
        }


        if (table == null) {
            throw new RuntimeException("Can't retrieve table information");
        }

        return table.value();
    }
}
