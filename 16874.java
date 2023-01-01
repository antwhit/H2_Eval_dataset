public class Scratch {

    public static void main(String args[]) {
        System.out.println("hello");
        if ("/page/show/12".matches("/page/show/[0-9]+")) System.out.println("works");
    }
}
