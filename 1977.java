class final_static_and_friend {

    final int a = 34;

    static int b = 34;

    int c = 34;

    void foo() {
        System.out.println(a + b + c);
    }

    public static void main(String[] arg) {
        new final_static_and_friend().foo();
    }
}
