package temple.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * User: shenzhang
 * Date: 9/6/14
 * Time: 11:30 PM
 */
public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            System.out.println(random.nextInt());
        }
    }
}
