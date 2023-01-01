public class fibo {

    public static void main(String[] args) {
        run();
    }

    static boolean run() {
        int i = fibo.fib(22);
        System.out.println("Fibo returned: " + i);
        return true;
    }

    static int fib(int x) {
        if (x > 2) return (fib(x - 1) + fib(x - 2)); else return (1);
    }
}
