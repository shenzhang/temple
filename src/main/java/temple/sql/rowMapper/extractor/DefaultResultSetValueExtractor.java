package temple.sql.rowMapper.extractor;

import java.lang.reflect.Method;
import java.sql.ResultSet;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 7:25 PM
 */
public class DefaultResultSetValueExtractor implements ResultSetValueExtractor {
    private Method resultSetMethod;

    public DefaultResultSetValueExtractor(String methodName) {
        try {
            this.resultSetMethod = ResultSet.class.getMethod(methodName, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object extract(ResultSet rs, String column) {
        try {
            return resultSetMethod.invoke(rs, column);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
