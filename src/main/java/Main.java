import com.google.common.reflect.ClassPath;

import java.io.IOException;

/**
 * User: Zhang Shen
 * Date: 6/10/15
 * Time: 8:18 PM
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ClassPath classpath = ClassPath.from(ClassLoader.getSystemClassLoader());
        System.out.println(classpath.getResources().size());
        System.out.println(classpath.getAllClasses().size());
        System.out.println(classpath.getTopLevelClasses().size());
    }
}
