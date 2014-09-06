package temple.sql;

import temple.sql.annotation.Table;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 9:14 PM
 */
public class Util {
    public static String property2Column(String property) {
        StringBuilder sb = new StringBuilder(property.length());
        for (char c : property.toCharArray()) {
            if (c >= 'A' && c <= 'Z' && sb.length() > 0) {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString().toUpperCase();
    }

    public static String column2Property(String column) {
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

    public static String getTableName(Class<?> clazz) {
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
