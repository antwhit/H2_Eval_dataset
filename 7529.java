@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ java.lang.annotation.ElementType.TYPE })
@interface Speculative {
}

class Count {

    public int n;
}

@Speculative
class Chick {

    private int x;

    public void _p_slice_for_ctor() {
    }

    public void f() {
        Count c = new Count();
        x++;
        x++;
        x++;
        x++;
        c.n = 1;
        x++;
        x++;
        x++;
        x += c.n;
        x++;
        x++;
        x++;
        x++;
    }

    public void _p_slice_for_f() {
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

    public static void main(String[] args) {
        int result = 0;
        result = test1();
        System.out.println("result is: " + result);
    }
}
