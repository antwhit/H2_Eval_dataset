@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ java.lang.annotation.ElementType.TYPE })
@interface Speculative {
}

@Speculative
class Chick {

    private int x;

    public void f() {
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
    }

    public int getResult() {
        return x;
    }
}

class Hello {

    public static int test1() {
        Chick a = new Chick();
        Chick b = new Chick();
        for (int n = 0; n < 10; ++n) {
            a.f();
            b.f();
        }
        int result = 0;
        result += a.getResult();
        result += b.getResult();
        return result;
    }

    public static int test2() {
        Chick[] all = new Chick[6];
        for (int i = 0; i < 6; ++i) all[i] = new Chick();
        for (int n = 0; n < 10; ++n) {
            for (int i = 0; i < 6; ++i) all[i].f();
        }
        int result = 0;
        for (int i = 0; i < 6; ++i) result += all[i].getResult();
        return result;
    }

    public static void main(String[] args) {
        int result = 0;
        result = test1();
        System.out.println("result is: " + result);
    }
}
