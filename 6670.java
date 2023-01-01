class multiple_finit {

    int foo = 99;

    class multiple_finit_inner {

        int inner = 34;

        void test() {
            System.out.println(inner);
            System.out.println(foo);
        }
    }

    void foo(String s) {
        multiple_finit_inner inn = this.new multiple_finit_inner();
        inn.test();
        System.out.println(foo);
    }

    void testx() {
    }

    public static void main(String[] arg) {
        System.out.println("Testing class `multiple_finit'...");
        new multiple_finit().foo("");
    }
}
