class SuperClass {

    int y;

    SuperClass(int x) {
        y = x;
    }
}

class Pamby extends SuperClass implements Mamby {

    int x;

    int y[] = new int[3];

    int hex;

    public Pamby() {
        this(3);
    }

    public Pamby(int x) {
        super(x);
    }

    public synchronized void baz(int x, int y) {
        this.x = x;
        super.y = y;
        hex = 0x8e;
        hex = (x > hex) ? 5 : 2;
        Object thing = null;
        boolean f = true;
        boolean g = false;
        x = 0722;
        char a = 'a';
        double z = 3.4e-9;
        String stuff = "wgeoih\n\\\b";
        synchronized (this) {
            x++;
        }
    }

    public void foo() {
        y[3] = 2;
        Blurp val = new Blurp();
        Object stuff = new Object();
        val = (Blurp) (val.echo(stuff));
        ((Blurp) val).setSomething(stuff);
    }

    class Blurp {

        public Object echo(Object o) {
            return o;
        }
    }
}
