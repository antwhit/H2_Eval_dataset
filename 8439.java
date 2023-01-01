class ExceptionTest7 {

    static int[] testa = null;

    public static void main(String[] args) {
        run();
    }

    public static boolean run() {
        try {
            foo();
        } catch (IndexOutOfBoundsException e5) {
            System.out.println(" IndexOutOfBoundsException caught");
        } catch (NullPointerException e) {
            System.out.println(" NullPointerException");
        }
        System.out.println(" At End");
        return true;
    }

    public static void foo() {
        testa[4] = 0;
    }
}
