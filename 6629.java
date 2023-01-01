@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ java.lang.annotation.ElementType.TYPE })
@interface Speculative {
}

class Beast {

    public int f(int a) {
        return 0;
    }
}

@Speculative
class Dog extends Beast {

    public Dog() {
        int x = 0;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
    }

    public void _p_slice_for_ctor() {
        int x = 0;
        x = 6;
    }

    public int g() {
        return 11;
    }

    public int _p_slice_for_g() {
        return 13;
    }
}

@Speculative
class Wolf extends Beast {

    public native void vmlog(String str);

    public Wolf() {
        int x = 0;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        vmlog("love " + x);
    }

    public void _p_slice_for_ctor() {
        int x = 0;
        x = 6;
    }

    public int f(int a) {
        int x = 0;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        return x;
    }

    public int _p_slice_for_f(int a) {
        return 5;
    }
}

class Hello {

    public static void main(String[] args) {
        int x = 50;
        Beast w = new Wolf();
        x -= w.f(3);
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
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        x++;
        System.out.println("x is: " + x);
    }
}
