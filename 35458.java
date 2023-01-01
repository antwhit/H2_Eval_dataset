class A {

    final int z = 10;

    final int w = 11;

    public static void main(String[] args) {
        A a = new A();
        a.test1();
        a.test2();
        a.test3();
        a.test4();
        a.test5();
        a.test6();
        a.test7();
        a.test8();
        a.test9();
        a.test10();
        a.test11();
        a.test12();
        a.test14();
    }

    public int test1() {
        return z;
    }

    public int test2() {
        return z + 1;
    }

    public int test3() {
        return 1 + 20;
    }

    public int test4() {
        int g = z + 1;
        return g;
    }

    public int test5() {
        int g = 1 + 20;
        return g;
    }

    public int test6() {
        int m = z;
        int g = 1 + m;
        m = m + 1;
        g = 1 + m;
        return g;
    }

    public int test7() {
        int g = 1 + z;
        int k = g / 2;
        return (g + z) + 10 + (z * g * (4 + k + k)) / k;
    }

    public int test8() {
        switch(z) {
            case 0:
                return 1;
            case 1:
                return 0;
            default:
                return z;
        }
    }

    public int test9() {
        int q = z;
        switch(q) {
            case 0:
                return 100;
            default:
                return 0;
        }
    }

    public int test10() {
        return (1 + z + w) + 10 + (z * 2 * (4 + w + z)) / w;
    }

    public int test11() {
        int r = 1;
        return r + ((1 + z + w) + 10 + (z * 2 * (4 + w + z)) / w);
    }

    public int test12() {
        int b = 10;
        switch(b) {
            case z:
                return 0;
            default:
                return 1;
        }
    }

    public int test14() {
        int b = 10;
        if (b == z) {
            return 0;
        } else if (b == 12) {
            return 12;
        } else {
            return 1;
        }
    }
}
