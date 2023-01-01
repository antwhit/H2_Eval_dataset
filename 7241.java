import java.util.regex.Matcher;

/**
 * This is just a test class for trying out database functions without needing to run Tomcat.
 * @author iwharris
 *
 */
public class Bootstrap {

    public static void main(String[] args) {
        DBAccess.isBootStrap = true;
        DBAccess.init();
        String out = Matcher.quoteReplacement("$");
        System.out.println(out);
        String s = "poop";
        System.out.println(s.replaceAll("poop", out));
    }
}
