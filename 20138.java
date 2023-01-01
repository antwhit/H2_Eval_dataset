public class CrwTest1 {

    static int f1;

    private int s(int i) {
        int j;
        System.out.println("in s()");
        f1 = i;
        System.out.println("in s()");
        j = g(i);
        return j;
    }

    private int g(int i) {
        System.out.println("in g()");
        return i;
    }

    public static void main(String[] args) {
        CrwTest1 temp = new CrwTest1();
        int i = temp.s(1);
        System.out.println("Hello world1");
        System.out.println("Hello world2");
        System.out.println("Hello world3");
        System.out.println("Hello world4");
        System.out.println("Hello world5");
        System.out.println("Hello world6");
        System.out.println("Hello world7");
    }
}
