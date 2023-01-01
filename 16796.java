class A {

    int w = 4;

    static final int x = 4;

    static int y = 5;

    int z = 3;
}

class Constant {

    interface I {

        String TEST = "Hello World";
    }

    class B extends A implements I {

        {
            int a = super.x;
            int b = this.y;
        }

        void f() {
            int c = w;
            int d = this.z;
            String str = TEST;
        }
    }
}
