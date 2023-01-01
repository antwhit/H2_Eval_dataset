import jacol.*;

public class test4 {

    public static void main(String[] args) {
        new test4();
    }

    public test4() {
        LispInterpreter lisp = InterpreterFactory.getInterpreter();
        System.out.println(lisp.eval("(+ 500 10345)"));
        lisp.exit();
    }
}
