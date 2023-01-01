import java.util.Map;

public class PPL_Test {

    static boolean NOISY = false;

    static boolean VERY_NOISY = false;

    public static void initialize() {
        String noisy_value = System.getenv("PPL_NOISY_TESTS");
        String vnoisy_value = System.getenv("PPL_VERY_NOISY_TESTS");
        if (vnoisy_value != null) {
            VERY_NOISY = true;
            NOISY = true;
        }
        if (noisy_value != null) NOISY = true;
    }

    public static void print_if_noisy(String str) {
        if (NOISY) System.out.print(str);
    }

    public static void println_if_noisy(String str) {
        if (NOISY) System.out.println(str);
    }

    public static void print_if_noisy(long l) {
        if (NOISY) System.out.print(l);
    }

    public static void println_if_noisy(long l) {
        if (NOISY) System.out.println(l);
    }

    public static void println_if_noisy() {
        if (NOISY) System.out.println();
    }

    public static void print_if_vnoisy(String str) {
        if (VERY_NOISY) System.out.print(str);
    }

    public static void println_if_vnoisy(String str) {
        if (VERY_NOISY) System.out.println(str);
    }

    public static void print_if_vnoisy(long l) {
        if (VERY_NOISY) System.out.print(l);
    }

    public static void println_if_vnoisy(long l) {
        if (VERY_NOISY) System.out.println(l);
    }

    public static void println_if_vnoisy() {
        if (VERY_NOISY) System.out.println();
    }
}
