public class Exception1 {

    public static void main(String[] args) {
        Exception1 e1 = new Exception1();
        try {
            FooEx1 f = new FooEx1();
            f.bar();
        } catch (BadLanguageExceptionEx1 e) {
            e.printStackTrace();
        }
        try {
            FooEx2 f = new FooEx2();
            f.bar();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

class FooEx1 {

    public void bar() throws BadLanguageExceptionEx1 {
        throw new BadLanguageExceptionEx1();
    }
}

class FooEx2 {

    public void bar() {
        throw new NullPointerException();
    }
}

class BadLanguageExceptionEx1 extends Exception {

    public BadLanguageExceptionEx1() {
        super("Try using a real language, like Perl");
    }
}
