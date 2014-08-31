package temple.sql;


import com.google.common.base.Objects;

import javax.sql.DataSource;

/**
 * User: shenzhang
 * Date: 8/31/14
 * Time: 10:04 AM
 */
class QueryKey<T> {
    private DataSource dataSource;
    private Class<T> clazz;
    private String sql;

    QueryKey(DataSource dataSource, Class<T> clazz, String sql) {
        this.dataSource = dataSource;
        this.clazz = clazz;
        this.sql = sql;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getSql() {
        return sql;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dataSource, clazz, sql);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof QueryKey)) {
            return false;
        }

        QueryKey key = (QueryKey)obj;
        return Objects.equal(key.dataSource, dataSource)
                && Objects.equal(key.clazz, clazz)
                && Objects.equal(key.sql, sql);
    }
}
