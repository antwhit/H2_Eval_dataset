public class NullTests {

    public static void main() {
        NullTests n = null;
        if (n == null) n = new NullTests();
        if (n == null) n = null;
        if (null == n) n = new NullTests();
        if (null == n) n = null;
    }
}
