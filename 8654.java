public class SomeClass {

    public SomeClass() {
    }

    public boolean makeFalse() {
        return false;
    }

    public static void main(String[] args) {
        SomeClass sc = new SomeClass();
        if (!sc.makeFalse()) System.out.println("TEST PASSED");
    }
}
