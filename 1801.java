class FinallyTest {

    static int[] testa = null;

    public static void main(String[] args) {
        run();
    }

    public static boolean run() {
        int i = 0, j = 0, k = 0;
        try {
            i = 1;
            j = 1;
            k = 1;
            foo();
        } catch (IndexOutOfBoundsException e5) {
            i = 1;
            j = 2;
            k = 1;
        } catch (NullPointerException e) {
            i = 2;
            j = 1;
            k = 1;
        } finally {
            i += 1000;
            j += 1000;
            k += 1000;
        }
        i = i + j + k;
        return true;
    }

    public static void foo() {
        testa[4] = 0;
    }
}
