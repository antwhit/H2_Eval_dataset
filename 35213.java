public class Blort {

    public int test1() throws RuntimeException {
        throw new RuntimeException();
    }

    public int test2() throws RuntimeException {
        throw new RuntimeException();
    }

    public int test3() throws Error, UnsupportedOperationException {
        throw new RuntimeException();
    }

    public int test4() throws Error, UnsupportedOperationException {
        throw new RuntimeException();
    }
}
